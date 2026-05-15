package com.kakao.vectormap.internal;

import android.util.Pair;
import com.kakao.vectormap.LatLng;
import com.kakao.vectormap.route.OnRouteLineAnimatorStopCallback;
import com.kakao.vectormap.route.OnRouteLineCreateCallback;
import com.kakao.vectormap.route.OnRouteLineProgressEndCallback;
import com.kakao.vectormap.route.RouteLine;
import com.kakao.vectormap.route.RouteLineAnimator;
import com.kakao.vectormap.route.RouteLineLayer;
import com.kakao.vectormap.route.RouteLineOptions;
import com.kakao.vectormap.route.RouteLineSegment;
import com.kakao.vectormap.route.RouteLineStylesSet;
import com.kakao.vectormap.route.RoutePoint;
import com.kakao.vectormap.route.animation.ProgressAnimation;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public interface IRouteLineDelegate {
    RouteLineLayer addLayer(String str, int i) throws RuntimeException;

    RouteLineAnimator addProgressAnimator(String str, ProgressAnimation progressAnimation) throws RuntimeException;

    void addRouteLine(RouteLineLayer routeLineLayer, RouteLineOptions routeLineOptions, OnRouteLineCreateCallback onRouteLineCreateCallback);

    String addRouteLineStyles(RouteLineStylesSet routeLineStylesSet) throws RuntimeException;

    void changeSegments(String str, String str2, RouteLineStylesSet routeLineStylesSet, RouteLineSegment... routeLineSegmentArr) throws RuntimeException;

    void changeStyles(String str, String str2, RouteLineStylesSet routeLineStylesSet, RouteLineSegment... routeLineSegmentArr) throws RuntimeException;

    RouteLineAnimator getAnimator(String str) throws RuntimeException;

    Pair<OnRouteLineAnimatorStopCallback, RouteLineAnimator> getAnimatorCallback(String str);

    RouteLineLayer getLayer(String str) throws RuntimeException;

    RoutePoint getPointFromProgress(String str, String str2, float f) throws RuntimeException;

    float getProgress(String str, String str2) throws RuntimeException;

    float getProgressFromPoint(String str, String str2, LatLng latLng) throws RuntimeException;

    RouteLineStylesSet getStylesSet(String str) throws RuntimeException;

    boolean hasLayer(String str) throws RuntimeException;

    boolean hasStylesSet(String str) throws RuntimeException;

    void progressTo(String str, String str2, float f, int i, OnRouteLineProgressEndCallback onRouteLineProgressEndCallback) throws RuntimeException;

    void removeAllRouteLine() throws RuntimeException;

    void removeAnimator(String str) throws RuntimeException;

    void removeLayer(String str) throws RuntimeException;

    void removeLayerRouteLine(String str) throws RuntimeException;

    void removeRouteLine(String str, String str2) throws RuntimeException;

    void setFactory(IRouteLineFactory iRouteLineFactory);

    void setLayerVisible(String str, boolean z) throws RuntimeException;

    void setProgress(String str, String str2, float f) throws RuntimeException;

    void setVisible(String str, String str2, boolean z) throws RuntimeException;

    void setZOrder(String str, String str2, int i) throws RuntimeException;

    void startAnimator(String str, List<RouteLine> list, OnRouteLineAnimatorStopCallback onRouteLineAnimatorStopCallback) throws RuntimeException;

    void stopAnimator(String str) throws RuntimeException;
}
