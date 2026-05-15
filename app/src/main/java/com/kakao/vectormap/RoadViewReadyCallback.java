package com.kakao.vectormap;

import com.kakao.vectormap.utils.MapUtils;

/* JADX INFO: loaded from: classes4.dex */
public abstract class RoadViewReadyCallback extends MapReadyCallback {
    public RoadViewRequest getRoadViewRequest() {
        return null;
    }

    public abstract void onRoadViewReady(RoadView roadView);

    public RoadViewReadyCallback() {
        super("roadview_" + MapUtils.getUniqueId());
    }

    @Override // com.kakao.vectormap.MapReadyCallback
    public LatLng getPosition() {
        return LatLng.from(37.402005d, 127.108621d);
    }

    public RoadViewInfo getViewInfo() {
        return new RoadViewInfo("roadview", "roadview");
    }
}
