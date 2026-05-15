package com.example.assistapp.features.LocationSharing;

import androidx.compose.foundation.layout.RowScope;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.lazy.LazyItemScope;
import androidx.compose.material3.ProgressIndicatorKt;
import androidx.compose.material3.TextKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.text.TextLayoutResult;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.text.font.FontFamily;
import androidx.compose.ui.text.font.FontStyle;
import androidx.compose.ui.text.font.FontWeight;
import androidx.compose.ui.text.style.TextAlign;
import androidx.compose.ui.text.style.TextDecoration;
import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: LocationSharingWithCodeScreen.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(k = 3, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
public final class ComposableSingletons$LocationSharingWithCodeScreenKt {
    public static final ComposableSingletons$LocationSharingWithCodeScreenKt INSTANCE = new ComposableSingletons$LocationSharingWithCodeScreenKt();

    /* JADX INFO: renamed from: lambda-1, reason: not valid java name */
    public static Function2<Composer, Integer, Unit> f84lambda1 = ComposableLambdaKt.composableLambdaInstance(-1759167339, false, new Function2<Composer, Integer, Unit>() { // from class: com.example.assistapp.features.LocationSharing.ComposableSingletons$LocationSharingWithCodeScreenKt$lambda-1$1
        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
            invoke(composer, num.intValue());
            return Unit.INSTANCE;
        }

        public final void invoke(Composer composer, int i) {
            ComposerKt.sourceInformation(composer, "C110@3740L17:LocationSharingWithCodeScreen.kt#l7p0sd");
            if ((i & 3) == 2 && composer.getSkipping()) {
                composer.skipToGroupEnd();
                return;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1759167339, i, -1, "com.example.assistapp.features.LocationSharing.ComposableSingletons$LocationSharingWithCodeScreenKt.lambda-1.<anonymous> (LocationSharingWithCodeScreen.kt:110)");
            }
            TextKt.m2996Text4IGK_g("상대방 암호 입력", (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, composer, 6, 0, 131070);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
    });

    /* JADX INFO: renamed from: lambda-2, reason: not valid java name */
    public static Function3<RowScope, Composer, Integer, Unit> f85lambda2 = ComposableLambdaKt.composableLambdaInstance(-214904088, false, new Function3<RowScope, Composer, Integer, Unit>() { // from class: com.example.assistapp.features.LocationSharing.ComposableSingletons$LocationSharingWithCodeScreenKt$lambda-2$1
        @Override // kotlin.jvm.functions.Function3
        public /* bridge */ /* synthetic */ Unit invoke(RowScope rowScope, Composer composer, Integer num) {
            invoke(rowScope, composer, num.intValue());
            return Unit.INSTANCE;
        }

        public final void invoke(RowScope Button, Composer composer, int i) {
            Intrinsics.checkNotNullParameter(Button, "$this$Button");
            ComposerKt.sourceInformation(composer, "C132@4435L13:LocationSharingWithCodeScreen.kt#l7p0sd");
            if ((i & 17) == 16 && composer.getSkipping()) {
                composer.skipToGroupEnd();
                return;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-214904088, i, -1, "com.example.assistapp.features.LocationSharing.ComposableSingletons$LocationSharingWithCodeScreenKt.lambda-2.<anonymous> (LocationSharingWithCodeScreen.kt:132)");
            }
            TextKt.m2996Text4IGK_g("추적 시작", (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, composer, 6, 0, 131070);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
    });

    /* JADX INFO: renamed from: lambda-3, reason: not valid java name */
    public static Function3<RowScope, Composer, Integer, Unit> f86lambda3 = ComposableLambdaKt.composableLambdaInstance(-943009150, false, new Function3<RowScope, Composer, Integer, Unit>() { // from class: com.example.assistapp.features.LocationSharing.ComposableSingletons$LocationSharingWithCodeScreenKt$lambda-3$1
        @Override // kotlin.jvm.functions.Function3
        public /* bridge */ /* synthetic */ Unit invoke(RowScope rowScope, Composer composer, Integer num) {
            invoke(rowScope, composer, num.intValue());
            return Unit.INSTANCE;
        }

        public final void invoke(RowScope OutlinedButton, Composer composer, int i) {
            Intrinsics.checkNotNullParameter(OutlinedButton, "$this$OutlinedButton");
            ComposerKt.sourceInformation(composer, "C153@5068L13:LocationSharingWithCodeScreen.kt#l7p0sd");
            if ((i & 17) == 16 && composer.getSkipping()) {
                composer.skipToGroupEnd();
                return;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-943009150, i, -1, "com.example.assistapp.features.LocationSharing.ComposableSingletons$LocationSharingWithCodeScreenKt.lambda-3.<anonymous> (LocationSharingWithCodeScreen.kt:153)");
            }
            TextKt.m2996Text4IGK_g("추적 중단", (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, composer, 6, 0, 131070);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
    });

    /* JADX INFO: renamed from: lambda-4, reason: not valid java name */
    public static Function3<LazyItemScope, Composer, Integer, Unit> f87lambda4 = ComposableLambdaKt.composableLambdaInstance(955521040, false, new Function3<LazyItemScope, Composer, Integer, Unit>() { // from class: com.example.assistapp.features.LocationSharing.ComposableSingletons$LocationSharingWithCodeScreenKt$lambda-4$1
        @Override // kotlin.jvm.functions.Function3
        public /* bridge */ /* synthetic */ Unit invoke(LazyItemScope lazyItemScope, Composer composer, Integer num) {
            invoke(lazyItemScope, composer, num.intValue());
            return Unit.INSTANCE;
        }

        public final void invoke(LazyItemScope item, Composer composer, int i) {
            Intrinsics.checkNotNullParameter(item, "$this$item");
            ComposerKt.sourceInformation(composer, "C175@5765L59:LocationSharingWithCodeScreen.kt#l7p0sd");
            if ((i & 17) == 16 && composer.getSkipping()) {
                composer.skipToGroupEnd();
                return;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(955521040, i, -1, "com.example.assistapp.features.LocationSharing.ComposableSingletons$LocationSharingWithCodeScreenKt.lambda-4.<anonymous> (LocationSharingWithCodeScreen.kt:175)");
            }
            ProgressIndicatorKt.m2679LinearProgressIndicatorrIrjwxo(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), 0L, 0L, 0, 0.0f, composer, 6, 30);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
    });

    /* JADX INFO: renamed from: getLambda-1$app_release, reason: not valid java name */
    public final Function2<Composer, Integer, Unit> m7505getLambda1$app_release() {
        return f84lambda1;
    }

    /* JADX INFO: renamed from: getLambda-2$app_release, reason: not valid java name */
    public final Function3<RowScope, Composer, Integer, Unit> m7506getLambda2$app_release() {
        return f85lambda2;
    }

    /* JADX INFO: renamed from: getLambda-3$app_release, reason: not valid java name */
    public final Function3<RowScope, Composer, Integer, Unit> m7507getLambda3$app_release() {
        return f86lambda3;
    }

    /* JADX INFO: renamed from: getLambda-4$app_release, reason: not valid java name */
    public final Function3<LazyItemScope, Composer, Integer, Unit> m7508getLambda4$app_release() {
        return f87lambda4;
    }
}
