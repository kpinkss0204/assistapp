package com.kakao.vectormap;

/* JADX INFO: loaded from: classes4.dex */
public class MapAuthException extends Exception {
    public static final int APP_KEY_INVALID_ERROR = 1003;
    public static final int CONNECT_ERROR = 499;
    public static final int CONNECT_INITIATE_FAILURE = -2;
    public static final int CONNECT_TIMEOUT_EXCEPTION = -4;
    public static final int INITIALIZE_FAILURE = 1001;
    public static final int RENDER_VIEW_FAILURE = 1002;
    public static final int SOCKET_TIMEOUT_EXCEPTION = -3;
    public static final int UNKNOWN_ERROR = -1;
    public int errorCode;

    public MapAuthException(String str) {
        super(str);
        this.errorCode = -1;
    }

    public MapAuthException(int i, String str) {
        super(str);
        this.errorCode = i;
    }

    public MapAuthException(String str, Throwable th) {
        super(str, th);
        this.errorCode = -1;
    }

    public MapAuthException(Throwable th) {
        super(th);
        this.errorCode = -1;
    }

    public MapAuthException(int i, Throwable th) {
        super(th);
        this.errorCode = i;
    }

    public MapAuthException(String str, Throwable th, boolean z, boolean z2) {
        super(str, th, z, z2);
        this.errorCode = -1;
    }

    public int getErrorCode() {
        return this.errorCode;
    }

    @Override // java.lang.Throwable
    public String getMessage() {
        return "MapAuthException(" + this.errorCode + "): " + super.getMessage() + "";
    }
}
