package com.android.systemui.keyguard;

import android.animation.ValueAnimator;
import android.app.trust.TrustManager;
import android.util.Log;
import com.android.systemui.keyguard.KeyguardViewMediator;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final /* synthetic */ class KeyguardViewMediator$$ExternalSyntheticLambda7 implements Runnable {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ Object f$0;

    public /* synthetic */ KeyguardViewMediator$$ExternalSyntheticLambda7(int i, Object obj) {
        this.$r8$classId = i;
        this.f$0 = obj;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i = this.$r8$classId;
        Object obj = this.f$0;
        switch (i) {
            case 0:
                ((TrustManager) obj).reportKeyguardShowingChanged();
                break;
            case 1:
                KeyguardViewMediator keyguardViewMediator = ((KeyguardViewMediator.AnonymousClass3) obj).this$0;
                if (!keyguardViewMediator.mIgnoreDismiss) {
                    keyguardViewMediator.mHandler.obtainMessage(11, new KeyguardViewMediator.DismissMessage(null, null)).sendToTarget();
                    break;
                } else {
                    Log.i("KeyguardViewMediator", "Ignoring request to dismiss (user switch in progress?)");
                    break;
                }
            case 2:
                ValueAnimator valueAnimator = (ValueAnimator) ((KeyguardViewMediator.AnonymousClass8) obj).mOccludeByDreamAnimator;
                if (valueAnimator != null) {
                    valueAnimator.cancel();
                    break;
                }
                break;
            default:
                ValueAnimator valueAnimator2 = ((KeyguardViewMediator.AnonymousClass9) obj).mUnoccludeAnimator;
                if (valueAnimator2 != null) {
                    valueAnimator2.cancel();
                    break;
                }
                break;
        }
    }
}
