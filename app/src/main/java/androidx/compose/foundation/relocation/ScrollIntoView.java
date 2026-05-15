package androidx.compose.foundation.relocation;

import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.node.DelegatableNode;
import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;

/* JADX INFO: compiled from: ScrollIntoViewRequester.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"androidx/compose/foundation/relocation/ScrollIntoView__ScrollIntoViewRequesterKt"}, k = 4, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
public final class ScrollIntoView {
    public static final Object scrollIntoView(DelegatableNode delegatableNode, Rect rect, Continuation<? super Unit> continuation) {
        return ScrollIntoView__ScrollIntoViewRequesterKt.scrollIntoView(delegatableNode, rect, continuation);
    }
}
