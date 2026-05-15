package androidx.compose.material3;

import androidx.compose.animation.core.Animatable;
import androidx.compose.animation.core.AnimationVector1D;
import androidx.compose.foundation.interaction.Interaction;
import androidx.compose.ui.unit.Dp;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.datastore.preferences.protobuf.DescriptorProtos;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: Button.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
@DebugMetadata(c = "androidx.compose.material3.ButtonElevation$animateElevation$2$1", f = "Button.kt", i = {}, l = {989, DescriptorProtos.Edition.EDITION_PROTO2_VALUE}, m = "invokeSuspend", n = {}, s = {})
final class ButtonElevation$animateElevation$2$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Animatable<Dp, AnimationVector1D> $animatable;
    final /* synthetic */ boolean $enabled;
    final /* synthetic */ Interaction $interaction;
    final /* synthetic */ float $target;
    int label;
    final /* synthetic */ ButtonElevation this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    ButtonElevation$animateElevation$2$1(Animatable<Dp, AnimationVector1D> animatable, float f, boolean z, ButtonElevation buttonElevation, Interaction interaction, Continuation<? super ButtonElevation$animateElevation$2$1> continuation) {
        super(2, continuation);
        this.$animatable = animatable;
        this.$target = f;
        this.$enabled = z;
        this.this$0 = buttonElevation;
        this.$interaction = interaction;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new ButtonElevation$animateElevation$2$1(this.$animatable, this.$target, this.$enabled, this.this$0, this.$interaction, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((ButtonElevation$animateElevation$2$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Code restructure failed: missing block: B:15:0x0048, code lost:
    
        if (r6.$animatable.snapTo(androidx.compose.ui.unit.Dp.m7025boximpl(r6.$target), r6) == r0) goto L28;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x00ab, code lost:
    
        if (androidx.compose.material3.internal.ElevationKt.m3204animateElevationrAjV9yQ(r6.$animatable, r6.$target, r3, r6.$interaction, r6) == r0) goto L28;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x00ad, code lost:
    
        return r0;
     */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r7) {
        /*
            r6 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r6.label
            r2 = 2
            r3 = 1
            if (r1 == 0) goto L1c
            if (r1 == r3) goto L17
            if (r1 != r2) goto Lf
            goto L17
        Lf:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r0)
            throw r7
        L17:
            kotlin.ResultKt.throwOnFailure(r7)
            goto Lae
        L1c:
            kotlin.ResultKt.throwOnFailure(r7)
            androidx.compose.animation.core.Animatable<androidx.compose.ui.unit.Dp, androidx.compose.animation.core.AnimationVector1D> r7 = r6.$animatable
            java.lang.Object r7 = r7.getTargetValue()
            androidx.compose.ui.unit.Dp r7 = (androidx.compose.ui.unit.Dp) r7
            float r7 = r7.m7041unboximpl()
            float r1 = r6.$target
            boolean r7 = androidx.compose.ui.unit.Dp.m7032equalsimpl0(r7, r1)
            if (r7 != 0) goto Lae
            boolean r7 = r6.$enabled
            if (r7 != 0) goto L4b
            androidx.compose.animation.core.Animatable<androidx.compose.ui.unit.Dp, androidx.compose.animation.core.AnimationVector1D> r7 = r6.$animatable
            float r1 = r6.$target
            androidx.compose.ui.unit.Dp r1 = androidx.compose.ui.unit.Dp.m7025boximpl(r1)
            r2 = r6
            kotlin.coroutines.Continuation r2 = (kotlin.coroutines.Continuation) r2
            r6.label = r3
            java.lang.Object r7 = r7.snapTo(r1, r2)
            if (r7 != r0) goto Lae
            goto Lad
        L4b:
            androidx.compose.animation.core.Animatable<androidx.compose.ui.unit.Dp, androidx.compose.animation.core.AnimationVector1D> r7 = r6.$animatable
            java.lang.Object r7 = r7.getTargetValue()
            androidx.compose.ui.unit.Dp r7 = (androidx.compose.ui.unit.Dp) r7
            float r7 = r7.m7041unboximpl()
            androidx.compose.material3.ButtonElevation r1 = r6.this$0
            float r1 = androidx.compose.material3.ButtonElevation.access$getPressedElevation$p(r1)
            boolean r1 = androidx.compose.ui.unit.Dp.m7032equalsimpl0(r7, r1)
            r3 = 0
            if (r1 == 0) goto L73
            androidx.compose.foundation.interaction.PressInteraction$Press r7 = new androidx.compose.foundation.interaction.PressInteraction$Press
            androidx.compose.ui.geometry.Offset$Companion r1 = androidx.compose.ui.geometry.Offset.INSTANCE
            long r4 = r1.m4343getZeroF1C5BW0()
            r7.<init>(r4, r3)
            r3 = r7
            androidx.compose.foundation.interaction.Interaction r3 = (androidx.compose.foundation.interaction.Interaction) r3
            goto L9c
        L73:
            androidx.compose.material3.ButtonElevation r1 = r6.this$0
            float r1 = androidx.compose.material3.ButtonElevation.access$getHoveredElevation$p(r1)
            boolean r1 = androidx.compose.ui.unit.Dp.m7032equalsimpl0(r7, r1)
            if (r1 == 0) goto L88
            androidx.compose.foundation.interaction.HoverInteraction$Enter r7 = new androidx.compose.foundation.interaction.HoverInteraction$Enter
            r7.<init>()
            r3 = r7
            androidx.compose.foundation.interaction.Interaction r3 = (androidx.compose.foundation.interaction.Interaction) r3
            goto L9c
        L88:
            androidx.compose.material3.ButtonElevation r1 = r6.this$0
            float r1 = androidx.compose.material3.ButtonElevation.access$getFocusedElevation$p(r1)
            boolean r7 = androidx.compose.ui.unit.Dp.m7032equalsimpl0(r7, r1)
            if (r7 == 0) goto L9c
            androidx.compose.foundation.interaction.FocusInteraction$Focus r7 = new androidx.compose.foundation.interaction.FocusInteraction$Focus
            r7.<init>()
            r3 = r7
            androidx.compose.foundation.interaction.Interaction r3 = (androidx.compose.foundation.interaction.Interaction) r3
        L9c:
            androidx.compose.animation.core.Animatable<androidx.compose.ui.unit.Dp, androidx.compose.animation.core.AnimationVector1D> r7 = r6.$animatable
            float r1 = r6.$target
            androidx.compose.foundation.interaction.Interaction r4 = r6.$interaction
            r5 = r6
            kotlin.coroutines.Continuation r5 = (kotlin.coroutines.Continuation) r5
            r6.label = r2
            java.lang.Object r7 = androidx.compose.material3.internal.ElevationKt.m3204animateElevationrAjV9yQ(r7, r1, r3, r4, r5)
            if (r7 != r0) goto Lae
        Lad:
            return r0
        Lae:
            kotlin.Unit r7 = kotlin.Unit.INSTANCE
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.ButtonElevation$animateElevation$2$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
