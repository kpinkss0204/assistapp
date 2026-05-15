package com.kakao.vectormap.mapwidget;

/* JADX INFO: loaded from: classes4.dex */
public class MapAlign {
    public Align horizontalAlign;
    public Align verticalAlign;

    MapAlign(Align align, Align align2) {
        this.verticalAlign = Align.Top;
        Align align3 = Align.Left;
        this.verticalAlign = align;
        this.horizontalAlign = align2;
    }

    public static MapAlign Create() {
        return new MapAlign(Align.Top, Align.Left);
    }

    public static MapAlign Create(Align align, Align align2) {
        if (align == Align.Left || align == Align.Right) {
            align = Align.Top;
        }
        if (align2 == Align.Top || align2 == Align.Bottom) {
            align2 = Align.Left;
        }
        return new MapAlign(align, align2);
    }
}
