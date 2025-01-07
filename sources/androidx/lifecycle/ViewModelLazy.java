package androidx.lifecycle;

import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.viewmodel.CreationExtras;
import androidx.lifecycle.viewmodel.ViewModelProviderImpl;
import kotlin.Lazy;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.ClassReference;
import kotlin.jvm.internal.Lambda;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class ViewModelLazy implements Lazy {
    public ViewModel cached;
    public final Lambda extrasProducer;
    public final Lambda factoryProducer;
    public final Lambda storeProducer;
    public final ClassReference viewModelClass;

    /* JADX WARN: Multi-variable type inference failed */
    public ViewModelLazy(ClassReference classReference, Function0 function0, Function0 function02, Function0 function03) {
        this.viewModelClass = classReference;
        this.storeProducer = (Lambda) function0;
        this.factoryProducer = (Lambda) function02;
        this.extrasProducer = (Lambda) function03;
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [kotlin.jvm.functions.Function0, kotlin.jvm.internal.Lambda] */
    /* JADX WARN: Type inference failed for: r1v0, types: [kotlin.jvm.functions.Function0, kotlin.jvm.internal.Lambda] */
    /* JADX WARN: Type inference failed for: r2v0, types: [kotlin.jvm.functions.Function0, kotlin.jvm.internal.Lambda] */
    @Override // kotlin.Lazy
    public final Object getValue() {
        ViewModel viewModel = this.cached;
        if (viewModel != null) {
            return viewModel;
        }
        ViewModelProviderImpl viewModelProviderImpl = new ViewModelProviderImpl((ViewModelStore) this.storeProducer.invoke(), (ViewModelProvider.Factory) this.factoryProducer.invoke(), (CreationExtras) this.extrasProducer.invoke());
        ClassReference classReference = this.viewModelClass;
        String qualifiedName = classReference.getQualifiedName();
        if (qualifiedName == null) {
            throw new IllegalArgumentException("Local and anonymous classes can not be ViewModels");
        }
        ViewModel viewModel$lifecycle_viewmodel_release = viewModelProviderImpl.getViewModel$lifecycle_viewmodel_release(classReference, "androidx.lifecycle.ViewModelProvider.DefaultKey:".concat(qualifiedName));
        this.cached = viewModel$lifecycle_viewmodel_release;
        return viewModel$lifecycle_viewmodel_release;
    }

    @Override // kotlin.Lazy
    public final boolean isInitialized() {
        throw null;
    }
}
