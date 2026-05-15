package com.kakao.vectormap;

import com.kakao.vectormap.utils.MapUtils;

/* JADX INFO: loaded from: classes4.dex */
public abstract class KakaoMapReadyCallback extends MapReadyCallback {
    @Override // com.kakao.vectormap.MapReadyCallback
    public int getZoomLevel() {
        return 15;
    }

    public abstract void onMapReady(KakaoMap kakaoMap);

    public KakaoMapReadyCallback() {
        super("map_" + MapUtils.getUniqueId());
    }

    @Override // com.kakao.vectormap.MapReadyCallback
    public LatLng getPosition() {
        return LatLng.from(37.402005d, 127.108621d);
    }

    public MapViewInfo getMapViewInfo() {
        return MapViewInfo.from("openmap", MapViewInfo.DEFAULT_MAP_TYPE);
    }
}
