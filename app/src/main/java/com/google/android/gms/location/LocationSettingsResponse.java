package com.google.android.gms.location;

import com.google.android.gms.common.api.Response;

/* JADX INFO: compiled from: com.google.android.gms:play-services-location@@21.1.0 */
/* JADX INFO: loaded from: classes3.dex */
public class LocationSettingsResponse extends Response<LocationSettingsResult> {
    public LocationSettingsResponse(LocationSettingsResult locationSettingsResult) {
        super(locationSettingsResult);
    }

    public LocationSettingsStates getLocationSettingsStates() {
        return getResult().getLocationSettingsStates();
    }
}
