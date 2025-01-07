package androidx.constraintlayout.motion.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.frameworks.stats.AnnotationValue$1$$ExternalSyntheticOutline0;
import android.graphics.Canvas;
import android.graphics.DashPathEffect;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.util.SparseBooleanArray;
import android.util.SparseIntArray;
import android.view.Display;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AnimationUtils;
import android.view.animation.AnticipateInterpolator;
import android.view.animation.BounceInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.OvershootInterpolator;
import androidx.activity.result.ActivityResultRegistry$$ExternalSyntheticOutline0;
import androidx.constraintlayout.core.motion.utils.ArcCurveFit;
import androidx.constraintlayout.core.motion.utils.CurveFit;
import androidx.constraintlayout.core.motion.utils.Easing;
import androidx.constraintlayout.core.motion.utils.KeyCache;
import androidx.constraintlayout.core.widgets.Barrier;
import androidx.constraintlayout.core.widgets.ConstraintWidget;
import androidx.constraintlayout.core.widgets.ConstraintWidgetContainer;
import androidx.constraintlayout.core.widgets.Flow;
import androidx.constraintlayout.core.widgets.Guideline;
import androidx.constraintlayout.core.widgets.HelperWidget;
import androidx.constraintlayout.core.widgets.Placeholder;
import androidx.constraintlayout.core.widgets.VirtualLayout;
import androidx.constraintlayout.motion.utils.StopLogic;
import androidx.constraintlayout.motion.widget.MotionScene;
import androidx.constraintlayout.motion.widget.TouchResponse;
import androidx.constraintlayout.widget.ConstraintHelper;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintLayoutStates;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.constraintlayout.widget.Constraints$LayoutParams;
import androidx.constraintlayout.widget.R$styleable;
import androidx.constraintlayout.widget.StateSet;
import androidx.core.view.NestedScrollingParent3;
import androidx.core.widget.NestedScrollView;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class MotionLayout extends ConstraintLayout implements NestedScrollingParent3 {
    public static boolean IS_IN_EDIT_MODE;
    public long mAnimationStartTime;
    public int mBeginState;
    public final RectF mBoundsCheck;
    public int mCurrentState;
    public int mDebugPath;
    public final DecelerateInterpolator mDecelerateLogic;
    public DevModeDraw mDevModeDraw;
    public int mEndState;
    public int mEndWrapHeight;
    public int mEndWrapWidth;
    public final HashMap mFrameArrayList;
    public int mFrames;
    public int mHeightMeasureMode;
    public boolean mInLayout;
    public boolean mInTransition;
    public final boolean mInteractionEnabled;
    public MotionInterpolator mInterpolator;
    public Matrix mInverseMatrix;
    public boolean mKeepAnimating;
    public final KeyCache mKeyCache;
    public long mLastDrawTime;
    public float mLastFps;
    public int mLastHeightMeasureSpec;
    public int mLastLayoutHeight;
    public int mLastLayoutWidth;
    public float mLastVelocity;
    public int mLastWidthMeasureSpec;
    public boolean mMeasureDuringTransition;
    public final Model mModel;
    public boolean mNeedsFireTransitionCompleted;
    public ViewTransition$$ExternalSyntheticLambda0 mOnComplete;
    public float mPostInterpolationPosition;
    public final HashMap mPreRotate;
    public Interpolator mProgressInterpolator;
    public View mRegionView;
    public MotionScene mScene;
    public float mScrollTargetDT;
    public float mScrollTargetDX;
    public float mScrollTargetDY;
    public long mScrollTargetTime;
    public int mStartWrapHeight;
    public int mStartWrapWidth;
    public StateCache mStateCache;
    public final StopLogic mStopLogic;
    public final Rect mTempRect;
    public boolean mTemporalInterpolator;
    public final ArrayList mTransitionCompleted;
    public float mTransitionDuration;
    public float mTransitionGoalPosition;
    public boolean mTransitionInstantly;
    public float mTransitionLastPosition;
    public long mTransitionLastTime;
    public float mTransitionPosition;
    public TransitionState mTransitionState;
    public boolean mUndergoingMotion;
    public int mWidthMeasureMode;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class DecelerateInterpolator extends MotionInterpolator {
        public float mMaxA;
        public float mInitialV = 0.0f;
        public float mCurrentP = 0.0f;

        public DecelerateInterpolator() {
        }

        @Override // android.animation.TimeInterpolator
        public final float getInterpolation(float f) {
            float f2 = this.mInitialV;
            if (f2 > 0.0f) {
                float f3 = this.mMaxA;
                if (f2 / f3 < f) {
                    f = f2 / f3;
                }
                MotionLayout.this.mLastVelocity = f2 - (f3 * f);
                return ((f2 * f) - (((f3 * f) * f) / 2.0f)) + this.mCurrentP;
            }
            float f4 = this.mMaxA;
            if ((-f2) / f4 < f) {
                f = (-f2) / f4;
            }
            MotionLayout.this.mLastVelocity = (f4 * f) + f2;
            return (((f4 * f) * f) / 2.0f) + (f2 * f) + this.mCurrentP;
        }

        @Override // androidx.constraintlayout.motion.widget.MotionInterpolator
        public final float getVelocity() {
            return MotionLayout.this.mLastVelocity;
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class DevModeDraw {
        public final Paint mFillPaint;
        public int mKeyFrameCount;
        public final float[] mKeyFramePoints;
        public final Paint mPaint;
        public final Paint mPaintGraph;
        public final Paint mPaintKeyframes;
        public Path mPath;
        public final int[] mPathMode;
        public float[] mPoints;
        public final float[] mRectangle;
        public final Paint mTextPaint;
        public final Rect mBounds = new Rect();
        public final int mShadowTranslate = 1;

        public DevModeDraw() {
            Paint paint = new Paint();
            this.mPaint = paint;
            paint.setAntiAlias(true);
            paint.setColor(-21965);
            paint.setStrokeWidth(2.0f);
            Paint.Style style = Paint.Style.STROKE;
            paint.setStyle(style);
            Paint paint2 = new Paint();
            this.mPaintKeyframes = paint2;
            paint2.setAntiAlias(true);
            paint2.setColor(-2067046);
            paint2.setStrokeWidth(2.0f);
            paint2.setStyle(style);
            Paint paint3 = new Paint();
            this.mPaintGraph = paint3;
            paint3.setAntiAlias(true);
            paint3.setColor(-13391360);
            paint3.setStrokeWidth(2.0f);
            paint3.setStyle(style);
            Paint paint4 = new Paint();
            this.mTextPaint = paint4;
            paint4.setAntiAlias(true);
            paint4.setColor(-13391360);
            paint4.setTextSize(MotionLayout.this.getContext().getResources().getDisplayMetrics().density * 12.0f);
            this.mRectangle = new float[8];
            Paint paint5 = new Paint();
            this.mFillPaint = paint5;
            paint5.setAntiAlias(true);
            paint3.setPathEffect(new DashPathEffect(new float[]{4.0f, 8.0f}, 0.0f));
            this.mKeyFramePoints = new float[100];
            this.mPathMode = new int[50];
        }

        public final void drawAll(Canvas canvas, int i, int i2, MotionController motionController) {
            int i3;
            int i4;
            float f;
            float f2;
            int i5;
            int[] iArr = this.mPathMode;
            if (i == 4) {
                boolean z = false;
                boolean z2 = false;
                for (int i6 = 0; i6 < this.mKeyFrameCount; i6++) {
                    int i7 = iArr[i6];
                    if (i7 == 1) {
                        z = true;
                    }
                    if (i7 == 0) {
                        z2 = true;
                    }
                }
                if (z) {
                    float[] fArr = this.mPoints;
                    canvas.drawLine(fArr[0], fArr[1], fArr[fArr.length - 2], fArr[fArr.length - 1], this.mPaintGraph);
                }
                if (z2) {
                    drawPathCartesian(canvas);
                }
            }
            if (i == 2) {
                float[] fArr2 = this.mPoints;
                canvas.drawLine(fArr2[0], fArr2[1], fArr2[fArr2.length - 2], fArr2[fArr2.length - 1], this.mPaintGraph);
            }
            if (i == 3) {
                drawPathCartesian(canvas);
            }
            canvas.drawLines(this.mPoints, this.mPaint);
            View view = motionController.mView;
            if (view != null) {
                i3 = view.getWidth();
                i4 = motionController.mView.getHeight();
            } else {
                i3 = 0;
                i4 = 0;
            }
            int i8 = 1;
            while (i8 < i2 - 1) {
                if (i == 4 && iArr[i8 - 1] == 0) {
                    i5 = i8;
                } else {
                    int i9 = i8 * 2;
                    float[] fArr3 = this.mKeyFramePoints;
                    float f3 = fArr3[i9];
                    float f4 = fArr3[i9 + 1];
                    this.mPath.reset();
                    this.mPath.moveTo(f3, f4 + 10.0f);
                    this.mPath.lineTo(f3 + 10.0f, f4);
                    this.mPath.lineTo(f3, f4 - 10.0f);
                    this.mPath.lineTo(f3 - 10.0f, f4);
                    this.mPath.close();
                    int i10 = i8 - 1;
                    if (i == 4) {
                        int i11 = iArr[i10];
                        if (i11 == 1) {
                            drawPathRelativeTicks(canvas, f3 - 0.0f, f4 - 0.0f);
                        } else if (i11 == 0) {
                            drawPathCartesianTicks(canvas, f3 - 0.0f, f4 - 0.0f);
                        } else if (i11 == 2) {
                            f = f4;
                            f2 = f3;
                            i5 = i8;
                            drawPathScreenTicks(canvas, f3 - 0.0f, f4 - 0.0f, i3, i4);
                            canvas.drawPath(this.mPath, this.mFillPaint);
                        }
                        f = f4;
                        f2 = f3;
                        i5 = i8;
                        canvas.drawPath(this.mPath, this.mFillPaint);
                    } else {
                        f = f4;
                        f2 = f3;
                        i5 = i8;
                    }
                    if (i == 2) {
                        drawPathRelativeTicks(canvas, f2 - 0.0f, f - 0.0f);
                    }
                    if (i == 3) {
                        drawPathCartesianTicks(canvas, f2 - 0.0f, f - 0.0f);
                    }
                    if (i == 6) {
                        drawPathScreenTicks(canvas, f2 - 0.0f, f - 0.0f, i3, i4);
                    }
                    canvas.drawPath(this.mPath, this.mFillPaint);
                }
                i8 = i5 + 1;
            }
            float[] fArr4 = this.mPoints;
            if (fArr4.length > 1) {
                canvas.drawCircle(fArr4[0], fArr4[1], 8.0f, this.mPaintKeyframes);
                float[] fArr5 = this.mPoints;
                canvas.drawCircle(fArr5[fArr5.length - 2], fArr5[fArr5.length - 1], 8.0f, this.mPaintKeyframes);
            }
        }

        public final void drawPathCartesian(Canvas canvas) {
            float[] fArr = this.mPoints;
            float f = fArr[0];
            float f2 = fArr[1];
            float f3 = fArr[fArr.length - 2];
            float f4 = fArr[fArr.length - 1];
            canvas.drawLine(Math.min(f, f3), Math.max(f2, f4), Math.max(f, f3), Math.max(f2, f4), this.mPaintGraph);
            canvas.drawLine(Math.min(f, f3), Math.min(f2, f4), Math.min(f, f3), Math.max(f2, f4), this.mPaintGraph);
        }

        public final void drawPathCartesianTicks(Canvas canvas, float f, float f2) {
            float[] fArr = this.mPoints;
            float f3 = fArr[0];
            float f4 = fArr[1];
            float f5 = fArr[fArr.length - 2];
            float f6 = fArr[fArr.length - 1];
            float min = Math.min(f3, f5);
            float max = Math.max(f4, f6);
            float min2 = f - Math.min(f3, f5);
            float max2 = Math.max(f4, f6) - f2;
            String str = "" + (((int) (((min2 * 100.0f) / Math.abs(f5 - f3)) + 0.5d)) / 100.0f);
            this.mTextPaint.getTextBounds(str, 0, str.length(), this.mBounds);
            canvas.drawText(str, ((min2 / 2.0f) - (this.mBounds.width() / 2)) + min, f2 - 20.0f, this.mTextPaint);
            canvas.drawLine(f, f2, Math.min(f3, f5), f2, this.mPaintGraph);
            String str2 = "" + (((int) (((max2 * 100.0f) / Math.abs(f6 - f4)) + 0.5d)) / 100.0f);
            this.mTextPaint.getTextBounds(str2, 0, str2.length(), this.mBounds);
            canvas.drawText(str2, f + 5.0f, max - ((max2 / 2.0f) - (this.mBounds.height() / 2)), this.mTextPaint);
            canvas.drawLine(f, f2, f, Math.max(f4, f6), this.mPaintGraph);
        }

        public final void drawPathRelativeTicks(Canvas canvas, float f, float f2) {
            float[] fArr = this.mPoints;
            float f3 = fArr[0];
            float f4 = fArr[1];
            float f5 = fArr[fArr.length - 2];
            float f6 = fArr[fArr.length - 1];
            float hypot = (float) Math.hypot(f3 - f5, f4 - f6);
            float f7 = f5 - f3;
            float f8 = f6 - f4;
            float f9 = (((f2 - f4) * f8) + ((f - f3) * f7)) / (hypot * hypot);
            float f10 = f3 + (f7 * f9);
            float f11 = f4 + (f9 * f8);
            Path path = new Path();
            path.moveTo(f, f2);
            path.lineTo(f10, f11);
            float hypot2 = (float) Math.hypot(f10 - f, f11 - f2);
            String str = "" + (((int) ((hypot2 * 100.0f) / hypot)) / 100.0f);
            this.mTextPaint.getTextBounds(str, 0, str.length(), this.mBounds);
            canvas.drawTextOnPath(str, path, (hypot2 / 2.0f) - (this.mBounds.width() / 2), -20.0f, this.mTextPaint);
            canvas.drawLine(f, f2, f10, f11, this.mPaintGraph);
        }

        public final void drawPathScreenTicks(Canvas canvas, float f, float f2, int i, int i2) {
            StringBuilder sb = new StringBuilder("");
            MotionLayout motionLayout = MotionLayout.this;
            sb.append(((int) ((((f - (i / 2)) * 100.0f) / (motionLayout.getWidth() - i)) + 0.5d)) / 100.0f);
            String sb2 = sb.toString();
            this.mTextPaint.getTextBounds(sb2, 0, sb2.length(), this.mBounds);
            canvas.drawText(sb2, ((f / 2.0f) - (this.mBounds.width() / 2)) + 0.0f, f2 - 20.0f, this.mTextPaint);
            canvas.drawLine(f, f2, Math.min(0.0f, 1.0f), f2, this.mPaintGraph);
            String str = "" + (((int) ((((f2 - (i2 / 2)) * 100.0f) / (motionLayout.getHeight() - i2)) + 0.5d)) / 100.0f);
            this.mTextPaint.getTextBounds(str, 0, str.length(), this.mBounds);
            canvas.drawText(str, f + 5.0f, 0.0f - ((f2 / 2.0f) - (this.mBounds.height() / 2)), this.mTextPaint);
            canvas.drawLine(f, f2, f, Math.max(0.0f, 1.0f), this.mPaintGraph);
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Model {
        public int mEndId;
        public int mStartId;
        public ConstraintWidgetContainer mLayoutStart = new ConstraintWidgetContainer();
        public ConstraintWidgetContainer mLayoutEnd = new ConstraintWidgetContainer();
        public ConstraintSet mStart = null;
        public ConstraintSet mEnd = null;

        public Model() {
        }

        public static void copy(ConstraintWidgetContainer constraintWidgetContainer, ConstraintWidgetContainer constraintWidgetContainer2) {
            ArrayList arrayList = constraintWidgetContainer.mChildren;
            HashMap hashMap = new HashMap();
            hashMap.put(constraintWidgetContainer, constraintWidgetContainer2);
            constraintWidgetContainer2.mChildren.clear();
            constraintWidgetContainer2.copy(constraintWidgetContainer, hashMap);
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                ConstraintWidget constraintWidget = (ConstraintWidget) it.next();
                ConstraintWidget barrier = constraintWidget instanceof Barrier ? new Barrier() : constraintWidget instanceof Guideline ? new Guideline() : constraintWidget instanceof Flow ? new Flow() : constraintWidget instanceof Placeholder ? new Placeholder() : constraintWidget instanceof HelperWidget ? new HelperWidget() : new ConstraintWidget();
                constraintWidgetContainer2.mChildren.add(barrier);
                ConstraintWidget constraintWidget2 = barrier.mParent;
                if (constraintWidget2 != null) {
                    ((ConstraintWidgetContainer) constraintWidget2).mChildren.remove(barrier);
                    barrier.reset();
                }
                barrier.mParent = constraintWidgetContainer2;
                hashMap.put(constraintWidget, barrier);
            }
            Iterator it2 = arrayList.iterator();
            while (it2.hasNext()) {
                ConstraintWidget constraintWidget3 = (ConstraintWidget) it2.next();
                ((ConstraintWidget) hashMap.get(constraintWidget3)).copy(constraintWidget3, hashMap);
            }
        }

        public static ConstraintWidget getWidget(ConstraintWidgetContainer constraintWidgetContainer, View view) {
            if (constraintWidgetContainer.mCompanionWidget == view) {
                return constraintWidgetContainer;
            }
            ArrayList arrayList = constraintWidgetContainer.mChildren;
            int size = arrayList.size();
            for (int i = 0; i < size; i++) {
                ConstraintWidget constraintWidget = (ConstraintWidget) arrayList.get(i);
                if (constraintWidget.mCompanionWidget == view) {
                    return constraintWidget;
                }
            }
            return null;
        }

        public final void build() {
            int i;
            SparseArray sparseArray;
            int[] iArr;
            int i2;
            Interpolator loadInterpolator;
            MotionLayout motionLayout = MotionLayout.this;
            int childCount = motionLayout.getChildCount();
            motionLayout.mFrameArrayList.clear();
            SparseArray sparseArray2 = new SparseArray();
            int[] iArr2 = new int[childCount];
            for (int i3 = 0; i3 < childCount; i3++) {
                View childAt = motionLayout.getChildAt(i3);
                MotionController motionController = new MotionController(childAt);
                int id = childAt.getId();
                iArr2[i3] = id;
                sparseArray2.put(id, motionController);
                motionLayout.mFrameArrayList.put(childAt, motionController);
            }
            int i4 = 0;
            while (i4 < childCount) {
                View childAt2 = motionLayout.getChildAt(i4);
                MotionController motionController2 = (MotionController) motionLayout.mFrameArrayList.get(childAt2);
                if (motionController2 == null) {
                    i = childCount;
                    sparseArray = sparseArray2;
                    iArr = iArr2;
                    i2 = i4;
                } else {
                    ConstraintSet constraintSet = this.mStart;
                    MotionPaths motionPaths = motionController2.mStartMotionPath;
                    if (constraintSet != null) {
                        ConstraintWidget widget = getWidget(this.mLayoutStart, childAt2);
                        if (widget != null) {
                            Rect access$2000 = MotionLayout.access$2000(motionLayout, widget);
                            ConstraintSet constraintSet2 = this.mStart;
                            int width = motionLayout.getWidth();
                            sparseArray = sparseArray2;
                            int height = motionLayout.getHeight();
                            iArr = iArr2;
                            int i5 = constraintSet2.mRotate;
                            i = childCount;
                            if (i5 != 0) {
                                MotionController.rotate(i5, width, height, access$2000, motionController2.mTempRect);
                            }
                            motionPaths.mTime = 0.0f;
                            motionPaths.mPosition = 0.0f;
                            motionController2.readView(motionPaths);
                            i2 = i4;
                            motionPaths.setBounds(access$2000.left, access$2000.top, access$2000.width(), access$2000.height());
                            ConstraintSet.Constraint constraint = constraintSet2.get(motionController2.mId);
                            motionPaths.applyParameters(constraint);
                            ConstraintSet.Motion motion = constraint.motion;
                            motionController2.mMotionStagger = motion.mMotionStagger;
                            motionController2.mStartPoint.setState(access$2000, constraintSet2, i5, motionController2.mId);
                            motionController2.mTransformPivotTarget = constraint.transform.transformPivotTarget;
                            motionController2.mQuantizeMotionSteps = motion.mQuantizeMotionSteps;
                            motionController2.mQuantizeMotionPhase = motion.mQuantizeMotionPhase;
                            Context context = motionController2.mView.getContext();
                            int i6 = motion.mQuantizeInterpolatorType;
                            String str = motion.mQuantizeInterpolatorString;
                            int i7 = motion.mQuantizeInterpolatorID;
                            if (i6 == -2) {
                                loadInterpolator = AnimationUtils.loadInterpolator(context, i7);
                            } else if (i6 != -1) {
                                loadInterpolator = i6 != 0 ? i6 != 1 ? i6 != 2 ? i6 != 4 ? i6 != 5 ? null : new OvershootInterpolator() : new BounceInterpolator() : new android.view.animation.DecelerateInterpolator() : new AccelerateInterpolator() : new AccelerateDecelerateInterpolator();
                            } else {
                                final Easing interpolator = Easing.getInterpolator(str);
                                loadInterpolator = new Interpolator() { // from class: androidx.constraintlayout.motion.widget.MotionController.1
                                    public AnonymousClass1() {
                                    }

                                    @Override // android.animation.TimeInterpolator
                                    public final float getInterpolation(float f) {
                                        return (float) Easing.this.get(f);
                                    }
                                };
                            }
                            motionController2.mQuantizeMotionInterpolator = loadInterpolator;
                        } else {
                            i = childCount;
                            sparseArray = sparseArray2;
                            iArr = iArr2;
                            i2 = i4;
                            if (motionLayout.mDebugPath != 0) {
                                Log.e("MotionLayout", Debug.getLocation() + "no widget for  " + Debug.getName(childAt2) + " (" + childAt2.getClass().getName() + ")");
                            }
                        }
                    } else {
                        i = childCount;
                        sparseArray = sparseArray2;
                        iArr = iArr2;
                        i2 = i4;
                    }
                    if (this.mEnd != null) {
                        ConstraintWidget widget2 = getWidget(this.mLayoutEnd, childAt2);
                        if (widget2 != null) {
                            Rect access$20002 = MotionLayout.access$2000(motionLayout, widget2);
                            ConstraintSet constraintSet3 = this.mEnd;
                            int width2 = motionLayout.getWidth();
                            int height2 = motionLayout.getHeight();
                            int i8 = constraintSet3.mRotate;
                            if (i8 != 0) {
                                MotionController.rotate(i8, width2, height2, access$20002, motionController2.mTempRect);
                                access$20002 = motionController2.mTempRect;
                            }
                            MotionPaths motionPaths2 = motionController2.mEndMotionPath;
                            motionPaths2.mTime = 1.0f;
                            motionPaths2.mPosition = 1.0f;
                            motionController2.readView(motionPaths2);
                            motionPaths2.setBounds(access$20002.left, access$20002.top, access$20002.width(), access$20002.height());
                            motionPaths2.applyParameters(constraintSet3.get(motionController2.mId));
                            motionController2.mEndPoint.setState(access$20002, constraintSet3, i8, motionController2.mId);
                        } else if (motionLayout.mDebugPath != 0) {
                            Log.e("MotionLayout", Debug.getLocation() + "no widget for  " + Debug.getName(childAt2) + " (" + childAt2.getClass().getName() + ")");
                        }
                    }
                }
                i4 = i2 + 1;
                sparseArray2 = sparseArray;
                iArr2 = iArr;
                childCount = i;
            }
            SparseArray sparseArray3 = sparseArray2;
            int[] iArr3 = iArr2;
            int i9 = childCount;
            int i10 = 0;
            while (i10 < i9) {
                SparseArray sparseArray4 = sparseArray3;
                MotionController motionController3 = (MotionController) sparseArray4.get(iArr3[i10]);
                int i11 = motionController3.mStartMotionPath.mAnimateRelativeTo;
                if (i11 != -1) {
                    MotionController motionController4 = (MotionController) sparseArray4.get(i11);
                    motionController3.mStartMotionPath.setupRelative(motionController4, motionController4.mStartMotionPath);
                    motionController3.mEndMotionPath.setupRelative(motionController4, motionController4.mEndMotionPath);
                }
                i10++;
                sparseArray3 = sparseArray4;
            }
        }

        public final void computeStartEndSize(int i, int i2) {
            MotionLayout motionLayout = MotionLayout.this;
            int i3 = motionLayout.mLayoutWidget.mOptimizationLevel;
            if (motionLayout.mCurrentState == motionLayout.mBeginState) {
                ConstraintWidgetContainer constraintWidgetContainer = this.mLayoutEnd;
                ConstraintSet constraintSet = this.mEnd;
                motionLayout.resolveSystem(constraintWidgetContainer, i3, (constraintSet == null || constraintSet.mRotate == 0) ? i : i2, (constraintSet == null || constraintSet.mRotate == 0) ? i2 : i);
                ConstraintSet constraintSet2 = this.mStart;
                if (constraintSet2 != null) {
                    ConstraintWidgetContainer constraintWidgetContainer2 = this.mLayoutStart;
                    int i4 = constraintSet2.mRotate;
                    int i5 = i4 == 0 ? i : i2;
                    if (i4 == 0) {
                        i = i2;
                    }
                    motionLayout.resolveSystem(constraintWidgetContainer2, i3, i5, i);
                    return;
                }
                return;
            }
            ConstraintSet constraintSet3 = this.mStart;
            if (constraintSet3 != null) {
                ConstraintWidgetContainer constraintWidgetContainer3 = this.mLayoutStart;
                int i6 = constraintSet3.mRotate;
                motionLayout.resolveSystem(constraintWidgetContainer3, i3, i6 == 0 ? i : i2, i6 == 0 ? i2 : i);
            }
            ConstraintWidgetContainer constraintWidgetContainer4 = this.mLayoutEnd;
            ConstraintSet constraintSet4 = this.mEnd;
            int i7 = (constraintSet4 == null || constraintSet4.mRotate == 0) ? i : i2;
            if (constraintSet4 == null || constraintSet4.mRotate == 0) {
                i = i2;
            }
            motionLayout.resolveSystem(constraintWidgetContainer4, i3, i7, i);
        }

        public final void initFrom(ConstraintSet constraintSet, ConstraintSet constraintSet2) {
            this.mStart = constraintSet;
            this.mEnd = constraintSet2;
            this.mLayoutStart = new ConstraintWidgetContainer();
            ConstraintWidgetContainer constraintWidgetContainer = new ConstraintWidgetContainer();
            this.mLayoutEnd = constraintWidgetContainer;
            ConstraintWidgetContainer constraintWidgetContainer2 = this.mLayoutStart;
            boolean z = MotionLayout.IS_IN_EDIT_MODE;
            MotionLayout motionLayout = MotionLayout.this;
            ConstraintWidgetContainer constraintWidgetContainer3 = motionLayout.mLayoutWidget;
            ConstraintLayout.Measurer measurer = constraintWidgetContainer3.mMeasurer;
            constraintWidgetContainer2.mMeasurer = measurer;
            constraintWidgetContainer2.mDependencyGraph.mMeasurer = measurer;
            ConstraintLayout.Measurer measurer2 = constraintWidgetContainer3.mMeasurer;
            constraintWidgetContainer.mMeasurer = measurer2;
            constraintWidgetContainer.mDependencyGraph.mMeasurer = measurer2;
            constraintWidgetContainer2.mChildren.clear();
            this.mLayoutEnd.mChildren.clear();
            copy(motionLayout.mLayoutWidget, this.mLayoutStart);
            copy(motionLayout.mLayoutWidget, this.mLayoutEnd);
            if (motionLayout.mTransitionLastPosition > 0.5d) {
                if (constraintSet != null) {
                    setupConstraintWidget(this.mLayoutStart, constraintSet);
                }
                setupConstraintWidget(this.mLayoutEnd, constraintSet2);
            } else {
                setupConstraintWidget(this.mLayoutEnd, constraintSet2);
                if (constraintSet != null) {
                    setupConstraintWidget(this.mLayoutStart, constraintSet);
                }
            }
            this.mLayoutStart.mIsRtl = motionLayout.isRtl$1();
            ConstraintWidgetContainer constraintWidgetContainer4 = this.mLayoutStart;
            constraintWidgetContainer4.mBasicMeasureSolver.updateHierarchy(constraintWidgetContainer4);
            this.mLayoutEnd.mIsRtl = motionLayout.isRtl$1();
            ConstraintWidgetContainer constraintWidgetContainer5 = this.mLayoutEnd;
            constraintWidgetContainer5.mBasicMeasureSolver.updateHierarchy(constraintWidgetContainer5);
            ViewGroup.LayoutParams layoutParams = motionLayout.getLayoutParams();
            if (layoutParams != null) {
                int i = layoutParams.width;
                ConstraintWidget.DimensionBehaviour dimensionBehaviour = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
                if (i == -2) {
                    this.mLayoutStart.setHorizontalDimensionBehaviour(dimensionBehaviour);
                    this.mLayoutEnd.setHorizontalDimensionBehaviour(dimensionBehaviour);
                }
                if (layoutParams.height == -2) {
                    this.mLayoutStart.setVerticalDimensionBehaviour(dimensionBehaviour);
                    this.mLayoutEnd.setVerticalDimensionBehaviour(dimensionBehaviour);
                }
            }
        }

        public final void reEvaluateState() {
            MotionLayout motionLayout = MotionLayout.this;
            int i = motionLayout.mLastWidthMeasureSpec;
            int i2 = motionLayout.mLastHeightMeasureSpec;
            int mode = View.MeasureSpec.getMode(i);
            int mode2 = View.MeasureSpec.getMode(i2);
            motionLayout.mWidthMeasureMode = mode;
            motionLayout.mHeightMeasureMode = mode2;
            computeStartEndSize(i, i2);
            int i3 = 0;
            if (!(motionLayout.getParent() instanceof MotionLayout) || mode != 1073741824 || mode2 != 1073741824) {
                computeStartEndSize(i, i2);
                motionLayout.mStartWrapWidth = this.mLayoutStart.getWidth();
                motionLayout.mStartWrapHeight = this.mLayoutStart.getHeight();
                motionLayout.mEndWrapWidth = this.mLayoutEnd.getWidth();
                int height = this.mLayoutEnd.getHeight();
                motionLayout.mEndWrapHeight = height;
                motionLayout.mMeasureDuringTransition = (motionLayout.mStartWrapWidth == motionLayout.mEndWrapWidth && motionLayout.mStartWrapHeight == height) ? false : true;
            }
            int i4 = motionLayout.mStartWrapWidth;
            int i5 = motionLayout.mStartWrapHeight;
            int i6 = motionLayout.mWidthMeasureMode;
            if (i6 == Integer.MIN_VALUE || i6 == 0) {
                i4 = (int) ((motionLayout.mPostInterpolationPosition * (motionLayout.mEndWrapWidth - i4)) + i4);
            }
            int i7 = i4;
            int i8 = motionLayout.mHeightMeasureMode;
            int i9 = (i8 == Integer.MIN_VALUE || i8 == 0) ? (int) ((motionLayout.mPostInterpolationPosition * (motionLayout.mEndWrapHeight - i5)) + i5) : i5;
            ConstraintWidgetContainer constraintWidgetContainer = this.mLayoutStart;
            motionLayout.resolveMeasuredDimension(i, i2, i7, i9, constraintWidgetContainer.mWidthMeasuredTooSmall || this.mLayoutEnd.mWidthMeasuredTooSmall, constraintWidgetContainer.mHeightMeasuredTooSmall || this.mLayoutEnd.mHeightMeasuredTooSmall);
            int childCount = motionLayout.getChildCount();
            motionLayout.mModel.build();
            motionLayout.mInTransition = true;
            SparseArray sparseArray = new SparseArray();
            for (int i10 = 0; i10 < childCount; i10++) {
                View childAt = motionLayout.getChildAt(i10);
                sparseArray.put(childAt.getId(), (MotionController) motionLayout.mFrameArrayList.get(childAt));
            }
            int width = motionLayout.getWidth();
            int height2 = motionLayout.getHeight();
            MotionScene.Transition transition = motionLayout.mScene.mCurrentTransition;
            int i11 = transition != null ? transition.mPathMotionArc : -1;
            if (i11 != -1) {
                for (int i12 = 0; i12 < childCount; i12++) {
                    MotionController motionController = (MotionController) motionLayout.mFrameArrayList.get(motionLayout.getChildAt(i12));
                    if (motionController != null) {
                        motionController.mPathMotionArc = i11;
                    }
                }
            }
            SparseBooleanArray sparseBooleanArray = new SparseBooleanArray();
            int[] iArr = new int[motionLayout.mFrameArrayList.size()];
            int i13 = 0;
            for (int i14 = 0; i14 < childCount; i14++) {
                MotionController motionController2 = (MotionController) motionLayout.mFrameArrayList.get(motionLayout.getChildAt(i14));
                int i15 = motionController2.mStartMotionPath.mAnimateRelativeTo;
                if (i15 != -1) {
                    sparseBooleanArray.put(i15, true);
                    iArr[i13] = motionController2.mStartMotionPath.mAnimateRelativeTo;
                    i13++;
                }
            }
            for (int i16 = 0; i16 < i13; i16++) {
                MotionController motionController3 = (MotionController) motionLayout.mFrameArrayList.get(motionLayout.findViewById(iArr[i16]));
                if (motionController3 != null) {
                    motionLayout.mScene.getKeyFrames(motionController3);
                    motionController3.setup(width, height2, System.nanoTime());
                }
            }
            for (int i17 = 0; i17 < childCount; i17++) {
                View childAt2 = motionLayout.getChildAt(i17);
                MotionController motionController4 = (MotionController) motionLayout.mFrameArrayList.get(childAt2);
                if (!sparseBooleanArray.get(childAt2.getId()) && motionController4 != null) {
                    motionLayout.mScene.getKeyFrames(motionController4);
                    motionController4.setup(width, height2, System.nanoTime());
                }
            }
            MotionScene.Transition transition2 = motionLayout.mScene.mCurrentTransition;
            float f = transition2 != null ? transition2.mStagger : 0.0f;
            if (f != 0.0f) {
                boolean z = ((double) f) < 0.0d;
                float abs = Math.abs(f);
                float f2 = -3.4028235E38f;
                float f3 = Float.MAX_VALUE;
                float f4 = -3.4028235E38f;
                float f5 = Float.MAX_VALUE;
                for (int i18 = 0; i18 < childCount; i18++) {
                    MotionController motionController5 = (MotionController) motionLayout.mFrameArrayList.get(motionLayout.getChildAt(i18));
                    if (!Float.isNaN(motionController5.mMotionStagger)) {
                        for (int i19 = 0; i19 < childCount; i19++) {
                            MotionController motionController6 = (MotionController) motionLayout.mFrameArrayList.get(motionLayout.getChildAt(i19));
                            if (!Float.isNaN(motionController6.mMotionStagger)) {
                                f3 = Math.min(f3, motionController6.mMotionStagger);
                                f2 = Math.max(f2, motionController6.mMotionStagger);
                            }
                        }
                        while (i3 < childCount) {
                            MotionController motionController7 = (MotionController) motionLayout.mFrameArrayList.get(motionLayout.getChildAt(i3));
                            if (!Float.isNaN(motionController7.mMotionStagger)) {
                                motionController7.mStaggerScale = 1.0f / (1.0f - abs);
                                if (z) {
                                    motionController7.mStaggerOffset = abs - (((f2 - motionController7.mMotionStagger) / (f2 - f3)) * abs);
                                } else {
                                    motionController7.mStaggerOffset = abs - (((motionController7.mMotionStagger - f3) * abs) / (f2 - f3));
                                }
                            }
                            i3++;
                        }
                        return;
                    }
                    MotionPaths motionPaths = motionController5.mEndMotionPath;
                    float f6 = motionPaths.mX;
                    float f7 = motionPaths.mY;
                    float f8 = z ? f7 - f6 : f7 + f6;
                    f5 = Math.min(f5, f8);
                    f4 = Math.max(f4, f8);
                }
                while (i3 < childCount) {
                    MotionController motionController8 = (MotionController) motionLayout.mFrameArrayList.get(motionLayout.getChildAt(i3));
                    MotionPaths motionPaths2 = motionController8.mEndMotionPath;
                    float f9 = motionPaths2.mX;
                    float f10 = motionPaths2.mY;
                    float f11 = z ? f10 - f9 : f10 + f9;
                    motionController8.mStaggerScale = 1.0f / (1.0f - abs);
                    motionController8.mStaggerOffset = abs - (((f11 - f5) * abs) / (f4 - f5));
                    i3++;
                }
            }
        }

        public final void setupConstraintWidget(ConstraintWidgetContainer constraintWidgetContainer, ConstraintSet constraintSet) {
            ConstraintSet.Constraint constraint;
            ConstraintSet.Constraint constraint2;
            SparseArray sparseArray = new SparseArray();
            Constraints$LayoutParams constraints$LayoutParams = new Constraints$LayoutParams(-2);
            sparseArray.clear();
            sparseArray.put(0, constraintWidgetContainer);
            MotionLayout motionLayout = MotionLayout.this;
            sparseArray.put(motionLayout.getId(), constraintWidgetContainer);
            if (constraintSet != null && constraintSet.mRotate != 0) {
                ConstraintWidgetContainer constraintWidgetContainer2 = this.mLayoutEnd;
                int i = motionLayout.mLayoutWidget.mOptimizationLevel;
                int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(motionLayout.getHeight(), 1073741824);
                int makeMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(motionLayout.getWidth(), 1073741824);
                boolean z = MotionLayout.IS_IN_EDIT_MODE;
                motionLayout.resolveSystem(constraintWidgetContainer2, i, makeMeasureSpec, makeMeasureSpec2);
            }
            Iterator it = constraintWidgetContainer.mChildren.iterator();
            while (it.hasNext()) {
                ConstraintWidget constraintWidget = (ConstraintWidget) it.next();
                constraintWidget.mAnimated = true;
                sparseArray.put(((View) constraintWidget.mCompanionWidget).getId(), constraintWidget);
            }
            Iterator it2 = constraintWidgetContainer.mChildren.iterator();
            while (it2.hasNext()) {
                ConstraintWidget constraintWidget2 = (ConstraintWidget) it2.next();
                View view = (View) constraintWidget2.mCompanionWidget;
                int id = view.getId();
                if (constraintSet.mConstraints.containsKey(Integer.valueOf(id)) && (constraint2 = (ConstraintSet.Constraint) constraintSet.mConstraints.get(Integer.valueOf(id))) != null) {
                    constraint2.applyTo(constraints$LayoutParams);
                }
                constraintWidget2.setWidth(constraintSet.getWidth(view.getId()));
                constraintWidget2.setHeight(constraintSet.getHeight(view.getId()));
                if (view instanceof ConstraintHelper) {
                    ConstraintHelper constraintHelper = (ConstraintHelper) view;
                    int id2 = constraintHelper.getId();
                    if (constraintSet.mConstraints.containsKey(Integer.valueOf(id2)) && (constraint = (ConstraintSet.Constraint) constraintSet.mConstraints.get(Integer.valueOf(id2))) != null && (constraintWidget2 instanceof HelperWidget)) {
                        constraintHelper.loadParameters(constraint, (HelperWidget) constraintWidget2, constraints$LayoutParams, sparseArray);
                    }
                    if (view instanceof androidx.constraintlayout.widget.Barrier) {
                        ((androidx.constraintlayout.widget.Barrier) view).validateParams();
                    }
                }
                constraints$LayoutParams.resolveLayoutDirection(motionLayout.getLayoutDirection());
                boolean z2 = MotionLayout.IS_IN_EDIT_MODE;
                motionLayout.applyConstraintsFromLayoutParams(false, view, constraintWidget2, constraints$LayoutParams, sparseArray);
                if (constraintSet.get(view.getId()).propertySet.mVisibilityMode == 1) {
                    constraintWidget2.mVisibility = view.getVisibility();
                } else {
                    constraintWidget2.mVisibility = constraintSet.get(view.getId()).propertySet.visibility;
                }
            }
            Iterator it3 = constraintWidgetContainer.mChildren.iterator();
            while (it3.hasNext()) {
                ConstraintWidget constraintWidget3 = (ConstraintWidget) it3.next();
                if (constraintWidget3 instanceof VirtualLayout) {
                    ConstraintHelper constraintHelper2 = (ConstraintHelper) constraintWidget3.mCompanionWidget;
                    HelperWidget helperWidget = (HelperWidget) constraintWidget3;
                    constraintHelper2.getClass();
                    helperWidget.mWidgetsCount = 0;
                    Arrays.fill(helperWidget.mWidgets, (Object) null);
                    for (int i2 = 0; i2 < constraintHelper2.mCount; i2++) {
                        helperWidget.add((ConstraintWidget) sparseArray.get(constraintHelper2.mIds[i2]));
                    }
                    VirtualLayout virtualLayout = (VirtualLayout) helperWidget;
                    for (int i3 = 0; i3 < virtualLayout.mWidgetsCount; i3++) {
                        ConstraintWidget constraintWidget4 = virtualLayout.mWidgets[i3];
                        if (constraintWidget4 != null) {
                            constraintWidget4.mInVirtualLayout = true;
                        }
                    }
                }
            }
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class MyTracker {
        public static final MyTracker sMe = new MyTracker();
        public VelocityTracker mTracker;
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class StateCache {
        public float mProgress = Float.NaN;
        public int mStartState = -1;
        public int mEndState = -1;

        public StateCache() {
        }
    }

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    final class TransitionState {
        public static final /* synthetic */ TransitionState[] $VALUES;
        public static final TransitionState FINISHED;
        public static final TransitionState MOVING;
        public static final TransitionState SETUP;
        public static final TransitionState UNDEFINED;

        static {
            TransitionState transitionState = new TransitionState("UNDEFINED", 0);
            UNDEFINED = transitionState;
            TransitionState transitionState2 = new TransitionState("SETUP", 1);
            SETUP = transitionState2;
            TransitionState transitionState3 = new TransitionState("MOVING", 2);
            MOVING = transitionState3;
            TransitionState transitionState4 = new TransitionState("FINISHED", 3);
            FINISHED = transitionState4;
            $VALUES = new TransitionState[]{transitionState, transitionState2, transitionState3, transitionState4};
        }

        public static TransitionState valueOf(String str) {
            return (TransitionState) Enum.valueOf(TransitionState.class, str);
        }

        public static TransitionState[] values() {
            return (TransitionState[]) $VALUES.clone();
        }
    }

    public MotionLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        MotionScene motionScene;
        this.mProgressInterpolator = null;
        this.mLastVelocity = 0.0f;
        this.mBeginState = -1;
        this.mCurrentState = -1;
        this.mEndState = -1;
        this.mLastWidthMeasureSpec = 0;
        this.mLastHeightMeasureSpec = 0;
        this.mInteractionEnabled = true;
        this.mFrameArrayList = new HashMap();
        this.mAnimationStartTime = 0L;
        this.mTransitionDuration = 1.0f;
        this.mTransitionPosition = 0.0f;
        this.mTransitionLastPosition = 0.0f;
        this.mTransitionGoalPosition = 0.0f;
        this.mInTransition = false;
        this.mDebugPath = 0;
        this.mTemporalInterpolator = false;
        this.mStopLogic = new StopLogic();
        this.mDecelerateLogic = new DecelerateInterpolator();
        this.mUndergoingMotion = false;
        this.mKeepAnimating = false;
        this.mFrames = 0;
        this.mLastDrawTime = -1L;
        this.mLastFps = 0.0f;
        this.mMeasureDuringTransition = false;
        this.mKeyCache = new KeyCache();
        this.mInLayout = false;
        this.mOnComplete = null;
        new HashMap();
        this.mTempRect = new Rect();
        this.mTransitionState = TransitionState.UNDEFINED;
        this.mModel = new Model();
        this.mNeedsFireTransitionCompleted = false;
        this.mBoundsCheck = new RectF();
        this.mRegionView = null;
        this.mInverseMatrix = null;
        new ArrayList();
        IS_IN_EDIT_MODE = isInEditMode();
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R$styleable.MotionLayout);
            int indexCount = obtainStyledAttributes.getIndexCount();
            boolean z = true;
            for (int i2 = 0; i2 < indexCount; i2++) {
                int index = obtainStyledAttributes.getIndex(i2);
                if (index == 2) {
                    this.mScene = new MotionScene(getContext(), this, obtainStyledAttributes.getResourceId(index, -1));
                } else if (index == 1) {
                    this.mCurrentState = obtainStyledAttributes.getResourceId(index, -1);
                } else if (index == 4) {
                    this.mTransitionGoalPosition = obtainStyledAttributes.getFloat(index, 0.0f);
                    this.mInTransition = true;
                } else if (index == 0) {
                    z = obtainStyledAttributes.getBoolean(index, z);
                } else if (index == 5) {
                    if (this.mDebugPath == 0) {
                        this.mDebugPath = obtainStyledAttributes.getBoolean(index, false) ? 2 : 0;
                    }
                } else if (index == 3) {
                    this.mDebugPath = obtainStyledAttributes.getInt(index, 0);
                }
            }
            obtainStyledAttributes.recycle();
            if (this.mScene == null) {
                Log.e("MotionLayout", "WARNING NO app:layoutDescription tag");
            }
            if (!z) {
                this.mScene = null;
            }
        }
        if (this.mDebugPath != 0) {
            MotionScene motionScene2 = this.mScene;
            if (motionScene2 == null) {
                Log.e("MotionLayout", "CHECK: motion scene not set! set \"app:layoutDescription=\"@xml/file\"");
            } else {
                int startId = motionScene2.getStartId();
                MotionScene motionScene3 = this.mScene;
                ConstraintSet constraintSet = motionScene3.getConstraintSet(motionScene3.getStartId());
                String name = Debug.getName(startId, getContext());
                int childCount = getChildCount();
                for (int i3 = 0; i3 < childCount; i3++) {
                    View childAt = getChildAt(i3);
                    int id = childAt.getId();
                    if (id == -1) {
                        StringBuilder m = ActivityResultRegistry$$ExternalSyntheticOutline0.m("CHECK: ", name, " ALL VIEWS SHOULD HAVE ID's ");
                        m.append(childAt.getClass().getName());
                        m.append(" does not!");
                        Log.w("MotionLayout", m.toString());
                    }
                    if (constraintSet.getConstraint(id) == null) {
                        StringBuilder m2 = ActivityResultRegistry$$ExternalSyntheticOutline0.m("CHECK: ", name, " NO CONSTRAINTS for ");
                        m2.append(Debug.getName(childAt));
                        Log.w("MotionLayout", m2.toString());
                    }
                }
                int[] knownIds = constraintSet.getKnownIds();
                for (int i4 = 0; i4 < knownIds.length; i4++) {
                    int i5 = knownIds[i4];
                    String name2 = Debug.getName(i5, getContext());
                    if (findViewById(knownIds[i4]) == null) {
                        Log.w("MotionLayout", "CHECK: " + name + " NO View matches id " + name2);
                    }
                    if (constraintSet.getHeight(i5) == -1) {
                        Log.w("MotionLayout", MotionLayout$$ExternalSyntheticOutline0.m("CHECK: ", name, "(", name2, ") no LAYOUT_HEIGHT"));
                    }
                    if (constraintSet.getWidth(i5) == -1) {
                        Log.w("MotionLayout", MotionLayout$$ExternalSyntheticOutline0.m("CHECK: ", name, "(", name2, ") no LAYOUT_HEIGHT"));
                    }
                }
                SparseIntArray sparseIntArray = new SparseIntArray();
                SparseIntArray sparseIntArray2 = new SparseIntArray();
                Iterator it = this.mScene.mTransitionList.iterator();
                while (it.hasNext()) {
                    MotionScene.Transition transition = (MotionScene.Transition) it.next();
                    MotionScene.Transition transition2 = this.mScene.mCurrentTransition;
                    if (transition.mConstraintSetStart == transition.mConstraintSetEnd) {
                        Log.e("MotionLayout", "CHECK: start and end constraint set should not be the same!");
                    }
                    int i6 = transition.mConstraintSetStart;
                    int i7 = transition.mConstraintSetEnd;
                    String name3 = Debug.getName(i6, getContext());
                    String name4 = Debug.getName(i7, getContext());
                    if (sparseIntArray.get(i6) == i7) {
                        Log.e("MotionLayout", "CHECK: two transitions with the same start and end " + name3 + "->" + name4);
                    }
                    if (sparseIntArray2.get(i7) == i6) {
                        Log.e("MotionLayout", "CHECK: you can't have reverse transitions" + name3 + "->" + name4);
                    }
                    sparseIntArray.put(i6, i7);
                    sparseIntArray2.put(i7, i6);
                    if (this.mScene.getConstraintSet(i6) == null) {
                        Log.e("MotionLayout", " no such constraintSetStart " + name3);
                    }
                    if (this.mScene.getConstraintSet(i7) == null) {
                        Log.e("MotionLayout", " no such constraintSetEnd " + name3);
                    }
                }
            }
        }
        if (this.mCurrentState != -1 || (motionScene = this.mScene) == null) {
            return;
        }
        this.mCurrentState = motionScene.getStartId();
        this.mBeginState = this.mScene.getStartId();
        MotionScene.Transition transition3 = this.mScene.mCurrentTransition;
        this.mEndState = transition3 != null ? transition3.mConstraintSetEnd : -1;
    }

    public static Rect access$2000(MotionLayout motionLayout, ConstraintWidget constraintWidget) {
        motionLayout.mTempRect.top = constraintWidget.getY();
        motionLayout.mTempRect.left = constraintWidget.getX();
        Rect rect = motionLayout.mTempRect;
        int width = constraintWidget.getWidth();
        Rect rect2 = motionLayout.mTempRect;
        rect.right = width + rect2.left;
        int height = constraintWidget.getHeight();
        Rect rect3 = motionLayout.mTempRect;
        rect2.bottom = height + rect3.top;
        return rect3;
    }

    public final void animateTo(float f) {
        MotionScene motionScene = this.mScene;
        if (motionScene == null) {
            return;
        }
        float f2 = this.mTransitionLastPosition;
        float f3 = this.mTransitionPosition;
        if (f2 != f3 && this.mTransitionInstantly) {
            this.mTransitionLastPosition = f3;
        }
        float f4 = this.mTransitionLastPosition;
        if (f4 == f) {
            return;
        }
        this.mTemporalInterpolator = false;
        this.mTransitionGoalPosition = f;
        this.mTransitionDuration = (motionScene.mCurrentTransition != null ? r3.mDuration : motionScene.mDefaultDuration) / 1000.0f;
        setProgress(f);
        Interpolator interpolator = null;
        this.mInterpolator = null;
        MotionScene motionScene2 = this.mScene;
        MotionScene.Transition transition = motionScene2.mCurrentTransition;
        int i = transition.mDefaultInterpolator;
        if (i == -2) {
            interpolator = AnimationUtils.loadInterpolator(motionScene2.mMotionLayout.getContext(), motionScene2.mCurrentTransition.mDefaultInterpolatorID);
        } else if (i == -1) {
            final Easing interpolator2 = Easing.getInterpolator(transition.mDefaultInterpolatorString);
            interpolator = new Interpolator() { // from class: androidx.constraintlayout.motion.widget.MotionScene.1
                @Override // android.animation.TimeInterpolator
                public final float getInterpolation(float f5) {
                    return (float) Easing.this.get(f5);
                }
            };
        } else if (i == 0) {
            interpolator = new AccelerateDecelerateInterpolator();
        } else if (i == 1) {
            interpolator = new AccelerateInterpolator();
        } else if (i == 2) {
            interpolator = new android.view.animation.DecelerateInterpolator();
        } else if (i == 4) {
            interpolator = new BounceInterpolator();
        } else if (i == 5) {
            interpolator = new OvershootInterpolator();
        } else if (i == 6) {
            interpolator = new AnticipateInterpolator();
        }
        this.mProgressInterpolator = interpolator;
        this.mTransitionInstantly = false;
        this.mAnimationStartTime = System.nanoTime();
        this.mInTransition = true;
        this.mTransitionPosition = f4;
        this.mTransitionLastPosition = f4;
        invalidate();
    }

    /* JADX WARN: Removed duplicated region for block: B:114:0x02ea  */
    /* JADX WARN: Removed duplicated region for block: B:129:0x0316  */
    /* JADX WARN: Removed duplicated region for block: B:136:0x0340  */
    /* JADX WARN: Removed duplicated region for block: B:141:0x036b  */
    /* JADX WARN: Removed duplicated region for block: B:143:0x0382  */
    /* JADX WARN: Removed duplicated region for block: B:146:0x038e  */
    /* JADX WARN: Removed duplicated region for block: B:150:0x0375  */
    @Override // androidx.constraintlayout.widget.ConstraintLayout, android.view.ViewGroup, android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void dispatchDraw(android.graphics.Canvas r30) {
        /*
            Method dump skipped, instructions count: 1275
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.motion.widget.MotionLayout.dispatchDraw(android.graphics.Canvas):void");
    }

    public final void endTrigger(boolean z) {
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            MotionController motionController = (MotionController) this.mFrameArrayList.get(getChildAt(i));
            if (motionController != null && "button".equals(Debug.getName(motionController.mView)) && motionController.mKeyTriggers != null) {
                int i2 = 0;
                while (true) {
                    KeyTrigger[] keyTriggerArr = motionController.mKeyTriggers;
                    if (i2 < keyTriggerArr.length) {
                        keyTriggerArr[i2].conditionallyFire(motionController.mView, z ? -100.0f : 100.0f);
                        i2++;
                    }
                }
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:100:0x0163  */
    /* JADX WARN: Removed duplicated region for block: B:109:0x0190  */
    /* JADX WARN: Removed duplicated region for block: B:114:0x01a7  */
    /* JADX WARN: Removed duplicated region for block: B:120:0x01b4  */
    /* JADX WARN: Removed duplicated region for block: B:123:0x01c1  */
    /* JADX WARN: Removed duplicated region for block: B:130:0x01e2  */
    /* JADX WARN: Removed duplicated region for block: B:135:0x01fb  */
    /* JADX WARN: Removed duplicated region for block: B:143:0x021b  */
    /* JADX WARN: Removed duplicated region for block: B:164:0x0144  */
    /* JADX WARN: Removed duplicated region for block: B:76:0x0106  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x010f  */
    /* JADX WARN: Removed duplicated region for block: B:94:0x0142  */
    /* JADX WARN: Removed duplicated region for block: B:97:0x014c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void evaluate(boolean r21) {
        /*
            Method dump skipped, instructions count: 616
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.motion.widget.MotionLayout.evaluate(boolean):void");
    }

    public final void getAnchorDpDt(int i, float f, float f2, float f3, float[] fArr) {
        double[] dArr;
        HashMap hashMap = this.mFrameArrayList;
        View viewById = getViewById(i);
        MotionController motionController = (MotionController) hashMap.get(viewById);
        if (motionController == null) {
            Log.w("MotionLayout", "WARNING could not find view id " + (viewById == null ? AnnotationValue$1$$ExternalSyntheticOutline0.m(i, "") : viewById.getContext().getResources().getResourceName(i)));
            return;
        }
        float[] fArr2 = motionController.mVelocity;
        float adjustedPosition = motionController.getAdjustedPosition(f, fArr2);
        CurveFit[] curveFitArr = motionController.mSpline;
        int i2 = 0;
        if (curveFitArr != null) {
            double d = adjustedPosition;
            curveFitArr[0].getSlope(d, motionController.mInterpolateVelocity);
            motionController.mSpline[0].getPos(d, motionController.mInterpolateData);
            float f4 = fArr2[0];
            while (true) {
                dArr = motionController.mInterpolateVelocity;
                if (i2 >= dArr.length) {
                    break;
                }
                dArr[i2] = dArr[i2] * f4;
                i2++;
            }
            ArcCurveFit arcCurveFit = motionController.mArcSpline;
            if (arcCurveFit != null) {
                double[] dArr2 = motionController.mInterpolateData;
                if (dArr2.length > 0) {
                    arcCurveFit.getPos(d, dArr2);
                    motionController.mArcSpline.getSlope(d, motionController.mInterpolateVelocity);
                    int[] iArr = motionController.mInterpolateVariables;
                    double[] dArr3 = motionController.mInterpolateVelocity;
                    double[] dArr4 = motionController.mInterpolateData;
                    motionController.mStartMotionPath.getClass();
                    MotionPaths.setDpDt(f2, f3, fArr, iArr, dArr3, dArr4);
                }
            } else {
                int[] iArr2 = motionController.mInterpolateVariables;
                double[] dArr5 = motionController.mInterpolateData;
                motionController.mStartMotionPath.getClass();
                MotionPaths.setDpDt(f2, f3, fArr, iArr2, dArr, dArr5);
            }
        } else {
            MotionPaths motionPaths = motionController.mEndMotionPath;
            float f5 = motionPaths.mX;
            MotionPaths motionPaths2 = motionController.mStartMotionPath;
            float f6 = f5 - motionPaths2.mX;
            float f7 = motionPaths.mY - motionPaths2.mY;
            float f8 = motionPaths.mWidth - motionPaths2.mWidth;
            float f9 = (motionPaths.mHeight - motionPaths2.mHeight) + f7;
            fArr[0] = ((f8 + f6) * f2) + ((1.0f - f2) * f6);
            fArr[1] = (f9 * f3) + ((1.0f - f3) * f7);
        }
        viewById.getY();
    }

    public final ConstraintSet getConstraintSet(int i) {
        MotionScene motionScene = this.mScene;
        if (motionScene == null) {
            return null;
        }
        return motionScene.getConstraintSet(i);
    }

    public final boolean handlesTouchEvent(float f, float f2, View view, MotionEvent motionEvent) {
        boolean z;
        boolean onTouchEvent;
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            for (int childCount = viewGroup.getChildCount() - 1; childCount >= 0; childCount--) {
                if (handlesTouchEvent((r3.getLeft() + f) - view.getScrollX(), (r3.getTop() + f2) - view.getScrollY(), viewGroup.getChildAt(childCount), motionEvent)) {
                    z = true;
                    break;
                }
            }
        }
        z = false;
        if (!z) {
            this.mBoundsCheck.set(f, f2, (view.getRight() + f) - view.getLeft(), (view.getBottom() + f2) - view.getTop());
            if (motionEvent.getAction() != 0 || this.mBoundsCheck.contains(motionEvent.getX(), motionEvent.getY())) {
                float f3 = -f;
                float f4 = -f2;
                Matrix matrix = view.getMatrix();
                if (matrix.isIdentity()) {
                    motionEvent.offsetLocation(f3, f4);
                    onTouchEvent = view.onTouchEvent(motionEvent);
                    motionEvent.offsetLocation(-f3, -f4);
                } else {
                    MotionEvent obtain = MotionEvent.obtain(motionEvent);
                    obtain.offsetLocation(f3, f4);
                    if (this.mInverseMatrix == null) {
                        this.mInverseMatrix = new Matrix();
                    }
                    matrix.invert(this.mInverseMatrix);
                    obtain.transform(this.mInverseMatrix);
                    onTouchEvent = view.onTouchEvent(obtain);
                    obtain.recycle();
                }
                if (onTouchEvent) {
                    return true;
                }
            }
        }
        return z;
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onAttachedToWindow() {
        MotionScene.Transition transition;
        int i;
        super.onAttachedToWindow();
        Display display = getDisplay();
        if (display != null) {
            display.getRotation();
        }
        MotionScene motionScene = this.mScene;
        if (motionScene != null && (i = this.mCurrentState) != -1) {
            ConstraintSet constraintSet = motionScene.getConstraintSet(i);
            MotionScene motionScene2 = this.mScene;
            loop0: for (int i2 = 0; i2 < motionScene2.mConstraintSetMap.size(); i2++) {
                int keyAt = motionScene2.mConstraintSetMap.keyAt(i2);
                int i3 = motionScene2.mDeriveMap.get(keyAt);
                int size = motionScene2.mDeriveMap.size();
                while (i3 > 0) {
                    if (i3 != keyAt) {
                        int i4 = size - 1;
                        if (size >= 0) {
                            i3 = motionScene2.mDeriveMap.get(i3);
                            size = i4;
                        }
                    }
                    Log.e("MotionScene", "Cannot be derived from yourself");
                    break loop0;
                }
                motionScene2.readConstraintChain(keyAt, this);
            }
            if (constraintSet != null) {
                constraintSet.applyTo(this);
            }
            this.mBeginState = this.mCurrentState;
        }
        onNewStateAttachHandlers();
        StateCache stateCache = this.mStateCache;
        TransitionState transitionState = TransitionState.MOVING;
        TransitionState transitionState2 = TransitionState.SETUP;
        if (stateCache == null) {
            MotionScene motionScene3 = this.mScene;
            if (motionScene3 == null || (transition = motionScene3.mCurrentTransition) == null || transition.mAutoTransition != 4) {
                return;
            }
            animateTo(1.0f);
            this.mOnComplete = null;
            setState(transitionState2);
            setState(transitionState);
            return;
        }
        int i5 = stateCache.mStartState;
        MotionLayout motionLayout = MotionLayout.this;
        if (i5 != -1 || stateCache.mEndState != -1) {
            if (i5 == -1) {
                motionLayout.transitionToState(stateCache.mEndState);
            } else {
                int i6 = stateCache.mEndState;
                if (i6 == -1) {
                    motionLayout.setState(i5);
                } else {
                    motionLayout.setTransition(i5, i6);
                }
            }
            motionLayout.setState(transitionState2);
        }
        if (Float.isNaN(Float.NaN)) {
            if (Float.isNaN(stateCache.mProgress)) {
                return;
            }
            motionLayout.setProgress(stateCache.mProgress);
            return;
        }
        float f = stateCache.mProgress;
        if (motionLayout.isAttachedToWindow()) {
            motionLayout.setProgress(f);
            motionLayout.setState(transitionState);
            motionLayout.mLastVelocity = Float.NaN;
            motionLayout.animateTo(0.0f);
        } else {
            if (motionLayout.mStateCache == null) {
                motionLayout.mStateCache = motionLayout.new StateCache();
            }
            motionLayout.mStateCache.mProgress = f;
        }
        stateCache.mProgress = Float.NaN;
        stateCache.mStartState = -1;
        stateCache.mEndState = -1;
    }

    /* JADX WARN: Removed duplicated region for block: B:62:0x00e8  */
    @Override // android.view.ViewGroup
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean onInterceptTouchEvent(android.view.MotionEvent r22) {
        /*
            Method dump skipped, instructions count: 440
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.motion.widget.MotionLayout.onInterceptTouchEvent(android.view.MotionEvent):boolean");
    }

    @Override // androidx.constraintlayout.widget.ConstraintLayout, android.view.ViewGroup, android.view.View
    public final void onLayout(boolean z, int i, int i2, int i3, int i4) {
        this.mInLayout = true;
        try {
            if (this.mScene == null) {
                super.onLayout(z, i, i2, i3, i4);
                return;
            }
            int i5 = i3 - i;
            int i6 = i4 - i2;
            if (this.mLastLayoutWidth != i5 || this.mLastLayoutHeight != i6) {
                rebuildScene();
                evaluate(true);
            }
            this.mLastLayoutWidth = i5;
            this.mLastLayoutHeight = i6;
        } finally {
            this.mInLayout = false;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:24:0x004a, code lost:
    
        if (r7 == r9.mEndId) goto L31;
     */
    /* JADX WARN: Removed duplicated region for block: B:33:0x00e3  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x00f6  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x0118  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x0122  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x0149  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x014f  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x016e  */
    /* JADX WARN: Removed duplicated region for block: B:68:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:78:0x00ee  */
    @Override // androidx.constraintlayout.widget.ConstraintLayout, android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void onMeasure(int r18, int r19) {
        /*
            Method dump skipped, instructions count: 370
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.motion.widget.MotionLayout.onMeasure(int, int):void");
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final boolean onNestedFling(View view, float f, float f2, boolean z) {
        return false;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final boolean onNestedPreFling(View view, float f, float f2) {
        return false;
    }

    /* JADX WARN: Type inference failed for: r1v3 */
    /* JADX WARN: Type inference failed for: r1v4, types: [boolean] */
    /* JADX WARN: Type inference failed for: r1v5 */
    @Override // androidx.core.view.NestedScrollingParent2
    public final void onNestedPreScroll(final View view, int i, int i2, int[] iArr, int i3) {
        MotionScene.Transition transition;
        boolean z;
        ?? r1;
        TouchResponse touchResponse;
        float f;
        TouchResponse touchResponse2;
        TouchResponse touchResponse3;
        TouchResponse touchResponse4;
        int i4;
        MotionScene motionScene = this.mScene;
        if (motionScene == null || (transition = motionScene.mCurrentTransition) == null || (z = transition.mDisable)) {
            return;
        }
        int i5 = -1;
        if (z || (touchResponse4 = transition.mTouchResponse) == null || (i4 = touchResponse4.mTouchRegionId) == -1 || view.getId() == i4) {
            MotionScene.Transition transition2 = motionScene.mCurrentTransition;
            if ((transition2 == null || (touchResponse3 = transition2.mTouchResponse) == null) ? false : touchResponse3.mMoveWhenScrollAtTop) {
                TouchResponse touchResponse5 = transition.mTouchResponse;
                if (touchResponse5 != null && (touchResponse5.mFlags & 4) != 0) {
                    i5 = i2;
                }
                float f2 = this.mTransitionPosition;
                if ((f2 == 1.0f || f2 == 0.0f) && view.canScrollVertically(i5)) {
                    return;
                }
            }
            TouchResponse touchResponse6 = transition.mTouchResponse;
            if (touchResponse6 != null && (touchResponse6.mFlags & 1) != 0) {
                float f3 = i;
                float f4 = i2;
                MotionScene.Transition transition3 = motionScene.mCurrentTransition;
                if (transition3 == null || (touchResponse2 = transition3.mTouchResponse) == null) {
                    f = 0.0f;
                } else {
                    MotionLayout motionLayout = touchResponse2.mMotionLayout;
                    motionLayout.getAnchorDpDt(touchResponse2.mTouchAnchorId, motionLayout.mTransitionLastPosition, touchResponse2.mTouchAnchorX, touchResponse2.mTouchAnchorY, touchResponse2.mAnchorDpDt);
                    float f5 = touchResponse2.mTouchDirectionX;
                    float[] fArr = touchResponse2.mAnchorDpDt;
                    if (f5 != 0.0f) {
                        if (fArr[0] == 0.0f) {
                            fArr[0] = 1.0E-7f;
                        }
                        f = (f3 * f5) / fArr[0];
                    } else {
                        if (fArr[1] == 0.0f) {
                            fArr[1] = 1.0E-7f;
                        }
                        f = (f4 * touchResponse2.mTouchDirectionY) / fArr[1];
                    }
                }
                float f6 = this.mTransitionLastPosition;
                if ((f6 <= 0.0f && f < 0.0f) || (f6 >= 1.0f && f > 0.0f)) {
                    view.setNestedScrollingEnabled(false);
                    view.post(new Runnable() { // from class: androidx.constraintlayout.motion.widget.MotionLayout.3
                        @Override // java.lang.Runnable
                        public final void run() {
                            view.setNestedScrollingEnabled(true);
                        }
                    });
                    return;
                }
            }
            float f7 = this.mTransitionPosition;
            long nanoTime = System.nanoTime();
            float f8 = i;
            this.mScrollTargetDX = f8;
            float f9 = i2;
            this.mScrollTargetDY = f9;
            this.mScrollTargetDT = (float) ((nanoTime - this.mScrollTargetTime) * 1.0E-9d);
            this.mScrollTargetTime = nanoTime;
            MotionScene.Transition transition4 = motionScene.mCurrentTransition;
            if (transition4 != null && (touchResponse = transition4.mTouchResponse) != null) {
                MotionLayout motionLayout2 = touchResponse.mMotionLayout;
                float f10 = motionLayout2.mTransitionLastPosition;
                if (!touchResponse.mDragStarted) {
                    touchResponse.mDragStarted = true;
                    motionLayout2.setProgress(f10);
                }
                touchResponse.mMotionLayout.getAnchorDpDt(touchResponse.mTouchAnchorId, f10, touchResponse.mTouchAnchorX, touchResponse.mTouchAnchorY, touchResponse.mAnchorDpDt);
                float f11 = touchResponse.mTouchDirectionX;
                float[] fArr2 = touchResponse.mAnchorDpDt;
                if (Math.abs((touchResponse.mTouchDirectionY * fArr2[1]) + (f11 * fArr2[0])) < 0.01d) {
                    fArr2[0] = 0.01f;
                    fArr2[1] = 0.01f;
                }
                float f12 = touchResponse.mTouchDirectionX;
                float max = Math.max(Math.min(f10 + (f12 != 0.0f ? (f8 * f12) / fArr2[0] : (f9 * touchResponse.mTouchDirectionY) / fArr2[1]), 1.0f), 0.0f);
                if (max != motionLayout2.mTransitionLastPosition) {
                    motionLayout2.setProgress(max);
                }
            }
            if (f7 != this.mTransitionPosition) {
                iArr[0] = i;
                r1 = 1;
                iArr[1] = i2;
            } else {
                r1 = 1;
            }
            evaluate(false);
            if (iArr[0] == 0 && iArr[r1] == 0) {
                return;
            }
            this.mUndergoingMotion = r1;
        }
    }

    @Override // androidx.core.view.NestedScrollingParent2
    public final void onNestedScroll(View view, int i, int i2, int i3, int i4, int i5) {
    }

    @Override // androidx.core.view.NestedScrollingParent2
    public final void onNestedScrollAccepted(View view, View view2, int i, int i2) {
        this.mScrollTargetTime = System.nanoTime();
        this.mScrollTargetDT = 0.0f;
        this.mScrollTargetDX = 0.0f;
        this.mScrollTargetDY = 0.0f;
    }

    public final void onNewStateAttachHandlers() {
        MotionScene.Transition transition;
        TouchResponse touchResponse;
        View view;
        MotionScene motionScene = this.mScene;
        if (motionScene == null) {
            return;
        }
        if (motionScene.autoTransition(this.mCurrentState, this)) {
            requestLayout();
            return;
        }
        int i = this.mCurrentState;
        if (i != -1) {
            MotionScene motionScene2 = this.mScene;
            Iterator it = motionScene2.mTransitionList.iterator();
            while (it.hasNext()) {
                MotionScene.Transition transition2 = (MotionScene.Transition) it.next();
                if (transition2.mOnClicks.size() > 0) {
                    Iterator it2 = transition2.mOnClicks.iterator();
                    while (it2.hasNext()) {
                        ((MotionScene.Transition.TransitionOnClick) it2.next()).removeOnClickListeners(this);
                    }
                }
            }
            Iterator it3 = motionScene2.mAbstractTransitionList.iterator();
            while (it3.hasNext()) {
                MotionScene.Transition transition3 = (MotionScene.Transition) it3.next();
                if (transition3.mOnClicks.size() > 0) {
                    Iterator it4 = transition3.mOnClicks.iterator();
                    while (it4.hasNext()) {
                        ((MotionScene.Transition.TransitionOnClick) it4.next()).removeOnClickListeners(this);
                    }
                }
            }
            Iterator it5 = motionScene2.mTransitionList.iterator();
            while (it5.hasNext()) {
                MotionScene.Transition transition4 = (MotionScene.Transition) it5.next();
                if (transition4.mOnClicks.size() > 0) {
                    Iterator it6 = transition4.mOnClicks.iterator();
                    while (it6.hasNext()) {
                        ((MotionScene.Transition.TransitionOnClick) it6.next()).addOnClickListeners(this, i, transition4);
                    }
                }
            }
            Iterator it7 = motionScene2.mAbstractTransitionList.iterator();
            while (it7.hasNext()) {
                MotionScene.Transition transition5 = (MotionScene.Transition) it7.next();
                if (transition5.mOnClicks.size() > 0) {
                    Iterator it8 = transition5.mOnClicks.iterator();
                    while (it8.hasNext()) {
                        ((MotionScene.Transition.TransitionOnClick) it8.next()).addOnClickListeners(this, i, transition5);
                    }
                }
            }
        }
        if (!this.mScene.supportTouch() || (transition = this.mScene.mCurrentTransition) == null || (touchResponse = transition.mTouchResponse) == null) {
            return;
        }
        int i2 = touchResponse.mTouchAnchorId;
        if (i2 != -1) {
            MotionLayout motionLayout = touchResponse.mMotionLayout;
            view = motionLayout.findViewById(i2);
            if (view == null) {
                Log.e("TouchResponse", "cannot find TouchAnchorId @id/" + Debug.getName(touchResponse.mTouchAnchorId, motionLayout.getContext()));
            }
        } else {
            view = null;
        }
        if (view instanceof NestedScrollView) {
            NestedScrollView nestedScrollView = (NestedScrollView) view;
            nestedScrollView.setOnTouchListener(new TouchResponse.AnonymousClass1());
            nestedScrollView.mOnScrollChangeListener = new TouchResponse.AnonymousClass2();
        }
    }

    @Override // android.view.View
    public final void onRtlPropertiesChanged(int i) {
        TouchResponse touchResponse;
        MotionScene motionScene = this.mScene;
        if (motionScene != null) {
            boolean isRtl$1 = isRtl$1();
            motionScene.mRtl = isRtl$1;
            MotionScene.Transition transition = motionScene.mCurrentTransition;
            if (transition == null || (touchResponse = transition.mTouchResponse) == null) {
                return;
            }
            touchResponse.setRTL(isRtl$1);
        }
    }

    @Override // androidx.core.view.NestedScrollingParent2
    public final boolean onStartNestedScroll(View view, View view2, int i, int i2) {
        MotionScene.Transition transition;
        TouchResponse touchResponse;
        MotionScene motionScene = this.mScene;
        return (motionScene == null || (transition = motionScene.mCurrentTransition) == null || (touchResponse = transition.mTouchResponse) == null || (touchResponse.mFlags & 2) != 0) ? false : true;
    }

    @Override // androidx.core.view.NestedScrollingParent2
    public final void onStopNestedScroll(View view, int i) {
        TouchResponse touchResponse;
        int i2;
        MotionScene motionScene = this.mScene;
        if (motionScene != null) {
            float f = this.mScrollTargetDT;
            if (f == 0.0f) {
                return;
            }
            float f2 = this.mScrollTargetDX / f;
            float f3 = this.mScrollTargetDY / f;
            MotionScene.Transition transition = motionScene.mCurrentTransition;
            if (transition == null || (touchResponse = transition.mTouchResponse) == null) {
                return;
            }
            touchResponse.mDragStarted = false;
            MotionLayout motionLayout = touchResponse.mMotionLayout;
            float f4 = motionLayout.mTransitionLastPosition;
            motionLayout.getAnchorDpDt(touchResponse.mTouchAnchorId, f4, touchResponse.mTouchAnchorX, touchResponse.mTouchAnchorY, touchResponse.mAnchorDpDt);
            float f5 = touchResponse.mTouchDirectionX;
            float[] fArr = touchResponse.mAnchorDpDt;
            float f6 = f5 != 0.0f ? (f2 * f5) / fArr[0] : (f3 * touchResponse.mTouchDirectionY) / fArr[1];
            if (!Float.isNaN(f6)) {
                f4 += f6 / 3.0f;
            }
            if (f4 == 0.0f || f4 == 1.0f || (i2 = touchResponse.mOnTouchUp) == 3) {
                return;
            }
            motionLayout.touchAnimateTo(i2, ((double) f4) >= 0.5d ? 1.0f : 0.0f, f6);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:221:0x04bd  */
    /* JADX WARN: Removed duplicated region for block: B:224:0x04fa  */
    /* JADX WARN: Removed duplicated region for block: B:258:0x0506  */
    /* JADX WARN: Removed duplicated region for block: B:259:0x04e0  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x07cb  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x07d0 A[RETURN] */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean onTouchEvent(android.view.MotionEvent r35) {
        /*
            Method dump skipped, instructions count: 2007
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.motion.widget.MotionLayout.onTouchEvent(android.view.MotionEvent):boolean");
    }

    @Override // androidx.constraintlayout.widget.ConstraintLayout
    public final void parseLayoutDescription(int i) {
        this.mConstraintLayoutSpec = null;
    }

    public final void rebuildScene() {
        this.mModel.reEvaluateState();
        invalidate();
    }

    @Override // androidx.constraintlayout.widget.ConstraintLayout, android.view.View, android.view.ViewParent
    public final void requestLayout() {
        MotionScene motionScene;
        MotionScene.Transition transition;
        if (!this.mMeasureDuringTransition && this.mCurrentState == -1 && (motionScene = this.mScene) != null && (transition = motionScene.mCurrentTransition) != null) {
            int i = transition.mLayoutDuringTransition;
            if (i == 0) {
                return;
            }
            if (i == 2) {
                int childCount = getChildCount();
                for (int i2 = 0; i2 < childCount; i2++) {
                    ((MotionController) this.mFrameArrayList.get(getChildAt(i2))).mForceMeasure = true;
                }
                return;
            }
        }
        super.requestLayout();
    }

    public final void setProgress(float f) {
        if (f < 0.0f || f > 1.0f) {
            Log.w("MotionLayout", "Warning! Progress is defined for values between 0.0 and 1.0 inclusive");
        }
        if (!isAttachedToWindow()) {
            if (this.mStateCache == null) {
                this.mStateCache = new StateCache();
            }
            this.mStateCache.mProgress = f;
            return;
        }
        TransitionState transitionState = TransitionState.FINISHED;
        TransitionState transitionState2 = TransitionState.MOVING;
        if (f <= 0.0f) {
            if (this.mTransitionLastPosition == 1.0f && this.mCurrentState == this.mEndState) {
                setState(transitionState2);
            }
            this.mCurrentState = this.mBeginState;
            if (this.mTransitionLastPosition == 0.0f) {
                setState(transitionState);
            }
        } else if (f >= 1.0f) {
            if (this.mTransitionLastPosition == 0.0f && this.mCurrentState == this.mBeginState) {
                setState(transitionState2);
            }
            this.mCurrentState = this.mEndState;
            if (this.mTransitionLastPosition == 1.0f) {
                setState(transitionState);
            }
        } else {
            this.mCurrentState = -1;
            setState(transitionState2);
        }
        if (this.mScene == null) {
            return;
        }
        this.mTransitionInstantly = true;
        this.mTransitionGoalPosition = f;
        this.mTransitionPosition = f;
        this.mTransitionLastTime = -1L;
        this.mAnimationStartTime = -1L;
        this.mInterpolator = null;
        this.mInTransition = true;
        invalidate();
    }

    public final void setState(TransitionState transitionState) {
        ViewTransition$$ExternalSyntheticLambda0 viewTransition$$ExternalSyntheticLambda0;
        ViewTransition$$ExternalSyntheticLambda0 viewTransition$$ExternalSyntheticLambda02;
        TransitionState transitionState2 = TransitionState.FINISHED;
        if (transitionState == transitionState2 && this.mCurrentState == -1) {
            return;
        }
        TransitionState transitionState3 = this.mTransitionState;
        this.mTransitionState = transitionState;
        int ordinal = transitionState3.ordinal();
        if (ordinal == 0 || ordinal == 1) {
            if (transitionState != transitionState2 || (viewTransition$$ExternalSyntheticLambda0 = this.mOnComplete) == null) {
                return;
            }
            viewTransition$$ExternalSyntheticLambda0.run();
            this.mOnComplete = null;
            return;
        }
        if (ordinal == 2 && transitionState == transitionState2 && (viewTransition$$ExternalSyntheticLambda02 = this.mOnComplete) != null) {
            viewTransition$$ExternalSyntheticLambda02.run();
            this.mOnComplete = null;
        }
    }

    public final void setTransition(int i, int i2) {
        if (!isAttachedToWindow()) {
            if (this.mStateCache == null) {
                this.mStateCache = new StateCache();
            }
            StateCache stateCache = this.mStateCache;
            stateCache.mStartState = i;
            stateCache.mEndState = i2;
            return;
        }
        MotionScene motionScene = this.mScene;
        if (motionScene != null) {
            this.mBeginState = i;
            this.mEndState = i2;
            motionScene.setTransition(i, i2);
            this.mModel.initFrom(this.mScene.getConstraintSet(i), this.mScene.getConstraintSet(i2));
            rebuildScene();
            this.mTransitionLastPosition = 0.0f;
            animateTo(0.0f);
        }
    }

    @Override // android.view.View
    public final String toString() {
        Context context = getContext();
        return Debug.getName(this.mBeginState, context) + "->" + Debug.getName(this.mEndState, context) + " (pos:" + this.mTransitionLastPosition + " Dpos/Dt:" + this.mLastVelocity;
    }

    /* JADX WARN: Code restructure failed: missing block: B:20:0x0046, code lost:
    
        if (r16 != 7) goto L89;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x0061, code lost:
    
        if ((((r18 * r3) - (((r2 * r3) * r3) / 2.0f)) + r1) > 1.0f) goto L30;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x0087, code lost:
    
        r1 = r15.mStopLogic;
        r2 = r15.mTransitionLastPosition;
        r5 = r15.mTransitionDuration;
        r6 = r15.mScene.getMaxAcceleration();
        r3 = r15.mScene.mCurrentTransition;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x0097, code lost:
    
        if (r3 == null) goto L36;
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x0099, code lost:
    
        r3 = r3.mTouchResponse;
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x009b, code lost:
    
        if (r3 == null) goto L36;
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x009d, code lost:
    
        r7 = r3.mMaxVelocity;
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x00a2, code lost:
    
        r1.config(r2, r17, r18, r5, r6, r7);
        r15.mLastVelocity = 0.0f;
        r1 = r15.mCurrentState;
        r15.mTransitionGoalPosition = r8;
        r15.mCurrentState = r1;
        r15.mInterpolator = r15.mStopLogic;
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x00a1, code lost:
    
        r7 = 0.0f;
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x0071, code lost:
    
        r1 = r15.mDecelerateLogic;
        r2 = r15.mTransitionLastPosition;
        r3 = r15.mScene.getMaxAcceleration();
        r1.mInitialV = r18;
        r1.mCurrentP = r2;
        r1.mMaxA = r3;
        r15.mInterpolator = r15.mDecelerateLogic;
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x006f, code lost:
    
        if ((((((r2 * r3) * r3) / 2.0f) + (r18 * r3)) + r1) < 0.0f) goto L30;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void touchAnimateTo(int r16, float r17, float r18) {
        /*
            Method dump skipped, instructions count: 375
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.motion.widget.MotionLayout.touchAnimateTo(int, float, float):void");
    }

    public final void transitionToState(int i) {
        StateSet stateSet;
        if (!isAttachedToWindow()) {
            if (this.mStateCache == null) {
                this.mStateCache = new StateCache();
            }
            this.mStateCache.mEndState = i;
            return;
        }
        MotionScene motionScene = this.mScene;
        if (motionScene != null && (stateSet = motionScene.mStateSet) != null) {
            int i2 = this.mCurrentState;
            float f = -1;
            StateSet.State state = (StateSet.State) stateSet.mStateList.get(i);
            if (state == null) {
                i2 = i;
            } else {
                int i3 = state.mConstraintID;
                if (f != -1.0f && f != -1.0f) {
                    Iterator it = state.mVariants.iterator();
                    StateSet.Variant variant = null;
                    while (true) {
                        if (it.hasNext()) {
                            StateSet.Variant variant2 = (StateSet.Variant) it.next();
                            if (variant2.match(f, f)) {
                                if (i2 == variant2.mConstraintID) {
                                    break;
                                } else {
                                    variant = variant2;
                                }
                            }
                        } else if (variant != null) {
                            i2 = variant.mConstraintID;
                        }
                    }
                } else if (i3 != i2) {
                    Iterator it2 = state.mVariants.iterator();
                    while (it2.hasNext()) {
                        if (i2 == ((StateSet.Variant) it2.next()).mConstraintID) {
                            break;
                        }
                    }
                    i2 = i3;
                }
            }
            if (i2 != -1) {
                i = i2;
            }
        }
        int i4 = this.mCurrentState;
        if (i4 == i) {
            return;
        }
        if (this.mBeginState == i) {
            animateTo(0.0f);
            return;
        }
        if (this.mEndState == i) {
            animateTo(1.0f);
            return;
        }
        this.mEndState = i;
        if (i4 != -1) {
            setTransition(i4, i);
            animateTo(1.0f);
            this.mTransitionLastPosition = 0.0f;
            animateTo(1.0f);
            this.mOnComplete = null;
            return;
        }
        this.mTemporalInterpolator = false;
        this.mTransitionGoalPosition = 1.0f;
        this.mTransitionPosition = 0.0f;
        this.mTransitionLastPosition = 0.0f;
        this.mTransitionLastTime = System.nanoTime();
        this.mAnimationStartTime = System.nanoTime();
        this.mTransitionInstantly = false;
        this.mInterpolator = null;
        MotionScene motionScene2 = this.mScene;
        this.mTransitionDuration = (motionScene2.mCurrentTransition != null ? r6.mDuration : motionScene2.mDefaultDuration) / 1000.0f;
        this.mBeginState = -1;
        motionScene2.setTransition(-1, this.mEndState);
        SparseArray sparseArray = new SparseArray();
        int childCount = getChildCount();
        this.mFrameArrayList.clear();
        for (int i5 = 0; i5 < childCount; i5++) {
            View childAt = getChildAt(i5);
            this.mFrameArrayList.put(childAt, new MotionController(childAt));
            sparseArray.put(childAt.getId(), (MotionController) this.mFrameArrayList.get(childAt));
        }
        this.mInTransition = true;
        this.mModel.initFrom(null, this.mScene.getConstraintSet(i));
        rebuildScene();
        this.mModel.build();
        int childCount2 = getChildCount();
        for (int i6 = 0; i6 < childCount2; i6++) {
            View childAt2 = getChildAt(i6);
            MotionController motionController = (MotionController) this.mFrameArrayList.get(childAt2);
            if (motionController != null) {
                MotionPaths motionPaths = motionController.mStartMotionPath;
                motionPaths.mTime = 0.0f;
                motionPaths.mPosition = 0.0f;
                motionPaths.setBounds(childAt2.getX(), childAt2.getY(), childAt2.getWidth(), childAt2.getHeight());
                MotionConstrainedPoint motionConstrainedPoint = motionController.mStartPoint;
                motionConstrainedPoint.getClass();
                childAt2.getX();
                childAt2.getY();
                childAt2.getWidth();
                childAt2.getHeight();
                motionConstrainedPoint.mVisibility = childAt2.getVisibility();
                motionConstrainedPoint.mAlpha = childAt2.getVisibility() != 0 ? 0.0f : childAt2.getAlpha();
                motionConstrainedPoint.mElevation = childAt2.getElevation();
                motionConstrainedPoint.mRotation = childAt2.getRotation();
                motionConstrainedPoint.mRotationX = childAt2.getRotationX();
                motionConstrainedPoint.rotationY = childAt2.getRotationY();
                motionConstrainedPoint.mScaleX = childAt2.getScaleX();
                motionConstrainedPoint.mScaleY = childAt2.getScaleY();
                motionConstrainedPoint.mPivotX = childAt2.getPivotX();
                motionConstrainedPoint.mPivotY = childAt2.getPivotY();
                motionConstrainedPoint.mTranslationX = childAt2.getTranslationX();
                motionConstrainedPoint.mTranslationY = childAt2.getTranslationY();
                motionConstrainedPoint.mTranslationZ = childAt2.getTranslationZ();
            }
        }
        int width = getWidth();
        int height = getHeight();
        for (int i7 = 0; i7 < childCount; i7++) {
            MotionController motionController2 = (MotionController) this.mFrameArrayList.get(getChildAt(i7));
            if (motionController2 != null) {
                this.mScene.getKeyFrames(motionController2);
                motionController2.setup(width, height, System.nanoTime());
            }
        }
        MotionScene.Transition transition = this.mScene.mCurrentTransition;
        float f2 = transition != null ? transition.mStagger : 0.0f;
        if (f2 != 0.0f) {
            float f3 = Float.MAX_VALUE;
            float f4 = -3.4028235E38f;
            for (int i8 = 0; i8 < childCount; i8++) {
                MotionPaths motionPaths2 = ((MotionController) this.mFrameArrayList.get(getChildAt(i8))).mEndMotionPath;
                float f5 = motionPaths2.mY + motionPaths2.mX;
                f3 = Math.min(f3, f5);
                f4 = Math.max(f4, f5);
            }
            for (int i9 = 0; i9 < childCount; i9++) {
                MotionController motionController3 = (MotionController) this.mFrameArrayList.get(getChildAt(i9));
                MotionPaths motionPaths3 = motionController3.mEndMotionPath;
                float f6 = motionPaths3.mX;
                float f7 = motionPaths3.mY;
                motionController3.mStaggerScale = 1.0f / (1.0f - f2);
                motionController3.mStaggerOffset = f2 - ((((f6 + f7) - f3) * f2) / (f4 - f3));
            }
        }
        this.mTransitionPosition = 0.0f;
        this.mTransitionLastPosition = 0.0f;
        this.mInTransition = true;
        invalidate();
    }

    public final void updateState(int i, ConstraintSet constraintSet) {
        MotionScene motionScene = this.mScene;
        if (motionScene != null) {
            motionScene.mConstraintSetMap.put(i, constraintSet);
        }
        this.mModel.initFrom(this.mScene.getConstraintSet(this.mBeginState), this.mScene.getConstraintSet(this.mEndState));
        rebuildScene();
        if (this.mCurrentState == i) {
            constraintSet.applyTo(this);
        }
    }

    public final void viewTransition(int i, View... viewArr) {
        String str;
        MotionScene motionScene = this.mScene;
        if (motionScene == null) {
            Log.e("MotionLayout", " no motionScene");
            return;
        }
        ViewTransitionController viewTransitionController = motionScene.mViewTransitionController;
        viewTransitionController.getClass();
        ArrayList arrayList = new ArrayList();
        Iterator it = viewTransitionController.mViewTransitions.iterator();
        ViewTransition viewTransition = null;
        while (true) {
            boolean hasNext = it.hasNext();
            str = viewTransitionController.mTAG;
            if (!hasNext) {
                break;
            }
            ViewTransition viewTransition2 = (ViewTransition) it.next();
            if (viewTransition2.mId == i) {
                for (View view : viewArr) {
                    if (viewTransition2.checkTags(view)) {
                        arrayList.add(view);
                    }
                }
                if (!arrayList.isEmpty()) {
                    View[] viewArr2 = (View[]) arrayList.toArray(new View[0]);
                    MotionLayout motionLayout = viewTransitionController.mMotionLayout;
                    int i2 = motionLayout.mCurrentState;
                    if (viewTransition2.mViewTransitionMode == 2) {
                        viewTransition2.applyTransition(viewTransitionController, motionLayout, i2, null, viewArr2);
                    } else if (i2 == -1) {
                        Log.w(str, "No support for ViewTransition within transition yet. Currently: " + motionLayout.toString());
                    } else {
                        ConstraintSet constraintSet = motionLayout.getConstraintSet(i2);
                        if (constraintSet != null) {
                            viewTransition2.applyTransition(viewTransitionController, viewTransitionController.mMotionLayout, i2, constraintSet, viewArr2);
                        }
                    }
                    arrayList.clear();
                }
                viewTransition = viewTransition2;
            }
        }
        if (viewTransition == null) {
            Log.e(str, " Could not find ViewTransition");
        }
    }

    @Override // androidx.core.view.NestedScrollingParent3
    public final void onNestedScroll(View view, int i, int i2, int i3, int i4, int i5, int[] iArr) {
        if (this.mUndergoingMotion || i != 0 || i2 != 0) {
            iArr[0] = iArr[0] + i3;
            iArr[1] = iArr[1] + i4;
        }
        this.mUndergoingMotion = false;
    }

    public final void setState(int i) {
        ConstraintSet constraintSet;
        ConstraintLayoutStates.State state;
        setState(TransitionState.SETUP);
        this.mCurrentState = i;
        this.mBeginState = -1;
        this.mEndState = -1;
        ConstraintLayoutStates constraintLayoutStates = this.mConstraintLayoutSpec;
        if (constraintLayoutStates != null) {
            float f = -1;
            int i2 = constraintLayoutStates.mCurrentStateId;
            int i3 = 0;
            ConstraintLayout constraintLayout = constraintLayoutStates.mConstraintLayout;
            if (i2 == i) {
                if (i == -1) {
                    state = (ConstraintLayoutStates.State) constraintLayoutStates.mStateList.valueAt(0);
                } else {
                    state = (ConstraintLayoutStates.State) constraintLayoutStates.mStateList.get(i2);
                }
                int i4 = constraintLayoutStates.mCurrentConstraintNumber;
                if (i4 == -1 || !((ConstraintLayoutStates.Variant) state.mVariants.get(i4)).match(f, f)) {
                    while (true) {
                        if (i3 >= state.mVariants.size()) {
                            i3 = -1;
                            break;
                        } else if (((ConstraintLayoutStates.Variant) state.mVariants.get(i3)).match(f, f)) {
                            break;
                        } else {
                            i3++;
                        }
                    }
                    if (constraintLayoutStates.mCurrentConstraintNumber == i3) {
                        return;
                    }
                    ConstraintSet constraintSet2 = i3 == -1 ? null : ((ConstraintLayoutStates.Variant) state.mVariants.get(i3)).mConstraintSet;
                    if (i3 != -1) {
                        int i5 = ((ConstraintLayoutStates.Variant) state.mVariants.get(i3)).mConstraintID;
                    }
                    if (constraintSet2 == null) {
                        return;
                    }
                    constraintLayoutStates.mCurrentConstraintNumber = i3;
                    constraintSet2.applyTo(constraintLayout);
                    return;
                }
                return;
            }
            constraintLayoutStates.mCurrentStateId = i;
            ConstraintLayoutStates.State state2 = (ConstraintLayoutStates.State) constraintLayoutStates.mStateList.get(i);
            while (true) {
                if (i3 >= state2.mVariants.size()) {
                    i3 = -1;
                    break;
                } else if (((ConstraintLayoutStates.Variant) state2.mVariants.get(i3)).match(f, f)) {
                    break;
                } else {
                    i3++;
                }
            }
            if (i3 == -1) {
                constraintSet = state2.mConstraintSet;
            } else {
                constraintSet = ((ConstraintLayoutStates.Variant) state2.mVariants.get(i3)).mConstraintSet;
            }
            if (i3 != -1) {
                int i6 = ((ConstraintLayoutStates.Variant) state2.mVariants.get(i3)).mConstraintID;
            }
            if (constraintSet == null) {
                return;
            }
            constraintLayoutStates.mCurrentConstraintNumber = i3;
            constraintSet.applyTo(constraintLayout);
            return;
        }
        MotionScene motionScene = this.mScene;
        if (motionScene != null) {
            motionScene.getConstraintSet(i).applyTo(this);
        }
    }

    public final void setTransition(int i) {
        MotionScene.Transition transition;
        float f;
        MotionScene motionScene = this.mScene;
        if (motionScene != null) {
            Iterator it = motionScene.mTransitionList.iterator();
            while (true) {
                if (!it.hasNext()) {
                    transition = null;
                    break;
                } else {
                    transition = (MotionScene.Transition) it.next();
                    if (transition.mId == i) {
                        break;
                    }
                }
            }
            this.mBeginState = transition.mConstraintSetStart;
            this.mEndState = transition.mConstraintSetEnd;
            if (!isAttachedToWindow()) {
                if (this.mStateCache == null) {
                    this.mStateCache = new StateCache();
                }
                StateCache stateCache = this.mStateCache;
                stateCache.mStartState = this.mBeginState;
                stateCache.mEndState = this.mEndState;
                return;
            }
            int i2 = this.mCurrentState;
            if (i2 == this.mBeginState) {
                f = 0.0f;
            } else {
                f = i2 == this.mEndState ? 1.0f : Float.NaN;
            }
            MotionScene motionScene2 = this.mScene;
            motionScene2.mCurrentTransition = transition;
            TouchResponse touchResponse = transition.mTouchResponse;
            if (touchResponse != null) {
                touchResponse.setRTL(motionScene2.mRtl);
            }
            this.mModel.initFrom(this.mScene.getConstraintSet(this.mBeginState), this.mScene.getConstraintSet(this.mEndState));
            rebuildScene();
            if (this.mTransitionLastPosition != f) {
                if (f == 0.0f) {
                    endTrigger(true);
                    this.mScene.getConstraintSet(this.mBeginState).applyTo(this);
                } else if (f == 1.0f) {
                    endTrigger(false);
                    this.mScene.getConstraintSet(this.mEndState).applyTo(this);
                }
            }
            this.mTransitionLastPosition = Float.isNaN(f) ? 0.0f : f;
            if (Float.isNaN(f)) {
                Debug.getLocation();
                animateTo(0.0f);
            } else {
                setProgress(f);
            }
        }
    }

    public final void setTransition(MotionScene.Transition transition) {
        MotionScene motionScene = this.mScene;
        motionScene.mCurrentTransition = transition;
        TouchResponse touchResponse = transition.mTouchResponse;
        if (touchResponse != null) {
            touchResponse.setRTL(motionScene.mRtl);
        }
        setState(TransitionState.SETUP);
        int i = this.mCurrentState;
        MotionScene.Transition transition2 = this.mScene.mCurrentTransition;
        if (i == (transition2 == null ? -1 : transition2.mConstraintSetEnd)) {
            this.mTransitionLastPosition = 1.0f;
            this.mTransitionPosition = 1.0f;
            this.mTransitionGoalPosition = 1.0f;
        } else {
            this.mTransitionLastPosition = 0.0f;
            this.mTransitionPosition = 0.0f;
            this.mTransitionGoalPosition = 0.0f;
        }
        this.mTransitionLastTime = (transition.mTransitionFlags & 1) != 0 ? -1L : System.nanoTime();
        int startId = this.mScene.getStartId();
        MotionScene motionScene2 = this.mScene;
        MotionScene.Transition transition3 = motionScene2.mCurrentTransition;
        int i2 = transition3 != null ? transition3.mConstraintSetEnd : -1;
        if (startId == this.mBeginState && i2 == this.mEndState) {
            return;
        }
        this.mBeginState = startId;
        this.mEndState = i2;
        motionScene2.setTransition(startId, i2);
        this.mModel.initFrom(this.mScene.getConstraintSet(this.mBeginState), this.mScene.getConstraintSet(this.mEndState));
        Model model = this.mModel;
        int i3 = this.mBeginState;
        int i4 = this.mEndState;
        model.mStartId = i3;
        model.mEndId = i4;
        model.reEvaluateState();
        rebuildScene();
    }
}
