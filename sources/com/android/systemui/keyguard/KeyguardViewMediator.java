package com.android.systemui.keyguard;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.app.ActivityManager;
import android.app.ActivityTaskManager;
import android.app.AlarmManager;
import android.app.BroadcastOptions;
import android.app.IActivityTaskManager;
import android.app.PendingIntent;
import android.app.StatusBarManager;
import android.app.trust.TrustManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.content.pm.UserInfo;
import android.frameworks.stats.AnnotationValue$1$$ExternalSyntheticOutline0;
import android.graphics.Matrix;
import android.hardware.biometrics.BiometricSourceType;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.MediaRouter;
import android.media.SoundPool;
import android.os.Binder;
import android.os.Bundle;
import android.os.DeadObjectException;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.PowerManager;
import android.os.RemoteException;
import android.os.SystemProperties;
import android.os.Trace;
import android.os.UserHandle;
import android.os.UserManager;
import android.provider.DeviceConfig;
import android.provider.Settings;
import android.telephony.SubscriptionManager;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.util.Slog;
import android.util.SparseBooleanArray;
import android.util.SparseIntArray;
import android.view.IRemoteAnimationFinishedCallback;
import android.view.IRemoteAnimationRunner;
import android.view.RemoteAnimationTarget;
import android.view.SyncRtSurfaceTransactionApplier;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowInsetsController;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import androidx.collection.MutableObjectList$$ExternalSyntheticOutline0;
import androidx.compose.foundation.text.ValidatingOffsetMappingKt$$ExternalSyntheticOutline0;
import androidx.exifinterface.media.ExifInterface$$ExternalSyntheticOutline0;
import com.android.app.animation.Interpolators;
import com.android.app.viewcapture.data.ViewNode;
import com.android.internal.foldables.FoldGracePeriodProvider;
import com.android.internal.jank.InteractionJankMonitor;
import com.android.internal.logging.UiEventLogger;
import com.android.internal.policy.IKeyguardDismissCallback;
import com.android.internal.policy.IKeyguardStateCallback;
import com.android.internal.policy.ScreenDecorationsUtils;
import com.android.internal.statusbar.IStatusBarService;
import com.android.internal.util.LatencyTracker;
import com.android.internal.widget.LockPatternUtils;
import com.android.keyguard.KeyguardConstants;
import com.android.keyguard.KeyguardDisplayManager;
import com.android.keyguard.KeyguardUpdateMonitor;
import com.android.keyguard.KeyguardUpdateMonitor$$ExternalSyntheticLambda3;
import com.android.keyguard.KeyguardUpdateMonitorCallback;
import com.android.settingslib.bluetooth.CachedBluetoothDevice$$ExternalSyntheticOutline0;
import com.android.settingslib.bluetooth.LocalBluetoothLeBroadcast$3$$ExternalSyntheticOutline0;
import com.android.settingslib.mobile.MobileStatusTracker$MobileTelephonyCallback$$ExternalSyntheticOutline0;
import com.android.systemui.CoreStartable;
import com.android.systemui.DejankUtils;
import com.android.systemui.Dumpable;
import com.android.systemui.animation.ActivityTransitionAnimator;
import com.android.systemui.animation.TransitionAnimator;
import com.android.systemui.broadcast.BroadcastDispatcher;
import com.android.systemui.classifier.FalsingCollector;
import com.android.systemui.communal.ui.viewmodel.CommunalTransitionViewModel;
import com.android.systemui.deviceentry.domain.interactor.SystemUIDeviceEntryFaceAuthInteractor;
import com.android.systemui.dreams.DreamOverlayStateController;
import com.android.systemui.dreams.ui.viewmodel.DreamViewModel;
import com.android.systemui.dump.DumpManager;
import com.android.systemui.flags.SystemPropertiesHelper;
import com.android.systemui.keyguard.KeyguardService;
import com.android.systemui.keyguard.KeyguardViewMediator;
import com.android.systemui.keyguard.data.repository.KeyguardRepositoryImpl;
import com.android.systemui.keyguard.domain.interactor.FromLockscreenTransitionInteractor;
import com.android.systemui.keyguard.domain.interactor.FromOccludedTransitionInteractor;
import com.android.systemui.keyguard.domain.interactor.KeyguardInteractor;
import com.android.systemui.keyguard.shared.model.TransitionStep;
import com.android.systemui.keyguard.ui.viewmodel.LockscreenToDreamingTransitionViewModel;
import com.android.systemui.log.SessionTracker;
import com.android.systemui.navigationbar.NavigationModeController;
import com.android.systemui.navigationbar.views.NavigationBarView;
import com.android.systemui.plugins.statusbar.StatusBarStateController;
import com.android.systemui.process.ProcessWrapper;
import com.android.systemui.settings.UserTracker;
import com.android.systemui.settings.UserTrackerImpl;
import com.android.systemui.shade.NotificationShadeWindowControllerImpl;
import com.android.systemui.shade.NotificationShadeWindowState;
import com.android.systemui.shade.NotificationShadeWindowView;
import com.android.systemui.shade.ShadeController;
import com.android.systemui.shared.system.QuickStepContract;
import com.android.systemui.shared.system.TaskStackChangeListeners;
import com.android.systemui.statusbar.NotificationShadeDepthController;
import com.android.systemui.statusbar.NotificationShadeWindowController;
import com.android.systemui.statusbar.StatusBarStateControllerImpl;
import com.android.systemui.statusbar.SysuiStatusBarStateController;
import com.android.systemui.statusbar.phone.CentralSurfacesImpl;
import com.android.systemui.statusbar.phone.DozeParameters;
import com.android.systemui.statusbar.phone.ScreenOffAnimationController;
import com.android.systemui.statusbar.phone.ScrimController;
import com.android.systemui.statusbar.phone.StatusBarKeyguardViewManager;
import com.android.systemui.statusbar.policy.KeyguardStateController;
import com.android.systemui.statusbar.policy.KeyguardStateControllerImpl;
import com.android.systemui.statusbar.policy.UserSwitcherController;
import com.android.systemui.user.domain.interactor.GuestUserInteractor;
import com.android.systemui.user.domain.interactor.SelectedUserInteractor;
import com.android.systemui.util.DeviceConfigProxy;
import com.android.systemui.util.kotlin.JavaAdapter;
import com.android.systemui.util.settings.SecureSettings;
import com.android.systemui.util.settings.SystemSettingsImpl;
import com.android.systemui.util.time.SystemClock;
import com.android.systemui.util.time.SystemClockImpl;
import com.android.systemui.wallpapers.data.repository.WallpaperRepositoryImpl;
import com.android.wm.shell.R;
import com.android.wm.shell.keyguard.KeyguardTransitions;
import dagger.Lazy;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.concurrent.Executor;
import java.util.function.IntFunction;
import java.util.function.Predicate;
import java.util.stream.Stream;
import kotlin.Unit;
import kotlinx.coroutines.flow.StateFlowImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class KeyguardViewMediator implements CoreStartable, Dumpable, StatusBarStateController.StateListener {
    public static final boolean DEBUG = KeyguardConstants.DEBUG;
    public static final Intent USER_PRESENT_INTENT = new Intent("android.intent.action.USER_PRESENT").addFlags(606076928);
    public static final Bundle USER_PRESENT_INTENT_OPTIONS = BroadcastOptions.makeBasic().setDeferralPolicy(2).setDeliveryGroupPolicy(1).toBundle();
    public final IActivityTaskManager mActivityTaskManagerService;
    public final Lazy mActivityTransitionAnimator;
    public AlarmManager mAlarmManager;
    public boolean mAnimatingScreenOff;
    public boolean mAodShowing;
    public final AnonymousClass7 mAppearAnimationRunner;
    public AudioManager mAudioManager;
    public boolean mBootCompleted;
    public boolean mBootSendUserPresent;
    public final BroadcastDispatcher mBroadcastDispatcher;
    public final AnonymousClass11 mBroadcastReceiver;
    public CentralSurfacesImpl mCentralSurfaces;
    public final Lazy mCommunalTransitionViewModel;
    public final Context mContext;
    public CharSequence mCustomMessage;
    public final AnonymousClass11 mDelayedLockBroadcastReceiver;
    public int mDelayedProfileShowingSequence;
    public int mDelayedShowingSequence;
    public boolean mDeviceInteractive;
    public final DismissCallbackRegistry mDismissCallbackRegistry;
    public final DozeParameters mDozeParameters;
    public boolean mDozing;
    public final int mDreamOpenAnimationDuration;
    public boolean mDreamOverlayShowing;
    public final DreamOverlayStateController.Callback mDreamOverlayStateCallback;
    public final DreamOverlayStateController mDreamOverlayStateController;
    public final Lazy mDreamViewModel;
    public final AnonymousClass6 mExitAnimationRunner;
    public final FalsingCollector mFalsingCollector;
    protected FoldGracePeriodProvider mFoldGracePeriodProvider;
    public boolean mGoingToSleep;
    public final AnonymousClass13 mHandler;
    public Animation mHideAnimation;
    public final KeyguardViewMediator$$ExternalSyntheticLambda1 mHideAnimationFinishedRunnable;
    public boolean mHiding;
    public boolean mIgnoreDismiss;
    public boolean mInGestureNavigationMode;
    public boolean mInputRestricted;
    public final InteractionJankMonitor mInteractionJankMonitor;
    public final JavaAdapter mJavaAdapter;
    public final KeyguardDisplayManager mKeyguardDisplayManager;
    public ActivityTransitionAnimator.Runner mKeyguardExitAnimationRunner;
    public final AnonymousClass14 mKeyguardGoingAwayRunnable;
    public final KeyguardInteractor mKeyguardInteractor;
    public final KeyguardStateController mKeyguardStateController;
    public final AnonymousClass10 mKeyguardStateControllerCallback;
    public final KeyguardTransitions mKeyguardTransitions;
    public final Lazy mKeyguardUnlockAnimationControllerLazy;
    public final Lazy mKeyguardViewControllerLazy;
    public boolean mLockLater;
    public final LockPatternUtils mLockPatternUtils;
    public int mLockSoundId;
    public int mLockSoundStreamId;
    public float mLockSoundVolume;
    public SoundPool mLockSounds;
    public final Lazy mNotificationShadeDepthController;
    public final Lazy mNotificationShadeWindowControllerLazy;
    final ActivityTransitionAnimator.Controller mOccludeAnimationController;
    public final OccludeActivityLaunchRemoteAnimationRunner mOccludeAnimationRunner;
    public final AnonymousClass8 mOccludeByDreamAnimationRunner;
    public RemoteAnimationTarget mOccludingRemoteAnimationTarget;
    public final AnonymousClass1 mOnPropertiesChangedListener;
    public final boolean mOrderUnlockAndWake;
    public final PowerManager mPM;
    public boolean mPendingLock;
    public boolean mPendingReset;
    public final float mPowerButtonY;
    public final ProcessWrapper mProcessWrapper;
    public RemoteAnimationTarget mRemoteAnimationTarget;
    public final ScreenOffAnimationController mScreenOffAnimationController;
    public final Lazy mScrimControllerLazy;
    public final SecureSettings mSecureSettings;
    public final SelectedUserInteractor mSelectedUserInteractor;
    public final SessionTracker mSessionTracker;
    public final Lazy mShadeController;
    public boolean mShowCommunalWhenUnoccluding;
    public boolean mShowHomeOverLockscreen;
    public final PowerManager.WakeLock mShowKeyguardWakeLock;
    public boolean mShowing;
    public boolean mShuttingDown;
    public StatusBarManager mStatusBarManager;
    public final IStatusBarService mStatusBarService;
    public final SysuiStatusBarStateController mStatusBarStateController;
    public IRemoteAnimationFinishedCallback mSurfaceBehindRemoteAnimationFinishedCallback;
    public boolean mSurfaceBehindRemoteAnimationRunning;
    public final SystemClock mSystemClock;
    public final SystemPropertiesHelper mSystemPropertiesHelper;
    public boolean mSystemReady;
    public final SystemSettingsImpl mSystemSettings;
    public final TrustManager mTrustManager;
    public int mTrustedSoundId;
    public final Executor mUiBgExecutor;
    public final UiEventLogger mUiEventLogger;
    public int mUiSoundsStreamType;
    public int mUnlockSoundId;
    public final AnonymousClass9 mUnoccludeAnimationRunner;
    public IRemoteAnimationFinishedCallback mUnoccludeFinishedCallback;
    public final KeyguardUpdateMonitorCallback mUpdateCallback;
    public final KeyguardUpdateMonitor mUpdateMonitor;
    public final UserSwitcherController mUserSwitcherController;
    public final UserTracker mUserTracker;
    public final AnonymousClass4 mViewMediatorCallback;
    public final WallpaperRepositoryImpl mWallpaperRepository;
    public boolean mWallpaperSupportsAmbientMode;
    public final float mWindowCornerRadius;
    public final IBinder mStatusBarDisableToken = new Binder();
    public boolean mExternallyEnabled = true;
    public boolean mNeedToReshowWhenReenabled = false;
    public boolean mOccluded = false;
    public boolean mOccludeAnimationPlaying = false;
    public boolean mWakeAndUnlocking = false;
    public final SparseIntArray mLastSimStates = new SparseIntArray();
    public final SparseBooleanArray mSimWasLocked = new SparseBooleanArray();
    public final String mPhoneState = TelephonyManager.EXTRA_STATE_IDLE;
    public boolean mWaitingUntilKeyguardVisible = false;
    public boolean mKeyguardDonePending = false;
    public boolean mUnlockingAndWakingFromDream = false;
    public boolean mHideAnimationRun = false;
    public boolean mHideAnimationRunning = false;
    public boolean mIsKeyguardExitAnimationCanceled = false;
    public final ArrayList mKeyguardStateCallbacks = new ArrayList();
    public boolean mPendingPinLock = false;
    public boolean mPowerGestureIntercepted = false;
    public boolean mSurfaceBehindRemoteAnimationRequested = false;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.keyguard.KeyguardViewMediator$13, reason: invalid class name */
    public final class AnonymousClass13 extends Handler {
        public AnonymousClass13(Looper looper) {
            super(looper, null, true);
        }

        @Override // android.os.Handler
        public final void handleMessage(Message message) {
            KeyguardViewMediator keyguardViewMediator;
            WindowInsetsController windowInsetsController;
            String str = "";
            switch (message.what) {
                case 1:
                    str = "SHOW";
                    KeyguardViewMediator keyguardViewMediator2 = KeyguardViewMediator.this;
                    Bundle bundle = (Bundle) message.obj;
                    keyguardViewMediator2.getClass();
                    Trace.beginSection("KeyguardViewMediator#handleShow");
                    try {
                        keyguardViewMediator2.handleShowInner(bundle);
                        break;
                    } finally {
                        Trace.endSection();
                    }
                case 2:
                    str = "HIDE";
                    KeyguardViewMediator.this.handleHide();
                    break;
                case 3:
                    str = "RESET";
                    KeyguardViewMediator.m821$$Nest$mhandleReset(KeyguardViewMediator.this, message.arg1 != 0);
                    break;
                case 5:
                    str = "NOTIFY_FINISHED_GOING_TO_SLEEP";
                    KeyguardViewMediator.m819$$Nest$mhandleNotifyFinishedGoingToSleep(KeyguardViewMediator.this);
                    break;
                case 7:
                    str = "KEYGUARD_DONE";
                    Trace.beginSection("KeyguardViewMediator#handleMessage KEYGUARD_DONE");
                    KeyguardViewMediator.this.handleKeyguardDone();
                    Trace.endSection();
                    break;
                case 8:
                    str = "KEYGUARD_DONE_DRAWING";
                    Trace.beginSection("KeyguardViewMediator#handleMessage KEYGUARD_DONE_DRAWING");
                    KeyguardViewMediator.m818$$Nest$mhandleKeyguardDoneDrawing(KeyguardViewMediator.this);
                    Trace.endSection();
                    break;
                case 9:
                    str = "SET_OCCLUDED";
                    Trace.beginSection("KeyguardViewMediator#handleMessage SET_OCCLUDED");
                    KeyguardViewMediator.m822$$Nest$mhandleSetOccluded(KeyguardViewMediator.this, message.arg1 != 0, message.arg2 != 0);
                    Trace.endSection();
                    break;
                case 10:
                    str = "KEYGUARD_TIMEOUT";
                    synchronized (KeyguardViewMediator.this) {
                        KeyguardViewMediator.this.doKeyguardLocked((Bundle) message.obj);
                    }
                    break;
                case ViewNode.TRANSLATIONX_FIELD_NUMBER /* 11 */:
                    str = "DISMISS";
                    DismissMessage dismissMessage = (DismissMessage) message.obj;
                    KeyguardViewMediator keyguardViewMediator3 = KeyguardViewMediator.this;
                    IKeyguardDismissCallback iKeyguardDismissCallback = dismissMessage.mCallback;
                    CharSequence charSequence = dismissMessage.mMessage;
                    if (!keyguardViewMediator3.mShowing) {
                        Log.w("KeyguardViewMediator", "Ignoring request to DISMISS because mShowing=false");
                        if (iKeyguardDismissCallback != null) {
                            try {
                                iKeyguardDismissCallback.onDismissError();
                                break;
                            } catch (RemoteException e) {
                                Log.i("DismissCallbackWrapper", "Failed to call callback", e);
                                break;
                            }
                        }
                    } else {
                        if (iKeyguardDismissCallback != null) {
                            DismissCallbackRegistry dismissCallbackRegistry = keyguardViewMediator3.mDismissCallbackRegistry;
                            dismissCallbackRegistry.getClass();
                            Log.d("DismissCallbackRegistry", "Adding callback: " + iKeyguardDismissCallback);
                            ArrayList arrayList = dismissCallbackRegistry.mDismissCallbacks;
                            DismissCallbackWrapper dismissCallbackWrapper = new DismissCallbackWrapper();
                            dismissCallbackWrapper.mCallback = iKeyguardDismissCallback;
                            arrayList.add(dismissCallbackWrapper);
                        }
                        keyguardViewMediator3.mCustomMessage = charSequence;
                        ((StatusBarKeyguardViewManager) keyguardViewMediator3.mKeyguardViewControllerLazy.get()).mActivityStarter.executeRunnableDismissingKeyguard(null, null, true, false, true);
                        break;
                    }
                    break;
                case ViewNode.TRANSLATIONY_FIELD_NUMBER /* 12 */:
                    str = "START_KEYGUARD_EXIT_ANIM";
                    Trace.beginSection("KeyguardViewMediator#handleMessage START_KEYGUARD_EXIT_ANIM");
                    synchronized (KeyguardViewMediator.this) {
                        keyguardViewMediator = KeyguardViewMediator.this;
                        keyguardViewMediator.mHiding = true;
                    }
                    final StartKeyguardExitAnimParams startKeyguardExitAnimParams = (StartKeyguardExitAnimParams) message.obj;
                    ((NotificationShadeWindowControllerImpl) ((NotificationShadeWindowController) keyguardViewMediator.mNotificationShadeWindowControllerLazy.get())).batchApplyWindowLayoutParams(new Runnable() { // from class: com.android.systemui.keyguard.KeyguardViewMediator$13$$ExternalSyntheticLambda0
                        @Override // java.lang.Runnable
                        public final void run() {
                            KeyguardViewMediator.AnonymousClass13 anonymousClass13 = KeyguardViewMediator.AnonymousClass13.this;
                            KeyguardViewMediator.StartKeyguardExitAnimParams startKeyguardExitAnimParams2 = startKeyguardExitAnimParams;
                            long j = startKeyguardExitAnimParams2.startTime;
                            long j2 = startKeyguardExitAnimParams2.fadeoutDuration;
                            RemoteAnimationTarget[] remoteAnimationTargetArr = startKeyguardExitAnimParams2.mApps;
                            RemoteAnimationTarget[] remoteAnimationTargetArr2 = startKeyguardExitAnimParams2.mWallpapers;
                            RemoteAnimationTarget[] remoteAnimationTargetArr3 = startKeyguardExitAnimParams2.mNonApps;
                            IRemoteAnimationFinishedCallback iRemoteAnimationFinishedCallback = startKeyguardExitAnimParams2.mFinishedCallback;
                            KeyguardViewMediator keyguardViewMediator4 = KeyguardViewMediator.this;
                            keyguardViewMediator4.getClass();
                            Trace.beginSection("KeyguardViewMediator#handleStartKeyguardExitAnimation");
                            try {
                                keyguardViewMediator4.handleStartKeyguardExitAnimationInner(j, j2, remoteAnimationTargetArr, remoteAnimationTargetArr2, remoteAnimationTargetArr3, iRemoteAnimationFinishedCallback);
                                Trace.endSection();
                                keyguardViewMediator4.mFalsingCollector.onSuccessfulUnlock();
                            } catch (Throwable th) {
                                Trace.endSection();
                                throw th;
                            }
                        }
                    });
                    Trace.endSection();
                    break;
                case ViewNode.SCALEX_FIELD_NUMBER /* 13 */:
                    str = "KEYGUARD_DONE_PENDING_TIMEOUT";
                    Trace.beginSection("KeyguardViewMediator#handleMessage KEYGUARD_DONE_PENDING_TIMEOUT");
                    Log.w("KeyguardViewMediator", "Timeout while waiting for activity drawn!");
                    Trace.endSection();
                    break;
                case ViewNode.SCALEY_FIELD_NUMBER /* 14 */:
                    str = "NOTIFY_STARTED_WAKING_UP";
                    Trace.beginSection("KeyguardViewMediator#handleMessage NOTIFY_STARTED_WAKING_UP");
                    KeyguardViewMediator.m820$$Nest$mhandleNotifyStartedWakingUp(KeyguardViewMediator.this);
                    Trace.endSection();
                    break;
                case ViewNode.CLIPCHILDREN_FIELD_NUMBER /* 17 */:
                    str = "NOTIFY_STARTED_GOING_TO_SLEEP";
                    KeyguardViewMediator keyguardViewMediator4 = KeyguardViewMediator.this;
                    synchronized (keyguardViewMediator4) {
                        try {
                            if (KeyguardViewMediator.DEBUG) {
                                Log.d("KeyguardViewMediator", "handleNotifyStartedGoingToSleep");
                            }
                            StatusBarKeyguardViewManager statusBarKeyguardViewManager = (StatusBarKeyguardViewManager) keyguardViewMediator4.mKeyguardViewControllerLazy.get();
                            statusBarKeyguardViewManager.mIsSleeping = true;
                            NotificationShadeWindowView notificationShadeWindowView = ((NotificationShadeWindowControllerImpl) statusBarKeyguardViewManager.mNotificationShadeWindowController).mWindowRootView;
                            if (notificationShadeWindowView != null && (windowInsetsController = notificationShadeWindowView.getWindowInsetsController()) != null) {
                                windowInsetsController.setAnimationsDisabled(true);
                            }
                            NavigationBarView navigationBarView = statusBarKeyguardViewManager.mCentralSurfaces.getNavigationBarView();
                            if (navigationBarView != null) {
                                View view = navigationBarView.mVertical;
                                if (view != null) {
                                    view.animate().alpha(0.0f).setDuration(125L).start();
                                }
                                View view2 = navigationBarView.mHorizontal;
                                if (view2 != null) {
                                    view2.animate().alpha(0.0f).setDuration(125L).start();
                                }
                            }
                        } finally {
                        }
                    }
                    break;
                case ViewNode.VISIBILITY_FIELD_NUMBER /* 18 */:
                    str = "SYSTEM_READY";
                    KeyguardViewMediator keyguardViewMediator5 = KeyguardViewMediator.this;
                    synchronized (keyguardViewMediator5) {
                        try {
                            if (KeyguardViewMediator.DEBUG) {
                                Log.d("KeyguardViewMediator", "onSystemReady");
                            }
                            keyguardViewMediator5.mSystemReady = true;
                            keyguardViewMediator5.doKeyguardLocked(null);
                            keyguardViewMediator5.mUpdateMonitor.registerCallback(keyguardViewMediator5.mUpdateCallback);
                            keyguardViewMediator5.adjustStatusBarLocked(false, false);
                            keyguardViewMediator5.mDreamOverlayStateController.addCallback(keyguardViewMediator5.mDreamOverlayStateCallback);
                            DreamViewModel dreamViewModel = (DreamViewModel) keyguardViewMediator5.mDreamViewModel.get();
                            CommunalTransitionViewModel communalTransitionViewModel = (CommunalTransitionViewModel) keyguardViewMediator5.mCommunalTransitionViewModel.get();
                            keyguardViewMediator5.mJavaAdapter.alwaysCollectFlow(dreamViewModel.dreamAlpha, new KeyguardViewMediator$$ExternalSyntheticLambda5(keyguardViewMediator5, 3));
                            keyguardViewMediator5.mJavaAdapter.alwaysCollectFlow(dreamViewModel.transitionEnded, new KeyguardViewMediator$$ExternalSyntheticLambda5(keyguardViewMediator5, 2));
                            keyguardViewMediator5.mJavaAdapter.alwaysCollectFlow(communalTransitionViewModel.showCommunalFromOccluded, new KeyguardViewMediator$$ExternalSyntheticLambda5(keyguardViewMediator5, 1));
                            keyguardViewMediator5.mJavaAdapter.alwaysCollectFlow(communalTransitionViewModel.transitionFromOccludedEnded, new KeyguardViewMediator$$ExternalSyntheticLambda5(keyguardViewMediator5, 2));
                            UserTracker userTracker = keyguardViewMediator5.mUserTracker;
                            if (((UserTrackerImpl) userTracker).isUserSwitching) {
                                keyguardViewMediator5.mUpdateCallback.onUserSwitching(((UserTrackerImpl) userTracker).getUserId());
                            }
                        } finally {
                        }
                    }
                    keyguardViewMediator5.maybeSendUserPresentBroadcast();
                    break;
                case ViewNode.ELEVATION_FIELD_NUMBER /* 19 */:
                    str = "CANCEL_KEYGUARD_EXIT_ANIM";
                    Trace.beginSection("KeyguardViewMediator#handleMessage CANCEL_KEYGUARD_EXIT_ANIM");
                    KeyguardViewMediator keyguardViewMediator6 = KeyguardViewMediator.this;
                    if (keyguardViewMediator6.mPendingLock) {
                        Log.d("KeyguardViewMediator", "#handleCancelKeyguardExitAnimation: keyguard exit animation cancelled. There's a pending lock, so we were cancelled because the device was locked again during the unlock sequence. We should end up locked.");
                        keyguardViewMediator6.mIsKeyguardExitAnimationCanceled = true;
                        keyguardViewMediator6.finishSurfaceBehindRemoteAnimation(true);
                        keyguardViewMediator6.maybeHandlePendingLock();
                    } else {
                        Log.d("KeyguardViewMediator", "#handleCancelKeyguardExitAnimation: keyguard exit animation cancelled. No pending lock, we should end up unlocked with the app/launcher visible.");
                        keyguardViewMediator6.showSurfaceBehindKeyguard();
                        keyguardViewMediator6.exitKeyguardAndFinishSurfaceBehindRemoteAnimation();
                    }
                    Trace.endSection();
                    break;
            }
            Log.d("KeyguardViewMediator", "KeyguardViewMediator queue processing message: ".concat(str));
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.keyguard.KeyguardViewMediator$14, reason: invalid class name */
    public final class AnonymousClass14 implements Runnable {
        public AnonymousClass14() {
        }

        /* JADX WARN: Code restructure failed: missing block: B:19:0x006c, code lost:
        
            if (r0.mWallpaperSupportsAmbientMode != false) goto L21;
         */
        @Override // java.lang.Runnable
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final void run() {
            /*
                r6 = this;
                java.lang.String r0 = "KeyguardViewMediator.mKeyGuardGoingAwayRunnable"
                android.os.Trace.beginSection(r0)
                boolean r0 = com.android.systemui.keyguard.KeyguardViewMediator.DEBUG
                if (r0 == 0) goto L10
                java.lang.String r0 = "KeyguardViewMediator"
                java.lang.String r1 = "keyguardGoingAway"
                android.util.Log.d(r0, r1)
            L10:
                com.android.systemui.keyguard.KeyguardViewMediator r0 = com.android.systemui.keyguard.KeyguardViewMediator.this
                dagger.Lazy r0 = r0.mKeyguardViewControllerLazy
                java.lang.Object r0 = r0.get()
                com.android.systemui.statusbar.phone.StatusBarKeyguardViewManager r0 = (com.android.systemui.statusbar.phone.StatusBarKeyguardViewManager) r0
                com.android.systemui.statusbar.phone.CentralSurfacesImpl r0 = r0.mCentralSurfaces
                com.android.systemui.statusbar.policy.KeyguardStateController r1 = r0.mKeyguardStateController
                com.android.systemui.statusbar.policy.KeyguardStateControllerImpl r1 = (com.android.systemui.statusbar.policy.KeyguardStateControllerImpl) r1
                r2 = 1
                r1.notifyKeyguardGoingAway(r2)
                com.android.systemui.statusbar.CommandQueue r1 = r0.mCommandQueue
                int r3 = r0.mDisplayId
                java.lang.Object r4 = r1.mLock
                monitor-enter(r4)
                com.android.systemui.statusbar.CommandQueue$H r1 = r1.mHandler     // Catch: java.lang.Throwable -> Ld5
                r5 = 1245184(0x130000, float:1.744874E-39)
                android.os.Message r1 = r1.obtainMessage(r5, r3, r2)     // Catch: java.lang.Throwable -> Ld5
                r1.sendToTarget()     // Catch: java.lang.Throwable -> Ld5
                monitor-exit(r4)     // Catch: java.lang.Throwable -> Ld5
                r0.updateScrimController()
                com.android.systemui.keyguard.KeyguardViewMediator r0 = com.android.systemui.keyguard.KeyguardViewMediator.this
                dagger.Lazy r0 = r0.mKeyguardViewControllerLazy
                java.lang.Object r0 = r0.get()
                com.android.systemui.statusbar.phone.StatusBarKeyguardViewManager r0 = (com.android.systemui.statusbar.phone.StatusBarKeyguardViewManager) r0
                r0.getClass()
                com.android.systemui.keyguard.KeyguardViewMediator r0 = com.android.systemui.keyguard.KeyguardViewMediator.this
                boolean r1 = r0.mWakeAndUnlocking
                if (r1 == 0) goto L53
                boolean r1 = r0.mWallpaperSupportsAmbientMode
                if (r1 != 0) goto L53
                r1 = 2
                goto L54
            L53:
                r1 = 0
            L54:
                dagger.Lazy r0 = r0.mKeyguardViewControllerLazy
                java.lang.Object r0 = r0.get()
                com.android.systemui.statusbar.phone.StatusBarKeyguardViewManager r0 = (com.android.systemui.statusbar.phone.StatusBarKeyguardViewManager) r0
                com.android.systemui.statusbar.SysuiStatusBarStateController r0 = r0.mStatusBarStateController
                com.android.systemui.statusbar.StatusBarStateControllerImpl r0 = (com.android.systemui.statusbar.StatusBarStateControllerImpl) r0
                boolean r0 = r0.mLeaveOpenOnKeyguardHide
                if (r0 != 0) goto L6e
                com.android.systemui.keyguard.KeyguardViewMediator r0 = com.android.systemui.keyguard.KeyguardViewMediator.this
                boolean r3 = r0.mWakeAndUnlocking
                if (r3 == 0) goto L70
                boolean r0 = r0.mWallpaperSupportsAmbientMode
                if (r0 == 0) goto L70
            L6e:
                r1 = r1 | 1
            L70:
                com.android.systemui.keyguard.KeyguardViewMediator r0 = com.android.systemui.keyguard.KeyguardViewMediator.this
                dagger.Lazy r0 = r0.mKeyguardViewControllerLazy
                java.lang.Object r0 = r0.get()
                com.android.systemui.statusbar.phone.StatusBarKeyguardViewManager r0 = (com.android.systemui.statusbar.phone.StatusBarKeyguardViewManager) r0
                com.android.systemui.statusbar.NotificationShadeWindowController r0 = r0.mNotificationShadeWindowController
                com.android.systemui.shade.NotificationShadeWindowControllerImpl r0 = (com.android.systemui.shade.NotificationShadeWindowControllerImpl) r0
                com.android.systemui.shade.NotificationShadeWindowState r0 = r0.mCurrentState
                r0.getClass()
                r0 = r1 | 4
                com.android.systemui.keyguard.KeyguardViewMediator r3 = com.android.systemui.keyguard.KeyguardViewMediator.this
                dagger.Lazy r3 = r3.mKeyguardViewControllerLazy
                java.lang.Object r3 = r3.get()
                com.android.systemui.statusbar.phone.StatusBarKeyguardViewManager r3 = (com.android.systemui.statusbar.phone.StatusBarKeyguardViewManager) r3
                r3.getClass()
                com.android.systemui.keyguard.KeyguardViewMediator r3 = com.android.systemui.keyguard.KeyguardViewMediator.this
                boolean r4 = r3.mWakeAndUnlocking
                if (r4 == 0) goto La8
                dagger.Lazy r3 = r3.mKeyguardUnlockAnimationControllerLazy
                java.lang.Object r3 = r3.get()
                com.android.systemui.keyguard.KeyguardUnlockAnimationController r3 = (com.android.systemui.keyguard.KeyguardUnlockAnimationController) r3
                boolean r3 = r3.isSupportedLauncherUnderneath()
                if (r3 == 0) goto La8
                r0 = r1 | 20
            La8:
                com.android.systemui.keyguard.KeyguardViewMediator r1 = com.android.systemui.keyguard.KeyguardViewMediator.this
                com.android.keyguard.KeyguardUpdateMonitor r1 = r1.mUpdateMonitor
                r1.setKeyguardGoingAway(r2)
                com.android.systemui.keyguard.KeyguardViewMediator r1 = com.android.systemui.keyguard.KeyguardViewMediator.this
                dagger.Lazy r1 = r1.mKeyguardViewControllerLazy
                java.lang.Object r1 = r1.get()
                com.android.systemui.statusbar.phone.StatusBarKeyguardViewManager r1 = (com.android.systemui.statusbar.phone.StatusBarKeyguardViewManager) r1
                com.android.systemui.statusbar.NotificationShadeWindowController r1 = r1.mNotificationShadeWindowController
                com.android.systemui.shade.NotificationShadeWindowControllerImpl r1 = (com.android.systemui.shade.NotificationShadeWindowControllerImpl) r1
                com.android.systemui.shade.NotificationShadeWindowState r3 = r1.mCurrentState
                r3.keyguardGoingAway = r2
                r1.apply(r3)
                com.android.systemui.keyguard.KeyguardViewMediator r1 = com.android.systemui.keyguard.KeyguardViewMediator.this
                java.util.concurrent.Executor r1 = r1.mUiBgExecutor
                com.android.systemui.keyguard.KeyguardViewMediator$$ExternalSyntheticLambda9 r2 = new com.android.systemui.keyguard.KeyguardViewMediator$$ExternalSyntheticLambda9
                r3 = 2
                r2.<init>(r0, r3, r6)
                r1.execute(r2)
                android.os.Trace.endSection()
                return
            Ld5:
                r6 = move-exception
                monitor-exit(r4)     // Catch: java.lang.Throwable -> Ld5
                throw r6
            */
            throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.keyguard.KeyguardViewMediator.AnonymousClass14.run():void");
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.keyguard.KeyguardViewMediator$16, reason: invalid class name */
    public final class AnonymousClass16 extends AnimatorListenerAdapter {
        public final /* synthetic */ int $r8$classId;
        public final /* synthetic */ Object this$0;
        public final /* synthetic */ IRemoteAnimationFinishedCallback val$finishedCallback;

        public /* synthetic */ AnonymousClass16(Object obj, IRemoteAnimationFinishedCallback iRemoteAnimationFinishedCallback, int i) {
            this.$r8$classId = i;
            this.this$0 = obj;
            this.val$finishedCallback = iRemoteAnimationFinishedCallback;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator animator) {
            KeyguardViewMediator keyguardViewMediator;
            switch (this.$r8$classId) {
                case 0:
                    try {
                        try {
                            this.val$finishedCallback.onAnimationFinished();
                            keyguardViewMediator = (KeyguardViewMediator) this.this$0;
                        } catch (RemoteException unused) {
                            Slog.e("KeyguardViewMediator", "RemoteException");
                            keyguardViewMediator = (KeyguardViewMediator) this.this$0;
                        }
                        keyguardViewMediator.mInteractionJankMonitor.cancel(29);
                        return;
                    } catch (Throwable th) {
                        ((KeyguardViewMediator) this.this$0).mInteractionJankMonitor.cancel(29);
                        throw th;
                    }
                default:
                    super.onAnimationCancel(animator);
                    return;
            }
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public final void onAnimationEnd(Animator animator) {
            KeyguardViewMediator keyguardViewMediator;
            switch (this.$r8$classId) {
                case 0:
                    try {
                        try {
                            this.val$finishedCallback.onAnimationFinished();
                            keyguardViewMediator = (KeyguardViewMediator) this.this$0;
                        } catch (Throwable th) {
                            ((KeyguardViewMediator) this.this$0).mInteractionJankMonitor.end(29);
                            throw th;
                        }
                    } catch (RemoteException unused) {
                        Slog.e("KeyguardViewMediator", "RemoteException");
                        keyguardViewMediator = (KeyguardViewMediator) this.this$0;
                    }
                    this = keyguardViewMediator.mInteractionJankMonitor;
                    this.end(29);
                    return;
                default:
                    try {
                        this.val$finishedCallback.onAnimationFinished();
                        AnonymousClass9 anonymousClass9 = (AnonymousClass9) this.this$0;
                        anonymousClass9.mUnoccludeAnimator = null;
                        KeyguardViewMediator.this.mInteractionJankMonitor.end(64);
                        return;
                    } catch (RemoteException e) {
                        Log.e("KeyguardViewMediator", "Failed to finish transition", e);
                        return;
                    }
            }
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.keyguard.KeyguardViewMediator$3, reason: invalid class name */
    class AnonymousClass3 extends KeyguardUpdateMonitorCallback {
        public AnonymousClass3() {
        }

        @Override // com.android.keyguard.KeyguardUpdateMonitorCallback
        public final void onBiometricAuthFailed(BiometricSourceType biometricSourceType) {
            KeyguardViewMediator keyguardViewMediator = KeyguardViewMediator.this;
            int selectedUserId = keyguardViewMediator.mSelectedUserInteractor.getSelectedUserId();
            if (keyguardViewMediator.mLockPatternUtils.isSecure(selectedUserId)) {
                keyguardViewMediator.mLockPatternUtils.getDevicePolicyManager().reportFailedBiometricAttempt(selectedUserId);
            }
        }

        @Override // com.android.keyguard.KeyguardUpdateMonitorCallback
        public final void onBiometricAuthenticated(int i, BiometricSourceType biometricSourceType, boolean z) {
            KeyguardViewMediator keyguardViewMediator = KeyguardViewMediator.this;
            if (keyguardViewMediator.mLockPatternUtils.isSecure(i)) {
                keyguardViewMediator.mLockPatternUtils.getDevicePolicyManager().reportSuccessfulBiometricAttempt(i);
            }
        }

        @Override // com.android.keyguard.KeyguardUpdateMonitorCallback
        public final void onDeviceProvisioned() {
            KeyguardViewMediator.this.sendUserPresentBroadcast();
        }

        @Override // com.android.keyguard.KeyguardUpdateMonitorCallback
        public final void onKeyguardVisibilityChanged(boolean z) {
            synchronized (KeyguardViewMediator.this) {
                if (!z) {
                    try {
                        if (KeyguardViewMediator.this.mPendingPinLock) {
                            Log.i("KeyguardViewMediator", "PIN lock requested, starting keyguard");
                            KeyguardViewMediator keyguardViewMediator = KeyguardViewMediator.this;
                            keyguardViewMediator.mPendingPinLock = false;
                            keyguardViewMediator.doKeyguardLocked(null);
                        }
                    } catch (Throwable th) {
                        throw th;
                    }
                }
            }
        }

        @Override // com.android.keyguard.KeyguardUpdateMonitorCallback
        public final void onSimStateChanged(int i, int i2, int i3) {
            boolean z;
            StringBuilder m = ValidatingOffsetMappingKt$$ExternalSyntheticOutline0.m(i, i2, "onSimStateChanged(subId=", ", slotId=", ",state=");
            m.append(TelephonyManager.simStateToString(i3));
            m.append(")");
            Log.d("KeyguardViewMediator", m.toString());
            int size = KeyguardViewMediator.this.mKeyguardStateCallbacks.size();
            boolean isSimPinSecure = KeyguardViewMediator.this.mUpdateMonitor.isSimPinSecure();
            for (int i4 = size - 1; i4 >= 0; i4--) {
                try {
                    ((IKeyguardStateCallback) KeyguardViewMediator.this.mKeyguardStateCallbacks.get(i4)).onSimSecureStateChanged(isSimPinSecure);
                } catch (RemoteException e) {
                    Slog.w("KeyguardViewMediator", "Failed to call onSimSecureStateChanged", e);
                    if (e instanceof DeadObjectException) {
                        KeyguardViewMediator.this.mKeyguardStateCallbacks.remove(i4);
                    }
                }
            }
            synchronized (KeyguardViewMediator.this) {
                int i5 = KeyguardViewMediator.this.mLastSimStates.get(i2);
                if (i5 != 2 && i5 != 3) {
                    z = false;
                    KeyguardViewMediator.this.mLastSimStates.append(i2, i3);
                }
                z = true;
                KeyguardViewMediator.this.mLastSimStates.append(i2, i3);
            }
            if (i3 != 0 && i3 != 1) {
                if (i3 == 2 || i3 == 3) {
                    synchronized (KeyguardViewMediator.this) {
                        try {
                            KeyguardViewMediator.this.mSimWasLocked.append(i2, true);
                            KeyguardViewMediator keyguardViewMediator = KeyguardViewMediator.this;
                            keyguardViewMediator.mPendingPinLock = true;
                            if (keyguardViewMediator.mShowing) {
                                keyguardViewMediator.resetStateLocked(true);
                            } else {
                                Log.d("KeyguardViewMediator", "INTENT_VALUE_ICC_LOCKED and keygaurd isn't showing; need to show keyguard so user can enter sim pin");
                                KeyguardViewMediator.this.doKeyguardLocked(null);
                            }
                        } finally {
                        }
                    }
                    return;
                }
                if (i3 == 5) {
                    synchronized (KeyguardViewMediator.this) {
                        try {
                            Log.d("KeyguardViewMediator", "READY, reset state? " + KeyguardViewMediator.this.mShowing);
                            KeyguardViewMediator keyguardViewMediator2 = KeyguardViewMediator.this;
                            if (keyguardViewMediator2.mShowing && keyguardViewMediator2.mSimWasLocked.get(i2, false)) {
                                Log.d("KeyguardViewMediator", "SIM moved to READY when the previously was locked. Reset the state.");
                                KeyguardViewMediator.this.mSimWasLocked.append(i2, false);
                                KeyguardViewMediator.this.resetStateLocked(true);
                            }
                        } finally {
                        }
                    }
                    return;
                }
                if (i3 != 6) {
                    if (i3 != 7) {
                        return;
                    }
                    synchronized (KeyguardViewMediator.this) {
                        try {
                            if (KeyguardViewMediator.this.mShowing) {
                                Log.d("KeyguardViewMediator", "PERM_DISABLED, resetStateLocked toshow permanently disabled message in lockscreen.");
                                KeyguardViewMediator.this.resetStateLocked(true);
                            } else {
                                Log.d("KeyguardViewMediator", "PERM_DISABLED and keygaurd isn't showing.");
                                KeyguardViewMediator.this.doKeyguardLocked(null);
                            }
                        } finally {
                        }
                    }
                    return;
                }
            }
            KeyguardViewMediator keyguardViewMediator3 = KeyguardViewMediator.this;
            keyguardViewMediator3.mPendingPinLock = false;
            synchronized (keyguardViewMediator3) {
                try {
                    if (KeyguardViewMediator.this.shouldWaitForProvisioning()) {
                        KeyguardViewMediator keyguardViewMediator4 = KeyguardViewMediator.this;
                        if (keyguardViewMediator4.mShowing) {
                            keyguardViewMediator4.resetStateLocked(true);
                        } else {
                            Log.d("KeyguardViewMediator", "ICC_ABSENT isn't showing, we need to show the keyguard since the device isn't provisioned yet.");
                            KeyguardViewMediator.this.doKeyguardLocked(null);
                        }
                    }
                    if (i3 == 1) {
                        if (z) {
                            Log.d("KeyguardViewMediator", "SIM moved to ABSENT when the previous state was locked. Reset the state.");
                            KeyguardViewMediator.this.resetStateLocked(true);
                        }
                        KeyguardViewMediator.this.mSimWasLocked.append(i2, false);
                    }
                } finally {
                }
            }
        }

        @Override // com.android.keyguard.KeyguardUpdateMonitorCallback
        public final void onStrongAuthStateChanged(int i) {
            KeyguardViewMediator keyguardViewMediator = KeyguardViewMediator.this;
            if (keyguardViewMediator.mLockPatternUtils.isUserInLockdown(keyguardViewMediator.mSelectedUserInteractor.getSelectedUserId())) {
                keyguardViewMediator.doKeyguardLocked(null);
            }
        }

        @Override // com.android.keyguard.KeyguardUpdateMonitorCallback
        public final void onTrustChanged(int i) {
            if (i == KeyguardViewMediator.this.mSelectedUserInteractor.getSelectedUserId()) {
                synchronized (KeyguardViewMediator.this) {
                    KeyguardViewMediator keyguardViewMediator = KeyguardViewMediator.this;
                    KeyguardViewMediator.m823$$Nest$mnotifyTrustedChangedLocked(keyguardViewMediator, keyguardViewMediator.mUpdateMonitor.getUserHasTrust(i));
                }
            }
        }

        @Override // com.android.keyguard.KeyguardUpdateMonitorCallback
        public final void onUserSwitchComplete(int i) {
            KeyguardViewMediator keyguardViewMediator = KeyguardViewMediator.this;
            keyguardViewMediator.mIgnoreDismiss = false;
            Log.d("KeyguardViewMediator", String.format("onUserSwitchComplete %d", Integer.valueOf(i)));
            keyguardViewMediator.mHandler.postDelayed(new KeyguardViewMediator$$ExternalSyntheticLambda7(1, this), 500L);
        }

        @Override // com.android.keyguard.KeyguardUpdateMonitorCallback
        public final void onUserSwitching(int i) {
            Log.d("KeyguardViewMediator", String.format("onUserSwitching %d", Integer.valueOf(i)));
            synchronized (KeyguardViewMediator.this) {
                KeyguardViewMediator keyguardViewMediator = KeyguardViewMediator.this;
                keyguardViewMediator.mIgnoreDismiss = true;
                KeyguardViewMediator.m823$$Nest$mnotifyTrustedChangedLocked(keyguardViewMediator, keyguardViewMediator.mUpdateMonitor.getUserHasTrust(i));
                KeyguardViewMediator.this.resetKeyguardDonePendingLocked();
                KeyguardViewMediator.this.resetStateLocked(true);
                KeyguardViewMediator.this.adjustStatusBarLocked(false, false);
            }
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.keyguard.KeyguardViewMediator$4, reason: invalid class name */
    public final class AnonymousClass4 {
        public AnonymousClass4() {
        }

        public final void keyguardGone() {
            Trace.beginSection("KeyguardViewMediator.mViewMediatorCallback#keyguardGone");
            if (KeyguardViewMediator.DEBUG) {
                Log.d("KeyguardViewMediator", "keyguardGone");
            }
            KeyguardViewMediator keyguardViewMediator = KeyguardViewMediator.this;
            NotificationShadeWindowControllerImpl notificationShadeWindowControllerImpl = (NotificationShadeWindowControllerImpl) ((StatusBarKeyguardViewManager) keyguardViewMediator.mKeyguardViewControllerLazy.get()).mNotificationShadeWindowController;
            NotificationShadeWindowState notificationShadeWindowState = notificationShadeWindowControllerImpl.mCurrentState;
            notificationShadeWindowState.keyguardGoingAway = false;
            notificationShadeWindowControllerImpl.apply(notificationShadeWindowState);
            KeyguardDisplayManager keyguardDisplayManager = keyguardViewMediator.mKeyguardDisplayManager;
            if (keyguardDisplayManager.mShowing) {
                MediaRouter mediaRouter = keyguardDisplayManager.mMediaRouter;
                if (mediaRouter != null) {
                    mediaRouter.removeCallback(keyguardDisplayManager.mMediaRouterCallback);
                }
                keyguardDisplayManager.updateDisplays(false);
            }
            keyguardDisplayManager.mShowing = false;
            KeyguardUpdateMonitor keyguardUpdateMonitor = keyguardViewMediator.mUpdateMonitor;
            keyguardUpdateMonitor.mBackgroundExecutor.execute(new KeyguardUpdateMonitor$$ExternalSyntheticLambda3(keyguardUpdateMonitor, 2));
            if (keyguardViewMediator.mUnlockingAndWakingFromDream) {
                Log.d("KeyguardViewMediator", "waking from dream after unlock");
                keyguardViewMediator.setUnlockAndWakeFromDream(2, false);
                if (((KeyguardStateControllerImpl) keyguardViewMediator.mKeyguardStateController).mShowing) {
                    Log.d("KeyguardViewMediator", "keyguard showing after keyguardGone, dismiss");
                    ((StatusBarKeyguardViewManager) keyguardViewMediator.mKeyguardViewControllerLazy.get()).notifyKeyguardAuthenticated(!keyguardViewMediator.mWakeAndUnlocking);
                } else {
                    Log.d("KeyguardViewMediator", "keyguard gone, waking up from dream");
                    PowerManager powerManager = keyguardViewMediator.mPM;
                    ((SystemClockImpl) keyguardViewMediator.mSystemClock).getClass();
                    powerManager.wakeUp(android.os.SystemClock.uptimeMillis(), keyguardViewMediator.mWakeAndUnlocking ? 17 : 4, "com.android.systemui:UNLOCK_DREAMING");
                }
            }
            Trace.endSection();
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.keyguard.KeyguardViewMediator$8, reason: invalid class name */
    public final class AnonymousClass8 extends IRemoteAnimationRunner.Stub {
        public final /* synthetic */ int $r8$classId = 0;
        public Object mOccludeByDreamAnimator;

        public AnonymousClass8() {
        }

        public final void onAnimationCancelled() {
            switch (this.$r8$classId) {
                case 0:
                    KeyguardViewMediator.this.mContext.getMainExecutor().execute(new KeyguardViewMediator$$ExternalSyntheticLambda7(2, this));
                    Log.d("KeyguardViewMediator", "OccludeByDreamAnimator#onAnimationCancelled. Set occluded = true");
                    KeyguardViewMediator.this.setOccluded(true, false);
                    break;
                default:
                    ((IRemoteAnimationRunner) this.mOccludeByDreamAnimator).onAnimationCancelled();
                    break;
            }
        }

        public final void onAnimationStart(int i, RemoteAnimationTarget[] remoteAnimationTargetArr, RemoteAnimationTarget[] remoteAnimationTargetArr2, RemoteAnimationTarget[] remoteAnimationTargetArr3, final IRemoteAnimationFinishedCallback iRemoteAnimationFinishedCallback) {
            final RemoteAnimationTarget remoteAnimationTarget;
            switch (this.$r8$classId) {
                case 0:
                    if (remoteAnimationTargetArr != null && remoteAnimationTargetArr.length != 0 && (remoteAnimationTarget = remoteAnimationTargetArr[0]) != null) {
                        ActivityManager.RunningTaskInfo runningTaskInfo = remoteAnimationTarget.taskInfo;
                        if (runningTaskInfo != null && runningTaskInfo.topActivityType == 5) {
                            final SyncRtSurfaceTransactionApplier syncRtSurfaceTransactionApplier = new SyncRtSurfaceTransactionApplier(((StatusBarKeyguardViewManager) KeyguardViewMediator.this.mKeyguardViewControllerLazy.get()).getViewRootImpl().getView());
                            KeyguardViewMediator.this.mContext.getMainExecutor().execute(new Runnable() { // from class: com.android.systemui.keyguard.KeyguardViewMediator$8$$ExternalSyntheticLambda1
                                @Override // java.lang.Runnable
                                public final void run() {
                                    final KeyguardViewMediator.AnonymousClass8 anonymousClass8 = KeyguardViewMediator.AnonymousClass8.this;
                                    RemoteAnimationTarget remoteAnimationTarget2 = remoteAnimationTarget;
                                    SyncRtSurfaceTransactionApplier syncRtSurfaceTransactionApplier2 = syncRtSurfaceTransactionApplier;
                                    final IRemoteAnimationFinishedCallback iRemoteAnimationFinishedCallback2 = iRemoteAnimationFinishedCallback;
                                    ValueAnimator valueAnimator = (ValueAnimator) anonymousClass8.mOccludeByDreamAnimator;
                                    if (valueAnimator != null) {
                                        valueAnimator.cancel();
                                    }
                                    ValueAnimator ofFloat = ValueAnimator.ofFloat(0.0f, 1.0f);
                                    anonymousClass8.mOccludeByDreamAnimator = ofFloat;
                                    ofFloat.setDuration(KeyguardViewMediator.this.mDreamOpenAnimationDuration);
                                    ((ValueAnimator) anonymousClass8.mOccludeByDreamAnimator).addUpdateListener(new KeyguardViewMediator$$ExternalSyntheticLambda22(remoteAnimationTarget2, syncRtSurfaceTransactionApplier2, 1));
                                    ((ValueAnimator) anonymousClass8.mOccludeByDreamAnimator).addListener(new AnimatorListenerAdapter() { // from class: com.android.systemui.keyguard.KeyguardViewMediator.8.1
                                        public boolean mIsCancelled = false;

                                        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                                        public final void onAnimationCancel(Animator animator) {
                                            this.mIsCancelled = true;
                                        }

                                        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                                        public final void onAnimationEnd(Animator animator) {
                                            try {
                                                if (!this.mIsCancelled) {
                                                    KeyguardViewMediator.m822$$Nest$mhandleSetOccluded(KeyguardViewMediator.this, true, false);
                                                }
                                                iRemoteAnimationFinishedCallback2.onAnimationFinished();
                                                AnonymousClass8.this.mOccludeByDreamAnimator = null;
                                            } catch (RemoteException e) {
                                                Log.e("KeyguardViewMediator", "Failed to finish transition", e);
                                            }
                                        }
                                    });
                                    ((ValueAnimator) anonymousClass8.mOccludeByDreamAnimator).start();
                                }
                            });
                            break;
                        } else {
                            Log.w("KeyguardViewMediator", "The occluding app isn't Dream; finishing up. Please check that the config is correct.");
                        }
                    } else {
                        Log.d("KeyguardViewMediator", "No apps provided to the OccludeByDream runner; skipping occluding animation.");
                    }
                    KeyguardViewMediator.this.setOccluded(true, false);
                    iRemoteAnimationFinishedCallback.onAnimationFinished();
                    break;
                default:
                    if (((StatusBarKeyguardViewManager) KeyguardViewMediator.this.mKeyguardViewControllerLazy.get()).getViewRootImpl() == null) {
                        Log.w("KeyguardViewMediator", "Skipping remote animation - view root not ready");
                        break;
                    } else {
                        ((IRemoteAnimationRunner) this.mOccludeByDreamAnimator).onAnimationStart(i, remoteAnimationTargetArr, remoteAnimationTargetArr2, remoteAnimationTargetArr3, iRemoteAnimationFinishedCallback);
                        break;
                    }
            }
        }

        public AnonymousClass8(IRemoteAnimationRunner iRemoteAnimationRunner) {
            this.mOccludeByDreamAnimator = iRemoteAnimationRunner;
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.keyguard.KeyguardViewMediator$9, reason: invalid class name */
    public final class AnonymousClass9 extends IRemoteAnimationRunner.Stub {
        public ValueAnimator mUnoccludeAnimator;
        public final Matrix mUnoccludeMatrix = new Matrix();

        public AnonymousClass9() {
        }

        public final void onAnimationCancelled() {
            KeyguardViewMediator.this.mContext.getMainExecutor().execute(new KeyguardViewMediator$$ExternalSyntheticLambda7(3, this));
            Log.d("KeyguardViewMediator", "Unocclude animation cancelled.");
            KeyguardViewMediator.this.mInteractionJankMonitor.cancel(64);
        }

        public final void onAnimationStart(int i, RemoteAnimationTarget[] remoteAnimationTargetArr, final RemoteAnimationTarget[] remoteAnimationTargetArr2, RemoteAnimationTarget[] remoteAnimationTargetArr3, final IRemoteAnimationFinishedCallback iRemoteAnimationFinishedCallback) {
            RemoteAnimationTarget remoteAnimationTarget;
            Log.d("KeyguardViewMediator", "UnoccludeAnimator#onAnimationStart. Set occluded = false.");
            KeyguardViewMediator keyguardViewMediator = KeyguardViewMediator.this;
            keyguardViewMediator.mInteractionJankMonitor.begin(keyguardViewMediator.createInteractionJankMonitorConf(64, null).setTag("UNOCCLUDE"));
            KeyguardViewMediator.this.setOccluded(false, true);
            if (remoteAnimationTargetArr == null || remoteAnimationTargetArr.length == 0 || (remoteAnimationTarget = remoteAnimationTargetArr[0]) == null) {
                Log.d("KeyguardViewMediator", "No apps provided to unocclude runner; skipping animation and unoccluding.");
                iRemoteAnimationFinishedCallback.onAnimationFinished();
                return;
            }
            KeyguardViewMediator keyguardViewMediator2 = KeyguardViewMediator.this;
            keyguardViewMediator2.mRemoteAnimationTarget = remoteAnimationTarget;
            ActivityManager.RunningTaskInfo runningTaskInfo = remoteAnimationTarget.taskInfo;
            final boolean z = runningTaskInfo != null && runningTaskInfo.topActivityType == 5;
            final SyncRtSurfaceTransactionApplier syncRtSurfaceTransactionApplier = new SyncRtSurfaceTransactionApplier(((StatusBarKeyguardViewManager) keyguardViewMediator2.mKeyguardViewControllerLazy.get()).getViewRootImpl().getView());
            KeyguardViewMediator.this.mContext.getMainExecutor().execute(new Runnable() { // from class: com.android.systemui.keyguard.KeyguardViewMediator$9$$ExternalSyntheticLambda1
                /* JADX WARN: Code restructure failed: missing block: B:35:0x00a6, code lost:
                
                    if (r10.keyguardUpdateMonitor.isEncryptedOrLockdown(((com.android.systemui.settings.UserTrackerImpl) r10.userTracker).getUserId()) == false) goto L33;
                 */
                @Override // java.lang.Runnable
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct add '--show-bad-code' argument
                */
                public final void run() {
                    /*
                        r10 = this;
                        r0 = 2
                        r1 = 1
                        com.android.systemui.keyguard.KeyguardViewMediator$9 r2 = com.android.systemui.keyguard.KeyguardViewMediator.AnonymousClass9.this
                        boolean r3 = r2
                        android.view.RemoteAnimationTarget[] r4 = r3
                        android.view.IRemoteAnimationFinishedCallback r5 = r4
                        android.view.SyncRtSurfaceTransactionApplier r10 = r5
                        android.animation.ValueAnimator r6 = r2.mUnoccludeAnimator
                        if (r6 == 0) goto L13
                        r6.cancel()
                    L13:
                        r6 = 1065353216(0x3f800000, float:1.0)
                        if (r3 != 0) goto L50
                        com.android.systemui.keyguard.KeyguardViewMediator r7 = com.android.systemui.keyguard.KeyguardViewMediator.this
                        boolean r7 = r7.mShowCommunalWhenUnoccluding
                        if (r7 == 0) goto L1e
                        goto L50
                    L1e:
                        float[] r3 = new float[r0]
                        r3 = {x00ce: FILL_ARRAY_DATA , data: [1065353216, 0} // fill-array
                        android.animation.ValueAnimator r3 = android.animation.ValueAnimator.ofFloat(r3)
                        r2.mUnoccludeAnimator = r3
                        r6 = 250(0xfa, double:1.235E-321)
                        r3.setDuration(r6)
                        android.animation.ValueAnimator r3 = r2.mUnoccludeAnimator
                        android.view.animation.Interpolator r4 = com.android.app.animation.Interpolators.TOUCH_RESPONSE
                        r3.setInterpolator(r4)
                        android.animation.ValueAnimator r3 = r2.mUnoccludeAnimator
                        com.android.systemui.keyguard.KeyguardViewMediator$$ExternalSyntheticLambda22 r4 = new com.android.systemui.keyguard.KeyguardViewMediator$$ExternalSyntheticLambda22
                        r4.<init>(r2, r10, r0)
                        r3.addUpdateListener(r4)
                        android.animation.ValueAnimator r10 = r2.mUnoccludeAnimator
                        com.android.systemui.keyguard.KeyguardViewMediator$16 r0 = new com.android.systemui.keyguard.KeyguardViewMediator$16
                        r0.<init>(r2, r5, r1)
                        r10.addListener(r0)
                        android.animation.ValueAnimator r10 = r2.mUnoccludeAnimator
                        r10.start()
                        goto Lcc
                    L50:
                        int r10 = r4.length
                        r0 = 0
                        r7 = r0
                    L53:
                        if (r7 >= r10) goto L78
                        r8 = r4[r7]
                        int r9 = r8.mode
                        if (r9 == 0) goto L5c
                        goto L6c
                    L5c:
                        android.view.SurfaceControl$Transaction r9 = new android.view.SurfaceControl$Transaction
                        r9.<init>()
                        android.view.SurfaceControl r8 = r8.leash     // Catch: java.lang.Throwable -> L6e
                        r9.setAlpha(r8, r6)     // Catch: java.lang.Throwable -> L6e
                        r9.apply()     // Catch: java.lang.Throwable -> L6e
                        r9.close()
                    L6c:
                        int r7 = r7 + r1
                        goto L53
                    L6e:
                        r10 = move-exception
                        r9.close()     // Catch: java.lang.Throwable -> L73
                        goto L77
                    L73:
                        r0 = move-exception
                        r10.addSuppressed(r0)
                    L77:
                        throw r10
                    L78:
                        if (r3 == 0) goto Lb0
                        com.android.systemui.keyguard.KeyguardViewMediator r10 = com.android.systemui.keyguard.KeyguardViewMediator.this
                        dagger.Lazy r10 = r10.mDreamViewModel
                        java.lang.Object r10 = r10.get()
                        com.android.systemui.dreams.ui.viewmodel.DreamViewModel r10 = (com.android.systemui.dreams.ui.viewmodel.DreamViewModel) r10
                        com.android.systemui.communal.domain.interactor.CommunalInteractor r3 = r10.communalInteractor
                        kotlinx.coroutines.flow.ReadonlyStateFlow r3 = r3.isCommunalEnabled
                        kotlinx.coroutines.flow.MutableStateFlow r3 = r3.$$delegate_0
                        kotlinx.coroutines.flow.StateFlowImpl r3 = (kotlinx.coroutines.flow.StateFlowImpl) r3
                        java.lang.Object r3 = r3.getValue()
                        java.lang.Boolean r3 = (java.lang.Boolean) r3
                        boolean r3 = r3.booleanValue()
                        if (r3 == 0) goto La9
                        com.android.systemui.settings.UserTracker r3 = r10.userTracker
                        com.android.systemui.settings.UserTrackerImpl r3 = (com.android.systemui.settings.UserTrackerImpl) r3
                        int r3 = r3.getUserId()
                        com.android.keyguard.KeyguardUpdateMonitor r4 = r10.keyguardUpdateMonitor
                        boolean r3 = r4.isEncryptedOrLockdown(r3)
                        if (r3 != 0) goto La9
                        goto Laa
                    La9:
                        r1 = r0
                    Laa:
                        com.android.systemui.keyguard.domain.interactor.FromDreamingTransitionInteractor r10 = r10.fromDreamingTransitionInteractor
                        r10.startToLockscreenOrGlanceableHubTransition(r1)
                        goto Lc8
                    Lb0:
                        com.android.systemui.keyguard.KeyguardViewMediator r10 = com.android.systemui.keyguard.KeyguardViewMediator.this
                        dagger.Lazy r10 = r10.mCommunalTransitionViewModel
                        java.lang.Object r10 = r10.get()
                        com.android.systemui.communal.ui.viewmodel.CommunalTransitionViewModel r10 = (com.android.systemui.communal.ui.viewmodel.CommunalTransitionViewModel) r10
                        r10.getClass()
                        com.android.compose.animation.scene.SceneKey r0 = com.android.systemui.communal.shared.model.CommunalScenes.Communal
                        java.lang.String r1 = "transition view model"
                        r3 = 12
                        com.android.systemui.communal.domain.interactor.CommunalSceneInteractor r10 = r10.communalSceneInteractor
                        com.android.systemui.communal.domain.interactor.CommunalSceneInteractor.snapToScene$default(r10, r0, r1, r3)
                    Lc8:
                        com.android.systemui.keyguard.KeyguardViewMediator r10 = com.android.systemui.keyguard.KeyguardViewMediator.this
                        r10.mUnoccludeFinishedCallback = r5
                    Lcc:
                        return
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.keyguard.KeyguardViewMediator$9$$ExternalSyntheticLambda1.run():void");
                }
            });
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class DismissMessage {
        public final IKeyguardDismissCallback mCallback;
        public final CharSequence mMessage;

        public DismissMessage(IKeyguardDismissCallback iKeyguardDismissCallback, CharSequence charSequence) {
            this.mCallback = iKeyguardDismissCallback;
            this.mMessage = charSequence;
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class OccludeActivityLaunchRemoteAnimationRunner extends IRemoteAnimationRunner.Stub {
        public final AnonymousClass5 mActivityLaunchController;
        public ActivityTransitionAnimator.Runner mRunner;
        public final /* synthetic */ KeyguardViewMediator this$0$1;

        public OccludeActivityLaunchRemoteAnimationRunner(AnonymousClass5 anonymousClass5) {
            this.this$0$1 = KeyguardViewMediator.this;
            this.mActivityLaunchController = anonymousClass5;
        }

        public final void onAnimationCancelled() {
            onAnimationCancelled$com$android$systemui$keyguard$KeyguardViewMediator$ActivityLaunchRemoteAnimationRunner();
            Log.d("KeyguardViewMediator", "Occlude animation cancelled by WM.");
            KeyguardViewMediator.this.mInteractionJankMonitor.cancel(64);
        }

        public final void onAnimationCancelled$com$android$systemui$keyguard$KeyguardViewMediator$ActivityLaunchRemoteAnimationRunner() {
            ActivityTransitionAnimator.Runner runner = this.mRunner;
            if (runner != null) {
                runner.onAnimationCancelled();
            }
        }

        public final void onAnimationStart(int i, RemoteAnimationTarget[] remoteAnimationTargetArr, RemoteAnimationTarget[] remoteAnimationTargetArr2, RemoteAnimationTarget[] remoteAnimationTargetArr3, IRemoteAnimationFinishedCallback iRemoteAnimationFinishedCallback) {
            if (remoteAnimationTargetArr.length > 0) {
                KeyguardViewMediator.this.mOccludingRemoteAnimationTarget = remoteAnimationTargetArr[0];
            }
            onAnimationStart$com$android$systemui$keyguard$KeyguardViewMediator$ActivityLaunchRemoteAnimationRunner(i, remoteAnimationTargetArr, remoteAnimationTargetArr2, remoteAnimationTargetArr3, iRemoteAnimationFinishedCallback);
            KeyguardViewMediator keyguardViewMediator = KeyguardViewMediator.this;
            keyguardViewMediator.mInteractionJankMonitor.begin(keyguardViewMediator.createInteractionJankMonitorConf(64, null).setTag("OCCLUDE"));
            Log.d("KeyguardViewMediator", "OccludeAnimator#onAnimationStart. Set occluded = true.");
            KeyguardViewMediator.this.setOccluded(true, false);
        }

        public final void onAnimationStart$com$android$systemui$keyguard$KeyguardViewMediator$ActivityLaunchRemoteAnimationRunner(int i, RemoteAnimationTarget[] remoteAnimationTargetArr, RemoteAnimationTarget[] remoteAnimationTargetArr2, RemoteAnimationTarget[] remoteAnimationTargetArr3, IRemoteAnimationFinishedCallback iRemoteAnimationFinishedCallback) {
            ActivityTransitionAnimator.Runner createRunner = ((ActivityTransitionAnimator) this.this$0$1.mActivityTransitionAnimator.get()).createRunner(this.mActivityLaunchController);
            this.mRunner = createRunner;
            createRunner.onAnimationStart(i, remoteAnimationTargetArr, remoteAnimationTargetArr2, remoteAnimationTargetArr3, iRemoteAnimationFinishedCallback);
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class StartKeyguardExitAnimParams {
        public long fadeoutDuration;
        public RemoteAnimationTarget[] mApps;
        public IRemoteAnimationFinishedCallback mFinishedCallback;
        public RemoteAnimationTarget[] mNonApps;
        public RemoteAnimationTarget[] mWallpapers;
        public long startTime;
    }

    /* renamed from: -$$Nest$mhandleKeyguardDoneDrawing, reason: not valid java name */
    public static void m818$$Nest$mhandleKeyguardDoneDrawing(KeyguardViewMediator keyguardViewMediator) {
        keyguardViewMediator.getClass();
        Trace.beginSection("KeyguardViewMediator#handleKeyguardDoneDrawing");
        synchronized (keyguardViewMediator) {
            try {
                boolean z = DEBUG;
                if (z) {
                    Log.d("KeyguardViewMediator", "handleKeyguardDoneDrawing");
                }
                if (keyguardViewMediator.mWaitingUntilKeyguardVisible) {
                    if (z) {
                        Log.d("KeyguardViewMediator", "handleKeyguardDoneDrawing: notifying mWaitingUntilKeyguardVisible");
                    }
                    keyguardViewMediator.mWaitingUntilKeyguardVisible = false;
                    keyguardViewMediator.notifyAll();
                    keyguardViewMediator.mHandler.removeMessages(8);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        Trace.endSection();
    }

    /* renamed from: -$$Nest$mhandleNotifyFinishedGoingToSleep, reason: not valid java name */
    public static void m819$$Nest$mhandleNotifyFinishedGoingToSleep(KeyguardViewMediator keyguardViewMediator) {
        synchronized (keyguardViewMediator) {
            try {
                if (DEBUG) {
                    Log.d("KeyguardViewMediator", "handleNotifyFinishedGoingToSleep");
                }
                ((StatusBarKeyguardViewManager) keyguardViewMediator.mKeyguardViewControllerLazy.get()).mPrimaryBouncerInteractor.hide();
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    /* renamed from: -$$Nest$mhandleNotifyStartedWakingUp, reason: not valid java name */
    public static void m820$$Nest$mhandleNotifyStartedWakingUp(KeyguardViewMediator keyguardViewMediator) {
        WindowInsetsController windowInsetsController;
        keyguardViewMediator.getClass();
        Trace.beginSection("KeyguardViewMediator#handleMotifyStartedWakingUp");
        synchronized (keyguardViewMediator) {
            try {
                if (DEBUG) {
                    Log.d("KeyguardViewMediator", "handleNotifyWakingUp");
                }
                StatusBarKeyguardViewManager statusBarKeyguardViewManager = (StatusBarKeyguardViewManager) keyguardViewMediator.mKeyguardViewControllerLazy.get();
                statusBarKeyguardViewManager.mIsSleeping = false;
                NotificationShadeWindowView notificationShadeWindowView = ((NotificationShadeWindowControllerImpl) statusBarKeyguardViewManager.mNotificationShadeWindowController).mWindowRootView;
                if (notificationShadeWindowView != null && (windowInsetsController = notificationShadeWindowView.getWindowInsetsController()) != null) {
                    windowInsetsController.setAnimationsDisabled(false);
                }
                NavigationBarView navigationBarView = statusBarKeyguardViewManager.mCentralSurfaces.getNavigationBarView();
                if (navigationBarView != null) {
                    View view = navigationBarView.mVertical;
                    if (view != null) {
                        view.animate().alpha(1.0f).setDuration(125L).start();
                    }
                    View view2 = navigationBarView.mHorizontal;
                    if (view2 != null) {
                        view2.animate().alpha(1.0f).setDuration(125L).start();
                    }
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        Trace.endSection();
    }

    /* renamed from: -$$Nest$mhandleReset, reason: not valid java name */
    public static void m821$$Nest$mhandleReset(KeyguardViewMediator keyguardViewMediator, boolean z) {
        synchronized (keyguardViewMediator) {
            try {
                if (DEBUG) {
                    Log.d("KeyguardViewMediator", "handleReset");
                }
                ((StatusBarKeyguardViewManager) keyguardViewMediator.mKeyguardViewControllerLazy.get()).reset(z, false);
            } catch (Throwable th) {
                throw th;
            }
        }
        keyguardViewMediator.scheduleNonStrongBiometricIdleTimeout();
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x006d A[Catch: all -> 0x0048, TryCatch #0 {all -> 0x0048, blocks: (B:28:0x0040, B:6:0x004b, B:8:0x0051, B:10:0x005d, B:13:0x0063, B:15:0x0069, B:17:0x006d, B:18:0x0088), top: B:27:0x0040 }] */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0051 A[Catch: all -> 0x0048, TryCatch #0 {all -> 0x0048, blocks: (B:28:0x0040, B:6:0x004b, B:8:0x0051, B:10:0x005d, B:13:0x0063, B:15:0x0069, B:17:0x006d, B:18:0x0088), top: B:27:0x0040 }] */
    /* renamed from: -$$Nest$mhandleSetOccluded, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static void m822$$Nest$mhandleSetOccluded(com.android.systemui.keyguard.KeyguardViewMediator r4, boolean r5, boolean r6) {
        /*
            r4.getClass()
            java.lang.String r0 = "isOccluded="
            java.lang.String r1 = "KeyguardViewMediator#handleSetOccluded"
            android.os.Trace.beginSection(r1)
            java.lang.String r1 = "KeyguardViewMediator"
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r3 = "handleSetOccluded("
            r2.<init>(r3)
            r2.append(r5)
            java.lang.String r3 = ")"
            r2.append(r3)
            java.lang.String r2 = r2.toString()
            android.util.Log.d(r1, r2)
            java.lang.Integer r1 = java.lang.Integer.valueOf(r5)
            java.lang.Integer r2 = java.lang.Integer.valueOf(r6)
            java.lang.Object[] r1 = new java.lang.Object[]{r1, r2}
            r2 = 36080(0x8cf0, float:5.0559E-41)
            android.util.EventLog.writeEvent(r2, r1)
            com.android.internal.jank.InteractionJankMonitor r1 = r4.mInteractionJankMonitor
            r2 = 23
            r1.cancel(r2)
            monitor-enter(r4)
            r1 = 1
            r2 = 0
            if (r5 == 0) goto L4a
            com.android.keyguard.KeyguardUpdateMonitor r3 = r4.mUpdateMonitor     // Catch: java.lang.Throwable -> L48
            boolean r3 = r3.mSecureCameraLaunched     // Catch: java.lang.Throwable -> L48
            if (r3 == 0) goto L4a
            r3 = r1
            goto L4b
        L48:
            r5 = move-exception
            goto L8d
        L4a:
            r3 = r2
        L4b:
            r4.mPowerGestureIntercepted = r3     // Catch: java.lang.Throwable -> L48
            boolean r3 = r4.mOccluded     // Catch: java.lang.Throwable -> L48
            if (r3 == r5) goto L69
            r4.mOccluded = r5     // Catch: java.lang.Throwable -> L48
            dagger.Lazy r3 = r4.mKeyguardViewControllerLazy     // Catch: java.lang.Throwable -> L48
            java.lang.Object r3 = r3.get()     // Catch: java.lang.Throwable -> L48
            com.android.systemui.statusbar.phone.StatusBarKeyguardViewManager r3 = (com.android.systemui.statusbar.phone.StatusBarKeyguardViewManager) r3     // Catch: java.lang.Throwable -> L48
            if (r6 == 0) goto L62
            boolean r6 = r4.mDeviceInteractive     // Catch: java.lang.Throwable -> L48
            if (r6 == 0) goto L62
            goto L63
        L62:
            r1 = r2
        L63:
            r3.setOccluded(r5, r1)     // Catch: java.lang.Throwable -> L48
            r4.adjustStatusBarLocked(r2, r2)     // Catch: java.lang.Throwable -> L48
        L69:
            boolean r6 = com.android.systemui.keyguard.KeyguardViewMediator.DEBUG     // Catch: java.lang.Throwable -> L48
            if (r6 == 0) goto L88
            java.lang.String r6 = "KeyguardViewMediator"
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L48
            r1.<init>(r0)     // Catch: java.lang.Throwable -> L48
            r1.append(r5)     // Catch: java.lang.Throwable -> L48
            java.lang.String r5 = ",mPowerGestureIntercepted="
            r1.append(r5)     // Catch: java.lang.Throwable -> L48
            boolean r5 = r4.mPowerGestureIntercepted     // Catch: java.lang.Throwable -> L48
            r1.append(r5)     // Catch: java.lang.Throwable -> L48
            java.lang.String r5 = r1.toString()     // Catch: java.lang.Throwable -> L48
            android.util.Log.d(r6, r5)     // Catch: java.lang.Throwable -> L48
        L88:
            monitor-exit(r4)     // Catch: java.lang.Throwable -> L48
            android.os.Trace.endSection()
            return
        L8d:
            monitor-exit(r4)     // Catch: java.lang.Throwable -> L48
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.keyguard.KeyguardViewMediator.m822$$Nest$mhandleSetOccluded(com.android.systemui.keyguard.KeyguardViewMediator, boolean, boolean):void");
    }

    /* renamed from: -$$Nest$mnotifyTrustedChangedLocked, reason: not valid java name */
    public static void m823$$Nest$mnotifyTrustedChangedLocked(KeyguardViewMediator keyguardViewMediator, boolean z) {
        for (int size = keyguardViewMediator.mKeyguardStateCallbacks.size() - 1; size >= 0; size--) {
            try {
                ((IKeyguardStateCallback) keyguardViewMediator.mKeyguardStateCallbacks.get(size)).onTrustedChanged(z);
            } catch (RemoteException e) {
                Slog.w("KeyguardViewMediator", "Failed to call notifyTrustedChangedLocked", e);
                if (e instanceof DeadObjectException) {
                    keyguardViewMediator.mKeyguardStateCallbacks.remove(size);
                }
            }
        }
    }

    /* JADX WARN: Type inference failed for: r7v3, types: [com.android.systemui.animation.ActivityTransitionAnimator$Controller, com.android.systemui.keyguard.KeyguardViewMediator$5] */
    /* JADX WARN: Type inference failed for: r8v0, types: [com.android.systemui.keyguard.KeyguardViewMediator$6] */
    /* JADX WARN: Type inference failed for: r8v3, types: [com.android.systemui.keyguard.KeyguardViewMediator$11] */
    /* JADX WARN: Type inference failed for: r8v4, types: [com.android.systemui.keyguard.KeyguardViewMediator$11] */
    public KeyguardViewMediator(IActivityTaskManager iActivityTaskManager, TrustManager trustManager, Context context, PowerManager powerManager, InteractionJankMonitor interactionJankMonitor, UiEventLogger uiEventLogger, IStatusBarService iStatusBarService, LockPatternUtils lockPatternUtils, KeyguardDisplayManager keyguardDisplayManager, KeyguardUpdateMonitor keyguardUpdateMonitor, BroadcastDispatcher broadcastDispatcher, FalsingCollector falsingCollector, DreamOverlayStateController dreamOverlayStateController, DumpManager dumpManager, SystemPropertiesHelper systemPropertiesHelper, DismissCallbackRegistry dismissCallbackRegistry, WindowManagerOcclusionManager windowManagerOcclusionManager, KeyguardInteractor keyguardInteractor, SessionTracker sessionTracker, NavigationModeController navigationModeController, ProcessWrapper processWrapper, UserTracker userTracker, SysuiStatusBarStateController sysuiStatusBarStateController, DozeParameters dozeParameters, ScreenOffAnimationController screenOffAnimationController, KeyguardStateController keyguardStateController, UserSwitcherController userSwitcherController, SelectedUserInteractor selectedUserInteractor, DeviceConfigProxy deviceConfigProxy, JavaAdapter javaAdapter, SecureSettings secureSettings, SystemSettingsImpl systemSettingsImpl, SystemClock systemClock, WallpaperRepositoryImpl wallpaperRepositoryImpl, KeyguardTransitions keyguardTransitions, Lazy lazy, Lazy lazy2, Lazy lazy3, Lazy lazy4, Lazy lazy5, Lazy lazy6, Lazy lazy7, Lazy lazy8, Lazy lazy9, Lazy lazy10, Executor executor) {
        final int i = 1;
        final int i2 = 0;
        DeviceConfig.OnPropertiesChangedListener onPropertiesChangedListener = new DeviceConfig.OnPropertiesChangedListener() { // from class: com.android.systemui.keyguard.KeyguardViewMediator.1
            public final void onPropertiesChanged(DeviceConfig.Properties properties) {
                if (properties.getKeyset().contains("nav_bar_handle_show_over_lockscreen")) {
                    KeyguardViewMediator.this.mShowHomeOverLockscreen = properties.getBoolean("nav_bar_handle_show_over_lockscreen", true);
                }
            }
        };
        this.mDreamOverlayStateCallback = new DreamOverlayStateController.Callback() { // from class: com.android.systemui.keyguard.KeyguardViewMediator.2
            @Override // com.android.systemui.dreams.DreamOverlayStateController.Callback
            public final void onStateChanged() {
                KeyguardViewMediator keyguardViewMediator = KeyguardViewMediator.this;
                keyguardViewMediator.mDreamOverlayShowing = keyguardViewMediator.mDreamOverlayStateController.isOverlayActive();
            }
        };
        this.mUpdateCallback = new AnonymousClass3();
        this.mViewMediatorCallback = new AnonymousClass4();
        ?? r7 = new ActivityTransitionAnimator.Controller() { // from class: com.android.systemui.keyguard.KeyguardViewMediator.5
            @Override // com.android.systemui.animation.TransitionAnimator.Controller
            public final TransitionAnimator.State createAnimatorState() {
                int width = getTransitionContainer().getWidth();
                int height = getTransitionContainer().getHeight();
                KeyguardViewMediator keyguardViewMediator = KeyguardViewMediator.this;
                if (keyguardViewMediator.mUpdateMonitor.mSecureCameraLaunched) {
                    float f = height / 3.0f;
                    float f2 = width;
                    float f3 = f / 2.0f;
                    float f4 = keyguardViewMediator.mPowerButtonY;
                    float f5 = keyguardViewMediator.mWindowCornerRadius;
                    return new TransitionAnimator.State((int) (f4 - f3), (int) (f4 + f3), (int) (f2 - (f2 / 3.0f)), width, f5, f5);
                }
                RemoteAnimationTarget remoteAnimationTarget = keyguardViewMediator.mOccludingRemoteAnimationTarget;
                if (remoteAnimationTarget != null && remoteAnimationTarget.isTranslucent) {
                    return new TransitionAnimator.State(0, height, 0, width, 0.0f, 0.0f);
                }
                float f6 = height;
                float f7 = f6 / 2.0f;
                float f8 = width;
                float f9 = f8 / 2.0f;
                float f10 = f6 - f7;
                float f11 = f8 - f9;
                float f12 = keyguardViewMediator.mWindowCornerRadius;
                return new TransitionAnimator.State(((int) f10) / 2, (int) ((f10 / 2.0f) + f7), ((int) f11) / 2, (int) ((f11 / 2.0f) + f9), f12, f12);
            }

            @Override // com.android.systemui.animation.TransitionAnimator.Controller
            public final ViewGroup getTransitionContainer() {
                return (ViewGroup) ((StatusBarKeyguardViewManager) KeyguardViewMediator.this.mKeyguardViewControllerLazy.get()).getViewRootImpl().getView();
            }

            @Override // com.android.systemui.animation.TransitionAnimator.Controller
            public final boolean isLaunching() {
                return true;
            }

            @Override // com.android.systemui.animation.ActivityTransitionAnimator.Controller
            public final void onTransitionAnimationCancelled() {
                StringBuilder sb = new StringBuilder("Occlude launch animation cancelled. Occluded state is now: ");
                KeyguardViewMediator keyguardViewMediator = KeyguardViewMediator.this;
                CachedBluetoothDevice$$ExternalSyntheticOutline0.m(sb, keyguardViewMediator.mOccluded, "KeyguardViewMediator");
                keyguardViewMediator.mOccludeAnimationPlaying = false;
                keyguardViewMediator.mCentralSurfaces.updateIsKeyguard(false);
                ((ScrimController) keyguardViewMediator.mScrimControllerLazy.get()).setOccludeAnimationPlaying(false);
            }

            @Override // com.android.systemui.animation.TransitionAnimator.Controller
            public final void onTransitionAnimationEnd(boolean z) {
                KeyguardViewMediator keyguardViewMediator = KeyguardViewMediator.this;
                if (z) {
                    ((ShadeController) keyguardViewMediator.mShadeController.get()).instantCollapseShade();
                }
                keyguardViewMediator.mOccludeAnimationPlaying = false;
                keyguardViewMediator.mCentralSurfaces.updateIsKeyguard(false);
                ((ScrimController) keyguardViewMediator.mScrimControllerLazy.get()).setOccludeAnimationPlaying(false);
                keyguardViewMediator.mInteractionJankMonitor.end(64);
            }

            @Override // com.android.systemui.animation.TransitionAnimator.Controller
            public final void onTransitionAnimationStart(boolean z) {
                KeyguardViewMediator keyguardViewMediator = KeyguardViewMediator.this;
                keyguardViewMediator.mOccludeAnimationPlaying = true;
                ((ScrimController) keyguardViewMediator.mScrimControllerLazy.get()).setOccludeAnimationPlaying(true);
            }

            @Override // com.android.systemui.animation.TransitionAnimator.Controller
            public final void setTransitionContainer(ViewGroup viewGroup) {
                Log.wtf("KeyguardViewMediator", "Someone tried to change the launch container for the ActivityTransitionAnimator, which should never happen.");
            }
        };
        this.mOccludeAnimationController = r7;
        this.mExitAnimationRunner = new IRemoteAnimationRunner.Stub() { // from class: com.android.systemui.keyguard.KeyguardViewMediator.6
            public final void onAnimationCancelled() {
                KeyguardViewMediator keyguardViewMediator = KeyguardViewMediator.this;
                keyguardViewMediator.getClass();
                Trace.beginSection("KeyguardViewMediator#cancelKeyguardExitAnimation");
                AnonymousClass13 anonymousClass13 = keyguardViewMediator.mHandler;
                anonymousClass13.sendMessage(anonymousClass13.obtainMessage(19));
                Trace.endSection();
            }

            public final void onAnimationStart(int i3, RemoteAnimationTarget[] remoteAnimationTargetArr, RemoteAnimationTarget[] remoteAnimationTargetArr2, RemoteAnimationTarget[] remoteAnimationTargetArr3, IRemoteAnimationFinishedCallback iRemoteAnimationFinishedCallback) {
                Trace.beginSection("mExitAnimationRunner.onAnimationStart#startKeyguardExitAnimation");
                KeyguardViewMediator.this.startKeyguardExitAnimation(0L, 0L, remoteAnimationTargetArr, remoteAnimationTargetArr2, remoteAnimationTargetArr3, iRemoteAnimationFinishedCallback);
                Trace.endSection();
            }
        };
        this.mAppearAnimationRunner = new AnonymousClass7();
        this.mOccludeAnimationRunner = new OccludeActivityLaunchRemoteAnimationRunner(r7);
        this.mOccludeByDreamAnimationRunner = new AnonymousClass8();
        this.mUnoccludeAnimationRunner = new AnonymousClass9();
        this.mFoldGracePeriodProvider = new FoldGracePeriodProvider();
        KeyguardStateController.Callback callback = new KeyguardStateController.Callback() { // from class: com.android.systemui.keyguard.KeyguardViewMediator.10
            @Override // com.android.systemui.statusbar.policy.KeyguardStateController.Callback
            public final void onPrimaryBouncerShowingChanged() {
                synchronized (KeyguardViewMediator.this) {
                    KeyguardViewMediator keyguardViewMediator = KeyguardViewMediator.this;
                    KeyguardStateController keyguardStateController2 = keyguardViewMediator.mKeyguardStateController;
                    if (((KeyguardStateControllerImpl) keyguardStateController2).mPrimaryBouncerShowing && !((KeyguardStateControllerImpl) keyguardStateController2).mKeyguardGoingAway) {
                        keyguardViewMediator.mPendingPinLock = false;
                    }
                    keyguardViewMediator.adjustStatusBarLocked(((KeyguardStateControllerImpl) keyguardStateController2).mPrimaryBouncerShowing, false);
                }
            }
        };
        this.mShowCommunalWhenUnoccluding = false;
        this.mDelayedLockBroadcastReceiver = new BroadcastReceiver(this) { // from class: com.android.systemui.keyguard.KeyguardViewMediator.11
            public final /* synthetic */ KeyguardViewMediator this$0;

            {
                this.this$0 = this;
            }

            @Override // android.content.BroadcastReceiver
            public final void onReceive(Context context2, Intent intent) {
                switch (i2) {
                    case 0:
                        if ("com.android.internal.policy.impl.PhoneWindowManager.DELAYED_KEYGUARD".equals(intent.getAction())) {
                            int intExtra = intent.getIntExtra("seq", 0);
                            if (KeyguardViewMediator.DEBUG) {
                                LocalBluetoothLeBroadcast$3$$ExternalSyntheticOutline0.m(MutableObjectList$$ExternalSyntheticOutline0.m("received DELAYED_KEYGUARD_ACTION with seq = ", ", mDelayedShowingSequence = ", intExtra), this.this$0.mDelayedShowingSequence, "KeyguardViewMediator");
                            }
                            synchronized (this.this$0) {
                                KeyguardViewMediator keyguardViewMediator = this.this$0;
                                if (keyguardViewMediator.mDelayedShowingSequence == intExtra) {
                                    keyguardViewMediator.doKeyguardLocked(null);
                                }
                            }
                            return;
                        }
                        if ("com.android.internal.policy.impl.PhoneWindowManager.DELAYED_LOCK".equals(intent.getAction())) {
                            int intExtra2 = intent.getIntExtra("seq", 0);
                            int intExtra3 = intent.getIntExtra("android.intent.extra.USER_ID", 0);
                            if (intExtra3 != 0) {
                                synchronized (this.this$0) {
                                    KeyguardViewMediator keyguardViewMediator2 = this.this$0;
                                    if (keyguardViewMediator2.mDelayedProfileShowingSequence == intExtra2) {
                                        keyguardViewMediator2.mTrustManager.setDeviceLockedForUser(intExtra3, true);
                                    }
                                }
                                return;
                            }
                            return;
                        }
                        return;
                    default:
                        if ("android.intent.action.ACTION_SHUTDOWN".equals(intent.getAction())) {
                            synchronized (this.this$0) {
                                this.this$0.mShuttingDown = true;
                            }
                            return;
                        }
                        return;
                }
            }
        };
        this.mBroadcastReceiver = new BroadcastReceiver(this) { // from class: com.android.systemui.keyguard.KeyguardViewMediator.11
            public final /* synthetic */ KeyguardViewMediator this$0;

            {
                this.this$0 = this;
            }

            @Override // android.content.BroadcastReceiver
            public final void onReceive(Context context2, Intent intent) {
                switch (i) {
                    case 0:
                        if ("com.android.internal.policy.impl.PhoneWindowManager.DELAYED_KEYGUARD".equals(intent.getAction())) {
                            int intExtra = intent.getIntExtra("seq", 0);
                            if (KeyguardViewMediator.DEBUG) {
                                LocalBluetoothLeBroadcast$3$$ExternalSyntheticOutline0.m(MutableObjectList$$ExternalSyntheticOutline0.m("received DELAYED_KEYGUARD_ACTION with seq = ", ", mDelayedShowingSequence = ", intExtra), this.this$0.mDelayedShowingSequence, "KeyguardViewMediator");
                            }
                            synchronized (this.this$0) {
                                KeyguardViewMediator keyguardViewMediator = this.this$0;
                                if (keyguardViewMediator.mDelayedShowingSequence == intExtra) {
                                    keyguardViewMediator.doKeyguardLocked(null);
                                }
                            }
                            return;
                        }
                        if ("com.android.internal.policy.impl.PhoneWindowManager.DELAYED_LOCK".equals(intent.getAction())) {
                            int intExtra2 = intent.getIntExtra("seq", 0);
                            int intExtra3 = intent.getIntExtra("android.intent.extra.USER_ID", 0);
                            if (intExtra3 != 0) {
                                synchronized (this.this$0) {
                                    KeyguardViewMediator keyguardViewMediator2 = this.this$0;
                                    if (keyguardViewMediator2.mDelayedProfileShowingSequence == intExtra2) {
                                        keyguardViewMediator2.mTrustManager.setDeviceLockedForUser(intExtra3, true);
                                    }
                                }
                                return;
                            }
                            return;
                        }
                        return;
                    default:
                        if ("android.intent.action.ACTION_SHUTDOWN".equals(intent.getAction())) {
                            synchronized (this.this$0) {
                                this.this$0.mShuttingDown = true;
                            }
                            return;
                        }
                        return;
                }
            }
        };
        final AnonymousClass13 anonymousClass13 = new AnonymousClass13(Looper.myLooper());
        this.mHandler = anonymousClass13;
        this.mKeyguardGoingAwayRunnable = new AnonymousClass14();
        this.mHideAnimationFinishedRunnable = new KeyguardViewMediator$$ExternalSyntheticLambda1(this, 0);
        this.mContext = context;
        this.mUserTracker = userTracker;
        this.mFalsingCollector = falsingCollector;
        this.mLockPatternUtils = lockPatternUtils;
        this.mBroadcastDispatcher = broadcastDispatcher;
        this.mKeyguardViewControllerLazy = lazy;
        this.mDismissCallbackRegistry = dismissCallbackRegistry;
        this.mNotificationShadeDepthController = lazy3;
        this.mUiBgExecutor = executor;
        this.mUpdateMonitor = keyguardUpdateMonitor;
        this.mPM = powerManager;
        this.mTrustManager = trustManager;
        this.mUserSwitcherController = userSwitcherController;
        this.mSecureSettings = secureSettings;
        this.mSystemSettings = systemSettingsImpl;
        this.mSystemClock = systemClock;
        this.mProcessWrapper = processWrapper;
        this.mSystemPropertiesHelper = systemPropertiesHelper;
        this.mStatusBarService = iStatusBarService;
        this.mKeyguardDisplayManager = keyguardDisplayManager;
        this.mShadeController = lazy4;
        dumpManager.registerDumpable(this);
        this.mKeyguardTransitions = keyguardTransitions;
        this.mNotificationShadeWindowControllerLazy = lazy5;
        deviceConfigProxy.getClass();
        this.mShowHomeOverLockscreen = DeviceConfig.getBoolean("systemui", "nav_bar_handle_show_over_lockscreen", true);
        DeviceConfig.addOnPropertiesChangedListener("systemui", new Executor() { // from class: com.android.systemui.keyguard.KeyguardViewMediator$$ExternalSyntheticLambda2
            @Override // java.util.concurrent.Executor
            public final void execute(Runnable runnable) {
                KeyguardViewMediator.AnonymousClass13.this.post(runnable);
            }
        }, onPropertiesChangedListener);
        this.mInGestureNavigationMode = QuickStepContract.isGesturalMode(navigationModeController.addListener(new NavigationModeController.ModeChangedListener() { // from class: com.android.systemui.keyguard.KeyguardViewMediator$$ExternalSyntheticLambda3
            @Override // com.android.systemui.navigationbar.NavigationModeController.ModeChangedListener
            public final void onNavigationModeChanged(int i3) {
                KeyguardViewMediator.this.mInGestureNavigationMode = QuickStepContract.isGesturalMode(i3);
            }
        }));
        this.mDozeParameters = dozeParameters;
        this.mSelectedUserInteractor = selectedUserInteractor;
        this.mKeyguardInteractor = keyguardInteractor;
        this.mStatusBarStateController = sysuiStatusBarStateController;
        ((StatusBarStateControllerImpl) sysuiStatusBarStateController).addCallback((StatusBarStateController.StateListener) this);
        this.mKeyguardStateController = keyguardStateController;
        ((KeyguardStateControllerImpl) keyguardStateController).addCallback(callback);
        this.mKeyguardUnlockAnimationControllerLazy = lazy2;
        this.mScreenOffAnimationController = screenOffAnimationController;
        this.mInteractionJankMonitor = interactionJankMonitor;
        this.mDreamOverlayStateController = dreamOverlayStateController;
        this.mJavaAdapter = javaAdapter;
        this.mWallpaperRepository = wallpaperRepositoryImpl;
        this.mActivityTransitionAnimator = lazy6;
        this.mScrimControllerLazy = lazy7;
        this.mActivityTaskManagerService = iActivityTaskManager;
        this.mPowerButtonY = context.getResources().getDimensionPixelSize(R.dimen.physical_power_button_center_screen_location_y);
        this.mWindowCornerRadius = ScreenDecorationsUtils.getWindowCornerRadius(context);
        this.mDreamOpenAnimationDuration = (int) LockscreenToDreamingTransitionViewModel.DREAMING_ANIMATION_DURATION_MS;
        this.mUiEventLogger = uiEventLogger;
        this.mSessionTracker = sessionTracker;
        this.mDreamViewModel = lazy8;
        this.mCommunalTransitionViewModel = lazy9;
        this.mOrderUnlockAndWake = context.getResources().getBoolean(android.R.bool.config_powerDecoupleInteractiveModeFromDisplay);
        PowerManager.WakeLock newWakeLock = powerManager.newWakeLock(1, "show keyguard");
        this.mShowKeyguardWakeLock = newWakeLock;
        newWakeLock.setReferenceCounted(false);
    }

    public final void adjustStatusBarLocked(boolean z, boolean z2) {
        if (this.mStatusBarManager == null) {
            this.mStatusBarManager = (StatusBarManager) this.mContext.getSystemService("statusbar");
        }
        if (this.mStatusBarManager == null) {
            Log.w("KeyguardViewMediator", "Could not get status bar manager");
            return;
        }
        SelectedUserInteractor selectedUserInteractor = this.mSelectedUserInteractor;
        boolean z3 = DEBUG;
        ProcessWrapper processWrapper = this.mProcessWrapper;
        if (z2) {
            if (UserManager.isVisibleBackgroundUsersEnabled()) {
                processWrapper.getClass();
                if (!ProcessWrapper.isSystemUser() && !ProcessWrapper.isForegroundUser()) {
                    if (z3) {
                        Log.d("KeyguardViewMediator", "Status bar manager is disabled for visible background users");
                    }
                }
            }
            try {
                this.mStatusBarService.disableForUser(0, this.mStatusBarDisableToken, this.mContext.getPackageName(), selectedUserInteractor.getSelectedUserId());
            } catch (RemoteException e) {
                Log.d("KeyguardViewMediator", "Failed to force clear flags", e);
            }
        }
        if (z || isShowingAndNotOccluded()) {
            r5 = ((this.mShowHomeOverLockscreen && this.mInGestureNavigationMode) ? 0 : 2097152) | 16777216;
        }
        if (this.mPowerGestureIntercepted && this.mOccluded && isSecure() && this.mUpdateMonitor.isFaceEnabledAndEnrolled()) {
            r5 |= 16777216;
        }
        if (z3) {
            Log.d("KeyguardViewMediator", "adjustStatusBarLocked: mShowing=" + this.mShowing + " mOccluded=" + this.mOccluded + " isSecure=" + isSecure() + " force=" + z + " mPowerGestureIntercepted=" + this.mPowerGestureIntercepted + " --> flags=0x" + Integer.toHexString(r5));
        }
        if (UserManager.isVisibleBackgroundUsersEnabled()) {
            processWrapper.getClass();
            if (!ProcessWrapper.isSystemUser() && !ProcessWrapper.isForegroundUser()) {
                if (z3) {
                    Log.d("KeyguardViewMediator", "Status bar manager is disabled for visible background users");
                    return;
                }
                return;
            }
        }
        try {
            this.mStatusBarService.disableForUser(r5, this.mStatusBarDisableToken, this.mContext.getPackageName(), selectedUserInteractor.getSelectedUserId());
        } catch (RemoteException e2) {
            Log.d("KeyguardViewMediator", "Failed to set disable flags: " + r5, e2);
        }
    }

    public final InteractionJankMonitor.Configuration.Builder createInteractionJankMonitorConf(int i, String str) {
        InteractionJankMonitor.Configuration.Builder withView = InteractionJankMonitor.Configuration.Builder.withView(i, ((StatusBarKeyguardViewManager) this.mKeyguardViewControllerLazy.get()).getViewRootImpl().getView());
        return str != null ? withView.setTag(str) : withView;
    }

    public final void doKeyguardForChildProfilesLocked() {
        for (UserInfo userInfo : ((UserTrackerImpl) this.mUserTracker).getUserProfiles()) {
            if (userInfo.isEnabled()) {
                int i = userInfo.id;
                if (this.mLockPatternUtils.isSeparateProfileChallengeEnabled(i)) {
                    this.mTrustManager.setDeviceLockedForUser(i, true);
                }
            }
        }
    }

    public final void doKeyguardLaterLocked(long j) {
        SystemClock systemClock = this.mSystemClock;
        ((SystemClockImpl) systemClock).getClass();
        long elapsedRealtime = android.os.SystemClock.elapsedRealtime() + j;
        Intent intent = new Intent("com.android.internal.policy.impl.PhoneWindowManager.DELAYED_KEYGUARD");
        intent.setPackage(this.mContext.getPackageName());
        intent.putExtra("seq", this.mDelayedShowingSequence);
        intent.addFlags(268435456);
        this.mAlarmManager.setExactAndAllowWhileIdle(2, elapsedRealtime, PendingIntent.getBroadcast(this.mContext, 0, intent, 335544320));
        if (DEBUG) {
            LocalBluetoothLeBroadcast$3$$ExternalSyntheticOutline0.m(new StringBuilder("setting alarm to turn off keyguard, seq = "), this.mDelayedShowingSequence, "KeyguardViewMediator");
        }
        for (UserInfo userInfo : ((UserTrackerImpl) this.mUserTracker).getUserProfiles()) {
            if (userInfo.isEnabled()) {
                int i = userInfo.id;
                if (this.mLockPatternUtils.isSeparateProfileChallengeEnabled(i)) {
                    long lockTimeout = getLockTimeout(i);
                    if (lockTimeout == 0) {
                        doKeyguardForChildProfilesLocked();
                    } else {
                        ((SystemClockImpl) systemClock).getClass();
                        long elapsedRealtime2 = android.os.SystemClock.elapsedRealtime() + lockTimeout;
                        Intent intent2 = new Intent("com.android.internal.policy.impl.PhoneWindowManager.DELAYED_LOCK");
                        intent2.setPackage(this.mContext.getPackageName());
                        intent2.putExtra("seq", this.mDelayedProfileShowingSequence);
                        intent2.putExtra("android.intent.extra.USER_ID", i);
                        intent2.addFlags(268435456);
                        this.mAlarmManager.setExactAndAllowWhileIdle(2, elapsedRealtime2, PendingIntent.getBroadcast(this.mContext, 0, intent2, 335544320));
                    }
                }
            }
        }
    }

    public final void doKeyguardLocked(Bundle bundle) {
        boolean z = this.mExternallyEnabled;
        SelectedUserInteractor selectedUserInteractor = this.mSelectedUserInteractor;
        boolean z2 = DEBUG;
        if (!z && !this.mLockPatternUtils.isUserInLockdown(selectedUserInteractor.getSelectedUserId())) {
            if (z2) {
                Log.d("KeyguardViewMediator", "doKeyguard: not showing because externally disabled");
            }
            this.mNeedToReshowWhenReenabled = true;
            return;
        }
        if (this.mShowing) {
            KeyguardStateControllerImpl keyguardStateControllerImpl = (KeyguardStateControllerImpl) this.mKeyguardStateController;
            if (keyguardStateControllerImpl.mShowing) {
                if (!this.mPM.isInteractive() || this.mHiding) {
                    Log.e("KeyguardViewMediator", "doKeyguard: already showing, but re-showing because we're interactive or were in the middle of hiding.");
                } else {
                    if (!keyguardStateControllerImpl.mKeyguardGoingAway) {
                        if (z2) {
                            Log.d("KeyguardViewMediator", "doKeyguard: not showing (instead, resetting) because it is already showing, we're interactive, we were not previously hiding. It should be safe to short-circuit here.");
                        }
                        resetStateLocked(false);
                        return;
                    }
                    Log.e("KeyguardViewMediator", "doKeyguard: we're still showing, but going away. Re-show the keyguard rather than short-circuiting and resetting.");
                }
            }
        }
        boolean z3 = SystemProperties.getBoolean("keyguard.no_require_sim", false);
        KeyguardUpdateMonitor keyguardUpdateMonitor = this.mUpdateMonitor;
        boolean z4 = keyguardUpdateMonitor.isSimPinSecure() || ((SubscriptionManager.isValidSubscriptionId(keyguardUpdateMonitor.getNextSubIdForState(1)) || SubscriptionManager.isValidSubscriptionId(keyguardUpdateMonitor.getNextSubIdForState(7))) && !z3);
        if (!z4 && shouldWaitForProvisioning()) {
            if (z2) {
                Log.d("KeyguardViewMediator", "doKeyguard: not showing because device isn't provisioned and the sim is not locked or missing");
                return;
            }
            return;
        }
        boolean z5 = bundle != null && bundle.getBoolean("force_show", false);
        if (!this.mLockPatternUtils.isLockScreenDisabled(selectedUserInteractor.getSelectedUserId()) || z4 || z5) {
            if (z2) {
                Log.d("KeyguardViewMediator", "doKeyguard: showing the lock screen");
            }
            showKeyguard(bundle);
        } else if (z2) {
            Log.d("KeyguardViewMediator", "doKeyguard: not showing because lockscreen is off");
        }
    }

    @Override // com.android.systemui.CoreStartable, com.android.systemui.Dumpable
    public final void dump(PrintWriter printWriter, String[] strArr) {
        printWriter.print("  mSystemReady: ");
        printWriter.println(this.mSystemReady);
        printWriter.print("  mBootCompleted: ");
        printWriter.println(this.mBootCompleted);
        printWriter.print("  mBootSendUserPresent: ");
        printWriter.println(this.mBootSendUserPresent);
        printWriter.print("  mExternallyEnabled: ");
        printWriter.println(this.mExternallyEnabled);
        printWriter.print("  mShuttingDown: ");
        printWriter.println(this.mShuttingDown);
        printWriter.print("  mNeedToReshowWhenReenabled: ");
        printWriter.println(this.mNeedToReshowWhenReenabled);
        printWriter.print("  mShowing: ");
        printWriter.println(this.mShowing);
        printWriter.print("  mInputRestricted: ");
        printWriter.println(this.mInputRestricted);
        printWriter.print("  mOccluded: ");
        printWriter.println(this.mOccluded);
        printWriter.print("  mDelayedShowingSequence: ");
        printWriter.println(this.mDelayedShowingSequence);
        printWriter.print("  mDeviceInteractive: ");
        printWriter.println(this.mDeviceInteractive);
        printWriter.print("  mGoingToSleep: ");
        printWriter.println(this.mGoingToSleep);
        printWriter.print("  mHiding: ");
        printWriter.println(this.mHiding);
        printWriter.print("  mDozing: ");
        printWriter.println(this.mDozing);
        printWriter.print("  mAodShowing: ");
        printWriter.println(this.mAodShowing);
        printWriter.print("  mWaitingUntilKeyguardVisible: ");
        printWriter.println(this.mWaitingUntilKeyguardVisible);
        printWriter.print("  mKeyguardDonePending: ");
        printWriter.println(this.mKeyguardDonePending);
        printWriter.print("  mHideAnimationRun: ");
        printWriter.println(this.mHideAnimationRun);
        printWriter.print("  mPendingReset: ");
        printWriter.println(this.mPendingReset);
        printWriter.print("  mPendingLock: ");
        printWriter.println(this.mPendingLock);
        printWriter.print("  wakeAndUnlocking: ");
        printWriter.println(this.mWakeAndUnlocking);
        printWriter.print("  mPendingPinLock: ");
        printWriter.println(this.mPendingPinLock);
        printWriter.print("  mPowerGestureIntercepted: ");
        printWriter.println(this.mPowerGestureIntercepted);
    }

    public final void exitKeyguardAndFinishSurfaceBehindRemoteAnimation() {
        Log.d("KeyguardViewMediator", "exitKeyguardAndFinishSurfaceBehindRemoteAnimation");
        if (this.mSurfaceBehindRemoteAnimationRunning || this.mSurfaceBehindRemoteAnimationRequested) {
            ((StatusBarKeyguardViewManager) this.mKeyguardViewControllerLazy.get()).mShadeLockscreenInteractor.blockExpansionForCurrentTouch();
            boolean z = this.mShowing;
            InteractionJankMonitor.getInstance().end(29);
            DejankUtils.postAfterTraversal(new KeyguardViewMediator$$ExternalSyntheticLambda0(this, z, 0));
            return;
        }
        StringBuilder sb = new StringBuilder("skip onKeyguardExitRemoteAnimationFinished showKeyguard=false surfaceAnimationRunning=");
        sb.append(this.mSurfaceBehindRemoteAnimationRunning);
        sb.append(" surfaceAnimationRequested=");
        CachedBluetoothDevice$$ExternalSyntheticOutline0.m(sb, this.mSurfaceBehindRemoteAnimationRequested, "KeyguardViewMediator");
    }

    public final void finishSurfaceBehindRemoteAnimation(boolean z) {
        ((KeyguardUnlockAnimationController) this.mKeyguardUnlockAnimationControllerLazy.get()).notifyFinishedKeyguardExitAnimation(z);
        this.mSurfaceBehindRemoteAnimationRequested = false;
        this.mSurfaceBehindRemoteAnimationRunning = false;
        ((KeyguardStateControllerImpl) this.mKeyguardStateController).notifyKeyguardGoingAway(false);
        IRemoteAnimationFinishedCallback iRemoteAnimationFinishedCallback = this.mSurfaceBehindRemoteAnimationFinishedCallback;
        if (iRemoteAnimationFinishedCallback != null) {
            try {
                iRemoteAnimationFinishedCallback.onAnimationFinished();
            } finally {
                try {
                } finally {
                }
            }
        }
        if (z) {
            this.mKeyguardInteractor.showKeyguard();
        }
    }

    public final long getLockTimeout(int i) {
        long intForUser = this.mSecureSettings.getIntForUser("lock_screen_lock_after_timeout", 5000, i);
        long maximumTimeToLock = this.mLockPatternUtils.getDevicePolicyManager().getMaximumTimeToLock(null, i);
        return maximumTimeToLock <= 0 ? intForUser : Math.max(Math.min(maximumTimeToLock - Math.max(this.mSystemSettings.getIntForUser("screen_off_timeout", 30000, i), 0L), intForUser), 0L);
    }

    public final void handleHide() {
        Trace.beginSection("KeyguardViewMediator#handleHide");
        if (this.mAodShowing) {
            PowerManager powerManager = this.mPM;
            ((SystemClockImpl) this.mSystemClock).getClass();
            powerManager.wakeUp(android.os.SystemClock.uptimeMillis(), 4, "com.android.systemui:BOUNCER_DOZING");
        }
        synchronized (this) {
            try {
                if (DEBUG) {
                    Log.d("KeyguardViewMediator", "handleHide");
                }
                if (!this.mWakeAndUnlocking) {
                    setUnlockAndWakeFromDream(0, ((StatusBarStateControllerImpl) this.mStatusBarStateController).mIsDreaming && this.mPM.isInteractive());
                }
                if (!this.mBootCompleted || ((!this.mShowing || this.mOccluded) && !this.mUnlockingAndWakingFromDream)) {
                    StatusBarKeyguardViewManager statusBarKeyguardViewManager = (StatusBarKeyguardViewManager) this.mKeyguardViewControllerLazy.get();
                    ((SystemClockImpl) this.mSystemClock).getClass();
                    statusBarKeyguardViewManager.hide(android.os.SystemClock.uptimeMillis() + this.mHideAnimation.getStartOffset(), this.mHideAnimation.getDuration());
                    onKeyguardExitFinished("Hiding keyguard while occluded. Just hide the keyguard view and exit.");
                } else {
                    if (this.mUnlockingAndWakingFromDream) {
                        Log.d("KeyguardViewMediator", "hiding keyguard before waking from dream");
                    }
                    this.mHiding = true;
                    this.mKeyguardGoingAwayRunnable.run();
                }
                if ((this.mDreamOverlayShowing || this.mUpdateMonitor.mIsDreaming) && !this.mOrderUnlockAndWake) {
                    PowerManager powerManager2 = this.mPM;
                    ((SystemClockImpl) this.mSystemClock).getClass();
                    powerManager2.wakeUp(android.os.SystemClock.uptimeMillis(), 4, "com.android.systemui:UNLOCK_DREAMING");
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        Trace.endSection();
    }

    public final void handleKeyguardDone() {
        Trace.beginSection("KeyguardViewMediator#handleKeyguardDone");
        int selectedUserId = this.mSelectedUserInteractor.getSelectedUserId();
        this.mUiBgExecutor.execute(new KeyguardViewMediator$$ExternalSyntheticLambda9(selectedUserId, 1, this));
        if (DEBUG) {
            Log.d("KeyguardViewMediator", "handleKeyguardDone");
        }
        synchronized (this) {
            resetKeyguardDonePendingLocked();
        }
        if (this.mGoingToSleep) {
            this.mUpdateMonitor.clearFingerprintRecognized(selectedUserId);
            Log.i("KeyguardViewMediator", "Device is going to sleep, aborting keyguardDone");
        } else {
            setPendingLock(false);
            handleHide();
            KeyguardRepositoryImpl keyguardRepositoryImpl = this.mKeyguardInteractor.repository;
            keyguardRepositoryImpl.keyguardDoneAnimationsFinished.tryEmit(Unit.INSTANCE);
            this.mUpdateMonitor.clearFingerprintRecognized(selectedUserId);
        }
        Trace.endSection();
    }

    /* JADX WARN: Removed duplicated region for block: B:29:0x006e A[Catch: all -> 0x0041, TryCatch #0 {all -> 0x0041, blocks: (B:11:0x0031, B:13:0x0035, B:15:0x0039, B:16:0x0044, B:19:0x0046, B:21:0x004a, B:22:0x0051, B:24:0x0060, B:29:0x006e, B:30:0x0094, B:31:0x00ce), top: B:10:0x0031 }] */
    /* JADX WARN: Removed duplicated region for block: B:34:0x00d5  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void handleShowInner(android.os.Bundle r6) {
        /*
            Method dump skipped, instructions count: 244
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.keyguard.KeyguardViewMediator.handleShowInner(android.os.Bundle):void");
    }

    public final void handleStartKeyguardExitAnimationInner(long j, long j2, final RemoteAnimationTarget[] remoteAnimationTargetArr, RemoteAnimationTarget[] remoteAnimationTargetArr2, RemoteAnimationTarget[] remoteAnimationTargetArr3, final IRemoteAnimationFinishedCallback iRemoteAnimationFinishedCallback) {
        Log.d("KeyguardViewMediator", "handleStartKeyguardExitAnimation startTime=" + j + " fadeoutDuration=" + j2);
        synchronized (this) {
            this.mIsKeyguardExitAnimationCanceled = false;
            if (!this.mHiding && !this.mSurfaceBehindRemoteAnimationRequested && !((KeyguardStateControllerImpl) this.mKeyguardStateController).mFlingingToDismissKeyguardDuringSwipeGesture) {
                if (iRemoteAnimationFinishedCallback != null) {
                    try {
                        iRemoteAnimationFinishedCallback.onAnimationFinished();
                    } catch (RemoteException e) {
                        Slog.w("KeyguardViewMediator", "Failed to call onAnimationFinished", e);
                    }
                }
                setShowingLocked("handleStartKeyguardExitAnimation - canceled", this.mShowing, true);
                return;
            }
            this.mHiding = false;
            ActivityTransitionAnimator.Runner runner = this.mKeyguardExitAnimationRunner;
            this.mKeyguardExitAnimationRunner = null;
            LatencyTracker.getInstance(this.mContext).onActionEnd(11);
            if (runner != null && iRemoteAnimationFinishedCallback != null) {
                IRemoteAnimationFinishedCallback iRemoteAnimationFinishedCallback2 = new IRemoteAnimationFinishedCallback() { // from class: com.android.systemui.keyguard.KeyguardViewMediator.15
                    public final IBinder asBinder() {
                        return iRemoteAnimationFinishedCallback.asBinder();
                    }

                    public final void onAnimationFinished() {
                        try {
                            iRemoteAnimationFinishedCallback.onAnimationFinished();
                        } catch (RemoteException e2) {
                            Slog.w("KeyguardViewMediator", "Failed to call onAnimationFinished", e2);
                        }
                        KeyguardViewMediator keyguardViewMediator = KeyguardViewMediator.this;
                        if (!keyguardViewMediator.mIsKeyguardExitAnimationCanceled) {
                            keyguardViewMediator.onKeyguardExitFinished("onRemoteAnimationFinished");
                            ((StatusBarKeyguardViewManager) KeyguardViewMediator.this.mKeyguardViewControllerLazy.get()).hide(0L, 0L);
                        }
                        KeyguardViewMediator.this.mInteractionJankMonitor.end(29);
                    }
                };
                try {
                    this.mInteractionJankMonitor.begin(createInteractionJankMonitorConf(29, "RunRemoteAnimation"));
                    runner.onAnimationStart(7, remoteAnimationTargetArr, remoteAnimationTargetArr2, remoteAnimationTargetArr3, iRemoteAnimationFinishedCallback2);
                } catch (RemoteException e2) {
                    Slog.w("KeyguardViewMediator", "Failed to call onAnimationStart", e2);
                }
            } else if (((StatusBarStateControllerImpl) this.mStatusBarStateController).mLeaveOpenOnKeyguardHide || remoteAnimationTargetArr == null || remoteAnimationTargetArr.length <= 0) {
                this.mInteractionJankMonitor.begin(createInteractionJankMonitorConf(29, "RemoteAnimationDisabled"));
                ((StatusBarKeyguardViewManager) this.mKeyguardViewControllerLazy.get()).hide(j, j2);
                this.mContext.getMainExecutor().execute(new Runnable() { // from class: com.android.systemui.keyguard.KeyguardViewMediator$$ExternalSyntheticLambda20
                    @Override // java.lang.Runnable
                    public final void run() {
                        int i = 0;
                        KeyguardViewMediator keyguardViewMediator = KeyguardViewMediator.this;
                        IRemoteAnimationFinishedCallback iRemoteAnimationFinishedCallback3 = iRemoteAnimationFinishedCallback;
                        RemoteAnimationTarget[] remoteAnimationTargetArr4 = remoteAnimationTargetArr;
                        if (iRemoteAnimationFinishedCallback3 == null) {
                            ((KeyguardUnlockAnimationController) keyguardViewMediator.mKeyguardUnlockAnimationControllerLazy.get()).notifyFinishedKeyguardExitAnimation(false);
                            return;
                        }
                        keyguardViewMediator.getClass();
                        if (remoteAnimationTargetArr4 == null || remoteAnimationTargetArr4.length == 0) {
                            Slog.e("KeyguardViewMediator", "Keyguard exit without a corresponding app to show.");
                            try {
                                try {
                                    iRemoteAnimationFinishedCallback3.onAnimationFinished();
                                } catch (RemoteException unused) {
                                    Slog.e("KeyguardViewMediator", "RemoteException");
                                }
                                return;
                            } finally {
                                keyguardViewMediator.mInteractionJankMonitor.end(29);
                            }
                        }
                        SyncRtSurfaceTransactionApplier syncRtSurfaceTransactionApplier = new SyncRtSurfaceTransactionApplier(((StatusBarKeyguardViewManager) keyguardViewMediator.mKeyguardViewControllerLazy.get()).getViewRootImpl().getView());
                        RemoteAnimationTarget remoteAnimationTarget = remoteAnimationTargetArr4[0];
                        ValueAnimator ofFloat = ValueAnimator.ofFloat(0.0f, 1.0f);
                        ofFloat.setDuration(400L);
                        ofFloat.setInterpolator(Interpolators.LINEAR);
                        ofFloat.addUpdateListener(new KeyguardViewMediator$$ExternalSyntheticLambda22(remoteAnimationTarget, syncRtSurfaceTransactionApplier, i));
                        ofFloat.addListener(new KeyguardViewMediator.AnonymousClass16(keyguardViewMediator, iRemoteAnimationFinishedCallback3, i));
                        ofFloat.start();
                    }
                });
                onKeyguardExitFinished("remote animation disabled");
            } else {
                this.mSurfaceBehindRemoteAnimationFinishedCallback = iRemoteAnimationFinishedCallback;
                this.mSurfaceBehindRemoteAnimationRunning = true;
                this.mInteractionJankMonitor.begin(createInteractionJankMonitorConf(29, "DismissPanel"));
                Stream stream = Arrays.stream(remoteAnimationTargetArr);
                final int i = 0;
                Stream filter = stream.filter(new Predicate() { // from class: com.android.systemui.keyguard.KeyguardViewMediator$$ExternalSyntheticLambda14
                    @Override // java.util.function.Predicate
                    public final boolean test(Object obj) {
                        RemoteAnimationTarget remoteAnimationTarget = (RemoteAnimationTarget) obj;
                        switch (i) {
                            case 0:
                                return remoteAnimationTarget.mode == 0;
                            case 1:
                                return remoteAnimationTarget.mode == 0;
                            default:
                                return remoteAnimationTarget.mode == 1;
                        }
                    }
                });
                final int i2 = 0;
                RemoteAnimationTarget[] remoteAnimationTargetArr4 = (RemoteAnimationTarget[]) filter.toArray(new IntFunction() { // from class: com.android.systemui.keyguard.KeyguardViewMediator$$ExternalSyntheticLambda15
                    @Override // java.util.function.IntFunction
                    public final Object apply(int i3) {
                        switch (i2) {
                            case 0:
                                return new RemoteAnimationTarget[i3];
                            case 1:
                                return new RemoteAnimationTarget[i3];
                            default:
                                return new RemoteAnimationTarget[i3];
                        }
                    }
                });
                final int i3 = 1;
                Stream filter2 = Arrays.stream(remoteAnimationTargetArr2).filter(new Predicate() { // from class: com.android.systemui.keyguard.KeyguardViewMediator$$ExternalSyntheticLambda14
                    @Override // java.util.function.Predicate
                    public final boolean test(Object obj) {
                        RemoteAnimationTarget remoteAnimationTarget = (RemoteAnimationTarget) obj;
                        switch (i3) {
                            case 0:
                                return remoteAnimationTarget.mode == 0;
                            case 1:
                                return remoteAnimationTarget.mode == 0;
                            default:
                                return remoteAnimationTarget.mode == 1;
                        }
                    }
                });
                final int i4 = 1;
                RemoteAnimationTarget[] remoteAnimationTargetArr5 = (RemoteAnimationTarget[]) filter2.toArray(new IntFunction() { // from class: com.android.systemui.keyguard.KeyguardViewMediator$$ExternalSyntheticLambda15
                    @Override // java.util.function.IntFunction
                    public final Object apply(int i32) {
                        switch (i4) {
                            case 0:
                                return new RemoteAnimationTarget[i32];
                            case 1:
                                return new RemoteAnimationTarget[i32];
                            default:
                                return new RemoteAnimationTarget[i32];
                        }
                    }
                });
                final int i5 = 2;
                Stream filter3 = Arrays.stream(remoteAnimationTargetArr2).filter(new Predicate() { // from class: com.android.systemui.keyguard.KeyguardViewMediator$$ExternalSyntheticLambda14
                    @Override // java.util.function.Predicate
                    public final boolean test(Object obj) {
                        RemoteAnimationTarget remoteAnimationTarget = (RemoteAnimationTarget) obj;
                        switch (i5) {
                            case 0:
                                return remoteAnimationTarget.mode == 0;
                            case 1:
                                return remoteAnimationTarget.mode == 0;
                            default:
                                return remoteAnimationTarget.mode == 1;
                        }
                    }
                });
                final int i6 = 2;
                ((KeyguardUnlockAnimationController) this.mKeyguardUnlockAnimationControllerLazy.get()).notifyStartSurfaceBehindRemoteAnimation(remoteAnimationTargetArr4, remoteAnimationTargetArr5, (RemoteAnimationTarget[]) filter3.toArray(new IntFunction() { // from class: com.android.systemui.keyguard.KeyguardViewMediator$$ExternalSyntheticLambda15
                    @Override // java.util.function.IntFunction
                    public final Object apply(int i32) {
                        switch (i6) {
                            case 0:
                                return new RemoteAnimationTarget[i32];
                            case 1:
                                return new RemoteAnimationTarget[i32];
                            default:
                                return new RemoteAnimationTarget[i32];
                        }
                    }
                }), j, this.mSurfaceBehindRemoteAnimationRequested);
            }
            return;
        }
    }

    public final boolean isAnimatingBetweenKeyguardAndSurfaceBehindOrWillBe() {
        return this.mSurfaceBehindRemoteAnimationRunning || ((KeyguardStateControllerImpl) this.mKeyguardStateController).mFlingingToDismissKeyguard;
    }

    public final boolean isSecure() {
        return this.mLockPatternUtils.isSecure(this.mSelectedUserInteractor.getSelectedUserId()) || this.mUpdateMonitor.isSimPinSecure();
    }

    public final boolean isShowingAndNotOccluded() {
        return this.mShowing && !this.mOccluded;
    }

    public final void maybeHandlePendingLock() {
        if (this.mPendingLock) {
            boolean shouldDelayKeyguardShow = this.mScreenOffAnimationController.shouldDelayKeyguardShow();
            boolean z = DEBUG;
            if (shouldDelayKeyguardShow) {
                if (z) {
                    Log.d("KeyguardViewMediator", "#maybeHandlePendingLock: not handling because the screen off animation's shouldDelayKeyguardShow() returned true. This should be handled soon by #onStartedWakingUp, or by the end actions of the screen off animation.");
                }
            } else if (((KeyguardStateControllerImpl) this.mKeyguardStateController).mKeyguardGoingAway) {
                if (z) {
                    Log.d("KeyguardViewMediator", "#maybeHandlePendingLock: not handling because the keyguard is going away. This should be handled shortly by StatusBar#finishKeyguardFadingAway.");
                }
            } else {
                if (z) {
                    Log.d("KeyguardViewMediator", "#maybeHandlePendingLock: handling pending lock; locking keyguard.");
                }
                doKeyguardLocked(null);
                setPendingLock(false);
            }
        }
    }

    public final void maybeSendUserPresentBroadcast() {
        boolean z = this.mSystemReady;
        SelectedUserInteractor selectedUserInteractor = this.mSelectedUserInteractor;
        if (z && this.mLockPatternUtils.isLockScreenDisabled(selectedUserInteractor.getSelectedUserId())) {
            sendUserPresentBroadcast();
        } else if (this.mSystemReady && shouldWaitForProvisioning()) {
            this.mLockPatternUtils.userPresent(selectedUserInteractor.getSelectedUserId());
        }
    }

    @Override // com.android.systemui.CoreStartable
    public final void onBootCompleted() {
        synchronized (this) {
            try {
                if (this.mContext.getResources().getBoolean(android.R.bool.config_honor_data_retry_timer_for_emergency_network)) {
                    ((GuestUserInteractor) this.mUserSwitcherController.guestUserInteractor$delegate.getValue()).onDeviceBootCompleted();
                }
                this.mBootCompleted = true;
                adjustStatusBarLocked(false, true);
                if (this.mBootSendUserPresent) {
                    sendUserPresentBroadcast();
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    @Override // com.android.systemui.plugins.statusbar.StatusBarStateController.StateListener
    public final void onDozeAmountChanged(float f, float f2) {
        if (this.mAnimatingScreenOff && this.mDozing && f == 1.0f) {
            this.mAnimatingScreenOff = false;
            setShowingLocked("onDozeAmountChanged", this.mShowing, true);
        }
    }

    public final void onKeyguardExitFinished(String str) {
        if (DEBUG) {
            Log.d("KeyguardViewMediator", "onKeyguardExitFinished()");
        }
        if (TelephonyManager.EXTRA_STATE_IDLE.equals(this.mPhoneState)) {
            playSound(this.mUnlockSoundId);
        }
        setShowingLocked("onKeyguardExitFinished: ".concat(str), false, false);
        this.mWakeAndUnlocking = false;
        DismissCallbackRegistry dismissCallbackRegistry = this.mDismissCallbackRegistry;
        Log.d("DismissCallbackRegistry", "notifyDismissSucceeded(" + dismissCallbackRegistry.mDismissCallbacks.size() + ")");
        for (int size = dismissCallbackRegistry.mDismissCallbacks.size() + (-1); size >= 0; size--) {
            DismissCallbackWrapper dismissCallbackWrapper = (DismissCallbackWrapper) dismissCallbackRegistry.mDismissCallbacks.get(size);
            Executor executor = dismissCallbackRegistry.mUiBgExecutor;
            Objects.requireNonNull(dismissCallbackWrapper);
            executor.execute(new DismissCallbackRegistry$$ExternalSyntheticLambda0(dismissCallbackWrapper, 0));
        }
        dismissCallbackRegistry.mDismissCallbacks.clear();
        resetKeyguardDonePendingLocked();
        this.mHideAnimationRun = false;
        adjustStatusBarLocked(false, false);
        sendUserPresentBroadcast();
        KeyguardInteractor keyguardInteractor = this.mKeyguardInteractor;
        int ordinal = ((TransitionStep) ((StateFlowImpl) keyguardInteractor.keyguardTransitionInteractor.transitionState.$$delegate_0).getValue()).to.ordinal();
        if (ordinal == 7) {
            ((FromLockscreenTransitionInteractor) keyguardInteractor.fromLockscreenTransitionInteractor.get()).dismissKeyguard();
        } else {
            if (ordinal != 11) {
                return;
            }
            ((FromOccludedTransitionInteractor) keyguardInteractor.fromOccludedTransitionInteractor.get()).dismissFromOccluded();
        }
    }

    public final void playSound(int i) {
        if (i == 0) {
            return;
        }
        if (this.mSystemSettings.getIntForUser("lockscreen_sounds_enabled", 1, this.mSelectedUserInteractor.getSelectedUserId()) == 1) {
            this.mLockSounds.stop(this.mLockSoundStreamId);
            if (this.mAudioManager == null) {
                AudioManager audioManager = (AudioManager) this.mContext.getSystemService("audio");
                this.mAudioManager = audioManager;
                if (audioManager == null) {
                    return;
                } else {
                    this.mUiSoundsStreamType = audioManager.getUiSoundsStreamType();
                }
            }
            this.mUiBgExecutor.execute(new KeyguardViewMediator$$ExternalSyntheticLambda9(i, 0, this));
        }
    }

    public final void resetKeyguardDonePendingLocked() {
        this.mKeyguardDonePending = false;
        this.mHandler.removeMessages(13);
    }

    public final void resetStateLocked(boolean z) {
        if (DEBUG) {
            Log.e("KeyguardViewMediator", "resetStateLocked");
        }
        AnonymousClass13 anonymousClass13 = this.mHandler;
        anonymousClass13.sendMessage(anonymousClass13.obtainMessage(3, z ? 1 : 0, 0));
    }

    public final void scheduleNonStrongBiometricIdleTimeout() {
        int selectedUserId = this.mSelectedUserInteractor.getSelectedUserId();
        KeyguardUpdateMonitor keyguardUpdateMonitor = this.mUpdateMonitor;
        SystemUIDeviceEntryFaceAuthInteractor systemUIDeviceEntryFaceAuthInteractor = keyguardUpdateMonitor.mFaceAuthInteractor;
        if ((systemUIDeviceEntryFaceAuthInteractor == null || systemUIDeviceEntryFaceAuthInteractor.isFaceAuthStrong() || !keyguardUpdateMonitor.isUnlockWithFacePossible()) && !(keyguardUpdateMonitor.isFingerprintClass3() && keyguardUpdateMonitor.isUnlockWithFingerprintPossible(selectedUserId))) {
            return;
        }
        if (DEBUG) {
            ExifInterface$$ExternalSyntheticOutline0.m("scheduleNonStrongBiometricIdleTimeout: schedule an alarm for currentUser=", "KeyguardViewMediator", selectedUserId);
        }
        this.mLockPatternUtils.scheduleNonStrongBiometricIdleTimeout(selectedUserId);
    }

    public final void sendUserPresentBroadcast() {
        synchronized (this) {
            try {
                if (this.mBootCompleted) {
                    final int selectedUserId = this.mSelectedUserInteractor.getSelectedUserId();
                    final UserHandle userHandle = new UserHandle(selectedUserId);
                    final UserManager userManager = (UserManager) this.mContext.getSystemService("user");
                    this.mUiBgExecutor.execute(new Runnable() { // from class: com.android.systemui.keyguard.KeyguardViewMediator$$ExternalSyntheticLambda4
                        @Override // java.lang.Runnable
                        public final void run() {
                            KeyguardViewMediator keyguardViewMediator = KeyguardViewMediator.this;
                            UserManager userManager2 = userManager;
                            UserHandle userHandle2 = userHandle;
                            int i = selectedUserId;
                            keyguardViewMediator.getClass();
                            for (int i2 : userManager2.getProfileIdsWithDisabled(userHandle2.getIdentifier())) {
                                keyguardViewMediator.mContext.sendBroadcastAsUser(KeyguardViewMediator.USER_PRESENT_INTENT, UserHandle.of(i2), null, KeyguardViewMediator.USER_PRESENT_INTENT_OPTIONS);
                            }
                            keyguardViewMediator.mLockPatternUtils.userPresent(i);
                        }
                    });
                } else {
                    this.mBootSendUserPresent = true;
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final void setBlursDisabledForAppLaunch(boolean z) {
        NotificationShadeDepthController notificationShadeDepthController = (NotificationShadeDepthController) this.mNotificationShadeDepthController.get();
        if (notificationShadeDepthController.blursDisabledForAppLaunch == z) {
            return;
        }
        notificationShadeDepthController.blursDisabledForAppLaunch = z;
        notificationShadeDepthController.scheduleUpdate();
        float f = notificationShadeDepthController.shadeExpansion;
        NotificationShadeDepthController.DepthAnimation depthAnimation = notificationShadeDepthController.shadeAnimation;
        if (!(f == 0.0f && depthAnimation.radius == 0.0f) && z) {
            if (depthAnimation.pendingRadius != 0) {
                depthAnimation.pendingRadius = 0;
                depthAnimation.springAnimation.animateToFinalPosition(0);
            }
            depthAnimation.finishIfRunning();
        }
    }

    public final void setOccluded(boolean z, boolean z2) {
        Log.d("KeyguardViewMediator", "setOccluded(" + z + ")");
        Trace.beginSection("KeyguardViewMediator#setOccluded");
        if (DEBUG) {
            MobileStatusTracker$MobileTelephonyCallback$$ExternalSyntheticOutline0.m("setOccluded ", "KeyguardViewMediator", z);
        }
        AnonymousClass13 anonymousClass13 = this.mHandler;
        anonymousClass13.removeMessages(9);
        anonymousClass13.sendMessage(anonymousClass13.obtainMessage(9, z ? 1 : 0, z2 ? 1 : 0));
        Trace.endSection();
    }

    public final void setPendingLock(boolean z) {
        this.mPendingLock = z;
        Trace.traceCounter(4096L, "pendingLock", z ? 1 : 0);
    }

    public final void setShowingLocked(final String str, final boolean z, boolean z2) {
        final boolean z3 = this.mDozing && !this.mWakeAndUnlocking;
        boolean z4 = this.mShowing;
        boolean z5 = z != z4 || z2;
        boolean z6 = (z == z4 && z3 == this.mAodShowing && !z2) ? false : true;
        this.mShowing = z;
        this.mAodShowing = z3;
        if (z5) {
            DejankUtils.whitelistIpcs(new KeyguardViewMediator$$ExternalSyntheticLambda0(this, z, 1));
            updateInputRestrictedLocked();
            Executor executor = this.mUiBgExecutor;
            TrustManager trustManager = this.mTrustManager;
            Objects.requireNonNull(trustManager);
            executor.execute(new KeyguardViewMediator$$ExternalSyntheticLambda7(0, trustManager));
        }
        if (z6) {
            this.mUiBgExecutor.execute(new Runnable() { // from class: com.android.systemui.keyguard.KeyguardViewMediator$$ExternalSyntheticLambda8
                @Override // java.lang.Runnable
                public final void run() {
                    KeyguardViewMediator keyguardViewMediator = KeyguardViewMediator.this;
                    boolean z7 = z;
                    boolean z8 = z3;
                    String str2 = str;
                    keyguardViewMediator.getClass();
                    Log.d("KeyguardViewMediator", "updateActivityLockScreenState(" + z7 + ", " + z8 + ", " + str2 + ")");
                    try {
                        keyguardViewMediator.mActivityTaskManagerService.setLockScreenShown(z7, z8);
                    } catch (RemoteException unused) {
                    }
                }
            });
        }
    }

    public final void setUnlockAndWakeFromDream(int i, boolean z) {
        String str;
        if (this.mOrderUnlockAndWake && z != this.mUnlockingAndWakingFromDream) {
            if (i == 0) {
                str = "hiding keyguard";
            } else if (i == 1) {
                str = "showing keyguard";
            } else if (i == 2) {
                str = "fulfilling existing request";
            } else {
                if (i != 3) {
                    throw new IllegalStateException(AnnotationValue$1$$ExternalSyntheticOutline0.m(i, "Unexpected value: "));
                }
                str = "waking to unlock";
            }
            boolean z2 = (z || i == 2) ? false : true;
            this.mUnlockingAndWakingFromDream = z;
            Log.d("KeyguardViewMediator", "Updating waking and unlocking request to " + z + ". description:[" + (z2 ? "Interrupting request to wake and unlock" : z ? "Initiating request to wake and unlock" : "Fulfilling request to wake and unlock") + "]. reason:[" + str + "]");
        }
    }

    public final void setupLocked() {
        boolean z;
        int i = 0;
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.ACTION_SHUTDOWN");
        this.mBroadcastDispatcher.registerReceiver(this.mBroadcastReceiver, intentFilter);
        IntentFilter intentFilter2 = new IntentFilter();
        intentFilter2.addAction("com.android.internal.policy.impl.PhoneWindowManager.DELAYED_KEYGUARD");
        intentFilter2.addAction("com.android.internal.policy.impl.PhoneWindowManager.DELAYED_LOCK");
        intentFilter2.setPriority(1000);
        this.mContext.registerReceiver(this.mDelayedLockBroadcastReceiver, intentFilter2, "com.android.systemui.permission.SELF", null, 2);
        this.mAlarmManager = (AlarmManager) this.mContext.getSystemService("alarm");
        try {
            z = this.mContext.getPackageManager().getServiceInfo(new ComponentName(this.mContext, (Class<?>) KeyguardService.class), 0).isEnabled();
        } catch (PackageManager.NameNotFoundException unused) {
            z = true;
        }
        if (z) {
            setShowingLocked("setupLocked - keyguard service enabled", (shouldWaitForProvisioning() || this.mLockPatternUtils.isLockScreenDisabled(this.mSelectedUserInteractor.getSelectedUserId())) ? false : true, true);
        } else {
            setShowingLocked("setupLocked - keyguard service disabled", false, true);
        }
        AnonymousClass8 anonymousClass8 = new AnonymousClass8(this.mExitAnimationRunner);
        int i2 = KeyguardService.$r8$clinit;
        this.mKeyguardTransitions.register(new KeyguardService.AnonymousClass1(anonymousClass8, this), new KeyguardService.AnonymousClass1(new AnonymousClass8(this.mAppearAnimationRunner), this), new KeyguardService.AnonymousClass1(new AnonymousClass8(this.mOccludeAnimationRunner), this), new KeyguardService.AnonymousClass1(new AnonymousClass8(this.mOccludeByDreamAnimationRunner), this), new KeyguardService.AnonymousClass1(new AnonymousClass8(this.mUnoccludeAnimationRunner), this));
        ContentResolver contentResolver = this.mContext.getContentResolver();
        this.mDeviceInteractive = this.mPM.isInteractive();
        this.mLockSounds = new SoundPool.Builder().setMaxStreams(1).setAudioAttributes(new AudioAttributes.Builder().setUsage(13).setContentType(4).build()).build();
        String string = Settings.Global.getString(contentResolver, "lock_sound");
        if (string != null) {
            this.mLockSoundId = this.mLockSounds.load(string, 1);
        }
        if (string == null || this.mLockSoundId == 0) {
            Log.w("KeyguardViewMediator", "failed to load lock sound from " + string);
        }
        String string2 = Settings.Global.getString(contentResolver, "unlock_sound");
        if (string2 != null) {
            this.mUnlockSoundId = this.mLockSounds.load(string2, 1);
        }
        if (string2 == null || this.mUnlockSoundId == 0) {
            Log.w("KeyguardViewMediator", "failed to load unlock sound from " + string2);
        }
        String string3 = Settings.Global.getString(contentResolver, "trusted_sound");
        if (string3 != null) {
            this.mTrustedSoundId = this.mLockSounds.load(string3, 1);
        }
        if (string3 == null || this.mTrustedSoundId == 0) {
            Log.w("KeyguardViewMediator", "failed to load trusted sound from " + string3);
        }
        this.mLockSoundVolume = (float) Math.pow(10.0d, this.mContext.getResources().getInteger(android.R.integer.config_lightSensorWarmupTime) / 20.0f);
        this.mHideAnimation = AnimationUtils.loadAnimation(this.mContext, android.R.anim.lock_screen_behind_enter);
        new WorkLockActivityController(this.mContext, this.mUserTracker, TaskStackChangeListeners.INSTANCE, ActivityTaskManager.getService());
        this.mJavaAdapter.alwaysCollectFlow(this.mWallpaperRepository.wallpaperSupportsAmbientMode, new KeyguardViewMediator$$ExternalSyntheticLambda5(this, i));
    }

    public final boolean shouldWaitForProvisioning() {
        return (this.mUpdateMonitor.mDeviceProvisioned || isSecure()) ? false : true;
    }

    public final void showKeyguard(Bundle bundle) {
        Trace.beginSection("KeyguardViewMediator#showKeyguard acquiring mShowKeyguardWakeLock");
        if (DEBUG) {
            Log.d("KeyguardViewMediator", "showKeyguard");
        }
        this.mShowKeyguardWakeLock.acquire();
        AnonymousClass13 anonymousClass13 = this.mHandler;
        anonymousClass13.sendMessageAtFrontOfQueue(anonymousClass13.obtainMessage(1, bundle));
        Trace.endSection();
    }

    public final void showSurfaceBehindKeyguard() {
        this.mSurfaceBehindRemoteAnimationRequested = true;
        try {
            int i = ((KeyguardUnlockAnimationController) this.mKeyguardUnlockAnimationControllerLazy.get()).isSupportedLauncherUnderneath() ? 22 : 6;
            ((KeyguardStateControllerImpl) this.mKeyguardStateController).notifyKeyguardGoingAway(true);
            this.mActivityTaskManagerService.keyguardGoingAway(i);
        } catch (RemoteException e) {
            this.mSurfaceBehindRemoteAnimationRequested = false;
            Log.e("KeyguardViewMediator", "Failed to report keyguardGoingAway", e);
        }
    }

    @Override // com.android.systemui.CoreStartable
    public final void start() {
        synchronized (this) {
            setupLocked();
        }
    }

    public final void startKeyguardExitAnimation(long j, long j2, RemoteAnimationTarget[] remoteAnimationTargetArr, RemoteAnimationTarget[] remoteAnimationTargetArr2, RemoteAnimationTarget[] remoteAnimationTargetArr3, IRemoteAnimationFinishedCallback iRemoteAnimationFinishedCallback) {
        Trace.beginSection("KeyguardViewMediator#startKeyguardExitAnimation");
        this.mInteractionJankMonitor.cancel(23);
        StartKeyguardExitAnimParams startKeyguardExitAnimParams = new StartKeyguardExitAnimParams();
        startKeyguardExitAnimParams.startTime = j;
        startKeyguardExitAnimParams.fadeoutDuration = j2;
        startKeyguardExitAnimParams.mApps = remoteAnimationTargetArr;
        startKeyguardExitAnimParams.mWallpapers = remoteAnimationTargetArr2;
        startKeyguardExitAnimParams.mNonApps = remoteAnimationTargetArr3;
        startKeyguardExitAnimParams.mFinishedCallback = iRemoteAnimationFinishedCallback;
        AnonymousClass13 anonymousClass13 = this.mHandler;
        anonymousClass13.sendMessage(anonymousClass13.obtainMessage(12, startKeyguardExitAnimParams));
        Trace.endSection();
    }

    public final void tryKeyguardDone() {
        boolean z = DEBUG;
        if (z) {
            StringBuilder sb = new StringBuilder("tryKeyguardDone: pending - ");
            sb.append(this.mKeyguardDonePending);
            sb.append(", animRan - ");
            sb.append(this.mHideAnimationRun);
            sb.append(" animRunning - ");
            CachedBluetoothDevice$$ExternalSyntheticOutline0.m(sb, this.mHideAnimationRunning, "KeyguardViewMediator");
        }
        if (!this.mKeyguardDonePending && this.mHideAnimationRun && !this.mHideAnimationRunning) {
            handleKeyguardDone();
            return;
        }
        if (this.mSurfaceBehindRemoteAnimationRunning) {
            exitKeyguardAndFinishSurfaceBehindRemoteAnimation();
            return;
        }
        if (this.mHideAnimationRun) {
            return;
        }
        if (z) {
            Log.d("KeyguardViewMediator", "tryKeyguardDone: starting pre-hide animation");
        }
        this.mHideAnimationRun = true;
        this.mHideAnimationRunning = true;
        ((StatusBarKeyguardViewManager) this.mKeyguardViewControllerLazy.get()).startPreHideAnimation(this.mHideAnimationFinishedRunnable);
    }

    public final void updateInputRestrictedLocked() {
        boolean z = this.mShowing || this.mNeedToReshowWhenReenabled;
        if (this.mInputRestricted != z) {
            this.mInputRestricted = z;
            for (int size = this.mKeyguardStateCallbacks.size() - 1; size >= 0; size--) {
                IKeyguardStateCallback iKeyguardStateCallback = (IKeyguardStateCallback) this.mKeyguardStateCallbacks.get(size);
                try {
                    iKeyguardStateCallback.onInputRestrictedStateChanged(z);
                } catch (RemoteException e) {
                    Slog.w("KeyguardViewMediator", "Failed to call onDeviceProvisioned", e);
                    if (e instanceof DeadObjectException) {
                        this.mKeyguardStateCallbacks.remove(iKeyguardStateCallback);
                    }
                }
            }
        }
    }

    public final void userActivity() {
        this.mUiBgExecutor.execute(new KeyguardViewMediator$$ExternalSyntheticLambda1(this, 1));
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.keyguard.KeyguardViewMediator$7, reason: invalid class name */
    public final class AnonymousClass7 extends IRemoteAnimationRunner.Stub {
        public final void onAnimationStart(int i, RemoteAnimationTarget[] remoteAnimationTargetArr, RemoteAnimationTarget[] remoteAnimationTargetArr2, RemoteAnimationTarget[] remoteAnimationTargetArr3, IRemoteAnimationFinishedCallback iRemoteAnimationFinishedCallback) {
            try {
                iRemoteAnimationFinishedCallback.onAnimationFinished();
            } catch (RemoteException e) {
                Log.e("KeyguardViewMediator", "Failed to finish transition", e);
            }
        }

        public final void onAnimationCancelled() {
        }
    }
}
