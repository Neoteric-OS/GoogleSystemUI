package androidx.lifecycle;

import androidx.lifecycle.viewmodel.internal.ViewModelImpl;
import java.util.Iterator;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class ViewModel {
    public final ViewModelImpl impl = new ViewModelImpl();

    public final void addCloseable(String str, AutoCloseable autoCloseable) {
        AutoCloseable autoCloseable2;
        ViewModelImpl viewModelImpl = this.impl;
        if (viewModelImpl != null) {
            if (viewModelImpl.isCleared) {
                ViewModelImpl.closeWithRuntimeException(autoCloseable);
                return;
            }
            synchronized (viewModelImpl.lock) {
                autoCloseable2 = (AutoCloseable) viewModelImpl.keyToCloseables.put(str, autoCloseable);
            }
            ViewModelImpl.closeWithRuntimeException(autoCloseable2);
        }
    }

    public final void clear$lifecycle_viewmodel_release() {
        ViewModelImpl viewModelImpl = this.impl;
        if (viewModelImpl != null && !viewModelImpl.isCleared) {
            viewModelImpl.isCleared = true;
            synchronized (viewModelImpl.lock) {
                try {
                    Iterator it = viewModelImpl.keyToCloseables.values().iterator();
                    while (it.hasNext()) {
                        ViewModelImpl.closeWithRuntimeException((AutoCloseable) it.next());
                    }
                    Iterator it2 = viewModelImpl.closeables.iterator();
                    while (it2.hasNext()) {
                        ViewModelImpl.closeWithRuntimeException((AutoCloseable) it2.next());
                    }
                    viewModelImpl.closeables.clear();
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
        onCleared();
    }

    public void onCleared() {
    }
}
