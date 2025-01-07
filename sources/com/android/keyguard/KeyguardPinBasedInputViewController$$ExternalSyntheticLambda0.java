package com.android.keyguard;

import android.view.MotionEvent;
import android.view.View;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final /* synthetic */ class KeyguardPinBasedInputViewController$$ExternalSyntheticLambda0 implements View.OnTouchListener {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ KeyguardPinBasedInputViewController f$0;

    public /* synthetic */ KeyguardPinBasedInputViewController$$ExternalSyntheticLambda0(KeyguardPinBasedInputViewController keyguardPinBasedInputViewController, int i) {
        this.$r8$classId = i;
        this.f$0 = keyguardPinBasedInputViewController;
    }

    @Override // android.view.View.OnTouchListener
    public final boolean onTouch(View view, MotionEvent motionEvent) {
        int i = this.$r8$classId;
        KeyguardPinBasedInputViewController keyguardPinBasedInputViewController = this.f$0;
        keyguardPinBasedInputViewController.getClass();
        switch (i) {
            case 0:
                if (motionEvent.getActionMasked() == 0) {
                    keyguardPinBasedInputViewController.mFalsingCollector.avoidGesture();
                    break;
                }
                break;
            default:
                if (motionEvent.getActionMasked() == 0) {
                    ((KeyguardPinBasedInputView) keyguardPinBasedInputViewController.mView).performHapticFeedback(1, 1);
                    break;
                }
                break;
        }
        return false;
    }
}
