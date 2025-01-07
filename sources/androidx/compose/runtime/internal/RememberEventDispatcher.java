package androidx.compose.runtime.internal;

import android.os.Trace;
import androidx.collection.MutableIntList;
import androidx.collection.MutableScatterSet;
import androidx.compose.runtime.ComposeNodeLifecycleCallback;
import androidx.compose.runtime.RememberObserver;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.functions.Function0;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class RememberEventDispatcher {
    public final Set abandoning;
    public final MutableIntList afters;
    public final List currentRememberingList;
    public final List leaving;
    public final List pending;
    public final MutableIntList priorities;
    public MutableScatterSet releasing;
    public final List remembering;
    public final List sideEffects;

    public RememberEventDispatcher(Set set) {
        this.abandoning = set;
        ArrayList arrayList = new ArrayList();
        this.remembering = arrayList;
        this.currentRememberingList = arrayList;
        this.leaving = new ArrayList();
        this.sideEffects = new ArrayList();
        this.pending = new ArrayList();
        this.priorities = new MutableIntList();
        this.afters = new MutableIntList();
    }

    public final void dispatchAbandons() {
        if (this.abandoning.isEmpty()) {
            return;
        }
        Trace.beginSection("Compose:abandons");
        try {
            Iterator it = this.abandoning.iterator();
            while (it.hasNext()) {
                RememberObserver rememberObserver = (RememberObserver) it.next();
                it.remove();
                rememberObserver.onAbandoned();
            }
        } finally {
            Trace.endSection();
        }
    }

    public final void dispatchRememberObservers() {
        processPendingLeaving(Integer.MIN_VALUE);
        if (!this.leaving.isEmpty()) {
            Trace.beginSection("Compose:onForgotten");
            try {
                MutableScatterSet mutableScatterSet = this.releasing;
                int size = ((ArrayList) this.leaving).size();
                while (true) {
                    size--;
                    if (-1 >= size) {
                        break;
                    }
                    Object obj = ((ArrayList) this.leaving).get(size);
                    if (obj instanceof RememberObserver) {
                        this.abandoning.remove(obj);
                        ((RememberObserver) obj).onForgotten();
                    }
                    if (obj instanceof ComposeNodeLifecycleCallback) {
                        if (mutableScatterSet == null || !mutableScatterSet.contains(obj)) {
                            ((ComposeNodeLifecycleCallback) obj).onDeactivate();
                        } else {
                            ((ComposeNodeLifecycleCallback) obj).onRelease();
                        }
                    }
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        if (this.remembering.isEmpty()) {
            return;
        }
        Trace.beginSection("Compose:onRemembered");
        try {
            ArrayList arrayList = (ArrayList) this.remembering;
            int size2 = arrayList.size();
            for (int i = 0; i < size2; i++) {
                RememberObserver rememberObserver = (RememberObserver) arrayList.get(i);
                this.abandoning.remove(rememberObserver);
                rememberObserver.onRemembered();
            }
        } finally {
            Trace.endSection();
        }
    }

    public final void dispatchSideEffects() {
        if (this.sideEffects.isEmpty()) {
            return;
        }
        Trace.beginSection("Compose:sideeffects");
        try {
            List list = this.sideEffects;
            int size = list.size();
            for (int i = 0; i < size; i++) {
                ((Function0) ((ArrayList) list).get(i)).invoke();
            }
            this.sideEffects.clear();
            Trace.endSection();
        } catch (Throwable th) {
            Trace.endSection();
            throw th;
        }
    }

    public final void processPendingLeaving(int i) {
        if (this.pending.isEmpty()) {
            return;
        }
        int i2 = 0;
        List list = null;
        int i3 = 0;
        MutableIntList mutableIntList = null;
        MutableIntList mutableIntList2 = null;
        while (true) {
            MutableIntList mutableIntList3 = this.afters;
            if (i3 >= mutableIntList3._size) {
                break;
            }
            if (i <= mutableIntList3.get(i3)) {
                Object remove = this.pending.remove(i3);
                int removeAt = mutableIntList3.removeAt(i3);
                int removeAt2 = this.priorities.removeAt(i3);
                if (list == null) {
                    list = CollectionsKt__CollectionsKt.mutableListOf(remove);
                    mutableIntList2 = new MutableIntList();
                    mutableIntList2.add(removeAt);
                    mutableIntList = new MutableIntList();
                    mutableIntList.add(removeAt2);
                } else {
                    list.add(remove);
                    mutableIntList2.add(removeAt);
                    mutableIntList.add(removeAt2);
                }
            } else {
                i3++;
            }
        }
        if (list != null) {
            int size = list.size() - 1;
            while (i2 < size) {
                int i4 = i2 + 1;
                int size2 = list.size();
                for (int i5 = i4; i5 < size2; i5++) {
                    int i6 = mutableIntList2.get(i2);
                    int i7 = mutableIntList2.get(i5);
                    if (i6 < i7 || (i7 == i6 && mutableIntList.get(i2) < mutableIntList.get(i5))) {
                        Object obj = list.get(i2);
                        list.set(i2, list.get(i5));
                        list.set(i5, obj);
                        int i8 = mutableIntList.get(i2);
                        mutableIntList.set(i2, mutableIntList.get(i5));
                        mutableIntList.set(i5, i8);
                        int i9 = mutableIntList2.get(i2);
                        mutableIntList2.set(i2, mutableIntList2.get(i5));
                        mutableIntList2.set(i5, i9);
                    }
                }
                i2 = i4;
            }
            this.leaving.addAll(list);
        }
    }

    public final void recordLeaving(Object obj, int i, int i2, int i3) {
        processPendingLeaving(i);
        if (i3 < 0 || i3 >= i) {
            this.leaving.add(obj);
            return;
        }
        this.pending.add(obj);
        this.priorities.add(i2);
        this.afters.add(i3);
    }
}
