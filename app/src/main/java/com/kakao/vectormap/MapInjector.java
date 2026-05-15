package com.kakao.vectormap;

import com.kakao.vectormap.internal.IKakaoMapDelegate;
import com.kakao.vectormap.internal.IRoadViewDelegate;

/* JADX INFO: loaded from: classes4.dex */
public class MapInjector {
    public static KakaoMap newKakaoMap(IKakaoMapDelegate iKakaoMapDelegate) {
        return new KakaoMap(iKakaoMapDelegate);
    }

    public static RoadView newRoadView(IRoadViewDelegate iRoadViewDelegate) {
        return new RoadView(iRoadViewDelegate);
    }
}
