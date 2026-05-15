package com.kakao.vectormap.shape;

import com.kakao.vectormap.MapLogger;
import com.kakao.vectormap.animation.Animation;
import com.kakao.vectormap.internal.IDimScreenDelegate;
import com.kakao.vectormap.shape.animation.CircleWaves;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: loaded from: classes4.dex */
public class DimScreenManager {
    private IDimScreenDelegate delegate;
    private DimScreenLayer dimScreenLayer;
    private ShapeFactory shapeFactory = new ShapeFactory();
    private Map<String, ShapeAnimator> animatorMap = new ConcurrentHashMap();

    public DimScreenManager(IDimScreenDelegate iDimScreenDelegate) {
        this.delegate = iDimScreenDelegate;
        this.dimScreenLayer = new DimScreenLayer(iDimScreenDelegate);
    }

    public DimScreenLayer getDimScreenLayer() {
        return this.dimScreenLayer;
    }

    public synchronized PolygonStylesSet addPolygonStyles(PolygonStylesSet polygonStylesSet) {
        try {
            if (polygonStylesSet == null) {
                throw new RuntimeException("addPolygonStyles failure. PolygonStylesSet is null.");
            }
            if (!this.delegate.hasPolygonStylesSet(polygonStylesSet.getStyleId())) {
                this.delegate.addPolygonStylesSet(polygonStylesSet, true);
            }
        } catch (Exception e) {
            MapLogger.e(e);
            return null;
        }
        return this.delegate.getPolygonStylesSet(polygonStylesSet.getStyleId());
    }

    public synchronized PolygonStylesSet getPolygonStyles(String str) {
        try {
        } catch (RuntimeException e) {
            MapLogger.e(e);
            return null;
        }
        return this.delegate.getPolygonStylesSet(str);
    }

    public synchronized ShapeAnimator addAnimator(Animation animation) {
        try {
        } catch (RuntimeException e) {
            MapLogger.e(e);
        }
        if (animation == null) {
            MapLogger.e("addAnimator failed. Animation is null.");
            return null;
        }
        ShapeAnimator shapeAnimator = this.animatorMap.get(animation.getId());
        if (shapeAnimator != null) {
            return shapeAnimator;
        }
        if (animation instanceof CircleWaves) {
            this.delegate.addCircleWaveAnimator(false, (CircleWaves) animation);
            ShapeAnimator shapeAnimatorNewAnimator = this.shapeFactory.newAnimator(this.delegate, animation.getId(), false, ((CircleWaves) animation).getRepeatCount(), ((CircleWaves) animation).getDuration(), ((CircleWaves) animation).isHideShapeAtStop(), ((CircleWaves) animation).getInterpolation());
            this.animatorMap.put(shapeAnimatorNewAnimator.getId(), shapeAnimatorNewAnimator);
            return shapeAnimatorNewAnimator;
        }
        return null;
    }

    public synchronized void removeAllAnimator() {
        try {
            this.delegate.clearAllAnimator(true);
        } catch (RuntimeException e) {
            MapLogger.e(e);
        }
    }

    public synchronized ShapeAnimator getAnimator(String str) {
        return this.animatorMap.get(str);
    }
}
