package androidx.compose.ui.unit;

import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.geometry.SizeKt;
import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Metadata;

/* JADX INFO: compiled from: Density.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\bg\u0018\u00002\u00020\u0001J\u0016\u0010\b\u001a\u00020\t*\u00020\nH\u0017ø\u0001\u0000¢\u0006\u0004\b\u000b\u0010\fJ\u0016\u0010\b\u001a\u00020\t*\u00020\rH\u0017ø\u0001\u0000¢\u0006\u0004\b\u000e\u0010\u000fJ\u0019\u0010\u0010\u001a\u00020\n*\u00020\u0003H\u0017ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b\u0011\u0010\u0012J\u0019\u0010\u0010\u001a\u00020\n*\u00020\tH\u0017ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b\u0011\u0010\u0013J\u0016\u0010\u0014\u001a\u00020\u0015*\u00020\u0016H\u0017ø\u0001\u0000¢\u0006\u0004\b\u0017\u0010\u0018J\u0016\u0010\u0019\u001a\u00020\u0003*\u00020\nH\u0017ø\u0001\u0000¢\u0006\u0004\b\u001a\u0010\u0012J\u0016\u0010\u0019\u001a\u00020\u0003*\u00020\rH\u0017ø\u0001\u0000¢\u0006\u0004\b\u001b\u0010\u001cJ\f\u0010\u001d\u001a\u00020\u001e*\u00020\u001fH\u0017J\u0016\u0010 \u001a\u00020\u0016*\u00020\u0015H\u0017ø\u0001\u0000¢\u0006\u0004\b!\u0010\u0018J\u0019\u0010\"\u001a\u00020\r*\u00020\u0003H\u0017ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b#\u0010$J\u0019\u0010\"\u001a\u00020\r*\u00020\tH\u0017ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b#\u0010%R\u001a\u0010\u0002\u001a\u00020\u00038&X§\u0004¢\u0006\f\u0012\u0004\b\u0004\u0010\u0005\u001a\u0004\b\u0006\u0010\u0007ø\u0001\u0002\u0082\u0002\u0011\n\u0005\b¡\u001e0\u0001\n\u0002\b!\n\u0004\b!0\u0001¨\u0006&À\u0006\u0003"}, d2 = {"Landroidx/compose/ui/unit/Density;", "Landroidx/compose/ui/unit/FontScaling;", "density", "", "getDensity$annotations", "()V", "getDensity", "()F", "roundToPx", "", "Landroidx/compose/ui/unit/Dp;", "roundToPx-0680j_4", "(F)I", "Landroidx/compose/ui/unit/TextUnit;", "roundToPx--R2X_6o", "(J)I", "toDp", "toDp-u2uoSUM", "(F)F", "(I)F", "toDpSize", "Landroidx/compose/ui/unit/DpSize;", "Landroidx/compose/ui/geometry/Size;", "toDpSize-k-rfVVM", "(J)J", "toPx", "toPx-0680j_4", "toPx--R2X_6o", "(J)F", "toRect", "Landroidx/compose/ui/geometry/Rect;", "Landroidx/compose/ui/unit/DpRect;", "toSize", "toSize-XkaWNTQ", "toSp", "toSp-kPz2Gy4", "(F)J", "(I)J", "ui-unit_release"}, k = 1, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
public interface Density extends FontScaling {
    float getDensity();

    /* JADX INFO: compiled from: Density.kt */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class DefaultImpls {
        public static /* synthetic */ void getDensity$annotations() {
        }

        @Deprecated
        /* JADX INFO: renamed from: toDp-GaN1DYA, reason: not valid java name */
        public static float m7015toDpGaN1DYA(Density density, long j) {
            return Density.super.mo652toDpGaN1DYA(j);
        }

        @Deprecated
        /* JADX INFO: renamed from: toSp-0xMU5do, reason: not valid java name */
        public static long m7022toSp0xMU5do(Density density, float f) {
            return Density.super.mo659toSp0xMU5do(f);
        }

        @Deprecated
        /* JADX INFO: renamed from: toPx-0680j_4, reason: not valid java name */
        public static float m7020toPx0680j_4(Density density, float f) {
            return Density.super.mo657toPx0680j_4(f);
        }

        @Deprecated
        /* JADX INFO: renamed from: roundToPx-0680j_4, reason: not valid java name */
        public static int m7014roundToPx0680j_4(Density density, float f) {
            return Density.super.mo651roundToPx0680j_4(f);
        }

        @Deprecated
        /* JADX INFO: renamed from: toPx--R2X_6o, reason: not valid java name */
        public static float m7019toPxR2X_6o(Density density, long j) {
            return Density.super.mo656toPxR2X_6o(j);
        }

        @Deprecated
        /* JADX INFO: renamed from: roundToPx--R2X_6o, reason: not valid java name */
        public static int m7013roundToPxR2X_6o(Density density, long j) {
            return Density.super.mo650roundToPxR2X_6o(j);
        }

        @Deprecated
        /* JADX INFO: renamed from: toDp-u2uoSUM, reason: not valid java name */
        public static float m7017toDpu2uoSUM(Density density, int i) {
            return Density.super.mo654toDpu2uoSUM(i);
        }

        @Deprecated
        /* JADX INFO: renamed from: toSp-kPz2Gy4, reason: not valid java name */
        public static long m7024toSpkPz2Gy4(Density density, int i) {
            return Density.super.mo661toSpkPz2Gy4(i);
        }

        @Deprecated
        /* JADX INFO: renamed from: toDp-u2uoSUM, reason: not valid java name */
        public static float m7016toDpu2uoSUM(Density density, float f) {
            return Density.super.mo653toDpu2uoSUM(f);
        }

        @Deprecated
        /* JADX INFO: renamed from: toSp-kPz2Gy4, reason: not valid java name */
        public static long m7023toSpkPz2Gy4(Density density, float f) {
            return Density.super.mo660toSpkPz2Gy4(f);
        }

        @Deprecated
        public static Rect toRect(Density density, DpRect dpRect) {
            return Density.super.toRect(dpRect);
        }

        @Deprecated
        /* JADX INFO: renamed from: toSize-XkaWNTQ, reason: not valid java name */
        public static long m7021toSizeXkaWNTQ(Density density, long j) {
            return Density.super.mo658toSizeXkaWNTQ(j);
        }

        @Deprecated
        /* JADX INFO: renamed from: toDpSize-k-rfVVM, reason: not valid java name */
        public static long m7018toDpSizekrfVVM(Density density, long j) {
            return Density.super.mo655toDpSizekrfVVM(j);
        }
    }

    /* JADX INFO: renamed from: toPx-0680j_4 */
    default float mo657toPx0680j_4(float f) {
        return f * getDensity();
    }

    /* JADX INFO: renamed from: roundToPx-0680j_4 */
    default int mo651roundToPx0680j_4(float f) {
        float fMo657toPx0680j_4 = mo657toPx0680j_4(f);
        if (Float.isInfinite(fMo657toPx0680j_4)) {
            return Integer.MAX_VALUE;
        }
        return Math.round(fMo657toPx0680j_4);
    }

    /* JADX INFO: renamed from: toPx--R2X_6o */
    default float mo656toPxR2X_6o(long j) {
        if (!TextUnitType.m7248equalsimpl0(TextUnit.m7219getTypeUIouoOA(j), TextUnitType.INSTANCE.m7253getSpUIouoOA())) {
            throw new IllegalStateException("Only Sp can convert to Px".toString());
        }
        return mo657toPx0680j_4(mo652toDpGaN1DYA(j));
    }

    /* JADX INFO: renamed from: roundToPx--R2X_6o */
    default int mo650roundToPxR2X_6o(long j) {
        return Math.round(mo656toPxR2X_6o(j));
    }

    /* JADX INFO: renamed from: toDp-u2uoSUM */
    default float mo654toDpu2uoSUM(int i) {
        return Dp.m7027constructorimpl(i / getDensity());
    }

    /* JADX INFO: renamed from: toSp-kPz2Gy4 */
    default long mo661toSpkPz2Gy4(int i) {
        return mo659toSp0xMU5do(mo654toDpu2uoSUM(i));
    }

    /* JADX INFO: renamed from: toDp-u2uoSUM */
    default float mo653toDpu2uoSUM(float f) {
        return Dp.m7027constructorimpl(f / getDensity());
    }

    /* JADX INFO: renamed from: toSp-kPz2Gy4 */
    default long mo660toSpkPz2Gy4(float f) {
        return mo659toSp0xMU5do(mo653toDpu2uoSUM(f));
    }

    default Rect toRect(DpRect dpRect) {
        return new Rect(mo657toPx0680j_4(dpRect.m7110getLeftD9Ej5fM()), mo657toPx0680j_4(dpRect.m7112getTopD9Ej5fM()), mo657toPx0680j_4(dpRect.m7111getRightD9Ej5fM()), mo657toPx0680j_4(dpRect.m7109getBottomD9Ej5fM()));
    }

    /* JADX INFO: renamed from: toSize-XkaWNTQ */
    default long mo658toSizeXkaWNTQ(long j) {
        if (j != androidx.compose.ui.geometry.InlineClassHelperKt.UnspecifiedPackedFloats) {
            return SizeKt.Size(mo657toPx0680j_4(DpSize.m7125getWidthD9Ej5fM(j)), mo657toPx0680j_4(DpSize.m7123getHeightD9Ej5fM(j)));
        }
        return Size.INSTANCE.m4404getUnspecifiedNHjbRc();
    }

    /* JADX INFO: renamed from: toDpSize-k-rfVVM */
    default long mo655toDpSizekrfVVM(long j) {
        if (j != androidx.compose.ui.geometry.InlineClassHelperKt.UnspecifiedPackedFloats) {
            return DpKt.m7049DpSizeYgX7TsA(mo653toDpu2uoSUM(Size.m4396getWidthimpl(j)), mo653toDpu2uoSUM(Size.m4393getHeightimpl(j)));
        }
        return DpSize.INSTANCE.m7134getUnspecifiedMYxV2XQ();
    }
}
