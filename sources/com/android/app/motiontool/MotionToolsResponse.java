package com.android.app.motiontool;

import com.android.app.motiontool.ErrorResponse;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Parser;
import com.google.protobuf.RawMessageInfo;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class MotionToolsResponse extends GeneratedMessageLite {
    public static final int BEGIN_TRACE_FIELD_NUMBER = 3;
    private static final MotionToolsResponse DEFAULT_INSTANCE;
    public static final int END_TRACE_FIELD_NUMBER = 4;
    public static final int ERROR_FIELD_NUMBER = 1;
    public static final int HANDSHAKE_FIELD_NUMBER = 2;
    private static volatile Parser PARSER = null;
    public static final int POLL_TRACE_FIELD_NUMBER = 5;
    private int bitField0_;
    private int typeCase_ = 0;
    private Object type_;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Builder extends GeneratedMessageLite.Builder {
        public final void setError(ErrorResponse.Builder builder) {
            copyOnWrite();
            MotionToolsResponse.access$200((MotionToolsResponse) this.instance, (ErrorResponse) builder.build());
        }
    }

    static {
        MotionToolsResponse motionToolsResponse = new MotionToolsResponse();
        DEFAULT_INSTANCE = motionToolsResponse;
        GeneratedMessageLite.registerDefaultInstance(MotionToolsResponse.class, motionToolsResponse);
    }

    public static void access$1100(MotionToolsResponse motionToolsResponse, EndTraceResponse endTraceResponse) {
        motionToolsResponse.getClass();
        motionToolsResponse.type_ = endTraceResponse;
        motionToolsResponse.typeCase_ = 4;
    }

    public static void access$1400(MotionToolsResponse motionToolsResponse, PollTraceResponse pollTraceResponse) {
        motionToolsResponse.getClass();
        motionToolsResponse.type_ = pollTraceResponse;
        motionToolsResponse.typeCase_ = 5;
    }

    public static void access$200(MotionToolsResponse motionToolsResponse, ErrorResponse errorResponse) {
        motionToolsResponse.getClass();
        motionToolsResponse.type_ = errorResponse;
        motionToolsResponse.typeCase_ = 1;
    }

    public static void access$500(MotionToolsResponse motionToolsResponse, HandshakeResponse handshakeResponse) {
        motionToolsResponse.getClass();
        motionToolsResponse.type_ = handshakeResponse;
        motionToolsResponse.typeCase_ = 2;
    }

    public static void access$800(MotionToolsResponse motionToolsResponse, BeginTraceResponse beginTraceResponse) {
        motionToolsResponse.getClass();
        motionToolsResponse.type_ = beginTraceResponse;
        motionToolsResponse.typeCase_ = 3;
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
                return new RawMessageInfo(DEFAULT_INSTANCE, "\u0001\u0005\u0001\u0001\u0001\u0005\u0005\u0000\u0000\u0000\u0001ြ\u0000\u0002ြ\u0000\u0003ြ\u0000\u0004ြ\u0000\u0005ြ\u0000", new Object[]{"type_", "typeCase_", "bitField0_", ErrorResponse.class, HandshakeResponse.class, BeginTraceResponse.class, EndTraceResponse.class, PollTraceResponse.class});
            case 3:
                return new MotionToolsResponse();
            case 4:
                return new Builder(DEFAULT_INSTANCE);
            case 5:
                return DEFAULT_INSTANCE;
            case 6:
                Parser parser = PARSER;
                if (parser == null) {
                    synchronized (MotionToolsResponse.class) {
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
