package androidx.compose.material.internal;

import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;

/* JADX INFO: compiled from: ExposedDropdownMenuPopup.android.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(k = 3, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
public final class ComposableSingletons$ExposedDropdownMenuPopup_androidKt {
    public static final ComposableSingletons$ExposedDropdownMenuPopup_androidKt INSTANCE = new ComposableSingletons$ExposedDropdownMenuPopup_androidKt();

    /* JADX INFO: renamed from: lambda-1, reason: not valid java name */
    public static Function2<Composer, Integer, Unit> f22lambda1 = ComposableLambdaKt.composableLambdaInstance(-1578637197, false, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.internal.ComposableSingletons$ExposedDropdownMenuPopup_androidKt$lambda-1$1
        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
            invoke(composer, num.intValue());
            return Unit.INSTANCE;
        }

        public final void invoke(Composer composer, int i) {
            ComposerKt.sourceInformation(composer, "C:ExposedDropdownMenuPopup.android.kt#mnwmf7");
            if ((i & 3) == 2 && composer.getSkipping()) {
                composer.skipToGroupEnd();
                return;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1578637197, i, -1, "androidx.compose.material.internal.ComposableSingletons$ExposedDropdownMenuPopup_androidKt.lambda-1.<anonymous> (ExposedDropdownMenuPopup.android.kt:285)");
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
    });

    /* JADX INFO: renamed from: getLambda-1$material_release, reason: not valid java name */
    public final Function2<Composer, Integer, Unit> m2025getLambda1$material_release() {
        return f22lambda1;
    }
}
