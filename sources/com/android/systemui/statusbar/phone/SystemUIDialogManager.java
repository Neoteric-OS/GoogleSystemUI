package com.android.systemui.statusbar.phone;

import com.android.systemui.Dumpable;
import com.android.systemui.dump.DumpManager;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class SystemUIDialogManager implements Dumpable {
    public final StatusBarKeyguardViewManager mKeyguardViewController;
    public final Set mDialogsShowing = new HashSet();
    public final Set mListeners = new HashSet();

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public interface Listener {
        void shouldHideAffordances(boolean z);
    }

    public SystemUIDialogManager(DumpManager dumpManager, StatusBarKeyguardViewManager statusBarKeyguardViewManager) {
        dumpManager.registerDumpable(this);
        this.mKeyguardViewController = statusBarKeyguardViewManager;
    }

    @Override // com.android.systemui.Dumpable
    public final void dump(PrintWriter printWriter, String[] strArr) {
        printWriter.println("listeners:");
        Iterator it = ((HashSet) this.mListeners).iterator();
        while (it.hasNext()) {
            printWriter.println("\t" + ((Listener) it.next()));
        }
        printWriter.println("dialogs tracked:");
        Iterator it2 = ((HashSet) this.mDialogsShowing).iterator();
        while (it2.hasNext()) {
            printWriter.println("\t" + ((SystemUIDialog) it2.next()));
        }
    }

    public final void setShowing(SystemUIDialog systemUIDialog, boolean z) {
        boolean shouldHideAffordance = shouldHideAffordance();
        if (z) {
            this.mDialogsShowing.add(systemUIDialog);
        } else {
            this.mDialogsShowing.remove(systemUIDialog);
        }
        if (shouldHideAffordance != shouldHideAffordance()) {
            if (shouldHideAffordance()) {
                this.mKeyguardViewController.hideAlternateBouncer(true);
            }
            Iterator it = ((HashSet) this.mListeners).iterator();
            while (it.hasNext()) {
                ((Listener) it.next()).shouldHideAffordances(shouldHideAffordance());
            }
        }
    }

    public final boolean shouldHideAffordance() {
        return !this.mDialogsShowing.isEmpty();
    }
}
