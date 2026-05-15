package io.grpc.okhttp;

import com.google.common.base.Preconditions;
import io.grpc.internal.GrpcUtil;
import io.grpc.okhttp.internal.OptionalMethod;
import io.grpc.okhttp.internal.Platform;
import io.grpc.okhttp.internal.Protocol;
import io.grpc.okhttp.internal.Util;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Nullable;
import javax.net.ssl.SSLParameters;
import javax.net.ssl.SSLSocket;

/* JADX INFO: loaded from: classes4.dex */
class OkHttpProtocolNegotiator {
    protected final Platform platform;
    private static final Logger logger = Logger.getLogger(OkHttpProtocolNegotiator.class.getName());
    private static final Platform DEFAULT_PLATFORM = Platform.get();
    private static OkHttpProtocolNegotiator NEGOTIATOR = createNegotiator(OkHttpProtocolNegotiator.class.getClassLoader());

    OkHttpProtocolNegotiator(Platform platform) {
        this.platform = (Platform) Preconditions.checkNotNull(platform, "platform");
    }

    public static OkHttpProtocolNegotiator get() {
        return NEGOTIATOR;
    }

    static OkHttpProtocolNegotiator createNegotiator(ClassLoader classLoader) {
        try {
            classLoader.loadClass("com.android.org.conscrypt.OpenSSLSocketImpl");
        } catch (ClassNotFoundException e) {
            logger.log(Level.FINE, "Unable to find Conscrypt. Skipping", (Throwable) e);
            try {
                classLoader.loadClass("org.apache.harmony.xnet.provider.jsse.OpenSSLSocketImpl");
            } catch (ClassNotFoundException e2) {
                logger.log(Level.FINE, "Unable to find any OpenSSLSocketImpl. Skipping", (Throwable) e2);
                return new OkHttpProtocolNegotiator(DEFAULT_PLATFORM);
            }
        }
        return new AndroidNegotiator(DEFAULT_PLATFORM);
    }

    public String negotiate(SSLSocket sSLSocket, String str, @Nullable List<Protocol> list) throws IOException {
        if (list != null) {
            configureTlsExtensions(sSLSocket, str, list);
        }
        try {
            sSLSocket.startHandshake();
            String selectedProtocol = getSelectedProtocol(sSLSocket);
            if (selectedProtocol != null) {
                return selectedProtocol;
            }
            throw new RuntimeException("TLS ALPN negotiation failed with protocols: " + list);
        } finally {
            this.platform.afterHandshake(sSLSocket);
        }
    }

    protected void configureTlsExtensions(SSLSocket sSLSocket, String str, List<Protocol> list) {
        this.platform.configureTlsExtensions(sSLSocket, str, list);
    }

    public String getSelectedProtocol(SSLSocket sSLSocket) {
        return this.platform.getSelectedProtocol(sSLSocket);
    }

    static final class AndroidNegotiator extends OkHttpProtocolNegotiator {
        private static final Method GET_APPLICATION_PROTOCOL;
        private static final Method GET_APPLICATION_PROTOCOLS;
        private static final Method SET_APPLICATION_PROTOCOLS;
        private static final Method SET_SERVER_NAMES;
        private static final Constructor<?> SNI_HOST_NAME;
        private static final Method SSL_SOCKETS_IS_SUPPORTED_SOCKET;
        private static final Method SSL_SOCKETS_SET_USE_SESSION_TICKET;
        private static final OptionalMethod<Socket> SET_USE_SESSION_TICKETS = new OptionalMethod<>(null, "setUseSessionTickets", Boolean.TYPE);
        private static final OptionalMethod<Socket> SET_HOSTNAME = new OptionalMethod<>(null, "setHostname", String.class);
        private static final OptionalMethod<Socket> GET_ALPN_SELECTED_PROTOCOL = new OptionalMethod<>(byte[].class, "getAlpnSelectedProtocol", new Class[0]);
        private static final OptionalMethod<Socket> SET_ALPN_PROTOCOLS = new OptionalMethod<>(null, "setAlpnProtocols", byte[].class);
        private static final OptionalMethod<Socket> GET_NPN_SELECTED_PROTOCOL = new OptionalMethod<>(byte[].class, "getNpnSelectedProtocol", new Class[0]);
        private static final OptionalMethod<Socket> SET_NPN_PROTOCOLS = new OptionalMethod<>(null, "setNpnProtocols", byte[].class);

