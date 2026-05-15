package io.grpc.internal;

/* JADX INFO: loaded from: classes4.dex */
public interface ServerListener {
    void serverShutdown();

    ServerTransportListener transportCreated(ServerTransport serverTransport);
}
