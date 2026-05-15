package org.simpleframework.xml.transform;

import java.util.TimeZone;

/* JADX INFO: loaded from: classes3.dex */
class TimeZoneTransform implements Transform<TimeZone> {
    TimeZoneTransform() {
    }

    @Override // org.simpleframework.xml.transform.Transform
    public TimeZone read(String str) {
        return TimeZone.getTimeZone(str);
    }

    @Override // org.simpleframework.xml.transform.Transform
    public String write(TimeZone timeZone) {
        return timeZone.getID();
    }
}
