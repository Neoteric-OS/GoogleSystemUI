package androidx.constraintlayout.motion.widget;

import android.util.SparseIntArray;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.SharedValues;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class ViewTransitionController {
    public ArrayList mAnimations;
    public final MotionLayout mMotionLayout;
    public HashSet mRelatedViews;
    public final ArrayList mViewTransitions = new ArrayList();
    public final String mTAG = "ViewTransitionController";
    public final ArrayList mRemoveList = new ArrayList();

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: androidx.constraintlayout.motion.widget.ViewTransitionController$1, reason: invalid class name */
    public final class AnonymousClass1 {
    }

    public ViewTransitionController(MotionLayout motionLayout) {
        this.mMotionLayout = motionLayout;
    }

    public static void listenForSharedVariable(ViewTransition viewTransition) {
        if (ConstraintLayout.sSharedValues == null) {
            SharedValues sharedValues = new SharedValues();
            new SparseIntArray();
            sharedValues.mValuesListeners = new HashMap();
            ConstraintLayout.sSharedValues = sharedValues;
        }
        SharedValues sharedValues2 = ConstraintLayout.sSharedValues;
        int i = viewTransition.mSharedValueID;
        AnonymousClass1 anonymousClass1 = new AnonymousClass1();
        HashSet hashSet = (HashSet) sharedValues2.mValuesListeners.get(Integer.valueOf(i));
        if (hashSet == null) {
            hashSet = new HashSet();
            sharedValues2.mValuesListeners.put(Integer.valueOf(i), hashSet);
        }
        hashSet.add(new WeakReference(anonymousClass1));
    }
}
