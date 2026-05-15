package androidx.compose.ui.draw;

import androidx.compose.ui.geometry.SizeKt;
import androidx.compose.ui.graphics.Canvas;
import androidx.compose.ui.graphics.GraphicsContext;
import androidx.compose.ui.graphics.drawscope.ContentDrawScope;
import androidx.compose.ui.graphics.drawscope.DrawContext;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.graphics.layer.GraphicsLayer;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.IntSize;
import androidx.compose.ui.unit.IntSizeKt;
import androidx.compose.ui.unit.LayoutDirection;
import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: DrawModifier.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000l\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u00002\u00020\u0001B\u0007\b\u0000¢\u0006\u0002\u0010\u0002J\u0006\u0010*\u001a\u00020+J\u001f\u0010,\u001a\u00020\u00142\u0017\u0010-\u001a\u0013\u0012\u0004\u0012\u00020/\u0012\u0004\u0012\u0002000.¢\u0006\u0002\b1J\u001f\u00102\u001a\u00020\u00142\u0017\u0010-\u001a\u0013\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u0002000.¢\u0006\u0002\b1JK\u00103\u001a\u000200*\u00020+2\b\b\u0002\u0010\u000f\u001a\u00020\u00012\b\b\u0002\u0010\"\u001a\u00020#2\b\b\u0002\u0010&\u001a\u0002042\u0017\u0010-\u001a\u0013\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u0002000.¢\u0006\u0002\b1ø\u0001\u0000¢\u0006\u0004\b5\u00106R\u001a\u0010\u0003\u001a\u00020\u0004X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001c\u0010\t\u001a\u0004\u0018\u00010\nX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u0014\u0010\u000f\u001a\u00020\u00108VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u0012R\u001c\u0010\u0013\u001a\u0004\u0018\u00010\u0014X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R\u0014\u0010\u0019\u001a\u00020\u00108VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u001a\u0010\u0012R\"\u0010\u001b\u001a\n\u0012\u0004\u0012\u00020\u001d\u0018\u00010\u001cX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u001f\"\u0004\b \u0010!R\u0011\u0010\"\u001a\u00020#8F¢\u0006\u0006\u001a\u0004\b$\u0010%R\u0017\u0010&\u001a\u00020'8Fø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\u001a\u0004\b(\u0010)\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u00067"}, d2 = {"Landroidx/compose/ui/draw/CacheDrawScope;", "Landroidx/compose/ui/unit/Density;", "()V", "cacheParams", "Landroidx/compose/ui/draw/BuildDrawCacheParams;", "getCacheParams$ui_release", "()Landroidx/compose/ui/draw/BuildDrawCacheParams;", "setCacheParams$ui_release", "(Landroidx/compose/ui/draw/BuildDrawCacheParams;)V", "contentDrawScope", "Landroidx/compose/ui/graphics/drawscope/ContentDrawScope;", "getContentDrawScope$ui_release", "()Landroidx/compose/ui/graphics/drawscope/ContentDrawScope;", "setContentDrawScope$ui_release", "(Landroidx/compose/ui/graphics/drawscope/ContentDrawScope;)V", "density", "", "getDensity", "()F", "drawResult", "Landroidx/compose/ui/draw/DrawResult;", "getDrawResult$ui_release", "()Landroidx/compose/ui/draw/DrawResult;", "setDrawResult$ui_release", "(Landroidx/compose/ui/draw/DrawResult;)V", "fontScale", "getFontScale", "graphicsContextProvider", "Lkotlin/Function0;", "Landroidx/compose/ui/graphics/GraphicsContext;", "getGraphicsContextProvider$ui_release", "()Lkotlin/jvm/functions/Function0;", "setGraphicsContextProvider$ui_release", "(Lkotlin/jvm/functions/Function0;)V", "layoutDirection", "Landroidx/compose/ui/unit/LayoutDirection;", "getLayoutDirection", "()Landroidx/compose/ui/unit/LayoutDirection;", "size", "Landroidx/compose/ui/geometry/Size;", "getSize-NH-jbRc", "()J", "obtainGraphicsLayer", "Landroidx/compose/ui/graphics/layer/GraphicsLayer;", "onDrawBehind", "block", "Lkotlin/Function1;", "Landroidx/compose/ui/graphics/drawscope/DrawScope;", "", "Lkotlin/ExtensionFunctionType;", "onDrawWithContent", "record", "Landroidx/compose/ui/unit/IntSize;", "record-TdoYBX4", "(Landroidx/compose/ui/graphics/layer/GraphicsLayer;Landroidx/compose/ui/unit/Density;Landroidx/compose/ui/unit/LayoutDirection;JLkotlin/jvm/functions/Function1;)V", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
public final class CacheDrawScope implements Density {
    public static final int $stable = 0;
    private BuildDrawCacheParams cacheParams = EmptyBuildDrawCacheParams.INSTANCE;
    private ContentDrawScope contentDrawScope;
    private DrawResult drawResult;
    private Function0<? extends GraphicsContext> graphicsContextProvider;

    /* JADX INFO: renamed from: getCacheParams$ui_release, reason: from getter */
    public final BuildDrawCacheParams getCacheParams() {
        return this.cacheParams;
    }

    public final void setCacheParams$ui_release(BuildDrawCacheParams buildDrawCacheParams) {
        this.cacheParams = buildDrawCacheParams;
    }

    /* JADX INFO: renamed from: getDrawResult$ui_release, reason: from getter */
    public final DrawResult getDrawResult() {
        return this.drawResult;
    }

    public final void setDrawResult$ui_release(DrawResult drawResult) {
        this.drawResult = drawResult;
    }

    /* JADX INFO: renamed from: getContentDrawScope$ui_release, reason: from getter */
    public final ContentDrawScope getContentDrawScope() {
        return this.contentDrawScope;
    }

    public final void setContentDrawScope$ui_release(ContentDrawScope contentDrawScope) {
        this.contentDrawScope = contentDrawScope;
    }

    public final Function0<GraphicsContext> getGraphicsContextProvider$ui_release() {
        return this.graphicsContextProvider;
    }

    public final void setGraphicsContextProvider$ui_release(Function0<? extends GraphicsContext> function0) {
        this.graphicsContextProvider = function0;
    }

    /* JADX INFO: renamed from: getSize-NH-jbRc, reason: not valid java name */
    public final long m4217getSizeNHjbRc() {
        return this.cacheParams.mo4215getSizeNHjbRc();
    }

    public final LayoutDirection getLayoutDirection() {
        return this.cacheParams.getLayoutDirection();
    }

    public final GraphicsLayer obtainGraphicsLayer() {
        Function0<? extends GraphicsContext> function0 = this.graphicsContextProvider;
        Intrinsics.checkNotNull(function0);
        return function0.invoke().createGraphicsLayer();
    }

    /* JADX INFO: renamed from: record-TdoYBX4$default, reason: not valid java name */
    public static /* synthetic */ void m4216recordTdoYBX4$default(CacheDrawScope cacheDrawScope, GraphicsLayer graphicsLayer, Density density, LayoutDirection layoutDirection, long j, Function1 function1, int i, Object obj) {
        if ((i & 1) != 0) {
            density = cacheDrawScope;
        }
        Density density2 = density;
        if ((i & 2) != 0) {
            layoutDirection = cacheDrawScope.getLayoutDirection();
        }
        LayoutDirection layoutDirection2 = layoutDirection;
        if ((i & 4) != 0) {
            j = IntSizeKt.m7208toIntSizeuvyYCjk(cacheDrawScope.m4217getSizeNHjbRc());
        }
        cacheDrawScope.m4218recordTdoYBX4(graphicsLayer, density2, layoutDirection2, j, function1);
    }

    /* JADX INFO: renamed from: record-TdoYBX4, reason: not valid java name */
    public final void m4218recordTdoYBX4(GraphicsLayer graphicsLayer, final Density density, final LayoutDirection layoutDirection, final long j, final Function1<? super ContentDrawScope, Unit> function1) {
        graphicsLayer.m5218recordmLhObY(density, layoutDirection, j, new Function1<DrawScope, Unit>() { // from class: androidx.compose.ui.draw.CacheDrawScope$record$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
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
                ContentDrawScope contentDrawScope = this.this$0.getContentDrawScope();
                Intrinsics.checkNotNull(contentDrawScope);
                Density density2 = density;
                LayoutDirection layoutDirection2 = layoutDirection;
                long j2 = j;
                Function1<ContentDrawScope, Unit> function12 = function1;
                Canvas canvas = drawScope.getDrawContext().getCanvas();
                ContentDrawScope contentDrawScope2 = contentDrawScope;
                long jSize = SizeKt.Size(IntSize.m7197getWidthimpl(j2), IntSize.m7196getHeightimpl(j2));
                Density density3 = contentDrawScope2.getDrawContext().getDensity();
                LayoutDirection layoutDirection3 = contentDrawScope2.getDrawContext().getLayoutDirection();
                Canvas canvas2 = contentDrawScope2.getDrawContext().getCanvas();
                long jMo5045getSizeNHjbRc = contentDrawScope2.getDrawContext().mo5045getSizeNHjbRc();
                GraphicsLayer graphicsLayer2 = contentDrawScope2.getDrawContext().getGraphicsLayer();
                DrawContext drawContext = contentDrawScope2.getDrawContext();
                drawContext.setDensity(density2);
                drawContext.setLayoutDirection(layoutDirection2);
                drawContext.setCanvas(canvas);
                drawContext.mo5046setSizeuvyYCjk(jSize);
                drawContext.setGraphicsLayer(null);
                canvas.save();
                try {
                    function12.invoke(contentDrawScope);
                } finally {
                    canvas.restore();
                    DrawContext drawContext2 = contentDrawScope2.getDrawContext();
                    drawContext2.setDensity(density3);
                    drawContext2.setLayoutDirection(layoutDirection3);
                    drawContext2.setCanvas(canvas2);
                    drawContext2.mo5046setSizeuvyYCjk(jMo5045getSizeNHjbRc);
                    drawContext2.setGraphicsLayer(graphicsLayer2);
                }
            }
        });
    }

    public final DrawResult onDrawBehind(final Function1<? super DrawScope, Unit> block) {
        return onDrawWithContent(new Function1<ContentDrawScope, Unit>() { // from class: androidx.compose.ui.draw.CacheDrawScope.onDrawBehind.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(ContentDrawScope contentDrawScope) {
                invoke2(contentDrawScope);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(ContentDrawScope contentDrawScope) {
                block.invoke(contentDrawScope);
                contentDrawScope.drawContent();
            }
        });
    }

    public final DrawResult onDrawWithContent(Function1<? super ContentDrawScope, Unit> block) {
        DrawResult drawResult = new DrawResult(block);
        this.drawResult = drawResult;
        return drawResult;
    }

    @Override // androidx.compose.ui.unit.Density
    public float getDensity() {
        return this.cacheParams.getDensity().getDensity();
    }

    @Override // androidx.compose.ui.unit.FontScaling
    public float getFontScale() {
        return this.cacheParams.getDensity().getFontScale();
    }
}
