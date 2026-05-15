package com.kakao.vectormap.label;

import android.content.Context;
import androidx.core.view.ViewCompat;
import com.kakao.vectormap.R;
import java.util.Objects;

/* JADX INFO: loaded from: classes4.dex */
public class LabelTextStyle {
    public float aspectRatio;
    public int characterSpace;
    public int color;
    public String font;
    public float lineSpace;
    public int size;
    public int stroke;
    public int strokeColor;

    LabelTextStyle() {
        this.font = "";
        this.size = 24;
        this.color = ViewCompat.MEASURED_STATE_MASK;
        this.stroke = 0;
        this.strokeColor = 0;
        this.characterSpace = 0;
        this.lineSpace = 1.0f;
        this.aspectRatio = 1.0f;
    }

    LabelTextStyle(int i, int i2) {
        this.font = "";
        this.stroke = 0;
        this.strokeColor = 0;
        this.characterSpace = 0;
        this.lineSpace = 1.0f;
        this.aspectRatio = 1.0f;
        this.size = i;
        this.color = i2;
    }

    LabelTextStyle(int i, int i2, int i3, int i4) {
        this.font = "";
        this.size = i;
        this.color = i2;
        this.stroke = i3;
        this.strokeColor = i4;
        this.characterSpace = 0;
        this.lineSpace = 1.0f;
        this.aspectRatio = 1.0f;
    }

    LabelTextStyle(String str, int i, int i2, int i3, int i4, int i5, float f, float f2) {
        this.font = "";
        this.size = 24;
        this.color = ViewCompat.MEASURED_STATE_MASK;
        this.stroke = 0;
        this.strokeColor = 0;
        this.characterSpace = 0;
        this.lineSpace = 1.0f;
        this.aspectRatio = 1.0f;
        this.font = str == null ? "" : str;
        this.size = i;
        this.color = i2;
        this.stroke = i3;
        this.strokeColor = i4;
        this.characterSpace = i5;
        this.lineSpace = f;
        this.aspectRatio = f2;
    }

    public static LabelTextStyle from(int i, int i2) {
        return new LabelTextStyle(i, i2);
    }

    public static LabelTextStyle from(int i, int i2, int i3, int i4) {
        return new LabelTextStyle("", i, i2, i3, i4, 0, 1.0f, 1.0f);
    }

    public static LabelTextStyle from(Context context, int i) {
        if (i == 0) {
            return new LabelTextStyle();
        }
        return new LabelTextStyle(context.obtainStyledAttributes(i, R.styleable.LabelTextAttr).getString(R.styleable.LabelTextAttr_mapTextFont), context.obtainStyledAttributes(i, R.styleable.LabelTextAttr).getInt(R.styleable.LabelTextAttr_mapTextSize, 24), context.obtainStyledAttributes(i, R.styleable.LabelTextAttr).getInteger(R.styleable.LabelTextAttr_mapTextColor, ViewCompat.MEASURED_STATE_MASK), context.obtainStyledAttributes(i, R.styleable.LabelTextAttr).getInt(R.styleable.LabelTextAttr_mapTextStrokeWidth, 0), context.obtainStyledAttributes(i, R.styleable.LabelTextAttr).getInteger(R.styleable.LabelTextAttr_mapTextStrokeColor, 0), context.obtainStyledAttributes(i, R.styleable.LabelTextAttr).getInt(R.styleable.LabelTextAttr_mapTextCharacterSpace, 0), context.obtainStyledAttributes(i, R.styleable.LabelTextAttr).getFloat(R.styleable.LabelTextAttr_mapTextLineSpace, 1.0f), context.obtainStyledAttributes(i, R.styleable.LabelTextAttr).getFloat(R.styleable.LabelTextAttr_mapTextAspectRatio, 1.0f));
    }

    public LabelTextStyle setFont(String str) {
        if (str == null) {
            str = "";
        }
        this.font = str;
        return this;
    }

    public LabelTextStyle setFont(int i) {
        this.font = String.valueOf(i);
        return this;
    }

    public String getFont() {
        return this.font;
    }

    public LabelTextStyle setCharacterSpace(int i) {
        this.characterSpace = i;
        return this;
    }

    public int getCharacterSpace() {
        return this.characterSpace;
    }

    public LabelTextStyle setLineSpace(float f) {
        this.lineSpace = f;
        return this;
    }

    public float getLineSpace() {
        return this.lineSpace;
    }

    public LabelTextStyle setAspectRatio(float f) {
        this.aspectRatio = f;
        return this;
    }

    public float getAspectRatio() {
        return this.aspectRatio;
    }

    public int getSize() {
        return this.size;
    }

    public int getColor() {
        return this.color;
    }

    public int getStroke() {
        return this.stroke;
    }

    public int getStrokeColor() {
        return this.strokeColor;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof LabelTextStyle)) {
            return false;
        }
        LabelTextStyle labelTextStyle = (LabelTextStyle) obj;
        return Objects.equals(labelTextStyle.getFont(), getFont()) && labelTextStyle.getSize() == getSize() && getColor() == labelTextStyle.getColor() && labelTextStyle.getStroke() == getStroke() && getStrokeColor() == labelTextStyle.getStrokeColor() && getCharacterSpace() == labelTextStyle.getCharacterSpace() && getLineSpace() == labelTextStyle.getLineSpace() && getAspectRatio() == labelTextStyle.getAspectRatio();
    }

    public int hashCode() {
        return Objects.hash(getFont(), Integer.valueOf(getSize()), Integer.valueOf(getColor()), Integer.valueOf(getStroke()), Integer.valueOf(getStrokeColor()), Integer.valueOf(getCharacterSpace()), Float.valueOf(getLineSpace()), Float.valueOf(getAspectRatio()));
    }
}
