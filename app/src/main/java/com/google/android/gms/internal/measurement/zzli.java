package com.google.android.gms.internal.measurement;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement-base@@22.1.2 */
/* JADX INFO: loaded from: classes3.dex */
final class zzli<T> implements zzlu<T> {
    private final zzlc zza;
    private final zzmk<?, ?> zzb;
    private final boolean zzc;
    private final zzji<?> zzd;

    @Override // com.google.android.gms.internal.measurement.zzlu
    public final int zza(T t) {
        zzmk<?, ?> zzmkVar = this.zzb;
        int iZzb = zzmkVar.zzb(zzmkVar.zzd(t));
        return this.zzc ? iZzb + this.zzd.zza(t).zza() : iZzb;
    }

    @Override // com.google.android.gms.internal.measurement.zzlu
    public final int zzb(T t) {
        int iHashCode = this.zzb.zzd(t).hashCode();
        return this.zzc ? (iHashCode * 53) + this.zzd.zza(t).hashCode() : iHashCode;
    }

    static <T> zzli<T> zza(zzmk<?, ?> zzmkVar, zzji<?> zzjiVar, zzlc zzlcVar) {
        return new zzli<>(zzmkVar, zzjiVar, zzlcVar);
    }

    @Override // com.google.android.gms.internal.measurement.zzlu
    public final T zza() {
        zzlc zzlcVar = this.zza;
        if (zzlcVar instanceof zzjt) {
            return (T) ((zzjt) zzlcVar).zzce();
        }
        return (T) zzlcVar.zzci().zzaj();
    }

    private zzli(zzmk<?, ?> zzmkVar, zzji<?> zzjiVar, zzlc zzlcVar) {
        this.zzb = zzmkVar;
        this.zzc = zzjiVar.zza(zzlcVar);
        this.zzd = zzjiVar;
        this.zza = zzlcVar;
    }

    @Override // com.google.android.gms.internal.measurement.zzlu
    public final void zzd(T t) {
        this.zzb.zzf(t);
        this.zzd.zzc(t);
    }

