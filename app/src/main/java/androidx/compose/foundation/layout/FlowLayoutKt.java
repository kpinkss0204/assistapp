package androidx.compose.foundation.layout;

import androidx.collection.IntIntPair;
import androidx.collection.IntObjectMapKt;
import androidx.collection.MutableIntList;
import androidx.collection.MutableIntObjectMap;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.FlowLayoutBuildingBlocks;
import androidx.compose.foundation.layout.FlowLayoutOverflow;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.collection.MutableVector;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.layout.IntrinsicMeasurable;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.MultiContentMeasurePolicy;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.unit.Constraints;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.ranges.IntRange;
import kotlin.ranges.RangesKt;

/* JADX INFO: compiled from: FlowLayout.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000Ô\u0001\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0015\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010(\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\u001ag\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u000f2\b\b\u0002\u0010\u0011\u001a\u00020\u00122\u001c\u0010\u0013\u001a\u0018\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020\u00070\u0014¢\u0006\u0002\b\u0016¢\u0006\u0002\b\u0017H\u0007¢\u0006\u0002\u0010\u0018\u001ag\u0010\u0019\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\u001a\u001a\u00020\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u000f2\b\b\u0002\u0010\u0011\u001a\u00020\u001b2\u001c\u0010\u0013\u001a\u0018\u0012\u0004\u0012\u00020\u001c\u0012\u0004\u0012\u00020\u00070\u0014¢\u0006\u0002\b\u0016¢\u0006\u0002\b\u0017H\u0007¢\u0006\u0002\u0010\u001d\u001a%\u0010\u001e\u001a\u00020\u001f2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010 \u001a\u00020\u000fH\u0001¢\u0006\u0002\u0010!\u001a5\u0010\"\u001a\u00020#2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010 \u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u000f2\u0006\u0010$\u001a\u00020%H\u0001¢\u0006\u0002\u0010&\u001a\u0095\u0001\u0010'\u001a\u00020(2\f\u0010)\u001a\b\u0012\u0004\u0012\u00020+0*2#\u0010,\u001a\u001f\u0012\u0004\u0012\u00020+\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u000f0-¢\u0006\u0002\b\u00172#\u0010.\u001a\u001f\u0012\u0004\u0012\u00020+\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u000f0-¢\u0006\u0002\b\u00172\u0006\u0010/\u001a\u00020\u000f2\u0006\u00100\u001a\u00020\u000f2\u0006\u00101\u001a\u00020\u000f2\u0006\u0010 \u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0011\u001a\u00020%H\u0002¢\u0006\u0002\u00102\u001a[\u0010'\u001a\u00020(2\f\u0010)\u001a\b\u0012\u0004\u0012\u00020+0*2\u0006\u00103\u001a\u0002042\u0006\u00105\u001a\u0002042\u0006\u0010/\u001a\u00020\u000f2\u0006\u00100\u001a\u00020\u000f2\u0006\u00101\u001a\u00020\u000f2\u0006\u0010 \u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0011\u001a\u00020%H\u0002¢\u0006\u0002\u00106\u001aS\u00107\u001a\u00020\u000f2\f\u0010)\u001a\b\u0012\u0004\u0012\u00020+0*2#\u0010,\u001a\u001f\u0012\u0004\u0012\u00020+\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u000f0-¢\u0006\u0002\b\u00172\u0006\u00108\u001a\u00020\u000f2\u0006\u00100\u001a\u00020\u000f2\u0006\u0010 \u001a\u00020\u000fH\u0002\u001a\u0090\u0001\u00109\u001a\u00020\u000f2\f\u0010)\u001a\b\u0012\u0004\u0012\u00020+0*2#\u0010,\u001a\u001f\u0012\u0004\u0012\u00020+\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u000f0-¢\u0006\u0002\b\u00172#\u0010.\u001a\u001f\u0012\u0004\u0012\u00020+\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u000f0-¢\u0006\u0002\b\u00172\u0006\u00108\u001a\u00020\u000f2\u0006\u00100\u001a\u00020\u000f2\u0006\u00101\u001a\u00020\u000f2\u0006\u0010 \u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0011\u001a\u00020%H\u0002\u001a%\u0010:\u001a\u00020\u001f2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010 \u001a\u00020\u000fH\u0001¢\u0006\u0002\u0010;\u001a5\u0010<\u001a\u00020#2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010 \u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u000f2\u0006\u0010$\u001a\u00020%H\u0001¢\u0006\u0002\u0010=\u001a\\\u0010>\u001a\u00020?*\u00020@2\u0006\u0010A\u001a\u00020B2\f\u0010C\u001a\b\u0012\u0004\u0012\u00020E0D2\u0006\u0010F\u001a\u00020G2\u0006\u0010H\u001a\u00020G2\u0006\u0010I\u001a\u00020J2\u0006\u0010 \u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0011\u001a\u00020%H\u0000ø\u0001\u0000¢\u0006\u0004\bK\u0010L\u001a\u001c\u0010M\u001a\u00020\u000f*\u00020+2\u0006\u0010N\u001a\u00020O2\u0006\u0010,\u001a\u00020\u000fH\u0000\u001a\u001c\u0010P\u001a\u00020\u000f*\u00020+2\u0006\u0010N\u001a\u00020O2\u0006\u0010.\u001a\u00020\u000fH\u0000\u001a<\u0010Q\u001a\u00020(*\u00020E2\u0006\u0010A\u001a\u00020B2\u0006\u0010I\u001a\u00020R2\u0014\u0010S\u001a\u0010\u0012\u0006\u0012\u0004\u0018\u00010T\u0012\u0004\u0012\u00020\u00070\u0014H\u0000ø\u0001\u0000¢\u0006\u0004\bU\u0010V\u001aT\u0010W\u001a\u00020?*\u00020@2\u0006\u0010I\u001a\u00020J2\u0006\u0010X\u001a\u00020\u000f2\u0006\u0010Y\u001a\u00020\u000f2\u0006\u00105\u001a\u0002042\f\u0010Z\u001a\b\u0012\u0004\u0012\u00020?0[2\u0006\u0010\\\u001a\u00020B2\u0006\u0010]\u001a\u000204H\u0000ø\u0001\u0000¢\u0006\u0004\b^\u0010_\u001a\u001e\u0010`\u001a\u0004\u0018\u00010E*\b\u0012\u0004\u0012\u00020E0D2\b\u0010a\u001a\u0004\u0018\u00010bH\u0002\"\u0014\u0010\u0000\u001a\u00020\u0001X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\u0003\"\u0014\u0010\u0004\u001a\u00020\u0001X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0003\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006c"}, d2 = {"CROSS_AXIS_ALIGNMENT_START", "Landroidx/compose/foundation/layout/CrossAxisAlignment;", "getCROSS_AXIS_ALIGNMENT_START", "()Landroidx/compose/foundation/layout/CrossAxisAlignment;", "CROSS_AXIS_ALIGNMENT_TOP", "getCROSS_AXIS_ALIGNMENT_TOP", "FlowColumn", "", "modifier", "Landroidx/compose/ui/Modifier;", "verticalArrangement", "Landroidx/compose/foundation/layout/Arrangement$Vertical;", "horizontalArrangement", "Landroidx/compose/foundation/layout/Arrangement$Horizontal;", "maxItemsInEachColumn", "", "maxLines", "overflow", "Landroidx/compose/foundation/layout/FlowColumnOverflow;", FirebaseAnalytics.Param.CONTENT, "Lkotlin/Function1;", "Landroidx/compose/foundation/layout/FlowColumnScope;", "Landroidx/compose/runtime/Composable;", "Lkotlin/ExtensionFunctionType;", "(Landroidx/compose/ui/Modifier;Landroidx/compose/foundation/layout/Arrangement$Vertical;Landroidx/compose/foundation/layout/Arrangement$Horizontal;IILandroidx/compose/foundation/layout/FlowColumnOverflow;Lkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;II)V", "FlowRow", "maxItemsInEachRow", "Landroidx/compose/foundation/layout/FlowRowOverflow;", "Landroidx/compose/foundation/layout/FlowRowScope;", "(Landroidx/compose/ui/Modifier;Landroidx/compose/foundation/layout/Arrangement$Horizontal;Landroidx/compose/foundation/layout/Arrangement$Vertical;IILandroidx/compose/foundation/layout/FlowRowOverflow;Lkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;II)V", "columnMeasurementHelper", "Landroidx/compose/ui/layout/MeasurePolicy;", "maxItemsInMainAxis", "(Landroidx/compose/foundation/layout/Arrangement$Vertical;Landroidx/compose/foundation/layout/Arrangement$Horizontal;ILandroidx/compose/runtime/Composer;I)Landroidx/compose/ui/layout/MeasurePolicy;", "columnMeasurementMultiContentHelper", "Landroidx/compose/ui/layout/MultiContentMeasurePolicy;", "overflowState", "Landroidx/compose/foundation/layout/FlowLayoutOverflowState;", "(Landroidx/compose/foundation/layout/Arrangement$Vertical;Landroidx/compose/foundation/layout/Arrangement$Horizontal;IILandroidx/compose/foundation/layout/FlowLayoutOverflowState;Landroidx/compose/runtime/Composer;I)Landroidx/compose/ui/layout/MultiContentMeasurePolicy;", "intrinsicCrossAxisSize", "Landroidx/collection/IntIntPair;", "children", "", "Landroidx/compose/ui/layout/IntrinsicMeasurable;", "mainAxisSize", "Lkotlin/Function3;", "crossAxisSize", "mainAxisAvailable", "mainAxisSpacing", "crossAxisSpacing", "(Ljava/util/List;Lkotlin/jvm/functions/Function3;Lkotlin/jvm/functions/Function3;IIIIILandroidx/compose/foundation/layout/FlowLayoutOverflowState;)J", "mainAxisSizes", "", "crossAxisSizes", "(Ljava/util/List;[I[IIIIIILandroidx/compose/foundation/layout/FlowLayoutOverflowState;)J", "maxIntrinsicMainAxisSize", "crossAxisAvailable", "minIntrinsicMainAxisSize", "rowMeasurementHelper", "(Landroidx/compose/foundation/layout/Arrangement$Horizontal;Landroidx/compose/foundation/layout/Arrangement$Vertical;ILandroidx/compose/runtime/Composer;I)Landroidx/compose/ui/layout/MeasurePolicy;", "rowMeasurementMultiContentHelper", "(Landroidx/compose/foundation/layout/Arrangement$Horizontal;Landroidx/compose/foundation/layout/Arrangement$Vertical;IILandroidx/compose/foundation/layout/FlowLayoutOverflowState;Landroidx/compose/runtime/Composer;I)Landroidx/compose/ui/layout/MultiContentMeasurePolicy;", "breakDownItems", "Landroidx/compose/ui/layout/MeasureResult;", "Landroidx/compose/ui/layout/MeasureScope;", "measurePolicy", "Landroidx/compose/foundation/layout/FlowLineMeasurePolicy;", "measurablesIterator", "", "Landroidx/compose/ui/layout/Measurable;", "mainAxisSpacingDp", "Landroidx/compose/ui/unit/Dp;", "crossAxisSpacingDp", "constraints", "Landroidx/compose/foundation/layout/OrientationIndependentConstraints;", "breakDownItems-di9J0FM", "(Landroidx/compose/ui/layout/MeasureScope;Landroidx/compose/foundation/layout/FlowLineMeasurePolicy;Ljava/util/Iterator;FFJIILandroidx/compose/foundation/layout/FlowLayoutOverflowState;)Landroidx/compose/ui/layout/MeasureResult;", "crossAxisMin", "isHorizontal", "", "mainAxisMin", "measureAndCache", "Landroidx/compose/ui/unit/Constraints;", "storePlaceable", "Landroidx/compose/ui/layout/Placeable;", "measureAndCache-rqJ1uqs", "(Landroidx/compose/ui/layout/Measurable;Landroidx/compose/foundation/layout/FlowLineMeasurePolicy;JLkotlin/jvm/functions/Function1;)J", "placeHelper", "mainAxisTotalSize", "crossAxisTotalSize", FirebaseAnalytics.Param.ITEMS, "Landroidx/compose/runtime/collection/MutableVector;", "measureHelper", "outPosition", "placeHelper-BmaY500", "(Landroidx/compose/ui/layout/MeasureScope;JII[ILandroidx/compose/runtime/collection/MutableVector;Landroidx/compose/foundation/layout/FlowLineMeasurePolicy;[I)Landroidx/compose/ui/layout/MeasureResult;", "safeNext", "info", "Landroidx/compose/foundation/layout/FlowLineInfo;", "foundation-layout_release"}, k = 2, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
public final class FlowLayoutKt {
    private static final CrossAxisAlignment CROSS_AXIS_ALIGNMENT_TOP = CrossAxisAlignment.INSTANCE.vertical$foundation_layout_release(Alignment.INSTANCE.getTop());
    private static final CrossAxisAlignment CROSS_AXIS_ALIGNMENT_START = CrossAxisAlignment.INSTANCE.horizontal$foundation_layout_release(Alignment.INSTANCE.getStart());

    /* JADX WARN: Removed duplicated region for block: B:100:0x0126  */
    /* JADX WARN: Removed duplicated region for block: B:103:0x012e  */
    /* JADX WARN: Removed duplicated region for block: B:106:0x0147  */
    /* JADX WARN: Removed duplicated region for block: B:107:0x0149  */
    /* JADX WARN: Removed duplicated region for block: B:112:0x0158  */
    /* JADX WARN: Removed duplicated region for block: B:115:0x017d  */
    /* JADX WARN: Removed duplicated region for block: B:116:0x017f  */
    /* JADX WARN: Removed duplicated region for block: B:119:0x0187  */
    /* JADX WARN: Removed duplicated region for block: B:120:0x0189  */
    /* JADX WARN: Removed duplicated region for block: B:123:0x0191  */
    /* JADX WARN: Removed duplicated region for block: B:125:0x0199  */
    /* JADX WARN: Removed duplicated region for block: B:149:0x029f  */
    /* JADX WARN: Removed duplicated region for block: B:151:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0050  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0053  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x006c  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x006f  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x0088  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x008b  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x00a6  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x00ab  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x00c4  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x00c7  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x00e4  */
    /* JADX WARN: Removed duplicated region for block: B:84:0x00f7  */
    /* JADX WARN: Removed duplicated region for block: B:85:0x00f9  */
    /* JADX WARN: Removed duplicated region for block: B:86:0x00fe  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x0101  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x0108  */
    /* JADX WARN: Removed duplicated region for block: B:91:0x010b  */
    /* JADX WARN: Removed duplicated region for block: B:94:0x0117  */
    /* JADX WARN: Removed duplicated region for block: B:96:0x011a  */
    /* JADX WARN: Removed duplicated region for block: B:97:0x011c  */
    /* JADX WARN: Removed duplicated region for block: B:99:0x011f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final void FlowRow(androidx.compose.ui.Modifier r19, androidx.compose.foundation.layout.Arrangement.Horizontal r20, androidx.compose.foundation.layout.Arrangement.Vertical r21, int r22, int r23, androidx.compose.foundation.layout.FlowRowOverflow r24, final kotlin.jvm.functions.Function3<? super androidx.compose.foundation.layout.FlowRowScope, ? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r25, androidx.compose.runtime.Composer r26, final int r27, final int r28) {
        /*
            Method dump skipped, instruction units count: 684
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.layout.FlowLayoutKt.FlowRow(androidx.compose.ui.Modifier, androidx.compose.foundation.layout.Arrangement$Horizontal, androidx.compose.foundation.layout.Arrangement$Vertical, int, int, androidx.compose.foundation.layout.FlowRowOverflow, kotlin.jvm.functions.Function3, androidx.compose.runtime.Composer, int, int):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:100:0x0126  */
    /* JADX WARN: Removed duplicated region for block: B:103:0x012e  */
    /* JADX WARN: Removed duplicated region for block: B:106:0x0147  */
    /* JADX WARN: Removed duplicated region for block: B:107:0x0149  */
    /* JADX WARN: Removed duplicated region for block: B:112:0x0158  */
    /* JADX WARN: Removed duplicated region for block: B:115:0x017d  */
    /* JADX WARN: Removed duplicated region for block: B:116:0x017f  */
    /* JADX WARN: Removed duplicated region for block: B:119:0x0187  */
    /* JADX WARN: Removed duplicated region for block: B:120:0x0189  */
    /* JADX WARN: Removed duplicated region for block: B:123:0x0191  */
    /* JADX WARN: Removed duplicated region for block: B:125:0x0199  */
    /* JADX WARN: Removed duplicated region for block: B:149:0x029f  */
    /* JADX WARN: Removed duplicated region for block: B:151:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0050  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0053  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x006c  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x006f  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x0088  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x008b  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x00a6  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x00ab  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x00c4  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x00c7  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x00e4  */
    /* JADX WARN: Removed duplicated region for block: B:84:0x00f7  */
    /* JADX WARN: Removed duplicated region for block: B:85:0x00f9  */
    /* JADX WARN: Removed duplicated region for block: B:86:0x00fe  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x0101  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x0108  */
    /* JADX WARN: Removed duplicated region for block: B:91:0x010b  */
    /* JADX WARN: Removed duplicated region for block: B:94:0x0117  */
    /* JADX WARN: Removed duplicated region for block: B:96:0x011a  */
    /* JADX WARN: Removed duplicated region for block: B:97:0x011c  */
    /* JADX WARN: Removed duplicated region for block: B:99:0x011f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final void FlowColumn(androidx.compose.ui.Modifier r19, androidx.compose.foundation.layout.Arrangement.Vertical r20, androidx.compose.foundation.layout.Arrangement.Horizontal r21, int r22, int r23, androidx.compose.foundation.layout.FlowColumnOverflow r24, final kotlin.jvm.functions.Function3<? super androidx.compose.foundation.layout.FlowColumnScope, ? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r25, androidx.compose.runtime.Composer r26, final int r27, final int r28) {
        /*
            Method dump skipped, instruction units count: 684
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.layout.FlowLayoutKt.FlowColumn(androidx.compose.ui.Modifier, androidx.compose.foundation.layout.Arrangement$Vertical, androidx.compose.foundation.layout.Arrangement$Horizontal, int, int, androidx.compose.foundation.layout.FlowColumnOverflow, kotlin.jvm.functions.Function3, androidx.compose.runtime.Composer, int, int):void");
    }

    public static final MeasurePolicy rowMeasurementHelper(Arrangement.Horizontal horizontal, Arrangement.Vertical vertical, int i, Composer composer, int i2) {
        ComposerKt.sourceInformationMarkerStart(composer, 1479255111, "C(rowMeasurementHelper)P(!1,2)380@13086L883:FlowLayout.kt#2w3rfo");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1479255111, i2, -1, "androidx.compose.foundation.layout.rowMeasurementHelper (FlowLayout.kt:379)");
        }
        ComposerKt.sourceInformationMarkerStart(composer, -75342195, "CC(remember):FlowLayout.kt#9igjgp");
        boolean z = ((((i2 & 14) ^ 6) > 4 && composer.changed(horizontal)) || (i2 & 6) == 4) | ((((i2 & 112) ^ 48) > 32 && composer.changed(vertical)) || (i2 & 48) == 32) | ((((i2 & 896) ^ 384) > 256 && composer.changed(i)) || (i2 & 384) == 256);
        MeasurePolicy measurePolicyRememberedValue = composer.rememberedValue();
        if (z || measurePolicyRememberedValue == Composer.INSTANCE.getEmpty()) {
            final FlowMeasurePolicy flowMeasurePolicy = new FlowMeasurePolicy(true, horizontal, vertical, horizontal.getSpacing(), CROSS_AXIS_ALIGNMENT_TOP, vertical.getSpacing(), i, Integer.MAX_VALUE, FlowRowOverflow.INSTANCE.getVisible().createOverflowState$foundation_layout_release(), null);
            measurePolicyRememberedValue = new MeasurePolicy() { // from class: androidx.compose.foundation.layout.FlowLayoutKt$rowMeasurementHelper$1$1
                @Override // androidx.compose.ui.layout.MeasurePolicy
                /* JADX INFO: renamed from: measure-3p2s80s */
                public final MeasureResult mo322measure3p2s80s(MeasureScope measureScope, List<? extends Measurable> list, long j) {
                    return flowMeasurePolicy.mo913measure3p2s80s(measureScope, CollectionsKt.listOf(list), j);
                }
            };
            composer.updateRememberedValue(measurePolicyRememberedValue);
        }
        MeasurePolicy measurePolicy = (MeasurePolicy) measurePolicyRememberedValue;
        ComposerKt.sourceInformationMarkerEnd(composer);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd(composer);
        return measurePolicy;
    }

    public static final MultiContentMeasurePolicy rowMeasurementMultiContentHelper(Arrangement.Horizontal horizontal, Arrangement.Vertical vertical, int i, int i2, FlowLayoutOverflowState flowLayoutOverflowState, Composer composer, int i3) {
        ComposerKt.sourceInformationMarkerStart(composer, -2134502475, "C(rowMeasurementMultiContentHelper)P(!1,4)414@14301L649:FlowLayout.kt#2w3rfo");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-2134502475, i3, -1, "androidx.compose.foundation.layout.rowMeasurementMultiContentHelper (FlowLayout.kt:413)");
        }
        ComposerKt.sourceInformationMarkerStart(composer, 744443043, "CC(remember):FlowLayout.kt#9igjgp");
        boolean zChanged = ((((i3 & 14) ^ 6) > 4 && composer.changed(horizontal)) || (i3 & 6) == 4) | ((((i3 & 112) ^ 48) > 32 && composer.changed(vertical)) || (i3 & 48) == 32) | ((((i3 & 896) ^ 384) > 256 && composer.changed(i)) || (i3 & 384) == 256) | ((((i3 & 7168) ^ 3072) > 2048 && composer.changed(i2)) || (i3 & 3072) == 2048) | composer.changed(flowLayoutOverflowState);
        Object objRememberedValue = composer.rememberedValue();
        if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
            FlowMeasurePolicy flowMeasurePolicy = new FlowMeasurePolicy(true, horizontal, vertical, horizontal.getSpacing(), CROSS_AXIS_ALIGNMENT_TOP, vertical.getSpacing(), i, i2, flowLayoutOverflowState, null);
            composer.updateRememberedValue(flowMeasurePolicy);
            objRememberedValue = flowMeasurePolicy;
        }
        FlowMeasurePolicy flowMeasurePolicy2 = (FlowMeasurePolicy) objRememberedValue;
        ComposerKt.sourceInformationMarkerEnd(composer);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd(composer);
        return flowMeasurePolicy2;
    }

    public static final MeasurePolicy columnMeasurementHelper(Arrangement.Vertical vertical, Arrangement.Horizontal horizontal, int i, Composer composer, int i2) {
        ComposerKt.sourceInformationMarkerStart(composer, -2013098357, "C(columnMeasurementHelper)P(2)443@15212L856:FlowLayout.kt#2w3rfo");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-2013098357, i2, -1, "androidx.compose.foundation.layout.columnMeasurementHelper (FlowLayout.kt:442)");
        }
        ComposerKt.sourceInformationMarkerStart(composer, 1837869538, "CC(remember):FlowLayout.kt#9igjgp");
        boolean z = ((((i2 & 14) ^ 6) > 4 && composer.changed(vertical)) || (i2 & 6) == 4) | ((((i2 & 112) ^ 48) > 32 && composer.changed(horizontal)) || (i2 & 48) == 32) | ((((i2 & 896) ^ 384) > 256 && composer.changed(i)) || (i2 & 384) == 256);
        MeasurePolicy measurePolicyRememberedValue = composer.rememberedValue();
        if (z || measurePolicyRememberedValue == Composer.INSTANCE.getEmpty()) {
            final FlowMeasurePolicy flowMeasurePolicy = new FlowMeasurePolicy(false, horizontal, vertical, vertical.getSpacing(), CROSS_AXIS_ALIGNMENT_START, horizontal.getSpacing(), i, Integer.MAX_VALUE, FlowRowOverflow.INSTANCE.getVisible().createOverflowState$foundation_layout_release(), null);
            measurePolicyRememberedValue = new MeasurePolicy() { // from class: androidx.compose.foundation.layout.FlowLayoutKt$columnMeasurementHelper$1$1
                @Override // androidx.compose.ui.layout.MeasurePolicy
                /* JADX INFO: renamed from: measure-3p2s80s */
                public final MeasureResult mo322measure3p2s80s(MeasureScope measureScope, List<? extends Measurable> list, long j) {
                    return flowMeasurePolicy.mo913measure3p2s80s(measureScope, CollectionsKt.listOf(list), j);
                }
            };
            composer.updateRememberedValue(measurePolicyRememberedValue);
        }
        MeasurePolicy measurePolicy = (MeasurePolicy) measurePolicyRememberedValue;
        ComposerKt.sourceInformationMarkerEnd(composer);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd(composer);
        return measurePolicy;
    }

    public static final MultiContentMeasurePolicy columnMeasurementMultiContentHelper(Arrangement.Vertical vertical, Arrangement.Horizontal horizontal, int i, int i2, FlowLayoutOverflowState flowLayoutOverflowState, Composer composer, int i3) {
        ComposerKt.sourceInformationMarkerStart(composer, 748776953, "C(columnMeasurementMultiContentHelper)P(4)475@16365L652:FlowLayout.kt#2w3rfo");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(748776953, i3, -1, "androidx.compose.foundation.layout.columnMeasurementMultiContentHelper (FlowLayout.kt:474)");
        }
        ComposerKt.sourceInformationMarkerStart(composer, 287541334, "CC(remember):FlowLayout.kt#9igjgp");
        boolean zChanged = ((((i3 & 14) ^ 6) > 4 && composer.changed(vertical)) || (i3 & 6) == 4) | ((((i3 & 112) ^ 48) > 32 && composer.changed(horizontal)) || (i3 & 48) == 32) | ((((i3 & 896) ^ 384) > 256 && composer.changed(i)) || (i3 & 384) == 256) | ((((i3 & 7168) ^ 3072) > 2048 && composer.changed(i2)) || (i3 & 3072) == 2048) | composer.changed(flowLayoutOverflowState);
        Object objRememberedValue = composer.rememberedValue();
        if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
            FlowMeasurePolicy flowMeasurePolicy = new FlowMeasurePolicy(false, horizontal, vertical, vertical.getSpacing(), CROSS_AXIS_ALIGNMENT_START, horizontal.getSpacing(), i, i2, flowLayoutOverflowState, null);
            composer.updateRememberedValue(flowMeasurePolicy);
            objRememberedValue = flowMeasurePolicy;
        }
        FlowMeasurePolicy flowMeasurePolicy2 = (FlowMeasurePolicy) objRememberedValue;
        ComposerKt.sourceInformationMarkerEnd(composer);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd(composer);
        return flowMeasurePolicy2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Type inference failed for: r5v2, types: [kotlin.collections.IntIterator] */
    /* JADX WARN: Type inference failed for: r9v13, types: [kotlin.collections.IntIterator] */
    public static final int minIntrinsicMainAxisSize(List<? extends IntrinsicMeasurable> list, Function3<? super IntrinsicMeasurable, ? super Integer, ? super Integer, Integer> function3, Function3<? super IntrinsicMeasurable, ? super Integer, ? super Integer, Integer> function32, int i, int i2, int i3, int i4, int i5, FlowLayoutOverflowState flowLayoutOverflowState) {
        int i6 = i4;
        int i7 = i5;
        if (list.isEmpty()) {
            return 0;
        }
        int size = list.size();
        int[] iArr = new int[size];
        for (int i8 = 0; i8 < size; i8++) {
            iArr[i8] = 0;
        }
        int size2 = list.size();
        int[] iArr2 = new int[size2];
        for (int i9 = 0; i9 < size2; i9++) {
            iArr2[i9] = 0;
        }
        int size3 = list.size();
        for (int i10 = 0; i10 < size3; i10++) {
            IntrinsicMeasurable intrinsicMeasurable = list.get(i10);
            int iIntValue = function3.invoke(intrinsicMeasurable, Integer.valueOf(i10), Integer.valueOf(i)).intValue();
            iArr[i10] = iIntValue;
            iArr2[i10] = function32.invoke(intrinsicMeasurable, Integer.valueOf(i10), Integer.valueOf(iIntValue)).intValue();
        }
        List<? extends IntrinsicMeasurable> list2 = list;
        int i11 = Integer.MAX_VALUE;
        if (i7 != Integer.MAX_VALUE && i6 != Integer.MAX_VALUE) {
            i11 = i6 * i7;
        }
        int iMin = Math.min(i11 - (((i11 >= list2.size() || !(flowLayoutOverflowState.getType$foundation_layout_release() == FlowLayoutOverflow.OverflowType.ExpandIndicator || flowLayoutOverflowState.getType$foundation_layout_release() == FlowLayoutOverflow.OverflowType.ExpandOrCollapseIndicator)) && (i11 < list2.size() || i7 < flowLayoutOverflowState.getMinLinesToShowCollapse$foundation_layout_release() || flowLayoutOverflowState.getType$foundation_layout_release() != FlowLayoutOverflow.OverflowType.ExpandOrCollapseIndicator)) ? 0 : 1), list2.size());
        int iSum = ArraysKt.sum(iArr) + ((list2.size() - 1) * i2);
        if (size2 != 0) {
            int i12 = iArr2[0];
            ?? it = new IntRange(1, ArraysKt.getLastIndex(iArr2)).iterator();
            while (it.hasNext()) {
                int i13 = iArr2[it.nextInt()];
                if (i12 < i13) {
                    i12 = i13;
                }
            }
            if (size != 0) {
                int i14 = iArr[0];
                ?? it2 = new IntRange(1, ArraysKt.getLastIndex(iArr)).iterator();
                while (it2.hasNext()) {
                    int i15 = iArr[it2.nextInt()];
                    if (i14 < i15) {
                        i14 = i15;
                    }
                }
                int i16 = i14;
                int i17 = iSum;
                while (i16 <= i17 && i12 != i) {
                    int i18 = (i16 + i17) / 2;
                    long jIntrinsicCrossAxisSize = intrinsicCrossAxisSize(list2, iArr, iArr2, i18, i2, i3, i6, i7, flowLayoutOverflowState);
                    int iM312getFirstimpl = IntIntPair.m312getFirstimpl(jIntrinsicCrossAxisSize);
                    int iM313getSecondimpl = IntIntPair.m313getSecondimpl(jIntrinsicCrossAxisSize);
                    if (iM312getFirstimpl > i || iM313getSecondimpl < iMin) {
                        i16 = i18 + 1;
                        if (i16 > i17) {
                            return i16;
                        }
                    } else {
                        if (iM312getFirstimpl >= i) {
                            return i18;
                        }
                        i17 = i18 - 1;
                    }
                    list2 = list;
                    i6 = i4;
                    i7 = i5;
                    iSum = i18;
                    i12 = iM312getFirstimpl;
                }
                return iSum;
            }
            throw new NoSuchElementException();
        }
        throw new NoSuchElementException();
    }

    private static final long intrinsicCrossAxisSize(List<? extends IntrinsicMeasurable> list, final int[] iArr, final int[] iArr2, int i, int i2, int i3, int i4, int i5, FlowLayoutOverflowState flowLayoutOverflowState) {
        return intrinsicCrossAxisSize(list, new Function3<IntrinsicMeasurable, Integer, Integer, Integer>() { // from class: androidx.compose.foundation.layout.FlowLayoutKt.intrinsicCrossAxisSize.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(3);
            }

            @Override // kotlin.jvm.functions.Function3
            public /* bridge */ /* synthetic */ Integer invoke(IntrinsicMeasurable intrinsicMeasurable, Integer num, Integer num2) {
                return invoke(intrinsicMeasurable, num.intValue(), num2.intValue());
            }

            public final Integer invoke(IntrinsicMeasurable intrinsicMeasurable, int i6, int i7) {
                return Integer.valueOf(iArr[i6]);
            }
        }, new Function3<IntrinsicMeasurable, Integer, Integer, Integer>() { // from class: androidx.compose.foundation.layout.FlowLayoutKt.intrinsicCrossAxisSize.2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(3);
            }

            @Override // kotlin.jvm.functions.Function3
            public /* bridge */ /* synthetic */ Integer invoke(IntrinsicMeasurable intrinsicMeasurable, Integer num, Integer num2) {
                return invoke(intrinsicMeasurable, num.intValue(), num2.intValue());
            }

            public final Integer invoke(IntrinsicMeasurable intrinsicMeasurable, int i6, int i7) {
                return Integer.valueOf(iArr2[i6]);
            }
        }, i, i2, i3, i4, i5, flowLayoutOverflowState);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final long intrinsicCrossAxisSize(List<? extends IntrinsicMeasurable> list, Function3<? super IntrinsicMeasurable, ? super Integer, ? super Integer, Integer> function3, Function3<? super IntrinsicMeasurable, ? super Integer, ? super Integer, Integer> function32, int i, int i2, int i3, int i4, int i5, FlowLayoutOverflowState flowLayoutOverflowState) {
        int i6;
        if (list.isEmpty()) {
            return IntIntPair.m308constructorimpl(0, 0);
        }
        FlowLayoutBuildingBlocks flowLayoutBuildingBlocks = new FlowLayoutBuildingBlocks(i4, flowLayoutOverflowState, OrientationIndependentConstraints.m932constructorimpl(0, i, 0, Integer.MAX_VALUE), i5, i2, i3, null);
        IntrinsicMeasurable intrinsicMeasurable = (IntrinsicMeasurable) CollectionsKt.getOrNull(list, 0);
        int iIntValue = intrinsicMeasurable != null ? function32.invoke(intrinsicMeasurable, 0, Integer.valueOf(i)).intValue() : 0;
        int iIntValue2 = intrinsicMeasurable != null ? function3.invoke(intrinsicMeasurable, 0, Integer.valueOf(iIntValue)).intValue() : 0;
        int i7 = 0;
        int iMax = 0;
        if (flowLayoutBuildingBlocks.m888getWrapInfoOpUlnko(list.size() > 1, 0, IntIntPair.m308constructorimpl(i, Integer.MAX_VALUE), intrinsicMeasurable == null ? null : IntIntPair.m305boximpl(IntIntPair.m308constructorimpl(iIntValue2, iIntValue)), 0, 0, 0, false, false).getIsLastItemInContainer()) {
            IntIntPair intIntPairM893ellipsisSizeF35zmw$foundation_layout_release = flowLayoutOverflowState.m893ellipsisSizeF35zmw$foundation_layout_release(intrinsicMeasurable != null, 0, 0);
            return IntIntPair.m308constructorimpl(intIntPairM893ellipsisSizeF35zmw$foundation_layout_release != null ? IntIntPair.m313getSecondimpl(intIntPairM893ellipsisSizeF35zmw$foundation_layout_release.getPackedValue()) : 0, 0);
        }
        int size = list.size();
        int i8 = i;
        int i9 = 0;
        int i10 = 0;
        int i11 = 0;
        int i12 = 0;
        while (true) {
            int i13 = iMax;
            if (i9 >= size) {
                i6 = i10;
                break;
            }
            int i14 = i8 - iIntValue2;
            int i15 = i9 + 1;
            iMax = Math.max(i13, iIntValue);
            IntrinsicMeasurable intrinsicMeasurable2 = (IntrinsicMeasurable) CollectionsKt.getOrNull(list, i15);
            int iIntValue3 = intrinsicMeasurable2 != null ? function32.invoke(intrinsicMeasurable2, Integer.valueOf(i15), Integer.valueOf(i)).intValue() : 0;
            int iIntValue4 = intrinsicMeasurable2 != null ? function3.invoke(intrinsicMeasurable2, Integer.valueOf(i15), Integer.valueOf(iIntValue3)).intValue() + i2 : 0;
            int i16 = i15 - i11;
            i6 = i15;
            int i17 = i12;
            FlowLayoutBuildingBlocks.WrapInfo wrapInfoM888getWrapInfoOpUlnko = flowLayoutBuildingBlocks.m888getWrapInfoOpUlnko(i9 + 2 < list.size(), i16, IntIntPair.m308constructorimpl(i14, Integer.MAX_VALUE), intrinsicMeasurable2 == null ? null : IntIntPair.m305boximpl(IntIntPair.m308constructorimpl(iIntValue4, iIntValue3)), i17, i7, iMax, false, false);
            if (wrapInfoM888getWrapInfoOpUlnko.getIsLastItemInLine()) {
                int iM313getSecondimpl = i7 + iMax + i3;
                FlowLayoutBuildingBlocks.WrapEllipsisInfo wrapEllipsisInfo = flowLayoutBuildingBlocks.getWrapEllipsisInfo(wrapInfoM888getWrapInfoOpUlnko, intrinsicMeasurable2 != null, i17, iM313getSecondimpl, i14, i16);
                iIntValue4 -= i2;
                i12 = i17 + 1;
                if (wrapInfoM888getWrapInfoOpUlnko.getIsLastItemInContainer()) {
                    if (wrapEllipsisInfo != null) {
                        long ellipsisSize = wrapEllipsisInfo.getEllipsisSize();
                        if (!wrapEllipsisInfo.getPlaceEllipsisOnLastContentLine()) {
                            iM313getSecondimpl += IntIntPair.m313getSecondimpl(ellipsisSize) + i3;
                        }
                    }
                    i7 = iM313getSecondimpl;
                } else {
                    i8 = i;
                    i11 = i6;
                    i7 = iM313getSecondimpl;
                    iMax = 0;
                }
            } else {
                i8 = i14;
                i12 = i17;
            }
            iIntValue2 = iIntValue4;
            iIntValue = iIntValue3;
            i9 = i6;
            i10 = i9;
        }
        return IntIntPair.m308constructorimpl(i7 - i3, i6);
    }

    private static final Measurable safeNext(Iterator<? extends Measurable> it, FlowLineInfo flowLineInfo) {
        try {
            if (it instanceof ContextualFlowItemIterator) {
                Intrinsics.checkNotNull(flowLineInfo);
                return ((ContextualFlowItemIterator) it).getNext$foundation_layout_release(flowLineInfo);
            }
            return it.next();
        } catch (ArrayIndexOutOfBoundsException unused) {
            return null;
        }
    }

    public static final int mainAxisMin(IntrinsicMeasurable intrinsicMeasurable, boolean z, int i) {
        if (z) {
            return intrinsicMeasurable.minIntrinsicWidth(i);
        }
        return intrinsicMeasurable.minIntrinsicHeight(i);
    }

    public static final int crossAxisMin(IntrinsicMeasurable intrinsicMeasurable, boolean z, int i) {
        if (z) {
            return intrinsicMeasurable.minIntrinsicHeight(i);
        }
        return intrinsicMeasurable.minIntrinsicWidth(i);
    }

    public static final CrossAxisAlignment getCROSS_AXIS_ALIGNMENT_TOP() {
        return CROSS_AXIS_ALIGNMENT_TOP;
    }

    public static final CrossAxisAlignment getCROSS_AXIS_ALIGNMENT_START() {
        return CROSS_AXIS_ALIGNMENT_START;
    }

    /* JADX INFO: renamed from: measureAndCache-rqJ1uqs, reason: not valid java name */
    public static final long m891measureAndCacherqJ1uqs(Measurable measurable, FlowLineMeasurePolicy flowLineMeasurePolicy, long j, Function1<? super Placeable, Unit> function1) {
        FlowLayoutData flowLayoutData;
        Measurable measurable2 = measurable;
        if (RowColumnImplKt.getWeight(RowColumnImplKt.getRowColumnParentData(measurable2)) == 0.0f) {
            RowColumnParentData rowColumnParentData = RowColumnImplKt.getRowColumnParentData(measurable2);
            if (((rowColumnParentData == null || (flowLayoutData = rowColumnParentData.getFlowLayoutData()) == null) ? null : Float.valueOf(flowLayoutData.getFillCrossAxisFraction())) == null) {
                Placeable placeableMo5903measureBRTryo0 = measurable.mo5903measureBRTryo0(j);
                function1.invoke(placeableMo5903measureBRTryo0);
                return IntIntPair.m308constructorimpl(flowLineMeasurePolicy.mainAxisSize(placeableMo5903measureBRTryo0), flowLineMeasurePolicy.crossAxisSize(placeableMo5903measureBRTryo0));
            }
        }
        int iMainAxisMin = mainAxisMin(measurable2, flowLineMeasurePolicy.isHorizontal(), Integer.MAX_VALUE);
        return IntIntPair.m308constructorimpl(iMainAxisMin, crossAxisMin(measurable2, flowLineMeasurePolicy.isHorizontal(), iMainAxisMin));
    }

    /* JADX INFO: renamed from: placeHelper-BmaY500, reason: not valid java name */
    public static final MeasureResult m892placeHelperBmaY500(MeasureScope measureScope, long j, int i, int i2, int[] iArr, final MutableVector<MeasureResult> mutableVector, FlowLineMeasurePolicy flowLineMeasurePolicy, int[] iArr2) {
        int iCoerceIn;
        int i3;
        int i4;
        boolean zIsHorizontal = flowLineMeasurePolicy.isHorizontal();
        Arrangement.Vertical verticalArrangement = flowLineMeasurePolicy.getVerticalArrangement();
        Arrangement.Horizontal horizontalArrangement = flowLineMeasurePolicy.getHorizontalArrangement();
        if (zIsHorizontal) {
            if (verticalArrangement == null) {
                throw new IllegalArgumentException("null verticalArrangement".toString());
            }
            iCoerceIn = RangesKt.coerceIn(i2 + (measureScope.mo651roundToPx0680j_4(verticalArrangement.getSpacing()) * (mutableVector.getSize() - 1)), Constraints.m6981getMinHeightimpl(j), Constraints.m6979getMaxHeightimpl(j));
            verticalArrangement.arrange(measureScope, iCoerceIn, iArr, iArr2);
        } else {
            if (horizontalArrangement == null) {
                throw new IllegalArgumentException("null horizontalArrangement".toString());
            }
            int iCoerceIn2 = RangesKt.coerceIn(i2 + (measureScope.mo651roundToPx0680j_4(horizontalArrangement.getSpacing()) * (mutableVector.getSize() - 1)), Constraints.m6981getMinHeightimpl(j), Constraints.m6979getMaxHeightimpl(j));
            horizontalArrangement.arrange(measureScope, iCoerceIn2, iArr, measureScope.getLayoutDirection(), iArr2);
            iCoerceIn = iCoerceIn2;
        }
        int iCoerceIn3 = RangesKt.coerceIn(i, Constraints.m6982getMinWidthimpl(j), Constraints.m6980getMaxWidthimpl(j));
        if (zIsHorizontal) {
            i4 = iCoerceIn3;
            i3 = iCoerceIn;
        } else {
            i3 = iCoerceIn3;
            i4 = iCoerceIn;
        }
        return MeasureScope.layout$default(measureScope, i4, i3, null, new Function1<Placeable.PlacementScope, Unit>() { // from class: androidx.compose.foundation.layout.FlowLayoutKt$placeHelper$5
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
                MutableVector<MeasureResult> mutableVector2 = mutableVector;
                int size = mutableVector2.getSize();
                if (size > 0) {
                    MeasureResult[] content = mutableVector2.getContent();
                    int i5 = 0;
                    do {
                        content[i5].placeChildren();
                        i5++;
                    } while (i5 < size);
                }
            }
        }, 4, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final int maxIntrinsicMainAxisSize(List<? extends IntrinsicMeasurable> list, Function3<? super IntrinsicMeasurable, ? super Integer, ? super Integer, Integer> function3, int i, int i2, int i3) {
        int size = list.size();
        int i4 = 0;
        int iMax = 0;
        int i5 = 0;
        int i6 = 0;
        while (i4 < size) {
            int iIntValue = function3.invoke(list.get(i4), Integer.valueOf(i4), Integer.valueOf(i)).intValue() + i2;
            int i7 = i4 + 1;
            if (i7 - i5 == i3 || i7 == list.size()) {
                iMax = Math.max(iMax, (i6 + iIntValue) - i2);
                i6 = 0;
                i5 = i4;
            } else {
                i6 += iIntValue;
            }
            i4 = i7;
        }
        return iMax;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX INFO: renamed from: breakDownItems-di9J0FM, reason: not valid java name */
    public static final MeasureResult m890breakDownItemsdi9J0FM(MeasureScope measureScope, FlowLineMeasurePolicy flowLineMeasurePolicy, Iterator<? extends Measurable> it, float f, float f2, long j, int i, int i2, FlowLayoutOverflowState flowLayoutOverflowState) {
        int i3;
        FlowLayoutBuildingBlocks.WrapEllipsisInfo wrapEllipsisInfo;
        MutableIntObjectMap mutableIntObjectMap;
        ArrayList arrayList;
        int i4;
        int i5;
        MeasureScope measureScope2;
        int[] iArr;
        FlowLineMeasurePolicy flowLineMeasurePolicy2;
        int[] iArr2;
        long j2;
        int i6;
        int i7;
        int i8;
        MutableIntObjectMap mutableIntObjectMap2;
        ArrayList arrayList2;
        MutableIntList mutableIntList;
        MutableIntList mutableIntList2;
        int i9;
        int i10;
        FlowLayoutBuildingBlocks.WrapEllipsisInfo wrapEllipsisInfo2;
        int i11;
        int i12;
        int iCoerceAtLeast;
        int iCoerceAtLeast2;
        MeasureScope measureScope3 = measureScope;
        MutableVector mutableVector = new MutableVector(new MeasureResult[16], 0);
        int iM6980getMaxWidthimpl = Constraints.m6980getMaxWidthimpl(j);
        int iM6982getMinWidthimpl = Constraints.m6982getMinWidthimpl(j);
        int iM6979getMaxHeightimpl = Constraints.m6979getMaxHeightimpl(j);
        MutableIntObjectMap mutableIntObjectMapMutableIntObjectMapOf = IntObjectMapKt.mutableIntObjectMapOf();
        ArrayList arrayList3 = new ArrayList();
        int iCeil = (int) Math.ceil(measureScope3.mo657toPx0680j_4(f));
        int iCeil2 = (int) Math.ceil(measureScope3.mo657toPx0680j_4(f2));
        long jM932constructorimpl = OrientationIndependentConstraints.m932constructorimpl(0, iM6980getMaxWidthimpl, 0, iM6979getMaxHeightimpl);
        long jM947toBoxConstraintsOenEA2s = OrientationIndependentConstraints.m947toBoxConstraintsOenEA2s(OrientationIndependentConstraints.m936copyyUG9Ft0$default(jM932constructorimpl, 0, 0, 0, 0, 14, null), flowLineMeasurePolicy.isHorizontal() ? LayoutOrientation.Horizontal : LayoutOrientation.Vertical);
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        FlowLineInfo flowLineInfo = it instanceof ContextualFlowItemIterator ? new FlowLineInfo(0, 0, measureScope3.mo654toDpu2uoSUM(iM6980getMaxWidthimpl), measureScope3.mo654toDpu2uoSUM(iM6979getMaxHeightimpl), null) : null;
        Measurable measurableSafeNext = !it.hasNext() ? null : safeNext(it, flowLineInfo);
        IntIntPair intIntPairM305boximpl = measurableSafeNext != null ? IntIntPair.m305boximpl(m891measureAndCacherqJ1uqs(measurableSafeNext, flowLineMeasurePolicy, jM947toBoxConstraintsOenEA2s, new Function1<Placeable, Unit>() { // from class: androidx.compose.foundation.layout.FlowLayoutKt$breakDownItems$nextSize$1$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Placeable placeable) {
                invoke2(placeable);
                return Unit.INSTANCE;
            }

            /* JADX WARN: Multi-variable type inference failed */
            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(Placeable placeable) {
                objectRef.element = placeable;
            }
        })) : null;
        Integer numValueOf = intIntPairM305boximpl != null ? Integer.valueOf(IntIntPair.m312getFirstimpl(intIntPairM305boximpl.getPackedValue())) : null;
        Integer numValueOf2 = intIntPairM305boximpl != null ? Integer.valueOf(IntIntPair.m313getSecondimpl(intIntPairM305boximpl.getPackedValue())) : null;
        Integer numValueOf3 = numValueOf;
        Measurable measurable = measurableSafeNext;
        MutableIntList mutableIntList3 = new MutableIntList(0, 1, null);
        MutableIntList mutableIntList4 = new MutableIntList(0, 1, null);
        FlowLayoutBuildingBlocks flowLayoutBuildingBlocks = new FlowLayoutBuildingBlocks(i, flowLayoutOverflowState, j, i2, iCeil, iCeil2, null);
        FlowLayoutBuildingBlocks.WrapInfo wrapInfoM888getWrapInfoOpUlnko = flowLayoutBuildingBlocks.m888getWrapInfoOpUlnko(it.hasNext(), 0, IntIntPair.m308constructorimpl(iM6980getMaxWidthimpl, iM6979getMaxHeightimpl), intIntPairM305boximpl, 0, 0, 0, false, false);
        if (wrapInfoM888getWrapInfoOpUlnko.getIsLastItemInContainer()) {
            wrapEllipsisInfo = flowLayoutBuildingBlocks.getWrapEllipsisInfo(wrapInfoM888getWrapInfoOpUlnko, intIntPairM305boximpl != null, -1, 0, iM6980getMaxWidthimpl, 0);
            i3 = iM6980getMaxWidthimpl;
        } else {
            i3 = iM6980getMaxWidthimpl;
            wrapEllipsisInfo = null;
        }
        FlowLayoutBuildingBlocks.WrapEllipsisInfo wrapEllipsisInfo3 = wrapEllipsisInfo;
        MutableIntList mutableIntList5 = mutableIntList3;
        int i13 = iM6979getMaxHeightimpl;
        int i14 = i3;
        FlowLayoutBuildingBlocks.WrapInfo wrapInfo = wrapInfoM888getWrapInfoOpUlnko;
        int i15 = iM6982getMinWidthimpl;
        Measurable measurableSafeNext2 = measurable;
        int i16 = 0;
        int i17 = 0;
        int i18 = 0;
        int i19 = 0;
        int i20 = 0;
        int i21 = 0;
        while (!wrapInfo.getIsLastItemInContainer() && measurableSafeNext2 != null) {
            Intrinsics.checkNotNull(numValueOf3);
            int iIntValue = numValueOf3.intValue();
            Intrinsics.checkNotNull(numValueOf2);
            MutableIntList mutableIntList6 = mutableIntList4;
            int i22 = i3;
            int i23 = i19 + iIntValue;
            int iMax = Math.max(i16, numValueOf2.intValue());
            int i24 = i14 - iIntValue;
            int i25 = i17 + 1;
            int i26 = i15;
            flowLayoutOverflowState.setItemShown$foundation_layout_release(i25);
            arrayList3.add(measurableSafeNext2);
            mutableIntObjectMapMutableIntObjectMapOf.set(i17, objectRef.element);
            int i27 = i25 - i18;
            boolean z = i27 < i;
            if (flowLineInfo != null) {
                if (z) {
                    i8 = i27;
                    i11 = i20;
                } else {
                    i8 = i27;
                    i11 = i20 + 1;
                }
                int i28 = z ? i8 : 0;
                if (z) {
                    mutableIntObjectMap2 = mutableIntObjectMapMutableIntObjectMapOf;
                    i12 = 0;
                    iCoerceAtLeast = RangesKt.coerceAtLeast(i24 - iCeil, 0);
                } else {
                    mutableIntObjectMap2 = mutableIntObjectMapMutableIntObjectMapOf;
                    i12 = 0;
                    iCoerceAtLeast = i22;
                }
                float f3 = measureScope3.mo654toDpu2uoSUM(iCoerceAtLeast);
                if (z) {
                    arrayList2 = arrayList3;
                    iCoerceAtLeast2 = i13;
                } else {
                    arrayList2 = arrayList3;
                    iCoerceAtLeast2 = RangesKt.coerceAtLeast((i13 - iMax) - iCeil2, i12);
                }
                flowLineInfo.m901update4j6BHR0$foundation_layout_release(i11, i28, f3, measureScope3.mo654toDpu2uoSUM(iCoerceAtLeast2));
            } else {
                i8 = i27;
                mutableIntObjectMap2 = mutableIntObjectMapMutableIntObjectMapOf;
                arrayList2 = arrayList3;
            }
            measurableSafeNext2 = !it.hasNext() ? null : safeNext(it, flowLineInfo);
            IntIntPair intIntPairM305boximpl2 = null;
            objectRef.element = null;
            IntIntPair intIntPairM305boximpl3 = measurableSafeNext2 != null ? IntIntPair.m305boximpl(m891measureAndCacherqJ1uqs(measurableSafeNext2, flowLineMeasurePolicy, jM947toBoxConstraintsOenEA2s, new Function1<Placeable, Unit>() { // from class: androidx.compose.foundation.layout.FlowLayoutKt$breakDownItems$1$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(Placeable placeable) {
                    invoke2(placeable);
                    return Unit.INSTANCE;
                }

                /* JADX WARN: Multi-variable type inference failed */
                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(Placeable placeable) {
                    objectRef.element = placeable;
                }
            })) : null;
            Integer numValueOf4 = intIntPairM305boximpl3 != null ? Integer.valueOf(IntIntPair.m312getFirstimpl(intIntPairM305boximpl3.getPackedValue()) + iCeil) : null;
            numValueOf2 = intIntPairM305boximpl3 != null ? Integer.valueOf(IntIntPair.m313getSecondimpl(intIntPairM305boximpl3.getPackedValue())) : null;
            boolean zHasNext = it.hasNext();
            int i29 = i20;
            long jM308constructorimpl = IntIntPair.m308constructorimpl(i24, i13);
            if (intIntPairM305boximpl3 != null) {
                Intrinsics.checkNotNull(numValueOf4);
                int iIntValue2 = numValueOf4.intValue();
                Intrinsics.checkNotNull(numValueOf2);
                intIntPairM305boximpl2 = IntIntPair.m305boximpl(IntIntPair.m308constructorimpl(iIntValue2, numValueOf2.intValue()));
            }
            FlowLayoutBuildingBlocks.WrapInfo wrapInfoM888getWrapInfoOpUlnko2 = flowLayoutBuildingBlocks.m888getWrapInfoOpUlnko(zHasNext, i8, jM308constructorimpl, intIntPairM305boximpl2, i29, i21, iMax, false, false);
            if (wrapInfoM888getWrapInfoOpUlnko2.getIsLastItemInLine()) {
                int iMin = Math.min(Math.max(i26, i23), i22);
                int i30 = i21 + iMax;
                FlowLayoutBuildingBlocks.WrapEllipsisInfo wrapEllipsisInfo4 = flowLayoutBuildingBlocks.getWrapEllipsisInfo(wrapInfoM888getWrapInfoOpUlnko2, intIntPairM305boximpl3 != null, i29, i30, i24, i8);
                mutableIntList2 = mutableIntList6;
                mutableIntList2.add(iMax);
                i10 = (i13 - i30) - iCeil2;
                MutableIntList mutableIntList7 = mutableIntList5;
                mutableIntList7.add(i25);
                i20 = i29 + 1;
                numValueOf3 = numValueOf4 != null ? Integer.valueOf(numValueOf4.intValue() - iCeil) : null;
                i14 = i22;
                i22 = i14;
                i18 = i25;
                i21 = i30 + iCeil2;
                i9 = 0;
                i15 = iMin;
                wrapEllipsisInfo2 = wrapEllipsisInfo4;
                mutableIntList = mutableIntList7;
                i23 = 0;
            } else {
                i15 = i26;
                mutableIntList = mutableIntList5;
                mutableIntList2 = mutableIntList6;
                numValueOf3 = numValueOf4;
                i9 = iMax;
                i14 = i24;
                i20 = i29;
                i10 = i13;
                wrapEllipsisInfo2 = wrapEllipsisInfo3;
            }
            wrapEllipsisInfo3 = wrapEllipsisInfo2;
            mutableIntList5 = mutableIntList;
            i16 = i9;
            i13 = i10;
            i17 = i25;
            arrayList3 = arrayList2;
            wrapInfo = wrapInfoM888getWrapInfoOpUlnko2;
            mutableIntList4 = mutableIntList2;
            mutableIntObjectMapMutableIntObjectMapOf = mutableIntObjectMap2;
            i19 = i23;
            i3 = i22;
        }
        MutableIntObjectMap mutableIntObjectMap3 = mutableIntObjectMapMutableIntObjectMapOf;
        ArrayList arrayList4 = arrayList3;
        MutableIntList mutableIntList8 = mutableIntList4;
        MutableIntList mutableIntList9 = mutableIntList5;
        if (wrapEllipsisInfo3 != null) {
            arrayList = arrayList4;
            arrayList.add(wrapEllipsisInfo3.getEllipsis());
            mutableIntObjectMap = mutableIntObjectMap3;
            mutableIntObjectMap.set(arrayList.size() - 1, wrapEllipsisInfo3.getPlaceable());
            int i31 = mutableIntList9._size - 1;
            if (wrapEllipsisInfo3.getPlaceEllipsisOnLastContentLine()) {
                int size = mutableIntList9.getSize() - 1;
                mutableIntList8.set(i31, Math.max(mutableIntList8.get(i31), IntIntPair.m313getSecondimpl(wrapEllipsisInfo3.getEllipsisSize())));
                mutableIntList9.set(size, mutableIntList9.last() + 1);
            } else {
                mutableIntList8.add(IntIntPair.m313getSecondimpl(wrapEllipsisInfo3.getEllipsisSize()));
                mutableIntList9.add(mutableIntList9.last() + 1);
            }
        } else {
            mutableIntObjectMap = mutableIntObjectMap3;
            arrayList = arrayList4;
        }
        int size2 = arrayList.size();
        int i32 = i15;
        Placeable[] placeableArr = new Placeable[size2];
        for (int i33 = 0; i33 < size2; i33++) {
            placeableArr[i33] = mutableIntObjectMap.get(i33);
        }
        int size3 = mutableIntList9.getSize();
        int[] iArr3 = new int[size3];
        for (int i34 = 0; i34 < size3; i34++) {
            iArr3[i34] = 0;
        }
        int size4 = mutableIntList9.getSize();
        int[] iArr4 = new int[size4];
        for (int i35 = 0; i35 < size4; i35++) {
            iArr4[i35] = 0;
        }
        MutableIntList mutableIntList10 = mutableIntList9;
        int[] iArr5 = mutableIntList10.content;
        int i36 = mutableIntList10._size;
        int iMax2 = i32;
        int i37 = 0;
        int i38 = 0;
        int i39 = 0;
        while (i38 < i36) {
            int i40 = iArr5[i38];
            MutableIntList mutableIntList11 = mutableIntList8;
            int i41 = i36;
            MeasureResult measureResultMeasure = RowColumnMeasurePolicyKt.measure(flowLineMeasurePolicy, iMax2, Constraints.m6981getMinHeightimpl(jM932constructorimpl), Constraints.m6980getMaxWidthimpl(jM932constructorimpl), mutableIntList11.get(i38), iCeil, measureScope3, arrayList, placeableArr, i37, i40, iArr3, i38);
            if (flowLineMeasurePolicy.isHorizontal()) {
                i6 = measureResultMeasure.getWidth();
                i7 = measureResultMeasure.getHeight();
            } else {
                i6 = measureResultMeasure.getHeight();
                i7 = measureResultMeasure.getWidth();
            }
            iArr4[i38] = i7;
            i39 += i7;
            iMax2 = Math.max(iMax2, i6);
            mutableVector.add(measureResultMeasure);
            i38++;
            i37 = i40;
            i36 = i41;
            mutableIntList8 = mutableIntList11;
            measureScope3 = measureScope;
        }
        if (mutableVector.isEmpty()) {
            i4 = 0;
            i5 = 0;
            measureScope2 = measureScope;
            j2 = j;
            iArr = iArr3;
            flowLineMeasurePolicy2 = flowLineMeasurePolicy;
            iArr2 = iArr4;
        } else {
            i4 = iMax2;
            i5 = i39;
            measureScope2 = measureScope;
            iArr = iArr3;
            flowLineMeasurePolicy2 = flowLineMeasurePolicy;
            iArr2 = iArr4;
            j2 = j;
        }
        return m892placeHelperBmaY500(measureScope2, j2, i4, i5, iArr2, mutableVector, flowLineMeasurePolicy2, iArr);
    }
}
