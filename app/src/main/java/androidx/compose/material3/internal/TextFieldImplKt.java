package androidx.compose.material3.internal;

import androidx.compose.animation.SingleValueAnimationKt;
import androidx.compose.animation.core.AnimateAsStateKt;
import androidx.compose.animation.core.AnimationSpecKt;
import androidx.compose.foundation.BorderStroke;
import androidx.compose.foundation.BorderStrokeKt;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.material3.ContentColorKt;
import androidx.compose.material3.TextFieldColors;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalKt;
import androidx.compose.runtime.ProvidedValue;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.draw.CacheDrawScope;
import androidx.compose.ui.draw.DrawModifierKt;
import androidx.compose.ui.draw.DrawResult;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.ColorProducer;
import androidx.compose.ui.graphics.Outline;
import androidx.compose.ui.graphics.OutlineKt;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.layout.IntrinsicMeasurable;
import androidx.compose.ui.layout.LayoutIdParentData;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.semantics.SemanticsModifierKt;
import androidx.compose.ui.semantics.SemanticsPropertiesKt;
import androidx.compose.ui.semantics.SemanticsPropertyReceiver;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.unit.ConstraintsKt;
import androidx.compose.ui.unit.Dp;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.savedstate.serialization.ClassDiscriminatorModeKt;
import com.google.firebase.analytics.FirebaseAnalytics;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;

