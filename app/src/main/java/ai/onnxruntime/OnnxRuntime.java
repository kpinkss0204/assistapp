package ai.onnxruntime;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.FileAttribute;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/* JADX INFO: loaded from: classes.dex */
final class OnnxRuntime {
    static final String ONNXRUNTIME_JNI_LIBRARY_NAME = "onnxruntime4j_jni";
    static final String ONNXRUNTIME_LIBRARY_CUDA_NAME = "onnxruntime_providers_cuda";
    static final String ONNXRUNTIME_LIBRARY_DNNL_NAME = "onnxruntime_providers_dnnl";
    static final String ONNXRUNTIME_LIBRARY_NAME = "onnxruntime";
    static final String ONNXRUNTIME_LIBRARY_OPENVINO_NAME = "onnxruntime_providers_openvino";
    static final String ONNXRUNTIME_LIBRARY_ROCM_NAME = "onnxruntime_providers_rocm";
    static final String ONNXRUNTIME_LIBRARY_SHARED_NAME = "onnxruntime_providers_shared";
    static final String ONNXRUNTIME_LIBRARY_TENSORRT_NAME = "onnxruntime_providers_tensorrt";
    static final String ONNXRUNTIME_NATIVE_PATH = "onnxruntime.native.path";
    private static final int ORT_API_VERSION_1 = 1;
    private static final int ORT_API_VERSION_11 = 11;
    private static final int ORT_API_VERSION_13 = 13;
    private static final int ORT_API_VERSION_14 = 14;
    private static final int ORT_API_VERSION_2 = 2;
    private static final int ORT_API_VERSION_3 = 3;
    private static final int ORT_API_VERSION_7 = 7;
    private static final int ORT_API_VERSION_8 = 8;
    private static final int ORT_TRAINING_API_VERSION_1 = 1;
    private static String libraryDirPathProperty;
    static long ortApiHandle;
    static long ortTrainingApiHandle;
    static EnumSet<OrtProvider> providers;
    private static Path tempDirectory;
    static boolean trainingEnabled;
    private static String version;
    private static final Logger logger = Logger.getLogger(OnnxRuntime.class.getName());
    private static final String OS_ARCH_STR = initOsArch();
    private static boolean loaded = false;
    private static final Set<String> extractedSharedProviders = new HashSet();

    private static native String[] getAvailableProviders(long j);

    private static native long initialiseAPIBase(int i);

    private static native long initialiseTrainingAPIBase(long j, int i);

    private static native String initialiseVersion();

    private OnnxRuntime() {
    }

