package com.android.app.motiontool;

import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Parser;
import com.google.protobuf.RawMessageInfo;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class HandshakeRequest extends GeneratedMessageLite {
    public static final int CLIENT_VERSION_FIELD_NUMBER = 2;
    private static final HandshakeRequest DEFAULT_INSTANCE;
    private static volatile Parser PARSER = null;
    public static final int WINDOW_FIELD_NUMBER = 1;
    private int bitField0_;
    private int clientVersion_;
    private WindowIdentifier window_;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Builder extends GeneratedMessageLite.Builder {
    }

    static {
        HandshakeRequest handshakeRequest = new HandshakeRequest();
        DEFAULT_INSTANCE = handshakeRequest;
        GeneratedMessageLite.registerDefaultInstance(HandshakeRequest.class, handshakeRequest);
    }

    public static HandshakeRequest getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke) {
        switch (methodToInvoke.ordinal()) {
            case 0:
                return (byte) 1;
            case 1:
                return null;
            case 2:
                return new RawMessageInfo(DEFAULT_INSTANCE, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001ဉ\u0000\u0002င\u0001", new Object[]{"bitField0_", "window_", "clientVersion_"});
            case 3:
                return new HandshakeRequest();
            case 4:
                return new Builder(DEFAULT_INSTANCE);
            case 5:
                return DEFAULT_INSTANCE;
            case 6:
                Parser parser = PARSER;
                if (parser == null) {
                    synchronized (HandshakeRequest.class) {
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

    public final WindowIdentifier getWindow() {
        WindowIdentifier windowIdentifier = this.window_;
        return windowIdentifier == null ? WindowIdentifier.getDefaultInstance() : windowIdentifier;
    }
}
