package com.kakao.vectormap;

import android.util.Log;
import java.io.PrintWriter;
import java.io.StringWriter;

/* JADX INFO: loaded from: classes4.dex */
public class MapLogger {
    public static int CAMERA_LOG = 3;
    public static int COMMON_LOG = 3;
    public static boolean ENABLE = true;
    public static int LABEL_LOG = 3;
    public static int MAP_WIDGET_LOG = 3;
    public static int ROUTE_LOG = 3;
    public static int SHAPE_LOG = 3;

    public static native void setCameraLog(int i);

    public static native void setCameraLogEnable(boolean z);

    public static native void setCommonLog(int i);

    public static native void setCommonLogEnable(boolean z);

    public static native void setLabelLog(int i);

    public static native void setLabelLogEnable(boolean z);

    public static native void setLogEnable(boolean z);

    public static native void setMapWidgetLog(int i);

    public static native void setMapWidgetLogEnable(boolean z);

    public static native void setRouteLog(int i);

    public static native void setRouteLogEnable(boolean z);

    public static native void setShapeLog(int i);

    public static native void setShapeLogEnable(boolean z);

    public static String toString(Exception exc) {
        if (exc == null) {
            return "Unknown Exception";
        }
        try {
            StringWriter stringWriter = new StringWriter();
            exc.printStackTrace(new PrintWriter(stringWriter));
            return stringWriter.toString();
        } catch (Exception unused) {
            return exc.getLocalizedMessage();
        }
    }

    public static void e(Exception exc) {
        if (exc == null) {
            Log.e(Const.TAG, "Unknown Exception");
        }
        try {
            StringWriter stringWriter = new StringWriter();
            exc.printStackTrace(new PrintWriter(stringWriter));
            Log.e(Const.TAG, stringWriter.toString());
        } catch (Exception unused) {
            Log.e(Const.TAG, exc.getLocalizedMessage());
        }
    }

    public static void e(String str) {
        Log.e(Const.TAG, str);
    }

    public static void d(String str) {
        Log.d(Const.TAG, str);
    }

    public static void w(String str) {
        Log.w(Const.TAG, str);
    }
}
