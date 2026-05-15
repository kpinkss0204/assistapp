package com.google.android.gms.libs.identity;

import android.location.Location;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.location.LocationListener;

/* JADX INFO: compiled from: com.google.android.gms:play-services-location@@21.1.0 */
/* JADX INFO: loaded from: classes3.dex */
final class zzdr implements ListenerHolder.Notifier {
    final /* synthetic */ Location zza;

    zzdr(zzdt zzdtVar, Location location) {
        this.zza = location;
    }

    @Override // com.google.android.gms.common.api.internal.ListenerHolder.Notifier
    public final /* bridge */ /* synthetic */ void notifyListener(Object obj) {
        ((LocationListener) obj).onLocationChanged(this.zza);
    }

    @Override // com.google.android.gms.common.api.internal.ListenerHolder.Notifier
    public final void onNotifyListenerFailed() {
    }
}
