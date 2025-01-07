package com.android.systemui.biometrics;

import com.android.systemui.biometrics.AuthContainerView;
import com.android.systemui.biometrics.domain.interactor.PromptSelectorInteractor;
import com.android.systemui.biometrics.domain.interactor.PromptSelectorInteractorImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final /* synthetic */ class AuthContainerView$$ExternalSyntheticLambda3 implements Runnable {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ AuthContainerView f$0;

    public /* synthetic */ AuthContainerView$$ExternalSyntheticLambda3(AuthContainerView authContainerView, int i) {
        this.$r8$classId = i;
        this.f$0 = authContainerView;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i = this.$r8$classId;
        AuthContainerView authContainerView = this.f$0;
        switch (i) {
            case 0:
                authContainerView.setVisibility(4);
                ((PromptSelectorInteractorImpl) ((PromptSelectorInteractor) authContainerView.mPromptSelectorInteractorProvider.get())).resetPrompt(authContainerView.mConfig.mRequestId);
                authContainerView.removeWindowIfAttached();
                break;
            case 1:
                authContainerView.animate().alpha(1.0f).translationY(0.0f).setDuration(250L).setInterpolator(authContainerView.mLinearOutSlowIn).withLayer().setListener(new AuthContainerView.AnonymousClass1(authContainerView, authContainerView, "show", 250L)).withEndAction(new AuthContainerView$$ExternalSyntheticLambda3(authContainerView, 2)).start();
                break;
            default:
                authContainerView.onDialogAnimatedIn();
                break;
        }
    }
}
