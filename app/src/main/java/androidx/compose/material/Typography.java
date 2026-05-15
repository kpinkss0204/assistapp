package androidx.compose.material;

import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.text.font.FontFamily;
import androidx.compose.ui.text.font.FontWeight;
import androidx.compose.ui.text.font.SystemFontFamily;
import androidx.compose.ui.unit.TextUnitKt;
import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Typography.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u001e\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u0093\u0001\b\u0016\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0005\u0012\b\b\u0002\u0010\b\u001a\u00020\u0005\u0012\b\b\u0002\u0010\t\u001a\u00020\u0005\u0012\b\b\u0002\u0010\n\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u000b\u001a\u00020\u0005\u0012\b\b\u0002\u0010\f\u001a\u00020\u0005\u0012\b\b\u0002\u0010\r\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u000e\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u000f\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0010\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0011\u001a\u00020\u0005¢\u0006\u0002\u0010\u0012Bo\b\u0000\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0005\u0012\u0006\u0010\b\u001a\u00020\u0005\u0012\u0006\u0010\t\u001a\u00020\u0005\u0012\u0006\u0010\n\u001a\u00020\u0005\u0012\u0006\u0010\u000b\u001a\u00020\u0005\u0012\u0006\u0010\f\u001a\u00020\u0005\u0012\u0006\u0010\r\u001a\u00020\u0005\u0012\u0006\u0010\u000e\u001a\u00020\u0005\u0012\u0006\u0010\u000f\u001a\u00020\u0005\u0012\u0006\u0010\u0010\u001a\u00020\u0005\u0012\u0006\u0010\u0011\u001a\u00020\u0005¢\u0006\u0002\u0010\u0013J\u0088\u0001\u0010\"\u001a\u00020\u00002\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u00052\b\b\u0002\u0010\b\u001a\u00020\u00052\b\b\u0002\u0010\t\u001a\u00020\u00052\b\b\u0002\u0010\n\u001a\u00020\u00052\b\b\u0002\u0010\u000b\u001a\u00020\u00052\b\b\u0002\u0010\f\u001a\u00020\u00052\b\b\u0002\u0010\r\u001a\u00020\u00052\b\b\u0002\u0010\u000e\u001a\u00020\u00052\b\b\u0002\u0010\u000f\u001a\u00020\u00052\b\b\u0002\u0010\u0010\u001a\u00020\u00052\b\b\u0002\u0010\u0011\u001a\u00020\u0005J\u0013\u0010#\u001a\u00020$2\b\u0010%\u001a\u0004\u0018\u00010\u0001H\u0096\u0002J\b\u0010&\u001a\u00020'H\u0016J\b\u0010(\u001a\u00020)H\u0016R\u0011\u0010\r\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0011\u0010\u000e\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0015R\u0011\u0010\u000f\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0015R\u0011\u0010\u0010\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0015R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0015R\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0015R\u0011\u0010\u0007\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0015R\u0011\u0010\b\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0015R\u0011\u0010\t\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u0015R\u0011\u0010\n\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u0015R\u0011\u0010\u0011\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u0015R\u0011\u0010\u000b\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b \u0010\u0015R\u0011\u0010\f\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\u0015¨\u0006*"}, d2 = {"Landroidx/compose/material/Typography;", "", "defaultFontFamily", "Landroidx/compose/ui/text/font/FontFamily;", "h1", "Landroidx/compose/ui/text/TextStyle;", "h2", "h3", "h4", "h5", "h6", "subtitle1", "subtitle2", "body1", "body2", "button", "caption", "overline", "(Landroidx/compose/ui/text/font/FontFamily;Landroidx/compose/ui/text/TextStyle;Landroidx/compose/ui/text/TextStyle;Landroidx/compose/ui/text/TextStyle;Landroidx/compose/ui/text/TextStyle;Landroidx/compose/ui/text/TextStyle;Landroidx/compose/ui/text/TextStyle;Landroidx/compose/ui/text/TextStyle;Landroidx/compose/ui/text/TextStyle;Landroidx/compose/ui/text/TextStyle;Landroidx/compose/ui/text/TextStyle;Landroidx/compose/ui/text/TextStyle;Landroidx/compose/ui/text/TextStyle;Landroidx/compose/ui/text/TextStyle;)V", "(Landroidx/compose/ui/text/TextStyle;Landroidx/compose/ui/text/TextStyle;Landroidx/compose/ui/text/TextStyle;Landroidx/compose/ui/text/TextStyle;Landroidx/compose/ui/text/TextStyle;Landroidx/compose/ui/text/TextStyle;Landroidx/compose/ui/text/TextStyle;Landroidx/compose/ui/text/TextStyle;Landroidx/compose/ui/text/TextStyle;Landroidx/compose/ui/text/TextStyle;Landroidx/compose/ui/text/TextStyle;Landroidx/compose/ui/text/TextStyle;Landroidx/compose/ui/text/TextStyle;)V", "getBody1", "()Landroidx/compose/ui/text/TextStyle;", "getBody2", "getButton", "getCaption", "getH1", "getH2", "getH3", "getH4", "getH5", "getH6", "getOverline", "getSubtitle1", "getSubtitle2", "copy", "equals", "", "other", "hashCode", "", "toString", "", "material_release"}, k = 1, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
public final class Typography {
    public static final int $stable = 0;
    private final TextStyle body1;
    private final TextStyle body2;
    private final TextStyle button;
    private final TextStyle caption;
    private final TextStyle h1;
    private final TextStyle h2;
    private final TextStyle h3;
    private final TextStyle h4;
    private final TextStyle h5;
    private final TextStyle h6;
    private final TextStyle overline;
    private final TextStyle subtitle1;
    private final TextStyle subtitle2;

