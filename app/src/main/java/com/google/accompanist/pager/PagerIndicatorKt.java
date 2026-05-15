package com.google.accompanist.pager;

import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Metadata;

/* JADX INFO: compiled from: PagerIndicator.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000F\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a\u0081\u0001\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\u0014\b\u0002\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\t2\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\u000b2\b\b\u0002\u0010\r\u001a\u00020\u000e2\b\b\u0002\u0010\u000f\u001a\u00020\u000e2\b\b\u0002\u0010\u0010\u001a\u00020\u000e2\b\b\u0002\u0010\u0011\u001a\u00020\u0012H\u0007ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u0013\u0010\u0014\u001a\u0083\u0001\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00152\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\u0004\u001a\u00020\u00052\u0014\b\u0002\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\t2\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\u000b2\b\b\u0002\u0010\r\u001a\u00020\u000e2\b\b\u0002\u0010\u000f\u001a\u00020\u000e2\b\b\u0002\u0010\u0010\u001a\u00020\u000e2\b\b\u0002\u0010\u0011\u001a\u00020\u0012H\u0007ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u0013\u0010\u0016\u001a\u0081\u0001\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00172\u0006\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\u0014\b\u0002\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\t2\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\u000b2\b\b\u0002\u0010\r\u001a\u00020\u000e2\b\b\u0002\u0010\u000f\u001a\u00020\u000e2\b\b\u0002\u0010\u0010\u001a\u00020\u000e2\b\b\u0002\u0010\u0011\u001a\u00020\u0012H\u0003ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u0013\u0010\u0018\u001a\u0081\u0001\u0010\u0019\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\u0014\b\u0002\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\t2\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\u000b2\b\b\u0002\u0010\u000f\u001a\u00020\u000e2\b\b\u0002\u0010\r\u001a\u00020\u000e2\b\b\u0002\u0010\u0010\u001a\u00020\u000e2\b\b\u0002\u0010\u0011\u001a\u00020\u0012H\u0007ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u001a\u0010\u0014\u001a\u0083\u0001\u0010\u0019\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00152\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\u0004\u001a\u00020\u00052\u0014\b\u0002\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\t2\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\u000b2\b\b\u0002\u0010\u000f\u001a\u00020\u000e2\b\b\u0002\u0010\r\u001a\u00020\u000e2\b\b\u0002\u0010\u0010\u001a\u00020\u000e2\b\b\u0002\u0010\u0011\u001a\u00020\u0012H\u0007ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u001a\u0010\u0016\u001a\u0081\u0001\u0010\u0019\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00172\u0006\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\u0014\b\u0002\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\t2\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\u000b2\b\b\u0002\u0010\u000f\u001a\u00020\u000e2\b\b\u0002\u0010\r\u001a\u00020\u000e2\b\b\u0002\u0010\u0010\u001a\u00020\u000e2\b\b\u0002\u0010\u0011\u001a\u00020\u0012H\u0003ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u001a\u0010\u0018\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b\u0019¨\u0006\u001b"}, d2 = {"HorizontalPagerIndicator", "", "pagerState", "Landroidx/compose/foundation/pager/PagerState;", "pageCount", "", "modifier", "Landroidx/compose/ui/Modifier;", "pageIndexMapping", "Lkotlin/Function1;", "activeColor", "Landroidx/compose/ui/graphics/Color;", "inactiveColor", "indicatorWidth", "Landroidx/compose/ui/unit/Dp;", "indicatorHeight", "spacing", "indicatorShape", "Landroidx/compose/ui/graphics/Shape;", "HorizontalPagerIndicator-K_mkGiw", "(Landroidx/compose/foundation/pager/PagerState;ILandroidx/compose/ui/Modifier;Lkotlin/jvm/functions/Function1;JJFFFLandroidx/compose/ui/graphics/Shape;Landroidx/compose/runtime/Composer;II)V", "Lcom/google/accompanist/pager/PagerState;", "(Lcom/google/accompanist/pager/PagerState;Landroidx/compose/ui/Modifier;ILkotlin/jvm/functions/Function1;JJFFFLandroidx/compose/ui/graphics/Shape;Landroidx/compose/runtime/Composer;II)V", "Lcom/google/accompanist/pager/PagerStateBridge;", "(Lcom/google/accompanist/pager/PagerStateBridge;ILandroidx/compose/ui/Modifier;Lkotlin/jvm/functions/Function1;JJFFFLandroidx/compose/ui/graphics/Shape;Landroidx/compose/runtime/Composer;II)V", "VerticalPagerIndicator", "VerticalPagerIndicator-K_mkGiw", "pager-indicators_release"}, k = 2, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
public final class PagerIndicatorKt {
    /* JADX WARN: Removed duplicated region for block: B:102:0x012b  */
    /* JADX WARN: Removed duplicated region for block: B:110:0x0141  */
    /* JADX WARN: Removed duplicated region for block: B:113:0x014f  */
    /* JADX WARN: Removed duplicated region for block: B:117:0x0169  */
    /* JADX WARN: Removed duplicated region for block: B:143:0x01c5  */
    /* JADX WARN: Removed duplicated region for block: B:144:0x01c7  */
    /* JADX WARN: Removed duplicated region for block: B:147:0x01d0  */
    /* JADX WARN: Removed duplicated region for block: B:149:0x01d9  */
    /* JADX WARN: Removed duplicated region for block: B:152:0x01e2  */
    /* JADX WARN: Removed duplicated region for block: B:153:0x022a  */
    /* JADX WARN: Removed duplicated region for block: B:156:0x0230  */
    /* JADX WARN: Removed duplicated region for block: B:158:0x024c  */
    /* JADX WARN: Removed duplicated region for block: B:161:0x0258  */
    /* JADX WARN: Removed duplicated region for block: B:164:0x0261  */
    /* JADX WARN: Removed duplicated region for block: B:165:0x0268  */
    /* JADX WARN: Removed duplicated region for block: B:168:0x026e  */
    /* JADX WARN: Removed duplicated region for block: B:169:0x028c  */
    /* JADX WARN: Removed duplicated region for block: B:172:0x02af  */
    /* JADX WARN: Removed duplicated region for block: B:177:0x02d2  */
    /* JADX WARN: Removed duplicated region for block: B:180:0x030c  */
    /* JADX WARN: Removed duplicated region for block: B:184:0x0327 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:185:0x0328  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0050  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x0065  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x006b  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x006e  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x008a  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x009f  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x00a7  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x00bc  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x00c6  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x00cb  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x00eb  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x0103  */
    /* JADX WARN: Removed duplicated region for block: B:91:0x010d  */
    /* JADX WARN: Removed duplicated region for block: B:99:0x0123  */
    @kotlin.Deprecated(message = "\n   HorizontalPagerIndicator for accompanist Pagers are deprecated, please use the version that takes \n   androidx.compose.foundation.pager.PagerState instead\nFor more migration information, please visit https://google.github.io/accompanist/pager/#migration\n")
    /* JADX INFO: renamed from: HorizontalPagerIndicator-K_mkGiw, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final void m7554HorizontalPagerIndicatorK_mkGiw(final com.google.accompanist.pager.PagerState r33, androidx.compose.ui.Modifier r34, int r35, kotlin.jvm.functions.Function1<? super java.lang.Integer, java.lang.Integer> r36, long r37, long r39, float r41, float r42, float r43, androidx.compose.ui.graphics.Shape r44, androidx.compose.runtime.Composer r45, final int r46, final int r47) {
        /*
            Method dump skipped, instruction units count: 819
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.accompanist.pager.PagerIndicatorKt.m7554HorizontalPagerIndicatorK_mkGiw(com.google.accompanist.pager.PagerState, androidx.compose.ui.Modifier, int, kotlin.jvm.functions.Function1, long, long, float, float, float, androidx.compose.ui.graphics.Shape, androidx.compose.runtime.Composer, int, int):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:102:0x011f  */
    /* JADX WARN: Removed duplicated region for block: B:110:0x0135  */
    /* JADX WARN: Removed duplicated region for block: B:113:0x0143  */
    /* JADX WARN: Removed duplicated region for block: B:117:0x015d  */
    /* JADX WARN: Removed duplicated region for block: B:139:0x01af  */
    /* JADX WARN: Removed duplicated region for block: B:140:0x01b1  */
    /* JADX WARN: Removed duplicated region for block: B:142:0x01b8  */
    /* JADX WARN: Removed duplicated region for block: B:145:0x01c1  */
    /* JADX WARN: Removed duplicated region for block: B:146:0x0203  */
    /* JADX WARN: Removed duplicated region for block: B:149:0x020b  */
    /* JADX WARN: Removed duplicated region for block: B:150:0x0224  */
    /* JADX WARN: Removed duplicated region for block: B:152:0x0227  */
    /* JADX WARN: Removed duplicated region for block: B:153:0x022f  */
    /* JADX WARN: Removed duplicated region for block: B:156:0x0235  */
    /* JADX WARN: Removed duplicated region for block: B:157:0x023a  */
    /* JADX WARN: Removed duplicated region for block: B:160:0x0241  */
    /* JADX WARN: Removed duplicated region for block: B:161:0x0246  */
    /* JADX WARN: Removed duplicated region for block: B:164:0x024d  */
    /* JADX WARN: Removed duplicated region for block: B:165:0x0262  */
    /* JADX WARN: Removed duplicated region for block: B:169:0x027c  */
    /* JADX WARN: Removed duplicated region for block: B:174:0x02a2  */
    /* JADX WARN: Removed duplicated region for block: B:177:0x02c6  */
    /* JADX WARN: Removed duplicated region for block: B:181:0x02df A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:182:0x02e0  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0050  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0053  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x006c  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x006f  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x008a  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x009f  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x00a6  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x00bb  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x00c1  */
    /* JADX WARN: Removed duplicated region for block: B:71:0x00c8  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x00e3  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x00f9  */
    /* JADX WARN: Removed duplicated region for block: B:91:0x0101  */
    /* JADX WARN: Removed duplicated region for block: B:99:0x0117  */
    /* JADX INFO: renamed from: HorizontalPagerIndicator-K_mkGiw, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final void m7553HorizontalPagerIndicatorK_mkGiw(final androidx.compose.foundation.pager.PagerState r30, final int r31, androidx.compose.ui.Modifier r32, kotlin.jvm.functions.Function1<? super java.lang.Integer, java.lang.Integer> r33, long r34, long r36, float r38, float r39, float r40, androidx.compose.ui.graphics.Shape r41, androidx.compose.runtime.Composer r42, final int r43, final int r44) {
        /*
            Method dump skipped, instruction units count: 749
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.accompanist.pager.PagerIndicatorKt.m7553HorizontalPagerIndicatorK_mkGiw(androidx.compose.foundation.pager.PagerState, int, androidx.compose.ui.Modifier, kotlin.jvm.functions.Function1, long, long, float, float, float, androidx.compose.ui.graphics.Shape, androidx.compose.runtime.Composer, int, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:101:0x0117  */
    /* JADX WARN: Removed duplicated region for block: B:109:0x012d  */
    /* JADX WARN: Removed duplicated region for block: B:112:0x0139  */
    /* JADX WARN: Removed duplicated region for block: B:116:0x0151  */
    /* JADX WARN: Removed duplicated region for block: B:137:0x01a4  */
    /* JADX WARN: Removed duplicated region for block: B:138:0x01a6  */
    /* JADX WARN: Removed duplicated region for block: B:139:0x01ab  */
    /* JADX WARN: Removed duplicated region for block: B:141:0x01ae  */
    /* JADX WARN: Removed duplicated region for block: B:142:0x01b3  */
    /* JADX WARN: Removed duplicated region for block: B:145:0x01b8  */
    /* JADX WARN: Removed duplicated region for block: B:146:0x01f7  */
    /* JADX WARN: Removed duplicated region for block: B:149:0x01fd  */
    /* JADX WARN: Removed duplicated region for block: B:150:0x0216  */
    /* JADX WARN: Removed duplicated region for block: B:152:0x0219  */
    /* JADX WARN: Removed duplicated region for block: B:153:0x0221  */
    /* JADX WARN: Removed duplicated region for block: B:156:0x0227  */
    /* JADX WARN: Removed duplicated region for block: B:157:0x022c  */
    /* JADX WARN: Removed duplicated region for block: B:160:0x0233  */
    /* JADX WARN: Removed duplicated region for block: B:161:0x0238  */
    /* JADX WARN: Removed duplicated region for block: B:164:0x023f  */
    /* JADX WARN: Removed duplicated region for block: B:165:0x024d  */
    /* JADX WARN: Removed duplicated region for block: B:169:0x0260  */
    /* JADX WARN: Removed duplicated region for block: B:170:0x026c  */
    /* JADX WARN: Removed duplicated region for block: B:173:0x0318  */
    /* JADX WARN: Removed duplicated region for block: B:176:0x0324  */
    /* JADX WARN: Removed duplicated region for block: B:177:0x0328  */
    /* JADX WARN: Removed duplicated region for block: B:180:0x03f3  */
    /* JADX WARN: Removed duplicated region for block: B:183:0x03ff  */
    /* JADX WARN: Removed duplicated region for block: B:184:0x0403  */
    /* JADX WARN: Removed duplicated region for block: B:187:0x046b A[LOOP:0: B:186:0x0469->B:187:0x046b, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:190:0x04a7  */
    /* JADX WARN: Removed duplicated region for block: B:191:0x04b2  */
    /* JADX WARN: Removed duplicated region for block: B:194:0x04d5  */
    /* JADX WARN: Removed duplicated region for block: B:198:0x04e9 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:199:0x04ea  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0064  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0067  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x0082  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x0097  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x009e  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x00b3  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x00b9  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x00c0  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x00db  */
    /* JADX WARN: Removed duplicated region for block: B:87:0x00f1  */
    /* JADX WARN: Removed duplicated region for block: B:90:0x00f9  */
    /* JADX WARN: Removed duplicated region for block: B:98:0x010f  */
    /* JADX INFO: renamed from: HorizontalPagerIndicator-K_mkGiw, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final void m7555HorizontalPagerIndicatorK_mkGiw(final com.google.accompanist.pager.PagerStateBridge r31, final int r32, androidx.compose.ui.Modifier r33, kotlin.jvm.functions.Function1<? super java.lang.Integer, java.lang.Integer> r34, long r35, long r37, float r39, float r40, float r41, androidx.compose.ui.graphics.Shape r42, androidx.compose.runtime.Composer r43, final int r44, final int r45) {
        /*
            Method dump skipped, instruction units count: 1277
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.accompanist.pager.PagerIndicatorKt.m7555HorizontalPagerIndicatorK_mkGiw(com.google.accompanist.pager.PagerStateBridge, int, androidx.compose.ui.Modifier, kotlin.jvm.functions.Function1, long, long, float, float, float, androidx.compose.ui.graphics.Shape, androidx.compose.runtime.Composer, int, int):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:102:0x012b  */
    /* JADX WARN: Removed duplicated region for block: B:110:0x0141  */
    /* JADX WARN: Removed duplicated region for block: B:113:0x014f  */
    /* JADX WARN: Removed duplicated region for block: B:117:0x0169  */
    /* JADX WARN: Removed duplicated region for block: B:143:0x01c5  */
    /* JADX WARN: Removed duplicated region for block: B:144:0x01c7  */
    /* JADX WARN: Removed duplicated region for block: B:147:0x01d0  */
    /* JADX WARN: Removed duplicated region for block: B:149:0x01d9  */
    /* JADX WARN: Removed duplicated region for block: B:152:0x01e2  */
    /* JADX WARN: Removed duplicated region for block: B:153:0x022a  */
    /* JADX WARN: Removed duplicated region for block: B:156:0x0230  */
    /* JADX WARN: Removed duplicated region for block: B:158:0x024c  */
    /* JADX WARN: Removed duplicated region for block: B:161:0x0258  */
    /* JADX WARN: Removed duplicated region for block: B:164:0x0261  */
    /* JADX WARN: Removed duplicated region for block: B:165:0x0268  */
    /* JADX WARN: Removed duplicated region for block: B:168:0x026e  */
    /* JADX WARN: Removed duplicated region for block: B:169:0x028c  */
    /* JADX WARN: Removed duplicated region for block: B:172:0x02af  */
    /* JADX WARN: Removed duplicated region for block: B:177:0x02d2  */
    /* JADX WARN: Removed duplicated region for block: B:180:0x030c  */
    /* JADX WARN: Removed duplicated region for block: B:184:0x0327 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:185:0x0328  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0050  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x0065  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x006b  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x006e  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x008a  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x009f  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x00a7  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x00bc  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x00c6  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x00cb  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x00eb  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x0103  */
    /* JADX WARN: Removed duplicated region for block: B:91:0x010d  */
    /* JADX WARN: Removed duplicated region for block: B:99:0x0123  */
    @kotlin.Deprecated(message = "\n   VerticalPagerIndicator for accompanist Pagers are deprecated, please use the version that takes \n   androidx.compose.foundation.pager.PagerState instead\nFor more migration information, please visit https://google.github.io/accompanist/pager/#migration\n")
    /* JADX INFO: renamed from: VerticalPagerIndicator-K_mkGiw, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final void m7557VerticalPagerIndicatorK_mkGiw(final com.google.accompanist.pager.PagerState r33, androidx.compose.ui.Modifier r34, int r35, kotlin.jvm.functions.Function1<? super java.lang.Integer, java.lang.Integer> r36, long r37, long r39, float r41, float r42, float r43, androidx.compose.ui.graphics.Shape r44, androidx.compose.runtime.Composer r45, final int r46, final int r47) {
        /*
            Method dump skipped, instruction units count: 819
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.accompanist.pager.PagerIndicatorKt.m7557VerticalPagerIndicatorK_mkGiw(com.google.accompanist.pager.PagerState, androidx.compose.ui.Modifier, int, kotlin.jvm.functions.Function1, long, long, float, float, float, androidx.compose.ui.graphics.Shape, androidx.compose.runtime.Composer, int, int):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:102:0x011f  */
    /* JADX WARN: Removed duplicated region for block: B:110:0x0135  */
    /* JADX WARN: Removed duplicated region for block: B:113:0x0143  */
    /* JADX WARN: Removed duplicated region for block: B:117:0x015d  */
    /* JADX WARN: Removed duplicated region for block: B:139:0x01af  */
    /* JADX WARN: Removed duplicated region for block: B:140:0x01b1  */
    /* JADX WARN: Removed duplicated region for block: B:142:0x01b8  */
    /* JADX WARN: Removed duplicated region for block: B:145:0x01c1  */
    /* JADX WARN: Removed duplicated region for block: B:146:0x0203  */
    /* JADX WARN: Removed duplicated region for block: B:149:0x020b  */
    /* JADX WARN: Removed duplicated region for block: B:150:0x0224  */
    /* JADX WARN: Removed duplicated region for block: B:152:0x0227  */
    /* JADX WARN: Removed duplicated region for block: B:153:0x022f  */
    /* JADX WARN: Removed duplicated region for block: B:156:0x0235  */
    /* JADX WARN: Removed duplicated region for block: B:157:0x023a  */
    /* JADX WARN: Removed duplicated region for block: B:160:0x0241  */
    /* JADX WARN: Removed duplicated region for block: B:161:0x0246  */
    /* JADX WARN: Removed duplicated region for block: B:164:0x024d  */
    /* JADX WARN: Removed duplicated region for block: B:165:0x0262  */
    /* JADX WARN: Removed duplicated region for block: B:169:0x027c  */
    /* JADX WARN: Removed duplicated region for block: B:174:0x02a2  */
    /* JADX WARN: Removed duplicated region for block: B:177:0x02c6  */
    /* JADX WARN: Removed duplicated region for block: B:181:0x02df A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:182:0x02e0  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0050  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0053  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x006c  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x006f  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x008a  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x009f  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x00a6  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x00bb  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x00c1  */
    /* JADX WARN: Removed duplicated region for block: B:71:0x00c8  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x00e3  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x00f9  */
    /* JADX WARN: Removed duplicated region for block: B:91:0x0101  */
    /* JADX WARN: Removed duplicated region for block: B:99:0x0117  */
    /* JADX INFO: renamed from: VerticalPagerIndicator-K_mkGiw, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final void m7556VerticalPagerIndicatorK_mkGiw(final androidx.compose.foundation.pager.PagerState r30, final int r31, androidx.compose.ui.Modifier r32, kotlin.jvm.functions.Function1<? super java.lang.Integer, java.lang.Integer> r33, long r34, long r36, float r38, float r39, float r40, androidx.compose.ui.graphics.Shape r41, androidx.compose.runtime.Composer r42, final int r43, final int r44) {
        /*
            Method dump skipped, instruction units count: 749
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.accompanist.pager.PagerIndicatorKt.m7556VerticalPagerIndicatorK_mkGiw(androidx.compose.foundation.pager.PagerState, int, androidx.compose.ui.Modifier, kotlin.jvm.functions.Function1, long, long, float, float, float, androidx.compose.ui.graphics.Shape, androidx.compose.runtime.Composer, int, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:101:0x0117  */
    /* JADX WARN: Removed duplicated region for block: B:109:0x012d  */
    /* JADX WARN: Removed duplicated region for block: B:112:0x0139  */
    /* JADX WARN: Removed duplicated region for block: B:116:0x0151  */
    /* JADX WARN: Removed duplicated region for block: B:137:0x01a4  */
    /* JADX WARN: Removed duplicated region for block: B:138:0x01a6  */
    /* JADX WARN: Removed duplicated region for block: B:139:0x01ab  */
    /* JADX WARN: Removed duplicated region for block: B:141:0x01ae  */
    /* JADX WARN: Removed duplicated region for block: B:142:0x01b3  */
    /* JADX WARN: Removed duplicated region for block: B:145:0x01b8  */
    /* JADX WARN: Removed duplicated region for block: B:146:0x01f7  */
    /* JADX WARN: Removed duplicated region for block: B:149:0x01fd  */
    /* JADX WARN: Removed duplicated region for block: B:150:0x0216  */
    /* JADX WARN: Removed duplicated region for block: B:152:0x0219  */
    /* JADX WARN: Removed duplicated region for block: B:153:0x0221  */
    /* JADX WARN: Removed duplicated region for block: B:156:0x0227  */
    /* JADX WARN: Removed duplicated region for block: B:157:0x022c  */
    /* JADX WARN: Removed duplicated region for block: B:160:0x0233  */
    /* JADX WARN: Removed duplicated region for block: B:161:0x0238  */
    /* JADX WARN: Removed duplicated region for block: B:164:0x023f  */
    /* JADX WARN: Removed duplicated region for block: B:165:0x024d  */
    /* JADX WARN: Removed duplicated region for block: B:169:0x0260  */
    /* JADX WARN: Removed duplicated region for block: B:170:0x026c  */
    /* JADX WARN: Removed duplicated region for block: B:173:0x0318  */
    /* JADX WARN: Removed duplicated region for block: B:176:0x0324  */
    /* JADX WARN: Removed duplicated region for block: B:177:0x0328  */
    /* JADX WARN: Removed duplicated region for block: B:180:0x03f3  */
    /* JADX WARN: Removed duplicated region for block: B:183:0x03ff  */
    /* JADX WARN: Removed duplicated region for block: B:184:0x0403  */
    /* JADX WARN: Removed duplicated region for block: B:187:0x046b A[LOOP:0: B:186:0x0469->B:187:0x046b, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:190:0x04a7  */
    /* JADX WARN: Removed duplicated region for block: B:191:0x04b2  */
    /* JADX WARN: Removed duplicated region for block: B:194:0x04d5  */
    /* JADX WARN: Removed duplicated region for block: B:198:0x04e9 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:199:0x04ea  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0064  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0067  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x0082  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x0097  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x009e  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x00b3  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x00b9  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x00c0  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x00db  */
    /* JADX WARN: Removed duplicated region for block: B:87:0x00f1  */
    /* JADX WARN: Removed duplicated region for block: B:90:0x00f9  */
    /* JADX WARN: Removed duplicated region for block: B:98:0x010f  */
    /* JADX INFO: renamed from: VerticalPagerIndicator-K_mkGiw, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final void m7558VerticalPagerIndicatorK_mkGiw(final com.google.accompanist.pager.PagerStateBridge r31, final int r32, androidx.compose.ui.Modifier r33, kotlin.jvm.functions.Function1<? super java.lang.Integer, java.lang.Integer> r34, long r35, long r37, float r39, float r40, float r41, androidx.compose.ui.graphics.Shape r42, androidx.compose.runtime.Composer r43, final int r44, final int r45) {
        /*
            Method dump skipped, instruction units count: 1277
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.accompanist.pager.PagerIndicatorKt.m7558VerticalPagerIndicatorK_mkGiw(com.google.accompanist.pager.PagerStateBridge, int, androidx.compose.ui.Modifier, kotlin.jvm.functions.Function1, long, long, float, float, float, androidx.compose.ui.graphics.Shape, androidx.compose.runtime.Composer, int, int):void");
    }
}