        /* JADX WARN: Can't wrap try/catch for region: R(16:0|2|61|3|63|4|(5:53|5|59|6|7)|(2:51|8)|35|57|36|55|37|49|50|(1:(0))) */
        /* JADX WARN: Code restructure failed: missing block: B:39:0x00f8, code lost:
        
            r2 = e;
         */
        /* JADX WARN: Code restructure failed: missing block: B:41:0x00fa, code lost:
        
            r2 = e;
         */
        /* JADX WARN: Code restructure failed: missing block: B:43:0x00fc, code lost:
        
            r2 = e;
         */
        /* JADX WARN: Code restructure failed: missing block: B:44:0x00fd, code lost:
        
            r1 = null;
         */
        /* JADX WARN: Code restructure failed: missing block: B:45:0x00fe, code lost:
        
            io.grpc.okhttp.OkHttpProtocolNegotiator.logger.log(java.util.logging.Level.FINER, "Failed to find Android 7.0+ APIs", (java.lang.Throwable) r2);
         */
        /* JADX WARN: Code restructure failed: missing block: B:46:0x0108, code lost:
        
            r2 = e;
         */
        /* JADX WARN: Code restructure failed: missing block: B:47:0x0109, code lost:
        
            r1 = null;
         */
        /* JADX WARN: Code restructure failed: missing block: B:48:0x010a, code lost:
        
            io.grpc.okhttp.OkHttpProtocolNegotiator.logger.log(java.util.logging.Level.FINER, "Failed to find Android 7.0+ APIs", (java.lang.Throwable) r2);
         */
        static {
            /*
                Method dump skipped, instruction units count: 280
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: io.grpc.okhttp.OkHttpProtocolNegotiator.AndroidNegotiator.<clinit>():void");
        }

        AndroidNegotiator(Platform platform) {
            super(platform);
        }

        @Override // io.grpc.okhttp.OkHttpProtocolNegotiator
        public String negotiate(SSLSocket sSLSocket, String str, List<Protocol> list) throws IOException {
            String selectedProtocol = getSelectedProtocol(sSLSocket);
            return selectedProtocol == null ? super.negotiate(sSLSocket, str, list) : selectedProtocol;
        }

        @Override // io.grpc.okhttp.OkHttpProtocolNegotiator
        protected void configureTlsExtensions(SSLSocket sSLSocket, String str, List<Protocol> list) {
            Constructor<?> constructor;
            Method method;
            String[] strArrProtocolIds = OkHttpProtocolNegotiator.protocolIds(list);
            SSLParameters sSLParameters = sSLSocket.getSSLParameters();
            boolean z = true;
            if (str != null) {
                try {
                    try {
                        if (isValidHostName(str)) {
                            Method method2 = SSL_SOCKETS_IS_SUPPORTED_SOCKET;
                            if (method2 == null || !((Boolean) method2.invoke(null, sSLSocket)).booleanValue()) {
                                SET_USE_SESSION_TICKETS.invokeOptionalWithoutCheckedException(sSLSocket, true);
                            } else {
                                SSL_SOCKETS_SET_USE_SESSION_TICKET.invoke(null, sSLSocket, true);
                            }
                            Method method3 = SET_SERVER_NAMES;
                            if (method3 != null && (constructor = SNI_HOST_NAME) != null) {
                                method3.invoke(sSLParameters, Collections.singletonList(constructor.newInstance(str)));
                            } else {
                                SET_HOSTNAME.invokeOptionalWithoutCheckedException(sSLSocket, str);
                            }
                        }
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
                    } catch (InstantiationException e2) {
                        throw new RuntimeException(e2);
                    }
                } catch (InvocationTargetException e3) {
                    throw new RuntimeException(e3);
                }
            }
            Method method4 = GET_APPLICATION_PROTOCOL;
            if (method4 != null) {
                try {
                    method4.invoke(sSLSocket, new Object[0]);
                    SET_APPLICATION_PROTOCOLS.invoke(sSLParameters, strArrProtocolIds);
                } catch (InvocationTargetException e4) {
                    if (e4.getTargetException() instanceof UnsupportedOperationException) {
                        OkHttpProtocolNegotiator.logger.log(Level.FINER, "setApplicationProtocol unsupported, will try old methods");
                        z = false;
                    } else {
                        throw e4;
                    }
                }
            } else {
                z = false;
            }
            sSLSocket.setSSLParameters(sSLParameters);
            if (z && (method = GET_APPLICATION_PROTOCOLS) != null && Arrays.equals(strArrProtocolIds, (String[]) method.invoke(sSLSocket.getSSLParameters(), new Object[0]))) {
                return;
            }
            Object[] objArr = {Platform.concatLengthPrefixed(list)};
            if (this.platform.getTlsExtensionType() == Platform.TlsExtensionType.ALPN_AND_NPN) {
                SET_ALPN_PROTOCOLS.invokeWithoutCheckedException(sSLSocket, objArr);
            }
            if (this.platform.getTlsExtensionType() != Platform.TlsExtensionType.NONE) {
                SET_NPN_PROTOCOLS.invokeWithoutCheckedException(sSLSocket, objArr);
                return;
            }
            throw new RuntimeException("We can not do TLS handshake on this Android version, please install the Google Play Services Dynamic Security Provider to use TLS");
        }

        @Override // io.grpc.okhttp.OkHttpProtocolNegotiator
        public String getSelectedProtocol(SSLSocket sSLSocket) {
            Method method = GET_APPLICATION_PROTOCOL;
            if (method != null) {
                try {
                    return (String) method.invoke(sSLSocket, new Object[0]);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                } catch (InvocationTargetException e2) {
                    if (e2.getTargetException() instanceof UnsupportedOperationException) {
                        OkHttpProtocolNegotiator.logger.log(Level.FINER, "Socket unsupported for getApplicationProtocol, will try old methods");
                    } else {
                        throw new RuntimeException(e2);
                    }
                }
            }
            if (this.platform.getTlsExtensionType() == Platform.TlsExtensionType.ALPN_AND_NPN) {
                try {
                    byte[] bArr = (byte[]) GET_ALPN_SELECTED_PROTOCOL.invokeWithoutCheckedException(sSLSocket, new Object[0]);
                    if (bArr != null) {
                        return new String(bArr, Util.UTF_8);
                    }
                } catch (Exception e3) {
                    OkHttpProtocolNegotiator.logger.log(Level.FINE, "Failed calling getAlpnSelectedProtocol()", (Throwable) e3);
                }
            }
            if (this.platform.getTlsExtensionType() == Platform.TlsExtensionType.NONE) {
                return null;
            }
            try {
                byte[] bArr2 = (byte[]) GET_NPN_SELECTED_PROTOCOL.invokeWithoutCheckedException(sSLSocket, new Object[0]);
                if (bArr2 != null) {
                    return new String(bArr2, Util.UTF_8);
                }
                return null;
            } catch (Exception e4) {
                OkHttpProtocolNegotiator.logger.log(Level.FINE, "Failed calling getNpnSelectedProtocol()", (Throwable) e4);
                return null;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String[] protocolIds(List<Protocol> list) {
        ArrayList arrayList = new ArrayList();
        Iterator<Protocol> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(it.next().toString());
        }
        return (String[]) arrayList.toArray(new String[0]);
    }

    static boolean isValidHostName(String str) {
        if (str.contains("_")) {
            return false;
        }
        try {
            GrpcUtil.checkAuthority(str);
            return true;
        } catch (IllegalArgumentException unused) {
            return false;
        }
    }
}
