package com.android.systemui.shade;

import android.view.MotionEvent;
import com.android.systemui.statusbar.policy.BrightnessMirrorController$$ExternalSyntheticLambda0;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public interface ShadeViewController {
    void cancelInputFocusTransfer();

    void finishInputFocusTransfer(float f);

    ShadeFoldAnimator getShadeFoldAnimator$1();

    ShadeHeadsUpTracker getShadeHeadsUpTracker$1();

    boolean handleExternalInterceptTouch(MotionEvent motionEvent);

    boolean handleExternalTouch(MotionEvent motionEvent);

    boolean isViewEnabled();

    void setAlpha(int i, boolean z);

    void setAlphaChangeAnimationEndAction(BrightnessMirrorController$$ExternalSyntheticLambda0 brightnessMirrorController$$ExternalSyntheticLambda0);

    void setAmbientIndicationTop(int i, boolean z);

    void setQsScrimEnabled(boolean z);

    void startExpandLatencyTracking();

    void startInputFocusTransfer();

    void updateSystemUiStateFlags();

    void updateTouchableRegion();
}
