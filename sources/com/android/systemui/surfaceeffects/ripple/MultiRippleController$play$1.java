package com.android.systemui.surfaceeffects.ripple;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class MultiRippleController$play$1 implements Runnable {
    public final /* synthetic */ RippleAnimation $rippleAnimation;
    public final /* synthetic */ MultiRippleController this$0;

    public MultiRippleController$play$1(MultiRippleController multiRippleController, RippleAnimation rippleAnimation) {
        this.this$0 = multiRippleController;
        this.$rippleAnimation = rippleAnimation;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.this$0.multipleRippleView.ripples.remove(this.$rippleAnimation);
    }
}
