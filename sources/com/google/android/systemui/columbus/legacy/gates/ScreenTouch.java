package com.google.android.systemui.columbus.legacy.gates;

import android.frameworks.stats.AnnotationValue$1$$ExternalSyntheticOutline0;
import android.view.InputEvent;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
import com.android.systemui.shared.system.InputChannelCompat$InputEventListener;
import com.android.systemui.shared.system.InputChannelCompat$InputEventReceiver;
import com.android.systemui.shared.system.InputMonitorCompat;
import com.google.android.systemui.columbus.legacy.gates.Gate;
import com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.internal.ContextScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class ScreenTouch extends TransientGate {
    public final ScreenTouch$gateListener$1 gateListener = new Gate.Listener() { // from class: com.google.android.systemui.columbus.legacy.gates.ScreenTouch$gateListener$1
        @Override // com.google.android.systemui.columbus.legacy.gates.Gate.Listener
        public final void onGateChanged(Gate gate) {
            ScreenTouch screenTouch = ScreenTouch.this;
            boolean isBlocking = screenTouch.powerState.isBlocking();
            ContextScope contextScope = screenTouch.coroutineScope;
            if (isBlocking) {
                BuildersKt.launch$default(contextScope, null, null, new ScreenTouch$stopListeningForTouch$1(screenTouch, null), 3);
            } else {
                BuildersKt.launch$default(contextScope, null, null, new ScreenTouch$startListeningForTouch$1(screenTouch, null), 3);
            }
        }
    };
    public final ScreenTouch$inputEventListener$1 inputEventListener = new InputChannelCompat$InputEventListener() { // from class: com.google.android.systemui.columbus.legacy.gates.ScreenTouch$inputEventListener$1
        @Override // com.android.systemui.shared.system.InputChannelCompat$InputEventListener
        public final void onInputEvent(InputEvent inputEvent) {
            long longPressTimeout;
            MotionEvent motionEvent = inputEvent instanceof MotionEvent ? (MotionEvent) inputEvent : null;
            if (motionEvent != null) {
                ScreenTouch screenTouch = ScreenTouch.this;
                screenTouch.getClass();
                int action = motionEvent.getAction() & 255;
                if (action == 0 || action == 1 || action == 5 || action == 6) {
                    screenTouch.getClass();
                    int action2 = motionEvent.getAction() & 255;
                    if (action2 != 0) {
                        if (action2 != 1) {
                            if (action2 != 5) {
                                if (action2 != 6) {
                                    throw new IllegalStateException(AnnotationValue$1$$ExternalSyntheticOutline0.m(motionEvent.getAction(), "No valid delay for MotionEvent action: "));
                                }
                            }
                        }
                        longPressTimeout = 250;
                        screenTouch.blockForMillis(longPressTimeout);
                    }
                    longPressTimeout = ViewConfiguration.getLongPressTimeout() + 500;
                    screenTouch.blockForMillis(longPressTimeout);
                }
            }
        }
    };
    public InputChannelCompat$InputEventReceiver inputEventReceiver;
    public InputMonitorCompat inputMonitor;
    public final DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider inputMonitorProvider;
    public final PowerState powerState;

    /* JADX WARN: Type inference failed for: r1v1, types: [com.google.android.systemui.columbus.legacy.gates.ScreenTouch$gateListener$1] */
    /* JADX WARN: Type inference failed for: r1v2, types: [com.google.android.systemui.columbus.legacy.gates.ScreenTouch$inputEventListener$1] */
    public ScreenTouch(PowerState powerState, DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider switchingProvider) {
        this.powerState = powerState;
        this.inputMonitorProvider = switchingProvider;
    }

    @Override // com.google.android.systemui.columbus.legacy.gates.Gate
    public final void onActivate() {
        PowerState powerState = this.powerState;
        powerState.registerListener(this.gateListener);
        if (!powerState.isBlocking()) {
            BuildersKt.launch$default(this.coroutineScope, null, null, new ScreenTouch$startListeningForTouch$1(this, null), 3);
        }
        setBlocking(false);
    }

    @Override // com.google.android.systemui.columbus.legacy.gates.Gate
    public final void onDeactivate() {
        this.powerState.unregisterListener(this.gateListener);
        BuildersKt.launch$default(this.coroutineScope, null, null, new ScreenTouch$stopListeningForTouch$1(this, null), 3);
    }
}