    @Override // com.google.android.gms.internal.measurement.zzlu
    public final void zza(T t, T t2) {
        zzlw.zza(this.zzb, t, t2);
        if (this.zzc) {
            zzlw.zza(this.zzd, t, t2);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:49:0x0085 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:53:? A[LOOP:0: B:45:0x000c->B:53:?, LOOP_END, SYNTHETIC] */
    @Override // com.google.android.gms.internal.measurement.zzlu
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void zza(T r11, com.google.android.gms.internal.measurement.zzlr r12, com.google.android.gms.internal.measurement.zzjg r13) throws java.io.IOException {
        /*
            r10 = this;
            com.google.android.gms.internal.measurement.zzmk<?, ?> r0 = r10.zzb
            com.google.android.gms.internal.measurement.zzji<?> r1 = r10.zzd
            java.lang.Object r2 = r0.zzc(r11)
            com.google.android.gms.internal.measurement.zzjm r3 = r1.zzb(r11)
        Lc:
            int r4 = r12.zzc()     // Catch: java.lang.Throwable -> L8e
            r5 = 2147483647(0x7fffffff, float:NaN)
            if (r4 != r5) goto L19
            r0.zzb(r11, r2)
            return
        L19:
            int r4 = r12.zzd()     // Catch: java.lang.Throwable -> L8e
            r6 = 11
            r7 = 0
            if (r4 == r6) goto L3f
            r5 = r4 & 7
            r6 = 2
            if (r5 != r6) goto L3a
            com.google.android.gms.internal.measurement.zzlc r5 = r10.zza     // Catch: java.lang.Throwable -> L8e
            int r4 = r4 >>> 3
            java.lang.Object r4 = r1.zza(r13, r5, r4)     // Catch: java.lang.Throwable -> L8e
            if (r4 == 0) goto L35
            r1.zza(r12, r4, r13, r3)     // Catch: java.lang.Throwable -> L8e
            goto L82
        L35:
            boolean r4 = r0.zza(r2, r12, r7)     // Catch: java.lang.Throwable -> L8e
            goto L83
        L3a:
            boolean r4 = r12.zzt()     // Catch: java.lang.Throwable -> L8e
            goto L83
        L3f:
            r4 = 0
            r6 = r4
        L41:
            int r8 = r12.zzc()     // Catch: java.lang.Throwable -> L8e
            if (r8 == r5) goto L6f
            int r8 = r12.zzd()     // Catch: java.lang.Throwable -> L8e
            r9 = 16
            if (r8 != r9) goto L5a
            int r7 = r12.zzj()     // Catch: java.lang.Throwable -> L8e
            com.google.android.gms.internal.measurement.zzlc r4 = r10.zza     // Catch: java.lang.Throwable -> L8e
            java.lang.Object r4 = r1.zza(r13, r4, r7)     // Catch: java.lang.Throwable -> L8e
            goto L41
        L5a:
            r9 = 26
            if (r8 != r9) goto L69
            if (r4 == 0) goto L64
            r1.zza(r12, r4, r13, r3)     // Catch: java.lang.Throwable -> L8e
            goto L41
        L64:
            com.google.android.gms.internal.measurement.zzik r6 = r12.zzp()     // Catch: java.lang.Throwable -> L8e
            goto L41
        L69:
            boolean r8 = r12.zzt()     // Catch: java.lang.Throwable -> L8e
            if (r8 != 0) goto L41
        L6f:
            int r5 = r12.zzd()     // Catch: java.lang.Throwable -> L8e
            r8 = 12
            if (r5 != r8) goto L89
            if (r6 == 0) goto L82
            if (r4 == 0) goto L7f
            r1.zza(r6, r4, r13, r3)     // Catch: java.lang.Throwable -> L8e
            goto L82
        L7f:
            r0.zza(r2, r7, r6)     // Catch: java.lang.Throwable -> L8e
        L82:
            r4 = 1
        L83:
            if (r4 != 0) goto Lc
            r0.zzb(r11, r2)
            return
        L89:
            com.google.android.gms.internal.measurement.zzkb r12 = com.google.android.gms.internal.measurement.zzkb.zzb()     // Catch: java.lang.Throwable -> L8e
            throw r12     // Catch: java.lang.Throwable -> L8e
        L8e:
            r12 = move-exception
            r0.zzb(r11, r2)
            throw r12
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzli.zza(java.lang.Object, com.google.android.gms.internal.measurement.zzlr, com.google.android.gms.internal.measurement.zzjg):void");
    }

    /* JADX WARN: Code restructure failed: missing block: B:35:0x00a1, code lost:
    
        if (r12 == null) goto L37;
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x00a3, code lost:
    
        r6.zza((r11 << 3) | 2, r12);
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x00a9, code lost:
    
        r12 = r13;
        r11 = r3;
        r13 = r5;
        r14 = r7;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:33:0x009b  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x00a1 A[EDGE_INSN: B:58:0x00a1->B:35:0x00a1 BREAK  A[LOOP:1: B:18:0x0059->B:63:0x0059], SYNTHETIC] */
    @Override // com.google.android.gms.internal.measurement.zzlu
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void zza(T r10, byte[] r11, int r12, int r13, com.google.android.gms.internal.measurement.zzij r14) throws java.io.IOException {
        /*
            r9 = this;
            r0 = r10
            com.google.android.gms.internal.measurement.zzjt r0 = (com.google.android.gms.internal.measurement.zzjt) r0
            com.google.android.gms.internal.measurement.zzmj r1 = r0.zzb
            com.google.android.gms.internal.measurement.zzmj r2 = com.google.android.gms.internal.measurement.zzmj.zzc()
            if (r1 != r2) goto L11
            com.google.android.gms.internal.measurement.zzmj r1 = com.google.android.gms.internal.measurement.zzmj.zzd()
            r0.zzb = r1
        L11:
            r6 = r1
            com.google.android.gms.internal.measurement.zzjt$zzd r10 = (com.google.android.gms.internal.measurement.zzjt.zzd) r10
            r10.zza()
            r10 = 0
            r0 = r10
        L19:
            if (r12 >= r13) goto Laf
            int r4 = com.google.android.gms.internal.measurement.zzig.zzc(r11, r12, r14)
            int r2 = r14.zza
            r12 = 11
            r1 = 2
            if (r2 == r12) goto L54
            r12 = r2 & 7
            if (r12 != r1) goto L4c
            com.google.android.gms.internal.measurement.zzji<?> r12 = r9.zzd
            com.google.android.gms.internal.measurement.zzjg r0 = r14.zzd
            com.google.android.gms.internal.measurement.zzlc r1 = r9.zza
            int r3 = r2 >>> 3
            java.lang.Object r12 = r12.zza(r0, r1, r3)
            r0 = r12
            com.google.android.gms.internal.measurement.zzjt$zzf r0 = (com.google.android.gms.internal.measurement.zzjt.zzf) r0
            if (r0 != 0) goto L43
            r3 = r11
            r5 = r13
            r7 = r14
            int r12 = com.google.android.gms.internal.measurement.zzig.zza(r2, r3, r4, r5, r6, r7)
            goto L19
        L43:
            com.google.android.gms.internal.measurement.zzlq.zza()
            java.lang.NoSuchMethodError r10 = new java.lang.NoSuchMethodError
            r10.<init>()
            throw r10
        L4c:
            r3 = r11
            r5 = r13
            r7 = r14
            int r12 = com.google.android.gms.internal.measurement.zzig.zza(r2, r3, r4, r5, r7)
            goto L19
        L54:
            r3 = r11
            r5 = r13
            r7 = r14
            r11 = 0
            r12 = r10
        L59:
            if (r4 >= r5) goto La0
            int r13 = com.google.android.gms.internal.measurement.zzig.zzc(r3, r4, r7)
            int r14 = r7.zza
            int r2 = r14 >>> 3
            r4 = r14 & 7
            if (r2 == r1) goto L81
            r8 = 3
            if (r2 == r8) goto L6b
            goto L97
        L6b:
            if (r0 != 0) goto L78
            if (r4 != r1) goto L97
            int r4 = com.google.android.gms.internal.measurement.zzig.zza(r3, r13, r7)
            java.lang.Object r12 = r7.zzc
            com.google.android.gms.internal.measurement.zzik r12 = (com.google.android.gms.internal.measurement.zzik) r12
            goto L59
        L78:
            com.google.android.gms.internal.measurement.zzlq.zza()
            java.lang.NoSuchMethodError r10 = new java.lang.NoSuchMethodError
            r10.<init>()
            throw r10
        L81:
            if (r4 != 0) goto L97
            int r4 = com.google.android.gms.internal.measurement.zzig.zzc(r3, r13, r7)
            int r11 = r7.zza
            com.google.android.gms.internal.measurement.zzji<?> r13 = r9.zzd
            com.google.android.gms.internal.measurement.zzjg r14 = r7.zzd
            com.google.android.gms.internal.measurement.zzlc r0 = r9.zza
            java.lang.Object r13 = r13.zza(r14, r0, r11)
            r0 = r13
            com.google.android.gms.internal.measurement.zzjt$zzf r0 = (com.google.android.gms.internal.measurement.zzjt.zzf) r0
            goto L59
        L97:
            r2 = 12
            if (r14 == r2) goto La1
            int r4 = com.google.android.gms.internal.measurement.zzig.zza(r14, r3, r13, r5, r7)
            goto L59
        La0:
            r13 = r4
        La1:
            if (r12 == 0) goto La9
            int r11 = r11 << 3
            r11 = r11 | r1
            r6.zza(r11, r12)
        La9:
            r12 = r13
            r11 = r3
            r13 = r5
            r14 = r7
            goto L19
        Laf:
            r5 = r13
            if (r12 != r5) goto Lb3
            return
        Lb3:
            com.google.android.gms.internal.measurement.zzkb r10 = com.google.android.gms.internal.measurement.zzkb.zzg()
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzli.zza(java.lang.Object, byte[], int, int, com.google.android.gms.internal.measurement.zzij):void");
    }

    @Override // com.google.android.gms.internal.measurement.zzlu
    public final void zza(T t, zznb zznbVar) throws IOException {
        Iterator itZzd = this.zzd.zza(t).zzd();
        while (itZzd.hasNext()) {
            Map.Entry entry = (Map.Entry) itZzd.next();
            zzjo zzjoVar = (zzjo) entry.getKey();
            if (zzjoVar.zzc() != zzmz.MESSAGE || zzjoVar.zze() || zzjoVar.zzd()) {
                throw new IllegalStateException("Found invalid MessageSet item.");
            }
            if (entry instanceof zzkf) {
                zznbVar.zza(zzjoVar.zza(), (Object) ((zzkf) entry).zza().zzb());
            } else {
                zznbVar.zza(zzjoVar.zza(), entry.getValue());
            }
        }
        zzmk<?, ?> zzmkVar = this.zzb;
        zzmkVar.zza(zzmkVar.zzd(t), zznbVar);
    }

    @Override // com.google.android.gms.internal.measurement.zzlu
    public final boolean zzb(T t, T t2) {
        if (!this.zzb.zzd(t).equals(this.zzb.zzd(t2))) {
            return false;
        }
        if (this.zzc) {
            return this.zzd.zza(t).equals(this.zzd.zza(t2));
        }
        return true;
    }

    @Override // com.google.android.gms.internal.measurement.zzlu
    public final boolean zze(T t) {
        return this.zzd.zza(t).zzg();
    }
}
