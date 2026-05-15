package androidx.compose.material;

import androidx.compose.animation.core.Animatable;
import androidx.compose.animation.core.AnimatableKt;
import androidx.compose.animation.core.AnimationVector1D;
import androidx.compose.animation.core.TweenSpec;
import androidx.compose.foundation.BackgroundKt;
import androidx.compose.foundation.CanvasKt;
import androidx.compose.foundation.FocusableKt;
import androidx.compose.foundation.HoverableKt;
import androidx.compose.foundation.IndicationKt;
import androidx.compose.foundation.ProgressSemanticsKt;
import androidx.compose.foundation.gestures.DragScope;
import androidx.compose.foundation.gestures.DraggableKt;
import androidx.compose.foundation.gestures.DraggableState;
import androidx.compose.foundation.gestures.ForEachGestureKt;
import androidx.compose.foundation.gestures.Orientation;
import androidx.compose.foundation.interaction.DragInteraction;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.BoxScope;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.foundation.layout.BoxWithConstraintsScope;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.layout.SpacerKt;
import androidx.compose.foundation.shape.RoundedCornerShapeKt;
import androidx.compose.material.SliderKt;
import androidx.compose.runtime.Applier;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.CompositionScopedCoroutineScopeCanceller;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.MutableFloatState;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.PrimitiveSnapshotStateKt;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.snapshots.SnapshotStateList;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.draw.ShadowKt;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.OffsetKt;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.PointMode;
import androidx.compose.ui.graphics.StrokeCap;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.input.pointer.AwaitPointerEventScope;
import androidx.compose.ui.input.pointer.PointerInputScope;
import androidx.compose.ui.input.pointer.SuspendingPointerInputFilterKt;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.platform.InspectableValueKt;
import androidx.compose.ui.platform.InspectorInfo;
import androidx.compose.ui.semantics.SemanticsModifierKt;
import androidx.compose.ui.semantics.SemanticsPropertiesKt;
import androidx.compose.ui.semantics.SemanticsPropertyReceiver;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.LayoutDirection;
import androidx.compose.ui.util.MathHelpersKt;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.NotificationCompat;
import androidx.core.view.accessibility.AccessibilityEventCompat;
import androidx.profileinstaller.ProfileVerifier;
import androidx.savedstate.serialization.ClassDiscriminatorModeKt;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.RestrictedSuspendLambda;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.Ref;
import kotlin.ranges.ClosedFloatingPointRange;
import kotlin.ranges.RangesKt;
import kotlin.reflect.KFunction;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import org.simpleframework.xml.strategy.Name;

