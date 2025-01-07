package com.google.android.systemui.dreamliner;

import android.content.Intent;
import androidx.dynamicanimation.animation.DynamicAnimation;
import com.android.wm.shell.shared.animation.PhysicsAnimator;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class DockGestureController$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ DockGestureController f$0;

    public /* synthetic */ DockGestureController$$ExternalSyntheticLambda0(DockGestureController dockGestureController, int i) {
        this.$r8$classId = i;
        this.f$0 = dockGestureController;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i = this.$r8$classId;
        DockGestureController dockGestureController = this.f$0;
        switch (i) {
            case 0:
                dockGestureController.hideGear();
                break;
            case 1:
                dockGestureController.getClass();
                dockGestureController.sendProtectedBroadcast(new Intent("com.google.android.systemui.dreamliner.PHOTO_EVENT"));
                break;
            case 2:
                PhysicsAnimator physicsAnimator = dockGestureController.mPreviewTargetAnimator;
                physicsAnimator.spring(DynamicAnimation.TRANSLATION_X, 0.0f, dockGestureController.mVelocityX, dockGestureController.mTargetSpringConfig);
                physicsAnimator.start();
                break;
            case 3:
                PhysicsAnimator physicsAnimator2 = dockGestureController.mPreviewTargetAnimator;
                physicsAnimator2.spring(DynamicAnimation.TRANSLATION_X, dockGestureController.mPhotoPreview.getRight(), dockGestureController.mVelocityX, dockGestureController.mTargetSpringConfig);
                physicsAnimator2.withEndActions(new DockGestureController$$ExternalSyntheticLambda0(dockGestureController, 5));
                physicsAnimator2.start();
                break;
            case 4:
                dockGestureController.hidePhotoPreview(false);
                break;
            case 5:
                dockGestureController.mPhotoPreview.setAlpha(0.0f);
                dockGestureController.mPhotoPreview.setVisibility(4);
                break;
            default:
                dockGestureController.mSettingsGear.setVisibility(4);
                break;
        }
    }
}
