package com.kakao.vectormap;

import android.content.Context;
import com.kakao.vectormap.utils.MapUtils;
import java.util.concurrent.Executors;

/* JADX INFO: loaded from: classes4.dex */
class MapAuthenticator {

    public interface OnResponseListener {
        void onMapAuthFailure(MapAuthException mapAuthException);

        void onMapAuthSucceed();
    }

    static native String getUrlByPhase(String str);

    static native boolean isByPass(String str);

    MapAuthenticator() {
    }

    static void request(Context context, OnResponseListener onResponseListener) {
        boolean zIsByPass = isByPass(MapUtils.encrypt(context.getPackageName()));
        try {
            MapAuthHttpClient mapAuthHttpClient = new MapAuthHttpClient(context, getUrlByPhase(KakaoMapSdk.INSTANCE.getPhase().getValue()), zIsByPass);
            mapAuthHttpClient.setListener(onResponseListener);
            Executors.newSingleThreadExecutor().execute(mapAuthHttpClient);
        } catch (Exception e) {
            if (zIsByPass) {
                if (onResponseListener != null) {
                    onResponseListener.onMapAuthSucceed();
                }
            } else if (onResponseListener != null) {
                onResponseListener.onMapAuthFailure(new MapAuthException(-2, e));
            }
        }
    }
}
