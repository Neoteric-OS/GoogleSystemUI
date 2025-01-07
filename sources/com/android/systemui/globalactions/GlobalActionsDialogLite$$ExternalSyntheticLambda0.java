package com.android.systemui.globalactions;

import com.android.systemui.globalactions.GlobalActionsDialogLite;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final /* synthetic */ class GlobalActionsDialogLite$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ Object f$0;

    public /* synthetic */ GlobalActionsDialogLite$$ExternalSyntheticLambda0(int i, Object obj) {
        this.$r8$classId = i;
        this.f$0 = obj;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i = this.$r8$classId;
        Object obj = this.f$0;
        switch (i) {
            case 0:
                ((GlobalActionsDialogLite) obj).createActionItems();
                break;
            case 1:
                GlobalActionsDialogLite globalActionsDialogLite = ((GlobalActionsDialogLite.LockDownAction) obj).this$0;
                int i2 = globalActionsDialogLite.getCurrentUser().id;
                for (int i3 : globalActionsDialogLite.mUserManager.getEnabledProfileIds(i2)) {
                    if (i3 != i2) {
                        globalActionsDialogLite.mTrustManager.setDeviceLockedForUser(i3, true);
                    }
                }
                break;
            default:
                ((GlobalActionsDialogLite.AnonymousClass1) obj).this$0.mDevicePolicyManager.logoutUser();
                break;
        }
    }
}
