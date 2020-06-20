package exception;

public class NoEmptyLockerException extends  Exception {
    public NoEmptyLockerException(String message) {
        super(message);
    }

    public NoEmptyLockerException() {
        super("fail to save the bag, no Empty Box");
    }
}
