package androidx.compose.material;

import androidx.compose.animation.core.AnimationSpec;
import androidx.compose.foundation.gestures.DraggableKt;
import androidx.compose.foundation.gestures.DraggableState;
import androidx.compose.foundation.gestures.Orientation;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.DisposableEffectResult;
import androidx.compose.runtime.DisposableEffectScope;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.runtime.saveable.RememberSaveableKt;
import androidx.compose.runtime.saveable.Saver;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.platform.InspectableValueKt;
import androidx.compose.ui.platform.InspectorInfo;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.Dp;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.exifinterface.media.ExifInterface;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Swipeable.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u008c\u0001\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0010\"\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u001aP\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\r\u001a\u00020\u000b2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u000b0\u000f2\u0018\u0010\u0010\u001a\u0014\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u000b0\u00112\u0006\u0010\u0012\u001a\u00020\u000b2\u0006\u0010\u0013\u001a\u00020\u000bH\u0002\u001a$\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u000b0\u00152\u0006\u0010\f\u001a\u00020\u000b2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u000b0\u000fH\u0002\u001aZ\u0010\u0016\u001a\b\u0012\u0004\u0012\u0002H\u00040\u0005\"\b\b\u0000\u0010\u0004*\u00020\u00172\u0006\u0010\u0018\u001a\u0002H\u00042\u000e\b\u0002\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u000b0\u001a2#\b\u0002\u0010\u001b\u001a\u001d\u0012\u0013\u0012\u0011H\u0004¢\u0006\f\b\u001d\u0012\b\b\u001e\u0012\u0004\b\b(\u001f\u0012\u0004\u0012\u00020 0\u001cH\u0007¢\u0006\u0002\u0010!\u001aI\u0010\"\u001a\b\u0012\u0004\u0012\u0002H\u00040\u0005\"\b\b\u0000\u0010\u0004*\u00020\u00172\u0006\u0010#\u001a\u0002H\u00042\u0012\u0010$\u001a\u000e\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u00020%0\u001c2\u000e\b\u0002\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u000b0\u001aH\u0001¢\u0006\u0002\u0010&\u001a-\u0010'\u001a\u0004\u0018\u00010\u000b\"\u0004\b\u0000\u0010\u0004*\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u0002H\u00040(2\u0006\u0010)\u001a\u0002H\u0004H\u0002¢\u0006\u0002\u0010*\u001a¶\u0001\u0010+\u001a\u00020,\"\u0004\b\u0000\u0010\u0004*\u00020,2\f\u0010)\u001a\b\u0012\u0004\u0012\u0002H\u00040\u00052\u0012\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u0002H\u00040(2\u0006\u0010-\u001a\u00020.2\b\b\u0002\u0010/\u001a\u00020 2\b\b\u0002\u00100\u001a\u00020 2\n\b\u0002\u00101\u001a\u0004\u0018\u00010228\b\u0002\u0010\u0010\u001a2\u0012\u0013\u0012\u0011H\u0004¢\u0006\f\b\u001d\u0012\b\b\u001e\u0012\u0004\b\b(3\u0012\u0013\u0012\u0011H\u0004¢\u0006\f\b\u001d\u0012\b\b\u001e\u0012\u0004\b\b(4\u0012\u0004\u0012\u0002050\u00112\n\b\u0002\u00106\u001a\u0004\u0018\u0001072\b\b\u0002\u0010\u0013\u001a\u000208H\u0007ø\u0001\u0000¢\u0006\u0004\b9\u0010:\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"*\u0010\u0002\u001a\u00020\u0003\"\u0004\b\u0000\u0010\u0004*\b\u0012\u0004\u0012\u0002H\u00040\u00058@X\u0081\u0004¢\u0006\f\u0012\u0004\b\u0006\u0010\u0007\u001a\u0004\b\b\u0010\t\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006;"}, d2 = {"SwipeableDeprecation", "", "PreUpPostDownNestedScrollConnection", "Landroidx/compose/ui/input/nestedscroll/NestedScrollConnection;", ExifInterface.GPS_DIRECTION_TRUE, "Landroidx/compose/material/SwipeableState;", "getPreUpPostDownNestedScrollConnection$annotations", "(Landroidx/compose/material/SwipeableState;)V", "getPreUpPostDownNestedScrollConnection", "(Landroidx/compose/material/SwipeableState;)Landroidx/compose/ui/input/nestedscroll/NestedScrollConnection;", "computeTarget", "", TypedValues.CycleType.S_WAVE_OFFSET, "lastValue", "anchors", "", "thresholds", "Lkotlin/Function2;", "velocity", "velocityThreshold", "findBounds", "", "rememberSwipeableState", "", "initialValue", "animationSpec", "Landroidx/compose/animation/core/AnimationSpec;", "confirmStateChange", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "newValue", "", "(Ljava/lang/Object;Landroidx/compose/animation/core/AnimationSpec;Lkotlin/jvm/functions/Function1;Landroidx/compose/runtime/Composer;II)Landroidx/compose/material/SwipeableState;", "rememberSwipeableStateFor", "value", "onValueChange", "", "(Ljava/lang/Object;Lkotlin/jvm/functions/Function1;Landroidx/compose/animation/core/AnimationSpec;Landroidx/compose/runtime/Composer;II)Landroidx/compose/material/SwipeableState;", "getOffset", "", "state", "(Ljava/util/Map;Ljava/lang/Object;)Ljava/lang/Float;", "swipeable", "Landroidx/compose/ui/Modifier;", "orientation", "Landroidx/compose/foundation/gestures/Orientation;", "enabled", "reverseDirection", "interactionSource", "Landroidx/compose/foundation/interaction/MutableInteractionSource;", TypedValues.TransitionType.S_FROM, TypedValues.TransitionType.S_TO, "Landroidx/compose/material/ThresholdConfig;", "resistance", "Landroidx/compose/material/ResistanceConfig;", "Landroidx/compose/ui/unit/Dp;", "swipeable-pPrIpRY", "(Landroidx/compose/ui/Modifier;Landroidx/compose/material/SwipeableState;Ljava/util/Map;Landroidx/compose/foundation/gestures/Orientation;ZZLandroidx/compose/foundation/interaction/MutableInteractionSource;Lkotlin/jvm/functions/Function2;Landroidx/compose/material/ResistanceConfig;F)Landroidx/compose/ui/Modifier;", "material_release"}, k = 2, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
public final class SwipeableKt {
    private static final String SwipeableDeprecation = "Material's Swipeable has been replaced by Foundation's AnchoredDraggable APIs. Please see developer.android.com for an overview of the changes and a migration guide.";

