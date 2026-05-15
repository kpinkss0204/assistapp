package com.google.accompanist.pager;

import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.OffsetKt;
import androidx.compose.ui.unit.Velocity;
import androidx.compose.ui.unit.VelocityKt;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.firebase.analytics.FirebaseAnalytics;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Pager.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000\u0080\u0001\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\u001aÇ\u0001\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u00012\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\u000e2\b\b\u0002\u0010\u000f\u001a\u00020\u00102\b\b\u0002\u0010\u0011\u001a\u00020\u00122%\b\u0002\u0010\u0013\u001a\u001f\u0012\u0013\u0012\u00110\u0005¢\u0006\f\b\u0015\u0012\b\b\u0016\u0012\u0004\b\b(\u0017\u0012\u0004\u0012\u00020\u0018\u0018\u00010\u00142\b\b\u0002\u0010\u0019\u001a\u00020\u000121\u0010\u001a\u001a-\u0012\u0004\u0012\u00020\u001c\u0012\u0013\u0012\u00110\u0005¢\u0006\f\b\u0015\u0012\b\b\u0016\u0012\u0004\b\b(\u0017\u0012\u0004\u0012\u00020\u00030\u001b¢\u0006\u0002\b\u001d¢\u0006\u0002\b\u001eH\u0007ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u001f\u0010 \u001aÉ\u0001\u0010!\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u00012\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\"\u001a\u00020\u00012\u0006\u0010\u0011\u001a\u00020\u00122#\u0010\u0013\u001a\u001f\u0012\u0013\u0012\u00110\u0005¢\u0006\f\b\u0015\u0012\b\b\u0016\u0012\u0004\b\b(\u0017\u0012\u0004\u0012\u00020\u0018\u0018\u00010\u00142\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u0019\u001a\u00020\u00012\b\b\u0002\u0010\u000f\u001a\u00020\u00102\b\b\u0002\u0010#\u001a\u00020$21\u0010\u001a\u001a-\u0012\u0004\u0012\u00020\u001c\u0012\u0013\u0012\u00110\u0005¢\u0006\f\b\u0015\u0012\b\b\u0016\u0012\u0004\b\b(\u0017\u0012\u0004\u0012\u00020\u00030\u001b¢\u0006\u0002\b\u001d¢\u0006\u0002\b\u001eH\u0001ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b%\u0010&\u001aÇ\u0001\u0010'\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u00012\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\u000e2\b\b\u0002\u0010#\u001a\u00020$2\b\b\u0002\u0010\u0011\u001a\u00020\u00122%\b\u0002\u0010\u0013\u001a\u001f\u0012\u0013\u0012\u00110\u0005¢\u0006\f\b\u0015\u0012\b\b\u0016\u0012\u0004\b\b(\u0017\u0012\u0004\u0012\u00020\u0018\u0018\u00010\u00142\b\b\u0002\u0010\u0019\u001a\u00020\u000121\u0010\u001a\u001a-\u0012\u0004\u0012\u00020\u001c\u0012\u0013\u0012\u00110\u0005¢\u0006\f\b\u0015\u0012\b\b\u0016\u0012\u0004\b\b(\u0017\u0012\u0004\u0012\u00020\u00030\u001b¢\u0006\u0002\b\u001d¢\u0006\u0002\b\u001eH\u0007ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b(\u0010)\u001a\u0014\u0010*\u001a\u00020+*\u00020\u001c2\u0006\u0010\u0017\u001a\u00020\u0005H\u0007\u001a)\u0010,\u001a\u00020-*\u00020-2\u0006\u0010.\u001a\u00020\u00012\u0006\u0010/\u001a\u00020\u0001H\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b0\u00101\u001a)\u0010,\u001a\u000202*\u0002022\u0006\u0010.\u001a\u00020\u00012\u0006\u0010/\u001a\u00020\u0001H\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b3\u00101\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0080T¢\u0006\u0002\n\u0000\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b\u0019¨\u00064"}, d2 = {"DebugLog", "", "HorizontalPager", "", "count", "", "modifier", "Landroidx/compose/ui/Modifier;", "state", "Lcom/google/accompanist/pager/PagerState;", "reverseLayout", "itemSpacing", "Landroidx/compose/ui/unit/Dp;", "contentPadding", "Landroidx/compose/foundation/layout/PaddingValues;", "verticalAlignment", "Landroidx/compose/ui/Alignment$Vertical;", "flingBehavior", "Landroidx/compose/foundation/gestures/FlingBehavior;", "key", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "page", "", "userScrollEnabled", FirebaseAnalytics.Param.CONTENT, "Lkotlin/Function2;", "Lcom/google/accompanist/pager/PagerScope;", "Landroidx/compose/runtime/Composable;", "Lkotlin/ExtensionFunctionType;", "HorizontalPager-7SJ-wSw", "(ILandroidx/compose/ui/Modifier;Lcom/google/accompanist/pager/PagerState;ZFLandroidx/compose/foundation/layout/PaddingValues;Landroidx/compose/ui/Alignment$Vertical;Landroidx/compose/foundation/gestures/FlingBehavior;Lkotlin/jvm/functions/Function1;ZLkotlin/jvm/functions/Function4;Landroidx/compose/runtime/Composer;III)V", "Pager", "isVertical", "horizontalAlignment", "Landroidx/compose/ui/Alignment$Horizontal;", "Pager-upBTbn8", "(ILandroidx/compose/ui/Modifier;Lcom/google/accompanist/pager/PagerState;ZFZLandroidx/compose/foundation/gestures/FlingBehavior;Lkotlin/jvm/functions/Function1;Landroidx/compose/foundation/layout/PaddingValues;ZLandroidx/compose/ui/Alignment$Vertical;Landroidx/compose/ui/Alignment$Horizontal;Lkotlin/jvm/functions/Function4;Landroidx/compose/runtime/Composer;III)V", "VerticalPager", "VerticalPager-7SJ-wSw", "(ILandroidx/compose/ui/Modifier;Lcom/google/accompanist/pager/PagerState;ZFLandroidx/compose/foundation/layout/PaddingValues;Landroidx/compose/ui/Alignment$Horizontal;Landroidx/compose/foundation/gestures/FlingBehavior;Lkotlin/jvm/functions/Function1;ZLkotlin/jvm/functions/Function4;Landroidx/compose/runtime/Composer;III)V", "calculateCurrentOffsetForPage", "", "consume", "Landroidx/compose/ui/geometry/Offset;", "consumeHorizontal", "consumeVertical", "consume-9KIMszo", "(JZZ)J", "Landroidx/compose/ui/unit/Velocity;", "consume-BMRW4eQ", "pager_release"}, k = 2, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
public final class Pager {
    public static final boolean DebugLog = false;

    /* JADX WARN: Removed duplicated region for block: B:101:0x0134  */
    /* JADX WARN: Removed duplicated region for block: B:102:0x0139  */
    /* JADX WARN: Removed duplicated region for block: B:112:0x0157  */
    /* JADX WARN: Removed duplicated region for block: B:113:0x015a  */
    /* JADX WARN: Removed duplicated region for block: B:123:0x0176  */
    /* JADX WARN: Removed duplicated region for block: B:130:0x0195  */
    /* JADX WARN: Removed duplicated region for block: B:143:0x01c5  */
    /* JADX WARN: Removed duplicated region for block: B:144:0x01c7  */
    /* JADX WARN: Removed duplicated region for block: B:145:0x01cc  */
    /* JADX WARN: Removed duplicated region for block: B:148:0x01d3  */
    /* JADX WARN: Removed duplicated region for block: B:149:0x01da  */
    /* JADX WARN: Removed duplicated region for block: B:152:0x01df  */
    /* JADX WARN: Removed duplicated region for block: B:154:0x01e2  */
    /* JADX WARN: Removed duplicated region for block: B:155:0x01e9  */
    /* JADX WARN: Removed duplicated region for block: B:157:0x01ed  */
    /* JADX WARN: Removed duplicated region for block: B:159:0x01f9  */
    /* JADX WARN: Removed duplicated region for block: B:160:0x0202  */
    /* JADX WARN: Removed duplicated region for block: B:163:0x0208  */
    /* JADX WARN: Removed duplicated region for block: B:164:0x0226  */
    /* JADX WARN: Removed duplicated region for block: B:166:0x022e  */
    /* JADX WARN: Removed duplicated region for block: B:167:0x0230  */
    /* JADX WARN: Removed duplicated region for block: B:169:0x0234  */
    /* JADX WARN: Removed duplicated region for block: B:170:0x023a  */
    /* JADX WARN: Removed duplicated region for block: B:174:0x024b  */
    /* JADX WARN: Removed duplicated region for block: B:177:0x0295  */
    /* JADX WARN: Removed duplicated region for block: B:181:0x02aa A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:182:0x02ab  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0054  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x0069  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x006f  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x0072  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x008e  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x0093  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x00ac  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x00af  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x00d1  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x00d8  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x00f1  */
    /* JADX WARN: Removed duplicated region for block: B:87:0x0107  */
    /* JADX WARN: Removed duplicated region for block: B:90:0x010f  */
    /* JADX WARN: Removed duplicated region for block: B:91:0x0114  */
    @kotlin.Deprecated(message = "\naccompanist/pager is deprecated.\nThe androidx.compose equivalent of HorizontalPager is androidx.compose.foundation.pager.HorizontalPager\nFor more migration information, please visit https://google.github.io/accompanist/pager/#migration\n", replaceWith = @kotlin.ReplaceWith(expression = "HorizontalPager", imports = {"androidx.compose.foundation.pager.HorizontalPager"}))
    /* JADX INFO: renamed from: HorizontalPager-7SJ-wSw, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final void m7543HorizontalPager7SJwSw(final int r29, androidx.compose.ui.Modifier r30, com.google.accompanist.pager.PagerState r31, boolean r32, float r33, androidx.compose.foundation.layout.PaddingValues r34, androidx.compose.ui.Alignment.Vertical r35, androidx.compose.foundation.gestures.FlingBehavior r36, kotlin.jvm.functions.Function1<? super java.lang.Integer, ? extends java.lang.Object> r37, boolean r38, final kotlin.jvm.functions.Function4<? super com.google.accompanist.pager.PagerScope, ? super java.lang.Integer, ? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r39, androidx.compose.runtime.Composer r40, final int r41, final int r42, final int r43) {
        /*
            Method dump skipped, instruction units count: 704
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.accompanist.pager.Pager.m7543HorizontalPager7SJwSw(int, androidx.compose.ui.Modifier, com.google.accompanist.pager.PagerState, boolean, float, androidx.compose.foundation.layout.PaddingValues, androidx.compose.ui.Alignment$Vertical, androidx.compose.foundation.gestures.FlingBehavior, kotlin.jvm.functions.Function1, boolean, kotlin.jvm.functions.Function4, androidx.compose.runtime.Composer, int, int, int):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:101:0x0134  */
    /* JADX WARN: Removed duplicated region for block: B:102:0x0139  */
    /* JADX WARN: Removed duplicated region for block: B:112:0x0157  */
    /* JADX WARN: Removed duplicated region for block: B:113:0x015a  */
    /* JADX WARN: Removed duplicated region for block: B:123:0x0176  */
    /* JADX WARN: Removed duplicated region for block: B:130:0x0195  */
    /* JADX WARN: Removed duplicated region for block: B:143:0x01c5  */
    /* JADX WARN: Removed duplicated region for block: B:144:0x01c7  */
    /* JADX WARN: Removed duplicated region for block: B:145:0x01cc  */
    /* JADX WARN: Removed duplicated region for block: B:148:0x01d3  */
    /* JADX WARN: Removed duplicated region for block: B:149:0x01da  */
    /* JADX WARN: Removed duplicated region for block: B:152:0x01df  */
    /* JADX WARN: Removed duplicated region for block: B:154:0x01e2  */
    /* JADX WARN: Removed duplicated region for block: B:155:0x01e9  */
    /* JADX WARN: Removed duplicated region for block: B:157:0x01ed  */
    /* JADX WARN: Removed duplicated region for block: B:159:0x01f9  */
    /* JADX WARN: Removed duplicated region for block: B:160:0x0202  */
    /* JADX WARN: Removed duplicated region for block: B:163:0x0208  */
    /* JADX WARN: Removed duplicated region for block: B:164:0x0224  */
    /* JADX WARN: Removed duplicated region for block: B:166:0x022c  */
    /* JADX WARN: Removed duplicated region for block: B:167:0x022e  */
    /* JADX WARN: Removed duplicated region for block: B:169:0x0232  */
    /* JADX WARN: Removed duplicated region for block: B:170:0x0238  */
    /* JADX WARN: Removed duplicated region for block: B:174:0x0248  */
    /* JADX WARN: Removed duplicated region for block: B:177:0x0291  */
    /* JADX WARN: Removed duplicated region for block: B:181:0x02a5 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:182:0x02a6  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0054  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x0069  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x006f  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x0072  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x008e  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x0093  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x00ac  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x00af  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x00d1  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x00d8  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x00f1  */
    /* JADX WARN: Removed duplicated region for block: B:87:0x0107  */
    /* JADX WARN: Removed duplicated region for block: B:90:0x010f  */
    /* JADX WARN: Removed duplicated region for block: B:91:0x0114  */
    @kotlin.Deprecated(message = "\naccompanist/pager is deprecated.\nThe androidx.compose equivalent of VerticalPager is androidx.compose.foundation.pager.VerticalPager.\nFor more migration information, please visit https://google.github.io/accompanist/pager/#migration\n")
    /* JADX INFO: renamed from: VerticalPager-7SJ-wSw, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final void m7545VerticalPager7SJwSw(final int r29, androidx.compose.ui.Modifier r30, com.google.accompanist.pager.PagerState r31, boolean r32, float r33, androidx.compose.foundation.layout.PaddingValues r34, androidx.compose.ui.Alignment.Horizontal r35, androidx.compose.foundation.gestures.FlingBehavior r36, kotlin.jvm.functions.Function1<? super java.lang.Integer, ? extends java.lang.Object> r37, boolean r38, final kotlin.jvm.functions.Function4<? super com.google.accompanist.pager.PagerScope, ? super java.lang.Integer, ? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r39, androidx.compose.runtime.Composer r40, final int r41, final int r42, final int r43) {
        /*
            Method dump skipped, instruction units count: 699
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.accompanist.pager.Pager.m7545VerticalPager7SJwSw(int, androidx.compose.ui.Modifier, com.google.accompanist.pager.PagerState, boolean, float, androidx.compose.foundation.layout.PaddingValues, androidx.compose.ui.Alignment$Horizontal, androidx.compose.foundation.gestures.FlingBehavior, kotlin.jvm.functions.Function1, boolean, kotlin.jvm.functions.Function4, androidx.compose.runtime.Composer, int, int, int):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:107:0x014f  */
    /* JADX WARN: Removed duplicated region for block: B:108:0x0156  */
    /* JADX WARN: Removed duplicated region for block: B:117:0x0173  */
    /* JADX WARN: Removed duplicated region for block: B:118:0x0178  */
    /* JADX WARN: Removed duplicated region for block: B:128:0x0196  */
    /* JADX WARN: Removed duplicated region for block: B:129:0x0199  */
    /* JADX WARN: Removed duplicated region for block: B:138:0x01b2  */
    /* JADX WARN: Removed duplicated region for block: B:144:0x01c9  */
    /* JADX WARN: Removed duplicated region for block: B:145:0x01cb  */
    /* JADX WARN: Removed duplicated region for block: B:146:0x01d3  */
    /* JADX WARN: Removed duplicated region for block: B:148:0x01d7  */
    /* JADX WARN: Removed duplicated region for block: B:149:0x01e0  */
    /* JADX WARN: Removed duplicated region for block: B:152:0x01e8  */
    /* JADX WARN: Removed duplicated region for block: B:154:0x01f2  */
    /* JADX WARN: Removed duplicated region for block: B:165:0x025a  */
    /* JADX WARN: Removed duplicated region for block: B:170:0x028d  */
    /* JADX WARN: Removed duplicated region for block: B:175:0x02be  */
    /* JADX WARN: Removed duplicated region for block: B:180:0x0320  */
    /* JADX WARN: Removed duplicated region for block: B:185:0x035d  */
    /* JADX WARN: Removed duplicated region for block: B:190:0x0386  */
    /* JADX WARN: Removed duplicated region for block: B:193:0x0398  */
    /* JADX WARN: Removed duplicated region for block: B:205:0x0426  */
    /* JADX WARN: Removed duplicated region for block: B:217:0x04ba  */
    /* JADX WARN: Removed duplicated region for block: B:221:0x04c6 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:222:0x04c7  */
    /* JADX WARN: Removed duplicated region for block: B:224:0x04f1  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x00ac  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x00af  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x00c9  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x00ce  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x00e4  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x00e9  */
    /* JADX WARN: Removed duplicated region for block: B:76:0x00ff  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x0103  */
    /* JADX WARN: Removed duplicated region for block: B:86:0x0117  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x011b  */
    /* JADX WARN: Removed duplicated region for block: B:96:0x0130  */
    /* JADX WARN: Removed duplicated region for block: B:97:0x0134  */
    /* JADX INFO: renamed from: Pager-upBTbn8, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final void m7544PagerupBTbn8(final int r30, final androidx.compose.ui.Modifier r31, final com.google.accompanist.pager.PagerState r32, final boolean r33, final float r34, final boolean r35, final androidx.compose.foundation.gestures.FlingBehavior r36, final kotlin.jvm.functions.Function1<? super java.lang.Integer, ? extends java.lang.Object> r37, final androidx.compose.foundation.layout.PaddingValues r38, final boolean r39, androidx.compose.ui.Alignment.Vertical r40, androidx.compose.ui.Alignment.Horizontal r41, final kotlin.jvm.functions.Function4<? super com.google.accompanist.pager.PagerScope, ? super java.lang.Integer, ? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r42, androidx.compose.runtime.Composer r43, final int r44, final int r45, final int r46) {
        /*
            Method dump skipped, instruction units count: 1277
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.accompanist.pager.Pager.m7544PagerupBTbn8(int, androidx.compose.ui.Modifier, com.google.accompanist.pager.PagerState, boolean, float, boolean, androidx.compose.foundation.gestures.FlingBehavior, kotlin.jvm.functions.Function1, androidx.compose.foundation.layout.PaddingValues, boolean, androidx.compose.ui.Alignment$Vertical, androidx.compose.ui.Alignment$Horizontal, kotlin.jvm.functions.Function4, androidx.compose.runtime.Composer, int, int, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: consume-9KIMszo, reason: not valid java name */
    public static final long m7548consume9KIMszo(long j, boolean z, boolean z2) {
        return OffsetKt.Offset(z ? Offset.m4327getXimpl(j) : 0.0f, z2 ? Offset.m4328getYimpl(j) : 0.0f);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: consume-BMRW4eQ, reason: not valid java name */
    public static final long m7549consumeBMRW4eQ(long j, boolean z, boolean z2) {
        return VelocityKt.Velocity(z ? Velocity.m7264getXimpl(j) : 0.0f, z2 ? Velocity.m7265getYimpl(j) : 0.0f);
    }

    @Deprecated(message = "\naccompanist/pager is deprecated.\nThe androidx.compose equivalent of Pager is androidx.compose.foundation.pager.Pager.\nFor more migration information, please visit https://google.github.io/accompanist/pager/#migration\n")
    public static final float calculateCurrentOffsetForPage(PagerScope pagerScope, int i) {
        Intrinsics.checkNotNullParameter(pagerScope, "<this>");
        return (pagerScope.getCurrentPage() - i) + pagerScope.getCurrentPageOffset();
    }
}
