package com.example.assistapp.features.ScheduleSharing;

import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;

/* JADX INFO: compiled from: ScheduleSendActivity.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(k = 3, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
public final class ComposableSingletons$ScheduleSendActivityKt {
    public static final ComposableSingletons$ScheduleSendActivityKt INSTANCE = new ComposableSingletons$ScheduleSendActivityKt();

    /* JADX INFO: renamed from: lambda-1, reason: not valid java name */
    public static Function2<Composer, Integer, Unit> f93lambda1 = ComposableLambdaKt.composableLambdaInstance(1294739891, false, new Function2<Composer, Integer, Unit>() { // from class: com.example.assistapp.features.ScheduleSharing.ComposableSingletons$ScheduleSendActivityKt$lambda-1$1
        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
            invoke(composer, num.intValue());
            return Unit.INSTANCE;
        }

        public final void invoke(Composer composer, int i) {
            ComposerKt.sourceInformation(composer, "C10@359L20:ScheduleSendActivity.kt#l4w89x");
            if ((i & 3) == 2 && composer.getSkipping()) {
                composer.skipToGroupEnd();
                return;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1294739891, i, -1, "com.example.assistapp.features.ScheduleSharing.ComposableSingletons$ScheduleSendActivityKt.lambda-1.<anonymous> (ScheduleSendActivity.kt:10)");
            }
            ScheduleSendScreenKt.ScheduleSendScreen(composer, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
    });

    /* JADX INFO: renamed from: getLambda-1$app_release, reason: not valid java name */
    public final Function2<Composer, Integer, Unit> m7520getLambda1$app_release() {
        return f93lambda1;
    }
}
