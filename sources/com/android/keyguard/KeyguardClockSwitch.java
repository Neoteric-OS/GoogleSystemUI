package com.android.keyguard;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Property;
import android.view.View;
import android.widget.RelativeLayout;
import androidx.core.content.res.ResourcesCompat;
import com.android.app.animation.Interpolators;
import com.android.keyguard.KeyguardClockFrame;
import com.android.systemui.log.LogBuffer;
import com.android.systemui.log.LogMessageImpl;
import com.android.systemui.log.core.LogLevel;
import com.android.systemui.log.core.LogMessage;
import com.android.wm.shell.R;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public class KeyguardClockSwitch extends RelativeLayout {
    public static final /* synthetic */ int $r8$clinit = 0;
    boolean mAnimateOnLayout;
    boolean mChildrenAreLaidOut;
    AnimatorSet mClockInAnim;
    AnimatorSet mClockOutAnim;
    public int mClockSwitchYAmount;
    public Integer mDisplayedClockSize;
    public int mDrawAlpha;
    public LogBuffer mLogBuffer;
    AnimatorSet mStatusAreaAnim;

    public KeyguardClockSwitch(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mDrawAlpha = 255;
        this.mDisplayedClockSize = null;
        this.mClockInAnim = null;
        this.mClockOutAnim = null;
        this.mStatusAreaAnim = null;
        this.mChildrenAreLaidOut = false;
        this.mAnimateOnLayout = true;
        this.mLogBuffer = null;
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void dispatchDraw(Canvas canvas) {
        int i = this.mDrawAlpha;
        Function1 function1 = new Function1() { // from class: com.android.keyguard.KeyguardClockSwitch$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                super/*android.widget.RelativeLayout*/.dispatchDraw((Canvas) obj);
                return Unit.INSTANCE;
            }
        };
        int i2 = KeyguardClockFrame.$r8$clinit;
        KeyguardClockFrame.Companion.saveCanvasAlpha(this, canvas, i, function1);
    }

    public final void onConfigChanged() {
        this.mClockSwitchYAmount = ((RelativeLayout) this).mContext.getResources().getDimensionPixelSize(R.dimen.keyguard_clock_switch_y_shift);
        ((RelativeLayout) this).mContext.getResources().getDimensionPixelSize(R.dimen.keyguard_smartspace_top_offset);
        float f = ((RelativeLayout) this).mContext.getResources().getConfiguration().fontScale;
        float f2 = ((RelativeLayout) this).mContext.getResources().getDisplayMetrics().density;
        Resources resources = ((RelativeLayout) this).mContext.getResources();
        ThreadLocal threadLocal = ResourcesCompat.sTempTypedValue;
        resources.getFloat(R.dimen.weather_clock_smartspace_scale);
        ((RelativeLayout) this).mContext.getResources().getDimensionPixelSize(R.dimen.weather_clock_smartspace_translateX);
        ((RelativeLayout) this).mContext.getResources().getDimensionPixelSize(R.dimen.weather_clock_smartspace_translateY);
        ((RelativeLayout) this).mContext.getResources().getDimensionPixelSize(R.dimen.status_bar_height);
        Integer num = this.mDisplayedClockSize;
        if (num == null || !this.mChildrenAreLaidOut) {
            return;
        }
        updateClockViews(num.intValue() == 0, false);
        throw null;
    }

    @Override // android.view.View
    public final void onFinishInflate() {
        super.onFinishInflate();
        removeView(findViewById(R.id.lockscreen_clock_view));
        removeView(findViewById(R.id.lockscreen_clock_view_large));
        onConfigChanged();
    }

    @Override // android.widget.RelativeLayout, android.view.ViewGroup, android.view.View
    public final void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        if (z) {
            final int i5 = 1;
            post(new Runnable(this) { // from class: com.android.keyguard.KeyguardClockSwitch$$ExternalSyntheticLambda3
                public final /* synthetic */ KeyguardClockSwitch f$0;

                {
                    this.f$0 = this;
                }

                @Override // java.lang.Runnable
                public final void run() {
                    int i6 = i5;
                    KeyguardClockSwitch keyguardClockSwitch = this.f$0;
                    switch (i6) {
                        case 0:
                            keyguardClockSwitch.updateClockViews(keyguardClockSwitch.mDisplayedClockSize.intValue() == 0, keyguardClockSwitch.mAnimateOnLayout);
                            throw null;
                        default:
                            int i7 = KeyguardClockSwitch.$r8$clinit;
                            keyguardClockSwitch.getClass();
                            return;
                    }
                }
            });
        }
        if (this.mDisplayedClockSize != null && !this.mChildrenAreLaidOut) {
            final int i6 = 0;
            post(new Runnable(this) { // from class: com.android.keyguard.KeyguardClockSwitch$$ExternalSyntheticLambda3
                public final /* synthetic */ KeyguardClockSwitch f$0;

                {
                    this.f$0 = this;
                }

                @Override // java.lang.Runnable
                public final void run() {
                    int i62 = i6;
                    KeyguardClockSwitch keyguardClockSwitch = this.f$0;
                    switch (i62) {
                        case 0:
                            keyguardClockSwitch.updateClockViews(keyguardClockSwitch.mDisplayedClockSize.intValue() == 0, keyguardClockSwitch.mAnimateOnLayout);
                            throw null;
                        default:
                            int i7 = KeyguardClockSwitch.$r8$clinit;
                            keyguardClockSwitch.getClass();
                            return;
                    }
                }
            });
        }
        this.mChildrenAreLaidOut = true;
    }

    @Override // android.view.View
    public final boolean onSetAlpha(int i) {
        this.mDrawAlpha = i;
        return true;
    }

    public final void updateClockViews(boolean z, boolean z2) {
        LogBuffer logBuffer = this.mLogBuffer;
        final KeyguardClockFrame keyguardClockFrame = null;
        if (logBuffer != null) {
            LogMessage obtain = logBuffer.obtain("KeyguardClockSwitch", LogLevel.DEBUG, new KeyguardClockSwitch$$ExternalSyntheticLambda0(), null);
            ((LogMessageImpl) obtain).bool1 = z;
            LogMessageImpl logMessageImpl = (LogMessageImpl) obtain;
            logMessageImpl.bool2 = z2;
            logMessageImpl.bool3 = this.mChildrenAreLaidOut;
            logBuffer.commit(obtain);
        }
        AnimatorSet animatorSet = this.mClockInAnim;
        if (animatorSet != null) {
            animatorSet.cancel();
        }
        AnimatorSet animatorSet2 = this.mClockOutAnim;
        if (animatorSet2 != null) {
            animatorSet2.cancel();
        }
        AnimatorSet animatorSet3 = this.mStatusAreaAnim;
        if (animatorSet3 != null) {
            animatorSet3.cancel();
        }
        this.mClockInAnim = null;
        this.mClockOutAnim = null;
        this.mStatusAreaAnim = null;
        if (z) {
            if (indexOfChild(null) != -1) {
                throw null;
            }
            addView((View) null, 0);
            throw null;
        }
        float f = this.mClockSwitchYAmount * (-1.0f);
        removeView(null);
        if (!z2) {
            throw null;
        }
        AnimatorSet animatorSet4 = new AnimatorSet();
        this.mClockOutAnim = animatorSet4;
        animatorSet4.setDuration(133L);
        this.mClockOutAnim.setInterpolator(Interpolators.LINEAR);
        this.mClockOutAnim.playTogether(ObjectAnimator.ofFloat((Object) null, (Property<Object, Float>) RelativeLayout.ALPHA, 0.0f), ObjectAnimator.ofFloat((Object) null, (Property<Object, Float>) RelativeLayout.TRANSLATION_Y, f));
        this.mClockOutAnim.addListener(new AnimatorListenerAdapter() { // from class: com.android.keyguard.KeyguardClockSwitch.1
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public final void onAnimationEnd(Animator animator) {
                if (KeyguardClockSwitch.this.mClockOutAnim == animator) {
                    keyguardClockFrame.setVisibility(4);
                    KeyguardClockSwitch.this.mClockOutAnim = null;
                }
            }
        });
        throw null;
    }
}
