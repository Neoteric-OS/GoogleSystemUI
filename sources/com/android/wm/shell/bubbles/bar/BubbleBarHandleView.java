package com.android.wm.shell.bubbles.bar;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Outline;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewOutlineProvider;
import com.android.wm.shell.R;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public class BubbleBarHandleView extends View {
    public ObjectAnimator mColorChangeAnim;
    public int mCurrentColor;
    public final int mHandleDarkColor;
    public final int mHandleLightColor;
    public final Path mPath;

    public BubbleBarHandleView(Context context) {
        this(context, null);
    }

    public BubbleBarHandleView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public BubbleBarHandleView(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, 0);
    }

    public BubbleBarHandleView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.mPath = new Path();
        final int dimensionPixelSize = getResources().getDimensionPixelSize(R.dimen.bubble_bar_expanded_view_handle_height);
        this.mHandleLightColor = getContext().getColor(R.color.bubble_bar_expanded_view_handle_light);
        this.mHandleDarkColor = getContext().getColor(R.color.bubble_bar_expanded_view_handle_dark);
        setClipToOutline(true);
        setOutlineProvider(new ViewOutlineProvider() { // from class: com.android.wm.shell.bubbles.bar.BubbleBarHandleView.1
            @Override // android.view.ViewOutlineProvider
            public final void getOutline(View view, Outline outline) {
                int height = view.getHeight() / 2;
                int i3 = dimensionPixelSize;
                RectF rectF = new RectF(0.0f, height - (i3 / 2), view.getWidth(), r0 + i3);
                BubbleBarHandleView.this.mPath.reset();
                float f = i3 / 2;
                BubbleBarHandleView.this.mPath.addRoundRect(rectF, f, f, Path.Direction.CW);
                outline.setPath(BubbleBarHandleView.this.mPath);
            }
        });
        setContentDescription(getResources().getString(R.string.handle_text));
    }
}
