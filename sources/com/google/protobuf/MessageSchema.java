package com.google.protobuf;

import androidx.activity.result.ActivityResultRegistry$$ExternalSyntheticOutline0;
import com.android.app.viewcapture.data.ViewNode;
import com.google.protobuf.ArrayDecoders;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.Internal;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.MapEntryLite;
import com.google.protobuf.UnsafeUtil;
import java.io.IOException;
import java.lang.reflect.Field;
import java.security.AccessController;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import sun.misc.Unsafe;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class MessageSchema implements Schema {
    public static final int[] EMPTY_INT_ARRAY = new int[0];
    public static final Unsafe UNSAFE;
    public final int[] buffer;
    public final int checkInitializedCount;
    public final MessageLite defaultInstance;
    public final ExtensionSchemaLite extensionSchema;
    public final int[] intArray;
    public final ListFieldSchema listFieldSchema;
    public final boolean lite;
    public final MapFieldSchemaLite mapFieldSchema;
    public final int maxFieldNumber;
    public final int minFieldNumber;
    public final NewInstanceSchemaLite newInstanceSchema;
    public final Object[] objects;
    public final boolean proto3;
    public final int repeatedFieldOffsetStart;
    public final UnknownFieldSetLiteSchema unknownFieldSchema;

    static {
        Unsafe unsafe;
        try {
            unsafe = (Unsafe) AccessController.doPrivileged(new UnsafeUtil.AnonymousClass1());
        } catch (Throwable unused) {
            unsafe = null;
        }
        UNSAFE = unsafe;
    }

    public MessageSchema(int[] iArr, Object[] objArr, int i, int i2, MessageLite messageLite, boolean z, int[] iArr2, int i3, int i4, NewInstanceSchemaLite newInstanceSchemaLite, ListFieldSchema listFieldSchema, UnknownFieldSetLiteSchema unknownFieldSetLiteSchema, ExtensionSchemaLite extensionSchemaLite, MapFieldSchemaLite mapFieldSchemaLite) {
        this.buffer = iArr;
        this.objects = objArr;
        this.minFieldNumber = i;
        this.maxFieldNumber = i2;
        this.lite = messageLite instanceof GeneratedMessageLite;
        this.proto3 = z;
        this.intArray = iArr2;
        this.checkInitializedCount = i3;
        this.repeatedFieldOffsetStart = i4;
        this.newInstanceSchema = newInstanceSchemaLite;
        this.listFieldSchema = listFieldSchema;
        this.unknownFieldSchema = unknownFieldSetLiteSchema;
        this.defaultInstance = messageLite;
        this.mapFieldSchema = mapFieldSchemaLite;
    }

    public static void checkMutable(Object obj) {
        if (isMutable(obj)) {
            return;
        }
        throw new IllegalArgumentException("Mutating immutable message: " + obj);
    }

    public static int decodeMapEntryValue(byte[] bArr, int i, int i2, WireFormat$FieldType wireFormat$FieldType, Class cls, ArrayDecoders.Registers registers) {
        switch (wireFormat$FieldType.ordinal()) {
            case 0:
                registers.object1 = Double.valueOf(Double.longBitsToDouble(ArrayDecoders.decodeFixed64(i, bArr)));
                return i + 8;
            case 1:
                registers.object1 = Float.valueOf(Float.intBitsToFloat(ArrayDecoders.decodeFixed32(i, bArr)));
                return i + 4;
            case 2:
            case 3:
                int decodeVarint64 = ArrayDecoders.decodeVarint64(bArr, i, registers);
                registers.object1 = Long.valueOf(registers.long1);
                return decodeVarint64;
            case 4:
            case ViewNode.TRANSLATIONY_FIELD_NUMBER /* 12 */:
            case ViewNode.SCALEX_FIELD_NUMBER /* 13 */:
                int decodeVarint32 = ArrayDecoders.decodeVarint32(bArr, i, registers);
                registers.object1 = Integer.valueOf(registers.int1);
                return decodeVarint32;
            case 5:
            case 15:
                registers.object1 = Long.valueOf(ArrayDecoders.decodeFixed64(i, bArr));
                return i + 8;
            case 6:
            case ViewNode.SCALEY_FIELD_NUMBER /* 14 */:
                registers.object1 = Integer.valueOf(ArrayDecoders.decodeFixed32(i, bArr));
                return i + 4;
            case 7:
                int decodeVarint642 = ArrayDecoders.decodeVarint64(bArr, i, registers);
                registers.object1 = Boolean.valueOf(registers.long1 != 0);
                return decodeVarint642;
            case 8:
                return ArrayDecoders.decodeStringRequireUtf8(bArr, i, registers);
            case 9:
            default:
                throw new RuntimeException("unsupported field type.");
            case 10:
                Schema schemaFor = Protobuf.INSTANCE.schemaFor(cls);
                GeneratedMessageLite newInstance = schemaFor.newInstance();
                int mergeMessageField = ArrayDecoders.mergeMessageField(newInstance, schemaFor, bArr, i, i2, registers);
                schemaFor.makeImmutable(newInstance);
                registers.object1 = newInstance;
                return mergeMessageField;
            case ViewNode.TRANSLATIONX_FIELD_NUMBER /* 11 */:
                return ArrayDecoders.decodeBytes(bArr, i, registers);
            case 16:
                int decodeVarint322 = ArrayDecoders.decodeVarint32(bArr, i, registers);
                registers.object1 = Integer.valueOf(CodedInputStream$StreamDecoder.decodeZigZag32(registers.int1));
                return decodeVarint322;
            case ViewNode.CLIPCHILDREN_FIELD_NUMBER /* 17 */:
                int decodeVarint643 = ArrayDecoders.decodeVarint64(bArr, i, registers);
                registers.object1 = Long.valueOf(CodedInputStream$StreamDecoder.decodeZigZag64(registers.long1));
                return decodeVarint643;
        }
    }

    public static UnknownFieldSetLite getMutableUnknownFields(Object obj) {
        GeneratedMessageLite generatedMessageLite = (GeneratedMessageLite) obj;
        UnknownFieldSetLite unknownFieldSetLite = generatedMessageLite.unknownFields;
        if (unknownFieldSetLite != UnknownFieldSetLite.DEFAULT_INSTANCE) {
            return unknownFieldSetLite;
        }
        UnknownFieldSetLite newInstance = UnknownFieldSetLite.newInstance();
        generatedMessageLite.unknownFields = newInstance;
        return newInstance;
    }

    public static boolean isMutable(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj instanceof GeneratedMessageLite) {
            return ((GeneratedMessageLite) obj).isMutable();
        }
        return true;
    }

    /* JADX WARN: Removed duplicated region for block: B:68:0x0278  */
    /* JADX WARN: Removed duplicated region for block: B:72:0x0294  */
    /* JADX WARN: Removed duplicated region for block: B:85:0x0297  */
    /* JADX WARN: Removed duplicated region for block: B:86:0x027e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static com.google.protobuf.MessageSchema newSchema(com.google.protobuf.RawMessageInfo r34, com.google.protobuf.NewInstanceSchemaLite r35, com.google.protobuf.ListFieldSchema r36, com.google.protobuf.UnknownFieldSetLiteSchema r37, com.google.protobuf.ExtensionSchemaLite r38, com.google.protobuf.MapFieldSchemaLite r39) {
        /*
            Method dump skipped, instructions count: 1045
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.MessageSchema.newSchema(com.google.protobuf.RawMessageInfo, com.google.protobuf.NewInstanceSchemaLite, com.google.protobuf.ListFieldSchema, com.google.protobuf.UnknownFieldSetLiteSchema, com.google.protobuf.ExtensionSchemaLite, com.google.protobuf.MapFieldSchemaLite):com.google.protobuf.MessageSchema");
    }

    public static long offset(int i) {
        return i & 1048575;
    }

    public static int oneofIntAt(long j, Object obj) {
        return ((Integer) UnsafeUtil.getObject(j, obj)).intValue();
    }

    public static long oneofLongAt(long j, Object obj) {
        return ((Long) UnsafeUtil.getObject(j, obj)).longValue();
    }

    public static Field reflectField(Class cls, String str) {
        try {
            return cls.getDeclaredField(str);
        } catch (NoSuchFieldException unused) {
            Field[] declaredFields = cls.getDeclaredFields();
            for (Field field : declaredFields) {
                if (str.equals(field.getName())) {
                    return field;
                }
            }
            StringBuilder m = ActivityResultRegistry$$ExternalSyntheticOutline0.m("Field ", str, " for ");
            m.append(cls.getName());
            m.append(" not found. Known fields are ");
            m.append(Arrays.toString(declaredFields));
            throw new RuntimeException(m.toString());
        }
    }

    public static int type(int i) {
        return (i & 267386880) >>> 20;
    }

    public static void writeString(int i, Object obj, CodedOutputStreamWriter codedOutputStreamWriter) {
        if (!(obj instanceof String)) {
            codedOutputStreamWriter.writeBytes(i, (ByteString) obj);
        } else {
            codedOutputStreamWriter.output.writeString(i, (String) obj);
        }
    }

    public final boolean arePresentForEquals(GeneratedMessageLite generatedMessageLite, GeneratedMessageLite generatedMessageLite2, int i) {
        return isFieldPresent(i, generatedMessageLite) == isFieldPresent(i, generatedMessageLite2);
    }

    /* JADX WARN: Code restructure failed: missing block: B:16:0x006c, code lost:
    
        if (com.google.protobuf.SchemaUtil.safeEquals(com.google.protobuf.UnsafeUtil.getObject(r7, r12), com.google.protobuf.UnsafeUtil.getObject(r7, r13)) != false) goto L105;
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x0080, code lost:
    
        if (com.google.protobuf.UnsafeUtil.getLong(r7, r12) == com.google.protobuf.UnsafeUtil.getLong(r7, r13)) goto L105;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x0092, code lost:
    
        if (com.google.protobuf.UnsafeUtil.getInt(r7, r12) == com.google.protobuf.UnsafeUtil.getInt(r7, r13)) goto L105;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x00a6, code lost:
    
        if (com.google.protobuf.UnsafeUtil.getLong(r7, r12) == com.google.protobuf.UnsafeUtil.getLong(r7, r13)) goto L105;
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x00b8, code lost:
    
        if (com.google.protobuf.UnsafeUtil.getInt(r7, r12) == com.google.protobuf.UnsafeUtil.getInt(r7, r13)) goto L105;
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x00ca, code lost:
    
        if (com.google.protobuf.UnsafeUtil.getInt(r7, r12) == com.google.protobuf.UnsafeUtil.getInt(r7, r13)) goto L105;
     */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x00dc, code lost:
    
        if (com.google.protobuf.UnsafeUtil.getInt(r7, r12) == com.google.protobuf.UnsafeUtil.getInt(r7, r13)) goto L105;
     */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x00f2, code lost:
    
        if (com.google.protobuf.SchemaUtil.safeEquals(com.google.protobuf.UnsafeUtil.getObject(r7, r12), com.google.protobuf.UnsafeUtil.getObject(r7, r13)) != false) goto L105;
     */
    /* JADX WARN: Code restructure failed: missing block: B:48:0x0108, code lost:
    
        if (com.google.protobuf.SchemaUtil.safeEquals(com.google.protobuf.UnsafeUtil.getObject(r7, r12), com.google.protobuf.UnsafeUtil.getObject(r7, r13)) != false) goto L105;
     */
    /* JADX WARN: Code restructure failed: missing block: B:52:0x011e, code lost:
    
        if (com.google.protobuf.SchemaUtil.safeEquals(com.google.protobuf.UnsafeUtil.getObject(r7, r12), com.google.protobuf.UnsafeUtil.getObject(r7, r13)) != false) goto L105;
     */
    /* JADX WARN: Code restructure failed: missing block: B:56:0x0132, code lost:
    
        if (r5.getBoolean(r7, r12) == r5.getBoolean(r7, r13)) goto L105;
     */
    /* JADX WARN: Code restructure failed: missing block: B:60:0x0144, code lost:
    
        if (com.google.protobuf.UnsafeUtil.getInt(r7, r12) == com.google.protobuf.UnsafeUtil.getInt(r7, r13)) goto L105;
     */
    /* JADX WARN: Code restructure failed: missing block: B:64:0x0158, code lost:
    
        if (com.google.protobuf.UnsafeUtil.getLong(r7, r12) == com.google.protobuf.UnsafeUtil.getLong(r7, r13)) goto L105;
     */
    /* JADX WARN: Code restructure failed: missing block: B:68:0x016a, code lost:
    
        if (com.google.protobuf.UnsafeUtil.getInt(r7, r12) == com.google.protobuf.UnsafeUtil.getInt(r7, r13)) goto L105;
     */
    /* JADX WARN: Code restructure failed: missing block: B:72:0x017d, code lost:
    
        if (com.google.protobuf.UnsafeUtil.getLong(r7, r12) == com.google.protobuf.UnsafeUtil.getLong(r7, r13)) goto L105;
     */
    /* JADX WARN: Code restructure failed: missing block: B:76:0x0190, code lost:
    
        if (com.google.protobuf.UnsafeUtil.getLong(r7, r12) == com.google.protobuf.UnsafeUtil.getLong(r7, r13)) goto L105;
     */
    /* JADX WARN: Code restructure failed: missing block: B:80:0x01ab, code lost:
    
        if (java.lang.Float.floatToIntBits(r5.getFloat(r7, r12)) == java.lang.Float.floatToIntBits(r5.getFloat(r7, r13))) goto L105;
     */
    /* JADX WARN: Code restructure failed: missing block: B:84:0x01c8, code lost:
    
        if (java.lang.Double.doubleToLongBits(r5.getDouble(r7, r12)) == java.lang.Double.doubleToLongBits(r5.getDouble(r7, r13))) goto L105;
     */
    /* JADX WARN: Code restructure failed: missing block: B:9:0x0037, code lost:
    
        if (com.google.protobuf.SchemaUtil.safeEquals(com.google.protobuf.UnsafeUtil.getObject(r7, r12), com.google.protobuf.UnsafeUtil.getObject(r7, r13)) != false) goto L105;
     */
    @Override // com.google.protobuf.Schema
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean equals(com.google.protobuf.GeneratedMessageLite r12, com.google.protobuf.GeneratedMessageLite r13) {
        /*
            Method dump skipped, instructions count: 624
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.MessageSchema.equals(com.google.protobuf.GeneratedMessageLite, com.google.protobuf.GeneratedMessageLite):boolean");
    }

    public final Object filterMapUnknownEnumValues(Object obj, int i, Object obj2, UnknownFieldSetLiteSchema unknownFieldSetLiteSchema, Object obj3) {
        int i2 = this.buffer[i];
        Object object = UnsafeUtil.getObject(typeAndOffsetAt(i) & 1048575, obj);
        if (object == null) {
            return obj2;
        }
        Internal.EnumVerifier enumFieldVerifier = getEnumFieldVerifier(i);
        if (enumFieldVerifier == null) {
            return obj2;
        }
        this.mapFieldSchema.getClass();
        MapEntryLite.Metadata metadata = ((MapEntryLite) getMapFieldDefaultEntry(i)).metadata;
        Iterator it = ((MapFieldLite) object).entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            if (!enumFieldVerifier.isInRange(((Integer) entry.getValue()).intValue())) {
                if (obj2 == null) {
                    unknownFieldSetLiteSchema.getClass();
                    obj2 = UnknownFieldSetLiteSchema.getBuilderFromMessage(obj3);
                }
                int computeSerializedSize = MapEntryLite.computeSerializedSize(metadata, entry.getKey(), entry.getValue());
                byte[] bArr = new byte[computeSerializedSize];
                CodedOutputStream.ArrayEncoder arrayEncoder = new CodedOutputStream.ArrayEncoder(computeSerializedSize, bArr);
                try {
                    Object key = entry.getKey();
                    Object value = entry.getValue();
                    FieldSet.writeElement(arrayEncoder, metadata.keyType, 1, key);
                    FieldSet.writeElement(arrayEncoder, metadata.valueType, 2, value);
                    if (arrayEncoder.spaceLeft() != 0) {
                        throw new IllegalStateException("Did not write as much data as expected.");
                    }
                    ByteString.LiteralByteString literalByteString = new ByteString.LiteralByteString(bArr);
                    unknownFieldSetLiteSchema.getClass();
                    ((UnknownFieldSetLite) obj2).storeField((i2 << 3) | 2, literalByteString);
                    it.remove();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return obj2;
    }

    public final Internal.EnumVerifier getEnumFieldVerifier(int i) {
        return (Internal.EnumVerifier) this.objects[((i / 3) * 2) + 1];
    }

    public final Object getMapFieldDefaultEntry(int i) {
        return this.objects[(i / 3) * 2];
    }

    public final Schema getMessageFieldSchema(int i) {
        int i2 = (i / 3) * 2;
        Object[] objArr = this.objects;
        Schema schema = (Schema) objArr[i2];
        if (schema != null) {
            return schema;
        }
        Schema schemaFor = Protobuf.INSTANCE.schemaFor((Class) objArr[i2 + 1]);
        objArr[i2] = schemaFor;
        return schemaFor;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    @Override // com.google.protobuf.Schema
    public final int getSerializedSize(GeneratedMessageLite generatedMessageLite) {
        int i;
        int i2;
        int i3;
        char c;
        int computeTagSize;
        int computeUInt64SizeNoTag;
        int i4;
        int computeBytesSize;
        int computeTagSize2;
        int computeUInt32SizeNoTag;
        int computeGroupSize;
        int computeTagSize3;
        int i5;
        char c2;
        char c3;
        int computeTagSize4;
        int computeUInt64SizeNoTag2;
        int computeTagSize5;
        int computeInt32SizeNoTag;
        int computeTagSize6;
        int computeUInt64SizeNoTag3;
        int computeTagSize7;
        int computeInt32SizeNoTag2;
        int computeFixed64Size;
        int computeTagSize8;
        int computeStringSizeNoTag;
        int computeBytesSize2;
        int i6;
        int computeTagSize9;
        int computeUInt64SizeNoTag4;
        int[] iArr = this.buffer;
        boolean z = this.proto3;
        UnknownFieldSetLiteSchema unknownFieldSetLiteSchema = this.unknownFieldSchema;
        MapFieldSchemaLite mapFieldSchemaLite = this.mapFieldSchema;
        int i7 = 1;
        int i8 = 1048575;
        if (z) {
            Unsafe unsafe = UNSAFE;
            int i9 = 0;
            int i10 = 0;
            while (i9 < iArr.length) {
                int typeAndOffsetAt = typeAndOffsetAt(i9);
                int type = type(typeAndOffsetAt);
                int i11 = iArr[i9];
                long j = typeAndOffsetAt & i8;
                if (type >= FieldType.DOUBLE_LIST_PACKED.id() && type <= FieldType.SINT64_LIST_PACKED.id()) {
                    int i12 = iArr[i9 + 2];
                }
                switch (type) {
                    case 0:
                        if (isFieldPresent(i9, generatedMessageLite)) {
                            i10 = MessageSchema$$ExternalSyntheticOutline0.m(i11, 8, i10);
                            break;
                        } else {
                            break;
                        }
                    case 1:
                        if (isFieldPresent(i9, generatedMessageLite)) {
                            i10 = MessageSchema$$ExternalSyntheticOutline0.m(i11, 4, i10);
                            break;
                        } else {
                            break;
                        }
                    case 2:
                        if (isFieldPresent(i9, generatedMessageLite)) {
                            long j2 = UnsafeUtil.getLong(j, generatedMessageLite);
                            computeTagSize6 = CodedOutputStream.computeTagSize(i11);
                            computeUInt64SizeNoTag3 = CodedOutputStream.computeUInt64SizeNoTag(j2);
                            computeFixed64Size = computeUInt64SizeNoTag3 + computeTagSize6;
                            i10 += computeFixed64Size;
                            break;
                        } else {
                            break;
                        }
                    case 3:
                        if (isFieldPresent(i9, generatedMessageLite)) {
                            long j3 = UnsafeUtil.getLong(j, generatedMessageLite);
                            computeTagSize6 = CodedOutputStream.computeTagSize(i11);
                            computeUInt64SizeNoTag3 = CodedOutputStream.computeUInt64SizeNoTag(j3);
                            computeFixed64Size = computeUInt64SizeNoTag3 + computeTagSize6;
                            i10 += computeFixed64Size;
                            break;
                        } else {
                            break;
                        }
                    case 4:
                        if (isFieldPresent(i9, generatedMessageLite)) {
                            int i13 = UnsafeUtil.getInt(j, generatedMessageLite);
                            computeTagSize7 = CodedOutputStream.computeTagSize(i11);
                            computeInt32SizeNoTag2 = CodedOutputStream.computeInt32SizeNoTag(i13);
                            computeFixed64Size = computeInt32SizeNoTag2 + computeTagSize7;
                            i10 += computeFixed64Size;
                            break;
                        } else {
                            break;
                        }
                    case 5:
                        if (isFieldPresent(i9, generatedMessageLite)) {
                            computeFixed64Size = CodedOutputStream.computeFixed64Size(i11);
                            i10 += computeFixed64Size;
                            break;
                        } else {
                            break;
                        }
                    case 6:
                        if (isFieldPresent(i9, generatedMessageLite)) {
                            computeFixed64Size = CodedOutputStream.computeFixed32Size(i11);
                            i10 += computeFixed64Size;
                            break;
                        } else {
                            break;
                        }
                    case 7:
                        if (isFieldPresent(i9, generatedMessageLite)) {
                            i10 = MessageSchema$$ExternalSyntheticOutline0.m(i11, 1, i10);
                            break;
                        } else {
                            break;
                        }
                    case 8:
                        if (!isFieldPresent(i9, generatedMessageLite)) {
                            break;
                        } else {
                            Object object = UnsafeUtil.getObject(j, generatedMessageLite);
                            if (object instanceof ByteString) {
                                computeBytesSize2 = CodedOutputStream.computeBytesSize(i11, (ByteString) object);
                                i10 = computeBytesSize2 + i10;
                                break;
                            } else {
                                computeTagSize8 = CodedOutputStream.computeTagSize(i11);
                                computeStringSizeNoTag = CodedOutputStream.computeStringSizeNoTag((String) object);
                                computeBytesSize2 = computeStringSizeNoTag + computeTagSize8;
                                i10 = computeBytesSize2 + i10;
                            }
                        }
                    case 9:
                        if (isFieldPresent(i9, generatedMessageLite)) {
                            Object object2 = UnsafeUtil.getObject(j, generatedMessageLite);
                            Schema messageFieldSchema = getMessageFieldSchema(i9);
                            Class cls = SchemaUtil.GENERATED_MESSAGE_CLASS;
                            int computeTagSize10 = CodedOutputStream.computeTagSize(i11);
                            int serializedSize = ((AbstractMessageLite) ((MessageLite) object2)).getSerializedSize(messageFieldSchema);
                            i10 = MapFieldSchemaLite$$ExternalSyntheticOutline0.m(serializedSize, serializedSize, computeTagSize10, i10);
                            break;
                        } else {
                            break;
                        }
                    case 10:
                        if (isFieldPresent(i9, generatedMessageLite)) {
                            computeFixed64Size = CodedOutputStream.computeBytesSize(i11, (ByteString) UnsafeUtil.getObject(j, generatedMessageLite));
                            i10 += computeFixed64Size;
                            break;
                        } else {
                            break;
                        }
                    case ViewNode.TRANSLATIONX_FIELD_NUMBER /* 11 */:
                        if (isFieldPresent(i9, generatedMessageLite)) {
                            int i14 = UnsafeUtil.getInt(j, generatedMessageLite);
                            computeTagSize7 = CodedOutputStream.computeTagSize(i11);
                            computeInt32SizeNoTag2 = CodedOutputStream.computeUInt32SizeNoTag(i14);
                            computeFixed64Size = computeInt32SizeNoTag2 + computeTagSize7;
                            i10 += computeFixed64Size;
                            break;
                        } else {
                            break;
                        }
                    case ViewNode.TRANSLATIONY_FIELD_NUMBER /* 12 */:
                        if (isFieldPresent(i9, generatedMessageLite)) {
                            int i15 = UnsafeUtil.getInt(j, generatedMessageLite);
                            computeTagSize7 = CodedOutputStream.computeTagSize(i11);
                            computeInt32SizeNoTag2 = CodedOutputStream.computeInt32SizeNoTag(i15);
                            computeFixed64Size = computeInt32SizeNoTag2 + computeTagSize7;
                            i10 += computeFixed64Size;
                            break;
                        } else {
                            break;
                        }
                    case ViewNode.SCALEX_FIELD_NUMBER /* 13 */:
                        if (isFieldPresent(i9, generatedMessageLite)) {
                            i10 = MessageSchema$$ExternalSyntheticOutline0.m(i11, 4, i10);
                            break;
                        } else {
                            break;
                        }
                    case ViewNode.SCALEY_FIELD_NUMBER /* 14 */:
                        if (isFieldPresent(i9, generatedMessageLite)) {
                            i10 = MessageSchema$$ExternalSyntheticOutline0.m(i11, 8, i10);
                            break;
                        } else {
                            break;
                        }
                    case 15:
                        if (isFieldPresent(i9, generatedMessageLite)) {
                            int i16 = UnsafeUtil.getInt(j, generatedMessageLite);
                            computeTagSize7 = CodedOutputStream.computeTagSize(i11);
                            computeInt32SizeNoTag2 = CodedOutputStream.computeUInt32SizeNoTag((i16 >> 31) ^ (i16 << 1));
                            computeFixed64Size = computeInt32SizeNoTag2 + computeTagSize7;
                            i10 += computeFixed64Size;
                            break;
                        } else {
                            break;
                        }
                    case 16:
                        if (isFieldPresent(i9, generatedMessageLite)) {
                            long j4 = UnsafeUtil.getLong(j, generatedMessageLite);
                            computeTagSize6 = CodedOutputStream.computeTagSize(i11);
                            computeUInt64SizeNoTag3 = CodedOutputStream.computeUInt64SizeNoTag((j4 >> 63) ^ (j4 << 1));
                            computeFixed64Size = computeUInt64SizeNoTag3 + computeTagSize6;
                            i10 += computeFixed64Size;
                            break;
                        } else {
                            break;
                        }
                    case ViewNode.CLIPCHILDREN_FIELD_NUMBER /* 17 */:
                        if (isFieldPresent(i9, generatedMessageLite)) {
                            computeFixed64Size = CodedOutputStream.computeGroupSize(i11, (MessageLite) UnsafeUtil.getObject(j, generatedMessageLite), getMessageFieldSchema(i9));
                            i10 += computeFixed64Size;
                            break;
                        } else {
                            break;
                        }
                    case ViewNode.VISIBILITY_FIELD_NUMBER /* 18 */:
                        computeFixed64Size = SchemaUtil.computeSizeFixed64List(i11, (List) UnsafeUtil.getObject(j, generatedMessageLite));
                        i10 += computeFixed64Size;
                        break;
                    case ViewNode.ELEVATION_FIELD_NUMBER /* 19 */:
                        computeFixed64Size = SchemaUtil.computeSizeFixed32List(i11, (List) UnsafeUtil.getObject(j, generatedMessageLite));
                        i10 += computeFixed64Size;
                        break;
                    case 20:
                        computeFixed64Size = SchemaUtil.computeSizeInt64List(i11, (List) UnsafeUtil.getObject(j, generatedMessageLite));
                        i10 += computeFixed64Size;
                        break;
                    case 21:
                        computeFixed64Size = SchemaUtil.computeSizeUInt64List(i11, (List) UnsafeUtil.getObject(j, generatedMessageLite));
                        i10 += computeFixed64Size;
                        break;
                    case 22:
                        computeFixed64Size = SchemaUtil.computeSizeInt32List(i11, (List) UnsafeUtil.getObject(j, generatedMessageLite));
                        i10 += computeFixed64Size;
                        break;
                    case 23:
                        computeFixed64Size = SchemaUtil.computeSizeFixed64List(i11, (List) UnsafeUtil.getObject(j, generatedMessageLite));
                        i10 += computeFixed64Size;
                        break;
                    case 24:
                        computeFixed64Size = SchemaUtil.computeSizeFixed32List(i11, (List) UnsafeUtil.getObject(j, generatedMessageLite));
                        i10 += computeFixed64Size;
                        break;
                    case 25:
                        List list = (List) UnsafeUtil.getObject(j, generatedMessageLite);
                        Class cls2 = SchemaUtil.GENERATED_MESSAGE_CLASS;
                        int size = list.size();
                        i10 += size == 0 ? 0 : (CodedOutputStream.computeTagSize(i11) + 1) * size;
                        break;
                    case 26:
                        computeFixed64Size = SchemaUtil.computeSizeStringList(i11, (List) UnsafeUtil.getObject(j, generatedMessageLite));
                        i10 += computeFixed64Size;
                        break;
                    case 27:
                        computeFixed64Size = SchemaUtil.computeSizeMessageList(i11, (List) UnsafeUtil.getObject(j, generatedMessageLite), getMessageFieldSchema(i9));
                        i10 += computeFixed64Size;
                        break;
                    case 28:
                        computeFixed64Size = SchemaUtil.computeSizeByteStringList(i11, (List) UnsafeUtil.getObject(j, generatedMessageLite));
                        i10 += computeFixed64Size;
                        break;
                    case 29:
                        computeFixed64Size = SchemaUtil.computeSizeUInt32List(i11, (List) UnsafeUtil.getObject(j, generatedMessageLite));
                        i10 += computeFixed64Size;
                        break;
                    case 30:
                        computeFixed64Size = SchemaUtil.computeSizeEnumList(i11, (List) UnsafeUtil.getObject(j, generatedMessageLite));
                        i10 += computeFixed64Size;
                        break;
                    case 31:
                        computeFixed64Size = SchemaUtil.computeSizeFixed32List(i11, (List) UnsafeUtil.getObject(j, generatedMessageLite));
                        i10 += computeFixed64Size;
                        break;
                    case 32:
                        computeFixed64Size = SchemaUtil.computeSizeFixed64List(i11, (List) UnsafeUtil.getObject(j, generatedMessageLite));
                        i10 += computeFixed64Size;
                        break;
                    case 33:
                        computeFixed64Size = SchemaUtil.computeSizeSInt32List(i11, (List) UnsafeUtil.getObject(j, generatedMessageLite));
                        i10 += computeFixed64Size;
                        break;
                    case 34:
                        computeFixed64Size = SchemaUtil.computeSizeSInt64List(i11, (List) UnsafeUtil.getObject(j, generatedMessageLite));
                        i10 += computeFixed64Size;
                        break;
                    case 35:
                        int computeSizeFixed64ListNoTag = SchemaUtil.computeSizeFixed64ListNoTag((List) unsafe.getObject(generatedMessageLite, j));
                        if (computeSizeFixed64ListNoTag > 0) {
                            i10 = MapFieldSchemaLite$$ExternalSyntheticOutline0.m(computeSizeFixed64ListNoTag, CodedOutputStream.computeTagSize(i11), computeSizeFixed64ListNoTag, i10);
                            break;
                        } else {
                            break;
                        }
                    case 36:
                        int computeSizeFixed32ListNoTag = SchemaUtil.computeSizeFixed32ListNoTag((List) unsafe.getObject(generatedMessageLite, j));
                        if (computeSizeFixed32ListNoTag > 0) {
                            i10 = MapFieldSchemaLite$$ExternalSyntheticOutline0.m(computeSizeFixed32ListNoTag, CodedOutputStream.computeTagSize(i11), computeSizeFixed32ListNoTag, i10);
                            break;
                        } else {
                            break;
                        }
                    case 37:
                        int computeSizeInt64ListNoTag = SchemaUtil.computeSizeInt64ListNoTag((List) unsafe.getObject(generatedMessageLite, j));
                        if (computeSizeInt64ListNoTag > 0) {
                            i10 = MapFieldSchemaLite$$ExternalSyntheticOutline0.m(computeSizeInt64ListNoTag, CodedOutputStream.computeTagSize(i11), computeSizeInt64ListNoTag, i10);
                            break;
                        } else {
                            break;
                        }
                    case 38:
                        int computeSizeUInt64ListNoTag = SchemaUtil.computeSizeUInt64ListNoTag((List) unsafe.getObject(generatedMessageLite, j));
                        if (computeSizeUInt64ListNoTag > 0) {
                            i10 = MapFieldSchemaLite$$ExternalSyntheticOutline0.m(computeSizeUInt64ListNoTag, CodedOutputStream.computeTagSize(i11), computeSizeUInt64ListNoTag, i10);
                            break;
                        } else {
                            break;
                        }
                    case 39:
                        int computeSizeInt32ListNoTag = SchemaUtil.computeSizeInt32ListNoTag((List) unsafe.getObject(generatedMessageLite, j));
                        if (computeSizeInt32ListNoTag > 0) {
                            i10 = MapFieldSchemaLite$$ExternalSyntheticOutline0.m(computeSizeInt32ListNoTag, CodedOutputStream.computeTagSize(i11), computeSizeInt32ListNoTag, i10);
                            break;
                        } else {
                            break;
                        }
                    case 40:
                        int computeSizeFixed64ListNoTag2 = SchemaUtil.computeSizeFixed64ListNoTag((List) unsafe.getObject(generatedMessageLite, j));
                        if (computeSizeFixed64ListNoTag2 > 0) {
                            i10 = MapFieldSchemaLite$$ExternalSyntheticOutline0.m(computeSizeFixed64ListNoTag2, CodedOutputStream.computeTagSize(i11), computeSizeFixed64ListNoTag2, i10);
                            break;
                        } else {
                            break;
                        }
                    case 41:
                        int computeSizeFixed32ListNoTag2 = SchemaUtil.computeSizeFixed32ListNoTag((List) unsafe.getObject(generatedMessageLite, j));
                        if (computeSizeFixed32ListNoTag2 > 0) {
                            i10 = MapFieldSchemaLite$$ExternalSyntheticOutline0.m(computeSizeFixed32ListNoTag2, CodedOutputStream.computeTagSize(i11), computeSizeFixed32ListNoTag2, i10);
                            break;
                        } else {
                            break;
                        }
                    case 42:
                        List list2 = (List) unsafe.getObject(generatedMessageLite, j);
                        Class cls3 = SchemaUtil.GENERATED_MESSAGE_CLASS;
                        int size2 = list2.size();
                        if (size2 > 0) {
                            i10 = MapFieldSchemaLite$$ExternalSyntheticOutline0.m(size2, CodedOutputStream.computeTagSize(i11), size2, i10);
                            break;
                        } else {
                            break;
                        }
                    case 43:
                        int computeSizeUInt32ListNoTag = SchemaUtil.computeSizeUInt32ListNoTag((List) unsafe.getObject(generatedMessageLite, j));
                        if (computeSizeUInt32ListNoTag > 0) {
                            i10 = MapFieldSchemaLite$$ExternalSyntheticOutline0.m(computeSizeUInt32ListNoTag, CodedOutputStream.computeTagSize(i11), computeSizeUInt32ListNoTag, i10);
                            break;
                        } else {
                            break;
                        }
                    case 44:
                        int computeSizeEnumListNoTag = SchemaUtil.computeSizeEnumListNoTag((List) unsafe.getObject(generatedMessageLite, j));
                        if (computeSizeEnumListNoTag > 0) {
                            i10 = MapFieldSchemaLite$$ExternalSyntheticOutline0.m(computeSizeEnumListNoTag, CodedOutputStream.computeTagSize(i11), computeSizeEnumListNoTag, i10);
                            break;
                        } else {
                            break;
                        }
                    case 45:
                        int computeSizeFixed32ListNoTag3 = SchemaUtil.computeSizeFixed32ListNoTag((List) unsafe.getObject(generatedMessageLite, j));
                        if (computeSizeFixed32ListNoTag3 > 0) {
                            i10 = MapFieldSchemaLite$$ExternalSyntheticOutline0.m(computeSizeFixed32ListNoTag3, CodedOutputStream.computeTagSize(i11), computeSizeFixed32ListNoTag3, i10);
                            break;
                        } else {
                            break;
                        }
                    case 46:
                        int computeSizeFixed64ListNoTag3 = SchemaUtil.computeSizeFixed64ListNoTag((List) unsafe.getObject(generatedMessageLite, j));
                        if (computeSizeFixed64ListNoTag3 > 0) {
                            i10 = MapFieldSchemaLite$$ExternalSyntheticOutline0.m(computeSizeFixed64ListNoTag3, CodedOutputStream.computeTagSize(i11), computeSizeFixed64ListNoTag3, i10);
                            break;
                        } else {
                            break;
                        }
                    case 47:
                        int computeSizeSInt32ListNoTag = SchemaUtil.computeSizeSInt32ListNoTag((List) unsafe.getObject(generatedMessageLite, j));
                        if (computeSizeSInt32ListNoTag > 0) {
                            i10 = MapFieldSchemaLite$$ExternalSyntheticOutline0.m(computeSizeSInt32ListNoTag, CodedOutputStream.computeTagSize(i11), computeSizeSInt32ListNoTag, i10);
                            break;
                        } else {
                            break;
                        }
                    case 48:
                        int computeSizeSInt64ListNoTag = SchemaUtil.computeSizeSInt64ListNoTag((List) unsafe.getObject(generatedMessageLite, j));
                        if (computeSizeSInt64ListNoTag > 0) {
                            i10 = MapFieldSchemaLite$$ExternalSyntheticOutline0.m(computeSizeSInt64ListNoTag, CodedOutputStream.computeTagSize(i11), computeSizeSInt64ListNoTag, i10);
                            break;
                        } else {
                            break;
                        }
                    case 49:
                        List list3 = (List) UnsafeUtil.getObject(j, generatedMessageLite);
                        Schema messageFieldSchema2 = getMessageFieldSchema(i9);
                        Class cls4 = SchemaUtil.GENERATED_MESSAGE_CLASS;
                        int size3 = list3.size();
                        if (size3 == 0) {
                            i6 = 0;
                        } else {
                            i6 = 0;
                            for (int i17 = 0; i17 < size3; i17++) {
                                i6 = CodedOutputStream.computeGroupSize(i11, (MessageLite) list3.get(i17), messageFieldSchema2) + i6;
                            }
                        }
                        i10 = i6 + i10;
                        break;
                    case 50:
                        Object object3 = UnsafeUtil.getObject(j, generatedMessageLite);
                        Object mapFieldDefaultEntry = getMapFieldDefaultEntry(i9);
                        mapFieldSchemaLite.getClass();
                        computeFixed64Size = MapFieldSchemaLite.getSerializedSize(i11, object3, mapFieldDefaultEntry);
                        i10 += computeFixed64Size;
                        break;
                    case 51:
                        if (isOneofPresent(i11, i9, generatedMessageLite)) {
                            i10 = MessageSchema$$ExternalSyntheticOutline0.m(i11, 8, i10);
                            break;
                        } else {
                            break;
                        }
                    case 52:
                        if (isOneofPresent(i11, i9, generatedMessageLite)) {
                            i10 = MessageSchema$$ExternalSyntheticOutline0.m(i11, 4, i10);
                            break;
                        } else {
                            break;
                        }
                    case 53:
                        if (isOneofPresent(i11, i9, generatedMessageLite)) {
                            long oneofLongAt = oneofLongAt(j, generatedMessageLite);
                            computeTagSize9 = CodedOutputStream.computeTagSize(i11);
                            computeUInt64SizeNoTag4 = CodedOutputStream.computeUInt64SizeNoTag(oneofLongAt);
                            computeFixed64Size = computeUInt64SizeNoTag4 + computeTagSize9;
                            i10 += computeFixed64Size;
                            break;
                        } else {
                            break;
                        }
                    case 54:
                        if (isOneofPresent(i11, i9, generatedMessageLite)) {
                            long oneofLongAt2 = oneofLongAt(j, generatedMessageLite);
                            computeTagSize9 = CodedOutputStream.computeTagSize(i11);
                            computeUInt64SizeNoTag4 = CodedOutputStream.computeUInt64SizeNoTag(oneofLongAt2);
                            computeFixed64Size = computeUInt64SizeNoTag4 + computeTagSize9;
                            i10 += computeFixed64Size;
                            break;
                        } else {
                            break;
                        }
                    case 55:
                        if (isOneofPresent(i11, i9, generatedMessageLite)) {
                            int oneofIntAt = oneofIntAt(j, generatedMessageLite);
                            computeTagSize7 = CodedOutputStream.computeTagSize(i11);
                            computeInt32SizeNoTag2 = CodedOutputStream.computeInt32SizeNoTag(oneofIntAt);
                            computeFixed64Size = computeInt32SizeNoTag2 + computeTagSize7;
                            i10 += computeFixed64Size;
                            break;
                        } else {
                            break;
                        }
                    case 56:
                        if (isOneofPresent(i11, i9, generatedMessageLite)) {
                            computeFixed64Size = CodedOutputStream.computeFixed64Size(i11);
                            i10 += computeFixed64Size;
                            break;
                        } else {
                            break;
                        }
                    case 57:
                        if (isOneofPresent(i11, i9, generatedMessageLite)) {
                            computeFixed64Size = CodedOutputStream.computeFixed32Size(i11);
                            i10 += computeFixed64Size;
                            break;
                        } else {
                            break;
                        }
                    case 58:
                        if (isOneofPresent(i11, i9, generatedMessageLite)) {
                            i10 = MessageSchema$$ExternalSyntheticOutline0.m(i11, 1, i10);
                            break;
                        } else {
                            break;
                        }
                    case 59:
                        if (!isOneofPresent(i11, i9, generatedMessageLite)) {
                            break;
                        } else {
                            Object object4 = UnsafeUtil.getObject(j, generatedMessageLite);
                            if (object4 instanceof ByteString) {
                                computeBytesSize2 = CodedOutputStream.computeBytesSize(i11, (ByteString) object4);
                                i10 = computeBytesSize2 + i10;
                                break;
                            } else {
                                computeTagSize8 = CodedOutputStream.computeTagSize(i11);
                                computeStringSizeNoTag = CodedOutputStream.computeStringSizeNoTag((String) object4);
                                computeBytesSize2 = computeStringSizeNoTag + computeTagSize8;
                                i10 = computeBytesSize2 + i10;
                            }
                        }
                    case 60:
                        if (isOneofPresent(i11, i9, generatedMessageLite)) {
                            Object object5 = UnsafeUtil.getObject(j, generatedMessageLite);
                            Schema messageFieldSchema3 = getMessageFieldSchema(i9);
                            Class cls5 = SchemaUtil.GENERATED_MESSAGE_CLASS;
                            int computeTagSize11 = CodedOutputStream.computeTagSize(i11);
                            int serializedSize2 = ((AbstractMessageLite) ((MessageLite) object5)).getSerializedSize(messageFieldSchema3);
                            i10 = MapFieldSchemaLite$$ExternalSyntheticOutline0.m(serializedSize2, serializedSize2, computeTagSize11, i10);
                            break;
                        } else {
                            break;
                        }
                    case 61:
                        if (isOneofPresent(i11, i9, generatedMessageLite)) {
                            computeFixed64Size = CodedOutputStream.computeBytesSize(i11, (ByteString) UnsafeUtil.getObject(j, generatedMessageLite));
                            i10 += computeFixed64Size;
                            break;
                        } else {
                            break;
                        }
                    case 62:
                        if (isOneofPresent(i11, i9, generatedMessageLite)) {
                            int oneofIntAt2 = oneofIntAt(j, generatedMessageLite);
                            computeTagSize7 = CodedOutputStream.computeTagSize(i11);
                            computeInt32SizeNoTag2 = CodedOutputStream.computeUInt32SizeNoTag(oneofIntAt2);
                            computeFixed64Size = computeInt32SizeNoTag2 + computeTagSize7;
                            i10 += computeFixed64Size;
                            break;
                        } else {
                            break;
                        }
                    case 63:
                        if (isOneofPresent(i11, i9, generatedMessageLite)) {
                            int oneofIntAt3 = oneofIntAt(j, generatedMessageLite);
                            computeTagSize7 = CodedOutputStream.computeTagSize(i11);
                            computeInt32SizeNoTag2 = CodedOutputStream.computeInt32SizeNoTag(oneofIntAt3);
                            computeFixed64Size = computeInt32SizeNoTag2 + computeTagSize7;
                            i10 += computeFixed64Size;
                            break;
                        } else {
                            break;
                        }
                    case 64:
                        if (isOneofPresent(i11, i9, generatedMessageLite)) {
                            i10 = MessageSchema$$ExternalSyntheticOutline0.m(i11, 4, i10);
                            break;
                        } else {
                            break;
                        }
                    case 65:
                        if (isOneofPresent(i11, i9, generatedMessageLite)) {
                            i10 = MessageSchema$$ExternalSyntheticOutline0.m(i11, 8, i10);
                            break;
                        } else {
                            break;
                        }
                    case 66:
                        if (isOneofPresent(i11, i9, generatedMessageLite)) {
                            int oneofIntAt4 = oneofIntAt(j, generatedMessageLite);
                            computeTagSize7 = CodedOutputStream.computeTagSize(i11);
                            computeInt32SizeNoTag2 = CodedOutputStream.computeUInt32SizeNoTag((oneofIntAt4 >> 31) ^ (oneofIntAt4 << 1));
                            computeFixed64Size = computeInt32SizeNoTag2 + computeTagSize7;
                            i10 += computeFixed64Size;
                            break;
                        } else {
                            break;
                        }
                    case 67:
                        if (isOneofPresent(i11, i9, generatedMessageLite)) {
                            long oneofLongAt3 = oneofLongAt(j, generatedMessageLite);
                            computeTagSize9 = CodedOutputStream.computeTagSize(i11);
                            computeUInt64SizeNoTag4 = CodedOutputStream.computeUInt64SizeNoTag((oneofLongAt3 >> 63) ^ (oneofLongAt3 << 1));
                            computeFixed64Size = computeUInt64SizeNoTag4 + computeTagSize9;
                            i10 += computeFixed64Size;
                            break;
                        } else {
                            break;
                        }
                    case 68:
                        if (isOneofPresent(i11, i9, generatedMessageLite)) {
                            computeFixed64Size = CodedOutputStream.computeGroupSize(i11, (MessageLite) UnsafeUtil.getObject(j, generatedMessageLite), getMessageFieldSchema(i9));
                            i10 += computeFixed64Size;
                            break;
                        } else {
                            break;
                        }
                }
                i9 += 3;
                i8 = 1048575;
            }
            unknownFieldSetLiteSchema.getClass();
            return generatedMessageLite.unknownFields.getSerializedSize() + i10;
        }
        Unsafe unsafe2 = UNSAFE;
        int i18 = 0;
        int i19 = 0;
        int i20 = 1048575;
        int i21 = 0;
        while (i18 < iArr.length) {
            int typeAndOffsetAt2 = typeAndOffsetAt(i18);
            int i22 = iArr[i18];
            int type2 = type(typeAndOffsetAt2);
            if (type2 <= 17) {
                int i23 = iArr[i18 + 2];
                i = 1048575;
                int i24 = i23 & 1048575;
                i2 = i7 << (i23 >>> 20);
                if (i24 != i20) {
                    i21 = unsafe2.getInt(generatedMessageLite, i24);
                    i20 = i24;
                }
            } else {
                i = 1048575;
                i2 = 0;
            }
            int i25 = i20;
            long j5 = typeAndOffsetAt2 & i;
            switch (type2) {
                case 0:
                    i3 = 1;
                    c = '?';
                    if ((i21 & i2) != 0) {
                        i19 = MessageSchema$$ExternalSyntheticOutline0.m(i22, 8, i19);
                        break;
                    }
                    break;
                case 1:
                    i3 = 1;
                    c = '?';
                    if ((i21 & i2) != 0) {
                        i19 = MessageSchema$$ExternalSyntheticOutline0.m(i22, 4, i19);
                        break;
                    }
                case 2:
                    i3 = 1;
                    c = '?';
                    if ((i21 & i2) != 0) {
                        long j6 = unsafe2.getLong(generatedMessageLite, j5);
                        computeTagSize = CodedOutputStream.computeTagSize(i22);
                        computeUInt64SizeNoTag = CodedOutputStream.computeUInt64SizeNoTag(j6);
                        i4 = computeUInt64SizeNoTag + computeTagSize;
                        i19 += i4;
                    }
                    break;
                case 3:
                    i3 = 1;
                    c = '?';
                    if ((i21 & i2) != 0) {
                        long j7 = unsafe2.getLong(generatedMessageLite, j5);
                        computeTagSize = CodedOutputStream.computeTagSize(i22);
                        computeUInt64SizeNoTag = CodedOutputStream.computeUInt64SizeNoTag(j7);
                        i4 = computeUInt64SizeNoTag + computeTagSize;
                        i19 += i4;
                    }
                    break;
                case 4:
                    i3 = 1;
                    c = '?';
                    if ((i21 & i2) != 0) {
                        i4 = CodedOutputStream.computeInt32SizeNoTag(unsafe2.getInt(generatedMessageLite, j5)) + CodedOutputStream.computeTagSize(i22);
                        i19 += i4;
                    }
                    break;
                case 5:
                    i3 = 1;
                    c = '?';
                    if ((i21 & i2) != 0) {
                        i4 = CodedOutputStream.computeFixed64Size(i22);
                        i19 += i4;
                    }
                    break;
                case 6:
                    i3 = 1;
                    c = '?';
                    if ((i21 & i2) != 0) {
                        i4 = CodedOutputStream.computeFixed32Size(i22);
                        i19 += i4;
                    }
                    break;
                case 7:
                    c = '?';
                    if ((i21 & i2) != 0) {
                        i3 = 1;
                        i19 = MessageSchema$$ExternalSyntheticOutline0.m(i22, 1, i19);
                        break;
                    }
                    i3 = 1;
                case 8:
                    c = '?';
                    if ((i21 & i2) != 0) {
                        Object object6 = unsafe2.getObject(generatedMessageLite, j5);
                        i19 = (object6 instanceof ByteString ? CodedOutputStream.computeBytesSize(i22, (ByteString) object6) : CodedOutputStream.computeStringSizeNoTag((String) object6) + CodedOutputStream.computeTagSize(i22)) + i19;
                    }
                    i3 = 1;
                    break;
                case 9:
                    c = '?';
                    if ((i21 & i2) != 0) {
                        Object object7 = unsafe2.getObject(generatedMessageLite, j5);
                        Schema messageFieldSchema4 = getMessageFieldSchema(i18);
                        Class cls6 = SchemaUtil.GENERATED_MESSAGE_CLASS;
                        int computeTagSize12 = CodedOutputStream.computeTagSize(i22);
                        int serializedSize3 = ((AbstractMessageLite) ((MessageLite) object7)).getSerializedSize(messageFieldSchema4);
                        i19 = MapFieldSchemaLite$$ExternalSyntheticOutline0.m(serializedSize3, serializedSize3, computeTagSize12, i19);
                    }
                    i3 = 1;
                    break;
                case 10:
                    c = '?';
                    if ((i21 & i2) != 0) {
                        computeBytesSize = CodedOutputStream.computeBytesSize(i22, (ByteString) unsafe2.getObject(generatedMessageLite, j5));
                        i19 += computeBytesSize;
                    }
                    i3 = 1;
                    break;
                case ViewNode.TRANSLATIONX_FIELD_NUMBER /* 11 */:
                    c = '?';
                    if ((i21 & i2) != 0) {
                        int i26 = unsafe2.getInt(generatedMessageLite, j5);
                        computeTagSize2 = CodedOutputStream.computeTagSize(i22);
                        computeUInt32SizeNoTag = CodedOutputStream.computeUInt32SizeNoTag(i26);
                        computeBytesSize = computeUInt32SizeNoTag + computeTagSize2;
                        i19 += computeBytesSize;
                    }
                    i3 = 1;
                    break;
                case ViewNode.TRANSLATIONY_FIELD_NUMBER /* 12 */:
                    c = '?';
                    if ((i21 & i2) != 0) {
                        int i27 = unsafe2.getInt(generatedMessageLite, j5);
                        computeTagSize2 = CodedOutputStream.computeTagSize(i22);
                        computeUInt32SizeNoTag = CodedOutputStream.computeInt32SizeNoTag(i27);
                        computeBytesSize = computeUInt32SizeNoTag + computeTagSize2;
                        i19 += computeBytesSize;
                    }
                    i3 = 1;
                    break;
                case ViewNode.SCALEX_FIELD_NUMBER /* 13 */:
                    c = '?';
                    if ((i21 & i2) != 0) {
                        i19 = MessageSchema$$ExternalSyntheticOutline0.m(i22, 4, i19);
                        i3 = 1;
                        break;
                    }
                    i3 = 1;
                case ViewNode.SCALEY_FIELD_NUMBER /* 14 */:
                    c = '?';
                    if ((i21 & i2) != 0) {
                        i19 = MessageSchema$$ExternalSyntheticOutline0.m(i22, 8, i19);
                        i3 = 1;
                        break;
                    }
                    i3 = 1;
                    break;
                case 15:
                    c = '?';
                    if ((i21 & i2) != 0) {
                        int i28 = unsafe2.getInt(generatedMessageLite, j5);
                        computeTagSize2 = CodedOutputStream.computeTagSize(i22);
                        computeUInt32SizeNoTag = CodedOutputStream.computeUInt32SizeNoTag((i28 >> 31) ^ (i28 << 1));
                        computeBytesSize = computeUInt32SizeNoTag + computeTagSize2;
                        i19 += computeBytesSize;
                    }
                    i3 = 1;
                    break;
                case 16:
                    if ((i21 & i2) != 0) {
                        long j8 = unsafe2.getLong(generatedMessageLite, j5);
                        c = '?';
                        i19 += CodedOutputStream.computeUInt64SizeNoTag((j8 >> 63) ^ (j8 << 1)) + CodedOutputStream.computeTagSize(i22);
                    } else {
                        c = '?';
                    }
                    i3 = 1;
                    break;
                case ViewNode.CLIPCHILDREN_FIELD_NUMBER /* 17 */:
                    if ((i21 & i2) != 0) {
                        computeGroupSize = CodedOutputStream.computeGroupSize(i22, (MessageLite) unsafe2.getObject(generatedMessageLite, j5), getMessageFieldSchema(i18));
                        i19 += computeGroupSize;
                    }
                    i3 = 1;
                    c = '?';
                    break;
                case ViewNode.VISIBILITY_FIELD_NUMBER /* 18 */:
                    computeGroupSize = SchemaUtil.computeSizeFixed64List(i22, (List) unsafe2.getObject(generatedMessageLite, j5));
                    i19 += computeGroupSize;
                    i3 = 1;
                    c = '?';
                    break;
                case ViewNode.ELEVATION_FIELD_NUMBER /* 19 */:
                    computeGroupSize = SchemaUtil.computeSizeFixed32List(i22, (List) unsafe2.getObject(generatedMessageLite, j5));
                    i19 += computeGroupSize;
                    i3 = 1;
                    c = '?';
                    break;
                case 20:
                    computeGroupSize = SchemaUtil.computeSizeInt64List(i22, (List) unsafe2.getObject(generatedMessageLite, j5));
                    i19 += computeGroupSize;
                    i3 = 1;
                    c = '?';
                    break;
                case 21:
                    computeGroupSize = SchemaUtil.computeSizeUInt64List(i22, (List) unsafe2.getObject(generatedMessageLite, j5));
                    i19 += computeGroupSize;
                    i3 = 1;
                    c = '?';
                    break;
                case 22:
                    computeGroupSize = SchemaUtil.computeSizeInt32List(i22, (List) unsafe2.getObject(generatedMessageLite, j5));
                    i19 += computeGroupSize;
                    i3 = 1;
                    c = '?';
                    break;
                case 23:
                    computeGroupSize = SchemaUtil.computeSizeFixed64List(i22, (List) unsafe2.getObject(generatedMessageLite, j5));
                    i19 += computeGroupSize;
                    i3 = 1;
                    c = '?';
                    break;
                case 24:
                    computeGroupSize = SchemaUtil.computeSizeFixed32List(i22, (List) unsafe2.getObject(generatedMessageLite, j5));
                    i19 += computeGroupSize;
                    i3 = 1;
                    c = '?';
                    break;
                case 25:
                    List list4 = (List) unsafe2.getObject(generatedMessageLite, j5);
                    Class cls7 = SchemaUtil.GENERATED_MESSAGE_CLASS;
                    int size4 = list4.size();
                    computeTagSize3 = size4 == 0 ? 0 : (CodedOutputStream.computeTagSize(i22) + 1) * size4;
                    i19 += computeTagSize3;
                    i3 = 1;
                    c = '?';
                    break;
                case 26:
                    computeGroupSize = SchemaUtil.computeSizeStringList(i22, (List) unsafe2.getObject(generatedMessageLite, j5));
                    i19 += computeGroupSize;
                    i3 = 1;
                    c = '?';
                    break;
                case 27:
                    computeGroupSize = SchemaUtil.computeSizeMessageList(i22, (List) unsafe2.getObject(generatedMessageLite, j5), getMessageFieldSchema(i18));
                    i19 += computeGroupSize;
                    i3 = 1;
                    c = '?';
                    break;
                case 28:
                    computeGroupSize = SchemaUtil.computeSizeByteStringList(i22, (List) unsafe2.getObject(generatedMessageLite, j5));
                    i19 += computeGroupSize;
                    i3 = 1;
                    c = '?';
                    break;
                case 29:
                    computeGroupSize = SchemaUtil.computeSizeUInt32List(i22, (List) unsafe2.getObject(generatedMessageLite, j5));
                    i19 += computeGroupSize;
                    i3 = 1;
                    c = '?';
                    break;
                case 30:
                    computeGroupSize = SchemaUtil.computeSizeEnumList(i22, (List) unsafe2.getObject(generatedMessageLite, j5));
                    i19 += computeGroupSize;
                    i3 = 1;
                    c = '?';
                    break;
                case 31:
                    computeGroupSize = SchemaUtil.computeSizeFixed32List(i22, (List) unsafe2.getObject(generatedMessageLite, j5));
                    i19 += computeGroupSize;
                    i3 = 1;
                    c = '?';
                    break;
                case 32:
                    computeGroupSize = SchemaUtil.computeSizeFixed64List(i22, (List) unsafe2.getObject(generatedMessageLite, j5));
                    i19 += computeGroupSize;
                    i3 = 1;
                    c = '?';
                    break;
                case 33:
                    computeGroupSize = SchemaUtil.computeSizeSInt32List(i22, (List) unsafe2.getObject(generatedMessageLite, j5));
                    i19 += computeGroupSize;
                    i3 = 1;
                    c = '?';
                    break;
                case 34:
                    computeGroupSize = SchemaUtil.computeSizeSInt64List(i22, (List) unsafe2.getObject(generatedMessageLite, j5));
                    i19 += computeGroupSize;
                    i3 = 1;
                    c = '?';
                    break;
                case 35:
                    int computeSizeFixed64ListNoTag4 = SchemaUtil.computeSizeFixed64ListNoTag((List) unsafe2.getObject(generatedMessageLite, j5));
                    if (computeSizeFixed64ListNoTag4 > 0) {
                        i19 = MapFieldSchemaLite$$ExternalSyntheticOutline0.m(computeSizeFixed64ListNoTag4, CodedOutputStream.computeTagSize(i22), computeSizeFixed64ListNoTag4, i19);
                    }
                    i3 = 1;
                    c = '?';
                    break;
                case 36:
                    int computeSizeFixed32ListNoTag4 = SchemaUtil.computeSizeFixed32ListNoTag((List) unsafe2.getObject(generatedMessageLite, j5));
                    if (computeSizeFixed32ListNoTag4 > 0) {
                        i19 = MapFieldSchemaLite$$ExternalSyntheticOutline0.m(computeSizeFixed32ListNoTag4, CodedOutputStream.computeTagSize(i22), computeSizeFixed32ListNoTag4, i19);
                    }
                    i3 = 1;
                    c = '?';
                    break;
                case 37:
                    int computeSizeInt64ListNoTag2 = SchemaUtil.computeSizeInt64ListNoTag((List) unsafe2.getObject(generatedMessageLite, j5));
                    if (computeSizeInt64ListNoTag2 > 0) {
                        i19 = MapFieldSchemaLite$$ExternalSyntheticOutline0.m(computeSizeInt64ListNoTag2, CodedOutputStream.computeTagSize(i22), computeSizeInt64ListNoTag2, i19);
                    }
                    i3 = 1;
                    c = '?';
                    break;
                case 38:
                    int computeSizeUInt64ListNoTag2 = SchemaUtil.computeSizeUInt64ListNoTag((List) unsafe2.getObject(generatedMessageLite, j5));
                    if (computeSizeUInt64ListNoTag2 > 0) {
                        i19 = MapFieldSchemaLite$$ExternalSyntheticOutline0.m(computeSizeUInt64ListNoTag2, CodedOutputStream.computeTagSize(i22), computeSizeUInt64ListNoTag2, i19);
                    }
                    i3 = 1;
                    c = '?';
                    break;
                case 39:
                    int computeSizeInt32ListNoTag2 = SchemaUtil.computeSizeInt32ListNoTag((List) unsafe2.getObject(generatedMessageLite, j5));
                    if (computeSizeInt32ListNoTag2 > 0) {
                        i19 = MapFieldSchemaLite$$ExternalSyntheticOutline0.m(computeSizeInt32ListNoTag2, CodedOutputStream.computeTagSize(i22), computeSizeInt32ListNoTag2, i19);
                    }
                    i3 = 1;
                    c = '?';
                    break;
                case 40:
                    int computeSizeFixed64ListNoTag5 = SchemaUtil.computeSizeFixed64ListNoTag((List) unsafe2.getObject(generatedMessageLite, j5));
                    if (computeSizeFixed64ListNoTag5 > 0) {
                        i19 = MapFieldSchemaLite$$ExternalSyntheticOutline0.m(computeSizeFixed64ListNoTag5, CodedOutputStream.computeTagSize(i22), computeSizeFixed64ListNoTag5, i19);
                    }
                    i3 = 1;
                    c = '?';
                    break;
                case 41:
                    int computeSizeFixed32ListNoTag5 = SchemaUtil.computeSizeFixed32ListNoTag((List) unsafe2.getObject(generatedMessageLite, j5));
                    if (computeSizeFixed32ListNoTag5 > 0) {
                        i19 = MapFieldSchemaLite$$ExternalSyntheticOutline0.m(computeSizeFixed32ListNoTag5, CodedOutputStream.computeTagSize(i22), computeSizeFixed32ListNoTag5, i19);
                    }
                    i3 = 1;
                    c = '?';
                    break;
                case 42:
                    List list5 = (List) unsafe2.getObject(generatedMessageLite, j5);
                    Class cls8 = SchemaUtil.GENERATED_MESSAGE_CLASS;
                    int size5 = list5.size();
                    if (size5 > 0) {
                        i19 = MapFieldSchemaLite$$ExternalSyntheticOutline0.m(size5, CodedOutputStream.computeTagSize(i22), size5, i19);
                    }
                    i3 = 1;
                    c = '?';
                    break;
                case 43:
                    int computeSizeUInt32ListNoTag2 = SchemaUtil.computeSizeUInt32ListNoTag((List) unsafe2.getObject(generatedMessageLite, j5));
                    if (computeSizeUInt32ListNoTag2 > 0) {
                        i19 = MapFieldSchemaLite$$ExternalSyntheticOutline0.m(computeSizeUInt32ListNoTag2, CodedOutputStream.computeTagSize(i22), computeSizeUInt32ListNoTag2, i19);
                    }
                    i3 = 1;
                    c = '?';
                    break;
                case 44:
                    int computeSizeEnumListNoTag2 = SchemaUtil.computeSizeEnumListNoTag((List) unsafe2.getObject(generatedMessageLite, j5));
                    if (computeSizeEnumListNoTag2 > 0) {
                        i19 = MapFieldSchemaLite$$ExternalSyntheticOutline0.m(computeSizeEnumListNoTag2, CodedOutputStream.computeTagSize(i22), computeSizeEnumListNoTag2, i19);
                    }
                    i3 = 1;
                    c = '?';
                    break;
                case 45:
                    int computeSizeFixed32ListNoTag6 = SchemaUtil.computeSizeFixed32ListNoTag((List) unsafe2.getObject(generatedMessageLite, j5));
                    if (computeSizeFixed32ListNoTag6 > 0) {
                        i19 = MapFieldSchemaLite$$ExternalSyntheticOutline0.m(computeSizeFixed32ListNoTag6, CodedOutputStream.computeTagSize(i22), computeSizeFixed32ListNoTag6, i19);
                    }
                    i3 = 1;
                    c = '?';
                    break;
                case 46:
                    int computeSizeFixed64ListNoTag6 = SchemaUtil.computeSizeFixed64ListNoTag((List) unsafe2.getObject(generatedMessageLite, j5));
                    if (computeSizeFixed64ListNoTag6 > 0) {
                        i19 = MapFieldSchemaLite$$ExternalSyntheticOutline0.m(computeSizeFixed64ListNoTag6, CodedOutputStream.computeTagSize(i22), computeSizeFixed64ListNoTag6, i19);
                    }
                    i3 = 1;
                    c = '?';
                    break;
                case 47:
                    int computeSizeSInt32ListNoTag2 = SchemaUtil.computeSizeSInt32ListNoTag((List) unsafe2.getObject(generatedMessageLite, j5));
                    if (computeSizeSInt32ListNoTag2 > 0) {
                        i19 = MapFieldSchemaLite$$ExternalSyntheticOutline0.m(computeSizeSInt32ListNoTag2, CodedOutputStream.computeTagSize(i22), computeSizeSInt32ListNoTag2, i19);
                    }
                    i3 = 1;
                    c = '?';
                    break;
                case 48:
                    int computeSizeSInt64ListNoTag2 = SchemaUtil.computeSizeSInt64ListNoTag((List) unsafe2.getObject(generatedMessageLite, j5));
                    if (computeSizeSInt64ListNoTag2 > 0) {
                        i19 = MapFieldSchemaLite$$ExternalSyntheticOutline0.m(computeSizeSInt64ListNoTag2, CodedOutputStream.computeTagSize(i22), computeSizeSInt64ListNoTag2, i19);
                    }
                    i3 = 1;
                    c = '?';
                    break;
                case 49:
                    List list6 = (List) unsafe2.getObject(generatedMessageLite, j5);
                    Schema messageFieldSchema5 = getMessageFieldSchema(i18);
                    Class cls9 = SchemaUtil.GENERATED_MESSAGE_CLASS;
                    int size6 = list6.size();
                    if (size6 == 0) {
                        i5 = 0;
                    } else {
                        i5 = 0;
                        for (int i29 = 0; i29 < size6; i29++) {
                            i5 += CodedOutputStream.computeGroupSize(i22, (MessageLite) list6.get(i29), messageFieldSchema5);
                        }
                    }
                    i19 += i5;
                    i3 = 1;
                    c = '?';
                    break;
                case 50:
                    Object object8 = unsafe2.getObject(generatedMessageLite, j5);
                    Object mapFieldDefaultEntry2 = getMapFieldDefaultEntry(i18);
                    mapFieldSchemaLite.getClass();
                    computeGroupSize = MapFieldSchemaLite.getSerializedSize(i22, object8, mapFieldDefaultEntry2);
                    i19 += computeGroupSize;
                    i3 = 1;
                    c = '?';
                    break;
                case 51:
                    if (isOneofPresent(i22, i18, generatedMessageLite)) {
                        c2 = '\b';
                        i19 = MessageSchema$$ExternalSyntheticOutline0.m(i22, 8, i19);
                        i3 = 1;
                        c = '?';
                        break;
                    }
                    i3 = 1;
                    c = '?';
                case 52:
                    if (isOneofPresent(i22, i18, generatedMessageLite)) {
                        c3 = 4;
                        i19 = MessageSchema$$ExternalSyntheticOutline0.m(i22, 4, i19);
                        i3 = 1;
                        c = '?';
                        break;
                    }
                    i3 = 1;
                    c = '?';
                case 53:
                    if (isOneofPresent(i22, i18, generatedMessageLite)) {
                        long oneofLongAt4 = oneofLongAt(j5, generatedMessageLite);
                        computeTagSize4 = CodedOutputStream.computeTagSize(i22);
                        computeUInt64SizeNoTag2 = CodedOutputStream.computeUInt64SizeNoTag(oneofLongAt4);
                        computeTagSize3 = computeUInt64SizeNoTag2 + computeTagSize4;
                        i19 += computeTagSize3;
                    }
                    i3 = 1;
                    c = '?';
                    break;
                case 54:
                    if (isOneofPresent(i22, i18, generatedMessageLite)) {
                        long oneofLongAt5 = oneofLongAt(j5, generatedMessageLite);
                        computeTagSize4 = CodedOutputStream.computeTagSize(i22);
                        computeUInt64SizeNoTag2 = CodedOutputStream.computeUInt64SizeNoTag(oneofLongAt5);
                        computeTagSize3 = computeUInt64SizeNoTag2 + computeTagSize4;
                        i19 += computeTagSize3;
                    }
                    i3 = 1;
                    c = '?';
                    break;
                case 55:
                    if (isOneofPresent(i22, i18, generatedMessageLite)) {
                        int oneofIntAt5 = oneofIntAt(j5, generatedMessageLite);
                        computeTagSize5 = CodedOutputStream.computeTagSize(i22);
                        computeInt32SizeNoTag = CodedOutputStream.computeInt32SizeNoTag(oneofIntAt5);
                        computeGroupSize = computeInt32SizeNoTag + computeTagSize5;
                        i19 += computeGroupSize;
                    }
                    i3 = 1;
                    c = '?';
                    break;
                case 56:
                    if (isOneofPresent(i22, i18, generatedMessageLite)) {
                        computeGroupSize = CodedOutputStream.computeFixed64Size(i22);
                        i19 += computeGroupSize;
                    }
                    i3 = 1;
                    c = '?';
                    break;
                case 57:
                    if (isOneofPresent(i22, i18, generatedMessageLite)) {
                        computeGroupSize = CodedOutputStream.computeFixed32Size(i22);
                        i19 += computeGroupSize;
                    }
                    i3 = 1;
                    c = '?';
                    break;
                case 58:
                    if (isOneofPresent(i22, i18, generatedMessageLite)) {
                        i3 = 1;
                        i19 = MessageSchema$$ExternalSyntheticOutline0.m(i22, 1, i19);
                        c = '?';
                        break;
                    }
                    i3 = 1;
                    c = '?';
                case 59:
                    if (isOneofPresent(i22, i18, generatedMessageLite)) {
                        Object object9 = unsafe2.getObject(generatedMessageLite, j5);
                        i19 = (object9 instanceof ByteString ? CodedOutputStream.computeBytesSize(i22, (ByteString) object9) : CodedOutputStream.computeStringSizeNoTag((String) object9) + CodedOutputStream.computeTagSize(i22)) + i19;
                    }
                    i3 = 1;
                    c = '?';
                    break;
                case 60:
                    if (isOneofPresent(i22, i18, generatedMessageLite)) {
                        Object object10 = unsafe2.getObject(generatedMessageLite, j5);
                        Schema messageFieldSchema6 = getMessageFieldSchema(i18);
                        Class cls10 = SchemaUtil.GENERATED_MESSAGE_CLASS;
                        int computeTagSize13 = CodedOutputStream.computeTagSize(i22);
                        int serializedSize4 = ((AbstractMessageLite) ((MessageLite) object10)).getSerializedSize(messageFieldSchema6);
                        i19 = MapFieldSchemaLite$$ExternalSyntheticOutline0.m(serializedSize4, serializedSize4, computeTagSize13, i19);
                    }
                    i3 = 1;
                    c = '?';
                    break;
                case 61:
                    if (isOneofPresent(i22, i18, generatedMessageLite)) {
                        computeGroupSize = CodedOutputStream.computeBytesSize(i22, (ByteString) unsafe2.getObject(generatedMessageLite, j5));
                        i19 += computeGroupSize;
                    }
                    i3 = 1;
                    c = '?';
                    break;
                case 62:
                    if (isOneofPresent(i22, i18, generatedMessageLite)) {
                        int oneofIntAt6 = oneofIntAt(j5, generatedMessageLite);
                        computeTagSize5 = CodedOutputStream.computeTagSize(i22);
                        computeInt32SizeNoTag = CodedOutputStream.computeUInt32SizeNoTag(oneofIntAt6);
                        computeGroupSize = computeInt32SizeNoTag + computeTagSize5;
                        i19 += computeGroupSize;
                    }
                    i3 = 1;
                    c = '?';
                    break;
                case 63:
                    if (isOneofPresent(i22, i18, generatedMessageLite)) {
                        int oneofIntAt7 = oneofIntAt(j5, generatedMessageLite);
                        computeTagSize5 = CodedOutputStream.computeTagSize(i22);
                        computeInt32SizeNoTag = CodedOutputStream.computeInt32SizeNoTag(oneofIntAt7);
                        computeGroupSize = computeInt32SizeNoTag + computeTagSize5;
                        i19 += computeGroupSize;
                    }
                    i3 = 1;
                    c = '?';
                    break;
                case 64:
                    if (isOneofPresent(i22, i18, generatedMessageLite)) {
                        c3 = 4;
                        i19 = MessageSchema$$ExternalSyntheticOutline0.m(i22, 4, i19);
                        i3 = 1;
                        c = '?';
                        break;
                    }
                    i3 = 1;
                    c = '?';
                case 65:
                    if (isOneofPresent(i22, i18, generatedMessageLite)) {
                        c2 = '\b';
                        i19 = MessageSchema$$ExternalSyntheticOutline0.m(i22, 8, i19);
                        i3 = 1;
                        c = '?';
                        break;
                    }
                    i3 = 1;
                    c = '?';
                case 66:
                    if (isOneofPresent(i22, i18, generatedMessageLite)) {
                        int oneofIntAt8 = oneofIntAt(j5, generatedMessageLite);
                        computeTagSize5 = CodedOutputStream.computeTagSize(i22);
                        computeInt32SizeNoTag = CodedOutputStream.computeUInt32SizeNoTag((oneofIntAt8 >> 31) ^ (oneofIntAt8 << 1));
                        computeGroupSize = computeInt32SizeNoTag + computeTagSize5;
                        i19 += computeGroupSize;
                    }
                    i3 = 1;
                    c = '?';
                    break;
                case 67:
                    if (isOneofPresent(i22, i18, generatedMessageLite)) {
                        long oneofLongAt6 = oneofLongAt(j5, generatedMessageLite);
                        computeTagSize4 = CodedOutputStream.computeTagSize(i22);
                        computeUInt64SizeNoTag2 = CodedOutputStream.computeUInt64SizeNoTag((oneofLongAt6 >> 63) ^ (oneofLongAt6 << 1));
                        computeTagSize3 = computeUInt64SizeNoTag2 + computeTagSize4;
                        i19 += computeTagSize3;
                    }
                    i3 = 1;
                    c = '?';
                    break;
                case 68:
                    if (isOneofPresent(i22, i18, generatedMessageLite)) {
                        computeGroupSize = CodedOutputStream.computeGroupSize(i22, (MessageLite) unsafe2.getObject(generatedMessageLite, j5), getMessageFieldSchema(i18));
                        i19 += computeGroupSize;
                    }
                    i3 = 1;
                    c = '?';
                    break;
                default:
                    i3 = 1;
                    c = '?';
                    break;
            }
            i18 += 3;
            i7 = i3;
            i20 = i25;
        }
        unknownFieldSetLiteSchema.getClass();
        return generatedMessageLite.unknownFields.getSerializedSize() + i19;
    }

    /* JADX WARN: Code restructure failed: missing block: B:103:0x01f0, code lost:
    
        if (r4 != false) goto L42;
     */
    /* JADX WARN: Code restructure failed: missing block: B:51:0x00d5, code lost:
    
        if (r4 != false) goto L42;
     */
    /* JADX WARN: Code restructure failed: missing block: B:52:0x00d7, code lost:
    
        r8 = 1231;
     */
    /* JADX WARN: Code restructure failed: missing block: B:53:0x00d8, code lost:
    
        r3 = r8 + r3;
     */
    @Override // com.google.protobuf.Schema
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final int hashCode(com.google.protobuf.GeneratedMessageLite r12) {
        /*
            Method dump skipped, instructions count: 746
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.MessageSchema.hashCode(com.google.protobuf.GeneratedMessageLite):int");
    }

    public final boolean isFieldPresent(int i, Object obj) {
        int i2 = this.buffer[i + 2];
        long j = i2 & 1048575;
        if (j != 1048575) {
            return ((1 << (i2 >>> 20)) & UnsafeUtil.getInt(j, obj)) != 0;
        }
        int typeAndOffsetAt = typeAndOffsetAt(i);
        long j2 = typeAndOffsetAt & 1048575;
        switch (type(typeAndOffsetAt)) {
            case 0:
                return Double.doubleToRawLongBits(UnsafeUtil.MEMORY_ACCESSOR.getDouble(j2, obj)) != 0;
            case 1:
                return Float.floatToRawIntBits(UnsafeUtil.MEMORY_ACCESSOR.getFloat(j2, obj)) != 0;
            case 2:
                return UnsafeUtil.getLong(j2, obj) != 0;
            case 3:
                return UnsafeUtil.getLong(j2, obj) != 0;
            case 4:
                return UnsafeUtil.getInt(j2, obj) != 0;
            case 5:
                return UnsafeUtil.getLong(j2, obj) != 0;
            case 6:
                return UnsafeUtil.getInt(j2, obj) != 0;
            case 7:
                return UnsafeUtil.MEMORY_ACCESSOR.getBoolean(j2, obj);
            case 8:
                Object object = UnsafeUtil.getObject(j2, obj);
                if (object instanceof String) {
                    return !((String) object).isEmpty();
                }
                if (object instanceof ByteString) {
                    return !ByteString.EMPTY.equals(object);
                }
                throw new IllegalArgumentException();
            case 9:
                return UnsafeUtil.getObject(j2, obj) != null;
            case 10:
                return !ByteString.EMPTY.equals(UnsafeUtil.getObject(j2, obj));
            case ViewNode.TRANSLATIONX_FIELD_NUMBER /* 11 */:
                return UnsafeUtil.getInt(j2, obj) != 0;
            case ViewNode.TRANSLATIONY_FIELD_NUMBER /* 12 */:
                return UnsafeUtil.getInt(j2, obj) != 0;
            case ViewNode.SCALEX_FIELD_NUMBER /* 13 */:
                return UnsafeUtil.getInt(j2, obj) != 0;
            case ViewNode.SCALEY_FIELD_NUMBER /* 14 */:
                return UnsafeUtil.getLong(j2, obj) != 0;
            case 15:
                return UnsafeUtil.getInt(j2, obj) != 0;
            case 16:
                return UnsafeUtil.getLong(j2, obj) != 0;
            case ViewNode.CLIPCHILDREN_FIELD_NUMBER /* 17 */:
                return UnsafeUtil.getObject(j2, obj) != null;
            default:
                throw new IllegalArgumentException();
        }
    }

    @Override // com.google.protobuf.Schema
    public final boolean isInitialized(Object obj) {
        int i = 1048575;
        int i2 = 0;
        int i3 = 0;
        while (true) {
            boolean z = true;
            if (i2 >= this.checkInitializedCount) {
                return true;
            }
            int i4 = this.intArray[i2];
            int[] iArr = this.buffer;
            int i5 = iArr[i4];
            int typeAndOffsetAt = typeAndOffsetAt(i4);
            int i6 = iArr[i4 + 2];
            int i7 = i6 & 1048575;
            int i8 = 1 << (i6 >>> 20);
            if (i7 != i) {
                if (i7 != 1048575) {
                    i3 = UNSAFE.getInt(obj, i7);
                }
                i = i7;
            }
            if ((268435456 & typeAndOffsetAt) != 0) {
                if (!(i == 1048575 ? isFieldPresent(i4, obj) : (i3 & i8) != 0)) {
                    return false;
                }
            }
            int type = type(typeAndOffsetAt);
            if (type == 9 || type == 17) {
                if (i == 1048575) {
                    z = isFieldPresent(i4, obj);
                } else if ((i8 & i3) == 0) {
                    z = false;
                }
                if (z && !getMessageFieldSchema(i4).isInitialized(UnsafeUtil.getObject(typeAndOffsetAt & 1048575, obj))) {
                    return false;
                }
            } else {
                if (type != 27) {
                    if (type == 60 || type == 68) {
                        if (isOneofPresent(i5, i4, obj) && !getMessageFieldSchema(i4).isInitialized(UnsafeUtil.getObject(typeAndOffsetAt & 1048575, obj))) {
                            return false;
                        }
                    } else if (type != 49) {
                        if (type != 50) {
                            continue;
                        } else {
                            Object object = UnsafeUtil.getObject(typeAndOffsetAt & 1048575, obj);
                            this.mapFieldSchema.getClass();
                            MapFieldLite mapFieldLite = (MapFieldLite) object;
                            if (!mapFieldLite.isEmpty() && ((MapEntryLite) getMapFieldDefaultEntry(i4)).metadata.valueType.getJavaType() == WireFormat$JavaType.MESSAGE) {
                                Schema schema = null;
                                for (Object obj2 : mapFieldLite.values()) {
                                    if (schema == null) {
                                        schema = Protobuf.INSTANCE.schemaFor(obj2.getClass());
                                    }
                                    if (!schema.isInitialized(obj2)) {
                                        return false;
                                    }
                                }
                            }
                        }
                    }
                }
                List list = (List) UnsafeUtil.getObject(typeAndOffsetAt & 1048575, obj);
                if (list.isEmpty()) {
                    continue;
                } else {
                    Schema messageFieldSchema = getMessageFieldSchema(i4);
                    for (int i9 = 0; i9 < list.size(); i9++) {
                        if (!messageFieldSchema.isInitialized(list.get(i9))) {
                            return false;
                        }
                    }
                }
            }
            i2++;
        }
    }

    public final boolean isOneofPresent(int i, int i2, Object obj) {
        return UnsafeUtil.getInt((long) (this.buffer[i2 + 2] & 1048575), obj) == i;
    }

    @Override // com.google.protobuf.Schema
    public final void makeImmutable(Object obj) {
        if (isMutable(obj)) {
            if (obj instanceof GeneratedMessageLite) {
                GeneratedMessageLite generatedMessageLite = (GeneratedMessageLite) obj;
                generatedMessageLite.setMemoizedSerializedSize(Integer.MAX_VALUE);
                generatedMessageLite.memoizedHashCode = 0;
                generatedMessageLite.markImmutable();
            }
            int length = this.buffer.length;
            for (int i = 0; i < length; i += 3) {
                int typeAndOffsetAt = typeAndOffsetAt(i);
                long j = 1048575 & typeAndOffsetAt;
                int type = type(typeAndOffsetAt);
                if (type != 9) {
                    switch (type) {
                        case ViewNode.VISIBILITY_FIELD_NUMBER /* 18 */:
                        case ViewNode.ELEVATION_FIELD_NUMBER /* 19 */:
                        case 20:
                        case 21:
                        case 22:
                        case 23:
                        case 24:
                        case 25:
                        case 26:
                        case 27:
                        case 28:
                        case 29:
                        case 30:
                        case 31:
                        case 32:
                        case 33:
                        case 34:
                        case 35:
                        case 36:
                        case 37:
                        case 38:
                        case 39:
                        case 40:
                        case 41:
                        case 42:
                        case 43:
                        case 44:
                        case 45:
                        case 46:
                        case 47:
                        case 48:
                        case 49:
                            this.listFieldSchema.makeImmutableListAt(j, obj);
                            break;
                        case 50:
                            Unsafe unsafe = UNSAFE;
                            Object object = unsafe.getObject(obj, j);
                            if (object != null) {
                                this.mapFieldSchema.getClass();
                                ((MapFieldLite) object).makeImmutable();
                                unsafe.putObject(obj, j, object);
                                break;
                            } else {
                                break;
                            }
                    }
                }
                if (isFieldPresent(i, obj)) {
                    getMessageFieldSchema(i).makeImmutable(UNSAFE.getObject(obj, j));
                }
            }
            this.unknownFieldSchema.getClass();
            ((GeneratedMessageLite) obj).unknownFields.isMutable = false;
        }
    }

    @Override // com.google.protobuf.Schema
    public final void mergeFrom(Object obj, Object obj2) {
        checkMutable(obj);
        obj2.getClass();
        int i = 0;
        while (true) {
            int[] iArr = this.buffer;
            if (i >= iArr.length) {
                SchemaUtil.mergeUnknownFields(this.unknownFieldSchema, obj, obj2);
                return;
            }
            int typeAndOffsetAt = typeAndOffsetAt(i);
            long j = 1048575 & typeAndOffsetAt;
            int i2 = iArr[i];
            switch (type(typeAndOffsetAt)) {
                case 0:
                    if (!isFieldPresent(i, obj2)) {
                        break;
                    } else {
                        UnsafeUtil.Android32MemoryAccessor android32MemoryAccessor = UnsafeUtil.MEMORY_ACCESSOR;
                        android32MemoryAccessor.putDouble(obj, j, android32MemoryAccessor.getDouble(j, obj2));
                        setFieldPresent(i, obj);
                        break;
                    }
                case 1:
                    if (!isFieldPresent(i, obj2)) {
                        break;
                    } else {
                        UnsafeUtil.Android32MemoryAccessor android32MemoryAccessor2 = UnsafeUtil.MEMORY_ACCESSOR;
                        android32MemoryAccessor2.putFloat(obj, j, android32MemoryAccessor2.getFloat(j, obj2));
                        setFieldPresent(i, obj);
                        break;
                    }
                case 2:
                    if (!isFieldPresent(i, obj2)) {
                        break;
                    } else {
                        UnsafeUtil.putLong(obj, j, UnsafeUtil.getLong(j, obj2));
                        setFieldPresent(i, obj);
                        break;
                    }
                case 3:
                    if (!isFieldPresent(i, obj2)) {
                        break;
                    } else {
                        UnsafeUtil.putLong(obj, j, UnsafeUtil.getLong(j, obj2));
                        setFieldPresent(i, obj);
                        break;
                    }
                case 4:
                    if (!isFieldPresent(i, obj2)) {
                        break;
                    } else {
                        UnsafeUtil.putInt(obj, j, UnsafeUtil.getInt(j, obj2));
                        setFieldPresent(i, obj);
                        break;
                    }
                case 5:
                    if (!isFieldPresent(i, obj2)) {
                        break;
                    } else {
                        UnsafeUtil.putLong(obj, j, UnsafeUtil.getLong(j, obj2));
                        setFieldPresent(i, obj);
                        break;
                    }
                case 6:
                    if (!isFieldPresent(i, obj2)) {
                        break;
                    } else {
                        UnsafeUtil.putInt(obj, j, UnsafeUtil.getInt(j, obj2));
                        setFieldPresent(i, obj);
                        break;
                    }
                case 7:
                    if (!isFieldPresent(i, obj2)) {
                        break;
                    } else {
                        UnsafeUtil.Android32MemoryAccessor android32MemoryAccessor3 = UnsafeUtil.MEMORY_ACCESSOR;
                        android32MemoryAccessor3.putBoolean(obj, j, android32MemoryAccessor3.getBoolean(j, obj2));
                        setFieldPresent(i, obj);
                        break;
                    }
                case 8:
                    if (!isFieldPresent(i, obj2)) {
                        break;
                    } else {
                        UnsafeUtil.putObject(j, obj, UnsafeUtil.getObject(j, obj2));
                        setFieldPresent(i, obj);
                        break;
                    }
                case 9:
                    mergeMessage(i, obj, obj2);
                    break;
                case 10:
                    if (!isFieldPresent(i, obj2)) {
                        break;
                    } else {
                        UnsafeUtil.putObject(j, obj, UnsafeUtil.getObject(j, obj2));
                        setFieldPresent(i, obj);
                        break;
                    }
                case ViewNode.TRANSLATIONX_FIELD_NUMBER /* 11 */:
                    if (!isFieldPresent(i, obj2)) {
                        break;
                    } else {
                        UnsafeUtil.putInt(obj, j, UnsafeUtil.getInt(j, obj2));
                        setFieldPresent(i, obj);
                        break;
                    }
                case ViewNode.TRANSLATIONY_FIELD_NUMBER /* 12 */:
                    if (!isFieldPresent(i, obj2)) {
                        break;
                    } else {
                        UnsafeUtil.putInt(obj, j, UnsafeUtil.getInt(j, obj2));
                        setFieldPresent(i, obj);
                        break;
                    }
                case ViewNode.SCALEX_FIELD_NUMBER /* 13 */:
                    if (!isFieldPresent(i, obj2)) {
                        break;
                    } else {
                        UnsafeUtil.putInt(obj, j, UnsafeUtil.getInt(j, obj2));
                        setFieldPresent(i, obj);
                        break;
                    }
                case ViewNode.SCALEY_FIELD_NUMBER /* 14 */:
                    if (!isFieldPresent(i, obj2)) {
                        break;
                    } else {
                        UnsafeUtil.putLong(obj, j, UnsafeUtil.getLong(j, obj2));
                        setFieldPresent(i, obj);
                        break;
                    }
                case 15:
                    if (!isFieldPresent(i, obj2)) {
                        break;
                    } else {
                        UnsafeUtil.putInt(obj, j, UnsafeUtil.getInt(j, obj2));
                        setFieldPresent(i, obj);
                        break;
                    }
                case 16:
                    if (!isFieldPresent(i, obj2)) {
                        break;
                    } else {
                        UnsafeUtil.putLong(obj, j, UnsafeUtil.getLong(j, obj2));
                        setFieldPresent(i, obj);
                        break;
                    }
                case ViewNode.CLIPCHILDREN_FIELD_NUMBER /* 17 */:
                    mergeMessage(i, obj, obj2);
                    break;
                case ViewNode.VISIBILITY_FIELD_NUMBER /* 18 */:
                case ViewNode.ELEVATION_FIELD_NUMBER /* 19 */:
                case 20:
                case 21:
                case 22:
                case 23:
                case 24:
                case 25:
                case 26:
                case 27:
                case 28:
                case 29:
                case 30:
                case 31:
                case 32:
                case 33:
                case 34:
                case 35:
                case 36:
                case 37:
                case 38:
                case 39:
                case 40:
                case 41:
                case 42:
                case 43:
                case 44:
                case 45:
                case 46:
                case 47:
                case 48:
                case 49:
                    this.listFieldSchema.mergeListsAt(j, obj, obj2);
                    break;
                case 50:
                    Class cls = SchemaUtil.GENERATED_MESSAGE_CLASS;
                    Object object = UnsafeUtil.getObject(j, obj);
                    Object object2 = UnsafeUtil.getObject(j, obj2);
                    this.mapFieldSchema.getClass();
                    UnsafeUtil.putObject(j, obj, MapFieldSchemaLite.mergeFrom(object, object2));
                    break;
                case 51:
                case 52:
                case 53:
                case 54:
                case 55:
                case 56:
                case 57:
                case 58:
                case 59:
                    if (!isOneofPresent(i2, i, obj2)) {
                        break;
                    } else {
                        UnsafeUtil.putObject(j, obj, UnsafeUtil.getObject(j, obj2));
                        setOneofPresent(i2, i, obj);
                        break;
                    }
                case 60:
                    mergeOneofMessage(i, obj, obj2);
                    break;
                case 61:
                case 62:
                case 63:
                case 64:
                case 65:
                case 66:
                case 67:
                    if (!isOneofPresent(i2, i, obj2)) {
                        break;
                    } else {
                        UnsafeUtil.putObject(j, obj, UnsafeUtil.getObject(j, obj2));
                        setOneofPresent(i2, i, obj);
                        break;
                    }
                case 68:
                    mergeOneofMessage(i, obj, obj2);
                    break;
            }
            i += 3;
        }
    }

    public final void mergeFromHelper(UnknownFieldSetLiteSchema unknownFieldSetLiteSchema, Object obj, CodedInputStreamReader codedInputStreamReader, ExtensionRegistryLite extensionRegistryLite) {
        int[] iArr = this.intArray;
        int i = this.repeatedFieldOffsetStart;
        int i2 = this.checkInitializedCount;
        Object obj2 = null;
        while (true) {
            try {
                int fieldNumber = codedInputStreamReader.getFieldNumber();
                int slowPositionForFieldNumber = (fieldNumber < this.minFieldNumber || fieldNumber > this.maxFieldNumber) ? -1 : slowPositionForFieldNumber(fieldNumber, 0);
                if (slowPositionForFieldNumber >= 0) {
                    int typeAndOffsetAt = typeAndOffsetAt(slowPositionForFieldNumber);
                    try {
                        int type = type(typeAndOffsetAt);
                        CodedInputStream$StreamDecoder codedInputStream$StreamDecoder = codedInputStreamReader.input;
                        ListFieldSchema listFieldSchema = this.listFieldSchema;
                        switch (type) {
                            case 0:
                                long offset = offset(typeAndOffsetAt);
                                codedInputStreamReader.requireWireType(1);
                                UnsafeUtil.MEMORY_ACCESSOR.putDouble(obj, offset, codedInputStream$StreamDecoder.readDouble());
                                setFieldPresent(slowPositionForFieldNumber, obj);
                                break;
                            case 1:
                                long offset2 = offset(typeAndOffsetAt);
                                codedInputStreamReader.requireWireType(5);
                                UnsafeUtil.MEMORY_ACCESSOR.putFloat(obj, offset2, codedInputStream$StreamDecoder.readFloat());
                                setFieldPresent(slowPositionForFieldNumber, obj);
                                break;
                            case 2:
                                long offset3 = offset(typeAndOffsetAt);
                                codedInputStreamReader.requireWireType(0);
                                UnsafeUtil.putLong(obj, offset3, codedInputStream$StreamDecoder.readInt64());
                                setFieldPresent(slowPositionForFieldNumber, obj);
                                break;
                            case 3:
                                long offset4 = offset(typeAndOffsetAt);
                                codedInputStreamReader.requireWireType(0);
                                UnsafeUtil.putLong(obj, offset4, codedInputStream$StreamDecoder.readUInt64());
                                setFieldPresent(slowPositionForFieldNumber, obj);
                                break;
                            case 4:
                                long offset5 = offset(typeAndOffsetAt);
                                codedInputStreamReader.requireWireType(0);
                                UnsafeUtil.putInt(obj, offset5, codedInputStream$StreamDecoder.readInt32());
                                setFieldPresent(slowPositionForFieldNumber, obj);
                                break;
                            case 5:
                                long offset6 = offset(typeAndOffsetAt);
                                codedInputStreamReader.requireWireType(1);
                                UnsafeUtil.putLong(obj, offset6, codedInputStream$StreamDecoder.readFixed64());
                                setFieldPresent(slowPositionForFieldNumber, obj);
                                break;
                            case 6:
                                long offset7 = offset(typeAndOffsetAt);
                                codedInputStreamReader.requireWireType(5);
                                UnsafeUtil.putInt(obj, offset7, codedInputStream$StreamDecoder.readFixed32());
                                setFieldPresent(slowPositionForFieldNumber, obj);
                                break;
                            case 7:
                                long offset8 = offset(typeAndOffsetAt);
                                codedInputStreamReader.requireWireType(0);
                                UnsafeUtil.MEMORY_ACCESSOR.putBoolean(obj, offset8, codedInputStream$StreamDecoder.readBool());
                                setFieldPresent(slowPositionForFieldNumber, obj);
                                break;
                            case 8:
                                readString(obj, typeAndOffsetAt, codedInputStreamReader);
                                setFieldPresent(slowPositionForFieldNumber, obj);
                                break;
                            case 9:
                                MessageLite messageLite = (MessageLite) mutableMessageFieldForMerge(slowPositionForFieldNumber, obj);
                                Schema messageFieldSchema = getMessageFieldSchema(slowPositionForFieldNumber);
                                codedInputStreamReader.requireWireType(2);
                                codedInputStreamReader.mergeMessageFieldInternal(messageLite, messageFieldSchema, extensionRegistryLite);
                                storeMessageField(slowPositionForFieldNumber, obj, messageLite);
                                break;
                            case 10:
                                UnsafeUtil.putObject(offset(typeAndOffsetAt), obj, codedInputStreamReader.readBytes());
                                setFieldPresent(slowPositionForFieldNumber, obj);
                                break;
                            case ViewNode.TRANSLATIONX_FIELD_NUMBER /* 11 */:
                                long offset9 = offset(typeAndOffsetAt);
                                codedInputStreamReader.requireWireType(0);
                                UnsafeUtil.putInt(obj, offset9, codedInputStream$StreamDecoder.readUInt32());
                                setFieldPresent(slowPositionForFieldNumber, obj);
                                break;
                            case ViewNode.TRANSLATIONY_FIELD_NUMBER /* 12 */:
                                codedInputStreamReader.requireWireType(0);
                                int readEnum = codedInputStream$StreamDecoder.readEnum();
                                Internal.EnumVerifier enumFieldVerifier = getEnumFieldVerifier(slowPositionForFieldNumber);
                                if (enumFieldVerifier != null && !enumFieldVerifier.isInRange(readEnum)) {
                                    obj2 = SchemaUtil.storeUnknownEnum(obj, fieldNumber, readEnum, obj2, unknownFieldSetLiteSchema);
                                    break;
                                }
                                UnsafeUtil.putInt(obj, offset(typeAndOffsetAt), readEnum);
                                setFieldPresent(slowPositionForFieldNumber, obj);
                                break;
                            case ViewNode.SCALEX_FIELD_NUMBER /* 13 */:
                                long offset10 = offset(typeAndOffsetAt);
                                codedInputStreamReader.requireWireType(5);
                                UnsafeUtil.putInt(obj, offset10, codedInputStream$StreamDecoder.readSFixed32());
                                setFieldPresent(slowPositionForFieldNumber, obj);
                                break;
                            case ViewNode.SCALEY_FIELD_NUMBER /* 14 */:
                                long offset11 = offset(typeAndOffsetAt);
                                codedInputStreamReader.requireWireType(1);
                                UnsafeUtil.putLong(obj, offset11, codedInputStream$StreamDecoder.readSFixed64());
                                setFieldPresent(slowPositionForFieldNumber, obj);
                                break;
                            case 15:
                                long offset12 = offset(typeAndOffsetAt);
                                codedInputStreamReader.requireWireType(0);
                                UnsafeUtil.putInt(obj, offset12, codedInputStream$StreamDecoder.readSInt32());
                                setFieldPresent(slowPositionForFieldNumber, obj);
                                break;
                            case 16:
                                long offset13 = offset(typeAndOffsetAt);
                                codedInputStreamReader.requireWireType(0);
                                UnsafeUtil.putLong(obj, offset13, codedInputStream$StreamDecoder.readSInt64());
                                setFieldPresent(slowPositionForFieldNumber, obj);
                                break;
                            case ViewNode.CLIPCHILDREN_FIELD_NUMBER /* 17 */:
                                MessageLite messageLite2 = (MessageLite) mutableMessageFieldForMerge(slowPositionForFieldNumber, obj);
                                Schema messageFieldSchema2 = getMessageFieldSchema(slowPositionForFieldNumber);
                                codedInputStreamReader.requireWireType(3);
                                codedInputStreamReader.mergeGroupFieldInternal(messageLite2, messageFieldSchema2, extensionRegistryLite);
                                storeMessageField(slowPositionForFieldNumber, obj, messageLite2);
                                break;
                            case ViewNode.VISIBILITY_FIELD_NUMBER /* 18 */:
                                codedInputStreamReader.readDoubleList(listFieldSchema.mutableListAt(offset(typeAndOffsetAt), obj));
                                break;
                            case ViewNode.ELEVATION_FIELD_NUMBER /* 19 */:
                                codedInputStreamReader.readFloatList(listFieldSchema.mutableListAt(offset(typeAndOffsetAt), obj));
                                break;
                            case 20:
                                codedInputStreamReader.readInt64List(listFieldSchema.mutableListAt(offset(typeAndOffsetAt), obj));
                                break;
                            case 21:
                                codedInputStreamReader.readUInt64List(listFieldSchema.mutableListAt(offset(typeAndOffsetAt), obj));
                                break;
                            case 22:
                                codedInputStreamReader.readInt32List(listFieldSchema.mutableListAt(offset(typeAndOffsetAt), obj));
                                break;
                            case 23:
                                codedInputStreamReader.readFixed64List(listFieldSchema.mutableListAt(offset(typeAndOffsetAt), obj));
                                break;
                            case 24:
                                codedInputStreamReader.readFixed32List(listFieldSchema.mutableListAt(offset(typeAndOffsetAt), obj));
                                break;
                            case 25:
                                codedInputStreamReader.readBoolList(listFieldSchema.mutableListAt(offset(typeAndOffsetAt), obj));
                                break;
                            case 26:
                                readStringList(obj, typeAndOffsetAt, codedInputStreamReader);
                                break;
                            case 27:
                                readMessageList(obj, typeAndOffsetAt, codedInputStreamReader, getMessageFieldSchema(slowPositionForFieldNumber), extensionRegistryLite);
                                break;
                            case 28:
                                codedInputStreamReader.readBytesList(listFieldSchema.mutableListAt(offset(typeAndOffsetAt), obj));
                                break;
                            case 29:
                                codedInputStreamReader.readUInt32List(listFieldSchema.mutableListAt(offset(typeAndOffsetAt), obj));
                                break;
                            case 30:
                                List mutableListAt = listFieldSchema.mutableListAt(offset(typeAndOffsetAt), obj);
                                codedInputStreamReader.readEnumList(mutableListAt);
                                obj2 = SchemaUtil.filterUnknownEnumList(obj, fieldNumber, mutableListAt, getEnumFieldVerifier(slowPositionForFieldNumber), obj2, unknownFieldSetLiteSchema);
                                break;
                            case 31:
                                codedInputStreamReader.readSFixed32List(listFieldSchema.mutableListAt(offset(typeAndOffsetAt), obj));
                                break;
                            case 32:
                                codedInputStreamReader.readSFixed64List(listFieldSchema.mutableListAt(offset(typeAndOffsetAt), obj));
                                break;
                            case 33:
                                codedInputStreamReader.readSInt32List(listFieldSchema.mutableListAt(offset(typeAndOffsetAt), obj));
                                break;
                            case 34:
                                codedInputStreamReader.readSInt64List(listFieldSchema.mutableListAt(offset(typeAndOffsetAt), obj));
                                break;
                            case 35:
                                codedInputStreamReader.readDoubleList(listFieldSchema.mutableListAt(offset(typeAndOffsetAt), obj));
                                break;
                            case 36:
                                codedInputStreamReader.readFloatList(listFieldSchema.mutableListAt(offset(typeAndOffsetAt), obj));
                                break;
                            case 37:
                                codedInputStreamReader.readInt64List(listFieldSchema.mutableListAt(offset(typeAndOffsetAt), obj));
                                break;
                            case 38:
                                codedInputStreamReader.readUInt64List(listFieldSchema.mutableListAt(offset(typeAndOffsetAt), obj));
                                break;
                            case 39:
                                codedInputStreamReader.readInt32List(listFieldSchema.mutableListAt(offset(typeAndOffsetAt), obj));
                                break;
                            case 40:
                                codedInputStreamReader.readFixed64List(listFieldSchema.mutableListAt(offset(typeAndOffsetAt), obj));
                                break;
                            case 41:
                                codedInputStreamReader.readFixed32List(listFieldSchema.mutableListAt(offset(typeAndOffsetAt), obj));
                                break;
                            case 42:
                                codedInputStreamReader.readBoolList(listFieldSchema.mutableListAt(offset(typeAndOffsetAt), obj));
                                break;
                            case 43:
                                codedInputStreamReader.readUInt32List(listFieldSchema.mutableListAt(offset(typeAndOffsetAt), obj));
                                break;
                            case 44:
                                List mutableListAt2 = listFieldSchema.mutableListAt(offset(typeAndOffsetAt), obj);
                                codedInputStreamReader.readEnumList(mutableListAt2);
                                obj2 = SchemaUtil.filterUnknownEnumList(obj, fieldNumber, mutableListAt2, getEnumFieldVerifier(slowPositionForFieldNumber), obj2, unknownFieldSetLiteSchema);
                                break;
                            case 45:
                                codedInputStreamReader.readSFixed32List(listFieldSchema.mutableListAt(offset(typeAndOffsetAt), obj));
                                break;
                            case 46:
                                codedInputStreamReader.readSFixed64List(listFieldSchema.mutableListAt(offset(typeAndOffsetAt), obj));
                                break;
                            case 47:
                                codedInputStreamReader.readSInt32List(listFieldSchema.mutableListAt(offset(typeAndOffsetAt), obj));
                                break;
                            case 48:
                                codedInputStreamReader.readSInt64List(listFieldSchema.mutableListAt(offset(typeAndOffsetAt), obj));
                                break;
                            case 49:
                                readGroupList(obj, offset(typeAndOffsetAt), codedInputStreamReader, getMessageFieldSchema(slowPositionForFieldNumber), extensionRegistryLite);
                                break;
                            case 50:
                                mergeMap(obj, slowPositionForFieldNumber, getMapFieldDefaultEntry(slowPositionForFieldNumber), extensionRegistryLite, codedInputStreamReader);
                                break;
                            case 51:
                                long offset14 = offset(typeAndOffsetAt);
                                codedInputStreamReader.requireWireType(1);
                                UnsafeUtil.putObject(offset14, obj, Double.valueOf(codedInputStream$StreamDecoder.readDouble()));
                                setOneofPresent(fieldNumber, slowPositionForFieldNumber, obj);
                                break;
                            case 52:
                                long offset15 = offset(typeAndOffsetAt);
                                codedInputStreamReader.requireWireType(5);
                                UnsafeUtil.putObject(offset15, obj, Float.valueOf(codedInputStream$StreamDecoder.readFloat()));
                                setOneofPresent(fieldNumber, slowPositionForFieldNumber, obj);
                                break;
                            case 53:
                                long offset16 = offset(typeAndOffsetAt);
                                codedInputStreamReader.requireWireType(0);
                                UnsafeUtil.putObject(offset16, obj, Long.valueOf(codedInputStream$StreamDecoder.readInt64()));
                                setOneofPresent(fieldNumber, slowPositionForFieldNumber, obj);
                                break;
                            case 54:
                                long offset17 = offset(typeAndOffsetAt);
                                codedInputStreamReader.requireWireType(0);
                                UnsafeUtil.putObject(offset17, obj, Long.valueOf(codedInputStream$StreamDecoder.readUInt64()));
                                setOneofPresent(fieldNumber, slowPositionForFieldNumber, obj);
                                break;
                            case 55:
                                long offset18 = offset(typeAndOffsetAt);
                                codedInputStreamReader.requireWireType(0);
                                UnsafeUtil.putObject(offset18, obj, Integer.valueOf(codedInputStream$StreamDecoder.readInt32()));
                                setOneofPresent(fieldNumber, slowPositionForFieldNumber, obj);
                                break;
                            case 56:
                                long offset19 = offset(typeAndOffsetAt);
                                codedInputStreamReader.requireWireType(1);
                                UnsafeUtil.putObject(offset19, obj, Long.valueOf(codedInputStream$StreamDecoder.readFixed64()));
                                setOneofPresent(fieldNumber, slowPositionForFieldNumber, obj);
                                break;
                            case 57:
                                long offset20 = offset(typeAndOffsetAt);
                                codedInputStreamReader.requireWireType(5);
                                UnsafeUtil.putObject(offset20, obj, Integer.valueOf(codedInputStream$StreamDecoder.readFixed32()));
                                setOneofPresent(fieldNumber, slowPositionForFieldNumber, obj);
                                break;
                            case 58:
                                long offset21 = offset(typeAndOffsetAt);
                                codedInputStreamReader.requireWireType(0);
                                UnsafeUtil.putObject(offset21, obj, Boolean.valueOf(codedInputStream$StreamDecoder.readBool()));
                                setOneofPresent(fieldNumber, slowPositionForFieldNumber, obj);
                                break;
                            case 59:
                                readString(obj, typeAndOffsetAt, codedInputStreamReader);
                                setOneofPresent(fieldNumber, slowPositionForFieldNumber, obj);
                                break;
                            case 60:
                                MessageLite messageLite3 = (MessageLite) mutableOneofMessageFieldForMerge(fieldNumber, slowPositionForFieldNumber, obj);
                                Schema messageFieldSchema3 = getMessageFieldSchema(slowPositionForFieldNumber);
                                codedInputStreamReader.requireWireType(2);
                                codedInputStreamReader.mergeMessageFieldInternal(messageLite3, messageFieldSchema3, extensionRegistryLite);
                                storeOneofMessageField(fieldNumber, slowPositionForFieldNumber, obj, messageLite3);
                                break;
                            case 61:
                                UnsafeUtil.putObject(offset(typeAndOffsetAt), obj, codedInputStreamReader.readBytes());
                                setOneofPresent(fieldNumber, slowPositionForFieldNumber, obj);
                                break;
                            case 62:
                                long offset22 = offset(typeAndOffsetAt);
                                codedInputStreamReader.requireWireType(0);
                                UnsafeUtil.putObject(offset22, obj, Integer.valueOf(codedInputStream$StreamDecoder.readUInt32()));
                                setOneofPresent(fieldNumber, slowPositionForFieldNumber, obj);
                                break;
                            case 63:
                                codedInputStreamReader.requireWireType(0);
                                int readEnum2 = codedInputStream$StreamDecoder.readEnum();
                                Internal.EnumVerifier enumFieldVerifier2 = getEnumFieldVerifier(slowPositionForFieldNumber);
                                if (enumFieldVerifier2 != null && !enumFieldVerifier2.isInRange(readEnum2)) {
                                    obj2 = SchemaUtil.storeUnknownEnum(obj, fieldNumber, readEnum2, obj2, unknownFieldSetLiteSchema);
                                    break;
                                }
                                UnsafeUtil.putObject(offset(typeAndOffsetAt), obj, Integer.valueOf(readEnum2));
                                setOneofPresent(fieldNumber, slowPositionForFieldNumber, obj);
                                break;
                            case 64:
                                long offset23 = offset(typeAndOffsetAt);
                                codedInputStreamReader.requireWireType(5);
                                UnsafeUtil.putObject(offset23, obj, Integer.valueOf(codedInputStream$StreamDecoder.readSFixed32()));
                                setOneofPresent(fieldNumber, slowPositionForFieldNumber, obj);
                                break;
                            case 65:
                                long offset24 = offset(typeAndOffsetAt);
                                codedInputStreamReader.requireWireType(1);
                                UnsafeUtil.putObject(offset24, obj, Long.valueOf(codedInputStream$StreamDecoder.readSFixed64()));
                                setOneofPresent(fieldNumber, slowPositionForFieldNumber, obj);
                                break;
                            case 66:
                                long offset25 = offset(typeAndOffsetAt);
                                codedInputStreamReader.requireWireType(0);
                                UnsafeUtil.putObject(offset25, obj, Integer.valueOf(codedInputStream$StreamDecoder.readSInt32()));
                                setOneofPresent(fieldNumber, slowPositionForFieldNumber, obj);
                                break;
                            case 67:
                                long offset26 = offset(typeAndOffsetAt);
                                codedInputStreamReader.requireWireType(0);
                                UnsafeUtil.putObject(offset26, obj, Long.valueOf(codedInputStream$StreamDecoder.readSInt64()));
                                setOneofPresent(fieldNumber, slowPositionForFieldNumber, obj);
                                break;
                            case 68:
                                MessageLite messageLite4 = (MessageLite) mutableOneofMessageFieldForMerge(fieldNumber, slowPositionForFieldNumber, obj);
                                Schema messageFieldSchema4 = getMessageFieldSchema(slowPositionForFieldNumber);
                                codedInputStreamReader.requireWireType(3);
                                codedInputStreamReader.mergeGroupFieldInternal(messageLite4, messageFieldSchema4, extensionRegistryLite);
                                storeOneofMessageField(fieldNumber, slowPositionForFieldNumber, obj, messageLite4);
                                break;
                            default:
                                if (obj2 == null) {
                                    unknownFieldSetLiteSchema.getClass();
                                    obj2 = UnknownFieldSetLiteSchema.getBuilderFromMessage(obj);
                                }
                                unknownFieldSetLiteSchema.getClass();
                                if (!UnknownFieldSetLiteSchema.mergeOneFieldFrom(obj2, codedInputStreamReader)) {
                                    Object obj3 = obj2;
                                    while (i2 < i) {
                                        obj3 = filterMapUnknownEnumValues(obj, iArr[i2], obj3, unknownFieldSetLiteSchema, obj);
                                        i2++;
                                    }
                                    if (obj3 != null) {
                                        ((GeneratedMessageLite) obj).unknownFields = (UnknownFieldSetLite) obj3;
                                        return;
                                    }
                                    return;
                                }
                                break;
                        }
                    } catch (InvalidProtocolBufferException.InvalidWireTypeException unused) {
                        unknownFieldSetLiteSchema.getClass();
                        if (obj2 == null) {
                            obj2 = UnknownFieldSetLiteSchema.getBuilderFromMessage(obj);
                        }
                        if (!UnknownFieldSetLiteSchema.mergeOneFieldFrom(obj2, codedInputStreamReader)) {
                            Object obj4 = obj2;
                            while (i2 < i) {
                                obj4 = filterMapUnknownEnumValues(obj, iArr[i2], obj4, unknownFieldSetLiteSchema, obj);
                                i2++;
                            }
                            if (obj4 != null) {
                                ((GeneratedMessageLite) obj).unknownFields = (UnknownFieldSetLite) obj4;
                                return;
                            }
                            return;
                        }
                    }
                } else {
                    if (fieldNumber == Integer.MAX_VALUE) {
                        Object obj5 = obj2;
                        while (i2 < i) {
                            obj5 = filterMapUnknownEnumValues(obj, iArr[i2], obj5, unknownFieldSetLiteSchema, obj);
                            i2++;
                        }
                        if (obj5 != null) {
                            unknownFieldSetLiteSchema.getClass();
                            ((GeneratedMessageLite) obj).unknownFields = (UnknownFieldSetLite) obj5;
                            return;
                        }
                        return;
                    }
                    unknownFieldSetLiteSchema.getClass();
                    if (obj2 == null) {
                        obj2 = UnknownFieldSetLiteSchema.getBuilderFromMessage(obj);
                    }
                    if (!UnknownFieldSetLiteSchema.mergeOneFieldFrom(obj2, codedInputStreamReader)) {
                        Object obj6 = obj2;
                        while (i2 < i) {
                            obj6 = filterMapUnknownEnumValues(obj, iArr[i2], obj6, unknownFieldSetLiteSchema, obj);
                            i2++;
                        }
                        if (obj6 != null) {
                            ((GeneratedMessageLite) obj).unknownFields = (UnknownFieldSetLite) obj6;
                            return;
                        }
                        return;
                    }
                }
            } catch (Throwable th) {
                Object obj7 = obj2;
                while (i2 < i) {
                    obj7 = filterMapUnknownEnumValues(obj, iArr[i2], obj7, unknownFieldSetLiteSchema, obj);
                    i2++;
                }
                if (obj7 != null) {
                    unknownFieldSetLiteSchema.getClass();
                    ((GeneratedMessageLite) obj).unknownFields = (UnknownFieldSetLite) obj7;
                }
                throw th;
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:41:0x0099, code lost:
    
        r9.put(r1, r3);
     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x009c, code lost:
    
        r10.popLimit(r0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x009f, code lost:
    
        return;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void mergeMap(java.lang.Object r8, int r9, java.lang.Object r10, com.google.protobuf.ExtensionRegistryLite r11, com.google.protobuf.CodedInputStreamReader r12) {
        /*
            r7 = this;
            int r9 = r7.typeAndOffsetAt(r9)
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r9 = r9 & r0
            long r0 = (long) r9
            java.lang.Object r9 = com.google.protobuf.UnsafeUtil.getObject(r0, r8)
            com.google.protobuf.MapFieldSchemaLite r7 = r7.mapFieldSchema
            if (r9 != 0) goto L1e
            r7.getClass()
            com.google.protobuf.MapFieldLite r9 = com.google.protobuf.MapFieldLite.EMPTY_MAP_FIELD
            com.google.protobuf.MapFieldLite r9 = r9.mutableCopy()
            com.google.protobuf.UnsafeUtil.putObject(r0, r8, r9)
            goto L37
        L1e:
            r7.getClass()
            r2 = r9
            com.google.protobuf.MapFieldLite r2 = (com.google.protobuf.MapFieldLite) r2
            boolean r2 = r2.isMutable()
            if (r2 != 0) goto L37
            com.google.protobuf.MapFieldLite r2 = com.google.protobuf.MapFieldLite.EMPTY_MAP_FIELD
            com.google.protobuf.MapFieldLite r2 = r2.mutableCopy()
            com.google.protobuf.MapFieldSchemaLite.mergeFrom(r2, r9)
            com.google.protobuf.UnsafeUtil.putObject(r0, r8, r2)
            r9 = r2
        L37:
            r7.getClass()
            com.google.protobuf.MapFieldLite r9 = (com.google.protobuf.MapFieldLite) r9
            com.google.protobuf.MapEntryLite r10 = (com.google.protobuf.MapEntryLite) r10
            com.google.protobuf.MapEntryLite$Metadata r7 = r10.metadata
            r8 = 2
            r12.requireWireType(r8)
            com.google.protobuf.CodedInputStream$StreamDecoder r10 = r12.input
            int r0 = r10.readUInt32()
            int r0 = r10.pushLimit(r0)
            java.lang.Object r1 = r7.defaultKey
            java.lang.Object r2 = r7.defaultValue
            r3 = r2
        L53:
            int r4 = r12.getFieldNumber()     // Catch: java.lang.Throwable -> L77
            r5 = 2147483647(0x7fffffff, float:NaN)
            if (r4 == r5) goto L99
            boolean r5 = r10.isAtEnd()     // Catch: java.lang.Throwable -> L77
            if (r5 == 0) goto L63
            goto L99
        L63:
            r5 = 1
            java.lang.String r6 = "Unable to parse map entry."
            if (r4 == r5) goto L84
            if (r4 == r8) goto L79
            boolean r4 = r12.skipField()     // Catch: java.lang.Throwable -> L77 com.google.protobuf.InvalidProtocolBufferException.InvalidWireTypeException -> L8c
            if (r4 == 0) goto L71
            goto L53
        L71:
            com.google.protobuf.InvalidProtocolBufferException r4 = new com.google.protobuf.InvalidProtocolBufferException     // Catch: java.lang.Throwable -> L77 com.google.protobuf.InvalidProtocolBufferException.InvalidWireTypeException -> L8c
            r4.<init>(r6)     // Catch: java.lang.Throwable -> L77 com.google.protobuf.InvalidProtocolBufferException.InvalidWireTypeException -> L8c
            throw r4     // Catch: java.lang.Throwable -> L77 com.google.protobuf.InvalidProtocolBufferException.InvalidWireTypeException -> L8c
        L77:
            r7 = move-exception
            goto La0
        L79:
            com.google.protobuf.WireFormat$FieldType r4 = r7.valueType     // Catch: java.lang.Throwable -> L77 com.google.protobuf.InvalidProtocolBufferException.InvalidWireTypeException -> L8c
            java.lang.Class r5 = r2.getClass()     // Catch: java.lang.Throwable -> L77 com.google.protobuf.InvalidProtocolBufferException.InvalidWireTypeException -> L8c
            java.lang.Object r3 = r12.readField(r4, r5, r11)     // Catch: java.lang.Throwable -> L77 com.google.protobuf.InvalidProtocolBufferException.InvalidWireTypeException -> L8c
            goto L53
        L84:
            com.google.protobuf.WireFormat$FieldType r4 = r7.keyType     // Catch: java.lang.Throwable -> L77 com.google.protobuf.InvalidProtocolBufferException.InvalidWireTypeException -> L8c
            r5 = 0
            java.lang.Object r1 = r12.readField(r4, r5, r5)     // Catch: java.lang.Throwable -> L77 com.google.protobuf.InvalidProtocolBufferException.InvalidWireTypeException -> L8c
            goto L53
        L8c:
            boolean r4 = r12.skipField()     // Catch: java.lang.Throwable -> L77
            if (r4 == 0) goto L93
            goto L53
        L93:
            com.google.protobuf.InvalidProtocolBufferException r7 = new com.google.protobuf.InvalidProtocolBufferException     // Catch: java.lang.Throwable -> L77
            r7.<init>(r6)     // Catch: java.lang.Throwable -> L77
            throw r7     // Catch: java.lang.Throwable -> L77
        L99:
            r9.put(r1, r3)     // Catch: java.lang.Throwable -> L77
            r10.popLimit(r0)
            return
        La0:
            r10.popLimit(r0)
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.MessageSchema.mergeMap(java.lang.Object, int, java.lang.Object, com.google.protobuf.ExtensionRegistryLite, com.google.protobuf.CodedInputStreamReader):void");
    }

    public final void mergeMessage(int i, Object obj, Object obj2) {
        if (isFieldPresent(i, obj2)) {
            long typeAndOffsetAt = typeAndOffsetAt(i) & 1048575;
            Unsafe unsafe = UNSAFE;
            Object object = unsafe.getObject(obj2, typeAndOffsetAt);
            if (object == null) {
                throw new IllegalStateException("Source subfield " + this.buffer[i] + " is present but null: " + obj2);
            }
            Schema messageFieldSchema = getMessageFieldSchema(i);
            if (!isFieldPresent(i, obj)) {
                if (isMutable(object)) {
                    GeneratedMessageLite newInstance = messageFieldSchema.newInstance();
                    messageFieldSchema.mergeFrom(newInstance, object);
                    unsafe.putObject(obj, typeAndOffsetAt, newInstance);
                } else {
                    unsafe.putObject(obj, typeAndOffsetAt, object);
                }
                setFieldPresent(i, obj);
                return;
            }
            Object object2 = unsafe.getObject(obj, typeAndOffsetAt);
            if (!isMutable(object2)) {
                GeneratedMessageLite newInstance2 = messageFieldSchema.newInstance();
                messageFieldSchema.mergeFrom(newInstance2, object2);
                unsafe.putObject(obj, typeAndOffsetAt, newInstance2);
                object2 = newInstance2;
            }
            messageFieldSchema.mergeFrom(object2, object);
        }
    }

    public final void mergeOneofMessage(int i, Object obj, Object obj2) {
        int[] iArr = this.buffer;
        int i2 = iArr[i];
        if (isOneofPresent(i2, i, obj2)) {
            long typeAndOffsetAt = typeAndOffsetAt(i) & 1048575;
            Unsafe unsafe = UNSAFE;
            Object object = unsafe.getObject(obj2, typeAndOffsetAt);
            if (object == null) {
                throw new IllegalStateException("Source subfield " + iArr[i] + " is present but null: " + obj2);
            }
            Schema messageFieldSchema = getMessageFieldSchema(i);
            if (!isOneofPresent(i2, i, obj)) {
                if (isMutable(object)) {
                    GeneratedMessageLite newInstance = messageFieldSchema.newInstance();
                    messageFieldSchema.mergeFrom(newInstance, object);
                    unsafe.putObject(obj, typeAndOffsetAt, newInstance);
                } else {
                    unsafe.putObject(obj, typeAndOffsetAt, object);
                }
                setOneofPresent(i2, i, obj);
                return;
            }
            Object object2 = unsafe.getObject(obj, typeAndOffsetAt);
            if (!isMutable(object2)) {
                GeneratedMessageLite newInstance2 = messageFieldSchema.newInstance();
                messageFieldSchema.mergeFrom(newInstance2, object2);
                unsafe.putObject(obj, typeAndOffsetAt, newInstance2);
                object2 = newInstance2;
            }
            messageFieldSchema.mergeFrom(object2, object);
        }
    }

    public final Object mutableMessageFieldForMerge(int i, Object obj) {
        Schema messageFieldSchema = getMessageFieldSchema(i);
        long typeAndOffsetAt = typeAndOffsetAt(i) & 1048575;
        if (!isFieldPresent(i, obj)) {
            return messageFieldSchema.newInstance();
        }
        Object object = UNSAFE.getObject(obj, typeAndOffsetAt);
        if (isMutable(object)) {
            return object;
        }
        GeneratedMessageLite newInstance = messageFieldSchema.newInstance();
        if (object != null) {
            messageFieldSchema.mergeFrom(newInstance, object);
        }
        return newInstance;
    }

    public final Object mutableOneofMessageFieldForMerge(int i, int i2, Object obj) {
        Schema messageFieldSchema = getMessageFieldSchema(i2);
        if (!isOneofPresent(i, i2, obj)) {
            return messageFieldSchema.newInstance();
        }
        Object object = UNSAFE.getObject(obj, typeAndOffsetAt(i2) & 1048575);
        if (isMutable(object)) {
            return object;
        }
        GeneratedMessageLite newInstance = messageFieldSchema.newInstance();
        if (object != null) {
            messageFieldSchema.mergeFrom(newInstance, object);
        }
        return newInstance;
    }

    @Override // com.google.protobuf.Schema
    public final GeneratedMessageLite newInstance() {
        this.newInstanceSchema.getClass();
        return ((GeneratedMessageLite) this.defaultInstance).newMutableInstance$1();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v5, types: [int] */
    public final int parseMapField(Object obj, byte[] bArr, int i, int i2, int i3, long j, ArrayDecoders.Registers registers) {
        Unsafe unsafe = UNSAFE;
        Object mapFieldDefaultEntry = getMapFieldDefaultEntry(i3);
        Object object = unsafe.getObject(obj, j);
        this.mapFieldSchema.getClass();
        if (!((MapFieldLite) object).isMutable()) {
            MapFieldLite mutableCopy = MapFieldLite.EMPTY_MAP_FIELD.mutableCopy();
            MapFieldSchemaLite.mergeFrom(mutableCopy, object);
            unsafe.putObject(obj, j, mutableCopy);
            object = mutableCopy;
        }
        MapEntryLite.Metadata metadata = ((MapEntryLite) mapFieldDefaultEntry).metadata;
        MapFieldLite mapFieldLite = (MapFieldLite) object;
        int decodeVarint32 = ArrayDecoders.decodeVarint32(bArr, i, registers);
        int i4 = registers.int1;
        if (i4 < 0 || i4 > i2 - decodeVarint32) {
            throw InvalidProtocolBufferException.truncatedMessage();
        }
        int i5 = decodeVarint32 + i4;
        Object obj2 = metadata.defaultKey;
        Object obj3 = metadata.defaultValue;
        Object obj4 = obj2;
        Object obj5 = obj3;
        while (decodeVarint32 < i5) {
            int i6 = decodeVarint32 + 1;
            byte b = bArr[decodeVarint32];
            if (b < 0) {
                i6 = ArrayDecoders.decodeVarint32(b, bArr, i6, registers);
                b = registers.int1;
            }
            int i7 = b >>> 3;
            int i8 = b & 7;
            if (i7 != 1) {
                if (i7 == 2) {
                    WireFormat$FieldType wireFormat$FieldType = metadata.valueType;
                    if (i8 == wireFormat$FieldType.getWireType()) {
                        decodeVarint32 = decodeMapEntryValue(bArr, i6, i2, wireFormat$FieldType, obj3.getClass(), registers);
                        obj5 = registers.object1;
                    }
                }
                decodeVarint32 = ArrayDecoders.skipField(b, bArr, i6, i2, registers);
            } else {
                WireFormat$FieldType wireFormat$FieldType2 = metadata.keyType;
                if (i8 == wireFormat$FieldType2.getWireType()) {
                    decodeVarint32 = decodeMapEntryValue(bArr, i6, i2, wireFormat$FieldType2, null, registers);
                    obj4 = registers.object1;
                } else {
                    decodeVarint32 = ArrayDecoders.skipField(b, bArr, i6, i2, registers);
                }
            }
        }
        if (decodeVarint32 != i5) {
            throw InvalidProtocolBufferException.parseFailure();
        }
        mapFieldLite.put(obj4, obj5);
        return i5;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public final int parseOneofField(Object obj, byte[] bArr, int i, int i2, int i3, int i4, int i5, int i6, int i7, long j, int i8, ArrayDecoders.Registers registers) {
        int mergeMessageField;
        Unsafe unsafe = UNSAFE;
        long j2 = this.buffer[i8 + 2] & 1048575;
        switch (i7) {
            case 51:
                if (i5 == 1) {
                    unsafe.putObject(obj, j, Double.valueOf(Double.longBitsToDouble(ArrayDecoders.decodeFixed64(i, bArr))));
                    int i9 = i + 8;
                    unsafe.putInt(obj, j2, i4);
                    return i9;
                }
                return i;
            case 52:
                if (i5 == 5) {
                    unsafe.putObject(obj, j, Float.valueOf(Float.intBitsToFloat(ArrayDecoders.decodeFixed32(i, bArr))));
                    int i10 = i + 4;
                    unsafe.putInt(obj, j2, i4);
                    return i10;
                }
                return i;
            case 53:
            case 54:
                if (i5 == 0) {
                    int decodeVarint64 = ArrayDecoders.decodeVarint64(bArr, i, registers);
                    unsafe.putObject(obj, j, Long.valueOf(registers.long1));
                    unsafe.putInt(obj, j2, i4);
                    return decodeVarint64;
                }
                return i;
            case 55:
            case 62:
                if (i5 == 0) {
                    int decodeVarint32 = ArrayDecoders.decodeVarint32(bArr, i, registers);
                    unsafe.putObject(obj, j, Integer.valueOf(registers.int1));
                    unsafe.putInt(obj, j2, i4);
                    return decodeVarint32;
                }
                return i;
            case 56:
            case 65:
                if (i5 == 1) {
                    unsafe.putObject(obj, j, Long.valueOf(ArrayDecoders.decodeFixed64(i, bArr)));
                    int i11 = i + 8;
                    unsafe.putInt(obj, j2, i4);
                    return i11;
                }
                return i;
            case 57:
            case 64:
                if (i5 == 5) {
                    unsafe.putObject(obj, j, Integer.valueOf(ArrayDecoders.decodeFixed32(i, bArr)));
                    int i12 = i + 4;
                    unsafe.putInt(obj, j2, i4);
                    return i12;
                }
                return i;
            case 58:
                if (i5 == 0) {
                    int decodeVarint642 = ArrayDecoders.decodeVarint64(bArr, i, registers);
                    unsafe.putObject(obj, j, Boolean.valueOf(registers.long1 != 0));
                    unsafe.putInt(obj, j2, i4);
                    return decodeVarint642;
                }
                return i;
            case 59:
                if (i5 == 2) {
                    int decodeVarint322 = ArrayDecoders.decodeVarint32(bArr, i, registers);
                    int i13 = registers.int1;
                    if (i13 == 0) {
                        unsafe.putObject(obj, j, "");
                    } else {
                        if ((i6 & 536870912) != 0) {
                            if (!Utf8.processor.isValidUtf8(bArr, decodeVarint322, decodeVarint322 + i13)) {
                                throw InvalidProtocolBufferException.invalidUtf8();
                            }
                        }
                        unsafe.putObject(obj, j, new String(bArr, decodeVarint322, i13, Internal.UTF_8));
                        decodeVarint322 += i13;
                    }
                    unsafe.putInt(obj, j2, i4);
                    return decodeVarint322;
                }
                return i;
            case 60:
                if (i5 == 2) {
                    Object mutableOneofMessageFieldForMerge = mutableOneofMessageFieldForMerge(i4, i8, obj);
                    mergeMessageField = ArrayDecoders.mergeMessageField(mutableOneofMessageFieldForMerge, getMessageFieldSchema(i8), bArr, i, i2, registers);
                    storeOneofMessageField(i4, i8, obj, mutableOneofMessageFieldForMerge);
                    break;
                }
                return i;
            case 61:
                if (i5 == 2) {
                    int decodeBytes = ArrayDecoders.decodeBytes(bArr, i, registers);
                    unsafe.putObject(obj, j, registers.object1);
                    unsafe.putInt(obj, j2, i4);
                    return decodeBytes;
                }
                return i;
            case 63:
                if (i5 == 0) {
                    int decodeVarint323 = ArrayDecoders.decodeVarint32(bArr, i, registers);
                    int i14 = registers.int1;
                    Internal.EnumVerifier enumFieldVerifier = getEnumFieldVerifier(i8);
                    if (enumFieldVerifier == null || enumFieldVerifier.isInRange(i14)) {
                        unsafe.putObject(obj, j, Integer.valueOf(i14));
                        unsafe.putInt(obj, j2, i4);
                    } else {
                        getMutableUnknownFields(obj).storeField(i3, Long.valueOf(i14));
                    }
                    return decodeVarint323;
                }
                return i;
            case 66:
                if (i5 == 0) {
                    int decodeVarint324 = ArrayDecoders.decodeVarint32(bArr, i, registers);
                    unsafe.putObject(obj, j, Integer.valueOf(CodedInputStream$StreamDecoder.decodeZigZag32(registers.int1)));
                    unsafe.putInt(obj, j2, i4);
                    return decodeVarint324;
                }
                return i;
            case 67:
                if (i5 == 0) {
                    int decodeVarint643 = ArrayDecoders.decodeVarint64(bArr, i, registers);
                    unsafe.putObject(obj, j, Long.valueOf(CodedInputStream$StreamDecoder.decodeZigZag64(registers.long1)));
                    unsafe.putInt(obj, j2, i4);
                    return decodeVarint643;
                }
                return i;
            case 68:
                if (i5 == 3) {
                    Object mutableOneofMessageFieldForMerge2 = mutableOneofMessageFieldForMerge(i4, i8, obj);
                    mergeMessageField = ((MessageSchema) getMessageFieldSchema(i8)).parseProto2Message(mutableOneofMessageFieldForMerge2, bArr, i, i2, (i3 & (-8)) | 4, registers);
                    registers.object1 = mutableOneofMessageFieldForMerge2;
                    storeOneofMessageField(i4, i8, obj, mutableOneofMessageFieldForMerge2);
                    break;
                }
                return i;
            default:
                return i;
        }
        return mergeMessageField;
    }

    /* JADX WARN: Code restructure failed: missing block: B:187:0x03ba, code lost:
    
        if (r0 != r31) goto L117;
     */
    /* JADX WARN: Code restructure failed: missing block: B:188:0x03bc, code lost:
    
        r15 = r28;
        r14 = r29;
        r12 = r30;
        r3 = r31;
        r13 = r32;
        r11 = r33;
        r9 = r34;
     */
    /* JADX WARN: Code restructure failed: missing block: B:189:0x03d6, code lost:
    
        r7 = r31;
        r6 = r33;
        r2 = r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:194:0x0405, code lost:
    
        if (r0 != r15) goto L117;
     */
    /* JADX WARN: Code restructure failed: missing block: B:196:0x0429, code lost:
    
        if (r0 != r15) goto L117;
     */
    /* JADX WARN: Failed to find 'out' block for switch in B:22:0x00a8. Please report as an issue. */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final int parseProto2Message(java.lang.Object r29, byte[] r30, int r31, int r32, int r33, com.google.protobuf.ArrayDecoders.Registers r34) {
        /*
            Method dump skipped, instructions count: 1246
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.MessageSchema.parseProto2Message(java.lang.Object, byte[], int, int, int, com.google.protobuf.ArrayDecoders$Registers):int");
    }

    /* JADX WARN: Removed duplicated region for block: B:154:0x0271  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:117:0x0284 -> B:110:0x0259). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final int parseRepeatedField(java.lang.Object r16, byte[] r17, int r18, int r19, int r20, int r21, int r22, int r23, long r24, int r26, long r27, com.google.protobuf.ArrayDecoders.Registers r29) {
        /*
            Method dump skipped, instructions count: 1256
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.MessageSchema.parseRepeatedField(java.lang.Object, byte[], int, int, int, int, int, int, long, int, long, com.google.protobuf.ArrayDecoders$Registers):int");
    }

    public final void readGroupList(Object obj, long j, CodedInputStreamReader codedInputStreamReader, Schema schema, ExtensionRegistryLite extensionRegistryLite) {
        int readTag;
        List mutableListAt = this.listFieldSchema.mutableListAt(j, obj);
        int i = codedInputStreamReader.tag;
        if ((i & 7) != 3) {
            throw InvalidProtocolBufferException.invalidWireType();
        }
        do {
            GeneratedMessageLite newInstance = schema.newInstance();
            codedInputStreamReader.mergeGroupFieldInternal(newInstance, schema, extensionRegistryLite);
            schema.makeImmutable(newInstance);
            mutableListAt.add(newInstance);
            CodedInputStream$StreamDecoder codedInputStream$StreamDecoder = codedInputStreamReader.input;
            if (codedInputStream$StreamDecoder.isAtEnd() || codedInputStreamReader.nextTag != 0) {
                return;
            } else {
                readTag = codedInputStream$StreamDecoder.readTag();
            }
        } while (readTag == i);
        codedInputStreamReader.nextTag = readTag;
    }

    public final void readMessageList(Object obj, int i, CodedInputStreamReader codedInputStreamReader, Schema schema, ExtensionRegistryLite extensionRegistryLite) {
        int readTag;
        List mutableListAt = this.listFieldSchema.mutableListAt(i & 1048575, obj);
        int i2 = codedInputStreamReader.tag;
        if ((i2 & 7) != 2) {
            throw InvalidProtocolBufferException.invalidWireType();
        }
        do {
            GeneratedMessageLite newInstance = schema.newInstance();
            codedInputStreamReader.mergeMessageFieldInternal(newInstance, schema, extensionRegistryLite);
            schema.makeImmutable(newInstance);
            mutableListAt.add(newInstance);
            CodedInputStream$StreamDecoder codedInputStream$StreamDecoder = codedInputStreamReader.input;
            if (codedInputStream$StreamDecoder.isAtEnd() || codedInputStreamReader.nextTag != 0) {
                return;
            } else {
                readTag = codedInputStream$StreamDecoder.readTag();
            }
        } while (readTag == i2);
        codedInputStreamReader.nextTag = readTag;
    }

    public final void readString(Object obj, int i, CodedInputStreamReader codedInputStreamReader) {
        if ((536870912 & i) != 0) {
            codedInputStreamReader.requireWireType(2);
            UnsafeUtil.putObject(i & 1048575, obj, codedInputStreamReader.input.readStringRequireUtf8());
        } else if (!this.lite) {
            UnsafeUtil.putObject(i & 1048575, obj, codedInputStreamReader.readBytes());
        } else {
            codedInputStreamReader.requireWireType(2);
            UnsafeUtil.putObject(i & 1048575, obj, codedInputStreamReader.input.readString());
        }
    }

    public final void readStringList(Object obj, int i, CodedInputStreamReader codedInputStreamReader) {
        boolean z = (536870912 & i) != 0;
        ListFieldSchema listFieldSchema = this.listFieldSchema;
        if (z) {
            codedInputStreamReader.readStringListInternal(listFieldSchema.mutableListAt(i & 1048575, obj), true);
        } else {
            codedInputStreamReader.readStringListInternal(listFieldSchema.mutableListAt(i & 1048575, obj), false);
        }
    }

    public final void setFieldPresent(int i, Object obj) {
        int i2 = this.buffer[i + 2];
        long j = 1048575 & i2;
        if (j == 1048575) {
            return;
        }
        UnsafeUtil.putInt(obj, j, (1 << (i2 >>> 20)) | UnsafeUtil.getInt(j, obj));
    }

    public final void setOneofPresent(int i, int i2, Object obj) {
        UnsafeUtil.putInt(obj, this.buffer[i2 + 2] & 1048575, i);
    }

    public final int slowPositionForFieldNumber(int i, int i2) {
        int[] iArr = this.buffer;
        int length = (iArr.length / 3) - 1;
        while (i2 <= length) {
            int i3 = (length + i2) >>> 1;
            int i4 = i3 * 3;
            int i5 = iArr[i4];
            if (i == i5) {
                return i4;
            }
            if (i < i5) {
                length = i3 - 1;
            } else {
                i2 = i3 + 1;
            }
        }
        return -1;
    }

    public final void storeMessageField(int i, Object obj, Object obj2) {
        UNSAFE.putObject(obj, typeAndOffsetAt(i) & 1048575, obj2);
        setFieldPresent(i, obj);
    }

    public final void storeOneofMessageField(int i, int i2, Object obj, Object obj2) {
        UNSAFE.putObject(obj, typeAndOffsetAt(i2) & 1048575, obj2);
        setOneofPresent(i, i2, obj);
    }

    public final int typeAndOffsetAt(int i) {
        return this.buffer[i + 1];
    }

    public final void writeMapHelper(CodedOutputStreamWriter codedOutputStreamWriter, int i, Object obj, int i2) {
        if (obj != null) {
            Object mapFieldDefaultEntry = getMapFieldDefaultEntry(i2);
            this.mapFieldSchema.getClass();
            MapEntryLite.Metadata metadata = ((MapEntryLite) mapFieldDefaultEntry).metadata;
            CodedOutputStream codedOutputStream = codedOutputStreamWriter.output;
            codedOutputStream.getClass();
            for (Map.Entry entry : ((MapFieldLite) obj).entrySet()) {
                codedOutputStream.writeTag(i, 2);
                codedOutputStream.writeUInt32NoTag(MapEntryLite.computeSerializedSize(metadata, entry.getKey(), entry.getValue()));
                Object key = entry.getKey();
                Object value = entry.getValue();
                FieldSet.writeElement(codedOutputStream, metadata.keyType, 1, key);
                FieldSet.writeElement(codedOutputStream, metadata.valueType, 2, value);
            }
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    @Override // com.google.protobuf.Schema
    public final void writeTo(Object obj, CodedOutputStreamWriter codedOutputStreamWriter) {
        int i;
        int i2;
        boolean z;
        boolean z2;
        char c;
        boolean z3;
        boolean z4;
        codedOutputStreamWriter.getClass();
        boolean z5 = this.proto3;
        UnknownFieldSetLiteSchema unknownFieldSetLiteSchema = this.unknownFieldSchema;
        int i3 = 1048575;
        int[] iArr = this.buffer;
        CodedOutputStream codedOutputStream = codedOutputStreamWriter.output;
        if (z5) {
            int length = iArr.length;
            for (int i4 = 0; i4 < length; i4 += 3) {
                int typeAndOffsetAt = typeAndOffsetAt(i4);
                int i5 = iArr[i4];
                switch (type(typeAndOffsetAt)) {
                    case 0:
                        if (isFieldPresent(i4, obj)) {
                            double d = UnsafeUtil.MEMORY_ACCESSOR.getDouble(typeAndOffsetAt & 1048575, obj);
                            codedOutputStream.getClass();
                            codedOutputStream.writeFixed64(Double.doubleToRawLongBits(d), i5);
                            break;
                        } else {
                            break;
                        }
                    case 1:
                        if (isFieldPresent(i4, obj)) {
                            float f = UnsafeUtil.MEMORY_ACCESSOR.getFloat(typeAndOffsetAt & 1048575, obj);
                            codedOutputStream.getClass();
                            codedOutputStream.writeFixed32(i5, Float.floatToRawIntBits(f));
                            break;
                        } else {
                            break;
                        }
                    case 2:
                        if (isFieldPresent(i4, obj)) {
                            codedOutputStream.writeUInt64(UnsafeUtil.getLong(typeAndOffsetAt & 1048575, obj), i5);
                            break;
                        } else {
                            break;
                        }
                    case 3:
                        if (isFieldPresent(i4, obj)) {
                            codedOutputStream.writeUInt64(UnsafeUtil.getLong(typeAndOffsetAt & 1048575, obj), i5);
                            break;
                        } else {
                            break;
                        }
                    case 4:
                        if (isFieldPresent(i4, obj)) {
                            codedOutputStream.writeInt32(i5, UnsafeUtil.getInt(typeAndOffsetAt & 1048575, obj));
                            break;
                        } else {
                            break;
                        }
                    case 5:
                        if (isFieldPresent(i4, obj)) {
                            codedOutputStream.writeFixed64(UnsafeUtil.getLong(typeAndOffsetAt & 1048575, obj), i5);
                            break;
                        } else {
                            break;
                        }
                    case 6:
                        if (isFieldPresent(i4, obj)) {
                            codedOutputStream.writeFixed32(i5, UnsafeUtil.getInt(typeAndOffsetAt & 1048575, obj));
                            break;
                        } else {
                            break;
                        }
                    case 7:
                        if (isFieldPresent(i4, obj)) {
                            codedOutputStream.writeBool(i5, UnsafeUtil.MEMORY_ACCESSOR.getBoolean(typeAndOffsetAt & 1048575, obj));
                            break;
                        } else {
                            break;
                        }
                    case 8:
                        if (isFieldPresent(i4, obj)) {
                            writeString(i5, UnsafeUtil.getObject(typeAndOffsetAt & 1048575, obj), codedOutputStreamWriter);
                            break;
                        } else {
                            break;
                        }
                    case 9:
                        if (isFieldPresent(i4, obj)) {
                            codedOutputStream.writeMessage(i5, (MessageLite) UnsafeUtil.getObject(typeAndOffsetAt & 1048575, obj), getMessageFieldSchema(i4));
                            break;
                        } else {
                            break;
                        }
                    case 10:
                        if (isFieldPresent(i4, obj)) {
                            codedOutputStreamWriter.writeBytes(i5, (ByteString) UnsafeUtil.getObject(typeAndOffsetAt & 1048575, obj));
                            break;
                        } else {
                            break;
                        }
                    case ViewNode.TRANSLATIONX_FIELD_NUMBER /* 11 */:
                        if (isFieldPresent(i4, obj)) {
                            codedOutputStream.writeUInt32(i5, UnsafeUtil.getInt(typeAndOffsetAt & 1048575, obj));
                            break;
                        } else {
                            break;
                        }
                    case ViewNode.TRANSLATIONY_FIELD_NUMBER /* 12 */:
                        if (isFieldPresent(i4, obj)) {
                            codedOutputStream.writeInt32(i5, UnsafeUtil.getInt(typeAndOffsetAt & 1048575, obj));
                            break;
                        } else {
                            break;
                        }
                    case ViewNode.SCALEX_FIELD_NUMBER /* 13 */:
                        if (isFieldPresent(i4, obj)) {
                            codedOutputStream.writeFixed32(i5, UnsafeUtil.getInt(typeAndOffsetAt & 1048575, obj));
                            break;
                        } else {
                            break;
                        }
                    case ViewNode.SCALEY_FIELD_NUMBER /* 14 */:
                        if (isFieldPresent(i4, obj)) {
                            codedOutputStream.writeFixed64(UnsafeUtil.getLong(typeAndOffsetAt & 1048575, obj), i5);
                            break;
                        } else {
                            break;
                        }
                    case 15:
                        if (isFieldPresent(i4, obj)) {
                            int i6 = UnsafeUtil.getInt(typeAndOffsetAt & 1048575, obj);
                            codedOutputStream.writeUInt32(i5, (i6 >> 31) ^ (i6 << 1));
                            break;
                        } else {
                            break;
                        }
                    case 16:
                        if (isFieldPresent(i4, obj)) {
                            long j = UnsafeUtil.getLong(typeAndOffsetAt & 1048575, obj);
                            codedOutputStream.writeUInt64((j << 1) ^ (j >> 63), i5);
                            break;
                        } else {
                            break;
                        }
                    case ViewNode.CLIPCHILDREN_FIELD_NUMBER /* 17 */:
                        if (isFieldPresent(i4, obj)) {
                            codedOutputStreamWriter.writeGroup(i5, UnsafeUtil.getObject(typeAndOffsetAt & 1048575, obj), getMessageFieldSchema(i4));
                            break;
                        } else {
                            break;
                        }
                    case ViewNode.VISIBILITY_FIELD_NUMBER /* 18 */:
                        SchemaUtil.writeDoubleList(iArr[i4], (List) UnsafeUtil.getObject(typeAndOffsetAt & 1048575, obj), codedOutputStreamWriter, false);
                        break;
                    case ViewNode.ELEVATION_FIELD_NUMBER /* 19 */:
                        SchemaUtil.writeFloatList(iArr[i4], (List) UnsafeUtil.getObject(typeAndOffsetAt & 1048575, obj), codedOutputStreamWriter, false);
                        break;
                    case 20:
                        SchemaUtil.writeInt64List(iArr[i4], (List) UnsafeUtil.getObject(typeAndOffsetAt & 1048575, obj), codedOutputStreamWriter, false);
                        break;
                    case 21:
                        SchemaUtil.writeUInt64List(iArr[i4], (List) UnsafeUtil.getObject(typeAndOffsetAt & 1048575, obj), codedOutputStreamWriter, false);
                        break;
                    case 22:
                        SchemaUtil.writeInt32List(iArr[i4], (List) UnsafeUtil.getObject(typeAndOffsetAt & 1048575, obj), codedOutputStreamWriter, false);
                        break;
                    case 23:
                        SchemaUtil.writeFixed64List(iArr[i4], (List) UnsafeUtil.getObject(typeAndOffsetAt & 1048575, obj), codedOutputStreamWriter, false);
                        break;
                    case 24:
                        SchemaUtil.writeFixed32List(iArr[i4], (List) UnsafeUtil.getObject(typeAndOffsetAt & 1048575, obj), codedOutputStreamWriter, false);
                        break;
                    case 25:
                        SchemaUtil.writeBoolList(iArr[i4], (List) UnsafeUtil.getObject(typeAndOffsetAt & 1048575, obj), codedOutputStreamWriter, false);
                        break;
                    case 26:
                        SchemaUtil.writeStringList(iArr[i4], (List) UnsafeUtil.getObject(typeAndOffsetAt & 1048575, obj), codedOutputStreamWriter);
                        break;
                    case 27:
                        SchemaUtil.writeMessageList(iArr[i4], (List) UnsafeUtil.getObject(typeAndOffsetAt & 1048575, obj), codedOutputStreamWriter, getMessageFieldSchema(i4));
                        break;
                    case 28:
                        SchemaUtil.writeBytesList(iArr[i4], (List) UnsafeUtil.getObject(typeAndOffsetAt & 1048575, obj), codedOutputStreamWriter);
                        break;
                    case 29:
                        SchemaUtil.writeUInt32List(iArr[i4], (List) UnsafeUtil.getObject(typeAndOffsetAt & 1048575, obj), codedOutputStreamWriter, false);
                        break;
                    case 30:
                        SchemaUtil.writeEnumList(iArr[i4], (List) UnsafeUtil.getObject(typeAndOffsetAt & 1048575, obj), codedOutputStreamWriter, false);
                        break;
                    case 31:
                        SchemaUtil.writeSFixed32List(iArr[i4], (List) UnsafeUtil.getObject(typeAndOffsetAt & 1048575, obj), codedOutputStreamWriter, false);
                        break;
                    case 32:
                        SchemaUtil.writeSFixed64List(iArr[i4], (List) UnsafeUtil.getObject(typeAndOffsetAt & 1048575, obj), codedOutputStreamWriter, false);
                        break;
                    case 33:
                        SchemaUtil.writeSInt32List(iArr[i4], (List) UnsafeUtil.getObject(typeAndOffsetAt & 1048575, obj), codedOutputStreamWriter, false);
                        break;
                    case 34:
                        SchemaUtil.writeSInt64List(iArr[i4], (List) UnsafeUtil.getObject(typeAndOffsetAt & 1048575, obj), codedOutputStreamWriter, false);
                        break;
                    case 35:
                        SchemaUtil.writeDoubleList(iArr[i4], (List) UnsafeUtil.getObject(typeAndOffsetAt & 1048575, obj), codedOutputStreamWriter, true);
                        break;
                    case 36:
                        SchemaUtil.writeFloatList(iArr[i4], (List) UnsafeUtil.getObject(typeAndOffsetAt & 1048575, obj), codedOutputStreamWriter, true);
                        break;
                    case 37:
                        SchemaUtil.writeInt64List(iArr[i4], (List) UnsafeUtil.getObject(typeAndOffsetAt & 1048575, obj), codedOutputStreamWriter, true);
                        break;
                    case 38:
                        SchemaUtil.writeUInt64List(iArr[i4], (List) UnsafeUtil.getObject(typeAndOffsetAt & 1048575, obj), codedOutputStreamWriter, true);
                        break;
                    case 39:
                        SchemaUtil.writeInt32List(iArr[i4], (List) UnsafeUtil.getObject(typeAndOffsetAt & 1048575, obj), codedOutputStreamWriter, true);
                        break;
                    case 40:
                        SchemaUtil.writeFixed64List(iArr[i4], (List) UnsafeUtil.getObject(typeAndOffsetAt & 1048575, obj), codedOutputStreamWriter, true);
                        break;
                    case 41:
                        SchemaUtil.writeFixed32List(iArr[i4], (List) UnsafeUtil.getObject(typeAndOffsetAt & 1048575, obj), codedOutputStreamWriter, true);
                        break;
                    case 42:
                        SchemaUtil.writeBoolList(iArr[i4], (List) UnsafeUtil.getObject(typeAndOffsetAt & 1048575, obj), codedOutputStreamWriter, true);
                        break;
                    case 43:
                        SchemaUtil.writeUInt32List(iArr[i4], (List) UnsafeUtil.getObject(typeAndOffsetAt & 1048575, obj), codedOutputStreamWriter, true);
                        break;
                    case 44:
                        SchemaUtil.writeEnumList(iArr[i4], (List) UnsafeUtil.getObject(typeAndOffsetAt & 1048575, obj), codedOutputStreamWriter, true);
                        break;
                    case 45:
                        SchemaUtil.writeSFixed32List(iArr[i4], (List) UnsafeUtil.getObject(typeAndOffsetAt & 1048575, obj), codedOutputStreamWriter, true);
                        break;
                    case 46:
                        SchemaUtil.writeSFixed64List(iArr[i4], (List) UnsafeUtil.getObject(typeAndOffsetAt & 1048575, obj), codedOutputStreamWriter, true);
                        break;
                    case 47:
                        SchemaUtil.writeSInt32List(iArr[i4], (List) UnsafeUtil.getObject(typeAndOffsetAt & 1048575, obj), codedOutputStreamWriter, true);
                        break;
                    case 48:
                        SchemaUtil.writeSInt64List(iArr[i4], (List) UnsafeUtil.getObject(typeAndOffsetAt & 1048575, obj), codedOutputStreamWriter, true);
                        break;
                    case 49:
                        SchemaUtil.writeGroupList(iArr[i4], (List) UnsafeUtil.getObject(typeAndOffsetAt & 1048575, obj), codedOutputStreamWriter, getMessageFieldSchema(i4));
                        break;
                    case 50:
                        writeMapHelper(codedOutputStreamWriter, i5, UnsafeUtil.getObject(typeAndOffsetAt & 1048575, obj), i4);
                        break;
                    case 51:
                        if (isOneofPresent(i5, i4, obj)) {
                            double doubleValue = ((Double) UnsafeUtil.getObject(typeAndOffsetAt & 1048575, obj)).doubleValue();
                            codedOutputStream.getClass();
                            codedOutputStream.writeFixed64(Double.doubleToRawLongBits(doubleValue), i5);
                            break;
                        } else {
                            break;
                        }
                    case 52:
                        if (isOneofPresent(i5, i4, obj)) {
                            float floatValue = ((Float) UnsafeUtil.getObject(typeAndOffsetAt & 1048575, obj)).floatValue();
                            codedOutputStream.getClass();
                            codedOutputStream.writeFixed32(i5, Float.floatToRawIntBits(floatValue));
                            break;
                        } else {
                            break;
                        }
                    case 53:
                        if (isOneofPresent(i5, i4, obj)) {
                            codedOutputStream.writeUInt64(oneofLongAt(typeAndOffsetAt & 1048575, obj), i5);
                            break;
                        } else {
                            break;
                        }
                    case 54:
                        if (isOneofPresent(i5, i4, obj)) {
                            codedOutputStream.writeUInt64(oneofLongAt(typeAndOffsetAt & 1048575, obj), i5);
                            break;
                        } else {
                            break;
                        }
                    case 55:
                        if (isOneofPresent(i5, i4, obj)) {
                            codedOutputStream.writeInt32(i5, oneofIntAt(typeAndOffsetAt & 1048575, obj));
                            break;
                        } else {
                            break;
                        }
                    case 56:
                        if (isOneofPresent(i5, i4, obj)) {
                            codedOutputStream.writeFixed64(oneofLongAt(typeAndOffsetAt & 1048575, obj), i5);
                            break;
                        } else {
                            break;
                        }
                    case 57:
                        if (isOneofPresent(i5, i4, obj)) {
                            codedOutputStream.writeFixed32(i5, oneofIntAt(typeAndOffsetAt & 1048575, obj));
                            break;
                        } else {
                            break;
                        }
                    case 58:
                        if (isOneofPresent(i5, i4, obj)) {
                            codedOutputStream.writeBool(i5, ((Boolean) UnsafeUtil.getObject(typeAndOffsetAt & 1048575, obj)).booleanValue());
                            break;
                        } else {
                            break;
                        }
                    case 59:
                        if (isOneofPresent(i5, i4, obj)) {
                            writeString(i5, UnsafeUtil.getObject(typeAndOffsetAt & 1048575, obj), codedOutputStreamWriter);
                            break;
                        } else {
                            break;
                        }
                    case 60:
                        if (isOneofPresent(i5, i4, obj)) {
                            codedOutputStream.writeMessage(i5, (MessageLite) UnsafeUtil.getObject(typeAndOffsetAt & 1048575, obj), getMessageFieldSchema(i4));
                            break;
                        } else {
                            break;
                        }
                    case 61:
                        if (isOneofPresent(i5, i4, obj)) {
                            codedOutputStreamWriter.writeBytes(i5, (ByteString) UnsafeUtil.getObject(typeAndOffsetAt & 1048575, obj));
                            break;
                        } else {
                            break;
                        }
                    case 62:
                        if (isOneofPresent(i5, i4, obj)) {
                            codedOutputStream.writeUInt32(i5, oneofIntAt(typeAndOffsetAt & 1048575, obj));
                            break;
                        } else {
                            break;
                        }
                    case 63:
                        if (isOneofPresent(i5, i4, obj)) {
                            codedOutputStream.writeInt32(i5, oneofIntAt(typeAndOffsetAt & 1048575, obj));
                            break;
                        } else {
                            break;
                        }
                    case 64:
                        if (isOneofPresent(i5, i4, obj)) {
                            codedOutputStream.writeFixed32(i5, oneofIntAt(typeAndOffsetAt & 1048575, obj));
                            break;
                        } else {
                            break;
                        }
                    case 65:
                        if (isOneofPresent(i5, i4, obj)) {
                            codedOutputStream.writeFixed64(oneofLongAt(typeAndOffsetAt & 1048575, obj), i5);
                            break;
                        } else {
                            break;
                        }
                    case 66:
                        if (isOneofPresent(i5, i4, obj)) {
                            int oneofIntAt = oneofIntAt(typeAndOffsetAt & 1048575, obj);
                            codedOutputStream.writeUInt32(i5, (oneofIntAt >> 31) ^ (oneofIntAt << 1));
                            break;
                        } else {
                            break;
                        }
                    case 67:
                        if (isOneofPresent(i5, i4, obj)) {
                            long oneofLongAt = oneofLongAt(typeAndOffsetAt & 1048575, obj);
                            codedOutputStream.writeUInt64((oneofLongAt << 1) ^ (oneofLongAt >> 63), i5);
                            break;
                        } else {
                            break;
                        }
                    case 68:
                        if (isOneofPresent(i5, i4, obj)) {
                            codedOutputStreamWriter.writeGroup(i5, UnsafeUtil.getObject(typeAndOffsetAt & 1048575, obj), getMessageFieldSchema(i4));
                            break;
                        } else {
                            break;
                        }
                }
            }
            unknownFieldSetLiteSchema.getClass();
            ((GeneratedMessageLite) obj).unknownFields.writeTo(codedOutputStreamWriter);
            return;
        }
        int length2 = iArr.length;
        Unsafe unsafe = UNSAFE;
        int i7 = 1048575;
        int i8 = 0;
        int i9 = 0;
        while (i8 < length2) {
            int typeAndOffsetAt2 = typeAndOffsetAt(i8);
            int i10 = iArr[i8];
            int type = type(typeAndOffsetAt2);
            if (type <= 17) {
                int i11 = iArr[i8 + 2];
                i = length2;
                int i12 = i11 & i3;
                if (i12 != i7) {
                    i9 = unsafe.getInt(obj, i12);
                    i7 = i12;
                }
                i2 = 1 << (i11 >>> 20);
            } else {
                i = length2;
                i2 = 0;
            }
            long j2 = typeAndOffsetAt2 & i3;
            switch (type) {
                case 0:
                    z = false;
                    z2 = true;
                    c = '?';
                    if ((i2 & i9) != 0) {
                        double d2 = UnsafeUtil.MEMORY_ACCESSOR.getDouble(j2, obj);
                        codedOutputStream.getClass();
                        codedOutputStream.writeFixed64(Double.doubleToRawLongBits(d2), i10);
                        break;
                    } else {
                        break;
                    }
                case 1:
                    z = false;
                    z2 = true;
                    c = '?';
                    if ((i2 & i9) != 0) {
                        float f2 = UnsafeUtil.MEMORY_ACCESSOR.getFloat(j2, obj);
                        codedOutputStream.getClass();
                        codedOutputStream.writeFixed32(i10, Float.floatToRawIntBits(f2));
                        break;
                    } else {
                        break;
                    }
                case 2:
                    z = false;
                    z2 = true;
                    c = '?';
                    if ((i2 & i9) != 0) {
                        codedOutputStream.writeUInt64(unsafe.getLong(obj, j2), i10);
                        break;
                    } else {
                        break;
                    }
                case 3:
                    z = false;
                    z2 = true;
                    c = '?';
                    if ((i2 & i9) != 0) {
                        codedOutputStream.writeUInt64(unsafe.getLong(obj, j2), i10);
                        break;
                    } else {
                        break;
                    }
                case 4:
                    z = false;
                    z2 = true;
                    c = '?';
                    if ((i2 & i9) != 0) {
                        codedOutputStream.writeInt32(i10, unsafe.getInt(obj, j2));
                        break;
                    } else {
                        break;
                    }
                case 5:
                    z = false;
                    z2 = true;
                    c = '?';
                    if ((i2 & i9) != 0) {
                        codedOutputStream.writeFixed64(unsafe.getLong(obj, j2), i10);
                        break;
                    } else {
                        break;
                    }
                case 6:
                    z = false;
                    z2 = true;
                    c = '?';
                    if ((i2 & i9) != 0) {
                        codedOutputStream.writeFixed32(i10, unsafe.getInt(obj, j2));
                        break;
                    } else {
                        break;
                    }
                case 7:
                    z = false;
                    z2 = true;
                    c = '?';
                    if ((i2 & i9) != 0) {
                        codedOutputStream.writeBool(i10, UnsafeUtil.MEMORY_ACCESSOR.getBoolean(j2, obj));
                        break;
                    } else {
                        break;
                    }
                case 8:
                    z = false;
                    z2 = true;
                    c = '?';
                    if ((i2 & i9) != 0) {
                        writeString(i10, unsafe.getObject(obj, j2), codedOutputStreamWriter);
                        break;
                    } else {
                        break;
                    }
                case 9:
                    z = false;
                    z2 = true;
                    c = '?';
                    if ((i2 & i9) != 0) {
                        codedOutputStream.writeMessage(i10, (MessageLite) unsafe.getObject(obj, j2), getMessageFieldSchema(i8));
                        break;
                    } else {
                        break;
                    }
                case 10:
                    z = false;
                    z2 = true;
                    c = '?';
                    if ((i2 & i9) != 0) {
                        codedOutputStreamWriter.writeBytes(i10, (ByteString) unsafe.getObject(obj, j2));
                        break;
                    } else {
                        break;
                    }
                case ViewNode.TRANSLATIONX_FIELD_NUMBER /* 11 */:
                    z = false;
                    z2 = true;
                    c = '?';
                    if ((i2 & i9) != 0) {
                        codedOutputStream.writeUInt32(i10, unsafe.getInt(obj, j2));
                        break;
                    } else {
                        break;
                    }
                case ViewNode.TRANSLATIONY_FIELD_NUMBER /* 12 */:
                    z = false;
                    z2 = true;
                    c = '?';
                    if ((i2 & i9) != 0) {
                        codedOutputStream.writeInt32(i10, unsafe.getInt(obj, j2));
                        break;
                    } else {
                        break;
                    }
                case ViewNode.SCALEX_FIELD_NUMBER /* 13 */:
                    z = false;
                    z2 = true;
                    c = '?';
                    if ((i2 & i9) != 0) {
                        codedOutputStream.writeFixed32(i10, unsafe.getInt(obj, j2));
                        break;
                    } else {
                        break;
                    }
                case ViewNode.SCALEY_FIELD_NUMBER /* 14 */:
                    z = false;
                    z2 = true;
                    c = '?';
                    if ((i2 & i9) != 0) {
                        codedOutputStream.writeFixed64(unsafe.getLong(obj, j2), i10);
                        break;
                    } else {
                        break;
                    }
                case 15:
                    z = false;
                    z2 = true;
                    c = '?';
                    if ((i2 & i9) != 0) {
                        int i13 = unsafe.getInt(obj, j2);
                        codedOutputStream.writeUInt32(i10, (i13 >> 31) ^ (i13 << 1));
                        break;
                    } else {
                        break;
                    }
                case 16:
                    z = false;
                    if ((i2 & i9) != 0) {
                        long j3 = unsafe.getLong(obj, j2);
                        z2 = true;
                        c = '?';
                        codedOutputStream.writeUInt64((j3 << 1) ^ (j3 >> 63), i10);
                        break;
                    }
                    z2 = true;
                    c = '?';
                    break;
                case ViewNode.CLIPCHILDREN_FIELD_NUMBER /* 17 */:
                    z = false;
                    if ((i2 & i9) != 0) {
                        codedOutputStreamWriter.writeGroup(i10, unsafe.getObject(obj, j2), getMessageFieldSchema(i8));
                    }
                    z2 = true;
                    c = '?';
                    break;
                case ViewNode.VISIBILITY_FIELD_NUMBER /* 18 */:
                    z = false;
                    SchemaUtil.writeDoubleList(iArr[i8], (List) unsafe.getObject(obj, j2), codedOutputStreamWriter, false);
                    z2 = true;
                    c = '?';
                    break;
                case ViewNode.ELEVATION_FIELD_NUMBER /* 19 */:
                    z = false;
                    SchemaUtil.writeFloatList(iArr[i8], (List) unsafe.getObject(obj, j2), codedOutputStreamWriter, false);
                    z2 = true;
                    c = '?';
                    break;
                case 20:
                    z = false;
                    SchemaUtil.writeInt64List(iArr[i8], (List) unsafe.getObject(obj, j2), codedOutputStreamWriter, false);
                    z2 = true;
                    c = '?';
                    break;
                case 21:
                    z = false;
                    SchemaUtil.writeUInt64List(iArr[i8], (List) unsafe.getObject(obj, j2), codedOutputStreamWriter, false);
                    z2 = true;
                    c = '?';
                    break;
                case 22:
                    z = false;
                    SchemaUtil.writeInt32List(iArr[i8], (List) unsafe.getObject(obj, j2), codedOutputStreamWriter, false);
                    z2 = true;
                    c = '?';
                    break;
                case 23:
                    z = false;
                    SchemaUtil.writeFixed64List(iArr[i8], (List) unsafe.getObject(obj, j2), codedOutputStreamWriter, false);
                    z2 = true;
                    c = '?';
                    break;
                case 24:
                    z = false;
                    SchemaUtil.writeFixed32List(iArr[i8], (List) unsafe.getObject(obj, j2), codedOutputStreamWriter, false);
                    z2 = true;
                    c = '?';
                    break;
                case 25:
                    z = false;
                    SchemaUtil.writeBoolList(iArr[i8], (List) unsafe.getObject(obj, j2), codedOutputStreamWriter, false);
                    z2 = true;
                    c = '?';
                    break;
                case 26:
                    SchemaUtil.writeStringList(iArr[i8], (List) unsafe.getObject(obj, j2), codedOutputStreamWriter);
                    z = false;
                    z2 = true;
                    c = '?';
                    break;
                case 27:
                    SchemaUtil.writeMessageList(iArr[i8], (List) unsafe.getObject(obj, j2), codedOutputStreamWriter, getMessageFieldSchema(i8));
                    z = false;
                    z2 = true;
                    c = '?';
                    break;
                case 28:
                    SchemaUtil.writeBytesList(iArr[i8], (List) unsafe.getObject(obj, j2), codedOutputStreamWriter);
                    z = false;
                    z2 = true;
                    c = '?';
                    break;
                case 29:
                    z3 = false;
                    SchemaUtil.writeUInt32List(iArr[i8], (List) unsafe.getObject(obj, j2), codedOutputStreamWriter, false);
                    z = z3;
                    z2 = true;
                    c = '?';
                    break;
                case 30:
                    z3 = false;
                    SchemaUtil.writeEnumList(iArr[i8], (List) unsafe.getObject(obj, j2), codedOutputStreamWriter, false);
                    z = z3;
                    z2 = true;
                    c = '?';
                    break;
                case 31:
                    z3 = false;
                    SchemaUtil.writeSFixed32List(iArr[i8], (List) unsafe.getObject(obj, j2), codedOutputStreamWriter, false);
                    z = z3;
                    z2 = true;
                    c = '?';
                    break;
                case 32:
                    z3 = false;
                    SchemaUtil.writeSFixed64List(iArr[i8], (List) unsafe.getObject(obj, j2), codedOutputStreamWriter, false);
                    z = z3;
                    z2 = true;
                    c = '?';
                    break;
                case 33:
                    z3 = false;
                    SchemaUtil.writeSInt32List(iArr[i8], (List) unsafe.getObject(obj, j2), codedOutputStreamWriter, false);
                    z = z3;
                    z2 = true;
                    c = '?';
                    break;
                case 34:
                    z3 = false;
                    SchemaUtil.writeSInt64List(iArr[i8], (List) unsafe.getObject(obj, j2), codedOutputStreamWriter, false);
                    z = z3;
                    z2 = true;
                    c = '?';
                    break;
                case 35:
                    z4 = true;
                    SchemaUtil.writeDoubleList(iArr[i8], (List) unsafe.getObject(obj, j2), codedOutputStreamWriter, true);
                    z2 = z4;
                    z = false;
                    c = '?';
                    break;
                case 36:
                    z4 = true;
                    SchemaUtil.writeFloatList(iArr[i8], (List) unsafe.getObject(obj, j2), codedOutputStreamWriter, true);
                    z2 = z4;
                    z = false;
                    c = '?';
                    break;
                case 37:
                    z4 = true;
                    SchemaUtil.writeInt64List(iArr[i8], (List) unsafe.getObject(obj, j2), codedOutputStreamWriter, true);
                    z2 = z4;
                    z = false;
                    c = '?';
                    break;
                case 38:
                    z4 = true;
                    SchemaUtil.writeUInt64List(iArr[i8], (List) unsafe.getObject(obj, j2), codedOutputStreamWriter, true);
                    z2 = z4;
                    z = false;
                    c = '?';
                    break;
                case 39:
                    z4 = true;
                    SchemaUtil.writeInt32List(iArr[i8], (List) unsafe.getObject(obj, j2), codedOutputStreamWriter, true);
                    z2 = z4;
                    z = false;
                    c = '?';
                    break;
                case 40:
                    z4 = true;
                    SchemaUtil.writeFixed64List(iArr[i8], (List) unsafe.getObject(obj, j2), codedOutputStreamWriter, true);
                    z2 = z4;
                    z = false;
                    c = '?';
                    break;
                case 41:
                    z4 = true;
                    SchemaUtil.writeFixed32List(iArr[i8], (List) unsafe.getObject(obj, j2), codedOutputStreamWriter, true);
                    z2 = z4;
                    z = false;
                    c = '?';
                    break;
                case 42:
                    z4 = true;
                    SchemaUtil.writeBoolList(iArr[i8], (List) unsafe.getObject(obj, j2), codedOutputStreamWriter, true);
                    z2 = z4;
                    z = false;
                    c = '?';
                    break;
                case 43:
                    z4 = true;
                    SchemaUtil.writeUInt32List(iArr[i8], (List) unsafe.getObject(obj, j2), codedOutputStreamWriter, true);
                    z2 = z4;
                    z = false;
                    c = '?';
                    break;
                case 44:
                    z4 = true;
                    SchemaUtil.writeEnumList(iArr[i8], (List) unsafe.getObject(obj, j2), codedOutputStreamWriter, true);
                    z2 = z4;
                    z = false;
                    c = '?';
                    break;
                case 45:
                    z4 = true;
                    SchemaUtil.writeSFixed32List(iArr[i8], (List) unsafe.getObject(obj, j2), codedOutputStreamWriter, true);
                    z2 = z4;
                    z = false;
                    c = '?';
                    break;
                case 46:
                    z4 = true;
                    SchemaUtil.writeSFixed64List(iArr[i8], (List) unsafe.getObject(obj, j2), codedOutputStreamWriter, true);
                    z2 = z4;
                    z = false;
                    c = '?';
                    break;
                case 47:
                    z4 = true;
                    SchemaUtil.writeSInt32List(iArr[i8], (List) unsafe.getObject(obj, j2), codedOutputStreamWriter, true);
                    z2 = z4;
                    z = false;
                    c = '?';
                    break;
                case 48:
                    z4 = true;
                    SchemaUtil.writeSInt64List(iArr[i8], (List) unsafe.getObject(obj, j2), codedOutputStreamWriter, true);
                    z2 = z4;
                    z = false;
                    c = '?';
                    break;
                case 49:
                    SchemaUtil.writeGroupList(iArr[i8], (List) unsafe.getObject(obj, j2), codedOutputStreamWriter, getMessageFieldSchema(i8));
                    z = false;
                    z2 = true;
                    c = '?';
                    break;
                case 50:
                    writeMapHelper(codedOutputStreamWriter, i10, unsafe.getObject(obj, j2), i8);
                    z = false;
                    z2 = true;
                    c = '?';
                    break;
                case 51:
                    if (isOneofPresent(i10, i8, obj)) {
                        double doubleValue2 = ((Double) UnsafeUtil.getObject(j2, obj)).doubleValue();
                        codedOutputStream.getClass();
                        codedOutputStream.writeFixed64(Double.doubleToRawLongBits(doubleValue2), i10);
                    }
                    z = false;
                    z2 = true;
                    c = '?';
                    break;
                case 52:
                    if (isOneofPresent(i10, i8, obj)) {
                        float floatValue2 = ((Float) UnsafeUtil.getObject(j2, obj)).floatValue();
                        codedOutputStream.getClass();
                        codedOutputStream.writeFixed32(i10, Float.floatToRawIntBits(floatValue2));
                    }
                    z = false;
                    z2 = true;
                    c = '?';
                    break;
                case 53:
                    if (isOneofPresent(i10, i8, obj)) {
                        codedOutputStream.writeUInt64(oneofLongAt(j2, obj), i10);
                    }
                    z = false;
                    z2 = true;
                    c = '?';
                    break;
                case 54:
                    if (isOneofPresent(i10, i8, obj)) {
                        codedOutputStream.writeUInt64(oneofLongAt(j2, obj), i10);
                    }
                    z = false;
                    z2 = true;
                    c = '?';
                    break;
                case 55:
                    if (isOneofPresent(i10, i8, obj)) {
                        codedOutputStream.writeInt32(i10, oneofIntAt(j2, obj));
                    }
                    z = false;
                    z2 = true;
                    c = '?';
                    break;
                case 56:
                    if (isOneofPresent(i10, i8, obj)) {
                        codedOutputStream.writeFixed64(oneofLongAt(j2, obj), i10);
                    }
                    z = false;
                    z2 = true;
                    c = '?';
                    break;
                case 57:
                    if (isOneofPresent(i10, i8, obj)) {
                        codedOutputStream.writeFixed32(i10, oneofIntAt(j2, obj));
                    }
                    z = false;
                    z2 = true;
                    c = '?';
                    break;
                case 58:
                    if (isOneofPresent(i10, i8, obj)) {
                        codedOutputStream.writeBool(i10, ((Boolean) UnsafeUtil.getObject(j2, obj)).booleanValue());
                    }
                    z = false;
                    z2 = true;
                    c = '?';
                    break;
                case 59:
                    if (isOneofPresent(i10, i8, obj)) {
                        writeString(i10, unsafe.getObject(obj, j2), codedOutputStreamWriter);
                    }
                    z = false;
                    z2 = true;
                    c = '?';
                    break;
                case 60:
                    if (isOneofPresent(i10, i8, obj)) {
                        codedOutputStream.writeMessage(i10, (MessageLite) unsafe.getObject(obj, j2), getMessageFieldSchema(i8));
                    }
                    z = false;
                    z2 = true;
                    c = '?';
                    break;
                case 61:
                    if (isOneofPresent(i10, i8, obj)) {
                        codedOutputStreamWriter.writeBytes(i10, (ByteString) unsafe.getObject(obj, j2));
                    }
                    z = false;
                    z2 = true;
                    c = '?';
                    break;
                case 62:
                    if (isOneofPresent(i10, i8, obj)) {
                        codedOutputStream.writeUInt32(i10, oneofIntAt(j2, obj));
                    }
                    z = false;
                    z2 = true;
                    c = '?';
                    break;
                case 63:
                    if (isOneofPresent(i10, i8, obj)) {
                        codedOutputStream.writeInt32(i10, oneofIntAt(j2, obj));
                    }
                    z = false;
                    z2 = true;
                    c = '?';
                    break;
                case 64:
                    if (isOneofPresent(i10, i8, obj)) {
                        codedOutputStream.writeFixed32(i10, oneofIntAt(j2, obj));
                    }
                    z = false;
                    z2 = true;
                    c = '?';
                    break;
                case 65:
                    if (isOneofPresent(i10, i8, obj)) {
                        codedOutputStream.writeFixed64(oneofLongAt(j2, obj), i10);
                    }
                    z = false;
                    z2 = true;
                    c = '?';
                    break;
                case 66:
                    if (isOneofPresent(i10, i8, obj)) {
                        int oneofIntAt2 = oneofIntAt(j2, obj);
                        codedOutputStream.writeUInt32(i10, (oneofIntAt2 >> 31) ^ (oneofIntAt2 << 1));
                    }
                    z = false;
                    z2 = true;
                    c = '?';
                    break;
                case 67:
                    if (isOneofPresent(i10, i8, obj)) {
                        long oneofLongAt2 = oneofLongAt(j2, obj);
                        codedOutputStream.writeUInt64((oneofLongAt2 << 1) ^ (oneofLongAt2 >> 63), i10);
                    }
                    z = false;
                    z2 = true;
                    c = '?';
                    break;
                case 68:
                    if (isOneofPresent(i10, i8, obj)) {
                        codedOutputStreamWriter.writeGroup(i10, unsafe.getObject(obj, j2), getMessageFieldSchema(i8));
                    }
                    z = false;
                    z2 = true;
                    c = '?';
                    break;
                default:
                    z = false;
                    z2 = true;
                    c = '?';
                    break;
            }
            i8 += 3;
            length2 = i;
            i3 = 1048575;
        }
        unknownFieldSetLiteSchema.getClass();
        ((GeneratedMessageLite) obj).unknownFields.writeTo(codedOutputStreamWriter);
    }

    @Override // com.google.protobuf.Schema
    public final void mergeFrom(Object obj, CodedInputStreamReader codedInputStreamReader, ExtensionRegistryLite extensionRegistryLite) {
        extensionRegistryLite.getClass();
        checkMutable(obj);
        mergeFromHelper(this.unknownFieldSchema, obj, codedInputStreamReader, extensionRegistryLite);
    }

    /* JADX WARN: Code restructure failed: missing block: B:45:0x02b5, code lost:
    
        if (r0 != r32) goto L107;
     */
    /* JADX WARN: Code restructure failed: missing block: B:46:0x02b7, code lost:
    
        r15 = r29;
        r14 = r30;
        r12 = r31;
        r13 = r33;
        r11 = r34;
        r1 = r17;
        r2 = r18;
        r10 = r20;
        r6 = r26;
     */
    /* JADX WARN: Code restructure failed: missing block: B:47:0x02cf, code lost:
    
        r2 = r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:53:0x0301, code lost:
    
        if (r0 != r15) goto L107;
     */
    /* JADX WARN: Code restructure failed: missing block: B:55:0x0321, code lost:
    
        if (r0 != r15) goto L107;
     */
    /* JADX WARN: Failed to find 'out' block for switch in B:66:0x00a1. Please report as an issue. */
    @Override // com.google.protobuf.Schema
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void mergeFrom(java.lang.Object r30, byte[] r31, int r32, int r33, com.google.protobuf.ArrayDecoders.Registers r34) {
        /*
            Method dump skipped, instructions count: 924
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.MessageSchema.mergeFrom(java.lang.Object, byte[], int, int, com.google.protobuf.ArrayDecoders$Registers):void");
    }
}
