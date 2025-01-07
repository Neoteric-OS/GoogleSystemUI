package com.android.systemui.statusbar.pipeline.shared.ui.model;

import android.content.Context;
import android.graphics.drawable.Drawable;
import com.android.settingslib.graph.SignalDrawable;
import com.android.systemui.plugins.qs.QSTile;
import java.util.Arrays;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class SignalIcon extends QSTile.Icon {
    public final int state;

    public SignalIcon(int i) {
        this.state = i;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof SignalIcon) && this.state == ((SignalIcon) obj).state;
    }

    @Override // com.android.systemui.plugins.qs.QSTile.Icon
    public final Drawable getDrawable(Context context) {
        SignalDrawable signalDrawable = new SignalDrawable(context);
        signalDrawable.setLevel(this.state);
        return signalDrawable;
    }

    @Override // com.android.systemui.plugins.qs.QSTile.Icon
    public final int hashCode() {
        return Integer.hashCode(this.state);
    }

    @Override // com.android.systemui.plugins.qs.QSTile.Icon
    public final String toString() {
        return String.format("SignalIcon[mState=0x%08x]", Arrays.copyOf(new Object[]{Integer.valueOf(this.state)}, 1));
    }
}
