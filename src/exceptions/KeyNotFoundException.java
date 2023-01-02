package exceptions;

public class KeyNotFoundException extends Exception {
    public KeyNotFoundException() {
        super("Key not found");
    }
}

