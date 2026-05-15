package com.kakao.vectormap.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.util.Base64;
import com.kakao.vectormap.LatLng;
import com.kakao.vectormap.MapLogger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Collection;
import java.util.UUID;

/* JADX INFO: loaded from: classes4.dex */
public class MapUtils {
    public static String getUniqueId(String str) {
        return (str == null || str.isEmpty()) ? getUniqueId() : str;
    }

    public static String getUniqueId() {
        return UUID.randomUUID().toString();
    }

    public static String getUniqueId(int i) {
        return i + getUniqueId();
    }

    public static String getUniqueId(String str, int i) {
        return (str == null || str.isEmpty()) ? getUniqueId(i) : str;
    }

    public static String generateId(String str) {
        return (str == null || str.isEmpty()) ? getUniqueId() : str;
    }

    public static boolean isNullOrEmpty(Collection collection) {
        return collection == null || collection.isEmpty();
    }

    public static boolean isNullOrEmpty(Object obj) {
        return obj == null || obj.equals("");
    }

    public static String getHashKey(Context context) {
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 64);
            if (packageInfo == null) {
                MapLogger.e("Authentication failure. PackageInfo is null");
                return "";
            }
            Signature[] signatureArr = packageInfo.signatures;
            if (signatureArr.length > 0) {
                Signature signature = signatureArr[0];
                try {
                    MessageDigest messageDigest = MessageDigest.getInstance("SHA");
                    messageDigest.update(signature.toByteArray());
                    return Base64.encodeToString(messageDigest.digest(), 0).trim();
                } catch (NoSuchAlgorithmException e) {
                    MapLogger.e(e.getLocalizedMessage());
                    return "";
                }
            }
            MapLogger.e("Authentication failure. Signature is invalid.");
            return "";
        } catch (PackageManager.NameNotFoundException e2) {
            MapLogger.e(e2.getLocalizedMessage());
            return "";
        }
    }

    public static String encrypt(String str) {
        try {
            byte[] bArrDigest = MessageDigest.getInstance("SHA-256").digest(str.getBytes("UTF-8"));
            StringBuffer stringBuffer = new StringBuffer();
            for (byte b : bArrDigest) {
                String hexString = Integer.toHexString(b & 255);
                if (hexString.length() == 1) {
                    stringBuffer.append('0');
                }
                stringBuffer.append(hexString);
            }
            return stringBuffer.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static LatLng getEndPoint(LatLng latLng, int i, double d) {
        double latitude = latLng.getLatitude() * 0.017453292519444445d;
        double longitude = latLng.getLongitude() * 0.017453292519444445d;
        double d2 = ((double) i) / 6371000.0d;
        double dAsin = Math.asin((Math.sin(latitude) * Math.cos(d2)) + (Math.cos(latitude) * Math.sin(d2) * Math.cos(d)));
        return LatLng.from(dAsin * 57.29577951471995d, (longitude + Math.atan2(Math.sin(d) * Math.sin(d2) * Math.cos(latitude), Math.cos(d2) - (Math.sin(latitude) * Math.sin(dAsin)))) * 57.29577951471995d);
    }
}
