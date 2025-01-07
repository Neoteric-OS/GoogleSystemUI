package com.android.wm.shell.shared.bubbles;

import android.content.Context;
import android.content.res.Configuration;
import android.widget.FrameLayout;
import android.widget.ImageView;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class DismissCircleView extends FrameLayout {
    public int mBackgroundResId;
    public int mIconSizeResId;
    public final ImageView mIconView;

    public DismissCircleView(Context context) {
        super(context);
        ImageView imageView = new ImageView(getContext());
        this.mIconView = imageView;
        addView(imageView);
    }

    @Override // android.view.View
    public final void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        setBackground(getContext().getDrawable(this.mBackgroundResId));
        int dimensionPixelSize = getResources().getDimensionPixelSize(this.mIconSizeResId);
        this.mIconView.setLayoutParams(new FrameLayout.LayoutParams(dimensionPixelSize, dimensionPixelSize, 17));
    }
}
