package com.kakao.vectormap.route;

import com.kakao.vectormap.MapLogger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/* JADX INFO: loaded from: classes4.dex */
public class RouteLineStylesSet {
    private List<RouteLinePattern> patterns;
    private String styleId;
    private List<RouteLineStyles> styles;

    RouteLineStylesSet(String str, List<RouteLineStyles> list, List<RouteLinePattern> list2) {
        this.styleId = "";
        this.styles = new ArrayList();
        this.patterns = new ArrayList();
        this.styleId = str;
        if (list != null && !list.isEmpty()) {
            this.styles = new ArrayList(list);
        }
        if (list2 == null || list2.isEmpty()) {
            return;
        }
        this.patterns = new ArrayList(list2);
    }

    RouteLineStylesSet(String str, Set<RouteLineStyles> set) {
        this.styleId = "";
        this.styles = new ArrayList();
        this.patterns = new ArrayList();
        this.styleId = str;
        if (set != null && !set.isEmpty()) {
            this.styles = new ArrayList(set);
        }
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        for (RouteLineStyles routeLineStyles : set) {
            if (routeLineStyles.getPatterns() != null) {
                linkedHashSet.addAll(routeLineStyles.getPatterns());
            }
        }
        this.patterns = new ArrayList(linkedHashSet);
    }

    public static RouteLineStylesSet from(RouteLineStyle... routeLineStyleArr) {
        return new RouteLineStylesSet("", new LinkedHashSet(Arrays.asList(RouteLineStyles.from(routeLineStyleArr))));
    }

    public static RouteLineStylesSet from(String str, RouteLineStyle... routeLineStyleArr) {
        return new RouteLineStylesSet(str, new LinkedHashSet(Arrays.asList(RouteLineStyles.from(routeLineStyleArr))));
    }

    public static RouteLineStylesSet from(RouteLineStyles... routeLineStylesArr) {
        return new RouteLineStylesSet("", new LinkedHashSet(Arrays.asList(routeLineStylesArr)));
    }

    public static RouteLineStylesSet from(String str, RouteLineStyles... routeLineStylesArr) {
        return new RouteLineStylesSet(str, new LinkedHashSet(Arrays.asList(routeLineStylesArr)));
    }

    public static RouteLineStylesSet from(List<RouteLineStyles> list) {
        return new RouteLineStylesSet("", new LinkedHashSet(list));
    }

    public static RouteLineStylesSet from(String str, List<RouteLineStyles> list) {
        return new RouteLineStylesSet(str, new LinkedHashSet(list));
    }

    public static RouteLineStylesSet from(List<RouteLineStyles> list, List<RouteLinePattern> list2) {
        return new RouteLineStylesSet("", list, list2);
    }

    public static RouteLineStylesSet from(String str, List<RouteLineStyles> list, List<RouteLinePattern> list2) {
        return new RouteLineStylesSet(str, list, list2);
    }

    public void checkStyleId() {
        String str = this.styleId;
        if (str == null || str.isEmpty()) {
            this.styleId = String.valueOf(hashCode());
        }
    }

    public String getStyleId() {
        return this.styleId;
    }

    public List<RouteLinePattern> getPatterns() {
        return this.patterns;
    }

    public List<RouteLineStyles> getStyles() {
        return this.styles;
    }

    public RouteLineStyles getStyles(int i) {
        try {
            return this.styles.get(i);
        } catch (Exception e) {
            MapLogger.e(e);
            return null;
        }
    }

    public RouteLineStyles getStyles(String str) {
        try {
            for (RouteLineStyles routeLineStyles : this.styles) {
                if (Objects.equals(routeLineStyles.getStyleId(), str)) {
                    return routeLineStyles;
                }
            }
            return null;
        } catch (Exception e) {
            MapLogger.e(e);
            return null;
        }
    }

    public boolean isEmpty() {
        List<RouteLineStyles> list = this.styles;
        return list == null || list.isEmpty();
    }
}
