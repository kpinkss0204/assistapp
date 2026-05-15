package com.google.android.gms.internal.mlkit_vision_barcode;

import android.os.SystemClock;
import androidx.compose.animation.core.AnimationKt;

/* JADX INFO: compiled from: com.google.android.gms:play-services-mlkit-barcode-scanning@@18.3.1 */
/* JADX INFO: loaded from: classes3.dex */
final class zzaq extends zzbb {
    zzaq() {
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode.zzbb
    public final long zza() {
        return SystemClock.elapsedRealtime() * AnimationKt.MillisToNanos;
    }
}
