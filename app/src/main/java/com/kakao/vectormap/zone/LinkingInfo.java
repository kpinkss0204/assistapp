package com.kakao.vectormap.zone;

/* JADX INFO: loaded from: classes4.dex */
public class LinkingInfo {
    public final String detailId;
    public final String zoneId;

    LinkingInfo(String str, String str2) {
        this.zoneId = str;
        this.detailId = str2;
    }

    public static LinkingInfo from(String str, String str2) {
        return new LinkingInfo(str, str2);
    }

    public String getZoneId() {
        return this.zoneId;
    }

    public String getDetailId() {
        return this.detailId;
    }
}
