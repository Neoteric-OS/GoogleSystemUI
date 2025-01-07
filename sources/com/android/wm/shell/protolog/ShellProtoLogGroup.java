package com.android.wm.shell.protolog;

import com.android.internal.protolog.common.IProtoLogGroup;
import java.util.UUID;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public enum ShellProtoLogGroup implements IProtoLogGroup {
    WM_SHELL(true, true, true, "WindowManagerShell"),
    WM_SHELL_INIT(true, true, true, "WindowManagerShell"),
    WM_SHELL_TASK_ORG(true, true, false, "WindowManagerShell"),
    WM_SHELL_TRANSITIONS(true, true, true, "WindowManagerShell"),
    WM_SHELL_RECENTS_TRANSITION(true, true, true, "ShellRecents"),
    WM_SHELL_DRAG_AND_DROP(true, true, true, "ShellDragAndDrop"),
    WM_SHELL_STARTING_WINDOW(true, true, false, "ShellStartingWindow"),
    WM_SHELL_BACK_PREVIEW(true, true, true, "ShellBackPreview"),
    WM_SHELL_RECENT_TASKS(true, true, false, "WindowManagerShell"),
    WM_SHELL_PICTURE_IN_PICTURE(true, true, true, "WindowManagerShell"),
    WM_SHELL_SPLIT_SCREEN(true, true, true, "ShellSplitScreen"),
    WM_SHELL_SYSUI_EVENTS(true, true, false, "WindowManagerShell"),
    WM_SHELL_DESKTOP_MODE(true, true, true, "ShellDesktopMode"),
    WM_SHELL_FLOATING_APPS(true, true, false, "WindowManagerShell"),
    WM_SHELL_FOLDABLE(true, true, false, "WindowManagerShell"),
    WM_SHELL_BUBBLES(true, true, false, "Bubbles"),
    WM_SHELL_COMPAT_UI(true, true, false, "CompatUi"),
    TEST_GROUP(true, true, false, "WindowManagerShellProtoLogTest");

    private final boolean mEnabled;
    private volatile boolean mLogToLogcat;
    private volatile boolean mLogToProto;
    private final String mTag;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public abstract class Consts {
        public static final int START_ID = (int) (UUID.nameUUIDFromBytes(ShellProtoLogGroup.class.getName().getBytes()).getMostSignificantBits() % 2147483647L);
    }

    ShellProtoLogGroup(boolean z, boolean z2, boolean z3, String str) {
        this.mEnabled = z;
        this.mLogToProto = z2;
        this.mLogToLogcat = z3;
        this.mTag = str;
    }

    public int getId() {
        return ordinal() + Consts.START_ID;
    }

    public String getTag() {
        return this.mTag;
    }

    public boolean isEnabled() {
        return this.mEnabled;
    }

    public boolean isLogToAny() {
        return this.mLogToLogcat || this.mLogToProto;
    }

    public boolean isLogToLogcat() {
        return this.mLogToLogcat;
    }

    public boolean isLogToProto() {
        return this.mLogToProto;
    }

    public void setLogToLogcat(boolean z) {
        this.mLogToLogcat = z;
    }

    public void setLogToProto(boolean z) {
        this.mLogToProto = z;
    }
}
