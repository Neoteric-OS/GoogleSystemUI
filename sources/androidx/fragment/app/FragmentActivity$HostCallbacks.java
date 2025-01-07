package androidx.fragment.app;

import android.os.Handler;
import androidx.activity.OnBackPressedDispatcher;
import androidx.activity.OnBackPressedDispatcherOwner;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.ViewModelStore;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.savedstate.SavedStateRegistry;
import androidx.savedstate.SavedStateRegistryOwner;
import androidx.slice.compat.SlicePermissionActivity;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class FragmentActivity$HostCallbacks implements ViewModelStoreOwner, OnBackPressedDispatcherOwner, SavedStateRegistryOwner {
    public final SlicePermissionActivity context;
    public final FragmentManagerImpl fragmentManager;
    public final Handler handler;
    public final /* synthetic */ SlicePermissionActivity this$0;

    public FragmentActivity$HostCallbacks(SlicePermissionActivity slicePermissionActivity) {
        this.this$0 = slicePermissionActivity;
        Handler handler = new Handler();
        this.context = slicePermissionActivity;
        this.handler = handler;
        this.fragmentManager = new FragmentManagerImpl();
    }

    @Override // androidx.lifecycle.LifecycleOwner
    public final Lifecycle getLifecycle() {
        return this.this$0.mFragmentLifecycleRegistry;
    }

    @Override // androidx.activity.OnBackPressedDispatcherOwner
    public final OnBackPressedDispatcher getOnBackPressedDispatcher() {
        return this.this$0.getOnBackPressedDispatcher();
    }

    @Override // androidx.savedstate.SavedStateRegistryOwner
    public final SavedStateRegistry getSavedStateRegistry() {
        return this.this$0.savedStateRegistryController.savedStateRegistry;
    }

    @Override // androidx.lifecycle.ViewModelStoreOwner
    public final ViewModelStore getViewModelStore() {
        return this.this$0.getViewModelStore();
    }
}
