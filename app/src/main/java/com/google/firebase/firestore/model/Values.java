package com.google.firebase.firestore.model;

import com.google.firebase.firestore.util.Assert;
import com.google.firebase.firestore.util.Util;
import com.google.firestore.v1.ArrayValue;
import com.google.firestore.v1.ArrayValueOrBuilder;
import com.google.firestore.v1.MapValue;
import com.google.firestore.v1.Value;
import com.google.protobuf.ByteString;
import com.google.protobuf.NullValue;
import com.google.protobuf.Timestamp;
import com.google.type.LatLng;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

/* JADX INFO: loaded from: classes4.dex */
public class Values {
    public static final Value MAX_VALUE;
    public static final Value MAX_VALUE_TYPE;
    public static Value MIN_ARRAY = null;
    public static Value MIN_BOOLEAN = null;
    public static Value MIN_BYTES = null;
    public static Value MIN_GEO_POINT = null;
    public static Value MIN_MAP = null;
    public static Value MIN_NUMBER = null;
    public static Value MIN_REFERENCE = null;
    public static Value MIN_STRING = null;
    public static Value MIN_TIMESTAMP = null;
    public static final Value MIN_VALUE;
    private static final Value MIN_VECTOR_VALUE;
    public static final Value NAN_VALUE = Value.newBuilder().setDoubleValue(Double.NaN).build();
    public static final Value NULL_VALUE;
    public static final String TYPE_KEY = "__type__";
    public static final int TYPE_ORDER_ARRAY = 9;
    public static final int TYPE_ORDER_BLOB = 6;
    public static final int TYPE_ORDER_BOOLEAN = 1;
    public static final int TYPE_ORDER_GEOPOINT = 8;
    public static final int TYPE_ORDER_MAP = 11;
    public static final int TYPE_ORDER_MAX_VALUE = Integer.MAX_VALUE;
    public static final int TYPE_ORDER_NULL = 0;
    public static final int TYPE_ORDER_NUMBER = 2;
    public static final int TYPE_ORDER_REFERENCE = 7;
    public static final int TYPE_ORDER_SERVER_TIMESTAMP = 4;
    public static final int TYPE_ORDER_STRING = 5;
    public static final int TYPE_ORDER_TIMESTAMP = 3;
    public static final int TYPE_ORDER_VECTOR = 10;
    public static final String VECTOR_MAP_VECTORS_KEY = "value";
    public static final Value VECTOR_VALUE_TYPE;

    static {
        Value valueBuild = Value.newBuilder().setNullValue(NullValue.NULL_VALUE).build();
        NULL_VALUE = valueBuild;
        MIN_VALUE = valueBuild;
        Value valueBuild2 = Value.newBuilder().setStringValue("__max__").build();
        MAX_VALUE_TYPE = valueBuild2;
        MAX_VALUE = Value.newBuilder().setMapValue(MapValue.newBuilder().putFields(TYPE_KEY, valueBuild2)).build();
        Value valueBuild3 = Value.newBuilder().setStringValue("__vector__").build();
        VECTOR_VALUE_TYPE = valueBuild3;
        MIN_VECTOR_VALUE = Value.newBuilder().setMapValue(MapValue.newBuilder().putFields(TYPE_KEY, valueBuild3).putFields("value", Value.newBuilder().setArrayValue(ArrayValue.newBuilder()).build())).build();
        MIN_BOOLEAN = Value.newBuilder().setBooleanValue(false).build();
        MIN_NUMBER = Value.newBuilder().setDoubleValue(Double.NaN).build();
        MIN_TIMESTAMP = Value.newBuilder().setTimestampValue(Timestamp.newBuilder().setSeconds(Long.MIN_VALUE)).build();
        MIN_STRING = Value.newBuilder().setStringValue("").build();
        MIN_BYTES = Value.newBuilder().setBytesValue(ByteString.EMPTY).build();
        MIN_REFERENCE = refValue(DatabaseId.EMPTY, DocumentKey.empty());
        MIN_GEO_POINT = Value.newBuilder().setGeoPointValue(LatLng.newBuilder().setLatitude(-90.0d).setLongitude(-180.0d)).build();
        MIN_ARRAY = Value.newBuilder().setArrayValue(ArrayValue.getDefaultInstance()).build();
        MIN_MAP = Value.newBuilder().setMapValue(MapValue.getDefaultInstance()).build();
    }

