package com.google.accompanist.pager;

import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.saveable.RememberSaveableKt;
import androidx.compose.runtime.saveable.Saver;
import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.jvm.functions.Function0;

/* JADX INFO: compiled from: PagerState.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\u001a\u0017\u0010\u0000\u001a\u00020\u00012\b\b\u0003\u0010\u0002\u001a\u00020\u0003H\u0007¢\u0006\u0002\u0010\u0004¨\u0006\u0005"}, d2 = {"rememberPagerState", "Lcom/google/accompanist/pager/PagerState;", "initialPage", "", "(ILandroidx/compose/runtime/Composer;II)Lcom/google/accompanist/pager/PagerState;", "pager_release"}, k = 2, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
public final class PagerStateKt {
    @Deprecated(message = "\naccompanist/pager is deprecated.\nThe androidx.compose equivalent of rememberPagerState is androidx.compose.foundation.pager.rememberPagerState().\nFor more migration information, please visit https://google.github.io/accompanist/pager/#migration\n", replaceWith = @ReplaceWith(expression = "androidx.compose.foundation.pager.rememberPagerState(initialPage = initialPage)", imports = {"androidx.compose.foundation.pager.rememberPagerState"}))
    public static final PagerState rememberPagerState(final int i, Composer composer, int i2, int i3) {
        composer.startReplaceableGroup(1352421093);
        ComposerKt.sourceInformation(composer, "C(rememberPagerState)");
        if ((i3 & 1) != 0) {
            i = 0;
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1352421093, i2, -1, "com.google.accompanist.pager.rememberPagerState (PagerState.kt:66)");
        }
        Object[] objArr = new Object[0];
        Saver<PagerState, ?> saver = PagerState.INSTANCE.getSaver();
        Integer numValueOf = Integer.valueOf(i);
        composer.startReplaceableGroup(1157296644);
        ComposerKt.sourceInformation(composer, "CC(remember)P(1):Composables.kt#9igjgp");
        boolean zChanged = composer.changed(numValueOf);
        Object objRememberedValue = composer.rememberedValue();
        if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
            objRememberedValue = (Function0) new Function0<PagerState>() { // from class: com.google.accompanist.pager.PagerStateKt$rememberPagerState$1$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                /* JADX WARN: Can't rename method to resolve collision */
                @Override // kotlin.jvm.functions.Function0
                public final PagerState invoke() {
                    return new PagerState(i);
                }
            };
            composer.updateRememberedValue(objRememberedValue);
        }
        composer.endReplaceableGroup();
        PagerState pagerState = (PagerState) RememberSaveableKt.m4139rememberSaveable(objArr, (Saver) saver, (String) null, (Function0) objRememberedValue, composer, 72, 4);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
        return pagerState;
    }
}
