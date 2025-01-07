package com.android.systemui.media.dialog;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import com.android.systemui.animation.DialogTransitionAnimator;
import com.android.systemui.animation.DialogTransitionAnimator$createActivityTransitionController$1;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final /* synthetic */ class MediaOutputBaseDialog$$ExternalSyntheticLambda0 implements View.OnClickListener {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ Object f$0;

    public /* synthetic */ MediaOutputBaseDialog$$ExternalSyntheticLambda0(int i, Object obj) {
        this.$r8$classId = i;
        this.f$0 = obj;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        int i = this.$r8$classId;
        Object obj = this.f$0;
        switch (i) {
            case 0:
                ((MediaOutputBaseDialog) obj).onStopButtonClick();
                break;
            case 1:
                ((MediaOutputBaseDialog) obj).onBroadcastIconClick();
                break;
            case 2:
                ((MediaOutputBaseDialog) obj).dismiss();
                break;
            case 3:
                ((MediaOutputBaseDialog) obj).onStopButtonClick();
                break;
            default:
                MediaSwitchingController mediaSwitchingController = (MediaSwitchingController) obj;
                DialogTransitionAnimator dialogTransitionAnimator = mediaSwitchingController.mDialogTransitionAnimator;
                dialogTransitionAnimator.getClass();
                DialogTransitionAnimator$createActivityTransitionController$1 createActivityTransitionController$default = DialogTransitionAnimator.createActivityTransitionController$default(dialogTransitionAnimator, view);
                Intent launchIntentForPackage = TextUtils.isEmpty(mediaSwitchingController.mPackageName) ? null : mediaSwitchingController.mContext.getPackageManager().getLaunchIntentForPackage(mediaSwitchingController.mPackageName);
                if (launchIntentForPackage != null) {
                    launchIntentForPackage.addFlags(268435456);
                    ((MediaOutputBaseDialog) mediaSwitchingController.mCallback).mBroadcastSender.closeSystemDialogs();
                    mediaSwitchingController.startActivity(launchIntentForPackage, createActivityTransitionController$default);
                    break;
                }
                break;
        }
    }
}
