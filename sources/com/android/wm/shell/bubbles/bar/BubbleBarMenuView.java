package com.android.wm.shell.bubbles.bar;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.drawable.Icon;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.android.systemui.plugins.DarkIconDispatcher;
import com.android.wm.shell.R;
import java.util.ArrayList;
import java.util.Iterator;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public class BubbleBarMenuView extends LinearLayout {
    public static final /* synthetic */ int $r8$clinit = 0;
    public ViewGroup mActionsSectionView;
    public ImageView mBubbleDismissIconView;
    public ImageView mBubbleIconView;
    public ViewGroup mBubbleSectionView;
    public TextView mBubbleTitleView;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class MenuAction {
        public final Icon mIcon;
        public final View.OnClickListener mOnClick;
        public final int mTint;
        public final String mTitle;

        public MenuAction(Icon icon, String str, int i, View.OnClickListener onClickListener) {
            this.mIcon = icon;
            this.mTitle = str;
            this.mTint = i;
            this.mOnClick = onClickListener;
        }
    }

    public BubbleBarMenuView(Context context) {
        this(context, null);
    }

    @Override // android.view.View
    public final float getAlpha() {
        return this.mBubbleSectionView.getAlpha();
    }

    @Override // android.view.View
    public final void onFinishInflate() {
        super.onFinishInflate();
        this.mBubbleSectionView = (ViewGroup) findViewById(R.id.bubble_bar_manage_menu_bubble_section);
        this.mActionsSectionView = (ViewGroup) findViewById(R.id.bubble_bar_manage_menu_actions_section);
        this.mBubbleIconView = (ImageView) findViewById(R.id.bubble_bar_manage_menu_bubble_icon);
        this.mBubbleTitleView = (TextView) findViewById(R.id.bubble_bar_manage_menu_bubble_title);
        this.mBubbleDismissIconView = (ImageView) findViewById(R.id.bubble_bar_manage_menu_dismiss_icon);
        TypedArray obtainStyledAttributes = ((LinearLayout) this).mContext.obtainStyledAttributes(new int[]{android.R.^attr-private.materialColorSurfaceBright, android.R.^attr-private.materialColorOnSurface});
        try {
            this.mActionsSectionView.getBackground().setTint(obtainStyledAttributes.getColor(0, -1));
            this.mBubbleDismissIconView.setImageTintList(ColorStateList.valueOf(obtainStyledAttributes.getColor(1, DarkIconDispatcher.DEFAULT_INVERSE_ICON_TINT)));
            obtainStyledAttributes.close();
            this.mBubbleSectionView.setAccessibilityDelegate(new View.AccessibilityDelegate() { // from class: com.android.wm.shell.bubbles.bar.BubbleBarMenuView.1
                @Override // android.view.View.AccessibilityDelegate
                public final void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfo accessibilityNodeInfo) {
                    super.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfo);
                    accessibilityNodeInfo.addAction(new AccessibilityNodeInfo.AccessibilityAction(16, BubbleBarMenuView.this.getResources().getString(R.string.bubble_accessibility_action_collapse_menu)));
                }
            });
        } catch (Throwable th) {
            if (obtainStyledAttributes != null) {
                try {
                    obtainStyledAttributes.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
            }
            throw th;
        }
    }

    @Override // android.view.View
    public final void setAlpha(float f) {
        this.mBubbleSectionView.setAlpha(f);
        this.mActionsSectionView.setAlpha(f);
    }

    public final void updateActions(ArrayList arrayList) {
        this.mActionsSectionView.removeAllViews();
        LayoutInflater from = LayoutInflater.from(((LinearLayout) this).mContext);
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            MenuAction menuAction = (MenuAction) it.next();
            BubbleBarMenuItemView bubbleBarMenuItemView = (BubbleBarMenuItemView) from.inflate(R.layout.bubble_bar_menu_item, this.mActionsSectionView, false);
            Icon icon = menuAction.mIcon;
            int i = menuAction.mTint;
            if (i == 0) {
                bubbleBarMenuItemView.mTextView.setTextColor(bubbleBarMenuItemView.getContext().obtainStyledAttributes(new int[]{android.R.^attr-private.materialColorOnSurface}).getColor(0, DarkIconDispatcher.DEFAULT_INVERSE_ICON_TINT));
            } else {
                bubbleBarMenuItemView.getClass();
                icon.setTint(i);
                bubbleBarMenuItemView.mTextView.setTextColor(i);
            }
            bubbleBarMenuItemView.mImageView.setImageIcon(icon);
            bubbleBarMenuItemView.mTextView.setText(menuAction.mTitle);
            bubbleBarMenuItemView.setOnClickListener(menuAction.mOnClick);
            this.mActionsSectionView.addView(bubbleBarMenuItemView);
        }
    }

    public BubbleBarMenuView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public BubbleBarMenuView(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, 0);
    }

    public BubbleBarMenuView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
    }
}
