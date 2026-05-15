package com.kakao.vectormap.internal;

import android.graphics.Bitmap;
import java.io.IOException;

/* JADX INFO: loaded from: classes4.dex */
public interface IStyleBuilder {
    IStyleBuilder addImage(String str, int i, Bitmap bitmap) throws IOException, RuntimeException;

    IStyleBuilder addName(String str) throws IOException, RuntimeException;

    IStyleBuilder addValue(String str) throws IOException, RuntimeException;

    IStyleBuilder addValue(String str, double d) throws IOException, RuntimeException;

    IStyleBuilder addValue(String str, float f) throws IOException, RuntimeException;

    IStyleBuilder addValue(String str, int i) throws IOException, RuntimeException;

    IStyleBuilder addValue(String str, String str2) throws IOException, RuntimeException;

    IStyleBuilder addValue(String str, boolean z) throws IOException, RuntimeException;

    IStyleBuilder beginArray() throws IOException;

    IStyleBuilder beginObject() throws IOException;

    IStyleBuilder endArray() throws IOException;

    IStyleBuilder endObject() throws IOException;
}
