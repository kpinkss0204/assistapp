package androidx.compose.ui.text.android;

import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextDirectionHeuristic;
import android.text.TextPaint;
import android.text.TextUtils;
import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Metadata;

/* JADX INFO: compiled from: StaticLayoutFactory.android.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\r\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\u0015\n\u0002\b\u0004\bÇ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002JØ\u0001\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\f2\b\b\u0002\u0010\u000e\u001a\u00020\f2\b\b\u0002\u0010\u000f\u001a\u00020\u00102\b\b\u0002\u0010\u0011\u001a\u00020\u00122\b\b\u0003\u0010\u0013\u001a\u00020\f2\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u00152\b\b\u0003\u0010\u0016\u001a\u00020\f2\b\b\u0003\u0010\u0017\u001a\u00020\u00182\b\b\u0002\u0010\u0019\u001a\u00020\u00182\b\b\u0002\u0010\u001a\u001a\u00020\f2\b\b\u0002\u0010\u001b\u001a\u00020\u001c2\b\b\u0002\u0010\u001d\u001a\u00020\u001c2\b\b\u0002\u0010\u001e\u001a\u00020\f2\b\b\u0002\u0010\u001f\u001a\u00020\f2\b\b\u0002\u0010 \u001a\u00020\f2\b\b\u0002\u0010!\u001a\u00020\f2\n\b\u0002\u0010\"\u001a\u0004\u0018\u00010#2\n\b\u0002\u0010$\u001a\u0004\u0018\u00010#J\u0016\u0010%\u001a\u00020\u001c2\u0006\u0010&\u001a\u00020\u00062\u0006\u0010\u001d\u001a\u00020\u001cR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006'"}, d2 = {"Landroidx/compose/ui/text/android/StaticLayoutFactory;", "", "()V", "delegate", "Landroidx/compose/ui/text/android/StaticLayoutFactoryImpl;", "create", "Landroid/text/StaticLayout;", "text", "", "paint", "Landroid/text/TextPaint;", "width", "", "start", "end", "textDir", "Landroid/text/TextDirectionHeuristic;", "alignment", "Landroid/text/Layout$Alignment;", "maxLines", "ellipsize", "Landroid/text/TextUtils$TruncateAt;", "ellipsizedWidth", "lineSpacingMultiplier", "", "lineSpacingExtra", "justificationMode", "includePadding", "", "useFallbackLineSpacing", "breakStrategy", "lineBreakStyle", "lineBreakWordStyle", "hyphenationFrequency", "leftIndents", "", "rightIndents", "isFallbackLineSpacingEnabled", "layout", "ui-text_release"}, k = 1, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
public final class StaticLayoutFactory {
    public static final StaticLayoutFactory INSTANCE = new StaticLayoutFactory();
    private static final StaticLayoutFactoryImpl delegate = new StaticLayoutFactory23();
    public static final int $stable = 8;

    private StaticLayoutFactory() {
    }

    public static /* synthetic */ StaticLayout create$default(StaticLayoutFactory staticLayoutFactory, CharSequence charSequence, TextPaint textPaint, int i, int i2, int i3, TextDirectionHeuristic textDirectionHeuristic, Layout.Alignment alignment, int i4, TextUtils.TruncateAt truncateAt, int i5, float f, float f2, int i6, boolean z, boolean z2, int i7, int i8, int i9, int i10, int[] iArr, int[] iArr2, int i11, Object obj) {
        int[] iArr3;
        StaticLayoutFactory staticLayoutFactory2;
        CharSequence charSequence2;
        TextPaint textPaint2;
        int i12;
        int i13 = (i11 & 8) != 0 ? 0 : i2;
        int length = (i11 & 16) != 0 ? charSequence.length() : i3;
        TextDirectionHeuristic dEFAULT_TEXT_DIRECTION_HEURISTIC$ui_text_release = (i11 & 32) != 0 ? LayoutCompat.INSTANCE.getDEFAULT_TEXT_DIRECTION_HEURISTIC$ui_text_release() : textDirectionHeuristic;
        Layout.Alignment dEFAULT_LAYOUT_ALIGNMENT$ui_text_release = (i11 & 64) != 0 ? LayoutCompat.INSTANCE.getDEFAULT_LAYOUT_ALIGNMENT$ui_text_release() : alignment;
        int i14 = (i11 & 128) != 0 ? Integer.MAX_VALUE : i4;
        TextUtils.TruncateAt truncateAt2 = (i11 & 256) != 0 ? null : truncateAt;
        int i15 = (i11 & 512) != 0 ? i : i5;
        float f3 = (i11 & 1024) != 0 ? 1.0f : f;
        float f4 = (i11 & 2048) != 0 ? 0.0f : f2;
        int i16 = (i11 & 4096) != 0 ? 0 : i6;
        boolean z3 = (i11 & 8192) != 0 ? false : z;
        boolean z4 = (i11 & 16384) != 0 ? true : z2;
        int i17 = (32768 & i11) != 0 ? 0 : i7;
        int i18 = (65536 & i11) != 0 ? 0 : i8;
        int i19 = (131072 & i11) != 0 ? 0 : i9;
        int i20 = (262144 & i11) != 0 ? 0 : i10;
        int[] iArr4 = (524288 & i11) != 0 ? null : iArr;
        if ((i11 & 1048576) != 0) {
            iArr3 = null;
            charSequence2 = charSequence;
            textPaint2 = textPaint;
            i12 = i;
            staticLayoutFactory2 = staticLayoutFactory;
        } else {
            iArr3 = iArr2;
            staticLayoutFactory2 = staticLayoutFactory;
            charSequence2 = charSequence;
            textPaint2 = textPaint;
            i12 = i;
        }
        return staticLayoutFactory2.create(charSequence2, textPaint2, i12, i13, length, dEFAULT_TEXT_DIRECTION_HEURISTIC$ui_text_release, dEFAULT_LAYOUT_ALIGNMENT$ui_text_release, i14, truncateAt2, i15, f3, f4, i16, z3, z4, i17, i18, i19, i20, iArr4, iArr3);
    }

    public final StaticLayout create(CharSequence text, TextPaint paint, int width, int start, int end, TextDirectionHeuristic textDir, Layout.Alignment alignment, int maxLines, TextUtils.TruncateAt ellipsize, int ellipsizedWidth, float lineSpacingMultiplier, float lineSpacingExtra, int justificationMode, boolean includePadding, boolean useFallbackLineSpacing, int breakStrategy, int lineBreakStyle, int lineBreakWordStyle, int hyphenationFrequency, int[] leftIndents, int[] rightIndents) {
        return delegate.create(new StaticLayoutParams(text, start, end, paint, width, textDir, alignment, maxLines, ellipsize, ellipsizedWidth, lineSpacingMultiplier, lineSpacingExtra, justificationMode, includePadding, useFallbackLineSpacing, breakStrategy, lineBreakStyle, lineBreakWordStyle, hyphenationFrequency, leftIndents, rightIndents));
    }

    public final boolean isFallbackLineSpacingEnabled(StaticLayout layout, boolean useFallbackLineSpacing) {
        return delegate.isFallbackLineSpacingEnabled(layout, useFallbackLineSpacing);
    }
}
