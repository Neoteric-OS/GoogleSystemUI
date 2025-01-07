package com.android.systemui.mediaprojection.data.model;

import android.app.ActivityManager;
import androidx.activity.ComponentActivity$activityResultRegistry$1$$ExternalSyntheticOutline0;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public interface MediaProjectionState {

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class NotProjecting implements MediaProjectionState {
        public static final NotProjecting INSTANCE = new NotProjecting();

        public final boolean equals(Object obj) {
            return this == obj || (obj instanceof NotProjecting);
        }

        public final int hashCode() {
            return -570342309;
        }

        public final String toString() {
            return "NotProjecting";
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public abstract class Projecting implements MediaProjectionState {

        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        public final class EntireScreen extends Projecting {
            public final String hostDeviceName;
            public final String hostPackage;

            public EntireScreen(String str, String str2) {
                this.hostPackage = str;
                this.hostDeviceName = str2;
            }

            public final boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                if (!(obj instanceof EntireScreen)) {
                    return false;
                }
                EntireScreen entireScreen = (EntireScreen) obj;
                return Intrinsics.areEqual(this.hostPackage, entireScreen.hostPackage) && Intrinsics.areEqual(this.hostDeviceName, entireScreen.hostDeviceName);
            }

            @Override // com.android.systemui.mediaprojection.data.model.MediaProjectionState.Projecting
            public final String getHostDeviceName() {
                return this.hostDeviceName;
            }

            @Override // com.android.systemui.mediaprojection.data.model.MediaProjectionState.Projecting
            public final String getHostPackage() {
                return this.hostPackage;
            }

            public final int hashCode() {
                int hashCode = this.hostPackage.hashCode() * 31;
                String str = this.hostDeviceName;
                return hashCode + (str == null ? 0 : str.hashCode());
            }

            public final String toString() {
                StringBuilder sb = new StringBuilder("EntireScreen(hostPackage=");
                sb.append(this.hostPackage);
                sb.append(", hostDeviceName=");
                return ComponentActivity$activityResultRegistry$1$$ExternalSyntheticOutline0.m(sb, this.hostDeviceName, ")");
            }
        }

        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        public final class SingleTask extends Projecting {
            public final String hostDeviceName;
            public final String hostPackage;
            public final ActivityManager.RunningTaskInfo task;

            public SingleTask(String str, String str2, ActivityManager.RunningTaskInfo runningTaskInfo) {
                this.hostPackage = str;
                this.hostDeviceName = str2;
                this.task = runningTaskInfo;
            }

            public final boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                if (!(obj instanceof SingleTask)) {
                    return false;
                }
                SingleTask singleTask = (SingleTask) obj;
                return Intrinsics.areEqual(this.hostPackage, singleTask.hostPackage) && Intrinsics.areEqual(this.hostDeviceName, singleTask.hostDeviceName) && Intrinsics.areEqual(this.task, singleTask.task);
            }

            @Override // com.android.systemui.mediaprojection.data.model.MediaProjectionState.Projecting
            public final String getHostDeviceName() {
                return this.hostDeviceName;
            }

            @Override // com.android.systemui.mediaprojection.data.model.MediaProjectionState.Projecting
            public final String getHostPackage() {
                return this.hostPackage;
            }

            public final int hashCode() {
                int hashCode = this.hostPackage.hashCode() * 31;
                String str = this.hostDeviceName;
                return this.task.hashCode() + ((hashCode + (str == null ? 0 : str.hashCode())) * 31);
            }

            public final String toString() {
                return "SingleTask(hostPackage=" + this.hostPackage + ", hostDeviceName=" + this.hostDeviceName + ", task=" + this.task + ")";
            }
        }

        public abstract String getHostDeviceName();

        public abstract String getHostPackage();
    }
}
