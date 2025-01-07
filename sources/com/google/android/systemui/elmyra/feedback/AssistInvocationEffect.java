package com.google.android.systemui.elmyra.feedback;

import com.android.systemui.shared.system.QuickStepContract;
import com.google.android.systemui.assist.AssistManagerGoogle;
import com.google.android.systemui.elmyra.sensors.GestureSensor;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class AssistInvocationEffect implements FeedbackEffect {
    public final AssistManagerGoogle mAssistManager;
    public final OpaHomeButton mOpaHomeButton;
    public final OpaLockscreen mOpaLockscreen;

    public AssistInvocationEffect(AssistManagerGoogle assistManagerGoogle, OpaHomeButton opaHomeButton, OpaLockscreen opaLockscreen) {
        this.mAssistManager = assistManagerGoogle;
        this.mOpaHomeButton = opaHomeButton;
    }

    @Override // com.google.android.systemui.elmyra.feedback.FeedbackEffect
    public final void onProgress(int i, float f) {
        AssistManagerGoogle assistManagerGoogle = this.mAssistManager;
        if (QuickStepContract.isGesturalMode(assistManagerGoogle.mNavigationMode)) {
            assistManagerGoogle.onInvocationProgress(2, f);
        } else {
            this.mOpaHomeButton.onProgress(i, f);
        }
    }

    @Override // com.google.android.systemui.elmyra.feedback.FeedbackEffect
    public final void onRelease() {
        AssistManagerGoogle assistManagerGoogle = this.mAssistManager;
        if (QuickStepContract.isGesturalMode(assistManagerGoogle.mNavigationMode)) {
            assistManagerGoogle.onInvocationProgress(2, 0.0f);
        } else {
            this.mOpaHomeButton.onRelease();
        }
    }

    @Override // com.google.android.systemui.elmyra.feedback.FeedbackEffect
    public final void onResolve(GestureSensor.DetectionProperties detectionProperties) {
        AssistManagerGoogle assistManagerGoogle = this.mAssistManager;
        if (QuickStepContract.isGesturalMode(assistManagerGoogle.mNavigationMode)) {
            assistManagerGoogle.onInvocationProgress(2, 1.0f);
        } else {
            this.mOpaHomeButton.onResolve(detectionProperties);
        }
    }
}
