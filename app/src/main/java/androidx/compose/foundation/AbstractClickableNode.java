package androidx.compose.foundation;

import android.view.KeyEvent;
import androidx.compose.foundation.gestures.PressGestureScope;
import androidx.compose.foundation.interaction.HoverInteraction;
import androidx.compose.foundation.interaction.InteractionSourceKt;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.foundation.interaction.PressInteraction;
import androidx.compose.ui.focus.FocusEventModifierNode;
import androidx.compose.ui.focus.FocusState;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.OffsetKt;
import androidx.compose.ui.input.key.Key;
import androidx.compose.ui.input.key.KeyEvent_androidKt;
import androidx.compose.ui.input.key.KeyInputModifierNode;
import androidx.compose.ui.input.pointer.PointerEvent;
import androidx.compose.ui.input.pointer.PointerEventPass;
import androidx.compose.ui.input.pointer.PointerEventType;
import androidx.compose.ui.input.pointer.PointerInputScope;
import androidx.compose.ui.input.pointer.SuspendingPointerInputFilterKt;
import androidx.compose.ui.input.pointer.SuspendingPointerInputModifierNode;
import androidx.compose.ui.node.DelegatableNode;
import androidx.compose.ui.node.DelegatingNode;
import androidx.compose.ui.node.PointerInputModifierNode;
import androidx.compose.ui.node.SemanticsModifierNode;
import androidx.compose.ui.node.TraversableNode;
import androidx.compose.ui.semantics.Role;
import androidx.compose.ui.semantics.SemanticsPropertiesKt;
import androidx.compose.ui.semantics.SemanticsPropertyReceiver;
import androidx.compose.ui.unit.IntOffset;
import androidx.compose.ui.unit.IntSizeKt;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.NotificationCompat;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScopeKt;

