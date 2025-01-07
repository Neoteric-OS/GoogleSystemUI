package com.android.systemui.statusbar;

import android.content.Context;
import android.content.res.Configuration;
import android.widget.FrameLayout;
import com.android.systemui.statusbar.events.BackgroundAnimatableView;
import com.android.wm.shell.R;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class ConnectedDisplayChip extends FrameLayout implements BackgroundAnimatableView {
    public final FrameLayout iconContainer;

    public ConnectedDisplayChip(Context context) {
        super(context, null);
        FrameLayout.inflate(context, R.layout.connected_display_chip, this);
        this.iconContainer = (FrameLayout) requireViewById(R.id.icons_rounded_container);
    }

    @Override // android.view.View
    public final void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        this.iconContainer.setBackground(getContext().getDrawable(R.drawable.statusbar_chip_bg));
    }

    @Override // com.android.systemui.statusbar.events.BackgroundAnimatableView
    public final void setBoundsForAnimation(int i, int i2, int i3, int i4) {
        this.iconContainer.setLeftTopRightBottom(i - getLeft(), i2 - getTop(), i3 - getLeft(), i4 - getTop());
    }
}
