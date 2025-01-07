package com.android.wm.shell.desktopmode.persistence;

import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.IntArrayList;
import com.google.protobuf.Internal;
import com.google.protobuf.MapEntryLite;
import com.google.protobuf.MapFieldLite;
import com.google.protobuf.Parser;
import com.google.protobuf.RawMessageInfo;
import com.google.protobuf.WireFormat$FieldType;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class Desktop extends GeneratedMessageLite {
    private static final Desktop DEFAULT_INSTANCE;
    public static final int DESKTOP_ID_FIELD_NUMBER = 2;
    public static final int DISPLAY_ID_FIELD_NUMBER = 1;
    private static volatile Parser PARSER = null;
    public static final int TASKS_BY_TASK_ID_FIELD_NUMBER = 3;
    public static final int Z_ORDERED_TASKS_FIELD_NUMBER = 4;
    private int bitField0_;
    private int desktopId_;
    private int displayId_;
    private MapFieldLite tasksByTaskId_ = MapFieldLite.EMPTY_MAP_FIELD;
    private Internal.IntList zOrderedTasks_ = IntArrayList.EMPTY_LIST;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Builder extends GeneratedMessageLite.Builder {
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public abstract class TasksByTaskIdDefaultEntryHolder {
        public static final MapEntryLite defaultEntry = new MapEntryLite(WireFormat$FieldType.INT32, 0, WireFormat$FieldType.MESSAGE, DesktopTask.getDefaultInstance());
    }

    static {
        Desktop desktop = new Desktop();
        DEFAULT_INSTANCE = desktop;
        GeneratedMessageLite.registerDefaultInstance(Desktop.class, desktop);
    }

    public static Desktop getDefaultInstance() {
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
                return new RawMessageInfo(DEFAULT_INSTANCE, "\u0001\u0004\u0000\u0001\u0001\u0004\u0004\u0001\u0001\u0000\u0001င\u0000\u0002င\u0001\u00032\u0004\u0016", new Object[]{"bitField0_", "displayId_", "desktopId_", "tasksByTaskId_", TasksByTaskIdDefaultEntryHolder.defaultEntry, "zOrderedTasks_"});
            case 3:
                return new Desktop();
            case 4:
                return new Builder(DEFAULT_INSTANCE);
            case 5:
                return DEFAULT_INSTANCE;
            case 6:
                Parser parser = PARSER;
                if (parser == null) {
                    synchronized (Desktop.class) {
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
