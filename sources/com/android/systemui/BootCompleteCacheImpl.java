package com.android.systemui;

import com.android.keyguard.ActiveUnlockConfig$$ExternalSyntheticOutline0;
import com.android.systemui.dump.DumpManager;
import java.io.PrintWriter;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class BootCompleteCacheImpl implements Dumpable {
    public final AtomicBoolean bootComplete;
    public final List listeners;

    public BootCompleteCacheImpl(DumpManager dumpManager) {
        DumpManager.registerDumpable$default(dumpManager, "BootCompleteCacheImpl", this);
        this.listeners = new ArrayList();
        this.bootComplete = new AtomicBoolean(false);
    }

    public final boolean addListener(BootCompleteCache$BootCompleteListener bootCompleteCache$BootCompleteListener) {
        if (this.bootComplete.get()) {
            return true;
        }
        synchronized (this.listeners) {
            if (this.bootComplete.get()) {
                return true;
            }
            this.listeners.add(new WeakReference(bootCompleteCache$BootCompleteListener));
            return false;
        }
    }

    @Override // com.android.systemui.Dumpable
    public final void dump(PrintWriter printWriter, String[] strArr) {
        printWriter.println("BootCompleteCache state:");
        ActiveUnlockConfig$$ExternalSyntheticOutline0.m("  boot complete: ", this.bootComplete.get(), printWriter);
        if (this.bootComplete.get()) {
            return;
        }
        printWriter.println("  listeners:");
        synchronized (this.listeners) {
            Iterator it = this.listeners.iterator();
            while (it.hasNext()) {
                printWriter.println("    " + ((WeakReference) it.next()));
            }
        }
    }

    public final void setBootComplete() {
        if (this.bootComplete.compareAndSet(false, true)) {
            synchronized (this.listeners) {
                try {
                    Iterator it = this.listeners.iterator();
                    while (it.hasNext()) {
                        BootCompleteCache$BootCompleteListener bootCompleteCache$BootCompleteListener = (BootCompleteCache$BootCompleteListener) ((WeakReference) it.next()).get();
                        if (bootCompleteCache$BootCompleteListener != null) {
                            bootCompleteCache$BootCompleteListener.onBootComplete();
                        }
                    }
                    this.listeners.clear();
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
    }
}
