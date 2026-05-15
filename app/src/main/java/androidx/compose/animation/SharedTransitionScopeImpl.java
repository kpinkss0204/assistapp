package androidx.compose.animation;

import androidx.collection.MutableScatterMap;
import androidx.compose.animation.SharedTransitionScope;
import androidx.compose.animation.core.AnimationVector4D;
import androidx.compose.animation.core.MutableTransitionState;
import androidx.compose.animation.core.Transition;
import androidx.compose.animation.core.VectorConvertersKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.runtime.snapshots.SnapshotStateList;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.graphics.AndroidPath_androidKt;
import androidx.compose.ui.graphics.OutlineKt;
import androidx.compose.ui.graphics.Path;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.graphics.drawscope.ContentDrawScope;
import androidx.compose.ui.layout.LayoutCoordinates;
import androidx.compose.ui.layout.LookaheadScope;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.LayoutDirection;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.exifinterface.media.ExifInterface;
import java.util.Comparator;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.TypeIntrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: SharedTransitionScope.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000à\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0007\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\t\b\u0001\u0018\u00002\u00020\u00012\u00020\u0002:\u0001uB\u0017\b\u0000\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0010\u0010,\u001a\u00020-2\u0006\u0010.\u001a\u00020/H\u0016J\u0015\u00100\u001a\u00020\u001b2\u0006\u00101\u001a\u000202H\u0000¢\u0006\u0002\b3J\u0015\u00104\u001a\u00020\u001b2\u0006\u00105\u001a\u00020\u001eH\u0000¢\u0006\u0002\b6J\u0015\u00107\u001a\u00020\u001b2\u0006\u00105\u001a\u00020\u001eH\u0000¢\u0006\u0002\b8J\u0015\u00109\u001a\u00020\u001b2\u0006\u0010:\u001a\u00020;H\u0000¢\u0006\u0002\b<J\u0015\u0010=\u001a\u00020\u001b2\u0006\u0010:\u001a\u00020;H\u0000¢\u0006\u0002\b>J\u0015\u0010?\u001a\u00020@2\u0006\u0010A\u001a\u00020$H\u0017¢\u0006\u0002\u0010BJM\u0010C\u001a\u00020;2\u0006\u0010D\u001a\u00020%2\u0006\u0010E\u001a\u00020F2\u0006\u0010G\u001a\u00020H2\u0006\u0010I\u001a\u00020\n2\u0006\u0010J\u001a\u00020@2\u0006\u0010K\u001a\u00020-2\u0006\u0010L\u001a\u00020M2\u0006\u0010N\u001a\u00020\nH\u0003¢\u0006\u0002\u0010OJ\u0010\u0010P\u001a\u00020%2\u0006\u0010A\u001a\u00020$H\u0002J\b\u0010&\u001a\u00020\u001bH\u0002J3\u0010Q\u001a\u00020R*\u00020\u00122\u0006\u0010S\u001a\u00020\u00122\b\b\u0002\u0010T\u001a\u00020R2\b\b\u0002\u0010U\u001a\u00020\nH\u0096\u0001ø\u0001\u0000¢\u0006\u0004\bV\u0010WJ>\u0010X\u001a\u00020Y*\u00020Y2\f\u0010Z\u001a\b\u0012\u0004\u0012\u00020\n0\u001a2\u0006\u0010L\u001a\u00020M2\u001a\u0010K\u001a\u0016\u0012\u0004\u0012\u00020\\\u0012\u0004\u0012\u00020]\u0012\u0006\u0012\u0004\u0018\u00010^0[H\u0016J\\\u0010_\u001a\u00020Y*\u00020Y2\u0006\u0010J\u001a\u00020@2\u0006\u0010`\u001a\u00020a2\u0006\u0010b\u001a\u00020c2\u0006\u0010d\u001a\u00020e2\u0006\u0010f\u001a\u00020g2\u0006\u0010h\u001a\u00020i2\u0006\u0010G\u001a\u00020H2\u0006\u0010N\u001a\u00020\n2\u0006\u0010L\u001a\u00020M2\u0006\u0010K\u001a\u00020-H\u0016Jp\u0010j\u001a\u00020Y\"\u0004\b\u0000\u0010k*\u00020Y2\u0006\u0010J\u001a\u00020@2\u000e\u0010l\u001a\n\u0012\u0004\u0012\u0002Hk\u0018\u00010m2\u0012\u0010n\u001a\u000e\u0012\u0004\u0012\u0002Hk\u0012\u0004\u0012\u00020\n0'2\u0006\u0010f\u001a\u00020g2\b\b\u0002\u0010G\u001a\u00020H2\u0006\u0010I\u001a\u00020\n2\u0006\u0010N\u001a\u00020\n2\u0006\u0010L\u001a\u00020M2\u0006\u0010K\u001a\u00020-H\u0002JS\u0010o\u001a\u00020Y*\u00020Y2\u0006\u0010J\u001a\u00020@2\u0006\u0010n\u001a\u00020\n2\b\b\u0002\u0010f\u001a\u00020g2\b\b\u0002\u0010G\u001a\u00020H2\b\b\u0002\u0010N\u001a\u00020\n2\b\b\u0002\u0010L\u001a\u00020M2\b\b\u0002\u0010K\u001a\u00020-H\u0000¢\u0006\u0002\bpJD\u0010D\u001a\u00020Y*\u00020Y2\u0006\u0010q\u001a\u00020@2\u0006\u0010`\u001a\u00020a2\u0006\u0010f\u001a\u00020g2\u0006\u0010G\u001a\u00020H2\u0006\u0010N\u001a\u00020\n2\u0006\u0010L\u001a\u00020M2\u0006\u0010K\u001a\u00020-H\u0016JD\u0010r\u001a\u00020Y*\u00020Y2\u0006\u0010J\u001a\u00020@2\u0006\u0010n\u001a\u00020\n2\u0006\u0010f\u001a\u00020g2\u0006\u0010G\u001a\u00020H2\u0006\u0010N\u001a\u00020\n2\u0006\u0010L\u001a\u00020M2\u0006\u0010K\u001a\u00020-H\u0016J\f\u0010s\u001a\u00020Y*\u00020YH\u0016J\r\u0010t\u001a\u00020\u0012*\u00020\u0012H\u0096\u0001R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR+\u0010\u000b\u001a\u00020\n2\u0006\u0010\t\u001a\u00020\n8V@RX\u0096\u008e\u0002¢\u0006\u0012\n\u0004\b\u000f\u0010\u0010\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u0014\u0010\u0011\u001a\u00020\u00128@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0014R\u001c\u0010\u0015\u001a\u0004\u0018\u00010\u0012X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0014\"\u0004\b\u0017\u0010\u0018R\u0014\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u001b0\u001aX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u001e0\u001dX\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u001f\u001a\u00020\u0012X\u0080.¢\u0006\u000e\n\u0000\u001a\u0004\b \u0010\u0014\"\u0004\b!\u0010\u0018R\u001a\u0010\"\u001a\u000e\u0012\u0004\u0012\u00020$\u0012\u0004\u0012\u00020%0#X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010&\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u001b0'X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010(\u001a\u00020\u0012*\u00020)X\u0096\u0005¢\u0006\u0006\u001a\u0004\b*\u0010+\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006v"}, d2 = {"Landroidx/compose/animation/SharedTransitionScopeImpl;", "Landroidx/compose/animation/SharedTransitionScope;", "Landroidx/compose/ui/layout/LookaheadScope;", "lookaheadScope", "coroutineScope", "Lkotlinx/coroutines/CoroutineScope;", "(Landroidx/compose/ui/layout/LookaheadScope;Lkotlinx/coroutines/CoroutineScope;)V", "getCoroutineScope", "()Lkotlinx/coroutines/CoroutineScope;", "<set-?>", "", "isTransitionActive", "()Z", "setTransitionActive", "(Z)V", "isTransitionActive$delegate", "Landroidx/compose/runtime/MutableState;", "lookaheadRoot", "Landroidx/compose/ui/layout/LayoutCoordinates;", "getLookaheadRoot$animation_release", "()Landroidx/compose/ui/layout/LayoutCoordinates;", "nullableLookaheadRoot", "getNullableLookaheadRoot$animation_release", "setNullableLookaheadRoot$animation_release", "(Landroidx/compose/ui/layout/LayoutCoordinates;)V", "observeAnimatingBlock", "Lkotlin/Function0;", "", "renderers", "Landroidx/compose/runtime/snapshots/SnapshotStateList;", "Landroidx/compose/animation/LayerRenderer;", "root", "getRoot$animation_release", "setRoot$animation_release", "sharedElements", "Landroidx/collection/MutableScatterMap;", "", "Landroidx/compose/animation/SharedElement;", "updateTransitionActiveness", "Lkotlin/Function1;", "lookaheadScopeCoordinates", "Landroidx/compose/ui/layout/Placeable$PlacementScope;", "getLookaheadScopeCoordinates", "(Landroidx/compose/ui/layout/Placeable$PlacementScope;)Landroidx/compose/ui/layout/LayoutCoordinates;", "OverlayClip", "Landroidx/compose/animation/SharedTransitionScope$OverlayClip;", "clipShape", "Landroidx/compose/ui/graphics/Shape;", "drawInOverlay", "scope", "Landroidx/compose/ui/graphics/drawscope/ContentDrawScope;", "drawInOverlay$animation_release", "onLayerRendererCreated", "renderer", "onLayerRendererCreated$animation_release", "onLayerRendererRemoved", "onLayerRendererRemoved$animation_release", "onStateAdded", "sharedElementState", "Landroidx/compose/animation/SharedElementInternalState;", "onStateAdded$animation_release", "onStateRemoved", "onStateRemoved$animation_release", "rememberSharedContentState", "Landroidx/compose/animation/SharedTransitionScope$SharedContentState;", "key", "(Ljava/lang/Object;Landroidx/compose/runtime/Composer;I)Landroidx/compose/animation/SharedTransitionScope$SharedContentState;", "rememberSharedElementState", "sharedElement", "boundsAnimation", "Landroidx/compose/animation/BoundsAnimation;", "placeHolderSize", "Landroidx/compose/animation/SharedTransitionScope$PlaceHolderSize;", "renderOnlyWhenVisible", "sharedContentState", "clipInOverlayDuringTransition", "zIndexInOverlay", "", "renderInOverlayDuringTransition", "(Landroidx/compose/animation/SharedElement;Landroidx/compose/animation/BoundsAnimation;Landroidx/compose/animation/SharedTransitionScope$PlaceHolderSize;ZLandroidx/compose/animation/SharedTransitionScope$SharedContentState;Landroidx/compose/animation/SharedTransitionScope$OverlayClip;FZLandroidx/compose/runtime/Composer;I)Landroidx/compose/animation/SharedElementInternalState;", "sharedElementsFor", "localLookaheadPositionOf", "Landroidx/compose/ui/geometry/Offset;", "sourceCoordinates", "relativeToSource", "includeMotionFrameOfReference", "localLookaheadPositionOf-au-aQtc", "(Landroidx/compose/ui/layout/LayoutCoordinates;Landroidx/compose/ui/layout/LayoutCoordinates;JZ)J", "renderInSharedTransitionScopeOverlay", "Landroidx/compose/ui/Modifier;", "renderInOverlay", "Lkotlin/Function2;", "Landroidx/compose/ui/unit/LayoutDirection;", "Landroidx/compose/ui/unit/Density;", "Landroidx/compose/ui/graphics/Path;", "sharedBounds", "animatedVisibilityScope", "Landroidx/compose/animation/AnimatedVisibilityScope;", "enter", "Landroidx/compose/animation/EnterTransition;", "exit", "Landroidx/compose/animation/ExitTransition;", "boundsTransform", "Landroidx/compose/animation/BoundsTransform;", "resizeMode", "Landroidx/compose/animation/SharedTransitionScope$ResizeMode;", "sharedBoundsImpl", ExifInterface.GPS_DIRECTION_TRUE, "parentTransition", "Landroidx/compose/animation/core/Transition;", "visible", "sharedBoundsWithCallerManagedVisibility", "sharedBoundsWithCallerManagedVisibility$animation_release", "state", "sharedElementWithCallerManagedVisibility", "skipToLookaheadSize", "toLookaheadCoordinates", "ShapeBasedClip", "animation_release"}, k = 1, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
public final class SharedTransitionScopeImpl implements SharedTransitionScope, LookaheadScope {
    public static final int $stable = 0;
    private final /* synthetic */ LookaheadScope $$delegate_0;
    private final CoroutineScope coroutineScope;
    private LayoutCoordinates nullableLookaheadRoot;
    public LayoutCoordinates root;

