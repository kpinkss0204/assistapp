package io.grpc.internal;

/* JADX INFO: loaded from: classes4.dex */
public interface RetryScheduler {
    void reset();

    void schedule(Runnable runnable);
}
