package com.android.systemui.animation;

import android.util.Log;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class ActivityTransitionAnimator$AnimationDelegate$onLongTimeout$1 implements Runnable {
    public static final ActivityTransitionAnimator$AnimationDelegate$onLongTimeout$1 INSTANCE = new ActivityTransitionAnimator$AnimationDelegate$onLongTimeout$1();

    @Override // java.lang.Runnable
    public final void run() {
        Log.wtf("ActivityTransitionAnimator", "The remote animation was neither cancelled or started within 5000");
    }
}
