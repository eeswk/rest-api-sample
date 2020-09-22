package ee.swan.exception;

public class AuthFailureException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    public AuthFailureException() {
    }

    public AuthFailureException(String message) {
        super(message);
    }
}
