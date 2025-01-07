package com.android.systemui.complication;

import androidx.lifecycle.LiveData;
import com.android.systemui.dreams.DreamOverlayStateController;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class ComplicationCollectionLiveData extends LiveData {
    public final DreamOverlayStateController mDreamOverlayStateController;
    public final DreamOverlayStateController.Callback mStateControllerCallback = new DreamOverlayStateController.Callback() { // from class: com.android.systemui.complication.ComplicationCollectionLiveData.1
        @Override // com.android.systemui.dreams.DreamOverlayStateController.Callback
        public final void onAvailableComplicationTypesChanged() {
            ComplicationCollectionLiveData complicationCollectionLiveData = ComplicationCollectionLiveData.this;
            complicationCollectionLiveData.setValue(complicationCollectionLiveData.mDreamOverlayStateController.getComplications());
        }

        @Override // com.android.systemui.dreams.DreamOverlayStateController.Callback
        public final void onComplicationsChanged() {
            ComplicationCollectionLiveData complicationCollectionLiveData = ComplicationCollectionLiveData.this;
            complicationCollectionLiveData.setValue(complicationCollectionLiveData.mDreamOverlayStateController.getComplications());
        }
    };

    public ComplicationCollectionLiveData(DreamOverlayStateController dreamOverlayStateController) {
        this.mDreamOverlayStateController = dreamOverlayStateController;
    }

    @Override // androidx.lifecycle.LiveData
    public final void onActive() {
        DreamOverlayStateController.Callback callback = this.mStateControllerCallback;
        DreamOverlayStateController dreamOverlayStateController = this.mDreamOverlayStateController;
        dreamOverlayStateController.addCallback(callback);
        setValue(dreamOverlayStateController.getComplications());
    }

    @Override // androidx.lifecycle.LiveData
    public final void onInactive() {
        this.mDreamOverlayStateController.removeCallback(this.mStateControllerCallback);
    }
}
