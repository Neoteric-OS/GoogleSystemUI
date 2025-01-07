package com.google.android.systemui.columbus.legacy.actions;

import android.content.Context;
import android.os.PowerManager;
import android.util.Log;
import com.android.systemui.keyguard.WakefulnessLifecycle;
import com.android.systemui.statusbar.policy.KeyguardStateController;
import com.android.systemui.statusbar.policy.KeyguardStateControllerImpl;
import com.google.android.systemui.columbus.legacy.ColumbusSettings;
import com.google.android.systemui.columbus.legacy.PowerManagerWrapper;
import com.google.android.systemui.columbus.legacy.actions.Action;
import com.google.android.systemui.columbus.legacy.sensors.GestureSensor;
import java.util.Iterator;
import java.util.Map;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class UserSelectedAction extends Action {
    public UserAction currentAction;
    public final UserSelectedAction$keyguardMonitorCallback$1 keyguardMonitorCallback;
    public final KeyguardStateController keyguardStateController;
    public final PowerManagerWrapper powerManager;
    public final TakeScreenshot takeScreenshot;
    public final Map userSelectedActions;
    public final UserSelectedAction$wakefulnessLifecycleObserver$1 wakefulnessLifecycleObserver;

    /* JADX WARN: Type inference failed for: r6v1, types: [com.google.android.systemui.columbus.legacy.actions.UserSelectedAction$keyguardMonitorCallback$1] */
    /* JADX WARN: Type inference failed for: r6v2, types: [com.google.android.systemui.columbus.legacy.actions.UserSelectedAction$wakefulnessLifecycleObserver$1] */
    public UserSelectedAction(Context context, ColumbusSettings columbusSettings, Map map, TakeScreenshot takeScreenshot, KeyguardStateController keyguardStateController, PowerManagerWrapper powerManagerWrapper, WakefulnessLifecycle wakefulnessLifecycle) {
        super(context, null);
        this.userSelectedActions = map;
        this.takeScreenshot = takeScreenshot;
        this.keyguardStateController = keyguardStateController;
        this.powerManager = powerManagerWrapper;
        ColumbusSettings.ColumbusSettingsChangeListener columbusSettingsChangeListener = new ColumbusSettings.ColumbusSettingsChangeListener() { // from class: com.google.android.systemui.columbus.legacy.actions.UserSelectedAction$settingsChangeListener$1
            @Override // com.google.android.systemui.columbus.legacy.ColumbusSettings.ColumbusSettingsChangeListener
            public final void onSelectedActionChange(String str) {
                UserSelectedAction userSelectedAction = UserSelectedAction.this;
                UserAction userAction = (UserAction) userSelectedAction.userSelectedActions.getOrDefault(str, userSelectedAction.takeScreenshot);
                if (Intrinsics.areEqual(userAction, userSelectedAction.currentAction)) {
                    return;
                }
                userSelectedAction.currentAction.updateFeedbackEffects(0, null);
                userSelectedAction.currentAction = userAction;
                Log.i("Columbus/SelectedAction", "User Action selected: " + userAction);
                userSelectedAction.updateAvailable$7();
            }
        };
        this.keyguardMonitorCallback = new KeyguardStateController.Callback() { // from class: com.google.android.systemui.columbus.legacy.actions.UserSelectedAction$keyguardMonitorCallback$1
            @Override // com.android.systemui.statusbar.policy.KeyguardStateController.Callback
            public final void onKeyguardShowingChanged() {
                UserSelectedAction.this.updateAvailable$7();
            }
        };
        this.wakefulnessLifecycleObserver = new WakefulnessLifecycle.Observer() { // from class: com.google.android.systemui.columbus.legacy.actions.UserSelectedAction$wakefulnessLifecycleObserver$1
            @Override // com.android.systemui.keyguard.WakefulnessLifecycle.Observer
            public final void onFinishedGoingToSleep$1() {
                UserSelectedAction.this.updateAvailable$7();
            }

            @Override // com.android.systemui.keyguard.WakefulnessLifecycle.Observer
            public final void onStartedWakingUp() {
                UserSelectedAction.this.updateAvailable$7();
            }
        };
        UserAction userAction = (UserAction) map.getOrDefault(columbusSettings.selectedAction(), takeScreenshot);
        this.currentAction = userAction;
        Log.i("Columbus/SelectedAction", "User Action selected: " + userAction);
        columbusSettings.registerColumbusSettingsChangeListener(columbusSettingsChangeListener);
        Action.Listener listener = new Action.Listener() { // from class: com.google.android.systemui.columbus.legacy.actions.UserSelectedAction$sublistener$1
            @Override // com.google.android.systemui.columbus.legacy.actions.Action.Listener
            public final void onActionAvailabilityChanged(Action action) {
                UserSelectedAction userSelectedAction = UserSelectedAction.this;
                if (Intrinsics.areEqual(userSelectedAction.currentAction, action)) {
                    userSelectedAction.updateAvailable$7();
                }
            }
        };
        Iterator it = map.values().iterator();
        while (it.hasNext()) {
            ((UserAction) it.next()).listeners.add(listener);
        }
        ((KeyguardStateControllerImpl) this.keyguardStateController).addCallback(this.keyguardMonitorCallback);
        wakefulnessLifecycle.addObserver(this.wakefulnessLifecycleObserver);
        updateAvailable$7();
    }

    @Override // com.google.android.systemui.columbus.legacy.actions.Action
    public final String getTag$vendor__unbundled_google__packages__SystemUIGoogle__android_common__sysuig() {
        return this.currentAction.getTag$vendor__unbundled_google__packages__SystemUIGoogle__android_common__sysuig();
    }

    @Override // com.google.android.systemui.columbus.legacy.actions.Action
    public final void onGestureDetected(int i, GestureSensor.DetectionProperties detectionProperties) {
        this.currentAction.onGestureDetected(i, detectionProperties);
    }

    @Override // com.google.android.systemui.columbus.legacy.actions.Action
    public final void onTrigger(GestureSensor.DetectionProperties detectionProperties) {
        this.currentAction.onTrigger(detectionProperties);
    }

    @Override // com.google.android.systemui.columbus.legacy.actions.Action
    public final String toString() {
        return super.toString() + " [currentAction -> " + this.currentAction + "]";
    }

    public final void updateAvailable$7() {
        UserAction userAction = this.currentAction;
        if (!userAction.isAvailable) {
            setAvailable(false);
            return;
        }
        if (!userAction.availableOnScreenOff()) {
            PowerManager powerManager = this.powerManager.powerManager;
            if (!Intrinsics.areEqual(powerManager != null ? Boolean.valueOf(powerManager.isInteractive()) : null, Boolean.TRUE)) {
                setAvailable(false);
                return;
            }
        }
        if (this.currentAction.availableOnLockscreen() || !((KeyguardStateControllerImpl) this.keyguardStateController).mShowing) {
            setAvailable(true);
        } else {
            setAvailable(false);
        }
    }

    @Override // com.google.android.systemui.columbus.legacy.actions.Action
    public final void updateFeedbackEffects(int i, GestureSensor.DetectionProperties detectionProperties) {
        this.currentAction.updateFeedbackEffects(i, detectionProperties);
    }
}
