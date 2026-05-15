package androidx.compose.material;

import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;

/* JADX INFO: compiled from: Scaffold.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(k = 3, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
public final class ComposableSingletons$ScaffoldKt {
    public static final ComposableSingletons$ScaffoldKt INSTANCE = new ComposableSingletons$ScaffoldKt();

    /* JADX INFO: renamed from: lambda-1, reason: not valid java name */
    public static Function2<Composer, Integer, Unit> f11lambda1 = ComposableLambdaKt.composableLambdaInstance(20777959, false, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.ComposableSingletons$ScaffoldKt$lambda-1$1
        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
            invoke(composer, num.intValue());
            return Unit.INSTANCE;
        }

        public final void invoke(Composer composer, int i) {
            ComposerKt.sourceInformation(composer, "C:Scaffold.kt#jmzs0o");
            if ((i & 3) == 2 && composer.getSkipping()) {
                composer.skipToGroupEnd();
                return;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(20777959, i, -1, "androidx.compose.material.ComposableSingletons$ScaffoldKt.lambda-1.<anonymous> (Scaffold.kt:185)");
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
    });

    /* JADX INFO: renamed from: lambda-2, reason: not valid java name */
    public static Function2<Composer, Integer, Unit> f12lambda2 = ComposableLambdaKt.composableLambdaInstance(-212732681, false, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.ComposableSingletons$ScaffoldKt$lambda-2$1
        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
            invoke(composer, num.intValue());
            return Unit.INSTANCE;
        }

        public final void invoke(Composer composer, int i) {
            ComposerKt.sourceInformation(composer, "C:Scaffold.kt#jmzs0o");
            if ((i & 3) == 2 && composer.getSkipping()) {
                composer.skipToGroupEnd();
                return;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-212732681, i, -1, "androidx.compose.material.ComposableSingletons$ScaffoldKt.lambda-2.<anonymous> (Scaffold.kt:186)");
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
    });

    /* JADX INFO: renamed from: lambda-3, reason: not valid java name */
    public static Function3<SnackbarHostState, Composer, Integer, Unit> f13lambda3 = ComposableLambdaKt.composableLambdaInstance(2086581188, false, new Function3<SnackbarHostState, Composer, Integer, Unit>() { // from class: androidx.compose.material.ComposableSingletons$ScaffoldKt$lambda-3$1
        @Override // kotlin.jvm.functions.Function3
        public /* bridge */ /* synthetic */ Unit invoke(SnackbarHostState snackbarHostState, Composer composer, Integer num) {
            invoke(snackbarHostState, composer, num.intValue());
            return Unit.INSTANCE;
        }

        public final void invoke(SnackbarHostState snackbarHostState, Composer composer, int i) {
            ComposerKt.sourceInformation(composer, "C187@8526L16:Scaffold.kt#jmzs0o");
            if ((i & 6) == 0) {
                i |= composer.changed(snackbarHostState) ? 4 : 2;
            }
            if ((i & 19) == 18 && composer.getSkipping()) {
                composer.skipToGroupEnd();
                return;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(2086581188, i, -1, "androidx.compose.material.ComposableSingletons$ScaffoldKt.lambda-3.<anonymous> (Scaffold.kt:187)");
            }
            SnackbarHostKt.SnackbarHost(snackbarHostState, null, null, composer, i & 14, 6);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
    });

    /* JADX INFO: renamed from: lambda-4, reason: not valid java name */
    public static Function2<Composer, Integer, Unit> f14lambda4 = ComposableLambdaKt.composableLambdaInstance(10046167, false, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.ComposableSingletons$ScaffoldKt$lambda-4$1
        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
            invoke(composer, num.intValue());
            return Unit.INSTANCE;
        }

        public final void invoke(Composer composer, int i) {
            ComposerKt.sourceInformation(composer, "C:Scaffold.kt#jmzs0o");
            if ((i & 3) == 2 && composer.getSkipping()) {
                composer.skipToGroupEnd();
                return;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(10046167, i, -1, "androidx.compose.material.ComposableSingletons$ScaffoldKt.lambda-4.<anonymous> (Scaffold.kt:188)");
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
    });

    /* JADX INFO: renamed from: lambda-5, reason: not valid java name */
    public static Function2<Composer, Integer, Unit> f15lambda5 = ComposableLambdaKt.composableLambdaInstance(2069405901, false, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.ComposableSingletons$ScaffoldKt$lambda-5$1
        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
            invoke(composer, num.intValue());
            return Unit.INSTANCE;
        }

        public final void invoke(Composer composer, int i) {
            ComposerKt.sourceInformation(composer, "C:Scaffold.kt#jmzs0o");
            if ((i & 3) == 2 && composer.getSkipping()) {
                composer.skipToGroupEnd();
                return;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(2069405901, i, -1, "androidx.compose.material.ComposableSingletons$ScaffoldKt.lambda-5.<anonymous> (Scaffold.kt:313)");
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
    });

    /* JADX INFO: renamed from: lambda-6, reason: not valid java name */
    public static Function2<Composer, Integer, Unit> f16lambda6 = ComposableLambdaKt.composableLambdaInstance(-231850563, false, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.ComposableSingletons$ScaffoldKt$lambda-6$1
        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
            invoke(composer, num.intValue());
            return Unit.INSTANCE;
        }

        public final void invoke(Composer composer, int i) {
            ComposerKt.sourceInformation(composer, "C:Scaffold.kt#jmzs0o");
            if ((i & 3) == 2 && composer.getSkipping()) {
                composer.skipToGroupEnd();
                return;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-231850563, i, -1, "androidx.compose.material.ComposableSingletons$ScaffoldKt.lambda-6.<anonymous> (Scaffold.kt:314)");
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
    });

    /* JADX INFO: renamed from: lambda-7, reason: not valid java name */
    public static Function3<SnackbarHostState, Composer, Integer, Unit> f17lambda7 = ComposableLambdaKt.composableLambdaInstance(-147687984, false, new Function3<SnackbarHostState, Composer, Integer, Unit>() { // from class: androidx.compose.material.ComposableSingletons$ScaffoldKt$lambda-7$1
        @Override // kotlin.jvm.functions.Function3
        public /* bridge */ /* synthetic */ Unit invoke(SnackbarHostState snackbarHostState, Composer composer, Integer num) {
            invoke(snackbarHostState, composer, num.intValue());
            return Unit.INSTANCE;
        }

        public final void invoke(SnackbarHostState snackbarHostState, Composer composer, int i) {
            ComposerKt.sourceInformation(composer, "C315@14971L16:Scaffold.kt#jmzs0o");
            if ((i & 6) == 0) {
                i |= composer.changed(snackbarHostState) ? 4 : 2;
            }
            if ((i & 19) == 18 && composer.getSkipping()) {
                composer.skipToGroupEnd();
                return;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-147687984, i, -1, "androidx.compose.material.ComposableSingletons$ScaffoldKt.lambda-7.<anonymous> (Scaffold.kt:315)");
            }
            SnackbarHostKt.SnackbarHost(snackbarHostState, null, null, composer, i & 14, 6);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
    });

    /* JADX INFO: renamed from: lambda-8, reason: not valid java name */
    public static Function2<Composer, Integer, Unit> f18lambda8 = ComposableLambdaKt.composableLambdaInstance(-900670499, false, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.ComposableSingletons$ScaffoldKt$lambda-8$1
        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
            invoke(composer, num.intValue());
            return Unit.INSTANCE;
        }

        public final void invoke(Composer composer, int i) {
            ComposerKt.sourceInformation(composer, "C:Scaffold.kt#jmzs0o");
            if ((i & 3) == 2 && composer.getSkipping()) {
                composer.skipToGroupEnd();
                return;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-900670499, i, -1, "androidx.compose.material.ComposableSingletons$ScaffoldKt.lambda-8.<anonymous> (Scaffold.kt:316)");
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
    });

    /* JADX INFO: renamed from: getLambda-1$material_release, reason: not valid java name */
    public final Function2<Composer, Integer, Unit> m1807getLambda1$material_release() {
        return f11lambda1;
    }

    /* JADX INFO: renamed from: getLambda-2$material_release, reason: not valid java name */
    public final Function2<Composer, Integer, Unit> m1808getLambda2$material_release() {
        return f12lambda2;
    }

    /* JADX INFO: renamed from: getLambda-3$material_release, reason: not valid java name */
    public final Function3<SnackbarHostState, Composer, Integer, Unit> m1809getLambda3$material_release() {
        return f13lambda3;
    }

    /* JADX INFO: renamed from: getLambda-4$material_release, reason: not valid java name */
    public final Function2<Composer, Integer, Unit> m1810getLambda4$material_release() {
        return f14lambda4;
    }

    /* JADX INFO: renamed from: getLambda-5$material_release, reason: not valid java name */
    public final Function2<Composer, Integer, Unit> m1811getLambda5$material_release() {
        return f15lambda5;
    }

    /* JADX INFO: renamed from: getLambda-6$material_release, reason: not valid java name */
    public final Function2<Composer, Integer, Unit> m1812getLambda6$material_release() {
        return f16lambda6;
    }

    /* JADX INFO: renamed from: getLambda-7$material_release, reason: not valid java name */
    public final Function3<SnackbarHostState, Composer, Integer, Unit> m1813getLambda7$material_release() {
        return f17lambda7;
    }

    /* JADX INFO: renamed from: getLambda-8$material_release, reason: not valid java name */
    public final Function2<Composer, Integer, Unit> m1814getLambda8$material_release() {
        return f18lambda8;
    }
}
