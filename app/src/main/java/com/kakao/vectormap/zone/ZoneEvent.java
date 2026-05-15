package com.kakao.vectormap.zone;

import java.util.Map;

/* JADX INFO: loaded from: classes4.dex */
public class ZoneEvent {
    public final String detailZoneId;
    public final String[] detailZones;
    public final Map<String, LinkingInfo[]> linkingInfo;
    public final String zoneId;
    public final String zoneType;

    ZoneEvent(String str, String str2, String str3, Map<String, LinkingInfo[]> map, String... strArr) {
        this.zoneType = str;
        this.zoneId = str2;
        this.detailZoneId = str3;
        this.detailZones = strArr;
        this.linkingInfo = map;
    }

    public static ZoneEvent from(String str, String str2, String str3, Map<String, LinkingInfo[]> map, String... strArr) {
        return new ZoneEvent(str, str2, str3, map, strArr);
    }

    public String getDetailZoneId() {
        return this.detailZoneId;
    }

    public String getZoneId() {
        return this.zoneId;
    }

    public String getZoneType() {
        return this.zoneType;
    }

    public String[] getDetailZones() {
        return this.detailZones;
    }

    public Map<String, LinkingInfo[]> getLinkingInfo() {
        return this.linkingInfo;
    }
}