/* JADX INFO: compiled from: Slider.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u009e\u0001\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0002\b\t\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0002\b\u0014\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\u001aS\u0010\u0011\u001a\u00020\u00122\u0012\u0010\u0013\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\b0\u00142\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\b0\u00162\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\b0\u00162\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\b0\u00192\u0006\u0010\u001a\u001a\u00020\bH\u0003¢\u0006\u0002\u0010\u001b\u001a\u007f\u0010\u001c\u001a\u00020\u00122\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\b0\u00162\u0018\u0010\u001d\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u0016\u0012\u0004\u0012\u00020\u00120\u00142\b\b\u0002\u0010\u001e\u001a\u00020\u00012\b\b\u0002\u0010\u001f\u001a\u00020 2\u000e\b\u0002\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\b0\u00162\b\b\u0003\u0010!\u001a\u00020\"2\u0010\b\u0002\u0010#\u001a\n\u0012\u0004\u0012\u00020\u0012\u0018\u00010$2\b\b\u0002\u0010%\u001a\u00020&H\u0007¢\u0006\u0002\u0010'\u001ak\u0010(\u001a\u00020\u00122\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010)\u001a\u00020\b2\u0006\u0010*\u001a\u00020\b2\f\u0010+\u001a\b\u0012\u0004\u0012\u00020\b0,2\u0006\u0010%\u001a\u00020&2\u0006\u0010-\u001a\u00020\b2\u0006\u0010.\u001a\u00020/2\u0006\u00100\u001a\u00020/2\u0006\u0010\u001e\u001a\u00020\u00012\u0006\u00101\u001a\u00020\u00012\u0006\u00102\u001a\u00020\u0001H\u0003¢\u0006\u0002\u00103\u001a\u007f\u00104\u001a\u00020\u00122\u0006\u0010\u001a\u001a\u00020\b2\u0012\u0010\u001d\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u00120\u00142\b\b\u0002\u0010\u001e\u001a\u00020\u00012\b\b\u0002\u0010\u001f\u001a\u00020 2\u000e\b\u0002\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\b0\u00162\b\b\u0003\u0010!\u001a\u00020\"2\u0010\b\u0002\u0010#\u001a\n\u0012\u0004\u0012\u00020\u0012\u0018\u00010$2\n\b\u0002\u00105\u001a\u0004\u0018\u00010/2\b\b\u0002\u0010%\u001a\u00020&H\u0007¢\u0006\u0002\u00106\u001aK\u00107\u001a\u00020\u00122\u0006\u0010\u001f\u001a\u00020 2\u0006\u00108\u001a\u00020\b2\f\u0010+\u001a\b\u0012\u0004\u0012\u00020\b0,2\u0006\u0010%\u001a\u00020&2\u0006\u0010-\u001a\u00020\b2\u0006\u00105\u001a\u00020/2\u0006\u0010\u001e\u001a\u00020\u0001H\u0003¢\u0006\u0002\u00109\u001aS\u0010:\u001a\u00020\u00122\u0006\u0010\u001e\u001a\u00020\u00012\u0006\u0010%\u001a\u00020&2\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010)\u001a\u00020\b2\u0006\u0010*\u001a\u00020\b2\f\u0010+\u001a\b\u0012\u0004\u0012\u00020\b0,2\u0006\u0010;\u001a\u00020\b2\u0006\u0010<\u001a\u00020\bH\u0003¢\u0006\u0002\u0010=\u001a.\u0010>\u001a\u00020\u00122\u0006\u0010?\u001a\u00020@2\u0006\u0010A\u001a\u00020\b2\u0006\u0010B\u001a\u00020\b2\u0006\u0010C\u001a\u00020\bH\u0082@¢\u0006\u0002\u0010D\u001a \u0010E\u001a\u00020\b2\u0006\u0010F\u001a\u00020\b2\u0006\u0010G\u001a\u00020\b2\u0006\u0010H\u001a\u00020\bH\u0002\u001a0\u0010I\u001a\u00020\b2\u0006\u0010J\u001a\u00020\b2\u0006\u0010K\u001a\u00020\b2\u0006\u0010L\u001a\u00020\b2\u0006\u0010M\u001a\u00020\b2\u0006\u0010N\u001a\u00020\bH\u0002\u001a<\u0010I\u001a\b\u0012\u0004\u0012\u00020\b0\u00162\u0006\u0010J\u001a\u00020\b2\u0006\u0010K\u001a\u00020\b2\f\u0010O\u001a\b\u0012\u0004\u0012\u00020\b0\u00162\u0006\u0010M\u001a\u00020\b2\u0006\u0010N\u001a\u00020\bH\u0002\u001a.\u0010P\u001a\u00020\b2\u0006\u0010A\u001a\u00020\b2\f\u0010+\u001a\b\u0012\u0004\u0012\u00020\b0,2\u0006\u0010Q\u001a\u00020\b2\u0006\u0010R\u001a\u00020\bH\u0002\u001a\u0016\u0010S\u001a\b\u0012\u0004\u0012\u00020\b0,2\u0006\u0010!\u001a\u00020\"H\u0002\u001aF\u0010T\u001a\u00020\u0012*\u00020U2\u0006\u0010\u001e\u001a\u00020\u00012\u0006\u0010V\u001a\u00020\u00032\u0006\u00105\u001a\u00020/2\u0006\u0010%\u001a\u00020&2\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010W\u001a\u00020\u0003H\u0003ø\u0001\u0000¢\u0006\u0004\bX\u0010Y\u001a5\u0010Z\u001a\u0010\u0012\u0004\u0012\u00020\\\u0012\u0004\u0012\u00020\b\u0018\u00010[*\u00020]2\u0006\u0010^\u001a\u00020_2\u0006\u0010`\u001a\u00020aH\u0082@ø\u0001\u0000¢\u0006\u0004\bb\u0010c\u001a\u0098\u0001\u0010d\u001a\u00020\u0001*\u00020\u00012\u0006\u0010.\u001a\u00020/2\u0006\u00100\u001a\u00020/2\f\u0010e\u001a\b\u0012\u0004\u0012\u00020\b0f2\f\u0010g\u001a\b\u0012\u0004\u0012\u00020\b0f2\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010h\u001a\u00020 2\u0006\u0010R\u001a\u00020\b2\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\b0\u00162\u0018\u0010i\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020 \u0012\u0004\u0012\u00020\u00120\u00140f2\u001e\u0010j\u001a\u001a\u0012\u0016\u0012\u0014\u0012\u0004\u0012\u00020 \u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u00120k0fH\u0002\u001a\\\u0010l\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u001a\u001a\u00020\b2\u0006\u0010\u001f\u001a\u00020 2\u0012\u0010\u001d\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u00120\u00142\u0010\b\u0002\u0010#\u001a\n\u0012\u0004\u0012\u00020\u0012\u0018\u00010$2\u000e\b\u0002\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\b0\u00162\b\b\u0002\u0010!\u001a\u00020\"H\u0002\u001aj\u0010m\u001a\u00020\u0001*\u00020\u00012\u0006\u0010?\u001a\u00020@2\u0006\u00105\u001a\u00020/2\u0006\u0010R\u001a\u00020\b2\u0006\u0010h\u001a\u00020 2\f\u0010n\u001a\b\u0012\u0004\u0012\u00020\b0f2\u0018\u0010i\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u00120\u00140f2\f\u0010o\u001a\b\u0012\u0004\u0012\u00020\b0\u00192\u0006\u0010\u001f\u001a\u00020 H\u0002\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"\u0010\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0004\"\u0010\u0010\u0005\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0004\"\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000\"\u0010\u0010\t\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0004\"\u0010\u0010\n\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0004\"\u0016\u0010\u000b\u001a\u00020\u0003X\u0080\u0004¢\u0006\n\n\u0002\u0010\u0004\u001a\u0004\b\f\u0010\r\"\u0010\u0010\u000e\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0004\"\u0016\u0010\u000f\u001a\u00020\u0003X\u0080\u0004¢\u0006\n\n\u0002\u0010\u0004\u001a\u0004\b\u0010\u0010\r\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006p"}, d2 = {"DefaultSliderConstraints", "Landroidx/compose/ui/Modifier;", "SliderHeight", "Landroidx/compose/ui/unit/Dp;", "F", "SliderMinWidth", "SliderToTickAnimation", "Landroidx/compose/animation/core/TweenSpec;", "", "ThumbDefaultElevation", "ThumbPressedElevation", "ThumbRadius", "getThumbRadius", "()F", "ThumbRippleRadius", "TrackHeight", "getTrackHeight", "CorrectValueSideEffect", "", "scaleToOffset", "Lkotlin/Function1;", "valueRange", "Lkotlin/ranges/ClosedFloatingPointRange;", "trackRange", "valueState", "Landroidx/compose/runtime/MutableState;", "value", "(Lkotlin/jvm/functions/Function1;Lkotlin/ranges/ClosedFloatingPointRange;Lkotlin/ranges/ClosedFloatingPointRange;Landroidx/compose/runtime/MutableState;FLandroidx/compose/runtime/Composer;I)V", "RangeSlider", "onValueChange", "modifier", "enabled", "", "steps", "", "onValueChangeFinished", "Lkotlin/Function0;", "colors", "Landroidx/compose/material/SliderColors;", "(Lkotlin/ranges/ClosedFloatingPointRange;Lkotlin/jvm/functions/Function1;Landroidx/compose/ui/Modifier;ZLkotlin/ranges/ClosedFloatingPointRange;ILkotlin/jvm/functions/Function0;Landroidx/compose/material/SliderColors;Landroidx/compose/runtime/Composer;II)V", "RangeSliderImpl", "positionFractionStart", "positionFractionEnd", "tickFractions", "", "width", "startInteractionSource", "Landroidx/compose/foundation/interaction/MutableInteractionSource;", "endInteractionSource", "startThumbSemantics", "endThumbSemantics", "(ZFFLjava/util/List;Landroidx/compose/material/SliderColors;FLandroidx/compose/foundation/interaction/MutableInteractionSource;Landroidx/compose/foundation/interaction/MutableInteractionSource;Landroidx/compose/ui/Modifier;Landroidx/compose/ui/Modifier;Landroidx/compose/ui/Modifier;Landroidx/compose/runtime/Composer;II)V", "Slider", "interactionSource", "(FLkotlin/jvm/functions/Function1;Landroidx/compose/ui/Modifier;ZLkotlin/ranges/ClosedFloatingPointRange;ILkotlin/jvm/functions/Function0;Landroidx/compose/foundation/interaction/MutableInteractionSource;Landroidx/compose/material/SliderColors;Landroidx/compose/runtime/Composer;II)V", "SliderImpl", "positionFraction", "(ZFLjava/util/List;Landroidx/compose/material/SliderColors;FLandroidx/compose/foundation/interaction/MutableInteractionSource;Landroidx/compose/ui/Modifier;Landroidx/compose/runtime/Composer;I)V", "Track", "thumbPx", "trackStrokeWidth", "(Landroidx/compose/ui/Modifier;Landroidx/compose/material/SliderColors;ZFFLjava/util/List;FFLandroidx/compose/runtime/Composer;I)V", "animateToTarget", "draggableState", "Landroidx/compose/foundation/gestures/DraggableState;", "current", TypedValues.AttributesType.S_TARGET, "velocity", "(Landroidx/compose/foundation/gestures/DraggableState;FFFLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "calcFraction", "a", "b", "pos", "scale", "a1", "b1", "x1", "a2", "b2", "x", "snapValueToTick", "minPx", "maxPx", "stepsToTickFractions", "SliderThumb", "Landroidx/compose/foundation/layout/BoxScope;", TypedValues.CycleType.S_WAVE_OFFSET, "thumbSize", "SliderThumb-PcYyNuk", "(Landroidx/compose/foundation/layout/BoxScope;Landroidx/compose/ui/Modifier;FLandroidx/compose/foundation/interaction/MutableInteractionSource;Landroidx/compose/material/SliderColors;ZFLandroidx/compose/runtime/Composer;I)V", "awaitSlop", "Lkotlin/Pair;", "Landroidx/compose/ui/input/pointer/PointerInputChange;", "Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;", Name.MARK, "Landroidx/compose/ui/input/pointer/PointerId;", ClassDiscriminatorModeKt.CLASS_DISCRIMINATOR_KEY, "Landroidx/compose/ui/input/pointer/PointerType;", "awaitSlop-8vUncbI", "(Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;JILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "rangeSliderPressDragModifier", "rawOffsetStart", "Landroidx/compose/runtime/State;", "rawOffsetEnd", "isRtl", "gestureEndAction", "onDrag", "Lkotlin/Function2;", "sliderSemantics", "sliderTapModifier", "rawOffset", "pressOffset", "material_release"}, k = 2, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
public final class SliderKt {
    private static final Modifier DefaultSliderConstraints;
    private static final float SliderHeight;
    private static final float SliderMinWidth;
    private static final TweenSpec<Float> SliderToTickAnimation;
    private static final float ThumbRadius = Dp.m7027constructorimpl(10);
    private static final float ThumbRippleRadius = Dp.m7027constructorimpl(24);
    private static final float ThumbDefaultElevation = Dp.m7027constructorimpl(1);
    private static final float ThumbPressedElevation = Dp.m7027constructorimpl(6);
    private static final float TrackHeight = Dp.m7027constructorimpl(4);

    /* JADX INFO: Access modifiers changed from: private */
    public static final float calcFraction(float f, float f2, float f3) {
        float f4 = f2 - f;
        float f5 = f4 == 0.0f ? 0.0f : (f3 - f) / f4;
        float f6 = f5 >= 0.0f ? f5 : 0.0f;
        if (f6 > 1.0f) {
            return 1.0f;
        }
        return f6;
    }

    /* JADX WARN: Removed duplicated region for block: B:100:0x011f  */
    /* JADX WARN: Removed duplicated region for block: B:104:0x0134  */
    /* JADX WARN: Removed duplicated region for block: B:116:0x0167  */
    /* JADX WARN: Removed duplicated region for block: B:117:0x0169  */
    /* JADX WARN: Removed duplicated region for block: B:119:0x0170  */
    /* JADX WARN: Removed duplicated region for block: B:122:0x0175  */
    /* JADX WARN: Removed duplicated region for block: B:123:0x017f  */
    /* JADX WARN: Removed duplicated region for block: B:125:0x0182  */
    /* JADX WARN: Removed duplicated region for block: B:126:0x0184  */
    /* JADX WARN: Removed duplicated region for block: B:129:0x0188  */
    /* JADX WARN: Removed duplicated region for block: B:130:0x018b  */
    /* JADX WARN: Removed duplicated region for block: B:133:0x0190  */
    /* JADX WARN: Removed duplicated region for block: B:136:0x0196  */
    /* JADX WARN: Removed duplicated region for block: B:137:0x01c4  */
    /* JADX WARN: Removed duplicated region for block: B:140:0x01d8  */
    /* JADX WARN: Removed duplicated region for block: B:143:0x01e5  */
    /* JADX WARN: Removed duplicated region for block: B:147:0x0212  */
    /* JADX WARN: Removed duplicated region for block: B:149:0x021e  */
    /* JADX WARN: Removed duplicated region for block: B:164:0x02e1  */
    /* JADX WARN: Removed duplicated region for block: B:166:0x02f0  */
    /* JADX WARN: Removed duplicated region for block: B:168:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0069  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x006c  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x0085  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x009a  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x00a2  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x00a4  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x00bf  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x00c4  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x00dd  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x00e2  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x00fb  */
    /* JADX WARN: Removed duplicated region for block: B:97:0x0111  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final void Slider(final float r40, final kotlin.jvm.functions.Function1<? super java.lang.Float, kotlin.Unit> r41, androidx.compose.ui.Modifier r42, boolean r43, kotlin.ranges.ClosedFloatingPointRange<java.lang.Float> r44, int r45, kotlin.jvm.functions.Function0<kotlin.Unit> r46, androidx.compose.foundation.interaction.MutableInteractionSource r47, androidx.compose.material.SliderColors r48, androidx.compose.runtime.Composer r49, final int r50, final int r51) {
        /*
            Method dump skipped, instruction units count: 765
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material.SliderKt.Slider(float, kotlin.jvm.functions.Function1, androidx.compose.ui.Modifier, boolean, kotlin.ranges.ClosedFloatingPointRange, int, kotlin.jvm.functions.Function0, androidx.compose.foundation.interaction.MutableInteractionSource, androidx.compose.material.SliderColors, androidx.compose.runtime.Composer, int, int):void");
    }

    /* JADX INFO: renamed from: androidx.compose.material.SliderKt$Slider$2, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: Slider.kt */
    @Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u000b¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Landroidx/compose/foundation/layout/BoxWithConstraintsScope;", "invoke", "(Landroidx/compose/foundation/layout/BoxWithConstraintsScope;Landroidx/compose/runtime/Composer;I)V"}, k = 3, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    static final class C04472 extends Lambda implements Function3<BoxWithConstraintsScope, Composer, Integer, Unit> {
        final /* synthetic */ SliderColors $colors;
        final /* synthetic */ boolean $enabled;
        final /* synthetic */ MutableInteractionSource $interactionSource;
        final /* synthetic */ Function0<Unit> $onValueChangeFinished;
        final /* synthetic */ State<Function1<Float, Unit>> $onValueChangeState;
        final /* synthetic */ List<Float> $tickFractions;
        final /* synthetic */ float $value;
        final /* synthetic */ ClosedFloatingPointRange<Float> $valueRange;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        C04472(ClosedFloatingPointRange<Float> closedFloatingPointRange, float f, List<Float> list, Function0<Unit> function0, MutableInteractionSource mutableInteractionSource, boolean z, SliderColors sliderColors, State<? extends Function1<? super Float, Unit>> state) {
            super(3);
            this.$valueRange = closedFloatingPointRange;
            this.$value = f;
            this.$tickFractions = list;
            this.$onValueChangeFinished = function0;
            this.$interactionSource = mutableInteractionSource;
            this.$enabled = z;
            this.$colors = sliderColors;
            this.$onValueChangeState = state;
        }

        @Override // kotlin.jvm.functions.Function3
        public /* bridge */ /* synthetic */ Unit invoke(BoxWithConstraintsScope boxWithConstraintsScope, Composer composer, Integer num) {
            invoke(boxWithConstraintsScope, composer, num.intValue());
            return Unit.INSTANCE;
        }

        public final void invoke(BoxWithConstraintsScope boxWithConstraintsScope, Composer composer, int i) {
            BoxWithConstraintsScope boxWithConstraintsScope2;
            int i2;
            MutableFloatState mutableFloatState;
            Ref.FloatRef floatRef;
            Ref.FloatRef floatRef2;
            final SliderDraggableState sliderDraggableState;
            Ref.FloatRef floatRef3;
            Ref.FloatRef floatRef4;
            ComposerKt.sourceInformation(composer, "C181@8427L7,*186@8588L7,197@9002L24,198@9051L54,199@9132L36,201@9199L392,210@9624L15,210@9601L83,212@9755L585,212@9717L623,241@10834L55,248@11175L209:Slider.kt#jmzs0o");
            if ((i & 6) == 0) {
                boxWithConstraintsScope2 = boxWithConstraintsScope;
                i2 = i | (composer.changed(boxWithConstraintsScope2) ? 4 : 2);
            } else {
                boxWithConstraintsScope2 = boxWithConstraintsScope;
                i2 = i;
            }
            if ((i2 & 19) != 18 || !composer.getSkipping()) {
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(2085116814, i2, -1, "androidx.compose.material.Slider.<anonymous> (Slider.kt:181)");
                }
                ProvidableCompositionLocal<LayoutDirection> localLayoutDirection = CompositionLocalsKt.getLocalLayoutDirection();
                ComposerKt.sourceInformationMarkerStart(composer, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                Object objConsume = composer.consume(localLayoutDirection);
                ComposerKt.sourceInformationMarkerEnd(composer);
                boolean z = objConsume == LayoutDirection.Rtl;
                float fM6980getMaxWidthimpl = Constraints.m6980getMaxWidthimpl(boxWithConstraintsScope2.mo872getConstraintsmsEJaDk());
                final Ref.FloatRef floatRef5 = new Ref.FloatRef();
                final Ref.FloatRef floatRef6 = new Ref.FloatRef();
                ProvidableCompositionLocal<Density> localDensity = CompositionLocalsKt.getLocalDensity();
                ComposerKt.sourceInformationMarkerStart(composer, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                Object objConsume2 = composer.consume(localDensity);
                ComposerKt.sourceInformationMarkerEnd(composer);
                Density density = (Density) objConsume2;
                floatRef5.element = Math.max(fM6980getMaxWidthimpl - density.mo657toPx0680j_4(SliderKt.getThumbRadius()), 0.0f);
                floatRef6.element = Math.min(density.mo657toPx0680j_4(SliderKt.getThumbRadius()), floatRef5.element);
                ComposerKt.sourceInformationMarkerStart(composer, 773894976, "CC(rememberCoroutineScope)489@20472L144:Effects.kt#9igjgp");
                ComposerKt.sourceInformationMarkerStart(composer, -954363344, "CC(remember):Effects.kt#9igjgp");
                Object objRememberedValue = composer.rememberedValue();
                if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                    Object compositionScopedCoroutineScopeCanceller = new CompositionScopedCoroutineScopeCanceller(EffectsKt.createCompositionCoroutineScope(EmptyCoroutineContext.INSTANCE, composer));
                    composer.updateRememberedValue(compositionScopedCoroutineScopeCanceller);
                    objRememberedValue = compositionScopedCoroutineScopeCanceller;
                }
                ComposerKt.sourceInformationMarkerEnd(composer);
                final CoroutineScope coroutineScope = ((CompositionScopedCoroutineScopeCanceller) objRememberedValue).getCoroutineScope();
                ComposerKt.sourceInformationMarkerEnd(composer);
                ComposerKt.sourceInformationMarkerStart(composer, -2058992228, "CC(remember):Slider.kt#9igjgp");
                float f = this.$value;
                ClosedFloatingPointRange<Float> closedFloatingPointRange = this.$valueRange;
                Object objRememberedValue2 = composer.rememberedValue();
                if (objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue2 = PrimitiveSnapshotStateKt.mutableFloatStateOf(invoke$scaleToOffset(closedFloatingPointRange, floatRef6, floatRef5, f));
                    composer.updateRememberedValue(objRememberedValue2);
                }
                final MutableFloatState mutableFloatState2 = (MutableFloatState) objRememberedValue2;
                ComposerKt.sourceInformationMarkerEnd(composer);
                ComposerKt.sourceInformationMarkerStart(composer, -2058989654, "CC(remember):Slider.kt#9igjgp");
                Object objRememberedValue3 = composer.rememberedValue();
                if (objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue3 = PrimitiveSnapshotStateKt.mutableFloatStateOf(0.0f);
                    composer.updateRememberedValue(objRememberedValue3);
                }
                final MutableFloatState mutableFloatState3 = (MutableFloatState) objRememberedValue3;
                ComposerKt.sourceInformationMarkerEnd(composer);
                ComposerKt.sourceInformationMarkerStart(composer, -2058987154, "CC(remember):Slider.kt#9igjgp");
                boolean zChanged = composer.changed(floatRef6.element) | composer.changed(floatRef5.element) | composer.changed(this.$valueRange);
                final State<Function1<Float, Unit>> state = this.$onValueChangeState;
                final ClosedFloatingPointRange<Float> closedFloatingPointRange2 = this.$valueRange;
                Object objRememberedValue4 = composer.rememberedValue();
                if (zChanged || objRememberedValue4 == Composer.INSTANCE.getEmpty()) {
                    mutableFloatState = mutableFloatState3;
                    floatRef = floatRef6;
                    floatRef2 = floatRef5;
                    objRememberedValue4 = new SliderDraggableState(new Function1<Float, Unit>() { // from class: androidx.compose.material.SliderKt$Slider$2$draggableState$1$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        /* JADX WARN: Multi-variable type inference failed */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(Float f2) {
                            invoke(f2.floatValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(float f2) {
                            MutableFloatState mutableFloatState4 = mutableFloatState2;
                            mutableFloatState4.setFloatValue(mutableFloatState4.getFloatValue() + f2 + mutableFloatState3.getFloatValue());
                            mutableFloatState3.setFloatValue(0.0f);
                            state.getValue().invoke(Float.valueOf(SliderKt.C04472.invoke$scaleToUserValue(floatRef6, floatRef5, closedFloatingPointRange2, RangesKt.coerceIn(mutableFloatState2.getFloatValue(), floatRef6.element, floatRef5.element))));
                        }
                    });
                    composer.updateRememberedValue(objRememberedValue4);
                } else {
                    floatRef2 = floatRef5;
                    floatRef = floatRef6;
                    mutableFloatState = mutableFloatState3;
                }
                SliderDraggableState sliderDraggableState2 = (SliderDraggableState) objRememberedValue4;
                ComposerKt.sourceInformationMarkerEnd(composer);
                ComposerKt.sourceInformationMarkerStart(composer, -2058973931, "CC(remember):Slider.kt#9igjgp");
                boolean zChanged2 = composer.changed(this.$valueRange) | composer.changed(floatRef.element) | composer.changed(floatRef2.element);
                ClosedFloatingPointRange<Float> closedFloatingPointRange3 = this.$valueRange;
                Object objRememberedValue5 = composer.rememberedValue();
                if (zChanged2 || objRememberedValue5 == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue5 = (KFunction) new SliderKt$Slider$2$2$1(closedFloatingPointRange3, floatRef, floatRef2);
                    composer.updateRememberedValue(objRememberedValue5);
                }
                ComposerKt.sourceInformationMarkerEnd(composer);
                SliderKt.CorrectValueSideEffect((Function1) ((KFunction) objRememberedValue5), this.$valueRange, RangesKt.rangeTo(floatRef.element, floatRef2.element), mutableFloatState2, this.$value, composer, 3072);
                ComposerKt.sourceInformationMarkerStart(composer, -2058969169, "CC(remember):Slider.kt#9igjgp");
                boolean zChangedInstance = composer.changedInstance(this.$tickFractions) | composer.changed(floatRef.element) | composer.changed(floatRef2.element) | composer.changedInstance(coroutineScope) | composer.changedInstance(sliderDraggableState2) | composer.changed(this.$onValueChangeFinished);
                final List<Float> list = this.$tickFractions;
                final Function0<Unit> function0 = this.$onValueChangeFinished;
                Object objRememberedValue6 = composer.rememberedValue();
                if (zChangedInstance || objRememberedValue6 == Composer.INSTANCE.getEmpty()) {
                    final Ref.FloatRef floatRef7 = floatRef;
                    sliderDraggableState = sliderDraggableState2;
                    final Ref.FloatRef floatRef8 = floatRef2;
                    Object obj = new Function1<Float, Unit>() { // from class: androidx.compose.material.SliderKt$Slider$2$gestureEndAction$1$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(Float f2) {
                            invoke(f2.floatValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(float f2) {
                            Function0<Unit> function02;
                            float floatValue = mutableFloatState2.getFloatValue();
                            float fSnapValueToTick = SliderKt.snapValueToTick(floatValue, list, floatRef7.element, floatRef8.element);
                            if (floatValue != fSnapValueToTick) {
                                BuildersKt__Builders_commonKt.launch$default(coroutineScope, null, null, new AnonymousClass1(sliderDraggableState, floatValue, fSnapValueToTick, f2, function0, null), 3, null);
                            } else {
                                if (sliderDraggableState.isDragging() || (function02 = function0) == null) {
                                    return;
                                }
                                function02.invoke();
                            }
                        }

                        /* JADX INFO: renamed from: androidx.compose.material.SliderKt$Slider$2$gestureEndAction$1$1$1, reason: invalid class name */
                        /* JADX INFO: compiled from: Slider.kt */
                        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
                        @DebugMetadata(c = "androidx.compose.material.SliderKt$Slider$2$gestureEndAction$1$1$1", f = "Slider.kt", i = {}, l = {218}, m = "invokeSuspend", n = {}, s = {})
                        static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                            final /* synthetic */ float $current;
                            final /* synthetic */ SliderDraggableState $draggableState;
                            final /* synthetic */ Function0<Unit> $onValueChangeFinished;
                            final /* synthetic */ float $target;
                            final /* synthetic */ float $velocity;
                            int label;

                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            AnonymousClass1(SliderDraggableState sliderDraggableState, float f, float f2, float f3, Function0<Unit> function0, Continuation<? super AnonymousClass1> continuation) {
                                super(2, continuation);
                                this.$draggableState = sliderDraggableState;
                                this.$current = f;
                                this.$target = f2;
                                this.$velocity = f3;
                                this.$onValueChangeFinished = function0;
                            }

                            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                                return new AnonymousClass1(this.$draggableState, this.$current, this.$target, this.$velocity, this.$onValueChangeFinished, continuation);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                                return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                            }

                            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                            public final Object invokeSuspend(Object obj) {
                                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                                int i = this.label;
                                if (i == 0) {
                                    ResultKt.throwOnFailure(obj);
                                    this.label = 1;
                                    if (SliderKt.animateToTarget(this.$draggableState, this.$current, this.$target, this.$velocity, this) == coroutine_suspended) {
                                        return coroutine_suspended;
                                    }
                                } else {
                                    if (i != 1) {
                                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                                    }
                                    ResultKt.throwOnFailure(obj);
                                }
                                Function0<Unit> function0 = this.$onValueChangeFinished;
                                if (function0 != null) {
                                    function0.invoke();
                                }
                                return Unit.INSTANCE;
                            }
                        }
                    };
                    floatRef3 = floatRef7;
                    floatRef4 = floatRef8;
                    objRememberedValue6 = (Function1) obj;
                    composer.updateRememberedValue(objRememberedValue6);
                } else {
                    sliderDraggableState = sliderDraggableState2;
                    floatRef4 = floatRef2;
                    floatRef3 = floatRef;
                }
                ComposerKt.sourceInformationMarkerEnd(composer);
                State stateRememberUpdatedState = SnapshotStateKt.rememberUpdatedState((Function1) objRememberedValue6, composer, 0);
                SliderDraggableState sliderDraggableState3 = sliderDraggableState;
                Modifier modifierSliderTapModifier = SliderKt.sliderTapModifier(Modifier.INSTANCE, sliderDraggableState3, this.$interactionSource, fM6980getMaxWidthimpl, z, mutableFloatState2, stateRememberUpdatedState, mutableFloatState, this.$enabled);
                Modifier.Companion companion = Modifier.INSTANCE;
                Orientation orientation = Orientation.Horizontal;
                boolean zIsDragging = sliderDraggableState.isDragging();
                Modifier.Companion companion2 = companion;
                boolean z2 = this.$enabled;
                boolean z3 = z;
                MutableInteractionSource mutableInteractionSource = this.$interactionSource;
                ComposerKt.sourceInformationMarkerStart(composer, -2058935171, "CC(remember):Slider.kt#9igjgp");
                boolean zChanged3 = composer.changed(stateRememberUpdatedState);
                Object objRememberedValue7 = composer.rememberedValue();
                if (zChanged3 || objRememberedValue7 == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue7 = (Function3) new SliderKt$Slider$2$drag$1$1(stateRememberUpdatedState, null);
                    composer.updateRememberedValue(objRememberedValue7);
                }
                ComposerKt.sourceInformationMarkerEnd(composer);
                SliderKt.SliderImpl(this.$enabled, SliderKt.calcFraction(this.$valueRange.getStart().floatValue(), this.$valueRange.getEndInclusive().floatValue(), RangesKt.coerceIn(this.$value, this.$valueRange.getStart().floatValue(), this.$valueRange.getEndInclusive().floatValue())), this.$tickFractions, this.$colors, floatRef4.element - floatRef3.element, this.$interactionSource, modifierSliderTapModifier.then(DraggableKt.draggable$default(companion2, sliderDraggableState3, orientation, z2, mutableInteractionSource, zIsDragging, null, (Function3) objRememberedValue7, z3, 32, null)), composer, 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                    return;
                }
                return;
            }
            composer.skipToGroupEnd();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final float invoke$scaleToUserValue(Ref.FloatRef floatRef, Ref.FloatRef floatRef2, ClosedFloatingPointRange<Float> closedFloatingPointRange, float f) {
            return SliderKt.scale(floatRef.element, floatRef2.element, f, closedFloatingPointRange.getStart().floatValue(), closedFloatingPointRange.getEndInclusive().floatValue());
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final float invoke$scaleToOffset(ClosedFloatingPointRange<Float> closedFloatingPointRange, Ref.FloatRef floatRef, Ref.FloatRef floatRef2, float f) {
            return SliderKt.scale(closedFloatingPointRange.getStart().floatValue(), closedFloatingPointRange.getEndInclusive().floatValue(), f, floatRef.element, floatRef2.element);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:105:0x0146  */
    /* JADX WARN: Removed duplicated region for block: B:106:0x0148  */
    /* JADX WARN: Removed duplicated region for block: B:108:0x014f  */
    /* JADX WARN: Removed duplicated region for block: B:109:0x0151  */
    /* JADX WARN: Removed duplicated region for block: B:112:0x0156  */
    /* JADX WARN: Removed duplicated region for block: B:113:0x0160  */
    /* JADX WARN: Removed duplicated region for block: B:115:0x0163  */
    /* JADX WARN: Removed duplicated region for block: B:116:0x0166  */
    /* JADX WARN: Removed duplicated region for block: B:118:0x016a  */
    /* JADX WARN: Removed duplicated region for block: B:121:0x016f  */
    /* JADX WARN: Removed duplicated region for block: B:122:0x019e  */
    /* JADX WARN: Removed duplicated region for block: B:125:0x01b2  */
    /* JADX WARN: Removed duplicated region for block: B:128:0x01cf  */
    /* JADX WARN: Removed duplicated region for block: B:131:0x01ef  */
    /* JADX WARN: Removed duplicated region for block: B:134:0x01ff  */
    /* JADX WARN: Removed duplicated region for block: B:149:0x02a1  */
    /* JADX WARN: Removed duplicated region for block: B:151:0x02ae  */
    /* JADX WARN: Removed duplicated region for block: B:153:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0069  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x006c  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x0085  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x009a  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x00a2  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x00a7  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x00c0  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x00c5  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x00de  */
    /* JADX WARN: Removed duplicated region for block: B:86:0x00f4  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x0102  */
    /* JADX WARN: Removed duplicated region for block: B:93:0x0114  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final void RangeSlider(final kotlin.ranges.ClosedFloatingPointRange<java.lang.Float> r39, final kotlin.jvm.functions.Function1<? super kotlin.ranges.ClosedFloatingPointRange<java.lang.Float>, kotlin.Unit> r40, androidx.compose.ui.Modifier r41, boolean r42, kotlin.ranges.ClosedFloatingPointRange<java.lang.Float> r43, int r44, kotlin.jvm.functions.Function0<kotlin.Unit> r45, androidx.compose.material.SliderColors r46, androidx.compose.runtime.Composer r47, final int r48, final int r49) {
        /*
            Method dump skipped, instruction units count: 699
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material.SliderKt.RangeSlider(kotlin.ranges.ClosedFloatingPointRange, kotlin.jvm.functions.Function1, androidx.compose.ui.Modifier, boolean, kotlin.ranges.ClosedFloatingPointRange, int, kotlin.jvm.functions.Function0, androidx.compose.material.SliderColors, androidx.compose.runtime.Composer, int, int):void");
    }

    /* JADX INFO: renamed from: androidx.compose.material.SliderKt$RangeSlider$2, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: Slider.kt */
    @Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u000b¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Landroidx/compose/foundation/layout/BoxWithConstraintsScope;", "invoke", "(Landroidx/compose/foundation/layout/BoxWithConstraintsScope;Landroidx/compose/runtime/Composer;I)V"}, k = 3, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    static final class C04452 extends Lambda implements Function3<BoxWithConstraintsScope, Composer, Integer, Unit> {
        final /* synthetic */ SliderColors $colors;
        final /* synthetic */ boolean $enabled;
        final /* synthetic */ MutableInteractionSource $endInteractionSource;
        final /* synthetic */ Function0<Unit> $onValueChangeFinished;
        final /* synthetic */ State<Function1<ClosedFloatingPointRange<Float>, Unit>> $onValueChangeState;
        final /* synthetic */ MutableInteractionSource $startInteractionSource;
        final /* synthetic */ int $steps;
        final /* synthetic */ List<Float> $tickFractions;
        final /* synthetic */ ClosedFloatingPointRange<Float> $value;
        final /* synthetic */ ClosedFloatingPointRange<Float> $valueRange;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        C04452(ClosedFloatingPointRange<Float> closedFloatingPointRange, ClosedFloatingPointRange<Float> closedFloatingPointRange2, List<Float> list, Function0<Unit> function0, State<? extends Function1<? super ClosedFloatingPointRange<Float>, Unit>> state, MutableInteractionSource mutableInteractionSource, MutableInteractionSource mutableInteractionSource2, boolean z, int i, SliderColors sliderColors) {
            super(3);
            this.$valueRange = closedFloatingPointRange;
            this.$value = closedFloatingPointRange2;
            this.$tickFractions = list;
            this.$onValueChangeFinished = function0;
            this.$onValueChangeState = state;
            this.$startInteractionSource = mutableInteractionSource;
            this.$endInteractionSource = mutableInteractionSource2;
            this.$enabled = z;
            this.$steps = i;
            this.$colors = sliderColors;
        }

        @Override // kotlin.jvm.functions.Function3
        public /* bridge */ /* synthetic */ Unit invoke(BoxWithConstraintsScope boxWithConstraintsScope, Composer composer, Integer num) {
            invoke(boxWithConstraintsScope, composer, num.intValue());
            return Unit.INSTANCE;
        }

        public final void invoke(BoxWithConstraintsScope boxWithConstraintsScope, Composer composer, int i) {
            BoxWithConstraintsScope boxWithConstraintsScope2;
            int i2;
            Ref.FloatRef floatRef;
            Ref.FloatRef floatRef2;
            ComposerKt.sourceInformation(composer, "C320@14393L7,*325@14554L7,336@14982L60,337@15070L67,340@15183L15,339@15147L164,347@15356L15,346@15320L169,354@15511L24,355@15607L924,355@15567L964,379@16601L857,379@16554L904,421@18477L63,429@18767L65,435@18960L340:Slider.kt#jmzs0o");
            if ((i & 6) == 0) {
                boxWithConstraintsScope2 = boxWithConstraintsScope;
                i2 = i | (composer.changed(boxWithConstraintsScope2) ? 4 : 2);
            } else {
                boxWithConstraintsScope2 = boxWithConstraintsScope;
                i2 = i;
            }
            if ((i2 & 19) != 18 || !composer.getSkipping()) {
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(652589923, i2, -1, "androidx.compose.material.RangeSlider.<anonymous> (Slider.kt:320)");
                }
                ProvidableCompositionLocal<LayoutDirection> localLayoutDirection = CompositionLocalsKt.getLocalLayoutDirection();
                ComposerKt.sourceInformationMarkerStart(composer, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                Object objConsume = composer.consume(localLayoutDirection);
                ComposerKt.sourceInformationMarkerEnd(composer);
                boolean z = objConsume == LayoutDirection.Rtl;
                float fM6980getMaxWidthimpl = Constraints.m6980getMaxWidthimpl(boxWithConstraintsScope2.mo872getConstraintsmsEJaDk());
                final Ref.FloatRef floatRef3 = new Ref.FloatRef();
                final Ref.FloatRef floatRef4 = new Ref.FloatRef();
                ProvidableCompositionLocal<Density> localDensity = CompositionLocalsKt.getLocalDensity();
                ComposerKt.sourceInformationMarkerStart(composer, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                Object objConsume2 = composer.consume(localDensity);
                ComposerKt.sourceInformationMarkerEnd(composer);
                Density density = (Density) objConsume2;
                floatRef3.element = fM6980getMaxWidthimpl - density.mo657toPx0680j_4(SliderKt.getThumbRadius());
                floatRef4.element = density.mo657toPx0680j_4(SliderKt.getThumbRadius());
                ComposerKt.sourceInformationMarkerStart(composer, 338378195, "CC(remember):Slider.kt#9igjgp");
                ClosedFloatingPointRange<Float> closedFloatingPointRange = this.$value;
                ClosedFloatingPointRange<Float> closedFloatingPointRange2 = this.$valueRange;
                Object objRememberedValue = composer.rememberedValue();
                if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue = PrimitiveSnapshotStateKt.mutableFloatStateOf(invoke$scaleToOffset(closedFloatingPointRange2, floatRef4, floatRef3, closedFloatingPointRange.getStart().floatValue()));
                    composer.updateRememberedValue(objRememberedValue);
                }
                final MutableFloatState mutableFloatState = (MutableFloatState) objRememberedValue;
                ComposerKt.sourceInformationMarkerEnd(composer);
                ComposerKt.sourceInformationMarkerStart(composer, 338381018, "CC(remember):Slider.kt#9igjgp");
                ClosedFloatingPointRange<Float> closedFloatingPointRange3 = this.$value;
                ClosedFloatingPointRange<Float> closedFloatingPointRange4 = this.$valueRange;
                Object objRememberedValue2 = composer.rememberedValue();
                if (objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue2 = PrimitiveSnapshotStateKt.mutableFloatStateOf(invoke$scaleToOffset(closedFloatingPointRange4, floatRef4, floatRef3, closedFloatingPointRange3.getEndInclusive().floatValue()));
                    composer.updateRememberedValue(objRememberedValue2);
                }
                final MutableFloatState mutableFloatState2 = (MutableFloatState) objRememberedValue2;
                ComposerKt.sourceInformationMarkerEnd(composer);
                ComposerKt.sourceInformationMarkerStart(composer, 338384582, "CC(remember):Slider.kt#9igjgp");
                boolean zChanged = composer.changed(this.$valueRange) | composer.changed(floatRef4.element) | composer.changed(floatRef3.element);
                ClosedFloatingPointRange<Float> closedFloatingPointRange5 = this.$valueRange;
                Object objRememberedValue3 = composer.rememberedValue();
                if (zChanged || objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue3 = (KFunction) new SliderKt$RangeSlider$2$2$1(closedFloatingPointRange5, floatRef4, floatRef3);
                    composer.updateRememberedValue(objRememberedValue3);
                }
                ComposerKt.sourceInformationMarkerEnd(composer);
                SliderKt.CorrectValueSideEffect((Function1) ((KFunction) objRememberedValue3), this.$valueRange, RangesKt.rangeTo(floatRef4.element, floatRef3.element), mutableFloatState, this.$value.getStart().floatValue(), composer, 3072);
                ComposerKt.sourceInformationMarkerStart(composer, 338390118, "CC(remember):Slider.kt#9igjgp");
                boolean zChanged2 = composer.changed(this.$valueRange) | composer.changed(floatRef4.element) | composer.changed(floatRef3.element);
                ClosedFloatingPointRange<Float> closedFloatingPointRange6 = this.$valueRange;
                Object objRememberedValue4 = composer.rememberedValue();
                if (zChanged2 || objRememberedValue4 == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue4 = (KFunction) new SliderKt$RangeSlider$2$3$1(closedFloatingPointRange6, floatRef4, floatRef3);
                    composer.updateRememberedValue(objRememberedValue4);
                }
                ComposerKt.sourceInformationMarkerEnd(composer);
                SliderKt.CorrectValueSideEffect((Function1) ((KFunction) objRememberedValue4), this.$valueRange, RangesKt.rangeTo(floatRef4.element, floatRef3.element), mutableFloatState2, this.$value.getEndInclusive().floatValue(), composer, 3072);
                ComposerKt.sourceInformationMarkerStart(composer, 773894976, "CC(rememberCoroutineScope)489@20472L144:Effects.kt#9igjgp");
                ComposerKt.sourceInformationMarkerStart(composer, -954363344, "CC(remember):Effects.kt#9igjgp");
                Object objRememberedValue5 = composer.rememberedValue();
                if (objRememberedValue5 == Composer.INSTANCE.getEmpty()) {
                    Object compositionScopedCoroutineScopeCanceller = new CompositionScopedCoroutineScopeCanceller(EffectsKt.createCompositionCoroutineScope(EmptyCoroutineContext.INSTANCE, composer));
                    composer.updateRememberedValue(compositionScopedCoroutineScopeCanceller);
                    objRememberedValue5 = compositionScopedCoroutineScopeCanceller;
                }
                ComposerKt.sourceInformationMarkerEnd(composer);
                final CoroutineScope coroutineScope = ((CompositionScopedCoroutineScopeCanceller) objRememberedValue5).getCoroutineScope();
                ComposerKt.sourceInformationMarkerEnd(composer);
                ComposerKt.sourceInformationMarkerStart(composer, 338399059, "CC(remember):Slider.kt#9igjgp");
                boolean zChangedInstance = composer.changedInstance(this.$tickFractions) | composer.changed(floatRef4.element) | composer.changed(floatRef3.element) | composer.changed(this.$onValueChangeFinished) | composer.changedInstance(coroutineScope) | composer.changed(this.$onValueChangeState) | composer.changed(this.$valueRange);
                final List<Float> list = this.$tickFractions;
                final Function0<Unit> function0 = this.$onValueChangeFinished;
                final State<Function1<ClosedFloatingPointRange<Float>, Unit>> state = this.$onValueChangeState;
                final ClosedFloatingPointRange<Float> closedFloatingPointRange7 = this.$valueRange;
                Object objRememberedValue6 = composer.rememberedValue();
                if (zChangedInstance || objRememberedValue6 == Composer.INSTANCE.getEmpty()) {
                    Object obj = new Function1<Boolean, Unit>() { // from class: androidx.compose.material.SliderKt$RangeSlider$2$gestureEndAction$1$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        /* JADX WARN: Multi-variable type inference failed */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(Boolean bool) {
                            invoke(bool.booleanValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(boolean z2) {
                            float floatValue = (z2 ? mutableFloatState : mutableFloatState2).getFloatValue();
                            float fSnapValueToTick = SliderKt.snapValueToTick(floatValue, list, floatRef4.element, floatRef3.element);
                            if (floatValue != fSnapValueToTick) {
                                BuildersKt__Builders_commonKt.launch$default(coroutineScope, null, null, new AnonymousClass1(floatValue, fSnapValueToTick, function0, z2, mutableFloatState, mutableFloatState2, state, floatRef4, floatRef3, closedFloatingPointRange7, null), 3, null);
                                return;
                            }
                            Function0<Unit> function02 = function0;
                            if (function02 != null) {
                                function02.invoke();
                            }
                        }

                        /* JADX INFO: renamed from: androidx.compose.material.SliderKt$RangeSlider$2$gestureEndAction$1$1$1, reason: invalid class name */
                        /* JADX INFO: compiled from: Slider.kt */
                        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
                        @DebugMetadata(c = "androidx.compose.material.SliderKt$RangeSlider$2$gestureEndAction$1$1$1", f = "Slider.kt", i = {}, l = {366}, m = "invokeSuspend", n = {}, s = {})
                        static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                            final /* synthetic */ float $current;
                            final /* synthetic */ boolean $isStart;
                            final /* synthetic */ Ref.FloatRef $maxPx;
                            final /* synthetic */ Ref.FloatRef $minPx;
                            final /* synthetic */ Function0<Unit> $onValueChangeFinished;
                            final /* synthetic */ State<Function1<ClosedFloatingPointRange<Float>, Unit>> $onValueChangeState;
                            final /* synthetic */ MutableFloatState $rawOffsetEnd;
                            final /* synthetic */ MutableFloatState $rawOffsetStart;
                            final /* synthetic */ float $target;
                            final /* synthetic */ ClosedFloatingPointRange<Float> $valueRange;
                            int label;

                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            /* JADX WARN: Multi-variable type inference failed */
                            AnonymousClass1(float f, float f2, Function0<Unit> function0, boolean z, MutableFloatState mutableFloatState, MutableFloatState mutableFloatState2, State<? extends Function1<? super ClosedFloatingPointRange<Float>, Unit>> state, Ref.FloatRef floatRef, Ref.FloatRef floatRef2, ClosedFloatingPointRange<Float> closedFloatingPointRange, Continuation<? super AnonymousClass1> continuation) {
                                super(2, continuation);
                                this.$current = f;
                                this.$target = f2;
                                this.$onValueChangeFinished = function0;
                                this.$isStart = z;
                                this.$rawOffsetStart = mutableFloatState;
                                this.$rawOffsetEnd = mutableFloatState2;
                                this.$onValueChangeState = state;
                                this.$minPx = floatRef;
                                this.$maxPx = floatRef2;
                                this.$valueRange = closedFloatingPointRange;
                            }

                            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                                return new AnonymousClass1(this.$current, this.$target, this.$onValueChangeFinished, this.$isStart, this.$rawOffsetStart, this.$rawOffsetEnd, this.$onValueChangeState, this.$minPx, this.$maxPx, this.$valueRange, continuation);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                                return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                            }

                            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                            public final Object invokeSuspend(Object obj) {
                                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                                int i = this.label;
                                if (i == 0) {
                                    ResultKt.throwOnFailure(obj);
                                    Animatable animatableAnimatable$default = AnimatableKt.Animatable$default(this.$current, 0.0f, 2, null);
                                    Float fBoxFloat = Boxing.boxFloat(this.$target);
                                    TweenSpec tweenSpec = SliderKt.SliderToTickAnimation;
                                    Float fBoxFloat2 = Boxing.boxFloat(0.0f);
                                    final boolean z = this.$isStart;
                                    final MutableFloatState mutableFloatState = this.$rawOffsetStart;
                                    final MutableFloatState mutableFloatState2 = this.$rawOffsetEnd;
                                    final State<Function1<ClosedFloatingPointRange<Float>, Unit>> state = this.$onValueChangeState;
                                    final Ref.FloatRef floatRef = this.$minPx;
                                    final Ref.FloatRef floatRef2 = this.$maxPx;
                                    final ClosedFloatingPointRange<Float> closedFloatingPointRange = this.$valueRange;
                                    this.label = 1;
                                    if (animatableAnimatable$default.animateTo(fBoxFloat, tweenSpec, fBoxFloat2, new Function1<Animatable<Float, AnimationVector1D>, Unit>() { // from class: androidx.compose.material.SliderKt.RangeSlider.2.gestureEndAction.1.1.1.1
                                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                        /* JADX WARN: Multi-variable type inference failed */
                                        {
                                            super(1);
                                        }

                                        @Override // kotlin.jvm.functions.Function1
                                        public /* bridge */ /* synthetic */ Unit invoke(Animatable<Float, AnimationVector1D> animatable) {
                                            invoke2(animatable);
                                            return Unit.INSTANCE;
                                        }

                                        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                                        public final void invoke2(Animatable<Float, AnimationVector1D> animatable) {
                                            (z ? mutableFloatState : mutableFloatState2).setFloatValue(animatable.getValue().floatValue());
                                            state.getValue().invoke(SliderKt.C04452.invoke$scaleToUserValue(floatRef, floatRef2, closedFloatingPointRange, RangesKt.rangeTo(mutableFloatState.getFloatValue(), mutableFloatState2.getFloatValue())));
                                        }
                                    }, this) == coroutine_suspended) {
                                        return coroutine_suspended;
                                    }
                                } else {
                                    if (i != 1) {
                                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                                    }
                                    ResultKt.throwOnFailure(obj);
                                }
                                Function0<Unit> function0 = this.$onValueChangeFinished;
                                if (function0 != null) {
                                    function0.invoke();
                                }
                                return Unit.INSTANCE;
                            }
                        }
                    };
                    floatRef = floatRef4;
                    floatRef2 = floatRef3;
                    objRememberedValue6 = (Function1) obj;
                    composer.updateRememberedValue(objRememberedValue6);
                } else {
                    floatRef2 = floatRef3;
                    floatRef = floatRef4;
                }
                ComposerKt.sourceInformationMarkerEnd(composer);
                State stateRememberUpdatedState = SnapshotStateKt.rememberUpdatedState((Function1) objRememberedValue6, composer, 0);
                ComposerKt.sourceInformationMarkerStart(composer, 338430800, "CC(remember):Slider.kt#9igjgp");
                boolean zChanged3 = composer.changed(this.$valueRange) | composer.changed(floatRef.element) | composer.changed(floatRef2.element) | composer.changed(this.$value) | composer.changed(this.$onValueChangeState);
                final ClosedFloatingPointRange<Float> closedFloatingPointRange8 = this.$value;
                final State<Function1<ClosedFloatingPointRange<Float>, Unit>> state2 = this.$onValueChangeState;
                final ClosedFloatingPointRange<Float> closedFloatingPointRange9 = this.$valueRange;
                Object objRememberedValue7 = composer.rememberedValue();
                if (zChanged3 || objRememberedValue7 == Composer.INSTANCE.getEmpty()) {
                    final Ref.FloatRef floatRef5 = floatRef2;
                    final Ref.FloatRef floatRef6 = floatRef;
                    objRememberedValue7 = (Function2) new Function2<Boolean, Float, Unit>() { // from class: androidx.compose.material.SliderKt$RangeSlider$2$onDrag$1$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        /* JADX WARN: Multi-variable type inference failed */
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(Boolean bool, Float f) {
                            invoke(bool.booleanValue(), f.floatValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(boolean z2, float f) {
                            ClosedFloatingPointRange<Float> closedFloatingPointRangeRangeTo;
                            if (z2) {
                                MutableFloatState mutableFloatState3 = mutableFloatState;
                                mutableFloatState3.setFloatValue(mutableFloatState3.getFloatValue() + f);
                                mutableFloatState2.setFloatValue(SliderKt.C04452.invoke$scaleToOffset(closedFloatingPointRange9, floatRef6, floatRef5, closedFloatingPointRange8.getEndInclusive().floatValue()));
                                float floatValue = mutableFloatState2.getFloatValue();
                                closedFloatingPointRangeRangeTo = RangesKt.rangeTo(RangesKt.coerceIn(mutableFloatState.getFloatValue(), floatRef6.element, floatValue), floatValue);
                            } else {
                                MutableFloatState mutableFloatState4 = mutableFloatState2;
                                mutableFloatState4.setFloatValue(mutableFloatState4.getFloatValue() + f);
                                mutableFloatState.setFloatValue(SliderKt.C04452.invoke$scaleToOffset(closedFloatingPointRange9, floatRef6, floatRef5, closedFloatingPointRange8.getStart().floatValue()));
                                float floatValue2 = mutableFloatState.getFloatValue();
                                closedFloatingPointRangeRangeTo = RangesKt.rangeTo(floatValue2, RangesKt.coerceIn(mutableFloatState2.getFloatValue(), floatValue2, floatRef5.element));
                            }
                            state2.getValue().invoke(SliderKt.C04452.invoke$scaleToUserValue(floatRef6, floatRef5, closedFloatingPointRange9, closedFloatingPointRangeRangeTo));
                        }
                    };
                    composer.updateRememberedValue(objRememberedValue7);
                }
                ComposerKt.sourceInformationMarkerEnd(composer);
                Modifier modifierRangeSliderPressDragModifier = SliderKt.rangeSliderPressDragModifier(Modifier.INSTANCE, this.$startInteractionSource, this.$endInteractionSource, mutableFloatState, mutableFloatState2, this.$enabled, z, fM6980getMaxWidthimpl, this.$valueRange, stateRememberUpdatedState, SnapshotStateKt.rememberUpdatedState((Function2) objRememberedValue7, composer, 0));
                final float fCoerceIn = RangesKt.coerceIn(this.$value.getStart().floatValue(), this.$valueRange.getStart().floatValue(), this.$value.getEndInclusive().floatValue());
                final float fCoerceIn2 = RangesKt.coerceIn(this.$value.getEndInclusive().floatValue(), this.$value.getStart().floatValue(), this.$valueRange.getEndInclusive().floatValue());
                float fCalcFraction = SliderKt.calcFraction(this.$valueRange.getStart().floatValue(), this.$valueRange.getEndInclusive().floatValue(), fCoerceIn);
                float fCalcFraction2 = SliderKt.calcFraction(this.$valueRange.getStart().floatValue(), this.$valueRange.getEndInclusive().floatValue(), fCoerceIn2);
                int iFloor = (int) Math.floor(this.$steps * fCalcFraction2);
                int iFloor2 = (int) Math.floor(this.$steps * (1.0f - fCalcFraction));
                Modifier.Companion companion = Modifier.INSTANCE;
                boolean z2 = this.$enabled;
                ComposerKt.sourceInformationMarkerStart(composer, 338490038, "CC(remember):Slider.kt#9igjgp");
                boolean zChanged4 = composer.changed(this.$onValueChangeState) | composer.changed(fCoerceIn2);
                final State<Function1<ClosedFloatingPointRange<Float>, Unit>> state3 = this.$onValueChangeState;
                Object objRememberedValue8 = composer.rememberedValue();
                if (zChanged4 || objRememberedValue8 == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue8 = (Function1) new Function1<Float, Unit>() { // from class: androidx.compose.material.SliderKt$RangeSlider$2$startThumbSemantics$1$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        /* JADX WARN: Multi-variable type inference failed */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(Float f) {
                            invoke(f.floatValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(float f) {
                            state3.getValue().invoke(RangesKt.rangeTo(f, fCoerceIn2));
                        }
                    };
                    composer.updateRememberedValue(objRememberedValue8);
                }
                ComposerKt.sourceInformationMarkerEnd(composer);
                Modifier modifierSliderSemantics = SliderKt.sliderSemantics(companion, fCoerceIn, z2, (Function1) objRememberedValue8, this.$onValueChangeFinished, RangesKt.rangeTo(this.$valueRange.getStart().floatValue(), fCoerceIn2), iFloor);
                Modifier.Companion companion2 = Modifier.INSTANCE;
                boolean z3 = this.$enabled;
                ComposerKt.sourceInformationMarkerStart(composer, 338499320, "CC(remember):Slider.kt#9igjgp");
                boolean zChanged5 = composer.changed(this.$onValueChangeState) | composer.changed(fCoerceIn);
                final State<Function1<ClosedFloatingPointRange<Float>, Unit>> state4 = this.$onValueChangeState;
                Object objRememberedValue9 = composer.rememberedValue();
                if (zChanged5 || objRememberedValue9 == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue9 = (Function1) new Function1<Float, Unit>() { // from class: androidx.compose.material.SliderKt$RangeSlider$2$endThumbSemantics$1$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        /* JADX WARN: Multi-variable type inference failed */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(Float f) {
                            invoke(f.floatValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(float f) {
                            state4.getValue().invoke(RangesKt.rangeTo(fCoerceIn, f));
                        }
                    };
                    composer.updateRememberedValue(objRememberedValue9);
                }
                ComposerKt.sourceInformationMarkerEnd(composer);
                SliderKt.RangeSliderImpl(this.$enabled, fCalcFraction, fCalcFraction2, this.$tickFractions, this.$colors, floatRef2.element - floatRef.element, this.$startInteractionSource, this.$endInteractionSource, modifierRangeSliderPressDragModifier, modifierSliderSemantics, SliderKt.sliderSemantics(companion2, fCoerceIn2, z3, (Function1) objRememberedValue9, this.$onValueChangeFinished, RangesKt.rangeTo(fCoerceIn, this.$valueRange.getEndInclusive().floatValue()), iFloor2), composer, 14155776, 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                    return;
                }
                return;
            }
            composer.skipToGroupEnd();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final ClosedFloatingPointRange<Float> invoke$scaleToUserValue(Ref.FloatRef floatRef, Ref.FloatRef floatRef2, ClosedFloatingPointRange<Float> closedFloatingPointRange, ClosedFloatingPointRange<Float> closedFloatingPointRange2) {
            return SliderKt.scale(floatRef.element, floatRef2.element, closedFloatingPointRange2, closedFloatingPointRange.getStart().floatValue(), closedFloatingPointRange.getEndInclusive().floatValue());
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final float invoke$scaleToOffset(ClosedFloatingPointRange<Float> closedFloatingPointRange, Ref.FloatRef floatRef, Ref.FloatRef floatRef2, float f) {
            return SliderKt.scale(closedFloatingPointRange.getStart().floatValue(), closedFloatingPointRange.getEndInclusive().floatValue(), f, floatRef.element, floatRef2.element);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void SliderImpl(final boolean z, final float f, final List<Float> list, final SliderColors sliderColors, final float f2, final MutableInteractionSource mutableInteractionSource, final Modifier modifier, Composer composer, final int i) {
        int i2;
        List<Float> list2;
        Composer composerStartRestartGroup = composer.startRestartGroup(1679682785);
        ComposerKt.sourceInformation(composerStartRestartGroup, "C(SliderImpl)P(1,4,5!1,6)593@25125L712:Slider.kt#jmzs0o");
        if ((i & 6) == 0) {
            i2 = (composerStartRestartGroup.changed(z) ? 4 : 2) | i;
        } else {
            i2 = i;
        }
        if ((i & 48) == 0) {
            i2 |= composerStartRestartGroup.changed(f) ? 32 : 16;
        }
        if ((i & 384) == 0) {
            list2 = list;
            i2 |= composerStartRestartGroup.changedInstance(list2) ? 256 : 128;
        } else {
            list2 = list;
        }
        if ((i & 3072) == 0) {
            i2 |= composerStartRestartGroup.changed(sliderColors) ? 2048 : 1024;
        }
        if ((i & 24576) == 0) {
            i2 |= composerStartRestartGroup.changed(f2) ? 16384 : 8192;
        }
        if ((196608 & i) == 0) {
            i2 |= composerStartRestartGroup.changed(mutableInteractionSource) ? 131072 : 65536;
        }
        if ((1572864 & i) == 0) {
            i2 |= composerStartRestartGroup.changed(modifier) ? 1048576 : 524288;
        }
        int i3 = i2;
        if ((599187 & i3) != 599186 || !composerStartRestartGroup.getSkipping()) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1679682785, i3, -1, "androidx.compose.material.SliderImpl (Slider.kt:592)");
            }
            Modifier modifierThen = modifier.then(DefaultSliderConstraints);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
            MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false);
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
            Updater.m4020setimpl(composerM4013constructorimpl, measurePolicyMaybeCachedBoxMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4020setimpl(composerM4013constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if (composerM4013constructorimpl.getInserting() || !Intrinsics.areEqual(composerM4013constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                composerM4013constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                composerM4013constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
            }
            Updater.m4020setimpl(composerM4013constructorimpl, modifierMaterializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
            BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1216522066, "C*597@25285L7,606@25530L216,616@25755L76:Slider.kt#jmzs0o");
            ProvidableCompositionLocal<Density> localDensity = CompositionLocalsKt.getLocalDensity();
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object objConsume = composerStartRestartGroup.consume(localDensity);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            Density density = (Density) objConsume;
            float fMo657toPx0680j_4 = density.mo657toPx0680j_4(TrackHeight);
            float f3 = ThumbRadius;
            float fMo657toPx0680j_42 = density.mo657toPx0680j_4(f3);
            float fMo653toDpu2uoSUM = density.mo653toDpu2uoSUM(f2);
            float fM7027constructorimpl = Dp.m7027constructorimpl(f3 * 2);
            float fM7027constructorimpl2 = Dp.m7027constructorimpl(fMo653toDpu2uoSUM * f);
            int i4 = i3 >> 6;
            int i5 = i3 << 9;
            Track(SizeKt.fillMaxSize$default(Modifier.INSTANCE, 0.0f, 1, null), sliderColors, z, 0.0f, f, list2, fMo657toPx0680j_42, fMo657toPx0680j_4, composerStartRestartGroup, (i4 & 112) | 3078 | ((i3 << 6) & 896) | (i5 & 57344) | (i5 & 458752));
            m1936SliderThumbPcYyNuk(boxScopeInstance, Modifier.INSTANCE, fM7027constructorimpl2, mutableInteractionSource, sliderColors, z, fM7027constructorimpl, composerStartRestartGroup, (i4 & 7168) | 1572918 | ((i3 << 3) & 57344) | ((i3 << 15) & 458752));
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            composerStartRestartGroup.endNode();
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
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
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.SliderKt.SliderImpl.2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                    invoke(composer2, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer composer2, int i6) {
                    SliderKt.SliderImpl(z, f, list, sliderColors, f2, mutableInteractionSource, modifier, composer2, RecomposeScopeImplKt.updateChangedFlags(i | 1));
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void RangeSliderImpl(final boolean z, final float f, final float f2, final List<Float> list, final SliderColors sliderColors, final float f3, final MutableInteractionSource mutableInteractionSource, final MutableInteractionSource mutableInteractionSource2, final Modifier modifier, final Modifier modifier2, Modifier modifier3, Composer composer, final int i, final int i2) {
        int i3;
        List<Float> list2;
        SliderColors sliderColors2;
        int i4;
        final Modifier modifier4;
        Composer composerStartRestartGroup = composer.startRestartGroup(-278895713);
        ComposerKt.sourceInformation(composerStartRestartGroup, "C(RangeSliderImpl)P(1,6,5,9!1,10,7!1,4,8)635@26282L35,636@26350L33,637@26388L1522:Slider.kt#jmzs0o");
        if ((i & 6) == 0) {
            i3 = (composerStartRestartGroup.changed(z) ? 4 : 2) | i;
        } else {
            i3 = i;
        }
        if ((i & 48) == 0) {
            i3 |= composerStartRestartGroup.changed(f) ? 32 : 16;
        }
        if ((i & 384) == 0) {
            i3 |= composerStartRestartGroup.changed(f2) ? 256 : 128;
        }
        if ((i & 3072) == 0) {
            list2 = list;
            i3 |= composerStartRestartGroup.changedInstance(list2) ? 2048 : 1024;
        } else {
            list2 = list;
        }
        if ((i & 24576) == 0) {
            sliderColors2 = sliderColors;
            i3 |= composerStartRestartGroup.changed(sliderColors2) ? 16384 : 8192;
        } else {
            sliderColors2 = sliderColors;
        }
        if ((i & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
            i3 |= composerStartRestartGroup.changed(f3) ? 131072 : 65536;
        }
        if ((i & 1572864) == 0) {
            i3 |= composerStartRestartGroup.changed(mutableInteractionSource) ? 1048576 : 524288;
        }
        if ((i & 12582912) == 0) {
            i3 |= composerStartRestartGroup.changed(mutableInteractionSource2) ? 8388608 : 4194304;
        }
        if ((i & 100663296) == 0) {
            i3 |= composerStartRestartGroup.changed(modifier) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
        }
        if ((i & 805306368) == 0) {
            i3 |= composerStartRestartGroup.changed(modifier2) ? 536870912 : 268435456;
        }
        if ((i2 & 6) == 0) {
            i4 = i2 | (composerStartRestartGroup.changed(modifier3) ? 4 : 2);
        } else {
            i4 = i2;
        }
        if ((i3 & 306783379) != 306783378 || (i4 & 3) != 2 || !composerStartRestartGroup.getSkipping()) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-278895713, i3, i4, "androidx.compose.material.RangeSliderImpl (Slider.kt:633)");
            }
            final String strM1958getString4foXLRw = Strings_androidKt.m1958getString4foXLRw(Strings.INSTANCE.m1957getSliderRangeStartUdPEhr4(), composerStartRestartGroup, 6);
            final String strM1958getString4foXLRw2 = Strings_androidKt.m1958getString4foXLRw(Strings.INSTANCE.m1956getSliderRangeEndUdPEhr4(), composerStartRestartGroup, 6);
            Modifier modifierThen = modifier.then(DefaultSliderConstraints);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
            MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false);
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
            Updater.m4020setimpl(composerM4013constructorimpl, measurePolicyMaybeCachedBoxMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4020setimpl(composerM4013constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if (composerM4013constructorimpl.getInserting() || !Intrinsics.areEqual(composerM4013constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                composerM4013constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                composerM4013constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
            }
            Updater.m4020setimpl(composerM4013constructorimpl, modifierMaterializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
            BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -757846185, "C*641@26548L7,650@26856L301,665@27253L48,663@27167L369,676@27631L46,674@27545L359:Slider.kt#jmzs0o");
            ProvidableCompositionLocal<Density> localDensity = CompositionLocalsKt.getLocalDensity();
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object objConsume = composerStartRestartGroup.consume(localDensity);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            Density density = (Density) objConsume;
            float fMo657toPx0680j_4 = density.mo657toPx0680j_4(TrackHeight);
            float f4 = ThumbRadius;
            float fMo657toPx0680j_42 = density.mo657toPx0680j_4(f4);
            float fMo653toDpu2uoSUM = density.mo653toDpu2uoSUM(f3);
            float fM7027constructorimpl = Dp.m7027constructorimpl(f4 * 2);
            float fM7027constructorimpl2 = Dp.m7027constructorimpl(fMo653toDpu2uoSUM * f);
            float fM7027constructorimpl3 = Dp.m7027constructorimpl(fMo653toDpu2uoSUM * f2);
            int i5 = i3 >> 9;
            int i6 = i3 << 6;
            int i7 = i3;
            Track(SizeKt.fillMaxSize$default(boxScopeInstance.align(Modifier.INSTANCE, Alignment.INSTANCE.getCenterStart()), 0.0f, 1, null), sliderColors2, z, f, f2, list2, fMo657toPx0680j_42, fMo657toPx0680j_4, composerStartRestartGroup, (i6 & 896) | (i5 & 112) | (i6 & 7168) | (i6 & 57344) | (i6 & 458752));
            composerStartRestartGroup = composerStartRestartGroup;
            Modifier.Companion companion = Modifier.INSTANCE;
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -855706137, "CC(remember):Slider.kt#9igjgp");
            boolean zChanged = composerStartRestartGroup.changed(strM1958getString4foXLRw);
            Object objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = (Function1) new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material.SliderKt$RangeSliderImpl$1$2$1
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
                        SemanticsPropertiesKt.setContentDescription(semanticsPropertyReceiver, strM1958getString4foXLRw);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            int i8 = i7 & 57344;
            int i9 = (i7 << 15) & 458752;
            m1936SliderThumbPcYyNuk(boxScopeInstance, FocusableKt.focusable(SemanticsModifierKt.semantics(companion, true, (Function1) objRememberedValue), true, mutableInteractionSource).then(modifier2), fM7027constructorimpl2, mutableInteractionSource, sliderColors, z, fM7027constructorimpl, composerStartRestartGroup, (i5 & 7168) | 1572870 | i8 | i9);
            Modifier.Companion companion2 = Modifier.INSTANCE;
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -855694043, "CC(remember):Slider.kt#9igjgp");
            boolean zChanged2 = composerStartRestartGroup.changed(strM1958getString4foXLRw2);
            Object objRememberedValue2 = composerStartRestartGroup.rememberedValue();
            if (zChanged2 || objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue2 = (Function1) new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material.SliderKt$RangeSliderImpl$1$3$1
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
                        SemanticsPropertiesKt.setContentDescription(semanticsPropertyReceiver, strM1958getString4foXLRw2);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
            }
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            modifier4 = modifier3;
            m1936SliderThumbPcYyNuk(boxScopeInstance, FocusableKt.focusable(SemanticsModifierKt.semantics(companion2, true, (Function1) objRememberedValue2), true, mutableInteractionSource2).then(modifier4), fM7027constructorimpl3, mutableInteractionSource2, sliderColors, z, fM7027constructorimpl, composerStartRestartGroup, ((i7 >> 12) & 7168) | 1572870 | i8 | i9);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            composerStartRestartGroup.endNode();
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composerStartRestartGroup.skipToGroupEnd();
            modifier4 = modifier3;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.SliderKt.RangeSliderImpl.2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                    invoke(composer2, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer composer2, int i10) {
                    SliderKt.RangeSliderImpl(z, f, f2, list, sliderColors, f3, mutableInteractionSource, mutableInteractionSource2, modifier, modifier2, modifier4, composer2, RecomposeScopeImplKt.updateChangedFlags(i | 1), RecomposeScopeImplKt.updateChangedFlags(i2));
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: SliderThumb-PcYyNuk, reason: not valid java name */
    public static final void m1936SliderThumbPcYyNuk(final BoxScope boxScope, final Modifier modifier, final float f, final MutableInteractionSource mutableInteractionSource, final SliderColors sliderColors, final boolean z, final float f2, Composer composer, final int i) {
        int i2;
        Composer composerStartRestartGroup = composer.startRestartGroup(428907178);
        ComposerKt.sourceInformation(composerStartRestartGroup, "C(SliderThumb)P(3,4:c#ui.unit.Dp,2!,5:c#ui.unit.Dp)697@28123L1639:Slider.kt#jmzs0o");
        if ((i & 6) == 0) {
            i2 = (composerStartRestartGroup.changed(boxScope) ? 4 : 2) | i;
        } else {
            i2 = i;
        }
        if ((i & 48) == 0) {
            i2 |= composerStartRestartGroup.changed(modifier) ? 32 : 16;
        }
        if ((i & 384) == 0) {
            i2 |= composerStartRestartGroup.changed(f) ? 256 : 128;
        }
        if ((i & 3072) == 0) {
            i2 |= composerStartRestartGroup.changed(mutableInteractionSource) ? 2048 : 1024;
        }
        if ((i & 24576) == 0) {
            i2 |= composerStartRestartGroup.changed(sliderColors) ? 16384 : 8192;
        }
        if ((196608 & i) == 0) {
            i2 |= composerStartRestartGroup.changed(z) ? 131072 : 65536;
        }
        if ((1572864 & i) == 0) {
            i2 |= composerStartRestartGroup.changed(f2) ? 1048576 : 524288;
        }
        if ((599187 & i2) == 599186 && composerStartRestartGroup.getSkipping()) {
            composerStartRestartGroup.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(428907178, i2, -1, "androidx.compose.material.SliderThumb (Slider.kt:696)");
            }
            Modifier modifierAlign = boxScope.align(PaddingKt.m969paddingqDBjuR0$default(Modifier.INSTANCE, f, 0.0f, 0.0f, 0.0f, 14, null), Alignment.INSTANCE.getCenterStart());
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
            MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap = composerStartRestartGroup.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierAlign);
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
            Updater.m4020setimpl(composerM4013constructorimpl, measurePolicyMaybeCachedBoxMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4020setimpl(composerM4013constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if (composerM4013constructorimpl.getInserting() || !Intrinsics.areEqual(composerM4013constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                composerM4013constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                composerM4013constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
            }
            Updater.m4020setimpl(composerM4013constructorimpl, modifierMaterializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
            BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -756667644, "C701@28254L46,702@28343L658,702@28309L692,725@29357L145,732@29707L19,720@29163L593:Slider.kt#jmzs0o");
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 806874492, "CC(remember):Slider.kt#9igjgp");
            Object objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = SnapshotStateKt.mutableStateListOf();
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            SnapshotStateList snapshotStateList = (SnapshotStateList) objRememberedValue;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 806877952, "CC(remember):Slider.kt#9igjgp");
            boolean z2 = (i2 & 7168) == 2048;
            SliderKt$SliderThumb$1$1$1 sliderKt$SliderThumb$1$1$1RememberedValue = composerStartRestartGroup.rememberedValue();
            if (z2 || sliderKt$SliderThumb$1$1$1RememberedValue == Composer.INSTANCE.getEmpty()) {
                sliderKt$SliderThumb$1$1$1RememberedValue = new SliderKt$SliderThumb$1$1$1(mutableInteractionSource, snapshotStateList, null);
                composerStartRestartGroup.updateRememberedValue(sliderKt$SliderThumb$1$1$1RememberedValue);
            }
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            int i3 = i2 >> 9;
            EffectsKt.LaunchedEffect(mutableInteractionSource, (Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object>) sliderKt$SliderThumb$1$1$1RememberedValue, composerStartRestartGroup, i3 & 14);
            SpacerKt.Spacer(BackgroundKt.m519backgroundbw27NRU(ShadowKt.m4232shadows4CzXII$default(HoverableKt.hoverable$default(IndicationKt.indication(SizeKt.m1012sizeVpY3zN4(modifier, f2, f2), mutableInteractionSource, RippleKt.m1929rippleOrFallbackImplementation9IZ8Weo(false, ThumbRippleRadius, 0L, composerStartRestartGroup, 54, 4)), mutableInteractionSource, false, 2, null), z ? !snapshotStateList.isEmpty() ? ThumbPressedElevation : ThumbDefaultElevation : Dp.m7027constructorimpl(0), RoundedCornerShapeKt.getCircleShape(), false, 0L, 0L, 24, null), sliderColors.thumbColor(z, composerStartRestartGroup, ((i2 >> 15) & 14) | (i3 & 112)).getValue().m4578unboximpl(), RoundedCornerShapeKt.getCircleShape()), composerStartRestartGroup, 0);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            composerStartRestartGroup.endNode();
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.SliderKt$SliderThumb$2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                    invoke(composer2, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer composer2, int i4) {
                    SliderKt.m1936SliderThumbPcYyNuk(boxScope, modifier, f, mutableInteractionSource, sliderColors, z, f2, composer2, RecomposeScopeImplKt.updateChangedFlags(i | 1));
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void Track(final Modifier modifier, final SliderColors sliderColors, final boolean z, final float f, final float f2, final List<Float> list, final float f3, final float f4, Composer composer, final int i) {
        int i2;
        float f5;
        float f6;
        int i3;
        Composer composerStartRestartGroup = composer.startRestartGroup(1833126050);
        ComposerKt.sourceInformation(composerStartRestartGroup, "C(Track)P(2!2,4!1,6)748@30055L35,749@30125L34,750@30195L34,751@30263L33,752@30318L1535,752@30301L1552:Slider.kt#jmzs0o");
        if ((i & 6) == 0) {
            i2 = (composerStartRestartGroup.changed(modifier) ? 4 : 2) | i;
        } else {
            i2 = i;
        }
        if ((i & 48) == 0) {
            i2 |= composerStartRestartGroup.changed(sliderColors) ? 32 : 16;
        }
        if ((i & 384) == 0) {
            i2 |= composerStartRestartGroup.changed(z) ? 256 : 128;
        }
        if ((i & 3072) == 0) {
            i2 |= composerStartRestartGroup.changed(f) ? 2048 : 1024;
        }
        if ((i & 24576) == 0) {
            f5 = f2;
            i2 |= composerStartRestartGroup.changed(f5) ? 16384 : 8192;
        } else {
            f5 = f2;
        }
        if ((196608 & i) == 0) {
            i2 |= composerStartRestartGroup.changedInstance(list) ? 131072 : 65536;
        }
        if ((1572864 & i) == 0) {
            f6 = f3;
            i2 |= composerStartRestartGroup.changed(f6) ? 1048576 : 524288;
        } else {
            f6 = f3;
        }
        if ((12582912 & i) == 0) {
            i2 |= composerStartRestartGroup.changed(f4) ? 8388608 : 4194304;
        }
        if ((i2 & 4793491) != 4793490 || !composerStartRestartGroup.getSkipping()) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1833126050, i2, -1, "androidx.compose.material.Track (Slider.kt:747)");
            }
            int i4 = ((i2 >> 6) & 14) | 48 | ((i2 << 3) & 896);
            final State<Color> stateTrackColor = sliderColors.trackColor(z, false, composerStartRestartGroup, i4);
            final State<Color> stateTrackColor2 = sliderColors.trackColor(z, true, composerStartRestartGroup, i4);
            final State<Color> stateTickColor = sliderColors.tickColor(z, false, composerStartRestartGroup, i4);
            final State<Color> stateTickColor2 = sliderColors.tickColor(z, true, composerStartRestartGroup, i4);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1731271772, "CC(remember):Slider.kt#9igjgp");
            boolean zChanged = ((29360128 & i2) == 8388608) | ((3670016 & i2) == 1048576) | composerStartRestartGroup.changed(stateTrackColor) | ((57344 & i2) == 16384) | ((i2 & 7168) == 2048) | composerStartRestartGroup.changed(stateTrackColor2) | composerStartRestartGroup.changedInstance(list) | composerStartRestartGroup.changed(stateTickColor) | composerStartRestartGroup.changed(stateTickColor2);
            Object objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                i3 = i2;
                final float f7 = f6;
                final float f8 = f5;
                objRememberedValue = (Function1) new Function1<DrawScope, Unit>() { // from class: androidx.compose.material.SliderKt$Track$1$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(DrawScope drawScope) {
                        invoke2(drawScope);
                        return Unit.INSTANCE;
                    }

                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(DrawScope drawScope) {
                        boolean z2 = drawScope.getLayoutDirection() == LayoutDirection.Rtl;
                        long jOffset = OffsetKt.Offset(f7, Offset.m4328getYimpl(drawScope.mo5123getCenterF1C5BW0()));
                        long jOffset2 = OffsetKt.Offset(Size.m4396getWidthimpl(drawScope.mo5124getSizeNHjbRc()) - f7, Offset.m4328getYimpl(drawScope.mo5123getCenterF1C5BW0()));
                        long j = z2 ? jOffset2 : jOffset;
                        long j2 = z2 ? jOffset : jOffset2;
                        DrawScope.m5110drawLineNGM6Ib0$default(drawScope, stateTrackColor.getValue().m4578unboximpl(), j, j2, f4, StrokeCap.INSTANCE.m4922getRoundKaPHkGw(), null, 0.0f, null, 0, 480, null);
                        DrawScope.m5110drawLineNGM6Ib0$default(drawScope, stateTrackColor2.getValue().m4578unboximpl(), OffsetKt.Offset(Offset.m4327getXimpl(j) + ((Offset.m4327getXimpl(j2) - Offset.m4327getXimpl(j)) * f), Offset.m4328getYimpl(drawScope.mo5123getCenterF1C5BW0())), OffsetKt.Offset(Offset.m4327getXimpl(j) + ((Offset.m4327getXimpl(j2) - Offset.m4327getXimpl(j)) * f8), Offset.m4328getYimpl(drawScope.mo5123getCenterF1C5BW0())), f4, StrokeCap.INSTANCE.m4922getRoundKaPHkGw(), null, 0.0f, null, 0, 480, null);
                        List<Float> list2 = list;
                        float f9 = f8;
                        float f10 = f;
                        LinkedHashMap linkedHashMap = new LinkedHashMap();
                        for (Object obj : list2) {
                            float fFloatValue = ((Number) obj).floatValue();
                            Boolean boolValueOf = Boolean.valueOf(fFloatValue > f9 || fFloatValue < f10);
                            Object obj2 = linkedHashMap.get(boolValueOf);
                            if (obj2 == null) {
                                obj2 = (List) new ArrayList();
                                linkedHashMap.put(boolValueOf, obj2);
                            }
                            ((List) obj2).add(obj);
                        }
                        State<Color> state = stateTickColor;
                        State<Color> state2 = stateTickColor2;
                        float f11 = f4;
                        for (Map.Entry entry : linkedHashMap.entrySet()) {
                            boolean zBooleanValue = ((Boolean) entry.getKey()).booleanValue();
                            List list3 = (List) entry.getValue();
                            ArrayList arrayList = new ArrayList(list3.size());
                            int size = list3.size();
                            for (int i5 = 0; i5 < size; i5++) {
                                arrayList.add(Offset.m4316boximpl(OffsetKt.Offset(Offset.m4327getXimpl(OffsetKt.m4350lerpWko1d7g(j, j2, ((Number) list3.get(i5)).floatValue())), Offset.m4328getYimpl(drawScope.mo5123getCenterF1C5BW0()))));
                            }
                            DrawScope.m5115drawPointsF8ZwMP8$default(drawScope, arrayList, PointMode.INSTANCE.m4874getPointsr_lszbg(), (zBooleanValue ? state : state2).getValue().m4578unboximpl(), f11, StrokeCap.INSTANCE.m4922getRoundKaPHkGw(), null, 0.0f, null, 0, 480, null);
                        }
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            } else {
                i3 = i2;
            }
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            CanvasKt.Canvas(modifier, (Function1) objRememberedValue, composerStartRestartGroup, i3 & 14);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composerStartRestartGroup.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.SliderKt.Track.2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                    invoke(composer2, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer composer2, int i5) {
                    SliderKt.Track(modifier, sliderColors, z, f, f2, list, f3, f4, composer2, RecomposeScopeImplKt.updateChangedFlags(i | 1));
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
    public static final java.lang.Object m1939awaitSlop8vUncbI(androidx.compose.ui.input.pointer.AwaitPointerEventScope r8, long r9, int r11, kotlin.coroutines.Continuation<? super kotlin.Pair<androidx.compose.ui.input.pointer.PointerInputChange, java.lang.Float>> r12) {
        /*
            boolean r0 = r12 instanceof androidx.compose.material.SliderKt$awaitSlop$1
            if (r0 == 0) goto L14
            r0 = r12
            androidx.compose.material.SliderKt$awaitSlop$1 r0 = (androidx.compose.material.SliderKt$awaitSlop$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r12 = r0.label
            int r12 = r12 - r2
            r0.label = r12
            goto L19
        L14:
            androidx.compose.material.SliderKt$awaitSlop$1 r0 = new androidx.compose.material.SliderKt$awaitSlop$1
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
            androidx.compose.material.SliderKt$awaitSlop$postPointerSlop$1 r1 = new androidx.compose.material.SliderKt$awaitSlop$postPointerSlop$1
            r1.<init>()
            r5 = r1
            kotlin.jvm.functions.Function2 r5 = (kotlin.jvm.functions.Function2) r5
            r6.L$0 = r12
            r6.label = r2
            r1 = r8
            r2 = r9
            r4 = r11
            java.lang.Object r8 = androidx.compose.material.DragGestureDetectorCopyKt.m1822awaitHorizontalPointerSlopOrCancellationgDDlDlE(r1, r2, r4, r5, r6)
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
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material.SliderKt.m1939awaitSlop8vUncbI(androidx.compose.ui.input.pointer.AwaitPointerEventScope, long, int, kotlin.coroutines.Continuation):java.lang.Object");
    }

    private static final List<Float> stepsToTickFractions(int i) {
        if (i == 0) {
            return CollectionsKt.emptyList();
        }
        int i2 = i + 2;
        ArrayList arrayList = new ArrayList(i2);
        for (int i3 = 0; i3 < i2; i3++) {
            arrayList.add(Float.valueOf(i3 / (i + 1)));
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final float scale(float f, float f2, float f3, float f4, float f5) {
        return MathHelpersKt.lerp(f4, f5, calcFraction(f, f2, f3));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final ClosedFloatingPointRange<Float> scale(float f, float f2, ClosedFloatingPointRange<Float> closedFloatingPointRange, float f3, float f4) {
        return RangesKt.rangeTo(scale(f, f2, closedFloatingPointRange.getStart().floatValue(), f3, f4), scale(f, f2, closedFloatingPointRange.getEndInclusive().floatValue(), f3, f4));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void CorrectValueSideEffect(final Function1<? super Float, Float> function1, final ClosedFloatingPointRange<Float> closedFloatingPointRange, final ClosedFloatingPointRange<Float> closedFloatingPointRange2, final MutableState<Float> mutableState, final float f, Composer composer, final int i) {
        int i2;
        float f2;
        Composer composerStartRestartGroup = composer.startRestartGroup(-743965752);
        ComposerKt.sourceInformation(composerStartRestartGroup, "C(CorrectValueSideEffect)P(!1,3!1,4)848@33715L300,848@33704L311:Slider.kt#jmzs0o");
        if ((i & 6) == 0) {
            i2 = (composerStartRestartGroup.changedInstance(function1) ? 4 : 2) | i;
        } else {
            i2 = i;
        }
        if ((i & 48) == 0) {
            i2 |= composerStartRestartGroup.changed(closedFloatingPointRange) ? 32 : 16;
        }
        if ((i & 384) == 0) {
            i2 |= composerStartRestartGroup.changed(closedFloatingPointRange2) ? 256 : 128;
        }
        if ((i & 3072) == 0) {
            i2 |= composerStartRestartGroup.changed(mutableState) ? 2048 : 1024;
        }
        if ((i & 24576) == 0) {
            f2 = f;
            i2 |= composerStartRestartGroup.changed(f2) ? 16384 : 8192;
        } else {
            f2 = f;
        }
        if ((i2 & 9363) != 9362 || !composerStartRestartGroup.getSkipping()) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-743965752, i2, -1, "androidx.compose.material.CorrectValueSideEffect (Slider.kt:847)");
            }
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1313906175, "CC(remember):Slider.kt#9igjgp");
            boolean z = ((i2 & 112) == 32) | ((i2 & 14) == 4) | ((57344 & i2) == 16384) | ((i2 & 7168) == 2048) | ((i2 & 896) == 256);
            Object objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (z || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                final float f3 = f2;
                objRememberedValue = (Function0) new Function0<Unit>() { // from class: androidx.compose.material.SliderKt$CorrectValueSideEffect$1$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    /* JADX WARN: Multi-variable type inference failed */
                    {
                        super(0);
                    }

                    @Override // kotlin.jvm.functions.Function0
                    public /* bridge */ /* synthetic */ Unit invoke() {
                        invoke2();
                        return Unit.INSTANCE;
                    }

                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2() {
                        float fFloatValue = (closedFloatingPointRange.getEndInclusive().floatValue() - closedFloatingPointRange.getStart().floatValue()) / 1000;
                        float fFloatValue2 = function1.invoke(Float.valueOf(f3)).floatValue();
                        if (Math.abs(fFloatValue2 - mutableState.getValue().floatValue()) <= fFloatValue || !closedFloatingPointRange2.contains(mutableState.getValue())) {
                            return;
                        }
                        mutableState.setValue(Float.valueOf(fFloatValue2));
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            EffectsKt.SideEffect((Function0) objRememberedValue, composerStartRestartGroup, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composerStartRestartGroup.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.SliderKt.CorrectValueSideEffect.2
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

                public final void invoke(Composer composer2, int i3) {
                    SliderKt.CorrectValueSideEffect(function1, closedFloatingPointRange, closedFloatingPointRange2, mutableState, f, composer2, RecomposeScopeImplKt.updateChangedFlags(i | 1));
                }
            });
        }
    }

    static /* synthetic */ Modifier sliderSemantics$default(Modifier modifier, float f, boolean z, Function1 function1, Function0 function0, ClosedFloatingPointRange closedFloatingPointRange, int i, int i2, Object obj) {
        if ((i2 & 8) != 0) {
            function0 = null;
        }
        Function0 function02 = function0;
        if ((i2 & 16) != 0) {
            closedFloatingPointRange = RangesKt.rangeTo(0.0f, 1.0f);
        }
        ClosedFloatingPointRange closedFloatingPointRange2 = closedFloatingPointRange;
        if ((i2 & 32) != 0) {
            i = 0;
        }
        return sliderSemantics(modifier, f, z, function1, function02, closedFloatingPointRange2, i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Modifier sliderSemantics(Modifier modifier, float f, final boolean z, final Function1<? super Float, Unit> function1, final Function0<Unit> function0, final ClosedFloatingPointRange<Float> closedFloatingPointRange, final int i) {
        final float fCoerceIn = RangesKt.coerceIn(f, closedFloatingPointRange.getStart().floatValue(), closedFloatingPointRange.getEndInclusive().floatValue());
        return ProgressSemanticsKt.progressSemantics(SemanticsModifierKt.semantics$default(modifier, false, new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material.SliderKt.sliderSemantics.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
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
                final ClosedFloatingPointRange<Float> closedFloatingPointRange2 = closedFloatingPointRange;
                final int i2 = i;
                final float f2 = fCoerceIn;
                final Function1<Float, Unit> function12 = function1;
                final Function0<Unit> function02 = function0;
                SemanticsPropertiesKt.setProgress$default(semanticsPropertyReceiver, null, new Function1<Float, Boolean>() { // from class: androidx.compose.material.SliderKt.sliderSemantics.1.1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    /* JADX WARN: Multi-variable type inference failed */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Boolean invoke(Float f3) {
                        return invoke(f3.floatValue());
                    }

                    public final Boolean invoke(float f3) {
                        int i3;
                        float fCoerceIn2 = RangesKt.coerceIn(f3, closedFloatingPointRange2.getStart().floatValue(), closedFloatingPointRange2.getEndInclusive().floatValue());
                        int i4 = i2;
                        boolean z2 = false;
                        if (i4 > 0 && (i3 = i4 + 1) >= 0) {
                            float fAbs = fCoerceIn2;
                            float f4 = fAbs;
                            int i5 = 0;
                            while (true) {
                                float fLerp = MathHelpersKt.lerp(closedFloatingPointRange2.getStart().floatValue(), closedFloatingPointRange2.getEndInclusive().floatValue(), i5 / (i2 + 1));
                                float f5 = fLerp - fCoerceIn2;
                                if (Math.abs(f5) <= fAbs) {
                                    fAbs = Math.abs(f5);
                                    f4 = fLerp;
                                }
                                if (i5 == i3) {
                                    break;
                                }
                                i5++;
                            }
                            fCoerceIn2 = f4;
                        }
                        if (fCoerceIn2 != f2) {
                            function12.invoke(Float.valueOf(fCoerceIn2));
                            Function0<Unit> function03 = function02;
                            if (function03 != null) {
                                function03.invoke();
                            }
                            z2 = true;
                        }
                        return Boolean.valueOf(z2);
                    }
                }, 1, null);
            }
        }, 1, null), f, closedFloatingPointRange, i);
    }

    /* JADX INFO: renamed from: androidx.compose.material.SliderKt$animateToTarget$2, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: Slider.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Landroidx/compose/foundation/gestures/DragScope;"}, k = 3, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    @DebugMetadata(c = "androidx.compose.material.SliderKt$animateToTarget$2", f = "Slider.kt", i = {}, l = {964}, m = "invokeSuspend", n = {}, s = {})
    static final class C04512 extends SuspendLambda implements Function2<DragScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ float $current;
        final /* synthetic */ float $target;
        final /* synthetic */ float $velocity;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C04512(float f, float f2, float f3, Continuation<? super C04512> continuation) {
            super(2, continuation);
            this.$current = f;
            this.$target = f2;
            this.$velocity = f3;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C04512 c04512 = new C04512(this.$current, this.$target, this.$velocity, continuation);
            c04512.L$0 = obj;
            return c04512;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(DragScope dragScope, Continuation<? super Unit> continuation) {
            return ((C04512) create(dragScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                final DragScope dragScope = (DragScope) this.L$0;
                final Ref.FloatRef floatRef = new Ref.FloatRef();
                floatRef.element = this.$current;
                this.label = 1;
                if (AnimatableKt.Animatable$default(this.$current, 0.0f, 2, null).animateTo(Boxing.boxFloat(this.$target), SliderKt.SliderToTickAnimation, Boxing.boxFloat(this.$velocity), new Function1<Animatable<Float, AnimationVector1D>, Unit>() { // from class: androidx.compose.material.SliderKt.animateToTarget.2.1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(Animatable<Float, AnimationVector1D> animatable) {
                        invoke2(animatable);
                        return Unit.INSTANCE;
                    }

                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(Animatable<Float, AnimationVector1D> animatable) {
                        dragScope.dragBy(animatable.getValue().floatValue() - floatRef.element);
                        floatRef.element = animatable.getValue().floatValue();
                    }
                }, this) == coroutine_suspended) {
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

    /* JADX INFO: Access modifiers changed from: private */
    public static final Object animateToTarget(DraggableState draggableState, float f, float f2, float f3, Continuation<? super Unit> continuation) {
        Object objDrag$default = DraggableState.drag$default(draggableState, null, new C04512(f, f2, f3, null), continuation, 1, null);
        return objDrag$default == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objDrag$default : Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: androidx.compose.material.SliderKt$rangeSliderPressDragModifier$1, reason: invalid class name */
    /* JADX INFO: compiled from: Slider.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Landroidx/compose/ui/input/pointer/PointerInputScope;"}, k = 3, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    @DebugMetadata(c = "androidx.compose.material.SliderKt$rangeSliderPressDragModifier$1", f = "Slider.kt", i = {}, l = {992}, m = "invokeSuspend", n = {}, s = {})
    static final class AnonymousClass1 extends SuspendLambda implements Function2<PointerInputScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ MutableInteractionSource $endInteractionSource;
        final /* synthetic */ State<Function1<Boolean, Unit>> $gestureEndAction;
        final /* synthetic */ boolean $isRtl;
        final /* synthetic */ float $maxPx;
        final /* synthetic */ State<Function2<Boolean, Float, Unit>> $onDrag;
        final /* synthetic */ State<Float> $rawOffsetEnd;
        final /* synthetic */ State<Float> $rawOffsetStart;
        final /* synthetic */ MutableInteractionSource $startInteractionSource;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        AnonymousClass1(MutableInteractionSource mutableInteractionSource, MutableInteractionSource mutableInteractionSource2, State<Float> state, State<Float> state2, State<? extends Function2<? super Boolean, ? super Float, Unit>> state3, boolean z, float f, State<? extends Function1<? super Boolean, Unit>> state4, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.$startInteractionSource = mutableInteractionSource;
            this.$endInteractionSource = mutableInteractionSource2;
            this.$rawOffsetStart = state;
            this.$rawOffsetEnd = state2;
            this.$onDrag = state3;
            this.$isRtl = z;
            this.$maxPx = f;
            this.$gestureEndAction = state4;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$startInteractionSource, this.$endInteractionSource, this.$rawOffsetStart, this.$rawOffsetEnd, this.$onDrag, this.$isRtl, this.$maxPx, this.$gestureEndAction, continuation);
            anonymousClass1.L$0 = obj;
            return anonymousClass1;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(PointerInputScope pointerInputScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(pointerInputScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                PointerInputScope pointerInputScope = (PointerInputScope) this.L$0;
                RangeSliderLogic rangeSliderLogic = new RangeSliderLogic(this.$startInteractionSource, this.$endInteractionSource, this.$rawOffsetStart, this.$rawOffsetEnd, this.$onDrag);
                this.label = 1;
                if (CoroutineScopeKt.coroutineScope(new C00871(pointerInputScope, this.$isRtl, this.$maxPx, rangeSliderLogic, this.$rawOffsetStart, this.$gestureEndAction, this.$rawOffsetEnd, this.$onDrag, null), this) == coroutine_suspended) {
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

        /* JADX INFO: renamed from: androidx.compose.material.SliderKt$rangeSliderPressDragModifier$1$1, reason: invalid class name and collision with other inner class name */
        /* JADX INFO: compiled from: Slider.kt */
        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
        @DebugMetadata(c = "androidx.compose.material.SliderKt$rangeSliderPressDragModifier$1$1", f = "Slider.kt", i = {}, l = {993}, m = "invokeSuspend", n = {}, s = {})
        static final class C00871 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            final /* synthetic */ PointerInputScope $$this$pointerInput;
            final /* synthetic */ State<Function1<Boolean, Unit>> $gestureEndAction;
            final /* synthetic */ boolean $isRtl;
            final /* synthetic */ float $maxPx;
            final /* synthetic */ State<Function2<Boolean, Float, Unit>> $onDrag;
            final /* synthetic */ RangeSliderLogic $rangeSliderLogic;
            final /* synthetic */ State<Float> $rawOffsetEnd;
            final /* synthetic */ State<Float> $rawOffsetStart;
            private /* synthetic */ Object L$0;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            C00871(PointerInputScope pointerInputScope, boolean z, float f, RangeSliderLogic rangeSliderLogic, State<Float> state, State<? extends Function1<? super Boolean, Unit>> state2, State<Float> state3, State<? extends Function2<? super Boolean, ? super Float, Unit>> state4, Continuation<? super C00871> continuation) {
                super(2, continuation);
                this.$$this$pointerInput = pointerInputScope;
                this.$isRtl = z;
                this.$maxPx = f;
                this.$rangeSliderLogic = rangeSliderLogic;
                this.$rawOffsetStart = state;
                this.$gestureEndAction = state2;
                this.$rawOffsetEnd = state3;
                this.$onDrag = state4;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                C00871 c00871 = new C00871(this.$$this$pointerInput, this.$isRtl, this.$maxPx, this.$rangeSliderLogic, this.$rawOffsetStart, this.$gestureEndAction, this.$rawOffsetEnd, this.$onDrag, continuation);
                c00871.L$0 = obj;
                return c00871;
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                return ((C00871) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            /* JADX INFO: renamed from: androidx.compose.material.SliderKt$rangeSliderPressDragModifier$1$1$1, reason: invalid class name and collision with other inner class name */
            /* JADX INFO: compiled from: Slider.kt */
            @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;"}, k = 3, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
            @DebugMetadata(c = "androidx.compose.material.SliderKt$rangeSliderPressDragModifier$1$1$1", f = "Slider.kt", i = {0, 1, 1, 1, 1, 1, 2, 2}, l = {994, 1004, 1023}, m = "invokeSuspend", n = {"$this$awaitEachGesture", "$this$awaitEachGesture", NotificationCompat.CATEGORY_EVENT, "interaction", "posX", "draggingStart", "interaction", "draggingStart"}, s = {"L$0", "L$0", "L$1", "L$2", "L$3", "L$4", "L$0", "L$1"})
            static final class C00881 extends RestrictedSuspendLambda implements Function2<AwaitPointerEventScope, Continuation<? super Unit>, Object> {
                final /* synthetic */ CoroutineScope $$this$coroutineScope;
                final /* synthetic */ State<Function1<Boolean, Unit>> $gestureEndAction;
                final /* synthetic */ boolean $isRtl;
                final /* synthetic */ float $maxPx;
                final /* synthetic */ State<Function2<Boolean, Float, Unit>> $onDrag;
                final /* synthetic */ RangeSliderLogic $rangeSliderLogic;
                final /* synthetic */ State<Float> $rawOffsetEnd;
                final /* synthetic */ State<Float> $rawOffsetStart;
                private /* synthetic */ Object L$0;
                Object L$1;
                Object L$2;
                Object L$3;
                Object L$4;
                int label;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                C00881(boolean z, float f, RangeSliderLogic rangeSliderLogic, State<Float> state, CoroutineScope coroutineScope, State<? extends Function1<? super Boolean, Unit>> state2, State<Float> state3, State<? extends Function2<? super Boolean, ? super Float, Unit>> state4, Continuation<? super C00881> continuation) {
                    super(2, continuation);
                    this.$isRtl = z;
                    this.$maxPx = f;
                    this.$rangeSliderLogic = rangeSliderLogic;
                    this.$rawOffsetStart = state;
                    this.$$this$coroutineScope = coroutineScope;
                    this.$gestureEndAction = state2;
                    this.$rawOffsetEnd = state3;
                    this.$onDrag = state4;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                    C00881 c00881 = new C00881(this.$isRtl, this.$maxPx, this.$rangeSliderLogic, this.$rawOffsetStart, this.$$this$coroutineScope, this.$gestureEndAction, this.$rawOffsetEnd, this.$onDrag, continuation);
                    c00881.L$0 = obj;
                    return c00881;
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(AwaitPointerEventScope awaitPointerEventScope, Continuation<? super Unit> continuation) {
                    return ((C00881) create(awaitPointerEventScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                /* JADX WARN: Removed duplicated region for block: B:36:0x00e8  */
                /* JADX WARN: Removed duplicated region for block: B:54:0x0185  */
                /* JADX WARN: Removed duplicated region for block: B:57:0x018f A[Catch: CancellationException -> 0x01a1, TryCatch #1 {CancellationException -> 0x01a1, blocks: (B:8:0x001c, B:55:0x0187, B:57:0x018f, B:58:0x0197), top: B:67:0x001c }] */
                /* JADX WARN: Removed duplicated region for block: B:58:0x0197 A[Catch: CancellationException -> 0x01a1, TRY_LEAVE, TryCatch #1 {CancellationException -> 0x01a1, blocks: (B:8:0x001c, B:55:0x0187, B:57:0x018f, B:58:0x0197), top: B:67:0x001c }] */
                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct code enable 'Show inconsistent code' option in preferences
                */
                public final java.lang.Object invokeSuspend(java.lang.Object r17) {
                    /*
                        Method dump skipped, instruction units count: 463
                        To view this dump change 'Code comments level' option to 'DEBUG'
                    */
                    throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material.SliderKt.AnonymousClass1.C00871.C00881.invokeSuspend(java.lang.Object):java.lang.Object");
                }

                /* JADX INFO: renamed from: androidx.compose.material.SliderKt$rangeSliderPressDragModifier$1$1$1$2, reason: invalid class name */
                /* JADX INFO: compiled from: Slider.kt */
                @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
                @DebugMetadata(c = "androidx.compose.material.SliderKt$rangeSliderPressDragModifier$1$1$1$2", f = "Slider.kt", i = {}, l = {1040}, m = "invokeSuspend", n = {}, s = {})
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
                    if (ForEachGestureKt.awaitEachGesture(this.$$this$pointerInput, new C00881(this.$isRtl, this.$maxPx, this.$rangeSliderLogic, this.$rawOffsetStart, coroutineScope, this.$gestureEndAction, this.$rawOffsetEnd, this.$onDrag, null), this) == coroutine_suspended) {
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

    /* JADX INFO: Access modifiers changed from: private */
    public static final Modifier rangeSliderPressDragModifier(Modifier modifier, MutableInteractionSource mutableInteractionSource, MutableInteractionSource mutableInteractionSource2, State<Float> state, State<Float> state2, boolean z, boolean z2, float f, ClosedFloatingPointRange<Float> closedFloatingPointRange, State<? extends Function1<? super Boolean, Unit>> state3, State<? extends Function2<? super Boolean, ? super Float, Unit>> state4) {
        return z ? SuspendingPointerInputFilterKt.pointerInput(modifier, new Object[]{mutableInteractionSource, mutableInteractionSource2, Float.valueOf(f), Boolean.valueOf(z2), closedFloatingPointRange}, (Function2<? super PointerInputScope, ? super Continuation<? super Unit>, ? extends Object>) new AnonymousClass1(mutableInteractionSource, mutableInteractionSource2, state, state2, state4, z2, f, state3, null)) : modifier;
    }

    public static final float getThumbRadius() {
        return ThumbRadius;
    }

    public static final float getTrackHeight() {
        return TrackHeight;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final float snapValueToTick(float f, List<Float> list, float f2, float f3) {
        Float f4;
        if (list.isEmpty()) {
            f4 = null;
        } else {
            Float f5 = list.get(0);
            float fAbs = Math.abs(MathHelpersKt.lerp(f2, f3, f5.floatValue()) - f);
            int lastIndex = CollectionsKt.getLastIndex(list);
            int i = 1;
            if (1 <= lastIndex) {
                while (true) {
                    Float f6 = list.get(i);
                    float fAbs2 = Math.abs(MathHelpersKt.lerp(f2, f3, f6.floatValue()) - f);
                    if (Float.compare(fAbs, fAbs2) > 0) {
                        f5 = f6;
                        fAbs = fAbs2;
                    }
                    if (i == lastIndex) {
                        break;
                    }
                    i++;
                }
            }
            f4 = f5;
        }
        Float f7 = f4;
        return f7 != null ? MathHelpersKt.lerp(f2, f3, f7.floatValue()) : f;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Modifier sliderTapModifier(Modifier modifier, final DraggableState draggableState, final MutableInteractionSource mutableInteractionSource, final float f, final boolean z, final State<Float> state, final State<? extends Function1<? super Float, Unit>> state2, final MutableState<Float> mutableState, final boolean z2) {
        return ComposedModifierKt.composed(modifier, InspectableValueKt.isDebugInspectorInfoEnabled() ? new Function1<InspectorInfo, Unit>() { // from class: androidx.compose.material.SliderKt$sliderTapModifier$$inlined$debugInspectorInfo$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(InspectorInfo inspectorInfo) {
                invoke2(inspectorInfo);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(InspectorInfo inspectorInfo) {
                inspectorInfo.setName("sliderTapModifier");
                inspectorInfo.getProperties().set("draggableState", draggableState);
                inspectorInfo.getProperties().set("interactionSource", mutableInteractionSource);
                inspectorInfo.getProperties().set("maxPx", Float.valueOf(f));
                inspectorInfo.getProperties().set("isRtl", Boolean.valueOf(z));
                inspectorInfo.getProperties().set("rawOffset", state);
                inspectorInfo.getProperties().set("gestureEndAction", state2);
                inspectorInfo.getProperties().set("pressOffset", mutableState);
                inspectorInfo.getProperties().set("enabled", Boolean.valueOf(z2));
            }
        } : InspectableValueKt.getNoInspectorInfo(), new Function3<Modifier, Composer, Integer, Modifier>() { // from class: androidx.compose.material.SliderKt.sliderTapModifier.2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(3);
            }

            @Override // kotlin.jvm.functions.Function3
            public /* bridge */ /* synthetic */ Modifier invoke(Modifier modifier2, Composer composer, Integer num) {
                return invoke(modifier2, composer, num.intValue());
            }

            public final Modifier invoke(Modifier modifier2, Composer composer, int i) {
                composer.startReplaceGroup(1945228890);
                ComposerKt.sourceInformation(composer, "C:Slider.kt#jmzs0o");
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(1945228890, i, -1, "androidx.compose.material.sliderTapModifier.<anonymous> (Slider.kt:915)");
                }
                if (!z2) {
                    composer.startReplaceGroup(-397959404);
                    composer.endReplaceGroup();
                } else {
                    composer.startReplaceGroup(-398958937);
                    ComposerKt.sourceInformation(composer, "916@36114L24,917@36213L898");
                    ComposerKt.sourceInformationMarkerStart(composer, 773894976, "CC(rememberCoroutineScope)489@20472L144:Effects.kt#9igjgp");
                    ComposerKt.sourceInformationMarkerStart(composer, -954363344, "CC(remember):Effects.kt#9igjgp");
                    Object objRememberedValue = composer.rememberedValue();
                    if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                        Object compositionScopedCoroutineScopeCanceller = new CompositionScopedCoroutineScopeCanceller(EffectsKt.createCompositionCoroutineScope(EmptyCoroutineContext.INSTANCE, composer));
                        composer.updateRememberedValue(compositionScopedCoroutineScopeCanceller);
                        objRememberedValue = compositionScopedCoroutineScopeCanceller;
                    }
                    ComposerKt.sourceInformationMarkerEnd(composer);
                    CoroutineScope coroutineScope = ((CompositionScopedCoroutineScopeCanceller) objRememberedValue).getCoroutineScope();
                    ComposerKt.sourceInformationMarkerEnd(composer);
                    Object[] objArr = {draggableState, mutableInteractionSource, Float.valueOf(f), Boolean.valueOf(z)};
                    ComposerKt.sourceInformationMarkerStart(composer, -1952528428, "CC(remember):Slider.kt#9igjgp");
                    boolean zChanged = composer.changed(z) | composer.changed(f) | composer.changed(mutableState) | composer.changed(state) | composer.changedInstance(coroutineScope) | composer.changedInstance(draggableState) | composer.changed(state2);
                    boolean z3 = z;
                    float f2 = f;
                    MutableState<Float> mutableState2 = mutableState;
                    State<Float> state3 = state;
                    DraggableState draggableState2 = draggableState;
                    State<Function1<Float, Unit>> state4 = state2;
                    Object objRememberedValue2 = composer.rememberedValue();
                    if (zChanged || objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                        objRememberedValue2 = (Function2) new SliderKt$sliderTapModifier$2$1$1(z3, f2, mutableState2, state3, coroutineScope, draggableState2, state4, null);
                        composer.updateRememberedValue(objRememberedValue2);
                    }
                    ComposerKt.sourceInformationMarkerEnd(composer);
                    modifier2 = SuspendingPointerInputFilterKt.pointerInput(modifier2, objArr, (Function2<? super PointerInputScope, ? super Continuation<? super Unit>, ? extends Object>) objRememberedValue2);
                    composer.endReplaceGroup();
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                composer.endReplaceGroup();
                return modifier2;
            }
        });
    }

    static {
        float fM7027constructorimpl = Dp.m7027constructorimpl(48);
        SliderHeight = fM7027constructorimpl;
        float fM7027constructorimpl2 = Dp.m7027constructorimpl(144);
        SliderMinWidth = fM7027constructorimpl2;
        DefaultSliderConstraints = SizeKt.m998heightInVpY3zN4$default(SizeKt.m1017widthInVpY3zN4$default(Modifier.INSTANCE, fM7027constructorimpl2, 0.0f, 2, null), 0.0f, fM7027constructorimpl, 1, null);
        SliderToTickAnimation = new TweenSpec<>(100, 0, null, 6, null);
    }
}
