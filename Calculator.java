public class Calculator {
    int num1;
    int num2;

    public static int add(int num1, int num2) {
        return num1 + num2;
        
    }
    public static int multiply(int num1, int num2) {
        return num1 * num2;
    }

    public static int divide(int num1, int num2) {
        try {
            return num1 / num2;

        } catch  (ArithmeticException ex) {
            System.out.println("Can't Divide by zero.");
            return 0;
        }
    }

    public static void main(String [] args) {
    int sum = Calculator.add(5, 3);
    System.out.println("The sum of the two numbers is:" + sum);
    
    int result = Calculator.divide(10, 2);
    System.out.println("The division Result is: " + result);

    int multiplied = Calculator.multiply(15, 15);
    System.out.println("The result is " + multiplied);

    }
    
}

