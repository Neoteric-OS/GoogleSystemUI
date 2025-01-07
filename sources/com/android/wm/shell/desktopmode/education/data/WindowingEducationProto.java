package com.android.wm.shell.desktopmode.education.data;

import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.MapEntryLite;
import com.google.protobuf.MapFieldLite;
import com.google.protobuf.Parser;
import com.google.protobuf.RawMessageInfo;
import com.google.protobuf.WireFormat$FieldType;
import java.io.InputStream;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class WindowingEducationProto extends GeneratedMessageLite {
    public static final int APP_HANDLE_EDUCATION_FIELD_NUMBER = 3;
    private static final WindowingEducationProto DEFAULT_INSTANCE;
    public static final int EDUCATION_VIEWED_TIMESTAMP_MILLIS_FIELD_NUMBER = 1;
    public static final int FEATURE_USED_TIMESTAMP_MILLIS_FIELD_NUMBER = 2;
    private static volatile Parser PARSER;
    private int bitField0_;
    private int educationDataCase_ = 0;
    private Object educationData_;
    private long educationViewedTimestampMillis_;
    private long featureUsedTimestampMillis_;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class AppHandleEducation extends GeneratedMessageLite {
        public static final int APP_USAGE_STATS_FIELD_NUMBER = 1;
        public static final int APP_USAGE_STATS_LAST_UPDATE_TIMESTAMP_MILLIS_FIELD_NUMBER = 2;
        private static final AppHandleEducation DEFAULT_INSTANCE;
        private static volatile Parser PARSER;
        private long appUsageStatsLastUpdateTimestampMillis_;
        private MapFieldLite appUsageStats_ = MapFieldLite.EMPTY_MAP_FIELD;
        private int bitField0_;

        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        public abstract class AppUsageStatsDefaultEntryHolder {
            public static final MapEntryLite defaultEntry = new MapEntryLite(WireFormat$FieldType.STRING, "", WireFormat$FieldType.INT32, 0);
        }

        static {
            AppHandleEducation appHandleEducation = new AppHandleEducation();
            DEFAULT_INSTANCE = appHandleEducation;
            GeneratedMessageLite.registerDefaultInstance(AppHandleEducation.class, appHandleEducation);
        }

        @Override // com.google.protobuf.GeneratedMessageLite
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke) {
            switch (methodToInvoke.ordinal()) {
                case 0:
                    return (byte) 1;
                case 1:
                    return null;
                case 2:
                    return new RawMessageInfo(DEFAULT_INSTANCE, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0001\u0000\u0000\u00012\u0002ဂ\u0000", new Object[]{"bitField0_", "appUsageStats_", AppUsageStatsDefaultEntryHolder.defaultEntry, "appUsageStatsLastUpdateTimestampMillis_"});
                case 3:
                    return new AppHandleEducation();
                case 4:
                    return new Builder(DEFAULT_INSTANCE);
                case 5:
                    return DEFAULT_INSTANCE;
                case 6:
                    Parser parser = PARSER;
                    if (parser == null) {
                        synchronized (AppHandleEducation.class) {
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

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Builder extends GeneratedMessageLite.Builder {
    }

    static {
        WindowingEducationProto windowingEducationProto = new WindowingEducationProto();
        DEFAULT_INSTANCE = windowingEducationProto;
        GeneratedMessageLite.registerDefaultInstance(WindowingEducationProto.class, windowingEducationProto);
    }

    public static WindowingEducationProto getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static WindowingEducationProto parseFrom(InputStream inputStream) {
        return (WindowingEducationProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke) {
        switch (methodToInvoke.ordinal()) {
            case 0:
                return (byte) 1;
            case 1:
                return null;
            case 2:
                return new RawMessageInfo(DEFAULT_INSTANCE, "\u0001\u0003\u0001\u0001\u0001\u0003\u0003\u0000\u0000\u0000\u0001ဂ\u0000\u0002ဂ\u0001\u0003ြ\u0000", new Object[]{"educationData_", "educationDataCase_", "bitField0_", "educationViewedTimestampMillis_", "featureUsedTimestampMillis_", AppHandleEducation.class});
            case 3:
                return new WindowingEducationProto();
            case 4:
                return new Builder(DEFAULT_INSTANCE);
            case 5:
                return DEFAULT_INSTANCE;
            case 6:
                Parser parser = PARSER;
                if (parser == null) {
                    synchronized (WindowingEducationProto.class) {
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
