package io.github.aakira.napier;

import io.github.aakira.napier.Napier;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlin.text.Typography;

/* JADX INFO: compiled from: DebugAntilog.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0002\u0018\u0000 \u00192\u00020\u0001:\u0001\u0019B\u000f\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0015\u0010\f\u001a\u00020\u00032\u0006\u0010\r\u001a\u00020\u0003H\u0000¢\u0006\u0002\b\u000eJ.\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u00032\b\u0010\u0014\u001a\u0004\u0018\u00010\t2\b\u0010\u0015\u001a\u0004\u0018\u00010\u0003H\u0014J\u0010\u0010\u0016\u001a\u00020\u00032\u0006\u0010\u0013\u001a\u00020\u0003H\u0002J\f\u0010\u0017\u001a\u00020\u0018*\u00020\u0012H\u0002R\u0016\u0010\u0005\u001a\n \u0007*\u0004\u0018\u00010\u00060\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0018\u0010\b\u001a\u00020\u0003*\u00020\t8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\u000b¨\u0006\u001a"}, d2 = {"Lio/github/aakira/napier/DebugAntilog;", "Lio/github/aakira/napier/Antilog;", "defaultTag", "", "(Ljava/lang/String;)V", "anonymousClass", "Ljava/util/regex/Pattern;", "kotlin.jvm.PlatformType", "stackTraceString", "", "getStackTraceString", "(Ljava/lang/Throwable;)Ljava/lang/String;", "createStackElementTag", "className", "createStackElementTag$napier_release", "performLog", "", "priority", "Lio/github/aakira/napier/Napier$Level;", "tag", "throwable", "message", "performTag", "toValue", "", "Companion", "napier_release"}, k = 1, mv = {1, 4, 2})
public final class DebugAntilog extends Antilog {
    private static final int CALL_STACK_INDEX = 9;
    private static final int MAX_LOG_LENGTH = 4000;
    private static final int MAX_TAG_LENGTH = 23;
    private final Pattern anonymousClass;
    private final String defaultTag;

    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 4, 2})
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[Napier.Level.values().length];
            $EnumSwitchMapping$0 = iArr;
            iArr[Napier.Level.VERBOSE.ordinal()] = 1;
            iArr[Napier.Level.DEBUG.ordinal()] = 2;
            iArr[Napier.Level.INFO.ordinal()] = 3;
            iArr[Napier.Level.WARNING.ordinal()] = 4;
            iArr[Napier.Level.ERROR.ordinal()] = 5;
            iArr[Napier.Level.ASSERT.ordinal()] = 6;
        }
    }

    public DebugAntilog() {
        this(null, 1, 0 == true ? 1 : 0);
    }

    public DebugAntilog(String defaultTag) {
        Intrinsics.checkNotNullParameter(defaultTag, "defaultTag");
        this.defaultTag = defaultTag;
        this.anonymousClass = Pattern.compile("(\\$\\d+)+$");
    }

    public /* synthetic */ DebugAntilog(String str, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? "app" : str);
    }

    /* JADX WARN: Code restructure failed: missing block: B:33:0x0088, code lost:
    
        r3 = r1 + 1;
     */
    @Override // io.github.aakira.napier.Antilog
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected void performLog(io.github.aakira.napier.Napier.Level r8, java.lang.String r9, java.lang.Throwable r10, java.lang.String r11) {
        /*
            r7 = this;
            java.lang.String r0 = "priority"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r8, r0)
            if (r9 == 0) goto L8
            goto Le
        L8:
            java.lang.String r9 = r7.defaultTag
            java.lang.String r9 = r7.performTag(r9)
        Le:
            if (r11 == 0) goto L2e
            if (r10 == 0) goto L36
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.StringBuilder r11 = r0.append(r11)
            r0 = 10
            java.lang.StringBuilder r11 = r11.append(r0)
            java.lang.String r10 = r7.getStackTraceString(r10)
            java.lang.StringBuilder r10 = r11.append(r10)
            java.lang.String r11 = r10.toString()
            goto L36
        L2e:
            if (r10 == 0) goto L95
            java.lang.String r11 = r7.getStackTraceString(r10)
            if (r11 == 0) goto L95
        L36:
            int r10 = r11.length()
            r0 = 4000(0xfa0, float:5.605E-42)
            if (r10 > r0) goto L4e
            io.github.aakira.napier.Napier$Level r10 = io.github.aakira.napier.Napier.Level.ASSERT
            if (r8 != r10) goto L46
            android.util.Log.wtf(r9, r11)
            return
        L46:
            int r8 = r7.toValue(r8)
            android.util.Log.println(r8, r9, r11)
            return
        L4e:
            r0 = 0
            r3 = r0
        L50:
            if (r3 >= r10) goto L95
            r1 = r11
            java.lang.CharSequence r1 = (java.lang.CharSequence) r1
            r5 = 4
            r6 = 0
            r2 = 10
            r4 = 0
            int r0 = kotlin.text.StringsKt.indexOf$default(r1, r2, r3, r4, r5, r6)
            r1 = -1
            if (r0 == r1) goto L62
            goto L63
        L62:
            r0 = r10
        L63:
            int r1 = r3 + 4000
            int r1 = java.lang.Math.min(r0, r1)
            if (r11 == 0) goto L8d
            java.lang.String r2 = r11.substring(r3, r1)
            java.lang.String r3 = "(this as java.lang.Strin…ing(startIndex, endIndex)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r2, r3)
            int r3 = r7.toValue(r8)
            r4 = 7
            if (r3 != r4) goto L7f
            android.util.Log.wtf(r9, r2)
            goto L86
        L7f:
            int r3 = r7.toValue(r8)
            android.util.Log.println(r3, r9, r2)
        L86:
            if (r1 < r0) goto L8b
            int r3 = r1 + 1
            goto L50
        L8b:
            r3 = r1
            goto L63
        L8d:
            java.lang.NullPointerException r8 = new java.lang.NullPointerException
            java.lang.String r9 = "null cannot be cast to non-null type java.lang.String"
            r8.<init>(r9)
            throw r8
        L95:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: io.github.aakira.napier.DebugAntilog.performLog(io.github.aakira.napier.Napier$Level, java.lang.String, java.lang.Throwable, java.lang.String):void");
    }

    private final String performTag(String tag) {
        Thread threadCurrentThread = Thread.currentThread();
        Intrinsics.checkNotNullExpressionValue(threadCurrentThread, "Thread.currentThread()");
        StackTraceElement[] stackTrace = threadCurrentThread.getStackTrace();
        if (stackTrace == null || stackTrace.length < 9) {
            return tag;
        }
        StackTraceElement stackTraceElement = stackTrace[9];
        StringBuilder sb = new StringBuilder();
        String className = stackTraceElement.getClassName();
        Intrinsics.checkNotNullExpressionValue(className, "className");
        return sb.append(createStackElementTag$napier_release(className)).append(Typography.dollar).append(stackTraceElement.getMethodName()).toString();
    }

    public final String createStackElementTag$napier_release(String className) {
        Intrinsics.checkNotNullParameter(className, "className");
        Matcher matcher = this.anonymousClass.matcher(className);
        if (matcher.find()) {
            className = matcher.replaceAll("");
            Intrinsics.checkNotNullExpressionValue(className, "m.replaceAll(\"\")");
        }
        int iLastIndexOf$default = StringsKt.lastIndexOf$default((CharSequence) className, '.', 0, false, 6, (Object) null) + 1;
        if (className == null) {
            throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
        }
        String strSubstring = className.substring(iLastIndexOf$default);
        Intrinsics.checkNotNullExpressionValue(strSubstring, "(this as java.lang.String).substring(startIndex)");
        strSubstring.length();
        return strSubstring;
    }

    private final String getStackTraceString(Throwable th) {
        StringWriter stringWriter = new StringWriter(256);
        PrintWriter printWriter = new PrintWriter((Writer) stringWriter, false);
        th.printStackTrace(printWriter);
        printWriter.flush();
        String string = stringWriter.toString();
        Intrinsics.checkNotNullExpressionValue(string, "sw.toString()");
        return string;
    }

    private final int toValue(Napier.Level level) {
        switch (WhenMappings.$EnumSwitchMapping$0[level.ordinal()]) {
            case 1:
                return 2;
            case 2:
                return 3;
            case 3:
                return 4;
            case 4:
                return 5;
            case 5:
                return 6;
            case 6:
                return 7;
            default:
                throw new NoWhenBranchMatchedException();
        }
    }
}
