package androidx.camera.extensions.internal.sessionprocessor;

import android.content.Context;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CaptureRequest;
import android.hardware.camera2.CaptureResult;
import android.hardware.camera2.TotalCaptureResult;
import android.util.Pair;
import androidx.camera.camera2.impl.Camera2CameraCaptureResultConverter;
import androidx.camera.camera2.interop.CaptureRequestOptions;
import androidx.camera.core.Logger;
import androidx.camera.core.impl.CameraCaptureFailure;
import androidx.camera.core.impl.CameraCaptureResult;
import androidx.camera.core.impl.Config;
import androidx.camera.core.impl.OutputSurface;
import androidx.camera.core.impl.RequestProcessor;
import androidx.camera.core.impl.SessionProcessor;
import androidx.camera.extensions.impl.CaptureProcessorImpl;
import androidx.camera.extensions.impl.CaptureStageImpl;
import androidx.camera.extensions.impl.ImageCaptureExtenderImpl;
import androidx.camera.extensions.impl.PreviewExtenderImpl;
import androidx.camera.extensions.impl.RequestUpdateProcessorImpl;
import androidx.camera.extensions.internal.ClientVersion;
import androidx.camera.extensions.internal.ExtensionVersion;
import androidx.camera.extensions.internal.Version;
import androidx.camera.extensions.internal.compat.workaround.OnEnableDisableSessionDurationCheck;
import androidx.camera.extensions.internal.sessionprocessor.PreviewProcessor;
import androidx.camera.extensions.internal.sessionprocessor.RequestBuilder;
import androidx.camera.extensions.internal.sessionprocessor.StillCaptureProcessor;
import androidx.core.util.Preconditions;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: loaded from: classes.dex */
public class BasicExtenderSessionProcessor extends SessionProcessorBase {
    private static final int PREVIEW_PROCESS_MAX_IMAGES = 2;
    private static final String TAG = "BasicSessionProcessor";
    static AtomicInteger sLastOutputConfigId = new AtomicInteger(0);
    private volatile Camera2OutputConfig mAnalysisOutputConfig;
    private volatile Camera2OutputConfig mCaptureOutputConfig;
    private volatile OutputSurface mCaptureOutputSurfaceConfig;
    private final Context mContext;
    private final ImageCaptureExtenderImpl mImageCaptureExtenderImpl;
    volatile boolean mIsCapturing;
    final Object mLock;
    private final AtomicInteger mNextCaptureSequenceId;
    private OnEnableDisableSessionDurationCheck mOnEnableDisableSessionDurationCheck;
    private final Map<CaptureRequest.Key<?>, Object> mParameters;
    private final PreviewExtenderImpl mPreviewExtenderImpl;
    private volatile Camera2OutputConfig mPreviewOutputConfig;
    private volatile OutputSurface mPreviewOutputSurfaceConfig;
    volatile PreviewProcessor mPreviewProcessor;
    private volatile RequestProcessor mRequestProcessor;
    volatile RequestUpdateProcessorImpl mRequestUpdateProcessor;
    volatile StillCaptureProcessor mStillCaptureProcessor;
    private final List<CaptureResult.Key> mSupportedResultKeys;

    @Override // androidx.camera.extensions.internal.sessionprocessor.SessionProcessorBase, androidx.camera.core.impl.SessionProcessor
    public /* bridge */ /* synthetic */ Set getSupportedCameraOperations() {
        return super.getSupportedCameraOperations();
    }

    public BasicExtenderSessionProcessor(PreviewExtenderImpl previewExtenderImpl, ImageCaptureExtenderImpl imageCaptureExtenderImpl, List<CaptureRequest.Key> list, List<CaptureResult.Key> list2, Context context) {
        super(list);
        this.mLock = new Object();
        this.mStillCaptureProcessor = null;
        this.mPreviewProcessor = null;
        this.mRequestUpdateProcessor = null;
        this.mAnalysisOutputConfig = null;
        this.mIsCapturing = false;
        this.mNextCaptureSequenceId = new AtomicInteger(0);
        this.mParameters = new LinkedHashMap();
        this.mOnEnableDisableSessionDurationCheck = new OnEnableDisableSessionDurationCheck();
        this.mPreviewExtenderImpl = previewExtenderImpl;
        this.mImageCaptureExtenderImpl = imageCaptureExtenderImpl;
        this.mSupportedResultKeys = list2;
        this.mContext = context;
    }

