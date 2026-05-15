package androidx.compose.foundation.interaction;

import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Metadata;

/* JADX INFO: compiled from: InteractionSource.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u0006\u0010\u0000\u001a\u00020\u0001¨\u0006\u0002"}, d2 = {"MutableInteractionSource", "Landroidx/compose/foundation/interaction/MutableInteractionSource;", "foundation_release"}, k = 2, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
public final class InteractionSourceKt {
    public static final MutableInteractionSource MutableInteractionSource() {
        return new MutableInteractionSourceImpl();
    }
}
