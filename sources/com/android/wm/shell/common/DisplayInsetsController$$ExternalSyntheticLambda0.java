package com.android.wm.shell.common;

import com.android.wm.shell.common.DisplayInsetsController;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class DisplayInsetsController$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ int $r8$classId = 1;
    public final /* synthetic */ Object f$0;

    @Override // java.lang.Runnable
    public final void run() {
        int i = this.$r8$classId;
        Object obj = this.f$0;
        switch (i) {
            case 0:
                DisplayInsetsController displayInsetsController = (DisplayInsetsController) obj;
                displayInsetsController.mDisplayController.addDisplayWindowListener(displayInsetsController);
                break;
            default:
                DisplayInsetsController.PerDisplay perDisplay = ((DisplayInsetsController.PerDisplay.DisplayWindowInsetsControllerImpl) obj).this$1;
                CopyOnWriteArrayList copyOnWriteArrayList = (CopyOnWriteArrayList) perDisplay.this$0.mListeners.get(perDisplay.mDisplayId);
                if (copyOnWriteArrayList != null) {
                    Iterator it = copyOnWriteArrayList.iterator();
                    while (it.hasNext()) {
                        ((DisplayInsetsController.OnInsetsChangedListener) it.next()).getClass();
                    }
                    break;
                }
                break;
        }
    }
}
