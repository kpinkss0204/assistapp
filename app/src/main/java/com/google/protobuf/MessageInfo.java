package com.google.protobuf;

/* JADX INFO: loaded from: classes4.dex */
@CheckReturnValue
interface MessageInfo {
    MessageLite getDefaultInstance();

    ProtoSyntax getSyntax();

    boolean isMessageSetWireFormat();
}
