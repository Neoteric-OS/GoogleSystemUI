package com.android.app.viewcapture.data;

import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.AbstractProtobufList;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Internal;
import com.google.protobuf.Parser;
import com.google.protobuf.ProtobufArrayList;
import com.google.protobuf.RawMessageInfo;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class MotionWindowData extends GeneratedMessageLite {
    public static final int CLASSNAME_FIELD_NUMBER = 2;
    private static final MotionWindowData DEFAULT_INSTANCE;
    public static final int FRAMEDATA_FIELD_NUMBER = 1;
    private static volatile Parser PARSER;
    private Internal.ProtobufList classname_;
    private Internal.ProtobufList frameData_;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Builder extends GeneratedMessageLite.Builder {
    }

    static {
        MotionWindowData motionWindowData = new MotionWindowData();
        DEFAULT_INSTANCE = motionWindowData;
        GeneratedMessageLite.registerDefaultInstance(MotionWindowData.class, motionWindowData);
    }

    public MotionWindowData() {
        ProtobufArrayList protobufArrayList = ProtobufArrayList.EMPTY_LIST;
        this.frameData_ = protobufArrayList;
        this.classname_ = protobufArrayList;
    }

    public static void access$400(MotionWindowData motionWindowData, Iterable iterable) {
        Internal.ProtobufList protobufList = motionWindowData.frameData_;
        if (!((AbstractProtobufList) protobufList).isMutable) {
            motionWindowData.frameData_ = GeneratedMessageLite.mutableCopy(protobufList);
        }
        AbstractMessageLite.addAll(iterable, motionWindowData.frameData_);
    }

    public static void access$500(MotionWindowData motionWindowData) {
        motionWindowData.getClass();
        motionWindowData.frameData_ = ProtobufArrayList.EMPTY_LIST;
    }

    public static void access$900(MotionWindowData motionWindowData, Iterable iterable) {
        Internal.ProtobufList protobufList = motionWindowData.classname_;
        if (!((AbstractProtobufList) protobufList).isMutable) {
            motionWindowData.classname_ = GeneratedMessageLite.mutableCopy(protobufList);
        }
        AbstractMessageLite.addAll(iterable, motionWindowData.classname_);
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
                return new RawMessageInfo(DEFAULT_INSTANCE, "\u0001\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0002\u0000\u0001\u001b\u0002\u001a", new Object[]{"frameData_", FrameData.class, "classname_"});
            case 3:
                return new MotionWindowData();
            case 4:
                return new Builder(DEFAULT_INSTANCE);
            case 5:
                return DEFAULT_INSTANCE;
            case 6:
                Parser parser = PARSER;
                if (parser == null) {
                    synchronized (MotionWindowData.class) {
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

    public final Internal.ProtobufList getFrameDataList() {
        return this.frameData_;
    }
}
