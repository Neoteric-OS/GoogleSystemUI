package androidx.datastore.preferences.protobuf;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class SchemaUtil {
    public static final Class GENERATED_MESSAGE_CLASS;
    public static final UnknownFieldSetLiteSchema PROTO2_UNKNOWN_FIELD_SET_SCHEMA;
    public static final UnknownFieldSetLiteSchema PROTO3_UNKNOWN_FIELD_SET_SCHEMA;
    public static final UnknownFieldSetLiteSchema UNKNOWN_FIELD_SET_LITE_SCHEMA;

    static {
        Class<?> cls;
        try {
            cls = Class.forName("androidx.datastore.preferences.protobuf.GeneratedMessageV3");
        } catch (Throwable unused) {
            cls = null;
        }
        GENERATED_MESSAGE_CLASS = cls;
        PROTO2_UNKNOWN_FIELD_SET_SCHEMA = getUnknownFieldSetSchema(false);
        PROTO3_UNKNOWN_FIELD_SET_SCHEMA = getUnknownFieldSetSchema(true);
        UNKNOWN_FIELD_SET_LITE_SCHEMA = new UnknownFieldSetLiteSchema();
    }

    public static int computeSizeByteStringList(int i, List list) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int computeTagSize = CodedOutputStream$OutputStreamEncoder.computeTagSize(i) * size;
        for (int i2 = 0; i2 < list.size(); i2++) {
            computeTagSize += CodedOutputStream$OutputStreamEncoder.computeBytesSizeNoTag((ByteString) list.get(i2));
        }
        return computeTagSize;
    }

    public static int computeSizeEnumList(int i, List list) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return (CodedOutputStream$OutputStreamEncoder.computeTagSize(i) * size) + computeSizeEnumListNoTag(list);
    }

    public static int computeSizeEnumListNoTag(List list) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int i = 0;
        for (int i2 = 0; i2 < size; i2++) {
            i += CodedOutputStream$OutputStreamEncoder.computeInt32SizeNoTag(((Integer) list.get(i2)).intValue());
        }
        return i;
    }

    public static int computeSizeFixed32List(int i, List list) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return CodedOutputStream$OutputStreamEncoder.computeFixed32Size(i) * size;
    }

    public static int computeSizeFixed32ListNoTag(List list) {
        return list.size() * 4;
    }

    public static int computeSizeFixed64List(int i, List list) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return CodedOutputStream$OutputStreamEncoder.computeFixed64Size(i) * size;
    }

    public static int computeSizeFixed64ListNoTag(List list) {
        return list.size() * 8;
    }

    public static int computeSizeInt32List(int i, List list) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return (CodedOutputStream$OutputStreamEncoder.computeTagSize(i) * size) + computeSizeInt32ListNoTag(list);
    }

    public static int computeSizeInt32ListNoTag(List list) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int i = 0;
        for (int i2 = 0; i2 < size; i2++) {
            i += CodedOutputStream$OutputStreamEncoder.computeInt32SizeNoTag(((Integer) list.get(i2)).intValue());
        }
        return i;
    }

    public static int computeSizeInt64List(int i, List list) {
        if (list.size() == 0) {
            return 0;
        }
        return (CodedOutputStream$OutputStreamEncoder.computeTagSize(i) * list.size()) + computeSizeInt64ListNoTag(list);
    }

    public static int computeSizeInt64ListNoTag(List list) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int i = 0;
        for (int i2 = 0; i2 < size; i2++) {
            i += CodedOutputStream$OutputStreamEncoder.computeUInt64SizeNoTag(((Long) list.get(i2)).longValue());
        }
        return i;
    }

    public static int computeSizeMessageList(int i, List list, Schema schema) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int computeTagSize = CodedOutputStream$OutputStreamEncoder.computeTagSize(i) * size;
        for (int i2 = 0; i2 < size; i2++) {
            int serializedSize = ((AbstractMessageLite) ((MessageLite) list.get(i2))).getSerializedSize(schema);
            computeTagSize += CodedOutputStream$OutputStreamEncoder.computeUInt32SizeNoTag(serializedSize) + serializedSize;
        }
        return computeTagSize;
    }

    public static int computeSizeSInt32List(int i, List list) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return (CodedOutputStream$OutputStreamEncoder.computeTagSize(i) * size) + computeSizeSInt32ListNoTag(list);
    }

    public static int computeSizeSInt32ListNoTag(List list) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int i = 0;
        for (int i2 = 0; i2 < size; i2++) {
            int intValue = ((Integer) list.get(i2)).intValue();
            i += CodedOutputStream$OutputStreamEncoder.computeUInt32SizeNoTag((intValue >> 31) ^ (intValue << 1));
        }
        return i;
    }

    public static int computeSizeSInt64List(int i, List list) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return (CodedOutputStream$OutputStreamEncoder.computeTagSize(i) * size) + computeSizeSInt64ListNoTag(list);
    }

    public static int computeSizeSInt64ListNoTag(List list) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int i = 0;
        for (int i2 = 0; i2 < size; i2++) {
            long longValue = ((Long) list.get(i2)).longValue();
            i += CodedOutputStream$OutputStreamEncoder.computeUInt64SizeNoTag((longValue >> 63) ^ (longValue << 1));
        }
        return i;
    }

    public static int computeSizeStringList(int i, List list) {
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        int computeTagSize = CodedOutputStream$OutputStreamEncoder.computeTagSize(i) * size;
        if (list instanceof LazyStringList) {
            LazyStringList lazyStringList = (LazyStringList) list;
            while (i2 < size) {
                Object raw = lazyStringList.getRaw(i2);
                computeTagSize = (raw instanceof ByteString ? CodedOutputStream$OutputStreamEncoder.computeBytesSizeNoTag((ByteString) raw) : CodedOutputStream$OutputStreamEncoder.computeStringSizeNoTag((String) raw)) + computeTagSize;
                i2++;
            }
        } else {
            while (i2 < size) {
                Object obj = list.get(i2);
                computeTagSize = (obj instanceof ByteString ? CodedOutputStream$OutputStreamEncoder.computeBytesSizeNoTag((ByteString) obj) : CodedOutputStream$OutputStreamEncoder.computeStringSizeNoTag((String) obj)) + computeTagSize;
                i2++;
            }
        }
        return computeTagSize;
    }

    public static int computeSizeUInt32List(int i, List list) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return (CodedOutputStream$OutputStreamEncoder.computeTagSize(i) * size) + computeSizeUInt32ListNoTag(list);
    }

    public static int computeSizeUInt32ListNoTag(List list) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int i = 0;
        for (int i2 = 0; i2 < size; i2++) {
            i += CodedOutputStream$OutputStreamEncoder.computeUInt32SizeNoTag(((Integer) list.get(i2)).intValue());
        }
        return i;
    }

    public static int computeSizeUInt64List(int i, List list) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return (CodedOutputStream$OutputStreamEncoder.computeTagSize(i) * size) + computeSizeUInt64ListNoTag(list);
    }

    public static int computeSizeUInt64ListNoTag(List list) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int i = 0;
        for (int i2 = 0; i2 < size; i2++) {
            i += CodedOutputStream$OutputStreamEncoder.computeUInt64SizeNoTag(((Long) list.get(i2)).longValue());
        }
        return i;
    }

    public static UnknownFieldSetLiteSchema getUnknownFieldSetSchema(boolean z) {
        Class<?> cls;
        try {
            cls = Class.forName("androidx.datastore.preferences.protobuf.UnknownFieldSetSchema");
        } catch (Throwable unused) {
            cls = null;
        }
        if (cls == null) {
            return null;
        }
        try {
            return (UnknownFieldSetLiteSchema) cls.getConstructor(Boolean.TYPE).newInstance(Boolean.valueOf(z));
        } catch (Throwable unused2) {
            return null;
        }
    }

    public static void mergeUnknownFields(UnknownFieldSetLiteSchema unknownFieldSetLiteSchema, Object obj, Object obj2) {
        unknownFieldSetLiteSchema.getClass();
        GeneratedMessageLite generatedMessageLite = (GeneratedMessageLite) obj;
        UnknownFieldSetLite unknownFieldSetLite = generatedMessageLite.unknownFields;
        UnknownFieldSetLite unknownFieldSetLite2 = ((GeneratedMessageLite) obj2).unknownFields;
        UnknownFieldSetLite unknownFieldSetLite3 = UnknownFieldSetLite.DEFAULT_INSTANCE;
        if (!unknownFieldSetLite3.equals(unknownFieldSetLite2)) {
            if (unknownFieldSetLite3.equals(unknownFieldSetLite)) {
                int i = unknownFieldSetLite.count + unknownFieldSetLite2.count;
                int[] copyOf = Arrays.copyOf(unknownFieldSetLite.tags, i);
                System.arraycopy(unknownFieldSetLite2.tags, 0, copyOf, unknownFieldSetLite.count, unknownFieldSetLite2.count);
                Object[] copyOf2 = Arrays.copyOf(unknownFieldSetLite.objects, i);
                System.arraycopy(unknownFieldSetLite2.objects, 0, copyOf2, unknownFieldSetLite.count, unknownFieldSetLite2.count);
                unknownFieldSetLite = new UnknownFieldSetLite(i, copyOf, copyOf2, true);
            } else {
                unknownFieldSetLite.getClass();
                if (!unknownFieldSetLite2.equals(unknownFieldSetLite3)) {
                    if (!unknownFieldSetLite.isMutable) {
                        throw new UnsupportedOperationException();
                    }
                    int i2 = unknownFieldSetLite.count + unknownFieldSetLite2.count;
                    unknownFieldSetLite.ensureCapacity(i2);
                    System.arraycopy(unknownFieldSetLite2.tags, 0, unknownFieldSetLite.tags, unknownFieldSetLite.count, unknownFieldSetLite2.count);
                    System.arraycopy(unknownFieldSetLite2.objects, 0, unknownFieldSetLite.objects, unknownFieldSetLite.count, unknownFieldSetLite2.count);
                    unknownFieldSetLite.count = i2;
                }
            }
        }
        generatedMessageLite.unknownFields = unknownFieldSetLite;
    }

    public static boolean safeEquals(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    public static void writeBoolList(int i, List list, CodedOutputStreamWriter codedOutputStreamWriter, boolean z) {
        if (list == null || list.isEmpty()) {
            return;
        }
        CodedOutputStream$OutputStreamEncoder codedOutputStream$OutputStreamEncoder = codedOutputStreamWriter.output;
        int i2 = 0;
        if (!z) {
            while (i2 < list.size()) {
                codedOutputStream$OutputStreamEncoder.writeBool(i, ((Boolean) list.get(i2)).booleanValue());
                i2++;
            }
            return;
        }
        codedOutputStream$OutputStreamEncoder.writeTag(i, 2);
        int i3 = 0;
        for (int i4 = 0; i4 < list.size(); i4++) {
            ((Boolean) list.get(i4)).getClass();
            Logger logger = CodedOutputStream$OutputStreamEncoder.logger;
            i3++;
        }
        codedOutputStream$OutputStreamEncoder.writeUInt32NoTag(i3);
        while (i2 < list.size()) {
            codedOutputStream$OutputStreamEncoder.write(((Boolean) list.get(i2)).booleanValue() ? (byte) 1 : (byte) 0);
            i2++;
        }
    }

    public static void writeBytesList(int i, List list, CodedOutputStreamWriter codedOutputStreamWriter) {
        if (list == null || list.isEmpty()) {
            return;
        }
        codedOutputStreamWriter.getClass();
        for (int i2 = 0; i2 < list.size(); i2++) {
            codedOutputStreamWriter.output.writeBytes(i, (ByteString) list.get(i2));
        }
    }

    public static void writeDoubleList(int i, List list, CodedOutputStreamWriter codedOutputStreamWriter, boolean z) {
        if (list == null || list.isEmpty()) {
            return;
        }
        CodedOutputStream$OutputStreamEncoder codedOutputStream$OutputStreamEncoder = codedOutputStreamWriter.output;
        int i2 = 0;
        if (!z) {
            while (i2 < list.size()) {
                double doubleValue = ((Double) list.get(i2)).doubleValue();
                codedOutputStream$OutputStreamEncoder.getClass();
                codedOutputStream$OutputStreamEncoder.writeFixed64(Double.doubleToRawLongBits(doubleValue), i);
                i2++;
            }
            return;
        }
        codedOutputStream$OutputStreamEncoder.writeTag(i, 2);
        int i3 = 0;
        for (int i4 = 0; i4 < list.size(); i4++) {
            ((Double) list.get(i4)).getClass();
            Logger logger = CodedOutputStream$OutputStreamEncoder.logger;
            i3 += 8;
        }
        codedOutputStream$OutputStreamEncoder.writeUInt32NoTag(i3);
        while (i2 < list.size()) {
            codedOutputStream$OutputStreamEncoder.writeFixed64NoTag(Double.doubleToRawLongBits(((Double) list.get(i2)).doubleValue()));
            i2++;
        }
    }

    public static void writeEnumList(int i, List list, CodedOutputStreamWriter codedOutputStreamWriter, boolean z) {
        if (list == null || list.isEmpty()) {
            return;
        }
        CodedOutputStream$OutputStreamEncoder codedOutputStream$OutputStreamEncoder = codedOutputStreamWriter.output;
        int i2 = 0;
        if (!z) {
            while (i2 < list.size()) {
                codedOutputStream$OutputStreamEncoder.writeInt32(i, ((Integer) list.get(i2)).intValue());
                i2++;
            }
            return;
        }
        codedOutputStream$OutputStreamEncoder.writeTag(i, 2);
        int i3 = 0;
        for (int i4 = 0; i4 < list.size(); i4++) {
            i3 += CodedOutputStream$OutputStreamEncoder.computeInt32SizeNoTag(((Integer) list.get(i4)).intValue());
        }
        codedOutputStream$OutputStreamEncoder.writeUInt32NoTag(i3);
        while (i2 < list.size()) {
            codedOutputStream$OutputStreamEncoder.writeInt32NoTag(((Integer) list.get(i2)).intValue());
            i2++;
        }
    }

    public static void writeFixed32List(int i, List list, CodedOutputStreamWriter codedOutputStreamWriter, boolean z) {
        if (list == null || list.isEmpty()) {
            return;
        }
        CodedOutputStream$OutputStreamEncoder codedOutputStream$OutputStreamEncoder = codedOutputStreamWriter.output;
        int i2 = 0;
        if (!z) {
            while (i2 < list.size()) {
                codedOutputStream$OutputStreamEncoder.writeFixed32(i, ((Integer) list.get(i2)).intValue());
                i2++;
            }
            return;
        }
        codedOutputStream$OutputStreamEncoder.writeTag(i, 2);
        int i3 = 0;
        for (int i4 = 0; i4 < list.size(); i4++) {
            ((Integer) list.get(i4)).getClass();
            Logger logger = CodedOutputStream$OutputStreamEncoder.logger;
            i3 += 4;
        }
        codedOutputStream$OutputStreamEncoder.writeUInt32NoTag(i3);
        while (i2 < list.size()) {
            codedOutputStream$OutputStreamEncoder.writeFixed32NoTag(((Integer) list.get(i2)).intValue());
            i2++;
        }
    }

    public static void writeFixed64List(int i, List list, CodedOutputStreamWriter codedOutputStreamWriter, boolean z) {
        if (list == null || list.isEmpty()) {
            return;
        }
        CodedOutputStream$OutputStreamEncoder codedOutputStream$OutputStreamEncoder = codedOutputStreamWriter.output;
        int i2 = 0;
        if (!z) {
            while (i2 < list.size()) {
                codedOutputStream$OutputStreamEncoder.writeFixed64(((Long) list.get(i2)).longValue(), i);
                i2++;
            }
            return;
        }
        codedOutputStream$OutputStreamEncoder.writeTag(i, 2);
        int i3 = 0;
        for (int i4 = 0; i4 < list.size(); i4++) {
            ((Long) list.get(i4)).getClass();
            Logger logger = CodedOutputStream$OutputStreamEncoder.logger;
            i3 += 8;
        }
        codedOutputStream$OutputStreamEncoder.writeUInt32NoTag(i3);
        while (i2 < list.size()) {
            codedOutputStream$OutputStreamEncoder.writeFixed64NoTag(((Long) list.get(i2)).longValue());
            i2++;
        }
    }

    public static void writeFloatList(int i, List list, CodedOutputStreamWriter codedOutputStreamWriter, boolean z) {
        if (list == null || list.isEmpty()) {
            return;
        }
        CodedOutputStream$OutputStreamEncoder codedOutputStream$OutputStreamEncoder = codedOutputStreamWriter.output;
        int i2 = 0;
        if (!z) {
            while (i2 < list.size()) {
                float floatValue = ((Float) list.get(i2)).floatValue();
                codedOutputStream$OutputStreamEncoder.getClass();
                codedOutputStream$OutputStreamEncoder.writeFixed32(i, Float.floatToRawIntBits(floatValue));
                i2++;
            }
            return;
        }
        codedOutputStream$OutputStreamEncoder.writeTag(i, 2);
        int i3 = 0;
        for (int i4 = 0; i4 < list.size(); i4++) {
            ((Float) list.get(i4)).getClass();
            Logger logger = CodedOutputStream$OutputStreamEncoder.logger;
            i3 += 4;
        }
        codedOutputStream$OutputStreamEncoder.writeUInt32NoTag(i3);
        while (i2 < list.size()) {
            codedOutputStream$OutputStreamEncoder.writeFixed32NoTag(Float.floatToRawIntBits(((Float) list.get(i2)).floatValue()));
            i2++;
        }
    }

    public static void writeGroupList(int i, List list, CodedOutputStreamWriter codedOutputStreamWriter, Schema schema) {
        if (list == null || list.isEmpty()) {
            return;
        }
        codedOutputStreamWriter.getClass();
        for (int i2 = 0; i2 < list.size(); i2++) {
            codedOutputStreamWriter.writeGroup(i, list.get(i2), schema);
        }
    }

    public static void writeInt32List(int i, List list, CodedOutputStreamWriter codedOutputStreamWriter, boolean z) {
        if (list == null || list.isEmpty()) {
            return;
        }
        CodedOutputStream$OutputStreamEncoder codedOutputStream$OutputStreamEncoder = codedOutputStreamWriter.output;
        int i2 = 0;
        if (!z) {
            while (i2 < list.size()) {
                codedOutputStream$OutputStreamEncoder.writeInt32(i, ((Integer) list.get(i2)).intValue());
                i2++;
            }
            return;
        }
        codedOutputStream$OutputStreamEncoder.writeTag(i, 2);
        int i3 = 0;
        for (int i4 = 0; i4 < list.size(); i4++) {
            i3 += CodedOutputStream$OutputStreamEncoder.computeInt32SizeNoTag(((Integer) list.get(i4)).intValue());
        }
        codedOutputStream$OutputStreamEncoder.writeUInt32NoTag(i3);
        while (i2 < list.size()) {
            codedOutputStream$OutputStreamEncoder.writeInt32NoTag(((Integer) list.get(i2)).intValue());
            i2++;
        }
    }

    public static void writeInt64List(int i, List list, CodedOutputStreamWriter codedOutputStreamWriter, boolean z) {
        if (list == null || list.isEmpty()) {
            return;
        }
        CodedOutputStream$OutputStreamEncoder codedOutputStream$OutputStreamEncoder = codedOutputStreamWriter.output;
        int i2 = 0;
        if (!z) {
            while (i2 < list.size()) {
                codedOutputStream$OutputStreamEncoder.writeUInt64(((Long) list.get(i2)).longValue(), i);
                i2++;
            }
            return;
        }
        codedOutputStream$OutputStreamEncoder.writeTag(i, 2);
        int i3 = 0;
        for (int i4 = 0; i4 < list.size(); i4++) {
            i3 += CodedOutputStream$OutputStreamEncoder.computeUInt64SizeNoTag(((Long) list.get(i4)).longValue());
        }
        codedOutputStream$OutputStreamEncoder.writeUInt32NoTag(i3);
        while (i2 < list.size()) {
            codedOutputStream$OutputStreamEncoder.writeUInt64NoTag(((Long) list.get(i2)).longValue());
            i2++;
        }
    }

    public static void writeMessageList(int i, List list, CodedOutputStreamWriter codedOutputStreamWriter, Schema schema) {
        if (list == null || list.isEmpty()) {
            return;
        }
        codedOutputStreamWriter.getClass();
        for (int i2 = 0; i2 < list.size(); i2++) {
            codedOutputStreamWriter.output.writeMessage(i, (MessageLite) list.get(i2), schema);
        }
    }

    public static void writeSFixed32List(int i, List list, CodedOutputStreamWriter codedOutputStreamWriter, boolean z) {
        if (list == null || list.isEmpty()) {
            return;
        }
        CodedOutputStream$OutputStreamEncoder codedOutputStream$OutputStreamEncoder = codedOutputStreamWriter.output;
        int i2 = 0;
        if (!z) {
            while (i2 < list.size()) {
                codedOutputStream$OutputStreamEncoder.writeFixed32(i, ((Integer) list.get(i2)).intValue());
                i2++;
            }
            return;
        }
        codedOutputStream$OutputStreamEncoder.writeTag(i, 2);
        int i3 = 0;
        for (int i4 = 0; i4 < list.size(); i4++) {
            ((Integer) list.get(i4)).getClass();
            Logger logger = CodedOutputStream$OutputStreamEncoder.logger;
            i3 += 4;
        }
        codedOutputStream$OutputStreamEncoder.writeUInt32NoTag(i3);
        while (i2 < list.size()) {
            codedOutputStream$OutputStreamEncoder.writeFixed32NoTag(((Integer) list.get(i2)).intValue());
            i2++;
        }
    }

    public static void writeSFixed64List(int i, List list, CodedOutputStreamWriter codedOutputStreamWriter, boolean z) {
        if (list == null || list.isEmpty()) {
            return;
        }
        CodedOutputStream$OutputStreamEncoder codedOutputStream$OutputStreamEncoder = codedOutputStreamWriter.output;
        int i2 = 0;
        if (!z) {
            while (i2 < list.size()) {
                codedOutputStream$OutputStreamEncoder.writeFixed64(((Long) list.get(i2)).longValue(), i);
                i2++;
            }
            return;
        }
        codedOutputStream$OutputStreamEncoder.writeTag(i, 2);
        int i3 = 0;
        for (int i4 = 0; i4 < list.size(); i4++) {
            ((Long) list.get(i4)).getClass();
            Logger logger = CodedOutputStream$OutputStreamEncoder.logger;
            i3 += 8;
        }
        codedOutputStream$OutputStreamEncoder.writeUInt32NoTag(i3);
        while (i2 < list.size()) {
            codedOutputStream$OutputStreamEncoder.writeFixed64NoTag(((Long) list.get(i2)).longValue());
            i2++;
        }
    }

    public static void writeSInt32List(int i, List list, CodedOutputStreamWriter codedOutputStreamWriter, boolean z) {
        if (list == null || list.isEmpty()) {
            return;
        }
        CodedOutputStream$OutputStreamEncoder codedOutputStream$OutputStreamEncoder = codedOutputStreamWriter.output;
        int i2 = 0;
        if (!z) {
            while (i2 < list.size()) {
                int intValue = ((Integer) list.get(i2)).intValue();
                codedOutputStream$OutputStreamEncoder.writeUInt32(i, (intValue >> 31) ^ (intValue << 1));
                i2++;
            }
            return;
        }
        codedOutputStream$OutputStreamEncoder.writeTag(i, 2);
        int i3 = 0;
        for (int i4 = 0; i4 < list.size(); i4++) {
            int intValue2 = ((Integer) list.get(i4)).intValue();
            i3 += CodedOutputStream$OutputStreamEncoder.computeUInt32SizeNoTag((intValue2 >> 31) ^ (intValue2 << 1));
        }
        codedOutputStream$OutputStreamEncoder.writeUInt32NoTag(i3);
        while (i2 < list.size()) {
            int intValue3 = ((Integer) list.get(i2)).intValue();
            codedOutputStream$OutputStreamEncoder.writeUInt32NoTag((intValue3 >> 31) ^ (intValue3 << 1));
            i2++;
        }
    }

    public static void writeSInt64List(int i, List list, CodedOutputStreamWriter codedOutputStreamWriter, boolean z) {
        if (list == null || list.isEmpty()) {
            return;
        }
        CodedOutputStream$OutputStreamEncoder codedOutputStream$OutputStreamEncoder = codedOutputStreamWriter.output;
        int i2 = 0;
        if (!z) {
            while (i2 < list.size()) {
                long longValue = ((Long) list.get(i2)).longValue();
                codedOutputStream$OutputStreamEncoder.writeUInt64((longValue >> 63) ^ (longValue << 1), i);
                i2++;
            }
            return;
        }
        codedOutputStream$OutputStreamEncoder.writeTag(i, 2);
        int i3 = 0;
        for (int i4 = 0; i4 < list.size(); i4++) {
            long longValue2 = ((Long) list.get(i4)).longValue();
            i3 += CodedOutputStream$OutputStreamEncoder.computeUInt64SizeNoTag((longValue2 >> 63) ^ (longValue2 << 1));
        }
        codedOutputStream$OutputStreamEncoder.writeUInt32NoTag(i3);
        while (i2 < list.size()) {
            long longValue3 = ((Long) list.get(i2)).longValue();
            codedOutputStream$OutputStreamEncoder.writeUInt64NoTag((longValue3 >> 63) ^ (longValue3 << 1));
            i2++;
        }
    }

    public static void writeStringList(int i, List list, CodedOutputStreamWriter codedOutputStreamWriter) {
        if (list == null || list.isEmpty()) {
            return;
        }
        codedOutputStreamWriter.getClass();
        boolean z = list instanceof LazyStringList;
        CodedOutputStream$OutputStreamEncoder codedOutputStream$OutputStreamEncoder = codedOutputStreamWriter.output;
        int i2 = 0;
        if (!z) {
            while (i2 < list.size()) {
                codedOutputStream$OutputStreamEncoder.writeString(i, (String) list.get(i2));
                i2++;
            }
            return;
        }
        LazyStringList lazyStringList = (LazyStringList) list;
        while (i2 < list.size()) {
            Object raw = lazyStringList.getRaw(i2);
            if (raw instanceof String) {
                codedOutputStream$OutputStreamEncoder.writeString(i, (String) raw);
            } else {
                codedOutputStream$OutputStreamEncoder.writeBytes(i, (ByteString) raw);
            }
            i2++;
        }
    }

    public static void writeUInt32List(int i, List list, CodedOutputStreamWriter codedOutputStreamWriter, boolean z) {
        if (list == null || list.isEmpty()) {
            return;
        }
        CodedOutputStream$OutputStreamEncoder codedOutputStream$OutputStreamEncoder = codedOutputStreamWriter.output;
        int i2 = 0;
        if (!z) {
            while (i2 < list.size()) {
                codedOutputStream$OutputStreamEncoder.writeUInt32(i, ((Integer) list.get(i2)).intValue());
                i2++;
            }
            return;
        }
        codedOutputStream$OutputStreamEncoder.writeTag(i, 2);
        int i3 = 0;
        for (int i4 = 0; i4 < list.size(); i4++) {
            i3 += CodedOutputStream$OutputStreamEncoder.computeUInt32SizeNoTag(((Integer) list.get(i4)).intValue());
        }
        codedOutputStream$OutputStreamEncoder.writeUInt32NoTag(i3);
        while (i2 < list.size()) {
            codedOutputStream$OutputStreamEncoder.writeUInt32NoTag(((Integer) list.get(i2)).intValue());
            i2++;
        }
    }

    public static void writeUInt64List(int i, List list, CodedOutputStreamWriter codedOutputStreamWriter, boolean z) {
        if (list == null || list.isEmpty()) {
            return;
        }
        CodedOutputStream$OutputStreamEncoder codedOutputStream$OutputStreamEncoder = codedOutputStreamWriter.output;
        int i2 = 0;
        if (!z) {
            while (i2 < list.size()) {
                codedOutputStream$OutputStreamEncoder.writeUInt64(((Long) list.get(i2)).longValue(), i);
                i2++;
            }
            return;
        }
        codedOutputStream$OutputStreamEncoder.writeTag(i, 2);
        int i3 = 0;
        for (int i4 = 0; i4 < list.size(); i4++) {
            i3 += CodedOutputStream$OutputStreamEncoder.computeUInt64SizeNoTag(((Long) list.get(i4)).longValue());
        }
        codedOutputStream$OutputStreamEncoder.writeUInt32NoTag(i3);
        while (i2 < list.size()) {
            codedOutputStream$OutputStreamEncoder.writeUInt64NoTag(((Long) list.get(i2)).longValue());
            i2++;
        }
    }

    public static Object filterUnknownEnumList(Object obj, int i, List list, Object obj2, UnknownFieldSetLiteSchema unknownFieldSetLiteSchema) {
        return obj2;
    }
}
