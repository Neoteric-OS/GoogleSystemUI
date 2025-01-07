package com.android.app.viewcapture.data;

import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Parser;
import com.google.protobuf.RawMessageInfo;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class FrameData extends GeneratedMessageLite {
    private static final FrameData DEFAULT_INSTANCE;
    public static final int NODE_FIELD_NUMBER = 2;
    private static volatile Parser PARSER = null;
    public static final int TIMESTAMP_FIELD_NUMBER = 1;
    private int bitField0_;
    private ViewNode node_;
    private long timestamp_;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Builder extends GeneratedMessageLite.Builder {
    }

    static {
        FrameData frameData = new FrameData();
        DEFAULT_INSTANCE = frameData;
        GeneratedMessageLite.registerDefaultInstance(FrameData.class, frameData);
    }

    public static void access$100(FrameData frameData, long j) {
        frameData.bitField0_ |= 1;
        frameData.timestamp_ = j;
    }

    public static void access$300(FrameData frameData, ViewNode viewNode) {
        frameData.getClass();
        frameData.node_ = viewNode;
        frameData.bitField0_ |= 2;
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
                return new RawMessageInfo(DEFAULT_INSTANCE, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001ဂ\u0000\u0002ဉ\u0001", new Object[]{"bitField0_", "timestamp_", "node_"});
            case 3:
                return new FrameData();
            case 4:
                return new Builder(DEFAULT_INSTANCE);
            case 5:
                return DEFAULT_INSTANCE;
            case 6:
                Parser parser = PARSER;
                if (parser == null) {
                    synchronized (FrameData.class) {
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

    public final long getTimestamp() {
        return this.timestamp_;
    }
}
