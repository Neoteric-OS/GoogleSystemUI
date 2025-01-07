package com.android.wm.shell.desktopmode.persistence;

import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.MapEntryLite;
import com.google.protobuf.MapFieldLite;
import com.google.protobuf.Parser;
import com.google.protobuf.RawMessageInfo;
import com.google.protobuf.WireFormat$FieldType;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class DesktopRepositoryState extends GeneratedMessageLite {
    private static final DesktopRepositoryState DEFAULT_INSTANCE;
    public static final int DESKTOP_FIELD_NUMBER = 1;
    private static volatile Parser PARSER;
    private MapFieldLite desktop_ = MapFieldLite.EMPTY_MAP_FIELD;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Builder extends GeneratedMessageLite.Builder {
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public abstract class DesktopDefaultEntryHolder {
        public static final MapEntryLite defaultEntry = new MapEntryLite(WireFormat$FieldType.INT32, 0, WireFormat$FieldType.MESSAGE, Desktop.getDefaultInstance());
    }

    static {
        DesktopRepositoryState desktopRepositoryState = new DesktopRepositoryState();
        DEFAULT_INSTANCE = desktopRepositoryState;
        GeneratedMessageLite.registerDefaultInstance(DesktopRepositoryState.class, desktopRepositoryState);
    }

    public static DesktopRepositoryState getDefaultInstance() {
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
                return new RawMessageInfo(DEFAULT_INSTANCE, "\u0001\u0001\u0000\u0000\u0001\u0001\u0001\u0001\u0000\u0000\u00012", new Object[]{"desktop_", DesktopDefaultEntryHolder.defaultEntry});
            case 3:
                return new DesktopRepositoryState();
            case 4:
                return new Builder(DEFAULT_INSTANCE);
            case 5:
                return DEFAULT_INSTANCE;
            case 6:
                Parser parser = PARSER;
                if (parser == null) {
                    synchronized (DesktopRepositoryState.class) {
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
