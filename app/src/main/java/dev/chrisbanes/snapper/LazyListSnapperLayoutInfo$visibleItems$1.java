package dev.chrisbanes.snapper;

import androidx.compose.foundation.lazy.LazyListItemInfo;
import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: LazyList.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(k = 3, mv = {1, 6, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* synthetic */ class LazyListSnapperLayoutInfo$visibleItems$1 extends FunctionReferenceImpl implements Function1<LazyListItemInfo, LazyListSnapperLayoutItemInfo> {
    public static final LazyListSnapperLayoutInfo$visibleItems$1 INSTANCE = new LazyListSnapperLayoutInfo$visibleItems$1();

    LazyListSnapperLayoutInfo$visibleItems$1() {
        super(1, LazyListSnapperLayoutItemInfo.class, "<init>", "<init>(Landroidx/compose/foundation/lazy/LazyListItemInfo;)V", 0);
    }

    @Override // kotlin.jvm.functions.Function1
    public final LazyListSnapperLayoutItemInfo invoke(LazyListItemInfo p0) {
        Intrinsics.checkNotNullParameter(p0, "p0");
        return new LazyListSnapperLayoutItemInfo(p0);
    }
}
