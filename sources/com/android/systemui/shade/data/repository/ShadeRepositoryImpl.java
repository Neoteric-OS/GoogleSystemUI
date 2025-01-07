package com.android.systemui.shade.data.repository;

import kotlinx.coroutines.flow.ReadonlyStateFlow;
import kotlinx.coroutines.flow.StateFlowImpl;
import kotlinx.coroutines.flow.StateFlowKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class ShadeRepositoryImpl implements ShadeRepository {
    public final StateFlowImpl _currentFling;
    public final StateFlowImpl _isShadeLayoutWide;
    public final StateFlowImpl _legacyExpandImmediate;
    public final StateFlowImpl _legacyExpandedOrAwaitingInputTransfer;
    public final StateFlowImpl _legacyIsClosing;
    public final StateFlowImpl _legacyIsQsExpanded;
    public final StateFlowImpl _legacyQsFullscreen;
    public final StateFlowImpl _legacyQsTracking;
    public final StateFlowImpl _legacyShadeExpansion;
    public final StateFlowImpl _legacyShadeTracking;
    public final StateFlowImpl _lockscreenShadeExpansion;
    public final StateFlowImpl _qsExpansion;
    public final StateFlowImpl _udfpsTransitionToFullShadeProgress;
    public final ReadonlyStateFlow currentFling;
    public final ReadonlyStateFlow isShadeLayoutWide;
    public final ReadonlyStateFlow legacyExpandImmediate;
    public final ReadonlyStateFlow legacyExpandedOrAwaitingInputTransfer;
    public final ReadonlyStateFlow legacyIsClosing;
    public final ReadonlyStateFlow legacyIsQsExpanded;
    public final StateFlowImpl legacyLockscreenShadeTracking;
    public final ReadonlyStateFlow legacyQsFullscreen;
    public final ReadonlyStateFlow legacyQsTracking;
    public final ReadonlyStateFlow legacyShadeExpansion;
    public final ReadonlyStateFlow legacyShadeTracking;
    public final ReadonlyStateFlow lockscreenShadeExpansion;
    public final ReadonlyStateFlow qsExpansion;
    public final ReadonlyStateFlow udfpsTransitionToFullShadeProgress;

    public ShadeRepositoryImpl() {
        Float valueOf = Float.valueOf(0.0f);
        StateFlowImpl MutableStateFlow = StateFlowKt.MutableStateFlow(valueOf);
        this._qsExpansion = MutableStateFlow;
        this.qsExpansion = new ReadonlyStateFlow(MutableStateFlow);
        StateFlowImpl MutableStateFlow2 = StateFlowKt.MutableStateFlow(valueOf);
        this._lockscreenShadeExpansion = MutableStateFlow2;
        this.lockscreenShadeExpansion = new ReadonlyStateFlow(MutableStateFlow2);
        StateFlowImpl MutableStateFlow3 = StateFlowKt.MutableStateFlow(valueOf);
        this._udfpsTransitionToFullShadeProgress = MutableStateFlow3;
        new ReadonlyStateFlow(MutableStateFlow3);
        StateFlowImpl MutableStateFlow4 = StateFlowKt.MutableStateFlow(null);
        this._currentFling = MutableStateFlow4;
        this.currentFling = new ReadonlyStateFlow(MutableStateFlow4);
        StateFlowImpl MutableStateFlow5 = StateFlowKt.MutableStateFlow(valueOf);
        this._legacyShadeExpansion = MutableStateFlow5;
        this.legacyShadeExpansion = new ReadonlyStateFlow(MutableStateFlow5);
        Boolean bool = Boolean.FALSE;
        StateFlowImpl MutableStateFlow6 = StateFlowKt.MutableStateFlow(bool);
        this._legacyShadeTracking = MutableStateFlow6;
        this.legacyShadeTracking = new ReadonlyStateFlow(MutableStateFlow6);
        this.legacyLockscreenShadeTracking = StateFlowKt.MutableStateFlow(bool);
        StateFlowImpl MutableStateFlow7 = StateFlowKt.MutableStateFlow(bool);
        this._legacyQsTracking = MutableStateFlow7;
        this.legacyQsTracking = new ReadonlyStateFlow(MutableStateFlow7);
        StateFlowImpl MutableStateFlow8 = StateFlowKt.MutableStateFlow(bool);
        this._legacyExpandedOrAwaitingInputTransfer = MutableStateFlow8;
        this.legacyExpandedOrAwaitingInputTransfer = new ReadonlyStateFlow(MutableStateFlow8);
        StateFlowImpl MutableStateFlow9 = StateFlowKt.MutableStateFlow(bool);
        this._legacyIsQsExpanded = MutableStateFlow9;
        this.legacyIsQsExpanded = new ReadonlyStateFlow(MutableStateFlow9);
        StateFlowImpl MutableStateFlow10 = StateFlowKt.MutableStateFlow(bool);
        this._legacyExpandImmediate = MutableStateFlow10;
        this.legacyExpandImmediate = new ReadonlyStateFlow(MutableStateFlow10);
        StateFlowImpl MutableStateFlow11 = StateFlowKt.MutableStateFlow(bool);
        this._legacyQsFullscreen = MutableStateFlow11;
        this.legacyQsFullscreen = new ReadonlyStateFlow(MutableStateFlow11);
        StateFlowImpl MutableStateFlow12 = StateFlowKt.MutableStateFlow(bool);
        this._isShadeLayoutWide = MutableStateFlow12;
        this.isShadeLayoutWide = new ReadonlyStateFlow(MutableStateFlow12);
        StateFlowImpl MutableStateFlow13 = StateFlowKt.MutableStateFlow(bool);
        this._legacyIsClosing = MutableStateFlow13;
        this.legacyIsClosing = new ReadonlyStateFlow(MutableStateFlow13);
    }
}
