package com.android.keyguard;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final /* synthetic */ class KeyguardPatternView$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ KeyguardPatternView f$0;

    public /* synthetic */ KeyguardPatternView$$ExternalSyntheticLambda0(KeyguardPatternView keyguardPatternView, int i) {
        this.$r8$classId = i;
        this.f$0 = keyguardPatternView;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i = 1;
        int i2 = this.$r8$classId;
        KeyguardPatternView keyguardPatternView = this.f$0;
        switch (i2) {
            case 0:
                int i3 = KeyguardPatternView.$r8$clinit;
                keyguardPatternView.setAlpha(1.0f);
                keyguardPatternView.mAppearAnimationUtils.startAnimation2d(keyguardPatternView.mLockPatternView.getCellStates(), new KeyguardPatternView$$ExternalSyntheticLambda0(keyguardPatternView, i), keyguardPatternView);
                break;
            default:
                int i4 = KeyguardPatternView.$r8$clinit;
                keyguardPatternView.enableClipping(true);
                keyguardPatternView.mLockPatternView.invalidate();
                break;
        }
    }
}
