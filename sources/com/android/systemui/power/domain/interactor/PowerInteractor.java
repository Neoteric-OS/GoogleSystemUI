package com.android.systemui.power.domain.interactor;

import android.os.PowerManager;
import android.os.SystemClock;
import com.android.systemui.classifier.FalsingCollector;
import com.android.systemui.plugins.statusbar.StatusBarStateController;
import com.android.systemui.power.data.repository.PowerRepositoryImpl;
import com.android.systemui.power.shared.model.ScreenPowerState;
import com.android.systemui.power.shared.model.WakefulnessModel;
import com.android.systemui.statusbar.phone.ScreenOffAnimation;
import com.android.systemui.statusbar.phone.ScreenOffAnimationController;
import com.android.systemui.util.time.SystemClockImpl;
import dagger.internal.Provider;
import java.util.Iterator;
import java.util.List;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.flow.DistinctFlowImpl;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.ReadonlyStateFlow;
import kotlinx.coroutines.flow.StateFlowImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class PowerInteractor {
    public static final Function2 checkEquivalentUnlessEmitDuplicatesUnderTest = new Function2() { // from class: com.android.systemui.power.domain.interactor.PowerInteractor$Companion$checkEquivalentUnlessEmitDuplicatesUnderTest$1
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            boolean booleanValue = ((Boolean) obj).booleanValue();
            boolean booleanValue2 = ((Boolean) obj2).booleanValue();
            Function2 function2 = PowerInteractor.checkEquivalentUnlessEmitDuplicatesUnderTest;
            return Boolean.valueOf(booleanValue == booleanValue2);
        }
    };
    public final Provider cameraGestureHelper;
    public final ReadonlyStateFlow detailedWakefulness;
    public final FalsingCollector falsingCollector;
    public final PowerInteractor$special$$inlined$map$1 isAsleep;
    public final DistinctFlowImpl isAwake;
    public final Flow isInteractive;
    public final PowerRepositoryImpl repository;
    public final ScreenOffAnimationController screenOffAnimationController;
    public final ReadonlyStateFlow screenPowerState;
    public final StatusBarStateController statusBarStateController;

    public PowerInteractor(PowerRepositoryImpl powerRepositoryImpl, FalsingCollector falsingCollector, ScreenOffAnimationController screenOffAnimationController, StatusBarStateController statusBarStateController, Provider provider) {
        this.repository = powerRepositoryImpl;
        this.falsingCollector = falsingCollector;
        this.screenOffAnimationController = screenOffAnimationController;
        this.statusBarStateController = statusBarStateController;
        this.cameraGestureHelper = provider;
        this.isInteractive = powerRepositoryImpl.isInteractive;
        ReadonlyStateFlow readonlyStateFlow = powerRepositoryImpl.wakefulness;
        this.detailedWakefulness = readonlyStateFlow;
        DistinctFlowImpl distinctUntilChanged = FlowKt.distinctUntilChanged(checkEquivalentUnlessEmitDuplicatesUnderTest, new PowerInteractor$special$$inlined$map$1(readonlyStateFlow, 0));
        this.isAwake = distinctUntilChanged;
        this.isAsleep = new PowerInteractor$special$$inlined$map$1(distinctUntilChanged, 1);
        this.screenPowerState = powerRepositoryImpl.screenPowerState;
    }

    public static void onUserTouch$default(PowerInteractor powerInteractor) {
        PowerRepositoryImpl powerRepositoryImpl = powerInteractor.repository;
        PowerManager powerManager = powerRepositoryImpl.manager;
        ((SystemClockImpl) powerRepositoryImpl.systemClock).getClass();
        powerManager.userActivity(SystemClock.uptimeMillis(), 2, 0);
    }

    public final void onScreenPowerStateUpdated(ScreenPowerState screenPowerState) {
        StateFlowImpl stateFlowImpl = this.repository._screenPowerState;
        stateFlowImpl.getClass();
        stateFlowImpl.updateState(null, screenPowerState);
    }

    public final void wakeUpForFullScreenIntent() {
        PowerRepositoryImpl powerRepositoryImpl = this.repository;
        if (((WakefulnessModel) ((StateFlowImpl) powerRepositoryImpl.wakefulness.$$delegate_0).getValue()).isAsleep() || this.statusBarStateController.isDozing()) {
            powerRepositoryImpl.wakeUp(2, "full_screen_intent");
        }
    }

    public final void wakeUpIfDozing(int i, String str) {
        if (this.statusBarStateController.isDozing()) {
            List list = this.screenOffAnimationController.animations;
            if (list == null || !list.isEmpty()) {
                Iterator it = list.iterator();
                while (it.hasNext()) {
                    if (((ScreenOffAnimation) it.next()).isAnimationPlaying()) {
                        return;
                    }
                }
            }
            this.repository.wakeUp(i, str);
            this.falsingCollector.onScreenOnFromTouch();
        }
    }
}
