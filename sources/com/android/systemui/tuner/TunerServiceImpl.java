package com.android.systemui.tuner;

import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.UserInfo;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Handler;
import android.os.HandlerExecutor;
import android.os.Looper;
import android.os.SystemClock;
import android.os.UserManager;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.ArrayMap;
import android.util.ArraySet;
import com.android.internal.util.ArrayUtils;
import com.android.systemui.DejankUtils;
import com.android.systemui.demomode.DemoModeController;
import com.android.systemui.settings.UserTracker;
import com.android.systemui.settings.UserTrackerImpl;
import com.android.systemui.statusbar.phone.ui.StatusBarIconController;
import com.android.systemui.tuner.TunerService;
import com.android.systemui.util.leak.LeakDetector;
import com.android.systemui.util.leak.TrackedCollections;
import com.android.systemui.util.leak.WeakIdentityHashMap;
import com.android.systemui.util.settings.GlobalSettings;
import com.android.systemui.util.settings.GlobalSettingsImpl;
import dagger.Lazy;
import java.lang.ref.Reference;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Supplier;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class TunerServiceImpl extends TunerService {
    public static final String[] RESET_EXCEPTION_LIST = {"sysui_qs_tiles", "doze_always_on", "qs_media_resumption", "qs_media_recommend"};
    public final ContentResolver mContentResolver;
    public final Context mContext;
    public int mCurrentUser;
    public UserTracker.Callback mCurrentUserTracker;
    public final DemoModeController mDemoModeController;
    public final LeakDetector mLeakDetector;
    public final Lazy mSystemUIDialogFactoryLazy;
    public final HashSet mTunables;
    public final ComponentName mTunerComponent;
    public UserTracker mUserTracker;
    public final Observer mObserver = new Observer();
    public final ArrayMap mListeningUris = new ArrayMap();
    public final ConcurrentHashMap mTunableLookup = new ConcurrentHashMap();

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Observer extends ContentObserver {
        public Observer() {
            super(new Handler(Looper.getMainLooper()));
        }

        public final void onChange(boolean z, Collection collection, int i, int i2) {
            if (i2 == ((UserTrackerImpl) TunerServiceImpl.this.mUserTracker).getUserId()) {
                Iterator it = collection.iterator();
                while (it.hasNext()) {
                    Uri uri = (Uri) it.next();
                    TunerServiceImpl tunerServiceImpl = TunerServiceImpl.this;
                    String str = (String) tunerServiceImpl.mListeningUris.get(uri);
                    Set set = (Set) tunerServiceImpl.mTunableLookup.get(str);
                    if (set != null) {
                        String stringForUser = Settings.Secure.getStringForUser(tunerServiceImpl.mContentResolver, str, tunerServiceImpl.mCurrentUser);
                        Iterator it2 = set.iterator();
                        while (it2.hasNext()) {
                            ((TunerService.Tunable) it2.next()).onTuningChanged(str, stringForUser);
                        }
                    }
                }
            }
        }
    }

    public TunerServiceImpl(Context context, Handler handler, LeakDetector leakDetector, DemoModeController demoModeController, UserTracker userTracker, Lazy lazy) {
        String stringForUser;
        this.mTunables = LeakDetector.ENABLED ? new HashSet() : null;
        this.mContext = context;
        this.mSystemUIDialogFactoryLazy = lazy;
        this.mContentResolver = context.getContentResolver();
        this.mLeakDetector = leakDetector;
        this.mDemoModeController = demoModeController;
        this.mUserTracker = userTracker;
        this.mTunerComponent = new ComponentName(context, (Class<?>) TunerActivity.class);
        Iterator it = UserManager.get(context).getUsers().iterator();
        while (it.hasNext()) {
            int identifier = ((UserInfo) it.next()).getUserHandle().getIdentifier();
            this.mCurrentUser = identifier;
            if (Settings.Secure.getIntForUser(this.mContentResolver, "sysui_tuner_version", 0, identifier) != 4) {
                int intForUser = Settings.Secure.getIntForUser(this.mContentResolver, "sysui_tuner_version", 0, this.mCurrentUser);
                if (intForUser < 1 && (stringForUser = Settings.Secure.getStringForUser(this.mContentResolver, "icon_blacklist", this.mCurrentUser)) != null) {
                    ArraySet iconHideList = StatusBarIconController.getIconHideList(this.mContext, stringForUser);
                    iconHideList.add("rotate");
                    iconHideList.add("headset");
                    Settings.Secure.putStringForUser(this.mContentResolver, "icon_blacklist", TextUtils.join(",", iconHideList), this.mCurrentUser);
                }
                if (intForUser < 2) {
                    ((UserTrackerImpl) this.mUserTracker).getUserContext().getPackageManager().setComponentEnabledSetting(this.mTunerComponent, 2, 1);
                }
                if (intForUser < 4) {
                    final int i = this.mCurrentUser;
                    handler.postDelayed(new Runnable() { // from class: com.android.systemui.tuner.TunerServiceImpl$$ExternalSyntheticLambda1
                        @Override // java.lang.Runnable
                        public final void run() {
                            TunerServiceImpl.this.clearAllFromUser(i);
                        }
                    }, 5000L);
                }
                Settings.Secure.putIntForUser(this.mContentResolver, "sysui_tuner_version", 4, this.mCurrentUser);
            }
        }
        this.mCurrentUser = ((UserTrackerImpl) this.mUserTracker).getUserId();
        UserTracker.Callback callback = new UserTracker.Callback() { // from class: com.android.systemui.tuner.TunerServiceImpl.1
            @Override // com.android.systemui.settings.UserTracker.Callback
            public final void onUserChanged(int i2, Context context2) {
                TunerServiceImpl tunerServiceImpl = TunerServiceImpl.this;
                tunerServiceImpl.mCurrentUser = i2;
                for (String str : tunerServiceImpl.mTunableLookup.keySet()) {
                    String stringForUser2 = Settings.Secure.getStringForUser(tunerServiceImpl.mContentResolver, str, tunerServiceImpl.mCurrentUser);
                    Iterator it2 = ((Set) tunerServiceImpl.mTunableLookup.get(str)).iterator();
                    while (it2.hasNext()) {
                        ((TunerService.Tunable) it2.next()).onTuningChanged(str, stringForUser2);
                    }
                }
                if (tunerServiceImpl.mListeningUris.size() == 0) {
                    return;
                }
                ContentResolver contentResolver = tunerServiceImpl.mContentResolver;
                Observer observer = tunerServiceImpl.mObserver;
                contentResolver.unregisterContentObserver(observer);
                Iterator it3 = tunerServiceImpl.mListeningUris.keySet().iterator();
                while (it3.hasNext()) {
                    tunerServiceImpl.mContentResolver.registerContentObserver((Uri) it3.next(), false, observer, tunerServiceImpl.mCurrentUser);
                }
            }
        };
        this.mCurrentUserTracker = callback;
        ((UserTrackerImpl) this.mUserTracker).addCallback(callback, new HandlerExecutor(handler));
    }

    @Override // com.android.systemui.tuner.TunerService
    public final void addTunable(TunerService.Tunable tunable, String... strArr) {
        for (final String str : strArr) {
            if (!this.mTunableLookup.containsKey(str)) {
                this.mTunableLookup.put(str, new ArraySet());
            }
            ((Set) this.mTunableLookup.get(str)).add(tunable);
            if (LeakDetector.ENABLED) {
                this.mTunables.add(tunable);
                LeakDetector leakDetector = this.mLeakDetector;
                HashSet hashSet = this.mTunables;
                TrackedCollections trackedCollections = leakDetector.mTrackedCollections;
                if (trackedCollections != null) {
                    synchronized (trackedCollections) {
                        synchronized (trackedCollections) {
                            try {
                                WeakIdentityHashMap weakIdentityHashMap = trackedCollections.mCollections;
                                while (true) {
                                    Reference poll = weakIdentityHashMap.mRefQueue.poll();
                                    if (poll == null) {
                                        break;
                                    } else {
                                        weakIdentityHashMap.mMap.remove(poll);
                                    }
                                }
                                TrackedCollections.CollectionState collectionState = (TrackedCollections.CollectionState) weakIdentityHashMap.mMap.get(new WeakIdentityHashMap.CmpWeakReference(hashSet));
                                if (collectionState == null) {
                                    collectionState = new TrackedCollections.CollectionState();
                                    collectionState.halfwayCount = -1;
                                    collectionState.lastCount = -1;
                                    collectionState.startUptime = SystemClock.uptimeMillis();
                                    WeakIdentityHashMap weakIdentityHashMap2 = trackedCollections.mCollections;
                                    while (true) {
                                        Reference poll2 = weakIdentityHashMap2.mRefQueue.poll();
                                        if (poll2 == null) {
                                            break;
                                        } else {
                                            weakIdentityHashMap2.mMap.remove(poll2);
                                        }
                                    }
                                    weakIdentityHashMap2.mMap.put(new WeakIdentityHashMap.CmpWeakReference(hashSet, weakIdentityHashMap2.mRefQueue), collectionState);
                                }
                                if (collectionState.halfwayCount == -1 && SystemClock.uptimeMillis() - collectionState.startUptime > 1800000) {
                                    collectionState.halfwayCount = collectionState.lastCount;
                                }
                                collectionState.lastCount = hashSet.size();
                                SystemClock.uptimeMillis();
                            } finally {
                            }
                        }
                    }
                }
            }
            Uri uriFor = Settings.Secure.getUriFor(str);
            if (!this.mListeningUris.containsKey(uriFor)) {
                this.mListeningUris.put(uriFor, str);
                this.mContentResolver.registerContentObserver(uriFor, false, this.mObserver, this.mCurrentUser);
            }
            tunable.onTuningChanged(str, (String) DejankUtils.whitelistIpcs(new Supplier() { // from class: com.android.systemui.tuner.TunerServiceImpl$$ExternalSyntheticLambda0
                @Override // java.util.function.Supplier
                public final Object get() {
                    TunerServiceImpl tunerServiceImpl = TunerServiceImpl.this;
                    return Settings.Secure.getStringForUser(tunerServiceImpl.mContentResolver, str, tunerServiceImpl.mCurrentUser);
                }
            }));
        }
    }

    public final void clearAllFromUser(int i) {
        DemoModeController demoModeController = this.mDemoModeController;
        GlobalSettings globalSettings = demoModeController.globalSettings;
        Settings.Global.putString(((GlobalSettingsImpl) globalSettings).mContentResolver, "sysui_tuner_demo_on", String.valueOf(0));
        GlobalSettings globalSettings2 = demoModeController.globalSettings;
        Settings.Global.putString(((GlobalSettingsImpl) globalSettings2).mContentResolver, "sysui_demo_allowed", String.valueOf(0));
        for (String str : this.mTunableLookup.keySet()) {
            if (!ArrayUtils.contains(RESET_EXCEPTION_LIST, str)) {
                Settings.Secure.putStringForUser(this.mContentResolver, str, null, i);
            }
        }
    }

    @Override // com.android.systemui.tuner.TunerService
    public final void removeTunable(TunerService.Tunable tunable) {
        Iterator it = this.mTunableLookup.values().iterator();
        while (it.hasNext()) {
            ((Set) it.next()).remove(tunable);
        }
        if (LeakDetector.ENABLED) {
            this.mTunables.remove(tunable);
        }
    }
}
