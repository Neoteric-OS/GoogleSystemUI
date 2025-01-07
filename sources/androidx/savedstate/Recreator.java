package androidx.savedstate;

import android.os.Bundle;
import androidx.appsearch.app.AppSearchSchema$Builder$$ExternalSyntheticOutline0;
import androidx.appsearch.app.DocumentClassFactoryRegistry$$ExternalSyntheticOutline0;
import androidx.lifecycle.LegacySavedStateHandleController;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleEventObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.SavedStateHandleController;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelStore;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.lifecycle.viewmodel.internal.ViewModelImpl;
import androidx.savedstate.SavedStateRegistry;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class Recreator implements LifecycleEventObserver {
    public final SavedStateRegistryOwner owner;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class SavedStateProvider implements SavedStateRegistry.SavedStateProvider {
        public final Set classes = new LinkedHashSet();

        public SavedStateProvider(SavedStateRegistry savedStateRegistry) {
            savedStateRegistry.registerSavedStateProvider("androidx.savedstate.Restarter", this);
        }

        @Override // androidx.savedstate.SavedStateRegistry.SavedStateProvider
        public final Bundle saveState() {
            Bundle bundle = new Bundle();
            bundle.putStringArrayList("classes_to_restore", new ArrayList<>(this.classes));
            return bundle;
        }
    }

    public Recreator(SavedStateRegistryOwner savedStateRegistryOwner) {
        this.owner = savedStateRegistryOwner;
    }

    @Override // androidx.lifecycle.LifecycleEventObserver
    public final void onStateChanged(LifecycleOwner lifecycleOwner, Lifecycle.Event event) {
        AutoCloseable autoCloseable;
        boolean z;
        if (event != Lifecycle.Event.ON_CREATE) {
            throw new AssertionError("Next event must be ON_CREATE");
        }
        lifecycleOwner.getLifecycle().removeObserver(this);
        SavedStateRegistryOwner savedStateRegistryOwner = this.owner;
        Bundle consumeRestoredStateForKey = savedStateRegistryOwner.getSavedStateRegistry().consumeRestoredStateForKey("androidx.savedstate.Restarter");
        if (consumeRestoredStateForKey == null) {
            return;
        }
        ArrayList<String> stringArrayList = consumeRestoredStateForKey.getStringArrayList("classes_to_restore");
        if (stringArrayList == null) {
            throw new IllegalStateException("Bundle with restored state for the component \"androidx.savedstate.Restarter\" must contain list of strings by the key \"classes_to_restore\"");
        }
        for (String str : stringArrayList) {
            try {
                Class<? extends U> asSubclass = Class.forName(str, false, Recreator.class.getClassLoader()).asSubclass(SavedStateRegistry.AutoRecreated.class);
                try {
                    Class[] clsArr = new Class[0];
                    Constructor declaredConstructor = asSubclass.getDeclaredConstructor(null);
                    declaredConstructor.setAccessible(true);
                    try {
                        if (!(savedStateRegistryOwner instanceof ViewModelStoreOwner)) {
                            throw new IllegalStateException("Internal error: OnRecreation should be registered only on components that implement ViewModelStoreOwner");
                        }
                        ViewModelStore viewModelStore = ((ViewModelStoreOwner) savedStateRegistryOwner).getViewModelStore();
                        SavedStateRegistry savedStateRegistry = savedStateRegistryOwner.getSavedStateRegistry();
                        Iterator it = new HashSet(viewModelStore.map.keySet()).iterator();
                        while (it.hasNext()) {
                            ViewModel viewModel = (ViewModel) viewModelStore.map.get((String) it.next());
                            Intrinsics.checkNotNull(viewModel);
                            Lifecycle lifecycle = savedStateRegistryOwner.getLifecycle();
                            ViewModelImpl viewModelImpl = viewModel.impl;
                            if (viewModelImpl != null) {
                                synchronized (viewModelImpl.lock) {
                                    autoCloseable = (AutoCloseable) viewModelImpl.keyToCloseables.get("androidx.lifecycle.savedstate.vm.tag");
                                }
                            } else {
                                autoCloseable = null;
                            }
                            SavedStateHandleController savedStateHandleController = (SavedStateHandleController) autoCloseable;
                            if (savedStateHandleController != null && !(z = savedStateHandleController.isAttached)) {
                                if (z) {
                                    throw new IllegalStateException("Already attached to lifecycleOwner");
                                }
                                savedStateHandleController.isAttached = true;
                                lifecycle.addObserver(savedStateHandleController);
                                savedStateRegistry.registerSavedStateProvider(savedStateHandleController.key, savedStateHandleController.handle.savedStateProvider);
                                LegacySavedStateHandleController.tryToAddRecreator(lifecycle, savedStateRegistry);
                            }
                        }
                        if (!new HashSet(viewModelStore.map.keySet()).isEmpty()) {
                            savedStateRegistry.runOnNextRecreation();
                        }
                    } catch (Exception e) {
                        throw new RuntimeException(AppSearchSchema$Builder$$ExternalSyntheticOutline0.m("Failed to instantiate ", str), e);
                    }
                } catch (NoSuchMethodException e2) {
                    throw new IllegalStateException("Class " + asSubclass.getSimpleName() + " must have default constructor in order to be automatically recreated", e2);
                }
            } catch (ClassNotFoundException e3) {
                throw new RuntimeException(DocumentClassFactoryRegistry$$ExternalSyntheticOutline0.m("Class ", str, " wasn't found"), e3);
            }
        }
    }
}
