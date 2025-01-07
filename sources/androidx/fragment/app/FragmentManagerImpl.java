package androidx.fragment.app;

import android.os.Looper;
import android.util.Log;
import androidx.activity.BackEventCompat;
import androidx.activity.OnBackPressedCallback;
import androidx.activity.OnBackPressedDispatcher;
import androidx.activity.result.ActivityResultRegistry$register$2;
import androidx.appcompat.view.menu.CascadingMenuPopup$$ExternalSyntheticOutline0;
import androidx.core.app.MultiWindowModeChangedInfo;
import androidx.core.app.NotificationCompat$Builder$$ExternalSyntheticOutline0;
import androidx.core.app.PictureInPictureModeChangedInfo;
import androidx.core.util.Consumer;
import androidx.core.view.VelocityTrackerCompat$$ExternalSyntheticThrowCCEIfNotNull0;
import androidx.lifecycle.Lifecycle;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class FragmentManagerImpl {
    public final ArrayList mBackStackChangeListeners;
    public FragmentActivity$HostCallbacks mContainer;
    public int mCurState;
    public boolean mDestroyed;
    public final FragmentManager$5 mExecCommit;
    public boolean mExecutingActions;
    public FragmentActivity$HostCallbacks mHost;
    public final FragmentManager$3 mHostFragmentFactory;
    public ArrayDeque mLaunchedFragments;
    public final FragmentManager$2 mMenuProvider;
    public FragmentManagerViewModel mNonConfig;
    public final CopyOnWriteArrayList mOnAttachListeners;
    public OnBackPressedDispatcher mOnBackPressedDispatcher;
    public final FragmentManager$$ExternalSyntheticLambda0 mOnConfigurationChangedListener;
    public final FragmentManager$$ExternalSyntheticLambda0 mOnMultiWindowModeChangedListener;
    public final FragmentManager$$ExternalSyntheticLambda0 mOnPictureInPictureModeChangedListener;
    public final FragmentManager$$ExternalSyntheticLambda0 mOnTrimMemoryListener;
    public ActivityResultRegistry$register$2 mRequestPermissions;
    public ActivityResultRegistry$register$2 mStartActivityForResult;
    public ActivityResultRegistry$register$2 mStartIntentSenderForResult;
    public boolean mStateSaved;
    public boolean mStopped;
    public ArrayList mTmpAddedFragments;
    public ArrayList mTmpIsPop;
    public ArrayList mTmpRecords;
    public final ArrayList mPendingActions = new ArrayList();
    public final FragmentStore mFragmentStore = new FragmentStore();
    public ArrayList mBackStack = new ArrayList();
    public final FragmentLayoutInflaterFactory mLayoutInflaterFactory = new FragmentLayoutInflaterFactory(this);
    public BackStackRecord mTransitioningOp = null;
    public boolean mHandlingTransitioningOp = false;
    public final FragmentManager$1 mOnBackPressedCallback = new OnBackPressedCallback() { // from class: androidx.fragment.app.FragmentManager$1
        {
            super(false);
        }

        @Override // androidx.activity.OnBackPressedCallback
        public final void handleOnBackCancelled() {
            boolean isLoggable = Log.isLoggable("FragmentManager", 3);
            final FragmentManagerImpl fragmentManagerImpl = FragmentManagerImpl.this;
            if (isLoggable) {
                Log.d("FragmentManager", "handleOnBackCancelled. PREDICTIVE_BACK = true fragment manager " + fragmentManagerImpl);
            }
            if (Log.isLoggable("FragmentManager", 3)) {
                Log.d("FragmentManager", "cancelBackStackTransition for transition " + fragmentManagerImpl.mTransitioningOp);
            }
            BackStackRecord backStackRecord = fragmentManagerImpl.mTransitioningOp;
            if (backStackRecord != null) {
                backStackRecord.mCommitted = false;
                Runnable runnable = new Runnable() { // from class: androidx.fragment.app.FragmentManager$$ExternalSyntheticLambda5
                    @Override // java.lang.Runnable
                    public final void run() {
                        Iterator it = FragmentManagerImpl.this.mBackStackChangeListeners.iterator();
                        if (it.hasNext()) {
                            throw NotificationCompat$Builder$$ExternalSyntheticOutline0.m(it);
                        }
                    }
                };
                if (backStackRecord.mCommitRunnables == null) {
                    backStackRecord.mCommitRunnables = new ArrayList();
                }
                backStackRecord.mCommitRunnables.add(runnable);
                fragmentManagerImpl.mTransitioningOp.commitInternal(true);
                fragmentManagerImpl.mHandlingTransitioningOp = true;
                fragmentManagerImpl.execPendingActions(true);
                Iterator it = ((HashSet) fragmentManagerImpl.collectAllSpecialEffectsController()).iterator();
                while (it.hasNext()) {
                    ((DefaultSpecialEffectsController) it.next()).getClass();
                }
                fragmentManagerImpl.mHandlingTransitioningOp = false;
                fragmentManagerImpl.mTransitioningOp = null;
            }
        }

        @Override // androidx.activity.OnBackPressedCallback
        public final void handleOnBackPressed() {
            boolean isLoggable = Log.isLoggable("FragmentManager", 3);
            FragmentManagerImpl fragmentManagerImpl = FragmentManagerImpl.this;
            if (isLoggable) {
                Log.d("FragmentManager", "handleOnBackPressed. PREDICTIVE_BACK = true fragment manager " + fragmentManagerImpl);
            }
            fragmentManagerImpl.mHandlingTransitioningOp = true;
            fragmentManagerImpl.execPendingActions(true);
            fragmentManagerImpl.mHandlingTransitioningOp = false;
            BackStackRecord backStackRecord = fragmentManagerImpl.mTransitioningOp;
            FragmentManager$1 fragmentManager$1 = fragmentManagerImpl.mOnBackPressedCallback;
            if (backStackRecord == null) {
                if (!fragmentManager$1.isEnabled) {
                    if (Log.isLoggable("FragmentManager", 3)) {
                        Log.d("FragmentManager", "Calling onBackPressed via onBackPressed callback");
                    }
                    fragmentManagerImpl.mOnBackPressedDispatcher.onBackPressed();
                    return;
                }
                if (Log.isLoggable("FragmentManager", 3)) {
                    Log.d("FragmentManager", "Calling popBackStackImmediate via onBackPressed callback");
                }
                fragmentManagerImpl.execPendingActions(false);
                fragmentManagerImpl.ensureExecReady(true);
                if (fragmentManagerImpl.popBackStackState(fragmentManagerImpl.mTmpRecords, fragmentManagerImpl.mTmpIsPop)) {
                    fragmentManagerImpl.mExecutingActions = true;
                    try {
                        fragmentManagerImpl.removeRedundantOperationsAndExecute(fragmentManagerImpl.mTmpRecords, fragmentManagerImpl.mTmpIsPop);
                    } finally {
                        fragmentManagerImpl.cleanupExec();
                    }
                }
                fragmentManagerImpl.updateOnBackPressedCallbackEnabled();
                fragmentManagerImpl.mFragmentStore.mActive.values().removeAll(Collections.singleton(null));
                return;
            }
            if (!fragmentManagerImpl.mBackStackChangeListeners.isEmpty()) {
                LinkedHashSet linkedHashSet = new LinkedHashSet(FragmentManagerImpl.fragmentsFromRecord(fragmentManagerImpl.mTransitioningOp));
                Iterator it = fragmentManagerImpl.mBackStackChangeListeners.iterator();
                while (it.hasNext()) {
                    if (it.next() != null) {
                        throw new ClassCastException();
                    }
                    Iterator it2 = linkedHashSet.iterator();
                    if (it2.hasNext()) {
                        throw NotificationCompat$Builder$$ExternalSyntheticOutline0.m(it2);
                    }
                }
            }
            Iterator it3 = fragmentManagerImpl.mTransitioningOp.mOps.iterator();
            while (it3.hasNext()) {
                ((FragmentTransaction$Op) it3.next()).getClass();
            }
            Iterator it4 = ((HashSet) FragmentManagerImpl.collectChangedControllers(new ArrayList(Collections.singletonList(fragmentManagerImpl.mTransitioningOp)), 0, 1)).iterator();
            if (it4.hasNext()) {
                DefaultSpecialEffectsController defaultSpecialEffectsController = (DefaultSpecialEffectsController) it4.next();
                if (Log.isLoggable("FragmentManager", 3)) {
                    defaultSpecialEffectsController.getClass();
                    Log.d("FragmentManager", "SpecialEffectsController: Completing Back ");
                }
                defaultSpecialEffectsController.getClass();
                throw null;
            }
            Iterator it5 = fragmentManagerImpl.mTransitioningOp.mOps.iterator();
            while (it5.hasNext()) {
                ((FragmentTransaction$Op) it5.next()).getClass();
            }
            fragmentManagerImpl.mTransitioningOp = null;
            fragmentManagerImpl.updateOnBackPressedCallbackEnabled();
            if (Log.isLoggable("FragmentManager", 3)) {
                Log.d("FragmentManager", "Op is being set to null");
                Log.d("FragmentManager", "OnBackPressedCallback enabled=" + fragmentManager$1.isEnabled + " for  FragmentManager " + fragmentManagerImpl);
            }
        }

        @Override // androidx.activity.OnBackPressedCallback
        public final void handleOnBackProgressed(BackEventCompat backEventCompat) {
            FragmentManagerImpl fragmentManagerImpl = FragmentManagerImpl.this;
            if (fragmentManagerImpl.mTransitioningOp != null) {
                Iterator it = ((HashSet) FragmentManagerImpl.collectChangedControllers(new ArrayList(Collections.singletonList(fragmentManagerImpl.mTransitioningOp)), 0, 1)).iterator();
                if (it.hasNext()) {
                    ((DefaultSpecialEffectsController) it.next()).getClass();
                    new ArrayList();
                    throw null;
                }
                Iterator it2 = fragmentManagerImpl.mBackStackChangeListeners.iterator();
                if (it2.hasNext()) {
                    throw NotificationCompat$Builder$$ExternalSyntheticOutline0.m(it2);
                }
            }
        }

        @Override // androidx.activity.OnBackPressedCallback
        public final void handleOnBackStarted() {
            boolean isLoggable = Log.isLoggable("FragmentManager", 3);
            final FragmentManagerImpl fragmentManagerImpl = FragmentManagerImpl.this;
            if (isLoggable) {
                Log.d("FragmentManager", "handleOnBackStarted. PREDICTIVE_BACK = true fragment manager " + fragmentManagerImpl);
            }
            fragmentManagerImpl.endAnimatingAwayFragments();
            fragmentManagerImpl.enqueueAction(new FragmentManager$OpGenerator() { // from class: androidx.fragment.app.FragmentManager$PrepareBackStackTransitionState
                @Override // androidx.fragment.app.FragmentManager$OpGenerator
                public final boolean generateOps(ArrayList arrayList, ArrayList arrayList2) {
                    boolean popBackStackState;
                    FragmentManagerImpl fragmentManagerImpl2 = FragmentManagerImpl.this;
                    if (fragmentManagerImpl2.mBackStack.isEmpty()) {
                        Log.i("FragmentManager", "Ignoring call to start back stack pop because the back stack is empty.");
                        popBackStackState = false;
                    } else {
                        BackStackRecord backStackRecord = (BackStackRecord) CascadingMenuPopup$$ExternalSyntheticOutline0.m(fragmentManagerImpl2.mBackStack, 1);
                        fragmentManagerImpl2.mTransitioningOp = backStackRecord;
                        Iterator it = backStackRecord.mOps.iterator();
                        while (it.hasNext()) {
                            ((FragmentTransaction$Op) it.next()).getClass();
                        }
                        popBackStackState = fragmentManagerImpl2.popBackStackState(arrayList, arrayList2);
                    }
                    if (!fragmentManagerImpl2.mBackStackChangeListeners.isEmpty() && arrayList.size() > 0) {
                        ((Boolean) arrayList2.get(arrayList.size() - 1)).getClass();
                        LinkedHashSet linkedHashSet = new LinkedHashSet();
                        Iterator it2 = arrayList.iterator();
                        while (it2.hasNext()) {
                            linkedHashSet.addAll(FragmentManagerImpl.fragmentsFromRecord((BackStackRecord) it2.next()));
                        }
                        Iterator it3 = fragmentManagerImpl2.mBackStackChangeListeners.iterator();
                        while (it3.hasNext()) {
                            if (it3.next() != null) {
                                throw new ClassCastException();
                            }
                            Iterator it4 = linkedHashSet.iterator();
                            if (it4.hasNext()) {
                                throw NotificationCompat$Builder$$ExternalSyntheticOutline0.m(it4);
                            }
                        }
                    }
                    return popBackStackState;
                }
            });
        }
    };
    public final AtomicInteger mBackStackIndex = new AtomicInteger();
    public final Map mBackStackStates = Collections.synchronizedMap(new HashMap());
    public final Map mResults = Collections.synchronizedMap(new HashMap());

    /* JADX WARN: Type inference failed for: r0v16, types: [androidx.fragment.app.FragmentManager$$ExternalSyntheticLambda0] */
    /* JADX WARN: Type inference failed for: r0v17, types: [androidx.fragment.app.FragmentManager$$ExternalSyntheticLambda0] */
    /* JADX WARN: Type inference failed for: r0v18, types: [androidx.fragment.app.FragmentManager$$ExternalSyntheticLambda0] */
    /* JADX WARN: Type inference failed for: r0v19, types: [androidx.fragment.app.FragmentManager$$ExternalSyntheticLambda0] */
    /* JADX WARN: Type inference failed for: r0v24, types: [androidx.fragment.app.FragmentManager$5] */
    /* JADX WARN: Type inference failed for: r0v6, types: [androidx.fragment.app.FragmentManager$1] */
    public FragmentManagerImpl() {
        Collections.synchronizedMap(new HashMap());
        this.mBackStackChangeListeners = new ArrayList();
        new CopyOnWriteArrayList();
        this.mOnAttachListeners = new CopyOnWriteArrayList();
        final int i = 0;
        this.mOnConfigurationChangedListener = new Consumer(this) { // from class: androidx.fragment.app.FragmentManager$$ExternalSyntheticLambda0
            public final /* synthetic */ FragmentManagerImpl f$0;

            {
                this.f$0 = this;
            }

            @Override // androidx.core.util.Consumer
            public final void accept(Object obj) {
                switch (i) {
                    case 0:
                        Iterator it = this.f$0.mFragmentStore.getFragments().iterator();
                        while (it.hasNext()) {
                            if (it.next() != null) {
                                throw new ClassCastException();
                            }
                        }
                        return;
                    case 1:
                        FragmentManagerImpl fragmentManagerImpl = this.f$0;
                        fragmentManagerImpl.getClass();
                        if (((Integer) obj).intValue() == 80) {
                            Iterator it2 = fragmentManagerImpl.mFragmentStore.getFragments().iterator();
                            while (it2.hasNext()) {
                                if (it2.next() != null) {
                                    throw new ClassCastException();
                                }
                            }
                            return;
                        }
                        return;
                    case 2:
                        FragmentManagerImpl fragmentManagerImpl2 = this.f$0;
                        fragmentManagerImpl2.getClass();
                        boolean z = ((MultiWindowModeChangedInfo) obj).isInMultiWindowMode;
                        Iterator it3 = fragmentManagerImpl2.mFragmentStore.getFragments().iterator();
                        while (it3.hasNext()) {
                            if (it3.next() != null) {
                                throw new ClassCastException();
                            }
                        }
                        return;
                    default:
                        FragmentManagerImpl fragmentManagerImpl3 = this.f$0;
                        fragmentManagerImpl3.getClass();
                        boolean z2 = ((PictureInPictureModeChangedInfo) obj).isInPictureInPictureMode;
                        Iterator it4 = fragmentManagerImpl3.mFragmentStore.getFragments().iterator();
                        while (it4.hasNext()) {
                            if (it4.next() != null) {
                                throw new ClassCastException();
                            }
                        }
                        return;
                }
            }
        };
        final int i2 = 1;
        this.mOnTrimMemoryListener = new Consumer(this) { // from class: androidx.fragment.app.FragmentManager$$ExternalSyntheticLambda0
            public final /* synthetic */ FragmentManagerImpl f$0;

            {
                this.f$0 = this;
            }

            @Override // androidx.core.util.Consumer
            public final void accept(Object obj) {
                switch (i2) {
                    case 0:
                        Iterator it = this.f$0.mFragmentStore.getFragments().iterator();
                        while (it.hasNext()) {
                            if (it.next() != null) {
                                throw new ClassCastException();
                            }
                        }
                        return;
                    case 1:
                        FragmentManagerImpl fragmentManagerImpl = this.f$0;
                        fragmentManagerImpl.getClass();
                        if (((Integer) obj).intValue() == 80) {
                            Iterator it2 = fragmentManagerImpl.mFragmentStore.getFragments().iterator();
                            while (it2.hasNext()) {
                                if (it2.next() != null) {
                                    throw new ClassCastException();
                                }
                            }
                            return;
                        }
                        return;
                    case 2:
                        FragmentManagerImpl fragmentManagerImpl2 = this.f$0;
                        fragmentManagerImpl2.getClass();
                        boolean z = ((MultiWindowModeChangedInfo) obj).isInMultiWindowMode;
                        Iterator it3 = fragmentManagerImpl2.mFragmentStore.getFragments().iterator();
                        while (it3.hasNext()) {
                            if (it3.next() != null) {
                                throw new ClassCastException();
                            }
                        }
                        return;
                    default:
                        FragmentManagerImpl fragmentManagerImpl3 = this.f$0;
                        fragmentManagerImpl3.getClass();
                        boolean z2 = ((PictureInPictureModeChangedInfo) obj).isInPictureInPictureMode;
                        Iterator it4 = fragmentManagerImpl3.mFragmentStore.getFragments().iterator();
                        while (it4.hasNext()) {
                            if (it4.next() != null) {
                                throw new ClassCastException();
                            }
                        }
                        return;
                }
            }
        };
        final int i3 = 2;
        this.mOnMultiWindowModeChangedListener = new Consumer(this) { // from class: androidx.fragment.app.FragmentManager$$ExternalSyntheticLambda0
            public final /* synthetic */ FragmentManagerImpl f$0;

            {
                this.f$0 = this;
            }

            @Override // androidx.core.util.Consumer
            public final void accept(Object obj) {
                switch (i3) {
                    case 0:
                        Iterator it = this.f$0.mFragmentStore.getFragments().iterator();
                        while (it.hasNext()) {
                            if (it.next() != null) {
                                throw new ClassCastException();
                            }
                        }
                        return;
                    case 1:
                        FragmentManagerImpl fragmentManagerImpl = this.f$0;
                        fragmentManagerImpl.getClass();
                        if (((Integer) obj).intValue() == 80) {
                            Iterator it2 = fragmentManagerImpl.mFragmentStore.getFragments().iterator();
                            while (it2.hasNext()) {
                                if (it2.next() != null) {
                                    throw new ClassCastException();
                                }
                            }
                            return;
                        }
                        return;
                    case 2:
                        FragmentManagerImpl fragmentManagerImpl2 = this.f$0;
                        fragmentManagerImpl2.getClass();
                        boolean z = ((MultiWindowModeChangedInfo) obj).isInMultiWindowMode;
                        Iterator it3 = fragmentManagerImpl2.mFragmentStore.getFragments().iterator();
                        while (it3.hasNext()) {
                            if (it3.next() != null) {
                                throw new ClassCastException();
                            }
                        }
                        return;
                    default:
                        FragmentManagerImpl fragmentManagerImpl3 = this.f$0;
                        fragmentManagerImpl3.getClass();
                        boolean z2 = ((PictureInPictureModeChangedInfo) obj).isInPictureInPictureMode;
                        Iterator it4 = fragmentManagerImpl3.mFragmentStore.getFragments().iterator();
                        while (it4.hasNext()) {
                            if (it4.next() != null) {
                                throw new ClassCastException();
                            }
                        }
                        return;
                }
            }
        };
        final int i4 = 3;
        this.mOnPictureInPictureModeChangedListener = new Consumer(this) { // from class: androidx.fragment.app.FragmentManager$$ExternalSyntheticLambda0
            public final /* synthetic */ FragmentManagerImpl f$0;

            {
                this.f$0 = this;
            }

            @Override // androidx.core.util.Consumer
            public final void accept(Object obj) {
                switch (i4) {
                    case 0:
                        Iterator it = this.f$0.mFragmentStore.getFragments().iterator();
                        while (it.hasNext()) {
                            if (it.next() != null) {
                                throw new ClassCastException();
                            }
                        }
                        return;
                    case 1:
                        FragmentManagerImpl fragmentManagerImpl = this.f$0;
                        fragmentManagerImpl.getClass();
                        if (((Integer) obj).intValue() == 80) {
                            Iterator it2 = fragmentManagerImpl.mFragmentStore.getFragments().iterator();
                            while (it2.hasNext()) {
                                if (it2.next() != null) {
                                    throw new ClassCastException();
                                }
                            }
                            return;
                        }
                        return;
                    case 2:
                        FragmentManagerImpl fragmentManagerImpl2 = this.f$0;
                        fragmentManagerImpl2.getClass();
                        boolean z = ((MultiWindowModeChangedInfo) obj).isInMultiWindowMode;
                        Iterator it3 = fragmentManagerImpl2.mFragmentStore.getFragments().iterator();
                        while (it3.hasNext()) {
                            if (it3.next() != null) {
                                throw new ClassCastException();
                            }
                        }
                        return;
                    default:
                        FragmentManagerImpl fragmentManagerImpl3 = this.f$0;
                        fragmentManagerImpl3.getClass();
                        boolean z2 = ((PictureInPictureModeChangedInfo) obj).isInPictureInPictureMode;
                        Iterator it4 = fragmentManagerImpl3.mFragmentStore.getFragments().iterator();
                        while (it4.hasNext()) {
                            if (it4.next() != null) {
                                throw new ClassCastException();
                            }
                        }
                        return;
                }
            }
        };
        this.mMenuProvider = new FragmentManager$2(this);
        this.mCurState = -1;
        this.mHostFragmentFactory = new FragmentManager$3(this);
        this.mLaunchedFragments = new ArrayDeque();
        this.mExecCommit = new Runnable() { // from class: androidx.fragment.app.FragmentManager$5
            @Override // java.lang.Runnable
            public final void run() {
                FragmentManagerImpl.this.execPendingActions(true);
            }
        };
    }

    public static Set collectChangedControllers(ArrayList arrayList, int i, int i2) {
        HashSet hashSet = new HashSet();
        while (i < i2) {
            Iterator it = ((BackStackRecord) arrayList.get(i)).mOps.iterator();
            while (it.hasNext()) {
                ((FragmentTransaction$Op) it.next()).getClass();
            }
            i++;
        }
        return hashSet;
    }

    public static Set fragmentsFromRecord(BackStackRecord backStackRecord) {
        HashSet hashSet = new HashSet();
        for (int i = 0; i < backStackRecord.mOps.size(); i++) {
            ((FragmentTransaction$Op) backStackRecord.mOps.get(i)).getClass();
        }
        return hashSet;
    }

    public final void cleanupExec() {
        this.mExecutingActions = false;
        this.mTmpIsPop.clear();
        this.mTmpRecords.clear();
    }

    public final Set collectAllSpecialEffectsController() {
        HashSet hashSet = new HashSet();
        Iterator it = this.mFragmentStore.getActiveFragmentStateManagers().iterator();
        if (!it.hasNext()) {
            return hashSet;
        }
        ((FragmentStateManager) it.next()).getClass();
        throw null;
    }

    public final void dispatchStateChange(int i) {
        try {
            this.mExecutingActions = true;
            Iterator it = this.mFragmentStore.mActive.values().iterator();
            while (it.hasNext()) {
                VelocityTrackerCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(it.next());
            }
            moveToState(i, false);
            Iterator it2 = ((HashSet) collectAllSpecialEffectsController()).iterator();
            if (it2.hasNext()) {
                ((DefaultSpecialEffectsController) it2.next()).getClass();
                throw null;
            }
            this.mExecutingActions = false;
            execPendingActions(true);
        } catch (Throwable th) {
            this.mExecutingActions = false;
            throw th;
        }
    }

    public final void endAnimatingAwayFragments() {
        Iterator it = ((HashSet) collectAllSpecialEffectsController()).iterator();
        if (it.hasNext()) {
            ((DefaultSpecialEffectsController) it.next()).getClass();
            throw null;
        }
    }

    public final void enqueueAction(FragmentManager$OpGenerator fragmentManager$OpGenerator) {
        if (this.mHost == null) {
            if (!this.mDestroyed) {
                throw new IllegalStateException("FragmentManager has not been attached to a host.");
            }
            throw new IllegalStateException("FragmentManager has been destroyed");
        }
        if (this.mStateSaved || this.mStopped) {
            throw new IllegalStateException("Can not perform this action after onSaveInstanceState");
        }
        synchronized (this.mPendingActions) {
            try {
                if (this.mHost == null) {
                    throw new IllegalStateException("Activity has been destroyed");
                }
                this.mPendingActions.add(fragmentManager$OpGenerator);
                scheduleCommit();
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final void ensureExecReady(boolean z) {
        if (this.mExecutingActions) {
            throw new IllegalStateException("FragmentManager is already executing transactions");
        }
        if (this.mHost == null) {
            if (!this.mDestroyed) {
                throw new IllegalStateException("FragmentManager has not been attached to a host.");
            }
            throw new IllegalStateException("FragmentManager has been destroyed");
        }
        if (Looper.myLooper() != this.mHost.handler.getLooper()) {
            throw new IllegalStateException("Must be called from main thread of fragment host");
        }
        if (!z && (this.mStateSaved || this.mStopped)) {
            throw new IllegalStateException("Can not perform this action after onSaveInstanceState");
        }
        if (this.mTmpRecords == null) {
            this.mTmpRecords = new ArrayList();
            this.mTmpIsPop = new ArrayList();
        }
    }

    public final boolean execPendingActions(boolean z) {
        boolean z2;
        BackStackRecord backStackRecord;
        ensureExecReady(z);
        if (!this.mHandlingTransitioningOp && (backStackRecord = this.mTransitioningOp) != null) {
            backStackRecord.mCommitted = false;
            if (Log.isLoggable("FragmentManager", 3)) {
                Log.d("FragmentManager", "Reversing mTransitioningOp " + this.mTransitioningOp + " as part of execPendingActions for actions " + this.mPendingActions);
            }
            this.mTransitioningOp.commitInternal(false);
            this.mPendingActions.add(0, this.mTransitioningOp);
            Iterator it = this.mTransitioningOp.mOps.iterator();
            while (it.hasNext()) {
                ((FragmentTransaction$Op) it.next()).getClass();
            }
            this.mTransitioningOp = null;
        }
        boolean z3 = false;
        while (true) {
            ArrayList arrayList = this.mTmpRecords;
            ArrayList arrayList2 = this.mTmpIsPop;
            synchronized (this.mPendingActions) {
                if (this.mPendingActions.isEmpty()) {
                    z2 = false;
                } else {
                    try {
                        int size = this.mPendingActions.size();
                        z2 = false;
                        for (int i = 0; i < size; i++) {
                            z2 |= ((FragmentManager$OpGenerator) this.mPendingActions.get(i)).generateOps(arrayList, arrayList2);
                        }
                    } finally {
                    }
                }
            }
            if (!z2) {
                updateOnBackPressedCallbackEnabled();
                this.mFragmentStore.mActive.values().removeAll(Collections.singleton(null));
                return z3;
            }
            z3 = true;
            this.mExecutingActions = true;
            try {
                removeRedundantOperationsAndExecute(this.mTmpRecords, this.mTmpIsPop);
            } finally {
                cleanupExec();
            }
        }
    }

    public final void executeOpsTogether(ArrayList arrayList, ArrayList arrayList2, int i, int i2) {
        Object obj;
        int i3 = i;
        boolean z = ((BackStackRecord) arrayList.get(i3)).mReorderingAllowed;
        ArrayList arrayList3 = this.mTmpAddedFragments;
        if (arrayList3 == null) {
            this.mTmpAddedFragments = new ArrayList();
        } else {
            arrayList3.clear();
        }
        this.mTmpAddedFragments.addAll(this.mFragmentStore.getFragments());
        boolean z2 = false;
        for (int i4 = i3; i4 < i2; i4++) {
            BackStackRecord backStackRecord = (BackStackRecord) arrayList.get(i4);
            int i5 = 3;
            if (((Boolean) arrayList2.get(i4)).booleanValue()) {
                ArrayList arrayList4 = this.mTmpAddedFragments;
                for (int size = backStackRecord.mOps.size() - 1; size >= 0; size--) {
                    FragmentTransaction$Op fragmentTransaction$Op = (FragmentTransaction$Op) backStackRecord.mOps.get(size);
                    int i6 = fragmentTransaction$Op.mCmd;
                    if (i6 != 1) {
                        if (i6 != 3) {
                            switch (i6) {
                                case 7:
                                    obj = null;
                                    break;
                                case 10:
                                    fragmentTransaction$Op.mCurrentMaxState = fragmentTransaction$Op.mOldMaxState;
                                    break;
                            }
                        }
                        arrayList4.add(null);
                    } else {
                        obj = null;
                    }
                    arrayList4.remove(obj);
                }
            } else {
                ArrayList arrayList5 = this.mTmpAddedFragments;
                int i7 = 0;
                while (i7 < backStackRecord.mOps.size()) {
                    FragmentTransaction$Op fragmentTransaction$Op2 = (FragmentTransaction$Op) backStackRecord.mOps.get(i7);
                    int i8 = fragmentTransaction$Op2.mCmd;
                    if (i8 != 1) {
                        if (i8 == 2) {
                            throw null;
                        }
                        if (i8 == i5 || i8 == 6) {
                            arrayList5.remove((Object) null);
                            ArrayList arrayList6 = backStackRecord.mOps;
                            FragmentTransaction$Op fragmentTransaction$Op3 = new FragmentTransaction$Op();
                            fragmentTransaction$Op3.mCmd = 9;
                            fragmentTransaction$Op3.mFromExpandedOp = false;
                            Lifecycle.State state = Lifecycle.State.RESUMED;
                            fragmentTransaction$Op3.mOldMaxState = state;
                            fragmentTransaction$Op3.mCurrentMaxState = state;
                            arrayList6.add(i7, fragmentTransaction$Op3);
                        } else if (i8 != 7) {
                            if (i8 != 8) {
                                i7++;
                                i5 = 3;
                            } else {
                                ArrayList arrayList7 = backStackRecord.mOps;
                                FragmentTransaction$Op fragmentTransaction$Op4 = new FragmentTransaction$Op();
                                fragmentTransaction$Op4.mCmd = 9;
                                fragmentTransaction$Op4.mFromExpandedOp = true;
                                Lifecycle.State state2 = Lifecycle.State.RESUMED;
                                fragmentTransaction$Op4.mOldMaxState = state2;
                                fragmentTransaction$Op4.mCurrentMaxState = state2;
                                arrayList7.add(i7, fragmentTransaction$Op4);
                                fragmentTransaction$Op2.mFromExpandedOp = true;
                            }
                        }
                        i7++;
                        i7++;
                        i5 = 3;
                    }
                    arrayList5.add(null);
                    i7++;
                    i5 = 3;
                }
            }
            z2 = z2 || backStackRecord.mAddToBackStack;
        }
        this.mTmpAddedFragments.clear();
        if (!z && this.mCurState >= 1) {
            for (int i9 = i3; i9 < i2; i9++) {
                Iterator it = ((BackStackRecord) arrayList.get(i9)).mOps.iterator();
                while (it.hasNext()) {
                    ((FragmentTransaction$Op) it.next()).getClass();
                }
            }
        }
        for (int i10 = i3; i10 < i2; i10++) {
            BackStackRecord backStackRecord2 = (BackStackRecord) arrayList.get(i10);
            if (((Boolean) arrayList2.get(i10)).booleanValue()) {
                backStackRecord2.bumpBackStackNesting();
                for (int size2 = backStackRecord2.mOps.size() - 1; size2 >= 0; size2--) {
                    FragmentTransaction$Op fragmentTransaction$Op5 = (FragmentTransaction$Op) backStackRecord2.mOps.get(size2);
                    fragmentTransaction$Op5.getClass();
                    int i11 = fragmentTransaction$Op5.mCmd;
                    FragmentManagerImpl fragmentManagerImpl = backStackRecord2.mManager;
                    switch (i11) {
                        case 1:
                            throw null;
                        case 2:
                        default:
                            throw new IllegalArgumentException("Unknown cmd: " + fragmentTransaction$Op5.mCmd);
                        case 3:
                            throw null;
                        case 4:
                            throw null;
                        case 5:
                            throw null;
                        case 6:
                            throw null;
                        case 7:
                            throw null;
                        case 8:
                            fragmentManagerImpl.getClass();
                            break;
                        case 9:
                            fragmentManagerImpl.getClass();
                            break;
                        case 10:
                            fragmentManagerImpl.getClass();
                            throw null;
                    }
                }
            } else {
                backStackRecord2.bumpBackStackNesting();
                int size3 = backStackRecord2.mOps.size();
                for (int i12 = 0; i12 < size3; i12++) {
                    FragmentTransaction$Op fragmentTransaction$Op6 = (FragmentTransaction$Op) backStackRecord2.mOps.get(i12);
                    fragmentTransaction$Op6.getClass();
                    int i13 = fragmentTransaction$Op6.mCmd;
                    FragmentManagerImpl fragmentManagerImpl2 = backStackRecord2.mManager;
                    switch (i13) {
                        case 1:
                            throw null;
                        case 2:
                        default:
                            throw new IllegalArgumentException("Unknown cmd: " + fragmentTransaction$Op6.mCmd);
                        case 3:
                            throw null;
                        case 4:
                            throw null;
                        case 5:
                            throw null;
                        case 6:
                            throw null;
                        case 7:
                            throw null;
                        case 8:
                            fragmentManagerImpl2.getClass();
                            break;
                        case 9:
                            fragmentManagerImpl2.getClass();
                            break;
                        case 10:
                            fragmentManagerImpl2.getClass();
                            throw null;
                    }
                }
            }
        }
        boolean booleanValue = ((Boolean) arrayList2.get(i2 - 1)).booleanValue();
        if (z2 && !this.mBackStackChangeListeners.isEmpty()) {
            LinkedHashSet linkedHashSet = new LinkedHashSet();
            Iterator it2 = arrayList.iterator();
            while (it2.hasNext()) {
                linkedHashSet.addAll(fragmentsFromRecord((BackStackRecord) it2.next()));
            }
            if (this.mTransitioningOp == null) {
                Iterator it3 = this.mBackStackChangeListeners.iterator();
                while (it3.hasNext()) {
                    if (it3.next() != null) {
                        throw new ClassCastException();
                    }
                    Iterator it4 = linkedHashSet.iterator();
                    if (it4.hasNext()) {
                        throw NotificationCompat$Builder$$ExternalSyntheticOutline0.m(it4);
                    }
                }
                Iterator it5 = this.mBackStackChangeListeners.iterator();
                while (it5.hasNext()) {
                    if (it5.next() != null) {
                        throw new ClassCastException();
                    }
                    Iterator it6 = linkedHashSet.iterator();
                    if (it6.hasNext()) {
                        throw NotificationCompat$Builder$$ExternalSyntheticOutline0.m(it6);
                    }
                }
            }
        }
        for (int i14 = i3; i14 < i2; i14++) {
            BackStackRecord backStackRecord3 = (BackStackRecord) arrayList.get(i14);
            if (booleanValue) {
                for (int size4 = backStackRecord3.mOps.size() - 1; size4 >= 0; size4--) {
                    ((FragmentTransaction$Op) backStackRecord3.mOps.get(size4)).getClass();
                }
            } else {
                Iterator it7 = backStackRecord3.mOps.iterator();
                while (it7.hasNext()) {
                    ((FragmentTransaction$Op) it7.next()).getClass();
                }
            }
        }
        moveToState(this.mCurState, true);
        Iterator it8 = ((HashSet) collectChangedControllers(arrayList, i3, i2)).iterator();
        if (it8.hasNext()) {
            ((DefaultSpecialEffectsController) it8.next()).getClass();
            throw null;
        }
        while (i3 < i2) {
            BackStackRecord backStackRecord4 = (BackStackRecord) arrayList.get(i3);
            if (((Boolean) arrayList2.get(i3)).booleanValue() && backStackRecord4.mIndex >= 0) {
                backStackRecord4.mIndex = -1;
            }
            if (backStackRecord4.mCommitRunnables != null) {
                for (int i15 = 0; i15 < backStackRecord4.mCommitRunnables.size(); i15++) {
                    ((Runnable) backStackRecord4.mCommitRunnables.get(i15)).run();
                }
                backStackRecord4.mCommitRunnables = null;
            }
            i3++;
        }
        if (!z2 || this.mBackStackChangeListeners.size() <= 0) {
            return;
        }
        this.mBackStackChangeListeners.get(0).getClass();
        throw new ClassCastException();
    }

    public final void findFragmentById() {
        FragmentStore fragmentStore = this.mFragmentStore;
        for (int size = fragmentStore.mAdded.size() - 1; size >= 0; size--) {
            if (fragmentStore.mAdded.get(size) != null) {
                throw new ClassCastException();
            }
        }
        Iterator it = fragmentStore.mActive.values().iterator();
        while (it.hasNext()) {
            VelocityTrackerCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(it.next());
        }
    }

    public final void moveToState(int i, boolean z) {
        if (this.mHost == null && i != -1) {
            throw new IllegalStateException("No activity");
        }
        if (z || i != this.mCurState) {
            this.mCurState = i;
            FragmentStore fragmentStore = this.mFragmentStore;
            Iterator it = fragmentStore.mAdded.iterator();
            if (it.hasNext()) {
                throw NotificationCompat$Builder$$ExternalSyntheticOutline0.m(it);
            }
            Iterator it2 = fragmentStore.mActive.values().iterator();
            while (it2.hasNext()) {
                VelocityTrackerCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(it2.next());
            }
            Iterator it3 = fragmentStore.getActiveFragmentStateManagers().iterator();
            if (it3.hasNext()) {
                VelocityTrackerCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(it3.next());
                throw null;
            }
        }
    }

    public final boolean popBackStackState(ArrayList arrayList, ArrayList arrayList2) {
        int size = this.mBackStack.isEmpty() ? -1 : this.mBackStack.size() - 1;
        if (size < 0) {
            return false;
        }
        for (int size2 = this.mBackStack.size() - 1; size2 >= size; size2--) {
            arrayList.add((BackStackRecord) this.mBackStack.remove(size2));
            arrayList2.add(Boolean.TRUE);
        }
        return true;
    }

    public final void removeRedundantOperationsAndExecute(ArrayList arrayList, ArrayList arrayList2) {
        if (arrayList.isEmpty()) {
            return;
        }
        if (arrayList.size() != arrayList2.size()) {
            throw new IllegalStateException("Internal error with the back stack records");
        }
        int size = arrayList.size();
        int i = 0;
        int i2 = 0;
        while (i < size) {
            if (!((BackStackRecord) arrayList.get(i)).mReorderingAllowed) {
                if (i2 != i) {
                    executeOpsTogether(arrayList, arrayList2, i2, i);
                }
                i2 = i + 1;
                if (((Boolean) arrayList2.get(i)).booleanValue()) {
                    while (i2 < size && ((Boolean) arrayList2.get(i2)).booleanValue() && !((BackStackRecord) arrayList.get(i2)).mReorderingAllowed) {
                        i2++;
                    }
                }
                executeOpsTogether(arrayList, arrayList2, i, i2);
                i = i2 - 1;
            }
            i++;
        }
        if (i2 != size) {
            executeOpsTogether(arrayList, arrayList2, i2, size);
        }
    }

    public final void scheduleCommit() {
        synchronized (this.mPendingActions) {
            try {
                if (this.mPendingActions.size() == 1) {
                    this.mHost.handler.removeCallbacks(this.mExecCommit);
                    this.mHost.handler.post(this.mExecCommit);
                    updateOnBackPressedCallbackEnabled();
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder(128);
        sb.append("FragmentManager{");
        sb.append(Integer.toHexString(System.identityHashCode(this)));
        sb.append(" in ");
        FragmentActivity$HostCallbacks fragmentActivity$HostCallbacks = this.mHost;
        if (fragmentActivity$HostCallbacks != null) {
            sb.append(fragmentActivity$HostCallbacks.getClass().getSimpleName());
            sb.append("{");
            sb.append(Integer.toHexString(System.identityHashCode(this.mHost)));
            sb.append("}");
        } else {
            sb.append("null");
        }
        sb.append("}}");
        return sb.toString();
    }

    /* JADX WARN: Type inference failed for: r2v6, types: [kotlin.jvm.functions.Function0, kotlin.jvm.internal.FunctionReferenceImpl] */
    /* JADX WARN: Type inference failed for: r5v3, types: [kotlin.jvm.functions.Function0, kotlin.jvm.internal.FunctionReferenceImpl] */
    public final void updateOnBackPressedCallbackEnabled() {
        synchronized (this.mPendingActions) {
            try {
                if (!this.mPendingActions.isEmpty()) {
                    FragmentManager$1 fragmentManager$1 = this.mOnBackPressedCallback;
                    fragmentManager$1.isEnabled = true;
                    ?? r2 = fragmentManager$1.enabledChangedCallback;
                    if (r2 != 0) {
                        r2.invoke();
                    }
                    if (Log.isLoggable("FragmentManager", 3)) {
                        Log.d("FragmentManager", "FragmentManager " + this + " enabling OnBackPressedCallback, caused by non-empty pending actions");
                    }
                    return;
                }
                boolean z = this.mBackStack.size() + (this.mTransitioningOp != null ? 1 : 0) > 0;
                if (Log.isLoggable("FragmentManager", 3)) {
                    Log.d("FragmentManager", "OnBackPressedCallback for FragmentManager " + this + " enabled state is " + z);
                }
                FragmentManager$1 fragmentManager$12 = this.mOnBackPressedCallback;
                fragmentManager$12.isEnabled = z;
                ?? r5 = fragmentManager$12.enabledChangedCallback;
                if (r5 != 0) {
                    r5.invoke();
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }
}
