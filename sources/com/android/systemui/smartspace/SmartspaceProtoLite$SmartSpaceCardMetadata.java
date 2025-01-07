package com.android.systemui.smartspace;

import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Parser;
import com.google.protobuf.RawMessageInfo;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class SmartspaceProtoLite$SmartSpaceCardMetadata extends GeneratedMessageLite {
    public static final int CARD_TYPE_ID_FIELD_NUMBER = 2;
    private static final SmartspaceProtoLite$SmartSpaceCardMetadata DEFAULT_INSTANCE;
    public static final int INSTANCE_ID_FIELD_NUMBER = 1;
    private static volatile Parser PARSER;
    private int bitField0_;
    private int cardTypeId_;
    private int instanceId_;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Builder extends GeneratedMessageLite.Builder {
    }

    /* renamed from: -$$Nest$msetCardTypeId, reason: not valid java name */
    public static void m865$$Nest$msetCardTypeId(SmartspaceProtoLite$SmartSpaceCardMetadata smartspaceProtoLite$SmartSpaceCardMetadata, int i) {
        smartspaceProtoLite$SmartSpaceCardMetadata.bitField0_ |= 2;
        smartspaceProtoLite$SmartSpaceCardMetadata.cardTypeId_ = i;
    }

    /* renamed from: -$$Nest$msetInstanceId, reason: not valid java name */
    public static void m866$$Nest$msetInstanceId(SmartspaceProtoLite$SmartSpaceCardMetadata smartspaceProtoLite$SmartSpaceCardMetadata, int i) {
        smartspaceProtoLite$SmartSpaceCardMetadata.bitField0_ |= 1;
        smartspaceProtoLite$SmartSpaceCardMetadata.instanceId_ = i;
    }

    static {
        SmartspaceProtoLite$SmartSpaceCardMetadata smartspaceProtoLite$SmartSpaceCardMetadata = new SmartspaceProtoLite$SmartSpaceCardMetadata();
        DEFAULT_INSTANCE = smartspaceProtoLite$SmartSpaceCardMetadata;
        GeneratedMessageLite.registerDefaultInstance(SmartspaceProtoLite$SmartSpaceCardMetadata.class, smartspaceProtoLite$SmartSpaceCardMetadata);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.createBuilder();
    }

    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke) {
        switch (methodToInvoke.ordinal()) {
            case 0:
                return (byte) 1;
            case 1:
                return null;
            case 2:
                return new RawMessageInfo(DEFAULT_INSTANCE, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001င\u0000\u0002င\u0001", new Object[]{"bitField0_", "instanceId_", "cardTypeId_"});
            case 3:
                return new SmartspaceProtoLite$SmartSpaceCardMetadata();
            case 4:
                return new Builder(DEFAULT_INSTANCE);
            case 5:
                return DEFAULT_INSTANCE;
            case 6:
                Parser parser = PARSER;
                if (parser == null) {
                    synchronized (SmartspaceProtoLite$SmartSpaceCardMetadata.class) {
                        try {
                            parser = PARSER;
                            if (parser == null) {
                                parser = new GeneratedMessageLite.DefaultInstanceBasedParser();
                                PARSER = parser;
                            }
                        } finally {
                        }
                    }
                }
                return parser;
            default:
                throw new UnsupportedOperationException();
        }
    }
}
