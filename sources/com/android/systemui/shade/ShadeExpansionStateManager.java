package com.android.systemui.shade;

import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class ShadeExpansionStateManager {
    public boolean expanded;
    public float fraction;
    public int state;
    public boolean tracking;
    public final CopyOnWriteArrayList expansionListeners = new CopyOnWriteArrayList();
    public final CopyOnWriteArrayList stateListeners = new CopyOnWriteArrayList();

    public final ShadeExpansionChangeEvent addExpansionListener(ShadeExpansionListener shadeExpansionListener) {
        this.expansionListeners.add(shadeExpansionListener);
        return new ShadeExpansionChangeEvent(this.fraction, this.expanded, this.tracking);
    }

    public final void updateStateInternal(int i) {
        ShadeExpansionStateManagerKt.panelStateToString(this.state);
        ShadeExpansionStateManagerKt.panelStateToString(i);
        this.state = i;
        Iterator it = this.stateListeners.iterator();
        while (it.hasNext()) {
            ((ShadeStateListener) it.next()).onPanelStateChanged(i);
        }
    }
}
