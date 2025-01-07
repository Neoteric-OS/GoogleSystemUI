package com.android.systemui.settings;

import android.app.IActivityManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.UserInfo;
import android.os.Handler;
import android.os.UserHandle;
import android.os.UserManager;
import androidx.appsearch.app.AppSearchSchema$Builder$$ExternalSyntheticOutline0;
import com.android.keyguard.ActiveUnlockConfig$$ExternalSyntheticOutline0;
import com.android.systemui.Dumpable;
import com.android.systemui.dump.DumpManager;
import com.android.systemui.settings.UserTracker;
import com.android.systemui.util.Assert;
import dagger.internal.DelegateFactory;
import java.io.PrintWriter;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.function.Predicate;
import kotlin.collections.CollectionsKt;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.collections.EmptyList;
import kotlin.jvm.internal.CallableReference;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.MutablePropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KProperty;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class UserTrackerImpl extends BroadcastReceiver implements UserTracker, Dumpable {
    public static final /* synthetic */ KProperty[] $$delegatedProperties;
    public final CoroutineDispatcher backgroundContext;
    public final Handler backgroundHandler;
    public final Context context;
    public final DumpManager dumpManager;
    public final DelegateFactory featureFlagsProvider;
    public final IActivityManager iActivityManager;
    public boolean initialized;
    public boolean isUserSwitching;
    public final SynchronizedDelegate userContext$delegate;
    public final SynchronizedDelegate userHandle$delegate;
    public final SynchronizedDelegate userId$delegate;
    public final SynchronizedDelegate userInfo$delegate;
    public final UserManager userManager;
    public final Object mutex = new Object();
    public final SynchronizedDelegate userProfiles$delegate = new SynchronizedDelegate(EmptyList.INSTANCE);
    public final List callbacks = new ArrayList();

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class SynchronizedDelegate {
        public Object value;

        public SynchronizedDelegate(Object obj) {
            this.value = obj;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public final Object getValue(UserTrackerImpl userTrackerImpl, KProperty kProperty) {
            Object obj;
            if (!userTrackerImpl.initialized) {
                throw new IllegalStateException(AppSearchSchema$Builder$$ExternalSyntheticOutline0.m("Must initialize before getting ", ((CallableReference) kProperty).getName()));
            }
            synchronized (userTrackerImpl.mutex) {
                obj = this.value;
            }
            return obj;
        }

        public final void setValue(UserTrackerImpl userTrackerImpl, Object obj) {
            synchronized (userTrackerImpl.mutex) {
                this.value = obj;
            }
        }
    }

    static {
        MutablePropertyReference1Impl mutablePropertyReference1Impl = new MutablePropertyReference1Impl(UserTrackerImpl.class, "userId", "getUserId()I", 0);
        Reflection.factory.getClass();
        $$delegatedProperties = new KProperty[]{mutablePropertyReference1Impl, new MutablePropertyReference1Impl(UserTrackerImpl.class, "userHandle", "getUserHandle()Landroid/os/UserHandle;", 0), new MutablePropertyReference1Impl(UserTrackerImpl.class, "userContext", "getUserContext()Landroid/content/Context;", 0), new MutablePropertyReference1Impl(UserTrackerImpl.class, "userInfo", "getUserInfo()Landroid/content/pm/UserInfo;", 0), new MutablePropertyReference1Impl(UserTrackerImpl.class, "userProfiles", "getUserProfiles()Ljava/util/List;", 0)};
    }

    public UserTrackerImpl(Context context, DelegateFactory delegateFactory, UserManager userManager, IActivityManager iActivityManager, DumpManager dumpManager, CoroutineScope coroutineScope, CoroutineDispatcher coroutineDispatcher, Handler handler) {
        this.context = context;
        this.featureFlagsProvider = delegateFactory;
        this.userManager = userManager;
        this.iActivityManager = iActivityManager;
        this.dumpManager = dumpManager;
        this.backgroundHandler = handler;
        this.userId$delegate = new SynchronizedDelegate(Integer.valueOf(context.getUserId()));
        this.userHandle$delegate = new SynchronizedDelegate(context.getUser());
        this.userContext$delegate = new SynchronizedDelegate(context);
        this.userInfo$delegate = new SynchronizedDelegate(new UserInfo(context.getUserId(), "", 0));
    }

    public final void addCallback(UserTracker.Callback callback, Executor executor) {
        synchronized (this.callbacks) {
            this.callbacks.add(new DataItem(new WeakReference(callback), executor));
        }
    }

    @Override // com.android.systemui.Dumpable
    public final void dump(PrintWriter printWriter, String[] strArr) {
        List list;
        ActiveUnlockConfig$$ExternalSyntheticOutline0.m("Initialized: ", this.initialized, printWriter);
        if (this.initialized) {
            printWriter.println("userId: " + getUserId());
            List userProfiles = getUserProfiles();
            ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(userProfiles, 10));
            Iterator it = userProfiles.iterator();
            while (it.hasNext()) {
                arrayList.add(((UserInfo) it.next()).toFullString());
            }
            printWriter.println("userProfiles: " + arrayList);
        }
        synchronized (this.callbacks) {
            list = CollectionsKt.toList(this.callbacks);
        }
        printWriter.println("Callbacks:");
        Iterator it2 = list.iterator();
        while (it2.hasNext()) {
            UserTracker.Callback callback = (UserTracker.Callback) ((DataItem) it2.next()).callback.get();
            if (callback != null) {
                printWriter.println("  " + callback);
            }
        }
    }

    public final Context getUserContext() {
        return (Context) this.userContext$delegate.getValue(this, $$delegatedProperties[2]);
    }

    public final UserHandle getUserHandle() {
        return (UserHandle) this.userHandle$delegate.getValue(this, $$delegatedProperties[1]);
    }

    public final int getUserId() {
        return ((Number) this.userId$delegate.getValue(this, $$delegatedProperties[0])).intValue();
    }

    public final UserInfo getUserInfo() {
        return (UserInfo) this.userInfo$delegate.getValue(this, $$delegatedProperties[3]);
    }

    public final List getUserProfiles() {
        return (List) this.userProfiles$delegate.getValue(this, $$delegatedProperties[4]);
    }

    @Override // android.content.BroadcastReceiver
    public final void onReceive(Context context, Intent intent) {
        List<DataItem> list;
        String action = intent.getAction();
        if (action != null) {
            switch (action.hashCode()) {
                case -1823736795:
                    if (!action.equals("android.intent.action.PROFILE_REMOVED")) {
                        return;
                    }
                    break;
                case -1462075554:
                    if (!action.equals("android.intent.action.MANAGED_PROFILE_UNLOCKED")) {
                        return;
                    }
                    break;
                case -1238404651:
                    if (!action.equals("android.intent.action.MANAGED_PROFILE_UNAVAILABLE")) {
                        return;
                    }
                    break;
                case -1112607227:
                    if (!action.equals("android.intent.action.PROFILE_ADDED")) {
                        return;
                    }
                    break;
                case -1062385259:
                    if (!action.equals("android.intent.action.PROFILE_UNAVAILABLE")) {
                        return;
                    }
                    break;
                case -864107122:
                    if (!action.equals("android.intent.action.MANAGED_PROFILE_AVAILABLE")) {
                        return;
                    }
                    break;
                case -385593787:
                    if (!action.equals("android.intent.action.MANAGED_PROFILE_ADDED")) {
                        return;
                    }
                    break;
                case -201513518:
                    if (!action.equals("android.intent.action.USER_INFO_CHANGED")) {
                        return;
                    }
                    break;
                case -19011148:
                    if (!action.equals("android.intent.action.LOCALE_CHANGED")) {
                        return;
                    }
                    break;
                case 1051477093:
                    if (!action.equals("android.intent.action.MANAGED_PROFILE_REMOVED")) {
                        return;
                    }
                    break;
                case 2014285134:
                    if (!action.equals("android.intent.action.PROFILE_AVAILABLE")) {
                        return;
                    }
                    break;
                default:
                    return;
            }
            Assert.isNotMainThread();
            final List profiles = this.userManager.getProfiles(getUserId());
            synchronized (this.mutex) {
                try {
                    Intrinsics.checkNotNull(profiles);
                    ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(profiles, 10));
                    Iterator it = profiles.iterator();
                    while (it.hasNext()) {
                        arrayList.add(new UserInfo((UserInfo) it.next()));
                    }
                    SynchronizedDelegate synchronizedDelegate = this.userProfiles$delegate;
                    KProperty kProperty = $$delegatedProperties[4];
                    synchronizedDelegate.setValue(this, arrayList);
                } catch (Throwable th) {
                    throw th;
                }
            }
            synchronized (this.callbacks) {
                list = CollectionsKt.toList(this.callbacks);
            }
            final CountDownLatch countDownLatch = new CountDownLatch(list.size());
            for (DataItem dataItem : list) {
                final UserTracker.Callback callback = (UserTracker.Callback) dataItem.callback.get();
                if (callback != null) {
                    dataItem.executor.execute(new Runnable(countDownLatch, profiles) { // from class: com.android.systemui.settings.UserTrackerImpl$handleProfilesChanged$$inlined$notifySubscribers$1
                        public final /* synthetic */ List $profiles$inlined;

                        {
                            this.$profiles$inlined = profiles;
                        }

                        @Override // java.lang.Runnable
                        public final void run() {
                            UserTracker.Callback callback2 = UserTracker.Callback.this;
                            Intrinsics.checkNotNull(this.$profiles$inlined);
                            callback2.onProfilesChanged(this.$profiles$inlined);
                        }
                    });
                } else {
                    countDownLatch.countDown();
                }
            }
        }
    }

    public final void removeCallback(final UserTracker.Callback callback) {
        synchronized (this.callbacks) {
            ((ArrayList) this.callbacks).removeIf(new Predicate() { // from class: com.android.systemui.settings.UserTrackerImpl$removeCallback$1$1
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    UserTracker.Callback callback2 = UserTracker.Callback.this;
                    UserTracker.Callback callback3 = (UserTracker.Callback) ((DataItem) obj).callback.get();
                    if (callback3 != null) {
                        return callback3.equals(callback2);
                    }
                    return true;
                }
            });
        }
    }

    public final void setUserIdInternal(int i) {
        List profiles = this.userManager.getProfiles(i);
        UserHandle userHandle = new UserHandle(i);
        Context createContextAsUser = this.context.createContextAsUser(userHandle, 0);
        synchronized (this.mutex) {
            try {
                SynchronizedDelegate synchronizedDelegate = this.userId$delegate;
                KProperty[] kPropertyArr = $$delegatedProperties;
                KProperty kProperty = kPropertyArr[0];
                synchronizedDelegate.setValue(this, Integer.valueOf(i));
                SynchronizedDelegate synchronizedDelegate2 = this.userHandle$delegate;
                KProperty kProperty2 = kPropertyArr[1];
                synchronizedDelegate2.setValue(this, userHandle);
                SynchronizedDelegate synchronizedDelegate3 = this.userContext$delegate;
                KProperty kProperty3 = kPropertyArr[2];
                synchronizedDelegate3.setValue(this, createContextAsUser);
                Intrinsics.checkNotNull(profiles);
                ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(profiles, 10));
                Iterator it = profiles.iterator();
                while (it.hasNext()) {
                    arrayList.add(new UserInfo((UserInfo) it.next()));
                }
                SynchronizedDelegate synchronizedDelegate4 = this.userProfiles$delegate;
                KProperty kProperty4 = $$delegatedProperties[4];
                synchronizedDelegate4.setValue(this, arrayList);
                for (Object obj : profiles) {
                    if (((UserInfo) obj).id == i) {
                        SynchronizedDelegate synchronizedDelegate5 = this.userInfo$delegate;
                        KProperty kProperty5 = $$delegatedProperties[3];
                        synchronizedDelegate5.setValue(this, (UserInfo) obj);
                    }
                }
                throw new NoSuchElementException("Collection contains no element matching the predicate.");
            } catch (Throwable th) {
                throw th;
            }
        }
    }
}