    /* JADX INFO: renamed from: isTransitionActive$delegate, reason: from kotlin metadata */
    private final MutableState isTransitionActive = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(false, null, 2, null);
    private final Function0<Unit> observeAnimatingBlock = new Function0<Unit>() { // from class: androidx.compose.animation.SharedTransitionScopeImpl$observeAnimatingBlock$1
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
            MutableScatterMap mutableScatterMap = this.this$0.sharedElements;
            Object[] objArr = mutableScatterMap.keys;
            Object[] objArr2 = mutableScatterMap.values;
            long[] jArr = mutableScatterMap.metadata;
            int length = jArr.length - 2;
            if (length < 0) {
                return;
            }
            int i = 0;
            while (true) {
                long j = jArr[i];
                if ((((~j) << 7) & j & (-9187201950435737472L)) != -9187201950435737472L) {
                    int i2 = 8 - ((~(i - length)) >>> 31);
                    for (int i3 = 0; i3 < i2; i3++) {
                        if ((255 & j) < 128) {
                            int i4 = (i << 3) + i3;
                            Object obj = objArr[i4];
                            if (((SharedElement) objArr2[i4]).isAnimating()) {
                                return;
                            }
                        }
                        j >>= 8;
                    }
                    if (i2 != 8) {
                        return;
                    }
                }
                if (i == length) {
                    return;
                } else {
                    i++;
                }
            }
        }
    };
    private final Function1<SharedTransitionScope, Unit> updateTransitionActiveness = new Function1<SharedTransitionScope, Unit>() { // from class: androidx.compose.animation.SharedTransitionScopeImpl.updateTransitionActiveness.1
        {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(SharedTransitionScope sharedTransitionScope) {
            invoke2(sharedTransitionScope);
            return Unit.INSTANCE;
        }

        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
        public final void invoke2(SharedTransitionScope sharedTransitionScope) {
            SharedTransitionScopeImpl.this.updateTransitionActiveness();
        }
    };
    private final SnapshotStateList<LayerRenderer> renderers = SnapshotStateKt.mutableStateListOf();
    private final MutableScatterMap<Object, SharedElement> sharedElements = new MutableScatterMap<>(0, 1, null);

    @Override // androidx.compose.ui.layout.LookaheadScope
    public LayoutCoordinates getLookaheadScopeCoordinates(Placeable.PlacementScope placementScope) {
        return this.$$delegate_0.getLookaheadScopeCoordinates(placementScope);
    }

    @Override // androidx.compose.ui.layout.LookaheadScope
    /* JADX INFO: renamed from: localLookaheadPositionOf-au-aQtc, reason: not valid java name */
    public long mo393localLookaheadPositionOfauaQtc(LayoutCoordinates layoutCoordinates, LayoutCoordinates layoutCoordinates2, long j, boolean z) {
        return this.$$delegate_0.mo393localLookaheadPositionOfauaQtc(layoutCoordinates, layoutCoordinates2, j, z);
    }

    @Override // androidx.compose.ui.layout.LookaheadScope
    public LayoutCoordinates toLookaheadCoordinates(LayoutCoordinates layoutCoordinates) {
        return this.$$delegate_0.toLookaheadCoordinates(layoutCoordinates);
    }

    public SharedTransitionScopeImpl(LookaheadScope lookaheadScope, CoroutineScope coroutineScope) {
        this.coroutineScope = coroutineScope;
        this.$$delegate_0 = lookaheadScope;
    }

    public final CoroutineScope getCoroutineScope() {
        return this.coroutineScope;
    }

    private void setTransitionActive(boolean z) {
        this.isTransitionActive.setValue(Boolean.valueOf(z));
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // androidx.compose.animation.SharedTransitionScope
    public boolean isTransitionActive() {
        return ((Boolean) this.isTransitionActive.getValue()).booleanValue();
    }

    @Override // androidx.compose.animation.SharedTransitionScope
    public Modifier skipToLookaheadSize(Modifier modifier) {
        return modifier.then(new SkipToLookaheadElement(null, 0 == true ? 1 : 0, 3, 0 == true ? 1 : 0));
    }

    @Override // androidx.compose.animation.SharedTransitionScope
    public Modifier renderInSharedTransitionScopeOverlay(Modifier modifier, Function0<Boolean> function0, float f, Function2<? super LayoutDirection, ? super Density, ? extends Path> function2) {
        return modifier.then(new RenderInTransitionOverlayNodeElement(this, function0, f, function2));
    }

    @Override // androidx.compose.animation.SharedTransitionScope
    public Modifier sharedElement(Modifier modifier, SharedTransitionScope.SharedContentState sharedContentState, AnimatedVisibilityScope animatedVisibilityScope, BoundsTransform boundsTransform, SharedTransitionScope.PlaceHolderSize placeHolderSize, boolean z, float f, SharedTransitionScope.OverlayClip overlayClip) {
        return sharedBoundsImpl(modifier, sharedContentState, animatedVisibilityScope.getTransition(), new Function1<EnterExitState, Boolean>() { // from class: androidx.compose.animation.SharedTransitionScopeImpl.sharedElement.1
            @Override // kotlin.jvm.functions.Function1
            public final Boolean invoke(EnterExitState enterExitState) {
                return Boolean.valueOf(enterExitState == EnterExitState.Visible);
            }
        }, boundsTransform, placeHolderSize, true, z, f, overlayClip);
    }

    @Override // androidx.compose.animation.SharedTransitionScope
    public Modifier sharedBounds(Modifier modifier, final SharedTransitionScope.SharedContentState sharedContentState, final AnimatedVisibilityScope animatedVisibilityScope, final EnterTransition enterTransition, final ExitTransition exitTransition, BoundsTransform boundsTransform, final SharedTransitionScope.ResizeMode resizeMode, SharedTransitionScope.PlaceHolderSize placeHolderSize, boolean z, float f, SharedTransitionScope.OverlayClip overlayClip) {
        return ComposedModifierKt.composed$default(sharedBoundsImpl(modifier, sharedContentState, animatedVisibilityScope.getTransition(), new Function1<EnterExitState, Boolean>() { // from class: androidx.compose.animation.SharedTransitionScopeImpl.sharedBounds.1
            @Override // kotlin.jvm.functions.Function1
            public final Boolean invoke(EnterExitState enterExitState) {
                return Boolean.valueOf(enterExitState == EnterExitState.Visible);
            }
        }, boundsTransform, placeHolderSize, false, z, f, overlayClip), null, new Function3<Modifier, Composer, Integer, Modifier>() { // from class: androidx.compose.animation.SharedTransitionScopeImpl.sharedBounds.2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(3);
            }

            @Override // kotlin.jvm.functions.Function3
            public /* bridge */ /* synthetic */ Modifier invoke(Modifier modifier2, Composer composer, Integer num) {
                return invoke(modifier2, composer, num.intValue());
            }

            public final Modifier invoke(Modifier modifier2, Composer composer, int i) {
                Modifier.Companion companionCreateContentScaleModifier;
                composer.startReplaceGroup(-419341573);
                ComposerKt.sourceInformation(composer, "C779@41927L35,771@41404L656:SharedTransitionScope.kt#xbi5r1");
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-419341573, i, -1, "androidx.compose.animation.SharedTransitionScopeImpl.sharedBounds.<anonymous> (SharedTransitionScope.kt:770)");
                }
                Transition<EnterExitState> transition = animatedVisibilityScope.getTransition();
                EnterTransition enterTransition2 = enterTransition;
                ExitTransition exitTransition2 = exitTransition;
                ComposerKt.sourceInformationMarkerStart(composer, -1411456741, "CC(remember):SharedTransitionScope.kt#9igjgp");
                boolean zChangedInstance = composer.changedInstance(sharedContentState);
                final SharedTransitionScope.SharedContentState sharedContentState2 = sharedContentState;
                Object objRememberedValue = composer.rememberedValue();
                if (zChangedInstance || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue = (Function0) new Function0<Boolean>() { // from class: androidx.compose.animation.SharedTransitionScopeImpl$sharedBounds$2$1$1
                        {
                            super(0);
                        }

                        /* JADX WARN: Can't rename method to resolve collision */
                        @Override // kotlin.jvm.functions.Function0
                        public final Boolean invoke() {
                            return Boolean.valueOf(sharedContentState2.isMatchFound());
                        }
                    };
                    composer.updateRememberedValue(objRememberedValue);
                }
                ComposerKt.sourceInformationMarkerEnd(composer);
                Modifier modifierCreateModifier = EnterExitTransitionKt.createModifier(transition, enterTransition2, exitTransition2, (Function0) objRememberedValue, "enter/exit for " + sharedContentState.getKey(), composer, 0, 0);
                if (resizeMode instanceof ScaleToBoundsImpl) {
                    composer.startReplaceGroup(-805247216);
                    ComposerKt.sourceInformation(composer, "784@42227L530");
                    Modifier.Companion companion = Modifier.INSTANCE;
                    ScaleToBoundsImpl scaleToBoundsImpl = (ScaleToBoundsImpl) resizeMode;
                    ComposerKt.sourceInformationMarkerStart(composer, -1411446646, "CC(remember):SharedTransitionScope.kt#9igjgp");
                    boolean zChangedInstance2 = composer.changedInstance(sharedContentState);
                    final SharedTransitionScope.SharedContentState sharedContentState3 = sharedContentState;
                    Object objRememberedValue2 = composer.rememberedValue();
                    if (zChangedInstance2 || objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                        objRememberedValue2 = (Function0) new Function0<Boolean>() { // from class: androidx.compose.animation.SharedTransitionScopeImpl$sharedBounds$2$2$1
                            {
                                super(0);
                            }

                            /* JADX WARN: Can't rename method to resolve collision */
                            @Override // kotlin.jvm.functions.Function0
                            public final Boolean invoke() {
                                return Boolean.valueOf(sharedContentState3.isMatchFound());
                            }
                        };
                        composer.updateRememberedValue(objRememberedValue2);
                    }
                    ComposerKt.sourceInformationMarkerEnd(composer);
                    companionCreateContentScaleModifier = SharedTransitionScopeKt.createContentScaleModifier(companion, scaleToBoundsImpl, (Function0) objRememberedValue2);
                    composer.endReplaceGroup();
                } else {
                    composer.startReplaceGroup(-804630006);
                    composer.endReplaceGroup();
                    companionCreateContentScaleModifier = Modifier.INSTANCE;
                }
                Modifier modifierThen = modifierCreateModifier.then(companionCreateContentScaleModifier);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                composer.endReplaceGroup();
                return modifierThen;
            }
        }, 1, null);
    }

    @Override // androidx.compose.animation.SharedTransitionScope
    public Modifier sharedElementWithCallerManagedVisibility(Modifier modifier, SharedTransitionScope.SharedContentState sharedContentState, final boolean z, BoundsTransform boundsTransform, SharedTransitionScope.PlaceHolderSize placeHolderSize, boolean z2, float f, SharedTransitionScope.OverlayClip overlayClip) {
        return sharedBoundsImpl(modifier, sharedContentState, null, new Function1<Unit, Boolean>() { // from class: androidx.compose.animation.SharedTransitionScopeImpl.sharedElementWithCallerManagedVisibility.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Boolean invoke(Unit unit) {
                return Boolean.valueOf(z);
            }
        }, boundsTransform, placeHolderSize, true, z2, f, overlayClip);
    }

    public static /* synthetic */ Modifier sharedBoundsWithCallerManagedVisibility$animation_release$default(SharedTransitionScopeImpl sharedTransitionScopeImpl, Modifier modifier, SharedTransitionScope.SharedContentState sharedContentState, boolean z, BoundsTransform boundsTransform, SharedTransitionScope.PlaceHolderSize placeHolderSize, boolean z2, float f, SharedTransitionScope.OverlayClip overlayClip, int i, Object obj) {
        if ((i & 4) != 0) {
            boundsTransform = SharedTransitionScopeKt.DefaultBoundsTransform;
        }
        BoundsTransform boundsTransform2 = boundsTransform;
        if ((i & 8) != 0) {
            placeHolderSize = SharedTransitionScope.PlaceHolderSize.INSTANCE.getContentSize();
        }
        return sharedTransitionScopeImpl.sharedBoundsWithCallerManagedVisibility$animation_release(modifier, sharedContentState, z, boundsTransform2, placeHolderSize, (i & 16) != 0 ? true : z2, (i & 32) != 0 ? 0.0f : f, (i & 64) != 0 ? SharedTransitionScopeKt.ParentClip : overlayClip);
    }

    public final Modifier sharedBoundsWithCallerManagedVisibility$animation_release(Modifier modifier, SharedTransitionScope.SharedContentState sharedContentState, final boolean z, BoundsTransform boundsTransform, SharedTransitionScope.PlaceHolderSize placeHolderSize, boolean z2, float f, SharedTransitionScope.OverlayClip overlayClip) {
        return sharedBoundsImpl(modifier, sharedContentState, null, new Function1<Unit, Boolean>() { // from class: androidx.compose.animation.SharedTransitionScopeImpl$sharedBoundsWithCallerManagedVisibility$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Boolean invoke(Unit unit) {
                return Boolean.valueOf(z);
            }
        }, boundsTransform, placeHolderSize, false, z2, f, overlayClip);
    }

    @Override // androidx.compose.animation.SharedTransitionScope
    public SharedTransitionScope.OverlayClip OverlayClip(Shape clipShape) {
        return new ShapeBasedClip(clipShape);
    }

    @Override // androidx.compose.animation.SharedTransitionScope
    public SharedTransitionScope.SharedContentState rememberSharedContentState(Object obj, Composer composer, int i) {
        composer.startReplaceGroup(799702514);
        ComposerKt.sourceInformation(composer, "C(rememberSharedContentState)912@49465L53:SharedTransitionScope.kt#xbi5r1");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(799702514, i, -1, "androidx.compose.animation.SharedTransitionScopeImpl.rememberSharedContentState (SharedTransitionScope.kt:912)");
        }
        ComposerKt.sourceInformationMarkerStart(composer, 935469886, "CC(remember):SharedTransitionScope.kt#9igjgp");
        boolean zChanged = composer.changed(obj);
        Object objRememberedValue = composer.rememberedValue();
        if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
            objRememberedValue = new SharedTransitionScope.SharedContentState(obj);
            composer.updateRememberedValue(objRememberedValue);
        }
        SharedTransitionScope.SharedContentState sharedContentState = (SharedTransitionScope.SharedContentState) objRememberedValue;
        ComposerKt.sourceInformationMarkerEnd(composer);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceGroup();
        return sharedContentState;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:16:0x0051  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x00a6  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x00ae  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x00ee  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void updateTransitionActiveness() {
        /*
            Method dump skipped, instruction units count: 255
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.animation.SharedTransitionScopeImpl.updateTransitionActiveness():void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final <T> Modifier sharedBoundsImpl(Modifier modifier, final SharedTransitionScope.SharedContentState sharedContentState, final Transition<T> transition, final Function1<? super T, Boolean> function1, final BoundsTransform boundsTransform, final SharedTransitionScope.PlaceHolderSize placeHolderSize, final boolean z, final boolean z2, final float f, final SharedTransitionScope.OverlayClip overlayClip) {
        return ComposedModifierKt.composed$default(modifier, null, new Function3<Modifier, Composer, Integer, Modifier>() { // from class: androidx.compose.animation.SharedTransitionScopeImpl.sharedBoundsImpl.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(3);
            }

            @Override // kotlin.jvm.functions.Function3
            public /* bridge */ /* synthetic */ Modifier invoke(Modifier modifier2, Composer composer, Integer num) {
                return invoke(modifier2, composer, num.intValue());
            }

            /* JADX WARN: Type inference incomplete: some casts might be missing */
            public final Modifier invoke(Modifier modifier2, Composer composer, int i) {
                Transition transitionRememberTransition;
                Composer composer2 = composer;
                composer.startReplaceGroup(-1843478929);
                ComposerKt.sourceInformation(composer, "C:SharedTransitionScope.kt#xbi5r1");
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-1843478929, i, -1, "androidx.compose.animation.SharedTransitionScopeImpl.sharedBoundsImpl.<anonymous> (SharedTransitionScope.kt:968)");
                }
                Object key = sharedContentState.getKey();
                composer.startMovableGroup(-359675295, key);
                ComposerKt.sourceInformation(composer, "970@51556L35,1006@53390L522");
                ComposerKt.sourceInformationMarkerStart(composer, -359676293, "CC(remember):SharedTransitionScope.kt#9igjgp");
                SharedTransitionScopeImpl sharedTransitionScopeImpl = this;
                Object objRememberedValue = composer.rememberedValue();
                if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue = sharedTransitionScopeImpl.sharedElementsFor(key);
                    composer.updateRememberedValue(objRememberedValue);
                }
                SharedElement sharedElement = (SharedElement) objRememberedValue;
                ComposerKt.sourceInformationMarkerEnd(composer);
                composer.startMovableGroup(-359672306, transition);
                ComposerKt.sourceInformation(composer, "*997@53029L278");
                boolean z3 = false;
                if (transition != 0) {
                    composer.startReplaceGroup(1735101820);
                    ComposerKt.sourceInformation(composer, "974@51759L53");
                    Transition<T> transition2 = transition;
                    String string = key.toString();
                    Function1<T, Boolean> function12 = function1;
                    ComposerKt.sourceInformationMarkerStart(composer, 1215497572, "CC(createChildTransition)1811@74006L36,1812@74066L74,1813@74163L39,1814@74214L63:Transition.kt#pdpnli");
                    ComposerKt.sourceInformationMarkerStart(composer, 272023029, "CC(remember):Transition.kt#9igjgp");
                    boolean zChanged = composer.changed(transition2);
                    Object objRememberedValue2 = composer.rememberedValue();
                    if (zChanged || objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                        objRememberedValue2 = transition2.getCurrentState();
                        composer.updateRememberedValue(objRememberedValue2);
                    }
                    ComposerKt.sourceInformationMarkerEnd(composer);
                    if (transition2.isSeeking()) {
                        objRememberedValue2 = transition2.getCurrentState();
                    }
                    composer.startReplaceGroup(1329676753);
                    ComposerKt.sourceInformation(composer, "C:SharedTransitionScope.kt#xbi5r1");
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(1329676753, 0, -1, "androidx.compose.animation.SharedTransitionScopeImpl.sharedBoundsImpl.<anonymous>.<anonymous>.<anonymous>.<anonymous> (SharedTransitionScope.kt:974)");
                    }
                    boolean zBooleanValue = function12.invoke((T) objRememberedValue2).booleanValue();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    composer.endReplaceGroup();
                    Boolean boolValueOf = Boolean.valueOf(zBooleanValue);
                    Object targetState = transition2.getTargetState();
                    composer.startReplaceGroup(1329676753);
                    ComposerKt.sourceInformation(composer, "C:SharedTransitionScope.kt#xbi5r1");
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(1329676753, 0, -1, "androidx.compose.animation.SharedTransitionScopeImpl.sharedBoundsImpl.<anonymous>.<anonymous>.<anonymous>.<anonymous> (SharedTransitionScope.kt:974)");
                    }
                    boolean zBooleanValue2 = function12.invoke((T) targetState).booleanValue();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    composer.endReplaceGroup();
                    transitionRememberTransition = androidx.compose.animation.core.TransitionKt.createChildTransitionInternal(transition2, boolValueOf, Boolean.valueOf(zBooleanValue2), string, composer2, 0);
                    composer2 = composer2;
                    ComposerKt.sourceInformationMarkerEnd(composer);
                    composer.endReplaceGroup();
                } else {
                    composer.startReplaceGroup(1735245009);
                    ComposerKt.sourceInformation(composer, "*979@52034L707,992@52800L35");
                    Function1<T, Boolean> function13 = function1;
                    Intrinsics.checkNotNull(function13, "null cannot be cast to non-null type kotlin.Function1<kotlin.Unit, kotlin.Boolean>");
                    boolean zBooleanValue3 = ((Boolean) ((Function1) TypeIntrinsics.beforeCheckcastToFunctionOfArity(function13, 1)).invoke(Unit.INSTANCE)).booleanValue();
                    ComposerKt.sourceInformationMarkerStart(composer, -359660325, "CC(remember):SharedTransitionScope.kt#9igjgp");
                    Object objRememberedValue3 = composer.rememberedValue();
                    if (objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                        if (sharedElement.getCurrentBounds() == null) {
                            z3 = zBooleanValue3;
                        } else if (!zBooleanValue3) {
                            z3 = true;
                        }
                        objRememberedValue3 = new MutableTransitionState(Boolean.valueOf(z3));
                        composer.updateRememberedValue(objRememberedValue3);
                    }
                    MutableTransitionState mutableTransitionState = (MutableTransitionState) objRememberedValue3;
                    ComposerKt.sourceInformationMarkerEnd(composer);
                    mutableTransitionState.setTargetState$animation_core_release(Boolean.valueOf(zBooleanValue3));
                    transitionRememberTransition = androidx.compose.animation.core.TransitionKt.rememberTransition(mutableTransitionState, null, composer, MutableTransitionState.$stable, 2);
                    composer.endReplaceGroup();
                }
                composer.startMovableGroup(-359633642, Boolean.valueOf(this.isTransitionActive()));
                ComposerKt.sourceInformation(composer, "995@52949L45");
                Transition<Boolean>.DeferredAnimation<Rect, AnimationVector4D> deferredAnimationCreateDeferredAnimation = androidx.compose.animation.core.TransitionKt.createDeferredAnimation(transitionRememberTransition, VectorConvertersKt.getVectorConverter(Rect.INSTANCE), null, composer2, 0, 2);
                composer.endMovableGroup();
                ComposerKt.sourceInformationMarkerStart(composer, -359628914, "CC(remember):SharedTransitionScope.kt#9igjgp");
                boolean zChanged2 = composer.changed(transitionRememberTransition);
                SharedTransitionScopeImpl sharedTransitionScopeImpl2 = this;
                BoundsTransform boundsTransform2 = boundsTransform;
                Object objRememberedValue4 = composer.rememberedValue();
                if (zChanged2 || objRememberedValue4 == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue4 = new BoundsAnimation(sharedTransitionScopeImpl2, transitionRememberTransition, deferredAnimationCreateDeferredAnimation, boundsTransform2);
                    composer.updateRememberedValue(objRememberedValue4);
                }
                BoundsAnimation boundsAnimation = (BoundsAnimation) objRememberedValue4;
                ComposerKt.sourceInformationMarkerEnd(composer);
                boundsAnimation.updateAnimation(deferredAnimationCreateDeferredAnimation, boundsTransform);
                composer.endMovableGroup();
                SharedElementInternalState sharedElementInternalStateRememberSharedElementState = this.rememberSharedElementState(sharedElement, boundsAnimation, placeHolderSize, z, sharedContentState, overlayClip, f, z2, composer, 0);
                composer.endMovableGroup();
                Modifier modifierThen = modifier2.then(new SharedBoundsNodeElement(sharedElementInternalStateRememberSharedElementState));
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                composer.endReplaceGroup();
                return modifierThen;
            }
        }, 1, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final SharedElementInternalState rememberSharedElementState(SharedElement sharedElement, BoundsAnimation boundsAnimation, SharedTransitionScope.PlaceHolderSize placeHolderSize, boolean z, SharedTransitionScope.SharedContentState sharedContentState, SharedTransitionScope.OverlayClip overlayClip, float f, boolean z2, Composer composer, int i) {
        ComposerKt.sourceInformationMarkerStart(composer, 2066772852, "C(rememberSharedElementState)P(6!1,2,4,5!1,7)*1032@54440L467:SharedTransitionScope.kt#xbi5r1");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(2066772852, i, -1, "androidx.compose.animation.SharedTransitionScopeImpl.rememberSharedElementState (SharedTransitionScope.kt:1032)");
        }
        ComposerKt.sourceInformationMarkerStart(composer, -663265575, "CC(remember):SharedTransitionScope.kt#9igjgp");
        Object objRememberedValue = composer.rememberedValue();
        if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
            objRememberedValue = new SharedElementInternalState(sharedElement, boundsAnimation, placeHolderSize, z, overlayClip, z2, sharedContentState, f);
            composer.updateRememberedValue(objRememberedValue);
        }
        SharedElementInternalState sharedElementInternalState = (SharedElementInternalState) objRememberedValue;
        ComposerKt.sourceInformationMarkerEnd(composer);
        sharedContentState.setInternalState$animation_release(sharedElementInternalState);
        sharedElementInternalState.setSharedElement(sharedElement);
        sharedElementInternalState.setRenderOnlyWhenVisible(z);
        sharedElementInternalState.setBoundsAnimation(boundsAnimation);
        sharedElementInternalState.setPlaceHolderSize(placeHolderSize);
        sharedElementInternalState.setOverlayClip(overlayClip);
        sharedElementInternalState.setZIndex(f);
        sharedElementInternalState.setRenderInOverlayDuringTransition(z2);
        sharedElementInternalState.setUserState(sharedContentState);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd(composer);
        return sharedElementInternalState;
    }

    public final LayoutCoordinates getRoot$animation_release() {
        LayoutCoordinates layoutCoordinates = this.root;
        if (layoutCoordinates != null) {
            return layoutCoordinates;
        }
        Intrinsics.throwUninitializedPropertyAccessException("root");
        return null;
    }

    public final void setRoot$animation_release(LayoutCoordinates layoutCoordinates) {
        this.root = layoutCoordinates;
    }

    public final LayoutCoordinates getLookaheadRoot$animation_release() {
        LayoutCoordinates layoutCoordinates = this.nullableLookaheadRoot;
        if (layoutCoordinates != null) {
            return layoutCoordinates;
        }
        throw new IllegalArgumentException("Error: Uninitialized LayoutCoordinates. Please make sure when using the SharedTransitionScope composable function, the modifier passed to the child content is being used, or use SharedTransitionLayout instead.".toString());
    }

    /* JADX INFO: renamed from: getNullableLookaheadRoot$animation_release, reason: from getter */
    public final LayoutCoordinates getNullableLookaheadRoot() {
        return this.nullableLookaheadRoot;
    }

    public final void setNullableLookaheadRoot$animation_release(LayoutCoordinates layoutCoordinates) {
        this.nullableLookaheadRoot = layoutCoordinates;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final SharedElement sharedElementsFor(Object key) {
        SharedElement sharedElement = this.sharedElements.get(key);
        if (sharedElement != null) {
            return sharedElement;
        }
        SharedElement sharedElement2 = new SharedElement(key, this);
        this.sharedElements.set(key, sharedElement2);
        return sharedElement2;
    }

    public final void drawInOverlay$animation_release(ContentDrawScope scope) {
        SnapshotStateList<LayerRenderer> snapshotStateList = this.renderers;
        if (snapshotStateList.size() > 1) {
            CollectionsKt.sortWith(snapshotStateList, new Comparator() { // from class: androidx.compose.animation.SharedTransitionScopeImpl$drawInOverlay$$inlined$sortBy$1
                @Override // java.util.Comparator
                public final int compare(T t, T t2) {
                    LayerRenderer layerRenderer = (LayerRenderer) t;
                    LayerRenderer layerRenderer2 = (LayerRenderer) t2;
                    return ComparisonsKt.compareValues(Float.valueOf((layerRenderer.getZIndex() == 0.0f && (layerRenderer instanceof SharedElementInternalState) && ((SharedElementInternalState) layerRenderer).getParentState() == null) ? -1.0f : layerRenderer.getZIndex()), Float.valueOf((layerRenderer2.getZIndex() == 0.0f && (layerRenderer2 instanceof SharedElementInternalState) && ((SharedElementInternalState) layerRenderer2).getParentState() == null) ? -1.0f : layerRenderer2.getZIndex()));
                }
            });
        }
        SnapshotStateList<LayerRenderer> snapshotStateList2 = this.renderers;
        int size = snapshotStateList2.size();
        for (int i = 0; i < size; i++) {
            snapshotStateList2.get(i).drawInOverlay(scope);
        }
    }

    public final void onStateRemoved$animation_release(SharedElementInternalState sharedElementState) {
        SharedElement sharedElement = sharedElementState.getSharedElement();
        sharedElement.removeState(sharedElementState);
        this.updateTransitionActiveness.invoke(this);
        SharedTransitionScopeKt.getSharedTransitionObserver().observeReads(sharedElement.getScope(), this.updateTransitionActiveness, this.observeAnimatingBlock);
        this.renderers.remove(sharedElementState);
        if (sharedElement.getStates().isEmpty()) {
            BuildersKt__Builders_commonKt.launch$default(sharedElement.getScope().coroutineScope, null, null, new SharedTransitionScopeImpl$onStateRemoved$1$1(sharedElement, null), 3, null);
        }
    }

    public final void onStateAdded$animation_release(SharedElementInternalState sharedElementState) {
        SharedElement sharedElement = sharedElementState.getSharedElement();
        sharedElement.addState(sharedElementState);
        this.updateTransitionActiveness.invoke(this);
        SharedTransitionScopeKt.getSharedTransitionObserver().observeReads(sharedElement.getScope(), this.updateTransitionActiveness, this.observeAnimatingBlock);
        Iterator<LayerRenderer> it = this.renderers.iterator();
        int i = 0;
        while (true) {
            if (!it.hasNext()) {
                i = -1;
                break;
            }
            LayerRenderer next = it.next();
            SharedElementInternalState sharedElementInternalState = next instanceof SharedElementInternalState ? (SharedElementInternalState) next : null;
            if (Intrinsics.areEqual(sharedElementInternalState != null ? sharedElementInternalState.getSharedElement() : null, sharedElementState.getSharedElement())) {
                break;
            } else {
                i++;
            }
        }
        if (i == this.renderers.size() - 1 || i == -1) {
            this.renderers.add(sharedElementState);
        } else {
            this.renderers.add(i + 1, sharedElementState);
        }
    }

    public final void onLayerRendererCreated$animation_release(LayerRenderer renderer) {
        this.renderers.add(renderer);
    }

    public final void onLayerRendererRemoved$animation_release(LayerRenderer renderer) {
        this.renderers.remove(renderer);
    }

    /* JADX INFO: compiled from: SharedTransitionScope.kt */
    @Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J(\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011H\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Landroidx/compose/animation/SharedTransitionScopeImpl$ShapeBasedClip;", "Landroidx/compose/animation/SharedTransitionScope$OverlayClip;", "clipShape", "Landroidx/compose/ui/graphics/Shape;", "(Landroidx/compose/ui/graphics/Shape;)V", "getClipShape", "()Landroidx/compose/ui/graphics/Shape;", "path", "Landroidx/compose/ui/graphics/Path;", "getClipPath", "state", "Landroidx/compose/animation/SharedTransitionScope$SharedContentState;", "bounds", "Landroidx/compose/ui/geometry/Rect;", "layoutDirection", "Landroidx/compose/ui/unit/LayoutDirection;", "density", "Landroidx/compose/ui/unit/Density;", "animation_release"}, k = 1, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    private static final class ShapeBasedClip implements SharedTransitionScope.OverlayClip {
        private final Shape clipShape;
        private final Path path = AndroidPath_androidKt.Path();

        public ShapeBasedClip(Shape shape) {
            this.clipShape = shape;
        }

        public final Shape getClipShape() {
            return this.clipShape;
        }

        @Override // androidx.compose.animation.SharedTransitionScope.OverlayClip
        public Path getClipPath(SharedTransitionScope.SharedContentState state, Rect bounds, LayoutDirection layoutDirection, Density density) {
            this.path.reset();
            OutlineKt.addOutline(this.path, this.clipShape.mo568createOutlinePq9zytI(bounds.m4360getSizeNHjbRc(), layoutDirection, density));
            this.path.mo4463translatek4lQ0M(bounds.m4362getTopLeftF1C5BW0());
            return this.path;
        }
    }
}
