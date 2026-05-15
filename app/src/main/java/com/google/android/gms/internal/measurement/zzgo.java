package com.google.android.gms.internal.measurement;

import android.content.ContentProviderClient;
import android.content.ContentResolver;
import android.database.Cursor;
import android.os.RemoteException;
import java.util.Map;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement-impl@@22.1.2 */
/* JADX INFO: loaded from: classes3.dex */
public final class zzgo implements zzgq {
    @Override // com.google.android.gms.internal.measurement.zzgq
    public final String zza(ContentResolver contentResolver, String str) throws zzgt {
        ContentProviderClient contentProviderClientAcquireUnstableContentProviderClient = contentResolver.acquireUnstableContentProviderClient(zzgi.zza);
        try {
            if (contentProviderClientAcquireUnstableContentProviderClient == null) {
                throw new zzgt("Unable to acquire ContentProviderClient");
            }
            try {
                Cursor cursorQuery = contentProviderClientAcquireUnstableContentProviderClient.query(zzgi.zza, null, null, new String[]{str}, null);
                try {
                    if (cursorQuery == null) {
                        throw new zzgt("ContentProvider query returned null cursor");
                    }
                    if (!cursorQuery.moveToFirst()) {
                        if (cursorQuery != null) {
                            cursorQuery.close();
                        }
                        contentProviderClientAcquireUnstableContentProviderClient.release();
                        return null;
                    }
                    String string = cursorQuery.getString(1);
                    if (cursorQuery != null) {
                        cursorQuery.close();
                    }
                    contentProviderClientAcquireUnstableContentProviderClient.release();
                    return string;
                } finally {
                }
            } catch (RemoteException e) {
                throw new zzgt("ContentProvider query failed", e);
            }
        } catch (Throwable th) {
            contentProviderClientAcquireUnstableContentProviderClient.release();
            throw th;
        }
    }

    @Override // com.google.android.gms.internal.measurement.zzgq
    public final <T extends Map<String, String>> T zza(ContentResolver contentResolver, String[] strArr, zzgr<T> zzgrVar) throws zzgt {
        ContentProviderClient contentProviderClientAcquireUnstableContentProviderClient = contentResolver.acquireUnstableContentProviderClient(zzgi.zzb);
        try {
            if (contentProviderClientAcquireUnstableContentProviderClient == null) {
                throw new zzgt("Unable to acquire ContentProviderClient");
            }
            try {
                Cursor cursorQuery = contentProviderClientAcquireUnstableContentProviderClient.query(zzgi.zzb, null, null, strArr, null);
                try {
                    if (cursorQuery == null) {
                        throw new zzgt("ContentProvider query returned null cursor");
                    }
                    T t = (T) zzgrVar.zza(cursorQuery.getCount());
                    while (cursorQuery.moveToNext()) {
                        t.put(cursorQuery.getString(0), cursorQuery.getString(1));
                    }
                    if (!cursorQuery.isAfterLast()) {
                        throw new zzgt("Cursor read incomplete (ContentProvider dead?)");
                    }
                    if (cursorQuery != null) {
                        cursorQuery.close();
                    }
                    contentProviderClientAcquireUnstableContentProviderClient.release();
                    return t;
                } finally {
                }
            } catch (RemoteException e) {
                throw new zzgt("ContentProvider query failed", e);
            }
        } catch (Throwable th) {
            contentProviderClientAcquireUnstableContentProviderClient.release();
            throw th;
        }
    }
}
