package androidx.compose.material.ripple;

import androidx.collection.MutableScatterMap;
import androidx.compose.foundation.interaction.InteractionSource;
import androidx.compose.foundation.interaction.PressInteraction;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.ColorProducer;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: CommonRipple.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\b\u0000\u0018\u00002\u00020\u0001B3\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000b¢\u0006\u0002\u0010\rJ*\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00102\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0018H\u0016ø\u0001\u0000¢\u0006\u0004\b\u0019\u0010\u001aJ\b\u0010\u001b\u001a\u00020\u0013H\u0016J\u0010\u0010\u001c\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0010H\u0016J\f\u0010\u001d\u001a\u00020\u0013*\u00020\u001eH\u0016R\u001a\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u00110\u000fX\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006\u001f"}, d2 = {"Landroidx/compose/material/ripple/CommonRippleNode;", "Landroidx/compose/material/ripple/RippleNode;", "interactionSource", "Landroidx/compose/foundation/interaction/InteractionSource;", "bounded", "", "radius", "Landroidx/compose/ui/unit/Dp;", TypedValues.Custom.S_COLOR, "Landroidx/compose/ui/graphics/ColorProducer;", "rippleAlpha", "Lkotlin/Function0;", "Landroidx/compose/material/ripple/RippleAlpha;", "(Landroidx/compose/foundation/interaction/InteractionSource;ZFLandroidx/compose/ui/graphics/ColorProducer;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/internal/DefaultConstructorMarker;)V", "ripples", "Landroidx/collection/MutableScatterMap;", "Landroidx/compose/foundation/interaction/PressInteraction$Press;", "Landroidx/compose/material/ripple/RippleAnimation;", "addRipple", "", "interaction", "size", "Landroidx/compose/ui/geometry/Size;", "targetRadius", "", "addRipple-12SF9DM", "(Landroidx/compose/foundation/interaction/PressInteraction$Press;JF)V", "onDetach", "removeRipple", "drawRipples", "Landroidx/compose/ui/graphics/drawscope/DrawScope;", "material-ripple_release"}, k = 1, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
public final class CommonRippleNode extends RippleNode {
    public static final int $stable = 8;
    private final MutableScatterMap<PressInteraction.Press, RippleAnimation> ripples;

    public /* synthetic */ CommonRippleNode(InteractionSource interactionSource, boolean z, float f, ColorProducer colorProducer, Function0 function0, DefaultConstructorMarker defaultConstructorMarker) {
        this(interactionSource, z, f, colorProducer, function0);
    }

