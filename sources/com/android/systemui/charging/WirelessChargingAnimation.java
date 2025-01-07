package com.android.systemui.charging;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.util.Slog;
import android.util.TypedValue;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.view.ViewParent;
import android.view.WindowManager;
import android.view.animation.Interpolator;
import android.view.animation.PathInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import com.android.app.animation.Interpolators;
import com.android.app.viewcapture.ViewCaptureAwareWindowManager;
import com.android.internal.logging.UiEventLogger;
import com.android.settingslib.Utils;
import com.android.systemui.shade.NotificationShadeWindowControllerImpl;
import com.android.systemui.shared.recents.utilities.Utilities;
import com.android.systemui.statusbar.phone.CentralSurfacesImpl;
import com.android.systemui.surfaceeffects.ripple.RippleShader;
import com.android.systemui.surfaceeffects.ripple.RippleView;
import com.android.wm.shell.R;
import java.text.NumberFormat;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class WirelessChargingAnimation {
    public static final boolean DEBUG = Log.isLoggable("WirelessChargingView", 3);
    public static WirelessChargingView mPreviousWirelessChargingView;
    public final WirelessChargingView mCurrentWirelessChargingView;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class WirelessChargingView {
        public final CentralSurfacesImpl.AnonymousClass4 mCallback;
        public final AnonymousClass1 mHandler;
        public WirelessChargingLayout mNextView;
        public final WindowManager.LayoutParams mParams;
        public final UiEventLogger mUiEventLogger;
        public WirelessChargingLayout mView;
        public final ViewCaptureAwareWindowManager mWM;

        /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
        /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        final class WirelessChargingRippleEvent implements UiEventLogger.UiEventEnum {
            public static final /* synthetic */ WirelessChargingRippleEvent[] $VALUES;
            public static final WirelessChargingRippleEvent WIRELESS_RIPPLE_PLAYED;
            private final int mInt = 830;

            static {
                WirelessChargingRippleEvent wirelessChargingRippleEvent = new WirelessChargingRippleEvent();
                WIRELESS_RIPPLE_PLAYED = wirelessChargingRippleEvent;
                $VALUES = new WirelessChargingRippleEvent[]{wirelessChargingRippleEvent};
            }

            public static WirelessChargingRippleEvent valueOf(String str) {
                return (WirelessChargingRippleEvent) Enum.valueOf(WirelessChargingRippleEvent.class, str);
            }

            public static WirelessChargingRippleEvent[] values() {
                return (WirelessChargingRippleEvent[]) $VALUES.clone();
            }

            public final int getId() {
                return this.mInt;
            }
        }

        /* JADX WARN: Type inference failed for: r2v10, types: [com.android.systemui.charging.WirelessChargingAnimation$WirelessChargingView$1] */
        public WirelessChargingView(Context context, int i, int i2, CentralSurfacesImpl.AnonymousClass4 anonymousClass4, UiEventLogger uiEventLogger, ViewCaptureAwareWindowManager viewCaptureAwareWindowManager) {
            long j;
            RippleShader.RippleShape rippleShape = RippleShader.RippleShape.CIRCLE;
            WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
            this.mParams = layoutParams;
            this.mCallback = anonymousClass4;
            WirelessChargingLayout wirelessChargingLayout = new WirelessChargingLayout(context);
            boolean z = i != -1;
            FrameLayout.inflate(new ContextThemeWrapper(context, R.style.ChargingAnim_WallpaperBackground), R.layout.wireless_charging_layout, wirelessChargingLayout);
            TextView textView = (TextView) wirelessChargingLayout.findViewById(R.id.wireless_charging_percentage);
            if (i2 != -1) {
                textView.setText(NumberFormat.getPercentInstance().format(i2 / 100.0f));
                textView.setAlpha(0.0f);
            }
            long integer = context.getResources().getInteger(R.integer.wireless_charging_fade_offset);
            long integer2 = context.getResources().getInteger(R.integer.wireless_charging_fade_duration);
            float f = context.getResources().getFloat(R.dimen.wireless_charging_anim_battery_level_text_size_start);
            float f2 = context.getResources().getFloat(R.dimen.wireless_charging_anim_battery_level_text_size_end) * (z ? 0.75f : 1.0f);
            ObjectAnimator ofFloat = ObjectAnimator.ofFloat(textView, "textSize", f, f2);
            ofFloat.setInterpolator(new PathInterpolator(0.0f, 0.0f, 0.0f, 1.0f));
            ofFloat.setDuration(context.getResources().getInteger(R.integer.wireless_charging_battery_level_text_scale_animation_duration));
            ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(textView, "alpha", 0.0f, 1.0f);
            Interpolator interpolator = Interpolators.LINEAR;
            ofFloat2.setInterpolator(interpolator);
            ofFloat2.setDuration(context.getResources().getInteger(R.integer.wireless_charging_battery_level_text_opacity_duration));
            ofFloat2.setStartDelay(context.getResources().getInteger(R.integer.wireless_charging_anim_opacity_offset));
            ObjectAnimator ofFloat3 = ObjectAnimator.ofFloat(textView, "alpha", 1.0f, 0.0f);
            ofFloat3.setDuration(integer2);
            ofFloat3.setInterpolator(interpolator);
            ofFloat3.setStartDelay(integer);
            AnimatorSet animatorSet = new AnimatorSet();
            animatorSet.playTogether(ofFloat, ofFloat2, ofFloat3);
            if (Utilities.isLargeScreen(context)) {
                j = integer;
            } else {
                ObjectAnimator ofArgb = ObjectAnimator.ofArgb(wirelessChargingLayout, "backgroundColor", 0, 1275068416);
                ofArgb.setDuration(300L);
                ofArgb.setInterpolator(interpolator);
                wirelessChargingLayout = wirelessChargingLayout;
                ObjectAnimator ofArgb2 = ObjectAnimator.ofArgb(wirelessChargingLayout, "backgroundColor", 1275068416, 0);
                ofArgb2.setDuration(300L);
                ofArgb2.setInterpolator(interpolator);
                ofArgb2.setStartDelay(1500 - 300);
                AnimatorSet animatorSet2 = new AnimatorSet();
                j = integer;
                animatorSet2.playTogether(ofArgb, ofArgb2);
                animatorSet2.start();
            }
            RippleView rippleView = (RippleView) wirelessChargingLayout.findViewById(R.id.wireless_charging_ripple);
            wirelessChargingLayout.mRippleView = rippleView;
            rippleView.setupShader(rippleShape);
            int defaultColor = Utils.getColorAttr(android.R.attr.colorAccent, wirelessChargingLayout.mRippleView.getContext()).getDefaultColor();
            RippleView rippleView2 = wirelessChargingLayout.mRippleView;
            RippleShader.RippleShape rippleShape2 = rippleView2.rippleShape;
            if ((rippleShape2 == null ? null : rippleShape2) == RippleShader.RippleShape.ROUNDED_BOX) {
                rippleView2.duration = 3000L;
                RippleShader rippleShader = rippleView2.rippleShader;
                (rippleShader == null ? null : rippleShader).setFloatUniform("in_sparkle_strength", 0.22f);
                wirelessChargingLayout.mRippleView.setColor(defaultColor, 102);
                RippleShader rippleShader2 = wirelessChargingLayout.mRippleView.rippleShader;
                RippleShader.FadeParams fadeParams = (rippleShader2 == null ? null : rippleShader2).baseRingFadeParams;
                fadeParams.getClass();
                fadeParams.fadeInEnd = 0.0f;
                fadeParams.fadeOutStart = 0.2f;
                fadeParams.fadeOutEnd = 0.47f;
                RippleShader rippleShader3 = wirelessChargingLayout.mRippleView.rippleShader;
                RippleShader.FadeParams fadeParams2 = (rippleShader3 == null ? null : rippleShader3).sparkleRingFadeParams;
                fadeParams2.getClass();
                fadeParams2.fadeInEnd = 0.0f;
                fadeParams2.fadeOutStart = 0.2f;
                fadeParams2.fadeOutEnd = 1.0f;
                RippleShader rippleShader4 = wirelessChargingLayout.mRippleView.rippleShader;
                RippleShader.FadeParams fadeParams3 = (rippleShader4 == null ? null : rippleShader4).centerFillFadeParams;
                fadeParams3.getClass();
                fadeParams3.fadeInEnd = 0.0f;
                fadeParams3.fadeOutStart = 0.0f;
                fadeParams3.fadeOutEnd = 0.2f;
                RippleView rippleView3 = wirelessChargingLayout.mRippleView;
                RippleShader rippleShader5 = rippleView3.rippleShader;
                (rippleShader5 == null ? null : rippleShader5).getClass();
                RippleShader rippleShader6 = rippleView3.rippleShader;
                (rippleShader6 != null ? rippleShader6 : null).getClass();
            } else {
                rippleView2.duration = 1500L;
                rippleView2.setColor(defaultColor, 115);
            }
            wirelessChargingLayout.mRippleView.addOnAttachStateChangeListener(new View.OnAttachStateChangeListener() { // from class: com.android.systemui.charging.WirelessChargingLayout.1
                public AnonymousClass1() {
                }

                @Override // android.view.View.OnAttachStateChangeListener
                public final void onViewAttachedToWindow(View view) {
                    WirelessChargingLayout.this.mRippleView.startRipple(null);
                    WirelessChargingLayout.this.mRippleView.removeOnAttachStateChangeListener(this);
                }

                @Override // android.view.View.OnAttachStateChangeListener
                public final void onViewDetachedFromWindow(View view) {
                }
            });
            if (z) {
                TextView textView2 = (TextView) wirelessChargingLayout.findViewById(R.id.reverse_wireless_charging_percentage);
                textView2.setVisibility(0);
                textView2.setText(NumberFormat.getPercentInstance().format(i / 100.0f));
                textView2.setAlpha(0.0f);
                ObjectAnimator ofFloat4 = ObjectAnimator.ofFloat(textView2, "textSize", f, f2);
                ofFloat4.setInterpolator(new PathInterpolator(0.0f, 0.0f, 0.0f, 1.0f));
                ofFloat4.setDuration(context.getResources().getInteger(R.integer.wireless_charging_battery_level_text_scale_animation_duration));
                ObjectAnimator ofFloat5 = ObjectAnimator.ofFloat(textView2, "alpha", 0.0f, 1.0f);
                ofFloat5.setInterpolator(interpolator);
                ofFloat5.setDuration(context.getResources().getInteger(R.integer.wireless_charging_battery_level_text_opacity_duration));
                ofFloat5.setStartDelay(context.getResources().getInteger(R.integer.wireless_charging_anim_opacity_offset));
                ObjectAnimator ofFloat6 = ObjectAnimator.ofFloat(textView2, "alpha", 1.0f, 0.0f);
                ofFloat6.setDuration(integer2);
                ofFloat6.setInterpolator(interpolator);
                long j2 = j;
                ofFloat6.setStartDelay(j2);
                AnimatorSet animatorSet3 = new AnimatorSet();
                animatorSet3.playTogether(ofFloat4, ofFloat5, ofFloat6);
                ImageView imageView = (ImageView) wirelessChargingLayout.findViewById(R.id.reverse_wireless_charging_icon);
                imageView.setVisibility(0);
                int round = Math.round(TypedValue.applyDimension(1, f2, wirelessChargingLayout.getResources().getDisplayMetrics()));
                imageView.setPadding(round, 0, round, 0);
                ObjectAnimator ofFloat7 = ObjectAnimator.ofFloat(imageView, "alpha", 0.0f, 1.0f);
                ofFloat7.setInterpolator(interpolator);
                ofFloat7.setDuration(context.getResources().getInteger(R.integer.wireless_charging_battery_level_text_opacity_duration));
                ofFloat7.setStartDelay(context.getResources().getInteger(R.integer.wireless_charging_anim_opacity_offset));
                ObjectAnimator ofFloat8 = ObjectAnimator.ofFloat(imageView, "alpha", 1.0f, 0.0f);
                ofFloat8.setDuration(integer2);
                ofFloat8.setInterpolator(interpolator);
                ofFloat8.setStartDelay(j2);
                AnimatorSet animatorSet4 = new AnimatorSet();
                animatorSet4.playTogether(ofFloat7, ofFloat8);
                animatorSet.start();
                animatorSet3.start();
                animatorSet4.start();
            } else {
                animatorSet.start();
            }
            this.mNextView = wirelessChargingLayout;
            this.mUiEventLogger = uiEventLogger;
            this.mWM = viewCaptureAwareWindowManager;
            layoutParams.height = -1;
            layoutParams.width = -1;
            layoutParams.format = -3;
            layoutParams.type = 2009;
            layoutParams.setTitle("Charging Animation");
            layoutParams.layoutInDisplayCutoutMode = 3;
            layoutParams.setFitInsetsTypes(0);
            layoutParams.flags = 24;
            layoutParams.setTrustedOverlay();
            Looper myLooper = Looper.myLooper();
            if (myLooper == null) {
                throw new RuntimeException("Can't display wireless animation on a thread that has not called Looper.prepare()");
            }
            this.mHandler = new Handler(myLooper) { // from class: com.android.systemui.charging.WirelessChargingAnimation.WirelessChargingView.1
                @Override // android.os.Handler
                public final void handleMessage(Message message) {
                    int i3 = message.what;
                    WirelessChargingView wirelessChargingView = WirelessChargingView.this;
                    if (i3 != 0) {
                        if (i3 != 1) {
                            return;
                        }
                        wirelessChargingView.handleHide();
                        wirelessChargingView.mNextView = null;
                        return;
                    }
                    wirelessChargingView.getClass();
                    boolean z2 = WirelessChargingAnimation.DEBUG;
                    if (z2) {
                        Slog.d("WirelessChargingView", "HANDLE SHOW: " + wirelessChargingView + " mView=" + wirelessChargingView.mView + " mNextView=" + wirelessChargingView.mNextView);
                    }
                    if (wirelessChargingView.mView != wirelessChargingView.mNextView) {
                        wirelessChargingView.handleHide();
                        WirelessChargingLayout wirelessChargingLayout2 = wirelessChargingView.mNextView;
                        wirelessChargingView.mView = wirelessChargingLayout2;
                        Context applicationContext = wirelessChargingLayout2.getContext().getApplicationContext();
                        String opPackageName = wirelessChargingView.mView.getContext().getOpPackageName();
                        if (applicationContext == null) {
                            wirelessChargingView.mView.getContext();
                        }
                        WindowManager.LayoutParams layoutParams2 = wirelessChargingView.mParams;
                        layoutParams2.packageName = opPackageName;
                        layoutParams2.hideTimeoutMilliseconds = 1500L;
                        ViewParent parent = wirelessChargingView.mView.getParent();
                        ViewCaptureAwareWindowManager viewCaptureAwareWindowManager2 = wirelessChargingView.mWM;
                        if (parent != null) {
                            if (z2) {
                                Slog.d("WirelessChargingView", "REMOVE! " + wirelessChargingView.mView + " in " + wirelessChargingView);
                            }
                            viewCaptureAwareWindowManager2.removeView(wirelessChargingView.mView);
                        }
                        if (z2) {
                            Slog.d("WirelessChargingView", "ADD! " + wirelessChargingView.mView + " in " + wirelessChargingView);
                        }
                        try {
                            CentralSurfacesImpl.AnonymousClass4 anonymousClass42 = wirelessChargingView.mCallback;
                            if (anonymousClass42 != null) {
                                ((NotificationShadeWindowControllerImpl) CentralSurfacesImpl.this.mNotificationShadeWindowController).setRequestTopUi("CentralSurfaces", true);
                            }
                            viewCaptureAwareWindowManager2.addView(wirelessChargingView.mView, wirelessChargingView.mParams);
                            wirelessChargingView.mUiEventLogger.log(WirelessChargingRippleEvent.WIRELESS_RIPPLE_PLAYED);
                        } catch (WindowManager.BadTokenException e) {
                            Slog.d("WirelessChargingView", "Unable to add wireless charging view. " + e);
                        }
                    }
                }
            };
        }

        public final void handleHide() {
            boolean z = WirelessChargingAnimation.DEBUG;
            if (z) {
                Slog.d("WirelessChargingView", "HANDLE HIDE: " + this + " mView=" + this.mView);
            }
            WirelessChargingLayout wirelessChargingLayout = this.mView;
            if (wirelessChargingLayout != null) {
                if (wirelessChargingLayout.getParent() != null) {
                    if (z) {
                        Slog.d("WirelessChargingView", "REMOVE! " + this.mView + " in " + this);
                    }
                    CentralSurfacesImpl.AnonymousClass4 anonymousClass4 = this.mCallback;
                    if (anonymousClass4 != null) {
                        ((NotificationShadeWindowControllerImpl) CentralSurfacesImpl.this.mNotificationShadeWindowController).setRequestTopUi("CentralSurfaces", false);
                    }
                    this.mWM.removeViewImmediate(this.mView);
                }
                this.mView = null;
            }
        }

        public final void hide(long j) {
            AnonymousClass1 anonymousClass1 = this.mHandler;
            anonymousClass1.removeMessages(1);
            if (WirelessChargingAnimation.DEBUG) {
                Slog.d("WirelessChargingView", "HIDE: " + this);
            }
            anonymousClass1.sendMessageDelayed(Message.obtain(anonymousClass1, 1), j);
        }
    }

    public WirelessChargingAnimation(Context context, int i, int i2, CentralSurfacesImpl.AnonymousClass4 anonymousClass4, UiEventLogger uiEventLogger, ViewCaptureAwareWindowManager viewCaptureAwareWindowManager) {
        RippleShader.RippleShape rippleShape = RippleShader.RippleShape.CIRCLE;
        this.mCurrentWirelessChargingView = new WirelessChargingView(context, i, i2, anonymousClass4, uiEventLogger, viewCaptureAwareWindowManager);
    }
}
