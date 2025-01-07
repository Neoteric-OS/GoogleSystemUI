package com.android.app.motiontool;

import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Internal;
import com.google.protobuf.Parser;
import com.google.protobuf.RawMessageInfo;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class ErrorResponse extends GeneratedMessageLite {
    public static final int CODE_FIELD_NUMBER = 1;
    private static final ErrorResponse DEFAULT_INSTANCE;
    public static final int MESSAGE_FIELD_NUMBER = 2;
    private static volatile Parser PARSER;
    private int bitField0_;
    private int code_;
    private String message_ = "";

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Builder extends GeneratedMessageLite.Builder {
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public enum Code implements Internal.EnumLite {
        UNKNOWN(0),
        INVALID_REQUEST(1),
        UNKNOWN_TRACE_ID(2),
        WINDOW_NOT_FOUND(3);

        private final int value;

        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        public final class CodeVerifier implements Internal.EnumVerifier {
            public static final CodeVerifier INSTANCE = new CodeVerifier();

            @Override // com.google.protobuf.Internal.EnumVerifier
            public final boolean isInRange(int i) {
                return (i != 0 ? i != 1 ? i != 2 ? i != 3 ? null : Code.WINDOW_NOT_FOUND : Code.UNKNOWN_TRACE_ID : Code.INVALID_REQUEST : Code.UNKNOWN) != null;
            }
        }

        Code(int i) {
            this.value = i;
        }

        @Override // com.google.protobuf.Internal.EnumLite
        public final int getNumber() {
            return this.value;
        }
    }

    static {
        ErrorResponse errorResponse = new ErrorResponse();
        DEFAULT_INSTANCE = errorResponse;
        GeneratedMessageLite.registerDefaultInstance(ErrorResponse.class, errorResponse);
    }

    public static void access$100(ErrorResponse errorResponse, Code code) {
        errorResponse.getClass();
        errorResponse.code_ = code.getNumber();
        errorResponse.bitField0_ |= 1;
    }

    public static void access$300(ErrorResponse errorResponse, String str) {
        errorResponse.getClass();
        str.getClass();
        errorResponse.bitField0_ |= 2;
        errorResponse.message_ = str;
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
                return new RawMessageInfo(DEFAULT_INSTANCE, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001ဌ\u0000\u0002ဈ\u0001", new Object[]{"bitField0_", "code_", Code.CodeVerifier.INSTANCE, "message_"});
            case 3:
                return new ErrorResponse();
            case 4:
                return new Builder(DEFAULT_INSTANCE);
            case 5:
                return DEFAULT_INSTANCE;
            case 6:
                Parser parser = PARSER;
                if (parser == null) {
                    synchronized (ErrorResponse.class) {
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
