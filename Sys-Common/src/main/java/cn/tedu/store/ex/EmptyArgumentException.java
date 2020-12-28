package cn.tedu.store.ex;

/**
 * 参数为null或""时抛出的异常
 */
public class EmptyArgumentException extends ServiceException{

    public EmptyArgumentException() {
    }

    public EmptyArgumentException(String message) {
        super(message);
    }

    public EmptyArgumentException(String message, Throwable cause) {
        super(message, cause);
    }

    public EmptyArgumentException(Throwable cause) {
        super(cause);
    }

    public EmptyArgumentException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
