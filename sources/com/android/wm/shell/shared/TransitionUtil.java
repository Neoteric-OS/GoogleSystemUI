package com.android.wm.shell.shared;

import android.app.ActivityManager;
import android.app.WindowConfiguration;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.ArrayMap;
import android.util.SparseBooleanArray;
import android.view.RemoteAnimationTarget;
import android.view.SurfaceControl;
import android.window.TransitionInfo;
import com.android.systemui.animation.RemoteAnimationRunnerCompat$1$$ExternalSyntheticOutline0;
import java.util.function.Predicate;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class TransitionUtil {

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class LeafTaskFilter implements Predicate {
        public final SparseBooleanArray mChildTaskTargets = new SparseBooleanArray();

        @Override // java.util.function.Predicate
        public final boolean test(TransitionInfo.Change change) {
            ActivityManager.RunningTaskInfo taskInfo = change.getTaskInfo();
            if (taskInfo == null) {
                return false;
            }
            boolean z = this.mChildTaskTargets.get(taskInfo.taskId);
            if (taskInfo.hasParentTask()) {
                this.mChildTaskTargets.put(taskInfo.parentTaskId, true);
            }
            return !z;
        }
    }

    public static boolean hasDisplayChange(TransitionInfo transitionInfo) {
        for (int m = RemoteAnimationRunnerCompat$1$$ExternalSyntheticOutline0.m(transitionInfo, 1); m >= 0; m--) {
            TransitionInfo.Change change = (TransitionInfo.Change) transitionInfo.getChanges().get(m);
            if (change.getMode() == 6 && change.hasFlags(32)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isClosingMode(int i) {
        return i == 2 || i == 4;
    }

    public static boolean isClosingType(int i) {
        return i == 2 || i == 4;
    }

    public static boolean isDividerBar(TransitionInfo.Change change) {
        return change.getTaskInfo() == null && !change.hasFlags(2) && !change.hasFlags(512) && change.hasFlags(8388608);
    }

    public static boolean isOpenOrCloseMode(int i) {
        return isOpeningMode(i) || isClosingMode(i);
    }

    public static boolean isOpeningMode(int i) {
        return i == 1 || i == 3;
    }

    public static boolean isOpeningType(int i) {
        return i == 1 || i == 3 || i == 7 || i == 13;
    }

    public static boolean isOrderOnly(TransitionInfo.Change change) {
        return change.getMode() == 6 && (change.getFlags() & 1048576) != 0 && change.getStartAbsBounds().equals(change.getEndAbsBounds()) && (change.getLastParent() == null || change.getLastParent().equals(change.getParent()));
    }

    public static boolean isWallpaper(TransitionInfo.Change change) {
        return change.getTaskInfo() == null && change.hasFlags(2) && !change.hasFlags(512);
    }

    public static int newModeToLegacyMode(int i) {
        if (i == 1) {
            return 0;
        }
        if (i != 2) {
            if (i == 3) {
                return 0;
            }
            if (i != 4) {
                return 2;
            }
        }
        return 1;
    }

    public static RemoteAnimationTarget newSyntheticTarget(ActivityManager.RunningTaskInfo runningTaskInfo, SurfaceControl surfaceControl, int i) {
        boolean z;
        WindowConfiguration windowConfiguration;
        int i2;
        if (runningTaskInfo != null) {
            i2 = runningTaskInfo.taskId;
            z = true ^ runningTaskInfo.isRunning;
            windowConfiguration = runningTaskInfo.configuration.windowConfiguration;
        } else {
            z = true;
            windowConfiguration = new WindowConfiguration();
            i2 = -1;
        }
        return new RemoteAnimationTarget(i2, newModeToLegacyMode(i), surfaceControl, true, (Rect) null, new Rect(0, 0, 0, 0), 0, (Point) null, new Rect(), new Rect(), windowConfiguration, z, (SurfaceControl) null, new Rect(), runningTaskInfo, false, -1);
    }

    public static RemoteAnimationTarget newTarget(TransitionInfo.Change change, int i, boolean z, TransitionInfo transitionInfo, SurfaceControl.Transaction transaction, ArrayMap arrayMap) {
        SurfaceControl surfaceControl;
        if (change.getParent() == null || (change.getFlags() & 2) == 0) {
            int rootIndexFor = rootIndexFor(change, transitionInfo);
            SurfaceControl build = new SurfaceControl.Builder().setName(change.getLeash().toString() + "_transition-leash").setContainerLayer().setHidden(false).setParent(transitionInfo.getRoot(rootIndexFor).getLeash()).build();
            int m = RemoteAnimationRunnerCompat$1$$ExternalSyntheticOutline0.m(transitionInfo, i);
            boolean isOpeningType = isOpeningType(transitionInfo.getType());
            int size = transitionInfo.getChanges().size();
            int mode = change.getMode();
            transaction.reparent(build, transitionInfo.getRoot(rootIndexFor(change, transitionInfo)).getLeash());
            Rect endAbsBounds = mode == 1 ? change.getEndAbsBounds() : change.getStartAbsBounds();
            transaction.setPosition(build, endAbsBounds.left - transitionInfo.getRoot(r11).getOffset().x, endAbsBounds.top - transitionInfo.getRoot(r11).getOffset().y);
            if (isDividerBar(change)) {
                if (isOpeningType(mode)) {
                    transaction.setAlpha(build, 0.0f);
                }
                transaction.setPosition(build, 0.0f, 0.0f);
                transaction.setLayer(build, Integer.MAX_VALUE);
            } else if ((change.getFlags() & 2) != 0) {
                if (mode == 1 || mode == 3) {
                    transaction.setLayer(build, (transitionInfo.getChanges().size() + (-size)) - m);
                } else {
                    transaction.setLayer(build, (-size) - m);
                }
            } else if (isOpeningType(mode)) {
                if (isOpeningType) {
                    transaction.setLayer(build, (transitionInfo.getChanges().size() + size) - m);
                    if ((change.getFlags() & 8) == 0) {
                        transaction.setAlpha(build, 0.0f);
                    }
                } else {
                    transaction.setLayer(build, size - m);
                }
            } else if (!isClosingType(mode)) {
                transaction.setLayer(build, (transitionInfo.getChanges().size() + size) - m);
            } else if (isOpeningType) {
                transaction.setLayer(build, size - m);
            } else {
                transaction.setLayer(build, (transitionInfo.getChanges().size() + size) - m);
            }
            transaction.reparent(change.getLeash(), build);
            transaction.setAlpha(change.getLeash(), 1.0f);
            transaction.show(change.getLeash());
            if (!isDividerBar(change)) {
                transaction.setPosition(change.getLeash(), 0.0f, 0.0f);
            }
            transaction.setLayer(change.getLeash(), 0);
            surfaceControl = build;
        } else {
            surfaceControl = change.getLeash();
        }
        if (arrayMap != null) {
            arrayMap.put(change.getLeash(), surfaceControl);
        }
        return newTarget(change, i, surfaceControl, z);
    }

    public static int rootIndexFor(TransitionInfo.Change change, TransitionInfo transitionInfo) {
        int findRootIndex = transitionInfo.findRootIndex(change.getEndDisplayId());
        if (findRootIndex >= 0) {
            return findRootIndex;
        }
        int findRootIndex2 = transitionInfo.findRootIndex(change.getStartDisplayId());
        if (findRootIndex2 >= 0) {
            return findRootIndex2;
        }
        return 0;
    }

    public static RemoteAnimationTarget newTarget(TransitionInfo.Change change, int i, SurfaceControl surfaceControl, boolean z) {
        boolean z2;
        WindowConfiguration windowConfiguration;
        int i2;
        if (isDividerBar(change)) {
            return new RemoteAnimationTarget(-1, newModeToLegacyMode(change.getMode()), surfaceControl, false, (Rect) null, (Rect) null, Integer.MAX_VALUE, new Point(0, 0), change.getStartAbsBounds(), change.getStartAbsBounds(), new WindowConfiguration(), true, (SurfaceControl) null, (Rect) null, (ActivityManager.RunningTaskInfo) null, false, 2034);
        }
        ActivityManager.RunningTaskInfo taskInfo = change.getTaskInfo();
        if (taskInfo != null) {
            i2 = taskInfo.taskId;
            z2 = !taskInfo.isRunning;
            windowConfiguration = taskInfo.configuration.windowConfiguration;
        } else {
            z2 = true;
            windowConfiguration = new WindowConfiguration();
            i2 = -1;
        }
        Rect rect = new Rect(change.getEndAbsBounds());
        rect.offsetTo(change.getEndRelOffset().x, change.getEndRelOffset().y);
        RemoteAnimationTarget remoteAnimationTarget = new RemoteAnimationTarget(i2, newModeToLegacyMode(change.getMode()), surfaceControl, z || (change.getFlags() & 4) != 0, (Rect) null, new Rect(0, 0, 0, 0), i, (Point) null, rect, new Rect(change.getEndAbsBounds()), windowConfiguration, z2, (SurfaceControl) null, new Rect(change.getStartAbsBounds()), taskInfo, change.isAllowEnterPip(), -1);
        remoteAnimationTarget.setWillShowImeOnTarget((change.getFlags() & 2048) != 0);
        remoteAnimationTarget.setRotationChange(change.getEndRotation() - change.getStartRotation());
        remoteAnimationTarget.backgroundColor = change.getBackgroundColor();
        return remoteAnimationTarget;
    }
}
