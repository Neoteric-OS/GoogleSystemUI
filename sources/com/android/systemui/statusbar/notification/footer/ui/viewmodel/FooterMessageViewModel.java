package com.android.systemui.statusbar.notification.footer.ui.viewmodel;

import androidx.compose.animation.core.KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0;
import androidx.compose.foundation.text.ValidatingOffsetMappingKt$$ExternalSyntheticOutline0;
import com.android.wm.shell.R;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.flow.StateFlowImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class FooterMessageViewModel {
    public final StateFlowImpl isVisible;

    public FooterMessageViewModel(StateFlowImpl stateFlowImpl) {
        this.isVisible = stateFlowImpl;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof FooterMessageViewModel)) {
            return false;
        }
        FooterMessageViewModel footerMessageViewModel = (FooterMessageViewModel) obj;
        footerMessageViewModel.getClass();
        return Intrinsics.areEqual(this.isVisible, footerMessageViewModel.isVisible);
    }

    public final int hashCode() {
        return this.isVisible.hashCode() + KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0.m(R.drawable.ic_friction_lock_closed, Integer.hashCode(R.string.unlock_to_see_notif_text) * 31, 31);
    }

    public final String toString() {
        StringBuilder m = ValidatingOffsetMappingKt$$ExternalSyntheticOutline0.m(R.string.unlock_to_see_notif_text, R.drawable.ic_friction_lock_closed, "FooterMessageViewModel(messageId=", ", iconId=", ", isVisible=");
        m.append(this.isVisible);
        m.append(")");
        return m.toString();
    }
}
