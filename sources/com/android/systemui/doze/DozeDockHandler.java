package com.android.systemui.doze;

import android.hardware.display.AmbientDisplayConfiguration;
import androidx.exifinterface.media.ExifInterface$$ExternalSyntheticOutline0;
import com.android.keyguard.KeyguardClockSwitchController$$ExternalSyntheticOutline0;
import com.android.systemui.dock.DockManager;
import com.android.systemui.doze.DozeMachine;
import com.android.systemui.settings.UserTracker;
import com.android.systemui.settings.UserTrackerImpl;
import com.google.android.systemui.dreamliner.DockObserver;
import java.io.PrintWriter;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class DozeDockHandler implements DozeMachine.Part {
    public static final boolean DEBUG = DozeService.DEBUG;
    public final AmbientDisplayConfiguration mConfig;
    public final DockManager mDockManager;
    public DozeMachine mMachine;
    public final UserTracker mUserTracker;
    public int mDockState = 0;
    public final DockEventListener mDockEventListener = new DockEventListener();

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class DockEventListener implements DockManager.DockEventListener {
        public boolean mRegistered;

        public DockEventListener() {
        }

        @Override // com.android.systemui.dock.DockManager.DockEventListener
        public final void onEvent(int i) {
            DozeMachine.State state;
            if (DozeDockHandler.DEBUG) {
                ExifInterface$$ExternalSyntheticOutline0.m("dock event = ", "DozeDockHandler", i);
            }
            DozeDockHandler dozeDockHandler = DozeDockHandler.this;
            if (dozeDockHandler.mDockState == i) {
                return;
            }
            dozeDockHandler.mDockState = i;
            if (dozeDockHandler.mMachine.isExecutingTransition() || (state = dozeDockHandler.mMachine.getState()) == DozeMachine.State.DOZE_REQUEST_PULSE || state == DozeMachine.State.DOZE_PULSING || state == DozeMachine.State.DOZE_PULSING_BRIGHT) {
                return;
            }
            int i2 = dozeDockHandler.mDockState;
            DozeMachine.State state2 = DozeMachine.State.DOZE;
            if (i2 != 0) {
                if (i2 == 1) {
                    state2 = DozeMachine.State.DOZE_AOD_DOCKED;
                } else if (i2 != 2) {
                    return;
                }
            } else if (dozeDockHandler.mConfig.alwaysOnEnabled(((UserTrackerImpl) dozeDockHandler.mUserTracker).getUserId())) {
                state2 = DozeMachine.State.DOZE_AOD;
            }
            dozeDockHandler.mMachine.requestState(state2);
        }
    }

    public DozeDockHandler(AmbientDisplayConfiguration ambientDisplayConfiguration, DockManager dockManager, UserTracker userTracker) {
        this.mConfig = ambientDisplayConfiguration;
        this.mDockManager = dockManager;
        this.mUserTracker = userTracker;
    }

    @Override // com.android.systemui.doze.DozeMachine.Part
    public final void dump(PrintWriter printWriter) {
        StringBuilder m = KeyguardClockSwitchController$$ExternalSyntheticOutline0.m(printWriter, "DozeDockHandler:", " dockState=");
        m.append(this.mDockState);
        printWriter.println(m.toString());
    }

    @Override // com.android.systemui.doze.DozeMachine.Part
    public final void setDozeMachine(DozeMachine dozeMachine) {
        this.mMachine = dozeMachine;
    }

    @Override // com.android.systemui.doze.DozeMachine.Part
    public final void transitionTo(DozeMachine.State state, DozeMachine.State state2) {
        int ordinal = state2.ordinal();
        DockEventListener dockEventListener = this.mDockEventListener;
        DozeDockHandler dozeDockHandler = DozeDockHandler.this;
        if (ordinal == 1) {
            if (dockEventListener.mRegistered) {
                return;
            }
            DockManager dockManager = dozeDockHandler.mDockManager;
            if (dockManager != null) {
                ((DockObserver) dockManager).addListener(dockEventListener);
            }
            dockEventListener.mRegistered = true;
            return;
        }
        if (ordinal == 9 && dockEventListener.mRegistered) {
            DockManager dockManager2 = dozeDockHandler.mDockManager;
            if (dockManager2 != null) {
                ((DockObserver) dockManager2).removeListener(dockEventListener);
            }
            dockEventListener.mRegistered = false;
        }
    }
}
