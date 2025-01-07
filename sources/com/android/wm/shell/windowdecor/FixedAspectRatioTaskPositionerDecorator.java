package com.android.wm.shell.windowdecor;

import android.app.ActivityManager;
import android.graphics.PointF;
import android.graphics.Rect;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class FixedAspectRatioTaskPositionerDecorator implements TaskPositioner {
    public int edgeResizeCtrlType;
    public boolean isTaskPortrait;
    public int originalCtrlType;
    public float startingAspectRatio;
    public final TaskPositioner taskPositioner;
    public final DesktopModeWindowDecoration windowDecoration;
    public final Rect lastRepositionedBounds = new Rect();
    public final PointF startingPoint = new PointF();
    public final PointF lastValidPoint = new PointF();

    public FixedAspectRatioTaskPositionerDecorator(DesktopModeWindowDecoration desktopModeWindowDecoration, TaskPositioner taskPositioner) {
        this.taskPositioner = taskPositioner;
        this.windowDecoration = desktopModeWindowDecoration;
    }

    public static boolean isBottomRightOrTopLeftCorner(int i) {
        return i == 10 || i == 5;
    }

    public final Rect dragAdjustedEnd(float f, float f2) {
        float abs = Math.abs(f - this.lastValidPoint.x);
        float abs2 = Math.abs(f2 - this.lastValidPoint.y);
        TaskPositioner taskPositioner = this.taskPositioner;
        return abs2 < abs ? taskPositioner.onDragPositioningEnd(getScaledChangeForX(f2), f2) : taskPositioner.onDragPositioningEnd(f, getScaledChangeForY(f));
    }

    public final Rect dragAdjustedMove(float f, float f2) {
        float abs = Math.abs(f - this.lastValidPoint.x);
        float abs2 = Math.abs(f2 - this.lastValidPoint.y);
        TaskPositioner taskPositioner = this.taskPositioner;
        if (abs2 < abs) {
            this.lastValidPoint.set(getScaledChangeForX(f2), f2);
            return taskPositioner.onDragPositioningMove(getScaledChangeForX(f2), f2);
        }
        this.lastValidPoint.set(f, getScaledChangeForY(f));
        return taskPositioner.onDragPositioningMove(f, getScaledChangeForY(f));
    }

    public final Rect getBounds(ActivityManager.RunningTaskInfo runningTaskInfo) {
        return runningTaskInfo.configuration.windowConfiguration.getBounds();
    }

    public final float getScaledChangeForX(float f) {
        float f2 = f - this.startingPoint.y;
        float f3 = this.isTaskPortrait ? f2 / this.startingAspectRatio : f2 * this.startingAspectRatio;
        return (isBottomRightOrTopLeftCorner(this.originalCtrlType) || isBottomRightOrTopLeftCorner(this.edgeResizeCtrlType)) ? this.startingPoint.x + f3 : this.startingPoint.x - f3;
    }

    public final float getScaledChangeForY(float f) {
        float f2 = f - this.startingPoint.x;
        float f3 = this.isTaskPortrait ? f2 * this.startingAspectRatio : f2 / this.startingAspectRatio;
        return (isBottomRightOrTopLeftCorner(this.originalCtrlType) || isBottomRightOrTopLeftCorner(this.edgeResizeCtrlType)) ? this.startingPoint.y + f3 : this.startingPoint.y - f3;
    }

    @Override // com.android.wm.shell.windowdecor.TaskPositioner
    public final boolean isResizingOrAnimating() {
        return this.taskPositioner.isResizingOrAnimating();
    }

    @Override // com.android.wm.shell.windowdecor.TaskPositioner
    public final Rect onDragPositioningEnd(float f, float f2) {
        boolean requiresFixedAspectRatio = requiresFixedAspectRatio();
        TaskPositioner taskPositioner = this.taskPositioner;
        if (!requiresFixedAspectRatio) {
            return taskPositioner.onDragPositioningEnd(f, f2);
        }
        PointF pointF = this.lastValidPoint;
        float f3 = pointF.x;
        float f4 = f - f3;
        float f5 = pointF.y;
        float f6 = f2 - f5;
        switch (this.originalCtrlType) {
            case 5:
            case 10:
                if ((f4 > 0.0f && f6 > 0.0f) || (f4 < 0.0f && f6 < 0.0f)) {
                    break;
                } else {
                    break;
                }
            case 6:
            case 9:
                if ((f4 > 0.0f && f6 < 0.0f) || (f4 < 0.0f && f6 > 0.0f)) {
                    break;
                } else {
                    break;
                }
                break;
        }
        return taskPositioner.onDragPositioningEnd(f, f2);
    }

    @Override // com.android.wm.shell.windowdecor.TaskPositioner
    public final Rect onDragPositioningMove(float f, float f2) {
        boolean requiresFixedAspectRatio = requiresFixedAspectRatio();
        TaskPositioner taskPositioner = this.taskPositioner;
        if (!requiresFixedAspectRatio) {
            return taskPositioner.onDragPositioningMove(f, f2);
        }
        PointF pointF = this.lastValidPoint;
        float f3 = f - pointF.x;
        float f4 = f2 - pointF.y;
        switch (this.originalCtrlType) {
            case 1:
            case 2:
                float scaledChangeForY = getScaledChangeForY(f);
                this.lastValidPoint.set(f, scaledChangeForY);
                this.lastRepositionedBounds.set(taskPositioner.onDragPositioningMove(f, scaledChangeForY));
                break;
            case 4:
            case 8:
                float scaledChangeForX = getScaledChangeForX(f2);
                this.lastValidPoint.set(scaledChangeForX, f2);
                this.lastRepositionedBounds.set(taskPositioner.onDragPositioningMove(scaledChangeForX, f2));
                break;
            case 5:
            case 10:
                if ((f3 > 0.0f && f4 > 0.0f) || (f3 < 0.0f && f4 < 0.0f)) {
                    this.lastRepositionedBounds.set(dragAdjustedMove(f, f2));
                    break;
                }
                break;
            case 6:
            case 9:
                if ((f3 > 0.0f && f4 < 0.0f) || (f3 < 0.0f && f4 > 0.0f)) {
                    this.lastRepositionedBounds.set(dragAdjustedMove(f, f2));
                    break;
                }
                break;
        }
        return this.lastRepositionedBounds;
    }

    @Override // com.android.wm.shell.windowdecor.TaskPositioner
    public final Rect onDragPositioningStart(int i, float f, float f2) {
        Rect onDragPositioningStart;
        this.originalCtrlType = i;
        boolean requiresFixedAspectRatio = requiresFixedAspectRatio();
        TaskPositioner taskPositioner = this.taskPositioner;
        if (!requiresFixedAspectRatio) {
            return taskPositioner.onDragPositioningStart(this.originalCtrlType, f, f2);
        }
        this.lastRepositionedBounds.set(getBounds(this.windowDecoration.mTaskInfo));
        this.startingPoint.set(f, f2);
        this.lastValidPoint.set(f, f2);
        int width = this.lastRepositionedBounds.width();
        int height = this.lastRepositionedBounds.height();
        this.startingAspectRatio = Math.max(width, height) / Math.min(width, height);
        this.isTaskPortrait = width <= height;
        Rect rect = this.lastRepositionedBounds;
        int i2 = this.originalCtrlType;
        if (i2 == 1 || i2 == 2) {
            int i3 = i2 + (f2 < ((float) ((height / 2) + rect.top)) ? 4 : 8);
            this.edgeResizeCtrlType = i3;
            onDragPositioningStart = taskPositioner.onDragPositioningStart(i3, f, f2);
        } else if (i2 == 4 || i2 == 8) {
            int i4 = i2 + (f >= ((float) ((width / 2) + rect.left)) ? 2 : 1);
            this.edgeResizeCtrlType = i4;
            onDragPositioningStart = taskPositioner.onDragPositioningStart(i4, f, f2);
        } else {
            this.edgeResizeCtrlType = 0;
            onDragPositioningStart = taskPositioner.onDragPositioningStart(i2, f, f2);
        }
        rect.set(onDragPositioningStart);
        return this.lastRepositionedBounds;
    }

    public final boolean requiresFixedAspectRatio() {
        int i = this.originalCtrlType;
        return (((i & 4) == 0 && (i & 8) == 0 && (i & 1) == 0 && (i & 2) == 0) || this.windowDecoration.mTaskInfo.isResizeable) ? false : true;
    }
}
