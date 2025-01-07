package com.android.systemui.statusbar.notification.footer.ui.view;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.IndentingPrintWriter;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import com.android.settingslib.Utils;
import com.android.systemui.statusbar.notification.ColorUpdateLogger;
import com.android.systemui.statusbar.notification.row.FooterViewButton;
import com.android.systemui.statusbar.notification.row.StackScrollerDecorView;
import com.android.systemui.statusbar.notification.stack.AnimationProperties;
import com.android.systemui.statusbar.notification.stack.ExpandableViewState;
import com.android.systemui.statusbar.notification.stack.StackStateAnimator;
import com.android.systemui.statusbar.notification.stack.ViewState;
import com.android.systemui.util.DumpUtilsKt;
import com.android.wm.shell.R;
import java.io.PrintWriter;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public class FooterView extends StackScrollerDecorView {
    public static final /* synthetic */ int $r8$clinit = 0;
    public FooterViewButton mClearAllButton;
    public View.OnClickListener mClearAllButtonClickListener;
    public int mClearAllButtonDescriptionId;
    public int mClearAllButtonTextId;
    public FooterViewButton mManageOrHistoryButton;
    public int mManageOrHistoryButtonDescriptionId;
    public int mManageOrHistoryButtonTextId;
    public int mMessageIconId;
    public int mMessageStringId;
    public TextView mSeenNotifsFooterTextView;
    public boolean mShouldBeHidden;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class FooterViewState extends ExpandableViewState {
        public boolean hideContent;
        public boolean resetY;

        @Override // com.android.systemui.statusbar.notification.stack.ExpandableViewState, com.android.systemui.statusbar.notification.stack.ViewState
        public final void animateTo(View view, AnimationProperties animationProperties) {
            if ((view instanceof FooterView) && this.resetY) {
                StackStateAnimator.this.mAnimationFilter.animateY = false;
                this.resetY = false;
            }
            super.animateTo(view, animationProperties);
        }

        @Override // com.android.systemui.statusbar.notification.stack.ExpandableViewState, com.android.systemui.statusbar.notification.stack.ViewState
        public final void applyToView(View view) {
            super.applyToView(view);
            if (view instanceof FooterView) {
                ((FooterView) view).setContentVisible(!this.hideContent, true, null);
            }
        }

        @Override // com.android.systemui.statusbar.notification.stack.ExpandableViewState, com.android.systemui.statusbar.notification.stack.ViewState
        public final void copyFrom(ViewState viewState) {
            super.copyFrom(viewState);
            if (viewState instanceof FooterViewState) {
                this.hideContent = ((FooterViewState) viewState).hideContent;
            }
        }
    }

    public FooterView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override // com.android.systemui.statusbar.notification.row.ExpandableView
    public final ExpandableViewState createExpandableViewState() {
        FooterViewState footerViewState = new FooterViewState();
        footerViewState.resetY = false;
        return footerViewState;
    }

    @Override // com.android.systemui.statusbar.notification.row.ExpandableView, com.android.systemui.Dumpable
    public final void dump(PrintWriter printWriter, String[] strArr) {
        final IndentingPrintWriter asIndenting = DumpUtilsKt.asIndenting(printWriter);
        super.dump(asIndenting, strArr);
        DumpUtilsKt.withIncreasedIndent(asIndenting, new Runnable() { // from class: com.android.systemui.statusbar.notification.footer.ui.view.FooterView$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                FooterView footerView = FooterView.this;
                IndentingPrintWriter indentingPrintWriter = asIndenting;
                int i = FooterView.$r8$clinit;
                indentingPrintWriter.println("visibility: " + DumpUtilsKt.visibilityString(footerView.getVisibility()));
                indentingPrintWriter.println("manageButton showHistory: false");
                indentingPrintWriter.println("manageButton visibility: " + DumpUtilsKt.visibilityString(footerView.mClearAllButton.getVisibility()));
                indentingPrintWriter.println("dismissButton visibility: " + DumpUtilsKt.visibilityString(footerView.mClearAllButton.getVisibility()));
            }
        });
    }

    @Override // com.android.systemui.statusbar.notification.row.StackScrollerDecorView
    public final View findContentView() {
        return findViewById(R.id.content);
    }

    @Override // com.android.systemui.statusbar.notification.row.StackScrollerDecorView
    public final View findSecondaryView() {
        return findViewById(R.id.dismiss_text);
    }

    @Override // com.android.systemui.statusbar.notification.row.ExpandableView, android.view.View
    public final void onConfigurationChanged(Configuration configuration) {
        int i = ColorUpdateLogger.$r8$clinit;
        super.onConfigurationChanged(configuration);
        updateColors$2();
        if (this.mClearAllButtonTextId != 0) {
            this.mClearAllButton.setText(getContext().getString(this.mClearAllButtonTextId));
        }
        if (this.mClearAllButtonDescriptionId != 0) {
            this.mClearAllButton.setContentDescription(getContext().getString(this.mClearAllButtonDescriptionId));
        }
        if (this.mManageOrHistoryButtonTextId != 0) {
            this.mManageOrHistoryButton.setText(getContext().getString(this.mManageOrHistoryButtonTextId));
        }
        if (this.mManageOrHistoryButtonDescriptionId != 0) {
            this.mManageOrHistoryButton.setContentDescription(getContext().getString(this.mManageOrHistoryButtonDescriptionId));
        }
        if (this.mMessageStringId != 0) {
            this.mSeenNotifsFooterTextView.setText(getContext().getString(this.mMessageStringId));
        }
        updateMessageIcon();
    }

    @Override // com.android.systemui.statusbar.notification.row.StackScrollerDecorView, android.view.View
    public final void onFinishInflate() {
        int i = ColorUpdateLogger.$r8$clinit;
        super.onFinishInflate();
        this.mClearAllButton = (FooterViewButton) findViewById(R.id.dismiss_text);
        this.mManageOrHistoryButton = (FooterViewButton) findViewById(R.id.manage_text);
        this.mSeenNotifsFooterTextView = (TextView) findViewById(R.id.unlock_prompt_footer);
        if (this.mClearAllButtonTextId != 0) {
            this.mClearAllButton.setText(getContext().getString(this.mClearAllButtonTextId));
        }
        if (this.mClearAllButtonDescriptionId != 0) {
            this.mClearAllButton.setContentDescription(getContext().getString(this.mClearAllButtonDescriptionId));
        }
        if (this.mManageOrHistoryButtonTextId != 0) {
            this.mManageOrHistoryButton.setText(getContext().getString(this.mManageOrHistoryButtonTextId));
        }
        if (this.mManageOrHistoryButtonDescriptionId != 0) {
            this.mManageOrHistoryButton.setContentDescription(getContext().getString(this.mManageOrHistoryButtonDescriptionId));
        }
        if (this.mMessageStringId != 0) {
            this.mSeenNotifsFooterTextView.setText(getContext().getString(this.mMessageStringId));
        }
        updateMessageIcon();
        updateColors$2();
    }

    public final void updateColors$2() {
        Resources.Theme theme = ((FrameLayout) this).mContext.getTheme();
        int colorAttrDefaultColor = Utils.getColorAttrDefaultColor(android.R.^attr-private.materialColorOnSurface, 0, ((FrameLayout) this).mContext);
        Drawable drawable = theme.getDrawable(R.drawable.notif_footer_btn_background);
        Drawable drawable2 = theme.getDrawable(R.drawable.notif_footer_btn_background);
        int colorAttrDefaultColor2 = Utils.getColorAttrDefaultColor(android.R.^attr-private.materialColorSurfaceContainerHigh, 0, ((FrameLayout) this).mContext);
        if (colorAttrDefaultColor2 != 0) {
            PorterDuffColorFilter porterDuffColorFilter = new PorterDuffColorFilter(colorAttrDefaultColor2, PorterDuff.Mode.SRC_ATOP);
            drawable.setColorFilter(porterDuffColorFilter);
            drawable2.setColorFilter(porterDuffColorFilter);
        }
        this.mClearAllButton.setBackground(drawable);
        this.mClearAllButton.setTextColor(colorAttrDefaultColor);
        this.mManageOrHistoryButton.setBackground(drawable2);
        this.mManageOrHistoryButton.setTextColor(colorAttrDefaultColor);
        this.mSeenNotifsFooterTextView.setTextColor(colorAttrDefaultColor);
        this.mSeenNotifsFooterTextView.setCompoundDrawableTintList(ColorStateList.valueOf(colorAttrDefaultColor));
        int i = ColorUpdateLogger.$r8$clinit;
    }

    public final void updateMessageIcon() {
        if (this.mMessageIconId == 0) {
            return;
        }
        int dimensionPixelSize = getResources().getDimensionPixelSize(R.dimen.notifications_unseen_footer_icon_size);
        Drawable drawable = getContext().getDrawable(this.mMessageIconId);
        if (drawable != null) {
            drawable.setBounds(0, 0, dimensionPixelSize, dimensionPixelSize);
            this.mSeenNotifsFooterTextView.setCompoundDrawablesRelative(drawable, null, null, null);
        }
    }
}
