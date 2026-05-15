package com.example.assistapp.features.LocationSharing;

import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;

/* JADX INFO: compiled from: LocationSharingWithCodeActivity.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(k = 3, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
public final class ComposableSingletons$LocationSharingWithCodeActivityKt {
    public static final ComposableSingletons$LocationSharingWithCodeActivityKt INSTANCE = new ComposableSingletons$LocationSharingWithCodeActivityKt();

    /* JADX INFO: renamed from: lambda-1, reason: not valid java name */
    public static Function2<Composer, Integer, Unit> f83lambda1 = ComposableLambdaKt.composableLambdaInstance(1860661809, false, new Function2<Composer, Integer, Unit>() { // from class: com.example.assistapp.features.LocationSharing.ComposableSingletons$LocationSharingWithCodeActivityKt$lambda-1$1
        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
            invoke(composer, num.intValue());
            return Unit.INSTANCE;
        }

        public final void invoke(Composer composer, int i) {
            ComposerKt.sourceInformation(composer, "C10@364L31:LocationSharingWithCodeActivity.kt#l7p0sd");
            if ((i & 3) == 2 && composer.getSkipping()) {
                composer.skipToGroupEnd();
                return;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1860661809, i, -1, "com.example.assistapp.features.LocationSharing.ComposableSingletons$LocationSharingWithCodeActivityKt.lambda-1.<anonymous> (LocationSharingWithCodeActivity.kt:10)");
            }
            LocationSharingWithCodeScreenKt.LocationSharingWithCodeScreen(composer, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
    });

    /* JADX INFO: renamed from: getLambda-1$app_release, reason: not valid java name */
    public final Function2<Composer, Integer, Unit> m7504getLambda1$app_release() {
        return f83lambda1;
    }
}
