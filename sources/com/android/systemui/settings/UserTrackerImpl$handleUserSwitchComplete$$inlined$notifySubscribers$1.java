package com.android.systemui.settings;

import com.android.systemui.settings.UserTracker;
import java.util.concurrent.CountDownLatch;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class UserTrackerImpl$handleUserSwitchComplete$$inlined$notifySubscribers$1 implements Runnable {
    public final /* synthetic */ UserTracker.Callback $callback;
    public final /* synthetic */ int $newUserId$inlined;
    public final /* synthetic */ int $r8$classId = 1;
    public final /* synthetic */ Object this$0;

    public UserTrackerImpl$handleUserSwitchComplete$$inlined$notifySubscribers$1(UserTracker.Callback callback, CountDownLatch countDownLatch, int i) {
        this.$callback = callback;
        this.this$0 = countDownLatch;
        this.$newUserId$inlined = i;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.$r8$classId) {
            case 0:
                UserTracker.Callback callback = this.$callback;
                callback.onUserChanged(this.$newUserId$inlined, ((UserTrackerImpl) this.this$0).getUserContext());
                callback.onProfilesChanged(((UserTrackerImpl) this.this$0).getUserProfiles());
                break;
            default:
                UserTracker.Callback callback2 = this.$callback;
                CountDownLatch countDownLatch = (CountDownLatch) this.this$0;
                callback2.onBeforeUserSwitching(this.$newUserId$inlined);
                countDownLatch.countDown();
                break;
        }
    }

    public UserTrackerImpl$handleUserSwitchComplete$$inlined$notifySubscribers$1(UserTracker.Callback callback, CountDownLatch countDownLatch, int i, UserTrackerImpl userTrackerImpl) {
        this.$callback = callback;
        this.$newUserId$inlined = i;
        this.this$0 = userTrackerImpl;
    }
}
