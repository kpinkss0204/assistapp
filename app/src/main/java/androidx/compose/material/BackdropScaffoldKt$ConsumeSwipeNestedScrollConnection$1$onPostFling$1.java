package androidx.compose.material;

import androidx.compose.material.BackdropScaffoldKt;
import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* JADX INFO: compiled from: BackdropScaffold.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(k = 3, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
@DebugMetadata(c = "androidx.compose.material.BackdropScaffoldKt$ConsumeSwipeNestedScrollConnection$1", f = "BackdropScaffold.kt", i = {0}, l = {717}, m = "onPostFling-RZ2iAVY", n = {"available"}, s = {"J$0"})
final class BackdropScaffoldKt$ConsumeSwipeNestedScrollConnection$1$onPostFling$1 extends ContinuationImpl {
    long J$0;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ BackdropScaffoldKt.C04261 this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    BackdropScaffoldKt$ConsumeSwipeNestedScrollConnection$1$onPostFling$1(BackdropScaffoldKt.C04261 c04261, Continuation<? super BackdropScaffoldKt$ConsumeSwipeNestedScrollConnection$1$onPostFling$1> continuation) {
        super(continuation);
        this.this$0 = c04261;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.mo766onPostFlingRZ2iAVY(0L, 0L, this);
    }
}
