package com.android.systemui.navigationbar.gestural;

import android.R;
import android.content.res.Resources;
import android.graphics.Point;
import android.os.Handler;
import android.util.MathUtils;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.WindowManager;
import androidx.core.animation.Interpolator;
import androidx.core.animation.PathInterpolator;
import androidx.dynamicanimation.animation.DynamicAnimation;
import androidx.dynamicanimation.animation.SpringAnimation;
import androidx.dynamicanimation.animation.SpringForce;
import com.android.app.viewcapture.ViewCaptureAwareWindowManager;
import com.android.internal.jank.InteractionJankMonitor;
import com.android.internal.util.LatencyTracker;
import com.android.keyguard.ActiveUnlockConfig$$ExternalSyntheticOutline0;
import com.android.settingslib.Utils;
import com.android.systemui.navigationbar.gestural.BackPanel;
import com.android.systemui.navigationbar.gestural.EdgePanelParams;
import com.android.systemui.navigationbar.gestural.Step;
import com.android.systemui.plugins.NavigationEdgeBackPlugin;
import com.android.systemui.statusbar.VibratorHelper;
import com.android.systemui.statusbar.phone.ConfigurationControllerImpl;
import com.android.systemui.statusbar.policy.ConfigurationController;
import com.android.systemui.util.ViewController;
import com.android.systemui.util.concurrency.UiThreadContext;
import com.android.systemui.util.time.SystemClock;
import com.android.systemui.util.time.SystemClockImpl;
import java.io.PrintWriter;
import java.util.Iterator;
import kotlin.NoWhenBranchMatchedException;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.ClosedFloatRange;
import kotlin.ranges.RangesKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class BackPanelController extends ViewController implements NavigationEdgeBackPlugin {
    public NavigationEdgeBackPlugin.BackCallback backCallback;
    public final ConfigurationController configurationController;
    public final BackPanelController$configurationListener$1 configurationListener;
    public GestureState currentState;
    public final Point displaySize;
    public float entryToActiveDelay;
    public final Function0 entryToActiveDelayCalculation;
    public final BackPanelController$failsafeRunnable$1 failsafeRunnable;
    public float fullyStretchedThreshold;
    public long gestureEntryTime;
    public long gestureInactiveTime;
    public boolean hasPassedDragSlop;
    public final InteractionJankMonitor interactionJankMonitor;
    public WindowManager.LayoutParams layoutParams;
    public final Handler mainHandler;
    public int minFlingDistance;
    public final DelayedOnAnimationEndListener onAlphaEndSetGoneStateListener;
    public final DelayedOnAnimationEndListener onEndSetCommittedStateListener;
    public final DelayedOnAnimationEndListener onEndSetGoneStateListener;
    public final EdgePanelParams params;
    public long pastThresholdWhileEntryOrInactiveTime;
    public Interpolator previousPreThresholdWidthInterpolator;
    public GestureState previousState;
    public float previousXTranslation;
    public float previousXTranslationOnActiveOffset;
    public float startX;
    public float startY;
    public final SystemClock systemClock;
    public float totalTouchDeltaActive;
    public float totalTouchDeltaInactive;
    public float touchDeltaStartX;
    public VelocityTracker velocityTracker;
    public final VibratorHelper vibratorHelper;
    public final ViewConfiguration viewConfiguration;
    public final ViewCaptureAwareWindowManager windowManager;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class DelayedOnAnimationEndListener implements DynamicAnimation.OnAnimationEndListener {
        public final Handler handler;
        public final Runnable runnable;

        public DelayedOnAnimationEndListener(Handler handler, Runnable runnable) {
            this.handler = handler;
            this.runnable = runnable;
        }

        @Override // androidx.dynamicanimation.animation.DynamicAnimation.OnAnimationEndListener
        public final void onAnimationEnd(DynamicAnimation dynamicAnimation, boolean z, float f, float f2) {
            dynamicAnimation.removeEndListener(this);
            if (z) {
                return;
            }
            long max = Math.max(0L, 0 - BackPanelController.this.getElapsedTimeSinceEntry());
            this.handler.postDelayed(this.runnable, max);
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Factory {
        public final ConfigurationController configurationController;
        public final InteractionJankMonitor interactionJankMonitor;
        public final LatencyTracker latencyTracker;
        public final SystemClock systemClock;
        public final UiThreadContext uiThreadContext;
        public final VibratorHelper vibratorHelper;
        public final ViewConfiguration viewConfiguration;
        public final ViewCaptureAwareWindowManager windowManager;

        public Factory(ViewCaptureAwareWindowManager viewCaptureAwareWindowManager, ViewConfiguration viewConfiguration, UiThreadContext uiThreadContext, SystemClock systemClock, VibratorHelper vibratorHelper, ConfigurationController configurationController, LatencyTracker latencyTracker, InteractionJankMonitor interactionJankMonitor) {
            this.windowManager = viewCaptureAwareWindowManager;
            this.viewConfiguration = viewConfiguration;
            this.uiThreadContext = uiThreadContext;
            this.systemClock = systemClock;
            this.vibratorHelper = vibratorHelper;
            this.configurationController = configurationController;
            this.latencyTracker = latencyTracker;
            this.interactionJankMonitor = interactionJankMonitor;
        }
    }

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class GestureState {
        public static final /* synthetic */ GestureState[] $VALUES;
        public static final GestureState ACTIVE;
        public static final GestureState CANCELLED;
        public static final GestureState COMMITTED;
        public static final GestureState ENTRY;
        public static final GestureState FLUNG;
        public static final GestureState GONE;
        public static final GestureState INACTIVE;

        static {
            GestureState gestureState = new GestureState("GONE", 0);
            GONE = gestureState;
            GestureState gestureState2 = new GestureState("ENTRY", 1);
            ENTRY = gestureState2;
            GestureState gestureState3 = new GestureState("ACTIVE", 2);
            ACTIVE = gestureState3;
            GestureState gestureState4 = new GestureState("INACTIVE", 3);
            INACTIVE = gestureState4;
            GestureState gestureState5 = new GestureState("FLUNG", 4);
            FLUNG = gestureState5;
            GestureState gestureState6 = new GestureState("COMMITTED", 5);
            COMMITTED = gestureState6;
            GestureState gestureState7 = new GestureState("CANCELLED", 6);
            CANCELLED = gestureState7;
            GestureState[] gestureStateArr = {gestureState, gestureState2, gestureState3, gestureState4, gestureState5, gestureState6, gestureState7};
            $VALUES = gestureStateArr;
            EnumEntriesKt.enumEntries(gestureStateArr);
        }

        public static GestureState valueOf(String str) {
            return (GestureState) Enum.valueOf(GestureState.class, str);
        }

        public static GestureState[] values() {
            return (GestureState[]) $VALUES.clone();
        }
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /* JADX WARN: Type inference failed for: r3v9, types: [com.android.systemui.navigationbar.gestural.BackPanelController$configurationListener$1] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public BackPanelController(android.content.Context r2, com.android.app.viewcapture.ViewCaptureAwareWindowManager r3, android.view.ViewConfiguration r4, android.os.Handler r5, com.android.systemui.util.time.SystemClock r6, com.android.systemui.statusbar.VibratorHelper r7, com.android.systemui.statusbar.policy.ConfigurationController r8, com.android.internal.util.LatencyTracker r9, com.android.internal.jank.InteractionJankMonitor r10) {
        /*
            r1 = this;
            com.android.systemui.navigationbar.gestural.BackPanel r0 = new com.android.systemui.navigationbar.gestural.BackPanel
            r0.<init>(r2, r9)
            r1.<init>(r0)
            r1.windowManager = r3
            r1.viewConfiguration = r4
            r1.mainHandler = r5
            r1.systemClock = r6
            r1.vibratorHelper = r7
            r1.configurationController = r8
            r1.interactionJankMonitor = r10
            com.android.systemui.navigationbar.gestural.EdgePanelParams r2 = new com.android.systemui.navigationbar.gestural.EdgePanelParams
            android.content.res.Resources r3 = r0.getResources()
            r2.<init>()
            r2.resources = r3
            r2.update(r3)
            r1.params = r2
            com.android.systemui.navigationbar.gestural.BackPanelController$GestureState r3 = com.android.systemui.navigationbar.gestural.BackPanelController.GestureState.GONE
            r1.currentState = r3
            r1.previousState = r3
            android.graphics.Point r3 = new android.graphics.Point
            r3.<init>()
            r1.displaySize = r3
            com.android.systemui.navigationbar.gestural.BackPanelController$entryToActiveDelayCalculation$1 r3 = new com.android.systemui.navigationbar.gestural.BackPanelController$entryToActiveDelayCalculation$1
            r3.<init>()
            r1.entryToActiveDelayCalculation = r3
            com.android.systemui.navigationbar.gestural.BackPanelController$failsafeRunnable$1 r3 = new com.android.systemui.navigationbar.gestural.BackPanelController$failsafeRunnable$1
            r4 = 0
            r3.<init>(r1, r4)
            r1.failsafeRunnable = r3
            com.android.systemui.navigationbar.gestural.BackPanelController$DelayedOnAnimationEndListener r3 = new com.android.systemui.navigationbar.gestural.BackPanelController$DelayedOnAnimationEndListener
            com.android.systemui.navigationbar.gestural.BackPanelController$failsafeRunnable$1 r4 = new com.android.systemui.navigationbar.gestural.BackPanelController$failsafeRunnable$1
            r6 = 2
            r4.<init>(r1, r6)
            r3.<init>(r5, r4)
            r1.onEndSetCommittedStateListener = r3
            com.android.systemui.navigationbar.gestural.BackPanelController$DelayedOnAnimationEndListener r3 = new com.android.systemui.navigationbar.gestural.BackPanelController$DelayedOnAnimationEndListener
            com.android.systemui.navigationbar.gestural.BackPanelController$failsafeRunnable$1 r4 = new com.android.systemui.navigationbar.gestural.BackPanelController$failsafeRunnable$1
            r6 = 3
            r4.<init>(r1, r6)
            r3.<init>(r5, r4)
            r1.onEndSetGoneStateListener = r3
            com.android.systemui.navigationbar.gestural.BackPanelController$DelayedOnAnimationEndListener r3 = new com.android.systemui.navigationbar.gestural.BackPanelController$DelayedOnAnimationEndListener
            com.android.systemui.navigationbar.gestural.BackPanelController$failsafeRunnable$1 r4 = new com.android.systemui.navigationbar.gestural.BackPanelController$failsafeRunnable$1
            r6 = 1
            r4.<init>(r1, r6)
            r3.<init>(r5, r4)
            r1.onAlphaEndSetGoneStateListener = r3
            com.android.systemui.navigationbar.gestural.BackPanelController$configurationListener$1 r3 = new com.android.systemui.navigationbar.gestural.BackPanelController$configurationListener$1
            r3.<init>()
            r1.configurationListener = r3
            androidx.core.animation.PathInterpolator r2 = r2.entryWidthInterpolator
            if (r2 == 0) goto L75
            goto L76
        L75:
            r2 = 0
        L76:
            r1.previousPreThresholdWidthInterpolator = r2
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.navigationbar.gestural.BackPanelController.<init>(android.content.Context, com.android.app.viewcapture.ViewCaptureAwareWindowManager, android.view.ViewConfiguration, android.os.Handler, com.android.systemui.util.time.SystemClock, com.android.systemui.statusbar.VibratorHelper, com.android.systemui.statusbar.policy.ConfigurationController, com.android.internal.util.LatencyTracker, com.android.internal.jank.InteractionJankMonitor):void");
    }

    public static boolean isFlungAwayFromEdge$default(BackPanelController backPanelController, float f) {
        float f2;
        float f3 = backPanelController.touchDeltaStartX;
        float f4 = ((BackPanel) backPanelController.mView).isLeftPanel ? f - f3 : f3 - f;
        if (backPanelController.velocityTracker == null) {
            backPanelController.velocityTracker = VelocityTracker.obtain();
        }
        VelocityTracker velocityTracker = backPanelController.velocityTracker;
        if (velocityTracker != null) {
            velocityTracker.computeCurrentVelocity(1000);
            Float valueOf = Float.valueOf(velocityTracker.getXVelocity());
            if (!((BackPanel) backPanelController.mView).isLeftPanel) {
                valueOf = null;
            }
            f2 = valueOf != null ? valueOf.floatValue() : velocityTracker.getXVelocity() * (-1);
        } else {
            f2 = 0.0f;
        }
        return f4 > ((float) backPanelController.minFlingDistance) && ((f2 > ((float) backPanelController.viewConfiguration.getScaledMinimumFlingVelocity()) ? 1 : (f2 == ((float) backPanelController.viewConfiguration.getScaledMinimumFlingVelocity()) ? 0 : -1)) > 0);
    }

    public static boolean isPastThresholdToActive$default(BackPanelController backPanelController, boolean z, Function0 function0, int i) {
        final Float valueOf = Float.valueOf(160.0f);
        if ((i & 2) != 0) {
            valueOf = null;
        }
        if ((i & 4) != 0) {
            function0 = new Function0() { // from class: com.android.systemui.navigationbar.gestural.BackPanelController$isPastThresholdToActive$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    Float f = valueOf;
                    return Float.valueOf(f != null ? f.floatValue() : 0.0f);
                }
            };
        }
        boolean z2 = backPanelController.pastThresholdWhileEntryOrInactiveTime == 0;
        if (!z) {
            backPanelController.pastThresholdWhileEntryOrInactiveTime = 0L;
            return false;
        }
        SystemClock systemClock = backPanelController.systemClock;
        if (z2) {
            ((SystemClockImpl) systemClock).getClass();
            backPanelController.pastThresholdWhileEntryOrInactiveTime = android.os.SystemClock.uptimeMillis();
            backPanelController.entryToActiveDelay = ((Number) function0.invoke()).floatValue();
        }
        ((SystemClockImpl) systemClock).getClass();
        return ((float) (android.os.SystemClock.uptimeMillis() - backPanelController.pastThresholdWhileEntryOrInactiveTime)) > backPanelController.entryToActiveDelay;
    }

    @Override // com.android.systemui.plugins.NavigationEdgeBackPlugin
    public final void dump(PrintWriter printWriter) {
        printWriter.println("BackPanelController:");
        printWriter.println("  currentState=" + this.currentState);
        ActiveUnlockConfig$$ExternalSyntheticOutline0.m("  isLeftPanel=", ((BackPanel) this.mView).isLeftPanel, printWriter);
    }

    public final BackPanel getBackPanelView$frameworks__base__packages__SystemUI__android_common__SystemUI_core() {
        return (BackPanel) this.mView;
    }

    public final long getElapsedTimeSinceEntry() {
        ((SystemClockImpl) this.systemClock).getClass();
        return android.os.SystemClock.uptimeMillis() - this.gestureEntryTime;
    }

    @Override // com.android.systemui.plugins.Plugin
    public final void onDestroy() {
        this.mainHandler.removeCallbacks(this.failsafeRunnable);
        this.windowManager.removeView(this.mView);
    }

    @Override // com.android.systemui.plugins.NavigationEdgeBackPlugin
    public final void onMotionEvent(MotionEvent motionEvent) {
        VelocityTracker velocityTracker;
        boolean z;
        EdgePanelParams.BackIndicatorDimens backIndicatorDimens;
        Interpolator interpolator;
        VelocityTracker velocityTracker2;
        if (this.velocityTracker == null) {
            this.velocityTracker = VelocityTracker.obtain();
        }
        VelocityTracker velocityTracker3 = this.velocityTracker;
        Intrinsics.checkNotNull(velocityTracker3);
        velocityTracker3.addMovement(motionEvent);
        int actionMasked = motionEvent.getActionMasked();
        EdgePanelParams edgePanelParams = this.params;
        Handler handler = this.mainHandler;
        if (actionMasked == 0) {
            handler.removeCallbacks(this.failsafeRunnable);
            Iterator it = ((BackPanel) this.mView).allAnimatedFloat.iterator();
            while (it.hasNext()) {
                ((BackPanel.AnimatedFloat) it.next()).animation.cancel();
            }
            handler.removeCallbacks(this.onEndSetCommittedStateListener.runnable);
            handler.removeCallbacks(this.onEndSetGoneStateListener.runnable);
            handler.removeCallbacks(this.onAlphaEndSetGoneStateListener.runnable);
            this.startX = motionEvent.getX();
            this.startY = motionEvent.getY();
            updateArrowState(GestureState.GONE, false);
            float max = Math.max(this.startY - edgePanelParams.fingerOffset, edgePanelParams.minArrowYPosition);
            WindowManager.LayoutParams layoutParams = this.layoutParams;
            (layoutParams != null ? layoutParams : null).y = MathUtils.constrain((int) (max - ((layoutParams == null ? null : layoutParams).height / 2.0f)), 0, this.displaySize.y);
            BackPanel backPanel = (BackPanel) this.mView;
            boolean z2 = backPanel.isLeftPanel;
            this.hasPassedDragSlop = false;
            backPanel.backgroundAlpha.snapTo(1.0f);
            backPanel.verticalTranslation.snapTo(0.0f);
            backPanel.scale.snapTo(1.0f);
            backPanel.horizontalTranslation.snapToRestingPosition();
            backPanel.arrowLength.snapToRestingPosition();
            backPanel.arrowHeight.snapToRestingPosition();
            backPanel.arrowAlpha.snapToRestingPosition();
            backPanel.backgroundWidth.snapToRestingPosition();
            backPanel.backgroundHeight.snapToRestingPosition();
            backPanel.backgroundEdgeCornerRadius.snapToRestingPosition();
            backPanel.backgroundFarCornerRadius.snapToRestingPosition();
            return;
        }
        if (actionMasked == 1) {
            switch (this.currentState.ordinal()) {
                case 0:
                case 4:
                case 5:
                case 6:
                    updateArrowState(GestureState.CANCELLED, false);
                    break;
                case 1:
                    if (!isFlungAwayFromEdge$default(this, motionEvent.getX()) && this.previousXTranslation <= edgePanelParams.staticTriggerThreshold) {
                        updateArrowState(GestureState.CANCELLED, false);
                        break;
                    } else {
                        updateArrowState(GestureState.FLUNG, false);
                        break;
                    }
                    break;
                case 2:
                    if (this.previousState == GestureState.ENTRY && getElapsedTimeSinceEntry() < 100) {
                        updateArrowState(GestureState.FLUNG, false);
                        break;
                    } else {
                        if (this.previousState == GestureState.INACTIVE) {
                            ((SystemClockImpl) this.systemClock).getClass();
                            if (android.os.SystemClock.uptimeMillis() - this.gestureInactiveTime < 400) {
                                handler.postDelayed(new BackPanelController$failsafeRunnable$1(this, 5), 130L);
                                break;
                            }
                        }
                        updateArrowState(GestureState.COMMITTED, false);
                        break;
                    }
                case 3:
                    if (!isFlungAwayFromEdge$default(this, motionEvent.getX())) {
                        updateArrowState(GestureState.CANCELLED, false);
                        break;
                    } else {
                        NavigationEdgeBackPlugin.BackCallback backCallback = this.backCallback;
                        if (backCallback == null) {
                            backCallback = null;
                        }
                        backCallback.setTriggerBack(true);
                        handler.postDelayed(new BackPanelController$failsafeRunnable$1(this, 4), 50L);
                        break;
                    }
            }
            if (!Intrinsics.areEqual(this.velocityTracker, (Object) null) && (velocityTracker = this.velocityTracker) != null) {
                velocityTracker.recycle();
            }
            this.velocityTracker = null;
            return;
        }
        if (actionMasked != 2) {
            if (actionMasked != 3) {
                return;
            }
            this.interactionJankMonitor.cancel(88);
            updateArrowState(GestureState.GONE, false);
            if (!Intrinsics.areEqual(this.velocityTracker, (Object) null) && (velocityTracker2 = this.velocityTracker) != null) {
                velocityTracker2.recycle();
            }
            this.velocityTracker = null;
            return;
        }
        float x = motionEvent.getX();
        float f = this.startX;
        if (this.hasPassedDragSlop) {
            z = true;
        } else {
            if (Math.abs(x - f) > this.viewConfiguration.getScaledEdgeSlop()) {
                updateArrowState(GestureState.ENTRY, false);
                View view = this.mView;
                WindowManager.LayoutParams layoutParams2 = this.layoutParams;
                if (layoutParams2 == null) {
                    layoutParams2 = null;
                }
                this.windowManager.updateViewLayout(view, layoutParams2);
                BackPanel backPanel2 = (BackPanel) this.mView;
                backPanel2.latencyTracker.onActionStart(15);
                backPanel2.trackingBackArrowLatency = true;
                this.hasPassedDragSlop = true;
            }
            z = this.hasPassedDragSlop;
        }
        if (z) {
            float x2 = motionEvent.getX();
            float y = motionEvent.getY() - this.startY;
            float abs = Math.abs(y);
            float max2 = Math.max(0.0f, ((BackPanel) this.mView).isLeftPanel ? x2 - this.startX : this.startX - x2);
            float f2 = max2 - this.previousXTranslation;
            this.previousXTranslation = max2;
            if (Math.abs(f2) > 0.0f) {
                boolean z3 = Math.signum(f2) == Math.signum(this.totalTouchDeltaActive);
                ClosedFloatRange closedFloatRange = edgePanelParams.dynamicTriggerThresholdRange;
                if (closedFloatRange == null) {
                    closedFloatRange = null;
                }
                Float valueOf = Float.valueOf(this.totalTouchDeltaActive);
                closedFloatRange.getClass();
                float floatValue = valueOf.floatValue();
                boolean z4 = floatValue >= closedFloatRange._start && floatValue <= closedFloatRange._endInclusive;
                if (z3 || z4) {
                    this.totalTouchDeltaActive += f2;
                } else {
                    this.totalTouchDeltaActive = f2;
                    this.touchDeltaStartX = x2;
                }
                this.totalTouchDeltaInactive = RangesKt.coerceAtLeast(this.totalTouchDeltaInactive + f2, -this.viewConfiguration.getScaledTouchSlop());
            }
            boolean z5 = ((float) 2) * max2 >= abs;
            boolean z6 = max2 > edgePanelParams.staticTriggerThreshold;
            int ordinal = this.currentState.ordinal();
            if (ordinal != 1) {
                if (ordinal == 2) {
                    boolean z7 = this.totalTouchDeltaActive <= (-edgePanelParams.deactivationTriggerThreshold);
                    boolean z8 = getElapsedTimeSinceEntry() > 300;
                    if ((!z5 || z7) && z8) {
                        updateArrowState(GestureState.INACTIVE, false);
                    }
                } else if (ordinal == 3) {
                    if (isPastThresholdToActive$default(this, z6 && ((this.totalTouchDeltaInactive > edgePanelParams.reactivationTriggerThreshold ? 1 : (this.totalTouchDeltaInactive == edgePanelParams.reactivationTriggerThreshold ? 0 : -1)) >= 0) && z5, null, 4)) {
                        updateArrowState(GestureState.ACTIVE, false);
                    }
                }
            } else if (isPastThresholdToActive$default(this, z6, this.entryToActiveDelayCalculation, 2)) {
                updateArrowState(GestureState.ACTIVE, false);
            }
            int ordinal2 = this.currentState.ordinal();
            Float valueOf2 = ordinal2 != 1 ? ordinal2 != 2 ? ordinal2 != 3 ? null : Float.valueOf(MathUtils.saturate(this.totalTouchDeltaInactive / edgePanelParams.reactivationTriggerThreshold)) : Float.valueOf(MathUtils.saturate((max2 - this.previousXTranslationOnActiveOffset) / this.fullyStretchedThreshold)) : Float.valueOf(MathUtils.saturate(max2 / edgePanelParams.staticTriggerThreshold));
            if (valueOf2 != null) {
                int ordinal3 = this.currentState.ordinal();
                if (ordinal3 == 1) {
                    float floatValue2 = valueOf2.floatValue();
                    BackPanel backPanel3 = (BackPanel) this.mView;
                    Interpolator interpolator2 = edgePanelParams.arrowAngleInterpolator;
                    if (interpolator2 == null) {
                        interpolator2 = null;
                    }
                    float interpolation = interpolator2.getInterpolation(floatValue2);
                    PathInterpolator pathInterpolator = edgePanelParams.entryWidthInterpolator;
                    if (pathInterpolator == null) {
                        pathInterpolator = null;
                    }
                    float interpolation2 = pathInterpolator.getInterpolation(floatValue2);
                    PathInterpolator pathInterpolator2 = edgePanelParams.heightInterpolator;
                    if (pathInterpolator2 == null) {
                        pathInterpolator2 = null;
                    }
                    float interpolation3 = pathInterpolator2.getInterpolation(floatValue2);
                    EdgePanelParams.BackIndicatorDimens backIndicatorDimens2 = edgePanelParams.entryIndicator;
                    if (backIndicatorDimens2 == null) {
                        backIndicatorDimens2 = null;
                    }
                    Step step = backIndicatorDimens2.arrowDimens.alphaInterpolator;
                    float floatValue3 = step != null ? ((Number) step.get(floatValue2).value).floatValue() : 0.0f;
                    PathInterpolator pathInterpolator3 = edgePanelParams.edgeCornerInterpolator;
                    if (pathInterpolator3 == null) {
                        pathInterpolator3 = null;
                    }
                    float interpolation4 = pathInterpolator3.getInterpolation(floatValue2);
                    PathInterpolator pathInterpolator4 = edgePanelParams.farCornerInterpolator;
                    if (pathInterpolator4 == null) {
                        pathInterpolator4 = null;
                    }
                    float interpolation5 = pathInterpolator4.getInterpolation(floatValue2);
                    EdgePanelParams.BackIndicatorDimens backIndicatorDimens3 = edgePanelParams.preThresholdIndicator;
                    backPanel3.setStretch(0.0f, interpolation, floatValue3, interpolation2, interpolation3, interpolation4, interpolation5, backIndicatorDimens3 != null ? backIndicatorDimens3 : null);
                } else if (ordinal3 == 2) {
                    float floatValue4 = valueOf2.floatValue();
                    BackPanel backPanel4 = (BackPanel) this.mView;
                    PathInterpolator pathInterpolator5 = edgePanelParams.horizontalTranslationInterpolator;
                    if (pathInterpolator5 == null) {
                        pathInterpolator5 = null;
                    }
                    float interpolation6 = pathInterpolator5.getInterpolation(floatValue4);
                    Interpolator interpolator3 = edgePanelParams.arrowAngleInterpolator;
                    if (interpolator3 == null) {
                        interpolator3 = null;
                    }
                    float interpolation7 = interpolator3.getInterpolation(floatValue4);
                    PathInterpolator pathInterpolator6 = edgePanelParams.activeWidthInterpolator;
                    if (pathInterpolator6 == null) {
                        pathInterpolator6 = null;
                    }
                    float interpolation8 = pathInterpolator6.getInterpolation(floatValue4);
                    EdgePanelParams.BackIndicatorDimens backIndicatorDimens4 = edgePanelParams.fullyStretchedIndicator;
                    backPanel4.setStretch(interpolation6, interpolation7, 1.0f, interpolation8, 1.0f, 1.0f, 1.0f, backIndicatorDimens4 != null ? backIndicatorDimens4 : null);
                } else if (ordinal3 == 3) {
                    float floatValue5 = valueOf2.floatValue();
                    BackPanel backPanel5 = (BackPanel) this.mView;
                    Interpolator interpolator4 = edgePanelParams.arrowAngleInterpolator;
                    if (interpolator4 == null) {
                        interpolator4 = null;
                    }
                    float interpolation9 = interpolator4.getInterpolation(floatValue5);
                    if (this.totalTouchDeltaInactive <= this.viewConfiguration.getScaledTouchSlop()) {
                        interpolator = this.previousPreThresholdWidthInterpolator;
                    } else if (this.totalTouchDeltaInactive <= 0.0f ? (interpolator = edgePanelParams.entryWidthTowardsEdgeInterpolator) == null : (interpolator = edgePanelParams.entryWidthInterpolator) == null) {
                        interpolator = null;
                    }
                    this.previousPreThresholdWidthInterpolator = interpolator;
                    float coerceAtLeast = RangesKt.coerceAtLeast(interpolator.getInterpolation(floatValue5), 0.0f);
                    PathInterpolator pathInterpolator7 = edgePanelParams.heightInterpolator;
                    if (pathInterpolator7 == null) {
                        pathInterpolator7 = null;
                    }
                    float interpolation10 = pathInterpolator7.getInterpolation(floatValue5);
                    EdgePanelParams.BackIndicatorDimens backIndicatorDimens5 = edgePanelParams.preThresholdIndicator;
                    if (backIndicatorDimens5 == null) {
                        backIndicatorDimens5 = null;
                    }
                    Step step2 = backIndicatorDimens5.arrowDimens.alphaInterpolator;
                    float floatValue6 = step2 != null ? ((Number) step2.get(floatValue5).value).floatValue() : 0.0f;
                    PathInterpolator pathInterpolator8 = edgePanelParams.edgeCornerInterpolator;
                    if (pathInterpolator8 == null) {
                        pathInterpolator8 = null;
                    }
                    float interpolation11 = pathInterpolator8.getInterpolation(floatValue5);
                    PathInterpolator pathInterpolator9 = edgePanelParams.farCornerInterpolator;
                    if (pathInterpolator9 == null) {
                        pathInterpolator9 = null;
                    }
                    float interpolation12 = pathInterpolator9.getInterpolation(floatValue5);
                    EdgePanelParams.BackIndicatorDimens backIndicatorDimens6 = edgePanelParams.preThresholdIndicator;
                    backPanel5.setStretch(0.0f, interpolation9, floatValue6, coerceAtLeast, interpolation10, interpolation11, interpolation12, backIndicatorDimens6 != null ? backIndicatorDimens6 : null);
                }
            }
            switch (this.currentState.ordinal()) {
                case 0:
                case 6:
                    valueOf2 = Float.valueOf(0.0f);
                    break;
                case 1:
                case 3:
                    break;
                case 2:
                case 4:
                case 5:
                    valueOf2 = Float.valueOf(1.0f);
                    break;
                default:
                    throw new NoWhenBranchMatchedException();
            }
            int ordinal4 = this.currentState.ordinal();
            if (ordinal4 == 1 ? (backIndicatorDimens = edgePanelParams.entryIndicator) == null : ordinal4 == 2 ? (backIndicatorDimens = edgePanelParams.activeIndicator) == null : ordinal4 == 3 ? (backIndicatorDimens = edgePanelParams.preThresholdIndicator) == null : (backIndicatorDimens = edgePanelParams.preThresholdIndicator) == null) {
                backIndicatorDimens = null;
            }
            if (valueOf2 != null) {
                float floatValue7 = valueOf2.floatValue();
                Step step3 = backIndicatorDimens.arrowDimens.alphaSpring;
                if (step3 != null) {
                    Step.Value value = step3.get(floatValue7);
                    if (!value.isNewState) {
                        value = null;
                    }
                    if (value != null) {
                        BackPanel backPanel6 = (BackPanel) this.mView;
                        SpringForce springForce = (SpringForce) value.value;
                        BackPanel.AnimatedFloat animatedFloat = backPanel6.arrowAlpha;
                        SpringAnimation springAnimation = animatedFloat.animation;
                        springAnimation.cancel();
                        springAnimation.mVelocity = 0.0f;
                        if (springForce != null) {
                            springAnimation.mSpring = springForce;
                        }
                        springAnimation.animateToFinalPosition(animatedFloat.restingPosition + 0.0f);
                    }
                }
            }
            float abs2 = Math.abs(y);
            float height = ((BackPanel) this.mView).getHeight();
            EdgePanelParams.BackIndicatorDimens backIndicatorDimens7 = edgePanelParams.entryIndicator;
            if (backIndicatorDimens7 == null) {
                backIndicatorDimens7 = null;
            }
            float f3 = (height - backIndicatorDimens7.backgroundDimens.height) / 2.0f;
            float saturate = MathUtils.saturate(abs2 / (15.0f * f3));
            PathInterpolator pathInterpolator10 = edgePanelParams.verticalTranslationInterpolator;
            if (pathInterpolator10 == null) {
                pathInterpolator10 = null;
            }
            BackPanel.AnimatedFloat.stretchTo$default(((BackPanel) this.mView).verticalTranslation, Math.signum(y) * pathInterpolator10.getInterpolation(saturate) * f3, null, 6);
        }
    }

    @Override // com.android.systemui.util.ViewController
    public final void onViewAttached() {
        updateConfiguration$1();
        ConfigurationControllerImpl configurationControllerImpl = (ConfigurationControllerImpl) this.configurationController;
        boolean z = configurationControllerImpl.layoutDirection == 1;
        BackPanel backPanel = (BackPanel) this.mView;
        if (backPanel.arrowsPointLeft != z) {
            backPanel.invalidate();
            backPanel.arrowsPointLeft = z;
        }
        updateArrowState(GestureState.GONE, true);
        updateRestingArrowDimens();
        configurationControllerImpl.addCallback(this.configurationListener);
    }

    @Override // com.android.systemui.util.ViewController
    public final void onViewDetached() {
        ((ConfigurationControllerImpl) this.configurationController).removeCallback(this.configurationListener);
    }

    public final void playWithBackgroundWidthAnimation(final DelayedOnAnimationEndListener delayedOnAnimationEndListener, long j) {
        Handler handler = this.mainHandler;
        if (j != 0) {
            handler.postDelayed(new Runnable() { // from class: com.android.systemui.navigationbar.gestural.BackPanelController$playWithBackgroundWidthAnimation$$inlined$postDelayed$default$1
                @Override // java.lang.Runnable
                public final void run() {
                    BackPanelController.this.playWithBackgroundWidthAnimation(delayedOnAnimationEndListener, 0L);
                }
            }, j);
            return;
        }
        updateRestingArrowDimens();
        BackPanel backPanel = (BackPanel) this.mView;
        BackPanel.AnimatedFloat animatedFloat = backPanel.backgroundWidth;
        backPanel.getClass();
        SpringAnimation springAnimation = animatedFloat.animation;
        if (springAnimation.mRunning) {
            springAnimation.addEndListener(delayedOnAnimationEndListener);
            return;
        }
        delayedOnAnimationEndListener.runnable.run();
        BackPanelController$failsafeRunnable$1 backPanelController$failsafeRunnable$1 = this.failsafeRunnable;
        handler.removeCallbacks(backPanelController$failsafeRunnable$1);
        handler.postDelayed(backPanelController$failsafeRunnable$1, 350L);
    }

    @Override // com.android.systemui.plugins.NavigationEdgeBackPlugin
    public final void setBackCallback(NavigationEdgeBackPlugin.BackCallback backCallback) {
        this.backCallback = backCallback;
    }

    @Override // com.android.systemui.plugins.NavigationEdgeBackPlugin
    public final void setDisplaySize(Point point) {
        this.displaySize.set(point.x, point.y);
        this.fullyStretchedThreshold = Math.min(point.x, this.params.swipeProgressThreshold);
    }

    @Override // com.android.systemui.plugins.NavigationEdgeBackPlugin
    public final void setIsLeftPanel(boolean z) {
        ((BackPanel) this.mView).isLeftPanel = z;
        WindowManager.LayoutParams layoutParams = this.layoutParams;
        if (layoutParams == null) {
            layoutParams = null;
        }
        layoutParams.gravity = z ? 51 : 53;
    }

    @Override // com.android.systemui.plugins.NavigationEdgeBackPlugin
    public final void setLayoutParams(WindowManager.LayoutParams layoutParams) {
        this.layoutParams = layoutParams;
        this.windowManager.addView(this.mView, layoutParams);
    }

    public final void updateArrowState(GestureState gestureState, boolean z) {
        if (z || this.currentState != gestureState) {
            this.previousState = this.currentState;
            this.currentState = gestureState;
            int ordinal = gestureState.ordinal();
            if (ordinal == 0) {
                this.interactionJankMonitor.end(88);
            } else if (ordinal == 1) {
                this.interactionJankMonitor.cancel(88);
                this.interactionJankMonitor.begin(this.mView, 88);
            }
            switch (this.currentState.ordinal()) {
                case 1:
                case 3:
                    NavigationEdgeBackPlugin.BackCallback backCallback = this.backCallback;
                    if (backCallback == null) {
                        backCallback = null;
                    }
                    backCallback.setTriggerBack(false);
                    break;
                case 2:
                    NavigationEdgeBackPlugin.BackCallback backCallback2 = this.backCallback;
                    if (backCallback2 == null) {
                        backCallback2 = null;
                    }
                    backCallback2.setTriggerBack(true);
                    break;
                case 4:
                case 5:
                    if (this.previousState != GestureState.FLUNG) {
                        NavigationEdgeBackPlugin.BackCallback backCallback3 = this.backCallback;
                        if (backCallback3 == null) {
                            backCallback3 = null;
                        }
                        backCallback3.triggerBack();
                        break;
                    }
                    break;
                case 6:
                    NavigationEdgeBackPlugin.BackCallback backCallback4 = this.backCallback;
                    if (backCallback4 == null) {
                        backCallback4 = null;
                    }
                    backCallback4.cancelBack();
                    break;
            }
            int ordinal2 = this.currentState.ordinal();
            EdgePanelParams edgePanelParams = this.params;
            VibratorHelper vibratorHelper = this.vibratorHelper;
            SystemClock systemClock = this.systemClock;
            DelayedOnAnimationEndListener delayedOnAnimationEndListener = this.onEndSetGoneStateListener;
            Handler handler = this.mainHandler;
            switch (ordinal2) {
                case 0:
                    updateRestingArrowDimens();
                    this.mView.setVisibility(8);
                    break;
                case 1:
                    this.mView.setVisibility(0);
                    updateRestingArrowDimens();
                    ((SystemClockImpl) systemClock).getClass();
                    this.gestureEntryTime = android.os.SystemClock.uptimeMillis();
                    break;
                case 2:
                    this.previousXTranslationOnActiveOffset = this.previousXTranslation;
                    updateRestingArrowDimens();
                    View view = this.mView;
                    vibratorHelper.getClass();
                    view.performHapticFeedback(23);
                    ((BackPanel) this.mView).popOffEdge(this.previousState == GestureState.INACTIVE ? 4.7f : 4.5f);
                    break;
                case 3:
                    ((SystemClockImpl) systemClock).getClass();
                    this.gestureInactiveTime = android.os.SystemClock.uptimeMillis();
                    this.totalTouchDeltaInactive = -edgePanelParams.deactivationTriggerThreshold;
                    ((BackPanel) this.mView).popOffEdge(-1.5f);
                    View view2 = this.mView;
                    vibratorHelper.getClass();
                    view2.performHapticFeedback(24);
                    updateRestingArrowDimens();
                    break;
                case 4:
                    if (this.previousState != GestureState.ACTIVE) {
                        View view3 = this.mView;
                        vibratorHelper.getClass();
                        view3.performHapticFeedback(23);
                    }
                    handler.postDelayed(new BackPanelController$failsafeRunnable$1(this, 6), 60L);
                    handler.postDelayed(this.onEndSetCommittedStateListener.runnable, 160L);
                    updateRestingArrowDimens();
                    break;
                case 5:
                    if (this.previousState != GestureState.FLUNG) {
                        BackPanel backPanel = (BackPanel) this.mView;
                        backPanel.scalePivotX.snapTo(backPanel.backgroundWidth.pos / 2);
                        BackPanel.AnimatedFloat.stretchTo$default(backPanel.scale, 0.0f, Float.valueOf(3.0f), 4);
                        handler.postDelayed(this.onAlphaEndSetGoneStateListener.runnable, 80L);
                        break;
                    } else {
                        updateRestingArrowDimens();
                        handler.postDelayed(delayedOnAnimationEndListener.runnable, 120L);
                        break;
                    }
                case 6:
                    playWithBackgroundWidthAnimation(delayedOnAnimationEndListener, Math.max(0L, 200 - getElapsedTimeSinceEntry()));
                    EdgePanelParams.BackIndicatorDimens backIndicatorDimens = edgePanelParams.cancelledIndicator;
                    if (backIndicatorDimens == null) {
                        backIndicatorDimens = null;
                    }
                    Step step = backIndicatorDimens.arrowDimens.alphaSpring;
                    SpringForce springForce = step != null ? (SpringForce) step.get(0.0f).value : null;
                    BackPanel.AnimatedFloat animatedFloat = ((BackPanel) this.mView).arrowAlpha;
                    SpringAnimation springAnimation = animatedFloat.animation;
                    springAnimation.cancel();
                    springAnimation.mVelocity = 0.0f;
                    if (springForce != null) {
                        springAnimation.mSpring = springForce;
                    }
                    springAnimation.animateToFinalPosition(animatedFloat.restingPosition + 0.0f);
                    break;
            }
        }
    }

    public final void updateConfiguration$1() {
        Resources resources = this.mView.getResources();
        EdgePanelParams edgePanelParams = this.params;
        edgePanelParams.update(resources);
        BackPanel backPanel = (BackPanel) this.mView;
        backPanel.arrowPaint.setStrokeWidth(edgePanelParams.arrowThickness);
        boolean z = (backPanel.getResources().getConfiguration().uiMode & 48) == 32;
        backPanel.arrowPaint.setColor(Utils.getColorAttrDefaultColor(z ? R.^attr-private.materialColorOnSecondaryContainer : R.^attr-private.materialColorOnSecondaryFixed, 0, backPanel.getContext()));
        backPanel.arrowBackgroundPaint.setColor(Utils.getColorAttrDefaultColor(z ? R.^attr-private.materialColorSecondaryContainer : R.^attr-private.materialColorSecondaryFixedDim, 0, backPanel.getContext()));
        this.minFlingDistance = this.viewConfiguration.getScaledTouchSlop() * 3;
    }

    /* JADX WARN: Removed duplicated region for block: B:202:0x0299  */
    /* JADX WARN: Removed duplicated region for block: B:204:0x029f  */
    /* JADX WARN: Removed duplicated region for block: B:210:0x02d4  */
    /* JADX WARN: Removed duplicated region for block: B:212:0x02da  */
    /* JADX WARN: Removed duplicated region for block: B:218:0x02e2  */
    /* JADX WARN: Removed duplicated region for block: B:222:0x02ea  */
    /* JADX WARN: Removed duplicated region for block: B:226:0x02f2  */
    /* JADX WARN: Removed duplicated region for block: B:230:0x02fa  */
    /* JADX WARN: Removed duplicated region for block: B:234:0x02a8  */
    /* JADX WARN: Removed duplicated region for block: B:238:0x02b1  */
    /* JADX WARN: Removed duplicated region for block: B:242:0x02ba  */
    /* JADX WARN: Removed duplicated region for block: B:246:0x02c3  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void updateRestingArrowDimens() {
        /*
            Method dump skipped, instructions count: 938
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.navigationbar.gestural.BackPanelController.updateRestingArrowDimens():void");
    }

    public static /* synthetic */ void getCurrentState$frameworks__base__packages__SystemUI__android_common__SystemUI_core$annotations() {
    }

    public static /* synthetic */ void getParams$frameworks__base__packages__SystemUI__android_common__SystemUI_core$annotations() {
    }

    @Override // com.android.systemui.plugins.NavigationEdgeBackPlugin
    public final void setInsets(int i, int i2) {
    }
}
