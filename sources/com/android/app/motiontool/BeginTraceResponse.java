package com.android.app.motiontool;

import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Parser;
import com.google.protobuf.RawMessageInfo;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class BeginTraceResponse extends GeneratedMessageLite {
    private static final BeginTraceResponse DEFAULT_INSTANCE;
    private static volatile Parser PARSER = null;
    public static final int TRACE_ID_FIELD_NUMBER = 1;
    private int bitField0_;
    private int traceId_;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Builder extends GeneratedMessageLite.Builder {
    }

    static {
        BeginTraceResponse beginTraceResponse = new BeginTraceResponse();
        DEFAULT_INSTANCE = beginTraceResponse;
        GeneratedMessageLite.registerDefaultInstance(BeginTraceResponse.class, beginTraceResponse);
    }

    public static void access$100(BeginTraceResponse beginTraceResponse, int i) {
        beginTraceResponse.bitField0_ |= 1;
        beginTraceResponse.traceId_ = i;
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
                return new RawMessageInfo(DEFAULT_INSTANCE, "\u0001\u0001\u0000\u0001\u0001\u0001\u0001\u0000\u0000\u0000\u0001င\u0000", new Object[]{"bitField0_", "traceId_"});
            case 3:
                return new BeginTraceResponse();
            case 4:
                return new Builder(DEFAULT_INSTANCE);
            case 5:
                return DEFAULT_INSTANCE;
            case 6:
                Parser parser = PARSER;
                if (parser == null) {
                    synchronized (BeginTraceResponse.class) {
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
