package com.android.systemui.assist.ui;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.metrics.LogMaker;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.PathInterpolator;
import android.widget.FrameLayout;
import com.android.app.viewcapture.ViewCaptureAwareWindowManager;
import com.android.internal.logging.MetricsLogger;
import com.android.systemui.assist.AssistLogger;
import com.android.systemui.assist.AssistManager$UiController;
import com.android.systemui.assist.AssistantSessionEvent;
import com.android.systemui.navigationbar.NavigationBarControllerImpl;
import com.android.wm.shell.R;
import com.google.android.systemui.assist.AssistManagerGoogle;
import com.google.android.systemui.assist.uihints.AssistantInvocationLightsView;
import dagger.Lazy;
import java.util.Locale;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public class DefaultUiController implements AssistManager$UiController {
    public final AssistLogger mAssistLogger;
    public final Lazy mAssistManagerLazy;
    public AssistantInvocationLightsView mInvocationLightsView;
    public final WindowManager.LayoutParams mLayoutParams;
    public final MetricsLogger mMetricsLogger;
    public final FrameLayout mRoot;
    public final ViewCaptureAwareWindowManager mWindowManager;
    public final PathInterpolator mProgressInterpolator = new PathInterpolator(0.83f, 0.0f, 0.84f, 1.0f);
    public boolean mAttached = false;
    public boolean mInvocationInProgress = false;
    public float mLastInvocationProgress = 0.0f;
    public ValueAnimator mInvocationAnimator = new ValueAnimator();

    static {
        String str = Build.TYPE;
        Locale locale = Locale.ROOT;
        if (str.toLowerCase(locale).contains("debug")) {
            return;
        }
        str.toLowerCase(locale).equals("eng");
    }

    public DefaultUiController(Context context, AssistLogger assistLogger, ViewCaptureAwareWindowManager viewCaptureAwareWindowManager, MetricsLogger metricsLogger, Lazy lazy, NavigationBarControllerImpl navigationBarControllerImpl) {
        this.mAssistLogger = assistLogger;
        FrameLayout frameLayout = new FrameLayout(context);
        this.mRoot = frameLayout;
        this.mWindowManager = viewCaptureAwareWindowManager;
        this.mMetricsLogger = metricsLogger;
        this.mAssistManagerLazy = lazy;
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams(-1, -2, 0, 0, 2024, 808, -3);
        this.mLayoutParams = layoutParams;
        layoutParams.privateFlags = 64;
        layoutParams.gravity = 80;
        layoutParams.setFitInsetsTypes(0);
        layoutParams.setTitle("Assist");
        AssistantInvocationLightsView assistantInvocationLightsView = (AssistantInvocationLightsView) LayoutInflater.from(context).inflate(R.layout.invocation_lights, (ViewGroup) frameLayout, false);
        this.mInvocationLightsView = assistantInvocationLightsView;
        assistantInvocationLightsView.mNavigationBarController = navigationBarControllerImpl;
        frameLayout.addView(assistantInvocationLightsView);
    }

    public final void animateInvocationCompletion(final int i) {
        ValueAnimator ofFloat = ValueAnimator.ofFloat(this.mLastInvocationProgress, 1.0f);
        this.mInvocationAnimator = ofFloat;
        ofFloat.setStartDelay(1L);
        this.mInvocationAnimator.setDuration(200L);
        this.mInvocationAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener(i) { // from class: com.android.systemui.assist.ui.DefaultUiController$$ExternalSyntheticLambda0
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                DefaultUiController defaultUiController = DefaultUiController.this;
                defaultUiController.getClass();
                defaultUiController.mInvocationLightsView.onInvocationProgress(defaultUiController.mProgressInterpolator.getInterpolation(((Float) valueAnimator.getAnimatedValue()).floatValue()));
            }
        });
        this.mInvocationAnimator.addListener(new AnimatorListenerAdapter() { // from class: com.android.systemui.assist.ui.DefaultUiController.1
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public final void onAnimationEnd(Animator animator) {
                super.onAnimationEnd(animator);
                DefaultUiController defaultUiController = DefaultUiController.this;
                defaultUiController.mInvocationInProgress = false;
                defaultUiController.mLastInvocationProgress = 0.0f;
                defaultUiController.hide();
            }
        });
        this.mInvocationAnimator.start();
    }

    @Override // com.android.systemui.assist.AssistManager$UiController
    public final void hide() {
        if (this.mAttached) {
            this.mWindowManager.removeViewImmediate(this.mRoot);
            this.mAttached = false;
        }
        if (this.mInvocationAnimator.isRunning()) {
            this.mInvocationAnimator.cancel();
        }
        this.mInvocationLightsView.hide();
        this.mInvocationInProgress = false;
    }

    public final void logInvocationProgressMetrics$1(float f, boolean z, int i) {
        AssistLogger assistLogger = this.mAssistLogger;
        if (!z && f > 0.0f) {
            assistLogger.reportAssistantInvocationEventFromLegacy(i, false, null, null);
            this.mMetricsLogger.write(new LogMaker(1716).setType(4).setSubtype((i << 1) | (((AssistManagerGoogle) this.mAssistManagerLazy.get()).mPhoneStateMonitor.getPhoneState() << 4)));
        }
        ValueAnimator valueAnimator = this.mInvocationAnimator;
        if ((valueAnimator == null || !valueAnimator.isRunning()) && z && f == 0.0f) {
            assistLogger.reportAssistantSessionEvent(AssistantSessionEvent.ASSISTANT_SESSION_INVOCATION_CANCELLED);
            MetricsLogger.action(new LogMaker(1716).setType(5).setSubtype(1));
        }
    }

    @Override // com.android.systemui.assist.AssistManager$UiController
    public final void onGestureCompletion(float f) {
        animateInvocationCompletion(1);
        logInvocationProgressMetrics$1(1.0f, this.mInvocationInProgress, 1);
    }

    @Override // com.android.systemui.assist.AssistManager$UiController
    public final void onInvocationProgress(int i, float f) {
        boolean z = this.mInvocationInProgress;
        if (f == 1.0f) {
            animateInvocationCompletion(i);
        } else if (f == 0.0f) {
            hide();
        } else {
            if (!z) {
                if (!this.mAttached) {
                    this.mWindowManager.addView(this.mRoot, this.mLayoutParams);
                    this.mAttached = true;
                }
                this.mInvocationInProgress = true;
            }
            this.mInvocationLightsView.onInvocationProgress(this.mProgressInterpolator.getInterpolation(f));
        }
        this.mLastInvocationProgress = f;
        logInvocationProgressMetrics$1(f, z, i);
    }
}
