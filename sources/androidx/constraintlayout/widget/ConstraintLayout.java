package androidx.constraintlayout.widget;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.View;
import android.view.ViewGroup;
import androidx.constraintlayout.core.LinearSystem;
import androidx.constraintlayout.core.widgets.ConstraintAnchor;
import androidx.constraintlayout.core.widgets.ConstraintWidget;
import androidx.constraintlayout.core.widgets.ConstraintWidgetContainer;
import androidx.constraintlayout.core.widgets.HelperWidget;
import com.android.app.viewcapture.data.ViewNode;
import com.android.keyguard.KeyguardSecurityContainer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public class ConstraintLayout extends ViewGroup {
    public static SharedValues sSharedValues;
    public final SparseArray mChildrenByIds;
    public final ArrayList mConstraintHelpers;
    public ConstraintLayoutStates mConstraintLayoutSpec;
    public ConstraintSet mConstraintSet;
    public int mConstraintSetId;
    public HashMap mDesignIds;
    public boolean mDirtyHierarchy;
    public final ConstraintWidgetContainer mLayoutWidget;
    public int mMaxHeight;
    public int mMaxWidth;
    public final Measurer mMeasurer;
    public int mMinHeight;
    public int mMinWidth;
    public int mOptimizationLevel;
    public final SparseArray mTempMapIdToWidget;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public class LayoutParams extends ViewGroup.MarginLayoutParams {
        public int baselineMargin;
        public int baselineToBaseline;
        public int baselineToBottom;
        public int baselineToTop;
        public int bottomToBottom;
        public int bottomToTop;
        public float circleAngle;
        public int circleConstraint;
        public int circleRadius;
        public boolean constrainedHeight;
        public boolean constrainedWidth;
        public String constraintTag;
        public String dimensionRatio;
        public int editorAbsoluteX;
        public int editorAbsoluteY;
        public int endToEnd;
        public int endToStart;
        public int goneBaselineMargin;
        public int goneBottomMargin;
        public int goneEndMargin;
        public int goneLeftMargin;
        public int goneRightMargin;
        public int goneStartMargin;
        public int goneTopMargin;
        public int guideBegin;
        public int guideEnd;
        public float guidePercent;
        public boolean guidelineUseRtl;
        public float horizontalBias;
        public int horizontalChainStyle;
        public float horizontalWeight;
        public int leftToLeft;
        public int leftToRight;
        public boolean mHorizontalDimensionFixed;
        public boolean mIsGuideline;
        public boolean mIsHelper;
        public boolean mNeedsBaseline;
        public int mResolveGoneLeftMargin;
        public int mResolveGoneRightMargin;
        public int mResolvedGuideBegin;
        public int mResolvedGuideEnd;
        public float mResolvedGuidePercent;
        public float mResolvedHorizontalBias;
        public int mResolvedLeftToLeft;
        public int mResolvedLeftToRight;
        public int mResolvedRightToLeft;
        public int mResolvedRightToRight;
        public boolean mVerticalDimensionFixed;
        public ConstraintWidget mWidget;
        public int matchConstraintDefaultHeight;
        public int matchConstraintDefaultWidth;
        public int matchConstraintMaxHeight;
        public int matchConstraintMaxWidth;
        public int matchConstraintMinHeight;
        public int matchConstraintMinWidth;
        public float matchConstraintPercentHeight;
        public float matchConstraintPercentWidth;
        public int orientation;
        public int rightToLeft;
        public int rightToRight;
        public int startToEnd;
        public int startToStart;
        public int topToBottom;
        public int topToTop;
        public float verticalBias;
        public int verticalChainStyle;
        public float verticalWeight;
        public int wrapBehaviorInParent;

        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        public abstract class Table {
            public static final SparseIntArray sMap;

            static {
                SparseIntArray sparseIntArray = new SparseIntArray();
                sMap = sparseIntArray;
                sparseIntArray.append(98, 64);
                sparseIntArray.append(75, 65);
                sparseIntArray.append(84, 8);
                sparseIntArray.append(85, 9);
                sparseIntArray.append(87, 10);
                sparseIntArray.append(88, 11);
                sparseIntArray.append(94, 12);
                sparseIntArray.append(93, 13);
                sparseIntArray.append(65, 14);
                sparseIntArray.append(64, 15);
                sparseIntArray.append(60, 16);
                sparseIntArray.append(62, 52);
                sparseIntArray.append(61, 53);
                sparseIntArray.append(66, 2);
                sparseIntArray.append(68, 3);
                sparseIntArray.append(67, 4);
                sparseIntArray.append(103, 49);
                sparseIntArray.append(104, 50);
                sparseIntArray.append(72, 5);
                sparseIntArray.append(73, 6);
                sparseIntArray.append(74, 7);
                sparseIntArray.append(55, 67);
                sparseIntArray.append(0, 1);
                sparseIntArray.append(89, 17);
                sparseIntArray.append(90, 18);
                sparseIntArray.append(71, 19);
                sparseIntArray.append(70, 20);
                sparseIntArray.append(108, 21);
                sparseIntArray.append(111, 22);
                sparseIntArray.append(109, 23);
                sparseIntArray.append(106, 24);
                sparseIntArray.append(110, 25);
                sparseIntArray.append(107, 26);
                sparseIntArray.append(105, 55);
                sparseIntArray.append(112, 54);
                sparseIntArray.append(80, 29);
                sparseIntArray.append(95, 30);
                sparseIntArray.append(69, 44);
                sparseIntArray.append(82, 45);
                sparseIntArray.append(97, 46);
                sparseIntArray.append(81, 47);
                sparseIntArray.append(96, 48);
                sparseIntArray.append(58, 27);
                sparseIntArray.append(57, 28);
                sparseIntArray.append(99, 31);
                sparseIntArray.append(76, 32);
                sparseIntArray.append(101, 33);
                sparseIntArray.append(100, 34);
                sparseIntArray.append(102, 35);
                sparseIntArray.append(78, 36);
                sparseIntArray.append(77, 37);
                sparseIntArray.append(79, 38);
                sparseIntArray.append(83, 39);
                sparseIntArray.append(92, 40);
                sparseIntArray.append(86, 41);
                sparseIntArray.append(63, 42);
                sparseIntArray.append(59, 43);
                sparseIntArray.append(91, 51);
                sparseIntArray.append(114, 66);
            }
        }

        public LayoutParams(int i) {
            super(-2, i);
            this.guideBegin = -1;
            this.guideEnd = -1;
            this.guidePercent = -1.0f;
            this.guidelineUseRtl = true;
            this.leftToLeft = -1;
            this.leftToRight = -1;
            this.rightToLeft = -1;
            this.rightToRight = -1;
            this.topToTop = -1;
            this.topToBottom = -1;
            this.bottomToTop = -1;
            this.bottomToBottom = -1;
            this.baselineToBaseline = -1;
            this.baselineToTop = -1;
            this.baselineToBottom = -1;
            this.circleConstraint = -1;
            this.circleRadius = 0;
            this.circleAngle = 0.0f;
            this.startToEnd = -1;
            this.startToStart = -1;
            this.endToStart = -1;
            this.endToEnd = -1;
            this.goneLeftMargin = Integer.MIN_VALUE;
            this.goneTopMargin = Integer.MIN_VALUE;
            this.goneRightMargin = Integer.MIN_VALUE;
            this.goneBottomMargin = Integer.MIN_VALUE;
            this.goneStartMargin = Integer.MIN_VALUE;
            this.goneEndMargin = Integer.MIN_VALUE;
            this.goneBaselineMargin = Integer.MIN_VALUE;
            this.baselineMargin = 0;
            this.horizontalBias = 0.5f;
            this.verticalBias = 0.5f;
            this.dimensionRatio = null;
            this.horizontalWeight = -1.0f;
            this.verticalWeight = -1.0f;
            this.horizontalChainStyle = 0;
            this.verticalChainStyle = 0;
            this.matchConstraintDefaultWidth = 0;
            this.matchConstraintDefaultHeight = 0;
            this.matchConstraintMinWidth = 0;
            this.matchConstraintMinHeight = 0;
            this.matchConstraintMaxWidth = 0;
            this.matchConstraintMaxHeight = 0;
            this.matchConstraintPercentWidth = 1.0f;
            this.matchConstraintPercentHeight = 1.0f;
            this.editorAbsoluteX = -1;
            this.editorAbsoluteY = -1;
            this.orientation = -1;
            this.constrainedWidth = false;
            this.constrainedHeight = false;
            this.constraintTag = null;
            this.wrapBehaviorInParent = 0;
            this.mHorizontalDimensionFixed = true;
            this.mVerticalDimensionFixed = true;
            this.mNeedsBaseline = false;
            this.mIsGuideline = false;
            this.mIsHelper = false;
            this.mResolvedLeftToLeft = -1;
            this.mResolvedLeftToRight = -1;
            this.mResolvedRightToLeft = -1;
            this.mResolvedRightToRight = -1;
            this.mResolveGoneLeftMargin = Integer.MIN_VALUE;
            this.mResolveGoneRightMargin = Integer.MIN_VALUE;
            this.mResolvedHorizontalBias = 0.5f;
            this.mWidget = new ConstraintWidget();
        }

        /* JADX WARN: Removed duplicated region for block: B:12:0x004a  */
        /* JADX WARN: Removed duplicated region for block: B:15:0x0051  */
        /* JADX WARN: Removed duplicated region for block: B:18:0x0058  */
        /* JADX WARN: Removed duplicated region for block: B:21:0x005e  */
        /* JADX WARN: Removed duplicated region for block: B:24:0x0064  */
        /* JADX WARN: Removed duplicated region for block: B:33:0x007a  */
        /* JADX WARN: Removed duplicated region for block: B:34:0x0082  */
        @Override // android.view.ViewGroup.MarginLayoutParams, android.view.ViewGroup.LayoutParams
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final void resolveLayoutDirection(int r11) {
            /*
                Method dump skipped, instructions count: 259
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.widget.ConstraintLayout.LayoutParams.resolveLayoutDirection(int):void");
        }

        public final void validate() {
            this.mIsGuideline = false;
            this.mHorizontalDimensionFixed = true;
            this.mVerticalDimensionFixed = true;
            int i = ((ViewGroup.MarginLayoutParams) this).width;
            if (i == -2 && this.constrainedWidth) {
                this.mHorizontalDimensionFixed = false;
                if (this.matchConstraintDefaultWidth == 0) {
                    this.matchConstraintDefaultWidth = 1;
                }
            }
            int i2 = ((ViewGroup.MarginLayoutParams) this).height;
            if (i2 == -2 && this.constrainedHeight) {
                this.mVerticalDimensionFixed = false;
                if (this.matchConstraintDefaultHeight == 0) {
                    this.matchConstraintDefaultHeight = 1;
                }
            }
            if (i == 0 || i == -1) {
                this.mHorizontalDimensionFixed = false;
                if (i == 0 && this.matchConstraintDefaultWidth == 1) {
                    ((ViewGroup.MarginLayoutParams) this).width = -2;
                    this.constrainedWidth = true;
                }
            }
            if (i2 == 0 || i2 == -1) {
                this.mVerticalDimensionFixed = false;
                if (i2 == 0 && this.matchConstraintDefaultHeight == 1) {
                    ((ViewGroup.MarginLayoutParams) this).height = -2;
                    this.constrainedHeight = true;
                }
            }
            if (this.guidePercent == -1.0f && this.guideBegin == -1 && this.guideEnd == -1) {
                return;
            }
            this.mIsGuideline = true;
            this.mHorizontalDimensionFixed = true;
            this.mVerticalDimensionFixed = true;
            if (!(this.mWidget instanceof androidx.constraintlayout.core.widgets.Guideline)) {
                this.mWidget = new androidx.constraintlayout.core.widgets.Guideline();
            }
            ((androidx.constraintlayout.core.widgets.Guideline) this.mWidget).setOrientation(this.orientation);
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Measurer {
        public final ConstraintLayout mLayout;
        public int mLayoutHeightSpec;
        public int mLayoutWidthSpec;
        public int mPaddingBottom;
        public int mPaddingHeight;
        public int mPaddingTop;
        public int mPaddingWidth;

        public Measurer(ConstraintLayout constraintLayout) {
            this.mLayout = constraintLayout;
        }

        public static boolean isSimilarSpec(int i, int i2, int i3) {
            if (i == i2) {
                return true;
            }
            int mode = View.MeasureSpec.getMode(i);
            return View.MeasureSpec.getMode(i2) == 1073741824 && (mode == Integer.MIN_VALUE || mode == 0) && i3 == View.MeasureSpec.getSize(i2);
        }

        /* JADX WARN: Code restructure failed: missing block: B:162:0x00ec, code lost:
        
            if (r9 == 2) goto L75;
         */
        /* JADX WARN: Removed duplicated region for block: B:147:0x01a2  */
        /* JADX WARN: Removed duplicated region for block: B:148:0x019d  */
        /* JADX WARN: Removed duplicated region for block: B:178:0x0122  */
        /* JADX WARN: Removed duplicated region for block: B:21:0x00b4  */
        /* JADX WARN: Removed duplicated region for block: B:52:0x019b  */
        /* JADX WARN: Removed duplicated region for block: B:54:0x01a0  */
        /* JADX WARN: Removed duplicated region for block: B:72:0x01d0 A[RETURN] */
        /* JADX WARN: Removed duplicated region for block: B:73:0x01d1  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final void measure(androidx.constraintlayout.core.widgets.ConstraintWidget r18, androidx.constraintlayout.core.widgets.analyzer.BasicMeasure.Measure r19) {
            /*
                Method dump skipped, instructions count: 709
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.widget.ConstraintLayout.Measurer.measure(androidx.constraintlayout.core.widgets.ConstraintWidget, androidx.constraintlayout.core.widgets.analyzer.BasicMeasure$Measure):void");
        }
    }

    public ConstraintLayout(Context context) {
        super(context);
        this.mChildrenByIds = new SparseArray();
        this.mConstraintHelpers = new ArrayList(4);
        this.mLayoutWidget = new ConstraintWidgetContainer();
        this.mMinWidth = 0;
        this.mMinHeight = 0;
        this.mMaxWidth = Integer.MAX_VALUE;
        this.mMaxHeight = Integer.MAX_VALUE;
        this.mDirtyHierarchy = true;
        this.mOptimizationLevel = 257;
        this.mConstraintSet = null;
        this.mConstraintLayoutSpec = null;
        this.mConstraintSetId = -1;
        this.mDesignIds = new HashMap();
        this.mTempMapIdToWidget = new SparseArray();
        this.mMeasurer = new Measurer(this);
        init(null, 0, 0);
    }

    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:102:0x02ec -> B:74:0x02ed). Please report as a decompilation issue!!! */
    public final void applyConstraintsFromLayoutParams(boolean z, View view, ConstraintWidget constraintWidget, LayoutParams layoutParams, SparseArray sparseArray) {
        ConstraintAnchor.Type type;
        ConstraintAnchor.Type type2;
        ConstraintWidget constraintWidget2;
        ConstraintWidget constraintWidget3;
        ConstraintWidget constraintWidget4;
        ConstraintWidget constraintWidget5;
        float f;
        int i;
        float f2;
        int i2;
        layoutParams.validate();
        constraintWidget.mVisibility = view.getVisibility();
        constraintWidget.mCompanionWidget = view;
        if (view instanceof ConstraintHelper) {
            ((ConstraintHelper) view).resolveRtl(constraintWidget, this.mLayoutWidget.mIsRtl);
        }
        int i3 = -1;
        if (layoutParams.mIsGuideline) {
            androidx.constraintlayout.core.widgets.Guideline guideline = (androidx.constraintlayout.core.widgets.Guideline) constraintWidget;
            int i4 = layoutParams.mResolvedGuideBegin;
            int i5 = layoutParams.mResolvedGuideEnd;
            float f3 = layoutParams.mResolvedGuidePercent;
            if (f3 != -1.0f) {
                if (f3 > -1.0f) {
                    guideline.mRelativePercent = f3;
                    guideline.mRelativeBegin = -1;
                    guideline.mRelativeEnd = -1;
                    return;
                }
                return;
            }
            if (i4 != -1) {
                if (i4 > -1) {
                    guideline.mRelativePercent = -1.0f;
                    guideline.mRelativeBegin = i4;
                    guideline.mRelativeEnd = -1;
                    return;
                }
                return;
            }
            if (i5 == -1 || i5 <= -1) {
                return;
            }
            guideline.mRelativePercent = -1.0f;
            guideline.mRelativeBegin = -1;
            guideline.mRelativeEnd = i5;
            return;
        }
        int i6 = layoutParams.mResolvedLeftToLeft;
        int i7 = layoutParams.mResolvedLeftToRight;
        int i8 = layoutParams.mResolvedRightToLeft;
        int i9 = layoutParams.mResolvedRightToRight;
        int i10 = layoutParams.mResolveGoneLeftMargin;
        int i11 = layoutParams.mResolveGoneRightMargin;
        float f4 = layoutParams.mResolvedHorizontalBias;
        int i12 = layoutParams.circleConstraint;
        ConstraintAnchor.Type type3 = ConstraintAnchor.Type.RIGHT;
        ConstraintAnchor.Type type4 = ConstraintAnchor.Type.LEFT;
        ConstraintAnchor.Type type5 = ConstraintAnchor.Type.BOTTOM;
        ConstraintAnchor.Type type6 = ConstraintAnchor.Type.TOP;
        if (i12 != -1) {
            ConstraintWidget constraintWidget6 = (ConstraintWidget) sparseArray.get(i12);
            if (constraintWidget6 != null) {
                float f5 = layoutParams.circleAngle;
                int i13 = layoutParams.circleRadius;
                ConstraintAnchor.Type type7 = ConstraintAnchor.Type.CENTER;
                constraintWidget.immediateConnect(type7, constraintWidget6, type7, i13, 0);
                constraintWidget.mCircleConstraintAngle = f5;
                f = 0.0f;
                type2 = type4;
                type = type5;
                type6 = type6;
            } else {
                type = type5;
                type2 = type4;
                f = 0.0f;
            }
        } else {
            if (i6 != -1) {
                ConstraintWidget constraintWidget7 = (ConstraintWidget) sparseArray.get(i6);
                if (constraintWidget7 != null) {
                    type6 = type6;
                    type = type5;
                    type2 = type4;
                    constraintWidget.immediateConnect(type4, constraintWidget7, type4, ((ViewGroup.MarginLayoutParams) layoutParams).leftMargin, i10);
                } else {
                    type6 = type6;
                    type = type5;
                    type2 = type4;
                }
            } else {
                type6 = type6;
                type = type5;
                type2 = type4;
                if (i7 != -1 && (constraintWidget2 = (ConstraintWidget) sparseArray.get(i7)) != null) {
                    constraintWidget.immediateConnect(type2, constraintWidget2, type3, ((ViewGroup.MarginLayoutParams) layoutParams).leftMargin, i10);
                }
            }
            if (i8 != -1) {
                ConstraintWidget constraintWidget8 = (ConstraintWidget) sparseArray.get(i8);
                if (constraintWidget8 != null) {
                    constraintWidget.immediateConnect(type3, constraintWidget8, type2, ((ViewGroup.MarginLayoutParams) layoutParams).rightMargin, i11);
                }
            } else if (i9 != -1 && (constraintWidget3 = (ConstraintWidget) sparseArray.get(i9)) != null) {
                constraintWidget.immediateConnect(type3, constraintWidget3, type3, ((ViewGroup.MarginLayoutParams) layoutParams).rightMargin, i11);
            }
            int i14 = layoutParams.topToTop;
            if (i14 != -1) {
                ConstraintWidget constraintWidget9 = (ConstraintWidget) sparseArray.get(i14);
                if (constraintWidget9 != null) {
                    constraintWidget.immediateConnect(type6, constraintWidget9, type6, ((ViewGroup.MarginLayoutParams) layoutParams).topMargin, layoutParams.goneTopMargin);
                }
            } else {
                int i15 = layoutParams.topToBottom;
                if (i15 != -1 && (constraintWidget4 = (ConstraintWidget) sparseArray.get(i15)) != null) {
                    constraintWidget.immediateConnect(type6, constraintWidget4, type, ((ViewGroup.MarginLayoutParams) layoutParams).topMargin, layoutParams.goneTopMargin);
                }
            }
            int i16 = layoutParams.bottomToTop;
            if (i16 != -1) {
                ConstraintWidget constraintWidget10 = (ConstraintWidget) sparseArray.get(i16);
                if (constraintWidget10 != null) {
                    constraintWidget.immediateConnect(type, constraintWidget10, type6, ((ViewGroup.MarginLayoutParams) layoutParams).bottomMargin, layoutParams.goneBottomMargin);
                }
            } else {
                int i17 = layoutParams.bottomToBottom;
                if (i17 != -1 && (constraintWidget5 = (ConstraintWidget) sparseArray.get(i17)) != null) {
                    constraintWidget.immediateConnect(type, constraintWidget5, type, ((ViewGroup.MarginLayoutParams) layoutParams).bottomMargin, layoutParams.goneBottomMargin);
                }
            }
            int i18 = layoutParams.baselineToBaseline;
            if (i18 != -1) {
                setWidgetBaseline(constraintWidget, layoutParams, sparseArray, i18, ConstraintAnchor.Type.BASELINE);
            } else {
                int i19 = layoutParams.baselineToTop;
                if (i19 != -1) {
                    setWidgetBaseline(constraintWidget, layoutParams, sparseArray, i19, type6);
                } else {
                    int i20 = layoutParams.baselineToBottom;
                    if (i20 != -1) {
                        setWidgetBaseline(constraintWidget, layoutParams, sparseArray, i20, type);
                    }
                }
            }
            f = 0.0f;
            if (f4 >= 0.0f) {
                constraintWidget.mHorizontalBiasPercent = f4;
            }
            float f6 = layoutParams.verticalBias;
            if (f6 >= 0.0f) {
                constraintWidget.mVerticalBiasPercent = f6;
            }
        }
        if (z && ((i2 = layoutParams.editorAbsoluteX) != -1 || layoutParams.editorAbsoluteY != -1)) {
            int i21 = layoutParams.editorAbsoluteY;
            constraintWidget.mX = i2;
            constraintWidget.mY = i21;
        }
        boolean z2 = layoutParams.mHorizontalDimensionFixed;
        ConstraintWidget.DimensionBehaviour dimensionBehaviour = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
        ConstraintWidget.DimensionBehaviour dimensionBehaviour2 = ConstraintWidget.DimensionBehaviour.FIXED;
        ConstraintWidget.DimensionBehaviour dimensionBehaviour3 = ConstraintWidget.DimensionBehaviour.MATCH_PARENT;
        ConstraintWidget.DimensionBehaviour dimensionBehaviour4 = ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT;
        if (z2) {
            constraintWidget.setHorizontalDimensionBehaviour(dimensionBehaviour2);
            constraintWidget.setWidth(((ViewGroup.MarginLayoutParams) layoutParams).width);
            if (((ViewGroup.MarginLayoutParams) layoutParams).width == -2) {
                constraintWidget.setHorizontalDimensionBehaviour(dimensionBehaviour);
            }
        } else if (((ViewGroup.MarginLayoutParams) layoutParams).width == -1) {
            if (layoutParams.constrainedWidth) {
                constraintWidget.setHorizontalDimensionBehaviour(dimensionBehaviour4);
            } else {
                constraintWidget.setHorizontalDimensionBehaviour(dimensionBehaviour3);
            }
            constraintWidget.getAnchor(type2).mMargin = ((ViewGroup.MarginLayoutParams) layoutParams).leftMargin;
            constraintWidget.getAnchor(type3).mMargin = ((ViewGroup.MarginLayoutParams) layoutParams).rightMargin;
        } else {
            constraintWidget.setHorizontalDimensionBehaviour(dimensionBehaviour4);
            constraintWidget.setWidth(0);
        }
        if (layoutParams.mVerticalDimensionFixed) {
            constraintWidget.setVerticalDimensionBehaviour(dimensionBehaviour2);
            constraintWidget.setHeight(((ViewGroup.MarginLayoutParams) layoutParams).height);
            if (((ViewGroup.MarginLayoutParams) layoutParams).height == -2) {
                constraintWidget.setVerticalDimensionBehaviour(dimensionBehaviour);
            }
        } else if (((ViewGroup.MarginLayoutParams) layoutParams).height == -1) {
            if (layoutParams.constrainedHeight) {
                constraintWidget.setVerticalDimensionBehaviour(dimensionBehaviour4);
            } else {
                constraintWidget.setVerticalDimensionBehaviour(dimensionBehaviour3);
            }
            constraintWidget.getAnchor(type6).mMargin = ((ViewGroup.MarginLayoutParams) layoutParams).topMargin;
            constraintWidget.getAnchor(type).mMargin = ((ViewGroup.MarginLayoutParams) layoutParams).bottomMargin;
        } else {
            constraintWidget.setVerticalDimensionBehaviour(dimensionBehaviour4);
            constraintWidget.setHeight(0);
        }
        String str = layoutParams.dimensionRatio;
        if (str == null || str.length() == 0) {
            constraintWidget.mDimensionRatio = f;
        } else {
            int length = str.length();
            int indexOf = str.indexOf(44);
            if (indexOf <= 0 || indexOf >= length - 1) {
                i = 0;
            } else {
                String substring = str.substring(0, indexOf);
                if (substring.equalsIgnoreCase("W")) {
                    i3 = 0;
                } else if (substring.equalsIgnoreCase("H")) {
                    i3 = 1;
                }
                i = indexOf + 1;
            }
            int indexOf2 = str.indexOf(58);
            if (indexOf2 < 0 || indexOf2 >= length - 1) {
                String substring2 = str.substring(i);
                if (substring2.length() > 0) {
                    f2 = Float.parseFloat(substring2);
                }
                f2 = f;
            } else {
                String substring3 = str.substring(i, indexOf2);
                String substring4 = str.substring(indexOf2 + 1);
                if (substring3.length() > 0 && substring4.length() > 0) {
                    float parseFloat = Float.parseFloat(substring3);
                    float parseFloat2 = Float.parseFloat(substring4);
                    if (parseFloat > f && parseFloat2 > f) {
                        f2 = i3 == 1 ? Math.abs(parseFloat2 / parseFloat) : Math.abs(parseFloat / parseFloat2);
                    }
                }
                f2 = f;
            }
            if (f2 > f) {
                constraintWidget.mDimensionRatio = f2;
                constraintWidget.mDimensionRatioSide = i3;
            }
        }
        float f7 = layoutParams.horizontalWeight;
        float[] fArr = constraintWidget.mWeight;
        fArr[0] = f7;
        fArr[1] = layoutParams.verticalWeight;
        constraintWidget.mHorizontalChainStyle = layoutParams.horizontalChainStyle;
        constraintWidget.mVerticalChainStyle = layoutParams.verticalChainStyle;
        int i22 = layoutParams.wrapBehaviorInParent;
        if (i22 >= 0 && i22 <= 3) {
            constraintWidget.mWrapBehaviorInParent = i22;
        }
        int i23 = layoutParams.matchConstraintDefaultWidth;
        int i24 = layoutParams.matchConstraintMinWidth;
        int i25 = layoutParams.matchConstraintMaxWidth;
        float f8 = layoutParams.matchConstraintPercentWidth;
        constraintWidget.mMatchConstraintDefaultWidth = i23;
        constraintWidget.mMatchConstraintMinWidth = i24;
        if (i25 == Integer.MAX_VALUE) {
            i25 = 0;
        }
        constraintWidget.mMatchConstraintMaxWidth = i25;
        constraintWidget.mMatchConstraintPercentWidth = f8;
        if (f8 > f && f8 < 1.0f && i23 == 0) {
            constraintWidget.mMatchConstraintDefaultWidth = 2;
        }
        int i26 = layoutParams.matchConstraintDefaultHeight;
        int i27 = layoutParams.matchConstraintMinHeight;
        int i28 = layoutParams.matchConstraintMaxHeight;
        float f9 = layoutParams.matchConstraintPercentHeight;
        constraintWidget.mMatchConstraintDefaultHeight = i26;
        constraintWidget.mMatchConstraintMinHeight = i27;
        constraintWidget.mMatchConstraintMaxHeight = i28 != Integer.MAX_VALUE ? i28 : 0;
        constraintWidget.mMatchConstraintPercentHeight = f9;
        if (f9 <= f || f9 >= 1.0f || i26 != 0) {
            return;
        }
        constraintWidget.mMatchConstraintDefaultHeight = 2;
    }

    @Override // android.view.ViewGroup
    public final boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof LayoutParams;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void dispatchDraw(Canvas canvas) {
        Object tag;
        int size;
        ArrayList arrayList = this.mConstraintHelpers;
        if (arrayList != null && (size = arrayList.size()) > 0) {
            for (int i = 0; i < size; i++) {
                ((ConstraintHelper) this.mConstraintHelpers.get(i)).updatePreDraw(this);
            }
        }
        super.dispatchDraw(canvas);
        if (isInEditMode()) {
            float width = getWidth();
            float height = getHeight();
            int childCount = getChildCount();
            for (int i2 = 0; i2 < childCount; i2++) {
                View childAt = getChildAt(i2);
                if (childAt.getVisibility() != 8 && (tag = childAt.getTag()) != null && (tag instanceof String)) {
                    String[] split = ((String) tag).split(",");
                    if (split.length == 4) {
                        int parseInt = Integer.parseInt(split[0]);
                        int parseInt2 = Integer.parseInt(split[1]);
                        int parseInt3 = Integer.parseInt(split[2]);
                        int i3 = (int) ((parseInt / 1080.0f) * width);
                        int i4 = (int) ((parseInt2 / 1920.0f) * height);
                        Paint paint = new Paint();
                        paint.setColor(-65536);
                        float f = i3;
                        float f2 = i4;
                        float f3 = i3 + ((int) ((parseInt3 / 1080.0f) * width));
                        canvas.drawLine(f, f2, f3, f2, paint);
                        float parseInt4 = i4 + ((int) ((Integer.parseInt(split[3]) / 1920.0f) * height));
                        canvas.drawLine(f3, f2, f3, parseInt4, paint);
                        canvas.drawLine(f3, parseInt4, f, parseInt4, paint);
                        canvas.drawLine(f, parseInt4, f, f2, paint);
                        paint.setColor(-16711936);
                        canvas.drawLine(f, f2, f3, parseInt4, paint);
                        canvas.drawLine(f, parseInt4, f3, f2, paint);
                    }
                }
            }
        }
    }

    @Override // android.view.View
    public final void forceLayout() {
        this.mDirtyHierarchy = true;
        super.forceLayout();
    }

    @Override // android.view.ViewGroup
    public final ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams(-2);
    }

    @Override // android.view.ViewGroup
    public final ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        Context context = getContext();
        LayoutParams layoutParams = new LayoutParams(context, attributeSet);
        layoutParams.guideBegin = -1;
        layoutParams.guideEnd = -1;
        layoutParams.guidePercent = -1.0f;
        layoutParams.guidelineUseRtl = true;
        layoutParams.leftToLeft = -1;
        layoutParams.leftToRight = -1;
        layoutParams.rightToLeft = -1;
        layoutParams.rightToRight = -1;
        layoutParams.topToTop = -1;
        layoutParams.topToBottom = -1;
        layoutParams.bottomToTop = -1;
        layoutParams.bottomToBottom = -1;
        layoutParams.baselineToBaseline = -1;
        layoutParams.baselineToTop = -1;
        layoutParams.baselineToBottom = -1;
        layoutParams.circleConstraint = -1;
        layoutParams.circleRadius = 0;
        layoutParams.circleAngle = 0.0f;
        layoutParams.startToEnd = -1;
        layoutParams.startToStart = -1;
        layoutParams.endToStart = -1;
        layoutParams.endToEnd = -1;
        layoutParams.goneLeftMargin = Integer.MIN_VALUE;
        layoutParams.goneTopMargin = Integer.MIN_VALUE;
        layoutParams.goneRightMargin = Integer.MIN_VALUE;
        layoutParams.goneBottomMargin = Integer.MIN_VALUE;
        layoutParams.goneStartMargin = Integer.MIN_VALUE;
        layoutParams.goneEndMargin = Integer.MIN_VALUE;
        layoutParams.goneBaselineMargin = Integer.MIN_VALUE;
        layoutParams.baselineMargin = 0;
        layoutParams.horizontalBias = 0.5f;
        layoutParams.verticalBias = 0.5f;
        layoutParams.dimensionRatio = null;
        layoutParams.horizontalWeight = -1.0f;
        layoutParams.verticalWeight = -1.0f;
        layoutParams.horizontalChainStyle = 0;
        layoutParams.verticalChainStyle = 0;
        layoutParams.matchConstraintDefaultWidth = 0;
        layoutParams.matchConstraintDefaultHeight = 0;
        layoutParams.matchConstraintMinWidth = 0;
        layoutParams.matchConstraintMinHeight = 0;
        layoutParams.matchConstraintMaxWidth = 0;
        layoutParams.matchConstraintMaxHeight = 0;
        layoutParams.matchConstraintPercentWidth = 1.0f;
        layoutParams.matchConstraintPercentHeight = 1.0f;
        layoutParams.editorAbsoluteX = -1;
        layoutParams.editorAbsoluteY = -1;
        layoutParams.orientation = -1;
        layoutParams.constrainedWidth = false;
        layoutParams.constrainedHeight = false;
        layoutParams.constraintTag = null;
        layoutParams.wrapBehaviorInParent = 0;
        layoutParams.mHorizontalDimensionFixed = true;
        layoutParams.mVerticalDimensionFixed = true;
        layoutParams.mNeedsBaseline = false;
        layoutParams.mIsGuideline = false;
        layoutParams.mIsHelper = false;
        layoutParams.mResolvedLeftToLeft = -1;
        layoutParams.mResolvedLeftToRight = -1;
        layoutParams.mResolvedRightToLeft = -1;
        layoutParams.mResolvedRightToRight = -1;
        layoutParams.mResolveGoneLeftMargin = Integer.MIN_VALUE;
        layoutParams.mResolveGoneRightMargin = Integer.MIN_VALUE;
        layoutParams.mResolvedHorizontalBias = 0.5f;
        layoutParams.mWidget = new ConstraintWidget();
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.ConstraintLayout_Layout);
        int indexCount = obtainStyledAttributes.getIndexCount();
        for (int i = 0; i < indexCount; i++) {
            int index = obtainStyledAttributes.getIndex(i);
            int i2 = LayoutParams.Table.sMap.get(index);
            switch (i2) {
                case 1:
                    layoutParams.orientation = obtainStyledAttributes.getInt(index, layoutParams.orientation);
                    break;
                case 2:
                    int resourceId = obtainStyledAttributes.getResourceId(index, layoutParams.circleConstraint);
                    layoutParams.circleConstraint = resourceId;
                    if (resourceId == -1) {
                        layoutParams.circleConstraint = obtainStyledAttributes.getInt(index, -1);
                        break;
                    } else {
                        break;
                    }
                case 3:
                    layoutParams.circleRadius = obtainStyledAttributes.getDimensionPixelSize(index, layoutParams.circleRadius);
                    break;
                case 4:
                    float f = obtainStyledAttributes.getFloat(index, layoutParams.circleAngle) % 360.0f;
                    layoutParams.circleAngle = f;
                    if (f < 0.0f) {
                        layoutParams.circleAngle = (360.0f - f) % 360.0f;
                        break;
                    } else {
                        break;
                    }
                case 5:
                    layoutParams.guideBegin = obtainStyledAttributes.getDimensionPixelOffset(index, layoutParams.guideBegin);
                    break;
                case 6:
                    layoutParams.guideEnd = obtainStyledAttributes.getDimensionPixelOffset(index, layoutParams.guideEnd);
                    break;
                case 7:
                    layoutParams.guidePercent = obtainStyledAttributes.getFloat(index, layoutParams.guidePercent);
                    break;
                case 8:
                    int resourceId2 = obtainStyledAttributes.getResourceId(index, layoutParams.leftToLeft);
                    layoutParams.leftToLeft = resourceId2;
                    if (resourceId2 == -1) {
                        layoutParams.leftToLeft = obtainStyledAttributes.getInt(index, -1);
                        break;
                    } else {
                        break;
                    }
                case 9:
                    int resourceId3 = obtainStyledAttributes.getResourceId(index, layoutParams.leftToRight);
                    layoutParams.leftToRight = resourceId3;
                    if (resourceId3 == -1) {
                        layoutParams.leftToRight = obtainStyledAttributes.getInt(index, -1);
                        break;
                    } else {
                        break;
                    }
                case 10:
                    int resourceId4 = obtainStyledAttributes.getResourceId(index, layoutParams.rightToLeft);
                    layoutParams.rightToLeft = resourceId4;
                    if (resourceId4 == -1) {
                        layoutParams.rightToLeft = obtainStyledAttributes.getInt(index, -1);
                        break;
                    } else {
                        break;
                    }
                case ViewNode.TRANSLATIONX_FIELD_NUMBER /* 11 */:
                    int resourceId5 = obtainStyledAttributes.getResourceId(index, layoutParams.rightToRight);
                    layoutParams.rightToRight = resourceId5;
                    if (resourceId5 == -1) {
                        layoutParams.rightToRight = obtainStyledAttributes.getInt(index, -1);
                        break;
                    } else {
                        break;
                    }
                case ViewNode.TRANSLATIONY_FIELD_NUMBER /* 12 */:
                    int resourceId6 = obtainStyledAttributes.getResourceId(index, layoutParams.topToTop);
                    layoutParams.topToTop = resourceId6;
                    if (resourceId6 == -1) {
                        layoutParams.topToTop = obtainStyledAttributes.getInt(index, -1);
                        break;
                    } else {
                        break;
                    }
                case ViewNode.SCALEX_FIELD_NUMBER /* 13 */:
                    int resourceId7 = obtainStyledAttributes.getResourceId(index, layoutParams.topToBottom);
                    layoutParams.topToBottom = resourceId7;
                    if (resourceId7 == -1) {
                        layoutParams.topToBottom = obtainStyledAttributes.getInt(index, -1);
                        break;
                    } else {
                        break;
                    }
                case ViewNode.SCALEY_FIELD_NUMBER /* 14 */:
                    int resourceId8 = obtainStyledAttributes.getResourceId(index, layoutParams.bottomToTop);
                    layoutParams.bottomToTop = resourceId8;
                    if (resourceId8 == -1) {
                        layoutParams.bottomToTop = obtainStyledAttributes.getInt(index, -1);
                        break;
                    } else {
                        break;
                    }
                case 15:
                    int resourceId9 = obtainStyledAttributes.getResourceId(index, layoutParams.bottomToBottom);
                    layoutParams.bottomToBottom = resourceId9;
                    if (resourceId9 == -1) {
                        layoutParams.bottomToBottom = obtainStyledAttributes.getInt(index, -1);
                        break;
                    } else {
                        break;
                    }
                case 16:
                    int resourceId10 = obtainStyledAttributes.getResourceId(index, layoutParams.baselineToBaseline);
                    layoutParams.baselineToBaseline = resourceId10;
                    if (resourceId10 == -1) {
                        layoutParams.baselineToBaseline = obtainStyledAttributes.getInt(index, -1);
                        break;
                    } else {
                        break;
                    }
                case ViewNode.CLIPCHILDREN_FIELD_NUMBER /* 17 */:
                    int resourceId11 = obtainStyledAttributes.getResourceId(index, layoutParams.startToEnd);
                    layoutParams.startToEnd = resourceId11;
                    if (resourceId11 == -1) {
                        layoutParams.startToEnd = obtainStyledAttributes.getInt(index, -1);
                        break;
                    } else {
                        break;
                    }
                case ViewNode.VISIBILITY_FIELD_NUMBER /* 18 */:
                    int resourceId12 = obtainStyledAttributes.getResourceId(index, layoutParams.startToStart);
                    layoutParams.startToStart = resourceId12;
                    if (resourceId12 == -1) {
                        layoutParams.startToStart = obtainStyledAttributes.getInt(index, -1);
                        break;
                    } else {
                        break;
                    }
                case ViewNode.ELEVATION_FIELD_NUMBER /* 19 */:
                    int resourceId13 = obtainStyledAttributes.getResourceId(index, layoutParams.endToStart);
                    layoutParams.endToStart = resourceId13;
                    if (resourceId13 == -1) {
                        layoutParams.endToStart = obtainStyledAttributes.getInt(index, -1);
                        break;
                    } else {
                        break;
                    }
                case 20:
                    int resourceId14 = obtainStyledAttributes.getResourceId(index, layoutParams.endToEnd);
                    layoutParams.endToEnd = resourceId14;
                    if (resourceId14 == -1) {
                        layoutParams.endToEnd = obtainStyledAttributes.getInt(index, -1);
                        break;
                    } else {
                        break;
                    }
                case 21:
                    layoutParams.goneLeftMargin = obtainStyledAttributes.getDimensionPixelSize(index, layoutParams.goneLeftMargin);
                    break;
                case 22:
                    layoutParams.goneTopMargin = obtainStyledAttributes.getDimensionPixelSize(index, layoutParams.goneTopMargin);
                    break;
                case 23:
                    layoutParams.goneRightMargin = obtainStyledAttributes.getDimensionPixelSize(index, layoutParams.goneRightMargin);
                    break;
                case 24:
                    layoutParams.goneBottomMargin = obtainStyledAttributes.getDimensionPixelSize(index, layoutParams.goneBottomMargin);
                    break;
                case 25:
                    layoutParams.goneStartMargin = obtainStyledAttributes.getDimensionPixelSize(index, layoutParams.goneStartMargin);
                    break;
                case 26:
                    layoutParams.goneEndMargin = obtainStyledAttributes.getDimensionPixelSize(index, layoutParams.goneEndMargin);
                    break;
                case 27:
                    layoutParams.constrainedWidth = obtainStyledAttributes.getBoolean(index, layoutParams.constrainedWidth);
                    break;
                case 28:
                    layoutParams.constrainedHeight = obtainStyledAttributes.getBoolean(index, layoutParams.constrainedHeight);
                    break;
                case 29:
                    layoutParams.horizontalBias = obtainStyledAttributes.getFloat(index, layoutParams.horizontalBias);
                    break;
                case 30:
                    layoutParams.verticalBias = obtainStyledAttributes.getFloat(index, layoutParams.verticalBias);
                    break;
                case 31:
                    int i3 = obtainStyledAttributes.getInt(index, 0);
                    layoutParams.matchConstraintDefaultWidth = i3;
                    if (i3 == 1) {
                        Log.e("ConstraintLayout", "layout_constraintWidth_default=\"wrap\" is deprecated.\nUse layout_width=\"WRAP_CONTENT\" and layout_constrainedWidth=\"true\" instead.");
                        break;
                    } else {
                        break;
                    }
                case 32:
                    int i4 = obtainStyledAttributes.getInt(index, 0);
                    layoutParams.matchConstraintDefaultHeight = i4;
                    if (i4 == 1) {
                        Log.e("ConstraintLayout", "layout_constraintHeight_default=\"wrap\" is deprecated.\nUse layout_height=\"WRAP_CONTENT\" and layout_constrainedHeight=\"true\" instead.");
                        break;
                    } else {
                        break;
                    }
                case 33:
                    try {
                        layoutParams.matchConstraintMinWidth = obtainStyledAttributes.getDimensionPixelSize(index, layoutParams.matchConstraintMinWidth);
                        break;
                    } catch (Exception unused) {
                        if (obtainStyledAttributes.getInt(index, layoutParams.matchConstraintMinWidth) == -2) {
                            layoutParams.matchConstraintMinWidth = -2;
                            break;
                        } else {
                            break;
                        }
                    }
                case 34:
                    try {
                        layoutParams.matchConstraintMaxWidth = obtainStyledAttributes.getDimensionPixelSize(index, layoutParams.matchConstraintMaxWidth);
                        break;
                    } catch (Exception unused2) {
                        if (obtainStyledAttributes.getInt(index, layoutParams.matchConstraintMaxWidth) == -2) {
                            layoutParams.matchConstraintMaxWidth = -2;
                            break;
                        } else {
                            break;
                        }
                    }
                case 35:
                    layoutParams.matchConstraintPercentWidth = Math.max(0.0f, obtainStyledAttributes.getFloat(index, layoutParams.matchConstraintPercentWidth));
                    layoutParams.matchConstraintDefaultWidth = 2;
                    break;
                case 36:
                    try {
                        layoutParams.matchConstraintMinHeight = obtainStyledAttributes.getDimensionPixelSize(index, layoutParams.matchConstraintMinHeight);
                        break;
                    } catch (Exception unused3) {
                        if (obtainStyledAttributes.getInt(index, layoutParams.matchConstraintMinHeight) == -2) {
                            layoutParams.matchConstraintMinHeight = -2;
                            break;
                        } else {
                            break;
                        }
                    }
                case 37:
                    try {
                        layoutParams.matchConstraintMaxHeight = obtainStyledAttributes.getDimensionPixelSize(index, layoutParams.matchConstraintMaxHeight);
                        break;
                    } catch (Exception unused4) {
                        if (obtainStyledAttributes.getInt(index, layoutParams.matchConstraintMaxHeight) == -2) {
                            layoutParams.matchConstraintMaxHeight = -2;
                            break;
                        } else {
                            break;
                        }
                    }
                case 38:
                    layoutParams.matchConstraintPercentHeight = Math.max(0.0f, obtainStyledAttributes.getFloat(index, layoutParams.matchConstraintPercentHeight));
                    layoutParams.matchConstraintDefaultHeight = 2;
                    break;
                default:
                    switch (i2) {
                        case 44:
                            ConstraintSet.parseDimensionRatioString(layoutParams, obtainStyledAttributes.getString(index));
                            break;
                        case 45:
                            layoutParams.horizontalWeight = obtainStyledAttributes.getFloat(index, layoutParams.horizontalWeight);
                            break;
                        case 46:
                            layoutParams.verticalWeight = obtainStyledAttributes.getFloat(index, layoutParams.verticalWeight);
                            break;
                        case 47:
                            layoutParams.horizontalChainStyle = obtainStyledAttributes.getInt(index, 0);
                            break;
                        case 48:
                            layoutParams.verticalChainStyle = obtainStyledAttributes.getInt(index, 0);
                            break;
                        case 49:
                            layoutParams.editorAbsoluteX = obtainStyledAttributes.getDimensionPixelOffset(index, layoutParams.editorAbsoluteX);
                            break;
                        case 50:
                            layoutParams.editorAbsoluteY = obtainStyledAttributes.getDimensionPixelOffset(index, layoutParams.editorAbsoluteY);
                            break;
                        case 51:
                            layoutParams.constraintTag = obtainStyledAttributes.getString(index);
                            break;
                        case 52:
                            int resourceId15 = obtainStyledAttributes.getResourceId(index, layoutParams.baselineToTop);
                            layoutParams.baselineToTop = resourceId15;
                            if (resourceId15 == -1) {
                                layoutParams.baselineToTop = obtainStyledAttributes.getInt(index, -1);
                                break;
                            } else {
                                break;
                            }
                        case 53:
                            int resourceId16 = obtainStyledAttributes.getResourceId(index, layoutParams.baselineToBottom);
                            layoutParams.baselineToBottom = resourceId16;
                            if (resourceId16 == -1) {
                                layoutParams.baselineToBottom = obtainStyledAttributes.getInt(index, -1);
                                break;
                            } else {
                                break;
                            }
                        case 54:
                            layoutParams.baselineMargin = obtainStyledAttributes.getDimensionPixelSize(index, layoutParams.baselineMargin);
                            break;
                        case 55:
                            layoutParams.goneBaselineMargin = obtainStyledAttributes.getDimensionPixelSize(index, layoutParams.goneBaselineMargin);
                            break;
                        default:
                            switch (i2) {
                                case 64:
                                    ConstraintSet.parseDimensionConstraints(layoutParams, obtainStyledAttributes, index, 0);
                                    break;
                                case 65:
                                    ConstraintSet.parseDimensionConstraints(layoutParams, obtainStyledAttributes, index, 1);
                                    break;
                                case 66:
                                    layoutParams.wrapBehaviorInParent = obtainStyledAttributes.getInt(index, layoutParams.wrapBehaviorInParent);
                                    break;
                                case 67:
                                    layoutParams.guidelineUseRtl = obtainStyledAttributes.getBoolean(index, layoutParams.guidelineUseRtl);
                                    break;
                            }
                    }
            }
        }
        obtainStyledAttributes.recycle();
        layoutParams.validate();
        return layoutParams;
    }

    public final View getViewById(int i) {
        return (View) this.mChildrenByIds.get(i);
    }

    public final ConstraintWidget getViewWidget(View view) {
        if (view == this) {
            return this.mLayoutWidget;
        }
        if (view == null) {
            return null;
        }
        if (view.getLayoutParams() instanceof LayoutParams) {
            return ((LayoutParams) view.getLayoutParams()).mWidget;
        }
        view.setLayoutParams(generateLayoutParams(view.getLayoutParams()));
        if (view.getLayoutParams() instanceof LayoutParams) {
            return ((LayoutParams) view.getLayoutParams()).mWidget;
        }
        return null;
    }

    public final void init(AttributeSet attributeSet, int i, int i2) {
        ConstraintWidgetContainer constraintWidgetContainer = this.mLayoutWidget;
        constraintWidgetContainer.mCompanionWidget = this;
        Measurer measurer = this.mMeasurer;
        constraintWidgetContainer.mMeasurer = measurer;
        constraintWidgetContainer.mDependencyGraph.mMeasurer = measurer;
        this.mChildrenByIds.put(getId(), this);
        this.mConstraintSet = null;
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R$styleable.ConstraintLayout_Layout, i, i2);
            int indexCount = obtainStyledAttributes.getIndexCount();
            for (int i3 = 0; i3 < indexCount; i3++) {
                int index = obtainStyledAttributes.getIndex(i3);
                if (index == 16) {
                    this.mMinWidth = obtainStyledAttributes.getDimensionPixelOffset(index, this.mMinWidth);
                } else if (index == 17) {
                    this.mMinHeight = obtainStyledAttributes.getDimensionPixelOffset(index, this.mMinHeight);
                } else if (index == 14) {
                    this.mMaxWidth = obtainStyledAttributes.getDimensionPixelOffset(index, this.mMaxWidth);
                } else if (index == 15) {
                    this.mMaxHeight = obtainStyledAttributes.getDimensionPixelOffset(index, this.mMaxHeight);
                } else if (index == 113) {
                    this.mOptimizationLevel = obtainStyledAttributes.getInt(index, this.mOptimizationLevel);
                } else if (index == 56) {
                    int resourceId = obtainStyledAttributes.getResourceId(index, 0);
                    if (resourceId != 0) {
                        try {
                            parseLayoutDescription(resourceId);
                        } catch (Resources.NotFoundException unused) {
                            this.mConstraintLayoutSpec = null;
                        }
                    }
                } else if (index == 34) {
                    int resourceId2 = obtainStyledAttributes.getResourceId(index, 0);
                    try {
                        ConstraintSet constraintSet = new ConstraintSet();
                        this.mConstraintSet = constraintSet;
                        constraintSet.load(resourceId2, getContext());
                    } catch (Resources.NotFoundException unused2) {
                        this.mConstraintSet = null;
                    }
                    this.mConstraintSetId = resourceId2;
                }
            }
            obtainStyledAttributes.recycle();
        }
        ConstraintWidgetContainer constraintWidgetContainer2 = this.mLayoutWidget;
        constraintWidgetContainer2.mOptimizationLevel = this.mOptimizationLevel;
        LinearSystem.USE_DEPENDENCY_ORDERING = constraintWidgetContainer2.optimizeFor(512);
    }

    public final boolean isRtl$1() {
        return (getContext().getApplicationInfo().flags & 4194304) != 0 && 1 == getLayoutDirection();
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int childCount = getChildCount();
        boolean isInEditMode = isInEditMode();
        for (int i5 = 0; i5 < childCount; i5++) {
            View childAt = getChildAt(i5);
            LayoutParams layoutParams = (LayoutParams) childAt.getLayoutParams();
            ConstraintWidget constraintWidget = layoutParams.mWidget;
            if (childAt.getVisibility() != 8 || layoutParams.mIsGuideline || layoutParams.mIsHelper || isInEditMode) {
                int x = constraintWidget.getX();
                int y = constraintWidget.getY();
                childAt.layout(x, y, constraintWidget.getWidth() + x, constraintWidget.getHeight() + y);
            }
        }
        int size = this.mConstraintHelpers.size();
        if (size > 0) {
            for (int i6 = 0; i6 < size; i6++) {
                ((ConstraintHelper) this.mConstraintHelpers.get(i6)).updatePostLayout();
            }
        }
    }

    @Override // android.view.View
    public void onMeasure(int i, int i2) {
        String str;
        int findId;
        ConstraintWidget constraintWidget;
        boolean z = this.mDirtyHierarchy;
        this.mDirtyHierarchy = z;
        boolean z2 = true;
        if (!z) {
            int childCount = getChildCount();
            int i3 = 0;
            while (true) {
                if (i3 >= childCount) {
                    break;
                }
                if (getChildAt(i3).isLayoutRequested()) {
                    this.mDirtyHierarchy = true;
                    break;
                }
                i3++;
            }
        }
        this.mLayoutWidget.mIsRtl = isRtl$1();
        if (this.mDirtyHierarchy) {
            this.mDirtyHierarchy = false;
            int childCount2 = getChildCount();
            int i4 = 0;
            while (true) {
                if (i4 >= childCount2) {
                    z2 = false;
                    break;
                } else if (getChildAt(i4).isLayoutRequested()) {
                    break;
                } else {
                    i4++;
                }
            }
            if (z2) {
                boolean isInEditMode = isInEditMode();
                int childCount3 = getChildCount();
                for (int i5 = 0; i5 < childCount3; i5++) {
                    ConstraintWidget viewWidget = getViewWidget(getChildAt(i5));
                    if (viewWidget != null) {
                        viewWidget.reset();
                    }
                }
                if (isInEditMode) {
                    for (int i6 = 0; i6 < childCount3; i6++) {
                        View childAt = getChildAt(i6);
                        try {
                            String resourceName = getResources().getResourceName(childAt.getId());
                            Integer valueOf = Integer.valueOf(childAt.getId());
                            if (resourceName != null) {
                                if (this.mDesignIds == null) {
                                    this.mDesignIds = new HashMap();
                                }
                                int indexOf = resourceName.indexOf("/");
                                this.mDesignIds.put(indexOf != -1 ? resourceName.substring(indexOf + 1) : resourceName, valueOf);
                            }
                            int indexOf2 = resourceName.indexOf(47);
                            if (indexOf2 != -1) {
                                resourceName = resourceName.substring(indexOf2 + 1);
                            }
                            int id = childAt.getId();
                            if (id == 0) {
                                constraintWidget = this.mLayoutWidget;
                            } else {
                                View view = (View) this.mChildrenByIds.get(id);
                                if (view == null && (view = findViewById(id)) != null && view != this && view.getParent() == this) {
                                    onViewAdded(view);
                                }
                                constraintWidget = view == this ? this.mLayoutWidget : view == null ? null : ((LayoutParams) view.getLayoutParams()).mWidget;
                            }
                            constraintWidget.mDebugName = resourceName;
                        } catch (Resources.NotFoundException unused) {
                        }
                    }
                }
                if (this.mConstraintSetId != -1) {
                    for (int i7 = 0; i7 < childCount3; i7++) {
                        getChildAt(i7).getId();
                    }
                }
                ConstraintSet constraintSet = this.mConstraintSet;
                if (constraintSet != null) {
                    constraintSet.applyToInternal(this);
                }
                this.mLayoutWidget.mChildren.clear();
                int size = this.mConstraintHelpers.size();
                if (size > 0) {
                    for (int i8 = 0; i8 < size; i8++) {
                        ConstraintHelper constraintHelper = (ConstraintHelper) this.mConstraintHelpers.get(i8);
                        if (constraintHelper.isInEditMode()) {
                            constraintHelper.setIds(constraintHelper.mReferenceIds);
                        }
                        HelperWidget helperWidget = constraintHelper.mHelperWidget;
                        if (helperWidget != null) {
                            helperWidget.mWidgetsCount = 0;
                            Arrays.fill(helperWidget.mWidgets, (Object) null);
                            for (int i9 = 0; i9 < constraintHelper.mCount; i9++) {
                                int i10 = constraintHelper.mIds[i9];
                                View viewById = getViewById(i10);
                                if (viewById == null && (findId = constraintHelper.findId(this, (str = (String) constraintHelper.mMap.get(Integer.valueOf(i10))))) != 0) {
                                    constraintHelper.mIds[i9] = findId;
                                    constraintHelper.mMap.put(Integer.valueOf(findId), str);
                                    viewById = getViewById(findId);
                                }
                                if (viewById != null) {
                                    constraintHelper.mHelperWidget.add(getViewWidget(viewById));
                                }
                            }
                            constraintHelper.mHelperWidget.updateConstraints();
                        }
                    }
                }
                for (int i11 = 0; i11 < childCount3; i11++) {
                    getChildAt(i11);
                }
                this.mTempMapIdToWidget.clear();
                this.mTempMapIdToWidget.put(0, this.mLayoutWidget);
                this.mTempMapIdToWidget.put(getId(), this.mLayoutWidget);
                for (int i12 = 0; i12 < childCount3; i12++) {
                    View childAt2 = getChildAt(i12);
                    this.mTempMapIdToWidget.put(childAt2.getId(), getViewWidget(childAt2));
                }
                for (int i13 = 0; i13 < childCount3; i13++) {
                    View childAt3 = getChildAt(i13);
                    ConstraintWidget viewWidget2 = getViewWidget(childAt3);
                    if (viewWidget2 != null) {
                        LayoutParams layoutParams = (LayoutParams) childAt3.getLayoutParams();
                        ConstraintWidgetContainer constraintWidgetContainer = this.mLayoutWidget;
                        constraintWidgetContainer.mChildren.add(viewWidget2);
                        ConstraintWidget constraintWidget2 = viewWidget2.mParent;
                        if (constraintWidget2 != null) {
                            ((ConstraintWidgetContainer) constraintWidget2).mChildren.remove(viewWidget2);
                            viewWidget2.reset();
                        }
                        viewWidget2.mParent = constraintWidgetContainer;
                        applyConstraintsFromLayoutParams(isInEditMode, childAt3, viewWidget2, layoutParams, this.mTempMapIdToWidget);
                    }
                }
            }
            if (z2) {
                ConstraintWidgetContainer constraintWidgetContainer2 = this.mLayoutWidget;
                constraintWidgetContainer2.mBasicMeasureSolver.updateHierarchy(constraintWidgetContainer2);
            }
        }
        this.mLayoutWidget.mSystem.getClass();
        resolveSystem(this.mLayoutWidget, this.mOptimizationLevel, i, i2);
        int width = this.mLayoutWidget.getWidth();
        int height = this.mLayoutWidget.getHeight();
        ConstraintWidgetContainer constraintWidgetContainer3 = this.mLayoutWidget;
        resolveMeasuredDimension(i, i2, width, height, constraintWidgetContainer3.mWidthMeasuredTooSmall, constraintWidgetContainer3.mHeightMeasuredTooSmall);
    }

    @Override // android.view.ViewGroup
    public void onViewAdded(View view) {
        super.onViewAdded(view);
        ConstraintWidget viewWidget = getViewWidget(view);
        if ((view instanceof Guideline) && !(viewWidget instanceof androidx.constraintlayout.core.widgets.Guideline)) {
            LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
            androidx.constraintlayout.core.widgets.Guideline guideline = new androidx.constraintlayout.core.widgets.Guideline();
            layoutParams.mWidget = guideline;
            layoutParams.mIsGuideline = true;
            guideline.setOrientation(layoutParams.orientation);
        }
        if (view instanceof ConstraintHelper) {
            ConstraintHelper constraintHelper = (ConstraintHelper) view;
            constraintHelper.validateParams();
            ((LayoutParams) view.getLayoutParams()).mIsHelper = true;
            if (!this.mConstraintHelpers.contains(constraintHelper)) {
                this.mConstraintHelpers.add(constraintHelper);
            }
        }
        this.mChildrenByIds.put(view.getId(), view);
        this.mDirtyHierarchy = true;
    }

    @Override // android.view.ViewGroup
    public void onViewRemoved(View view) {
        super.onViewRemoved(view);
        this.mChildrenByIds.remove(view.getId());
        ConstraintWidget viewWidget = getViewWidget(view);
        this.mLayoutWidget.mChildren.remove(viewWidget);
        viewWidget.reset();
        this.mConstraintHelpers.remove(view);
        this.mDirtyHierarchy = true;
    }

    public void parseLayoutDescription(int i) {
        this.mConstraintLayoutSpec = new ConstraintLayoutStates(getContext(), this, i);
    }

    @Override // android.view.View, android.view.ViewParent
    public void requestLayout() {
        this.mDirtyHierarchy = true;
        super.requestLayout();
    }

    public final void resolveMeasuredDimension(int i, int i2, int i3, int i4, boolean z, boolean z2) {
        Measurer measurer = this.mMeasurer;
        int i5 = measurer.mPaddingHeight;
        int resolveSizeAndState = ViewGroup.resolveSizeAndState(i3 + measurer.mPaddingWidth, i, 0);
        int resolveSizeAndState2 = ViewGroup.resolveSizeAndState(i4 + i5, i2, 0) & 16777215;
        int min = Math.min(this.mMaxWidth, resolveSizeAndState & 16777215);
        int min2 = Math.min(this.mMaxHeight, resolveSizeAndState2);
        if (z) {
            min |= 16777216;
        }
        if (z2) {
            min2 |= 16777216;
        }
        setMeasuredDimension(min, min2);
    }

    /* JADX WARN: Removed duplicated region for block: B:146:0x0474  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x00d2  */
    /* JADX WARN: Removed duplicated region for block: B:152:0x047b  */
    /* JADX WARN: Removed duplicated region for block: B:205:0x0512  */
    /* JADX WARN: Removed duplicated region for block: B:207:0x0515  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0118  */
    /* JADX WARN: Removed duplicated region for block: B:224:0x055d  */
    /* JADX WARN: Removed duplicated region for block: B:226:0x0562  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x014d  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0157  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x017f  */
    /* JADX WARN: Removed duplicated region for block: B:320:0x0718  */
    /* JADX WARN: Removed duplicated region for block: B:321:0x054b  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x018e  */
    /* JADX WARN: Removed duplicated region for block: B:384:0x0464  */
    /* JADX WARN: Removed duplicated region for block: B:388:0x015a  */
    /* JADX WARN: Removed duplicated region for block: B:389:0x0150  */
    /* JADX WARN: Removed duplicated region for block: B:396:0x00ff  */
    /* JADX WARN: Removed duplicated region for block: B:75:0x01e9 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:79:0x01f3  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void resolveSystem(androidx.constraintlayout.core.widgets.ConstraintWidgetContainer r25, int r26, int r27, int r28) {
        /*
            Method dump skipped, instructions count: 1831
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.widget.ConstraintLayout.resolveSystem(androidx.constraintlayout.core.widgets.ConstraintWidgetContainer, int, int, int):void");
    }

    @Override // android.view.View
    public final void setId(int i) {
        this.mChildrenByIds.remove(getId());
        super.setId(i);
        this.mChildrenByIds.put(getId(), this);
    }

    public final void setWidgetBaseline(ConstraintWidget constraintWidget, LayoutParams layoutParams, SparseArray sparseArray, int i, ConstraintAnchor.Type type) {
        View view = (View) this.mChildrenByIds.get(i);
        ConstraintWidget constraintWidget2 = (ConstraintWidget) sparseArray.get(i);
        if (constraintWidget2 == null || view == null || !(view.getLayoutParams() instanceof LayoutParams)) {
            return;
        }
        layoutParams.mNeedsBaseline = true;
        ConstraintAnchor.Type type2 = ConstraintAnchor.Type.BASELINE;
        if (type == type2) {
            LayoutParams layoutParams2 = (LayoutParams) view.getLayoutParams();
            layoutParams2.mNeedsBaseline = true;
            layoutParams2.mWidget.mHasBaseline = true;
        }
        constraintWidget.getAnchor(type2).connect(constraintWidget2.getAnchor(type), layoutParams.baselineMargin, layoutParams.goneBaselineMargin, true);
        constraintWidget.mHasBaseline = true;
        constraintWidget.getAnchor(ConstraintAnchor.Type.TOP).reset();
        constraintWidget.getAnchor(ConstraintAnchor.Type.BOTTOM).reset();
    }

    @Override // android.view.ViewGroup
    public boolean shouldDelayChildPressedState() {
        return this instanceof KeyguardSecurityContainer;
    }

    public ConstraintLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mChildrenByIds = new SparseArray();
        this.mConstraintHelpers = new ArrayList(4);
        this.mLayoutWidget = new ConstraintWidgetContainer();
        this.mMinWidth = 0;
        this.mMinHeight = 0;
        this.mMaxWidth = Integer.MAX_VALUE;
        this.mMaxHeight = Integer.MAX_VALUE;
        this.mDirtyHierarchy = true;
        this.mOptimizationLevel = 257;
        this.mConstraintSet = null;
        this.mConstraintLayoutSpec = null;
        this.mConstraintSetId = -1;
        this.mDesignIds = new HashMap();
        this.mTempMapIdToWidget = new SparseArray();
        this.mMeasurer = new Measurer(this);
        init(attributeSet, 0, 0);
    }

    public ConstraintLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mChildrenByIds = new SparseArray();
        this.mConstraintHelpers = new ArrayList(4);
        this.mLayoutWidget = new ConstraintWidgetContainer();
        this.mMinWidth = 0;
        this.mMinHeight = 0;
        this.mMaxWidth = Integer.MAX_VALUE;
        this.mMaxHeight = Integer.MAX_VALUE;
        this.mDirtyHierarchy = true;
        this.mOptimizationLevel = 257;
        this.mConstraintSet = null;
        this.mConstraintLayoutSpec = null;
        this.mConstraintSetId = -1;
        this.mDesignIds = new HashMap();
        this.mTempMapIdToWidget = new SparseArray();
        this.mMeasurer = new Measurer(this);
        init(attributeSet, i, 0);
    }

    public ConstraintLayout(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.mChildrenByIds = new SparseArray();
        this.mConstraintHelpers = new ArrayList(4);
        this.mLayoutWidget = new ConstraintWidgetContainer();
        this.mMinWidth = 0;
        this.mMinHeight = 0;
        this.mMaxWidth = Integer.MAX_VALUE;
        this.mMaxHeight = Integer.MAX_VALUE;
        this.mDirtyHierarchy = true;
        this.mOptimizationLevel = 257;
        this.mConstraintSet = null;
        this.mConstraintLayoutSpec = null;
        this.mConstraintSetId = -1;
        this.mDesignIds = new HashMap();
        this.mTempMapIdToWidget = new SparseArray();
        this.mMeasurer = new Measurer(this);
        init(attributeSet, i, i2);
    }

    @Override // android.view.ViewGroup
    public final ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        LayoutParams layoutParams2 = new LayoutParams(layoutParams);
        layoutParams2.guideBegin = -1;
        layoutParams2.guideEnd = -1;
        layoutParams2.guidePercent = -1.0f;
        layoutParams2.guidelineUseRtl = true;
        layoutParams2.leftToLeft = -1;
        layoutParams2.leftToRight = -1;
        layoutParams2.rightToLeft = -1;
        layoutParams2.rightToRight = -1;
        layoutParams2.topToTop = -1;
        layoutParams2.topToBottom = -1;
        layoutParams2.bottomToTop = -1;
        layoutParams2.bottomToBottom = -1;
        layoutParams2.baselineToBaseline = -1;
        layoutParams2.baselineToTop = -1;
        layoutParams2.baselineToBottom = -1;
        layoutParams2.circleConstraint = -1;
        layoutParams2.circleRadius = 0;
        layoutParams2.circleAngle = 0.0f;
        layoutParams2.startToEnd = -1;
        layoutParams2.startToStart = -1;
        layoutParams2.endToStart = -1;
        layoutParams2.endToEnd = -1;
        layoutParams2.goneLeftMargin = Integer.MIN_VALUE;
        layoutParams2.goneTopMargin = Integer.MIN_VALUE;
        layoutParams2.goneRightMargin = Integer.MIN_VALUE;
        layoutParams2.goneBottomMargin = Integer.MIN_VALUE;
        layoutParams2.goneStartMargin = Integer.MIN_VALUE;
        layoutParams2.goneEndMargin = Integer.MIN_VALUE;
        layoutParams2.goneBaselineMargin = Integer.MIN_VALUE;
        layoutParams2.baselineMargin = 0;
        layoutParams2.horizontalBias = 0.5f;
        layoutParams2.verticalBias = 0.5f;
        layoutParams2.dimensionRatio = null;
        layoutParams2.horizontalWeight = -1.0f;
        layoutParams2.verticalWeight = -1.0f;
        layoutParams2.horizontalChainStyle = 0;
        layoutParams2.verticalChainStyle = 0;
        layoutParams2.matchConstraintDefaultWidth = 0;
        layoutParams2.matchConstraintDefaultHeight = 0;
        layoutParams2.matchConstraintMinWidth = 0;
        layoutParams2.matchConstraintMinHeight = 0;
        layoutParams2.matchConstraintMaxWidth = 0;
        layoutParams2.matchConstraintMaxHeight = 0;
        layoutParams2.matchConstraintPercentWidth = 1.0f;
        layoutParams2.matchConstraintPercentHeight = 1.0f;
        layoutParams2.editorAbsoluteX = -1;
        layoutParams2.editorAbsoluteY = -1;
        layoutParams2.orientation = -1;
        layoutParams2.constrainedWidth = false;
        layoutParams2.constrainedHeight = false;
        layoutParams2.constraintTag = null;
        layoutParams2.wrapBehaviorInParent = 0;
        layoutParams2.mHorizontalDimensionFixed = true;
        layoutParams2.mVerticalDimensionFixed = true;
        layoutParams2.mNeedsBaseline = false;
        layoutParams2.mIsGuideline = false;
        layoutParams2.mIsHelper = false;
        layoutParams2.mResolvedLeftToLeft = -1;
        layoutParams2.mResolvedLeftToRight = -1;
        layoutParams2.mResolvedRightToLeft = -1;
        layoutParams2.mResolvedRightToRight = -1;
        layoutParams2.mResolveGoneLeftMargin = Integer.MIN_VALUE;
        layoutParams2.mResolveGoneRightMargin = Integer.MIN_VALUE;
        layoutParams2.mResolvedHorizontalBias = 0.5f;
        layoutParams2.mWidget = new ConstraintWidget();
        if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) layoutParams;
            ((ViewGroup.MarginLayoutParams) layoutParams2).leftMargin = marginLayoutParams.leftMargin;
            ((ViewGroup.MarginLayoutParams) layoutParams2).rightMargin = marginLayoutParams.rightMargin;
            ((ViewGroup.MarginLayoutParams) layoutParams2).topMargin = marginLayoutParams.topMargin;
            ((ViewGroup.MarginLayoutParams) layoutParams2).bottomMargin = marginLayoutParams.bottomMargin;
            layoutParams2.setMarginStart(marginLayoutParams.getMarginStart());
            layoutParams2.setMarginEnd(marginLayoutParams.getMarginEnd());
        }
        if (layoutParams instanceof LayoutParams) {
            LayoutParams layoutParams3 = (LayoutParams) layoutParams;
            layoutParams2.guideBegin = layoutParams3.guideBegin;
            layoutParams2.guideEnd = layoutParams3.guideEnd;
            layoutParams2.guidePercent = layoutParams3.guidePercent;
            layoutParams2.guidelineUseRtl = layoutParams3.guidelineUseRtl;
            layoutParams2.leftToLeft = layoutParams3.leftToLeft;
            layoutParams2.leftToRight = layoutParams3.leftToRight;
            layoutParams2.rightToLeft = layoutParams3.rightToLeft;
            layoutParams2.rightToRight = layoutParams3.rightToRight;
            layoutParams2.topToTop = layoutParams3.topToTop;
            layoutParams2.topToBottom = layoutParams3.topToBottom;
            layoutParams2.bottomToTop = layoutParams3.bottomToTop;
            layoutParams2.bottomToBottom = layoutParams3.bottomToBottom;
            layoutParams2.baselineToBaseline = layoutParams3.baselineToBaseline;
            layoutParams2.baselineToTop = layoutParams3.baselineToTop;
            layoutParams2.baselineToBottom = layoutParams3.baselineToBottom;
            layoutParams2.circleConstraint = layoutParams3.circleConstraint;
            layoutParams2.circleRadius = layoutParams3.circleRadius;
            layoutParams2.circleAngle = layoutParams3.circleAngle;
            layoutParams2.startToEnd = layoutParams3.startToEnd;
            layoutParams2.startToStart = layoutParams3.startToStart;
            layoutParams2.endToStart = layoutParams3.endToStart;
            layoutParams2.endToEnd = layoutParams3.endToEnd;
            layoutParams2.goneLeftMargin = layoutParams3.goneLeftMargin;
            layoutParams2.goneTopMargin = layoutParams3.goneTopMargin;
            layoutParams2.goneRightMargin = layoutParams3.goneRightMargin;
            layoutParams2.goneBottomMargin = layoutParams3.goneBottomMargin;
            layoutParams2.goneStartMargin = layoutParams3.goneStartMargin;
            layoutParams2.goneEndMargin = layoutParams3.goneEndMargin;
            layoutParams2.goneBaselineMargin = layoutParams3.goneBaselineMargin;
            layoutParams2.baselineMargin = layoutParams3.baselineMargin;
            layoutParams2.horizontalBias = layoutParams3.horizontalBias;
            layoutParams2.verticalBias = layoutParams3.verticalBias;
            layoutParams2.dimensionRatio = layoutParams3.dimensionRatio;
            layoutParams2.horizontalWeight = layoutParams3.horizontalWeight;
            layoutParams2.verticalWeight = layoutParams3.verticalWeight;
            layoutParams2.horizontalChainStyle = layoutParams3.horizontalChainStyle;
            layoutParams2.verticalChainStyle = layoutParams3.verticalChainStyle;
            layoutParams2.constrainedWidth = layoutParams3.constrainedWidth;
            layoutParams2.constrainedHeight = layoutParams3.constrainedHeight;
            layoutParams2.matchConstraintDefaultWidth = layoutParams3.matchConstraintDefaultWidth;
            layoutParams2.matchConstraintDefaultHeight = layoutParams3.matchConstraintDefaultHeight;
            layoutParams2.matchConstraintMinWidth = layoutParams3.matchConstraintMinWidth;
            layoutParams2.matchConstraintMaxWidth = layoutParams3.matchConstraintMaxWidth;
            layoutParams2.matchConstraintMinHeight = layoutParams3.matchConstraintMinHeight;
            layoutParams2.matchConstraintMaxHeight = layoutParams3.matchConstraintMaxHeight;
            layoutParams2.matchConstraintPercentWidth = layoutParams3.matchConstraintPercentWidth;
            layoutParams2.matchConstraintPercentHeight = layoutParams3.matchConstraintPercentHeight;
            layoutParams2.editorAbsoluteX = layoutParams3.editorAbsoluteX;
            layoutParams2.editorAbsoluteY = layoutParams3.editorAbsoluteY;
            layoutParams2.orientation = layoutParams3.orientation;
            layoutParams2.mHorizontalDimensionFixed = layoutParams3.mHorizontalDimensionFixed;
            layoutParams2.mVerticalDimensionFixed = layoutParams3.mVerticalDimensionFixed;
            layoutParams2.mNeedsBaseline = layoutParams3.mNeedsBaseline;
            layoutParams2.mIsGuideline = layoutParams3.mIsGuideline;
            layoutParams2.mResolvedLeftToLeft = layoutParams3.mResolvedLeftToLeft;
            layoutParams2.mResolvedLeftToRight = layoutParams3.mResolvedLeftToRight;
            layoutParams2.mResolvedRightToLeft = layoutParams3.mResolvedRightToLeft;
            layoutParams2.mResolvedRightToRight = layoutParams3.mResolvedRightToRight;
            layoutParams2.mResolveGoneLeftMargin = layoutParams3.mResolveGoneLeftMargin;
            layoutParams2.mResolveGoneRightMargin = layoutParams3.mResolveGoneRightMargin;
            layoutParams2.mResolvedHorizontalBias = layoutParams3.mResolvedHorizontalBias;
            layoutParams2.constraintTag = layoutParams3.constraintTag;
            layoutParams2.wrapBehaviorInParent = layoutParams3.wrapBehaviorInParent;
            layoutParams2.mWidget = layoutParams3.mWidget;
        }
        return layoutParams2;
    }
}
