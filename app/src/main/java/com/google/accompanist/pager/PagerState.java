package com.google.accompanist.pager;

import androidx.compose.animation.core.AnimationSpec;
import androidx.compose.animation.core.AnimationSpecKt;
import androidx.compose.foundation.MutatePriority;
import androidx.compose.foundation.gestures.ScrollScope;
import androidx.compose.foundation.gestures.ScrollableState;
import androidx.compose.foundation.interaction.InteractionSource;
import androidx.compose.foundation.lazy.LazyListItemInfo;
import androidx.compose.foundation.lazy.LazyListLayoutInfo;
import androidx.compose.foundation.lazy.LazyListState;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.runtime.saveable.ListSaverKt;
import androidx.compose.runtime.saveable.Saver;
import androidx.compose.runtime.saveable.SaverScope;
import androidx.constraintlayout.widget.ConstraintLayout;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;

/* JADX INFO: compiled from: PagerState.kt */
/* JADX INFO: loaded from: classes2.dex */
@Deprecated(message = "\naccompanist/pager is deprecated.\nThe androidx.compose equivalent of Insets is Pager.\nFor more migration information, please visit https://google.github.io/accompanist/pager/#migration\n", replaceWith = @ReplaceWith(expression = "PagerState(currentPage = currentPage)", imports = {"androidx.compose.foundation.pager.PagerState"}))
@Metadata(d1 = {"\u0000x\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0012\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0007\u0018\u0000 _2\u00020\u0001:\u0001_B\u000f\u0012\b\b\u0003\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J%\u0010?\u001a\u00020@2\b\b\u0001\u0010A\u001a\u00020\u00032\b\b\u0003\u0010B\u001a\u00020\u001aH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010CJI\u0010?\u001a\u00020@2\b\b\u0001\u0010A\u001a\u00020\u00032\b\b\u0003\u0010B\u001a\u00020\u001a2\u000e\b\u0002\u0010D\u001a\b\u0012\u0004\u0012\u00020\u001a0E2\b\b\u0002\u0010F\u001a\u00020\u001a2\b\b\u0002\u0010G\u001a\u00020+H\u0087@ø\u0001\u0000¢\u0006\u0002\u0010HJ\u0010\u0010I\u001a\u00020\u001a2\u0006\u0010J\u001a\u00020\u001aH\u0016J\r\u0010K\u001a\u00020@H\u0000¢\u0006\u0002\bLJ\u0018\u0010M\u001a\u00020@2\u0006\u0010\u0012\u001a\u00020\u00032\u0006\u0010N\u001a\u00020OH\u0002J\u0018\u0010P\u001a\u00020@2\u0006\u0010\u0012\u001a\u00020\u001a2\u0006\u0010N\u001a\u00020OH\u0002JB\u0010Q\u001a\u00020@2\u0006\u0010R\u001a\u00020S2'\u0010T\u001a#\b\u0001\u0012\u0004\u0012\u00020V\u0012\n\u0012\b\u0012\u0004\u0012\u00020@0W\u0012\u0006\u0012\u0004\u0018\u00010X0U¢\u0006\u0002\bYH\u0096@ø\u0001\u0000¢\u0006\u0002\u0010ZJ%\u0010[\u001a\u00020@2\b\b\u0001\u0010A\u001a\u00020\u00032\b\b\u0003\u0010B\u001a\u00020\u001aH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010CJ\b\u0010\\\u001a\u00020OH\u0016J\r\u0010]\u001a\u00020@H\u0000¢\u0006\u0002\b^R+\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u00038B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\b\n\u0010\u000b\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\u0004R/\u0010\f\u001a\u0004\u0018\u00010\u00032\b\u0010\u0005\u001a\u0004\u0018\u00010\u00038B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\b\u0011\u0010\u000b\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R$\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0012\u001a\u00020\u00038G@@X\u0086\u000e¢\u0006\f\u001a\u0004\b\u0013\u0010\b\"\u0004\b\u0014\u0010\u0004R\u0016\u0010\u0015\u001a\u0004\u0018\u00010\u00168BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0017\u0010\u0018R\u001b\u0010\u0019\u001a\u00020\u001a8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u001d\u0010\u001e\u001a\u0004\b\u001b\u0010\u001cR?\u0010 \u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0003\u0018\u00010\u001f2\u0010\u0010\u0005\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0003\u0018\u00010\u001f8@@@X\u0080\u008e\u0002¢\u0006\u0012\n\u0004\b%\u0010\u000b\u001a\u0004\b!\u0010\"\"\u0004\b#\u0010$R\u0011\u0010&\u001a\u00020'8F¢\u0006\u0006\u001a\u0004\b(\u0010)R\u0014\u0010*\u001a\u00020+8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b*\u0010,R+\u0010-\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u00038@@@X\u0080\u008e\u0002¢\u0006\u0012\n\u0004\b0\u0010\u000b\u001a\u0004\b.\u0010\b\"\u0004\b/\u0010\u0004R\u0014\u00101\u001a\u000202X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b3\u00104R\u0016\u00105\u001a\u0004\u0018\u00010\u00168@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b6\u0010\u0018R!\u00107\u001a\u00020\u00038GX\u0087\u0084\u0002¢\u0006\u0012\n\u0004\b;\u0010\u001e\u0012\u0004\b8\u00109\u001a\u0004\b:\u0010\bR\u001a\u0010<\u001a\u00020\u00038FX\u0087\u0004¢\u0006\f\u0012\u0004\b=\u00109\u001a\u0004\b>\u0010\b\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006`"}, d2 = {"Lcom/google/accompanist/pager/PagerState;", "Landroidx/compose/foundation/gestures/ScrollableState;", "currentPage", "", "(I)V", "<set-?>", "_currentPage", "get_currentPage", "()I", "set_currentPage", "_currentPage$delegate", "Landroidx/compose/runtime/MutableState;", "animationTargetPage", "getAnimationTargetPage", "()Ljava/lang/Integer;", "setAnimationTargetPage", "(Ljava/lang/Integer;)V", "animationTargetPage$delegate", "value", "getCurrentPage", "setCurrentPage$pager_release", "currentPageLayoutInfo", "Landroidx/compose/foundation/lazy/LazyListItemInfo;", "getCurrentPageLayoutInfo", "()Landroidx/compose/foundation/lazy/LazyListItemInfo;", "currentPageOffset", "", "getCurrentPageOffset", "()F", "currentPageOffset$delegate", "Landroidx/compose/runtime/State;", "Lkotlin/Function0;", "flingAnimationTarget", "getFlingAnimationTarget$pager_release", "()Lkotlin/jvm/functions/Function0;", "setFlingAnimationTarget$pager_release", "(Lkotlin/jvm/functions/Function0;)V", "flingAnimationTarget$delegate", "interactionSource", "Landroidx/compose/foundation/interaction/InteractionSource;", "getInteractionSource", "()Landroidx/compose/foundation/interaction/InteractionSource;", "isScrollInProgress", "", "()Z", "itemSpacing", "getItemSpacing$pager_release", "setItemSpacing$pager_release", "itemSpacing$delegate", "lazyListState", "Landroidx/compose/foundation/lazy/LazyListState;", "getLazyListState$pager_release", "()Landroidx/compose/foundation/lazy/LazyListState;", "mostVisiblePageLayoutInfo", "getMostVisiblePageLayoutInfo$pager_release", "pageCount", "getPageCount$annotations", "()V", "getPageCount", "pageCount$delegate", "targetPage", "getTargetPage$annotations", "getTargetPage", "animateScrollToPage", "", "page", "pageOffset", "(IFLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "animationSpec", "Landroidx/compose/animation/core/AnimationSpec;", "initialVelocity", "skipPages", "(IFLandroidx/compose/animation/core/AnimationSpec;FZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "dispatchRawDelta", "delta", "onScrollFinished", "onScrollFinished$pager_release", "requireCurrentPage", "name", "", "requireCurrentPageOffset", "scroll", "scrollPriority", "Landroidx/compose/foundation/MutatePriority;", "block", "Lkotlin/Function2;", "Landroidx/compose/foundation/gestures/ScrollScope;", "Lkotlin/coroutines/Continuation;", "", "Lkotlin/ExtensionFunctionType;", "(Landroidx/compose/foundation/MutatePriority;Lkotlin/jvm/functions/Function2;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "scrollToPage", "toString", "updateCurrentPageBasedOnLazyListState", "updateCurrentPageBasedOnLazyListState$pager_release", "Companion", "pager_release"}, k = 1, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
public final class PagerState implements ScrollableState {
    public static final int $stable = 0;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final Saver<PagerState, ?> Saver = ListSaverKt.listSaver(new Function2<SaverScope, PagerState, List<? extends Object>>() { // from class: com.google.accompanist.pager.PagerState$Companion$Saver$1
        @Override // kotlin.jvm.functions.Function2
        public final List<Object> invoke(SaverScope listSaver, PagerState it) {
            Intrinsics.checkNotNullParameter(listSaver, "$this$listSaver");
            Intrinsics.checkNotNullParameter(it, "it");
            return CollectionsKt.listOf(Integer.valueOf(it.getCurrentPage()));
        }
    }, new Function1<List<? extends Object>, PagerState>() { // from class: com.google.accompanist.pager.PagerState$Companion$Saver$2
        @Override // kotlin.jvm.functions.Function1
        public final PagerState invoke(List<? extends Object> it) {
            Intrinsics.checkNotNullParameter(it, "it");
            Object obj = it.get(0);
            Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type kotlin.Int");
            return new PagerState(((Integer) obj).intValue());
        }
    });

    /* JADX INFO: renamed from: _currentPage$delegate, reason: from kotlin metadata */
    private final MutableState _currentPage;

    /* JADX INFO: renamed from: animationTargetPage$delegate, reason: from kotlin metadata */
    private final MutableState animationTargetPage;

    /* JADX INFO: renamed from: currentPageOffset$delegate, reason: from kotlin metadata */
    private final State currentPageOffset;

    /* JADX INFO: renamed from: flingAnimationTarget$delegate, reason: from kotlin metadata */
    private final MutableState flingAnimationTarget;

    /* JADX INFO: renamed from: itemSpacing$delegate, reason: from kotlin metadata */
    private final MutableState itemSpacing;
    private final LazyListState lazyListState;

    /* JADX INFO: renamed from: pageCount$delegate, reason: from kotlin metadata */
    private final State pageCount;

    /* JADX INFO: renamed from: com.google.accompanist.pager.PagerState$animateScrollToPage$2, reason: invalid class name */
    /* JADX INFO: compiled from: PagerState.kt */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    @DebugMetadata(c = "com.google.accompanist.pager.PagerState", f = "PagerState.kt", i = {0, 0, 0, 1, 2, 2, 2, 3, 4, 4, 4, 4, 5}, l = {239, 244, 247, 255, 262, 274}, m = "animateScrollToPage", n = {"this", "page", "pageOffset", "this", "this", "page", "pageOffset", "this", "this", "page", "pageOffset", "currentSize", "this"}, s = {"L$0", "I$0", "F$0", "L$0", "L$0", "I$0", "F$0", "L$0", "L$0", "I$0", "F$0", "I$1", "L$0"})
    static final class AnonymousClass2 extends ContinuationImpl {
        float F$0;
        int I$0;
        int I$1;
        Object L$0;
        int label;
        /* synthetic */ Object result;

        AnonymousClass2(Continuation<? super AnonymousClass2> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return PagerState.this.animateScrollToPage(0, 0.0f, this);
        }
    }

    /* JADX INFO: renamed from: com.google.accompanist.pager.PagerState$scrollToPage$1, reason: invalid class name */
    /* JADX INFO: compiled from: PagerState.kt */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    @DebugMetadata(c = "com.google.accompanist.pager.PagerState", f = "PagerState.kt", i = {0, 0, 1}, l = {309, 315}, m = "scrollToPage", n = {"this", "pageOffset", "this"}, s = {"L$0", "F$0", "L$0"})
    static final class AnonymousClass1 extends ContinuationImpl {
        float F$0;
        Object L$0;
        int label;
        /* synthetic */ Object result;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return PagerState.this.scrollToPage(0, 0.0f, this);
        }
    }

    public PagerState() {
        this(0, 1, null);
    }

    @Deprecated(message = "pageCount is deprecated, use androidx.compose.foundation.pager.PagerState.canScrollForward or androidx.compose.foundation.pager.PagerState.canScrollBackward")
    public static /* synthetic */ void getPageCount$annotations() {
    }

    @Deprecated(message = "targetPage is deprecated in favor of currentPage as currentPage property isnow being updated right after we over scrolled the half of the previous current page.If you still think that you need targetPage, not currentPage please file a bug as we are planning to remove this property in future.", replaceWith = @ReplaceWith(expression = "currentPage", imports = {}))
    public static /* synthetic */ void getTargetPage$annotations() {
    }

    public PagerState(int i) {
        this.lazyListState = new LazyListState(i, 0, 2, null);
        this._currentPage = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(Integer.valueOf(i), null, 2, null);
        this.itemSpacing = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(0, null, 2, null);
        this.pageCount = SnapshotStateKt.derivedStateOf(new Function0<Integer>() { // from class: com.google.accompanist.pager.PagerState$pageCount$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Integer invoke() {
                return Integer.valueOf(this.this$0.getLazyListState().getLayoutInfo().getTotalItemsCount());
            }
        });
        this.currentPageOffset = SnapshotStateKt.derivedStateOf(new Function0<Float>() { // from class: com.google.accompanist.pager.PagerState$currentPageOffset$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Float invoke() {
                float fCoerceIn;
                if (this.this$0.getCurrentPageLayoutInfo() != null) {
                    fCoerceIn = RangesKt.coerceIn((-r0.getOffset()) / (r0.getSize() + this.this$0.getItemSpacing$pager_release()), -0.5f, 0.5f);
                } else {
                    fCoerceIn = 0.0f;
                }
                return Float.valueOf(fCoerceIn);
            }
        });
        this.animationTargetPage = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(null, null, 2, null);
        this.flingAnimationTarget = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(null, null, 2, null);
    }

    public /* synthetic */ PagerState(int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? 0 : i);
    }

    /* JADX INFO: renamed from: getLazyListState$pager_release, reason: from getter */
    public final LazyListState getLazyListState() {
        return this.lazyListState;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final int get_currentPage() {
        return ((Number) this._currentPage.getValue()).intValue();
    }

    private final void set_currentPage(int i) {
        this._currentPage.setValue(Integer.valueOf(i));
    }

    public final LazyListItemInfo getMostVisiblePageLayoutInfo$pager_release() {
        Object obj;
        LazyListLayoutInfo layoutInfo = this.lazyListState.getLayoutInfo();
        Iterator<T> it = layoutInfo.getVisibleItemsInfo().iterator();
        if (it.hasNext()) {
            Object next = it.next();
            if (it.hasNext()) {
                LazyListItemInfo lazyListItemInfo = (LazyListItemInfo) next;
                int iMin = Math.min(lazyListItemInfo.getOffset() + lazyListItemInfo.getSize(), layoutInfo.getViewportEndOffset() - layoutInfo.getAfterContentPadding()) - Math.max(lazyListItemInfo.getOffset(), 0);
                do {
                    Object next2 = it.next();
                    LazyListItemInfo lazyListItemInfo2 = (LazyListItemInfo) next2;
                    int iMin2 = Math.min(lazyListItemInfo2.getOffset() + lazyListItemInfo2.getSize(), layoutInfo.getViewportEndOffset() - layoutInfo.getAfterContentPadding()) - Math.max(lazyListItemInfo2.getOffset(), 0);
                    if (iMin < iMin2) {
                        next = next2;
                        iMin = iMin2;
                    }
                } while (it.hasNext());
            }
            obj = next;
        } else {
            obj = null;
        }
        return (LazyListItemInfo) obj;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final int getItemSpacing$pager_release() {
        return ((Number) this.itemSpacing.getValue()).intValue();
    }

    public final void setItemSpacing$pager_release(int i) {
        this.itemSpacing.setValue(Integer.valueOf(i));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final LazyListItemInfo getCurrentPageLayoutInfo() {
        LazyListItemInfo lazyListItemInfoPrevious;
        List<LazyListItemInfo> visibleItemsInfo = this.lazyListState.getLayoutInfo().getVisibleItemsInfo();
        ListIterator<LazyListItemInfo> listIterator = visibleItemsInfo.listIterator(visibleItemsInfo.size());
        while (true) {
            if (!listIterator.hasPrevious()) {
                lazyListItemInfoPrevious = null;
                break;
            }
            lazyListItemInfoPrevious = listIterator.previous();
            if (lazyListItemInfoPrevious.getIndex() == getCurrentPage()) {
                break;
            }
        }
        return lazyListItemInfoPrevious;
    }

    public final InteractionSource getInteractionSource() {
        return this.lazyListState.getInteractionSource();
    }

    public final int getPageCount() {
        return ((Number) this.pageCount.getValue()).intValue();
    }

    public final int getCurrentPage() {
        return get_currentPage();
    }

    public final void setCurrentPage$pager_release(int i) {
        if (i != get_currentPage()) {
            set_currentPage(i);
        }
    }

    public final float getCurrentPageOffset() {
        return ((Number) this.currentPageOffset.getValue()).floatValue();
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final Integer getAnimationTargetPage() {
        return (Integer) this.animationTargetPage.getValue();
    }

    private final void setAnimationTargetPage(Integer num) {
        this.animationTargetPage.setValue(num);
    }

    public final Function0<Integer> getFlingAnimationTarget$pager_release() {
        return (Function0) this.flingAnimationTarget.getValue();
    }

    public final void setFlingAnimationTarget$pager_release(Function0<Integer> function0) {
        this.flingAnimationTarget.setValue(function0);
    }

    public final int getTargetPage() {
        Integer animationTargetPage = getAnimationTargetPage();
        if (animationTargetPage != null) {
            return animationTargetPage.intValue();
        }
        Function0<Integer> flingAnimationTarget$pager_release = getFlingAnimationTarget$pager_release();
        Integer numInvoke = flingAnimationTarget$pager_release != null ? flingAnimationTarget$pager_release.invoke() : null;
        if (numInvoke != null) {
            return numInvoke.intValue();
        }
        if (isScrollInProgress() && Math.abs(getCurrentPageOffset()) >= 0.001f) {
            return getCurrentPageOffset() < 0.0f ? RangesKt.coerceAtLeast(getCurrentPage() - 1, 0) : RangesKt.coerceAtMost(getCurrentPage() + 1, getPageCount() - 1);
        }
        return getCurrentPage();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ Object animateScrollToPage$default(PagerState pagerState, int i, float f, AnimationSpec animationSpec, float f2, boolean z, Continuation continuation, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            f = 0.0f;
        }
        if ((i2 & 4) != 0) {
            animationSpec = AnimationSpecKt.spring$default(0.0f, 0.0f, null, 7, null);
        }
        if ((i2 & 8) != 0) {
            f2 = 0.0f;
        }
        if ((i2 & 16) != 0) {
            z = true;
        }
        return pagerState.animateScrollToPage(i, f, animationSpec, f2, z, continuation);
    }

    @Deprecated(message = "Replaced with animateScrollToPage(page, pageOffset)", replaceWith = @ReplaceWith(expression = "animateScrollToPage(page = page, pageOffset = pageOffset)", imports = {}))
    public final Object animateScrollToPage(int i, float f, AnimationSpec<Float> animationSpec, float f2, boolean z, Continuation<? super Unit> continuation) throws Throwable {
        Object objAnimateScrollToPage = animateScrollToPage(i, f, continuation);
        return objAnimateScrollToPage == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objAnimateScrollToPage : Unit.INSTANCE;
    }

    public static /* synthetic */ Object animateScrollToPage$default(PagerState pagerState, int i, float f, Continuation continuation, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            f = 0.0f;
        }
        return pagerState.animateScrollToPage(i, f, continuation);
    }

    /* JADX WARN: Code restructure failed: missing block: B:80:0x01c8, code lost:
    
        if (r13.animateScrollToItem(r1, r12, r4) != r0) goto L82;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:45:0x00c8 A[Catch: all -> 0x005f, TryCatch #3 {all -> 0x005f, blocks: (B:69:0x017b, B:70:0x018b, B:72:0x0191, B:75:0x019f, B:77:0x01a3, B:79:0x01ae, B:52:0x00fb, B:53:0x010b, B:55:0x0111, B:59:0x0120, B:61:0x0124, B:64:0x0142, B:66:0x014e, B:21:0x005a, B:43:0x00bd, B:45:0x00c8, B:48:0x00da), top: B:92:0x0024 }] */
    /* JADX WARN: Removed duplicated region for block: B:48:0x00da A[Catch: all -> 0x005f, TryCatch #3 {all -> 0x005f, blocks: (B:69:0x017b, B:70:0x018b, B:72:0x0191, B:75:0x019f, B:77:0x01a3, B:79:0x01ae, B:52:0x00fb, B:53:0x010b, B:55:0x0111, B:59:0x0120, B:61:0x0124, B:64:0x0142, B:66:0x014e, B:21:0x005a, B:43:0x00bd, B:45:0x00c8, B:48:0x00da), top: B:92:0x0024 }] */
    /* JADX WARN: Removed duplicated region for block: B:55:0x0111 A[Catch: all -> 0x005f, TryCatch #3 {all -> 0x005f, blocks: (B:69:0x017b, B:70:0x018b, B:72:0x0191, B:75:0x019f, B:77:0x01a3, B:79:0x01ae, B:52:0x00fb, B:53:0x010b, B:55:0x0111, B:59:0x0120, B:61:0x0124, B:64:0x0142, B:66:0x014e, B:21:0x005a, B:43:0x00bd, B:45:0x00c8, B:48:0x00da), top: B:92:0x0024 }] */
    /* JADX WARN: Removed duplicated region for block: B:61:0x0124 A[Catch: all -> 0x005f, TryCatch #3 {all -> 0x005f, blocks: (B:69:0x017b, B:70:0x018b, B:72:0x0191, B:75:0x019f, B:77:0x01a3, B:79:0x01ae, B:52:0x00fb, B:53:0x010b, B:55:0x0111, B:59:0x0120, B:61:0x0124, B:64:0x0142, B:66:0x014e, B:21:0x005a, B:43:0x00bd, B:45:0x00c8, B:48:0x00da), top: B:92:0x0024 }] */
    /* JADX WARN: Removed duplicated region for block: B:64:0x0142 A[Catch: all -> 0x005f, TryCatch #3 {all -> 0x005f, blocks: (B:69:0x017b, B:70:0x018b, B:72:0x0191, B:75:0x019f, B:77:0x01a3, B:79:0x01ae, B:52:0x00fb, B:53:0x010b, B:55:0x0111, B:59:0x0120, B:61:0x0124, B:64:0x0142, B:66:0x014e, B:21:0x005a, B:43:0x00bd, B:45:0x00c8, B:48:0x00da), top: B:92:0x0024 }] */
    /* JADX WARN: Removed duplicated region for block: B:72:0x0191 A[Catch: all -> 0x005f, TryCatch #3 {all -> 0x005f, blocks: (B:69:0x017b, B:70:0x018b, B:72:0x0191, B:75:0x019f, B:77:0x01a3, B:79:0x01ae, B:52:0x00fb, B:53:0x010b, B:55:0x0111, B:59:0x0120, B:61:0x0124, B:64:0x0142, B:66:0x014e, B:21:0x005a, B:43:0x00bd, B:45:0x00c8, B:48:0x00da), top: B:92:0x0024 }] */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0014  */
    /* JADX WARN: Removed duplicated region for block: B:93:0x019f A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:96:0x011f A[SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r1v0, types: [int] */
    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object animateScrollToPage(int r11, float r12, kotlin.coroutines.Continuation<? super kotlin.Unit> r13) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 490
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.accompanist.pager.PagerState.animateScrollToPage(int, float, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: renamed from: com.google.accompanist.pager.PagerState$animateScrollToPage$3, reason: invalid class name */
    /* JADX INFO: compiled from: PagerState.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Landroidx/compose/foundation/gestures/ScrollScope;"}, k = 3, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    @DebugMetadata(c = "com.google.accompanist.pager.PagerState$animateScrollToPage$3", f = "PagerState.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class AnonymousClass3 extends SuspendLambda implements Function2<ScrollScope, Continuation<? super Unit>, Object> {
        int label;

        AnonymousClass3(Continuation<? super AnonymousClass3> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass3(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(ScrollScope scrollScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass3) create(scrollScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            return Unit.INSTANCE;
        }
    }

    public static /* synthetic */ Object scrollToPage$default(PagerState pagerState, int i, float f, Continuation continuation, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            f = 0.0f;
        }
        return pagerState.scrollToPage(i, f, continuation);
    }

    /* JADX WARN: Code restructure failed: missing block: B:32:0x009a, code lost:
    
        if (androidx.compose.foundation.gestures.ScrollableState.scroll$default(r9, null, r3, r4, 1, null) == r0) goto L33;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0014  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object scrollToPage(int r9, float r10, kotlin.coroutines.Continuation<? super kotlin.Unit> r11) throws java.lang.Throwable {
        /*
            r8 = this;
            boolean r0 = r11 instanceof com.google.accompanist.pager.PagerState.AnonymousClass1
            if (r0 == 0) goto L14
            r0 = r11
            com.google.accompanist.pager.PagerState$scrollToPage$1 r0 = (com.google.accompanist.pager.PagerState.AnonymousClass1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r11 = r0.label
            int r11 = r11 - r2
            r0.label = r11
            goto L19
        L14:
            com.google.accompanist.pager.PagerState$scrollToPage$1 r0 = new com.google.accompanist.pager.PagerState$scrollToPage$1
            r0.<init>(r11)
        L19:
            r4 = r0
            java.lang.Object r11 = r4.result
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r4.label
            r7 = 2
            r2 = 1
            if (r1 == 0) goto L47
            if (r1 == r2) goto L3a
            if (r1 != r7) goto L32
            java.lang.Object r9 = r4.L$0
            com.google.accompanist.pager.PagerState r9 = (com.google.accompanist.pager.PagerState) r9
            kotlin.ResultKt.throwOnFailure(r11)     // Catch: java.lang.Throwable -> L44
            goto L9d
        L32:
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
            java.lang.String r10 = "call to 'resume' before 'invoke' with coroutine"
            r9.<init>(r10)
            throw r9
        L3a:
            float r10 = r4.F$0
            java.lang.Object r9 = r4.L$0
            com.google.accompanist.pager.PagerState r9 = (com.google.accompanist.pager.PagerState) r9
            kotlin.ResultKt.throwOnFailure(r11)     // Catch: java.lang.Throwable -> L44
            goto L6f
        L44:
            r0 = move-exception
            r10 = r0
            goto La6
        L47:
            kotlin.ResultKt.throwOnFailure(r11)
            java.lang.String r11 = "page"
            r8.requireCurrentPage(r9, r11)
            java.lang.String r11 = "pageOffset"
            r8.requireCurrentPageOffset(r10, r11)
            java.lang.Integer r11 = kotlin.coroutines.jvm.internal.Boxing.boxInt(r9)     // Catch: java.lang.Throwable -> La3
            r8.setAnimationTargetPage(r11)     // Catch: java.lang.Throwable -> La3
            androidx.compose.foundation.lazy.LazyListState r1 = r8.lazyListState     // Catch: java.lang.Throwable -> La3
            r4.L$0 = r8     // Catch: java.lang.Throwable -> La3
            r4.F$0 = r10     // Catch: java.lang.Throwable -> La3
            r4.label = r2     // Catch: java.lang.Throwable -> La3
            r3 = 0
            r5 = 2
            r6 = 0
            r2 = r9
            java.lang.Object r9 = androidx.compose.foundation.lazy.LazyListState.scrollToItem$default(r1, r2, r3, r4, r5, r6)     // Catch: java.lang.Throwable -> La3
            if (r9 != r0) goto L6e
            goto L9c
        L6e:
            r9 = r8
        L6f:
            r9.updateCurrentPageBasedOnLazyListState$pager_release()     // Catch: java.lang.Throwable -> L44
            float r11 = java.lang.Math.abs(r10)     // Catch: java.lang.Throwable -> L44
            r1 = 953267991(0x38d1b717, float:1.0E-4)
            int r11 = (r11 > r1 ? 1 : (r11 == r1 ? 0 : -1))
            if (r11 <= 0) goto L9d
            androidx.compose.foundation.lazy.LazyListItemInfo r11 = r9.getCurrentPageLayoutInfo()     // Catch: java.lang.Throwable -> L44
            if (r11 == 0) goto L9d
            r1 = r9
            androidx.compose.foundation.gestures.ScrollableState r1 = (androidx.compose.foundation.gestures.ScrollableState) r1     // Catch: java.lang.Throwable -> L44
            com.google.accompanist.pager.PagerState$scrollToPage$2$1 r2 = new com.google.accompanist.pager.PagerState$scrollToPage$2$1     // Catch: java.lang.Throwable -> L44
            r3 = 0
            r2.<init>(r11, r9, r10, r3)     // Catch: java.lang.Throwable -> L44
            r3 = r2
            kotlin.jvm.functions.Function2 r3 = (kotlin.jvm.functions.Function2) r3     // Catch: java.lang.Throwable -> L44
            r4.L$0 = r9     // Catch: java.lang.Throwable -> L44
            r4.label = r7     // Catch: java.lang.Throwable -> L44
            r2 = 0
            r5 = 1
            r6 = 0
            java.lang.Object r10 = androidx.compose.foundation.gestures.ScrollableState.scroll$default(r1, r2, r3, r4, r5, r6)     // Catch: java.lang.Throwable -> L44
            if (r10 != r0) goto L9d
        L9c:
            return r0
        L9d:
            r9.onScrollFinished$pager_release()
            kotlin.Unit r9 = kotlin.Unit.INSTANCE
            return r9
        La3:
            r0 = move-exception
            r10 = r0
            r9 = r8
        La6:
            r9.onScrollFinished$pager_release()
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.accompanist.pager.PagerState.scrollToPage(int, float, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public final void updateCurrentPageBasedOnLazyListState$pager_release() {
        LazyListItemInfo mostVisiblePageLayoutInfo$pager_release = getMostVisiblePageLayoutInfo$pager_release();
        if (mostVisiblePageLayoutInfo$pager_release != null) {
            setCurrentPage$pager_release(mostVisiblePageLayoutInfo$pager_release.getIndex());
        }
    }

    public final void onScrollFinished$pager_release() {
        setAnimationTargetPage(null);
    }

    @Override // androidx.compose.foundation.gestures.ScrollableState
    public Object scroll(MutatePriority mutatePriority, Function2<? super ScrollScope, ? super Continuation<? super Unit>, ? extends Object> function2, Continuation<? super Unit> continuation) {
        Object objScroll = this.lazyListState.scroll(mutatePriority, function2, continuation);
        return objScroll == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objScroll : Unit.INSTANCE;
    }

    @Override // androidx.compose.foundation.gestures.ScrollableState
    public float dispatchRawDelta(float delta) {
        return this.lazyListState.dispatchRawDelta(delta);
    }

    @Override // androidx.compose.foundation.gestures.ScrollableState
    public boolean isScrollInProgress() {
        return this.lazyListState.isScrollInProgress();
    }

    public String toString() {
        return "PagerState(pageCount=" + getPageCount() + ", currentPage=" + getCurrentPage() + ", currentPageOffset=" + getCurrentPageOffset() + ')';
    }

    private final void requireCurrentPage(int value, String name) {
        if (value < 0) {
            throw new IllegalArgumentException((name + '[' + value + "] must be >= 0").toString());
        }
    }

    private final void requireCurrentPageOffset(float value, String name) {
        if (-1.0f > value || value > 1.0f) {
            throw new IllegalArgumentException((name + " must be >= -1 and <= 1").toString());
        }
    }

    /* JADX INFO: compiled from: PagerState.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u001b\u0010\u0003\u001a\f\u0012\u0004\u0012\u00020\u0005\u0012\u0002\b\u00030\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lcom/google/accompanist/pager/PagerState$Companion;", "", "()V", "Saver", "Landroidx/compose/runtime/saveable/Saver;", "Lcom/google/accompanist/pager/PagerState;", "getSaver", "()Landroidx/compose/runtime/saveable/Saver;", "pager_release"}, k = 1, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final Saver<PagerState, ?> getSaver() {
            return PagerState.Saver;
        }
    }
}
