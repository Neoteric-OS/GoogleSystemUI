package com.google.android.material.sidesheet;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.Log;
import android.util.Property;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewParent;
import androidx.activity.BackEventCompat;
import androidx.activity.ComponentActivity$activityResultRegistry$1$$ExternalSyntheticOutline0;
import androidx.appsearch.app.GenericDocument$$ExternalSyntheticOutline0;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.app.NotificationCompat$Builder$$ExternalSyntheticOutline0;
import androidx.core.math.MathUtils;
import androidx.core.view.ViewCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.core.view.accessibility.AccessibilityViewCommand;
import androidx.customview.view.AbsSavedState;
import androidx.customview.widget.ViewDragHelper;
import androidx.interpolator.view.animation.FastOutSlowInInterpolator;
import com.android.wm.shell.R;
import com.google.android.material.R$styleable;
import com.google.android.material.animation.AnimationUtils;
import com.google.android.material.elevation.ElevationOverlayProvider;
import com.google.android.material.motion.MaterialBackHandler;
import com.google.android.material.motion.MaterialSideContainerBackHelper;
import com.google.android.material.resources.MaterialResources;
import com.google.android.material.shape.AbsoluteCornerSize;
import com.google.android.material.shape.MaterialShapeDrawable;
import com.google.android.material.shape.ShapeAppearanceModel;
import com.google.android.material.sidesheet.SideSheetBehavior;
import java.lang.ref.WeakReference;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.WeakHashMap;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public class SideSheetBehavior extends CoordinatorLayout.Behavior implements MaterialBackHandler {
    public final ColorStateList backgroundTint;
    public final Set callbacks;
    public int childWidth;
    public final int coplanarSiblingViewId;
    public WeakReference coplanarSiblingViewRef;
    public final AnonymousClass1 dragCallback;
    public final boolean draggable;
    public final float elevation;
    public final float hideFriction;
    public boolean ignoreEvents;
    public int initialX;
    public int innerMargin;
    public final MaterialShapeDrawable materialShapeDrawable;
    public int parentInnerEdge;
    public int parentWidth;
    public final ShapeAppearanceModel shapeAppearanceModel;
    public SheetDelegate sheetDelegate;
    public MaterialSideContainerBackHelper sideContainerBackHelper;
    public int state;
    public final StateSettlingTracker stateSettlingTracker;
    public VelocityTracker velocityTracker;
    public ViewDragHelper viewDragHelper;
    public WeakReference viewRef;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class StateSettlingTracker {
        public final SideSheetBehavior$StateSettlingTracker$$ExternalSyntheticLambda0 continueSettlingRunnable = new Runnable() { // from class: com.google.android.material.sidesheet.SideSheetBehavior$StateSettlingTracker$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                SideSheetBehavior.StateSettlingTracker stateSettlingTracker = SideSheetBehavior.StateSettlingTracker.this;
                stateSettlingTracker.isContinueSettlingRunnablePosted = false;
                SideSheetBehavior sideSheetBehavior = SideSheetBehavior.this;
                ViewDragHelper viewDragHelper = sideSheetBehavior.viewDragHelper;
                if (viewDragHelper != null && viewDragHelper.continueSettling()) {
                    stateSettlingTracker.continueSettlingToState(stateSettlingTracker.targetState);
                } else if (sideSheetBehavior.state == 2) {
                    sideSheetBehavior.setStateInternal(stateSettlingTracker.targetState);
                }
            }
        };
        public boolean isContinueSettlingRunnablePosted;
        public int targetState;

        /* JADX WARN: Type inference failed for: r1v1, types: [com.google.android.material.sidesheet.SideSheetBehavior$StateSettlingTracker$$ExternalSyntheticLambda0] */
        public StateSettlingTracker() {
        }

        public final void continueSettlingToState(int i) {
            SideSheetBehavior sideSheetBehavior = SideSheetBehavior.this;
            WeakReference weakReference = sideSheetBehavior.viewRef;
            if (weakReference == null || weakReference.get() == null) {
                return;
            }
            this.targetState = i;
            if (this.isContinueSettlingRunnablePosted) {
                return;
            }
            View view = (View) sideSheetBehavior.viewRef.get();
            SideSheetBehavior$StateSettlingTracker$$ExternalSyntheticLambda0 sideSheetBehavior$StateSettlingTracker$$ExternalSyntheticLambda0 = this.continueSettlingRunnable;
            WeakHashMap weakHashMap = ViewCompat.sViewPropertyAnimatorMap;
            view.postOnAnimation(sideSheetBehavior$StateSettlingTracker$$ExternalSyntheticLambda0);
            this.isContinueSettlingRunnablePosted = true;
        }
    }

    /* JADX WARN: Type inference failed for: r0v6, types: [com.google.android.material.sidesheet.SideSheetBehavior$1] */
    public SideSheetBehavior() {
        this.stateSettlingTracker = new StateSettlingTracker();
        this.draggable = true;
        this.state = 5;
        this.hideFriction = 0.1f;
        this.coplanarSiblingViewId = -1;
        this.callbacks = new LinkedHashSet();
        this.dragCallback = new ViewDragHelper.Callback() { // from class: com.google.android.material.sidesheet.SideSheetBehavior.1
            @Override // androidx.customview.widget.ViewDragHelper.Callback
            public final int clampViewPositionHorizontal(View view, int i) {
                SideSheetBehavior sideSheetBehavior = SideSheetBehavior.this;
                return MathUtils.clamp(i, sideSheetBehavior.sheetDelegate.getMinViewPositionHorizontal(), sideSheetBehavior.sheetDelegate.getMaxViewPositionHorizontal());
            }

            @Override // androidx.customview.widget.ViewDragHelper.Callback
            public final int clampViewPositionVertical(View view, int i) {
                return view.getTop();
            }

            @Override // androidx.customview.widget.ViewDragHelper.Callback
            public final int getViewHorizontalDragRange(View view) {
                SideSheetBehavior sideSheetBehavior = SideSheetBehavior.this;
                return sideSheetBehavior.childWidth + sideSheetBehavior.innerMargin;
            }

            @Override // androidx.customview.widget.ViewDragHelper.Callback
            public final void onViewDragStateChanged(int i) {
                if (i == 1) {
                    SideSheetBehavior sideSheetBehavior = SideSheetBehavior.this;
                    if (sideSheetBehavior.draggable) {
                        sideSheetBehavior.setStateInternal(1);
                    }
                }
            }

            @Override // androidx.customview.widget.ViewDragHelper.Callback
            public final void onViewPositionChanged(View view, int i, int i2) {
                ViewGroup.MarginLayoutParams marginLayoutParams;
                SideSheetBehavior sideSheetBehavior = SideSheetBehavior.this;
                WeakReference weakReference = sideSheetBehavior.coplanarSiblingViewRef;
                View view2 = weakReference != null ? (View) weakReference.get() : null;
                if (view2 != null && (marginLayoutParams = (ViewGroup.MarginLayoutParams) view2.getLayoutParams()) != null) {
                    sideSheetBehavior.sheetDelegate.updateCoplanarSiblingLayoutParams(marginLayoutParams, view.getLeft(), view.getRight());
                    view2.setLayoutParams(marginLayoutParams);
                }
                if (sideSheetBehavior.callbacks.isEmpty()) {
                    return;
                }
                sideSheetBehavior.sheetDelegate.calculateSlideOffset(i);
                Iterator it = sideSheetBehavior.callbacks.iterator();
                if (it.hasNext()) {
                    throw NotificationCompat$Builder$$ExternalSyntheticOutline0.m(it);
                }
            }

            /* JADX WARN: Code restructure failed: missing block: B:15:0x0054, code lost:
            
                if (java.lang.Math.abs(r5 - r3.sheetDelegate.getExpandedOffset()) < java.lang.Math.abs(r5 - r3.sheetDelegate.getHiddenOffset())) goto L19;
             */
            /* JADX WARN: Code restructure failed: missing block: B:8:0x0023, code lost:
            
                if (r3.sheetDelegate.isReleasedCloseToInnerEdge(r4) == false) goto L19;
             */
            @Override // androidx.customview.widget.ViewDragHelper.Callback
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public final void onViewReleased(android.view.View r4, float r5, float r6) {
                /*
                    r3 = this;
                    com.google.android.material.sidesheet.SideSheetBehavior r3 = com.google.android.material.sidesheet.SideSheetBehavior.this
                    com.google.android.material.sidesheet.SheetDelegate r0 = r3.sheetDelegate
                    boolean r0 = r0.isExpandingOutwards(r5)
                    r1 = 3
                    if (r0 == 0) goto Lc
                    goto L56
                Lc:
                    com.google.android.material.sidesheet.SheetDelegate r0 = r3.sheetDelegate
                    boolean r0 = r0.shouldHide(r4, r5)
                    r2 = 5
                    if (r0 == 0) goto L27
                    com.google.android.material.sidesheet.SheetDelegate r0 = r3.sheetDelegate
                    boolean r5 = r0.isSwipeSignificant(r5, r6)
                    if (r5 != 0) goto L25
                    com.google.android.material.sidesheet.SheetDelegate r5 = r3.sheetDelegate
                    boolean r5 = r5.isReleasedCloseToInnerEdge(r4)
                    if (r5 == 0) goto L56
                L25:
                    r1 = r2
                    goto L56
                L27:
                    r0 = 0
                    int r0 = (r5 > r0 ? 1 : (r5 == r0 ? 0 : -1))
                    if (r0 == 0) goto L39
                    float r5 = java.lang.Math.abs(r5)
                    float r6 = java.lang.Math.abs(r6)
                    int r5 = (r5 > r6 ? 1 : (r5 == r6 ? 0 : -1))
                    if (r5 <= 0) goto L39
                    goto L25
                L39:
                    int r5 = r4.getLeft()
                    com.google.android.material.sidesheet.SheetDelegate r6 = r3.sheetDelegate
                    int r6 = r6.getExpandedOffset()
                    int r6 = r5 - r6
                    int r6 = java.lang.Math.abs(r6)
                    com.google.android.material.sidesheet.SheetDelegate r0 = r3.sheetDelegate
                    int r0 = r0.getHiddenOffset()
                    int r5 = r5 - r0
                    int r5 = java.lang.Math.abs(r5)
                    if (r6 >= r5) goto L25
                L56:
                    r5 = 1
                    r3.startSettling$1(r1, r4, r5)
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.sidesheet.SideSheetBehavior.AnonymousClass1.onViewReleased(android.view.View, float, float):void");
            }

            @Override // androidx.customview.widget.ViewDragHelper.Callback
            public final boolean tryCaptureView(View view, int i) {
                WeakReference weakReference;
                SideSheetBehavior sideSheetBehavior = SideSheetBehavior.this;
                return (sideSheetBehavior.state == 1 || (weakReference = sideSheetBehavior.viewRef) == null || weakReference.get() != view) ? false : true;
            }
        };
    }

    @Override // com.google.android.material.motion.MaterialBackHandler
    public final void cancelBackProgress() {
        MaterialSideContainerBackHelper materialSideContainerBackHelper = this.sideContainerBackHelper;
        if (materialSideContainerBackHelper == null) {
            return;
        }
        if (materialSideContainerBackHelper.backEvent == null) {
            Log.w("MaterialBackHelper", "Must call startBackProgress() and updateBackProgress() before cancelBackProgress()");
        }
        BackEventCompat backEventCompat = materialSideContainerBackHelper.backEvent;
        materialSideContainerBackHelper.backEvent = null;
        if (backEventCompat == null) {
            return;
        }
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(ObjectAnimator.ofFloat(materialSideContainerBackHelper.view, (Property<View, Float>) View.SCALE_X, 1.0f), ObjectAnimator.ofFloat(materialSideContainerBackHelper.view, (Property<View, Float>) View.SCALE_Y, 1.0f));
        View view = materialSideContainerBackHelper.view;
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            for (int i = 0; i < viewGroup.getChildCount(); i++) {
                animatorSet.playTogether(ObjectAnimator.ofFloat(viewGroup.getChildAt(i), (Property<View, Float>) View.SCALE_Y, 1.0f));
            }
        }
        animatorSet.setDuration(materialSideContainerBackHelper.cancelDuration);
        animatorSet.start();
    }

    public MaterialSideContainerBackHelper getBackHelper() {
        return this.sideContainerBackHelper;
    }

    @Override // com.google.android.material.motion.MaterialBackHandler
    public final void handleBackInvoked() {
        int i;
        final ViewGroup.MarginLayoutParams marginLayoutParams;
        MaterialSideContainerBackHelper materialSideContainerBackHelper = this.sideContainerBackHelper;
        if (materialSideContainerBackHelper == null) {
            return;
        }
        BackEventCompat backEventCompat = materialSideContainerBackHelper.backEvent;
        ValueAnimator.AnimatorUpdateListener animatorUpdateListener = null;
        materialSideContainerBackHelper.backEvent = null;
        int i2 = 5;
        if (backEventCompat == null) {
            setState$2(5);
            return;
        }
        SheetDelegate sheetDelegate = this.sheetDelegate;
        if (sheetDelegate != null && sheetDelegate.getSheetEdge() != 0) {
            i2 = 3;
        }
        AnimatorListenerAdapter animatorListenerAdapter = new AnimatorListenerAdapter() { // from class: com.google.android.material.sidesheet.SideSheetBehavior.2
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public final void onAnimationEnd(Animator animator) {
                SideSheetBehavior.this.setStateInternal(5);
                WeakReference weakReference = SideSheetBehavior.this.viewRef;
                if (weakReference == null || weakReference.get() == null) {
                    return;
                }
                ((View) SideSheetBehavior.this.viewRef.get()).requestLayout();
            }
        };
        WeakReference weakReference = this.coplanarSiblingViewRef;
        final View view = weakReference != null ? (View) weakReference.get() : null;
        if (view != null && (marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams()) != null) {
            final int coplanarSiblingAdjacentMargin = this.sheetDelegate.getCoplanarSiblingAdjacentMargin(marginLayoutParams);
            animatorUpdateListener = new ValueAnimator.AnimatorUpdateListener() { // from class: com.google.android.material.sidesheet.SideSheetBehavior$$ExternalSyntheticLambda2
                @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                    SideSheetBehavior sideSheetBehavior = SideSheetBehavior.this;
                    ViewGroup.MarginLayoutParams marginLayoutParams2 = marginLayoutParams;
                    int i3 = coplanarSiblingAdjacentMargin;
                    View view2 = view;
                    sideSheetBehavior.sheetDelegate.updateCoplanarSiblingAdjacentMargin(marginLayoutParams2, AnimationUtils.lerp(i3, valueAnimator.getAnimatedFraction(), 0));
                    view2.requestLayout();
                }
            };
        }
        boolean z = backEventCompat.swipeEdge == 0;
        View view2 = materialSideContainerBackHelper.view;
        WeakHashMap weakHashMap = ViewCompat.sViewPropertyAnimatorMap;
        boolean z2 = (Gravity.getAbsoluteGravity(i2, view2.getLayoutDirection()) & 3) == 3;
        float scaleX = materialSideContainerBackHelper.view.getScaleX() * materialSideContainerBackHelper.view.getWidth();
        ViewGroup.LayoutParams layoutParams = materialSideContainerBackHelper.view.getLayoutParams();
        if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams marginLayoutParams2 = (ViewGroup.MarginLayoutParams) layoutParams;
            i = z2 ? marginLayoutParams2.leftMargin : marginLayoutParams2.rightMargin;
        } else {
            i = 0;
        }
        float f = scaleX + i;
        View view3 = materialSideContainerBackHelper.view;
        Property property = View.TRANSLATION_X;
        if (z2) {
            f = -f;
        }
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(view3, (Property<View, Float>) property, f);
        if (animatorUpdateListener != null) {
            ofFloat.addUpdateListener(animatorUpdateListener);
        }
        ofFloat.setInterpolator(new FastOutSlowInInterpolator());
        ofFloat.setDuration(AnimationUtils.lerp(materialSideContainerBackHelper.hideDurationMax, backEventCompat.progress, materialSideContainerBackHelper.hideDurationMin));
        ofFloat.addListener(new AnimatorListenerAdapter() { // from class: com.google.android.material.motion.MaterialSideContainerBackHelper.1
            public final /* synthetic */ int val$gravity;
            public final /* synthetic */ boolean val$leftSwipeEdge;

            public AnonymousClass1(boolean z3, int i22) {
                r2 = z3;
                r3 = i22;
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public final void onAnimationEnd(Animator animator) {
                MaterialSideContainerBackHelper.this.view.setTranslationX(0.0f);
                MaterialSideContainerBackHelper.this.updateBackProgress(0.0f, r2, r3);
            }
        });
        ofFloat.addListener(animatorListenerAdapter);
        ofFloat.start();
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public final void onAttachedToLayoutParams(CoordinatorLayout.LayoutParams layoutParams) {
        this.viewRef = null;
        this.viewDragHelper = null;
        this.sideContainerBackHelper = null;
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public final void onDetachedFromLayoutParams() {
        this.viewRef = null;
        this.viewDragHelper = null;
        this.sideContainerBackHelper = null;
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public final boolean onInterceptTouchEvent(CoordinatorLayout coordinatorLayout, View view, MotionEvent motionEvent) {
        ViewDragHelper viewDragHelper;
        VelocityTracker velocityTracker;
        if ((!view.isShown() && ViewCompat.getAccessibilityPaneTitle(view) == null) || !this.draggable) {
            this.ignoreEvents = true;
            return false;
        }
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0 && (velocityTracker = this.velocityTracker) != null) {
            velocityTracker.recycle();
            this.velocityTracker = null;
        }
        if (this.velocityTracker == null) {
            this.velocityTracker = VelocityTracker.obtain();
        }
        this.velocityTracker.addMovement(motionEvent);
        if (actionMasked == 0) {
            this.initialX = (int) motionEvent.getX();
        } else if ((actionMasked == 1 || actionMasked == 3) && this.ignoreEvents) {
            this.ignoreEvents = false;
            return false;
        }
        return (this.ignoreEvents || (viewDragHelper = this.viewDragHelper) == null || !viewDragHelper.shouldInterceptTouchEvent(motionEvent)) ? false : true;
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public final boolean onLayoutChild(CoordinatorLayout coordinatorLayout, View view, int i) {
        View view2;
        View view3;
        int i2;
        View findViewById;
        MaterialShapeDrawable materialShapeDrawable = this.materialShapeDrawable;
        WeakHashMap weakHashMap = ViewCompat.sViewPropertyAnimatorMap;
        if (coordinatorLayout.getFitsSystemWindows() && !view.getFitsSystemWindows()) {
            view.setFitsSystemWindows(true);
        }
        int i3 = 0;
        if (this.viewRef == null) {
            this.viewRef = new WeakReference(view);
            this.sideContainerBackHelper = new MaterialSideContainerBackHelper(view);
            if (materialShapeDrawable != null) {
                view.setBackground(materialShapeDrawable);
                float f = this.elevation;
                if (f == -1.0f) {
                    f = ViewCompat.Api21Impl.getElevation(view);
                }
                materialShapeDrawable.setElevation(f);
            } else {
                ColorStateList colorStateList = this.backgroundTint;
                if (colorStateList != null) {
                    ViewCompat.Api21Impl.setBackgroundTintList(view, colorStateList);
                }
            }
            int i4 = this.state == 5 ? 4 : 0;
            if (view.getVisibility() != i4) {
                view.setVisibility(i4);
            }
            updateAccessibilityActions$1$1();
            if (view.getImportantForAccessibility() == 0) {
                view.setImportantForAccessibility(1);
            }
            if (ViewCompat.getAccessibilityPaneTitle(view) == null) {
                ViewCompat.setAccessibilityPaneTitle(view, view.getResources().getString(R.string.side_sheet_accessibility_pane_title));
            }
        }
        int i5 = Gravity.getAbsoluteGravity(((CoordinatorLayout.LayoutParams) view.getLayoutParams()).gravity, i) == 3 ? 1 : 0;
        SheetDelegate sheetDelegate = this.sheetDelegate;
        if (sheetDelegate == null || sheetDelegate.getSheetEdge() != i5) {
            ShapeAppearanceModel shapeAppearanceModel = this.shapeAppearanceModel;
            CoordinatorLayout.LayoutParams layoutParams = null;
            if (i5 == 0) {
                this.sheetDelegate = new RightSheetDelegate(this);
                if (shapeAppearanceModel != null) {
                    WeakReference weakReference = this.viewRef;
                    if (weakReference != null && (view3 = (View) weakReference.get()) != null && (view3.getLayoutParams() instanceof CoordinatorLayout.LayoutParams)) {
                        layoutParams = (CoordinatorLayout.LayoutParams) view3.getLayoutParams();
                    }
                    if (layoutParams == null || ((ViewGroup.MarginLayoutParams) layoutParams).rightMargin <= 0) {
                        ShapeAppearanceModel.Builder builder = shapeAppearanceModel.toBuilder();
                        builder.topRightCornerSize = new AbsoluteCornerSize(0.0f);
                        builder.bottomRightCornerSize = new AbsoluteCornerSize(0.0f);
                        ShapeAppearanceModel build = builder.build();
                        if (materialShapeDrawable != null) {
                            materialShapeDrawable.setShapeAppearanceModel(build);
                        }
                    }
                }
            } else {
                if (i5 != 1) {
                    throw new IllegalArgumentException(GenericDocument$$ExternalSyntheticOutline0.m("Invalid sheet edge position value: ", ". Must be 0 or 1.", i5));
                }
                this.sheetDelegate = new LeftSheetDelegate(this);
                if (shapeAppearanceModel != null) {
                    WeakReference weakReference2 = this.viewRef;
                    if (weakReference2 != null && (view2 = (View) weakReference2.get()) != null && (view2.getLayoutParams() instanceof CoordinatorLayout.LayoutParams)) {
                        layoutParams = (CoordinatorLayout.LayoutParams) view2.getLayoutParams();
                    }
                    if (layoutParams == null || ((ViewGroup.MarginLayoutParams) layoutParams).leftMargin <= 0) {
                        ShapeAppearanceModel.Builder builder2 = shapeAppearanceModel.toBuilder();
                        builder2.topLeftCornerSize = new AbsoluteCornerSize(0.0f);
                        builder2.bottomLeftCornerSize = new AbsoluteCornerSize(0.0f);
                        ShapeAppearanceModel build2 = builder2.build();
                        if (materialShapeDrawable != null) {
                            materialShapeDrawable.setShapeAppearanceModel(build2);
                        }
                    }
                }
            }
        }
        if (this.viewDragHelper == null) {
            this.viewDragHelper = new ViewDragHelper(coordinatorLayout.getContext(), coordinatorLayout, this.dragCallback);
        }
        int outerEdge = this.sheetDelegate.getOuterEdge(view);
        coordinatorLayout.onLayoutChild(view, i);
        this.parentWidth = coordinatorLayout.getWidth();
        this.parentInnerEdge = this.sheetDelegate.getParentInnerEdge(coordinatorLayout);
        this.childWidth = view.getWidth();
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        this.innerMargin = marginLayoutParams != null ? this.sheetDelegate.calculateInnerMargin(marginLayoutParams) : 0;
        int i6 = this.state;
        if (i6 == 1 || i6 == 2) {
            i3 = outerEdge - this.sheetDelegate.getOuterEdge(view);
        } else if (i6 != 3) {
            if (i6 != 5) {
                throw new IllegalStateException("Unexpected value: " + this.state);
            }
            i3 = this.sheetDelegate.getHiddenOffset();
        }
        view.offsetLeftAndRight(i3);
        if (this.coplanarSiblingViewRef == null && (i2 = this.coplanarSiblingViewId) != -1 && (findViewById = coordinatorLayout.findViewById(i2)) != null) {
            this.coplanarSiblingViewRef = new WeakReference(findViewById);
        }
        Iterator it = this.callbacks.iterator();
        while (it.hasNext()) {
            if (it.next() != null) {
                throw new ClassCastException();
            }
        }
        return true;
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public final boolean onMeasureChild(CoordinatorLayout coordinatorLayout, View view, int i, int i2, int i3) {
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        view.measure(ViewGroup.getChildMeasureSpec(i, coordinatorLayout.getPaddingRight() + coordinatorLayout.getPaddingLeft() + marginLayoutParams.leftMargin + marginLayoutParams.rightMargin + i2, marginLayoutParams.width), ViewGroup.getChildMeasureSpec(i3, coordinatorLayout.getPaddingBottom() + coordinatorLayout.getPaddingTop() + marginLayoutParams.topMargin + marginLayoutParams.bottomMargin, marginLayoutParams.height));
        return true;
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public final void onRestoreInstanceState(View view, Parcelable parcelable) {
        int i = ((SavedState) parcelable).state;
        if (i == 1 || i == 2) {
            i = 5;
        }
        this.state = i;
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public final Parcelable onSaveInstanceState(View view) {
        return new SavedState(View.BaseSavedState.EMPTY_STATE, this);
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public final boolean onTouchEvent(View view, MotionEvent motionEvent) {
        VelocityTracker velocityTracker;
        if (!view.isShown()) {
            return false;
        }
        int actionMasked = motionEvent.getActionMasked();
        if (this.state == 1 && actionMasked == 0) {
            return true;
        }
        if (shouldHandleDraggingWithHelper$1()) {
            this.viewDragHelper.processTouchEvent(motionEvent);
        }
        if (actionMasked == 0 && (velocityTracker = this.velocityTracker) != null) {
            velocityTracker.recycle();
            this.velocityTracker = null;
        }
        if (this.velocityTracker == null) {
            this.velocityTracker = VelocityTracker.obtain();
        }
        this.velocityTracker.addMovement(motionEvent);
        if (shouldHandleDraggingWithHelper$1() && actionMasked == 2 && !this.ignoreEvents && shouldHandleDraggingWithHelper$1()) {
            float abs = Math.abs(this.initialX - motionEvent.getX());
            ViewDragHelper viewDragHelper = this.viewDragHelper;
            if (abs > viewDragHelper.mTouchSlop) {
                viewDragHelper.captureChildView(view, motionEvent.getPointerId(motionEvent.getActionIndex()));
            }
        }
        return !this.ignoreEvents;
    }

    public final void setState$2(final int i) {
        if (i == 1 || i == 2) {
            throw new IllegalArgumentException(ComponentActivity$activityResultRegistry$1$$ExternalSyntheticOutline0.m(new StringBuilder("STATE_"), i == 1 ? "DRAGGING" : "SETTLING", " should not be set externally."));
        }
        WeakReference weakReference = this.viewRef;
        if (weakReference == null || weakReference.get() == null) {
            setStateInternal(i);
            return;
        }
        View view = (View) this.viewRef.get();
        Runnable runnable = new Runnable() { // from class: com.google.android.material.sidesheet.SideSheetBehavior$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                SideSheetBehavior sideSheetBehavior = SideSheetBehavior.this;
                int i2 = i;
                View view2 = (View) sideSheetBehavior.viewRef.get();
                if (view2 != null) {
                    sideSheetBehavior.startSettling$1(i2, view2, false);
                }
            }
        };
        ViewParent parent = view.getParent();
        if (parent != null && parent.isLayoutRequested()) {
            WeakHashMap weakHashMap = ViewCompat.sViewPropertyAnimatorMap;
            if (view.isAttachedToWindow()) {
                view.post(runnable);
                return;
            }
        }
        runnable.run();
    }

    public final void setStateInternal(int i) {
        View view;
        if (this.state == i) {
            return;
        }
        this.state = i;
        WeakReference weakReference = this.viewRef;
        if (weakReference == null || (view = (View) weakReference.get()) == null) {
            return;
        }
        int i2 = this.state == 5 ? 4 : 0;
        if (view.getVisibility() != i2) {
            view.setVisibility(i2);
        }
        Iterator it = this.callbacks.iterator();
        if (it.hasNext()) {
            throw NotificationCompat$Builder$$ExternalSyntheticOutline0.m(it);
        }
        updateAccessibilityActions$1$1();
    }

    public final boolean shouldHandleDraggingWithHelper$1() {
        return this.viewDragHelper != null && (this.draggable || this.state == 1);
    }

    @Override // com.google.android.material.motion.MaterialBackHandler
    public final void startBackProgress(BackEventCompat backEventCompat) {
        MaterialSideContainerBackHelper materialSideContainerBackHelper = this.sideContainerBackHelper;
        if (materialSideContainerBackHelper == null) {
            return;
        }
        materialSideContainerBackHelper.backEvent = backEventCompat;
    }

    /* JADX WARN: Code restructure failed: missing block: B:10:0x002d, code lost:
    
        if (r1.settleCapturedViewAt(r0, r4.getTop()) != false) goto L24;
     */
    /* JADX WARN: Code restructure failed: missing block: B:11:0x004d, code lost:
    
        setStateInternal(2);
        r2.stateSettlingTracker.continueSettlingToState(r3);
     */
    /* JADX WARN: Code restructure failed: missing block: B:12:?, code lost:
    
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x004b, code lost:
    
        if (r4 != false) goto L24;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void startSettling$1(int r3, android.view.View r4, boolean r5) {
        /*
            r2 = this;
            r0 = 3
            if (r3 == r0) goto L19
            r0 = 5
            if (r3 != r0) goto Ld
            com.google.android.material.sidesheet.SheetDelegate r0 = r2.sheetDelegate
            int r0 = r0.getHiddenOffset()
            goto L1f
        Ld:
            java.lang.IllegalArgumentException r2 = new java.lang.IllegalArgumentException
            java.lang.String r4 = "Invalid state to get outer edge offset: "
            java.lang.String r3 = android.frameworks.stats.AnnotationValue$1$$ExternalSyntheticOutline0.m(r3, r4)
            r2.<init>(r3)
            throw r2
        L19:
            com.google.android.material.sidesheet.SheetDelegate r0 = r2.sheetDelegate
            int r0 = r0.getExpandedOffset()
        L1f:
            androidx.customview.widget.ViewDragHelper r1 = r2.viewDragHelper
            if (r1 == 0) goto L57
            if (r5 == 0) goto L30
            int r4 = r4.getTop()
            boolean r4 = r1.settleCapturedViewAt(r0, r4)
            if (r4 == 0) goto L57
            goto L4d
        L30:
            int r5 = r4.getTop()
            r1.mCapturedView = r4
            r4 = -1
            r1.mActivePointerId = r4
            r4 = 0
            boolean r4 = r1.forceSettleCapturedViewAt(r0, r5, r4, r4)
            if (r4 != 0) goto L4b
            int r5 = r1.mDragState
            if (r5 != 0) goto L4b
            android.view.View r5 = r1.mCapturedView
            if (r5 == 0) goto L4b
            r5 = 0
            r1.mCapturedView = r5
        L4b:
            if (r4 == 0) goto L57
        L4d:
            r4 = 2
            r2.setStateInternal(r4)
            com.google.android.material.sidesheet.SideSheetBehavior$StateSettlingTracker r2 = r2.stateSettlingTracker
            r2.continueSettlingToState(r3)
            goto L5a
        L57:
            r2.setStateInternal(r3)
        L5a:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.sidesheet.SideSheetBehavior.startSettling$1(int, android.view.View, boolean):void");
    }

    public final void updateAccessibilityActions$1$1() {
        View view;
        WeakReference weakReference = this.viewRef;
        if (weakReference == null || (view = (View) weakReference.get()) == null) {
            return;
        }
        ViewCompat.removeActionWithId(view, 262144);
        ViewCompat.notifyViewAccessibilityStateChangedIfNeeded(view, 0);
        ViewCompat.removeActionWithId(view, 1048576);
        ViewCompat.notifyViewAccessibilityStateChangedIfNeeded(view, 0);
        final int i = 5;
        if (this.state != 5) {
            ViewCompat.replaceAccessibilityAction(view, AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_DISMISS, null, new AccessibilityViewCommand() { // from class: com.google.android.material.sidesheet.SideSheetBehavior$$ExternalSyntheticLambda0
                @Override // androidx.core.view.accessibility.AccessibilityViewCommand
                public final boolean perform(View view2) {
                    SideSheetBehavior.this.setState$2(i);
                    return true;
                }
            });
        }
        final int i2 = 3;
        if (this.state != 3) {
            ViewCompat.replaceAccessibilityAction(view, AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_EXPAND, null, new AccessibilityViewCommand() { // from class: com.google.android.material.sidesheet.SideSheetBehavior$$ExternalSyntheticLambda0
                @Override // androidx.core.view.accessibility.AccessibilityViewCommand
                public final boolean perform(View view2) {
                    SideSheetBehavior.this.setState$2(i2);
                    return true;
                }
            });
        }
    }

    @Override // com.google.android.material.motion.MaterialBackHandler
    public final void updateBackProgress(BackEventCompat backEventCompat) {
        ViewGroup.MarginLayoutParams marginLayoutParams;
        MaterialSideContainerBackHelper materialSideContainerBackHelper = this.sideContainerBackHelper;
        if (materialSideContainerBackHelper == null) {
            return;
        }
        SheetDelegate sheetDelegate = this.sheetDelegate;
        int i = 5;
        if (sheetDelegate != null && sheetDelegate.getSheetEdge() != 0) {
            i = 3;
        }
        if (materialSideContainerBackHelper.backEvent == null) {
            Log.w("MaterialBackHelper", "Must call startBackProgress() before updateBackProgress()");
        }
        BackEventCompat backEventCompat2 = materialSideContainerBackHelper.backEvent;
        materialSideContainerBackHelper.backEvent = backEventCompat;
        if (backEventCompat2 != null) {
            materialSideContainerBackHelper.updateBackProgress(backEventCompat.progress, backEventCompat.swipeEdge == 0, i);
        }
        WeakReference weakReference = this.viewRef;
        if (weakReference == null || weakReference.get() == null) {
            return;
        }
        View view = (View) this.viewRef.get();
        WeakReference weakReference2 = this.coplanarSiblingViewRef;
        View view2 = weakReference2 != null ? (View) weakReference2.get() : null;
        if (view2 == null || (marginLayoutParams = (ViewGroup.MarginLayoutParams) view2.getLayoutParams()) == null) {
            return;
        }
        this.sheetDelegate.updateCoplanarSiblingAdjacentMargin(marginLayoutParams, (int) ((view.getScaleX() * this.childWidth) + this.innerMargin));
        view2.requestLayout();
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class SavedState extends AbsSavedState {
        public static final Parcelable.Creator CREATOR = new AnonymousClass1();
        public final int state;

        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        /* renamed from: com.google.android.material.sidesheet.SideSheetBehavior$SavedState$1, reason: invalid class name */
        public final class AnonymousClass1 implements Parcelable.ClassLoaderCreator {
            @Override // android.os.Parcelable.ClassLoaderCreator
            public final Object createFromParcel(Parcel parcel, ClassLoader classLoader) {
                return new SavedState(parcel, classLoader);
            }

            @Override // android.os.Parcelable.Creator
            public final Object[] newArray(int i) {
                return new SavedState[i];
            }

            @Override // android.os.Parcelable.Creator
            public final Object createFromParcel(Parcel parcel) {
                return new SavedState(parcel, (ClassLoader) null);
            }
        }

        public SavedState(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            this.state = parcel.readInt();
        }

        @Override // androidx.customview.view.AbsSavedState, android.os.Parcelable
        public final void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeInt(this.state);
        }

        public SavedState(Parcelable parcelable, SideSheetBehavior sideSheetBehavior) {
            super(parcelable);
            this.state = sideSheetBehavior.state;
        }
    }

    /* JADX WARN: Type inference failed for: r3v1, types: [com.google.android.material.sidesheet.SideSheetBehavior$1] */
    public SideSheetBehavior(Context context, AttributeSet attributeSet) {
        this.stateSettlingTracker = new StateSettlingTracker();
        this.draggable = true;
        this.state = 5;
        this.hideFriction = 0.1f;
        this.coplanarSiblingViewId = -1;
        this.callbacks = new LinkedHashSet();
        this.dragCallback = new ViewDragHelper.Callback() { // from class: com.google.android.material.sidesheet.SideSheetBehavior.1
            @Override // androidx.customview.widget.ViewDragHelper.Callback
            public final int clampViewPositionHorizontal(View view, int i) {
                SideSheetBehavior sideSheetBehavior = SideSheetBehavior.this;
                return MathUtils.clamp(i, sideSheetBehavior.sheetDelegate.getMinViewPositionHorizontal(), sideSheetBehavior.sheetDelegate.getMaxViewPositionHorizontal());
            }

            @Override // androidx.customview.widget.ViewDragHelper.Callback
            public final int clampViewPositionVertical(View view, int i) {
                return view.getTop();
            }

            @Override // androidx.customview.widget.ViewDragHelper.Callback
            public final int getViewHorizontalDragRange(View view) {
                SideSheetBehavior sideSheetBehavior = SideSheetBehavior.this;
                return sideSheetBehavior.childWidth + sideSheetBehavior.innerMargin;
            }

            @Override // androidx.customview.widget.ViewDragHelper.Callback
            public final void onViewDragStateChanged(int i) {
                if (i == 1) {
                    SideSheetBehavior sideSheetBehavior = SideSheetBehavior.this;
                    if (sideSheetBehavior.draggable) {
                        sideSheetBehavior.setStateInternal(1);
                    }
                }
            }

            @Override // androidx.customview.widget.ViewDragHelper.Callback
            public final void onViewPositionChanged(View view, int i, int i2) {
                ViewGroup.MarginLayoutParams marginLayoutParams;
                SideSheetBehavior sideSheetBehavior = SideSheetBehavior.this;
                WeakReference weakReference = sideSheetBehavior.coplanarSiblingViewRef;
                View view2 = weakReference != null ? (View) weakReference.get() : null;
                if (view2 != null && (marginLayoutParams = (ViewGroup.MarginLayoutParams) view2.getLayoutParams()) != null) {
                    sideSheetBehavior.sheetDelegate.updateCoplanarSiblingLayoutParams(marginLayoutParams, view.getLeft(), view.getRight());
                    view2.setLayoutParams(marginLayoutParams);
                }
                if (sideSheetBehavior.callbacks.isEmpty()) {
                    return;
                }
                sideSheetBehavior.sheetDelegate.calculateSlideOffset(i);
                Iterator it = sideSheetBehavior.callbacks.iterator();
                if (it.hasNext()) {
                    throw NotificationCompat$Builder$$ExternalSyntheticOutline0.m(it);
                }
            }

            @Override // androidx.customview.widget.ViewDragHelper.Callback
            public final void onViewReleased(View view, float f, float f2) {
                /*
                    this = this;
                    com.google.android.material.sidesheet.SideSheetBehavior r3 = com.google.android.material.sidesheet.SideSheetBehavior.this
                    com.google.android.material.sidesheet.SheetDelegate r0 = r3.sheetDelegate
                    boolean r0 = r0.isExpandingOutwards(r5)
                    r1 = 3
                    if (r0 == 0) goto Lc
                    goto L56
                Lc:
                    com.google.android.material.sidesheet.SheetDelegate r0 = r3.sheetDelegate
                    boolean r0 = r0.shouldHide(r4, r5)
                    r2 = 5
                    if (r0 == 0) goto L27
                    com.google.android.material.sidesheet.SheetDelegate r0 = r3.sheetDelegate
                    boolean r5 = r0.isSwipeSignificant(r5, r6)
                    if (r5 != 0) goto L25
                    com.google.android.material.sidesheet.SheetDelegate r5 = r3.sheetDelegate
                    boolean r5 = r5.isReleasedCloseToInnerEdge(r4)
                    if (r5 == 0) goto L56
                L25:
                    r1 = r2
                    goto L56
                L27:
                    r0 = 0
                    int r0 = (r5 > r0 ? 1 : (r5 == r0 ? 0 : -1))
                    if (r0 == 0) goto L39
                    float r5 = java.lang.Math.abs(r5)
                    float r6 = java.lang.Math.abs(r6)
                    int r5 = (r5 > r6 ? 1 : (r5 == r6 ? 0 : -1))
                    if (r5 <= 0) goto L39
                    goto L25
                L39:
                    int r5 = r4.getLeft()
                    com.google.android.material.sidesheet.SheetDelegate r6 = r3.sheetDelegate
                    int r6 = r6.getExpandedOffset()
                    int r6 = r5 - r6
                    int r6 = java.lang.Math.abs(r6)
                    com.google.android.material.sidesheet.SheetDelegate r0 = r3.sheetDelegate
                    int r0 = r0.getHiddenOffset()
                    int r5 = r5 - r0
                    int r5 = java.lang.Math.abs(r5)
                    if (r6 >= r5) goto L25
                L56:
                    r5 = 1
                    r3.startSettling$1(r1, r4, r5)
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.sidesheet.SideSheetBehavior.AnonymousClass1.onViewReleased(android.view.View, float, float):void");
            }

            @Override // androidx.customview.widget.ViewDragHelper.Callback
            public final boolean tryCaptureView(View view, int i) {
                WeakReference weakReference;
                SideSheetBehavior sideSheetBehavior = SideSheetBehavior.this;
                return (sideSheetBehavior.state == 1 || (weakReference = sideSheetBehavior.viewRef) == null || weakReference.get() != view) ? false : true;
            }
        };
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.SideSheetBehavior_Layout);
        if (obtainStyledAttributes.hasValue(3)) {
            this.backgroundTint = MaterialResources.getColorStateList(context, obtainStyledAttributes, 3);
        }
        if (obtainStyledAttributes.hasValue(6)) {
            this.shapeAppearanceModel = ShapeAppearanceModel.builder(context, attributeSet, 0, R.style.Widget_Material3_SideSheet).build();
        }
        if (obtainStyledAttributes.hasValue(5)) {
            int resourceId = obtainStyledAttributes.getResourceId(5, -1);
            this.coplanarSiblingViewId = resourceId;
            WeakReference weakReference = this.coplanarSiblingViewRef;
            if (weakReference != null) {
                weakReference.clear();
            }
            this.coplanarSiblingViewRef = null;
            WeakReference weakReference2 = this.viewRef;
            if (weakReference2 != null) {
                View view = (View) weakReference2.get();
                if (resourceId != -1) {
                    WeakHashMap weakHashMap = ViewCompat.sViewPropertyAnimatorMap;
                    if (view.isLaidOut()) {
                        view.requestLayout();
                    }
                }
            }
        }
        ShapeAppearanceModel shapeAppearanceModel = this.shapeAppearanceModel;
        if (shapeAppearanceModel != null) {
            MaterialShapeDrawable materialShapeDrawable = new MaterialShapeDrawable(shapeAppearanceModel);
            this.materialShapeDrawable = materialShapeDrawable;
            materialShapeDrawable.drawableState.elevationOverlayProvider = new ElevationOverlayProvider(context);
            materialShapeDrawable.updateZ();
            ColorStateList colorStateList = this.backgroundTint;
            if (colorStateList != null) {
                MaterialShapeDrawable materialShapeDrawable2 = this.materialShapeDrawable;
                MaterialShapeDrawable.MaterialShapeDrawableState materialShapeDrawableState = materialShapeDrawable2.drawableState;
                if (materialShapeDrawableState.fillColor != colorStateList) {
                    materialShapeDrawableState.fillColor = colorStateList;
                    materialShapeDrawable2.onStateChange(materialShapeDrawable2.getState());
                }
            } else {
                TypedValue typedValue = new TypedValue();
                context.getTheme().resolveAttribute(android.R.attr.colorBackground, typedValue, true);
                this.materialShapeDrawable.setTint(typedValue.data);
            }
        }
        this.elevation = obtainStyledAttributes.getDimension(2, -1.0f);
        this.draggable = obtainStyledAttributes.getBoolean(4, true);
        obtainStyledAttributes.recycle();
        ViewConfiguration.get(context).getScaledMaximumFlingVelocity();
    }
}
