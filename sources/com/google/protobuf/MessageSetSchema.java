package com.google.protobuf;

import androidx.compose.animation.AnimatedVisibilityScope$animateEnterExit$$inlined$debugInspectorInfo$1$$ExternalSyntheticOutline0;
import androidx.core.view.VelocityTrackerCompat$$ExternalSyntheticThrowCCEIfNotNull0;
import com.google.protobuf.ArrayDecoders;
import com.google.protobuf.GeneratedMessageLite;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class MessageSetSchema implements Schema {
    public final MessageLite defaultInstance;
    public final ExtensionSchemaLite extensionSchema;
    public final UnknownFieldSetLiteSchema unknownFieldSchema;

    public MessageSetSchema(UnknownFieldSetLiteSchema unknownFieldSetLiteSchema, ExtensionSchemaLite extensionSchemaLite, MessageLite messageLite) {
        this.unknownFieldSchema = unknownFieldSetLiteSchema;
        extensionSchemaLite.getClass();
        this.extensionSchema = extensionSchemaLite;
        this.defaultInstance = messageLite;
    }

    @Override // com.google.protobuf.Schema
    public final boolean equals(GeneratedMessageLite generatedMessageLite, GeneratedMessageLite generatedMessageLite2) {
        this.unknownFieldSchema.getClass();
        return generatedMessageLite.unknownFields.equals(generatedMessageLite2.unknownFields);
    }

    @Override // com.google.protobuf.Schema
    public final int getSerializedSize(GeneratedMessageLite generatedMessageLite) {
        this.unknownFieldSchema.getClass();
        UnknownFieldSetLite unknownFieldSetLite = generatedMessageLite.unknownFields;
        int i = unknownFieldSetLite.memoizedSerializedSize;
        if (i != -1) {
            return i;
        }
        int i2 = 0;
        for (int i3 = 0; i3 < unknownFieldSetLite.count; i3++) {
            int i4 = unknownFieldSetLite.tags[i3] >>> 3;
            i2 += CodedOutputStream.computeBytesSize(3, (ByteString) unknownFieldSetLite.objects[i3]) + CodedOutputStream.computeUInt32SizeNoTag(i4) + CodedOutputStream.computeTagSize(2) + (CodedOutputStream.computeTagSize(1) * 2);
        }
        unknownFieldSetLite.memoizedSerializedSize = i2;
        return i2;
    }

    @Override // com.google.protobuf.Schema
    public final int hashCode(GeneratedMessageLite generatedMessageLite) {
        this.unknownFieldSchema.getClass();
        return generatedMessageLite.unknownFields.hashCode();
    }

    @Override // com.google.protobuf.Schema
    public final boolean isInitialized(Object obj) {
        this.extensionSchema.getClass();
        VelocityTrackerCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(obj);
        throw null;
    }

    @Override // com.google.protobuf.Schema
    public final void makeImmutable(Object obj) {
        this.unknownFieldSchema.getClass();
        ((GeneratedMessageLite) obj).unknownFields.isMutable = false;
        this.extensionSchema.getClass();
        VelocityTrackerCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(obj);
        throw null;
    }

    @Override // com.google.protobuf.Schema
    public final void mergeFrom(Object obj, Object obj2) {
        SchemaUtil.mergeUnknownFields(this.unknownFieldSchema, obj, obj2);
    }

    @Override // com.google.protobuf.Schema
    public final GeneratedMessageLite newInstance() {
        MessageLite messageLite = this.defaultInstance;
        return messageLite instanceof GeneratedMessageLite ? ((GeneratedMessageLite) messageLite).newMutableInstance$1() : ((GeneratedMessageLite.Builder) ((GeneratedMessageLite) messageLite).dynamicMethod(GeneratedMessageLite.MethodToInvoke.NEW_BUILDER)).buildPartial();
    }

    @Override // com.google.protobuf.Schema
    public final void writeTo(Object obj, CodedOutputStreamWriter codedOutputStreamWriter) {
        this.extensionSchema.getClass();
        VelocityTrackerCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(obj);
        throw null;
    }

    @Override // com.google.protobuf.Schema
    public final void mergeFrom(Object obj, byte[] bArr, int i, int i2, ArrayDecoders.Registers registers) {
        GeneratedMessageLite generatedMessageLite = (GeneratedMessageLite) obj;
        if (generatedMessageLite.unknownFields == UnknownFieldSetLite.DEFAULT_INSTANCE) {
            generatedMessageLite.unknownFields = UnknownFieldSetLite.newInstance();
        }
        throw AnimatedVisibilityScope$animateEnterExit$$inlined$debugInspectorInfo$1$$ExternalSyntheticOutline0.m(obj);
    }

    @Override // com.google.protobuf.Schema
    public final void mergeFrom(Object obj, CodedInputStreamReader codedInputStreamReader, ExtensionRegistryLite extensionRegistryLite) {
        this.unknownFieldSchema.getClass();
        UnknownFieldSetLiteSchema.getBuilderFromMessage(obj);
        this.extensionSchema.getClass();
        obj.getClass();
        throw new ClassCastException();
    }
}
