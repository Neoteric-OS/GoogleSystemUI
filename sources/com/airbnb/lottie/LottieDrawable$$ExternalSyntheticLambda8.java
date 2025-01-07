package com.airbnb.lottie;

import com.airbnb.lottie.LottieDrawable;
import com.airbnb.lottie.utils.LottieValueAnimator;
import com.airbnb.lottie.utils.MiscUtils;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final /* synthetic */ class LottieDrawable$$ExternalSyntheticLambda8 implements LottieDrawable.LazyCompositionTask {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ LottieDrawable f$0;
    public final /* synthetic */ float f$1;

    public /* synthetic */ LottieDrawable$$ExternalSyntheticLambda8(LottieDrawable lottieDrawable, float f, int i) {
        this.$r8$classId = i;
        this.f$0 = lottieDrawable;
        this.f$1 = f;
    }

    @Override // com.airbnb.lottie.LottieDrawable.LazyCompositionTask
    public final void run() {
        switch (this.$r8$classId) {
            case 0:
                LottieDrawable lottieDrawable = this.f$0;
                LottieComposition lottieComposition = lottieDrawable.composition;
                float f = this.f$1;
                if (lottieComposition != null) {
                    lottieDrawable.setMinFrame((int) MiscUtils.lerp(lottieComposition.startFrame, lottieComposition.endFrame, f));
                    break;
                } else {
                    lottieDrawable.lazyCompositionTasks.add(new LottieDrawable$$ExternalSyntheticLambda8(lottieDrawable, f, 0));
                    break;
                }
            case 1:
                LottieDrawable lottieDrawable2 = this.f$0;
                LottieComposition lottieComposition2 = lottieDrawable2.composition;
                float f2 = this.f$1;
                if (lottieComposition2 != null) {
                    LottieValueAnimator lottieValueAnimator = lottieDrawable2.animator;
                    lottieValueAnimator.setMinAndMaxFrames(lottieValueAnimator.minFrame, MiscUtils.lerp(lottieComposition2.startFrame, lottieComposition2.endFrame, f2));
                    break;
                } else {
                    lottieDrawable2.lazyCompositionTasks.add(new LottieDrawable$$ExternalSyntheticLambda8(lottieDrawable2, f2, 1));
                    break;
                }
            default:
                this.f$0.setProgress(this.f$1);
                break;
        }
    }
}
