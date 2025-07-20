public class BarkRecognizer {
    private DogDoor door;

    public BarkRecognizer (DogDoor door) {
        this.door = door;
    }
    public void recognize(String bark) {
        System.out.println("BarkRecognizer: Heard a  '" + bark + "'");
        door.open();

    } 
}
//this is a class for the DogDoor that recognizes the bark and opens the door automatically.
