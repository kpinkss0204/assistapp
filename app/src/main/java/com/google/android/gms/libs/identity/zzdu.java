package com.google.android.gms.libs.identity;

import android.app.PendingIntent;
import android.content.Context;
import android.location.Location;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import android.os.RemoteException;
import androidx.collection.SimpleArrayMap;
import com.google.android.gms.common.Feature;
import com.google.android.gms.common.api.internal.ConnectionCallbacks;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.common.api.internal.ListenerHolders;
import com.google.android.gms.common.api.internal.OnConnectionFailedListener;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.common.internal.GmsClient;
import com.google.android.gms.common.internal.ICancelToken;
import com.google.android.gms.location.CurrentLocationRequest;
import com.google.android.gms.location.DeviceOrientationRequest;
import com.google.android.gms.location.GeofencingRequest;
import com.google.android.gms.location.LastLocationRequest;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.zzad;
import com.google.android.gms.location.zzo;
import com.google.android.gms.tasks.CancellationToken;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnTokenCanceledListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import java.util.Objects;

/* JADX INFO: compiled from: com.google.android.gms:play-services-location@@21.1.0 */
/* JADX INFO: loaded from: classes3.dex */
public final class zzdu extends GmsClient {
    public static final /* synthetic */ int zze = 0;
    private final SimpleArrayMap zzf;
    private final SimpleArrayMap zzg;
    private final SimpleArrayMap zzh;
    private final SimpleArrayMap zzi;

    public zzdu(Context context, Looper looper, ClientSettings clientSettings, ConnectionCallbacks connectionCallbacks, OnConnectionFailedListener onConnectionFailedListener) {
        super(context, looper, 23, clientSettings, connectionCallbacks, onConnectionFailedListener);
        this.zzf = new SimpleArrayMap();
        this.zzg = new SimpleArrayMap();
        this.zzh = new SimpleArrayMap();
        this.zzi = new SimpleArrayMap();
    }

