package com.android.systemui.recents;

import android.R;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.content.pm.ResolveInfo;
import android.graphics.Region;
import android.hardware.input.InputManagerGlobal;
import android.os.Binder;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import android.os.Parcel;
import android.os.Process;
import android.os.RemoteException;
import android.os.SystemClock;
import android.os.UserHandle;
import android.os.UserManager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.accessibility.AccessibilityManager;
import android.view.inputmethod.InputMethodManager;
import com.android.app.viewcapture.data.ViewNode;
import com.android.internal.app.AssistUtils;
import com.android.internal.app.IVoiceInteractionSessionListener;
import com.android.internal.logging.UiEventLogger;
import com.android.internal.util.ScreenshotHelper;
import com.android.internal.util.ScreenshotRequest;
import com.android.systemui.Dumpable;
import com.android.systemui.broadcast.BroadcastDispatcher;
import com.android.systemui.dump.DumpManager;
import com.android.systemui.education.dagger.NoOpKeyboardTouchpadEduStatsInteractor;
import com.android.systemui.keyguard.KeyguardUnlockAnimationController;
import com.android.systemui.keyguard.WakefulnessLifecycle;
import com.android.systemui.model.SysUiState;
import com.android.systemui.navigationbar.NavigationBarControllerImpl;
import com.android.systemui.navigationbar.NavigationModeController;
import com.android.systemui.navigationbar.views.NavigationBar;
import com.android.systemui.navigationbar.views.NavigationBarView;
import com.android.systemui.navigationbar.views.buttons.KeyButtonView;
import com.android.systemui.recents.OverviewProxyService;
import com.android.systemui.settings.DisplayTracker;
import com.android.systemui.settings.UserTracker;
import com.android.systemui.settings.UserTrackerImpl;
import com.android.systemui.shade.NotificationShadeWindowControllerImpl;
import com.android.systemui.shade.ShadeViewController;
import com.android.systemui.shared.recents.IOverviewProxy;
import com.android.systemui.shared.recents.ISystemUiProxy;
import com.android.systemui.statusbar.CommandQueue;
import com.android.systemui.statusbar.NotificationShadeWindowController;
import com.android.systemui.statusbar.phone.StatusBarWindowCallback;
import com.android.systemui.statusbar.policy.CallbackController;
import com.android.systemui.unfold.progress.UnfoldTransitionProgressForwarder;
import com.android.wm.shell.shared.desktopmode.DesktopModeStatus;
import com.android.wm.shell.sysui.ShellInterface;
import dagger.Lazy;
import dagger.internal.DelegateFactory;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.Executor;
import java.util.function.Consumer;
import java.util.function.Supplier;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class OverviewProxyService implements CallbackController, NavigationModeController.ModeChangedListener, Dumpable {
    static final String ACTION_QUICKSTEP = "android.intent.action.QUICKSTEP_SERVICE";
    public Region mActiveNavBarRegion;
    public boolean mBound;
    public final CommandQueue mCommandQueue;
    public int mConnectionBackoffAttempts;
    public final List mConnectionCallbacks;
    public final OverviewProxyService$$ExternalSyntheticLambda0 mConnectionRunnable;
    public final Context mContext;
    public int mCurrentBoundedUserId;
    public final OverviewProxyService$$ExternalSyntheticLambda0 mDeferredConnectionCallback;
    public final DisplayTracker mDisplayTracker;
    public final Handler mHandler;
    public long mInputFocusTransferStartMillis;
    public float mInputFocusTransferStartY;
    public boolean mInputFocusTransferStarted;
    public boolean mIsEnabled;
    public final boolean mIsSystemOrVisibleBgUser;
    public final NoOpKeyboardTouchpadEduStatsInteractor mKeyboardTouchpadEduStatsInteractor;
    public final AnonymousClass2 mLauncherStateChangedReceiver;
    public final Lazy mNavBarControllerLazy;
    public int mNavBarMode;
    public IOverviewProxy mOverviewProxy;
    public final AnonymousClass4 mOverviewServiceConnection;
    public final OverviewProxyService$$ExternalSyntheticLambda3 mOverviewServiceDeathRcpt;
    public final Intent mQuickStepIntent;
    public final ComponentName mRecentsComponentName;
    public final DelegateFactory mSceneInteractor;
    public final ScreenPinningRequest mScreenPinningRequest;
    public final ScreenshotHelper mScreenshotHelper;
    public final DelegateFactory mShadeInteractor;
    public final Lazy mShadeViewControllerLazy;
    public final ShellInterface mShellInterface;
    public final NotificationShadeWindowController mStatusBarWinController;
    public final StatusBarWindowCallback mStatusBarWindowCallback;
    public ISystemUiProxy mSysUiProxy;
    public final SysUiState mSysUiState;
    public final KeyguardUnlockAnimationController mSysuiUnlockAnimationController;
    public final UiEventLogger mUiEventLogger;
    public final Optional mUnfoldTransitionProgressForwarder;
    public final UserTracker.Callback mUserChangedCallback;
    public final AnonymousClass2 mUserEventReceiver;
    public final UserTracker mUserTracker;
    public final AnonymousClass5 mVoiceInteractionSessionListener;
    public final AnonymousClass8 mWakefulnessLifecycleObserver;

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r10v1, types: [com.android.systemui.recents.OverviewProxyService$4] */
    /* JADX WARN: Type inference failed for: r11v0, types: [com.android.systemui.recents.OverviewProxyService$$ExternalSyntheticLambda3] */
    /* JADX WARN: Type inference failed for: r9v2, types: [android.content.BroadcastReceiver, com.android.systemui.recents.OverviewProxyService$2] */
    public OverviewProxyService(Context context, Executor executor, CommandQueue commandQueue, ShellInterface shellInterface, Lazy lazy, Lazy lazy2, ScreenPinningRequest screenPinningRequest, NavigationModeController navigationModeController, NotificationShadeWindowController notificationShadeWindowController, final SysUiState sysUiState, DelegateFactory delegateFactory, DelegateFactory delegateFactory2, UserTracker userTracker, UserManager userManager, WakefulnessLifecycle wakefulnessLifecycle, UiEventLogger uiEventLogger, DisplayTracker displayTracker, KeyguardUnlockAnimationController keyguardUnlockAnimationController, AssistUtils assistUtils, DumpManager dumpManager, Optional optional, BroadcastDispatcher broadcastDispatcher) {
        IVoiceInteractionSessionListener iVoiceInteractionSessionListener;
        NoOpKeyboardTouchpadEduStatsInteractor noOpKeyboardTouchpadEduStatsInteractor = NoOpKeyboardTouchpadEduStatsInteractor.INSTANCE;
        this.mConnectionRunnable = new OverviewProxyService$$ExternalSyntheticLambda0(this, 0);
        this.mConnectionCallbacks = new ArrayList();
        this.mCurrentBoundedUserId = -1;
        this.mNavBarMode = 0;
        this.mSysUiProxy = new AnonymousClass1();
        this.mDeferredConnectionCallback = new OverviewProxyService$$ExternalSyntheticLambda0(this, 1);
        final int i = 0;
        BroadcastReceiver broadcastReceiver = new BroadcastReceiver(this) { // from class: com.android.systemui.recents.OverviewProxyService.2
            public final /* synthetic */ OverviewProxyService this$0;

            {
                this.this$0 = this;
            }

            @Override // android.content.BroadcastReceiver
            public final void onReceive(Context context2, Intent intent) {
                switch (i) {
                    case 0:
                        if (Objects.equals(intent.getAction(), "android.intent.action.USER_UNLOCKED")) {
                            OverviewProxyService overviewProxyService = this.this$0;
                            if (overviewProxyService.mOverviewProxy == null) {
                                overviewProxyService.startConnectionToCurrentUser();
                                break;
                            }
                        }
                        break;
                    default:
                        if (Objects.equals(intent.getAction(), "android.intent.action.PACKAGE_ADDED")) {
                            this.this$0.updateEnabledAndBinding();
                            break;
                        } else {
                            String[] stringArrayExtra = intent.getStringArrayExtra("android.intent.extra.changed_component_name_list");
                            if (stringArrayExtra != null) {
                                ResolveInfo resolveService = context2.getPackageManager().resolveService(new Intent(OverviewProxyService.ACTION_QUICKSTEP), 0);
                                if (resolveService != null) {
                                    String str = resolveService.serviceInfo.name;
                                    for (String str2 : stringArrayExtra) {
                                        if (str.equals(str2)) {
                                            Log.i("OverviewProxyService", "Rebinding for component [" + str2 + "] change");
                                            this.this$0.updateEnabledAndBinding();
                                            break;
                                        }
                                    }
                                    break;
                                }
                            }
                        }
                        break;
                }
            }
        };
        final int i2 = 1;
        ?? r9 = new BroadcastReceiver(this) { // from class: com.android.systemui.recents.OverviewProxyService.2
            public final /* synthetic */ OverviewProxyService this$0;

            {
                this.this$0 = this;
            }

            @Override // android.content.BroadcastReceiver
            public final void onReceive(Context context2, Intent intent) {
                switch (i2) {
                    case 0:
                        if (Objects.equals(intent.getAction(), "android.intent.action.USER_UNLOCKED")) {
                            OverviewProxyService overviewProxyService = this.this$0;
                            if (overviewProxyService.mOverviewProxy == null) {
                                overviewProxyService.startConnectionToCurrentUser();
                                break;
                            }
                        }
                        break;
                    default:
                        if (Objects.equals(intent.getAction(), "android.intent.action.PACKAGE_ADDED")) {
                            this.this$0.updateEnabledAndBinding();
                            break;
                        } else {
                            String[] stringArrayExtra = intent.getStringArrayExtra("android.intent.extra.changed_component_name_list");
                            if (stringArrayExtra != null) {
                                ResolveInfo resolveService = context2.getPackageManager().resolveService(new Intent(OverviewProxyService.ACTION_QUICKSTEP), 0);
                                if (resolveService != null) {
                                    String str = resolveService.serviceInfo.name;
                                    for (String str2 : stringArrayExtra) {
                                        if (str.equals(str2)) {
                                            Log.i("OverviewProxyService", "Rebinding for component [" + str2 + "] change");
                                            this.this$0.updateEnabledAndBinding();
                                            break;
                                        }
                                    }
                                    break;
                                }
                            }
                        }
                        break;
                }
            }
        };
        this.mLauncherStateChangedReceiver = r9;
        this.mOverviewServiceConnection = new ServiceConnection() { // from class: com.android.systemui.recents.OverviewProxyService.4
            @Override // android.content.ServiceConnection
            public final void onBindingDied(ComponentName componentName) {
                Log.w("OverviewProxyService", "Binding died of '" + componentName + "', try reconnecting");
                OverviewProxyService overviewProxyService = OverviewProxyService.this;
                overviewProxyService.mCurrentBoundedUserId = -1;
                overviewProxyService.retryConnectionWithBackoff();
            }

            @Override // android.content.ServiceConnection
            public final void onNullBinding(ComponentName componentName) {
                Log.w("OverviewProxyService", "Null binding of '" + componentName + "', try reconnecting");
                OverviewProxyService overviewProxyService = OverviewProxyService.this;
                overviewProxyService.mCurrentBoundedUserId = -1;
                overviewProxyService.retryConnectionWithBackoff();
            }

            /* JADX WARN: Multi-variable type inference failed */
            /* JADX WARN: Type inference failed for: r3v8, types: [com.android.systemui.shared.recents.IOverviewProxy] */
            @Override // android.content.ServiceConnection
            public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                IOverviewProxy.Stub.Proxy proxy;
                Log.d("OverviewProxyService", "Overview proxy service connected");
                OverviewProxyService overviewProxyService = OverviewProxyService.this;
                overviewProxyService.mConnectionBackoffAttempts = 0;
                overviewProxyService.mHandler.removeCallbacks(overviewProxyService.mDeferredConnectionCallback);
                try {
                    iBinder.linkToDeath(OverviewProxyService.this.mOverviewServiceDeathRcpt, 0);
                    OverviewProxyService overviewProxyService2 = OverviewProxyService.this;
                    overviewProxyService2.mCurrentBoundedUserId = ((UserTrackerImpl) overviewProxyService2.mUserTracker).getUserId();
                    OverviewProxyService overviewProxyService3 = OverviewProxyService.this;
                    int i3 = IOverviewProxy.Stub.$r8$clinit;
                    IInterface queryLocalInterface = iBinder.queryLocalInterface("com.android.systemui.shared.recents.IOverviewProxy");
                    if (queryLocalInterface == null || !(queryLocalInterface instanceof IOverviewProxy)) {
                        IOverviewProxy.Stub.Proxy proxy2 = new IOverviewProxy.Stub.Proxy();
                        proxy2.mRemote = iBinder;
                        proxy = proxy2;
                    } else {
                        proxy = (IOverviewProxy) queryLocalInterface;
                    }
                    overviewProxyService3.mOverviewProxy = proxy;
                    final Bundle bundle = new Bundle();
                    AnonymousClass1 anonymousClass1 = (AnonymousClass1) OverviewProxyService.this.mSysUiProxy;
                    anonymousClass1.getClass();
                    bundle.putBinder("extra_sysui_proxy", anonymousClass1);
                    KeyguardUnlockAnimationController keyguardUnlockAnimationController2 = OverviewProxyService.this.mSysuiUnlockAnimationController;
                    keyguardUnlockAnimationController2.getClass();
                    bundle.putBinder("unlock_animation", keyguardUnlockAnimationController2);
                    OverviewProxyService.this.mUnfoldTransitionProgressForwarder.ifPresent(new Consumer() { // from class: com.android.systemui.recents.OverviewProxyService$4$$ExternalSyntheticLambda0
                        @Override // java.util.function.Consumer
                        public final void accept(Object obj) {
                            Bundle bundle2 = bundle;
                            UnfoldTransitionProgressForwarder unfoldTransitionProgressForwarder = (UnfoldTransitionProgressForwarder) obj;
                            unfoldTransitionProgressForwarder.getClass();
                            bundle2.putBinder("extra_unfold_animation", unfoldTransitionProgressForwarder);
                        }
                    });
                    OverviewProxyService.this.mShellInterface.createExternalInterfaces(bundle);
                    try {
                        Log.d("OverviewProxyService", "OverviewProxyService connected, initializing overview proxy");
                        IOverviewProxy.Stub.Proxy proxy3 = (IOverviewProxy.Stub.Proxy) OverviewProxyService.this.mOverviewProxy;
                        Parcel obtain = Parcel.obtain(proxy3.mRemote);
                        try {
                            obtain.writeInterfaceToken("com.android.systemui.shared.recents.IOverviewProxy");
                            obtain.writeTypedObject(bundle, 0);
                            proxy3.mRemote.transact(13, obtain, null, 1);
                            obtain.recycle();
                        } catch (Throwable th) {
                            obtain.recycle();
                            throw th;
                        }
                    } catch (RemoteException e) {
                        OverviewProxyService.this.mCurrentBoundedUserId = -1;
                        Log.e("OverviewProxyService", "Failed to call onInitialize()", e);
                    }
                    OverviewProxyService.this.dispatchNavButtonBounds();
                    OverviewProxyService overviewProxyService4 = OverviewProxyService.this;
                    Lazy lazy3 = overviewProxyService4.mNavBarControllerLazy;
                    NavigationBar defaultNavigationBar = ((NavigationBarControllerImpl) lazy3.get()).getDefaultNavigationBar();
                    NavigationBarView navigationBarView = ((NavigationBarControllerImpl) lazy3.get()).getNavigationBarView(overviewProxyService4.mContext.getDisplayId());
                    if (defaultNavigationBar != null) {
                        defaultNavigationBar.updateSystemUiStateFlags();
                    }
                    if (navigationBarView != null) {
                        navigationBarView.updateDisabledSystemUiStateFlags(overviewProxyService4.mSysUiState);
                    }
                    ((ShadeViewController) overviewProxyService4.mShadeViewControllerLazy.get()).updateSystemUiStateFlags();
                    NotificationShadeWindowController notificationShadeWindowController2 = overviewProxyService4.mStatusBarWinController;
                    if (notificationShadeWindowController2 != null) {
                        ((NotificationShadeWindowControllerImpl) notificationShadeWindowController2).notifyStateChangedCallbacks();
                    }
                    OverviewProxyService overviewProxyService5 = OverviewProxyService.this;
                    overviewProxyService5.notifySystemUiStateFlags(overviewProxyService5.mSysUiState.mFlags);
                    OverviewProxyService.this.notifyConnectionChanged();
                } catch (RemoteException e2) {
                    Log.e("OverviewProxyService", "Lost connection to launcher service", e2);
                    OverviewProxyService.this.disconnectFromLauncherService("Lost connection to launcher service");
                    OverviewProxyService.this.retryConnectionWithBackoff();
                }
            }

            @Override // android.content.ServiceConnection
            public final void onServiceDisconnected(ComponentName componentName) {
                Log.w("OverviewProxyService", "Service disconnected");
                OverviewProxyService.this.mCurrentBoundedUserId = -1;
            }
        };
        StatusBarWindowCallback statusBarWindowCallback = new StatusBarWindowCallback() { // from class: com.android.systemui.recents.OverviewProxyService$$ExternalSyntheticLambda2
            @Override // com.android.systemui.statusbar.phone.StatusBarWindowCallback
            public final void onStateChanged(boolean z, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6, boolean z7, boolean z8) {
                OverviewProxyService overviewProxyService = OverviewProxyService.this;
                overviewProxyService.getClass();
                boolean z9 = false;
                boolean z10 = z && !z2;
                SysUiState sysUiState2 = overviewProxyService.mSysUiState;
                sysUiState2.setFlag(64L, z10);
                if (z && z2) {
                    z9 = true;
                }
                sysUiState2.setFlag(512L, z9);
                sysUiState2.setFlag(2147483648L, z3);
                sysUiState2.setFlag(8L, z4);
                sysUiState2.setFlag(2097152L, z5);
                sysUiState2.setFlag(134217728L, z7);
                sysUiState2.setFlag(34359738368L, z8);
                sysUiState2.commitUpdate(overviewProxyService.mContext.getDisplayId());
            }
        };
        this.mStatusBarWindowCallback = statusBarWindowCallback;
        this.mOverviewServiceDeathRcpt = new IBinder.DeathRecipient() { // from class: com.android.systemui.recents.OverviewProxyService$$ExternalSyntheticLambda3
            @Override // android.os.IBinder.DeathRecipient
            public final void binderDied() {
                OverviewProxyService overviewProxyService = OverviewProxyService.this;
                if (overviewProxyService.mInputFocusTransferStarted) {
                    overviewProxyService.mHandler.post(new OverviewProxyService$$ExternalSyntheticLambda0(overviewProxyService, 2));
                }
                overviewProxyService.startConnectionToCurrentUser();
            }
        };
        IVoiceInteractionSessionListener anonymousClass5 = new AnonymousClass5();
        UserTracker.Callback callback = new UserTracker.Callback() { // from class: com.android.systemui.recents.OverviewProxyService.6
            @Override // com.android.systemui.settings.UserTracker.Callback
            public final void onUserChanged(int i3, Context context2) {
                OverviewProxyService overviewProxyService = OverviewProxyService.this;
                overviewProxyService.mConnectionBackoffAttempts = 0;
                overviewProxyService.internalConnectToCurrentUser("User changed");
            }
        };
        this.mUserChangedCallback = callback;
        WakefulnessLifecycle.Observer observer = new WakefulnessLifecycle.Observer() { // from class: com.android.systemui.recents.OverviewProxyService.8
            @Override // com.android.systemui.keyguard.WakefulnessLifecycle.Observer
            public final void onFinishedGoingToSleep$1() {
                OverviewProxyService overviewProxyService = OverviewProxyService.this;
                SysUiState sysUiState2 = overviewProxyService.mSysUiState;
                sysUiState2.setFlag(268435456L, false);
                sysUiState2.setFlag(536870912L, false);
                sysUiState2.commitUpdate(overviewProxyService.mContext.getDisplayId());
            }

            @Override // com.android.systemui.keyguard.WakefulnessLifecycle.Observer
            public final void onFinishedWakingUp() {
                OverviewProxyService overviewProxyService = OverviewProxyService.this;
                SysUiState sysUiState2 = overviewProxyService.mSysUiState;
                sysUiState2.setFlag(268435456L, true);
                sysUiState2.setFlag(536870912L, false);
                sysUiState2.commitUpdate(overviewProxyService.mContext.getDisplayId());
            }

            @Override // com.android.systemui.keyguard.WakefulnessLifecycle.Observer
            public final void onStartedGoingToSleep() {
                OverviewProxyService overviewProxyService = OverviewProxyService.this;
                SysUiState sysUiState2 = overviewProxyService.mSysUiState;
                sysUiState2.setFlag(268435456L, false);
                sysUiState2.setFlag(536870912L, true);
                sysUiState2.commitUpdate(overviewProxyService.mContext.getDisplayId());
            }

            @Override // com.android.systemui.keyguard.WakefulnessLifecycle.Observer
            public final void onStartedWakingUp() {
                OverviewProxyService overviewProxyService = OverviewProxyService.this;
                SysUiState sysUiState2 = overviewProxyService.mSysUiState;
                sysUiState2.setFlag(268435456L, true);
                sysUiState2.setFlag(536870912L, true);
                sysUiState2.commitUpdate(overviewProxyService.mContext.getDisplayId());
            }
        };
        boolean equals = Process.myUserHandle().equals(UserHandle.SYSTEM);
        boolean z = userManager.isVisibleBackgroundUsersSupported() && !userManager.isUserForeground();
        if (equals || !z) {
            iVoiceInteractionSessionListener = anonymousClass5;
        } else {
            iVoiceInteractionSessionListener = anonymousClass5;
            Log.d("OverviewProxyService", "Initialization for visibleBackgroundUser");
        }
        boolean z2 = equals || z;
        this.mIsSystemOrVisibleBgUser = z2;
        if (!z2) {
            Log.wtf("OverviewProxyService", "Unexpected initialization for non-system foreground user", new Throwable());
        }
        this.mContext = context;
        this.mShellInterface = shellInterface;
        this.mShadeViewControllerLazy = lazy2;
        this.mHandler = new Handler();
        this.mNavBarControllerLazy = lazy;
        this.mScreenPinningRequest = screenPinningRequest;
        this.mStatusBarWinController = notificationShadeWindowController;
        this.mUserTracker = userTracker;
        this.mConnectionBackoffAttempts = 0;
        ComponentName unflattenFromString = ComponentName.unflattenFromString(context.getString(R.string.config_satellite_sim_spn_identifier));
        this.mRecentsComponentName = unflattenFromString;
        this.mQuickStepIntent = new Intent(ACTION_QUICKSTEP).setPackage(unflattenFromString.getPackageName());
        this.mSysUiState = sysUiState;
        SysUiState.SysUiStateCallback sysUiStateCallback = new SysUiState.SysUiStateCallback() { // from class: com.android.systemui.recents.OverviewProxyService$$ExternalSyntheticLambda4
            @Override // com.android.systemui.model.SysUiState.SysUiStateCallback
            public final void onSystemUiStateChanged(long j) {
                OverviewProxyService.this.notifySystemUiStateFlags(j);
            }
        };
        sysUiState.mCallbacks.add(sysUiStateCallback);
        sysUiStateCallback.onSystemUiStateChanged(sysUiState.mFlags);
        this.mUiEventLogger = uiEventLogger;
        this.mDisplayTracker = displayTracker;
        this.mUnfoldTransitionProgressForwarder = optional;
        this.mKeyboardTouchpadEduStatsInteractor = noOpKeyboardTouchpadEduStatsInteractor;
        this.mSysuiUnlockAnimationController = keyguardUnlockAnimationController;
        dumpManager.getClass();
        DumpManager.registerDumpable$default(dumpManager, "OverviewProxyService", this);
        this.mNavBarMode = navigationModeController.addListener(this);
        IntentFilter intentFilter = new IntentFilter("android.intent.action.PACKAGE_ADDED");
        intentFilter.addDataScheme("package");
        intentFilter.addDataSchemeSpecificPart(unflattenFromString.getPackageName(), 0);
        intentFilter.addAction("android.intent.action.PACKAGE_CHANGED");
        context.registerReceiver(r9, intentFilter);
        broadcastDispatcher.registerReceiver(broadcastReceiver, new IntentFilter("android.intent.action.USER_UNLOCKED"), null, UserHandle.ALL);
        ((NotificationShadeWindowControllerImpl) notificationShadeWindowController).registerCallback(statusBarWindowCallback);
        this.mScreenshotHelper = new ScreenshotHelper(context);
        commandQueue.addCallback(new CommandQueue.Callbacks() { // from class: com.android.systemui.recents.OverviewProxyService.7
            @Override // com.android.systemui.statusbar.CommandQueue.Callbacks
            public final void moveFocusedTaskToStageSplit(int i3, boolean z3) {
                OverviewProxyService overviewProxyService = OverviewProxyService.this;
                if (overviewProxyService.mOverviewProxy != null) {
                    try {
                        if (!DesktopModeStatus.canEnterDesktopMode(overviewProxyService.mContext) || (sysUiState.mFlags & 67108864) == 0) {
                            IOverviewProxy.Stub.Proxy proxy = (IOverviewProxy.Stub.Proxy) overviewProxyService.mOverviewProxy;
                            Parcel obtain = Parcel.obtain(proxy.mRemote);
                            try {
                                obtain.writeInterfaceToken("com.android.systemui.shared.recents.IOverviewProxy");
                                obtain.writeBoolean(z3);
                                proxy.mRemote.transact(26, obtain, null, 1);
                                obtain.recycle();
                            } catch (Throwable th) {
                                obtain.recycle();
                                throw th;
                            }
                        }
                    } catch (RemoteException unused) {
                        Log.w("OverviewProxyService", "Unable to enter stage split from the current running app");
                    }
                }
            }
        });
        this.mCommandQueue = commandQueue;
        ((UserTrackerImpl) userTracker).addCallback(callback, executor);
        wakefulnessLifecycle.mObservers.add(observer);
        updateEnabledAndBinding();
        assistUtils.registerVoiceInteractionSessionListener(iVoiceInteractionSessionListener);
    }

    public final void disconnectFromLauncherService(String str) {
        Log.d("OverviewProxyService", "disconnectFromLauncherService bound?: " + this.mBound + " currentProxy: " + this.mOverviewProxy + " disconnectReason: " + str, new Throwable());
        if (this.mBound) {
            this.mContext.unbindService(this.mOverviewServiceConnection);
            this.mBound = false;
        }
        IOverviewProxy iOverviewProxy = this.mOverviewProxy;
        if (iOverviewProxy != null) {
            ((IOverviewProxy.Stub.Proxy) iOverviewProxy).mRemote.unlinkToDeath(this.mOverviewServiceDeathRcpt, 0);
            this.mOverviewProxy = null;
            notifyConnectionChanged();
        }
    }

    public final void dispatchNavButtonBounds() {
        Region region;
        IOverviewProxy iOverviewProxy = this.mOverviewProxy;
        if (iOverviewProxy == null || (region = this.mActiveNavBarRegion) == null) {
            return;
        }
        try {
            IOverviewProxy.Stub.Proxy proxy = (IOverviewProxy.Stub.Proxy) iOverviewProxy;
            Parcel obtain = Parcel.obtain(proxy.mRemote);
            try {
                obtain.writeInterfaceToken("com.android.systemui.shared.recents.IOverviewProxy");
                obtain.writeTypedObject(region, 0);
                proxy.mRemote.transact(12, obtain, null, 1);
                obtain.recycle();
            } catch (Throwable th) {
                obtain.recycle();
                throw th;
            }
        } catch (RemoteException e) {
            Log.e("OverviewProxyService", "Failed to call onActiveNavBarRegionChanges()", e);
        }
    }

    @Override // com.android.systemui.Dumpable
    public final void dump(PrintWriter printWriter, String[] strArr) {
        printWriter.println("OverviewProxyService state:");
        printWriter.print("  isConnected=");
        printWriter.println(this.mOverviewProxy != null);
        printWriter.print("  mIsEnabled=");
        printWriter.println(this.mIsEnabled);
        printWriter.print("  mRecentsComponentName=");
        printWriter.println(this.mRecentsComponentName);
        printWriter.print("  mQuickStepIntent=");
        printWriter.println(this.mQuickStepIntent);
        printWriter.print("  mBound=");
        printWriter.println(this.mBound);
        printWriter.print("  mCurrentBoundedUserId=");
        printWriter.println(this.mCurrentBoundedUserId);
        printWriter.print("  mConnectionBackoffAttempts=");
        printWriter.println(this.mConnectionBackoffAttempts);
        printWriter.print("  mInputFocusTransferStarted=");
        printWriter.println(this.mInputFocusTransferStarted);
        printWriter.print("  mInputFocusTransferStartY=");
        printWriter.println(this.mInputFocusTransferStartY);
        printWriter.print("  mInputFocusTransferStartMillis=");
        printWriter.println(this.mInputFocusTransferStartMillis);
        printWriter.print("  mActiveNavBarRegion=");
        printWriter.println(this.mActiveNavBarRegion);
        printWriter.print("  mNavBarMode=");
        printWriter.println(this.mNavBarMode);
        this.mSysUiState.dump(printWriter, strArr);
    }

    public final void internalConnectToCurrentUser(String str) {
        if (!this.mIsSystemOrVisibleBgUser) {
            Log.w("OverviewProxyService", "Skipping connection to overview service due to non-system foreground user caller");
            return;
        }
        disconnectFromLauncherService(str);
        if (this.mIsEnabled) {
            OverviewProxyService$$ExternalSyntheticLambda0 overviewProxyService$$ExternalSyntheticLambda0 = this.mConnectionRunnable;
            Handler handler = this.mHandler;
            handler.removeCallbacks(overviewProxyService$$ExternalSyntheticLambda0);
            UserHandle of = UserHandle.of(((UserTrackerImpl) this.mUserTracker).getUserId());
            if (UserManager.isHeadlessSystemUserMode() && of.isSystem()) {
                Log.w("OverviewProxyService", "Skipping connection to TouchInteractionService for the System user in HSUM mode.");
                return;
            }
            try {
                this.mBound = this.mContext.bindServiceAsUser(this.mQuickStepIntent, this.mOverviewServiceConnection, 33554433, of);
            } catch (SecurityException e) {
                Log.e("OverviewProxyService", "Unable to bind because of security error", e);
            }
            if (this.mBound) {
                handler.postDelayed(this.mDeferredConnectionCallback, 5000L);
            } else {
                retryConnectionWithBackoff();
            }
        }
    }

    public final void notifyConnectionChanged() {
        for (int size = ((ArrayList) this.mConnectionCallbacks).size() - 1; size >= 0; size--) {
            ((OverviewProxyListener) ((ArrayList) this.mConnectionCallbacks).get(size)).onConnectionChanged(this.mOverviewProxy != null);
        }
    }

    public final void notifySystemUiStateFlags(long j) {
        try {
            IOverviewProxy iOverviewProxy = this.mOverviewProxy;
            if (iOverviewProxy != null) {
                IOverviewProxy.Stub.Proxy proxy = (IOverviewProxy.Stub.Proxy) iOverviewProxy;
                Parcel obtain = Parcel.obtain(proxy.mRemote);
                try {
                    obtain.writeInterfaceToken("com.android.systemui.shared.recents.IOverviewProxy");
                    obtain.writeLong(j);
                    proxy.mRemote.transact(17, obtain, null, 1);
                    obtain.recycle();
                } catch (Throwable th) {
                    obtain.recycle();
                    throw th;
                }
            }
        } catch (RemoteException e) {
            Log.e("OverviewProxyService", "Failed to notify sysui state change", e);
        }
    }

    @Override // com.android.systemui.navigationbar.NavigationModeController.ModeChangedListener
    public final void onNavigationModeChanged(int i) {
        this.mNavBarMode = i;
    }

    public final void retryConnectionWithBackoff() {
        OverviewProxyService$$ExternalSyntheticLambda0 overviewProxyService$$ExternalSyntheticLambda0 = this.mConnectionRunnable;
        Handler handler = this.mHandler;
        if (handler.hasCallbacks(overviewProxyService$$ExternalSyntheticLambda0)) {
            return;
        }
        long min = (long) Math.min(Math.scalb(1000.0f, this.mConnectionBackoffAttempts), 600000.0f);
        handler.postDelayed(overviewProxyService$$ExternalSyntheticLambda0, min);
        this.mConnectionBackoffAttempts++;
        Log.w("OverviewProxyService", "Failed to connect on attempt " + this.mConnectionBackoffAttempts + " will try again in " + min + "ms");
    }

    public final boolean shouldShowSwipeUpUI() {
        return this.mIsEnabled && this.mNavBarMode != 0;
    }

    public void shutdownForTest() {
        this.mContext.unregisterReceiver(this.mLauncherStateChangedReceiver);
        this.mIsEnabled = false;
        this.mHandler.removeCallbacks(this.mConnectionRunnable);
        disconnectFromLauncherService("Shutdown for test");
    }

    public final void startConnectionToCurrentUser() {
        Handler handler = this.mHandler;
        if (handler.getLooper() != Looper.myLooper()) {
            handler.post(this.mConnectionRunnable);
        } else {
            internalConnectToCurrentUser("startConnectionToCurrentUser");
        }
    }

    public final void updateEnabledAndBinding() {
        this.mIsEnabled = this.mContext.getPackageManager().resolveServiceAsUser(this.mQuickStepIntent, 1048576, ((UserTrackerImpl) this.mUserTracker).getUserId()) != null;
        startConnectionToCurrentUser();
    }

    @Override // com.android.systemui.statusbar.policy.CallbackController
    public final void addCallback(OverviewProxyListener overviewProxyListener) {
        if (!this.mConnectionCallbacks.contains(overviewProxyListener)) {
            this.mConnectionCallbacks.add(overviewProxyListener);
        }
        overviewProxyListener.onConnectionChanged(this.mOverviewProxy != null);
    }

    @Override // com.android.systemui.statusbar.policy.CallbackController
    public final void removeCallback(OverviewProxyListener overviewProxyListener) {
        this.mConnectionCallbacks.remove(overviewProxyListener);
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.recents.OverviewProxyService$1, reason: invalid class name */
    public final class AnonymousClass1 extends Binder implements ISystemUiProxy {
        public AnonymousClass1() {
            attachInterface(this, "com.android.systemui.shared.recents.ISystemUiProxy");
        }

        @Override // android.os.Binder
        public final boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
            if (i >= 1 && i <= 16777215) {
                parcel.enforceInterface("com.android.systemui.shared.recents.ISystemUiProxy");
            }
            if (i == 1598968902) {
                parcel2.writeString("com.android.systemui.shared.recents.ISystemUiProxy");
                return true;
            }
            if (i == 2) {
                final int readInt = parcel.readInt();
                parcel.enforceNoDataAvail();
                final int i3 = 0;
                verifyCallerAndClearCallingIdentityPostMain("startScreenPinning", new Runnable(this) { // from class: com.android.systemui.recents.OverviewProxyService$1$$ExternalSyntheticLambda13
                    public final /* synthetic */ OverviewProxyService.AnonymousClass1 f$0;

                    {
                        this.f$0 = this;
                    }

                    @Override // java.lang.Runnable
                    public final void run() {
                        switch (i3) {
                            case 0:
                                OverviewProxyService.this.mScreenPinningRequest.showPrompt(readInt, false);
                                break;
                            case 1:
                                OverviewProxyService.AnonymousClass1 anonymousClass1 = this.f$0;
                                int i4 = readInt;
                                OverviewProxyService overviewProxyService = OverviewProxyService.this;
                                for (int size = ((ArrayList) overviewProxyService.mConnectionCallbacks).size() - 1; size >= 0; size--) {
                                    ((OverviewProxyService.OverviewProxyListener) ((ArrayList) overviewProxyService.mConnectionCallbacks).get(size)).onPrioritizedRotation(i4);
                                }
                                break;
                            default:
                                AccessibilityManager.getInstance(OverviewProxyService.this.mContext).notifyAccessibilityButtonClicked(readInt);
                                break;
                        }
                    }
                });
            } else if (i == 7) {
                boolean readBoolean = parcel.readBoolean();
                parcel.enforceNoDataAvail();
                verifyCallerAndClearCallingIdentityPostMain("onOverviewShown", new OverviewProxyService$1$$ExternalSyntheticLambda5(this, readBoolean));
            } else if (i == 10) {
                MotionEvent motionEvent = (MotionEvent) parcel.readTypedObject(MotionEvent.CREATOR);
                parcel.enforceNoDataAvail();
                final OverviewProxyService$1$$ExternalSyntheticLambda4 overviewProxyService$1$$ExternalSyntheticLambda4 = new OverviewProxyService$1$$ExternalSyntheticLambda4(this, motionEvent, 2);
                verifyCallerAndClearCallingIdentity("onStatusBarTouchEvent", new Supplier() { // from class: com.android.systemui.recents.OverviewProxyService$1$$ExternalSyntheticLambda23
                    @Override // java.util.function.Supplier
                    public final Object get() {
                        overviewProxyService$1$$ExternalSyntheticLambda4.run();
                        return null;
                    }
                });
            } else if (i == 26) {
                final int readInt2 = parcel.readInt();
                parcel.enforceNoDataAvail();
                final int i4 = 1;
                verifyCallerAndClearCallingIdentityPostMain("notifyPrioritizedRotation", new Runnable(this) { // from class: com.android.systemui.recents.OverviewProxyService$1$$ExternalSyntheticLambda13
                    public final /* synthetic */ OverviewProxyService.AnonymousClass1 f$0;

                    {
                        this.f$0 = this;
                    }

                    @Override // java.lang.Runnable
                    public final void run() {
                        switch (i4) {
                            case 0:
                                OverviewProxyService.this.mScreenPinningRequest.showPrompt(readInt2, false);
                                break;
                            case 1:
                                OverviewProxyService.AnonymousClass1 anonymousClass1 = this.f$0;
                                int i42 = readInt2;
                                OverviewProxyService overviewProxyService = OverviewProxyService.this;
                                for (int size = ((ArrayList) overviewProxyService.mConnectionCallbacks).size() - 1; size >= 0; size--) {
                                    ((OverviewProxyService.OverviewProxyListener) ((ArrayList) overviewProxyService.mConnectionCallbacks).get(size)).onPrioritizedRotation(i42);
                                }
                                break;
                            default:
                                AccessibilityManager.getInstance(OverviewProxyService.this.mContext).notifyAccessibilityButtonClicked(readInt2);
                                break;
                        }
                    }
                });
            } else if (i == 30) {
                verifyCallerAndClearCallingIdentityPostMain("expandNotificationPanel", new OverviewProxyService$1$$ExternalSyntheticLambda5(this, 1));
            } else if (i == 13) {
                final float readFloat = parcel.readFloat();
                parcel.enforceNoDataAvail();
                final int i5 = 1;
                verifyCallerAndClearCallingIdentityPostMain("onAssistantProgress", new Runnable(this) { // from class: com.android.systemui.recents.OverviewProxyService$1$$ExternalSyntheticLambda3
                    public final /* synthetic */ OverviewProxyService.AnonymousClass1 f$0;

                    {
                        this.f$0 = this;
                    }

                    @Override // java.lang.Runnable
                    public final void run() {
                        switch (i5) {
                            case 0:
                                OverviewProxyService.AnonymousClass1 anonymousClass1 = this.f$0;
                                float f = readFloat;
                                OverviewProxyService overviewProxyService = OverviewProxyService.this;
                                for (int size = ((ArrayList) overviewProxyService.mConnectionCallbacks).size() - 1; size >= 0; size--) {
                                    ((OverviewProxyService.OverviewProxyListener) ((ArrayList) overviewProxyService.mConnectionCallbacks).get(size)).onAssistantGestureCompletion(f);
                                }
                                break;
                            default:
                                OverviewProxyService.AnonymousClass1 anonymousClass12 = this.f$0;
                                float f2 = readFloat;
                                OverviewProxyService overviewProxyService2 = OverviewProxyService.this;
                                for (int size2 = ((ArrayList) overviewProxyService2.mConnectionCallbacks).size() - 1; size2 >= 0; size2--) {
                                    ((OverviewProxyService.OverviewProxyListener) ((ArrayList) overviewProxyService2.mConnectionCallbacks).get(size2)).onAssistantProgress(f2);
                                }
                                break;
                        }
                    }
                });
            } else if (i == 14) {
                Bundle bundle = (Bundle) parcel.readTypedObject(Bundle.CREATOR);
                parcel.enforceNoDataAvail();
                verifyCallerAndClearCallingIdentityPostMain("startAssistant", new OverviewProxyService$1$$ExternalSyntheticLambda16(this, bundle));
            } else if (i == 45) {
                verifyCallerAndClearCallingIdentityPostMain("onBackPressed", new OverviewProxyService$1$$ExternalSyntheticLambda5(this, 4));
            } else if (i != 46) {
                switch (i) {
                    case 16:
                        final int readInt3 = parcel.readInt();
                        parcel.enforceNoDataAvail();
                        final int i6 = 2;
                        final Runnable runnable = new Runnable(this) { // from class: com.android.systemui.recents.OverviewProxyService$1$$ExternalSyntheticLambda13
                            public final /* synthetic */ OverviewProxyService.AnonymousClass1 f$0;

                            {
                                this.f$0 = this;
                            }

                            @Override // java.lang.Runnable
                            public final void run() {
                                switch (i6) {
                                    case 0:
                                        OverviewProxyService.this.mScreenPinningRequest.showPrompt(readInt3, false);
                                        break;
                                    case 1:
                                        OverviewProxyService.AnonymousClass1 anonymousClass1 = this.f$0;
                                        int i42 = readInt3;
                                        OverviewProxyService overviewProxyService = OverviewProxyService.this;
                                        for (int size = ((ArrayList) overviewProxyService.mConnectionCallbacks).size() - 1; size >= 0; size--) {
                                            ((OverviewProxyService.OverviewProxyListener) ((ArrayList) overviewProxyService.mConnectionCallbacks).get(size)).onPrioritizedRotation(i42);
                                        }
                                        break;
                                    default:
                                        AccessibilityManager.getInstance(OverviewProxyService.this.mContext).notifyAccessibilityButtonClicked(readInt3);
                                        break;
                                }
                            }
                        };
                        verifyCallerAndClearCallingIdentity("notifyAccessibilityButtonClicked", new Supplier() { // from class: com.android.systemui.recents.OverviewProxyService$1$$ExternalSyntheticLambda23
                            @Override // java.util.function.Supplier
                            public final Object get() {
                                runnable.run();
                                return null;
                            }
                        });
                        break;
                    case ViewNode.CLIPCHILDREN_FIELD_NUMBER /* 17 */:
                        final OverviewProxyService$1$$ExternalSyntheticLambda5 overviewProxyService$1$$ExternalSyntheticLambda5 = new OverviewProxyService$1$$ExternalSyntheticLambda5(this, 5);
                        verifyCallerAndClearCallingIdentity("notifyAccessibilityButtonLongClicked", new Supplier() { // from class: com.android.systemui.recents.OverviewProxyService$1$$ExternalSyntheticLambda23
                            @Override // java.util.function.Supplier
                            public final Object get() {
                                overviewProxyService$1$$ExternalSyntheticLambda5.run();
                                return null;
                            }
                        });
                        break;
                    case ViewNode.VISIBILITY_FIELD_NUMBER /* 18 */:
                        verifyCallerAndClearCallingIdentityPostMain("stopScreenPinning", new OverviewProxyService$1$$ExternalSyntheticLambda20());
                        break;
                    case ViewNode.ELEVATION_FIELD_NUMBER /* 19 */:
                        final float readFloat2 = parcel.readFloat();
                        parcel.enforceNoDataAvail();
                        final int i7 = 0;
                        verifyCallerAndClearCallingIdentityPostMain("onAssistantGestureCompletion", new Runnable(this) { // from class: com.android.systemui.recents.OverviewProxyService$1$$ExternalSyntheticLambda3
                            public final /* synthetic */ OverviewProxyService.AnonymousClass1 f$0;

                            {
                                this.f$0 = this;
                            }

                            @Override // java.lang.Runnable
                            public final void run() {
                                switch (i7) {
                                    case 0:
                                        OverviewProxyService.AnonymousClass1 anonymousClass1 = this.f$0;
                                        float f = readFloat2;
                                        OverviewProxyService overviewProxyService = OverviewProxyService.this;
                                        for (int size = ((ArrayList) overviewProxyService.mConnectionCallbacks).size() - 1; size >= 0; size--) {
                                            ((OverviewProxyService.OverviewProxyListener) ((ArrayList) overviewProxyService.mConnectionCallbacks).get(size)).onAssistantGestureCompletion(f);
                                        }
                                        break;
                                    default:
                                        OverviewProxyService.AnonymousClass1 anonymousClass12 = this.f$0;
                                        float f2 = readFloat2;
                                        OverviewProxyService overviewProxyService2 = OverviewProxyService.this;
                                        for (int size2 = ((ArrayList) overviewProxyService2.mConnectionCallbacks).size() - 1; size2 >= 0; size2--) {
                                            ((OverviewProxyService.OverviewProxyListener) ((ArrayList) overviewProxyService2.mConnectionCallbacks).get(size2)).onAssistantProgress(f2);
                                        }
                                        break;
                                }
                            }
                        });
                        break;
                    default:
                        switch (i) {
                            case 48:
                                final boolean readBoolean2 = parcel.readBoolean();
                                final boolean readBoolean3 = parcel.readBoolean();
                                parcel.enforceNoDataAvail();
                                verifyCallerAndClearCallingIdentityPostMain("notifyTaskbarStatus", new Runnable() { // from class: com.android.systemui.recents.OverviewProxyService$1$$ExternalSyntheticLambda2
                                    @Override // java.lang.Runnable
                                    public final void run() {
                                        OverviewProxyService.AnonymousClass1 anonymousClass1 = OverviewProxyService.AnonymousClass1.this;
                                        boolean z = readBoolean2;
                                        boolean z2 = readBoolean3;
                                        OverviewProxyService overviewProxyService = OverviewProxyService.this;
                                        for (int size = ((ArrayList) overviewProxyService.mConnectionCallbacks).size() - 1; size >= 0; size--) {
                                            ((OverviewProxyService.OverviewProxyListener) ((ArrayList) overviewProxyService.mConnectionCallbacks).get(size)).onTaskbarStatusUpdated$1(z, z2);
                                        }
                                    }
                                });
                                break;
                            case 49:
                                boolean readBoolean4 = parcel.readBoolean();
                                parcel.enforceNoDataAvail();
                                verifyCallerAndClearCallingIdentityPostMain("notifyTaskbarAutohideSuspend", new OverviewProxyService$5$$ExternalSyntheticLambda0(this, readBoolean4, 1));
                                break;
                            case 50:
                                InputMethodManager inputMethodManager = (InputMethodManager) OverviewProxyService.this.mContext.getSystemService(InputMethodManager.class);
                                OverviewProxyService.this.mDisplayTracker.getClass();
                                inputMethodManager.onImeSwitchButtonClickFromSystem(0);
                                OverviewProxyService.this.mUiEventLogger.log(KeyButtonView.NavBarButtonEvent.NAVBAR_IME_SWITCHER_BUTTON_TAP);
                                break;
                            case 51:
                                verifyCallerAndClearCallingIdentityPostMain("toggleNotificationPanel", new OverviewProxyService$1$$ExternalSyntheticLambda5(this, 3));
                                break;
                            case 52:
                                ScreenshotRequest screenshotRequest = (ScreenshotRequest) parcel.readTypedObject(ScreenshotRequest.CREATOR);
                                parcel.enforceNoDataAvail();
                                OverviewProxyService overviewProxyService = OverviewProxyService.this;
                                overviewProxyService.mScreenshotHelper.takeScreenshot(screenshotRequest, overviewProxyService.mHandler, (Consumer) null);
                                break;
                            case 53:
                                MotionEvent motionEvent2 = (MotionEvent) parcel.readTypedObject(MotionEvent.CREATOR);
                                parcel.enforceNoDataAvail();
                                verifyCallerAndClearCallingIdentityPostMain("onStatusBarTrackpadEvent", new OverviewProxyService$1$$ExternalSyntheticLambda4(this, motionEvent2, 0));
                                break;
                            case 54:
                                int[] createIntArray = parcel.createIntArray();
                                parcel.enforceNoDataAvail();
                                verifyCallerAndClearCallingIdentityPostMain("setAssistantOverridesRequested", new OverviewProxyService$1$$ExternalSyntheticLambda16(this, createIntArray));
                                break;
                            case 55:
                                final boolean readBoolean5 = parcel.readBoolean();
                                final boolean readBoolean6 = parcel.readBoolean();
                                final long readLong = parcel.readLong();
                                parcel.enforceNoDataAvail();
                                verifyCallerAndClearCallingIdentityPostMain("animateNavBarLongPress", new Runnable() { // from class: com.android.systemui.recents.OverviewProxyService$1$$ExternalSyntheticLambda0
                                    @Override // java.lang.Runnable
                                    public final void run() {
                                        OverviewProxyService.AnonymousClass1 anonymousClass1 = OverviewProxyService.AnonymousClass1.this;
                                        boolean z = readBoolean5;
                                        boolean z2 = readBoolean6;
                                        long j = readLong;
                                        OverviewProxyService overviewProxyService2 = OverviewProxyService.this;
                                        for (int size = ((ArrayList) overviewProxyService2.mConnectionCallbacks).size() - 1; size >= 0; size--) {
                                            ((OverviewProxyService.OverviewProxyListener) ((ArrayList) overviewProxyService2.mConnectionCallbacks).get(size)).animateNavBarLongPress(z, z2, j);
                                        }
                                    }
                                });
                                break;
                            case 56:
                                final long readLong2 = parcel.readLong();
                                final float readFloat3 = parcel.readFloat();
                                final boolean readBoolean7 = parcel.readBoolean();
                                parcel.enforceNoDataAvail();
                                verifyCallerAndClearCallingIdentityPostMain("setOverrideHomeButtonLongPress", new Runnable() { // from class: com.android.systemui.recents.OverviewProxyService$1$$ExternalSyntheticLambda9
                                    @Override // java.lang.Runnable
                                    public final void run() {
                                        OverviewProxyService.AnonymousClass1 anonymousClass1 = OverviewProxyService.AnonymousClass1.this;
                                        long j = readLong2;
                                        float f = readFloat3;
                                        boolean z = readBoolean7;
                                        OverviewProxyService overviewProxyService2 = OverviewProxyService.this;
                                        for (int size = ((ArrayList) overviewProxyService2.mConnectionCallbacks).size() - 1; size >= 0; size--) {
                                            ((OverviewProxyService.OverviewProxyListener) ((ArrayList) overviewProxyService2.mConnectionCallbacks).get(size)).setOverrideHomeButtonLongPress(f, j, z);
                                        }
                                    }
                                });
                                break;
                            case 57:
                                verifyCallerAndClearCallingIdentityPostMain("toggleQuickSettingsPanel", new OverviewProxyService$1$$ExternalSyntheticLambda5(this, 0));
                                break;
                            case 58:
                                InputMethodManager inputMethodManager2 = (InputMethodManager) OverviewProxyService.this.mContext.getSystemService(InputMethodManager.class);
                                OverviewProxyService.this.mDisplayTracker.getClass();
                                inputMethodManager2.showInputMethodPickerFromSystem(true, 0);
                                OverviewProxyService.this.mUiEventLogger.log(KeyButtonView.NavBarButtonEvent.NAVBAR_IME_SWITCHER_BUTTON_LONGPRESS);
                                break;
                            case 59:
                                boolean readBoolean8 = parcel.readBoolean();
                                String readString = parcel.readString();
                                parcel.enforceNoDataAvail();
                                verifyCallerAndClearCallingIdentityPostMain("updateContextualEduStats", new OverviewProxyService$1$$ExternalSyntheticLambda1(this, readBoolean8, readString, 0));
                                break;
                            default:
                                return super.onTransact(i, parcel, parcel2, i2);
                        }
                }
            } else {
                boolean readBoolean9 = parcel.readBoolean();
                parcel.enforceNoDataAvail();
                verifyCallerAndClearCallingIdentityPostMain("setHomeRotationEnabled", new OverviewProxyService$5$$ExternalSyntheticLambda0(this, readBoolean9, 2));
            }
            return true;
        }

        public final void sendEvent(int i) {
            long uptimeMillis = SystemClock.uptimeMillis();
            KeyEvent keyEvent = new KeyEvent(uptimeMillis, uptimeMillis, i, 4, 0, 0, -1, 0, 72, 257);
            keyEvent.setDisplayId(OverviewProxyService.this.mContext.getDisplay().getDisplayId());
            InputManagerGlobal.getInstance().injectInputEvent(keyEvent, 0);
        }

        public final void verifyCallerAndClearCallingIdentity(String str, Supplier supplier) {
            int identifier = Binder.getCallingUserHandle().getIdentifier();
            if (identifier == OverviewProxyService.this.mCurrentBoundedUserId) {
                long clearCallingIdentity = Binder.clearCallingIdentity();
                try {
                    supplier.get();
                    return;
                } finally {
                    Binder.restoreCallingIdentity(clearCallingIdentity);
                }
            }
            Log.w("OverviewProxyService", "Launcher called sysui with invalid user: " + identifier + ", reason: " + str);
        }

        public final void verifyCallerAndClearCallingIdentityPostMain(String str, final Runnable runnable) {
            verifyCallerAndClearCallingIdentity(str, new Supplier() { // from class: com.android.systemui.recents.OverviewProxyService$1$$ExternalSyntheticLambda22
                @Override // java.util.function.Supplier
                public final Object get() {
                    OverviewProxyService.AnonymousClass1 anonymousClass1 = OverviewProxyService.AnonymousClass1.this;
                    return Boolean.valueOf(OverviewProxyService.this.mHandler.post(runnable));
                }
            });
        }

        @Override // android.os.IInterface
        public final IBinder asBinder() {
            return this;
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.recents.OverviewProxyService$5, reason: invalid class name */
    public final class AnonymousClass5 extends IVoiceInteractionSessionListener.Stub {
        public AnonymousClass5() {
        }

        public final void onVoiceSessionWindowVisibilityChanged(boolean z) {
            OverviewProxyService.this.mContext.getMainExecutor().execute(new OverviewProxyService$5$$ExternalSyntheticLambda0(this, z));
        }

        public final void onSetUiHints(Bundle bundle) {
        }

        public final void onVoiceSessionHidden() {
        }

        public final void onVoiceSessionShown() {
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public interface OverviewProxyListener {
        default void onAssistantGestureCompletion(float f) {
        }

        default void onAssistantProgress(float f) {
        }

        default void onConnectionChanged(boolean z) {
        }

        default void onHomeRotationEnabled(boolean z) {
        }

        default void onOverviewShown() {
        }

        default void onPrioritizedRotation(int i) {
        }

        default void onTaskbarAutohideSuspend(boolean z) {
        }

        default void onToggleRecentApps() {
        }

        default void setAssistantOverridesRequested(int[] iArr) {
        }

        default void startAssistant(Bundle bundle) {
        }

        default void onTaskbarStatusUpdated$1(boolean z, boolean z2) {
        }

        default void animateNavBarLongPress(boolean z, boolean z2, long j) {
        }

        default void setOverrideHomeButtonLongPress(float f, long j, boolean z) {
        }
    }
}