    /* JADX INFO: renamed from: com.google.firebase.firestore.model.Values$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$google$firestore$v1$Value$ValueTypeCase;

        static {
            int[] iArr = new int[Value.ValueTypeCase.values().length];
            $SwitchMap$com$google$firestore$v1$Value$ValueTypeCase = iArr;
            try {
                iArr[Value.ValueTypeCase.NULL_VALUE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$google$firestore$v1$Value$ValueTypeCase[Value.ValueTypeCase.BOOLEAN_VALUE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$google$firestore$v1$Value$ValueTypeCase[Value.ValueTypeCase.INTEGER_VALUE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$google$firestore$v1$Value$ValueTypeCase[Value.ValueTypeCase.DOUBLE_VALUE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$google$firestore$v1$Value$ValueTypeCase[Value.ValueTypeCase.TIMESTAMP_VALUE.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$google$firestore$v1$Value$ValueTypeCase[Value.ValueTypeCase.STRING_VALUE.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$google$firestore$v1$Value$ValueTypeCase[Value.ValueTypeCase.BYTES_VALUE.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$google$firestore$v1$Value$ValueTypeCase[Value.ValueTypeCase.REFERENCE_VALUE.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$com$google$firestore$v1$Value$ValueTypeCase[Value.ValueTypeCase.GEO_POINT_VALUE.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$com$google$firestore$v1$Value$ValueTypeCase[Value.ValueTypeCase.ARRAY_VALUE.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                $SwitchMap$com$google$firestore$v1$Value$ValueTypeCase[Value.ValueTypeCase.MAP_VALUE.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
        }
    }

    public static int typeOrder(Value value) {
        switch (AnonymousClass1.$SwitchMap$com$google$firestore$v1$Value$ValueTypeCase[value.getValueTypeCase().ordinal()]) {
            case 1:
                return 0;
            case 2:
                return 1;
            case 3:
            case 4:
                return 2;
            case 5:
                return 3;
            case 6:
                return 5;
            case 7:
                return 6;
            case 8:
                return 7;
            case 9:
                return 8;
            case 10:
                return 9;
            case 11:
                if (ServerTimestamps.isServerTimestamp(value)) {
                    return 4;
                }
                if (isMaxValue(value)) {
                    return Integer.MAX_VALUE;
                }
                return isVectorValue(value) ? 10 : 11;
            default:
                throw Assert.fail("Invalid value type: " + value.getValueTypeCase(), new Object[0]);
        }
    }

    public static boolean equals(Value value, Value value2) {
        int iTypeOrder;
        if (value == value2) {
            return true;
        }
        if (value == null || value2 == null || (iTypeOrder = typeOrder(value)) != typeOrder(value2)) {
            return false;
        }
        if (iTypeOrder == 2) {
            return numberEquals(value, value2);
        }
        if (iTypeOrder == 4) {
            return ServerTimestamps.getLocalWriteTime(value).equals(ServerTimestamps.getLocalWriteTime(value2));
        }
        if (iTypeOrder == Integer.MAX_VALUE) {
            return true;
        }
        switch (iTypeOrder) {
            case 9:
                return arrayEquals(value, value2);
            case 10:
            case 11:
                return objectEquals(value, value2);
            default:
                return value.equals(value2);
        }
    }

    private static boolean numberEquals(Value value, Value value2) {
        return (value.getValueTypeCase() == Value.ValueTypeCase.INTEGER_VALUE && value2.getValueTypeCase() == Value.ValueTypeCase.INTEGER_VALUE) ? value.getIntegerValue() == value2.getIntegerValue() : value.getValueTypeCase() == Value.ValueTypeCase.DOUBLE_VALUE && value2.getValueTypeCase() == Value.ValueTypeCase.DOUBLE_VALUE && Double.doubleToLongBits(value.getDoubleValue()) == Double.doubleToLongBits(value2.getDoubleValue());
    }

    private static boolean arrayEquals(Value value, Value value2) {
        ArrayValue arrayValue = value.getArrayValue();
        ArrayValue arrayValue2 = value2.getArrayValue();
        if (arrayValue.getValuesCount() != arrayValue2.getValuesCount()) {
            return false;
        }
        for (int i = 0; i < arrayValue.getValuesCount(); i++) {
            if (!equals(arrayValue.getValues(i), arrayValue2.getValues(i))) {
                return false;
            }
        }
        return true;
    }

    private static boolean objectEquals(Value value, Value value2) {
        MapValue mapValue = value.getMapValue();
        MapValue mapValue2 = value2.getMapValue();
        if (mapValue.getFieldsCount() != mapValue2.getFieldsCount()) {
            return false;
        }
        for (Map.Entry<String, Value> entry : mapValue.getFieldsMap().entrySet()) {
            if (!equals(entry.getValue(), mapValue2.getFieldsMap().get(entry.getKey()))) {
                return false;
            }
        }
        return true;
    }

    public static boolean contains(ArrayValueOrBuilder arrayValueOrBuilder, Value value) {
        Iterator<Value> it = arrayValueOrBuilder.getValuesList().iterator();
        while (it.hasNext()) {
            if (equals(it.next(), value)) {
                return true;
            }
        }
        return false;
    }

    public static int compare(Value value, Value value2) {
        int iTypeOrder = typeOrder(value);
        int iTypeOrder2 = typeOrder(value2);
        if (iTypeOrder != iTypeOrder2) {
            return Integer.compare(iTypeOrder, iTypeOrder2);
        }
        if (iTypeOrder != Integer.MAX_VALUE) {
            switch (iTypeOrder) {
                case 0:
                    break;
                case 1:
                    return Util.compareBooleans(value.getBooleanValue(), value2.getBooleanValue());
                case 2:
                    return compareNumbers(value, value2);
                case 3:
                    return compareTimestamps(value.getTimestampValue(), value2.getTimestampValue());
                case 4:
                    return compareTimestamps(ServerTimestamps.getLocalWriteTime(value), ServerTimestamps.getLocalWriteTime(value2));
                case 5:
                    return Util.compareUtf8Strings(value.getStringValue(), value2.getStringValue());
                case 6:
                    return Util.compareByteStrings(value.getBytesValue(), value2.getBytesValue());
                case 7:
                    return compareReferences(value.getReferenceValue(), value2.getReferenceValue());
                case 8:
                    return compareGeoPoints(value.getGeoPointValue(), value2.getGeoPointValue());
                case 9:
                    return compareArrays(value.getArrayValue(), value2.getArrayValue());
                case 10:
                    return compareVectors(value.getMapValue(), value2.getMapValue());
                case 11:
                    return compareMaps(value.getMapValue(), value2.getMapValue());
                default:
                    throw Assert.fail("Invalid value type: " + iTypeOrder, new Object[0]);
            }
        }
        return 0;
    }

    public static int lowerBoundCompare(Value value, boolean z, Value value2, boolean z2) {
        int iCompare = compare(value, value2);
        if (iCompare != 0) {
            return iCompare;
        }
        if (!z || z2) {
            return (z || !z2) ? 0 : 1;
        }
        return -1;
    }

    public static int upperBoundCompare(Value value, boolean z, Value value2, boolean z2) {
        int iCompare = compare(value, value2);
        if (iCompare != 0) {
            return iCompare;
        }
        if (!z || z2) {
            return (z || !z2) ? 0 : -1;
        }
        return 1;
    }

    private static int compareNumbers(Value value, Value value2) {
        if (value.getValueTypeCase() == Value.ValueTypeCase.DOUBLE_VALUE) {
            double doubleValue = value.getDoubleValue();
            if (value2.getValueTypeCase() == Value.ValueTypeCase.DOUBLE_VALUE) {
                return Util.compareDoubles(doubleValue, value2.getDoubleValue());
            }
            if (value2.getValueTypeCase() == Value.ValueTypeCase.INTEGER_VALUE) {
                return Util.compareMixed(doubleValue, value2.getIntegerValue());
            }
        } else if (value.getValueTypeCase() == Value.ValueTypeCase.INTEGER_VALUE) {
            long integerValue = value.getIntegerValue();
            if (value2.getValueTypeCase() == Value.ValueTypeCase.INTEGER_VALUE) {
                return Long.compare(integerValue, value2.getIntegerValue());
            }
            if (value2.getValueTypeCase() == Value.ValueTypeCase.DOUBLE_VALUE) {
                return Util.compareMixed(value2.getDoubleValue(), integerValue) * (-1);
            }
        }
        throw Assert.fail("Unexpected values: %s vs %s", value, value2);
    }

    private static int compareTimestamps(Timestamp timestamp, Timestamp timestamp2) {
        int iCompare = Long.compare(timestamp.getSeconds(), timestamp2.getSeconds());
        return iCompare != 0 ? iCompare : Integer.compare(timestamp.getNanos(), timestamp2.getNanos());
    }

    private static int compareReferences(String str, String str2) {
        String[] strArrSplit = str.split("/", -1);
        String[] strArrSplit2 = str2.split("/", -1);
        int iMin = Math.min(strArrSplit.length, strArrSplit2.length);
        for (int i = 0; i < iMin; i++) {
            int iCompareTo = strArrSplit[i].compareTo(strArrSplit2[i]);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        return Integer.compare(strArrSplit.length, strArrSplit2.length);
    }

    private static int compareGeoPoints(LatLng latLng, LatLng latLng2) {
        int iCompareDoubles = Util.compareDoubles(latLng.getLatitude(), latLng2.getLatitude());
        return iCompareDoubles == 0 ? Util.compareDoubles(latLng.getLongitude(), latLng2.getLongitude()) : iCompareDoubles;
    }

    private static int compareArrays(ArrayValue arrayValue, ArrayValue arrayValue2) {
        int iMin = Math.min(arrayValue.getValuesCount(), arrayValue2.getValuesCount());
        for (int i = 0; i < iMin; i++) {
            int iCompare = compare(arrayValue.getValues(i), arrayValue2.getValues(i));
            if (iCompare != 0) {
                return iCompare;
            }
        }
        return Integer.compare(arrayValue.getValuesCount(), arrayValue2.getValuesCount());
    }

    private static int compareMaps(MapValue mapValue, MapValue mapValue2) {
        Iterator it = new TreeMap(mapValue.getFieldsMap()).entrySet().iterator();
        Iterator it2 = new TreeMap(mapValue2.getFieldsMap()).entrySet().iterator();
        while (it.hasNext() && it2.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            Map.Entry entry2 = (Map.Entry) it2.next();
            int iCompareUtf8Strings = Util.compareUtf8Strings((String) entry.getKey(), (String) entry2.getKey());
            if (iCompareUtf8Strings != 0) {
                return iCompareUtf8Strings;
            }
            int iCompare = compare((Value) entry.getValue(), (Value) entry2.getValue());
            if (iCompare != 0) {
                return iCompare;
            }
        }
        return Util.compareBooleans(it.hasNext(), it2.hasNext());
    }

    private static int compareVectors(MapValue mapValue, MapValue mapValue2) {
        Map<String, Value> fieldsMap = mapValue.getFieldsMap();
        Map<String, Value> fieldsMap2 = mapValue2.getFieldsMap();
        ArrayValue arrayValue = fieldsMap.get("value").getArrayValue();
        ArrayValue arrayValue2 = fieldsMap2.get("value").getArrayValue();
        int iCompare = Integer.compare(arrayValue.getValuesCount(), arrayValue2.getValuesCount());
        return iCompare != 0 ? iCompare : compareArrays(arrayValue, arrayValue2);
    }

    public static String canonicalId(Value value) {
        StringBuilder sb = new StringBuilder();
        canonifyValue(sb, value);
        return sb.toString();
    }

    private static void canonifyValue(StringBuilder sb, Value value) {
        switch (AnonymousClass1.$SwitchMap$com$google$firestore$v1$Value$ValueTypeCase[value.getValueTypeCase().ordinal()]) {
            case 1:
                sb.append("null");
                return;
            case 2:
                sb.append(value.getBooleanValue());
                return;
            case 3:
                sb.append(value.getIntegerValue());
                return;
            case 4:
                sb.append(value.getDoubleValue());
                return;
            case 5:
                canonifyTimestamp(sb, value.getTimestampValue());
                return;
            case 6:
                sb.append(value.getStringValue());
                return;
            case 7:
                sb.append(Util.toDebugString(value.getBytesValue()));
                return;
            case 8:
                canonifyReference(sb, value);
                return;
            case 9:
                canonifyGeoPoint(sb, value.getGeoPointValue());
                return;
            case 10:
                canonifyArray(sb, value.getArrayValue());
                return;
            case 11:
                canonifyObject(sb, value.getMapValue());
                return;
            default:
                throw Assert.fail("Invalid value type: " + value.getValueTypeCase(), new Object[0]);
        }
    }

    private static void canonifyTimestamp(StringBuilder sb, Timestamp timestamp) {
        sb.append(String.format("time(%s,%s)", Long.valueOf(timestamp.getSeconds()), Integer.valueOf(timestamp.getNanos())));
    }

    private static void canonifyGeoPoint(StringBuilder sb, LatLng latLng) {
        sb.append(String.format("geo(%s,%s)", Double.valueOf(latLng.getLatitude()), Double.valueOf(latLng.getLongitude())));
    }

    private static void canonifyReference(StringBuilder sb, Value value) {
        Assert.hardAssert(isReferenceValue(value), "Value should be a ReferenceValue", new Object[0]);
        sb.append(DocumentKey.fromName(value.getReferenceValue()));
    }

    private static void canonifyObject(StringBuilder sb, MapValue mapValue) {
        ArrayList<String> arrayList = new ArrayList(mapValue.getFieldsMap().keySet());
        Collections.sort(arrayList);
        sb.append("{");
        boolean z = true;
        for (String str : arrayList) {
            if (z) {
                z = false;
            } else {
                sb.append(",");
            }
            sb.append(str).append(":");
            canonifyValue(sb, mapValue.getFieldsOrThrow(str));
        }
        sb.append("}");
    }

    private static void canonifyArray(StringBuilder sb, ArrayValue arrayValue) {
        sb.append("[");
        for (int i = 0; i < arrayValue.getValuesCount(); i++) {
            canonifyValue(sb, arrayValue.getValues(i));
            if (i != arrayValue.getValuesCount() - 1) {
                sb.append(",");
            }
        }
        sb.append("]");
    }

    public static boolean isInteger(Value value) {
        return value != null && value.getValueTypeCase() == Value.ValueTypeCase.INTEGER_VALUE;
    }

    public static boolean isDouble(Value value) {
        return value != null && value.getValueTypeCase() == Value.ValueTypeCase.DOUBLE_VALUE;
    }

    public static boolean isNumber(Value value) {
        return isInteger(value) || isDouble(value);
    }

    public static boolean isArray(Value value) {
        return value != null && value.getValueTypeCase() == Value.ValueTypeCase.ARRAY_VALUE;
    }

    public static boolean isReferenceValue(Value value) {
        return value != null && value.getValueTypeCase() == Value.ValueTypeCase.REFERENCE_VALUE;
    }

    public static boolean isNullValue(Value value) {
        return value != null && value.getValueTypeCase() == Value.ValueTypeCase.NULL_VALUE;
    }

    public static boolean isNanValue(Value value) {
        return value != null && Double.isNaN(value.getDoubleValue());
    }

    public static boolean isMapValue(Value value) {
        return value != null && value.getValueTypeCase() == Value.ValueTypeCase.MAP_VALUE;
    }

    public static Value refValue(DatabaseId databaseId, DocumentKey documentKey) {
        return Value.newBuilder().setReferenceValue(String.format("projects/%s/databases/%s/documents/%s", databaseId.getProjectId(), databaseId.getDatabaseId(), documentKey.toString())).build();
    }

    public static Value getLowerBound(Value value) {
        switch (AnonymousClass1.$SwitchMap$com$google$firestore$v1$Value$ValueTypeCase[value.getValueTypeCase().ordinal()]) {
            case 1:
                return NULL_VALUE;
            case 2:
                return MIN_BOOLEAN;
            case 3:
            case 4:
                return MIN_NUMBER;
            case 5:
                return MIN_TIMESTAMP;
            case 6:
                return MIN_STRING;
            case 7:
                return MIN_BYTES;
            case 8:
                return MIN_REFERENCE;
            case 9:
                return MIN_GEO_POINT;
            case 10:
                return MIN_ARRAY;
            case 11:
                if (isVectorValue(value)) {
                    return MIN_VECTOR_VALUE;
                }
                return MIN_MAP;
            default:
                throw new IllegalArgumentException("Unknown value type: " + value.getValueTypeCase());
        }
    }

    public static Value getUpperBound(Value value) {
        switch (AnonymousClass1.$SwitchMap$com$google$firestore$v1$Value$ValueTypeCase[value.getValueTypeCase().ordinal()]) {
            case 1:
                return MIN_BOOLEAN;
            case 2:
                return MIN_NUMBER;
            case 3:
            case 4:
                return MIN_TIMESTAMP;
            case 5:
                return MIN_STRING;
            case 6:
                return MIN_BYTES;
            case 7:
                return MIN_REFERENCE;
            case 8:
                return MIN_GEO_POINT;
            case 9:
                return MIN_ARRAY;
            case 10:
                return MIN_VECTOR_VALUE;
            case 11:
                if (isVectorValue(value)) {
                    return MIN_MAP;
                }
                return MAX_VALUE;
            default:
                throw new IllegalArgumentException("Unknown value type: " + value.getValueTypeCase());
        }
    }

    public static boolean isMaxValue(Value value) {
        return MAX_VALUE_TYPE.equals(value.getMapValue().getFieldsMap().get(TYPE_KEY));
    }

    public static boolean isVectorValue(Value value) {
        return VECTOR_VALUE_TYPE.equals(value.getMapValue().getFieldsMap().get(TYPE_KEY));
    }
}
