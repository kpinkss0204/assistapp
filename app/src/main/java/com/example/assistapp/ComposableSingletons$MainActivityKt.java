package com.example.assistapp;

import androidx.compose.animation.AnimatedContentScope;
import androidx.compose.material3.MaterialThemeKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.example.assistapp.features.LocationSharing.LocationSharingWithCodeScreenKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: MainActivity.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(k = 3, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
public final class ComposableSingletons$MainActivityKt {
    public static final ComposableSingletons$MainActivityKt INSTANCE = new ComposableSingletons$MainActivityKt();

    /* JADX INFO: renamed from: lambda-1, reason: not valid java name */
    public static Function2<Composer, Integer, Unit> f80lambda1 = ComposableLambdaKt.composableLambdaInstance(-933366083, false, new Function2<Composer, Integer, Unit>() { // from class: com.example.assistapp.ComposableSingletons$MainActivityKt$lambda-1$1
        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
            invoke(composer, num.intValue());
            return Unit.INSTANCE;
        }

        public final void invoke(Composer composer, int i) {
            ComposerKt.sourceInformation(composer, "C38@1566L12:MainActivity.kt#wr12u1");
            if ((i & 3) == 2 && composer.getSkipping()) {
                composer.skipToGroupEnd();
                return;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-933366083, i, -1, "com.example.assistapp.ComposableSingletons$MainActivityKt.lambda-1.<anonymous> (MainActivity.kt:38)");
            }
            MainActivityKt.MainScreen(composer, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
    });

    /* JADX INFO: renamed from: lambda-2, reason: not valid java name */
    public static Function2<Composer, Integer, Unit> f81lambda2 = ComposableLambdaKt.composableLambdaInstance(1348309777, false, new Function2<Composer, Integer, Unit>() { // from class: com.example.assistapp.ComposableSingletons$MainActivityKt$lambda-2$1
        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
            invoke(composer, num.intValue());
            return Unit.INSTANCE;
        }

        public final void invoke(Composer composer, int i) {
            ComposerKt.sourceInformation(composer, "C37@1534L58:MainActivity.kt#wr12u1");
            if ((i & 3) != 2 || !composer.getSkipping()) {
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(1348309777, i, -1, "com.example.assistapp.ComposableSingletons$MainActivityKt.lambda-2.<anonymous> (MainActivity.kt:37)");
                }
                MaterialThemeKt.MaterialTheme(null, null, null, ComposableSingletons$MainActivityKt.INSTANCE.m7498getLambda1$app_release(), composer, 3072, 7);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                    return;
                }
                return;
            }
            composer.skipToGroupEnd();
        }
    });

    /* JADX INFO: renamed from: lambda-3, reason: not valid java name */
    public static Function4<AnimatedContentScope, Integer, Composer, Integer, Unit> f82lambda3 = ComposableLambdaKt.composableLambdaInstance(-18419747, false, new Function4<AnimatedContentScope, Integer, Composer, Integer, Unit>() { // from class: com.example.assistapp.ComposableSingletons$MainActivityKt$lambda-3$1
        @Override // kotlin.jvm.functions.Function4
        public /* bridge */ /* synthetic */ Unit invoke(AnimatedContentScope animatedContentScope, Integer num, Composer composer, Integer num2) {
            invoke(animatedContentScope, num.intValue(), composer, num2.intValue());
            return Unit.INSTANCE;
        }

        public final void invoke(AnimatedContentScope AnimatedContent, int i, Composer composer, int i2) {
            Intrinsics.checkNotNullParameter(AnimatedContent, "$this$AnimatedContent");
            ComposerKt.sourceInformation(composer, "C113@4692L31:MainActivity.kt#wr12u1");
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-18419747, i2, -1, "com.example.assistapp.ComposableSingletons$MainActivityKt.lambda-3.<anonymous> (MainActivity.kt:112)");
            }
            if (i == 0) {
                LocationSharingWithCodeScreenKt.LocationSharingWithCodeScreen(composer, 0);
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
    });

    /* JADX INFO: renamed from: getLambda-1$app_release, reason: not valid java name */
    public final Function2<Composer, Integer, Unit> m7498getLambda1$app_release() {
        return f80lambda1;
    }

    /* JADX INFO: renamed from: getLambda-2$app_release, reason: not valid java name */
    public final Function2<Composer, Integer, Unit> m7499getLambda2$app_release() {
        return f81lambda2;
    }

    /* JADX INFO: renamed from: getLambda-3$app_release, reason: not valid java name */
    public final Function4<AnimatedContentScope, Integer, Composer, Integer, Unit> m7500getLambda3$app_release() {
        return f82lambda3;
    }
}
