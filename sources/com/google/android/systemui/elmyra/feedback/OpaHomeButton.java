package com.google.android.systemui.elmyra.feedback;

import android.view.KeyEvent;
import android.view.View;
import android.view.ViewParent;
import com.android.systemui.keyguard.KeyguardViewMediator;
import com.android.systemui.navigationbar.NavigationModeController;
import com.android.systemui.navigationbar.views.NavigationBarView;
import com.android.systemui.statusbar.phone.CentralSurfaces;
import com.android.systemui.statusbar.phone.CentralSurfacesImpl;
import java.util.ArrayList;
import java.util.List;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class OpaHomeButton extends NavigationBarEffect {
    public final CentralSurfaces mCentralSurfaces;
    public final KeyguardViewMediator mKeyguardViewMediator;

    public OpaHomeButton(KeyguardViewMediator keyguardViewMediator, CentralSurfaces centralSurfaces, NavigationModeController navigationModeController) {
        super(centralSurfaces, navigationModeController);
        this.mCentralSurfaces = centralSurfaces;
        this.mKeyguardViewMediator = keyguardViewMediator;
    }

    @Override // com.google.android.systemui.elmyra.feedback.NavigationBarEffect
    public final List findFeedbackEffects(NavigationBarView navigationBarView) {
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = navigationBarView.getHomeButton().mViews;
        for (int i = 0; i < arrayList2.size(); i++) {
            KeyEvent.Callback callback = (View) arrayList2.get(i);
            if (callback instanceof FeedbackEffect) {
                arrayList.add((FeedbackEffect) callback);
            }
        }
        return arrayList;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.android.systemui.elmyra.feedback.NavigationBarEffect
    public final boolean isActiveFeedbackEffect(FeedbackEffect feedbackEffect) {
        if (this.mKeyguardViewMediator.isShowingAndNotOccluded()) {
            return false;
        }
        View view = ((CentralSurfacesImpl) this.mCentralSurfaces).getNavigationBarView().mCurrentView;
        for (ViewParent parent = ((View) feedbackEffect).getParent(); parent != null; parent = parent.getParent()) {
            if (parent.equals(view)) {
                return true;
            }
        }
        return false;
    }

    @Override // com.google.android.systemui.elmyra.feedback.NavigationBarEffect
    public final boolean validateFeedbackEffects(List list) {
        for (int i = 0; i < list.size(); i++) {
            if (!((View) list.get(i)).isAttachedToWindow()) {
                return false;
            }
        }
        return true;
    }
}
