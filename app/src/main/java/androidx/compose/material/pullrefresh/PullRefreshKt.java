package androidx.compose.material.pullrefresh;

import androidx.compose.ui.Modifier;
import androidx.compose.ui.input.nestedscroll.NestedScrollModifierKt;
import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.SuspendFunction;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.AdaptedFunctionReference;
import kotlin.jvm.internal.FunctionReferenceImpl;

/* JADX INFO: compiled from: PullRefresh.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u00006\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u001aq\u0010\u0000\u001a\u00020\u0001*\u00020\u00012!\u0010\u0002\u001a\u001d\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007\u0012\u0004\u0012\u00020\u00040\u000321\u0010\b\u001a-\b\u0001\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\n\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u000b\u0012\u0006\u0012\u0004\u0018\u00010\f0\t2\b\b\u0002\u0010\r\u001a\u00020\u000eH\u0007¢\u0006\u0002\u0010\u000f\u001a\u001e\u0010\u0000\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0010\u001a\u00020\u00112\b\b\u0002\u0010\r\u001a\u00020\u000eH\u0007¨\u0006\u0012"}, d2 = {"pullRefresh", "Landroidx/compose/ui/Modifier;", "onPull", "Lkotlin/Function1;", "", "Lkotlin/ParameterName;", "name", "pullDelta", "onRelease", "Lkotlin/Function2;", "flingVelocity", "Lkotlin/coroutines/Continuation;", "", "enabled", "", "(Landroidx/compose/ui/Modifier;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function2;Z)Landroidx/compose/ui/Modifier;", "state", "Landroidx/compose/material/pullrefresh/PullRefreshState;", "material_release"}, k = 2, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
public final class PullRefreshKt {
    public static /* synthetic */ Modifier pullRefresh$default(Modifier modifier, PullRefreshState pullRefreshState, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = true;
        }
        return pullRefresh(modifier, pullRefreshState, z);
    }

    /* JADX INFO: renamed from: androidx.compose.material.pullrefresh.PullRefreshKt$pullRefresh$1, reason: invalid class name */
    /* JADX INFO: compiled from: PullRefresh.kt */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    /* synthetic */ class AnonymousClass1 extends FunctionReferenceImpl implements Function1<Float, Float> {
        AnonymousClass1(Object obj) {
            super(1, obj, PullRefreshState.class, "onPull", "onPull$material_release(F)F", 0);
        }

        public final Float invoke(float f) {
            return Float.valueOf(((PullRefreshState) this.receiver).onPull$material_release(f));
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Float invoke(Float f) {
            return invoke(f.floatValue());
        }
    }

    /* JADX INFO: renamed from: androidx.compose.material.pullrefresh.PullRefreshKt$pullRefresh$2, reason: invalid class name */
    /* JADX INFO: compiled from: PullRefresh.kt */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    /* synthetic */ class AnonymousClass2 extends AdaptedFunctionReference implements Function2<Float, Continuation<? super Float>, Object>, SuspendFunction {
        AnonymousClass2(Object obj) {
            super(2, obj, PullRefreshState.class, "onRelease", "onRelease$material_release(F)F", 4);
        }

        public final Object invoke(float f, Continuation<? super Float> continuation) {
            return PullRefreshKt.pullRefresh$onRelease((PullRefreshState) this.receiver, f, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(Float f, Continuation<? super Float> continuation) {
            return invoke(f.floatValue(), continuation);
        }
    }

    public static final Modifier pullRefresh(Modifier modifier, PullRefreshState pullRefreshState, boolean z) {
        return pullRefresh(modifier, new AnonymousClass1(pullRefreshState), new AnonymousClass2(pullRefreshState), z);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final /* synthetic */ Object pullRefresh$onRelease(PullRefreshState pullRefreshState, float f, Continuation continuation) {
        return Boxing.boxFloat(pullRefreshState.onRelease$material_release(f));
    }

    public static /* synthetic */ Modifier pullRefresh$default(Modifier modifier, Function1 function1, Function2 function2, boolean z, int i, Object obj) {
        if ((i & 4) != 0) {
            z = true;
        }
        return pullRefresh(modifier, function1, function2, z);
    }

    public static final Modifier pullRefresh(Modifier modifier, Function1<? super Float, Float> function1, Function2<? super Float, ? super Continuation<? super Float>, ? extends Object> function2, boolean z) {
        return NestedScrollModifierKt.nestedScroll$default(modifier, new PullRefreshNestedScrollConnection(function1, function2, z), null, 2, null);
    }
}
