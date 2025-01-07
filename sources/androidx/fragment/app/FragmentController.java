package androidx.fragment.app;

import java.util.Iterator;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class FragmentController {
    public final FragmentActivity$HostCallbacks mHost;

    public FragmentController(FragmentActivity$HostCallbacks fragmentActivity$HostCallbacks) {
        this.mHost = fragmentActivity$HostCallbacks;
    }

    public final void noteStateNotSaved() {
        FragmentManagerImpl fragmentManagerImpl = this.mHost.fragmentManager;
        if (fragmentManagerImpl.mHost == null) {
            return;
        }
        fragmentManagerImpl.mStateSaved = false;
        fragmentManagerImpl.mStopped = false;
        fragmentManagerImpl.mNonConfig.getClass();
        Iterator it = fragmentManagerImpl.mFragmentStore.getFragments().iterator();
        while (it.hasNext()) {
            if (it.next() != null) {
                throw new ClassCastException();
            }
        }
    }
}
