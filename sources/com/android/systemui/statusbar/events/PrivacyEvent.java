package com.android.systemui.statusbar.events;

import android.content.Context;
import com.android.systemui.privacy.OngoingPrivacyChip;
import java.util.List;
import kotlin.collections.EmptyList;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class PrivacyEvent implements StatusEvent {
    public String contentDescription;
    public OngoingPrivacyChip privacyChip;
    public final boolean showAnimation;
    public boolean forceVisible = true;
    public List privacyItems = EmptyList.INSTANCE;
    public final Function1 viewCreator = new Function1() { // from class: com.android.systemui.statusbar.events.PrivacyEvent$viewCreator$1
        {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Object obj) {
            OngoingPrivacyChip ongoingPrivacyChip = new OngoingPrivacyChip((Context) obj, null, 0, 0, 14, null);
            ongoingPrivacyChip.setPrivacyList(PrivacyEvent.this.privacyItems);
            ongoingPrivacyChip.setContentDescription(PrivacyEvent.this.contentDescription);
            PrivacyEvent.this.privacyChip = ongoingPrivacyChip;
            return ongoingPrivacyChip;
        }
    };

    public PrivacyEvent(boolean z) {
        this.showAnimation = z;
    }

    @Override // com.android.systemui.statusbar.events.StatusEvent
    public final String getContentDescription() {
        return this.contentDescription;
    }

    @Override // com.android.systemui.statusbar.events.StatusEvent
    public final boolean getForceVisible() {
        return this.forceVisible;
    }

    @Override // com.android.systemui.statusbar.events.StatusEvent
    public final int getPriority() {
        return 100;
    }

    @Override // com.android.systemui.statusbar.events.StatusEvent
    public final boolean getShouldAnnounceAccessibilityEvent() {
        return false;
    }

    @Override // com.android.systemui.statusbar.events.StatusEvent
    public final boolean getShowAnimation() {
        return this.showAnimation;
    }

    @Override // com.android.systemui.statusbar.events.StatusEvent
    public final Function1 getViewCreator() {
        return this.viewCreator;
    }

    @Override // com.android.systemui.statusbar.events.StatusEvent
    public final void setForceVisible() {
        this.forceVisible = false;
    }

    @Override // com.android.systemui.statusbar.events.StatusEvent
    public final boolean shouldUpdateFromEvent(StatusEvent statusEvent) {
        if (statusEvent instanceof PrivacyEvent) {
            PrivacyEvent privacyEvent = (PrivacyEvent) statusEvent;
            if (!Intrinsics.areEqual(privacyEvent.privacyItems, this.privacyItems) || !Intrinsics.areEqual(privacyEvent.contentDescription, this.contentDescription) || (privacyEvent.forceVisible && !this.forceVisible)) {
                return true;
            }
        }
        return false;
    }

    public final String toString() {
        return "PrivacyEvent(forceVisible=" + this.forceVisible + ", privacyItems=" + this.privacyItems + ")";
    }

    @Override // com.android.systemui.statusbar.events.StatusEvent
    public final void updateFromEvent(StatusEvent statusEvent) {
        if (statusEvent instanceof PrivacyEvent) {
            PrivacyEvent privacyEvent = (PrivacyEvent) statusEvent;
            this.privacyItems = privacyEvent.privacyItems;
            this.contentDescription = privacyEvent.contentDescription;
            OngoingPrivacyChip ongoingPrivacyChip = this.privacyChip;
            if (ongoingPrivacyChip != null) {
                ongoingPrivacyChip.setContentDescription(privacyEvent.contentDescription);
            }
            OngoingPrivacyChip ongoingPrivacyChip2 = this.privacyChip;
            if (ongoingPrivacyChip2 != null) {
                ongoingPrivacyChip2.setPrivacyList(privacyEvent.privacyItems);
            }
            if (privacyEvent.forceVisible) {
                this.forceVisible = true;
            }
        }
    }
}
