package com.airbnb.lottie;

import com.airbnb.lottie.LottieDrawable;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final /* synthetic */ class LottieDrawable$$ExternalSyntheticLambda10 implements LottieDrawable.LazyCompositionTask {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ LottieDrawable f$0;
    public final /* synthetic */ int f$1;

    public /* synthetic */ LottieDrawable$$ExternalSyntheticLambda10(LottieDrawable lottieDrawable, int i, int i2) {
        this.$r8$classId = i2;
        this.f$0 = lottieDrawable;
        this.f$1 = i;
    }

    @Override // com.airbnb.lottie.LottieDrawable.LazyCompositionTask
    public final void run() {
        switch (this.$r8$classId) {
            case 0:
                this.f$0.setFrame(this.f$1);
                break;
            case 1:
                this.f$0.setMaxFrame(this.f$1);
                break;
            default:
                this.f$0.setMinFrame(this.f$1);
                break;
        }
    }
}
