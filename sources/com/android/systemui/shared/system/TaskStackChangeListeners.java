package com.android.systemui.shared.system;

import android.app.ActivityManager;
import android.app.ActivityTaskManager;
import android.app.TaskStackListener;
import android.content.ComponentName;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.Trace;
import android.util.Log;
import android.window.TaskSnapshot;
import com.android.app.viewcapture.data.ViewNode;
import com.android.internal.os.SomeArgs;
import com.android.systemui.shared.recents.model.ThumbnailData;
import java.util.ArrayList;
import java.util.List;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class TaskStackChangeListeners {
    public static final TaskStackChangeListeners INSTANCE = new TaskStackChangeListeners();
    public final Impl mImpl;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class PinnedActivityInfo {
        public final String mPackageName;

        public PinnedActivityInfo(String str, int i, int i2, int i3) {
            this.mPackageName = str;
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class TestSyncHandler extends Handler {
        public Impl mCb;

        @Override // android.os.Handler
        public final boolean sendMessageAtTime(Message message, long j) {
            this.mCb.handleMessage(message);
            return true;
        }
    }

    public TaskStackChangeListeners() {
        this.mImpl = new Impl(Looper.getMainLooper());
    }

    public static TaskStackChangeListeners getTestInstance() {
        TestSyncHandler testSyncHandler = new TestSyncHandler(Looper.getMainLooper());
        TaskStackChangeListeners taskStackChangeListeners = new TaskStackChangeListeners(testSyncHandler);
        testSyncHandler.mCb = taskStackChangeListeners.mImpl;
        return taskStackChangeListeners;
    }

    public TaskStackListener getListenerImpl() {
        return this.mImpl;
    }

    public final void registerTaskStackListener(TaskStackChangeListener taskStackChangeListener) {
        synchronized (this.mImpl) {
            this.mImpl.addListener(taskStackChangeListener);
        }
    }

    public final void unregisterTaskStackListener(TaskStackChangeListener taskStackChangeListener) {
        synchronized (this.mImpl) {
            this.mImpl.removeListener(taskStackChangeListener);
        }
    }

    public TaskStackChangeListeners(TestSyncHandler testSyncHandler) {
        this.mImpl = new Impl(testSyncHandler);
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Impl extends TaskStackListener implements Handler.Callback {
        public final Handler mHandler;
        public boolean mRegistered;
        public final List mTaskStackListeners = new ArrayList();
        public final List mTmpListeners = new ArrayList();

        public Impl(Looper looper) {
            this.mHandler = new Handler(looper, this);
        }

        public final void addListener(TaskStackChangeListener taskStackChangeListener) {
            synchronized (this.mTaskStackListeners) {
                this.mTaskStackListeners.add(taskStackChangeListener);
            }
            if (this.mRegistered) {
                return;
            }
            try {
                ActivityTaskManager.getService().registerTaskStackListener(this);
                this.mRegistered = true;
            } catch (Exception e) {
                TaskStackChangeListeners taskStackChangeListeners = TaskStackChangeListeners.INSTANCE;
                Log.w("TaskStackChangeListeners", "Failed to call registerTaskStackListener", e);
            }
        }

        @Override // android.os.Handler.Callback
        public final boolean handleMessage(Message message) {
            synchronized (this.mTaskStackListeners) {
                try {
                    switch (message.what) {
                        case 1:
                            Trace.beginSection("onTaskStackChanged");
                            for (int size = ((ArrayList) this.mTaskStackListeners).size() - 1; size >= 0; size--) {
                                ((TaskStackChangeListener) ((ArrayList) this.mTaskStackListeners).get(size)).onTaskStackChanged();
                            }
                            Trace.endSection();
                            break;
                        case 2:
                            Trace.beginSection("onTaskSnapshotChanged");
                            TaskSnapshot taskSnapshot = (TaskSnapshot) message.obj;
                            ThumbnailData fromSnapshot = ThumbnailData.fromSnapshot(taskSnapshot);
                            for (int size2 = ((ArrayList) this.mTaskStackListeners).size() - 1; size2 >= 0; size2--) {
                                ((TaskStackChangeListener) ((ArrayList) this.mTaskStackListeners).get(size2)).getClass();
                            }
                            Bitmap bitmap = fromSnapshot.thumbnail;
                            if (bitmap != null) {
                                bitmap.recycle();
                            }
                            if (taskSnapshot.getHardwareBuffer() != null) {
                                taskSnapshot.getHardwareBuffer().close();
                            }
                            Trace.endSection();
                            break;
                        case 3:
                            PinnedActivityInfo pinnedActivityInfo = (PinnedActivityInfo) message.obj;
                            for (int size3 = ((ArrayList) this.mTaskStackListeners).size() - 1; size3 >= 0; size3--) {
                                TaskStackChangeListener taskStackChangeListener = (TaskStackChangeListener) ((ArrayList) this.mTaskStackListeners).get(size3);
                                String str = pinnedActivityInfo.mPackageName;
                                taskStackChangeListener.getClass();
                            }
                            break;
                        case 4:
                            for (int size4 = ((ArrayList) this.mTaskStackListeners).size() - 1; size4 >= 0; size4--) {
                                ((TaskStackChangeListener) ((ArrayList) this.mTaskStackListeners).get(size4)).getClass();
                            }
                            break;
                        case 6:
                            for (int size5 = ((ArrayList) this.mTaskStackListeners).size() - 1; size5 >= 0; size5--) {
                                TaskStackChangeListener taskStackChangeListener2 = (TaskStackChangeListener) ((ArrayList) this.mTaskStackListeners).get(size5);
                                taskStackChangeListener2.getClass();
                            }
                            break;
                        case 7:
                            for (int size6 = ((ArrayList) this.mTaskStackListeners).size() - 1; size6 >= 0; size6--) {
                                ((TaskStackChangeListener) ((ArrayList) this.mTaskStackListeners).get(size6)).getClass();
                            }
                            break;
                        case 8:
                            ActivityManager.RunningTaskInfo runningTaskInfo = (ActivityManager.RunningTaskInfo) message.obj;
                            int i = message.arg1;
                            for (int size7 = ((ArrayList) this.mTaskStackListeners).size() - 1; size7 >= 0; size7--) {
                                ((TaskStackChangeListener) ((ArrayList) this.mTaskStackListeners).get(size7)).onTaskProfileLocked(runningTaskInfo, i);
                            }
                            break;
                        case 10:
                            for (int size8 = ((ArrayList) this.mTaskStackListeners).size() - 1; size8 >= 0; size8--) {
                                ((TaskStackChangeListener) ((ArrayList) this.mTaskStackListeners).get(size8)).getClass();
                            }
                            break;
                        case ViewNode.TRANSLATIONX_FIELD_NUMBER /* 11 */:
                            for (int size9 = ((ArrayList) this.mTaskStackListeners).size() - 1; size9 >= 0; size9--) {
                                ((TaskStackChangeListener) ((ArrayList) this.mTaskStackListeners).get(size9)).getClass();
                            }
                            break;
                        case ViewNode.TRANSLATIONY_FIELD_NUMBER /* 12 */:
                            for (int size10 = ((ArrayList) this.mTaskStackListeners).size() - 1; size10 >= 0; size10--) {
                                ((TaskStackChangeListener) ((ArrayList) this.mTaskStackListeners).get(size10)).onTaskCreated((ComponentName) message.obj);
                            }
                            break;
                        case ViewNode.SCALEX_FIELD_NUMBER /* 13 */:
                            for (int size11 = ((ArrayList) this.mTaskStackListeners).size() - 1; size11 >= 0; size11--) {
                                ((TaskStackChangeListener) ((ArrayList) this.mTaskStackListeners).get(size11)).onTaskRemoved();
                            }
                            break;
                        case ViewNode.SCALEY_FIELD_NUMBER /* 14 */:
                            ActivityManager.RunningTaskInfo runningTaskInfo2 = (ActivityManager.RunningTaskInfo) message.obj;
                            for (int size12 = ((ArrayList) this.mTaskStackListeners).size() - 1; size12 >= 0; size12--) {
                                ((TaskStackChangeListener) ((ArrayList) this.mTaskStackListeners).get(size12)).onTaskMovedToFront(runningTaskInfo2);
                            }
                            break;
                        case 15:
                            for (int size13 = ((ArrayList) this.mTaskStackListeners).size() - 1; size13 >= 0; size13--) {
                                ((TaskStackChangeListener) ((ArrayList) this.mTaskStackListeners).get(size13)).onActivityRequestedOrientationChanged(message.arg1);
                            }
                            break;
                        case 16:
                            for (int size14 = ((ArrayList) this.mTaskStackListeners).size() - 1; size14 >= 0; size14--) {
                                ((TaskStackChangeListener) ((ArrayList) this.mTaskStackListeners).get(size14)).getClass();
                            }
                            break;
                        case ViewNode.CLIPCHILDREN_FIELD_NUMBER /* 17 */:
                            for (int size15 = ((ArrayList) this.mTaskStackListeners).size() - 1; size15 >= 0; size15--) {
                                TaskStackChangeListener taskStackChangeListener3 = (TaskStackChangeListener) ((ArrayList) this.mTaskStackListeners).get(size15);
                                taskStackChangeListener3.getClass();
                            }
                            break;
                        case ViewNode.VISIBILITY_FIELD_NUMBER /* 18 */:
                            for (int size16 = ((ArrayList) this.mTaskStackListeners).size() - 1; size16 >= 0; size16--) {
                                ((TaskStackChangeListener) ((ArrayList) this.mTaskStackListeners).get(size16)).getClass();
                            }
                            break;
                        case ViewNode.ELEVATION_FIELD_NUMBER /* 19 */:
                            for (int size17 = ((ArrayList) this.mTaskStackListeners).size() - 1; size17 >= 0; size17--) {
                                ((TaskStackChangeListener) ((ArrayList) this.mTaskStackListeners).get(size17)).getClass();
                            }
                            break;
                        case 20:
                            for (int size18 = ((ArrayList) this.mTaskStackListeners).size() - 1; size18 >= 0; size18--) {
                                ((TaskStackChangeListener) ((ArrayList) this.mTaskStackListeners).get(size18)).getClass();
                            }
                            break;
                        case 21:
                            for (int size19 = ((ArrayList) this.mTaskStackListeners).size() - 1; size19 >= 0; size19--) {
                                ((TaskStackChangeListener) ((ArrayList) this.mTaskStackListeners).get(size19)).getClass();
                            }
                            break;
                        case 22:
                            for (int size20 = ((ArrayList) this.mTaskStackListeners).size() - 1; size20 >= 0; size20--) {
                                ((TaskStackChangeListener) ((ArrayList) this.mTaskStackListeners).get(size20)).getClass();
                            }
                            break;
                        case 23:
                            for (int size21 = ((ArrayList) this.mTaskStackListeners).size() - 1; size21 >= 0; size21--) {
                                ((TaskStackChangeListener) ((ArrayList) this.mTaskStackListeners).get(size21)).onLockTaskModeChanged(message.arg1);
                            }
                            break;
                        case 24:
                            Trace.beginSection("onTaskSnapshotInvalidated");
                            new Rect();
                            new Rect();
                            for (int size22 = ((ArrayList) this.mTaskStackListeners).size() - 1; size22 >= 0; size22--) {
                                ((TaskStackChangeListener) ((ArrayList) this.mTaskStackListeners).get(size22)).getClass();
                            }
                            Trace.endSection();
                            break;
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
            Object obj = message.obj;
            if (obj instanceof SomeArgs) {
                ((SomeArgs) obj).recycle();
            }
            return true;
        }

        public final void onActivityDismissingDockedTask() {
            this.mHandler.sendEmptyMessage(7);
        }

        public final void onActivityForcedResizable(String str, int i, int i2) {
            this.mHandler.obtainMessage(6, i, i2, str).sendToTarget();
        }

        public final void onActivityLaunchOnSecondaryDisplayFailed(ActivityManager.RunningTaskInfo runningTaskInfo, int i) {
            this.mHandler.obtainMessage(11, i, 0, runningTaskInfo).sendToTarget();
        }

        public final void onActivityLaunchOnSecondaryDisplayRerouted(ActivityManager.RunningTaskInfo runningTaskInfo, int i) {
            this.mHandler.obtainMessage(16, i, 0, runningTaskInfo).sendToTarget();
        }

        public final void onActivityPinned(String str, int i, int i2, int i3) {
            this.mHandler.removeMessages(3);
            this.mHandler.obtainMessage(3, new PinnedActivityInfo(str, i, i2, i3)).sendToTarget();
        }

        public final void onActivityRequestedOrientationChanged(int i, int i2) {
            this.mHandler.obtainMessage(15, i, i2).sendToTarget();
        }

        public final void onActivityRestartAttempt(ActivityManager.RunningTaskInfo runningTaskInfo, boolean z, boolean z2, boolean z3) {
            SomeArgs obtain = SomeArgs.obtain();
            obtain.arg1 = runningTaskInfo;
            obtain.argi1 = z ? 1 : 0;
            obtain.argi2 = z2 ? 1 : 0;
            obtain.argi3 = z3 ? 1 : 0;
            this.mHandler.removeMessages(4);
            this.mHandler.obtainMessage(4, obtain).sendToTarget();
        }

        public final void onActivityRotation(int i) {
            this.mHandler.obtainMessage(22, i, 0).sendToTarget();
        }

        public final void onActivityUnpinned() {
            this.mHandler.removeMessages(10);
            this.mHandler.sendEmptyMessage(10);
        }

        public final void onBackPressedOnTaskRoot(ActivityManager.RunningTaskInfo runningTaskInfo) {
            this.mHandler.obtainMessage(17, runningTaskInfo).sendToTarget();
        }

        public final void onLockTaskModeChanged(int i) {
            this.mHandler.obtainMessage(23, i, 0).sendToTarget();
        }

        public final void onRecentTaskListFrozenChanged(boolean z) {
            this.mHandler.obtainMessage(20, z ? 1 : 0, 0).sendToTarget();
        }

        public final void onRecentTaskListUpdated() {
            this.mHandler.obtainMessage(19).sendToTarget();
        }

        public final void onTaskCreated(int i, ComponentName componentName) {
            this.mHandler.obtainMessage(12, i, 0, componentName).sendToTarget();
        }

        public final void onTaskDescriptionChanged(ActivityManager.RunningTaskInfo runningTaskInfo) {
            this.mHandler.obtainMessage(21, runningTaskInfo).sendToTarget();
        }

        public final void onTaskDisplayChanged(int i, int i2) {
            this.mHandler.obtainMessage(18, i, i2).sendToTarget();
        }

        public final void onTaskMovedToFront(ActivityManager.RunningTaskInfo runningTaskInfo) {
            this.mHandler.obtainMessage(14, runningTaskInfo).sendToTarget();
        }

        public final void onTaskProfileLocked(ActivityManager.RunningTaskInfo runningTaskInfo, int i) {
            this.mHandler.obtainMessage(8, i, 0, runningTaskInfo).sendToTarget();
        }

        public final void onTaskRemoved(int i) {
            this.mHandler.obtainMessage(13, i, 0).sendToTarget();
        }

        public final void onTaskSnapshotChanged(int i, TaskSnapshot taskSnapshot) {
            this.mHandler.obtainMessage(2, i, 0, taskSnapshot).sendToTarget();
        }

        public final void onTaskSnapshotInvalidated(int i) {
            this.mHandler.obtainMessage(24, i, 0).sendToTarget();
        }

        public final void onTaskStackChanged() {
            synchronized (this.mTaskStackListeners) {
                this.mTmpListeners.addAll(this.mTaskStackListeners);
            }
            for (int size = ((ArrayList) this.mTmpListeners).size() - 1; size >= 0; size--) {
                ((TaskStackChangeListener) ((ArrayList) this.mTmpListeners).get(size)).onTaskStackChangedBackground();
            }
            this.mTmpListeners.clear();
            this.mHandler.removeMessages(1);
            this.mHandler.sendEmptyMessage(1);
        }

        public final void removeListener(TaskStackChangeListener taskStackChangeListener) {
            boolean isEmpty;
            synchronized (this.mTaskStackListeners) {
                this.mTaskStackListeners.remove(taskStackChangeListener);
                isEmpty = this.mTaskStackListeners.isEmpty();
            }
            if (isEmpty && this.mRegistered) {
                try {
                    ActivityTaskManager.getService().unregisterTaskStackListener(this);
                    this.mRegistered = false;
                } catch (Exception e) {
                    TaskStackChangeListeners taskStackChangeListeners = TaskStackChangeListeners.INSTANCE;
                    Log.w("TaskStackChangeListeners", "Failed to call unregisterTaskStackListener", e);
                }
            }
        }

        public Impl(TestSyncHandler testSyncHandler) {
            this.mHandler = testSyncHandler;
        }
    }
}
