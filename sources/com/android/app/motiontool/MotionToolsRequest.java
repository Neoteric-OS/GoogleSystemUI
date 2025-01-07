package com.android.app.motiontool;

import com.google.protobuf.ArrayDecoders;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Parser;
import com.google.protobuf.Protobuf;
import com.google.protobuf.RawMessageInfo;
import com.google.protobuf.Schema;
import com.google.protobuf.UninitializedMessageException;
import java.io.IOException;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class MotionToolsRequest extends GeneratedMessageLite {
    public static final int BEGIN_TRACE_FIELD_NUMBER = 2;
    private static final MotionToolsRequest DEFAULT_INSTANCE;
    public static final int END_TRACE_FIELD_NUMBER = 3;
    public static final int HANDSHAKE_FIELD_NUMBER = 1;
    private static volatile Parser PARSER = null;
    public static final int POLL_TRACE_FIELD_NUMBER = 4;
    private int bitField0_;
    private int typeCase_ = 0;
    private Object type_;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Builder extends GeneratedMessageLite.Builder {
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public enum TypeCase {
        HANDSHAKE(1),
        BEGIN_TRACE(2),
        END_TRACE(3),
        POLL_TRACE(4),
        TYPE_NOT_SET(0);

        private final int value;

        TypeCase(int i) {
            this.value = i;
        }

        public final int getNumber() {
            return this.value;
        }
    }

    static {
        MotionToolsRequest motionToolsRequest = new MotionToolsRequest();
        DEFAULT_INSTANCE = motionToolsRequest;
        GeneratedMessageLite.registerDefaultInstance(MotionToolsRequest.class, motionToolsRequest);
    }

    public static MotionToolsRequest parseFrom(byte[] bArr) {
        MotionToolsRequest motionToolsRequest = DEFAULT_INSTANCE;
        int length = bArr.length;
        ExtensionRegistryLite emptyRegistry = ExtensionRegistryLite.getEmptyRegistry();
        GeneratedMessageLite newMutableInstance$1 = motionToolsRequest.newMutableInstance$1();
        try {
            Protobuf protobuf = Protobuf.INSTANCE;
            protobuf.getClass();
            Schema schemaFor = protobuf.schemaFor(newMutableInstance$1.getClass());
            ArrayDecoders.Registers registers = new ArrayDecoders.Registers();
            emptyRegistry.getClass();
            schemaFor.mergeFrom(newMutableInstance$1, bArr, 0, length, registers);
            schemaFor.makeImmutable(newMutableInstance$1);
            if (GeneratedMessageLite.isInitialized(newMutableInstance$1, true)) {
                return (MotionToolsRequest) newMutableInstance$1;
            }
            InvalidProtocolBufferException invalidProtocolBufferException = new InvalidProtocolBufferException(new UninitializedMessageException().getMessage());
            invalidProtocolBufferException.setUnfinishedMessage(newMutableInstance$1);
            throw invalidProtocolBufferException;
        } catch (InvalidProtocolBufferException e) {
            e = e;
            if (e.getThrownFromInputStream()) {
                e = new InvalidProtocolBufferException(e);
            }
            e.setUnfinishedMessage(newMutableInstance$1);
            throw e;
        } catch (UninitializedMessageException e2) {
            InvalidProtocolBufferException invalidProtocolBufferException2 = new InvalidProtocolBufferException(e2.getMessage());
            invalidProtocolBufferException2.setUnfinishedMessage(newMutableInstance$1);
            throw invalidProtocolBufferException2;
        } catch (IOException e3) {
            if (e3.getCause() instanceof InvalidProtocolBufferException) {
                throw ((InvalidProtocolBufferException) e3.getCause());
            }
            InvalidProtocolBufferException invalidProtocolBufferException3 = new InvalidProtocolBufferException(e3);
            invalidProtocolBufferException3.setUnfinishedMessage(newMutableInstance$1);
            throw invalidProtocolBufferException3;
        } catch (IndexOutOfBoundsException unused) {
            InvalidProtocolBufferException truncatedMessage = InvalidProtocolBufferException.truncatedMessage();
            truncatedMessage.setUnfinishedMessage(newMutableInstance$1);
            throw truncatedMessage;
        }
    }

    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke) {
        switch (methodToInvoke.ordinal()) {
            case 0:
                return (byte) 1;
            case 1:
                return null;
            case 2:
                return new RawMessageInfo(DEFAULT_INSTANCE, "\u0001\u0004\u0001\u0001\u0001\u0004\u0004\u0000\u0000\u0000\u0001ြ\u0000\u0002ြ\u0000\u0003ြ\u0000\u0004ြ\u0000", new Object[]{"type_", "typeCase_", "bitField0_", HandshakeRequest.class, BeginTraceRequest.class, EndTraceRequest.class, PollTraceRequest.class});
            case 3:
                return new MotionToolsRequest();
            case 4:
                return new Builder(DEFAULT_INSTANCE);
            case 5:
                return DEFAULT_INSTANCE;
            case 6:
                Parser parser = PARSER;
                if (parser == null) {
                    synchronized (MotionToolsRequest.class) {
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

    public final BeginTraceRequest getBeginTrace() {
        return this.typeCase_ == 2 ? (BeginTraceRequest) this.type_ : BeginTraceRequest.getDefaultInstance();
    }

    public final EndTraceRequest getEndTrace() {
        return this.typeCase_ == 3 ? (EndTraceRequest) this.type_ : EndTraceRequest.getDefaultInstance();
    }

    public final HandshakeRequest getHandshake() {
        return this.typeCase_ == 1 ? (HandshakeRequest) this.type_ : HandshakeRequest.getDefaultInstance();
    }

    public final PollTraceRequest getPollTrace() {
        return this.typeCase_ == 4 ? (PollTraceRequest) this.type_ : PollTraceRequest.getDefaultInstance();
    }

    public final TypeCase getTypeCase() {
        int i = this.typeCase_;
        if (i == 0) {
            return TypeCase.TYPE_NOT_SET;
        }
        if (i == 1) {
            return TypeCase.HANDSHAKE;
        }
        if (i == 2) {
            return TypeCase.BEGIN_TRACE;
        }
        if (i == 3) {
            return TypeCase.END_TRACE;
        }
        if (i != 4) {
            return null;
        }
        return TypeCase.POLL_TRACE;
    }
}
