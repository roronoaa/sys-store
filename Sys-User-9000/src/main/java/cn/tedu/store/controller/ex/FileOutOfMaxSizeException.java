package cn.tedu.store.controller.ex;


public class FileOutOfMaxSizeException extends FileUploadException {
    public FileOutOfMaxSizeException() {
    }

    public FileOutOfMaxSizeException(String message) {
        super(message);
    }

    public FileOutOfMaxSizeException(String message, Throwable cause) {
        super(message, cause);
    }

    public FileOutOfMaxSizeException(Throwable cause) {
        super(cause);
    }

    public FileOutOfMaxSizeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
