package com.google.android.gms.measurement.internal;

import android.content.ContentValues;
import android.database.sqlite.SQLiteException;
import androidx.collection.ArrayMap;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.measurement.zzfo;
import com.google.android.gms.internal.measurement.zzfy;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement@@22.1.2 */
/* JADX INFO: loaded from: classes3.dex */
final class zzt extends zznr {
    private String zza;
    private Set<Integer> zzb;
    private Map<Integer, zzv> zzc;
    private Long zzd;
    private Long zze;

    private final zzv zza(Integer num) {
        if (this.zzc.containsKey(num)) {
            return this.zzc.get(num);
        }
        zzv zzvVar = new zzv(this, this.zza);
        this.zzc.put(num, zzvVar);
        return zzvVar;
    }

    @Override // com.google.android.gms.measurement.internal.zznr
    protected final boolean zzc() {
        return false;
    }

    final List<zzfy.zzd> zza(String str, List<zzfy.zzf> list, List<zzfy.zzo> list2, Long l, Long l2) {
        return zza(str, list, list2, l, l2, false);
    }

    final List<zzfy.zzd> zza(String str, List<zzfy.zzf> list, List<zzfy.zzo> list2, Long l, Long l2, boolean z) {
        boolean z2;
        boolean z3;
        boolean z4;
        HashSet hashSet;
        Map<Integer, zzfy.zzm> map;
        List<zzfo.zzb> list3;
        Iterator it;
        boolean z5;
        Iterator<zzfy.zzn> it2;
        Map<Integer, zzfy.zzm> map2;
        zzfy.zzm zzmVar;
        HashSet hashSet2;
        Map<Integer, List<Integer>> map3;
        Iterator<Integer> it3;
        boolean z6;
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(list);
        Preconditions.checkNotNull(list2);
        this.zza = str;
        this.zzb = new HashSet();
        this.zzc = new ArrayMap();
        this.zzd = l;
        this.zze = l2;
        Iterator<zzfy.zzf> it4 = list.iterator();
        while (true) {
            z2 = true;
            if (!it4.hasNext()) {
                z3 = false;
                break;
            }
            if ("_s".equals(it4.next().zzg())) {
                z3 = true;
                break;
            }
        }
        boolean z7 = com.google.android.gms.internal.measurement.zzoe.zza() && zze().zzf(this.zza, zzbh.zzbp);
        boolean z8 = com.google.android.gms.internal.measurement.zzoe.zza() && zze().zzf(this.zza, zzbh.zzbo);
        if (z3) {
            zzal zzalVarZzh = zzh();
            String str2 = this.zza;
            zzalVarZzh.zzal();
            zzalVarZzh.zzt();
            Preconditions.checkNotEmpty(str2);
            ContentValues contentValues = new ContentValues();
            contentValues.put("current_session_count", (Integer) 0);
            try {
                zzalVarZzh.e_().update("events", contentValues, "app_id = ?", new String[]{str2});
            } catch (SQLiteException e) {
                zzalVarZzh.zzj().zzg().zza("Error resetting session-scoped event counts. appId", zzgo.zza(str2), e);
            }
        }
        Map<Integer, List<zzfo.zzb>> mapEmptyMap = Collections.emptyMap();
        if (z8 && z7) {
            mapEmptyMap = zzh().zzn(this.zza);
        }
        Map<Integer, zzfy.zzm> mapZzm = zzh().zzm(this.zza);
        if (mapZzm.isEmpty()) {
            z4 = true;
        } else {
            HashSet hashSet3 = new HashSet(mapZzm.keySet());
            if (z3) {
                String str3 = this.zza;
                Map<Integer, List<Integer>> mapZzo = zzh().zzo(this.zza);
                Preconditions.checkNotEmpty(str3);
                Preconditions.checkNotNull(mapZzm);
                ArrayMap arrayMap = new ArrayMap();
                if (!mapZzm.isEmpty()) {
                    Iterator<Integer> it5 = mapZzm.keySet().iterator();
                    while (it5.hasNext()) {
                        int iIntValue = it5.next().intValue();
                        zzfy.zzm zzmVar2 = mapZzm.get(Integer.valueOf(iIntValue));
                        List<Integer> list4 = mapZzo.get(Integer.valueOf(iIntValue));
                        if (list4 == null || list4.isEmpty()) {
                            hashSet2 = hashSet3;
                            map3 = mapZzo;
                            it3 = it5;
                            z6 = z2;
                            arrayMap.put(Integer.valueOf(iIntValue), zzmVar2);
                        } else {
                            z6 = z2;
                            hashSet2 = hashSet3;
                            List<Long> listZza = g_().zza(zzmVar2.zzi(), list4);
                            if (listZza.isEmpty()) {
                                z2 = z6;
                                hashSet3 = hashSet2;
                            } else {
                                zzfy.zzm.zza zzaVarZzb = zzmVar2.zzcd().zzb().zzb(listZza);
                                zzaVarZzb.zzd().zzd(g_().zza(zzmVar2.zzk(), list4));
                                ArrayList arrayList = new ArrayList();
                                for (zzfy.zze zzeVar : zzmVar2.zzh()) {
                                    Map<Integer, List<Integer>> map4 = mapZzo;
                                    Iterator<Integer> it6 = it5;
                                    if (!list4.contains(Integer.valueOf(zzeVar.zza()))) {
                                        arrayList.add(zzeVar);
                                    }
                                    mapZzo = map4;
                                    it5 = it6;
                                }
                                map3 = mapZzo;
                                it3 = it5;
                                zzaVarZzb.zza().zza(arrayList);
                                ArrayList arrayList2 = new ArrayList();
                                for (zzfy.zzn zznVar : zzmVar2.zzj()) {
                                    if (!list4.contains(Integer.valueOf(zznVar.zzb()))) {
                                        arrayList2.add(zznVar);
                                    }
                                }
                                zzaVarZzb.zzc().zzc(arrayList2);
                                arrayMap.put(Integer.valueOf(iIntValue), (zzfy.zzm) ((com.google.android.gms.internal.measurement.zzjt) zzaVarZzb.zzai()));
                            }
                        }
                        z2 = z6;
                        hashSet3 = hashSet2;
                        mapZzo = map3;
                        it5 = it3;
                    }
                }
                hashSet = hashSet3;
                z4 = z2;
                map = arrayMap;
            } else {
                hashSet = hashSet3;
                z4 = true;
                map = mapZzm;
            }
            Iterator it7 = hashSet.iterator();
            while (it7.hasNext()) {
                int iIntValue2 = ((Integer) it7.next()).intValue();
                zzfy.zzm zzmVar3 = map.get(Integer.valueOf(iIntValue2));
                BitSet bitSet = new BitSet();
                BitSet bitSet2 = new BitSet();
                ArrayMap arrayMap2 = new ArrayMap();
                if (zzmVar3 != null && zzmVar3.zza() != 0) {
                    for (zzfy.zze zzeVar2 : zzmVar3.zzh()) {
                        if (zzeVar2.zzf()) {
                            zzmVar = zzmVar3;
                            arrayMap2.put(Integer.valueOf(zzeVar2.zza()), zzeVar2.zze() ? Long.valueOf(zzeVar2.zzb()) : null);
                        } else {
                            zzmVar = zzmVar3;
                        }
                        zzmVar3 = zzmVar;
                    }
                }
                zzfy.zzm zzmVar4 = zzmVar3;
                ArrayMap arrayMap3 = new ArrayMap();
                if (zzmVar4 != null && zzmVar4.zzc() != 0) {
                    Iterator<zzfy.zzn> it8 = zzmVar4.zzj().iterator();
                    while (it8.hasNext()) {
                        zzfy.zzn next = it8.next();
                        if (!next.zzf() || next.zza() <= 0) {
                            it2 = it8;
                            map2 = map;
                        } else {
                            it2 = it8;
                            map2 = map;
                            arrayMap3.put(Integer.valueOf(next.zzb()), Long.valueOf(next.zza(next.zza() - 1)));
                        }
                        it8 = it2;
                        map = map2;
                    }
                }
                Map<Integer, zzfy.zzm> map5 = map;
                if (zzmVar4 != null) {
                    int i = 0;
                    while (i < (zzmVar4.zzd() << 6)) {
                        if (zzoo.zza(zzmVar4.zzk(), i)) {
                            it = it7;
                            z5 = z7;
                            zzj().zzp().zza("Filter already evaluated. audience ID, filter ID", Integer.valueOf(iIntValue2), Integer.valueOf(i));
                            bitSet2.set(i);
                            if (zzoo.zza(zzmVar4.zzi(), i)) {
                                bitSet.set(i);
                            }
                            i++;
                            it7 = it;
                            z7 = z5;
                        } else {
                            it = it7;
                            z5 = z7;
                        }
                        arrayMap2.remove(Integer.valueOf(i));
                        i++;
                        it7 = it;
                        z7 = z5;
                    }
                }
                Iterator it9 = it7;
                boolean z9 = z7;
                zzfy.zzm zzmVar5 = mapZzm.get(Integer.valueOf(iIntValue2));
                if (z8 && z9 && (list3 = mapEmptyMap.get(Integer.valueOf(iIntValue2))) != null && this.zze != null && this.zzd != null) {
                    for (zzfo.zzb zzbVar : list3) {
                        int iZzb = zzbVar.zzb();
                        long jLongValue = this.zze.longValue() / 1000;
                        if (zzbVar.zzi()) {
                            jLongValue = this.zzd.longValue() / 1000;
                        }
                        if (arrayMap2.containsKey(Integer.valueOf(iZzb))) {
                            arrayMap2.put(Integer.valueOf(iZzb), Long.valueOf(jLongValue));
                        }
                        if (arrayMap3.containsKey(Integer.valueOf(iZzb))) {
                            arrayMap3.put(Integer.valueOf(iZzb), Long.valueOf(jLongValue));
                        }
                    }
                }
                this.zzc.put(Integer.valueOf(iIntValue2), new zzv(this, this.zza, zzmVar5, bitSet, bitSet2, arrayMap2, arrayMap3));
                mapZzm = mapZzm;
                mapEmptyMap = mapEmptyMap;
                it7 = it9;
                z7 = z9;
                map = map5;
            }
        }
        if (com.google.android.gms.internal.measurement.zznm.zza() && zze().zzf(null, zzbh.zzcy)) {
            zza(list, z);
            if (z) {
                return new ArrayList();
            }
            zza(list2);
            return zzu();
        }
        zza(list, z4);
        zza(list2);
        return zzu();
    }

