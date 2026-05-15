package com.kakao.vectormap;

import com.kakao.vectormap.internal.RenderViewController;

/* JADX INFO: loaded from: classes4.dex */
public class CoordConverter {
    public static LatLng toLatLngFromWCONG(double d, double d2) {
        try {
            return RenderViewController.toLatLngFromWCong(d, d2);
        } catch (Exception e) {
            MapLogger.e(e);
            return null;
        }
    }

    public static Coordinate toWTMFromWCONG(double d, double d2) {
        try {
            return RenderViewController.toWTMFromWCong(d, d2);
        } catch (Exception e) {
            MapLogger.e(e);
            return null;
        }
    }

    public static LatLng toLatLngFromWTM(double d, double d2) {
        try {
            return RenderViewController.toLatLngFromWTM(d, d2);
        } catch (Exception e) {
            MapLogger.e(e);
            return null;
        }
    }

    public static Coordinate toWCONGFromWTM(double d, double d2) {
        try {
            return RenderViewController.toWCongFromWTM(d, d2);
        } catch (Exception e) {
            MapLogger.e(e);
            return null;
        }
    }

    public static Coordinate toWCONGFromLatLng(double d, double d2) {
        try {
            return RenderViewController.toWCongFromLatLng(d2, d);
        } catch (Exception e) {
            MapLogger.e(e);
            return null;
        }
    }

    public static Coordinate toWTMFromLatLng(double d, double d2) {
        try {
            return RenderViewController.toWTMFromLatLng(d2, d);
        } catch (Exception e) {
            MapLogger.e(e);
            return null;
        }
    }
}
