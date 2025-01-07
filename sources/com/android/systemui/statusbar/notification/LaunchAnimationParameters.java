package com.android.systemui.statusbar.notification;

import com.android.systemui.animation.TransitionAnimator;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class LaunchAnimationParameters extends TransitionAnimator.State {
    public float linearProgress;
    public int notificationParentTop;
    public int parentStartClipTopAmount;
    public int parentStartRoundedTopClipping;
    public float progress;
    public int startClipTopAmount;
    public int startNotificationTop;
    public int startRoundedTopClipping;
    public float startTranslationZ;

    public LaunchAnimationParameters() {
        super(0, 0, 0, 0, 0.0f, 0.0f);
    }
}
