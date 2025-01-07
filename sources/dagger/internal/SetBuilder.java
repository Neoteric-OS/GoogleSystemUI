package dagger.internal;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class SetBuilder {
    public final List contributions;

    public SetBuilder(int i) {
        this.contributions = new ArrayList(i);
    }

    public final void add(Object obj) {
        List list = this.contributions;
        Preconditions.checkNotNull(obj, "Set contributions cannot be null");
        list.add(obj);
    }

    public final void addAll(Collection collection) {
        Iterator it = collection.iterator();
        while (it.hasNext()) {
            Preconditions.checkNotNull(it.next(), "Set contributions cannot be null");
        }
        this.contributions.addAll(collection);
    }

    public final Set build() {
        return this.contributions.isEmpty() ? Collections.emptySet() : ((ArrayList) this.contributions).size() == 1 ? Collections.singleton(((ArrayList) this.contributions).get(0)) : Collections.unmodifiableSet(new HashSet(this.contributions));
    }
}
