package com.android.systemui.power.data.repository;

import android.content.Context;
import android.os.PowerManager;
import androidx.appsearch.app.DocumentClassFactoryRegistry$$ExternalSyntheticOutline0;
import com.android.systemui.broadcast.BroadcastDispatcher;
import com.android.systemui.power.shared.model.ScreenPowerState;
import com.android.systemui.power.shared.model.WakeSleepReason;
import com.android.systemui.power.shared.model.WakefulnessModel;
import com.android.systemui.power.shared.model.WakefulnessState;
import com.android.systemui.util.time.SystemClock;
import com.android.systemui.util.time.SystemClockImpl;
import com.android.systemui.utils.coroutines.flow.FlowConflatedKt;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.ReadonlyStateFlow;
import kotlinx.coroutines.flow.StateFlowImpl;
import kotlinx.coroutines.flow.StateFlowKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class PowerRepositoryImpl {
    public final StateFlowImpl _screenPowerState;
    public final StateFlowImpl _wakefulness;
    public final Context applicationContext;
    public final Flow isInteractive;
    public final PowerManager manager;
    public final ReadonlyStateFlow screenPowerState;
    public final SystemClock systemClock;
    public final ReadonlyStateFlow wakefulness;

    public PowerRepositoryImpl(PowerManager powerManager, Context context, SystemClock systemClock, BroadcastDispatcher broadcastDispatcher) {
        this.manager = powerManager;
        this.applicationContext = context;
        this.systemClock = systemClock;
        this.isInteractive = FlowConflatedKt.conflatedCallbackFlow(new PowerRepositoryImpl$isInteractive$1(broadcastDispatcher, this, null));
        WakefulnessState wakefulnessState = WakefulnessState.AWAKE;
        WakeSleepReason wakeSleepReason = WakeSleepReason.OTHER;
        StateFlowImpl MutableStateFlow = StateFlowKt.MutableStateFlow(new WakefulnessModel(wakefulnessState, wakeSleepReason, wakeSleepReason, false));
        this._wakefulness = MutableStateFlow;
        this.wakefulness = new ReadonlyStateFlow(MutableStateFlow);
        StateFlowImpl MutableStateFlow2 = StateFlowKt.MutableStateFlow(ScreenPowerState.SCREEN_OFF);
        this._screenPowerState = MutableStateFlow2;
        this.screenPowerState = new ReadonlyStateFlow(MutableStateFlow2);
    }

    public static void updateWakefulness$default(PowerRepositoryImpl powerRepositoryImpl, WakefulnessState wakefulnessState, WakeSleepReason wakeSleepReason, WakeSleepReason wakeSleepReason2, boolean z, int i) {
        if ((i & 1) != 0) {
            wakefulnessState = ((WakefulnessModel) ((StateFlowImpl) powerRepositoryImpl.wakefulness.$$delegate_0).getValue()).internalWakefulnessState;
        }
        if ((i & 2) != 0) {
            wakeSleepReason = ((WakefulnessModel) ((StateFlowImpl) powerRepositoryImpl.wakefulness.$$delegate_0).getValue()).lastWakeReason;
        }
        if ((i & 4) != 0) {
            wakeSleepReason2 = ((WakefulnessModel) ((StateFlowImpl) powerRepositoryImpl.wakefulness.$$delegate_0).getValue()).lastSleepReason;
        }
        if ((i & 8) != 0) {
            z = ((WakefulnessModel) ((StateFlowImpl) powerRepositoryImpl.wakefulness.$$delegate_0).getValue()).powerButtonLaunchGestureTriggered;
        }
        StateFlowImpl stateFlowImpl = powerRepositoryImpl._wakefulness;
        WakefulnessModel wakefulnessModel = new WakefulnessModel(wakefulnessState, wakeSleepReason, wakeSleepReason2, z);
        stateFlowImpl.getClass();
        stateFlowImpl.updateState(null, wakefulnessModel);
    }

    public final void wakeUp(int i, String str) {
        PowerManager powerManager = this.manager;
        ((SystemClockImpl) this.systemClock).getClass();
        powerManager.wakeUp(android.os.SystemClock.uptimeMillis(), i, DocumentClassFactoryRegistry$$ExternalSyntheticOutline0.m$1(this.applicationContext.getPackageName(), ":", str));
    }
}
