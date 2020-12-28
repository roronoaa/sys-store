package cn.tedu.store.service.ex;

import cn.tedu.store.ex.ServiceException;

/**
 * 登录失败时抛出的异常
 */
public class LoginException extends ServiceException {

    public LoginException() {
    }

    public LoginException(String message) {
        super(message);
    }

    public LoginException(String message, Throwable cause) {
        super(message, cause);
    }

    public LoginException(Throwable cause) {
        super(cause);
    }

    public LoginException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
