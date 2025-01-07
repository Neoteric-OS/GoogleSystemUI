package com.android.wm.shell.desktopmode;

import android.util.ArraySet;
import android.util.SparseArray;
import com.android.wm.shell.desktopmode.DesktopModeTaskRepository;
import java.util.ArrayList;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class DesktopModeTaskRepository$desktopTaskDataByDisplayId$1 extends SparseArray {
    public final DesktopModeTaskRepository.DesktopTaskData getOrCreate(int i) {
        DesktopModeTaskRepository.DesktopTaskData desktopTaskData = (DesktopModeTaskRepository.DesktopTaskData) get(i);
        if (desktopTaskData != null) {
            return desktopTaskData;
        }
        DesktopModeTaskRepository.DesktopTaskData desktopTaskData2 = new DesktopModeTaskRepository.DesktopTaskData(new ArraySet(), new ArraySet(), new ArraySet(), new ArraySet(), new ArrayList());
        set(i, desktopTaskData2);
        return desktopTaskData2;
    }
}
