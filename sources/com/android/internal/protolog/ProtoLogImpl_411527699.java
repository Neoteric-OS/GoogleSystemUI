package com.android.internal.protolog;

import android.util.Log;
import com.android.internal.protolog.common.IProtoLog;
import com.android.internal.protolog.common.IProtoLogGroup;
import com.android.internal.protolog.common.LogLevel;
import com.android.wm.shell.protolog.ShellProtoLogGroup;
import java.util.TreeMap;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class ProtoLogImpl_411527699 {
    public static final /* synthetic */ int $r8$clinit = 0;
    private static IProtoLog sServiceInstance;
    private static final TreeMap sLogGroups = createLogGroupsMap();
    private static final Runnable sCacheUpdater = new ProtoLogImpl_411527699$$ExternalSyntheticLambda0();

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public abstract class Cache {
        public static boolean[] WM_SHELL_enabled = {true, true, true, true, true, true};
        public static boolean[] WM_SHELL_INIT_enabled = {true, true, true, true, true, true};
        public static boolean[] WM_SHELL_TASK_ORG_enabled = {true, true, true, true, true, true};
        public static boolean[] WM_SHELL_TRANSITIONS_enabled = {true, true, true, true, true, true};
        public static boolean[] WM_SHELL_RECENTS_TRANSITION_enabled = {true, true, true, true, true, true};
        public static boolean[] WM_SHELL_DRAG_AND_DROP_enabled = {true, true, true, true, true, true};
        public static boolean[] WM_SHELL_STARTING_WINDOW_enabled = {true, true, true, true, true, true};
        public static boolean[] WM_SHELL_BACK_PREVIEW_enabled = {true, true, true, true, true, true};
        public static boolean[] WM_SHELL_RECENT_TASKS_enabled = {true, true, true, true, true, true};
        public static boolean[] WM_SHELL_PICTURE_IN_PICTURE_enabled = {true, true, true, true, true, true};
        public static boolean[] WM_SHELL_SPLIT_SCREEN_enabled = {true, true, true, true, true, true};
        public static boolean[] WM_SHELL_SYSUI_EVENTS_enabled = {true, true, true, true, true, true};
        public static boolean[] WM_SHELL_DESKTOP_MODE_enabled = {true, true, true, true, true, true};
        public static boolean[] WM_SHELL_FLOATING_APPS_enabled = {true, true, true, true, true, true};
        public static boolean[] WM_SHELL_FOLDABLE_enabled = {true, true, true, true, true, true};
        public static boolean[] WM_SHELL_BUBBLES_enabled = {true, true, true, true, true, true};
        public static boolean[] WM_SHELL_COMPAT_UI_enabled = {true, true, true, true, true, true};
        public static boolean[] TEST_GROUP_enabled = {true, true, true, true, true, true};

        /* JADX INFO: Access modifiers changed from: private */
        public static void update() {
            boolean[] zArr = WM_SHELL_enabled;
            ShellProtoLogGroup shellProtoLogGroup = ShellProtoLogGroup.WM_SHELL;
            LogLevel logLevel = LogLevel.DEBUG;
            zArr[0] = ProtoLogImpl_411527699.isEnabled(shellProtoLogGroup, logLevel);
            boolean[] zArr2 = WM_SHELL_enabled;
            LogLevel logLevel2 = LogLevel.VERBOSE;
            zArr2[1] = ProtoLogImpl_411527699.isEnabled(shellProtoLogGroup, logLevel2);
            boolean[] zArr3 = WM_SHELL_enabled;
            LogLevel logLevel3 = LogLevel.INFO;
            zArr3[2] = ProtoLogImpl_411527699.isEnabled(shellProtoLogGroup, logLevel3);
            boolean[] zArr4 = WM_SHELL_enabled;
            LogLevel logLevel4 = LogLevel.WARN;
            zArr4[3] = ProtoLogImpl_411527699.isEnabled(shellProtoLogGroup, logLevel4);
            boolean[] zArr5 = WM_SHELL_enabled;
            LogLevel logLevel5 = LogLevel.ERROR;
            zArr5[4] = ProtoLogImpl_411527699.isEnabled(shellProtoLogGroup, logLevel5);
            boolean[] zArr6 = WM_SHELL_enabled;
            LogLevel logLevel6 = LogLevel.WTF;
            zArr6[5] = ProtoLogImpl_411527699.isEnabled(shellProtoLogGroup, logLevel6);
            boolean[] zArr7 = WM_SHELL_INIT_enabled;
            ShellProtoLogGroup shellProtoLogGroup2 = ShellProtoLogGroup.WM_SHELL_INIT;
            zArr7[0] = ProtoLogImpl_411527699.isEnabled(shellProtoLogGroup2, logLevel);
            WM_SHELL_INIT_enabled[1] = ProtoLogImpl_411527699.isEnabled(shellProtoLogGroup2, logLevel2);
            WM_SHELL_INIT_enabled[2] = ProtoLogImpl_411527699.isEnabled(shellProtoLogGroup2, logLevel3);
            WM_SHELL_INIT_enabled[3] = ProtoLogImpl_411527699.isEnabled(shellProtoLogGroup2, logLevel4);
            WM_SHELL_INIT_enabled[4] = ProtoLogImpl_411527699.isEnabled(shellProtoLogGroup2, logLevel5);
            WM_SHELL_INIT_enabled[5] = ProtoLogImpl_411527699.isEnabled(shellProtoLogGroup2, logLevel6);
            boolean[] zArr8 = WM_SHELL_TASK_ORG_enabled;
            ShellProtoLogGroup shellProtoLogGroup3 = ShellProtoLogGroup.WM_SHELL_TASK_ORG;
            zArr8[0] = ProtoLogImpl_411527699.isEnabled(shellProtoLogGroup3, logLevel);
            WM_SHELL_TASK_ORG_enabled[1] = ProtoLogImpl_411527699.isEnabled(shellProtoLogGroup3, logLevel2);
            WM_SHELL_TASK_ORG_enabled[2] = ProtoLogImpl_411527699.isEnabled(shellProtoLogGroup3, logLevel3);
            WM_SHELL_TASK_ORG_enabled[3] = ProtoLogImpl_411527699.isEnabled(shellProtoLogGroup3, logLevel4);
            WM_SHELL_TASK_ORG_enabled[4] = ProtoLogImpl_411527699.isEnabled(shellProtoLogGroup3, logLevel5);
            WM_SHELL_TASK_ORG_enabled[5] = ProtoLogImpl_411527699.isEnabled(shellProtoLogGroup3, logLevel6);
            boolean[] zArr9 = WM_SHELL_TRANSITIONS_enabled;
            ShellProtoLogGroup shellProtoLogGroup4 = ShellProtoLogGroup.WM_SHELL_TRANSITIONS;
            zArr9[0] = ProtoLogImpl_411527699.isEnabled(shellProtoLogGroup4, logLevel);
            WM_SHELL_TRANSITIONS_enabled[1] = ProtoLogImpl_411527699.isEnabled(shellProtoLogGroup4, logLevel2);
            WM_SHELL_TRANSITIONS_enabled[2] = ProtoLogImpl_411527699.isEnabled(shellProtoLogGroup4, logLevel3);
            WM_SHELL_TRANSITIONS_enabled[3] = ProtoLogImpl_411527699.isEnabled(shellProtoLogGroup4, logLevel4);
            WM_SHELL_TRANSITIONS_enabled[4] = ProtoLogImpl_411527699.isEnabled(shellProtoLogGroup4, logLevel5);
            WM_SHELL_TRANSITIONS_enabled[5] = ProtoLogImpl_411527699.isEnabled(shellProtoLogGroup4, logLevel6);
            boolean[] zArr10 = WM_SHELL_RECENTS_TRANSITION_enabled;
            ShellProtoLogGroup shellProtoLogGroup5 = ShellProtoLogGroup.WM_SHELL_RECENTS_TRANSITION;
            zArr10[0] = ProtoLogImpl_411527699.isEnabled(shellProtoLogGroup5, logLevel);
            WM_SHELL_RECENTS_TRANSITION_enabled[1] = ProtoLogImpl_411527699.isEnabled(shellProtoLogGroup5, logLevel2);
            WM_SHELL_RECENTS_TRANSITION_enabled[2] = ProtoLogImpl_411527699.isEnabled(shellProtoLogGroup5, logLevel3);
            WM_SHELL_RECENTS_TRANSITION_enabled[3] = ProtoLogImpl_411527699.isEnabled(shellProtoLogGroup5, logLevel4);
            WM_SHELL_RECENTS_TRANSITION_enabled[4] = ProtoLogImpl_411527699.isEnabled(shellProtoLogGroup5, logLevel5);
            WM_SHELL_RECENTS_TRANSITION_enabled[5] = ProtoLogImpl_411527699.isEnabled(shellProtoLogGroup5, logLevel6);
            boolean[] zArr11 = WM_SHELL_DRAG_AND_DROP_enabled;
            ShellProtoLogGroup shellProtoLogGroup6 = ShellProtoLogGroup.WM_SHELL_DRAG_AND_DROP;
            zArr11[0] = ProtoLogImpl_411527699.isEnabled(shellProtoLogGroup6, logLevel);
            WM_SHELL_DRAG_AND_DROP_enabled[1] = ProtoLogImpl_411527699.isEnabled(shellProtoLogGroup6, logLevel2);
            WM_SHELL_DRAG_AND_DROP_enabled[2] = ProtoLogImpl_411527699.isEnabled(shellProtoLogGroup6, logLevel3);
            WM_SHELL_DRAG_AND_DROP_enabled[3] = ProtoLogImpl_411527699.isEnabled(shellProtoLogGroup6, logLevel4);
            WM_SHELL_DRAG_AND_DROP_enabled[4] = ProtoLogImpl_411527699.isEnabled(shellProtoLogGroup6, logLevel5);
            WM_SHELL_DRAG_AND_DROP_enabled[5] = ProtoLogImpl_411527699.isEnabled(shellProtoLogGroup6, logLevel6);
            boolean[] zArr12 = WM_SHELL_STARTING_WINDOW_enabled;
            ShellProtoLogGroup shellProtoLogGroup7 = ShellProtoLogGroup.WM_SHELL_STARTING_WINDOW;
            zArr12[0] = ProtoLogImpl_411527699.isEnabled(shellProtoLogGroup7, logLevel);
            WM_SHELL_STARTING_WINDOW_enabled[1] = ProtoLogImpl_411527699.isEnabled(shellProtoLogGroup7, logLevel2);
            WM_SHELL_STARTING_WINDOW_enabled[2] = ProtoLogImpl_411527699.isEnabled(shellProtoLogGroup7, logLevel3);
            WM_SHELL_STARTING_WINDOW_enabled[3] = ProtoLogImpl_411527699.isEnabled(shellProtoLogGroup7, logLevel4);
            WM_SHELL_STARTING_WINDOW_enabled[4] = ProtoLogImpl_411527699.isEnabled(shellProtoLogGroup7, logLevel5);
            WM_SHELL_STARTING_WINDOW_enabled[5] = ProtoLogImpl_411527699.isEnabled(shellProtoLogGroup7, logLevel6);
            boolean[] zArr13 = WM_SHELL_BACK_PREVIEW_enabled;
            ShellProtoLogGroup shellProtoLogGroup8 = ShellProtoLogGroup.WM_SHELL_BACK_PREVIEW;
            zArr13[0] = ProtoLogImpl_411527699.isEnabled(shellProtoLogGroup8, logLevel);
            WM_SHELL_BACK_PREVIEW_enabled[1] = ProtoLogImpl_411527699.isEnabled(shellProtoLogGroup8, logLevel2);
            WM_SHELL_BACK_PREVIEW_enabled[2] = ProtoLogImpl_411527699.isEnabled(shellProtoLogGroup8, logLevel3);
            WM_SHELL_BACK_PREVIEW_enabled[3] = ProtoLogImpl_411527699.isEnabled(shellProtoLogGroup8, logLevel4);
            WM_SHELL_BACK_PREVIEW_enabled[4] = ProtoLogImpl_411527699.isEnabled(shellProtoLogGroup8, logLevel5);
            WM_SHELL_BACK_PREVIEW_enabled[5] = ProtoLogImpl_411527699.isEnabled(shellProtoLogGroup8, logLevel6);
            boolean[] zArr14 = WM_SHELL_RECENT_TASKS_enabled;
            ShellProtoLogGroup shellProtoLogGroup9 = ShellProtoLogGroup.WM_SHELL_RECENT_TASKS;
            zArr14[0] = ProtoLogImpl_411527699.isEnabled(shellProtoLogGroup9, logLevel);
            WM_SHELL_RECENT_TASKS_enabled[1] = ProtoLogImpl_411527699.isEnabled(shellProtoLogGroup9, logLevel2);
            WM_SHELL_RECENT_TASKS_enabled[2] = ProtoLogImpl_411527699.isEnabled(shellProtoLogGroup9, logLevel3);
            WM_SHELL_RECENT_TASKS_enabled[3] = ProtoLogImpl_411527699.isEnabled(shellProtoLogGroup9, logLevel4);
            WM_SHELL_RECENT_TASKS_enabled[4] = ProtoLogImpl_411527699.isEnabled(shellProtoLogGroup9, logLevel5);
            WM_SHELL_RECENT_TASKS_enabled[5] = ProtoLogImpl_411527699.isEnabled(shellProtoLogGroup9, logLevel6);
            boolean[] zArr15 = WM_SHELL_PICTURE_IN_PICTURE_enabled;
            ShellProtoLogGroup shellProtoLogGroup10 = ShellProtoLogGroup.WM_SHELL_PICTURE_IN_PICTURE;
            zArr15[0] = ProtoLogImpl_411527699.isEnabled(shellProtoLogGroup10, logLevel);
            WM_SHELL_PICTURE_IN_PICTURE_enabled[1] = ProtoLogImpl_411527699.isEnabled(shellProtoLogGroup10, logLevel2);
            WM_SHELL_PICTURE_IN_PICTURE_enabled[2] = ProtoLogImpl_411527699.isEnabled(shellProtoLogGroup10, logLevel3);
            WM_SHELL_PICTURE_IN_PICTURE_enabled[3] = ProtoLogImpl_411527699.isEnabled(shellProtoLogGroup10, logLevel4);
            WM_SHELL_PICTURE_IN_PICTURE_enabled[4] = ProtoLogImpl_411527699.isEnabled(shellProtoLogGroup10, logLevel5);
            WM_SHELL_PICTURE_IN_PICTURE_enabled[5] = ProtoLogImpl_411527699.isEnabled(shellProtoLogGroup10, logLevel6);
            boolean[] zArr16 = WM_SHELL_SPLIT_SCREEN_enabled;
            ShellProtoLogGroup shellProtoLogGroup11 = ShellProtoLogGroup.WM_SHELL_SPLIT_SCREEN;
            zArr16[0] = ProtoLogImpl_411527699.isEnabled(shellProtoLogGroup11, logLevel);
            WM_SHELL_SPLIT_SCREEN_enabled[1] = ProtoLogImpl_411527699.isEnabled(shellProtoLogGroup11, logLevel2);
            WM_SHELL_SPLIT_SCREEN_enabled[2] = ProtoLogImpl_411527699.isEnabled(shellProtoLogGroup11, logLevel3);
            WM_SHELL_SPLIT_SCREEN_enabled[3] = ProtoLogImpl_411527699.isEnabled(shellProtoLogGroup11, logLevel4);
            WM_SHELL_SPLIT_SCREEN_enabled[4] = ProtoLogImpl_411527699.isEnabled(shellProtoLogGroup11, logLevel5);
            WM_SHELL_SPLIT_SCREEN_enabled[5] = ProtoLogImpl_411527699.isEnabled(shellProtoLogGroup11, logLevel6);
            boolean[] zArr17 = WM_SHELL_SYSUI_EVENTS_enabled;
            ShellProtoLogGroup shellProtoLogGroup12 = ShellProtoLogGroup.WM_SHELL_SYSUI_EVENTS;
            zArr17[0] = ProtoLogImpl_411527699.isEnabled(shellProtoLogGroup12, logLevel);
            WM_SHELL_SYSUI_EVENTS_enabled[1] = ProtoLogImpl_411527699.isEnabled(shellProtoLogGroup12, logLevel2);
            WM_SHELL_SYSUI_EVENTS_enabled[2] = ProtoLogImpl_411527699.isEnabled(shellProtoLogGroup12, logLevel3);
            WM_SHELL_SYSUI_EVENTS_enabled[3] = ProtoLogImpl_411527699.isEnabled(shellProtoLogGroup12, logLevel4);
            WM_SHELL_SYSUI_EVENTS_enabled[4] = ProtoLogImpl_411527699.isEnabled(shellProtoLogGroup12, logLevel5);
            WM_SHELL_SYSUI_EVENTS_enabled[5] = ProtoLogImpl_411527699.isEnabled(shellProtoLogGroup12, logLevel6);
            boolean[] zArr18 = WM_SHELL_DESKTOP_MODE_enabled;
            ShellProtoLogGroup shellProtoLogGroup13 = ShellProtoLogGroup.WM_SHELL_DESKTOP_MODE;
            zArr18[0] = ProtoLogImpl_411527699.isEnabled(shellProtoLogGroup13, logLevel);
            WM_SHELL_DESKTOP_MODE_enabled[1] = ProtoLogImpl_411527699.isEnabled(shellProtoLogGroup13, logLevel2);
            WM_SHELL_DESKTOP_MODE_enabled[2] = ProtoLogImpl_411527699.isEnabled(shellProtoLogGroup13, logLevel3);
            WM_SHELL_DESKTOP_MODE_enabled[3] = ProtoLogImpl_411527699.isEnabled(shellProtoLogGroup13, logLevel4);
            WM_SHELL_DESKTOP_MODE_enabled[4] = ProtoLogImpl_411527699.isEnabled(shellProtoLogGroup13, logLevel5);
            WM_SHELL_DESKTOP_MODE_enabled[5] = ProtoLogImpl_411527699.isEnabled(shellProtoLogGroup13, logLevel6);
            boolean[] zArr19 = WM_SHELL_FLOATING_APPS_enabled;
            ShellProtoLogGroup shellProtoLogGroup14 = ShellProtoLogGroup.WM_SHELL_FLOATING_APPS;
            zArr19[0] = ProtoLogImpl_411527699.isEnabled(shellProtoLogGroup14, logLevel);
            WM_SHELL_FLOATING_APPS_enabled[1] = ProtoLogImpl_411527699.isEnabled(shellProtoLogGroup14, logLevel2);
            WM_SHELL_FLOATING_APPS_enabled[2] = ProtoLogImpl_411527699.isEnabled(shellProtoLogGroup14, logLevel3);
            WM_SHELL_FLOATING_APPS_enabled[3] = ProtoLogImpl_411527699.isEnabled(shellProtoLogGroup14, logLevel4);
            WM_SHELL_FLOATING_APPS_enabled[4] = ProtoLogImpl_411527699.isEnabled(shellProtoLogGroup14, logLevel5);
            WM_SHELL_FLOATING_APPS_enabled[5] = ProtoLogImpl_411527699.isEnabled(shellProtoLogGroup14, logLevel6);
            boolean[] zArr20 = WM_SHELL_FOLDABLE_enabled;
            ShellProtoLogGroup shellProtoLogGroup15 = ShellProtoLogGroup.WM_SHELL_FOLDABLE;
            zArr20[0] = ProtoLogImpl_411527699.isEnabled(shellProtoLogGroup15, logLevel);
            WM_SHELL_FOLDABLE_enabled[1] = ProtoLogImpl_411527699.isEnabled(shellProtoLogGroup15, logLevel2);
            WM_SHELL_FOLDABLE_enabled[2] = ProtoLogImpl_411527699.isEnabled(shellProtoLogGroup15, logLevel3);
            WM_SHELL_FOLDABLE_enabled[3] = ProtoLogImpl_411527699.isEnabled(shellProtoLogGroup15, logLevel4);
            WM_SHELL_FOLDABLE_enabled[4] = ProtoLogImpl_411527699.isEnabled(shellProtoLogGroup15, logLevel5);
            WM_SHELL_FOLDABLE_enabled[5] = ProtoLogImpl_411527699.isEnabled(shellProtoLogGroup15, logLevel6);
            boolean[] zArr21 = WM_SHELL_BUBBLES_enabled;
            ShellProtoLogGroup shellProtoLogGroup16 = ShellProtoLogGroup.WM_SHELL_BUBBLES;
            zArr21[0] = ProtoLogImpl_411527699.isEnabled(shellProtoLogGroup16, logLevel);
            WM_SHELL_BUBBLES_enabled[1] = ProtoLogImpl_411527699.isEnabled(shellProtoLogGroup16, logLevel2);
            WM_SHELL_BUBBLES_enabled[2] = ProtoLogImpl_411527699.isEnabled(shellProtoLogGroup16, logLevel3);
            WM_SHELL_BUBBLES_enabled[3] = ProtoLogImpl_411527699.isEnabled(shellProtoLogGroup16, logLevel4);
            WM_SHELL_BUBBLES_enabled[4] = ProtoLogImpl_411527699.isEnabled(shellProtoLogGroup16, logLevel5);
            WM_SHELL_BUBBLES_enabled[5] = ProtoLogImpl_411527699.isEnabled(shellProtoLogGroup16, logLevel6);
            boolean[] zArr22 = WM_SHELL_COMPAT_UI_enabled;
            ShellProtoLogGroup shellProtoLogGroup17 = ShellProtoLogGroup.WM_SHELL_COMPAT_UI;
            zArr22[0] = ProtoLogImpl_411527699.isEnabled(shellProtoLogGroup17, logLevel);
            WM_SHELL_COMPAT_UI_enabled[1] = ProtoLogImpl_411527699.isEnabled(shellProtoLogGroup17, logLevel2);
            WM_SHELL_COMPAT_UI_enabled[2] = ProtoLogImpl_411527699.isEnabled(shellProtoLogGroup17, logLevel3);
            WM_SHELL_COMPAT_UI_enabled[3] = ProtoLogImpl_411527699.isEnabled(shellProtoLogGroup17, logLevel4);
            WM_SHELL_COMPAT_UI_enabled[4] = ProtoLogImpl_411527699.isEnabled(shellProtoLogGroup17, logLevel5);
            WM_SHELL_COMPAT_UI_enabled[5] = ProtoLogImpl_411527699.isEnabled(shellProtoLogGroup17, logLevel6);
            boolean[] zArr23 = TEST_GROUP_enabled;
            ShellProtoLogGroup shellProtoLogGroup18 = ShellProtoLogGroup.TEST_GROUP;
            zArr23[0] = ProtoLogImpl_411527699.isEnabled(shellProtoLogGroup18, logLevel);
            TEST_GROUP_enabled[1] = ProtoLogImpl_411527699.isEnabled(shellProtoLogGroup18, logLevel2);
            TEST_GROUP_enabled[2] = ProtoLogImpl_411527699.isEnabled(shellProtoLogGroup18, logLevel3);
            TEST_GROUP_enabled[3] = ProtoLogImpl_411527699.isEnabled(shellProtoLogGroup18, logLevel4);
            TEST_GROUP_enabled[4] = ProtoLogImpl_411527699.isEnabled(shellProtoLogGroup18, logLevel5);
            TEST_GROUP_enabled[5] = ProtoLogImpl_411527699.isEnabled(shellProtoLogGroup18, logLevel6);
        }
    }

    private static final TreeMap createLogGroupsMap() {
        TreeMap treeMap = new TreeMap();
        treeMap.put("WM_SHELL", ShellProtoLogGroup.WM_SHELL);
        treeMap.put("WM_SHELL_INIT", ShellProtoLogGroup.WM_SHELL_INIT);
        treeMap.put("WM_SHELL_TASK_ORG", ShellProtoLogGroup.WM_SHELL_TASK_ORG);
        treeMap.put("WM_SHELL_TRANSITIONS", ShellProtoLogGroup.WM_SHELL_TRANSITIONS);
        treeMap.put("WM_SHELL_RECENTS_TRANSITION", ShellProtoLogGroup.WM_SHELL_RECENTS_TRANSITION);
        treeMap.put("WM_SHELL_DRAG_AND_DROP", ShellProtoLogGroup.WM_SHELL_DRAG_AND_DROP);
        treeMap.put("WM_SHELL_STARTING_WINDOW", ShellProtoLogGroup.WM_SHELL_STARTING_WINDOW);
        treeMap.put("WM_SHELL_BACK_PREVIEW", ShellProtoLogGroup.WM_SHELL_BACK_PREVIEW);
        treeMap.put("WM_SHELL_RECENT_TASKS", ShellProtoLogGroup.WM_SHELL_RECENT_TASKS);
        treeMap.put("WM_SHELL_PICTURE_IN_PICTURE", ShellProtoLogGroup.WM_SHELL_PICTURE_IN_PICTURE);
        treeMap.put("WM_SHELL_SPLIT_SCREEN", ShellProtoLogGroup.WM_SHELL_SPLIT_SCREEN);
        treeMap.put("WM_SHELL_SYSUI_EVENTS", ShellProtoLogGroup.WM_SHELL_SYSUI_EVENTS);
        treeMap.put("WM_SHELL_DESKTOP_MODE", ShellProtoLogGroup.WM_SHELL_DESKTOP_MODE);
        treeMap.put("WM_SHELL_FLOATING_APPS", ShellProtoLogGroup.WM_SHELL_FLOATING_APPS);
        treeMap.put("WM_SHELL_FOLDABLE", ShellProtoLogGroup.WM_SHELL_FOLDABLE);
        treeMap.put("WM_SHELL_BUBBLES", ShellProtoLogGroup.WM_SHELL_BUBBLES);
        treeMap.put("WM_SHELL_COMPAT_UI", ShellProtoLogGroup.WM_SHELL_COMPAT_UI);
        treeMap.put("TEST_GROUP", ShellProtoLogGroup.TEST_GROUP);
        return treeMap;
    }

    public static void d(IProtoLogGroup iProtoLogGroup, long j, int i, Object... objArr) {
        getSingleInstance().log(LogLevel.DEBUG, iProtoLogGroup, j, i, objArr);
    }

    public static void e(IProtoLogGroup iProtoLogGroup, long j, int i, Object... objArr) {
        getSingleInstance().log(LogLevel.ERROR, iProtoLogGroup, j, i, objArr);
    }

    public static synchronized IProtoLog getSingleInstance() {
        IProtoLog iProtoLog;
        synchronized (ProtoLogImpl_411527699.class) {
            try {
                if (sServiceInstance == null) {
                    Log.i("ProtoLogImpl", "Setting up ProtoLogImpl with viewerConfigPath = /system_ext/etc/wmshell.protolog.pb");
                    IProtoLogGroup[] iProtoLogGroupArr = (IProtoLogGroup[]) sLogGroups.values().toArray(new IProtoLogGroup[0]);
                    Runnable runnable = sCacheUpdater;
                    LegacyProtoLogImpl legacyProtoLogImpl = new LegacyProtoLogImpl("/data/misc/wmtrace/shell_log.winscope", "/system_ext/etc/wmshell.protolog.json.gz", runnable);
                    legacyProtoLogImpl.registerGroups(iProtoLogGroupArr);
                    sServiceInstance = legacyProtoLogImpl;
                    runnable.run();
                }
                iProtoLog = sServiceInstance;
            } catch (Throwable th) {
                throw th;
            }
        }
        return iProtoLog;
    }

    public static void i(IProtoLogGroup iProtoLogGroup, long j, int i, Object... objArr) {
        getSingleInstance().log(LogLevel.INFO, iProtoLogGroup, j, i, objArr);
    }

    public static boolean isEnabled(IProtoLogGroup iProtoLogGroup, LogLevel logLevel) {
        return getSingleInstance().isEnabled(iProtoLogGroup, logLevel);
    }

    public static synchronized void setSingleInstance(IProtoLog iProtoLog) {
        synchronized (ProtoLogImpl_411527699.class) {
            sServiceInstance = iProtoLog;
        }
    }

    public static void v(IProtoLogGroup iProtoLogGroup, long j, int i, Object... objArr) {
        getSingleInstance().log(LogLevel.VERBOSE, iProtoLogGroup, j, i, objArr);
    }

    public static void w(IProtoLogGroup iProtoLogGroup, long j, int i, Object... objArr) {
        getSingleInstance().log(LogLevel.WARN, iProtoLogGroup, j, i, objArr);
    }

    public static void wtf(IProtoLogGroup iProtoLogGroup, long j, int i, Object... objArr) {
        getSingleInstance().log(LogLevel.WTF, iProtoLogGroup, j, i, objArr);
    }
}
