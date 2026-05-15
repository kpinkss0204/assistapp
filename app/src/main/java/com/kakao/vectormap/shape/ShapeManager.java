package com.kakao.vectormap.shape;

import com.kakao.vectormap.MapLogger;
import com.kakao.vectormap.animation.Animation;
import com.kakao.vectormap.internal.IShapeDelegate;
import com.kakao.vectormap.shape.animation.CircleWaves;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: loaded from: classes4.dex */
public class ShapeManager {
    private static final String DEFAULT_LAYER_ID = "vector_layer_0";
    public static final int DEFAULT_Z_ORDER = 10000;
    private Map<String, ShapeAnimator> animatorMap;
    private IShapeDelegate delegate;
    private ShapeFactory shapeFactory;

    public ShapeManager(IShapeDelegate iShapeDelegate) {
        this.delegate = iShapeDelegate;
        ShapeFactory shapeFactory = new ShapeFactory();
        this.shapeFactory = shapeFactory;
        this.delegate.setShapeFactory(shapeFactory);
        this.animatorMap = new ConcurrentHashMap();
        addLayer(ShapeLayerOptions.from(DEFAULT_LAYER_ID, 10000));
    }

    public synchronized PolygonStylesSet addPolygonStyles(PolygonStylesSet polygonStylesSet) {
        try {
            if (polygonStylesSet == null) {
                throw new RuntimeException("addPolygonStyles failure. PolygonStylesSet is null.");
            }
            if (!this.delegate.hasPolygonStylesSet(polygonStylesSet.getStyleId())) {
                this.delegate.addPolygonStylesSet(polygonStylesSet, false);
            }
        } catch (Exception e) {
            MapLogger.e(e);
            return null;
        }
        return this.delegate.getPolygonStylesSet(polygonStylesSet.getStyleId());
    }

    public synchronized PolylineStylesSet addPolylineStyles(PolylineStylesSet polylineStylesSet) {
        try {
            if (polylineStylesSet == null) {
                throw new RuntimeException("addPolygonStyles failure. PolygonStylesSet is null.");
            }
            if (!this.delegate.hasPolylineStylesSet(polylineStylesSet.getStyleId())) {
                this.delegate.addPolylineStylesSet(polylineStylesSet);
            }
        } catch (Exception e) {
            MapLogger.e(e);
            return null;
        }
        return this.delegate.getPolylineStylesSet(polylineStylesSet.getStyleId());
    }

    public synchronized PolygonStylesSet getPolygonStyles(String str) {
        try {
        } catch (RuntimeException e) {
            MapLogger.e(e);
            return null;
        }
        return this.delegate.getPolygonStylesSet(str);
    }

    public synchronized PolylineStylesSet getPolylineStyles(String str) {
        try {
        } catch (RuntimeException e) {
            MapLogger.e(e);
            return null;
        }
        return this.delegate.getPolylineStylesSet(str);
    }

    public synchronized ShapeLayer addLayer(ShapeLayerOptions shapeLayerOptions) {
        try {
            if (shapeLayerOptions == null) {
                throw new RuntimeException("addLayer failure. ShapeLayerOptions is null.");
            }
            if (this.delegate.hasLayer(shapeLayerOptions.getLayerId())) {
                return this.delegate.getLayer(shapeLayerOptions.getLayerId());
            }
            return this.delegate.addLayer(shapeLayerOptions);
        } catch (Exception e) {
            MapLogger.e(e);
            return null;
        }
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
            this.delegate.clearAllAnimator(false);
        } catch (RuntimeException e) {
            MapLogger.e(e);
        }
    }

    public synchronized ShapeAnimator getAnimator(String str) {
        return this.animatorMap.get(str);
    }

    public synchronized ShapeLayer getLayer() {
        try {
        } catch (Exception e) {
            MapLogger.e(e);
            return null;
        }
        return this.delegate.getLayer(DEFAULT_LAYER_ID);
    }

    public synchronized ShapeLayer getLayer(String str) {
        try {
        } catch (Exception e) {
            MapLogger.e(e);
            return null;
        }
        return this.delegate.getLayer(str);
    }

    public synchronized void remove(Polygon polygon) {
        try {
            this.delegate.removePolygon(polygon.toDimScreen(), polygon.getLayerId(), polygon.getId());
        } catch (RuntimeException e) {
            MapLogger.e(e);
        }
    }

    public synchronized void remove(Polyline polyline) {
        try {
            this.delegate.removePolyline(polyline.toDimScreen(), polyline.getLayerId(), polyline.getId());
        } catch (RuntimeException e) {
            MapLogger.e(e);
        }
    }

    public synchronized void remove(ShapeLayer shapeLayer) {
        try {
            this.delegate.removeLayer(shapeLayer.getLayerId());
        } catch (RuntimeException e) {
            MapLogger.e(e);
        }
    }

    public synchronized void clearAll() {
        try {
            this.delegate.removeAllShape(false);
        } catch (RuntimeException e) {
            MapLogger.e(e);
        }
    }
}
