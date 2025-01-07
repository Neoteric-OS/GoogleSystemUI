package com.android.systemui.smartspace;

import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.AbstractProtobufList;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Internal;
import com.google.protobuf.Parser;
import com.google.protobuf.ProtobufArrayList;
import com.google.protobuf.RawMessageInfo;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class SmartspaceProtoLite$SmartSpaceSubcards extends GeneratedMessageLite {
    public static final int CLICKED_SUBCARD_INDEX_FIELD_NUMBER = 2;
    private static final SmartspaceProtoLite$SmartSpaceSubcards DEFAULT_INSTANCE;
    private static volatile Parser PARSER = null;
    public static final int SUBCARDS_FIELD_NUMBER = 1;
    private int bitField0_;
    private int clickedSubcardIndex_;
    private Internal.ProtobufList subcards_ = ProtobufArrayList.EMPTY_LIST;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Builder extends GeneratedMessageLite.Builder {
    }

    /* renamed from: -$$Nest$maddAllSubcards, reason: not valid java name */
    public static void m867$$Nest$maddAllSubcards(SmartspaceProtoLite$SmartSpaceSubcards smartspaceProtoLite$SmartSpaceSubcards, Iterable iterable) {
        Internal.ProtobufList protobufList = smartspaceProtoLite$SmartSpaceSubcards.subcards_;
        if (!((AbstractProtobufList) protobufList).isMutable) {
            smartspaceProtoLite$SmartSpaceSubcards.subcards_ = GeneratedMessageLite.mutableCopy(protobufList);
        }
        AbstractMessageLite.addAll(iterable, smartspaceProtoLite$SmartSpaceSubcards.subcards_);
    }

    /* renamed from: -$$Nest$msetClickedSubcardIndex, reason: not valid java name */
    public static void m868$$Nest$msetClickedSubcardIndex(SmartspaceProtoLite$SmartSpaceSubcards smartspaceProtoLite$SmartSpaceSubcards, int i) {
        smartspaceProtoLite$SmartSpaceSubcards.bitField0_ |= 1;
        smartspaceProtoLite$SmartSpaceSubcards.clickedSubcardIndex_ = i;
    }

    static {
        SmartspaceProtoLite$SmartSpaceSubcards smartspaceProtoLite$SmartSpaceSubcards = new SmartspaceProtoLite$SmartSpaceSubcards();
        DEFAULT_INSTANCE = smartspaceProtoLite$SmartSpaceSubcards;
        GeneratedMessageLite.registerDefaultInstance(SmartspaceProtoLite$SmartSpaceSubcards.class, smartspaceProtoLite$SmartSpaceSubcards);
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
                return new RawMessageInfo(DEFAULT_INSTANCE, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0001\u0000\u0001\u001b\u0002င\u0000", new Object[]{"bitField0_", "subcards_", SmartspaceProtoLite$SmartSpaceCardMetadata.class, "clickedSubcardIndex_"});
            case 3:
                return new SmartspaceProtoLite$SmartSpaceSubcards();
            case 4:
                return new Builder(DEFAULT_INSTANCE);
            case 5:
                return DEFAULT_INSTANCE;
            case 6:
                Parser parser = PARSER;
                if (parser == null) {
                    synchronized (SmartspaceProtoLite$SmartSpaceSubcards.class) {
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
