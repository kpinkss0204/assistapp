package androidx.camera.extensions.internal.sessionprocessor;

import android.content.Context;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CaptureFailure;
import android.hardware.camera2.CaptureRequest;
import android.hardware.camera2.CaptureResult;
import android.hardware.camera2.TotalCaptureResult;
import android.media.Image;
import android.util.Pair;
import android.util.Size;
import android.view.Surface;
import androidx.camera.camera2.impl.Camera2CameraCaptureResultConverter;
import androidx.camera.camera2.impl.Camera2ImplConfig;
import androidx.camera.camera2.interop.CaptureRequestOptions;
import androidx.camera.core.impl.CameraCaptureFailure;
import androidx.camera.core.impl.CameraCaptureResult;
import androidx.camera.core.impl.Config;
import androidx.camera.core.impl.OutputSurface;
import androidx.camera.core.impl.RequestProcessor;
import androidx.camera.core.impl.SessionProcessor;
import androidx.camera.extensions.impl.advanced.Camera2OutputConfigImpl;
import androidx.camera.extensions.impl.advanced.Camera2SessionConfigImpl;
import androidx.camera.extensions.impl.advanced.ImageProcessorImpl;
import androidx.camera.extensions.impl.advanced.ImageReferenceImpl;
import androidx.camera.extensions.impl.advanced.OutputSurfaceImpl;
import androidx.camera.extensions.impl.advanced.RequestProcessorImpl;
import androidx.camera.extensions.impl.advanced.SessionProcessorImpl;
import androidx.camera.extensions.internal.ClientVersion;
import androidx.camera.extensions.internal.ExtensionVersion;
import androidx.camera.extensions.internal.Version;
import androidx.core.util.Preconditions;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public class AdvancedSessionProcessor extends SessionProcessorBase {
    private final Context mContext;
    private final SessionProcessorImpl mImpl;

    @Override // androidx.camera.extensions.internal.sessionprocessor.SessionProcessorBase, androidx.camera.core.impl.SessionProcessor
    public /* bridge */ /* synthetic */ Set getSupportedCameraOperations() {
        return super.getSupportedCameraOperations();
    }

    public AdvancedSessionProcessor(SessionProcessorImpl sessionProcessorImpl, List<CaptureRequest.Key> list, Context context) {
        super(list);
        this.mImpl = sessionProcessorImpl;
        this.mContext = context;
    }

    @Override // androidx.camera.extensions.internal.sessionprocessor.SessionProcessorBase
    protected Camera2SessionConfig initSessionInternal(String str, Map<String, CameraCharacteristics> map, OutputSurface outputSurface, OutputSurface outputSurface2, OutputSurface outputSurface3) {
        return convertToCamera2SessionConfig(this.mImpl.initSession(str, map, this.mContext, new OutputSurfaceImplAdapter(outputSurface), new OutputSurfaceImplAdapter(outputSurface2), outputSurface3 == null ? null : new OutputSurfaceImplAdapter(outputSurface3)));
    }

    private Camera2SessionConfig convertToCamera2SessionConfig(Camera2SessionConfigImpl camera2SessionConfigImpl) {
        Camera2SessionConfigBuilder camera2SessionConfigBuilder = new Camera2SessionConfigBuilder();
        Iterator it = camera2SessionConfigImpl.getOutputConfigs().iterator();
        while (it.hasNext()) {
            camera2SessionConfigBuilder.addOutputConfig(Camera2OutputConfigConverter.fromImpl((Camera2OutputConfigImpl) it.next()));
        }
        for (CaptureRequest.Key key : camera2SessionConfigImpl.getSessionParameters().keySet()) {
            camera2SessionConfigBuilder.addSessionParameter(key, camera2SessionConfigImpl.getSessionParameters().get(key));
        }
        camera2SessionConfigBuilder.setSessionTemplateId(camera2SessionConfigImpl.getSessionTemplateId());
        return camera2SessionConfigBuilder.build();
    }

    @Override // androidx.camera.extensions.internal.sessionprocessor.SessionProcessorBase
    protected void deInitSessionInternal() {
        this.mImpl.deInitSession();
    }

    @Override // androidx.camera.core.impl.SessionProcessor
    public void setParameters(Config config) {
        this.mImpl.setParameters(convertConfigToMap(config));
    }

    private static HashMap<CaptureRequest.Key<?>, Object> convertConfigToMap(Config config) {
        HashMap<CaptureRequest.Key<?>, Object> map = new HashMap<>();
        CaptureRequestOptions captureRequestOptionsBuild = CaptureRequestOptions.Builder.from(config).build();
        for (Config.Option<?> option : captureRequestOptionsBuild.listOptions()) {
            map.put((CaptureRequest.Key) option.getToken(), captureRequestOptionsBuild.retrieveOption(option));
        }
        return map;
    }

    @Override // androidx.camera.core.impl.SessionProcessor
    public void onCaptureSessionStart(RequestProcessor requestProcessor) {
        this.mImpl.onCaptureSessionStart(new RequestProcessorImplAdapter(requestProcessor));
    }

    @Override // androidx.camera.core.impl.SessionProcessor
    public void onCaptureSessionEnd() {
        this.mImpl.onCaptureSessionEnd();
    }

    @Override // androidx.camera.core.impl.SessionProcessor
    public int startCapture(SessionProcessor.CaptureCallback captureCallback) {
        return this.mImpl.startCapture(new SessionProcessorImplCaptureCallbackAdapter(captureCallback));
    }

    @Override // androidx.camera.core.impl.SessionProcessor
    public int startRepeating(SessionProcessor.CaptureCallback captureCallback) {
        return this.mImpl.startRepeating(new SessionProcessorImplCaptureCallbackAdapter(captureCallback));
    }

    @Override // androidx.camera.core.impl.SessionProcessor
    public int startTrigger(Config config, SessionProcessor.CaptureCallback captureCallback) {
        HashMap<CaptureRequest.Key<?>, Object> mapConvertConfigToMap = convertConfigToMap(config);
        if (ClientVersion.isMinimumCompatibleVersion(Version.VERSION_1_3) && ExtensionVersion.isMinimumCompatibleVersion(Version.VERSION_1_3)) {
            return this.mImpl.startTrigger(mapConvertConfigToMap, new SessionProcessorImplCaptureCallbackAdapter(captureCallback));
        }
        return -1;
    }

    @Override // androidx.camera.core.impl.SessionProcessor
    public void stopRepeating() {
        this.mImpl.stopRepeating();
    }

    @Override // androidx.camera.core.impl.SessionProcessor
    public void abortCapture(int i) {
        this.mImpl.abortCapture(i);
    }

    @Override // androidx.camera.core.impl.SessionProcessor
    public Pair<Long, Long> getRealtimeCaptureLatency() {
        if (ClientVersion.isMinimumCompatibleVersion(Version.VERSION_1_4) && ExtensionVersion.isMinimumCompatibleVersion(Version.VERSION_1_4)) {
            return this.mImpl.getRealtimeCaptureLatency();
        }
        return null;
    }

    private static class OutputSurfaceImplAdapter implements OutputSurfaceImpl {
        private final OutputSurface mOutputSurface;

        OutputSurfaceImplAdapter(OutputSurface outputSurface) {
            this.mOutputSurface = outputSurface;
        }

        public Surface getSurface() {
            return this.mOutputSurface.getSurface();
        }

        public Size getSize() {
            return this.mOutputSurface.getSize();
        }

        public int getImageFormat() {
            return this.mOutputSurface.getImageFormat();
        }
    }

    private class RequestProcessorImplAdapter implements RequestProcessorImpl {
        private final RequestProcessor mRequestProcessor;

        RequestProcessorImplAdapter(RequestProcessor requestProcessor) {
            this.mRequestProcessor = requestProcessor;
        }

        public void setImageProcessor(int i, ImageProcessorImpl imageProcessorImpl) {
            AdvancedSessionProcessor.this.setImageProcessor(i, new ImageProcessorAdapter(imageProcessorImpl));
        }

        public int submit(RequestProcessorImpl.Request request, RequestProcessorImpl.Callback callback) {
            return this.mRequestProcessor.submit(new RequestAdapter(request), new CallbackAdapter(callback));
        }

        public int submit(List<RequestProcessorImpl.Request> list, RequestProcessorImpl.Callback callback) {
            ArrayList arrayList = new ArrayList();
            Iterator<RequestProcessorImpl.Request> it = list.iterator();
            while (it.hasNext()) {
                arrayList.add(new RequestAdapter(it.next()));
            }
            return this.mRequestProcessor.submit(arrayList, new CallbackAdapter(callback));
        }

        public int setRepeating(RequestProcessorImpl.Request request, RequestProcessorImpl.Callback callback) {
            return this.mRequestProcessor.setRepeating(new RequestAdapter(request), new CallbackAdapter(callback));
        }

        public void abortCaptures() {
            this.mRequestProcessor.abortCaptures();
        }

        public void stopRepeating() {
            this.mRequestProcessor.stopRepeating();
        }
    }

    private static class RequestAdapter implements RequestProcessor.Request {
        private final RequestProcessorImpl.Request mImplRequest;
        private final Config mParameters;
        private final List<Integer> mTargetOutputConfigIds;
        private final int mTemplateId;

        RequestAdapter(RequestProcessorImpl.Request request) {
            this.mImplRequest = request;
            ArrayList arrayList = new ArrayList();
            Iterator it = request.getTargetOutputConfigIds().iterator();
            while (it.hasNext()) {
                arrayList.add((Integer) it.next());
            }
            this.mTargetOutputConfigIds = arrayList;
            Camera2ImplConfig.Builder builder = new Camera2ImplConfig.Builder();
            for (CaptureRequest.Key key : request.getParameters().keySet()) {
                builder.setCaptureRequestOption(key, request.getParameters().get(key));
            }
            this.mParameters = builder.build();
            this.mTemplateId = request.getTemplateId().intValue();
        }

        @Override // androidx.camera.core.impl.RequestProcessor.Request
        public List<Integer> getTargetOutputConfigIds() {
            return this.mTargetOutputConfigIds;
        }

        @Override // androidx.camera.core.impl.RequestProcessor.Request
        public Config getParameters() {
            return this.mParameters;
        }

        @Override // androidx.camera.core.impl.RequestProcessor.Request
        public int getTemplateId() {
            return this.mTemplateId;
        }

        public RequestProcessorImpl.Request getImplRequest() {
            return this.mImplRequest;
        }
    }

    private static class ImageProcessorAdapter implements ImageProcessor {
        private final ImageProcessorImpl mImpl;

        ImageProcessorAdapter(ImageProcessorImpl imageProcessorImpl) {
            this.mImpl = imageProcessorImpl;
        }

        @Override // androidx.camera.extensions.internal.sessionprocessor.ImageProcessor
        public void onNextImageAvailable(int i, long j, ImageReference imageReference, String str) {
            this.mImpl.onNextImageAvailable(i, j, new ImageReferenceImplAdapter(imageReference), str);
        }
    }

    private static class ImageReferenceImplAdapter implements ImageReferenceImpl {
        private final ImageReference mImageReference;

        ImageReferenceImplAdapter(ImageReference imageReference) {
            this.mImageReference = imageReference;
        }

        public boolean increment() {
            return this.mImageReference.increment();
        }

        public boolean decrement() {
            return this.mImageReference.decrement();
        }

        public Image get() {
            return this.mImageReference.get();
        }
    }

    private static class CallbackAdapter implements RequestProcessor.Callback {
        private final RequestProcessorImpl.Callback mCallback;

        CallbackAdapter(RequestProcessorImpl.Callback callback) {
            this.mCallback = callback;
        }

        @Override // androidx.camera.core.impl.RequestProcessor.Callback
        public void onCaptureStarted(RequestProcessor.Request request, long j, long j2) {
            this.mCallback.onCaptureStarted(getImplRequest(request), j, j2);
        }

        @Override // androidx.camera.core.impl.RequestProcessor.Callback
        public void onCaptureProgressed(RequestProcessor.Request request, CameraCaptureResult cameraCaptureResult) {
            CaptureResult captureResult = Camera2CameraCaptureResultConverter.getCaptureResult(cameraCaptureResult);
            Preconditions.checkArgument(captureResult != null, "Cannot get CaptureResult from the cameraCaptureResult ");
            this.mCallback.onCaptureProgressed(getImplRequest(request), captureResult);
        }

        @Override // androidx.camera.core.impl.RequestProcessor.Callback
        public void onCaptureCompleted(RequestProcessor.Request request, CameraCaptureResult cameraCaptureResult) {
            CaptureResult captureResult = Camera2CameraCaptureResultConverter.getCaptureResult(cameraCaptureResult);
            Preconditions.checkArgument(captureResult instanceof TotalCaptureResult, "CaptureResult in cameraCaptureResult is not a TotalCaptureResult");
            this.mCallback.onCaptureCompleted(getImplRequest(request), (TotalCaptureResult) captureResult);
        }

        @Override // androidx.camera.core.impl.RequestProcessor.Callback
        public void onCaptureFailed(RequestProcessor.Request request, CameraCaptureFailure cameraCaptureFailure) {
            CaptureFailure captureFailure = Camera2CameraCaptureResultConverter.getCaptureFailure(cameraCaptureFailure);
            Preconditions.checkArgument(captureFailure != null, "CameraCaptureFailure does not contain CaptureFailure.");
            this.mCallback.onCaptureFailed(getImplRequest(request), captureFailure);
        }

        @Override // androidx.camera.core.impl.RequestProcessor.Callback
        public void onCaptureBufferLost(RequestProcessor.Request request, long j, int i) {
            this.mCallback.onCaptureBufferLost(getImplRequest(request), j, i);
        }

        @Override // androidx.camera.core.impl.RequestProcessor.Callback
        public void onCaptureSequenceCompleted(int i, long j) {
            this.mCallback.onCaptureSequenceCompleted(i, j);
        }

        @Override // androidx.camera.core.impl.RequestProcessor.Callback
        public void onCaptureSequenceAborted(int i) {
            this.mCallback.onCaptureSequenceAborted(i);
        }

        private RequestProcessorImpl.Request getImplRequest(RequestProcessor.Request request) {
            Preconditions.checkArgument(request instanceof RequestAdapter);
            return ((RequestAdapter) request).getImplRequest();
        }
    }

    private static class SessionProcessorImplCaptureCallbackAdapter implements SessionProcessorImpl.CaptureCallback {
        private final SessionProcessor.CaptureCallback mCaptureCallback;

        public void onCaptureProcessProgressed(int i) {
        }

        SessionProcessorImplCaptureCallbackAdapter(SessionProcessor.CaptureCallback captureCallback) {
            this.mCaptureCallback = captureCallback;
        }

        public void onCaptureStarted(int i, long j) {
            this.mCaptureCallback.onCaptureStarted(i, j);
        }

        public void onCaptureProcessStarted(int i) {
            this.mCaptureCallback.onCaptureProcessStarted(i);
        }

        public void onCaptureFailed(int i) {
            this.mCaptureCallback.onCaptureFailed(i);
        }

        public void onCaptureSequenceCompleted(int i) {
            this.mCaptureCallback.onCaptureSequenceCompleted(i);
        }

        public void onCaptureSequenceAborted(int i) {
            this.mCaptureCallback.onCaptureSequenceAborted(i);
        }

        public void onCaptureCompleted(long j, int i, Map<CaptureResult.Key, Object> map) {
            this.mCaptureCallback.onCaptureCompleted(j, i, map);
        }
    }
}
