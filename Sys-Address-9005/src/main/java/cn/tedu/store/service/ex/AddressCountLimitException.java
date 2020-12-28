package cn.tedu.store.service.ex;

import cn.tedu.store.ex.ServiceException;

/**
 * 用户已添加地址达到上限时抛出的异常
 */
public class AddressCountLimitException extends ServiceException {
    public AddressCountLimitException() {
    }

    public AddressCountLimitException(String message) {
        super(message);
    }

    public AddressCountLimitException(String message, Throwable cause) {
        super(message, cause);
    }

    public AddressCountLimitException(Throwable cause) {
        super(cause);
    }

    public AddressCountLimitException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
