package com.ql.tools.core.exception;

/**
 * WES统一异常基类
 *
 * @author wanqiuli
 * @date 2022/2/17 10:29 AM
 */
public class WesBaseException extends RuntimeException {
    private static final long serialVersionUID = -8529158938428677770L;

    private String errorMessage;

    private String errorCode;

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public WesBaseException() {
        super();
    }

    public WesBaseException(String errorMessage) {
        super((String) null);
        this.errorMessage = errorMessage;
    }

    public WesBaseException(Throwable cause) {
        super(null, cause);
    }

    public WesBaseException(String errorMessage, Throwable cause) {
        super(null, cause);
        this.errorMessage = errorMessage;
    }

    public WesBaseException(String errorMessage, String errorCode, Throwable cause) {
        super(errorMessage, cause);
        this.errorCode = errorCode;
    }

    public WesBaseException(String errorMessage, String errorCode) {
        super(errorMessage);
        this.errorCode = errorCode;
    }

    @Override
    public String getMessage() {
        return getErrorMessage();
    }
}
