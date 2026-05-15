package androidx.compose.ui.text.input;

import androidx.compose.ui.text.TextRange;
import androidx.compose.ui.text.TextRangeKt;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Metadata;

/* JADX INFO: compiled from: EditingBuffer.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u001a\"\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0001H\u0000ø\u0001\u0000¢\u0006\u0004\b\u0004\u0010\u0005\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006\u0006"}, d2 = {"updateRangeAfterDelete", "Landroidx/compose/ui/text/TextRange;", TypedValues.AttributesType.S_TARGET, "deleted", "updateRangeAfterDelete-pWDy79M", "(JJ)J", "ui-text_release"}, k = 2, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
public final class EditingBufferKt {
    /* JADX INFO: renamed from: updateRangeAfterDelete-pWDy79M, reason: not valid java name */
    public static final long m6673updateRangeAfterDeletepWDy79M(long j, long j2) {
        int iM6508getLengthimpl;
        int iM6510getMinimpl = TextRange.m6510getMinimpl(j);
        int iM6509getMaximpl = TextRange.m6509getMaximpl(j);
        if (TextRange.m6514intersects5zctL8(j2, j)) {
            if (TextRange.m6502contains5zctL8(j2, j)) {
                iM6510getMinimpl = TextRange.m6510getMinimpl(j2);
                iM6509getMaximpl = iM6510getMinimpl;
            } else {
                if (TextRange.m6502contains5zctL8(j, j2)) {
                    iM6508getLengthimpl = TextRange.m6508getLengthimpl(j2);
                } else if (TextRange.m6503containsimpl(j2, iM6510getMinimpl)) {
                    iM6510getMinimpl = TextRange.m6510getMinimpl(j2);
                    iM6508getLengthimpl = TextRange.m6508getLengthimpl(j2);
                } else {
                    iM6509getMaximpl = TextRange.m6510getMinimpl(j2);
                }
                iM6509getMaximpl -= iM6508getLengthimpl;
            }
        } else if (iM6509getMaximpl > TextRange.m6510getMinimpl(j2)) {
            iM6510getMinimpl -= TextRange.m6508getLengthimpl(j2);
            iM6508getLengthimpl = TextRange.m6508getLengthimpl(j2);
            iM6509getMaximpl -= iM6508getLengthimpl;
        }
        return TextRangeKt.TextRange(iM6510getMinimpl, iM6509getMaximpl);
    }
}
