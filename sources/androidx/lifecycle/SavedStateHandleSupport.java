package androidx.lifecycle;

import android.os.Bundle;
import androidx.activity.ComponentActivity;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.viewmodel.CreationExtras;
import androidx.lifecycle.viewmodel.MutableCreationExtras;
import androidx.lifecycle.viewmodel.ViewModelProviderImpl;
import androidx.savedstate.SavedStateRegistry;
import androidx.savedstate.SavedStateRegistryOwner;
import java.util.List;
import kotlin.jvm.internal.Reflection;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class SavedStateHandleSupport {
    public static final SavedStateHandleSupport$special$$inlined$Key$1 SAVED_STATE_REGISTRY_OWNER_KEY = new SavedStateHandleSupport$special$$inlined$Key$1();
    public static final SavedStateHandleSupport$special$$inlined$Key$1 VIEW_MODEL_STORE_OWNER_KEY = new SavedStateHandleSupport$special$$inlined$Key$1();
    public static final SavedStateHandleSupport$special$$inlined$Key$1 DEFAULT_ARGS_KEY = new SavedStateHandleSupport$special$$inlined$Key$1();

    public static final SavedStateHandle createSavedStateHandle(MutableCreationExtras mutableCreationExtras) {
        SavedStateRegistryOwner savedStateRegistryOwner = (SavedStateRegistryOwner) mutableCreationExtras.extras.get(SAVED_STATE_REGISTRY_OWNER_KEY);
        if (savedStateRegistryOwner == null) {
            throw new IllegalArgumentException("CreationExtras must have a value by `SAVED_STATE_REGISTRY_OWNER_KEY`");
        }
        ViewModelStoreOwner viewModelStoreOwner = (ViewModelStoreOwner) mutableCreationExtras.extras.get(VIEW_MODEL_STORE_OWNER_KEY);
        if (viewModelStoreOwner == null) {
            throw new IllegalArgumentException("CreationExtras must have a value by `VIEW_MODEL_STORE_OWNER_KEY`");
        }
        Bundle bundle = (Bundle) mutableCreationExtras.extras.get(DEFAULT_ARGS_KEY);
        String str = (String) mutableCreationExtras.extras.get(ViewModelProvider.VIEW_MODEL_KEY);
        if (str == null) {
            throw new IllegalArgumentException("CreationExtras must have a value by `VIEW_MODEL_KEY`");
        }
        SavedStateRegistry.SavedStateProvider savedStateProvider = savedStateRegistryOwner.getSavedStateRegistry().getSavedStateProvider();
        SavedStateHandlesProvider savedStateHandlesProvider = savedStateProvider instanceof SavedStateHandlesProvider ? (SavedStateHandlesProvider) savedStateProvider : null;
        if (savedStateHandlesProvider == null) {
            throw new IllegalStateException("enableSavedStateHandles() wasn't called prior to createSavedStateHandle() call");
        }
        SavedStateHandlesVM savedStateHandlesVM = getSavedStateHandlesVM(viewModelStoreOwner);
        SavedStateHandle savedStateHandle = (SavedStateHandle) savedStateHandlesVM.handles.get(str);
        if (savedStateHandle != null) {
            return savedStateHandle;
        }
        List list = SavedStateHandle.ACCEPTABLE_CLASSES;
        savedStateHandlesProvider.performRestore();
        Bundle bundle2 = savedStateHandlesProvider.restoredState;
        Bundle bundle3 = bundle2 != null ? bundle2.getBundle(str) : null;
        Bundle bundle4 = savedStateHandlesProvider.restoredState;
        if (bundle4 != null) {
            bundle4.remove(str);
        }
        Bundle bundle5 = savedStateHandlesProvider.restoredState;
        if (bundle5 != null && bundle5.isEmpty()) {
            savedStateHandlesProvider.restoredState = null;
        }
        SavedStateHandle createHandle = SavedStateHandle.Companion.createHandle(bundle3, bundle);
        savedStateHandlesVM.handles.put(str, createHandle);
        return createHandle;
    }

    public static final SavedStateHandlesVM getSavedStateHandlesVM(ViewModelStoreOwner viewModelStoreOwner) {
        return (SavedStateHandlesVM) new ViewModelProviderImpl(viewModelStoreOwner.getViewModelStore(), new SavedStateHandleSupport$savedStateHandlesVM$1(), viewModelStoreOwner instanceof HasDefaultViewModelProviderFactory ? ((ComponentActivity) ((HasDefaultViewModelProviderFactory) viewModelStoreOwner)).getDefaultViewModelCreationExtras() : CreationExtras.Empty.INSTANCE).getViewModel$lifecycle_viewmodel_release(Reflection.getOrCreateKotlinClass(SavedStateHandlesVM.class), "androidx.lifecycle.internal.SavedStateHandlesVM");
    }
}