    @Override // androidx.camera.extensions.internal.sessionprocessor.SessionProcessorBase
    protected Camera2SessionConfig initSessionInternal(String str, Map<String, CameraCharacteristics> map, OutputSurface outputSurface, OutputSurface outputSurface2, OutputSurface outputSurface3) {
        Logger.d(TAG, "PreviewExtenderImpl.onInit");
        this.mPreviewExtenderImpl.onInit(str, map.get(str), this.mContext);
        Logger.d(TAG, "ImageCaptureExtenderImpl.onInit");
        this.mImageCaptureExtenderImpl.onInit(str, map.get(str), this.mContext);
        this.mPreviewOutputSurfaceConfig = outputSurface;
        this.mCaptureOutputSurfaceConfig = outputSurface2;
        PreviewExtenderImpl.ProcessorType processorType = this.mPreviewExtenderImpl.getProcessorType();
        Logger.d(TAG, "preview processorType=" + processorType);
        if (processorType == PreviewExtenderImpl.ProcessorType.PROCESSOR_TYPE_IMAGE_PROCESSOR) {
            this.mPreviewOutputConfig = ImageReaderOutputConfig.create(sLastOutputConfigId.getAndIncrement(), outputSurface.getSize(), 35, 2);
            this.mPreviewProcessor = new PreviewProcessor(this.mPreviewExtenderImpl.getProcessor(), this.mPreviewOutputSurfaceConfig.getSurface(), this.mPreviewOutputSurfaceConfig.getSize());
        } else if (processorType == PreviewExtenderImpl.ProcessorType.PROCESSOR_TYPE_REQUEST_UPDATE_ONLY) {
            this.mPreviewOutputConfig = SurfaceOutputConfig.create(sLastOutputConfigId.getAndIncrement(), outputSurface.getSurface());
            this.mRequestUpdateProcessor = this.mPreviewExtenderImpl.getProcessor();
        } else {
            this.mPreviewOutputConfig = SurfaceOutputConfig.create(sLastOutputConfigId.getAndIncrement(), outputSurface.getSurface());
        }
        CaptureProcessorImpl captureProcessor = this.mImageCaptureExtenderImpl.getCaptureProcessor();
        Logger.d(TAG, "CaptureProcessor=" + captureProcessor);
        if (captureProcessor != null) {
            this.mCaptureOutputConfig = ImageReaderOutputConfig.create(sLastOutputConfigId.getAndIncrement(), outputSurface2.getSize(), 35, this.mImageCaptureExtenderImpl.getMaxCaptureStage());
            this.mStillCaptureProcessor = new StillCaptureProcessor(captureProcessor, this.mCaptureOutputSurfaceConfig.getSurface(), this.mCaptureOutputSurfaceConfig.getSize());
        } else {
            this.mCaptureOutputConfig = SurfaceOutputConfig.create(sLastOutputConfigId.getAndIncrement(), outputSurface2.getSurface());
        }
        if (outputSurface3 != null) {
            this.mAnalysisOutputConfig = SurfaceOutputConfig.create(sLastOutputConfigId.getAndIncrement(), outputSurface3.getSurface());
        }
        Camera2SessionConfigBuilder sessionTemplateId = new Camera2SessionConfigBuilder().addOutputConfig(this.mPreviewOutputConfig).addOutputConfig(this.mCaptureOutputConfig).setSessionTemplateId(1);
        if (this.mAnalysisOutputConfig != null) {
            sessionTemplateId.addOutputConfig(this.mAnalysisOutputConfig);
        }
        CaptureStageImpl captureStageImplOnPresetSession = this.mPreviewExtenderImpl.onPresetSession();
        Logger.d(TAG, "preview onPresetSession:" + captureStageImplOnPresetSession);
        CaptureStageImpl captureStageImplOnPresetSession2 = this.mImageCaptureExtenderImpl.onPresetSession();
        Logger.d(TAG, "capture onPresetSession:" + captureStageImplOnPresetSession2);
        if (captureStageImplOnPresetSession != null && captureStageImplOnPresetSession.getParameters() != null) {
            for (Pair pair : captureStageImplOnPresetSession.getParameters()) {
                sessionTemplateId.addSessionParameter((CaptureRequest.Key) pair.first, pair.second);
            }
        }
        if (captureStageImplOnPresetSession2 != null && captureStageImplOnPresetSession2.getParameters() != null) {
            for (Pair pair2 : captureStageImplOnPresetSession2.getParameters()) {
                sessionTemplateId.addSessionParameter((CaptureRequest.Key) pair2.first, pair2.second);
            }
        }
        return sessionTemplateId.build();
    }

