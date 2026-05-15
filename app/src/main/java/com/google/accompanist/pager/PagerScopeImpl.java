package com.google.accompanist.pager;

import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Pager.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0003\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0014\u0010\u0005\u001a\u00020\u00068VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\bR\u0014\u0010\t\u001a\u00020\n8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\fR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lcom/google/accompanist/pager/PagerScopeImpl;", "Lcom/google/accompanist/pager/PagerScope;", "state", "Lcom/google/accompanist/pager/PagerState;", "(Lcom/google/accompanist/pager/PagerState;)V", "currentPage", "", "getCurrentPage", "()I", "currentPageOffset", "", "getCurrentPageOffset", "()F", "pager_release"}, k = 1, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
final class PagerScopeImpl implements PagerScope {
    private final PagerState state;

    public PagerScopeImpl(PagerState state) {
        Intrinsics.checkNotNullParameter(state, "state");
        this.state = state;
    }

    @Override // com.google.accompanist.pager.PagerScope
    public int getCurrentPage() {
        return this.state.getCurrentPage();
    }

    @Override // com.google.accompanist.pager.PagerScope
    public float getCurrentPageOffset() {
        return this.state.getCurrentPageOffset();
    }
}
