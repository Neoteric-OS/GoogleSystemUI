package com.android.wm.shell.bubbles;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.Path;
import android.graphics.drawable.AdaptiveIconDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.InsetDrawable;
import android.util.PathParser;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import com.android.launcher3.icons.BubbleIconFactory;
import com.android.launcher3.icons.IconNormalizer;
import com.android.systemui.plugins.DarkIconDispatcher;
import com.android.wm.shell.R;
import com.android.wm.shell.bubbles.BadgedImageView;
import com.android.wm.shell.bubbles.bar.BubbleBarExpandedView;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class BubbleOverflow implements BubbleViewProvider {
    public Bitmap bitmap;
    public BubbleBarExpandedView bubbleBarExpandedView;
    public final Context context;
    public int dotColor;
    public Path dotPath;
    public BubbleExpandedView expandedView;
    public final LayoutInflater inflater;
    public BadgedImageView overflowBtn;
    public int overflowIconInset;
    public final BubblePositioner positioner;
    public boolean showDot;

    public BubbleOverflow(Context context, BubblePositioner bubblePositioner) {
        this.context = context;
        this.positioner = bubblePositioner;
        this.inflater = LayoutInflater.from(context);
        updateResources();
        this.expandedView = null;
        this.overflowBtn = null;
    }

    @Override // com.android.wm.shell.bubbles.BubbleViewProvider
    public final Bitmap getAppBadge() {
        return null;
    }

    @Override // com.android.wm.shell.bubbles.BubbleViewProvider
    public final BubbleBarExpandedView getBubbleBarExpandedView() {
        return this.bubbleBarExpandedView;
    }

    @Override // com.android.wm.shell.bubbles.BubbleViewProvider
    public final Bitmap getBubbleIcon() {
        Bitmap bitmap = this.bitmap;
        if (bitmap == null) {
            return null;
        }
        return bitmap;
    }

    @Override // com.android.wm.shell.bubbles.BubbleViewProvider
    public final int getDotColor() {
        return this.dotColor;
    }

    @Override // com.android.wm.shell.bubbles.BubbleViewProvider
    public final Path getDotPath() {
        Path path = this.dotPath;
        if (path == null) {
            return null;
        }
        return path;
    }

    @Override // com.android.wm.shell.bubbles.BubbleViewProvider
    public final BubbleExpandedView getExpandedView() {
        return this.expandedView;
    }

    @Override // com.android.wm.shell.bubbles.BubbleViewProvider
    /* renamed from: getIconView, reason: merged with bridge method [inline-methods] */
    public final BadgedImageView getIconView$1() {
        if (this.overflowBtn == null) {
            BadgedImageView badgedImageView = (BadgedImageView) this.inflater.inflate(R.layout.bubble_overflow_button, (ViewGroup) null, false);
            this.overflowBtn = badgedImageView;
            BubblePositioner bubblePositioner = this.positioner;
            badgedImageView.initialize(bubblePositioner);
            BadgedImageView badgedImageView2 = this.overflowBtn;
            if (badgedImageView2 != null) {
                badgedImageView2.setContentDescription(this.context.getResources().getString(R.string.bubble_overflow_button_content_description));
            }
            int i = bubblePositioner.mBubbleSize;
            BadgedImageView badgedImageView3 = this.overflowBtn;
            if (badgedImageView3 != null) {
                badgedImageView3.setLayoutParams(new FrameLayout.LayoutParams(i, i));
            }
            updateBtnTheme();
        }
        return this.overflowBtn;
    }

    @Override // com.android.wm.shell.bubbles.BubbleViewProvider
    public final String getKey() {
        return "Overflow";
    }

    @Override // com.android.wm.shell.bubbles.BubbleViewProvider
    public final int getTaskId() {
        BubbleExpandedView bubbleExpandedView = this.expandedView;
        if (bubbleExpandedView == null) {
            return -1;
        }
        Intrinsics.checkNotNull(bubbleExpandedView);
        return bubbleExpandedView.mTaskId;
    }

    @Override // com.android.wm.shell.bubbles.BubbleViewProvider
    public final boolean showDot() {
        return this.showDot;
    }

    public final void updateBtnTheme() {
        Drawable drawable;
        Resources resources = this.context.getResources();
        TypedArray obtainStyledAttributes = this.context.obtainStyledAttributes(new int[]{android.R.^attr-private.materialColorPrimaryFixed, android.R.^attr-private.materialColorOnPrimaryFixed});
        int color = obtainStyledAttributes.getColor(0, -1);
        int color2 = obtainStyledAttributes.getColor(1, DarkIconDispatcher.DEFAULT_INVERSE_ICON_TINT);
        obtainStyledAttributes.recycle();
        this.dotColor = color;
        BadgedImageView badgedImageView = this.overflowBtn;
        if (badgedImageView != null && (drawable = badgedImageView.mBubbleIcon.getDrawable()) != null) {
            drawable.setTint(color2);
        }
        BubbleIconFactory bubbleIconFactory = new BubbleIconFactory(this.context, resources.getDimensionPixelSize(R.dimen.bubble_size), resources.getDimensionPixelSize(R.dimen.bubble_badge_size), this.context.getColor(R.color.important_conversation), resources.getDimensionPixelSize(android.R.dimen.indeterminate_progress_alpha_08));
        BadgedImageView badgedImageView2 = this.overflowBtn;
        this.bitmap = bubbleIconFactory.createBadgedIconBitmap(new AdaptiveIconDrawable(new ColorDrawable(color), new InsetDrawable(badgedImageView2 != null ? badgedImageView2.mBubbleIcon.getDrawable() : null, this.overflowIconInset)), null).icon;
        this.dotPath = PathParser.createPathFromPathData(resources.getString(android.R.string.config_mainBuiltInDisplayCutout));
        IconNormalizer normalizer = bubbleIconFactory.getNormalizer();
        BadgedImageView iconView$1 = getIconView$1();
        Intrinsics.checkNotNull(iconView$1);
        float scale = normalizer.getScale(iconView$1.mBubbleIcon.getDrawable(), null, null, null);
        Matrix matrix = new Matrix();
        matrix.setScale(scale, scale, 50.0f, 50.0f);
        Path path = this.dotPath;
        (path != null ? path : null).transform(matrix);
        BadgedImageView badgedImageView3 = this.overflowBtn;
        if (badgedImageView3 != null) {
            badgedImageView3.setRenderedBubble(this);
        }
        BadgedImageView badgedImageView4 = this.overflowBtn;
        if (badgedImageView4 != null) {
            badgedImageView4.removeDotSuppressionFlag(BadgedImageView.SuppressionFlag.FLYOUT_VISIBLE);
        }
    }

    public final void updateResources() {
        this.overflowIconInset = this.context.getResources().getDimensionPixelSize(R.dimen.bubble_overflow_icon_inset);
        BadgedImageView badgedImageView = this.overflowBtn;
        if (badgedImageView != null) {
            int i = this.positioner.mBubbleSize;
            badgedImageView.setLayoutParams(new FrameLayout.LayoutParams(i, i));
        }
        BubbleExpandedView bubbleExpandedView = this.expandedView;
        if (bubbleExpandedView != null) {
            bubbleExpandedView.updateDimensions();
        }
    }

    @Override // com.android.wm.shell.bubbles.BubbleViewProvider
    public final void setTaskViewVisibility() {
    }
}
