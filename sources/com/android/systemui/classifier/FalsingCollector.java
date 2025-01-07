package com.android.systemui.classifier;

import android.view.KeyEvent;
import android.view.MotionEvent;
import com.android.systemui.classifier.FalsingClassifier;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public interface FalsingCollector {
    void avoidGesture();

    void init();

    void onA11yAction();

    void onBouncerHidden();

    void onBouncerShown();

    void onKeyEvent(KeyEvent keyEvent);

    void onMotionEventComplete();

    void onScreenOff();

    void onScreenOnFromTouch();

    void onScreenTurningOn();

    void onSuccessfulUnlock();

    void onTouchEvent(MotionEvent motionEvent);

    void setShowingAod(boolean z);

    void updateFalseConfidence(FalsingClassifier.Result result);
}
