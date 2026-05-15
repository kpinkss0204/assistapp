package com.google.android.gms.libs.identity;

import androidx.compose.material3.internal.CalendarModelKt;
import com.google.firebase.firestore.util.ExponentialBackoff;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/* JADX INFO: compiled from: com.google.android.gms:play-services-location@@21.1.0 */
/* JADX INFO: loaded from: classes3.dex */
public final class zzej {
    private static final SimpleDateFormat zza = new SimpleDateFormat("MM-dd HH:mm:ss.SSS", Locale.ROOT);
    private static final SimpleDateFormat zzb = new SimpleDateFormat("MM-dd HH:mm:ss", Locale.ROOT);
    private static final StringBuilder zzc = new StringBuilder(33);

    public static String zza(long j) {
        return j >= 0 ? zza.format(new Date(j)) : Long.toString(j);
    }

    public static String zzb(long j) {
        String string;
        StringBuilder sb = zzc;
        synchronized (sb) {
            sb.setLength(0);
            zzc(j, sb);
            string = sb.toString();
        }
        return string;
    }

    public static StringBuilder zzc(long j, StringBuilder sb) {
        if (j == 0) {
            sb.append("0s");
            return sb;
        }
        sb.ensureCapacity(sb.length() + 27);
        boolean z = false;
        if (j < 0) {
            sb.append("-");
            if (j != Long.MIN_VALUE) {
                j = -j;
            } else {
                j = Long.MAX_VALUE;
                z = true;
            }
        }
        if (j >= CalendarModelKt.MillisecondsIn24Hours) {
            sb.append(j / CalendarModelKt.MillisecondsIn24Hours);
            sb.append("d");
            j %= CalendarModelKt.MillisecondsIn24Hours;
        }
        if (true == z) {
            j = 25975808;
        }
        if (j >= 3600000) {
            sb.append(j / 3600000);
            sb.append("h");
            j %= 3600000;
        }
        if (j >= ExponentialBackoff.DEFAULT_BACKOFF_MAX_DELAY_MS) {
            sb.append(j / ExponentialBackoff.DEFAULT_BACKOFF_MAX_DELAY_MS);
            sb.append("m");
            j %= ExponentialBackoff.DEFAULT_BACKOFF_MAX_DELAY_MS;
        }
        if (j >= 1000) {
            sb.append(j / 1000);
            sb.append("s");
            j %= 1000;
        }
        if (j > 0) {
            sb.append(j);
            sb.append("ms");
        }
        return sb;
    }
}
