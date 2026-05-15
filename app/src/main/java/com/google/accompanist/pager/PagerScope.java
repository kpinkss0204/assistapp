package com.google.accompanist.pager;

import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Deprecated;
import kotlin.Metadata;

/* JADX INFO: compiled from: Pager.kt */
/* JADX INFO: loaded from: classes2.dex */
@Deprecated(message = "\naccompanist/pager is deprecated.\nThe androidx.compose equivalent of Pager is androidx.compose.foundation.pager.Pager.\nFor more migration information, please visit https://google.github.io/accompanist/pager/#migration\n")
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0003\bg\u0018\u00002\u00020\u0001R\u0012\u0010\u0002\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005R\u0012\u0010\u0006\u001a\u00020\u0007X¦\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\tø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\nÀ\u0006\u0001"}, d2 = {"Lcom/google/accompanist/pager/PagerScope;", "", "currentPage", "", "getCurrentPage", "()I", "currentPageOffset", "", "getCurrentPageOffset", "()F", "pager_release"}, k = 1, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
public interface PagerScope {
    int getCurrentPage();

    float getCurrentPageOffset();
}
