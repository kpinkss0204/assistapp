package com.kakao.vectormap;

/* JADX INFO: loaded from: classes4.dex */
public class RoadViewByDate {
    public final String address;
    public final String date;
    public final int index;

    public RoadViewByDate(int i, String str, String str2) {
        this.index = i;
        this.date = str;
        this.address = str2;
    }

    public int getIndex() {
        return this.index;
    }

    public String getDate() {
        return this.date;
    }

    public String getAddress() {
        return this.address;
    }

    public String toString() {
        return "RoadViewByDate(index=" + this.index + ", date=" + this.date + ", address=" + this.address + ")";
    }
}
