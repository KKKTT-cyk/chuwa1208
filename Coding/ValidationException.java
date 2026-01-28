/**
 * This is a custom exception class for validation errors.
 */
public class ValidationException extends RuntimeException {
    private HttpStatus httpStatus;
    private String message;
    private List<String> errors; // For multiple validation errors

    /**
     * Constructs a new ValidationException with the specified message.
     * Default status code is BAD_REQUEST (400).
     *
     * @param message the validation error message
     */
    public ValidationException(String message) {
        super(message);
        this.httpStatus = HttpStatus.BAD_REQUEST;
        this.message = message;
    }

    /**
     * Constructs a new ValidationException with the specified HTTP status and
     * message.
     *
     * @param httpStatus the HTTP status code to return
     * @param message    the validation error message
     */
    public ValidationException(HttpStatus httpStatus, String message) {
        super(message);
        this.httpStatus = httpStatus;
        this.message = message;
    }

    /**
     * Constructs a new ValidationException with multiple validation errors.
     *
     * @param message the main validation error message
     * @param errors  list of specific validation errors
     */
    public ValidationException(String message, List<String> errors) {
        super(message);
        this.httpStatus = HttpStatus.BAD_REQUEST;
        this.message = message;
        this.errors = errors;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }
}
