package com.android.wm.shell.shared.animation;

import com.android.wm.shell.shared.animation.PhysicsAnimator;
import java.util.WeakHashMap;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class PhysicsAnimatorKt {
    public static final WeakHashMap animators = new WeakHashMap();
    public static final PhysicsAnimator.SpringConfig globalDefaultSpring = new PhysicsAnimator.SpringConfig(1500.0f, 0.5f);
}