/* JADX INFO: compiled from: Clickable.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000Æ\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0000\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b \u0018\u0000 a2\u00020\u00012\u00020\u00022\u00020\u00032\u00020\u00042\u00020\u00052\u00020\u0006:\u0001aBC\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\b\u0012\b\u0010\t\u001a\u0004\u0018\u00010\n\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\b\u0010\r\u001a\u0004\u0018\u00010\u000e\u0012\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010\u0012\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00130\u0012¢\u0006\u0002\u0010\u0014J\b\u00106\u001a\u00020\fH\u0002J\b\u00107\u001a\u00020\u0013H\u0004J\b\u00108\u001a\u00020\u0013H\u0002J\b\u00109\u001a\u00020\u0013H\u0002J\b\u0010:\u001a\u00020\u0013H\u0002J\u0006\u0010;\u001a\u00020\u0013J\u0006\u0010<\u001a\u00020\u0013J\u0006\u0010=\u001a\u00020\u0013J\u000e\u0010>\u001a\u00020\u00132\u0006\u0010?\u001a\u00020@J\u0018\u0010A\u001a\u00020\f2\u0006\u0010B\u001a\u00020Cø\u0001\u0000¢\u0006\u0004\bD\u0010EJ(\u0010F\u001a\u00020\u00132\u0006\u0010G\u001a\u00020H2\u0006\u0010I\u001a\u00020J2\u0006\u0010K\u001a\u00020Lø\u0001\u0000¢\u0006\u0004\bM\u0010NJ\u0018\u0010O\u001a\u00020\f2\u0006\u0010B\u001a\u00020Cø\u0001\u0000¢\u0006\u0004\bP\u0010EJ\u000f\u0010Q\u001a\u0004\u0018\u00010\u0013H\u0004¢\u0006\u0002\u0010RJ\b\u0010S\u001a\u00020\fH\u0002JN\u0010T\u001a\u00020\u00132\b\u0010\u0007\u001a\u0004\u0018\u00010\b2\b\u0010\t\u001a\u0004\u0018\u00010\n2\u0006\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u00102\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00130\u0012H\u0004ø\u0001\u0000¢\u0006\u0002\bUJ\f\u0010V\u001a\u00020\u0013*\u00020WH\u0016J\n\u0010X\u001a\u00020\u0013*\u00020WJ\u0012\u0010Y\u001a\u00020\u0013*\u00020ZH¦@¢\u0006\u0002\u0010[J\u001f\u0010\\\u001a\u00020\u0013*\u00020]2\u0006\u0010^\u001a\u00020\u0016H\u0084@ø\u0001\u0000¢\u0006\u0004\b_\u0010`R\u0016\u0010\u0015\u001a\u00020\u0016X\u0082\u000eø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\n\u0002\u0010\u0017R\u001a\u0010\u0018\u001a\u000e\u0012\u0004\u0012\u00020\u001a\u0012\u0004\u0012\u00020\u001b0\u0019X\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\u000b\u001a\u00020\f2\u0006\u0010\u001c\u001a\u00020\f@BX\u0084\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001eR\u000e\u0010\u001f\u001a\u00020 X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010!\u001a\u00020\"X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010#\u001a\u0004\u0018\u00010$X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010%\u001a\u0004\u0018\u00010&X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010'\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R*\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00130\u00122\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u00130\u0012@BX\u0084\u000e¢\u0006\b\n\u0000\u001a\u0004\b(\u0010)R\u0010\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010*\u001a\u0004\u0018\u00010+X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010,\u001a\u0004\u0018\u00010\u001bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\u000f\u001a\u0004\u0018\u00010\u0010X\u0082\u000eø\u0001\u0000ø\u0001\u0001¢\u0006\u0002\n\u0000R\u0014\u0010-\u001a\u00020\fX\u0086D¢\u0006\b\n\u0000\u001a\u0004\b.\u0010\u001eR\u0011\u0010/\u001a\u00020\f8F¢\u0006\u0006\u001a\u0004\b0\u0010\u001eR\u0014\u00101\u001a\u000202X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b3\u00104R\u0010\u00105\u001a\u0004\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006b"}, d2 = {"Landroidx/compose/foundation/AbstractClickableNode;", "Landroidx/compose/ui/node/DelegatingNode;", "Landroidx/compose/ui/node/PointerInputModifierNode;", "Landroidx/compose/ui/input/key/KeyInputModifierNode;", "Landroidx/compose/ui/focus/FocusEventModifierNode;", "Landroidx/compose/ui/node/SemanticsModifierNode;", "Landroidx/compose/ui/node/TraversableNode;", "interactionSource", "Landroidx/compose/foundation/interaction/MutableInteractionSource;", "indicationNodeFactory", "Landroidx/compose/foundation/IndicationNodeFactory;", "enabled", "", "onClickLabel", "", "role", "Landroidx/compose/ui/semantics/Role;", "onClick", "Lkotlin/Function0;", "", "(Landroidx/compose/foundation/interaction/MutableInteractionSource;Landroidx/compose/foundation/IndicationNodeFactory;ZLjava/lang/String;Landroidx/compose/ui/semantics/Role;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/internal/DefaultConstructorMarker;)V", "centerOffset", "Landroidx/compose/ui/geometry/Offset;", "J", "currentKeyPressInteractions", "", "Landroidx/compose/ui/input/key/Key;", "Landroidx/compose/foundation/interaction/PressInteraction$Press;", "<set-?>", "getEnabled", "()Z", "focusableInNonTouchMode", "Landroidx/compose/foundation/FocusableInNonTouchMode;", "focusableNode", "Landroidx/compose/foundation/FocusableNode;", "hoverInteraction", "Landroidx/compose/foundation/interaction/HoverInteraction$Enter;", "indicationNode", "Landroidx/compose/ui/node/DelegatableNode;", "lazilyCreateIndication", "getOnClick", "()Lkotlin/jvm/functions/Function0;", "pointerInputNode", "Landroidx/compose/ui/input/pointer/SuspendingPointerInputModifierNode;", "pressInteraction", "shouldAutoInvalidate", "getShouldAutoInvalidate", "shouldMergeDescendantSemantics", "getShouldMergeDescendantSemantics", "traverseKey", "", "getTraverseKey", "()Ljava/lang/Object;", "userProvidedInteractionSource", "delayPressInteraction", "disposeInteractions", "emitHoverEnter", "emitHoverExit", "initializeIndicationAndInteractionSourceIfNeeded", "onAttach", "onCancelPointerInput", "onDetach", "onFocusEvent", "focusState", "Landroidx/compose/ui/focus/FocusState;", "onKeyEvent", NotificationCompat.CATEGORY_EVENT, "Landroidx/compose/ui/input/key/KeyEvent;", "onKeyEvent-ZmokQxo", "(Landroid/view/KeyEvent;)Z", "onPointerEvent", "pointerEvent", "Landroidx/compose/ui/input/pointer/PointerEvent;", "pass", "Landroidx/compose/ui/input/pointer/PointerEventPass;", "bounds", "Landroidx/compose/ui/unit/IntSize;", "onPointerEvent-H0pRuoY", "(Landroidx/compose/ui/input/pointer/PointerEvent;Landroidx/compose/ui/input/pointer/PointerEventPass;J)V", "onPreKeyEvent", "onPreKeyEvent-ZmokQxo", "resetPointerInputHandler", "()Lkotlin/Unit;", "shouldLazilyCreateIndication", "updateCommon", "updateCommon-QzZPfjk", "applyAdditionalSemantics", "Landroidx/compose/ui/semantics/SemanticsPropertyReceiver;", "applySemantics", "clickPointerInput", "Landroidx/compose/ui/input/pointer/PointerInputScope;", "(Landroidx/compose/ui/input/pointer/PointerInputScope;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "handlePressInteraction", "Landroidx/compose/foundation/gestures/PressGestureScope;", TypedValues.CycleType.S_WAVE_OFFSET, "handlePressInteraction-d-4ec7I", "(Landroidx/compose/foundation/gestures/PressGestureScope;JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "TraverseKey", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
public abstract class AbstractClickableNode extends DelegatingNode implements PointerInputModifierNode, KeyInputModifierNode, FocusEventModifierNode, SemanticsModifierNode, TraversableNode {
    private long centerOffset;
    private final Map<Key, PressInteraction.Press> currentKeyPressInteractions;
    private boolean enabled;
    private final FocusableInNonTouchMode focusableInNonTouchMode;
    private final FocusableNode focusableNode;
    private HoverInteraction.Enter hoverInteraction;
    private DelegatableNode indicationNode;
    private IndicationNodeFactory indicationNodeFactory;
    private MutableInteractionSource interactionSource;
    private boolean lazilyCreateIndication;
    private Function0<Unit> onClick;
    private String onClickLabel;
    private SuspendingPointerInputModifierNode pointerInputNode;
    private PressInteraction.Press pressInteraction;
    private Role role;
    private final boolean shouldAutoInvalidate;
    private final Object traverseKey;
    private MutableInteractionSource userProvidedInteractionSource;

    /* JADX INFO: renamed from: TraverseKey, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final int $stable = 8;

    public /* synthetic */ AbstractClickableNode(MutableInteractionSource mutableInteractionSource, IndicationNodeFactory indicationNodeFactory, boolean z, String str, Role role, Function0 function0, DefaultConstructorMarker defaultConstructorMarker) {
        this(mutableInteractionSource, indicationNodeFactory, z, str, role, function0);
    }

    public void applyAdditionalSemantics(SemanticsPropertyReceiver semanticsPropertyReceiver) {
    }

    public abstract Object clickPointerInput(PointerInputScope pointerInputScope, Continuation<? super Unit> continuation);

    @Override // androidx.compose.ui.node.SemanticsModifierNode
    public final boolean getShouldMergeDescendantSemantics() {
        return true;
    }

    @Override // androidx.compose.ui.input.key.KeyInputModifierNode
    /* JADX INFO: renamed from: onPreKeyEvent-ZmokQxo, reason: not valid java name */
    public final boolean mo494onPreKeyEventZmokQxo(KeyEvent event) {
        return false;
    }

    private AbstractClickableNode(MutableInteractionSource mutableInteractionSource, IndicationNodeFactory indicationNodeFactory, boolean z, String str, Role role, Function0<Unit> function0) {
        this.interactionSource = mutableInteractionSource;
        this.indicationNodeFactory = indicationNodeFactory;
        this.onClickLabel = str;
        this.role = role;
        this.enabled = z;
        this.onClick = function0;
        this.focusableInNonTouchMode = new FocusableInNonTouchMode();
        this.focusableNode = new FocusableNode(this.interactionSource);
        this.currentKeyPressInteractions = new LinkedHashMap();
        this.centerOffset = Offset.INSTANCE.m4343getZeroF1C5BW0();
        this.userProvidedInteractionSource = this.interactionSource;
        this.lazilyCreateIndication = shouldLazilyCreateIndication();
        this.traverseKey = INSTANCE;
    }

    protected final boolean getEnabled() {
        return this.enabled;
    }

    protected final Function0<Unit> getOnClick() {
        return this.onClick;
    }

    @Override // androidx.compose.ui.Modifier.Node
    public final boolean getShouldAutoInvalidate() {
        return this.shouldAutoInvalidate;
    }

    private final boolean shouldLazilyCreateIndication() {
        return this.userProvidedInteractionSource == null && this.indicationNodeFactory != null;
    }

    /* JADX WARN: Removed duplicated region for block: B:28:0x0083  */
    /* JADX INFO: renamed from: updateCommon-QzZPfjk, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected final void m495updateCommonQzZPfjk(androidx.compose.foundation.interaction.MutableInteractionSource r3, androidx.compose.foundation.IndicationNodeFactory r4, boolean r5, java.lang.String r6, androidx.compose.ui.semantics.Role r7, kotlin.jvm.functions.Function0<kotlin.Unit> r8) {
        /*
            r2 = this;
            androidx.compose.foundation.interaction.MutableInteractionSource r0 = r2.userProvidedInteractionSource
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r3)
            r1 = 1
            if (r0 != 0) goto L12
            r2.disposeInteractions()
            r2.userProvidedInteractionSource = r3
            r2.interactionSource = r3
            r3 = r1
            goto L13
        L12:
            r3 = 0
        L13:
            androidx.compose.foundation.IndicationNodeFactory r0 = r2.indicationNodeFactory
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r4)
            if (r0 != 0) goto L1e
            r2.indicationNodeFactory = r4
            r3 = r1
        L1e:
            boolean r4 = r2.enabled
            if (r4 == r5) goto L4c
            if (r5 == 0) goto L33
            androidx.compose.foundation.FocusableInNonTouchMode r4 = r2.focusableInNonTouchMode
            androidx.compose.ui.node.DelegatableNode r4 = (androidx.compose.ui.node.DelegatableNode) r4
            r2.delegate(r4)
            androidx.compose.foundation.FocusableNode r4 = r2.focusableNode
            androidx.compose.ui.node.DelegatableNode r4 = (androidx.compose.ui.node.DelegatableNode) r4
            r2.delegate(r4)
            goto L44
        L33:
            androidx.compose.foundation.FocusableInNonTouchMode r4 = r2.focusableInNonTouchMode
            androidx.compose.ui.node.DelegatableNode r4 = (androidx.compose.ui.node.DelegatableNode) r4
            r2.undelegate(r4)
            androidx.compose.foundation.FocusableNode r4 = r2.focusableNode
            androidx.compose.ui.node.DelegatableNode r4 = (androidx.compose.ui.node.DelegatableNode) r4
            r2.undelegate(r4)
            r2.disposeInteractions()
        L44:
            r4 = r2
            androidx.compose.ui.node.SemanticsModifierNode r4 = (androidx.compose.ui.node.SemanticsModifierNode) r4
            androidx.compose.ui.node.SemanticsModifierNodeKt.invalidateSemantics(r4)
            r2.enabled = r5
        L4c:
            java.lang.String r4 = r2.onClickLabel
            boolean r4 = kotlin.jvm.internal.Intrinsics.areEqual(r4, r6)
            if (r4 != 0) goto L5c
            r2.onClickLabel = r6
            r4 = r2
            androidx.compose.ui.node.SemanticsModifierNode r4 = (androidx.compose.ui.node.SemanticsModifierNode) r4
            androidx.compose.ui.node.SemanticsModifierNodeKt.invalidateSemantics(r4)
        L5c:
            androidx.compose.ui.semantics.Role r4 = r2.role
            boolean r4 = kotlin.jvm.internal.Intrinsics.areEqual(r4, r7)
            if (r4 != 0) goto L6c
            r2.role = r7
            r4 = r2
            androidx.compose.ui.node.SemanticsModifierNode r4 = (androidx.compose.ui.node.SemanticsModifierNode) r4
            androidx.compose.ui.node.SemanticsModifierNodeKt.invalidateSemantics(r4)
        L6c:
            r2.onClick = r8
            boolean r4 = r2.lazilyCreateIndication
            boolean r5 = r2.shouldLazilyCreateIndication()
            if (r4 == r5) goto L83
            boolean r4 = r2.shouldLazilyCreateIndication()
            r2.lazilyCreateIndication = r4
            if (r4 != 0) goto L83
            androidx.compose.ui.node.DelegatableNode r4 = r2.indicationNode
            if (r4 != 0) goto L83
            goto L84
        L83:
            r1 = r3
        L84:
            if (r1 == 0) goto L99
            androidx.compose.ui.node.DelegatableNode r3 = r2.indicationNode
            if (r3 != 0) goto L8e
            boolean r4 = r2.lazilyCreateIndication
            if (r4 != 0) goto L99
        L8e:
            if (r3 == 0) goto L93
            r2.undelegate(r3)
        L93:
            r3 = 0
            r2.indicationNode = r3
            r2.initializeIndicationAndInteractionSourceIfNeeded()
        L99:
            androidx.compose.foundation.FocusableNode r3 = r2.focusableNode
            androidx.compose.foundation.interaction.MutableInteractionSource r4 = r2.interactionSource
            r3.update(r4)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.AbstractClickableNode.m495updateCommonQzZPfjk(androidx.compose.foundation.interaction.MutableInteractionSource, androidx.compose.foundation.IndicationNodeFactory, boolean, java.lang.String, androidx.compose.ui.semantics.Role, kotlin.jvm.functions.Function0):void");
    }

    @Override // androidx.compose.ui.Modifier.Node
    public final void onAttach() {
        if (!this.lazilyCreateIndication) {
            initializeIndicationAndInteractionSourceIfNeeded();
        }
        if (this.enabled) {
            delegate(this.focusableInNonTouchMode);
            delegate(this.focusableNode);
        }
    }

    @Override // androidx.compose.ui.Modifier.Node
    public final void onDetach() {
        disposeInteractions();
        if (this.userProvidedInteractionSource == null) {
            this.interactionSource = null;
        }
        DelegatableNode delegatableNode = this.indicationNode;
        if (delegatableNode != null) {
            undelegate(delegatableNode);
        }
        this.indicationNode = null;
    }

    protected final void disposeInteractions() {
        MutableInteractionSource mutableInteractionSource = this.interactionSource;
        if (mutableInteractionSource != null) {
            PressInteraction.Press press = this.pressInteraction;
            if (press != null) {
                mutableInteractionSource.tryEmit(new PressInteraction.Cancel(press));
            }
            HoverInteraction.Enter enter = this.hoverInteraction;
            if (enter != null) {
                mutableInteractionSource.tryEmit(new HoverInteraction.Exit(enter));
            }
            Iterator<T> it = this.currentKeyPressInteractions.values().iterator();
            while (it.hasNext()) {
                mutableInteractionSource.tryEmit(new PressInteraction.Cancel((PressInteraction.Press) it.next()));
            }
        }
        this.pressInteraction = null;
        this.hoverInteraction = null;
        this.currentKeyPressInteractions.clear();
    }

    private final void initializeIndicationAndInteractionSourceIfNeeded() {
        IndicationNodeFactory indicationNodeFactory;
        if (this.indicationNode == null && (indicationNodeFactory = this.indicationNodeFactory) != null) {
            if (this.interactionSource == null) {
                this.interactionSource = InteractionSourceKt.MutableInteractionSource();
            }
            this.focusableNode.update(this.interactionSource);
            MutableInteractionSource mutableInteractionSource = this.interactionSource;
            Intrinsics.checkNotNull(mutableInteractionSource);
            DelegatableNode delegatableNodeCreate = indicationNodeFactory.create(mutableInteractionSource);
            delegate(delegatableNodeCreate);
            this.indicationNode = delegatableNodeCreate;
        }
    }

    @Override // androidx.compose.ui.node.PointerInputModifierNode
    /* JADX INFO: renamed from: onPointerEvent-H0pRuoY, reason: not valid java name */
    public final void mo493onPointerEventH0pRuoY(PointerEvent pointerEvent, PointerEventPass pass, long bounds) {
        long jM7203getCenterozmzZPI = IntSizeKt.m7203getCenterozmzZPI(bounds);
        this.centerOffset = OffsetKt.Offset(IntOffset.m7155getXimpl(jM7203getCenterozmzZPI), IntOffset.m7156getYimpl(jM7203getCenterozmzZPI));
        initializeIndicationAndInteractionSourceIfNeeded();
        if (this.enabled && pass == PointerEventPass.Main) {
            int type = pointerEvent.getType();
            if (PointerEventType.m5723equalsimpl0(type, PointerEventType.INSTANCE.m5727getEnter7fucELk())) {
                BuildersKt__Builders_commonKt.launch$default(getCoroutineScope(), null, null, new AbstractClickableNode$onPointerEvent$1(this, null), 3, null);
            } else if (PointerEventType.m5723equalsimpl0(type, PointerEventType.INSTANCE.m5728getExit7fucELk())) {
                BuildersKt__Builders_commonKt.launch$default(getCoroutineScope(), null, null, new AbstractClickableNode$onPointerEvent$2(this, null), 3, null);
            }
        }
        if (this.pointerInputNode == null) {
            this.pointerInputNode = (SuspendingPointerInputModifierNode) delegate(SuspendingPointerInputFilterKt.SuspendingPointerInputModifierNode(new AbstractClickableNode$onPointerEvent$3(this, null)));
        }
        SuspendingPointerInputModifierNode suspendingPointerInputModifierNode = this.pointerInputNode;
        if (suspendingPointerInputModifierNode != null) {
            suspendingPointerInputModifierNode.mo493onPointerEventH0pRuoY(pointerEvent, pass, bounds);
        }
    }

    @Override // androidx.compose.ui.node.PointerInputModifierNode
    public final void onCancelPointerInput() {
        HoverInteraction.Enter enter;
        MutableInteractionSource mutableInteractionSource = this.interactionSource;
        if (mutableInteractionSource != null && (enter = this.hoverInteraction) != null) {
            mutableInteractionSource.tryEmit(new HoverInteraction.Exit(enter));
        }
        this.hoverInteraction = null;
        SuspendingPointerInputModifierNode suspendingPointerInputModifierNode = this.pointerInputNode;
        if (suspendingPointerInputModifierNode != null) {
            suspendingPointerInputModifierNode.onCancelPointerInput();
        }
    }

    @Override // androidx.compose.ui.input.key.KeyInputModifierNode
    /* JADX INFO: renamed from: onKeyEvent-ZmokQxo, reason: not valid java name */
    public final boolean mo492onKeyEventZmokQxo(KeyEvent event) {
        initializeIndicationAndInteractionSourceIfNeeded();
        if (this.enabled && Clickable_androidKt.m567isPressZmokQxo(event)) {
            if (this.currentKeyPressInteractions.containsKey(Key.m5321boximpl(KeyEvent_androidKt.m5632getKeyZmokQxo(event)))) {
                return false;
            }
            PressInteraction.Press press = new PressInteraction.Press(this.centerOffset, null);
            this.currentKeyPressInteractions.put(Key.m5321boximpl(KeyEvent_androidKt.m5632getKeyZmokQxo(event)), press);
            if (this.interactionSource != null) {
                BuildersKt__Builders_commonKt.launch$default(getCoroutineScope(), null, null, new AbstractClickableNode$onKeyEvent$1(this, press, null), 3, null);
            }
            return true;
        }
        if (!this.enabled || !Clickable_androidKt.m565isClickZmokQxo(event)) {
            return false;
        }
        PressInteraction.Press pressRemove = this.currentKeyPressInteractions.remove(Key.m5321boximpl(KeyEvent_androidKt.m5632getKeyZmokQxo(event)));
        if (pressRemove != null && this.interactionSource != null) {
            BuildersKt__Builders_commonKt.launch$default(getCoroutineScope(), null, null, new AbstractClickableNode$onKeyEvent$2$1(this, pressRemove, null), 3, null);
        }
        this.onClick.invoke();
        return true;
    }

    @Override // androidx.compose.ui.focus.FocusEventModifierNode
    public final void onFocusEvent(FocusState focusState) {
        if (focusState.isFocused()) {
            initializeIndicationAndInteractionSourceIfNeeded();
        }
        if (this.enabled) {
            this.focusableNode.onFocusEvent(focusState);
        }
    }

    @Override // androidx.compose.ui.node.SemanticsModifierNode
    public final void applySemantics(SemanticsPropertyReceiver semanticsPropertyReceiver) {
        Role role = this.role;
        if (role != null) {
            Intrinsics.checkNotNull(role);
            SemanticsPropertiesKt.m6341setRolekuIjeqM(semanticsPropertyReceiver, role.getValue());
        }
        SemanticsPropertiesKt.onClick(semanticsPropertyReceiver, this.onClickLabel, new Function0<Boolean>() { // from class: androidx.compose.foundation.AbstractClickableNode.applySemantics.1
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Boolean invoke() {
                AbstractClickableNode.this.getOnClick().invoke();
                return true;
            }
        });
        if (this.enabled) {
            this.focusableNode.applySemantics(semanticsPropertyReceiver);
        } else {
            SemanticsPropertiesKt.disabled(semanticsPropertyReceiver);
        }
        applyAdditionalSemantics(semanticsPropertyReceiver);
    }

    protected final Unit resetPointerInputHandler() {
        SuspendingPointerInputModifierNode suspendingPointerInputModifierNode = this.pointerInputNode;
        if (suspendingPointerInputModifierNode == null) {
            return null;
        }
        suspendingPointerInputModifierNode.resetPointerInputHandler();
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: handlePressInteraction-d-4ec7I, reason: not valid java name */
    protected final Object m491handlePressInteractiond4ec7I(PressGestureScope pressGestureScope, long j, Continuation<? super Unit> continuation) {
        Object objCoroutineScope;
        MutableInteractionSource mutableInteractionSource = this.interactionSource;
        return (mutableInteractionSource == null || (objCoroutineScope = CoroutineScopeKt.coroutineScope(new AbstractClickableNode$handlePressInteraction$2$1(pressGestureScope, j, mutableInteractionSource, this, null), continuation)) != IntrinsicsKt.getCOROUTINE_SUSPENDED()) ? Unit.INSTANCE : objCoroutineScope;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean delayPressInteraction() {
        return ClickableKt.hasScrollableContainer(this) || Clickable_androidKt.isComposeRootInScrollableContainer(this);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void emitHoverEnter() {
        if (this.hoverInteraction == null) {
            HoverInteraction.Enter enter = new HoverInteraction.Enter();
            MutableInteractionSource mutableInteractionSource = this.interactionSource;
            if (mutableInteractionSource != null) {
                BuildersKt__Builders_commonKt.launch$default(getCoroutineScope(), null, null, new AbstractClickableNode$emitHoverEnter$1$1(mutableInteractionSource, enter, null), 3, null);
            }
            this.hoverInteraction = enter;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void emitHoverExit() {
        HoverInteraction.Enter enter = this.hoverInteraction;
        if (enter != null) {
            HoverInteraction.Exit exit = new HoverInteraction.Exit(enter);
            MutableInteractionSource mutableInteractionSource = this.interactionSource;
            if (mutableInteractionSource != null) {
                BuildersKt__Builders_commonKt.launch$default(getCoroutineScope(), null, null, new AbstractClickableNode$emitHoverExit$1$1$1(mutableInteractionSource, exit, null), 3, null);
            }
            this.hoverInteraction = null;
        }
    }

    @Override // androidx.compose.ui.node.TraversableNode
    public Object getTraverseKey() {
        return this.traverseKey;
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.AbstractClickableNode$TraverseKey, reason: from kotlin metadata */
    /* JADX INFO: compiled from: Clickable.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Landroidx/compose/foundation/AbstractClickableNode$TraverseKey;", "", "()V", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
