package com.google.protobuf;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class UnknownFieldSetLiteSchema {
    public static UnknownFieldSetLite getBuilderFromMessage(Object obj) {
        GeneratedMessageLite generatedMessageLite = (GeneratedMessageLite) obj;
        UnknownFieldSetLite unknownFieldSetLite = generatedMessageLite.unknownFields;
        if (unknownFieldSetLite != UnknownFieldSetLite.DEFAULT_INSTANCE) {
            return unknownFieldSetLite;
        }
        UnknownFieldSetLite newInstance = UnknownFieldSetLite.newInstance();
        generatedMessageLite.unknownFields = newInstance;
        return newInstance;
    }

    public static boolean mergeOneFieldFrom(Object obj, CodedInputStreamReader codedInputStreamReader) {
        int i = codedInputStreamReader.tag;
        int i2 = i >>> 3;
        int i3 = i & 7;
        CodedInputStream$StreamDecoder codedInputStream$StreamDecoder = codedInputStreamReader.input;
        if (i3 == 0) {
            codedInputStreamReader.requireWireType(0);
            ((UnknownFieldSetLite) obj).storeField(i2 << 3, Long.valueOf(codedInputStream$StreamDecoder.readInt64()));
            return true;
        }
        if (i3 == 1) {
            codedInputStreamReader.requireWireType(1);
            ((UnknownFieldSetLite) obj).storeField((i2 << 3) | 1, Long.valueOf(codedInputStream$StreamDecoder.readFixed64()));
            return true;
        }
        if (i3 == 2) {
            ((UnknownFieldSetLite) obj).storeField((i2 << 3) | 2, codedInputStreamReader.readBytes());
            return true;
        }
        if (i3 != 3) {
            if (i3 == 4) {
                return false;
            }
            if (i3 != 5) {
                throw InvalidProtocolBufferException.invalidWireType();
            }
            codedInputStreamReader.requireWireType(5);
            ((UnknownFieldSetLite) obj).storeField((i2 << 3) | 5, Integer.valueOf(codedInputStream$StreamDecoder.readFixed32()));
            return true;
        }
        UnknownFieldSetLite newInstance = UnknownFieldSetLite.newInstance();
        int i4 = i2 << 3;
        int i5 = i4 | 4;
        while (codedInputStreamReader.getFieldNumber() != Integer.MAX_VALUE && mergeOneFieldFrom(newInstance, codedInputStreamReader)) {
        }
        if (i5 != codedInputStreamReader.tag) {
            throw new InvalidProtocolBufferException("Protocol message end-group tag did not match expected tag.");
        }
        newInstance.isMutable = false;
        ((UnknownFieldSetLite) obj).storeField(i4 | 3, newInstance);
        return true;
    }
}
