package androidx.compose.runtime.snapshots;

import androidx.compose.runtime.internal.AtomicInt;
import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Metadata;

/* JADX INFO: compiled from: StateObjectImpl.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\b!\u0018\u00002\u00020\u0001B\t\b\u0000¢\u0006\u0004\b\u0002\u0010\u0003J\u0017\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0000¢\u0006\u0004\b\n\u0010\u000bJ\u0017\u0010\f\u001a\u00020\r2\u0006\u0010\b\u001a\u00020\tH\u0000¢\u0006\u0004\b\u000e\u0010\u000fR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Landroidx/compose/runtime/snapshots/StateObjectImpl;", "Landroidx/compose/runtime/snapshots/StateObject;", "<init>", "()V", "readerKind", "Landroidx/compose/runtime/internal/AtomicInt;", "recordReadIn", "", "reader", "Landroidx/compose/runtime/snapshots/ReaderKind;", "recordReadIn-h_f27i8$runtime", "(I)V", "isReadIn", "", "isReadIn-h_f27i8$runtime", "(I)Z", "runtime"}, k = 1, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
public abstract class StateObjectImpl implements StateObject {
    public static final int $stable = 8;
    private final AtomicInt readerKind = new AtomicInt(0);

    /* JADX INFO: renamed from: recordReadIn-h_f27i8$runtime, reason: not valid java name */
    public final void m4171recordReadInh_f27i8$runtime(int reader) {
        int iM4153constructorimpl;
        do {
            iM4153constructorimpl = ReaderKind.m4153constructorimpl(this.readerKind.get());
            if ((iM4153constructorimpl & reader) != 0) {
                return;
            }
        } while (!this.readerKind.compareAndSet(iM4153constructorimpl, ReaderKind.m4153constructorimpl(iM4153constructorimpl | reader)));
    }

    /* JADX INFO: renamed from: isReadIn-h_f27i8$runtime, reason: not valid java name */
    public final boolean m4170isReadInh_f27i8$runtime(int reader) {
        return (reader & ReaderKind.m4153constructorimpl(this.readerKind.get())) != 0;
    }
}
