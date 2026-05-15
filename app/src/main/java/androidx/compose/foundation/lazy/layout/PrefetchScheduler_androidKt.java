package androidx.compose.foundation.lazy.layout;

import android.view.View;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.ui.platform.AndroidCompositionLocals_androidKt;
import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Metadata;

/* JADX INFO: compiled from: PrefetchScheduler.android.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\r\u0010\u0000\u001a\u00020\u0001H\u0001¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"rememberDefaultPrefetchScheduler", "Landroidx/compose/foundation/lazy/layout/PrefetchScheduler;", "(Landroidx/compose/runtime/Composer;I)Landroidx/compose/foundation/lazy/layout/PrefetchScheduler;", "foundation_release"}, k = 2, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
public final class PrefetchScheduler_androidKt {
    public static final PrefetchScheduler rememberDefaultPrefetchScheduler(Composer composer, int i) {
        ComposerKt.sourceInformationMarkerStart(composer, 1141871251, "C(rememberDefaultPrefetchScheduler)32@1216L7,33@1235L61:PrefetchScheduler.android.kt#wow0x6");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1141871251, i, -1, "androidx.compose.foundation.lazy.layout.rememberDefaultPrefetchScheduler (PrefetchScheduler.android.kt:31)");
        }
        ProvidableCompositionLocal<View> localView = AndroidCompositionLocals_androidKt.getLocalView();
        ComposerKt.sourceInformationMarkerStart(composer, 2023513938, "CC:CompositionLocal.kt#9igjgp");
        Object objConsume = composer.consume(localView);
        ComposerKt.sourceInformationMarkerEnd(composer);
        View view = (View) objConsume;
        ComposerKt.sourceInformationMarkerStart(composer, -930674095, "CC(remember):PrefetchScheduler.android.kt#9igjgp");
        boolean zChanged = composer.changed(view);
        Object objRememberedValue = composer.rememberedValue();
        if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
            objRememberedValue = new AndroidPrefetchScheduler(view);
            composer.updateRememberedValue(objRememberedValue);
        }
        AndroidPrefetchScheduler androidPrefetchScheduler = (AndroidPrefetchScheduler) objRememberedValue;
        ComposerKt.sourceInformationMarkerEnd(composer);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd(composer);
        return androidPrefetchScheduler;
    }
}
