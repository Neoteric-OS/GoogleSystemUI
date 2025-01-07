package androidx.constraintlayout.motion.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.util.Log;
import android.util.Xml;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AnimationUtils;
import android.view.animation.AnticipateInterpolator;
import android.view.animation.BounceInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.OvershootInterpolator;
import androidx.constraintlayout.core.motion.utils.Easing;
import androidx.constraintlayout.core.motion.utils.KeyCache;
import androidx.constraintlayout.motion.widget.MotionScene;
import androidx.constraintlayout.widget.ConstraintAttribute;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.constraintlayout.widget.R$styleable;
import com.android.wm.shell.R;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class ViewTransition {
    public final ConstraintSet.Constraint mConstraintDelta;
    public final Context mContext;
    public int mId;
    public final KeyFrames mKeyFrames;
    public int mTargetId;
    public String mTargetString;
    public int mViewTransitionMode;
    public int mOnStateTransition = -1;
    public boolean mDisabled = false;
    public int mPathMotionArc = 0;
    public int mDuration = -1;
    public int mUpDuration = -1;
    public int mDefaultInterpolator = 0;
    public String mDefaultInterpolatorString = null;
    public int mDefaultInterpolatorID = -1;
    public int mSetsTag = -1;
    public int mClearsTag = -1;
    public int mIfTagSet = -1;
    public int mIfTagNotSet = -1;
    public int mSharedValueTarget = -1;
    public int mSharedValueID = -1;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Animate {
        public final int mClearsTag;
        public float mDpositionDt;
        public final boolean mHoldAt100;
        public final Interpolator mInterpolator;
        public final MotionController mMC;
        public float mPosition;
        public final int mSetsTag;
        public final int mUpDuration;
        public final ViewTransitionController mVtController;
        public final KeyCache mCache = new KeyCache();
        public boolean mReverse = false;
        public final Rect mTempRec = new Rect();
        public long mLastRender = System.nanoTime();

        public Animate(ViewTransitionController viewTransitionController, MotionController motionController, int i, int i2, int i3, Interpolator interpolator, int i4, int i5) {
            this.mHoldAt100 = false;
            this.mVtController = viewTransitionController;
            this.mMC = motionController;
            this.mUpDuration = i2;
            if (viewTransitionController.mAnimations == null) {
                viewTransitionController.mAnimations = new ArrayList();
            }
            viewTransitionController.mAnimations.add(this);
            this.mInterpolator = interpolator;
            this.mSetsTag = i4;
            this.mClearsTag = i5;
            if (i3 == 3) {
                this.mHoldAt100 = true;
            }
            this.mDpositionDt = i == 0 ? Float.MAX_VALUE : 1.0f / i;
            mutate();
        }

        public final void mutate() {
            boolean z = this.mReverse;
            ViewTransitionController viewTransitionController = this.mVtController;
            MotionController motionController = this.mMC;
            int i = this.mClearsTag;
            int i2 = this.mSetsTag;
            if (z) {
                long nanoTime = System.nanoTime();
                long j = nanoTime - this.mLastRender;
                this.mLastRender = nanoTime;
                float f = this.mPosition - (((float) (j * 1.0E-6d)) * this.mDpositionDt);
                this.mPosition = f;
                if (f < 0.0f) {
                    this.mPosition = 0.0f;
                }
                Interpolator interpolator = this.mInterpolator;
                boolean interpolate = motionController.interpolate(interpolator == null ? this.mPosition : interpolator.getInterpolation(this.mPosition), nanoTime, motionController.mView, this.mCache);
                if (this.mPosition <= 0.0f) {
                    if (i2 != -1) {
                        motionController.mView.setTag(i2, Long.valueOf(System.nanoTime()));
                    }
                    if (i != -1) {
                        motionController.mView.setTag(i, null);
                    }
                    viewTransitionController.mRemoveList.add(this);
                }
                if (this.mPosition > 0.0f || interpolate) {
                    viewTransitionController.mMotionLayout.invalidate();
                    return;
                }
                return;
            }
            long nanoTime2 = System.nanoTime();
            long j2 = nanoTime2 - this.mLastRender;
            this.mLastRender = nanoTime2;
            float f2 = (((float) (j2 * 1.0E-6d)) * this.mDpositionDt) + this.mPosition;
            this.mPosition = f2;
            if (f2 >= 1.0f) {
                this.mPosition = 1.0f;
            }
            Interpolator interpolator2 = this.mInterpolator;
            boolean interpolate2 = motionController.interpolate(interpolator2 == null ? this.mPosition : interpolator2.getInterpolation(this.mPosition), nanoTime2, motionController.mView, this.mCache);
            if (this.mPosition >= 1.0f) {
                if (i2 != -1) {
                    motionController.mView.setTag(i2, Long.valueOf(System.nanoTime()));
                }
                if (i != -1) {
                    motionController.mView.setTag(i, null);
                }
                if (!this.mHoldAt100) {
                    viewTransitionController.mRemoveList.add(this);
                }
            }
            if (this.mPosition < 1.0f || interpolate2) {
                viewTransitionController.mMotionLayout.invalidate();
            }
        }

        public final void reverse() {
            this.mReverse = true;
            int i = this.mUpDuration;
            if (i != -1) {
                this.mDpositionDt = i == 0 ? Float.MAX_VALUE : 1.0f / i;
            }
            this.mVtController.mMotionLayout.invalidate();
            this.mLastRender = System.nanoTime();
        }
    }

    public ViewTransition(Context context, XmlPullParser xmlPullParser) {
        char c;
        this.mContext = context;
        try {
            int eventType = xmlPullParser.getEventType();
            while (eventType != 1) {
                if (eventType == 2) {
                    String name = xmlPullParser.getName();
                    switch (name.hashCode()) {
                        case -1962203927:
                            if (name.equals("ConstraintOverride")) {
                                c = 2;
                                break;
                            }
                            c = 65535;
                            break;
                        case -1239391468:
                            if (name.equals("KeyFrameSet")) {
                                c = 1;
                                break;
                            }
                            c = 65535;
                            break;
                        case 61998586:
                            if (name.equals("ViewTransition")) {
                                c = 0;
                                break;
                            }
                            c = 65535;
                            break;
                        case 366511058:
                            if (name.equals("CustomMethod")) {
                                c = 4;
                                break;
                            }
                            c = 65535;
                            break;
                        case 1791837707:
                            if (name.equals("CustomAttribute")) {
                                c = 3;
                                break;
                            }
                            c = 65535;
                            break;
                        default:
                            c = 65535;
                            break;
                    }
                    if (c == 0) {
                        parseViewTransitionTags(context, xmlPullParser);
                    } else if (c == 1) {
                        this.mKeyFrames = new KeyFrames(context, xmlPullParser);
                    } else if (c == 2) {
                        this.mConstraintDelta = ConstraintSet.buildDelta(context, xmlPullParser);
                    } else if (c == 3 || c == 4) {
                        ConstraintAttribute.parse(context, xmlPullParser, this.mConstraintDelta.mCustomConstraints);
                    } else {
                        Log.e("ViewTransition", Debug.getLoc() + " unknown tag " + name);
                        StringBuilder sb = new StringBuilder();
                        sb.append(".xml:");
                        sb.append(xmlPullParser.getLineNumber());
                        Log.e("ViewTransition", sb.toString());
                    }
                } else if (eventType == 3 && "ViewTransition".equals(xmlPullParser.getName())) {
                    return;
                }
                eventType = xmlPullParser.next();
            }
        } catch (IOException e) {
            Log.e("ViewTransition", "Error parsing XML resource", e);
        } catch (XmlPullParserException e2) {
            Log.e("ViewTransition", "Error parsing XML resource", e2);
        }
    }

    public final void applyTransition(ViewTransitionController viewTransitionController, MotionLayout motionLayout, int i, ConstraintSet constraintSet, View... viewArr) {
        if (this.mDisabled) {
            return;
        }
        int i2 = this.mViewTransitionMode;
        KeyFrames keyFrames = this.mKeyFrames;
        int[] iArr = null;
        Interpolator loadInterpolator = null;
        int i3 = 0;
        if (i2 == 2) {
            View view = viewArr[0];
            MotionController motionController = new MotionController(view);
            MotionPaths motionPaths = motionController.mStartMotionPath;
            motionPaths.mTime = 0.0f;
            motionPaths.mPosition = 0.0f;
            motionController.mNoMovement = true;
            motionPaths.setBounds(view.getX(), view.getY(), view.getWidth(), view.getHeight());
            motionController.mEndMotionPath.setBounds(view.getX(), view.getY(), view.getWidth(), view.getHeight());
            MotionConstrainedPoint motionConstrainedPoint = motionController.mStartPoint;
            motionConstrainedPoint.getClass();
            view.getX();
            view.getY();
            view.getWidth();
            view.getHeight();
            motionConstrainedPoint.mVisibility = view.getVisibility();
            motionConstrainedPoint.mAlpha = view.getVisibility() != 0 ? 0.0f : view.getAlpha();
            motionConstrainedPoint.mElevation = view.getElevation();
            motionConstrainedPoint.mRotation = view.getRotation();
            motionConstrainedPoint.mRotationX = view.getRotationX();
            motionConstrainedPoint.rotationY = view.getRotationY();
            motionConstrainedPoint.mScaleX = view.getScaleX();
            motionConstrainedPoint.mScaleY = view.getScaleY();
            motionConstrainedPoint.mPivotX = view.getPivotX();
            motionConstrainedPoint.mPivotY = view.getPivotY();
            motionConstrainedPoint.mTranslationX = view.getTranslationX();
            motionConstrainedPoint.mTranslationY = view.getTranslationY();
            motionConstrainedPoint.mTranslationZ = view.getTranslationZ();
            MotionConstrainedPoint motionConstrainedPoint2 = motionController.mEndPoint;
            motionConstrainedPoint2.getClass();
            view.getX();
            view.getY();
            view.getWidth();
            view.getHeight();
            motionConstrainedPoint2.mVisibility = view.getVisibility();
            motionConstrainedPoint2.mAlpha = view.getVisibility() == 0 ? view.getAlpha() : 0.0f;
            motionConstrainedPoint2.mElevation = view.getElevation();
            motionConstrainedPoint2.mRotation = view.getRotation();
            motionConstrainedPoint2.mRotationX = view.getRotationX();
            motionConstrainedPoint2.rotationY = view.getRotationY();
            motionConstrainedPoint2.mScaleX = view.getScaleX();
            motionConstrainedPoint2.mScaleY = view.getScaleY();
            motionConstrainedPoint2.mPivotX = view.getPivotX();
            motionConstrainedPoint2.mPivotY = view.getPivotY();
            motionConstrainedPoint2.mTranslationX = view.getTranslationX();
            motionConstrainedPoint2.mTranslationY = view.getTranslationY();
            motionConstrainedPoint2.mTranslationZ = view.getTranslationZ();
            ArrayList arrayList = (ArrayList) keyFrames.mFramesMap.get(-1);
            if (arrayList != null) {
                motionController.mKeyList.addAll(arrayList);
            }
            motionController.setup(motionLayout.getWidth(), motionLayout.getHeight(), System.nanoTime());
            int i4 = this.mDuration;
            int i5 = this.mUpDuration;
            int i6 = this.mOnStateTransition;
            Context context = motionLayout.getContext();
            int i7 = this.mDefaultInterpolator;
            if (i7 == -2) {
                loadInterpolator = AnimationUtils.loadInterpolator(context, this.mDefaultInterpolatorID);
            } else if (i7 == -1) {
                final Easing interpolator = Easing.getInterpolator(this.mDefaultInterpolatorString);
                loadInterpolator = new Interpolator() { // from class: androidx.constraintlayout.motion.widget.ViewTransition.1
                    @Override // android.animation.TimeInterpolator
                    public final float getInterpolation(float f) {
                        return (float) Easing.this.get(f);
                    }
                };
            } else if (i7 == 0) {
                loadInterpolator = new AccelerateDecelerateInterpolator();
            } else if (i7 == 1) {
                loadInterpolator = new AccelerateInterpolator();
            } else if (i7 == 2) {
                loadInterpolator = new DecelerateInterpolator();
            } else if (i7 == 4) {
                loadInterpolator = new BounceInterpolator();
            } else if (i7 == 5) {
                loadInterpolator = new OvershootInterpolator();
            } else if (i7 == 6) {
                loadInterpolator = new AnticipateInterpolator();
            }
            new Animate(viewTransitionController, motionController, i4, i5, i6, loadInterpolator, this.mSetsTag, this.mClearsTag);
            return;
        }
        ConstraintSet.Constraint constraint = this.mConstraintDelta;
        if (i2 == 1) {
            MotionScene motionScene = motionLayout.mScene;
            if (motionScene != null) {
                int size = motionScene.mConstraintSetMap.size();
                int[] iArr2 = new int[size];
                for (int i8 = 0; i8 < size; i8++) {
                    iArr2[i8] = motionScene.mConstraintSetMap.keyAt(i8);
                }
                iArr = iArr2;
            }
            int i9 = 0;
            while (i9 < iArr.length) {
                int i10 = iArr[i9];
                if (i10 != i) {
                    ConstraintSet constraintSet2 = motionLayout.getConstraintSet(i10);
                    int length = viewArr.length;
                    for (int i11 = i3; i11 < length; i11++) {
                        ConstraintSet.Constraint constraint2 = constraintSet2.getConstraint(viewArr[i11].getId());
                        if (constraint != null) {
                            ConstraintSet.Constraint.Delta delta = constraint.mDelta;
                            if (delta != null) {
                                delta.applyDelta(constraint2);
                            }
                            constraint2.mCustomConstraints.putAll(constraint.mCustomConstraints);
                        }
                    }
                }
                i9++;
                i3 = 0;
            }
        }
        ConstraintSet constraintSet3 = new ConstraintSet();
        constraintSet3.clone(constraintSet);
        for (View view2 : viewArr) {
            ConstraintSet.Constraint constraint3 = constraintSet3.getConstraint(view2.getId());
            if (constraint != null) {
                ConstraintSet.Constraint.Delta delta2 = constraint.mDelta;
                if (delta2 != null) {
                    delta2.applyDelta(constraint3);
                }
                constraint3.mCustomConstraints.putAll(constraint.mCustomConstraints);
            }
        }
        motionLayout.updateState(i, constraintSet3);
        motionLayout.updateState(R.id.view_transition, constraintSet);
        motionLayout.setState(R.id.view_transition);
        MotionScene.Transition transition = new MotionScene.Transition(motionLayout.mScene, i);
        for (View view3 : viewArr) {
            int i12 = this.mDuration;
            if (i12 != -1) {
                transition.mDuration = Math.max(i12, 8);
            }
            transition.mPathMotionArc = this.mPathMotionArc;
            int i13 = this.mDefaultInterpolator;
            String str = this.mDefaultInterpolatorString;
            int i14 = this.mDefaultInterpolatorID;
            transition.mDefaultInterpolator = i13;
            transition.mDefaultInterpolatorString = str;
            transition.mDefaultInterpolatorID = i14;
            int id = view3.getId();
            if (keyFrames != null) {
                ArrayList arrayList2 = (ArrayList) keyFrames.mFramesMap.get(-1);
                KeyFrames keyFrames2 = new KeyFrames();
                keyFrames2.mFramesMap = new HashMap();
                Iterator it = arrayList2.iterator();
                while (it.hasNext()) {
                    Key m708clone = ((Key) it.next()).m708clone();
                    m708clone.mTargetId = id;
                    keyFrames2.addKey(m708clone);
                }
                transition.mKeyFramesList.add(keyFrames2);
            }
        }
        motionLayout.setTransition(transition);
        ViewTransition$$ExternalSyntheticLambda0 viewTransition$$ExternalSyntheticLambda0 = new ViewTransition$$ExternalSyntheticLambda0(this, viewArr);
        motionLayout.animateTo(1.0f);
        motionLayout.mOnComplete = viewTransition$$ExternalSyntheticLambda0;
    }

    public final boolean checkTags(View view) {
        int i = this.mIfTagSet;
        boolean z = i == -1 || view.getTag(i) != null;
        int i2 = this.mIfTagNotSet;
        return z && (i2 == -1 || view.getTag(i2) == null);
    }

    public final boolean matchesView(View view) {
        String str;
        if (view == null) {
            return false;
        }
        if ((this.mTargetId == -1 && this.mTargetString == null) || !checkTags(view)) {
            return false;
        }
        if (view.getId() == this.mTargetId) {
            return true;
        }
        return this.mTargetString != null && (view.getLayoutParams() instanceof ConstraintLayout.LayoutParams) && (str = ((ConstraintLayout.LayoutParams) view.getLayoutParams()).constraintTag) != null && str.matches(this.mTargetString);
    }

    public final void parseViewTransitionTags(Context context, XmlPullParser xmlPullParser) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(Xml.asAttributeSet(xmlPullParser), R$styleable.ViewTransition);
        int indexCount = obtainStyledAttributes.getIndexCount();
        for (int i = 0; i < indexCount; i++) {
            int index = obtainStyledAttributes.getIndex(i);
            if (index == 0) {
                this.mId = obtainStyledAttributes.getResourceId(index, this.mId);
            } else if (index == 8) {
                if (MotionLayout.IS_IN_EDIT_MODE) {
                    int resourceId = obtainStyledAttributes.getResourceId(index, this.mTargetId);
                    this.mTargetId = resourceId;
                    if (resourceId == -1) {
                        this.mTargetString = obtainStyledAttributes.getString(index);
                    }
                } else if (obtainStyledAttributes.peekValue(index).type == 3) {
                    this.mTargetString = obtainStyledAttributes.getString(index);
                } else {
                    this.mTargetId = obtainStyledAttributes.getResourceId(index, this.mTargetId);
                }
            } else if (index == 9) {
                this.mOnStateTransition = obtainStyledAttributes.getInt(index, this.mOnStateTransition);
            } else if (index == 12) {
                this.mDisabled = obtainStyledAttributes.getBoolean(index, this.mDisabled);
            } else if (index == 10) {
                this.mPathMotionArc = obtainStyledAttributes.getInt(index, this.mPathMotionArc);
            } else if (index == 4) {
                this.mDuration = obtainStyledAttributes.getInt(index, this.mDuration);
            } else if (index == 13) {
                this.mUpDuration = obtainStyledAttributes.getInt(index, this.mUpDuration);
            } else if (index == 14) {
                this.mViewTransitionMode = obtainStyledAttributes.getInt(index, this.mViewTransitionMode);
            } else if (index == 7) {
                int i2 = obtainStyledAttributes.peekValue(index).type;
                if (i2 == 1) {
                    int resourceId2 = obtainStyledAttributes.getResourceId(index, -1);
                    this.mDefaultInterpolatorID = resourceId2;
                    if (resourceId2 != -1) {
                        this.mDefaultInterpolator = -2;
                    }
                } else if (i2 == 3) {
                    String string = obtainStyledAttributes.getString(index);
                    this.mDefaultInterpolatorString = string;
                    if (string == null || string.indexOf("/") <= 0) {
                        this.mDefaultInterpolator = -1;
                    } else {
                        this.mDefaultInterpolatorID = obtainStyledAttributes.getResourceId(index, -1);
                        this.mDefaultInterpolator = -2;
                    }
                } else {
                    this.mDefaultInterpolator = obtainStyledAttributes.getInteger(index, this.mDefaultInterpolator);
                }
            } else if (index == 11) {
                this.mSetsTag = obtainStyledAttributes.getResourceId(index, this.mSetsTag);
            } else if (index == 3) {
                this.mClearsTag = obtainStyledAttributes.getResourceId(index, this.mClearsTag);
            } else if (index == 6) {
                this.mIfTagSet = obtainStyledAttributes.getResourceId(index, this.mIfTagSet);
            } else if (index == 5) {
                this.mIfTagNotSet = obtainStyledAttributes.getResourceId(index, this.mIfTagNotSet);
            } else if (index == 2) {
                this.mSharedValueID = obtainStyledAttributes.getResourceId(index, this.mSharedValueID);
            } else if (index == 1) {
                this.mSharedValueTarget = obtainStyledAttributes.getInteger(index, this.mSharedValueTarget);
            }
        }
        obtainStyledAttributes.recycle();
    }

    public final String toString() {
        return "ViewTransition(" + Debug.getName(this.mId, this.mContext) + ")";
    }
}
