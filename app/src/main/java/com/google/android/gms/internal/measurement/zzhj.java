package com.google.android.gms.internal.measurement;

import android.content.Context;
import com.google.android.gms.internal.measurement.zzhg;
import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import javax.annotation.Nullable;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement-impl@@22.1.2 */
/* JADX INFO: loaded from: classes3.dex */
public abstract class zzhj<T> {
    private static final Object zza = new Object();

    @Nullable
    private static volatile zzhu zzb = null;
    private static volatile boolean zzc = false;
    private static zzhy zzd;
    private static final AtomicInteger zze;
    private final zzhr zzf;
    private final String zzg;
    private Object zzh;
    private volatile int zzi;
    private volatile T zzj;
    private final boolean zzk;
    private volatile boolean zzl;

    static /* synthetic */ boolean zzd() {
        return true;
    }

    abstract T zza(Object obj);

    static /* synthetic */ zzhj zza(zzhr zzhrVar, String str, Boolean bool, boolean z) {
        return new zzhq(zzhrVar, str, bool, true);
    }

    static /* synthetic */ zzhj zza(zzhr zzhrVar, String str, Double d, boolean z) {
        return new zzhp(zzhrVar, str, d, true);
    }

    static /* synthetic */ zzhj zza(zzhr zzhrVar, String str, Long l, boolean z) {
        return new zzhn(zzhrVar, str, l, true);
    }

    static /* synthetic */ zzhj zza(zzhr zzhrVar, String str, String str2, boolean z) {
        return new zzhs(zzhrVar, str, str2, true);
    }

