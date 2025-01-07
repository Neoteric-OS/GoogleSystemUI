package com.android.wm.shell.desktopmode.persistence;

import com.android.wm.shell.desktopmode.persistence.DesktopTaskState;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Parser;
import com.google.protobuf.RawMessageInfo;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class DesktopTask extends GeneratedMessageLite {
    private static final DesktopTask DEFAULT_INSTANCE;
    public static final int DESKTOP_TASK_STATE_FIELD_NUMBER = 2;
    private static volatile Parser PARSER = null;
    public static final int TASK_ID_FIELD_NUMBER = 1;
    private int bitField0_;
    private int desktopTaskState_;
    private int taskId_;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Builder extends GeneratedMessageLite.Builder {
    }

    static {
        DesktopTask desktopTask = new DesktopTask();
        DEFAULT_INSTANCE = desktopTask;
        GeneratedMessageLite.registerDefaultInstance(DesktopTask.class, desktopTask);
    }

    public static DesktopTask getDefaultInstance() {
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
                return new RawMessageInfo(DEFAULT_INSTANCE, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001င\u0000\u0002ဌ\u0001", new Object[]{"bitField0_", "taskId_", "desktopTaskState_", DesktopTaskState.DesktopTaskStateVerifier.INSTANCE});
            case 3:
                return new DesktopTask();
            case 4:
                return new Builder(DEFAULT_INSTANCE);
            case 5:
                return DEFAULT_INSTANCE;
            case 6:
                Parser parser = PARSER;
                if (parser == null) {
                    synchronized (DesktopTask.class) {
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
