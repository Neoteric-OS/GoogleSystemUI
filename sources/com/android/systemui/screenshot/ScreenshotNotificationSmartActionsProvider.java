package com.android.systemui.screenshot;

import android.content.ComponentName;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.UserHandle;
import java.util.Collections;
import java.util.concurrent.CompletableFuture;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public class ScreenshotNotificationSmartActionsProvider {

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class ScreenshotOp {
        public static final /* synthetic */ ScreenshotOp[] $VALUES;
        public static final ScreenshotOp REQUEST_SMART_ACTIONS;
        public static final ScreenshotOp RETRIEVE_SMART_ACTIONS;
        public static final ScreenshotOp WAIT_FOR_SMART_ACTIONS;

        /* JADX INFO: Fake field, exist only in values array */
        ScreenshotOp EF0;

        static {
            ScreenshotOp screenshotOp = new ScreenshotOp("OP_UNKNOWN", 0);
            ScreenshotOp screenshotOp2 = new ScreenshotOp("RETRIEVE_SMART_ACTIONS", 1);
            RETRIEVE_SMART_ACTIONS = screenshotOp2;
            ScreenshotOp screenshotOp3 = new ScreenshotOp("REQUEST_SMART_ACTIONS", 2);
            REQUEST_SMART_ACTIONS = screenshotOp3;
            ScreenshotOp screenshotOp4 = new ScreenshotOp("WAIT_FOR_SMART_ACTIONS", 3);
            WAIT_FOR_SMART_ACTIONS = screenshotOp4;
            $VALUES = new ScreenshotOp[]{screenshotOp, screenshotOp2, screenshotOp3, screenshotOp4};
        }

        public static ScreenshotOp valueOf(String str) {
            return (ScreenshotOp) Enum.valueOf(ScreenshotOp.class, str);
        }

        public static ScreenshotOp[] values() {
            return (ScreenshotOp[]) $VALUES.clone();
        }
    }

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class ScreenshotOpStatus {
        public static final /* synthetic */ ScreenshotOpStatus[] $VALUES;
        public static final ScreenshotOpStatus ERROR;
        public static final ScreenshotOpStatus SUCCESS;
        public static final ScreenshotOpStatus TIMEOUT;

        /* JADX INFO: Fake field, exist only in values array */
        ScreenshotOpStatus EF0;

        static {
            ScreenshotOpStatus screenshotOpStatus = new ScreenshotOpStatus("OP_STATUS_UNKNOWN", 0);
            ScreenshotOpStatus screenshotOpStatus2 = new ScreenshotOpStatus("SUCCESS", 1);
            SUCCESS = screenshotOpStatus2;
            ScreenshotOpStatus screenshotOpStatus3 = new ScreenshotOpStatus("ERROR", 2);
            ERROR = screenshotOpStatus3;
            ScreenshotOpStatus screenshotOpStatus4 = new ScreenshotOpStatus("TIMEOUT", 3);
            TIMEOUT = screenshotOpStatus4;
            $VALUES = new ScreenshotOpStatus[]{screenshotOpStatus, screenshotOpStatus2, screenshotOpStatus3, screenshotOpStatus4};
        }

        public static ScreenshotOpStatus valueOf(String str) {
            return (ScreenshotOpStatus) Enum.valueOf(ScreenshotOpStatus.class, str);
        }

        public static ScreenshotOpStatus[] values() {
            return (ScreenshotOpStatus[]) $VALUES.clone();
        }
    }

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class ScreenshotSmartActionType {
        public static final /* synthetic */ ScreenshotSmartActionType[] $VALUES;
        public static final ScreenshotSmartActionType QUICK_SHARE_ACTION;
        public static final ScreenshotSmartActionType REGULAR_SMART_ACTIONS;

        static {
            ScreenshotSmartActionType screenshotSmartActionType = new ScreenshotSmartActionType("REGULAR_SMART_ACTIONS", 0);
            REGULAR_SMART_ACTIONS = screenshotSmartActionType;
            ScreenshotSmartActionType screenshotSmartActionType2 = new ScreenshotSmartActionType("QUICK_SHARE_ACTION", 1);
            QUICK_SHARE_ACTION = screenshotSmartActionType2;
            $VALUES = new ScreenshotSmartActionType[]{screenshotSmartActionType, screenshotSmartActionType2};
        }

        public static ScreenshotSmartActionType valueOf(String str) {
            return (ScreenshotSmartActionType) Enum.valueOf(ScreenshotSmartActionType.class, str);
        }

        public static ScreenshotSmartActionType[] values() {
            return (ScreenshotSmartActionType[]) $VALUES.clone();
        }
    }

    public CompletableFuture getActions(String str, Uri uri, Bitmap bitmap, ComponentName componentName, ScreenshotSmartActionType screenshotSmartActionType, UserHandle userHandle) {
        return CompletableFuture.completedFuture(Collections.emptyList());
    }

    public void notifyAction(String str, String str2, Intent intent) {
    }

    public void notifyOp(String str, ScreenshotOp screenshotOp, ScreenshotOpStatus screenshotOpStatus, long j) {
    }
}
