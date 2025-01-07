package androidx.lifecycle.viewmodel;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;
import androidx.lifecycle.viewmodel.internal.SynchronizedObject;
import kotlin.jvm.internal.ClassReference;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class ViewModelProviderImpl {
    public final CreationExtras defaultExtras;
    public final ViewModelProvider.Factory factory;
    public final SynchronizedObject lock = new SynchronizedObject();
    public final ViewModelStore store;

    public ViewModelProviderImpl(ViewModelStore viewModelStore, ViewModelProvider.Factory factory, CreationExtras creationExtras) {
        this.store = viewModelStore;
        this.factory = factory;
        this.defaultExtras = creationExtras;
    }

    public final ViewModel getViewModel$lifecycle_viewmodel_release(ClassReference classReference, String str) {
        ViewModel viewModel;
        ViewModel create;
        synchronized (this.lock) {
            try {
                viewModel = (ViewModel) this.store.map.get(str);
                if (classReference.isInstance(viewModel)) {
                    Object obj = this.factory;
                    if (obj instanceof ViewModelProvider.OnRequeryFactory) {
                        Intrinsics.checkNotNull(viewModel);
                        ((ViewModelProvider.OnRequeryFactory) obj).onRequery(viewModel);
                    }
                } else {
                    MutableCreationExtras mutableCreationExtras = new MutableCreationExtras(this.defaultExtras);
                    mutableCreationExtras.extras.put(ViewModelProvider.VIEW_MODEL_KEY, str);
                    ViewModelProvider.Factory factory = this.factory;
                    try {
                        try {
                            create = factory.create(classReference, mutableCreationExtras);
                        } catch (AbstractMethodError unused) {
                            create = factory.create(classReference.getJClass(), mutableCreationExtras);
                        }
                    } catch (AbstractMethodError unused2) {
                        create = factory.create(classReference.getJClass());
                    }
                    viewModel = create;
                    ViewModel viewModel2 = (ViewModel) this.store.map.put(str, viewModel);
                    if (viewModel2 != null) {
                        viewModel2.clear$lifecycle_viewmodel_release();
                    }
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        return viewModel;
    }
}