    private CommonRippleNode(InteractionSource interactionSource, boolean z, float f, ColorProducer colorProducer, Function0<RippleAlpha> function0) {
        super(interactionSource, z, f, colorProducer, function0, null);
        this.ripples = new MutableScatterMap<>(0, 1, null);
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x004e  */
    @Override // androidx.compose.material.ripple.RippleNode
    /* JADX INFO: renamed from: addRipple-12SF9DM */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void mo2038addRipple12SF9DM(androidx.compose.foundation.interaction.PressInteraction.Press r18, long r19, float r21) {
        /*
            r17 = this;
            r0 = r17
            r1 = r18
            androidx.collection.MutableScatterMap<androidx.compose.foundation.interaction.PressInteraction$Press, androidx.compose.material.ripple.RippleAnimation> r2 = r0.ripples
            androidx.collection.ScatterMap r2 = (androidx.collection.ScatterMap) r2
            java.lang.Object[] r3 = r2.keys
            java.lang.Object[] r4 = r2.values
            long[] r2 = r2.metadata
            int r5 = r2.length
            int r5 = r5 + (-2)
            if (r5 < 0) goto L53
            r6 = 0
            r7 = r6
        L15:
            r8 = r2[r7]
            long r10 = ~r8
            r12 = 7
            long r10 = r10 << r12
            long r10 = r10 & r8
            r12 = -9187201950435737472(0x8080808080808080, double:-2.937446524422997E-306)
            long r10 = r10 & r12
            int r10 = (r10 > r12 ? 1 : (r10 == r12 ? 0 : -1))
            if (r10 == 0) goto L4e
            int r10 = r7 - r5
            int r10 = ~r10
            int r10 = r10 >>> 31
            r11 = 8
            int r10 = 8 - r10
            r12 = r6
        L2f:
            if (r12 >= r10) goto L4c
            r13 = 255(0xff, double:1.26E-321)
            long r13 = r13 & r8
            r15 = 128(0x80, double:6.3E-322)
            int r13 = (r13 > r15 ? 1 : (r13 == r15 ? 0 : -1))
            if (r13 >= 0) goto L48
            int r13 = r7 << 3
            int r13 = r13 + r12
            r14 = r3[r13]
            r13 = r4[r13]
            androidx.compose.material.ripple.RippleAnimation r13 = (androidx.compose.material.ripple.RippleAnimation) r13
            androidx.compose.foundation.interaction.PressInteraction$Press r14 = (androidx.compose.foundation.interaction.PressInteraction.Press) r14
            r13.finish()
        L48:
            long r8 = r8 >> r11
            int r12 = r12 + 1
            goto L2f
        L4c:
            if (r10 != r11) goto L53
        L4e:
            if (r7 == r5) goto L53
            int r7 = r7 + 1
            goto L15
        L53:
            boolean r2 = r0.getBounded()
            r3 = 0
            if (r2 == 0) goto L63
            long r4 = r1.getPressPosition()
            androidx.compose.ui.geometry.Offset r2 = androidx.compose.ui.geometry.Offset.m4316boximpl(r4)
            goto L64
        L63:
            r2 = r3
        L64:
            androidx.compose.material.ripple.RippleAnimation r4 = new androidx.compose.material.ripple.RippleAnimation
            boolean r5 = r0.getBounded()
            r6 = r21
            r4.<init>(r2, r6, r5, r3)
            androidx.collection.MutableScatterMap<androidx.compose.foundation.interaction.PressInteraction$Press, androidx.compose.material.ripple.RippleAnimation> r2 = r0.ripples
            r2.set(r1, r4)
            kotlinx.coroutines.CoroutineScope r5 = r0.getCoroutineScope()
            androidx.compose.material.ripple.CommonRippleNode$addRipple$2 r2 = new androidx.compose.material.ripple.CommonRippleNode$addRipple$2
            r2.<init>(r4, r0, r1, r3)
            r8 = r2
            kotlin.jvm.functions.Function2 r8 = (kotlin.jvm.functions.Function2) r8
            r9 = 3
            r10 = 0
            r6 = 0
            r7 = 0
            kotlinx.coroutines.BuildersKt.launch$default(r5, r6, r7, r8, r9, r10)
            r1 = r0
            androidx.compose.ui.node.DrawModifierNode r1 = (androidx.compose.ui.node.DrawModifierNode) r1
            androidx.compose.ui.node.DrawModifierNodeKt.invalidateDraw(r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material.ripple.CommonRippleNode.mo2038addRipple12SF9DM(androidx.compose.foundation.interaction.PressInteraction$Press, long, float):void");
    }

    @Override // androidx.compose.material.ripple.RippleNode
    public void removeRipple(PressInteraction.Press interaction) {
        RippleAnimation rippleAnimation = this.ripples.get(interaction);
        if (rippleAnimation != null) {
            rippleAnimation.finish();
        }
    }

    @Override // androidx.compose.material.ripple.RippleNode
    public void drawRipples(DrawScope drawScope) {
        float f;
        int i;
        int i2;
        int i3;
        float f2;
        float pressedAlpha = getRippleAlpha().invoke().getPressedAlpha();
        if (pressedAlpha == 0.0f) {
            return;
        }
        MutableScatterMap<PressInteraction.Press, RippleAnimation> mutableScatterMap = this.ripples;
        Object[] objArr = mutableScatterMap.keys;
        Object[] objArr2 = mutableScatterMap.values;
        long[] jArr = mutableScatterMap.metadata;
        int length = jArr.length - 2;
        if (length < 0) {
            return;
        }
        int i4 = 0;
        while (true) {
            long j = jArr[i4];
            if ((((~j) << 7) & j & (-9187201950435737472L)) != -9187201950435737472L) {
                int i5 = 8;
                int i6 = 8 - ((~(i4 - length)) >>> 31);
                long j2 = j;
                int i7 = 0;
                while (i7 < i6) {
                    if ((j2 & 255) < 128) {
                        int i8 = (i4 << 3) + i7;
                        i = i7;
                        i2 = i6;
                        i3 = i5;
                        f2 = pressedAlpha;
                        ((RippleAnimation) objArr2[i8]).m2041draw4WTKRHQ(drawScope, Color.m4567copywmQWz5c$default(m2050getRippleColor0d7_KjU(), pressedAlpha, 0.0f, 0.0f, 0.0f, 14, null));
                    } else {
                        i = i7;
                        i2 = i6;
                        i3 = i5;
                        f2 = pressedAlpha;
                    }
                    j2 >>= i3;
                    i7 = i + 1;
                    pressedAlpha = f2;
                    i6 = i2;
                    i5 = i3;
                }
                int i9 = i6;
                f = pressedAlpha;
                if (i9 != i5) {
                    return;
                }
            } else {
                f = pressedAlpha;
            }
            if (i4 == length) {
                return;
            }
            i4++;
            pressedAlpha = f;
        }
    }

    @Override // androidx.compose.ui.Modifier.Node
    public void onDetach() {
        this.ripples.clear();
    }
}
