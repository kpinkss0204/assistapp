package dev.chrisbanes.snapper;

import androidx.compose.foundation.lazy.LazyListItemInfo;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.firebase.analytics.FirebaseAnalytics;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: LazyList.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0007\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0014\u0010\u0005\u001a\u00020\u00068VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\bR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\u00020\u00068VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\bR\u0014\u0010\u000b\u001a\u00020\u00068VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\b¨\u0006\r"}, d2 = {"Ldev/chrisbanes/snapper/LazyListSnapperLayoutItemInfo;", "Ldev/chrisbanes/snapper/SnapperLayoutItemInfo;", "lazyListItem", "Landroidx/compose/foundation/lazy/LazyListItemInfo;", "(Landroidx/compose/foundation/lazy/LazyListItemInfo;)V", FirebaseAnalytics.Param.INDEX, "", "getIndex", "()I", TypedValues.CycleType.S_WAVE_OFFSET, "getOffset", "size", "getSize", "lib_release"}, k = 1, mv = {1, 6, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
final class LazyListSnapperLayoutItemInfo extends SnapperLayoutItemInfo {
    private final LazyListItemInfo lazyListItem;

    public LazyListSnapperLayoutItemInfo(LazyListItemInfo lazyListItem) {
        Intrinsics.checkNotNullParameter(lazyListItem, "lazyListItem");
        this.lazyListItem = lazyListItem;
    }

    @Override // dev.chrisbanes.snapper.SnapperLayoutItemInfo
    public int getIndex() {
        return this.lazyListItem.getIndex();
    }

    @Override // dev.chrisbanes.snapper.SnapperLayoutItemInfo
    public int getOffset() {
        return this.lazyListItem.getOffset();
    }

    @Override // dev.chrisbanes.snapper.SnapperLayoutItemInfo
    public int getSize() {
        return this.lazyListItem.getSize();
    }
}
