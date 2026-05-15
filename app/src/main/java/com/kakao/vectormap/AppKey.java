package com.kakao.vectormap;

/* JADX INFO: loaded from: classes4.dex */
public class AppKey {
    private String appKey;
    private MapAuthException exception;
    private boolean hasKey;
    private String phaseUrl;

    public AppKey(boolean z, MapAuthException mapAuthException) {
        this.hasKey = z;
        this.appKey = "";
        this.phaseUrl = "";
        this.exception = mapAuthException;
    }

    public AppKey(boolean z, String str, String str2) {
        this.hasKey = z;
        this.appKey = str;
        this.phaseUrl = str2;
        this.exception = new MapAuthException(-1, Const.UnKnownError);
    }

    public String getPhaseUrl() {
        return this.phaseUrl;
    }

    public String getAppKey() {
        return this.appKey;
    }

    public boolean hasKey() {
        return this.hasKey;
    }

    public void setException(MapAuthException mapAuthException) {
        this.exception = mapAuthException;
    }

    public MapAuthException getException() {
        return this.exception;
    }
}
