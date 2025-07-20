import java.util.Timer;
import java.util.TimerTask;


public class DogDoor {
    private boolean open;
    public DogDoor() {
        this.open = false;
    }
    public void open() {
        System.out.println("The dog door opens");
        open = true;


        // modified version for the door uses the automatic identifier
        final Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            public void run() {
                close();   // the door closes itself.
                timer.cancel();
            }

        }, 5000);
    }
    public void close() {
        System.out.println("The dog door closes..");
        open = false;
    }
    public boolean isOpen() {
        return open;
    }    
}
    // }
    // public boolean isOpen() {
    //     return open;
    // }

    
// // }
// public class DogDoor {
//     private boolean open;

//     public DogDoor() {
//         this.open = false;
//     }

//     public void open() {
//         System.out.println("The dog door opens");
//         open = true;
//     }

//     public void close() {
//         System.out.println("The dog door closes..");
//         open = false;
//     }

//     public boolean isOpen() {
//         return open;
//     }
// }
