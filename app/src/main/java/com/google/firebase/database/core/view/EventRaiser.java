package com.google.firebase.database.core.view;

import com.google.firebase.database.core.Context;
import com.google.firebase.database.core.EventTarget;
import com.google.firebase.database.logging.LogWrapper;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class EventRaiser {
    private final EventTarget eventTarget;
    private final LogWrapper logger;

    public EventRaiser(Context context) {
        this.eventTarget = context.getEventTarget();
        this.logger = context.getLogger("EventRaiser");
    }

    public void raiseEvents(List<? extends Event> list) {
        if (this.logger.logsDebug()) {
            this.logger.debug("Raising " + list.size() + " event(s)", new Object[0]);
        }
        final ArrayList arrayList = new ArrayList(list);
        this.eventTarget.postEvent(new Runnable() { // from class: com.google.firebase.database.core.view.EventRaiser.1
            @Override // java.lang.Runnable
            public void run() {
                for (Event event : arrayList) {
                    if (EventRaiser.this.logger.logsDebug()) {
                        EventRaiser.this.logger.debug("Raising " + event.toString(), new Object[0]);
                    }
                    event.fire();
                }
            }
        });
    }
}
