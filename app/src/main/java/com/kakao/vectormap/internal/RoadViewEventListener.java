package com.kakao.vectormap.internal;

import android.graphics.PointF;
import android.graphics.Rect;
import android.os.Handler;
import android.os.Looper;
import com.kakao.vectormap.LatLng;
import com.kakao.vectormap.MapLogger;
import com.kakao.vectormap.RoadView;
import com.kakao.vectormap.RoadViewByDate;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes4.dex */
class RoadViewEventListener extends Destroyable {
    private RoadView.OnRoadViewClickListener clickListener;
    private Handler mainHandler = new Handler(Looper.getMainLooper());
    private RoadView.OnRoadViewRequestListener requestListener;
    private RoadView.OnRoadViewResizeListener resizeListener;
    private RoadView roadView;
    private RoadViewDelegate roadViewDelegate;
    private RoadView.OnRoadViewUpdateListener updateListener;

    RoadViewEventListener(RoadView roadView, RoadViewDelegate roadViewDelegate) {
        this.roadView = roadView;
        this.roadViewDelegate = roadViewDelegate;
    }

    @Override // com.kakao.vectormap.internal.Destroyable
    void onDestroy() {
        this.roadView = null;
        this.mainHandler = null;
        this.roadViewDelegate = null;
    }

    public void setOnRoadViewEventListener(RoadView.OnRoadViewRequestListener onRoadViewRequestListener) {
        this.requestListener = onRoadViewRequestListener;
    }

    public void setOnRoadViewResizeListener(RoadView.OnRoadViewResizeListener onRoadViewResizeListener) {
        this.resizeListener = onRoadViewResizeListener;
    }

    public void setOnRoadViewClickListener(RoadView.OnRoadViewClickListener onRoadViewClickListener) {
        this.clickListener = onRoadViewClickListener;
    }

    public void setOnRoadViewUpdateListener(RoadView.OnRoadViewUpdateListener onRoadViewUpdateListener) {
        this.updateListener = onRoadViewUpdateListener;
    }

    public void onRoadViewRequestSuccess(final String str, final double d, final double d2, final String[] strArr, final String[] strArr2) {
        if (!isRunning()) {
            MapLogger.e("onRoadViewRequestSuccess return. Initialization Failed, Reload Map Required.");
        }
        if (this.requestListener == null) {
            MapLogger.e("RoadViewRequestListener null return.");
        } else {
            this.mainHandler.post(new Runnable() { // from class: com.kakao.vectormap.internal.RoadViewEventListener.1
                @Override // java.lang.Runnable
                public void run() {
                    ArrayList arrayList = new ArrayList();
                    if (strArr != null) {
                        for (int i = 0; i < strArr.length; i++) {
                            arrayList.add(new RoadViewByDate(i, strArr[i], strArr2[i]));
                        }
                    }
                    RoadViewEventListener.this.requestListener.onRoadViewResultReceived(str, LatLng.from(d, d2), arrayList);
                }
            });
        }
    }

    public void onRoadViewRequestFailure(final int i, final String str) {
        if (!isRunning()) {
            MapLogger.e("onRoadViewRequestSuccess return. Initialization Failed, Reload Map Required.");
        }
        if (this.requestListener == null) {
            return;
        }
        this.mainHandler.post(new Runnable() { // from class: com.kakao.vectormap.internal.RoadViewEventListener.2
            @Override // java.lang.Runnable
            public void run() {
                RoadViewEventListener.this.requestListener.onRoadViewRequestFailed(str + "(" + i + ")");
            }
        });
    }

    public void onMapResized(int i, int i2, int i3, int i4) {
        if (!isRunning()) {
            MapLogger.e("onRoadviewResized return. Initialization Failed, Reload Map Required.");
        }
        final Rect viewportInternal = this.roadViewDelegate.setViewportInternal(i, i2, i3, i4);
        if (this.resizeListener == null) {
            return;
        }
        this.mainHandler.post(new Runnable() { // from class: com.kakao.vectormap.internal.RoadViewEventListener.3
            @Override // java.lang.Runnable
            public void run() {
                RoadViewEventListener.this.resizeListener.onRoadViewportResized(RoadViewEventListener.this.roadView, viewportInternal);
            }
        });
    }

    public void onRenderViewClicked(final float f, final float f2, double d, double d2) {
        if (!isRunning()) {
            MapLogger.e("onRenderViewClicked return. Initialization Failed, Reload Map Required.");
        } else {
            if (this.clickListener == null) {
                return;
            }
            this.mainHandler.post(new Runnable() { // from class: com.kakao.vectormap.internal.RoadViewEventListener.4
                @Override // java.lang.Runnable
                public void run() {
                    RoadViewEventListener.this.clickListener.onRoadViewClicked(RoadViewEventListener.this.roadView, new PointF(f, f2));
                }
            });
        }
    }

    public void onRoadViewUpdateDone() {
        if (!isRunning()) {
            MapLogger.e("onRoadViewUpdateDone return. Initialization Failed, Reload Map Required.");
        } else {
            if (this.updateListener == null) {
                return;
            }
            this.mainHandler.post(new Runnable() { // from class: com.kakao.vectormap.internal.RoadViewEventListener.5
                @Override // java.lang.Runnable
                public void run() {
                    RoadViewEventListener.this.updateListener.onRoadViewUpdateDone(RoadViewEventListener.this.roadView);
                }
            });
        }
    }
}