    @Override // androidx.camera.extensions.internal.sessionprocessor.SessionProcessorBase
    protected void deInitSessionInternal() {
        Logger.d(TAG, "preview onDeInit");
        this.mPreviewExtenderImpl.onDeInit();
        Logger.d(TAG, "capture onDeInit");
        this.mImageCaptureExtenderImpl.onDeInit();
        if (this.mPreviewProcessor != null) {
            this.mPreviewProcessor.close();
            this.mPreviewProcessor = null;
        }
        if (this.mStillCaptureProcessor != null) {
            this.mStillCaptureProcessor.close();
            this.mStillCaptureProcessor = null;
        }
    }

    @Override // androidx.camera.core.impl.SessionProcessor
    public void setParameters(Config config) {
        synchronized (this.mLock) {
            HashMap map = new HashMap();
            CaptureRequestOptions captureRequestOptionsBuild = CaptureRequestOptions.Builder.from(config).build();
            for (Config.Option<?> option : captureRequestOptionsBuild.listOptions()) {
                map.put((CaptureRequest.Key) option.getToken(), captureRequestOptionsBuild.retrieveOption(option));
            }
            this.mParameters.clear();
            this.mParameters.putAll(map);
            applyRotationAndJpegQualityToProcessor();
        }
    }

    @Override // androidx.camera.core.impl.SessionProcessor
    public void onCaptureSessionStart(RequestProcessor requestProcessor) {
        this.mRequestProcessor = requestProcessor;
        ArrayList arrayList = new ArrayList();
        CaptureStageImpl captureStageImplOnEnableSession = this.mPreviewExtenderImpl.onEnableSession();
        Logger.d(TAG, "preview onEnableSession: " + captureStageImplOnEnableSession);
        if (captureStageImplOnEnableSession != null) {
            arrayList.add(captureStageImplOnEnableSession);
        }
        CaptureStageImpl captureStageImplOnEnableSession2 = this.mImageCaptureExtenderImpl.onEnableSession();
        Logger.d(TAG, "capture onEnableSession:" + captureStageImplOnEnableSession2);
        if (captureStageImplOnEnableSession2 != null) {
            arrayList.add(captureStageImplOnEnableSession2);
        }
        this.mOnEnableDisableSessionDurationCheck.onEnableSessionInvoked();
        if (!arrayList.isEmpty()) {
            submitRequestByCaptureStages(requestProcessor, arrayList);
        }
        if (this.mPreviewProcessor != null) {
            setImageProcessor(this.mPreviewOutputConfig.getId(), new ImageProcessor() { // from class: androidx.camera.extensions.internal.sessionprocessor.BasicExtenderSessionProcessor.1
                @Override // androidx.camera.extensions.internal.sessionprocessor.ImageProcessor
                public void onNextImageAvailable(int i, long j, ImageReference imageReference, String str) {
                    if (BasicExtenderSessionProcessor.this.mPreviewProcessor != null) {
                        BasicExtenderSessionProcessor.this.mPreviewProcessor.notifyImage(imageReference);
                    }
                }
            });
        }
    }

    private void applyParameters(RequestBuilder requestBuilder) {
        synchronized (this.mLock) {
            for (CaptureRequest.Key<?> key : this.mParameters.keySet()) {
                Object obj = this.mParameters.get(key);
                if (obj != null) {
                    requestBuilder.setParameters(key, obj);
                }
            }
        }
    }

