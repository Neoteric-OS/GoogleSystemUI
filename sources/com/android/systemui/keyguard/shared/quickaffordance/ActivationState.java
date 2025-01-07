package com.android.systemui.keyguard.shared.quickaffordance;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class ActivationState {

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Active extends ActivationState {
        public static final Active INSTANCE = new Active();
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Inactive extends ActivationState {
        public static final Inactive INSTANCE = new Inactive();
        public static final Inactive INSTANCE$1 = new Inactive();
    }
}
