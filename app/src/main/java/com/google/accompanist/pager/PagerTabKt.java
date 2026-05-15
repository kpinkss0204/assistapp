package com.google.accompanist.pager;

import androidx.compose.material.TabPosition;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.layout.LayoutModifierKt;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.ConstraintsKt;
import androidx.compose.ui.unit.DpKt;
import androidx.constraintlayout.widget.ConstraintLayout;
import java.util.List;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: PagerTab.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000*\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a8\u0010\u0000\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\u0014\b\u0002\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\t0\bH\u0007\u001a8\u0010\u0000\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\n2\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\u0014\b\u0002\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\t0\bH\u0007\u001a8\u0010\u0000\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u000b2\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\u0014\b\u0002\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\t0\bH\u0002¨\u0006\f"}, d2 = {"pagerTabIndicatorOffset", "Landroidx/compose/ui/Modifier;", "pagerState", "Landroidx/compose/foundation/pager/PagerState;", "tabPositions", "", "Landroidx/compose/material/TabPosition;", "pageIndexMapping", "Lkotlin/Function1;", "", "Lcom/google/accompanist/pager/PagerState;", "Lcom/google/accompanist/pager/PagerStateBridge;", "pager-indicators_release"}, k = 2, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
public final class PagerTabKt {
    public static /* synthetic */ Modifier pagerTabIndicatorOffset$default(Modifier modifier, PagerState pagerState, List list, Function1 function1, int i, Object obj) {
        if ((i & 4) != 0) {
            function1 = new Function1<Integer, Integer>() { // from class: com.google.accompanist.pager.PagerTabKt.pagerTabIndicatorOffset.1
                public final Integer invoke(int i2) {
                    return Integer.valueOf(i2);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Integer invoke(Integer num) {
                    return invoke(num.intValue());
                }
            };
        }
        return pagerTabIndicatorOffset(modifier, pagerState, (List<TabPosition>) list, (Function1<? super Integer, Integer>) function1);
    }

    @Deprecated(message = "\n   pagerTabIndicatorOffset for accompanist Pagers are deprecated, please use the version that takes \n   androidx.compose.foundation.pager.PagerState instead\nFor more migration information, please visit https://google.github.io/accompanist/pager/#migration\n")
    public static final Modifier pagerTabIndicatorOffset(Modifier modifier, final PagerState pagerState, List<TabPosition> tabPositions, Function1<? super Integer, Integer> pageIndexMapping) {
        Intrinsics.checkNotNullParameter(modifier, "<this>");
        Intrinsics.checkNotNullParameter(pagerState, "pagerState");
        Intrinsics.checkNotNullParameter(tabPositions, "tabPositions");
        Intrinsics.checkNotNullParameter(pageIndexMapping, "pageIndexMapping");
        return pagerTabIndicatorOffset(modifier, new PagerStateBridge() { // from class: com.google.accompanist.pager.PagerTabKt$pagerTabIndicatorOffset$stateBridge$1
            @Override // com.google.accompanist.pager.PagerStateBridge
            public int getCurrentPage() {
                return pagerState.getCurrentPage();
            }

            @Override // com.google.accompanist.pager.PagerStateBridge
            public float getCurrentPageOffset() {
                return pagerState.getCurrentPageOffset();
            }
        }, tabPositions, pageIndexMapping);
    }

    public static /* synthetic */ Modifier pagerTabIndicatorOffset$default(Modifier modifier, androidx.compose.foundation.pager.PagerState pagerState, List list, Function1 function1, int i, Object obj) {
        if ((i & 4) != 0) {
            function1 = new Function1<Integer, Integer>() { // from class: com.google.accompanist.pager.PagerTabKt.pagerTabIndicatorOffset.2
                public final Integer invoke(int i2) {
                    return Integer.valueOf(i2);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Integer invoke(Integer num) {
                    return invoke(num.intValue());
                }
            };
        }
        return pagerTabIndicatorOffset(modifier, pagerState, (List<TabPosition>) list, (Function1<? super Integer, Integer>) function1);
    }

    public static final Modifier pagerTabIndicatorOffset(Modifier modifier, final androidx.compose.foundation.pager.PagerState pagerState, List<TabPosition> tabPositions, Function1<? super Integer, Integer> pageIndexMapping) {
        Intrinsics.checkNotNullParameter(modifier, "<this>");
        Intrinsics.checkNotNullParameter(pagerState, "pagerState");
        Intrinsics.checkNotNullParameter(tabPositions, "tabPositions");
        Intrinsics.checkNotNullParameter(pageIndexMapping, "pageIndexMapping");
        return pagerTabIndicatorOffset(modifier, new PagerStateBridge() { // from class: com.google.accompanist.pager.PagerTabKt$pagerTabIndicatorOffset$stateBridge$2
            @Override // com.google.accompanist.pager.PagerStateBridge
            public int getCurrentPage() {
                return pagerState.getCurrentPage();
            }

            @Override // com.google.accompanist.pager.PagerStateBridge
            public float getCurrentPageOffset() {
                return pagerState.getCurrentPageOffsetFraction();
            }
        }, tabPositions, pageIndexMapping);
    }

    static /* synthetic */ Modifier pagerTabIndicatorOffset$default(Modifier modifier, PagerStateBridge pagerStateBridge, List list, Function1 function1, int i, Object obj) {
        if ((i & 4) != 0) {
            function1 = new Function1<Integer, Integer>() { // from class: com.google.accompanist.pager.PagerTabKt.pagerTabIndicatorOffset.3
                public final Integer invoke(int i2) {
                    return Integer.valueOf(i2);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Integer invoke(Integer num) {
                    return invoke(num.intValue());
                }
            };
        }
        return pagerTabIndicatorOffset(modifier, pagerStateBridge, (List<TabPosition>) list, (Function1<? super Integer, Integer>) function1);
    }

    private static final Modifier pagerTabIndicatorOffset(Modifier modifier, final PagerStateBridge pagerStateBridge, final List<TabPosition> list, final Function1<? super Integer, Integer> function1) {
        return LayoutModifierKt.layout(modifier, new Function3<MeasureScope, Measurable, Constraints, MeasureResult>() { // from class: com.google.accompanist.pager.PagerTabKt.pagerTabIndicatorOffset.4
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(3);
            }

            @Override // kotlin.jvm.functions.Function3
            public /* bridge */ /* synthetic */ MeasureResult invoke(MeasureScope measureScope, Measurable measurable, Constraints constraints) {
                return m7563invoke3p2s80s(measureScope, measurable, constraints.getValue());
            }

            /* JADX INFO: renamed from: invoke-3p2s80s, reason: not valid java name */
            public final MeasureResult m7563invoke3p2s80s(MeasureScope layout, Measurable measurable, final long j) {
                int i;
                final int i2;
                Intrinsics.checkNotNullParameter(layout, "$this$layout");
                Intrinsics.checkNotNullParameter(measurable, "measurable");
                if (list.isEmpty()) {
                    return MeasureScope.layout$default(layout, Constraints.m6980getMaxWidthimpl(j), 0, null, new Function1<Placeable.PlacementScope, Unit>() { // from class: com.google.accompanist.pager.PagerTabKt.pagerTabIndicatorOffset.4.1
                        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(Placeable.PlacementScope layout2) {
                            Intrinsics.checkNotNullParameter(layout2, "$this$layout");
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(Placeable.PlacementScope placementScope) {
                            invoke2(placementScope);
                            return Unit.INSTANCE;
                        }
                    }, 4, null);
                }
                int iMin = Math.min(CollectionsKt.getLastIndex(list), function1.invoke(Integer.valueOf(pagerStateBridge.getCurrentPage())).intValue());
                TabPosition tabPosition = list.get(iMin);
                TabPosition tabPosition2 = (TabPosition) CollectionsKt.getOrNull(list, iMin - 1);
                TabPosition tabPosition3 = (TabPosition) CollectionsKt.getOrNull(list, iMin + 1);
                float currentPageOffset = pagerStateBridge.getCurrentPageOffset();
                if (currentPageOffset > 0.0f && tabPosition3 != null) {
                    i = layout.mo651roundToPx0680j_4(DpKt.m7070lerpMdfbLM(tabPosition.getWidth(), tabPosition3.getWidth(), currentPageOffset));
                } else if (currentPageOffset < 0.0f && tabPosition2 != null) {
                    i = layout.mo651roundToPx0680j_4(DpKt.m7070lerpMdfbLM(tabPosition.getWidth(), tabPosition2.getWidth(), -currentPageOffset));
                } else {
                    i = layout.mo651roundToPx0680j_4(tabPosition.getWidth());
                }
                if (currentPageOffset > 0.0f && tabPosition3 != null) {
                    i2 = layout.mo651roundToPx0680j_4(DpKt.m7070lerpMdfbLM(tabPosition.getLeft(), tabPosition3.getLeft(), currentPageOffset));
                } else if (currentPageOffset < 0.0f && tabPosition2 != null) {
                    i2 = layout.mo651roundToPx0680j_4(DpKt.m7070lerpMdfbLM(tabPosition.getLeft(), tabPosition2.getLeft(), -currentPageOffset));
                } else {
                    i2 = layout.mo651roundToPx0680j_4(tabPosition.getLeft());
                }
                final Placeable placeableMo5903measureBRTryo0 = measurable.mo5903measureBRTryo0(ConstraintsKt.Constraints(i, i, 0, Constraints.m6979getMaxHeightimpl(j)));
                return MeasureScope.layout$default(layout, Constraints.m6980getMaxWidthimpl(j), Math.max(placeableMo5903measureBRTryo0.getHeight(), Constraints.m6981getMinHeightimpl(j)), null, new Function1<Placeable.PlacementScope, Unit>() { // from class: com.google.accompanist.pager.PagerTabKt.pagerTabIndicatorOffset.4.2
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
                    public final void invoke2(Placeable.PlacementScope layout2) {
                        Intrinsics.checkNotNullParameter(layout2, "$this$layout");
                        Placeable.PlacementScope.placeRelative$default(layout2, placeableMo5903measureBRTryo0, i2, Math.max(Constraints.m6981getMinHeightimpl(j) - placeableMo5903measureBRTryo0.getHeight(), 0), 0.0f, 4, null);
                    }
                }, 4, null);
            }
        });
    }
}
