package com.android.systemui.screenshot;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Insets;
import android.graphics.Rect;
import android.graphics.Region;
import android.graphics.RenderEffect;
import android.graphics.Shader;
import android.os.Looper;
import android.util.DisplayMetrics;
import android.view.Choreographer;
import android.view.InputEvent;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.WindowInsets;
import android.view.WindowManager;
import android.widget.ImageView;
import android.window.OnBackInvokedCallback;
import android.window.OnBackInvokedDispatcher;
import com.android.internal.logging.UiEventLogger;
import com.android.systemui.screenshot.LegacyScreenshotController;
import com.android.systemui.screenshot.ScreenshotShelfViewProxy;
import com.android.systemui.screenshot.ui.ScreenshotAnimationController;
import com.android.systemui.screenshot.ui.ScreenshotAnimationController$getActionsAnimator$1;
import com.android.systemui.screenshot.ui.ScreenshotAnimationController$getEntranceAnimation$$inlined$doOnEnd$1;
import com.android.systemui.screenshot.ui.ScreenshotShelfView;
import com.android.systemui.screenshot.ui.binder.ScreenshotShelfViewBinder;
import com.android.systemui.screenshot.ui.viewmodel.AnimationState;
import com.android.systemui.screenshot.ui.viewmodel.ScreenshotViewModel;
import com.android.systemui.shared.system.InputChannelCompat$InputEventListener;
import com.android.systemui.shared.system.InputChannelCompat$InputEventReceiver;
import com.android.systemui.shared.system.InputMonitorCompat;
import com.android.wm.shell.R;
import com.google.android.systemui.screenshot.ThumbnailObserverGoogle;
import com.google.android.systemui.screenshot.ThumbnailObserverGoogle$setViews$glowBorderEffectDrawCallback$1;
import com.google.android.systemui.screenshot.ThumbnailObserverGoogle$setViews$rippleRevealEffectDrawCallback$1;
import com.google.android.systemui.screenshot.surfaceeffects.gloweffect.GlowPieEffect;
import com.google.android.systemui.screenshot.surfaceeffects.revealeffect.RippleRevealEffect;
import kotlin.Unit;
import kotlin.collections.EmptyList;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.Reflection;
import kotlinx.coroutines.flow.StateFlowImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class ScreenshotShelfViewProxy {
    public final ScreenshotAnimationController animationController;
    public LegacyScreenshotController.AnonymousClass2 callbacks;
    public final Context context;
    public final int displayId;
    public InputChannelCompat$InputEventReceiver inputEventReceiver;
    public InputMonitorCompat inputMonitor;
    public boolean isDismissing;
    public final UiEventLogger logger;
    public String packageName;
    public final View screenshotPreview;
    public final ThumbnailObserverGoogle thumbnailObserver;
    public final ScreenshotShelfView view;
    public final ScreenshotViewModel viewModel;
    public final WindowManager windowManager;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.screenshot.ScreenshotShelfViewProxy$3, reason: invalid class name */
    final class AnonymousClass3 extends Lambda implements Function1 {
        public AnonymousClass3() {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Object obj) {
            ScreenshotShelfViewProxy.this.requestDismissal(ScreenshotEvent.SCREENSHOT_DISMISSED_OTHER, null);
            return Unit.INSTANCE;
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.screenshot.ScreenshotShelfViewProxy$4, reason: invalid class name */
    final class AnonymousClass4 extends Lambda implements Function1 {
        public AnonymousClass4() {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Object obj) {
            ScreenshotShelfViewProxy.this.requestDismissal(ScreenshotEvent.SCREENSHOT_DISMISSED_OTHER, null);
            return Unit.INSTANCE;
        }
    }

    /* JADX WARN: Type inference failed for: r11v1, types: [com.android.systemui.screenshot.ScreenshotShelfViewProxy$addPredictiveBackListener$onBackInvokedCallback$1] */
    public ScreenshotShelfViewProxy(UiEventLogger uiEventLogger, ScreenshotViewModel screenshotViewModel, WindowManager windowManager, ScreenshotShelfViewBinder screenshotShelfViewBinder, ThumbnailObserverGoogle thumbnailObserverGoogle, Context context, int i) {
        this.logger = uiEventLogger;
        this.viewModel = screenshotViewModel;
        this.windowManager = windowManager;
        this.thumbnailObserver = thumbnailObserverGoogle;
        this.context = context;
        this.displayId = i;
        ScreenshotShelfView screenshotShelfView = (ScreenshotShelfView) LayoutInflater.from(context).inflate(R.layout.screenshot_shelf, (ViewGroup) null);
        this.view = screenshotShelfView;
        this.packageName = "";
        ScreenshotAnimationController screenshotAnimationController = new ScreenshotAnimationController(screenshotShelfView, screenshotViewModel);
        this.animationController = screenshotAnimationController;
        screenshotShelfViewBinder.bind(screenshotShelfView, screenshotViewModel, screenshotAnimationController, LayoutInflater.from(context), new Function2() { // from class: com.android.systemui.screenshot.ScreenshotShelfViewProxy.1
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                ScreenshotShelfViewProxy.this.requestDismissal((ScreenshotEvent) obj, (Float) obj2);
                return Unit.INSTANCE;
            }
        }, new Function0() { // from class: com.android.systemui.screenshot.ScreenshotShelfViewProxy.2
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                LegacyScreenshotController.AnonymousClass2 anonymousClass2 = ScreenshotShelfViewProxy.this.callbacks;
                if (anonymousClass2 != null) {
                    anonymousClass2.onUserInteraction();
                }
                return Unit.INSTANCE;
            }
        });
        screenshotShelfView.updateInsets(windowManager.getCurrentWindowMetrics().getWindowInsets());
        final AnonymousClass3 anonymousClass3 = new AnonymousClass3();
        final ?? r11 = new OnBackInvokedCallback() { // from class: com.android.systemui.screenshot.ScreenshotShelfViewProxy$addPredictiveBackListener$onBackInvokedCallback$1
            @Override // android.window.OnBackInvokedCallback
            public final void onBackInvoked() {
                Reflection.getOrCreateKotlinClass(ScreenshotShelfViewProxy.this.getClass()).getSimpleName();
                ((ScreenshotShelfViewProxy.AnonymousClass3) anonymousClass3).invoke(ScreenshotEvent.SCREENSHOT_DISMISSED_OTHER);
            }
        };
        screenshotShelfView.addOnAttachStateChangeListener(new View.OnAttachStateChangeListener() { // from class: com.android.systemui.screenshot.ScreenshotShelfViewProxy$addPredictiveBackListener$1
            @Override // android.view.View.OnAttachStateChangeListener
            public final void onViewAttachedToWindow(View view) {
                Reflection.getOrCreateKotlinClass(ScreenshotShelfViewProxy$addPredictiveBackListener$1.class).getSimpleName();
                OnBackInvokedDispatcher findOnBackInvokedDispatcher = ScreenshotShelfViewProxy.this.view.findOnBackInvokedDispatcher();
                if (findOnBackInvokedDispatcher != null) {
                    findOnBackInvokedDispatcher.registerOnBackInvokedCallback(0, r11);
                }
            }

            @Override // android.view.View.OnAttachStateChangeListener
            public final void onViewDetachedFromWindow(View view) {
                Reflection.getOrCreateKotlinClass(ScreenshotShelfViewProxy$addPredictiveBackListener$1.class).getSimpleName();
                OnBackInvokedDispatcher findOnBackInvokedDispatcher = view.findOnBackInvokedDispatcher();
                if (findOnBackInvokedDispatcher != null) {
                    findOnBackInvokedDispatcher.unregisterOnBackInvokedCallback(r11);
                }
            }
        });
        final AnonymousClass4 anonymousClass4 = new AnonymousClass4();
        screenshotShelfView.setOnKeyListener(new View.OnKeyListener() { // from class: com.android.systemui.screenshot.ScreenshotShelfViewProxy$setOnKeyListener$1
            @Override // android.view.View.OnKeyListener
            public final boolean onKey(View view, int i2, KeyEvent keyEvent) {
                if (i2 != 4 && i2 != 111) {
                    return false;
                }
                Reflection.getOrCreateKotlinClass(ScreenshotShelfViewProxy$setOnKeyListener$1.class).getSimpleName();
                ((ScreenshotShelfViewProxy.AnonymousClass4) Function1.this).invoke(ScreenshotEvent.SCREENSHOT_DISMISSED_OTHER);
                return true;
            }
        });
        Reflection.getOrCreateKotlinClass(ScreenshotShelfViewProxy.class).getSimpleName();
        screenshotShelfView.getViewTreeObserver().addOnComputeInternalInsetsListener(new ViewTreeObserver.OnComputeInternalInsetsListener() { // from class: com.android.systemui.screenshot.ScreenshotShelfViewProxy.6
            public final void onComputeInternalInsets(ViewTreeObserver.InternalInsetsInfo internalInsetsInfo) {
                internalInsetsInfo.setTouchableInsets(3);
                internalInsetsInfo.touchableRegion.set(ScreenshotShelfViewProxy.access$getTouchRegion(ScreenshotShelfViewProxy.this));
            }
        });
        ImageView imageView = screenshotShelfView.screenshotPreview;
        this.screenshotPreview = imageView == null ? null : imageView;
        ImageView imageView2 = screenshotShelfView.blurredScreenshotPreview;
        ImageView imageView3 = imageView2 != null ? imageView2 : null;
        View requireViewById = screenshotShelfView.requireViewById(R.id.screenshot_preview_border);
        float f = imageView3.getContext().getResources().getDisplayMetrics().density * 20.0f;
        ThumbnailObserverGoogle$setViews$rippleRevealEffectDrawCallback$1 thumbnailObserverGoogle$setViews$rippleRevealEffectDrawCallback$1 = new ThumbnailObserverGoogle$setViews$rippleRevealEffectDrawCallback$1(RenderEffect.createBlurEffect(f, f, Shader.TileMode.CLAMP), imageView3);
        ThumbnailObserverGoogle$setViews$glowBorderEffectDrawCallback$1 thumbnailObserverGoogle$setViews$glowBorderEffectDrawCallback$1 = new ThumbnailObserverGoogle$setViews$glowBorderEffectDrawCallback$1(imageView3);
        thumbnailObserverGoogle.getClass();
        thumbnailObserverGoogle.rippleRevealEffect = new RippleRevealEffect(ThumbnailObserverGoogle.createRippleRevealConfig(imageView3), thumbnailObserverGoogle$setViews$rippleRevealEffectDrawCallback$1, thumbnailObserverGoogle$setViews$glowBorderEffectDrawCallback$1);
        imageView3.addOnLayoutChangeListener(thumbnailObserverGoogle.thumbnailLayoutChangeListener);
        thumbnailObserverGoogle.glowBorderEffect = new GlowPieEffect(ThumbnailObserverGoogle.createGlowBorderConfig(requireViewById), new ThumbnailObserverGoogle$setViews$glowBorderEffectDrawCallback$1(requireViewById));
        requireViewById.addOnLayoutChangeListener(thumbnailObserverGoogle.borderLayoutChangeListener);
        screenshotShelfView.addOnAttachStateChangeListener(new View.OnAttachStateChangeListener() { // from class: com.android.systemui.screenshot.ScreenshotShelfViewProxy.7
            @Override // android.view.View.OnAttachStateChangeListener
            public final void onViewAttachedToWindow(View view) {
                final ScreenshotShelfViewProxy screenshotShelfViewProxy = ScreenshotShelfViewProxy.this;
                screenshotShelfViewProxy.stopInputListening();
                InputMonitorCompat inputMonitorCompat = new InputMonitorCompat("Screenshot", screenshotShelfViewProxy.displayId);
                screenshotShelfViewProxy.inputEventReceiver = inputMonitorCompat.getInputReceiver(Looper.getMainLooper(), Choreographer.getInstance(), new InputChannelCompat$InputEventListener() { // from class: com.android.systemui.screenshot.ScreenshotShelfViewProxy$startInputListening$1$1
                    @Override // com.android.systemui.shared.system.InputChannelCompat$InputEventListener
                    public final void onInputEvent(InputEvent inputEvent) {
                        LegacyScreenshotController.AnonymousClass2 anonymousClass2;
                        if (inputEvent instanceof MotionEvent) {
                            MotionEvent motionEvent = (MotionEvent) inputEvent;
                            if (motionEvent.getActionMasked() == 0) {
                                ScreenshotShelfViewProxy screenshotShelfViewProxy2 = ScreenshotShelfViewProxy.this;
                                if (ScreenshotShelfViewProxy.access$getTouchRegion(screenshotShelfViewProxy2).contains((int) motionEvent.getRawX(), (int) motionEvent.getRawY()) || (anonymousClass2 = screenshotShelfViewProxy2.callbacks) == null) {
                                    return;
                                }
                                LegacyScreenshotController.this.setWindowFocusable(false);
                            }
                        }
                    }
                });
                screenshotShelfViewProxy.inputMonitor = inputMonitorCompat;
            }

            @Override // android.view.View.OnAttachStateChangeListener
            public final void onViewDetachedFromWindow(View view) {
                ScreenshotShelfViewProxy.this.stopInputListening();
            }
        });
    }

    public static final Region access$getTouchRegion(ScreenshotShelfViewProxy screenshotShelfViewProxy) {
        Insets insets = screenshotShelfViewProxy.windowManager.getCurrentWindowMetrics().getWindowInsets().getInsets(WindowInsets.Type.systemGestures());
        ScreenshotShelfView screenshotShelfView = screenshotShelfViewProxy.view;
        screenshotShelfView.getClass();
        Region region = new Region();
        int dpToPx = (int) FloatingWindowUtil.dpToPx(screenshotShelfView.displayMetrics, -12.0f);
        ImageView imageView = screenshotShelfView.screenshotPreview;
        if (imageView == null) {
            imageView = null;
        }
        screenshotShelfView.addInsetView(region, imageView, dpToPx);
        View view = screenshotShelfView.actionsContainerBackground;
        if (view == null) {
            view = null;
        }
        screenshotShelfView.addInsetView(region, view, dpToPx);
        View view2 = screenshotShelfView.dismissButton;
        screenshotShelfView.addInsetView(region, view2 != null ? view2 : null, dpToPx);
        View findViewById = screenshotShelfView.findViewById(R.id.screenshot_message_container);
        if (findViewById != null) {
            screenshotShelfView.addInsetView(region, findViewById, dpToPx);
        }
        Rect rect = new Rect(0, 0, insets.left, screenshotShelfView.displayMetrics.heightPixels);
        Region.Op op = Region.Op.UNION;
        region.op(rect, op);
        DisplayMetrics displayMetrics = screenshotShelfView.displayMetrics;
        int i = displayMetrics.widthPixels;
        rect.set(i - insets.right, 0, i, displayMetrics.heightPixels);
        region.op(rect, op);
        return region;
    }

    public final void requestDismissal(ScreenshotEvent screenshotEvent, Float f) {
        Reflection.getOrCreateKotlinClass(ScreenshotShelfViewProxy.class).getSimpleName();
        if (this.isDismissing) {
            Reflection.getOrCreateKotlinClass(ScreenshotShelfViewProxy.class).getSimpleName();
            return;
        }
        if (screenshotEvent != null) {
            this.logger.log(screenshotEvent, 0, this.packageName);
        }
        ScreenshotAnimationController screenshotAnimationController = this.animationController;
        Animator animator = screenshotAnimationController.animator;
        if (animator != null) {
            animator.cancel();
        }
        ScreenshotShelfView screenshotShelfView = screenshotAnimationController.view;
        float f2 = 1.5f;
        if (f != null) {
            f2 = Math.max(1.5f, Math.abs(f.floatValue())) * Math.signum(f.floatValue());
        } else if (screenshotShelfView.getResources().getConfiguration().getLayoutDirection() == 0) {
            f2 = -1.5f;
        }
        float right = f2 < 0.0f ? screenshotAnimationController.actionContainer.getRight() * (-1.0f) : screenshotShelfView.getResources().getDisplayMetrics().widthPixels - screenshotAnimationController.actionContainer.getLeft();
        float translationX = right - screenshotShelfView.getTranslationX();
        ValueAnimator ofFloat = ValueAnimator.ofFloat(screenshotShelfView.getTranslationX(), right);
        ofFloat.addUpdateListener(new ScreenshotAnimationController$getActionsAnimator$1(screenshotAnimationController, 5));
        ofFloat.setDuration((long) Math.abs(translationX / f2));
        ofFloat.addListener(new ScreenshotAnimationController$getEntranceAnimation$$inlined$doOnEnd$1(screenshotAnimationController, 7));
        ofFloat.addListener(new ScreenshotAnimationController$getEntranceAnimation$$inlined$doOnEnd$1(screenshotAnimationController, 6));
        screenshotAnimationController.animator = ofFloat;
        ofFloat.addListener(new AnimatorListenerAdapter() { // from class: com.android.systemui.screenshot.ScreenshotShelfViewProxy$requestDismissal$4
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public final void onAnimationEnd(Animator animator2) {
                ScreenshotShelfViewProxy screenshotShelfViewProxy = ScreenshotShelfViewProxy.this;
                screenshotShelfViewProxy.isDismissing = false;
                LegacyScreenshotController.AnonymousClass2 anonymousClass2 = screenshotShelfViewProxy.callbacks;
                if (anonymousClass2 != null) {
                    anonymousClass2.onDismiss();
                }
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public final void onAnimationStart(Animator animator2) {
                ScreenshotShelfViewProxy.this.isDismissing = true;
            }
        });
        ofFloat.start();
    }

    public final void reset() {
        Animator animator = this.animationController.animator;
        if (animator != null) {
            animator.cancel();
        }
        ScreenshotViewModel screenshotViewModel = this.viewModel;
        screenshotViewModel._preview.setValue(null);
        screenshotViewModel._scrollingScrim.setValue(null);
        screenshotViewModel._badge.setValue(null);
        screenshotViewModel._previewAction.setValue(null);
        screenshotViewModel._actions.setValue(EmptyList.INSTANCE);
        AnimationState animationState = AnimationState.NOT_STARTED;
        StateFlowImpl stateFlowImpl = screenshotViewModel._animationState;
        stateFlowImpl.getClass();
        stateFlowImpl.updateState(null, animationState);
        Boolean bool = Boolean.FALSE;
        StateFlowImpl stateFlowImpl2 = screenshotViewModel._isAnimating;
        stateFlowImpl2.getClass();
        stateFlowImpl2.updateState(null, bool);
        screenshotViewModel._scrollableRect.setValue(null);
    }

    public final void stopInputListening() {
        InputMonitorCompat inputMonitorCompat = this.inputMonitor;
        if (inputMonitorCompat != null) {
            inputMonitorCompat.dispose();
        }
        this.inputMonitor = null;
        InputChannelCompat$InputEventReceiver inputChannelCompat$InputEventReceiver = this.inputEventReceiver;
        if (inputChannelCompat$InputEventReceiver != null) {
            inputChannelCompat$InputEventReceiver.dispose();
        }
        this.inputEventReceiver = null;
    }
}
