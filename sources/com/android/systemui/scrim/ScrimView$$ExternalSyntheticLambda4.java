package com.android.systemui.scrim;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class ScrimView$$ExternalSyntheticLambda4 implements Runnable {
    public final /* synthetic */ ScrimView f$0;
    public final /* synthetic */ int f$1;

    public /* synthetic */ ScrimView$$ExternalSyntheticLambda4(ScrimView scrimView, int i) {
        this.f$0 = scrimView;
        this.f$1 = i;
    }

    @Override // java.lang.Runnable
    public final void run() {
        ScrimView scrimView = this.f$0;
        int i = this.f$1;
        if (scrimView.mTintColor == i) {
            return;
        }
        scrimView.mTintColor = i;
        scrimView.updateColorWithTint(false);
    }
}
