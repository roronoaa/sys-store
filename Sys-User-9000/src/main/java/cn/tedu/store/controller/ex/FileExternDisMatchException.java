package cn.tedu.store.controller.ex;

public class FileExternDisMatchException extends FileUploadException {
    public FileExternDisMatchException() {
    }

    public FileExternDisMatchException(String message) {
        super(message);
    }

    public FileExternDisMatchException(String message, Throwable cause) {
        super(message, cause);
    }

    public FileExternDisMatchException(Throwable cause) {
        super(cause);
    }

    public FileExternDisMatchException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
