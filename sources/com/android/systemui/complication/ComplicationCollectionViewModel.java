package com.android.systemui.complication;

import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class ComplicationCollectionViewModel extends ViewModel {
    public final MediatorLiveData mComplications;
    public final ComplicationViewModelTransformer mTransformer;

    public ComplicationCollectionViewModel(ComplicationCollectionLiveData complicationCollectionLiveData, ComplicationViewModelTransformer complicationViewModelTransformer) {
        this.mComplications = Transformations.map(complicationCollectionLiveData, new ComplicationCollectionViewModel$$ExternalSyntheticLambda0(this));
        this.mTransformer = complicationViewModelTransformer;
    }
}
