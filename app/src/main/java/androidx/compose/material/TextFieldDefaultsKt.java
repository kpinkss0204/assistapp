package androidx.compose.material;

import androidx.compose.animation.core.AnimateAsStateKt;
import androidx.compose.animation.core.AnimationSpecKt;
import androidx.compose.foundation.BorderStroke;
import androidx.compose.foundation.interaction.FocusInteractionKt;
import androidx.compose.foundation.interaction.InteractionSource;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.SolidColor;
import androidx.compose.ui.unit.Dp;
import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Metadata;

/* JADX INFO: compiled from: TextFieldDefaults.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000(\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u001aH\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000bH\u0003ø\u0001\u0000¢\u0006\u0004\b\r\u0010\u000e\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006\u000f²\u0006\n\u0010\u0010\u001a\u00020\u0004X\u008a\u0084\u0002"}, d2 = {"animateBorderStrokeAsState", "Landroidx/compose/runtime/State;", "Landroidx/compose/foundation/BorderStroke;", "enabled", "", "isError", "interactionSource", "Landroidx/compose/foundation/interaction/InteractionSource;", "colors", "Landroidx/compose/material/TextFieldColors;", "focusedBorderThickness", "Landroidx/compose/ui/unit/Dp;", "unfocusedBorderThickness", "animateBorderStrokeAsState-NuRrP5Q", "(ZZLandroidx/compose/foundation/interaction/InteractionSource;Landroidx/compose/material/TextFieldColors;FFLandroidx/compose/runtime/Composer;I)Landroidx/compose/runtime/State;", "material_release", "focused"}, k = 2, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
public final class TextFieldDefaultsKt {
    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: animateBorderStrokeAsState-NuRrP5Q, reason: not valid java name */
    public static final State<BorderStroke> m2009animateBorderStrokeAsStateNuRrP5Q(boolean z, boolean z2, InteractionSource interactionSource, TextFieldColors textFieldColors, float f, float f2, Composer composer, int i) {
        State<Dp> stateRememberUpdatedState;
        ComposerKt.sourceInformationMarkerStart(composer, 1097899920, "C(animateBorderStrokeAsState)P(1,4,3!1,2:c#ui.unit.Dp,5:c#ui.unit.Dp)983@42959L25,984@43017L51,991@43374L107:TextFieldDefaults.kt#jmzs0o");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1097899920, i, -1, "androidx.compose.material.animateBorderStrokeAsState (TextFieldDefaults.kt:982)");
        }
        State<Boolean> stateCollectIsFocusedAsState = FocusInteractionKt.collectIsFocusedAsState(interactionSource, composer, (i >> 6) & 14);
        State<Color> stateIndicatorColor = textFieldColors.indicatorColor(z, z2, interactionSource, composer, i & 8190);
        float f3 = animateBorderStrokeAsState_NuRrP5Q$lambda$0(stateCollectIsFocusedAsState) ? f : f2;
        if (z) {
            composer.startReplaceGroup(772641254);
            ComposerKt.sourceInformation(composer, "987@43212L76");
            stateRememberUpdatedState = AnimateAsStateKt.m412animateDpAsStateAjpBEmI(f3, AnimationSpecKt.tween$default(150, 0, null, 6, null), null, null, composer, 48, 12);
            composer.endReplaceGroup();
        } else {
            composer.startReplaceGroup(772737540);
            ComposerKt.sourceInformation(composer, "989@43310L46");
            stateRememberUpdatedState = SnapshotStateKt.rememberUpdatedState(Dp.m7025boximpl(f2), composer, (i >> 15) & 14);
            composer.endReplaceGroup();
        }
        State<BorderStroke> stateRememberUpdatedState2 = SnapshotStateKt.rememberUpdatedState(new BorderStroke(stateRememberUpdatedState.getValue().m7041unboximpl(), new SolidColor(stateIndicatorColor.getValue().m4578unboximpl(), null), null), composer, 0);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd(composer);
        return stateRememberUpdatedState2;
    }

    private static final boolean animateBorderStrokeAsState_NuRrP5Q$lambda$0(State<Boolean> state) {
        return state.getValue().booleanValue();
    }
}
