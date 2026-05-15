package com.kakao.vectormap;

import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import androidx.core.os.EnvironmentCompat;
import androidx.vectordrawable.graphics.drawable.PathInterpolatorCompat;
import com.google.common.net.HttpHeaders;
import com.kakao.vectormap.MapAuthenticator;
import java.net.URL;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import javax.net.ssl.HttpsURLConnection;

/* JADX INFO: loaded from: classes4.dex */
public class MapAuthHttpClient implements Runnable {
    private String authUrl;
    private final boolean isByPassed;
    private MapAuthenticator.OnResponseListener listener;
    private Map<String, String> requestHeaders;
    private int readTimeout = 15000;
    private int[] retryConnectionTimeouts = {PathInterpolatorCompat.MAX_NUM_POINTS, 5000, 15000};

    public MapAuthHttpClient(Context context, String str, boolean z) {
        this.authUrl = str;
        this.isByPassed = z;
        HashMap map = new HashMap();
        this.requestHeaders = map;
        map.put(HttpHeaders.ACCEPT, "application/json");
        this.requestHeaders.put(HttpHeaders.AUTHORIZATION, "KakaoAK " + KakaoMapSdk.INSTANCE.getAppKey());
        this.requestHeaders.put("KA", getKAHeader(context));
    }

    public void setListener(MapAuthenticator.OnResponseListener onResponseListener) {
        this.listener = onResponseListener;
    }

    public void setReadTimeout(int i) {
        this.readTimeout = i;
    }

    public void setConnectionTimeout(int i) {
        this.retryConnectionTimeouts[0] = i;
    }

    private String getKAHeader(Context context) {
        String language;
        Locale locale = context.getResources().getConfiguration().getLocales().get(0);
        language = "ko";
        String country = "KR";
        if (locale != null) {
            language = locale.getLanguage().isEmpty() ? "ko" : locale.getLanguage();
            if (!locale.getCountry().isEmpty()) {
                country = locale.getCountry();
            }
        }
        return String.format("%s/%s %s/android-%s %s/%s-%s %s/%s %s/%s %s/%s", "mapSdk", BuildConfig.SDK_VERSION, "os", Integer.valueOf(Build.VERSION.SDK_INT), "lang", language, country, "origin", KakaoMapSdk.INSTANCE.getHashKey(), "device", Build.MODEL == null ? EnvironmentCompat.MEDIA_UNKNOWN : Build.MODEL.replaceAll(" ", ""), "android_pkg", context.getPackageName());
    }

    @Override // java.lang.Runnable
    public void run() {
        int i = 0;
        while (true) {
            int[] iArr = this.retryConnectionTimeouts;
            if (i >= iArr.length) {
                return;
            }
            int i2 = i + 1;
            int i3 = iArr[i];
            try {
                HttpsURLConnection httpsURLConnection = (HttpsURLConnection) new URL(this.authUrl).openConnection();
                httpsURLConnection.setRequestMethod("GET");
                httpsURLConnection.setDoInput(true);
                httpsURLConnection.setDoOutput(true);
                httpsURLConnection.setUseCaches(false);
                httpsURLConnection.setReadTimeout(this.readTimeout);
                httpsURLConnection.setConnectTimeout(i3);
                for (String str : this.requestHeaders.keySet()) {
                    httpsURLConnection.setRequestProperty(str, this.requestHeaders.get(str));
                }
                Log.d(Const.TAG, "---> RequestHeader(" + httpsURLConnection.getURL().toString() + ") " + httpsURLConnection.getRequestProperties());
                httpsURLConnection.connect();
                int responseCode = httpsURLConnection.getResponseCode();
                if (responseCode == 200 || (responseCode >= 500 && responseCode < 600)) {
                    callSucceed(this.listener);
                    Log.v(Const.TAG, "<-- " + httpsURLConnection.getHeaderFields());
                    return;
                } else {
                    callFailure(this.listener, new MapAuthException(responseCode, httpsURLConnection.getResponseMessage()));
                    Log.e(Const.TAG, "<-- " + httpsURLConnection.getHeaderFields());
                    return;
                }
            } catch (Exception e) {
                if (i2 >= this.retryConnectionTimeouts.length) {
                    callSucceed(this.listener);
                }
                MapLogger.w(e.getMessage());
                i = i2;
            }
        }
    }

    void callSucceed(final MapAuthenticator.OnResponseListener onResponseListener) {
        if (onResponseListener == null) {
            return;
        }
        new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.kakao.vectormap.MapAuthHttpClient.1
            @Override // java.lang.Runnable
            public void run() {
                onResponseListener.onMapAuthSucceed();
            }
        });
    }

    void callFailure(final MapAuthenticator.OnResponseListener onResponseListener, final MapAuthException mapAuthException) {
        if (this.isByPassed) {
            callSucceed(onResponseListener);
        } else {
            if (onResponseListener == null) {
                return;
            }
            new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.kakao.vectormap.MapAuthHttpClient.2
                @Override // java.lang.Runnable
                public void run() {
                    onResponseListener.onMapAuthFailure(mapAuthException);
                }
            });
        }
    }
}
