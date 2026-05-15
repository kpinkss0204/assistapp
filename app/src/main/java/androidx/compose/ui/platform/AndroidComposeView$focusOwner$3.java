package androidx.compose.ui.platform;

import androidx.compose.ui.focus.FocusDirection;
import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionReferenceImpl;

/* JADX INFO: compiled from: AndroidComposeView.android.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(k = 3, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* synthetic */ class AndroidComposeView$focusOwner$3 extends FunctionReferenceImpl implements Function1<FocusDirection, Boolean> {
    AndroidComposeView$focusOwner$3(Object obj) {
        super(1, obj, AndroidComposeView.class, "onMoveFocusInChildren", "onMoveFocusInChildren-3ESFkO8(I)Z", 0);
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Boolean invoke(FocusDirection focusDirection) {
        return m6230invoke3ESFkO8(focusDirection.getValue());
    }

    /* JADX INFO: renamed from: invoke-3ESFkO8, reason: not valid java name */
    public final Boolean m6230invoke3ESFkO8(int i) {
        return Boolean.valueOf(((AndroidComposeView) this.receiver).m6222onMoveFocusInChildren3ESFkO8(i));
    }
}
