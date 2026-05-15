package ai.onnxruntime;

import ai.onnxruntime.OrtException;
import java.io.IOException;
import java.util.logging.Logger;

/* JADX INFO: loaded from: classes.dex */
public abstract class OrtProviderOptions implements AutoCloseable {
    private static final Logger logger = Logger.getLogger(OrtProviderOptions.class.getName());
    protected boolean closed = false;
    protected final long nativeHandle;

    @FunctionalInterface
    public interface OrtProviderSupplier {
        long create() throws OrtException;
    }

    protected abstract void applyToNative() throws OrtException;

    protected abstract void close(long j, long j2);

    public abstract OrtProvider getProvider();

    static {
        try {
            OnnxRuntime.init();
        } catch (IOException e) {
            throw new RuntimeException("Failed to load onnx-runtime library", e);
        }
    }

    protected OrtProviderOptions(long j) {
        this.nativeHandle = j;
    }

    protected static long getApiHandle() {
        return OnnxRuntime.ortApiHandle;
    }

    public synchronized boolean isClosed() {
        return this.closed;
    }

    @Override // java.lang.AutoCloseable
    public void close() {
        if (!this.closed) {
            close(OnnxRuntime.ortApiHandle, this.nativeHandle);
            this.closed = true;
        } else {
            logger.warning("Closing an already closed tensor.");
        }
    }

    protected void checkClosed() {
        if (this.closed) {
            throw new IllegalStateException("Trying to use a closed OrtProviderOptions");
        }
    }

    /* JADX INFO: renamed from: ai.onnxruntime.OrtProviderOptions$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$ai$onnxruntime$OrtProvider;

        static {
            int[] iArr = new int[OrtProvider.values().length];
            $SwitchMap$ai$onnxruntime$OrtProvider = iArr;
            try {
                iArr[OrtProvider.CUDA.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$ai$onnxruntime$OrtProvider[OrtProvider.DNNL.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$ai$onnxruntime$OrtProvider[OrtProvider.OPEN_VINO.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$ai$onnxruntime$OrtProvider[OrtProvider.ROCM.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$ai$onnxruntime$OrtProvider[OrtProvider.TENSOR_RT.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    protected static long loadLibraryAndCreate(OrtProvider ortProvider, OrtProviderSupplier ortProviderSupplier) throws OrtException {
        int i = AnonymousClass1.$SwitchMap$ai$onnxruntime$OrtProvider[ortProvider.ordinal()];
        if (i != 1) {
            if (i != 2) {
                if (i != 3) {
                    if (i == 4) {
                        if (!OnnxRuntime.extractROCM()) {
                            throw new OrtException(OrtException.OrtErrorCode.ORT_EP_FAIL, "Failed to find ROCm shared provider");
                        }
                    } else if (i == 5 && !OnnxRuntime.extractTensorRT()) {
                        throw new OrtException(OrtException.OrtErrorCode.ORT_EP_FAIL, "Failed to find TensorRT shared provider");
                    }
                } else if (!OnnxRuntime.extractOpenVINO()) {
                    throw new OrtException(OrtException.OrtErrorCode.ORT_EP_FAIL, "Failed to find OpenVINO shared provider");
                }
            } else if (!OnnxRuntime.extractDNNL()) {
                throw new OrtException(OrtException.OrtErrorCode.ORT_EP_FAIL, "Failed to find DNNL shared provider");
            }
        } else if (!OnnxRuntime.extractCUDA()) {
            throw new OrtException(OrtException.OrtErrorCode.ORT_EP_FAIL, "Failed to find CUDA shared provider");
        }
        return ortProviderSupplier.create();
    }
}
