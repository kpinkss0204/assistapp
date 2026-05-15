package com.google.android.gms.measurement.internal;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.Pair;
import androidx.collection.ArrayMap;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.measurement.zzfo;
import com.google.android.gms.internal.measurement.zzfy;
import com.google.android.gms.internal.measurement.zzjt;
import com.google.android.gms.internal.measurement.zzpu;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.database.core.ServerValues;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.time.DurationKt;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement@@22.1.2 */
/* JADX INFO: loaded from: classes3.dex */
final class zzal extends zznr {
    private static final String[] zza = {"last_bundled_timestamp", "ALTER TABLE events ADD COLUMN last_bundled_timestamp INTEGER;", "last_bundled_day", "ALTER TABLE events ADD COLUMN last_bundled_day INTEGER;", "last_sampled_complex_event_id", "ALTER TABLE events ADD COLUMN last_sampled_complex_event_id INTEGER;", "last_sampling_rate", "ALTER TABLE events ADD COLUMN last_sampling_rate INTEGER;", "last_exempt_from_sampling", "ALTER TABLE events ADD COLUMN last_exempt_from_sampling INTEGER;", "current_session_count", "ALTER TABLE events ADD COLUMN current_session_count INTEGER;"};
    private static final String[] zzb = {"origin", "ALTER TABLE user_attributes ADD COLUMN origin TEXT;"};
    private static final String[] zzc = {"app_version", "ALTER TABLE apps ADD COLUMN app_version TEXT;", "app_store", "ALTER TABLE apps ADD COLUMN app_store TEXT;", "gmp_version", "ALTER TABLE apps ADD COLUMN gmp_version INTEGER;", "dev_cert_hash", "ALTER TABLE apps ADD COLUMN dev_cert_hash INTEGER;", "measurement_enabled", "ALTER TABLE apps ADD COLUMN measurement_enabled INTEGER;", "last_bundle_start_timestamp", "ALTER TABLE apps ADD COLUMN last_bundle_start_timestamp INTEGER;", "day", "ALTER TABLE apps ADD COLUMN day INTEGER;", "daily_public_events_count", "ALTER TABLE apps ADD COLUMN daily_public_events_count INTEGER;", "daily_events_count", "ALTER TABLE apps ADD COLUMN daily_events_count INTEGER;", "daily_conversions_count", "ALTER TABLE apps ADD COLUMN daily_conversions_count INTEGER;", "remote_config", "ALTER TABLE apps ADD COLUMN remote_config BLOB;", "config_fetched_time", "ALTER TABLE apps ADD COLUMN config_fetched_time INTEGER;", "failed_config_fetch_time", "ALTER TABLE apps ADD COLUMN failed_config_fetch_time INTEGER;", "app_version_int", "ALTER TABLE apps ADD COLUMN app_version_int INTEGER;", "firebase_instance_id", "ALTER TABLE apps ADD COLUMN firebase_instance_id TEXT;", "daily_error_events_count", "ALTER TABLE apps ADD COLUMN daily_error_events_count INTEGER;", "daily_realtime_events_count", "ALTER TABLE apps ADD COLUMN daily_realtime_events_count INTEGER;", "health_monitor_sample", "ALTER TABLE apps ADD COLUMN health_monitor_sample TEXT;", "android_id", "ALTER TABLE apps ADD COLUMN android_id INTEGER;", "adid_reporting_enabled", "ALTER TABLE apps ADD COLUMN adid_reporting_enabled INTEGER;", "ssaid_reporting_enabled", "ALTER TABLE apps ADD COLUMN ssaid_reporting_enabled INTEGER;", "admob_app_id", "ALTER TABLE apps ADD COLUMN admob_app_id TEXT;", "linked_admob_app_id", "ALTER TABLE apps ADD COLUMN linked_admob_app_id TEXT;", "dynamite_version", "ALTER TABLE apps ADD COLUMN dynamite_version INTEGER;", "safelisted_events", "ALTER TABLE apps ADD COLUMN safelisted_events TEXT;", "ga_app_id", "ALTER TABLE apps ADD COLUMN ga_app_id TEXT;", "config_last_modified_time", "ALTER TABLE apps ADD COLUMN config_last_modified_time TEXT;", "e_tag", "ALTER TABLE apps ADD COLUMN e_tag TEXT;", "session_stitching_token", "ALTER TABLE apps ADD COLUMN session_stitching_token TEXT;", "sgtm_upload_enabled", "ALTER TABLE apps ADD COLUMN sgtm_upload_enabled INTEGER;", "target_os_version", "ALTER TABLE apps ADD COLUMN target_os_version INTEGER;", "session_stitching_token_hash", "ALTER TABLE apps ADD COLUMN session_stitching_token_hash INTEGER;", "ad_services_version", "ALTER TABLE apps ADD COLUMN ad_services_version INTEGER;", "unmatched_first_open_without_ad_id", "ALTER TABLE apps ADD COLUMN unmatched_first_open_without_ad_id INTEGER;", "npa_metadata_value", "ALTER TABLE apps ADD COLUMN npa_metadata_value INTEGER;", "attribution_eligibility_status", "ALTER TABLE apps ADD COLUMN attribution_eligibility_status INTEGER;", "sgtm_preview_key", "ALTER TABLE apps ADD COLUMN sgtm_preview_key TEXT;", "dma_consent_state", "ALTER TABLE apps ADD COLUMN dma_consent_state INTEGER;", "daily_realtime_dcu_count", "ALTER TABLE apps ADD COLUMN daily_realtime_dcu_count INTEGER;", "bundle_delivery_index", "ALTER TABLE apps ADD COLUMN bundle_delivery_index INTEGER;", "serialized_npa_metadata", "ALTER TABLE apps ADD COLUMN serialized_npa_metadata TEXT;", "unmatched_pfo", "ALTER TABLE apps ADD COLUMN unmatched_pfo INTEGER;", "unmatched_uwa", "ALTER TABLE apps ADD COLUMN unmatched_uwa INTEGER;", "ad_campaign_info", "ALTER TABLE apps ADD COLUMN ad_campaign_info BLOB;", "daily_registered_triggers_count", "ALTER TABLE apps ADD COLUMN daily_registered_triggers_count INTEGER;"};
    private static final String[] zzd = {"realtime", "ALTER TABLE raw_events ADD COLUMN realtime INTEGER;"};
    private static final String[] zze = {"has_realtime", "ALTER TABLE queue ADD COLUMN has_realtime INTEGER;", "retry_count", "ALTER TABLE queue ADD COLUMN retry_count INTEGER;"};
    private static final String[] zzf = {"session_scoped", "ALTER TABLE event_filters ADD COLUMN session_scoped BOOLEAN;"};
    private static final String[] zzh = {"session_scoped", "ALTER TABLE property_filters ADD COLUMN session_scoped BOOLEAN;"};
    private static final String[] zzi = {"previous_install_count", "ALTER TABLE app2 ADD COLUMN previous_install_count INTEGER;"};
    private static final String[] zzj = {"consent_source", "ALTER TABLE consent_settings ADD COLUMN consent_source INTEGER;", "dma_consent_settings", "ALTER TABLE consent_settings ADD COLUMN dma_consent_settings TEXT;", "storage_consent_at_bundling", "ALTER TABLE consent_settings ADD COLUMN storage_consent_at_bundling TEXT;"};
    private static final String[] zzk = {"idempotent", "CREATE INDEX IF NOT EXISTS trigger_uris_index ON trigger_uris (app_id);"};
    private final zzat zzl;
    private final zznl zzm;

