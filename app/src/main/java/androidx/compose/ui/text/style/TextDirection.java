package androidx.compose.ui.text.style;

import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Metadata;
import kotlin.jvm.JvmInline;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: TextDirection.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\u0004\b\u0087@\u0018\u0000 \u00112\u00020\u0001:\u0001\u0011B\u0011\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u001a\u0010\u0006\u001a\u00020\u00072\b\u0010\b\u001a\u0004\u0018\u00010\u0001HÖ\u0003¢\u0006\u0004\b\t\u0010\nJ\u0010\u0010\u000b\u001a\u00020\u0003HÖ\u0001¢\u0006\u0004\b\f\u0010\u0005J\u000f\u0010\r\u001a\u00020\u000eH\u0016¢\u0006\u0004\b\u000f\u0010\u0010R\u000e\u0010\u0002\u001a\u00020\u0003X\u0080\u0004¢\u0006\u0002\n\u0000\u0088\u0001\u0002\u0092\u0001\u00020\u0003¨\u0006\u0012"}, d2 = {"Landroidx/compose/ui/text/style/TextDirection;", "", "value", "", "constructor-impl", "(I)I", "equals", "", "other", "equals-impl", "(ILjava/lang/Object;)Z", "hashCode", "hashCode-impl", "toString", "", "toString-impl", "(I)Ljava/lang/String;", "Companion", "ui-text_release"}, k = 1, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
@JvmInline
public final class TextDirection {
    private final int value;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final int Ltr = m6924constructorimpl(1);
    private static final int Rtl = m6924constructorimpl(2);
    private static final int Content = m6924constructorimpl(3);
    private static final int ContentOrLtr = m6924constructorimpl(4);
    private static final int ContentOrRtl = m6924constructorimpl(5);
    private static final int Unspecified = m6924constructorimpl(Integer.MIN_VALUE);

    /* JADX INFO: renamed from: box-impl, reason: not valid java name */
    public static final /* synthetic */ TextDirection m6923boximpl(int i) {
        return new TextDirection(i);
    }

    /* JADX INFO: renamed from: constructor-impl, reason: not valid java name */
    public static int m6924constructorimpl(int i) {
        return i;
    }

    /* JADX INFO: renamed from: equals-impl, reason: not valid java name */
    public static boolean m6925equalsimpl(int i, Object obj) {
        return (obj instanceof TextDirection) && i == ((TextDirection) obj).getValue();
    }

    /* JADX INFO: renamed from: equals-impl0, reason: not valid java name */
    public static final boolean m6926equalsimpl0(int i, int i2) {
        return i == i2;
    }

    /* JADX INFO: renamed from: hashCode-impl, reason: not valid java name */
    public static int m6927hashCodeimpl(int i) {
        return Integer.hashCode(i);
    }

    public boolean equals(Object obj) {
        return m6925equalsimpl(this.value, obj);
    }

    public int hashCode() {
        return m6927hashCodeimpl(this.value);
    }

    /* JADX INFO: renamed from: unbox-impl, reason: not valid java name and from getter */
    public final /* synthetic */ int getValue() {
        return this.value;
    }

    private /* synthetic */ TextDirection(int i) {
        this.value = i;
    }

    public String toString() {
        return m6928toStringimpl(this.value);
    }

    /* JADX INFO: renamed from: toString-impl, reason: not valid java name */
    public static String m6928toStringimpl(int i) {
        return m6926equalsimpl0(i, Ltr) ? "Ltr" : m6926equalsimpl0(i, Rtl) ? "Rtl" : m6926equalsimpl0(i, Content) ? "Content" : m6926equalsimpl0(i, ContentOrLtr) ? "ContentOrLtr" : m6926equalsimpl0(i, ContentOrRtl) ? "ContentOrRtl" : m6926equalsimpl0(i, Unspecified) ? "Unspecified" : "Invalid";
    }

    /* JADX INFO: compiled from: TextDirection.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000e\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0019\u0010\u0003\u001a\u00020\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b\u0005\u0010\u0006R\u0019\u0010\b\u001a\u00020\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b\t\u0010\u0006R\u0019\u0010\n\u001a\u00020\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b\u000b\u0010\u0006R\u0019\u0010\f\u001a\u00020\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b\r\u0010\u0006R\u0019\u0010\u000e\u001a\u00020\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b\u000f\u0010\u0006R\u0019\u0010\u0010\u001a\u00020\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b\u0011\u0010\u0006\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006\u0012"}, d2 = {"Landroidx/compose/ui/text/style/TextDirection$Companion;", "", "()V", "Content", "Landroidx/compose/ui/text/style/TextDirection;", "getContent-s_7X-co", "()I", "I", "ContentOrLtr", "getContentOrLtr-s_7X-co", "ContentOrRtl", "getContentOrRtl-s_7X-co", "Ltr", "getLtr-s_7X-co", "Rtl", "getRtl-s_7X-co", "Unspecified", "getUnspecified-s_7X-co", "ui-text_release"}, k = 1, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* JADX INFO: renamed from: getLtr-s_7X-co, reason: not valid java name */
        public final int m6933getLtrs_7Xco() {
            return TextDirection.Ltr;
        }

        /* JADX INFO: renamed from: getRtl-s_7X-co, reason: not valid java name */
        public final int m6934getRtls_7Xco() {
            return TextDirection.Rtl;
        }

        /* JADX INFO: renamed from: getContent-s_7X-co, reason: not valid java name */
        public final int m6930getContents_7Xco() {
            return TextDirection.Content;
        }

        /* JADX INFO: renamed from: getContentOrLtr-s_7X-co, reason: not valid java name */
        public final int m6931getContentOrLtrs_7Xco() {
            return TextDirection.ContentOrLtr;
        }

        /* JADX INFO: renamed from: getContentOrRtl-s_7X-co, reason: not valid java name */
        public final int m6932getContentOrRtls_7Xco() {
            return TextDirection.ContentOrRtl;
        }

        /* JADX INFO: renamed from: getUnspecified-s_7X-co, reason: not valid java name */
        public final int m6935getUnspecifieds_7Xco() {
            return TextDirection.Unspecified;
        }
    }
}
