package androidx.compose.material3;

import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.PaddingValues;
import androidx.compose.material3.tokens.DialogTokens;
import androidx.compose.runtime.Applier;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.window.DialogProperties;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.accessibility.AccessibilityEventCompat;
import androidx.profileinstaller.ProfileVerifier;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;

/* JADX INFO: compiled from: AlertDialog.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000@\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0013\u001aB\u0010\u000e\u001a\u00020\u000f2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u000f0\u00112\b\b\u0002\u0010\u0012\u001a\u00020\u00132\b\b\u0002\u0010\u0014\u001a\u00020\u00152\u0011\u0010\u0016\u001a\r\u0012\u0004\u0012\u00020\u000f0\u0011¢\u0006\u0002\b\u0017H\u0007¢\u0006\u0002\u0010\u0018\u001a¦\u0001\u0010\u0019\u001a\u00020\u000f2\u0011\u0010\u001a\u001a\r\u0012\u0004\u0012\u00020\u000f0\u0011¢\u0006\u0002\b\u00172\b\b\u0002\u0010\u0012\u001a\u00020\u00132\u0013\u0010\u001b\u001a\u000f\u0012\u0004\u0012\u00020\u000f\u0018\u00010\u0011¢\u0006\u0002\b\u00172\u0013\u0010\u001c\u001a\u000f\u0012\u0004\u0012\u00020\u000f\u0018\u00010\u0011¢\u0006\u0002\b\u00172\u0013\u0010\u001d\u001a\u000f\u0012\u0004\u0012\u00020\u000f\u0018\u00010\u0011¢\u0006\u0002\b\u00172\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020\u00012\u0006\u0010#\u001a\u00020!2\u0006\u0010$\u001a\u00020!2\u0006\u0010%\u001a\u00020!2\u0006\u0010&\u001a\u00020!H\u0001ø\u0001\u0000¢\u0006\u0004\b'\u0010(\u001a5\u0010)\u001a\u00020\u000f2\u0006\u0010*\u001a\u00020\u00012\u0006\u0010+\u001a\u00020\u00012\u0011\u0010\u0016\u001a\r\u0012\u0004\u0012\u00020\u000f0\u0011¢\u0006\u0002\b\u0017H\u0001ø\u0001\u0000¢\u0006\u0004\b,\u0010-\u001aÇ\u0001\u0010.\u001a\u00020\u000f2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u000f0\u00112\u0011\u0010/\u001a\r\u0012\u0004\u0012\u00020\u000f0\u0011¢\u0006\u0002\b\u00172\u0006\u0010\u0012\u001a\u00020\u00132\u0013\u00100\u001a\u000f\u0012\u0004\u0012\u00020\u000f\u0018\u00010\u0011¢\u0006\u0002\b\u00172\u0013\u0010\u001b\u001a\u000f\u0012\u0004\u0012\u00020\u000f\u0018\u00010\u0011¢\u0006\u0002\b\u00172\u0013\u0010\u001c\u001a\u000f\u0012\u0004\u0012\u00020\u000f\u0018\u00010\u0011¢\u0006\u0002\b\u00172\u0013\u0010\u001d\u001a\u000f\u0012\u0004\u0012\u00020\u000f\u0018\u00010\u0011¢\u0006\u0002\b\u00172\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020!2\u0006\u0010$\u001a\u00020!2\u0006\u0010%\u001a\u00020!2\u0006\u0010&\u001a\u00020!2\u0006\u0010\"\u001a\u00020\u00012\u0006\u0010\u0014\u001a\u00020\u0015H\u0001ø\u0001\u0000¢\u0006\u0004\b1\u00102\u001aB\u00103\u001a\u00020\u000f2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u000f0\u00112\b\b\u0002\u0010\u0012\u001a\u00020\u00132\b\b\u0002\u0010\u0014\u001a\u00020\u00152\u0011\u0010\u0016\u001a\r\u0012\u0004\u0012\u00020\u000f0\u0011¢\u0006\u0002\b\u0017H\u0007¢\u0006\u0002\u0010\u0018\"\u0010\u0010\u0000\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0002\"\u0010\u0010\u0003\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0002\"\u0016\u0010\u0004\u001a\u00020\u0001X\u0080\u0004¢\u0006\n\n\u0002\u0010\u0002\u001a\u0004\b\u0005\u0010\u0006\"\u0016\u0010\u0007\u001a\u00020\u0001X\u0080\u0004¢\u0006\n\n\u0002\u0010\u0002\u001a\u0004\b\b\u0010\u0006\"\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010\u000b\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010\f\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010\r\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u00064"}, d2 = {"ButtonsCrossAxisSpacing", "Landroidx/compose/ui/unit/Dp;", "F", "ButtonsMainAxisSpacing", "DialogMaxWidth", "getDialogMaxWidth", "()F", "DialogMinWidth", "getDialogMinWidth", "DialogPadding", "Landroidx/compose/foundation/layout/PaddingValues;", "IconPadding", "TextPadding", "TitlePadding", "AlertDialog", "", "onDismissRequest", "Lkotlin/Function0;", "modifier", "Landroidx/compose/ui/Modifier;", "properties", "Landroidx/compose/ui/window/DialogProperties;", FirebaseAnalytics.Param.CONTENT, "Landroidx/compose/runtime/Composable;", "(Lkotlin/jvm/functions/Function0;Landroidx/compose/ui/Modifier;Landroidx/compose/ui/window/DialogProperties;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;II)V", "AlertDialogContent", "buttons", "icon", "title", "text", "shape", "Landroidx/compose/ui/graphics/Shape;", "containerColor", "Landroidx/compose/ui/graphics/Color;", "tonalElevation", "buttonContentColor", "iconContentColor", "titleContentColor", "textContentColor", "AlertDialogContent-4hvqGtA", "(Lkotlin/jvm/functions/Function2;Landroidx/compose/ui/Modifier;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Landroidx/compose/ui/graphics/Shape;JFJJJJLandroidx/compose/runtime/Composer;III)V", "AlertDialogFlowRow", "mainAxisSpacing", "crossAxisSpacing", "AlertDialogFlowRow-ixp7dh8", "(FFLkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;I)V", "AlertDialogImpl", "confirmButton", "dismissButton", "AlertDialogImpl-wrnwzgE", "(Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function2;Landroidx/compose/ui/Modifier;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Landroidx/compose/ui/graphics/Shape;JJJJFLandroidx/compose/ui/window/DialogProperties;Landroidx/compose/runtime/Composer;II)V", "BasicAlertDialog", "material3_release"}, k = 2, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
public final class AlertDialogKt {
    private static final PaddingValues DialogPadding;
    private static final PaddingValues IconPadding;
    private static final PaddingValues TextPadding;
    private static final PaddingValues TitlePadding;
    private static final float DialogMinWidth = Dp.m7027constructorimpl(280);
    private static final float DialogMaxWidth = Dp.m7027constructorimpl(560);
    private static final float ButtonsMainAxisSpacing = Dp.m7027constructorimpl(8);
    private static final float ButtonsCrossAxisSpacing = Dp.m7027constructorimpl(12);

    /* JADX WARN: Removed duplicated region for block: B:26:0x0050  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0053  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x006c  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x006f  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x0085  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x0092  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x0094  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x0099  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x009c  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x00a8  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x00af  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x00dc  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x00e7  */
    /* JADX WARN: Removed duplicated region for block: B:68:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final void BasicAlertDialog(final kotlin.jvm.functions.Function0<kotlin.Unit> r16, androidx.compose.ui.Modifier r17, androidx.compose.ui.window.DialogProperties r18, final kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r19, androidx.compose.runtime.Composer r20, final int r21, final int r22) {
        /*
            Method dump skipped, instruction units count: 246
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.AlertDialogKt.BasicAlertDialog(kotlin.jvm.functions.Function0, androidx.compose.ui.Modifier, androidx.compose.ui.window.DialogProperties, kotlin.jvm.functions.Function2, androidx.compose.runtime.Composer, int, int):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:26:0x004e  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0051  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x006a  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x006f  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x0087  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x0094  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x0096  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x009b  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x009e  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x00ab  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x00b2  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x00c6  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x00d1  */
    /* JADX WARN: Removed duplicated region for block: B:68:? A[RETURN, SYNTHETIC] */
    @kotlin.Deprecated(message = "Use BasicAlertDialog instead", replaceWith = @kotlin.ReplaceWith(expression = "BasicAlertDialog(onDismissRequest, modifier, properties, content)", imports = {}))
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final void AlertDialog(final kotlin.jvm.functions.Function0<kotlin.Unit> r17, androidx.compose.ui.Modifier r18, androidx.compose.ui.window.DialogProperties r19, final kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r20, androidx.compose.runtime.Composer r21, final int r22, final int r23) {
        /*
            Method dump skipped, instruction units count: 226
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.AlertDialogKt.AlertDialog(kotlin.jvm.functions.Function0, androidx.compose.ui.Modifier, androidx.compose.ui.window.DialogProperties, kotlin.jvm.functions.Function2, androidx.compose.runtime.Composer, int, int):void");
    }

    /* JADX INFO: renamed from: AlertDialogImpl-wrnwzgE, reason: not valid java name */
    public static final void m2061AlertDialogImplwrnwzgE(final Function0<Unit> function0, final Function2<? super Composer, ? super Integer, Unit> function2, final Modifier modifier, final Function2<? super Composer, ? super Integer, Unit> function22, final Function2<? super Composer, ? super Integer, Unit> function23, final Function2<? super Composer, ? super Integer, Unit> function24, final Function2<? super Composer, ? super Integer, Unit> function25, final Shape shape, final long j, final long j2, final long j3, final long j4, final float f, final DialogProperties dialogProperties, Composer composer, final int i, final int i2) {
        int i3;
        Function2<? super Composer, ? super Integer, Unit> function26;
        Function2<? super Composer, ? super Integer, Unit> function27;
        Function2<? super Composer, ? super Integer, Unit> function28;
        int i4;
        float f2;
        Composer composerStartRestartGroup = composer.startRestartGroup(-919826268);
        ComposerKt.sourceInformation(composerStartRestartGroup, "C(AlertDialogImpl)P(6!1,5,2,3,11,9,8,1:c#ui.graphics.Color,4:c#ui.graphics.Color,12:c#ui.graphics.Color,10:c#ui.graphics.Color,13:c#ui.unit.Dp)251@10652L1109,247@10522L1239:AlertDialog.kt#uh7d8r");
        if ((i & 6) == 0) {
            i3 = (composerStartRestartGroup.changedInstance(function0) ? 4 : 2) | i;
        } else {
            i3 = i;
        }
        if ((i & 48) == 0) {
            function26 = function2;
            i3 |= composerStartRestartGroup.changedInstance(function26) ? 32 : 16;
        } else {
            function26 = function2;
        }
        if ((i & 384) == 0) {
            i3 |= composerStartRestartGroup.changed(modifier) ? 256 : 128;
        }
        if ((i & 3072) == 0) {
            function27 = function22;
            i3 |= composerStartRestartGroup.changedInstance(function27) ? 2048 : 1024;
        } else {
            function27 = function22;
        }
        if ((i & 24576) == 0) {
            function28 = function23;
            i3 |= composerStartRestartGroup.changedInstance(function28) ? 16384 : 8192;
        } else {
            function28 = function23;
        }
        if ((i & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
            i3 |= composerStartRestartGroup.changedInstance(function24) ? 131072 : 65536;
        }
        if ((i & 1572864) == 0) {
            i3 |= composerStartRestartGroup.changedInstance(function25) ? 1048576 : 524288;
        }
        if ((i & 12582912) == 0) {
            i3 |= composerStartRestartGroup.changed(shape) ? 8388608 : 4194304;
        }
        if ((i & 100663296) == 0) {
            i3 |= composerStartRestartGroup.changed(j) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
        }
        if ((i & 805306368) == 0) {
            i3 |= composerStartRestartGroup.changed(j2) ? 536870912 : 268435456;
        }
        if ((i2 & 6) == 0) {
            i4 = i2 | (composerStartRestartGroup.changed(j3) ? 4 : 2);
        } else {
            i4 = i2;
        }
        if ((i2 & 48) == 0) {
            i4 |= composerStartRestartGroup.changed(j4) ? 32 : 16;
        }
        if ((i2 & 384) == 0) {
            f2 = f;
            i4 |= composerStartRestartGroup.changed(f2) ? 256 : 128;
        } else {
            f2 = f;
        }
        if ((i2 & 3072) == 0) {
            i4 |= composerStartRestartGroup.changed(dialogProperties) ? 2048 : 1024;
        }
        int i5 = i4;
        if ((i3 & 306783379) != 306783378 || (i5 & 1171) != 1170 || !composerStartRestartGroup.getSkipping()) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-919826268, i3, i5, "androidx.compose.material3.AlertDialogImpl (AlertDialog.kt:247)");
            }
            final Function2<? super Composer, ? super Integer, Unit> function29 = function26;
            final Function2<? super Composer, ? super Integer, Unit> function210 = function27;
            final float f3 = f2;
            final Function2<? super Composer, ? super Integer, Unit> function211 = function28;
            BasicAlertDialog(function0, modifier, dialogProperties, ComposableLambdaKt.rememberComposableLambda(-1852840226, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.AlertDialogKt$AlertDialogImpl$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                    invoke(composer2, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer composer2, int i6) {
                    ComposerKt.sourceInformation(composer2, "C253@10704L294,272@11590L5,252@10662L1093:AlertDialog.kt#uh7d8r");
                    if ((i6 & 3) != 2 || !composer2.getSkipping()) {
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-1852840226, i6, -1, "androidx.compose.material3.AlertDialogImpl.<anonymous> (AlertDialog.kt:252)");
                        }
                        final Function2<Composer, Integer, Unit> function212 = function210;
                        final Function2<Composer, Integer, Unit> function213 = function29;
                        AlertDialogKt.m2059AlertDialogContent4hvqGtA(ComposableLambdaKt.rememberComposableLambda(1163543932, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.AlertDialogKt$AlertDialogImpl$1.1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            /* JADX WARN: Multi-variable type inference failed */
                            {
                                super(2);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                                invoke(composer3, num.intValue());
                                return Unit.INSTANCE;
                            }

                            public final void invoke(Composer composer3, int i7) {
                                ComposerKt.sourceInformation(composer3, "C257@10885L99,254@10722L262:AlertDialog.kt#uh7d8r");
                                if ((i7 & 3) != 2 || !composer3.getSkipping()) {
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(1163543932, i7, -1, "androidx.compose.material3.AlertDialogImpl.<anonymous>.<anonymous> (AlertDialog.kt:254)");
                                    }
                                    float f4 = AlertDialogKt.ButtonsMainAxisSpacing;
                                    float f5 = AlertDialogKt.ButtonsCrossAxisSpacing;
                                    final Function2<Composer, Integer, Unit> function214 = function212;
                                    final Function2<Composer, Integer, Unit> function215 = function213;
                                    AlertDialogKt.m2060AlertDialogFlowRowixp7dh8(f4, f5, ComposableLambdaKt.rememberComposableLambda(1887135077, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.AlertDialogKt.AlertDialogImpl.1.1.1
                                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                        /* JADX WARN: Multi-variable type inference failed */
                                        {
                                            super(2);
                                        }

                                        @Override // kotlin.jvm.functions.Function2
                                        public /* bridge */ /* synthetic */ Unit invoke(Composer composer4, Integer num) {
                                            invoke(composer4, num.intValue());
                                            return Unit.INSTANCE;
                                        }

                                        public final void invoke(Composer composer4, int i8) {
                                            ComposerKt.sourceInformation(composer4, "C259@10951L15:AlertDialog.kt#uh7d8r");
                                            if ((i8 & 3) != 2 || !composer4.getSkipping()) {
                                                if (ComposerKt.isTraceInProgress()) {
                                                    ComposerKt.traceEventStart(1887135077, i8, -1, "androidx.compose.material3.AlertDialogImpl.<anonymous>.<anonymous>.<anonymous> (AlertDialog.kt:258)");
                                                }
                                                Function2<Composer, Integer, Unit> function216 = function214;
                                                composer4.startReplaceGroup(1497073862);
                                                ComposerKt.sourceInformation(composer4, "258@10922L8");
                                                if (function216 != null) {
                                                    function216.invoke(composer4, 0);
                                                    Unit unit = Unit.INSTANCE;
                                                }
                                                composer4.endReplaceGroup();
                                                function215.invoke(composer4, 0);
                                                if (ComposerKt.isTraceInProgress()) {
                                                    ComposerKt.traceEventEnd();
                                                    return;
                                                }
                                                return;
                                            }
                                            composer4.skipToGroupEnd();
                                        }
                                    }, composer3, 54), composer3, 438);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                        return;
                                    }
                                    return;
                                }
                                composer3.skipToGroupEnd();
                            }
                        }, composer2, 54), null, function211, function24, function25, shape, j, f3, ColorSchemeKt.getValue(DialogTokens.INSTANCE.getActionLabelTextColor(), composer2, 6), j2, j3, j4, composer2, 6, 0, 2);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                            return;
                        }
                        return;
                    }
                    composer2.skipToGroupEnd();
                }
            }, composerStartRestartGroup, 54), composerStartRestartGroup, (i3 & 14) | 3072 | ((i3 >> 3) & 112) | ((i5 >> 3) & 896), 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composerStartRestartGroup.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.AlertDialogKt$AlertDialogImpl$2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                    invoke(composer2, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer composer2, int i6) {
                    AlertDialogKt.m2061AlertDialogImplwrnwzgE(function0, function2, modifier, function22, function23, function24, function25, shape, j, j2, j3, j4, f, dialogProperties, composer2, RecomposeScopeImplKt.updateChangedFlags(i | 1), RecomposeScopeImplKt.updateChangedFlags(i2));
                }
            });
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:100:0x011f  */
    /* JADX WARN: Removed duplicated region for block: B:101:0x0124  */
    /* JADX WARN: Removed duplicated region for block: B:110:0x013a  */
    /* JADX WARN: Removed duplicated region for block: B:111:0x013f  */
    /* JADX WARN: Removed duplicated region for block: B:121:0x0159  */
    /* JADX WARN: Removed duplicated region for block: B:122:0x015c  */
    /* JADX WARN: Removed duplicated region for block: B:132:0x017c  */
    /* JADX WARN: Removed duplicated region for block: B:138:0x018e  */
    /* JADX WARN: Removed duplicated region for block: B:139:0x0190  */
    /* JADX WARN: Removed duplicated region for block: B:140:0x0195  */
    /* JADX WARN: Removed duplicated region for block: B:143:0x019c  */
    /* JADX WARN: Removed duplicated region for block: B:146:0x01ef  */
    /* JADX WARN: Removed duplicated region for block: B:150:0x01f9  */
    /* JADX WARN: Removed duplicated region for block: B:152:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0050  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0055  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x006b  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x006e  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x0087  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x008a  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x00a5  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x00a8  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x00c4  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x00c9  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x00e2  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x00e5  */
    /* JADX WARN: Removed duplicated region for block: B:90:0x0101  */
    /* JADX WARN: Removed duplicated region for block: B:91:0x0106  */
    /* JADX INFO: renamed from: AlertDialogContent-4hvqGtA, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final void m2059AlertDialogContent4hvqGtA(final kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r29, androidx.compose.ui.Modifier r30, final kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r31, final kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r32, final kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r33, final androidx.compose.ui.graphics.Shape r34, final long r35, final float r37, final long r38, final long r40, final long r42, final long r44, androidx.compose.runtime.Composer r46, final int r47, final int r48, final int r49) {
        /*
            Method dump skipped, instruction units count: 549
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.AlertDialogKt.m2059AlertDialogContent4hvqGtA(kotlin.jvm.functions.Function2, androidx.compose.ui.Modifier, kotlin.jvm.functions.Function2, kotlin.jvm.functions.Function2, kotlin.jvm.functions.Function2, androidx.compose.ui.graphics.Shape, long, float, long, long, long, long, androidx.compose.runtime.Composer, int, int, int):void");
    }

    /* JADX INFO: renamed from: AlertDialogFlowRow-ixp7dh8, reason: not valid java name */
    public static final void m2060AlertDialogFlowRowixp7dh8(final float f, final float f2, final Function2<? super Composer, ? super Integer, Unit> function2, Composer composer, final int i) {
        int i2;
        Composer composerStartRestartGroup = composer.startRestartGroup(586821353);
        ComposerKt.sourceInformation(composerStartRestartGroup, "C(AlertDialogFlowRow)P(2:c#ui.unit.Dp,1:c#ui.unit.Dp)365@14664L3370,365@14648L3386:AlertDialog.kt#uh7d8r");
        if ((i & 6) == 0) {
            i2 = (composerStartRestartGroup.changed(f) ? 4 : 2) | i;
        } else {
            i2 = i;
        }
        if ((i & 48) == 0) {
            i2 |= composerStartRestartGroup.changed(f2) ? 32 : 16;
        }
        if ((i & 384) == 0) {
            i2 |= composerStartRestartGroup.changedInstance(function2) ? 256 : 128;
        }
        if ((i2 & 147) != 146 || !composerStartRestartGroup.getSkipping()) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(586821353, i2, -1, "androidx.compose.material3.AlertDialogFlowRow (AlertDialog.kt:364)");
            }
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -767073803, "CC(remember):AlertDialog.kt#9igjgp");
            boolean z = ((i2 & 14) == 4) | ((i2 & 112) == 32);
            MeasurePolicy measurePolicyRememberedValue = composerStartRestartGroup.rememberedValue();
            if (z || measurePolicyRememberedValue == Composer.INSTANCE.getEmpty()) {
                measurePolicyRememberedValue = new MeasurePolicy() { // from class: androidx.compose.material3.AlertDialogKt$AlertDialogFlowRow$1$1
                    @Override // androidx.compose.ui.layout.MeasurePolicy
                    /* JADX INFO: renamed from: measure-3p2s80s */
                    public final MeasureResult mo322measure3p2s80s(final MeasureScope measureScope, List<? extends Measurable> list, long j) {
                        int i3;
                        ArrayList arrayList = new ArrayList();
                        ArrayList arrayList2 = new ArrayList();
                        final ArrayList arrayList3 = new ArrayList();
                        Ref.IntRef intRef = new Ref.IntRef();
                        Ref.IntRef intRef2 = new Ref.IntRef();
                        ArrayList arrayList4 = new ArrayList();
                        Ref.IntRef intRef3 = new Ref.IntRef();
                        Ref.IntRef intRef4 = new Ref.IntRef();
                        float f3 = f;
                        float f4 = f2;
                        int size = list.size();
                        int i4 = 0;
                        while (i4 < size) {
                            int i5 = size;
                            ArrayList arrayList5 = arrayList;
                            Placeable placeableMo5903measureBRTryo0 = list.get(i4).mo5903measureBRTryo0(j);
                            Ref.IntRef intRef5 = intRef3;
                            intRef3 = intRef5;
                            if (measure_3p2s80s$canAddToCurrentSequence(arrayList4, intRef5, measureScope, f3, j, placeableMo5903measureBRTryo0)) {
                                i3 = i4;
                                arrayList = arrayList5;
                            } else {
                                i3 = i4;
                                arrayList = arrayList5;
                                measure_3p2s80s$startNewSequence(arrayList, intRef2, measureScope, f4, arrayList4, arrayList2, intRef4, arrayList3, intRef, intRef3);
                            }
                            if (!arrayList4.isEmpty()) {
                                intRef3.element += measureScope.mo651roundToPx0680j_4(f3);
                            }
                            arrayList4.add(placeableMo5903measureBRTryo0);
                            intRef3.element += placeableMo5903measureBRTryo0.getWidth();
                            intRef4.element = Math.max(intRef4.element, placeableMo5903measureBRTryo0.getHeight());
                            i4 = i3 + 1;
                            size = i5;
                        }
                        if (!arrayList4.isEmpty()) {
                            measure_3p2s80s$startNewSequence(arrayList, intRef2, measureScope, f2, arrayList4, arrayList2, intRef4, arrayList3, intRef, intRef3);
                        }
                        final int iMax = Math.max(intRef.element, Constraints.m6982getMinWidthimpl(j));
                        int iMax2 = Math.max(intRef2.element, Constraints.m6981getMinHeightimpl(j));
                        final ArrayList arrayList6 = arrayList;
                        final float f5 = f;
                        return MeasureScope.layout$default(measureScope, iMax, iMax2, null, new Function1<Placeable.PlacementScope, Unit>() { // from class: androidx.compose.material3.AlertDialogKt$AlertDialogFlowRow$1$1.2
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Unit invoke(Placeable.PlacementScope placementScope) {
                                invoke2(placementScope);
                                return Unit.INSTANCE;
                            }

                            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2(Placeable.PlacementScope placementScope) {
                                List<List<Placeable>> list2 = arrayList6;
                                MeasureScope measureScope2 = measureScope;
                                float f6 = f5;
                                int i6 = iMax;
                                List<Integer> list3 = arrayList3;
                                int size2 = list2.size();
                                for (int i7 = 0; i7 < size2; i7++) {
                                    List<Placeable> list4 = list2.get(i7);
                                    int size3 = list4.size();
                                    int[] iArr = new int[size3];
                                    int i8 = 0;
                                    while (i8 < size3) {
                                        iArr[i8] = list4.get(i8).getWidth() + (i8 < CollectionsKt.getLastIndex(list4) ? measureScope2.mo651roundToPx0680j_4(f6) : 0);
                                        i8++;
                                    }
                                    Arrangement.Horizontal end = Arrangement.INSTANCE.getEnd();
                                    int[] iArr2 = new int[size3];
                                    for (int i9 = 0; i9 < size3; i9++) {
                                        iArr2[i9] = 0;
                                    }
                                    end.arrange(measureScope2, i6, iArr, measureScope2.getLayoutDirection(), iArr2);
                                    int size4 = list4.size();
                                    for (int i10 = 0; i10 < size4; i10++) {
                                        Placeable.PlacementScope.place$default(placementScope, list4.get(i10), iArr2[i10], list3.get(i7).intValue(), 0.0f, 4, null);
                                    }
                                }
                            }
                        }, 4, null);
                    }

                    private static final boolean measure_3p2s80s$canAddToCurrentSequence(List<Placeable> list, Ref.IntRef intRef, MeasureScope measureScope, float f3, long j, Placeable placeable) {
                        return list.isEmpty() || (intRef.element + measureScope.mo651roundToPx0680j_4(f3)) + placeable.getWidth() <= Constraints.m6980getMaxWidthimpl(j);
                    }

                    private static final void measure_3p2s80s$startNewSequence(List<List<Placeable>> list, Ref.IntRef intRef, MeasureScope measureScope, float f3, List<Placeable> list2, List<Integer> list3, Ref.IntRef intRef2, List<Integer> list4, Ref.IntRef intRef3, Ref.IntRef intRef4) {
                        if (!list.isEmpty()) {
                            intRef.element += measureScope.mo651roundToPx0680j_4(f3);
                        }
                        list.add(0, CollectionsKt.toList(list2));
                        list3.add(Integer.valueOf(intRef2.element));
                        list4.add(Integer.valueOf(intRef.element));
                        intRef.element += intRef2.element;
                        intRef3.element = Math.max(intRef3.element, intRef4.element);
                        list2.clear();
                        intRef4.element = 0;
                        intRef2.element = 0;
                    }
                };
                composerStartRestartGroup.updateRememberedValue(measurePolicyRememberedValue);
            }
            MeasurePolicy measurePolicy = (MeasurePolicy) measurePolicyRememberedValue;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            int i3 = (i2 >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
            Modifier.Companion companion = Modifier.INSTANCE;
            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap = composerStartRestartGroup.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composerStartRestartGroup, companion);
            Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
            int i4 = ((i3 << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
            if (!(composerStartRestartGroup.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            composerStartRestartGroup.startReusableNode();
            if (composerStartRestartGroup.getInserting()) {
                composerStartRestartGroup.createNode(constructor);
            } else {
                composerStartRestartGroup.useNode();
            }
            Composer composerM4013constructorimpl = Updater.m4013constructorimpl(composerStartRestartGroup);
            Updater.m4020setimpl(composerM4013constructorimpl, measurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4020setimpl(composerM4013constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if (composerM4013constructorimpl.getInserting() || !Intrinsics.areEqual(composerM4013constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                composerM4013constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                composerM4013constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
            }
            Updater.m4020setimpl(composerM4013constructorimpl, modifierMaterializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
            function2.invoke(composerStartRestartGroup, Integer.valueOf((i4 >> 6) & 14));
            composerStartRestartGroup.endNode();
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composerStartRestartGroup.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.AlertDialogKt$AlertDialogFlowRow$2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                    invoke(composer2, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer composer2, int i5) {
                    AlertDialogKt.m2060AlertDialogFlowRowixp7dh8(f, f2, function2, composer2, RecomposeScopeImplKt.updateChangedFlags(i | 1));
                }
            });
        }
    }

    public static final float getDialogMinWidth() {
        return DialogMinWidth;
    }

    public static final float getDialogMaxWidth() {
        return DialogMaxWidth;
    }

    static {
        float f = 24;
        DialogPadding = PaddingKt.m958PaddingValues0680j_4(Dp.m7027constructorimpl(f));
        float f2 = 16;
        IconPadding = PaddingKt.m962PaddingValuesa9UjIt4$default(0.0f, 0.0f, 0.0f, Dp.m7027constructorimpl(f2), 7, null);
        TitlePadding = PaddingKt.m962PaddingValuesa9UjIt4$default(0.0f, 0.0f, 0.0f, Dp.m7027constructorimpl(f2), 7, null);
        TextPadding = PaddingKt.m962PaddingValuesa9UjIt4$default(0.0f, 0.0f, 0.0f, Dp.m7027constructorimpl(f), 7, null);
    }
}
