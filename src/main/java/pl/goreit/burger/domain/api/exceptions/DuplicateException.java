package pl.goreit.burger.domain.api.exceptions;

public class DuplicateException extends Exception{
    public DuplicateException(String message) {
        super(message);
    }
}
