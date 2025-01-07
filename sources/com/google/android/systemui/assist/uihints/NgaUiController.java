package com.google.android.systemui.assist.uihints;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.Region;
import android.metrics.LogMaker;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.os.PowerManager;
import android.os.SystemClock;
import android.util.MathUtils;
import android.view.ViewTreeObserver;
import android.view.animation.PathInterpolator;
import com.android.internal.logging.MetricsLogger;
import com.android.systemui.DejankUtils;
import com.android.systemui.assist.AssistLogger;
import com.android.systemui.assist.AssistManager$UiController;
import com.android.systemui.assist.ui.EdgeLight;
import com.android.systemui.navigationbar.NavigationBarControllerImpl;
import com.android.systemui.navigationbar.views.NavigationBar;
import com.android.systemui.plugins.statusbar.StatusBarStateController;
import com.android.wm.shell.R;
import com.google.android.systemui.assist.AssistManagerGoogle;
import dagger.Lazy;
import java.util.Locale;
import java.util.Optional;
import java.util.function.Consumer;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class NgaUiController implements AssistManager$UiController, ViewTreeObserver.OnComputeInternalInsetsListener, StatusBarStateController.StateListener {
    public static final boolean VERBOSE;
    public static final PathInterpolator mProgressInterpolator;
    public final AssistLogger mAssistLogger;
    public final Lazy mAssistManager;
    public final Context mContext;
    public final FlingVelocityWrapper mFlingVelocity;
    public final AssistantInvocationLightsView mInvocationLightsView;
    public final LightnessProvider mLightnessProvider;
    public final NavBarFadeController mNavBarFadeController;
    public final PromptView mPromptView;
    public final ScrimController mScrimController;
    public final OverlayUiHost mUiHost;
    public final PowerManager.WakeLock mWakeLock;
    public final Handler mUiHandler = new Handler(Looper.getMainLooper());
    public boolean mHasDarkBackground = false;
    public boolean mIsMonitoringColor = false;
    public boolean mInvocationInProgress = false;
    public boolean mShowingAssistUi = false;
    public boolean mShouldKeepWakeLock = false;
    public long mLastInvocationStartTime = 0;
    public long mColorMonitoringStart = 0;

    static {
        String str = Build.TYPE;
        Locale locale = Locale.ROOT;
        VERBOSE = str.toLowerCase(locale).contains("debug") || str.toLowerCase(locale).equals("eng");
        mProgressInterpolator = new PathInterpolator(0.83f, 0.0f, 0.84f, 1.0f);
    }

    public NgaUiController(Context context, TimeoutManager timeoutManager, TouchInsideHandler touchInsideHandler, OverlayUiHost overlayUiHost, ScrimController scrimController, LightnessProvider lightnessProvider, StatusBarStateController statusBarStateController, Lazy lazy, FlingVelocityWrapper flingVelocityWrapper, NavBarFadeController navBarFadeController, AssistLogger assistLogger) {
        NavigationBarControllerImpl navigationBarControllerImpl;
        NavigationBar defaultNavigationBar;
        this.mContext = context;
        this.mAssistLogger = assistLogger;
        this.mUiHost = overlayUiHost;
        this.mScrimController = scrimController;
        this.mLightnessProvider = lightnessProvider;
        this.mAssistManager = lazy;
        this.mFlingVelocity = flingVelocityWrapper;
        this.mNavBarFadeController = navBarFadeController;
        lightnessProvider.mListener = new NgaUiController$$ExternalSyntheticLambda2(this);
        touchInsideHandler.mFallback = new NgaUiController$$ExternalSyntheticLambda1(this);
        this.mWakeLock = ((PowerManager) context.getSystemService("power")).newWakeLock(805306378, "Assist (NGA)");
        scrimController.mVisibilityListener = new NgaUiController$$ExternalSyntheticLambda2(this);
        AssistUIView assistUIView = overlayUiHost.mRoot;
        AssistantInvocationLightsView assistantInvocationLightsView = (AssistantInvocationLightsView) assistUIView.findViewById(R.id.invocation_lights);
        this.mInvocationLightsView = assistantInvocationLightsView;
        int i = assistantInvocationLightsView.mColorBlue;
        int i2 = assistantInvocationLightsView.mColorRed;
        int i3 = assistantInvocationLightsView.mColorYellow;
        int i4 = assistantInvocationLightsView.mColorGreen;
        assistantInvocationLightsView.mUseNavBarColor = false;
        if (assistantInvocationLightsView.mRegistered && (navigationBarControllerImpl = assistantInvocationLightsView.mNavigationBarController) != null && (defaultNavigationBar = navigationBarControllerImpl.getDefaultNavigationBar()) != null) {
            defaultNavigationBar.mNavigationBarTransitions.mDarkIntensityListeners.remove(assistantInvocationLightsView);
            assistantInvocationLightsView.mRegistered = false;
        }
        ((EdgeLight) assistantInvocationLightsView.mAssistInvocationLights.get(0)).setColor(i);
        ((EdgeLight) assistantInvocationLightsView.mAssistInvocationLights.get(1)).setColor(i2);
        ((EdgeLight) assistantInvocationLightsView.mAssistInvocationLights.get(2)).setColor(i3);
        ((EdgeLight) assistantInvocationLightsView.mAssistInvocationLights.get(3)).setColor(i4);
        PromptView promptView = (PromptView) assistUIView.findViewById(R.id.prompt);
        this.mPromptView = promptView;
        boolean z = this.mHasDarkBackground;
        if (z != promptView.mHasDarkBackground) {
            promptView.setTextColor(z ? promptView.mTextColorDark : promptView.mTextColorLight);
            promptView.mHasDarkBackground = z;
        }
        statusBarStateController.addCallback(this);
        refresh$1();
        timeoutManager.getClass();
    }

    @Override // com.android.systemui.assist.AssistManager$UiController
    public final void hide() {
        this.mInvocationInProgress = false;
        PromptView promptView = this.mPromptView;
        promptView.mEnabled = false;
        promptView.setVisibility(8);
        refresh$1();
    }

    public final void logInvocationProgressMetrics(float f, boolean z, int i) {
        if (z || f <= 0.0f) {
            return;
        }
        this.mAssistLogger.reportAssistantInvocationEventFromLegacy(i, false, null, null);
        MetricsLogger.action(new LogMaker(1716).setType(4).setSubtype((((AssistManagerGoogle) this.mAssistManager.get()).mPhoneStateMonitor.getPhoneState() << 4) | (i << 1)));
    }

    public final void onComputeInternalInsets(ViewTreeObserver.InternalInsetsInfo internalInsetsInfo) {
        Optional empty;
        internalInsetsInfo.setTouchableInsets(3);
        Region region = new Region();
        final Region region2 = new Region();
        ScrimController scrimController = this.mScrimController;
        if (scrimController.mScrimView.getVisibility() == 0) {
            Rect rect = new Rect();
            scrimController.mScrimView.getHitRect(rect);
            rect.top = rect.bottom - scrimController.mScrimView.getResources().getDimensionPixelSize(R.dimen.scrim_touchable_height);
            empty = Optional.of(new Region(rect));
        } else {
            empty = Optional.empty();
        }
        empty.ifPresent(new Consumer() { // from class: com.google.android.systemui.assist.uihints.NgaUiController$$ExternalSyntheticLambda4
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                region2.op((Region) obj, Region.Op.UNION);
            }
        });
        region.op(region2, Region.Op.UNION);
        internalInsetsInfo.touchableRegion.set(region);
    }

    @Override // com.android.systemui.plugins.statusbar.StatusBarStateController.StateListener
    public final void onDozingChanged(final boolean z) {
        if (Looper.myLooper() != this.mUiHandler.getLooper()) {
            this.mUiHandler.post(new Runnable() { // from class: com.google.android.systemui.assist.uihints.NgaUiController$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    NgaUiController.this.onDozingChanged(z);
                }
            });
            return;
        }
        ScrimController scrimController = this.mScrimController;
        scrimController.mIsDozing = z;
        scrimController.refresh();
        if (z && this.mShowingAssistUi) {
            DejankUtils.whitelistIpcs(new NgaUiController$$ExternalSyntheticLambda1(this));
        }
    }

    @Override // com.android.systemui.assist.AssistManager$UiController
    public final void onGestureCompletion(float f) {
        this.mFlingVelocity.getClass();
        setProgress(1, 0.0f);
        this.mInvocationInProgress = false;
        this.mInvocationLightsView.hide();
        ScrimController scrimController = this.mScrimController;
        scrimController.getClass();
        float constrain = MathUtils.constrain(0.0f, 0.0f, 1.0f);
        if (scrimController.mInvocationProgress != constrain) {
            scrimController.mInvocationProgress = constrain;
            scrimController.refresh();
        }
        refresh$1();
        logInvocationProgressMetrics(1.0f, this.mInvocationInProgress, 1);
    }

    @Override // com.android.systemui.assist.AssistManager$UiController
    public final void onInvocationProgress(int i, float f) {
        boolean z = this.mInvocationInProgress;
        if (f < 1.0f) {
            if (!z && f > 0.0f) {
                this.mLastInvocationStartTime = SystemClock.uptimeMillis();
            }
            boolean z2 = f > 0.0f && f < 1.0f;
            this.mInvocationInProgress = z2;
            if (!z2) {
                PromptView promptView = this.mPromptView;
                promptView.mEnabled = false;
                promptView.setVisibility(8);
            } else if (f < 0.9f && SystemClock.uptimeMillis() - this.mLastInvocationStartTime > 200) {
                this.mPromptView.mEnabled = true;
            }
            setProgress(i, i == 2 ? 0.95f * f : mProgressInterpolator.getInterpolation(0.8f * f));
        }
        logInvocationProgressMetrics(f, z, i);
    }

    /* JADX WARN: Code restructure failed: missing block: B:17:0x0042, code lost:
    
        if ((r4.mScrimView.getViewRootImpl() == null ? null : r4.mScrimView.getViewRootImpl().getSurfaceControl()) == null) goto L40;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void refresh$1() {
        /*
            Method dump skipped, instructions count: 383
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.systemui.assist.uihints.NgaUiController.refresh$1():void");
    }

    public final void setProgress(int i, float f) {
        this.mInvocationLightsView.onInvocationProgress(f);
        ScrimController scrimController = this.mScrimController;
        scrimController.getClass();
        float constrain = MathUtils.constrain(f, 0.0f, 1.0f);
        if (scrimController.mInvocationProgress != constrain) {
            scrimController.mInvocationProgress = constrain;
            scrimController.refresh();
        }
        PromptView promptView = this.mPromptView;
        if (f > 1.0f) {
            promptView.getClass();
        } else if (f == 0.0f) {
            promptView.setVisibility(8);
            promptView.setAlpha(0.0f);
            promptView.setTranslationY(0.0f);
            promptView.mLastInvocationType = 0;
        } else if (promptView.mEnabled) {
            if (i != 1) {
                if (i != 2) {
                    promptView.mLastInvocationType = 0;
                    promptView.setText("");
                } else if (promptView.mLastInvocationType != i) {
                    promptView.mLastInvocationType = i;
                    promptView.setText(promptView.mSqueezeString);
                    promptView.announceForAccessibility(promptView.mSqueezeString);
                }
            } else if (promptView.mLastInvocationType != i) {
                promptView.mLastInvocationType = i;
                promptView.setText(promptView.mHandleString);
                promptView.announceForAccessibility(promptView.mHandleString);
            }
            promptView.setVisibility(0);
            promptView.setTranslationY((-promptView.mRiseDistance) * f);
            if (i != 2 && f > 0.8f) {
                promptView.setAlpha(0.0f);
            } else if (f > 0.32000002f) {
                promptView.setAlpha(1.0f);
            } else {
                promptView.setAlpha(promptView.mDecelerateInterpolator.getInterpolation(f / 0.32000002f));
            }
        }
        refresh$1();
    }
}
