package androidx.lifecycle;

import android.app.Application;
import android.os.Bundle;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.viewmodel.MutableCreationExtras;
import androidx.lifecycle.viewmodel.internal.ViewModelImpl;
import androidx.savedstate.SavedStateRegistry;
import androidx.savedstate.SavedStateRegistryOwner;
import java.lang.reflect.Constructor;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class SavedStateViewModelFactory extends ViewModelProvider.OnRequeryFactory implements ViewModelProvider.Factory {
    public final Application application;
    public final Bundle defaultArgs;
    public final ViewModelProvider.AndroidViewModelFactory factory;
    public final Lifecycle lifecycle;
    public final SavedStateRegistry savedStateRegistry;

    public SavedStateViewModelFactory(Application application, SavedStateRegistryOwner savedStateRegistryOwner, Bundle bundle) {
        ViewModelProvider.AndroidViewModelFactory androidViewModelFactory;
        this.savedStateRegistry = savedStateRegistryOwner.getSavedStateRegistry();
        this.lifecycle = savedStateRegistryOwner.getLifecycle();
        this.defaultArgs = bundle;
        this.application = application;
        if (application != null) {
            if (ViewModelProvider.AndroidViewModelFactory._instance == null) {
                ViewModelProvider.AndroidViewModelFactory._instance = new ViewModelProvider.AndroidViewModelFactory(application);
            }
            androidViewModelFactory = ViewModelProvider.AndroidViewModelFactory._instance;
            Intrinsics.checkNotNull(androidViewModelFactory);
        } else {
            androidViewModelFactory = new ViewModelProvider.AndroidViewModelFactory(null);
        }
        this.factory = androidViewModelFactory;
    }

    @Override // androidx.lifecycle.ViewModelProvider.Factory
    public final ViewModel create(Class cls, MutableCreationExtras mutableCreationExtras) {
        String str = (String) mutableCreationExtras.extras.get(ViewModelProvider.VIEW_MODEL_KEY);
        if (str == null) {
            throw new IllegalStateException("VIEW_MODEL_KEY must always be provided by ViewModelProvider");
        }
        if (mutableCreationExtras.extras.get(SavedStateHandleSupport.SAVED_STATE_REGISTRY_OWNER_KEY) != null) {
            if (mutableCreationExtras.extras.get(SavedStateHandleSupport.VIEW_MODEL_STORE_OWNER_KEY) != null) {
                Application application = (Application) mutableCreationExtras.extras.get(ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY);
                boolean isAssignableFrom = AndroidViewModel.class.isAssignableFrom(cls);
                Constructor findMatchingConstructor = (!isAssignableFrom || application == null) ? SavedStateViewModelFactoryKt.findMatchingConstructor(cls, SavedStateViewModelFactoryKt.VIEWMODEL_SIGNATURE) : SavedStateViewModelFactoryKt.findMatchingConstructor(cls, SavedStateViewModelFactoryKt.ANDROID_VIEWMODEL_SIGNATURE);
                return findMatchingConstructor == null ? this.factory.create(cls, mutableCreationExtras) : (!isAssignableFrom || application == null) ? SavedStateViewModelFactoryKt.newInstance(cls, findMatchingConstructor, SavedStateHandleSupport.createSavedStateHandle(mutableCreationExtras)) : SavedStateViewModelFactoryKt.newInstance(cls, findMatchingConstructor, application, SavedStateHandleSupport.createSavedStateHandle(mutableCreationExtras));
            }
        }
        if (this.lifecycle != null) {
            return create$1(cls, str);
        }
        throw new IllegalStateException("SAVED_STATE_REGISTRY_OWNER_KEY andVIEW_MODEL_STORE_OWNER_KEY must be provided in the creation extras tosuccessfully create a ViewModel.");
    }

    public final ViewModel create$1(Class cls, String str) {
        Application application;
        Lifecycle lifecycle = this.lifecycle;
        if (lifecycle == null) {
            throw new UnsupportedOperationException("SavedStateViewModelFactory constructed with empty constructor supports only calls to create(modelClass: Class<T>, extras: CreationExtras).");
        }
        boolean isAssignableFrom = AndroidViewModel.class.isAssignableFrom(cls);
        Constructor findMatchingConstructor = (!isAssignableFrom || this.application == null) ? SavedStateViewModelFactoryKt.findMatchingConstructor(cls, SavedStateViewModelFactoryKt.VIEWMODEL_SIGNATURE) : SavedStateViewModelFactoryKt.findMatchingConstructor(cls, SavedStateViewModelFactoryKt.ANDROID_VIEWMODEL_SIGNATURE);
        if (findMatchingConstructor == null) {
            if (this.application != null) {
                return this.factory.create(cls);
            }
            if (ViewModelProvider.NewInstanceFactory._instance == null) {
                ViewModelProvider.NewInstanceFactory._instance = new ViewModelProvider.NewInstanceFactory();
            }
            ViewModelProvider.NewInstanceFactory newInstanceFactory = ViewModelProvider.NewInstanceFactory._instance;
            Intrinsics.checkNotNull(newInstanceFactory);
            return newInstanceFactory.create(cls);
        }
        SavedStateRegistry savedStateRegistry = this.savedStateRegistry;
        Intrinsics.checkNotNull(savedStateRegistry);
        Bundle bundle = this.defaultArgs;
        Bundle consumeRestoredStateForKey = savedStateRegistry.consumeRestoredStateForKey(str);
        List list = SavedStateHandle.ACCEPTABLE_CLASSES;
        SavedStateHandle createHandle = SavedStateHandle.Companion.createHandle(consumeRestoredStateForKey, bundle);
        SavedStateHandleController savedStateHandleController = new SavedStateHandleController(str, createHandle);
        if (savedStateHandleController.isAttached) {
            throw new IllegalStateException("Already attached to lifecycleOwner");
        }
        savedStateHandleController.isAttached = true;
        lifecycle.addObserver(savedStateHandleController);
        savedStateRegistry.registerSavedStateProvider(str, createHandle.savedStateProvider);
        LegacySavedStateHandleController.tryToAddRecreator(lifecycle, savedStateRegistry);
        ViewModel newInstance = (!isAssignableFrom || (application = this.application) == null) ? SavedStateViewModelFactoryKt.newInstance(cls, findMatchingConstructor, createHandle) : SavedStateViewModelFactoryKt.newInstance(cls, findMatchingConstructor, application, createHandle);
        newInstance.addCloseable("androidx.lifecycle.savedstate.vm.tag", savedStateHandleController);
        return newInstance;
    }

    @Override // androidx.lifecycle.ViewModelProvider.OnRequeryFactory
    public final void onRequery(ViewModel viewModel) {
        AutoCloseable autoCloseable;
        boolean z;
        Lifecycle lifecycle = this.lifecycle;
        if (lifecycle != null) {
            SavedStateRegistry savedStateRegistry = this.savedStateRegistry;
            Intrinsics.checkNotNull(savedStateRegistry);
            ViewModelImpl viewModelImpl = viewModel.impl;
            if (viewModelImpl != null) {
                synchronized (viewModelImpl.lock) {
                    autoCloseable = (AutoCloseable) viewModelImpl.keyToCloseables.get("androidx.lifecycle.savedstate.vm.tag");
                }
            } else {
                autoCloseable = null;
            }
            SavedStateHandleController savedStateHandleController = (SavedStateHandleController) autoCloseable;
            if (savedStateHandleController == null || (z = savedStateHandleController.isAttached)) {
                return;
            }
            if (z) {
                throw new IllegalStateException("Already attached to lifecycleOwner");
            }
            savedStateHandleController.isAttached = true;
            lifecycle.addObserver(savedStateHandleController);
            savedStateRegistry.registerSavedStateProvider(savedStateHandleController.key, savedStateHandleController.handle.savedStateProvider);
            LegacySavedStateHandleController.tryToAddRecreator(lifecycle, savedStateRegistry);
        }
    }

    @Override // androidx.lifecycle.ViewModelProvider.Factory
    public final ViewModel create(Class cls) {
        String canonicalName = cls.getCanonicalName();
        if (canonicalName != null) {
            return create$1(cls, canonicalName);
        }
        throw new IllegalArgumentException("Local and anonymous classes can not be ViewModels");
    }
}
