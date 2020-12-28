package cn.tedu.store.service.ex;

import cn.tedu.store.ex.ServiceException;

/**
 * 用户名已存在时抛出的异常
 */
public class UserExistException extends ServiceException {

    public UserExistException() {
    }

    public UserExistException(String message) {
        super(message);
    }

    public UserExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserExistException(Throwable cause) {
        super(cause);
    }

    public UserExistException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
