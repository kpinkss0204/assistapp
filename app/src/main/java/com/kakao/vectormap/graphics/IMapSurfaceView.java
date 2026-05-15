package com.kakao.vectormap.graphics;

import android.view.SurfaceView;

/* JADX INFO: loaded from: classes4.dex */
public interface IMapSurfaceView {
    void finish() throws RuntimeException;

    String getEngineState();

    MapRenderer getMapRenderer();

    SurfaceView getView();

    boolean isFinishManually();

    boolean isPaused() throws RuntimeException;

    boolean isResumed() throws RuntimeException;

    void pause() throws RuntimeException;

    void requestRender();

    void resume() throws RuntimeException;

    void reverseMouseWheelScroll(boolean z);

    void setFinishManually(boolean z);

    void setMapRenderer(MapRenderer mapRenderer);
}
