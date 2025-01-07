package com.android.wm.shell.bubbles;

import android.R;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Outline;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.util.PathParser;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewOutlineProvider;
import android.widget.ImageView;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.android.launcher3.icons.DotRenderer;
import com.android.systemui.plugins.DarkIconDispatcher;
import com.android.wm.shell.shared.animation.Interpolators;
import java.util.EnumSet;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public class BadgedImageView extends ConstraintLayout {
    public static final /* synthetic */ int $r8$clinit = 0;
    public float mAnimatingToDotScale;
    public final ImageView mAppIcon;
    public boolean mBadgeOnLeft;
    public BubbleViewProvider mBubble;
    public final ImageView mBubbleIcon;
    public int mDotColor;
    public boolean mDotIsAnimating;
    public boolean mDotOnLeft;
    public DotRenderer mDotRenderer;
    public float mDotScale;
    public final EnumSet mDotSuppressionFlags;
    public final DotRenderer.DrawParams mDrawParams;
    public BubblePositioner mPositioner;
    public final Rect mTempBounds;

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    final class SuppressionFlag {
        public static final /* synthetic */ SuppressionFlag[] $VALUES;
        public static final SuppressionFlag BEHIND_STACK;
        public static final SuppressionFlag FLYOUT_VISIBLE;

        static {
            SuppressionFlag suppressionFlag = new SuppressionFlag("FLYOUT_VISIBLE", 0);
            FLYOUT_VISIBLE = suppressionFlag;
            SuppressionFlag suppressionFlag2 = new SuppressionFlag("BEHIND_STACK", 1);
            BEHIND_STACK = suppressionFlag2;
            $VALUES = new SuppressionFlag[]{suppressionFlag, suppressionFlag2};
        }

        public static SuppressionFlag valueOf(String str) {
            return (SuppressionFlag) Enum.valueOf(SuppressionFlag.class, str);
        }

        public static SuppressionFlag[] values() {
            return (SuppressionFlag[]) $VALUES.clone();
        }
    }

    public BadgedImageView(Context context) {
        this(context, null);
    }

    public final void animateDotScale(float f, final Runnable runnable) {
        this.mDotIsAnimating = true;
        if (this.mAnimatingToDotScale == f || !shouldDrawDot()) {
            this.mDotIsAnimating = false;
            return;
        }
        this.mAnimatingToDotScale = f;
        final boolean z = f > 0.0f;
        clearAnimation();
        animate().setDuration(200L).setInterpolator(Interpolators.FAST_OUT_SLOW_IN).setUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.android.wm.shell.bubbles.BadgedImageView$$ExternalSyntheticLambda0
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                BadgedImageView badgedImageView = BadgedImageView.this;
                boolean z2 = z;
                int i = BadgedImageView.$r8$clinit;
                badgedImageView.getClass();
                float animatedFraction = valueAnimator.getAnimatedFraction();
                if (!z2) {
                    animatedFraction = 1.0f - animatedFraction;
                }
                badgedImageView.mDotScale = animatedFraction;
                badgedImageView.invalidate();
            }
        }).withEndAction(new Runnable() { // from class: com.android.wm.shell.bubbles.BadgedImageView$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                BadgedImageView badgedImageView = BadgedImageView.this;
                boolean z2 = z;
                Runnable runnable2 = runnable;
                int i = BadgedImageView.$r8$clinit;
                badgedImageView.getClass();
                badgedImageView.mDotScale = z2 ? 1.0f : 0.0f;
                badgedImageView.invalidate();
                badgedImageView.mDotIsAnimating = false;
                if (runnable2 != null) {
                    runnable2.run();
                }
            }
        }).start();
    }

    @Override // androidx.constraintlayout.widget.ConstraintLayout, android.view.ViewGroup, android.view.View
    public final void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        if (shouldDrawDot()) {
            getDrawingRect(this.mTempBounds);
            DotRenderer.DrawParams drawParams = this.mDrawParams;
            drawParams.dotColor = this.mDotColor;
            drawParams.iconBounds = this.mTempBounds;
            drawParams.leftAlign = this.mDotOnLeft;
            drawParams.scale = this.mDotScale;
            DotRenderer dotRenderer = this.mDotRenderer;
            dotRenderer.getClass();
            if (drawParams == null) {
                Log.e("DotRenderer", "Invalid null argument(s) passed in call to draw.");
                return;
            }
            canvas.save();
            Rect rect = drawParams.iconBounds;
            float[] fArr = drawParams.leftAlign ? dotRenderer.mLeftDotPosition : dotRenderer.mRightDotPosition;
            float width = (rect.width() * fArr[0]) + rect.left;
            float height = (rect.height() * fArr[1]) + rect.top;
            Rect clipBounds = canvas.getClipBounds();
            boolean z = drawParams.leftAlign;
            float f = dotRenderer.mBitmapOffset;
            canvas.translate(width + (z ? Math.max(0.0f, clipBounds.left - (width + f)) : Math.min(0.0f, clipBounds.right - (width - f))), height + Math.max(0.0f, clipBounds.top - (height + f)));
            float f2 = drawParams.scale;
            canvas.scale(f2, f2);
            dotRenderer.mCirclePaint.setColor(DarkIconDispatcher.DEFAULT_INVERSE_ICON_TINT);
            canvas.drawBitmap(dotRenderer.mBackgroundWithShadow, f, f, dotRenderer.mCirclePaint);
            dotRenderer.mCirclePaint.setColor(drawParams.dotColor);
            canvas.drawCircle(0.0f, 0.0f, dotRenderer.mCircleRadius, dotRenderer.mCirclePaint);
            canvas.restore();
        }
    }

    public final float[] getDotCenter() {
        float[] fArr = this.mDotOnLeft ? this.mDotRenderer.mLeftDotPosition : this.mDotRenderer.mRightDotPosition;
        getDrawingRect(this.mTempBounds);
        return new float[]{this.mTempBounds.width() * fArr[0], this.mTempBounds.height() * fArr[1]};
    }

    public final void hideDotAndBadge(boolean z) {
        if (this.mDotSuppressionFlags.add(SuppressionFlag.BEHIND_STACK)) {
            updateDotVisibility(true);
        }
        this.mBadgeOnLeft = z;
        this.mDotOnLeft = z;
        this.mAppIcon.setVisibility(8);
    }

    public final void initialize(BubblePositioner bubblePositioner) {
        this.mPositioner = bubblePositioner;
        this.mDotRenderer = new DotRenderer(this.mPositioner.mBubbleSize, PathParser.createPathFromPathData(getResources().getString(R.string.config_mainBuiltInDisplayCutout)));
    }

    public final void removeDotSuppressionFlag(SuppressionFlag suppressionFlag) {
        if (this.mDotSuppressionFlags.remove(suppressionFlag)) {
            updateDotVisibility(suppressionFlag == SuppressionFlag.BEHIND_STACK);
        }
    }

    public final void setRenderedBubble(BubbleViewProvider bubbleViewProvider) {
        this.mBubble = bubbleViewProvider;
        this.mBubbleIcon.setImageBitmap(bubbleViewProvider.getBubbleIcon());
        this.mAppIcon.setImageBitmap(bubbleViewProvider.getAppBadge());
        if (this.mDotSuppressionFlags.contains(SuppressionFlag.BEHIND_STACK)) {
            this.mAppIcon.setVisibility(8);
        } else {
            showBadge();
        }
        this.mDotColor = bubbleViewProvider.getDotColor();
        this.mDotRenderer = new DotRenderer(this.mPositioner.mBubbleSize, bubbleViewProvider.getDotPath());
        invalidate();
    }

    public final boolean shouldDrawDot() {
        return this.mDotIsAnimating || (this.mBubble.showDot() && this.mDotSuppressionFlags.isEmpty());
    }

    public final void showBadge() {
        Bitmap appBadge = this.mBubble.getAppBadge();
        BubbleViewProvider bubbleViewProvider = this.mBubble;
        if (bubbleViewProvider instanceof Bubble) {
            ((Bubble) bubbleViewProvider).getClass();
        }
        if (appBadge == null) {
            this.mAppIcon.setVisibility(8);
        } else {
            this.mAppIcon.setTranslationX(this.mBadgeOnLeft ? -(this.mBubble.getBubbleIcon().getWidth() - appBadge.getWidth()) : 0);
            this.mAppIcon.setVisibility(0);
        }
    }

    public final void showDotAndBadge(final boolean z) {
        removeDotSuppressionFlag(SuppressionFlag.BEHIND_STACK);
        if (z != this.mDotOnLeft) {
            if (shouldDrawDot()) {
                animateDotScale(0.0f, new Runnable() { // from class: com.android.wm.shell.bubbles.BadgedImageView$$ExternalSyntheticLambda2
                    @Override // java.lang.Runnable
                    public final void run() {
                        BadgedImageView badgedImageView = BadgedImageView.this;
                        badgedImageView.mDotOnLeft = z;
                        badgedImageView.invalidate();
                        badgedImageView.animateDotScale(1.0f, null);
                    }
                });
            } else {
                this.mDotOnLeft = z;
            }
        }
        this.mBadgeOnLeft = z;
        showBadge();
    }

    @Override // android.view.View
    public final String toString() {
        return "BadgedImageView{" + this.mBubble + "}";
    }

    public final void updateDotVisibility(boolean z) {
        float f = shouldDrawDot() ? 1.0f : 0.0f;
        if (z) {
            animateDotScale(f, null);
            return;
        }
        this.mDotScale = f;
        this.mAnimatingToDotScale = f;
        invalidate();
    }

    public BadgedImageView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public BadgedImageView(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, 0);
    }

    public BadgedImageView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.mDotSuppressionFlags = EnumSet.of(SuppressionFlag.FLYOUT_VISIBLE);
        this.mDotScale = 0.0f;
        this.mAnimatingToDotScale = 0.0f;
        this.mDotIsAnimating = false;
        this.mTempBounds = new Rect();
        setLayoutDirection(0);
        LayoutInflater.from(context).inflate(com.android.wm.shell.R.layout.badged_image_view, this);
        ImageView imageView = (ImageView) findViewById(com.android.wm.shell.R.id.icon_view);
        this.mBubbleIcon = imageView;
        this.mAppIcon = (ImageView) findViewById(com.android.wm.shell.R.id.app_icon_view);
        TypedArray obtainStyledAttributes = ((ViewGroup) this).mContext.obtainStyledAttributes(attributeSet, new int[]{R.attr.src}, i, i2);
        imageView.setImageResource(obtainStyledAttributes.getResourceId(0, 0));
        obtainStyledAttributes.recycle();
        DotRenderer.DrawParams drawParams = new DotRenderer.DrawParams();
        drawParams.iconBounds = new Rect();
        this.mDrawParams = drawParams;
        setFocusable(true);
        setClickable(true);
        setOutlineProvider(new ViewOutlineProvider() { // from class: com.android.wm.shell.bubbles.BadgedImageView.1
            @Override // android.view.ViewOutlineProvider
            public final void getOutline(View view, Outline outline) {
                int i3 = BadgedImageView.this.mPositioner.mBubbleSize;
                int round = (int) Math.round(Math.sqrt((((i3 * i3) * 0.6597222f) * 4.0f) / 3.141592653589793d));
                int i4 = (i3 - round) / 2;
                int i5 = round + i4;
                outline.setOval(i4, i4, i5, i5);
            }
        });
    }
}
