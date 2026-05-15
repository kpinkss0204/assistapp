package org.pytorch;

/* JADX INFO: loaded from: classes4.dex */
interface INativePeer {
    IValue forward(IValue... iValueArr);

    void resetNative();

    IValue runMethod(String str, IValue... iValueArr);
}
