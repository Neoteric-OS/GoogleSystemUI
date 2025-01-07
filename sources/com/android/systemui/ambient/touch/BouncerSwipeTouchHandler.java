package com.android.systemui.ambient.touch;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.graphics.Rect;
import android.graphics.Region;
import android.util.Log;
import android.view.GestureDetector;
import android.view.InputEvent;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import androidx.lifecycle.LifecycleCoroutineScopeImpl;
import com.android.internal.logging.UiEventLogger;
import com.android.systemui.ambient.touch.BouncerSwipeTouchHandler;
import com.android.systemui.ambient.touch.TouchMonitor;
import com.android.systemui.ambient.touch.dagger.BouncerSwipeModule$$ExternalSyntheticLambda0;
import com.android.systemui.ambient.touch.scrim.ScrimController;
import com.android.systemui.ambient.touch.scrim.ScrimManager;
import com.android.systemui.ambient.touch.scrim.ScrimManager$$ExternalSyntheticLambda0;
import com.android.systemui.communal.ui.viewmodel.CommunalViewModel;
import com.android.systemui.keyguard.domain.interactor.KeyguardInteractor;
import com.android.systemui.plugins.ActivityStarter;
import com.android.systemui.shade.ShadeExpansionChangeEvent;
import com.android.systemui.shared.system.InputChannelCompat$InputEventListener;
import com.android.systemui.statusbar.NotificationShadeWindowController;
import com.android.systemui.statusbar.phone.CentralSurfaces;
import com.android.systemui.statusbar.phone.CentralSurfacesImpl;
import com.android.wm.shell.animation.FlingAnimationUtils;
import java.util.Optional;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.StateFlowImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class BouncerSwipeTouchHandler implements TouchHandler {
    public final ActivityStarter activityStarter;
    public final float bouncerZoneScreenPercentage;
    public Boolean capture;
    public final Optional centralSurfaces;
    public final CommunalViewModel communalViewModel;
    public float currentExpansion;
    public ScrimController currentScrimController;
    public boolean expanded;
    public final FlingAnimationUtils flingAnimationUtils;
    public final FlingAnimationUtils flingAnimationUtilsClosing;
    public final KeyguardInteractor keyguardInteractor;
    public final float minBouncerZoneScreenPercentage;
    public final ScrimManager scrimManager;
    public boolean touchAvailable;
    public TouchMonitor.TouchSessionImpl touchSession;
    public final UiEventLogger uiEventLogger;
    public final BouncerSwipeModule$$ExternalSyntheticLambda0 valueAnimatorCreator;
    public VelocityTracker velocityTracker;
    public final BouncerSwipeModule$$ExternalSyntheticLambda0 velocityTrackerFactory;
    public final BouncerSwipeTouchHandler$scrimManagerCallback$1 scrimManagerCallback = new BouncerSwipeTouchHandler$scrimManagerCallback$1(this);
    public final BouncerSwipeTouchHandler$onGestureListener$1 onGestureListener = new GestureDetector.SimpleOnGestureListener() { // from class: com.android.systemui.ambient.touch.BouncerSwipeTouchHandler$onGestureListener$1
        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
        public final boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
            BouncerSwipeTouchHandler bouncerSwipeTouchHandler = BouncerSwipeTouchHandler.this;
            if (bouncerSwipeTouchHandler.capture == null) {
                bouncerSwipeTouchHandler.capture = Boolean.valueOf(Math.abs((double) f2) > Math.abs((double) f) && f2 > 0.0f && BouncerSwipeTouchHandler.this.touchAvailable);
                if (Intrinsics.areEqual(BouncerSwipeTouchHandler.this.capture, Boolean.TRUE)) {
                    BouncerSwipeTouchHandler bouncerSwipeTouchHandler2 = BouncerSwipeTouchHandler.this;
                    bouncerSwipeTouchHandler2.expanded = false;
                    ScrimController scrimController = bouncerSwipeTouchHandler2.currentScrimController;
                    if (scrimController != null) {
                        scrimController.show();
                    }
                }
            }
            if (!Intrinsics.areEqual(BouncerSwipeTouchHandler.this.capture, Boolean.TRUE)) {
                return false;
            }
            if (BouncerSwipeTouchHandler.this.centralSurfaces.isPresent() && motionEvent != null) {
                final BouncerSwipeTouchHandler bouncerSwipeTouchHandler3 = BouncerSwipeTouchHandler.this;
                if (motionEvent.getY() < motionEvent2.getY()) {
                    return true;
                }
                if (motionEvent.getY() > motionEvent2.getY() && ((Boolean) ((StateFlowImpl) bouncerSwipeTouchHandler3.keyguardInteractor.isKeyguardDismissible).getValue()).booleanValue()) {
                    bouncerSwipeTouchHandler3.activityStarter.executeRunnableDismissingKeyguard(new Runnable() { // from class: com.android.systemui.ambient.touch.BouncerSwipeTouchHandler$onGestureListener$1$onScroll$1$1
                        @Override // java.lang.Runnable
                        public final void run() {
                            ((CentralSurfacesImpl) ((CentralSurfaces) BouncerSwipeTouchHandler.this.centralSurfaces.get())).awakenDreams();
                        }
                    }, null, true, true, false);
                    return true;
                }
                if (bouncerSwipeTouchHandler3.touchSession != null) {
                    float abs = 1 - ((float) (Math.abs(motionEvent.getY() - motionEvent2.getY()) / r12.mBounds.height()));
                    bouncerSwipeTouchHandler3.currentExpansion = abs;
                    ShadeExpansionChangeEvent shadeExpansionChangeEvent = new ShadeExpansionChangeEvent(abs, bouncerSwipeTouchHandler3.expanded, true);
                    ScrimController scrimController2 = bouncerSwipeTouchHandler3.currentScrimController;
                    if (scrimController2 != null) {
                        scrimController2.expand(shadeExpansionChangeEvent);
                    }
                }
            }
            return true;
        }
    };

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.ambient.touch.BouncerSwipeTouchHandler$1, reason: invalid class name */
    final class AnonymousClass1 extends SuspendLambda implements Function2 {
        int label;

        public AnonymousClass1(Continuation continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation create(Object obj, Continuation continuation) {
            return BouncerSwipeTouchHandler.this.new AnonymousClass1(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ((AnonymousClass1) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                final BouncerSwipeTouchHandler bouncerSwipeTouchHandler = BouncerSwipeTouchHandler.this;
                Flow flow = bouncerSwipeTouchHandler.communalViewModel.glanceableTouchAvailable;
                FlowCollector flowCollector = new FlowCollector() { // from class: com.android.systemui.ambient.touch.BouncerSwipeTouchHandler.1.1
                    @Override // kotlinx.coroutines.flow.FlowCollector
                    public final Object emit(Object obj2, Continuation continuation) {
                        BouncerSwipeTouchHandler.this.onGlanceableTouchAvailable(((Boolean) obj2).booleanValue());
                        return Unit.INSTANCE;
                    }
                };
                this.label = 1;
                if (flow.collect(flowCollector, this) == coroutineSingletons) {
                    return coroutineSingletons;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            return Unit.INSTANCE;
        }
    }

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class DreamEvent implements UiEventLogger.UiEventEnum {
        public static final /* synthetic */ DreamEvent[] $VALUES;
        public static final DreamEvent DREAM_BOUNCER_FULLY_VISIBLE;
        public static final DreamEvent DREAM_SWIPED;
        private final int mId;

        static {
            DreamEvent dreamEvent = new DreamEvent("DREAM_SWIPED", 0, 988);
            DREAM_SWIPED = dreamEvent;
            DreamEvent dreamEvent2 = new DreamEvent("DREAM_BOUNCER_FULLY_VISIBLE", 1, 1056);
            DREAM_BOUNCER_FULLY_VISIBLE = dreamEvent2;
            DreamEvent[] dreamEventArr = {dreamEvent, dreamEvent2};
            $VALUES = dreamEventArr;
            EnumEntriesKt.enumEntries(dreamEventArr);
        }

        public DreamEvent(String str, int i, int i2) {
            this.mId = i2;
        }

        public static DreamEvent valueOf(String str) {
            return (DreamEvent) Enum.valueOf(DreamEvent.class, str);
        }

        public static DreamEvent[] values() {
            return (DreamEvent[]) $VALUES.clone();
        }

        public final int getId() {
            return this.mId;
        }
    }

    /* JADX WARN: Type inference failed for: r2v2, types: [com.android.systemui.ambient.touch.BouncerSwipeTouchHandler$onGestureListener$1] */
    public BouncerSwipeTouchHandler(LifecycleCoroutineScopeImpl lifecycleCoroutineScopeImpl, ScrimManager scrimManager, Optional optional, NotificationShadeWindowController notificationShadeWindowController, BouncerSwipeModule$$ExternalSyntheticLambda0 bouncerSwipeModule$$ExternalSyntheticLambda0, BouncerSwipeModule$$ExternalSyntheticLambda0 bouncerSwipeModule$$ExternalSyntheticLambda02, CommunalViewModel communalViewModel, FlingAnimationUtils flingAnimationUtils, FlingAnimationUtils flingAnimationUtils2, float f, float f2, UiEventLogger uiEventLogger, ActivityStarter activityStarter, KeyguardInteractor keyguardInteractor) {
        this.scrimManager = scrimManager;
        this.centralSurfaces = optional;
        this.communalViewModel = communalViewModel;
        this.flingAnimationUtils = flingAnimationUtils;
        this.flingAnimationUtilsClosing = flingAnimationUtils2;
        this.bouncerZoneScreenPercentage = f;
        this.minBouncerZoneScreenPercentage = f2;
        this.uiEventLogger = uiEventLogger;
        this.activityStarter = activityStarter;
        this.keyguardInteractor = keyguardInteractor;
        BuildersKt.launch$default(lifecycleCoroutineScopeImpl, null, null, new AnonymousClass1(null), 3);
    }

    @Override // com.android.systemui.ambient.touch.TouchHandler
    public final void getTouchInitiationRegion(Rect rect, Region region, Rect rect2) {
        int width = rect.width();
        int height = rect.height();
        float f = height;
        float f2 = 1;
        int round = Math.round((f2 - this.minBouncerZoneScreenPercentage) * f);
        Rect rect3 = new Rect(0, Math.round((f2 - this.bouncerZoneScreenPercentage) * f), width, height);
        region.op(rect, Region.Op.UNION);
        if (rect2 != null) {
            region.op(rect2, Region.Op.DIFFERENCE);
        }
        if (rect2 != null) {
            rect3.top = (int) Math.max(rect3.top, (int) Math.min(Math.max(0.0d, rect2.bottom), round));
        }
        region.union(rect3);
    }

    public final void onGlanceableTouchAvailable(boolean z) {
        this.touchAvailable = z;
    }

    @Override // com.android.systemui.ambient.touch.TouchHandler
    public final void onSessionStart(TouchMonitor.TouchSessionImpl touchSessionImpl) {
        VelocityTracker obtain = VelocityTracker.obtain();
        this.velocityTracker = obtain;
        this.touchSession = touchSessionImpl;
        if (obtain != null) {
            obtain.clear();
        }
        ScrimManager scrimManager = this.scrimManager;
        scrimManager.mExecutor.execute(new ScrimManager$$ExternalSyntheticLambda0(scrimManager, this.scrimManagerCallback, 1));
        this.currentScrimController = scrimManager.mCurrentController;
        touchSessionImpl.mCallbacks.add(new TouchHandler$TouchSession$Callback() { // from class: com.android.systemui.ambient.touch.BouncerSwipeTouchHandler$onSessionStart$2
            @Override // com.android.systemui.ambient.touch.TouchHandler$TouchSession$Callback
            public final void onRemoved() {
                BouncerSwipeTouchHandler bouncerSwipeTouchHandler = BouncerSwipeTouchHandler.this;
                VelocityTracker velocityTracker = bouncerSwipeTouchHandler.velocityTracker;
                if (velocityTracker != null) {
                    velocityTracker.recycle();
                }
                bouncerSwipeTouchHandler.velocityTracker = null;
                ScrimManager scrimManager2 = bouncerSwipeTouchHandler.scrimManager;
                scrimManager2.mExecutor.execute(new ScrimManager$$ExternalSyntheticLambda0(scrimManager2, bouncerSwipeTouchHandler.scrimManagerCallback, 0));
                bouncerSwipeTouchHandler.capture = null;
                bouncerSwipeTouchHandler.touchSession = null;
            }
        });
        touchSessionImpl.mGestureListeners.add(this.onGestureListener);
        touchSessionImpl.mEventListeners.add(new InputChannelCompat$InputEventListener() { // from class: com.android.systemui.ambient.touch.BouncerSwipeTouchHandler$onSessionStart$3
            @Override // com.android.systemui.shared.system.InputChannelCompat$InputEventListener
            public final void onInputEvent(InputEvent inputEvent) {
                TouchMonitor.TouchSessionImpl touchSessionImpl2;
                final BouncerSwipeTouchHandler bouncerSwipeTouchHandler = BouncerSwipeTouchHandler.this;
                bouncerSwipeTouchHandler.getClass();
                if (!(inputEvent instanceof MotionEvent)) {
                    Log.e("BouncerSwipeTouchHandler", "non MotionEvent received:" + inputEvent);
                    return;
                }
                MotionEvent motionEvent = (MotionEvent) inputEvent;
                int action = motionEvent.getAction();
                if (action != 1 && action != 3) {
                    VelocityTracker velocityTracker = bouncerSwipeTouchHandler.velocityTracker;
                    Intrinsics.checkNotNull(velocityTracker);
                    velocityTracker.addMovement(motionEvent);
                    return;
                }
                if (Intrinsics.areEqual(bouncerSwipeTouchHandler.capture, Boolean.TRUE)) {
                    bouncerSwipeTouchHandler.communalViewModel.onResetTouchState();
                }
                TouchMonitor.TouchSessionImpl touchSessionImpl3 = bouncerSwipeTouchHandler.touchSession;
                if (touchSessionImpl3 != null) {
                    touchSessionImpl3.pop();
                }
                Boolean bool = bouncerSwipeTouchHandler.capture;
                if (bool == null || !bool.booleanValue()) {
                    return;
                }
                VelocityTracker velocityTracker2 = bouncerSwipeTouchHandler.velocityTracker;
                Intrinsics.checkNotNull(velocityTracker2);
                velocityTracker2.computeCurrentVelocity(1000);
                VelocityTracker velocityTracker3 = bouncerSwipeTouchHandler.velocityTracker;
                Intrinsics.checkNotNull(velocityTracker3);
                float yVelocity = velocityTracker3.getYVelocity();
                Intrinsics.checkNotNull(bouncerSwipeTouchHandler.velocityTracker);
                double abs = Math.abs((float) Math.hypot(r12.getXVelocity(), yVelocity));
                FlingAnimationUtils flingAnimationUtils = bouncerSwipeTouchHandler.flingAnimationUtils;
                boolean z = abs >= ((double) flingAnimationUtils.mMinVelocityPxPerSecond) ? yVelocity > 0.0f : bouncerSwipeTouchHandler.currentExpansion > 0.5f;
                bouncerSwipeTouchHandler.expanded = !z;
                float f = !z ? 0.0f : 1.0f;
                if (f == 0.0f) {
                    bouncerSwipeTouchHandler.uiEventLogger.log(BouncerSwipeTouchHandler.DreamEvent.DREAM_SWIPED);
                }
                if (!bouncerSwipeTouchHandler.centralSurfaces.isPresent() || ((Boolean) ((StateFlowImpl) bouncerSwipeTouchHandler.keyguardInteractor.isKeyguardDismissible).getValue()).booleanValue() || (touchSessionImpl2 = bouncerSwipeTouchHandler.touchSession) == null) {
                    return;
                }
                float height = touchSessionImpl2.mBounds.height();
                float f2 = bouncerSwipeTouchHandler.currentExpansion;
                float f3 = height * f2;
                float f4 = height * f;
                ValueAnimator ofFloat = ValueAnimator.ofFloat(f2, f);
                ofFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.android.systemui.ambient.touch.BouncerSwipeTouchHandler$createExpansionAnimator$1
                    @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                    public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                        float floatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                        BouncerSwipeTouchHandler bouncerSwipeTouchHandler2 = BouncerSwipeTouchHandler.this;
                        bouncerSwipeTouchHandler2.currentExpansion = floatValue;
                        ShadeExpansionChangeEvent shadeExpansionChangeEvent = new ShadeExpansionChangeEvent(floatValue, bouncerSwipeTouchHandler2.expanded, true);
                        ScrimController scrimController = bouncerSwipeTouchHandler2.currentScrimController;
                        if (scrimController != null) {
                            scrimController.expand(shadeExpansionChangeEvent);
                        }
                    }
                });
                if (f == 0.0f) {
                    ofFloat.addListener(new AnimatorListenerAdapter() { // from class: com.android.systemui.ambient.touch.BouncerSwipeTouchHandler$createExpansionAnimator$2
                        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                        public final void onAnimationEnd(Animator animator) {
                            BouncerSwipeTouchHandler.this.uiEventLogger.log(BouncerSwipeTouchHandler.DreamEvent.DREAM_BOUNCER_FULLY_VISIBLE);
                        }
                    });
                }
                if (f == 1.0f) {
                    bouncerSwipeTouchHandler.flingAnimationUtilsClosing.apply(ofFloat, f3, f4, yVelocity, height);
                } else {
                    flingAnimationUtils.apply(ofFloat, f3, f4, yVelocity, height);
                }
                ofFloat.start();
            }
        });
    }
}
