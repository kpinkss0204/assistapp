package com.kakao.vectormap.internal;

import android.util.Log;
import com.kakao.vectormap.Const;
import com.kakao.vectormap.MapLogger;

/* JADX INFO: loaded from: classes4.dex */
class APILogger {
    APILogger() {
    }

    public static void callStart(String str) {
        String methodName;
        try {
            methodName = Thread.currentThread().getStackTrace()[3].getMethodName();
        } catch (Exception e) {
            MapLogger.e(e);
            methodName = "unknownMethod";
        }
        Log.i(Const.TAG, "-> " + methodName + "(" + str + ")");
    }
}
