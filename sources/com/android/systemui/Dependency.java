package com.android.systemui;

import android.util.ArrayMap;
import com.android.internal.util.Preconditions;
import com.android.systemui.dump.DumpManager;
import dagger.Lazy;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class Dependency {
    public static final DependencyKey BG_LOOPER = new DependencyKey("background_looper");
    public static final DependencyKey TIME_TICK_HANDLER = new DependencyKey("time_tick_handler");
    public static Dependency sDependency;
    public Lazy mAmbientStateLazy;
    public Lazy mAssistManager;
    public Lazy mBgLooper;
    public Lazy mBluetoothController;
    public Lazy mBroadcastDispatcher;
    public Lazy mCommandQueue;
    public Lazy mDarkIconDispatcher;
    public Lazy mDeviceProvisionedController;
    public Lazy mDialogTransitionAnimatorLazy;
    public DumpManager mDumpManager;
    public Lazy mFeatureFlagsLazy;
    public Lazy mFragmentService;
    public Lazy mGroupExpansionManagerLazy;
    public Lazy mGroupMembershipManagerLazy;
    public Lazy mKeyguardUpdateMonitor;
    public Lazy mLightBarController;
    public Lazy mMetricsLogger;
    public Lazy mNavBarModeController;
    public Lazy mNavigationBarController;
    public Lazy mNotificationMediaManager;
    public Lazy mNotificationSectionsManagerLazy;
    public Lazy mOverviewProxyService;
    public Lazy mPluginManager;
    public Lazy mScreenOffAnimationController;
    public Lazy mStatusBarStateController;
    public Lazy mStatusBarWindowControllerLazy;
    public Lazy mSysUiStateFlagsContainer;
    public Lazy mSystemUIDialogManagerLazy;
    public Lazy mTimeTickHandler;
    public Lazy mTunerService;
    public Lazy mUiEventLogger;
    public Lazy mUiOffloadThread;
    public Lazy mUserTrackerLazy;
    public Lazy mVolumeDialogController;
    public final ArrayMap mDependencies = new ArrayMap();
    public final ArrayMap mProviders = new ArrayMap();

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class DependencyKey {
        public final String mDisplayName;

        public DependencyKey(String str) {
            this.mDisplayName = str;
        }

        public final String toString() {
            return this.mDisplayName;
        }
    }

    public static void setInstance(Dependency dependency) {
        sDependency = dependency;
    }

    public Object createDependency(Object obj) {
        Preconditions.checkArgument((obj instanceof DependencyKey) || (obj instanceof Class));
        Dependency$$ExternalSyntheticLambda0 dependency$$ExternalSyntheticLambda0 = (Dependency$$ExternalSyntheticLambda0) this.mProviders.get(obj);
        if (dependency$$ExternalSyntheticLambda0 != null) {
            return dependency$$ExternalSyntheticLambda0.f$0.get();
        }
        throw new IllegalArgumentException("Unsupported dependency " + obj + ". " + this.mProviders.size() + " providers known.");
    }

    public final synchronized Object getDependencyInner(Object obj) {
        Object obj2;
        obj2 = this.mDependencies.get(obj);
        if (obj2 == null) {
            obj2 = createDependency(obj);
            this.mDependencies.put(obj, obj2);
        }
        return obj2;
    }
}
