package com.google.android.systemui.elmyra.feedback;

import android.content.Context;
import android.view.View;
import com.android.systemui.keyguard.KeyguardViewMediator;
import com.android.systemui.navigationbar.NavigationModeController;
import com.android.systemui.navigationbar.views.NavigationBarView;
import com.android.systemui.statusbar.phone.CentralSurfaces;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class SquishyNavigationButtons extends NavigationBarEffect {
    public final KeyguardViewMediator mKeyguardViewMediator;
    public final SquishyViewController mViewController;

    public SquishyNavigationButtons(Context context, KeyguardViewMediator keyguardViewMediator, CentralSurfaces centralSurfaces, NavigationModeController navigationModeController) {
        super(centralSurfaces, navigationModeController);
        this.mViewController = new SquishyViewController(context);
        this.mKeyguardViewMediator = keyguardViewMediator;
    }

    @Override // com.google.android.systemui.elmyra.feedback.NavigationBarEffect
    public final List findFeedbackEffects(NavigationBarView navigationBarView) {
        SquishyViewController squishyViewController = this.mViewController;
        squishyViewController.translateViews(0.0f);
        squishyViewController.mLeftViews.clear();
        squishyViewController.mRightViews.clear();
        ArrayList arrayList = navigationBarView.getBackButton().mViews;
        for (int i = 0; i < arrayList.size(); i++) {
            squishyViewController.mLeftViews.add((View) arrayList.get(i));
        }
        ArrayList arrayList2 = navigationBarView.getRecentsButton().mViews;
        for (int i2 = 0; i2 < arrayList2.size(); i2++) {
            squishyViewController.mRightViews.add((View) arrayList2.get(i2));
        }
        return Arrays.asList(squishyViewController);
    }

    @Override // com.google.android.systemui.elmyra.feedback.NavigationBarEffect
    public final boolean isActiveFeedbackEffect(FeedbackEffect feedbackEffect) {
        return !this.mKeyguardViewMediator.isShowingAndNotOccluded();
    }

    @Override // com.google.android.systemui.elmyra.feedback.NavigationBarEffect
    public final void reset$1() {
        super.reset$1();
        SquishyViewController squishyViewController = this.mViewController;
        squishyViewController.translateViews(0.0f);
        squishyViewController.mLeftViews.clear();
        squishyViewController.mRightViews.clear();
    }

    @Override // com.google.android.systemui.elmyra.feedback.NavigationBarEffect
    public final boolean validateFeedbackEffects(List list) {
        SquishyViewController squishyViewController;
        boolean z = false;
        int i = 0;
        while (true) {
            squishyViewController = this.mViewController;
            if (i >= ((ArrayList) squishyViewController.mLeftViews).size()) {
                int i2 = 0;
                while (true) {
                    if (i2 >= ((ArrayList) squishyViewController.mRightViews).size()) {
                        z = true;
                        break;
                    }
                    if (!((View) ((ArrayList) squishyViewController.mRightViews).get(i2)).isAttachedToWindow()) {
                        break;
                    }
                    i2++;
                }
            } else {
                if (!((View) ((ArrayList) squishyViewController.mLeftViews).get(i)).isAttachedToWindow()) {
                    break;
                }
                i++;
            }
        }
        if (!z) {
            squishyViewController.translateViews(0.0f);
            squishyViewController.mLeftViews.clear();
            squishyViewController.mRightViews.clear();
        }
        return z;
    }
}
