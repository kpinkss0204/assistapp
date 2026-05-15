package androidx.compose.material3;

import androidx.compose.material3.tokens.ColorSchemeKeyTokens;
import androidx.compose.material3.tokens.ShapeKeyTokens;
import androidx.compose.material3.tokens.TypographyKeyTokens;
import androidx.compose.ui.unit.Dp;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.firebase.analytics.FirebaseAnalytics;
import kotlin.Metadata;
import kotlin.math.MathKt;

/* JADX INFO: compiled from: ExpressiveNavigationBar.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000r\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0014\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\u001aW\u0010 \u001a\u00020!2\b\b\u0002\u0010\"\u001a\u00020#2\b\b\u0002\u0010$\u001a\u00020%2\b\b\u0002\u0010&\u001a\u00020%2\b\b\u0002\u0010'\u001a\u00020(2\b\b\u0002\u0010)\u001a\u00020*2\u0011\u0010+\u001a\r\u0012\u0004\u0012\u00020!0,¢\u0006\u0002\b-H\u0001ø\u0001\u0000¢\u0006\u0004\b.\u0010/\u001a\u009d\u0001\u00100\u001a\u00020!2\u0006\u00101\u001a\u0002022\f\u00103\u001a\b\u0012\u0004\u0012\u00020!0,2\u0011\u00104\u001a\r\u0012\u0004\u0012\u00020!0,¢\u0006\u0002\b-2\b\b\u0002\u0010\"\u001a\u00020#2\b\b\u0002\u00105\u001a\u0002022\u0015\b\u0002\u00106\u001a\u000f\u0012\u0004\u0012\u00020!\u0018\u00010,¢\u0006\u0002\b-2\u0015\b\u0002\u00107\u001a\u000f\u0012\u0004\u0012\u00020!\u0018\u00010,¢\u0006\u0002\b-2\b\b\u0002\u00108\u001a\u0002092\b\b\u0002\u0010:\u001a\u00020;2\n\b\u0002\u0010<\u001a\u0004\u0018\u00010=H\u0001ø\u0001\u0000¢\u0006\u0004\b>\u0010?\u001a\u0018\u0010@\u001a\u00020A2\u0006\u0010B\u001a\u00020A2\u0006\u0010C\u001a\u00020AH\u0002\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010\u0005\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"\u0010\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\b\"\u000e\u0010\t\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010\n\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000\"\u0010\u0010\r\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\b\"\u0016\u0010\u000e\u001a\u00020\u0007X\u0080\u0004¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u000f\u0010\u0010\"\u0016\u0010\u0011\u001a\u00020\u0007X\u0080\u0004¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u0012\u0010\u0010\"\u0010\u0010\u0013\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\b\"\u0016\u0010\u0014\u001a\u00020\u0007X\u0080\u0004¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u0015\u0010\u0010\"\u0016\u0010\u0016\u001a\u00020\u0007X\u0080\u0004¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u0017\u0010\u0010\"\u0016\u0010\u0018\u001a\u00020\u0007X\u0080\u0004¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u0019\u0010\u0010\"\u0016\u0010\u001a\u001a\u00020\u0007X\u0080\u0004¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u001b\u0010\u0010\"\u0010\u0010\u001c\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\b\"\u0010\u0010\u001d\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\b\"\u0016\u0010\u001e\u001a\u00020\u0007X\u0080\u0004¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u001f\u0010\u0010\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006D"}, d2 = {"ActiveIconColor", "Landroidx/compose/material3/tokens/ColorSchemeKeyTokens;", "ActiveIndicatorColor", "ActiveIndicatorShape", "Landroidx/compose/material3/tokens/ShapeKeyTokens;", "ActiveLabelTextColor", "IconSize", "Landroidx/compose/ui/unit/Dp;", "F", "InactiveIconColor", "InactiveLabelTextColor", "LabelTextFont", "Landroidx/compose/material3/tokens/TypographyKeyTokens;", "NavigationBarHeight", "StartIconIndicatorHorizontalPadding", "getStartIconIndicatorHorizontalPadding", "()F", "StartIconIndicatorVerticalPadding", "getStartIconIndicatorVerticalPadding", "StartIconItemActiveIndicatorHeight", "StartIconToLabelPadding", "getStartIconToLabelPadding", "TopIconIndicatorHorizontalPadding", "getTopIconIndicatorHorizontalPadding", "TopIconIndicatorToLabelPadding", "getTopIconIndicatorToLabelPadding", "TopIconIndicatorVerticalPadding", "getTopIconIndicatorVerticalPadding", "TopIconItemActiveIndicatorHeight", "TopIconItemActiveIndicatorWidth", "TopIconItemVerticalPadding", "getTopIconItemVerticalPadding", "ExpressiveNavigationBar", "", "modifier", "Landroidx/compose/ui/Modifier;", "containerColor", "Landroidx/compose/ui/graphics/Color;", "contentColor", "windowInsets", "Landroidx/compose/foundation/layout/WindowInsets;", "arrangement", "Landroidx/compose/material3/NavigationBarArrangement;", FirebaseAnalytics.Param.CONTENT, "Lkotlin/Function0;", "Landroidx/compose/runtime/Composable;", "ExpressiveNavigationBar-NiJtXQ4", "(Landroidx/compose/ui/Modifier;JJLandroidx/compose/foundation/layout/WindowInsets;ILkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;II)V", "ExpressiveNavigationBarItem", "selected", "", "onClick", "icon", "enabled", "label", "badge", "iconPosition", "Landroidx/compose/material3/NavigationItemIconPosition;", "colors", "Landroidx/compose/material3/NavigationItemColors;", "interactionSource", "Landroidx/compose/foundation/interaction/MutableInteractionSource;", "ExpressiveNavigationBarItem-pli-t6k", "(ZLkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function2;Landroidx/compose/ui/Modifier;ZLkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;ILandroidx/compose/material3/NavigationItemColors;Landroidx/compose/foundation/interaction/MutableInteractionSource;Landroidx/compose/runtime/Composer;II)V", "calculateCenteredContentHorizontalPadding", "", "itemsCount", "barWidth", "material3_release"}, k = 2, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
public final class ExpressiveNavigationBarKt {
    private static final ColorSchemeKeyTokens ActiveIconColor;
    private static final ColorSchemeKeyTokens ActiveIndicatorColor;
    private static final ShapeKeyTokens ActiveIndicatorShape;
    private static final ColorSchemeKeyTokens ActiveLabelTextColor;
    private static final float IconSize;
    private static final ColorSchemeKeyTokens InactiveIconColor;
    private static final ColorSchemeKeyTokens InactiveLabelTextColor;
    private static final TypographyKeyTokens LabelTextFont;
    private static final float NavigationBarHeight;
    private static final float StartIconIndicatorHorizontalPadding;
    private static final float StartIconIndicatorVerticalPadding;
    private static final float StartIconItemActiveIndicatorHeight;
    private static final float StartIconToLabelPadding;
    private static final float TopIconIndicatorHorizontalPadding;
    private static final float TopIconIndicatorToLabelPadding;
    private static final float TopIconIndicatorVerticalPadding;
    private static final float TopIconItemActiveIndicatorHeight;
    private static final float TopIconItemActiveIndicatorWidth;
    private static final float TopIconItemVerticalPadding;

    /* JADX WARN: Removed duplicated region for block: B:108:0x0143  */
    /* JADX WARN: Removed duplicated region for block: B:111:0x017c  */
    /* JADX INFO: renamed from: ExpressiveNavigationBar-NiJtXQ4, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final void m2398ExpressiveNavigationBarNiJtXQ4(androidx.compose.ui.Modifier r23, long r24, long r26, androidx.compose.foundation.layout.WindowInsets r28, int r29, final kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r30, androidx.compose.runtime.Composer r31, final int r32, final int r33) {
        /*
            Method dump skipped, instruction units count: 407
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.ExpressiveNavigationBarKt.m2398ExpressiveNavigationBarNiJtXQ4(androidx.compose.ui.Modifier, long, long, androidx.compose.foundation.layout.WindowInsets, int, kotlin.jvm.functions.Function2, androidx.compose.runtime.Composer, int, int):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:102:0x011c  */
    /* JADX WARN: Removed duplicated region for block: B:103:0x011f  */
    /* JADX WARN: Removed duplicated region for block: B:113:0x0145  */
    /* JADX WARN: Removed duplicated region for block: B:117:0x015d  */
    /* JADX WARN: Removed duplicated region for block: B:127:0x0199  */
    /* JADX WARN: Removed duplicated region for block: B:128:0x019b  */
    /* JADX WARN: Removed duplicated region for block: B:130:0x01a2  */
    /* JADX WARN: Removed duplicated region for block: B:133:0x01a7  */
    /* JADX WARN: Removed duplicated region for block: B:135:0x01aa  */
    /* JADX WARN: Removed duplicated region for block: B:137:0x01ad  */
    /* JADX WARN: Removed duplicated region for block: B:138:0x01b4  */
    /* JADX WARN: Removed duplicated region for block: B:141:0x01ba  */
    /* JADX WARN: Removed duplicated region for block: B:142:0x01c3  */
    /* JADX WARN: Removed duplicated region for block: B:145:0x01ca  */
    /* JADX WARN: Removed duplicated region for block: B:149:0x01da  */
    /* JADX WARN: Removed duplicated region for block: B:152:0x01f0  */
    /* JADX WARN: Removed duplicated region for block: B:156:0x0213  */
    /* JADX WARN: Removed duplicated region for block: B:159:0x0224  */
    /* JADX WARN: Removed duplicated region for block: B:160:0x0227  */
    /* JADX WARN: Removed duplicated region for block: B:163:0x022d  */
    /* JADX WARN: Removed duplicated region for block: B:164:0x0230  */
    /* JADX WARN: Removed duplicated region for block: B:167:0x027d  */
    /* JADX WARN: Removed duplicated region for block: B:171:0x0293  */
    /* JADX WARN: Removed duplicated region for block: B:173:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0068  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x006b  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x0084  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x0087  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x00a2  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x00a4  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x00be  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x00c0  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x00db  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x00de  */
    /* JADX WARN: Removed duplicated region for block: B:91:0x00fe  */
    /* JADX WARN: Removed duplicated region for block: B:99:0x0114  */
    /* JADX INFO: renamed from: ExpressiveNavigationBarItem-pli-t6k, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final void m2399ExpressiveNavigationBarItemplit6k(final boolean r34, final kotlin.jvm.functions.Function0<kotlin.Unit> r35, final kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r36, androidx.compose.ui.Modifier r37, boolean r38, kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r39, kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r40, int r41, androidx.compose.material3.NavigationItemColors r42, androidx.compose.foundation.interaction.MutableInteractionSource r43, androidx.compose.runtime.Composer r44, final int r45, final int r46) {
        /*
            Method dump skipped, instruction units count: 676
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.ExpressiveNavigationBarKt.m2399ExpressiveNavigationBarItemplit6k(boolean, kotlin.jvm.functions.Function0, kotlin.jvm.functions.Function2, androidx.compose.ui.Modifier, boolean, kotlin.jvm.functions.Function2, kotlin.jvm.functions.Function2, int, androidx.compose.material3.NavigationItemColors, androidx.compose.foundation.interaction.MutableInteractionSource, androidx.compose.runtime.Composer, int, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final int calculateCenteredContentHorizontalPadding(int i, int i2) {
        if (i > 6) {
            return 0;
        }
        return MathKt.roundToInt((((100 - ((i + 3) * 10)) / 2.0f) / 100) * i2);
    }

    public static final float getTopIconItemVerticalPadding() {
        return TopIconItemVerticalPadding;
    }

    public static final float getTopIconIndicatorVerticalPadding() {
        return TopIconIndicatorVerticalPadding;
    }

    public static final float getTopIconIndicatorHorizontalPadding() {
        return TopIconIndicatorHorizontalPadding;
    }

    public static final float getStartIconIndicatorVerticalPadding() {
        return StartIconIndicatorVerticalPadding;
    }

    public static final float getTopIconIndicatorToLabelPadding() {
        return TopIconIndicatorToLabelPadding;
    }

    public static final float getStartIconIndicatorHorizontalPadding() {
        return StartIconIndicatorHorizontalPadding;
    }

    public static final float getStartIconToLabelPadding() {
        return StartIconToLabelPadding;
    }

    static {
        float fM7027constructorimpl = Dp.m7027constructorimpl((float) 24.0d);
        IconSize = fM7027constructorimpl;
        float fM7027constructorimpl2 = Dp.m7027constructorimpl(56);
        TopIconItemActiveIndicatorWidth = fM7027constructorimpl2;
        float fM7027constructorimpl3 = Dp.m7027constructorimpl(32);
        TopIconItemActiveIndicatorHeight = fM7027constructorimpl3;
        float fM7027constructorimpl4 = Dp.m7027constructorimpl(40);
        StartIconItemActiveIndicatorHeight = fM7027constructorimpl4;
        LabelTextFont = TypographyKeyTokens.LabelMedium;
        ActiveIndicatorShape = ShapeKeyTokens.CornerFull;
        ActiveIconColor = ColorSchemeKeyTokens.Secondary;
        ActiveLabelTextColor = ColorSchemeKeyTokens.Secondary;
        ActiveIndicatorColor = ColorSchemeKeyTokens.SecondaryContainer;
        InactiveIconColor = ColorSchemeKeyTokens.OnSurfaceVariant;
        InactiveLabelTextColor = ColorSchemeKeyTokens.OnSurfaceVariant;
        NavigationBarHeight = Dp.m7027constructorimpl(64);
        TopIconItemVerticalPadding = Dp.m7027constructorimpl(6);
        float f = 2;
        TopIconIndicatorVerticalPadding = Dp.m7027constructorimpl(Dp.m7027constructorimpl(fM7027constructorimpl3 - fM7027constructorimpl) / f);
        TopIconIndicatorHorizontalPadding = Dp.m7027constructorimpl(Dp.m7027constructorimpl(fM7027constructorimpl2 - fM7027constructorimpl) / f);
        StartIconIndicatorVerticalPadding = Dp.m7027constructorimpl(Dp.m7027constructorimpl(fM7027constructorimpl4 - fM7027constructorimpl) / f);
        float f2 = 4;
        TopIconIndicatorToLabelPadding = Dp.m7027constructorimpl(f2);
        StartIconIndicatorHorizontalPadding = Dp.m7027constructorimpl(16);
        StartIconToLabelPadding = Dp.m7027constructorimpl(f2);
    }
}