    private void applyRotationAndJpegQualityToProcessor() {
        synchronized (this.mLock) {
            if (this.mStillCaptureProcessor == null) {
                return;
            }
            Integer num = (Integer) this.mParameters.get(CaptureRequest.JPEG_ORIENTATION);
            if (num != null) {
                this.mStillCaptureProcessor.setRotationDegrees(num.intValue());
            }
            Byte b = (Byte) this.mParameters.get(CaptureRequest.JPEG_QUALITY);
            if (b != null) {
                this.mStillCaptureProcessor.setJpegQuality(b.byteValue());
            }
        }
    }

    private void submitRequestByCaptureStages(RequestProcessor requestProcessor, List<CaptureStageImpl> list) {
        ArrayList arrayList = new ArrayList();
        for (CaptureStageImpl captureStageImpl : list) {
            RequestBuilder requestBuilder = new RequestBuilder();
            requestBuilder.addTargetOutputConfigIds(this.mPreviewOutputConfig.getId());
            if (this.mAnalysisOutputConfig != null) {
                requestBuilder.addTargetOutputConfigIds(this.mAnalysisOutputConfig.getId());
            }
            for (Pair pair : captureStageImpl.getParameters()) {
                requestBuilder.setParameters((CaptureRequest.Key) pair.first, pair.second);
            }
            requestBuilder.setTemplateId(1);
            arrayList.add(requestBuilder.build());
        }
        requestProcessor.submit(arrayList, new RequestProcessor.Callback() { // from class: androidx.camera.extensions.internal.sessionprocessor.BasicExtenderSessionProcessor.2
        });
    }

    @Override // androidx.camera.core.impl.SessionProcessor
    public void onCaptureSessionEnd() {
        this.mOnEnableDisableSessionDurationCheck.onDisableSessionInvoked();
        ArrayList arrayList = new ArrayList();
        CaptureStageImpl captureStageImplOnDisableSession = this.mPreviewExtenderImpl.onDisableSession();
        Logger.d(TAG, "preview onDisableSession: " + captureStageImplOnDisableSession);
        if (captureStageImplOnDisableSession != null) {
            arrayList.add(captureStageImplOnDisableSession);
        }
        CaptureStageImpl captureStageImplOnDisableSession2 = this.mImageCaptureExtenderImpl.onDisableSession();
        Logger.d(TAG, "capture onDisableSession:" + captureStageImplOnDisableSession2);
        if (captureStageImplOnDisableSession2 != null) {
            arrayList.add(captureStageImplOnDisableSession2);
        }
        if (!arrayList.isEmpty()) {
            submitRequestByCaptureStages(this.mRequestProcessor, arrayList);
        }
        this.mRequestProcessor = null;
        this.mIsCapturing = false;
    }

    Map<CaptureResult.Key, Object> getCaptureResultKeyMapFromList(List<Pair<CaptureResult.Key, Object>> list) {
        HashMap map = new HashMap();
        for (Pair<CaptureResult.Key, Object> pair : list) {
            map.put((CaptureResult.Key) pair.first, pair.second);
        }
        return map;
    }

    Map<CaptureResult.Key, Object> getCaptureResultKeyMaps(TotalCaptureResult totalCaptureResult) {
        HashMap map = new HashMap();
        for (CaptureResult.Key<?> key : totalCaptureResult.getKeys()) {
            if (this.mSupportedResultKeys.contains(key)) {
                map.put(key, totalCaptureResult.get(key));
            }
        }
        return map;
    }

    @Override // androidx.camera.core.impl.SessionProcessor
    public int startRepeating(final SessionProcessor.CaptureCallback captureCallback) {
        final int andIncrement = this.mNextCaptureSequenceId.getAndIncrement();
        if (this.mRequestProcessor == null) {
            captureCallback.onCaptureFailed(andIncrement);
            captureCallback.onCaptureSequenceAborted(andIncrement);
            return andIncrement;
        }
        if (this.mPreviewProcessor != null) {
            this.mPreviewProcessor.start(new PreviewProcessor.OnCaptureResultCallback() { // from class: androidx.camera.extensions.internal.sessionprocessor.BasicExtenderSessionProcessor$$ExternalSyntheticLambda0
                @Override // androidx.camera.extensions.internal.sessionprocessor.PreviewProcessor.OnCaptureResultCallback
                public final void onCaptureResult(long j, List list) {
                    this.f$0.m202xc693c9b1(captureCallback, andIncrement, j, list);
                }
            });
        }
        updateRepeating(andIncrement, captureCallback);
        return andIncrement;
    }

