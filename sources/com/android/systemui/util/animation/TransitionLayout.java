package com.android.systemui.util.animation;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.PointF;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import com.android.systemui.animation.LaunchableView;
import com.android.systemui.animation.LaunchableViewDelegate;
import com.android.systemui.statusbar.CrossFadeHelper;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class TransitionLayout extends ConstraintLayout implements LaunchableView {
    public final Rect boundsRect;
    public TransitionViewState currentState;
    public final LaunchableViewDelegate delegate;
    public int desiredMeasureHeight;
    public int desiredMeasureWidth;
    public boolean isPreDrawApplicatorRegistered;
    public boolean measureAsConstraint;
    public final Set originalGoneChildrenSet;
    public final Map originalViewAlphas;
    public final TransitionLayout$preDrawApplicator$1 preDrawApplicator;
    public boolean updateScheduled;

    public TransitionLayout(Context context) {
        this(context, null, 0, 6, null);
    }

    public final void applyCurrentState$1() {
        int childCount = getChildCount();
        PointF pointF = this.currentState.contentTranslation;
        int i = (int) pointF.x;
        int i2 = (int) pointF.y;
        for (int i3 = 0; i3 < childCount; i3++) {
            View childAt = getChildAt(i3);
            WidgetState widgetState = (WidgetState) this.currentState.widgetStates.get(Integer.valueOf(childAt.getId()));
            if (widgetState != null) {
                Integer valueOf = (!(childAt instanceof TextView) || widgetState.width >= widgetState.measureWidth) ? null : Integer.valueOf(((TextView) childAt).getLayout().getParagraphDirection(0) == -1 ? widgetState.measureWidth - widgetState.width : 0);
                if (childAt.getMeasuredWidth() != widgetState.measureWidth || childAt.getMeasuredHeight() != widgetState.measureHeight) {
                    childAt.measure(View.MeasureSpec.makeMeasureSpec(widgetState.measureWidth, 1073741824), View.MeasureSpec.makeMeasureSpec(widgetState.measureHeight, 1073741824));
                    childAt.layout(0, 0, childAt.getMeasuredWidth(), childAt.getMeasuredHeight());
                }
                int intValue = valueOf != null ? valueOf.intValue() : 0;
                int i4 = (((int) widgetState.x) + i) - intValue;
                int i5 = ((int) widgetState.y) + i2;
                boolean z = valueOf != null;
                childAt.setLeftTopRightBottom(i4, i5, (z ? widgetState.measureWidth : widgetState.width) + i4, (z ? widgetState.measureHeight : widgetState.height) + i5);
                childAt.setScaleX(widgetState.scale);
                childAt.setScaleY(widgetState.scale);
                Rect clipBounds = childAt.getClipBounds();
                if (clipBounds == null) {
                    clipBounds = new Rect();
                }
                clipBounds.set(intValue, 0, widgetState.width + intValue, widgetState.height);
                childAt.setClipBounds(clipBounds);
                CrossFadeHelper.fadeIn(childAt, widgetState.alpha, false);
                childAt.setVisibility((widgetState.gone || widgetState.alpha == 0.0f) ? 4 : 0);
            }
        }
        int left = getLeft();
        int top = getTop();
        TransitionViewState transitionViewState = this.currentState;
        setLeftTopRightBottom(left, top, transitionViewState.width + left, transitionViewState.height + top);
        this.boundsRect.set(0, 0, getWidth(), getHeight());
        setTranslationX(this.currentState.translation.x);
        setTranslationY(this.currentState.translation.y);
        CrossFadeHelper.fadeIn((View) this, this.currentState.alpha, false);
    }

    public final TransitionViewState calculateViewState(MeasurementInput measurementInput, ConstraintSet constraintSet, TransitionViewState transitionViewState) {
        int childCount = getChildCount();
        int i = 0;
        while (true) {
            float f = 1.0f;
            if (i >= childCount) {
                break;
            }
            View childAt = getChildAt(i);
            if (this.originalGoneChildrenSet.contains(Integer.valueOf(childAt.getId()))) {
                childAt.setVisibility(8);
            }
            Float f2 = (Float) this.originalViewAlphas.get(Integer.valueOf(childAt.getId()));
            if (f2 != null) {
                f = f2.floatValue();
            }
            childAt.setAlpha(f);
            i++;
        }
        constraintSet.applyTo(this);
        int measuredHeight = getMeasuredHeight();
        int measuredWidth = getMeasuredWidth();
        this.measureAsConstraint = true;
        measure(measurementInput.widthMeasureSpec, measurementInput.heightMeasureSpec);
        int left = getLeft();
        int top = getTop();
        layout(left, top, getMeasuredWidth() + left, getMeasuredHeight() + top);
        this.measureAsConstraint = false;
        int childCount2 = getChildCount();
        for (int i2 = 0; i2 < childCount2; i2++) {
            View childAt2 = getChildAt(i2);
            Map map = transitionViewState.widgetStates;
            Integer valueOf = Integer.valueOf(childAt2.getId());
            Object obj = map.get(valueOf);
            if (obj == null) {
                obj = new WidgetState(384);
                map.put(valueOf, obj);
            }
            WidgetState widgetState = (WidgetState) obj;
            boolean z = childAt2.getVisibility() == 8;
            widgetState.gone = z;
            if (z) {
                ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) childAt2.getLayoutParams();
                widgetState.x = layoutParams.mWidget.getX();
                widgetState.y = layoutParams.mWidget.getY();
                widgetState.width = layoutParams.mWidget.getWidth();
                int height = layoutParams.mWidget.getHeight();
                widgetState.height = height;
                widgetState.measureHeight = height;
                widgetState.measureWidth = widgetState.width;
                widgetState.alpha = 0.0f;
                widgetState.scale = 0.0f;
            } else {
                widgetState.x = childAt2.getLeft();
                widgetState.y = childAt2.getTop();
                widgetState.width = childAt2.getWidth();
                int height2 = childAt2.getHeight();
                widgetState.height = height2;
                widgetState.measureWidth = widgetState.width;
                widgetState.measureHeight = height2;
                widgetState.gone = childAt2.getVisibility() == 8;
                widgetState.alpha = childAt2.getAlpha();
                widgetState.scale = 1.0f;
            }
        }
        transitionViewState.width = getMeasuredWidth();
        int measuredHeight2 = getMeasuredHeight();
        transitionViewState.height = measuredHeight2;
        transitionViewState.measureWidth = transitionViewState.width;
        transitionViewState.measureHeight = measuredHeight2;
        transitionViewState.translation.set(0.0f, 0.0f);
        transitionViewState.contentTranslation.set(0.0f, 0.0f);
        transitionViewState.alpha = 1.0f;
        int childCount3 = getChildCount();
        for (int i3 = 0; i3 < childCount3; i3++) {
            View childAt3 = getChildAt(i3);
            WidgetState widgetState2 = (WidgetState) this.currentState.widgetStates.get(Integer.valueOf(childAt3.getId()));
            childAt3.setVisibility((widgetState2 == null || widgetState2.gone) ? 4 : 0);
        }
        setMeasuredDimension(measuredWidth, measuredHeight);
        if (!this.updateScheduled) {
            this.updateScheduled = true;
            if (!this.isPreDrawApplicatorRegistered) {
                getViewTreeObserver().addOnPreDrawListener(this.preDrawApplicator);
                this.isPreDrawApplicatorRegistered = true;
            }
        }
        return transitionViewState;
    }

    @Override // androidx.constraintlayout.widget.ConstraintLayout, android.view.ViewGroup, android.view.View
    public final void dispatchDraw(Canvas canvas) {
        canvas.save();
        canvas.clipRect(this.boundsRect);
        super.dispatchDraw(canvas);
        canvas.restore();
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.isPreDrawApplicatorRegistered) {
            getViewTreeObserver().removeOnPreDrawListener(this.preDrawApplicator);
            this.isPreDrawApplicatorRegistered = false;
        }
    }

    @Override // android.view.View
    public final void onFinishInflate() {
        super.onFinishInflate();
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            if (childAt.getId() == -1) {
                childAt.setId(i);
            }
            if (childAt.getVisibility() == 8) {
                this.originalGoneChildrenSet.add(Integer.valueOf(childAt.getId()));
            }
            this.originalViewAlphas.put(Integer.valueOf(childAt.getId()), Float.valueOf(childAt.getAlpha()));
        }
    }

    @Override // androidx.constraintlayout.widget.ConstraintLayout, android.view.ViewGroup, android.view.View
    public final void onLayout(boolean z, int i, int i2, int i3, int i4) {
        if (this.measureAsConstraint) {
            super.onLayout(z, getLeft(), getTop(), getRight(), getBottom());
            return;
        }
        int childCount = getChildCount();
        for (int i5 = 0; i5 < childCount; i5++) {
            View childAt = getChildAt(i5);
            childAt.layout(0, 0, childAt.getMeasuredWidth(), childAt.getMeasuredHeight());
        }
        applyCurrentState$1();
    }

    @Override // androidx.constraintlayout.widget.ConstraintLayout, android.view.View
    public final void onMeasure(int i, int i2) {
        if (this.measureAsConstraint) {
            super.onMeasure(i, i2);
            return;
        }
        int childCount = getChildCount();
        for (int i3 = 0; i3 < childCount; i3++) {
            View childAt = getChildAt(i3);
            WidgetState widgetState = (WidgetState) this.currentState.widgetStates.get(Integer.valueOf(childAt.getId()));
            if (widgetState != null) {
                childAt.measure(View.MeasureSpec.makeMeasureSpec(widgetState.measureWidth, 1073741824), View.MeasureSpec.makeMeasureSpec(widgetState.measureHeight, 1073741824));
            }
        }
        setMeasuredDimension(this.desiredMeasureWidth, this.desiredMeasureHeight);
    }

    @Override // com.android.systemui.animation.LaunchableView
    public final void setShouldBlockVisibilityChanges(boolean z) {
        this.delegate.setShouldBlockVisibilityChanges(z);
    }

    @Override // android.view.View
    public final void setVisibility(int i) {
        this.delegate.setVisibility(i);
    }

    public TransitionLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0, 4, null);
    }

    public /* synthetic */ TransitionLayout(Context context, AttributeSet attributeSet, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i2 & 2) != 0 ? null : attributeSet, (i2 & 4) != 0 ? 0 : i);
    }

    /* JADX WARN: Type inference failed for: r1v9, types: [com.android.systemui.util.animation.TransitionLayout$preDrawApplicator$1] */
    public TransitionLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.boundsRect = new Rect();
        this.originalGoneChildrenSet = new LinkedHashSet();
        this.originalViewAlphas = new LinkedHashMap();
        this.currentState = new TransitionViewState();
        this.delegate = new LaunchableViewDelegate(this, new Function1() { // from class: com.android.systemui.util.animation.TransitionLayout$delegate$1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                super/*android.view.ViewGroup*/.setVisibility(((Number) obj).intValue());
                return Unit.INSTANCE;
            }
        });
        new LinkedHashMap();
        new PointF();
        new PointF();
        this.preDrawApplicator = new ViewTreeObserver.OnPreDrawListener() { // from class: com.android.systemui.util.animation.TransitionLayout$preDrawApplicator$1
            @Override // android.view.ViewTreeObserver.OnPreDrawListener
            public final boolean onPreDraw() {
                TransitionLayout transitionLayout = TransitionLayout.this;
                transitionLayout.updateScheduled = false;
                transitionLayout.getViewTreeObserver().removeOnPreDrawListener(this);
                TransitionLayout transitionLayout2 = TransitionLayout.this;
                transitionLayout2.isPreDrawApplicatorRegistered = false;
                transitionLayout2.applyCurrentState$1();
                return true;
            }
        };
    }
}
