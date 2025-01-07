package com.android.wm.shell.bubbles;

import android.animation.ArgbEvaluator;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Outline;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewOutlineProvider;
import android.view.ViewPropertyAnimator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.compose.animation.AndroidFlingSpline$$ExternalSyntheticOutline0;
import com.android.wm.shell.R;
import com.android.wm.shell.bubbles.Bubble;
import com.android.wm.shell.shared.TriangleShape;
import com.android.wm.shell.shared.animation.Interpolators;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class BubbleFlyoutView extends FrameLayout {
    public static final /* synthetic */ int $r8$clinit = 0;
    public final ArgbEvaluator mArgbEvaluator;
    public boolean mArrowPointingLeft;
    public final Paint mBgPaint;
    public final RectF mBgRect;
    public float mBgTranslationX;
    public float mBgTranslationY;
    public final int mBubbleElevation;
    public int mBubbleSize;
    public final float mCornerRadius;
    public float[] mDotCenter;
    public int mDotColor;
    public int mFloatingBackgroundColor;
    public final int mFlyoutElevation;
    public final int mFlyoutPadding;
    public final int mFlyoutSpaceFromBubble;
    public final ViewGroup mFlyoutTextContainer;
    public float mFlyoutToDotHeightDelta;
    public float mFlyoutToDotWidthDelta;
    public float mFlyoutY;
    public final ShapeDrawable mLeftTriangleShape;
    public final TextView mMessageText;
    public float mNewDotRadius;
    public float mNewDotSize;
    public int mNightModeFlags;
    public BubbleStackView$$ExternalSyntheticLambda27 mOnHide;
    public float mOriginalDotSize;
    public float mPercentStillFlyout;
    public float mPercentTransitionedToDot;
    public final BubblePositioner mPositioner;
    public float mRestingTranslationX;
    public final ShapeDrawable mRightTriangleShape;
    public final ImageView mSenderAvatar;
    public final TextView mSenderText;
    public float mTranslationXWhenDot;
    public float mTranslationYWhenDot;
    public final Outline mTriangleOutline;

    public BubbleFlyoutView(Context context, BubblePositioner bubblePositioner) {
        super(context);
        this.mBgPaint = new Paint(3);
        this.mArgbEvaluator = new ArgbEvaluator();
        this.mArrowPointingLeft = true;
        this.mTriangleOutline = new Outline();
        this.mBgRect = new RectF();
        this.mFlyoutY = 0.0f;
        this.mPercentTransitionedToDot = 1.0f;
        this.mPercentStillFlyout = 0.0f;
        this.mFlyoutToDotWidthDelta = 0.0f;
        this.mFlyoutToDotHeightDelta = 0.0f;
        this.mTranslationXWhenDot = 0.0f;
        this.mTranslationYWhenDot = 0.0f;
        this.mRestingTranslationX = 0.0f;
        this.mPositioner = bubblePositioner;
        LayoutInflater.from(context).inflate(R.layout.bubble_flyout, (ViewGroup) this, true);
        ViewGroup viewGroup = (ViewGroup) findViewById(R.id.bubble_flyout_text_container);
        this.mFlyoutTextContainer = viewGroup;
        this.mSenderText = (TextView) findViewById(R.id.bubble_flyout_name);
        this.mSenderAvatar = (ImageView) findViewById(R.id.bubble_flyout_avatar);
        this.mMessageText = (TextView) viewGroup.findViewById(R.id.bubble_flyout_text);
        Resources resources = getResources();
        this.mFlyoutPadding = resources.getDimensionPixelSize(R.dimen.bubble_flyout_padding_x);
        this.mFlyoutSpaceFromBubble = resources.getDimensionPixelSize(R.dimen.bubble_flyout_space_from_bubble);
        this.mBubbleElevation = resources.getDimensionPixelSize(R.dimen.bubble_elevation);
        int dimensionPixelSize = resources.getDimensionPixelSize(R.dimen.bubble_flyout_elevation);
        this.mFlyoutElevation = dimensionPixelSize;
        TypedArray obtainStyledAttributes = ((FrameLayout) this).mContext.obtainStyledAttributes(new int[]{android.R.attr.dialogCornerRadius});
        this.mCornerRadius = obtainStyledAttributes.getDimensionPixelSize(0, 0);
        obtainStyledAttributes.recycle();
        setPadding(0, 0, 0, 0);
        setWillNotDraw(false);
        setClipChildren(true);
        setTranslationZ(dimensionPixelSize);
        setOutlineProvider(new ViewOutlineProvider() { // from class: com.android.wm.shell.bubbles.BubbleFlyoutView.1
            @Override // android.view.ViewOutlineProvider
            public final void getOutline(View view, Outline outline) {
                BubbleFlyoutView bubbleFlyoutView = BubbleFlyoutView.this;
                bubbleFlyoutView.mTriangleOutline.isEmpty();
                Path path = new Path();
                float f = bubbleFlyoutView.mNewDotRadius;
                float f2 = bubbleFlyoutView.mPercentTransitionedToDot;
                float m = AndroidFlingSpline$$ExternalSyntheticOutline0.m(1.0f, f2, bubbleFlyoutView.mCornerRadius, f * f2);
                path.addRoundRect(bubbleFlyoutView.mBgRect, m, m, Path.Direction.CW);
                outline.setPath(path);
                Matrix matrix = new Matrix();
                matrix.postTranslate(bubbleFlyoutView.getLeft() + bubbleFlyoutView.mBgTranslationX, bubbleFlyoutView.getTop() + bubbleFlyoutView.mBgTranslationY);
                float f3 = bubbleFlyoutView.mPercentTransitionedToDot;
                if (f3 > 0.98f) {
                    float f4 = (f3 - 0.98f) / 0.02f;
                    float f5 = 1.0f - f4;
                    float f6 = bubbleFlyoutView.mNewDotRadius * f4;
                    matrix.postTranslate(f6, f6);
                    matrix.preScale(f5, f5);
                }
                outline.mPath.transform(matrix);
            }
        });
        setLayoutDirection(3);
        float f = 0;
        ShapeDrawable shapeDrawable = new ShapeDrawable(TriangleShape.createHorizontal(f, f, true));
        this.mLeftTriangleShape = shapeDrawable;
        shapeDrawable.setBounds(0, 0, 0, 0);
        ShapeDrawable shapeDrawable2 = new ShapeDrawable(TriangleShape.createHorizontal(f, f, false));
        this.mRightTriangleShape = shapeDrawable2;
        shapeDrawable2.setBounds(0, 0, 0, 0);
        applyConfigurationColors(getResources().getConfiguration());
    }

    public final void applyConfigurationColors(Configuration configuration) {
        boolean z = (configuration.uiMode & 48) == 32;
        TypedArray obtainStyledAttributes = ((FrameLayout) this).mContext.obtainStyledAttributes(new int[]{android.R.^attr-private.materialColorSurfaceContainer, android.R.^attr-private.materialColorOnSurface, android.R.^attr-private.materialColorOnSurfaceVariant});
        int i = -1;
        try {
            this.mFloatingBackgroundColor = obtainStyledAttributes.getColor(0, z ? -16777216 : -1);
            this.mSenderText.setTextColor(obtainStyledAttributes.getColor(1, z ? -1 : -16777216));
            TextView textView = this.mMessageText;
            if (!z) {
                i = -16777216;
            }
            textView.setTextColor(obtainStyledAttributes.getColor(2, i));
            this.mBgPaint.setColor(this.mFloatingBackgroundColor);
            this.mLeftTriangleShape.getPaint().setColor(this.mFloatingBackgroundColor);
            this.mRightTriangleShape.getPaint().setColor(this.mFloatingBackgroundColor);
            obtainStyledAttributes.close();
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

    public final void fade(boolean z, PointF pointF, boolean z2, Runnable runnable) {
        this.mFlyoutY = ((this.mBubbleSize - this.mFlyoutTextContainer.getHeight()) / 2.0f) + pointF.y;
        setAlpha(z ? 0.0f : 1.0f);
        float f = this.mFlyoutY;
        if (z) {
            f += 40.0f;
        }
        setTranslationY(f);
        updateFlyoutX(pointF.x);
        setTranslationX(this.mRestingTranslationX);
        updateDot(pointF, z2);
        animate().alpha(z ? 1.0f : 0.0f).setDuration(z ? 250L : 150L).setInterpolator(z ? Interpolators.ALPHA_IN : Interpolators.ALPHA_OUT);
        ViewPropertyAnimator animate = animate();
        float f2 = this.mFlyoutY;
        if (!z) {
            f2 -= 40.0f;
        }
        animate.translationY(f2).setDuration(z ? 250L : 150L).setInterpolator(z ? Interpolators.ALPHA_IN : Interpolators.ALPHA_OUT).withEndAction(runnable);
    }

    @Override // android.view.View
    public final void onConfigurationChanged(Configuration configuration) {
        int i = configuration.uiMode & 48;
        boolean z = i != this.mNightModeFlags;
        if (z) {
            this.mNightModeFlags = i;
            applyConfigurationColors(configuration);
        }
        if (z) {
            invalidate();
        }
    }

    @Override // android.view.View
    public final void onDraw(Canvas canvas) {
        float width = getWidth() - (this.mFlyoutToDotWidthDelta * this.mPercentTransitionedToDot);
        float height = getHeight();
        float f = this.mFlyoutToDotHeightDelta;
        float f2 = this.mPercentTransitionedToDot;
        float f3 = height - (f * f2);
        float m = AndroidFlingSpline$$ExternalSyntheticOutline0.m(1.0f, f2, this.mCornerRadius, this.mNewDotRadius * f2);
        this.mBgTranslationX = this.mTranslationXWhenDot * f2;
        this.mBgTranslationY = this.mTranslationYWhenDot * f2;
        RectF rectF = this.mBgRect;
        float f4 = 0 * this.mPercentStillFlyout;
        rectF.set(f4, 0.0f, width - f4, f3);
        this.mBgPaint.setColor(((Integer) this.mArgbEvaluator.evaluate(this.mPercentTransitionedToDot, Integer.valueOf(this.mFloatingBackgroundColor), Integer.valueOf(this.mDotColor))).intValue());
        canvas.save();
        canvas.translate(this.mBgTranslationX, this.mBgTranslationY);
        canvas.drawRoundRect(this.mBgRect, m, m, this.mBgPaint);
        canvas.restore();
        invalidateOutline();
        super.onDraw(canvas);
    }

    public final void setCollapsePercent(float f) {
        if (Float.isNaN(f)) {
            return;
        }
        float max = Math.max(0.0f, Math.min(f, 1.0f));
        this.mPercentTransitionedToDot = max;
        this.mPercentStillFlyout = 1.0f - max;
        float width = max * (this.mArrowPointingLeft ? -getWidth() : getWidth());
        float min = Math.min(1.0f, Math.max(0.0f, (this.mPercentStillFlyout - 0.75f) / 0.25f));
        this.mMessageText.setTranslationX(width);
        this.mMessageText.setAlpha(min);
        this.mSenderText.setTranslationX(width);
        this.mSenderText.setAlpha(min);
        this.mSenderAvatar.setTranslationX(width);
        this.mSenderAvatar.setAlpha(min);
        setTranslationZ(this.mFlyoutElevation - ((r5 - this.mBubbleElevation) * this.mPercentTransitionedToDot));
        invalidate();
    }

    public final void updateDot(PointF pointF, boolean z) {
        float f = z ? 0.0f : this.mNewDotSize;
        this.mFlyoutToDotWidthDelta = getWidth() - f;
        this.mFlyoutToDotHeightDelta = getHeight() - f;
        float f2 = z ? 0.0f : this.mOriginalDotSize / 2.0f;
        float f3 = pointF.x;
        float[] fArr = this.mDotCenter;
        float f4 = (f3 + fArr[0]) - f2;
        float f5 = (pointF.y + fArr[1]) - f2;
        float f6 = this.mRestingTranslationX - f4;
        float f7 = this.mFlyoutY - f5;
        this.mTranslationXWhenDot = -f6;
        this.mTranslationYWhenDot = -f7;
    }

    public final void updateFlyoutMessage(Bubble.FlyoutMessage flyoutMessage) {
        Drawable drawable = flyoutMessage.senderAvatar;
        if (drawable == null || !flyoutMessage.isGroupChat) {
            this.mSenderAvatar.setVisibility(8);
            this.mSenderAvatar.setTranslationX(0.0f);
            this.mMessageText.setTranslationX(0.0f);
            this.mSenderText.setTranslationX(0.0f);
        } else {
            this.mSenderAvatar.setVisibility(0);
            this.mSenderAvatar.setImageDrawable(drawable);
        }
        int max = ((int) (this.mPositioner.mDeviceConfig.isLargeScreen ? Math.max(r0.mScreenRect.width() * 0.3f, r0.mMinimumFlyoutWidthLargeScreen) : r0.mScreenRect.width() * 0.6f)) - (this.mFlyoutPadding * 2);
        if (TextUtils.isEmpty(flyoutMessage.senderName)) {
            this.mSenderText.setVisibility(8);
        } else {
            this.mSenderText.setMaxWidth(max);
            this.mSenderText.setText(flyoutMessage.senderName);
            this.mSenderText.setVisibility(0);
        }
        this.mMessageText.setMaxWidth(max);
        this.mMessageText.setText(flyoutMessage.message);
    }

    public final void updateFlyoutX(float f) {
        this.mRestingTranslationX = this.mArrowPointingLeft ? f + this.mBubbleSize + this.mFlyoutSpaceFromBubble : (f - getWidth()) - this.mFlyoutSpaceFromBubble;
    }

    public final void updateFontSize() {
        float dimensionPixelSize = ((FrameLayout) this).mContext.getResources().getDimensionPixelSize(android.R.dimen.text_size_menu_material);
        this.mMessageText.setTextSize(0, dimensionPixelSize);
        this.mSenderText.setTextSize(0, dimensionPixelSize);
    }
}
