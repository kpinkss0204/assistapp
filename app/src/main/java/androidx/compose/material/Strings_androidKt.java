package androidx.compose.material;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.ui.platform.AndroidCompositionLocals_androidKt;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Metadata;

/* JADX INFO: compiled from: Strings.android.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\u001a\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0001ø\u0001\u0000¢\u0006\u0004\b\u0004\u0010\u0005\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006\u0006"}, d2 = {"getString", "", TypedValues.Custom.S_STRING, "Landroidx/compose/material/Strings;", "getString-4foXLRw", "(ILandroidx/compose/runtime/Composer;I)Ljava/lang/String;", "material_release"}, k = 2, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
public final class Strings_androidKt {
    /* JADX INFO: renamed from: getString-4foXLRw, reason: not valid java name */
    public static final String m1958getString4foXLRw(int i, Composer composer, int i2) {
        String string;
        ComposerKt.sourceInformationMarkerStart(composer, -726638443, "C(getString)P(0:c#material.Strings)25@921L7,26@962L7:Strings.android.kt#jmzs0o");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-726638443, i2, -1, "androidx.compose.material.getString (Strings.android.kt:24)");
        }
        ProvidableCompositionLocal<Configuration> localConfiguration = AndroidCompositionLocals_androidKt.getLocalConfiguration();
        ComposerKt.sourceInformationMarkerStart(composer, 2023513938, "CC:CompositionLocal.kt#9igjgp");
        composer.consume(localConfiguration);
        ComposerKt.sourceInformationMarkerEnd(composer);
        ProvidableCompositionLocal<Context> localContext = AndroidCompositionLocals_androidKt.getLocalContext();
        ComposerKt.sourceInformationMarkerStart(composer, 2023513938, "CC:CompositionLocal.kt#9igjgp");
        Object objConsume = composer.consume(localContext);
        ComposerKt.sourceInformationMarkerEnd(composer);
        Resources resources = ((Context) objConsume).getResources();
        if (Strings.m1947equalsimpl0(i, Strings.INSTANCE.m1955getNavigationMenuUdPEhr4())) {
            string = resources.getString(androidx.compose.ui.R.string.navigation_menu);
        } else if (Strings.m1947equalsimpl0(i, Strings.INSTANCE.m1951getCloseDrawerUdPEhr4())) {
            string = resources.getString(androidx.compose.ui.R.string.close_drawer);
        } else if (Strings.m1947equalsimpl0(i, Strings.INSTANCE.m1952getCloseSheetUdPEhr4())) {
            string = resources.getString(androidx.compose.ui.R.string.close_sheet);
        } else if (Strings.m1947equalsimpl0(i, Strings.INSTANCE.m1953getDefaultErrorMessageUdPEhr4())) {
            string = resources.getString(androidx.compose.ui.R.string.default_error_message);
        } else if (Strings.m1947equalsimpl0(i, Strings.INSTANCE.m1954getExposedDropdownMenuUdPEhr4())) {
            string = resources.getString(androidx.compose.ui.R.string.dropdown_menu);
        } else if (Strings.m1947equalsimpl0(i, Strings.INSTANCE.m1957getSliderRangeStartUdPEhr4())) {
            string = resources.getString(androidx.compose.ui.R.string.range_start);
        } else {
            string = Strings.m1947equalsimpl0(i, Strings.INSTANCE.m1956getSliderRangeEndUdPEhr4()) ? resources.getString(androidx.compose.ui.R.string.range_end) : "";
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd(composer);
        return string;
    }
}
