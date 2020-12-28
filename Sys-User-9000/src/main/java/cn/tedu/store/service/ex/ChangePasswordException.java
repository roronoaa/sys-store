package cn.tedu.store.service.ex;

import cn.tedu.store.ex.ServiceException;

/**
 * 更新密码时发现用户被删除或者原始密码不正确时抛出的异常
 */
public class ChangePasswordException extends ServiceException {

    public ChangePasswordException() {
    }

    public ChangePasswordException(String message) {
        super(message);
    }

    public ChangePasswordException(String message, Throwable cause) {
        super(message, cause);
    }

    public ChangePasswordException(Throwable cause) {
        super(cause);
    }

    public ChangePasswordException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