    /* JADX WARN: Removed duplicated region for block: B:26:0x007b A[PHI: r1
  0x007b: PHI (r1v14 java.lang.String) = (r1v9 java.lang.String), (r1v10 java.lang.String), (r1v11 java.lang.String) binds: [B:25:0x0079, B:28:0x0083, B:31:0x008c] A[DONT_GENERATE, DONT_INLINE]] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static java.lang.String initOsArch() {
        /*
            java.lang.String r0 = "os.name"
            java.lang.String r1 = "generic"
            java.lang.String r0 = java.lang.System.getProperty(r0, r1)
            java.util.Locale r2 = java.util.Locale.ENGLISH
            java.lang.String r0 = r0.toLowerCase(r2)
            java.lang.String r2 = "mac"
            boolean r2 = r0.contains(r2)
            if (r2 != 0) goto L52
            java.lang.String r2 = "darwin"
            boolean r2 = r0.contains(r2)
            if (r2 == 0) goto L1f
            goto L52
        L1f:
            java.lang.String r2 = "win"
            boolean r3 = r0.contains(r2)
            if (r3 == 0) goto L29
            goto L54
        L29:
            java.lang.String r2 = "nux"
            boolean r2 = r0.contains(r2)
            if (r2 == 0) goto L34
            java.lang.String r2 = "linux"
            goto L54
        L34:
            boolean r2 = isAndroid()
            if (r2 == 0) goto L3d
            java.lang.String r2 = "android"
            goto L54
        L3d:
            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r3 = "Unsupported os:"
            r2.<init>(r3)
            java.lang.StringBuilder r0 = r2.append(r0)
            java.lang.String r0 = r0.toString()
            r1.<init>(r0)
            throw r1
        L52:
            java.lang.String r2 = "osx"
        L54:
            java.lang.String r0 = "os.arch"
            java.lang.String r0 = java.lang.System.getProperty(r0, r1)
            java.util.Locale r1 = java.util.Locale.ENGLISH
            java.lang.String r0 = r0.toLowerCase(r1)
            java.lang.String r1 = "amd64"
            boolean r1 = r0.startsWith(r1)
            if (r1 != 0) goto Lab
            java.lang.String r1 = "x86_64"
            boolean r1 = r0.startsWith(r1)
            if (r1 == 0) goto L72
            goto Lab
        L72:
            java.lang.String r1 = "x86"
            boolean r3 = r0.startsWith(r1)
            if (r3 == 0) goto L7d
        L7b:
            r0 = r1
            goto Lae
        L7d:
            java.lang.String r1 = "aarch64"
            boolean r3 = r0.startsWith(r1)
            if (r3 == 0) goto L86
            goto L7b
        L86:
            java.lang.String r1 = "ppc64"
            boolean r3 = r0.startsWith(r1)
            if (r3 == 0) goto L8f
            goto L7b
        L8f:
            boolean r1 = isAndroid()
            if (r1 == 0) goto L96
            goto Lae
        L96:
            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r3 = "Unsupported arch:"
            r2.<init>(r3)
            java.lang.StringBuilder r0 = r2.append(r0)
            java.lang.String r0 = r0.toString()
            r1.<init>(r0)
            throw r1
        Lab:
            java.lang.String r0 = "x64"
        Lae:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.StringBuilder r1 = r1.append(r2)
            r2 = 45
            java.lang.StringBuilder r1 = r1.append(r2)
            java.lang.StringBuilder r0 = r1.append(r0)
            java.lang.String r0 = r0.toString()
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: ai.onnxruntime.OnnxRuntime.initOsArch():java.lang.String");
    }

    static synchronized void init() throws IOException {
        if (loaded) {
            return;
        }
        tempDirectory = isAndroid() ? null : Files.createTempDirectory("onnxruntime-java", new FileAttribute[0]);
        try {
            libraryDirPathProperty = System.getProperty(ONNXRUNTIME_NATIVE_PATH);
            extractProviderLibrary(ONNXRUNTIME_LIBRARY_SHARED_NAME);
            load(ONNXRUNTIME_LIBRARY_NAME);
            load(ONNXRUNTIME_JNI_LIBRARY_NAME);
            long jInitialiseAPIBase = initialiseAPIBase(14);
            ortApiHandle = jInitialiseAPIBase;
            if (jInitialiseAPIBase == 0) {
                throw new IllegalStateException("There is a mismatch between the ORT class files and the ORT native library, and the native library could not be loaded");
            }
            long jInitialiseTrainingAPIBase = initialiseTrainingAPIBase(jInitialiseAPIBase, 14);
            ortTrainingApiHandle = jInitialiseTrainingAPIBase;
            trainingEnabled = jInitialiseTrainingAPIBase != 0;
            providers = initialiseProviders(ortApiHandle);
            version = initialiseVersion();
            loaded = true;
        } finally {
            Path path = tempDirectory;
            if (path != null) {
                cleanUp(path.toFile());
            }
        }
    }

    private static void cleanUp(File file) {
        if (file.exists()) {
            logger.log(Level.FINE, "Deleting " + file + " on exit");
            file.deleteOnExit();
        }
    }

    static String version() {
        return version;
    }

    static boolean extractCUDA() {
        return extractProviderLibrary(ONNXRUNTIME_LIBRARY_CUDA_NAME);
    }

    static boolean extractROCM() {
        return extractProviderLibrary(ONNXRUNTIME_LIBRARY_ROCM_NAME);
    }

    static boolean extractDNNL() {
        return extractProviderLibrary(ONNXRUNTIME_LIBRARY_DNNL_NAME);
    }

    static boolean extractOpenVINO() {
        return extractProviderLibrary(ONNXRUNTIME_LIBRARY_OPENVINO_NAME);
    }

    static boolean extractTensorRT() {
        return extractProviderLibrary(ONNXRUNTIME_LIBRARY_TENSORRT_NAME);
    }

    static synchronized boolean extractProviderLibrary(String str) {
        if (isAndroid()) {
            return false;
        }
        Set<String> set = extractedSharedProviders;
        if (set.contains(str)) {
            return true;
        }
        if (extractFromResources(str).isPresent()) {
            set.add(str);
            return true;
        }
        if (libraryDirPathProperty == null) {
            return false;
        }
        if (!Paths.get(libraryDirPathProperty, mapLibraryName(str)).toFile().exists()) {
            return false;
        }
        set.add(str);
        return true;
    }

    static boolean isAndroid() {
        return System.getProperty("java.vendor", "generic").equals("The Android Project");
    }

    private static void load(String str) throws IOException {
        if (isAndroid()) {
            System.loadLibrary(ONNXRUNTIME_JNI_LIBRARY_NAME);
            return;
        }
        if (Boolean.TRUE.toString().equalsIgnoreCase(System.getProperty("onnxruntime.native." + str + ".skip"))) {
            logger.log(Level.FINE, "Skipping load of native library '" + str + "'");
            return;
        }
        String strMapLibraryName = mapLibraryName(str);
        if (libraryDirPathProperty != null) {
            Logger logger2 = logger;
            logger2.log(Level.FINE, "Attempting to load native library '" + str + "' from specified path: " + libraryDirPathProperty);
            File file = Paths.get(libraryDirPathProperty, strMapLibraryName).toFile();
            String absolutePath = file.getAbsolutePath();
            if (!file.exists()) {
                throw new IOException("Native library '" + str + "' not found at " + absolutePath);
            }
            System.load(absolutePath);
            logger2.log(Level.FINE, "Loaded native library '" + str + "' from specified path");
            return;
        }
        String property = System.getProperty("onnxruntime.native." + str + ".path");
        if (property != null) {
            Logger logger3 = logger;
            logger3.log(Level.FINE, "Attempting to load native library '" + str + "' from specified path: " + property);
            File file2 = new File(property);
            String absolutePath2 = file2.getAbsolutePath();
            if (!file2.exists()) {
                throw new IOException("Native library '" + str + "' not found at " + absolutePath2);
            }
            System.load(absolutePath2);
            logger3.log(Level.FINE, "Loaded native library '" + str + "' from specified path");
            return;
        }
        Optional<File> optionalExtractFromResources = extractFromResources(str);
        if (optionalExtractFromResources.isPresent()) {
            System.load(optionalExtractFromResources.get().getAbsolutePath());
            logger.log(Level.FINE, "Loaded native library '" + str + "' from resource path");
        } else {
            Logger logger4 = logger;
            logger4.log(Level.FINE, "Attempting to load native library '" + str + "' from library path");
            System.loadLibrary(str);
            logger4.log(Level.FINE, "Loaded native library '" + str + "' from library path");
        }
    }

    private static Optional<File> extractFromResources(String str) {
        String strMapLibraryName = mapLibraryName(str);
        String str2 = "/ai/onnxruntime/native/" + OS_ARCH_STR + '/' + strMapLibraryName;
        File file = tempDirectory.resolve(strMapLibraryName).toFile();
        try {
            InputStream resourceAsStream = OnnxRuntime.class.getResourceAsStream(str2);
            try {
                if (resourceAsStream == null) {
                    Optional<File> optionalEmpty = Optional.empty();
                    if (resourceAsStream != null) {
                        resourceAsStream.close();
                    }
                    return optionalEmpty;
                }
                logger.log(Level.FINE, "Attempting to load native library '" + str + "' from resource path " + str2 + " copying to " + file);
                byte[] bArr = new byte[4096];
                FileOutputStream fileOutputStream = new FileOutputStream(file);
                while (true) {
                    try {
                        int i = resourceAsStream.read(bArr);
                        if (i == -1) {
                            break;
                        }
                        fileOutputStream.write(bArr, 0, i);
                    } finally {
                    }
                }
                fileOutputStream.close();
                logger.log(Level.FINE, "Extracted native library '" + str + "' from resource path");
                Optional<File> optionalOf = Optional.of(file);
                if (resourceAsStream != null) {
                    resourceAsStream.close();
                }
                return optionalOf;
            } catch (Throwable th) {
                if (resourceAsStream != null) {
                    try {
                        resourceAsStream.close();
                    } catch (Throwable th2) {
                        th.addSuppressed(th2);
                    }
                }
                throw th;
            }
        } catch (IOException e) {
            logger.log(Level.WARNING, "Failed to extract library '" + str + "' from the resources", (Throwable) e);
            return Optional.empty();
        } finally {
            cleanUp(file);
        }
    }

    private static String mapLibraryName(String str) {
        return System.mapLibraryName(str).replace("jnilib", "dylib");
    }

    private static EnumSet<OrtProvider> initialiseProviders(long j) {
        String[] availableProviders = getAvailableProviders(j);
        EnumSet<OrtProvider> enumSetNoneOf = EnumSet.noneOf(OrtProvider.class);
        for (String str : availableProviders) {
            enumSetNoneOf.add(OrtProvider.mapFromName(str));
        }
        return enumSetNoneOf;
    }
}
