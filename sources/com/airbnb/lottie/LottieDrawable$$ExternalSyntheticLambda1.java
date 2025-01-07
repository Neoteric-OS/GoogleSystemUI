package com.airbnb.lottie;

import com.airbnb.lottie.LottieDrawable;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final /* synthetic */ class LottieDrawable$$ExternalSyntheticLambda1 implements LottieDrawable.LazyCompositionTask {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ LottieDrawable f$0;

    public /* synthetic */ LottieDrawable$$ExternalSyntheticLambda1(LottieDrawable lottieDrawable, int i) {
        this.$r8$classId = i;
        this.f$0 = lottieDrawable;
    }

    @Override // com.airbnb.lottie.LottieDrawable.LazyCompositionTask
    public final void run() {
        switch (this.$r8$classId) {
            case 0:
                this.f$0.resumeAnimation();
                break;
            default:
                this.f$0.playAnimation();
                break;
        }
    }
}
