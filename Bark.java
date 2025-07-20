public class Bark {
    private String sound;

    public Bark (String sound) {
        this.sound = sound;
    }
    public String getSound() {
        return sound;
    }

    public boolean equals  (Object bark) {
        if (bark instanceof Bark) {
            Bark otherBark =  (Bark) bark;
            return this.sound.equalsIgnoreCase(otherBark.getSound()); 
            
        }
        return false;
    }    
    @Override
    public int hashCode() {
        return sound.toLowerCase().hashCode();
    }
}

// Delegation allows the app to loosely coupled.
//With  Delegation and a loosely coupled application , you can change the implemaentation of one object, like Bark, and one don't have to change all other objects in the application. The objects are  shielded from imlementation changes in other objects.

// Home lesson: Delegation shields objects from implementation changes to other obects in your software.

// Looking at the nouns (and verbs) in your use case  to figure  out classes and methods is called textual analysis.
// A good use case clearly and accurately explains what a system does, in language that is easily understood.

//  With good use case complete, textual analysis is a quick and easy way to figure out the classes in your system.

// Nouns are candidate for Classes, and verbs are candidate for Methods
