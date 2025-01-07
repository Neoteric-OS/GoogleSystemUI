package com.android.wm.shell.common.pip;

import android.app.TaskInfo;
import android.content.ComponentName;
import android.content.pm.PackageManager;
import com.android.internal.logging.UiEventLogger;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class PipUiEventLogger {
    public final PackageManager mPackageManager;
    public String mPackageName;
    public int mPackageUid = -1;
    public final UiEventLogger mUiEventLogger;

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class PipUiEventEnum implements UiEventLogger.UiEventEnum {
        public static final /* synthetic */ PipUiEventEnum[] $VALUES;
        public static final PipUiEventEnum PICTURE_IN_PICTURE_AUTO_ENTER;
        public static final PipUiEventEnum PICTURE_IN_PICTURE_CUSTOM_CLOSE;
        public static final PipUiEventEnum PICTURE_IN_PICTURE_DRAG_TO_REMOVE;
        public static final PipUiEventEnum PICTURE_IN_PICTURE_ENTER;
        public static final PipUiEventEnum PICTURE_IN_PICTURE_ENTER_CONTENT_PIP;
        public static final PipUiEventEnum PICTURE_IN_PICTURE_EXPAND_TO_FULLSCREEN;
        public static final PipUiEventEnum PICTURE_IN_PICTURE_HIDE_MENU;
        public static final PipUiEventEnum PICTURE_IN_PICTURE_RESIZE;
        public static final PipUiEventEnum PICTURE_IN_PICTURE_SHOW_MENU;
        public static final PipUiEventEnum PICTURE_IN_PICTURE_SHOW_SETTINGS;
        public static final PipUiEventEnum PICTURE_IN_PICTURE_STASH_LEFT;
        public static final PipUiEventEnum PICTURE_IN_PICTURE_STASH_RIGHT;
        public static final PipUiEventEnum PICTURE_IN_PICTURE_STASH_UNSTASHED;
        public static final PipUiEventEnum PICTURE_IN_PICTURE_TAP_TO_REMOVE;
        private final int mId;

        static {
            PipUiEventEnum pipUiEventEnum = new PipUiEventEnum("PICTURE_IN_PICTURE_ENTER", 0, 603);
            PICTURE_IN_PICTURE_ENTER = pipUiEventEnum;
            PipUiEventEnum pipUiEventEnum2 = new PipUiEventEnum("PICTURE_IN_PICTURE_AUTO_ENTER", 1, 1313);
            PICTURE_IN_PICTURE_AUTO_ENTER = pipUiEventEnum2;
            PipUiEventEnum pipUiEventEnum3 = new PipUiEventEnum("PICTURE_IN_PICTURE_ENTER_CONTENT_PIP", 2, 1314);
            PICTURE_IN_PICTURE_ENTER_CONTENT_PIP = pipUiEventEnum3;
            PipUiEventEnum pipUiEventEnum4 = new PipUiEventEnum("PICTURE_IN_PICTURE_EXPAND_TO_FULLSCREEN", 3, 604);
            PICTURE_IN_PICTURE_EXPAND_TO_FULLSCREEN = pipUiEventEnum4;
            PipUiEventEnum pipUiEventEnum5 = new PipUiEventEnum("PICTURE_IN_PICTURE_TAP_TO_REMOVE", 4, 605);
            PICTURE_IN_PICTURE_TAP_TO_REMOVE = pipUiEventEnum5;
            PipUiEventEnum pipUiEventEnum6 = new PipUiEventEnum("PICTURE_IN_PICTURE_DRAG_TO_REMOVE", 5, 606);
            PICTURE_IN_PICTURE_DRAG_TO_REMOVE = pipUiEventEnum6;
            PipUiEventEnum pipUiEventEnum7 = new PipUiEventEnum("PICTURE_IN_PICTURE_SHOW_MENU", 6, 607);
            PICTURE_IN_PICTURE_SHOW_MENU = pipUiEventEnum7;
            PipUiEventEnum pipUiEventEnum8 = new PipUiEventEnum("PICTURE_IN_PICTURE_HIDE_MENU", 7, 608);
            PICTURE_IN_PICTURE_HIDE_MENU = pipUiEventEnum8;
            PipUiEventEnum pipUiEventEnum9 = new PipUiEventEnum("PICTURE_IN_PICTURE_CHANGE_ASPECT_RATIO", 8, 609);
            PipUiEventEnum pipUiEventEnum10 = new PipUiEventEnum("PICTURE_IN_PICTURE_RESIZE", 9, 610);
            PICTURE_IN_PICTURE_RESIZE = pipUiEventEnum10;
            PipUiEventEnum pipUiEventEnum11 = new PipUiEventEnum("PICTURE_IN_PICTURE_STASH_UNSTASHED", 10, 709);
            PICTURE_IN_PICTURE_STASH_UNSTASHED = pipUiEventEnum11;
            PipUiEventEnum pipUiEventEnum12 = new PipUiEventEnum("PICTURE_IN_PICTURE_STASH_LEFT", 11, 710);
            PICTURE_IN_PICTURE_STASH_LEFT = pipUiEventEnum12;
            PipUiEventEnum pipUiEventEnum13 = new PipUiEventEnum("PICTURE_IN_PICTURE_STASH_RIGHT", 12, 711);
            PICTURE_IN_PICTURE_STASH_RIGHT = pipUiEventEnum13;
            PipUiEventEnum pipUiEventEnum14 = new PipUiEventEnum("PICTURE_IN_PICTURE_SHOW_SETTINGS", 13, 933);
            PICTURE_IN_PICTURE_SHOW_SETTINGS = pipUiEventEnum14;
            PipUiEventEnum pipUiEventEnum15 = new PipUiEventEnum("PICTURE_IN_PICTURE_CUSTOM_CLOSE", 14, 1058);
            PICTURE_IN_PICTURE_CUSTOM_CLOSE = pipUiEventEnum15;
            PipUiEventEnum[] pipUiEventEnumArr = {pipUiEventEnum, pipUiEventEnum2, pipUiEventEnum3, pipUiEventEnum4, pipUiEventEnum5, pipUiEventEnum6, pipUiEventEnum7, pipUiEventEnum8, pipUiEventEnum9, pipUiEventEnum10, pipUiEventEnum11, pipUiEventEnum12, pipUiEventEnum13, pipUiEventEnum14, pipUiEventEnum15};
            $VALUES = pipUiEventEnumArr;
            EnumEntriesKt.enumEntries(pipUiEventEnumArr);
        }

        public PipUiEventEnum(String str, int i, int i2) {
            this.mId = i2;
        }

        public static PipUiEventEnum valueOf(String str) {
            return (PipUiEventEnum) Enum.valueOf(PipUiEventEnum.class, str);
        }

        public static PipUiEventEnum[] values() {
            return (PipUiEventEnum[]) $VALUES.clone();
        }

        public final int getId() {
            return this.mId;
        }
    }

    public PipUiEventLogger(UiEventLogger uiEventLogger, PackageManager packageManager) {
        this.mUiEventLogger = uiEventLogger;
        this.mPackageManager = packageManager;
    }

    public final void log(PipUiEventEnum pipUiEventEnum) {
        if (this.mPackageName == null || this.mPackageUid == -1) {
            return;
        }
        UiEventLogger uiEventLogger = this.mUiEventLogger;
        Intrinsics.checkNotNull(pipUiEventEnum);
        uiEventLogger.log(pipUiEventEnum, this.mPackageUid, this.mPackageName);
    }

    public final void setTaskInfo(TaskInfo taskInfo) {
        int i = -1;
        if ((taskInfo != null ? taskInfo.topActivity : null) == null) {
            this.mPackageName = null;
            this.mPackageUid = -1;
            return;
        }
        ComponentName componentName = taskInfo.topActivity;
        Intrinsics.checkNotNull(componentName);
        String packageName = componentName.getPackageName();
        this.mPackageName = packageName;
        Intrinsics.checkNotNull(packageName);
        try {
            i = this.mPackageManager.getApplicationInfoAsUser(packageName, 0, taskInfo.userId).uid;
        } catch (PackageManager.NameNotFoundException unused) {
        }
        this.mPackageUid = i;
    }
}
