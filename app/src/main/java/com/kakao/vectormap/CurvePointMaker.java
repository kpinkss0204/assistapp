package com.kakao.vectormap;

import com.kakao.vectormap.internal.RenderViewController;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public class CurvePointMaker {
    public static List<LatLng> make(LatLng latLng, LatLng latLng2, CurveType curveType) {
        if (latLng == null || latLng2 == null) {
            return new ArrayList();
        }
        try {
            LatLng[] latLngArrMakeCurvePoints = RenderViewController.makeCurvePoints(latLng.latitude, latLng.longitude, latLng2.latitude, latLng2.longitude, curveType.getValue());
            if (latLngArrMakeCurvePoints != null) {
                return Arrays.asList(latLngArrMakeCurvePoints);
            }
        } catch (Exception e) {
            MapLogger.e(e);
        }
        return new ArrayList();
    }
}
