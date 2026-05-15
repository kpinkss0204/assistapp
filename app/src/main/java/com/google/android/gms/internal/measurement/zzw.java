package com.google.android.gms.internal.measurement;

import androidx.savedstate.serialization.ClassDiscriminatorModeKt;
import java.util.List;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement@@22.1.2 */
/* JADX INFO: loaded from: classes3.dex */
public final class zzw extends zzal {
    private zzaa zzk;

    @Override // com.google.android.gms.internal.measurement.zzal
    public final zzaq zza(zzh zzhVar, List<zzaq> list) {
        zzg.zza(this.zza, 3, list);
        String strZzf = zzhVar.zza(list.get(0)).zzf();
        zzaq zzaqVarZza = zzhVar.zza(list.get(1));
        if (!(zzaqVarZza instanceof zzar)) {
            throw new IllegalArgumentException("Invalid callback type");
        }
        zzaq zzaqVarZza2 = zzhVar.zza(list.get(2));
        if (!(zzaqVarZza2 instanceof zzap)) {
            throw new IllegalArgumentException("Invalid callback params");
        }
        zzap zzapVar = (zzap) zzaqVarZza2;
        if (!zzapVar.zzc(ClassDiscriminatorModeKt.CLASS_DISCRIMINATOR_KEY)) {
            throw new IllegalArgumentException("Undefined rule type");
        }
        this.zzk.zza(strZzf, zzapVar.zzc("priority") ? zzg.zzb(zzapVar.zza("priority").zze().doubleValue()) : 1000, (zzar) zzaqVarZza, zzapVar.zza(ClassDiscriminatorModeKt.CLASS_DISCRIMINATOR_KEY).zzf());
        return zzaq.zzc;
    }

    public zzw(zzaa zzaaVar) {
        super("internal.registerCallback");
        this.zzk = zzaaVar;
    }
}
