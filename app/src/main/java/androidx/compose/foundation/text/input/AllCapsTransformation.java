package androidx.compose.foundation.text.input;

import androidx.compose.foundation.text.KeyboardOptions;
import androidx.compose.foundation.text.input.TextFieldBuffer;
import androidx.compose.ui.text.StringKt;
import androidx.compose.ui.text.TextRange;
import androidx.compose.ui.text.TextRangeKt;
import androidx.compose.ui.text.input.KeyboardCapitalization;
import androidx.compose.ui.text.input.PlatformImeOptions;
import androidx.compose.ui.text.intl.Locale;
import androidx.compose.ui.text.intl.LocaleList;
import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.internal.ws.WebSocketProtocol;

/* JADX INFO: compiled from: InputTransformation.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\b\u0082\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\t\u001a\u00020\u0003HÂ\u0003J\u0013\u0010\n\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000eHÖ\u0003J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001J\b\u0010\u0011\u001a\u00020\u0012H\u0016J\f\u0010\u0013\u001a\u00020\u0014*\u00020\u0015H\u0016R\u0014\u0010\u0005\u001a\u00020\u0006X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0016"}, d2 = {"Landroidx/compose/foundation/text/input/AllCapsTransformation;", "Landroidx/compose/foundation/text/input/InputTransformation;", "locale", "Landroidx/compose/ui/text/intl/Locale;", "(Landroidx/compose/ui/text/intl/Locale;)V", "keyboardOptions", "Landroidx/compose/foundation/text/KeyboardOptions;", "getKeyboardOptions", "()Landroidx/compose/foundation/text/KeyboardOptions;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "transformInput", "", "Landroidx/compose/foundation/text/input/TextFieldBuffer;", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
final /* data */ class AllCapsTransformation implements InputTransformation {
    private final KeyboardOptions keyboardOptions = new KeyboardOptions(KeyboardCapitalization.INSTANCE.m6722getCharactersIUNYP9k(), (Boolean) null, 0, 0, (PlatformImeOptions) null, (Boolean) null, (LocaleList) null, WebSocketProtocol.PAYLOAD_SHORT, (DefaultConstructorMarker) null);
    private final Locale locale;

    /* JADX INFO: renamed from: component1, reason: from getter */
    private final Locale getLocale() {
        return this.locale;
    }

    public static /* synthetic */ AllCapsTransformation copy$default(AllCapsTransformation allCapsTransformation, Locale locale, int i, Object obj) {
        if ((i & 1) != 0) {
            locale = allCapsTransformation.locale;
        }
        return allCapsTransformation.copy(locale);
    }

    public final AllCapsTransformation copy(Locale locale) {
        return new AllCapsTransformation(locale);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        return (other instanceof AllCapsTransformation) && Intrinsics.areEqual(this.locale, ((AllCapsTransformation) other).locale);
    }

    public int hashCode() {
        return this.locale.hashCode();
    }

    public AllCapsTransformation(Locale locale) {
        this.locale = locale;
    }

    @Override // androidx.compose.foundation.text.input.InputTransformation
    public KeyboardOptions getKeyboardOptions() {
        return this.keyboardOptions;
    }

    @Override // androidx.compose.foundation.text.input.InputTransformation
    public void transformInput(TextFieldBuffer textFieldBuffer) {
        TextFieldBuffer.ChangeList changes = textFieldBuffer.getChanges();
        for (int i = 0; i < changes.getChangeCount(); i++) {
            long jMo1390getRangejx7JFs = changes.mo1390getRangejx7JFs(i);
            changes.mo1389getOriginalRangejx7JFs(i);
            if (!TextRange.m6506getCollapsedimpl(jMo1390getRangejx7JFs)) {
                textFieldBuffer.replace(TextRange.m6510getMinimpl(jMo1390getRangejx7JFs), TextRange.m6509getMaximpl(jMo1390getRangejx7JFs), StringKt.toUpperCase(TextRangeKt.m6519substringFDrldGo(textFieldBuffer.asCharSequence(), jMo1390getRangejx7JFs), this.locale));
            }
        }
    }

    public String toString() {
        return "InputTransformation.allCaps(locale=" + this.locale + ')';
    }
}
