package com.kakao.vectormap.label;

import android.graphics.PointF;
import com.kakao.vectormap.MapLogger;
import com.kakao.vectormap.internal.ILabelDelegate;

/* JADX INFO: loaded from: classes4.dex */
public class Badge extends IBadge {
    Badge(ILabelDelegate iLabelDelegate, boolean z, String str, String str2, String str3, float f, float f2, int i, Object obj) {
        super(iLabelDelegate, str3, z, str, str2, i, obj, new PointF(f, f2));
    }

    public synchronized void setOffset(float f, float f2) {
        try {
            this.delegate.setBadgeOffset(this.isLod, this.groupId, this.labelId, this.id, f, f2);
            this.offset.set(f, f2);
        } catch (RuntimeException e) {
            MapLogger.e(e);
        }
    }
}
