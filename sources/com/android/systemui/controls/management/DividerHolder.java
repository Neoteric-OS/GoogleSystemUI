package com.android.systemui.controls.management;

import android.view.View;
import com.android.wm.shell.R;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class DividerHolder extends Holder {
    public final View divider;
    public final View frame;

    public DividerHolder(View view) {
        super(view);
        this.frame = view.requireViewById(R.id.frame);
        this.divider = view.requireViewById(R.id.divider);
    }

    @Override // com.android.systemui.controls.management.Holder
    public final void bindData(ElementWrapper elementWrapper) {
        DividerWrapper dividerWrapper = (DividerWrapper) elementWrapper;
        this.frame.setVisibility(dividerWrapper.showNone ? 0 : 8);
        this.divider.setVisibility(dividerWrapper.showDivider ? 0 : 8);
    }
}
