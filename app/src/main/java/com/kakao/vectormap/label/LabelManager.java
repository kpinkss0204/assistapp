package com.kakao.vectormap.label;

import com.kakao.vectormap.MapLogger;
import com.kakao.vectormap.animation.Animation;
import com.kakao.vectormap.internal.ILabelDelegate;
import com.kakao.vectormap.label.animation.AlphaAnimations;
import com.kakao.vectormap.label.animation.DropAnimation;
import com.kakao.vectormap.label.animation.ScaleAlphaAnimations;
import com.kakao.vectormap.label.animation.ScaleAnimations;
import com.kakao.vectormap.label.animation.TransformAnimations;
import java.util.Collection;

/* JADX INFO: loaded from: classes4.dex */
public class LabelManager {
    public static final String DEFAULT_LAYER_ID = "label_default_layer";
    public static final String DEFAULT_LOD_LAYER_ID = "lodLabel_default_layer";
    public static final int DEFAULT_Z_ORDER = 10001;
    private final ILabelDelegate delegate;

    public LabelManager(ILabelDelegate iLabelDelegate) {
        this.delegate = iLabelDelegate;
        iLabelDelegate.setLabelFactory(new LabelFactory());
        addLayer(LabelLayerOptions.from(DEFAULT_LAYER_ID));
        addLodLayer(LabelLayerOptions.from(DEFAULT_LOD_LAYER_ID));
    }

    public synchronized LabelLayer addLayer(LabelLayerOptions labelLayerOptions) {
        try {
            try {
                if (labelLayerOptions == null) {
                    throw new RuntimeException("addLayer failed. LabelLayerOptions is null.");
                }
                if (this.delegate.hasLayer(labelLayerOptions.getLayerId())) {
                    return this.delegate.getLabelLayer(labelLayerOptions.getLayerId());
                }
                if (this.delegate.hasLodLayer(labelLayerOptions.getLayerId())) {
                    throw new RuntimeException("addLayer(id=" + labelLayerOptions.getLayerId() + ") is already the id of LodLabelLayer");
                }
                return this.delegate.addLayer(labelLayerOptions);
            } catch (RuntimeException e) {
                MapLogger.e(e);
                return null;
            }
        } catch (Throwable th) {
            throw th;
        }
    }

    public synchronized LodLabelLayer addLodLayer(LabelLayerOptions labelLayerOptions) {
        try {
            try {
                if (labelLayerOptions == null) {
                    throw new RuntimeException("addLodLayer failed. LabelLayerOptions is null.");
                }
                if (this.delegate.hasLodLayer(labelLayerOptions.getLayerId())) {
                    return this.delegate.getLodLabelLayer(labelLayerOptions.getLayerId());
                }
                if (this.delegate.hasLayer(labelLayerOptions.getLayerId())) {
                    throw new RuntimeException("addLodLayer(id=" + labelLayerOptions.getLayerId() + ") is already the id of LodLabelLayer");
                }
                return this.delegate.addLodLayer(labelLayerOptions);
            } catch (RuntimeException e) {
                MapLogger.e(e);
                return null;
            }
        } catch (Throwable th) {
            throw th;
        }
    }

    public synchronized LabelLayer getLayer() {
        LabelLayer labelLayer;
        try {
            labelLayer = this.delegate.getLabelLayer(DEFAULT_LAYER_ID);
            if (labelLayer == null) {
                labelLayer = addLayer(LabelLayerOptions.from(DEFAULT_LAYER_ID));
            }
        } catch (RuntimeException e) {
            MapLogger.e(e);
            return null;
        }
        return labelLayer;
    }

    public synchronized LodLabelLayer getLodLayer() {
        LodLabelLayer lodLabelLayer;
        try {
            lodLabelLayer = this.delegate.getLodLabelLayer(DEFAULT_LOD_LAYER_ID);
            if (lodLabelLayer == null) {
                lodLabelLayer = addLodLayer(LabelLayerOptions.from(DEFAULT_LOD_LAYER_ID));
            }
        } catch (RuntimeException e) {
            MapLogger.e(e);
            return null;
        }
        return lodLabelLayer;
    }

    public synchronized LabelStyles addLabelStyles(LabelStyles labelStyles) {
        try {
            if (labelStyles == null) {
                throw new RuntimeException("addStyles failed. LabelStyles is null.");
            }
            this.delegate.addLabelStyles(labelStyles);
        } catch (RuntimeException e) {
            MapLogger.e(e);
            return null;
        }
        return labelStyles;
    }

