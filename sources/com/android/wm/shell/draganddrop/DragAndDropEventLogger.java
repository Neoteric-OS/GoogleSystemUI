package com.android.wm.shell.draganddrop;

import android.content.pm.ActivityInfo;
import com.android.internal.logging.InstanceId;
import com.android.internal.logging.InstanceIdSequence;
import com.android.internal.logging.UiEventLogger;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class DragAndDropEventLogger {
    public ActivityInfo mActivityInfo;
    public final InstanceIdSequence mIdSequence = new InstanceIdSequence(Integer.MAX_VALUE);
    public InstanceId mInstanceId;
    public final UiEventLogger mUiEventLogger;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public enum DragAndDropUiEventEnum implements UiEventLogger.UiEventEnum {
        GLOBAL_APP_DRAG_START_ACTIVITY(884),
        GLOBAL_APP_DRAG_START_SHORTCUT(885),
        GLOBAL_APP_DRAG_START_TASK(888),
        GLOBAL_APP_DRAG_DROPPED(887),
        GLOBAL_APP_DRAG_END(886);

        private final int mId;

        DragAndDropUiEventEnum(int i) {
            this.mId = i;
        }

        public final int getId() {
            return this.mId;
        }
    }

    public DragAndDropEventLogger(UiEventLogger uiEventLogger) {
        this.mUiEventLogger = uiEventLogger;
    }

    public final void log(DragAndDropUiEventEnum dragAndDropUiEventEnum, ActivityInfo activityInfo) {
        this.mUiEventLogger.logWithInstanceId(dragAndDropUiEventEnum, activityInfo == null ? 0 : activityInfo.applicationInfo.uid, activityInfo == null ? null : activityInfo.applicationInfo.packageName, this.mInstanceId);
    }
}
