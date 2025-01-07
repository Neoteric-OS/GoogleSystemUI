package com.android.wm.shell.pip;

import android.view.IPinnedTaskListener;
import com.android.wm.shell.common.HandlerExecutor;
import com.android.wm.shell.common.ShellExecutor;
import com.android.wm.shell.pip.PinnedStackListenerForwarder;
import java.util.ArrayList;
import java.util.Iterator;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class PinnedStackListenerForwarder {
    public final PinnedTaskListenerImpl mListenerImpl = new PinnedTaskListenerImpl();
    public final ArrayList mListeners = new ArrayList();
    public final ShellExecutor mMainExecutor;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class PinnedTaskListenerImpl extends IPinnedTaskListener.Stub {
        public PinnedTaskListenerImpl() {
        }

        public final void onImeVisibilityChanged(final boolean z, final int i) {
            ((HandlerExecutor) PinnedStackListenerForwarder.this.mMainExecutor).execute(new Runnable() { // from class: com.android.wm.shell.pip.PinnedStackListenerForwarder$PinnedTaskListenerImpl$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    PinnedStackListenerForwarder.PinnedTaskListenerImpl pinnedTaskListenerImpl = PinnedStackListenerForwarder.PinnedTaskListenerImpl.this;
                    boolean z2 = z;
                    int i2 = i;
                    Iterator it = PinnedStackListenerForwarder.this.mListeners.iterator();
                    while (it.hasNext()) {
                        ((PinnedStackListenerForwarder.PinnedTaskListener) it.next()).onImeVisibilityChanged(z2, i2);
                    }
                }
            });
        }

        public final void onMovementBoundsChanged(final boolean z) {
            ((HandlerExecutor) PinnedStackListenerForwarder.this.mMainExecutor).execute(new Runnable() { // from class: com.android.wm.shell.pip.PinnedStackListenerForwarder$PinnedTaskListenerImpl$$ExternalSyntheticLambda1
                @Override // java.lang.Runnable
                public final void run() {
                    PinnedStackListenerForwarder.PinnedTaskListenerImpl pinnedTaskListenerImpl = PinnedStackListenerForwarder.PinnedTaskListenerImpl.this;
                    boolean z2 = z;
                    Iterator it = PinnedStackListenerForwarder.this.mListeners.iterator();
                    while (it.hasNext()) {
                        ((PinnedStackListenerForwarder.PinnedTaskListener) it.next()).onMovementBoundsChanged(z2);
                    }
                }
            });
        }
    }

    public PinnedStackListenerForwarder(ShellExecutor shellExecutor) {
        this.mMainExecutor = shellExecutor;
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public abstract class PinnedTaskListener {
        public abstract void onImeVisibilityChanged(boolean z, int i);

        public void onMovementBoundsChanged(boolean z) {
        }
    }
}
