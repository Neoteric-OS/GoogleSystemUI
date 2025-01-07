package com.android.systemui.keyguard.ui.view.layout.sections;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewTreeObserver;
import androidx.constraintlayout.core.widgets.ConstraintWidget;
import androidx.constraintlayout.widget.ConstraintHelper;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.R$styleable;
import java.util.Arrays;
import kotlin.collections.ArraysKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class AodBurnInLayer extends ConstraintHelper {
    public final AodBurnInLayer$_predrawListener$1 _predrawListener;
    public float _scaleX;
    public float _scaleY;
    public float _translationX;
    public float _translationY;
    public boolean mApplyElevationOnAttach;
    public boolean mApplyVisibilityOnAttach;
    public float mComputedCenterX;
    public float mComputedCenterY;
    public float mComputedMaxX;
    public float mComputedMaxY;
    public float mComputedMinX;
    public float mComputedMinY;
    public ConstraintLayout mContainer;
    public float mGroupRotateAngle;
    public boolean mNeedBounds;
    public float mRotationCenterX;
    public float mRotationCenterY;
    public float mScaleX;
    public float mScaleY;
    public float mShiftX;
    public float mShiftY;
    public View[] mViews;

    /* JADX WARN: Type inference failed for: r2v5, types: [com.android.systemui.keyguard.ui.view.layout.sections.AodBurnInLayer$_predrawListener$1] */
    public AodBurnInLayer(Context context) {
        super(context);
        this.mRotationCenterX = Float.NaN;
        this.mRotationCenterY = Float.NaN;
        this.mGroupRotateAngle = Float.NaN;
        this.mScaleX = 1.0f;
        this.mScaleY = 1.0f;
        this.mComputedCenterX = Float.NaN;
        this.mComputedCenterY = Float.NaN;
        this.mComputedMaxX = Float.NaN;
        this.mComputedMaxY = Float.NaN;
        this.mComputedMinX = Float.NaN;
        this.mComputedMinY = Float.NaN;
        this.mNeedBounds = true;
        this.mViews = null;
        this.mShiftX = 0.0f;
        this.mShiftY = 0.0f;
        this._scaleX = 1.0f;
        this._scaleY = 1.0f;
        this._predrawListener = new ViewTreeObserver.OnPreDrawListener() { // from class: com.android.systemui.keyguard.ui.view.layout.sections.AodBurnInLayer$_predrawListener$1
            @Override // android.view.ViewTreeObserver.OnPreDrawListener
            public final boolean onPreDraw() {
                AodBurnInLayer aodBurnInLayer = AodBurnInLayer.this;
                aodBurnInLayer.setScaleX$androidx$constraintlayout$helper$widget$Layer(aodBurnInLayer._scaleX);
                AodBurnInLayer aodBurnInLayer2 = AodBurnInLayer.this;
                aodBurnInLayer2.setScaleY$androidx$constraintlayout$helper$widget$Layer(aodBurnInLayer2._scaleY);
                AodBurnInLayer aodBurnInLayer3 = AodBurnInLayer.this;
                aodBurnInLayer3.setTranslationX$androidx$constraintlayout$helper$widget$Layer(aodBurnInLayer3._translationX);
                AodBurnInLayer aodBurnInLayer4 = AodBurnInLayer.this;
                aodBurnInLayer4.setTranslationY$androidx$constraintlayout$helper$widget$Layer(aodBurnInLayer4._translationY);
                return true;
            }
        };
    }

    @Override // androidx.constraintlayout.widget.ConstraintHelper
    public final void addView(View view) {
        if (view == null || ArraysKt.contains(Arrays.copyOf(this.mIds, this.mCount), view.getId())) {
            return;
        }
        super.addView(view);
    }

    @Override // androidx.constraintlayout.widget.ConstraintHelper
    public final void applyLayoutFeaturesInConstraintSet(ConstraintLayout constraintLayout) {
        applyLayoutFeatures(constraintLayout);
    }

    public final void calcCenters() {
        if (this.mContainer == null) {
            return;
        }
        if (this.mNeedBounds || Float.isNaN(this.mComputedCenterX) || Float.isNaN(this.mComputedCenterY)) {
            if (!Float.isNaN(this.mRotationCenterX) && !Float.isNaN(this.mRotationCenterY)) {
                this.mComputedCenterY = this.mRotationCenterY;
                this.mComputedCenterX = this.mRotationCenterX;
                return;
            }
            ConstraintLayout constraintLayout = this.mContainer;
            View[] viewArr = super.mViews;
            if (viewArr == null || viewArr.length != this.mCount) {
                super.mViews = new View[this.mCount];
            }
            for (int i = 0; i < this.mCount; i++) {
                super.mViews[i] = constraintLayout.getViewById(this.mIds[i]);
            }
            View[] viewArr2 = super.mViews;
            int left = viewArr2[0].getLeft();
            int top = viewArr2[0].getTop();
            int right = viewArr2[0].getRight();
            int bottom = viewArr2[0].getBottom();
            for (int i2 = 0; i2 < this.mCount; i2++) {
                View view = viewArr2[i2];
                left = Math.min(left, view.getLeft());
                top = Math.min(top, view.getTop());
                right = Math.max(right, view.getRight());
                bottom = Math.max(bottom, view.getBottom());
            }
            this.mComputedMaxX = right;
            this.mComputedMaxY = bottom;
            this.mComputedMinX = left;
            this.mComputedMinY = top;
            if (Float.isNaN(this.mRotationCenterX)) {
                this.mComputedCenterX = (left + right) / 2;
            } else {
                this.mComputedCenterX = this.mRotationCenterX;
            }
            if (Float.isNaN(this.mRotationCenterY)) {
                this.mComputedCenterY = (top + bottom) / 2;
            } else {
                this.mComputedCenterY = this.mRotationCenterY;
            }
        }
    }

    @Override // android.view.View
    public final float getScaleX() {
        return this._scaleX;
    }

    @Override // android.view.View
    public final float getScaleY() {
        return this._scaleY;
    }

    @Override // android.view.View
    public final float getTranslationX() {
        return this._translationX;
    }

    @Override // android.view.View
    public final float getTranslationY() {
        return this._translationY;
    }

    @Override // androidx.constraintlayout.widget.ConstraintHelper
    public final void init(AttributeSet attributeSet) {
        super.init(attributeSet);
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R$styleable.ConstraintLayout_Layout);
            int indexCount = obtainStyledAttributes.getIndexCount();
            for (int i = 0; i < indexCount; i++) {
                int index = obtainStyledAttributes.getIndex(i);
                if (index == 6) {
                    this.mApplyVisibilityOnAttach = true;
                } else if (index == 22) {
                    this.mApplyElevationOnAttach = true;
                }
            }
            obtainStyledAttributes.recycle();
        }
    }

    @Override // androidx.constraintlayout.widget.ConstraintHelper, android.view.View
    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.mContainer = (ConstraintLayout) getParent();
        if (this.mApplyVisibilityOnAttach || this.mApplyElevationOnAttach) {
            int visibility = getVisibility();
            float elevation = getElevation();
            for (int i = 0; i < this.mCount; i++) {
                View viewById = this.mContainer.getViewById(this.mIds[i]);
                if (viewById != null) {
                    if (this.mApplyVisibilityOnAttach) {
                        viewById.setVisibility(visibility);
                    }
                    if (this.mApplyElevationOnAttach && elevation > 0.0f) {
                        viewById.setTranslationZ(viewById.getTranslationZ() + elevation);
                    }
                }
            }
        }
    }

    public final void reCacheViews() {
        int i;
        if (this.mContainer == null || (i = this.mCount) == 0) {
            return;
        }
        View[] viewArr = this.mViews;
        if (viewArr == null || viewArr.length != i) {
            this.mViews = new View[i];
        }
        for (int i2 = 0; i2 < this.mCount; i2++) {
            this.mViews[i2] = this.mContainer.getViewById(this.mIds[i2]);
        }
    }

    @Override // android.view.View
    public final void setElevation(float f) {
        super.setElevation(f);
        applyLayoutFeatures();
    }

    @Override // android.view.View
    public final void setPivotX(float f) {
        this.mRotationCenterX = f;
        transform();
    }

    @Override // android.view.View
    public final void setPivotY(float f) {
        this.mRotationCenterY = f;
        transform();
    }

    @Override // android.view.View
    public final void setRotation(float f) {
        this.mGroupRotateAngle = f;
        transform();
    }

    @Override // android.view.View
    public final void setScaleX(float f) {
        this._scaleX = f;
        setScaleX$androidx$constraintlayout$helper$widget$Layer(f);
    }

    public final void setScaleX$androidx$constraintlayout$helper$widget$Layer(float f) {
        this.mScaleX = f;
        transform();
    }

    @Override // android.view.View
    public final void setScaleY(float f) {
        this._scaleY = f;
        setScaleY$androidx$constraintlayout$helper$widget$Layer(f);
    }

    public final void setScaleY$androidx$constraintlayout$helper$widget$Layer(float f) {
        this.mScaleY = f;
        transform();
    }

    @Override // android.view.View
    public final void setTranslationX(float f) {
        this._translationX = f;
        setTranslationX$androidx$constraintlayout$helper$widget$Layer(f);
    }

    public final void setTranslationX$androidx$constraintlayout$helper$widget$Layer(float f) {
        this.mShiftX = f;
        transform();
    }

    @Override // android.view.View
    public final void setTranslationY(float f) {
        this._translationY = f;
        setTranslationY$androidx$constraintlayout$helper$widget$Layer(f);
    }

    public final void setTranslationY$androidx$constraintlayout$helper$widget$Layer(float f) {
        this.mShiftY = f;
        transform();
    }

    @Override // android.view.View
    public final void setVisibility(int i) {
        super.setVisibility(i);
        applyLayoutFeatures();
    }

    public final void transform() {
        if (this.mContainer == null) {
            return;
        }
        if (this.mViews == null) {
            reCacheViews();
        }
        calcCenters();
        double radians = Float.isNaN(this.mGroupRotateAngle) ? 0.0d : Math.toRadians(this.mGroupRotateAngle);
        float sin = (float) Math.sin(radians);
        float cos = (float) Math.cos(radians);
        float f = this.mScaleX;
        float f2 = f * cos;
        float f3 = this.mScaleY;
        float f4 = (-f3) * sin;
        float f5 = f * sin;
        float f6 = f3 * cos;
        for (int i = 0; i < this.mCount; i++) {
            View view = this.mViews[i];
            int right = (view.getRight() + view.getLeft()) / 2;
            int bottom = (view.getBottom() + view.getTop()) / 2;
            float f7 = right - this.mComputedCenterX;
            float f8 = bottom - this.mComputedCenterY;
            float f9 = (((f4 * f8) + (f2 * f7)) - f7) + this.mShiftX;
            float f10 = (((f6 * f8) + (f7 * f5)) - f8) + this.mShiftY;
            view.setTranslationX(f9);
            view.setTranslationY(f10);
            view.setScaleY(this.mScaleY);
            view.setScaleX(this.mScaleX);
            if (!Float.isNaN(this.mGroupRotateAngle)) {
                view.setRotation(this.mGroupRotateAngle);
            }
        }
    }

    @Override // androidx.constraintlayout.widget.ConstraintHelper
    public final void updatePostLayout() {
        reCacheViews();
        this.mComputedCenterX = Float.NaN;
        this.mComputedCenterY = Float.NaN;
        ConstraintWidget constraintWidget = ((ConstraintLayout.LayoutParams) getLayoutParams()).mWidget;
        constraintWidget.setWidth(0);
        constraintWidget.setHeight(0);
        calcCenters();
        layout(((int) this.mComputedMinX) - getPaddingLeft(), ((int) this.mComputedMinY) - getPaddingTop(), getPaddingRight() + ((int) this.mComputedMaxX), getPaddingBottom() + ((int) this.mComputedMaxY));
        transform();
    }

    @Override // androidx.constraintlayout.widget.ConstraintHelper
    public final void updatePreDraw(ConstraintLayout constraintLayout) {
        this.mContainer = constraintLayout;
        float rotation = getRotation();
        if (rotation != 0.0f) {
            this.mGroupRotateAngle = rotation;
        } else {
            if (Float.isNaN(this.mGroupRotateAngle)) {
                return;
            }
            this.mGroupRotateAngle = rotation;
        }
    }
}
