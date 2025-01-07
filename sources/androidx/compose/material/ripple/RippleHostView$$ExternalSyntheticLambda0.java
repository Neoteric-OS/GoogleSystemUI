package androidx.compose.material.ripple;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final /* synthetic */ class RippleHostView$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ RippleHostView f$0;

    @Override // java.lang.Runnable
    public final void run() {
        RippleHostView rippleHostView = this.f$0;
        UnprojectedRipple unprojectedRipple = rippleHostView.ripple;
        if (unprojectedRipple != null) {
            unprojectedRipple.setState(RippleHostView.RestingState);
        }
        rippleHostView.resetRippleRunnable = null;
    }
}
