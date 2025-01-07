package com.android.app.viewcapture.data;

import com.google.protobuf.AbstractProtobufList;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Internal;
import com.google.protobuf.Parser;
import com.google.protobuf.ProtobufArrayList;
import com.google.protobuf.RawMessageInfo;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class WindowData extends GeneratedMessageLite {
    private static final WindowData DEFAULT_INSTANCE;
    public static final int FRAMEDATA_FIELD_NUMBER = 1;
    private static volatile Parser PARSER = null;
    public static final int TITLE_FIELD_NUMBER = 2;
    private int bitField0_;
    private Internal.ProtobufList frameData_ = ProtobufArrayList.EMPTY_LIST;
    private String title_ = "";

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Builder extends GeneratedMessageLite.Builder {
    }

    static {
        WindowData windowData = new WindowData();
        DEFAULT_INSTANCE = windowData;
        GeneratedMessageLite.registerDefaultInstance(WindowData.class, windowData);
    }

    public static void access$200(WindowData windowData, FrameData frameData) {
        windowData.getClass();
        Internal.ProtobufList protobufList = windowData.frameData_;
        if (!((AbstractProtobufList) protobufList).isMutable) {
            windowData.frameData_ = GeneratedMessageLite.mutableCopy(protobufList);
        }
        windowData.frameData_.add(frameData);
    }

    public static void access$700(WindowData windowData, String str) {
        windowData.getClass();
        str.getClass();
        windowData.bitField0_ |= 1;
        windowData.title_ = str;
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
                return new RawMessageInfo(DEFAULT_INSTANCE, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0001\u0000\u0001\u001b\u0002ဈ\u0000", new Object[]{"bitField0_", "frameData_", FrameData.class, "title_"});
            case 3:
                return new WindowData();
            case 4:
                return new Builder(DEFAULT_INSTANCE);
            case 5:
                return DEFAULT_INSTANCE;
            case 6:
                Parser parser = PARSER;
                if (parser == null) {
                    synchronized (WindowData.class) {
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