/* JADX INFO: compiled from: TextFieldImpl.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000º\u0001\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\b\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a \u0002\u0010-\u001a\u00020.2\u0006\u0010/\u001a\u0002002\u0006\u00101\u001a\u00020\u00012\u0011\u00102\u001a\r\u0012\u0004\u0012\u00020.03¢\u0006\u0002\b42\u0006\u00105\u001a\u0002062\u0013\u00107\u001a\u000f\u0012\u0004\u0012\u00020.\u0018\u000103¢\u0006\u0002\b42\u0015\b\u0002\u00108\u001a\u000f\u0012\u0004\u0012\u00020.\u0018\u000103¢\u0006\u0002\b42\u0015\b\u0002\u00109\u001a\u000f\u0012\u0004\u0012\u00020.\u0018\u000103¢\u0006\u0002\b42\u0015\b\u0002\u0010:\u001a\u000f\u0012\u0004\u0012\u00020.\u0018\u000103¢\u0006\u0002\b42\u0015\b\u0002\u0010;\u001a\u000f\u0012\u0004\u0012\u00020.\u0018\u000103¢\u0006\u0002\b42\u0015\b\u0002\u0010<\u001a\u000f\u0012\u0004\u0012\u00020.\u0018\u000103¢\u0006\u0002\b42\u0015\b\u0002\u0010=\u001a\u000f\u0012\u0004\u0012\u00020.\u0018\u000103¢\u0006\u0002\b42\b\b\u0002\u0010>\u001a\u00020?2\b\b\u0002\u0010@\u001a\u00020?2\b\b\u0002\u0010A\u001a\u00020?2\u0006\u0010B\u001a\u00020C2\u0006\u0010D\u001a\u00020E2\u0006\u0010F\u001a\u00020G2\u0011\u0010H\u001a\r\u0012\u0004\u0012\u00020.03¢\u0006\u0002\b4H\u0001¢\u0006\u0002\u0010I\u001a-\u0010J\u001a\u00020.2\u0006\u0010K\u001a\u00020L2\u0011\u0010M\u001a\r\u0012\u0004\u0012\u00020.03¢\u0006\u0002\b4H\u0003ø\u0001\u0000¢\u0006\u0004\bN\u0010O\u001a5\u0010J\u001a\u00020.2\u0006\u0010K\u001a\u00020L2\u0006\u0010P\u001a\u00020Q2\u0011\u0010M\u001a\r\u0012\u0004\u0012\u00020.03¢\u0006\u0002\b4H\u0003ø\u0001\u0000¢\u0006\u0004\bR\u0010S\u001a×\u0001\u0010T\u001a\u00020.2\u0006\u0010U\u001a\u00020V2\u0006\u0010W\u001a\u00020L2\u0006\u0010X\u001a\u00020L2\u0006\u0010Y\u001a\u00020L2\u0006\u0010Z\u001a\u00020?2\u0099\u0001\u0010M\u001a\u0094\u0001\u0012\u0019\u0012\u0017\u0012\u0004\u0012\u00020]0\\¢\u0006\f\b^\u0012\b\b_\u0012\u0004\b\b(`\u0012\u0019\u0012\u0017\u0012\u0004\u0012\u00020L0\\¢\u0006\f\b^\u0012\b\b_\u0012\u0004\b\b(a\u0012\u0019\u0012\u0017\u0012\u0004\u0012\u00020L0\\¢\u0006\f\b^\u0012\b\b_\u0012\u0004\b\b(b\u0012\u0019\u0012\u0017\u0012\u0004\u0012\u00020]0\\¢\u0006\f\b^\u0012\b\b_\u0012\u0004\b\b(c\u0012\u0019\u0012\u0017\u0012\u0004\u0012\u00020]0\\¢\u0006\f\b^\u0012\b\b_\u0012\u0004\b\b(d\u0012\u0004\u0012\u00020.0[¢\u0006\u0002\b4H\u0083\bø\u0001\u0000¢\u0006\u0004\be\u0010f\u001aH\u0010g\u001a\b\u0012\u0004\u0012\u00020h0\\2\u0006\u0010@\u001a\u00020?2\u0006\u0010A\u001a\u00020?2\u0006\u0010i\u001a\u00020?2\u0006\u0010F\u001a\u00020G2\u0006\u0010j\u001a\u00020\u00032\u0006\u0010k\u001a\u00020\u0003H\u0001ø\u0001\u0000¢\u0006\u0004\bl\u0010m\u001a\u0012\u0010n\u001a\u00020\u00142\b\u0010o\u001a\u0004\u0018\u00010pH\u0000\u001a\u0012\u0010q\u001a\u00020\u00142\b\u0010o\u001a\u0004\u0018\u00010pH\u0000\u001a\u001c\u0010r\u001a\u00020\b*\u00020\b2\u0006\u0010A\u001a\u00020?2\u0006\u0010s\u001a\u00020\u0001H\u0000\u001a\u001c\u0010t\u001a\u00020\b*\u00020\b2\u0006\u0010u\u001a\u00020v2\u0006\u0010w\u001a\u00020xH\u0000\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0080T¢\u0006\u0002\n\u0000\"\u0016\u0010\u0002\u001a\u00020\u0003X\u0080\u0004¢\u0006\n\n\u0002\u0010\u0006\u001a\u0004\b\u0004\u0010\u0005\"\u0014\u0010\u0007\u001a\u00020\bX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n\"\u000e\u0010\u000b\u001a\u00020\u0001X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010\f\u001a\u00020\u0001X\u0080T¢\u0006\u0002\n\u0000\"\u0016\u0010\r\u001a\u00020\u0003X\u0080\u0004¢\u0006\n\n\u0002\u0010\u0006\u001a\u0004\b\u000e\u0010\u0005\"\u0016\u0010\u000f\u001a\u00020\u0003X\u0080\u0004¢\u0006\n\n\u0002\u0010\u0006\u001a\u0004\b\u0010\u0010\u0005\"\u0016\u0010\u0011\u001a\u00020\u0003X\u0080\u0004¢\u0006\n\n\u0002\u0010\u0006\u001a\u0004\b\u0012\u0010\u0005\"\u000e\u0010\u0013\u001a\u00020\u0014X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0015\u001a\u00020\u0014X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0016\u001a\u00020\u0001X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0017\u001a\u00020\u0001X\u0080T¢\u0006\u0002\n\u0000\"\u0016\u0010\u0018\u001a\u00020\u0003X\u0080\u0004¢\u0006\n\n\u0002\u0010\u0006\u001a\u0004\b\u0019\u0010\u0005\"\u000e\u0010\u001a\u001a\u00020\u0001X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010\u001b\u001a\u00020\u0001X\u0080T¢\u0006\u0002\n\u0000\"\u0016\u0010\u001c\u001a\u00020\u0003X\u0080\u0004¢\u0006\n\n\u0002\u0010\u0006\u001a\u0004\b\u001d\u0010\u0005\"\u000e\u0010\u001e\u001a\u00020\u0014X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010\u001f\u001a\u00020\u0001X\u0080T¢\u0006\u0002\n\u0000\"\u0016\u0010 \u001a\u00020\u0003X\u0080\u0004¢\u0006\n\n\u0002\u0010\u0006\u001a\u0004\b!\u0010\u0005\"\u000e\u0010\"\u001a\u00020\u0001X\u0080T¢\u0006\u0002\n\u0000\"\u0016\u0010#\u001a\u00020$X\u0080\u0004¢\u0006\n\n\u0002\u0010'\u001a\u0004\b%\u0010&\"\u001a\u0010(\u001a\u0004\u0018\u00010)*\u00020*8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b+\u0010,\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006y²\u0006\n\u0010z\u001a\u00020?X\u008a\u0084\u0002²\u0006\n\u0010{\u001a\u00020?X\u008a\u0084\u0002"}, d2 = {"ContainerId", "", "HorizontalIconPadding", "Landroidx/compose/ui/unit/Dp;", "getHorizontalIconPadding", "()F", "F", "IconDefaultSizeModifier", "Landroidx/compose/ui/Modifier;", "getIconDefaultSizeModifier", "()Landroidx/compose/ui/Modifier;", "LabelId", "LeadingId", "MinFocusedLabelLineHeight", "getMinFocusedLabelLineHeight", "MinSupportingTextLineHeight", "getMinSupportingTextLineHeight", "MinTextLineHeight", "getMinTextLineHeight", "PlaceholderAnimationDelayOrDuration", "", "PlaceholderAnimationDuration", "PlaceholderId", "PrefixId", "PrefixSuffixTextPadding", "getPrefixSuffixTextPadding", "SuffixId", "SupportingId", "SupportingTopPadding", "getSupportingTopPadding", "TextFieldAnimationDuration", "TextFieldId", "TextFieldPadding", "getTextFieldPadding", "TrailingId", "ZeroConstraints", "Landroidx/compose/ui/unit/Constraints;", "getZeroConstraints", "()J", "J", "layoutId", "", "Landroidx/compose/ui/layout/IntrinsicMeasurable;", "getLayoutId", "(Landroidx/compose/ui/layout/IntrinsicMeasurable;)Ljava/lang/Object;", "CommonDecorationBox", "", ClassDiscriminatorModeKt.CLASS_DISCRIMINATOR_KEY, "Landroidx/compose/material3/internal/TextFieldType;", "value", "innerTextField", "Lkotlin/Function0;", "Landroidx/compose/runtime/Composable;", "visualTransformation", "Landroidx/compose/ui/text/input/VisualTransformation;", "label", "placeholder", "leadingIcon", "trailingIcon", "prefix", "suffix", "supportingText", "singleLine", "", "enabled", "isError", "interactionSource", "Landroidx/compose/foundation/interaction/InteractionSource;", "contentPadding", "Landroidx/compose/foundation/layout/PaddingValues;", "colors", "Landroidx/compose/material3/TextFieldColors;", "container", "(Landroidx/compose/material3/internal/TextFieldType;Ljava/lang/String;Lkotlin/jvm/functions/Function2;Landroidx/compose/ui/text/input/VisualTransformation;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;ZZZLandroidx/compose/foundation/interaction/InteractionSource;Landroidx/compose/foundation/layout/PaddingValues;Landroidx/compose/material3/TextFieldColors;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;III)V", "Decoration", "contentColor", "Landroidx/compose/ui/graphics/Color;", FirebaseAnalytics.Param.CONTENT, "Decoration-Iv8Zu3U", "(JLkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;I)V", "textStyle", "Landroidx/compose/ui/text/TextStyle;", "Decoration-3J-VO9M", "(JLandroidx/compose/ui/text/TextStyle;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;I)V", "TextFieldTransitionScope", "inputState", "Landroidx/compose/material3/internal/InputPhase;", "focusedLabelTextStyleColor", "unfocusedLabelTextStyleColor", "labelColor", "showLabel", "Lkotlin/Function5;", "Landroidx/compose/runtime/State;", "", "Lkotlin/ParameterName;", "name", "labelProgress", "labelTextStyleColor", "labelContentColor", "placeholderOpacity", "prefixSuffixOpacity", "TextFieldTransitionScope-Jy8F4Js", "(Landroidx/compose/material3/internal/InputPhase;JJJZLkotlin/jvm/functions/Function7;Landroidx/compose/runtime/Composer;I)V", "animateBorderStrokeAsState", "Landroidx/compose/foundation/BorderStroke;", "focused", "focusedBorderThickness", "unfocusedBorderThickness", "animateBorderStrokeAsState-NuRrP5Q", "(ZZZLandroidx/compose/material3/TextFieldColors;FFLandroidx/compose/runtime/Composer;I)Landroidx/compose/runtime/State;", "heightOrZero", "placeable", "Landroidx/compose/ui/layout/Placeable;", "widthOrZero", "defaultErrorSemantics", "defaultErrorMessage", "textFieldBackground", TypedValues.Custom.S_COLOR, "Landroidx/compose/ui/graphics/ColorProducer;", "shape", "Landroidx/compose/ui/graphics/Shape;", "material3_release", "showPlaceholder", "showPrefixSuffix"}, k = 2, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
public final class TextFieldImplKt {
    public static final String ContainerId = "Container";
    private static final Modifier IconDefaultSizeModifier;
    public static final String LabelId = "Label";
    public static final String LeadingId = "Leading";
    private static final float MinFocusedLabelLineHeight;
    private static final float MinSupportingTextLineHeight;
    private static final int PlaceholderAnimationDelayOrDuration = 67;
    private static final int PlaceholderAnimationDuration = 83;
    public static final String PlaceholderId = "Hint";
    public static final String PrefixId = "Prefix";
    public static final String SuffixId = "Suffix";
    public static final String SupportingId = "Supporting";
    public static final int TextFieldAnimationDuration = 150;
    public static final String TextFieldId = "TextField";
    private static final float TextFieldPadding;
    public static final String TrailingId = "Trailing";
    private static final long ZeroConstraints = ConstraintsKt.Constraints(0, 0, 0, 0);
    private static final float HorizontalIconPadding = Dp.m7027constructorimpl(12);
    private static final float SupportingTopPadding = Dp.m7027constructorimpl(4);
    private static final float PrefixSuffixTextPadding = Dp.m7027constructorimpl(2);
    private static final float MinTextLineHeight = Dp.m7027constructorimpl(24);

    /* JADX INFO: compiled from: TextFieldImpl.kt */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;

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
            int[] iArr2 = new int[InputPhase.values().length];
            try {
                iArr2[InputPhase.Focused.ordinal()] = 1;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr2[InputPhase.UnfocusedEmpty.ordinal()] = 2;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                iArr2[InputPhase.UnfocusedNotEmpty.ordinal()] = 3;
            } catch (NoSuchFieldError unused5) {
            }
            $EnumSwitchMapping$1 = iArr2;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:100:0x0130  */
    /* JADX WARN: Removed duplicated region for block: B:110:0x014e  */
    /* JADX WARN: Removed duplicated region for block: B:111:0x0157  */
    /* JADX WARN: Removed duplicated region for block: B:121:0x0177  */
    /* JADX WARN: Removed duplicated region for block: B:122:0x017c  */
    /* JADX WARN: Removed duplicated region for block: B:132:0x019c  */
    /* JADX WARN: Removed duplicated region for block: B:133:0x019f  */
    /* JADX WARN: Removed duplicated region for block: B:144:0x01bf  */
    /* JADX WARN: Removed duplicated region for block: B:145:0x01c2  */
    /* JADX WARN: Removed duplicated region for block: B:156:0x01e2  */
    /* JADX WARN: Removed duplicated region for block: B:157:0x01e5  */
    /* JADX WARN: Removed duplicated region for block: B:166:0x01fb  */
    /* JADX WARN: Removed duplicated region for block: B:168:0x01ff  */
    /* JADX WARN: Removed duplicated region for block: B:176:0x0215  */
    /* JADX WARN: Removed duplicated region for block: B:178:0x0219  */
    /* JADX WARN: Removed duplicated region for block: B:186:0x022f  */
    /* JADX WARN: Removed duplicated region for block: B:188:0x0233  */
    /* JADX WARN: Removed duplicated region for block: B:196:0x024d  */
    /* JADX WARN: Removed duplicated region for block: B:202:0x0275  */
    /* JADX WARN: Removed duplicated region for block: B:203:0x0277  */
    /* JADX WARN: Removed duplicated region for block: B:205:0x027a  */
    /* JADX WARN: Removed duplicated region for block: B:206:0x027c  */
    /* JADX WARN: Removed duplicated region for block: B:208:0x0280  */
    /* JADX WARN: Removed duplicated region for block: B:209:0x0282  */
    /* JADX WARN: Removed duplicated region for block: B:211:0x0286  */
    /* JADX WARN: Removed duplicated region for block: B:212:0x0289  */
    /* JADX WARN: Removed duplicated region for block: B:214:0x028d  */
    /* JADX WARN: Removed duplicated region for block: B:215:0x0290  */
    /* JADX WARN: Removed duplicated region for block: B:217:0x0294  */
    /* JADX WARN: Removed duplicated region for block: B:218:0x0296  */
    /* JADX WARN: Removed duplicated region for block: B:220:0x029a  */
    /* JADX WARN: Removed duplicated region for block: B:221:0x029d  */
    /* JADX WARN: Removed duplicated region for block: B:223:0x02a1  */
    /* JADX WARN: Removed duplicated region for block: B:224:0x02a3  */
    /* JADX WARN: Removed duplicated region for block: B:226:0x02a7  */
    /* JADX WARN: Removed duplicated region for block: B:227:0x02a9  */
    /* JADX WARN: Removed duplicated region for block: B:230:0x02b1  */
    /* JADX WARN: Removed duplicated region for block: B:231:0x02bc  */
    /* JADX WARN: Removed duplicated region for block: B:234:0x02ce  */
    /* JADX WARN: Removed duplicated region for block: B:235:0x02d0  */
    /* JADX WARN: Removed duplicated region for block: B:238:0x02d9  */
    /* JADX WARN: Removed duplicated region for block: B:239:0x02db  */
    /* JADX WARN: Removed duplicated region for block: B:244:0x02ec  */
    /* JADX WARN: Removed duplicated region for block: B:247:0x032c  */
    /* JADX WARN: Removed duplicated region for block: B:249:0x0333  */
    /* JADX WARN: Removed duplicated region for block: B:265:0x03a5  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x005e  */
    /* JADX WARN: Removed duplicated region for block: B:271:0x03b4  */
    /* JADX WARN: Removed duplicated region for block: B:276:0x03bf  */
    /* JADX WARN: Removed duplicated region for block: B:277:0x03c1  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0061  */
    /* JADX WARN: Removed duplicated region for block: B:280:0x0411  */
    /* JADX WARN: Removed duplicated region for block: B:281:0x041d  */
    /* JADX WARN: Removed duplicated region for block: B:284:0x042d  */
    /* JADX WARN: Removed duplicated region for block: B:291:0x043b  */
    /* JADX WARN: Removed duplicated region for block: B:294:0x0443  */
    /* JADX WARN: Removed duplicated region for block: B:297:0x0462  */
    /* JADX WARN: Removed duplicated region for block: B:298:0x0470  */
    /* JADX WARN: Removed duplicated region for block: B:301:0x047f  */
    /* JADX WARN: Removed duplicated region for block: B:309:0x048e  */
    /* JADX WARN: Removed duplicated region for block: B:312:0x0496  */
    /* JADX WARN: Removed duplicated region for block: B:315:0x04fd  */
    /* JADX WARN: Removed duplicated region for block: B:316:0x050e  */
    /* JADX WARN: Removed duplicated region for block: B:319:0x051f  */
    /* JADX WARN: Removed duplicated region for block: B:328:0x0530  */
    /* JADX WARN: Removed duplicated region for block: B:331:0x0538  */
    /* JADX WARN: Removed duplicated region for block: B:334:0x0557  */
    /* JADX WARN: Removed duplicated region for block: B:335:0x0566  */
    /* JADX WARN: Removed duplicated region for block: B:338:0x0575  */
    /* JADX WARN: Removed duplicated region for block: B:347:0x0586  */
    /* JADX WARN: Removed duplicated region for block: B:350:0x058e  */
    /* JADX WARN: Removed duplicated region for block: B:353:0x05ef  */
    /* JADX WARN: Removed duplicated region for block: B:354:0x05fe  */
    /* JADX WARN: Removed duplicated region for block: B:357:0x060d  */
    /* JADX WARN: Removed duplicated region for block: B:366:0x061e  */
    /* JADX WARN: Removed duplicated region for block: B:369:0x0626  */
    /* JADX WARN: Removed duplicated region for block: B:372:0x0645  */
    /* JADX WARN: Removed duplicated region for block: B:373:0x0654  */
    /* JADX WARN: Removed duplicated region for block: B:376:0x0663  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x007b  */
    /* JADX WARN: Removed duplicated region for block: B:385:0x0674  */
    /* JADX WARN: Removed duplicated region for block: B:388:0x067c  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x007e  */
    /* JADX WARN: Removed duplicated region for block: B:391:0x06d1  */
    /* JADX WARN: Removed duplicated region for block: B:392:0x06df  */
    /* JADX WARN: Removed duplicated region for block: B:395:0x06f0  */
    /* JADX WARN: Removed duplicated region for block: B:396:0x06f3  */
    /* JADX WARN: Removed duplicated region for block: B:399:0x06fb  */
    /* JADX WARN: Removed duplicated region for block: B:402:0x0717  */
    /* JADX WARN: Removed duplicated region for block: B:404:0x071f  */
    /* JADX WARN: Removed duplicated region for block: B:407:0x074c  */
    /* JADX WARN: Removed duplicated region for block: B:408:0x0758  */
    /* JADX WARN: Removed duplicated region for block: B:411:0x0767  */
    /* JADX WARN: Removed duplicated region for block: B:412:0x076a  */
    /* JADX WARN: Removed duplicated region for block: B:415:0x0772  */
    /* JADX WARN: Removed duplicated region for block: B:418:0x078e  */
    /* JADX WARN: Removed duplicated region for block: B:419:0x079a  */
    /* JADX WARN: Removed duplicated region for block: B:422:0x07a9  */
    /* JADX WARN: Removed duplicated region for block: B:423:0x07ac  */
    /* JADX WARN: Removed duplicated region for block: B:426:0x07b4  */
    /* JADX WARN: Removed duplicated region for block: B:429:0x080b  */
    /* JADX WARN: Removed duplicated region for block: B:430:0x0817  */
    /* JADX WARN: Removed duplicated region for block: B:433:0x0821  */
    /* JADX WARN: Removed duplicated region for block: B:438:0x0845  */
    /* JADX WARN: Removed duplicated region for block: B:441:0x0872  */
    /* JADX WARN: Removed duplicated region for block: B:444:0x087f  */
    /* JADX WARN: Removed duplicated region for block: B:447:0x089b  */
    /* JADX WARN: Removed duplicated region for block: B:448:0x08a3  */
    /* JADX WARN: Removed duplicated region for block: B:451:0x08aa  */
    /* JADX WARN: Removed duplicated region for block: B:454:0x08ff  */
    /* JADX WARN: Removed duplicated region for block: B:455:0x0906  */
    /* JADX WARN: Removed duplicated region for block: B:458:0x094a  */
    /* JADX WARN: Removed duplicated region for block: B:466:0x09a4  */
    /* JADX WARN: Removed duplicated region for block: B:469:0x09c0  */
    /* JADX WARN: Removed duplicated region for block: B:475:0x0a11  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x0092  */
    /* JADX WARN: Removed duplicated region for block: B:481:0x0a56  */
    /* JADX WARN: Removed duplicated region for block: B:484:0x0a70  */
    /* JADX WARN: Removed duplicated region for block: B:485:0x0a72  */
    /* JADX WARN: Removed duplicated region for block: B:488:0x0a97  */
    /* JADX WARN: Removed duplicated region for block: B:489:0x0a9c  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x0095  */
    /* JADX WARN: Removed duplicated region for block: B:492:0x0ac5  */
    /* JADX WARN: Removed duplicated region for block: B:493:0x0acb  */
    /* JADX WARN: Removed duplicated region for block: B:496:0x0aed  */
    /* JADX WARN: Removed duplicated region for block: B:508:0x0ba9  */
    /* JADX WARN: Removed duplicated region for block: B:511:0x0c0a  */
    /* JADX WARN: Removed duplicated region for block: B:515:0x0c1f  */
    /* JADX WARN: Removed duplicated region for block: B:517:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:57:0x00ab  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x00b0  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x00c9  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x00ce  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x00e7  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x00ea  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x010a  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x010d  */
    /* JADX WARN: Removed duplicated region for block: B:99:0x012d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final void CommonDecorationBox(final androidx.compose.material3.internal.TextFieldType r44, final java.lang.String r45, final kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r46, final androidx.compose.ui.text.input.VisualTransformation r47, final kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r48, kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r49, kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r50, kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r51, kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r52, kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r53, kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r54, boolean r55, boolean r56, boolean r57, final androidx.compose.foundation.interaction.InteractionSource r58, final androidx.compose.foundation.layout.PaddingValues r59, final androidx.compose.material3.TextFieldColors r60, kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r61, androidx.compose.runtime.Composer r62, final int r63, final int r64, final int r65) {
        /*
            Method dump skipped, instruction units count: 3143
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.internal.TextFieldImplKt.CommonDecorationBox(androidx.compose.material3.internal.TextFieldType, java.lang.String, kotlin.jvm.functions.Function2, androidx.compose.ui.text.input.VisualTransformation, kotlin.jvm.functions.Function2, kotlin.jvm.functions.Function2, kotlin.jvm.functions.Function2, kotlin.jvm.functions.Function2, kotlin.jvm.functions.Function2, kotlin.jvm.functions.Function2, kotlin.jvm.functions.Function2, boolean, boolean, boolean, androidx.compose.foundation.interaction.InteractionSource, androidx.compose.foundation.layout.PaddingValues, androidx.compose.material3.TextFieldColors, kotlin.jvm.functions.Function2, androidx.compose.runtime.Composer, int, int, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: Decoration-3J-VO9M, reason: not valid java name */
    public static final void m3280Decoration3JVO9M(long j, TextStyle textStyle, Function2<? super Composer, ? super Integer, Unit> function2, Composer composer, final int i) {
        int i2;
        final long j2;
        final TextStyle textStyle2;
        final Function2<? super Composer, ? super Integer, Unit> function22;
        Composer composerStartRestartGroup = composer.startRestartGroup(1208685580);
        ComposerKt.sourceInformation(composerStartRestartGroup, "C(Decoration)P(1:c#ui.graphics.Color,2)298@12599L62:TextFieldImpl.kt#mqatfk");
        if ((i & 6) == 0) {
            i2 = (composerStartRestartGroup.changed(j) ? 4 : 2) | i;
        } else {
            i2 = i;
        }
        if ((i & 48) == 0) {
            i2 |= composerStartRestartGroup.changed(textStyle) ? 32 : 16;
        }
        if ((i & 384) == 0) {
            i2 |= composerStartRestartGroup.changedInstance(function2) ? 256 : 128;
        }
        if ((i2 & 147) == 146 && composerStartRestartGroup.getSkipping()) {
            composerStartRestartGroup.skipToGroupEnd();
            function22 = function2;
            textStyle2 = textStyle;
            j2 = j;
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1208685580, i2, -1, "androidx.compose.material3.internal.Decoration (TextFieldImpl.kt:298)");
            }
            ProvideContentColorTextStyleKt.m3206ProvideContentColorTextStyle3JVO9M(j, textStyle, function2, composerStartRestartGroup, i2 & 1022);
            j2 = j;
            textStyle2 = textStyle;
            function22 = function2;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.internal.TextFieldImplKt$Decoration$1
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
                    TextFieldImplKt.m3280Decoration3JVO9M(j2, textStyle2, function22, composer2, RecomposeScopeImplKt.updateChangedFlags(i | 1));
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: Decoration-Iv8Zu3U, reason: not valid java name */
    public static final void m3281DecorationIv8Zu3U(final long j, final Function2<? super Composer, ? super Integer, Unit> function2, Composer composer, final int i) {
        int i2;
        Composer composerStartRestartGroup = composer.startRestartGroup(660142980);
        ComposerKt.sourceInformation(composerStartRestartGroup, "C(Decoration)P(1:c#ui.graphics.Color)303@12806L84:TextFieldImpl.kt#mqatfk");
        if ((i & 6) == 0) {
            i2 = (composerStartRestartGroup.changed(j) ? 4 : 2) | i;
        } else {
            i2 = i;
        }
        if ((i & 48) == 0) {
            i2 |= composerStartRestartGroup.changedInstance(function2) ? 32 : 16;
        }
        if ((i2 & 19) == 18 && composerStartRestartGroup.getSkipping()) {
            composerStartRestartGroup.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(660142980, i2, -1, "androidx.compose.material3.internal.Decoration (TextFieldImpl.kt:303)");
            }
            CompositionLocalKt.CompositionLocalProvider(ContentColorKt.getLocalContentColor().provides(Color.m4558boximpl(j)), function2, composerStartRestartGroup, (i2 & 112) | ProvidedValue.$stable);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.internal.TextFieldImplKt$Decoration$2
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
                    TextFieldImplKt.m3281DecorationIv8Zu3U(j, function2, composer2, RecomposeScopeImplKt.updateChangedFlags(i | 1));
                }
            });
        }
    }

    public static final Modifier defaultErrorSemantics(Modifier modifier, boolean z, final String str) {
        return z ? SemanticsModifierKt.semantics$default(modifier, false, new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.internal.TextFieldImplKt.defaultErrorSemantics.1
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

    public static final Modifier textFieldBackground(Modifier modifier, final ColorProducer colorProducer, final Shape shape) {
        return DrawModifierKt.drawWithCache(modifier, new Function1<CacheDrawScope, DrawResult>() { // from class: androidx.compose.material3.internal.TextFieldImplKt.textFieldBackground.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final DrawResult invoke(CacheDrawScope cacheDrawScope) {
                final Outline outlineMo568createOutlinePq9zytI = shape.mo568createOutlinePq9zytI(cacheDrawScope.m4217getSizeNHjbRc(), cacheDrawScope.getLayoutDirection(), cacheDrawScope);
                final ColorProducer colorProducer2 = colorProducer;
                return cacheDrawScope.onDrawBehind(new Function1<DrawScope, Unit>() { // from class: androidx.compose.material3.internal.TextFieldImplKt.textFieldBackground.1.1
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
                        OutlineKt.m4827drawOutlinewDX37Ww$default(drawScope, outlineMo568createOutlinePq9zytI, colorProducer2.mo1820invoke0d7_KjU(), 0.0f, null, null, 0, 60, null);
                    }
                });
            }
        });
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

    /* JADX WARN: Removed duplicated region for block: B:101:0x0235  */
    /* JADX WARN: Removed duplicated region for block: B:104:0x027e  */
    /* JADX WARN: Removed duplicated region for block: B:107:0x028b  */
    /* JADX WARN: Removed duplicated region for block: B:108:0x028e  */
    /* JADX WARN: Removed duplicated region for block: B:111:0x0296  */
    /* JADX WARN: Removed duplicated region for block: B:114:0x02b2  */
    /* JADX WARN: Removed duplicated region for block: B:116:0x02ba  */
    /* JADX WARN: Removed duplicated region for block: B:119:0x02e8  */
    /* JADX WARN: Removed duplicated region for block: B:122:0x02f5  */
    /* JADX WARN: Removed duplicated region for block: B:123:0x02f8  */
    /* JADX WARN: Removed duplicated region for block: B:126:0x0300  */
    /* JADX WARN: Removed duplicated region for block: B:129:0x031c  */
    /* JADX WARN: Removed duplicated region for block: B:132:0x0329  */
    /* JADX WARN: Removed duplicated region for block: B:133:0x032c  */
    /* JADX WARN: Removed duplicated region for block: B:136:0x0334  */
    /* JADX WARN: Removed duplicated region for block: B:139:0x037d  */
    /* JADX WARN: Removed duplicated region for block: B:142:0x0386  */
    /* JADX WARN: Removed duplicated region for block: B:145:0x03a2  */
    /* JADX WARN: Removed duplicated region for block: B:147:0x03aa  */
    /* JADX WARN: Removed duplicated region for block: B:150:0x03d7  */
    /* JADX WARN: Removed duplicated region for block: B:153:0x03e0  */
    /* JADX WARN: Removed duplicated region for block: B:156:0x03fc  */
    /* JADX WARN: Removed duplicated region for block: B:159:0x0405  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x0138  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x0154  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x0161  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x0171  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x0179  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x01ce  */
    /* JADX WARN: Removed duplicated region for block: B:72:0x01db  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x01ed  */
    /* JADX WARN: Removed duplicated region for block: B:85:0x01f6  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x0212  */
    /* JADX WARN: Removed duplicated region for block: B:91:0x021f  */
    /* JADX WARN: Removed duplicated region for block: B:98:0x022d  */
    /* JADX INFO: renamed from: TextFieldTransitionScope-Jy8F4Js, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static final void m3282TextFieldTransitionScopeJy8F4Js(androidx.compose.material3.internal.InputPhase r24, long r25, long r27, long r29, boolean r31, kotlin.jvm.functions.Function7<? super androidx.compose.runtime.State<java.lang.Float>, ? super androidx.compose.runtime.State<androidx.compose.ui.graphics.Color>, ? super androidx.compose.runtime.State<androidx.compose.ui.graphics.Color>, ? super androidx.compose.runtime.State<java.lang.Float>, ? super androidx.compose.runtime.State<java.lang.Float>, ? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r32, androidx.compose.runtime.Composer r33, int r34) {
        /*
            Method dump skipped, instruction units count: 1108
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.internal.TextFieldImplKt.m3282TextFieldTransitionScopeJy8F4Js(androidx.compose.material3.internal.InputPhase, long, long, long, boolean, kotlin.jvm.functions.Function7, androidx.compose.runtime.Composer, int):void");
    }

    /* JADX INFO: renamed from: animateBorderStrokeAsState-NuRrP5Q, reason: not valid java name */
    public static final State<BorderStroke> m3285animateBorderStrokeAsStateNuRrP5Q(boolean z, boolean z2, boolean z3, TextFieldColors textFieldColors, float f, float f2, Composer composer, int i) {
        State<Color> stateRememberUpdatedState;
        State<Dp> stateRememberUpdatedState2;
        Composer composer2 = composer;
        ComposerKt.sourceInformationMarkerStart(composer2, 2047013045, "C(animateBorderStrokeAsState)P(1,4,2!1,3:c#ui.unit.Dp,5:c#ui.unit.Dp)458@18252L73:TextFieldImpl.kt#mqatfk");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(2047013045, i, -1, "androidx.compose.material3.internal.animateBorderStrokeAsState (TextFieldImpl.kt:441)");
        }
        long jM2958indicatorColorXeAY9LY$material3_release = textFieldColors.m2958indicatorColorXeAY9LY$material3_release(z, z2, z3);
        if (z) {
            composer2.startReplaceGroup(1023053998);
            ComposerKt.sourceInformation(composer2, "445@17754L84");
            stateRememberUpdatedState = SingleValueAnimationKt.m398animateColorAsStateeuL9pac(jM2958indicatorColorXeAY9LY$material3_release, AnimationSpecKt.tween$default(150, 0, null, 6, null), null, null, composer, 48, 12);
            composer2 = composer;
            composer2.endReplaceGroup();
        } else {
            composer2.startReplaceGroup(1023165505);
            ComposerKt.sourceInformation(composer2, "447@17868L33");
            stateRememberUpdatedState = SnapshotStateKt.rememberUpdatedState(Color.m4558boximpl(jM2958indicatorColorXeAY9LY$material3_release), composer2, 0);
            composer2.endReplaceGroup();
        }
        State<Color> state = stateRememberUpdatedState;
        if (z) {
            composer2.startReplaceGroup(1023269417);
            ComposerKt.sourceInformation(composer2, "453@18068L85");
            stateRememberUpdatedState2 = AnimateAsStateKt.m412animateDpAsStateAjpBEmI(z3 ? f : f2, AnimationSpecKt.tween$default(150, 0, null, 6, null), null, null, composer2, 48, 12);
            composer2.endReplaceGroup();
        } else {
            composer2.startReplaceGroup(1023478388);
            ComposerKt.sourceInformation(composer2, "455@18183L46");
            stateRememberUpdatedState2 = SnapshotStateKt.rememberUpdatedState(Dp.m7025boximpl(f2), composer2, (i >> 15) & 14);
            composer2.endReplaceGroup();
        }
        State<BorderStroke> stateRememberUpdatedState3 = SnapshotStateKt.rememberUpdatedState(BorderStrokeKt.m547BorderStrokecXLIe8U(stateRememberUpdatedState2.getValue().m7041unboximpl(), state.getValue().m4578unboximpl()), composer2, 0);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd(composer2);
        return stateRememberUpdatedState3;
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
        float f = 16;
        TextFieldPadding = Dp.m7027constructorimpl(f);
        MinFocusedLabelLineHeight = Dp.m7027constructorimpl(f);
        MinSupportingTextLineHeight = Dp.m7027constructorimpl(f);
        float f2 = 48;
        IconDefaultSizeModifier = SizeKt.m994defaultMinSizeVpY3zN4(Modifier.INSTANCE, Dp.m7027constructorimpl(f2), Dp.m7027constructorimpl(f2));
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

    public static final float getSupportingTopPadding() {
        return SupportingTopPadding;
    }

    public static final float getPrefixSuffixTextPadding() {
        return PrefixSuffixTextPadding;
    }

    public static final float getMinTextLineHeight() {
        return MinTextLineHeight;
    }

    public static final float getMinFocusedLabelLineHeight() {
        return MinFocusedLabelLineHeight;
    }

    public static final float getMinSupportingTextLineHeight() {
        return MinSupportingTextLineHeight;
    }

    public static final Modifier getIconDefaultSizeModifier() {
        return IconDefaultSizeModifier;
    }

    private static final boolean CommonDecorationBox$lambda$15$lambda$7(State<Boolean> state) {
        return state.getValue().booleanValue();
    }

    private static final boolean CommonDecorationBox$lambda$15$lambda$9(State<Boolean> state) {
        return state.getValue().booleanValue();
    }
}
