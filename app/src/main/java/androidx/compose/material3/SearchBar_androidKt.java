package androidx.compose.material3;

import androidx.activity.BackEventCompat;
import androidx.compose.animation.EnterExitTransitionKt;
import androidx.compose.animation.EnterTransition;
import androidx.compose.animation.ExitTransition;
import androidx.compose.animation.core.Animatable;
import androidx.compose.animation.core.AnimationSpecKt;
import androidx.compose.animation.core.AnimationVector1D;
import androidx.compose.animation.core.CubicBezierEasing;
import androidx.compose.animation.core.FiniteAnimationSpec;
import androidx.compose.animation.core.TweenSpec;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.foundation.layout.WindowInsets;
import androidx.compose.foundation.layout.WindowInsetsKt;
import androidx.compose.foundation.layout.WindowInsetsPaddingKt;
import androidx.compose.foundation.text.selection.TextSelectionColors;
import androidx.compose.material3.internal.MutableWindowInsets;
import androidx.compose.material3.tokens.MotionTokens;
import androidx.compose.runtime.Applier;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.MutableFloatState;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.State;
import androidx.compose.runtime.Updater;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.ZIndexModifierKt;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.layout.LayoutIdKt;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.ConstraintsKt;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.IntSize;
import androidx.compose.ui.unit.LayoutDirection;
import androidx.compose.ui.util.MathHelpersKt;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.accessibility.AccessibilityEventCompat;
import com.google.android.gms.actions.SearchIntents;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.List;
import java.util.NoSuchElementException;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt;
import kotlin.ranges.RangesKt;

