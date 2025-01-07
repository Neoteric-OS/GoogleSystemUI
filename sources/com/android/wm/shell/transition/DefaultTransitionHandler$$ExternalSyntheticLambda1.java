package com.android.wm.shell.transition;

import android.animation.Animator;
import android.content.Context;
import android.content.IntentFilter;
import android.os.Handler;
import com.android.internal.policy.TransitionAnimation;
import com.android.wm.shell.transition.DefaultTransitionHandler;
import java.util.ArrayList;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class DefaultTransitionHandler$$ExternalSyntheticLambda1 implements Runnable {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ Object f$0;

    public /* synthetic */ DefaultTransitionHandler$$ExternalSyntheticLambda1(int i, Object obj) {
        this.$r8$classId = i;
        this.f$0 = obj;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i = this.$r8$classId;
        Object obj = this.f$0;
        switch (i) {
            case 0:
                DefaultTransitionHandler defaultTransitionHandler = (DefaultTransitionHandler) obj;
                defaultTransitionHandler.mEnterpriseThumbnailDrawable = defaultTransitionHandler.mDevicePolicyManager.getResources().getDrawable("WORK_PROFILE_ICON", "OUTLINE", "PROFILE_SWITCH_ANIMATION", new DefaultTransitionHandler$$ExternalSyntheticLambda2(defaultTransitionHandler));
                Context context = defaultTransitionHandler.mContext;
                DefaultTransitionHandler.AnonymousClass1 anonymousClass1 = defaultTransitionHandler.mEnterpriseResourceUpdatedReceiver;
                IntentFilter intentFilter = new IntentFilter("android.app.action.DEVICE_POLICY_RESOURCE_UPDATED");
                Handler handler = defaultTransitionHandler.mMainHandler;
                context.registerReceiver(anonymousClass1, intentFilter, null, handler);
                TransitionAnimation.initAttributeCache(defaultTransitionHandler.mContext, handler);
                break;
            default:
                ArrayList arrayList = (ArrayList) obj;
                for (int i2 = 0; i2 < arrayList.size(); i2++) {
                    ((Animator) arrayList.get(i2)).start();
                }
                break;
        }
    }
}
