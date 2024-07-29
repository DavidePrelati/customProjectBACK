package davide.prelati.customProjectBACK.exceptions;

public class NotFoundException extends RuntimeException {
    public NotFoundException(Long id) {
        super("Record con questo id non trovato");
    }

    public NotFoundException(String message) {
        super(message);
    }
}
