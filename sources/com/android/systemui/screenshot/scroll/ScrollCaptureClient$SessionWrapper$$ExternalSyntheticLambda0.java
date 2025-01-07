package com.android.systemui.screenshot.scroll;

import android.os.RemoteException;
import com.android.systemui.screenshot.scroll.ScrollCaptureClient;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class ScrollCaptureClient$SessionWrapper$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ ScrollCaptureClient.SessionWrapper f$0;

    public /* synthetic */ ScrollCaptureClient$SessionWrapper$$ExternalSyntheticLambda0(ScrollCaptureClient.SessionWrapper sessionWrapper, int i) {
        this.$r8$classId = i;
        this.f$0 = sessionWrapper;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i = this.$r8$classId;
        ScrollCaptureClient.SessionWrapper sessionWrapper = this.f$0;
        sessionWrapper.getClass();
        switch (i) {
            case 0:
                try {
                    sessionWrapper.mCancellationSignal.cancel();
                    break;
                } catch (RemoteException unused) {
                    return;
                }
            default:
                try {
                    sessionWrapper.mCancellationSignal.cancel();
                    break;
                } catch (RemoteException unused2) {
                    return;
                }
        }
    }
}
