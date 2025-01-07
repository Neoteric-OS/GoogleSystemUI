package com.android.wm.shell.desktopmode.persistence;

import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.MapEntryLite;
import com.google.protobuf.MapFieldLite;
import com.google.protobuf.Parser;
import com.google.protobuf.RawMessageInfo;
import com.google.protobuf.WireFormat$FieldType;
import java.io.InputStream;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class DesktopPersistentRepositories extends GeneratedMessageLite {
    private static final DesktopPersistentRepositories DEFAULT_INSTANCE;
    public static final int DESKTOP_REPO_BY_USER_FIELD_NUMBER = 1;
    private static volatile Parser PARSER;
    private MapFieldLite desktopRepoByUser_ = MapFieldLite.EMPTY_MAP_FIELD;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Builder extends GeneratedMessageLite.Builder {
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public abstract class DesktopRepoByUserDefaultEntryHolder {
        public static final MapEntryLite defaultEntry = new MapEntryLite(WireFormat$FieldType.INT32, 0, WireFormat$FieldType.MESSAGE, DesktopRepositoryState.getDefaultInstance());
    }

    static {
        DesktopPersistentRepositories desktopPersistentRepositories = new DesktopPersistentRepositories();
        DEFAULT_INSTANCE = desktopPersistentRepositories;
        GeneratedMessageLite.registerDefaultInstance(DesktopPersistentRepositories.class, desktopPersistentRepositories);
    }

    public static DesktopPersistentRepositories getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static DesktopPersistentRepositories parseFrom(InputStream inputStream) {
        return (DesktopPersistentRepositories) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke) {
        switch (methodToInvoke.ordinal()) {
            case 0:
                return (byte) 1;
            case 1:
                return null;
            case 2:
                return new RawMessageInfo(DEFAULT_INSTANCE, "\u0001\u0001\u0000\u0000\u0001\u0001\u0001\u0001\u0000\u0000\u00012", new Object[]{"desktopRepoByUser_", DesktopRepoByUserDefaultEntryHolder.defaultEntry});
            case 3:
                return new DesktopPersistentRepositories();
            case 4:
                return new Builder(DEFAULT_INSTANCE);
            case 5:
                return DEFAULT_INSTANCE;
            case 6:
                Parser parser = PARSER;
                if (parser == null) {
                    synchronized (DesktopPersistentRepositories.class) {
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
