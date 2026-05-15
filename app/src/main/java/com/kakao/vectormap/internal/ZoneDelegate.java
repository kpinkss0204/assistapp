package com.kakao.vectormap.internal;

import com.kakao.vectormap.Const;

/* JADX INFO: loaded from: classes4.dex */
public class ZoneDelegate extends Destroyable implements IZoneDelegate {
    private long appEngineHandle;
    private String viewName;

    static native void hideDetailZone(long j, String str, String str2);

    static native void setZoneBoundScale(long j, String str, String str2, int i, float f, float f2);

    static native void showDetailZone(long j, String str, String str2, String str3, String str4);

    @Override // com.kakao.vectormap.internal.Destroyable
    public /* bridge */ /* synthetic */ boolean isRunning() {
        return super.isRunning();
    }

    @Override // com.kakao.vectormap.internal.Destroyable
    public /* bridge */ /* synthetic */ void setRunning(boolean z) {
        super.setRunning(z);
    }

    public ZoneDelegate(String str, long j) {
        this.viewName = str;
        this.appEngineHandle = j;
    }

    @Override // com.kakao.vectormap.internal.IZoneDelegate
    public void showDetailZone(String str, String str2, String str3) throws RuntimeException {
        if (!isRunning()) {
            throw new RuntimeException(Const.UnInitialized);
        }
        showDetailZone(this.appEngineHandle, this.viewName, str, str2, str3);
    }

    @Override // com.kakao.vectormap.internal.IZoneDelegate
    public void hideDetailZone(String str) throws RuntimeException {
        if (!isRunning()) {
            throw new RuntimeException(Const.UnInitialized);
        }
        hideDetailZone(this.appEngineHandle, this.viewName, str);
    }

    @Override // com.kakao.vectormap.internal.IZoneDelegate
    public void setZoneBoundScale(String str, int i, float f, float f2) throws RuntimeException {
        if (!isRunning()) {
            throw new RuntimeException(Const.UnInitialized);
        }
        setZoneBoundScale(this.appEngineHandle, this.viewName, str, i, f, f2);
    }

    @Override // com.kakao.vectormap.internal.Destroyable
    void onDestroy() {
        this.appEngineHandle = 0L;
        this.viewName = "";
    }
}
