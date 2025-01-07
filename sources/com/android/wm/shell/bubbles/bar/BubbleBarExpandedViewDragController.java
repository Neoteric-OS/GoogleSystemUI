package com.android.wm.shell.bubbles.bar;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.RectF;
import android.util.Log;
import android.util.Property;
import android.util.Size;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import androidx.dynamicanimation.animation.DynamicAnimation;
import com.android.wm.shell.R;
import com.android.wm.shell.bubbles.BubbleController;
import com.android.wm.shell.bubbles.BubbleController$$ExternalSyntheticLambda2;
import com.android.wm.shell.bubbles.BubblePositioner;
import com.android.wm.shell.bubbles.BubbleViewProvider;
import com.android.wm.shell.bubbles.bar.BubbleBarAnimationHelper;
import com.android.wm.shell.bubbles.bar.BubbleBarAnimationHelper.DragAnimatorListenerAdapter;
import com.android.wm.shell.bubbles.bar.BubbleBarLayerView;
import com.android.wm.shell.bubbles.bar.BubbleExpandedViewPinController;
import com.android.wm.shell.shared.animation.Interpolators;
import com.android.wm.shell.shared.bubbles.BubbleBarLocation;
import com.android.wm.shell.shared.bubbles.DismissCircleView;
import com.android.wm.shell.shared.bubbles.DismissView;
import com.android.wm.shell.shared.bubbles.RelativeTouchListener;
import com.android.wm.shell.shared.magnetictarget.MagnetizedObject;
import com.android.wm.shell.shared.magnetictarget.MagnetizedObject$Companion$magnetizeView$1;
import com.android.wm.shell.shared.magnetictarget.MagnetizedObject$MagneticTarget$updateLocationOnScreen$1;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function5;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class BubbleBarExpandedViewDragController {
    public final BubbleBarAnimationHelper animationHelper;
    public final BubblePositioner bubblePositioner;
    public final DismissView dismissView;
    public final BubbleBarLayerView$$ExternalSyntheticLambda1 dragListener;
    public final BubbleBarExpandedView expandedView;
    public float expandedViewInitialTranslationX;
    public float expandedViewInitialTranslationY;
    public boolean isDragged;
    public boolean isStuckToDismiss;
    public final MagnetizedObject$Companion$magnetizeView$1 magnetizedExpandedView;
    public final BubbleExpandedViewPinController pinController;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class HandleDragListener extends RelativeTouchListener {
        public boolean isMoving;

        public HandleDragListener() {
        }

        public final void finishDrag() {
            BubbleBarExpandedViewDragController bubbleBarExpandedViewDragController = BubbleBarExpandedViewDragController.this;
            if (!bubbleBarExpandedViewDragController.isStuckToDismiss) {
                bubbleBarExpandedViewDragController.pinController.onDragEnd();
                BubbleBarExpandedViewDragController.this.dragListener.f$0.getClass();
                BubbleBarAnimationHelper bubbleBarAnimationHelper = BubbleBarExpandedViewDragController.this.animationHelper;
                BubbleBarExpandedView expandedView = bubbleBarAnimationHelper.getExpandedView();
                if (expandedView == null) {
                    Log.w("BubbleBarAnimationHelper", "Trying to animate expanded view to rest position without a bubble");
                } else {
                    boolean equals = bubbleBarAnimationHelper.mExpandedBubble.getKey().equals("Overflow");
                    BubblePositioner bubblePositioner = bubbleBarAnimationHelper.mPositioner;
                    Point expandedViewRestPosition = bubbleBarAnimationHelper.getExpandedViewRestPosition(new Size(equals ? bubblePositioner.mOverflowWidth : bubblePositioner.mExpandedViewLargeScreenWidth, bubblePositioner.getExpandedViewHeightForBubbleBar(equals)));
                    AnimatorSet animatorSet = new AnimatorSet();
                    animatorSet.playTogether(ObjectAnimator.ofFloat(expandedView, (Property<BubbleBarExpandedView, Float>) View.X, expandedViewRestPosition.x), ObjectAnimator.ofFloat(expandedView, (Property<BubbleBarExpandedView, Float>) View.Y, expandedViewRestPosition.y), ObjectAnimator.ofFloat(expandedView, (Property<BubbleBarExpandedView, Float>) View.SCALE_X, 1.0f), ObjectAnimator.ofFloat(expandedView, (Property<BubbleBarExpandedView, Float>) View.SCALE_Y, 1.0f), ObjectAnimator.ofFloat(expandedView, BubbleBarExpandedView.CORNER_RADIUS, expandedView.mRestingCornerRadius));
                    animatorSet.setDuration(400L).setInterpolator(Interpolators.EMPHASIZED);
                    ObjectAnimator duration = ObjectAnimator.ofFloat(expandedView.mHandleView, (Property<BubbleBarHandleView, Float>) View.ALPHA, 1.0f).setDuration(100L);
                    AnimatorSet animatorSet2 = new AnimatorSet();
                    animatorSet2.playTogether(animatorSet, duration);
                    animatorSet2.addListener(new BubbleBarAnimationHelper.AnonymousClass2(bubbleBarAnimationHelper, expandedView, expandedView, 0));
                    bubbleBarAnimationHelper.cancelAnimations();
                    bubbleBarAnimationHelper.mRunningDragAnimator = animatorSet2;
                    animatorSet2.start();
                }
                BubbleBarExpandedViewDragController.this.dismissView.hide();
            }
            this.isMoving = false;
            BubbleBarExpandedViewDragController.this.isDragged = false;
        }

        @Override // com.android.wm.shell.shared.bubbles.RelativeTouchListener
        public final void onCancel() {
            BubbleBarExpandedViewDragController.this.isStuckToDismiss = false;
            finishDrag();
        }

        @Override // com.android.wm.shell.shared.bubbles.RelativeTouchListener
        public final boolean onDown(View view, MotionEvent motionEvent) {
            BubbleBarExpandedViewDragController bubbleBarExpandedViewDragController = BubbleBarExpandedViewDragController.this;
            if (bubbleBarExpandedViewDragController.expandedView.mIsAnimating) {
                return false;
            }
            boolean isBubbleBarOnLeft = bubbleBarExpandedViewDragController.bubblePositioner.isBubbleBarOnLeft();
            BubbleExpandedViewPinController bubbleExpandedViewPinController = bubbleBarExpandedViewDragController.pinController;
            bubbleExpandedViewPinController.initialLocationOnLeft = isBubbleBarOnLeft;
            bubbleExpandedViewPinController.onLeft = isBubbleBarOnLeft;
            bubbleExpandedViewPinController.screenCenterX = ((Point) ((BubbleExpandedViewPinController.AnonymousClass1) bubbleExpandedViewPinController.screenSizeProvider).invoke()).x / 2;
            RectF rectF = new RectF(0.0f, 0.0f, ((Number) bubbleExpandedViewPinController.exclRectWidth$delegate.getValue()).floatValue(), ((Number) bubbleExpandedViewPinController.exclRectHeight$delegate.getValue()).floatValue());
            rectF.offsetTo(bubbleExpandedViewPinController.screenCenterX - (rectF.width() / 2), ((Point) r7.invoke()).y - rectF.height());
            bubbleExpandedViewPinController.dismissZone = rectF;
            BubbleBarExpandedViewDragController.this.isDragged = true;
            return true;
        }

        @Override // com.android.wm.shell.shared.bubbles.RelativeTouchListener
        public final void onMove(View view, MotionEvent motionEvent, float f, float f2, float f3, float f4) {
            View view2;
            if (!this.isMoving) {
                this.isMoving = true;
                BubbleBarAnimationHelper bubbleBarAnimationHelper = BubbleBarExpandedViewDragController.this.animationHelper;
                BubbleBarExpandedView expandedView = bubbleBarAnimationHelper.getExpandedView();
                if (expandedView == null) {
                    Log.w("BubbleBarAnimationHelper", "Trying to animate start drag without a bubble");
                } else {
                    BubbleBarAnimationHelper.setDragPivot(expandedView);
                    if (true != expandedView.mIsDragging) {
                        expandedView.mIsDragging = true;
                        expandedView.updateSamplingState();
                    }
                    float f5 = expandedView.mDraggedCornerRadius / 0.4f;
                    AnimatorSet animatorSet = new AnimatorSet();
                    animatorSet.playTogether(ObjectAnimator.ofFloat(expandedView, (Property<BubbleBarExpandedView, Float>) View.SCALE_X, 0.4f), ObjectAnimator.ofFloat(expandedView, (Property<BubbleBarExpandedView, Float>) View.SCALE_Y, 0.4f), ObjectAnimator.ofFloat(expandedView, BubbleBarExpandedView.CORNER_RADIUS, f5));
                    animatorSet.setDuration(400L).setInterpolator(Interpolators.EMPHASIZED);
                    ObjectAnimator duration = ObjectAnimator.ofFloat(expandedView.mHandleView, (Property<BubbleBarHandleView, Float>) View.ALPHA, 0.0f).setDuration(100L);
                    AnimatorSet animatorSet2 = new AnimatorSet();
                    animatorSet2.playTogether(animatorSet, duration);
                    animatorSet2.addListener(bubbleBarAnimationHelper.new DragAnimatorListenerAdapter(expandedView));
                    bubbleBarAnimationHelper.cancelAnimations();
                    bubbleBarAnimationHelper.mRunningDragAnimator = animatorSet2;
                    animatorSet2.start();
                }
            }
            BubbleBarExpandedViewDragController bubbleBarExpandedViewDragController = BubbleBarExpandedViewDragController.this;
            bubbleBarExpandedViewDragController.expandedView.setTranslationX(bubbleBarExpandedViewDragController.expandedViewInitialTranslationX + f3);
            BubbleBarExpandedViewDragController bubbleBarExpandedViewDragController2 = BubbleBarExpandedViewDragController.this;
            bubbleBarExpandedViewDragController2.expandedView.setTranslationY(bubbleBarExpandedViewDragController2.expandedViewInitialTranslationY + f4);
            BubbleBarExpandedViewDragController.this.dismissView.show();
            final BubbleExpandedViewPinController bubbleExpandedViewPinController = BubbleBarExpandedViewDragController.this.pinController;
            float rawX = motionEvent.getRawX();
            float rawY = motionEvent.getRawY();
            RectF rectF = bubbleExpandedViewPinController.dismissZone;
            if (rectF == null || !rectF.contains(rawX, rawY)) {
                boolean z = bubbleExpandedViewPinController.onLeft;
                boolean z2 = rawX < ((float) bubbleExpandedViewPinController.screenCenterX);
                bubbleExpandedViewPinController.onLeft = z2;
                if (z != z2) {
                    final BubbleBarLocation bubbleBarLocation = z2 ? BubbleBarLocation.LEFT : BubbleBarLocation.RIGHT;
                    final View view3 = bubbleExpandedViewPinController.dropTargetView;
                    if (view3 == null) {
                        LayoutInflater from = LayoutInflater.from(bubbleExpandedViewPinController.context);
                        BubbleBarLayerView bubbleBarLayerView = bubbleExpandedViewPinController.container;
                        view3 = from.inflate(R.layout.bubble_bar_drop_target, (ViewGroup) bubbleBarLayerView, false);
                        bubbleExpandedViewPinController.dropTargetView = view3;
                        bubbleBarLayerView.addView(view3, 0);
                        view3.setAlpha(0.0f);
                    }
                    if (view3.getAlpha() > 0.0f) {
                        bubbleExpandedViewPinController.animateOut(view3, new Runnable() { // from class: com.android.wm.shell.shared.bubbles.BaseBubblePinController$showDropTarget$1
                            @Override // java.lang.Runnable
                            public final void run() {
                                BubbleExpandedViewPinController.this.updateLocation(bubbleBarLocation);
                                BubbleExpandedViewPinController.this.animateIn(view3);
                            }
                        });
                    } else {
                        bubbleExpandedViewPinController.updateLocation(bubbleBarLocation);
                        bubbleExpandedViewPinController.animateIn(view3);
                    }
                    BubbleBarLayerView.AnonymousClass1 anonymousClass1 = bubbleExpandedViewPinController.listener;
                    if (anonymousClass1 != null) {
                        BubbleController bubbleController = BubbleBarLayerView.this.mBubbleController;
                        if (bubbleController.canShowAsBubbleBar()) {
                            ((BubbleController.IBubblesImpl) bubbleController.mBubbleStateListener.this$0).mListener.call(new BubbleController$$ExternalSyntheticLambda2(bubbleBarLocation));
                        }
                    }
                } else if (bubbleExpandedViewPinController.stuckToDismissTarget && (view2 = bubbleExpandedViewPinController.dropTargetView) != null) {
                    bubbleExpandedViewPinController.animateIn(view2);
                }
                bubbleExpandedViewPinController.stuckToDismissTarget = false;
            }
        }

        @Override // com.android.wm.shell.shared.bubbles.RelativeTouchListener
        public final void onUp(View view, MotionEvent motionEvent, float f, float f2, float f3, float f4) {
            finishDrag();
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class MagnetListener implements MagnetizedObject.MagnetListener {
        public MagnetListener() {
        }

        @Override // com.android.wm.shell.shared.magnetictarget.MagnetizedObject.MagnetListener
        public final void onReleasedInTarget(MagnetizedObject.MagneticTarget magneticTarget, MagnetizedObject magnetizedObject) {
            BubbleBarExpandedViewDragController bubbleBarExpandedViewDragController = BubbleBarExpandedViewDragController.this;
            BubbleBarLayerView bubbleBarLayerView = bubbleBarExpandedViewDragController.dragListener.f$0;
            BubbleViewProvider bubbleViewProvider = bubbleBarLayerView.mExpandedBubble;
            if (bubbleViewProvider != null) {
                BubbleController bubbleController = bubbleBarLayerView.mBubbleController;
                bubbleController.mBubbleData.dismissBubbleWithKey(1, bubbleViewProvider.getKey());
            }
            bubbleBarExpandedViewDragController.pinController.onDragEnd();
            bubbleBarExpandedViewDragController.dismissView.hide();
        }

        @Override // com.android.wm.shell.shared.magnetictarget.MagnetizedObject.MagnetListener
        public final void onStuckToTarget(MagnetizedObject.MagneticTarget magneticTarget, MagnetizedObject magnetizedObject) {
            BubbleBarExpandedViewDragController bubbleBarExpandedViewDragController = BubbleBarExpandedViewDragController.this;
            bubbleBarExpandedViewDragController.isStuckToDismiss = true;
            final BubbleExpandedViewPinController bubbleExpandedViewPinController = bubbleBarExpandedViewDragController.pinController;
            bubbleExpandedViewPinController.stuckToDismissTarget = true;
            boolean z = bubbleExpandedViewPinController.onLeft;
            boolean z2 = bubbleExpandedViewPinController.initialLocationOnLeft;
            final boolean z3 = z != z2;
            if (z3) {
                bubbleExpandedViewPinController.onLeft = z2;
                BubbleBarLayerView.AnonymousClass1 anonymousClass1 = bubbleExpandedViewPinController.listener;
                if (anonymousClass1 != null) {
                    BubbleBarLocation bubbleBarLocation = z2 ? BubbleBarLocation.LEFT : BubbleBarLocation.RIGHT;
                    BubbleController bubbleController = BubbleBarLayerView.this.mBubbleController;
                    if (bubbleController.canShowAsBubbleBar()) {
                        ((BubbleController.IBubblesImpl) bubbleController.mBubbleStateListener.this$0).mListener.call(new BubbleController$$ExternalSyntheticLambda2(bubbleBarLocation));
                    }
                }
            }
            View view = bubbleExpandedViewPinController.dropTargetView;
            if (view != null) {
                bubbleExpandedViewPinController.animateOut(view, new Runnable() { // from class: com.android.wm.shell.shared.bubbles.BaseBubblePinController$onStuckToDismissTarget$1$1
                    @Override // java.lang.Runnable
                    public final void run() {
                        if (z3) {
                            BubbleExpandedViewPinController bubbleExpandedViewPinController2 = bubbleExpandedViewPinController;
                            bubbleExpandedViewPinController2.updateLocation(bubbleExpandedViewPinController2.onLeft ? BubbleBarLocation.LEFT : BubbleBarLocation.RIGHT);
                        }
                    }
                });
            }
        }

        @Override // com.android.wm.shell.shared.magnetictarget.MagnetizedObject.MagnetListener
        public final void onUnstuckFromTarget(MagnetizedObject.MagneticTarget magneticTarget, MagnetizedObject magnetizedObject, float f, float f2, boolean z) {
            BubbleBarExpandedViewDragController bubbleBarExpandedViewDragController = BubbleBarExpandedViewDragController.this;
            bubbleBarExpandedViewDragController.isStuckToDismiss = false;
            BubbleBarAnimationHelper bubbleBarAnimationHelper = bubbleBarExpandedViewDragController.animationHelper;
            BubbleBarExpandedView expandedView = bubbleBarAnimationHelper.getExpandedView();
            if (expandedView == null) {
                Log.w("BubbleBarAnimationHelper", "Trying to unsnap the expanded view from dismiss without a bubble");
                return;
            }
            BubbleBarAnimationHelper.setDragPivot(expandedView);
            float f3 = expandedView.mDraggedCornerRadius / 0.4f;
            AnimatorSet animatorSet = new AnimatorSet();
            Property property = View.SCALE_X;
            Property property2 = View.SCALE_Y;
            animatorSet.playTogether(ObjectAnimator.ofFloat(expandedView, (Property<BubbleBarExpandedView, Float>) property, 0.4f), ObjectAnimator.ofFloat(expandedView, (Property<BubbleBarExpandedView, Float>) property2, 0.4f), ObjectAnimator.ofFloat(expandedView, BubbleBarExpandedView.CORNER_RADIUS, f3), ObjectAnimator.ofFloat(magneticTarget.targetView, (Property<View, Float>) property, 1.0f), ObjectAnimator.ofFloat(magneticTarget.targetView, (Property<View, Float>) property2, 1.0f));
            animatorSet.setDuration(400L).setInterpolator(Interpolators.EMPHASIZED_DECELERATE);
            animatorSet.addListener(bubbleBarAnimationHelper.new DragAnimatorListenerAdapter(expandedView));
            bubbleBarAnimationHelper.cancelAnimations();
            bubbleBarAnimationHelper.mRunningDragAnimator = animatorSet;
            animatorSet.start();
        }
    }

    public BubbleBarExpandedViewDragController(BubbleBarExpandedView bubbleBarExpandedView, DismissView dismissView, BubbleBarAnimationHelper bubbleBarAnimationHelper, BubblePositioner bubblePositioner, BubbleExpandedViewPinController bubbleExpandedViewPinController, BubbleBarLayerView$$ExternalSyntheticLambda1 bubbleBarLayerView$$ExternalSyntheticLambda1) {
        this.expandedView = bubbleBarExpandedView;
        this.dismissView = dismissView;
        this.animationHelper = bubbleBarAnimationHelper;
        this.bubblePositioner = bubblePositioner;
        this.pinController = bubbleExpandedViewPinController;
        this.dragListener = bubbleBarLayerView$$ExternalSyntheticLambda1;
        Context context = bubbleBarExpandedView.getContext();
        DynamicAnimation.AnonymousClass1 anonymousClass1 = DynamicAnimation.TRANSLATION_X;
        DynamicAnimation.AnonymousClass1 anonymousClass12 = DynamicAnimation.TRANSLATION_Y;
        Intrinsics.checkNotNull(context);
        MagnetizedObject$Companion$magnetizeView$1 magnetizedObject$Companion$magnetizeView$1 = new MagnetizedObject$Companion$magnetizeView$1(context, bubbleBarExpandedView, anonymousClass1, anonymousClass12);
        this.magnetizedExpandedView = magnetizedObject$Companion$magnetizeView$1;
        magnetizedObject$Companion$magnetizeView$1.magnetListener = new MagnetListener();
        magnetizedObject$Companion$magnetizeView$1.animateStuckToTarget = new Function5() { // from class: com.android.wm.shell.bubbles.bar.BubbleBarExpandedViewDragController.1
            @Override // kotlin.jvm.functions.Function5
            public final Object invoke(Object obj, Object obj2, Object obj3, Object obj4, Object obj5) {
                MagnetizedObject.MagneticTarget magneticTarget = (MagnetizedObject.MagneticTarget) obj;
                ((Number) obj2).floatValue();
                ((Number) obj3).floatValue();
                ((Boolean) obj4).getClass();
                Function0 function0 = (Function0) obj5;
                BubbleBarAnimationHelper bubbleBarAnimationHelper2 = BubbleBarExpandedViewDragController.this.animationHelper;
                BubbleBarExpandedViewDragController$sam$java_lang_Runnable$0 bubbleBarExpandedViewDragController$sam$java_lang_Runnable$0 = function0 != null ? new BubbleBarExpandedViewDragController$sam$java_lang_Runnable$0(function0) : null;
                BubbleBarExpandedView expandedView = bubbleBarAnimationHelper2.getExpandedView();
                if (expandedView == null) {
                    Log.w("BubbleBarAnimationHelper", "Trying to snap the expanded view to target without a bubble");
                } else {
                    BubbleBarAnimationHelper.setDragPivot(expandedView);
                    int[] iArr = bubbleBarAnimationHelper2.mTmpLocation;
                    expandedView.getLocationOnScreen(iArr);
                    iArr[0] = iArr[0] + ((int) ((expandedView.getScaleX() * expandedView.getWidth()) / 2.0f));
                    int height = iArr[1] + ((int) ((expandedView.getHeight() * 0.2f) / 2.0f));
                    iArr[1] = height;
                    PointF pointF = magneticTarget.centerOnScreen;
                    float f = pointF.x - iArr[0];
                    float f2 = pointF.y - height;
                    float f3 = expandedView.mDraggedCornerRadius / 0.2f;
                    AnimatorSet animatorSet = new AnimatorSet();
                    Property property = View.TRANSLATION_X;
                    float[] fArr = {expandedView.getTranslationX() + f};
                    Property property2 = View.TRANSLATION_Y;
                    float[] fArr2 = {expandedView.getTranslationY() + f2};
                    Property property3 = View.SCALE_X;
                    Property property4 = View.SCALE_Y;
                    animatorSet.playTogether(ObjectAnimator.ofFloat(expandedView, (Property<BubbleBarExpandedView, Float>) property, fArr), ObjectAnimator.ofFloat(expandedView, (Property<BubbleBarExpandedView, Float>) property2, fArr2), ObjectAnimator.ofFloat(expandedView, (Property<BubbleBarExpandedView, Float>) property3, 0.2f), ObjectAnimator.ofFloat(expandedView, (Property<BubbleBarExpandedView, Float>) property4, 0.2f), ObjectAnimator.ofFloat(expandedView, BubbleBarExpandedView.CORNER_RADIUS, f3), ObjectAnimator.ofFloat(magneticTarget.targetView, (Property<View, Float>) property3, 1.25f), ObjectAnimator.ofFloat(magneticTarget.targetView, (Property<View, Float>) property4, 1.25f));
                    animatorSet.setDuration(400L).setInterpolator(Interpolators.EMPHASIZED_DECELERATE);
                    animatorSet.addListener(new BubbleBarAnimationHelper.AnonymousClass2(bubbleBarAnimationHelper2, expandedView, bubbleBarExpandedViewDragController$sam$java_lang_Runnable$0, 1));
                    bubbleBarAnimationHelper2.cancelAnimations();
                    bubbleBarAnimationHelper2.mRunningDragAnimator = animatorSet;
                    animatorSet.start();
                }
                return Unit.INSTANCE;
            }
        };
        DismissCircleView dismissCircleView = dismissView.circle;
        MagnetizedObject.MagneticTarget magneticTarget = new MagnetizedObject.MagneticTarget(dismissCircleView, dismissCircleView.getWidth());
        magnetizedObject$Companion$magnetizeView$1.associatedTargets.add(magneticTarget);
        dismissCircleView.post(new MagnetizedObject$MagneticTarget$updateLocationOnScreen$1(magneticTarget));
        final HandleDragListener handleDragListener = new HandleDragListener();
        bubbleBarExpandedView.mHandleView.setOnTouchListener(new View.OnTouchListener() { // from class: com.android.wm.shell.bubbles.bar.BubbleBarExpandedViewDragController.2
            @Override // android.view.View.OnTouchListener
            public final boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getActionMasked() == 0) {
                    BubbleBarExpandedViewDragController bubbleBarExpandedViewDragController = BubbleBarExpandedViewDragController.this;
                    bubbleBarExpandedViewDragController.expandedViewInitialTranslationX = bubbleBarExpandedViewDragController.expandedView.getTranslationX();
                    BubbleBarExpandedViewDragController bubbleBarExpandedViewDragController2 = BubbleBarExpandedViewDragController.this;
                    bubbleBarExpandedViewDragController2.expandedViewInitialTranslationY = bubbleBarExpandedViewDragController2.expandedView.getTranslationY();
                }
                boolean maybeConsumeMotionEvent = BubbleBarExpandedViewDragController.this.magnetizedExpandedView.maybeConsumeMotionEvent(motionEvent);
                if (motionEvent.getActionMasked() == 2 && maybeConsumeMotionEvent) {
                    return true;
                }
                HandleDragListener handleDragListener2 = handleDragListener;
                Intrinsics.checkNotNull(view);
                return handleDragListener2.onTouch(view, motionEvent) || maybeConsumeMotionEvent;
            }
        });
    }
}
