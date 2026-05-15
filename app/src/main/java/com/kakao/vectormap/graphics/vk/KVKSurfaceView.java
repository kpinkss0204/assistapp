package com.kakao.vectormap.graphics.vk;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewConfiguration;
import com.kakao.vectormap.MapLifeCycleCallback;
import com.kakao.vectormap.MapReadyCallback;
import com.kakao.vectormap.MapView;
import com.kakao.vectormap.graphics.IMapSurfaceView;
import com.kakao.vectormap.graphics.MapRenderer;
import com.kakao.vectormap.graphics.vk.VKSurfaceView;
import com.kakao.vectormap.internal.EngineCreateCallback;
import com.kakao.vectormap.internal.EngineHandler;
import com.kakao.vectormap.internal.MapViewHolder;
import com.kakao.vectormap.internal.RenderCallback;
import com.kakao.vectormap.internal.TouchEventConverter;
import com.kakao.vectormap.internal.VKMapRenderer;

/* JADX INFO: loaded from: classes4.dex */
public class KVKSurfaceView extends VKSurfaceView implements IMapSurfaceView {
    private boolean finishManually;
    private MapView mapView;
    private MapViewHolder mapViewHolder;
    private MapRenderer renderer;
    private View.OnTouchListener touchListener;

    @Override // com.kakao.vectormap.graphics.IMapSurfaceView
    public String getEngineState() {
        return null;
    }

    @Override // com.kakao.vectormap.graphics.IMapSurfaceView
    public SurfaceView getView() {
        return this;
    }

    public KVKSurfaceView(Context context, MapLifeCycleCallback mapLifeCycleCallback, MapView mapView, MapReadyCallback... mapReadyCallbackArr) {
        super(context);
        this.mapView = mapView;
        this.finishManually = false;
        this.mapViewHolder = new MapViewHolder(mapLifeCycleCallback, mapView);
        for (MapReadyCallback mapReadyCallback : mapReadyCallbackArr) {
            this.mapViewHolder.addRenderView(mapReadyCallback);
        }
    }

    public KVKSurfaceView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.kakao.vectormap.graphics.IMapSurfaceView
    public void setMapRenderer(MapRenderer mapRenderer) {
        if (mapRenderer instanceof VKRenderer) {
            this.renderer = mapRenderer;
            setRenderer((VKSurfaceView.Renderer) mapRenderer);
            setRenderMode(0);
        }
    }

    @Override // com.kakao.vectormap.graphics.IMapSurfaceView
    public MapRenderer getMapRenderer() {
        return this.renderer;
    }

    @Override // com.kakao.vectormap.graphics.vk.VKSurfaceView, com.kakao.vectormap.graphics.IMapSurfaceView
    public void requestRender() {
        super.requestRender();
    }

    @Override // com.kakao.vectormap.graphics.IMapSurfaceView
    public void setFinishManually(boolean z) {
        this.finishManually = z;
    }

    @Override // com.kakao.vectormap.graphics.IMapSurfaceView
    public boolean isFinishManually() {
        return this.finishManually;
    }

    @Override // com.kakao.vectormap.graphics.IMapSurfaceView
    public void resume() throws RuntimeException {
        try {
            onResume();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override // com.kakao.vectormap.graphics.IMapSurfaceView
    public boolean isResumed() throws RuntimeException {
        try {
            return this.renderer.isResumed();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override // com.kakao.vectormap.graphics.IMapSurfaceView
    public void pause() throws RuntimeException {
        try {
            onPause();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override // com.kakao.vectormap.graphics.IMapSurfaceView
    public boolean isPaused() throws RuntimeException {
        try {
            return this.renderer.isPaused();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override // com.kakao.vectormap.graphics.vk.VKSurfaceView
    public void initEngine(boolean z) {
        this.touchListener = new TouchEventConverter(ViewConfiguration.get(getContext()).getScaledTouchSlop());
        this.renderer = new VKMapRenderer();
        this.renderer.setEngineHandler(new EngineHandler(this.mapViewHolder, (EngineCreateCallback) this.touchListener, new RenderCallback(this), true));
        setMapRenderer(this.renderer);
    }

    @Override // com.kakao.vectormap.graphics.IMapSurfaceView
    public void finish() {
        try {
            finalize();
        } catch (Throwable th) {
            try {
                th.printStackTrace();
            } finally {
                this.touchListener = null;
            }
        }
    }

    @Override // com.kakao.vectormap.graphics.IMapSurfaceView
    public void reverseMouseWheelScroll(boolean z) {
        TouchEventConverter.reverseMouseWheelScroll(z);
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        View.OnTouchListener onTouchListener = this.touchListener;
        if (onTouchListener == null) {
            return true;
        }
        onTouchListener.onTouch(this, motionEvent);
        return true;
    }
}
