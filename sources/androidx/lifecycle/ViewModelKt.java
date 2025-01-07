package androidx.lifecycle;

import androidx.lifecycle.viewmodel.internal.CloseableCoroutineScope;
import androidx.lifecycle.viewmodel.internal.SynchronizedObject;
import androidx.lifecycle.viewmodel.internal.ViewModelImpl;
import com.android.systemui.inputdevice.tutorial.ui.viewmodel.KeyboardTouchpadTutorialViewModel;
import kotlin.NotImplementedError;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.SupervisorKt;
import kotlinx.coroutines.internal.MainDispatcherLoader;
import kotlinx.coroutines.scheduling.DefaultScheduler;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class ViewModelKt {
    public static final SynchronizedObject VIEW_MODEL_SCOPE_LOCK = new SynchronizedObject();

    public static final CloseableCoroutineScope getViewModelScope(KeyboardTouchpadTutorialViewModel keyboardTouchpadTutorialViewModel) {
        AutoCloseable autoCloseable;
        CloseableCoroutineScope closeableCoroutineScope;
        CoroutineContext coroutineContext;
        synchronized (VIEW_MODEL_SCOPE_LOCK) {
            ViewModelImpl viewModelImpl = keyboardTouchpadTutorialViewModel.impl;
            if (viewModelImpl != null) {
                synchronized (viewModelImpl.lock) {
                    autoCloseable = (AutoCloseable) viewModelImpl.keyToCloseables.get("androidx.lifecycle.viewmodel.internal.ViewModelCoroutineScope.JOB_KEY");
                }
            } else {
                autoCloseable = null;
            }
            closeableCoroutineScope = (CloseableCoroutineScope) autoCloseable;
            if (closeableCoroutineScope == null) {
                try {
                    DefaultScheduler defaultScheduler = Dispatchers.Default;
                    coroutineContext = MainDispatcherLoader.dispatcher.immediate;
                } catch (IllegalStateException unused) {
                    coroutineContext = EmptyCoroutineContext.INSTANCE;
                } catch (NotImplementedError unused2) {
                    coroutineContext = EmptyCoroutineContext.INSTANCE;
                }
                CloseableCoroutineScope closeableCoroutineScope2 = new CloseableCoroutineScope(coroutineContext.plus(SupervisorKt.SupervisorJob$default()));
                keyboardTouchpadTutorialViewModel.addCloseable("androidx.lifecycle.viewmodel.internal.ViewModelCoroutineScope.JOB_KEY", closeableCoroutineScope2);
                closeableCoroutineScope = closeableCoroutineScope2;
            }
        }
        return closeableCoroutineScope;
    }
}
