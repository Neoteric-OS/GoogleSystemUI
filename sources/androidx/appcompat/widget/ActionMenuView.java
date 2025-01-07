package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.Configuration;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import android.widget.LinearLayout;
import androidx.appcompat.view.menu.ActionMenuItemView;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.appcompat.view.menu.MenuItemImpl;
import androidx.appcompat.view.menu.MenuPresenter;
import androidx.appcompat.view.menu.MenuView;
import androidx.appcompat.widget.ActionMenuPresenter;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.appcompat.widget.Toolbar;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public class ActionMenuView extends LinearLayoutCompat implements MenuBuilder.ItemInvoker, MenuView {
    public boolean mFormatItems;
    public int mFormatItemsWidth;
    public final int mGeneratedItemPadding;
    public MenuBuilder mMenu;
    public Toolbar.AnonymousClass1 mMenuBuilderCallback;
    public final int mMinCellSize;
    public Toolbar.AnonymousClass1 mOnMenuItemClickListener;
    public Context mPopupContext;
    public int mPopupTheme;
    public ActionMenuPresenter mPresenter;
    public boolean mReserveOverflow;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public interface ActionMenuChildView {
        boolean needsDividerAfter();

        boolean needsDividerBefore();
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class LayoutParams extends LinearLayoutCompat.LayoutParams {
        public int cellsUsed;
        public boolean expandable;
        public boolean expanded;
        public int extraPixels;
        public boolean isOverflowButton;
        public boolean preventEdgeOffset;
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class MenuBuilderCallback implements MenuBuilder.Callback {
        public MenuBuilderCallback() {
        }

        @Override // androidx.appcompat.view.menu.MenuBuilder.Callback
        public final boolean onMenuItemSelected(MenuBuilder menuBuilder, MenuItem menuItem) {
            Toolbar.AnonymousClass1 anonymousClass1 = ActionMenuView.this.mOnMenuItemClickListener;
            if (anonymousClass1 == null) {
                return false;
            }
            Toolbar.this.mMenuHostHelper.onMenuItemSelected();
            return false;
        }

        @Override // androidx.appcompat.view.menu.MenuBuilder.Callback
        public final void onMenuModeChange(MenuBuilder menuBuilder) {
            Toolbar.AnonymousClass1 anonymousClass1 = ActionMenuView.this.mMenuBuilderCallback;
            if (anonymousClass1 != null) {
                anonymousClass1.onMenuModeChange(menuBuilder);
            }
        }
    }

    public ActionMenuView(Context context) {
        this(context, null);
    }

    @Override // androidx.appcompat.widget.LinearLayoutCompat, android.view.ViewGroup
    public final boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof LayoutParams;
    }

    @Override // android.view.View
    public final boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        return false;
    }

    @Override // androidx.appcompat.widget.LinearLayoutCompat, android.view.ViewGroup
    public final /* bridge */ /* synthetic */ ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return generateDefaultLayoutParams();
    }

    @Override // androidx.appcompat.widget.LinearLayoutCompat, android.view.ViewGroup
    public final /* bridge */ /* synthetic */ ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return generateLayoutParams(layoutParams);
    }

    public final MenuBuilder getMenu() {
        if (this.mMenu == null) {
            Context context = getContext();
            MenuBuilder menuBuilder = new MenuBuilder(context);
            this.mMenu = menuBuilder;
            menuBuilder.mCallback = new MenuBuilderCallback();
            ActionMenuPresenter actionMenuPresenter = new ActionMenuPresenter(context);
            this.mPresenter = actionMenuPresenter;
            actionMenuPresenter.mReserveOverflow = true;
            actionMenuPresenter.mReserveOverflowSet = true;
            actionMenuPresenter.mCallback = new ActionMenuPresenterCallback();
            this.mMenu.addMenuPresenter(actionMenuPresenter, this.mPopupContext);
            ActionMenuPresenter actionMenuPresenter2 = this.mPresenter;
            actionMenuPresenter2.mMenuView = this;
            this.mMenu = actionMenuPresenter2.mMenu;
        }
        return this.mMenu;
    }

    public final boolean hasSupportDividerBeforeChildAt(int i) {
        boolean z = false;
        if (i == 0) {
            return false;
        }
        KeyEvent.Callback childAt = getChildAt(i - 1);
        KeyEvent.Callback childAt2 = getChildAt(i);
        if (i < getChildCount() && (childAt instanceof ActionMenuChildView)) {
            z = ((ActionMenuChildView) childAt).needsDividerAfter();
        }
        return (i <= 0 || !(childAt2 instanceof ActionMenuChildView)) ? z : z | ((ActionMenuChildView) childAt2).needsDividerBefore();
    }

    @Override // androidx.appcompat.view.menu.MenuView
    public final void initialize(MenuBuilder menuBuilder) {
        this.mMenu = menuBuilder;
    }

    @Override // androidx.appcompat.view.menu.MenuBuilder.ItemInvoker
    public final boolean invokeItem(MenuItemImpl menuItemImpl) {
        return this.mMenu.performItemAction(menuItemImpl, null, 0);
    }

    @Override // android.view.View
    public final void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        ActionMenuPresenter actionMenuPresenter = this.mPresenter;
        if (actionMenuPresenter != null) {
            actionMenuPresenter.updateMenuView();
            if (this.mPresenter.isOverflowMenuShowing()) {
                this.mPresenter.hideOverflowMenu();
                this.mPresenter.showOverflowMenu();
            }
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        ActionMenuPresenter actionMenuPresenter = this.mPresenter;
        if (actionMenuPresenter != null) {
            actionMenuPresenter.hideOverflowMenu();
            ActionMenuPresenter.OverflowPopup overflowPopup = actionMenuPresenter.mActionButtonPopup;
            if (overflowPopup == null || !overflowPopup.isShowing()) {
                return;
            }
            overflowPopup.mPopup.dismiss();
        }
    }

    @Override // androidx.appcompat.widget.LinearLayoutCompat, android.view.ViewGroup, android.view.View
    public final void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int width;
        int i5;
        if (!this.mFormatItems) {
            super.onLayout(z, i, i2, i3, i4);
            return;
        }
        int childCount = getChildCount();
        int i6 = (i4 - i2) / 2;
        int i7 = this.mDividerWidth;
        int i8 = i3 - i;
        int paddingRight = (i8 - getPaddingRight()) - getPaddingLeft();
        boolean z2 = getLayoutDirection() == 1;
        int i9 = 0;
        int i10 = 0;
        for (int i11 = 0; i11 < childCount; i11++) {
            View childAt = getChildAt(i11);
            if (childAt.getVisibility() != 8) {
                LayoutParams layoutParams = (LayoutParams) childAt.getLayoutParams();
                if (layoutParams.isOverflowButton) {
                    int measuredWidth = childAt.getMeasuredWidth();
                    if (hasSupportDividerBeforeChildAt(i11)) {
                        measuredWidth += i7;
                    }
                    int measuredHeight = childAt.getMeasuredHeight();
                    if (z2) {
                        i5 = getPaddingLeft() + ((LinearLayout.LayoutParams) layoutParams).leftMargin;
                        width = i5 + measuredWidth;
                    } else {
                        width = (getWidth() - getPaddingRight()) - ((LinearLayout.LayoutParams) layoutParams).rightMargin;
                        i5 = width - measuredWidth;
                    }
                    int i12 = i6 - (measuredHeight / 2);
                    childAt.layout(i5, i12, width, measuredHeight + i12);
                    paddingRight -= measuredWidth;
                    i9 = 1;
                } else {
                    paddingRight -= (childAt.getMeasuredWidth() + ((LinearLayout.LayoutParams) layoutParams).leftMargin) + ((LinearLayout.LayoutParams) layoutParams).rightMargin;
                    hasSupportDividerBeforeChildAt(i11);
                    i10++;
                }
            }
        }
        if (childCount == 1 && i9 == 0) {
            View childAt2 = getChildAt(0);
            int measuredWidth2 = childAt2.getMeasuredWidth();
            int measuredHeight2 = childAt2.getMeasuredHeight();
            int i13 = (i8 / 2) - (measuredWidth2 / 2);
            int i14 = i6 - (measuredHeight2 / 2);
            childAt2.layout(i13, i14, measuredWidth2 + i13, measuredHeight2 + i14);
            return;
        }
        int i15 = i10 - (i9 ^ 1);
        int max = Math.max(0, i15 > 0 ? paddingRight / i15 : 0);
        if (z2) {
            int width2 = getWidth() - getPaddingRight();
            for (int i16 = 0; i16 < childCount; i16++) {
                View childAt3 = getChildAt(i16);
                LayoutParams layoutParams2 = (LayoutParams) childAt3.getLayoutParams();
                if (childAt3.getVisibility() != 8 && !layoutParams2.isOverflowButton) {
                    int i17 = width2 - ((LinearLayout.LayoutParams) layoutParams2).rightMargin;
                    int measuredWidth3 = childAt3.getMeasuredWidth();
                    int measuredHeight3 = childAt3.getMeasuredHeight();
                    int i18 = i6 - (measuredHeight3 / 2);
                    childAt3.layout(i17 - measuredWidth3, i18, i17, measuredHeight3 + i18);
                    width2 = i17 - ((measuredWidth3 + ((LinearLayout.LayoutParams) layoutParams2).leftMargin) + max);
                }
            }
            return;
        }
        int paddingLeft = getPaddingLeft();
        for (int i19 = 0; i19 < childCount; i19++) {
            View childAt4 = getChildAt(i19);
            LayoutParams layoutParams3 = (LayoutParams) childAt4.getLayoutParams();
            if (childAt4.getVisibility() != 8 && !layoutParams3.isOverflowButton) {
                int i20 = paddingLeft + ((LinearLayout.LayoutParams) layoutParams3).leftMargin;
                int measuredWidth4 = childAt4.getMeasuredWidth();
                int measuredHeight4 = childAt4.getMeasuredHeight();
                int i21 = i6 - (measuredHeight4 / 2);
                childAt4.layout(i20, i21, i20 + measuredWidth4, measuredHeight4 + i21);
                paddingLeft = measuredWidth4 + ((LinearLayout.LayoutParams) layoutParams3).rightMargin + max + i20;
            }
        }
    }

    /* JADX WARN: Type inference failed for: r12v11 */
    /* JADX WARN: Type inference failed for: r12v12, types: [boolean, int] */
    /* JADX WARN: Type inference failed for: r12v32 */
    @Override // androidx.appcompat.widget.LinearLayoutCompat, android.view.View
    public final void onMeasure(int i, int i2) {
        int i3;
        int i4;
        ?? r12;
        int i5;
        int i6;
        int i7;
        int i8;
        MenuBuilder menuBuilder;
        boolean z = this.mFormatItems;
        boolean z2 = View.MeasureSpec.getMode(i) == 1073741824;
        this.mFormatItems = z2;
        if (z != z2) {
            this.mFormatItemsWidth = 0;
        }
        int size = View.MeasureSpec.getSize(i);
        if (this.mFormatItems && (menuBuilder = this.mMenu) != null && size != this.mFormatItemsWidth) {
            this.mFormatItemsWidth = size;
            menuBuilder.onItemsChanged(true);
        }
        int childCount = getChildCount();
        if (!this.mFormatItems || childCount <= 0) {
            for (int i9 = 0; i9 < childCount; i9++) {
                LayoutParams layoutParams = (LayoutParams) getChildAt(i9).getLayoutParams();
                ((LinearLayout.LayoutParams) layoutParams).rightMargin = 0;
                ((LinearLayout.LayoutParams) layoutParams).leftMargin = 0;
            }
            super.onMeasure(i, i2);
            return;
        }
        int mode = View.MeasureSpec.getMode(i2);
        int size2 = View.MeasureSpec.getSize(i);
        int size3 = View.MeasureSpec.getSize(i2);
        int paddingRight = getPaddingRight() + getPaddingLeft();
        int paddingBottom = getPaddingBottom() + getPaddingTop();
        int childMeasureSpec = ViewGroup.getChildMeasureSpec(i2, paddingBottom, -2);
        int i10 = size2 - paddingRight;
        int i11 = this.mMinCellSize;
        int i12 = i10 / i11;
        int i13 = i10 % i11;
        if (i12 == 0) {
            setMeasuredDimension(i10, 0);
            return;
        }
        int i14 = (i13 / i12) + i11;
        int childCount2 = getChildCount();
        int i15 = 0;
        int i16 = 0;
        int i17 = 0;
        int i18 = 0;
        boolean z3 = false;
        int i19 = 0;
        long j = 0;
        while (i18 < childCount2) {
            View childAt = getChildAt(i18);
            int i20 = size3;
            if (childAt.getVisibility() == 8) {
                i6 = mode;
                i5 = i10;
                i7 = paddingBottom;
            } else {
                boolean z4 = childAt instanceof ActionMenuItemView;
                int i21 = i16 + 1;
                if (z4) {
                    int i22 = this.mGeneratedItemPadding;
                    i4 = i21;
                    r12 = 0;
                    childAt.setPadding(i22, 0, i22, 0);
                } else {
                    i4 = i21;
                    r12 = 0;
                }
                LayoutParams layoutParams2 = (LayoutParams) childAt.getLayoutParams();
                layoutParams2.expanded = r12;
                layoutParams2.extraPixels = r12;
                layoutParams2.cellsUsed = r12;
                layoutParams2.expandable = r12;
                ((LinearLayout.LayoutParams) layoutParams2).leftMargin = r12;
                ((LinearLayout.LayoutParams) layoutParams2).rightMargin = r12;
                layoutParams2.preventEdgeOffset = z4 && !TextUtils.isEmpty(((ActionMenuItemView) childAt).getText());
                int i23 = layoutParams2.isOverflowButton ? 1 : i12;
                i5 = i10;
                LayoutParams layoutParams3 = (LayoutParams) childAt.getLayoutParams();
                i6 = mode;
                i7 = paddingBottom;
                int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(childMeasureSpec) - paddingBottom, View.MeasureSpec.getMode(childMeasureSpec));
                ActionMenuItemView actionMenuItemView = z4 ? (ActionMenuItemView) childAt : null;
                boolean z5 = (actionMenuItemView == null || TextUtils.isEmpty(actionMenuItemView.getText())) ? false : true;
                if (i23 <= 0 || (z5 && i23 < 2)) {
                    i8 = 0;
                } else {
                    childAt.measure(View.MeasureSpec.makeMeasureSpec(i23 * i14, Integer.MIN_VALUE), makeMeasureSpec);
                    int measuredWidth = childAt.getMeasuredWidth();
                    i8 = measuredWidth / i14;
                    if (measuredWidth % i14 != 0) {
                        i8++;
                    }
                    if (z5 && i8 < 2) {
                        i8 = 2;
                    }
                }
                layoutParams3.expandable = !layoutParams3.isOverflowButton && z5;
                layoutParams3.cellsUsed = i8;
                childAt.measure(View.MeasureSpec.makeMeasureSpec(i8 * i14, 1073741824), makeMeasureSpec);
                i17 = Math.max(i17, i8);
                if (layoutParams2.expandable) {
                    i19++;
                }
                if (layoutParams2.isOverflowButton) {
                    z3 = true;
                }
                i12 -= i8;
                i15 = Math.max(i15, childAt.getMeasuredHeight());
                if (i8 == 1) {
                    j |= 1 << i18;
                }
                i16 = i4;
            }
            i18++;
            size3 = i20;
            paddingBottom = i7;
            i10 = i5;
            mode = i6;
        }
        int i24 = mode;
        int i25 = i10;
        int i26 = size3;
        boolean z6 = z3 && i16 == 2;
        boolean z7 = false;
        while (i19 > 0 && i12 > 0) {
            int i27 = Integer.MAX_VALUE;
            int i28 = 0;
            long j2 = 0;
            for (int i29 = 0; i29 < childCount2; i29++) {
                LayoutParams layoutParams4 = (LayoutParams) getChildAt(i29).getLayoutParams();
                if (layoutParams4.expandable) {
                    int i30 = layoutParams4.cellsUsed;
                    if (i30 < i27) {
                        j2 = 1 << i29;
                        i27 = i30;
                        i28 = 1;
                    } else if (i30 == i27) {
                        i28++;
                        j2 |= 1 << i29;
                    }
                }
            }
            j |= j2;
            if (i28 > i12) {
                break;
            }
            int i31 = i27 + 1;
            int i32 = 0;
            while (i32 < childCount2) {
                View childAt2 = getChildAt(i32);
                LayoutParams layoutParams5 = (LayoutParams) childAt2.getLayoutParams();
                int i33 = childMeasureSpec;
                int i34 = childCount2;
                long j3 = 1 << i32;
                if ((j2 & j3) != 0) {
                    if (z6 && layoutParams5.preventEdgeOffset && i12 == 1) {
                        int i35 = this.mGeneratedItemPadding;
                        childAt2.setPadding(i35 + i14, 0, i35, 0);
                    }
                    layoutParams5.cellsUsed++;
                    layoutParams5.expanded = true;
                    i12--;
                } else if (layoutParams5.cellsUsed == i31) {
                    j |= j3;
                }
                i32++;
                childMeasureSpec = i33;
                childCount2 = i34;
            }
            z7 = true;
        }
        int i36 = childMeasureSpec;
        int i37 = childCount2;
        boolean z8 = !z3 && i16 == 1;
        if (i12 <= 0 || j == 0 || (i12 >= i16 - 1 && !z8 && i17 <= 1)) {
            i3 = i37;
        } else {
            float bitCount = Long.bitCount(j);
            if (!z8) {
                if ((j & 1) != 0 && !((LayoutParams) getChildAt(0).getLayoutParams()).preventEdgeOffset) {
                    bitCount -= 0.5f;
                }
                int i38 = i37 - 1;
                if ((j & (1 << i38)) != 0 && !((LayoutParams) getChildAt(i38).getLayoutParams()).preventEdgeOffset) {
                    bitCount -= 0.5f;
                }
            }
            int i39 = bitCount > 0.0f ? (int) ((i12 * i14) / bitCount) : 0;
            boolean z9 = z7;
            i3 = i37;
            for (int i40 = 0; i40 < i3; i40++) {
                if ((j & (1 << i40)) != 0) {
                    View childAt3 = getChildAt(i40);
                    LayoutParams layoutParams6 = (LayoutParams) childAt3.getLayoutParams();
                    if (childAt3 instanceof ActionMenuItemView) {
                        layoutParams6.extraPixels = i39;
                        layoutParams6.expanded = true;
                        if (i40 == 0 && !layoutParams6.preventEdgeOffset) {
                            ((LinearLayout.LayoutParams) layoutParams6).leftMargin = (-i39) / 2;
                        }
                        z9 = true;
                    } else {
                        if (layoutParams6.isOverflowButton) {
                            layoutParams6.extraPixels = i39;
                            layoutParams6.expanded = true;
                            ((LinearLayout.LayoutParams) layoutParams6).rightMargin = (-i39) / 2;
                            z9 = true;
                        } else {
                            if (i40 != 0) {
                                ((LinearLayout.LayoutParams) layoutParams6).leftMargin = i39 / 2;
                            }
                            if (i40 != i3 - 1) {
                                ((LinearLayout.LayoutParams) layoutParams6).rightMargin = i39 / 2;
                            }
                        }
                    }
                }
            }
            z7 = z9;
        }
        if (z7) {
            for (int i41 = 0; i41 < i3; i41++) {
                View childAt4 = getChildAt(i41);
                LayoutParams layoutParams7 = (LayoutParams) childAt4.getLayoutParams();
                if (layoutParams7.expanded) {
                    childAt4.measure(View.MeasureSpec.makeMeasureSpec((layoutParams7.cellsUsed * i14) + layoutParams7.extraPixels, 1073741824), i36);
                }
            }
        }
        setMeasuredDimension(i25, i24 != 1073741824 ? i15 : i26);
    }

    public ActionMenuView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mBaselineAligned = false;
        float f = context.getResources().getDisplayMetrics().density;
        this.mMinCellSize = (int) (56.0f * f);
        this.mGeneratedItemPadding = (int) (f * 4.0f);
        this.mPopupContext = context;
        this.mPopupTheme = 0;
    }

    @Override // androidx.appcompat.widget.LinearLayoutCompat, android.view.ViewGroup
    public final /* bridge */ /* synthetic */ LinearLayoutCompat.LayoutParams generateDefaultLayoutParams() {
        return generateDefaultLayoutParams();
    }

    @Override // androidx.appcompat.widget.LinearLayoutCompat, android.view.ViewGroup
    public final /* bridge */ /* synthetic */ LinearLayoutCompat.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return generateLayoutParams(layoutParams);
    }

    public static LayoutParams generateDefaultLayoutParams() {
        LayoutParams layoutParams = new LayoutParams(-2, -2);
        layoutParams.isOverflowButton = false;
        ((LinearLayout.LayoutParams) layoutParams).gravity = 16;
        return layoutParams;
    }

    @Override // androidx.appcompat.widget.LinearLayoutCompat, android.view.ViewGroup
    public final ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new LayoutParams(getContext(), attributeSet);
    }

    @Override // androidx.appcompat.widget.LinearLayoutCompat, android.view.ViewGroup
    public final LinearLayoutCompat.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new LayoutParams(getContext(), attributeSet);
    }

    public static LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        LayoutParams layoutParams2;
        if (layoutParams != null) {
            if (layoutParams instanceof LayoutParams) {
                LayoutParams layoutParams3 = (LayoutParams) layoutParams;
                layoutParams2 = new LayoutParams(layoutParams3);
                layoutParams2.isOverflowButton = layoutParams3.isOverflowButton;
            } else {
                layoutParams2 = new LayoutParams(layoutParams);
            }
            if (((LinearLayout.LayoutParams) layoutParams2).gravity <= 0) {
                ((LinearLayout.LayoutParams) layoutParams2).gravity = 16;
            }
            return layoutParams2;
        }
        return generateDefaultLayoutParams();
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class ActionMenuPresenterCallback implements MenuPresenter.Callback {
        @Override // androidx.appcompat.view.menu.MenuPresenter.Callback
        public final boolean onOpenSubMenu(MenuBuilder menuBuilder) {
            return false;
        }

        @Override // androidx.appcompat.view.menu.MenuPresenter.Callback
        public final void onCloseMenu(MenuBuilder menuBuilder, boolean z) {
        }
    }
}
