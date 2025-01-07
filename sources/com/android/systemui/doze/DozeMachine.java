package com.android.systemui.doze;

import android.content.res.Configuration;
import android.hardware.display.AmbientDisplayConfiguration;
import android.util.Log;
import com.android.internal.util.Preconditions;
import com.android.systemui.dock.DockManager;
import com.android.systemui.keyguard.WakefulnessLifecycle;
import com.android.systemui.settings.UserTracker;
import com.android.systemui.statusbar.phone.DozeServiceHost;
import com.android.systemui.util.Assert;
import com.android.systemui.util.wakelock.WakeLock;
import java.io.PrintWriter;
import java.util.ArrayList;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class DozeMachine {
    public static final boolean DEBUG = DozeService.DEBUG;
    public final AmbientDisplayConfiguration mAmbientDisplayConfig;
    public final DockManager mDockManager;
    public final DozeServiceHost mDozeHost;
    public final DozeLog mDozeLog;
    public final Service mDozeService;
    public final Part[] mParts;
    public int mPulseReason;
    public final UserTracker mUserTracker;
    public final WakeLock mWakeLock;
    public final WakefulnessLifecycle mWakefulnessLifecycle;
    public final ArrayList mQueuedRequests = new ArrayList();
    public State mState = State.UNINITIALIZED;
    public boolean mWakeLockHeldForCurrentState = false;
    public int mUiModeType = 1;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public interface Service {

        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        public abstract class Delegate implements Service {
            public final Service mDelegate;

            public Delegate(Service service) {
                this.mDelegate = service;
            }

            @Override // com.android.systemui.doze.DozeMachine.Service
            public final void finish() {
                this.mDelegate.finish();
            }

            @Override // com.android.systemui.doze.DozeMachine.Service
            public final void requestWakeUp(int i) {
                this.mDelegate.requestWakeUp(i);
            }

            @Override // com.android.systemui.doze.DozeMachine.Service
            public void setDozeScreenBrightness(int i) {
                this.mDelegate.setDozeScreenBrightness(i);
            }

            @Override // com.android.systemui.doze.DozeMachine.Service
            public void setDozeScreenBrightnessFloat(float f) {
                this.mDelegate.setDozeScreenBrightnessFloat(f);
            }

            @Override // com.android.systemui.doze.DozeMachine.Service
            public void setDozeScreenState(int i) {
                this.mDelegate.setDozeScreenState(i);
            }
        }

        void finish();

        void requestWakeUp(int i);

        void setDozeScreenBrightness(int i);

        void setDozeScreenBrightnessFloat(float f);

        void setDozeScreenState(int i);
    }

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class State {
        public static final /* synthetic */ State[] $VALUES;
        public static final State DOZE;
        public static final State DOZE_AOD;
        public static final State DOZE_AOD_DOCKED;
        public static final State DOZE_AOD_PAUSED;
        public static final State DOZE_AOD_PAUSING;
        public static final State DOZE_PULSE_DONE;
        public static final State DOZE_PULSING;
        public static final State DOZE_PULSING_BRIGHT;
        public static final State DOZE_REQUEST_PULSE;
        public static final State DOZE_SUSPEND_TRIGGERS;
        public static final State FINISH;
        public static final State INITIALIZED;
        public static final State UNINITIALIZED;

        static {
            State state = new State("UNINITIALIZED", 0);
            UNINITIALIZED = state;
            State state2 = new State("INITIALIZED", 1);
            INITIALIZED = state2;
            State state3 = new State("DOZE", 2);
            DOZE = state3;
            State state4 = new State("DOZE_SUSPEND_TRIGGERS", 3);
            DOZE_SUSPEND_TRIGGERS = state4;
            State state5 = new State("DOZE_AOD", 4);
            DOZE_AOD = state5;
            State state6 = new State("DOZE_REQUEST_PULSE", 5);
            DOZE_REQUEST_PULSE = state6;
            State state7 = new State("DOZE_PULSING", 6);
            DOZE_PULSING = state7;
            State state8 = new State("DOZE_PULSING_BRIGHT", 7);
            DOZE_PULSING_BRIGHT = state8;
            State state9 = new State("DOZE_PULSE_DONE", 8);
            DOZE_PULSE_DONE = state9;
            State state10 = new State("FINISH", 9);
            FINISH = state10;
            State state11 = new State("DOZE_AOD_PAUSED", 10);
            DOZE_AOD_PAUSED = state11;
            State state12 = new State("DOZE_AOD_PAUSING", 11);
            DOZE_AOD_PAUSING = state12;
            State state13 = new State("DOZE_AOD_DOCKED", 12);
            DOZE_AOD_DOCKED = state13;
            $VALUES = new State[]{state, state2, state3, state4, state5, state6, state7, state8, state9, state10, state11, state12, state13};
        }

        public static State valueOf(String str) {
            return (State) Enum.valueOf(State.class, str);
        }

        public static State[] values() {
            return (State[]) $VALUES.clone();
        }

        public final boolean isAlwaysOn() {
            return this == DOZE_AOD || this == DOZE_AOD_DOCKED;
        }
    }

    public DozeMachine(Service service, AmbientDisplayConfiguration ambientDisplayConfiguration, WakeLock wakeLock, WakefulnessLifecycle wakefulnessLifecycle, DozeLog dozeLog, DockManager dockManager, DozeServiceHost dozeServiceHost, Part[] partArr, UserTracker userTracker) {
        this.mDozeService = service;
        this.mAmbientDisplayConfig = ambientDisplayConfiguration;
        this.mWakefulnessLifecycle = wakefulnessLifecycle;
        this.mWakeLock = wakeLock;
        this.mDozeLog = dozeLog;
        this.mDockManager = dockManager;
        this.mDozeHost = dozeServiceHost;
        this.mParts = partArr;
        this.mUserTracker = userTracker;
        for (Part part : partArr) {
            part.setDozeMachine(this);
        }
    }

    public final State getState() {
        Assert.isMainThread();
        if (!isExecutingTransition()) {
            return this.mState;
        }
        throw new IllegalStateException("Cannot get state because there were pending transitions: " + this.mQueuedRequests);
    }

    public final boolean isExecutingTransition() {
        return !this.mQueuedRequests.isEmpty();
    }

    public final void onConfigurationChanged(Configuration configuration) {
        int i = configuration.uiMode & 15;
        if (this.mUiModeType == i) {
            return;
        }
        this.mUiModeType = i;
        for (Part part : this.mParts) {
            part.onUiModeTypeChanged(this.mUiModeType);
        }
    }

    public final void requestState(State state) {
        Preconditions.checkArgument(state != State.DOZE_REQUEST_PULSE);
        requestState(state, -1);
    }

    /* JADX WARN: Code restructure failed: missing block: B:123:0x0040, code lost:
    
        if (r2 != 12) goto L21;
     */
    /* JADX WARN: Removed duplicated region for block: B:67:0x0238  */
    /* JADX WARN: Removed duplicated region for block: B:71:0x0241  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void transitionTo(com.android.systemui.doze.DozeMachine.State r17, int r18) {
        /*
            Method dump skipped, instructions count: 658
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.doze.DozeMachine.transitionTo(com.android.systemui.doze.DozeMachine$State, int):void");
    }

    public final void requestState(State state, int i) {
        Assert.isMainThread();
        if (DEBUG) {
            Log.i("DozeMachine", "request: current=" + this.mState + " req=" + state, new Throwable("here"));
        }
        boolean isExecutingTransition = isExecutingTransition();
        this.mQueuedRequests.add(state);
        if (isExecutingTransition) {
            return;
        }
        WakeLock wakeLock = this.mWakeLock;
        wakeLock.acquire("DozeMachine#requestState");
        for (int i2 = 0; i2 < this.mQueuedRequests.size(); i2++) {
            transitionTo((State) this.mQueuedRequests.get(i2), i);
        }
        this.mQueuedRequests.clear();
        wakeLock.release("DozeMachine#requestState");
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public interface Part {
        void transitionTo(State state, State state2);

        default void destroy() {
        }

        default void dump(PrintWriter printWriter) {
        }

        default void onScreenState(int i) {
        }

        default void onUiModeTypeChanged(int i) {
        }

        default void setDozeMachine(DozeMachine dozeMachine) {
        }
    }
}
