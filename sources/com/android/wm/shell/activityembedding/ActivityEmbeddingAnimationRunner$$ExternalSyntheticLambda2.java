package com.android.wm.shell.activityembedding;

import android.R;
import android.graphics.Rect;
import android.view.animation.Animation;
import android.window.TransitionInfo;
import com.android.wm.shell.shared.TransitionUtil;
import com.android.wm.shell.transition.TransitionAnimationHelper;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class ActivityEmbeddingAnimationRunner$$ExternalSyntheticLambda2 {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ ActivityEmbeddingAnimationSpec f$0;

    public /* synthetic */ ActivityEmbeddingAnimationRunner$$ExternalSyntheticLambda2(ActivityEmbeddingAnimationSpec activityEmbeddingAnimationSpec, int i) {
        this.$r8$classId = i;
        this.f$0 = activityEmbeddingAnimationSpec;
    }

    public final Animation get(TransitionInfo transitionInfo, TransitionInfo.Change change, Rect rect) {
        int i = this.$r8$classId;
        ActivityEmbeddingAnimationSpec activityEmbeddingAnimationSpec = this.f$0;
        activityEmbeddingAnimationSpec.getClass();
        switch (i) {
            case 0:
                boolean isOpeningType = TransitionUtil.isOpeningType(change.getMode());
                Animation loadCustomAnimationFromOptions = activityEmbeddingAnimationSpec.loadCustomAnimationFromOptions(change.getAnimationOptions(), change.getMode());
                if (loadCustomAnimationFromOptions == null) {
                    Animation loadAttributeAnimation = TransitionAnimationHelper.loadAttributeAnimation(TransitionAnimationHelper.getTransitionTypeFromInfo(transitionInfo), transitionInfo, change, 0, activityEmbeddingAnimationSpec.mTransitionAnimation, false);
                    if (loadAttributeAnimation == null || !loadAttributeAnimation.getShowBackdrop()) {
                        loadCustomAnimationFromOptions = activityEmbeddingAnimationSpec.mTransitionAnimation.loadDefaultAnimationRes(isOpeningType ? R.anim.activity_open_enter : R.anim.activity_open_exit);
                    } else {
                        loadCustomAnimationFromOptions = activityEmbeddingAnimationSpec.mTransitionAnimation.loadDefaultAnimationRes(isOpeningType ? R.anim.toast_enter : R.anim.toast_exit);
                    }
                }
                loadCustomAnimationFromOptions.initialize(rect.width(), rect.height(), rect.width(), rect.height());
                loadCustomAnimationFromOptions.scaleCurrentDuration(activityEmbeddingAnimationSpec.mTransitionAnimationScaleSetting);
                return loadCustomAnimationFromOptions;
            default:
                boolean isOpeningType2 = TransitionUtil.isOpeningType(change.getMode());
                Animation loadCustomAnimationFromOptions2 = activityEmbeddingAnimationSpec.loadCustomAnimationFromOptions(change.getAnimationOptions(), change.getMode());
                if (loadCustomAnimationFromOptions2 == null) {
                    Animation loadAttributeAnimation2 = TransitionAnimationHelper.loadAttributeAnimation(TransitionAnimationHelper.getTransitionTypeFromInfo(transitionInfo), transitionInfo, change, 0, activityEmbeddingAnimationSpec.mTransitionAnimation, false);
                    if (loadAttributeAnimation2 == null || !loadAttributeAnimation2.getShowBackdrop()) {
                        loadCustomAnimationFromOptions2 = activityEmbeddingAnimationSpec.mTransitionAnimation.loadDefaultAnimationRes(isOpeningType2 ? R.anim.activity_close_enter : R.anim.activity_close_exit);
                    } else {
                        loadCustomAnimationFromOptions2 = activityEmbeddingAnimationSpec.mTransitionAnimation.loadDefaultAnimationRes(isOpeningType2 ? R.anim.task_open_enter_cross_profile_apps : R.anim.task_open_exit);
                    }
                }
                loadCustomAnimationFromOptions2.initialize(rect.width(), rect.height(), rect.width(), rect.height());
                loadCustomAnimationFromOptions2.scaleCurrentDuration(activityEmbeddingAnimationSpec.mTransitionAnimationScaleSetting);
                return loadCustomAnimationFromOptions2;
        }
    }
}
