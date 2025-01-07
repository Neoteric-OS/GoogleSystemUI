package com.android.wm.shell.desktopmode;

import android.content.Context;
import android.graphics.Region;
import android.util.ArrayMap;
import android.util.ArraySet;
import android.util.SparseArray;
import android.window.WindowContainerToken;
import androidx.core.util.SparseArrayKt$valueIterator$1;
import com.android.internal.protolog.ProtoLog;
import com.android.systemui.navigationbar.gestural.EdgeBackGestureHandler$$ExternalSyntheticLambda1;
import com.android.wm.shell.desktopmode.persistence.DesktopPersistentRepository;
import com.android.wm.shell.protolog.ShellProtoLogGroup;
import com.android.wm.shell.shared.desktopmode.DesktopModeStatus;
import com.android.wm.shell.sysui.ShellInit;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Executor;
import kotlin.collections.CollectionsKt;
import kotlin.collections.EmptyList;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SpreadBuilder;
import kotlin.sequences.ConstrainedOnceSequence;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequencesKt;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class DesktopModeTaskRepository {
    public final Context context;
    public Executor desktopGestureExclusionExecutor;
    public EdgeBackGestureHandler$$ExternalSyntheticLambda1 desktopGestureExclusionListener;
    public final DesktopPersistentRepository persistentRepository;
    public WindowContainerToken wallpaperActivityToken;
    public final ArraySet activeTasksListeners = new ArraySet();
    public final ArrayMap visibleTasksListeners = new ArrayMap();
    public final SparseArray desktopExclusionRegions = new SparseArray();
    public final SparseArray boundsBeforeMaximizeByTaskId = new SparseArray();
    public final DesktopModeTaskRepository$desktopTaskDataByDisplayId$1 desktopTaskDataByDisplayId = new DesktopModeTaskRepository$desktopTaskDataByDisplayId$1();

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.wm.shell.desktopmode.DesktopModeTaskRepository$1, reason: invalid class name */
    public final /* synthetic */ class AnonymousClass1 implements Runnable {
        public final /* synthetic */ int $r8$classId;
        public final /* synthetic */ DesktopModeTaskRepository $tmp0;

        public /* synthetic */ AnonymousClass1(DesktopModeTaskRepository desktopModeTaskRepository, int i) {
            this.$r8$classId = i;
            this.$tmp0 = desktopModeTaskRepository;
        }

        @Override // java.lang.Runnable
        public final void run() {
            switch (this.$r8$classId) {
                case 0:
                    this.$tmp0.getClass();
                    break;
                case 1:
                    DesktopModeTaskRepository desktopModeTaskRepository = this.$tmp0;
                    EdgeBackGestureHandler$$ExternalSyntheticLambda1 edgeBackGestureHandler$$ExternalSyntheticLambda1 = desktopModeTaskRepository.desktopGestureExclusionListener;
                    if (edgeBackGestureHandler$$ExternalSyntheticLambda1 != null) {
                        edgeBackGestureHandler$$ExternalSyntheticLambda1.accept(DesktopModeTaskRepository.access$calculateDesktopExclusionRegion(desktopModeTaskRepository));
                        break;
                    }
                    break;
                case 2:
                    DesktopModeTaskRepository desktopModeTaskRepository2 = this.$tmp0;
                    EdgeBackGestureHandler$$ExternalSyntheticLambda1 edgeBackGestureHandler$$ExternalSyntheticLambda12 = desktopModeTaskRepository2.desktopGestureExclusionListener;
                    if (edgeBackGestureHandler$$ExternalSyntheticLambda12 != null) {
                        edgeBackGestureHandler$$ExternalSyntheticLambda12.accept(DesktopModeTaskRepository.access$calculateDesktopExclusionRegion(desktopModeTaskRepository2));
                        break;
                    }
                    break;
                default:
                    DesktopModeTaskRepository desktopModeTaskRepository3 = this.$tmp0;
                    EdgeBackGestureHandler$$ExternalSyntheticLambda1 edgeBackGestureHandler$$ExternalSyntheticLambda13 = desktopModeTaskRepository3.desktopGestureExclusionListener;
                    if (edgeBackGestureHandler$$ExternalSyntheticLambda13 != null) {
                        edgeBackGestureHandler$$ExternalSyntheticLambda13.accept(DesktopModeTaskRepository.access$calculateDesktopExclusionRegion(desktopModeTaskRepository3));
                        break;
                    }
                    break;
            }
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public interface ActiveTasksListener {
        void onActiveTasksChanged(int i);
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class DesktopTaskData {
        public final ArraySet activeTasks;
        public final ArraySet closingTasks;
        public final ArrayList freeformTasksInZOrder;
        public final ArraySet minimizedTasks;
        public final ArraySet visibleTasks;

        public DesktopTaskData(ArraySet arraySet, ArraySet arraySet2, ArraySet arraySet3, ArraySet arraySet4, ArrayList arrayList) {
            this.activeTasks = arraySet;
            this.visibleTasks = arraySet2;
            this.minimizedTasks = arraySet3;
            this.closingTasks = arraySet4;
            this.freeformTasksInZOrder = arrayList;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof DesktopTaskData)) {
                return false;
            }
            DesktopTaskData desktopTaskData = (DesktopTaskData) obj;
            return Intrinsics.areEqual(this.activeTasks, desktopTaskData.activeTasks) && Intrinsics.areEqual(this.visibleTasks, desktopTaskData.visibleTasks) && Intrinsics.areEqual(this.minimizedTasks, desktopTaskData.minimizedTasks) && Intrinsics.areEqual(this.closingTasks, desktopTaskData.closingTasks) && Intrinsics.areEqual(this.freeformTasksInZOrder, desktopTaskData.freeformTasksInZOrder);
        }

        public final int hashCode() {
            return this.freeformTasksInZOrder.hashCode() + ((this.closingTasks.hashCode() + ((this.minimizedTasks.hashCode() + ((this.visibleTasks.hashCode() + (this.activeTasks.hashCode() * 31)) * 31)) * 31)) * 31);
        }

        public final String toString() {
            return "DesktopTaskData(activeTasks=" + this.activeTasks + ", visibleTasks=" + this.visibleTasks + ", minimizedTasks=" + this.minimizedTasks + ", closingTasks=" + this.closingTasks + ", freeformTasksInZOrder=" + this.freeformTasksInZOrder + ")";
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public interface VisibleTasksListener {
        void onTasksVisibilityChanged(int i, int i2);
    }

    public DesktopModeTaskRepository(Context context, ShellInit shellInit, DesktopPersistentRepository desktopPersistentRepository, CoroutineScope coroutineScope) {
        if (DesktopModeStatus.canEnterDesktopMode(context)) {
            shellInit.addInitCallback(new AnonymousClass1(this, 0), this);
        }
    }

    public static final Region access$calculateDesktopExclusionRegion(DesktopModeTaskRepository desktopModeTaskRepository) {
        desktopModeTaskRepository.getClass();
        Region region = new Region();
        SparseArray sparseArray = desktopModeTaskRepository.desktopExclusionRegions;
        for (int i = 0; i < sparseArray.size(); i++) {
            region.op((Region) sparseArray.valueAt(i), Region.Op.UNION);
        }
        return region;
    }

    public static void logD(String str, Object... objArr) {
        ShellProtoLogGroup shellProtoLogGroup = ShellProtoLogGroup.WM_SHELL_DESKTOP_MODE;
        String concat = "%s: ".concat(str);
        SpreadBuilder spreadBuilder = new SpreadBuilder(2);
        spreadBuilder.add("DesktopModeTaskRepository");
        spreadBuilder.addSpread(objArr);
        ProtoLog.d(shellProtoLogGroup, concat, spreadBuilder.list.toArray(new Object[spreadBuilder.list.size()]));
    }

    public static void logW(String str, Object... objArr) {
        ShellProtoLogGroup shellProtoLogGroup = ShellProtoLogGroup.WM_SHELL_DESKTOP_MODE;
        String concat = "%s: ".concat(str);
        SpreadBuilder spreadBuilder = new SpreadBuilder(2);
        spreadBuilder.add("DesktopModeTaskRepository");
        spreadBuilder.addSpread(objArr);
        ProtoLog.w(shellProtoLogGroup, concat, spreadBuilder.list.toArray(new Object[spreadBuilder.list.size()]));
    }

    public final void addActiveTask(int i, int i2) {
        removeActiveTask(i2, Integer.valueOf(i));
        if (this.desktopTaskDataByDisplayId.getOrCreate(i).activeTasks.add(Integer.valueOf(i2))) {
            logD("Adds active task=%d displayId=%d", Integer.valueOf(i2), Integer.valueOf(i));
            Iterator it = this.activeTasksListeners.iterator();
            while (it.hasNext()) {
                ((ActiveTasksListener) it.next()).onActiveTasksChanged(i);
            }
        }
    }

    public final void addClosingTask(int i, int i2) {
        if (this.desktopTaskDataByDisplayId.getOrCreate(i).closingTasks.add(Integer.valueOf(i2))) {
            logD("Added closing task=%d displayId=%d", Integer.valueOf(i2), Integer.valueOf(i));
        } else {
            logW("Task with taskId=%d displayId=%d is already closing", Integer.valueOf(i2), Integer.valueOf(i));
        }
    }

    public final void addOrMoveFreeformTaskToTop(int i, int i2) {
        logD("Add or move task to top: display=%d taskId=%d", Integer.valueOf(i2), Integer.valueOf(i));
        DesktopModeTaskRepository$desktopTaskDataByDisplayId$1 desktopModeTaskRepository$desktopTaskDataByDisplayId$1 = this.desktopTaskDataByDisplayId;
        DesktopTaskData desktopTaskData = (DesktopTaskData) desktopModeTaskRepository$desktopTaskDataByDisplayId$1.get(i);
        if (desktopTaskData != null) {
            desktopTaskData.freeformTasksInZOrder.remove(Integer.valueOf(i2));
        }
        desktopModeTaskRepository$desktopTaskDataByDisplayId$1.getOrCreate(i).freeformTasksInZOrder.add(0, Integer.valueOf(i2));
        unminimizeTask(i, i2);
    }

    public final void addVisibleTasksListener(VisibleTasksListener visibleTasksListener, Executor executor) {
        this.visibleTasksListeners.put(visibleTasksListener, executor);
        int i = 0;
        while (true) {
            DesktopModeTaskRepository$desktopTaskDataByDisplayId$1 desktopModeTaskRepository$desktopTaskDataByDisplayId$1 = this.desktopTaskDataByDisplayId;
            if (i >= desktopModeTaskRepository$desktopTaskDataByDisplayId$1.size()) {
                return;
            }
            int i2 = i + 1;
            int keyAt = desktopModeTaskRepository$desktopTaskDataByDisplayId$1.keyAt(i);
            executor.execute(new DesktopModeTaskRepository$addVisibleTasksListener$1$1(visibleTasksListener, keyAt, getVisibleTaskCount(keyAt), 0));
            i = i2;
        }
    }

    public final Sequence desktopTaskDataSequence() {
        return SequencesKt.asSequence(new SparseArrayKt$valueIterator$1(this.desktopTaskDataByDisplayId));
    }

    public final List getActiveNonMinimizedOrderedTasks(int i) {
        DesktopTaskData desktopTaskData = (DesktopTaskData) this.desktopTaskDataByDisplayId.get(i);
        ArrayList arrayList = new ArrayList(desktopTaskData != null ? desktopTaskData.freeformTasksInZOrder : EmptyList.INSTANCE);
        ArrayList arrayList2 = new ArrayList();
        for (Object obj : arrayList) {
            if (!isMinimizedTask(((Number) obj).intValue())) {
                arrayList2.add(obj);
            }
        }
        return arrayList2;
    }

    public final int getVisibleTaskCount(int i) {
        DesktopTaskData desktopTaskData = (DesktopTaskData) this.desktopTaskDataByDisplayId.get(i);
        if (desktopTaskData != null) {
            return desktopTaskData.visibleTasks.size();
        }
        logD("getVisibleTaskCount=0", new Object[0]);
        return 0;
    }

    public final boolean isActiveTask(int i) {
        Iterator it = ((ConstrainedOnceSequence) desktopTaskDataSequence()).iterator();
        while (it.hasNext()) {
            if (((DesktopTaskData) it.next()).activeTasks.contains(Integer.valueOf(i))) {
                return true;
            }
        }
        return false;
    }

    public final boolean isMinimizedTask(int i) {
        Iterator it = ((ConstrainedOnceSequence) desktopTaskDataSequence()).iterator();
        while (it.hasNext()) {
            if (((DesktopTaskData) it.next()).minimizedTasks.contains(Integer.valueOf(i))) {
                return true;
            }
        }
        return false;
    }

    public final boolean isOnlyVisibleNonClosingTask(int i) {
        Iterator it = ((ConstrainedOnceSequence) desktopTaskDataSequence()).iterator();
        while (it.hasNext()) {
            DesktopTaskData desktopTaskData = (DesktopTaskData) it.next();
            Set subtract = CollectionsKt.subtract(CollectionsKt.subtract(desktopTaskData.visibleTasks, desktopTaskData.closingTasks), desktopTaskData.minimizedTasks);
            Object obj = null;
            if (subtract instanceof List) {
                List list = (List) subtract;
                if (list.size() == 1) {
                    obj = list.get(0);
                }
            } else {
                Iterator it2 = subtract.iterator();
                if (it2.hasNext()) {
                    Object next = it2.next();
                    if (!it2.hasNext()) {
                        obj = next;
                    }
                }
            }
            Integer num = (Integer) obj;
            if (num != null && num.intValue() == i) {
                return true;
            }
        }
        return false;
    }

    public final boolean isVisibleTask(int i) {
        Iterator it = ((ConstrainedOnceSequence) desktopTaskDataSequence()).iterator();
        while (it.hasNext()) {
            if (((DesktopTaskData) it.next()).visibleTasks.contains(Integer.valueOf(i))) {
                return true;
            }
        }
        return false;
    }

    public final void notifyVisibleTaskListeners(int i, int i2) {
        for (Map.Entry entry : this.visibleTasksListeners.entrySet()) {
            ((Executor) entry.getValue()).execute(new DesktopModeTaskRepository$addVisibleTasksListener$1$1((VisibleTasksListener) entry.getKey(), i, i2, 1));
        }
    }

    public final void removeActiveTask(int i, Integer num) {
        DesktopModeTaskRepository$desktopTaskDataByDisplayId$1 desktopModeTaskRepository$desktopTaskDataByDisplayId$1 = this.desktopTaskDataByDisplayId;
        int size = desktopModeTaskRepository$desktopTaskDataByDisplayId$1.size();
        for (int i2 = 0; i2 < size; i2++) {
            int keyAt = desktopModeTaskRepository$desktopTaskDataByDisplayId$1.keyAt(i2);
            DesktopTaskData desktopTaskData = (DesktopTaskData) desktopModeTaskRepository$desktopTaskDataByDisplayId$1.valueAt(i2);
            if ((num == null || keyAt != num.intValue()) && desktopTaskData.activeTasks.remove(Integer.valueOf(i))) {
                logD("Removed active task=%d displayId=%d", Integer.valueOf(i), Integer.valueOf(keyAt));
                Iterator it = this.activeTasksListeners.iterator();
                while (it.hasNext()) {
                    ((ActiveTasksListener) it.next()).onActiveTasksChanged(keyAt);
                }
            }
        }
    }

    public final void removeTaskFromDisplay(int i, int i2) {
        logD("Removes freeform task: taskId=%d, displayId=%d", Integer.valueOf(i2), Integer.valueOf(i));
        DesktopModeTaskRepository$desktopTaskDataByDisplayId$1 desktopModeTaskRepository$desktopTaskDataByDisplayId$1 = this.desktopTaskDataByDisplayId;
        DesktopTaskData desktopTaskData = (DesktopTaskData) desktopModeTaskRepository$desktopTaskDataByDisplayId$1.get(i);
        if (desktopTaskData != null) {
            desktopTaskData.freeformTasksInZOrder.remove(Integer.valueOf(i2));
        }
        this.boundsBeforeMaximizeByTaskId.remove(i2);
        DesktopTaskData desktopTaskData2 = (DesktopTaskData) desktopModeTaskRepository$desktopTaskDataByDisplayId$1.get(i);
        logD("Remaining freeform tasks: %s", desktopTaskData2 != null ? CollectionsKt.joinToString$default(desktopTaskData2.freeformTasksInZOrder, ", ", "[", "]", null, 56) : null);
        unminimizeTask(i, i2);
        removeActiveTask(i2, null);
        updateTaskVisibility(i, i2, false);
    }

    public final void removeVisibleTask(int i, Integer num) {
        DesktopModeTaskRepository$desktopTaskDataByDisplayId$1 desktopModeTaskRepository$desktopTaskDataByDisplayId$1 = this.desktopTaskDataByDisplayId;
        int size = desktopModeTaskRepository$desktopTaskDataByDisplayId$1.size();
        for (int i2 = 0; i2 < size; i2++) {
            int keyAt = desktopModeTaskRepository$desktopTaskDataByDisplayId$1.keyAt(i2);
            DesktopTaskData desktopTaskData = (DesktopTaskData) desktopModeTaskRepository$desktopTaskDataByDisplayId$1.valueAt(i2);
            if ((num == null || keyAt != num.intValue()) && desktopTaskData.visibleTasks.remove(Integer.valueOf(i))) {
                notifyVisibleTaskListeners(keyAt, desktopTaskData.visibleTasks.size());
            }
        }
    }

    public final void unminimizeTask(int i, int i2) {
        logD("Unminimize Task: display=%d, task=%d", Integer.valueOf(i), Integer.valueOf(i2));
        DesktopTaskData desktopTaskData = (DesktopTaskData) this.desktopTaskDataByDisplayId.get(i);
        if (desktopTaskData != null) {
            desktopTaskData.minimizedTasks.remove(Integer.valueOf(i2));
        } else {
            logW("Unminimize Task: display=%d, task=%d, no task data", Integer.valueOf(i), Integer.valueOf(i2));
        }
    }

    public final void updateTaskVisibility(int i, int i2, boolean z) {
        if (z) {
            removeVisibleTask(i2, Integer.valueOf(i));
        } else if (i == -1) {
            removeVisibleTask(i2, null);
            return;
        }
        int visibleTaskCount = getVisibleTaskCount(i);
        DesktopModeTaskRepository$desktopTaskDataByDisplayId$1 desktopModeTaskRepository$desktopTaskDataByDisplayId$1 = this.desktopTaskDataByDisplayId;
        if (z) {
            desktopModeTaskRepository$desktopTaskDataByDisplayId$1.getOrCreate(i).visibleTasks.add(Integer.valueOf(i2));
            unminimizeTask(i, i2);
        } else {
            DesktopTaskData desktopTaskData = (DesktopTaskData) desktopModeTaskRepository$desktopTaskDataByDisplayId$1.get(i);
            if (desktopTaskData != null) {
                desktopTaskData.visibleTasks.remove(Integer.valueOf(i2));
            }
        }
        int visibleTaskCount2 = getVisibleTaskCount(i);
        if (visibleTaskCount != visibleTaskCount2) {
            logD("Update task visibility taskId=%d visible=%b displayId=%d", Integer.valueOf(i2), Boolean.valueOf(z), Integer.valueOf(i));
            logD("VisibleTaskCount has changed from %d to %d", Integer.valueOf(visibleTaskCount), Integer.valueOf(visibleTaskCount2));
            notifyVisibleTaskListeners(i, visibleTaskCount2);
        }
    }
}
