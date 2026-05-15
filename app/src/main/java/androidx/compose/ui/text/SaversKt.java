package androidx.compose.ui.text;

import androidx.compose.runtime.saveable.Saver;
import androidx.compose.runtime.saveable.SaverKt;
import androidx.compose.runtime.saveable.SaverScope;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.OffsetKt;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.ColorKt;
import androidx.compose.ui.graphics.Shadow;
import androidx.compose.ui.text.AnnotatedString;
import androidx.compose.ui.text.LinkAnnotation;
import androidx.compose.ui.text.TextRange;
import androidx.compose.ui.text.font.FontWeight;
import androidx.compose.ui.text.intl.Locale;
import androidx.compose.ui.text.intl.LocaleList;
import androidx.compose.ui.text.style.BaselineShift;
import androidx.compose.ui.text.style.LineHeightStyle;
import androidx.compose.ui.text.style.TextAlign;
import androidx.compose.ui.text.style.TextDecoration;
import androidx.compose.ui.text.style.TextDirection;
import androidx.compose.ui.text.style.TextGeometricTransform;
import androidx.compose.ui.text.style.TextIndent;
import androidx.compose.ui.text.style.TextMotion;
import androidx.compose.ui.unit.TextUnit;
import androidx.compose.ui.unit.TextUnitKt;
import androidx.compose.ui.unit.TextUnitType;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.exifinterface.media.ExifInterface;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Savers.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000\u0086\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\u001ay\u0010Q\u001a\u000e\u0012\u0004\u0012\u0002HR\u0012\u0004\u0012\u0002HS0\u0011\"\u0004\b\u0000\u0010R\"\b\b\u0001\u0010S*\u00020\u00032.\u0010T\u001a*\u0012\u0004\u0012\u00020V\u0012\u0013\u0012\u0011HR¢\u0006\f\bW\u0012\b\bX\u0012\u0004\b\b(Y\u0012\u0006\u0012\u0004\u0018\u0001HS0U¢\u0006\u0002\bZ2#\u0010[\u001a\u001f\u0012\u0013\u0012\u0011HS¢\u0006\f\bW\u0012\b\bX\u0012\u0004\b\b(Y\u0012\u0006\u0012\u0004\u0018\u0001HR0\\H\u0002\u001aL\u0010[\u001a\u0004\u0018\u0001H]\"\u0014\b\u0000\u0010^*\u000e\u0012\u0004\u0012\u0002HR\u0012\u0004\u0012\u0002HS0\u0001\"\u0004\b\u0001\u0010R\"\u0004\b\u0002\u0010S\"\u0006\b\u0003\u0010]\u0018\u00012\b\u0010Y\u001a\u0004\u0018\u0001HS2\u0006\u0010_\u001a\u0002H^H\u0080\b¢\u0006\u0002\u0010`\u001a\"\u0010[\u001a\u0004\u0018\u0001H]\"\u0006\b\u0000\u0010]\u0018\u00012\b\u0010Y\u001a\u0004\u0018\u00010\u0003H\u0080\b¢\u0006\u0002\u0010a\u001aI\u0010T\u001a\u00020\u0003\"\u0014\b\u0000\u0010^*\u000e\u0012\u0004\u0012\u0002HR\u0012\u0004\u0012\u0002HS0\u0001\"\u0004\b\u0001\u0010R\"\u0004\b\u0002\u0010S2\b\u0010Y\u001a\u0004\u0018\u0001HR2\u0006\u0010_\u001a\u0002H^2\u0006\u0010b\u001a\u00020VH\u0000¢\u0006\u0002\u0010c\u001a\u001f\u0010T\u001a\u0004\u0018\u0001H^\"\u0004\b\u0000\u0010^2\b\u0010Y\u001a\u0004\u0018\u0001H^H\u0000¢\u0006\u0002\u0010a\" \u0010\u0000\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0004\u0010\u0005\"(\u0010\u0006\u001a\u001c\u0012\u0012\u0012\u0010\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u00020\u00030\b0\u0007\u0012\u0004\u0012\u00020\u00030\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"(\u0010\t\u001a\u0016\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u00020\u00030\b\u0012\u0004\u0012\u00020\u00030\u0001X\u0082\u0004¢\u0006\b\n\u0000\u0012\u0004\b\n\u0010\u000b\"\u001a\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u00030\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"\u001a\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u00030\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"\u001a\u0010\u0010\u001a\u000e\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\u00030\u0011X\u0082\u0004¢\u0006\u0002\n\u0000\"\u001a\u0010\u0013\u001a\u000e\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u00030\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"\u001a\u0010\u0015\u001a\u000e\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020\u00030\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"\u001a\u0010\u0017\u001a\u000e\u0012\u0004\u0012\u00020\u0018\u0012\u0004\u0012\u00020\u00030\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"\u001a\u0010\u0019\u001a\u000e\u0012\u0004\u0012\u00020\u001a\u0012\u0004\u0012\u00020\u00030\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"\u001a\u0010\u001b\u001a\u000e\u0012\u0004\u0012\u00020\u001c\u0012\u0004\u0012\u00020\u00030\u0011X\u0082\u0004¢\u0006\u0002\n\u0000\" \u0010\u001d\u001a\u000e\u0012\u0004\u0012\u00020\u001e\u0012\u0004\u0012\u00020\u00030\u0001X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u0005\"\u001a\u0010 \u001a\u000e\u0012\u0004\u0012\u00020!\u0012\u0004\u0012\u00020\u00030\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\" \u0010\"\u001a\u000e\u0012\u0004\u0012\u00020#\u0012\u0004\u0012\u00020\u00030\u0001X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b$\u0010\u0005\"\u001a\u0010%\u001a\u000e\u0012\u0004\u0012\u00020&\u0012\u0004\u0012\u00020\u00030\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"\u001a\u0010'\u001a\u000e\u0012\u0004\u0012\u00020(\u0012\u0004\u0012\u00020\u00030\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"\u001a\u0010)\u001a\u000e\u0012\u0004\u0012\u00020*\u0012\u0004\u0012\u00020\u00030\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\" \u0010+\u001a\u000e\u0012\u0004\u0012\u00020,\u0012\u0004\u0012\u00020\u00030\u0001X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b-\u0010\u0005\"\u001a\u0010.\u001a\u000e\u0012\u0004\u0012\u00020/\u0012\u0004\u0012\u00020\u00030\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"\u001a\u00100\u001a\u000e\u0012\u0004\u0012\u000201\u0012\u0004\u0012\u00020\u00030\u0011X\u0082\u0004¢\u0006\u0002\n\u0000\" \u00102\u001a\u000e\u0012\u0004\u0012\u000203\u0012\u0004\u0012\u00020\u00030\u0001X\u0082\u0004¢\u0006\b\n\u0000\u0012\u0004\b4\u0010\u000b\"\u001a\u00105\u001a\u000e\u0012\u0004\u0012\u000206\u0012\u0004\u0012\u00020\u00030\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"$\u00107\u001a\u000e\u0012\u0004\u0012\u00020\u001c\u0012\u0004\u0012\u00020\u00030\u0001*\u0002088@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b9\u0010:\"$\u00107\u001a\u000e\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\u00030\u0001*\u00020;8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b9\u0010<\"$\u00107\u001a\u000e\u0012\u0004\u0012\u00020!\u0012\u0004\u0012\u00020\u00030\u0001*\u00020=8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b9\u0010>\"$\u00107\u001a\u000e\u0012\u0004\u0012\u00020/\u0012\u0004\u0012\u00020\u00030\u0001*\u00020?8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b9\u0010@\"$\u00107\u001a\u000e\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u00030\u0001*\u00020A8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b9\u0010B\"$\u00107\u001a\u000e\u0012\u0004\u0012\u00020\u001a\u0012\u0004\u0012\u00020\u00030\u0001*\u00020C8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b9\u0010D\"$\u00107\u001a\u000e\u0012\u0004\u0012\u00020\u0018\u0012\u0004\u0012\u00020\u00030\u0001*\u00020E8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b9\u0010F\"$\u00107\u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u00030\u0001*\u00020G8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b9\u0010H\"$\u00107\u001a\u000e\u0012\u0004\u0012\u00020&\u0012\u0004\u0012\u00020\u00030\u0001*\u00020I8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b9\u0010J\"$\u00107\u001a\u000e\u0012\u0004\u0012\u00020(\u0012\u0004\u0012\u00020\u00030\u0001*\u00020K8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b9\u0010L\"$\u00107\u001a\u000e\u0012\u0004\u0012\u00020*\u0012\u0004\u0012\u00020\u00030\u0001*\u00020M8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b9\u0010N\"$\u00107\u001a\u000e\u0012\u0004\u0012\u000201\u0012\u0004\u0012\u00020\u00030\u0001*\u00020O8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b9\u0010P¨\u0006d"}, d2 = {"AnnotatedStringSaver", "Landroidx/compose/runtime/saveable/Saver;", "Landroidx/compose/ui/text/AnnotatedString;", "", "getAnnotatedStringSaver", "()Landroidx/compose/runtime/saveable/Saver;", "AnnotationRangeListSaver", "", "Landroidx/compose/ui/text/AnnotatedString$Range;", "AnnotationRangeSaver", "getAnnotationRangeSaver$annotations", "()V", "BaselineShiftSaver", "Landroidx/compose/ui/text/style/BaselineShift;", "ClickableSaver", "Landroidx/compose/ui/text/LinkAnnotation$Clickable;", "ColorSaver", "Landroidx/compose/ui/text/NonNullValueClassSaver;", "Landroidx/compose/ui/graphics/Color;", "FontWeightSaver", "Landroidx/compose/ui/text/font/FontWeight;", "LinkSaver", "Landroidx/compose/ui/text/LinkAnnotation$Url;", "LocaleListSaver", "Landroidx/compose/ui/text/intl/LocaleList;", "LocaleSaver", "Landroidx/compose/ui/text/intl/Locale;", "OffsetSaver", "Landroidx/compose/ui/geometry/Offset;", "ParagraphStyleSaver", "Landroidx/compose/ui/text/ParagraphStyle;", "getParagraphStyleSaver", "ShadowSaver", "Landroidx/compose/ui/graphics/Shadow;", "SpanStyleSaver", "Landroidx/compose/ui/text/SpanStyle;", "getSpanStyleSaver", "TextDecorationSaver", "Landroidx/compose/ui/text/style/TextDecoration;", "TextGeometricTransformSaver", "Landroidx/compose/ui/text/style/TextGeometricTransform;", "TextIndentSaver", "Landroidx/compose/ui/text/style/TextIndent;", "TextLinkStylesSaver", "Landroidx/compose/ui/text/TextLinkStyles;", "getTextLinkStylesSaver", "TextRangeSaver", "Landroidx/compose/ui/text/TextRange;", "TextUnitSaver", "Landroidx/compose/ui/unit/TextUnit;", "UrlAnnotationSaver", "Landroidx/compose/ui/text/UrlAnnotation;", "getUrlAnnotationSaver$annotations", "VerbatimTtsAnnotationSaver", "Landroidx/compose/ui/text/VerbatimTtsAnnotation;", "Saver", "Landroidx/compose/ui/geometry/Offset$Companion;", "getSaver", "(Landroidx/compose/ui/geometry/Offset$Companion;)Landroidx/compose/runtime/saveable/Saver;", "Landroidx/compose/ui/graphics/Color$Companion;", "(Landroidx/compose/ui/graphics/Color$Companion;)Landroidx/compose/runtime/saveable/Saver;", "Landroidx/compose/ui/graphics/Shadow$Companion;", "(Landroidx/compose/ui/graphics/Shadow$Companion;)Landroidx/compose/runtime/saveable/Saver;", "Landroidx/compose/ui/text/TextRange$Companion;", "(Landroidx/compose/ui/text/TextRange$Companion;)Landroidx/compose/runtime/saveable/Saver;", "Landroidx/compose/ui/text/font/FontWeight$Companion;", "(Landroidx/compose/ui/text/font/FontWeight$Companion;)Landroidx/compose/runtime/saveable/Saver;", "Landroidx/compose/ui/text/intl/Locale$Companion;", "(Landroidx/compose/ui/text/intl/Locale$Companion;)Landroidx/compose/runtime/saveable/Saver;", "Landroidx/compose/ui/text/intl/LocaleList$Companion;", "(Landroidx/compose/ui/text/intl/LocaleList$Companion;)Landroidx/compose/runtime/saveable/Saver;", "Landroidx/compose/ui/text/style/BaselineShift$Companion;", "(Landroidx/compose/ui/text/style/BaselineShift$Companion;)Landroidx/compose/runtime/saveable/Saver;", "Landroidx/compose/ui/text/style/TextDecoration$Companion;", "(Landroidx/compose/ui/text/style/TextDecoration$Companion;)Landroidx/compose/runtime/saveable/Saver;", "Landroidx/compose/ui/text/style/TextGeometricTransform$Companion;", "(Landroidx/compose/ui/text/style/TextGeometricTransform$Companion;)Landroidx/compose/runtime/saveable/Saver;", "Landroidx/compose/ui/text/style/TextIndent$Companion;", "(Landroidx/compose/ui/text/style/TextIndent$Companion;)Landroidx/compose/runtime/saveable/Saver;", "Landroidx/compose/ui/unit/TextUnit$Companion;", "(Landroidx/compose/ui/unit/TextUnit$Companion;)Landroidx/compose/runtime/saveable/Saver;", "NonNullValueClassSaver", "Original", "Saveable", "save", "Lkotlin/Function2;", "Landroidx/compose/runtime/saveable/SaverScope;", "Lkotlin/ParameterName;", "name", "value", "Lkotlin/ExtensionFunctionType;", "restore", "Lkotlin/Function1;", "Result", ExifInterface.GPS_DIRECTION_TRUE, "saver", "(Ljava/lang/Object;Landroidx/compose/runtime/saveable/Saver;)Ljava/lang/Object;", "(Ljava/lang/Object;)Ljava/lang/Object;", "scope", "(Ljava/lang/Object;Landroidx/compose/runtime/saveable/Saver;Landroidx/compose/runtime/saveable/SaverScope;)Ljava/lang/Object;", "ui-text_release"}, k = 2, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
public final class SaversKt {
    private static final Saver<AnnotatedString, Object> AnnotatedStringSaver = SaverKt.Saver(new Function2<SaverScope, AnnotatedString, Object>() { // from class: androidx.compose.ui.text.SaversKt$AnnotatedStringSaver$1
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(SaverScope saverScope, AnnotatedString annotatedString) {
            return CollectionsKt.arrayListOf(SaversKt.save(annotatedString.getText()), SaversKt.save(annotatedString.getSpanStyles(), SaversKt.AnnotationRangeListSaver, saverScope), SaversKt.save(annotatedString.getParagraphStyles(), SaversKt.AnnotationRangeListSaver, saverScope), SaversKt.save(annotatedString.getAnnotations$ui_text_release(), SaversKt.AnnotationRangeListSaver, saverScope));
        }
    }, new Function1<Object, AnnotatedString>() { // from class: androidx.compose.ui.text.SaversKt$AnnotatedStringSaver$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function1
        public final AnnotatedString invoke(Object obj) {
            List list;
            List list2;
            Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type kotlin.collections.List<kotlin.Any?>");
            List list3 = (List) obj;
            Object obj2 = list3.get(1);
            Saver saver = SaversKt.AnnotationRangeListSaver;
            List list4 = null;
            List list5 = ((!Intrinsics.areEqual(obj2, (Object) false) || (saver instanceof NonNullValueClassSaver)) && obj2 != null) ? (List) saver.restore(obj2) : null;
            Object obj3 = list3.get(2);
            Saver saver2 = SaversKt.AnnotationRangeListSaver;
            List list6 = ((!Intrinsics.areEqual(obj3, (Object) false) || (saver2 instanceof NonNullValueClassSaver)) && obj3 != null) ? (List) saver2.restore(obj3) : null;
            Object obj4 = list3.get(0);
            String str = obj4 != null ? (String) obj4 : null;
            Intrinsics.checkNotNull(str);
            if (list5 != null) {
                List list7 = list5;
                if (list7.isEmpty()) {
                    list7 = null;
                }
                list = list7;
            } else {
                list = null;
            }
            if (list6 != null) {
                List list8 = list6;
                if (list8.isEmpty()) {
                    list8 = null;
                }
                list2 = list8;
            } else {
                list2 = null;
            }
            Object obj5 = list3.get(3);
            Saver saver3 = SaversKt.AnnotationRangeListSaver;
            if ((!Intrinsics.areEqual(obj5, (Object) false) || (saver3 instanceof NonNullValueClassSaver)) && obj5 != null) {
                list4 = (List) saver3.restore(obj5);
            }
            return new AnnotatedString(str, list, list2, list4);
        }
    });
    private static final Saver<List<AnnotatedString.Range<? extends Object>>, Object> AnnotationRangeListSaver = SaverKt.Saver(new Function2<SaverScope, List<? extends AnnotatedString.Range<? extends Object>>, Object>() { // from class: androidx.compose.ui.text.SaversKt$AnnotationRangeListSaver$1
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(SaverScope saverScope, List<? extends AnnotatedString.Range<? extends Object>> list) {
            ArrayList arrayList = new ArrayList(list.size());
            int size = list.size();
            for (int i = 0; i < size; i++) {
                arrayList.add(SaversKt.save(list.get(i), SaversKt.AnnotationRangeSaver, saverScope));
            }
            return arrayList;
        }
    }, new Function1<Object, List<? extends AnnotatedString.Range<? extends Object>>>() { // from class: androidx.compose.ui.text.SaversKt$AnnotationRangeListSaver$2
        @Override // kotlin.jvm.functions.Function1
        public final List<? extends AnnotatedString.Range<? extends Object>> invoke(Object obj) {
            Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type kotlin.collections.List<kotlin.Any>");
            List list = (List) obj;
            ArrayList arrayList = new ArrayList(list.size());
            int size = list.size();
            for (int i = 0; i < size; i++) {
                Object obj2 = list.get(i);
                ArrayList arrayList2 = arrayList;
                Saver saver = SaversKt.AnnotationRangeSaver;
                AnnotatedString.Range range = null;
                if ((!Intrinsics.areEqual(obj2, (Object) false) || (saver instanceof NonNullValueClassSaver)) && obj2 != null) {
                    range = (AnnotatedString.Range) saver.restore(obj2);
                }
                Intrinsics.checkNotNull(range);
                arrayList2.add(range);
            }
            return arrayList;
        }
    });
    private static final Saver<AnnotatedString.Range<? extends Object>, Object> AnnotationRangeSaver = SaverKt.Saver(new Function2<SaverScope, AnnotatedString.Range<? extends Object>, Object>() { // from class: androidx.compose.ui.text.SaversKt$AnnotationRangeSaver$1

        /* JADX INFO: compiled from: Savers.kt */
        @Metadata(k = 3, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
        public /* synthetic */ class WhenMappings {
            public static final /* synthetic */ int[] $EnumSwitchMapping$0;

            static {
                int[] iArr = new int[AnnotationType.values().length];
                try {
                    iArr[AnnotationType.Paragraph.ordinal()] = 1;
                } catch (NoSuchFieldError unused) {
                }
                try {
                    iArr[AnnotationType.Span.ordinal()] = 2;
                } catch (NoSuchFieldError unused2) {
                }
                try {
                    iArr[AnnotationType.VerbatimTts.ordinal()] = 3;
                } catch (NoSuchFieldError unused3) {
                }
                try {
                    iArr[AnnotationType.Url.ordinal()] = 4;
                } catch (NoSuchFieldError unused4) {
                }
                try {
                    iArr[AnnotationType.Link.ordinal()] = 5;
                } catch (NoSuchFieldError unused5) {
                }
                try {
                    iArr[AnnotationType.Clickable.ordinal()] = 6;
                } catch (NoSuchFieldError unused6) {
                }
                try {
                    iArr[AnnotationType.String.ordinal()] = 7;
                } catch (NoSuchFieldError unused7) {
                }
                $EnumSwitchMapping$0 = iArr;
            }
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(SaverScope saverScope, AnnotatedString.Range<? extends Object> range) {
            AnnotationType annotationType;
            Object objSave;
            Object item = range.getItem();
            if (item instanceof ParagraphStyle) {
                annotationType = AnnotationType.Paragraph;
            } else if (item instanceof SpanStyle) {
                annotationType = AnnotationType.Span;
            } else if (item instanceof VerbatimTtsAnnotation) {
                annotationType = AnnotationType.VerbatimTts;
            } else if (item instanceof UrlAnnotation) {
                annotationType = AnnotationType.Url;
            } else if (item instanceof LinkAnnotation.Url) {
                annotationType = AnnotationType.Link;
            } else {
                annotationType = item instanceof LinkAnnotation.Clickable ? AnnotationType.Clickable : AnnotationType.String;
            }
            switch (WhenMappings.$EnumSwitchMapping$0[annotationType.ordinal()]) {
                case 1:
                    Object item2 = range.getItem();
                    Intrinsics.checkNotNull(item2, "null cannot be cast to non-null type androidx.compose.ui.text.ParagraphStyle");
                    objSave = SaversKt.save((ParagraphStyle) item2, SaversKt.getParagraphStyleSaver(), saverScope);
                    break;
                case 2:
                    Object item3 = range.getItem();
                    Intrinsics.checkNotNull(item3, "null cannot be cast to non-null type androidx.compose.ui.text.SpanStyle");
                    objSave = SaversKt.save((SpanStyle) item3, SaversKt.getSpanStyleSaver(), saverScope);
                    break;
                case 3:
                    Object item4 = range.getItem();
                    Intrinsics.checkNotNull(item4, "null cannot be cast to non-null type androidx.compose.ui.text.VerbatimTtsAnnotation");
                    objSave = SaversKt.save((VerbatimTtsAnnotation) item4, SaversKt.VerbatimTtsAnnotationSaver, saverScope);
                    break;
                case 4:
                    Object item5 = range.getItem();
                    Intrinsics.checkNotNull(item5, "null cannot be cast to non-null type androidx.compose.ui.text.UrlAnnotation");
                    objSave = SaversKt.save((UrlAnnotation) item5, SaversKt.UrlAnnotationSaver, saverScope);
                    break;
                case 5:
                    Object item6 = range.getItem();
                    Intrinsics.checkNotNull(item6, "null cannot be cast to non-null type androidx.compose.ui.text.LinkAnnotation.Url");
                    objSave = SaversKt.save((LinkAnnotation.Url) item6, SaversKt.LinkSaver, saverScope);
                    break;
                case 6:
                    Object item7 = range.getItem();
                    Intrinsics.checkNotNull(item7, "null cannot be cast to non-null type androidx.compose.ui.text.LinkAnnotation.Clickable");
                    objSave = SaversKt.save((LinkAnnotation.Clickable) item7, SaversKt.ClickableSaver, saverScope);
                    break;
                case 7:
                    objSave = SaversKt.save(range.getItem());
                    break;
                default:
                    throw new NoWhenBranchMatchedException();
            }
            return CollectionsKt.arrayListOf(SaversKt.save(annotationType), objSave, SaversKt.save(Integer.valueOf(range.getStart())), SaversKt.save(Integer.valueOf(range.getEnd())), SaversKt.save(range.getTag()));
        }
    }, new Function1<Object, AnnotatedString.Range<? extends Object>>() { // from class: androidx.compose.ui.text.SaversKt$AnnotationRangeSaver$2

        /* JADX INFO: compiled from: Savers.kt */
        @Metadata(k = 3, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
        public /* synthetic */ class WhenMappings {
            public static final /* synthetic */ int[] $EnumSwitchMapping$0;

            static {
                int[] iArr = new int[AnnotationType.values().length];
                try {
                    iArr[AnnotationType.Paragraph.ordinal()] = 1;
                } catch (NoSuchFieldError unused) {
                }
                try {
                    iArr[AnnotationType.Span.ordinal()] = 2;
                } catch (NoSuchFieldError unused2) {
                }
                try {
                    iArr[AnnotationType.VerbatimTts.ordinal()] = 3;
                } catch (NoSuchFieldError unused3) {
                }
                try {
                    iArr[AnnotationType.Url.ordinal()] = 4;
                } catch (NoSuchFieldError unused4) {
                }
                try {
                    iArr[AnnotationType.Link.ordinal()] = 5;
                } catch (NoSuchFieldError unused5) {
                }
                try {
                    iArr[AnnotationType.Clickable.ordinal()] = 6;
                } catch (NoSuchFieldError unused6) {
                }
                try {
                    iArr[AnnotationType.String.ordinal()] = 7;
                } catch (NoSuchFieldError unused7) {
                }
                $EnumSwitchMapping$0 = iArr;
            }
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function1
        public final AnnotatedString.Range<? extends Object> invoke(Object obj) {
            Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type kotlin.collections.List<kotlin.Any>");
            List list = (List) obj;
            Object obj2 = list.get(0);
            AnnotationType annotationType = obj2 != null ? (AnnotationType) obj2 : null;
            Intrinsics.checkNotNull(annotationType);
            Object obj3 = list.get(2);
            Integer num = obj3 != null ? (Integer) obj3 : null;
            Intrinsics.checkNotNull(num);
            int iIntValue = num.intValue();
            Object obj4 = list.get(3);
            Integer num2 = obj4 != null ? (Integer) obj4 : null;
            Intrinsics.checkNotNull(num2);
            int iIntValue2 = num2.intValue();
            Object obj5 = list.get(4);
            String str = obj5 != null ? (String) obj5 : null;
            Intrinsics.checkNotNull(str);
            switch (WhenMappings.$EnumSwitchMapping$0[annotationType.ordinal()]) {
                case 1:
                    Object obj6 = list.get(1);
                    Saver<ParagraphStyle, Object> paragraphStyleSaver = SaversKt.getParagraphStyleSaver();
                    if ((!Intrinsics.areEqual(obj6, (Object) false) || (paragraphStyleSaver instanceof NonNullValueClassSaver)) && obj6 != null) {
                        paragraphStyleRestore = paragraphStyleSaver.restore(obj6);
                    }
                    Intrinsics.checkNotNull(paragraphStyleRestore);
                    return new AnnotatedString.Range<>(paragraphStyleRestore, iIntValue, iIntValue2, str);
                case 2:
                    Object obj7 = list.get(1);
                    Saver<SpanStyle, Object> spanStyleSaver = SaversKt.getSpanStyleSaver();
                    if ((!Intrinsics.areEqual(obj7, (Object) false) || (spanStyleSaver instanceof NonNullValueClassSaver)) && obj7 != null) {
                        paragraphStyleRestore = spanStyleSaver.restore(obj7);
                    }
                    Intrinsics.checkNotNull(paragraphStyleRestore);
                    return new AnnotatedString.Range<>(paragraphStyleRestore, iIntValue, iIntValue2, str);
                case 3:
                    Object obj8 = list.get(1);
                    Saver saver = SaversKt.VerbatimTtsAnnotationSaver;
                    if ((!Intrinsics.areEqual(obj8, (Object) false) || (saver instanceof NonNullValueClassSaver)) && obj8 != null) {
                        paragraphStyleRestore = (VerbatimTtsAnnotation) saver.restore(obj8);
                    }
                    Intrinsics.checkNotNull(paragraphStyleRestore);
                    return new AnnotatedString.Range<>(paragraphStyleRestore, iIntValue, iIntValue2, str);
                case 4:
                    Object obj9 = list.get(1);
                    Saver saver2 = SaversKt.UrlAnnotationSaver;
                    if ((!Intrinsics.areEqual(obj9, (Object) false) || (saver2 instanceof NonNullValueClassSaver)) && obj9 != null) {
                        paragraphStyleRestore = (UrlAnnotation) saver2.restore(obj9);
                    }
                    Intrinsics.checkNotNull(paragraphStyleRestore);
                    return new AnnotatedString.Range<>(paragraphStyleRestore, iIntValue, iIntValue2, str);
                case 5:
                    Object obj10 = list.get(1);
                    Saver saver3 = SaversKt.LinkSaver;
                    if ((!Intrinsics.areEqual(obj10, (Object) false) || (saver3 instanceof NonNullValueClassSaver)) && obj10 != null) {
                        paragraphStyleRestore = (LinkAnnotation.Url) saver3.restore(obj10);
                    }
                    Intrinsics.checkNotNull(paragraphStyleRestore);
                    return new AnnotatedString.Range<>(paragraphStyleRestore, iIntValue, iIntValue2, str);
                case 6:
                    Object obj11 = list.get(1);
                    Saver saver4 = SaversKt.ClickableSaver;
                    if ((!Intrinsics.areEqual(obj11, (Object) false) || (saver4 instanceof NonNullValueClassSaver)) && obj11 != null) {
                        paragraphStyleRestore = (LinkAnnotation.Clickable) saver4.restore(obj11);
                    }
                    Intrinsics.checkNotNull(paragraphStyleRestore);
                    return new AnnotatedString.Range<>(paragraphStyleRestore, iIntValue, iIntValue2, str);
                case 7:
                    Object obj12 = list.get(1);
                    paragraphStyleRestore = obj12 != null ? (String) obj12 : null;
                    Intrinsics.checkNotNull(paragraphStyleRestore);
                    return new AnnotatedString.Range<>(paragraphStyleRestore, iIntValue, iIntValue2, str);
                default:
                    throw new NoWhenBranchMatchedException();
            }
        }
    });
    private static final Saver<VerbatimTtsAnnotation, Object> VerbatimTtsAnnotationSaver = SaverKt.Saver(new Function2<SaverScope, VerbatimTtsAnnotation, Object>() { // from class: androidx.compose.ui.text.SaversKt$VerbatimTtsAnnotationSaver$1
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(SaverScope saverScope, VerbatimTtsAnnotation verbatimTtsAnnotation) {
            return SaversKt.save(verbatimTtsAnnotation.getVerbatim());
        }
    }, new Function1<Object, VerbatimTtsAnnotation>() { // from class: androidx.compose.ui.text.SaversKt$VerbatimTtsAnnotationSaver$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function1
        public final VerbatimTtsAnnotation invoke(Object obj) {
            String str = obj != null ? (String) obj : null;
            Intrinsics.checkNotNull(str);
            return new VerbatimTtsAnnotation(str);
        }
    });
    private static final Saver<UrlAnnotation, Object> UrlAnnotationSaver = SaverKt.Saver(new Function2<SaverScope, UrlAnnotation, Object>() { // from class: androidx.compose.ui.text.SaversKt$UrlAnnotationSaver$1
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(SaverScope saverScope, UrlAnnotation urlAnnotation) {
            return SaversKt.save(urlAnnotation.getUrl());
        }
    }, new Function1<Object, UrlAnnotation>() { // from class: androidx.compose.ui.text.SaversKt$UrlAnnotationSaver$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function1
        public final UrlAnnotation invoke(Object obj) {
            String str = obj != null ? (String) obj : null;
            Intrinsics.checkNotNull(str);
            return new UrlAnnotation(str);
        }
    });
    private static final Saver<LinkAnnotation.Url, Object> LinkSaver = SaverKt.Saver(new Function2<SaverScope, LinkAnnotation.Url, Object>() { // from class: androidx.compose.ui.text.SaversKt$LinkSaver$1
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(SaverScope saverScope, LinkAnnotation.Url url) {
            return CollectionsKt.arrayListOf(SaversKt.save(url.getUrl()), SaversKt.save(url.getStyles(), SaversKt.getTextLinkStylesSaver(), saverScope));
        }
    }, new Function1<Object, LinkAnnotation.Url>() { // from class: androidx.compose.ui.text.SaversKt$LinkSaver$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function1
        public final LinkAnnotation.Url invoke(Object obj) {
            Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type kotlin.collections.List<kotlin.Any?>");
            List list = (List) obj;
            Object obj2 = list.get(0);
            TextLinkStyles textLinkStylesRestore = null;
            String str = obj2 != null ? (String) obj2 : null;
            Intrinsics.checkNotNull(str);
            Object obj3 = list.get(1);
            Saver<TextLinkStyles, Object> textLinkStylesSaver = SaversKt.getTextLinkStylesSaver();
            if ((!Intrinsics.areEqual(obj3, (Object) false) || (textLinkStylesSaver instanceof NonNullValueClassSaver)) && obj3 != null) {
                textLinkStylesRestore = textLinkStylesSaver.restore(obj3);
            }
            return new LinkAnnotation.Url(str, textLinkStylesRestore, null, 4, null);
        }
    });
    private static final Saver<LinkAnnotation.Clickable, Object> ClickableSaver = SaverKt.Saver(new Function2<SaverScope, LinkAnnotation.Clickable, Object>() { // from class: androidx.compose.ui.text.SaversKt$ClickableSaver$1
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(SaverScope saverScope, LinkAnnotation.Clickable clickable) {
            return CollectionsKt.arrayListOf(SaversKt.save(clickable.getTag()), SaversKt.save(clickable.getStyles(), SaversKt.getTextLinkStylesSaver(), saverScope));
        }
    }, new Function1<Object, LinkAnnotation.Clickable>() { // from class: androidx.compose.ui.text.SaversKt$ClickableSaver$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function1
        public final LinkAnnotation.Clickable invoke(Object obj) {
            Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type kotlin.collections.List<kotlin.Any?>");
            List list = (List) obj;
            Object obj2 = list.get(0);
            String str = obj2 != null ? (String) obj2 : null;
            Intrinsics.checkNotNull(str);
            Object obj3 = list.get(1);
            Saver<TextLinkStyles, Object> textLinkStylesSaver = SaversKt.getTextLinkStylesSaver();
            return new LinkAnnotation.Clickable(str, ((!Intrinsics.areEqual(obj3, (Object) false) || (textLinkStylesSaver instanceof NonNullValueClassSaver)) && obj3 != null) ? textLinkStylesSaver.restore(obj3) : null, null);
        }
    });
    private static final Saver<ParagraphStyle, Object> ParagraphStyleSaver = SaverKt.Saver(new Function2<SaverScope, ParagraphStyle, Object>() { // from class: androidx.compose.ui.text.SaversKt$ParagraphStyleSaver$1
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(SaverScope saverScope, ParagraphStyle paragraphStyle) {
            return CollectionsKt.arrayListOf(SaversKt.save(TextAlign.m6909boximpl(paragraphStyle.getTextAlign())), SaversKt.save(TextDirection.m6923boximpl(paragraphStyle.getTextDirection())), SaversKt.save(TextUnit.m7210boximpl(paragraphStyle.getLineHeight()), SaversKt.getSaver(TextUnit.INSTANCE), saverScope), SaversKt.save(paragraphStyle.getTextIndent(), SaversKt.getSaver(TextIndent.INSTANCE), saverScope));
        }
    }, new Function1<Object, ParagraphStyle>() { // from class: androidx.compose.ui.text.SaversKt$ParagraphStyleSaver$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function1
        public final ParagraphStyle invoke(Object obj) {
            Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type kotlin.collections.List<kotlin.Any?>");
            List list = (List) obj;
            Object obj2 = list.get(0);
            TextIndent textIndentRestore = null;
            TextAlign textAlign = obj2 != null ? (TextAlign) obj2 : null;
            Intrinsics.checkNotNull(textAlign);
            int value = textAlign.getValue();
            Object obj3 = list.get(1);
            TextDirection textDirection = obj3 != null ? (TextDirection) obj3 : null;
            Intrinsics.checkNotNull(textDirection);
            int value2 = textDirection.getValue();
            Object obj4 = list.get(2);
            Saver<TextUnit, Object> saver = SaversKt.getSaver(TextUnit.INSTANCE);
            TextUnit textUnitRestore = ((!Intrinsics.areEqual(obj4, (Object) false) || (saver instanceof NonNullValueClassSaver)) && obj4 != null) ? saver.restore(obj4) : null;
            Intrinsics.checkNotNull(textUnitRestore);
            long packedValue = textUnitRestore.getPackedValue();
            Object obj5 = list.get(3);
            Saver<TextIndent, Object> saver2 = SaversKt.getSaver(TextIndent.INSTANCE);
            if ((!Intrinsics.areEqual(obj5, (Object) false) || (saver2 instanceof NonNullValueClassSaver)) && obj5 != null) {
                textIndentRestore = saver2.restore(obj5);
            }
            return new ParagraphStyle(value, value2, packedValue, textIndentRestore, (PlatformParagraphStyle) null, (LineHeightStyle) null, 0, 0, (TextMotion) null, 496, (DefaultConstructorMarker) null);
        }
    });
    private static final Saver<SpanStyle, Object> SpanStyleSaver = SaverKt.Saver(new Function2<SaverScope, SpanStyle, Object>() { // from class: androidx.compose.ui.text.SaversKt$SpanStyleSaver$1
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(SaverScope saverScope, SpanStyle spanStyle) {
            return CollectionsKt.arrayListOf(SaversKt.save(Color.m4558boximpl(spanStyle.m6462getColor0d7_KjU()), SaversKt.getSaver(Color.INSTANCE), saverScope), SaversKt.save(TextUnit.m7210boximpl(spanStyle.getFontSize()), SaversKt.getSaver(TextUnit.INSTANCE), saverScope), SaversKt.save(spanStyle.getFontWeight(), SaversKt.getSaver(FontWeight.INSTANCE), saverScope), SaversKt.save(spanStyle.getFontStyle()), SaversKt.save(spanStyle.getFontSynthesis()), SaversKt.save(-1), SaversKt.save(spanStyle.getFontFeatureSettings()), SaversKt.save(TextUnit.m7210boximpl(spanStyle.getLetterSpacing()), SaversKt.getSaver(TextUnit.INSTANCE), saverScope), SaversKt.save(spanStyle.getBaselineShift(), SaversKt.getSaver(BaselineShift.INSTANCE), saverScope), SaversKt.save(spanStyle.getTextGeometricTransform(), SaversKt.getSaver(TextGeometricTransform.INSTANCE), saverScope), SaversKt.save(spanStyle.getLocaleList(), SaversKt.getSaver(LocaleList.INSTANCE), saverScope), SaversKt.save(Color.m4558boximpl(spanStyle.getBackground()), SaversKt.getSaver(Color.INSTANCE), saverScope), SaversKt.save(spanStyle.getTextDecoration(), SaversKt.getSaver(TextDecoration.INSTANCE), saverScope), SaversKt.save(spanStyle.getShadow(), SaversKt.getSaver(Shadow.INSTANCE), saverScope));
        }
    }, new Function1<Object, SpanStyle>() { // from class: androidx.compose.ui.text.SaversKt$SpanStyleSaver$2
        /* JADX WARN: Can't rename method to resolve collision */
        /*  JADX ERROR: JadxRuntimeException in pass: ModVisitor
            jadx.core.utils.exceptions.JadxRuntimeException: Can't remove SSA var: r1v1 androidx.compose.ui.text.SpanStyle, still in use, count: 2, list:
              (r1v1 androidx.compose.ui.text.SpanStyle) from 0x00ff: MOVE (r16v2 androidx.compose.ui.text.SpanStyle) = (r1v1 androidx.compose.ui.text.SpanStyle)
              (r1v1 androidx.compose.ui.text.SpanStyle) from 0x00f8: MOVE (r16v7 androidx.compose.ui.text.SpanStyle) = (r1v1 androidx.compose.ui.text.SpanStyle)
            	at jadx.core.utils.InsnRemover.removeSsaVar(InsnRemover.java:162)
            	at jadx.core.utils.InsnRemover.unbindResult(InsnRemover.java:127)
            	at jadx.core.utils.InsnRemover.unbindInsn(InsnRemover.java:91)
            	at jadx.core.utils.InsnRemover.addAndUnbind(InsnRemover.java:57)
            	at jadx.core.dex.visitors.ModVisitor.removeStep(ModVisitor.java:463)
            	at jadx.core.dex.visitors.ModVisitor.visit(ModVisitor.java:97)
            */
        @Override // kotlin.jvm.functions.Function1
        public final androidx.compose.ui.text.SpanStyle invoke(java.lang.Object r25) {
            /*
                Method dump skipped, instruction units count: 452
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.text.SaversKt$SpanStyleSaver$2.invoke(java.lang.Object):androidx.compose.ui.text.SpanStyle");
        }
    });
    private static final Saver<TextLinkStyles, Object> TextLinkStylesSaver = SaverKt.Saver(new Function2<SaverScope, TextLinkStyles, Object>() { // from class: androidx.compose.ui.text.SaversKt$TextLinkStylesSaver$1
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(SaverScope saverScope, TextLinkStyles textLinkStyles) {
            return CollectionsKt.arrayListOf(SaversKt.save(textLinkStyles.getStyle(), SaversKt.getSpanStyleSaver(), saverScope), SaversKt.save(textLinkStyles.getFocusedStyle(), SaversKt.getSpanStyleSaver(), saverScope), SaversKt.save(textLinkStyles.getHoveredStyle(), SaversKt.getSpanStyleSaver(), saverScope), SaversKt.save(textLinkStyles.getPressedStyle(), SaversKt.getSpanStyleSaver(), saverScope));
        }
    }, new Function1<Object, TextLinkStyles>() { // from class: androidx.compose.ui.text.SaversKt$TextLinkStylesSaver$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function1
        public final TextLinkStyles invoke(Object obj) {
            Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type kotlin.collections.List<kotlin.Any?>");
            List list = (List) obj;
            Object obj2 = list.get(0);
            Saver<SpanStyle, Object> spanStyleSaver = SaversKt.getSpanStyleSaver();
            SpanStyle spanStyleRestore = null;
            SpanStyle spanStyleRestore2 = ((!Intrinsics.areEqual(obj2, (Object) false) || (spanStyleSaver instanceof NonNullValueClassSaver)) && obj2 != null) ? spanStyleSaver.restore(obj2) : null;
            Object obj3 = list.get(1);
            Saver<SpanStyle, Object> spanStyleSaver2 = SaversKt.getSpanStyleSaver();
            SpanStyle spanStyleRestore3 = ((!Intrinsics.areEqual(obj3, (Object) false) || (spanStyleSaver2 instanceof NonNullValueClassSaver)) && obj3 != null) ? spanStyleSaver2.restore(obj3) : null;
            Object obj4 = list.get(2);
            Saver<SpanStyle, Object> spanStyleSaver3 = SaversKt.getSpanStyleSaver();
            SpanStyle spanStyleRestore4 = ((!Intrinsics.areEqual(obj4, (Object) false) || (spanStyleSaver3 instanceof NonNullValueClassSaver)) && obj4 != null) ? spanStyleSaver3.restore(obj4) : null;
            Object obj5 = list.get(3);
            Saver<SpanStyle, Object> spanStyleSaver4 = SaversKt.getSpanStyleSaver();
            if ((!Intrinsics.areEqual(obj5, (Object) false) || (spanStyleSaver4 instanceof NonNullValueClassSaver)) && obj5 != null) {
                spanStyleRestore = spanStyleSaver4.restore(obj5);
            }
            return new TextLinkStyles(spanStyleRestore2, spanStyleRestore3, spanStyleRestore4, spanStyleRestore);
        }
    });
    private static final Saver<TextDecoration, Object> TextDecorationSaver = SaverKt.Saver(new Function2<SaverScope, TextDecoration, Object>() { // from class: androidx.compose.ui.text.SaversKt$TextDecorationSaver$1
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(SaverScope saverScope, TextDecoration textDecoration) {
            return Integer.valueOf(textDecoration.getMask());
        }
    }, new Function1<Object, TextDecoration>() { // from class: androidx.compose.ui.text.SaversKt$TextDecorationSaver$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function1
        public final TextDecoration invoke(Object obj) {
            Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type kotlin.Int");
            return new TextDecoration(((Integer) obj).intValue());
        }
    });
    private static final Saver<TextGeometricTransform, Object> TextGeometricTransformSaver = SaverKt.Saver(new Function2<SaverScope, TextGeometricTransform, Object>() { // from class: androidx.compose.ui.text.SaversKt$TextGeometricTransformSaver$1
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(SaverScope saverScope, TextGeometricTransform textGeometricTransform) {
            return CollectionsKt.arrayListOf(Float.valueOf(textGeometricTransform.getScaleX()), Float.valueOf(textGeometricTransform.getSkewX()));
        }
    }, new Function1<Object, TextGeometricTransform>() { // from class: androidx.compose.ui.text.SaversKt$TextGeometricTransformSaver$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function1
        public final TextGeometricTransform invoke(Object obj) {
            Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type kotlin.collections.List<kotlin.Float>");
            List list = (List) obj;
            return new TextGeometricTransform(((Number) list.get(0)).floatValue(), ((Number) list.get(1)).floatValue());
        }
    });
    private static final Saver<TextIndent, Object> TextIndentSaver = SaverKt.Saver(new Function2<SaverScope, TextIndent, Object>() { // from class: androidx.compose.ui.text.SaversKt$TextIndentSaver$1
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(SaverScope saverScope, TextIndent textIndent) {
            return CollectionsKt.arrayListOf(SaversKt.save(TextUnit.m7210boximpl(textIndent.getFirstLine()), SaversKt.getSaver(TextUnit.INSTANCE), saverScope), SaversKt.save(TextUnit.m7210boximpl(textIndent.getRestLine()), SaversKt.getSaver(TextUnit.INSTANCE), saverScope));
        }
    }, new Function1<Object, TextIndent>() { // from class: androidx.compose.ui.text.SaversKt$TextIndentSaver$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function1
        public final TextIndent invoke(Object obj) {
            Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type kotlin.collections.List<kotlin.Any>");
            List list = (List) obj;
            Object obj2 = list.get(0);
            Saver<TextUnit, Object> saver = SaversKt.getSaver(TextUnit.INSTANCE);
            TextUnit textUnitRestore = null;
            TextUnit textUnitRestore2 = ((!Intrinsics.areEqual(obj2, (Object) false) || (saver instanceof NonNullValueClassSaver)) && obj2 != null) ? saver.restore(obj2) : null;
            Intrinsics.checkNotNull(textUnitRestore2);
            long packedValue = textUnitRestore2.getPackedValue();
            Object obj3 = list.get(1);
            Saver<TextUnit, Object> saver2 = SaversKt.getSaver(TextUnit.INSTANCE);
            if ((!Intrinsics.areEqual(obj3, (Object) false) || (saver2 instanceof NonNullValueClassSaver)) && obj3 != null) {
                textUnitRestore = saver2.restore(obj3);
            }
            Intrinsics.checkNotNull(textUnitRestore);
            return new TextIndent(packedValue, textUnitRestore.getPackedValue(), null);
        }
    });
    private static final Saver<FontWeight, Object> FontWeightSaver = SaverKt.Saver(new Function2<SaverScope, FontWeight, Object>() { // from class: androidx.compose.ui.text.SaversKt$FontWeightSaver$1
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(SaverScope saverScope, FontWeight fontWeight) {
            return Integer.valueOf(fontWeight.getWeight());
        }
    }, new Function1<Object, FontWeight>() { // from class: androidx.compose.ui.text.SaversKt$FontWeightSaver$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function1
        public final FontWeight invoke(Object obj) {
            Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type kotlin.Int");
            return new FontWeight(((Integer) obj).intValue());
        }
    });
    private static final Saver<BaselineShift, Object> BaselineShiftSaver = SaverKt.Saver(new Function2<SaverScope, BaselineShift, Object>() { // from class: androidx.compose.ui.text.SaversKt$BaselineShiftSaver$1
        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(SaverScope saverScope, BaselineShift baselineShift) {
            return m6442invoke8a2Sb4w(saverScope, baselineShift.m6806unboximpl());
        }

        /* JADX INFO: renamed from: invoke-8a2Sb4w, reason: not valid java name */
        public final Object m6442invoke8a2Sb4w(SaverScope saverScope, float f) {
            return Float.valueOf(f);
        }
    }, new Function1<Object, BaselineShift>() { // from class: androidx.compose.ui.text.SaversKt$BaselineShiftSaver$2
        @Override // kotlin.jvm.functions.Function1
        /* JADX INFO: renamed from: invoke-jTk7eUs, reason: not valid java name and merged with bridge method [inline-methods] */
        public final BaselineShift invoke(Object obj) {
            Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type kotlin.Float");
            return BaselineShift.m6800boximpl(BaselineShift.m6801constructorimpl(((Float) obj).floatValue()));
        }
    });
    private static final Saver<TextRange, Object> TextRangeSaver = SaverKt.Saver(new Function2<SaverScope, TextRange, Object>() { // from class: androidx.compose.ui.text.SaversKt$TextRangeSaver$1
        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(SaverScope saverScope, TextRange textRange) {
            return m6448invokeFDrldGo(saverScope, textRange.getPackedValue());
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX INFO: renamed from: invoke-FDrldGo, reason: not valid java name */
        public final Object m6448invokeFDrldGo(SaverScope saverScope, long j) {
            return CollectionsKt.arrayListOf(SaversKt.save(Integer.valueOf(TextRange.m6512getStartimpl(j))), SaversKt.save(Integer.valueOf(TextRange.m6507getEndimpl(j))));
        }
    }, new Function1<Object, TextRange>() { // from class: androidx.compose.ui.text.SaversKt$TextRangeSaver$2
        @Override // kotlin.jvm.functions.Function1
        /* JADX INFO: renamed from: invoke-VqIyPBM, reason: not valid java name and merged with bridge method [inline-methods] */
        public final TextRange invoke(Object obj) {
            Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type kotlin.collections.List<kotlin.Any>");
            List list = (List) obj;
            Object obj2 = list.get(0);
            Integer num = obj2 != null ? (Integer) obj2 : null;
            Intrinsics.checkNotNull(num);
            int iIntValue = num.intValue();
            Object obj3 = list.get(1);
            Integer num2 = obj3 != null ? (Integer) obj3 : null;
            Intrinsics.checkNotNull(num2);
            return TextRange.m6500boximpl(TextRangeKt.TextRange(iIntValue, num2.intValue()));
        }
    });
    private static final Saver<Shadow, Object> ShadowSaver = SaverKt.Saver(new Function2<SaverScope, Shadow, Object>() { // from class: androidx.compose.ui.text.SaversKt$ShadowSaver$1
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(SaverScope saverScope, Shadow shadow) {
            return CollectionsKt.arrayListOf(SaversKt.save(Color.m4558boximpl(shadow.getColor()), SaversKt.getSaver(Color.INSTANCE), saverScope), SaversKt.save(Offset.m4316boximpl(shadow.getOffset()), SaversKt.getSaver(Offset.INSTANCE), saverScope), SaversKt.save(Float.valueOf(shadow.getBlurRadius())));
        }
    }, new Function1<Object, Shadow>() { // from class: androidx.compose.ui.text.SaversKt$ShadowSaver$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function1
        public final Shadow invoke(Object obj) {
            Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type kotlin.collections.List<kotlin.Any>");
            List list = (List) obj;
            Object obj2 = list.get(0);
            Saver<Color, Object> saver = SaversKt.getSaver(Color.INSTANCE);
            Color colorRestore = ((!Intrinsics.areEqual(obj2, (Object) false) || (saver instanceof NonNullValueClassSaver)) && obj2 != null) ? saver.restore(obj2) : null;
            Intrinsics.checkNotNull(colorRestore);
            long jM4578unboximpl = colorRestore.m4578unboximpl();
            Object obj3 = list.get(1);
            Saver<Offset, Object> saver2 = SaversKt.getSaver(Offset.INSTANCE);
            Offset offsetRestore = ((!Intrinsics.areEqual(obj3, (Object) false) || (saver2 instanceof NonNullValueClassSaver)) && obj3 != null) ? saver2.restore(obj3) : null;
            Intrinsics.checkNotNull(offsetRestore);
            long packedValue = offsetRestore.getPackedValue();
            Object obj4 = list.get(2);
            Float f = obj4 != null ? (Float) obj4 : null;
            Intrinsics.checkNotNull(f);
            return new Shadow(jM4578unboximpl, packedValue, f.floatValue(), null);
        }
    });
    private static final NonNullValueClassSaver<Color, Object> ColorSaver = NonNullValueClassSaver(new Function2<SaverScope, Color, Object>() { // from class: androidx.compose.ui.text.SaversKt$ColorSaver$1
        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(SaverScope saverScope, Color color) {
            return m6444invoke4WTKRHQ(saverScope, color.m4578unboximpl());
        }

        /* JADX INFO: renamed from: invoke-4WTKRHQ, reason: not valid java name */
        public final Object m6444invoke4WTKRHQ(SaverScope saverScope, long j) {
            if (j == 16) {
                return false;
            }
            return Integer.valueOf(ColorKt.m4622toArgb8_81llA(j));
        }
    }, new Function1<Object, Color>() { // from class: androidx.compose.ui.text.SaversKt$ColorSaver$2
        @Override // kotlin.jvm.functions.Function1
        /* JADX INFO: renamed from: invoke-ijrfgN4, reason: not valid java name and merged with bridge method [inline-methods] */
        public final Color invoke(Object obj) {
            long jColor;
            if (Intrinsics.areEqual(obj, (Object) false)) {
                jColor = Color.INSTANCE.m4604getUnspecified0d7_KjU();
            } else {
                Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type kotlin.Int");
                jColor = ColorKt.Color(((Integer) obj).intValue());
            }
            return Color.m4558boximpl(jColor);
        }
    });
    private static final NonNullValueClassSaver<TextUnit, Object> TextUnitSaver = NonNullValueClassSaver(new Function2<SaverScope, TextUnit, Object>() { // from class: androidx.compose.ui.text.SaversKt$TextUnitSaver$1
        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(SaverScope saverScope, TextUnit textUnit) {
            return m6450invokempE4wyQ(saverScope, textUnit.getPackedValue());
        }

        /* JADX INFO: renamed from: invoke-mpE4wyQ, reason: not valid java name */
        public final Object m6450invokempE4wyQ(SaverScope saverScope, long j) {
            if (TextUnit.m7217equalsimpl0(j, TextUnit.INSTANCE.m7231getUnspecifiedXSAIIZE())) {
                return (Serializable) false;
            }
            return CollectionsKt.arrayListOf(SaversKt.save(Float.valueOf(TextUnit.m7220getValueimpl(j))), SaversKt.save(TextUnitType.m7245boximpl(TextUnit.m7219getTypeUIouoOA(j))));
        }
    }, new Function1<Object, TextUnit>() { // from class: androidx.compose.ui.text.SaversKt$TextUnitSaver$2
        @Override // kotlin.jvm.functions.Function1
        /* JADX INFO: renamed from: invoke-XNhUCwk, reason: not valid java name and merged with bridge method [inline-methods] */
        public final TextUnit invoke(Object obj) {
            if (Intrinsics.areEqual(obj, (Object) false)) {
                return TextUnit.m7210boximpl(TextUnit.INSTANCE.m7231getUnspecifiedXSAIIZE());
            }
            Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type kotlin.collections.List<kotlin.Any>");
            List list = (List) obj;
            Object obj2 = list.get(0);
            Float f = obj2 != null ? (Float) obj2 : null;
            Intrinsics.checkNotNull(f);
            float fFloatValue = f.floatValue();
            Object obj3 = list.get(1);
            TextUnitType textUnitType = obj3 != null ? (TextUnitType) obj3 : null;
            Intrinsics.checkNotNull(textUnitType);
            return TextUnit.m7210boximpl(TextUnitKt.m7232TextUnitanM5pPY(fFloatValue, textUnitType.getType()));
        }
    });
    private static final NonNullValueClassSaver<Offset, Object> OffsetSaver = NonNullValueClassSaver(new Function2<SaverScope, Offset, Object>() { // from class: androidx.compose.ui.text.SaversKt$OffsetSaver$1
        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(SaverScope saverScope, Offset offset) {
            return m6446invokeUv8p0NA(saverScope, offset.getPackedValue());
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX INFO: renamed from: invoke-Uv8p0NA, reason: not valid java name */
        public final Object m6446invokeUv8p0NA(SaverScope saverScope, long j) {
            return Offset.m4324equalsimpl0(j, Offset.INSTANCE.m4342getUnspecifiedF1C5BW0()) ? (Serializable) false : CollectionsKt.arrayListOf(SaversKt.save(Float.valueOf(Offset.m4327getXimpl(j))), SaversKt.save(Float.valueOf(Offset.m4328getYimpl(j))));
        }
    }, new Function1<Object, Offset>() { // from class: androidx.compose.ui.text.SaversKt$OffsetSaver$2
        @Override // kotlin.jvm.functions.Function1
        /* JADX INFO: renamed from: invoke-x-9fifI, reason: not valid java name and merged with bridge method [inline-methods] */
        public final Offset invoke(Object obj) {
            if (Intrinsics.areEqual(obj, (Object) false)) {
                return Offset.m4316boximpl(Offset.INSTANCE.m4342getUnspecifiedF1C5BW0());
            }
            Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type kotlin.collections.List<kotlin.Any?>");
            List list = (List) obj;
            Object obj2 = list.get(0);
            Float f = obj2 != null ? (Float) obj2 : null;
            Intrinsics.checkNotNull(f);
            float fFloatValue = f.floatValue();
            Object obj3 = list.get(1);
            Float f2 = obj3 != null ? (Float) obj3 : null;
            Intrinsics.checkNotNull(f2);
            return Offset.m4316boximpl(OffsetKt.Offset(fFloatValue, f2.floatValue()));
        }
    });
    private static final Saver<LocaleList, Object> LocaleListSaver = SaverKt.Saver(new Function2<SaverScope, LocaleList, Object>() { // from class: androidx.compose.ui.text.SaversKt$LocaleListSaver$1
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(SaverScope saverScope, LocaleList localeList) {
            List<Locale> localeList2 = localeList.getLocaleList();
            ArrayList arrayList = new ArrayList(localeList2.size());
            int size = localeList2.size();
            for (int i = 0; i < size; i++) {
                arrayList.add(SaversKt.save(localeList2.get(i), SaversKt.getSaver(Locale.INSTANCE), saverScope));
            }
            return arrayList;
        }
    }, new Function1<Object, LocaleList>() { // from class: androidx.compose.ui.text.SaversKt$LocaleListSaver$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function1
        public final LocaleList invoke(Object obj) {
            Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type kotlin.collections.List<kotlin.Any>");
            List list = (List) obj;
            ArrayList arrayList = new ArrayList(list.size());
            int size = list.size();
            for (int i = 0; i < size; i++) {
                Object obj2 = list.get(i);
                ArrayList arrayList2 = arrayList;
                Saver<Locale, Object> saver = SaversKt.getSaver(Locale.INSTANCE);
                Locale localeRestore = null;
                if ((!Intrinsics.areEqual(obj2, (Object) false) || (saver instanceof NonNullValueClassSaver)) && obj2 != null) {
                    localeRestore = saver.restore(obj2);
                }
                Intrinsics.checkNotNull(localeRestore);
                arrayList2.add(localeRestore);
            }
            return new LocaleList(arrayList);
        }
    });
    private static final Saver<Locale, Object> LocaleSaver = SaverKt.Saver(new Function2<SaverScope, Locale, Object>() { // from class: androidx.compose.ui.text.SaversKt$LocaleSaver$1
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(SaverScope saverScope, Locale locale) {
            return locale.toLanguageTag();
        }
    }, new Function1<Object, Locale>() { // from class: androidx.compose.ui.text.SaversKt$LocaleSaver$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function1
        public final Locale invoke(Object obj) {
            Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type kotlin.String");
            return new Locale((String) obj);
        }
    });

    private static /* synthetic */ void getAnnotationRangeSaver$annotations() {
    }

    private static /* synthetic */ void getUrlAnnotationSaver$annotations() {
    }

    public static final <T> T save(T t) {
        return t;
    }

    public static final <T extends Saver<Original, Saveable>, Original, Saveable> Object save(Original original, T t, SaverScope saverScope) {
        Object objSave;
        if (original == null || (objSave = t.save(saverScope, original)) == null) {
            return false;
        }
        return objSave;
    }

    public static final /* synthetic */ <T extends Saver<Original, Saveable>, Original, Saveable, Result> Result restore(Saveable saveable, T t) {
        if ((Intrinsics.areEqual((Object) saveable, (Object) false) && !(t instanceof NonNullValueClassSaver)) || saveable == null) {
            return null;
        }
        Result result = (Result) t.restore(saveable);
        Intrinsics.reifiedOperationMarker(1, "Result");
        return result;
    }

    private static final <Original, Saveable> NonNullValueClassSaver<Original, Saveable> NonNullValueClassSaver(final Function2<? super SaverScope, ? super Original, ? extends Saveable> function2, final Function1<? super Saveable, ? extends Original> function1) {
        return new NonNullValueClassSaver<Original, Saveable>() { // from class: androidx.compose.ui.text.SaversKt.NonNullValueClassSaver.1
            @Override // androidx.compose.runtime.saveable.Saver
            public Saveable save(SaverScope saverScope, Original original) {
                return function2.invoke(saverScope, original);
            }

            @Override // androidx.compose.runtime.saveable.Saver
            public Original restore(Saveable value) {
                return function1.invoke(value);
            }
        };
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final /* synthetic */ <Result> Result restore(Object obj) {
        if (obj == 0) {
            return null;
        }
        Intrinsics.reifiedOperationMarker(1, "Result");
        return obj;
    }

    public static final Saver<AnnotatedString, Object> getAnnotatedStringSaver() {
        return AnnotatedStringSaver;
    }

    public static final Saver<ParagraphStyle, Object> getParagraphStyleSaver() {
        return ParagraphStyleSaver;
    }

    public static final Saver<SpanStyle, Object> getSpanStyleSaver() {
        return SpanStyleSaver;
    }

    public static final Saver<TextLinkStyles, Object> getTextLinkStylesSaver() {
        return TextLinkStylesSaver;
    }

    public static final Saver<TextDecoration, Object> getSaver(TextDecoration.Companion companion) {
        return TextDecorationSaver;
    }

    public static final Saver<TextGeometricTransform, Object> getSaver(TextGeometricTransform.Companion companion) {
        return TextGeometricTransformSaver;
    }

    public static final Saver<TextIndent, Object> getSaver(TextIndent.Companion companion) {
        return TextIndentSaver;
    }

    public static final Saver<FontWeight, Object> getSaver(FontWeight.Companion companion) {
        return FontWeightSaver;
    }

    public static final Saver<BaselineShift, Object> getSaver(BaselineShift.Companion companion) {
        return BaselineShiftSaver;
    }

    public static final Saver<TextRange, Object> getSaver(TextRange.Companion companion) {
        return TextRangeSaver;
    }

    public static final Saver<Shadow, Object> getSaver(Shadow.Companion companion) {
        return ShadowSaver;
    }

    public static final Saver<Color, Object> getSaver(Color.Companion companion) {
        return ColorSaver;
    }

    public static final Saver<TextUnit, Object> getSaver(TextUnit.Companion companion) {
        return TextUnitSaver;
    }

    public static final Saver<Offset, Object> getSaver(Offset.Companion companion) {
        return OffsetSaver;
    }

    public static final Saver<LocaleList, Object> getSaver(LocaleList.Companion companion) {
        return LocaleListSaver;
    }

    public static final Saver<Locale, Object> getSaver(Locale.Companion companion) {
        return LocaleSaver;
    }
}
