package pl.goreit.burger.domain.api.exceptions;

public class EntityNotFoundException extends Exception {

    public EntityNotFoundException(String message) {
        super(message);
    }
}
