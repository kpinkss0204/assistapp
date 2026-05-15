package com.google.accompanist.pager;

import androidx.compose.foundation.gestures.ScrollScope;
import androidx.compose.foundation.lazy.LazyListItemInfo;
import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;

/* JADX INFO: compiled from: PagerState.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Landroidx/compose/foundation/gestures/ScrollScope;"}, k = 3, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
@DebugMetadata(c = "com.google.accompanist.pager.PagerState$scrollToPage$2$1", f = "PagerState.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
final class PagerState$scrollToPage$2$1 extends SuspendLambda implements Function2<ScrollScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ LazyListItemInfo $it;
    final /* synthetic */ float $pageOffset;
    private /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ PagerState this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    PagerState$scrollToPage$2$1(LazyListItemInfo lazyListItemInfo, PagerState pagerState, float f, Continuation<? super PagerState$scrollToPage$2$1> continuation) {
        super(2, continuation);
        this.$it = lazyListItemInfo;
        this.this$0 = pagerState;
        this.$pageOffset = f;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        PagerState$scrollToPage$2$1 pagerState$scrollToPage$2$1 = new PagerState$scrollToPage$2$1(this.$it, this.this$0, this.$pageOffset, continuation);
        pagerState$scrollToPage$2$1.L$0 = obj;
        return pagerState$scrollToPage$2$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(ScrollScope scrollScope, Continuation<? super Unit> continuation) {
        return ((PagerState$scrollToPage$2$1) create(scrollScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        ((ScrollScope) this.L$0).scrollBy((this.$it.getSize() + this.this$0.getItemSpacing$pager_release()) * this.$pageOffset);
        return Unit.INSTANCE;
    }
}
