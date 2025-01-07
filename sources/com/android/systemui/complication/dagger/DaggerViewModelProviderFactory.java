package com.android.systemui.complication.dagger;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class DaggerViewModelProviderFactory implements ViewModelProvider.Factory {
    public final ComplicationModule$$ExternalSyntheticLambda0 mCreator;

    public DaggerViewModelProviderFactory(ComplicationModule$$ExternalSyntheticLambda0 complicationModule$$ExternalSyntheticLambda0) {
        this.mCreator = complicationModule$$ExternalSyntheticLambda0;
    }

    @Override // androidx.lifecycle.ViewModelProvider.Factory
    public final ViewModel create(Class cls) {
        return this.mCreator.f$0;
    }
}
