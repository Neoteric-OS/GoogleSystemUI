package com.android.systemui.accessibility.floatingmenu;

import android.content.ComponentCallbacks;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Rect;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.android.wm.shell.R;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class MenuMessageView extends LinearLayout implements ViewTreeObserver.OnComputeInternalInsetsListener, ComponentCallbacks {
    public final TextView mTextView;
    public final Button mUndoButton;

    public MenuMessageView(Context context) {
        super(context);
        setLayoutDirection(3);
        setVisibility(8);
        TextView textView = new TextView(context);
        this.mTextView = textView;
        Button button = new Button(context);
        this.mUndoButton = button;
        addView(textView, 0, new LinearLayout.LayoutParams(0, -2, 1.0f));
        addView(button, 1, new LinearLayout.LayoutParams(-2, -2));
        setClickable(false);
        setFocusable(false);
        setAccessibilityLiveRegion(1);
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-2, -2);
        layoutParams.gravity = 81;
        setLayoutParams(layoutParams);
        setGravity(16);
        this.mUndoButton.setBackground(null);
        updateResources();
        getContext().registerComponentCallbacks(this);
        getViewTreeObserver().addOnComputeInternalInsetsListener(this);
    }

    public final void onComputeInternalInsets(ViewTreeObserver.InternalInsetsInfo internalInsetsInfo) {
        internalInsetsInfo.setTouchableInsets(3);
        if (getVisibility() == 0) {
            int x = (int) getX();
            int y = (int) getY();
            internalInsetsInfo.touchableRegion.union(new Rect(x, y, getWidth() + x, getHeight() + y));
        }
    }

    @Override // android.view.View, android.content.ComponentCallbacks
    public final void onConfigurationChanged(Configuration configuration) {
        updateResources();
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        getContext().unregisterComponentCallbacks(this);
        getViewTreeObserver().removeOnComputeInternalInsetsListener(this);
    }

    public final void updateResources() {
        Resources resources = getResources();
        int dimensionPixelSize = resources.getDimensionPixelSize(R.dimen.accessibility_floating_menu_message_container_horizontal_padding);
        int dimensionPixelSize2 = resources.getDimensionPixelSize(R.dimen.accessibility_floating_menu_message_margin);
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) getLayoutParams();
        layoutParams.setMargins(dimensionPixelSize2, dimensionPixelSize2, dimensionPixelSize2, dimensionPixelSize2);
        setLayoutParams(layoutParams);
        setBackground(resources.getDrawable(R.drawable.accessibility_floating_message_background));
        setPadding(dimensionPixelSize, 0, dimensionPixelSize, 0);
        setMinimumWidth(resources.getDimensionPixelSize(R.dimen.accessibility_floating_menu_message_min_width));
        setMinimumHeight(resources.getDimensionPixelSize(R.dimen.accessibility_floating_menu_message_min_height));
        setElevation(resources.getDimensionPixelSize(R.dimen.accessibility_floating_menu_message_elevation));
        int dimensionPixelSize3 = resources.getDimensionPixelSize(R.dimen.accessibility_floating_menu_message_text_vertical_padding);
        int color = resources.getColor(R.color.accessibility_floating_menu_message_text);
        int dimensionPixelSize4 = resources.getDimensionPixelSize(R.dimen.accessibility_floating_menu_message_text_size);
        this.mTextView.setPadding(0, dimensionPixelSize3, 0, dimensionPixelSize3);
        float f = dimensionPixelSize4;
        this.mTextView.setTextSize(0, f);
        this.mTextView.setTextColor(color);
        this.mTextView.setHyphenationFrequency(1);
        this.mUndoButton.setText(resources.getString(R.string.accessibility_floating_button_undo));
        this.mUndoButton.setTextSize(0, f);
        this.mUndoButton.setTextColor(color);
        this.mUndoButton.setAllCaps(true);
    }

    @Override // android.content.ComponentCallbacks
    public final void onLowMemory() {
    }
}
