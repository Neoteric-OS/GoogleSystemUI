package com.android.systemui.settings;

import android.app.ActivityManager;
import android.app.IActivityManager;
import android.app.UserSwitchObserver;
import android.content.Context;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.IRemoteCallback;
import android.os.UserManager;
import android.util.Log;
import com.android.systemui.dump.DumpManager;
import com.android.systemui.flags.FeatureFlagsClassic;
import com.android.systemui.flags.Flags;
import com.android.systemui.flags.UnreleasedFlag;
import com.android.systemui.settings.UserTracker;
import com.android.systemui.util.Assert;
import dagger.internal.DelegateFactory;
import dagger.internal.Provider;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import kotlin.collections.CollectionsKt;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class MultiUserUtilsModule_ProvideUserTrackerFactory implements Provider {
    public static UserTrackerImpl provideUserTracker(Context context, DelegateFactory delegateFactory, UserManager userManager, IActivityManager iActivityManager, DumpManager dumpManager, CoroutineScope coroutineScope, CoroutineDispatcher coroutineDispatcher, Handler handler) {
        int currentUser = ActivityManager.getCurrentUser();
        final UserTrackerImpl userTrackerImpl = new UserTrackerImpl(context, delegateFactory, userManager, iActivityManager, dumpManager, coroutineScope, coroutineDispatcher, handler);
        if (!userTrackerImpl.initialized) {
            Log.i("UserTrackerImpl", "Starting user: " + currentUser);
            userTrackerImpl.initialized = true;
            userTrackerImpl.setUserIdInternal(currentUser);
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.intent.action.LOCALE_CHANGED");
            intentFilter.addAction("android.intent.action.USER_INFO_CHANGED");
            intentFilter.addAction("android.intent.action.PROFILE_ADDED");
            intentFilter.addAction("android.intent.action.PROFILE_REMOVED");
            intentFilter.addAction("android.intent.action.PROFILE_AVAILABLE");
            intentFilter.addAction("android.intent.action.PROFILE_UNAVAILABLE");
            intentFilter.addAction("android.intent.action.MANAGED_PROFILE_AVAILABLE");
            intentFilter.addAction("android.intent.action.MANAGED_PROFILE_UNAVAILABLE");
            intentFilter.addAction("android.intent.action.MANAGED_PROFILE_ADDED");
            intentFilter.addAction("android.intent.action.MANAGED_PROFILE_REMOVED");
            intentFilter.addAction("android.intent.action.MANAGED_PROFILE_UNLOCKED");
            userTrackerImpl.context.registerReceiverForAllUsers(userTrackerImpl, intentFilter, null, userTrackerImpl.backgroundHandler);
            userTrackerImpl.iActivityManager.registerUserSwitchObserver(new UserSwitchObserver() { // from class: com.android.systemui.settings.UserTrackerImpl$registerUserSwitchObserver$1
                public final void onBeforeUserSwitching(int i) {
                    List<DataItem> list;
                    UserTrackerImpl userTrackerImpl2 = UserTrackerImpl.this;
                    userTrackerImpl2.setUserIdInternal(i);
                    synchronized (userTrackerImpl2.callbacks) {
                        list = CollectionsKt.toList(userTrackerImpl2.callbacks);
                    }
                    CountDownLatch countDownLatch = new CountDownLatch(list.size());
                    for (DataItem dataItem : list) {
                        UserTracker.Callback callback = (UserTracker.Callback) dataItem.callback.get();
                        if (callback != null) {
                            dataItem.executor.execute(new UserTrackerImpl$handleUserSwitchComplete$$inlined$notifySubscribers$1(callback, countDownLatch, i));
                        } else {
                            countDownLatch.countDown();
                        }
                    }
                    countDownLatch.await();
                }

                public final void onUserSwitchComplete(int i) {
                    List<DataItem> list;
                    UserTrackerImpl userTrackerImpl2 = UserTrackerImpl.this;
                    userTrackerImpl2.isUserSwitching = false;
                    FeatureFlagsClassic featureFlagsClassic = (FeatureFlagsClassic) userTrackerImpl2.featureFlagsProvider.get();
                    UnreleasedFlag unreleasedFlag = Flags.NULL_FLAG;
                    featureFlagsClassic.getClass();
                    UserTrackerImpl userTrackerImpl3 = UserTrackerImpl.this;
                    userTrackerImpl3.getClass();
                    Assert.isNotMainThread();
                    Log.i("UserTrackerImpl", "Switched to user " + i);
                    synchronized (userTrackerImpl3.callbacks) {
                        list = CollectionsKt.toList(userTrackerImpl3.callbacks);
                    }
                    CountDownLatch countDownLatch = new CountDownLatch(list.size());
                    for (DataItem dataItem : list) {
                        UserTracker.Callback callback = (UserTracker.Callback) dataItem.callback.get();
                        if (callback != null) {
                            dataItem.executor.execute(new UserTrackerImpl$handleUserSwitchComplete$$inlined$notifySubscribers$1(callback, countDownLatch, i, userTrackerImpl3));
                        } else {
                            countDownLatch.countDown();
                        }
                    }
                }

                public final void onUserSwitching(final int i, IRemoteCallback iRemoteCallback) {
                    List<DataItem> list;
                    UserTrackerImpl userTrackerImpl2 = UserTrackerImpl.this;
                    userTrackerImpl2.isUserSwitching = true;
                    FeatureFlagsClassic featureFlagsClassic = (FeatureFlagsClassic) userTrackerImpl2.featureFlagsProvider.get();
                    UnreleasedFlag unreleasedFlag = Flags.NULL_FLAG;
                    featureFlagsClassic.getClass();
                    final UserTrackerImpl userTrackerImpl3 = UserTrackerImpl.this;
                    Assert.isNotMainThread();
                    Log.i("UserTrackerImpl", "Switching to user " + i);
                    synchronized (userTrackerImpl3.callbacks) {
                        list = CollectionsKt.toList(userTrackerImpl3.callbacks);
                    }
                    final CountDownLatch countDownLatch = new CountDownLatch(list.size());
                    for (DataItem dataItem : list) {
                        final UserTracker.Callback callback = (UserTracker.Callback) dataItem.callback.get();
                        if (callback != null) {
                            dataItem.executor.execute(new Runnable() { // from class: com.android.systemui.settings.UserTrackerImpl$handleUserSwitching$$inlined$notifySubscribers$1
                                @Override // java.lang.Runnable
                                public final void run() {
                                    UserTracker.Callback callback2 = UserTracker.Callback.this;
                                    final CountDownLatch countDownLatch2 = countDownLatch;
                                    callback2.onUserChanging(i, userTrackerImpl3.getUserContext(), new Runnable() { // from class: com.android.systemui.settings.UserTrackerImpl$handleUserSwitching$$inlined$notifySubscribers$1.1
                                        @Override // java.lang.Runnable
                                        public final void run() {
                                            countDownLatch2.countDown();
                                        }
                                    });
                                }
                            });
                        } else {
                            countDownLatch.countDown();
                        }
                    }
                    countDownLatch.await();
                    if (iRemoteCallback != null) {
                        iRemoteCallback.sendResult((Bundle) null);
                    }
                }
            }, "UserTrackerImpl");
            DumpManager.registerDumpable$default(userTrackerImpl.dumpManager, "UserTrackerImpl", userTrackerImpl);
        }
        return userTrackerImpl;
    }
}