    /* JADX INFO: renamed from: lambda$startRepeating$0$androidx-camera-extensions-internal-sessionprocessor-BasicExtenderSessionProcessor, reason: not valid java name */
    /* synthetic */ void m202xc693c9b1(SessionProcessor.CaptureCallback captureCallback, int i, long j, List list) {
        captureCallback.onCaptureCompleted(j, i, getCaptureResultKeyMapFromList(list));
    }

    void updateRepeating(final int i, final SessionProcessor.CaptureCallback captureCallback) {
        if (this.mRequestProcessor == null) {
            Logger.d(TAG, "mRequestProcessor is null, ignore repeating request");
            return;
        }
        RequestBuilder requestBuilder = new RequestBuilder();
        requestBuilder.addTargetOutputConfigIds(this.mPreviewOutputConfig.getId());
        if (this.mAnalysisOutputConfig != null) {
            requestBuilder.addTargetOutputConfigIds(this.mAnalysisOutputConfig.getId());
        }
        requestBuilder.setTemplateId(1);
        applyParameters(requestBuilder);
        applyPreviewStagesParameters(requestBuilder);
        RequestProcessor.Callback callback = new RequestProcessor.Callback() { // from class: androidx.camera.extensions.internal.sessionprocessor.BasicExtenderSessionProcessor.3
            @Override // androidx.camera.core.impl.RequestProcessor.Callback
            public void onCaptureCompleted(RequestProcessor.Request request, CameraCaptureResult cameraCaptureResult) {
                Long l;
                CaptureResult captureResult = Camera2CameraCaptureResultConverter.getCaptureResult(cameraCaptureResult);
                Preconditions.checkArgument(captureResult instanceof TotalCaptureResult, "Cannot get TotalCaptureResult from the cameraCaptureResult ");
                TotalCaptureResult totalCaptureResult = (TotalCaptureResult) captureResult;
                if (BasicExtenderSessionProcessor.this.mPreviewProcessor != null) {
                    BasicExtenderSessionProcessor.this.mPreviewProcessor.notifyCaptureResult(totalCaptureResult);
                } else if (ClientVersion.isMinimumCompatibleVersion(Version.VERSION_1_3) && ExtensionVersion.isMinimumCompatibleVersion(Version.VERSION_1_3) && (l = (Long) totalCaptureResult.get(CaptureResult.SENSOR_TIMESTAMP)) != null) {
                    captureCallback.onCaptureCompleted(l.longValue(), i, BasicExtenderSessionProcessor.this.getCaptureResultKeyMaps(totalCaptureResult));
                }
                if (BasicExtenderSessionProcessor.this.mRequestUpdateProcessor != null && BasicExtenderSessionProcessor.this.mRequestUpdateProcessor.process(totalCaptureResult) != null) {
                    BasicExtenderSessionProcessor.this.updateRepeating(i, captureCallback);
                }
                captureCallback.onCaptureSequenceCompleted(i);
            }
        };
        Logger.d(TAG, "requestProcessor setRepeating");
        this.mRequestProcessor.setRepeating(requestBuilder.build(), callback);
    }

    private void applyPreviewStagesParameters(RequestBuilder requestBuilder) {
        CaptureStageImpl captureStage = this.mPreviewExtenderImpl.getCaptureStage();
        if (captureStage != null) {
            for (Pair pair : captureStage.getParameters()) {
                requestBuilder.setParameters((CaptureRequest.Key) pair.first, pair.second);
            }
        }
    }

    @Override // androidx.camera.core.impl.SessionProcessor
    public void stopRepeating() {
        this.mRequestProcessor.stopRepeating();
    }

