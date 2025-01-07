package com.android.systemui.keyguard;

import android.os.RemoteException;
import android.util.Log;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final /* synthetic */ class DismissCallbackRegistry$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ DismissCallbackWrapper f$0;

    public /* synthetic */ DismissCallbackRegistry$$ExternalSyntheticLambda0(DismissCallbackWrapper dismissCallbackWrapper, int i) {
        this.$r8$classId = i;
        this.f$0 = dismissCallbackWrapper;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i = this.$r8$classId;
        DismissCallbackWrapper dismissCallbackWrapper = this.f$0;
        dismissCallbackWrapper.getClass();
        switch (i) {
            case 0:
                try {
                    dismissCallbackWrapper.mCallback.onDismissSucceeded();
                    break;
                } catch (RemoteException e) {
                    Log.i("DismissCallbackWrapper", "Failed to call callback", e);
                    return;
                }
            default:
                try {
                    dismissCallbackWrapper.mCallback.onDismissCancelled();
                    break;
                } catch (RemoteException e2) {
                    Log.i("DismissCallbackWrapper", "Failed to call callback", e2);
                }
        }
    }
}