    public static /* synthetic */ void getPreUpPostDownNestedScrollConnection$annotations(SwipeableState swipeableState) {
    }

    @Deprecated(message = SwipeableDeprecation)
    public static final <T> SwipeableState<T> rememberSwipeableState(final T t, final AnimationSpec<Float> animationSpec, final Function1<? super T, Boolean> function1, Composer composer, int i, int i2) {
        ComposerKt.sourceInformationMarkerStart(composer, -1237755169, "C(rememberSwipeableState)P(2)485@19667L177,480@19500L344:Swipeable.kt#jmzs0o");
        if ((i2 & 2) != 0) {
            animationSpec = SwipeableDefaults.INSTANCE.getAnimationSpec();
        }
        if ((i2 & 4) != 0) {
            function1 = new Function1<T, Boolean>() { // from class: androidx.compose.material.SwipeableKt.rememberSwipeableState.1
                /* JADX WARN: Can't rename method to resolve collision */
                @Override // kotlin.jvm.functions.Function1
                public final Boolean invoke(T t2) {
                    return true;
                }
            };
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1237755169, i, -1, "androidx.compose.material.rememberSwipeableState (Swipeable.kt:479)");
        }
        Object[] objArr = new Object[0];
        Saver<SwipeableState<T>, T> Saver = SwipeableState.INSTANCE.Saver(animationSpec, function1);
        ComposerKt.sourceInformationMarkerStart(composer, -400382047, "CC(remember):Swipeable.kt#9igjgp");
        boolean zChangedInstance = ((((i & 14) ^ 6) > 4 && composer.changedInstance(t)) || (i & 6) == 4) | composer.changedInstance(animationSpec) | ((((i & 896) ^ 384) > 256 && composer.changed(function1)) || (i & 384) == 256);
        Object objRememberedValue = composer.rememberedValue();
        if (zChangedInstance || objRememberedValue == Composer.INSTANCE.getEmpty()) {
            objRememberedValue = (Function0) new Function0<SwipeableState<T>>() { // from class: androidx.compose.material.SwipeableKt$rememberSwipeableState$2$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public final SwipeableState<T> invoke() {
                    return new SwipeableState<>(t, animationSpec, function1);
                }
            };
            composer.updateRememberedValue(objRememberedValue);
        }
        ComposerKt.sourceInformationMarkerEnd(composer);
        SwipeableState<T> swipeableState = (SwipeableState) RememberSaveableKt.m4139rememberSaveable(objArr, (Saver) Saver, (String) null, (Function0) objRememberedValue, composer, 0, 4);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd(composer);
        return swipeableState;
    }

    @Deprecated(message = SwipeableDeprecation)
    public static final <T> SwipeableState<T> rememberSwipeableStateFor(final T t, final Function1<? super T, Unit> function1, AnimationSpec<Float> animationSpec, Composer composer, int i, int i2) {
        ComposerKt.sourceInformationMarkerStart(composer, 1156387078, "C(rememberSwipeableStateFor)P(2,1)510@20701L169,517@20901L34,518@20989L113,518@20940L162,523@21153L213,523@21107L259:Swipeable.kt#jmzs0o");
        if ((i2 & 4) != 0) {
            animationSpec = SwipeableDefaults.INSTANCE.getAnimationSpec();
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1156387078, i, -1, "androidx.compose.material.rememberSwipeableStateFor (Swipeable.kt:509)");
        }
        ComposerKt.sourceInformationMarkerStart(composer, 2132120610, "CC(remember):Swipeable.kt#9igjgp");
        Object objRememberedValue = composer.rememberedValue();
        if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
            objRememberedValue = new SwipeableState(t, animationSpec, new Function1<T, Boolean>() { // from class: androidx.compose.material.SwipeableKt$rememberSwipeableStateFor$swipeableState$1$1
                /* JADX WARN: Can't rename method to resolve collision */
                @Override // kotlin.jvm.functions.Function1
                public final Boolean invoke(T t2) {
                    return true;
                }
            });
            composer.updateRememberedValue(objRememberedValue);
        }
        final SwipeableState<T> swipeableState = (SwipeableState) objRememberedValue;
        ComposerKt.sourceInformationMarkerEnd(composer);
        ComposerKt.sourceInformationMarkerStart(composer, 2132126875, "CC(remember):Swipeable.kt#9igjgp");
        Object objRememberedValue2 = composer.rememberedValue();
        if (objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
            objRememberedValue2 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(false, null, 2, null);
            composer.updateRememberedValue(objRememberedValue2);
        }
        final MutableState mutableState = (MutableState) objRememberedValue2;
        ComposerKt.sourceInformationMarkerEnd(composer);
        Object value = mutableState.getValue();
        ComposerKt.sourceInformationMarkerStart(composer, 2132129770, "CC(remember):Swipeable.kt#9igjgp");
        int i3 = (i & 14) ^ 6;
        boolean z = (i3 > 4 && composer.changedInstance(t)) || (i & 6) == 4;
        SwipeableKt$rememberSwipeableStateFor$1$1 swipeableKt$rememberSwipeableStateFor$1$1RememberedValue = composer.rememberedValue();
        if (z || swipeableKt$rememberSwipeableStateFor$1$1RememberedValue == Composer.INSTANCE.getEmpty()) {
            swipeableKt$rememberSwipeableStateFor$1$1RememberedValue = new SwipeableKt$rememberSwipeableStateFor$1$1(t, swipeableState, null);
            composer.updateRememberedValue(swipeableKt$rememberSwipeableStateFor$1$1RememberedValue);
        }
        ComposerKt.sourceInformationMarkerEnd(composer);
        int i4 = i & 8;
        EffectsKt.LaunchedEffect(t, value, (Function2) swipeableKt$rememberSwipeableStateFor$1$1RememberedValue, composer, i & 14);
        T currentValue = swipeableState.getCurrentValue();
        ComposerKt.sourceInformationMarkerStart(composer, 2132135118, "CC(remember):Swipeable.kt#9igjgp");
        boolean z2 = ((i3 > 4 && composer.changedInstance(t)) || (i & 6) == 4) | ((((i & 112) ^ 48) > 32 && composer.changed(function1)) || (i & 48) == 32);
        Object objRememberedValue3 = composer.rememberedValue();
        if (z2 || objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
            objRememberedValue3 = (Function1) new Function1<DisposableEffectScope, DisposableEffectResult>() { // from class: androidx.compose.material.SwipeableKt$rememberSwipeableStateFor$2$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                {
                    super(1);
                }

                /* JADX WARN: Type inference incomplete: some casts might be missing */
                @Override // kotlin.jvm.functions.Function1
                public final DisposableEffectResult invoke(DisposableEffectScope disposableEffectScope) {
                    if (!Intrinsics.areEqual(t, swipeableState.getCurrentValue())) {
                        function1.invoke((T) swipeableState.getCurrentValue());
                        mutableState.setValue(Boolean.valueOf(!r2.getValue().booleanValue()));
                    }
                    return new DisposableEffectResult() { // from class: androidx.compose.material.SwipeableKt$rememberSwipeableStateFor$2$1$invoke$$inlined$onDispose$1
                        @Override // androidx.compose.runtime.DisposableEffectResult
                        public void dispose() {
                        }
                    };
                }
            };
            composer.updateRememberedValue(objRememberedValue3);
        }
        ComposerKt.sourceInformationMarkerEnd(composer);
        EffectsKt.DisposableEffect(currentValue, (Function1<? super DisposableEffectScope, ? extends DisposableEffectResult>) objRememberedValue3, composer, i4);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd(composer);
        return swipeableState;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final List<Float> findBounds(float f, Set<Float> set) {
        Object obj;
        Set<Float> set2 = set;
        ArrayList arrayList = new ArrayList();
        for (Object obj2 : set2) {
            if (((Number) obj2).floatValue() <= ((double) f) + 0.001d) {
                arrayList.add(obj2);
            }
        }
        ArrayList arrayList2 = arrayList;
        Object obj3 = null;
        if (arrayList2.isEmpty()) {
            obj = null;
        } else {
            obj = arrayList2.get(0);
            float fFloatValue = ((Number) obj).floatValue();
            int lastIndex = CollectionsKt.getLastIndex(arrayList2);
            if (1 <= lastIndex) {
                int i = 1;
                while (true) {
                    Object obj4 = arrayList2.get(i);
                    float fFloatValue2 = ((Number) obj4).floatValue();
                    if (Float.compare(fFloatValue, fFloatValue2) < 0) {
                        obj = obj4;
                        fFloatValue = fFloatValue2;
                    }
                    if (i == lastIndex) {
                        break;
                    }
                    i++;
                }
            }
        }
        Float f2 = (Float) obj;
        ArrayList arrayList3 = new ArrayList();
        for (Object obj5 : set2) {
            if (((Number) obj5).floatValue() >= ((double) f) - 0.001d) {
                arrayList3.add(obj5);
            }
        }
        ArrayList arrayList4 = arrayList3;
        if (!arrayList4.isEmpty()) {
            Object obj6 = arrayList4.get(0);
            float fFloatValue3 = ((Number) obj6).floatValue();
            int lastIndex2 = CollectionsKt.getLastIndex(arrayList4);
            if (1 <= lastIndex2) {
                int i2 = 1;
                while (true) {
                    Object obj7 = arrayList4.get(i2);
                    float fFloatValue4 = ((Number) obj7).floatValue();
                    if (Float.compare(fFloatValue3, fFloatValue4) > 0) {
                        obj6 = obj7;
                        fFloatValue3 = fFloatValue4;
                    }
                    if (i2 == lastIndex2) {
                        break;
                    }
                    i2++;
                }
            }
            obj3 = obj6;
        }
        Float f3 = (Float) obj3;
        if (f2 == null) {
            return CollectionsKt.listOfNotNull(f3);
        }
        if (f3 == null) {
            return CollectionsKt.listOf(f2);
        }
        return Intrinsics.areEqual(f2, f3) ? CollectionsKt.listOf(f2) : CollectionsKt.listOf((Object[]) new Float[]{f2, f3});
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final float computeTarget(float f, float f2, Set<Float> set, Function2<? super Float, ? super Float, Float> function2, float f3, float f4) {
        List<Float> listFindBounds = findBounds(f, set);
        int size = listFindBounds.size();
        if (size == 0) {
            return f2;
        }
        if (size == 1) {
            return listFindBounds.get(0).floatValue();
        }
        float fFloatValue = listFindBounds.get(0).floatValue();
        float fFloatValue2 = listFindBounds.get(1).floatValue();
        return (f2 > f ? f3 > (-f4) && f > function2.invoke(Float.valueOf(fFloatValue2), Float.valueOf(fFloatValue)).floatValue() : f3 >= f4 || f >= function2.invoke(Float.valueOf(fFloatValue), Float.valueOf(fFloatValue2)).floatValue()) ? fFloatValue2 : fFloatValue;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final <T> Float getOffset(Map<Float, ? extends T> map, T t) {
        T next;
        Iterator<T> it = map.entrySet().iterator();
        while (true) {
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
            if (Intrinsics.areEqual(((Map.Entry) next).getValue(), t)) {
                break;
            }
        }
        Map.Entry entry = (Map.Entry) next;
        if (entry != null) {
            return (Float) entry.getKey();
        }
        return null;
    }

    public static final <T> NestedScrollConnection getPreUpPostDownNestedScrollConnection(SwipeableState<T> swipeableState) {
        return new SwipeableKt$PreUpPostDownNestedScrollConnection$1(swipeableState);
    }

    @Deprecated(message = SwipeableDeprecation)
    /* JADX INFO: renamed from: swipeable-pPrIpRY, reason: not valid java name */
    public static final <T> Modifier m1969swipeablepPrIpRY(Modifier modifier, final SwipeableState<T> swipeableState, final Map<Float, ? extends T> map, final Orientation orientation, final boolean z, final boolean z2, final MutableInteractionSource mutableInteractionSource, final Function2<? super T, ? super T, ? extends ThresholdConfig> function2, final ResistanceConfig resistanceConfig, final float f) {
        return ComposedModifierKt.composed(modifier, InspectableValueKt.isDebugInspectorInfoEnabled() ? new Function1<InspectorInfo, Unit>() { // from class: androidx.compose.material.SwipeableKt$swipeable-pPrIpRY$$inlined$debugInspectorInfo$1
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
                inspectorInfo.setName("swipeable");
                inspectorInfo.getProperties().set("state", swipeableState);
                inspectorInfo.getProperties().set("anchors", map);
                inspectorInfo.getProperties().set("orientation", orientation);
                inspectorInfo.getProperties().set("enabled", Boolean.valueOf(z));
                inspectorInfo.getProperties().set("reverseDirection", Boolean.valueOf(z2));
                inspectorInfo.getProperties().set("interactionSource", mutableInteractionSource);
                inspectorInfo.getProperties().set("thresholds", function2);
                inspectorInfo.getProperties().set("resistance", resistanceConfig);
                inspectorInfo.getProperties().set("velocityThreshold", Dp.m7025boximpl(f));
            }
        } : InspectableValueKt.getNoInspectorInfo(), new Function3<Modifier, Composer, Integer, Modifier>() { // from class: androidx.compose.material.SwipeableKt$swipeable$3
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(3);
            }

            @Override // kotlin.jvm.functions.Function3
            public /* bridge */ /* synthetic */ Modifier invoke(Modifier modifier2, Composer composer, Integer num) {
                return invoke(modifier2, composer, num.intValue());
            }

            /* JADX WARN: Type inference fix 'apply assigned field type' failed
            java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
            	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
            	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
            	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
            	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
            	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
            	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
            	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
             */
            public final Modifier invoke(Modifier modifier2, Composer composer, int i) {
                composer.startReplaceGroup(43594985);
                ComposerKt.sourceInformation(composer, "C602@25169L7,604@25242L471,604@25211L502,625@25974L55:Swipeable.kt#jmzs0o");
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(43594985, i, -1, "androidx.compose.material.swipeable.<anonymous> (Swipeable.kt:596)");
                }
                if (map.isEmpty()) {
                    throw new IllegalArgumentException("You must have at least one anchor.".toString());
                }
                if (CollectionsKt.distinct(map.values()).size() != map.size()) {
                    throw new IllegalArgumentException("You cannot have two anchors mapped to the same state.".toString());
                }
                ProvidableCompositionLocal<Density> localDensity = CompositionLocalsKt.getLocalDensity();
                ComposerKt.sourceInformationMarkerStart(composer, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                Object objConsume = composer.consume(localDensity);
                ComposerKt.sourceInformationMarkerEnd(composer);
                Density density = (Density) objConsume;
                swipeableState.ensureInit$material_release(map);
                Object obj = map;
                Object obj2 = swipeableState;
                ComposerKt.sourceInformationMarkerStart(composer, -2035096284, "CC(remember):Swipeable.kt#9igjgp");
                boolean zChanged = composer.changed(swipeableState) | composer.changedInstance(map) | composer.changed(resistanceConfig) | composer.changed(function2) | composer.changed(density) | composer.changed(f);
                SwipeableState<T> swipeableState2 = swipeableState;
                Map<Float, T> map2 = map;
                ResistanceConfig resistanceConfig2 = resistanceConfig;
                Function2<T, T, ThresholdConfig> function22 = function2;
                float f2 = f;
                Object objRememberedValue = composer.rememberedValue();
                if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue = (Function2) new SwipeableKt$swipeable$3$3$1(swipeableState2, map2, resistanceConfig2, density, function22, f2, null);
                    composer.updateRememberedValue(objRememberedValue);
                }
                ComposerKt.sourceInformationMarkerEnd(composer);
                EffectsKt.LaunchedEffect(obj, obj2, (Function2) objRememberedValue, composer, 0);
                Modifier.Companion companion = Modifier.INSTANCE;
                boolean zIsAnimationRunning = swipeableState.isAnimationRunning();
                DraggableState draggableState = swipeableState.getDraggableState();
                Modifier.Companion companion2 = companion;
                Orientation orientation2 = orientation;
                boolean z3 = z;
                MutableInteractionSource mutableInteractionSource2 = mutableInteractionSource;
                ComposerKt.sourceInformationMarkerStart(composer, -2035073276, "CC(remember):Swipeable.kt#9igjgp");
                boolean zChanged2 = composer.changed(swipeableState);
                SwipeableState<T> swipeableState3 = swipeableState;
                Object objRememberedValue2 = composer.rememberedValue();
                if (zChanged2 || objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue2 = (Function3) new SwipeableKt$swipeable$3$4$1(swipeableState3, null);
                    composer.updateRememberedValue(objRememberedValue2);
                }
                ComposerKt.sourceInformationMarkerEnd(composer);
                Modifier modifierDraggable$default = DraggableKt.draggable$default(companion2, draggableState, orientation2, z3, mutableInteractionSource2, zIsAnimationRunning, null, (Function3) objRememberedValue2, z2, 32, null);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                composer.endReplaceGroup();
                return modifierDraggable$default;
            }
        });
    }
}
