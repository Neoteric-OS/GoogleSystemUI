package com.android.systemui.statusbar.events;

import android.content.Context;
import com.android.systemui.statusbar.ConnectedDisplayChip;
import kotlin.jvm.functions.Function1;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class ConnectedDisplayEvent implements StatusEvent {
    public String contentDescription = "";
    public final Function1 viewCreator = new Function1() { // from class: com.android.systemui.statusbar.events.ConnectedDisplayEvent$viewCreator$1
        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Object obj) {
            return new ConnectedDisplayChip((Context) obj);
        }
    };

    @Override // com.android.systemui.statusbar.events.StatusEvent
    public final String getContentDescription() {
        return this.contentDescription;
    }

    @Override // com.android.systemui.statusbar.events.StatusEvent
    public final boolean getForceVisible() {
        return false;
    }

    @Override // com.android.systemui.statusbar.events.StatusEvent
    public final int getPriority() {
        return 60;
    }

    @Override // com.android.systemui.statusbar.events.StatusEvent
    public final boolean getShouldAnnounceAccessibilityEvent() {
        return true;
    }

    @Override // com.android.systemui.statusbar.events.StatusEvent
    public final boolean getShowAnimation() {
        return true;
    }

    @Override // com.android.systemui.statusbar.events.StatusEvent
    public final Function1 getViewCreator() {
        return this.viewCreator;
    }

    public final String toString() {
        return "ConnectedDisplayEvent";
    }

    @Override // com.android.systemui.statusbar.events.StatusEvent
    public final void setForceVisible() {
    }
}
