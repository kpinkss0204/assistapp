package com.example.assistapp.features.LocationSharing;

import android.content.Context;
import androidx.activity.compose.ManagedActivityResultLauncher;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import java.util.Map;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: LocationSharingWithCodeScreen.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
@DebugMetadata(c = "com.example.assistapp.features.LocationSharing.LocationSharingWithCodeScreenKt$LocationSharingWithCodeScreen$1$1", f = "LocationSharingWithCodeScreen.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
final class LocationSharingWithCodeScreenKt$LocationSharingWithCodeScreen$1$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Context $context;
    final /* synthetic */ ManagedActivityResultLauncher<String[], Map<String, Boolean>> $permissionLauncher;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    LocationSharingWithCodeScreenKt$LocationSharingWithCodeScreen$1$1(Context context, ManagedActivityResultLauncher<String[], Map<String, Boolean>> managedActivityResultLauncher, Continuation<? super LocationSharingWithCodeScreenKt$LocationSharingWithCodeScreen$1$1> continuation) {
        super(2, continuation);
        this.$context = context;
        this.$permissionLauncher = managedActivityResultLauncher;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new LocationSharingWithCodeScreenKt$LocationSharingWithCodeScreen$1$1(this.$context, this.$permissionLauncher, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((LocationSharingWithCodeScreenKt$LocationSharingWithCodeScreen$1$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        if (ContextCompat.checkSelfPermission(this.$context, "android.permission.ACCESS_FINE_LOCATION") != 0) {
            this.$permissionLauncher.launch(new String[]{"android.permission.ACCESS_FINE_LOCATION", "android.permission.ACCESS_COARSE_LOCATION"});
        }
        return Unit.INSTANCE;
    }
}
