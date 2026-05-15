package com.kakao.vectormap.internal;

import com.kakao.vectormap.route.RouteLine;
import com.kakao.vectormap.route.RouteLineAnimator;
import com.kakao.vectormap.route.RouteLineLayer;
import com.kakao.vectormap.route.RouteLineOptions;

/* JADX INFO: loaded from: classes4.dex */
public interface IRouteLineFactory {
    RouteLineAnimator newAnimator(IRouteLineDelegate iRouteLineDelegate, String str, boolean z, boolean z2, int i);

    RouteLineLayer newLayer(IRouteLineDelegate iRouteLineDelegate, String str, int i, IRouteLineFactory iRouteLineFactory);

    RouteLine newRouteLine(IRouteLineDelegate iRouteLineDelegate, String str, RouteLineOptions routeLineOptions);
}
