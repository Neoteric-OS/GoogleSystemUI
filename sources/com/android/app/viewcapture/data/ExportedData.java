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
public final class ExportedData extends GeneratedMessageLite {
    public static final int CLASSNAME_FIELD_NUMBER = 4;
    private static final ExportedData DEFAULT_INSTANCE;
    public static final int MAGIC_NUMBER_FIELD_NUMBER = 1;
    public static final int PACKAGE_FIELD_NUMBER = 3;
    private static volatile Parser PARSER = null;
    public static final int REAL_TO_ELAPSED_TIME_OFFSET_NANOS_FIELD_NUMBER = 5;
    public static final int WINDOWDATA_FIELD_NUMBER = 2;
    private int bitField0_;
    private Internal.ProtobufList classname_;
    private long magicNumber_;
    private String package_;
    private long realToElapsedTimeOffsetNanos_;
    private Internal.ProtobufList windowData_;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Builder extends GeneratedMessageLite.Builder {
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public enum MagicNumber implements Internal.EnumLite {
        /* JADX INFO: Fake field, exist only in values array */
        INVALID(0),
        MAGIC_NUMBER_L(1703961976),
        MAGIC_NUMBER_H(1751482995);

        public static final MagicNumber INVALID = null;
        private final int value;

        MagicNumber(int i) {
            this.value = i;
        }

        @Override // com.google.protobuf.Internal.EnumLite
        public final int getNumber() {
            return this.value;
        }
    }

    static {
        ExportedData exportedData = new ExportedData();
        DEFAULT_INSTANCE = exportedData;
        GeneratedMessageLite.registerDefaultInstance(ExportedData.class, exportedData);
    }

    public ExportedData() {
        ProtobufArrayList protobufArrayList = ProtobufArrayList.EMPTY_LIST;
        this.windowData_ = protobufArrayList;
        this.package_ = "";
        this.classname_ = protobufArrayList;
    }

    public static void access$100(ExportedData exportedData, long j) {
        exportedData.bitField0_ |= 1;
        exportedData.magicNumber_ = j;
    }

    public static void access$1400(ExportedData exportedData, Iterable iterable) {
        Internal.ProtobufList protobufList = exportedData.classname_;
        if (!((AbstractProtobufList) protobufList).isMutable) {
            exportedData.classname_ = GeneratedMessageLite.mutableCopy(protobufList);
        }
        AbstractMessageLite.addAll(iterable, exportedData.classname_);
    }

    public static void access$1700(ExportedData exportedData, long j) {
        exportedData.bitField0_ |= 4;
        exportedData.realToElapsedTimeOffsetNanos_ = j;
    }

    public static void access$600(ExportedData exportedData, Iterable iterable) {
        Internal.ProtobufList protobufList = exportedData.windowData_;
        if (!((AbstractProtobufList) protobufList).isMutable) {
            exportedData.windowData_ = GeneratedMessageLite.mutableCopy(protobufList);
        }
        AbstractMessageLite.addAll(iterable, exportedData.windowData_);
    }

    public static void access$900(ExportedData exportedData, String str) {
        exportedData.getClass();
        str.getClass();
        exportedData.bitField0_ |= 2;
        exportedData.package_ = str;
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
                return new RawMessageInfo(DEFAULT_INSTANCE, "\u0001\u0005\u0000\u0001\u0001\u0005\u0005\u0000\u0002\u0000\u0001စ\u0000\u0002\u001b\u0003ဈ\u0001\u0004\u001a\u0005စ\u0002", new Object[]{"bitField0_", "magicNumber_", "windowData_", WindowData.class, "package_", "classname_", "realToElapsedTimeOffsetNanos_"});
            case 3:
                return new ExportedData();
            case 4:
                return new Builder(DEFAULT_INSTANCE);
            case 5:
                return DEFAULT_INSTANCE;
            case 6:
                Parser parser = PARSER;
                if (parser == null) {
                    synchronized (ExportedData.class) {
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
