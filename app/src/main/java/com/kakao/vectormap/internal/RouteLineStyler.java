package com.kakao.vectormap.internal;

import com.kakao.vectormap.route.RouteLineStylesSet;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: loaded from: classes4.dex */
public class RouteLineStyler {
    private Map<String, RouteLineStylesSet> lightModeStyles = new ConcurrentHashMap();
    private Map<String, RouteLineStylesSet> darkModeStyles = new ConcurrentHashMap();

    RouteLineStyler() {
    }

    boolean hasDarkStyles(String str) {
        return this.darkModeStyles.containsKey(str);
    }

    RouteLineStylesSet getDarkStyles(String str) {
        return this.darkModeStyles.get(str);
    }

    RouteLineStylesSet addDarkStyles(RouteLineStylesSet routeLineStylesSet) {
        this.darkModeStyles.put(routeLineStylesSet.getStyleId(), routeLineStylesSet);
        return this.darkModeStyles.get(routeLineStylesSet.getStyleId());
    }

    boolean hasLightStyles(String str) {
        return this.lightModeStyles.containsKey(str);
    }

    RouteLineStylesSet getLightStyles(String str) {
        return this.lightModeStyles.get(str);
    }

    RouteLineStylesSet addLightStyles(RouteLineStylesSet routeLineStylesSet) {
        this.lightModeStyles.put(routeLineStylesSet.getStyleId(), routeLineStylesSet);
        return this.lightModeStyles.get(routeLineStylesSet.getStyleId());
    }
}
