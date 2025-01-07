package com.android.settingslib.volume.data.repository;

import android.media.IVolumeController;
import com.android.settingslib.volume.data.model.VolumeControllerEvent;
import kotlinx.coroutines.flow.ReadonlySharedFlow;
import kotlinx.coroutines.flow.SharedFlowImpl;
import kotlinx.coroutines.flow.SharedFlowKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class ProducingVolumeController extends IVolumeController.Stub {
    public final ReadonlySharedFlow events;
    public final SharedFlowImpl mutableEvents;

    public ProducingVolumeController() {
        SharedFlowImpl MutableSharedFlow$default = SharedFlowKt.MutableSharedFlow$default(0, 32, null, 5);
        this.mutableEvents = MutableSharedFlow$default;
        this.events = new ReadonlySharedFlow(MutableSharedFlow$default);
    }

    public final void dismiss() {
        this.mutableEvents.tryEmit(VolumeControllerEvent.Dismiss.INSTANCE);
    }

    public final void displayCsdWarning(int i, int i2) {
        this.mutableEvents.tryEmit(new VolumeControllerEvent.DisplayCsdWarning(i, i2));
    }

    public final void displaySafeVolumeWarning(int i) {
        this.mutableEvents.tryEmit(new VolumeControllerEvent.DisplaySafeVolumeWarning(i));
    }

    public final void masterMuteChanged(int i) {
        this.mutableEvents.tryEmit(new VolumeControllerEvent.MasterMuteChanged(i));
    }

    public final void setA11yMode(int i) {
        this.mutableEvents.tryEmit(new VolumeControllerEvent.SetA11yMode(i));
    }

    public final void setLayoutDirection(int i) {
        this.mutableEvents.tryEmit(new VolumeControllerEvent.SetLayoutDirection(i));
    }

    public final void volumeChanged(int i, int i2) {
        this.mutableEvents.tryEmit(new VolumeControllerEvent.VolumeChanged(i, i2));
    }
}