    public synchronized LabelStyles[] addLabelStyles(LabelStyles... labelStylesArr) {
        if (labelStylesArr != null) {
            try {
                if (labelStylesArr.length > 0) {
                    this.delegate.addLabelStyles(labelStylesArr);
                }
            } catch (RuntimeException e) {
                MapLogger.e(e);
                return null;
            }
        }
        throw new RuntimeException("addStyles failure. LabelStyles is invalid.");
        return labelStylesArr;
    }

    public synchronized LabelStyles getLabelStyles(String str) {
        try {
            if (str == null) {
                throw new RuntimeException("getLabelStyles failure. stylesId is null.");
            }
        } catch (RuntimeException e) {
            MapLogger.e(e);
            return null;
        }
        return this.delegate.getLabelStyles(str);
    }

    public synchronized LabelAnimator addAnimator(Animation animation) {
        try {
        } catch (Exception e) {
            MapLogger.e(e);
        }
        if (animation == null) {
            MapLogger.e("addAnimator failed. Animation is null.");
            return null;
        }
        LabelAnimator animator = this.delegate.getAnimator(animation.getId());
        if (animator != null) {
            return animator;
        }
        if (animation instanceof DropAnimation) {
            return this.delegate.addDropAnimator((DropAnimation) animation);
        }
        if (animation instanceof ScaleAnimations) {
            return this.delegate.addScaleAnimator((ScaleAnimations) animation);
        }
        if (animation instanceof ScaleAlphaAnimations) {
            return this.delegate.addScaleAlphaAnimator((ScaleAlphaAnimations) animation);
        }
        if (animation instanceof AlphaAnimations) {
            return this.delegate.addAlphaAnimator((AlphaAnimations) animation);
        }
        if (animation instanceof TransformAnimations) {
            return this.delegate.addTransformAnimator((TransformAnimations) animation);
        }
        return null;
    }

    public synchronized LabelAnimator getAnimator(String str) {
        try {
        } catch (Exception e) {
            MapLogger.e(e);
            return null;
        }
        return this.delegate.getAnimator(str);
    }

    public synchronized void removeAnimator(String str) {
        try {
            this.delegate.removeAnimator(str);
        } catch (RuntimeException e) {
            MapLogger.e(e);
        }
    }

    public synchronized void removeAllAnimator() {
        try {
            this.delegate.removeAllAnimator();
        } catch (RuntimeException e) {
            MapLogger.e(e);
        }
    }

    public synchronized void update(Label[] labelArr) {
        try {
            try {
            } catch (RuntimeException e) {
                MapLogger.e(e);
            }
            if (labelArr == null) {
                throw new RuntimeException("update failure. Param is null.");
            }
            this.delegate.updateLabels(labelArr);
        } catch (Throwable th) {
            throw th;
        }
    }

    public synchronized void update(Collection<Label> collection) {
        try {
            try {
            } catch (RuntimeException e) {
                MapLogger.e(e);
            }
            if (collection == null) {
                throw new RuntimeException("update failure. Param is null.");
            }
            this.delegate.updateLabels((Label[]) collection.toArray(new Label[collection.size()]));
        } catch (Throwable th) {
            throw th;
        }
    }

    public synchronized LodLabelLayer getLodLayer(String str) {
        try {
        } catch (RuntimeException e) {
            MapLogger.e(e);
            return null;
        }
        return this.delegate.getLodLabelLayer(str);
    }

    public synchronized LabelLayer getLayer(String str) {
        try {
        } catch (RuntimeException e) {
            MapLogger.e(e);
            return null;
        }
        return this.delegate.getLabelLayer(str);
    }

    public synchronized void remove(LabelLayer labelLayer) {
        try {
            this.delegate.removeLayer(labelLayer.isLodLayer, labelLayer.getLayerId());
        } catch (RuntimeException e) {
            MapLogger.e(e);
        }
    }

    public synchronized void remove(LodLabelLayer lodLabelLayer) {
        try {
            this.delegate.removeLayer(true, lodLabelLayer.getLayerId());
        } catch (RuntimeException e) {
            MapLogger.e(e);
        }
    }

    public synchronized void removeAllLabelLayer() {
        try {
            this.delegate.removeAllLayer();
        } catch (RuntimeException e) {
            MapLogger.e(e);
        }
    }

    public synchronized void removeAllLodLabelLayer() {
        try {
            this.delegate.removeAllLodLayer();
        } catch (RuntimeException e) {
            MapLogger.e(e);
        }
    }

    public synchronized void clearAll() {
        try {
            this.delegate.clearAll();
        } catch (RuntimeException e) {
            MapLogger.e(e);
        }
    }
}