    @Override // androidx.camera.core.impl.SessionProcessor
    public int startCapture(final SessionProcessor.CaptureCallback captureCallback) {
        final int andIncrement = this.mNextCaptureSequenceId.getAndIncrement();
        if (this.mRequestProcessor == null || this.mIsCapturing) {
            Logger.d(TAG, "startCapture failed");
            captureCallback.onCaptureFailed(andIncrement);
            captureCallback.onCaptureSequenceAborted(andIncrement);
            return andIncrement;
        }
        this.mIsCapturing = true;
        ArrayList arrayList = new ArrayList();
        List<CaptureStageImpl> captureStages = this.mImageCaptureExtenderImpl.getCaptureStages();
        ArrayList arrayList2 = new ArrayList();
        for (CaptureStageImpl captureStageImpl : captureStages) {
            RequestBuilder requestBuilder = new RequestBuilder();
            requestBuilder.addTargetOutputConfigIds(this.mCaptureOutputConfig.getId());
            requestBuilder.setTemplateId(2);
            requestBuilder.setCaptureStageId(captureStageImpl.getId());
            arrayList2.add(Integer.valueOf(captureStageImpl.getId()));
            applyParameters(requestBuilder);
            applyPreviewStagesParameters(requestBuilder);
            for (Pair pair : captureStageImpl.getParameters()) {
                requestBuilder.setParameters((CaptureRequest.Key) pair.first, pair.second);
            }
            arrayList.add(requestBuilder.build());
        }
        Logger.d(TAG, "Wait for capture stage id: " + arrayList2);
        RequestProcessor.Callback callback = new RequestProcessor.Callback() { // from class: androidx.camera.extensions.internal.sessionprocessor.BasicExtenderSessionProcessor.4
            boolean mIsCaptureFailed = false;
            boolean mIsCaptureStarted = false;

            @Override // androidx.camera.core.impl.RequestProcessor.Callback
            public void onCaptureStarted(RequestProcessor.Request request, long j, long j2) {
                if (this.mIsCaptureStarted) {
                    return;
                }
                this.mIsCaptureStarted = true;
                captureCallback.onCaptureStarted(andIncrement, j2);
            }

            @Override // androidx.camera.core.impl.RequestProcessor.Callback
            public void onCaptureCompleted(RequestProcessor.Request request, CameraCaptureResult cameraCaptureResult) {
                CaptureResult captureResult = Camera2CameraCaptureResultConverter.getCaptureResult(cameraCaptureResult);
                Preconditions.checkArgument(captureResult instanceof TotalCaptureResult, "Cannot get capture TotalCaptureResult from the cameraCaptureResult ");
                TotalCaptureResult totalCaptureResult = (TotalCaptureResult) captureResult;
                RequestBuilder.RequestProcessorRequest requestProcessorRequest = (RequestBuilder.RequestProcessorRequest) request;
                if (BasicExtenderSessionProcessor.this.mStillCaptureProcessor != null) {
                    BasicExtenderSessionProcessor.this.mStillCaptureProcessor.notifyCaptureResult(totalCaptureResult, requestProcessorRequest.getCaptureStageId());
                    return;
                }
                captureCallback.onCaptureProcessStarted(andIncrement);
                captureCallback.onCaptureSequenceCompleted(andIncrement);
                BasicExtenderSessionProcessor.this.mIsCapturing = false;
            }

            @Override // androidx.camera.core.impl.RequestProcessor.Callback
            public void onCaptureFailed(RequestProcessor.Request request, CameraCaptureFailure cameraCaptureFailure) {
                if (this.mIsCaptureFailed) {
                    return;
                }
                this.mIsCaptureFailed = true;
                captureCallback.onCaptureFailed(andIncrement);
                captureCallback.onCaptureSequenceAborted(andIncrement);
                BasicExtenderSessionProcessor.this.mIsCapturing = false;
            }

            @Override // androidx.camera.core.impl.RequestProcessor.Callback
            public void onCaptureSequenceAborted(int i) {
                captureCallback.onCaptureSequenceAborted(andIncrement);
                BasicExtenderSessionProcessor.this.mIsCapturing = false;
            }
        };
        Logger.d(TAG, "startCapture");
        if (this.mStillCaptureProcessor != null) {
            this.mStillCaptureProcessor.startCapture(arrayList2, new StillCaptureProcessor.OnCaptureResultCallback() { // from class: androidx.camera.extensions.internal.sessionprocessor.BasicExtenderSessionProcessor.5
                @Override // androidx.camera.extensions.internal.sessionprocessor.StillCaptureProcessor.OnCaptureResultCallback
                public void onCompleted() {
                    captureCallback.onCaptureSequenceCompleted(andIncrement);
                    BasicExtenderSessionProcessor.this.mIsCapturing = false;
                }

                @Override // androidx.camera.extensions.internal.sessionprocessor.StillCaptureProcessor.OnCaptureResultCallback
                public void onError(Exception exc) {
                    captureCallback.onCaptureFailed(andIncrement);
                    BasicExtenderSessionProcessor.this.mIsCapturing = false;
                }

                @Override // androidx.camera.extensions.internal.sessionprocessor.StillCaptureProcessor.OnCaptureResultCallback
                public void onCaptureResult(long j, List<Pair<CaptureResult.Key, Object>> list) {
                    captureCallback.onCaptureCompleted(j, andIncrement, BasicExtenderSessionProcessor.this.getCaptureResultKeyMapFromList(list));
                }
            });
        }
        setImageProcessor(this.mCaptureOutputConfig.getId(), new ImageProcessor() { // from class: androidx.camera.extensions.internal.sessionprocessor.BasicExtenderSessionProcessor.6
            boolean mIsFirstFrame = true;

            @Override // androidx.camera.extensions.internal.sessionprocessor.ImageProcessor
            public void onNextImageAvailable(int i, long j, ImageReference imageReference, String str) {
                Logger.d(BasicExtenderSessionProcessor.TAG, "onNextImageAvailable  outputStreamId=" + i);
                if (BasicExtenderSessionProcessor.this.mStillCaptureProcessor != null) {
                    BasicExtenderSessionProcessor.this.mStillCaptureProcessor.notifyImage(imageReference);
                }
                if (this.mIsFirstFrame) {
                    captureCallback.onCaptureProcessStarted(andIncrement);
                    this.mIsFirstFrame = false;
                }
            }
        });
        this.mRequestProcessor.submit(arrayList, callback);
        return andIncrement;
    }

