package com.google.android.apps.warp.lockscreen.presentation.uidata;

import android.graphics.drawable.Icon;
import androidx.compose.animation.FlingCalculator$FlingInfo$$ExternalSyntheticOutline0;
import androidx.compose.animation.graphics.vector.PropertyValuesHolder2D$$ExternalSyntheticOutline0;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class WarpTaskItem {
    public final Direction direction;
    public final Icon icon;
    public final TaskPosition position;
    public final float randomX;
    public final float randomY;
    public final String title;

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Direction {
        public static final /* synthetic */ Direction[] $VALUES;
        public static final Direction DOWN = null;
        public static final Direction UP;
        private final int value;

        static {
            Direction direction = new Direction("UP", 0, -1);
            UP = direction;
            Direction[] directionArr = {direction, new Direction("DOWN", 1, 1)};
            $VALUES = directionArr;
            EnumEntriesKt.enumEntries(directionArr);
        }

        public Direction(String str, int i, int i2) {
            this.value = i2;
        }

        public static Direction valueOf(String str) {
            return (Direction) Enum.valueOf(Direction.class, str);
        }

        public static Direction[] values() {
            return (Direction[]) $VALUES.clone();
        }
    }

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class TaskPosition {
        public static final /* synthetic */ TaskPosition[] $VALUES;
        public static final TaskPosition CLOSE = null;
        public static final TaskPosition FAR = null;
        public static final TaskPosition GONE = null;
        public static final TaskPosition SELECTED;

        static {
            TaskPosition taskPosition = new TaskPosition("SELECTED", 0);
            SELECTED = taskPosition;
            TaskPosition[] taskPositionArr = {taskPosition, new TaskPosition("CLOSE", 1), new TaskPosition("FAR", 2), new TaskPosition("GONE", 3)};
            $VALUES = taskPositionArr;
            EnumEntriesKt.enumEntries(taskPositionArr);
        }

        public static TaskPosition valueOf(String str) {
            return (TaskPosition) Enum.valueOf(TaskPosition.class, str);
        }

        public static TaskPosition[] values() {
            return (TaskPosition[]) $VALUES.clone();
        }
    }

    public WarpTaskItem(Icon icon, String str) {
        TaskPosition taskPosition = TaskPosition.SELECTED;
        Direction direction = Direction.UP;
        this.icon = icon;
        this.title = str;
        this.position = taskPosition;
        this.direction = direction;
        this.randomX = 0.0f;
        this.randomY = 0.0f;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof WarpTaskItem)) {
            return false;
        }
        WarpTaskItem warpTaskItem = (WarpTaskItem) obj;
        return Intrinsics.areEqual(this.icon, warpTaskItem.icon) && Intrinsics.areEqual(this.title, warpTaskItem.title) && this.position == warpTaskItem.position && this.direction == warpTaskItem.direction && Float.compare(this.randomX, warpTaskItem.randomX) == 0 && Float.compare(this.randomY, warpTaskItem.randomY) == 0;
    }

    public final int hashCode() {
        return Float.hashCode(this.randomY) + FlingCalculator$FlingInfo$$ExternalSyntheticOutline0.m((this.direction.hashCode() + ((this.position.hashCode() + PropertyValuesHolder2D$$ExternalSyntheticOutline0.m(this.title, this.icon.hashCode() * 31, 31)) * 31)) * 31, this.randomX, 31);
    }

    public final String toString() {
        return "WarpTaskItem(icon=" + this.icon + ", title=" + this.title + ", position=" + this.position + ", direction=" + this.direction + ", randomX=" + this.randomX + ", randomY=" + this.randomY + ")";
    }
}
