package androidx.compose.material3;

import androidx.compose.foundation.FocusableKt;
import androidx.compose.foundation.ProgressSemanticsKt;
import androidx.compose.foundation.gestures.DraggableKt;
import androidx.compose.foundation.gestures.ForEachGestureKt;
import androidx.compose.foundation.gestures.Orientation;
import androidx.compose.foundation.gestures.PressGestureScope;
import androidx.compose.foundation.gestures.TapGestureDetectorKt;
import androidx.compose.foundation.interaction.DragInteraction;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.material3.internal.AccessibilityUtilKt;
import androidx.compose.material3.internal.Strings;
import androidx.compose.material3.internal.Strings_androidKt;
import androidx.compose.material3.tokens.SliderTokens;
import androidx.compose.runtime.Applier;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.Updater;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.input.pointer.AwaitPointerEventScope;
import androidx.compose.ui.input.pointer.PointerInputScope;
import androidx.compose.ui.input.pointer.SuspendingPointerInputFilterKt;
import androidx.compose.ui.layout.LayoutIdKt;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.OnRemeasuredModifierKt;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.semantics.SemanticsModifierKt;
import androidx.compose.ui.semantics.SemanticsPropertiesKt;
import androidx.compose.ui.semantics.SemanticsPropertyReceiver;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.ConstraintsKt;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.DpKt;
import androidx.compose.ui.unit.IntSize;
import androidx.compose.ui.unit.LayoutDirection;
import androidx.compose.ui.util.MathHelpersKt;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.NotificationCompat;
import androidx.savedstate.serialization.ClassDiscriminatorModeKt;
import java.util.List;
import java.util.NoSuchElementException;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.RestrictedSuspendLambda;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.math.MathKt;
import kotlin.ranges.ClosedFloatingPointRange;
import kotlin.ranges.IntRange;
import kotlin.ranges.RangesKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import org.simpleframework.xml.strategy.Name;

