package com.android.systemui.statusbar.lockscreen;

import android.app.smartspace.SmartspaceAction;
import android.app.smartspace.SmartspaceConfig;
import android.app.smartspace.SmartspaceManager;
import android.app.smartspace.SmartspaceSession;
import android.app.smartspace.SmartspaceTarget;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.UserInfo;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.UserHandle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import com.android.keyguard.KeyguardUpdateMonitor;
import com.android.keyguard.KeyguardUpdateMonitorCallback;
import com.android.settingslib.Utils;
import com.android.systemui.Dumpable;
import com.android.systemui.animation.ActivityTransitionAnimator;
import com.android.systemui.dump.DumpManager;
import com.android.systemui.flags.FeatureFlags;
import com.android.systemui.flags.Flags;
import com.android.systemui.flags.UnreleasedFlag;
import com.android.systemui.keyguard.WakefulnessLifecycle;
import com.android.systemui.plugins.ActivityStarter;
import com.android.systemui.plugins.BcSmartspaceConfigPlugin;
import com.android.systemui.plugins.BcSmartspaceDataPlugin;
import com.android.systemui.plugins.FalsingManager;
import com.android.systemui.plugins.clocks.WeatherData;
import com.android.systemui.plugins.statusbar.StatusBarStateController;
import com.android.systemui.power.domain.interactor.PowerInteractor;
import com.android.systemui.settings.UserTracker;
import com.android.systemui.settings.UserTrackerImpl;
import com.android.systemui.shared.regionsampling.RegionSampler;
import com.android.systemui.smartspace.ui.binder.SmartspaceViewBinder;
import com.android.systemui.smartspace.ui.viewmodel.SmartspaceViewModel;
import com.android.systemui.statusbar.phone.ConfigurationControllerImpl;
import com.android.systemui.statusbar.phone.KeyguardBypassController;
import com.android.systemui.statusbar.policy.ConfigurationController;
import com.android.systemui.statusbar.policy.DeviceProvisionedController;
import com.android.systemui.statusbar.policy.DeviceProvisionedControllerImpl;
import com.android.systemui.util.Assert;
import com.android.systemui.util.DumpUtilsKt;
import com.android.systemui.util.concurrency.ExecutionImpl;
import com.android.systemui.util.settings.SecureSettings;
import com.android.systemui.util.settings.SecureSettingsImpl;
import com.android.systemui.util.time.SystemClock;
import com.android.systemui.util.time.SystemClockImpl;
import com.android.wm.shell.R;
import com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl;
import com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$30;
import java.io.PrintWriter;
import java.lang.ref.WeakReference;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.Executor;
import java.util.function.Consumer;
import kotlin.Unit;
import kotlin.collections.EmptyList;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class LockscreenSmartspaceController implements Dumpable {
    public final ActivityStarter activityStarter;
    public final Handler bgHandler;
    public final KeyguardBypassController bypassController;
    public final LockscreenSmartspaceController$bypassStateChangedListener$1 bypassStateChangedListener;
    public final LockscreenSmartspaceController$configChangeListener$1 configChangeListener;
    public final BcSmartspaceConfigPlugin configPlugin;
    public final ConfigurationController configurationController;
    public final ContentResolver contentResolver;
    public final Context context;
    public final BcSmartspaceDataPlugin datePlugin;
    public final DeviceProvisionedController deviceProvisionedController;
    public final LockscreenSmartspaceController$deviceProvisionedListener$1 deviceProvisionedListener;
    public final ExecutionImpl execution;
    public final FalsingManager falsingManager;
    public final boolean isDateWeatherDecoupled;
    public final boolean isEnabled;
    public final KeyguardUpdateMonitor keyguardUpdateMonitor;
    public UserHandle managedUserHandle;
    public final BcSmartspaceDataPlugin plugin;
    public final Deque recentSmartspaceData;
    public final Map regionSamplers;
    public final SecureSettings secureSettings;
    public SmartspaceSession session;
    public final LockscreenSmartspaceController$sessionListener$1 sessionListener;
    public final LockscreenSmartspaceController$settingsObserver$1 settingsObserver;
    public boolean showNotifications;
    public boolean showSensitiveContentForCurrentUser;
    public boolean showSensitiveContentForManagedUser;
    public final SmartspaceManager smartspaceManager;
    public final DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$30 smartspaceViewModelFactory;
    public final Set smartspaceViews;
    public final LockscreenSmartspaceController$stateChangeListener$1 stateChangeListener;
    public final StatusBarStateController statusBarStateController;
    public final LockscreenSmartspaceController$statusBarStateListener$1 statusBarStateListener;
    public boolean suppressDisconnects;
    public final SystemClock systemClock;
    public final Executor uiExecutor;
    public final UserTracker userTracker;
    public final LockscreenSmartspaceController$userTrackerCallback$1 userTrackerCallback;
    public final BcSmartspaceDataPlugin weatherPlugin;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class SmartspaceTimeChangedDelegate implements BcSmartspaceDataPlugin.TimeChangedDelegate {
        public final KeyguardUpdateMonitor keyguardUpdateMonitor;
        public LockscreenSmartspaceController$SmartspaceTimeChangedDelegate$register$1 keyguardUpdateMonitorCallback;

        public SmartspaceTimeChangedDelegate(KeyguardUpdateMonitor keyguardUpdateMonitor) {
            this.keyguardUpdateMonitor = keyguardUpdateMonitor;
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r0v1, types: [com.android.keyguard.KeyguardUpdateMonitorCallback, com.android.systemui.statusbar.lockscreen.LockscreenSmartspaceController$SmartspaceTimeChangedDelegate$register$1] */
        @Override // com.android.systemui.plugins.BcSmartspaceDataPlugin.TimeChangedDelegate
        public final void register(final Runnable runnable) {
            if (this.keyguardUpdateMonitorCallback != null) {
                unregister();
            }
            ?? r0 = new KeyguardUpdateMonitorCallback() { // from class: com.android.systemui.statusbar.lockscreen.LockscreenSmartspaceController$SmartspaceTimeChangedDelegate$register$1
                @Override // com.android.keyguard.KeyguardUpdateMonitorCallback
                public final void onTimeChanged() {
                    runnable.run();
                }
            };
            this.keyguardUpdateMonitorCallback = r0;
            this.keyguardUpdateMonitor.registerCallback(r0);
            runnable.run();
        }

        @Override // com.android.systemui.plugins.BcSmartspaceDataPlugin.TimeChangedDelegate
        public final void unregister() {
            this.keyguardUpdateMonitor.removeCallback(this.keyguardUpdateMonitorCallback);
            this.keyguardUpdateMonitorCallback = null;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v23, types: [com.android.systemui.statusbar.lockscreen.LockscreenSmartspaceController$stateChangeListener$1] */
    /* JADX WARN: Type inference failed for: r2v24, types: [com.android.systemui.statusbar.lockscreen.LockscreenSmartspaceController$sessionListener$1] */
    /* JADX WARN: Type inference failed for: r2v25, types: [com.android.systemui.statusbar.lockscreen.LockscreenSmartspaceController$userTrackerCallback$1] */
    /* JADX WARN: Type inference failed for: r2v26, types: [com.android.systemui.statusbar.lockscreen.LockscreenSmartspaceController$settingsObserver$1] */
    /* JADX WARN: Type inference failed for: r2v27, types: [com.android.systemui.statusbar.lockscreen.LockscreenSmartspaceController$configChangeListener$1] */
    /* JADX WARN: Type inference failed for: r2v29, types: [com.android.systemui.statusbar.lockscreen.LockscreenSmartspaceController$deviceProvisionedListener$1, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r6v2, types: [com.android.systemui.statusbar.lockscreen.LockscreenSmartspaceController$bypassStateChangedListener$1] */
    public LockscreenSmartspaceController(Context context, FeatureFlags featureFlags, SmartspaceManager smartspaceManager, ActivityStarter activityStarter, FalsingManager falsingManager, SystemClock systemClock, SecureSettings secureSettings, UserTracker userTracker, ContentResolver contentResolver, ConfigurationController configurationController, StatusBarStateController statusBarStateController, DeviceProvisionedController deviceProvisionedController, KeyguardBypassController keyguardBypassController, KeyguardUpdateMonitor keyguardUpdateMonitor, WakefulnessLifecycle wakefulnessLifecycle, DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$30 daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$30, DumpManager dumpManager, ExecutionImpl executionImpl, Executor executor, Executor executor2, final Handler handler, Handler handler2, Optional optional, Optional optional2, Optional optional3, Optional optional4) {
        this.context = context;
        this.smartspaceManager = smartspaceManager;
        this.activityStarter = activityStarter;
        this.falsingManager = falsingManager;
        this.systemClock = systemClock;
        this.secureSettings = secureSettings;
        this.userTracker = userTracker;
        this.contentResolver = contentResolver;
        this.configurationController = configurationController;
        this.statusBarStateController = statusBarStateController;
        this.deviceProvisionedController = deviceProvisionedController;
        this.bypassController = keyguardBypassController;
        this.keyguardUpdateMonitor = keyguardUpdateMonitor;
        this.smartspaceViewModelFactory = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$30;
        this.execution = executionImpl;
        this.uiExecutor = executor;
        this.bgHandler = handler2;
        BcSmartspaceDataPlugin bcSmartspaceDataPlugin = (BcSmartspaceDataPlugin) optional.orElse(null);
        this.datePlugin = bcSmartspaceDataPlugin;
        BcSmartspaceDataPlugin bcSmartspaceDataPlugin2 = (BcSmartspaceDataPlugin) optional2.orElse(null);
        this.weatherPlugin = bcSmartspaceDataPlugin2;
        BcSmartspaceDataPlugin bcSmartspaceDataPlugin3 = (BcSmartspaceDataPlugin) optional3.orElse(null);
        this.plugin = bcSmartspaceDataPlugin3;
        this.configPlugin = (BcSmartspaceConfigPlugin) optional4.orElse(null);
        this.recentSmartspaceData = new LinkedList();
        this.smartspaceViews = new LinkedHashSet();
        this.regionSamplers = new LinkedHashMap();
        UnreleasedFlag unreleasedFlag = Flags.NULL_FLAG;
        this.stateChangeListener = new View.OnAttachStateChangeListener() { // from class: com.android.systemui.statusbar.lockscreen.LockscreenSmartspaceController$stateChangeListener$1
            /* JADX WARN: Multi-variable type inference failed */
            @Override // android.view.View.OnAttachStateChangeListener
            public final void onViewAttachedToWindow(View view) {
                BcSmartspaceDataPlugin.SmartspaceView smartspaceView = (BcSmartspaceDataPlugin.SmartspaceView) view;
                LockscreenSmartspaceController.this.getClass();
                smartspaceView.setSplitShadeEnabled(false);
                LockscreenSmartspaceController.this.smartspaceViews.add(smartspaceView);
                LockscreenSmartspaceController.this.connectSession();
                LockscreenSmartspaceController.access$updateTextColorFromWallpaper(LockscreenSmartspaceController.this);
                LockscreenSmartspaceController lockscreenSmartspaceController = LockscreenSmartspaceController.this;
                lockscreenSmartspaceController.statusBarStateListener.onDozeAmountChanged(0.0f, lockscreenSmartspaceController.statusBarStateController.getDozeAmount());
                LockscreenSmartspaceController.this.getClass();
            }

            /* JADX WARN: Multi-variable type inference failed */
            @Override // android.view.View.OnAttachStateChangeListener
            public final void onViewDetachedFromWindow(View view) {
                BcSmartspaceDataPlugin.SmartspaceView smartspaceView = (BcSmartspaceDataPlugin.SmartspaceView) view;
                LockscreenSmartspaceController.this.smartspaceViews.remove(smartspaceView);
                LockscreenSmartspaceController.this.regionSamplers.remove(smartspaceView);
                if (LockscreenSmartspaceController.this.smartspaceViews.isEmpty()) {
                    LockscreenSmartspaceController.this.disconnect();
                }
            }
        };
        this.sessionListener = new SmartspaceSession.OnTargetsAvailableListener() { // from class: com.android.systemui.statusbar.lockscreen.LockscreenSmartspaceController$sessionListener$1
            public final void onTargetsAvailable(List list) {
                final WeatherData weatherData;
                Object obj;
                Bundle extras;
                LockscreenSmartspaceController.this.execution.assertIsMainThread();
                BcSmartspaceDataPlugin bcSmartspaceDataPlugin4 = LockscreenSmartspaceController.this.weatherPlugin;
                if (bcSmartspaceDataPlugin4 != null) {
                    bcSmartspaceDataPlugin4.onTargetsAvailable(list);
                }
                ((SystemClockImpl) LockscreenSmartspaceController.this.systemClock).getClass();
                Instant ofEpochMilli = Instant.ofEpochMilli(System.currentTimeMillis());
                Iterator it = list.iterator();
                while (true) {
                    weatherData = null;
                    if (!it.hasNext()) {
                        obj = null;
                        break;
                    }
                    obj = it.next();
                    SmartspaceTarget smartspaceTarget = (SmartspaceTarget) obj;
                    if (smartspaceTarget.getFeatureType() == 1 && ofEpochMilli.isAfter(Instant.ofEpochMilli(smartspaceTarget.getCreationTimeMillis())) && ofEpochMilli.isBefore(Instant.ofEpochMilli(smartspaceTarget.getExpiryTimeMillis()))) {
                        break;
                    }
                }
                SmartspaceTarget smartspaceTarget2 = (SmartspaceTarget) obj;
                if (smartspaceTarget2 != null) {
                    SmartspaceAction headerAction = smartspaceTarget2.getHeaderAction();
                    final Intent intent = headerAction != null ? headerAction.getIntent() : null;
                    SmartspaceAction baseAction = smartspaceTarget2.getBaseAction();
                    if (baseAction != null && (extras = baseAction.getExtras()) != null) {
                        final LockscreenSmartspaceController lockscreenSmartspaceController = LockscreenSmartspaceController.this;
                        weatherData = WeatherData.Companion.fromBundle(extras, new Function1() { // from class: com.android.systemui.statusbar.lockscreen.LockscreenSmartspaceController$sessionListener$1$weatherData$1$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public final Object invoke(Object obj2) {
                                if (!LockscreenSmartspaceController.this.falsingManager.isFalseTap(1)) {
                                    LockscreenSmartspaceController.this.activityStarter.startActivity(intent, true, (ActivityTransitionAnimator.Controller) null, false);
                                }
                                return Unit.INSTANCE;
                            }
                        });
                    }
                    if (weatherData != null) {
                        final KeyguardUpdateMonitor keyguardUpdateMonitor2 = LockscreenSmartspaceController.this.keyguardUpdateMonitor;
                        keyguardUpdateMonitor2.mHandler.post(new Runnable() { // from class: com.android.keyguard.KeyguardUpdateMonitor$$ExternalSyntheticLambda9
                            @Override // java.lang.Runnable
                            public final void run() {
                                KeyguardUpdateMonitor keyguardUpdateMonitor3 = KeyguardUpdateMonitor.this;
                                WeatherData weatherData2 = weatherData;
                                int i = KeyguardUpdateMonitor.BIOMETRIC_HELP_FINGERPRINT_NOT_RECOGNIZED;
                                keyguardUpdateMonitor3.getClass();
                                Assert.isMainThread();
                                for (int i2 = 0; i2 < keyguardUpdateMonitor3.mCallbacks.size(); i2++) {
                                    KeyguardUpdateMonitorCallback keyguardUpdateMonitorCallback = (KeyguardUpdateMonitorCallback) ((WeakReference) keyguardUpdateMonitor3.mCallbacks.get(i2)).get();
                                    if (keyguardUpdateMonitorCallback != null) {
                                        keyguardUpdateMonitorCallback.onWeatherDataChanged(weatherData2);
                                    }
                                }
                            }
                        });
                    }
                }
                LockscreenSmartspaceController lockscreenSmartspaceController2 = LockscreenSmartspaceController.this;
                ArrayList arrayList = new ArrayList();
                for (Object obj2 : list) {
                    SmartspaceTarget smartspaceTarget3 = (SmartspaceTarget) obj2;
                    if (!lockscreenSmartspaceController2.isDateWeatherDecoupled || smartspaceTarget3.getFeatureType() != 1) {
                        if (lockscreenSmartspaceController2.showNotifications) {
                            UserHandle userHandle = smartspaceTarget3.getUserHandle();
                            UserTrackerImpl userTrackerImpl = (UserTrackerImpl) lockscreenSmartspaceController2.userTracker;
                            if (Intrinsics.areEqual(userHandle, userTrackerImpl.getUserHandle())) {
                                if (smartspaceTarget3.isSensitive() && !lockscreenSmartspaceController2.showSensitiveContentForCurrentUser) {
                                }
                                arrayList.add(obj2);
                            } else if (Intrinsics.areEqual(userHandle, lockscreenSmartspaceController2.managedUserHandle)) {
                                if (userTrackerImpl.getUserHandle().getIdentifier() == 0) {
                                    if (smartspaceTarget3.isSensitive() && !lockscreenSmartspaceController2.showSensitiveContentForManagedUser) {
                                    }
                                    arrayList.add(obj2);
                                }
                            }
                        } else if (smartspaceTarget3.getFeatureType() == 1) {
                            arrayList.add(obj2);
                        }
                    }
                }
                LockscreenSmartspaceController lockscreenSmartspaceController3 = LockscreenSmartspaceController.this;
                synchronized (lockscreenSmartspaceController3.recentSmartspaceData) {
                    ((LinkedList) lockscreenSmartspaceController3.recentSmartspaceData).offerLast(arrayList);
                    if (((LinkedList) lockscreenSmartspaceController3.recentSmartspaceData).size() > 5) {
                        ((LinkedList) lockscreenSmartspaceController3.recentSmartspaceData).pollFirst();
                    }
                }
                BcSmartspaceDataPlugin bcSmartspaceDataPlugin5 = LockscreenSmartspaceController.this.plugin;
                if (bcSmartspaceDataPlugin5 != null) {
                    bcSmartspaceDataPlugin5.onTargetsAvailable(arrayList);
                }
            }
        };
        this.userTrackerCallback = new UserTracker.Callback() { // from class: com.android.systemui.statusbar.lockscreen.LockscreenSmartspaceController$userTrackerCallback$1
            @Override // com.android.systemui.settings.UserTracker.Callback
            public final void onUserChanged(int i, Context context2) {
                LockscreenSmartspaceController lockscreenSmartspaceController = LockscreenSmartspaceController.this;
                lockscreenSmartspaceController.execution.assertIsMainThread();
                lockscreenSmartspaceController.reloadSmartspace();
            }
        };
        this.settingsObserver = new ContentObserver(handler) { // from class: com.android.systemui.statusbar.lockscreen.LockscreenSmartspaceController$settingsObserver$1
            @Override // android.database.ContentObserver
            public final void onChange(boolean z, Uri uri) {
                LockscreenSmartspaceController.this.execution.assertIsMainThread();
                LockscreenSmartspaceController.this.reloadSmartspace();
            }
        };
        this.configChangeListener = new ConfigurationController.ConfigurationListener() { // from class: com.android.systemui.statusbar.lockscreen.LockscreenSmartspaceController$configChangeListener$1
            @Override // com.android.systemui.statusbar.policy.ConfigurationController.ConfigurationListener
            public final void onThemeChanged() {
                LockscreenSmartspaceController lockscreenSmartspaceController = LockscreenSmartspaceController.this;
                lockscreenSmartspaceController.execution.assertIsMainThread();
                LockscreenSmartspaceController.access$updateTextColorFromWallpaper(lockscreenSmartspaceController);
            }
        };
        this.statusBarStateListener = new LockscreenSmartspaceController$statusBarStateListener$1(this);
        ?? r2 = new DeviceProvisionedController.DeviceProvisionedListener() { // from class: com.android.systemui.statusbar.lockscreen.LockscreenSmartspaceController$deviceProvisionedListener$1
            @Override // com.android.systemui.statusbar.policy.DeviceProvisionedController.DeviceProvisionedListener
            public final void onDeviceProvisionedChanged() {
                LockscreenSmartspaceController.this.connectSession();
            }

            @Override // com.android.systemui.statusbar.policy.DeviceProvisionedController.DeviceProvisionedListener
            public final void onUserSetupChanged() {
                LockscreenSmartspaceController.this.connectSession();
            }
        };
        this.deviceProvisionedListener = r2;
        this.bypassStateChangedListener = new KeyguardBypassController.OnBypassStateChangedListener() { // from class: com.android.systemui.statusbar.lockscreen.LockscreenSmartspaceController$bypassStateChangedListener$1
            @Override // com.android.systemui.statusbar.phone.KeyguardBypassController.OnBypassStateChangedListener
            public final void onBypassStateChanged(boolean z) {
                LockscreenSmartspaceController lockscreenSmartspaceController = LockscreenSmartspaceController.this;
                boolean bypassEnabled = lockscreenSmartspaceController.bypassController.getBypassEnabled();
                Iterator it = lockscreenSmartspaceController.smartspaceViews.iterator();
                while (it.hasNext()) {
                    ((BcSmartspaceDataPlugin.SmartspaceView) it.next()).setKeyguardBypassEnabled(bypassEnabled);
                }
            }
        };
        ((DeviceProvisionedControllerImpl) deviceProvisionedController).addCallback(r2);
        dumpManager.registerDumpable(this);
        boolean z = false;
        this.isEnabled = bcSmartspaceDataPlugin3 != null;
        if (bcSmartspaceDataPlugin != null && bcSmartspaceDataPlugin2 != null) {
            z = true;
        }
        this.isDateWeatherDecoupled = z;
    }

    public static final void access$updateTextColorFromWallpaper(LockscreenSmartspaceController lockscreenSmartspaceController) {
        lockscreenSmartspaceController.getClass();
        int colorAttrDefaultColor = Utils.getColorAttrDefaultColor(R.attr.wallpaperTextColor, 0, lockscreenSmartspaceController.context);
        Iterator it = lockscreenSmartspaceController.smartspaceViews.iterator();
        while (it.hasNext()) {
            ((BcSmartspaceDataPlugin.SmartspaceView) it.next()).setPrimaryTextColor(colorAttrDefaultColor);
        }
    }

    public final View buildAndConnectDateView(ViewGroup viewGroup) {
        this.execution.assertIsMainThread();
        if (!this.isEnabled) {
            throw new RuntimeException("Cannot build view when not enabled");
        }
        if (!this.isDateWeatherDecoupled) {
            throw new RuntimeException("Cannot build date view when not decoupled");
        }
        View buildView = buildView("date_view", viewGroup, this.datePlugin, null);
        connectSession();
        return buildView;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final View buildView(String str, ViewGroup viewGroup, BcSmartspaceDataPlugin bcSmartspaceDataPlugin, BcSmartspaceConfigPlugin bcSmartspaceConfigPlugin) {
        if (bcSmartspaceDataPlugin == null) {
            return null;
        }
        BcSmartspaceDataPlugin.SmartspaceView view = bcSmartspaceDataPlugin.getView(viewGroup);
        if (bcSmartspaceConfigPlugin != null) {
            view.registerConfigProvider(bcSmartspaceConfigPlugin);
        }
        view.setBgHandler(this.bgHandler);
        view.setUiSurface(BcSmartspaceDataPlugin.UI_SURFACE_LOCK_SCREEN_AOD);
        view.setTimeChangedDelegate(new SmartspaceTimeChangedDelegate(this.keyguardUpdateMonitor));
        view.registerDataProvider(bcSmartspaceDataPlugin);
        view.setIntentStarter(new LockscreenSmartspaceController$buildView$2(this, 0));
        view.setFalsingManager(this.falsingManager);
        view.setKeyguardBypassEnabled(this.bypassController.getBypassEnabled());
        View view2 = (View) view;
        view2.setTag(R.id.tag_smartspace_view, new Object());
        view2.addOnAttachStateChangeListener(this.stateChangeListener);
        SmartspaceViewBinder.bind(view, new SmartspaceViewModel((PowerInteractor) ((DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) this.smartspaceViewModelFactory.this$0.wMComponentImpl).powerInteractorProvider.get(), str));
        return view2;
    }

    public final void connectSession() {
        if (this.smartspaceManager == null) {
            return;
        }
        BcSmartspaceDataPlugin bcSmartspaceDataPlugin = this.plugin;
        BcSmartspaceDataPlugin bcSmartspaceDataPlugin2 = this.weatherPlugin;
        BcSmartspaceDataPlugin bcSmartspaceDataPlugin3 = this.datePlugin;
        if ((bcSmartspaceDataPlugin3 == null && bcSmartspaceDataPlugin2 == null && bcSmartspaceDataPlugin == null) || this.session != null || this.smartspaceViews.isEmpty()) {
            return;
        }
        DeviceProvisionedControllerImpl deviceProvisionedControllerImpl = (DeviceProvisionedControllerImpl) this.deviceProvisionedController;
        if (deviceProvisionedControllerImpl.deviceProvisioned.get() && deviceProvisionedControllerImpl.isCurrentUserSetup()) {
            SmartspaceSession createSmartspaceSession = this.smartspaceManager.createSmartspaceSession(new SmartspaceConfig.Builder(this.context, BcSmartspaceDataPlugin.UI_SURFACE_LOCK_SCREEN_AOD).build());
            Log.d("LockscreenSmartspaceController", "Starting smartspace session for lockscreen");
            createSmartspaceSession.addOnTargetsAvailableListener(this.uiExecutor, this.sessionListener);
            this.session = createSmartspaceSession;
            deviceProvisionedControllerImpl.removeCallback(this.deviceProvisionedListener);
            ((UserTrackerImpl) this.userTracker).addCallback(this.userTrackerCallback, this.uiExecutor);
            ContentResolver contentResolver = this.contentResolver;
            SecureSettingsImpl secureSettingsImpl = (SecureSettingsImpl) this.secureSettings;
            secureSettingsImpl.getClass();
            Uri uriFor = Settings.Secure.getUriFor("lock_screen_allow_private_notifications");
            LockscreenSmartspaceController$settingsObserver$1 lockscreenSmartspaceController$settingsObserver$1 = this.settingsObserver;
            contentResolver.registerContentObserver(uriFor, true, lockscreenSmartspaceController$settingsObserver$1, -1);
            ContentResolver contentResolver2 = this.contentResolver;
            secureSettingsImpl.getClass();
            contentResolver2.registerContentObserver(Settings.Secure.getUriFor("lock_screen_show_notifications"), true, lockscreenSmartspaceController$settingsObserver$1, -1);
            ((ConfigurationControllerImpl) this.configurationController).addCallback(this.configChangeListener);
            this.statusBarStateController.addCallback(this.statusBarStateListener);
            LockscreenSmartspaceController$bypassStateChangedListener$1 lockscreenSmartspaceController$bypassStateChangedListener$1 = this.bypassStateChangedListener;
            KeyguardBypassController keyguardBypassController = this.bypassController;
            keyguardBypassController.registerOnBypassStateChangedListener(lockscreenSmartspaceController$bypassStateChangedListener$1);
            if (bcSmartspaceDataPlugin3 != null) {
                bcSmartspaceDataPlugin3.registerSmartspaceEventNotifier(new LockscreenSmartspaceController$buildView$2(this, 1));
            }
            if (bcSmartspaceDataPlugin2 != null) {
                bcSmartspaceDataPlugin2.registerSmartspaceEventNotifier(new LockscreenSmartspaceController$buildView$2(this, 2));
            }
            if (bcSmartspaceDataPlugin != null) {
                bcSmartspaceDataPlugin.registerSmartspaceEventNotifier(new LockscreenSmartspaceController$buildView$2(this, 3));
            }
            boolean bypassEnabled = keyguardBypassController.getBypassEnabled();
            Iterator it = this.smartspaceViews.iterator();
            while (it.hasNext()) {
                ((BcSmartspaceDataPlugin.SmartspaceView) it.next()).setKeyguardBypassEnabled(bypassEnabled);
            }
            reloadSmartspace();
        }
    }

    public final void disconnect() {
        if (this.smartspaceViews.isEmpty() && !this.suppressDisconnects) {
            this.execution.assertIsMainThread();
            SmartspaceSession smartspaceSession = this.session;
            if (smartspaceSession == null) {
                return;
            }
            smartspaceSession.removeOnTargetsAvailableListener(this.sessionListener);
            smartspaceSession.close();
            ((UserTrackerImpl) this.userTracker).removeCallback(this.userTrackerCallback);
            this.contentResolver.unregisterContentObserver(this.settingsObserver);
            ((ConfigurationControllerImpl) this.configurationController).removeCallback(this.configChangeListener);
            this.statusBarStateController.removeCallback(this.statusBarStateListener);
            this.bypassController.unregisterOnBypassStateChangedListener(this.bypassStateChangedListener);
            this.session = null;
            BcSmartspaceDataPlugin bcSmartspaceDataPlugin = this.datePlugin;
            if (bcSmartspaceDataPlugin != null) {
                bcSmartspaceDataPlugin.registerSmartspaceEventNotifier(null);
            }
            BcSmartspaceDataPlugin bcSmartspaceDataPlugin2 = this.weatherPlugin;
            if (bcSmartspaceDataPlugin2 != null) {
                bcSmartspaceDataPlugin2.registerSmartspaceEventNotifier(null);
            }
            if (bcSmartspaceDataPlugin2 != null) {
                bcSmartspaceDataPlugin2.onTargetsAvailable(EmptyList.INSTANCE);
            }
            BcSmartspaceDataPlugin bcSmartspaceDataPlugin3 = this.plugin;
            if (bcSmartspaceDataPlugin3 != null) {
                bcSmartspaceDataPlugin3.registerSmartspaceEventNotifier(null);
            }
            if (bcSmartspaceDataPlugin3 != null) {
                bcSmartspaceDataPlugin3.onTargetsAvailable(EmptyList.INSTANCE);
            }
            Log.d("LockscreenSmartspaceController", "Ended smartspace session for lockscreen");
        }
    }

    @Override // com.android.systemui.Dumpable
    public final void dump(final PrintWriter printWriter, String[] strArr) {
        PrintWriter asIndenting = DumpUtilsKt.asIndenting(printWriter);
        Collection values = this.regionSamplers.values();
        asIndenting.append("Region Samplers").append((CharSequence) ": ").println(values.size());
        asIndenting.increaseIndent();
        try {
            Iterator it = values.iterator();
            if (it.hasNext()) {
                ((RegionSampler) it.next()).dump(asIndenting);
                throw null;
            }
            asIndenting.decreaseIndent();
            printWriter.println("Recent BC Smartspace Targets (most recent first)");
            synchronized (this.recentSmartspaceData) {
                if (((LinkedList) this.recentSmartspaceData).size() == 0) {
                    printWriter.println("   No data\n");
                } else {
                    ((LinkedList) this.recentSmartspaceData).descendingIterator().forEachRemaining(new Consumer() { // from class: com.android.systemui.statusbar.lockscreen.LockscreenSmartspaceController$dump$2$1
                        @Override // java.util.function.Consumer
                        public final void accept(Object obj) {
                            List<SmartspaceTarget> list = (List) obj;
                            printWriter.println("   Number of targets: " + list.size());
                            for (SmartspaceTarget smartspaceTarget : list) {
                                printWriter.println("      " + smartspaceTarget);
                            }
                            printWriter.println();
                        }
                    });
                }
            }
        } catch (Throwable th) {
            asIndenting.decreaseIndent();
            throw th;
        }
    }

    public final void reloadSmartspace() {
        UserHandle userHandle;
        UserTrackerImpl userTrackerImpl = (UserTrackerImpl) this.userTracker;
        int userId = userTrackerImpl.getUserId();
        SecureSettings secureSettings = this.secureSettings;
        this.showNotifications = secureSettings.getIntForUser("lock_screen_show_notifications", 0, userId) == 1;
        this.showSensitiveContentForCurrentUser = secureSettings.getIntForUser("lock_screen_allow_private_notifications", 0, userTrackerImpl.getUserId()) == 1;
        Iterator it = userTrackerImpl.getUserProfiles().iterator();
        while (true) {
            if (!it.hasNext()) {
                userHandle = null;
                break;
            }
            UserInfo userInfo = (UserInfo) it.next();
            if (userInfo.isManagedProfile()) {
                userHandle = userInfo.getUserHandle();
                break;
            }
        }
        this.managedUserHandle = userHandle;
        Integer valueOf = userHandle != null ? Integer.valueOf(userHandle.getIdentifier()) : null;
        if (valueOf != null) {
            this.showSensitiveContentForManagedUser = secureSettings.getIntForUser("lock_screen_allow_private_notifications", 0, valueOf.intValue()) == 1;
        }
        SmartspaceSession smartspaceSession = this.session;
        if (smartspaceSession != null) {
            smartspaceSession.requestSmartspaceUpdate();
        }
    }

    public static /* synthetic */ void getSmartspaceViews$annotations() {
    }
}
