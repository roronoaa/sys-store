package cn.tedu.store.service.ex;

import cn.tedu.store.ex.ServiceException;

/**
 * 修改用户信息时信息校验失败抛出的异常
 */
public class ChangeUserInfoException extends ServiceException {

    public ChangeUserInfoException() {
    }

    public ChangeUserInfoException(String message) {
        super(message);
    }

    public ChangeUserInfoException(String message, Throwable cause) {
        super(message, cause);
    }

    public ChangeUserInfoException(Throwable cause) {
        super(cause);
    }

    public ChangeUserInfoException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
