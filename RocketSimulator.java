// RocketSimulator.java
// Simple 1D vertical rocket simulator (Euler integration)

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class RocketSimulator {

    // Physical constants
    static final double G0 = 9.81;         // gravity (m/s^2)
    static final double RHO = 1.225;       // air density at sea level (kg/m^3)

    public static void main(String[] args) {
        // --- Rocket & simulation parameters (change to experiment) ---
        double dryMass = 1000.0;       // structure mass without fuel (kg)
        double fuelMass = 4000.0;      // initial fuel (kg)
        double thrust = 150000.0;      // constant thrust while burning (N)
        double burnRate = 250.0;       // fuel consumption rate (kg/s)
        double Cd = 0.5;               // drag coefficient (dimensionless)
        double area = 3.0;             // cross-sectional area (m^2)
        double dt = 0.1;               // time step (s)
        double maxTime = 1000.0;       // max sim time (s)

        // run simulation
        Simulation sim = new Simulation(dryMass, fuelMass, thrust, burnRate, Cd, area, dt, maxTime);
        sim.run();

        // optionally save trajectory to CSV
        try {
            sim.saveCsv("rocket_trajectory.csv");
            System.out.println("Trajectory saved to rocket_trajectory.csv");
        } catch (IOException e) {
            System.err.println("Failed to save CSV: " + e.getMessage());
        }
    }

    static class Simulation {
        double dryMass, fuelMass;
        double thrust, burnRate;
        double Cd, area;
        double dt, maxTime;

        // state
        double time = 0.0;
        double altitude = 0.0;
        double velocity = 0.0;

        // store for CSV printing
        StringBuilder csv = new StringBuilder();

        Simulation(double dryMass, double fuelMass, double thrust, double burnRate, double Cd, double area, double dt, double maxTime) {
            this.dryMass = dryMass;
            this.fuelMass = fuelMass;
            this.thrust = thrust;
            this.burnRate = burnRate;
            this.Cd = Cd;
            this.area = area;
            this.dt = dt;
            this.maxTime = maxTime;

            csv.append("time,altitude,velocity,mass,acceleration,fuelMass\n");
        }

        double mass() {
            return dryMass + fuelMass;
        }

        // Drag: 0.5 * rho * Cd * A * v^2, direction opposite velocity
        double dragForce(double v) {
            return 0.5 * RHO * Cd * area * v * v;
        }

        // single simulation run
        void run() {
            boolean burning = fuelMass > 0.0;
            double nextPrint = 0.0;

            System.out.printf("%6s %10s %12s %10s %10s\n", "t(s)", "alt(m)", "vel(m/s)", "mass(kg)", "acc(m/s^2)");
            while (time <= maxTime) {
                // Determine current thrust (0 once no fuel)
                double currentThrust = burning ? thrust : 0.0;

                // Drag direction: always opposite sign of velocity.
                double drag = dragForce(Math.abs(velocity));
                if (velocity > 0) drag = drag;     // upward motion, drag downward
                else drag = -drag;                 // downward motion, drag upward

                // Net force: thrust (up) + drag (sign included) - gravity*mass (down)
                double netForce = currentThrust + drag - mass() * G0;

                // Acceleration
                double accel = netForce / mass();

                // Integrate (Euler)
                velocity += accel * dt;
                altitude += velocity * dt;

                // Burn fuel
                if (burning) {
                    double burned = burnRate * dt;
                    fuelMass -= burned;
                    if (fuelMass <= 0.0) {
                        // clamp
                        burned += fuelMass; // subtract negative
                        fuelMass = 0.0;
                        burning = false;
                    }
                }

                // Record for CSV
                csv.append(String.format("%.3f,%.6f,%.6f,%.6f,%.6f,%.6f\n",
                        time, altitude, velocity, mass(), accel, fuelMass));

                // Print every 1 second (approx)
                if (time >= nextPrint - 1e-9) {
                    System.out.printf("%6.1f %10.2f %12.2f %10.1f %10.3f\n",
                            time, Math.max(0.0, altitude), velocity, mass(), accel);
                    nextPrint += 1.0;
                }

                // Stop if rocket has fallen back to ground after launch and time>0
                if (time > 0 && altitude <= 0.0 && velocity <= 0.0 && !burning) {
                    System.out.println("Rocket has returned to ground (or failed to climb). Simulation ends.");
                    break;
                }

                time += dt;
            }

            // final summary
            System.out.println("\n--- Final state ---");
            System.out.printf("t = %.2f s, altitude = %.2f m, velocity = %.2f m/s, mass = %.2f kg, fuel left = %.2f kg\n",
                    time, altitude, velocity, mass(), fuelMass);
        }

        // save CSV
        void saveCsv(String filename) throws IOException {
            try (PrintWriter out = new PrintWriter(new FileWriter(filename))) {
                out.print(csv.toString());
            }
        }
    }
}
