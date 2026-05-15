package com.kakao.vectormap.internal;

import android.util.ArrayMap;
import com.kakao.vectormap.label.LabelStyles;
import com.kakao.vectormap.label.PolylineLabelStyles;
import java.util.Map;

/* JADX INFO: loaded from: classes4.dex */
class LabelStyler {
    private Map<String, LabelStyles> lightModeLabelStyles = new ArrayMap();
    private Map<String, LabelStyles> darkModeLabelStyles = new ArrayMap();
    private Map<String, PolylineLabelStyles> lightModePolylineLabelStyles = new ArrayMap();
    private Map<String, PolylineLabelStyles> darkModePolylineLabelStyles = new ArrayMap();

    synchronized boolean hasLightModeLabelStyles(String str) {
        return this.lightModeLabelStyles.containsKey(str);
    }

    synchronized LabelStyles getLightModeLabelStyles(String str) {
        return this.lightModeLabelStyles.get(str);
    }

    synchronized LabelStyles addLightModeLabelStyles(LabelStyles labelStyles) {
        this.lightModeLabelStyles.put(labelStyles.getStyleId(), labelStyles);
        return this.lightModeLabelStyles.get(labelStyles.getStyleId());
    }

    synchronized boolean hasDarkModeLabelStyles(String str) {
        return this.darkModeLabelStyles.containsKey(str);
    }

    synchronized LabelStyles getDarkModeLabelStyles(String str) {
        return this.darkModeLabelStyles.get(str);
    }

    synchronized LabelStyles addDarkModeLabelStyles(LabelStyles labelStyles) {
        this.darkModeLabelStyles.put(labelStyles.getStyleId(), labelStyles);
        return this.darkModeLabelStyles.get(labelStyles.getStyleId());
    }

    synchronized boolean hasLightModePolylineLabelStyles(String str) {
        return this.lightModePolylineLabelStyles.containsKey(str);
    }

    synchronized PolylineLabelStyles getLightPolylineLabelStyles(String str) {
        return this.lightModePolylineLabelStyles.get(str);
    }

    synchronized PolylineLabelStyles addLightPolylineLabelStyles(PolylineLabelStyles polylineLabelStyles) {
        return this.lightModePolylineLabelStyles.put(polylineLabelStyles.getStyleId(), polylineLabelStyles);
    }

    synchronized boolean hasDarkModePolylineLabelStyles(String str) {
        return this.darkModePolylineLabelStyles.containsKey(str);
    }

    synchronized PolylineLabelStyles getDarkPolylineLabelStyles(String str) {
        return this.darkModePolylineLabelStyles.get(str);
    }

    synchronized PolylineLabelStyles addDarkPolylineLabelStyles(PolylineLabelStyles polylineLabelStyles) {
        return this.darkModePolylineLabelStyles.put(polylineLabelStyles.getStyleId(), polylineLabelStyles);
    }
}
