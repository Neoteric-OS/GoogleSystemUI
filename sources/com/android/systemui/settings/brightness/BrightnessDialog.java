package com.android.systemui.settings.brightness;

import android.app.Activity;
import android.graphics.Insets;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowInsets;
import android.view.WindowMetrics;
import android.widget.FrameLayout;
import com.android.internal.logging.MetricsLogger;
import com.android.systemui.settings.brightness.BrightnessController;
import com.android.systemui.settings.brightness.BrightnessSliderController;
import com.android.systemui.shade.domain.interactor.ShadeInteractor;
import com.android.systemui.shade.domain.interactor.ShadeInteractorImpl;
import com.android.systemui.statusbar.policy.AccessibilityManagerWrapper;
import com.android.systemui.util.concurrency.DelayableExecutor;
import com.android.systemui.util.concurrency.ExecutorImpl;
import com.android.systemui.util.kotlin.JavaAdapterKt;
import com.android.wm.shell.R;
import com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$61;
import java.util.List;
import java.util.function.Consumer;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public class BrightnessDialog extends Activity {
    static final int DIALOG_TIMEOUT_MILLIS = 3000;
    public final AccessibilityManagerWrapper mAccessibilityMgr;
    public BrightnessController mBrightnessController;
    public final BrightnessController.Factory mBrightnessControllerFactory;
    public ExecutorImpl.ExecutionToken mCancelTimeoutRunnable;
    public final DelayableExecutor mMainExecutor;
    public final ShadeInteractor mShadeInteractor;
    public final BrightnessSliderController.Factory mToggleSliderFactory;

    public BrightnessDialog(BrightnessSliderController.Factory factory, BrightnessController.Factory factory2, DelayableExecutor delayableExecutor, AccessibilityManagerWrapper accessibilityManagerWrapper, ShadeInteractor shadeInteractor) {
        this.mToggleSliderFactory = factory;
        this.mBrightnessControllerFactory = factory2;
        this.mMainExecutor = delayableExecutor;
        this.mAccessibilityMgr = accessibilityManagerWrapper;
        this.mShadeInteractor = shadeInteractor;
    }

    @Override // android.app.Activity
    public final void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Window window = getWindow();
        window.setGravity(8388659);
        window.clearFlags(2);
        window.requestFeature(1);
        window.getDecorView();
        window.setLayout(-2, -2);
        getTheme().applyStyle(R.style.Theme_SystemUI_QuickSettings, false);
        setContentView(R.layout.brightness_mirror_container);
        FrameLayout frameLayout = (FrameLayout) findViewById(R.id.brightness_mirror_container);
        frameLayout.setVisibility(0);
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) frameLayout.getLayoutParams();
        final int dimensionPixelSize = getResources().getDimensionPixelSize(R.dimen.notification_side_paddings);
        marginLayoutParams.leftMargin = dimensionPixelSize;
        marginLayoutParams.rightMargin = dimensionPixelSize;
        int dimensionPixelSize2 = getResources().getDimensionPixelSize(R.dimen.notification_guts_option_vertical_padding);
        marginLayoutParams.topMargin = dimensionPixelSize2;
        marginLayoutParams.bottomMargin = dimensionPixelSize2;
        frameLayout.setLayoutParams(marginLayoutParams);
        final Rect rect = new Rect();
        frameLayout.addOnLayoutChangeListener(new View.OnLayoutChangeListener() { // from class: com.android.systemui.settings.brightness.BrightnessDialog$$ExternalSyntheticLambda2
            @Override // android.view.View.OnLayoutChangeListener
            public final void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
                Rect rect2 = rect;
                int i9 = dimensionPixelSize;
                int i10 = BrightnessDialog.DIALOG_TIMEOUT_MILLIS;
                rect2.set(-i9, 0, (i3 - i) + i9, i4 - i2);
                view.setSystemGestureExclusionRects(List.of(rect2));
            }
        });
        BrightnessSliderController create = this.mToggleSliderFactory.create(this, frameLayout);
        create.init$9();
        frameLayout.addView(create.mView, -1, -2);
        this.mBrightnessController = ((DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$61) this.mBrightnessControllerFactory).create(create);
        int i = getResources().getConfiguration().orientation;
        WindowMetrics currentWindowMetrics = getWindowManager().getCurrentWindowMetrics();
        Insets insetsIgnoringVisibility = currentWindowMetrics.getWindowInsets().getInsetsIgnoringVisibility(WindowInsets.Type.navigationBars() | WindowInsets.Type.displayCutout());
        int width = currentWindowMetrics.getBounds().width() - (insetsIgnoringVisibility.right + insetsIgnoringVisibility.left);
        if (i == 2) {
            if (!getIntent().getBooleanExtra("android.intent.extra.BRIGHTNESS_DIALOG_IS_FULL_WIDTH", false)) {
                width /= 2;
            }
            marginLayoutParams.width = width - (dimensionPixelSize * 2);
        } else if (i == 1) {
            marginLayoutParams.width = width - (dimensionPixelSize * 2);
        }
        frameLayout.setLayoutParams(marginLayoutParams);
        ShadeInteractorImpl shadeInteractorImpl = (ShadeInteractorImpl) this.mShadeInteractor;
        if (((Boolean) shadeInteractorImpl.baseShadeInteractor.isQsExpanded().getValue()).booleanValue()) {
            finish();
        }
        View findViewById = findViewById(R.id.brightness_mirror_container);
        if (findViewById != null) {
            JavaAdapterKt.collectFlow(findViewById, shadeInteractorImpl.baseShadeInteractor.isQsExpanded(), new Consumer() { // from class: com.android.systemui.settings.brightness.BrightnessDialog$$ExternalSyntheticLambda1
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    BrightnessDialog brightnessDialog = BrightnessDialog.this;
                    boolean booleanValue = ((Boolean) obj).booleanValue();
                    int i2 = BrightnessDialog.DIALOG_TIMEOUT_MILLIS;
                    if (booleanValue) {
                        brightnessDialog.finish();
                    } else {
                        brightnessDialog.getClass();
                    }
                }
            });
        }
    }

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public final boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i == 25 || i == 24 || i == 164) {
            ExecutorImpl.ExecutionToken executionToken = this.mCancelTimeoutRunnable;
            if (executionToken != null) {
                executionToken.run();
            }
            finish();
        }
        return super.onKeyDown(i, keyEvent);
    }

    @Override // android.app.Activity
    public final void onPause() {
        super.onPause();
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

    @Override // android.app.Activity
    public final void onResume() {
        super.onResume();
        if (getIntent().getBooleanExtra("android.intent.extra.FROM_BRIGHTNESS_KEY", false)) {
            ExecutorImpl.ExecutionToken executionToken = this.mCancelTimeoutRunnable;
            if (executionToken != null) {
                executionToken.run();
            }
            this.mCancelTimeoutRunnable = this.mMainExecutor.executeDelayed(new Runnable() { // from class: com.android.systemui.settings.brightness.BrightnessDialog$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    BrightnessDialog.this.finish();
                }
            }, this.mAccessibilityMgr.mAccessibilityManager.getRecommendedTimeoutMillis(DIALOG_TIMEOUT_MILLIS, 4));
        }
    }

    @Override // android.app.Activity
    public final void onStart() {
        super.onStart();
        BrightnessController brightnessController = this.mBrightnessController;
        BrightnessController.AnonymousClass2 anonymousClass2 = brightnessController.mStartListeningRunnable;
        Handler handler = brightnessController.mBackgroundHandler;
        handler.removeCallbacks(anonymousClass2);
        handler.post(anonymousClass2);
        MetricsLogger.visible(this, 220);
    }

    @Override // android.app.Activity
    public final void onStop() {
        super.onStop();
        MetricsLogger.hidden(this, 220);
        BrightnessController brightnessController = this.mBrightnessController;
        BrightnessController.AnonymousClass2 anonymousClass2 = brightnessController.mStopListeningRunnable;
        Handler handler = brightnessController.mBackgroundHandler;
        handler.removeCallbacks(anonymousClass2);
        handler.post(anonymousClass2);
        brightnessController.mControlValueInitialized = false;
    }
}
