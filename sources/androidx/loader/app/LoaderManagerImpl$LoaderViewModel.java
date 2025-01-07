package androidx.loader.app;

import androidx.collection.SparseArrayCompat;
import androidx.compose.animation.AnimatedVisibilityScope$animateEnterExit$$inlined$debugInspectorInfo$1$$ExternalSyntheticOutline0;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public class LoaderManagerImpl$LoaderViewModel extends ViewModel {
    public static final AnonymousClass1 FACTORY = new AnonymousClass1();
    public final SparseArrayCompat mLoaders = new SparseArrayCompat(0);

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: androidx.loader.app.LoaderManagerImpl$LoaderViewModel$1, reason: invalid class name */
    public final class AnonymousClass1 implements ViewModelProvider.Factory {
        @Override // androidx.lifecycle.ViewModelProvider.Factory
        public final ViewModel create(Class cls) {
            return new LoaderManagerImpl$LoaderViewModel();
        }
    }

    @Override // androidx.lifecycle.ViewModel
    public final void onCleared() {
        SparseArrayCompat sparseArrayCompat = this.mLoaders;
        int i = sparseArrayCompat.size;
        if (i > 0) {
            throw AnimatedVisibilityScope$animateEnterExit$$inlined$debugInspectorInfo$1$$ExternalSyntheticOutline0.m(sparseArrayCompat.values[0]);
        }
        Object[] objArr = sparseArrayCompat.values;
        for (int i2 = 0; i2 < i; i2++) {
            objArr[i2] = null;
        }
        sparseArrayCompat.size = 0;
    }
}
