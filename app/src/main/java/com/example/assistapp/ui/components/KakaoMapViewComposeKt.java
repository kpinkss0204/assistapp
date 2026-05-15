package com.example.assistapp.ui.components;

import android.R;
import android.content.Context;
import android.widget.Toast;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.ui.Modifier;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.kakao.vectormap.KakaoMap;
import com.kakao.vectormap.KakaoMapReadyCallback;
import com.kakao.vectormap.LatLng;
import com.kakao.vectormap.MapLifeCycleCallback;
import com.kakao.vectormap.MapView;
import com.kakao.vectormap.label.LabelLayer;
import com.kakao.vectormap.label.LabelManager;
import com.kakao.vectormap.label.LabelOptions;
import com.kakao.vectormap.label.LabelStyle;
import com.kakao.vectormap.label.LabelStyles;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: KakaoMapViewCompose.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000\"\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\u001a1\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\bH\u0007¢\u0006\u0002\u0010\t¨\u0006\n²\u0006\f\u0010\u000b\u001a\u0004\u0018\u00010\fX\u008a\u008e\u0002"}, d2 = {"KakaoMapViewCompose", "", "lat", "", "lon", "zoom", "", "modifier", "Landroidx/compose/ui/Modifier;", "(DDILandroidx/compose/ui/Modifier;Landroidx/compose/runtime/Composer;II)V", "app_release", "kakaoMapInstance", "Lcom/kakao/vectormap/KakaoMap;"}, k = 2, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
public final class KakaoMapViewComposeKt {
    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit KakaoMapViewCompose$lambda$7(double d, double d2, int i, Modifier modifier, int i2, int i3, Composer composer, int i4) {
        KakaoMapViewCompose(d, d2, i, modifier, composer, RecomposeScopeImplKt.updateChangedFlags(i2 | 1), i3);
        return Unit.INSTANCE;
    }

    /* JADX WARN: Removed duplicated region for block: B:103:0x01bf  */
    /* JADX WARN: Removed duplicated region for block: B:105:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0068  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x006b  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x0087  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x0095  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x0097  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x009c  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x00a0  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x00a5  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x00ac  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x00e1  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x0104  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x0107  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x0110  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x0113  */
    /* JADX WARN: Removed duplicated region for block: B:73:0x011f  */
    /* JADX WARN: Removed duplicated region for block: B:74:0x0122  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x013c  */
    /* JADX WARN: Removed duplicated region for block: B:84:0x0172  */
    /* JADX WARN: Removed duplicated region for block: B:85:0x0175  */
    /* JADX WARN: Removed duplicated region for block: B:87:0x0179  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x017c  */
    /* JADX WARN: Removed duplicated region for block: B:91:0x0181  */
    /* JADX WARN: Removed duplicated region for block: B:96:0x0193  */
    /* JADX WARN: Removed duplicated region for block: B:99:0x01b4  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final void KakaoMapViewCompose(final double r22, final double r24, int r26, androidx.compose.ui.Modifier r27, androidx.compose.runtime.Composer r28, final int r29, final int r30) {
        /*
            Method dump skipped, instruction units count: 462
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.example.assistapp.ui.components.KakaoMapViewComposeKt.KakaoMapViewCompose(double, double, int, androidx.compose.ui.Modifier, androidx.compose.runtime.Composer, int, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final KakaoMap KakaoMapViewCompose$lambda$1(MutableState<KakaoMap> mutableState) {
        return mutableState.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final MapView KakaoMapViewCompose$lambda$5$lambda$4(final Context context, final double d, final double d2, final MutableState mutableState, final int i, Context ctx) {
        Intrinsics.checkNotNullParameter(ctx, "ctx");
        MapView mapView = new MapView(ctx);
        mapView.start(new MapLifeCycleCallback() { // from class: com.example.assistapp.ui.components.KakaoMapViewComposeKt$KakaoMapViewCompose$1$1$1$1
            @Override // com.kakao.vectormap.MapLifeCycleCallback
            public void onMapDestroy() {
                Toast.makeText(context, "지도 종료", 0).show();
            }

            @Override // com.kakao.vectormap.MapLifeCycleCallback
            public void onMapError(Exception error) {
                Intrinsics.checkNotNullParameter(error, "error");
                Toast.makeText(context, "지도 오류: " + error.getMessage(), 1).show();
            }
        }, new KakaoMapReadyCallback() { // from class: com.example.assistapp.ui.components.KakaoMapViewComposeKt$KakaoMapViewCompose$1$1$1$2
            @Override // com.kakao.vectormap.KakaoMapReadyCallback
            public void onMapReady(KakaoMap kakaoMap) {
                LabelLayer layer;
                Intrinsics.checkNotNullParameter(kakaoMap, "kakaoMap");
                mutableState.setValue(kakaoMap);
                Toast.makeText(context, "지도 로드 완료!", 0).show();
                LatLng latLngFrom = LatLng.from(d, d2);
                Intrinsics.checkNotNullExpressionValue(latLngFrom, "from(...)");
                LabelManager labelManager = kakaoMap.getLabelManager();
                if (labelManager == null || (layer = labelManager.getLayer()) == null) {
                    return;
                }
                layer.addLabel(LabelOptions.from(latLngFrom).setStyles(LabelStyles.from(LabelStyle.from(R.drawable.ic_menu_mylocation))));
            }

            @Override // com.kakao.vectormap.KakaoMapReadyCallback, com.kakao.vectormap.MapReadyCallback
            public LatLng getPosition() {
                LatLng latLngFrom = LatLng.from(d, d2);
                Intrinsics.checkNotNullExpressionValue(latLngFrom, "from(...)");
                return latLngFrom;
            }

            @Override // com.kakao.vectormap.KakaoMapReadyCallback, com.kakao.vectormap.MapReadyCallback
            /* JADX INFO: renamed from: getZoomLevel, reason: from getter */
            public int get$zoom() {
                return i;
            }
        });
        return mapView;
    }
}
