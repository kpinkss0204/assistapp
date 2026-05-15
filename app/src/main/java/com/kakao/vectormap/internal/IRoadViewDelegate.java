package com.kakao.vectormap.internal;

import android.graphics.Rect;
import com.kakao.vectormap.KakaoMap;
import com.kakao.vectormap.RoadView;
import com.kakao.vectormap.RoadViewRequest;

/* JADX INFO: loaded from: classes4.dex */
public interface IRoadViewDelegate extends IRenderViewDelegate {
    double getPanAngle() throws RuntimeException;

    Object getTag();

    double getTiltAngle() throws RuntimeException;

    String getViewName();

    Rect getViewport() throws RuntimeException;

    boolean isDev() throws RuntimeException;

    boolean isVisible() throws RuntimeException;

    void linkMap(KakaoMap kakaoMap) throws RuntimeException;

    void moveToRoadView(int i) throws RuntimeException;

    void requestNextRoadView(RoadViewRequest roadViewRequest) throws RuntimeException;

    void requestRoadView(RoadViewRequest roadViewRequest) throws RuntimeException;

    void setOnRoadViewClickListener(RoadView.OnRoadViewClickListener onRoadViewClickListener);

    void setOnRoadViewRequestListener(RoadView.OnRoadViewRequestListener onRoadViewRequestListener);

    void setOnRoadViewResizeListener(RoadView.OnRoadViewResizeListener onRoadViewResizeListener);

    void setOnRoadViewUpdateListener(RoadView.OnRoadViewUpdateListener onRoadViewUpdateListener);

    void setSearchRange(int i, int i2);

    void setTag(Object obj);

    void setViewport(int i, int i2) throws RuntimeException;

    void setViewport(int i, int i2, int i3, int i4) throws RuntimeException;

    void setViewport(Rect rect) throws RuntimeException;

    void setVisible(boolean z) throws RuntimeException;

    void unlinkMap() throws RuntimeException;
}
