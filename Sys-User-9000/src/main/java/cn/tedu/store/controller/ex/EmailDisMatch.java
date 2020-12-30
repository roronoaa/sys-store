package cn.tedu.store.controller.ex;

public class EmailDisMatch extends ControllerException{
    public EmailDisMatch() {
    }

    public EmailDisMatch(String message) {
        super(message);
    }

    public EmailDisMatch(String message, Throwable cause) {
        super(message, cause);
    }

    public EmailDisMatch(Throwable cause) {
        super(cause);
    }

    public EmailDisMatch(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
