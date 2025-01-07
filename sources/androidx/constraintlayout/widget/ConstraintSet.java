package androidx.constraintlayout.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.graphics.drawable.ColorDrawable;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseIntArray;
import android.util.Xml;
import android.view.View;
import android.view.ViewGroup;
import androidx.activity.result.ActivityResultRegistry$$ExternalSyntheticOutline0;
import androidx.constraintlayout.core.motion.utils.Easing;
import androidx.constraintlayout.motion.widget.Debug;
import androidx.constraintlayout.motion.widget.MotionLayout;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.android.app.viewcapture.data.ViewNode;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class ConstraintSet {
    public static final int[] VISIBILITY_FLAGS = {0, 4, 8};
    public static final SparseIntArray sMapToConstant;
    public static final SparseIntArray sOverrideMapToConstant;
    public String mIdString;
    public String derivedState = "";
    public String[] mMatchLabels = new String[0];
    public int mRotate = 0;
    public final HashMap mSavedAttributes = new HashMap();
    public boolean mForceId = true;
    public final HashMap mConstraints = new HashMap();

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Constraint {
        public final Layout layout;
        public HashMap mCustomConstraints;
        public Delta mDelta;
        public String mTargetString;
        public int mViewId;
        public final Motion motion;
        public final PropertySet propertySet;
        public final Transform transform;

        public Constraint() {
            PropertySet propertySet = new PropertySet();
            propertySet.mApply = false;
            propertySet.visibility = 0;
            propertySet.mVisibilityMode = 0;
            propertySet.alpha = 1.0f;
            propertySet.mProgress = Float.NaN;
            this.propertySet = propertySet;
            Motion motion = new Motion();
            motion.mApply = false;
            motion.mAnimateRelativeTo = -1;
            motion.mAnimateCircleAngleTo = 0;
            motion.mTransitionEasing = null;
            motion.mPathMotionArc = -1;
            motion.mDrawPath = 0;
            motion.mMotionStagger = Float.NaN;
            motion.mPathRotate = Float.NaN;
            motion.mQuantizeMotionPhase = Float.NaN;
            motion.mQuantizeMotionSteps = -1;
            motion.mQuantizeInterpolatorString = null;
            motion.mQuantizeInterpolatorType = -3;
            motion.mQuantizeInterpolatorID = -1;
            this.motion = motion;
            this.layout = new Layout();
            Transform transform = new Transform();
            transform.mApply = false;
            transform.rotation = 0.0f;
            transform.rotationX = 0.0f;
            transform.rotationY = 0.0f;
            transform.scaleX = 1.0f;
            transform.scaleY = 1.0f;
            transform.transformPivotX = Float.NaN;
            transform.transformPivotY = Float.NaN;
            transform.transformPivotTarget = -1;
            transform.translationX = 0.0f;
            transform.translationY = 0.0f;
            transform.translationZ = 0.0f;
            transform.applyElevation = false;
            transform.elevation = 0.0f;
            this.transform = transform;
            this.mCustomConstraints = new HashMap();
        }

        public static void access$000(Constraint constraint, int i, ConstraintLayout.LayoutParams layoutParams) {
            constraint.mViewId = i;
            int i2 = layoutParams.leftToLeft;
            Layout layout = constraint.layout;
            layout.leftToLeft = i2;
            layout.leftToRight = layoutParams.leftToRight;
            layout.rightToLeft = layoutParams.rightToLeft;
            layout.rightToRight = layoutParams.rightToRight;
            layout.topToTop = layoutParams.topToTop;
            layout.topToBottom = layoutParams.topToBottom;
            layout.bottomToTop = layoutParams.bottomToTop;
            layout.bottomToBottom = layoutParams.bottomToBottom;
            layout.baselineToBaseline = layoutParams.baselineToBaseline;
            layout.baselineToTop = layoutParams.baselineToTop;
            layout.baselineToBottom = layoutParams.baselineToBottom;
            layout.startToEnd = layoutParams.startToEnd;
            layout.startToStart = layoutParams.startToStart;
            layout.endToStart = layoutParams.endToStart;
            layout.endToEnd = layoutParams.endToEnd;
            layout.horizontalBias = layoutParams.horizontalBias;
            layout.verticalBias = layoutParams.verticalBias;
            layout.dimensionRatio = layoutParams.dimensionRatio;
            layout.circleConstraint = layoutParams.circleConstraint;
            layout.circleRadius = layoutParams.circleRadius;
            layout.circleAngle = layoutParams.circleAngle;
            layout.editorAbsoluteX = layoutParams.editorAbsoluteX;
            layout.editorAbsoluteY = layoutParams.editorAbsoluteY;
            layout.orientation = layoutParams.orientation;
            layout.guidePercent = layoutParams.guidePercent;
            layout.guideBegin = layoutParams.guideBegin;
            layout.guideEnd = layoutParams.guideEnd;
            layout.mWidth = ((ViewGroup.MarginLayoutParams) layoutParams).width;
            layout.mHeight = ((ViewGroup.MarginLayoutParams) layoutParams).height;
            layout.leftMargin = ((ViewGroup.MarginLayoutParams) layoutParams).leftMargin;
            layout.rightMargin = ((ViewGroup.MarginLayoutParams) layoutParams).rightMargin;
            layout.topMargin = ((ViewGroup.MarginLayoutParams) layoutParams).topMargin;
            layout.bottomMargin = ((ViewGroup.MarginLayoutParams) layoutParams).bottomMargin;
            layout.baselineMargin = layoutParams.baselineMargin;
            layout.verticalWeight = layoutParams.verticalWeight;
            layout.horizontalWeight = layoutParams.horizontalWeight;
            layout.verticalChainStyle = layoutParams.verticalChainStyle;
            layout.horizontalChainStyle = layoutParams.horizontalChainStyle;
            layout.constrainedWidth = layoutParams.constrainedWidth;
            layout.constrainedHeight = layoutParams.constrainedHeight;
            layout.widthDefault = layoutParams.matchConstraintDefaultWidth;
            layout.heightDefault = layoutParams.matchConstraintDefaultHeight;
            layout.widthMax = layoutParams.matchConstraintMaxWidth;
            layout.heightMax = layoutParams.matchConstraintMaxHeight;
            layout.widthMin = layoutParams.matchConstraintMinWidth;
            layout.heightMin = layoutParams.matchConstraintMinHeight;
            layout.widthPercent = layoutParams.matchConstraintPercentWidth;
            layout.heightPercent = layoutParams.matchConstraintPercentHeight;
            layout.mConstraintTag = layoutParams.constraintTag;
            layout.goneTopMargin = layoutParams.goneTopMargin;
            layout.goneBottomMargin = layoutParams.goneBottomMargin;
            layout.goneLeftMargin = layoutParams.goneLeftMargin;
            layout.goneRightMargin = layoutParams.goneRightMargin;
            layout.goneStartMargin = layoutParams.goneStartMargin;
            layout.goneEndMargin = layoutParams.goneEndMargin;
            layout.goneBaselineMargin = layoutParams.goneBaselineMargin;
            layout.mWrapBehavior = layoutParams.wrapBehaviorInParent;
            layout.endMargin = layoutParams.getMarginEnd();
            layout.startMargin = layoutParams.getMarginStart();
        }

        public final void applyTo(ConstraintLayout.LayoutParams layoutParams) {
            Layout layout = this.layout;
            layoutParams.leftToLeft = layout.leftToLeft;
            layoutParams.leftToRight = layout.leftToRight;
            layoutParams.rightToLeft = layout.rightToLeft;
            layoutParams.rightToRight = layout.rightToRight;
            layoutParams.topToTop = layout.topToTop;
            layoutParams.topToBottom = layout.topToBottom;
            layoutParams.bottomToTop = layout.bottomToTop;
            layoutParams.bottomToBottom = layout.bottomToBottom;
            layoutParams.baselineToBaseline = layout.baselineToBaseline;
            layoutParams.baselineToTop = layout.baselineToTop;
            layoutParams.baselineToBottom = layout.baselineToBottom;
            layoutParams.startToEnd = layout.startToEnd;
            layoutParams.startToStart = layout.startToStart;
            layoutParams.endToStart = layout.endToStart;
            layoutParams.endToEnd = layout.endToEnd;
            ((ViewGroup.MarginLayoutParams) layoutParams).leftMargin = layout.leftMargin;
            ((ViewGroup.MarginLayoutParams) layoutParams).rightMargin = layout.rightMargin;
            ((ViewGroup.MarginLayoutParams) layoutParams).topMargin = layout.topMargin;
            ((ViewGroup.MarginLayoutParams) layoutParams).bottomMargin = layout.bottomMargin;
            layoutParams.goneStartMargin = layout.goneStartMargin;
            layoutParams.goneEndMargin = layout.goneEndMargin;
            layoutParams.goneTopMargin = layout.goneTopMargin;
            layoutParams.goneBottomMargin = layout.goneBottomMargin;
            layoutParams.horizontalBias = layout.horizontalBias;
            layoutParams.verticalBias = layout.verticalBias;
            layoutParams.circleConstraint = layout.circleConstraint;
            layoutParams.circleRadius = layout.circleRadius;
            layoutParams.circleAngle = layout.circleAngle;
            layoutParams.dimensionRatio = layout.dimensionRatio;
            layoutParams.editorAbsoluteX = layout.editorAbsoluteX;
            layoutParams.editorAbsoluteY = layout.editorAbsoluteY;
            layoutParams.verticalWeight = layout.verticalWeight;
            layoutParams.horizontalWeight = layout.horizontalWeight;
            layoutParams.verticalChainStyle = layout.verticalChainStyle;
            layoutParams.horizontalChainStyle = layout.horizontalChainStyle;
            layoutParams.constrainedWidth = layout.constrainedWidth;
            layoutParams.constrainedHeight = layout.constrainedHeight;
            layoutParams.matchConstraintDefaultWidth = layout.widthDefault;
            layoutParams.matchConstraintDefaultHeight = layout.heightDefault;
            layoutParams.matchConstraintMaxWidth = layout.widthMax;
            layoutParams.matchConstraintMaxHeight = layout.heightMax;
            layoutParams.matchConstraintMinWidth = layout.widthMin;
            layoutParams.matchConstraintMinHeight = layout.heightMin;
            layoutParams.matchConstraintPercentWidth = layout.widthPercent;
            layoutParams.matchConstraintPercentHeight = layout.heightPercent;
            layoutParams.orientation = layout.orientation;
            layoutParams.guidePercent = layout.guidePercent;
            layoutParams.guideBegin = layout.guideBegin;
            layoutParams.guideEnd = layout.guideEnd;
            ((ViewGroup.MarginLayoutParams) layoutParams).width = layout.mWidth;
            ((ViewGroup.MarginLayoutParams) layoutParams).height = layout.mHeight;
            String str = layout.mConstraintTag;
            if (str != null) {
                layoutParams.constraintTag = str;
            }
            layoutParams.wrapBehaviorInParent = layout.mWrapBehavior;
            layoutParams.setMarginStart(layout.startMargin);
            layoutParams.setMarginEnd(layout.endMargin);
            layoutParams.validate();
        }

        /* renamed from: clone, reason: merged with bridge method [inline-methods] */
        public final Constraint m709clone() {
            Constraint constraint = new Constraint();
            constraint.layout.copyFrom(this.layout);
            constraint.motion.copyFrom(this.motion);
            PropertySet propertySet = constraint.propertySet;
            PropertySet propertySet2 = this.propertySet;
            propertySet.mApply = propertySet2.mApply;
            propertySet.visibility = propertySet2.visibility;
            propertySet.alpha = propertySet2.alpha;
            propertySet.mProgress = propertySet2.mProgress;
            propertySet.mVisibilityMode = propertySet2.mVisibilityMode;
            constraint.transform.copyFrom(this.transform);
            constraint.mViewId = this.mViewId;
            constraint.mDelta = this.mDelta;
            return constraint;
        }

        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        public final class Delta {
            public int mCountBoolean;
            public int mCountFloat;
            public int mCountInt;
            public int mCountString;
            public int[] mTypeBoolean;
            public int[] mTypeFloat;
            public int[] mTypeInt;
            public int[] mTypeString;
            public boolean[] mValueBoolean;
            public float[] mValueFloat;
            public int[] mValueInt;
            public String[] mValueString;

            public final void add(int i, int i2) {
                int i3 = this.mCountInt;
                int[] iArr = this.mTypeInt;
                if (i3 >= iArr.length) {
                    this.mTypeInt = Arrays.copyOf(iArr, iArr.length * 2);
                    int[] iArr2 = this.mValueInt;
                    this.mValueInt = Arrays.copyOf(iArr2, iArr2.length * 2);
                }
                int[] iArr3 = this.mTypeInt;
                int i4 = this.mCountInt;
                iArr3[i4] = i;
                int[] iArr4 = this.mValueInt;
                this.mCountInt = i4 + 1;
                iArr4[i4] = i2;
            }

            public final void applyDelta(Constraint constraint) {
                for (int i = 0; i < this.mCountInt; i++) {
                    int i2 = this.mTypeInt[i];
                    int i3 = this.mValueInt[i];
                    if (i2 == 6) {
                        constraint.layout.editorAbsoluteX = i3;
                    } else if (i2 == 7) {
                        constraint.layout.editorAbsoluteY = i3;
                    } else if (i2 == 8) {
                        constraint.layout.endMargin = i3;
                    } else if (i2 == 27) {
                        constraint.layout.orientation = i3;
                    } else if (i2 == 28) {
                        constraint.layout.rightMargin = i3;
                    } else if (i2 == 41) {
                        constraint.layout.horizontalChainStyle = i3;
                    } else if (i2 == 42) {
                        constraint.layout.verticalChainStyle = i3;
                    } else if (i2 == 61) {
                        constraint.layout.circleConstraint = i3;
                    } else if (i2 == 62) {
                        constraint.layout.circleRadius = i3;
                    } else if (i2 == 72) {
                        constraint.layout.mBarrierDirection = i3;
                    } else if (i2 == 73) {
                        constraint.layout.mBarrierMargin = i3;
                    } else if (i2 == 2) {
                        constraint.layout.bottomMargin = i3;
                    } else if (i2 == 31) {
                        constraint.layout.startMargin = i3;
                    } else if (i2 == 34) {
                        constraint.layout.topMargin = i3;
                    } else if (i2 == 38) {
                        constraint.mViewId = i3;
                    } else if (i2 == 64) {
                        constraint.motion.mAnimateRelativeTo = i3;
                    } else if (i2 == 66) {
                        constraint.motion.mDrawPath = i3;
                    } else if (i2 == 76) {
                        constraint.motion.mPathMotionArc = i3;
                    } else if (i2 == 78) {
                        constraint.propertySet.mVisibilityMode = i3;
                    } else if (i2 == 97) {
                        constraint.layout.mWrapBehavior = i3;
                    } else if (i2 == 93) {
                        constraint.layout.baselineMargin = i3;
                    } else if (i2 != 94) {
                        switch (i2) {
                            case ViewNode.TRANSLATIONX_FIELD_NUMBER /* 11 */:
                                constraint.layout.goneBottomMargin = i3;
                                break;
                            case ViewNode.TRANSLATIONY_FIELD_NUMBER /* 12 */:
                                constraint.layout.goneEndMargin = i3;
                                break;
                            case ViewNode.SCALEX_FIELD_NUMBER /* 13 */:
                                constraint.layout.goneLeftMargin = i3;
                                break;
                            case ViewNode.SCALEY_FIELD_NUMBER /* 14 */:
                                constraint.layout.goneRightMargin = i3;
                                break;
                            case 15:
                                constraint.layout.goneStartMargin = i3;
                                break;
                            case 16:
                                constraint.layout.goneTopMargin = i3;
                                break;
                            case ViewNode.CLIPCHILDREN_FIELD_NUMBER /* 17 */:
                                constraint.layout.guideBegin = i3;
                                break;
                            case ViewNode.VISIBILITY_FIELD_NUMBER /* 18 */:
                                constraint.layout.guideEnd = i3;
                                break;
                            default:
                                switch (i2) {
                                    case 21:
                                        constraint.layout.mHeight = i3;
                                        break;
                                    case 22:
                                        constraint.propertySet.visibility = i3;
                                        break;
                                    case 23:
                                        constraint.layout.mWidth = i3;
                                        break;
                                    case 24:
                                        constraint.layout.leftMargin = i3;
                                        break;
                                    default:
                                        switch (i2) {
                                            case 54:
                                                constraint.layout.widthDefault = i3;
                                                break;
                                            case 55:
                                                constraint.layout.heightDefault = i3;
                                                break;
                                            case 56:
                                                constraint.layout.widthMax = i3;
                                                break;
                                            case 57:
                                                constraint.layout.heightMax = i3;
                                                break;
                                            case 58:
                                                constraint.layout.widthMin = i3;
                                                break;
                                            case 59:
                                                constraint.layout.heightMin = i3;
                                                break;
                                            default:
                                                switch (i2) {
                                                    case 82:
                                                        constraint.motion.mAnimateCircleAngleTo = i3;
                                                        break;
                                                    case 83:
                                                        constraint.transform.transformPivotTarget = i3;
                                                        break;
                                                    case 84:
                                                        constraint.motion.mQuantizeMotionSteps = i3;
                                                        break;
                                                    default:
                                                        switch (i2) {
                                                            case 87:
                                                                break;
                                                            case 88:
                                                                constraint.motion.mQuantizeInterpolatorType = i3;
                                                                break;
                                                            case 89:
                                                                constraint.motion.mQuantizeInterpolatorID = i3;
                                                                break;
                                                            default:
                                                                Log.w("ConstraintSet", "Unknown attribute 0x");
                                                                break;
                                                        }
                                                }
                                        }
                                }
                        }
                    } else {
                        constraint.layout.goneBaselineMargin = i3;
                    }
                }
                for (int i4 = 0; i4 < this.mCountFloat; i4++) {
                    int i5 = this.mTypeFloat[i4];
                    float f = this.mValueFloat[i4];
                    if (i5 == 19) {
                        constraint.layout.guidePercent = f;
                    } else if (i5 == 20) {
                        constraint.layout.horizontalBias = f;
                    } else if (i5 == 37) {
                        constraint.layout.verticalBias = f;
                    } else if (i5 == 60) {
                        constraint.transform.rotation = f;
                    } else if (i5 == 63) {
                        constraint.layout.circleAngle = f;
                    } else if (i5 == 79) {
                        constraint.motion.mMotionStagger = f;
                    } else if (i5 == 85) {
                        constraint.motion.mQuantizeMotionPhase = f;
                    } else if (i5 != 87) {
                        if (i5 == 39) {
                            constraint.layout.horizontalWeight = f;
                        } else if (i5 != 40) {
                            switch (i5) {
                                case 43:
                                    constraint.propertySet.alpha = f;
                                    break;
                                case 44:
                                    Transform transform = constraint.transform;
                                    transform.elevation = f;
                                    transform.applyElevation = true;
                                    break;
                                case 45:
                                    constraint.transform.rotationX = f;
                                    break;
                                case 46:
                                    constraint.transform.rotationY = f;
                                    break;
                                case 47:
                                    constraint.transform.scaleX = f;
                                    break;
                                case 48:
                                    constraint.transform.scaleY = f;
                                    break;
                                case 49:
                                    constraint.transform.transformPivotX = f;
                                    break;
                                case 50:
                                    constraint.transform.transformPivotY = f;
                                    break;
                                case 51:
                                    constraint.transform.translationX = f;
                                    break;
                                case 52:
                                    constraint.transform.translationY = f;
                                    break;
                                case 53:
                                    constraint.transform.translationZ = f;
                                    break;
                                default:
                                    switch (i5) {
                                        case 67:
                                            constraint.motion.mPathRotate = f;
                                            break;
                                        case 68:
                                            constraint.propertySet.mProgress = f;
                                            break;
                                        case 69:
                                            constraint.layout.widthPercent = f;
                                            break;
                                        case 70:
                                            constraint.layout.heightPercent = f;
                                            break;
                                        default:
                                            Log.w("ConstraintSet", "Unknown attribute 0x");
                                            break;
                                    }
                            }
                        } else {
                            constraint.layout.verticalWeight = f;
                        }
                    }
                }
                for (int i6 = 0; i6 < this.mCountString; i6++) {
                    int i7 = this.mTypeString[i6];
                    String str = this.mValueString[i6];
                    if (i7 == 5) {
                        constraint.layout.dimensionRatio = str;
                    } else if (i7 == 65) {
                        constraint.motion.mTransitionEasing = str;
                    } else if (i7 == 74) {
                        Layout layout = constraint.layout;
                        layout.mReferenceIdString = str;
                        layout.mReferenceIds = null;
                    } else if (i7 == 77) {
                        constraint.layout.mConstraintTag = str;
                    } else if (i7 != 87) {
                        if (i7 != 90) {
                            Log.w("ConstraintSet", "Unknown attribute 0x");
                        } else {
                            constraint.motion.mQuantizeInterpolatorString = str;
                        }
                    }
                }
                for (int i8 = 0; i8 < this.mCountBoolean; i8++) {
                    int i9 = this.mTypeBoolean[i8];
                    boolean z = this.mValueBoolean[i8];
                    if (i9 == 44) {
                        constraint.transform.applyElevation = z;
                    } else if (i9 == 75) {
                        constraint.layout.mBarrierAllowsGoneWidgets = z;
                    } else if (i9 != 87) {
                        if (i9 == 80) {
                            constraint.layout.constrainedWidth = z;
                        } else if (i9 != 81) {
                            Log.w("ConstraintSet", "Unknown attribute 0x");
                        } else {
                            constraint.layout.constrainedHeight = z;
                        }
                    }
                }
            }

            public final void add(int i, float f) {
                int i2 = this.mCountFloat;
                int[] iArr = this.mTypeFloat;
                if (i2 >= iArr.length) {
                    this.mTypeFloat = Arrays.copyOf(iArr, iArr.length * 2);
                    float[] fArr = this.mValueFloat;
                    this.mValueFloat = Arrays.copyOf(fArr, fArr.length * 2);
                }
                int[] iArr2 = this.mTypeFloat;
                int i3 = this.mCountFloat;
                iArr2[i3] = i;
                float[] fArr2 = this.mValueFloat;
                this.mCountFloat = i3 + 1;
                fArr2[i3] = f;
            }

            public final void add(int i, String str) {
                int i2 = this.mCountString;
                int[] iArr = this.mTypeString;
                if (i2 >= iArr.length) {
                    this.mTypeString = Arrays.copyOf(iArr, iArr.length * 2);
                    String[] strArr = this.mValueString;
                    this.mValueString = (String[]) Arrays.copyOf(strArr, strArr.length * 2);
                }
                int[] iArr2 = this.mTypeString;
                int i3 = this.mCountString;
                iArr2[i3] = i;
                String[] strArr2 = this.mValueString;
                this.mCountString = i3 + 1;
                strArr2[i3] = str;
            }

            public final void add(int i, boolean z) {
                int i2 = this.mCountBoolean;
                int[] iArr = this.mTypeBoolean;
                if (i2 >= iArr.length) {
                    this.mTypeBoolean = Arrays.copyOf(iArr, iArr.length * 2);
                    boolean[] zArr = this.mValueBoolean;
                    this.mValueBoolean = Arrays.copyOf(zArr, zArr.length * 2);
                }
                int[] iArr2 = this.mTypeBoolean;
                int i3 = this.mCountBoolean;
                iArr2[i3] = i;
                boolean[] zArr2 = this.mValueBoolean;
                this.mCountBoolean = i3 + 1;
                zArr2[i3] = z;
            }
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Layout {
        public static final SparseIntArray sMapToConstant;
        public String mConstraintTag;
        public int mHeight;
        public String mReferenceIdString;
        public int[] mReferenceIds;
        public int mWidth;
        public boolean mIsGuideline = false;
        public boolean mApply = false;
        public int guideBegin = -1;
        public int guideEnd = -1;
        public float guidePercent = -1.0f;
        public boolean guidelineUseRtl = true;
        public int leftToLeft = -1;
        public int leftToRight = -1;
        public int rightToLeft = -1;
        public int rightToRight = -1;
        public int topToTop = -1;
        public int topToBottom = -1;
        public int bottomToTop = -1;
        public int bottomToBottom = -1;
        public int baselineToBaseline = -1;
        public int baselineToTop = -1;
        public int baselineToBottom = -1;
        public int startToEnd = -1;
        public int startToStart = -1;
        public int endToStart = -1;
        public int endToEnd = -1;
        public float horizontalBias = 0.5f;
        public float verticalBias = 0.5f;
        public String dimensionRatio = null;
        public int circleConstraint = -1;
        public int circleRadius = 0;
        public float circleAngle = 0.0f;
        public int editorAbsoluteX = -1;
        public int editorAbsoluteY = -1;
        public int orientation = -1;
        public int leftMargin = 0;
        public int rightMargin = 0;
        public int topMargin = 0;
        public int bottomMargin = 0;
        public int endMargin = 0;
        public int startMargin = 0;
        public int baselineMargin = 0;
        public int goneLeftMargin = Integer.MIN_VALUE;
        public int goneTopMargin = Integer.MIN_VALUE;
        public int goneRightMargin = Integer.MIN_VALUE;
        public int goneBottomMargin = Integer.MIN_VALUE;
        public int goneEndMargin = Integer.MIN_VALUE;
        public int goneStartMargin = Integer.MIN_VALUE;
        public int goneBaselineMargin = Integer.MIN_VALUE;
        public float verticalWeight = -1.0f;
        public float horizontalWeight = -1.0f;
        public int horizontalChainStyle = 0;
        public int verticalChainStyle = 0;
        public int widthDefault = 0;
        public int heightDefault = 0;
        public int widthMax = 0;
        public int heightMax = 0;
        public int widthMin = 0;
        public int heightMin = 0;
        public float widthPercent = 1.0f;
        public float heightPercent = 1.0f;
        public int mBarrierDirection = -1;
        public int mBarrierMargin = 0;
        public int mHelperType = -1;
        public boolean constrainedWidth = false;
        public boolean constrainedHeight = false;
        public boolean mBarrierAllowsGoneWidgets = true;
        public int mWrapBehavior = 0;

        static {
            SparseIntArray sparseIntArray = new SparseIntArray();
            sMapToConstant = sparseIntArray;
            sparseIntArray.append(43, 24);
            sparseIntArray.append(44, 25);
            sparseIntArray.append(46, 28);
            sparseIntArray.append(47, 29);
            sparseIntArray.append(52, 35);
            sparseIntArray.append(51, 34);
            sparseIntArray.append(24, 4);
            sparseIntArray.append(23, 3);
            sparseIntArray.append(19, 1);
            sparseIntArray.append(61, 6);
            sparseIntArray.append(62, 7);
            sparseIntArray.append(31, 17);
            sparseIntArray.append(32, 18);
            sparseIntArray.append(33, 19);
            sparseIntArray.append(15, 90);
            sparseIntArray.append(0, 26);
            sparseIntArray.append(48, 31);
            sparseIntArray.append(49, 32);
            sparseIntArray.append(30, 10);
            sparseIntArray.append(29, 9);
            sparseIntArray.append(66, 13);
            sparseIntArray.append(69, 16);
            sparseIntArray.append(67, 14);
            sparseIntArray.append(64, 11);
            sparseIntArray.append(68, 15);
            sparseIntArray.append(65, 12);
            sparseIntArray.append(55, 38);
            sparseIntArray.append(41, 37);
            sparseIntArray.append(40, 39);
            sparseIntArray.append(54, 40);
            sparseIntArray.append(39, 20);
            sparseIntArray.append(53, 36);
            sparseIntArray.append(28, 5);
            sparseIntArray.append(42, 91);
            sparseIntArray.append(50, 91);
            sparseIntArray.append(45, 91);
            sparseIntArray.append(22, 91);
            sparseIntArray.append(18, 91);
            sparseIntArray.append(3, 23);
            sparseIntArray.append(5, 27);
            sparseIntArray.append(7, 30);
            sparseIntArray.append(8, 8);
            sparseIntArray.append(4, 33);
            sparseIntArray.append(6, 2);
            sparseIntArray.append(1, 22);
            sparseIntArray.append(2, 21);
            sparseIntArray.append(56, 41);
            sparseIntArray.append(34, 42);
            sparseIntArray.append(17, 87);
            sparseIntArray.append(16, 88);
            sparseIntArray.append(71, 76);
            sparseIntArray.append(25, 61);
            sparseIntArray.append(27, 62);
            sparseIntArray.append(26, 63);
            sparseIntArray.append(60, 69);
            sparseIntArray.append(38, 70);
            sparseIntArray.append(12, 71);
            sparseIntArray.append(10, 72);
            sparseIntArray.append(11, 73);
            sparseIntArray.append(13, 74);
            sparseIntArray.append(9, 75);
            sparseIntArray.append(58, 84);
            sparseIntArray.append(59, 86);
            sparseIntArray.append(58, 83);
            sparseIntArray.append(37, 85);
            sparseIntArray.append(56, 87);
            sparseIntArray.append(34, 88);
            sparseIntArray.append(91, 89);
            sparseIntArray.append(15, 90);
        }

        public final void copyFrom(Layout layout) {
            this.mIsGuideline = layout.mIsGuideline;
            this.mWidth = layout.mWidth;
            this.mApply = layout.mApply;
            this.mHeight = layout.mHeight;
            this.guideBegin = layout.guideBegin;
            this.guideEnd = layout.guideEnd;
            this.guidePercent = layout.guidePercent;
            this.guidelineUseRtl = layout.guidelineUseRtl;
            this.leftToLeft = layout.leftToLeft;
            this.leftToRight = layout.leftToRight;
            this.rightToLeft = layout.rightToLeft;
            this.rightToRight = layout.rightToRight;
            this.topToTop = layout.topToTop;
            this.topToBottom = layout.topToBottom;
            this.bottomToTop = layout.bottomToTop;
            this.bottomToBottom = layout.bottomToBottom;
            this.baselineToBaseline = layout.baselineToBaseline;
            this.baselineToTop = layout.baselineToTop;
            this.baselineToBottom = layout.baselineToBottom;
            this.startToEnd = layout.startToEnd;
            this.startToStart = layout.startToStart;
            this.endToStart = layout.endToStart;
            this.endToEnd = layout.endToEnd;
            this.horizontalBias = layout.horizontalBias;
            this.verticalBias = layout.verticalBias;
            this.dimensionRatio = layout.dimensionRatio;
            this.circleConstraint = layout.circleConstraint;
            this.circleRadius = layout.circleRadius;
            this.circleAngle = layout.circleAngle;
            this.editorAbsoluteX = layout.editorAbsoluteX;
            this.editorAbsoluteY = layout.editorAbsoluteY;
            this.orientation = layout.orientation;
            this.leftMargin = layout.leftMargin;
            this.rightMargin = layout.rightMargin;
            this.topMargin = layout.topMargin;
            this.bottomMargin = layout.bottomMargin;
            this.endMargin = layout.endMargin;
            this.startMargin = layout.startMargin;
            this.baselineMargin = layout.baselineMargin;
            this.goneLeftMargin = layout.goneLeftMargin;
            this.goneTopMargin = layout.goneTopMargin;
            this.goneRightMargin = layout.goneRightMargin;
            this.goneBottomMargin = layout.goneBottomMargin;
            this.goneEndMargin = layout.goneEndMargin;
            this.goneStartMargin = layout.goneStartMargin;
            this.goneBaselineMargin = layout.goneBaselineMargin;
            this.verticalWeight = layout.verticalWeight;
            this.horizontalWeight = layout.horizontalWeight;
            this.horizontalChainStyle = layout.horizontalChainStyle;
            this.verticalChainStyle = layout.verticalChainStyle;
            this.widthDefault = layout.widthDefault;
            this.heightDefault = layout.heightDefault;
            this.widthMax = layout.widthMax;
            this.heightMax = layout.heightMax;
            this.widthMin = layout.widthMin;
            this.heightMin = layout.heightMin;
            this.widthPercent = layout.widthPercent;
            this.heightPercent = layout.heightPercent;
            this.mBarrierDirection = layout.mBarrierDirection;
            this.mBarrierMargin = layout.mBarrierMargin;
            this.mHelperType = layout.mHelperType;
            this.mConstraintTag = layout.mConstraintTag;
            int[] iArr = layout.mReferenceIds;
            if (iArr == null || layout.mReferenceIdString != null) {
                this.mReferenceIds = null;
            } else {
                this.mReferenceIds = Arrays.copyOf(iArr, iArr.length);
            }
            this.mReferenceIdString = layout.mReferenceIdString;
            this.constrainedWidth = layout.constrainedWidth;
            this.constrainedHeight = layout.constrainedHeight;
            this.mBarrierAllowsGoneWidgets = layout.mBarrierAllowsGoneWidgets;
            this.mWrapBehavior = layout.mWrapBehavior;
        }

        public final void fillFromAttributeList(Context context, AttributeSet attributeSet) {
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.Layout);
            this.mApply = true;
            int indexCount = obtainStyledAttributes.getIndexCount();
            for (int i = 0; i < indexCount; i++) {
                int index = obtainStyledAttributes.getIndex(i);
                SparseIntArray sparseIntArray = sMapToConstant;
                int i2 = sparseIntArray.get(index);
                switch (i2) {
                    case 1:
                        this.baselineToBaseline = ConstraintSet.lookupID(obtainStyledAttributes, index, this.baselineToBaseline);
                        break;
                    case 2:
                        this.bottomMargin = obtainStyledAttributes.getDimensionPixelSize(index, this.bottomMargin);
                        break;
                    case 3:
                        this.bottomToBottom = ConstraintSet.lookupID(obtainStyledAttributes, index, this.bottomToBottom);
                        break;
                    case 4:
                        this.bottomToTop = ConstraintSet.lookupID(obtainStyledAttributes, index, this.bottomToTop);
                        break;
                    case 5:
                        this.dimensionRatio = obtainStyledAttributes.getString(index);
                        break;
                    case 6:
                        this.editorAbsoluteX = obtainStyledAttributes.getDimensionPixelOffset(index, this.editorAbsoluteX);
                        break;
                    case 7:
                        this.editorAbsoluteY = obtainStyledAttributes.getDimensionPixelOffset(index, this.editorAbsoluteY);
                        break;
                    case 8:
                        this.endMargin = obtainStyledAttributes.getDimensionPixelSize(index, this.endMargin);
                        break;
                    case 9:
                        this.endToEnd = ConstraintSet.lookupID(obtainStyledAttributes, index, this.endToEnd);
                        break;
                    case 10:
                        this.endToStart = ConstraintSet.lookupID(obtainStyledAttributes, index, this.endToStart);
                        break;
                    case ViewNode.TRANSLATIONX_FIELD_NUMBER /* 11 */:
                        this.goneBottomMargin = obtainStyledAttributes.getDimensionPixelSize(index, this.goneBottomMargin);
                        break;
                    case ViewNode.TRANSLATIONY_FIELD_NUMBER /* 12 */:
                        this.goneEndMargin = obtainStyledAttributes.getDimensionPixelSize(index, this.goneEndMargin);
                        break;
                    case ViewNode.SCALEX_FIELD_NUMBER /* 13 */:
                        this.goneLeftMargin = obtainStyledAttributes.getDimensionPixelSize(index, this.goneLeftMargin);
                        break;
                    case ViewNode.SCALEY_FIELD_NUMBER /* 14 */:
                        this.goneRightMargin = obtainStyledAttributes.getDimensionPixelSize(index, this.goneRightMargin);
                        break;
                    case 15:
                        this.goneStartMargin = obtainStyledAttributes.getDimensionPixelSize(index, this.goneStartMargin);
                        break;
                    case 16:
                        this.goneTopMargin = obtainStyledAttributes.getDimensionPixelSize(index, this.goneTopMargin);
                        break;
                    case ViewNode.CLIPCHILDREN_FIELD_NUMBER /* 17 */:
                        this.guideBegin = obtainStyledAttributes.getDimensionPixelOffset(index, this.guideBegin);
                        break;
                    case ViewNode.VISIBILITY_FIELD_NUMBER /* 18 */:
                        this.guideEnd = obtainStyledAttributes.getDimensionPixelOffset(index, this.guideEnd);
                        break;
                    case ViewNode.ELEVATION_FIELD_NUMBER /* 19 */:
                        this.guidePercent = obtainStyledAttributes.getFloat(index, this.guidePercent);
                        break;
                    case 20:
                        this.horizontalBias = obtainStyledAttributes.getFloat(index, this.horizontalBias);
                        break;
                    case 21:
                        this.mHeight = obtainStyledAttributes.getLayoutDimension(index, this.mHeight);
                        break;
                    case 22:
                        this.mWidth = obtainStyledAttributes.getLayoutDimension(index, this.mWidth);
                        break;
                    case 23:
                        this.leftMargin = obtainStyledAttributes.getDimensionPixelSize(index, this.leftMargin);
                        break;
                    case 24:
                        this.leftToLeft = ConstraintSet.lookupID(obtainStyledAttributes, index, this.leftToLeft);
                        break;
                    case 25:
                        this.leftToRight = ConstraintSet.lookupID(obtainStyledAttributes, index, this.leftToRight);
                        break;
                    case 26:
                        this.orientation = obtainStyledAttributes.getInt(index, this.orientation);
                        break;
                    case 27:
                        this.rightMargin = obtainStyledAttributes.getDimensionPixelSize(index, this.rightMargin);
                        break;
                    case 28:
                        this.rightToLeft = ConstraintSet.lookupID(obtainStyledAttributes, index, this.rightToLeft);
                        break;
                    case 29:
                        this.rightToRight = ConstraintSet.lookupID(obtainStyledAttributes, index, this.rightToRight);
                        break;
                    case 30:
                        this.startMargin = obtainStyledAttributes.getDimensionPixelSize(index, this.startMargin);
                        break;
                    case 31:
                        this.startToEnd = ConstraintSet.lookupID(obtainStyledAttributes, index, this.startToEnd);
                        break;
                    case 32:
                        this.startToStart = ConstraintSet.lookupID(obtainStyledAttributes, index, this.startToStart);
                        break;
                    case 33:
                        this.topMargin = obtainStyledAttributes.getDimensionPixelSize(index, this.topMargin);
                        break;
                    case 34:
                        this.topToBottom = ConstraintSet.lookupID(obtainStyledAttributes, index, this.topToBottom);
                        break;
                    case 35:
                        this.topToTop = ConstraintSet.lookupID(obtainStyledAttributes, index, this.topToTop);
                        break;
                    case 36:
                        this.verticalBias = obtainStyledAttributes.getFloat(index, this.verticalBias);
                        break;
                    case 37:
                        this.horizontalWeight = obtainStyledAttributes.getFloat(index, this.horizontalWeight);
                        break;
                    case 38:
                        this.verticalWeight = obtainStyledAttributes.getFloat(index, this.verticalWeight);
                        break;
                    case 39:
                        this.horizontalChainStyle = obtainStyledAttributes.getInt(index, this.horizontalChainStyle);
                        break;
                    case 40:
                        this.verticalChainStyle = obtainStyledAttributes.getInt(index, this.verticalChainStyle);
                        break;
                    case 41:
                        ConstraintSet.parseDimensionConstraints(this, obtainStyledAttributes, index, 0);
                        break;
                    case 42:
                        ConstraintSet.parseDimensionConstraints(this, obtainStyledAttributes, index, 1);
                        break;
                    default:
                        switch (i2) {
                            case 61:
                                this.circleConstraint = ConstraintSet.lookupID(obtainStyledAttributes, index, this.circleConstraint);
                                break;
                            case 62:
                                this.circleRadius = obtainStyledAttributes.getDimensionPixelSize(index, this.circleRadius);
                                break;
                            case 63:
                                this.circleAngle = obtainStyledAttributes.getFloat(index, this.circleAngle);
                                break;
                            default:
                                switch (i2) {
                                    case 69:
                                        this.widthPercent = obtainStyledAttributes.getFloat(index, 1.0f);
                                        break;
                                    case 70:
                                        this.heightPercent = obtainStyledAttributes.getFloat(index, 1.0f);
                                        break;
                                    case 71:
                                        Log.e("ConstraintSet", "CURRENTLY UNSUPPORTED");
                                        break;
                                    case 72:
                                        this.mBarrierDirection = obtainStyledAttributes.getInt(index, this.mBarrierDirection);
                                        break;
                                    case 73:
                                        this.mBarrierMargin = obtainStyledAttributes.getDimensionPixelSize(index, this.mBarrierMargin);
                                        break;
                                    case 74:
                                        this.mReferenceIdString = obtainStyledAttributes.getString(index);
                                        break;
                                    case 75:
                                        this.mBarrierAllowsGoneWidgets = obtainStyledAttributes.getBoolean(index, this.mBarrierAllowsGoneWidgets);
                                        break;
                                    case 76:
                                        this.mWrapBehavior = obtainStyledAttributes.getInt(index, this.mWrapBehavior);
                                        break;
                                    case 77:
                                        this.baselineToTop = ConstraintSet.lookupID(obtainStyledAttributes, index, this.baselineToTop);
                                        break;
                                    case 78:
                                        this.baselineToBottom = ConstraintSet.lookupID(obtainStyledAttributes, index, this.baselineToBottom);
                                        break;
                                    case 79:
                                        this.goneBaselineMargin = obtainStyledAttributes.getDimensionPixelSize(index, this.goneBaselineMargin);
                                        break;
                                    case 80:
                                        this.baselineMargin = obtainStyledAttributes.getDimensionPixelSize(index, this.baselineMargin);
                                        break;
                                    case 81:
                                        this.widthDefault = obtainStyledAttributes.getInt(index, this.widthDefault);
                                        break;
                                    case 82:
                                        this.heightDefault = obtainStyledAttributes.getInt(index, this.heightDefault);
                                        break;
                                    case 83:
                                        this.heightMax = obtainStyledAttributes.getDimensionPixelSize(index, this.heightMax);
                                        break;
                                    case 84:
                                        this.widthMax = obtainStyledAttributes.getDimensionPixelSize(index, this.widthMax);
                                        break;
                                    case 85:
                                        this.heightMin = obtainStyledAttributes.getDimensionPixelSize(index, this.heightMin);
                                        break;
                                    case 86:
                                        this.widthMin = obtainStyledAttributes.getDimensionPixelSize(index, this.widthMin);
                                        break;
                                    case 87:
                                        this.constrainedWidth = obtainStyledAttributes.getBoolean(index, this.constrainedWidth);
                                        break;
                                    case 88:
                                        this.constrainedHeight = obtainStyledAttributes.getBoolean(index, this.constrainedHeight);
                                        break;
                                    case 89:
                                        this.mConstraintTag = obtainStyledAttributes.getString(index);
                                        break;
                                    case 90:
                                        this.guidelineUseRtl = obtainStyledAttributes.getBoolean(index, this.guidelineUseRtl);
                                        break;
                                    case 91:
                                        Log.w("ConstraintSet", "unused attribute 0x" + Integer.toHexString(index) + "   " + sparseIntArray.get(index));
                                        break;
                                    default:
                                        Log.w("ConstraintSet", "Unknown attribute 0x" + Integer.toHexString(index) + "   " + sparseIntArray.get(index));
                                        break;
                                }
                        }
                }
            }
            obtainStyledAttributes.recycle();
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Motion {
        public static final SparseIntArray sMapToConstant;
        public int mAnimateCircleAngleTo;
        public int mAnimateRelativeTo;
        public boolean mApply;
        public int mDrawPath;
        public float mMotionStagger;
        public int mPathMotionArc;
        public float mPathRotate;
        public int mQuantizeInterpolatorID;
        public String mQuantizeInterpolatorString;
        public int mQuantizeInterpolatorType;
        public float mQuantizeMotionPhase;
        public int mQuantizeMotionSteps;
        public String mTransitionEasing;

        static {
            SparseIntArray sparseIntArray = new SparseIntArray();
            sMapToConstant = sparseIntArray;
            sparseIntArray.append(3, 1);
            sparseIntArray.append(5, 2);
            sparseIntArray.append(9, 3);
            sparseIntArray.append(2, 4);
            sparseIntArray.append(1, 5);
            sparseIntArray.append(0, 6);
            sparseIntArray.append(4, 7);
            sparseIntArray.append(8, 8);
            sparseIntArray.append(7, 9);
            sparseIntArray.append(6, 10);
        }

        public final void copyFrom(Motion motion) {
            this.mApply = motion.mApply;
            this.mAnimateRelativeTo = motion.mAnimateRelativeTo;
            this.mTransitionEasing = motion.mTransitionEasing;
            this.mPathMotionArc = motion.mPathMotionArc;
            this.mDrawPath = motion.mDrawPath;
            this.mPathRotate = motion.mPathRotate;
            this.mMotionStagger = motion.mMotionStagger;
        }

        public final void fillFromAttributeList(Context context, AttributeSet attributeSet) {
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.Motion);
            this.mApply = true;
            int indexCount = obtainStyledAttributes.getIndexCount();
            for (int i = 0; i < indexCount; i++) {
                int index = obtainStyledAttributes.getIndex(i);
                switch (sMapToConstant.get(index)) {
                    case 1:
                        this.mPathRotate = obtainStyledAttributes.getFloat(index, this.mPathRotate);
                        break;
                    case 2:
                        this.mPathMotionArc = obtainStyledAttributes.getInt(index, this.mPathMotionArc);
                        break;
                    case 3:
                        if (obtainStyledAttributes.peekValue(index).type == 3) {
                            this.mTransitionEasing = obtainStyledAttributes.getString(index);
                            break;
                        } else {
                            this.mTransitionEasing = Easing.NAMED_EASING[obtainStyledAttributes.getInteger(index, 0)];
                            break;
                        }
                    case 4:
                        this.mDrawPath = obtainStyledAttributes.getInt(index, 0);
                        break;
                    case 5:
                        this.mAnimateRelativeTo = ConstraintSet.lookupID(obtainStyledAttributes, index, this.mAnimateRelativeTo);
                        break;
                    case 6:
                        this.mAnimateCircleAngleTo = obtainStyledAttributes.getInteger(index, this.mAnimateCircleAngleTo);
                        break;
                    case 7:
                        this.mMotionStagger = obtainStyledAttributes.getFloat(index, this.mMotionStagger);
                        break;
                    case 8:
                        this.mQuantizeMotionSteps = obtainStyledAttributes.getInteger(index, this.mQuantizeMotionSteps);
                        break;
                    case 9:
                        this.mQuantizeMotionPhase = obtainStyledAttributes.getFloat(index, this.mQuantizeMotionPhase);
                        break;
                    case 10:
                        int i2 = obtainStyledAttributes.peekValue(index).type;
                        if (i2 == 1) {
                            int resourceId = obtainStyledAttributes.getResourceId(index, -1);
                            this.mQuantizeInterpolatorID = resourceId;
                            if (resourceId != -1) {
                                this.mQuantizeInterpolatorType = -2;
                                break;
                            } else {
                                break;
                            }
                        } else if (i2 == 3) {
                            String string = obtainStyledAttributes.getString(index);
                            this.mQuantizeInterpolatorString = string;
                            if (string.indexOf("/") > 0) {
                                this.mQuantizeInterpolatorID = obtainStyledAttributes.getResourceId(index, -1);
                                this.mQuantizeInterpolatorType = -2;
                                break;
                            } else {
                                this.mQuantizeInterpolatorType = -1;
                                break;
                            }
                        } else {
                            this.mQuantizeInterpolatorType = obtainStyledAttributes.getInteger(index, this.mQuantizeInterpolatorID);
                            break;
                        }
                }
            }
            obtainStyledAttributes.recycle();
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class PropertySet {
        public float alpha;
        public boolean mApply;
        public float mProgress;
        public int mVisibilityMode;
        public int visibility;

        public final void fillFromAttributeList(Context context, AttributeSet attributeSet) {
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.PropertySet);
            this.mApply = true;
            int indexCount = obtainStyledAttributes.getIndexCount();
            for (int i = 0; i < indexCount; i++) {
                int index = obtainStyledAttributes.getIndex(i);
                if (index == 1) {
                    this.alpha = obtainStyledAttributes.getFloat(index, this.alpha);
                } else if (index == 0) {
                    int i2 = obtainStyledAttributes.getInt(index, this.visibility);
                    this.visibility = i2;
                    this.visibility = ConstraintSet.VISIBILITY_FLAGS[i2];
                } else if (index == 4) {
                    this.mVisibilityMode = obtainStyledAttributes.getInt(index, this.mVisibilityMode);
                } else if (index == 3) {
                    this.mProgress = obtainStyledAttributes.getFloat(index, this.mProgress);
                }
            }
            obtainStyledAttributes.recycle();
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Transform {
        public static final SparseIntArray sMapToConstant;
        public boolean applyElevation;
        public float elevation;
        public boolean mApply;
        public float rotation;
        public float rotationX;
        public float rotationY;
        public float scaleX;
        public float scaleY;
        public int transformPivotTarget;
        public float transformPivotX;
        public float transformPivotY;
        public float translationX;
        public float translationY;
        public float translationZ;

        static {
            SparseIntArray sparseIntArray = new SparseIntArray();
            sMapToConstant = sparseIntArray;
            sparseIntArray.append(6, 1);
            sparseIntArray.append(7, 2);
            sparseIntArray.append(8, 3);
            sparseIntArray.append(4, 4);
            sparseIntArray.append(5, 5);
            sparseIntArray.append(0, 6);
            sparseIntArray.append(1, 7);
            sparseIntArray.append(2, 8);
            sparseIntArray.append(3, 9);
            sparseIntArray.append(9, 10);
            sparseIntArray.append(10, 11);
            sparseIntArray.append(11, 12);
        }

        public final void copyFrom(Transform transform) {
            this.mApply = transform.mApply;
            this.rotation = transform.rotation;
            this.rotationX = transform.rotationX;
            this.rotationY = transform.rotationY;
            this.scaleX = transform.scaleX;
            this.scaleY = transform.scaleY;
            this.transformPivotX = transform.transformPivotX;
            this.transformPivotY = transform.transformPivotY;
            this.transformPivotTarget = transform.transformPivotTarget;
            this.translationX = transform.translationX;
            this.translationY = transform.translationY;
            this.translationZ = transform.translationZ;
            this.applyElevation = transform.applyElevation;
            this.elevation = transform.elevation;
        }

        public final void fillFromAttributeList(Context context, AttributeSet attributeSet) {
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.Transform);
            this.mApply = true;
            int indexCount = obtainStyledAttributes.getIndexCount();
            for (int i = 0; i < indexCount; i++) {
                int index = obtainStyledAttributes.getIndex(i);
                switch (sMapToConstant.get(index)) {
                    case 1:
                        this.rotation = obtainStyledAttributes.getFloat(index, this.rotation);
                        break;
                    case 2:
                        this.rotationX = obtainStyledAttributes.getFloat(index, this.rotationX);
                        break;
                    case 3:
                        this.rotationY = obtainStyledAttributes.getFloat(index, this.rotationY);
                        break;
                    case 4:
                        this.scaleX = obtainStyledAttributes.getFloat(index, this.scaleX);
                        break;
                    case 5:
                        this.scaleY = obtainStyledAttributes.getFloat(index, this.scaleY);
                        break;
                    case 6:
                        this.transformPivotX = obtainStyledAttributes.getDimension(index, this.transformPivotX);
                        break;
                    case 7:
                        this.transformPivotY = obtainStyledAttributes.getDimension(index, this.transformPivotY);
                        break;
                    case 8:
                        this.translationX = obtainStyledAttributes.getDimension(index, this.translationX);
                        break;
                    case 9:
                        this.translationY = obtainStyledAttributes.getDimension(index, this.translationY);
                        break;
                    case 10:
                        this.translationZ = obtainStyledAttributes.getDimension(index, this.translationZ);
                        break;
                    case ViewNode.TRANSLATIONX_FIELD_NUMBER /* 11 */:
                        this.applyElevation = true;
                        this.elevation = obtainStyledAttributes.getDimension(index, this.elevation);
                        break;
                    case ViewNode.TRANSLATIONY_FIELD_NUMBER /* 12 */:
                        this.transformPivotTarget = ConstraintSet.lookupID(obtainStyledAttributes, index, this.transformPivotTarget);
                        break;
                }
            }
            obtainStyledAttributes.recycle();
        }
    }

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sMapToConstant = sparseIntArray;
        SparseIntArray sparseIntArray2 = new SparseIntArray();
        sOverrideMapToConstant = sparseIntArray2;
        sparseIntArray.append(82, 25);
        sparseIntArray.append(83, 26);
        sparseIntArray.append(85, 29);
        sparseIntArray.append(86, 30);
        sparseIntArray.append(92, 36);
        sparseIntArray.append(91, 35);
        sparseIntArray.append(63, 4);
        sparseIntArray.append(62, 3);
        sparseIntArray.append(58, 1);
        sparseIntArray.append(60, 91);
        sparseIntArray.append(59, 92);
        sparseIntArray.append(101, 6);
        sparseIntArray.append(102, 7);
        sparseIntArray.append(70, 17);
        sparseIntArray.append(71, 18);
        sparseIntArray.append(72, 19);
        sparseIntArray.append(54, 99);
        sparseIntArray.append(0, 27);
        sparseIntArray.append(87, 32);
        sparseIntArray.append(88, 33);
        sparseIntArray.append(69, 10);
        sparseIntArray.append(68, 9);
        sparseIntArray.append(106, 13);
        sparseIntArray.append(109, 16);
        sparseIntArray.append(107, 14);
        sparseIntArray.append(104, 11);
        sparseIntArray.append(108, 15);
        sparseIntArray.append(105, 12);
        sparseIntArray.append(95, 40);
        sparseIntArray.append(80, 39);
        sparseIntArray.append(79, 41);
        sparseIntArray.append(94, 42);
        sparseIntArray.append(78, 20);
        sparseIntArray.append(93, 37);
        sparseIntArray.append(67, 5);
        sparseIntArray.append(81, 87);
        sparseIntArray.append(90, 87);
        sparseIntArray.append(84, 87);
        sparseIntArray.append(61, 87);
        sparseIntArray.append(57, 87);
        sparseIntArray.append(5, 24);
        sparseIntArray.append(7, 28);
        sparseIntArray.append(23, 31);
        sparseIntArray.append(24, 8);
        sparseIntArray.append(6, 34);
        sparseIntArray.append(8, 2);
        sparseIntArray.append(3, 23);
        sparseIntArray.append(4, 21);
        sparseIntArray.append(96, 95);
        sparseIntArray.append(73, 96);
        sparseIntArray.append(2, 22);
        sparseIntArray.append(13, 43);
        sparseIntArray.append(26, 44);
        sparseIntArray.append(21, 45);
        sparseIntArray.append(22, 46);
        sparseIntArray.append(20, 60);
        sparseIntArray.append(18, 47);
        sparseIntArray.append(19, 48);
        sparseIntArray.append(14, 49);
        sparseIntArray.append(15, 50);
        sparseIntArray.append(16, 51);
        sparseIntArray.append(17, 52);
        sparseIntArray.append(25, 53);
        sparseIntArray.append(97, 54);
        sparseIntArray.append(74, 55);
        sparseIntArray.append(98, 56);
        sparseIntArray.append(75, 57);
        sparseIntArray.append(99, 58);
        sparseIntArray.append(76, 59);
        sparseIntArray.append(64, 61);
        sparseIntArray.append(66, 62);
        sparseIntArray.append(65, 63);
        sparseIntArray.append(28, 64);
        sparseIntArray.append(121, 65);
        sparseIntArray.append(35, 66);
        sparseIntArray.append(122, 67);
        sparseIntArray.append(113, 79);
        sparseIntArray.append(1, 38);
        sparseIntArray.append(112, 68);
        sparseIntArray.append(100, 69);
        sparseIntArray.append(77, 70);
        sparseIntArray.append(111, 97);
        sparseIntArray.append(32, 71);
        sparseIntArray.append(30, 72);
        sparseIntArray.append(31, 73);
        sparseIntArray.append(33, 74);
        sparseIntArray.append(29, 75);
        sparseIntArray.append(114, 76);
        sparseIntArray.append(89, 77);
        sparseIntArray.append(123, 78);
        sparseIntArray.append(56, 80);
        sparseIntArray.append(55, 81);
        sparseIntArray.append(116, 82);
        sparseIntArray.append(120, 83);
        sparseIntArray.append(119, 84);
        sparseIntArray.append(118, 85);
        sparseIntArray.append(117, 86);
        sparseIntArray2.append(85, 6);
        sparseIntArray2.append(85, 7);
        sparseIntArray2.append(0, 27);
        sparseIntArray2.append(89, 13);
        sparseIntArray2.append(92, 16);
        sparseIntArray2.append(90, 14);
        sparseIntArray2.append(87, 11);
        sparseIntArray2.append(91, 15);
        sparseIntArray2.append(88, 12);
        sparseIntArray2.append(78, 40);
        sparseIntArray2.append(71, 39);
        sparseIntArray2.append(70, 41);
        sparseIntArray2.append(77, 42);
        sparseIntArray2.append(69, 20);
        sparseIntArray2.append(76, 37);
        sparseIntArray2.append(60, 5);
        sparseIntArray2.append(72, 87);
        sparseIntArray2.append(75, 87);
        sparseIntArray2.append(73, 87);
        sparseIntArray2.append(57, 87);
        sparseIntArray2.append(56, 87);
        sparseIntArray2.append(5, 24);
        sparseIntArray2.append(7, 28);
        sparseIntArray2.append(23, 31);
        sparseIntArray2.append(24, 8);
        sparseIntArray2.append(6, 34);
        sparseIntArray2.append(8, 2);
        sparseIntArray2.append(3, 23);
        sparseIntArray2.append(4, 21);
        sparseIntArray2.append(79, 95);
        sparseIntArray2.append(64, 96);
        sparseIntArray2.append(2, 22);
        sparseIntArray2.append(13, 43);
        sparseIntArray2.append(26, 44);
        sparseIntArray2.append(21, 45);
        sparseIntArray2.append(22, 46);
        sparseIntArray2.append(20, 60);
        sparseIntArray2.append(18, 47);
        sparseIntArray2.append(19, 48);
        sparseIntArray2.append(14, 49);
        sparseIntArray2.append(15, 50);
        sparseIntArray2.append(16, 51);
        sparseIntArray2.append(17, 52);
        sparseIntArray2.append(25, 53);
        sparseIntArray2.append(80, 54);
        sparseIntArray2.append(65, 55);
        sparseIntArray2.append(81, 56);
        sparseIntArray2.append(66, 57);
        sparseIntArray2.append(82, 58);
        sparseIntArray2.append(67, 59);
        sparseIntArray2.append(59, 62);
        sparseIntArray2.append(58, 63);
        sparseIntArray2.append(28, 64);
        sparseIntArray2.append(105, 65);
        sparseIntArray2.append(34, 66);
        sparseIntArray2.append(106, 67);
        sparseIntArray2.append(96, 79);
        sparseIntArray2.append(1, 38);
        sparseIntArray2.append(97, 98);
        sparseIntArray2.append(95, 68);
        sparseIntArray2.append(83, 69);
        sparseIntArray2.append(68, 70);
        sparseIntArray2.append(32, 71);
        sparseIntArray2.append(30, 72);
        sparseIntArray2.append(31, 73);
        sparseIntArray2.append(33, 74);
        sparseIntArray2.append(29, 75);
        sparseIntArray2.append(98, 76);
        sparseIntArray2.append(74, 77);
        sparseIntArray2.append(107, 78);
        sparseIntArray2.append(55, 80);
        sparseIntArray2.append(54, 81);
        sparseIntArray2.append(100, 82);
        sparseIntArray2.append(104, 83);
        sparseIntArray2.append(103, 84);
        sparseIntArray2.append(102, 85);
        sparseIntArray2.append(101, 86);
        sparseIntArray2.append(94, 97);
    }

    public static Constraint buildDelta(Context context, XmlPullParser xmlPullParser) {
        AttributeSet asAttributeSet = Xml.asAttributeSet(xmlPullParser);
        Constraint constraint = new Constraint();
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(asAttributeSet, R$styleable.ConstraintOverride);
        populateOverride(constraint, obtainStyledAttributes);
        obtainStyledAttributes.recycle();
        return constraint;
    }

    public static int[] convertReferenceString(Barrier barrier, String str) {
        int i;
        HashMap hashMap;
        String[] split = str.split(",");
        Context context = barrier.getContext();
        int[] iArr = new int[split.length];
        int i2 = 0;
        int i3 = 0;
        while (i2 < split.length) {
            String trim = split[i2].trim();
            Object obj = null;
            try {
                i = R$id.class.getField(trim).getInt(null);
            } catch (Exception unused) {
                i = 0;
            }
            if (i == 0) {
                i = context.getResources().getIdentifier(trim, "id", context.getPackageName());
            }
            if (i == 0 && barrier.isInEditMode() && (barrier.getParent() instanceof ConstraintLayout)) {
                ConstraintLayout constraintLayout = (ConstraintLayout) barrier.getParent();
                constraintLayout.getClass();
                if (trim != null && (hashMap = constraintLayout.mDesignIds) != null && hashMap.containsKey(trim)) {
                    obj = constraintLayout.mDesignIds.get(trim);
                }
                if (obj != null && (obj instanceof Integer)) {
                    i = ((Integer) obj).intValue();
                }
            }
            iArr[i3] = i;
            i2++;
            i3++;
        }
        return i3 != split.length ? Arrays.copyOf(iArr, i3) : iArr;
    }

    public static Constraint fillFromAttributeList(Context context, AttributeSet attributeSet, boolean z) {
        Constraint constraint = new Constraint();
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, z ? R$styleable.ConstraintOverride : R$styleable.Constraint);
        if (z) {
            populateOverride(constraint, obtainStyledAttributes);
        } else {
            int indexCount = obtainStyledAttributes.getIndexCount();
            int i = 0;
            while (true) {
                Layout layout = constraint.layout;
                if (i < indexCount) {
                    int index = obtainStyledAttributes.getIndex(i);
                    PropertySet propertySet = constraint.propertySet;
                    Transform transform = constraint.transform;
                    Motion motion = constraint.motion;
                    if (index != 1 && 23 != index && 24 != index) {
                        motion.mApply = true;
                        layout.mApply = true;
                        propertySet.mApply = true;
                        transform.mApply = true;
                    }
                    SparseIntArray sparseIntArray = sMapToConstant;
                    switch (sparseIntArray.get(index)) {
                        case 1:
                            layout.baselineToBaseline = lookupID(obtainStyledAttributes, index, layout.baselineToBaseline);
                            break;
                        case 2:
                            layout.bottomMargin = obtainStyledAttributes.getDimensionPixelSize(index, layout.bottomMargin);
                            break;
                        case 3:
                            layout.bottomToBottom = lookupID(obtainStyledAttributes, index, layout.bottomToBottom);
                            break;
                        case 4:
                            layout.bottomToTop = lookupID(obtainStyledAttributes, index, layout.bottomToTop);
                            break;
                        case 5:
                            layout.dimensionRatio = obtainStyledAttributes.getString(index);
                            break;
                        case 6:
                            layout.editorAbsoluteX = obtainStyledAttributes.getDimensionPixelOffset(index, layout.editorAbsoluteX);
                            break;
                        case 7:
                            layout.editorAbsoluteY = obtainStyledAttributes.getDimensionPixelOffset(index, layout.editorAbsoluteY);
                            break;
                        case 8:
                            layout.endMargin = obtainStyledAttributes.getDimensionPixelSize(index, layout.endMargin);
                            break;
                        case 9:
                            layout.endToEnd = lookupID(obtainStyledAttributes, index, layout.endToEnd);
                            break;
                        case 10:
                            layout.endToStart = lookupID(obtainStyledAttributes, index, layout.endToStart);
                            break;
                        case ViewNode.TRANSLATIONX_FIELD_NUMBER /* 11 */:
                            layout.goneBottomMargin = obtainStyledAttributes.getDimensionPixelSize(index, layout.goneBottomMargin);
                            break;
                        case ViewNode.TRANSLATIONY_FIELD_NUMBER /* 12 */:
                            layout.goneEndMargin = obtainStyledAttributes.getDimensionPixelSize(index, layout.goneEndMargin);
                            break;
                        case ViewNode.SCALEX_FIELD_NUMBER /* 13 */:
                            layout.goneLeftMargin = obtainStyledAttributes.getDimensionPixelSize(index, layout.goneLeftMargin);
                            break;
                        case ViewNode.SCALEY_FIELD_NUMBER /* 14 */:
                            layout.goneRightMargin = obtainStyledAttributes.getDimensionPixelSize(index, layout.goneRightMargin);
                            break;
                        case 15:
                            layout.goneStartMargin = obtainStyledAttributes.getDimensionPixelSize(index, layout.goneStartMargin);
                            break;
                        case 16:
                            layout.goneTopMargin = obtainStyledAttributes.getDimensionPixelSize(index, layout.goneTopMargin);
                            break;
                        case ViewNode.CLIPCHILDREN_FIELD_NUMBER /* 17 */:
                            layout.guideBegin = obtainStyledAttributes.getDimensionPixelOffset(index, layout.guideBegin);
                            break;
                        case ViewNode.VISIBILITY_FIELD_NUMBER /* 18 */:
                            layout.guideEnd = obtainStyledAttributes.getDimensionPixelOffset(index, layout.guideEnd);
                            break;
                        case ViewNode.ELEVATION_FIELD_NUMBER /* 19 */:
                            layout.guidePercent = obtainStyledAttributes.getFloat(index, layout.guidePercent);
                            break;
                        case 20:
                            layout.horizontalBias = obtainStyledAttributes.getFloat(index, layout.horizontalBias);
                            break;
                        case 21:
                            layout.mHeight = obtainStyledAttributes.getLayoutDimension(index, layout.mHeight);
                            break;
                        case 22:
                            int i2 = obtainStyledAttributes.getInt(index, propertySet.visibility);
                            propertySet.visibility = i2;
                            propertySet.visibility = VISIBILITY_FLAGS[i2];
                            break;
                        case 23:
                            layout.mWidth = obtainStyledAttributes.getLayoutDimension(index, layout.mWidth);
                            break;
                        case 24:
                            layout.leftMargin = obtainStyledAttributes.getDimensionPixelSize(index, layout.leftMargin);
                            break;
                        case 25:
                            layout.leftToLeft = lookupID(obtainStyledAttributes, index, layout.leftToLeft);
                            break;
                        case 26:
                            layout.leftToRight = lookupID(obtainStyledAttributes, index, layout.leftToRight);
                            break;
                        case 27:
                            layout.orientation = obtainStyledAttributes.getInt(index, layout.orientation);
                            break;
                        case 28:
                            layout.rightMargin = obtainStyledAttributes.getDimensionPixelSize(index, layout.rightMargin);
                            break;
                        case 29:
                            layout.rightToLeft = lookupID(obtainStyledAttributes, index, layout.rightToLeft);
                            break;
                        case 30:
                            layout.rightToRight = lookupID(obtainStyledAttributes, index, layout.rightToRight);
                            break;
                        case 31:
                            layout.startMargin = obtainStyledAttributes.getDimensionPixelSize(index, layout.startMargin);
                            break;
                        case 32:
                            layout.startToEnd = lookupID(obtainStyledAttributes, index, layout.startToEnd);
                            break;
                        case 33:
                            layout.startToStart = lookupID(obtainStyledAttributes, index, layout.startToStart);
                            break;
                        case 34:
                            layout.topMargin = obtainStyledAttributes.getDimensionPixelSize(index, layout.topMargin);
                            break;
                        case 35:
                            layout.topToBottom = lookupID(obtainStyledAttributes, index, layout.topToBottom);
                            break;
                        case 36:
                            layout.topToTop = lookupID(obtainStyledAttributes, index, layout.topToTop);
                            break;
                        case 37:
                            layout.verticalBias = obtainStyledAttributes.getFloat(index, layout.verticalBias);
                            break;
                        case 38:
                            constraint.mViewId = obtainStyledAttributes.getResourceId(index, constraint.mViewId);
                            break;
                        case 39:
                            layout.horizontalWeight = obtainStyledAttributes.getFloat(index, layout.horizontalWeight);
                            break;
                        case 40:
                            layout.verticalWeight = obtainStyledAttributes.getFloat(index, layout.verticalWeight);
                            break;
                        case 41:
                            layout.horizontalChainStyle = obtainStyledAttributes.getInt(index, layout.horizontalChainStyle);
                            break;
                        case 42:
                            layout.verticalChainStyle = obtainStyledAttributes.getInt(index, layout.verticalChainStyle);
                            break;
                        case 43:
                            propertySet.alpha = obtainStyledAttributes.getFloat(index, propertySet.alpha);
                            break;
                        case 44:
                            transform.applyElevation = true;
                            transform.elevation = obtainStyledAttributes.getDimension(index, transform.elevation);
                            break;
                        case 45:
                            transform.rotationX = obtainStyledAttributes.getFloat(index, transform.rotationX);
                            break;
                        case 46:
                            transform.rotationY = obtainStyledAttributes.getFloat(index, transform.rotationY);
                            break;
                        case 47:
                            transform.scaleX = obtainStyledAttributes.getFloat(index, transform.scaleX);
                            break;
                        case 48:
                            transform.scaleY = obtainStyledAttributes.getFloat(index, transform.scaleY);
                            break;
                        case 49:
                            transform.transformPivotX = obtainStyledAttributes.getDimension(index, transform.transformPivotX);
                            break;
                        case 50:
                            transform.transformPivotY = obtainStyledAttributes.getDimension(index, transform.transformPivotY);
                            break;
                        case 51:
                            transform.translationX = obtainStyledAttributes.getDimension(index, transform.translationX);
                            break;
                        case 52:
                            transform.translationY = obtainStyledAttributes.getDimension(index, transform.translationY);
                            break;
                        case 53:
                            transform.translationZ = obtainStyledAttributes.getDimension(index, transform.translationZ);
                            break;
                        case 54:
                            layout.widthDefault = obtainStyledAttributes.getInt(index, layout.widthDefault);
                            break;
                        case 55:
                            layout.heightDefault = obtainStyledAttributes.getInt(index, layout.heightDefault);
                            break;
                        case 56:
                            layout.widthMax = obtainStyledAttributes.getDimensionPixelSize(index, layout.widthMax);
                            break;
                        case 57:
                            layout.heightMax = obtainStyledAttributes.getDimensionPixelSize(index, layout.heightMax);
                            break;
                        case 58:
                            layout.widthMin = obtainStyledAttributes.getDimensionPixelSize(index, layout.widthMin);
                            break;
                        case 59:
                            layout.heightMin = obtainStyledAttributes.getDimensionPixelSize(index, layout.heightMin);
                            break;
                        case 60:
                            transform.rotation = obtainStyledAttributes.getFloat(index, transform.rotation);
                            break;
                        case 61:
                            layout.circleConstraint = lookupID(obtainStyledAttributes, index, layout.circleConstraint);
                            break;
                        case 62:
                            layout.circleRadius = obtainStyledAttributes.getDimensionPixelSize(index, layout.circleRadius);
                            break;
                        case 63:
                            layout.circleAngle = obtainStyledAttributes.getFloat(index, layout.circleAngle);
                            break;
                        case 64:
                            motion.mAnimateRelativeTo = lookupID(obtainStyledAttributes, index, motion.mAnimateRelativeTo);
                            break;
                        case 65:
                            if (obtainStyledAttributes.peekValue(index).type != 3) {
                                motion.mTransitionEasing = Easing.NAMED_EASING[obtainStyledAttributes.getInteger(index, 0)];
                                break;
                            } else {
                                motion.mTransitionEasing = obtainStyledAttributes.getString(index);
                                break;
                            }
                        case 66:
                            motion.mDrawPath = obtainStyledAttributes.getInt(index, 0);
                            break;
                        case 67:
                            motion.mPathRotate = obtainStyledAttributes.getFloat(index, motion.mPathRotate);
                            break;
                        case 68:
                            propertySet.mProgress = obtainStyledAttributes.getFloat(index, propertySet.mProgress);
                            break;
                        case 69:
                            layout.widthPercent = obtainStyledAttributes.getFloat(index, 1.0f);
                            break;
                        case 70:
                            layout.heightPercent = obtainStyledAttributes.getFloat(index, 1.0f);
                            break;
                        case 71:
                            Log.e("ConstraintSet", "CURRENTLY UNSUPPORTED");
                            break;
                        case 72:
                            layout.mBarrierDirection = obtainStyledAttributes.getInt(index, layout.mBarrierDirection);
                            break;
                        case 73:
                            layout.mBarrierMargin = obtainStyledAttributes.getDimensionPixelSize(index, layout.mBarrierMargin);
                            break;
                        case 74:
                            layout.mReferenceIdString = obtainStyledAttributes.getString(index);
                            break;
                        case 75:
                            layout.mBarrierAllowsGoneWidgets = obtainStyledAttributes.getBoolean(index, layout.mBarrierAllowsGoneWidgets);
                            break;
                        case 76:
                            motion.mPathMotionArc = obtainStyledAttributes.getInt(index, motion.mPathMotionArc);
                            break;
                        case 77:
                            layout.mConstraintTag = obtainStyledAttributes.getString(index);
                            break;
                        case 78:
                            propertySet.mVisibilityMode = obtainStyledAttributes.getInt(index, propertySet.mVisibilityMode);
                            break;
                        case 79:
                            motion.mMotionStagger = obtainStyledAttributes.getFloat(index, motion.mMotionStagger);
                            break;
                        case 80:
                            layout.constrainedWidth = obtainStyledAttributes.getBoolean(index, layout.constrainedWidth);
                            break;
                        case 81:
                            layout.constrainedHeight = obtainStyledAttributes.getBoolean(index, layout.constrainedHeight);
                            break;
                        case 82:
                            motion.mAnimateCircleAngleTo = obtainStyledAttributes.getInteger(index, motion.mAnimateCircleAngleTo);
                            break;
                        case 83:
                            transform.transformPivotTarget = lookupID(obtainStyledAttributes, index, transform.transformPivotTarget);
                            break;
                        case 84:
                            motion.mQuantizeMotionSteps = obtainStyledAttributes.getInteger(index, motion.mQuantizeMotionSteps);
                            break;
                        case 85:
                            motion.mQuantizeMotionPhase = obtainStyledAttributes.getFloat(index, motion.mQuantizeMotionPhase);
                            break;
                        case 86:
                            int i3 = obtainStyledAttributes.peekValue(index).type;
                            if (i3 != 1) {
                                if (i3 != 3) {
                                    motion.mQuantizeInterpolatorType = obtainStyledAttributes.getInteger(index, motion.mQuantizeInterpolatorID);
                                    break;
                                } else {
                                    String string = obtainStyledAttributes.getString(index);
                                    motion.mQuantizeInterpolatorString = string;
                                    if (string.indexOf("/") <= 0) {
                                        motion.mQuantizeInterpolatorType = -1;
                                        break;
                                    } else {
                                        motion.mQuantizeInterpolatorID = obtainStyledAttributes.getResourceId(index, -1);
                                        motion.mQuantizeInterpolatorType = -2;
                                        break;
                                    }
                                }
                            } else {
                                int resourceId = obtainStyledAttributes.getResourceId(index, -1);
                                motion.mQuantizeInterpolatorID = resourceId;
                                if (resourceId == -1) {
                                    break;
                                } else {
                                    motion.mQuantizeInterpolatorType = -2;
                                    break;
                                }
                            }
                        case 87:
                            Log.w("ConstraintSet", "unused attribute 0x" + Integer.toHexString(index) + "   " + sparseIntArray.get(index));
                            break;
                        case 88:
                        case 89:
                        case 90:
                        default:
                            Log.w("ConstraintSet", "Unknown attribute 0x" + Integer.toHexString(index) + "   " + sparseIntArray.get(index));
                            break;
                        case 91:
                            layout.baselineToTop = lookupID(obtainStyledAttributes, index, layout.baselineToTop);
                            break;
                        case 92:
                            layout.baselineToBottom = lookupID(obtainStyledAttributes, index, layout.baselineToBottom);
                            break;
                        case 93:
                            layout.baselineMargin = obtainStyledAttributes.getDimensionPixelSize(index, layout.baselineMargin);
                            break;
                        case 94:
                            layout.goneBaselineMargin = obtainStyledAttributes.getDimensionPixelSize(index, layout.goneBaselineMargin);
                            break;
                        case 95:
                            parseDimensionConstraints(layout, obtainStyledAttributes, index, 0);
                            break;
                        case 96:
                            parseDimensionConstraints(layout, obtainStyledAttributes, index, 1);
                            break;
                        case 97:
                            layout.mWrapBehavior = obtainStyledAttributes.getInt(index, layout.mWrapBehavior);
                            break;
                    }
                    i++;
                } else if (layout.mReferenceIdString != null) {
                    layout.mReferenceIds = null;
                }
            }
        }
        obtainStyledAttributes.recycle();
        return constraint;
    }

    public static int lookupID(TypedArray typedArray, int i, int i2) {
        int resourceId = typedArray.getResourceId(i, i2);
        return resourceId == -1 ? typedArray.getInt(i, -1) : resourceId;
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x0035  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0043  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static void parseDimensionConstraints(java.lang.Object r7, android.content.res.TypedArray r8, int r9, int r10) {
        /*
            Method dump skipped, instructions count: 376
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.widget.ConstraintSet.parseDimensionConstraints(java.lang.Object, android.content.res.TypedArray, int, int):void");
    }

    public static void parseDimensionRatioString(ConstraintLayout.LayoutParams layoutParams, String str) {
        if (str != null) {
            int length = str.length();
            int indexOf = str.indexOf(44);
            char c = 65535;
            int i = 0;
            if (indexOf > 0 && indexOf < length - 1) {
                String substring = str.substring(0, indexOf);
                if (substring.equalsIgnoreCase("W")) {
                    c = 0;
                } else if (substring.equalsIgnoreCase("H")) {
                    c = 1;
                }
                i = indexOf + 1;
            }
            int indexOf2 = str.indexOf(58);
            try {
                if (indexOf2 < 0 || indexOf2 >= length - 1) {
                    String substring2 = str.substring(i);
                    if (substring2.length() > 0) {
                        Float.parseFloat(substring2);
                    }
                } else {
                    String substring3 = str.substring(i, indexOf2);
                    String substring4 = str.substring(indexOf2 + 1);
                    if (substring3.length() > 0 && substring4.length() > 0) {
                        float parseFloat = Float.parseFloat(substring3);
                        float parseFloat2 = Float.parseFloat(substring4);
                        if (parseFloat > 0.0f && parseFloat2 > 0.0f) {
                            if (c == 1) {
                                Math.abs(parseFloat2 / parseFloat);
                            } else {
                                Math.abs(parseFloat / parseFloat2);
                            }
                        }
                    }
                }
            } catch (NumberFormatException unused) {
            }
        }
        layoutParams.dimensionRatio = str;
    }

    public static void populateOverride(Constraint constraint, TypedArray typedArray) {
        char c;
        int indexCount = typedArray.getIndexCount();
        Constraint.Delta delta = new Constraint.Delta();
        delta.mTypeInt = new int[10];
        delta.mValueInt = new int[10];
        delta.mCountInt = 0;
        delta.mTypeFloat = new int[10];
        delta.mValueFloat = new float[10];
        delta.mCountFloat = 0;
        delta.mTypeString = new int[5];
        delta.mValueString = new String[5];
        delta.mCountString = 0;
        delta.mTypeBoolean = new int[4];
        delta.mValueBoolean = new boolean[4];
        delta.mCountBoolean = 0;
        constraint.mDelta = delta;
        Motion motion = constraint.motion;
        motion.mApply = false;
        Layout layout = constraint.layout;
        layout.mApply = false;
        PropertySet propertySet = constraint.propertySet;
        propertySet.mApply = false;
        Transform transform = constraint.transform;
        transform.mApply = false;
        for (int i = 0; i < indexCount; i++) {
            int index = typedArray.getIndex(i);
            switch (sOverrideMapToConstant.get(index)) {
                case 2:
                    c = 5;
                    delta.add(2, typedArray.getDimensionPixelSize(index, layout.bottomMargin));
                    break;
                case 3:
                case 4:
                case 9:
                case 10:
                case 25:
                case 26:
                case 29:
                case 30:
                case 32:
                case 33:
                case 35:
                case 36:
                case 61:
                case 88:
                case 89:
                case 90:
                case 91:
                case 92:
                default:
                    Log.w("ConstraintSet", "Unknown attribute 0x" + Integer.toHexString(index) + "   " + sMapToConstant.get(index));
                    c = 5;
                    break;
                case 5:
                    c = 5;
                    delta.add(5, typedArray.getString(index));
                    break;
                case 6:
                    delta.add(6, typedArray.getDimensionPixelOffset(index, layout.editorAbsoluteX));
                    c = 5;
                    break;
                case 7:
                    delta.add(7, typedArray.getDimensionPixelOffset(index, layout.editorAbsoluteY));
                    c = 5;
                    break;
                case 8:
                    delta.add(8, typedArray.getDimensionPixelSize(index, layout.endMargin));
                    c = 5;
                    break;
                case ViewNode.TRANSLATIONX_FIELD_NUMBER /* 11 */:
                    delta.add(11, typedArray.getDimensionPixelSize(index, layout.goneBottomMargin));
                    c = 5;
                    break;
                case ViewNode.TRANSLATIONY_FIELD_NUMBER /* 12 */:
                    delta.add(12, typedArray.getDimensionPixelSize(index, layout.goneEndMargin));
                    c = 5;
                    break;
                case ViewNode.SCALEX_FIELD_NUMBER /* 13 */:
                    delta.add(13, typedArray.getDimensionPixelSize(index, layout.goneLeftMargin));
                    c = 5;
                    break;
                case ViewNode.SCALEY_FIELD_NUMBER /* 14 */:
                    delta.add(14, typedArray.getDimensionPixelSize(index, layout.goneRightMargin));
                    c = 5;
                    break;
                case 15:
                    delta.add(15, typedArray.getDimensionPixelSize(index, layout.goneStartMargin));
                    c = 5;
                    break;
                case 16:
                    delta.add(16, typedArray.getDimensionPixelSize(index, layout.goneTopMargin));
                    c = 5;
                    break;
                case ViewNode.CLIPCHILDREN_FIELD_NUMBER /* 17 */:
                    delta.add(17, typedArray.getDimensionPixelOffset(index, layout.guideBegin));
                    c = 5;
                    break;
                case ViewNode.VISIBILITY_FIELD_NUMBER /* 18 */:
                    delta.add(18, typedArray.getDimensionPixelOffset(index, layout.guideEnd));
                    c = 5;
                    break;
                case ViewNode.ELEVATION_FIELD_NUMBER /* 19 */:
                    delta.add(19, typedArray.getFloat(index, layout.guidePercent));
                    c = 5;
                    break;
                case 20:
                    delta.add(20, typedArray.getFloat(index, layout.horizontalBias));
                    c = 5;
                    break;
                case 21:
                    delta.add(21, typedArray.getLayoutDimension(index, layout.mHeight));
                    c = 5;
                    break;
                case 22:
                    delta.add(22, VISIBILITY_FLAGS[typedArray.getInt(index, propertySet.visibility)]);
                    c = 5;
                    break;
                case 23:
                    delta.add(23, typedArray.getLayoutDimension(index, layout.mWidth));
                    c = 5;
                    break;
                case 24:
                    delta.add(24, typedArray.getDimensionPixelSize(index, layout.leftMargin));
                    c = 5;
                    break;
                case 27:
                    delta.add(27, typedArray.getInt(index, layout.orientation));
                    c = 5;
                    break;
                case 28:
                    delta.add(28, typedArray.getDimensionPixelSize(index, layout.rightMargin));
                    c = 5;
                    break;
                case 31:
                    delta.add(31, typedArray.getDimensionPixelSize(index, layout.startMargin));
                    c = 5;
                    break;
                case 34:
                    delta.add(34, typedArray.getDimensionPixelSize(index, layout.topMargin));
                    c = 5;
                    break;
                case 37:
                    delta.add(37, typedArray.getFloat(index, layout.verticalBias));
                    c = 5;
                    break;
                case 38:
                    int resourceId = typedArray.getResourceId(index, constraint.mViewId);
                    constraint.mViewId = resourceId;
                    delta.add(38, resourceId);
                    c = 5;
                    break;
                case 39:
                    delta.add(39, typedArray.getFloat(index, layout.horizontalWeight));
                    c = 5;
                    break;
                case 40:
                    delta.add(40, typedArray.getFloat(index, layout.verticalWeight));
                    c = 5;
                    break;
                case 41:
                    delta.add(41, typedArray.getInt(index, layout.horizontalChainStyle));
                    c = 5;
                    break;
                case 42:
                    delta.add(42, typedArray.getInt(index, layout.verticalChainStyle));
                    c = 5;
                    break;
                case 43:
                    delta.add(43, typedArray.getFloat(index, propertySet.alpha));
                    c = 5;
                    break;
                case 44:
                    delta.add(44, true);
                    delta.add(44, typedArray.getDimension(index, transform.elevation));
                    c = 5;
                    break;
                case 45:
                    delta.add(45, typedArray.getFloat(index, transform.rotationX));
                    c = 5;
                    break;
                case 46:
                    delta.add(46, typedArray.getFloat(index, transform.rotationY));
                    c = 5;
                    break;
                case 47:
                    delta.add(47, typedArray.getFloat(index, transform.scaleX));
                    c = 5;
                    break;
                case 48:
                    delta.add(48, typedArray.getFloat(index, transform.scaleY));
                    c = 5;
                    break;
                case 49:
                    delta.add(49, typedArray.getDimension(index, transform.transformPivotX));
                    c = 5;
                    break;
                case 50:
                    delta.add(50, typedArray.getDimension(index, transform.transformPivotY));
                    c = 5;
                    break;
                case 51:
                    delta.add(51, typedArray.getDimension(index, transform.translationX));
                    c = 5;
                    break;
                case 52:
                    delta.add(52, typedArray.getDimension(index, transform.translationY));
                    c = 5;
                    break;
                case 53:
                    delta.add(53, typedArray.getDimension(index, transform.translationZ));
                    c = 5;
                    break;
                case 54:
                    delta.add(54, typedArray.getInt(index, layout.widthDefault));
                    c = 5;
                    break;
                case 55:
                    delta.add(55, typedArray.getInt(index, layout.heightDefault));
                    c = 5;
                    break;
                case 56:
                    delta.add(56, typedArray.getDimensionPixelSize(index, layout.widthMax));
                    c = 5;
                    break;
                case 57:
                    delta.add(57, typedArray.getDimensionPixelSize(index, layout.heightMax));
                    c = 5;
                    break;
                case 58:
                    delta.add(58, typedArray.getDimensionPixelSize(index, layout.widthMin));
                    c = 5;
                    break;
                case 59:
                    delta.add(59, typedArray.getDimensionPixelSize(index, layout.heightMin));
                    c = 5;
                    break;
                case 60:
                    delta.add(60, typedArray.getFloat(index, transform.rotation));
                    c = 5;
                    break;
                case 62:
                    delta.add(62, typedArray.getDimensionPixelSize(index, layout.circleRadius));
                    c = 5;
                    break;
                case 63:
                    delta.add(63, typedArray.getFloat(index, layout.circleAngle));
                    c = 5;
                    break;
                case 64:
                    delta.add(64, lookupID(typedArray, index, motion.mAnimateRelativeTo));
                    c = 5;
                    break;
                case 65:
                    if (typedArray.peekValue(index).type == 3) {
                        delta.add(65, typedArray.getString(index));
                    } else {
                        delta.add(65, Easing.NAMED_EASING[typedArray.getInteger(index, 0)]);
                    }
                    c = 5;
                    break;
                case 66:
                    delta.add(66, typedArray.getInt(index, 0));
                    c = 5;
                    break;
                case 67:
                    delta.add(67, typedArray.getFloat(index, motion.mPathRotate));
                    c = 5;
                    break;
                case 68:
                    delta.add(68, typedArray.getFloat(index, propertySet.mProgress));
                    c = 5;
                    break;
                case 69:
                    delta.add(69, typedArray.getFloat(index, 1.0f));
                    c = 5;
                    break;
                case 70:
                    delta.add(70, typedArray.getFloat(index, 1.0f));
                    c = 5;
                    break;
                case 71:
                    Log.e("ConstraintSet", "CURRENTLY UNSUPPORTED");
                    c = 5;
                    break;
                case 72:
                    delta.add(72, typedArray.getInt(index, layout.mBarrierDirection));
                    c = 5;
                    break;
                case 73:
                    delta.add(73, typedArray.getDimensionPixelSize(index, layout.mBarrierMargin));
                    c = 5;
                    break;
                case 74:
                    delta.add(74, typedArray.getString(index));
                    c = 5;
                    break;
                case 75:
                    delta.add(75, typedArray.getBoolean(index, layout.mBarrierAllowsGoneWidgets));
                    c = 5;
                    break;
                case 76:
                    delta.add(76, typedArray.getInt(index, motion.mPathMotionArc));
                    c = 5;
                    break;
                case 77:
                    delta.add(77, typedArray.getString(index));
                    c = 5;
                    break;
                case 78:
                    delta.add(78, typedArray.getInt(index, propertySet.mVisibilityMode));
                    c = 5;
                    break;
                case 79:
                    delta.add(79, typedArray.getFloat(index, motion.mMotionStagger));
                    c = 5;
                    break;
                case 80:
                    delta.add(80, typedArray.getBoolean(index, layout.constrainedWidth));
                    c = 5;
                    break;
                case 81:
                    delta.add(81, typedArray.getBoolean(index, layout.constrainedHeight));
                    c = 5;
                    break;
                case 82:
                    delta.add(82, typedArray.getInteger(index, motion.mAnimateCircleAngleTo));
                    c = 5;
                    break;
                case 83:
                    delta.add(83, lookupID(typedArray, index, transform.transformPivotTarget));
                    c = 5;
                    break;
                case 84:
                    delta.add(84, typedArray.getInteger(index, motion.mQuantizeMotionSteps));
                    c = 5;
                    break;
                case 85:
                    delta.add(85, typedArray.getFloat(index, motion.mQuantizeMotionPhase));
                    c = 5;
                    break;
                case 86:
                    int i2 = typedArray.peekValue(index).type;
                    if (i2 == 1) {
                        int resourceId2 = typedArray.getResourceId(index, -1);
                        motion.mQuantizeInterpolatorID = resourceId2;
                        delta.add(89, resourceId2);
                        if (motion.mQuantizeInterpolatorID != -1) {
                            motion.mQuantizeInterpolatorType = -2;
                            delta.add(88, -2);
                        }
                    } else if (i2 == 3) {
                        String string = typedArray.getString(index);
                        motion.mQuantizeInterpolatorString = string;
                        delta.add(90, string);
                        if (motion.mQuantizeInterpolatorString.indexOf("/") > 0) {
                            int resourceId3 = typedArray.getResourceId(index, -1);
                            motion.mQuantizeInterpolatorID = resourceId3;
                            delta.add(89, resourceId3);
                            motion.mQuantizeInterpolatorType = -2;
                            delta.add(88, -2);
                        } else {
                            motion.mQuantizeInterpolatorType = -1;
                            delta.add(88, -1);
                        }
                    } else {
                        int integer = typedArray.getInteger(index, motion.mQuantizeInterpolatorID);
                        motion.mQuantizeInterpolatorType = integer;
                        delta.add(88, integer);
                    }
                    c = 5;
                    break;
                case 87:
                    Log.w("ConstraintSet", "unused attribute 0x" + Integer.toHexString(index) + "   " + sMapToConstant.get(index));
                    c = 5;
                    break;
                case 93:
                    delta.add(93, typedArray.getDimensionPixelSize(index, layout.baselineMargin));
                    c = 5;
                    break;
                case 94:
                    delta.add(94, typedArray.getDimensionPixelSize(index, layout.goneBaselineMargin));
                    c = 5;
                    break;
                case 95:
                    parseDimensionConstraints(delta, typedArray, index, 0);
                    c = 5;
                    break;
                case 96:
                    parseDimensionConstraints(delta, typedArray, index, 1);
                    c = 5;
                    break;
                case 97:
                    delta.add(97, typedArray.getInt(index, layout.mWrapBehavior));
                    c = 5;
                    break;
                case 98:
                    if (MotionLayout.IS_IN_EDIT_MODE) {
                        int resourceId4 = typedArray.getResourceId(index, constraint.mViewId);
                        constraint.mViewId = resourceId4;
                        if (resourceId4 == -1) {
                            constraint.mTargetString = typedArray.getString(index);
                        }
                    } else if (typedArray.peekValue(index).type == 3) {
                        constraint.mTargetString = typedArray.getString(index);
                    } else {
                        constraint.mViewId = typedArray.getResourceId(index, constraint.mViewId);
                    }
                    c = 5;
                    break;
                case 99:
                    delta.add(99, typedArray.getBoolean(index, layout.guidelineUseRtl));
                    c = 5;
                    break;
            }
        }
    }

    public static String sideToString(int i) {
        switch (i) {
            case 1:
                return "left";
            case 2:
                return "right";
            case 3:
                return "top";
            case 4:
                return "bottom";
            case 5:
                return "baseline";
            case 6:
                return "start";
            case 7:
                return "end";
            default:
                return "undefined";
        }
    }

    public final void applyCustomAttributes(MotionLayout motionLayout) {
        Constraint constraint;
        int childCount = motionLayout.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = motionLayout.getChildAt(i);
            int id = childAt.getId();
            if (!this.mConstraints.containsKey(Integer.valueOf(id))) {
                Log.w("ConstraintSet", "id unknown " + Debug.getName(childAt));
            } else {
                if (this.mForceId && id == -1) {
                    throw new RuntimeException("All children of ConstraintLayout must have ids to use ConstraintSet");
                }
                if (this.mConstraints.containsKey(Integer.valueOf(id)) && (constraint = (Constraint) this.mConstraints.get(Integer.valueOf(id))) != null) {
                    ConstraintAttribute.setAttributes(childAt, constraint.mCustomConstraints);
                }
            }
        }
    }

    public final void applyDeltaFrom(ConstraintSet constraintSet) {
        for (Constraint constraint : constraintSet.mConstraints.values()) {
            if (constraint.mDelta != null) {
                if (constraint.mTargetString == null) {
                    constraint.mDelta.applyDelta(getConstraint(constraint.mViewId));
                } else {
                    Iterator it = this.mConstraints.keySet().iterator();
                    while (it.hasNext()) {
                        Constraint constraint2 = getConstraint(((Integer) it.next()).intValue());
                        String str = constraint2.layout.mConstraintTag;
                        if (str != null && constraint.mTargetString.matches(str)) {
                            constraint.mDelta.applyDelta(constraint2);
                            constraint2.mCustomConstraints.putAll((HashMap) constraint.mCustomConstraints.clone());
                        }
                    }
                }
            }
        }
    }

    public final void applyTo(ConstraintLayout constraintLayout) {
        applyToInternal(constraintLayout);
        constraintLayout.mConstraintSet = null;
        constraintLayout.requestLayout();
    }

    public final void applyToInternal(ConstraintLayout constraintLayout) {
        int childCount = constraintLayout.getChildCount();
        HashSet hashSet = new HashSet(this.mConstraints.keySet());
        for (int i = 0; i < childCount; i++) {
            View childAt = constraintLayout.getChildAt(i);
            int id = childAt.getId();
            if (!this.mConstraints.containsKey(Integer.valueOf(id))) {
                Log.w("ConstraintSet", "id unknown " + Debug.getName(childAt));
            } else {
                if (this.mForceId && id == -1) {
                    throw new RuntimeException("All children of ConstraintLayout must have ids to use ConstraintSet");
                }
                if (id != -1 && this.mConstraints.containsKey(Integer.valueOf(id))) {
                    hashSet.remove(Integer.valueOf(id));
                    Constraint constraint = (Constraint) this.mConstraints.get(Integer.valueOf(id));
                    if (constraint != null) {
                        if (childAt instanceof Barrier) {
                            Layout layout = constraint.layout;
                            layout.mHelperType = 1;
                            Barrier barrier = (Barrier) childAt;
                            barrier.setId(id);
                            barrier.mIndicatedType = layout.mBarrierDirection;
                            int i2 = layout.mBarrierMargin;
                            androidx.constraintlayout.core.widgets.Barrier barrier2 = barrier.mBarrier;
                            barrier2.mMargin = i2;
                            barrier2.mAllowsGoneWidget = layout.mBarrierAllowsGoneWidgets;
                            int[] iArr = layout.mReferenceIds;
                            if (iArr != null) {
                                barrier.setReferencedIds(iArr);
                            } else {
                                String str = layout.mReferenceIdString;
                                if (str != null) {
                                    int[] convertReferenceString = convertReferenceString(barrier, str);
                                    layout.mReferenceIds = convertReferenceString;
                                    barrier.setReferencedIds(convertReferenceString);
                                }
                            }
                        }
                        ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) childAt.getLayoutParams();
                        layoutParams.validate();
                        constraint.applyTo(layoutParams);
                        ConstraintAttribute.setAttributes(childAt, constraint.mCustomConstraints);
                        childAt.setLayoutParams(layoutParams);
                        PropertySet propertySet = constraint.propertySet;
                        if (propertySet.mVisibilityMode == 0) {
                            childAt.setVisibility(propertySet.visibility);
                        }
                        childAt.setAlpha(propertySet.alpha);
                        Transform transform = constraint.transform;
                        childAt.setRotation(transform.rotation);
                        childAt.setRotationX(transform.rotationX);
                        childAt.setRotationY(transform.rotationY);
                        childAt.setScaleX(transform.scaleX);
                        childAt.setScaleY(transform.scaleY);
                        if (transform.transformPivotTarget != -1) {
                            if (((View) childAt.getParent()).findViewById(transform.transformPivotTarget) != null) {
                                float bottom = (r7.getBottom() + r7.getTop()) / 2.0f;
                                float right = (r7.getRight() + r7.getLeft()) / 2.0f;
                                if (childAt.getRight() - childAt.getLeft() > 0 && childAt.getBottom() - childAt.getTop() > 0) {
                                    childAt.setPivotX(right - childAt.getLeft());
                                    childAt.setPivotY(bottom - childAt.getTop());
                                }
                            }
                        } else {
                            if (!Float.isNaN(transform.transformPivotX)) {
                                childAt.setPivotX(transform.transformPivotX);
                            }
                            if (!Float.isNaN(transform.transformPivotY)) {
                                childAt.setPivotY(transform.transformPivotY);
                            }
                        }
                        childAt.setTranslationX(transform.translationX);
                        childAt.setTranslationY(transform.translationY);
                        childAt.setTranslationZ(transform.translationZ);
                        if (transform.applyElevation) {
                            childAt.setElevation(transform.elevation);
                        }
                    }
                }
            }
        }
        Iterator it = hashSet.iterator();
        while (it.hasNext()) {
            Integer num = (Integer) it.next();
            Constraint constraint2 = (Constraint) this.mConstraints.get(num);
            if (constraint2 != null) {
                Layout layout2 = constraint2.layout;
                if (layout2.mHelperType == 1) {
                    Barrier barrier3 = new Barrier(constraintLayout.getContext());
                    barrier3.setId(num.intValue());
                    int[] iArr2 = layout2.mReferenceIds;
                    if (iArr2 != null) {
                        barrier3.setReferencedIds(iArr2);
                    } else {
                        String str2 = layout2.mReferenceIdString;
                        if (str2 != null) {
                            int[] convertReferenceString2 = convertReferenceString(barrier3, str2);
                            layout2.mReferenceIds = convertReferenceString2;
                            barrier3.setReferencedIds(convertReferenceString2);
                        }
                    }
                    barrier3.mIndicatedType = layout2.mBarrierDirection;
                    barrier3.mBarrier.mMargin = layout2.mBarrierMargin;
                    SharedValues sharedValues = ConstraintLayout.sSharedValues;
                    ConstraintLayout.LayoutParams layoutParams2 = new ConstraintLayout.LayoutParams(-2);
                    barrier3.validateParams();
                    constraint2.applyTo(layoutParams2);
                    constraintLayout.addView(barrier3, layoutParams2);
                }
                if (layout2.mIsGuideline) {
                    View guideline = new Guideline(constraintLayout.getContext());
                    guideline.setId(num.intValue());
                    SharedValues sharedValues2 = ConstraintLayout.sSharedValues;
                    ConstraintLayout.LayoutParams layoutParams3 = new ConstraintLayout.LayoutParams(-2);
                    constraint2.applyTo(layoutParams3);
                    constraintLayout.addView(guideline, layoutParams3);
                }
            }
        }
        for (int i3 = 0; i3 < childCount; i3++) {
            View childAt2 = constraintLayout.getChildAt(i3);
            if (childAt2 instanceof ConstraintHelper) {
                ((ConstraintHelper) childAt2).applyLayoutFeaturesInConstraintSet(constraintLayout);
            }
        }
    }

    public final void center(int i, int i2, int i3) {
        if (i2 == 1 || i2 == 2) {
            connect(i, 1, 0, i2, 0);
            connect(i, 2, 0, i3, 0);
            Constraint constraint = (Constraint) this.mConstraints.get(Integer.valueOf(i));
            if (constraint != null) {
                constraint.layout.horizontalBias = 0.5f;
                return;
            }
            return;
        }
        if (i2 == 6 || i2 == 7) {
            connect(i, 6, 0, i2, 0);
            connect(i, 7, 0, i3, 0);
            Constraint constraint2 = (Constraint) this.mConstraints.get(Integer.valueOf(i));
            if (constraint2 != null) {
                constraint2.layout.horizontalBias = 0.5f;
                return;
            }
            return;
        }
        connect(i, 3, 0, i2, 0);
        connect(i, 4, 0, i3, 0);
        Constraint constraint3 = (Constraint) this.mConstraints.get(Integer.valueOf(i));
        if (constraint3 != null) {
            constraint3.layout.verticalBias = 0.5f;
        }
    }

    public final void clear(int i, int i2) {
        Constraint constraint;
        if (!this.mConstraints.containsKey(Integer.valueOf(i)) || (constraint = (Constraint) this.mConstraints.get(Integer.valueOf(i))) == null) {
            return;
        }
        Layout layout = constraint.layout;
        switch (i2) {
            case 1:
                layout.leftToRight = -1;
                layout.leftToLeft = -1;
                layout.leftMargin = -1;
                layout.goneLeftMargin = Integer.MIN_VALUE;
                return;
            case 2:
                layout.rightToRight = -1;
                layout.rightToLeft = -1;
                layout.rightMargin = -1;
                layout.goneRightMargin = Integer.MIN_VALUE;
                return;
            case 3:
                layout.topToBottom = -1;
                layout.topToTop = -1;
                layout.topMargin = 0;
                layout.goneTopMargin = Integer.MIN_VALUE;
                return;
            case 4:
                layout.bottomToTop = -1;
                layout.bottomToBottom = -1;
                layout.bottomMargin = 0;
                layout.goneBottomMargin = Integer.MIN_VALUE;
                return;
            case 5:
                layout.baselineToBaseline = -1;
                layout.baselineToTop = -1;
                layout.baselineToBottom = -1;
                layout.baselineMargin = 0;
                layout.goneBaselineMargin = Integer.MIN_VALUE;
                return;
            case 6:
                layout.startToEnd = -1;
                layout.startToStart = -1;
                layout.startMargin = 0;
                layout.goneStartMargin = Integer.MIN_VALUE;
                return;
            case 7:
                layout.endToStart = -1;
                layout.endToEnd = -1;
                layout.endMargin = 0;
                layout.goneEndMargin = Integer.MIN_VALUE;
                return;
            case 8:
                layout.circleAngle = -1.0f;
                layout.circleRadius = -1;
                layout.circleConstraint = -1;
                return;
            default:
                throw new IllegalArgumentException("unknown constraint");
        }
    }

    public final void clone(ConstraintSet constraintSet) {
        this.mConstraints.clear();
        for (Integer num : constraintSet.mConstraints.keySet()) {
            Constraint constraint = (Constraint) constraintSet.mConstraints.get(num);
            if (constraint != null) {
                this.mConstraints.put(num, constraint.m709clone());
            }
        }
    }

    public void connect(int i, int i2, int i3, int i4, int i5) {
        if (!this.mConstraints.containsKey(Integer.valueOf(i))) {
            this.mConstraints.put(Integer.valueOf(i), new Constraint());
        }
        Constraint constraint = (Constraint) this.mConstraints.get(Integer.valueOf(i));
        if (constraint == null) {
            return;
        }
        Layout layout = constraint.layout;
        switch (i2) {
            case 1:
                if (i4 == 1) {
                    layout.leftToLeft = i3;
                    layout.leftToRight = -1;
                } else {
                    if (i4 != 2) {
                        throw new IllegalArgumentException("Left to " + sideToString(i4) + " undefined");
                    }
                    layout.leftToRight = i3;
                    layout.leftToLeft = -1;
                }
                layout.leftMargin = i5;
                return;
            case 2:
                if (i4 == 1) {
                    layout.rightToLeft = i3;
                    layout.rightToRight = -1;
                } else {
                    if (i4 != 2) {
                        throw new IllegalArgumentException("right to " + sideToString(i4) + " undefined");
                    }
                    layout.rightToRight = i3;
                    layout.rightToLeft = -1;
                }
                layout.rightMargin = i5;
                return;
            case 3:
                if (i4 == 3) {
                    layout.topToTop = i3;
                    layout.topToBottom = -1;
                    layout.baselineToBaseline = -1;
                    layout.baselineToTop = -1;
                    layout.baselineToBottom = -1;
                } else {
                    if (i4 != 4) {
                        throw new IllegalArgumentException("right to " + sideToString(i4) + " undefined");
                    }
                    layout.topToBottom = i3;
                    layout.topToTop = -1;
                    layout.baselineToBaseline = -1;
                    layout.baselineToTop = -1;
                    layout.baselineToBottom = -1;
                }
                layout.topMargin = i5;
                return;
            case 4:
                if (i4 == 4) {
                    layout.bottomToBottom = i3;
                    layout.bottomToTop = -1;
                    layout.baselineToBaseline = -1;
                    layout.baselineToTop = -1;
                    layout.baselineToBottom = -1;
                } else {
                    if (i4 != 3) {
                        throw new IllegalArgumentException("right to " + sideToString(i4) + " undefined");
                    }
                    layout.bottomToTop = i3;
                    layout.bottomToBottom = -1;
                    layout.baselineToBaseline = -1;
                    layout.baselineToTop = -1;
                    layout.baselineToBottom = -1;
                }
                layout.bottomMargin = i5;
                return;
            case 5:
                if (i4 == 5) {
                    layout.baselineToBaseline = i3;
                    layout.bottomToBottom = -1;
                    layout.bottomToTop = -1;
                    layout.topToTop = -1;
                    layout.topToBottom = -1;
                    return;
                }
                if (i4 == 3) {
                    layout.baselineToTop = i3;
                    layout.bottomToBottom = -1;
                    layout.bottomToTop = -1;
                    layout.topToTop = -1;
                    layout.topToBottom = -1;
                    return;
                }
                if (i4 != 4) {
                    throw new IllegalArgumentException("right to " + sideToString(i4) + " undefined");
                }
                layout.baselineToBottom = i3;
                layout.bottomToBottom = -1;
                layout.bottomToTop = -1;
                layout.topToTop = -1;
                layout.topToBottom = -1;
                return;
            case 6:
                if (i4 == 6) {
                    layout.startToStart = i3;
                    layout.startToEnd = -1;
                } else {
                    if (i4 != 7) {
                        throw new IllegalArgumentException("right to " + sideToString(i4) + " undefined");
                    }
                    layout.startToEnd = i3;
                    layout.startToStart = -1;
                }
                layout.startMargin = i5;
                return;
            case 7:
                if (i4 == 7) {
                    layout.endToEnd = i3;
                    layout.endToStart = -1;
                } else {
                    if (i4 != 6) {
                        throw new IllegalArgumentException("right to " + sideToString(i4) + " undefined");
                    }
                    layout.endToStart = i3;
                    layout.endToEnd = -1;
                }
                layout.endMargin = i5;
                return;
            default:
                throw new IllegalArgumentException(sideToString(i2) + " to " + sideToString(i4) + " unknown");
        }
    }

    public void constrainHeight(int i, int i2) {
        get(i).layout.mHeight = i2;
    }

    public void constrainWidth(int i, int i2) {
        get(i).layout.mWidth = i2;
    }

    public final void createBarrier(int i, int i2, int i3, int... iArr) {
        Layout layout = get(i).layout;
        layout.mHelperType = 1;
        layout.mBarrierDirection = i2;
        layout.mBarrierMargin = i3;
        layout.mIsGuideline = false;
        layout.mReferenceIds = iArr;
    }

    public final Constraint get(int i) {
        if (!this.mConstraints.containsKey(Integer.valueOf(i))) {
            this.mConstraints.put(Integer.valueOf(i), new Constraint());
        }
        return (Constraint) this.mConstraints.get(Integer.valueOf(i));
    }

    public final Constraint getConstraint(int i) {
        if (this.mConstraints.containsKey(Integer.valueOf(i))) {
            return (Constraint) this.mConstraints.get(Integer.valueOf(i));
        }
        return null;
    }

    public int getHeight(int i) {
        return get(i).layout.mHeight;
    }

    public final int[] getKnownIds() {
        Integer[] numArr = (Integer[]) this.mConstraints.keySet().toArray(new Integer[0]);
        int length = numArr.length;
        int[] iArr = new int[length];
        for (int i = 0; i < length; i++) {
            iArr[i] = numArr[i].intValue();
        }
        return iArr;
    }

    public int getWidth(int i) {
        return get(i).layout.mWidth;
    }

    public final void load(int i, Context context) {
        XmlResourceParser xml = context.getResources().getXml(i);
        try {
            for (int eventType = xml.getEventType(); eventType != 1; eventType = xml.next()) {
                if (eventType == 2) {
                    String name = xml.getName();
                    Constraint fillFromAttributeList = fillFromAttributeList(context, Xml.asAttributeSet(xml), false);
                    if (name.equalsIgnoreCase("Guideline")) {
                        fillFromAttributeList.layout.mIsGuideline = true;
                    }
                    this.mConstraints.put(Integer.valueOf(fillFromAttributeList.mViewId), fillFromAttributeList);
                }
            }
        } catch (IOException e) {
            Log.e("ConstraintSet", "Error parsing resource: " + i, e);
        } catch (XmlPullParserException e2) {
            Log.e("ConstraintSet", "Error parsing resource: " + i, e2);
        }
    }

    public final void setAlpha(int i, float f) {
        get(i).propertySet.alpha = f;
    }

    public void setGoneMargin(int i, int i2, int i3) {
        Constraint constraint = get(i);
        switch (i2) {
            case 1:
                constraint.layout.goneLeftMargin = i3;
                return;
            case 2:
                constraint.layout.goneRightMargin = i3;
                return;
            case 3:
                constraint.layout.goneTopMargin = i3;
                return;
            case 4:
                constraint.layout.goneBottomMargin = i3;
                return;
            case 5:
                constraint.layout.goneBaselineMargin = i3;
                return;
            case 6:
                constraint.layout.goneStartMargin = i3;
                return;
            case 7:
                constraint.layout.goneEndMargin = i3;
                return;
            default:
                throw new IllegalArgumentException("unknown constraint");
        }
    }

    public final void setGuidelineBegin(int i, int i2) {
        get(i).layout.guideBegin = i2;
        get(i).layout.guideEnd = -1;
        get(i).layout.guidePercent = -1.0f;
    }

    public final void setGuidelineEnd(int i, int i2) {
        get(i).layout.guideEnd = i2;
        get(i).layout.guideBegin = -1;
        get(i).layout.guidePercent = -1.0f;
    }

    public final void setGuidelinePercent(int i, float f) {
        get(i).layout.guidePercent = f;
        get(i).layout.guideEnd = -1;
        get(i).layout.guideBegin = -1;
    }

    public final void setMargin(int i, int i2, int i3) {
        Constraint constraint = get(i);
        switch (i2) {
            case 1:
                constraint.layout.leftMargin = i3;
                return;
            case 2:
                constraint.layout.rightMargin = i3;
                return;
            case 3:
                constraint.layout.topMargin = i3;
                return;
            case 4:
                constraint.layout.bottomMargin = i3;
                return;
            case 5:
                constraint.layout.baselineMargin = i3;
                return;
            case 6:
                constraint.layout.startMargin = i3;
                return;
            case 7:
                constraint.layout.endMargin = i3;
                return;
            default:
                throw new IllegalArgumentException("unknown constraint");
        }
    }

    public final void setVisibility(int i, int i2) {
        get(i).propertySet.visibility = i2;
    }

    public final void clone(ConstraintLayout constraintLayout) {
        int i;
        int i2;
        ConstraintSet constraintSet = this;
        int childCount = constraintLayout.getChildCount();
        constraintSet.mConstraints.clear();
        int i3 = 0;
        while (i3 < childCount) {
            View childAt = constraintLayout.getChildAt(i3);
            ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) childAt.getLayoutParams();
            int id = childAt.getId();
            if (constraintSet.mForceId && id == -1) {
                throw new RuntimeException("All children of ConstraintLayout must have ids to use ConstraintSet");
            }
            if (!constraintSet.mConstraints.containsKey(Integer.valueOf(id))) {
                constraintSet.mConstraints.put(Integer.valueOf(id), new Constraint());
            }
            Constraint constraint = (Constraint) constraintSet.mConstraints.get(Integer.valueOf(id));
            if (constraint == null) {
                i = childCount;
            } else {
                HashMap hashMap = constraintSet.mSavedAttributes;
                HashMap hashMap2 = new HashMap();
                Class<?> cls = childAt.getClass();
                for (String str : hashMap.keySet()) {
                    ConstraintAttribute constraintAttribute = (ConstraintAttribute) hashMap.get(str);
                    try {
                        if (str.equals("BackgroundColor")) {
                            i2 = childCount;
                            try {
                                hashMap2.put(str, new ConstraintAttribute(constraintAttribute, Integer.valueOf(((ColorDrawable) childAt.getBackground()).getColor())));
                            } catch (IllegalAccessException e) {
                                e = e;
                                StringBuilder m = ActivityResultRegistry$$ExternalSyntheticOutline0.m(" Custom Attribute \"", str, "\" not found on ");
                                m.append(cls.getName());
                                Log.e("TransitionLayout", m.toString(), e);
                                childCount = i2;
                            } catch (NoSuchMethodException e2) {
                                e = e2;
                                Log.e("TransitionLayout", cls.getName() + " must have a method " + str, e);
                                childCount = i2;
                            } catch (InvocationTargetException e3) {
                                e = e3;
                                StringBuilder m2 = ActivityResultRegistry$$ExternalSyntheticOutline0.m(" Custom Attribute \"", str, "\" not found on ");
                                m2.append(cls.getName());
                                Log.e("TransitionLayout", m2.toString(), e);
                                childCount = i2;
                            }
                        } else {
                            i2 = childCount;
                            try {
                                Class[] clsArr = new Class[0];
                                hashMap2.put(str, new ConstraintAttribute(constraintAttribute, cls.getMethod("getMap" + str, null).invoke(childAt, null)));
                            } catch (IllegalAccessException e4) {
                                e = e4;
                                StringBuilder m3 = ActivityResultRegistry$$ExternalSyntheticOutline0.m(" Custom Attribute \"", str, "\" not found on ");
                                m3.append(cls.getName());
                                Log.e("TransitionLayout", m3.toString(), e);
                                childCount = i2;
                            } catch (NoSuchMethodException e5) {
                                e = e5;
                                Log.e("TransitionLayout", cls.getName() + " must have a method " + str, e);
                                childCount = i2;
                            } catch (InvocationTargetException e6) {
                                e = e6;
                                StringBuilder m22 = ActivityResultRegistry$$ExternalSyntheticOutline0.m(" Custom Attribute \"", str, "\" not found on ");
                                m22.append(cls.getName());
                                Log.e("TransitionLayout", m22.toString(), e);
                                childCount = i2;
                            }
                        }
                    } catch (IllegalAccessException e7) {
                        e = e7;
                        i2 = childCount;
                    } catch (NoSuchMethodException e8) {
                        e = e8;
                        i2 = childCount;
                    } catch (InvocationTargetException e9) {
                        e = e9;
                        i2 = childCount;
                    }
                    childCount = i2;
                }
                i = childCount;
                constraint.mCustomConstraints = hashMap2;
                Constraint.access$000(constraint, id, layoutParams);
                int visibility = childAt.getVisibility();
                PropertySet propertySet = constraint.propertySet;
                propertySet.visibility = visibility;
                propertySet.alpha = childAt.getAlpha();
                float rotation = childAt.getRotation();
                Transform transform = constraint.transform;
                transform.rotation = rotation;
                transform.rotationX = childAt.getRotationX();
                transform.rotationY = childAt.getRotationY();
                transform.scaleX = childAt.getScaleX();
                transform.scaleY = childAt.getScaleY();
                float pivotX = childAt.getPivotX();
                float pivotY = childAt.getPivotY();
                if (pivotX != 0.0d || pivotY != 0.0d) {
                    transform.transformPivotX = pivotX;
                    transform.transformPivotY = pivotY;
                }
                transform.translationX = childAt.getTranslationX();
                transform.translationY = childAt.getTranslationY();
                transform.translationZ = childAt.getTranslationZ();
                if (transform.applyElevation) {
                    transform.elevation = childAt.getElevation();
                }
                if (childAt instanceof Barrier) {
                    Barrier barrier = (Barrier) childAt;
                    boolean z = barrier.mBarrier.mAllowsGoneWidget;
                    Layout layout = constraint.layout;
                    layout.mBarrierAllowsGoneWidgets = z;
                    layout.mReferenceIds = Arrays.copyOf(barrier.mIds, barrier.mCount);
                    layout.mBarrierDirection = barrier.mIndicatedType;
                    layout.mBarrierMargin = barrier.mBarrier.mMargin;
                }
            }
            i3++;
            constraintSet = this;
            childCount = i;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:82:0x01d3, code lost:
    
        continue;
     */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue
    java.lang.NullPointerException: Cannot invoke "java.util.List.iterator()" because the return value of "jadx.core.dex.visitors.regions.SwitchOverStringVisitor$SwitchData.getNewCases()" is null
    	at jadx.core.dex.visitors.regions.SwitchOverStringVisitor.restoreSwitchOverString(SwitchOverStringVisitor.java:109)
    	at jadx.core.dex.visitors.regions.SwitchOverStringVisitor.visitRegion(SwitchOverStringVisitor.java:66)
    	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:77)
    	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:82)
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void load(android.content.Context r12, org.xmlpull.v1.XmlPullParser r13) {
        /*
            Method dump skipped, instructions count: 566
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.widget.ConstraintSet.load(android.content.Context, org.xmlpull.v1.XmlPullParser):void");
    }

    public final void connect(int i, int i2, int i3, int i4) {
        if (!this.mConstraints.containsKey(Integer.valueOf(i))) {
            this.mConstraints.put(Integer.valueOf(i), new Constraint());
        }
        Constraint constraint = (Constraint) this.mConstraints.get(Integer.valueOf(i));
        if (constraint == null) {
            return;
        }
        Layout layout = constraint.layout;
        switch (i2) {
            case 1:
                if (i4 == 1) {
                    layout.leftToLeft = i3;
                    layout.leftToRight = -1;
                    return;
                } else if (i4 == 2) {
                    layout.leftToRight = i3;
                    layout.leftToLeft = -1;
                    return;
                } else {
                    throw new IllegalArgumentException("left to " + sideToString(i4) + " undefined");
                }
            case 2:
                if (i4 == 1) {
                    layout.rightToLeft = i3;
                    layout.rightToRight = -1;
                    return;
                } else {
                    if (i4 != 2) {
                        throw new IllegalArgumentException("right to " + sideToString(i4) + " undefined");
                    }
                    layout.rightToRight = i3;
                    layout.rightToLeft = -1;
                    return;
                }
            case 3:
                if (i4 == 3) {
                    layout.topToTop = i3;
                    layout.topToBottom = -1;
                    layout.baselineToBaseline = -1;
                    layout.baselineToTop = -1;
                    layout.baselineToBottom = -1;
                    return;
                }
                if (i4 != 4) {
                    throw new IllegalArgumentException("right to " + sideToString(i4) + " undefined");
                }
                layout.topToBottom = i3;
                layout.topToTop = -1;
                layout.baselineToBaseline = -1;
                layout.baselineToTop = -1;
                layout.baselineToBottom = -1;
                return;
            case 4:
                if (i4 == 4) {
                    layout.bottomToBottom = i3;
                    layout.bottomToTop = -1;
                    layout.baselineToBaseline = -1;
                    layout.baselineToTop = -1;
                    layout.baselineToBottom = -1;
                    return;
                }
                if (i4 != 3) {
                    throw new IllegalArgumentException("right to " + sideToString(i4) + " undefined");
                }
                layout.bottomToTop = i3;
                layout.bottomToBottom = -1;
                layout.baselineToBaseline = -1;
                layout.baselineToTop = -1;
                layout.baselineToBottom = -1;
                return;
            case 5:
                if (i4 == 5) {
                    layout.baselineToBaseline = i3;
                    layout.bottomToBottom = -1;
                    layout.bottomToTop = -1;
                    layout.topToTop = -1;
                    layout.topToBottom = -1;
                    return;
                }
                if (i4 == 3) {
                    layout.baselineToTop = i3;
                    layout.bottomToBottom = -1;
                    layout.bottomToTop = -1;
                    layout.topToTop = -1;
                    layout.topToBottom = -1;
                    return;
                }
                if (i4 != 4) {
                    throw new IllegalArgumentException("right to " + sideToString(i4) + " undefined");
                }
                layout.baselineToBottom = i3;
                layout.bottomToBottom = -1;
                layout.bottomToTop = -1;
                layout.topToTop = -1;
                layout.topToBottom = -1;
                return;
            case 6:
                if (i4 == 6) {
                    layout.startToStart = i3;
                    layout.startToEnd = -1;
                    return;
                } else {
                    if (i4 != 7) {
                        throw new IllegalArgumentException("right to " + sideToString(i4) + " undefined");
                    }
                    layout.startToEnd = i3;
                    layout.startToStart = -1;
                    return;
                }
            case 7:
                if (i4 == 7) {
                    layout.endToEnd = i3;
                    layout.endToStart = -1;
                    return;
                } else {
                    if (i4 != 6) {
                        throw new IllegalArgumentException("right to " + sideToString(i4) + " undefined");
                    }
                    layout.endToStart = i3;
                    layout.endToEnd = -1;
                    return;
                }
            default:
                throw new IllegalArgumentException(sideToString(i2) + " to " + sideToString(i4) + " unknown");
        }
    }
}
