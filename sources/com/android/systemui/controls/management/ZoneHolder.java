package com.android.systemui.controls.management;

import android.view.View;
import android.widget.TextView;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class ZoneHolder extends Holder {
    public final TextView zone;

    public ZoneHolder(View view) {
        super(view);
        this.zone = (TextView) view;
    }

    @Override // com.android.systemui.controls.management.Holder
    public final void bindData(ElementWrapper elementWrapper) {
        this.zone.setText(((ZoneNameWrapper) elementWrapper).zoneName);
    }
}
