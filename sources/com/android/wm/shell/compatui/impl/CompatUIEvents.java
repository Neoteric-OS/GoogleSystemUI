package com.android.wm.shell.compatui.impl;

import androidx.appsearch.safeparcel.PropertyConfigParcel$JoinableConfigParcel$$ExternalSyntheticOutline0;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class CompatUIEvents {
    public final int eventId;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class SizeCompatRestartButtonAppeared extends CompatUIEvents {
        public final int taskId;

        public SizeCompatRestartButtonAppeared(int i) {
            super(0);
            this.taskId = i;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return (obj instanceof SizeCompatRestartButtonAppeared) && this.taskId == ((SizeCompatRestartButtonAppeared) obj).taskId;
        }

        public final int hashCode() {
            return Integer.hashCode(this.taskId);
        }

        public final String toString() {
            return PropertyConfigParcel$JoinableConfigParcel$$ExternalSyntheticOutline0.m(new StringBuilder("SizeCompatRestartButtonAppeared(taskId="), this.taskId, ")");
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class SizeCompatRestartButtonClicked extends CompatUIEvents {
        public final int taskId;

        public SizeCompatRestartButtonClicked(int i) {
            super(1);
            this.taskId = i;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return (obj instanceof SizeCompatRestartButtonClicked) && this.taskId == ((SizeCompatRestartButtonClicked) obj).taskId;
        }

        public final int hashCode() {
            return Integer.hashCode(this.taskId);
        }

        public final String toString() {
            return PropertyConfigParcel$JoinableConfigParcel$$ExternalSyntheticOutline0.m(new StringBuilder("SizeCompatRestartButtonClicked(taskId="), this.taskId, ")");
        }
    }

    public CompatUIEvents(int i) {
        this.eventId = i;
    }
}
