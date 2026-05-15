package androidx.compose.runtime.tooling;

import androidx.compose.runtime.Anchor;
import androidx.compose.runtime.GroupSourceInformation;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.constraintlayout.widget.ConstraintLayout;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: ComposeStackTraceBuilder.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\b!\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00060\bJ\u001a\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u0001H\u0002J\u001c\u0010\u000e\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u000b\u001a\u00020\f2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0001H\u0002J\u0012\u0010\u0010\u001a\u0004\u0018\u00010\f2\u0006\u0010\u0011\u001a\u00020\u0001H\u0002J\f\u0010\u0012\u001a\u00020\u0013*\u00020\fH\u0002J\u001a\u0010\u0014\u001a\u00020\n2\b\u0010\u0015\u001a\u0004\u0018\u00010\f2\b\u0010\u0016\u001a\u0004\u0018\u00010\u0001J\u0018\u0010\u0017\u001a\u00020\u00132\u0006\u0010\u0015\u001a\u00020\f2\u0006\u0010\u0018\u001a\u00020\u0001H\u0002J\u0012\u0010\u0010\u001a\u0004\u0018\u00010\f2\u0006\u0010\u0019\u001a\u00020\u001aH&J\u0010\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u0019\u001a\u00020\u001aH&R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001d"}, d2 = {"Landroidx/compose/runtime/tooling/ComposeStackTraceBuilder;", "", "<init>", "()V", "_trace", "", "Landroidx/compose/runtime/tooling/ComposeStackTraceFrame;", "trace", "", "appendTraceFrame", "", "groupSourceInformation", "Landroidx/compose/runtime/GroupSourceInformation;", "child", "extractTraceFrame", "targetChild", "sourceInformationOf", "group", "isCall", "", "processEdge", "sourceInformation", "childData", "findInGroupSourceInformation", TypedValues.AttributesType.S_TARGET, "anchor", "Landroidx/compose/runtime/Anchor;", "groupKeyOf", "", "runtime"}, k = 1, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
public abstract class ComposeStackTraceBuilder {
    public static final int $stable = 8;
    private final List<ComposeStackTraceFrame> _trace = new ArrayList();

    public abstract int groupKeyOf(Anchor anchor);

    public abstract GroupSourceInformation sourceInformationOf(Anchor anchor);

    public final List<ComposeStackTraceFrame> trace() {
        return this._trace;
    }

