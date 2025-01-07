package androidx.activity;

import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;
import kotlin.jvm.internal.FunctionReferenceImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class OnBackPressedCallback {
    public final CopyOnWriteArrayList cancellables = new CopyOnWriteArrayList();
    public FunctionReferenceImpl enabledChangedCallback;
    public boolean isEnabled;

    public OnBackPressedCallback(boolean z) {
        this.isEnabled = z;
    }

    public abstract void handleOnBackPressed();

    public final void remove() {
        Iterator it = this.cancellables.iterator();
        while (it.hasNext()) {
            ((Cancellable) it.next()).cancel();
        }
    }

    public void handleOnBackCancelled() {
    }

    public void handleOnBackStarted() {
    }

    public void handleOnBackProgressed(BackEventCompat backEventCompat) {
    }
}
