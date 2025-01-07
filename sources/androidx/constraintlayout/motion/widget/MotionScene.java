package androidx.constraintlayout.motion.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.util.Log;
import android.util.SparseArray;
import android.util.SparseIntArray;
import android.util.Xml;
import android.view.MotionEvent;
import android.view.View;
import androidx.activity.ComponentActivity$activityResultRegistry$1$$ExternalSyntheticOutline0;
import androidx.constraintlayout.motion.widget.MotionLayout;
import androidx.constraintlayout.widget.Barrier;
import androidx.constraintlayout.widget.ConstraintAttribute;
import androidx.constraintlayout.widget.ConstraintHelper;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.constraintlayout.widget.R$styleable;
import androidx.constraintlayout.widget.StateSet;
import com.android.wm.shell.R;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class MotionScene {
    public Transition mCurrentTransition;
    public final Transition mDefaultTransition;
    public MotionEvent mLastTouchDown;
    public float mLastTouchX;
    public float mLastTouchY;
    public final MotionLayout mMotionLayout;
    public boolean mRtl;
    public final StateSet mStateSet;
    public MotionLayout.MyTracker mVelocityTracker;
    public final ViewTransitionController mViewTransitionController;
    public final ArrayList mTransitionList = new ArrayList();
    public final ArrayList mAbstractTransitionList = new ArrayList();
    public final SparseArray mConstraintSetMap = new SparseArray();
    public final HashMap mConstraintSetIdMap = new HashMap();
    public final SparseIntArray mDeriveMap = new SparseIntArray();
    public int mDefaultDuration = 400;
    public int mLayoutDuringTransition = 0;
    public boolean mIgnoreTouch = false;
    public boolean mMotionOutsideRegion = false;

    public MotionScene(Context context, MotionLayout motionLayout, int i) {
        int eventType;
        Transition transition;
        this.mStateSet = null;
        this.mCurrentTransition = null;
        this.mDefaultTransition = null;
        this.mMotionLayout = motionLayout;
        this.mViewTransitionController = new ViewTransitionController(motionLayout);
        XmlResourceParser xml = context.getResources().getXml(i);
        try {
            eventType = xml.getEventType();
            transition = null;
        } catch (IOException e) {
            Log.e("MotionScene", "Error parsing resource: " + i, e);
        } catch (XmlPullParserException e2) {
            Log.e("MotionScene", "Error parsing resource: " + i, e2);
        }
        while (true) {
            char c = 1;
            if (eventType == 1) {
                this.mConstraintSetMap.put(R.id.motion_base, new ConstraintSet());
                this.mConstraintSetIdMap.put("motion_base", Integer.valueOf(R.id.motion_base));
                return;
            }
            if (eventType == 2) {
                String name = xml.getName();
                switch (name.hashCode()) {
                    case -1349929691:
                        if (name.equals("ConstraintSet")) {
                            c = 5;
                            break;
                        }
                        c = 65535;
                        break;
                    case -1239391468:
                        if (name.equals("KeyFrameSet")) {
                            c = '\b';
                            break;
                        }
                        c = 65535;
                        break;
                    case -687739768:
                        if (name.equals("Include")) {
                            c = 7;
                            break;
                        }
                        c = 65535;
                        break;
                    case 61998586:
                        if (name.equals("ViewTransition")) {
                            c = '\t';
                            break;
                        }
                        c = 65535;
                        break;
                    case 269306229:
                        if (name.equals("Transition")) {
                            break;
                        }
                        c = 65535;
                        break;
                    case 312750793:
                        if (name.equals("OnClick")) {
                            c = 3;
                            break;
                        }
                        c = 65535;
                        break;
                    case 327855227:
                        if (name.equals("OnSwipe")) {
                            c = 2;
                            break;
                        }
                        c = 65535;
                        break;
                    case 793277014:
                        if (name.equals("MotionScene")) {
                            c = 0;
                            break;
                        }
                        c = 65535;
                        break;
                    case 1382829617:
                        if (name.equals("StateSet")) {
                            c = 4;
                            break;
                        }
                        c = 65535;
                        break;
                    case 1942574248:
                        if (name.equals("include")) {
                            c = 6;
                            break;
                        }
                        c = 65535;
                        break;
                    default:
                        c = 65535;
                        break;
                }
                MotionLayout motionLayout2 = this.mMotionLayout;
                switch (c) {
                    case 0:
                        parseMotionSceneTags(context, xml);
                        break;
                    case 1:
                        ArrayList arrayList = this.mTransitionList;
                        transition = new Transition(this, context, xml);
                        boolean z = transition.mIsAbstract;
                        arrayList.add(transition);
                        if (this.mCurrentTransition == null && !z) {
                            this.mCurrentTransition = transition;
                            TouchResponse touchResponse = transition.mTouchResponse;
                            if (touchResponse != null) {
                                touchResponse.setRTL(this.mRtl);
                            }
                        }
                        if (!z) {
                            break;
                        } else {
                            if (transition.mConstraintSetEnd == -1) {
                                this.mDefaultTransition = transition;
                            } else {
                                this.mAbstractTransitionList.add(transition);
                            }
                            this.mTransitionList.remove(transition);
                            break;
                        }
                    case 2:
                        if (transition == null) {
                            context.getResources().getResourceEntryName(i);
                            xml.getLineNumber();
                        }
                        if (transition == null) {
                            break;
                        } else {
                            transition.mTouchResponse = new TouchResponse(context, motionLayout2, xml);
                            break;
                        }
                    case 3:
                        if (transition != null && !motionLayout2.isInEditMode()) {
                            transition.mOnClicks.add(new Transition.TransitionOnClick(context, transition, xml));
                            break;
                        }
                        break;
                    case 4:
                        this.mStateSet = new StateSet(context, xml);
                        break;
                    case 5:
                        parseConstraintSet(context, xml);
                        break;
                    case 6:
                    case 7:
                        parseInclude(context, xml);
                        break;
                    case '\b':
                        KeyFrames keyFrames = new KeyFrames(context, xml);
                        if (transition == null) {
                            break;
                        } else {
                            transition.mKeyFramesList.add(keyFrames);
                            break;
                        }
                    case '\t':
                        ViewTransition viewTransition = new ViewTransition(context, xml);
                        ViewTransitionController viewTransitionController = this.mViewTransitionController;
                        viewTransitionController.mViewTransitions.add(viewTransition);
                        viewTransitionController.mRelatedViews = null;
                        int i2 = viewTransition.mOnStateTransition;
                        if (i2 != 4) {
                            if (i2 != 5) {
                                break;
                            } else {
                                ViewTransitionController.listenForSharedVariable(viewTransition);
                                break;
                            }
                        } else {
                            ViewTransitionController.listenForSharedVariable(viewTransition);
                            break;
                        }
                }
            }
            eventType = xml.next();
        }
    }

    public static int getId(Context context, String str) {
        int i;
        if (str.contains("/")) {
            i = context.getResources().getIdentifier(str.substring(str.indexOf(47) + 1), "id", context.getPackageName());
        } else {
            i = -1;
        }
        if (i != -1) {
            return i;
        }
        if (str.length() > 1) {
            return Integer.parseInt(str.substring(1));
        }
        Log.e("MotionScene", "error in parsing id");
        return i;
    }

    public final boolean autoTransition(int i, MotionLayout motionLayout) {
        Transition transition;
        if (this.mVelocityTracker != null) {
            return false;
        }
        Iterator it = this.mTransitionList.iterator();
        while (it.hasNext()) {
            Transition transition2 = (Transition) it.next();
            int i2 = transition2.mAutoTransition;
            if (i2 != 0 && ((transition = this.mCurrentTransition) != transition2 || (transition.mTransitionFlags & 2) == 0)) {
                int i3 = transition2.mConstraintSetStart;
                MotionLayout.TransitionState transitionState = MotionLayout.TransitionState.FINISHED;
                MotionLayout.TransitionState transitionState2 = MotionLayout.TransitionState.MOVING;
                MotionLayout.TransitionState transitionState3 = MotionLayout.TransitionState.SETUP;
                if (i == i3 && (i2 == 4 || i2 == 2)) {
                    motionLayout.setState(transitionState);
                    motionLayout.setTransition(transition2);
                    if (transition2.mAutoTransition == 4) {
                        motionLayout.animateTo(1.0f);
                        motionLayout.mOnComplete = null;
                        motionLayout.setState(transitionState3);
                        motionLayout.setState(transitionState2);
                    } else {
                        motionLayout.setProgress(1.0f);
                        motionLayout.evaluate(true);
                        motionLayout.setState(transitionState3);
                        motionLayout.setState(transitionState2);
                        motionLayout.setState(transitionState);
                        motionLayout.onNewStateAttachHandlers();
                    }
                    return true;
                }
                if (i == transition2.mConstraintSetEnd && (i2 == 3 || i2 == 1)) {
                    motionLayout.setState(transitionState);
                    motionLayout.setTransition(transition2);
                    if (transition2.mAutoTransition == 3) {
                        motionLayout.animateTo(0.0f);
                        motionLayout.setState(transitionState3);
                        motionLayout.setState(transitionState2);
                    } else {
                        motionLayout.setProgress(0.0f);
                        motionLayout.evaluate(true);
                        motionLayout.setState(transitionState3);
                        motionLayout.setState(transitionState2);
                        motionLayout.setState(transitionState);
                        motionLayout.onNewStateAttachHandlers();
                    }
                    return true;
                }
            }
        }
        return false;
    }

    public final ConstraintSet getConstraintSet(int i) {
        int stateGetConstraintID;
        StateSet stateSet = this.mStateSet;
        if (stateSet != null && (stateGetConstraintID = stateSet.stateGetConstraintID(i)) != -1) {
            i = stateGetConstraintID;
        }
        if (this.mConstraintSetMap.get(i) != null) {
            return (ConstraintSet) this.mConstraintSetMap.get(i);
        }
        Log.e("MotionScene", "Warning could not find ConstraintSet id/" + Debug.getName(i, this.mMotionLayout.getContext()) + " In MotionScene");
        SparseArray sparseArray = this.mConstraintSetMap;
        return (ConstraintSet) sparseArray.get(sparseArray.keyAt(0));
    }

    public final void getKeyFrames(MotionController motionController) {
        Transition transition = this.mCurrentTransition;
        if (transition != null) {
            Iterator it = transition.mKeyFramesList.iterator();
            while (it.hasNext()) {
                ((KeyFrames) it.next()).addFrames(motionController);
            }
        } else {
            Transition transition2 = this.mDefaultTransition;
            if (transition2 != null) {
                Iterator it2 = transition2.mKeyFramesList.iterator();
                while (it2.hasNext()) {
                    ((KeyFrames) it2.next()).addFrames(motionController);
                }
            }
        }
    }

    public final float getMaxAcceleration() {
        TouchResponse touchResponse;
        Transition transition = this.mCurrentTransition;
        if (transition == null || (touchResponse = transition.mTouchResponse) == null) {
            return 0.0f;
        }
        return touchResponse.mMaxAcceleration;
    }

    public final int getStartId() {
        Transition transition = this.mCurrentTransition;
        if (transition == null) {
            return -1;
        }
        return transition.mConstraintSetStart;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public final int parseConstraintSet(Context context, XmlPullParser xmlPullParser) {
        char c;
        char c2;
        ConstraintSet constraintSet = new ConstraintSet();
        constraintSet.mForceId = false;
        int attributeCount = xmlPullParser.getAttributeCount();
        int i = -1;
        int i2 = -1;
        for (int i3 = 0; i3 < attributeCount; i3++) {
            String attributeName = xmlPullParser.getAttributeName(i3);
            String attributeValue = xmlPullParser.getAttributeValue(i3);
            attributeName.getClass();
            switch (attributeName.hashCode()) {
                case -1496482599:
                    if (attributeName.equals("deriveConstraintsFrom")) {
                        c = 0;
                        break;
                    }
                    c = 65535;
                    break;
                case -1153153640:
                    if (attributeName.equals("constraintRotate")) {
                        c = 1;
                        break;
                    }
                    c = 65535;
                    break;
                case 3355:
                    if (attributeName.equals("id")) {
                        c = 2;
                        break;
                    }
                    c = 65535;
                    break;
                case 973381616:
                    if (attributeName.equals("stateLabels")) {
                        c = 3;
                        break;
                    }
                    c = 65535;
                    break;
                default:
                    c = 65535;
                    break;
            }
            switch (c) {
                case 0:
                    i2 = getId(context, attributeValue);
                    break;
                case 1:
                    try {
                        constraintSet.mRotate = Integer.parseInt(attributeValue);
                        break;
                    } catch (NumberFormatException unused) {
                        attributeValue.getClass();
                        switch (attributeValue.hashCode()) {
                            case -768416914:
                                if (attributeValue.equals("x_left")) {
                                    c2 = 0;
                                    break;
                                }
                                c2 = 65535;
                                break;
                            case 3317767:
                                if (attributeValue.equals("left")) {
                                    c2 = 1;
                                    break;
                                }
                                c2 = 65535;
                                break;
                            case 3387192:
                                if (attributeValue.equals("none")) {
                                    c2 = 2;
                                    break;
                                }
                                c2 = 65535;
                                break;
                            case 108511772:
                                if (attributeValue.equals("right")) {
                                    c2 = 3;
                                    break;
                                }
                                c2 = 65535;
                                break;
                            case 1954540437:
                                if (attributeValue.equals("x_right")) {
                                    c2 = 4;
                                    break;
                                }
                                c2 = 65535;
                                break;
                            default:
                                c2 = 65535;
                                break;
                        }
                        switch (c2) {
                            case 0:
                                constraintSet.mRotate = 4;
                                break;
                            case 1:
                                constraintSet.mRotate = 2;
                                break;
                            case 2:
                                constraintSet.mRotate = 0;
                                break;
                            case 3:
                                constraintSet.mRotate = 1;
                                break;
                            case 4:
                                constraintSet.mRotate = 3;
                                break;
                        }
                    }
                    break;
                case 2:
                    i = getId(context, attributeValue);
                    HashMap hashMap = this.mConstraintSetIdMap;
                    int indexOf = attributeValue.indexOf(47);
                    if (indexOf >= 0) {
                        attributeValue = attributeValue.substring(indexOf + 1);
                    }
                    hashMap.put(attributeValue, Integer.valueOf(i));
                    constraintSet.mIdString = Debug.getName(i, context);
                    break;
                case 3:
                    constraintSet.mMatchLabels = attributeValue.split(",");
                    int i4 = 0;
                    while (true) {
                        String[] strArr = constraintSet.mMatchLabels;
                        if (i4 < strArr.length) {
                            strArr[i4] = strArr[i4].trim();
                            i4++;
                        }
                    }
                    break;
            }
        }
        if (i != -1) {
            int i5 = this.mMotionLayout.mDebugPath;
            constraintSet.load(context, xmlPullParser);
            if (i2 != -1) {
                this.mDeriveMap.put(i, i2);
            }
            this.mConstraintSetMap.put(i, constraintSet);
        }
        return i;
    }

    public final void parseInclude(Context context, XmlPullParser xmlPullParser) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(Xml.asAttributeSet(xmlPullParser), R$styleable.include);
        int indexCount = obtainStyledAttributes.getIndexCount();
        for (int i = 0; i < indexCount; i++) {
            int index = obtainStyledAttributes.getIndex(i);
            if (index == 0) {
                parseInclude(obtainStyledAttributes.getResourceId(index, -1), context);
            }
        }
        obtainStyledAttributes.recycle();
    }

    public final void parseMotionSceneTags(Context context, XmlPullParser xmlPullParser) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(Xml.asAttributeSet(xmlPullParser), R$styleable.MotionScene);
        int indexCount = obtainStyledAttributes.getIndexCount();
        for (int i = 0; i < indexCount; i++) {
            int index = obtainStyledAttributes.getIndex(i);
            if (index == 0) {
                int i2 = obtainStyledAttributes.getInt(index, this.mDefaultDuration);
                this.mDefaultDuration = i2;
                if (i2 < 8) {
                    this.mDefaultDuration = 8;
                }
            } else if (index == 1) {
                this.mLayoutDuringTransition = obtainStyledAttributes.getInteger(index, 0);
            }
        }
        obtainStyledAttributes.recycle();
    }

    public final void readConstraintChain(int i, MotionLayout motionLayout) {
        ConstraintSet constraintSet = (ConstraintSet) this.mConstraintSetMap.get(i);
        constraintSet.derivedState = constraintSet.mIdString;
        int i2 = this.mDeriveMap.get(i);
        if (i2 > 0) {
            readConstraintChain(i2, motionLayout);
            ConstraintSet constraintSet2 = (ConstraintSet) this.mConstraintSetMap.get(i2);
            if (constraintSet2 == null) {
                Log.e("MotionScene", "ERROR! invalid deriveConstraintsFrom: @id/" + Debug.getName(i2, this.mMotionLayout.getContext()));
                return;
            }
            constraintSet.derivedState += "/" + constraintSet2.derivedState;
            for (Integer num : constraintSet2.mConstraints.keySet()) {
                num.getClass();
                ConstraintSet.Constraint constraint = (ConstraintSet.Constraint) constraintSet2.mConstraints.get(num);
                if (!constraintSet.mConstraints.containsKey(num)) {
                    constraintSet.mConstraints.put(num, new ConstraintSet.Constraint());
                }
                ConstraintSet.Constraint constraint2 = (ConstraintSet.Constraint) constraintSet.mConstraints.get(num);
                if (constraint2 != null) {
                    ConstraintSet.Layout layout = constraint2.layout;
                    if (!layout.mApply) {
                        layout.copyFrom(constraint.layout);
                    }
                    ConstraintSet.PropertySet propertySet = constraint2.propertySet;
                    if (!propertySet.mApply) {
                        ConstraintSet.PropertySet propertySet2 = constraint.propertySet;
                        propertySet.mApply = propertySet2.mApply;
                        propertySet.visibility = propertySet2.visibility;
                        propertySet.alpha = propertySet2.alpha;
                        propertySet.mProgress = propertySet2.mProgress;
                        propertySet.mVisibilityMode = propertySet2.mVisibilityMode;
                    }
                    ConstraintSet.Transform transform = constraint2.transform;
                    if (!transform.mApply) {
                        transform.copyFrom(constraint.transform);
                    }
                    ConstraintSet.Motion motion = constraint2.motion;
                    if (!motion.mApply) {
                        motion.copyFrom(constraint.motion);
                    }
                    for (String str : constraint.mCustomConstraints.keySet()) {
                        if (!constraint2.mCustomConstraints.containsKey(str)) {
                            constraint2.mCustomConstraints.put(str, (ConstraintAttribute) constraint.mCustomConstraints.get(str));
                        }
                    }
                }
            }
        } else {
            constraintSet.derivedState = ComponentActivity$activityResultRegistry$1$$ExternalSyntheticOutline0.m(new StringBuilder(), constraintSet.derivedState, "  layout");
            int childCount = motionLayout.getChildCount();
            for (int i3 = 0; i3 < childCount; i3++) {
                View childAt = motionLayout.getChildAt(i3);
                ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) childAt.getLayoutParams();
                int id = childAt.getId();
                if (constraintSet.mForceId && id == -1) {
                    throw new RuntimeException("All children of ConstraintLayout must have ids to use ConstraintSet");
                }
                if (!constraintSet.mConstraints.containsKey(Integer.valueOf(id))) {
                    constraintSet.mConstraints.put(Integer.valueOf(id), new ConstraintSet.Constraint());
                }
                ConstraintSet.Constraint constraint3 = (ConstraintSet.Constraint) constraintSet.mConstraints.get(Integer.valueOf(id));
                if (constraint3 != null) {
                    ConstraintSet.Layout layout2 = constraint3.layout;
                    if (!layout2.mApply) {
                        ConstraintSet.Constraint.access$000(constraint3, id, layoutParams);
                        if (childAt instanceof ConstraintHelper) {
                            ConstraintHelper constraintHelper = (ConstraintHelper) childAt;
                            layout2.mReferenceIds = Arrays.copyOf(constraintHelper.mIds, constraintHelper.mCount);
                            if (childAt instanceof Barrier) {
                                Barrier barrier = (Barrier) childAt;
                                androidx.constraintlayout.core.widgets.Barrier barrier2 = barrier.mBarrier;
                                layout2.mBarrierAllowsGoneWidgets = barrier2.mAllowsGoneWidget;
                                layout2.mBarrierDirection = barrier.mIndicatedType;
                                layout2.mBarrierMargin = barrier2.mMargin;
                            }
                        }
                        layout2.mApply = true;
                    }
                    ConstraintSet.PropertySet propertySet3 = constraint3.propertySet;
                    if (!propertySet3.mApply) {
                        propertySet3.visibility = childAt.getVisibility();
                        propertySet3.alpha = childAt.getAlpha();
                        propertySet3.mApply = true;
                    }
                    ConstraintSet.Transform transform2 = constraint3.transform;
                    if (!transform2.mApply) {
                        transform2.mApply = true;
                        transform2.rotation = childAt.getRotation();
                        transform2.rotationX = childAt.getRotationX();
                        transform2.rotationY = childAt.getRotationY();
                        transform2.scaleX = childAt.getScaleX();
                        transform2.scaleY = childAt.getScaleY();
                        float pivotX = childAt.getPivotX();
                        float pivotY = childAt.getPivotY();
                        if (pivotX != 0.0d || pivotY != 0.0d) {
                            transform2.transformPivotX = pivotX;
                            transform2.transformPivotY = pivotY;
                        }
                        transform2.translationX = childAt.getTranslationX();
                        transform2.translationY = childAt.getTranslationY();
                        transform2.translationZ = childAt.getTranslationZ();
                        if (transform2.applyElevation) {
                            transform2.elevation = childAt.getElevation();
                        }
                    }
                }
            }
        }
        constraintSet.applyDeltaFrom(constraintSet);
    }

    /* JADX WARN: Code restructure failed: missing block: B:7:0x0013, code lost:
    
        if (r2 != (-1)) goto L13;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void setTransition(int r8, int r9) {
        /*
            r7 = this;
            androidx.constraintlayout.widget.StateSet r0 = r7.mStateSet
            r1 = -1
            if (r0 == 0) goto L18
            int r0 = r0.stateGetConstraintID(r8)
            if (r0 == r1) goto Lc
            goto Ld
        Lc:
            r0 = r8
        Ld:
            androidx.constraintlayout.widget.StateSet r2 = r7.mStateSet
            int r2 = r2.stateGetConstraintID(r9)
            if (r2 == r1) goto L16
            goto L1a
        L16:
            r2 = r9
            goto L1a
        L18:
            r0 = r8
            goto L16
        L1a:
            androidx.constraintlayout.motion.widget.MotionScene$Transition r3 = r7.mCurrentTransition
            if (r3 == 0) goto L27
            int r4 = r3.mConstraintSetEnd
            if (r4 != r9) goto L27
            int r3 = r3.mConstraintSetStart
            if (r3 != r8) goto L27
            return
        L27:
            java.util.ArrayList r3 = r7.mTransitionList
            java.util.Iterator r3 = r3.iterator()
        L2d:
            boolean r4 = r3.hasNext()
            if (r4 == 0) goto L53
            java.lang.Object r4 = r3.next()
            androidx.constraintlayout.motion.widget.MotionScene$Transition r4 = (androidx.constraintlayout.motion.widget.MotionScene.Transition) r4
            int r5 = r4.mConstraintSetEnd
            if (r5 != r2) goto L41
            int r6 = r4.mConstraintSetStart
            if (r6 == r0) goto L47
        L41:
            if (r5 != r9) goto L2d
            int r5 = r4.mConstraintSetStart
            if (r5 != r8) goto L2d
        L47:
            r7.mCurrentTransition = r4
            androidx.constraintlayout.motion.widget.TouchResponse r8 = r4.mTouchResponse
            if (r8 == 0) goto L52
            boolean r7 = r7.mRtl
            r8.setRTL(r7)
        L52:
            return
        L53:
            androidx.constraintlayout.motion.widget.MotionScene$Transition r8 = r7.mDefaultTransition
            java.util.ArrayList r3 = r7.mAbstractTransitionList
            java.util.Iterator r3 = r3.iterator()
        L5b:
            boolean r4 = r3.hasNext()
            if (r4 == 0) goto L6d
            java.lang.Object r4 = r3.next()
            androidx.constraintlayout.motion.widget.MotionScene$Transition r4 = (androidx.constraintlayout.motion.widget.MotionScene.Transition) r4
            int r5 = r4.mConstraintSetEnd
            if (r5 != r9) goto L5b
            r8 = r4
            goto L5b
        L6d:
            androidx.constraintlayout.motion.widget.MotionScene$Transition r9 = new androidx.constraintlayout.motion.widget.MotionScene$Transition
            r9.<init>(r7, r8)
            r9.mConstraintSetStart = r0
            r9.mConstraintSetEnd = r2
            if (r0 == r1) goto L7d
            java.util.ArrayList r8 = r7.mTransitionList
            r8.add(r9)
        L7d:
            r7.mCurrentTransition = r9
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.motion.widget.MotionScene.setTransition(int, int):void");
    }

    public final boolean supportTouch() {
        Iterator it = this.mTransitionList.iterator();
        while (it.hasNext()) {
            if (((Transition) it.next()).mTouchResponse != null) {
                return true;
            }
        }
        Transition transition = this.mCurrentTransition;
        return (transition == null || transition.mTouchResponse == null) ? false : true;
    }

    public final int parseInclude(int i, Context context) {
        XmlResourceParser xml = context.getResources().getXml(i);
        try {
            for (int eventType = xml.getEventType(); eventType != 1; eventType = xml.next()) {
                String name = xml.getName();
                if (2 == eventType && "ConstraintSet".equals(name)) {
                    return parseConstraintSet(context, xml);
                }
            }
            return -1;
        } catch (IOException e) {
            Log.e("MotionScene", "Error parsing resource: " + i, e);
            return -1;
        } catch (XmlPullParserException e2) {
            Log.e("MotionScene", "Error parsing resource: " + i, e2);
            return -1;
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Transition {
        public final int mAutoTransition;
        public int mConstraintSetEnd;
        public int mConstraintSetStart;
        public int mDefaultInterpolator;
        public int mDefaultInterpolatorID;
        public String mDefaultInterpolatorString;
        public final boolean mDisable;
        public int mDuration;
        public final int mId;
        public final boolean mIsAbstract;
        public final ArrayList mKeyFramesList;
        public final int mLayoutDuringTransition;
        public final MotionScene mMotionScene;
        public final ArrayList mOnClicks;
        public int mPathMotionArc;
        public final float mStagger;
        public TouchResponse mTouchResponse;
        public final int mTransitionFlags;

        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        public final class TransitionOnClick implements View.OnClickListener {
            public final int mMode;
            public final int mTargetId;
            public final Transition mTransition;

            public TransitionOnClick(Context context, Transition transition, XmlPullParser xmlPullParser) {
                this.mTargetId = -1;
                this.mMode = 17;
                this.mTransition = transition;
                TypedArray obtainStyledAttributes = context.obtainStyledAttributes(Xml.asAttributeSet(xmlPullParser), R$styleable.OnClick);
                int indexCount = obtainStyledAttributes.getIndexCount();
                for (int i = 0; i < indexCount; i++) {
                    int index = obtainStyledAttributes.getIndex(i);
                    if (index == 1) {
                        this.mTargetId = obtainStyledAttributes.getResourceId(index, this.mTargetId);
                    } else if (index == 0) {
                        this.mMode = obtainStyledAttributes.getInt(index, this.mMode);
                    }
                }
                obtainStyledAttributes.recycle();
            }

            /* JADX WARN: Multi-variable type inference failed */
            /* JADX WARN: Type inference failed for: r8v4, types: [android.view.View] */
            public final void addOnClickListeners(MotionLayout motionLayout, int i, Transition transition) {
                int i2 = this.mTargetId;
                MotionLayout motionLayout2 = motionLayout;
                if (i2 != -1) {
                    motionLayout2 = motionLayout.findViewById(i2);
                }
                if (motionLayout2 == null) {
                    Log.e("MotionScene", "OnClick could not find id " + this.mTargetId);
                    return;
                }
                int i3 = transition.mConstraintSetStart;
                int i4 = transition.mConstraintSetEnd;
                if (i3 == -1) {
                    motionLayout2.setOnClickListener(this);
                    return;
                }
                int i5 = this.mMode;
                int i6 = i5 & 1;
                if (((i6 != 0 && i == i3) | (i6 != 0 && i == i3) | ((i5 & 256) != 0 && i == i3) | ((i5 & 16) != 0 && i == i4)) || ((i5 & 4096) != 0 && i == i4)) {
                    motionLayout2.setOnClickListener(this);
                }
            }

            /* JADX WARN: Removed duplicated region for block: B:34:0x0070  */
            @Override // android.view.View.OnClickListener
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public final void onClick(android.view.View r11) {
                /*
                    Method dump skipped, instructions count: 191
                    To view this dump add '--comments-level debug' option
                */
                throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.motion.widget.MotionScene.Transition.TransitionOnClick.onClick(android.view.View):void");
            }

            public final void removeOnClickListeners(MotionLayout motionLayout) {
                int i = this.mTargetId;
                if (i == -1) {
                    return;
                }
                View findViewById = motionLayout.findViewById(i);
                if (findViewById != null) {
                    findViewById.setOnClickListener(null);
                    return;
                }
                Log.e("MotionScene", " (*)  could not find id " + this.mTargetId);
            }
        }

        public Transition(MotionScene motionScene, Transition transition) {
            this.mId = -1;
            this.mIsAbstract = false;
            this.mConstraintSetEnd = -1;
            this.mConstraintSetStart = -1;
            this.mDefaultInterpolator = 0;
            this.mDefaultInterpolatorString = null;
            this.mDefaultInterpolatorID = -1;
            this.mDuration = 400;
            this.mStagger = 0.0f;
            this.mKeyFramesList = new ArrayList();
            this.mTouchResponse = null;
            this.mOnClicks = new ArrayList();
            this.mAutoTransition = 0;
            this.mDisable = false;
            this.mPathMotionArc = -1;
            this.mLayoutDuringTransition = 0;
            this.mTransitionFlags = 0;
            this.mMotionScene = motionScene;
            this.mDuration = motionScene.mDefaultDuration;
            if (transition != null) {
                this.mPathMotionArc = transition.mPathMotionArc;
                this.mDefaultInterpolator = transition.mDefaultInterpolator;
                this.mDefaultInterpolatorString = transition.mDefaultInterpolatorString;
                this.mDefaultInterpolatorID = transition.mDefaultInterpolatorID;
                this.mDuration = transition.mDuration;
                this.mKeyFramesList = transition.mKeyFramesList;
                this.mStagger = transition.mStagger;
                this.mLayoutDuringTransition = transition.mLayoutDuringTransition;
            }
        }

        public Transition(MotionScene motionScene, int i) {
            this.mId = -1;
            this.mIsAbstract = false;
            this.mConstraintSetEnd = -1;
            this.mConstraintSetStart = -1;
            this.mDefaultInterpolator = 0;
            this.mDefaultInterpolatorString = null;
            this.mDefaultInterpolatorID = -1;
            this.mDuration = 400;
            this.mStagger = 0.0f;
            this.mKeyFramesList = new ArrayList();
            this.mTouchResponse = null;
            this.mOnClicks = new ArrayList();
            this.mAutoTransition = 0;
            this.mDisable = false;
            this.mPathMotionArc = -1;
            this.mLayoutDuringTransition = 0;
            this.mTransitionFlags = 0;
            this.mId = -1;
            this.mMotionScene = motionScene;
            this.mConstraintSetStart = R.id.view_transition;
            this.mConstraintSetEnd = i;
            this.mDuration = motionScene.mDefaultDuration;
            this.mLayoutDuringTransition = motionScene.mLayoutDuringTransition;
        }

        public Transition(MotionScene motionScene, Context context, XmlPullParser xmlPullParser) {
            this.mId = -1;
            this.mIsAbstract = false;
            this.mConstraintSetEnd = -1;
            this.mConstraintSetStart = -1;
            this.mDefaultInterpolator = 0;
            this.mDefaultInterpolatorString = null;
            this.mDefaultInterpolatorID = -1;
            this.mDuration = 400;
            this.mStagger = 0.0f;
            this.mKeyFramesList = new ArrayList();
            this.mTouchResponse = null;
            this.mOnClicks = new ArrayList();
            this.mAutoTransition = 0;
            this.mDisable = false;
            this.mPathMotionArc = -1;
            this.mLayoutDuringTransition = 0;
            this.mTransitionFlags = 0;
            this.mDuration = motionScene.mDefaultDuration;
            this.mLayoutDuringTransition = motionScene.mLayoutDuringTransition;
            this.mMotionScene = motionScene;
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(Xml.asAttributeSet(xmlPullParser), R$styleable.Transition);
            int indexCount = obtainStyledAttributes.getIndexCount();
            for (int i = 0; i < indexCount; i++) {
                int index = obtainStyledAttributes.getIndex(i);
                if (index == 2) {
                    this.mConstraintSetEnd = obtainStyledAttributes.getResourceId(index, -1);
                    String resourceTypeName = context.getResources().getResourceTypeName(this.mConstraintSetEnd);
                    if ("layout".equals(resourceTypeName)) {
                        ConstraintSet constraintSet = new ConstraintSet();
                        constraintSet.load(this.mConstraintSetEnd, context);
                        motionScene.mConstraintSetMap.append(this.mConstraintSetEnd, constraintSet);
                    } else if ("xml".equals(resourceTypeName)) {
                        this.mConstraintSetEnd = motionScene.parseInclude(this.mConstraintSetEnd, context);
                    }
                } else if (index == 3) {
                    this.mConstraintSetStart = obtainStyledAttributes.getResourceId(index, this.mConstraintSetStart);
                    String resourceTypeName2 = context.getResources().getResourceTypeName(this.mConstraintSetStart);
                    if ("layout".equals(resourceTypeName2)) {
                        ConstraintSet constraintSet2 = new ConstraintSet();
                        constraintSet2.load(this.mConstraintSetStart, context);
                        motionScene.mConstraintSetMap.append(this.mConstraintSetStart, constraintSet2);
                    } else if ("xml".equals(resourceTypeName2)) {
                        this.mConstraintSetStart = motionScene.parseInclude(this.mConstraintSetStart, context);
                    }
                } else if (index == 6) {
                    int i2 = obtainStyledAttributes.peekValue(index).type;
                    if (i2 == 1) {
                        int resourceId = obtainStyledAttributes.getResourceId(index, -1);
                        this.mDefaultInterpolatorID = resourceId;
                        if (resourceId != -1) {
                            this.mDefaultInterpolator = -2;
                        }
                    } else if (i2 == 3) {
                        String string = obtainStyledAttributes.getString(index);
                        this.mDefaultInterpolatorString = string;
                        if (string != null) {
                            if (string.indexOf("/") > 0) {
                                this.mDefaultInterpolatorID = obtainStyledAttributes.getResourceId(index, -1);
                                this.mDefaultInterpolator = -2;
                            } else {
                                this.mDefaultInterpolator = -1;
                            }
                        }
                    } else {
                        this.mDefaultInterpolator = obtainStyledAttributes.getInteger(index, this.mDefaultInterpolator);
                    }
                } else if (index == 4) {
                    int i3 = obtainStyledAttributes.getInt(index, this.mDuration);
                    this.mDuration = i3;
                    if (i3 < 8) {
                        this.mDuration = 8;
                    }
                } else if (index == 8) {
                    this.mStagger = obtainStyledAttributes.getFloat(index, this.mStagger);
                } else if (index == 1) {
                    this.mAutoTransition = obtainStyledAttributes.getInteger(index, this.mAutoTransition);
                } else if (index == 0) {
                    this.mId = obtainStyledAttributes.getResourceId(index, this.mId);
                } else if (index == 9) {
                    this.mDisable = obtainStyledAttributes.getBoolean(index, this.mDisable);
                } else if (index == 7) {
                    this.mPathMotionArc = obtainStyledAttributes.getInteger(index, -1);
                } else if (index == 5) {
                    this.mLayoutDuringTransition = obtainStyledAttributes.getInteger(index, 0);
                } else if (index == 10) {
                    this.mTransitionFlags = obtainStyledAttributes.getInteger(index, 0);
                }
            }
            if (this.mConstraintSetStart == -1) {
                this.mIsAbstract = true;
            }
            obtainStyledAttributes.recycle();
        }
    }
}
