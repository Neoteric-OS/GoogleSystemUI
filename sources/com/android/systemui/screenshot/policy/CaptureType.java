package com.android.systemui.screenshot.policy;

import android.graphics.Rect;
import androidx.appsearch.safeparcel.PropertyConfigParcel$JoinableConfigParcel$$ExternalSyntheticOutline0;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public interface CaptureType {

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class FullScreen implements CaptureType {
        public final int displayId;

        public FullScreen(int i) {
            this.displayId = i;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return (obj instanceof FullScreen) && this.displayId == ((FullScreen) obj).displayId;
        }

        public final int hashCode() {
            return Integer.hashCode(this.displayId);
        }

        public final String toString() {
            return PropertyConfigParcel$JoinableConfigParcel$$ExternalSyntheticOutline0.m(new StringBuilder("FullScreen(displayId="), this.displayId, ")");
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class IsolatedTask implements CaptureType {
        public final Rect taskBounds;
        public final int taskId;

        public IsolatedTask(int i, Rect rect) {
            this.taskId = i;
            this.taskBounds = rect;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof IsolatedTask)) {
                return false;
            }
            IsolatedTask isolatedTask = (IsolatedTask) obj;
            return this.taskId == isolatedTask.taskId && Intrinsics.areEqual(this.taskBounds, isolatedTask.taskBounds);
        }

        public final int hashCode() {
            int hashCode = Integer.hashCode(this.taskId) * 31;
            Rect rect = this.taskBounds;
            return hashCode + (rect == null ? 0 : rect.hashCode());
        }

        public final String toString() {
            return "IsolatedTask(taskId=" + this.taskId + ", taskBounds=" + this.taskBounds + ")";
        }
    }
}
