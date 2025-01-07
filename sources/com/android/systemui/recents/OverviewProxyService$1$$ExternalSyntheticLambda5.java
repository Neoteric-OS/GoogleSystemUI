package com.android.systemui.recents;

import android.view.KeyEvent;
import android.view.accessibility.AccessibilityManager;
import com.android.systemui.recents.OverviewProxyService;
import com.android.systemui.statusbar.CommandQueue;
import java.util.ArrayList;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class OverviewProxyService$1$$ExternalSyntheticLambda5 implements Runnable {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ OverviewProxyService.AnonymousClass1 f$0;

    public /* synthetic */ OverviewProxyService$1$$ExternalSyntheticLambda5(OverviewProxyService.AnonymousClass1 anonymousClass1, int i) {
        this.$r8$classId = i;
        this.f$0 = anonymousClass1;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i = this.$r8$classId;
        OverviewProxyService.AnonymousClass1 anonymousClass1 = this.f$0;
        switch (i) {
            case 0:
                CommandQueue commandQueue = OverviewProxyService.this.mCommandQueue;
                synchronized (commandQueue.mLock) {
                    commandQueue.mHandler.removeMessages(5373952);
                    commandQueue.mHandler.obtainMessage(5373952, 0, 0).sendToTarget();
                }
                return;
            case 1:
                OverviewProxyService.this.mCommandQueue.handleSystemKey(new KeyEvent(0, 281));
                return;
            case 2:
                for (int size = ((ArrayList) OverviewProxyService.this.mConnectionCallbacks).size() - 1; size >= 0; size--) {
                    ((OverviewProxyService.OverviewProxyListener) ((ArrayList) OverviewProxyService.this.mConnectionCallbacks).get(size)).onOverviewShown();
                }
                return;
            case 3:
                OverviewProxyService.this.mCommandQueue.toggleNotificationsPanel();
                return;
            case 4:
                anonymousClass1.sendEvent(0);
                anonymousClass1.sendEvent(1);
                return;
            default:
                AccessibilityManager accessibilityManager = AccessibilityManager.getInstance(OverviewProxyService.this.mContext);
                OverviewProxyService.this.mDisplayTracker.getClass();
                accessibilityManager.notifyAccessibilityButtonLongClicked(0);
                return;
        }
    }

    public /* synthetic */ OverviewProxyService$1$$ExternalSyntheticLambda5(OverviewProxyService.AnonymousClass1 anonymousClass1, boolean z) {
        this.$r8$classId = 2;
        this.f$0 = anonymousClass1;
    }
}
