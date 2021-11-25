package pl.goreit.burger.domain.api.exceptions;

public class WrongValueException extends Exception{
    public WrongValueException(String message) {
        super(message);
    }
}
