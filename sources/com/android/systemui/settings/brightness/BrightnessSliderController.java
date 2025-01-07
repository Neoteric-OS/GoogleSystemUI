package com.android.systemui.settings.brightness;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import com.android.internal.logging.UiEventLogger;
import com.android.systemui.Gefingerpoken;
import com.android.systemui.haptics.slider.HapticSliderViewBinder;
import com.android.systemui.haptics.slider.SeekableSliderTrackerConfig;
import com.android.systemui.haptics.slider.SeekbarHapticPlugin;
import com.android.systemui.haptics.slider.SliderHapticFeedbackConfig;
import com.android.systemui.plugins.ActivityStarter;
import com.android.systemui.plugins.FalsingManager;
import com.android.systemui.statusbar.NotificationShadeDepthController;
import com.android.systemui.statusbar.VibratorHelper;
import com.android.systemui.statusbar.policy.BrightnessMirrorController;
import com.android.systemui.util.ViewController;
import com.android.systemui.util.time.SystemClock;
import com.android.wm.shell.R;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class BrightnessSliderController extends ViewController {
    public final ActivityStarter mActivityStarter;
    public final SeekbarHapticPlugin mBrightnessSliderHapticPlugin;
    public final FalsingManager mFalsingManager;
    public ToggleSlider$Listener mListener;
    public BrightnessSliderController mMirror;
    public BrightnessMirrorController mMirrorController;
    public final AnonymousClass1 mOnInterceptListener;
    public final AnonymousClass2 mSeekListener;
    public boolean mTracking;
    public final UiEventLogger mUiEventLogger;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.settings.brightness.BrightnessSliderController$1, reason: invalid class name */
    public final class AnonymousClass1 implements Gefingerpoken {
        public AnonymousClass1() {
        }

        @Override // com.android.systemui.Gefingerpoken
        public final boolean onInterceptTouchEvent(MotionEvent motionEvent) {
            BrightnessSliderController brightnessSliderController = BrightnessSliderController.this;
            brightnessSliderController.mBrightnessSliderHapticPlugin.onTouchEvent(motionEvent);
            int actionMasked = motionEvent.getActionMasked();
            if (actionMasked != 1 && actionMasked != 3) {
                return false;
            }
            brightnessSliderController.mFalsingManager.isFalseTouch(10);
            return false;
        }

        @Override // com.android.systemui.Gefingerpoken
        public final boolean onTouchEvent(MotionEvent motionEvent) {
            return false;
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Factory {
        public final ActivityStarter mActivityStarter;
        public final FalsingManager mFalsingManager;
        public final SystemClock mSystemClock;
        public final UiEventLogger mUiEventLogger;
        public final VibratorHelper mVibratorHelper;

        public Factory(FalsingManager falsingManager, UiEventLogger uiEventLogger, VibratorHelper vibratorHelper, SystemClock systemClock, ActivityStarter activityStarter) {
            this.mFalsingManager = falsingManager;
            this.mUiEventLogger = uiEventLogger;
            this.mVibratorHelper = vibratorHelper;
            this.mSystemClock = systemClock;
            this.mActivityStarter = activityStarter;
        }

        public final BrightnessSliderController create(Context context, ViewGroup viewGroup) {
            BrightnessSliderView brightnessSliderView = (BrightnessSliderView) LayoutInflater.from(context).inflate(R.layout.quick_settings_brightness_dialog, viewGroup, false);
            SeekbarHapticPlugin seekbarHapticPlugin = new SeekbarHapticPlugin(this.mVibratorHelper, this.mSystemClock, new SliderHapticFeedbackConfig(0.15f, 0.015f, 2000.0f, 5, 0), new SeekableSliderTrackerConfig(0.05f, 0.95f));
            HapticSliderViewBinder.bind(viewGroup, seekbarHapticPlugin);
            return new BrightnessSliderController(brightnessSliderView, this.mFalsingManager, this.mUiEventLogger, seekbarHapticPlugin, this.mActivityStarter);
        }
    }

    /* JADX WARN: Type inference failed for: r1v2, types: [com.android.systemui.settings.brightness.BrightnessSliderController$2] */
    public BrightnessSliderController(BrightnessSliderView brightnessSliderView, FalsingManager falsingManager, UiEventLogger uiEventLogger, SeekbarHapticPlugin seekbarHapticPlugin, ActivityStarter activityStarter) {
        super(brightnessSliderView);
        this.mOnInterceptListener = new AnonymousClass1();
        this.mSeekListener = new SeekBar.OnSeekBarChangeListener() { // from class: com.android.systemui.settings.brightness.BrightnessSliderController.2
            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public final void onProgressChanged(SeekBar seekBar, int i, boolean z) {
                BrightnessSliderController brightnessSliderController = BrightnessSliderController.this;
                ToggleSlider$Listener toggleSlider$Listener = brightnessSliderController.mListener;
                if (toggleSlider$Listener != null) {
                    ((BrightnessController) toggleSlider$Listener).onChanged(i, brightnessSliderController.mTracking, false);
                    if (z) {
                        BrightnessSliderController.this.mBrightnessSliderHapticPlugin.onProgressChanged(seekBar, i, z);
                    }
                }
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public final void onStartTrackingTouch(SeekBar seekBar) {
                BrightnessSliderController brightnessSliderController = BrightnessSliderController.this;
                brightnessSliderController.mTracking = true;
                brightnessSliderController.mUiEventLogger.log(BrightnessSliderEvent.BRIGHTNESS_SLIDER_STARTED_TRACKING_TOUCH);
                BrightnessSliderController brightnessSliderController2 = BrightnessSliderController.this;
                ToggleSlider$Listener toggleSlider$Listener = brightnessSliderController2.mListener;
                if (toggleSlider$Listener != null) {
                    ((BrightnessController) toggleSlider$Listener).onChanged(((BrightnessSliderView) brightnessSliderController2.mView).mSlider.getProgress(), brightnessSliderController2.mTracking, false);
                    SeekbarHapticPlugin seekbarHapticPlugin2 = BrightnessSliderController.this.mBrightnessSliderHapticPlugin;
                    if (seekbarHapticPlugin2.isTracking()) {
                        seekbarHapticPlugin2.sliderEventProducer.onStartTracking(true);
                    }
                }
                BrightnessMirrorController brightnessMirrorController = BrightnessSliderController.this.mMirrorController;
                if (brightnessMirrorController != null) {
                    brightnessMirrorController.mBrightnessMirror.setVisibility(0);
                    brightnessMirrorController.mVisibilityCallback.accept(Boolean.TRUE);
                    brightnessMirrorController.mNotificationPanel.setAlpha(0, true);
                    NotificationShadeDepthController notificationShadeDepthController = brightnessMirrorController.mDepthController;
                    int blurRadiusOfRatio = (int) notificationShadeDepthController.blurUtils.blurRadiusOfRatio(1.0f);
                    NotificationShadeDepthController.DepthAnimation depthAnimation = notificationShadeDepthController.brightnessMirrorSpring;
                    if (depthAnimation.pendingRadius != blurRadiusOfRatio) {
                        depthAnimation.pendingRadius = blurRadiusOfRatio;
                        depthAnimation.springAnimation.animateToFinalPosition(blurRadiusOfRatio);
                    }
                    BrightnessSliderController brightnessSliderController3 = BrightnessSliderController.this;
                    BrightnessMirrorController brightnessMirrorController2 = brightnessSliderController3.mMirrorController;
                    View view = brightnessSliderController3.mView;
                    int[] iArr = brightnessMirrorController2.mInt2Cache;
                    view.getLocationInWindow(iArr);
                    int i = iArr[0];
                    int i2 = iArr[1] - brightnessMirrorController2.mBrightnessMirrorBackgroundPadding;
                    brightnessMirrorController2.mBrightnessMirror.setTranslationX(0.0f);
                    brightnessMirrorController2.mBrightnessMirror.setTranslationY(0.0f);
                    brightnessMirrorController2.mBrightnessMirror.getLocationInWindow(iArr);
                    int i3 = iArr[0];
                    int i4 = iArr[1];
                    brightnessMirrorController2.mBrightnessMirror.setTranslationX((i - r3) - i3);
                    brightnessMirrorController2.mBrightnessMirror.setTranslationY(i2 - i4);
                    int measuredWidth = (brightnessMirrorController2.mBrightnessMirrorBackgroundPadding * 2) + view.getMeasuredWidth();
                    if (measuredWidth != brightnessMirrorController2.mLastBrightnessSliderWidth) {
                        ViewGroup.LayoutParams layoutParams = brightnessMirrorController2.mBrightnessMirror.getLayoutParams();
                        layoutParams.width = measuredWidth;
                        brightnessMirrorController2.mBrightnessMirror.setLayoutParams(layoutParams);
                    }
                }
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public final void onStopTrackingTouch(SeekBar seekBar) {
                BrightnessSliderController brightnessSliderController = BrightnessSliderController.this;
                brightnessSliderController.mTracking = false;
                brightnessSliderController.mUiEventLogger.log(BrightnessSliderEvent.BRIGHTNESS_SLIDER_STOPPED_TRACKING_TOUCH);
                BrightnessSliderController brightnessSliderController2 = BrightnessSliderController.this;
                ToggleSlider$Listener toggleSlider$Listener = brightnessSliderController2.mListener;
                if (toggleSlider$Listener != null) {
                    ((BrightnessController) toggleSlider$Listener).onChanged(((BrightnessSliderView) brightnessSliderController2.mView).mSlider.getProgress(), brightnessSliderController2.mTracking, true);
                    SeekbarHapticPlugin seekbarHapticPlugin2 = BrightnessSliderController.this.mBrightnessSliderHapticPlugin;
                    if (seekbarHapticPlugin2.isTracking()) {
                        seekbarHapticPlugin2.sliderEventProducer.onStopTracking(true);
                    }
                }
                BrightnessMirrorController brightnessMirrorController = BrightnessSliderController.this.mMirrorController;
                if (brightnessMirrorController != null) {
                    brightnessMirrorController.mVisibilityCallback.accept(Boolean.FALSE);
                    brightnessMirrorController.mNotificationPanel.setAlpha(255, true);
                    NotificationShadeDepthController.DepthAnimation depthAnimation = brightnessMirrorController.mDepthController.brightnessMirrorSpring;
                    if (depthAnimation.pendingRadius == 0) {
                        return;
                    }
                    depthAnimation.pendingRadius = 0;
                    depthAnimation.springAnimation.animateToFinalPosition(0);
                }
            }
        };
        this.mFalsingManager = falsingManager;
        this.mUiEventLogger = uiEventLogger;
        this.mBrightnessSliderHapticPlugin = seekbarHapticPlugin;
        this.mActivityStarter = activityStarter;
    }

    public final boolean mirrorTouchEvent(MotionEvent motionEvent) {
        if (this.mMirror == null) {
            return ((BrightnessSliderView) this.mView).dispatchTouchEvent(motionEvent);
        }
        MotionEvent copy = motionEvent.copy();
        BrightnessSliderController brightnessSliderController = this.mMirror;
        boolean mirrorTouchEvent = brightnessSliderController != null ? brightnessSliderController.mirrorTouchEvent(copy) : false;
        copy.recycle();
        return mirrorTouchEvent;
    }

    @Override // com.android.systemui.util.ViewController
    public final void onViewAttached() {
        ((BrightnessSliderView) this.mView).mSlider.setOnSeekBarChangeListener(this.mSeekListener);
        BrightnessSliderView brightnessSliderView = (BrightnessSliderView) this.mView;
        brightnessSliderView.mOnInterceptListener = this.mOnInterceptListener;
        if (this.mMirror != null) {
            brightnessSliderView.mListener = new BrightnessSliderController$$ExternalSyntheticLambda0(this);
        }
    }

    @Override // com.android.systemui.util.ViewController
    public final void onViewDetached() {
        ((BrightnessSliderView) this.mView).mSlider.setOnSeekBarChangeListener(null);
        BrightnessSliderView brightnessSliderView = (BrightnessSliderView) this.mView;
        brightnessSliderView.mListener = null;
        brightnessSliderView.mOnInterceptListener = null;
    }

    public final void setMax(int i) {
        ((BrightnessSliderView) this.mView).mSlider.setMax(i);
        BrightnessSliderController brightnessSliderController = this.mMirror;
        if (brightnessSliderController != null) {
            brightnessSliderController.setMax(i);
        }
    }

    public final void setMirror(BrightnessSliderController brightnessSliderController) {
        this.mMirror = brightnessSliderController;
        if (brightnessSliderController == null) {
            ((BrightnessSliderView) this.mView).mListener = null;
            return;
        }
        brightnessSliderController.setMax(((BrightnessSliderView) this.mView).mSlider.getMax());
        this.mMirror.setValue(((BrightnessSliderView) this.mView).mSlider.getProgress());
        ((BrightnessSliderView) this.mView).mListener = new BrightnessSliderController$$ExternalSyntheticLambda0(this);
    }

    public final void setValue(int i) {
        ((BrightnessSliderView) this.mView).mSlider.setProgress(i);
        BrightnessSliderController brightnessSliderController = this.mMirror;
        if (brightnessSliderController != null) {
            brightnessSliderController.setValue(i);
        }
    }
}
