package androidx.compose.material;

import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalKt;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.ColorKt;
import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;

/* JADX INFO: compiled from: Colors.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u001a\n\u0002\u0010\u0002\n\u0002\b\u0002\u001a\u001a\u0010\t\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\u0006H\u0007ø\u0001\u0000¢\u0006\u0004\b\u000b\u0010\f\u001a\u0088\u0001\u0010\r\u001a\u00020\u00022\b\b\u0002\u0010\u000e\u001a\u00020\u00062\b\b\u0002\u0010\u000f\u001a\u00020\u00062\b\b\u0002\u0010\u0010\u001a\u00020\u00062\b\b\u0002\u0010\u0011\u001a\u00020\u00062\b\b\u0002\u0010\u0012\u001a\u00020\u00062\b\b\u0002\u0010\u0013\u001a\u00020\u00062\b\b\u0002\u0010\u0014\u001a\u00020\u00062\b\b\u0002\u0010\u0015\u001a\u00020\u00062\b\b\u0002\u0010\u0016\u001a\u00020\u00062\b\b\u0002\u0010\u0017\u001a\u00020\u00062\b\b\u0002\u0010\u0018\u001a\u00020\u00062\b\b\u0002\u0010\u0019\u001a\u00020\u0006ø\u0001\u0000¢\u0006\u0004\b\u001a\u0010\u001b\u001a\u0088\u0001\u0010\u001c\u001a\u00020\u00022\b\b\u0002\u0010\u000e\u001a\u00020\u00062\b\b\u0002\u0010\u000f\u001a\u00020\u00062\b\b\u0002\u0010\u0010\u001a\u00020\u00062\b\b\u0002\u0010\u0011\u001a\u00020\u00062\b\b\u0002\u0010\u0012\u001a\u00020\u00062\b\b\u0002\u0010\u0013\u001a\u00020\u00062\b\b\u0002\u0010\u0014\u001a\u00020\u00062\b\b\u0002\u0010\u0015\u001a\u00020\u00062\b\b\u0002\u0010\u0016\u001a\u00020\u00062\b\b\u0002\u0010\u0017\u001a\u00020\u00062\b\b\u0002\u0010\u0018\u001a\u00020\u00062\b\b\u0002\u0010\u0019\u001a\u00020\u0006ø\u0001\u0000¢\u0006\u0004\b\u001d\u0010\u001b\u001a\u001c\u0010\t\u001a\u00020\u0006*\u00020\u00022\u0006\u0010\n\u001a\u00020\u0006ø\u0001\u0000¢\u0006\u0004\b\u001e\u0010\u001f\u001a\u0014\u0010 \u001a\u00020!*\u00020\u00022\u0006\u0010\"\u001a\u00020\u0002H\u0000\"\u001a\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0003\u0010\u0004\"\u0015\u0010\u0005\u001a\u00020\u0006*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\u0007\u0010\b\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006#"}, d2 = {"LocalColors", "Landroidx/compose/runtime/ProvidableCompositionLocal;", "Landroidx/compose/material/Colors;", "getLocalColors", "()Landroidx/compose/runtime/ProvidableCompositionLocal;", "primarySurface", "Landroidx/compose/ui/graphics/Color;", "getPrimarySurface", "(Landroidx/compose/material/Colors;)J", "contentColorFor", "backgroundColor", "contentColorFor-ek8zF_U", "(JLandroidx/compose/runtime/Composer;I)J", "darkColors", "primary", "primaryVariant", "secondary", "secondaryVariant", "background", "surface", "error", "onPrimary", "onSecondary", "onBackground", "onSurface", "onError", "darkColors-2qZNXz8", "(JJJJJJJJJJJJ)Landroidx/compose/material/Colors;", "lightColors", "lightColors-2qZNXz8", "contentColorFor-4WTKRHQ", "(Landroidx/compose/material/Colors;J)J", "updateColorsFrom", "", "other", "material_release"}, k = 2, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
public final class ColorsKt {
    private static final ProvidableCompositionLocal<Colors> LocalColors = CompositionLocalKt.staticCompositionLocalOf(new Function0<Colors>() { // from class: androidx.compose.material.ColorsKt$LocalColors$1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final Colors invoke() {
            return ColorsKt.m1799lightColors2qZNXz8$default(0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 4095, null);
        }
    });

    /* JADX INFO: renamed from: lightColors-2qZNXz8$default, reason: not valid java name */
    public static /* synthetic */ Colors m1799lightColors2qZNXz8$default(long j, long j2, long j3, long j4, long j5, long j6, long j7, long j8, long j9, long j10, long j11, long j12, int i, Object obj) {
        long jColor = (i & 1) != 0 ? ColorKt.Color(4284612846L) : j;
        long jColor2 = (i & 2) != 0 ? ColorKt.Color(4281794739L) : j2;
        long jColor3 = (i & 4) != 0 ? ColorKt.Color(4278442694L) : j3;
        long jColor4 = (i & 8) != 0 ? ColorKt.Color(4278290310L) : j4;
        long jM4605getWhite0d7_KjU = (i & 16) != 0 ? Color.INSTANCE.m4605getWhite0d7_KjU() : j5;
        long jM4605getWhite0d7_KjU2 = (i & 32) != 0 ? Color.INSTANCE.m4605getWhite0d7_KjU() : j6;
        long jColor5 = (i & 64) != 0 ? ColorKt.Color(4289724448L) : j7;
        long jM4605getWhite0d7_KjU3 = (i & 128) != 0 ? Color.INSTANCE.m4605getWhite0d7_KjU() : j8;
        long j13 = jColor;
        long jM4594getBlack0d7_KjU = (i & 256) != 0 ? Color.INSTANCE.m4594getBlack0d7_KjU() : j9;
        long jM4594getBlack0d7_KjU2 = (i & 512) != 0 ? Color.INSTANCE.m4594getBlack0d7_KjU() : j10;
        long jM4594getBlack0d7_KjU3 = (i & 1024) != 0 ? Color.INSTANCE.m4594getBlack0d7_KjU() : j11;
        if ((i & 2048) != 0) {
            j12 = Color.INSTANCE.m4605getWhite0d7_KjU();
        }
        return m1798lightColors2qZNXz8(j13, jColor2, jColor3, jColor4, jM4605getWhite0d7_KjU, jM4605getWhite0d7_KjU2, jColor5, jM4605getWhite0d7_KjU3, jM4594getBlack0d7_KjU, jM4594getBlack0d7_KjU2, jM4594getBlack0d7_KjU3, j12);
    }

    /* JADX INFO: renamed from: lightColors-2qZNXz8, reason: not valid java name */
    public static final Colors m1798lightColors2qZNXz8(long j, long j2, long j3, long j4, long j5, long j6, long j7, long j8, long j9, long j10, long j11, long j12) {
        return new Colors(j, j2, j3, j4, j5, j6, j7, j8, j9, j10, j11, j12, true, null);
    }

    /* JADX INFO: renamed from: darkColors-2qZNXz8$default, reason: not valid java name */
    public static /* synthetic */ Colors m1797darkColors2qZNXz8$default(long j, long j2, long j3, long j4, long j5, long j6, long j7, long j8, long j9, long j10, long j11, long j12, int i, Object obj) {
        long jColor = (i & 1) != 0 ? ColorKt.Color(4290479868L) : j;
        long jColor2 = (i & 2) != 0 ? ColorKt.Color(4281794739L) : j2;
        long jColor3 = (i & 4) != 0 ? ColorKt.Color(4278442694L) : j3;
        long j13 = (i & 8) != 0 ? jColor3 : j4;
        long jColor4 = (i & 16) != 0 ? ColorKt.Color(4279374354L) : j5;
        long jColor5 = (i & 32) != 0 ? ColorKt.Color(4279374354L) : j6;
        long jColor6 = (i & 64) != 0 ? ColorKt.Color(4291782265L) : j7;
        long jM4594getBlack0d7_KjU = (i & 128) != 0 ? Color.INSTANCE.m4594getBlack0d7_KjU() : j8;
        long jM4594getBlack0d7_KjU2 = (i & 256) != 0 ? Color.INSTANCE.m4594getBlack0d7_KjU() : j9;
        long jM4605getWhite0d7_KjU = (i & 512) != 0 ? Color.INSTANCE.m4605getWhite0d7_KjU() : j10;
        long jM4605getWhite0d7_KjU2 = (i & 1024) != 0 ? Color.INSTANCE.m4605getWhite0d7_KjU() : j11;
        if ((i & 2048) != 0) {
            j12 = Color.INSTANCE.m4594getBlack0d7_KjU();
        }
        return m1796darkColors2qZNXz8(jColor, jColor2, jColor3, j13, jColor4, jColor5, jColor6, jM4594getBlack0d7_KjU, jM4594getBlack0d7_KjU2, jM4605getWhite0d7_KjU, jM4605getWhite0d7_KjU2, j12);
    }

    /* JADX INFO: renamed from: darkColors-2qZNXz8, reason: not valid java name */
    public static final Colors m1796darkColors2qZNXz8(long j, long j2, long j3, long j4, long j5, long j6, long j7, long j8, long j9, long j10, long j11, long j12) {
        return new Colors(j, j2, j3, j4, j5, j6, j7, j8, j9, j10, j11, j12, false, null);
    }

    public static final long getPrimarySurface(Colors colors) {
        return colors.isLight() ? colors.m1777getPrimary0d7_KjU() : colors.m1781getSurface0d7_KjU();
    }

    /* JADX INFO: renamed from: contentColorFor-4WTKRHQ, reason: not valid java name */
    public static final long m1794contentColorFor4WTKRHQ(Colors colors, long j) {
        if (!Color.m4569equalsimpl0(j, colors.m1777getPrimary0d7_KjU()) && !Color.m4569equalsimpl0(j, colors.m1778getPrimaryVariant0d7_KjU())) {
            if (!Color.m4569equalsimpl0(j, colors.m1779getSecondary0d7_KjU()) && !Color.m4569equalsimpl0(j, colors.m1780getSecondaryVariant0d7_KjU())) {
                return Color.m4569equalsimpl0(j, colors.m1770getBackground0d7_KjU()) ? colors.m1772getOnBackground0d7_KjU() : Color.m4569equalsimpl0(j, colors.m1781getSurface0d7_KjU()) ? colors.m1776getOnSurface0d7_KjU() : Color.m4569equalsimpl0(j, colors.m1771getError0d7_KjU()) ? colors.m1773getOnError0d7_KjU() : Color.INSTANCE.m4604getUnspecified0d7_KjU();
            }
            return colors.m1775getOnSecondary0d7_KjU();
        }
        return colors.m1774getOnPrimary0d7_KjU();
    }

    /* JADX INFO: renamed from: contentColorFor-ek8zF_U, reason: not valid java name */
    public static final long m1795contentColorForek8zF_U(long j, Composer composer, int i) {
        ComposerKt.sourceInformationMarkerStart(composer, 441849991, "C(contentColorFor)P(0:c#ui.graphics.Color):Colors.kt#jmzs0o");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(441849991, i, -1, "androidx.compose.material.contentColorFor (Colors.kt:296)");
        }
        composer.startReplaceGroup(-702395103);
        ComposerKt.sourceInformation(composer, "*296@11462L6,296@11533L7");
        long jM1794contentColorFor4WTKRHQ = m1794contentColorFor4WTKRHQ(MaterialTheme.INSTANCE.getColors(composer, 6), j);
        if (jM1794contentColorFor4WTKRHQ == 16) {
            ProvidableCompositionLocal<Color> localContentColor = ContentColorKt.getLocalContentColor();
            ComposerKt.sourceInformationMarkerStart(composer, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object objConsume = composer.consume(localContentColor);
            ComposerKt.sourceInformationMarkerEnd(composer);
            jM1794contentColorFor4WTKRHQ = ((Color) objConsume).m4578unboximpl();
        }
        composer.endReplaceGroup();
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd(composer);
        return jM1794contentColorFor4WTKRHQ;
    }

    public static final void updateColorsFrom(Colors colors, Colors colors2) {
        colors.m1789setPrimary8_81llA$material_release(colors2.m1777getPrimary0d7_KjU());
        colors.m1790setPrimaryVariant8_81llA$material_release(colors2.m1778getPrimaryVariant0d7_KjU());
        colors.m1791setSecondary8_81llA$material_release(colors2.m1779getSecondary0d7_KjU());
        colors.m1792setSecondaryVariant8_81llA$material_release(colors2.m1780getSecondaryVariant0d7_KjU());
        colors.m1782setBackground8_81llA$material_release(colors2.m1770getBackground0d7_KjU());
        colors.m1793setSurface8_81llA$material_release(colors2.m1781getSurface0d7_KjU());
        colors.m1783setError8_81llA$material_release(colors2.m1771getError0d7_KjU());
        colors.m1786setOnPrimary8_81llA$material_release(colors2.m1774getOnPrimary0d7_KjU());
        colors.m1787setOnSecondary8_81llA$material_release(colors2.m1775getOnSecondary0d7_KjU());
        colors.m1784setOnBackground8_81llA$material_release(colors2.m1772getOnBackground0d7_KjU());
        colors.m1788setOnSurface8_81llA$material_release(colors2.m1776getOnSurface0d7_KjU());
        colors.m1785setOnError8_81llA$material_release(colors2.m1773getOnError0d7_KjU());
        colors.setLight$material_release(colors2.isLight());
    }

    public static final ProvidableCompositionLocal<Colors> getLocalColors() {
        return LocalColors;
    }
}