    public Typography(TextStyle textStyle, TextStyle textStyle2, TextStyle textStyle3, TextStyle textStyle4, TextStyle textStyle5, TextStyle textStyle6, TextStyle textStyle7, TextStyle textStyle8, TextStyle textStyle9, TextStyle textStyle10, TextStyle textStyle11, TextStyle textStyle12, TextStyle textStyle13) {
        this.h1 = textStyle;
        this.h2 = textStyle2;
        this.h3 = textStyle3;
        this.h4 = textStyle4;
        this.h5 = textStyle5;
        this.h6 = textStyle6;
        this.subtitle1 = textStyle7;
        this.subtitle2 = textStyle8;
        this.body1 = textStyle9;
        this.body2 = textStyle10;
        this.button = textStyle11;
        this.caption = textStyle12;
        this.overline = textStyle13;
    }

    public final TextStyle getH1() {
        return this.h1;
    }

    public final TextStyle getH2() {
        return this.h2;
    }

    public final TextStyle getH3() {
        return this.h3;
    }

    public final TextStyle getH4() {
        return this.h4;
    }

    public final TextStyle getH5() {
        return this.h5;
    }

    public final TextStyle getH6() {
        return this.h6;
    }

    public final TextStyle getSubtitle1() {
        return this.subtitle1;
    }

    public final TextStyle getSubtitle2() {
        return this.subtitle2;
    }

    public final TextStyle getBody1() {
        return this.body1;
    }

    public final TextStyle getBody2() {
        return this.body2;
    }

    public final TextStyle getButton() {
        return this.button;
    }

    public final TextStyle getCaption() {
        return this.caption;
    }

