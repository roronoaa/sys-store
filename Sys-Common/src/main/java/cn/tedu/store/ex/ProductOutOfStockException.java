package cn.tedu.store.ex;

import cn.tedu.store.ex.ServiceException;

/**
 * 商品库存不足时抛出的异常
 */
public class ProductOutOfStockException extends ServiceException {

    public ProductOutOfStockException() {
    }

    public ProductOutOfStockException(String message) {
        super(message);
    }

    public ProductOutOfStockException(String message, Throwable cause) {
        super(message, cause);
    }

    public ProductOutOfStockException(Throwable cause) {
        super(cause);
    }

    public ProductOutOfStockException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
