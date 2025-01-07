package com.android.systemui.accessibility.floatingmenu;

import android.content.ComponentCallbacks;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.CornerPathEffect;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.android.settingslib.Utils;
import com.android.systemui.recents.TriangleShape;
import com.android.wm.shell.R;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class MenuEduTooltipView extends FrameLayout implements ComponentCallbacks {
    public int mArrowCornerRadius;
    public int mArrowHeight;
    public final View mArrowLeftView;
    public int mArrowMargin;
    public final View mArrowRightView;
    public int mArrowWidth;
    public int mColorAccentPrimary;
    public int mFontSize;
    public final MenuViewAppearance mMenuViewAppearance;
    public final TextView mMessageView;
    public int mTextViewCornerRadius;
    public int mTextViewMargin;
    public int mTextViewPadding;

    public MenuEduTooltipView(Context context, MenuViewAppearance menuViewAppearance) {
        super(context);
        this.mMenuViewAppearance = menuViewAppearance;
        updateResources();
        View inflate = LayoutInflater.from(getContext()).inflate(R.layout.accessibility_floating_menu_tooltip, (ViewGroup) this, false);
        TextView textView = (TextView) inflate.findViewById(R.id.text);
        this.mMessageView = textView;
        textView.setMovementMethod(LinkMovementMethod.getInstance());
        this.mArrowLeftView = inflate.findViewById(R.id.arrow_left);
        this.mArrowRightView = inflate.findViewById(R.id.arrow_right);
        updateMessageView();
        drawArrow(this.mArrowLeftView, true);
        drawArrow(this.mArrowRightView, false);
        addView(inflate);
    }

    public final void drawArrow(View view, boolean z) {
        ShapeDrawable shapeDrawable = new ShapeDrawable(TriangleShape.createHorizontal(this.mArrowWidth, this.mArrowHeight, z));
        Paint paint = shapeDrawable.getPaint();
        paint.setColor(this.mColorAccentPrimary);
        paint.setPathEffect(new CornerPathEffect(this.mArrowCornerRadius));
        view.setBackground(shapeDrawable);
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        getContext().registerComponentCallbacks(this);
    }

    @Override // android.view.View, android.content.ComponentCallbacks
    public final void onConfigurationChanged(Configuration configuration) {
        updateResources();
        updateMessageView();
        drawArrow(this.mArrowLeftView, true);
        drawArrow(this.mArrowRightView, false);
        updateLocationAndVisibility();
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        getContext().unregisterComponentCallbacks(this);
    }

    public final void updateLocationAndVisibility() {
        boolean isMenuOnLeftSide = this.mMenuViewAppearance.isMenuOnLeftSide();
        if (isMenuOnLeftSide) {
            this.mArrowLeftView.setVisibility(0);
            this.mArrowRightView.setVisibility(8);
        } else {
            this.mArrowLeftView.setVisibility(8);
            this.mArrowRightView.setVisibility(0);
        }
        Rect rect = new Rect();
        PointF menuPosition = this.mMenuViewAppearance.getMenuPosition();
        int i = (int) menuPosition.x;
        float f = menuPosition.y;
        int i2 = (int) f;
        MenuViewAppearance menuViewAppearance = this.mMenuViewAppearance;
        int i3 = menuViewAppearance.mSizeType;
        rect.set(i, i2, (i3 == 0 ? menuViewAppearance.mSmallIconSize : menuViewAppearance.mLargeIconSize) + ((i3 == 0 ? menuViewAppearance.mSmallPadding : menuViewAppearance.mLargePadding) * 2) + i, menuViewAppearance.getMenuHeight() + ((int) f));
        PointF menuPosition2 = this.mMenuViewAppearance.getMenuPosition();
        this.mMessageView.measure(View.MeasureSpec.makeMeasureSpec((((isMenuOnLeftSide ? this.mMenuViewAppearance.getMenuDraggableBoundsWith(true).width() - ((int) menuPosition2.x) : (int) menuPosition2.x) - this.mArrowWidth) - this.mArrowMargin) - this.mTextViewMargin, Integer.MIN_VALUE), View.MeasureSpec.makeMeasureSpec(0, 0));
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) this.mMessageView.getLayoutParams();
        layoutParams.width = this.mMessageView.getMeasuredWidth();
        this.mMessageView.setLayoutParams(layoutParams);
        setTranslationX(isMenuOnLeftSide ? rect.right : rect.left - ((this.mMessageView.getMeasuredWidth() + this.mArrowWidth) + this.mArrowMargin));
        setTranslationY(rect.centerY() - (this.mMessageView.getMeasuredHeight() / 2.0f));
    }

    public final void updateMessageView() {
        this.mMessageView.setTextSize(0, this.mFontSize);
        TextView textView = this.mMessageView;
        int i = this.mTextViewPadding;
        textView.setPadding(i, i, i, i);
        GradientDrawable gradientDrawable = (GradientDrawable) this.mMessageView.getBackground();
        gradientDrawable.setCornerRadius(this.mTextViewCornerRadius);
        gradientDrawable.setColor(this.mColorAccentPrimary);
    }

    public final void updateResources() {
        Resources resources = getResources();
        this.mArrowWidth = resources.getDimensionPixelSize(R.dimen.accessibility_floating_tooltip_arrow_width);
        this.mArrowHeight = resources.getDimensionPixelSize(R.dimen.accessibility_floating_tooltip_arrow_height);
        this.mArrowMargin = resources.getDimensionPixelSize(R.dimen.accessibility_floating_tooltip_arrow_margin);
        this.mArrowCornerRadius = resources.getDimensionPixelSize(R.dimen.accessibility_floating_tooltip_arrow_corner_radius);
        this.mFontSize = resources.getDimensionPixelSize(R.dimen.accessibility_floating_tooltip_font_size);
        this.mTextViewMargin = resources.getDimensionPixelSize(R.dimen.accessibility_floating_tooltip_margin);
        this.mTextViewPadding = resources.getDimensionPixelSize(R.dimen.accessibility_floating_tooltip_padding);
        this.mTextViewCornerRadius = resources.getDimensionPixelSize(R.dimen.accessibility_floating_tooltip_text_corner_radius);
        this.mColorAccentPrimary = Utils.getColorAttrDefaultColor(android.R.^attr-private.colorAccentPrimary, 0, getContext());
    }

    @Override // android.content.ComponentCallbacks
    public final void onLowMemory() {
    }
}
