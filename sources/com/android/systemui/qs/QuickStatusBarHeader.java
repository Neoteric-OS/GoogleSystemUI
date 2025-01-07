package com.android.systemui.qs;

import android.content.Context;
import android.content.res.Configuration;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import com.android.internal.policy.SystemBarUtils;
import com.android.wm.shell.R;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public class QuickStatusBarHeader extends FrameLayout {
    public boolean mExpanded;
    public QuickQSPanel mHeaderQsPanel;
    public boolean mQsDisabled;

    public QuickStatusBarHeader(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override // android.view.View
    public final void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        updateResources();
    }

    @Override // android.view.View
    public final void onFinishInflate() {
        super.onFinishInflate();
        this.mHeaderQsPanel = (QuickQSPanel) findViewById(R.id.quick_qs_panel);
        updateResources();
    }

    @Override // android.view.View
    public final boolean onTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getY() > this.mHeaderQsPanel.getTop()) {
            return super.onTouchEvent(motionEvent);
        }
        return false;
    }

    public final void updateResources() {
        boolean z = ((FrameLayout) this).mContext.getResources().getBoolean(R.bool.config_use_large_screen_shade_header);
        ViewGroup.LayoutParams layoutParams = getLayoutParams();
        if (this.mQsDisabled) {
            layoutParams.height = 0;
        } else {
            layoutParams.height = -2;
        }
        setLayoutParams(layoutParams);
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) this.mHeaderQsPanel.getLayoutParams();
        if (z) {
            marginLayoutParams.topMargin = ((FrameLayout) this).mContext.getResources().getDimensionPixelSize(R.dimen.qqs_layout_margin_top);
        } else {
            Context context = ((FrameLayout) this).mContext;
            marginLayoutParams.topMargin = Math.max(context.getResources().getDimensionPixelSize(R.dimen.large_screen_shade_header_height), SystemBarUtils.getStatusBarHeight(context));
        }
        this.mHeaderQsPanel.setLayoutParams(marginLayoutParams);
    }
}
