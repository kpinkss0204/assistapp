package androidx.compose.foundation.text.input.internal;

import android.os.Build;
import android.os.CancellationSignal;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.HandwritingGesture;
import android.view.inputmethod.InputConnection;
import android.view.inputmethod.PreviewableHandwritingGesture;
import androidx.compose.foundation.content.TransferableContent;
import androidx.compose.foundation.content.internal.ReceiveContentConfiguration;
import androidx.compose.foundation.text.input.InputTransformation;
import androidx.compose.foundation.text.input.TextFieldCharSequence;
import androidx.compose.foundation.text.input.TextFieldState;
import androidx.compose.foundation.text.input.internal.AndroidTextInputSession_androidKt;
import androidx.compose.foundation.text.input.internal.undo.TextFieldEditUndoBehavior;
import androidx.compose.ui.platform.PlatformTextInputMethodRequest;
import androidx.compose.ui.platform.PlatformTextInputSession;
import androidx.compose.ui.platform.ViewConfiguration;
import androidx.compose.ui.text.TextRange;
import androidx.compose.ui.text.input.ImeAction;
import androidx.compose.ui.text.input.ImeOptions;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.firebase.analytics.FirebaseAnalytics;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.flow.MutableSharedFlow;

/* JADX INFO: compiled from: AndroidTextInputSession.android.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000d\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0001\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a \u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\u00022\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00020\rH\u0002\u001al\u0010\u000e\u001a\u00020\u000f*\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u00182\u0014\u0010\u0019\u001a\u0010\u0012\u0004\u0012\u00020\u001b\u0012\u0004\u0012\u00020\n\u0018\u00010\u001a2\u0006\u0010\u001c\u001a\u00020\u001d2\u000e\u0010\u001e\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\u001f2\b\u0010 \u001a\u0004\u0018\u00010!H\u0081@¢\u0006\u0002\u0010\"\u001ah\u0010\u000e\u001a\u00020\u000f*\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u00182\u0014\u0010\u0019\u001a\u0010\u0012\u0004\u0012\u00020\u001b\u0012\u0004\u0012\u00020\n\u0018\u00010\u001a2\u0010\b\u0002\u0010\u001e\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\u001f2\n\b\u0002\u0010 \u001a\u0004\u0018\u00010!H\u0080@¢\u0006\u0002\u0010#\"\u0016\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0003\"\u0016\u0010\u0004\u001a\u00020\u00058\u0000X\u0081T¢\u0006\b\n\u0000\u0012\u0004\b\u0006\u0010\u0007\"\u000e\u0010\b\u001a\u00020\u0002X\u0082T¢\u0006\u0002\n\u0000¨\u0006$"}, d2 = {"ALL_MIME_TYPES", "", "", "[Ljava/lang/String;", "TIA_DEBUG", "", "getTIA_DEBUG$annotations", "()V", "TIA_TAG", "logDebug", "", "tag", FirebaseAnalytics.Param.CONTENT, "Lkotlin/Function0;", "platformSpecificTextInputSession", "", "Landroidx/compose/ui/platform/PlatformTextInputSession;", "state", "Landroidx/compose/foundation/text/input/internal/TransformedTextFieldState;", "layoutState", "Landroidx/compose/foundation/text/input/internal/TextLayoutState;", "imeOptions", "Landroidx/compose/ui/text/input/ImeOptions;", "receiveContentConfiguration", "Landroidx/compose/foundation/content/internal/ReceiveContentConfiguration;", "onImeAction", "Lkotlin/Function1;", "Landroidx/compose/ui/text/input/ImeAction;", "composeImm", "Landroidx/compose/foundation/text/input/internal/ComposeInputMethodManager;", "stylusHandwritingTrigger", "Lkotlinx/coroutines/flow/MutableSharedFlow;", "viewConfiguration", "Landroidx/compose/ui/platform/ViewConfiguration;", "(Landroidx/compose/ui/platform/PlatformTextInputSession;Landroidx/compose/foundation/text/input/internal/TransformedTextFieldState;Landroidx/compose/foundation/text/input/internal/TextLayoutState;Landroidx/compose/ui/text/input/ImeOptions;Landroidx/compose/foundation/content/internal/ReceiveContentConfiguration;Lkotlin/jvm/functions/Function1;Landroidx/compose/foundation/text/input/internal/ComposeInputMethodManager;Lkotlinx/coroutines/flow/MutableSharedFlow;Landroidx/compose/ui/platform/ViewConfiguration;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "(Landroidx/compose/ui/platform/PlatformTextInputSession;Landroidx/compose/foundation/text/input/internal/TransformedTextFieldState;Landroidx/compose/foundation/text/input/internal/TextLayoutState;Landroidx/compose/ui/text/input/ImeOptions;Landroidx/compose/foundation/content/internal/ReceiveContentConfiguration;Lkotlin/jvm/functions/Function1;Lkotlinx/coroutines/flow/MutableSharedFlow;Landroidx/compose/ui/platform/ViewConfiguration;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "foundation_release"}, k = 2, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
public final class AndroidTextInputSession_androidKt {
    private static final String[] ALL_MIME_TYPES = {"*/*", "image/*", "video/*"};
    public static final boolean TIA_DEBUG = false;
    private static final String TIA_TAG = "AndroidTextInputSession";

    /* JADX INFO: renamed from: androidx.compose.foundation.text.input.internal.AndroidTextInputSession_androidKt$platformSpecificTextInputSession$1, reason: invalid class name */
    /* JADX INFO: compiled from: AndroidTextInputSession.android.kt */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    @DebugMetadata(c = "androidx.compose.foundation.text.input.internal.AndroidTextInputSession_androidKt", f = "AndroidTextInputSession.android.kt", i = {}, l = {59}, m = "platformSpecificTextInputSession", n = {}, s = {})
    static final class AnonymousClass1 extends ContinuationImpl {
        int label;
        /* synthetic */ Object result;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return AndroidTextInputSession_androidKt.platformSpecificTextInputSession(null, null, null, null, null, null, null, null, this);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.text.input.internal.AndroidTextInputSession_androidKt$platformSpecificTextInputSession$2, reason: invalid class name */
    /* JADX INFO: compiled from: AndroidTextInputSession.android.kt */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    @DebugMetadata(c = "androidx.compose.foundation.text.input.internal.AndroidTextInputSession_androidKt", f = "AndroidTextInputSession.android.kt", i = {}, l = {82}, m = "platformSpecificTextInputSession", n = {}, s = {})
    static final class AnonymousClass2 extends ContinuationImpl {
        int label;
        /* synthetic */ Object result;

        AnonymousClass2(Continuation<? super AnonymousClass2> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return AndroidTextInputSession_androidKt.platformSpecificTextInputSession(null, null, null, null, null, null, null, null, null, this);
        }
    }

    public static /* synthetic */ void getTIA_DEBUG$annotations() {
    }

    private static final void logDebug(String str, Function0<String> function0) {
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0016  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final java.lang.Object platformSpecificTextInputSession(androidx.compose.ui.platform.PlatformTextInputSession r12, androidx.compose.foundation.text.input.internal.TransformedTextFieldState r13, androidx.compose.foundation.text.input.internal.TextLayoutState r14, androidx.compose.ui.text.input.ImeOptions r15, androidx.compose.foundation.content.internal.ReceiveContentConfiguration r16, kotlin.jvm.functions.Function1<? super androidx.compose.ui.text.input.ImeAction, kotlin.Unit> r17, kotlinx.coroutines.flow.MutableSharedFlow<kotlin.Unit> r18, androidx.compose.ui.platform.ViewConfiguration r19, kotlin.coroutines.Continuation<?> r20) {
        /*
            r0 = r20
            boolean r1 = r0 instanceof androidx.compose.foundation.text.input.internal.AndroidTextInputSession_androidKt.AnonymousClass1
            if (r1 == 0) goto L16
            r1 = r0
            androidx.compose.foundation.text.input.internal.AndroidTextInputSession_androidKt$platformSpecificTextInputSession$1 r1 = (androidx.compose.foundation.text.input.internal.AndroidTextInputSession_androidKt.AnonymousClass1) r1
            int r2 = r1.label
            r3 = -2147483648(0xffffffff80000000, float:-0.0)
            r2 = r2 & r3
            if (r2 == 0) goto L16
            int r0 = r1.label
            int r0 = r0 - r3
            r1.label = r0
            goto L1b
        L16:
            androidx.compose.foundation.text.input.internal.AndroidTextInputSession_androidKt$platformSpecificTextInputSession$1 r1 = new androidx.compose.foundation.text.input.internal.AndroidTextInputSession_androidKt$platformSpecificTextInputSession$1
            r1.<init>(r0)
        L1b:
            r11 = r1
            java.lang.Object r0 = r11.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r11.label
            r3 = 1
            if (r2 == 0) goto L35
            if (r2 == r3) goto L31
            java.lang.IllegalStateException r12 = new java.lang.IllegalStateException
            java.lang.String r13 = "call to 'resume' before 'invoke' with coroutine"
            r12.<init>(r13)
            throw r12
        L31:
            kotlin.ResultKt.throwOnFailure(r0)
            goto L55
        L35:
            kotlin.ResultKt.throwOnFailure(r0)
            android.view.View r0 = r12.getView()
            androidx.compose.foundation.text.input.internal.ComposeInputMethodManager r8 = androidx.compose.foundation.text.input.internal.ComposeInputMethodManager_androidKt.ComposeInputMethodManager(r0)
            r11.label = r3
            r2 = r12
            r3 = r13
            r4 = r14
            r5 = r15
            r6 = r16
            r7 = r17
            r9 = r18
            r10 = r19
            java.lang.Object r12 = platformSpecificTextInputSession(r2, r3, r4, r5, r6, r7, r8, r9, r10, r11)
            if (r12 != r1) goto L55
            return r1
        L55:
            kotlin.KotlinNothingValueException r12 = new kotlin.KotlinNothingValueException
            r12.<init>()
            throw r12
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.text.input.internal.AndroidTextInputSession_androidKt.platformSpecificTextInputSession(androidx.compose.ui.platform.PlatformTextInputSession, androidx.compose.foundation.text.input.internal.TransformedTextFieldState, androidx.compose.foundation.text.input.internal.TextLayoutState, androidx.compose.ui.text.input.ImeOptions, androidx.compose.foundation.content.internal.ReceiveContentConfiguration, kotlin.jvm.functions.Function1, kotlinx.coroutines.flow.MutableSharedFlow, androidx.compose.ui.platform.ViewConfiguration, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public static /* synthetic */ Object platformSpecificTextInputSession$default(PlatformTextInputSession platformTextInputSession, TransformedTextFieldState transformedTextFieldState, TextLayoutState textLayoutState, ImeOptions imeOptions, ReceiveContentConfiguration receiveContentConfiguration, Function1 function1, MutableSharedFlow mutableSharedFlow, ViewConfiguration viewConfiguration, Continuation continuation, int i, Object obj) {
        if ((i & 32) != 0) {
            mutableSharedFlow = null;
        }
        if ((i & 64) != 0) {
            viewConfiguration = null;
        }
        return platformSpecificTextInputSession(platformTextInputSession, transformedTextFieldState, textLayoutState, imeOptions, receiveContentConfiguration, function1, mutableSharedFlow, viewConfiguration, continuation);
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0016  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final java.lang.Object platformSpecificTextInputSession(androidx.compose.ui.platform.PlatformTextInputSession r16, androidx.compose.foundation.text.input.internal.TransformedTextFieldState r17, androidx.compose.foundation.text.input.internal.TextLayoutState r18, androidx.compose.ui.text.input.ImeOptions r19, androidx.compose.foundation.content.internal.ReceiveContentConfiguration r20, kotlin.jvm.functions.Function1<? super androidx.compose.ui.text.input.ImeAction, kotlin.Unit> r21, androidx.compose.foundation.text.input.internal.ComposeInputMethodManager r22, kotlinx.coroutines.flow.MutableSharedFlow<kotlin.Unit> r23, androidx.compose.ui.platform.ViewConfiguration r24, kotlin.coroutines.Continuation<?> r25) {
        /*
            r0 = r25
            boolean r1 = r0 instanceof androidx.compose.foundation.text.input.internal.AndroidTextInputSession_androidKt.AnonymousClass2
            if (r1 == 0) goto L16
            r1 = r0
            androidx.compose.foundation.text.input.internal.AndroidTextInputSession_androidKt$platformSpecificTextInputSession$2 r1 = (androidx.compose.foundation.text.input.internal.AndroidTextInputSession_androidKt.AnonymousClass2) r1
            int r2 = r1.label
            r3 = -2147483648(0xffffffff80000000, float:-0.0)
            r2 = r2 & r3
            if (r2 == 0) goto L16
            int r0 = r1.label
            int r0 = r0 - r3
            r1.label = r0
            goto L1b
        L16:
            androidx.compose.foundation.text.input.internal.AndroidTextInputSession_androidKt$platformSpecificTextInputSession$2 r1 = new androidx.compose.foundation.text.input.internal.AndroidTextInputSession_androidKt$platformSpecificTextInputSession$2
            r1.<init>(r0)
        L1b:
            java.lang.Object r0 = r1.result
            java.lang.Object r2 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r3 = r1.label
            r4 = 1
            if (r3 == 0) goto L34
            if (r3 == r4) goto L30
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r1)
            throw r0
        L30:
            kotlin.ResultKt.throwOnFailure(r0)
            goto L5a
        L34:
            kotlin.ResultKt.throwOnFailure(r0)
            androidx.compose.foundation.text.input.internal.AndroidTextInputSession_androidKt$platformSpecificTextInputSession$3 r5 = new androidx.compose.foundation.text.input.internal.AndroidTextInputSession_androidKt$platformSpecificTextInputSession$3
            r15 = 0
            r10 = r16
            r7 = r17
            r8 = r18
            r11 = r19
            r12 = r20
            r13 = r21
            r9 = r22
            r6 = r23
            r14 = r24
            r5.<init>(r6, r7, r8, r9, r10, r11, r12, r13, r14, r15)
            kotlin.jvm.functions.Function2 r5 = (kotlin.jvm.functions.Function2) r5
            r1.label = r4
            java.lang.Object r0 = kotlinx.coroutines.CoroutineScopeKt.coroutineScope(r5, r1)
            if (r0 != r2) goto L5a
            return r2
        L5a:
            kotlin.KotlinNothingValueException r0 = new kotlin.KotlinNothingValueException
            r0.<init>()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.text.input.internal.AndroidTextInputSession_androidKt.platformSpecificTextInputSession(androidx.compose.ui.platform.PlatformTextInputSession, androidx.compose.foundation.text.input.internal.TransformedTextFieldState, androidx.compose.foundation.text.input.internal.TextLayoutState, androidx.compose.ui.text.input.ImeOptions, androidx.compose.foundation.content.internal.ReceiveContentConfiguration, kotlin.jvm.functions.Function1, androidx.compose.foundation.text.input.internal.ComposeInputMethodManager, kotlinx.coroutines.flow.MutableSharedFlow, androidx.compose.ui.platform.ViewConfiguration, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.text.input.internal.AndroidTextInputSession_androidKt$platformSpecificTextInputSession$3, reason: invalid class name */
    /* JADX INFO: compiled from: AndroidTextInputSession.android.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0001\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    @DebugMetadata(c = "androidx.compose.foundation.text.input.internal.AndroidTextInputSession_androidKt$platformSpecificTextInputSession$3", f = "AndroidTextInputSession.android.kt", i = {}, l = {129}, m = "invokeSuspend", n = {}, s = {})
    static final class AnonymousClass3 extends SuspendLambda implements Function2<CoroutineScope, Continuation<?>, Object> {
        final /* synthetic */ ComposeInputMethodManager $composeImm;
        final /* synthetic */ ImeOptions $imeOptions;
        final /* synthetic */ TextLayoutState $layoutState;
        final /* synthetic */ Function1<ImeAction, Unit> $onImeAction;
        final /* synthetic */ ReceiveContentConfiguration $receiveContentConfiguration;
        final /* synthetic */ TransformedTextFieldState $state;
        final /* synthetic */ MutableSharedFlow<Unit> $stylusHandwritingTrigger;
        final /* synthetic */ PlatformTextInputSession $this_platformSpecificTextInputSession;
        final /* synthetic */ ViewConfiguration $viewConfiguration;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        AnonymousClass3(MutableSharedFlow<Unit> mutableSharedFlow, TransformedTextFieldState transformedTextFieldState, TextLayoutState textLayoutState, ComposeInputMethodManager composeInputMethodManager, PlatformTextInputSession platformTextInputSession, ImeOptions imeOptions, ReceiveContentConfiguration receiveContentConfiguration, Function1<? super ImeAction, Unit> function1, ViewConfiguration viewConfiguration, Continuation<? super AnonymousClass3> continuation) {
            super(2, continuation);
            this.$stylusHandwritingTrigger = mutableSharedFlow;
            this.$state = transformedTextFieldState;
            this.$layoutState = textLayoutState;
            this.$composeImm = composeInputMethodManager;
            this.$this_platformSpecificTextInputSession = platformTextInputSession;
            this.$imeOptions = imeOptions;
            this.$receiveContentConfiguration = receiveContentConfiguration;
            this.$onImeAction = function1;
            this.$viewConfiguration = viewConfiguration;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass3 anonymousClass3 = new AnonymousClass3(this.$stylusHandwritingTrigger, this.$state, this.$layoutState, this.$composeImm, this.$this_platformSpecificTextInputSession, this.$imeOptions, this.$receiveContentConfiguration, this.$onImeAction, this.$viewConfiguration, continuation);
            anonymousClass3.L$0 = obj;
            return anonymousClass3;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<?> continuation) {
            return ((AnonymousClass3) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX INFO: renamed from: androidx.compose.foundation.text.input.internal.AndroidTextInputSession_androidKt$platformSpecificTextInputSession$3$1, reason: invalid class name */
        /* JADX INFO: compiled from: AndroidTextInputSession.android.kt */
        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
        @DebugMetadata(c = "androidx.compose.foundation.text.input.internal.AndroidTextInputSession_androidKt$platformSpecificTextInputSession$3$1", f = "AndroidTextInputSession.android.kt", i = {}, l = {84}, m = "invokeSuspend", n = {}, s = {})
        static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            final /* synthetic */ ComposeInputMethodManager $composeImm;
            final /* synthetic */ TransformedTextFieldState $state;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            AnonymousClass1(TransformedTextFieldState transformedTextFieldState, ComposeInputMethodManager composeInputMethodManager, Continuation<? super AnonymousClass1> continuation) {
                super(2, continuation);
                this.$state = transformedTextFieldState;
                this.$composeImm = composeInputMethodManager;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                return new AnonymousClass1(this.$state, this.$composeImm, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    TransformedTextFieldState transformedTextFieldState = this.$state;
                    final ComposeInputMethodManager composeInputMethodManager = this.$composeImm;
                    this.label = 1;
                    if (transformedTextFieldState.collectImeNotifications(new TextFieldState.NotifyImeListener() { // from class: androidx.compose.foundation.text.input.internal.AndroidTextInputSession_androidKt$platformSpecificTextInputSession$3$1$$ExternalSyntheticLambda0
                        @Override // androidx.compose.foundation.text.input.TextFieldState.NotifyImeListener
                        public final void onChange(TextFieldCharSequence textFieldCharSequence, TextFieldCharSequence textFieldCharSequence2, boolean z) {
                            AndroidTextInputSession_androidKt.AnonymousClass3.AnonymousClass1.invokeSuspend$lambda$0(composeInputMethodManager, textFieldCharSequence, textFieldCharSequence2, z);
                        }
                    }, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                } else {
                    if (i != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                }
                throw new KotlinNothingValueException();
            }

            /* JADX INFO: Access modifiers changed from: private */
            public static final void invokeSuspend$lambda$0(ComposeInputMethodManager composeInputMethodManager, TextFieldCharSequence textFieldCharSequence, TextFieldCharSequence textFieldCharSequence2, boolean z) {
                long selection = textFieldCharSequence.getSelection();
                long selection2 = textFieldCharSequence2.getSelection();
                TextRange composition = textFieldCharSequence.getComposition();
                TextRange composition2 = textFieldCharSequence2.getComposition();
                if (z && textFieldCharSequence.getComposition() != null && !textFieldCharSequence.contentEquals(textFieldCharSequence2)) {
                    composeInputMethodManager.restartInput();
                } else {
                    if (TextRange.m6505equalsimpl0(selection, selection2) && Intrinsics.areEqual(composition, composition2)) {
                        return;
                    }
                    composeInputMethodManager.updateSelection(TextRange.m6510getMinimpl(selection2), TextRange.m6509getMaximpl(selection2), composition2 != null ? TextRange.m6510getMinimpl(composition2.getPackedValue()) : -1, composition2 != null ? TextRange.m6509getMaximpl(composition2.getPackedValue()) : -1);
                }
            }
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                CoroutineScope coroutineScope = (CoroutineScope) this.L$0;
                BuildersKt__Builders_commonKt.launch$default(coroutineScope, null, CoroutineStart.UNDISPATCHED, new AnonymousClass1(this.$state, this.$composeImm, null), 1, null);
                MutableSharedFlow<Unit> mutableSharedFlow = this.$stylusHandwritingTrigger;
                if (mutableSharedFlow != null) {
                    BuildersKt__Builders_commonKt.launch$default(coroutineScope, null, null, new AndroidTextInputSession_androidKt$platformSpecificTextInputSession$3$2$1(mutableSharedFlow, this.$composeImm, null), 3, null);
                }
                final CursorAnchorInfoController cursorAnchorInfoController = new CursorAnchorInfoController(this.$state, this.$layoutState, this.$composeImm, coroutineScope);
                PlatformTextInputSession platformTextInputSession = this.$this_platformSpecificTextInputSession;
                final TransformedTextFieldState transformedTextFieldState = this.$state;
                final ImeOptions imeOptions = this.$imeOptions;
                final ReceiveContentConfiguration receiveContentConfiguration = this.$receiveContentConfiguration;
                final ComposeInputMethodManager composeInputMethodManager = this.$composeImm;
                final Function1<ImeAction, Unit> function1 = this.$onImeAction;
                final TextLayoutState textLayoutState = this.$layoutState;
                final ViewConfiguration viewConfiguration = this.$viewConfiguration;
                this.label = 1;
                if (platformTextInputSession.startInputMethod(new PlatformTextInputMethodRequest() { // from class: androidx.compose.foundation.text.input.internal.AndroidTextInputSession_androidKt$platformSpecificTextInputSession$3$$ExternalSyntheticLambda0
                    @Override // androidx.compose.ui.platform.PlatformTextInputMethodRequest
                    public final InputConnection createInputConnection(EditorInfo editorInfo) {
                        return AndroidTextInputSession_androidKt.AnonymousClass3.invokeSuspend$lambda$2(transformedTextFieldState, imeOptions, receiveContentConfiguration, composeInputMethodManager, function1, cursorAnchorInfoController, textLayoutState, viewConfiguration, editorInfo);
                    }
                }, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            throw new KotlinNothingValueException();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final InputConnection invokeSuspend$lambda$2(final TransformedTextFieldState transformedTextFieldState, ImeOptions imeOptions, final ReceiveContentConfiguration receiveContentConfiguration, final ComposeInputMethodManager composeInputMethodManager, final Function1 function1, final CursorAnchorInfoController cursorAnchorInfoController, final TextLayoutState textLayoutState, final ViewConfiguration viewConfiguration, EditorInfo editorInfo) {
            AndroidTextInputSession_androidKt.logDebug$default(null, new Function0<String>() { // from class: androidx.compose.foundation.text.input.internal.AndroidTextInputSession_androidKt$platformSpecificTextInputSession$3$3$1
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public final String invoke() {
                    return "createInputConnection(value=\"" + ((Object) transformedTextFieldState.getVisualText()) + "\")";
                }
            }, 1, null);
            TextInputSession textInputSession = new TextInputSession() { // from class: androidx.compose.foundation.text.input.internal.AndroidTextInputSession_androidKt$platformSpecificTextInputSession$3$3$textInputSession$1
                @Override // androidx.compose.foundation.text.input.internal.TextInputSession
                public TextFieldCharSequence getText() {
                    return transformedTextFieldState.getVisualText();
                }

                @Override // androidx.compose.foundation.text.input.internal.TextInputSession
                public void requestEdit(Function1<? super EditingBuffer, Unit> block) {
                    TransformedTextFieldState transformedTextFieldState2 = transformedTextFieldState;
                    TextFieldState textFieldState = transformedTextFieldState2.textFieldState;
                    InputTransformation inputTransformation = transformedTextFieldState2.inputTransformation;
                    TextFieldEditUndoBehavior textFieldEditUndoBehavior = TextFieldEditUndoBehavior.MergeIfPossible;
                    textFieldState.getMainBuffer().getChangeTracker().clearChanges();
                    block.invoke(textFieldState.getMainBuffer());
                    textFieldState.commitEditAsUser(inputTransformation, false, textFieldEditUndoBehavior);
                }

                @Override // androidx.compose.foundation.text.input.internal.TextInputSession
                public void sendKeyEvent(KeyEvent keyEvent) {
                    composeInputMethodManager.sendKeyEvent(keyEvent);
                }

                @Override // androidx.compose.foundation.text.input.internal.TextInputSession
                /* JADX INFO: renamed from: onImeAction-KlQnJC8, reason: not valid java name */
                public void mo1420onImeActionKlQnJC8(int imeAction) {
                    Function1<ImeAction, Unit> function12 = function1;
                    if (function12 != null) {
                        function12.invoke(ImeAction.m6674boximpl(imeAction));
                    }
                }

                @Override // androidx.compose.foundation.text.input.internal.TextInputSession
                public boolean onCommitContent(TransferableContent transferableContent) {
                    ReceiveContentConfiguration receiveContentConfiguration2 = receiveContentConfiguration;
                    if (receiveContentConfiguration2 != null) {
                        return receiveContentConfiguration2.onCommitContent(transferableContent);
                    }
                    return false;
                }

                @Override // androidx.compose.foundation.text.input.internal.TextInputSession
                public void requestCursorUpdates(int cursorUpdateMode) {
                    cursorAnchorInfoController.requestUpdates(cursorUpdateMode);
                }

                @Override // androidx.compose.foundation.text.input.internal.TextInputSession
                public int performHandwritingGesture(HandwritingGesture gesture) {
                    if (Build.VERSION.SDK_INT >= 34) {
                        return HandwritingGestureApi34.INSTANCE.performHandwritingGesture$foundation_release(transformedTextFieldState, gesture, textLayoutState, viewConfiguration);
                    }
                    return 2;
                }

                @Override // androidx.compose.foundation.text.input.internal.TextInputSession
                public boolean previewHandwritingGesture(PreviewableHandwritingGesture gesture, CancellationSignal cancellationSignal) {
                    if (Build.VERSION.SDK_INT >= 34) {
                        return HandwritingGestureApi34.INSTANCE.previewHandwritingGesture$foundation_release(transformedTextFieldState, gesture, textLayoutState, cancellationSignal);
                    }
                    return false;
                }
            };
            EditorInfo_androidKt.m1429updatepLxbY9I(editorInfo, transformedTextFieldState.getVisualText(), transformedTextFieldState.getVisualText().getSelection(), imeOptions, receiveContentConfiguration != null ? AndroidTextInputSession_androidKt.ALL_MIME_TYPES : null);
            return new StatelessInputConnection(textInputSession, editorInfo);
        }
    }

    static /* synthetic */ void logDebug$default(String str, Function0 function0, int i, Object obj) {
        if ((i & 1) != 0) {
            str = TIA_TAG;
        }
        logDebug(str, function0);
    }
}
