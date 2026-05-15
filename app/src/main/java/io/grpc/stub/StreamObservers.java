package io.grpc.stub;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.google.common.base.Preconditions;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.Iterator;

/* JADX INFO: loaded from: classes4.dex */
@Deprecated
public final class StreamObservers {
    public static <V> void copyWithFlowControl(final Iterator<V> it, final CallStreamObserver<V> callStreamObserver) {
        Preconditions.checkNotNull(it, FirebaseAnalytics.Param.SOURCE);
        Preconditions.checkNotNull(callStreamObserver, TypedValues.AttributesType.S_TARGET);
        callStreamObserver.setOnReadyHandler(new Runnable() { // from class: io.grpc.stub.StreamObservers.1FlowControllingOnReadyHandler
            private boolean completed;

            @Override // java.lang.Runnable
            public void run() {
                if (this.completed) {
                    return;
                }
                while (callStreamObserver.isReady() && it.hasNext()) {
                    callStreamObserver.onNext(it.next());
                }
                if (it.hasNext()) {
                    return;
                }
                this.completed = true;
                callStreamObserver.onCompleted();
            }
        });
    }

    public static <V> void copyWithFlowControl(Iterable<V> iterable, CallStreamObserver<V> callStreamObserver) {
        Preconditions.checkNotNull(iterable, FirebaseAnalytics.Param.SOURCE);
        copyWithFlowControl(iterable.iterator(), callStreamObserver);
    }
}
