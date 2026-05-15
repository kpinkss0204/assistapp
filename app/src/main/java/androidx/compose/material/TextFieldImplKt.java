package androidx.compose.material;

import androidx.compose.foundation.BackgroundKt;
import androidx.compose.foundation.interaction.FocusInteractionKt;
import androidx.compose.foundation.interaction.InteractionSource;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.foundation.layout.PaddingValues;
import androidx.compose.runtime.Applier;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.ProvidedValue;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambda;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.draw.AlphaKt;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.geometry.SizeKt;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.layout.IntrinsicMeasurable;
import androidx.compose.ui.layout.LayoutIdKt;
import androidx.compose.ui.layout.LayoutIdParentData;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.semantics.SemanticsModifierKt;
import androidx.compose.ui.semantics.SemanticsPropertiesKt;
import androidx.compose.ui.semantics.SemanticsPropertyReceiver;
import androidx.compose.ui.text.AnnotatedString;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.text.TextStyleKt;
import androidx.compose.ui.text.input.TransformedText;
import androidx.compose.ui.text.input.VisualTransformation;
import androidx.compose.ui.unit.ConstraintsKt;
import androidx.compose.ui.unit.Dp;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.accessibility.AccessibilityEventCompat;
import androidx.profileinstaller.ProfileVerifier;
import androidx.savedstate.serialization.ClassDiscriminatorModeKt;
import com.google.firebase.analytics.FirebaseAnalytics;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.functions.Function6;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: TextFieldImpl.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0098\u0001\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\u001aÙ\u0001\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020\f2\u0011\u0010$\u001a\r\u0012\u0004\u0012\u00020 0%¢\u0006\u0002\b&2\u0006\u0010'\u001a\u00020(2\u0013\u0010)\u001a\u000f\u0012\u0004\u0012\u00020 \u0018\u00010%¢\u0006\u0002\b&2\u0013\u0010*\u001a\u000f\u0012\u0004\u0012\u00020 \u0018\u00010%¢\u0006\u0002\b&2\u0013\u0010+\u001a\u000f\u0012\u0004\u0012\u00020 \u0018\u00010%¢\u0006\u0002\b&2\u0013\u0010,\u001a\u000f\u0012\u0004\u0012\u00020 \u0018\u00010%¢\u0006\u0002\b&2\u0006\u0010-\u001a\u00020.2\u0006\u0010/\u001a\u00020.2\u0006\u00100\u001a\u00020.2\u0006\u00101\u001a\u0002022\u0006\u00103\u001a\u0002042\u0006\u00105\u001a\u0002062\u0006\u00107\u001a\u0002082\u0013\u00109\u001a\u000f\u0012\u0004\u0012\u00020 \u0018\u00010%¢\u0006\u0002\b&H\u0001¢\u0006\u0002\u0010:\u001aT\u0010;\u001a\u00020 2\u0006\u0010<\u001a\u00020=2\n\b\u0002\u0010>\u001a\u0004\u0018\u00010?2\n\b\u0002\u0010@\u001a\u0004\u0018\u00010A2 \u0010B\u001a\u001c\u0012\u0004\u0012\u00020 0%¢\u0006\u0002\b&¢\u0006\f\bC\u0012\b\bD\u0012\u0004\b\u0003\u0010\u0000H\u0001ø\u0001\u0000¢\u0006\u0004\bE\u0010F\u001a\u0012\u0010G\u001a\u00020\u00012\b\u0010H\u001a\u0004\u0018\u00010IH\u0000\u001a\u0012\u0010J\u001a\u00020\u00012\b\u0010H\u001a\u0004\u0018\u00010IH\u0000\u001a\u001c\u0010K\u001a\u00020\b*\u00020\b2\u0006\u00100\u001a\u00020.2\u0006\u0010L\u001a\u00020\fH\u0000\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0080T¢\u0006\u0002\n\u0000\"\u0016\u0010\u0002\u001a\u00020\u0003X\u0080\u0004¢\u0006\n\n\u0002\u0010\u0006\u001a\u0004\b\u0004\u0010\u0005\"\u0014\u0010\u0007\u001a\u00020\bX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n\"\u000e\u0010\u000b\u001a\u00020\fX\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010\r\u001a\u00020\fX\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010\u000e\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u000f\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0010\u001a\u00020\fX\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0011\u001a\u00020\fX\u0080T¢\u0006\u0002\n\u0000\"\u0016\u0010\u0012\u001a\u00020\u0003X\u0080\u0004¢\u0006\n\n\u0002\u0010\u0006\u001a\u0004\b\u0013\u0010\u0005\"\u000e\u0010\u0014\u001a\u00020\fX\u0080T¢\u0006\u0002\n\u0000\"\u0016\u0010\u0015\u001a\u00020\u0016X\u0080\u0004¢\u0006\n\n\u0002\u0010\u0019\u001a\u0004\b\u0017\u0010\u0018\"\u001a\u0010\u001a\u001a\u0004\u0018\u00010\u001b*\u00020\u001c8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u001d\u0010\u001e\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006M"}, d2 = {"AnimationDuration", "", "HorizontalIconPadding", "Landroidx/compose/ui/unit/Dp;", "getHorizontalIconPadding", "()F", "F", "IconDefaultSizeModifier", "Landroidx/compose/ui/Modifier;", "getIconDefaultSizeModifier", "()Landroidx/compose/ui/Modifier;", "LabelId", "", "LeadingId", "PlaceholderAnimationDelayOrDuration", "PlaceholderAnimationDuration", "PlaceholderId", "TextFieldId", "TextFieldPadding", "getTextFieldPadding", "TrailingId", "ZeroConstraints", "Landroidx/compose/ui/unit/Constraints;", "getZeroConstraints", "()J", "J", "layoutId", "", "Landroidx/compose/ui/layout/IntrinsicMeasurable;", "getLayoutId", "(Landroidx/compose/ui/layout/IntrinsicMeasurable;)Ljava/lang/Object;", "CommonDecorationBox", "", ClassDiscriminatorModeKt.CLASS_DISCRIMINATOR_KEY, "Landroidx/compose/material/TextFieldType;", "value", "innerTextField", "Lkotlin/Function0;", "Landroidx/compose/runtime/Composable;", "visualTransformation", "Landroidx/compose/ui/text/input/VisualTransformation;", "label", "placeholder", "leadingIcon", "trailingIcon", "singleLine", "", "enabled", "isError", "interactionSource", "Landroidx/compose/foundation/interaction/InteractionSource;", "contentPadding", "Landroidx/compose/foundation/layout/PaddingValues;", "shape", "Landroidx/compose/ui/graphics/Shape;", "colors", "Landroidx/compose/material/TextFieldColors;", OutlinedTextFieldKt.BorderId, "(Landroidx/compose/material/TextFieldType;Ljava/lang/String;Lkotlin/jvm/functions/Function2;Landroidx/compose/ui/text/input/VisualTransformation;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;ZZZLandroidx/compose/foundation/interaction/InteractionSource;Landroidx/compose/foundation/layout/PaddingValues;Landroidx/compose/ui/graphics/Shape;Landroidx/compose/material/TextFieldColors;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;II)V", "Decoration", "contentColor", "Landroidx/compose/ui/graphics/Color;", "typography", "Landroidx/compose/ui/text/TextStyle;", "contentAlpha", "", FirebaseAnalytics.Param.CONTENT, "Landroidx/compose/runtime/ComposableOpenTarget;", FirebaseAnalytics.Param.INDEX, "Decoration-euL9pac", "(JLandroidx/compose/ui/text/TextStyle;Ljava/lang/Float;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;II)V", "heightOrZero", "placeable", "Landroidx/compose/ui/layout/Placeable;", "widthOrZero", "defaultErrorSemantics", "defaultErrorMessage", "material_release"}, k = 2, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
public final class TextFieldImplKt {
    public static final int AnimationDuration = 150;
    private static final Modifier IconDefaultSizeModifier;
    public static final String LabelId = "Label";
    public static final String LeadingId = "Leading";
    private static final int PlaceholderAnimationDelayOrDuration = 67;
    private static final int PlaceholderAnimationDuration = 83;
    public static final String PlaceholderId = "Hint";
    public static final String TextFieldId = "TextField";
    public static final String TrailingId = "Trailing";
    private static final long ZeroConstraints = ConstraintsKt.Constraints(0, 0, 0, 0);
    private static final float TextFieldPadding = Dp.m7027constructorimpl(16);
    private static final float HorizontalIconPadding = Dp.m7027constructorimpl(12);

