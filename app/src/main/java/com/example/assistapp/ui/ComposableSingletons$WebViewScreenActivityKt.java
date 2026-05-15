package com.example.assistapp.ui;

import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Modifier;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.example.assistapp.features.Webview.WebViewScreenKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;

/* JADX INFO: compiled from: WebViewScreenActivity.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(k = 3, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
public final class ComposableSingletons$WebViewScreenActivityKt {
    public static final ComposableSingletons$WebViewScreenActivityKt INSTANCE = new ComposableSingletons$WebViewScreenActivityKt();

    /* JADX INFO: renamed from: lambda-1, reason: not valid java name */
    public static Function2<Composer, Integer, Unit> f106lambda1 = ComposableLambdaKt.composableLambdaInstance(299413273, false, new Function2<Composer, Integer, Unit>() { // from class: com.example.assistapp.ui.ComposableSingletons$WebViewScreenActivityKt$lambda-1$1
        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
            invoke(composer, num.intValue());
            return Unit.INSTANCE;
        }

        public final void invoke(Composer composer, int i) {
            ComposerKt.sourceInformation(composer, "C13@542L125:WebViewScreenActivity.kt#hif0b");
            if ((i & 3) != 2 || !composer.getSkipping()) {
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(299413273, i, -1, "com.example.assistapp.ui.ComposableSingletons$WebViewScreenActivityKt.lambda-1.<anonymous> (WebViewScreenActivity.kt:13)");
                }
                WebViewScreenKt.WebViewScreen("http://www.hsb.or.kr/", SizeKt.fillMaxSize$default(Modifier.INSTANCE, 0.0f, 1, null), composer, 54, 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                    return;
                }
                return;
            }
            composer.skipToGroupEnd();
        }
    });

    /* JADX INFO: renamed from: getLambda-1$app_release, reason: not valid java name */
    public final Function2<Composer, Integer, Unit> m7540getLambda1$app_release() {
        return f106lambda1;
    }
}