/* JADX INFO: compiled from: Slider.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0098\u0001\n\u0000\n\u0002\u0010\u0006\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u001d\n\u0002\u0010\u0014\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\u001a\u0098\u0001\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00192\b\b\u0002\u0010\u001a\u001a\u00020\u001b2\b\b\u0002\u0010\u001c\u001a\u00020\u00102\b\b\u0002\u0010\u001d\u001a\u00020\u001e2\b\b\u0002\u0010\u001f\u001a\u00020 2\b\b\u0002\u0010!\u001a\u00020 2\u0019\b\u0002\u0010\"\u001a\u0013\u0012\u0004\u0012\u00020\u0019\u0012\u0004\u0012\u00020\u00170#¢\u0006\u0002\b$2\u0019\b\u0002\u0010%\u001a\u0013\u0012\u0004\u0012\u00020\u0019\u0012\u0004\u0012\u00020\u00170#¢\u0006\u0002\b$2\u0019\b\u0002\u0010&\u001a\u0013\u0012\u0004\u0012\u00020\u0019\u0012\u0004\u0012\u00020\u00170#¢\u0006\u0002\b$H\u0007¢\u0006\u0002\u0010'\u001aä\u0001\u0010\u0016\u001a\u00020\u00172\f\u0010(\u001a\b\u0012\u0004\u0012\u00020*0)2\u0018\u0010+\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020*0)\u0012\u0004\u0012\u00020\u00170#2\b\b\u0002\u0010\u001a\u001a\u00020\u001b2\b\b\u0002\u0010\u001c\u001a\u00020\u00102\u000e\b\u0002\u0010,\u001a\b\u0012\u0004\u0012\u00020*0)2\u0010\b\u0002\u0010-\u001a\n\u0012\u0004\u0012\u00020\u0017\u0018\u00010.2\b\b\u0002\u0010\u001d\u001a\u00020\u001e2\b\b\u0002\u0010\u001f\u001a\u00020 2\b\b\u0002\u0010!\u001a\u00020 2\u0019\b\u0002\u0010\"\u001a\u0013\u0012\u0004\u0012\u00020\u0019\u0012\u0004\u0012\u00020\u00170#¢\u0006\u0002\b$2\u0019\b\u0002\u0010%\u001a\u0013\u0012\u0004\u0012\u00020\u0019\u0012\u0004\u0012\u00020\u00170#¢\u0006\u0002\b$2\u0019\b\u0002\u0010&\u001a\u0013\u0012\u0004\u0012\u00020\u0019\u0012\u0004\u0012\u00020\u00170#¢\u0006\u0002\b$2\b\b\u0003\u0010/\u001a\u000200H\u0007¢\u0006\u0002\u00101\u001a\u007f\u0010\u0016\u001a\u00020\u00172\f\u0010(\u001a\b\u0012\u0004\u0012\u00020*0)2\u0018\u0010+\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020*0)\u0012\u0004\u0012\u00020\u00170#2\b\b\u0002\u0010\u001a\u001a\u00020\u001b2\b\b\u0002\u0010\u001c\u001a\u00020\u00102\u000e\b\u0002\u0010,\u001a\b\u0012\u0004\u0012\u00020*0)2\b\b\u0003\u0010/\u001a\u0002002\u0010\b\u0002\u0010-\u001a\n\u0012\u0004\u0012\u00020\u0017\u0018\u00010.2\b\b\u0002\u0010\u001d\u001a\u00020\u001eH\u0007¢\u0006\u0002\u00102\u001a\u0080\u0001\u00103\u001a\u00020\u00172\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001c\u001a\u00020\u00102\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020 2\u0017\u0010\"\u001a\u0013\u0012\u0004\u0012\u00020\u0019\u0012\u0004\u0012\u00020\u00170#¢\u0006\u0002\b$2\u0017\u0010%\u001a\u0013\u0012\u0004\u0012\u00020\u0019\u0012\u0004\u0012\u00020\u00170#¢\u0006\u0002\b$2\u0017\u0010&\u001a\u0013\u0012\u0004\u0012\u00020\u0019\u0012\u0004\u0012\u00020\u00170#¢\u0006\u0002\b$H\u0003¢\u0006\u0002\u00104\u001as\u00105\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u0002062\b\b\u0002\u0010\u001a\u001a\u00020\u001b2\b\b\u0002\u0010\u001c\u001a\u00020\u00102\b\b\u0002\u0010\u001d\u001a\u00020\u001e2\b\b\u0002\u00107\u001a\u00020 2\u0019\b\u0002\u00108\u001a\u0013\u0012\u0004\u0012\u000206\u0012\u0004\u0012\u00020\u00170#¢\u0006\u0002\b$2\u0019\b\u0002\u0010&\u001a\u0013\u0012\u0004\u0012\u000206\u0012\u0004\u0012\u00020\u00170#¢\u0006\u0002\b$H\u0007¢\u0006\u0002\u00109\u001a³\u0001\u00105\u001a\u00020\u00172\u0006\u0010(\u001a\u00020*2\u0012\u0010+\u001a\u000e\u0012\u0004\u0012\u00020*\u0012\u0004\u0012\u00020\u00170#2\b\b\u0002\u0010\u001a\u001a\u00020\u001b2\b\b\u0002\u0010\u001c\u001a\u00020\u00102\u0010\b\u0002\u0010-\u001a\n\u0012\u0004\u0012\u00020\u0017\u0018\u00010.2\b\b\u0002\u0010\u001d\u001a\u00020\u001e2\b\b\u0002\u00107\u001a\u00020 2\b\b\u0003\u0010/\u001a\u0002002\u0019\b\u0002\u00108\u001a\u0013\u0012\u0004\u0012\u000206\u0012\u0004\u0012\u00020\u00170#¢\u0006\u0002\b$2\u0019\b\u0002\u0010&\u001a\u0013\u0012\u0004\u0012\u000206\u0012\u0004\u0012\u00020\u00170#¢\u0006\u0002\b$2\u000e\b\u0002\u0010,\u001a\b\u0012\u0004\u0012\u00020*0)H\u0007¢\u0006\u0002\u0010:\u001a}\u00105\u001a\u00020\u00172\u0006\u0010(\u001a\u00020*2\u0012\u0010+\u001a\u000e\u0012\u0004\u0012\u00020*\u0012\u0004\u0012\u00020\u00170#2\b\b\u0002\u0010\u001a\u001a\u00020\u001b2\b\b\u0002\u0010\u001c\u001a\u00020\u00102\u000e\b\u0002\u0010,\u001a\b\u0012\u0004\u0012\u00020*0)2\b\b\u0003\u0010/\u001a\u0002002\u0010\b\u0002\u0010-\u001a\n\u0012\u0004\u0012\u00020\u0017\u0018\u00010.2\b\b\u0002\u0010\u001d\u001a\u00020\u001e2\b\b\u0002\u00107\u001a\u00020 H\u0007¢\u0006\u0002\u0010;\u001a_\u0010<\u001a\u00020\u00172\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u0018\u001a\u0002062\u0006\u0010\u001c\u001a\u00020\u00102\u0006\u00107\u001a\u00020 2\u0017\u00108\u001a\u0013\u0012\u0004\u0012\u000206\u0012\u0004\u0012\u00020\u00170#¢\u0006\u0002\b$2\u0017\u0010&\u001a\u0013\u0012\u0004\u0012\u000206\u0012\u0004\u0012\u00020\u00170#¢\u0006\u0002\b$H\u0003¢\u0006\u0002\u0010=\u001a\u001d\u0010>\u001a\u00020\u00112\u0006\u0010?\u001a\u00020*2\u0006\u0010@\u001a\u00020*H\u0001¢\u0006\u0002\u0010A\u001a\u001b\u0010>\u001a\u00020\u00112\f\u0010B\u001a\b\u0012\u0004\u0012\u00020*0)H\u0001¢\u0006\u0002\u0010C\u001a \u0010D\u001a\u00020*2\u0006\u0010E\u001a\u00020*2\u0006\u0010F\u001a\u00020*2\u0006\u0010G\u001a\u00020*H\u0002\u001a:\u0010H\u001a\u00020\u00112\u0006\u0010I\u001a\u00020*2\u0006\u0010J\u001a\u00020*2\u0006\u0010K\u001a\u00020\u00112\u0006\u0010L\u001a\u00020*2\u0006\u0010M\u001a\u00020*H\u0002ø\u0001\u0000¢\u0006\u0004\bN\u0010O\u001a0\u0010H\u001a\u00020*2\u0006\u0010I\u001a\u00020*2\u0006\u0010J\u001a\u00020*2\u0006\u0010P\u001a\u00020*2\u0006\u0010L\u001a\u00020*2\u0006\u0010M\u001a\u00020*H\u0002\u001a(\u0010Q\u001a\u00020*2\u0006\u0010R\u001a\u00020*2\u0006\u0010S\u001a\u00020T2\u0006\u0010U\u001a\u00020*2\u0006\u0010V\u001a\u00020*H\u0002\u001a\u0010\u0010W\u001a\u00020T2\u0006\u0010/\u001a\u000200H\u0002\u001a5\u0010X\u001a\u0010\u0012\u0004\u0012\u00020Z\u0012\u0004\u0012\u00020*\u0018\u00010Y*\u00020[2\u0006\u0010\\\u001a\u00020]2\u0006\u0010^\u001a\u00020_H\u0082@ø\u0001\u0000¢\u0006\u0004\b`\u0010a\u001a\u001c\u0010b\u001a\u00020\u001b*\u00020\u001b2\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001c\u001a\u00020\u0010H\u0002\u001a,\u0010c\u001a\u00020\u001b*\u00020\u001b2\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020 2\u0006\u0010\u001c\u001a\u00020\u0010H\u0003\u001a\u001c\u0010d\u001a\u00020\u001b*\u00020\u001b2\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001c\u001a\u00020\u0010H\u0002\u001a\u001c\u0010e\u001a\u00020\u001b*\u00020\u001b2\u0006\u0010\u0018\u001a\u0002062\u0006\u0010\u001c\u001a\u00020\u0010H\u0002\u001a$\u0010f\u001a\u00020\u001b*\u00020\u001b2\u0006\u0010\u0018\u001a\u0002062\u0006\u00107\u001a\u00020 2\u0006\u0010\u001c\u001a\u00020\u0010H\u0003\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u0010\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0004\"\u0010\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0007\"\u0010\u0010\b\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0004\"\u0016\u0010\t\u001a\u00020\u0003X\u0080\u0004¢\u0006\n\n\u0002\u0010\u0004\u001a\u0004\b\n\u0010\u000b\"\u0016\u0010\f\u001a\u00020\u0003X\u0080\u0004¢\u0006\n\n\u0002\u0010\u0004\u001a\u0004\b\r\u0010\u000b\"\u0010\u0010\u000e\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0004\"\u001e\u0010\u000f\u001a\u00020\u0010*\u00020\u00118@X\u0081\u0004¢\u0006\f\u0012\u0004\b\u0012\u0010\u0013\u001a\u0004\b\u0014\u0010\u0015\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006g"}, d2 = {"SliderRangeTolerance", "", "ThumbHeight", "Landroidx/compose/ui/unit/Dp;", "F", "ThumbSize", "Landroidx/compose/ui/unit/DpSize;", "J", "ThumbTrackGapSize", "ThumbWidth", "getThumbWidth", "()F", "TrackHeight", "getTrackHeight", "TrackInsideCornerSize", "isSpecified", "", "Landroidx/compose/material3/SliderRange;", "isSpecified-If1S1O4$annotations", "(J)V", "isSpecified-If1S1O4", "(J)Z", "RangeSlider", "", "state", "Landroidx/compose/material3/RangeSliderState;", "modifier", "Landroidx/compose/ui/Modifier;", "enabled", "colors", "Landroidx/compose/material3/SliderColors;", "startInteractionSource", "Landroidx/compose/foundation/interaction/MutableInteractionSource;", "endInteractionSource", "startThumb", "Lkotlin/Function1;", "Landroidx/compose/runtime/Composable;", "endThumb", "track", "(Landroidx/compose/material3/RangeSliderState;Landroidx/compose/ui/Modifier;ZLandroidx/compose/material3/SliderColors;Landroidx/compose/foundation/interaction/MutableInteractionSource;Landroidx/compose/foundation/interaction/MutableInteractionSource;Lkotlin/jvm/functions/Function3;Lkotlin/jvm/functions/Function3;Lkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;II)V", "value", "Lkotlin/ranges/ClosedFloatingPointRange;", "", "onValueChange", "valueRange", "onValueChangeFinished", "Lkotlin/Function0;", "steps", "", "(Lkotlin/ranges/ClosedFloatingPointRange;Lkotlin/jvm/functions/Function1;Landroidx/compose/ui/Modifier;ZLkotlin/ranges/ClosedFloatingPointRange;Lkotlin/jvm/functions/Function0;Landroidx/compose/material3/SliderColors;Landroidx/compose/foundation/interaction/MutableInteractionSource;Landroidx/compose/foundation/interaction/MutableInteractionSource;Lkotlin/jvm/functions/Function3;Lkotlin/jvm/functions/Function3;Lkotlin/jvm/functions/Function3;ILandroidx/compose/runtime/Composer;III)V", "(Lkotlin/ranges/ClosedFloatingPointRange;Lkotlin/jvm/functions/Function1;Landroidx/compose/ui/Modifier;ZLkotlin/ranges/ClosedFloatingPointRange;ILkotlin/jvm/functions/Function0;Landroidx/compose/material3/SliderColors;Landroidx/compose/runtime/Composer;II)V", "RangeSliderImpl", "(Landroidx/compose/ui/Modifier;Landroidx/compose/material3/RangeSliderState;ZLandroidx/compose/foundation/interaction/MutableInteractionSource;Landroidx/compose/foundation/interaction/MutableInteractionSource;Lkotlin/jvm/functions/Function3;Lkotlin/jvm/functions/Function3;Lkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;I)V", "Slider", "Landroidx/compose/material3/SliderState;", "interactionSource", "thumb", "(Landroidx/compose/material3/SliderState;Landroidx/compose/ui/Modifier;ZLandroidx/compose/material3/SliderColors;Landroidx/compose/foundation/interaction/MutableInteractionSource;Lkotlin/jvm/functions/Function3;Lkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;II)V", "(FLkotlin/jvm/functions/Function1;Landroidx/compose/ui/Modifier;ZLkotlin/jvm/functions/Function0;Landroidx/compose/material3/SliderColors;Landroidx/compose/foundation/interaction/MutableInteractionSource;ILkotlin/jvm/functions/Function3;Lkotlin/jvm/functions/Function3;Lkotlin/ranges/ClosedFloatingPointRange;Landroidx/compose/runtime/Composer;III)V", "(FLkotlin/jvm/functions/Function1;Landroidx/compose/ui/Modifier;ZLkotlin/ranges/ClosedFloatingPointRange;ILkotlin/jvm/functions/Function0;Landroidx/compose/material3/SliderColors;Landroidx/compose/foundation/interaction/MutableInteractionSource;Landroidx/compose/runtime/Composer;II)V", "SliderImpl", "(Landroidx/compose/ui/Modifier;Landroidx/compose/material3/SliderState;ZLandroidx/compose/foundation/interaction/MutableInteractionSource;Lkotlin/jvm/functions/Function3;Lkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;I)V", "SliderRange", "start", "endInclusive", "(FF)J", "range", "(Lkotlin/ranges/ClosedFloatingPointRange;)J", "calcFraction", "a", "b", "pos", "scale", "a1", "b1", "x", "a2", "b2", "scale-ziovWd0", "(FFJFF)J", "x1", "snapValueToTick", "current", "tickFractions", "", "minPx", "maxPx", "stepsToTickFractions", "awaitSlop", "Lkotlin/Pair;", "Landroidx/compose/ui/input/pointer/PointerInputChange;", "Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;", Name.MARK, "Landroidx/compose/ui/input/pointer/PointerId;", ClassDiscriminatorModeKt.CLASS_DISCRIMINATOR_KEY, "Landroidx/compose/ui/input/pointer/PointerType;", "awaitSlop-8vUncbI", "(Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;JILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "rangeSliderEndThumbSemantics", "rangeSliderPressDragModifier", "rangeSliderStartThumbSemantics", "sliderSemantics", "sliderTapModifier", "material3_release"}, k = 2, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
public final class SliderKt {
    private static final double SliderRangeTolerance = 1.0E-4d;
    private static final float ThumbHeight;
    private static final long ThumbSize;
    private static final float ThumbTrackGapSize;
    private static final float ThumbWidth;
    private static final float TrackHeight = SliderTokens.INSTANCE.m3800getInactiveTrackHeightD9Ej5fM();
    private static final float TrackInsideCornerSize;

    /* JADX INFO: renamed from: isSpecified-If1S1O4$annotations, reason: not valid java name */
    public static /* synthetic */ void m2809isSpecifiedIf1S1O4$annotations(long j) {
    }

    /* JADX WARN: Removed duplicated region for block: B:102:0x0121  */
    /* JADX WARN: Removed duplicated region for block: B:106:0x0138  */
    /* JADX WARN: Removed duplicated region for block: B:120:0x0171  */
    /* JADX WARN: Removed duplicated region for block: B:121:0x0173  */
    /* JADX WARN: Removed duplicated region for block: B:123:0x017a  */
    /* JADX WARN: Removed duplicated region for block: B:126:0x017f  */
    /* JADX WARN: Removed duplicated region for block: B:127:0x018a  */
    /* JADX WARN: Removed duplicated region for block: B:129:0x018e  */
    /* JADX WARN: Removed duplicated region for block: B:131:0x0192  */
    /* JADX WARN: Removed duplicated region for block: B:134:0x0198  */
    /* JADX WARN: Removed duplicated region for block: B:135:0x01a2  */
    /* JADX WARN: Removed duplicated region for block: B:137:0x01a6  */
    /* JADX WARN: Removed duplicated region for block: B:141:0x01c7  */
    /* JADX WARN: Removed duplicated region for block: B:145:0x01dd  */
    /* JADX WARN: Removed duplicated region for block: B:148:0x023e  */
    /* JADX WARN: Removed duplicated region for block: B:152:0x0253  */
    /* JADX WARN: Removed duplicated region for block: B:154:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0068  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x006b  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x0084  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x0099  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x00a1  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x00a3  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x00bd  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x00bf  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x00da  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x00f0  */
    /* JADX WARN: Removed duplicated region for block: B:91:0x00f8  */
    /* JADX WARN: Removed duplicated region for block: B:92:0x00fb  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final void Slider(final float r27, final kotlin.jvm.functions.Function1<? super java.lang.Float, kotlin.Unit> r28, androidx.compose.ui.Modifier r29, boolean r30, kotlin.ranges.ClosedFloatingPointRange<java.lang.Float> r31, int r32, kotlin.jvm.functions.Function0<kotlin.Unit> r33, androidx.compose.material3.SliderColors r34, androidx.compose.foundation.interaction.MutableInteractionSource r35, androidx.compose.runtime.Composer r36, final int r37, final int r38) {
        /*
            Method dump skipped, instruction units count: 610
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.SliderKt.Slider(float, kotlin.jvm.functions.Function1, androidx.compose.ui.Modifier, boolean, kotlin.ranges.ClosedFloatingPointRange, int, kotlin.jvm.functions.Function0, androidx.compose.material3.SliderColors, androidx.compose.foundation.interaction.MutableInteractionSource, androidx.compose.runtime.Composer, int, int):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:100:0x011c  */
    /* JADX WARN: Removed duplicated region for block: B:101:0x011f  */
    /* JADX WARN: Removed duplicated region for block: B:111:0x013d  */
    /* JADX WARN: Removed duplicated region for block: B:119:0x0153  */
    /* JADX WARN: Removed duplicated region for block: B:122:0x0163  */
    /* JADX WARN: Removed duplicated region for block: B:129:0x0181  */
    /* JADX WARN: Removed duplicated region for block: B:141:0x01b2  */
    /* JADX WARN: Removed duplicated region for block: B:142:0x01b4  */
    /* JADX WARN: Removed duplicated region for block: B:144:0x01bb  */
    /* JADX WARN: Removed duplicated region for block: B:146:0x01be  */
    /* JADX WARN: Removed duplicated region for block: B:149:0x01c4  */
    /* JADX WARN: Removed duplicated region for block: B:150:0x01cf  */
    /* JADX WARN: Removed duplicated region for block: B:152:0x01d3  */
    /* JADX WARN: Removed duplicated region for block: B:157:0x01f6  */
    /* JADX WARN: Removed duplicated region for block: B:159:0x01fa  */
    /* JADX WARN: Removed duplicated region for block: B:160:0x020b  */
    /* JADX WARN: Removed duplicated region for block: B:162:0x020f  */
    /* JADX WARN: Removed duplicated region for block: B:163:0x0222  */
    /* JADX WARN: Removed duplicated region for block: B:166:0x022a  */
    /* JADX WARN: Removed duplicated region for block: B:167:0x0234  */
    /* JADX WARN: Removed duplicated region for block: B:171:0x0247  */
    /* JADX WARN: Removed duplicated region for block: B:172:0x0252  */
    /* JADX WARN: Removed duplicated region for block: B:175:0x0263  */
    /* JADX WARN: Removed duplicated region for block: B:176:0x0265  */
    /* JADX WARN: Removed duplicated region for block: B:186:0x0286  */
    /* JADX WARN: Removed duplicated region for block: B:188:0x028e  */
    /* JADX WARN: Removed duplicated region for block: B:191:0x02de  */
    /* JADX WARN: Removed duplicated region for block: B:195:0x02f1  */
    /* JADX WARN: Removed duplicated region for block: B:197:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0064  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0067  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x0080  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x0083  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x009d  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x00b3  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x00bb  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x00c0  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x00d9  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x00de  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x00f9  */
    /* JADX WARN: Removed duplicated region for block: B:90:0x00fc  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final void Slider(final float r27, final kotlin.jvm.functions.Function1<? super java.lang.Float, kotlin.Unit> r28, androidx.compose.ui.Modifier r29, boolean r30, kotlin.jvm.functions.Function0<kotlin.Unit> r31, androidx.compose.material3.SliderColors r32, androidx.compose.foundation.interaction.MutableInteractionSource r33, int r34, kotlin.jvm.functions.Function3<? super androidx.compose.material3.SliderState, ? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r35, kotlin.jvm.functions.Function3<? super androidx.compose.material3.SliderState, ? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r36, kotlin.ranges.ClosedFloatingPointRange<java.lang.Float> r37, androidx.compose.runtime.Composer r38, final int r39, final int r40, final int r41) {
        /*
            Method dump skipped, instruction units count: 766
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.SliderKt.Slider(float, kotlin.jvm.functions.Function1, androidx.compose.ui.Modifier, boolean, kotlin.jvm.functions.Function0, androidx.compose.material3.SliderColors, androidx.compose.foundation.interaction.MutableInteractionSource, int, kotlin.jvm.functions.Function3, kotlin.jvm.functions.Function3, kotlin.ranges.ClosedFloatingPointRange, androidx.compose.runtime.Composer, int, int, int):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:100:0x0119  */
    /* JADX WARN: Removed duplicated region for block: B:103:0x011e  */
    /* JADX WARN: Removed duplicated region for block: B:105:0x012a  */
    /* JADX WARN: Removed duplicated region for block: B:111:0x014f  */
    /* JADX WARN: Removed duplicated region for block: B:113:0x0160  */
    /* JADX WARN: Removed duplicated region for block: B:117:0x017c  */
    /* JADX WARN: Removed duplicated region for block: B:120:0x018b  */
    /* JADX WARN: Removed duplicated region for block: B:126:0x01bd  */
    /* JADX WARN: Removed duplicated region for block: B:128:0x01cc  */
    /* JADX WARN: Removed duplicated region for block: B:130:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:26:0x004b  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x004e  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0067  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x007c  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x0082  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x0085  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x00a0  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x00a2  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x00bc  */
    /* JADX WARN: Removed duplicated region for block: B:71:0x00be  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x00df  */
    /* JADX WARN: Removed duplicated region for block: B:86:0x00f1  */
    /* JADX WARN: Removed duplicated region for block: B:95:0x010e  */
    /* JADX WARN: Removed duplicated region for block: B:96:0x0110  */
    /* JADX WARN: Removed duplicated region for block: B:97:0x0115  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final void Slider(final androidx.compose.material3.SliderState r17, androidx.compose.ui.Modifier r18, boolean r19, androidx.compose.material3.SliderColors r20, androidx.compose.foundation.interaction.MutableInteractionSource r21, kotlin.jvm.functions.Function3<? super androidx.compose.material3.SliderState, ? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r22, kotlin.jvm.functions.Function3<? super androidx.compose.material3.SliderState, ? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r23, androidx.compose.runtime.Composer r24, final int r25, final int r26) {
        /*
            Method dump skipped, instruction units count: 473
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.SliderKt.Slider(androidx.compose.material3.SliderState, androidx.compose.ui.Modifier, boolean, androidx.compose.material3.SliderColors, androidx.compose.foundation.interaction.MutableInteractionSource, kotlin.jvm.functions.Function3, kotlin.jvm.functions.Function3, androidx.compose.runtime.Composer, int, int):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:109:0x0147  */
    /* JADX WARN: Removed duplicated region for block: B:110:0x0149  */
    /* JADX WARN: Removed duplicated region for block: B:111:0x014e  */
    /* JADX WARN: Removed duplicated region for block: B:113:0x0151  */
    /* JADX WARN: Removed duplicated region for block: B:116:0x0156  */
    /* JADX WARN: Removed duplicated region for block: B:117:0x0161  */
    /* JADX WARN: Removed duplicated region for block: B:119:0x0165  */
    /* JADX WARN: Removed duplicated region for block: B:121:0x0169  */
    /* JADX WARN: Removed duplicated region for block: B:124:0x016f  */
    /* JADX WARN: Removed duplicated region for block: B:125:0x0179  */
    /* JADX WARN: Removed duplicated region for block: B:129:0x018c  */
    /* JADX WARN: Removed duplicated region for block: B:132:0x01a6  */
    /* JADX WARN: Removed duplicated region for block: B:135:0x01c4  */
    /* JADX WARN: Removed duplicated region for block: B:138:0x0235  */
    /* JADX WARN: Removed duplicated region for block: B:142:0x0246  */
    /* JADX WARN: Removed duplicated region for block: B:144:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0068  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x006b  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x0084  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x0099  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x00a1  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x00a3  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x00bd  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x00bf  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x00da  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x00f0  */
    /* JADX WARN: Removed duplicated region for block: B:91:0x00fe  */
    /* JADX WARN: Removed duplicated region for block: B:95:0x0113  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final void RangeSlider(final kotlin.ranges.ClosedFloatingPointRange<java.lang.Float> r28, final kotlin.jvm.functions.Function1<? super kotlin.ranges.ClosedFloatingPointRange<java.lang.Float>, kotlin.Unit> r29, androidx.compose.ui.Modifier r30, boolean r31, kotlin.ranges.ClosedFloatingPointRange<java.lang.Float> r32, int r33, kotlin.jvm.functions.Function0<kotlin.Unit> r34, androidx.compose.material3.SliderColors r35, androidx.compose.runtime.Composer r36, final int r37, final int r38) {
        /*
            Method dump skipped, instruction units count: 597
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.SliderKt.RangeSlider(kotlin.ranges.ClosedFloatingPointRange, kotlin.jvm.functions.Function1, androidx.compose.ui.Modifier, boolean, kotlin.ranges.ClosedFloatingPointRange, int, kotlin.jvm.functions.Function0, androidx.compose.material3.SliderColors, androidx.compose.runtime.Composer, int, int):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:100:0x0125  */
    /* JADX WARN: Removed duplicated region for block: B:110:0x0143  */
    /* JADX WARN: Removed duplicated region for block: B:111:0x014a  */
    /* JADX WARN: Removed duplicated region for block: B:121:0x016a  */
    /* JADX WARN: Removed duplicated region for block: B:122:0x016f  */
    /* JADX WARN: Removed duplicated region for block: B:132:0x018f  */
    /* JADX WARN: Removed duplicated region for block: B:133:0x0192  */
    /* JADX WARN: Removed duplicated region for block: B:143:0x01b4  */
    /* JADX WARN: Removed duplicated region for block: B:149:0x01d7  */
    /* JADX WARN: Removed duplicated region for block: B:161:0x020a  */
    /* JADX WARN: Removed duplicated region for block: B:162:0x020c  */
    /* JADX WARN: Removed duplicated region for block: B:163:0x0211  */
    /* JADX WARN: Removed duplicated region for block: B:165:0x0214  */
    /* JADX WARN: Removed duplicated region for block: B:168:0x0219  */
    /* JADX WARN: Removed duplicated region for block: B:170:0x0226  */
    /* JADX WARN: Removed duplicated region for block: B:173:0x022b  */
    /* JADX WARN: Removed duplicated region for block: B:175:0x0238  */
    /* JADX WARN: Removed duplicated region for block: B:179:0x0257  */
    /* JADX WARN: Removed duplicated region for block: B:181:0x025a  */
    /* JADX WARN: Removed duplicated region for block: B:185:0x0279  */
    /* JADX WARN: Removed duplicated region for block: B:188:0x027f  */
    /* JADX WARN: Removed duplicated region for block: B:189:0x0293  */
    /* JADX WARN: Removed duplicated region for block: B:191:0x029c  */
    /* JADX WARN: Removed duplicated region for block: B:192:0x02ad  */
    /* JADX WARN: Removed duplicated region for block: B:194:0x02b3  */
    /* JADX WARN: Removed duplicated region for block: B:195:0x02c4  */
    /* JADX WARN: Removed duplicated region for block: B:198:0x02d0  */
    /* JADX WARN: Removed duplicated region for block: B:199:0x02d5  */
    /* JADX WARN: Removed duplicated region for block: B:203:0x02e6  */
    /* JADX WARN: Removed duplicated region for block: B:204:0x02f1  */
    /* JADX WARN: Removed duplicated region for block: B:207:0x02ff  */
    /* JADX WARN: Removed duplicated region for block: B:208:0x0301  */
    /* JADX WARN: Removed duplicated region for block: B:219:0x0323  */
    /* JADX WARN: Removed duplicated region for block: B:223:0x032f  */
    /* JADX WARN: Removed duplicated region for block: B:226:0x0371  */
    /* JADX WARN: Removed duplicated region for block: B:227:0x0373  */
    /* JADX WARN: Removed duplicated region for block: B:232:0x0382  */
    /* JADX WARN: Removed duplicated region for block: B:235:0x03f7  */
    /* JADX WARN: Removed duplicated region for block: B:239:0x040c  */
    /* JADX WARN: Removed duplicated region for block: B:241:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:36:0x006c  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x006f  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x0089  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x009f  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x00a7  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x00ac  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x00c5  */
    /* JADX WARN: Removed duplicated region for block: B:75:0x00d9  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x00e1  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x00e6  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x00ff  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x0102  */
    /* JADX WARN: Removed duplicated region for block: B:99:0x0122  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final void RangeSlider(final kotlin.ranges.ClosedFloatingPointRange<java.lang.Float> r31, final kotlin.jvm.functions.Function1<? super kotlin.ranges.ClosedFloatingPointRange<java.lang.Float>, kotlin.Unit> r32, androidx.compose.ui.Modifier r33, boolean r34, kotlin.ranges.ClosedFloatingPointRange<java.lang.Float> r35, kotlin.jvm.functions.Function0<kotlin.Unit> r36, androidx.compose.material3.SliderColors r37, androidx.compose.foundation.interaction.MutableInteractionSource r38, androidx.compose.foundation.interaction.MutableInteractionSource r39, kotlin.jvm.functions.Function3<? super androidx.compose.material3.RangeSliderState, ? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r40, kotlin.jvm.functions.Function3<? super androidx.compose.material3.RangeSliderState, ? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r41, kotlin.jvm.functions.Function3<? super androidx.compose.material3.RangeSliderState, ? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r42, int r43, androidx.compose.runtime.Composer r44, final int r45, final int r46, final int r47) {
        /*
            Method dump skipped, instruction units count: 1056
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.SliderKt.RangeSlider(kotlin.ranges.ClosedFloatingPointRange, kotlin.jvm.functions.Function1, androidx.compose.ui.Modifier, boolean, kotlin.ranges.ClosedFloatingPointRange, kotlin.jvm.functions.Function0, androidx.compose.material3.SliderColors, androidx.compose.foundation.interaction.MutableInteractionSource, androidx.compose.foundation.interaction.MutableInteractionSource, kotlin.jvm.functions.Function3, kotlin.jvm.functions.Function3, kotlin.jvm.functions.Function3, int, androidx.compose.runtime.Composer, int, int, int):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:102:0x0128  */
    /* JADX WARN: Removed duplicated region for block: B:106:0x0141  */
    /* JADX WARN: Removed duplicated region for block: B:116:0x0168  */
    /* JADX WARN: Removed duplicated region for block: B:117:0x016a  */
    /* JADX WARN: Removed duplicated region for block: B:120:0x0172  */
    /* JADX WARN: Removed duplicated region for block: B:123:0x0177  */
    /* JADX WARN: Removed duplicated region for block: B:126:0x0185  */
    /* JADX WARN: Removed duplicated region for block: B:130:0x01a4  */
    /* JADX WARN: Removed duplicated region for block: B:132:0x01a7  */
    /* JADX WARN: Removed duplicated region for block: B:138:0x01ca  */
    /* JADX WARN: Removed duplicated region for block: B:139:0x01d9  */
    /* JADX WARN: Removed duplicated region for block: B:141:0x01dd  */
    /* JADX WARN: Removed duplicated region for block: B:142:0x01ec  */
    /* JADX WARN: Removed duplicated region for block: B:144:0x01f0  */
    /* JADX WARN: Removed duplicated region for block: B:145:0x0201  */
    /* JADX WARN: Removed duplicated region for block: B:149:0x0214  */
    /* JADX WARN: Removed duplicated region for block: B:152:0x0220  */
    /* JADX WARN: Removed duplicated region for block: B:158:0x0261  */
    /* JADX WARN: Removed duplicated region for block: B:160:0x026e  */
    /* JADX WARN: Removed duplicated region for block: B:162:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:26:0x004d  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0050  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0069  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x007e  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x0084  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x0087  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x00a2  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x00a4  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x00be  */
    /* JADX WARN: Removed duplicated region for block: B:71:0x00c3  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x00dc  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x00df  */
    /* JADX WARN: Removed duplicated region for block: B:91:0x00ff  */
    /* JADX WARN: Removed duplicated region for block: B:92:0x0102  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final void RangeSlider(final androidx.compose.material3.RangeSliderState r22, androidx.compose.ui.Modifier r23, boolean r24, androidx.compose.material3.SliderColors r25, androidx.compose.foundation.interaction.MutableInteractionSource r26, androidx.compose.foundation.interaction.MutableInteractionSource r27, kotlin.jvm.functions.Function3<? super androidx.compose.material3.RangeSliderState, ? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r28, kotlin.jvm.functions.Function3<? super androidx.compose.material3.RangeSliderState, ? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r29, kotlin.jvm.functions.Function3<? super androidx.compose.material3.RangeSliderState, ? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r30, androidx.compose.runtime.Composer r31, final int r32, final int r33) {
        /*
            Method dump skipped, instruction units count: 635
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.SliderKt.RangeSlider(androidx.compose.material3.RangeSliderState, androidx.compose.ui.Modifier, boolean, androidx.compose.material3.SliderColors, androidx.compose.foundation.interaction.MutableInteractionSource, androidx.compose.foundation.interaction.MutableInteractionSource, kotlin.jvm.functions.Function3, kotlin.jvm.functions.Function3, kotlin.jvm.functions.Function3, androidx.compose.runtime.Composer, int, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void SliderImpl(final Modifier modifier, final SliderState sliderState, boolean z, MutableInteractionSource mutableInteractionSource, Function3<? super SliderState, ? super Composer, ? super Integer, Unit> function3, Function3<? super SliderState, ? super Composer, ? super Integer, Unit> function32, Composer composer, final int i) {
        int i2;
        boolean z2;
        Function3<? super SliderState, ? super Composer, ? super Integer, Unit> function33;
        final MutableInteractionSource mutableInteractionSource2 = mutableInteractionSource;
        Function3<? super SliderState, ? super Composer, ? super Integer, Unit> function34 = function32;
        Composer composerStartRestartGroup = composer.startRestartGroup(1390990089);
        ComposerKt.sourceInformation(composerStartRestartGroup, "C(SliderImpl)P(2,3)665@29717L7,673@30073L28,698@30935L1093,678@30197L1831:Slider.kt#uh7d8r");
        if ((i & 6) == 0) {
            i2 = (composerStartRestartGroup.changed(modifier) ? 4 : 2) | i;
        } else {
            i2 = i;
        }
        if ((i & 48) == 0) {
            i2 |= composerStartRestartGroup.changedInstance(sliderState) ? 32 : 16;
        }
        if ((i & 384) == 0) {
            i2 |= composerStartRestartGroup.changed(z) ? 256 : 128;
        }
        if ((i & 3072) == 0) {
            i2 |= composerStartRestartGroup.changed(mutableInteractionSource2) ? 2048 : 1024;
        }
        if ((i & 24576) == 0) {
            i2 |= composerStartRestartGroup.changedInstance(function3) ? 16384 : 8192;
        }
        if ((196608 & i) == 0) {
            i2 |= composerStartRestartGroup.changedInstance(function34) ? 131072 : 65536;
        }
        if ((74899 & i2) != 74898 || !composerStartRestartGroup.getSkipping()) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1390990089, i2, -1, "androidx.compose.material3.SliderImpl (Slider.kt:664)");
            }
            ProvidableCompositionLocal<LayoutDirection> localLayoutDirection = CompositionLocalsKt.getLocalLayoutDirection();
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object objConsume = composerStartRestartGroup.consume(localLayoutDirection);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            sliderState.setRtl$material3_release(objConsume == LayoutDirection.Rtl);
            Modifier modifierSliderTapModifier = sliderTapModifier(Modifier.INSTANCE, sliderState, mutableInteractionSource2, z);
            Modifier.Companion companion = Modifier.INSTANCE;
            Orientation orientation = Orientation.Horizontal;
            boolean zIsRtl$material3_release = sliderState.getIsRtl();
            boolean zIsDragging$material3_release = sliderState.isDragging$material3_release();
            Modifier.Companion companion2 = companion;
            SliderState sliderState2 = sliderState;
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 174725888, "CC(remember):Slider.kt#9igjgp");
            boolean zChangedInstance = composerStartRestartGroup.changedInstance(sliderState);
            SliderKt$SliderImpl$drag$1$1 sliderKt$SliderImpl$drag$1$1RememberedValue = composerStartRestartGroup.rememberedValue();
            if (zChangedInstance || sliderKt$SliderImpl$drag$1$1RememberedValue == Composer.INSTANCE.getEmpty()) {
                sliderKt$SliderImpl$drag$1$1RememberedValue = new SliderKt$SliderImpl$drag$1$1(sliderState, null);
                composerStartRestartGroup.updateRememberedValue(sliderKt$SliderImpl$drag$1$1RememberedValue);
            }
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            z2 = z;
            int i3 = i2;
            Modifier modifierDraggable$default = DraggableKt.draggable$default(companion2, sliderState2, orientation, z2, mutableInteractionSource, zIsDragging$material3_release, null, (Function3) sliderKt$SliderImpl$drag$1$1RememberedValue, zIsRtl$material3_release, 32, null);
            mutableInteractionSource2 = mutableInteractionSource;
            Modifier modifierThen = FocusableKt.focusable(sliderSemantics(SizeKt.m1006requiredSizeInqDBjuR0$default(InteractiveComponentSizeKt.minimumInteractiveComponentSize(modifier), ThumbWidth, TrackHeight, 0.0f, 0.0f, 12, null), sliderState, z2), z2, mutableInteractionSource2).then(modifierSliderTapModifier).then(modifierDraggable$default);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 174754537, "CC(remember):Slider.kt#9igjgp");
            boolean zChangedInstance2 = composerStartRestartGroup.changedInstance(sliderState);
            MeasurePolicy measurePolicyRememberedValue = composerStartRestartGroup.rememberedValue();
            if (zChangedInstance2 || measurePolicyRememberedValue == Composer.INSTANCE.getEmpty()) {
                measurePolicyRememberedValue = new MeasurePolicy() { // from class: androidx.compose.material3.SliderKt$SliderImpl$2$1
                    @Override // androidx.compose.ui.layout.MeasurePolicy
                    /* JADX INFO: renamed from: measure-3p2s80s */
                    public final MeasureResult mo322measure3p2s80s(MeasureScope measureScope, List<? extends Measurable> list, long j) {
                        int size = list.size();
                        int i4 = 0;
                        for (int i5 = 0; i5 < size; i5++) {
                            Measurable measurable = list.get(i5);
                            if (LayoutIdKt.getLayoutId(measurable) == SliderComponents.THUMB) {
                                long j2 = j;
                                final Placeable placeableMo5903measureBRTryo0 = measurable.mo5903measureBRTryo0(j2);
                                int size2 = list.size();
                                while (i4 < size2) {
                                    Measurable measurable2 = list.get(i4);
                                    if (LayoutIdKt.getLayoutId(measurable2) == SliderComponents.TRACK) {
                                        final Placeable placeableMo5903measureBRTryo02 = measurable2.mo5903measureBRTryo0(Constraints.m6971copyZbe2FdA$default(ConstraintsKt.m7000offsetNN6EwU$default(j2, -placeableMo5903measureBRTryo0.getWidth(), 0, 2, null), 0, 0, 0, 0, 11, null));
                                        int width = placeableMo5903measureBRTryo0.getWidth() + placeableMo5903measureBRTryo02.getWidth();
                                        int iMax = Math.max(placeableMo5903measureBRTryo02.getHeight(), placeableMo5903measureBRTryo0.getHeight());
                                        sliderState.updateDimensions$material3_release(placeableMo5903measureBRTryo02.getHeight(), width);
                                        final int width2 = placeableMo5903measureBRTryo0.getWidth() / 2;
                                        final int iRoundToInt = MathKt.roundToInt(placeableMo5903measureBRTryo02.getWidth() * sliderState.getCoercedValueAsFraction$material3_release());
                                        final int height = (iMax - placeableMo5903measureBRTryo02.getHeight()) / 2;
                                        final int height2 = (iMax - placeableMo5903measureBRTryo0.getHeight()) / 2;
                                        return MeasureScope.layout$default(measureScope, width, iMax, null, new Function1<Placeable.PlacementScope, Unit>() { // from class: androidx.compose.material3.SliderKt$SliderImpl$2$1.1
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
                                                Placeable.PlacementScope.placeRelative$default(placementScope, placeableMo5903measureBRTryo02, width2, height, 0.0f, 4, null);
                                                Placeable.PlacementScope.placeRelative$default(placementScope, placeableMo5903measureBRTryo0, iRoundToInt, height2, 0.0f, 4, null);
                                            }
                                        }, 4, null);
                                    }
                                    i4++;
                                    j2 = j;
                                }
                                throw new NoSuchElementException("Collection contains no element matching the predicate.");
                            }
                        }
                        throw new NoSuchElementException("Collection contains no element matching the predicate.");
                    }
                };
                composerStartRestartGroup.updateRememberedValue(measurePolicyRememberedValue);
            }
            MeasurePolicy measurePolicy = (MeasurePolicy) measurePolicyRememberedValue;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap = composerStartRestartGroup.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierThen);
            Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
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
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -610171494, "C682@30354L85,680@30227L271,688@30511L74:Slider.kt#uh7d8r");
            Modifier modifierWrapContentWidth$default = SizeKt.wrapContentWidth$default(LayoutIdKt.layoutId(Modifier.INSTANCE, SliderComponents.THUMB), null, false, 3, null);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 534510168, "CC(remember):Slider.kt#9igjgp");
            boolean zChangedInstance3 = composerStartRestartGroup.changedInstance(sliderState);
            Object objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (zChangedInstance3 || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = (Function1) new Function1<IntSize, Unit>() { // from class: androidx.compose.material3.SliderKt$SliderImpl$1$1$1
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(IntSize intSize) {
                        m2814invokeozmzZPI(intSize.getPackedValue());
                        return Unit.INSTANCE;
                    }

                    /* JADX INFO: renamed from: invoke-ozmzZPI, reason: not valid java name */
                    public final void m2814invokeozmzZPI(long j) {
                        sliderState.setThumbWidth$material3_release(IntSize.m7197getWidthimpl(j));
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            Modifier modifierOnSizeChanged = OnRemeasuredModifierKt.onSizeChanged(modifierWrapContentWidth$default, (Function1) objRememberedValue);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
            MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
            int currentCompositeKeyHash2 = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap2 = composerStartRestartGroup.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier2 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierOnSizeChanged);
            Function0<ComposeUiNode> constructor2 = ComposeUiNode.INSTANCE.getConstructor();
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
            if (!(composerStartRestartGroup.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            composerStartRestartGroup.startReusableNode();
            if (composerStartRestartGroup.getInserting()) {
                composerStartRestartGroup.createNode(constructor2);
            } else {
                composerStartRestartGroup.useNode();
            }
            Composer composerM4013constructorimpl2 = Updater.m4013constructorimpl(composerStartRestartGroup);
            Updater.m4020setimpl(composerM4013constructorimpl2, measurePolicyMaybeCachedBoxMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4020setimpl(composerM4013constructorimpl2, currentCompositionLocalMap2, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash2 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if (composerM4013constructorimpl2.getInserting() || !Intrinsics.areEqual(composerM4013constructorimpl2.rememberedValue(), Integer.valueOf(currentCompositeKeyHash2))) {
                composerM4013constructorimpl2.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash2));
                composerM4013constructorimpl2.apply(Integer.valueOf(currentCompositeKeyHash2), setCompositeKeyHash2);
            }
            Updater.m4020setimpl(composerM4013constructorimpl2, modifierMaterializeModifier2, ComposeUiNode.INSTANCE.getSetModifier());
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
            BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -469300363, "C686@30472L12:Slider.kt#uh7d8r");
            int i4 = (i3 >> 3) & 14;
            function33 = function3;
            function33.invoke(sliderState, composerStartRestartGroup, Integer.valueOf(((i3 >> 9) & 112) | i4));
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            composerStartRestartGroup.endNode();
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            Modifier modifierLayoutId = LayoutIdKt.layoutId(Modifier.INSTANCE, SliderComponents.TRACK);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
            MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy2 = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
            int currentCompositeKeyHash3 = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap3 = composerStartRestartGroup.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier3 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierLayoutId);
            Function0<ComposeUiNode> constructor3 = ComposeUiNode.INSTANCE.getConstructor();
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
            if (!(composerStartRestartGroup.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            composerStartRestartGroup.startReusableNode();
            if (composerStartRestartGroup.getInserting()) {
                composerStartRestartGroup.createNode(constructor3);
            } else {
                composerStartRestartGroup.useNode();
            }
            Composer composerM4013constructorimpl3 = Updater.m4013constructorimpl(composerStartRestartGroup);
            Updater.m4020setimpl(composerM4013constructorimpl3, measurePolicyMaybeCachedBoxMeasurePolicy2, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4020setimpl(composerM4013constructorimpl3, currentCompositionLocalMap3, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash3 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if (composerM4013constructorimpl3.getInserting() || !Intrinsics.areEqual(composerM4013constructorimpl3.rememberedValue(), Integer.valueOf(currentCompositeKeyHash3))) {
                composerM4013constructorimpl3.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash3));
                composerM4013constructorimpl3.apply(Integer.valueOf(currentCompositeKeyHash3), setCompositeKeyHash3);
            }
            Updater.m4020setimpl(composerM4013constructorimpl3, modifierMaterializeModifier3, ComposeUiNode.INSTANCE.getSetModifier());
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
            BoxScopeInstance boxScopeInstance2 = BoxScopeInstance.INSTANCE;
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -469202155, "C688@30571L12:Slider.kt#uh7d8r");
            function34 = function32;
            function34.invoke(sliderState, composerStartRestartGroup, Integer.valueOf(((i3 >> 12) & 112) | i4));
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            composerStartRestartGroup.endNode();
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            composerStartRestartGroup.endNode();
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composerStartRestartGroup.skipToGroupEnd();
            function33 = function3;
            z2 = z;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            final boolean z3 = z2;
            final Function3<? super SliderState, ? super Composer, ? super Integer, Unit> function35 = function33;
            final Function3<? super SliderState, ? super Composer, ? super Integer, Unit> function36 = function34;
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SliderKt.SliderImpl.3
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
                    SliderKt.SliderImpl(modifier, sliderState, z3, mutableInteractionSource2, function35, function36, composer2, RecomposeScopeImplKt.updateChangedFlags(i | 1));
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void RangeSliderImpl(final Modifier modifier, final RangeSliderState rangeSliderState, final boolean z, final MutableInteractionSource mutableInteractionSource, final MutableInteractionSource mutableInteractionSource2, final Function3<? super RangeSliderState, ? super Composer, ? super Integer, Unit> function3, Function3<? super RangeSliderState, ? super Composer, ? super Integer, Unit> function32, Function3<? super RangeSliderState, ? super Composer, ? super Integer, Unit> function33, Composer composer, final int i) {
        Modifier modifier2;
        int i2;
        Function3<? super RangeSliderState, ? super Composer, ? super Integer, Unit> function34;
        final Function3<? super RangeSliderState, ? super Composer, ? super Integer, Unit> function35 = function33;
        Composer composerStartRestartGroup = composer.startRestartGroup(-1411725677);
        ComposerKt.sourceInformation(composerStartRestartGroup, "C(RangeSliderImpl)P(3,6!1,4!1,5)736@32503L7,746@32756L35,747@32824L33,784@34404L2230,749@32863L3771:Slider.kt#uh7d8r");
        if ((i & 6) == 0) {
            modifier2 = modifier;
            i2 = (composerStartRestartGroup.changed(modifier2) ? 4 : 2) | i;
        } else {
            modifier2 = modifier;
            i2 = i;
        }
        if ((i & 48) == 0) {
            i2 |= composerStartRestartGroup.changedInstance(rangeSliderState) ? 32 : 16;
        }
        if ((i & 384) == 0) {
            i2 |= composerStartRestartGroup.changed(z) ? 256 : 128;
        }
        if ((i & 3072) == 0) {
            i2 |= composerStartRestartGroup.changed(mutableInteractionSource) ? 2048 : 1024;
        }
        if ((i & 24576) == 0) {
            i2 |= composerStartRestartGroup.changed(mutableInteractionSource2) ? 16384 : 8192;
        }
        if ((196608 & i) == 0) {
            i2 |= composerStartRestartGroup.changedInstance(function3) ? 131072 : 65536;
        }
        if ((1572864 & i) == 0) {
            i2 |= composerStartRestartGroup.changedInstance(function32) ? 1048576 : 524288;
        }
        if ((12582912 & i) == 0) {
            i2 |= composerStartRestartGroup.changedInstance(function35) ? 8388608 : 4194304;
        }
        if ((4793491 & i2) != 4793490 || !composerStartRestartGroup.getSkipping()) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1411725677, i2, -1, "androidx.compose.material3.RangeSliderImpl (Slider.kt:735)");
            }
            ProvidableCompositionLocal<LayoutDirection> localLayoutDirection = CompositionLocalsKt.getLocalLayoutDirection();
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object objConsume = composerStartRestartGroup.consume(localLayoutDirection);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            rangeSliderState.setRtl$material3_release(objConsume == LayoutDirection.Rtl);
            Modifier modifierRangeSliderPressDragModifier = rangeSliderPressDragModifier(Modifier.INSTANCE, rangeSliderState, mutableInteractionSource, mutableInteractionSource2, z);
            Strings.Companion companion = Strings.INSTANCE;
            final String strM3278getString2EP1pXo = Strings_androidKt.m3278getString2EP1pXo(Strings.m3208constructorimpl(androidx.compose.ui.R.string.range_start), composerStartRestartGroup, 0);
            Strings.Companion companion2 = Strings.INSTANCE;
            final String strM3278getString2EP1pXo2 = Strings_androidKt.m3278getString2EP1pXo(Strings.m3208constructorimpl(androidx.compose.ui.R.string.range_end), composerStartRestartGroup, 0);
            Modifier modifierThen = SizeKt.m1006requiredSizeInqDBjuR0$default(InteractiveComponentSizeKt.minimumInteractiveComponentSize(modifier2), ThumbWidth, TrackHeight, 0.0f, 0.0f, 12, null).then(modifierRangeSliderPressDragModifier);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1662324209, "CC(remember):Slider.kt#9igjgp");
            boolean zChangedInstance = composerStartRestartGroup.changedInstance(rangeSliderState);
            MeasurePolicy measurePolicyRememberedValue = composerStartRestartGroup.rememberedValue();
            if (zChangedInstance || measurePolicyRememberedValue == Composer.INSTANCE.getEmpty()) {
                measurePolicyRememberedValue = new MeasurePolicy() { // from class: androidx.compose.material3.SliderKt$RangeSliderImpl$2$1
                    @Override // androidx.compose.ui.layout.MeasurePolicy
                    /* JADX INFO: renamed from: measure-3p2s80s */
                    public final MeasureResult mo322measure3p2s80s(MeasureScope measureScope, List<? extends Measurable> list, long j) {
                        long j2 = j;
                        int size = list.size();
                        int i3 = 0;
                        int i4 = 0;
                        while (i4 < size) {
                            Measurable measurable = list.get(i4);
                            if (LayoutIdKt.getLayoutId(measurable) == RangeSliderComponents.STARTTHUMB) {
                                final Placeable placeableMo5903measureBRTryo0 = measurable.mo5903measureBRTryo0(j2);
                                int size2 = list.size();
                                int i5 = 0;
                                while (i5 < size2) {
                                    Measurable measurable2 = list.get(i5);
                                    if (LayoutIdKt.getLayoutId(measurable2) == RangeSliderComponents.ENDTHUMB) {
                                        final Placeable placeableMo5903measureBRTryo02 = measurable2.mo5903measureBRTryo0(j2);
                                        int size3 = list.size();
                                        while (i3 < size3) {
                                            Measurable measurable3 = list.get(i3);
                                            if (LayoutIdKt.getLayoutId(measurable3) == RangeSliderComponents.TRACK) {
                                                final Placeable placeableMo5903measureBRTryo03 = measurable3.mo5903measureBRTryo0(Constraints.m6971copyZbe2FdA$default(ConstraintsKt.m7000offsetNN6EwU$default(j2, (-(placeableMo5903measureBRTryo0.getWidth() + placeableMo5903measureBRTryo02.getWidth())) / 2, 0, 2, null), 0, 0, 0, 0, 11, null));
                                                int width = placeableMo5903measureBRTryo03.getWidth() + ((placeableMo5903measureBRTryo0.getWidth() + placeableMo5903measureBRTryo02.getWidth()) / 2);
                                                int iMax = Math.max(placeableMo5903measureBRTryo03.getHeight(), Math.max(placeableMo5903measureBRTryo0.getHeight(), placeableMo5903measureBRTryo02.getHeight()));
                                                rangeSliderState.setTrackHeight$material3_release(placeableMo5903measureBRTryo03.getHeight());
                                                rangeSliderState.setTotalWidth$material3_release(width);
                                                rangeSliderState.updateMinMaxPx$material3_release();
                                                final int width2 = placeableMo5903measureBRTryo0.getWidth() / 2;
                                                final int iRoundToInt = MathKt.roundToInt(placeableMo5903measureBRTryo03.getWidth() * rangeSliderState.getCoercedActiveRangeStartAsFraction$material3_release());
                                                final int iRoundToInt2 = MathKt.roundToInt((placeableMo5903measureBRTryo03.getWidth() * rangeSliderState.getCoercedActiveRangeEndAsFraction$material3_release()) + ((placeableMo5903measureBRTryo0.getWidth() - placeableMo5903measureBRTryo02.getWidth()) / 2));
                                                final int height = (iMax - placeableMo5903measureBRTryo03.getHeight()) / 2;
                                                final int height2 = (iMax - placeableMo5903measureBRTryo0.getHeight()) / 2;
                                                final int height3 = (iMax - placeableMo5903measureBRTryo02.getHeight()) / 2;
                                                return MeasureScope.layout$default(measureScope, width, iMax, null, new Function1<Placeable.PlacementScope, Unit>() { // from class: androidx.compose.material3.SliderKt$RangeSliderImpl$2$1.1
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
                                                        Placeable.PlacementScope.placeRelative$default(placementScope, placeableMo5903measureBRTryo03, width2, height, 0.0f, 4, null);
                                                        Placeable.PlacementScope.placeRelative$default(placementScope, placeableMo5903measureBRTryo0, iRoundToInt, height2, 0.0f, 4, null);
                                                        Placeable.PlacementScope.placeRelative$default(placementScope, placeableMo5903measureBRTryo02, iRoundToInt2, height3, 0.0f, 4, null);
                                                    }
                                                }, 4, null);
                                            }
                                            i3++;
                                            j2 = j;
                                        }
                                        throw new NoSuchElementException("Collection contains no element matching the predicate.");
                                    }
                                    i5++;
                                    j2 = j;
                                }
                                throw new NoSuchElementException("Collection contains no element matching the predicate.");
                            }
                            i4++;
                            j2 = j;
                        }
                        throw new NoSuchElementException("Collection contains no element matching the predicate.");
                    }
                };
                composerStartRestartGroup.updateRememberedValue(measurePolicyRememberedValue);
            }
            MeasurePolicy measurePolicy = (MeasurePolicy) measurePolicyRememberedValue;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap = composerStartRestartGroup.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierThen);
            int i3 = i2;
            Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
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
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2110426496, "C755@33080L46,757@33259L100,751@32893L598,768@33689L44,770@33864L98,764@33504L586,777@34103L79:Slider.kt#uh7d8r");
            Modifier modifierWrapContentWidth$default = SizeKt.wrapContentWidth$default(LayoutIdKt.layoutId(Modifier.INSTANCE, RangeSliderComponents.STARTTHUMB), null, false, 3, null);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1176461672, "CC(remember):Slider.kt#9igjgp");
            boolean zChangedInstance2 = composerStartRestartGroup.changedInstance(rangeSliderState);
            Object objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (zChangedInstance2 || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = (Function1) new Function1<IntSize, Unit>() { // from class: androidx.compose.material3.SliderKt$RangeSliderImpl$1$1$1
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(IntSize intSize) {
                        m2812invokeozmzZPI(intSize.getPackedValue());
                        return Unit.INSTANCE;
                    }

                    /* JADX INFO: renamed from: invoke-ozmzZPI, reason: not valid java name */
                    public final void m2812invokeozmzZPI(long j) {
                        rangeSliderState.setStartThumbWidth$material3_release(IntSize.m7197getWidthimpl(j));
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            Modifier modifierRangeSliderStartThumbSemantics = rangeSliderStartThumbSemantics(OnRemeasuredModifierKt.onSizeChanged(modifierWrapContentWidth$default, (Function1) objRememberedValue), rangeSliderState, z);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1176467454, "CC(remember):Slider.kt#9igjgp");
            boolean zChanged = composerStartRestartGroup.changed(strM3278getString2EP1pXo);
            Object objRememberedValue2 = composerStartRestartGroup.rememberedValue();
            if (zChanged || objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue2 = (Function1) new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.SliderKt$RangeSliderImpl$1$2$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                        invoke2(semanticsPropertyReceiver);
                        return Unit.INSTANCE;
                    }

                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                        SemanticsPropertiesKt.setContentDescription(semanticsPropertyReceiver, strM3278getString2EP1pXo);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
            }
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            Modifier modifierFocusable = FocusableKt.focusable(SemanticsModifierKt.semantics(modifierRangeSliderStartThumbSemantics, true, (Function1) objRememberedValue2), z, mutableInteractionSource);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
            MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
            int currentCompositeKeyHash2 = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap2 = composerStartRestartGroup.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier2 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierFocusable);
            Function0<ComposeUiNode> constructor2 = ComposeUiNode.INSTANCE.getConstructor();
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
            if (!(composerStartRestartGroup.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            composerStartRestartGroup.startReusableNode();
            if (composerStartRestartGroup.getInserting()) {
                composerStartRestartGroup.createNode(constructor2);
            } else {
                composerStartRestartGroup.useNode();
            }
            Composer composerM4013constructorimpl2 = Updater.m4013constructorimpl(composerStartRestartGroup);
            Updater.m4020setimpl(composerM4013constructorimpl2, measurePolicyMaybeCachedBoxMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4020setimpl(composerM4013constructorimpl2, currentCompositionLocalMap2, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash2 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if (composerM4013constructorimpl2.getInserting() || !Intrinsics.areEqual(composerM4013constructorimpl2.rememberedValue(), Integer.valueOf(currentCompositeKeyHash2))) {
                composerM4013constructorimpl2.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash2));
                composerM4013constructorimpl2.apply(Integer.valueOf(currentCompositeKeyHash2), setCompositeKeyHash2);
            }
            Updater.m4020setimpl(composerM4013constructorimpl2, modifierMaterializeModifier2, ComposeUiNode.INSTANCE.getSetModifier());
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
            BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 259534201, "C762@33460L17:Slider.kt#uh7d8r");
            int i4 = (i3 >> 3) & 14;
            function3.invoke(rangeSliderState, composerStartRestartGroup, Integer.valueOf(((i3 >> 12) & 112) | i4));
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            composerStartRestartGroup.endNode();
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            Modifier modifierWrapContentWidth$default2 = SizeKt.wrapContentWidth$default(LayoutIdKt.layoutId(Modifier.INSTANCE, RangeSliderComponents.ENDTHUMB), null, false, 3, null);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1176481158, "CC(remember):Slider.kt#9igjgp");
            boolean zChangedInstance3 = composerStartRestartGroup.changedInstance(rangeSliderState);
            Object objRememberedValue3 = composerStartRestartGroup.rememberedValue();
            if (zChangedInstance3 || objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue3 = (Function1) new Function1<IntSize, Unit>() { // from class: androidx.compose.material3.SliderKt$RangeSliderImpl$1$4$1
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(IntSize intSize) {
                        m2813invokeozmzZPI(intSize.getPackedValue());
                        return Unit.INSTANCE;
                    }

                    /* JADX INFO: renamed from: invoke-ozmzZPI, reason: not valid java name */
                    public final void m2813invokeozmzZPI(long j) {
                        rangeSliderState.setEndThumbWidth$material3_release(IntSize.m7197getWidthimpl(j));
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
            }
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            Modifier modifierRangeSliderEndThumbSemantics = rangeSliderEndThumbSemantics(OnRemeasuredModifierKt.onSizeChanged(modifierWrapContentWidth$default2, (Function1) objRememberedValue3), rangeSliderState, z);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1176486812, "CC(remember):Slider.kt#9igjgp");
            boolean zChanged2 = composerStartRestartGroup.changed(strM3278getString2EP1pXo2);
            Object objRememberedValue4 = composerStartRestartGroup.rememberedValue();
            if (zChanged2 || objRememberedValue4 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue4 = (Function1) new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.SliderKt$RangeSliderImpl$1$5$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                        invoke2(semanticsPropertyReceiver);
                        return Unit.INSTANCE;
                    }

                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                        SemanticsPropertiesKt.setContentDescription(semanticsPropertyReceiver, strM3278getString2EP1pXo2);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
            }
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            Modifier modifierFocusable2 = FocusableKt.focusable(SemanticsModifierKt.semantics(modifierRangeSliderEndThumbSemantics, true, (Function1) objRememberedValue4), z, mutableInteractionSource2);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
            MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy2 = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
            int currentCompositeKeyHash3 = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap3 = composerStartRestartGroup.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier3 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierFocusable2);
            Function0<ComposeUiNode> constructor3 = ComposeUiNode.INSTANCE.getConstructor();
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
            if (!(composerStartRestartGroup.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            composerStartRestartGroup.startReusableNode();
            if (composerStartRestartGroup.getInserting()) {
                composerStartRestartGroup.createNode(constructor3);
            } else {
                composerStartRestartGroup.useNode();
            }
            Composer composerM4013constructorimpl3 = Updater.m4013constructorimpl(composerStartRestartGroup);
            Updater.m4020setimpl(composerM4013constructorimpl3, measurePolicyMaybeCachedBoxMeasurePolicy2, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4020setimpl(composerM4013constructorimpl3, currentCompositionLocalMap3, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash3 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if (composerM4013constructorimpl3.getInserting() || !Intrinsics.areEqual(composerM4013constructorimpl3.rememberedValue(), Integer.valueOf(currentCompositeKeyHash3))) {
                composerM4013constructorimpl3.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash3));
                composerM4013constructorimpl3.apply(Integer.valueOf(currentCompositeKeyHash3), setCompositeKeyHash3);
            }
            Updater.m4020setimpl(composerM4013constructorimpl3, modifierMaterializeModifier3, ComposeUiNode.INSTANCE.getSetModifier());
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
            BoxScopeInstance boxScopeInstance2 = BoxScopeInstance.INSTANCE;
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 260130331, "C775@34061L15:Slider.kt#uh7d8r");
            function34 = function32;
            function34.invoke(rangeSliderState, composerStartRestartGroup, Integer.valueOf(((i3 >> 15) & 112) | i4));
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            composerStartRestartGroup.endNode();
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            Modifier modifierLayoutId = LayoutIdKt.layoutId(Modifier.INSTANCE, RangeSliderComponents.TRACK);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
            MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy3 = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
            int currentCompositeKeyHash4 = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap4 = composerStartRestartGroup.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier4 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierLayoutId);
            Function0<ComposeUiNode> constructor4 = ComposeUiNode.INSTANCE.getConstructor();
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
            if (!(composerStartRestartGroup.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            composerStartRestartGroup.startReusableNode();
            if (composerStartRestartGroup.getInserting()) {
                composerStartRestartGroup.createNode(constructor4);
            } else {
                composerStartRestartGroup.useNode();
            }
            Composer composerM4013constructorimpl4 = Updater.m4013constructorimpl(composerStartRestartGroup);
            Updater.m4020setimpl(composerM4013constructorimpl4, measurePolicyMaybeCachedBoxMeasurePolicy3, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4020setimpl(composerM4013constructorimpl4, currentCompositionLocalMap4, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash4 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if (composerM4013constructorimpl4.getInserting() || !Intrinsics.areEqual(composerM4013constructorimpl4.rememberedValue(), Integer.valueOf(currentCompositeKeyHash4))) {
                composerM4013constructorimpl4.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash4));
                composerM4013constructorimpl4.apply(Integer.valueOf(currentCompositeKeyHash4), setCompositeKeyHash4);
            }
            Updater.m4020setimpl(composerM4013constructorimpl4, modifierMaterializeModifier4, ComposeUiNode.INSTANCE.getSetModifier());
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
            BoxScopeInstance boxScopeInstance3 = BoxScopeInstance.INSTANCE;
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 260236382, "C777@34168L12:Slider.kt#uh7d8r");
            Integer numValueOf = Integer.valueOf(((i3 >> 18) & 112) | i4);
            function35 = function33;
            function35.invoke(rangeSliderState, composerStartRestartGroup, numValueOf);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            composerStartRestartGroup.endNode();
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            composerStartRestartGroup.endNode();
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composerStartRestartGroup.skipToGroupEnd();
            function34 = function32;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            final Function3<? super RangeSliderState, ? super Composer, ? super Integer, Unit> function36 = function34;
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SliderKt.RangeSliderImpl.3
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
                    SliderKt.RangeSliderImpl(modifier, rangeSliderState, z, mutableInteractionSource, mutableInteractionSource2, function3, function36, function35, composer2, RecomposeScopeImplKt.updateChangedFlags(i | 1));
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0014  */
    /* JADX INFO: renamed from: awaitSlop-8vUncbI, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final java.lang.Object m2807awaitSlop8vUncbI(androidx.compose.ui.input.pointer.AwaitPointerEventScope r8, long r9, int r11, kotlin.coroutines.Continuation<? super kotlin.Pair<androidx.compose.ui.input.pointer.PointerInputChange, java.lang.Float>> r12) {
        /*
            boolean r0 = r12 instanceof androidx.compose.material3.SliderKt$awaitSlop$1
            if (r0 == 0) goto L14
            r0 = r12
            androidx.compose.material3.SliderKt$awaitSlop$1 r0 = (androidx.compose.material3.SliderKt$awaitSlop$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r12 = r0.label
            int r12 = r12 - r2
            r0.label = r12
            goto L19
        L14:
            androidx.compose.material3.SliderKt$awaitSlop$1 r0 = new androidx.compose.material3.SliderKt$awaitSlop$1
            r0.<init>(r12)
        L19:
            r6 = r0
            java.lang.Object r12 = r6.result
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r6.label
            r2 = 1
            if (r1 == 0) goto L37
            if (r1 != r2) goto L2f
            java.lang.Object r8 = r6.L$0
            kotlin.jvm.internal.Ref$FloatRef r8 = (kotlin.jvm.internal.Ref.FloatRef) r8
            kotlin.ResultKt.throwOnFailure(r12)
            goto L58
        L2f:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r9 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r9)
            throw r8
        L37:
            kotlin.ResultKt.throwOnFailure(r12)
            kotlin.jvm.internal.Ref$FloatRef r12 = new kotlin.jvm.internal.Ref$FloatRef
            r12.<init>()
            androidx.compose.material3.SliderKt$awaitSlop$postPointerSlop$1 r1 = new androidx.compose.material3.SliderKt$awaitSlop$postPointerSlop$1
            r1.<init>()
            r5 = r1
            kotlin.jvm.functions.Function2 r5 = (kotlin.jvm.functions.Function2) r5
            r6.L$0 = r12
            r6.label = r2
            r1 = r8
            r2 = r9
            r4 = r11
            java.lang.Object r8 = androidx.compose.material3.internal.DragGestureDetectorCopyKt.m3196awaitHorizontalPointerSlopOrCancellationgDDlDlE(r1, r2, r4, r5, r6)
            if (r8 != r0) goto L55
            return r0
        L55:
            r7 = r12
            r12 = r8
            r8 = r7
        L58:
            androidx.compose.ui.input.pointer.PointerInputChange r12 = (androidx.compose.ui.input.pointer.PointerInputChange) r12
            if (r12 == 0) goto L67
            float r8 = r8.element
            java.lang.Float r8 = kotlin.coroutines.jvm.internal.Boxing.boxFloat(r8)
            kotlin.Pair r8 = kotlin.TuplesKt.to(r12, r8)
            return r8
        L67:
            r8 = 0
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.SliderKt.m2807awaitSlop8vUncbI(androidx.compose.ui.input.pointer.AwaitPointerEventScope, long, int, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final float[] stepsToTickFractions(int i) {
        if (i == 0) {
            return new float[0];
        }
        int i2 = i + 2;
        float[] fArr = new float[i2];
        for (int i3 = 0; i3 < i2; i3++) {
            fArr[i3] = i3 / (i + 1);
        }
        return fArr;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final float scale(float f, float f2, float f3, float f4, float f5) {
        return MathHelpersKt.lerp(f4, f5, calcFraction(f, f2, f3));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: scale-ziovWd0, reason: not valid java name */
    public static final long m2810scaleziovWd0(float f, float f2, long j, float f3, float f4) {
        return SliderRange(scale(f, f2, SliderRange.m2822getStartimpl(j), f3, f4), scale(f, f2, SliderRange.m2821getEndInclusiveimpl(j), f3, f4));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final float calcFraction(float f, float f2, float f3) {
        float f4 = f2 - f;
        return RangesKt.coerceIn(f4 == 0.0f ? 0.0f : (f3 - f) / f4, 0.0f, 1.0f);
    }

    private static final Modifier sliderSemantics(Modifier modifier, final SliderState sliderState, final boolean z) {
        return ProgressSemanticsKt.progressSemantics(SemanticsModifierKt.semantics$default(modifier, false, new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.SliderKt.sliderSemantics.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                invoke2(semanticsPropertyReceiver);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                if (!z) {
                    SemanticsPropertiesKt.disabled(semanticsPropertyReceiver);
                }
                final SliderState sliderState2 = sliderState;
                SemanticsPropertiesKt.setProgress$default(semanticsPropertyReceiver, null, new Function1<Float, Boolean>() { // from class: androidx.compose.material3.SliderKt.sliderSemantics.1.1
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Boolean invoke(Float f) {
                        return invoke(f.floatValue());
                    }

                    public final Boolean invoke(float f) {
                        int steps;
                        float fCoerceIn = RangesKt.coerceIn(f, sliderState2.getValueRange().getStart().floatValue(), sliderState2.getValueRange().getEndInclusive().floatValue());
                        boolean z2 = false;
                        if (sliderState2.getSteps() > 0 && (steps = sliderState2.getSteps() + 1) >= 0) {
                            float fAbs = fCoerceIn;
                            float f2 = fAbs;
                            int i = 0;
                            while (true) {
                                float fLerp = MathHelpersKt.lerp(sliderState2.getValueRange().getStart().floatValue(), sliderState2.getValueRange().getEndInclusive().floatValue(), i / (sliderState2.getSteps() + 1));
                                float f3 = fLerp - fCoerceIn;
                                if (Math.abs(f3) <= fAbs) {
                                    fAbs = Math.abs(f3);
                                    f2 = fLerp;
                                }
                                if (i == steps) {
                                    break;
                                }
                                i++;
                            }
                            fCoerceIn = f2;
                        }
                        if (fCoerceIn != sliderState2.getValue()) {
                            if (fCoerceIn != sliderState2.getValue()) {
                                if (sliderState2.getOnValueChange$material3_release() != null) {
                                    Function1<Float, Unit> onValueChange$material3_release = sliderState2.getOnValueChange$material3_release();
                                    if (onValueChange$material3_release != null) {
                                        onValueChange$material3_release.invoke(Float.valueOf(fCoerceIn));
                                    }
                                } else {
                                    sliderState2.setValue(fCoerceIn);
                                }
                            }
                            Function0<Unit> onValueChangeFinished = sliderState2.getOnValueChangeFinished();
                            if (onValueChangeFinished != null) {
                                onValueChangeFinished.invoke();
                            }
                            z2 = true;
                        }
                        return Boolean.valueOf(z2);
                    }
                }, 1, null);
            }
        }, 1, null).then(AccessibilityUtilKt.getIncreaseHorizontalSemanticsBounds()), sliderState.getValue(), RangesKt.rangeTo(sliderState.getValueRange().getStart().floatValue(), sliderState.getValueRange().getEndInclusive().floatValue()), sliderState.getSteps());
    }

    private static final Modifier rangeSliderStartThumbSemantics(Modifier modifier, final RangeSliderState rangeSliderState, final boolean z) {
        final ClosedFloatingPointRange<Float> closedFloatingPointRangeRangeTo = RangesKt.rangeTo(rangeSliderState.getValueRange().getStart().floatValue(), rangeSliderState.getActiveRangeEnd());
        return ProgressSemanticsKt.progressSemantics(SemanticsModifierKt.semantics$default(modifier, false, new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.SliderKt.rangeSliderStartThumbSemantics.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                invoke2(semanticsPropertyReceiver);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                if (!z) {
                    SemanticsPropertiesKt.disabled(semanticsPropertyReceiver);
                }
                final ClosedFloatingPointRange<Float> closedFloatingPointRange = closedFloatingPointRangeRangeTo;
                final RangeSliderState rangeSliderState2 = rangeSliderState;
                SemanticsPropertiesKt.setProgress$default(semanticsPropertyReceiver, null, new Function1<Float, Boolean>() { // from class: androidx.compose.material3.SliderKt.rangeSliderStartThumbSemantics.1.1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Boolean invoke(Float f) {
                        return invoke(f.floatValue());
                    }

                    public final Boolean invoke(float f) {
                        int startSteps$material3_release;
                        float fCoerceIn = RangesKt.coerceIn(f, closedFloatingPointRange.getStart().floatValue(), closedFloatingPointRange.getEndInclusive().floatValue());
                        boolean z2 = false;
                        if (rangeSliderState2.getStartSteps$material3_release() > 0 && (startSteps$material3_release = rangeSliderState2.getStartSteps$material3_release() + 1) >= 0) {
                            float fAbs = fCoerceIn;
                            float f2 = fAbs;
                            int i = 0;
                            while (true) {
                                float fLerp = MathHelpersKt.lerp(closedFloatingPointRange.getStart().floatValue(), closedFloatingPointRange.getEndInclusive().floatValue(), i / (rangeSliderState2.getStartSteps$material3_release() + 1));
                                float f3 = fLerp - fCoerceIn;
                                if (Math.abs(f3) <= fAbs) {
                                    fAbs = Math.abs(f3);
                                    f2 = fLerp;
                                }
                                if (i == startSteps$material3_release) {
                                    break;
                                }
                                i++;
                            }
                            fCoerceIn = f2;
                        }
                        if (fCoerceIn != rangeSliderState2.getActiveRangeStart()) {
                            long jSliderRange = SliderKt.SliderRange(fCoerceIn, rangeSliderState2.getActiveRangeEnd());
                            if (!SliderRange.m2820equalsimpl0(jSliderRange, SliderKt.SliderRange(rangeSliderState2.getActiveRangeStart(), rangeSliderState2.getActiveRangeEnd()))) {
                                if (rangeSliderState2.getOnValueChange$material3_release() != null) {
                                    Function1<SliderRange, Unit> onValueChange$material3_release = rangeSliderState2.getOnValueChange$material3_release();
                                    if (onValueChange$material3_release != null) {
                                        onValueChange$material3_release.invoke(SliderRange.m2817boximpl(jSliderRange));
                                    }
                                } else {
                                    rangeSliderState2.setActiveRangeStart(SliderRange.m2822getStartimpl(jSliderRange));
                                    rangeSliderState2.setActiveRangeEnd(SliderRange.m2821getEndInclusiveimpl(jSliderRange));
                                }
                            }
                            Function0<Unit> onValueChangeFinished = rangeSliderState2.getOnValueChangeFinished();
                            if (onValueChangeFinished != null) {
                                onValueChangeFinished.invoke();
                            }
                            z2 = true;
                        }
                        return Boolean.valueOf(z2);
                    }
                }, 1, null);
            }
        }, 1, null).then(AccessibilityUtilKt.getIncreaseHorizontalSemanticsBounds()), rangeSliderState.getActiveRangeStart(), closedFloatingPointRangeRangeTo, rangeSliderState.getStartSteps$material3_release());
    }

    private static final Modifier rangeSliderEndThumbSemantics(Modifier modifier, final RangeSliderState rangeSliderState, final boolean z) {
        final ClosedFloatingPointRange<Float> closedFloatingPointRangeRangeTo = RangesKt.rangeTo(rangeSliderState.getActiveRangeStart(), rangeSliderState.getValueRange().getEndInclusive().floatValue());
        return ProgressSemanticsKt.progressSemantics(SemanticsModifierKt.semantics$default(modifier, false, new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.SliderKt.rangeSliderEndThumbSemantics.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                invoke2(semanticsPropertyReceiver);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                if (!z) {
                    SemanticsPropertiesKt.disabled(semanticsPropertyReceiver);
                }
                final ClosedFloatingPointRange<Float> closedFloatingPointRange = closedFloatingPointRangeRangeTo;
                final RangeSliderState rangeSliderState2 = rangeSliderState;
                SemanticsPropertiesKt.setProgress$default(semanticsPropertyReceiver, null, new Function1<Float, Boolean>() { // from class: androidx.compose.material3.SliderKt.rangeSliderEndThumbSemantics.1.1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Boolean invoke(Float f) {
                        return invoke(f.floatValue());
                    }

                    public final Boolean invoke(float f) {
                        int endSteps$material3_release;
                        float fCoerceIn = RangesKt.coerceIn(f, closedFloatingPointRange.getStart().floatValue(), closedFloatingPointRange.getEndInclusive().floatValue());
                        boolean z2 = false;
                        if (rangeSliderState2.getEndSteps$material3_release() > 0 && (endSteps$material3_release = rangeSliderState2.getEndSteps$material3_release() + 1) >= 0) {
                            float fAbs = fCoerceIn;
                            float f2 = fAbs;
                            int i = 0;
                            while (true) {
                                float fLerp = MathHelpersKt.lerp(closedFloatingPointRange.getStart().floatValue(), closedFloatingPointRange.getEndInclusive().floatValue(), i / (rangeSliderState2.getEndSteps$material3_release() + 1));
                                float f3 = fLerp - fCoerceIn;
                                if (Math.abs(f3) <= fAbs) {
                                    fAbs = Math.abs(f3);
                                    f2 = fLerp;
                                }
                                if (i == endSteps$material3_release) {
                                    break;
                                }
                                i++;
                            }
                            fCoerceIn = f2;
                        }
                        if (fCoerceIn != rangeSliderState2.getActiveRangeEnd()) {
                            long jSliderRange = SliderKt.SliderRange(rangeSliderState2.getActiveRangeStart(), fCoerceIn);
                            if (!SliderRange.m2820equalsimpl0(jSliderRange, SliderKt.SliderRange(rangeSliderState2.getActiveRangeStart(), rangeSliderState2.getActiveRangeEnd()))) {
                                if (rangeSliderState2.getOnValueChange$material3_release() != null) {
                                    Function1<SliderRange, Unit> onValueChange$material3_release = rangeSliderState2.getOnValueChange$material3_release();
                                    if (onValueChange$material3_release != null) {
                                        onValueChange$material3_release.invoke(SliderRange.m2817boximpl(jSliderRange));
                                    }
                                } else {
                                    rangeSliderState2.setActiveRangeStart(SliderRange.m2822getStartimpl(jSliderRange));
                                    rangeSliderState2.setActiveRangeEnd(SliderRange.m2821getEndInclusiveimpl(jSliderRange));
                                }
                            }
                            Function0<Unit> onValueChangeFinished = rangeSliderState2.getOnValueChangeFinished();
                            if (onValueChangeFinished != null) {
                                onValueChangeFinished.invoke();
                            }
                            z2 = true;
                        }
                        return Boolean.valueOf(z2);
                    }
                }, 1, null);
            }
        }, 1, null).then(AccessibilityUtilKt.getIncreaseHorizontalSemanticsBounds()), rangeSliderState.getActiveRangeEnd(), closedFloatingPointRangeRangeTo, rangeSliderState.getEndSteps$material3_release());
    }

    /* JADX INFO: renamed from: androidx.compose.material3.SliderKt$sliderTapModifier$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: Slider.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Landroidx/compose/ui/input/pointer/PointerInputScope;"}, k = 3, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    @DebugMetadata(c = "androidx.compose.material3.SliderKt$sliderTapModifier$1", f = "Slider.kt", i = {}, l = {1627}, m = "invokeSuspend", n = {}, s = {})
    static final class C05721 extends SuspendLambda implements Function2<PointerInputScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ SliderState $state;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C05721(SliderState sliderState, Continuation<? super C05721> continuation) {
            super(2, continuation);
            this.$state = sliderState;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C05721 c05721 = new C05721(this.$state, continuation);
            c05721.L$0 = obj;
            return c05721;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(PointerInputScope pointerInputScope, Continuation<? super Unit> continuation) {
            return ((C05721) create(pointerInputScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                PointerInputScope pointerInputScope = (PointerInputScope) this.L$0;
                C01241 c01241 = new C01241(this.$state, null);
                final SliderState sliderState = this.$state;
                this.label = 1;
                if (TapGestureDetectorKt.detectTapGestures$default(pointerInputScope, null, null, c01241, new Function1<Offset, Unit>() { // from class: androidx.compose.material3.SliderKt.sliderTapModifier.1.2
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(Offset offset) {
                        m2816invokek4lQ0M(offset.getPackedValue());
                        return Unit.INSTANCE;
                    }

                    /* JADX INFO: renamed from: invoke-k-4lQ0M, reason: not valid java name */
                    public final void m2816invokek4lQ0M(long j) {
                        sliderState.dispatchRawDelta(0.0f);
                        sliderState.getGestureEndAction$material3_release().invoke();
                    }
                }, this, 3, null) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            return Unit.INSTANCE;
        }

        /* JADX INFO: renamed from: androidx.compose.material3.SliderKt$sliderTapModifier$1$1, reason: invalid class name and collision with other inner class name */
        /* JADX INFO: compiled from: Slider.kt */
        @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u008a@"}, d2 = {"<anonymous>", "", "Landroidx/compose/foundation/gestures/PressGestureScope;", "it", "Landroidx/compose/ui/geometry/Offset;"}, k = 3, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
        @DebugMetadata(c = "androidx.compose.material3.SliderKt$sliderTapModifier$1$1", f = "Slider.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        static final class C01241 extends SuspendLambda implements Function3<PressGestureScope, Offset, Continuation<? super Unit>, Object> {
            final /* synthetic */ SliderState $state;
            /* synthetic */ long J$0;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            C01241(SliderState sliderState, Continuation<? super C01241> continuation) {
                super(3, continuation);
                this.$state = sliderState;
            }

            @Override // kotlin.jvm.functions.Function3
            public /* bridge */ /* synthetic */ Object invoke(PressGestureScope pressGestureScope, Offset offset, Continuation<? super Unit> continuation) {
                return m2815invoked4ec7I(pressGestureScope, offset.getPackedValue(), continuation);
            }

            /* JADX INFO: renamed from: invoke-d-4ec7I, reason: not valid java name */
            public final Object m2815invoked4ec7I(PressGestureScope pressGestureScope, long j, Continuation<? super Unit> continuation) {
                C01241 c01241 = new C01241(this.$state, continuation);
                c01241.J$0 = j;
                return c01241.invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                IntrinsicsKt.getCOROUTINE_SUSPENDED();
                if (this.label != 0) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
                this.$state.m2828onPressk4lQ0M$material3_release(this.J$0);
                return Unit.INSTANCE;
            }
        }
    }

    private static final Modifier sliderTapModifier(Modifier modifier, SliderState sliderState, MutableInteractionSource mutableInteractionSource, boolean z) {
        return z ? SuspendingPointerInputFilterKt.pointerInput(modifier, sliderState, mutableInteractionSource, new C05721(sliderState, null)) : modifier;
    }

    /* JADX INFO: renamed from: androidx.compose.material3.SliderKt$rangeSliderPressDragModifier$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: Slider.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Landroidx/compose/ui/input/pointer/PointerInputScope;"}, k = 3, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    @DebugMetadata(c = "androidx.compose.material3.SliderKt$rangeSliderPressDragModifier$1", f = "Slider.kt", i = {}, l = {1651}, m = "invokeSuspend", n = {}, s = {})
    static final class C05691 extends SuspendLambda implements Function2<PointerInputScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ MutableInteractionSource $endInteractionSource;
        final /* synthetic */ MutableInteractionSource $startInteractionSource;
        final /* synthetic */ RangeSliderState $state;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C05691(RangeSliderState rangeSliderState, MutableInteractionSource mutableInteractionSource, MutableInteractionSource mutableInteractionSource2, Continuation<? super C05691> continuation) {
            super(2, continuation);
            this.$state = rangeSliderState;
            this.$startInteractionSource = mutableInteractionSource;
            this.$endInteractionSource = mutableInteractionSource2;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C05691 c05691 = new C05691(this.$state, this.$startInteractionSource, this.$endInteractionSource, continuation);
            c05691.L$0 = obj;
            return c05691;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(PointerInputScope pointerInputScope, Continuation<? super Unit> continuation) {
            return ((C05691) create(pointerInputScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                PointerInputScope pointerInputScope = (PointerInputScope) this.L$0;
                RangeSliderLogic rangeSliderLogic = new RangeSliderLogic(this.$state, this.$startInteractionSource, this.$endInteractionSource);
                this.label = 1;
                if (CoroutineScopeKt.coroutineScope(new C01201(pointerInputScope, this.$state, rangeSliderLogic, null), this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            return Unit.INSTANCE;
        }

        /* JADX INFO: renamed from: androidx.compose.material3.SliderKt$rangeSliderPressDragModifier$1$1, reason: invalid class name and collision with other inner class name */
        /* JADX INFO: compiled from: Slider.kt */
        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
        @DebugMetadata(c = "androidx.compose.material3.SliderKt$rangeSliderPressDragModifier$1$1", f = "Slider.kt", i = {}, l = {1652}, m = "invokeSuspend", n = {}, s = {})
        static final class C01201 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            final /* synthetic */ PointerInputScope $$this$pointerInput;
            final /* synthetic */ RangeSliderLogic $rangeSliderLogic;
            final /* synthetic */ RangeSliderState $state;
            private /* synthetic */ Object L$0;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            C01201(PointerInputScope pointerInputScope, RangeSliderState rangeSliderState, RangeSliderLogic rangeSliderLogic, Continuation<? super C01201> continuation) {
                super(2, continuation);
                this.$$this$pointerInput = pointerInputScope;
                this.$state = rangeSliderState;
                this.$rangeSliderLogic = rangeSliderLogic;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                C01201 c01201 = new C01201(this.$$this$pointerInput, this.$state, this.$rangeSliderLogic, continuation);
                c01201.L$0 = obj;
                return c01201;
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                return ((C01201) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            /* JADX INFO: renamed from: androidx.compose.material3.SliderKt$rangeSliderPressDragModifier$1$1$1, reason: invalid class name and collision with other inner class name */
            /* JADX INFO: compiled from: Slider.kt */
            @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;"}, k = 3, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
            @DebugMetadata(c = "androidx.compose.material3.SliderKt$rangeSliderPressDragModifier$1$1$1", f = "Slider.kt", i = {0, 1, 1, 1, 1, 1, 2, 2}, l = {1653, 1665, 1687}, m = "invokeSuspend", n = {"$this$awaitEachGesture", "$this$awaitEachGesture", NotificationCompat.CATEGORY_EVENT, "interaction", "posX", "draggingStart", "interaction", "draggingStart"}, s = {"L$0", "L$0", "L$1", "L$2", "L$3", "L$4", "L$0", "L$1"})
            static final class C01211 extends RestrictedSuspendLambda implements Function2<AwaitPointerEventScope, Continuation<? super Unit>, Object> {
                final /* synthetic */ CoroutineScope $$this$coroutineScope;
                final /* synthetic */ RangeSliderLogic $rangeSliderLogic;
                final /* synthetic */ RangeSliderState $state;
                private /* synthetic */ Object L$0;
                Object L$1;
                Object L$2;
                Object L$3;
                Object L$4;
                int label;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                C01211(RangeSliderState rangeSliderState, RangeSliderLogic rangeSliderLogic, CoroutineScope coroutineScope, Continuation<? super C01211> continuation) {
                    super(2, continuation);
                    this.$state = rangeSliderState;
                    this.$rangeSliderLogic = rangeSliderLogic;
                    this.$$this$coroutineScope = coroutineScope;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                    C01211 c01211 = new C01211(this.$state, this.$rangeSliderLogic, this.$$this$coroutineScope, continuation);
                    c01211.L$0 = obj;
                    return c01211;
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(AwaitPointerEventScope awaitPointerEventScope, Continuation<? super Unit> continuation) {
                    return ((C01211) create(awaitPointerEventScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                /* JADX WARN: Removed duplicated region for block: B:36:0x00e0  */
                /* JADX WARN: Removed duplicated region for block: B:53:0x016d  */
                /* JADX WARN: Removed duplicated region for block: B:56:0x0177 A[Catch: CancellationException -> 0x0189, TryCatch #1 {CancellationException -> 0x0189, blocks: (B:8:0x001b, B:54:0x016f, B:56:0x0177, B:57:0x017f), top: B:66:0x001b }] */
                /* JADX WARN: Removed duplicated region for block: B:57:0x017f A[Catch: CancellationException -> 0x0189, TRY_LEAVE, TryCatch #1 {CancellationException -> 0x0189, blocks: (B:8:0x001b, B:54:0x016f, B:56:0x0177, B:57:0x017f), top: B:66:0x001b }] */
                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct code enable 'Show inconsistent code' option in preferences
                */
                public final java.lang.Object invokeSuspend(java.lang.Object r15) {
                    /*
                        Method dump skipped, instruction units count: 437
                        To view this dump change 'Code comments level' option to 'DEBUG'
                    */
                    throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.SliderKt.C05691.C01201.C01211.invokeSuspend(java.lang.Object):java.lang.Object");
                }

                /* JADX INFO: renamed from: androidx.compose.material3.SliderKt$rangeSliderPressDragModifier$1$1$1$2, reason: invalid class name */
                /* JADX INFO: compiled from: Slider.kt */
                @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
                @DebugMetadata(c = "androidx.compose.material3.SliderKt$rangeSliderPressDragModifier$1$1$1$2", f = "Slider.kt", i = {}, l = {1705}, m = "invokeSuspend", n = {}, s = {})
                static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                    final /* synthetic */ Ref.BooleanRef $draggingStart;
                    final /* synthetic */ DragInteraction $finishInteraction;
                    final /* synthetic */ RangeSliderLogic $rangeSliderLogic;
                    int label;

                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    AnonymousClass2(RangeSliderLogic rangeSliderLogic, Ref.BooleanRef booleanRef, DragInteraction dragInteraction, Continuation<? super AnonymousClass2> continuation) {
                        super(2, continuation);
                        this.$rangeSliderLogic = rangeSliderLogic;
                        this.$draggingStart = booleanRef;
                        this.$finishInteraction = dragInteraction;
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                        return new AnonymousClass2(this.$rangeSliderLogic, this.$draggingStart, this.$finishInteraction, continuation);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                        return ((AnonymousClass2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Object invokeSuspend(Object obj) {
                        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                        int i = this.label;
                        if (i == 0) {
                            ResultKt.throwOnFailure(obj);
                            this.label = 1;
                            if (this.$rangeSliderLogic.activeInteraction(this.$draggingStart.element).emit(this.$finishInteraction, this) == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                        } else {
                            if (i != 1) {
                                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                            }
                            ResultKt.throwOnFailure(obj);
                        }
                        return Unit.INSTANCE;
                    }
                }
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    CoroutineScope coroutineScope = (CoroutineScope) this.L$0;
                    this.label = 1;
                    if (ForEachGestureKt.awaitEachGesture(this.$$this$pointerInput, new C01211(this.$state, this.$rangeSliderLogic, coroutineScope, null), this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                } else {
                    if (i != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                }
                return Unit.INSTANCE;
            }
        }
    }

    private static final Modifier rangeSliderPressDragModifier(Modifier modifier, RangeSliderState rangeSliderState, MutableInteractionSource mutableInteractionSource, MutableInteractionSource mutableInteractionSource2, boolean z) {
        return z ? SuspendingPointerInputFilterKt.pointerInput(modifier, new Object[]{mutableInteractionSource, mutableInteractionSource2, rangeSliderState}, (Function2<? super PointerInputScope, ? super Continuation<? super Unit>, ? extends Object>) new C05691(rangeSliderState, mutableInteractionSource, mutableInteractionSource2, null)) : modifier;
    }

    static {
        float fM3798getHandleWidthD9Ej5fM = SliderTokens.INSTANCE.m3798getHandleWidthD9Ej5fM();
        ThumbWidth = fM3798getHandleWidthD9Ej5fM;
        float fM3797getHandleHeightD9Ej5fM = SliderTokens.INSTANCE.m3797getHandleHeightD9Ej5fM();
        ThumbHeight = fM3797getHandleHeightD9Ej5fM;
        ThumbSize = DpKt.m7049DpSizeYgX7TsA(fM3798getHandleWidthD9Ej5fM, fM3797getHandleHeightD9Ej5fM);
        ThumbTrackGapSize = SliderTokens.INSTANCE.m3790getActiveHandleLeadingSpaceD9Ej5fM();
        TrackInsideCornerSize = Dp.m7027constructorimpl(2);
    }

    public static final float getTrackHeight() {
        return TrackHeight;
    }

    public static final float getThumbWidth() {
        return ThumbWidth;
    }

    public static final long SliderRange(float f, float f2) {
        if ((!Float.isNaN(f) || !Float.isNaN(f2)) && f > ((double) f2) + SliderRangeTolerance) {
            throw new IllegalArgumentException(("start(" + f + ") must be <= endInclusive(" + f2 + ')').toString());
        }
        return SliderRange.m2818constructorimpl((((long) Float.floatToRawIntBits(f2)) & 4294967295L) | (Float.floatToRawIntBits(f) << 32));
    }

    public static final long SliderRange(ClosedFloatingPointRange<Float> closedFloatingPointRange) {
        float fFloatValue = closedFloatingPointRange.getStart().floatValue();
        float fFloatValue2 = closedFloatingPointRange.getEndInclusive().floatValue();
        if ((!Float.isNaN(fFloatValue) || !Float.isNaN(fFloatValue2)) && fFloatValue > ((double) fFloatValue2) + SliderRangeTolerance) {
            throw new IllegalArgumentException(("ClosedFloatingPointRange<Float>.start(" + fFloatValue + ") must be <= ClosedFloatingPoint.endInclusive(" + fFloatValue2 + ')').toString());
        }
        return SliderRange.m2818constructorimpl((((long) Float.floatToRawIntBits(fFloatValue)) << 32) | (((long) Float.floatToRawIntBits(fFloatValue2)) & 4294967295L));
    }

    /* JADX INFO: renamed from: isSpecified-If1S1O4, reason: not valid java name */
    public static final boolean m2808isSpecifiedIf1S1O4(long j) {
        return j != SliderRange.INSTANCE.m2827getUnspecifiedFYbKRX4();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Type inference failed for: r1v1, types: [kotlin.collections.IntIterator] */
    public static final float snapValueToTick(float f, float[] fArr, float f2, float f3) {
        Float fValueOf;
        if (fArr.length == 0) {
            fValueOf = null;
        } else {
            float f4 = fArr[0];
            int lastIndex = ArraysKt.getLastIndex(fArr);
            if (lastIndex != 0) {
                float fAbs = Math.abs(MathHelpersKt.lerp(f2, f3, f4) - f);
                ?? it = new IntRange(1, lastIndex).iterator();
                while (it.hasNext()) {
                    float f5 = fArr[it.nextInt()];
                    float fAbs2 = Math.abs(MathHelpersKt.lerp(f2, f3, f5) - f);
                    if (Float.compare(fAbs, fAbs2) > 0) {
                        f4 = f5;
                        fAbs = fAbs2;
                    }
                }
                fValueOf = Float.valueOf(f4);
            } else {
                fValueOf = Float.valueOf(f4);
            }
        }
        return fValueOf != null ? MathHelpersKt.lerp(f2, f3, fValueOf.floatValue()) : f;
    }
}
