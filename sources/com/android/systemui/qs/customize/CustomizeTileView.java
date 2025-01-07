package com.android.systemui.qs.customize;

import android.text.TextUtils;
import android.view.ViewGroup;
import android.widget.TextView;
import com.android.systemui.plugins.qs.QSTile;
import com.android.systemui.qs.tileimpl.QSTileViewImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class CustomizeTileView extends QSTileViewImpl {
    public boolean showAppLabel;
    public boolean showSideView;

    @Override // com.android.systemui.qs.tileimpl.QSTileViewImpl
    public final boolean animationsEnabled() {
        return false;
    }

    @Override // com.android.systemui.qs.tileimpl.QSTileViewImpl
    public final void handleStateChanged(QSTile.State state) {
        super.handleStateChanged(state);
        TextView textView = this.secondaryLabel;
        if (textView == null) {
            textView = null;
        }
        textView.setVisibility((!this.showAppLabel || TextUtils.isEmpty(state.secondaryLabel)) ? 8 : 0);
        if (this.showSideView) {
            return;
        }
        ViewGroup viewGroup = this.sideView;
        (viewGroup != null ? viewGroup : null).setVisibility(8);
    }

    @Override // android.view.View
    public final boolean isLongClickable() {
        return false;
    }
}
