package com.android.systemui.recents;

import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.app.ActivityManager;
import android.app.ActivityTaskManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.graphics.drawable.ColorDrawable;
import android.os.Binder;
import android.os.RemoteException;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.accessibility.AccessibilityManager;
import android.view.animation.DecelerateInterpolator;
import android.widget.FrameLayout;
import com.android.app.viewcapture.ViewCaptureAwareWindowManager;
import com.android.systemui.CoreStartable;
import com.android.systemui.broadcast.BroadcastDispatcher;
import com.android.systemui.navigationbar.NavigationModeController;
import com.android.systemui.settings.UserTracker;
import com.android.systemui.settings.UserTrackerImpl;
import com.android.systemui.statusbar.policy.ConfigurationController;
import com.android.systemui.util.ConvenienceExtensionsKt;
import com.android.systemui.util.leak.RotationUtils;
import com.android.wm.shell.R;
import dagger.Lazy;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class ScreenPinningRequest implements View.OnClickListener, NavigationModeController.ModeChangedListener, CoreStartable, ConfigurationController.ConfigurationListener {
    public final AccessibilityManager mAccessibilityService;
    public final BroadcastDispatcher mBroadcastDispatcher;
    public final Context mContext;
    public int mNavBarMode;
    public final Lazy mNavigationBarControllerLazy;
    public RequestWindowView mRequestWindow;
    public final UserTracker.Callback mUserChangedCallback = new UserTracker.Callback() { // from class: com.android.systemui.recents.ScreenPinningRequest.1
        @Override // com.android.systemui.settings.UserTracker.Callback
        public final void onUserChanged(int i, Context context) {
            ScreenPinningRequest.this.clearPrompt();
        }
    };
    public final UserTracker mUserTracker;
    public final ViewCaptureAwareWindowManager mViewCaptureAwareWindowManager;
    public final WindowManager mWindowManager;
    public int taskId;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class RequestWindowView extends FrameLayout {
        public final ColorDrawable mColor;
        public ViewGroup mLayout;
        public final AnonymousClass3 mReceiver;
        public final boolean mShowCancel;
        public final AnonymousClass2 mUpdateLayoutRunnable;

        /* JADX WARN: Type inference failed for: r0v1, types: [com.android.systemui.recents.ScreenPinningRequest$RequestWindowView$2] */
        /* JADX WARN: Type inference failed for: r0v2, types: [com.android.systemui.recents.ScreenPinningRequest$RequestWindowView$3] */
        public RequestWindowView(Context context, boolean z) {
            super(context);
            ColorDrawable colorDrawable = new ColorDrawable(0);
            this.mColor = colorDrawable;
            this.mUpdateLayoutRunnable = new Runnable() { // from class: com.android.systemui.recents.ScreenPinningRequest.RequestWindowView.2
                @Override // java.lang.Runnable
                public final void run() {
                    ViewGroup viewGroup = RequestWindowView.this.mLayout;
                    if (viewGroup == null || viewGroup.getParent() == null) {
                        return;
                    }
                    RequestWindowView requestWindowView = RequestWindowView.this;
                    ViewGroup viewGroup2 = requestWindowView.mLayout;
                    ScreenPinningRequest screenPinningRequest = ScreenPinningRequest.this;
                    int rotation = RequestWindowView.getRotation(((FrameLayout) requestWindowView).mContext);
                    screenPinningRequest.getClass();
                    viewGroup2.setLayoutParams(new FrameLayout.LayoutParams(-2, -2, rotation == 3 ? 19 : rotation == 1 ? 21 : 81));
                }
            };
            this.mReceiver = new BroadcastReceiver() { // from class: com.android.systemui.recents.ScreenPinningRequest.RequestWindowView.3
                @Override // android.content.BroadcastReceiver
                public final void onReceive(Context context2, Intent intent) {
                    if (intent.getAction().equals("android.intent.action.CONFIGURATION_CHANGED")) {
                        RequestWindowView requestWindowView = RequestWindowView.this;
                        requestWindowView.post(requestWindowView.mUpdateLayoutRunnable);
                    } else if (intent.getAction().equals("android.intent.action.SCREEN_OFF")) {
                        ScreenPinningRequest.this.clearPrompt();
                    }
                }
            };
            setClickable(true);
            setOnClickListener(ScreenPinningRequest.this);
            setBackground(colorDrawable);
            this.mShowCancel = z;
        }

        public static int getRotation(Context context) {
            if (context.getResources().getConfiguration().smallestScreenWidthDp >= 600) {
                return 0;
            }
            return RotationUtils.getRotation(context);
        }

        /* JADX WARN: Removed duplicated region for block: B:26:0x00c8  */
        /* JADX WARN: Removed duplicated region for block: B:29:0x00f7  */
        /* JADX WARN: Removed duplicated region for block: B:32:0x0121  */
        /* JADX WARN: Removed duplicated region for block: B:35:0x0189  */
        /* JADX WARN: Removed duplicated region for block: B:46:0x0231  */
        /* JADX WARN: Removed duplicated region for block: B:49:0x0255  */
        /* JADX WARN: Removed duplicated region for block: B:53:0x0258  */
        /* JADX WARN: Removed duplicated region for block: B:56:0x0125  */
        /* JADX WARN: Removed duplicated region for block: B:66:0x00fc  */
        /* JADX WARN: Removed duplicated region for block: B:70:0x00d6  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final void inflateView(int r11) {
            /*
                Method dump skipped, instructions count: 615
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.recents.ScreenPinningRequest.RequestWindowView.inflateView(int):void");
        }

        @Override // android.view.ViewGroup, android.view.View
        public final void onAttachedToWindow() {
            DisplayMetrics displayMetrics = new DisplayMetrics();
            ScreenPinningRequest.this.mWindowManager.getDefaultDisplay().getMetrics(displayMetrics);
            float f = displayMetrics.density;
            int rotation = getRotation(((FrameLayout) this).mContext);
            inflateView(rotation);
            int color = ((FrameLayout) this).mContext.getColor(R.color.screen_pinning_request_window_bg);
            if (ActivityManager.isHighEndGfx()) {
                this.mLayout.setAlpha(0.0f);
                if (rotation == 3) {
                    this.mLayout.setTranslationX(f * (-96.0f));
                } else if (rotation == 1) {
                    this.mLayout.setTranslationX(f * 96.0f);
                } else {
                    this.mLayout.setTranslationY(f * 96.0f);
                }
                this.mLayout.animate().alpha(1.0f).translationX(0.0f).translationY(0.0f).setDuration(300L).setInterpolator(new DecelerateInterpolator()).start();
                ValueAnimator ofObject = ValueAnimator.ofObject(new ArgbEvaluator(), 0, Integer.valueOf(color));
                ofObject.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.android.systemui.recents.ScreenPinningRequest.RequestWindowView.1
                    @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                    public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                        RequestWindowView.this.mColor.setColor(((Integer) valueAnimator.getAnimatedValue()).intValue());
                    }
                });
                ofObject.setDuration(1000L);
                ofObject.start();
            } else {
                this.mColor.setColor(color);
            }
            IntentFilter intentFilter = new IntentFilter("android.intent.action.CONFIGURATION_CHANGED");
            intentFilter.addAction("android.intent.action.SCREEN_OFF");
            ScreenPinningRequest.this.mBroadcastDispatcher.registerReceiver(this.mReceiver, intentFilter);
            ScreenPinningRequest screenPinningRequest = ScreenPinningRequest.this;
            ((UserTrackerImpl) screenPinningRequest.mUserTracker).addCallback(screenPinningRequest.mUserChangedCallback, ((FrameLayout) this).mContext.getMainExecutor());
        }

        public final void onConfigurationChanged() {
            removeAllViews();
            inflateView(getRotation(((FrameLayout) this).mContext));
        }

        @Override // android.view.ViewGroup, android.view.View
        public final void onDetachedFromWindow() {
            ScreenPinningRequest.this.mBroadcastDispatcher.unregisterReceiver(this.mReceiver);
            ScreenPinningRequest screenPinningRequest = ScreenPinningRequest.this;
            ((UserTrackerImpl) screenPinningRequest.mUserTracker).removeCallback(screenPinningRequest.mUserChangedCallback);
        }
    }

    public ScreenPinningRequest(Context context, NavigationModeController navigationModeController, Lazy lazy, BroadcastDispatcher broadcastDispatcher, UserTracker userTracker, Lazy lazy2) {
        this.mContext = context;
        this.mNavigationBarControllerLazy = lazy;
        this.mAccessibilityService = (AccessibilityManager) context.getSystemService("accessibility");
        WindowManager windowManager = (WindowManager) context.getSystemService("window");
        this.mWindowManager = windowManager;
        this.mViewCaptureAwareWindowManager = new ViewCaptureAwareWindowManager(windowManager, ConvenienceExtensionsKt.toKotlinLazy(lazy2));
        this.mNavBarMode = navigationModeController.addListener(this);
        this.mBroadcastDispatcher = broadcastDispatcher;
        this.mUserTracker = userTracker;
    }

    public final void clearPrompt() {
        RequestWindowView requestWindowView = this.mRequestWindow;
        if (requestWindowView != null) {
            this.mViewCaptureAwareWindowManager.removeView(requestWindowView);
            this.mRequestWindow = null;
        }
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        if (view.getId() == R.id.screen_pinning_ok_button || this.mRequestWindow == view) {
            try {
                ActivityTaskManager.getService().startSystemLockTaskMode(this.taskId);
            } catch (RemoteException unused) {
            }
        }
        clearPrompt();
    }

    @Override // com.android.systemui.statusbar.policy.ConfigurationController.ConfigurationListener
    public final void onConfigChanged(Configuration configuration) {
        RequestWindowView requestWindowView = this.mRequestWindow;
        if (requestWindowView != null) {
            requestWindowView.onConfigurationChanged();
        }
    }

    @Override // com.android.systemui.navigationbar.NavigationModeController.ModeChangedListener
    public final void onNavigationModeChanged(int i) {
        this.mNavBarMode = i;
    }

    public final void showPrompt(int i, boolean z) {
        try {
            clearPrompt();
        } catch (IllegalArgumentException unused) {
        }
        this.taskId = i;
        RequestWindowView requestWindowView = new RequestWindowView(this.mContext, z);
        this.mRequestWindow = requestWindowView;
        requestWindowView.setSystemUiVisibility(256);
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams(-1, -1, 2024, 264, -3);
        layoutParams.token = new Binder();
        layoutParams.privateFlags |= 16;
        layoutParams.setTitle("ScreenPinningConfirmation");
        layoutParams.gravity = 119;
        layoutParams.setFitInsetsTypes(0);
        this.mViewCaptureAwareWindowManager.addView(this.mRequestWindow, layoutParams);
    }

    @Override // com.android.systemui.CoreStartable
    public final void start() {
    }
}
