package com.kakao.vectormap.route;

import com.kakao.vectormap.internal.IRouteLineDelegate;
import com.kakao.vectormap.internal.IRouteLineFactory;

/* JADX INFO: loaded from: classes4.dex */
class RouteLineFactory implements IRouteLineFactory {
    RouteLineFactory() {
    }

    @Override // com.kakao.vectormap.internal.IRouteLineFactory
    public RouteLineLayer newLayer(IRouteLineDelegate iRouteLineDelegate, String str, int i, IRouteLineFactory iRouteLineFactory) {
        return new RouteLineLayer(iRouteLineDelegate, str, i, iRouteLineFactory);
    }

    @Override // com.kakao.vectormap.internal.IRouteLineFactory
    public RouteLine newRouteLine(IRouteLineDelegate iRouteLineDelegate, String str, RouteLineOptions routeLineOptions) {
        return new RouteLine(iRouteLineDelegate, routeLineOptions.getLineId(), str, routeLineOptions.getZOrder(), routeLineOptions.getSegments(), routeLineOptions.isVisible(), routeLineOptions.getTag());
    }

    @Override // com.kakao.vectormap.internal.IRouteLineFactory
    public RouteLineAnimator newAnimator(IRouteLineDelegate iRouteLineDelegate, String str, boolean z, boolean z2, int i) {
        return new RouteLineAnimator(iRouteLineDelegate, str, z, z2, i);
    }
}