    public static final void CommonDecorationBox(final TextFieldType textFieldType, final String str, final Function2<? super Composer, ? super Integer, Unit> function2, final VisualTransformation visualTransformation, final Function2<? super Composer, ? super Integer, Unit> function22, final Function2<? super Composer, ? super Integer, Unit> function23, final Function2<? super Composer, ? super Integer, Unit> function24, final Function2<? super Composer, ? super Integer, Unit> function25, final boolean z, final boolean z2, final boolean z3, final InteractionSource interactionSource, final PaddingValues paddingValues, final Shape shape, final TextFieldColors textFieldColors, final Function2<? super Composer, ? super Integer, Unit> function26, Composer composer, final int i, final int i2) {
        TextFieldType textFieldType2;
        int i3;
        String str2;
        Function2<? super Composer, ? super Integer, Unit> function27;
        int i4;
        int i5;
        InputPhase inputPhase;
        Composer composer2;
        Composer composerStartRestartGroup = composer.startRestartGroup(341783750);
        ComposerKt.sourceInformation(composerStartRestartGroup, "C(CommonDecorationBox)P(13,14,4,15,7,9,8,12,11,3,6,5,2,10,1)82@3152L105,86@3307L25,103@3917L10,120@4754L4202,110@4252L4704:TextFieldImpl.kt#jmzs0o");
        if ((i & 6) == 0) {
            textFieldType2 = textFieldType;
            i3 = (composerStartRestartGroup.changed(textFieldType2) ? 4 : 2) | i;
        } else {
            textFieldType2 = textFieldType;
            i3 = i;
        }
        if ((i & 48) == 0) {
            str2 = str;
            i3 |= composerStartRestartGroup.changed(str2) ? 32 : 16;
        } else {
            str2 = str;
        }
        if ((i & 384) == 0) {
            function27 = function2;
            i3 |= composerStartRestartGroup.changedInstance(function27) ? 256 : 128;
        } else {
            function27 = function2;
        }
        if ((i & 3072) == 0) {
            i3 |= composerStartRestartGroup.changed(visualTransformation) ? 2048 : 1024;
        }
        if ((i & 24576) == 0) {
            i3 |= composerStartRestartGroup.changedInstance(function22) ? 16384 : 8192;
        }
        if ((i & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
            i4 = 196608;
            i3 |= composerStartRestartGroup.changedInstance(function23) ? 131072 : 65536;
        } else {
            i4 = 196608;
        }
        if ((i & 1572864) == 0) {
            i3 |= composerStartRestartGroup.changedInstance(function24) ? 1048576 : 524288;
        }
        if ((i & 12582912) == 0) {
            i3 |= composerStartRestartGroup.changedInstance(function25) ? 8388608 : 4194304;
        }
        if ((i & 100663296) == 0) {
            i3 |= composerStartRestartGroup.changed(z) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
        }
        if ((i & 805306368) == 0) {
            i3 |= composerStartRestartGroup.changed(z2) ? 536870912 : 268435456;
        }
        if ((i2 & 6) == 0) {
            i5 = i2 | (composerStartRestartGroup.changed(z3) ? 4 : 2);
        } else {
            i5 = i2;
        }
        if ((i2 & 48) == 0) {
            i5 |= composerStartRestartGroup.changed(interactionSource) ? 32 : 16;
        }
        if ((i2 & 384) == 0) {
            i5 |= composerStartRestartGroup.changed(paddingValues) ? 256 : 128;
        }
        if ((i2 & 3072) == 0) {
            i5 |= composerStartRestartGroup.changed(shape) ? 2048 : 1024;
        }
        if ((i2 & 24576) == 0) {
            i5 |= composerStartRestartGroup.changed(textFieldColors) ? 16384 : 8192;
        }
        if ((i2 & i4) == 0) {
            i5 |= composerStartRestartGroup.changedInstance(function26) ? 131072 : 65536;
        }
        int i6 = i5;
        if ((i3 & 306783379) != 306783378 || (74899 & i6) != 74898 || !composerStartRestartGroup.getSkipping()) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(341783750, i3, i6, "androidx.compose.material.CommonDecorationBox (TextFieldImpl.kt:81)");
            }
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1578828051, "CC(remember):TextFieldImpl.kt#9igjgp");
            boolean z4 = ((i3 & 112) == 32) | ((i3 & 7168) == 2048);
            Object objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (z4 || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = visualTransformation.filter(new AnnotatedString(str2, null, null, 6, null));
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            final String text = ((TransformedText) objRememberedValue).getText().getText();
            if (FocusInteractionKt.collectIsFocusedAsState(interactionSource, composerStartRestartGroup, (i6 >> 3) & 14).getValue().booleanValue()) {
                inputPhase = InputPhase.Focused;
            } else {
                inputPhase = text.length() == 0 ? InputPhase.UnfocusedEmpty : InputPhase.UnfocusedNotEmpty;
            }
            Function3<InputPhase, Composer, Integer, Color> function3 = new Function3<InputPhase, Composer, Integer, Color>() { // from class: androidx.compose.material.TextFieldImplKt$CommonDecorationBox$labelColor$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(3);
                }

                @Override // kotlin.jvm.functions.Function3
                public /* bridge */ /* synthetic */ Color invoke(InputPhase inputPhase2, Composer composer3, Integer num) {
                    return Color.m4558boximpl(m2013invokeXeAY9LY(inputPhase2, composer3, num.intValue()));
                }

                /* JADX INFO: renamed from: invoke-XeAY9LY, reason: not valid java name */
                public final long m2013invokeXeAY9LY(InputPhase inputPhase2, Composer composer3, int i7) {
                    composer3.startReplaceGroup(-1272940975);
                    ComposerKt.sourceInformation(composer3, "C94@3595L273:TextFieldImpl.kt#jmzs0o");
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-1272940975, i7, -1, "androidx.compose.material.CommonDecorationBox.<anonymous> (TextFieldImpl.kt:94)");
                    }
                    long jM4578unboximpl = textFieldColors.labelColor(z2, inputPhase2 == InputPhase.UnfocusedEmpty ? false : z3, interactionSource, composer3, 0).getValue().m4578unboximpl();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    composer3.endReplaceGroup();
                    return jM4578unboximpl;
                }
            };
            Typography typography = MaterialTheme.INSTANCE.getTypography(composerStartRestartGroup, 6);
            TextStyle subtitle1 = typography.getSubtitle1();
            TextStyle caption = typography.getCaption();
            final boolean z5 = (Color.m4569equalsimpl0(subtitle1.m6542getColor0d7_KjU(), Color.INSTANCE.m4604getUnspecified0d7_KjU()) && !Color.m4569equalsimpl0(caption.m6542getColor0d7_KjU(), Color.INSTANCE.m4604getUnspecified0d7_KjU())) || (!Color.m4569equalsimpl0(subtitle1.m6542getColor0d7_KjU(), Color.INSTANCE.m4604getUnspecified0d7_KjU()) && Color.m4569equalsimpl0(caption.m6542getColor0d7_KjU(), Color.INSTANCE.m4604getUnspecified0d7_KjU()));
            TextFieldTransitionScope textFieldTransitionScope = TextFieldTransitionScope.INSTANCE;
            composerStartRestartGroup.startReplaceGroup(1578865765);
            ComposerKt.sourceInformation(composerStartRestartGroup, "*112@4348L10");
            long jM6542getColor0d7_KjU = MaterialTheme.INSTANCE.getTypography(composerStartRestartGroup, 6).getCaption().m6542getColor0d7_KjU();
            if (z5) {
                composerStartRestartGroup.startReplaceGroup(-1572851052);
                ComposerKt.sourceInformation(composerStartRestartGroup, "*113@4440L22");
                if (jM6542getColor0d7_KjU == 16) {
                    jM6542getColor0d7_KjU = function3.invoke(inputPhase, composerStartRestartGroup, 0).m4578unboximpl();
                }
            } else {
                composerStartRestartGroup.startReplaceGroup(780548205);
            }
            composerStartRestartGroup.endReplaceGroup();
            long j = jM6542getColor0d7_KjU;
            composerStartRestartGroup.endReplaceGroup();
            composerStartRestartGroup.startReplaceGroup(1578871879);
            ComposerKt.sourceInformation(composerStartRestartGroup, "*115@4539L10");
            long jM6542getColor0d7_KjU2 = MaterialTheme.INSTANCE.getTypography(composerStartRestartGroup, 6).getSubtitle1().m6542getColor0d7_KjU();
            if (z5) {
                composerStartRestartGroup.startReplaceGroup(-1572659596);
                ComposerKt.sourceInformation(composerStartRestartGroup, "*116@4633L22");
                if (jM6542getColor0d7_KjU2 == 16) {
                    jM6542getColor0d7_KjU2 = function3.invoke(inputPhase, composerStartRestartGroup, 0).m4578unboximpl();
                }
            } else {
                composerStartRestartGroup.startReplaceGroup(780554381);
            }
            composerStartRestartGroup.endReplaceGroup();
            long j2 = jM6542getColor0d7_KjU2;
            composerStartRestartGroup.endReplaceGroup();
            final TextFieldType textFieldType3 = textFieldType2;
            final Function2<? super Composer, ? super Integer, Unit> function28 = function27;
            composer2 = composerStartRestartGroup;
            textFieldTransitionScope.m2018TransitionDTcfvLk(inputPhase, j, j2, function3, function22 != null, ComposableLambdaKt.rememberComposableLambda(225557475, true, new Function6<Float, Color, Color, Float, Composer, Integer, Unit>() { // from class: androidx.compose.material.TextFieldImplKt.CommonDecorationBox.3

                /* JADX INFO: renamed from: androidx.compose.material.TextFieldImplKt$CommonDecorationBox$3$WhenMappings */
                /* JADX INFO: compiled from: TextFieldImpl.kt */
                @Metadata(k = 3, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
                public /* synthetic */ class WhenMappings {
                    public static final /* synthetic */ int[] $EnumSwitchMapping$0;

                    static {
                        int[] iArr = new int[TextFieldType.values().length];
                        try {
                            iArr[TextFieldType.Filled.ordinal()] = 1;
                        } catch (NoSuchFieldError unused) {
                        }
                        try {
                            iArr[TextFieldType.Outlined.ordinal()] = 2;
                        } catch (NoSuchFieldError unused2) {
                        }
                        $EnumSwitchMapping$0 = iArr;
                    }
                }

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                {
                    super(6);
                }

                @Override // kotlin.jvm.functions.Function6
                public /* bridge */ /* synthetic */ Unit invoke(Float f, Color color, Color color2, Float f2, Composer composer3, Integer num) {
                    m2011invokeRIQooxk(f.floatValue(), color.m4578unboximpl(), color2.m4578unboximpl(), f2.floatValue(), composer3, num.intValue());
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke-RIQooxk, reason: not valid java name */
                public final void m2011invokeRIQooxk(float f, long j3, final long j4, final float f2, Composer composer3, int i7) {
                    int i8;
                    long j5;
                    boolean z6;
                    ComposableLambda composableLambda;
                    ComposableLambda composableLambda2;
                    ComposableLambda composableLambda3;
                    ComposableLambda composableLambda4;
                    final float f3 = f;
                    ComposerKt.sourceInformation(composer3, "CP(1,2:c#ui.graphics.Color,0:c#ui.graphics.Color)150@6176L53,157@6477L54,165@6815L24:TextFieldImpl.kt#jmzs0o");
                    if ((i7 & 6) == 0) {
                        i8 = (composer3.changed(f3) ? 4 : 2) | i7;
                    } else {
                        i8 = i7;
                    }
                    if ((i7 & 48) == 0) {
                        j5 = j3;
                        i8 |= composer3.changed(j5) ? 32 : 16;
                    } else {
                        j5 = j3;
                    }
                    if ((i7 & 384) == 0) {
                        i8 |= composer3.changed(j4) ? 256 : 128;
                    }
                    if ((i7 & 3072) == 0) {
                        i8 |= composer3.changed(f2) ? 2048 : 1024;
                    }
                    int i9 = i8;
                    if ((i9 & 9363) != 9362 || !composer3.getSkipping()) {
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(225557475, i9, -1, "androidx.compose.material.CommonDecorationBox.<anonymous> (TextFieldImpl.kt:122)");
                        }
                        final Function2<Composer, Integer, Unit> function29 = function22;
                        if (function29 == null) {
                            composer3.startReplaceGroup(-1572365903);
                            composer3.endReplaceGroup();
                            z6 = true;
                            composableLambda = null;
                        } else {
                            composer3.startReplaceGroup(-1572365902);
                            ComposerKt.sourceInformation(composer3, "*123@4933L415");
                            final boolean z7 = z5;
                            z6 = true;
                            final long j6 = j5;
                            Function2<Composer, Integer, Unit> function210 = new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.TextFieldImplKt$CommonDecorationBox$3$decoratedLabel$1$1
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

                                public final void invoke(Composer composer4, int i10) {
                                    ComposerKt.sourceInformation(composer4, "C*125@5012L10,126@5068L10,131@5279L55:TextFieldImpl.kt#jmzs0o");
                                    if ((i10 & 3) != 2 || !composer4.getSkipping()) {
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventStart(-1865025495, i10, -1, "androidx.compose.material.CommonDecorationBox.<anonymous>.<anonymous>.<anonymous> (TextFieldImpl.kt:124)");
                                        }
                                        TextStyle textStyleLerp = TextStyleKt.lerp(MaterialTheme.INSTANCE.getTypography(composer4, 6).getSubtitle1(), MaterialTheme.INSTANCE.getTypography(composer4, 6).getCaption(), f3);
                                        boolean z8 = z7;
                                        long j7 = j6;
                                        if (z8) {
                                            textStyleLerp = TextStyle.m6525copyp1EtxEg$default(textStyleLerp, j7, 0L, null, null, null, null, null, 0L, null, null, null, 0L, null, null, null, 0, 0, 0L, null, null, null, 0, 0, null, 16777214, null);
                                        }
                                        TextFieldImplKt.m2010DecorationeuL9pac(j4, textStyleLerp, null, function29, composer4, 384, 0);
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventEnd();
                                            return;
                                        }
                                        return;
                                    }
                                    composer4.skipToGroupEnd();
                                }
                            };
                            f3 = f3;
                            ComposableLambda composableLambdaRememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(-1865025495, true, function210, composer3, 54);
                            composer3.endReplaceGroup();
                            composableLambda = composableLambdaRememberComposableLambda;
                        }
                        if (function23 != null && text.length() == 0 && f2 > 0.0f) {
                            composer3.startReplaceGroup(-1571586748);
                            ComposerKt.sourceInformation(composer3, "139@5719L393");
                            final TextFieldColors textFieldColors2 = textFieldColors;
                            final boolean z8 = z2;
                            final Function2<Composer, Integer, Unit> function211 = function23;
                            ComposableLambda composableLambdaRememberComposableLambda2 = ComposableLambdaKt.rememberComposableLambda(-413527723, z6, new Function3<Modifier, Composer, Integer, Unit>() { // from class: androidx.compose.material.TextFieldImplKt$CommonDecorationBox$3$decoratedPlaceholder$1
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                /* JADX WARN: Multi-variable type inference failed */
                                {
                                    super(3);
                                }

                                @Override // kotlin.jvm.functions.Function3
                                public /* bridge */ /* synthetic */ Unit invoke(Modifier modifier, Composer composer4, Integer num) {
                                    invoke(modifier, composer4, num.intValue());
                                    return Unit.INSTANCE;
                                }

                                public final void invoke(Modifier modifier, Composer composer4, int i10) {
                                    ComposerKt.sourceInformation(composer4, "C140@5753L341:TextFieldImpl.kt#jmzs0o");
                                    if ((i10 & 6) == 0) {
                                        i10 |= composer4.changed(modifier) ? 4 : 2;
                                    }
                                    if ((i10 & 19) != 18 || !composer4.getSkipping()) {
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventStart(-413527723, i10, -1, "androidx.compose.material.CommonDecorationBox.<anonymous>.<anonymous> (TextFieldImpl.kt:140)");
                                        }
                                        Modifier modifierAlpha = AlphaKt.alpha(modifier, f2);
                                        TextFieldColors textFieldColors3 = textFieldColors2;
                                        boolean z9 = z8;
                                        Function2<Composer, Integer, Unit> function212 = function211;
                                        ComposerKt.sourceInformationMarkerStart(composer4, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
                                        MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false);
                                        ComposerKt.sourceInformationMarkerStart(composer4, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
                                        int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer4, 0);
                                        CompositionLocalMap currentCompositionLocalMap = composer4.getCurrentCompositionLocalMap();
                                        Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composer4, modifierAlpha);
                                        Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
                                        ComposerKt.sourceInformationMarkerStart(composer4, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                                        if (!(composer4.getApplier() instanceof Applier)) {
                                            ComposablesKt.invalidApplier();
                                        }
                                        composer4.startReusableNode();
                                        if (composer4.getInserting()) {
                                            composer4.createNode(constructor);
                                        } else {
                                            composer4.useNode();
                                        }
                                        Composer composerM4013constructorimpl = Updater.m4013constructorimpl(composer4);
                                        Updater.m4020setimpl(composerM4013constructorimpl, measurePolicyMaybeCachedBoxMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                                        Updater.m4020setimpl(composerM4013constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                                        Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                                        if (composerM4013constructorimpl.getInserting() || !Intrinsics.areEqual(composerM4013constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                                            composerM4013constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                                            composerM4013constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                                        }
                                        Updater.m4020setimpl(composerM4013constructorimpl, modifierMaterializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
                                        ComposerKt.sourceInformationMarkerStart(composer4, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
                                        BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                                        ComposerKt.sourceInformationMarkerStart(composer4, 717976197, "C142@5887L25,143@5975L10,141@5825L247:TextFieldImpl.kt#jmzs0o");
                                        TextFieldImplKt.m2010DecorationeuL9pac(textFieldColors3.placeholderColor(z9, composer4, 0).getValue().m4578unboximpl(), MaterialTheme.INSTANCE.getTypography(composer4, 6).getSubtitle1(), null, function212, composer4, 0, 4);
                                        ComposerKt.sourceInformationMarkerEnd(composer4);
                                        ComposerKt.sourceInformationMarkerEnd(composer4);
                                        composer4.endNode();
                                        ComposerKt.sourceInformationMarkerEnd(composer4);
                                        ComposerKt.sourceInformationMarkerEnd(composer4);
                                        ComposerKt.sourceInformationMarkerEnd(composer4);
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventEnd();
                                            return;
                                        }
                                        return;
                                    }
                                    composer4.skipToGroupEnd();
                                }
                            }, composer3, 54);
                            composer3.endReplaceGroup();
                            composableLambda2 = composableLambdaRememberComposableLambda2;
                        } else {
                            composer3.startReplaceGroup(-1571160716);
                            composer3.endReplaceGroup();
                            composableLambda2 = null;
                        }
                        final long jM4578unboximpl = textFieldColors.leadingIconColor(z2, z3, interactionSource, composer3, 0).getValue().m4578unboximpl();
                        final Function2<Composer, Integer, Unit> function212 = function24;
                        if (function212 == null) {
                            composer3.startReplaceGroup(-1570983241);
                            composer3.endReplaceGroup();
                            composableLambda3 = null;
                        } else {
                            composer3.startReplaceGroup(-1570983240);
                            ComposerKt.sourceInformation(composer3, "*152@6337L89");
                            ComposableLambda composableLambdaRememberComposableLambda3 = ComposableLambdaKt.rememberComposableLambda(-1165144581, z6, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.TextFieldImplKt$CommonDecorationBox$3$decoratedLeading$1$1
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

                                public final void invoke(Composer composer4, int i10) {
                                    ComposerKt.sourceInformation(composer4, "C153@6355L57:TextFieldImpl.kt#jmzs0o");
                                    if ((i10 & 3) == 2 && composer4.getSkipping()) {
                                        composer4.skipToGroupEnd();
                                        return;
                                    }
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(-1165144581, i10, -1, "androidx.compose.material.CommonDecorationBox.<anonymous>.<anonymous>.<anonymous> (TextFieldImpl.kt:153)");
                                    }
                                    TextFieldImplKt.m2010DecorationeuL9pac(jM4578unboximpl, null, null, function212, composer4, 0, 6);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                    }
                                }
                            }, composer3, 54);
                            composer3.endReplaceGroup();
                            composableLambda3 = composableLambdaRememberComposableLambda3;
                        }
                        final long jM4578unboximpl2 = textFieldColors.trailingIconColor(z2, z3, interactionSource, composer3, 0).getValue().m4578unboximpl();
                        final Function2<Composer, Integer, Unit> function213 = function25;
                        if (function213 == null) {
                            composer3.startReplaceGroup(-1570681642);
                            composer3.endReplaceGroup();
                            composableLambda4 = null;
                        } else {
                            composer3.startReplaceGroup(-1570681641);
                            ComposerKt.sourceInformation(composer3, "*159@6641L90");
                            ComposableLambda composableLambdaRememberComposableLambda4 = ComposableLambdaKt.rememberComposableLambda(1694126319, z6, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.TextFieldImplKt$CommonDecorationBox$3$decoratedTrailing$1$1
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

                                public final void invoke(Composer composer4, int i10) {
                                    ComposerKt.sourceInformation(composer4, "C160@6659L58:TextFieldImpl.kt#jmzs0o");
                                    if ((i10 & 3) == 2 && composer4.getSkipping()) {
                                        composer4.skipToGroupEnd();
                                        return;
                                    }
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(1694126319, i10, -1, "androidx.compose.material.CommonDecorationBox.<anonymous>.<anonymous>.<anonymous> (TextFieldImpl.kt:160)");
                                    }
                                    TextFieldImplKt.m2010DecorationeuL9pac(jM4578unboximpl2, null, null, function213, composer4, 0, 6);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                    }
                                }
                            }, composer3, 54);
                            composer3.endReplaceGroup();
                            composableLambda4 = composableLambdaRememberComposableLambda4;
                        }
                        Modifier modifierM519backgroundbw27NRU = BackgroundKt.m519backgroundbw27NRU(Modifier.INSTANCE, textFieldColors.backgroundColor(z2, composer3, 0).getValue().m4578unboximpl(), shape);
                        int i10 = WhenMappings.$EnumSwitchMapping$0[textFieldType3.ordinal()];
                        if (i10 == z6) {
                            composer3.startReplaceGroup(-1570370153);
                            ComposerKt.sourceInformation(composer3, "169@6931L482");
                            TextFieldKt.TextFieldLayout(modifierM519backgroundbw27NRU, function28, composableLambda, composableLambda2, composableLambda3, composableLambda4, z, f, paddingValues, composer3, (i9 << 21) & 29360128);
                            composer3.endReplaceGroup();
                        } else if (i10 == 2) {
                            composer3.startReplaceGroup(-1569791817);
                            ComposerKt.sourceInformation(composer3, "183@7535L38,184@7631L286,201@8340L420,193@7935L991");
                            ComposerKt.sourceInformationMarkerStart(composer3, 780646319, "CC(remember):TextFieldImpl.kt#9igjgp");
                            Object objRememberedValue2 = composer3.rememberedValue();
                            if (objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                                objRememberedValue2 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(Size.m4384boximpl(Size.INSTANCE.m4405getZeroNHjbRc()), null, 2, null);
                                composer3.updateRememberedValue(objRememberedValue2);
                            }
                            final MutableState mutableState = (MutableState) objRememberedValue2;
                            ComposerKt.sourceInformationMarkerEnd(composer3);
                            final PaddingValues paddingValues2 = paddingValues;
                            final Function2<Composer, Integer, Unit> function214 = function26;
                            ComposableLambda composableLambdaRememberComposableLambda5 = ComposableLambdaKt.rememberComposableLambda(-1212965554, z6, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.TextFieldImplKt$CommonDecorationBox$3$drawBorder$1
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

                                public final void invoke(Composer composer4, int i11) {
                                    ComposerKt.sourceInformation(composer4, "C185@7653L246:TextFieldImpl.kt#jmzs0o");
                                    if ((i11 & 3) != 2 || !composer4.getSkipping()) {
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventStart(-1212965554, i11, -1, "androidx.compose.material.CommonDecorationBox.<anonymous>.<anonymous> (TextFieldImpl.kt:185)");
                                        }
                                        Modifier modifierM1899outlineCutout12SF9DM = OutlinedTextFieldKt.m1899outlineCutout12SF9DM(LayoutIdKt.layoutId(Modifier.INSTANCE, OutlinedTextFieldKt.BorderId), mutableState.getValue().getPackedValue(), paddingValues2);
                                        Function2<Composer, Integer, Unit> function215 = function214;
                                        ComposerKt.sourceInformationMarkerStart(composer4, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
                                        MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), true);
                                        ComposerKt.sourceInformationMarkerStart(composer4, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
                                        int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer4, 0);
                                        CompositionLocalMap currentCompositionLocalMap = composer4.getCurrentCompositionLocalMap();
                                        Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composer4, modifierM1899outlineCutout12SF9DM);
                                        Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
                                        ComposerKt.sourceInformationMarkerStart(composer4, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                                        if (!(composer4.getApplier() instanceof Applier)) {
                                            ComposablesKt.invalidApplier();
                                        }
                                        composer4.startReusableNode();
                                        if (composer4.getInserting()) {
                                            composer4.createNode(constructor);
                                        } else {
                                            composer4.useNode();
                                        }
                                        Composer composerM4013constructorimpl = Updater.m4013constructorimpl(composer4);
                                        Updater.m4020setimpl(composerM4013constructorimpl, measurePolicyMaybeCachedBoxMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                                        Updater.m4020setimpl(composerM4013constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                                        Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                                        if (composerM4013constructorimpl.getInserting() || !Intrinsics.areEqual(composerM4013constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                                            composerM4013constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                                            composerM4013constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                                        }
                                        Updater.m4020setimpl(composerM4013constructorimpl, modifierMaterializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
                                        ComposerKt.sourceInformationMarkerStart(composer4, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
                                        BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                                        ComposerKt.sourceInformationMarkerStart(composer4, 719988748, "C:TextFieldImpl.kt#jmzs0o");
                                        if (function215 == null) {
                                            composer4.startReplaceGroup(719996434);
                                        } else {
                                            composer4.startReplaceGroup(-392416305);
                                            ComposerKt.sourceInformation(composer4, "189@7869L8");
                                            function215.invoke(composer4, 0);
                                        }
                                        composer4.endReplaceGroup();
                                        ComposerKt.sourceInformationMarkerEnd(composer4);
                                        ComposerKt.sourceInformationMarkerEnd(composer4);
                                        composer4.endNode();
                                        ComposerKt.sourceInformationMarkerEnd(composer4);
                                        ComposerKt.sourceInformationMarkerEnd(composer4);
                                        ComposerKt.sourceInformationMarkerEnd(composer4);
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventEnd();
                                            return;
                                        }
                                        return;
                                    }
                                    composer4.skipToGroupEnd();
                                }
                            }, composer3, 54);
                            Function2<Composer, Integer, Unit> function215 = function28;
                            ComposableLambda composableLambda5 = composableLambda;
                            boolean z9 = z;
                            ComposerKt.sourceInformationMarkerStart(composer3, 780672461, "CC(remember):TextFieldImpl.kt#9igjgp");
                            if ((i9 & 14) != 4) {
                                z6 = false;
                            }
                            Object objRememberedValue3 = composer3.rememberedValue();
                            if (z6 || objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                                objRememberedValue3 = (Function1) new Function1<Size, Unit>() { // from class: androidx.compose.material.TextFieldImplKt$CommonDecorationBox$3$1$1
                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                    {
                                        super(1);
                                    }

                                    @Override // kotlin.jvm.functions.Function1
                                    public /* bridge */ /* synthetic */ Unit invoke(Size size) {
                                        m2012invokeuvyYCjk(size.getPackedValue());
                                        return Unit.INSTANCE;
                                    }

                                    /* JADX INFO: renamed from: invoke-uvyYCjk, reason: not valid java name */
                                    public final void m2012invokeuvyYCjk(long j7) {
                                        float fM4396getWidthimpl = Size.m4396getWidthimpl(j7) * f3;
                                        float fM4393getHeightimpl = Size.m4393getHeightimpl(j7) * f3;
                                        if (Size.m4396getWidthimpl(mutableState.getValue().getPackedValue()) == fM4396getWidthimpl && Size.m4393getHeightimpl(mutableState.getValue().getPackedValue()) == fM4393getHeightimpl) {
                                            return;
                                        }
                                        mutableState.setValue(Size.m4384boximpl(SizeKt.Size(fM4396getWidthimpl, fM4393getHeightimpl)));
                                    }
                                };
                                composer3.updateRememberedValue(objRememberedValue3);
                            }
                            ComposerKt.sourceInformationMarkerEnd(composer3);
                            OutlinedTextFieldKt.OutlinedTextFieldLayout(modifierM519backgroundbw27NRU, function215, composableLambda2, composableLambda5, composableLambda3, composableLambda4, z9, f3, (Function1) objRememberedValue3, composableLambdaRememberComposableLambda5, paddingValues, composer3, 805306368 | ((i9 << 21) & 29360128), 0);
                            composer3.endReplaceGroup();
                        } else {
                            composer3.startReplaceGroup(-1568365383);
                            composer3.endReplaceGroup();
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                            return;
                        }
                        return;
                    }
                    composer3.skipToGroupEnd();
                }
            }, composerStartRestartGroup, 54), composer2, 1769472);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composerStartRestartGroup.skipToGroupEnd();
            composer2 = composerStartRestartGroup;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.TextFieldImplKt.CommonDecorationBox.4
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
                    TextFieldImplKt.CommonDecorationBox(textFieldType, str, function2, visualTransformation, function22, function23, function24, function25, z, z2, z3, interactionSource, paddingValues, shape, textFieldColors, function26, composer3, RecomposeScopeImplKt.updateChangedFlags(i | 1), RecomposeScopeImplKt.updateChangedFlags(i2));
                }
            });
        }
    }

    /* JADX INFO: renamed from: Decoration-euL9pac, reason: not valid java name */
    public static final void m2010DecorationeuL9pac(final long j, TextStyle textStyle, Float f, final Function2<? super Composer, ? super Integer, Unit> function2, Composer composer, final int i, final int i2) {
        int i3;
        final Float f2;
        Composer composerStartRestartGroup = composer.startRestartGroup(-399493340);
        ComposerKt.sourceInformation(composerStartRestartGroup, "C(Decoration)P(2:c#ui.graphics.Color,3,1)230@9338L492:TextFieldImpl.kt#jmzs0o");
        if ((i2 & 1) != 0) {
            i3 = i | 6;
        } else if ((i & 6) == 0) {
            i3 = (composerStartRestartGroup.changed(j) ? 4 : 2) | i;
        } else {
            i3 = i;
        }
        int i4 = i2 & 2;
        if (i4 != 0) {
            i3 |= 48;
        } else if ((i & 48) == 0) {
            i3 |= composerStartRestartGroup.changed(textStyle) ? 32 : 16;
        }
        int i5 = i2 & 4;
        if (i5 != 0) {
            i3 |= 384;
        } else if ((i & 384) == 0) {
            i3 |= composerStartRestartGroup.changed(f) ? 256 : 128;
        }
        if ((i2 & 8) != 0) {
            i3 |= 3072;
        } else if ((i & 3072) == 0) {
            i3 |= composerStartRestartGroup.changedInstance(function2) ? 2048 : 1024;
        }
        if ((i3 & 1171) != 1170 || !composerStartRestartGroup.getSkipping()) {
            if (i4 != 0) {
                textStyle = null;
            }
            final Float f3 = i5 == 0 ? f : null;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-399493340, i3, -1, "androidx.compose.material.Decoration (TextFieldImpl.kt:229)");
            }
            ComposableLambda composableLambdaRememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(494684590, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.TextFieldImplKt$Decoration$colorAndEmphasis$1
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
                    ComposerKt.sourceInformation(composer2, "C231@9414L410,231@9348L476:TextFieldImpl.kt#jmzs0o");
                    if ((i6 & 3) != 2 || !composer2.getSkipping()) {
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(494684590, i6, -1, "androidx.compose.material.Decoration.<anonymous> (TextFieldImpl.kt:231)");
                        }
                        ProvidedValue<Color> providedValueProvides = ContentColorKt.getLocalContentColor().provides(Color.m4558boximpl(j));
                        final Float f4 = f3;
                        final Function2<Composer, Integer, Unit> function22 = function2;
                        final long j2 = j;
                        CompositionLocalKt.CompositionLocalProvider(providedValueProvides, ComposableLambdaKt.rememberComposableLambda(-1132188434, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.TextFieldImplKt$Decoration$colorAndEmphasis$1.1
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
                                ComposerKt.sourceInformation(composer3, "C:TextFieldImpl.kt#jmzs0o");
                                if ((i7 & 3) != 2 || !composer3.getSkipping()) {
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(-1132188434, i7, -1, "androidx.compose.material.Decoration.<anonymous>.<anonymous> (TextFieldImpl.kt:232)");
                                    }
                                    if (f4 != null) {
                                        composer3.startReplaceGroup(-1178229056);
                                        ComposerKt.sourceInformation(composer3, "233@9472L142");
                                        CompositionLocalKt.CompositionLocalProvider(ContentAlphaKt.getLocalContentAlpha().provides(f4), function22, composer3, ProvidedValue.$stable);
                                        composer3.endReplaceGroup();
                                    } else {
                                        composer3.startReplaceGroup(-1178050310);
                                        ComposerKt.sourceInformation(composer3, "238@9652L148");
                                        CompositionLocalKt.CompositionLocalProvider(ContentAlphaKt.getLocalContentAlpha().provides(Float.valueOf(Color.m4570getAlphaimpl(j2))), function22, composer3, ProvidedValue.$stable);
                                        composer3.endReplaceGroup();
                                    }
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                        return;
                                    }
                                    return;
                                }
                                composer3.skipToGroupEnd();
                            }
                        }, composer2, 54), composer2, ProvidedValue.$stable | 48);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                            return;
                        }
                        return;
                    }
                    composer2.skipToGroupEnd();
                }
            }, composerStartRestartGroup, 54);
            if (textStyle != null) {
                composerStartRestartGroup.startReplaceGroup(2115969060);
                ComposerKt.sourceInformation(composerStartRestartGroup, "245@9859L46");
                TextKt.ProvideTextStyle(textStyle, composableLambdaRememberComposableLambda, composerStartRestartGroup, ((i3 >> 3) & 14) | 48);
            } else {
                composerStartRestartGroup.startReplaceGroup(2115970696);
                ComposerKt.sourceInformation(composerStartRestartGroup, "245@9911L18");
                composableLambdaRememberComposableLambda.invoke(composerStartRestartGroup, 6);
            }
            composerStartRestartGroup.endReplaceGroup();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            f2 = f3;
        } else {
            composerStartRestartGroup.skipToGroupEnd();
            f2 = f;
        }
        final TextStyle textStyle2 = textStyle;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.TextFieldImplKt$Decoration$1
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
                    TextFieldImplKt.m2010DecorationeuL9pac(j, textStyle2, f2, function2, composer2, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                }
            });
        }
    }

    public static final Modifier defaultErrorSemantics(Modifier modifier, boolean z, final String str) {
        return z ? SemanticsModifierKt.semantics$default(modifier, false, new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material.TextFieldImplKt.defaultErrorSemantics.1
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
                SemanticsPropertiesKt.error(semanticsPropertyReceiver, str);
            }
        }, 1, null) : modifier;
    }

    public static final int widthOrZero(Placeable placeable) {
        if (placeable != null) {
            return placeable.getWidth();
        }
        return 0;
    }

    public static final int heightOrZero(Placeable placeable) {
        if (placeable != null) {
            return placeable.getHeight();
        }
        return 0;
    }

    public static final Object getLayoutId(IntrinsicMeasurable intrinsicMeasurable) {
        Object parentData = intrinsicMeasurable.getParentData();
        LayoutIdParentData layoutIdParentData = parentData instanceof LayoutIdParentData ? (LayoutIdParentData) parentData : null;
        if (layoutIdParentData != null) {
            return layoutIdParentData.getLayoutId();
        }
        return null;
    }

    static {
        float f = 48;
        IconDefaultSizeModifier = androidx.compose.foundation.layout.SizeKt.m994defaultMinSizeVpY3zN4(Modifier.INSTANCE, Dp.m7027constructorimpl(f), Dp.m7027constructorimpl(f));
    }

    public static final long getZeroConstraints() {
        return ZeroConstraints;
    }

    public static final float getTextFieldPadding() {
        return TextFieldPadding;
    }

    public static final float getHorizontalIconPadding() {
        return HorizontalIconPadding;
    }

    public static final Modifier getIconDefaultSizeModifier() {
        return IconDefaultSizeModifier;
    }
}
