package com.android.systemui.clipboardoverlay;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.RemoteAction;
import android.content.BroadcastReceiver;
import android.content.ClipData;
import android.content.ClipDescription;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.graphics.Region;
import android.hardware.input.InputManager;
import android.net.Uri;
import android.os.Looper;
import android.provider.DeviceConfig;
import android.text.TextUtils;
import android.view.InputEvent;
import android.view.InputEventReceiver;
import android.view.InputMonitor;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.WindowInsets;
import android.view.animation.LinearInterpolator;
import android.view.animation.PathInterpolator;
import android.view.textclassifier.TextLinks;
import com.android.internal.logging.UiEventLogger;
import com.android.systemui.broadcast.BroadcastDispatcher;
import com.android.systemui.broadcast.BroadcastSender;
import com.android.systemui.clipboardoverlay.ClipboardModel;
import com.android.systemui.clipboardoverlay.ClipboardOverlayController;
import com.android.systemui.clipboardoverlay.ClipboardOverlayView;
import com.android.systemui.screenshot.FloatingWindowUtil;
import com.android.systemui.screenshot.TimeoutHandler;
import com.android.wm.shell.R;
import java.util.ArrayList;
import java.util.Optional;
import java.util.concurrent.Executor;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Stream;
import kotlinx.coroutines.BuildersKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class ClipboardOverlayController implements ClipboardOverlayView.ClipboardOverlayCallbacks {
    public final Executor mBgExecutor;
    public final BroadcastDispatcher mBroadcastDispatcher;
    public final AnonymousClass1 mClipboardCallbacks;
    public final ClipboardImageLoader mClipboardImageLoader;
    public final ClipboardLogger mClipboardLogger;
    public ClipboardModel mClipboardModel;
    public final ClipboardOverlayUtils mClipboardUtils;
    public AnonymousClass2 mCloseDialogsReceiver;
    public final Context mContext;
    public Animator mEnterAnimator;
    public Animator mExitAnimator;
    public AnonymousClass5 mInputEventReceiver;
    public InputMonitor mInputMonitor;
    public boolean mIsMinimized;
    public Runnable mOnPreviewTapped;
    public ClipboardOverlayController$$ExternalSyntheticLambda0 mOnRemoteCopyTapped;
    public ClipboardListener$$ExternalSyntheticLambda0 mOnSessionCompleteListener;
    public Runnable mOnShareTapped;
    public ClipboardOverlayController$$ExternalSyntheticLambda9 mOnUiUpdate;
    public AnonymousClass2 mScreenshotReceiver;
    public boolean mShowingUi;
    public final TimeoutHandler mTimeoutHandler;
    public final ClipboardOverlayView mView;
    public final ClipboardOverlayWindow mWindow;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.clipboardoverlay.ClipboardOverlayController$4, reason: invalid class name */
    public final class AnonymousClass4 extends AnimatorListenerAdapter {
        public final /* synthetic */ int $r8$classId;
        public final /* synthetic */ ClipboardOverlayController this$0;

        public /* synthetic */ AnonymousClass4(ClipboardOverlayController clipboardOverlayController, int i) {
            this.$r8$classId = i;
            this.this$0 = clipboardOverlayController;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public final void onAnimationEnd(Animator animator) {
            switch (this.$r8$classId) {
                case 0:
                    super.onAnimationEnd(animator);
                    ClipboardOverlayController clipboardOverlayController = this.this$0;
                    if (clipboardOverlayController.mIsMinimized) {
                        ClipboardLogger clipboardLogger = clipboardOverlayController.mClipboardLogger;
                        clipboardLogger.mUiEventLogger.log(ClipboardOverlayEvent.CLIPBOARD_OVERLAY_EXPANDED_FROM_MINIMIZED, 0, clipboardLogger.mClipSource);
                        this.this$0.mIsMinimized = false;
                    }
                    this.this$0.setExpandedView(new ClipboardOverlayController$$ExternalSyntheticLambda9(1, this));
                    break;
                default:
                    super.onAnimationEnd(animator);
                    ClipboardOverlayController clipboardOverlayController2 = this.this$0;
                    if (clipboardOverlayController2.mIsMinimized && clipboardOverlayController2.mWindow.mWindowManager.getCurrentWindowMetrics().getWindowInsets().getInsets(WindowInsets.Type.ime()).bottom <= 0) {
                        this.this$0.animateFromMinimized();
                    }
                    ClipboardOverlayController$$ExternalSyntheticLambda9 clipboardOverlayController$$ExternalSyntheticLambda9 = this.this$0.mOnUiUpdate;
                    if (clipboardOverlayController$$ExternalSyntheticLambda9 != null) {
                        clipboardOverlayController$$ExternalSyntheticLambda9.run();
                        break;
                    }
                    break;
            }
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
            switch (this.$r8$classId) {
                case 1:
                    super.onAnimationStart(animator);
                    this.this$0.mShowingUi = true;
                    break;
                default:
                    super.onAnimationStart(animator);
                    break;
            }
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class ClipboardLogger {
        public String mClipSource;
        public boolean mGuarded = false;
        public final UiEventLogger mUiEventLogger;

        public ClipboardLogger(UiEventLogger uiEventLogger) {
            this.mUiEventLogger = uiEventLogger;
        }

        public final void logSessionComplete(UiEventLogger.UiEventEnum uiEventEnum) {
            if (this.mGuarded) {
                return;
            }
            this.mGuarded = true;
            this.mUiEventLogger.log(uiEventEnum, 0, this.mClipSource);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v11, types: [com.android.systemui.clipboardoverlay.ClipboardOverlayController$5] */
    /* JADX WARN: Type inference failed for: r2v3, types: [android.content.BroadcastReceiver, com.android.systemui.clipboardoverlay.ClipboardOverlayController$2] */
    /* JADX WARN: Type inference failed for: r3v4, types: [android.content.BroadcastReceiver, com.android.systemui.clipboardoverlay.ClipboardOverlayController$2] */
    public ClipboardOverlayController(Context context, ClipboardOverlayView clipboardOverlayView, final ClipboardOverlayWindow clipboardOverlayWindow, BroadcastDispatcher broadcastDispatcher, BroadcastSender broadcastSender, TimeoutHandler timeoutHandler, ClipboardOverlayUtils clipboardOverlayUtils, Executor executor, ClipboardImageLoader clipboardImageLoader, ClipboardTransitionExecutor clipboardTransitionExecutor, UiEventLogger uiEventLogger) {
        final ClipboardOverlayView.ClipboardOverlayCallbacks clipboardOverlayCallbacks = new ClipboardOverlayView.ClipboardOverlayCallbacks() { // from class: com.android.systemui.clipboardoverlay.ClipboardOverlayController.1
            @Override // com.android.systemui.clipboardoverlay.ClipboardOverlayView.ClipboardOverlayCallbacks
            public final void onDismissButtonTapped() {
                ClipboardOverlayController clipboardOverlayController = ClipboardOverlayController.this;
                clipboardOverlayController.mClipboardLogger.logSessionComplete(ClipboardOverlayEvent.CLIPBOARD_OVERLAY_DISMISS_TAPPED);
                clipboardOverlayController.animateOut();
            }

            @Override // com.android.systemui.screenshot.DraggableConstraintLayout$SwipeDismissCallbacks
            public final void onDismissComplete() {
                ClipboardOverlayController.this.hideImmediate();
            }

            @Override // com.android.systemui.screenshot.DraggableConstraintLayout$SwipeDismissCallbacks
            public final void onInteraction() {
                ClipboardOverlayController$$ExternalSyntheticLambda9 clipboardOverlayController$$ExternalSyntheticLambda9 = ClipboardOverlayController.this.mOnUiUpdate;
                if (clipboardOverlayController$$ExternalSyntheticLambda9 != null) {
                    clipboardOverlayController$$ExternalSyntheticLambda9.run();
                }
            }

            @Override // com.android.systemui.clipboardoverlay.ClipboardOverlayView.ClipboardOverlayCallbacks
            public final void onMinimizedViewTapped() {
                ClipboardOverlayController.this.animateFromMinimized();
            }

            @Override // com.android.systemui.clipboardoverlay.ClipboardOverlayView.ClipboardOverlayCallbacks
            public final void onPreviewTapped() {
                Runnable runnable = ClipboardOverlayController.this.mOnPreviewTapped;
                if (runnable != null) {
                    runnable.run();
                }
            }

            @Override // com.android.systemui.clipboardoverlay.ClipboardOverlayView.ClipboardOverlayCallbacks
            public final void onRemoteCopyButtonTapped() {
                ClipboardOverlayController$$ExternalSyntheticLambda0 clipboardOverlayController$$ExternalSyntheticLambda0 = ClipboardOverlayController.this.mOnRemoteCopyTapped;
                if (clipboardOverlayController$$ExternalSyntheticLambda0 != null) {
                    clipboardOverlayController$$ExternalSyntheticLambda0.run();
                }
            }

            @Override // com.android.systemui.clipboardoverlay.ClipboardOverlayView.ClipboardOverlayCallbacks
            public final void onShareButtonTapped() {
                Runnable runnable = ClipboardOverlayController.this.mOnShareTapped;
                if (runnable != null) {
                    runnable.run();
                }
            }

            @Override // com.android.systemui.screenshot.DraggableConstraintLayout$SwipeDismissCallbacks
            public final void onSwipeDismissInitiated(Animator animator) {
                ClipboardOverlayController clipboardOverlayController = ClipboardOverlayController.this;
                clipboardOverlayController.mClipboardLogger.logSessionComplete(ClipboardOverlayEvent.CLIPBOARD_OVERLAY_SWIPE_DISMISSED);
                clipboardOverlayController.mExitAnimator = animator;
            }
        };
        this.mContext = context;
        this.mBroadcastDispatcher = broadcastDispatcher;
        this.mClipboardImageLoader = clipboardImageLoader;
        this.mClipboardLogger = new ClipboardLogger(uiEventLogger);
        this.mView = clipboardOverlayView;
        this.mWindow = clipboardOverlayWindow;
        ClipboardOverlayController$$ExternalSyntheticLambda2 clipboardOverlayController$$ExternalSyntheticLambda2 = new ClipboardOverlayController$$ExternalSyntheticLambda2(this);
        ClipboardOverlayController$$ExternalSyntheticLambda1 clipboardOverlayController$$ExternalSyntheticLambda1 = new ClipboardOverlayController$$ExternalSyntheticLambda1(this, 2);
        clipboardOverlayWindow.mOnKeyboardChangeListener = clipboardOverlayController$$ExternalSyntheticLambda2;
        clipboardOverlayWindow.mOnOrientationChangeListener = clipboardOverlayController$$ExternalSyntheticLambda1;
        View decorView = clipboardOverlayWindow.getDecorView();
        if (!decorView.isAttachedToWindow()) {
            clipboardOverlayWindow.mViewCaptureAwareWindowManager.addView(decorView, clipboardOverlayWindow.mWindowLayoutParams);
            decorView.requestApplyInsets();
        }
        clipboardOverlayWindow.withWindowAttached(new Runnable() { // from class: com.android.systemui.clipboardoverlay.ClipboardOverlayWindow$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                final ClipboardOverlayWindow clipboardOverlayWindow2 = ClipboardOverlayWindow.this;
                clipboardOverlayWindow2.mKeyboardVisible = clipboardOverlayWindow2.mWindowManager.getCurrentWindowMetrics().getWindowInsets().isVisible(WindowInsets.Type.ime());
                clipboardOverlayWindow2.peekDecorView().getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.android.systemui.clipboardoverlay.ClipboardOverlayWindow$$ExternalSyntheticLambda1
                    @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
                    public final void onGlobalLayout() {
                        ClipboardOverlayWindow clipboardOverlayWindow3 = ClipboardOverlayWindow.this;
                        WindowInsets windowInsets = clipboardOverlayWindow3.mWindowManager.getCurrentWindowMetrics().getWindowInsets();
                        boolean isVisible = windowInsets.isVisible(WindowInsets.Type.ime());
                        if (isVisible != clipboardOverlayWindow3.mKeyboardVisible) {
                            clipboardOverlayWindow3.mKeyboardVisible = isVisible;
                            clipboardOverlayWindow3.mOnKeyboardChangeListener.accept(windowInsets, Integer.valueOf(clipboardOverlayWindow3.mOrientation));
                        }
                    }
                });
                clipboardOverlayWindow2.peekDecorView().getViewRootImpl().setActivityConfigCallback(clipboardOverlayWindow2);
            }
        });
        this.mTimeoutHandler = timeoutHandler;
        timeoutHandler.mDefaultTimeout = 6000;
        this.mClipboardUtils = clipboardOverlayUtils;
        this.mBgExecutor = executor;
        clipboardOverlayView.mCallbacks = clipboardOverlayCallbacks;
        final int i = 0;
        clipboardOverlayView.mDismissButton.setOnClickListener(new View.OnClickListener() { // from class: com.android.systemui.clipboardoverlay.ClipboardOverlayView$$ExternalSyntheticLambda4
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                int i2 = i;
                ClipboardOverlayView.ClipboardOverlayCallbacks clipboardOverlayCallbacks2 = clipboardOverlayCallbacks;
                switch (i2) {
                    case 0:
                        int i3 = ClipboardOverlayView.$r8$clinit;
                        clipboardOverlayCallbacks2.onDismissButtonTapped();
                        break;
                    case 1:
                        int i4 = ClipboardOverlayView.$r8$clinit;
                        clipboardOverlayCallbacks2.onPreviewTapped();
                        break;
                    default:
                        int i5 = ClipboardOverlayView.$r8$clinit;
                        clipboardOverlayCallbacks2.onMinimizedViewTapped();
                        break;
                }
            }
        });
        final int i2 = 1;
        clipboardOverlayView.mClipboardPreview.setOnClickListener(new View.OnClickListener() { // from class: com.android.systemui.clipboardoverlay.ClipboardOverlayView$$ExternalSyntheticLambda4
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                int i22 = i2;
                ClipboardOverlayView.ClipboardOverlayCallbacks clipboardOverlayCallbacks2 = clipboardOverlayCallbacks;
                switch (i22) {
                    case 0:
                        int i3 = ClipboardOverlayView.$r8$clinit;
                        clipboardOverlayCallbacks2.onDismissButtonTapped();
                        break;
                    case 1:
                        int i4 = ClipboardOverlayView.$r8$clinit;
                        clipboardOverlayCallbacks2.onPreviewTapped();
                        break;
                    default:
                        int i5 = ClipboardOverlayView.$r8$clinit;
                        clipboardOverlayCallbacks2.onMinimizedViewTapped();
                        break;
                }
            }
        });
        final int i3 = 2;
        clipboardOverlayView.mMinimizedPreview.setOnClickListener(new View.OnClickListener() { // from class: com.android.systemui.clipboardoverlay.ClipboardOverlayView$$ExternalSyntheticLambda4
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                int i22 = i3;
                ClipboardOverlayView.ClipboardOverlayCallbacks clipboardOverlayCallbacks2 = clipboardOverlayCallbacks;
                switch (i22) {
                    case 0:
                        int i32 = ClipboardOverlayView.$r8$clinit;
                        clipboardOverlayCallbacks2.onDismissButtonTapped();
                        break;
                    case 1:
                        int i4 = ClipboardOverlayView.$r8$clinit;
                        clipboardOverlayCallbacks2.onPreviewTapped();
                        break;
                    default:
                        int i5 = ClipboardOverlayView.$r8$clinit;
                        clipboardOverlayCallbacks2.onMinimizedViewTapped();
                        break;
                }
            }
        });
        clipboardOverlayView.mClipboardCallbacks = clipboardOverlayCallbacks;
        clipboardOverlayWindow.withWindowAttached(new ClipboardOverlayController$$ExternalSyntheticLambda1(this, 3));
        timeoutHandler.mOnTimeout = new ClipboardOverlayController$$ExternalSyntheticLambda1(this, 4);
        final int i4 = 0;
        ?? r2 = new BroadcastReceiver(this) { // from class: com.android.systemui.clipboardoverlay.ClipboardOverlayController.2
            public final /* synthetic */ ClipboardOverlayController this$0;

            {
                this.this$0 = this;
            }

            @Override // android.content.BroadcastReceiver
            public final void onReceive(Context context2, Intent intent) {
                switch (i4) {
                    case 0:
                        if ("android.intent.action.CLOSE_SYSTEM_DIALOGS".equals(intent.getAction())) {
                            this.this$0.mClipboardLogger.logSessionComplete(ClipboardOverlayEvent.CLIPBOARD_OVERLAY_DISMISSED_OTHER);
                            this.this$0.animateOut();
                            break;
                        }
                        break;
                    default:
                        if ("com.android.systemui.SCREENSHOT".equals(intent.getAction())) {
                            this.this$0.mClipboardLogger.logSessionComplete(ClipboardOverlayEvent.CLIPBOARD_OVERLAY_DISMISSED_OTHER);
                            this.this$0.animateOut();
                            break;
                        }
                        break;
                }
            }
        };
        this.mCloseDialogsReceiver = r2;
        broadcastDispatcher.registerReceiver(r2, new IntentFilter("android.intent.action.CLOSE_SYSTEM_DIALOGS"));
        final int i5 = 1;
        ?? r3 = new BroadcastReceiver(this) { // from class: com.android.systemui.clipboardoverlay.ClipboardOverlayController.2
            public final /* synthetic */ ClipboardOverlayController this$0;

            {
                this.this$0 = this;
            }

            @Override // android.content.BroadcastReceiver
            public final void onReceive(Context context2, Intent intent) {
                switch (i5) {
                    case 0:
                        if ("android.intent.action.CLOSE_SYSTEM_DIALOGS".equals(intent.getAction())) {
                            this.this$0.mClipboardLogger.logSessionComplete(ClipboardOverlayEvent.CLIPBOARD_OVERLAY_DISMISSED_OTHER);
                            this.this$0.animateOut();
                            break;
                        }
                        break;
                    default:
                        if ("com.android.systemui.SCREENSHOT".equals(intent.getAction())) {
                            this.this$0.mClipboardLogger.logSessionComplete(ClipboardOverlayEvent.CLIPBOARD_OVERLAY_DISMISSED_OTHER);
                            this.this$0.animateOut();
                            break;
                        }
                        break;
                }
            }
        };
        this.mScreenshotReceiver = r3;
        broadcastDispatcher.registerReceiver(r3, new IntentFilter("com.android.systemui.SCREENSHOT"), null, null, 2, "com.android.systemui.permission.SELF");
        this.mInputMonitor = ((InputManager) context.getSystemService(InputManager.class)).monitorGestureInput("clipboard overlay", 0);
        this.mInputEventReceiver = new InputEventReceiver(this.mInputMonitor.getInputChannel(), Looper.getMainLooper()) { // from class: com.android.systemui.clipboardoverlay.ClipboardOverlayController.5
            public final void onInputEvent(InputEvent inputEvent) {
                if (ClipboardOverlayController.this.mShowingUi && (inputEvent instanceof MotionEvent)) {
                    MotionEvent motionEvent = (MotionEvent) inputEvent;
                    if (motionEvent.getActionMasked() == 0) {
                        ClipboardOverlayView clipboardOverlayView2 = ClipboardOverlayController.this.mView;
                        int rawX = (int) motionEvent.getRawX();
                        int rawY = (int) motionEvent.getRawY();
                        Region region = new Region();
                        Rect rect = new Rect();
                        clipboardOverlayView2.mPreviewBorder.getBoundsOnScreen(rect);
                        rect.inset((int) FloatingWindowUtil.dpToPx(clipboardOverlayView2.mDisplayMetrics, -12.0f), (int) FloatingWindowUtil.dpToPx(clipboardOverlayView2.mDisplayMetrics, -12.0f));
                        Region.Op op = Region.Op.UNION;
                        region.op(rect, op);
                        clipboardOverlayView2.mActionContainerBackground.getBoundsOnScreen(rect);
                        rect.inset((int) FloatingWindowUtil.dpToPx(clipboardOverlayView2.mDisplayMetrics, -12.0f), (int) FloatingWindowUtil.dpToPx(clipboardOverlayView2.mDisplayMetrics, -12.0f));
                        region.op(rect, op);
                        clipboardOverlayView2.mMinimizedPreview.getBoundsOnScreen(rect);
                        rect.inset((int) FloatingWindowUtil.dpToPx(clipboardOverlayView2.mDisplayMetrics, -12.0f), (int) FloatingWindowUtil.dpToPx(clipboardOverlayView2.mDisplayMetrics, -12.0f));
                        region.op(rect, op);
                        clipboardOverlayView2.mDismissButton.getBoundsOnScreen(rect);
                        region.op(rect, op);
                        if (!region.contains(rawX, rawY)) {
                            ClipboardOverlayController.this.mClipboardLogger.logSessionComplete(ClipboardOverlayEvent.CLIPBOARD_OVERLAY_TAP_OUTSIDE);
                            ClipboardOverlayController.this.animateOut();
                        }
                    }
                }
                finishInputEvent(inputEvent, true);
            }
        };
        Intent intent = new Intent("com.android.systemui.COPY");
        intent.setPackage(context.getPackageName());
        broadcastSender.sendBroadcast(intent, "com.android.systemui.permission.SELF");
    }

    public final void animateFromMinimized() {
        int i = 0;
        Animator animator = this.mEnterAnimator;
        if (animator != null && animator.isRunning()) {
            this.mEnterAnimator.cancel();
        }
        ClipboardOverlayView clipboardOverlayView = this.mView;
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(clipboardOverlayView.mMinimizedPreview, "alpha", 1.0f, 0.0f);
        ofFloat.setDuration(66L);
        ofFloat.addListener(new ClipboardOverlayView.AnonymousClass3(clipboardOverlayView, i));
        this.mEnterAnimator = ofFloat;
        ofFloat.addListener(new AnonymousClass4(this, i));
        this.mEnterAnimator.start();
    }

    public final void animateIn() {
        int i = 1;
        Animator animator = this.mEnterAnimator;
        if (animator == null || !animator.isRunning()) {
            ClipboardOverlayView clipboardOverlayView = this.mView;
            if (clipboardOverlayView.mAccessibilityManager.isEnabled()) {
                clipboardOverlayView.mDismissButton.setVisibility(0);
            }
            LinearInterpolator linearInterpolator = new LinearInterpolator();
            PathInterpolator pathInterpolator = new PathInterpolator(0.0f, 0.0f, 0.0f, 1.0f);
            AnimatorSet animatorSet = new AnimatorSet();
            ValueAnimator ofFloat = ValueAnimator.ofFloat(0.0f, 1.0f);
            ofFloat.setInterpolator(linearInterpolator);
            ofFloat.setDuration(66L);
            ofFloat.addUpdateListener(new ClipboardOverlayView$$ExternalSyntheticLambda1(clipboardOverlayView, 3));
            ValueAnimator ofFloat2 = ValueAnimator.ofFloat(0.0f, 1.0f);
            ofFloat2.setInterpolator(pathInterpolator);
            ofFloat2.setDuration(333L);
            ofFloat2.addUpdateListener(new ClipboardOverlayView$$ExternalSyntheticLambda1(clipboardOverlayView, 4));
            ValueAnimator ofFloat3 = ValueAnimator.ofFloat(0.0f, 1.0f);
            ofFloat3.setInterpolator(linearInterpolator);
            ofFloat3.setDuration(283L);
            ofFloat3.addUpdateListener(new ClipboardOverlayView$$ExternalSyntheticLambda1(clipboardOverlayView, 5));
            clipboardOverlayView.mMinimizedPreview.setAlpha(0.0f);
            clipboardOverlayView.mActionContainer.setAlpha(0.0f);
            clipboardOverlayView.mPreviewBorder.setAlpha(0.0f);
            clipboardOverlayView.mClipboardPreview.setAlpha(0.0f);
            animatorSet.play(ofFloat).with(ofFloat2);
            animatorSet.play(ofFloat3).after(50L).after(ofFloat);
            animatorSet.addListener(new ClipboardOverlayView.AnonymousClass3(clipboardOverlayView, i));
            this.mEnterAnimator = animatorSet;
            animatorSet.addListener(new AnonymousClass4(this, i));
            this.mEnterAnimator.start();
        }
    }

    public final void animateOut() {
        int i = 2;
        Animator animator = this.mExitAnimator;
        if (animator == null || !animator.isRunning()) {
            ClipboardOverlayView clipboardOverlayView = this.mView;
            LinearInterpolator linearInterpolator = new LinearInterpolator();
            PathInterpolator pathInterpolator = new PathInterpolator(0.3f, 0.0f, 1.0f, 1.0f);
            AnimatorSet animatorSet = new AnimatorSet();
            ValueAnimator ofFloat = ValueAnimator.ofFloat(0.0f, 1.0f);
            ofFloat.setInterpolator(linearInterpolator);
            ofFloat.setDuration(100L);
            ofFloat.addUpdateListener(new ClipboardOverlayView$$ExternalSyntheticLambda1(clipboardOverlayView, 0));
            ValueAnimator ofFloat2 = ValueAnimator.ofFloat(0.0f, 1.0f);
            ofFloat2.setInterpolator(pathInterpolator);
            ofFloat2.setDuration(250L);
            ofFloat2.addUpdateListener(new ClipboardOverlayView$$ExternalSyntheticLambda1(clipboardOverlayView, 1));
            ValueAnimator ofFloat3 = ValueAnimator.ofFloat(0.0f, 1.0f);
            ofFloat3.setInterpolator(linearInterpolator);
            ofFloat3.setDuration(166L);
            ofFloat3.addUpdateListener(new ClipboardOverlayView$$ExternalSyntheticLambda1(clipboardOverlayView, i));
            animatorSet.play(ofFloat3).with(ofFloat2);
            animatorSet.play(ofFloat).after(150L).after(ofFloat3);
            this.mExitAnimator = animatorSet;
            animatorSet.addListener(new AnimatorListenerAdapter() { // from class: com.android.systemui.clipboardoverlay.ClipboardOverlayController.7
                public boolean mCancelled;

                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                public final void onAnimationCancel(Animator animator2) {
                    super.onAnimationCancel(animator2);
                    this.mCancelled = true;
                }

                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                public final void onAnimationEnd(Animator animator2) {
                    super.onAnimationEnd(animator2);
                    if (this.mCancelled) {
                        return;
                    }
                    ClipboardOverlayController.this.hideImmediate();
                }
            });
            this.mExitAnimator.start();
        }
    }

    public final void hideImmediate() {
        this.mTimeoutHandler.removeMessages(2);
        ClipboardOverlayWindow clipboardOverlayWindow = this.mWindow;
        View peekDecorView = clipboardOverlayWindow.peekDecorView();
        if (peekDecorView != null && peekDecorView.isAttachedToWindow()) {
            clipboardOverlayWindow.mViewCaptureAwareWindowManager.removeViewImmediate(peekDecorView);
        }
        AnonymousClass2 anonymousClass2 = this.mCloseDialogsReceiver;
        BroadcastDispatcher broadcastDispatcher = this.mBroadcastDispatcher;
        if (anonymousClass2 != null) {
            broadcastDispatcher.unregisterReceiver(anonymousClass2);
            this.mCloseDialogsReceiver = null;
        }
        AnonymousClass2 anonymousClass22 = this.mScreenshotReceiver;
        if (anonymousClass22 != null) {
            broadcastDispatcher.unregisterReceiver(anonymousClass22);
            this.mScreenshotReceiver = null;
        }
        AnonymousClass5 anonymousClass5 = this.mInputEventReceiver;
        if (anonymousClass5 != null) {
            anonymousClass5.dispose();
            this.mInputEventReceiver = null;
        }
        InputMonitor inputMonitor = this.mInputMonitor;
        if (inputMonitor != null) {
            inputMonitor.dispose();
            this.mInputMonitor = null;
        }
        ClipboardListener$$ExternalSyntheticLambda0 clipboardListener$$ExternalSyntheticLambda0 = this.mOnSessionCompleteListener;
        if (clipboardListener$$ExternalSyntheticLambda0 != null) {
            clipboardListener$$ExternalSyntheticLambda0.run();
        }
    }

    @Override // com.android.systemui.screenshot.DraggableConstraintLayout$SwipeDismissCallbacks
    public final void onDismissComplete() {
        hideImmediate();
    }

    public void onInsetsChanged(WindowInsets windowInsets, int i) {
        ClipboardOverlayView clipboardOverlayView = this.mView;
        clipboardOverlayView.setInsets(windowInsets, i);
        if (windowInsets.getInsets(WindowInsets.Type.ime()).bottom <= 0 || this.mIsMinimized) {
            return;
        }
        this.mIsMinimized = true;
        clipboardOverlayView.setMinimized(true);
    }

    @Override // com.android.systemui.screenshot.DraggableConstraintLayout$SwipeDismissCallbacks
    public final void onInteraction() {
        if (this.mClipboardModel.isRemote) {
            return;
        }
        this.mTimeoutHandler.resetTimeout();
    }

    @Override // com.android.systemui.clipboardoverlay.ClipboardOverlayView.ClipboardOverlayCallbacks
    public final void onMinimizedViewTapped() {
        animateFromMinimized();
    }

    @Override // com.android.systemui.screenshot.DraggableConstraintLayout$SwipeDismissCallbacks
    public final void onSwipeDismissInitiated(Animator animator) {
        Animator animator2 = this.mExitAnimator;
        if (animator2 != null && animator2.isRunning()) {
            this.mExitAnimator.cancel();
        }
        this.mExitAnimator = animator;
        this.mClipboardLogger.logSessionComplete(ClipboardOverlayEvent.CLIPBOARD_OVERLAY_SWIPE_DISMISSED);
    }

    public final void setExpandedView(final Runnable runnable) {
        final int i = 2;
        final int i2 = 1;
        final ClipboardModel clipboardModel = this.mClipboardModel;
        ClipboardOverlayView clipboardOverlayView = this.mView;
        final int i3 = 0;
        clipboardOverlayView.setMinimized(false);
        int ordinal = clipboardModel.type.ordinal();
        boolean z = clipboardModel.isRemote;
        boolean z2 = clipboardModel.isSensitive;
        if (ordinal == 0) {
            if ((z || DeviceConfig.getBoolean("systemui", "clipboard_overlay_show_actions", false)) && clipboardModel.textLinks != null) {
                this.mBgExecutor.execute(new Runnable(this) { // from class: com.android.systemui.clipboardoverlay.ClipboardOverlayController$$ExternalSyntheticLambda11
                    public final /* synthetic */ ClipboardOverlayController f$0;

                    {
                        this.f$0 = this;
                    }

                    @Override // java.lang.Runnable
                    public final void run() {
                        switch (i) {
                            case 0:
                                ClipboardOverlayController clipboardOverlayController = this.f$0;
                                ClipboardModel clipboardModel2 = clipboardModel;
                                clipboardOverlayController.getClass();
                                Uri uri = clipboardModel2.uri;
                                clipboardOverlayController.mClipboardLogger.logSessionComplete(ClipboardOverlayEvent.CLIPBOARD_OVERLAY_EDIT_TAPPED);
                                Context context = clipboardOverlayController.mContext;
                                String string = context.getString(R.string.config_screenshotEditor);
                                Intent intent = new Intent("android.intent.action.EDIT");
                                if (!TextUtils.isEmpty(string)) {
                                    intent.setComponent(ComponentName.unflattenFromString(string));
                                }
                                intent.setDataAndType(uri, "image/*");
                                intent.addFlags(1);
                                intent.addFlags(268468224);
                                intent.putExtra("edit_source", "clipboard");
                                context.startActivity(intent);
                                clipboardOverlayController.animateOut();
                                break;
                            case 1:
                                ClipboardOverlayController clipboardOverlayController2 = this.f$0;
                                ClipboardModel clipboardModel3 = clipboardModel;
                                clipboardOverlayController2.getClass();
                                ClipData clipData = clipboardModel3.clipData;
                                clipboardOverlayController2.mClipboardLogger.logSessionComplete(ClipboardOverlayEvent.CLIPBOARD_OVERLAY_SHARE_TAPPED);
                                Context context2 = clipboardOverlayController2.mContext;
                                Intent intent2 = new Intent("android.intent.action.SEND");
                                Uri uri2 = clipData.getItemAt(0).getUri();
                                if (uri2 != null) {
                                    intent2.setType(clipData.getDescription().getMimeType(0));
                                    intent2.setClipData(new ClipData(new ClipDescription("content", new String[]{clipData.getDescription().getMimeType(0)}), new ClipData.Item(uri2)));
                                    intent2.putExtra("android.intent.extra.STREAM", uri2);
                                    intent2.addFlags(1);
                                } else {
                                    intent2.putExtra("android.intent.extra.TEXT", clipData.getItemAt(0).coerceToText(context2).toString());
                                    intent2.setType("text/plain");
                                }
                                context2.startActivity(Intent.createChooser(intent2, null).addFlags(268468224).addFlags(1));
                                clipboardOverlayController2.animateOut();
                                break;
                            default:
                                final ClipboardOverlayController clipboardOverlayController3 = this.f$0;
                                ClipboardModel clipboardModel4 = clipboardModel;
                                clipboardOverlayController3.getClass();
                                TextLinks textLinks = clipboardModel4.textLinks;
                                ClipboardOverlayUtils clipboardOverlayUtils = clipboardOverlayController3.mClipboardUtils;
                                clipboardOverlayUtils.getClass();
                                ArrayList arrayList = new ArrayList();
                                for (TextLinks.TextLink textLink : textLinks.getLinks()) {
                                    if (textLink.getEnd() - textLink.getStart() >= textLinks.getText().length() * 0.8f) {
                                        arrayList.addAll(clipboardOverlayUtils.mTextClassifier.classifyText(textLinks.getText(), textLink.getStart(), textLink.getEnd(), null).getActions());
                                    }
                                }
                                Stream stream = arrayList.stream();
                                final String str = clipboardModel4.source;
                                Optional findFirst = stream.filter(new Predicate() { // from class: com.android.systemui.clipboardoverlay.ClipboardOverlayUtils$$ExternalSyntheticLambda0
                                    @Override // java.util.function.Predicate
                                    public final boolean test(Object obj) {
                                        String str2 = str;
                                        ComponentName component = ((RemoteAction) obj).getActionIntent().getIntent().getComponent();
                                        return (component == null || TextUtils.equals(str2, component.getPackageName())) ? false : true;
                                    }
                                }).findFirst();
                                if (clipboardModel4.equals(clipboardOverlayController3.mClipboardModel)) {
                                    findFirst.ifPresent(new Consumer() { // from class: com.android.systemui.clipboardoverlay.ClipboardOverlayController$$ExternalSyntheticLambda17
                                        @Override // java.util.function.Consumer
                                        public final void accept(Object obj) {
                                            ClipboardOverlayController clipboardOverlayController4 = ClipboardOverlayController.this;
                                            clipboardOverlayController4.getClass();
                                            ClipboardOverlayEvent clipboardOverlayEvent = ClipboardOverlayEvent.CLIPBOARD_OVERLAY_ACTION_SHOWN;
                                            ClipboardOverlayController.ClipboardLogger clipboardLogger = clipboardOverlayController4.mClipboardLogger;
                                            clipboardLogger.mUiEventLogger.log(clipboardOverlayEvent, 0, clipboardLogger.mClipSource);
                                            clipboardOverlayController4.mView.post(new ClipboardOverlayController$$ExternalSyntheticLambda0(clipboardOverlayController4, (RemoteAction) obj));
                                        }
                                    });
                                    break;
                                }
                                break;
                        }
                    }
                });
            }
            if (z2) {
                clipboardOverlayView.showTextPreview(this.mContext.getString(R.string.clipboard_asterisks), true);
            } else {
                clipboardOverlayView.showTextPreview(clipboardModel.text.toString(), false);
            }
            clipboardOverlayView.setEditAccessibilityAction(true);
            this.mOnPreviewTapped = new ClipboardOverlayController$$ExternalSyntheticLambda1(this, i2);
            runnable.run();
        } else if (ordinal == 1) {
            clipboardOverlayView.setEditAccessibilityAction(true);
            this.mOnPreviewTapped = new Runnable(this) { // from class: com.android.systemui.clipboardoverlay.ClipboardOverlayController$$ExternalSyntheticLambda11
                public final /* synthetic */ ClipboardOverlayController f$0;

                {
                    this.f$0 = this;
                }

                @Override // java.lang.Runnable
                public final void run() {
                    switch (i3) {
                        case 0:
                            ClipboardOverlayController clipboardOverlayController = this.f$0;
                            ClipboardModel clipboardModel2 = clipboardModel;
                            clipboardOverlayController.getClass();
                            Uri uri = clipboardModel2.uri;
                            clipboardOverlayController.mClipboardLogger.logSessionComplete(ClipboardOverlayEvent.CLIPBOARD_OVERLAY_EDIT_TAPPED);
                            Context context = clipboardOverlayController.mContext;
                            String string = context.getString(R.string.config_screenshotEditor);
                            Intent intent = new Intent("android.intent.action.EDIT");
                            if (!TextUtils.isEmpty(string)) {
                                intent.setComponent(ComponentName.unflattenFromString(string));
                            }
                            intent.setDataAndType(uri, "image/*");
                            intent.addFlags(1);
                            intent.addFlags(268468224);
                            intent.putExtra("edit_source", "clipboard");
                            context.startActivity(intent);
                            clipboardOverlayController.animateOut();
                            break;
                        case 1:
                            ClipboardOverlayController clipboardOverlayController2 = this.f$0;
                            ClipboardModel clipboardModel3 = clipboardModel;
                            clipboardOverlayController2.getClass();
                            ClipData clipData = clipboardModel3.clipData;
                            clipboardOverlayController2.mClipboardLogger.logSessionComplete(ClipboardOverlayEvent.CLIPBOARD_OVERLAY_SHARE_TAPPED);
                            Context context2 = clipboardOverlayController2.mContext;
                            Intent intent2 = new Intent("android.intent.action.SEND");
                            Uri uri2 = clipData.getItemAt(0).getUri();
                            if (uri2 != null) {
                                intent2.setType(clipData.getDescription().getMimeType(0));
                                intent2.setClipData(new ClipData(new ClipDescription("content", new String[]{clipData.getDescription().getMimeType(0)}), new ClipData.Item(uri2)));
                                intent2.putExtra("android.intent.extra.STREAM", uri2);
                                intent2.addFlags(1);
                            } else {
                                intent2.putExtra("android.intent.extra.TEXT", clipData.getItemAt(0).coerceToText(context2).toString());
                                intent2.setType("text/plain");
                            }
                            context2.startActivity(Intent.createChooser(intent2, null).addFlags(268468224).addFlags(1));
                            clipboardOverlayController2.animateOut();
                            break;
                        default:
                            final ClipboardOverlayController clipboardOverlayController3 = this.f$0;
                            ClipboardModel clipboardModel4 = clipboardModel;
                            clipboardOverlayController3.getClass();
                            TextLinks textLinks = clipboardModel4.textLinks;
                            ClipboardOverlayUtils clipboardOverlayUtils = clipboardOverlayController3.mClipboardUtils;
                            clipboardOverlayUtils.getClass();
                            ArrayList arrayList = new ArrayList();
                            for (TextLinks.TextLink textLink : textLinks.getLinks()) {
                                if (textLink.getEnd() - textLink.getStart() >= textLinks.getText().length() * 0.8f) {
                                    arrayList.addAll(clipboardOverlayUtils.mTextClassifier.classifyText(textLinks.getText(), textLink.getStart(), textLink.getEnd(), null).getActions());
                                }
                            }
                            Stream stream = arrayList.stream();
                            final String str = clipboardModel4.source;
                            Optional findFirst = stream.filter(new Predicate() { // from class: com.android.systemui.clipboardoverlay.ClipboardOverlayUtils$$ExternalSyntheticLambda0
                                @Override // java.util.function.Predicate
                                public final boolean test(Object obj) {
                                    String str2 = str;
                                    ComponentName component = ((RemoteAction) obj).getActionIntent().getIntent().getComponent();
                                    return (component == null || TextUtils.equals(str2, component.getPackageName())) ? false : true;
                                }
                            }).findFirst();
                            if (clipboardModel4.equals(clipboardOverlayController3.mClipboardModel)) {
                                findFirst.ifPresent(new Consumer() { // from class: com.android.systemui.clipboardoverlay.ClipboardOverlayController$$ExternalSyntheticLambda17
                                    @Override // java.util.function.Consumer
                                    public final void accept(Object obj) {
                                        ClipboardOverlayController clipboardOverlayController4 = ClipboardOverlayController.this;
                                        clipboardOverlayController4.getClass();
                                        ClipboardOverlayEvent clipboardOverlayEvent = ClipboardOverlayEvent.CLIPBOARD_OVERLAY_ACTION_SHOWN;
                                        ClipboardOverlayController.ClipboardLogger clipboardLogger = clipboardOverlayController4.mClipboardLogger;
                                        clipboardLogger.mUiEventLogger.log(clipboardOverlayEvent, 0, clipboardLogger.mClipSource);
                                        clipboardOverlayController4.mView.post(new ClipboardOverlayController$$ExternalSyntheticLambda0(clipboardOverlayController4, (RemoteAction) obj));
                                    }
                                });
                                break;
                            }
                            break;
                    }
                }
            };
            if (z2) {
                clipboardOverlayView.showImagePreview(null);
                runnable.run();
            } else {
                Uri uri = clipboardModel.uri;
                Consumer consumer = new Consumer() { // from class: com.android.systemui.clipboardoverlay.ClipboardOverlayController$$ExternalSyntheticLambda12
                    @Override // java.util.function.Consumer
                    public final void accept(Object obj) {
                        final ClipboardOverlayController clipboardOverlayController = ClipboardOverlayController.this;
                        final Runnable runnable2 = runnable;
                        final Bitmap bitmap = (Bitmap) obj;
                        clipboardOverlayController.getClass();
                        clipboardOverlayController.mView.post(new Runnable() { // from class: com.android.systemui.clipboardoverlay.ClipboardOverlayController$$ExternalSyntheticLambda14
                            @Override // java.lang.Runnable
                            public final void run() {
                                ClipboardOverlayController clipboardOverlayController2 = ClipboardOverlayController.this;
                                Bitmap bitmap2 = bitmap;
                                Runnable runnable3 = runnable2;
                                ClipboardOverlayView clipboardOverlayView2 = clipboardOverlayController2.mView;
                                if (bitmap2 == null) {
                                    clipboardOverlayView2.showDefaultTextPreview();
                                } else {
                                    clipboardOverlayView2.showImagePreview(bitmap2);
                                }
                                runnable3.run();
                            }
                        });
                    }
                };
                ClipboardImageLoader clipboardImageLoader = this.mClipboardImageLoader;
                BuildersKt.launch$default(clipboardImageLoader.mainScope, null, null, new ClipboardImageLoader$loadAsync$1(consumer, clipboardImageLoader, uri, null), 3);
            }
        } else if (ordinal == 2 || ordinal == 3) {
            clipboardOverlayView.showDefaultTextPreview();
            runnable.run();
        }
        if (!z) {
            ClipData clipData = clipboardModel.clipData;
            Context context = this.mContext;
            Intent intent = new Intent("android.intent.action.REMOTE_COPY");
            String string = context.getString(R.string.config_remoteCopyPackage);
            if (!TextUtils.isEmpty(string)) {
                intent.setComponent(ComponentName.unflattenFromString(string));
            }
            intent.setClipData(clipData);
            intent.addFlags(1);
            intent.addFlags(268468224);
            if (this.mContext.getPackageManager().resolveActivity(intent, PackageManager.ResolveInfoFlags.of(0L)) != null) {
                clipboardOverlayView.mRemoteCopyChip.setVisibility(0);
                clipboardOverlayView.mActionContainerBackground.setVisibility(0);
                this.mOnRemoteCopyTapped = new ClipboardOverlayController$$ExternalSyntheticLambda0(this, intent);
            } else {
                clipboardOverlayView.mRemoteCopyChip.setVisibility(8);
            }
        }
        if (clipboardModel.type != ClipboardModel.Type.OTHER) {
            this.mOnShareTapped = new Runnable(this) { // from class: com.android.systemui.clipboardoverlay.ClipboardOverlayController$$ExternalSyntheticLambda11
                public final /* synthetic */ ClipboardOverlayController f$0;

                {
                    this.f$0 = this;
                }

                @Override // java.lang.Runnable
                public final void run() {
                    switch (i2) {
                        case 0:
                            ClipboardOverlayController clipboardOverlayController = this.f$0;
                            ClipboardModel clipboardModel2 = clipboardModel;
                            clipboardOverlayController.getClass();
                            Uri uri2 = clipboardModel2.uri;
                            clipboardOverlayController.mClipboardLogger.logSessionComplete(ClipboardOverlayEvent.CLIPBOARD_OVERLAY_EDIT_TAPPED);
                            Context context2 = clipboardOverlayController.mContext;
                            String string2 = context2.getString(R.string.config_screenshotEditor);
                            Intent intent2 = new Intent("android.intent.action.EDIT");
                            if (!TextUtils.isEmpty(string2)) {
                                intent2.setComponent(ComponentName.unflattenFromString(string2));
                            }
                            intent2.setDataAndType(uri2, "image/*");
                            intent2.addFlags(1);
                            intent2.addFlags(268468224);
                            intent2.putExtra("edit_source", "clipboard");
                            context2.startActivity(intent2);
                            clipboardOverlayController.animateOut();
                            break;
                        case 1:
                            ClipboardOverlayController clipboardOverlayController2 = this.f$0;
                            ClipboardModel clipboardModel3 = clipboardModel;
                            clipboardOverlayController2.getClass();
                            ClipData clipData2 = clipboardModel3.clipData;
                            clipboardOverlayController2.mClipboardLogger.logSessionComplete(ClipboardOverlayEvent.CLIPBOARD_OVERLAY_SHARE_TAPPED);
                            Context context22 = clipboardOverlayController2.mContext;
                            Intent intent22 = new Intent("android.intent.action.SEND");
                            Uri uri22 = clipData2.getItemAt(0).getUri();
                            if (uri22 != null) {
                                intent22.setType(clipData2.getDescription().getMimeType(0));
                                intent22.setClipData(new ClipData(new ClipDescription("content", new String[]{clipData2.getDescription().getMimeType(0)}), new ClipData.Item(uri22)));
                                intent22.putExtra("android.intent.extra.STREAM", uri22);
                                intent22.addFlags(1);
                            } else {
                                intent22.putExtra("android.intent.extra.TEXT", clipData2.getItemAt(0).coerceToText(context22).toString());
                                intent22.setType("text/plain");
                            }
                            context22.startActivity(Intent.createChooser(intent22, null).addFlags(268468224).addFlags(1));
                            clipboardOverlayController2.animateOut();
                            break;
                        default:
                            final ClipboardOverlayController clipboardOverlayController3 = this.f$0;
                            ClipboardModel clipboardModel4 = clipboardModel;
                            clipboardOverlayController3.getClass();
                            TextLinks textLinks = clipboardModel4.textLinks;
                            ClipboardOverlayUtils clipboardOverlayUtils = clipboardOverlayController3.mClipboardUtils;
                            clipboardOverlayUtils.getClass();
                            ArrayList arrayList = new ArrayList();
                            for (TextLinks.TextLink textLink : textLinks.getLinks()) {
                                if (textLink.getEnd() - textLink.getStart() >= textLinks.getText().length() * 0.8f) {
                                    arrayList.addAll(clipboardOverlayUtils.mTextClassifier.classifyText(textLinks.getText(), textLink.getStart(), textLink.getEnd(), null).getActions());
                                }
                            }
                            Stream stream = arrayList.stream();
                            final String str = clipboardModel4.source;
                            Optional findFirst = stream.filter(new Predicate() { // from class: com.android.systemui.clipboardoverlay.ClipboardOverlayUtils$$ExternalSyntheticLambda0
                                @Override // java.util.function.Predicate
                                public final boolean test(Object obj) {
                                    String str2 = str;
                                    ComponentName component = ((RemoteAction) obj).getActionIntent().getIntent().getComponent();
                                    return (component == null || TextUtils.equals(str2, component.getPackageName())) ? false : true;
                                }
                            }).findFirst();
                            if (clipboardModel4.equals(clipboardOverlayController3.mClipboardModel)) {
                                findFirst.ifPresent(new Consumer() { // from class: com.android.systemui.clipboardoverlay.ClipboardOverlayController$$ExternalSyntheticLambda17
                                    @Override // java.util.function.Consumer
                                    public final void accept(Object obj) {
                                        ClipboardOverlayController clipboardOverlayController4 = ClipboardOverlayController.this;
                                        clipboardOverlayController4.getClass();
                                        ClipboardOverlayEvent clipboardOverlayEvent = ClipboardOverlayEvent.CLIPBOARD_OVERLAY_ACTION_SHOWN;
                                        ClipboardOverlayController.ClipboardLogger clipboardLogger = clipboardOverlayController4.mClipboardLogger;
                                        clipboardLogger.mUiEventLogger.log(clipboardOverlayEvent, 0, clipboardLogger.mClipSource);
                                        clipboardOverlayController4.mView.post(new ClipboardOverlayController$$ExternalSyntheticLambda0(clipboardOverlayController4, (RemoteAction) obj));
                                    }
                                });
                                break;
                            }
                            break;
                    }
                }
            };
            clipboardOverlayView.mShareChip.setVisibility(0);
            clipboardOverlayView.mActionContainerBackground.setVisibility(0);
        }
    }

    @Override // com.android.systemui.clipboardoverlay.ClipboardOverlayView.ClipboardOverlayCallbacks
    public final void onDismissButtonTapped() {
    }

    @Override // com.android.systemui.clipboardoverlay.ClipboardOverlayView.ClipboardOverlayCallbacks
    public final void onPreviewTapped() {
    }

    @Override // com.android.systemui.clipboardoverlay.ClipboardOverlayView.ClipboardOverlayCallbacks
    public final void onRemoteCopyButtonTapped() {
    }

    @Override // com.android.systemui.clipboardoverlay.ClipboardOverlayView.ClipboardOverlayCallbacks
    public final void onShareButtonTapped() {
    }
}
