package com.android.wm.shell.bubbles.animation;

import android.view.View;
import androidx.dynamicanimation.animation.DynamicAnimation;
import androidx.dynamicanimation.animation.SpringAnimation;
import androidx.dynamicanimation.animation.SpringForce;
import com.android.wm.shell.bubbles.animation.PhysicsAnimationLayout;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class PhysicsAnimationLayout$PhysicsPropertyAnimator$$ExternalSyntheticLambda3 implements Runnable {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ Object f$0;

    public /* synthetic */ PhysicsAnimationLayout$PhysicsPropertyAnimator$$ExternalSyntheticLambda3(int i, Object obj) {
        this.$r8$classId = i;
        this.f$0 = obj;
    }

    @Override // java.lang.Runnable
    public final void run() {
        SpringForce springForce;
        SpringForce springForce2;
        int i = 0;
        int i2 = this.$r8$classId;
        Object obj = this.f$0;
        switch (i2) {
            case 0:
                Runnable[] runnableArr = (Runnable[]) obj;
                int length = runnableArr.length;
                while (i < length) {
                    runnableArr[i].run();
                    i++;
                }
                break;
            case 1:
                Runnable[] runnableArr2 = (Runnable[]) obj;
                int length2 = runnableArr2.length;
                while (i < length2) {
                    runnableArr2[i].run();
                    i++;
                }
                break;
            default:
                PhysicsAnimationLayout.PhysicsPropertyAnimator physicsPropertyAnimator = (PhysicsAnimationLayout.PhysicsPropertyAnimator) obj;
                DynamicAnimation.AnonymousClass1 anonymousClass1 = DynamicAnimation.TRANSLATION_X;
                View view = physicsPropertyAnimator.mView;
                float f = physicsPropertyAnimator.mCurrentPointOnPath.x;
                PhysicsAnimationLayout physicsAnimationLayout = physicsPropertyAnimator.this$0;
                if (view != null) {
                    int i3 = PhysicsAnimationLayout.$r8$clinit;
                    physicsAnimationLayout.getClass();
                    SpringAnimation springAnimation = (SpringAnimation) view.getTag(PhysicsAnimationLayout.getTagIdForProperty(anonymousClass1));
                    if (springAnimation != null && (springForce2 = springAnimation.mSpring) != null) {
                        springForce2.mFinalPosition = f;
                        springAnimation.start();
                    }
                }
                DynamicAnimation.AnonymousClass1 anonymousClass12 = DynamicAnimation.TRANSLATION_Y;
                View view2 = physicsPropertyAnimator.mView;
                float f2 = physicsPropertyAnimator.mCurrentPointOnPath.y;
                if (view2 != null) {
                    int i4 = PhysicsAnimationLayout.$r8$clinit;
                    physicsAnimationLayout.getClass();
                    SpringAnimation springAnimation2 = (SpringAnimation) view2.getTag(PhysicsAnimationLayout.getTagIdForProperty(anonymousClass12));
                    if (springAnimation2 != null && (springForce = springAnimation2.mSpring) != null) {
                        springForce.mFinalPosition = f2;
                        springAnimation2.start();
                        break;
                    }
                }
                break;
        }
    }
}
