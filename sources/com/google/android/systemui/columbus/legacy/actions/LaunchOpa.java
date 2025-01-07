package com.google.android.systemui.columbus.legacy.actions;

import android.app.KeyguardManager;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import com.android.internal.logging.UiEventLogger;
import com.android.keyguard.logging.BiometricUnlockLogger$logCalculateModeForFingerprintUnlockingAllowed$2$$ExternalSyntheticOutline0;
import com.android.systemui.settings.UserTrackerImpl;
import com.android.systemui.shade.ShadeController;
import com.android.systemui.tuner.TunerService;
import com.google.android.systemui.assist.AssistManagerGoogle;
import com.google.android.systemui.assist.OpaEnabledListener;
import com.google.android.systemui.assist.OpaEnabledReceiver;
import com.google.android.systemui.columbus.ColumbusEvent;
import com.google.android.systemui.columbus.legacy.ColumbusContentObserver;
import com.google.android.systemui.columbus.legacy.sensors.GestureSensor;
import dagger.Lazy;
import java.util.Set;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class LaunchOpa extends UserAction {
    public final AssistManagerGoogle assistManager;
    public boolean enableForAnyAssistant;
    public boolean isGestureEnabled;
    public boolean isOpaEnabled;
    public final Lazy keyguardManager;
    public final LaunchOpa$opaEnabledListener$1 opaEnabledListener;
    public final ShadeController shadeController;
    public final String tag;
    public final LaunchOpa$tunable$1 tunable;
    public final UiEventLogger uiEventLogger;

    public LaunchOpa(Context context, ShadeController shadeController, Set set, AssistManagerGoogle assistManagerGoogle, Lazy lazy, TunerService tunerService, ColumbusContentObserver.Factory factory, UiEventLogger uiEventLogger) {
        super(context, set);
        this.shadeController = shadeController;
        this.keyguardManager = lazy;
        this.uiEventLogger = uiEventLogger;
        this.tag = "Columbus/LaunchOpa";
        this.assistManager = assistManagerGoogle;
        OpaEnabledListener opaEnabledListener = new OpaEnabledListener() { // from class: com.google.android.systemui.columbus.legacy.actions.LaunchOpa$opaEnabledListener$1
            @Override // com.google.android.systemui.assist.OpaEnabledListener
            public final void onOpaEnabledReceived(Context context2, boolean z, boolean z2, boolean z3, boolean z4) {
                LaunchOpa launchOpa = LaunchOpa.this;
                boolean z5 = z2 || launchOpa.enableForAnyAssistant;
                StringBuilder m = BiometricUnlockLogger$logCalculateModeForFingerprintUnlockingAllowed$2$$ExternalSyntheticOutline0.m("eligible: ", ", supported: ", ", opa: ", z, z5);
                m.append(z3);
                Log.i("Columbus/LaunchOpa", m.toString());
                launchOpa.isOpaEnabled = z && z5 && z3;
                launchOpa.updateAvailable$5();
            }
        };
        Uri uriFor = Settings.Secure.getUriFor("assist_gesture_enabled");
        Function1 function1 = new Function1() { // from class: com.google.android.systemui.columbus.legacy.actions.LaunchOpa$settingsObserver$1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                LaunchOpa launchOpa = LaunchOpa.this;
                launchOpa.isGestureEnabled = Settings.Secure.getIntForUser(launchOpa.context.getContentResolver(), "assist_gesture_enabled", 1, -2) != 0;
                launchOpa.updateAvailable$5();
                return Unit.INSTANCE;
            }
        };
        factory.getClass();
        ColumbusContentObserver columbusContentObserver = new ColumbusContentObserver(factory.contentResolver, uriFor, function1, factory.userTracker, factory.executor, factory.handler);
        TunerService.Tunable tunable = new TunerService.Tunable() { // from class: com.google.android.systemui.columbus.legacy.actions.LaunchOpa$tunable$1
            @Override // com.android.systemui.tuner.TunerService.Tunable
            public final void onTuningChanged(String str, String str2) {
                if ("assist_gesture_any_assistant".equals(str)) {
                    boolean equals = "1".equals(str2);
                    LaunchOpa launchOpa = LaunchOpa.this;
                    launchOpa.enableForAnyAssistant = equals;
                    AssistManagerGoogle assistManagerGoogle2 = launchOpa.assistManager;
                    if (assistManagerGoogle2 != null) {
                        OpaEnabledReceiver opaEnabledReceiver = assistManagerGoogle2.mOpaEnabledReceiver;
                        opaEnabledReceiver.dispatchOpaEnabledState(opaEnabledReceiver.mContext);
                    }
                }
            }
        };
        this.isGestureEnabled = Settings.Secure.getIntForUser(context.getContentResolver(), "assist_gesture_enabled", 1, -2) != 0;
        this.enableForAnyAssistant = Settings.Secure.getInt(context.getContentResolver(), "assist_gesture_any_assistant", 0) == 1;
        ((UserTrackerImpl) columbusContentObserver.userTracker).addCallback(columbusContentObserver.userTrackerCallback, columbusContentObserver.executor);
        columbusContentObserver.contentResolver.contentResolver.unregisterContentObserver(columbusContentObserver);
        columbusContentObserver.contentResolver.contentResolver.registerContentObserver(columbusContentObserver.settingsUri, false, columbusContentObserver, ((UserTrackerImpl) columbusContentObserver.userTracker).getUserId());
        tunerService.addTunable(tunable, "assist_gesture_any_assistant");
        assistManagerGoogle.addOpaEnabledListener(opaEnabledListener);
        updateAvailable$5();
    }

    @Override // com.google.android.systemui.columbus.legacy.actions.UserAction
    public final boolean availableOnLockscreen() {
        return true;
    }

    @Override // com.google.android.systemui.columbus.legacy.actions.Action
    public final String getTag$vendor__unbundled_google__packages__SystemUIGoogle__android_common__sysuig() {
        return this.tag;
    }

    @Override // com.google.android.systemui.columbus.legacy.actions.Action
    public final void onTrigger(GestureSensor.DetectionProperties detectionProperties) {
        this.uiEventLogger.log(ColumbusEvent.COLUMBUS_INVOKED_ASSISTANT);
        this.shadeController.cancelExpansionAndCollapseShade();
        long j = detectionProperties != null ? detectionProperties.actionId : 0L;
        Bundle bundle = new Bundle();
        bundle.putInt("triggered_by", ((KeyguardManager) this.keyguardManager.get()).isKeyguardLocked() ? 120 : 119);
        bundle.putLong("latency_id", j);
        bundle.putInt("invocation_type", 2);
        AssistManagerGoogle assistManagerGoogle = this.assistManager;
        if (assistManagerGoogle != null) {
            assistManagerGoogle.startAssist(bundle);
        }
    }

    @Override // com.google.android.systemui.columbus.legacy.actions.Action
    public final String toString() {
        return super.toString() + " [isGestureEnabled -> " + this.isGestureEnabled + "; isOpaEnabled -> " + this.isOpaEnabled + "]";
    }

    public final void updateAvailable$5() {
        setAvailable(this.isGestureEnabled && this.isOpaEnabled);
    }
}
