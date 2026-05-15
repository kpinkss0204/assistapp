package androidx.compose.foundation.text.input.internal.selection;

import androidx.compose.foundation.gestures.PressGestureScope;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.FrameMetricsAggregator;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: TextFieldSelectionState.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
@DebugMetadata(c = "androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState$detectTextFieldTapGestures$2$1$1", f = "TextFieldSelectionState.kt", i = {}, l = {TypedValues.PositionType.TYPE_PERCENT_HEIGHT, FrameMetricsAggregator.EVERY_DURATION}, m = "invokeSuspend", n = {}, s = {})
final class TextFieldSelectionState$detectTextFieldTapGestures$2$1$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ PressGestureScope $$this$detectTapAndPress;
    final /* synthetic */ MutableInteractionSource $interactionSource;
    final /* synthetic */ long $offset;
    private /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ TextFieldSelectionState this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    TextFieldSelectionState$detectTextFieldTapGestures$2$1$1(PressGestureScope pressGestureScope, TextFieldSelectionState textFieldSelectionState, long j, MutableInteractionSource mutableInteractionSource, Continuation<? super TextFieldSelectionState$detectTextFieldTapGestures$2$1$1> continuation) {
        super(2, continuation);
        this.$$this$detectTapAndPress = pressGestureScope;
        this.this$0 = textFieldSelectionState;
        this.$offset = j;
        this.$interactionSource = mutableInteractionSource;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        TextFieldSelectionState$detectTextFieldTapGestures$2$1$1 textFieldSelectionState$detectTextFieldTapGestures$2$1$1 = new TextFieldSelectionState$detectTextFieldTapGestures$2$1$1(this.$$this$detectTapAndPress, this.this$0, this.$offset, this.$interactionSource, continuation);
        textFieldSelectionState$detectTextFieldTapGestures$2$1$1.L$0 = obj;
        return textFieldSelectionState$detectTextFieldTapGestures$2$1$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((TextFieldSelectionState$detectTextFieldTapGestures$2$1$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState$detectTextFieldTapGestures$2$1$1$1, reason: invalid class name */
    /* JADX INFO: compiled from: TextFieldSelectionState.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    @DebugMetadata(c = "androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState$detectTextFieldTapGestures$2$1$1$1", f = "TextFieldSelectionState.kt", i = {1}, l = {496, TypedValues.PositionType.TYPE_TRANSITION_EASING}, m = "invokeSuspend", n = {"press"}, s = {"L$0"})
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ MutableInteractionSource $interactionSource;
        final /* synthetic */ long $offset;
        Object L$0;
        int label;
        final /* synthetic */ TextFieldSelectionState this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(TextFieldSelectionState textFieldSelectionState, long j, MutableInteractionSource mutableInteractionSource, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.this$0 = textFieldSelectionState;
            this.$offset = j;
            this.$interactionSource = mutableInteractionSource;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass1(this.this$0, this.$offset, this.$interactionSource, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Removed duplicated region for block: B:20:0x0066  */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r8) {
            /*
                r7 = this;
                java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                int r1 = r7.label
                r2 = 0
                r3 = 2
                r4 = 1
                if (r1 == 0) goto L27
                if (r1 == r4) goto L1f
                if (r1 != r3) goto L17
                java.lang.Object r0 = r7.L$0
                androidx.compose.foundation.interaction.PressInteraction$Press r0 = (androidx.compose.foundation.interaction.PressInteraction.Press) r0
                kotlin.ResultKt.throwOnFailure(r8)
                goto L67
            L17:
                java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
                java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                r8.<init>(r0)
                throw r8
            L1f:
                java.lang.Object r1 = r7.L$0
                androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState r1 = (androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState) r1
                kotlin.ResultKt.throwOnFailure(r8)
                goto L49
            L27:
                kotlin.ResultKt.throwOnFailure(r8)
                androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState r8 = r7.this$0
                androidx.compose.foundation.interaction.PressInteraction$Press r8 = androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState.access$getPressInteraction$p(r8)
                if (r8 == 0) goto L4c
                androidx.compose.foundation.interaction.MutableInteractionSource r1 = r7.$interactionSource
                androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState r5 = r7.this$0
                androidx.compose.foundation.interaction.PressInteraction$Cancel r6 = new androidx.compose.foundation.interaction.PressInteraction$Cancel
                r6.<init>(r8)
                androidx.compose.foundation.interaction.Interaction r6 = (androidx.compose.foundation.interaction.Interaction) r6
                r7.L$0 = r5
                r7.label = r4
                java.lang.Object r8 = r1.emit(r6, r7)
                if (r8 != r0) goto L48
                goto L65
            L48:
                r1 = r5
            L49:
                androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState.access$setPressInteraction$p(r1, r2)
            L4c:
                androidx.compose.foundation.interaction.PressInteraction$Press r8 = new androidx.compose.foundation.interaction.PressInteraction$Press
                long r4 = r7.$offset
                r8.<init>(r4, r2)
                androidx.compose.foundation.interaction.MutableInteractionSource r1 = r7.$interactionSource
                r2 = r8
                androidx.compose.foundation.interaction.Interaction r2 = (androidx.compose.foundation.interaction.Interaction) r2
                r4 = r7
                kotlin.coroutines.Continuation r4 = (kotlin.coroutines.Continuation) r4
                r7.L$0 = r8
                r7.label = r3
                java.lang.Object r1 = r1.emit(r2, r4)
                if (r1 != r0) goto L66
            L65:
                return r0
            L66:
                r0 = r8
            L67:
                androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState r8 = r7.this$0
                androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState.access$setPressInteraction$p(r8, r0)
                kotlin.Unit r8 = kotlin.Unit.INSTANCE
                return r8
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState$detectTextFieldTapGestures$2$1$1.AnonymousClass1.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:20:0x0073, code lost:
    
        if (r3.emit(r12, r11) == r0) goto L21;
     */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r12) {
        /*
            r11 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r11.label
            r2 = 2
            r3 = 1
            if (r1 == 0) goto L1e
            if (r1 == r3) goto L1a
            if (r1 != r2) goto L12
            kotlin.ResultKt.throwOnFailure(r12)
            goto L76
        L12:
            java.lang.IllegalStateException r12 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r12.<init>(r0)
            throw r12
        L1a:
            kotlin.ResultKt.throwOnFailure(r12)
            goto L4a
        L1e:
            kotlin.ResultKt.throwOnFailure(r12)
            java.lang.Object r12 = r11.L$0
            r4 = r12
            kotlinx.coroutines.CoroutineScope r4 = (kotlinx.coroutines.CoroutineScope) r4
            androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState$detectTextFieldTapGestures$2$1$1$1 r5 = new androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState$detectTextFieldTapGestures$2$1$1$1
            androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState r6 = r11.this$0
            long r7 = r11.$offset
            androidx.compose.foundation.interaction.MutableInteractionSource r9 = r11.$interactionSource
            r10 = 0
            r5.<init>(r6, r7, r9, r10)
            r7 = r5
            kotlin.jvm.functions.Function2 r7 = (kotlin.jvm.functions.Function2) r7
            r8 = 3
            r9 = 0
            r5 = 0
            r6 = 0
            kotlinx.coroutines.BuildersKt.launch$default(r4, r5, r6, r7, r8, r9)
            androidx.compose.foundation.gestures.PressGestureScope r12 = r11.$$this$detectTapAndPress
            r1 = r11
            kotlin.coroutines.Continuation r1 = (kotlin.coroutines.Continuation) r1
            r11.label = r3
            java.lang.Object r12 = r12.tryAwaitRelease(r1)
            if (r12 != r0) goto L4a
            goto L75
        L4a:
            java.lang.Boolean r12 = (java.lang.Boolean) r12
            boolean r12 = r12.booleanValue()
            androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState r1 = r11.this$0
            androidx.compose.foundation.interaction.PressInteraction$Press r1 = androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState.access$getPressInteraction$p(r1)
            if (r1 == 0) goto L76
            androidx.compose.foundation.interaction.MutableInteractionSource r3 = r11.$interactionSource
            if (r12 == 0) goto L64
            androidx.compose.foundation.interaction.PressInteraction$Release r12 = new androidx.compose.foundation.interaction.PressInteraction$Release
            r12.<init>(r1)
            androidx.compose.foundation.interaction.PressInteraction r12 = (androidx.compose.foundation.interaction.PressInteraction) r12
            goto L6b
        L64:
            androidx.compose.foundation.interaction.PressInteraction$Cancel r12 = new androidx.compose.foundation.interaction.PressInteraction$Cancel
            r12.<init>(r1)
            androidx.compose.foundation.interaction.PressInteraction r12 = (androidx.compose.foundation.interaction.PressInteraction) r12
        L6b:
            androidx.compose.foundation.interaction.Interaction r12 = (androidx.compose.foundation.interaction.Interaction) r12
            r11.label = r2
            java.lang.Object r12 = r3.emit(r12, r11)
            if (r12 != r0) goto L76
        L75:
            return r0
        L76:
            androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState r12 = r11.this$0
            r0 = 0
            androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState.access$setPressInteraction$p(r12, r0)
            kotlin.Unit r12 = kotlin.Unit.INSTANCE
            return r12
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState$detectTextFieldTapGestures$2$1$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