/* JADX INFO: compiled from: SearchBar.android.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000¾\u0001\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\u001a\u0091\u0001\u0010,\u001a\u00020-2\u0011\u0010.\u001a\r\u0012\u0004\u0012\u00020-0/¢\u0006\u0002\b02\u0006\u00101\u001a\u0002022\u0012\u00103\u001a\u000e\u0012\u0004\u0012\u000202\u0012\u0004\u0012\u00020-042\b\b\u0002\u00105\u001a\u0002062\b\b\u0002\u00107\u001a\u0002082\b\b\u0002\u00109\u001a\u00020:2\b\b\u0002\u0010;\u001a\u00020\u00152\b\b\u0002\u0010<\u001a\u00020\u00152\u001c\u0010=\u001a\u0018\u0012\u0004\u0012\u00020>\u0012\u0004\u0012\u00020-04¢\u0006\u0002\b0¢\u0006\u0002\b?H\u0007ø\u0001\u0000¢\u0006\u0004\b@\u0010A\u001a\u0089\u0002\u0010,\u001a\u00020-2\u0006\u0010B\u001a\u00020\u001a2\u0012\u0010C\u001a\u000e\u0012\u0004\u0012\u00020\u001a\u0012\u0004\u0012\u00020-042\u0012\u0010D\u001a\u000e\u0012\u0004\u0012\u00020\u001a\u0012\u0004\u0012\u00020-042\u0006\u0010E\u001a\u0002022\u0012\u0010F\u001a\u000e\u0012\u0004\u0012\u000202\u0012\u0004\u0012\u00020-042\b\b\u0002\u00105\u001a\u0002062\b\b\u0002\u0010G\u001a\u0002022\u0015\b\u0002\u0010H\u001a\u000f\u0012\u0004\u0012\u00020-\u0018\u00010/¢\u0006\u0002\b02\u0015\b\u0002\u0010I\u001a\u000f\u0012\u0004\u0012\u00020-\u0018\u00010/¢\u0006\u0002\b02\u0015\b\u0002\u0010J\u001a\u000f\u0012\u0004\u0012\u00020-\u0018\u00010/¢\u0006\u0002\b02\b\b\u0002\u00107\u001a\u0002082\b\b\u0002\u00109\u001a\u00020:2\b\b\u0002\u0010;\u001a\u00020\u00152\b\b\u0002\u0010<\u001a\u00020\u00152\n\b\u0002\u0010K\u001a\u0004\u0018\u00010L2\u001c\u0010=\u001a\u0018\u0012\u0004\u0012\u00020>\u0012\u0004\u0012\u00020-04¢\u0006\u0002\b0¢\u0006\u0002\b?H\u0007ø\u0001\u0000¢\u0006\u0004\bM\u0010N\u001a\u009b\u0001\u0010O\u001a\u00020-2\u0011\u0010.\u001a\r\u0012\u0004\u0012\u00020-0/¢\u0006\u0002\b02\u0006\u00101\u001a\u0002022\u0012\u00103\u001a\u000e\u0012\u0004\u0012\u000202\u0012\u0004\u0012\u00020-042\b\b\u0002\u00105\u001a\u0002062\b\b\u0002\u00107\u001a\u0002082\b\b\u0002\u00109\u001a\u00020:2\b\b\u0002\u0010;\u001a\u00020\u00152\b\b\u0002\u0010<\u001a\u00020\u00152\b\b\u0002\u0010P\u001a\u00020Q2\u001c\u0010=\u001a\u0018\u0012\u0004\u0012\u00020>\u0012\u0004\u0012\u00020-04¢\u0006\u0002\b0¢\u0006\u0002\b?H\u0007ø\u0001\u0000¢\u0006\u0004\bR\u0010S\u001a\u0093\u0002\u0010O\u001a\u00020-2\u0006\u0010B\u001a\u00020\u001a2\u0012\u0010C\u001a\u000e\u0012\u0004\u0012\u00020\u001a\u0012\u0004\u0012\u00020-042\u0012\u0010D\u001a\u000e\u0012\u0004\u0012\u00020\u001a\u0012\u0004\u0012\u00020-042\u0006\u0010E\u001a\u0002022\u0012\u0010F\u001a\u000e\u0012\u0004\u0012\u000202\u0012\u0004\u0012\u00020-042\b\b\u0002\u00105\u001a\u0002062\b\b\u0002\u0010G\u001a\u0002022\u0015\b\u0002\u0010H\u001a\u000f\u0012\u0004\u0012\u00020-\u0018\u00010/¢\u0006\u0002\b02\u0015\b\u0002\u0010I\u001a\u000f\u0012\u0004\u0012\u00020-\u0018\u00010/¢\u0006\u0002\b02\u0015\b\u0002\u0010J\u001a\u000f\u0012\u0004\u0012\u00020-\u0018\u00010/¢\u0006\u0002\b02\b\b\u0002\u00107\u001a\u0002082\b\b\u0002\u00109\u001a\u00020:2\b\b\u0002\u0010;\u001a\u00020\u00152\b\b\u0002\u0010<\u001a\u00020\u00152\b\b\u0002\u0010P\u001a\u00020Q2\n\b\u0002\u0010K\u001a\u0004\u0018\u00010L2\u001c\u0010=\u001a\u0018\u0012\u0004\u0012\u00020>\u0012\u0004\u0012\u00020-04¢\u0006\u0002\b0¢\u0006\u0002\b?H\u0007ø\u0001\u0000¢\u0006\u0004\bT\u0010U\u001a»\u0001\u0010V\u001a\u00020-2\u0012\u0010W\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020Y0X2\u0006\u0010Z\u001a\u00020[2\u000e\u0010\\\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010^0]2\u000e\u0010_\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010^0]2\b\b\u0002\u00105\u001a\u0002062\u0011\u0010.\u001a\r\u0012\u0004\u0012\u00020-0/¢\u0006\u0002\b02\b\b\u0002\u00107\u001a\u0002082\b\b\u0002\u00109\u001a\u00020:2\b\b\u0002\u0010;\u001a\u00020\u00152\b\b\u0002\u0010<\u001a\u00020\u00152\b\b\u0002\u0010P\u001a\u00020Q2\u001c\u0010=\u001a\u0018\u0012\u0004\u0012\u00020>\u0012\u0004\u0012\u00020-04¢\u0006\u0002\b0¢\u0006\u0002\b?H\u0001ø\u0001\u0000¢\u0006\u0004\b`\u0010a\u001a\u0094\u0001\u0010b\u001a\u00020-2\u0012\u0010W\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020Y0X2\u0006\u0010Z\u001a\u00020[2\u000e\u0010\\\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010^0]2\u000e\u0010_\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010^0]2\u0006\u00105\u001a\u0002062\u0006\u0010P\u001a\u00020Q2\u0011\u0010.\u001a\r\u0012\u0004\u0012\u00020-0/¢\u0006\u0002\b02\u0011\u0010c\u001a\r\u0012\u0004\u0012\u00020-0/¢\u0006\u0002\b02\u0013\u0010=\u001a\u000f\u0012\u0004\u0012\u00020-\u0018\u00010/¢\u0006\u0002\b0H\u0003¢\u0006\u0002\u0010d\u001a\"\u0010e\u001a\u00020\u00072\b\u0010_\u001a\u0004\u0018\u00010^2\u0006\u0010f\u001a\u00020\u00072\u0006\u0010Z\u001a\u00020\u0007H\u0002\u001aD\u0010g\u001a\u00020\u00012\u0006\u0010h\u001a\u00020i2\u0006\u0010j\u001a\u00020\u00012\b\u0010_\u001a\u0004\u0018\u00010^2\u0006\u0010k\u001a\u00020l2\u0006\u0010f\u001a\u00020\u00072\u0006\u0010m\u001a\u00020\u0007H\u0002ø\u0001\u0000¢\u0006\u0004\bn\u0010o\u001aN\u0010p\u001a\u00020\u00012\u0006\u0010h\u001a\u00020i2\u0006\u0010j\u001a\u00020\u00012\b\u0010_\u001a\u0004\u0018\u00010^2\b\u0010\\\u001a\u0004\u0018\u00010^2\u0006\u0010q\u001a\u00020\u00012\u0006\u0010r\u001a\u00020\u00012\u0006\u0010m\u001a\u00020\u0007H\u0002ø\u0001\u0000¢\u0006\u0004\bs\u0010t\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000\"\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004¢\u0006\u0002\n\u0000\"\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020\t0\u0006X\u0082\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010\n\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u000b\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000\"\u0014\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004¢\u0006\u0002\n\u0000\"\u0014\u0010\r\u001a\b\u0012\u0004\u0012\u00020\t0\u0006X\u0082\u0004¢\u0006\u0002\n\u0000\"\u0014\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010\u0013\u001a\u00020\u0007X\u0082T¢\u0006\u0002\n\u0000\"\u0016\u0010\u0014\u001a\u00020\u0015X\u0080\u0004¢\u0006\n\n\u0002\u0010\u0018\u001a\u0004\b\u0016\u0010\u0017\"\u000e\u0010\u0019\u001a\u00020\u001aX\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u001b\u001a\u00020\u001aX\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u001c\u001a\u00020\u001aX\u0082T¢\u0006\u0002\n\u0000\"\u0016\u0010\u001d\u001a\u00020\u0015X\u0082\u0004¢\u0006\n\n\u0002\u0010\u0018\u0012\u0004\b\u001e\u0010\u001f\"\u0010\u0010 \u001a\u00020\u0015X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0018\"\u0010\u0010!\u001a\u00020\u0015X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0018\"\u0016\u0010\"\u001a\u00020\u0015X\u0080\u0004¢\u0006\n\n\u0002\u0010\u0018\u001a\u0004\b#\u0010\u0017\"\u000e\u0010$\u001a\u00020\u0007X\u0082T¢\u0006\u0002\n\u0000\"\u0010\u0010%\u001a\u00020\u0015X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0018\"\u0010\u0010&\u001a\u00020\u0015X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0018\"\u000e\u0010'\u001a\u00020\u0007X\u0082T¢\u0006\u0002\n\u0000\"\u0016\u0010(\u001a\u00020\u0015X\u0080\u0004¢\u0006\n\n\u0002\u0010\u0018\u001a\u0004\b)\u0010\u0017\"\u000e\u0010*\u001a\u00020+X\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006u²\u0006\n\u0010v\u001a\u000202X\u008a\u0084\u0002²\u0006\n\u0010w\u001a\u000202X\u008a\u0084\u0002"}, d2 = {"AnimationDelayMillis", "", "AnimationEnterDurationMillis", "AnimationEnterEasing", "Landroidx/compose/animation/core/CubicBezierEasing;", "AnimationEnterFloatSpec", "Landroidx/compose/animation/core/FiniteAnimationSpec;", "", "AnimationEnterSizeSpec", "Landroidx/compose/ui/unit/IntSize;", "AnimationExitDurationMillis", "AnimationExitEasing", "AnimationExitFloatSpec", "AnimationExitSizeSpec", "AnimationPredictiveBackExitFloatSpec", "DockedEnterTransition", "Landroidx/compose/animation/EnterTransition;", "DockedExitTransition", "Landroidx/compose/animation/ExitTransition;", "DockedExpandedTableMaxHeightScreenRatio", "DockedExpandedTableMinHeight", "Landroidx/compose/ui/unit/Dp;", "getDockedExpandedTableMinHeight", "()F", "F", "LayoutIdInputField", "", "LayoutIdSearchContent", "LayoutIdSurface", "SearchBarCornerRadius", "getSearchBarCornerRadius$annotations", "()V", "SearchBarIconOffsetX", "SearchBarMaxWidth", "SearchBarMinWidth", "getSearchBarMinWidth", "SearchBarPredictiveBackMaxOffsetXRatio", "SearchBarPredictiveBackMaxOffsetY", "SearchBarPredictiveBackMinMargin", "SearchBarPredictiveBackMinScale", "SearchBarVerticalPadding", "getSearchBarVerticalPadding", "UnspecifiedTextFieldColors", "Landroidx/compose/material3/TextFieldColors;", "DockedSearchBar", "", "inputField", "Lkotlin/Function0;", "Landroidx/compose/runtime/Composable;", "expanded", "", "onExpandedChange", "Lkotlin/Function1;", "modifier", "Landroidx/compose/ui/Modifier;", "shape", "Landroidx/compose/ui/graphics/Shape;", "colors", "Landroidx/compose/material3/SearchBarColors;", "tonalElevation", "shadowElevation", FirebaseAnalytics.Param.CONTENT, "Landroidx/compose/foundation/layout/ColumnScope;", "Lkotlin/ExtensionFunctionType;", "DockedSearchBar-EQC0FA8", "(Lkotlin/jvm/functions/Function2;ZLkotlin/jvm/functions/Function1;Landroidx/compose/ui/Modifier;Landroidx/compose/ui/graphics/Shape;Landroidx/compose/material3/SearchBarColors;FFLkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;II)V", SearchIntents.EXTRA_QUERY, "onQueryChange", "onSearch", AppMeasurementSdk.ConditionalUserProperty.ACTIVE, "onActiveChange", "enabled", "placeholder", "leadingIcon", "trailingIcon", "interactionSource", "Landroidx/compose/foundation/interaction/MutableInteractionSource;", "DockedSearchBar-eWTbjVg", "(Ljava/lang/String;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;ZLkotlin/jvm/functions/Function1;Landroidx/compose/ui/Modifier;ZLkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Landroidx/compose/ui/graphics/Shape;Landroidx/compose/material3/SearchBarColors;FFLandroidx/compose/foundation/interaction/MutableInteractionSource;Lkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;III)V", "SearchBar", "windowInsets", "Landroidx/compose/foundation/layout/WindowInsets;", "SearchBar-Y92LkZI", "(Lkotlin/jvm/functions/Function2;ZLkotlin/jvm/functions/Function1;Landroidx/compose/ui/Modifier;Landroidx/compose/ui/graphics/Shape;Landroidx/compose/material3/SearchBarColors;FFLandroidx/compose/foundation/layout/WindowInsets;Lkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;II)V", "SearchBar-WuY5d9Q", "(Ljava/lang/String;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;ZLkotlin/jvm/functions/Function1;Landroidx/compose/ui/Modifier;ZLkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Landroidx/compose/ui/graphics/Shape;Landroidx/compose/material3/SearchBarColors;FFLandroidx/compose/foundation/layout/WindowInsets;Landroidx/compose/foundation/interaction/MutableInteractionSource;Lkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;III)V", "SearchBarImpl", "animationProgress", "Landroidx/compose/animation/core/Animatable;", "Landroidx/compose/animation/core/AnimationVector1D;", "finalBackProgress", "Landroidx/compose/runtime/MutableFloatState;", "firstBackEvent", "Landroidx/compose/runtime/MutableState;", "Landroidx/activity/BackEventCompat;", "currentBackEvent", "SearchBarImpl-j1jLAyQ", "(Landroidx/compose/animation/core/Animatable;Landroidx/compose/runtime/MutableFloatState;Landroidx/compose/runtime/MutableState;Landroidx/compose/runtime/MutableState;Landroidx/compose/ui/Modifier;Lkotlin/jvm/functions/Function2;Landroidx/compose/ui/graphics/Shape;Landroidx/compose/material3/SearchBarColors;FFLandroidx/compose/foundation/layout/WindowInsets;Lkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;III)V", "SearchBarLayout", "surface", "(Landroidx/compose/animation/core/Animatable;Landroidx/compose/runtime/MutableFloatState;Landroidx/compose/runtime/MutableState;Landroidx/compose/runtime/MutableState;Landroidx/compose/ui/Modifier;Landroidx/compose/foundation/layout/WindowInsets;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;I)V", "calculatePredictiveBackMultiplier", "progress", "calculatePredictiveBackOffsetX", "constraints", "Landroidx/compose/ui/unit/Constraints;", "minMargin", "layoutDirection", "Landroidx/compose/ui/unit/LayoutDirection;", "predictiveBackMultiplier", "calculatePredictiveBackOffsetX-rOvwMX4", "(JILandroidx/activity/BackEventCompat;Landroidx/compose/ui/unit/LayoutDirection;FF)I", "calculatePredictiveBackOffsetY", "height", "maxOffsetY", "calculatePredictiveBackOffsetY-dzo92Q0", "(JILandroidx/activity/BackEventCompat;Landroidx/activity/BackEventCompat;IIF)I", "material3_release", "useFullScreenShape", "showContent"}, k = 2, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
public final class SearchBar_androidKt {
    private static final int AnimationDelayMillis = 100;
    private static final int AnimationEnterDurationMillis = 600;
    private static final CubicBezierEasing AnimationEnterEasing;
    private static final FiniteAnimationSpec<Float> AnimationEnterFloatSpec;
    private static final FiniteAnimationSpec<IntSize> AnimationEnterSizeSpec;
    private static final int AnimationExitDurationMillis = 350;
    private static final CubicBezierEasing AnimationExitEasing;
    private static final FiniteAnimationSpec<Float> AnimationExitFloatSpec;
    private static final FiniteAnimationSpec<IntSize> AnimationExitSizeSpec;
    private static final FiniteAnimationSpec<Float> AnimationPredictiveBackExitFloatSpec;
    private static final EnterTransition DockedEnterTransition;
    private static final ExitTransition DockedExitTransition;
    private static final float DockedExpandedTableMaxHeightScreenRatio = 0.6666667f;
    private static final String LayoutIdInputField = "InputField";
    private static final String LayoutIdSearchContent = "Content";
    private static final String LayoutIdSurface = "Surface";
    private static final float SearchBarPredictiveBackMaxOffsetXRatio = 0.05f;
    private static final float SearchBarPredictiveBackMinMargin;
    private static final float SearchBarPredictiveBackMinScale = 0.9f;
    private static final float SearchBarVerticalPadding;
    private static final TextFieldColors UnspecifiedTextFieldColors = new TextFieldColors(Color.INSTANCE.m4604getUnspecified0d7_KjU(), Color.INSTANCE.m4604getUnspecified0d7_KjU(), Color.INSTANCE.m4604getUnspecified0d7_KjU(), Color.INSTANCE.m4604getUnspecified0d7_KjU(), Color.INSTANCE.m4604getUnspecified0d7_KjU(), Color.INSTANCE.m4604getUnspecified0d7_KjU(), Color.INSTANCE.m4604getUnspecified0d7_KjU(), Color.INSTANCE.m4604getUnspecified0d7_KjU(), Color.INSTANCE.m4604getUnspecified0d7_KjU(), Color.INSTANCE.m4604getUnspecified0d7_KjU(), new TextSelectionColors(Color.INSTANCE.m4604getUnspecified0d7_KjU(), Color.INSTANCE.m4604getUnspecified0d7_KjU(), null), Color.INSTANCE.m4604getUnspecified0d7_KjU(), Color.INSTANCE.m4604getUnspecified0d7_KjU(), Color.INSTANCE.m4604getUnspecified0d7_KjU(), Color.INSTANCE.m4604getUnspecified0d7_KjU(), Color.INSTANCE.m4604getUnspecified0d7_KjU(), Color.INSTANCE.m4604getUnspecified0d7_KjU(), Color.INSTANCE.m4604getUnspecified0d7_KjU(), Color.INSTANCE.m4604getUnspecified0d7_KjU(), Color.INSTANCE.m4604getUnspecified0d7_KjU(), Color.INSTANCE.m4604getUnspecified0d7_KjU(), Color.INSTANCE.m4604getUnspecified0d7_KjU(), Color.INSTANCE.m4604getUnspecified0d7_KjU(), Color.INSTANCE.m4604getUnspecified0d7_KjU(), Color.INSTANCE.m4604getUnspecified0d7_KjU(), Color.INSTANCE.m4604getUnspecified0d7_KjU(), Color.INSTANCE.m4604getUnspecified0d7_KjU(), Color.INSTANCE.m4604getUnspecified0d7_KjU(), Color.INSTANCE.m4604getUnspecified0d7_KjU(), Color.INSTANCE.m4604getUnspecified0d7_KjU(), Color.INSTANCE.m4604getUnspecified0d7_KjU(), Color.INSTANCE.m4604getUnspecified0d7_KjU(), Color.INSTANCE.m4604getUnspecified0d7_KjU(), Color.INSTANCE.m4604getUnspecified0d7_KjU(), Color.INSTANCE.m4604getUnspecified0d7_KjU(), Color.INSTANCE.m4604getUnspecified0d7_KjU(), Color.INSTANCE.m4604getUnspecified0d7_KjU(), Color.INSTANCE.m4604getUnspecified0d7_KjU(), Color.INSTANCE.m4604getUnspecified0d7_KjU(), Color.INSTANCE.m4604getUnspecified0d7_KjU(), Color.INSTANCE.m4604getUnspecified0d7_KjU(), Color.INSTANCE.m4604getUnspecified0d7_KjU(), Color.INSTANCE.m4604getUnspecified0d7_KjU(), null);
    private static final float SearchBarCornerRadius = Dp.m7027constructorimpl(SearchBarDefaults.INSTANCE.m2721getInputFieldHeightD9Ej5fM() / 2);
    private static final float DockedExpandedTableMinHeight = Dp.m7027constructorimpl(240);
    private static final float SearchBarMinWidth = Dp.m7027constructorimpl(360);
    private static final float SearchBarMaxWidth = Dp.m7027constructorimpl(720);
    private static final float SearchBarIconOffsetX = Dp.m7027constructorimpl(4);
    private static final float SearchBarPredictiveBackMaxOffsetY = Dp.m7027constructorimpl(24);

    private static /* synthetic */ void getSearchBarCornerRadius$annotations() {
    }

    /* JADX WARN: Removed duplicated region for block: B:101:0x0114  */
    /* JADX WARN: Removed duplicated region for block: B:102:0x0117  */
    /* JADX WARN: Removed duplicated region for block: B:112:0x0139  */
    /* JADX WARN: Removed duplicated region for block: B:116:0x014e  */
    /* JADX WARN: Removed duplicated region for block: B:131:0x0189  */
    /* JADX WARN: Removed duplicated region for block: B:132:0x018b  */
    /* JADX WARN: Removed duplicated region for block: B:133:0x0190  */
    /* JADX WARN: Removed duplicated region for block: B:136:0x0196  */
    /* JADX WARN: Removed duplicated region for block: B:137:0x01a3  */
    /* JADX WARN: Removed duplicated region for block: B:140:0x01ab  */
    /* JADX WARN: Removed duplicated region for block: B:141:0x01c3  */
    /* JADX WARN: Removed duplicated region for block: B:143:0x01cd  */
    /* JADX WARN: Removed duplicated region for block: B:144:0x01d4  */
    /* JADX WARN: Removed duplicated region for block: B:146:0x01d8  */
    /* JADX WARN: Removed duplicated region for block: B:147:0x01df  */
    /* JADX WARN: Removed duplicated region for block: B:150:0x01e4  */
    /* JADX WARN: Removed duplicated region for block: B:151:0x01f0  */
    /* JADX WARN: Removed duplicated region for block: B:155:0x0202  */
    /* JADX WARN: Removed duplicated region for block: B:158:0x0220  */
    /* JADX WARN: Removed duplicated region for block: B:165:0x0246  */
    /* JADX WARN: Removed duplicated region for block: B:168:0x0268  */
    /* JADX WARN: Removed duplicated region for block: B:171:0x0287  */
    /* JADX WARN: Removed duplicated region for block: B:174:0x02af  */
    /* JADX WARN: Removed duplicated region for block: B:175:0x02b2  */
    /* JADX WARN: Removed duplicated region for block: B:182:0x02ce  */
    /* JADX WARN: Removed duplicated region for block: B:185:0x0301  */
    /* JADX WARN: Removed duplicated region for block: B:189:0x0321  */
    /* JADX WARN: Removed duplicated region for block: B:196:0x0337  */
    /* JADX WARN: Removed duplicated region for block: B:199:0x03a6  */
    /* JADX WARN: Removed duplicated region for block: B:203:0x03bd  */
    /* JADX WARN: Removed duplicated region for block: B:205:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0066  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0069  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x0082  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x0097  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x009e  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x00b3  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x00bb  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x00bd  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x00d8  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x00dd  */
    /* JADX WARN: Removed duplicated region for block: B:90:0x00f6  */
    /* JADX WARN: Removed duplicated region for block: B:98:0x010c  */
    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$PrimitiveArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    /* JADX INFO: renamed from: SearchBar-Y92LkZI, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final void m2729SearchBarY92LkZI(final kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r29, final boolean r30, final kotlin.jvm.functions.Function1<? super java.lang.Boolean, kotlin.Unit> r31, androidx.compose.ui.Modifier r32, androidx.compose.ui.graphics.Shape r33, androidx.compose.material3.SearchBarColors r34, float r35, float r36, androidx.compose.foundation.layout.WindowInsets r37, final kotlin.jvm.functions.Function3<? super androidx.compose.foundation.layout.ColumnScope, ? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r38, androidx.compose.runtime.Composer r39, final int r40, final int r41) {
        /*
            Method dump skipped, instruction units count: 976
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.SearchBar_androidKt.m2729SearchBarY92LkZI(kotlin.jvm.functions.Function2, boolean, kotlin.jvm.functions.Function1, androidx.compose.ui.Modifier, androidx.compose.ui.graphics.Shape, androidx.compose.material3.SearchBarColors, float, float, androidx.compose.foundation.layout.WindowInsets, kotlin.jvm.functions.Function3, androidx.compose.runtime.Composer, int, int):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:103:0x0123  */
    /* JADX WARN: Removed duplicated region for block: B:116:0x0153  */
    /* JADX WARN: Removed duplicated region for block: B:117:0x0155  */
    /* JADX WARN: Removed duplicated region for block: B:120:0x015e  */
    /* JADX WARN: Removed duplicated region for block: B:121:0x0168  */
    /* JADX WARN: Removed duplicated region for block: B:124:0x016f  */
    /* JADX WARN: Removed duplicated region for block: B:125:0x018b  */
    /* JADX WARN: Removed duplicated region for block: B:127:0x0192  */
    /* JADX WARN: Removed duplicated region for block: B:128:0x0199  */
    /* JADX WARN: Removed duplicated region for block: B:130:0x019d  */
    /* JADX WARN: Removed duplicated region for block: B:131:0x01a9  */
    /* JADX WARN: Removed duplicated region for block: B:135:0x01b9  */
    /* JADX WARN: Removed duplicated region for block: B:139:0x0221  */
    /* JADX WARN: Removed duplicated region for block: B:144:0x0230  */
    /* JADX WARN: Removed duplicated region for block: B:147:0x024d  */
    /* JADX WARN: Removed duplicated region for block: B:151:0x025d  */
    /* JADX WARN: Removed duplicated region for block: B:153:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:46:0x007f  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x0094  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x009b  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x00b0  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x00b8  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x00ba  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x00d4  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x00d9  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x00f2  */
    /* JADX WARN: Removed duplicated region for block: B:90:0x00f5  */
    /* JADX WARN: Removed duplicated region for block: B:99:0x0110  */
    /* JADX INFO: renamed from: DockedSearchBar-EQC0FA8, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final void m2726DockedSearchBarEQC0FA8(final kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r26, final boolean r27, final kotlin.jvm.functions.Function1<? super java.lang.Boolean, kotlin.Unit> r28, androidx.compose.ui.Modifier r29, androidx.compose.ui.graphics.Shape r30, androidx.compose.material3.SearchBarColors r31, float r32, float r33, final kotlin.jvm.functions.Function3<? super androidx.compose.foundation.layout.ColumnScope, ? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r34, androidx.compose.runtime.Composer r35, final int r36, final int r37) {
        /*
            Method dump skipped, instruction units count: 616
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.SearchBar_androidKt.m2726DockedSearchBarEQC0FA8(kotlin.jvm.functions.Function2, boolean, kotlin.jvm.functions.Function1, androidx.compose.ui.Modifier, androidx.compose.ui.graphics.Shape, androidx.compose.material3.SearchBarColors, float, float, kotlin.jvm.functions.Function3, androidx.compose.runtime.Composer, int, int):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:108:0x014a  */
    /* JADX WARN: Removed duplicated region for block: B:116:0x0160  */
    /* JADX WARN: Removed duplicated region for block: B:119:0x0168  */
    /* JADX WARN: Removed duplicated region for block: B:127:0x017e  */
    /* JADX WARN: Removed duplicated region for block: B:130:0x0186  */
    /* JADX WARN: Removed duplicated region for block: B:131:0x0189  */
    /* JADX WARN: Removed duplicated region for block: B:142:0x01a9  */
    /* JADX WARN: Removed duplicated region for block: B:143:0x01ae  */
    /* JADX WARN: Removed duplicated region for block: B:153:0x01ca  */
    /* JADX WARN: Removed duplicated region for block: B:160:0x01de  */
    /* JADX WARN: Removed duplicated region for block: B:163:0x01e7  */
    /* JADX WARN: Removed duplicated region for block: B:164:0x01ea  */
    /* JADX WARN: Removed duplicated region for block: B:174:0x0206  */
    /* JADX WARN: Removed duplicated region for block: B:175:0x020b  */
    /* JADX WARN: Removed duplicated region for block: B:184:0x0228  */
    /* JADX WARN: Removed duplicated region for block: B:190:0x0250  */
    /* JADX WARN: Removed duplicated region for block: B:205:0x0293  */
    /* JADX WARN: Removed duplicated region for block: B:206:0x0295  */
    /* JADX WARN: Removed duplicated region for block: B:207:0x029a  */
    /* JADX WARN: Removed duplicated region for block: B:209:0x029d  */
    /* JADX WARN: Removed duplicated region for block: B:210:0x02a0  */
    /* JADX WARN: Removed duplicated region for block: B:213:0x02a6  */
    /* JADX WARN: Removed duplicated region for block: B:214:0x02a9  */
    /* JADX WARN: Removed duplicated region for block: B:216:0x02ad  */
    /* JADX WARN: Removed duplicated region for block: B:218:0x02b1  */
    /* JADX WARN: Removed duplicated region for block: B:219:0x02b4  */
    /* JADX WARN: Removed duplicated region for block: B:222:0x02ba  */
    /* JADX WARN: Removed duplicated region for block: B:223:0x02c5  */
    /* JADX WARN: Removed duplicated region for block: B:226:0x02cb  */
    /* JADX WARN: Removed duplicated region for block: B:227:0x02e3  */
    /* JADX WARN: Removed duplicated region for block: B:229:0x02e9  */
    /* JADX WARN: Removed duplicated region for block: B:230:0x02f0  */
    /* JADX WARN: Removed duplicated region for block: B:232:0x02f4  */
    /* JADX WARN: Removed duplicated region for block: B:233:0x02fb  */
    /* JADX WARN: Removed duplicated region for block: B:236:0x0301  */
    /* JADX WARN: Removed duplicated region for block: B:237:0x030f  */
    /* JADX WARN: Removed duplicated region for block: B:239:0x0313  */
    /* JADX WARN: Removed duplicated region for block: B:240:0x031d  */
    /* JADX WARN: Removed duplicated region for block: B:244:0x033a  */
    /* JADX WARN: Removed duplicated region for block: B:247:0x039d  */
    /* JADX WARN: Removed duplicated region for block: B:251:0x03b8  */
    /* JADX WARN: Removed duplicated region for block: B:253:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:46:0x008e  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x0091  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x00b1  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x00b6  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x00cf  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x00d4  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x00ed  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x00f2  */
    /* JADX WARN: Removed duplicated region for block: B:87:0x010b  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x0110  */
    /* JADX WARN: Removed duplicated region for block: B:97:0x0129  */
    /* JADX WARN: Removed duplicated region for block: B:98:0x012c  */
    @kotlin.Deprecated(message = "Use overload which takes inputField as a parameter", replaceWith = @kotlin.ReplaceWith(expression = "SearchBar(\n    inputField = {\n        SearchBarDefaults.InputField(\n            query = query,\n            onQueryChange = onQueryChange,\n            onSearch = onSearch,\n            expanded = active,\n            onExpandedChange = onActiveChange,\n            enabled = enabled,\n            placeholder = placeholder,\n            leadingIcon = leadingIcon,\n            trailingIcon = trailingIcon,\n            colors = colors.inputFieldColors,\n            interactionSource = interactionSource,\n        )\n    },\n    expanded = active,\n    onExpandedChange = onActiveChange,\n    modifier = modifier,\n    shape = shape,\n    colors = colors,\n    tonalElevation = tonalElevation,\n    shadowElevation = shadowElevation,\n    windowInsets = windowInsets,\n    content = content,\n)", imports = {}))
    /* JADX INFO: renamed from: SearchBar-WuY5d9Q, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final void m2728SearchBarWuY5d9Q(final java.lang.String r35, final kotlin.jvm.functions.Function1<? super java.lang.String, kotlin.Unit> r36, final kotlin.jvm.functions.Function1<? super java.lang.String, kotlin.Unit> r37, final boolean r38, final kotlin.jvm.functions.Function1<? super java.lang.Boolean, kotlin.Unit> r39, androidx.compose.ui.Modifier r40, boolean r41, kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r42, kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r43, kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r44, androidx.compose.ui.graphics.Shape r45, androidx.compose.material3.SearchBarColors r46, float r47, float r48, androidx.compose.foundation.layout.WindowInsets r49, androidx.compose.foundation.interaction.MutableInteractionSource r50, final kotlin.jvm.functions.Function3<? super androidx.compose.foundation.layout.ColumnScope, ? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r51, androidx.compose.runtime.Composer r52, final int r53, final int r54, final int r55) {
        /*
            Method dump skipped, instruction units count: 986
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.SearchBar_androidKt.m2728SearchBarWuY5d9Q(java.lang.String, kotlin.jvm.functions.Function1, kotlin.jvm.functions.Function1, boolean, kotlin.jvm.functions.Function1, androidx.compose.ui.Modifier, boolean, kotlin.jvm.functions.Function2, kotlin.jvm.functions.Function2, kotlin.jvm.functions.Function2, androidx.compose.ui.graphics.Shape, androidx.compose.material3.SearchBarColors, float, float, androidx.compose.foundation.layout.WindowInsets, androidx.compose.foundation.interaction.MutableInteractionSource, kotlin.jvm.functions.Function3, androidx.compose.runtime.Composer, int, int, int):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:108:0x014a  */
    /* JADX WARN: Removed duplicated region for block: B:116:0x0160  */
    /* JADX WARN: Removed duplicated region for block: B:119:0x0168  */
    /* JADX WARN: Removed duplicated region for block: B:127:0x017e  */
    /* JADX WARN: Removed duplicated region for block: B:130:0x0186  */
    /* JADX WARN: Removed duplicated region for block: B:131:0x0189  */
    /* JADX WARN: Removed duplicated region for block: B:142:0x01a9  */
    /* JADX WARN: Removed duplicated region for block: B:143:0x01ac  */
    /* JADX WARN: Removed duplicated region for block: B:153:0x01cc  */
    /* JADX WARN: Removed duplicated region for block: B:154:0x01d1  */
    /* JADX WARN: Removed duplicated region for block: B:164:0x01ee  */
    /* JADX WARN: Removed duplicated region for block: B:165:0x01f1  */
    /* JADX WARN: Removed duplicated region for block: B:175:0x0213  */
    /* JADX WARN: Removed duplicated region for block: B:181:0x0239  */
    /* JADX WARN: Removed duplicated region for block: B:193:0x0274  */
    /* JADX WARN: Removed duplicated region for block: B:194:0x0276  */
    /* JADX WARN: Removed duplicated region for block: B:195:0x027b  */
    /* JADX WARN: Removed duplicated region for block: B:197:0x027e  */
    /* JADX WARN: Removed duplicated region for block: B:198:0x0281  */
    /* JADX WARN: Removed duplicated region for block: B:201:0x0287  */
    /* JADX WARN: Removed duplicated region for block: B:202:0x028a  */
    /* JADX WARN: Removed duplicated region for block: B:204:0x028e  */
    /* JADX WARN: Removed duplicated region for block: B:206:0x0292  */
    /* JADX WARN: Removed duplicated region for block: B:207:0x0295  */
    /* JADX WARN: Removed duplicated region for block: B:210:0x029b  */
    /* JADX WARN: Removed duplicated region for block: B:211:0x02a8  */
    /* JADX WARN: Removed duplicated region for block: B:214:0x02b0  */
    /* JADX WARN: Removed duplicated region for block: B:215:0x02cb  */
    /* JADX WARN: Removed duplicated region for block: B:217:0x02d6  */
    /* JADX WARN: Removed duplicated region for block: B:218:0x02dd  */
    /* JADX WARN: Removed duplicated region for block: B:220:0x02e1  */
    /* JADX WARN: Removed duplicated region for block: B:221:0x02e8  */
    /* JADX WARN: Removed duplicated region for block: B:223:0x02ec  */
    /* JADX WARN: Removed duplicated region for block: B:224:0x02f6  */
    /* JADX WARN: Removed duplicated region for block: B:228:0x030f  */
    /* JADX WARN: Removed duplicated region for block: B:231:0x036b  */
    /* JADX WARN: Removed duplicated region for block: B:235:0x0384  */
    /* JADX WARN: Removed duplicated region for block: B:237:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:46:0x008e  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x0091  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x00b1  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x00b6  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x00cf  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x00d4  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x00ed  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x00f2  */
    /* JADX WARN: Removed duplicated region for block: B:87:0x010b  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x0110  */
    /* JADX WARN: Removed duplicated region for block: B:97:0x0129  */
    /* JADX WARN: Removed duplicated region for block: B:98:0x012c  */
    @kotlin.Deprecated(message = "Use overload which takes inputField as a parameter", replaceWith = @kotlin.ReplaceWith(expression = "DockedSearchBar(\n    inputField = {\n        SearchBarDefaults.InputField(\n            query = query,\n            onQueryChange = onQueryChange,\n            onSearch = onSearch,\n            expanded = active,\n            onExpandedChange = onActiveChange,\n            enabled = enabled,\n            placeholder = placeholder,\n            leadingIcon = leadingIcon,\n            trailingIcon = trailingIcon,\n            colors = colors.inputFieldColors,\n            interactionSource = interactionSource,\n        )\n    },\n    expanded = active,\n    onExpandedChange = onActiveChange,\n    modifier = modifier,\n    shape = shape,\n    colors = colors,\n    tonalElevation = tonalElevation,\n    shadowElevation = shadowElevation,\n    content = content,\n)", imports = {}))
    /* JADX INFO: renamed from: DockedSearchBar-eWTbjVg, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final void m2727DockedSearchBareWTbjVg(final java.lang.String r34, final kotlin.jvm.functions.Function1<? super java.lang.String, kotlin.Unit> r35, final kotlin.jvm.functions.Function1<? super java.lang.String, kotlin.Unit> r36, final boolean r37, final kotlin.jvm.functions.Function1<? super java.lang.Boolean, kotlin.Unit> r38, androidx.compose.ui.Modifier r39, boolean r40, kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r41, kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r42, kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r43, androidx.compose.ui.graphics.Shape r44, androidx.compose.material3.SearchBarColors r45, float r46, float r47, androidx.compose.foundation.interaction.MutableInteractionSource r48, final kotlin.jvm.functions.Function3<? super androidx.compose.foundation.layout.ColumnScope, ? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r49, androidx.compose.runtime.Composer r50, final int r51, final int r52, final int r53) {
        /*
            Method dump skipped, instruction units count: 934
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.SearchBar_androidKt.m2727DockedSearchBareWTbjVg(java.lang.String, kotlin.jvm.functions.Function1, kotlin.jvm.functions.Function1, boolean, kotlin.jvm.functions.Function1, androidx.compose.ui.Modifier, boolean, kotlin.jvm.functions.Function2, kotlin.jvm.functions.Function2, kotlin.jvm.functions.Function2, androidx.compose.ui.graphics.Shape, androidx.compose.material3.SearchBarColors, float, float, androidx.compose.foundation.interaction.MutableInteractionSource, kotlin.jvm.functions.Function3, androidx.compose.runtime.Composer, int, int, int):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:101:0x0125  */
    /* JADX WARN: Removed duplicated region for block: B:102:0x0128  */
    /* JADX WARN: Removed duplicated region for block: B:112:0x0146  */
    /* JADX WARN: Removed duplicated region for block: B:120:0x015c  */
    /* JADX WARN: Removed duplicated region for block: B:123:0x0164  */
    /* JADX WARN: Removed duplicated region for block: B:124:0x0167  */
    /* JADX WARN: Removed duplicated region for block: B:133:0x0181  */
    /* JADX WARN: Removed duplicated region for block: B:139:0x019c  */
    /* JADX WARN: Removed duplicated region for block: B:154:0x01d1  */
    /* JADX WARN: Removed duplicated region for block: B:155:0x01d3  */
    /* JADX WARN: Removed duplicated region for block: B:156:0x01d8  */
    /* JADX WARN: Removed duplicated region for block: B:159:0x01dd  */
    /* JADX WARN: Removed duplicated region for block: B:160:0x01ea  */
    /* JADX WARN: Removed duplicated region for block: B:163:0x01f2  */
    /* JADX WARN: Removed duplicated region for block: B:164:0x020d  */
    /* JADX WARN: Removed duplicated region for block: B:166:0x0215  */
    /* JADX WARN: Removed duplicated region for block: B:167:0x021c  */
    /* JADX WARN: Removed duplicated region for block: B:169:0x0220  */
    /* JADX WARN: Removed duplicated region for block: B:170:0x0227  */
    /* JADX WARN: Removed duplicated region for block: B:173:0x022d  */
    /* JADX WARN: Removed duplicated region for block: B:174:0x023f  */
    /* JADX WARN: Removed duplicated region for block: B:178:0x0256  */
    /* JADX WARN: Removed duplicated region for block: B:179:0x0261  */
    /* JADX WARN: Removed duplicated region for block: B:182:0x029f  */
    /* JADX WARN: Removed duplicated region for block: B:193:0x02e6  */
    /* JADX WARN: Removed duplicated region for block: B:195:0x02ee  */
    /* JADX WARN: Removed duplicated region for block: B:197:0x02f4  */
    /* JADX WARN: Removed duplicated region for block: B:198:0x0304  */
    /* JADX WARN: Removed duplicated region for block: B:205:0x033a  */
    /* JADX WARN: Removed duplicated region for block: B:208:0x0362  */
    /* JADX WARN: Removed duplicated region for block: B:209:0x0374  */
    /* JADX WARN: Removed duplicated region for block: B:212:0x03b8  */
    /* JADX WARN: Removed duplicated region for block: B:216:0x03ca  */
    /* JADX WARN: Removed duplicated region for block: B:218:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:59:0x00aa  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x00af  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x00c8  */
    /* JADX WARN: Removed duplicated region for block: B:76:0x00dc  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x00e4  */
    /* JADX WARN: Removed duplicated region for block: B:87:0x00fa  */
    /* JADX WARN: Removed duplicated region for block: B:90:0x0102  */
    /* JADX WARN: Removed duplicated region for block: B:91:0x0105  */
    /* JADX INFO: renamed from: SearchBarImpl-j1jLAyQ, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final void m2730SearchBarImplj1jLAyQ(final androidx.compose.animation.core.Animatable<java.lang.Float, androidx.compose.animation.core.AnimationVector1D> r27, final androidx.compose.runtime.MutableFloatState r28, final androidx.compose.runtime.MutableState<androidx.activity.BackEventCompat> r29, final androidx.compose.runtime.MutableState<androidx.activity.BackEventCompat> r30, androidx.compose.ui.Modifier r31, final kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r32, androidx.compose.ui.graphics.Shape r33, androidx.compose.material3.SearchBarColors r34, float r35, float r36, androidx.compose.foundation.layout.WindowInsets r37, final kotlin.jvm.functions.Function3<? super androidx.compose.foundation.layout.ColumnScope, ? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r38, androidx.compose.runtime.Composer r39, final int r40, final int r41, final int r42) {
        /*
            Method dump skipped, instruction units count: 998
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.SearchBar_androidKt.m2730SearchBarImplj1jLAyQ(androidx.compose.animation.core.Animatable, androidx.compose.runtime.MutableFloatState, androidx.compose.runtime.MutableState, androidx.compose.runtime.MutableState, androidx.compose.ui.Modifier, kotlin.jvm.functions.Function2, androidx.compose.ui.graphics.Shape, androidx.compose.material3.SearchBarColors, float, float, androidx.compose.foundation.layout.WindowInsets, kotlin.jvm.functions.Function3, androidx.compose.runtime.Composer, int, int, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void SearchBarLayout(final Animatable<Float, AnimationVector1D> animatable, final MutableFloatState mutableFloatState, final MutableState<BackEventCompat> mutableState, final MutableState<BackEventCompat> mutableState2, final Modifier modifier, final WindowInsets windowInsets, final Function2<? super Composer, ? super Integer, Unit> function2, final Function2<? super Composer, ? super Integer, Unit> function22, final Function2<? super Composer, ? super Integer, Unit> function23, Composer composer, final int i) {
        int i2;
        final MutableFloatState mutableFloatState2;
        MutableState<BackEventCompat> mutableState3;
        MutableState<BackEventCompat> mutableState4;
        int i3;
        Composer composerStartRestartGroup = composer.startRestartGroup(70029564);
        ComposerKt.sourceInformation(composerStartRestartGroup, "C(SearchBarLayout)P(!1,3,4,2,6,8,5,7)945@42029L34,950@42191L120,965@42827L5009,946@42068L5768:SearchBar.android.kt#uh7d8r");
        if ((i & 6) == 0) {
            i2 = ((i & 8) == 0 ? composerStartRestartGroup.changed(animatable) : composerStartRestartGroup.changedInstance(animatable) ? 4 : 2) | i;
        } else {
            i2 = i;
        }
        if ((i & 48) == 0) {
            mutableFloatState2 = mutableFloatState;
            i2 |= composerStartRestartGroup.changed(mutableFloatState2) ? 32 : 16;
        } else {
            mutableFloatState2 = mutableFloatState;
        }
        if ((i & 384) == 0) {
            mutableState3 = mutableState;
            i2 |= composerStartRestartGroup.changed(mutableState3) ? 256 : 128;
        } else {
            mutableState3 = mutableState;
        }
        if ((i & 3072) == 0) {
            mutableState4 = mutableState2;
            i2 |= composerStartRestartGroup.changed(mutableState4) ? 2048 : 1024;
        } else {
            mutableState4 = mutableState2;
        }
        if ((i & 24576) == 0) {
            i2 |= composerStartRestartGroup.changed(modifier) ? 16384 : 8192;
        }
        if ((196608 & i) == 0) {
            i2 |= composerStartRestartGroup.changed(windowInsets) ? 131072 : 65536;
        }
        if ((1572864 & i) == 0) {
            i2 |= composerStartRestartGroup.changedInstance(function2) ? 1048576 : 524288;
        }
        if ((12582912 & i) == 0) {
            i2 |= composerStartRestartGroup.changedInstance(function22) ? 8388608 : 4194304;
        }
        if ((100663296 & i) == 0) {
            i2 |= composerStartRestartGroup.changedInstance(function23) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
        }
        if ((38347923 & i2) != 38347922 || !composerStartRestartGroup.getSkipping()) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(70029564, i2, -1, "androidx.compose.material3.SearchBarLayout (SearchBar.android.kt:941)");
            }
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -660147884, "CC(remember):SearchBar.android.kt#9igjgp");
            Object objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = new MutableWindowInsets(null, 1, null);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            final MutableWindowInsets mutableWindowInsets = (MutableWindowInsets) objRememberedValue;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            Modifier modifierZIndex = ZIndexModifierKt.zIndex(modifier, 1.0f);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -660142614, "CC(remember):SearchBar.android.kt#9igjgp");
            boolean z = (458752 & i2) == 131072;
            Object objRememberedValue2 = composerStartRestartGroup.rememberedValue();
            if (z || objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue2 = (Function1) new Function1<WindowInsets, Unit>() { // from class: androidx.compose.material3.SearchBar_androidKt$SearchBarLayout$1$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(WindowInsets windowInsets2) {
                        invoke2(windowInsets2);
                        return Unit.INSTANCE;
                    }

                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(WindowInsets windowInsets2) {
                        mutableWindowInsets.setInsets(WindowInsetsKt.exclude(windowInsets, windowInsets2));
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
            }
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            Modifier modifierConsumeWindowInsets = WindowInsetsPaddingKt.consumeWindowInsets(WindowInsetsPaddingKt.onConsumedWindowInsetsChanged(modifierZIndex, (Function1) objRememberedValue2), windowInsets);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -660117373, "CC(remember):SearchBar.android.kt#9igjgp");
            boolean z2 = ((i2 & 14) == 4 || ((i2 & 8) != 0 && composerStartRestartGroup.changedInstance(animatable))) | ((i2 & 7168) == 2048) | ((i2 & 112) == 32) | ((i2 & 896) == 256);
            MeasurePolicy measurePolicyRememberedValue = composerStartRestartGroup.rememberedValue();
            if (z2 || measurePolicyRememberedValue == Composer.INSTANCE.getEmpty()) {
                final MutableState<BackEventCompat> mutableState5 = mutableState3;
                i3 = i2;
                final MutableState<BackEventCompat> mutableState6 = mutableState4;
                measurePolicyRememberedValue = new MeasurePolicy() { // from class: androidx.compose.material3.SearchBar_androidKt$SearchBarLayout$2$1
                    @Override // androidx.compose.ui.layout.MeasurePolicy
                    /* JADX INFO: renamed from: measure-3p2s80s */
                    public final MeasureResult mo322measure3p2s80s(final MeasureScope measureScope, List<? extends Measurable> list, final long j) {
                        Measurable measurable;
                        final Placeable placeableMo5903measureBRTryo0;
                        int i4;
                        int iM6979getMaxHeightimpl;
                        MeasureScope measureScope2 = measureScope;
                        long j2 = j;
                        final float fFloatValue = animatable.getValue().floatValue();
                        int size = list.size();
                        int i5 = 0;
                        while (i5 < size) {
                            Measurable measurable2 = list.get(i5);
                            if (Intrinsics.areEqual(LayoutIdKt.getLayoutId(measurable2), "InputField")) {
                                int size2 = list.size();
                                int i6 = 0;
                                while (i6 < size2) {
                                    Measurable measurable3 = list.get(i6);
                                    if (Intrinsics.areEqual(LayoutIdKt.getLayoutId(measurable3), "Surface")) {
                                        int size3 = list.size();
                                        int i7 = 0;
                                        while (true) {
                                            if (i7 >= size3) {
                                                measurable = null;
                                                break;
                                            }
                                            measurable = list.get(i7);
                                            if (Intrinsics.areEqual(LayoutIdKt.getLayoutId(measurable), "Content")) {
                                                break;
                                            }
                                            i7++;
                                        }
                                        Measurable measurable4 = measurable;
                                        final int top = mutableWindowInsets.getTop(measureScope2) + measureScope2.mo651roundToPx0680j_4(SearchBar_androidKt.getSearchBarVerticalPadding());
                                        int i8 = measureScope2.mo651roundToPx0680j_4(SearchBar_androidKt.getSearchBarVerticalPadding());
                                        int iM6997constrainWidthK40F9xA = ConstraintsKt.m6997constrainWidthK40F9xA(j2, measurable2.maxIntrinsicWidth(Constraints.m6979getMaxHeightimpl(j2)));
                                        int iM6996constrainHeightK40F9xA = ConstraintsKt.m6996constrainHeightK40F9xA(j2, measurable2.minIntrinsicHeight(Constraints.m6980getMaxWidthimpl(j2)));
                                        int iRoundToInt = MathKt.roundToInt(Constraints.m6980getMaxWidthimpl(j2) * 0.9f);
                                        int iRoundToInt2 = MathKt.roundToInt(Constraints.m6979getMaxHeightimpl(j2) * 0.9f);
                                        final float fCalculatePredictiveBackMultiplier = SearchBar_androidKt.calculatePredictiveBackMultiplier(mutableState6.getValue(), fFloatValue, mutableFloatState2.getFloatValue());
                                        int iLerp = MathHelpersKt.lerp(iM6997constrainWidthK40F9xA, iRoundToInt, fCalculatePredictiveBackMultiplier);
                                        int i9 = top + iM6996constrainHeightK40F9xA;
                                        int iLerp2 = MathHelpersKt.lerp(i9, iRoundToInt2, fCalculatePredictiveBackMultiplier);
                                        int iM6980getMaxWidthimpl = Constraints.m6980getMaxWidthimpl(j2);
                                        int iM6979getMaxHeightimpl2 = Constraints.m6979getMaxHeightimpl(j2);
                                        int iLerp3 = MathHelpersKt.lerp(iLerp, iM6980getMaxWidthimpl, fFloatValue);
                                        final int iLerp4 = MathHelpersKt.lerp(iLerp2, iM6979getMaxHeightimpl2, fFloatValue);
                                        final int iLerp5 = MathHelpersKt.lerp(top, 0, fFloatValue);
                                        final int iLerp6 = MathHelpersKt.lerp(0, i8, fFloatValue);
                                        final Placeable placeableMo5903measureBRTryo02 = measurable2.mo5903measureBRTryo0(ConstraintsKt.Constraints(iLerp3, iM6980getMaxWidthimpl, iM6996constrainHeightK40F9xA, iM6996constrainHeightK40F9xA));
                                        int width = placeableMo5903measureBRTryo02.getWidth();
                                        final Placeable placeableMo5903measureBRTryo03 = measurable3.mo5903measureBRTryo0(Constraints.INSTANCE.m6990fixedJhjzzOo(width, iLerp4 - iLerp5));
                                        if (measurable4 != null) {
                                            if (Constraints.m6975getHasBoundedHeightimpl(j)) {
                                                i4 = 0;
                                                iM6979getMaxHeightimpl = RangesKt.coerceAtLeast(Constraints.m6979getMaxHeightimpl(j) - (i9 + i8), 0);
                                            } else {
                                                i4 = 0;
                                                iM6979getMaxHeightimpl = Constraints.m6979getMaxHeightimpl(j);
                                            }
                                            placeableMo5903measureBRTryo0 = measurable4.mo5903measureBRTryo0(ConstraintsKt.Constraints(width, width, i4, iM6979getMaxHeightimpl));
                                        } else {
                                            placeableMo5903measureBRTryo0 = null;
                                        }
                                        final MutableState<BackEventCompat> mutableState7 = mutableState6;
                                        final MutableState<BackEventCompat> mutableState8 = mutableState5;
                                        return MeasureScope.layout$default(measureScope, width, iLerp4, null, new Function1<Placeable.PlacementScope, Unit>() { // from class: androidx.compose.material3.SearchBar_androidKt$SearchBarLayout$2$1.1
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
                                                int i10 = measureScope.mo651roundToPx0680j_4(SearchBar_androidKt.SearchBarPredictiveBackMinMargin);
                                                int iM2733calculatePredictiveBackOffsetXrOvwMX4 = SearchBar_androidKt.m2733calculatePredictiveBackOffsetXrOvwMX4(j, i10, mutableState7.getValue(), measureScope.getLayoutDirection(), fFloatValue, fCalculatePredictiveBackMultiplier);
                                                int iM2734calculatePredictiveBackOffsetYdzo92Q0 = SearchBar_androidKt.m2734calculatePredictiveBackOffsetYdzo92Q0(j, i10, mutableState7.getValue(), mutableState8.getValue(), iLerp4, measureScope.mo651roundToPx0680j_4(SearchBar_androidKt.SearchBarPredictiveBackMaxOffsetY), fCalculatePredictiveBackMultiplier);
                                                Placeable.PlacementScope.placeRelative$default(placementScope, placeableMo5903measureBRTryo03, iM2733calculatePredictiveBackOffsetXrOvwMX4, iM2734calculatePredictiveBackOffsetYdzo92Q0 + iLerp5, 0.0f, 4, null);
                                                Placeable.PlacementScope.placeRelative$default(placementScope, placeableMo5903measureBRTryo02, iM2733calculatePredictiveBackOffsetXrOvwMX4, iM2734calculatePredictiveBackOffsetYdzo92Q0 + top, 0.0f, 4, null);
                                                Placeable placeable = placeableMo5903measureBRTryo0;
                                                if (placeable != null) {
                                                    Placeable.PlacementScope.placeRelative$default(placementScope, placeable, iM2733calculatePredictiveBackOffsetXrOvwMX4, iM2734calculatePredictiveBackOffsetYdzo92Q0 + top + placeableMo5903measureBRTryo02.getHeight() + iLerp6, 0.0f, 4, null);
                                                }
                                            }
                                        }, 4, null);
                                    }
                                    i6++;
                                    measureScope2 = measureScope;
                                    j2 = j;
                                }
                                throw new NoSuchElementException("Collection contains no element matching the predicate.");
                            }
                            i5++;
                            measureScope2 = measureScope;
                            j2 = j;
                        }
                        throw new NoSuchElementException("Collection contains no element matching the predicate.");
                    }
                };
                composerStartRestartGroup.updateRememberedValue(measurePolicyRememberedValue);
            } else {
                i3 = i2;
            }
            MeasurePolicy measurePolicy = (MeasurePolicy) measurePolicyRememberedValue;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap = composerStartRestartGroup.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierConsumeWindowInsets);
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
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 36789781, "C955@42396L85,956@42494L119:SearchBar.android.kt#uh7d8r");
            Modifier modifierLayoutId = LayoutIdKt.layoutId(Modifier.INSTANCE, LayoutIdSurface);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
            MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), true);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
            int currentCompositeKeyHash2 = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap2 = composerStartRestartGroup.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier2 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierLayoutId);
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
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1442016074, "C955@42470L9:SearchBar.android.kt#uh7d8r");
            function22.invoke(composerStartRestartGroup, Integer.valueOf((i3 >> 21) & 14));
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            composerStartRestartGroup.endNode();
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            Modifier modifierLayoutId2 = LayoutIdKt.layoutId(Modifier.INSTANCE, LayoutIdInputField);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
            MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy2 = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), true);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
            int currentCompositeKeyHash3 = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap3 = composerStartRestartGroup.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier3 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierLayoutId2);
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
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1442132231, "C957@42587L12:SearchBar.android.kt#uh7d8r");
            function2.invoke(composerStartRestartGroup, Integer.valueOf((i3 >> 18) & 14));
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            composerStartRestartGroup.endNode();
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            composerStartRestartGroup.startReplaceGroup(-1107184481);
            ComposerKt.sourceInformation(composerStartRestartGroup, "*960@42668L127");
            if (function23 != null) {
                Modifier modifierLayoutId3 = LayoutIdKt.layoutId(Modifier.INSTANCE, LayoutIdSearchContent);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
                MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy3 = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), true);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
                int currentCompositeKeyHash4 = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                CompositionLocalMap currentCompositionLocalMap4 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier4 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierLayoutId3);
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
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1302856683, "C961@42768L9:SearchBar.android.kt#uh7d8r");
                function23.invoke(composerStartRestartGroup, 0);
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                composerStartRestartGroup.endNode();
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                Unit unit = Unit.INSTANCE;
                Unit unit2 = Unit.INSTANCE;
            }
            composerStartRestartGroup.endReplaceGroup();
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
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
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SearchBar_androidKt.SearchBarLayout.4
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

                public final void invoke(Composer composer2, int i4) {
                    SearchBar_androidKt.SearchBarLayout(animatable, mutableFloatState, mutableState, mutableState2, modifier, windowInsets, function2, function22, function23, composer2, RecomposeScopeImplKt.updateChangedFlags(i | 1));
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final float calculatePredictiveBackMultiplier(BackEventCompat backEventCompat, float f, float f2) {
        if (backEventCompat == null) {
            return 0.0f;
        }
        if (Float.isNaN(f2)) {
            return 1.0f;
        }
        if (f2 <= 0.0f) {
            return 0.0f;
        }
        return f / f2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: calculatePredictiveBackOffsetX-rOvwMX4, reason: not valid java name */
    public static final int m2733calculatePredictiveBackOffsetXrOvwMX4(long j, int i, BackEventCompat backEventCompat, LayoutDirection layoutDirection, float f, float f2) {
        if (backEventCompat == null || f2 == 0.0f) {
            return 0;
        }
        return MathKt.roundToInt(((Constraints.m6980getMaxWidthimpl(j) * SearchBarPredictiveBackMaxOffsetXRatio) - i) * (1 - f) * f2 * (backEventCompat.getSwipeEdge() == 0 ? 1 : -1) * (layoutDirection == LayoutDirection.Ltr ? 1 : -1));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: calculatePredictiveBackOffsetY-dzo92Q0, reason: not valid java name */
    public static final int m2734calculatePredictiveBackOffsetYdzo92Q0(long j, int i, BackEventCompat backEventCompat, BackEventCompat backEventCompat2, int i2, int i3, float f) {
        if (backEventCompat2 == null || backEventCompat == null || f == 0.0f) {
            return 0;
        }
        int iMin = Math.min(Math.max(0, ((Constraints.m6979getMaxHeightimpl(j) - i2) / 2) - i), i3);
        float touchY = backEventCompat.getTouchY() - backEventCompat2.getTouchY();
        float fAbs = Math.abs(touchY) / Constraints.m6979getMaxHeightimpl(j);
        return MathKt.roundToInt(MathHelpersKt.lerp(0, iMin, fAbs) * f * Math.signum(touchY));
    }

    static {
        float f = 8;
        SearchBarVerticalPadding = Dp.m7027constructorimpl(f);
        SearchBarPredictiveBackMinMargin = Dp.m7027constructorimpl(f);
        CubicBezierEasing easingEmphasizedDecelerateCubicBezier = MotionTokens.INSTANCE.getEasingEmphasizedDecelerateCubicBezier();
        AnimationEnterEasing = easingEmphasizedDecelerateCubicBezier;
        CubicBezierEasing cubicBezierEasing = new CubicBezierEasing(0.0f, 1.0f, 0.0f, 1.0f);
        AnimationExitEasing = cubicBezierEasing;
        TweenSpec tweenSpecTween = AnimationSpecKt.tween(600, 100, easingEmphasizedDecelerateCubicBezier);
        AnimationEnterFloatSpec = tweenSpecTween;
        TweenSpec tweenSpecTween2 = AnimationSpecKt.tween(AnimationExitDurationMillis, 100, cubicBezierEasing);
        AnimationExitFloatSpec = tweenSpecTween2;
        AnimationPredictiveBackExitFloatSpec = AnimationSpecKt.tween$default(AnimationExitDurationMillis, 0, cubicBezierEasing, 2, null);
        TweenSpec tweenSpecTween3 = AnimationSpecKt.tween(600, 100, easingEmphasizedDecelerateCubicBezier);
        AnimationEnterSizeSpec = tweenSpecTween3;
        TweenSpec tweenSpecTween4 = AnimationSpecKt.tween(AnimationExitDurationMillis, 100, cubicBezierEasing);
        AnimationExitSizeSpec = tweenSpecTween4;
        DockedEnterTransition = EnterExitTransitionKt.fadeIn$default(tweenSpecTween, 0.0f, 2, null).plus(EnterExitTransitionKt.expandVertically$default(tweenSpecTween3, null, false, null, 14, null));
        DockedExitTransition = EnterExitTransitionKt.fadeOut$default(tweenSpecTween2, 0.0f, 2, null).plus(EnterExitTransitionKt.shrinkVertically$default(tweenSpecTween4, null, false, null, 14, null));
    }

    public static final float getDockedExpandedTableMinHeight() {
        return DockedExpandedTableMinHeight;
    }

    public static final float getSearchBarMinWidth() {
        return SearchBarMinWidth;
    }

    public static final float getSearchBarVerticalPadding() {
        return SearchBarVerticalPadding;
    }

    private static final boolean SearchBarImpl_j1jLAyQ$lambda$9(State<Boolean> state) {
        return state.getValue().booleanValue();
    }

    private static final boolean SearchBarImpl_j1jLAyQ$lambda$12(State<Boolean> state) {
        return state.getValue().booleanValue();
    }
}
