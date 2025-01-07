package com.android.systemui.unfold;

import android.content.Context;
import android.hardware.devicestate.DeviceStateManager;
import android.provider.Settings;
import com.android.internal.util.LatencyTracker;
import com.android.systemui.keyguard.WakefulnessLifecycle;
import com.android.systemui.keyguard.domain.interactor.ToAodFoldTransitionInteractor;
import com.android.systemui.shade.NotificationPanelView;
import com.android.systemui.shade.NotificationPanelViewController;
import com.android.systemui.shade.ShadeFoldAnimator;
import com.android.systemui.shade.ShadeViewController;
import com.android.systemui.statusbar.LightRevealScrim;
import com.android.systemui.statusbar.phone.CentralSurfaces;
import com.android.systemui.statusbar.phone.ScreenOffAnimation;
import com.android.systemui.statusbar.policy.CallbackController;
import com.android.systemui.util.concurrency.DelayableExecutor;
import com.android.systemui.util.concurrency.ExecutorImpl;
import com.android.systemui.util.concurrency.PendingTasksContainer$registerTask$1;
import com.android.systemui.util.settings.GlobalSettings;
import com.android.systemui.util.settings.GlobalSettingsImpl;
import com.android.wm.shell.R;
import dagger.Lazy;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.function.Consumer;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class FoldAodAnimationController implements CallbackController, ScreenOffAnimation, WakefulnessLifecycle.Observer {
    public boolean alwaysOnEnabled;
    public ExecutorImpl.ExecutionToken cancelAnimation;
    public final Context context;
    public final DeviceStateManager deviceStateManager;
    public final Lazy foldTransitionInteractor;
    public final GlobalSettings globalSettings;
    public boolean isAnimationPlaying;
    public boolean isFolded;
    public boolean isScrimOpaque;
    public final Lazy keyguardInteractor;
    public final LatencyTracker latencyTracker;
    public final DelayableExecutor mainExecutor;
    public PendingTasksContainer$registerTask$1 pendingScrimReadyCallback;
    public boolean shouldPlayAnimation;
    public final WakefulnessLifecycle wakefulnessLifecycle;
    public boolean isFoldHandled = true;
    public final ArrayList statusListeners = new ArrayList();
    public final FoldToAodLatencyTracker foldToAodLatencyTracker = new FoldToAodLatencyTracker();
    public final FoldAodAnimationController$onScreenTurnedOn$1 startAnimationRunnable = new FoldAodAnimationController$onScreenTurnedOn$1(this, 4);

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public interface FoldAodAnimationStatus {
        void onFoldToAodAnimationChanged();
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class FoldListener extends DeviceStateManager.FoldStateListener {
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class FoldToAodLatencyTracker {
        public FoldToAodLatencyTracker() {
        }
    }

    public FoldAodAnimationController(DelayableExecutor delayableExecutor, Context context, DeviceStateManager deviceStateManager, WakefulnessLifecycle wakefulnessLifecycle, GlobalSettings globalSettings, LatencyTracker latencyTracker, Lazy lazy, Lazy lazy2) {
        this.mainExecutor = delayableExecutor;
        this.context = context;
        this.deviceStateManager = deviceStateManager;
        this.wakefulnessLifecycle = wakefulnessLifecycle;
        this.globalSettings = globalSettings;
        this.latencyTracker = latencyTracker;
        this.keyguardInteractor = lazy;
        this.foldTransitionInteractor = lazy2;
    }

    @Override // com.android.systemui.statusbar.policy.CallbackController
    public final void addCallback(Object obj) {
        this.statusListeners.add((FoldAodAnimationStatus) obj);
    }

    @Override // com.android.systemui.statusbar.phone.ScreenOffAnimation
    public final void initialize(CentralSurfaces centralSurfaces, ShadeViewController shadeViewController, LightRevealScrim lightRevealScrim) {
        ToAodFoldTransitionInteractor toAodFoldTransitionInteractor = (ToAodFoldTransitionInteractor) this.foldTransitionInteractor.get();
        ShadeFoldAnimator shadeFoldAnimator$1 = shadeViewController.getShadeFoldAnimator$1();
        toAodFoldTransitionInteractor.getClass();
        toAodFoldTransitionInteractor.parentAnimator = shadeFoldAnimator$1 instanceof NotificationPanelViewController.ShadeFoldAnimatorImpl ? (NotificationPanelViewController.ShadeFoldAnimatorImpl) shadeFoldAnimator$1 : null;
        this.deviceStateManager.registerCallback(this.mainExecutor, new FoldListener(this.context, new Consumer() { // from class: com.android.systemui.unfold.FoldAodAnimationController.FoldListener.1
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                Boolean bool = (Boolean) obj;
                if (!bool.booleanValue()) {
                    FoldAodAnimationController.this.isFoldHandled = false;
                }
                FoldAodAnimationController.this.isFolded = bool.booleanValue();
                if (bool.booleanValue()) {
                    FoldAodAnimationController foldAodAnimationController = FoldAodAnimationController.this;
                    if (foldAodAnimationController.shouldStartAnimation()) {
                        foldAodAnimationController.latencyTracker.onActionStart(18);
                    }
                }
            }
        }));
        this.wakefulnessLifecycle.mObservers.add(this);
    }

    @Override // com.android.systemui.statusbar.phone.ScreenOffAnimation
    public final boolean isAnimationPlaying() {
        return this.isAnimationPlaying;
    }

    @Override // com.android.systemui.statusbar.phone.ScreenOffAnimation
    public final boolean isKeyguardHideDelayed() {
        return this.isAnimationPlaying;
    }

    @Override // com.android.systemui.statusbar.phone.ScreenOffAnimation
    public final void onAlwaysOnChanged(boolean z) {
        this.alwaysOnEnabled = z;
    }

    @Override // com.android.systemui.statusbar.phone.ScreenOffAnimation
    public final void onScrimOpaqueChanged(boolean z) {
        this.isScrimOpaque = z;
        if (z) {
            PendingTasksContainer$registerTask$1 pendingTasksContainer$registerTask$1 = this.pendingScrimReadyCallback;
            if (pendingTasksContainer$registerTask$1 != null) {
                pendingTasksContainer$registerTask$1.run();
            }
            this.pendingScrimReadyCallback = null;
        }
    }

    @Override // com.android.systemui.keyguard.WakefulnessLifecycle.Observer
    public final void onStartedWakingUp() {
        if (this.isAnimationPlaying) {
            FoldAodAnimationController.this.latencyTracker.onActionCancel(18);
            ExecutorImpl.ExecutionToken executionToken = this.cancelAnimation;
            if (executionToken != null) {
                executionToken.run();
            }
            NotificationPanelViewController.ShadeFoldAnimatorImpl shadeFoldAnimatorImpl = ((ToAodFoldTransitionInteractor) this.foldTransitionInteractor.get()).foldAnimator.this$0.parentAnimator;
            if (shadeFoldAnimatorImpl != null) {
                NotificationPanelViewController notificationPanelViewController = NotificationPanelViewController.this;
                notificationPanelViewController.cancelAnimation();
                notificationPanelViewController.resetAlpha();
                notificationPanelViewController.resetTranslation();
            }
        }
        setAnimationState(false);
    }

    @Override // com.android.systemui.statusbar.policy.CallbackController
    public final void removeCallback(Object obj) {
        this.statusListeners.remove((FoldAodAnimationStatus) obj);
    }

    public final void setAnimationState(boolean z) {
        this.shouldPlayAnimation = z;
        this.isAnimationPlaying = z;
        Iterator it = this.statusListeners.iterator();
        while (it.hasNext()) {
            ((FoldAodAnimationStatus) it.next()).onFoldToAodAnimationChanged();
        }
    }

    @Override // com.android.systemui.statusbar.phone.ScreenOffAnimation
    public final boolean shouldAnimateClockChange() {
        return !this.isAnimationPlaying;
    }

    @Override // com.android.systemui.statusbar.phone.ScreenOffAnimation
    public final boolean shouldAnimateDozingChange() {
        return !this.shouldPlayAnimation;
    }

    @Override // com.android.systemui.statusbar.phone.ScreenOffAnimation
    public final boolean shouldDelayDisplayDozeTransition() {
        return this.shouldPlayAnimation;
    }

    @Override // com.android.systemui.statusbar.phone.ScreenOffAnimation
    public final boolean shouldPlayAnimation() {
        return this.shouldPlayAnimation;
    }

    @Override // com.android.systemui.statusbar.phone.ScreenOffAnimation
    public final boolean shouldShowAodIconsWhenShade() {
        return this.shouldPlayAnimation;
    }

    public final boolean shouldStartAnimation() {
        return this.alwaysOnEnabled && this.wakefulnessLifecycle.mLastSleepReason == 13 && !Intrinsics.areEqual(Settings.Global.getString(((GlobalSettingsImpl) this.globalSettings).mContentResolver, "animator_duration_scale"), "0");
    }

    @Override // com.android.systemui.statusbar.phone.ScreenOffAnimation
    public final boolean startAnimation() {
        if (!shouldStartAnimation()) {
            setAnimationState(false);
            return false;
        }
        setAnimationState(true);
        NotificationPanelViewController.ShadeFoldAnimatorImpl shadeFoldAnimatorImpl = ((ToAodFoldTransitionInteractor) this.foldTransitionInteractor.get()).foldAnimator.this$0.parentAnimator;
        if (shadeFoldAnimatorImpl != null) {
            NotificationPanelViewController notificationPanelViewController = NotificationPanelViewController.this;
            notificationPanelViewController.setDozing(true, false);
            NotificationPanelView notificationPanelView = notificationPanelViewController.mView;
            notificationPanelView.setTranslationX(-notificationPanelView.getResources().getDimensionPixelSize(R.dimen.below_clock_padding_start));
            notificationPanelView.setAlpha(0.0f);
        }
        return true;
    }
}