    public final TextStyle getOverline() {
        return this.overline;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public /* synthetic */ Typography(FontFamily fontFamily, TextStyle textStyle, TextStyle textStyle2, TextStyle textStyle3, TextStyle textStyle4, TextStyle textStyle5, TextStyle textStyle6, TextStyle textStyle7, TextStyle textStyle8, TextStyle textStyle9, TextStyle textStyle10, TextStyle textStyle11, TextStyle textStyle12, TextStyle textStyle13, int i, DefaultConstructorMarker defaultConstructorMarker) {
        TextStyle textStyleM6525copyp1EtxEg$default;
        TextStyle textStyleM6525copyp1EtxEg$default2;
        TextStyle textStyleM6525copyp1EtxEg$default3;
        TextStyle textStyleM6525copyp1EtxEg$default4;
        TextStyle textStyleM6525copyp1EtxEg$default5;
        TextStyle textStyleM6525copyp1EtxEg$default6;
        TextStyle textStyleM6525copyp1EtxEg$default7;
        TextStyle textStyleM6525copyp1EtxEg$default8;
        TextStyle textStyleM6525copyp1EtxEg$default9;
        TextStyle textStyleM6525copyp1EtxEg$default10;
        TextStyle textStyleM6525copyp1EtxEg$default11;
        TextStyle textStyleM6525copyp1EtxEg$default12;
        TextStyle textStyleM6525copyp1EtxEg$default13;
        SystemFontFamily systemFontFamily = (i & 1) != 0 ? FontFamily.INSTANCE.getDefault() : fontFamily;
        if ((i & 2) != 0) {
            textStyleM6525copyp1EtxEg$default = TextStyle.m6525copyp1EtxEg$default(TypographyKt.getDefaultTextStyle(), 0L, TextUnitKt.getSp(96), FontWeight.INSTANCE.getLight(), null, null, null, null, TextUnitKt.getSp(-1.5d), null, null, null, 0L, null, null, null, 0, 0, TextUnitKt.getSp(112), null, null, null, 0, 0, null, 16646009, null);
        } else {
            textStyleM6525copyp1EtxEg$default = textStyle;
        }
        if ((i & 4) != 0) {
            textStyleM6525copyp1EtxEg$default2 = TextStyle.m6525copyp1EtxEg$default(TypographyKt.getDefaultTextStyle(), 0L, TextUnitKt.getSp(60), FontWeight.INSTANCE.getLight(), null, null, null, null, TextUnitKt.getSp(-0.5d), null, null, null, 0L, null, null, null, 0, 0, TextUnitKt.getSp(72), null, null, null, 0, 0, null, 16646009, null);
        } else {
            textStyleM6525copyp1EtxEg$default2 = textStyle2;
        }
        if ((i & 8) != 0) {
            textStyleM6525copyp1EtxEg$default3 = TextStyle.m6525copyp1EtxEg$default(TypographyKt.getDefaultTextStyle(), 0L, TextUnitKt.getSp(48), FontWeight.INSTANCE.getNormal(), null, null, null, null, TextUnitKt.getSp(0), null, null, null, 0L, null, null, null, 0, 0, TextUnitKt.getSp(56), null, null, null, 0, 0, null, 16646009, null);
        } else {
            textStyleM6525copyp1EtxEg$default3 = textStyle3;
        }
        if ((i & 16) != 0) {
            textStyleM6525copyp1EtxEg$default4 = TextStyle.m6525copyp1EtxEg$default(TypographyKt.getDefaultTextStyle(), 0L, TextUnitKt.getSp(34), FontWeight.INSTANCE.getNormal(), null, null, null, null, TextUnitKt.getSp(0.25d), null, null, null, 0L, null, null, null, 0, 0, TextUnitKt.getSp(36), null, null, null, 0, 0, null, 16646009, null);
        } else {
            textStyleM6525copyp1EtxEg$default4 = textStyle4;
        }
        if ((i & 32) != 0) {
            textStyleM6525copyp1EtxEg$default5 = TextStyle.m6525copyp1EtxEg$default(TypographyKt.getDefaultTextStyle(), 0L, TextUnitKt.getSp(24), FontWeight.INSTANCE.getNormal(), null, null, null, null, TextUnitKt.getSp(0), null, null, null, 0L, null, null, null, 0, 0, TextUnitKt.getSp(24), null, null, null, 0, 0, null, 16646009, null);
        } else {
            textStyleM6525copyp1EtxEg$default5 = textStyle5;
        }
        if ((i & 64) != 0) {
            textStyleM6525copyp1EtxEg$default6 = TextStyle.m6525copyp1EtxEg$default(TypographyKt.getDefaultTextStyle(), 0L, TextUnitKt.getSp(20), FontWeight.INSTANCE.getMedium(), null, null, null, null, TextUnitKt.getSp(0.15d), null, null, null, 0L, null, null, null, 0, 0, TextUnitKt.getSp(24), null, null, null, 0, 0, null, 16646009, null);
        } else {
            textStyleM6525copyp1EtxEg$default6 = textStyle6;
        }
        if ((i & 128) != 0) {
            textStyleM6525copyp1EtxEg$default7 = TextStyle.m6525copyp1EtxEg$default(TypographyKt.getDefaultTextStyle(), 0L, TextUnitKt.getSp(16), FontWeight.INSTANCE.getNormal(), null, null, null, null, TextUnitKt.getSp(0.15d), null, null, null, 0L, null, null, null, 0, 0, TextUnitKt.getSp(24), null, null, null, 0, 0, null, 16646009, null);
        } else {
            textStyleM6525copyp1EtxEg$default7 = textStyle7;
        }
        if ((i & 256) != 0) {
            textStyleM6525copyp1EtxEg$default8 = TextStyle.m6525copyp1EtxEg$default(TypographyKt.getDefaultTextStyle(), 0L, TextUnitKt.getSp(14), FontWeight.INSTANCE.getMedium(), null, null, null, null, TextUnitKt.getSp(0.1d), null, null, null, 0L, null, null, null, 0, 0, TextUnitKt.getSp(24), null, null, null, 0, 0, null, 16646009, null);
        } else {
            textStyleM6525copyp1EtxEg$default8 = textStyle8;
        }
        if ((i & 512) != 0) {
            textStyleM6525copyp1EtxEg$default9 = TextStyle.m6525copyp1EtxEg$default(TypographyKt.getDefaultTextStyle(), 0L, TextUnitKt.getSp(16), FontWeight.INSTANCE.getNormal(), null, null, null, null, TextUnitKt.getSp(0.5d), null, null, null, 0L, null, null, null, 0, 0, TextUnitKt.getSp(24), null, null, null, 0, 0, null, 16646009, null);
        } else {
            textStyleM6525copyp1EtxEg$default9 = textStyle9;
        }
        if ((i & 1024) != 0) {
            textStyleM6525copyp1EtxEg$default10 = TextStyle.m6525copyp1EtxEg$default(TypographyKt.getDefaultTextStyle(), 0L, TextUnitKt.getSp(14), FontWeight.INSTANCE.getNormal(), null, null, null, null, TextUnitKt.getSp(0.25d), null, null, null, 0L, null, null, null, 0, 0, TextUnitKt.getSp(20), null, null, null, 0, 0, null, 16646009, null);
        } else {
            textStyleM6525copyp1EtxEg$default10 = textStyle10;
        }
        if ((i & 2048) != 0) {
            textStyleM6525copyp1EtxEg$default11 = TextStyle.m6525copyp1EtxEg$default(TypographyKt.getDefaultTextStyle(), 0L, TextUnitKt.getSp(14), FontWeight.INSTANCE.getMedium(), null, null, null, null, TextUnitKt.getSp(1.25d), null, null, null, 0L, null, null, null, 0, 0, TextUnitKt.getSp(16), null, null, null, 0, 0, null, 16646009, null);
        } else {
            textStyleM6525copyp1EtxEg$default11 = textStyle11;
        }
        if ((i & 4096) != 0) {
            textStyleM6525copyp1EtxEg$default12 = TextStyle.m6525copyp1EtxEg$default(TypographyKt.getDefaultTextStyle(), 0L, TextUnitKt.getSp(12), FontWeight.INSTANCE.getNormal(), null, null, null, null, TextUnitKt.getSp(0.4d), null, null, null, 0L, null, null, null, 0, 0, TextUnitKt.getSp(16), null, null, null, 0, 0, null, 16646009, null);
        } else {
            textStyleM6525copyp1EtxEg$default12 = textStyle12;
        }
        if ((i & 8192) != 0) {
            textStyleM6525copyp1EtxEg$default13 = TextStyle.m6525copyp1EtxEg$default(TypographyKt.getDefaultTextStyle(), 0L, TextUnitKt.getSp(10), FontWeight.INSTANCE.getNormal(), null, null, null, null, TextUnitKt.getSp(1.5d), null, null, null, 0L, null, null, null, 0, 0, TextUnitKt.getSp(16), null, null, null, 0, 0, null, 16646009, null);
        } else {
            textStyleM6525copyp1EtxEg$default13 = textStyle13;
        }
        this(systemFontFamily, textStyleM6525copyp1EtxEg$default, textStyleM6525copyp1EtxEg$default2, textStyleM6525copyp1EtxEg$default3, textStyleM6525copyp1EtxEg$default4, textStyleM6525copyp1EtxEg$default5, textStyleM6525copyp1EtxEg$default6, textStyleM6525copyp1EtxEg$default7, textStyleM6525copyp1EtxEg$default8, textStyleM6525copyp1EtxEg$default9, textStyleM6525copyp1EtxEg$default10, textStyleM6525copyp1EtxEg$default11, textStyleM6525copyp1EtxEg$default12, textStyleM6525copyp1EtxEg$default13);
    }

    public Typography(FontFamily fontFamily, TextStyle textStyle, TextStyle textStyle2, TextStyle textStyle3, TextStyle textStyle4, TextStyle textStyle5, TextStyle textStyle6, TextStyle textStyle7, TextStyle textStyle8, TextStyle textStyle9, TextStyle textStyle10, TextStyle textStyle11, TextStyle textStyle12, TextStyle textStyle13) {
        this(TypographyKt.withDefaultFontFamily(textStyle, fontFamily), TypographyKt.withDefaultFontFamily(textStyle2, fontFamily), TypographyKt.withDefaultFontFamily(textStyle3, fontFamily), TypographyKt.withDefaultFontFamily(textStyle4, fontFamily), TypographyKt.withDefaultFontFamily(textStyle5, fontFamily), TypographyKt.withDefaultFontFamily(textStyle6, fontFamily), TypographyKt.withDefaultFontFamily(textStyle7, fontFamily), TypographyKt.withDefaultFontFamily(textStyle8, fontFamily), TypographyKt.withDefaultFontFamily(textStyle9, fontFamily), TypographyKt.withDefaultFontFamily(textStyle10, fontFamily), TypographyKt.withDefaultFontFamily(textStyle11, fontFamily), TypographyKt.withDefaultFontFamily(textStyle12, fontFamily), TypographyKt.withDefaultFontFamily(textStyle13, fontFamily));
    }

    public static /* synthetic */ Typography copy$default(Typography typography, TextStyle textStyle, TextStyle textStyle2, TextStyle textStyle3, TextStyle textStyle4, TextStyle textStyle5, TextStyle textStyle6, TextStyle textStyle7, TextStyle textStyle8, TextStyle textStyle9, TextStyle textStyle10, TextStyle textStyle11, TextStyle textStyle12, TextStyle textStyle13, int i, Object obj) {
        if ((i & 1) != 0) {
            textStyle = typography.h1;
        }
        return typography.copy(textStyle, (i & 2) != 0 ? typography.h2 : textStyle2, (i & 4) != 0 ? typography.h3 : textStyle3, (i & 8) != 0 ? typography.h4 : textStyle4, (i & 16) != 0 ? typography.h5 : textStyle5, (i & 32) != 0 ? typography.h6 : textStyle6, (i & 64) != 0 ? typography.subtitle1 : textStyle7, (i & 128) != 0 ? typography.subtitle2 : textStyle8, (i & 256) != 0 ? typography.body1 : textStyle9, (i & 512) != 0 ? typography.body2 : textStyle10, (i & 1024) != 0 ? typography.button : textStyle11, (i & 2048) != 0 ? typography.caption : textStyle12, (i & 4096) != 0 ? typography.overline : textStyle13);
    }

    public final Typography copy(TextStyle h1, TextStyle h2, TextStyle h3, TextStyle h4, TextStyle h5, TextStyle h6, TextStyle subtitle1, TextStyle subtitle2, TextStyle body1, TextStyle body2, TextStyle button, TextStyle caption, TextStyle overline) {
        return new Typography(h1, h2, h3, h4, h5, h6, subtitle1, subtitle2, body1, body2, button, caption, overline);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Typography)) {
            return false;
        }
        Typography typography = (Typography) other;
        return Intrinsics.areEqual(this.h1, typography.h1) && Intrinsics.areEqual(this.h2, typography.h2) && Intrinsics.areEqual(this.h3, typography.h3) && Intrinsics.areEqual(this.h4, typography.h4) && Intrinsics.areEqual(this.h5, typography.h5) && Intrinsics.areEqual(this.h6, typography.h6) && Intrinsics.areEqual(this.subtitle1, typography.subtitle1) && Intrinsics.areEqual(this.subtitle2, typography.subtitle2) && Intrinsics.areEqual(this.body1, typography.body1) && Intrinsics.areEqual(this.body2, typography.body2) && Intrinsics.areEqual(this.button, typography.button) && Intrinsics.areEqual(this.caption, typography.caption) && Intrinsics.areEqual(this.overline, typography.overline);
    }

    public int hashCode() {
        return (((((((((((((((((((((((this.h1.hashCode() * 31) + this.h2.hashCode()) * 31) + this.h3.hashCode()) * 31) + this.h4.hashCode()) * 31) + this.h5.hashCode()) * 31) + this.h6.hashCode()) * 31) + this.subtitle1.hashCode()) * 31) + this.subtitle2.hashCode()) * 31) + this.body1.hashCode()) * 31) + this.body2.hashCode()) * 31) + this.button.hashCode()) * 31) + this.caption.hashCode()) * 31) + this.overline.hashCode();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("Typography(h1=");
        sb.append(this.h1).append(", h2=").append(this.h2).append(", h3=").append(this.h3).append(", h4=").append(this.h4).append(", h5=").append(this.h5).append(", h6=").append(this.h6).append(", subtitle1=").append(this.subtitle1).append(", subtitle2=").append(this.subtitle2).append(", body1=").append(this.body1).append(", body2=").append(this.body2).append(", button=").append(this.button).append(", caption=");
        sb.append(this.caption).append(", overline=").append(this.overline).append(')');
        return sb.toString();
    }
}