    private final List<zzfy.zzd> zzu() {
        ArrayList arrayList = new ArrayList();
        Set<Integer> setKeySet = this.zzc.keySet();
        setKeySet.removeAll(this.zzb);
        Iterator<Integer> it = setKeySet.iterator();
        while (it.hasNext()) {
            int iIntValue = it.next().intValue();
            zzv zzvVar = this.zzc.get(Integer.valueOf(iIntValue));
            Preconditions.checkNotNull(zzvVar);
            zzfy.zzd zzdVarZza = zzvVar.zza(iIntValue);
            arrayList.add(zzdVarZza);
            zzal zzalVarZzh = zzh();
            String str = this.zza;
            zzfy.zzm zzmVarZzd = zzdVarZza.zzd();
            zzalVarZzh.zzal();
            zzalVarZzh.zzt();
            Preconditions.checkNotEmpty(str);
            Preconditions.checkNotNull(zzmVarZzd);
            byte[] bArrZzca = zzmVarZzd.zzca();
            ContentValues contentValues = new ContentValues();
            contentValues.put("app_id", str);
            contentValues.put("audience_id", Integer.valueOf(iIntValue));
            contentValues.put("current_results", bArrZzca);
            try {
                if (zzalVarZzh.e_().insertWithOnConflict("audience_filter_values", null, contentValues, 5) == -1) {
                    zzalVarZzh.zzj().zzg().zza("Failed to insert filter results (got -1). appId", zzgo.zza(str));
                }
            } catch (SQLiteException e) {
                zzalVarZzh.zzj().zzg().zza("Error storing filter results. appId", zzgo.zza(str), e);
            }
        }
        return arrayList;
    }

