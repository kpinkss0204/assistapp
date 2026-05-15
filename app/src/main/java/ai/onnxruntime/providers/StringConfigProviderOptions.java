package ai.onnxruntime.providers;

import ai.onnxruntime.OrtException;
import ai.onnxruntime.OrtProviderOptions;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

/* JADX INFO: loaded from: classes.dex */
abstract class StringConfigProviderOptions extends OrtProviderOptions {
    protected final Map<String, String> options;

    protected abstract void applyToNative(long j, long j2, String[] strArr, String[] strArr2) throws OrtException;

    protected StringConfigProviderOptions(long j) {
        super(j);
        this.options = new LinkedHashMap();
    }

    public void add(String str, String str2) throws OrtException {
        checkClosed();
        Objects.requireNonNull(str, "Key must not be null");
        Objects.requireNonNull(str2, "Value must not be null");
        this.options.put(str, str2);
    }

    public void parseOptionsString(String str) throws OrtException {
        for (String str2 : str.split(";")) {
            if (str2.contains("=")) {
                String[] strArrSplit = str2.split("=");
                if (strArrSplit.length == 2 && !strArrSplit[0].isEmpty() && !strArrSplit[1].isEmpty()) {
                    add(strArrSplit[0], strArrSplit[1]);
                } else {
                    throw new IllegalArgumentException("Failed to parse option from string '" + str2 + "'");
                }
            }
        }
    }

    public String toString() {
        return getClass().getSimpleName() + "(" + getOptionsString() + ")";
    }

    public String getOptionsString() {
        return (String) this.options.entrySet().stream().map(new Function() { // from class: ai.onnxruntime.providers.StringConfigProviderOptions$$ExternalSyntheticLambda0
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return StringConfigProviderOptions.lambda$getOptionsString$0((Map.Entry) obj);
            }
        }).collect(Collectors.joining(";", "", ";"));
    }

    static /* synthetic */ String lambda$getOptionsString$0(Map.Entry entry) {
        return ((String) entry.getKey()) + "=" + ((String) entry.getValue());
    }

    @Override // ai.onnxruntime.OrtProviderOptions
    protected void applyToNative() throws OrtException {
        if (this.options.isEmpty()) {
            return;
        }
        String[] strArr = new String[this.options.size()];
        String[] strArr2 = new String[this.options.size()];
        int i = 0;
        for (Map.Entry<String, String> entry : this.options.entrySet()) {
            strArr[i] = entry.getKey();
            strArr2[i] = entry.getValue();
            i++;
        }
        applyToNative(getApiHandle(), this.nativeHandle, strArr, strArr2);
    }
}
