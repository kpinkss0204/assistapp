package com.kakao.vectormap;

/* JADX INFO: loaded from: classes4.dex */
public enum KakaoMapPhase {
    REAL("real"),
    ALPHA("alpha"),
    BETA("beta"),
    SANDBOX("sandbox");

    private final String value;

    KakaoMapPhase(String str) {
        this.value = str;
    }

    public String getValue() {
        return this.value;
    }

    public static KakaoMapPhase getEnum(String str) {
        KakaoMapPhase kakaoMapPhase = REAL;
        if (str.equals(kakaoMapPhase.getValue())) {
            return kakaoMapPhase;
        }
        KakaoMapPhase kakaoMapPhase2 = ALPHA;
        if (str.equals(kakaoMapPhase2.getValue())) {
            return kakaoMapPhase2;
        }
        KakaoMapPhase kakaoMapPhase3 = BETA;
        if (str.equals(kakaoMapPhase3.getValue())) {
            return kakaoMapPhase3;
        }
        KakaoMapPhase kakaoMapPhase4 = SANDBOX;
        if (str.equals(kakaoMapPhase4.getValue())) {
            return kakaoMapPhase4;
        }
        MapLogger.e("KakaoMapPhase getEnum failure. invalid value.");
        return kakaoMapPhase;
    }
}
