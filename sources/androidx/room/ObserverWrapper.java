package androidx.room;

import androidx.room.InvalidationTracker;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.Set;
import kotlin.collections.EmptySet;
import kotlin.collections.builders.SetBuilder;
import kotlin.text.StringsKt__StringsJVMKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class ObserverWrapper {
    public final InvalidationTracker.Observer observer;
    public final Set singleTableSet;
    public final int[] tableIds;
    public final String[] tableNames;

    public ObserverWrapper(InvalidationTracker.Observer observer, int[] iArr, String[] strArr) {
        this.observer = observer;
        this.tableIds = iArr;
        this.tableNames = strArr;
        if (iArr.length != strArr.length) {
            throw new IllegalStateException("Check failed.");
        }
        this.singleTableSet = !(strArr.length == 0) ? Collections.singleton(strArr[0]) : EmptySet.INSTANCE;
    }

    public final void notifyByTableIds$room_runtime_release(Set set) {
        Set set2;
        int[] iArr = this.tableIds;
        int length = iArr.length;
        if (length != 0) {
            int i = 0;
            if (length != 1) {
                SetBuilder setBuilder = new SetBuilder();
                int length2 = iArr.length;
                int i2 = 0;
                while (i < length2) {
                    int i3 = i2 + 1;
                    if (set.contains(Integer.valueOf(iArr[i]))) {
                        setBuilder.add(this.tableNames[i2]);
                    }
                    i++;
                    i2 = i3;
                }
                set2 = setBuilder.build();
            } else {
                set2 = set.contains(Integer.valueOf(iArr[0])) ? this.singleTableSet : EmptySet.INSTANCE;
            }
        } else {
            set2 = EmptySet.INSTANCE;
        }
        if (set2.isEmpty()) {
            return;
        }
        this.observer.onInvalidated(set2);
    }

    public final void notifyByTableNames$room_runtime_release(Set set) {
        Set set2;
        String[] strArr = this.tableNames;
        int length = strArr.length;
        if (length == 0) {
            set2 = EmptySet.INSTANCE;
        } else if (length != 1) {
            SetBuilder setBuilder = new SetBuilder();
            Iterator it = set.iterator();
            while (it.hasNext()) {
                String str = (String) it.next();
                int length2 = strArr.length;
                int i = 0;
                while (true) {
                    if (i < length2) {
                        String str2 = strArr[i];
                        if (StringsKt__StringsJVMKt.equals(str2, str, true)) {
                            setBuilder.add(str2);
                            break;
                        }
                        i++;
                    }
                }
            }
            set2 = setBuilder.build();
        } else {
            Set set3 = set;
            if (!(set3 instanceof Collection) || !set3.isEmpty()) {
                Iterator it2 = set3.iterator();
                while (it2.hasNext()) {
                    if (StringsKt__StringsJVMKt.equals((String) it2.next(), strArr[0], true)) {
                        set2 = this.singleTableSet;
                        break;
                    }
                }
            }
            set2 = EmptySet.INSTANCE;
        }
        if (set2.isEmpty()) {
            return;
        }
        this.observer.onInvalidated(set2);
    }
}
