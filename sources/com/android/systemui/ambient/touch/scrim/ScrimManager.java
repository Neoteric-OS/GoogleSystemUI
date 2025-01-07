package com.android.systemui.ambient.touch.scrim;

import com.android.systemui.ambient.touch.BouncerSwipeTouchHandler;
import com.android.systemui.ambient.touch.BouncerSwipeTouchHandler$scrimManagerCallback$1;
import com.android.systemui.statusbar.policy.KeyguardStateController;
import com.android.systemui.statusbar.policy.KeyguardStateControllerImpl;
import java.util.HashSet;
import java.util.concurrent.Executor;
import java.util.function.Consumer;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class ScrimManager {
    public final BouncerScrimController mBouncerScrimController;
    public final BouncerlessScrimController mBouncerlessScrimController;
    public final HashSet mCallbacks;
    public ScrimController mCurrentController;
    public final Executor mExecutor;
    public final AnonymousClass1 mKeyguardStateCallback;
    public final KeyguardStateController mKeyguardStateController;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.ambient.touch.scrim.ScrimManager$1, reason: invalid class name */
    public final class AnonymousClass1 implements KeyguardStateController.Callback {
        public AnonymousClass1() {
        }

        @Override // com.android.systemui.statusbar.policy.KeyguardStateController.Callback
        public final void onKeyguardShowingChanged() {
            ScrimManager.this.mExecutor.execute(new Runnable() { // from class: com.android.systemui.ambient.touch.scrim.ScrimManager$1$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    ScrimManager.this.updateController();
                }
            });
        }
    }

    public ScrimManager(Executor executor, BouncerScrimController bouncerScrimController, BouncerlessScrimController bouncerlessScrimController, KeyguardStateController keyguardStateController) {
        AnonymousClass1 anonymousClass1 = new AnonymousClass1();
        this.mExecutor = executor;
        this.mCallbacks = new HashSet();
        this.mBouncerlessScrimController = bouncerlessScrimController;
        this.mBouncerScrimController = bouncerScrimController;
        this.mKeyguardStateController = keyguardStateController;
        ((KeyguardStateControllerImpl) keyguardStateController).addCallback(anonymousClass1);
        updateController();
    }

    public final void updateController() {
        ScrimController scrimController = this.mCurrentController;
        ScrimController scrimController2 = ((KeyguardStateControllerImpl) this.mKeyguardStateController).mCanDismissLockScreen ? this.mBouncerlessScrimController : this.mBouncerScrimController;
        this.mCurrentController = scrimController2;
        if (scrimController == scrimController2) {
            return;
        }
        this.mCallbacks.forEach(new Consumer() { // from class: com.android.systemui.ambient.touch.scrim.ScrimManager$$ExternalSyntheticLambda2
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                ScrimController scrimController3 = ScrimManager.this.mCurrentController;
                BouncerSwipeTouchHandler bouncerSwipeTouchHandler = ((BouncerSwipeTouchHandler$scrimManagerCallback$1) obj).this$0;
                ScrimController scrimController4 = bouncerSwipeTouchHandler.currentScrimController;
                if (scrimController4 != null) {
                    scrimController4.reset$1();
                }
                bouncerSwipeTouchHandler.currentScrimController = scrimController3;
            }
        });
    }
}
