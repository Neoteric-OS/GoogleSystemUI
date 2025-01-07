package com.android.systemui.wmshell;

import android.app.ActivityManager;
import android.app.IActivityTaskManager;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Rect;
import android.os.Build;
import android.os.Parcelable;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.os.UserHandle;
import android.util.Log;
import com.android.internal.protolog.ProtoLogImpl_411527699;
import com.android.keyguard.KeyguardUpdateMonitor;
import com.android.keyguard.KeyguardUpdateMonitorCallback;
import com.android.systemui.CoreStartable;
import com.android.systemui.communal.ui.viewmodel.CommunalTransitionViewModel;
import com.android.systemui.keyguard.KeyguardUnlockAnimationController;
import com.android.systemui.keyguard.KeyguardViewMediator;
import com.android.systemui.keyguard.ScreenLifecycle;
import com.android.systemui.keyguard.WakefulnessLifecycle;
import com.android.systemui.model.SysUiState;
import com.android.systemui.notetask.NoteTaskInitializer;
import com.android.systemui.notetask.NoteTaskInitializer$callbacks$1;
import com.android.systemui.settings.DisplayTracker;
import com.android.systemui.settings.UserTracker;
import com.android.systemui.settings.UserTrackerImpl;
import com.android.systemui.statusbar.CommandQueue;
import com.android.systemui.statusbar.phone.ConfigurationControllerImpl;
import com.android.systemui.statusbar.policy.ConfigurationController;
import com.android.systemui.statusbar.policy.KeyguardStateController;
import com.android.systemui.statusbar.policy.KeyguardStateControllerImpl;
import com.android.systemui.util.kotlin.JavaAdapter;
import com.android.systemui.wmshell.WMShell;
import com.android.systemui.wmshell.WMShell$$ExternalSyntheticLambda1;
import com.android.wm.shell.common.HandlerExecutor;
import com.android.wm.shell.common.split.SplitScreenUtils;
import com.android.wm.shell.desktopmode.DesktopModeTaskRepository;
import com.android.wm.shell.desktopmode.DesktopTasksController;
import com.android.wm.shell.desktopmode.DesktopTasksController$DesktopModeImpl$addVisibleTasksListener$1;
import com.android.wm.shell.desktopmode.DesktopTasksController$DesktopModeImpl$moveFocusedTaskToDesktop$1;
import com.android.wm.shell.onehanded.OneHanded;
import com.android.wm.shell.onehanded.OneHandedController;
import com.android.wm.shell.onehanded.OneHandedController$$ExternalSyntheticLambda7;
import com.android.wm.shell.onehanded.OneHandedController$OneHandedImpl$$ExternalSyntheticLambda0;
import com.android.wm.shell.onehanded.OneHandedController$OneHandedImpl$$ExternalSyntheticLambda3;
import com.android.wm.shell.onehanded.OneHandedTransitionCallback;
import com.android.wm.shell.pip.Pip;
import com.android.wm.shell.pip.PipTransitionController;
import com.android.wm.shell.protolog.ShellProtoLogGroup;
import com.android.wm.shell.recents.RecentTasks;
import com.android.wm.shell.recents.RecentTasksController;
import com.android.wm.shell.shared.desktopmode.DesktopModeTransitionSource;
import com.android.wm.shell.splitscreen.SplitScreen;
import com.android.wm.shell.splitscreen.SplitScreenController;
import com.android.wm.shell.splitscreen.SplitScreenController$$ExternalSyntheticLambda0;
import com.android.wm.shell.sysui.ShellInterface;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.Executor;
import java.util.function.Consumer;
import kotlin.jvm.internal.Reflection;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class WMShell implements CoreStartable, CommandQueue.Callbacks {
    public final CommandQueue mCommandQueue;
    public final CommunalTransitionViewModel mCommunalTransitionViewModel;
    public final ConfigurationController mConfigurationController;
    public final Context mContext;
    public final Optional mDesktopModeOptional;
    public final DisplayTracker mDisplayTracker;
    public final JavaAdapter mJavaAdapter;
    public final KeyguardStateController mKeyguardStateController;
    public final KeyguardUpdateMonitor mKeyguardUpdateMonitor;
    public final NoteTaskInitializer mNoteTaskInitializer;
    public final Optional mOneHandedOptional;
    public final Optional mPipOptional;
    public final Optional mRecentTasksOptional;
    public final ScreenLifecycle mScreenLifecycle;
    public final ShellInterface mShell;
    public final Optional mSplitScreenOptional;
    public final Executor mSysUiMainExecutor;
    public final SysUiState mSysUiState;
    public final UserTracker mUserTracker;
    public final WakefulnessLifecycle mWakefulnessLifecycle;
    public AnonymousClass7 mWakefulnessObserver;
    public final AnonymousClass1 mConfigurationListener = new ConfigurationController.ConfigurationListener() { // from class: com.android.systemui.wmshell.WMShell.1
        @Override // com.android.systemui.statusbar.policy.ConfigurationController.ConfigurationListener
        public final void onConfigChanged(Configuration configuration) {
            WMShell.this.mShell.onConfigurationChanged(configuration);
        }
    };
    public final AnonymousClass2 mKeyguardStateCallback = new KeyguardStateController.Callback() { // from class: com.android.systemui.wmshell.WMShell.2
        @Override // com.android.systemui.statusbar.policy.KeyguardStateController.Callback
        public final void onKeyguardShowingChanged() {
            WMShell wMShell = WMShell.this;
            ShellInterface shellInterface = wMShell.mShell;
            KeyguardStateControllerImpl keyguardStateControllerImpl = (KeyguardStateControllerImpl) wMShell.mKeyguardStateController;
            shellInterface.onKeyguardVisibilityChanged(keyguardStateControllerImpl.mShowing, keyguardStateControllerImpl.mOccluded, ((KeyguardViewMediator) ((KeyguardUnlockAnimationController) keyguardStateControllerImpl.mUnlockAnimationControllerLazy.get()).keyguardViewMediator.get()).mSurfaceBehindRemoteAnimationRunning);
        }
    };
    public final KeyguardUpdateMonitorCallback mKeyguardUpdateMonitorCallback = new KeyguardUpdateMonitorCallback() { // from class: com.android.systemui.wmshell.WMShell.3
        @Override // com.android.keyguard.KeyguardUpdateMonitorCallback
        public final void onKeyguardDismissAnimationFinished() {
            WMShell.this.mShell.onKeyguardDismissAnimationFinished();
        }
    };
    public final UserTracker.Callback mUserChangedCallback = new UserTracker.Callback() { // from class: com.android.systemui.wmshell.WMShell.4
        @Override // com.android.systemui.settings.UserTracker.Callback
        public final void onProfilesChanged(List list) {
            WMShell.this.mShell.onUserProfilesChanged(list);
        }

        @Override // com.android.systemui.settings.UserTracker.Callback
        public final void onUserChanged(int i, Context context) {
            WMShell.this.mShell.onUserChanged(i, context);
        }
    };

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.wmshell.WMShell$10, reason: invalid class name */
    public final class AnonymousClass10 implements OneHandedTransitionCallback {
        public AnonymousClass10() {
        }

        @Override // com.android.wm.shell.onehanded.OneHandedTransitionCallback
        public final void onStartFinished(Rect rect) {
            WMShell.this.mSysUiMainExecutor.execute(new WMShell$10$$ExternalSyntheticLambda0(0, this));
        }

        @Override // com.android.wm.shell.onehanded.OneHandedTransitionCallback
        public final void onStartTransition() {
            WMShell.this.mSysUiMainExecutor.execute(new WMShell$10$$ExternalSyntheticLambda0(2, this));
        }

        @Override // com.android.wm.shell.onehanded.OneHandedTransitionCallback
        public final void onStopFinished(Rect rect) {
            WMShell.this.mSysUiMainExecutor.execute(new WMShell$10$$ExternalSyntheticLambda0(1, this));
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.wmshell.WMShell$7, reason: invalid class name */
    public final class AnonymousClass7 implements WakefulnessLifecycle.Observer {
        public final /* synthetic */ int $r8$classId;
        public final /* synthetic */ Object val$splitScreen;

        public /* synthetic */ AnonymousClass7(int i, Object obj) {
            this.$r8$classId = i;
            this.val$splitScreen = obj;
        }

        @Override // com.android.systemui.keyguard.WakefulnessLifecycle.Observer
        public final void onFinishedWakingUp() {
            switch (this.$r8$classId) {
                case 0:
                    SplitScreenController splitScreenController = SplitScreenController.this;
                    ((HandlerExecutor) splitScreenController.mMainExecutor).execute(new SplitScreenController$$ExternalSyntheticLambda0(splitScreenController, 3));
                    break;
                default:
                    OneHandedController.OneHandedImpl oneHandedImpl = (OneHandedController.OneHandedImpl) ((OneHanded) this.val$splitScreen);
                    OneHandedController oneHandedController = OneHandedController.this;
                    ((HandlerExecutor) oneHandedController.mMainExecutor).execute(new OneHandedController$OneHandedImpl$$ExternalSyntheticLambda3(oneHandedImpl, false));
                    break;
            }
        }

        @Override // com.android.systemui.keyguard.WakefulnessLifecycle.Observer
        public final void onStartedGoingToSleep() {
            switch (this.$r8$classId) {
                case 0:
                    SplitScreenController splitScreenController = SplitScreenController.this;
                    ((HandlerExecutor) splitScreenController.mMainExecutor).execute(new SplitScreenController$$ExternalSyntheticLambda0(splitScreenController, 1));
                    break;
                default:
                    OneHandedController.OneHandedImpl oneHandedImpl = (OneHandedController.OneHandedImpl) ((OneHanded) this.val$splitScreen);
                    OneHandedController oneHandedController = OneHandedController.this;
                    ((HandlerExecutor) oneHandedController.mMainExecutor).execute(new OneHandedController$$ExternalSyntheticLambda7(8, oneHandedImpl));
                    ((HandlerExecutor) oneHandedController.mMainExecutor).execute(new OneHandedController$OneHandedImpl$$ExternalSyntheticLambda3(oneHandedImpl, true));
                    break;
            }
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.wmshell.WMShell$9, reason: invalid class name */
    public final class AnonymousClass9 {
        public /* synthetic */ AnonymousClass9() {
        }
    }

    /* JADX WARN: Type inference failed for: r1v0, types: [com.android.systemui.wmshell.WMShell$1] */
    /* JADX WARN: Type inference failed for: r1v1, types: [com.android.systemui.wmshell.WMShell$2] */
    public WMShell(Context context, ShellInterface shellInterface, Optional optional, Optional optional2, Optional optional3, Optional optional4, Optional optional5, CommandQueue commandQueue, ConfigurationController configurationController, KeyguardStateController keyguardStateController, KeyguardUpdateMonitor keyguardUpdateMonitor, ScreenLifecycle screenLifecycle, SysUiState sysUiState, WakefulnessLifecycle wakefulnessLifecycle, UserTracker userTracker, DisplayTracker displayTracker, NoteTaskInitializer noteTaskInitializer, CommunalTransitionViewModel communalTransitionViewModel, JavaAdapter javaAdapter, Executor executor) {
        this.mContext = context;
        this.mShell = shellInterface;
        this.mCommandQueue = commandQueue;
        this.mConfigurationController = configurationController;
        this.mKeyguardStateController = keyguardStateController;
        this.mKeyguardUpdateMonitor = keyguardUpdateMonitor;
        this.mScreenLifecycle = screenLifecycle;
        this.mSysUiState = sysUiState;
        this.mPipOptional = optional;
        this.mSplitScreenOptional = optional2;
        this.mOneHandedOptional = optional3;
        this.mDesktopModeOptional = optional4;
        this.mRecentTasksOptional = optional5;
        this.mWakefulnessLifecycle = wakefulnessLifecycle;
        this.mUserTracker = userTracker;
        this.mDisplayTracker = displayTracker;
        this.mNoteTaskInitializer = noteTaskInitializer;
        this.mCommunalTransitionViewModel = communalTransitionViewModel;
        this.mJavaAdapter = javaAdapter;
        this.mSysUiMainExecutor = executor;
    }

    @Override // com.android.systemui.CoreStartable, com.android.systemui.Dumpable
    public final void dump(PrintWriter printWriter, String[] strArr) {
        Log.d("com.android.systemui.wmshell.WMShell", "Dumping with args: " + String.join(", ", strArr));
        if (strArr[0].equals("dependency")) {
            strArr = (String[]) Arrays.copyOfRange(strArr, 1, strArr.length);
        }
        ShellInterface shellInterface = this.mShell;
        if (shellInterface.handleCommand(printWriter, strArr)) {
            return;
        }
        shellInterface.dump(printWriter);
    }

    public void initOneHanded(final OneHanded oneHanded) {
        final AnonymousClass10 anonymousClass10 = new AnonymousClass10();
        final OneHandedController.OneHandedImpl oneHandedImpl = (OneHandedController.OneHandedImpl) oneHanded;
        OneHandedController oneHandedController = OneHandedController.this;
        final int i = 0;
        ((HandlerExecutor) oneHandedController.mMainExecutor).execute(new Runnable() { // from class: com.android.wm.shell.onehanded.OneHandedController$OneHandedImpl$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                switch (i) {
                    case 0:
                        OneHandedController.OneHandedImpl oneHandedImpl2 = oneHandedImpl;
                        OneHandedController.this.mDisplayAreaOrganizer.mTransitionCallbacks.add((WMShell.AnonymousClass10) anonymousClass10);
                        break;
                    default:
                        OneHandedController.this.mEventCallback = (WMShell.AnonymousClass9) anonymousClass10;
                        break;
                }
            }
        });
        final AnonymousClass9 anonymousClass9 = new AnonymousClass9();
        OneHandedController oneHandedController2 = OneHandedController.this;
        final int i2 = 1;
        ((HandlerExecutor) oneHandedController2.mMainExecutor).execute(new Runnable() { // from class: com.android.wm.shell.onehanded.OneHandedController$OneHandedImpl$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                switch (i2) {
                    case 0:
                        OneHandedController.OneHandedImpl oneHandedImpl2 = oneHandedImpl;
                        OneHandedController.this.mDisplayAreaOrganizer.mTransitionCallbacks.add((WMShell.AnonymousClass10) anonymousClass9);
                        break;
                    default:
                        OneHandedController.this.mEventCallback = (WMShell.AnonymousClass9) anonymousClass9;
                        break;
                }
            }
        });
        this.mWakefulnessLifecycle.mObservers.add(new AnonymousClass7(1, oneHanded));
        this.mScreenLifecycle.mObservers.add(new ScreenLifecycle.Observer() { // from class: com.android.systemui.wmshell.WMShell.13
            @Override // com.android.systemui.keyguard.ScreenLifecycle.Observer
            public final void onScreenTurningOff() {
                OneHandedController.OneHandedImpl oneHandedImpl2 = (OneHandedController.OneHandedImpl) OneHanded.this;
                OneHandedController oneHandedController3 = OneHandedController.this;
                ((HandlerExecutor) oneHandedController3.mMainExecutor).execute(new OneHandedController$OneHandedImpl$$ExternalSyntheticLambda0(oneHandedImpl2, 7));
            }
        });
        this.mCommandQueue.addCallback(new CommandQueue.Callbacks() { // from class: com.android.systemui.wmshell.WMShell.14
            @Override // com.android.systemui.statusbar.CommandQueue.Callbacks
            public final void onCameraLaunchGestureDetected(int i3) {
                OneHandedController.OneHandedImpl oneHandedImpl2 = (OneHandedController.OneHandedImpl) oneHanded;
                OneHandedController oneHandedController3 = OneHandedController.this;
                ((HandlerExecutor) oneHandedController3.mMainExecutor).execute(new OneHandedController$$ExternalSyntheticLambda7(8, oneHandedImpl2));
            }

            @Override // com.android.systemui.statusbar.CommandQueue.Callbacks
            public final void setImeWindowStatus(int i3, int i4, int i5, boolean z) {
                WMShell.this.mDisplayTracker.getClass();
                if (i3 != 0 || (i4 & 2) == 0) {
                    return;
                }
                OneHandedController.OneHandedImpl oneHandedImpl2 = (OneHandedController.OneHandedImpl) oneHanded;
                OneHandedController oneHandedController3 = OneHandedController.this;
                ((HandlerExecutor) oneHandedController3.mMainExecutor).execute(new OneHandedController$OneHandedImpl$$ExternalSyntheticLambda0(oneHandedImpl2, 3));
            }
        });
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v1, types: [com.android.systemui.wmshell.WMShell$6] */
    public void initPip(final Pip pip) {
        this.mCommandQueue.addCallback(new CommandQueue.Callbacks() { // from class: com.android.systemui.wmshell.WMShell.5
            @Override // com.android.systemui.statusbar.CommandQueue.Callbacks
            public final void showPictureInPictureMenu() {
                Pip.this.showPictureInPictureMenu();
            }
        });
        pip.registerPipTransitionCallback(new PipTransitionController.PipTransitionCallback() { // from class: com.android.systemui.wmshell.WMShell.6
            @Override // com.android.wm.shell.pip.PipTransitionController.PipTransitionCallback
            public final void onPipTransitionFinished(int i) {
                WMShell wMShell = WMShell.this;
                SysUiState sysUiState = wMShell.mSysUiState;
                sysUiState.setFlag(17179869184L, false);
                wMShell.mDisplayTracker.getClass();
                sysUiState.commitUpdate(0);
            }

            @Override // com.android.wm.shell.pip.PipTransitionController.PipTransitionCallback
            public final void onPipTransitionStarted(int i, Rect rect) {
                WMShell wMShell = WMShell.this;
                SysUiState sysUiState = wMShell.mSysUiState;
                sysUiState.setFlag(17179869184L, true);
                wMShell.mDisplayTracker.getClass();
                sysUiState.commitUpdate(0);
            }

            @Override // com.android.wm.shell.pip.PipTransitionController.PipTransitionCallback
            public final void onPipTransitionCanceled(int i) {
            }
        }, this.mSysUiMainExecutor);
        SysUiState.SysUiStateCallback sysUiStateCallback = new SysUiState.SysUiStateCallback(this) { // from class: com.android.systemui.wmshell.WMShell$$ExternalSyntheticLambda0
            public final /* synthetic */ WMShell f$0;

            @Override // com.android.systemui.model.SysUiState.SysUiStateCallback
            public final void onSystemUiStateChanged(long j) {
                pip.onSystemUiStateChanged(j, (8440396 & j) == 0);
            }
        };
        SysUiState sysUiState = this.mSysUiState;
        sysUiState.mCallbacks.add(sysUiStateCallback);
        sysUiStateCallback.onSystemUiStateChanged(sysUiState.mFlags);
    }

    public void initRecentTasks(RecentTasks recentTasks) {
        final Executor executor = this.mSysUiMainExecutor;
        CommandQueue commandQueue = this.mCommandQueue;
        Objects.requireNonNull(commandQueue);
        final WMShell$$ExternalSyntheticLambda1 wMShell$$ExternalSyntheticLambda1 = new WMShell$$ExternalSyntheticLambda1(0, commandQueue);
        final RecentTasksController.RecentTasksImpl recentTasksImpl = (RecentTasksController.RecentTasksImpl) recentTasks;
        ((HandlerExecutor) RecentTasksController.this.mMainExecutor).execute(new Runnable() { // from class: com.android.wm.shell.recents.RecentTasksController$RecentTasksImpl$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                RecentTasksController.RecentTasksImpl recentTasksImpl2 = RecentTasksController.RecentTasksImpl.this;
                Executor executor2 = executor;
                WMShell$$ExternalSyntheticLambda1 wMShell$$ExternalSyntheticLambda12 = wMShell$$ExternalSyntheticLambda1;
                RecentsTransitionHandler recentsTransitionHandler = RecentTasksController.this.mTransitionHandler;
                if (recentsTransitionHandler == null) {
                    return;
                }
                recentsTransitionHandler.mStateListeners.add(new RecentsTransitionStateListener() { // from class: com.android.wm.shell.recents.RecentTasksController.RecentTasksImpl.1
                    public final /* synthetic */ Executor val$executor;
                    public final /* synthetic */ WMShell$$ExternalSyntheticLambda1 val$listener;

                    public AnonymousClass1(Executor executor22, WMShell$$ExternalSyntheticLambda1 wMShell$$ExternalSyntheticLambda122) {
                        r1 = executor22;
                        r2 = wMShell$$ExternalSyntheticLambda122;
                    }

                    @Override // com.android.wm.shell.recents.RecentsTransitionStateListener
                    public final void onAnimationStateChanged(final boolean z) {
                        Executor executor3 = r1;
                        final WMShell$$ExternalSyntheticLambda1 wMShell$$ExternalSyntheticLambda13 = r2;
                        executor3.execute(new Runnable() { // from class: com.android.wm.shell.recents.RecentTasksController$RecentTasksImpl$1$$ExternalSyntheticLambda0
                            @Override // java.lang.Runnable
                            public final void run() {
                                WMShell$$ExternalSyntheticLambda1.this.accept(Boolean.valueOf(z));
                            }
                        });
                    }
                });
            }
        });
        this.mJavaAdapter.alwaysCollectFlow(this.mCommunalTransitionViewModel.recentsBackgroundColor, new WMShell$$ExternalSyntheticLambda1(1, recentTasks));
    }

    public void initSplitScreen(final SplitScreen splitScreen) {
        this.mWakefulnessLifecycle.mObservers.add(new AnonymousClass7(0, splitScreen));
        this.mCommandQueue.addCallback(new CommandQueue.Callbacks() { // from class: com.android.systemui.wmshell.WMShell.8
            @Override // com.android.systemui.statusbar.CommandQueue.Callbacks
            public final void moveFocusedTaskToFullscreen(int i) {
                SplitScreenController splitScreenController = SplitScreenController.this;
                ((HandlerExecutor) splitScreenController.mMainExecutor).execute(new SplitScreenController$$ExternalSyntheticLambda0(splitScreenController, 2));
            }

            @Override // com.android.systemui.statusbar.CommandQueue.Callbacks
            public final void setSplitscreenFocus(final boolean z) {
                final SplitScreenController.SplitScreenImpl splitScreenImpl = (SplitScreenController.SplitScreenImpl) SplitScreen.this;
                ((HandlerExecutor) SplitScreenController.this.mMainExecutor).execute(new Runnable() { // from class: com.android.wm.shell.splitscreen.SplitScreenController$SplitScreenImpl$$ExternalSyntheticLambda3
                    @Override // java.lang.Runnable
                    public final void run() {
                        SplitScreenController.SplitScreenImpl splitScreenImpl2 = SplitScreenController.SplitScreenImpl.this;
                        boolean z2 = z;
                        StageCoordinator stageCoordinator = SplitScreenController.this.mStageCoordinator;
                        if (stageCoordinator.mMainStage.mIsActive) {
                            int i = stageCoordinator.mSideStagePosition;
                            if (i == 1) {
                                if (z2) {
                                    i = SplitScreenUtils.reverseSplitPosition(i);
                                }
                            } else if (!z2) {
                                i = SplitScreenUtils.reverseSplitPosition(i);
                            }
                            IActivityTaskManager asInterface = IActivityTaskManager.Stub.asInterface(ServiceManager.getService("activity_task"));
                            int i2 = -1;
                            if (i != -1) {
                                try {
                                    i2 = stageCoordinator.mSideStagePosition == i ? stageCoordinator.mSideStage.getTopVisibleChildTaskId() : stageCoordinator.mMainStage.getTopVisibleChildTaskId();
                                } catch (RemoteException | NullPointerException e) {
                                    if (ProtoLogImpl_411527699.Cache.WM_SHELL_SPLIT_SCREEN_enabled[4]) {
                                        ProtoLogImpl_411527699.e(ShellProtoLogGroup.WM_SHELL_SPLIT_SCREEN, -7228576009982558496L, 0, String.valueOf(e.getMessage()));
                                        return;
                                    }
                                    return;
                                }
                            }
                            asInterface.setFocusedTask(i2);
                        }
                    }
                });
            }
        });
        ((SplitScreenController.SplitScreenImpl) splitScreen).registerSplitAnimationListener(new AnonymousClass9(), this.mSysUiMainExecutor);
    }

    @Override // com.android.systemui.CoreStartable
    public final boolean isDumpCritical() {
        return false;
    }

    @Override // com.android.systemui.CoreStartable
    public final void start() {
        this.mShell.onConfigurationChanged(this.mContext.getResources().getConfiguration());
        ((ConfigurationControllerImpl) this.mConfigurationController).addCallback(this.mConfigurationListener);
        ((KeyguardStateControllerImpl) this.mKeyguardStateController).addCallback(this.mKeyguardStateCallback);
        this.mKeyguardUpdateMonitor.registerCallback(this.mKeyguardUpdateMonitorCallback);
        ((UserTrackerImpl) this.mUserTracker).addCallback(this.mUserChangedCallback, this.mContext.getMainExecutor());
        this.mCommandQueue.addCallback((CommandQueue.Callbacks) this);
        final int i = 0;
        this.mPipOptional.ifPresent(new Consumer(this) { // from class: com.android.systemui.wmshell.WMShell$$ExternalSyntheticLambda3
            public final /* synthetic */ WMShell f$0;

            {
                this.f$0 = this;
            }

            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                int i2 = i;
                final WMShell wMShell = this.f$0;
                switch (i2) {
                    case 0:
                        wMShell.initPip((Pip) obj);
                        break;
                    case 1:
                        wMShell.initSplitScreen((SplitScreen) obj);
                        break;
                    case 2:
                        wMShell.initOneHanded((OneHanded) obj);
                        break;
                    case 3:
                        final DesktopTasksController.DesktopModeImpl desktopModeImpl = (DesktopTasksController.DesktopModeImpl) obj;
                        wMShell.getClass();
                        DesktopModeTaskRepository.VisibleTasksListener visibleTasksListener = new DesktopModeTaskRepository.VisibleTasksListener() { // from class: com.android.systemui.wmshell.WMShell.15
                            @Override // com.android.wm.shell.desktopmode.DesktopModeTaskRepository.VisibleTasksListener
                            public final void onTasksVisibilityChanged(int i3, int i4) {
                                if (i3 == 0) {
                                    WMShell wMShell2 = WMShell.this;
                                    SysUiState sysUiState = wMShell2.mSysUiState;
                                    sysUiState.setFlag(67108864L, i4 > 0);
                                    wMShell2.mDisplayTracker.getClass();
                                    sysUiState.commitUpdate(0);
                                }
                            }
                        };
                        Executor executor = wMShell.mSysUiMainExecutor;
                        DesktopTasksController desktopTasksController = DesktopTasksController.this;
                        ((HandlerExecutor) desktopTasksController.mainExecutor).execute(new DesktopTasksController$DesktopModeImpl$addVisibleTasksListener$1(desktopTasksController, visibleTasksListener, executor, 0));
                        wMShell.mCommandQueue.addCallback(new CommandQueue.Callbacks() { // from class: com.android.systemui.wmshell.WMShell.16
                            @Override // com.android.systemui.statusbar.CommandQueue.Callbacks
                            public final void moveFocusedTaskToDesktop(int i3) {
                                Parcelable.Creator creator = DesktopModeTransitionSource.CREATOR;
                                DesktopTasksController desktopTasksController2 = DesktopTasksController.this;
                                ((HandlerExecutor) desktopTasksController2.mainExecutor).execute(new DesktopTasksController$DesktopModeImpl$moveFocusedTaskToDesktop$1(desktopTasksController2, i3, 0));
                            }

                            @Override // com.android.systemui.statusbar.CommandQueue.Callbacks
                            public final void moveFocusedTaskToFullscreen(int i3) {
                                Parcelable.Creator creator = DesktopModeTransitionSource.CREATOR;
                                DesktopTasksController desktopTasksController2 = DesktopTasksController.this;
                                ((HandlerExecutor) desktopTasksController2.mainExecutor).execute(new DesktopTasksController$DesktopModeImpl$moveFocusedTaskToDesktop$1(desktopTasksController2, i3, 1));
                            }

                            @Override // com.android.systemui.statusbar.CommandQueue.Callbacks
                            public final void moveFocusedTaskToStageSplit(final int i3, final boolean z) {
                                final DesktopTasksController desktopTasksController2 = DesktopTasksController.this;
                                ((HandlerExecutor) desktopTasksController2.mainExecutor).execute(new Runnable() { // from class: com.android.wm.shell.desktopmode.DesktopTasksController$DesktopModeImpl$moveFocusedTaskToStageSplit$1
                                    @Override // java.lang.Runnable
                                    public final void run() {
                                        DesktopTasksController desktopTasksController3 = DesktopTasksController.this;
                                        int i4 = i3;
                                        boolean z2 = z;
                                        ActivityManager.RunningTaskInfo focusedFreeformTask = desktopTasksController3.getFocusedFreeformTask(i4);
                                        if (focusedFreeformTask != null) {
                                            desktopTasksController3.requestSplit(focusedFreeformTask, z2);
                                        }
                                    }
                                });
                            }
                        });
                        break;
                    default:
                        wMShell.initRecentTasks((RecentTasks) obj);
                        break;
                }
            }
        });
        final int i2 = 1;
        this.mSplitScreenOptional.ifPresent(new Consumer(this) { // from class: com.android.systemui.wmshell.WMShell$$ExternalSyntheticLambda3
            public final /* synthetic */ WMShell f$0;

            {
                this.f$0 = this;
            }

            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                int i22 = i2;
                final WMShell wMShell = this.f$0;
                switch (i22) {
                    case 0:
                        wMShell.initPip((Pip) obj);
                        break;
                    case 1:
                        wMShell.initSplitScreen((SplitScreen) obj);
                        break;
                    case 2:
                        wMShell.initOneHanded((OneHanded) obj);
                        break;
                    case 3:
                        final DesktopTasksController.DesktopModeImpl desktopModeImpl = (DesktopTasksController.DesktopModeImpl) obj;
                        wMShell.getClass();
                        DesktopModeTaskRepository.VisibleTasksListener visibleTasksListener = new DesktopModeTaskRepository.VisibleTasksListener() { // from class: com.android.systemui.wmshell.WMShell.15
                            @Override // com.android.wm.shell.desktopmode.DesktopModeTaskRepository.VisibleTasksListener
                            public final void onTasksVisibilityChanged(int i3, int i4) {
                                if (i3 == 0) {
                                    WMShell wMShell2 = WMShell.this;
                                    SysUiState sysUiState = wMShell2.mSysUiState;
                                    sysUiState.setFlag(67108864L, i4 > 0);
                                    wMShell2.mDisplayTracker.getClass();
                                    sysUiState.commitUpdate(0);
                                }
                            }
                        };
                        Executor executor = wMShell.mSysUiMainExecutor;
                        DesktopTasksController desktopTasksController = DesktopTasksController.this;
                        ((HandlerExecutor) desktopTasksController.mainExecutor).execute(new DesktopTasksController$DesktopModeImpl$addVisibleTasksListener$1(desktopTasksController, visibleTasksListener, executor, 0));
                        wMShell.mCommandQueue.addCallback(new CommandQueue.Callbacks() { // from class: com.android.systemui.wmshell.WMShell.16
                            @Override // com.android.systemui.statusbar.CommandQueue.Callbacks
                            public final void moveFocusedTaskToDesktop(int i3) {
                                Parcelable.Creator creator = DesktopModeTransitionSource.CREATOR;
                                DesktopTasksController desktopTasksController2 = DesktopTasksController.this;
                                ((HandlerExecutor) desktopTasksController2.mainExecutor).execute(new DesktopTasksController$DesktopModeImpl$moveFocusedTaskToDesktop$1(desktopTasksController2, i3, 0));
                            }

                            @Override // com.android.systemui.statusbar.CommandQueue.Callbacks
                            public final void moveFocusedTaskToFullscreen(int i3) {
                                Parcelable.Creator creator = DesktopModeTransitionSource.CREATOR;
                                DesktopTasksController desktopTasksController2 = DesktopTasksController.this;
                                ((HandlerExecutor) desktopTasksController2.mainExecutor).execute(new DesktopTasksController$DesktopModeImpl$moveFocusedTaskToDesktop$1(desktopTasksController2, i3, 1));
                            }

                            @Override // com.android.systemui.statusbar.CommandQueue.Callbacks
                            public final void moveFocusedTaskToStageSplit(final int i3, final boolean z) {
                                final DesktopTasksController desktopTasksController2 = DesktopTasksController.this;
                                ((HandlerExecutor) desktopTasksController2.mainExecutor).execute(new Runnable() { // from class: com.android.wm.shell.desktopmode.DesktopTasksController$DesktopModeImpl$moveFocusedTaskToStageSplit$1
                                    @Override // java.lang.Runnable
                                    public final void run() {
                                        DesktopTasksController desktopTasksController3 = DesktopTasksController.this;
                                        int i4 = i3;
                                        boolean z2 = z;
                                        ActivityManager.RunningTaskInfo focusedFreeformTask = desktopTasksController3.getFocusedFreeformTask(i4);
                                        if (focusedFreeformTask != null) {
                                            desktopTasksController3.requestSplit(focusedFreeformTask, z2);
                                        }
                                    }
                                });
                            }
                        });
                        break;
                    default:
                        wMShell.initRecentTasks((RecentTasks) obj);
                        break;
                }
            }
        });
        final int i3 = 2;
        this.mOneHandedOptional.ifPresent(new Consumer(this) { // from class: com.android.systemui.wmshell.WMShell$$ExternalSyntheticLambda3
            public final /* synthetic */ WMShell f$0;

            {
                this.f$0 = this;
            }

            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                int i22 = i3;
                final WMShell wMShell = this.f$0;
                switch (i22) {
                    case 0:
                        wMShell.initPip((Pip) obj);
                        break;
                    case 1:
                        wMShell.initSplitScreen((SplitScreen) obj);
                        break;
                    case 2:
                        wMShell.initOneHanded((OneHanded) obj);
                        break;
                    case 3:
                        final DesktopTasksController.DesktopModeImpl desktopModeImpl = (DesktopTasksController.DesktopModeImpl) obj;
                        wMShell.getClass();
                        DesktopModeTaskRepository.VisibleTasksListener visibleTasksListener = new DesktopModeTaskRepository.VisibleTasksListener() { // from class: com.android.systemui.wmshell.WMShell.15
                            @Override // com.android.wm.shell.desktopmode.DesktopModeTaskRepository.VisibleTasksListener
                            public final void onTasksVisibilityChanged(int i32, int i4) {
                                if (i32 == 0) {
                                    WMShell wMShell2 = WMShell.this;
                                    SysUiState sysUiState = wMShell2.mSysUiState;
                                    sysUiState.setFlag(67108864L, i4 > 0);
                                    wMShell2.mDisplayTracker.getClass();
                                    sysUiState.commitUpdate(0);
                                }
                            }
                        };
                        Executor executor = wMShell.mSysUiMainExecutor;
                        DesktopTasksController desktopTasksController = DesktopTasksController.this;
                        ((HandlerExecutor) desktopTasksController.mainExecutor).execute(new DesktopTasksController$DesktopModeImpl$addVisibleTasksListener$1(desktopTasksController, visibleTasksListener, executor, 0));
                        wMShell.mCommandQueue.addCallback(new CommandQueue.Callbacks() { // from class: com.android.systemui.wmshell.WMShell.16
                            @Override // com.android.systemui.statusbar.CommandQueue.Callbacks
                            public final void moveFocusedTaskToDesktop(int i32) {
                                Parcelable.Creator creator = DesktopModeTransitionSource.CREATOR;
                                DesktopTasksController desktopTasksController2 = DesktopTasksController.this;
                                ((HandlerExecutor) desktopTasksController2.mainExecutor).execute(new DesktopTasksController$DesktopModeImpl$moveFocusedTaskToDesktop$1(desktopTasksController2, i32, 0));
                            }

                            @Override // com.android.systemui.statusbar.CommandQueue.Callbacks
                            public final void moveFocusedTaskToFullscreen(int i32) {
                                Parcelable.Creator creator = DesktopModeTransitionSource.CREATOR;
                                DesktopTasksController desktopTasksController2 = DesktopTasksController.this;
                                ((HandlerExecutor) desktopTasksController2.mainExecutor).execute(new DesktopTasksController$DesktopModeImpl$moveFocusedTaskToDesktop$1(desktopTasksController2, i32, 1));
                            }

                            @Override // com.android.systemui.statusbar.CommandQueue.Callbacks
                            public final void moveFocusedTaskToStageSplit(final int i32, final boolean z) {
                                final DesktopTasksController desktopTasksController2 = DesktopTasksController.this;
                                ((HandlerExecutor) desktopTasksController2.mainExecutor).execute(new Runnable() { // from class: com.android.wm.shell.desktopmode.DesktopTasksController$DesktopModeImpl$moveFocusedTaskToStageSplit$1
                                    @Override // java.lang.Runnable
                                    public final void run() {
                                        DesktopTasksController desktopTasksController3 = DesktopTasksController.this;
                                        int i4 = i32;
                                        boolean z2 = z;
                                        ActivityManager.RunningTaskInfo focusedFreeformTask = desktopTasksController3.getFocusedFreeformTask(i4);
                                        if (focusedFreeformTask != null) {
                                            desktopTasksController3.requestSplit(focusedFreeformTask, z2);
                                        }
                                    }
                                });
                            }
                        });
                        break;
                    default:
                        wMShell.initRecentTasks((RecentTasks) obj);
                        break;
                }
            }
        });
        final int i4 = 3;
        this.mDesktopModeOptional.ifPresent(new Consumer(this) { // from class: com.android.systemui.wmshell.WMShell$$ExternalSyntheticLambda3
            public final /* synthetic */ WMShell f$0;

            {
                this.f$0 = this;
            }

            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                int i22 = i4;
                final WMShell wMShell = this.f$0;
                switch (i22) {
                    case 0:
                        wMShell.initPip((Pip) obj);
                        break;
                    case 1:
                        wMShell.initSplitScreen((SplitScreen) obj);
                        break;
                    case 2:
                        wMShell.initOneHanded((OneHanded) obj);
                        break;
                    case 3:
                        final DesktopTasksController.DesktopModeImpl desktopModeImpl = (DesktopTasksController.DesktopModeImpl) obj;
                        wMShell.getClass();
                        DesktopModeTaskRepository.VisibleTasksListener visibleTasksListener = new DesktopModeTaskRepository.VisibleTasksListener() { // from class: com.android.systemui.wmshell.WMShell.15
                            @Override // com.android.wm.shell.desktopmode.DesktopModeTaskRepository.VisibleTasksListener
                            public final void onTasksVisibilityChanged(int i32, int i42) {
                                if (i32 == 0) {
                                    WMShell wMShell2 = WMShell.this;
                                    SysUiState sysUiState = wMShell2.mSysUiState;
                                    sysUiState.setFlag(67108864L, i42 > 0);
                                    wMShell2.mDisplayTracker.getClass();
                                    sysUiState.commitUpdate(0);
                                }
                            }
                        };
                        Executor executor = wMShell.mSysUiMainExecutor;
                        DesktopTasksController desktopTasksController = DesktopTasksController.this;
                        ((HandlerExecutor) desktopTasksController.mainExecutor).execute(new DesktopTasksController$DesktopModeImpl$addVisibleTasksListener$1(desktopTasksController, visibleTasksListener, executor, 0));
                        wMShell.mCommandQueue.addCallback(new CommandQueue.Callbacks() { // from class: com.android.systemui.wmshell.WMShell.16
                            @Override // com.android.systemui.statusbar.CommandQueue.Callbacks
                            public final void moveFocusedTaskToDesktop(int i32) {
                                Parcelable.Creator creator = DesktopModeTransitionSource.CREATOR;
                                DesktopTasksController desktopTasksController2 = DesktopTasksController.this;
                                ((HandlerExecutor) desktopTasksController2.mainExecutor).execute(new DesktopTasksController$DesktopModeImpl$moveFocusedTaskToDesktop$1(desktopTasksController2, i32, 0));
                            }

                            @Override // com.android.systemui.statusbar.CommandQueue.Callbacks
                            public final void moveFocusedTaskToFullscreen(int i32) {
                                Parcelable.Creator creator = DesktopModeTransitionSource.CREATOR;
                                DesktopTasksController desktopTasksController2 = DesktopTasksController.this;
                                ((HandlerExecutor) desktopTasksController2.mainExecutor).execute(new DesktopTasksController$DesktopModeImpl$moveFocusedTaskToDesktop$1(desktopTasksController2, i32, 1));
                            }

                            @Override // com.android.systemui.statusbar.CommandQueue.Callbacks
                            public final void moveFocusedTaskToStageSplit(final int i32, final boolean z) {
                                final DesktopTasksController desktopTasksController2 = DesktopTasksController.this;
                                ((HandlerExecutor) desktopTasksController2.mainExecutor).execute(new Runnable() { // from class: com.android.wm.shell.desktopmode.DesktopTasksController$DesktopModeImpl$moveFocusedTaskToStageSplit$1
                                    @Override // java.lang.Runnable
                                    public final void run() {
                                        DesktopTasksController desktopTasksController3 = DesktopTasksController.this;
                                        int i42 = i32;
                                        boolean z2 = z;
                                        ActivityManager.RunningTaskInfo focusedFreeformTask = desktopTasksController3.getFocusedFreeformTask(i42);
                                        if (focusedFreeformTask != null) {
                                            desktopTasksController3.requestSplit(focusedFreeformTask, z2);
                                        }
                                    }
                                });
                            }
                        });
                        break;
                    default:
                        wMShell.initRecentTasks((RecentTasks) obj);
                        break;
                }
            }
        });
        final int i5 = 4;
        this.mRecentTasksOptional.ifPresent(new Consumer(this) { // from class: com.android.systemui.wmshell.WMShell$$ExternalSyntheticLambda3
            public final /* synthetic */ WMShell f$0;

            {
                this.f$0 = this;
            }

            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                int i22 = i5;
                final WMShell wMShell = this.f$0;
                switch (i22) {
                    case 0:
                        wMShell.initPip((Pip) obj);
                        break;
                    case 1:
                        wMShell.initSplitScreen((SplitScreen) obj);
                        break;
                    case 2:
                        wMShell.initOneHanded((OneHanded) obj);
                        break;
                    case 3:
                        final DesktopTasksController.DesktopModeImpl desktopModeImpl = (DesktopTasksController.DesktopModeImpl) obj;
                        wMShell.getClass();
                        DesktopModeTaskRepository.VisibleTasksListener visibleTasksListener = new DesktopModeTaskRepository.VisibleTasksListener() { // from class: com.android.systemui.wmshell.WMShell.15
                            @Override // com.android.wm.shell.desktopmode.DesktopModeTaskRepository.VisibleTasksListener
                            public final void onTasksVisibilityChanged(int i32, int i42) {
                                if (i32 == 0) {
                                    WMShell wMShell2 = WMShell.this;
                                    SysUiState sysUiState = wMShell2.mSysUiState;
                                    sysUiState.setFlag(67108864L, i42 > 0);
                                    wMShell2.mDisplayTracker.getClass();
                                    sysUiState.commitUpdate(0);
                                }
                            }
                        };
                        Executor executor = wMShell.mSysUiMainExecutor;
                        DesktopTasksController desktopTasksController = DesktopTasksController.this;
                        ((HandlerExecutor) desktopTasksController.mainExecutor).execute(new DesktopTasksController$DesktopModeImpl$addVisibleTasksListener$1(desktopTasksController, visibleTasksListener, executor, 0));
                        wMShell.mCommandQueue.addCallback(new CommandQueue.Callbacks() { // from class: com.android.systemui.wmshell.WMShell.16
                            @Override // com.android.systemui.statusbar.CommandQueue.Callbacks
                            public final void moveFocusedTaskToDesktop(int i32) {
                                Parcelable.Creator creator = DesktopModeTransitionSource.CREATOR;
                                DesktopTasksController desktopTasksController2 = DesktopTasksController.this;
                                ((HandlerExecutor) desktopTasksController2.mainExecutor).execute(new DesktopTasksController$DesktopModeImpl$moveFocusedTaskToDesktop$1(desktopTasksController2, i32, 0));
                            }

                            @Override // com.android.systemui.statusbar.CommandQueue.Callbacks
                            public final void moveFocusedTaskToFullscreen(int i32) {
                                Parcelable.Creator creator = DesktopModeTransitionSource.CREATOR;
                                DesktopTasksController desktopTasksController2 = DesktopTasksController.this;
                                ((HandlerExecutor) desktopTasksController2.mainExecutor).execute(new DesktopTasksController$DesktopModeImpl$moveFocusedTaskToDesktop$1(desktopTasksController2, i32, 1));
                            }

                            @Override // com.android.systemui.statusbar.CommandQueue.Callbacks
                            public final void moveFocusedTaskToStageSplit(final int i32, final boolean z) {
                                final DesktopTasksController desktopTasksController2 = DesktopTasksController.this;
                                ((HandlerExecutor) desktopTasksController2.mainExecutor).execute(new Runnable() { // from class: com.android.wm.shell.desktopmode.DesktopTasksController$DesktopModeImpl$moveFocusedTaskToStageSplit$1
                                    @Override // java.lang.Runnable
                                    public final void run() {
                                        DesktopTasksController desktopTasksController3 = DesktopTasksController.this;
                                        int i42 = i32;
                                        boolean z2 = z;
                                        ActivityManager.RunningTaskInfo focusedFreeformTask = desktopTasksController3.getFocusedFreeformTask(i42);
                                        if (focusedFreeformTask != null) {
                                            desktopTasksController3.requestSplit(focusedFreeformTask, z2);
                                        }
                                    }
                                });
                            }
                        });
                        break;
                    default:
                        wMShell.initRecentTasks((RecentTasks) obj);
                        break;
                }
            }
        });
        NoteTaskInitializer noteTaskInitializer = this.mNoteTaskInitializer;
        noteTaskInitializer.getClass();
        boolean z = Build.IS_DEBUGGABLE;
        Reflection.getOrCreateKotlinClass(NoteTaskInitializer.class).getSimpleName();
        if (!noteTaskInitializer.isEnabled || noteTaskInitializer.optionalBubbles.isEmpty()) {
            return;
        }
        CommandQueue commandQueue = noteTaskInitializer.commandQueue;
        NoteTaskInitializer$callbacks$1 noteTaskInitializer$callbacks$1 = noteTaskInitializer.callbacks;
        commandQueue.addCallback((CommandQueue.Callbacks) noteTaskInitializer$callbacks$1);
        noteTaskInitializer.roleManager.addOnRoleHoldersChangedListenerAsUser(noteTaskInitializer.backgroundExecutor, noteTaskInitializer$callbacks$1, UserHandle.ALL);
        UserTrackerImpl userTrackerImpl = (UserTrackerImpl) noteTaskInitializer.userTracker;
        int userId = userTrackerImpl.getUserId();
        KeyguardUpdateMonitor keyguardUpdateMonitor = noteTaskInitializer.keyguardUpdateMonitor;
        if (keyguardUpdateMonitor.mUserIsUnlocked.get(userId)) {
            noteTaskInitializer.controller.updateNoteTaskForCurrentUserAndManagedProfiles();
        }
        keyguardUpdateMonitor.registerCallback(noteTaskInitializer$callbacks$1);
        userTrackerImpl.addCallback(noteTaskInitializer$callbacks$1, noteTaskInitializer.backgroundExecutor);
    }
}
