package com.android.systemui.classifier;

import android.hardware.biometrics.BiometricSourceType;
import android.util.Log;
import android.view.InputEvent;
import android.view.KeyEvent;
import android.view.MotionEvent;
import com.android.keyguard.KeyguardUpdateMonitor;
import com.android.keyguard.KeyguardUpdateMonitorCallback;
import com.android.systemui.classifier.FalsingClassifier;
import com.android.systemui.classifier.FalsingCollectorImpl;
import com.android.systemui.communal.domain.interactor.CommunalInteractor;
import com.android.systemui.dock.DockManager;
import com.android.systemui.plugins.FalsingManager;
import com.android.systemui.plugins.statusbar.StatusBarStateController;
import com.android.systemui.shade.domain.interactor.ShadeInteractor;
import com.android.systemui.shade.domain.interactor.ShadeInteractorImpl;
import com.android.systemui.statusbar.StatusBarState;
import com.android.systemui.statusbar.policy.BatteryController;
import com.android.systemui.statusbar.policy.BatteryControllerImpl;
import com.android.systemui.statusbar.policy.KeyguardStateController;
import com.android.systemui.statusbar.policy.KeyguardStateControllerImpl;
import com.android.systemui.user.domain.interactor.SelectedUserInteractor;
import com.android.systemui.util.concurrency.DelayableExecutor;
import com.android.systemui.util.kotlin.BooleanFlowOperators$not$$inlined$map$1;
import com.android.systemui.util.kotlin.JavaAdapter;
import com.android.systemui.util.sensors.ProximitySensor;
import com.android.systemui.util.sensors.ProximitySensorImpl;
import com.android.systemui.util.sensors.ThresholdSensor;
import com.android.systemui.util.sensors.ThresholdSensorEvent;
import com.android.systemui.util.time.SystemClock;
import com.android.systemui.util.time.SystemClockImpl;
import com.google.android.systemui.dreamliner.DockObserver;
import dagger.Lazy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Objects;
import java.util.Set;
import java.util.function.Consumer;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.StateFlow;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class FalsingCollectorImpl implements FalsingCollector {
    public static final boolean DEBUG = Log.isLoggable("FalsingCollector", 3);
    public boolean mAvoidGesture;
    public final BatteryController mBatteryController;
    public final Lazy mCommunalInteractorLazy;
    public final DockManager mDockManager;
    public final FalsingDataProvider mFalsingDataProvider;
    public final FalsingManager mFalsingManager;
    public final HistoryTracker mHistoryTracker;
    public final JavaAdapter mJavaAdapter;
    public final KeyguardStateController mKeyguardStateController;
    public final KeyguardUpdateMonitor mKeyguardUpdateMonitor;
    public final DelayableExecutor mMainExecutor;
    public MotionEvent mPendingDownEvent;
    public final ProximitySensor mProximitySensor;
    public boolean mScreenOn;
    public boolean mSessionStarted;
    public final Lazy mShadeInteractorLazy;
    public boolean mShowingAod;
    public boolean mShowingCommunalHub;
    public int mState;
    public final StatusBarStateController mStatusBarStateController;
    public final SystemClock mSystemClock;
    public final Lazy mUserInteractor;
    public final Set mAcceptedKeycodes = new HashSet(Arrays.asList(66, 111, 59, 60, 62));
    public final FalsingCollectorImpl$$ExternalSyntheticLambda3 mSensorEventListener = new ThresholdSensor.Listener() { // from class: com.android.systemui.classifier.FalsingCollectorImpl$$ExternalSyntheticLambda3
        @Override // com.android.systemui.util.sensors.ThresholdSensor.Listener
        public final void onThresholdCrossed(ThresholdSensorEvent thresholdSensorEvent) {
            FalsingCollectorImpl falsingCollectorImpl = FalsingCollectorImpl.this;
            falsingCollectorImpl.getClass();
            falsingCollectorImpl.mFalsingManager.onProximityEvent(new FalsingCollectorImpl.ProximityEventImpl(thresholdSensorEvent));
        }
    };
    public final AnonymousClass1 mStatusBarStateListener = new StatusBarStateController.StateListener() { // from class: com.android.systemui.classifier.FalsingCollectorImpl.1
        @Override // com.android.systemui.plugins.statusbar.StatusBarStateController.StateListener
        public final void onStateChanged(int i) {
            FalsingCollectorImpl.logDebug("StatusBarState=" + StatusBarState.toString(i));
            FalsingCollectorImpl falsingCollectorImpl = FalsingCollectorImpl.this;
            falsingCollectorImpl.mState = i;
            falsingCollectorImpl.updateSessionActive();
        }
    };
    public final AnonymousClass2 mKeyguardStateControllerCallback = new KeyguardStateController.Callback() { // from class: com.android.systemui.classifier.FalsingCollectorImpl.2
        @Override // com.android.systemui.statusbar.policy.KeyguardStateController.Callback
        public final void onKeyguardShowingChanged() {
            FalsingCollectorImpl.this.updateSensorRegistration();
        }
    };
    public final KeyguardUpdateMonitorCallback mKeyguardUpdateCallback = new KeyguardUpdateMonitorCallback() { // from class: com.android.systemui.classifier.FalsingCollectorImpl.3
        @Override // com.android.keyguard.KeyguardUpdateMonitorCallback
        public final void onBiometricAuthenticated(int i, BiometricSourceType biometricSourceType, boolean z) {
            FalsingCollectorImpl falsingCollectorImpl = FalsingCollectorImpl.this;
            if (i == ((SelectedUserInteractor) falsingCollectorImpl.mUserInteractor.get()).getSelectedUserId() && biometricSourceType == BiometricSourceType.FACE) {
                falsingCollectorImpl.mFalsingDataProvider.mJustUnlockedWithFace = true;
            }
        }
    };
    public final AnonymousClass4 mBatteryListener = new BatteryController.BatteryStateChangeCallback() { // from class: com.android.systemui.classifier.FalsingCollectorImpl.4
        @Override // com.android.systemui.statusbar.policy.BatteryController.BatteryStateChangeCallback
        public final void onWirelessChargingChanged(boolean z) {
            FalsingCollectorImpl falsingCollectorImpl = FalsingCollectorImpl.this;
            if (z || ((DockObserver) falsingCollectorImpl.mDockManager).isDocked()) {
                ((ProximitySensorImpl) falsingCollectorImpl.mProximitySensor).pause();
            } else {
                ((ProximitySensorImpl) falsingCollectorImpl.mProximitySensor).resume();
            }
        }
    };
    public final AnonymousClass5 mDockEventListener = new DockManager.DockEventListener() { // from class: com.android.systemui.classifier.FalsingCollectorImpl.5
        @Override // com.android.systemui.dock.DockManager.DockEventListener
        public final void onEvent(int i) {
            FalsingCollectorImpl falsingCollectorImpl = FalsingCollectorImpl.this;
            if (i != 0 || ((BatteryControllerImpl) falsingCollectorImpl.mBatteryController).mWirelessCharging) {
                ((ProximitySensorImpl) falsingCollectorImpl.mProximitySensor).pause();
            } else {
                ((ProximitySensorImpl) falsingCollectorImpl.mProximitySensor).resume();
            }
        }
    };

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class ProximityEventImpl implements FalsingManager.ProximityEvent {
        public final ThresholdSensorEvent mThresholdSensorEvent;

        public ProximityEventImpl(ThresholdSensorEvent thresholdSensorEvent) {
            this.mThresholdSensorEvent = thresholdSensorEvent;
        }

        @Override // com.android.systemui.plugins.FalsingManager.ProximityEvent
        public final boolean getCovered() {
            return this.mThresholdSensorEvent.mBelow;
        }

        @Override // com.android.systemui.plugins.FalsingManager.ProximityEvent
        public final long getTimestampNs() {
            return this.mThresholdSensorEvent.mTimestampNs;
        }
    }

    /* JADX WARN: Type inference failed for: r1v1, types: [com.android.systemui.classifier.FalsingCollectorImpl$$ExternalSyntheticLambda3] */
    /* JADX WARN: Type inference failed for: r1v2, types: [com.android.systemui.classifier.FalsingCollectorImpl$1] */
    /* JADX WARN: Type inference failed for: r1v3, types: [com.android.systemui.classifier.FalsingCollectorImpl$2] */
    /* JADX WARN: Type inference failed for: r1v5, types: [com.android.systemui.classifier.FalsingCollectorImpl$4] */
    /* JADX WARN: Type inference failed for: r1v6, types: [com.android.systemui.classifier.FalsingCollectorImpl$5] */
    public FalsingCollectorImpl(FalsingDataProvider falsingDataProvider, FalsingManager falsingManager, KeyguardUpdateMonitor keyguardUpdateMonitor, HistoryTracker historyTracker, ProximitySensor proximitySensor, StatusBarStateController statusBarStateController, KeyguardStateController keyguardStateController, Lazy lazy, BatteryController batteryController, DockManager dockManager, DelayableExecutor delayableExecutor, JavaAdapter javaAdapter, SystemClock systemClock, Lazy lazy2, Lazy lazy3, Lazy lazy4, Lazy lazy5) {
        this.mFalsingDataProvider = falsingDataProvider;
        this.mFalsingManager = falsingManager;
        this.mKeyguardUpdateMonitor = keyguardUpdateMonitor;
        this.mHistoryTracker = historyTracker;
        this.mProximitySensor = proximitySensor;
        this.mStatusBarStateController = statusBarStateController;
        this.mKeyguardStateController = keyguardStateController;
        this.mShadeInteractorLazy = lazy;
        this.mBatteryController = batteryController;
        this.mDockManager = dockManager;
        this.mMainExecutor = delayableExecutor;
        this.mJavaAdapter = javaAdapter;
        this.mSystemClock = systemClock;
        this.mUserInteractor = lazy2;
        this.mCommunalInteractorLazy = lazy3;
    }

    public static void logDebug(String str) {
        if (DEBUG) {
            Log.d("FalsingCollector", str);
        }
    }

    @Override // com.android.systemui.classifier.FalsingCollector
    public final void avoidGesture() {
        logDebug("REAL: avoidGesture");
        this.mAvoidGesture = true;
        MotionEvent motionEvent = this.mPendingDownEvent;
        if (motionEvent != null) {
            motionEvent.recycle();
            this.mPendingDownEvent = null;
        }
    }

    @Override // com.android.systemui.classifier.FalsingCollector
    public final void init() {
        ProximitySensorImpl proximitySensorImpl = (ProximitySensorImpl) this.mProximitySensor;
        proximitySensorImpl.setTag("FalsingCollector");
        proximitySensorImpl.setDelay();
        StatusBarStateController statusBarStateController = this.mStatusBarStateController;
        statusBarStateController.addCallback(this.mStatusBarStateListener);
        this.mState = statusBarStateController.getState();
        ((KeyguardStateControllerImpl) this.mKeyguardStateController).addCallback(this.mKeyguardStateControllerCallback);
        this.mKeyguardUpdateMonitor.registerCallback(this.mKeyguardUpdateCallback);
        StateFlow isQsExpanded = ((ShadeInteractorImpl) ((ShadeInteractor) this.mShadeInteractorLazy.get())).baseShadeInteractor.isQsExpanded();
        final int i = 0;
        Consumer consumer = new Consumer(this) { // from class: com.android.systemui.classifier.FalsingCollectorImpl$$ExternalSyntheticLambda0
            public final /* synthetic */ FalsingCollectorImpl f$0;

            {
                this.f$0 = this;
            }

            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                int i2 = i;
                FalsingCollectorImpl falsingCollectorImpl = this.f$0;
                Boolean bool = (Boolean) obj;
                switch (i2) {
                    case 0:
                        falsingCollectorImpl.onQsExpansionChanged(bool);
                        break;
                    default:
                        boolean booleanValue = bool.booleanValue();
                        falsingCollectorImpl.getClass();
                        FalsingCollectorImpl.logDebug("REAL: onShowingCommunalHubChanged(" + booleanValue + ")");
                        falsingCollectorImpl.mShowingCommunalHub = booleanValue;
                        falsingCollectorImpl.updateSessionActive();
                        break;
                }
            }
        };
        JavaAdapter javaAdapter = this.mJavaAdapter;
        javaAdapter.alwaysCollectFlow(isQsExpanded, consumer);
        CommunalInteractor communalInteractor = (CommunalInteractor) this.mCommunalInteractorLazy.get();
        Flow distinctUntilChanged = FlowKt.distinctUntilChanged(new BooleanFlowOperators$not$$inlined$map$1(1, (Flow[]) CollectionsKt.toList(ArraysKt.asIterable(new Flow[]{communalInteractor.isCommunalEnabled, communalInteractor.isCommunalShowing})).toArray(new Flow[0])));
        final int i2 = 1;
        javaAdapter.alwaysCollectFlow(distinctUntilChanged, new Consumer(this) { // from class: com.android.systemui.classifier.FalsingCollectorImpl$$ExternalSyntheticLambda0
            public final /* synthetic */ FalsingCollectorImpl f$0;

            {
                this.f$0 = this;
            }

            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                int i22 = i2;
                FalsingCollectorImpl falsingCollectorImpl = this.f$0;
                Boolean bool = (Boolean) obj;
                switch (i22) {
                    case 0:
                        falsingCollectorImpl.onQsExpansionChanged(bool);
                        break;
                    default:
                        boolean booleanValue = bool.booleanValue();
                        falsingCollectorImpl.getClass();
                        FalsingCollectorImpl.logDebug("REAL: onShowingCommunalHubChanged(" + booleanValue + ")");
                        falsingCollectorImpl.mShowingCommunalHub = booleanValue;
                        falsingCollectorImpl.updateSessionActive();
                        break;
                }
            }
        });
        this.mBatteryController.addCallback(this.mBatteryListener);
        ((DockObserver) this.mDockManager).addListener(this.mDockEventListener);
    }

    @Override // com.android.systemui.classifier.FalsingCollector
    public final void onA11yAction() {
        logDebug("REAL: onA11yAction");
        MotionEvent motionEvent = this.mPendingDownEvent;
        if (motionEvent != null) {
            motionEvent.recycle();
            this.mPendingDownEvent = null;
        }
        FalsingDataProvider falsingDataProvider = this.mFalsingDataProvider;
        falsingDataProvider.completePriorGesture();
        falsingDataProvider.mA11YAction = true;
    }

    @Override // com.android.systemui.classifier.FalsingCollector
    public final void onBouncerHidden() {
        logDebug("REAL: onBouncerHidden");
        if (this.mSessionStarted) {
            this.mProximitySensor.register(this.mSensorEventListener);
        }
    }

    @Override // com.android.systemui.classifier.FalsingCollector
    public final void onBouncerShown() {
        logDebug("REAL: onBouncerShown");
        this.mProximitySensor.unregister(this.mSensorEventListener);
    }

    @Override // com.android.systemui.classifier.FalsingCollector
    public final void onKeyEvent(KeyEvent keyEvent) {
        logDebug("REAL: onKeyEvent(" + KeyEvent.actionToString(keyEvent.getAction()) + ")");
        if (keyEvent.getAction() == 1 && this.mAcceptedKeycodes.contains(Integer.valueOf(keyEvent.getKeyCode()))) {
            TimeLimitedInputEventBuffer timeLimitedInputEventBuffer = this.mFalsingDataProvider.mRecentKeyEvents;
            timeLimitedInputEventBuffer.mInputEvents.add(keyEvent);
            timeLimitedInputEventBuffer.ejectOldEvents();
        }
    }

    @Override // com.android.systemui.classifier.FalsingCollector
    public final void onMotionEventComplete() {
        logDebug("REAL: onMotionEventComplete");
        final FalsingDataProvider falsingDataProvider = this.mFalsingDataProvider;
        Objects.requireNonNull(falsingDataProvider);
        this.mMainExecutor.executeDelayed(new Runnable() { // from class: com.android.systemui.classifier.FalsingCollectorImpl$$ExternalSyntheticLambda2
            @Override // java.lang.Runnable
            public final void run() {
                FalsingDataProvider falsingDataProvider2 = FalsingDataProvider.this;
                if (falsingDataProvider2.mRecentMotionEvents.mInputEvents.isEmpty()) {
                    return;
                }
                TimeLimitedInputEventBuffer timeLimitedInputEventBuffer = falsingDataProvider2.mRecentMotionEvents;
                int actionMasked = ((MotionEvent) ((InputEvent) ((ArrayList) timeLimitedInputEventBuffer.mInputEvents).get(timeLimitedInputEventBuffer.size() - 1))).getActionMasked();
                if (actionMasked == 1 || actionMasked == 3) {
                    falsingDataProvider2.completePriorGesture();
                }
            }
        }, 100L);
    }

    public void onQsExpansionChanged(Boolean bool) {
        logDebug("REAL: onQsExpansionChanged(" + bool + ")");
        boolean booleanValue = bool.booleanValue();
        ProximitySensor proximitySensor = this.mProximitySensor;
        FalsingCollectorImpl$$ExternalSyntheticLambda3 falsingCollectorImpl$$ExternalSyntheticLambda3 = this.mSensorEventListener;
        if (booleanValue) {
            proximitySensor.unregister(falsingCollectorImpl$$ExternalSyntheticLambda3);
        } else if (this.mSessionStarted) {
            proximitySensor.register(falsingCollectorImpl$$ExternalSyntheticLambda3);
        }
    }

    @Override // com.android.systemui.classifier.FalsingCollector
    public final void onScreenOff() {
        logDebug("REAL: onScreenOff");
        this.mScreenOn = false;
        updateSessionActive();
    }

    @Override // com.android.systemui.classifier.FalsingCollector
    public final void onScreenOnFromTouch() {
        logDebug("REAL: onScreenOnFromTouch");
        onScreenTurningOn();
    }

    @Override // com.android.systemui.classifier.FalsingCollector
    public final void onScreenTurningOn() {
        logDebug("REAL: onScreenTurningOn");
        this.mScreenOn = true;
        updateSessionActive();
    }

    @Override // com.android.systemui.classifier.FalsingCollector
    public final void onSuccessfulUnlock() {
        logDebug("REAL: onSuccessfulUnlock");
        this.mFalsingManager.onSuccessfulUnlock();
        sessionEnd();
    }

    @Override // com.android.systemui.classifier.FalsingCollector
    public final void onTouchEvent(MotionEvent motionEvent) {
        logDebug("REAL: onTouchEvent(" + MotionEvent.actionToString(motionEvent.getActionMasked()) + ")");
        if (!((KeyguardStateControllerImpl) this.mKeyguardStateController).mShowing) {
            avoidGesture();
            return;
        }
        if (motionEvent.getActionMasked() == 4) {
            return;
        }
        if (motionEvent.getActionMasked() == 0) {
            this.mPendingDownEvent = MotionEvent.obtain(motionEvent);
            this.mAvoidGesture = false;
        } else {
            if (this.mAvoidGesture) {
                return;
            }
            MotionEvent motionEvent2 = this.mPendingDownEvent;
            FalsingDataProvider falsingDataProvider = this.mFalsingDataProvider;
            if (motionEvent2 != null) {
                falsingDataProvider.onMotionEvent(motionEvent2);
                this.mPendingDownEvent.recycle();
                this.mPendingDownEvent = null;
            }
            falsingDataProvider.onMotionEvent(motionEvent);
        }
    }

    public final void sessionEnd() {
        if (this.mSessionStarted) {
            logDebug("Ending Session");
            this.mSessionStarted = false;
            FalsingDataProvider falsingDataProvider = this.mFalsingDataProvider;
            Iterator it = falsingDataProvider.mRecentMotionEvents.mInputEvents.iterator();
            while (it.hasNext()) {
                ((MotionEvent) it.next()).recycle();
            }
            falsingDataProvider.mRecentMotionEvents.clear();
            TimeLimitedInputEventBuffer timeLimitedInputEventBuffer = falsingDataProvider.mRecentKeyEvents;
            Iterator it2 = timeLimitedInputEventBuffer.mInputEvents.iterator();
            while (it2.hasNext()) {
                ((KeyEvent) it2.next()).recycle();
            }
            timeLimitedInputEventBuffer.clear();
            falsingDataProvider.mDirty = true;
            ((ArrayList) falsingDataProvider.mSessionListeners).forEach(new FalsingDataProvider$$ExternalSyntheticLambda0(1));
        }
    }

    @Override // com.android.systemui.classifier.FalsingCollector
    public final void setShowingAod(boolean z) {
        logDebug("REAL: setShowingAod(" + z + ")");
        this.mShowingAod = z;
        updateSessionActive();
    }

    @Override // com.android.systemui.classifier.FalsingCollector
    public final void updateFalseConfidence(FalsingClassifier.Result result) {
        logDebug("REAL: updateFalseConfidence(" + result.mFalsed + ")");
        Set singleton = Collections.singleton(result);
        ((SystemClockImpl) this.mSystemClock).getClass();
        this.mHistoryTracker.addResults(singleton, android.os.SystemClock.uptimeMillis());
    }

    public final void updateSensorRegistration() {
        int i = this.mState;
        boolean z = false;
        boolean z2 = i == 1;
        if (i == 0) {
            KeyguardStateController keyguardStateController = this.mKeyguardStateController;
            if (((KeyguardStateControllerImpl) keyguardStateController).mShowing && ((KeyguardStateControllerImpl) keyguardStateController).mOccluded) {
                z = true;
            }
        }
        boolean z3 = this.mScreenOn;
        ProximitySensor proximitySensor = this.mProximitySensor;
        FalsingCollectorImpl$$ExternalSyntheticLambda3 falsingCollectorImpl$$ExternalSyntheticLambda3 = this.mSensorEventListener;
        if (z3 && !this.mShowingAod && (z2 || z)) {
            proximitySensor.register(falsingCollectorImpl$$ExternalSyntheticLambda3);
        } else {
            proximitySensor.unregister(falsingCollectorImpl$$ExternalSyntheticLambda3);
        }
    }

    public final void updateSessionActive() {
        int i;
        boolean z;
        boolean z2;
        boolean z3 = this.mScreenOn;
        if (!z3 || (i = this.mState) != 1 || (z = this.mShowingAod) || (z2 = this.mShowingCommunalHub)) {
            sessionEnd();
        } else if (!this.mSessionStarted && z3 && i == 1 && !z && !z2) {
            logDebug("Starting Session");
            this.mSessionStarted = true;
            FalsingDataProvider falsingDataProvider = this.mFalsingDataProvider;
            falsingDataProvider.mJustUnlockedWithFace = false;
            ((ArrayList) falsingDataProvider.mSessionListeners).forEach(new FalsingDataProvider$$ExternalSyntheticLambda0(0));
        }
        updateSensorRegistration();
    }
}
