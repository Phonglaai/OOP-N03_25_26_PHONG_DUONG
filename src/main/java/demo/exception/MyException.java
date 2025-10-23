package demo.exception;

/**
 * MyException - Custom Exception class
 * Exception tùy chỉnh cho ứng dụng
 */
public class MyException extends Exception {

    /**
     * Constructor với message
     */
    public MyException(String message) {
        super(message);
    }

    /**
     * Constructor với message và cause
     */
    public MyException(String message, Throwable cause) {
        super(message, cause);
    }
}
