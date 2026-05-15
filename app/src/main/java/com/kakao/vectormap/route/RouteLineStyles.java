package com.kakao.vectormap.route;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/* JADX INFO: loaded from: classes4.dex */
public class RouteLineStyles {
    private RouteLineStyle[] styles;
    private String styleId = "";
    private Set<RouteLinePattern> patterns = new LinkedHashSet();

    RouteLineStyles(RouteLineStyle... routeLineStyleArr) {
        this.styles = routeLineStyleArr;
        for (RouteLineStyle routeLineStyle : routeLineStyleArr) {
            if (routeLineStyle.getPattern() != null) {
                this.patterns.add(routeLineStyle.getPattern());
            }
        }
    }

    public static RouteLineStyles from(RouteLineStyle... routeLineStyleArr) {
        return new RouteLineStyles(routeLineStyleArr);
    }

    public static RouteLineStyles from(List<RouteLineStyle> list) {
        return new RouteLineStyles((RouteLineStyle[]) list.toArray(new RouteLineStyle[list.size()]));
    }

    public RouteLineStyles setStyleId(String str) {
        if (str == null) {
            str = "";
        }
        this.styleId = str;
        return this;
    }

    public String getStyleId() {
        return this.styleId;
    }

    public RouteLineStyle[] getStyles() {
        return this.styles;
    }

    public int getStyleCount() {
        RouteLineStyle[] routeLineStyleArr = this.styles;
        if (routeLineStyleArr == null) {
            return 0;
        }
        return routeLineStyleArr.length;
    }

    public Set<RouteLinePattern> getPatterns() {
        return this.patterns;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof RouteLineStyles)) {
            return false;
        }
        RouteLineStyles routeLineStyles = (RouteLineStyles) obj;
        RouteLineStyle[] routeLineStyleArr = this.styles;
        if (routeLineStyleArr == null) {
            return routeLineStyles.styles == null;
        }
        return Arrays.deepEquals(routeLineStyleArr, routeLineStyles.styles);
    }

    public int hashCode() {
        RouteLineStyle[] routeLineStyleArr = this.styles;
        int iHashCode = 1;
        if (routeLineStyleArr != null && routeLineStyleArr.length > 0) {
            for (RouteLineStyle routeLineStyle : routeLineStyleArr) {
                iHashCode = (iHashCode * 31) + routeLineStyle.hashCode();
            }
        }
        return iHashCode;
    }
}
