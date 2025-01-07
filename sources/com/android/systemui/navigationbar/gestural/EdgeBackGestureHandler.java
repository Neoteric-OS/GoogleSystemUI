package com.android.systemui.navigationbar.gestural;

import android.R;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.Region;
import android.hardware.input.InputManager;
import android.icu.text.SimpleDateFormat;
import android.os.Handler;
import android.os.RemoteException;
import android.os.SystemClock;
import android.os.SystemProperties;
import android.os.Trace;
import android.provider.DeviceConfig;
import android.util.ArraySet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.StatsEvent;
import android.util.StatsLog;
import android.util.TypedValue;
import android.view.ISystemGestureExclusionListener;
import android.view.IWindowManager;
import android.view.InputDevice;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.ViewConfiguration;
import android.view.WindowManager;
import android.window.BackAnimationAdapter;
import android.window.BackNavigationInfo;
import android.window.BackTouchTracker;
import com.android.internal.jank.InteractionJankMonitor;
import com.android.internal.policy.GestureNavigationSettingsObserver;
import com.android.internal.protolog.ProtoLogImpl_411527699;
import com.android.internal.util.LatencyTracker;
import com.android.keyguard.KeyguardClockSwitchController$$ExternalSyntheticOutline0;
import com.android.keyguard.LegacyLockIconViewController$$ExternalSyntheticOutline0;
import com.android.systemui.model.SysUiState;
import com.android.systemui.navigationbar.NavigationModeController;
import com.android.systemui.navigationbar.gestural.BackPanelController;
import com.android.systemui.navigationbar.gestural.EdgeBackGestureHandler;
import com.android.systemui.navigationbar.gestural.EdgeBackGestureHandler$$ExternalSyntheticLambda6;
import com.android.systemui.navigationbar.gestural.EdgeBackGestureHandler$$ExternalSyntheticLambda7;
import com.android.systemui.navigationbar.gestural.domain.GestureInteractor;
import com.android.systemui.navigationbar.gestural.domain.TaskMatcher;
import com.android.systemui.navigationbar.views.NavigationBar$$ExternalSyntheticLambda0;
import com.android.systemui.navigationbar.views.NavigationBar$$ExternalSyntheticLambda1;
import com.android.systemui.plugins.FalsingManager;
import com.android.systemui.plugins.NavigationEdgeBackPlugin;
import com.android.systemui.plugins.Plugin;
import com.android.systemui.plugins.PluginListener;
import com.android.systemui.plugins.PluginManager;
import com.android.systemui.recents.OverviewProxyService;
import com.android.systemui.settings.UserTracker;
import com.android.systemui.shared.system.InputChannelCompat$InputEventReceiver;
import com.android.systemui.shared.system.InputMonitorCompat;
import com.android.systemui.shared.system.QuickStepContract;
import com.android.systemui.shared.system.TaskStackChangeListener;
import com.android.systemui.statusbar.NotificationShadeWindowController;
import com.android.systemui.util.concurrency.UiThreadContext;
import com.android.systemui.util.kotlin.JavaAdapter;
import com.android.wm.shell.back.BackAnimationController;
import com.android.wm.shell.back.BackAnimationController$BackAnimationImpl$$ExternalSyntheticLambda3;
import com.android.wm.shell.common.HandlerExecutor;
import com.android.wm.shell.common.ShellExecutor;
import com.android.wm.shell.protolog.ShellProtoLogGroup;
import com.google.android.systemui.gesture.BackGestureTfClassifierProviderGoogle;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.Date;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;
import javax.inject.Provider;
import kotlinx.coroutines.StandaloneCoroutine;
import org.tensorflow.lite.Interpreter;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class EdgeBackGestureHandler implements PluginListener {
    public static final int MAX_LONG_PRESS_TIMEOUT = SystemProperties.getInt("gestures.back_timeout", 250);
    public boolean mAllowGesture;
    public BackAnimationController.BackAnimationImpl mBackAnimation;
    public final AnonymousClass5 mBackCallback;
    public BackGestureTfClassifierProviderGoogle mBackGestureTfClassifierProvider;
    public final Provider mBackGestureTfClassifierProviderProvider;
    public final BackPanelController.Factory mBackPanelControllerFactory;
    public float mBackSwipeLinearThreshold;
    public final Executor mBackgroundExecutor;
    public final ArraySet mBlockedActivities;
    public StandaloneCoroutine mBlockedActivitiesJob;
    public float mBottomGestureHeight;
    public NavigationBar$$ExternalSyntheticLambda1 mButtonForcedVisibleCallback;
    public final Context mContext;
    public boolean mDeferSetIsOnLeftEdge;
    public final EdgeBackGestureHandler$$ExternalSyntheticLambda1 mDesktopCornersChangedListener;
    public final Region mDesktopModeExcludeRegion;
    public final Optional mDesktopModeOptional;
    public boolean mDisabledForQuickstep;
    public final int mDisplayId;
    public final Point mDisplaySize;
    public final PointF mDownPoint;
    public NavigationEdgeBackPlugin mEdgeBackPlugin;
    public int mEdgeWidthLeft;
    public int mEdgeWidthRight;
    public final PointF mEndPoint;
    public final Region mExcludeRegion;
    public final FalsingManager mFalsingManager;
    public final AtomicBoolean mGestureBlockingActivityRunning;
    public final GestureInteractor mGestureInteractor;
    public final LogArray mGestureLogInsideInsets;
    public final LogArray mGestureLogOutsideInsets;
    public final GestureNavigationSettingsObserver mGestureNavigationSettingsObserver;
    public boolean mInGestureNavMode;
    public boolean mInRejectedExclusion;
    public final AnonymousClass8 mInputDeviceListener;
    public InputChannelCompat$InputEventReceiver mInputEventReceiver;
    public final InputManager mInputManager;
    public InputMonitorCompat mInputMonitor;
    public boolean mIsAttached;
    public boolean mIsBackGestureAllowed;
    public boolean mIsButtonForcedVisible;
    public boolean mIsEnabled;
    public boolean mIsGestureHandlingEnabled;
    public boolean mIsInPip;
    public boolean mIsNavBarShownTransiently;
    public boolean mIsOnLeftEdge;
    public boolean mIsTrackpadThreeFingerSwipe;
    public final JavaAdapter mJavaAdapter;
    public final Configuration mLastReportedConfig;
    public int mLeftInset;
    public final Provider mLightBarControllerProvider;
    public final SimpleDateFormat mLogDateFormat;
    public boolean mLogGesture;
    public final int mLongPressTimeout;
    public int mMLEnableWidth;
    public boolean mMLModelIsLoading;
    public float mMLModelThreshold;
    public float mMLResults;
    public final Rect mNavBarOverlayExcludedBounds;
    public final NavigationModeController mNavigationModeController;
    public float mNonLinearFactor;
    public final NotificationShadeWindowController mNotificationShadeWindowController;
    public final EdgeBackGestureHandler$$ExternalSyntheticLambda1 mOnIsInPipStateChangedListener;
    public final OverviewProxyService mOverviewProxyService;
    public String mPackageName;
    public final Rect mPipExcludedBounds;
    public final Optional mPipOptional;
    public final PluginManager mPluginManager;
    public final LogArray mPredictionLog;
    public int mRightInset;
    public int mStartingQuickstepRotation;
    public NavigationBar$$ExternalSyntheticLambda0 mStateChangeCallback;
    public long mSysUiFlags;
    public final SysUiState mSysUiState;
    public final AnonymousClass6 mSysUiStateCallback;
    public boolean mThresholdCrossed;
    public final Date mTmpLogDate;
    public float mTouchSlop;
    public final Set mTrackpadsConnected;
    public final UiThreadContext mUiThreadContext;
    public final Region mUnrestrictedExcludeRegion;
    public boolean mUseMLModel;
    public final UserTracker.Callback mUserChangedCallback;
    public final UserTracker mUserTracker;
    public boolean mUsingThreeButtonNav;
    public final ViewConfiguration mViewConfiguration;
    public Map mVocab;
    public final WindowManager mWindowManager;
    public final IWindowManager mWindowManagerService;
    public final AnonymousClass1 mGestureExclusionListener = new AnonymousClass1();
    public final AnonymousClass2 mQuickSwitchListener = new OverviewProxyService.OverviewProxyListener() { // from class: com.android.systemui.navigationbar.gestural.EdgeBackGestureHandler.2
        @Override // com.android.systemui.recents.OverviewProxyService.OverviewProxyListener
        public final void onPrioritizedRotation(int i) {
            EdgeBackGestureHandler edgeBackGestureHandler = EdgeBackGestureHandler.this;
            edgeBackGestureHandler.mStartingQuickstepRotation = i;
            int rotation = edgeBackGestureHandler.mLastReportedConfig.windowConfiguration.getRotation();
            int i2 = edgeBackGestureHandler.mStartingQuickstepRotation;
            edgeBackGestureHandler.mDisabledForQuickstep = i2 > -1 && i2 != rotation;
        }
    };
    public final AnonymousClass3 mTaskStackListener = new TaskStackChangeListener() { // from class: com.android.systemui.navigationbar.gestural.EdgeBackGestureHandler.3
        @Override // com.android.systemui.shared.system.TaskStackChangeListener
        public final void onTaskCreated(ComponentName componentName) {
            EdgeBackGestureHandler edgeBackGestureHandler = EdgeBackGestureHandler.this;
            if (componentName != null) {
                edgeBackGestureHandler.mPackageName = componentName.getPackageName();
            } else {
                edgeBackGestureHandler.mPackageName = "_UNKNOWN";
            }
        }

        @Override // com.android.systemui.shared.system.TaskStackChangeListener
        public final void onTaskStackChanged() {
            EdgeBackGestureHandler edgeBackGestureHandler = EdgeBackGestureHandler.this;
            edgeBackGestureHandler.mBackgroundExecutor.execute(new EdgeBackGestureHandler$$ExternalSyntheticLambda3(edgeBackGestureHandler, 3));
        }
    };
    public final AnonymousClass4 mOnPropertiesChangedListener = new DeviceConfig.OnPropertiesChangedListener() { // from class: com.android.systemui.navigationbar.gestural.EdgeBackGestureHandler.4
        public final void onPropertiesChanged(DeviceConfig.Properties properties) {
            if ("systemui".equals(properties.getNamespace())) {
                if (properties.getKeyset().contains("back_gesture_ml_model_threshold") || properties.getKeyset().contains("use_back_gesture_ml_model") || properties.getKeyset().contains("back_gesture_ml_model_name")) {
                    EdgeBackGestureHandler.this.updateMLModelState();
                }
            }
        }
    };
    public final VelocityTracker mVelocityTracker = VelocityTracker.obtain();

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.navigationbar.gestural.EdgeBackGestureHandler$1, reason: invalid class name */
    public final class AnonymousClass1 extends ISystemGestureExclusionListener.Stub {
        public AnonymousClass1() {
        }

        public final void onSystemGestureExclusionChanged(int i, final Region region, final Region region2) {
            EdgeBackGestureHandler edgeBackGestureHandler = EdgeBackGestureHandler.this;
            if (i == edgeBackGestureHandler.mDisplayId) {
                edgeBackGestureHandler.mUiThreadContext.executor.execute(new Runnable() { // from class: com.android.systemui.navigationbar.gestural.EdgeBackGestureHandler$1$$ExternalSyntheticLambda0
                    @Override // java.lang.Runnable
                    public final void run() {
                        EdgeBackGestureHandler.AnonymousClass1 anonymousClass1 = EdgeBackGestureHandler.AnonymousClass1.this;
                        Region region3 = region;
                        Region region4 = region2;
                        EdgeBackGestureHandler.this.mExcludeRegion.set(region3);
                        Region region5 = EdgeBackGestureHandler.this.mUnrestrictedExcludeRegion;
                        if (region4 != null) {
                            region3 = region4;
                        }
                        region5.set(region3);
                    }
                });
            }
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.navigationbar.gestural.EdgeBackGestureHandler$6, reason: invalid class name */
    public final class AnonymousClass6 implements SysUiState.SysUiStateCallback {
        public AnonymousClass6() {
        }

        @Override // com.android.systemui.model.SysUiState.SysUiStateCallback
        public final void onSystemUiStateChanged(long j) {
            EdgeBackGestureHandler.this.mSysUiFlags = j;
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Factory {
        public final Provider mBackGestureTfClassifierProviderProvider;
        public final BackPanelController.Factory mBackPanelControllerFactory;
        public final Executor mBackgroundExecutor;
        public final Handler mBgHandler;
        public final Optional mDesktopModeOptional;
        public final FalsingManager mFalsingManager;
        public final GestureInteractor mGestureInteractor;
        public final InputManager mInputManager;
        public final JavaAdapter mJavaAdapter;
        public final Provider mLightBarControllerProvider;
        public final NavigationModeController mNavigationModeController;
        public final NotificationShadeWindowController mNotificationShadeWindowController;
        public final OverviewProxyService mOverviewProxyService;
        public final Optional mPipOptional;
        public final PluginManager mPluginManager;
        public final SysUiState mSysUiState;
        public final UiThreadContext mUiThreadContext;
        public final UserTracker mUserTracker;
        public final ViewConfiguration mViewConfiguration;
        public final WindowManager mWindowManager;
        public final IWindowManager mWindowManagerService;

        public Factory(OverviewProxyService overviewProxyService, SysUiState sysUiState, PluginManager pluginManager, UiThreadContext uiThreadContext, Executor executor, Handler handler, UserTracker userTracker, NavigationModeController navigationModeController, BackPanelController.Factory factory, ViewConfiguration viewConfiguration, WindowManager windowManager, IWindowManager iWindowManager, InputManager inputManager, Optional optional, Optional optional2, FalsingManager falsingManager, Provider provider, Provider provider2, NotificationShadeWindowController notificationShadeWindowController, GestureInteractor gestureInteractor, JavaAdapter javaAdapter) {
            this.mOverviewProxyService = overviewProxyService;
            this.mSysUiState = sysUiState;
            this.mPluginManager = pluginManager;
            this.mUiThreadContext = uiThreadContext;
            this.mBackgroundExecutor = executor;
            this.mBgHandler = handler;
            this.mUserTracker = userTracker;
            this.mNavigationModeController = navigationModeController;
            this.mBackPanelControllerFactory = factory;
            this.mViewConfiguration = viewConfiguration;
            this.mWindowManager = windowManager;
            this.mWindowManagerService = iWindowManager;
            this.mInputManager = inputManager;
            this.mPipOptional = optional;
            this.mDesktopModeOptional = optional2;
            this.mFalsingManager = falsingManager;
            this.mBackGestureTfClassifierProviderProvider = provider;
            this.mLightBarControllerProvider = provider2;
            this.mNotificationShadeWindowController = notificationShadeWindowController;
            this.mGestureInteractor = gestureInteractor;
            this.mJavaAdapter = javaAdapter;
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    class LogArray extends ArrayDeque {
        private final int mLength = 10;

        public final void log(String str) {
            if (size() >= this.mLength) {
                removeFirst();
            }
            addLast(str);
        }
    }

    /* renamed from: -$$Nest$msendEvent, reason: not valid java name */
    public static void m835$$Nest$msendEvent(EdgeBackGestureHandler edgeBackGestureHandler, int i) {
        edgeBackGestureHandler.getClass();
        long uptimeMillis = SystemClock.uptimeMillis();
        KeyEvent keyEvent = new KeyEvent(uptimeMillis, uptimeMillis, i, 4, 0, 0, -1, 0, 72, 257);
        keyEvent.setDisplayId(edgeBackGestureHandler.mContext.getDisplay().getDisplayId());
        ((InputManager) edgeBackGestureHandler.mContext.getSystemService(InputManager.class)).injectInputEvent(keyEvent, 0);
    }

    /* JADX WARN: Type inference failed for: r3v1, types: [com.android.systemui.navigationbar.gestural.EdgeBackGestureHandler$2] */
    /* JADX WARN: Type inference failed for: r3v2, types: [com.android.systemui.navigationbar.gestural.EdgeBackGestureHandler$3] */
    /* JADX WARN: Type inference failed for: r3v3, types: [com.android.systemui.navigationbar.gestural.EdgeBackGestureHandler$4] */
    /* JADX WARN: Type inference failed for: r5v6, types: [com.android.systemui.navigationbar.gestural.EdgeBackGestureHandler$5] */
    public EdgeBackGestureHandler(Context context, OverviewProxyService overviewProxyService, SysUiState sysUiState, PluginManager pluginManager, UiThreadContext uiThreadContext, Executor executor, Handler handler, UserTracker userTracker, NavigationModeController navigationModeController, BackPanelController.Factory factory, ViewConfiguration viewConfiguration, WindowManager windowManager, IWindowManager iWindowManager, InputManager inputManager, Optional optional, Optional optional2, FalsingManager falsingManager, Provider provider, Provider provider2, NotificationShadeWindowController notificationShadeWindowController, GestureInteractor gestureInteractor, JavaAdapter javaAdapter) {
        Configuration configuration = new Configuration();
        this.mLastReportedConfig = configuration;
        this.mDisplaySize = new Point();
        this.mPipExcludedBounds = new Rect();
        this.mNavBarOverlayExcludedBounds = new Rect();
        this.mExcludeRegion = new Region();
        this.mDesktopModeExcludeRegion = new Region();
        this.mUnrestrictedExcludeRegion = new Region();
        this.mBlockedActivities = new ArraySet();
        this.mBlockedActivitiesJob = null;
        this.mStartingQuickstepRotation = -1;
        this.mDownPoint = new PointF();
        this.mEndPoint = new PointF();
        this.mGestureBlockingActivityRunning = new AtomicBoolean();
        this.mThresholdCrossed = false;
        this.mAllowGesture = false;
        this.mLogGesture = false;
        this.mInRejectedExclusion = false;
        this.mTrackpadsConnected = new ArraySet();
        this.mPredictionLog = new LogArray();
        this.mGestureLogInsideInsets = new LogArray();
        this.mGestureLogOutsideInsets = new LogArray();
        this.mLogDateFormat = new SimpleDateFormat("HH:mm:ss.SSS", Locale.US);
        this.mTmpLogDate = new Date();
        this.mBackCallback = new NavigationEdgeBackPlugin.BackCallback() { // from class: com.android.systemui.navigationbar.gestural.EdgeBackGestureHandler.5
            @Override // com.android.systemui.plugins.NavigationEdgeBackPlugin.BackCallback
            public final void cancelBack() {
                EdgeBackGestureHandler edgeBackGestureHandler = EdgeBackGestureHandler.this;
                BackAnimationController.BackAnimationImpl backAnimationImpl = edgeBackGestureHandler.mBackAnimation;
                if (backAnimationImpl != null) {
                    BackAnimationController backAnimationController = BackAnimationController.this;
                    ((HandlerExecutor) backAnimationController.mShellExecutor).execute(new BackAnimationController$BackAnimationImpl$$ExternalSyntheticLambda3(backAnimationImpl, false));
                }
                edgeBackGestureHandler.logGesture(4);
            }

            @Override // com.android.systemui.plugins.NavigationEdgeBackPlugin.BackCallback
            public final void setTriggerBack(boolean z) {
                BackAnimationController.BackAnimationImpl backAnimationImpl = EdgeBackGestureHandler.this.mBackAnimation;
                if (backAnimationImpl != null) {
                    BackAnimationController backAnimationController = BackAnimationController.this;
                    ((HandlerExecutor) backAnimationController.mShellExecutor).execute(new BackAnimationController$BackAnimationImpl$$ExternalSyntheticLambda3(backAnimationImpl, z));
                }
            }

            @Override // com.android.systemui.plugins.NavigationEdgeBackPlugin.BackCallback
            public final void triggerBack() {
                EdgeBackGestureHandler edgeBackGestureHandler = EdgeBackGestureHandler.this;
                edgeBackGestureHandler.mFalsingManager.isFalseTouch(16);
                BackAnimationController.BackAnimationImpl backAnimationImpl = edgeBackGestureHandler.mBackAnimation;
                char c = 1;
                if (backAnimationImpl == null) {
                    EdgeBackGestureHandler.m835$$Nest$msendEvent(edgeBackGestureHandler, 0);
                    EdgeBackGestureHandler.m835$$Nest$msendEvent(edgeBackGestureHandler, 1);
                } else {
                    ((HandlerExecutor) BackAnimationController.this.mShellExecutor).execute(new BackAnimationController$BackAnimationImpl$$ExternalSyntheticLambda3(backAnimationImpl, c == true ? 1 : 0));
                }
                edgeBackGestureHandler.logGesture(edgeBackGestureHandler.mInRejectedExclusion ? 2 : 1);
            }
        };
        this.mSysUiStateCallback = new AnonymousClass6();
        this.mOnIsInPipStateChangedListener = new EdgeBackGestureHandler$$ExternalSyntheticLambda1(this, 0);
        this.mDesktopCornersChangedListener = new EdgeBackGestureHandler$$ExternalSyntheticLambda1(this, 4);
        this.mUserChangedCallback = new UserTracker.Callback() { // from class: com.android.systemui.navigationbar.gestural.EdgeBackGestureHandler.7
            @Override // com.android.systemui.settings.UserTracker.Callback
            public final void onUserChanged(int i, Context context2) {
                EdgeBackGestureHandler edgeBackGestureHandler = EdgeBackGestureHandler.this;
                edgeBackGestureHandler.updateIsEnabled();
                edgeBackGestureHandler.updateCurrentUserResources();
            }
        };
        this.mInputDeviceListener = new AnonymousClass8();
        this.mContext = context;
        this.mDisplayId = context.getDisplayId();
        this.mUiThreadContext = uiThreadContext;
        this.mBackgroundExecutor = executor;
        this.mUserTracker = userTracker;
        this.mOverviewProxyService = overviewProxyService;
        this.mSysUiState = sysUiState;
        this.mPluginManager = pluginManager;
        this.mNavigationModeController = navigationModeController;
        this.mBackPanelControllerFactory = factory;
        this.mViewConfiguration = viewConfiguration;
        this.mWindowManager = windowManager;
        this.mWindowManagerService = iWindowManager;
        this.mInputManager = inputManager;
        this.mPipOptional = optional;
        this.mDesktopModeOptional = optional2;
        this.mFalsingManager = falsingManager;
        this.mBackGestureTfClassifierProviderProvider = provider;
        this.mLightBarControllerProvider = provider2;
        this.mGestureInteractor = gestureInteractor;
        this.mJavaAdapter = javaAdapter;
        configuration.setTo(context.getResources().getConfiguration());
        ComponentName unflattenFromString = ComponentName.unflattenFromString(context.getString(R.string.config_satellite_sim_spn_identifier));
        if (unflattenFromString != null) {
            String packageName = unflattenFromString.getPackageName();
            PackageManager packageManager = context.getPackageManager();
            try {
                Resources resourcesForApplication = packageManager.getResourcesForApplication(packageManager.getApplicationInfo(packageName, 9728));
                int identifier = resourcesForApplication.getIdentifier("back_gesture_blocking_activities", "array", packageName);
                if (identifier == 0) {
                    Log.e("EdgeBackGestureHandler", "No resource found for gesture-blocking activities");
                } else {
                    for (String str : resourcesForApplication.getStringArray(identifier)) {
                        ComponentName unflattenFromString2 = ComponentName.unflattenFromString(str);
                        if (unflattenFromString2 != null) {
                            this.mGestureInteractor.addGestureBlockedMatcher(new TaskMatcher.TopActivityComponent(unflattenFromString2), GestureInteractor.Scope.Local);
                        }
                    }
                }
            } catch (PackageManager.NameNotFoundException e) {
                Log.e("EdgeBackGestureHandler", "Failed to add gesture blocking activities", e);
            }
        }
        this.mLongPressTimeout = Math.min(MAX_LONG_PRESS_TIMEOUT, ViewConfiguration.getLongPressTimeout());
        this.mGestureNavigationSettingsObserver = new GestureNavigationSettingsObserver(this.mUiThreadContext.handler, handler, this.mContext, new EdgeBackGestureHandler$$ExternalSyntheticLambda3(this, 0));
        updateCurrentUserResources();
        this.mNotificationShadeWindowController = notificationShadeWindowController;
    }

    public final void cancelGesture(MotionEvent motionEvent) {
        this.mAllowGesture = false;
        this.mLogGesture = false;
        this.mInRejectedExclusion = false;
        MotionEvent obtain = MotionEvent.obtain(motionEvent);
        obtain.setAction(3);
        this.mEdgeBackPlugin.onMotionEvent(obtain);
        dispatchToBackAnimation(obtain);
        obtain.recycle();
    }

    public final WindowManager.LayoutParams createLayoutParams() {
        Resources resources = this.mContext.getResources();
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams(resources.getDimensionPixelSize(com.android.wm.shell.R.dimen.navigation_edge_panel_width), resources.getDimensionPixelSize(com.android.wm.shell.R.dimen.navigation_edge_panel_height), 2024, 280, -3);
        layoutParams.accessibilityTitle = this.mContext.getString(com.android.wm.shell.R.string.nav_bar_edge_panel);
        layoutParams.windowAnimations = 0;
        layoutParams.privateFlags |= 2097168;
        layoutParams.setTitle("EdgeBackGestureHandler" + this.mContext.getDisplayId());
        layoutParams.setFitInsetsTypes(0);
        layoutParams.setTrustedOverlay();
        return layoutParams;
    }

    public final void dispatchToBackAnimation(MotionEvent motionEvent) {
        final float f;
        final float f2;
        if (this.mBackAnimation != null) {
            this.mVelocityTracker.addMovement(motionEvent);
            if (motionEvent.getAction() == 1) {
                this.mVelocityTracker.computeCurrentVelocity(1000, this.mViewConfiguration.getScaledMaximumFlingVelocity());
                f = this.mVelocityTracker.getXVelocity();
                f2 = this.mVelocityTracker.getYVelocity();
            } else {
                f = Float.NaN;
                f2 = Float.NaN;
            }
            final BackAnimationController.BackAnimationImpl backAnimationImpl = this.mBackAnimation;
            final float x = motionEvent.getX();
            final float y = motionEvent.getY();
            final int actionMasked = motionEvent.getActionMasked();
            final int i = !this.mIsOnLeftEdge ? 1 : 0;
            ((HandlerExecutor) BackAnimationController.this.mShellExecutor).execute(new Runnable() { // from class: com.android.wm.shell.back.BackAnimationController$BackAnimationImpl$$ExternalSyntheticLambda4
                @Override // java.lang.Runnable
                public final void run() {
                    BackTouchTracker backTouchTracker;
                    BackAnimationController.BackAnimationImpl backAnimationImpl2 = BackAnimationController.BackAnimationImpl.this;
                    float f3 = x;
                    float f4 = y;
                    float f5 = f;
                    float f6 = f2;
                    int i2 = actionMasked;
                    int i3 = i;
                    BackAnimationController backAnimationController = BackAnimationController.this;
                    BackTouchTracker activeTracker = backAnimationController.getActiveTracker();
                    if (activeTracker != null) {
                        activeTracker.update(f3, f4, f5, f6);
                    }
                    if (backAnimationController.mCurrentTracker.isFinished() && backAnimationController.mQueuedTracker.isFinished()) {
                        if (ProtoLogImpl_411527699.Cache.WM_SHELL_BACK_PREVIEW_enabled[0]) {
                            ProtoLogImpl_411527699.d(ShellProtoLogGroup.WM_SHELL_BACK_PREVIEW, -2731934872007265096L, 0, null);
                            return;
                        }
                        return;
                    }
                    if (i2 == 0) {
                        if (backAnimationController.mBackGestureStarted) {
                            return;
                        }
                        backAnimationController.mShouldStartOnNextMoveEvent = true;
                        return;
                    }
                    BackAnimationController$$ExternalSyntheticLambda3 backAnimationController$$ExternalSyntheticLambda3 = backAnimationController.mAnimationTimeoutRunnable;
                    ShellBackAnimationRegistry shellBackAnimationRegistry = backAnimationController.mShellBackAnimationRegistry;
                    ShellExecutor shellExecutor = backAnimationController.mShellExecutor;
                    if (i2 == 2) {
                        if (!backAnimationController.mBackGestureStarted && backAnimationController.mShouldStartOnNextMoveEvent) {
                            boolean z = backAnimationController.mPostCommitAnimationInProgress && backAnimationController.mCurrentTracker.isFinished() && !backAnimationController.mCurrentTracker.getTriggerBack() && backAnimationController.mQueuedTracker.isInInitialState();
                            if (z) {
                                backAnimationController.resetTouchTracker();
                            }
                            if (backAnimationController.mCurrentTracker.isInInitialState()) {
                                backTouchTracker = backAnimationController.mCurrentTracker;
                            } else if (backAnimationController.mQueuedTracker.isInInitialState()) {
                                backTouchTracker = backAnimationController.mQueuedTracker;
                            } else {
                                if (ProtoLogImpl_411527699.Cache.WM_SHELL_BACK_PREVIEW_enabled[3]) {
                                    ProtoLogImpl_411527699.w(ShellProtoLogGroup.WM_SHELL_BACK_PREVIEW, -4950819966474305888L, 0, null);
                                }
                                backAnimationController.mShouldStartOnNextMoveEvent = false;
                            }
                            backTouchTracker.setGestureStartLocation(f3, f4, i3);
                            backTouchTracker.setState(BackTouchTracker.TouchTrackerState.ACTIVE);
                            backAnimationController.mBackGestureStarted = true;
                            if (z) {
                                backAnimationController.mPostCommitAnimationInProgress = false;
                                ((HandlerExecutor) shellExecutor).removeCallbacks(backAnimationController$$ExternalSyntheticLambda3);
                                backAnimationController.startSystemAnimation();
                            } else {
                                BackTouchTracker backTouchTracker2 = backAnimationController.mCurrentTracker;
                                if (backTouchTracker == backTouchTracker2) {
                                    try {
                                        if (backAnimationController.mTrackingLatency) {
                                            backAnimationController.cancelLatencyTracking();
                                        }
                                        backAnimationController.mLatencyTracker.onActionStart(25);
                                        backAnimationController.mTrackingLatency = true;
                                        BackAnimationAdapter backAnimationAdapter = backAnimationController.mEnableAnimations.get() ? backAnimationController.mBackAnimationAdapter : null;
                                        if (backAnimationAdapter != null && shellBackAnimationRegistry.mSupportedAnimatorsChanged) {
                                            shellBackAnimationRegistry.mSupportedAnimatorsChanged = false;
                                            backAnimationAdapter.updateSupportedAnimators(shellBackAnimationRegistry.mSupportedAnimators);
                                        }
                                        BackNavigationInfo startBackNavigation = backAnimationController.mActivityTaskManager.startBackNavigation(backAnimationController.mNavigationObserver, backAnimationAdapter);
                                        backAnimationController.mBackNavigationInfo = startBackNavigation;
                                        backAnimationController.onBackNavigationInfoReceived(startBackNavigation, backTouchTracker2);
                                    } catch (RemoteException e) {
                                        Log.e("ShellBackPreview", "Failed to initAnimation", e);
                                        backAnimationController.finishBackNavigation(backTouchTracker2.getTriggerBack());
                                    }
                                }
                            }
                            backAnimationController.mShouldStartOnNextMoveEvent = false;
                        }
                        if (!backAnimationController.mBackGestureStarted || backAnimationController.mBackNavigationInfo == null || backAnimationController.mActiveCallback == null || !backAnimationController.mOnBackStartDispatched || backAnimationController.mQueuedTracker.isActive()) {
                            return;
                        }
                        backAnimationController.dispatchOnBackProgressed(backAnimationController.mActiveCallback, backAnimationController.mCurrentTracker.createProgressEvent());
                        return;
                    }
                    if (i2 == 1 || i2 == 3) {
                        if (ProtoLogImpl_411527699.Cache.WM_SHELL_BACK_PREVIEW_enabled[0]) {
                            ProtoLogImpl_411527699.d(ShellProtoLogGroup.WM_SHELL_BACK_PREVIEW, 8967565580839694775L, 1, Long.valueOf(i2));
                        }
                        if (i2 == 3) {
                            backAnimationController.setTriggerBack(false);
                        }
                        BackTouchTracker activeTracker2 = backAnimationController.getActiveTracker();
                        if (!backAnimationController.mBackGestureStarted || activeTracker2 == null) {
                            if (ProtoLogImpl_411527699.Cache.WM_SHELL_BACK_PREVIEW_enabled[0]) {
                                ProtoLogImpl_411527699.d(ShellProtoLogGroup.WM_SHELL_BACK_PREVIEW, -250489720305009514L, 0, null);
                                return;
                            }
                            return;
                        }
                        boolean triggerBack = activeTracker2.getTriggerBack();
                        if (ProtoLogImpl_411527699.Cache.WM_SHELL_BACK_PREVIEW_enabled[0]) {
                            ProtoLogImpl_411527699.d(ShellProtoLogGroup.WM_SHELL_BACK_PREVIEW, 1940238233150763812L, 0, String.valueOf(triggerBack));
                        }
                        backAnimationController.mThresholdCrossed = false;
                        backAnimationController.mPointersPilfered = false;
                        backAnimationController.mBackGestureStarted = false;
                        activeTracker2.setState(BackTouchTracker.TouchTrackerState.FINISHED);
                        if (backAnimationController.mPostCommitAnimationInProgress) {
                            if (ProtoLogImpl_411527699.Cache.WM_SHELL_BACK_PREVIEW_enabled[3]) {
                                ProtoLogImpl_411527699.w(ShellProtoLogGroup.WM_SHELL_BACK_PREVIEW, 6833772699392816046L, 0, null);
                                return;
                            }
                            return;
                        }
                        BackNavigationInfo backNavigationInfo = backAnimationController.mBackNavigationInfo;
                        if (backNavigationInfo == null) {
                            if (!backAnimationController.mQueuedTracker.isInInitialState() && ProtoLogImpl_411527699.Cache.WM_SHELL_BACK_PREVIEW_enabled[4]) {
                                ProtoLogImpl_411527699.e(ShellProtoLogGroup.WM_SHELL_BACK_PREVIEW, 5250769310321425521L, 0, null);
                            }
                            backAnimationController.mCurrentTracker.reset();
                            if (triggerBack) {
                                if (ProtoLogImpl_411527699.Cache.WM_SHELL_BACK_PREVIEW_enabled[0]) {
                                    ProtoLogImpl_411527699.d(ShellProtoLogGroup.WM_SHELL_BACK_PREVIEW, -1127091700925973460L, 0, null);
                                }
                                backAnimationController.sendBackEvent(0);
                                backAnimationController.sendBackEvent(1);
                            }
                            backAnimationController.finishBackNavigation(triggerBack);
                            return;
                        }
                        int type = backNavigationInfo.getType();
                        if (backAnimationController.shouldDispatchToAnimator()) {
                            BackAnimationRunner backAnimationRunner = (BackAnimationRunner) shellBackAnimationRegistry.mAnimationDefinition.get(type);
                            if (!(backAnimationRunner != null ? backAnimationRunner.mAnimationCancelled : true)) {
                                BackAnimationRunner backAnimationRunner2 = (BackAnimationRunner) shellBackAnimationRegistry.mAnimationDefinition.get(type);
                                if (!(backAnimationRunner2 == null ? false : backAnimationRunner2.mWaitingAnimation)) {
                                    backAnimationController.startPostCommitAnimation();
                                    return;
                                }
                                if (ProtoLogImpl_411527699.Cache.WM_SHELL_BACK_PREVIEW_enabled[3]) {
                                    ProtoLogImpl_411527699.w(ShellProtoLogGroup.WM_SHELL_BACK_PREVIEW, -8669768627275025840L, 0, null);
                                }
                                ((HandlerExecutor) shellExecutor).executeDelayed(backAnimationController$$ExternalSyntheticLambda3, 2000L);
                                return;
                            }
                        }
                        if (ProtoLogImpl_411527699.Cache.WM_SHELL_BACK_PREVIEW_enabled[0]) {
                            ProtoLogImpl_411527699.d(ShellProtoLogGroup.WM_SHELL_BACK_PREVIEW, 7659773614139456476L, 0, null);
                        }
                        backAnimationController.invokeOrCancelBack(backAnimationController.mCurrentTracker);
                        backAnimationController.mCurrentTracker.reset();
                    }
                }
            });
        }
    }

    public final void dump(PrintWriter printWriter) {
        StringBuilder m = KeyguardClockSwitchController$$ExternalSyntheticOutline0.m(KeyguardClockSwitchController$$ExternalSyntheticOutline0.m(KeyguardClockSwitchController$$ExternalSyntheticOutline0.m(KeyguardClockSwitchController$$ExternalSyntheticOutline0.m(KeyguardClockSwitchController$$ExternalSyntheticOutline0.m(KeyguardClockSwitchController$$ExternalSyntheticOutline0.m(printWriter, "EdgeBackGestureHandler:", "  mIsEnabled="), this.mIsEnabled, printWriter, "  mIsAttached="), this.mIsAttached, printWriter, "  mIsBackGestureAllowed="), this.mIsBackGestureAllowed, printWriter, "  mIsGestureHandlingEnabled="), this.mIsGestureHandlingEnabled, printWriter, "  mIsNavBarShownTransiently="), this.mIsNavBarShownTransiently, printWriter, "  mGestureBlockingActivityRunning=");
        m.append(this.mGestureBlockingActivityRunning.get());
        printWriter.println(m.toString());
        StringBuilder m2 = KeyguardClockSwitchController$$ExternalSyntheticOutline0.m(LegacyLockIconViewController$$ExternalSyntheticOutline0.m(KeyguardClockSwitchController$$ExternalSyntheticOutline0.m(KeyguardClockSwitchController$$ExternalSyntheticOutline0.m(KeyguardClockSwitchController$$ExternalSyntheticOutline0.m(new StringBuilder("  mAllowGesture="), this.mAllowGesture, printWriter, "  mUseMLModel="), this.mUseMLModel, printWriter, "  mDisabledForQuickstep="), this.mDisabledForQuickstep, printWriter, "  mStartingQuickstepRotation="), this.mStartingQuickstepRotation, printWriter, "  mInRejectedExclusion="), this.mInRejectedExclusion, printWriter, "  mExcludeRegion=");
        m2.append(this.mExcludeRegion);
        printWriter.println(m2.toString());
        printWriter.println("  mUnrestrictedExcludeRegion=" + this.mUnrestrictedExcludeRegion);
        StringBuilder m3 = KeyguardClockSwitchController$$ExternalSyntheticOutline0.m(new StringBuilder("  mIsInPip="), this.mIsInPip, printWriter, "  mPipExcludedBounds=");
        m3.append(this.mPipExcludedBounds);
        printWriter.println(m3.toString());
        printWriter.println("  mDesktopModeExclusionRegion=" + this.mDesktopModeExcludeRegion);
        printWriter.println("  mNavBarOverlayExcludedBounds=" + this.mNavBarOverlayExcludedBounds);
        StringBuilder m4 = LegacyLockIconViewController$$ExternalSyntheticOutline0.m(LegacyLockIconViewController$$ExternalSyntheticOutline0.m(LegacyLockIconViewController$$ExternalSyntheticOutline0.m(LegacyLockIconViewController$$ExternalSyntheticOutline0.m(LegacyLockIconViewController$$ExternalSyntheticOutline0.m(LegacyLockIconViewController$$ExternalSyntheticOutline0.m(LegacyLockIconViewController$$ExternalSyntheticOutline0.m(LegacyLockIconViewController$$ExternalSyntheticOutline0.m(new StringBuilder("  mEdgeWidthLeft="), this.mEdgeWidthLeft, printWriter, "  mEdgeWidthRight="), this.mEdgeWidthRight, printWriter, "  mLeftInset="), this.mLeftInset, printWriter, "  mRightInset="), this.mRightInset, printWriter, "  mMLEnableWidth="), this.mMLEnableWidth, printWriter, "  mMLModelThreshold="), this.mMLModelThreshold, printWriter, "  mTouchSlop="), this.mTouchSlop, printWriter, "  mBottomGestureHeight="), this.mBottomGestureHeight, printWriter, "  mPredictionLog=");
        m4.append(String.join("\n", this.mPredictionLog));
        printWriter.println(m4.toString());
        printWriter.println("  mGestureLogInsideInsets=" + String.join("\n", this.mGestureLogInsideInsets));
        printWriter.println("  mGestureLogOutsideInsets=" + String.join("\n", this.mGestureLogOutsideInsets));
        printWriter.println("  mTrackpadsConnected=" + ((String) this.mTrackpadsConnected.stream().map(new EdgeBackGestureHandler$$ExternalSyntheticLambda4()).collect(Collectors.joining())));
        StringBuilder m5 = KeyguardClockSwitchController$$ExternalSyntheticOutline0.m(new StringBuilder("  mUsingThreeButtonNav="), this.mUsingThreeButtonNav, printWriter, "  mEdgeBackPlugin=");
        m5.append(this.mEdgeBackPlugin);
        printWriter.println(m5.toString());
        NavigationEdgeBackPlugin navigationEdgeBackPlugin = this.mEdgeBackPlugin;
        if (navigationEdgeBackPlugin != null) {
            navigationEdgeBackPlugin.dump(printWriter);
        }
    }

    public final boolean isHandlingGestures() {
        return this.mIsEnabled && this.mIsBackGestureAllowed;
    }

    public final void logGesture(int i) {
        if (this.mLogGesture) {
            this.mLogGesture = false;
            Map map = this.mVocab;
            String str = (!this.mUseMLModel || map == null || !map.containsKey(this.mPackageName) || ((Integer) map.get(this.mPackageName)).intValue() >= 100) ? "" : this.mPackageName;
            PointF pointF = this.mDownPoint;
            int i2 = (int) pointF.y;
            int i3 = this.mIsOnLeftEdge ? 1 : 2;
            int i4 = (int) pointF.x;
            PointF pointF2 = this.mEndPoint;
            int i5 = (int) pointF2.x;
            int i6 = (int) pointF2.y;
            int i7 = this.mEdgeWidthLeft + this.mLeftInset;
            int i8 = this.mDisplaySize.x - (this.mEdgeWidthRight + this.mRightInset);
            float f = this.mUseMLModel ? this.mMLResults : -2.0f;
            int i9 = this.mIsTrackpadThreeFingerSwipe ? 2 : 1;
            StatsEvent.Builder newBuilder = StatsEvent.newBuilder();
            newBuilder.setAtomId(224);
            newBuilder.writeInt(i);
            newBuilder.writeInt(i2);
            newBuilder.writeInt(i3);
            newBuilder.writeInt(i4);
            newBuilder.writeInt(i2);
            newBuilder.writeInt(i5);
            newBuilder.writeInt(i6);
            newBuilder.writeInt(i7);
            newBuilder.writeInt(i8);
            newBuilder.writeFloat(f);
            newBuilder.writeString(str);
            newBuilder.writeInt(i9);
            newBuilder.usePooledBuffer();
            StatsLog.write(newBuilder.build());
        }
    }

    public final void onNavigationModeChanged(int i) {
        Trace.beginSection("EdgeBackGestureHandler#onNavigationModeChanged");
        try {
            this.mUsingThreeButtonNav = i == 0;
            this.mInGestureNavMode = QuickStepContract.isGesturalMode(i);
            updateIsEnabled();
            updateCurrentUserResources();
        } finally {
            Trace.endSection();
        }
    }

    @Override // com.android.systemui.plugins.PluginListener
    public final void onPluginConnected(Plugin plugin, Context context) {
        setEdgeBackPlugin((NavigationEdgeBackPlugin) plugin);
    }

    @Override // com.android.systemui.plugins.PluginListener
    public final void onPluginDisconnected(Plugin plugin) {
        resetEdgeBackPlugin();
    }

    public final void resetEdgeBackPlugin() {
        Context context = this.mContext;
        BackPanelController.Factory factory = this.mBackPanelControllerFactory;
        UiThreadContext uiThreadContext = factory.uiThreadContext;
        uiThreadContext.isCurrentThread();
        ViewConfiguration viewConfiguration = factory.viewConfiguration;
        LatencyTracker latencyTracker = factory.latencyTracker;
        InteractionJankMonitor interactionJankMonitor = factory.interactionJankMonitor;
        BackPanelController backPanelController = new BackPanelController(context, factory.windowManager, viewConfiguration, uiThreadContext.handler, factory.systemClock, factory.vibratorHelper, factory.configurationController, latencyTracker, interactionJankMonitor);
        backPanelController.init$9();
        setEdgeBackPlugin(backPanelController);
    }

    public final void setBackAnimation(final BackAnimationController.BackAnimationImpl backAnimationImpl) {
        this.mBackAnimation = backAnimationImpl;
        if (backAnimationImpl != null) {
            Executor executor = this.mUiThreadContext.executor;
            final EdgeBackGestureHandler$$ExternalSyntheticLambda6 edgeBackGestureHandler$$ExternalSyntheticLambda6 = new EdgeBackGestureHandler$$ExternalSyntheticLambda6(this, executor, 0);
            BackAnimationController backAnimationController = BackAnimationController.this;
            final int i = 0;
            Runnable runnable = new Runnable() { // from class: com.android.wm.shell.back.BackAnimationController$BackAnimationImpl$$ExternalSyntheticLambda1
                @Override // java.lang.Runnable
                public final void run() {
                    switch (i) {
                        case 0:
                            BackAnimationController.this.mPilferPointerCallback = (EdgeBackGestureHandler$$ExternalSyntheticLambda6) edgeBackGestureHandler$$ExternalSyntheticLambda6;
                            break;
                        default:
                            BackAnimationController.this.mRequestTopUiCallback = (EdgeBackGestureHandler$$ExternalSyntheticLambda7) edgeBackGestureHandler$$ExternalSyntheticLambda6;
                            break;
                    }
                }
            };
            ShellExecutor shellExecutor = backAnimationController.mShellExecutor;
            ((HandlerExecutor) shellExecutor).execute(runnable);
            final EdgeBackGestureHandler$$ExternalSyntheticLambda7 edgeBackGestureHandler$$ExternalSyntheticLambda7 = new EdgeBackGestureHandler$$ExternalSyntheticLambda7(this, executor);
            final int i2 = 1;
            ((HandlerExecutor) shellExecutor).execute(new Runnable() { // from class: com.android.wm.shell.back.BackAnimationController$BackAnimationImpl$$ExternalSyntheticLambda1
                @Override // java.lang.Runnable
                public final void run() {
                    switch (i2) {
                        case 0:
                            BackAnimationController.this.mPilferPointerCallback = (EdgeBackGestureHandler$$ExternalSyntheticLambda6) edgeBackGestureHandler$$ExternalSyntheticLambda7;
                            break;
                        default:
                            BackAnimationController.this.mRequestTopUiCallback = (EdgeBackGestureHandler$$ExternalSyntheticLambda7) edgeBackGestureHandler$$ExternalSyntheticLambda7;
                            break;
                    }
                }
            });
            updateBackAnimationThresholds();
            if (this.mLightBarControllerProvider.get() != null) {
                BackAnimationController.BackAnimationImpl backAnimationImpl2 = this.mBackAnimation;
                EdgeBackGestureHandler$$ExternalSyntheticLambda7 edgeBackGestureHandler$$ExternalSyntheticLambda72 = new EdgeBackGestureHandler$$ExternalSyntheticLambda7(this, executor);
                BackAnimationController backAnimationController2 = BackAnimationController.this;
                backAnimationController2.mCustomizer = edgeBackGestureHandler$$ExternalSyntheticLambda72;
                backAnimationController2.mAnimationBackground.mCustomizer = edgeBackGestureHandler$$ExternalSyntheticLambda72;
            }
        }
    }

    public final void setEdgeBackPlugin(NavigationEdgeBackPlugin navigationEdgeBackPlugin) {
        try {
            Trace.beginSection("setEdgeBackPlugin");
            this.mEdgeBackPlugin = navigationEdgeBackPlugin;
            navigationEdgeBackPlugin.setBackCallback(this.mBackCallback);
            this.mEdgeBackPlugin.setLayoutParams(createLayoutParams());
            updateDisplaySize();
        } finally {
            Trace.endSection();
        }
    }

    public final void updateBackAnimationThresholds() {
        if (this.mBackAnimation == null) {
            return;
        }
        final float f = this.mDisplaySize.x;
        final float min = Math.min(f, this.mBackSwipeLinearThreshold);
        final BackAnimationController.BackAnimationImpl backAnimationImpl = this.mBackAnimation;
        final float f2 = this.mNonLinearFactor;
        ((HandlerExecutor) BackAnimationController.this.mShellExecutor).execute(new Runnable() { // from class: com.android.wm.shell.back.BackAnimationController$BackAnimationImpl$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                BackAnimationController.BackAnimationImpl backAnimationImpl2 = BackAnimationController.BackAnimationImpl.this;
                float f3 = min;
                float f4 = f;
                float f5 = f2;
                BackAnimationController backAnimationController = BackAnimationController.this;
                backAnimationController.mCurrentTracker.setProgressThresholds(f3, f4, f5);
                backAnimationController.mQueuedTracker.setProgressThresholds(f3, f4, f5);
            }
        });
    }

    public final void updateCurrentUserResources() {
        NavigationBar$$ExternalSyntheticLambda1 navigationBar$$ExternalSyntheticLambda1;
        Resources resources = this.mNavigationModeController.getCurrentUserContext().getResources();
        this.mEdgeWidthLeft = this.mGestureNavigationSettingsObserver.getLeftSensitivity(resources);
        this.mEdgeWidthRight = this.mGestureNavigationSettingsObserver.getRightSensitivity(resources);
        boolean z = this.mIsButtonForcedVisible;
        boolean areNavigationButtonForcedVisible = this.mGestureNavigationSettingsObserver.areNavigationButtonForcedVisible();
        this.mIsButtonForcedVisible = areNavigationButtonForcedVisible;
        this.mIsBackGestureAllowed = !areNavigationButtonForcedVisible;
        if (z != areNavigationButtonForcedVisible && (navigationBar$$ExternalSyntheticLambda1 = this.mButtonForcedVisibleCallback) != null) {
            navigationBar$$ExternalSyntheticLambda1.accept(Boolean.valueOf(areNavigationButtonForcedVisible));
        }
        DisplayMetrics displayMetrics = resources.getDisplayMetrics();
        this.mBottomGestureHeight = TypedValue.applyDimension(1, DeviceConfig.getFloat("systemui", "back_gesture_bottom_height", resources.getDimension(R.dimen.navigation_edge_action_progress_threshold) / displayMetrics.density), displayMetrics);
        int applyDimension = (int) TypedValue.applyDimension(1, 12.0f, displayMetrics);
        this.mMLEnableWidth = applyDimension;
        int i = this.mEdgeWidthRight;
        if (applyDimension > i) {
            this.mMLEnableWidth = i;
        }
        int i2 = this.mMLEnableWidth;
        int i3 = this.mEdgeWidthLeft;
        if (i2 > i3) {
            this.mMLEnableWidth = i3;
        }
        this.mTouchSlop = this.mViewConfiguration.getScaledTouchSlop() * DeviceConfig.getFloat("systemui", "back_gesture_slop_multiplier", 0.75f);
        this.mBackSwipeLinearThreshold = resources.getDimension(R.dimen.notification_actions_icon_drawable_size);
        TypedValue typedValue = new TypedValue();
        resources.getValue(R.dimen.base_error_dialog_bottom_padding, typedValue, true);
        this.mNonLinearFactor = typedValue.getFloat();
        updateBackAnimationThresholds();
    }

    public final void updateDisplaySize() {
        Rect maxBounds = this.mLastReportedConfig.windowConfiguration.getMaxBounds();
        this.mDisplaySize.set(maxBounds.width(), maxBounds.height());
        NavigationEdgeBackPlugin navigationEdgeBackPlugin = this.mEdgeBackPlugin;
        if (navigationEdgeBackPlugin != null) {
            navigationEdgeBackPlugin.setDisplaySize(this.mDisplaySize);
        }
        updateBackAnimationThresholds();
    }

    public final void updateIsEnabled() {
        this.mUiThreadContext.handler.runWithScissors(new EdgeBackGestureHandler$$ExternalSyntheticLambda3(this, 4), 150L);
    }

    public final void updateMLModelState() {
        boolean z = this.mIsGestureHandlingEnabled && this.mContext.getResources().getBoolean(com.android.wm.shell.R.bool.config_useBackGestureML) && DeviceConfig.getBoolean("systemui", "use_back_gesture_ml_model", false);
        if (z == this.mUseMLModel) {
            return;
        }
        this.mUseMLModel = z;
        if (z) {
            this.mUiThreadContext.isCurrentThread();
            if (this.mMLModelIsLoading) {
                Log.d("EdgeBackGestureHandler", "Model tried to load while already loading.");
                return;
            } else {
                this.mMLModelIsLoading = true;
                this.mBackgroundExecutor.execute(new EdgeBackGestureHandler$$ExternalSyntheticLambda3(this, 2));
                return;
            }
        }
        BackGestureTfClassifierProviderGoogle backGestureTfClassifierProviderGoogle = this.mBackGestureTfClassifierProvider;
        if (backGestureTfClassifierProviderGoogle != null) {
            backGestureTfClassifierProviderGoogle.mVocab = null;
            backGestureTfClassifierProviderGoogle.mModelLoaded = false;
            Interpreter interpreter = backGestureTfClassifierProviderGoogle.mInterpreter;
            if (interpreter != null) {
                interpreter.close();
            }
            this.mBackGestureTfClassifierProvider = null;
            this.mVocab = null;
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.navigationbar.gestural.EdgeBackGestureHandler$8, reason: invalid class name */
    public final class AnonymousClass8 implements InputManager.InputDeviceListener {
        public AnonymousClass8() {
        }

        @Override // android.hardware.input.InputManager.InputDeviceListener
        public final void onInputDeviceAdded(int i) {
            InputDevice inputDevice = EdgeBackGestureHandler.this.mInputManager.getInputDevice(i);
            if (inputDevice != null && inputDevice.getSources() == 1056778) {
                boolean isEmpty = ((ArraySet) EdgeBackGestureHandler.this.mTrackpadsConnected).isEmpty();
                ((ArraySet) EdgeBackGestureHandler.this.mTrackpadsConnected).add(Integer.valueOf(i));
                if (isEmpty) {
                    EdgeBackGestureHandler edgeBackGestureHandler = EdgeBackGestureHandler.this;
                    if (!edgeBackGestureHandler.mIsEnabled || ((ArraySet) edgeBackGestureHandler.mTrackpadsConnected).isEmpty()) {
                        EdgeBackGestureHandler.this.updateIsEnabled();
                        EdgeBackGestureHandler.this.updateCurrentUserResources();
                    }
                }
            }
        }

        @Override // android.hardware.input.InputManager.InputDeviceListener
        public final void onInputDeviceRemoved(int i) {
            ((ArraySet) EdgeBackGestureHandler.this.mTrackpadsConnected).remove(Integer.valueOf(i));
            if (((ArraySet) EdgeBackGestureHandler.this.mTrackpadsConnected).isEmpty()) {
                EdgeBackGestureHandler edgeBackGestureHandler = EdgeBackGestureHandler.this;
                if (!edgeBackGestureHandler.mIsEnabled || ((ArraySet) edgeBackGestureHandler.mTrackpadsConnected).isEmpty()) {
                    EdgeBackGestureHandler.this.updateIsEnabled();
                    EdgeBackGestureHandler.this.updateCurrentUserResources();
                }
            }
        }

        @Override // android.hardware.input.InputManager.InputDeviceListener
        public final void onInputDeviceChanged(int i) {
        }
    }
}
