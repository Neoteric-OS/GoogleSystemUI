package com.android.wm.shell.common;

import android.util.Slog;
import com.android.wm.shell.common.DisplayController;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class DisplayController$DisplayWindowListenerImpl$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ DisplayController.DisplayWindowListenerImpl f$0;
    public final /* synthetic */ int f$1;

    public /* synthetic */ DisplayController$DisplayWindowListenerImpl$$ExternalSyntheticLambda0(DisplayController.DisplayWindowListenerImpl displayWindowListenerImpl, int i, int i2) {
        this.$r8$classId = i2;
        this.f$0 = displayWindowListenerImpl;
        this.f$1 = i;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.$r8$classId) {
            case 0:
                this.f$0.this$0.onDisplayAdded(this.f$1);
                return;
            case 1:
                DisplayController.DisplayWindowListenerImpl displayWindowListenerImpl = this.f$0;
                int i = this.f$1;
                DisplayController displayController = displayWindowListenerImpl.this$0;
                synchronized (displayController.mDisplays) {
                    try {
                        if (displayController.mDisplays.get(i) == null) {
                            return;
                        }
                        for (int size = displayController.mDisplayChangedListeners.size() - 1; size >= 0; size--) {
                            ((DisplayController.OnDisplaysChangedListener) displayController.mDisplayChangedListeners.get(size)).onDisplayRemoved(i);
                        }
                        displayController.mDisplays.remove(i);
                        return;
                    } finally {
                    }
                }
            default:
                DisplayController.DisplayWindowListenerImpl displayWindowListenerImpl2 = this.f$0;
                int i2 = this.f$1;
                DisplayController displayController2 = displayWindowListenerImpl2.this$0;
                synchronized (displayController2.mDisplays) {
                    try {
                        if (displayController2.mDisplays.get(i2) != null && displayController2.getDisplay(i2) != null) {
                            for (int size2 = displayController2.mDisplayChangedListeners.size() - 1; size2 >= 0; size2--) {
                                ((DisplayController.OnDisplaysChangedListener) displayController2.mDisplayChangedListeners.get(size2)).onFixedRotationFinished();
                            }
                            return;
                        }
                        Slog.w("DisplayController", "Skipping onFixedRotationFinished on unknown display, displayId=" + i2);
                        return;
                    } finally {
                    }
                }
        }
    }
}
