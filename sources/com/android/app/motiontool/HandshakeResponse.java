package com.android.app.motiontool;

import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Internal;
import com.google.protobuf.Parser;
import com.google.protobuf.RawMessageInfo;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class HandshakeResponse extends GeneratedMessageLite {
    private static final HandshakeResponse DEFAULT_INSTANCE;
    private static volatile Parser PARSER = null;
    public static final int SERVER_VERSION_FIELD_NUMBER = 2;
    public static final int STATUS_FIELD_NUMBER = 1;
    private int bitField0_;
    private int serverVersion_;
    private int status_ = 1;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Builder extends GeneratedMessageLite.Builder {
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public enum Status implements Internal.EnumLite {
        OK(1),
        WINDOW_NOT_FOUND(2);

        private final int value;

        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        public final class StatusVerifier implements Internal.EnumVerifier {
            public static final StatusVerifier INSTANCE = new StatusVerifier();

            @Override // com.google.protobuf.Internal.EnumVerifier
            public final boolean isInRange(int i) {
                return (i != 1 ? i != 2 ? null : Status.WINDOW_NOT_FOUND : Status.OK) != null;
            }
        }

        Status(int i) {
            this.value = i;
        }

        @Override // com.google.protobuf.Internal.EnumLite
        public final int getNumber() {
            return this.value;
        }
    }

    static {
        HandshakeResponse handshakeResponse = new HandshakeResponse();
        DEFAULT_INSTANCE = handshakeResponse;
        GeneratedMessageLite.registerDefaultInstance(HandshakeResponse.class, handshakeResponse);
    }

    public static void access$100(HandshakeResponse handshakeResponse, Status status) {
        handshakeResponse.getClass();
        handshakeResponse.status_ = status.getNumber();
        handshakeResponse.bitField0_ |= 1;
    }

    public static void access$300(HandshakeResponse handshakeResponse) {
        handshakeResponse.bitField0_ |= 2;
        handshakeResponse.serverVersion_ = 1;
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
                return new RawMessageInfo(DEFAULT_INSTANCE, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001ဌ\u0000\u0002င\u0001", new Object[]{"bitField0_", "status_", Status.StatusVerifier.INSTANCE, "serverVersion_"});
            case 3:
                return new HandshakeResponse();
            case 4:
                return new Builder(DEFAULT_INSTANCE);
            case 5:
                return DEFAULT_INSTANCE;
            case 6:
                Parser parser = PARSER;
                if (parser == null) {
                    synchronized (HandshakeResponse.class) {
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
