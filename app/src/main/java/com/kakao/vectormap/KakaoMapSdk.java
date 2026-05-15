package com.kakao.vectormap;

import android.content.Context;
import com.getkeepsafe.relinker.ReLinker;
import com.kakao.vectormap.utils.MapUtils;

/* JADX INFO: loaded from: classes4.dex */
public class KakaoMapSdk {
    public static KakaoMapSdk INSTANCE;
    private String appKey;
    private Context context;
    private String hashKey;
    private boolean initialized;
    private KakaoMapPhase phase;

    KakaoMapSdk(Context context, String str, KakaoMapPhase kakaoMapPhase) {
        this.initialized = false;
        if (context == null) {
            throw new IllegalArgumentException("Context cannot be null.");
        }
        if (str == null || str.isEmpty()) {
            throw new IllegalArgumentException("AppKey cannot be null or empty.");
        }
        ReLinker.loadLibrary(context, "K3fAndroid");
        this.context = context.getApplicationContext();
        this.appKey = str;
        this.phase = kakaoMapPhase;
        this.hashKey = MapUtils.getHashKey(context);
        this.initialized = true;
    }

    public static void init(Context context, String str) {
        init(context, str, KakaoMapPhase.REAL);
    }

    public static void init(Context context, String str, KakaoMapPhase kakaoMapPhase) {
        INSTANCE = new KakaoMapSdk(context, str, kakaoMapPhase);
    }

    public static boolean isInitialized() {
        KakaoMapSdk kakaoMapSdk = INSTANCE;
        return kakaoMapSdk != null && kakaoMapSdk.initialized;
    }

    public String getAppKey() {
        return this.appKey;
    }

    public KakaoMapPhase getPhase() {
        return this.phase;
    }

    public Context getContext() {
        return this.context;
    }

    public String getHashKey() {
        return this.hashKey;
    }

    public String toString() {
        return "KakaoMapSdk{appKey='" + this.appKey + "', hashKey='" + this.hashKey + "', context=" + this.context + ", phase=" + this.phase + '}';
    }
}
