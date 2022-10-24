package by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.exception;

public class DataBaseRuntimeException extends RuntimeException {

    public DataBaseRuntimeException(String message) {
        super(message);
    }

    public DataBaseRuntimeException(Throwable cause) {
        super(cause);
    }

    public DataBaseRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }
}
