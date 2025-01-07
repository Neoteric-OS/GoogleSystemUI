package androidx.fragment.app;

import java.util.ArrayList;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class BackStackRecord implements FragmentManager$OpGenerator {
    public boolean mAddToBackStack;
    public int mBreadCrumbShortTitleRes;
    public CharSequence mBreadCrumbShortTitleText;
    public int mBreadCrumbTitleRes;
    public CharSequence mBreadCrumbTitleText;
    public ArrayList mCommitRunnables;
    public boolean mCommitted;
    public int mEnterAnim;
    public int mExitAnim;
    public int mIndex;
    public final FragmentManagerImpl mManager;
    public String mName;
    public final ArrayList mOps;
    public int mPopEnterAnim;
    public int mPopExitAnim;
    public boolean mReorderingAllowed;
    public ArrayList mSharedElementSourceNames;
    public ArrayList mSharedElementTargetNames;
    public int mTransition;

    public BackStackRecord(FragmentManagerImpl fragmentManagerImpl) {
        FragmentManager$3 fragmentManager$3 = fragmentManagerImpl.mHostFragmentFactory;
        FragmentActivity$HostCallbacks fragmentActivity$HostCallbacks = fragmentManagerImpl.mHost;
        if (fragmentActivity$HostCallbacks != null) {
            fragmentActivity$HostCallbacks.context.getClassLoader();
        }
        this.mOps = new ArrayList();
        this.mReorderingAllowed = false;
        this.mIndex = -1;
        this.mManager = fragmentManagerImpl;
    }

    public final void bumpBackStackNesting() {
        if (this.mAddToBackStack) {
            int size = this.mOps.size();
            for (int i = 0; i < size; i++) {
                ((FragmentTransaction$Op) this.mOps.get(i)).getClass();
            }
        }
    }

    public final int commitInternal(boolean z) {
        if (this.mCommitted) {
            throw new IllegalStateException("commit already called");
        }
        this.mCommitted = true;
        boolean z2 = this.mAddToBackStack;
        FragmentManagerImpl fragmentManagerImpl = this.mManager;
        if (z2) {
            this.mIndex = fragmentManagerImpl.mBackStackIndex.getAndIncrement();
        } else {
            this.mIndex = -1;
        }
        if (z) {
            fragmentManagerImpl.enqueueAction(this);
        }
        return this.mIndex;
    }

    @Override // androidx.fragment.app.FragmentManager$OpGenerator
    public final boolean generateOps(ArrayList arrayList, ArrayList arrayList2) {
        arrayList.add(this);
        arrayList2.add(Boolean.FALSE);
        if (!this.mAddToBackStack) {
            return true;
        }
        this.mManager.mBackStack.add(this);
        return true;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder(128);
        sb.append("BackStackEntry{");
        sb.append(Integer.toHexString(System.identityHashCode(this)));
        if (this.mIndex >= 0) {
            sb.append(" #");
            sb.append(this.mIndex);
        }
        if (this.mName != null) {
            sb.append(" ");
            sb.append(this.mName);
        }
        sb.append("}");
        return sb.toString();
    }
}
