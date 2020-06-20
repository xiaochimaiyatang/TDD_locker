package exception;

public class NoEmptyLockerException extends Exception {
    public NoEmptyLockerException() {
        super("fail to save the bag, no Empty Box");
    }
}
