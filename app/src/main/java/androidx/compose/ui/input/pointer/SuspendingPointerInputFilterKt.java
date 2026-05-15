package androidx.compose.ui.input.pointer;

import androidx.compose.ui.Modifier;
import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function2;

/* JADX INFO: compiled from: SuspendingPointerInputFilter.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000F\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\u001a4\u0010\n\u001a\u00020\u000b2'\u0010\f\u001a#\b\u0001\u0012\u0004\u0012\u00020\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00100\u000f\u0012\u0006\u0012\u0004\u0018\u00010\u00110\r¢\u0006\u0002\b\u0012¢\u0006\u0002\u0010\u0013\u001aL\u0010\u0014\u001a\u00020\u0015*\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u00112\b\u0010\u0017\u001a\u0004\u0018\u00010\u00112'\u0010\u0018\u001a#\b\u0001\u0012\u0004\u0012\u00020\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00100\u000f\u0012\u0006\u0012\u0004\u0018\u00010\u00110\r¢\u0006\u0002\b\u0012¢\u0006\u0002\u0010\u0019\u001aB\u0010\u0014\u001a\u00020\u0015*\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u00112'\u0010\u0018\u001a#\b\u0001\u0012\u0004\u0012\u00020\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00100\u000f\u0012\u0006\u0012\u0004\u0018\u00010\u00110\r¢\u0006\u0002\b\u0012¢\u0006\u0002\u0010\u001a\u001aP\u0010\u0014\u001a\u00020\u0015*\u00020\u00152\u0016\u0010\u001b\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00110\u0003\"\u0004\u0018\u00010\u00112'\u0010\u0018\u001a#\b\u0001\u0012\u0004\u0012\u00020\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00100\u000f\u0012\u0006\u0012\u0004\u0018\u00010\u00110\r¢\u0006\u0002\b\u0012¢\u0006\u0002\u0010\u001c\u001a:\u0010\u0014\u001a\u00020\u0015*\u00020\u00152'\u0010\u0018\u001a#\b\u0001\u0012\u0004\u0012\u00020\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00100\u000f\u0012\u0006\u0012\u0004\u0018\u00010\u00110\r¢\u0006\u0002\b\u0012H\u0007¢\u0006\u0002\u0010\u001d\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"\u0016\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0005\"\u0014\u0010\u0006\u001a\u00020\u0007X\u0082T¢\u0006\b\n\u0000\u0012\u0004\b\b\u0010\t¨\u0006\u001e"}, d2 = {"EmptyPointerEvent", "Landroidx/compose/ui/input/pointer/PointerEvent;", "EmptyStackTraceElements", "", "Ljava/lang/StackTraceElement;", "[Ljava/lang/StackTraceElement;", "PointerInputModifierNoParamError", "", "getPointerInputModifierNoParamError$annotations", "()V", "SuspendingPointerInputModifierNode", "Landroidx/compose/ui/input/pointer/SuspendingPointerInputModifierNode;", "pointerInputHandler", "Lkotlin/Function2;", "Landroidx/compose/ui/input/pointer/PointerInputScope;", "Lkotlin/coroutines/Continuation;", "", "", "Lkotlin/ExtensionFunctionType;", "(Lkotlin/jvm/functions/Function2;)Landroidx/compose/ui/input/pointer/SuspendingPointerInputModifierNode;", "pointerInput", "Landroidx/compose/ui/Modifier;", "key1", "key2", "block", "(Landroidx/compose/ui/Modifier;Ljava/lang/Object;Ljava/lang/Object;Lkotlin/jvm/functions/Function2;)Landroidx/compose/ui/Modifier;", "(Landroidx/compose/ui/Modifier;Ljava/lang/Object;Lkotlin/jvm/functions/Function2;)Landroidx/compose/ui/Modifier;", "keys", "(Landroidx/compose/ui/Modifier;[Ljava/lang/Object;Lkotlin/jvm/functions/Function2;)Landroidx/compose/ui/Modifier;", "(Landroidx/compose/ui/Modifier;Lkotlin/jvm/functions/Function2;)Landroidx/compose/ui/Modifier;", "ui_release"}, k = 2, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
public final class SuspendingPointerInputFilterKt {
    private static final PointerEvent EmptyPointerEvent = new PointerEvent(CollectionsKt.emptyList());
    private static final StackTraceElement[] EmptyStackTraceElements = new StackTraceElement[0];
    private static final String PointerInputModifierNoParamError = "Modifier.pointerInput must provide one or more 'key' parameters that define the identity of the modifier and determine when its previous input processing coroutine should be cancelled and a new effect launched for the new key.";

    private static /* synthetic */ void getPointerInputModifierNoParamError$annotations() {
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = PointerInputModifierNoParamError)
    public static final Modifier pointerInput(Modifier modifier, Function2<? super PointerInputScope, ? super Continuation<? super Unit>, ? extends Object> function2) {
        throw new IllegalStateException(PointerInputModifierNoParamError.toString());
    }

    public static final Modifier pointerInput(Modifier modifier, Object obj, Function2<? super PointerInputScope, ? super Continuation<? super Unit>, ? extends Object> function2) {
        return modifier.then(new SuspendPointerInputElement(obj, null, null, function2, 6, null));
    }

    public static final Modifier pointerInput(Modifier modifier, Object obj, Object obj2, Function2<? super PointerInputScope, ? super Continuation<? super Unit>, ? extends Object> function2) {
        return modifier.then(new SuspendPointerInputElement(obj, obj2, null, function2, 4, null));
    }

    public static final Modifier pointerInput(Modifier modifier, Object[] objArr, Function2<? super PointerInputScope, ? super Continuation<? super Unit>, ? extends Object> function2) {
        return modifier.then(new SuspendPointerInputElement(null, null, objArr, function2, 3, null));
    }

    public static final SuspendingPointerInputModifierNode SuspendingPointerInputModifierNode(Function2<? super PointerInputScope, ? super Continuation<? super Unit>, ? extends Object> function2) {
        return new SuspendingPointerInputModifierNodeImpl(null, null, null, function2);
    }
}
