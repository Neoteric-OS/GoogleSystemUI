package androidx.constraintlayout.helper.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewParent;
import androidx.constraintlayout.core.widgets.ConstraintWidget;
import androidx.constraintlayout.core.widgets.HelperWidget;
import androidx.constraintlayout.core.widgets.VirtualLayout;
import androidx.constraintlayout.widget.ConstraintHelper;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.constraintlayout.widget.Constraints$LayoutParams;
import androidx.constraintlayout.widget.R$styleable;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public class Flow extends ConstraintHelper {
    public boolean mApplyElevationOnAttach;
    public boolean mApplyVisibilityOnAttach;
    public androidx.constraintlayout.core.widgets.Flow mFlow;

    public Flow(Context context) {
        super(context);
    }

    @Override // androidx.constraintlayout.widget.ConstraintHelper
    public final void applyLayoutFeaturesInConstraintSet(ConstraintLayout constraintLayout) {
        applyLayoutFeatures(constraintLayout);
    }

    @Override // androidx.constraintlayout.widget.ConstraintHelper
    public final void init(AttributeSet attributeSet) {
        super.init(attributeSet);
        int[] iArr = R$styleable.ConstraintLayout_Layout;
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, iArr);
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
        this.mFlow = new androidx.constraintlayout.core.widgets.Flow();
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes2 = getContext().obtainStyledAttributes(attributeSet, iArr);
            int indexCount2 = obtainStyledAttributes2.getIndexCount();
            for (int i2 = 0; i2 < indexCount2; i2++) {
                int index2 = obtainStyledAttributes2.getIndex(i2);
                if (index2 == 0) {
                    this.mFlow.mOrientation = obtainStyledAttributes2.getInt(index2, 0);
                } else if (index2 == 1) {
                    androidx.constraintlayout.core.widgets.Flow flow = this.mFlow;
                    int dimensionPixelSize = obtainStyledAttributes2.getDimensionPixelSize(index2, 0);
                    flow.mPaddingTop = dimensionPixelSize;
                    flow.mPaddingBottom = dimensionPixelSize;
                    flow.mPaddingStart = dimensionPixelSize;
                    flow.mPaddingEnd = dimensionPixelSize;
                } else if (index2 == 18) {
                    androidx.constraintlayout.core.widgets.Flow flow2 = this.mFlow;
                    int dimensionPixelSize2 = obtainStyledAttributes2.getDimensionPixelSize(index2, 0);
                    flow2.mPaddingStart = dimensionPixelSize2;
                    flow2.mResolvedPaddingLeft = dimensionPixelSize2;
                    flow2.mResolvedPaddingRight = dimensionPixelSize2;
                } else if (index2 == 19) {
                    this.mFlow.mPaddingEnd = obtainStyledAttributes2.getDimensionPixelSize(index2, 0);
                } else if (index2 == 2) {
                    this.mFlow.mResolvedPaddingLeft = obtainStyledAttributes2.getDimensionPixelSize(index2, 0);
                } else if (index2 == 3) {
                    this.mFlow.mPaddingTop = obtainStyledAttributes2.getDimensionPixelSize(index2, 0);
                } else if (index2 == 4) {
                    this.mFlow.mResolvedPaddingRight = obtainStyledAttributes2.getDimensionPixelSize(index2, 0);
                } else if (index2 == 5) {
                    this.mFlow.mPaddingBottom = obtainStyledAttributes2.getDimensionPixelSize(index2, 0);
                } else if (index2 == 54) {
                    this.mFlow.mWrapMode = obtainStyledAttributes2.getInt(index2, 0);
                } else if (index2 == 44) {
                    this.mFlow.mHorizontalStyle = obtainStyledAttributes2.getInt(index2, 0);
                } else if (index2 == 53) {
                    this.mFlow.mVerticalStyle = obtainStyledAttributes2.getInt(index2, 0);
                } else if (index2 == 38) {
                    this.mFlow.mFirstHorizontalStyle = obtainStyledAttributes2.getInt(index2, 0);
                } else if (index2 == 46) {
                    this.mFlow.mLastHorizontalStyle = obtainStyledAttributes2.getInt(index2, 0);
                } else if (index2 == 40) {
                    this.mFlow.mFirstVerticalStyle = obtainStyledAttributes2.getInt(index2, 0);
                } else if (index2 == 48) {
                    this.mFlow.mLastVerticalStyle = obtainStyledAttributes2.getInt(index2, 0);
                } else if (index2 == 42) {
                    this.mFlow.mHorizontalBias = obtainStyledAttributes2.getFloat(index2, 0.5f);
                } else if (index2 == 37) {
                    this.mFlow.mFirstHorizontalBias = obtainStyledAttributes2.getFloat(index2, 0.5f);
                } else if (index2 == 45) {
                    this.mFlow.mLastHorizontalBias = obtainStyledAttributes2.getFloat(index2, 0.5f);
                } else if (index2 == 39) {
                    this.mFlow.mFirstVerticalBias = obtainStyledAttributes2.getFloat(index2, 0.5f);
                } else if (index2 == 47) {
                    this.mFlow.mLastVerticalBias = obtainStyledAttributes2.getFloat(index2, 0.5f);
                } else if (index2 == 51) {
                    this.mFlow.mVerticalBias = obtainStyledAttributes2.getFloat(index2, 0.5f);
                } else if (index2 == 41) {
                    this.mFlow.mHorizontalAlign = obtainStyledAttributes2.getInt(index2, 2);
                } else if (index2 == 50) {
                    this.mFlow.mVerticalAlign = obtainStyledAttributes2.getInt(index2, 2);
                } else if (index2 == 43) {
                    this.mFlow.mHorizontalGap = obtainStyledAttributes2.getDimensionPixelSize(index2, 0);
                } else if (index2 == 52) {
                    this.mFlow.mVerticalGap = obtainStyledAttributes2.getDimensionPixelSize(index2, 0);
                } else if (index2 == 49) {
                    this.mFlow.mMaxElementsWrap = obtainStyledAttributes2.getInt(index2, -1);
                }
            }
            obtainStyledAttributes2.recycle();
        }
        this.mHelperWidget = this.mFlow;
        validateParams();
    }

    @Override // androidx.constraintlayout.widget.ConstraintHelper
    public final void loadParameters(ConstraintSet.Constraint constraint, HelperWidget helperWidget, Constraints$LayoutParams constraints$LayoutParams, SparseArray sparseArray) {
        super.loadParameters(constraint, helperWidget, constraints$LayoutParams, sparseArray);
        if (helperWidget instanceof androidx.constraintlayout.core.widgets.Flow) {
            androidx.constraintlayout.core.widgets.Flow flow = (androidx.constraintlayout.core.widgets.Flow) helperWidget;
            int i = constraints$LayoutParams.orientation;
            if (i != -1) {
                flow.mOrientation = i;
            }
        }
    }

    @Override // androidx.constraintlayout.widget.ConstraintHelper, android.view.View
    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.mApplyVisibilityOnAttach || this.mApplyElevationOnAttach) {
            ViewParent parent = getParent();
            if (parent instanceof ConstraintLayout) {
                ConstraintLayout constraintLayout = (ConstraintLayout) parent;
                int visibility = getVisibility();
                float elevation = getElevation();
                for (int i = 0; i < this.mCount; i++) {
                    View viewById = constraintLayout.getViewById(this.mIds[i]);
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
    }

    @Override // androidx.constraintlayout.widget.ConstraintHelper, android.view.View
    public final void onMeasure(int i, int i2) {
        onMeasure(this.mFlow, i, i2);
    }

    @Override // androidx.constraintlayout.widget.ConstraintHelper
    public final void resolveRtl(ConstraintWidget constraintWidget, boolean z) {
        androidx.constraintlayout.core.widgets.Flow flow = this.mFlow;
        int i = flow.mPaddingStart;
        if (i > 0 || flow.mPaddingEnd > 0) {
            if (z) {
                flow.mResolvedPaddingLeft = flow.mPaddingEnd;
                flow.mResolvedPaddingRight = i;
            } else {
                flow.mResolvedPaddingLeft = i;
                flow.mResolvedPaddingRight = flow.mPaddingEnd;
            }
        }
    }

    @Override // android.view.View
    public final void setElevation(float f) {
        super.setElevation(f);
        applyLayoutFeatures();
    }

    @Override // android.view.View
    public final void setVisibility(int i) {
        super.setVisibility(i);
        applyLayoutFeatures();
    }

    public Flow(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public final void onMeasure(VirtualLayout virtualLayout, int i, int i2) {
        int mode = View.MeasureSpec.getMode(i);
        int size = View.MeasureSpec.getSize(i);
        int mode2 = View.MeasureSpec.getMode(i2);
        int size2 = View.MeasureSpec.getSize(i2);
        if (virtualLayout == null) {
            setMeasuredDimension(0, 0);
        } else {
            virtualLayout.measure(mode, size, mode2, size2);
            setMeasuredDimension(virtualLayout.mMeasuredWidth, virtualLayout.mMeasuredHeight);
        }
    }

    public Flow(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }
}
