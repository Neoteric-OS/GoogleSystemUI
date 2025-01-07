package com.android.app.motiontool;

import com.android.app.viewcapture.data.MotionWindowData;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Parser;
import com.google.protobuf.RawMessageInfo;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class EndTraceResponse extends GeneratedMessageLite {
    public static final int DATA_FIELD_NUMBER = 1;
    private static final EndTraceResponse DEFAULT_INSTANCE;
    private static volatile Parser PARSER;
    private int bitField0_;
    private MotionWindowData data_;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Builder extends GeneratedMessageLite.Builder {
    }

    static {
        EndTraceResponse endTraceResponse = new EndTraceResponse();
        DEFAULT_INSTANCE = endTraceResponse;
        GeneratedMessageLite.registerDefaultInstance(EndTraceResponse.class, endTraceResponse);
    }

    public static void access$100(EndTraceResponse endTraceResponse, MotionWindowData motionWindowData) {
        endTraceResponse.getClass();
        endTraceResponse.data_ = motionWindowData;
        endTraceResponse.bitField0_ |= 1;
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
                return new RawMessageInfo(DEFAULT_INSTANCE, "\u0001\u0001\u0000\u0001\u0001\u0001\u0001\u0000\u0000\u0000\u0001ဉ\u0000", new Object[]{"bitField0_", "data_"});
            case 3:
                return new EndTraceResponse();
            case 4:
                return new Builder(DEFAULT_INSTANCE);
            case 5:
                return DEFAULT_INSTANCE;
            case 6:
                Parser parser = PARSER;
                if (parser == null) {
                    synchronized (EndTraceResponse.class) {
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
