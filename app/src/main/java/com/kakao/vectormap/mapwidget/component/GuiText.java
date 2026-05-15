package com.kakao.vectormap.mapwidget.component;

import androidx.core.view.ViewCompat;

/* JADX INFO: loaded from: classes4.dex */
public class GuiText extends GuiView {
    public int lineCount;
    public int strokeColor;
    public int strokeSize;
    public String text;
    public int textColor;
    public int textSize;

    public GuiText() {
        this.text = "";
        this.textSize = 14;
        this.textColor = ViewCompat.MEASURED_STATE_MASK;
        this.strokeSize = 0;
        this.strokeColor = 0;
        this.lineCount = 0;
        this.type = 4;
    }

    public GuiText(String str) {
        this.text = "";
        this.textSize = 14;
        this.textColor = ViewCompat.MEASURED_STATE_MASK;
        this.strokeSize = 0;
        this.strokeColor = 0;
        this.lineCount = 0;
        this.type = 4;
        this.text = str;
    }

    public void setTextSize(int i) {
        this.textSize = i;
    }

    public int getTextSize() {
        return this.textSize;
    }

    public void setTextColor(int i) {
        this.textColor = i;
    }

    public int getTextColor() {
        return this.textColor;
    }

    public void setStrokeColor(int i) {
        this.strokeColor = i;
    }

    public int getStrokeColor() {
        return this.strokeColor;
    }

    public void setStrokeSize(int i) {
        this.strokeSize = i;
    }

    public int getStrokeSize() {
        return this.strokeSize;
    }

    public void setText(String str) {
        this.text = str;
        if (str == null || str.isEmpty()) {
            this.lineCount = 0;
        } else {
            String[] strArrSplit = str.split("\\r?\\n");
            this.lineCount = strArrSplit != null ? strArrSplit.length : 0;
        }
    }

    public String getText() {
        return this.text;
    }

    public int getLineCount() {
        return this.lineCount;
    }
}
