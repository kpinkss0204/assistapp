package androidx.compose.material;

import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.ColorKt;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.firebase.analytics.FirebaseAnalytics;
import kotlin.Metadata;

/* JADX INFO: compiled from: ContentAlpha.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u000b\bÇ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J!\u0010\u000b\u001a\u00020\u00042\b\b\u0001\u0010\f\u001a\u00020\u00042\b\b\u0001\u0010\r\u001a\u00020\u0004H\u0003¢\u0006\u0002\u0010\u000eR\u0011\u0010\u0003\u001a\u00020\u00048G¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006R\u0011\u0010\u0007\u001a\u00020\u00048G¢\u0006\u0006\u001a\u0004\b\b\u0010\u0006R\u0011\u0010\t\u001a\u00020\u00048G¢\u0006\u0006\u001a\u0004\b\n\u0010\u0006¨\u0006\u000f"}, d2 = {"Landroidx/compose/material/ContentAlpha;", "", "()V", "disabled", "", "getDisabled", "(Landroidx/compose/runtime/Composer;I)F", "high", "getHigh", FirebaseAnalytics.Param.MEDIUM, "getMedium", "contentAlpha", "highContrastAlpha", "lowContrastAlpha", "(FFLandroidx/compose/runtime/Composer;I)F", "material_release"}, k = 1, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
public final class ContentAlpha {
    public static final int $stable = 0;
    public static final ContentAlpha INSTANCE = new ContentAlpha();

    private ContentAlpha() {
    }

    public final float getHigh(Composer composer, int i) {
        ComposerKt.sourceInformationMarkerStart(composer, 629162431, "C35@1145L146:ContentAlpha.kt#jmzs0o");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(629162431, i, -1, "androidx.compose.material.ContentAlpha.<get-high> (ContentAlpha.kt:35)");
        }
        float fContentAlpha = contentAlpha(1.0f, 0.87f, composer, ((i << 6) & 896) | 54);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd(composer);
        return fContentAlpha;
    }

    public final float getMedium(Composer composer, int i) {
        ComposerKt.sourceInformationMarkerStart(composer, 1999054879, "C46@1496L150:ContentAlpha.kt#jmzs0o");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1999054879, i, -1, "androidx.compose.material.ContentAlpha.<get-medium> (ContentAlpha.kt:46)");
        }
        float fContentAlpha = contentAlpha(0.74f, 0.6f, composer, ((i << 6) & 896) | 54);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd(composer);
        return fContentAlpha;
    }

    public final float getDisabled(Composer composer, int i) {
        ComposerKt.sourceInformationMarkerStart(composer, 621183615, "C57@1843L154:ContentAlpha.kt#jmzs0o");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(621183615, i, -1, "androidx.compose.material.ContentAlpha.<get-disabled> (ContentAlpha.kt:57)");
        }
        float fContentAlpha = contentAlpha(0.38f, 0.38f, composer, ((i << 6) & 896) | 54);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd(composer);
        return fContentAlpha;
    }

    private final float contentAlpha(float f, float f2, Composer composer, int i) {
        ComposerKt.sourceInformationMarkerStart(composer, -1528360391, "C(contentAlpha)77@2653L7,78@2700L6:ContentAlpha.kt#jmzs0o");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1528360391, i, -1, "androidx.compose.material.ContentAlpha.contentAlpha (ContentAlpha.kt:76)");
        }
        ProvidableCompositionLocal<Color> localContentColor = ContentColorKt.getLocalContentColor();
        ComposerKt.sourceInformationMarkerStart(composer, 2023513938, "CC:CompositionLocal.kt#9igjgp");
        Object objConsume = composer.consume(localContentColor);
        ComposerKt.sourceInformationMarkerEnd(composer);
        long jM4578unboximpl = ((Color) objConsume).m4578unboximpl();
        if (!MaterialTheme.INSTANCE.getColors(composer, 6).isLight() ? ColorKt.m4620luminance8_81llA(jM4578unboximpl) >= 0.5d : ColorKt.m4620luminance8_81llA(jM4578unboximpl) <= 0.5d) {
            f = f2;
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd(composer);
        return f;
    }
}
