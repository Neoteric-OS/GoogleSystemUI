package com.google.protobuf;

import com.google.protobuf.ListFieldSchema;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class Protobuf {
    public static final Protobuf INSTANCE = new Protobuf();
    public final ConcurrentMap schemaCache = new ConcurrentHashMap();
    public final ManifestSchemaFactory schemaFactory = new ManifestSchemaFactory();

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r3v6, types: [com.google.protobuf.MessageSetSchema] */
    /* JADX WARN: Type inference failed for: r3v8, types: [com.google.protobuf.MessageSetSchema] */
    public final Schema schemaFor(Class cls) {
        MessageSchema newSchema;
        MessageSchema messageSchema;
        Class cls2;
        Internal.checkNotNull(cls, "messageType");
        Schema schema = (Schema) this.schemaCache.get(cls);
        if (schema != null) {
            return schema;
        }
        ManifestSchemaFactory manifestSchemaFactory = this.schemaFactory;
        manifestSchemaFactory.getClass();
        Class cls3 = SchemaUtil.GENERATED_MESSAGE_CLASS;
        if (!GeneratedMessageLite.class.isAssignableFrom(cls) && (cls2 = SchemaUtil.GENERATED_MESSAGE_CLASS) != null && !cls2.isAssignableFrom(cls)) {
            throw new IllegalArgumentException("Message classes must extend GeneratedMessageV3 or GeneratedMessageLite");
        }
        RawMessageInfo messageInfoFor = manifestSchemaFactory.messageInfoFactory.messageInfoFor(cls);
        if ((messageInfoFor.flags & 2) == 2) {
            boolean isAssignableFrom = GeneratedMessageLite.class.isAssignableFrom(cls);
            MessageLite messageLite = messageInfoFor.defaultInstance;
            if (isAssignableFrom) {
                messageSchema = new MessageSetSchema(SchemaUtil.UNKNOWN_FIELD_SET_LITE_SCHEMA, ExtensionSchemas.LITE_SCHEMA, messageLite);
            } else {
                UnknownFieldSetLiteSchema unknownFieldSetLiteSchema = SchemaUtil.PROTO2_UNKNOWN_FIELD_SET_SCHEMA;
                ExtensionSchemaLite extensionSchemaLite = ExtensionSchemas.FULL_SCHEMA;
                if (extensionSchemaLite == null) {
                    throw new IllegalStateException("Protobuf runtime is not correctly loaded.");
                }
                messageSchema = new MessageSetSchema(unknownFieldSetLiteSchema, extensionSchemaLite, messageLite);
            }
            newSchema = messageSchema;
        } else {
            boolean isAssignableFrom2 = GeneratedMessageLite.class.isAssignableFrom(cls);
            ProtoSyntax protoSyntax = ProtoSyntax.PROTO3;
            ProtoSyntax protoSyntax2 = ProtoSyntax.PROTO2;
            if (isAssignableFrom2) {
                if ((messageInfoFor.flags & 1) == 1) {
                    protoSyntax = protoSyntax2;
                }
                newSchema = protoSyntax == protoSyntax2 ? MessageSchema.newSchema(messageInfoFor, NewInstanceSchemas.LITE_SCHEMA, ListFieldSchema.LITE_INSTANCE, SchemaUtil.UNKNOWN_FIELD_SET_LITE_SCHEMA, ExtensionSchemas.LITE_SCHEMA, MapFieldSchemas.LITE_SCHEMA) : MessageSchema.newSchema(messageInfoFor, NewInstanceSchemas.LITE_SCHEMA, ListFieldSchema.LITE_INSTANCE, SchemaUtil.UNKNOWN_FIELD_SET_LITE_SCHEMA, null, MapFieldSchemas.LITE_SCHEMA);
            } else {
                if ((messageInfoFor.flags & 1) == 1) {
                    protoSyntax = protoSyntax2;
                }
                if (protoSyntax == protoSyntax2) {
                    NewInstanceSchemaLite newInstanceSchemaLite = NewInstanceSchemas.FULL_SCHEMA;
                    ListFieldSchema.ListFieldSchemaFull listFieldSchemaFull = ListFieldSchema.FULL_INSTANCE;
                    UnknownFieldSetLiteSchema unknownFieldSetLiteSchema2 = SchemaUtil.PROTO2_UNKNOWN_FIELD_SET_SCHEMA;
                    ExtensionSchemaLite extensionSchemaLite2 = ExtensionSchemas.FULL_SCHEMA;
                    if (extensionSchemaLite2 == null) {
                        throw new IllegalStateException("Protobuf runtime is not correctly loaded.");
                    }
                    newSchema = MessageSchema.newSchema(messageInfoFor, newInstanceSchemaLite, listFieldSchemaFull, unknownFieldSetLiteSchema2, extensionSchemaLite2, MapFieldSchemas.FULL_SCHEMA);
                } else {
                    newSchema = MessageSchema.newSchema(messageInfoFor, NewInstanceSchemas.FULL_SCHEMA, ListFieldSchema.FULL_INSTANCE, SchemaUtil.PROTO3_UNKNOWN_FIELD_SET_SCHEMA, null, MapFieldSchemas.FULL_SCHEMA);
                }
            }
        }
        Schema schema2 = (Schema) ((ConcurrentHashMap) this.schemaCache).putIfAbsent(cls, newSchema);
        return schema2 != null ? schema2 : newSchema;
    }
}
