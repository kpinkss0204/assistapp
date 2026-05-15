package com.example.assistapp.ui.components;

import android.R;
import androidx.compose.runtime.MutableState;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.kakao.vectormap.KakaoMap;
import com.kakao.vectormap.LatLng;
import com.kakao.vectormap.camera.CameraUpdateFactory;
import com.kakao.vectormap.label.LabelLayer;
import com.kakao.vectormap.label.LabelManager;
import com.kakao.vectormap.label.LabelOptions;
import com.kakao.vectormap.label.LabelStyle;
import com.kakao.vectormap.label.LabelStyles;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: KakaoMapViewCompose.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
@DebugMetadata(c = "com.example.assistapp.ui.components.KakaoMapViewComposeKt$KakaoMapViewCompose$2$1", f = "KakaoMapViewCompose.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
final class KakaoMapViewComposeKt$KakaoMapViewCompose$2$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ MutableState<KakaoMap> $kakaoMapInstance$delegate;
    final /* synthetic */ double $lat;
    final /* synthetic */ double $lon;
    final /* synthetic */ int $zoom;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    KakaoMapViewComposeKt$KakaoMapViewCompose$2$1(MutableState<KakaoMap> mutableState, double d, double d2, int i, Continuation<? super KakaoMapViewComposeKt$KakaoMapViewCompose$2$1> continuation) {
        super(2, continuation);
        this.$kakaoMapInstance$delegate = mutableState;
        this.$lat = d;
        this.$lon = d2;
        this.$zoom = i;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new KakaoMapViewComposeKt$KakaoMapViewCompose$2$1(this.$kakaoMapInstance$delegate, this.$lat, this.$lon, this.$zoom, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((KakaoMapViewComposeKt$KakaoMapViewCompose$2$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        LabelLayer layer;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            KakaoMap kakaoMapKakaoMapViewCompose$lambda$1 = KakaoMapViewComposeKt.KakaoMapViewCompose$lambda$1(this.$kakaoMapInstance$delegate);
            if (kakaoMapKakaoMapViewCompose$lambda$1 != null) {
                double d = this.$lat;
                double d2 = this.$lon;
                int i = this.$zoom;
                LatLng latLngFrom = LatLng.from(d, d2);
                Intrinsics.checkNotNullExpressionValue(latLngFrom, "from(...)");
                kakaoMapKakaoMapViewCompose$lambda$1.moveCamera(CameraUpdateFactory.newCenterPosition(latLngFrom, i));
                LabelManager labelManager = kakaoMapKakaoMapViewCompose$lambda$1.getLabelManager();
                if (labelManager != null && (layer = labelManager.getLayer()) != null) {
                    layer.removeAll();
                    layer.addLabel(LabelOptions.from(latLngFrom).setStyles(LabelStyles.from(LabelStyle.from(R.drawable.ic_menu_mylocation))));
                }
            }
            return Unit.INSTANCE;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
