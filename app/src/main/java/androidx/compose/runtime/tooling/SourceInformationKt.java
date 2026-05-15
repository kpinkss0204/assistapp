package androidx.compose.runtime.tooling;

import androidx.compose.runtime.internal.Utils_androidKt;
import androidx.constraintlayout.widget.ConstraintLayout;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: SourceInformation.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000.\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u0012\u0010\u0000\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0007\u001a\u0010\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0000\u001a\f\u0010\u0005\u001a\u00020\u0006*\u00020\u0007H\u0002\u001a\u0012\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t*\u00020\u0007H\u0002\u001a\u0012\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\n0\t*\u00020\u0007H\u0002\u001a\u0012\u0010\f\u001a\b\u0012\u0004\u0012\u00020\r0\t*\u00020\u0007H\u0002\u001a\f\u0010\u000e\u001a\u00020\u0003*\u00020\u0003H\u0002¨\u0006\u000f"}, d2 = {"parseSourceInformation", "Landroidx/compose/runtime/tooling/SourceInformation;", "data", "", "parseSourceInformationInternal", "hasSection", "", "Landroidx/compose/runtime/tooling/SourceInfoParserState;", "parseParameterIndex", "", "Landroidx/compose/runtime/tooling/ParameterSourceInformation;", "parseParameterNames", "parseLocations", "Landroidx/compose/runtime/tooling/LocationSourceInformation;", "replaceComposePrefix", "runtime"}, k = 2, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
public final class SourceInformationKt {
    public static final SourceInformation parseSourceInformation(String str) {
        if (str.length() == 0) {
            return null;
        }
        try {
            return parseSourceInformationInternal(str);
        } catch (ParseException e) {
            Utils_androidKt.logError(e.getMessage(), e);
            return null;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x0049  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x00a7  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x00ac  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x00bf  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x00c8  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final androidx.compose.runtime.tooling.SourceInformation parseSourceInformationInternal(java.lang.String r13) throws androidx.compose.runtime.tooling.ParseException {
        /*
            Method dump skipped, instruction units count: 220
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.runtime.tooling.SourceInformationKt.parseSourceInformationInternal(java.lang.String):androidx.compose.runtime.tooling.SourceInformation");
    }

    private static final boolean hasSection(SourceInfoParserState sourceInfoParserState) {
        return sourceInfoParserState.getI() < sourceInfoParserState.getData().length() - 1 && Character.isLetter(sourceInfoParserState.getData().charAt(sourceInfoParserState.getI())) && sourceInfoParserState.getData().charAt(sourceInfoParserState.getI() + 1) == '(';
    }

    private static final List<ParameterSourceInformation> parseParameterIndex(SourceInfoParserState sourceInfoParserState) throws ParseException {
        String strReplaceComposePrefix;
        sourceInfoParserState.advance(2);
        ArrayList arrayList = new ArrayList();
        boolean z = false;
        while (!sourceInfoParserState.atEnd() && !sourceInfoParserState.matches(')')) {
            if (sourceInfoParserState.matches('!')) {
                SourceInfoParserState.advance$default(sourceInfoParserState, 0, 1, null);
                String strTakeUntil = sourceInfoParserState.takeUntil("!,)");
                if (strTakeUntil.length() != 0) {
                    int i = Integer.parseInt(strTakeUntil);
                    int i2 = 0;
                    while (i > 0) {
                        int size = arrayList.size();
                        int i3 = 0;
                        while (true) {
                            if (i3 < size) {
                                if (((ParameterSourceInformation) arrayList.get(i3)).getSortedIndex() == i2) {
                                    i2++;
                                    break;
                                }
                                i3++;
                            } else {
                                arrayList.add(new ParameterSourceInformation(i2, null, null, 6, null));
                                i--;
                                break;
                            }
                        }
                    }
                } else {
                    z = true;
                }
            } else {
                int iTakeIntUntil = sourceInfoParserState.takeIntUntil("!:,)");
                if (sourceInfoParserState.matches(':')) {
                    SourceInfoParserState.advance$default(sourceInfoParserState, 0, 1, null);
                    strReplaceComposePrefix = replaceComposePrefix(sourceInfoParserState.takeUntil("!,)"));
                } else {
                    strReplaceComposePrefix = null;
                }
                if (z) {
                    int i4 = 0;
                    while (i4 < iTakeIntUntil) {
                        int size2 = arrayList.size();
                        int i5 = 0;
                        while (true) {
                            if (i5 < size2) {
                                if (((ParameterSourceInformation) arrayList.get(i5)).getSortedIndex() == i4) {
                                    i4++;
                                    break;
                                }
                                i5++;
                            } else {
                                arrayList.add(new ParameterSourceInformation(i4, null, null, 6, null));
                                break;
                            }
                        }
                    }
                    z = false;
                }
                arrayList.add(new ParameterSourceInformation(iTakeIntUntil, null, strReplaceComposePrefix, 2, null));
            }
            if (sourceInfoParserState.matches(',')) {
                SourceInfoParserState.advance$default(sourceInfoParserState, 0, 1, null);
            }
        }
        sourceInfoParserState.expect(')');
        SourceInfoParserState.advance$default(sourceInfoParserState, 0, 1, null);
        return arrayList;
    }

    private static final List<ParameterSourceInformation> parseParameterNames(SourceInfoParserState sourceInfoParserState) throws ParseException {
        String strReplaceComposePrefix;
        sourceInfoParserState.advance(2);
        ArrayList arrayList = new ArrayList();
        while (!sourceInfoParserState.atEnd() && !sourceInfoParserState.matches(')')) {
            String strTakeUntil = sourceInfoParserState.takeUntil(":,)");
            if (sourceInfoParserState.matches(':')) {
                SourceInfoParserState.advance$default(sourceInfoParserState, 0, 1, null);
                strReplaceComposePrefix = replaceComposePrefix(sourceInfoParserState.takeUntil(",)"));
            } else {
                strReplaceComposePrefix = null;
            }
            arrayList.add(new ParameterSourceInformation(arrayList.size(), strTakeUntil, strReplaceComposePrefix));
            if (sourceInfoParserState.matches(',')) {
                SourceInfoParserState.advance$default(sourceInfoParserState, 0, 1, null);
            }
        }
        sourceInfoParserState.expect(')');
        SourceInfoParserState.advance$default(sourceInfoParserState, 0, 1, null);
        return arrayList;
    }

    private static final List<LocationSourceInformation> parseLocations(SourceInfoParserState sourceInfoParserState) throws ParseException {
        boolean z;
        Integer numValueOf;
        ArrayList arrayList = new ArrayList();
        while (!sourceInfoParserState.atEnd() && !sourceInfoParserState.matches(':')) {
            if (sourceInfoParserState.matches('*')) {
                SourceInfoParserState.advance$default(sourceInfoParserState, 0, 1, null);
                z = true;
            } else {
                z = false;
            }
            Integer numValueOf2 = !sourceInfoParserState.matches('@') ? Integer.valueOf(sourceInfoParserState.takeIntUntil("@") + 1) : null;
            SourceInfoParserState.advance$default(sourceInfoParserState, 0, 1, null);
            int iTakeIntUntil = sourceInfoParserState.takeIntUntil("L,:");
            if (sourceInfoParserState.matches('L')) {
                SourceInfoParserState.advance$default(sourceInfoParserState, 0, 1, null);
                numValueOf = Integer.valueOf(sourceInfoParserState.takeIntUntil(",:"));
            } else {
                numValueOf = null;
            }
            arrayList.add(new LocationSourceInformation(numValueOf2 != null ? numValueOf2.intValue() : -1, iTakeIntUntil, numValueOf != null ? numValueOf.intValue() : -1, z));
            if (sourceInfoParserState.matches(',')) {
                SourceInfoParserState.advance$default(sourceInfoParserState, 0, 1, null);
            }
        }
        SourceInfoParserState.advance$default(sourceInfoParserState, 0, 1, null);
        return arrayList;
    }

    private static final String replaceComposePrefix(String str) {
        return StringsKt.replaceFirst$default(str, "c#", "androidx.compose.", false, 4, (Object) null);
    }
}
