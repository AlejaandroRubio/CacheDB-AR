package edai.Cache.exceptions;

public class DuplicatedKeyException extends Exception {
    public DuplicatedKeyException() {
        super("Duplicated key");
    }
}
