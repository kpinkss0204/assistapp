package com.google.accompanist.pager;

import androidx.compose.foundation.lazy.LazyListItemInfo;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;

/* JADX INFO: compiled from: Pager.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(k = 3, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
@DebugMetadata(c = "com.google.accompanist.pager.Pager$Pager$5$1", f = "Pager.kt", i = {}, l = {406}, m = "invokeSuspend", n = {}, s = {})
final class Pager$Pager$5$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ PagerState $state;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    Pager$Pager$5$1(PagerState pagerState, Continuation<? super Pager$Pager$5$1> continuation) {
        super(2, continuation);
        this.$state = pagerState;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new Pager$Pager$5$1(this.$state, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((Pager$Pager$5$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            final PagerState pagerState = this.$state;
            Flow flowDistinctUntilChanged = FlowKt.distinctUntilChanged(SnapshotStateKt.snapshotFlow(new Function0<Integer>() { // from class: com.google.accompanist.pager.Pager$Pager$5$1.1
                {
                    super(0);
                }

                /* JADX WARN: Can't rename method to resolve collision */
                @Override // kotlin.jvm.functions.Function0
                public final Integer invoke() {
                    LazyListItemInfo mostVisiblePageLayoutInfo$pager_release = pagerState.getMostVisiblePageLayoutInfo$pager_release();
                    if (mostVisiblePageLayoutInfo$pager_release != null) {
                        return Integer.valueOf(mostVisiblePageLayoutInfo$pager_release.getIndex());
                    }
                    return null;
                }
            }));
            final PagerState pagerState2 = this.$state;
            this.label = 1;
            if (flowDistinctUntilChanged.collect(new FlowCollector<Integer>() { // from class: com.google.accompanist.pager.Pager$Pager$5$1.2
                /* JADX INFO: renamed from: emit, reason: avoid collision after fix types in other method */
                public final Object emit2(Integer num, Continuation<? super Unit> continuation) {
                    pagerState2.updateCurrentPageBasedOnLazyListState$pager_release();
                    return Unit.INSTANCE;
                }

                @Override // kotlinx.coroutines.flow.FlowCollector
                public /* bridge */ /* synthetic */ Object emit(Integer num, Continuation continuation) {
                    return emit2(num, (Continuation<? super Unit>) continuation);
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
        return Unit.INSTANCE;
    }
}
