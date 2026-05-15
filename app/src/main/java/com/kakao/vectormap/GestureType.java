package com.kakao.vectormap;

/* JADX INFO: loaded from: classes4.dex */
public enum GestureType {
    OneFingerDoubleTap(1),
    TwoFingerSingleTap(2),
    Pan(5),
    Rotate(6),
    Zoom(7),
    Tilt(8),
    LongTapAndDrag(9),
    RotateZoom(10),
    OneFingerZoom(11),
    Unknown(17);

    private final int value;

    GestureType(int i) {
        this.value = i;
    }

    public int getValue() {
        return this.value;
    }

    public static GestureType getEnum(int i) {
        GestureType gestureType = OneFingerDoubleTap;
        if (i == gestureType.getValue()) {
            return gestureType;
        }
        GestureType gestureType2 = TwoFingerSingleTap;
        if (i == gestureType2.getValue()) {
            return gestureType2;
        }
        GestureType gestureType3 = Pan;
        if (i == gestureType3.getValue()) {
            return gestureType3;
        }
        GestureType gestureType4 = Rotate;
        if (i == gestureType4.getValue()) {
            return gestureType4;
        }
        GestureType gestureType5 = Zoom;
        if (i == gestureType5.getValue()) {
            return gestureType5;
        }
        GestureType gestureType6 = Tilt;
        if (i == gestureType6.getValue()) {
            return gestureType6;
        }
        GestureType gestureType7 = LongTapAndDrag;
        if (i == gestureType7.getValue()) {
            return gestureType7;
        }
        GestureType gestureType8 = RotateZoom;
        if (i == gestureType8.getValue()) {
            return gestureType8;
        }
        GestureType gestureType9 = OneFingerZoom;
        return i == gestureType9.getValue() ? gestureType9 : Unknown;
    }
}
