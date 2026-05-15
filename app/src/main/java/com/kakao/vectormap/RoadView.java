package com.kakao.vectormap;

import android.graphics.PointF;
import android.graphics.Rect;
import androidx.camera.video.AudioStats;
import com.kakao.vectormap.internal.IRoadViewDelegate;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public class RoadView {
    private IRoadViewDelegate delegate;

    public interface OnRoadViewClickListener {
        void onRoadViewClicked(RoadView roadView, PointF pointF);
    }

    public interface OnRoadViewRequestListener {
        void onRoadViewRequestFailed(String str);

        void onRoadViewResultReceived(String str, LatLng latLng, List<RoadViewByDate> list);
    }

    public interface OnRoadViewResizeListener {
        void onRoadViewportResized(RoadView roadView, Rect rect);
    }

    public interface OnRoadViewUpdateListener {
        void onRoadViewUpdateDone(RoadView roadView);
    }

    RoadView(IRoadViewDelegate iRoadViewDelegate) {
        this.delegate = iRoadViewDelegate;
    }

    public boolean isDev() {
        try {
            return this.delegate.isDev();
        } catch (RuntimeException e) {
            MapLogger.e(e);
            return false;
        }
    }

    public Object getTag() {
        try {
            return this.delegate.getTag();
        } catch (RuntimeException e) {
            MapLogger.e(e);
            return "";
        }
    }

    public void setTag(Object obj) {
        try {
            this.delegate.setTag(obj);
        } catch (RuntimeException e) {
            MapLogger.e(e);
        }
    }

    public String getViewName() {
        try {
            return this.delegate.getViewName();
        } catch (RuntimeException e) {
            MapLogger.e(e);
            return "";
        }
    }

    public void setVisible(boolean z) {
        try {
            this.delegate.setVisible(z);
        } catch (RuntimeException e) {
            MapLogger.e(e);
        }
    }

    public boolean isVisible() {
        try {
            return this.delegate.isVisible();
        } catch (RuntimeException e) {
            MapLogger.e(e);
            return false;
        }
    }

    public Rect getViewport() {
        try {
            return this.delegate.getViewport();
        } catch (RuntimeException e) {
            MapLogger.e(e);
            return new Rect();
        }
    }

    public void setViewport(int i, int i2) {
        try {
            this.delegate.setViewport(i, i2);
        } catch (RuntimeException e) {
            MapLogger.e(e);
        }
    }

    public void setViewport(int i, int i2, int i3, int i4) {
        try {
            this.delegate.setViewport(i, i2, i3, i4);
        } catch (RuntimeException e) {
            MapLogger.e(e);
        }
    }

    public void setViewport(Rect rect) {
        try {
            this.delegate.setViewport(rect);
        } catch (RuntimeException e) {
            MapLogger.e(e);
        }
    }

    public synchronized void setOnRoadViewRequestListener(OnRoadViewRequestListener onRoadViewRequestListener) {
        this.delegate.setOnRoadViewRequestListener(onRoadViewRequestListener);
    }

    public synchronized void setOnRoadViewUpdateListener(OnRoadViewUpdateListener onRoadViewUpdateListener) {
        this.delegate.setOnRoadViewUpdateListener(onRoadViewUpdateListener);
    }

    public synchronized void setSearchRange(int i, int i2) {
        try {
            this.delegate.setSearchRange(i, i2);
        } catch (RuntimeException e) {
            MapLogger.e(e);
        }
    }

    public synchronized void requestRoadView(RoadViewRequest roadViewRequest) {
        try {
            this.delegate.requestRoadView(roadViewRequest);
        } catch (RuntimeException e) {
            MapLogger.e(e);
        }
    }

    public synchronized void requestNextRoadView(RoadViewRequest roadViewRequest) {
        try {
            this.delegate.requestNextRoadView(roadViewRequest);
        } catch (RuntimeException e) {
            MapLogger.e(e);
        }
    }

    public synchronized void moveToRoadView(RoadViewByDate roadViewByDate) {
        try {
            moveToRoadView(roadViewByDate.getIndex());
        } catch (RuntimeException e) {
            MapLogger.e(e);
        }
    }

    public synchronized void moveToRoadView(int i) {
        try {
            this.delegate.moveToRoadView(i);
        } catch (RuntimeException e) {
            MapLogger.e(e);
        }
    }

    public synchronized void linkMap(KakaoMap kakaoMap) {
        try {
            this.delegate.linkMap(kakaoMap);
        } catch (RuntimeException e) {
            MapLogger.e(e);
        }
    }

    public synchronized void unlinkMap() {
        try {
            this.delegate.unlinkMap();
        } catch (RuntimeException e) {
            MapLogger.e(e);
        }
    }

    public synchronized void setOnRoadViewResizeListener(OnRoadViewResizeListener onRoadViewResizeListener) {
        try {
            this.delegate.setOnRoadViewResizeListener(onRoadViewResizeListener);
        } catch (RuntimeException e) {
            MapLogger.e(e);
        }
    }

    public synchronized void setOnRoadViewClickListener(OnRoadViewClickListener onRoadViewClickListener) {
        try {
            this.delegate.setOnRoadViewClickListener(onRoadViewClickListener);
        } catch (RuntimeException e) {
            MapLogger.e(e);
        }
    }

    public synchronized double getTiltAngle() {
        try {
        } catch (RuntimeException e) {
            MapLogger.e(e);
            return AudioStats.AUDIO_AMPLITUDE_NONE;
        }
        return this.delegate.getTiltAngle();
    }

    public synchronized double getPanAngle() {
        try {
        } catch (RuntimeException e) {
            MapLogger.e(e);
            return AudioStats.AUDIO_AMPLITUDE_NONE;
        }
        return this.delegate.getPanAngle();
    }

    public synchronized Logo getLogo() {
        try {
        } catch (RuntimeException e) {
            MapLogger.e(e);
            return null;
        }
        return this.delegate.getLogo();
    }
}
