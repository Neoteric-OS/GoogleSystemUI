package androidx.core.view;

import androidx.fragment.app.FragmentManager$2;
import androidx.fragment.app.FragmentManagerImpl;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class MenuHostHelper {
    public final Runnable mOnInvalidateMenuCallback;
    public final CopyOnWriteArrayList mMenuProviders = new CopyOnWriteArrayList();
    public final Map mProviderToLifecycleContainers = new HashMap();

    public MenuHostHelper(Runnable runnable) {
        this.mOnInvalidateMenuCallback = runnable;
    }

    public final void onCreateMenu() {
        Iterator it = this.mMenuProviders.iterator();
        while (it.hasNext()) {
            FragmentManagerImpl fragmentManagerImpl = ((FragmentManager$2) it.next()).this$0;
            if (fragmentManagerImpl.mCurState >= 1) {
                Iterator it2 = fragmentManagerImpl.mFragmentStore.getFragments().iterator();
                while (it2.hasNext()) {
                    if (it2.next() != null) {
                        throw new ClassCastException();
                    }
                }
            }
        }
    }

    public final void onMenuItemSelected() {
        Iterator it = this.mMenuProviders.iterator();
        while (it.hasNext()) {
            FragmentManagerImpl fragmentManagerImpl = ((FragmentManager$2) it.next()).this$0;
            if (fragmentManagerImpl.mCurState >= 1) {
                Iterator it2 = fragmentManagerImpl.mFragmentStore.getFragments().iterator();
                while (it2.hasNext()) {
                    if (it2.next() != null) {
                        throw new ClassCastException();
                    }
                }
            }
        }
    }

    public final void onPrepareMenu() {
        Iterator it = this.mMenuProviders.iterator();
        while (it.hasNext()) {
            FragmentManagerImpl fragmentManagerImpl = ((FragmentManager$2) it.next()).this$0;
            if (fragmentManagerImpl.mCurState >= 1) {
                Iterator it2 = fragmentManagerImpl.mFragmentStore.getFragments().iterator();
                while (it2.hasNext()) {
                    if (it2.next() != null) {
                        throw new ClassCastException();
                    }
                }
            }
        }
    }
}