    private final boolean zzG(Feature feature) {
        Feature feature2;
        Feature[] availableFeatures = getAvailableFeatures();
        if (availableFeatures != null) {
            int i = 0;
            while (true) {
                if (i >= availableFeatures.length) {
                    feature2 = null;
                    break;
                }
                feature2 = availableFeatures[i];
                if (feature.getName().equals(feature2.getName())) {
                    break;
                }
                i++;
            }
            if (feature2 != null && feature2.getVersion() >= feature.getVersion()) {
                return true;
            }
        }
        return false;
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient
    protected final /* synthetic */ IInterface createServiceInterface(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
        return iInterfaceQueryLocalInterface instanceof zzv ? (zzv) iInterfaceQueryLocalInterface : new zzu(iBinder);
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient
    public final Feature[] getApiFeatures() {
        return zzo.zzo;
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient, com.google.android.gms.common.api.Api.Client
    public final int getMinApkVersion() {
        return 11717000;
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient
    protected final String getServiceDescriptor() {
        return "com.google.android.gms.location.internal.IGoogleLocationManagerService";
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient
    protected final String getStartServiceAction() {
        return "com.google.android.location.internal.GoogleLocationManagerService.START";
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient
    public final void onConnectionSuspended(int i) {
        super.onConnectionSuspended(i);
        synchronized (this.zzf) {
            this.zzf.clear();
        }
        synchronized (this.zzg) {
            this.zzg.clear();
        }
        synchronized (this.zzh) {
            this.zzh.clear();
        }
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient
    public final boolean usesClientTelemetry() {
        return true;
    }

    public final void zzA(Location location, TaskCompletionSource taskCompletionSource) throws RemoteException {
        if (zzG(zzo.zzh)) {
            ((zzv) getService()).zzB(location, new zzde(null, taskCompletionSource));
        } else {
            ((zzv) getService()).zzA(location);
            taskCompletionSource.setResult(null);
        }
    }

    public final void zzB(TaskCompletionSource taskCompletionSource) throws RemoteException {
        ((zzv) getService()).zzC(new zzdi(null, taskCompletionSource));
    }

    public final void zzC(ListenerHolder listenerHolder, DeviceOrientationRequest deviceOrientationRequest, TaskCompletionSource taskCompletionSource) throws RemoteException {
        ListenerHolder.ListenerKey listenerKey = (ListenerHolder.ListenerKey) Objects.requireNonNull(listenerHolder.getListenerKey());
        synchronized (this.zzh) {
            zzdl zzdlVar = (zzdl) this.zzh.get(listenerKey);
            if (zzdlVar == null) {
                zzdlVar = new zzdl(listenerHolder);
                this.zzh.put(listenerKey, zzdlVar);
            } else {
                zzdlVar.zzc(listenerHolder);
            }
            ((zzv) getService()).zzF(new zzj(1, new zzh(deviceOrientationRequest, zzh.zza, null), zzdlVar, new zzdi(null, taskCompletionSource)));
        }
    }

    public final void zzD(ListenerHolder.ListenerKey listenerKey, TaskCompletionSource taskCompletionSource) throws RemoteException {
        synchronized (this.zzh) {
            zzdl zzdlVar = (zzdl) this.zzh.remove(listenerKey);
            if (zzdlVar == null) {
                taskCompletionSource.setResult(Boolean.FALSE);
            } else {
                zzdlVar.zze();
                ((zzv) getService()).zzF(new zzj(2, null, zzdlVar, new zzdi(Boolean.TRUE, taskCompletionSource)));
            }
        }
    }

    public final void zzE(GeofencingRequest geofencingRequest, PendingIntent pendingIntent, TaskCompletionSource taskCompletionSource) throws RemoteException {
        if (zzG(zzo.zzn)) {
            ((zzv) getService()).zze(geofencingRequest, pendingIntent, new zzde(null, taskCompletionSource));
        } else {
            ((zzv) getService()).zzd(geofencingRequest, pendingIntent, new zzdb(taskCompletionSource));
        }
    }

    public final void zzF(zzeh zzehVar, TaskCompletionSource taskCompletionSource) throws RemoteException {
        if (zzG(zzo.zzn)) {
            ((zzv) getService()).zzg(zzehVar, new zzde(null, taskCompletionSource));
        } else {
            ((zzv) getService()).zzf(zzehVar, new zzdb(taskCompletionSource));
        }
    }

    public final void zzp(zzad zzadVar, TaskCompletionSource taskCompletionSource) throws RemoteException {
        if (zzG(zzo.zzj)) {
            ((zzv) getService()).zzo(zzadVar, new zzdz(5, null, new zzdg(taskCompletionSource), null, null));
        } else {
            taskCompletionSource.setResult(((zzv) getService()).zzp(getContext().getPackageName()));
        }
    }

    public final void zzq(LastLocationRequest lastLocationRequest, TaskCompletionSource taskCompletionSource) throws RemoteException {
        if (zzG(zzo.zzj)) {
            ((zzv) getService()).zzq(lastLocationRequest, zzdz.zzd(new zzdf(taskCompletionSource)));
        } else if (zzG(zzo.zzf)) {
            ((zzv) getService()).zzr(lastLocationRequest, new zzdf(taskCompletionSource));
        } else {
            taskCompletionSource.setResult(((zzv) getService()).zzs());
        }
    }

    public final void zzr(CurrentLocationRequest currentLocationRequest, CancellationToken cancellationToken, final TaskCompletionSource taskCompletionSource) throws RemoteException {
        if (zzG(zzo.zzj)) {
            final ICancelToken iCancelTokenZzt = ((zzv) getService()).zzt(currentLocationRequest, zzdz.zzd(new zzdf(taskCompletionSource)));
            if (cancellationToken != null) {
                cancellationToken.onCanceledRequested(new OnTokenCanceledListener() { // from class: com.google.android.gms.internal.location.zzdy
                    @Override // com.google.android.gms.tasks.OnTokenCanceledListener
                    public final /* synthetic */ void onCanceled() {
                        int i = zzdu.zze;
                        try {
                            iCancelTokenZzt.cancel();
                        } catch (RemoteException unused) {
                        }
                    }
                });
                return;
            }
            return;
        }
        if (zzG(zzo.zze)) {
            final ICancelToken iCancelTokenZzu = ((zzv) getService()).zzu(currentLocationRequest, new zzdf(taskCompletionSource));
            if (cancellationToken != null) {
                cancellationToken.onCanceledRequested(new OnTokenCanceledListener() { // from class: com.google.android.gms.internal.location.zzdw
                    @Override // com.google.android.gms.tasks.OnTokenCanceledListener
                    public final /* synthetic */ void onCanceled() {
                        int i = zzdu.zze;
                        try {
                            iCancelTokenZzu.cancel();
                        } catch (RemoteException unused) {
                        }
                    }
                });
                return;
            }
            return;
        }
        ListenerHolder listenerHolderCreateListenerHolder = ListenerHolders.createListenerHolder(new zzdc(this, taskCompletionSource), zzex.zza(), "GetCurrentLocation");
        final ListenerHolder.ListenerKey listenerKey = (ListenerHolder.ListenerKey) Objects.requireNonNull(listenerHolderCreateListenerHolder.getListenerKey());
        zzdd zzddVar = new zzdd(this, listenerHolderCreateListenerHolder, taskCompletionSource);
        TaskCompletionSource taskCompletionSource2 = new TaskCompletionSource();
        LocationRequest.Builder builder = new LocationRequest.Builder(currentLocationRequest.getPriority(), 0L);
        builder.setMinUpdateIntervalMillis(0L);
        builder.setDurationMillis(currentLocationRequest.getDurationMillis());
        builder.setGranularity(currentLocationRequest.getGranularity());
        builder.setMaxUpdateAgeMillis(currentLocationRequest.getMaxUpdateAgeMillis());
        builder.zzb(currentLocationRequest.zza());
        builder.zza(currentLocationRequest.zzb());
        builder.setWaitForAccurateLocation(true);
        builder.zzc(currentLocationRequest.zzc());
        zzt(zzddVar, builder.build(), taskCompletionSource2);
        taskCompletionSource2.getTask().addOnCompleteListener(new OnCompleteListener() { // from class: com.google.android.gms.internal.location.zzdv
            @Override // com.google.android.gms.tasks.OnCompleteListener
            public final /* synthetic */ void onComplete(Task task) {
                int i = zzdu.zze;
                if (task.isSuccessful()) {
                    return;
                }
                taskCompletionSource.trySetException((Exception) Objects.requireNonNull(task.getException()));
            }
        });
        if (cancellationToken != null) {
            cancellationToken.onCanceledRequested(new OnTokenCanceledListener() { // from class: com.google.android.gms.internal.location.zzdx
                @Override // com.google.android.gms.tasks.OnTokenCanceledListener
                public final /* synthetic */ void onCanceled() {
                    try {
                        this.zza.zzw(listenerKey, true, new TaskCompletionSource());
                    } catch (RemoteException unused) {
                    }
                }
            });
        }
    }

    public final void zzs(zzdm zzdmVar, LocationRequest locationRequest, TaskCompletionSource taskCompletionSource) throws RemoteException {
        zzdt zzdtVar;
        ListenerHolder listenerHolderZza = zzdmVar.zza();
        ListenerHolder.ListenerKey listenerKey = (ListenerHolder.ListenerKey) Objects.requireNonNull(listenerHolderZza.getListenerKey());
        boolean zZzG = zzG(zzo.zzj);
        synchronized (this.zzf) {
            zzdt zzdtVar2 = (zzdt) this.zzf.get(listenerKey);
            if (zzdtVar2 == null || zZzG) {
                zzdt zzdtVar3 = new zzdt(zzdmVar);
                this.zzf.put(listenerKey, zzdtVar3);
                zzdtVar = zzdtVar3;
            } else {
                zzdtVar2.zzc(listenerHolderZza);
                zzdtVar = zzdtVar2;
                zzdtVar2 = null;
            }
            if (zZzG) {
                ((zzv) getService()).zzw(zzdz.zza(zzdtVar2, zzdtVar, listenerKey.toIdString()), locationRequest, new zzde(null, taskCompletionSource));
            } else {
                ((zzv) getService()).zzv(new zzed(1, zzeb.zza(null, locationRequest), zzdtVar, null, null, new zzdj(taskCompletionSource, zzdtVar), listenerKey.toIdString()));
            }
        }
    }

    public final void zzt(zzdm zzdmVar, LocationRequest locationRequest, TaskCompletionSource taskCompletionSource) throws RemoteException {
        zzdq zzdqVar;
        ListenerHolder listenerHolderZza = zzdmVar.zza();
        ListenerHolder.ListenerKey listenerKey = (ListenerHolder.ListenerKey) Objects.requireNonNull(listenerHolderZza.getListenerKey());
        boolean zZzG = zzG(zzo.zzj);
        synchronized (this.zzg) {
            zzdq zzdqVar2 = (zzdq) this.zzg.get(listenerKey);
            if (zzdqVar2 == null || zZzG) {
                zzdq zzdqVar3 = new zzdq(zzdmVar);
                this.zzg.put(listenerKey, zzdqVar3);
                zzdqVar = zzdqVar3;
            } else {
                zzdqVar2.zzc(listenerHolderZza);
                zzdqVar = zzdqVar2;
                zzdqVar2 = null;
            }
            if (zZzG) {
                ((zzv) getService()).zzw(zzdz.zzb(zzdqVar2, zzdqVar, listenerKey.toIdString()), locationRequest, new zzde(null, taskCompletionSource));
            } else {
                ((zzv) getService()).zzv(new zzed(1, zzeb.zza(null, locationRequest), null, zzdqVar, null, new zzcy(taskCompletionSource, zzdqVar), listenerKey.toIdString()));
            }
        }
    }

    public final void zzu(PendingIntent pendingIntent, LocationRequest locationRequest, TaskCompletionSource taskCompletionSource) throws RemoteException {
        if (zzG(zzo.zzj)) {
            ((zzv) getService()).zzw(zzdz.zzc(pendingIntent), locationRequest, new zzde(null, taskCompletionSource));
            return;
        }
        zzv zzvVar = (zzv) getService();
        zzeb zzebVarZza = zzeb.zza(null, locationRequest);
        zzdi zzdiVar = new zzdi(null, taskCompletionSource);
        int iHashCode = pendingIntent.hashCode();
        StringBuilder sb = new StringBuilder(String.valueOf(iHashCode).length() + 14);
        sb.append("PendingIntent@");
        sb.append(iHashCode);
        zzvVar.zzv(new zzed(1, zzebVarZza, null, null, pendingIntent, zzdiVar, sb.toString()));
    }

    public final void zzv(ListenerHolder.ListenerKey listenerKey, boolean z, TaskCompletionSource taskCompletionSource) throws RemoteException {
        synchronized (this.zzf) {
            zzdt zzdtVar = (zzdt) this.zzf.remove(listenerKey);
            if (zzdtVar == null) {
                taskCompletionSource.setResult(Boolean.FALSE);
                return;
            }
            zzdtVar.zzf();
            if (!z) {
                taskCompletionSource.setResult(Boolean.TRUE);
            } else if (zzG(zzo.zzj)) {
                zzv zzvVar = (zzv) getService();
                int iIdentityHashCode = System.identityHashCode(zzdtVar);
                StringBuilder sb = new StringBuilder(String.valueOf(iIdentityHashCode).length() + 18);
                sb.append("ILocationListener@");
                sb.append(iIdentityHashCode);
                zzvVar.zzx(zzdz.zza(null, zzdtVar, sb.toString()), new zzde(Boolean.TRUE, taskCompletionSource));
            } else {
                ((zzv) getService()).zzv(new zzed(2, null, zzdtVar, null, null, new zzdi(Boolean.TRUE, taskCompletionSource), null));
            }
        }
    }

    public final void zzw(ListenerHolder.ListenerKey listenerKey, boolean z, TaskCompletionSource taskCompletionSource) throws RemoteException {
        synchronized (this.zzg) {
            zzdq zzdqVar = (zzdq) this.zzg.remove(listenerKey);
            if (zzdqVar == null) {
                taskCompletionSource.setResult(Boolean.FALSE);
                return;
            }
            zzdqVar.zzg();
            if (!z) {
                taskCompletionSource.setResult(Boolean.TRUE);
            } else if (zzG(zzo.zzj)) {
                zzv zzvVar = (zzv) getService();
                int iIdentityHashCode = System.identityHashCode(zzdqVar);
                StringBuilder sb = new StringBuilder(String.valueOf(iIdentityHashCode).length() + 18);
                sb.append("ILocationCallback@");
                sb.append(iIdentityHashCode);
                zzvVar.zzx(zzdz.zzb(null, zzdqVar, sb.toString()), new zzde(Boolean.TRUE, taskCompletionSource));
            } else {
                ((zzv) getService()).zzv(new zzed(2, null, null, zzdqVar, null, new zzdi(Boolean.TRUE, taskCompletionSource), null));
            }
        }
    }

    public final void zzx(PendingIntent pendingIntent, TaskCompletionSource taskCompletionSource, Object obj) throws RemoteException {
        if (zzG(zzo.zzj)) {
            ((zzv) getService()).zzx(zzdz.zzc(pendingIntent), new zzde(null, taskCompletionSource));
        } else {
            ((zzv) getService()).zzv(new zzed(2, null, null, null, pendingIntent, new zzdi(null, taskCompletionSource), null));
        }
    }

    public final void zzy(TaskCompletionSource taskCompletionSource) throws RemoteException {
        if (zzG(zzo.zzg)) {
            ((zzv) getService()).zzz(true, new zzde(null, taskCompletionSource));
        } else {
            ((zzv) getService()).zzy(true);
            taskCompletionSource.setResult(null);
        }
    }

    public final void zzz(TaskCompletionSource taskCompletionSource) throws RemoteException {
        if (zzG(zzo.zzg)) {
            ((zzv) getService()).zzz(false, new zzde(true, taskCompletionSource));
        } else {
            ((zzv) getService()).zzy(false);
            taskCompletionSource.setResult(true);
        }
    }
}
