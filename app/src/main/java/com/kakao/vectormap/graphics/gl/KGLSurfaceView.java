package com.kakao.vectormap.graphics.gl;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.ViewConfiguration;
import com.kakao.vectormap.MapLifeCycleCallback;
import com.kakao.vectormap.MapLogger;
import com.kakao.vectormap.MapReadyCallback;
import com.kakao.vectormap.MapView;
import com.kakao.vectormap.graphics.IMapSurfaceView;
import com.kakao.vectormap.graphics.MapRenderer;
import com.kakao.vectormap.internal.EngineHandler;
import com.kakao.vectormap.internal.GLMapRenderer;
import com.kakao.vectormap.internal.MapViewHolder;
import com.kakao.vectormap.internal.RenderCallback;
import com.kakao.vectormap.internal.TouchEventConverter;
import java.lang.Thread;

/* JADX INFO: loaded from: classes4.dex */
public class KGLSurfaceView extends GLSurfaceView implements IMapSurfaceView {
    private boolean finishManually;
    private MapViewHolder mapViewHolder;
    private MapRenderer renderer;
    private TouchEventConverter touchListener;

    @Override // com.kakao.vectormap.graphics.IMapSurfaceView
    public SurfaceView getView() {
        return this;
    }

    public KGLSurfaceView(Context context, MapLifeCycleCallback mapLifeCycleCallback, MapView mapView, MapReadyCallback... mapReadyCallbackArr) {
        super(context);
        this.finishManually = false;
        this.mapViewHolder = new MapViewHolder(mapLifeCycleCallback, mapView);
        for (MapReadyCallback mapReadyCallback : mapReadyCallbackArr) {
            this.mapViewHolder.addRenderView(mapReadyCallback);
        }
    }

    public KGLSurfaceView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override // com.kakao.vectormap.graphics.gl.GLSurfaceView
    public void initEngine(boolean z) {
        this.touchListener = new TouchEventConverter(ViewConfiguration.get(getContext()).getScaledTouchSlop());
        this.renderer = new GLMapRenderer();
        this.renderer.setEngineHandler(new EngineHandler(this.mapViewHolder, this.touchListener, new RenderCallback(this), false));
        if (z) {
            setEGLContextClientVersion(2);
            setPreserveEGLContextOnPause(true);
            setEGLWindowSurfaceFactory(new WindowSurfaceFactory((IEglLifeCycleCallback) this.renderer));
            setEGLContextFactory(new EglContextFactory(3, (IEglLifeCycleCallback) this.renderer));
            setEGLConfigChooser(new KConfigChooser(8, 8, 8, 0, 24, 8));
        }
        setMapRenderer(this.renderer);
    }

    @Override // com.kakao.vectormap.graphics.gl.GLSurfaceView, com.kakao.vectormap.graphics.IMapSurfaceView
    public boolean isFinishManually() {
        return this.finishManually;
    }

    @Override // com.kakao.vectormap.graphics.IMapSurfaceView
    public void resume() throws RuntimeException {
        queueEvent(new Runnable() { // from class: com.kakao.vectormap.graphics.gl.KGLSurfaceView.1
            @Override // java.lang.Runnable
            public void run() {
                try {
                    KGLSurfaceView.this.getMapRenderer().resume();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
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
        queueEvent(new Runnable() { // from class: com.kakao.vectormap.graphics.gl.KGLSurfaceView.2
            @Override // java.lang.Runnable
            public void run() {
                try {
                    KGLSurfaceView.this.getMapRenderer().pause();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    @Override // com.kakao.vectormap.graphics.IMapSurfaceView
    public boolean isPaused() throws RuntimeException {
        try {
            return this.renderer.isPaused();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override // com.kakao.vectormap.graphics.IMapSurfaceView
    public void setMapRenderer(MapRenderer mapRenderer) {
        if (mapRenderer instanceof GLRenderer) {
            this.renderer = mapRenderer;
            setRenderer((GLRenderer) mapRenderer, new Thread.UncaughtExceptionHandler() { // from class: com.kakao.vectormap.graphics.gl.KGLSurfaceView.3
                @Override // java.lang.Thread.UncaughtExceptionHandler
                public void uncaughtException(Thread thread, Throwable th) {
                    if (th != null) {
                        MapLogger.e(th.getLocalizedMessage());
                    }
                    if (KGLSurfaceView.this.mapViewHolder != null) {
                        KGLSurfaceView.this.mapViewHolder.callOnMapError(new RuntimeException(th));
                    }
                }
            });
            setRenderMode(0);
        }
    }

    @Override // com.kakao.vectormap.graphics.IMapSurfaceView
    public MapRenderer getMapRenderer() {
        return this.renderer;
    }

    @Override // com.kakao.vectormap.graphics.IMapSurfaceView
    public void setFinishManually(boolean z) {
        this.finishManually = z;
    }

    @Override // com.kakao.vectormap.graphics.IMapSurfaceView
    public void finish() throws RuntimeException {
        try {
            try {
                requestExit();
            } catch (Exception e) {
                throw new RuntimeException(e);
            } catch (Throwable th) {
                throw new RuntimeException(th);
            }
        } finally {
            this.touchListener = null;
        }
    }

    @Override // com.kakao.vectormap.graphics.IMapSurfaceView
    public String getEngineState() {
        MapRenderer mapRenderer = this.renderer;
        if (mapRenderer == null) {
            return "Renderer is null.";
        }
        return mapRenderer.getEngineState();
    }

    @Override // com.kakao.vectormap.graphics.IMapSurfaceView
    public void reverseMouseWheelScroll(boolean z) {
        TouchEventConverter.reverseMouseWheelScroll(z);
    }

    @Override // android.view.View
    public boolean onGenericMotionEvent(MotionEvent motionEvent) {
        TouchEventConverter touchEventConverter = this.touchListener;
        if (touchEventConverter == null) {
            return true;
        }
        touchEventConverter.onGenericMotion(motionEvent);
        return true;
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        TouchEventConverter touchEventConverter = this.touchListener;
        if (touchEventConverter == null) {
            return true;
        }
        touchEventConverter.onTouch(this, motionEvent);
        return true;
    }
}
