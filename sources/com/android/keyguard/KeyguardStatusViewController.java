package com.android.keyguard;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.app.smartspace.SmartspaceSession;
import android.content.res.Configuration;
import android.graphics.Rect;
import android.os.Trace;
import android.transition.Transition;
import android.transition.TransitionListenerAdapter;
import android.transition.TransitionValues;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import androidx.slice.Slice;
import androidx.slice.SliceViewManagerWrapper;
import com.android.app.animation.Interpolators;
import com.android.internal.jank.InteractionJankMonitor;
import com.android.keyguard.KeyguardStatusViewController;
import com.android.keyguard.logging.KeyguardLogger;
import com.android.systemui.animation.ViewHierarchyAnimator;
import com.android.systemui.keyguard.KeyguardSliceProvider;
import com.android.systemui.keyguard.domain.interactor.KeyguardInteractor;
import com.android.systemui.log.core.LogLevel;
import com.android.systemui.plugins.clocks.ClockController;
import com.android.systemui.power.domain.interactor.PowerInteractor;
import com.android.systemui.power.shared.model.ScreenPowerState;
import com.android.systemui.statusbar.lockscreen.LockscreenSmartspaceController;
import com.android.systemui.statusbar.notification.AnimatableProperty;
import com.android.systemui.statusbar.notification.PropertyAnimator;
import com.android.systemui.statusbar.notification.stack.AnimationProperties;
import com.android.systemui.statusbar.phone.DozeParameters;
import com.android.systemui.statusbar.phone.ScreenOffAnimationController;
import com.android.systemui.statusbar.policy.ConfigurationController;
import com.android.systemui.statusbar.policy.KeyguardStateController;
import com.android.systemui.util.ViewController;
import com.android.systemui.util.kotlin.JavaAdapterKt;
import com.android.wm.shell.R;
import java.util.function.Consumer;
import kotlin.coroutines.EmptyCoroutineContext;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class KeyguardStatusViewController extends ViewController {
    public static final AnimationProperties CLOCK_ANIMATION_PROPERTIES;
    static final String TAG = "KeyguardStatusViewController";
    public final Rect mClipBounds;
    public final AnonymousClass3 mConfigurationListener;
    public final DozeParameters mDozeParameters;
    public final InteractionJankMonitor mInteractionJankMonitor;
    public final KeyguardClockSwitchController mKeyguardClockSwitchController;
    public final KeyguardInteractor mKeyguardInteractor;
    public final KeyguardSliceViewController mKeyguardSliceViewController;
    public final AnonymousClass1 mKeyguardStatusAlignmentTransitionListener;
    public final KeyguardUpdateMonitor mKeyguardUpdateMonitor;
    public final KeyguardVisibilityHelper mKeyguardVisibilityHelper;
    public final PowerInteractor mPowerInteractor;
    public final Boolean mSplitShadeEnabled;
    public ValueAnimator mStatusAreaHeightAnimator;
    public final AnonymousClass2 mStatusAreaLayoutChangeListener;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    class SplitShadeTransitionAdapter extends Transition {
        public static final String[] TRANSITION_PROPERTIES = {"splitShadeTransitionAdapter:boundsLeft", "splitShadeTransitionAdapter:boundsRight", "splitShadeTransitionAdapter:xInWindow"};
        public final KeyguardClockSwitchController mController;

        public SplitShadeTransitionAdapter(KeyguardClockSwitchController keyguardClockSwitchController) {
            this.mController = keyguardClockSwitchController;
        }

        public static void captureValues(TransitionValues transitionValues) {
            transitionValues.values.put("splitShadeTransitionAdapter:boundsLeft", Integer.valueOf(transitionValues.view.getLeft()));
            transitionValues.values.put("splitShadeTransitionAdapter:boundsRight", Integer.valueOf(transitionValues.view.getRight()));
            int[] iArr = new int[2];
            transitionValues.view.getLocationInWindow(iArr);
            transitionValues.values.put("splitShadeTransitionAdapter:xInWindow", Integer.valueOf(iArr[0]));
        }

        @Override // android.transition.Transition
        public final void captureEndValues(TransitionValues transitionValues) {
            captureValues(transitionValues);
        }

        @Override // android.transition.Transition
        public final void captureStartValues(TransitionValues transitionValues) {
            captureValues(transitionValues);
        }

        @Override // android.transition.Transition
        public final Animator createAnimator(ViewGroup viewGroup, TransitionValues transitionValues, TransitionValues transitionValues2) {
            if (transitionValues == null || transitionValues2 == null) {
                return null;
            }
            ValueAnimator ofFloat = ValueAnimator.ofFloat(0.0f, 1.0f);
            final int intValue = ((Integer) transitionValues.values.get("splitShadeTransitionAdapter:boundsLeft")).intValue();
            final int i = ((Integer) transitionValues2.values.get("splitShadeTransitionAdapter:xInWindow")).intValue() - ((Integer) transitionValues.values.get("splitShadeTransitionAdapter:xInWindow")).intValue() > 0 ? 1 : -1;
            ofFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.android.keyguard.KeyguardStatusViewController$SplitShadeTransitionAdapter$$ExternalSyntheticLambda0
                @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                    KeyguardStatusViewController.SplitShadeTransitionAdapter splitShadeTransitionAdapter = KeyguardStatusViewController.SplitShadeTransitionAdapter.this;
                    int i2 = intValue;
                    int i3 = i;
                    ClockController clock = splitShadeTransitionAdapter.mController.getClock();
                    if (clock == null) {
                        return;
                    }
                    clock.getLargeClock().getAnimations().onPositionUpdated(i2, i3, valueAnimator.getAnimatedFraction());
                }
            });
            return ofFloat;
        }

        @Override // android.transition.Transition
        public final String[] getTransitionProperties() {
            return TRANSITION_PROPERTIES;
        }
    }

    static {
        boolean z = KeyguardConstants.DEBUG;
        AnimationProperties animationProperties = new AnimationProperties();
        animationProperties.duration = 360L;
        CLOCK_ANIMATION_PROPERTIES = animationProperties;
    }

    public KeyguardStatusViewController(KeyguardStatusView keyguardStatusView, KeyguardSliceViewController keyguardSliceViewController, KeyguardClockSwitchController keyguardClockSwitchController, KeyguardStateController keyguardStateController, KeyguardUpdateMonitor keyguardUpdateMonitor, ConfigurationController configurationController, DozeParameters dozeParameters, ScreenOffAnimationController screenOffAnimationController, KeyguardLogger keyguardLogger, InteractionJankMonitor interactionJankMonitor, KeyguardInteractor keyguardInteractor, PowerInteractor powerInteractor) {
        super(keyguardStatusView);
        this.mClipBounds = new Rect();
        this.mStatusAreaHeightAnimator = null;
        this.mSplitShadeEnabled = Boolean.FALSE;
        new TransitionListenerAdapter() { // from class: com.android.keyguard.KeyguardStatusViewController.1
            @Override // android.transition.TransitionListenerAdapter, android.transition.Transition.TransitionListener
            public final void onTransitionCancel(Transition transition) {
                KeyguardStatusViewController.this.mInteractionJankMonitor.cancel(70);
            }

            @Override // android.transition.TransitionListenerAdapter, android.transition.Transition.TransitionListener
            public final void onTransitionEnd(Transition transition) {
                KeyguardStatusViewController.this.mInteractionJankMonitor.end(70);
            }
        };
        new View.OnLayoutChangeListener() { // from class: com.android.keyguard.KeyguardStatusViewController.2
            @Override // android.view.View.OnLayoutChangeListener
            public final void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
                if (KeyguardStatusViewController.this.mDozeParameters.getAlwaysOn()) {
                    int height = view.getHeight() - (i8 - i6);
                    if (height == 0) {
                        return;
                    }
                    int i9 = height * (-1);
                    ValueAnimator valueAnimator = KeyguardStatusViewController.this.mStatusAreaHeightAnimator;
                    long j = 133;
                    if (valueAnimator != null && valueAnimator.isRunning()) {
                        j = 133 + (KeyguardStatusViewController.this.mStatusAreaHeightAnimator.getDuration() - KeyguardStatusViewController.this.mStatusAreaHeightAnimator.getCurrentPlayTime());
                        i9 += ((Integer) KeyguardStatusViewController.this.mStatusAreaHeightAnimator.getAnimatedValue()).intValue();
                        KeyguardStatusViewController.this.mStatusAreaHeightAnimator.cancel();
                        KeyguardStatusViewController.this.mStatusAreaHeightAnimator = null;
                    }
                    KeyguardStatusViewController.this.mStatusAreaHeightAnimator = ValueAnimator.ofInt(i9, 0);
                    KeyguardStatusViewController.this.mStatusAreaHeightAnimator.setDuration(j);
                    KeyguardStatusViewController.this.mKeyguardClockSwitchController.getClass();
                    KeyguardStatusViewController.this.mStatusAreaHeightAnimator.start();
                }
            }
        };
        new ConfigurationController.ConfigurationListener() { // from class: com.android.keyguard.KeyguardStatusViewController.3
            @Override // com.android.systemui.statusbar.policy.ConfigurationController.ConfigurationListener
            public final void onConfigChanged(Configuration configuration) {
                KeyguardClockSwitchController keyguardClockSwitchController2 = KeyguardStatusViewController.this.mKeyguardClockSwitchController;
                ((KeyguardClockSwitch) keyguardClockSwitchController2.mView).onConfigChanged();
                ((KeyguardClockSwitch) keyguardClockSwitchController2.mView).getResources().getDimensionPixelSize(R.dimen.keyguard_clock_top_margin);
                ((KeyguardClockSwitch) keyguardClockSwitchController2.mView).getResources().getDimensionPixelSize(R.dimen.keyguard_large_clock_top_margin);
                ((KeyguardClockSwitch) keyguardClockSwitchController2.mView).getResources().getInteger(R.integer.keyguard_date_weather_view_invisibility);
                ((KeyguardClockSwitch) keyguardClockSwitchController2.mView).getClass();
            }

            @Override // com.android.systemui.statusbar.policy.ConfigurationController.ConfigurationListener
            public final void onLocaleListChanged() {
                KeyguardStatusViewController keyguardStatusViewController = KeyguardStatusViewController.this;
                keyguardStatusViewController.refreshTime();
                KeyguardClockSwitchController keyguardClockSwitchController2 = keyguardStatusViewController.mKeyguardClockSwitchController;
                LockscreenSmartspaceController lockscreenSmartspaceController = keyguardClockSwitchController2.mSmartspaceController;
                if (lockscreenSmartspaceController.isEnabled) {
                    keyguardClockSwitchController2.removeViewsFromStatusArea();
                    if (lockscreenSmartspaceController.isDateWeatherDecoupled) {
                        throw null;
                    }
                }
            }
        };
        new KeyguardUpdateMonitorCallback() { // from class: com.android.keyguard.KeyguardStatusViewController.4
            @Override // com.android.keyguard.KeyguardUpdateMonitorCallback
            public final void onKeyguardVisibilityChanged(boolean z) {
                if (z) {
                    AnimationProperties animationProperties = KeyguardStatusViewController.CLOCK_ANIMATION_PROPERTIES;
                    KeyguardStatusViewController.this.refreshTime();
                }
            }

            @Override // com.android.keyguard.KeyguardUpdateMonitorCallback
            public final void onTimeChanged() {
                KeyguardStatusViewController.this.refreshTime();
            }
        };
        this.mKeyguardSliceViewController = keyguardSliceViewController;
        this.mKeyguardClockSwitchController = keyguardClockSwitchController;
        this.mKeyguardUpdateMonitor = keyguardUpdateMonitor;
        this.mDozeParameters = dozeParameters;
        this.mKeyguardVisibilityHelper = new KeyguardVisibilityHelper(keyguardStatusView, keyguardStateController, screenOffAnimationController, true, keyguardLogger.buffer);
        this.mInteractionJankMonitor = interactionJankMonitor;
        this.mKeyguardInteractor = keyguardInteractor;
        this.mPowerInteractor = powerInteractor;
    }

    public final void dozeTimeTick() {
        Slice bindSlice;
        refreshTime();
        KeyguardSliceViewController keyguardSliceViewController = this.mKeyguardSliceViewController;
        keyguardSliceViewController.getClass();
        Trace.beginSection("KeyguardSliceViewController#refresh");
        try {
            if ("content://com.android.systemui.keyguard/main".equals(keyguardSliceViewController.mKeyguardSliceUri.toString())) {
                KeyguardSliceProvider keyguardSliceProvider = KeyguardSliceProvider.sInstance;
                if (keyguardSliceProvider != null) {
                    keyguardSliceViewController.mBgHandler.post(new KeyguardSliceViewController$$ExternalSyntheticLambda2(keyguardSliceViewController, keyguardSliceProvider, 0));
                } else {
                    Log.w("KeyguardSliceViewCtrl", "Keyguard slice not bound yet?");
                    bindSlice = null;
                }
            } else {
                bindSlice = new SliceViewManagerWrapper(((KeyguardSliceView) keyguardSliceViewController.mView).getContext()).bindSlice(keyguardSliceViewController.mKeyguardSliceUri);
            }
            keyguardSliceViewController.mObserver.onChanged(bindSlice);
        } finally {
            Trace.endSection();
        }
    }

    @Override // com.android.systemui.util.ViewController
    public final void onInit() {
        KeyguardClockSwitchController keyguardClockSwitchController = this.mKeyguardClockSwitchController;
        keyguardClockSwitchController.init$9();
        final View findViewById = ((KeyguardStatusView) this.mView).findViewById(R.id.status_view_media_container);
        if (findViewById != null) {
            ((KeyguardClockSwitch) keyguardClockSwitchController.mView).addOnLayoutChangeListener(new View.OnLayoutChangeListener() { // from class: com.android.keyguard.KeyguardStatusViewController$$ExternalSyntheticLambda0
                @Override // android.view.View.OnLayoutChangeListener
                public final void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
                    KeyguardStatusViewController keyguardStatusViewController = KeyguardStatusViewController.this;
                    View view2 = findViewById;
                    if (keyguardStatusViewController.mSplitShadeEnabled.booleanValue()) {
                        ((KeyguardClockSwitch) keyguardStatusViewController.mKeyguardClockSwitchController.mView).getClass();
                        if (keyguardStatusViewController.mKeyguardUpdateMonitor.isKeyguardVisible()) {
                            if (view.getHeight() == i8 - i6 || view2.getVisibility() != 0 || view2.getHeight() == 0) {
                                return;
                            }
                            ViewHierarchyAnimator.Companion companion = ViewHierarchyAnimator.Companion;
                            ViewHierarchyAnimator.Companion.animateNextUpdate$default(view2, Interpolators.STANDARD, null, 16);
                        }
                    }
                }
            });
        }
        EmptyCoroutineContext emptyCoroutineContext = EmptyCoroutineContext.INSTANCE;
        final int i = 0;
        JavaAdapterKt.collectFlow(this.mView, this.mKeyguardInteractor.dozeTimeTick, new Consumer(this) { // from class: com.android.keyguard.KeyguardStatusViewController$$ExternalSyntheticLambda1
            public final /* synthetic */ KeyguardStatusViewController f$0;

            {
                this.f$0 = this;
            }

            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                int i2 = i;
                KeyguardStatusViewController keyguardStatusViewController = this.f$0;
                switch (i2) {
                    case 0:
                        keyguardStatusViewController.dozeTimeTick();
                        break;
                    default:
                        ScreenPowerState screenPowerState = (ScreenPowerState) obj;
                        keyguardStatusViewController.getClass();
                        if (screenPowerState == ScreenPowerState.SCREEN_TURNING_ON) {
                            keyguardStatusViewController.dozeTimeTick();
                            break;
                        }
                        break;
                }
            }
        }, emptyCoroutineContext);
        final int i2 = 1;
        JavaAdapterKt.collectFlow(this.mView, this.mPowerInteractor.screenPowerState, new Consumer(this) { // from class: com.android.keyguard.KeyguardStatusViewController$$ExternalSyntheticLambda1
            public final /* synthetic */ KeyguardStatusViewController f$0;

            {
                this.f$0 = this;
            }

            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                int i22 = i2;
                KeyguardStatusViewController keyguardStatusViewController = this.f$0;
                switch (i22) {
                    case 0:
                        keyguardStatusViewController.dozeTimeTick();
                        break;
                    default:
                        ScreenPowerState screenPowerState = (ScreenPowerState) obj;
                        keyguardStatusViewController.getClass();
                        if (screenPowerState == ScreenPowerState.SCREEN_TURNING_ON) {
                            keyguardStatusViewController.dozeTimeTick();
                            break;
                        }
                        break;
                }
            }
        }, emptyCoroutineContext);
        ((KeyguardStatusView) this.mView).setVisibility(8);
    }

    @Override // com.android.systemui.util.ViewController
    public final void onViewAttached() {
        ((KeyguardStatusView) this.mView).findViewById(R.id.keyguard_status_area);
    }

    public final void refreshTime() {
        SmartspaceSession smartspaceSession;
        KeyguardClockSwitchController keyguardClockSwitchController = this.mKeyguardClockSwitchController;
        keyguardClockSwitchController.getClass();
        keyguardClockSwitchController.mLogBuffer.log("KeyguardClockSwitchController", LogLevel.INFO, "refresh", null);
        LockscreenSmartspaceController lockscreenSmartspaceController = keyguardClockSwitchController.mSmartspaceController;
        if (lockscreenSmartspaceController != null && (smartspaceSession = lockscreenSmartspaceController.session) != null) {
            smartspaceSession.requestSmartspaceUpdate();
        }
        ClockController clock = keyguardClockSwitchController.getClock();
        if (clock != null) {
            clock.getSmallClock().getEvents().onTimeTick();
            clock.getLargeClock().getEvents().onTimeTick();
        }
    }

    public void setProperty(AnimatableProperty animatableProperty, float f, boolean z) {
        PropertyAnimator.setProperty((KeyguardStatusView) this.mView, animatableProperty, f, CLOCK_ANIMATION_PROPERTIES, z);
    }

    @Override // com.android.systemui.util.ViewController
    public final void onViewDetached() {
    }
}
