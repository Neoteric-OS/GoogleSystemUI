package com.android.systemui.screenshot;

import android.os.RemoteException;
import android.util.Log;
import com.android.systemui.screenshot.AssistContentRequester;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class AssistContentRequester$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ AssistContentRequester f$0;
    public final /* synthetic */ AssistContentRequester.Callback f$1;
    public final /* synthetic */ int f$2;

    public /* synthetic */ AssistContentRequester$$ExternalSyntheticLambda0(AssistContentRequester assistContentRequester, AssistContentRequester.Callback callback, int i) {
        this.f$0 = assistContentRequester;
        this.f$1 = callback;
        this.f$2 = i;
    }

    @Override // java.lang.Runnable
    public final void run() {
        AssistContentRequester assistContentRequester = this.f$0;
        AssistContentRequester.Callback callback = this.f$1;
        int i = this.f$2;
        assistContentRequester.getClass();
        try {
            if (assistContentRequester.mActivityTaskManager.requestAssistDataForTask(new AssistContentRequester.AssistDataReceiver(callback, assistContentRequester), i, assistContentRequester.mPackageName, assistContentRequester.mAttributionTag, false)) {
                return;
            }
            callback.onAssistContentAvailable(null);
        } catch (RemoteException e) {
            Log.e("AssistContentRequester", "Requesting assist content failed for task: " + i, e);
        }
    }
}
