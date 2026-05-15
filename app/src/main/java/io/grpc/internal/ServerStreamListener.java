package io.grpc.internal;

import io.grpc.Status;

/* JADX INFO: loaded from: classes4.dex */
public interface ServerStreamListener extends StreamListener {
    void closed(Status status);

    void halfClosed();
}