    @Override // androidx.camera.core.impl.SessionProcessor
    public void abortCapture(int i) {
        this.mRequestProcessor.abortCaptures();
    }

    @Override // androidx.camera.core.impl.SessionProcessor
    public int startTrigger(Config config, final SessionProcessor.CaptureCallback captureCallback) {
        Logger.d(TAG, "startTrigger");
        final int andIncrement = this.mNextCaptureSequenceId.getAndIncrement();
        RequestBuilder requestBuilder = new RequestBuilder();
        requestBuilder.addTargetOutputConfigIds(this.mPreviewOutputConfig.getId());
        if (this.mAnalysisOutputConfig != null) {
            requestBuilder.addTargetOutputConfigIds(this.mAnalysisOutputConfig.getId());
        }
        requestBuilder.setTemplateId(1);
        applyParameters(requestBuilder);
        applyPreviewStagesParameters(requestBuilder);
        CaptureRequestOptions captureRequestOptionsBuild = CaptureRequestOptions.Builder.from(config).build();
        for (Config.Option<?> option : captureRequestOptionsBuild.listOptions()) {
            requestBuilder.setParameters((CaptureRequest.Key) option.getToken(), captureRequestOptionsBuild.retrieveOption(option));
        }
        this.mRequestProcessor.submit(requestBuilder.build(), new RequestProcessor.Callback() { // from class: androidx.camera.extensions.internal.sessionprocessor.BasicExtenderSessionProcessor.7
            @Override // androidx.camera.core.impl.RequestProcessor.Callback
            public void onCaptureCompleted(RequestProcessor.Request request, CameraCaptureResult cameraCaptureResult) {
                captureCallback.onCaptureSequenceCompleted(andIncrement);
            }

            @Override // androidx.camera.core.impl.RequestProcessor.Callback
            public void onCaptureFailed(RequestProcessor.Request request, CameraCaptureFailure cameraCaptureFailure) {
                captureCallback.onCaptureFailed(andIncrement);
            }
        });
        return andIncrement;
    }

    @Override // androidx.camera.core.impl.SessionProcessor
    public Pair<Long, Long> getRealtimeCaptureLatency() {
        if (ClientVersion.isMinimumCompatibleVersion(Version.VERSION_1_4) && ExtensionVersion.isMinimumCompatibleVersion(Version.VERSION_1_4)) {
            return this.mImageCaptureExtenderImpl.getRealtimeCaptureLatency();
        }
        return null;
    }
}