    /* JADX WARN: Removed duplicated region for block: B:32:0x007d A[Catch: all -> 0x0098, TryCatch #0 {, blocks: (B:8:0x001c, B:10:0x0020, B:12:0x0029, B:14:0x0039, B:18:0x0056, B:20:0x0061, B:33:0x0081, B:36:0x0089, B:37:0x008e, B:38:0x0092, B:23:0x0068, B:32:0x007d, B:26:0x006f, B:29:0x0076, B:39:0x0096), top: B:46:0x001c }] */
    /* JADX WARN: Removed duplicated region for block: B:35:0x0087  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final T zza() {
        /*
            r8 = this;
            boolean r0 = r8.zzk
            if (r0 != 0) goto L11
            com.google.android.gms.internal.measurement.zzhy r0 = com.google.android.gms.internal.measurement.zzhj.zzd
            java.lang.String r1 = r8.zzg
            boolean r0 = r0.zza(r1)
            java.lang.String r1 = "Attempt to access PhenotypeFlag not via codegen. All new PhenotypeFlags must be accessed through codegen APIs. If you believe you are seeing this error by mistake, you can add your flag to the exemption list located at //java/com/google/android/libraries/phenotype/client/lockdown/flags.textproto. Send the addition CL to ph-reviews@. See go/phenotype-android-codegen for information about generated code. See go/ph-lockdown for more information about this error."
            com.google.common.base.Preconditions.checkState(r0, r1)
        L11:
            java.util.concurrent.atomic.AtomicInteger r0 = com.google.android.gms.internal.measurement.zzhj.zze
            int r0 = r0.get()
            int r1 = r8.zzi
            if (r1 >= r0) goto L9b
            monitor-enter(r8)
            int r1 = r8.zzi     // Catch: java.lang.Throwable -> L98
            if (r1 >= r0) goto L96
            com.google.android.gms.internal.measurement.zzhu r1 = com.google.android.gms.internal.measurement.zzhj.zzb     // Catch: java.lang.Throwable -> L98
            com.google.common.base.Optional r2 = com.google.common.base.Optional.absent()     // Catch: java.lang.Throwable -> L98
            r3 = 0
            if (r1 == 0) goto L51
            com.google.common.base.Supplier r2 = r1.zzb()     // Catch: java.lang.Throwable -> L98
            java.lang.Object r2 = r2.get()     // Catch: java.lang.Throwable -> L98
            com.google.common.base.Optional r2 = (com.google.common.base.Optional) r2     // Catch: java.lang.Throwable -> L98
            boolean r4 = r2.isPresent()     // Catch: java.lang.Throwable -> L98
            if (r4 == 0) goto L51
            java.lang.Object r3 = r2.get()     // Catch: java.lang.Throwable -> L98
            com.google.android.gms.internal.measurement.zzhh r3 = (com.google.android.gms.internal.measurement.zzhh) r3     // Catch: java.lang.Throwable -> L98
            com.google.android.gms.internal.measurement.zzhr r4 = r8.zzf     // Catch: java.lang.Throwable -> L98
            android.net.Uri r4 = r4.zzb     // Catch: java.lang.Throwable -> L98
            com.google.android.gms.internal.measurement.zzhr r5 = r8.zzf     // Catch: java.lang.Throwable -> L98
            java.lang.String r5 = r5.zza     // Catch: java.lang.Throwable -> L98
            com.google.android.gms.internal.measurement.zzhr r6 = r8.zzf     // Catch: java.lang.Throwable -> L98
            java.lang.String r6 = r6.zzd     // Catch: java.lang.Throwable -> L98
            java.lang.String r7 = r8.zzg     // Catch: java.lang.Throwable -> L98
            java.lang.String r3 = r3.zza(r4, r5, r6, r7)     // Catch: java.lang.Throwable -> L98
        L51:
            if (r1 == 0) goto L55
            r4 = 1
            goto L56
        L55:
            r4 = 0
        L56:
            java.lang.String r5 = "Must call PhenotypeFlagInitializer.maybeInit() first"
            com.google.common.base.Preconditions.checkState(r4, r5)     // Catch: java.lang.Throwable -> L98
            com.google.android.gms.internal.measurement.zzhr r4 = r8.zzf     // Catch: java.lang.Throwable -> L98
            boolean r4 = r4.zzf     // Catch: java.lang.Throwable -> L98
            if (r4 == 0) goto L6f
            java.lang.Object r4 = r8.zza(r1)     // Catch: java.lang.Throwable -> L98
            if (r4 == 0) goto L68
            goto L81
        L68:
            java.lang.Object r4 = r8.zzb(r1)     // Catch: java.lang.Throwable -> L98
            if (r4 == 0) goto L7d
            goto L81
        L6f:
            java.lang.Object r4 = r8.zzb(r1)     // Catch: java.lang.Throwable -> L98
            if (r4 == 0) goto L76
            goto L81
        L76:
            java.lang.Object r4 = r8.zza(r1)     // Catch: java.lang.Throwable -> L98
            if (r4 == 0) goto L7d
            goto L81
        L7d:
            java.lang.Object r4 = r8.zze()     // Catch: java.lang.Throwable -> L98
        L81:
            boolean r1 = r2.isPresent()     // Catch: java.lang.Throwable -> L98
            if (r1 == 0) goto L92
            if (r3 != 0) goto L8e
            java.lang.Object r4 = r8.zze()     // Catch: java.lang.Throwable -> L98
            goto L92
        L8e:
            java.lang.Object r4 = r8.zza(r3)     // Catch: java.lang.Throwable -> L98
        L92:
            r8.zzj = r4     // Catch: java.lang.Throwable -> L98
            r8.zzi = r0     // Catch: java.lang.Throwable -> L98
        L96:
            monitor-exit(r8)     // Catch: java.lang.Throwable -> L98
            goto L9b
        L98:
            r0 = move-exception
            monitor-exit(r8)     // Catch: java.lang.Throwable -> L98
            throw r0
        L9b:
            T r0 = r8.zzj
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzhj.zza():java.lang.Object");
    }

    private final T zze() {
        return (T) this.zzh;
    }

    @Nullable
    private final T zza(zzhu zzhuVar) {
        if (!this.zzf.zze && (this.zzf.zzh == null || this.zzf.zzh.apply(zzhuVar.zza()).booleanValue())) {
            Object objZza = zzhc.zza(zzhuVar.zza()).zza(this.zzf.zze ? null : zza(this.zzf.zzc));
            if (objZza != null) {
                return zza(objZza);
            }
        }
        return null;
    }

    @Nullable
    private final T zzb(zzhu zzhuVar) {
        zzhb zzhbVarZza;
        Object objZza;
        if (this.zzf.zzb != null) {
            if (!zzhi.zza(zzhuVar.zza(), this.zzf.zzb)) {
                zzhbVarZza = null;
            } else if (this.zzf.zzg) {
                zzhbVarZza = zzgu.zza(zzhuVar.zza().getContentResolver(), zzhk.zza(zzhk.zza(zzhuVar.zza(), this.zzf.zzb.getLastPathSegment())), new Runnable() { // from class: com.google.android.gms.internal.measurement.zzhm
                    @Override // java.lang.Runnable
                    public final void run() {
                        zzhj.zzc();
                    }
                });
            } else {
                zzhbVarZza = zzgu.zza(zzhuVar.zza().getContentResolver(), this.zzf.zzb, new Runnable() { // from class: com.google.android.gms.internal.measurement.zzhm
                    @Override // java.lang.Runnable
                    public final void run() {
                        zzhj.zzc();
                    }
                });
            }
        } else {
            zzhbVarZza = zzhw.zza(zzhuVar.zza(), this.zzf.zza, new Runnable() { // from class: com.google.android.gms.internal.measurement.zzhm
                @Override // java.lang.Runnable
                public final void run() {
                    zzhj.zzc();
                }
            });
        }
        if (zzhbVarZza == null || (objZza = zzhbVarZza.zza(zzb())) == null) {
            return null;
        }
        return zza(objZza);
    }

    public final String zzb() {
        return zza(this.zzf.zzd);
    }

    private final String zza(String str) {
        if (str != null && str.isEmpty()) {
            return this.zzg;
        }
        return str + this.zzg;
    }

    static {
        new AtomicReference();
        zzd = new zzhy(new zzhx() { // from class: com.google.android.gms.internal.measurement.zzho
            @Override // com.google.android.gms.internal.measurement.zzhx
            public final boolean zza() {
                return zzhj.zzd();
            }
        });
        zze = new AtomicInteger();
    }

    private zzhj(zzhr zzhrVar, String str, T t, boolean z) {
        this.zzi = -1;
        if (zzhrVar.zza == null && zzhrVar.zzb == null) {
            throw new IllegalArgumentException("Must pass a valid SharedPreferences file name or ContentProvider URI");
        }
        if (zzhrVar.zza != null && zzhrVar.zzb != null) {
            throw new IllegalArgumentException("Must pass one of SharedPreferences file name or ContentProvider URI");
        }
        this.zzf = zzhrVar;
        this.zzg = str;
        this.zzh = t;
        this.zzk = z;
        this.zzl = false;
    }

    public static void zzc() {
        zze.incrementAndGet();
    }

    public static void zzb(final Context context) {
        if (zzb != null || context == null) {
            return;
        }
        Object obj = zza;
        synchronized (obj) {
            if (zzb == null && context != null) {
                synchronized (obj) {
                    zzhu zzhuVar = zzb;
                    Context applicationContext = context.getApplicationContext();
                    if (applicationContext != null) {
                        context = applicationContext;
                    }
                    if (zzhuVar == null || zzhuVar.zza() != context) {
                        if (zzhuVar != null) {
                            zzgu.zzc();
                            zzhw.zza();
                            zzhc.zza();
                        }
                        zzb = new zzgv(context, Suppliers.memoize(new Supplier() { // from class: com.google.android.gms.internal.measurement.zzhl
                            @Override // com.google.common.base.Supplier
                            public final Object get() {
                                return zzhg.zza.zza(context);
                            }
                        }));
                        zze.incrementAndGet();
                    }
                }
            }
        }
    }
}