    zzt(zznv zznvVar) {
        super(zznvVar);
    }

    private final void zza(List<zzfy.zzf> list, boolean z) {
        ArrayMap arrayMap;
        zzy zzyVar;
        zzbb zzbbVar;
        zzac zzacVar;
        if (list.isEmpty()) {
            return;
        }
        zzac zzacVar2 = null;
        zzy zzyVar2 = new zzy(this);
        ArrayMap arrayMap2 = new ArrayMap();
        for (zzfy.zzf zzfVar : list) {
            zzfy.zzf zzfVarZza = zzyVar2.zza(this.zza, zzfVar);
            if (zzfVarZza != null) {
                zzal zzalVarZzh = zzh();
                String str = this.zza;
                String strZzg = zzfVarZza.zzg();
                zzbb zzbbVarZzd = zzalVarZzh.zzd(str, zzfVar.zzg());
                if (zzbbVarZzd == null) {
                    zzalVarZzh.zzj().zzu().zza("Event aggregate wasn't created during raw event logging. appId, event", zzgo.zza(str), zzalVarZzh.zzi().zza(strZzg));
                    zzyVar = zzyVar2;
                    arrayMap = arrayMap2;
                    zzbbVar = new zzbb(str, zzfVar.zzg(), 1L, 1L, 1L, zzfVar.zzd(), 0L, null, null, null, null);
                } else {
                    arrayMap = arrayMap2;
                    zzyVar = zzyVar2;
                    zzbbVar = new zzbb(zzbbVarZzd.zza, zzbbVarZzd.zzb, zzbbVarZzd.zzc + 1, zzbbVarZzd.zzd + 1, zzbbVarZzd.zze + 1, zzbbVarZzd.zzf, zzbbVarZzd.zzg, zzbbVarZzd.zzh, zzbbVarZzd.zzi, zzbbVarZzd.zzj, zzbbVarZzd.zzk);
                }
                zzh().zza(zzbbVar);
                if (com.google.android.gms.internal.measurement.zznm.zza()) {
                    zzacVar = null;
                    if (zze().zzf(null, zzbh.zzcy) && z) {
                        zzacVar2 = null;
                        arrayMap2 = arrayMap;
                    }
                    zzyVar2 = zzyVar;
                } else {
                    zzacVar = null;
                }
                long j = zzbbVar.zzc;
                String strZzg2 = zzfVarZza.zzg();
                ArrayMap arrayMap3 = arrayMap;
                Map<Integer, List<zzfo.zzb>> mapZzf = (Map) arrayMap3.get(strZzg2);
                if (mapZzf == null) {
                    mapZzf = zzh().zzf(this.zza, strZzg2);
                    arrayMap3.put(strZzg2, mapZzf);
                }
                Iterator<Integer> it = mapZzf.keySet().iterator();
                while (it.hasNext()) {
                    int iIntValue = it.next().intValue();
                    if (this.zzb.contains(Integer.valueOf(iIntValue))) {
                        zzj().zzp().zza("Skipping failed audience ID", Integer.valueOf(iIntValue));
                    } else {
                        Iterator<zzfo.zzb> it2 = mapZzf.get(Integer.valueOf(iIntValue)).iterator();
                        boolean z2 = true;
                        while (true) {
                            if (!it2.hasNext()) {
                                break;
                            }
                            zzfo.zzb next = it2.next();
                            zzx zzxVar = new zzx(this, this.zza, iIntValue, next);
                            boolean zZza = zzxVar.zza(this.zzd, this.zze, zzfVarZza, j, zzbbVar, zza(iIntValue, next.zzb()));
                            if (zZza) {
                                zza(Integer.valueOf(iIntValue)).zza(zzxVar);
                                z2 = zZza;
                            } else {
                                this.zzb.add(Integer.valueOf(iIntValue));
                                z2 = zZza;
                                break;
                            }
                        }
                        if (!z2) {
                            this.zzb.add(Integer.valueOf(iIntValue));
                        }
                    }
                }
                zzac zzacVar3 = zzacVar;
                arrayMap2 = arrayMap3;
                zzacVar2 = zzacVar3;
                zzyVar2 = zzyVar;
            } else {
                arrayMap2 = arrayMap2;
                zzacVar2 = zzacVar2;
            }
        }
    }

