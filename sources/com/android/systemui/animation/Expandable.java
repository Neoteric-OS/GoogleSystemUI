package com.android.systemui.animation;

import com.android.systemui.animation.ActivityTransitionAnimator;
import com.android.systemui.animation.DialogTransitionAnimator;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public interface Expandable {
    ActivityTransitionAnimator.Controller activityTransitionController(Integer num);

    DialogTransitionAnimator.Controller dialogTransitionController(DialogCuj dialogCuj);
}
