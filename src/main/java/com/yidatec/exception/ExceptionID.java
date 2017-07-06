package com.yidatec.exception;

/**
 * 异常消息代码定义
 *
 * @author QuShengWen
 */
public interface ExceptionID {
    public static final int SYSTEM_EXCEPTION = 1000000;
    public static final int INVALID_ACCOUNT = 1000001;
    public static final int INVALID_PASSWORD = 1000002;
    public static final int INVALID_VALIDATE_CODE = 1000003;
    public static final int REACH_MAX_TRY = 1000004;
    public static final int LOCKED_ACCOUNT = 1000005;
    public static final int DISABLED_ACCOUNT = 1000006;
    public static final int ACCESS_DENIED = 1000007;
    public static final int SESSION_EXPIRED = 1000008;
    public static final int RETRIEVE_PASSWORD_ERROR = 1000010;
    public static final int SESSION_ERROR = 1000011;




    /**************qu start******************************/
    public static final int ALWARYS_BE_ORDER = 5000001;
    public static final int NOT_ENOUGH_LESSIONS = 5000002;
    public static final int LESSION_NOT_EXISTS = 5000003;
    public static final int NOT_TRAINER = 5000004;
    public static final int LESSION_EXPIRED = 5000005;
    public static final int CHECKEDIN_FINISHED_CANNOT_CANCEL = 5000006;
    public static final int LESSION_CHANGED_RETRY = 5000007;
    public static final int LESSION_BE_REDUCTED = 5000008;
    public static final int LESSION_HAS_BEEN_DELETE_OR_CHECKINED = 5000009;
    public static final int GENERATE_FILE_ERROR = 5000022;
    public static final int DOWNLOAD_FILE_ERROR = 5000023;
}