    private final void appendTraceFrame(GroupSourceInformation groupSourceInformation, Object child) {
        ComposeStackTraceFrame composeStackTraceFrameExtractTraceFrame = extractTraceFrame(groupSourceInformation, child);
        if (composeStackTraceFrameExtractTraceFrame != null) {
            this._trace.add(composeStackTraceFrameExtractTraceFrame);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:41:0x0087  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final androidx.compose.runtime.tooling.ComposeStackTraceFrame extractTraceFrame(androidx.compose.runtime.GroupSourceInformation r12, java.lang.Object r13) {
        /*
            r11 = this;
            java.lang.String r0 = r12.getSourceInformation()
            r1 = 0
            if (r0 == 0) goto Lc
            androidx.compose.runtime.tooling.SourceInformation r0 = androidx.compose.runtime.tooling.SourceInformationKt.parseSourceInformation(r0)
            goto Ld
        Lc:
            r0 = r1
        Ld:
            if (r0 == 0) goto L9f
            if (r13 != 0) goto L17
            androidx.compose.runtime.tooling.ComposeStackTraceFrame r12 = new androidx.compose.runtime.tooling.ComposeStackTraceFrame
            r12.<init>(r0, r1)
            return r12
        L17:
            java.util.ArrayList r12 = r12.getGroups()
            r2 = 0
            if (r12 == 0) goto L95
            r3 = r12
            java.util.Collection r3 = (java.util.Collection) r3
            int r3 = r3.size()
            r4 = r2
            r5 = r4
        L27:
            if (r4 >= r3) goto L94
            java.lang.Object r6 = r12.get(r4)
            boolean r7 = kotlin.jvm.internal.Intrinsics.areEqual(r6, r13)
            if (r7 != 0) goto L94
            androidx.compose.runtime.GroupSourceInformation r7 = r11.sourceInformationOf(r6)
            r8 = 1
            if (r7 == 0) goto L87
            int r9 = r7.getKey()
            r10 = -127(0xffffffffffffff81, float:NaN)
            if (r9 == r10) goto L54
            int r9 = r7.getKey()
            if (r9 != 0) goto L87
            boolean r9 = r6 instanceof androidx.compose.runtime.Anchor
            if (r9 == 0) goto L87
            androidx.compose.runtime.Anchor r6 = (androidx.compose.runtime.Anchor) r6
            int r6 = r11.groupKeyOf(r6)
            if (r6 != r10) goto L87
        L54:
            if (r7 == 0) goto L5b
            java.lang.String r6 = r7.getSourceInformation()
            goto L5c
        L5b:
            r6 = r1
        L5c:
            if (r6 != 0) goto L87
            if (r7 == 0) goto L91
            java.util.ArrayList r6 = r7.getGroups()
            if (r6 == 0) goto L91
            java.util.List r6 = (java.util.List) r6
            r7 = r6
            java.util.Collection r7 = (java.util.Collection) r7
            int r7 = r7.size()
            r9 = r2
        L70:
            if (r9 >= r7) goto L91
            java.lang.Object r10 = r6.get(r9)
            androidx.compose.runtime.GroupSourceInformation r10 = r11.sourceInformationOf(r10)
            if (r10 == 0) goto L84
            boolean r10 = r11.isCall(r10)
            if (r10 != r8) goto L84
            int r5 = r5 + 1
        L84:
            int r9 = r9 + 1
            goto L70
        L87:
            if (r7 == 0) goto L91
            boolean r6 = r11.isCall(r7)
            if (r6 != r8) goto L91
            int r5 = r5 + 1
        L91:
            int r4 = r4 + 1
            goto L27
        L94:
            r2 = r5
        L95:
            androidx.compose.runtime.tooling.ComposeStackTraceFrame r12 = new androidx.compose.runtime.tooling.ComposeStackTraceFrame
            java.lang.Integer r13 = java.lang.Integer.valueOf(r2)
            r12.<init>(r0, r13)
            return r12
        L9f:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.runtime.tooling.ComposeStackTraceBuilder.extractTraceFrame(androidx.compose.runtime.GroupSourceInformation, java.lang.Object):androidx.compose.runtime.tooling.ComposeStackTraceFrame");
    }

    private final GroupSourceInformation sourceInformationOf(Object group) {
        if (group instanceof Anchor) {
            return sourceInformationOf((Anchor) group);
        }
        if (group instanceof GroupSourceInformation) {
            return (GroupSourceInformation) group;
        }
        throw new IllegalStateException(("Unexpected child source info " + group).toString());
    }

    private final boolean isCall(GroupSourceInformation groupSourceInformation) {
        String sourceInformation = groupSourceInformation.getSourceInformation();
        return sourceInformation != null && StringsKt.startsWith$default(sourceInformation, "C", false, 2, (Object) null);
    }

    public final void processEdge(GroupSourceInformation sourceInformation, Object childData) {
        if (sourceInformation != null) {
            if (childData == null) {
                appendTraceFrame(sourceInformation, null);
            } else {
                if (findInGroupSourceInformation(sourceInformation, childData) || sourceInformation.getClosed()) {
                    return;
                }
                appendTraceFrame(sourceInformation, null);
            }
        }
    }

    private final boolean findInGroupSourceInformation(GroupSourceInformation sourceInformation, Object target) {
        ArrayList<Object> groups = sourceInformation.getGroups();
        boolean z = false;
        if (groups == null) {
            if (!sourceInformation.getClosed()) {
                appendTraceFrame(sourceInformation, null);
                return true;
            }
            int dataStartOffset = sourceInformation.getDataStartOffset();
            int dataEndOffset = sourceInformation.getDataEndOffset();
            boolean z2 = target instanceof Integer;
            if (z2) {
                Number number = (Number) target;
                int iIntValue = number.intValue();
                if ((dataStartOffset <= iIntValue && iIntValue < dataEndOffset) || (dataStartOffset == dataEndOffset && z2 && dataStartOffset == number.intValue())) {
                    z = true;
                }
                if (z) {
                    appendTraceFrame(sourceInformation, null);
                }
            }
            return z;
        }
        ArrayList<Object> arrayList = groups;
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            Object obj = arrayList.get(i);
            if (obj instanceof Anchor) {
                if (Intrinsics.areEqual(obj, target)) {
                    appendTraceFrame(sourceInformation, obj);
                    return true;
                }
            } else if (obj instanceof GroupSourceInformation) {
                if (findInGroupSourceInformation((GroupSourceInformation) obj, target)) {
                    appendTraceFrame(sourceInformation, obj);
                    return true;
                }
            } else {
                throw new IllegalStateException(("Unexpected child source info " + obj).toString());
            }
        }
        return false;
    }
}
