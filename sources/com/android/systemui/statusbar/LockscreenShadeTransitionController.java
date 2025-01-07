package com.android.systemui.statusbar;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.Configuration;
import android.util.IndentingPrintWriter;
import android.util.MathUtils;
import android.view.View;
import android.view.ViewConfiguration;
import com.android.systemui.Dumpable;
import com.android.systemui.classifier.FalsingCollector;
import com.android.systemui.dump.DumpManager;
import com.android.systemui.keyguard.WakefulnessLifecycle;
import com.android.systemui.keyguard.domain.interactor.NaturalScrollingSettingObserver;
import com.android.systemui.media.controls.ui.controller.MediaHierarchyManager;
import com.android.systemui.media.controls.ui.view.MediaHost;
import com.android.systemui.plugins.ActivityStarter;
import com.android.systemui.plugins.FalsingManager;
import com.android.systemui.plugins.qs.QS;
import com.android.systemui.plugins.statusbar.StatusBarStateController;
import com.android.systemui.qs.QSImpl;
import com.android.systemui.qs.ui.adapter.QSSceneAdapterImpl;
import com.android.systemui.shade.QuickSettingsControllerImpl;
import com.android.systemui.shade.data.repository.ShadeRepository;
import com.android.systemui.shade.data.repository.ShadeRepositoryImpl;
import com.android.systemui.shade.domain.interactor.ShadeInteractor;
import com.android.systemui.shade.domain.interactor.ShadeInteractorImpl;
import com.android.systemui.shade.domain.interactor.ShadeLockscreenInteractor;
import com.android.systemui.statusbar.notification.collection.NotificationEntry;
import com.android.systemui.statusbar.notification.row.ExpandableNotificationRow;
import com.android.systemui.statusbar.notification.row.ExpandableView;
import com.android.systemui.statusbar.notification.stack.AmbientState;
import com.android.systemui.statusbar.notification.stack.NotificationStackScrollLayout;
import com.android.systemui.statusbar.notification.stack.NotificationStackScrollLayoutController;
import com.android.systemui.statusbar.phone.CentralSurfacesImpl;
import com.android.systemui.statusbar.phone.ConfigurationControllerImpl;
import com.android.systemui.statusbar.phone.KeyguardBypassController;
import com.android.systemui.statusbar.phone.LSShadeTransitionLogger;
import com.android.systemui.statusbar.phone.ScrimController;
import com.android.systemui.statusbar.policy.ConfigurationController;
import com.android.systemui.statusbar.policy.SplitShadeStateControllerImpl;
import com.android.wm.shell.R;
import com.android.wm.shell.shared.animation.Interpolators;
import com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl;
import com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl;
import com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$18;
import com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$19;
import com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$20;
import com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$21;
import com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl;
import dagger.Lazy;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;
import kotlinx.coroutines.flow.StateFlowImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class LockscreenShadeTransitionController implements Dumpable {
    public final ActivityStarter activityStarter;
    public final AmbientState ambientState;
    public Lambda animationHandlerOnKeyguardDismiss;
    public final List callbacks;
    public CentralSurfacesImpl centralSurfaces;
    public final Context context;
    public final NotificationShadeDepthController depthController;
    public int depthControllerTransitionDistance;
    public float dragDownAmount;
    public ValueAnimator dragDownAnimator;
    public NotificationEntry draggedDownEntry;
    public final FalsingCollector falsingCollector;
    public boolean forceApplyAmount;
    public float fractionToShade;
    public int fullTransitionDistance;
    public int fullTransitionDistanceByTap;
    public boolean isWakingToShadeLocked;
    public final KeyguardBypassController keyguardBypassController;
    public final DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$18 keyguardTransitionControllerFactory;
    public final Lazy lazyQSSceneAdapter;
    public final NotificationLockscreenUserManager lockScreenUserManager;
    public final LSShadeTransitionLogger logger;
    public final MediaHierarchyManager mediaHierarchyManager;
    public boolean nextHideKeyguardNeedsNoAnimation;
    public int notificationShelfTransitionDistance;
    public NotificationStackScrollLayoutController nsslController;
    public float pulseHeight;
    public ValueAnimator pulseHeightAnimator;
    public QS qS;
    public final LockscreenShadeQsTransitionController qsTransitionController;
    public final LockscreenShadeScrimTransitionController scrimTransitionController;
    public final ShadeInteractor shadeInteractor;
    public final Lazy shadeLockscreenInteractorLazy;
    public final ShadeRepository shadeRepository;
    public final DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$20 singleShadeOverScrollerFactory;
    public final DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$19 splitShadeOverScrollerFactory;
    public final SplitShadeStateControllerImpl splitShadeStateController;
    public final SysuiStatusBarStateController statusBarStateController;
    public int statusBarTransitionDistance;
    public final DragDownHelper touchHelper;
    public int udfpsTransitionDistance;
    public boolean useSplitShade;
    public final kotlin.Lazy splitShadeOverScroller$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.android.systemui.statusbar.LockscreenShadeTransitionController$splitShadeOverScroller$2

        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        /* renamed from: com.android.systemui.statusbar.LockscreenShadeTransitionController$splitShadeOverScroller$2$1, reason: invalid class name */
        final class AnonymousClass1 extends Lambda implements Function0 {
            final /* synthetic */ LockscreenShadeTransitionController this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public AnonymousClass1(LockscreenShadeTransitionController lockscreenShadeTransitionController) {
                super(0);
                this.this$0 = lockscreenShadeTransitionController;
            }

            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return this.this$0.qS;
            }
        }

        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        /* renamed from: com.android.systemui.statusbar.LockscreenShadeTransitionController$splitShadeOverScroller$2$2, reason: invalid class name */
        final class AnonymousClass2 extends Lambda implements Function0 {
            final /* synthetic */ LockscreenShadeTransitionController this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public AnonymousClass2(LockscreenShadeTransitionController lockscreenShadeTransitionController) {
                super(0);
                this.this$0 = lockscreenShadeTransitionController;
            }

            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                NotificationStackScrollLayoutController notificationStackScrollLayoutController = this.this$0.nsslController;
                if (notificationStackScrollLayoutController == null) {
                    return null;
                }
                return notificationStackScrollLayoutController;
            }
        }

        {
            super(0);
        }

        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            LockscreenShadeTransitionController lockscreenShadeTransitionController = LockscreenShadeTransitionController.this;
            DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$19 daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$19 = lockscreenShadeTransitionController.splitShadeOverScrollerFactory;
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(lockscreenShadeTransitionController);
            AnonymousClass2 anonymousClass2 = new AnonymousClass2(lockscreenShadeTransitionController);
            daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$19.getClass();
            DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider switchingProvider = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$19.this$0;
            ConfigurationController configurationController = (ConfigurationController) ((DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) switchingProvider.wMComponentImpl).provideGlobalConfigurationControllerProvider.get();
            DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl = switchingProvider.sysUIGoogleGlobalRootComponentImpl;
            DumpManager dumpManager = (DumpManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.dumpManagerProvider.get();
            Context context = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.context;
            DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl = (DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) switchingProvider.wMComponentImpl;
            return new SplitShadeLockScreenOverScroller(configurationController, dumpManager, context, (ScrimController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.scrimControllerProvider.get(), (SysuiStatusBarStateController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.statusBarStateControllerImplProvider.get(), anonymousClass1, anonymousClass2);
        }
    });
    public final kotlin.Lazy phoneShadeOverScroller$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.android.systemui.statusbar.LockscreenShadeTransitionController$phoneShadeOverScroller$2
        {
            super(0);
        }

        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            LockscreenShadeTransitionController lockscreenShadeTransitionController = LockscreenShadeTransitionController.this;
            DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$20 daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$20 = lockscreenShadeTransitionController.singleShadeOverScrollerFactory;
            NotificationStackScrollLayoutController notificationStackScrollLayoutController = lockscreenShadeTransitionController.nsslController;
            if (notificationStackScrollLayoutController == null) {
                notificationStackScrollLayoutController = null;
            }
            DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider switchingProvider = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$20.this$0;
            return new SingleShadeLockScreenOverScroller((ConfigurationController) ((DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) switchingProvider.wMComponentImpl).provideGlobalConfigurationControllerProvider.get(), switchingProvider.sysUIGoogleGlobalRootComponentImpl.context, (SysuiStatusBarStateController) ((DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) switchingProvider.wMComponentImpl).statusBarStateControllerImplProvider.get(), notificationStackScrollLayoutController);
        }
    });
    public final kotlin.Lazy keyguardTransitionController$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.android.systemui.statusbar.LockscreenShadeTransitionController$keyguardTransitionController$2
        {
            super(0);
        }

        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            LockscreenShadeTransitionController lockscreenShadeTransitionController = LockscreenShadeTransitionController.this;
            DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$18 daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$18 = lockscreenShadeTransitionController.keyguardTransitionControllerFactory;
            ShadeLockscreenInteractor shadeLockscreenInteractor = (ShadeLockscreenInteractor) lockscreenShadeTransitionController.shadeLockscreenInteractorLazy.get();
            DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider switchingProvider = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$18.this$0;
            MediaHierarchyManager mediaHierarchyManager = (MediaHierarchyManager) ((DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) switchingProvider.wMComponentImpl).mediaHierarchyManagerProvider.get();
            DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl = switchingProvider.sysUIGoogleGlobalRootComponentImpl;
            Context context = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.context;
            DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl = (DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) switchingProvider.wMComponentImpl;
            return new LockscreenShadeKeyguardTransitionController(mediaHierarchyManager, shadeLockscreenInteractor, context, (ConfigurationController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideGlobalConfigurationControllerProvider.get(), (DumpManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.dumpManagerProvider.get(), (SplitShadeStateControllerImpl) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.splitShadeStateControllerImplProvider.get());
        }
    });

    public LockscreenShadeTransitionController(SysuiStatusBarStateController sysuiStatusBarStateController, LSShadeTransitionLogger lSShadeTransitionLogger, KeyguardBypassController keyguardBypassController, NotificationLockscreenUserManager notificationLockscreenUserManager, FalsingCollector falsingCollector, AmbientState ambientState, MediaHierarchyManager mediaHierarchyManager, LockscreenShadeScrimTransitionController lockscreenShadeScrimTransitionController, DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$18 daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$18, NotificationShadeDepthController notificationShadeDepthController, Context context, DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$19 daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$19, DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$20 daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$20, ActivityStarter activityStarter, WakefulnessLifecycle wakefulnessLifecycle, ConfigurationController configurationController, FalsingManager falsingManager, DumpManager dumpManager, DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$21 daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$21, ShadeRepository shadeRepository, ShadeInteractor shadeInteractor, SplitShadeStateControllerImpl splitShadeStateControllerImpl, Lazy lazy, NaturalScrollingSettingObserver naturalScrollingSettingObserver, Lazy lazy2) {
        this.statusBarStateController = sysuiStatusBarStateController;
        this.logger = lSShadeTransitionLogger;
        this.keyguardBypassController = keyguardBypassController;
        this.lockScreenUserManager = notificationLockscreenUserManager;
        this.falsingCollector = falsingCollector;
        this.ambientState = ambientState;
        this.mediaHierarchyManager = mediaHierarchyManager;
        this.scrimTransitionController = lockscreenShadeScrimTransitionController;
        this.keyguardTransitionControllerFactory = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$18;
        this.depthController = notificationShadeDepthController;
        this.context = context;
        this.splitShadeOverScrollerFactory = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$19;
        this.singleShadeOverScrollerFactory = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$20;
        this.activityStarter = activityStarter;
        this.shadeRepository = shadeRepository;
        this.shadeInteractor = shadeInteractor;
        this.splitShadeStateController = splitShadeStateControllerImpl;
        this.shadeLockscreenInteractorLazy = lazy;
        this.lazyQSSceneAdapter = lazy2;
        this.touchHelper = new DragDownHelper(falsingManager, this, naturalScrollingSettingObserver, shadeRepository, context);
        Function0 function0 = new Function0() { // from class: com.android.systemui.statusbar.LockscreenShadeTransitionController$qsTransitionController$1
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return LockscreenShadeTransitionController.this.qS;
            }
        };
        DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider switchingProvider = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$21.this$0;
        Context context2 = switchingProvider.sysUIGoogleGlobalRootComponentImpl.context;
        DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl = (DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) switchingProvider.wMComponentImpl;
        this.qsTransitionController = new LockscreenShadeQsTransitionController(context2, (ConfigurationController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideGlobalConfigurationControllerProvider.get(), (DumpManager) switchingProvider.sysUIGoogleGlobalRootComponentImpl.dumpManagerProvider.get(), function0, (SplitShadeStateControllerImpl) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.splitShadeStateControllerImplProvider.get());
        this.callbacks = new ArrayList();
        updateResources$8();
        ((ConfigurationControllerImpl) configurationController).addCallback(new ConfigurationController.ConfigurationListener() { // from class: com.android.systemui.statusbar.LockscreenShadeTransitionController.1
            @Override // com.android.systemui.statusbar.policy.ConfigurationController.ConfigurationListener
            public final void onConfigChanged(Configuration configuration) {
                LockscreenShadeTransitionController lockscreenShadeTransitionController = LockscreenShadeTransitionController.this;
                lockscreenShadeTransitionController.updateResources$8();
                Context context3 = lockscreenShadeTransitionController.context;
                DragDownHelper dragDownHelper = lockscreenShadeTransitionController.touchHelper;
                dragDownHelper.getClass();
                dragDownHelper.minDragDistance = context3.getResources().getDimensionPixelSize(R.dimen.keyguard_drag_down_min_distance);
                ViewConfiguration viewConfiguration = ViewConfiguration.get(context3);
                dragDownHelper.touchSlop = viewConfiguration.getScaledTouchSlop();
                dragDownHelper.slopMultiplier = viewConfiguration.getScaledAmbiguousGestureMultiplier();
            }
        });
        dumpManager.registerDumpable(this);
        ((StatusBarStateControllerImpl) sysuiStatusBarStateController).addCallback(new StatusBarStateController.StateListener() { // from class: com.android.systemui.statusbar.LockscreenShadeTransitionController.2
            @Override // com.android.systemui.plugins.statusbar.StatusBarStateController.StateListener
            public final void onExpandedChanged(boolean z) {
                ValueAnimator valueAnimator;
                if (z) {
                    return;
                }
                LockscreenShadeTransitionController lockscreenShadeTransitionController = LockscreenShadeTransitionController.this;
                float f = lockscreenShadeTransitionController.dragDownAmount;
                LSShadeTransitionLogger lSShadeTransitionLogger2 = lockscreenShadeTransitionController.logger;
                if (f != 0.0f && ((valueAnimator = lockscreenShadeTransitionController.dragDownAnimator) == null || !valueAnimator.isRunning())) {
                    lSShadeTransitionLogger2.logDragDownAmountResetWhenFullyCollapsed();
                    lockscreenShadeTransitionController.setDragDownAmount$frameworks__base__packages__SystemUI__android_common__SystemUI_core(0.0f);
                }
                if (lockscreenShadeTransitionController.pulseHeight == 0.0f) {
                    return;
                }
                ValueAnimator valueAnimator2 = lockscreenShadeTransitionController.pulseHeightAnimator;
                if (valueAnimator2 == null || !valueAnimator2.isRunning()) {
                    lSShadeTransitionLogger2.logPulseHeightNotResetWhenFullyCollapsed();
                    lockscreenShadeTransitionController.setPulseHeight(0.0f, false);
                }
            }
        });
        wakefulnessLifecycle.mObservers.add(new WakefulnessLifecycle.Observer() { // from class: com.android.systemui.statusbar.LockscreenShadeTransitionController.3
            @Override // com.android.systemui.keyguard.WakefulnessLifecycle.Observer
            public final void onPostFinishedWakingUp() {
                LockscreenShadeTransitionController.this.isWakingToShadeLocked = false;
            }
        });
    }

    /* JADX WARN: Code restructure failed: missing block: B:7:0x0014, code lost:
    
        if (r0.mDynamicPrivacyController.isInLockedDownShade() != false) goto L9;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean canDragDown$frameworks__base__packages__SystemUI__android_common__SystemUI_core() {
        /*
            r2 = this;
            com.android.systemui.statusbar.SysuiStatusBarStateController r0 = r2.statusBarStateController
            com.android.systemui.statusbar.StatusBarStateControllerImpl r0 = (com.android.systemui.statusbar.StatusBarStateControllerImpl) r0
            int r0 = r0.mState
            r1 = 1
            if (r0 == r1) goto L16
            com.android.systemui.statusbar.notification.stack.NotificationStackScrollLayoutController r0 = r2.nsslController
            if (r0 != 0) goto Le
            r0 = 0
        Le:
            com.android.systemui.statusbar.notification.DynamicPrivacyController r0 = r0.mDynamicPrivacyController
            boolean r0 = r0.isInLockedDownShade()
            if (r0 == 0) goto L21
        L16:
            boolean r0 = r2.isQsFullyCollapsed$1()
            if (r0 != 0) goto L22
            boolean r2 = r2.useSplitShade
            if (r2 == 0) goto L21
            goto L22
        L21:
            r1 = 0
        L22:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.statusbar.LockscreenShadeTransitionController.canDragDown$frameworks__base__packages__SystemUI__android_common__SystemUI_core():boolean");
    }

    @Override // com.android.systemui.Dumpable
    public final void dump(PrintWriter printWriter, String[] strArr) {
        IndentingPrintWriter indentingPrintWriter = new IndentingPrintWriter(printWriter, "  ");
        indentingPrintWriter.println("LSShadeTransitionController:");
        indentingPrintWriter.increaseIndent();
        indentingPrintWriter.println("pulseHeight: " + this.pulseHeight);
        indentingPrintWriter.println("useSplitShade: " + this.useSplitShade);
        indentingPrintWriter.println("dragDownAmount: " + this.dragDownAmount);
        indentingPrintWriter.println("isDragDownAnywhereEnabled: " + isDragDownAnywhereEnabled$frameworks__base__packages__SystemUI__android_common__SystemUI_core());
        indentingPrintWriter.println("isFalsingCheckNeeded: " + (((StatusBarStateControllerImpl) this.statusBarStateController).mState == 1));
        indentingPrintWriter.println("isWakingToShadeLocked: " + this.isWakingToShadeLocked);
        indentingPrintWriter.println("hasPendingHandlerOnKeyguardDismiss: " + (this.animationHandlerOnKeyguardDismiss != null));
    }

    public final void finishPulseAnimation(boolean z) {
        this.logger.logPulseExpansionFinished(z);
        if (z) {
            setPulseHeight(0.0f, true);
            return;
        }
        Iterator it = this.callbacks.iterator();
        while (it.hasNext()) {
            QuickSettingsControllerImpl quickSettingsControllerImpl = QuickSettingsControllerImpl.this;
            quickSettingsControllerImpl.mAnimateNextNotificationBounds = true;
            quickSettingsControllerImpl.mNotificationBoundsAnimationDuration = 448L;
            quickSettingsControllerImpl.mNotificationBoundsAnimationDelay = 0L;
            quickSettingsControllerImpl.mIsPulseExpansionResettingAnimator = true;
        }
        setPulseHeight(0.0f, false);
    }

    public final float getFractionToShade() {
        return this.fractionToShade;
    }

    public final void goToLockedShade(View view, boolean z) {
        boolean z2 = ((StatusBarStateControllerImpl) this.statusBarStateController).mState == 1;
        this.logger.logTryGoToLockedShade(z2);
        if (z2) {
            goToLockedShadeInternal(view, (z || this.useSplitShade) ? null : new Function1() { // from class: com.android.systemui.statusbar.LockscreenShadeTransitionController$goToLockedShade$1
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    ((ShadeLockscreenInteractor) LockscreenShadeTransitionController.this.shadeLockscreenInteractorLazy.get()).transitionToExpandedShade(((Number) obj).longValue());
                    return Unit.INSTANCE;
                }
            }, null);
        }
    }

    public final void goToLockedShadeInternal(View view, final Function1 function1, final LockscreenShadeTransitionController$onDraggedDown$cancelRunnable$1 lockscreenShadeTransitionController$onDraggedDown$cancelRunnable$1) {
        NotificationEntry notificationEntry;
        boolean z;
        boolean booleanValue = ((Boolean) ((StateFlowImpl) ((ShadeInteractorImpl) this.shadeInteractor).isShadeEnabled.$$delegate_0).getValue()).booleanValue();
        LSShadeTransitionLogger lSShadeTransitionLogger = this.logger;
        if (!booleanValue) {
            if (lockscreenShadeTransitionController$onDraggedDown$cancelRunnable$1 != null) {
                lockscreenShadeTransitionController$onDraggedDown$cancelRunnable$1.run();
            }
            lSShadeTransitionLogger.logShadeDisabledOnGoToLockedShade();
            return;
        }
        NotificationLockscreenUserManagerImpl notificationLockscreenUserManagerImpl = (NotificationLockscreenUserManagerImpl) this.lockScreenUserManager;
        int i = notificationLockscreenUserManagerImpl.mCurrentUserId;
        if (view instanceof ExpandableNotificationRow) {
            notificationEntry = ((ExpandableNotificationRow) view).mEntry;
            ExpandableNotificationRow expandableNotificationRow = notificationEntry.row;
            if (expandableNotificationRow != null) {
                expandableNotificationRow.setUserExpanded(true, true);
            }
            ExpandableNotificationRow expandableNotificationRow2 = notificationEntry.row;
            if (expandableNotificationRow2 != null) {
                expandableNotificationRow2.mGroupExpansionChanging = true;
            }
            i = notificationEntry.mSbn.getUserId();
        } else {
            notificationEntry = null;
        }
        if (notificationLockscreenUserManagerImpl.mShowLockscreenNotifications) {
            this.falsingCollector.getClass();
            z = false;
        } else {
            z = true;
        }
        if (this.keyguardBypassController.getBypassEnabled()) {
            z = false;
        }
        boolean isLockscreenPublicMode = notificationLockscreenUserManagerImpl.isLockscreenPublicMode(i);
        SysuiStatusBarStateController sysuiStatusBarStateController = this.statusBarStateController;
        if (!isLockscreenPublicMode || !z) {
            lSShadeTransitionLogger.logGoingToLockedShade(function1 != null);
            if (((StatusBarStateControllerImpl) sysuiStatusBarStateController).mIsDozing) {
                this.isWakingToShadeLocked = true;
            }
            sysuiStatusBarStateController.getClass();
            ((StatusBarStateControllerImpl) sysuiStatusBarStateController).setState(2, false);
            if (function1 != null) {
                function1.invoke(0L);
                return;
            } else {
                performDefaultGoToFullShadeAnimation(0L);
                return;
            }
        }
        ((StatusBarStateControllerImpl) sysuiStatusBarStateController).mLeaveOpenOnKeyguardHide = true;
        ActivityStarter.OnDismissAction onDismissAction = function1 != null ? new ActivityStarter.OnDismissAction(function1) { // from class: com.android.systemui.statusbar.LockscreenShadeTransitionController$goToLockedShadeInternal$1
            public final /* synthetic */ Lambda $animationHandler;

            /* JADX WARN: Multi-variable type inference failed */
            {
                this.$animationHandler = (Lambda) function1;
            }

            @Override // com.android.systemui.plugins.ActivityStarter.OnDismissAction
            public final boolean onDismiss() {
                LockscreenShadeTransitionController.this.animationHandlerOnKeyguardDismiss = this.$animationHandler;
                return false;
            }
        } : null;
        Runnable runnable = new Runnable() { // from class: com.android.systemui.statusbar.LockscreenShadeTransitionController$goToLockedShadeInternal$cancelHandler$1
            @Override // java.lang.Runnable
            public final void run() {
                LockscreenShadeTransitionController lockscreenShadeTransitionController = LockscreenShadeTransitionController.this;
                ((StatusBarStateControllerImpl) lockscreenShadeTransitionController.statusBarStateController).mLeaveOpenOnKeyguardHide = false;
                NotificationEntry notificationEntry2 = lockscreenShadeTransitionController.draggedDownEntry;
                if (notificationEntry2 != null) {
                    ExpandableNotificationRow expandableNotificationRow3 = notificationEntry2.row;
                    if (expandableNotificationRow3 != null) {
                        expandableNotificationRow3.setUserLocked(false);
                    }
                    ExpandableNotificationRow expandableNotificationRow4 = notificationEntry2.row;
                    if (expandableNotificationRow4 != null) {
                        expandableNotificationRow4.notifyHeightChanged(false);
                    }
                    lockscreenShadeTransitionController.draggedDownEntry = null;
                }
                LockscreenShadeTransitionController$onDraggedDown$cancelRunnable$1 lockscreenShadeTransitionController$onDraggedDown$cancelRunnable$12 = lockscreenShadeTransitionController$onDraggedDown$cancelRunnable$1;
                if (lockscreenShadeTransitionController$onDraggedDown$cancelRunnable$12 != null) {
                    lockscreenShadeTransitionController$onDraggedDown$cancelRunnable$12.run();
                }
            }
        };
        lSShadeTransitionLogger.logShowBouncerOnGoToLockedShade();
        CentralSurfacesImpl centralSurfacesImpl = this.centralSurfaces;
        if (centralSurfacesImpl == null) {
            centralSurfacesImpl = null;
        }
        int i2 = centralSurfacesImpl.mState;
        if ((i2 == 1 || i2 == 2) && !centralSurfacesImpl.mKeyguardViewMediator.mHiding) {
            centralSurfacesImpl.mStatusBarKeyguardViewManager.dismissWithAction(onDismissAction, runnable, false, null);
        } else {
            runnable.run();
        }
        this.draggedDownEntry = notificationEntry;
    }

    public final boolean isDragDownAnywhereEnabled$frameworks__base__packages__SystemUI__android_common__SystemUI_core() {
        return ((StatusBarStateControllerImpl) this.statusBarStateController).mState == 1 && !this.keyguardBypassController.getBypassEnabled() && (isQsFullyCollapsed$1() || this.useSplitShade);
    }

    public final boolean isDragDownEnabledForView$frameworks__base__packages__SystemUI__android_common__SystemUI_core(ExpandableView expandableView) {
        if (isDragDownAnywhereEnabled$frameworks__base__packages__SystemUI__android_common__SystemUI_core()) {
            return true;
        }
        NotificationStackScrollLayoutController notificationStackScrollLayoutController = this.nsslController;
        if (notificationStackScrollLayoutController == null) {
            notificationStackScrollLayoutController = null;
        }
        if (!notificationStackScrollLayoutController.mDynamicPrivacyController.isInLockedDownShade()) {
            return false;
        }
        if (expandableView == null) {
            return true;
        }
        if (expandableView instanceof ExpandableNotificationRow) {
            return ((Boolean) ((ExpandableNotificationRow) expandableView).mEntry.mSensitive.getValue()).booleanValue();
        }
        return false;
    }

    public final boolean isQsFullyCollapsed$1() {
        QS qs = this.qS;
        if (qs != null) {
            return qs.isFullyCollapsed();
        }
        QSImpl qSImpl = (QSImpl) ((StateFlowImpl) ((QSSceneAdapterImpl) this.lazyQSSceneAdapter.get()).qsImpl.$$delegate_0).getValue();
        if (qSImpl != null) {
            return qSImpl.isFullyCollapsed();
        }
        return true;
    }

    public final void performDefaultGoToFullShadeAnimation(long j) {
        this.logger.logDefaultGoToFullShadeAnimation(j);
        ((ShadeLockscreenInteractor) this.shadeLockscreenInteractorLazy.get()).transitionToExpandedShade(j);
        this.forceApplyAmount = true;
        setDragDownAmount$frameworks__base__packages__SystemUI__android_common__SystemUI_core(1.0f);
        setDragDownAmountAnimated(this.fullTransitionDistanceByTap, j, new Function0() { // from class: com.android.systemui.statusbar.LockscreenShadeTransitionController$animateAppear$1
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                LockscreenShadeTransitionController.this.logger.logDragDownAmountReset();
                LockscreenShadeTransitionController.this.setDragDownAmount$frameworks__base__packages__SystemUI__android_common__SystemUI_core(0.0f);
                LockscreenShadeTransitionController.this.forceApplyAmount = false;
                return Unit.INSTANCE;
            }
        });
    }

    public final void setDragDownAmount$frameworks__base__packages__SystemUI__android_common__SystemUI_core(float f) {
        if (this.dragDownAmount != f || this.forceApplyAmount) {
            this.dragDownAmount = f;
            NotificationStackScrollLayoutController notificationStackScrollLayoutController = this.nsslController;
            if (notificationStackScrollLayoutController == null) {
                notificationStackScrollLayoutController = null;
            }
            if (!notificationStackScrollLayoutController.mDynamicPrivacyController.isInLockedDownShade() || this.dragDownAmount == 0.0f || this.forceApplyAmount) {
                float saturate = MathUtils.saturate(this.dragDownAmount / this.notificationShelfTransitionDistance);
                this.fractionToShade = saturate;
                StateFlowImpl stateFlowImpl = ((ShadeRepositoryImpl) this.shadeRepository)._lockscreenShadeExpansion;
                Float valueOf = Float.valueOf(saturate);
                stateFlowImpl.getClass();
                stateFlowImpl.updateState(null, valueOf);
                NotificationStackScrollLayoutController notificationStackScrollLayoutController2 = this.nsslController;
                NotificationStackScrollLayoutController notificationStackScrollLayoutController3 = notificationStackScrollLayoutController2 != null ? notificationStackScrollLayoutController2 : null;
                float f2 = this.fractionToShade;
                NotificationStackScrollLayout notificationStackScrollLayout = notificationStackScrollLayoutController3.mView;
                notificationStackScrollLayout.mAmbientState.mFractionToShade = f2;
                notificationStackScrollLayout.updateContentHeight();
                notificationStackScrollLayout.requestChildrenUpdate();
                LockscreenShadeQsTransitionController lockscreenShadeQsTransitionController = this.qsTransitionController;
                if (f != lockscreenShadeQsTransitionController.dragDownAmount) {
                    lockscreenShadeQsTransitionController.dragDownAmount = f;
                    float f3 = f - lockscreenShadeQsTransitionController.qsTransitionStartDelay;
                    lockscreenShadeQsTransitionController.qsDragDownAmount = f3;
                    lockscreenShadeQsTransitionController.qsTransitionFraction = MathUtils.saturate(f3 / lockscreenShadeQsTransitionController.qsTransitionDistance);
                    lockscreenShadeQsTransitionController.qsSquishTransitionFraction = MathUtils.lerp(lockscreenShadeQsTransitionController.qsSquishStartFraction, 1.0f, MathUtils.saturate(lockscreenShadeQsTransitionController.qsDragDownAmount / lockscreenShadeQsTransitionController.qsSquishTransitionDistance));
                    lockscreenShadeQsTransitionController.isTransitioningToFullShade = f > 0.0f;
                    QS qs = (QS) lockscreenShadeQsTransitionController.qsProvider.invoke();
                    if (qs != null) {
                        qs.setTransitionToFullShadeProgress(lockscreenShadeQsTransitionController.isTransitioningToFullShade, lockscreenShadeQsTransitionController.qsTransitionFraction, lockscreenShadeQsTransitionController.qsSquishTransitionFraction);
                    }
                }
                Iterator it = this.callbacks.iterator();
                while (it.hasNext()) {
                    ((QuickSettingsControllerImpl.LockscreenShadeTransitionCallback) it.next()).setTransitionToFullShadeAmount(this.dragDownAmount, 0L, false);
                }
                this.mediaHierarchyManager.setTransitionToFullShadeAmount(this.dragDownAmount);
                LockscreenShadeScrimTransitionController lockscreenShadeScrimTransitionController = this.scrimTransitionController;
                if (f != lockscreenShadeScrimTransitionController.dragDownAmount) {
                    lockscreenShadeScrimTransitionController.dragDownAmount = f;
                    lockscreenShadeScrimTransitionController.scrimProgress = MathUtils.saturate(f / lockscreenShadeScrimTransitionController.scrimTransitionDistance);
                    float f4 = f - lockscreenShadeScrimTransitionController.notificationsScrimTransitionDelay;
                    lockscreenShadeScrimTransitionController.notificationsScrimDragAmount = f4;
                    float saturate2 = MathUtils.saturate(f4 / lockscreenShadeScrimTransitionController.notificationsScrimTransitionDistance);
                    lockscreenShadeScrimTransitionController.notificationsScrimProgress = saturate2;
                    float f5 = lockscreenShadeScrimTransitionController.scrimProgress;
                    ScrimController scrimController = lockscreenShadeScrimTransitionController.scrimController;
                    if (f5 != scrimController.mTransitionToFullShadeProgress || saturate2 != scrimController.mTransitionToLockScreenFullShadeNotificationsProgress) {
                        scrimController.mTransitionToFullShadeProgress = f5;
                        scrimController.mTransitionToLockScreenFullShadeNotificationsProgress = saturate2;
                        boolean z = f5 > 0.0f || saturate2 > 0.0f;
                        if (z != scrimController.mTransitioningToFullShade) {
                            scrimController.mTransitioningToFullShade = z;
                        }
                        scrimController.applyAndDispatchState();
                    }
                }
                transitionToShadeAmountCommon(this.dragDownAmount);
                LockscreenShadeKeyguardTransitionController lockscreenShadeKeyguardTransitionController = (LockscreenShadeKeyguardTransitionController) this.keyguardTransitionController$delegate.getValue();
                if (f != lockscreenShadeKeyguardTransitionController.dragDownAmount) {
                    lockscreenShadeKeyguardTransitionController.dragDownAmount = f;
                    float saturate3 = MathUtils.saturate(f / lockscreenShadeKeyguardTransitionController.alphaTransitionDistance);
                    lockscreenShadeKeyguardTransitionController.alphaProgress = saturate3;
                    lockscreenShadeKeyguardTransitionController.alpha = 1.0f - saturate3;
                    int i = 0;
                    if (lockscreenShadeKeyguardTransitionController.useSplitShade) {
                        MediaHierarchyManager mediaHierarchyManager = lockscreenShadeKeyguardTransitionController.mediaHierarchyManager;
                        if (!mediaHierarchyManager.isCurrentlyInGuidedTransformation()) {
                            float saturate4 = MathUtils.saturate(f / lockscreenShadeKeyguardTransitionController.keyguardTransitionDistance);
                            lockscreenShadeKeyguardTransitionController.translationYProgress = saturate4;
                            i = (int) (saturate4 * lockscreenShadeKeyguardTransitionController.keyguardTransitionOffset);
                        } else if (mediaHierarchyManager.isCurrentlyInGuidedTransformation()) {
                            MediaHost host = mediaHierarchyManager.getHost(mediaHierarchyManager.previousLocation);
                            if (host != null && host.state.visible) {
                                i = mediaHierarchyManager.targetBounds.top - host.getCurrentBounds().top;
                            }
                        } else {
                            i = -1;
                        }
                    }
                    lockscreenShadeKeyguardTransitionController.translationY = i;
                    float f6 = lockscreenShadeKeyguardTransitionController.alpha;
                    ShadeLockscreenInteractor shadeLockscreenInteractor = lockscreenShadeKeyguardTransitionController.shadeLockscreenInteractor;
                    shadeLockscreenInteractor.setKeyguardTransitionProgress(i, f6);
                    float f7 = lockscreenShadeKeyguardTransitionController.useSplitShade ? lockscreenShadeKeyguardTransitionController.alpha : -1.0f;
                    lockscreenShadeKeyguardTransitionController.statusBarAlpha = f7;
                    shadeLockscreenInteractor.setKeyguardStatusBarAlpha(f7);
                }
                (this.useSplitShade ? (SplitShadeLockScreenOverScroller) this.splitShadeOverScroller$delegate.getValue() : (SingleShadeLockScreenOverScroller) this.phoneShadeOverScroller$delegate.getValue()).setExpansionDragDownAmount(this.dragDownAmount);
            }
        }
    }

    public final void setDragDownAmountAnimated(float f, long j, final Function0 function0) {
        this.logger.logDragDownAnimation(f);
        ValueAnimator ofFloat = ValueAnimator.ofFloat(this.dragDownAmount, f);
        ofFloat.setInterpolator(Interpolators.FAST_OUT_SLOW_IN);
        ofFloat.setDuration(375L);
        ofFloat.addUpdateListener(new LockscreenShadeTransitionController$setPulseHeight$1(this, 1));
        if (j > 0) {
            ofFloat.setStartDelay(j);
        }
        if (function0 != null) {
            ofFloat.addListener(new AnimatorListenerAdapter() { // from class: com.android.systemui.statusbar.LockscreenShadeTransitionController$setDragDownAmountAnimated$2
                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                public final void onAnimationEnd(Animator animator) {
                    Function0.this.invoke();
                }
            });
        }
        ofFloat.start();
        this.dragDownAnimator = ofFloat;
    }

    public final void setPulseHeight(float f, boolean z) {
        if (z) {
            ValueAnimator ofFloat = ValueAnimator.ofFloat(this.pulseHeight, f);
            ofFloat.setInterpolator(Interpolators.FAST_OUT_SLOW_IN);
            ofFloat.setDuration(375L);
            ofFloat.addUpdateListener(new LockscreenShadeTransitionController$setPulseHeight$1(this, 0));
            ofFloat.start();
            this.pulseHeightAnimator = ofFloat;
            return;
        }
        this.pulseHeight = f;
        NotificationStackScrollLayoutController notificationStackScrollLayoutController = this.nsslController;
        if (notificationStackScrollLayoutController == null) {
            notificationStackScrollLayoutController = null;
        }
        ((ShadeLockscreenInteractor) this.shadeLockscreenInteractorLazy.get()).setOverStretchAmount(notificationStackScrollLayoutController.mView.setPulseHeight(f));
        if (!this.keyguardBypassController.getBypassEnabled()) {
            f = 0.0f;
        }
        transitionToShadeAmountCommon(f);
    }

    public final void transitionToShadeAmountCommon(float f) {
        int i = this.depthControllerTransitionDistance;
        NotificationShadeDepthController notificationShadeDepthController = this.depthController;
        if (i != 0) {
            float saturate = MathUtils.saturate(f / i);
            if (notificationShadeDepthController.transitionToFullShadeProgress != saturate) {
                notificationShadeDepthController.transitionToFullShadeProgress = saturate;
                notificationShadeDepthController.scheduleUpdate();
            }
        } else if (notificationShadeDepthController.transitionToFullShadeProgress != 0.0f) {
            notificationShadeDepthController.transitionToFullShadeProgress = 0.0f;
            notificationShadeDepthController.scheduleUpdate();
        }
        float saturate2 = MathUtils.saturate(f / this.udfpsTransitionDistance);
        StateFlowImpl stateFlowImpl = ((ShadeRepositoryImpl) this.shadeRepository)._udfpsTransitionToFullShadeProgress;
        Float valueOf = Float.valueOf(saturate2);
        stateFlowImpl.getClass();
        stateFlowImpl.updateState(null, valueOf);
        float saturate3 = MathUtils.saturate(f / this.statusBarTransitionDistance);
        CentralSurfacesImpl centralSurfacesImpl = this.centralSurfaces;
        (centralSurfacesImpl != null ? centralSurfacesImpl : null).mTransitionToFullShadeProgress = saturate3;
    }

    public final void updateResources$8() {
        this.fullTransitionDistance = this.context.getResources().getDimensionPixelSize(R.dimen.lockscreen_shade_full_transition_distance);
        this.fullTransitionDistanceByTap = this.context.getResources().getDimensionPixelSize(R.dimen.lockscreen_shade_transition_by_tap_distance);
        this.notificationShelfTransitionDistance = this.context.getResources().getDimensionPixelSize(R.dimen.lockscreen_shade_notif_shelf_transition_distance);
        this.depthControllerTransitionDistance = this.context.getResources().getDimensionPixelSize(R.dimen.lockscreen_shade_depth_controller_transition_distance);
        this.udfpsTransitionDistance = this.context.getResources().getDimensionPixelSize(R.dimen.lockscreen_shade_udfps_keyguard_transition_distance);
        this.statusBarTransitionDistance = this.context.getResources().getDimensionPixelSize(R.dimen.lockscreen_shade_status_bar_transition_distance);
        this.useSplitShade = this.splitShadeStateController.shouldUseSplitNotificationShade(this.context.getResources());
    }

    public static /* synthetic */ void getDragDownAnimator$frameworks__base__packages__SystemUI__android_common__SystemUI_core$annotations() {
    }

    public static /* synthetic */ void getPulseHeightAnimator$frameworks__base__packages__SystemUI__android_common__SystemUI_core$annotations() {
    }
}