    private final void zza(List<zzfy.zzo> list) {
        zzfo.zze next;
        if (list.isEmpty()) {
            return;
        }
        ArrayMap arrayMap = new ArrayMap();
        for (zzfy.zzo zzoVar : list) {
            String strZzg = zzoVar.zzg();
            Map<Integer, List<zzfo.zze>> mapZzg = (Map) arrayMap.get(strZzg);
            if (mapZzg == null) {
                mapZzg = zzh().zzg(this.zza, strZzg);
                arrayMap.put(strZzg, mapZzg);
            }
            Iterator<Integer> it = mapZzg.keySet().iterator();
            while (true) {
                if (it.hasNext()) {
                    int iIntValue = it.next().intValue();
                    if (this.zzb.contains(Integer.valueOf(iIntValue))) {
                        zzj().zzp().zza("Skipping failed audience ID", Integer.valueOf(iIntValue));
                        break;
                    }
                    Iterator<zzfo.zze> it2 = mapZzg.get(Integer.valueOf(iIntValue)).iterator();
                    boolean zZza = true;
                    while (true) {
                        if (!it2.hasNext()) {
                            break;
                        }
                        next = it2.next();
                        if (zzj().zza(2)) {
                            zzj().zzp().zza("Evaluating filter. audience, filter, property", Integer.valueOf(iIntValue), next.zzi() ? Integer.valueOf(next.zza()) : null, zzi().zzc(next.zze()));
                            zzj().zzp().zza("Filter definition", g_().zza(next));
                        }
                        if (!next.zzi() || next.zza() > 256) {
                            break;
                        }
                        zzz zzzVar = new zzz(this, this.zza, iIntValue, next);
                        zZza = zzzVar.zza(this.zzd, this.zze, zzoVar, zza(iIntValue, next.zza()));
                        if (zZza) {
                            zza(Integer.valueOf(iIntValue)).zza(zzzVar);
                        } else {
                            this.zzb.add(Integer.valueOf(iIntValue));
                            break;
                        }
                    }
                    zzj().zzu().zza("Invalid property filter ID. appId, id", zzgo.zza(this.zza), String.valueOf(next.zzi() ? Integer.valueOf(next.zza()) : null));
                    zZza = false;
                    if (!zZza) {
                        this.zzb.add(Integer.valueOf(iIntValue));
                    }
                }
            }
        }
    }

    private final boolean zza(int i, int i2) {
        zzv zzvVar = this.zzc.get(Integer.valueOf(i));
        if (zzvVar == null) {
            return false;
        }
        return zzvVar.zzd.get(i2);
    }
}