    public final int zza(String str, String str2) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotEmpty(str2);
        zzt();
        zzal();
        try {
            return e_().delete("conditional_properties", "app_id=? and name=?", new String[]{str, str2});
        } catch (SQLiteException e) {
            zzj().zzg().zza("Error deleting conditional property", zzgo.zza(str), zzi().zzc(str2), e);
            return 0;
        }
    }

    @Override // com.google.android.gms.measurement.internal.zznr
    protected final boolean zzc() {
        return false;
    }

    public final long zza(String str) {
        Preconditions.checkNotEmpty(str);
        zzt();
        zzal();
        try {
            return e_().delete("raw_events", "rowid in (select rowid from raw_events where app_id=? order by rowid desc limit -1 offset ?)", new String[]{str, String.valueOf(Math.max(0, Math.min(DurationKt.NANOS_IN_MILLIS, zze().zzb(str, zzbh.zzp))))});
        } catch (SQLiteException e) {
            zzj().zzg().zza("Error deleting over the limit events. appId", zzgo.zza(str), e);
            return 0L;
        }
    }

    public final long b_() {
        Cursor cursorRawQuery = null;
        try {
            try {
                cursorRawQuery = e_().rawQuery("select rowid from raw_events order by rowid desc limit 1;", null);
                if (!cursorRawQuery.moveToFirst()) {
                    if (cursorRawQuery != null) {
                        cursorRawQuery.close();
                    }
                    return -1L;
                }
                long j = cursorRawQuery.getLong(0);
                if (cursorRawQuery != null) {
                    cursorRawQuery.close();
                }
                return j;
            } catch (SQLiteException e) {
                zzj().zzg().zza("Error querying raw events", e);
                if (cursorRawQuery != null) {
                    cursorRawQuery.close();
                }
                return -1L;
            }
        } catch (Throwable th) {
            if (cursorRawQuery != null) {
                cursorRawQuery.close();
            }
            throw th;
        }
    }

    public final long zza(zzfy.zzk zzkVar) throws IOException {
        zzt();
        zzal();
        Preconditions.checkNotNull(zzkVar);
        Preconditions.checkNotEmpty(zzkVar.zzz());
        byte[] bArrZzca = zzkVar.zzca();
        long jZza = g_().zza(bArrZzca);
        ContentValues contentValues = new ContentValues();
        contentValues.put("app_id", zzkVar.zzz());
        contentValues.put("metadata_fingerprint", Long.valueOf(jZza));
        contentValues.put("metadata", bArrZzca);
        try {
            e_().insertWithOnConflict("raw_events_metadata", null, contentValues, 4);
            return jZza;
        } catch (SQLiteException e) {
            zzj().zzg().zza("Error storing raw event metadata. appId", zzgo.zza(zzkVar.zzz()), e);
            throw e;
        }
    }

    protected final long zzb(String str, String str2) {
        long jZza;
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotEmpty(str2);
        zzt();
        zzal();
        SQLiteDatabase sQLiteDatabaseE_ = e_();
        sQLiteDatabaseE_.beginTransaction();
        long j = 0;
        try {
            try {
                jZza = zza("select " + str2 + " from app2 where app_id=?", new String[]{str}, -1L);
            } finally {
                sQLiteDatabaseE_.endTransaction();
            }
        } catch (SQLiteException e) {
            e = e;
        }
        if (jZza == -1) {
            ContentValues contentValues = new ContentValues();
            contentValues.put("app_id", str);
            contentValues.put("first_open_count", (Integer) 0);
            contentValues.put("previous_install_count", (Integer) 0);
            if (sQLiteDatabaseE_.insertWithOnConflict("app2", null, contentValues, 5) == -1) {
                zzj().zzg().zza("Failed to insert column (got -1). appId", zzgo.zza(str), str2);
                return -1L;
            }
            jZza = 0;
            zzj().zzg().zza("Error inserting column. appId", zzgo.zza(str), str2, e);
            return j;
        }
        try {
            ContentValues contentValues2 = new ContentValues();
            contentValues2.put("app_id", str);
            contentValues2.put(str2, Long.valueOf(1 + jZza));
            if (sQLiteDatabaseE_.update("app2", contentValues2, "app_id = ?", new String[]{str}) == 0) {
                zzj().zzg().zza("Failed to update column (got 0). appId", zzgo.zza(str), str2);
                return -1L;
            }
            sQLiteDatabaseE_.setTransactionSuccessful();
            return jZza;
        } catch (SQLiteException e2) {
            e = e2;
            j = jZza;
        }
    }

    public final long zzb(String str) {
        Preconditions.checkNotEmpty(str);
        zzt();
        zzal();
        return zza("select first_open_count from app2 where app_id=?", new String[]{str}, -1L);
    }

    public final long c_() {
        return zza("select max(bundle_end_timestamp) from queue", (String[]) null, 0L);
    }

    public final long d_() {
        return zza("select max(timestamp) from raw_events", (String[]) null, 0L);
    }

    public final long zzc(String str) {
        Preconditions.checkNotEmpty(str);
        return zza("select count(1) from events where app_id=? and name not like '!_%' escape '!'", new String[]{str}, 0L);
    }

    private final long zzb(String str, String[] strArr) {
        Cursor cursor = null;
        try {
            try {
                Cursor cursorRawQuery = e_().rawQuery(str, strArr);
                if (cursorRawQuery.moveToFirst()) {
                    long j = cursorRawQuery.getLong(0);
                    if (cursorRawQuery != null) {
                        cursorRawQuery.close();
                    }
                    return j;
                }
                throw new SQLiteException("Database returned empty set");
            } catch (SQLiteException e) {
                zzj().zzg().zza("Database error", str, e);
                throw e;
            }
        } catch (Throwable th) {
            if (0 != 0) {
                cursor.close();
            }
            throw th;
        }
    }

    private final long zza(String str, String[] strArr, long j) {
        Cursor cursorRawQuery = null;
        try {
            try {
                cursorRawQuery = e_().rawQuery(str, strArr);
                if (cursorRawQuery.moveToFirst()) {
                    return cursorRawQuery.getLong(0);
                }
                if (cursorRawQuery != null) {
                    cursorRawQuery.close();
                }
                return j;
            } catch (SQLiteException e) {
                zzj().zzg().zza("Database error", str, e);
                throw e;
            }
        } finally {
            if (cursorRawQuery != null) {
                cursorRawQuery.close();
            }
        }
    }

    final SQLiteDatabase e_() {
        zzt();
        try {
            return this.zzl.getWritableDatabase();
        } catch (SQLiteException e) {
            zzj().zzu().zza("Error opening database", e);
            throw e;
        }
    }

    /* JADX WARN: Not initialized variable reg: 1, insn: 0x0089: MOVE (r0 I:??[OBJECT, ARRAY]) = (r1 I:??[OBJECT, ARRAY]), block:B:32:0x0089 */
    /* JADX WARN: Removed duplicated region for block: B:34:0x008c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final android.os.Bundle zzd(java.lang.String r6) throws java.lang.Throwable {
        /*
            r5 = this;
            r5.zzt()
            r5.zzal()
            r0 = 0
            android.database.sqlite.SQLiteDatabase r1 = r5.e_()     // Catch: java.lang.Throwable -> L71 android.database.sqlite.SQLiteException -> L73
            java.lang.String r2 = "select parameters from default_event_params where app_id=?"
            r3 = 1
            java.lang.String[] r3 = new java.lang.String[r3]     // Catch: java.lang.Throwable -> L71 android.database.sqlite.SQLiteException -> L73
            r4 = 0
            r3[r4] = r6     // Catch: java.lang.Throwable -> L71 android.database.sqlite.SQLiteException -> L73
            android.database.Cursor r1 = r1.rawQuery(r2, r3)     // Catch: java.lang.Throwable -> L71 android.database.sqlite.SQLiteException -> L73
            boolean r2 = r1.moveToFirst()     // Catch: android.database.sqlite.SQLiteException -> L6f java.lang.Throwable -> L88
            if (r2 != 0) goto L30
            com.google.android.gms.measurement.internal.zzgo r6 = r5.zzj()     // Catch: android.database.sqlite.SQLiteException -> L6f java.lang.Throwable -> L88
            com.google.android.gms.measurement.internal.zzgq r6 = r6.zzp()     // Catch: android.database.sqlite.SQLiteException -> L6f java.lang.Throwable -> L88
            java.lang.String r2 = "Default event parameters not found"
            r6.zza(r2)     // Catch: android.database.sqlite.SQLiteException -> L6f java.lang.Throwable -> L88
            if (r1 == 0) goto L2f
            r1.close()
        L2f:
            return r0
        L30:
            byte[] r2 = r1.getBlob(r4)     // Catch: android.database.sqlite.SQLiteException -> L6f java.lang.Throwable -> L88
            com.google.android.gms.internal.measurement.zzfy$zzf$zza r3 = com.google.android.gms.internal.measurement.zzfy.zzf.zze()     // Catch: java.io.IOException -> L57 android.database.sqlite.SQLiteException -> L6f java.lang.Throwable -> L88
            com.google.android.gms.internal.measurement.zzlb r2 = com.google.android.gms.measurement.internal.zzoo.zza(r3, r2)     // Catch: java.io.IOException -> L57 android.database.sqlite.SQLiteException -> L6f java.lang.Throwable -> L88
            com.google.android.gms.internal.measurement.zzfy$zzf$zza r2 = (com.google.android.gms.internal.measurement.zzfy.zzf.zza) r2     // Catch: java.io.IOException -> L57 android.database.sqlite.SQLiteException -> L6f java.lang.Throwable -> L88
            com.google.android.gms.internal.measurement.zzlc r2 = r2.zzai()     // Catch: java.io.IOException -> L57 android.database.sqlite.SQLiteException -> L6f java.lang.Throwable -> L88
            com.google.android.gms.internal.measurement.zzjt r2 = (com.google.android.gms.internal.measurement.zzjt) r2     // Catch: java.io.IOException -> L57 android.database.sqlite.SQLiteException -> L6f java.lang.Throwable -> L88
            com.google.android.gms.internal.measurement.zzfy$zzf r2 = (com.google.android.gms.internal.measurement.zzfy.zzf) r2     // Catch: java.io.IOException -> L57 android.database.sqlite.SQLiteException -> L6f java.lang.Throwable -> L88
            r5.g_()     // Catch: android.database.sqlite.SQLiteException -> L6f java.lang.Throwable -> L88
            java.util.List r6 = r2.zzh()     // Catch: android.database.sqlite.SQLiteException -> L6f java.lang.Throwable -> L88
            android.os.Bundle r6 = com.google.android.gms.measurement.internal.zzoo.zza(r6)     // Catch: android.database.sqlite.SQLiteException -> L6f java.lang.Throwable -> L88
            if (r1 == 0) goto L56
            r1.close()
        L56:
            return r6
        L57:
            r2 = move-exception
            com.google.android.gms.measurement.internal.zzgo r3 = r5.zzj()     // Catch: android.database.sqlite.SQLiteException -> L6f java.lang.Throwable -> L88
            com.google.android.gms.measurement.internal.zzgq r3 = r3.zzg()     // Catch: android.database.sqlite.SQLiteException -> L6f java.lang.Throwable -> L88
            java.lang.String r4 = "Failed to retrieve default event parameters. appId"
            java.lang.Object r6 = com.google.android.gms.measurement.internal.zzgo.zza(r6)     // Catch: android.database.sqlite.SQLiteException -> L6f java.lang.Throwable -> L88
            r3.zza(r4, r6, r2)     // Catch: android.database.sqlite.SQLiteException -> L6f java.lang.Throwable -> L88
            if (r1 == 0) goto L6e
            r1.close()
        L6e:
            return r0
        L6f:
            r6 = move-exception
            goto L75
        L71:
            r6 = move-exception
            goto L8a
        L73:
            r6 = move-exception
            r1 = r0
        L75:
            com.google.android.gms.measurement.internal.zzgo r2 = r5.zzj()     // Catch: java.lang.Throwable -> L88
            com.google.android.gms.measurement.internal.zzgq r2 = r2.zzg()     // Catch: java.lang.Throwable -> L88
            java.lang.String r3 = "Error selecting default event parameters"
            r2.zza(r3, r6)     // Catch: java.lang.Throwable -> L88
            if (r1 == 0) goto L87
            r1.close()
        L87:
            return r0
        L88:
            r6 = move-exception
            r0 = r1
        L8a:
            if (r0 == 0) goto L8f
            r0.close()
        L8f:
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzal.zzd(java.lang.String):android.os.Bundle");
    }

    /* JADX WARN: Not initialized variable reg: 1, insn: 0x0091: MOVE (r0 I:??[OBJECT, ARRAY]) = (r1 I:??[OBJECT, ARRAY]), block:B:32:0x0091 */
    /* JADX WARN: Removed duplicated region for block: B:34:0x0094  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final android.util.Pair<com.google.android.gms.internal.measurement.zzfy.zzf, java.lang.Long> zza(java.lang.String r8, java.lang.Long r9) throws java.lang.Throwable {
        /*
            r7 = this;
            r7.zzt()
            r7.zzal()
            r0 = 0
            android.database.sqlite.SQLiteDatabase r1 = r7.e_()     // Catch: java.lang.Throwable -> L79 android.database.sqlite.SQLiteException -> L7b
            java.lang.String r2 = "select main_event, children_to_process from main_event_params where app_id=? and event_id=?"
            r3 = 2
            java.lang.String[] r3 = new java.lang.String[r3]     // Catch: java.lang.Throwable -> L79 android.database.sqlite.SQLiteException -> L7b
            r4 = 0
            r3[r4] = r8     // Catch: java.lang.Throwable -> L79 android.database.sqlite.SQLiteException -> L7b
            java.lang.String r5 = java.lang.String.valueOf(r9)     // Catch: java.lang.Throwable -> L79 android.database.sqlite.SQLiteException -> L7b
            r6 = 1
            r3[r6] = r5     // Catch: java.lang.Throwable -> L79 android.database.sqlite.SQLiteException -> L7b
            android.database.Cursor r1 = r1.rawQuery(r2, r3)     // Catch: java.lang.Throwable -> L79 android.database.sqlite.SQLiteException -> L7b
            boolean r2 = r1.moveToFirst()     // Catch: android.database.sqlite.SQLiteException -> L77 java.lang.Throwable -> L90
            if (r2 != 0) goto L37
            com.google.android.gms.measurement.internal.zzgo r8 = r7.zzj()     // Catch: android.database.sqlite.SQLiteException -> L77 java.lang.Throwable -> L90
            com.google.android.gms.measurement.internal.zzgq r8 = r8.zzp()     // Catch: android.database.sqlite.SQLiteException -> L77 java.lang.Throwable -> L90
            java.lang.String r9 = "Main event not found"
            r8.zza(r9)     // Catch: android.database.sqlite.SQLiteException -> L77 java.lang.Throwable -> L90
            if (r1 == 0) goto L36
            r1.close()
        L36:
            return r0
        L37:
            byte[] r2 = r1.getBlob(r4)     // Catch: android.database.sqlite.SQLiteException -> L77 java.lang.Throwable -> L90
            long r3 = r1.getLong(r6)     // Catch: android.database.sqlite.SQLiteException -> L77 java.lang.Throwable -> L90
            java.lang.Long r3 = java.lang.Long.valueOf(r3)     // Catch: android.database.sqlite.SQLiteException -> L77 java.lang.Throwable -> L90
            com.google.android.gms.internal.measurement.zzfy$zzf$zza r4 = com.google.android.gms.internal.measurement.zzfy.zzf.zze()     // Catch: java.io.IOException -> L5f android.database.sqlite.SQLiteException -> L77 java.lang.Throwable -> L90
            com.google.android.gms.internal.measurement.zzlb r2 = com.google.android.gms.measurement.internal.zzoo.zza(r4, r2)     // Catch: java.io.IOException -> L5f android.database.sqlite.SQLiteException -> L77 java.lang.Throwable -> L90
            com.google.android.gms.internal.measurement.zzfy$zzf$zza r2 = (com.google.android.gms.internal.measurement.zzfy.zzf.zza) r2     // Catch: java.io.IOException -> L5f android.database.sqlite.SQLiteException -> L77 java.lang.Throwable -> L90
            com.google.android.gms.internal.measurement.zzlc r2 = r2.zzai()     // Catch: java.io.IOException -> L5f android.database.sqlite.SQLiteException -> L77 java.lang.Throwable -> L90
            com.google.android.gms.internal.measurement.zzjt r2 = (com.google.android.gms.internal.measurement.zzjt) r2     // Catch: java.io.IOException -> L5f android.database.sqlite.SQLiteException -> L77 java.lang.Throwable -> L90
            com.google.android.gms.internal.measurement.zzfy$zzf r2 = (com.google.android.gms.internal.measurement.zzfy.zzf) r2     // Catch: java.io.IOException -> L5f android.database.sqlite.SQLiteException -> L77 java.lang.Throwable -> L90
            android.util.Pair r8 = android.util.Pair.create(r2, r3)     // Catch: android.database.sqlite.SQLiteException -> L77 java.lang.Throwable -> L90
            if (r1 == 0) goto L5e
            r1.close()
        L5e:
            return r8
        L5f:
            r2 = move-exception
            com.google.android.gms.measurement.internal.zzgo r3 = r7.zzj()     // Catch: android.database.sqlite.SQLiteException -> L77 java.lang.Throwable -> L90
            com.google.android.gms.measurement.internal.zzgq r3 = r3.zzg()     // Catch: android.database.sqlite.SQLiteException -> L77 java.lang.Throwable -> L90
            java.lang.String r4 = "Failed to merge main event. appId, eventId"
            java.lang.Object r8 = com.google.android.gms.measurement.internal.zzgo.zza(r8)     // Catch: android.database.sqlite.SQLiteException -> L77 java.lang.Throwable -> L90
            r3.zza(r4, r8, r9, r2)     // Catch: android.database.sqlite.SQLiteException -> L77 java.lang.Throwable -> L90
            if (r1 == 0) goto L76
            r1.close()
        L76:
            return r0
        L77:
            r8 = move-exception
            goto L7d
        L79:
            r8 = move-exception
            goto L92
        L7b:
            r8 = move-exception
            r1 = r0
        L7d:
            com.google.android.gms.measurement.internal.zzgo r9 = r7.zzj()     // Catch: java.lang.Throwable -> L90
            com.google.android.gms.measurement.internal.zzgq r9 = r9.zzg()     // Catch: java.lang.Throwable -> L90
            java.lang.String r2 = "Error selecting main event"
            r9.zza(r2, r8)     // Catch: java.lang.Throwable -> L90
            if (r1 == 0) goto L8f
            r1.close()
        L8f:
            return r0
        L90:
            r8 = move-exception
            r0 = r1
        L92:
            if (r0 == 0) goto L97
            r0.close()
        L97:
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzal.zza(java.lang.String, java.lang.Long):android.util.Pair");
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Not initialized variable reg: 4, insn: 0x043a: MOVE (r3 I:??[OBJECT, ARRAY]) = (r4 I:??[OBJECT, ARRAY]), block:B:130:0x043a */
    /* JADX WARN: Removed duplicated region for block: B:127:0x0435  */
    /* JADX WARN: Removed duplicated region for block: B:132:0x043d  */
    /* JADX WARN: Type inference failed for: r3v0 */
    /* JADX WARN: Type inference failed for: r3v1, types: [android.database.Cursor] */
    /* JADX WARN: Type inference failed for: r3v2 */
    /* JADX WARN: Type inference failed for: r4v1 */
    /* JADX WARN: Type inference failed for: r4v2, types: [android.database.Cursor] */
    /* JADX WARN: Type inference failed for: r4v4, types: [android.database.Cursor] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final com.google.android.gms.measurement.internal.zzg zze(java.lang.String r28) {
        /*
            Method dump skipped, instruction units count: 1089
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzal.zze(java.lang.String):com.google.android.gms.measurement.internal.zzg");
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:53:0x0166  */
    /* JADX WARN: Type inference failed for: r6v0 */
    /* JADX WARN: Type inference failed for: r6v1, types: [android.database.Cursor] */
    /* JADX WARN: Type inference failed for: r6v2 */
    /* JADX WARN: Type inference failed for: r7v0 */
    /* JADX WARN: Type inference failed for: r7v1 */
    /* JADX WARN: Type inference failed for: r7v2 */
    /* JADX WARN: Type inference failed for: r7v3, types: [android.database.Cursor] */
    /* JADX WARN: Type inference failed for: r7v5, types: [android.database.Cursor] */
    /* JADX WARN: Type inference failed for: r9v1 */
    /* JADX WARN: Type inference failed for: r9v10, types: [com.google.android.gms.measurement.internal.zzal, com.google.android.gms.measurement.internal.zzja, com.google.android.gms.measurement.internal.zzns] */
    /* JADX WARN: Type inference failed for: r9v2 */
    /* JADX WARN: Type inference failed for: r9v3, types: [com.google.android.gms.measurement.internal.zzja] */
    /* JADX WARN: Type inference failed for: r9v6 */
    /* JADX WARN: Type inference failed for: r9v8 */
    /* JADX WARN: Type inference failed for: r9v9 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final com.google.android.gms.measurement.internal.zzae zzc(java.lang.String r33, java.lang.String r34) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 362
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzal.zzc(java.lang.String, java.lang.String):com.google.android.gms.measurement.internal.zzae");
    }

    public final zzan zzf(String str) throws Throwable {
        Throwable th;
        Cursor cursorQuery;
        Preconditions.checkNotEmpty(str);
        zzt();
        zzal();
        Cursor cursor = null;
        try {
            cursorQuery = e_().query("apps", new String[]{"remote_config", "config_last_modified_time", "e_tag"}, "app_id=?", new String[]{str}, null, null, null);
        } catch (SQLiteException e) {
            e = e;
            cursorQuery = null;
        } catch (Throwable th2) {
            th = th2;
        }
        try {
            try {
                if (!cursorQuery.moveToFirst()) {
                    if (cursorQuery != null) {
                        cursorQuery.close();
                    }
                    return null;
                }
                byte[] blob = cursorQuery.getBlob(0);
                String string = cursorQuery.getString(1);
                String string2 = cursorQuery.getString(2);
                if (cursorQuery.moveToNext()) {
                    zzj().zzg().zza("Got multiple records for app config, expected one. appId", zzgo.zza(str));
                }
                if (blob == null) {
                    if (cursorQuery != null) {
                        cursorQuery.close();
                    }
                    return null;
                }
                zzan zzanVar = new zzan(blob, string, string2);
                if (cursorQuery != null) {
                    cursorQuery.close();
                }
                return zzanVar;
            } catch (Throwable th3) {
                th = th3;
                cursor = cursorQuery;
            }
        } catch (SQLiteException e2) {
            e = e2;
            zzj().zzg().zza("Error querying remote config. appId", zzgo.zza(str), e);
            if (cursorQuery != null) {
                cursorQuery.close();
            }
            return null;
        }
        th = th3;
        cursor = cursorQuery;
        if (cursor != null) {
            cursor.close();
            throw th;
        }
        throw th;
    }

    public final zzaq zza(long j, String str, boolean z, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6, boolean z7) {
        return zza(j, str, 1L, false, false, z3, false, z5, z6, z7);
    }

    /* JADX WARN: Removed duplicated region for block: B:43:0x015c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final com.google.android.gms.measurement.internal.zzaq zza(long r22, java.lang.String r24, long r25, boolean r27, boolean r28, boolean r29, boolean r30, boolean r31, boolean r32, boolean r33) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 352
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzal.zza(long, java.lang.String, long, boolean, boolean, boolean, boolean, boolean, boolean, boolean):com.google.android.gms.measurement.internal.zzaq");
    }

    public final zzax zzg(String str) {
        Preconditions.checkNotNull(str);
        zzt();
        zzal();
        return zzax.zza(zza("select dma_consent_settings from consent_settings where app_id=? limit 1;", new String[]{str}, ""));
    }

    public final zzbb zzd(String str, String str2) {
        return zzc("events", str, str2);
    }

    /* JADX WARN: Removed duplicated region for block: B:67:0x0160  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final com.google.android.gms.measurement.internal.zzbb zzc(java.lang.String r27, java.lang.String r28, java.lang.String r29) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 356
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzal.zzc(java.lang.String, java.lang.String, java.lang.String):com.google.android.gms.measurement.internal.zzbb");
    }

    public final zzje zzh(String str) {
        Preconditions.checkNotNull(str);
        zzt();
        zzal();
        return zzje.zzb(zza("select storage_consent_at_bundling from consent_settings where app_id=? limit 1;", new String[]{str}, ""));
    }

    public final zzje zzi(String str) {
        Preconditions.checkNotNull(str);
        zzt();
        zzal();
        zzje zzjeVar = (zzje) zza("select consent_state, consent_source from consent_settings where app_id=? limit 1;", new String[]{str}, new zzau() { // from class: com.google.android.gms.measurement.internal.zzao
            @Override // com.google.android.gms.measurement.internal.zzau
            public final Object zza(Cursor cursor) {
                return zzje.zza(cursor.getString(0), cursor.getInt(1));
            }
        });
        return zzjeVar == null ? zzje.zza : zzjeVar;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:64:0x01a9  */
    /* JADX WARN: Type inference failed for: r2v0 */
    /* JADX WARN: Type inference failed for: r2v1 */
    /* JADX WARN: Type inference failed for: r2v2, types: [android.database.Cursor] */
    /* JADX WARN: Type inference failed for: r2v6 */
    /* JADX WARN: Type inference failed for: r3v1 */
    /* JADX WARN: Type inference failed for: r3v2, types: [android.database.Cursor] */
    /* JADX WARN: Type inference failed for: r3v4, types: [android.database.Cursor] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final com.google.android.gms.measurement.internal.zzoj zzj(java.lang.String r20) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 437
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzal.zzj(java.lang.String):com.google.android.gms.measurement.internal.zzoj");
    }

    /* JADX WARN: Removed duplicated region for block: B:38:0x00ab  */
    /* JADX WARN: Removed duplicated region for block: B:47:? A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final com.google.android.gms.measurement.internal.zzop zze(java.lang.String r14, java.lang.String r15) {
        /*
            r13 = this;
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r14)
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r15)
            r13.zzt()
            r13.zzal()
            r1 = 0
            android.database.sqlite.SQLiteDatabase r2 = r13.e_()     // Catch: java.lang.Throwable -> L7f android.database.sqlite.SQLiteException -> L82
            java.lang.String r3 = "user_attributes"
            r0 = 3
            java.lang.String[] r4 = new java.lang.String[r0]     // Catch: java.lang.Throwable -> L7f android.database.sqlite.SQLiteException -> L82
            java.lang.String r0 = "set_timestamp"
            r10 = 0
            r4[r10] = r0     // Catch: java.lang.Throwable -> L7f android.database.sqlite.SQLiteException -> L82
            java.lang.String r0 = "value"
            r11 = 1
            r4[r11] = r0     // Catch: java.lang.Throwable -> L7f android.database.sqlite.SQLiteException -> L82
            java.lang.String r0 = "origin"
            r12 = 2
            r4[r12] = r0     // Catch: java.lang.Throwable -> L7f android.database.sqlite.SQLiteException -> L82
            java.lang.String r5 = "app_id=? and name=?"
            java.lang.String[] r6 = new java.lang.String[r12]     // Catch: java.lang.Throwable -> L7f android.database.sqlite.SQLiteException -> L82
            r6[r10] = r14     // Catch: java.lang.Throwable -> L7f android.database.sqlite.SQLiteException -> L82
            r6[r11] = r15     // Catch: java.lang.Throwable -> L7f android.database.sqlite.SQLiteException -> L82
            r8 = 0
            r9 = 0
            r7 = 0
            android.database.Cursor r2 = r2.query(r3, r4, r5, r6, r7, r8, r9)     // Catch: java.lang.Throwable -> L7f android.database.sqlite.SQLiteException -> L82
            boolean r0 = r2.moveToFirst()     // Catch: android.database.sqlite.SQLiteException -> L7a java.lang.Throwable -> La6
            if (r0 != 0) goto L40
            if (r2 == 0) goto L3f
            r2.close()
        L3f:
            return r1
        L40:
            long r7 = r2.getLong(r10)     // Catch: android.database.sqlite.SQLiteException -> L7a java.lang.Throwable -> La6
            java.lang.Object r9 = r13.zza(r2, r11)     // Catch: android.database.sqlite.SQLiteException -> L7a java.lang.Throwable -> La6
            if (r9 != 0) goto L50
            if (r2 == 0) goto L4f
            r2.close()
        L4f:
            return r1
        L50:
            java.lang.String r5 = r2.getString(r12)     // Catch: android.database.sqlite.SQLiteException -> L7a java.lang.Throwable -> La6
            com.google.android.gms.measurement.internal.zzop r3 = new com.google.android.gms.measurement.internal.zzop     // Catch: android.database.sqlite.SQLiteException -> L7a java.lang.Throwable -> La6
            r4 = r14
            r6 = r15
            r3.<init>(r4, r5, r6, r7, r9)     // Catch: android.database.sqlite.SQLiteException -> L78 java.lang.Throwable -> La6
            boolean r14 = r2.moveToNext()     // Catch: android.database.sqlite.SQLiteException -> L78 java.lang.Throwable -> La6
            if (r14 == 0) goto L72
            com.google.android.gms.measurement.internal.zzgo r14 = r13.zzj()     // Catch: android.database.sqlite.SQLiteException -> L78 java.lang.Throwable -> La6
            com.google.android.gms.measurement.internal.zzgq r14 = r14.zzg()     // Catch: android.database.sqlite.SQLiteException -> L78 java.lang.Throwable -> La6
            java.lang.String r15 = "Got multiple records for user property, expected one. appId"
            java.lang.Object r0 = com.google.android.gms.measurement.internal.zzgo.zza(r4)     // Catch: android.database.sqlite.SQLiteException -> L78 java.lang.Throwable -> La6
            r14.zza(r15, r0)     // Catch: android.database.sqlite.SQLiteException -> L78 java.lang.Throwable -> La6
        L72:
            if (r2 == 0) goto L77
            r2.close()
        L77:
            return r3
        L78:
            r0 = move-exception
            goto L7d
        L7a:
            r0 = move-exception
            r4 = r14
            r6 = r15
        L7d:
            r14 = r0
            goto L87
        L7f:
            r0 = move-exception
            r14 = r0
            goto La9
        L82:
            r0 = move-exception
            r4 = r14
            r6 = r15
            r14 = r0
            r2 = r1
        L87:
            com.google.android.gms.measurement.internal.zzgo r15 = r13.zzj()     // Catch: java.lang.Throwable -> La6
            com.google.android.gms.measurement.internal.zzgq r15 = r15.zzg()     // Catch: java.lang.Throwable -> La6
            java.lang.String r0 = "Error querying user property. appId"
            java.lang.Object r3 = com.google.android.gms.measurement.internal.zzgo.zza(r4)     // Catch: java.lang.Throwable -> La6
            com.google.android.gms.measurement.internal.zzgh r4 = r13.zzi()     // Catch: java.lang.Throwable -> La6
            java.lang.String r4 = r4.zzc(r6)     // Catch: java.lang.Throwable -> La6
            r15.zza(r0, r3, r4, r14)     // Catch: java.lang.Throwable -> La6
            if (r2 == 0) goto La5
            r2.close()
        La5:
            return r1
        La6:
            r0 = move-exception
            r14 = r0
            r1 = r2
        La9:
            if (r1 == 0) goto Lae
            r1.close()
        Lae:
            throw r14
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzal.zze(java.lang.String, java.lang.String):com.google.android.gms.measurement.internal.zzop");
    }

    private final Object zza(Cursor cursor, int i) {
        int type = cursor.getType(i);
        if (type == 0) {
            zzj().zzg().zza("Loaded invalid null value from database");
            return null;
        }
        if (type == 1) {
            return Long.valueOf(cursor.getLong(i));
        }
        if (type == 2) {
            return Double.valueOf(cursor.getDouble(i));
        }
        if (type == 3) {
            return cursor.getString(i);
        }
        if (type == 4) {
            zzj().zzg().zza("Loaded invalid blob type value, ignoring it");
            return null;
        }
        zzj().zzg().zza("Loaded invalid unknown value type, ignoring it", Integer.valueOf(type));
        return null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0049  */
    /* JADX WARN: Type inference failed for: r0v0 */
    /* JADX WARN: Type inference failed for: r0v1, types: [android.database.Cursor] */
    /* JADX WARN: Type inference failed for: r0v2 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final <T> T zza(java.lang.String r3, java.lang.String[] r4, com.google.android.gms.measurement.internal.zzau<T> r5) throws java.lang.Throwable {
        /*
            r2 = this;
            r0 = 0
            android.database.sqlite.SQLiteDatabase r1 = r2.e_()     // Catch: java.lang.Throwable -> L2e android.database.sqlite.SQLiteException -> L30
            android.database.Cursor r3 = r1.rawQuery(r3, r4)     // Catch: java.lang.Throwable -> L2e android.database.sqlite.SQLiteException -> L30
            boolean r4 = r3.moveToFirst()     // Catch: android.database.sqlite.SQLiteException -> L2c java.lang.Throwable -> L45
            if (r4 != 0) goto L22
            com.google.android.gms.measurement.internal.zzgo r4 = r2.zzj()     // Catch: android.database.sqlite.SQLiteException -> L2c java.lang.Throwable -> L45
            com.google.android.gms.measurement.internal.zzgq r4 = r4.zzp()     // Catch: android.database.sqlite.SQLiteException -> L2c java.lang.Throwable -> L45
            java.lang.String r5 = "No data found"
            r4.zza(r5)     // Catch: android.database.sqlite.SQLiteException -> L2c java.lang.Throwable -> L45
            if (r3 == 0) goto L21
            r3.close()
        L21:
            return r0
        L22:
            java.lang.Object r4 = r5.zza(r3)     // Catch: android.database.sqlite.SQLiteException -> L2c java.lang.Throwable -> L45
            if (r3 == 0) goto L2b
            r3.close()
        L2b:
            return r4
        L2c:
            r4 = move-exception
            goto L32
        L2e:
            r4 = move-exception
            goto L47
        L30:
            r4 = move-exception
            r3 = r0
        L32:
            com.google.android.gms.measurement.internal.zzgo r5 = r2.zzj()     // Catch: java.lang.Throwable -> L45
            com.google.android.gms.measurement.internal.zzgq r5 = r5.zzg()     // Catch: java.lang.Throwable -> L45
            java.lang.String r1 = "Error querying database."
            r5.zza(r1, r4)     // Catch: java.lang.Throwable -> L45
            if (r3 == 0) goto L44
            r3.close()
        L44:
            return r0
        L45:
            r4 = move-exception
            r0 = r3
        L47:
            if (r0 == 0) goto L4c
            r0.close()
        L4c:
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzal.zza(java.lang.String, java.lang.String[], com.google.android.gms.measurement.internal.zzau):java.lang.Object");
    }

    private final String zzao() {
        long jCurrentTimeMillis = zzb().currentTimeMillis();
        return "(" + ("(upload_type = " + zznt.GOOGLE_SIGNAL.zza() + " AND (ABS(creation_timestamp - " + jCurrentTimeMillis + ") > CAST(" + zzbh.zzaf.zza(null).longValue() + " AS INTEGER)))") + " OR " + ("(upload_type != " + zznt.GOOGLE_SIGNAL.zza() + " AND (ABS(creation_timestamp - " + jCurrentTimeMillis + ") > CAST(" + zzag.zzm() + " AS INTEGER)))") + ")";
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:27:0x005b  */
    /* JADX WARN: Type inference failed for: r0v0 */
    /* JADX WARN: Type inference failed for: r0v1, types: [android.database.Cursor] */
    /* JADX WARN: Type inference failed for: r0v2 */
    /* JADX WARN: Type inference failed for: r5v0, types: [long] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.String zza(long r5) throws java.lang.Throwable {
        /*
            r4 = this;
            r4.zzt()
            r4.zzal()
            r0 = 0
            android.database.sqlite.SQLiteDatabase r1 = r4.e_()     // Catch: java.lang.Throwable -> L40 android.database.sqlite.SQLiteException -> L42
            java.lang.String r2 = "select app_id from apps where app_id in (select distinct app_id from raw_events) and config_fetched_time < ? order by failed_config_fetch_time limit 1;"
            r3 = 1
            java.lang.String[] r3 = new java.lang.String[r3]     // Catch: java.lang.Throwable -> L40 android.database.sqlite.SQLiteException -> L42
            java.lang.String r5 = java.lang.String.valueOf(r5)     // Catch: java.lang.Throwable -> L40 android.database.sqlite.SQLiteException -> L42
            r6 = 0
            r3[r6] = r5     // Catch: java.lang.Throwable -> L40 android.database.sqlite.SQLiteException -> L42
            android.database.Cursor r5 = r1.rawQuery(r2, r3)     // Catch: java.lang.Throwable -> L40 android.database.sqlite.SQLiteException -> L42
            boolean r1 = r5.moveToFirst()     // Catch: android.database.sqlite.SQLiteException -> L3e java.lang.Throwable -> L57
            if (r1 != 0) goto L34
            com.google.android.gms.measurement.internal.zzgo r6 = r4.zzj()     // Catch: android.database.sqlite.SQLiteException -> L3e java.lang.Throwable -> L57
            com.google.android.gms.measurement.internal.zzgq r6 = r6.zzp()     // Catch: android.database.sqlite.SQLiteException -> L3e java.lang.Throwable -> L57
            java.lang.String r1 = "No expired configs for apps with pending events"
            r6.zza(r1)     // Catch: android.database.sqlite.SQLiteException -> L3e java.lang.Throwable -> L57
            if (r5 == 0) goto L33
            r5.close()
        L33:
            return r0
        L34:
            java.lang.String r6 = r5.getString(r6)     // Catch: android.database.sqlite.SQLiteException -> L3e java.lang.Throwable -> L57
            if (r5 == 0) goto L3d
            r5.close()
        L3d:
            return r6
        L3e:
            r6 = move-exception
            goto L44
        L40:
            r6 = move-exception
            goto L59
        L42:
            r6 = move-exception
            r5 = r0
        L44:
            com.google.android.gms.measurement.internal.zzgo r1 = r4.zzj()     // Catch: java.lang.Throwable -> L57
            com.google.android.gms.measurement.internal.zzgq r1 = r1.zzg()     // Catch: java.lang.Throwable -> L57
            java.lang.String r2 = "Error selecting expired configs"
            r1.zza(r2, r6)     // Catch: java.lang.Throwable -> L57
            if (r5 == 0) goto L56
            r5.close()
        L56:
            return r0
        L57:
            r6 = move-exception
            r0 = r5
        L59:
            if (r0 == 0) goto L5e
            r0.close()
        L5e:
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzal.zza(long):java.lang.String");
    }

    public final String f_() throws Throwable {
        Throwable th;
        Cursor cursorRawQuery;
        try {
            cursorRawQuery = e_().rawQuery("select app_id from queue order by has_realtime desc, rowid asc limit 1;", null);
        } catch (SQLiteException e) {
            e = e;
            cursorRawQuery = null;
        } catch (Throwable th2) {
            th = th2;
            cursorRawQuery = null;
        }
        try {
            try {
                if (!cursorRawQuery.moveToFirst()) {
                    if (cursorRawQuery != null) {
                        cursorRawQuery.close();
                    }
                    return null;
                }
                String string = cursorRawQuery.getString(0);
                if (cursorRawQuery != null) {
                    cursorRawQuery.close();
                }
                return string;
            } catch (Throwable th3) {
                th = th3;
            }
        } catch (SQLiteException e2) {
            e = e2;
            zzj().zzg().zza("Database error getting next bundle app id", e);
            if (cursorRawQuery != null) {
                cursorRawQuery.close();
            }
            return null;
        }
        th = th3;
        if (cursorRawQuery != null) {
            cursorRawQuery.close();
        }
        throw th;
    }

    private final String zza(String str, String[] strArr, String str2) {
        Cursor cursorRawQuery = null;
        try {
            try {
                cursorRawQuery = e_().rawQuery(str, strArr);
                if (cursorRawQuery.moveToFirst()) {
                    return cursorRawQuery.getString(0);
                }
                if (cursorRawQuery != null) {
                    cursorRawQuery.close();
                }
                return str2;
            } catch (SQLiteException e) {
                zzj().zzg().zza("Database error", str, e);
                throw e;
            }
        } finally {
            if (cursorRawQuery != null) {
                cursorRawQuery.close();
            }
        }
    }

    public final List<Pair<zzfy.zzk, Long>> zza(String str, int i, int i2) {
        byte[] bArrZzc;
        long jZzc;
        long jZzc2;
        zzt();
        zzal();
        int i3 = 1;
        Preconditions.checkArgument(i > 0);
        Preconditions.checkArgument(i2 > 0);
        Preconditions.checkNotEmpty(str);
        Cursor cursor = null;
        try {
            try {
                Cursor cursorQuery = e_().query("queue", new String[]{"rowid", "data", "retry_count"}, "app_id=?", new String[]{str}, null, null, "rowid", String.valueOf(i));
                if (!cursorQuery.moveToFirst()) {
                    List<Pair<zzfy.zzk, Long>> listEmptyList = Collections.emptyList();
                    if (cursorQuery != null) {
                        cursorQuery.close();
                    }
                    return listEmptyList;
                }
                ArrayList arrayList = new ArrayList();
                int length = 0;
                while (true) {
                    long j = cursorQuery.getLong(0);
                    try {
                        bArrZzc = g_().zzc(cursorQuery.getBlob(i3));
                    } catch (IOException e) {
                        zzj().zzg().zza("Failed to unzip queued bundle. appId", zzgo.zza(str), e);
                    }
                    if (!arrayList.isEmpty() && bArrZzc.length + length > i2) {
                        break;
                    }
                    try {
                        zzfy.zzk.zza zzaVar = (zzfy.zzk.zza) zzoo.zza(zzfy.zzk.zzw(), bArrZzc);
                        if (!arrayList.isEmpty()) {
                            zzfy.zzk zzkVar = (zzfy.zzk) ((Pair) arrayList.get(0)).first;
                            zzfy.zzk zzkVar2 = (zzfy.zzk) ((com.google.android.gms.internal.measurement.zzjt) zzaVar.zzai());
                            if (!zzkVar.zzae().equals(zzkVar2.zzae()) || !zzkVar.zzad().equals(zzkVar2.zzad()) || zzkVar.zzau() != zzkVar2.zzau() || !zzkVar.zzaf().equals(zzkVar2.zzaf())) {
                                break;
                            }
                            Iterator<zzfy.zzo> it = zzkVar.zzas().iterator();
                            while (true) {
                                jZzc = -1;
                                if (!it.hasNext()) {
                                    jZzc2 = -1;
                                    break;
                                }
                                zzfy.zzo next = it.next();
                                if ("_npa".equals(next.zzg())) {
                                    jZzc2 = next.zzc();
                                    break;
                                }
                            }
                            Iterator<zzfy.zzo> it2 = zzkVar2.zzas().iterator();
                            while (true) {
                                if (!it2.hasNext()) {
                                    break;
                                }
                                zzfy.zzo next2 = it2.next();
                                if ("_npa".equals(next2.zzg())) {
                                    jZzc = next2.zzc();
                                    break;
                                }
                            }
                            if (jZzc2 != jZzc) {
                                break;
                            }
                        }
                        if (!cursorQuery.isNull(2)) {
                            zzaVar.zzi(cursorQuery.getInt(2));
                        }
                        length += bArrZzc.length;
                        arrayList.add(Pair.create((zzfy.zzk) ((com.google.android.gms.internal.measurement.zzjt) zzaVar.zzai()), Long.valueOf(j)));
                    } catch (IOException e2) {
                        zzj().zzg().zza("Failed to merge queued bundle. appId", zzgo.zza(str), e2);
                    }
                    if (!cursorQuery.moveToNext() || length > i2) {
                        break;
                    }
                    i3 = 1;
                }
                if (cursorQuery != null) {
                    cursorQuery.close();
                }
                return arrayList;
            } catch (SQLiteException e3) {
                zzj().zzg().zza("Error querying bundles. appId", zzgo.zza(str), e3);
                List<Pair<zzfy.zzk, Long>> listEmptyList2 = Collections.emptyList();
                if (0 != 0) {
                    cursor.close();
                }
                return listEmptyList2;
            }
        } catch (Throwable th) {
            if (0 != 0) {
                cursor.close();
            }
            throw th;
        }
    }

    public final List<zzae> zza(String str, String str2, String str3) {
        Preconditions.checkNotEmpty(str);
        zzt();
        zzal();
        ArrayList arrayList = new ArrayList(3);
        arrayList.add(str);
        StringBuilder sb = new StringBuilder("app_id=?");
        if (!TextUtils.isEmpty(str2)) {
            arrayList.add(str2);
            sb.append(" and origin=?");
        }
        if (!TextUtils.isEmpty(str3)) {
            arrayList.add(str3 + "*");
            sb.append(" and name glob ?");
        }
        return zza(sb.toString(), (String[]) arrayList.toArray(new String[arrayList.size()]));
    }

    /* JADX WARN: Code restructure failed: missing block: B:12:0x0089, code lost:
    
        zzj().zzg().zza("Read more than the max allowed conditional properties, ignoring extra", 1000);
     */
    /* JADX WARN: Removed duplicated region for block: B:45:0x0171  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.util.List<com.google.android.gms.measurement.internal.zzae> zza(java.lang.String r33, java.lang.String[] r34) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 373
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzal.zza(java.lang.String, java.lang.String[]):java.util.List");
    }

    public final List<zzno> zzk(String str) {
        Preconditions.checkNotEmpty(str);
        zzt();
        zzal();
        ArrayList arrayList = new ArrayList();
        Cursor cursorQuery = null;
        try {
            try {
                cursorQuery = e_().query("trigger_uris", new String[]{"trigger_uri", "timestamp_millis", FirebaseAnalytics.Param.SOURCE}, "app_id=?", new String[]{str}, null, null, "rowid", null);
                if (cursorQuery.moveToFirst()) {
                    do {
                        String string = cursorQuery.getString(0);
                        if (string == null) {
                            string = "";
                        }
                        arrayList.add(new zzno(string, cursorQuery.getLong(1), cursorQuery.getInt(2)));
                    } while (cursorQuery.moveToNext());
                    if (cursorQuery != null) {
                        cursorQuery.close();
                    }
                } else if (cursorQuery != null) {
                    cursorQuery.close();
                    return arrayList;
                }
                return arrayList;
            } catch (SQLiteException e) {
                zzj().zzg().zza("Error querying trigger uris. appId", zzgo.zza(str), e);
                List<zzno> listEmptyList = Collections.emptyList();
                if (cursorQuery != null) {
                    cursorQuery.close();
                }
                return listEmptyList;
            }
        } finally {
        }
    }

    public final List<zzop> zzl(String str) {
        Preconditions.checkNotEmpty(str);
        zzt();
        zzal();
        ArrayList arrayList = new ArrayList();
        Cursor cursorQuery = null;
        try {
            try {
                cursorQuery = e_().query("user_attributes", new String[]{"name", "origin", "set_timestamp", "value"}, "app_id=?", new String[]{str}, null, null, "rowid", "1000");
                if (cursorQuery.moveToFirst()) {
                    do {
                        String string = cursorQuery.getString(0);
                        String string2 = cursorQuery.getString(1);
                        if (string2 == null) {
                            string2 = "";
                        }
                        String str2 = string2;
                        long j = cursorQuery.getLong(2);
                        Object objZza = zza(cursorQuery, 3);
                        if (objZza == null) {
                            zzj().zzg().zza("Read invalid user property value, ignoring it. appId", zzgo.zza(str));
                        } else {
                            arrayList.add(new zzop(str, str2, string, j, objZza));
                        }
                    } while (cursorQuery.moveToNext());
                    if (cursorQuery != null) {
                        cursorQuery.close();
                    }
                } else if (cursorQuery != null) {
                    cursorQuery.close();
                    return arrayList;
                }
                return arrayList;
            } catch (SQLiteException e) {
                zzj().zzg().zza("Error querying user properties. appId", zzgo.zza(str), e);
                List<zzop> listEmptyList = Collections.emptyList();
                if (cursorQuery != null) {
                    cursorQuery.close();
                }
                return listEmptyList;
            }
        } catch (Throwable th) {
            if (cursorQuery != null) {
                cursorQuery.close();
            }
            throw th;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:25:0x00b7, code lost:
    
        zzj().zzg().zza("Read more than the max allowed user properties, ignoring excess", 1000);
     */
    /* JADX WARN: Removed duplicated region for block: B:57:0x013f  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x0146  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.util.List<com.google.android.gms.measurement.internal.zzop> zzb(java.lang.String r19, java.lang.String r20, java.lang.String r21) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 330
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzal.zzb(java.lang.String, java.lang.String, java.lang.String):java.util.List");
    }

    final Map<Integer, zzfy.zzm> zzm(String str) {
        zzal();
        zzt();
        Preconditions.checkNotEmpty(str);
        Cursor cursor = null;
        try {
            try {
                Cursor cursorQuery = e_().query("audience_filter_values", new String[]{"audience_id", "current_results"}, "app_id=?", new String[]{str}, null, null, null);
                if (!cursorQuery.moveToFirst()) {
                    Map<Integer, zzfy.zzm> mapEmptyMap = Collections.emptyMap();
                    if (cursorQuery != null) {
                        cursorQuery.close();
                    }
                    return mapEmptyMap;
                }
                ArrayMap arrayMap = new ArrayMap();
                do {
                    int i = cursorQuery.getInt(0);
                    try {
                        arrayMap.put(Integer.valueOf(i), (zzfy.zzm) ((com.google.android.gms.internal.measurement.zzjt) ((zzfy.zzm.zza) zzoo.zza(zzfy.zzm.zze(), cursorQuery.getBlob(1))).zzai()));
                    } catch (IOException e) {
                        zzj().zzg().zza("Failed to merge filter results. appId, audienceId, error", zzgo.zza(str), Integer.valueOf(i), e);
                    }
                } while (cursorQuery.moveToNext());
                if (cursorQuery != null) {
                    cursorQuery.close();
                }
                return arrayMap;
            } catch (SQLiteException e2) {
                zzj().zzg().zza("Database error querying filter results. appId", zzgo.zza(str), e2);
                Map<Integer, zzfy.zzm> mapEmptyMap2 = Collections.emptyMap();
                if (0 != 0) {
                    cursor.close();
                }
                return mapEmptyMap2;
            }
        } finally {
        }
    }

    final Map<Integer, List<zzfo.zzb>> zzn(String str) {
        Preconditions.checkNotEmpty(str);
        ArrayMap arrayMap = new ArrayMap();
        Cursor cursor = null;
        try {
            try {
                Cursor cursorQuery = e_().query("event_filters", new String[]{"audience_id", "data"}, "app_id=?", new String[]{str}, null, null, null);
                if (!cursorQuery.moveToFirst()) {
                    Map<Integer, List<zzfo.zzb>> mapEmptyMap = Collections.emptyMap();
                    if (cursorQuery != null) {
                        cursorQuery.close();
                    }
                    return mapEmptyMap;
                }
                do {
                    try {
                        zzfo.zzb zzbVar = (zzfo.zzb) ((com.google.android.gms.internal.measurement.zzjt) ((zzfo.zzb.zza) zzoo.zza(zzfo.zzb.zzc(), cursorQuery.getBlob(1))).zzai());
                        if (zzbVar.zzk()) {
                            int i = cursorQuery.getInt(0);
                            List arrayList = (List) arrayMap.get(Integer.valueOf(i));
                            if (arrayList == null) {
                                arrayList = new ArrayList();
                                arrayMap.put(Integer.valueOf(i), arrayList);
                            }
                            arrayList.add(zzbVar);
                        }
                    } catch (IOException e) {
                        zzj().zzg().zza("Failed to merge filter. appId", zzgo.zza(str), e);
                    }
                } while (cursorQuery.moveToNext());
                if (cursorQuery != null) {
                    cursorQuery.close();
                }
                return arrayMap;
            } catch (SQLiteException e2) {
                zzj().zzg().zza("Database error querying filters. appId", zzgo.zza(str), e2);
                Map<Integer, List<zzfo.zzb>> mapEmptyMap2 = Collections.emptyMap();
                if (0 != 0) {
                    cursor.close();
                }
                return mapEmptyMap2;
            }
        } finally {
        }
    }

    final Map<Integer, List<zzfo.zzb>> zzf(String str, String str2) {
        zzal();
        zzt();
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotEmpty(str2);
        ArrayMap arrayMap = new ArrayMap();
        Cursor cursor = null;
        try {
            try {
                Cursor cursorQuery = e_().query("event_filters", new String[]{"audience_id", "data"}, "app_id=? AND event_name=?", new String[]{str, str2}, null, null, null);
                if (!cursorQuery.moveToFirst()) {
                    Map<Integer, List<zzfo.zzb>> mapEmptyMap = Collections.emptyMap();
                    if (cursorQuery != null) {
                        cursorQuery.close();
                    }
                    return mapEmptyMap;
                }
                do {
                    try {
                        zzfo.zzb zzbVar = (zzfo.zzb) ((com.google.android.gms.internal.measurement.zzjt) ((zzfo.zzb.zza) zzoo.zza(zzfo.zzb.zzc(), cursorQuery.getBlob(1))).zzai());
                        int i = cursorQuery.getInt(0);
                        List arrayList = (List) arrayMap.get(Integer.valueOf(i));
                        if (arrayList == null) {
                            arrayList = new ArrayList();
                            arrayMap.put(Integer.valueOf(i), arrayList);
                        }
                        arrayList.add(zzbVar);
                    } catch (IOException e) {
                        zzj().zzg().zza("Failed to merge filter. appId", zzgo.zza(str), e);
                    }
                } while (cursorQuery.moveToNext());
                if (cursorQuery != null) {
                    cursorQuery.close();
                }
                return arrayMap;
            } catch (SQLiteException e2) {
                zzj().zzg().zza("Database error querying filters. appId", zzgo.zza(str), e2);
                Map<Integer, List<zzfo.zzb>> mapEmptyMap2 = Collections.emptyMap();
                if (0 != 0) {
                    cursor.close();
                }
                return mapEmptyMap2;
            }
        } finally {
        }
    }

    final Map<Integer, List<zzfo.zze>> zzg(String str, String str2) {
        zzal();
        zzt();
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotEmpty(str2);
        ArrayMap arrayMap = new ArrayMap();
        Cursor cursor = null;
        try {
            try {
                Cursor cursorQuery = e_().query("property_filters", new String[]{"audience_id", "data"}, "app_id=? AND property_name=?", new String[]{str, str2}, null, null, null);
                if (!cursorQuery.moveToFirst()) {
                    Map<Integer, List<zzfo.zze>> mapEmptyMap = Collections.emptyMap();
                    if (cursorQuery != null) {
                        cursorQuery.close();
                    }
                    return mapEmptyMap;
                }
                do {
                    try {
                        zzfo.zze zzeVar = (zzfo.zze) ((com.google.android.gms.internal.measurement.zzjt) ((zzfo.zze.zza) zzoo.zza(zzfo.zze.zzc(), cursorQuery.getBlob(1))).zzai());
                        int i = cursorQuery.getInt(0);
                        List arrayList = (List) arrayMap.get(Integer.valueOf(i));
                        if (arrayList == null) {
                            arrayList = new ArrayList();
                            arrayMap.put(Integer.valueOf(i), arrayList);
                        }
                        arrayList.add(zzeVar);
                    } catch (IOException e) {
                        zzj().zzg().zza("Failed to merge filter", zzgo.zza(str), e);
                    }
                } while (cursorQuery.moveToNext());
                if (cursorQuery != null) {
                    cursorQuery.close();
                }
                return arrayMap;
            } catch (SQLiteException e2) {
                zzj().zzg().zza("Database error querying filters. appId", zzgo.zza(str), e2);
                Map<Integer, List<zzfo.zze>> mapEmptyMap2 = Collections.emptyMap();
                if (0 != 0) {
                    cursor.close();
                }
                return mapEmptyMap2;
            }
        } finally {
        }
    }

    final Map<Integer, List<Integer>> zzo(String str) {
        zzal();
        zzt();
        Preconditions.checkNotEmpty(str);
        ArrayMap arrayMap = new ArrayMap();
        Cursor cursor = null;
        try {
            try {
                Cursor cursorRawQuery = e_().rawQuery("select audience_id, filter_id from event_filters where app_id = ? and session_scoped = 1 UNION select audience_id, filter_id from property_filters where app_id = ? and session_scoped = 1;", new String[]{str, str});
                if (!cursorRawQuery.moveToFirst()) {
                    Map<Integer, List<Integer>> mapEmptyMap = Collections.emptyMap();
                    if (cursorRawQuery != null) {
                        cursorRawQuery.close();
                    }
                    return mapEmptyMap;
                }
                do {
                    int i = cursorRawQuery.getInt(0);
                    List arrayList = (List) arrayMap.get(Integer.valueOf(i));
                    if (arrayList == null) {
                        arrayList = new ArrayList();
                        arrayMap.put(Integer.valueOf(i), arrayList);
                    }
                    arrayList.add(Integer.valueOf(cursorRawQuery.getInt(1)));
                } while (cursorRawQuery.moveToNext());
                if (cursorRawQuery != null) {
                    cursorRawQuery.close();
                }
                return arrayMap;
            } catch (SQLiteException e) {
                zzj().zzg().zza("Database error querying scoped filters. appId", zzgo.zza(str), e);
                Map<Integer, List<Integer>> mapEmptyMap2 = Collections.emptyMap();
                if (0 != 0) {
                    cursor.close();
                }
                return mapEmptyMap2;
            }
        } catch (Throwable th) {
            if (0 != 0) {
                cursor.close();
            }
            throw th;
        }
    }

    zzal(zznv zznvVar) {
        super(zznvVar);
        this.zzm = new zznl(zzb());
        this.zzl = new zzat(this, zza(), "google_app_measurement.db");
    }

    public final void zza(String str, Bundle bundle) {
        zzal zzalVar = this;
        Preconditions.checkNotNull(bundle);
        zzalVar.zzt();
        zzalVar.zzal();
        String str2 = str;
        zzar zzarVar = new zzar(zzalVar, str2);
        List<zzap> listZza = zzarVar.zza();
        while (!listZza.isEmpty()) {
            for (zzap zzapVar : listZza) {
                zzoo zzooVarG_ = zzalVar.g_();
                zzfy.zzf zzfVar = zzapVar.zzd;
                Bundle bundle2 = new Bundle();
                for (zzfy.zzh zzhVar : zzfVar.zzh()) {
                    if (zzhVar.zzj()) {
                        bundle2.putDouble(zzhVar.zzg(), zzhVar.zza());
                    } else if (zzhVar.zzk()) {
                        bundle2.putFloat(zzhVar.zzg(), zzhVar.zzb());
                    } else if (zzhVar.zzl()) {
                        bundle2.putLong(zzhVar.zzg(), zzhVar.zzd());
                    } else if (zzhVar.zzn()) {
                        bundle2.putString(zzhVar.zzg(), zzhVar.zzh());
                    } else if (!zzhVar.zzi().isEmpty()) {
                        bundle2.putParcelableArray(zzhVar.zzg(), zzoo.zzb(zzhVar.zzi()));
                    } else {
                        zzooVarG_.zzj().zzg().zza("Unexpected parameter type for parameter", zzhVar);
                    }
                }
                String string = bundle2.getString("_o");
                bundle2.remove("_o");
                String strZzg = zzfVar.zzg();
                if (string == null) {
                    string = "";
                }
                zzgs zzgsVar = new zzgs(strZzg, string, bundle2, zzfVar.zzd());
                zzalVar.zzq().zza(zzgsVar.zzc, bundle);
                zzalVar.zza(zzapVar.zza, new zzbc(zzalVar.zzu, zzgsVar.zzb, str2, zzapVar.zzd.zzg(), zzapVar.zzd.zzd(), zzapVar.zzd.zzc(), zzgsVar.zzc), zzapVar.zzb, zzapVar.zzc);
                zzalVar = this;
                str2 = str;
            }
            listZza = zzarVar.zza();
            zzalVar = this;
            str2 = str;
        }
    }

    public final void zzp() {
        zzal();
        e_().beginTransaction();
    }

    public final void zzp(String str) {
        zzt();
        zzal();
        try {
            e_().execSQL("delete from default_event_params where app_id=?", new String[]{str});
        } catch (SQLiteException e) {
            zzj().zzg().zza("Error clearing default event params", e);
        }
    }

    private final void zzi(String str, String str2) {
        Preconditions.checkNotEmpty(str2);
        zzt();
        zzal();
        try {
            e_().delete(str, "app_id=?", new String[]{str2});
        } catch (SQLiteException e) {
            zzj().zzg().zza("Error deleting snapshot. appId", zzgo.zza(str2), e);
        }
    }

    public final void zzq(String str) {
        zzbb zzbbVarZzd;
        zzi("events_snapshot", str);
        Cursor cursorQuery = null;
        try {
            try {
                cursorQuery = e_().query("events", (String[]) Collections.singletonList("name").toArray(new String[0]), "app_id=?", new String[]{str}, null, null, null);
                if (!cursorQuery.moveToFirst()) {
                    if (cursorQuery != null) {
                        cursorQuery.close();
                        return;
                    }
                    return;
                }
                do {
                    String string = cursorQuery.getString(0);
                    if (string != null && (zzbbVarZzd = zzd(str, string)) != null) {
                        zza("events_snapshot", zzbbVarZzd);
                    }
                } while (cursorQuery.moveToNext());
                if (cursorQuery != null) {
                    cursorQuery.close();
                }
            } catch (SQLiteException e) {
                zzj().zzg().zza("Error creating snapshot. appId", zzgo.zza(str), e);
                if (cursorQuery != null) {
                    cursorQuery.close();
                }
            }
        } finally {
        }
    }

    public final void zzu() {
        zzal();
        e_().endTransaction();
    }

    final void zza(List<Long> list) {
        zzt();
        zzal();
        Preconditions.checkNotNull(list);
        Preconditions.checkNotZero(list.size());
        if (zzaa()) {
            String str = "(" + TextUtils.join(",", list) + ")";
            if (zzb("SELECT COUNT(1) FROM queue WHERE rowid IN " + str + " AND retry_count =  2147483647 LIMIT 1", (String[]) null) > 0) {
                zzj().zzu().zza("The number of upload retries exceeds the limit. Will remain unchanged.");
            }
            try {
                e_().execSQL("UPDATE queue SET retry_count = IFNULL(retry_count, 0) + 1 WHERE rowid IN " + str + " AND (retry_count IS NULL OR retry_count < 2147483647)");
            } catch (SQLiteException e) {
                zzj().zzg().zza("Error incrementing retry count. error", e);
            }
        }
    }

    final void zza(Long l) {
        zzt();
        zzal();
        Preconditions.checkNotNull(l);
        if ((!zzpu.zza() || zze().zza(zzbh.zzcb)) && zzaa()) {
            if (zzb("SELECT COUNT(1) FROM upload_queue WHERE rowid = " + l + " AND retry_count =  2147483647 LIMIT 1", (String[]) null) > 0) {
                zzj().zzu().zza("The number of upload retries exceeds the limit. Will remain unchanged.");
            }
            try {
                e_().execSQL("UPDATE upload_queue SET retry_count = retry_count + 1 WHERE rowid = " + l + " AND retry_count < 2147483647");
            } catch (SQLiteException e) {
                zzj().zzg().zza("Error incrementing retry count. error", e);
            }
        }
    }

    final void zzv() {
        int iDelete;
        zzt();
        zzal();
        if (zzaa()) {
            long jZza = zzn().zza.zza();
            long jElapsedRealtime = zzb().elapsedRealtime();
            if (Math.abs(jElapsedRealtime - jZza) > zzag.zzn()) {
                zzn().zza.zza(jElapsedRealtime);
                zzt();
                zzal();
                if (!zzaa() || (iDelete = e_().delete("queue", "abs(bundle_end_timestamp - ?) > cast(? as integer)", new String[]{String.valueOf(zzb().currentTimeMillis()), String.valueOf(zzag.zzm())})) <= 0) {
                    return;
                }
                zzj().zzp().zza("Deleted stale rows. rowsDeleted", Integer.valueOf(iDelete));
            }
        }
    }

    public final void zzh(String str, String str2) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotEmpty(str2);
        zzt();
        zzal();
        try {
            e_().delete("user_attributes", "app_id=? and name=?", new String[]{str, str2});
        } catch (SQLiteException e) {
            zzj().zzg().zza("Error deleting user property. appId", zzgo.zza(str), zzi().zzc(str2), e);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:67:0x0102  */
    /* JADX WARN: Removed duplicated region for block: B:72:0x010e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void zzr(java.lang.String r21) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 281
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzal.zzr(java.lang.String):void");
    }

    private static void zza(ContentValues contentValues, String str, Object obj) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(obj);
        if (obj instanceof String) {
            contentValues.put(str, (String) obj);
        } else if (obj instanceof Long) {
            contentValues.put(str, (Long) obj);
        } else {
            if (obj instanceof Double) {
                contentValues.put(str, (Double) obj);
                return;
            }
            throw new IllegalArgumentException("Invalid value type");
        }
    }

    final void zza(String str, List<zzfo.zza> list) {
        boolean z;
        boolean z2;
        Preconditions.checkNotNull(list);
        for (int i = 0; i < list.size(); i++) {
            zzfo.zza.C0164zza c0164zzaZzcd = list.get(i).zzcd();
            if (c0164zzaZzcd.zza() != 0) {
                for (int i2 = 0; i2 < c0164zzaZzcd.zza(); i2++) {
                    zzfo.zzb.zza zzaVarZzcd = c0164zzaZzcd.zza(i2).zzcd();
                    zzfo.zzb.zza zzaVar = (zzfo.zzb.zza) ((zzjt.zzb) zzaVarZzcd.clone());
                    String strZzb = zzji.zzb(zzaVarZzcd.zzb());
                    if (strZzb != null) {
                        zzaVar.zza(strZzb);
                        z2 = true;
                    } else {
                        z2 = false;
                    }
                    for (int i3 = 0; i3 < zzaVarZzcd.zza(); i3++) {
                        zzfo.zzc zzcVarZza = zzaVarZzcd.zza(i3);
                        String strZza = zzjk.zza(zzcVarZza.zze());
                        if (strZza != null) {
                            zzaVar.zza(i3, (zzfo.zzc) ((com.google.android.gms.internal.measurement.zzjt) zzcVarZza.zzcd().zza(strZza).zzai()));
                            z2 = true;
                        }
                    }
                    if (z2) {
                        c0164zzaZzcd = c0164zzaZzcd.zza(i2, zzaVar);
                        list.set(i, (zzfo.zza) ((com.google.android.gms.internal.measurement.zzjt) c0164zzaZzcd.zzai()));
                    }
                }
            }
            if (c0164zzaZzcd.zzb() != 0) {
                for (int i4 = 0; i4 < c0164zzaZzcd.zzb(); i4++) {
                    zzfo.zze zzeVarZzb = c0164zzaZzcd.zzb(i4);
                    String strZza2 = zzjj.zza(zzeVarZzb.zze());
                    if (strZza2 != null) {
                        c0164zzaZzcd = c0164zzaZzcd.zza(i4, zzeVarZzb.zzcd().zza(strZza2));
                        list.set(i, (zzfo.zza) ((com.google.android.gms.internal.measurement.zzjt) c0164zzaZzcd.zzai()));
                    }
                }
            }
        }
        zzal();
        zzt();
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(list);
        SQLiteDatabase sQLiteDatabaseE_ = e_();
        sQLiteDatabaseE_.beginTransaction();
        try {
            zzal();
            zzt();
            Preconditions.checkNotEmpty(str);
            SQLiteDatabase sQLiteDatabaseE_2 = e_();
            sQLiteDatabaseE_2.delete("property_filters", "app_id=?", new String[]{str});
            sQLiteDatabaseE_2.delete("event_filters", "app_id=?", new String[]{str});
            for (zzfo.zza zzaVar2 : list) {
                zzal();
                zzt();
                Preconditions.checkNotEmpty(str);
                Preconditions.checkNotNull(zzaVar2);
                if (!zzaVar2.zzg()) {
                    zzj().zzu().zza("Audience with no ID. appId", zzgo.zza(str));
                } else {
                    int iZza = zzaVar2.zza();
                    Iterator<zzfo.zzb> it = zzaVar2.zze().iterator();
                    while (true) {
                        if (it.hasNext()) {
                            if (!it.next().zzl()) {
                                zzj().zzu().zza("Event filter with no ID. Audience definition ignored. appId, audienceId", zzgo.zza(str), Integer.valueOf(iZza));
                                break;
                            }
                        } else {
                            Iterator<zzfo.zze> it2 = zzaVar2.zzf().iterator();
                            while (true) {
                                if (it2.hasNext()) {
                                    if (!it2.next().zzi()) {
                                        zzj().zzu().zza("Property filter with no ID. Audience definition ignored. appId, audienceId", zzgo.zza(str), Integer.valueOf(iZza));
                                        break;
                                    }
                                } else {
                                    Iterator<zzfo.zzb> it3 = zzaVar2.zze().iterator();
                                    while (true) {
                                        if (it3.hasNext()) {
                                            if (!zza(str, iZza, it3.next())) {
                                                z = false;
                                                break;
                                            }
                                        } else {
                                            z = true;
                                            break;
                                        }
                                    }
                                    if (z) {
                                        Iterator<zzfo.zze> it4 = zzaVar2.zzf().iterator();
                                        while (true) {
                                            if (it4.hasNext()) {
                                                if (!zza(str, iZza, it4.next())) {
                                                    z = false;
                                                    break;
                                                }
                                            } else {
                                                break;
                                            }
                                        }
                                    }
                                    if (!z) {
                                        zzal();
                                        zzt();
                                        Preconditions.checkNotEmpty(str);
                                        SQLiteDatabase sQLiteDatabaseE_3 = e_();
                                        sQLiteDatabaseE_3.delete("property_filters", "app_id=? and audience_id=?", new String[]{str, String.valueOf(iZza)});
                                        sQLiteDatabaseE_3.delete("event_filters", "app_id=? and audience_id=?", new String[]{str, String.valueOf(iZza)});
                                    }
                                }
                            }
                        }
                    }
                }
            }
            ArrayList arrayList = new ArrayList();
            for (zzfo.zza zzaVar3 : list) {
                arrayList.add(zzaVar3.zzg() ? Integer.valueOf(zzaVar3.zza()) : null);
            }
            zzb(str, arrayList);
            sQLiteDatabaseE_.setTransactionSuccessful();
        } finally {
            sQLiteDatabaseE_.endTransaction();
        }
    }

    public final void zzw() {
        zzal();
        e_().setTransactionSuccessful();
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0045  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void zza(com.google.android.gms.measurement.internal.zzg r8, boolean r9, boolean r10) {
        /*
            Method dump skipped, instruction units count: 845
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzal.zza(com.google.android.gms.measurement.internal.zzg, boolean, boolean):void");
    }

    public final void zza(String str, zzax zzaxVar) {
        Preconditions.checkNotNull(str);
        Preconditions.checkNotNull(zzaxVar);
        zzt();
        zzal();
        if (zze().zza(zzbh.zzcr) && zzi(str) == zzje.zza) {
            zzb(str, zzje.zza);
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put("app_id", str);
        contentValues.put("dma_consent_settings", zzaxVar.zzf());
        zza("consent_settings", "app_id", contentValues);
    }

    public final void zza(zzbb zzbbVar) {
        zza("events", zzbbVar);
    }

    private final void zza(String str, zzbb zzbbVar) {
        Preconditions.checkNotNull(zzbbVar);
        zzt();
        zzal();
        ContentValues contentValues = new ContentValues();
        contentValues.put("app_id", zzbbVar.zza);
        contentValues.put("name", zzbbVar.zzb);
        contentValues.put("lifetime_count", Long.valueOf(zzbbVar.zzc));
        contentValues.put("current_bundle_count", Long.valueOf(zzbbVar.zzd));
        contentValues.put("last_fire_timestamp", Long.valueOf(zzbbVar.zzf));
        contentValues.put("last_bundled_timestamp", Long.valueOf(zzbbVar.zzg));
        contentValues.put("last_bundled_day", zzbbVar.zzh);
        contentValues.put("last_sampled_complex_event_id", zzbbVar.zzi);
        contentValues.put("last_sampling_rate", zzbbVar.zzj);
        contentValues.put("current_session_count", Long.valueOf(zzbbVar.zze));
        contentValues.put("last_exempt_from_sampling", (zzbbVar.zzk == null || !zzbbVar.zzk.booleanValue()) ? null : 1L);
        try {
            if (e_().insertWithOnConflict(str, null, contentValues, 5) == -1) {
                zzj().zzg().zza("Failed to insert/update event aggregates (got -1). appId", zzgo.zza(zzbbVar.zza));
            }
        } catch (SQLiteException e) {
            zzj().zzg().zza("Error storing event aggregates. appId", zzgo.zza(zzbbVar.zza), e);
        }
    }

    private final void zza(String str, String str2, ContentValues contentValues) {
        try {
            SQLiteDatabase sQLiteDatabaseE_ = e_();
            if (contentValues.getAsString(str2) == null) {
                zzj().zzm().zza("Value of the primary key is not set.", zzgo.zza(str2));
            } else if (sQLiteDatabaseE_.update(str, contentValues, str2 + " = ?", new String[]{r1}) == 0 && sQLiteDatabaseE_.insertWithOnConflict(str, null, contentValues, 5) == -1) {
                zzj().zzg().zza("Failed to insert/update table (got -1). key", zzgo.zza(str), zzgo.zza(str2));
            }
        } catch (SQLiteException e) {
            zzj().zzg().zza("Error storing into table. key", zzgo.zza(str), zzgo.zza(str2), e);
        }
    }

    public final void zza(String str, zzje zzjeVar) {
        Preconditions.checkNotNull(str);
        Preconditions.checkNotNull(zzjeVar);
        zzt();
        zzal();
        zzb(str, zzi(str));
        ContentValues contentValues = new ContentValues();
        contentValues.put("app_id", str);
        contentValues.put("storage_consent_at_bundling", zzjeVar.zzf());
        zza("consent_settings", "app_id", contentValues);
    }

    public final void zzb(String str, zzje zzjeVar) {
        Preconditions.checkNotNull(str);
        Preconditions.checkNotNull(zzjeVar);
        zzt();
        zzal();
        ContentValues contentValues = new ContentValues();
        contentValues.put("app_id", str);
        contentValues.put("consent_state", zzjeVar.zzf());
        contentValues.put("consent_source", Integer.valueOf(zzjeVar.zza()));
        zza("consent_settings", "app_id", contentValues);
    }

    private final boolean zzb(String str, List<Integer> list) {
        Preconditions.checkNotEmpty(str);
        zzal();
        zzt();
        SQLiteDatabase sQLiteDatabaseE_ = e_();
        try {
            long jZzb = zzb("select count(1) from audience_filter_values where app_id=?", new String[]{str});
            int iMax = Math.max(0, Math.min(2000, zze().zzb(str, zzbh.zzah)));
            if (jZzb <= iMax) {
                return false;
            }
            ArrayList arrayList = new ArrayList();
            for (int i = 0; i < list.size(); i++) {
                Integer num = list.get(i);
                if (num == null) {
                    return false;
                }
                arrayList.add(Integer.toString(num.intValue()));
            }
            return sQLiteDatabaseE_.delete("audience_filter_values", new StringBuilder("audience_id in (select audience_id from audience_filter_values where app_id=? and audience_id not in ").append(new StringBuilder("(").append(TextUtils.join(",", arrayList)).append(")").toString()).append(" order by rowid desc limit -1 offset ?)").toString(), new String[]{str, Integer.toString(iMax)}) > 0;
        } catch (SQLiteException e) {
            zzj().zzg().zza("Database error querying filters. appId", zzgo.zza(str), e);
            return false;
        }
    }

    public final boolean zzx() {
        return zzb("select count(1) > 0 from raw_events", (String[]) null) != 0;
    }

    public final boolean zzy() {
        return zzb("select count(1) > 0 from queue where has_realtime = 1", (String[]) null) != 0;
    }

    public final boolean zzz() {
        return zzb("select count(1) > 0 from raw_events where realtime = 1", (String[]) null) != 0;
    }

    public final boolean zzs(String str) {
        return (!zzpu.zza() || zze().zza(zzbh.zzcb)) && zzb(new StringBuilder("SELECT COUNT(1) > 0 FROM upload_queue WHERE app_id=? AND NOT ").append(zzao()).toString(), new String[]{str}) != 0;
    }

    public final boolean zza(zzfy.zzk zzkVar, boolean z) {
        zzt();
        zzal();
        Preconditions.checkNotNull(zzkVar);
        Preconditions.checkNotEmpty(zzkVar.zzz());
        Preconditions.checkState(zzkVar.zzbj());
        zzv();
        long jCurrentTimeMillis = zzb().currentTimeMillis();
        if (zzkVar.zzm() < jCurrentTimeMillis - zzag.zzm() || zzkVar.zzm() > zzag.zzm() + jCurrentTimeMillis) {
            zzj().zzu().zza("Storing bundle outside of the max uploading time span. appId, now, timestamp", zzgo.zza(zzkVar.zzz()), Long.valueOf(jCurrentTimeMillis), Long.valueOf(zzkVar.zzm()));
        }
        try {
            byte[] bArrZzb = g_().zzb(zzkVar.zzca());
            zzj().zzp().zza("Saving bundle, size", Integer.valueOf(bArrZzb.length));
            ContentValues contentValues = new ContentValues();
            contentValues.put("app_id", zzkVar.zzz());
            contentValues.put("bundle_end_timestamp", Long.valueOf(zzkVar.zzm()));
            contentValues.put("data", bArrZzb);
            contentValues.put("has_realtime", Integer.valueOf(z ? 1 : 0));
            if (zzkVar.zzbq()) {
                contentValues.put("retry_count", Integer.valueOf(zzkVar.zzg()));
            }
            try {
                if (e_().insert("queue", null, contentValues) != -1) {
                    return true;
                }
                zzj().zzg().zza("Failed to insert bundle (got -1). appId", zzgo.zza(zzkVar.zzz()));
                return false;
            } catch (SQLiteException e) {
                zzj().zzg().zza("Error storing bundle. appId", zzgo.zza(zzkVar.zzz()), e);
                return false;
            }
        } catch (IOException e2) {
            zzj().zzg().zza("Data loss. Failed to serialize bundle. appId", zzgo.zza(zzkVar.zzz()), e2);
            return false;
        }
    }

    private final boolean zza(String str, int i, zzfo.zzb zzbVar) {
        zzal();
        zzt();
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(zzbVar);
        if (zzbVar.zzf().isEmpty()) {
            zzj().zzu().zza("Event filter had no event name. Audience definition ignored. appId, audienceId, filterId", zzgo.zza(str), Integer.valueOf(i), String.valueOf(zzbVar.zzl() ? Integer.valueOf(zzbVar.zzb()) : null));
            return false;
        }
        byte[] bArrZzca = zzbVar.zzca();
        ContentValues contentValues = new ContentValues();
        contentValues.put("app_id", str);
        contentValues.put("audience_id", Integer.valueOf(i));
        contentValues.put("filter_id", zzbVar.zzl() ? Integer.valueOf(zzbVar.zzb()) : null);
        contentValues.put("event_name", zzbVar.zzf());
        contentValues.put("session_scoped", zzbVar.zzm() ? Boolean.valueOf(zzbVar.zzj()) : null);
        contentValues.put("data", bArrZzca);
        try {
            if (e_().insertWithOnConflict("event_filters", null, contentValues, 5) != -1) {
                return true;
            }
            zzj().zzg().zza("Failed to insert event filter (got -1). appId", zzgo.zza(str));
            return true;
        } catch (SQLiteException e) {
            zzj().zzg().zza("Error storing event filter. appId", zzgo.zza(str), e);
            return false;
        }
    }

    private final boolean zza(String str, int i, zzfo.zze zzeVar) {
        zzal();
        zzt();
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(zzeVar);
        if (zzeVar.zze().isEmpty()) {
            zzj().zzu().zza("Property filter had no property name. Audience definition ignored. appId, audienceId, filterId", zzgo.zza(str), Integer.valueOf(i), String.valueOf(zzeVar.zzi() ? Integer.valueOf(zzeVar.zza()) : null));
            return false;
        }
        byte[] bArrZzca = zzeVar.zzca();
        ContentValues contentValues = new ContentValues();
        contentValues.put("app_id", str);
        contentValues.put("audience_id", Integer.valueOf(i));
        contentValues.put("filter_id", zzeVar.zzi() ? Integer.valueOf(zzeVar.zza()) : null);
        contentValues.put("property_name", zzeVar.zze());
        contentValues.put("session_scoped", zzeVar.zzj() ? Boolean.valueOf(zzeVar.zzh()) : null);
        contentValues.put("data", bArrZzca);
        try {
            if (e_().insertWithOnConflict("property_filters", null, contentValues, 5) != -1) {
                return true;
            }
            zzj().zzg().zza("Failed to insert property filter (got -1). appId", zzgo.zza(str));
            return false;
        } catch (SQLiteException e) {
            zzj().zzg().zza("Error storing property filter. appId", zzgo.zza(str), e);
            return false;
        }
    }

    public final boolean zza(zzbc zzbcVar, long j, boolean z) {
        zzt();
        zzal();
        Preconditions.checkNotNull(zzbcVar);
        Preconditions.checkNotEmpty(zzbcVar.zza);
        byte[] bArrZzca = g_().zza(zzbcVar).zzca();
        ContentValues contentValues = new ContentValues();
        contentValues.put("app_id", zzbcVar.zza);
        contentValues.put("name", zzbcVar.zzb);
        contentValues.put(ServerValues.NAME_OP_TIMESTAMP, Long.valueOf(zzbcVar.zzd));
        contentValues.put("metadata_fingerprint", Long.valueOf(j));
        contentValues.put("data", bArrZzca);
        contentValues.put("realtime", Integer.valueOf(z ? 1 : 0));
        try {
            if (e_().insert("raw_events", null, contentValues) != -1) {
                return true;
            }
            zzj().zzg().zza("Failed to insert raw event (got -1). appId", zzgo.zza(zzbcVar.zza));
            return false;
        } catch (SQLiteException e) {
            zzj().zzg().zza("Error storing raw event. appId", zzgo.zza(zzbcVar.zza), e);
            return false;
        }
    }

    public final boolean zza(String str, zzno zznoVar) {
        zzt();
        zzal();
        Preconditions.checkNotNull(zznoVar);
        Preconditions.checkNotEmpty(str);
        long jCurrentTimeMillis = zzb().currentTimeMillis();
        if (zznoVar.zzb < jCurrentTimeMillis - zzbh.zzbh.zza(null).longValue() || zznoVar.zzb > zzbh.zzbh.zza(null).longValue() + jCurrentTimeMillis) {
            zzj().zzu().zza("Storing trigger URI outside of the max retention time span. appId, now, timestamp", zzgo.zza(str), Long.valueOf(jCurrentTimeMillis), Long.valueOf(zznoVar.zzb));
        }
        zzj().zzp().zza("Saving trigger URI");
        ContentValues contentValues = new ContentValues();
        contentValues.put("app_id", str);
        contentValues.put("trigger_uri", zznoVar.zza);
        contentValues.put(FirebaseAnalytics.Param.SOURCE, Integer.valueOf(zznoVar.zzc));
        contentValues.put("timestamp_millis", Long.valueOf(zznoVar.zzb));
        try {
            if (e_().insert("trigger_uris", null, contentValues) != -1) {
                return true;
            }
            zzj().zzg().zza("Failed to insert trigger URI (got -1). appId", zzgo.zza(str));
            return false;
        } catch (SQLiteException e) {
            zzj().zzg().zza("Error storing trigger URI. appId", zzgo.zza(str), e);
            return false;
        }
    }

    public final boolean zza(String str, zzfy.zzj zzjVar, String str2, Map<String, String> map, zznt zzntVar) {
        int iDelete;
        zzt();
        zzal();
        Preconditions.checkNotNull(zzjVar);
        Preconditions.checkNotEmpty(str);
        if (zzpu.zza() && !zze().zza(zzbh.zzcb)) {
            return false;
        }
        zzt();
        zzal();
        if (zzaa()) {
            long jZza = zzn().zzb.zza();
            long jElapsedRealtime = zzb().elapsedRealtime();
            if (Math.abs(jElapsedRealtime - jZza) > zzag.zzn()) {
                zzn().zzb.zza(jElapsedRealtime);
                zzt();
                zzal();
                if (zzaa() && (iDelete = e_().delete("upload_queue", zzao(), new String[0])) > 0) {
                    zzj().zzp().zza("Deleted stale MeasurementBatch rows from upload_queue. rowsDeleted", Integer.valueOf(iDelete));
                }
            }
        }
        ArrayList arrayList = new ArrayList();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            arrayList.add(entry.getKey() + "=" + entry.getValue());
        }
        byte[] bArrZzca = zzjVar.zzca();
        ContentValues contentValues = new ContentValues();
        contentValues.put("app_id", str);
        contentValues.put("measurement_batch", bArrZzca);
        contentValues.put("upload_uri", str2);
        StringBuilder sb = new StringBuilder();
        int size = arrayList.size();
        if (size > 0) {
            sb.append((CharSequence) arrayList.get(0));
            int i = 1;
            while (i < size) {
                sb.append((CharSequence) "\r\n");
                Object obj = arrayList.get(i);
                i++;
                sb.append((CharSequence) obj);
            }
        }
        contentValues.put("upload_headers", sb.toString());
        contentValues.put("upload_type", Integer.valueOf(zzntVar.zza()));
        contentValues.put(AppMeasurementSdk.ConditionalUserProperty.CREATION_TIMESTAMP, Long.valueOf(zzb().currentTimeMillis()));
        contentValues.put("retry_count", (Integer) 0);
        try {
            if (e_().insert("upload_queue", null, contentValues) != -1) {
                return true;
            }
            zzj().zzg().zza("Failed to insert MeasurementBatch (got -1) to upload_queue. appId", str);
            return false;
        } catch (SQLiteException e) {
            zzj().zzg().zza("Error storing MeasurementBatch to upload_queue. appId", str, e);
            return false;
        }
    }

    protected final boolean zzaa() {
        return zza().getDatabasePath("google_app_measurement.db").exists();
    }

    public final boolean zza(String str, Long l, long j, zzfy.zzf zzfVar) {
        zzt();
        zzal();
        Preconditions.checkNotNull(zzfVar);
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(l);
        byte[] bArrZzca = zzfVar.zzca();
        zzj().zzp().zza("Saving complex main event, appId, data size", zzi().zza(str), Integer.valueOf(bArrZzca.length));
        ContentValues contentValues = new ContentValues();
        contentValues.put("app_id", str);
        contentValues.put("event_id", l);
        contentValues.put("children_to_process", Long.valueOf(j));
        contentValues.put("main_event", bArrZzca);
        try {
            if (e_().insertWithOnConflict("main_event_params", null, contentValues, 5) != -1) {
                return true;
            }
            zzj().zzg().zza("Failed to insert complex main event (got -1). appId", zzgo.zza(str));
            return false;
        } catch (SQLiteException e) {
            zzj().zzg().zza("Error storing complex main event. appId", zzgo.zza(str), e);
            return false;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:28:0x0061  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    final boolean zzt(java.lang.String r10) {
        /*
            r9 = this;
            r0 = 1
            r1 = 0
            r2 = 0
            android.database.sqlite.SQLiteDatabase r3 = r9.e_()     // Catch: java.lang.Throwable -> L4e android.database.sqlite.SQLiteException -> L50
            java.lang.String r4 = "select timestamp from raw_events where app_id=? and name = '_f' limit 1;"
            java.lang.String[] r5 = new java.lang.String[r0]     // Catch: java.lang.Throwable -> L4e android.database.sqlite.SQLiteException -> L50
            r5[r1] = r10     // Catch: java.lang.Throwable -> L4e android.database.sqlite.SQLiteException -> L50
            android.database.Cursor r2 = r3.rawQuery(r4, r5)     // Catch: java.lang.Throwable -> L4e android.database.sqlite.SQLiteException -> L50
            boolean r3 = r2.moveToFirst()     // Catch: java.lang.Throwable -> L4e android.database.sqlite.SQLiteException -> L50
            if (r3 != 0) goto L1d
            if (r2 == 0) goto L1c
            r2.close()
        L1c:
            return r1
        L1d:
            long r3 = r2.getLong(r1)     // Catch: java.lang.Throwable -> L4e android.database.sqlite.SQLiteException -> L50
            com.google.android.gms.common.util.Clock r5 = r9.zzb()     // Catch: java.lang.Throwable -> L4e android.database.sqlite.SQLiteException -> L50
            long r5 = r5.currentTimeMillis()     // Catch: java.lang.Throwable -> L4e android.database.sqlite.SQLiteException -> L50
            r7 = 15000(0x3a98, double:7.411E-320)
            long r3 = r3 + r7
            int r3 = (r5 > r3 ? 1 : (r5 == r3 ? 0 : -1))
            if (r3 >= 0) goto L32
            r3 = r0
            goto L33
        L32:
            r3 = r1
        L33:
            java.lang.String r4 = "select count(*) from raw_events where app_id=? and name not like '!_%' escape '!' limit 1;"
            java.lang.String[] r5 = new java.lang.String[r0]     // Catch: android.database.sqlite.SQLiteException -> L4c java.lang.Throwable -> L4e
            r5[r1] = r10     // Catch: android.database.sqlite.SQLiteException -> L4c java.lang.Throwable -> L4e
            r6 = 0
            long r4 = r9.zza(r4, r5, r6)     // Catch: android.database.sqlite.SQLiteException -> L4c java.lang.Throwable -> L4e
            int r10 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r10 <= 0) goto L45
            r10 = r0
            goto L46
        L45:
            r10 = r1
        L46:
            if (r2 == 0) goto L65
            r2.close()
            goto L65
        L4c:
            r10 = move-exception
            goto L52
        L4e:
            r10 = move-exception
            goto L6b
        L50:
            r10 = move-exception
            r3 = r1
        L52:
            com.google.android.gms.measurement.internal.zzgo r4 = r9.zzj()     // Catch: java.lang.Throwable -> L4e
            com.google.android.gms.measurement.internal.zzgq r4 = r4.zzg()     // Catch: java.lang.Throwable -> L4e
            java.lang.String r5 = "Error checking backfill conditions"
            r4.zza(r5, r10)     // Catch: java.lang.Throwable -> L4e
            if (r2 == 0) goto L64
            r2.close()
        L64:
            r10 = r1
        L65:
            if (r3 == 0) goto L6a
            if (r10 != 0) goto L6a
            return r0
        L6a:
            return r1
        L6b:
            if (r2 == 0) goto L70
            r2.close()
        L70:
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzal.zzt(java.lang.String):boolean");
    }

    public final boolean zza(zzae zzaeVar) {
        Preconditions.checkNotNull(zzaeVar);
        zzt();
        zzal();
        String str = zzaeVar.zza;
        Preconditions.checkNotNull(str);
        if (zze(str, zzaeVar.zzc.zza) == null && zzb("SELECT COUNT(1) FROM conditional_properties WHERE app_id=?", new String[]{str}) >= 1000) {
            return false;
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put("app_id", str);
        contentValues.put("origin", zzaeVar.zzb);
        contentValues.put("name", zzaeVar.zzc.zza);
        zza(contentValues, "value", Preconditions.checkNotNull(zzaeVar.zzc.zza()));
        contentValues.put(AppMeasurementSdk.ConditionalUserProperty.ACTIVE, Boolean.valueOf(zzaeVar.zze));
        contentValues.put(AppMeasurementSdk.ConditionalUserProperty.TRIGGER_EVENT_NAME, zzaeVar.zzf);
        contentValues.put(AppMeasurementSdk.ConditionalUserProperty.TRIGGER_TIMEOUT, Long.valueOf(zzaeVar.zzh));
        zzq();
        contentValues.put("timed_out_event", zzos.zza((Parcelable) zzaeVar.zzg));
        contentValues.put(AppMeasurementSdk.ConditionalUserProperty.CREATION_TIMESTAMP, Long.valueOf(zzaeVar.zzd));
        zzq();
        contentValues.put("triggered_event", zzos.zza((Parcelable) zzaeVar.zzi));
        contentValues.put(AppMeasurementSdk.ConditionalUserProperty.TRIGGERED_TIMESTAMP, Long.valueOf(zzaeVar.zzc.zzb));
        contentValues.put(AppMeasurementSdk.ConditionalUserProperty.TIME_TO_LIVE, Long.valueOf(zzaeVar.zzj));
        zzq();
        contentValues.put("expired_event", zzos.zza((Parcelable) zzaeVar.zzk));
        try {
            if (e_().insertWithOnConflict("conditional_properties", null, contentValues, 5) == -1) {
                zzj().zzg().zza("Failed to insert/update conditional user property (got -1)", zzgo.zza(str));
            }
        } catch (SQLiteException e) {
            zzj().zzg().zza("Error storing conditional user property", zzgo.zza(str), e);
        }
        return true;
    }

    final boolean zzb(String str, Bundle bundle) {
        zzt();
        zzal();
        byte[] bArrZzca = g_().zza(new zzbc(this.zzu, "", str, "dep", 0L, 0L, bundle)).zzca();
        zzj().zzp().zza("Saving default event parameters, appId, data size", zzi().zza(str), Integer.valueOf(bArrZzca.length));
        ContentValues contentValues = new ContentValues();
        contentValues.put("app_id", str);
        contentValues.put("parameters", bArrZzca);
        try {
            if (e_().insertWithOnConflict("default_event_params", null, contentValues, 5) != -1) {
                return true;
            }
            zzj().zzg().zza("Failed to insert default event parameters (got -1). appId", zzgo.zza(str));
            return false;
        } catch (SQLiteException e) {
            zzj().zzg().zza("Error storing default event parameters. appId", zzgo.zza(str), e);
            return false;
        }
    }

    private final boolean zza(long j, zzbc zzbcVar, long j2, boolean z) {
        zzt();
        zzal();
        Preconditions.checkNotNull(zzbcVar);
        Preconditions.checkNotEmpty(zzbcVar.zza);
        byte[] bArrZzca = g_().zza(zzbcVar).zzca();
        ContentValues contentValues = new ContentValues();
        contentValues.put("app_id", zzbcVar.zza);
        contentValues.put("name", zzbcVar.zzb);
        contentValues.put(ServerValues.NAME_OP_TIMESTAMP, Long.valueOf(zzbcVar.zzd));
        contentValues.put("metadata_fingerprint", Long.valueOf(j2));
        contentValues.put("data", bArrZzca);
        contentValues.put("realtime", Integer.valueOf(z ? 1 : 0));
        try {
            long jUpdate = e_().update("raw_events", contentValues, "rowid = ?", new String[]{String.valueOf(j)});
            if (jUpdate == 1) {
                return true;
            }
            zzj().zzg().zza("Failed to update raw event. appId, updatedRows", zzgo.zza(zzbcVar.zza), Long.valueOf(jUpdate));
            return false;
        } catch (SQLiteException e) {
            zzj().zzg().zza("Error updating raw event. appId", zzgo.zza(zzbcVar.zza), e);
            return false;
        }
    }

    public final boolean zza(zzop zzopVar) {
        Preconditions.checkNotNull(zzopVar);
        zzt();
        zzal();
        if (zze(zzopVar.zza, zzopVar.zzc) == null) {
            if (zzos.zzh(zzopVar.zzc)) {
                if (zzb("select count(1) from user_attributes where app_id=? and name not like '!_%' escape '!'", new String[]{zzopVar.zza}) >= zze().zza(zzopVar.zza, zzbh.zzai, 25, 100)) {
                    return false;
                }
            } else if (!"_npa".equals(zzopVar.zzc) && zzb("select count(1) from user_attributes where app_id=? and origin=? AND name like '!_%' escape '!'", new String[]{zzopVar.zza, zzopVar.zzb}) >= 25) {
                return false;
            }
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put("app_id", zzopVar.zza);
        contentValues.put("origin", zzopVar.zzb);
        contentValues.put("name", zzopVar.zzc);
        contentValues.put("set_timestamp", Long.valueOf(zzopVar.zzd));
        zza(contentValues, "value", zzopVar.zze);
        try {
            if (e_().insertWithOnConflict("user_attributes", null, contentValues, 5) == -1) {
                zzj().zzg().zza("Failed to insert/update user property (got -1). appId", zzgo.zza(zzopVar.zza));
            }
        } catch (SQLiteException e) {
            zzj().zzg().zza("Error storing user property. appId", zzgo.zza(zzopVar.zza), e);
        }
        return true;
    }
}
