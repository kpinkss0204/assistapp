package com.kakao.vectormap.label;

import com.kakao.vectormap.MapLogger;
import com.kakao.vectormap.internal.ILabelDelegate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public class LabelAnimator {
    private final ILabelDelegate delegate;
    private final boolean hideLabelAtStop;
    private final String id;
    private final List<Label> labels = new ArrayList();
    private final boolean removeLabelAtStop;
    private OnLabelAnimationStopListener stopListener;
    private Object tag;

    LabelAnimator(String str, ILabelDelegate iLabelDelegate, boolean z, boolean z2) {
        this.id = str;
        this.delegate = iLabelDelegate;
        this.hideLabelAtStop = z;
        this.removeLabelAtStop = z2;
    }

    public synchronized String getId() {
        return this.id;
    }

    public synchronized void addLabels(Label... labelArr) {
        if (labelArr != null) {
            try {
            } catch (RuntimeException e) {
                MapLogger.e(e);
            }
            if (labelArr.length != 0) {
                this.labels.addAll(Arrays.asList(labelArr));
                return;
            }
        }
        MapLogger.e("LabelAnimator addLabels failure. labels is invalid.");
    }

    public synchronized void start() {
        try {
            this.delegate.startAnimator(this.id, this.labels);
        } catch (RuntimeException e) {
            MapLogger.e(e);
        }
    }

    public synchronized void stop() {
        try {
            this.delegate.stopAnimator(this.id);
            this.labels.clear();
        } catch (RuntimeException e) {
            MapLogger.e(e);
        }
    }

    public synchronized void setOnAnimationStopListener(OnLabelAnimationStopListener onLabelAnimationStopListener) {
        this.stopListener = onLabelAnimationStopListener;
    }

    public synchronized OnLabelAnimationStopListener getStopListener() {
        return this.stopListener;
    }

    public boolean isHideLabelAtStop() {
        return this.hideLabelAtStop;
    }

    public boolean isRemoveLabelAtStop() {
        return this.removeLabelAtStop;
    }

    public void setTag(Object obj) {
        this.tag = obj;
    }

    public Object getTag() {
        return this.tag;
    }
}
