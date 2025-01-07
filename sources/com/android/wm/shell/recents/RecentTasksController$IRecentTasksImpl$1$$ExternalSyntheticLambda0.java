package com.android.wm.shell.recents;

import com.android.wm.shell.common.SingleInstanceRemoteListener;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class RecentTasksController$IRecentTasksImpl$1$$ExternalSyntheticLambda0 implements SingleInstanceRemoteListener.RemoteCall {
    @Override // com.android.wm.shell.common.SingleInstanceRemoteListener.RemoteCall
    public final void accept(Object obj) {
        ((IRecentTasksListener) obj).onRecentTasksChanged();
    }
}
