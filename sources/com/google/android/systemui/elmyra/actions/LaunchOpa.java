package com.google.android.systemui.elmyra.actions;

import android.app.KeyguardManager;
import android.content.Context;
import android.os.Bundle;
import android.provider.Settings;
import androidx.appcompat.app.AppCompatDelegateImpl$$ExternalSyntheticOutline0;
import com.android.systemui.shade.ShadeController;
import com.android.systemui.tuner.TunerService;
import com.google.android.systemui.assist.AssistManagerGoogle;
import com.google.android.systemui.assist.OpaEnabledListener;
import com.google.android.systemui.assist.OpaEnabledReceiver;
import com.google.android.systemui.elmyra.UserContentObserver;
import com.google.android.systemui.elmyra.feedback.AssistInvocationEffect;
import com.google.android.systemui.elmyra.sensors.GestureSensor;
import java.util.Collections;
import java.util.concurrent.Executor;
import java.util.function.Consumer;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class LaunchOpa extends Action implements TunerService.Tunable {
    public final AssistManagerGoogle mAssistManager;
    public final Context mContext;
    public boolean mEnableForAnyAssistant;
    public boolean mIsGestureEnabled;
    public boolean mIsOpaEnabled;
    public final KeyguardManager mKeyguardManager;
    public final AnonymousClass1 mOpaEnabledListener;
    public final ShadeController mShadeController;

    public LaunchOpa(Context context, Executor executor, ShadeController shadeController, AssistManagerGoogle assistManagerGoogle, KeyguardManager keyguardManager, TunerService tunerService, AssistInvocationEffect assistInvocationEffect) {
        super(executor, Collections.singletonList(assistInvocationEffect));
        OpaEnabledListener opaEnabledListener = new OpaEnabledListener() { // from class: com.google.android.systemui.elmyra.actions.LaunchOpa.1
            @Override // com.google.android.systemui.assist.OpaEnabledListener
            public final void onOpaEnabledReceived(Context context2, boolean z, boolean z2, boolean z3, boolean z4) {
                LaunchOpa launchOpa = LaunchOpa.this;
                boolean z5 = z && (z2 || launchOpa.mEnableForAnyAssistant) && z3;
                if (launchOpa.mIsOpaEnabled != z5) {
                    launchOpa.mIsOpaEnabled = z5;
                    launchOpa.notifyListener();
                }
            }
        };
        this.mContext = context;
        this.mShadeController = shadeController;
        this.mAssistManager = assistManagerGoogle;
        this.mKeyguardManager = keyguardManager;
        this.mIsGestureEnabled = Settings.Secure.getIntForUser(context.getContentResolver(), "assist_gesture_enabled", 1, -2) != 0;
        new UserContentObserver(context, Settings.Secure.getUriFor("assist_gesture_enabled"), new Consumer() { // from class: com.google.android.systemui.elmyra.actions.LaunchOpa$$ExternalSyntheticLambda0
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                LaunchOpa launchOpa = LaunchOpa.this;
                boolean z = Settings.Secure.getIntForUser(launchOpa.mContext.getContentResolver(), "assist_gesture_enabled", 1, -2) != 0;
                if (launchOpa.mIsGestureEnabled != z) {
                    launchOpa.mIsGestureEnabled = z;
                    launchOpa.notifyListener();
                }
            }
        }, true);
        tunerService.addTunable(this, "assist_gesture_any_assistant");
        this.mEnableForAnyAssistant = Settings.Secure.getInt(context.getContentResolver(), "assist_gesture_any_assistant", 0) == 1;
        assistManagerGoogle.addOpaEnabledListener(opaEnabledListener);
    }

    @Override // com.google.android.systemui.elmyra.actions.Action
    public final boolean isAvailable() {
        return this.mIsGestureEnabled && this.mIsOpaEnabled;
    }

    public final void launchOpa(long j) {
        Bundle bundle = new Bundle();
        bundle.putInt("triggered_by", this.mKeyguardManager.isKeyguardLocked() ? 14 : 13);
        bundle.putLong("latency_id", j);
        bundle.putInt("invocation_type", 2);
        this.mAssistManager.startAssist(bundle);
    }

    @Override // com.google.android.systemui.elmyra.actions.Action
    public final void onProgress(int i, float f) {
        updateFeedbackEffects(i, f);
    }

    @Override // com.google.android.systemui.elmyra.actions.Action
    public final void onTrigger(GestureSensor.DetectionProperties detectionProperties) {
        this.mShadeController.cancelExpansionAndCollapseShade();
        triggerFeedbackEffects(detectionProperties);
        launchOpa(detectionProperties != null ? detectionProperties.mActionId : 0L);
    }

    @Override // com.android.systemui.tuner.TunerService.Tunable
    public final void onTuningChanged(String str, String str2) {
        if ("assist_gesture_any_assistant".equals(str)) {
            this.mEnableForAnyAssistant = "1".equals(str2);
            OpaEnabledReceiver opaEnabledReceiver = this.mAssistManager.mOpaEnabledReceiver;
            opaEnabledReceiver.dispatchOpaEnabledState(opaEnabledReceiver.mContext);
        }
    }

    @Override // com.google.android.systemui.elmyra.actions.Action
    public final String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        sb.append(" [mIsGestureEnabled -> ");
        sb.append(this.mIsGestureEnabled);
        sb.append("; mIsOpaEnabled -> ");
        return AppCompatDelegateImpl$$ExternalSyntheticOutline0.m(sb, this.mIsOpaEnabled, "]");
    }
}
