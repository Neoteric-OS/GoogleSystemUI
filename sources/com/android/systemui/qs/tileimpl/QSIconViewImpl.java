package com.android.systemui.qs.tileimpl;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ArgbEvaluator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.graphics.drawable.Animatable2;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.android.settingslib.Utils;
import com.android.systemui.plugins.qs.QSIconView;
import com.android.systemui.plugins.qs.QSTile;
import com.android.wm.shell.R;
import java.util.Objects;
import java.util.function.Supplier;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class QSIconViewImpl extends QSIconView {
    public boolean mAnimationEnabled;
    public final ValueAnimator mColorAnimator;
    public boolean mDisabledByPolicy;
    public long mHighestScheduledIconChangeTransactionId;
    public final View mIcon;
    public int mIconSizePx;
    QSTile.Icon mLastIcon;
    public long mScheduledIconChangeTransactionId;
    public int mState;
    public int mTint;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class EndRunnableAnimatorListener extends AnimatorListenerAdapter {
        public QSIconViewImpl$$ExternalSyntheticLambda0 mRunnable;

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public final void onAnimationCancel(Animator animator) {
            super.onAnimationCancel(animator);
            this.mRunnable.run();
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public final void onAnimationEnd(Animator animator) {
            super.onAnimationEnd(animator);
            this.mRunnable.run();
        }
    }

    public QSIconViewImpl(Context context) {
        super(context);
        this.mAnimationEnabled = true;
        this.mState = -1;
        this.mDisabledByPolicy = false;
        this.mScheduledIconChangeTransactionId = -1L;
        this.mHighestScheduledIconChangeTransactionId = -1L;
        ValueAnimator valueAnimator = new ValueAnimator();
        this.mColorAnimator = valueAnimator;
        this.mIconSizePx = context.getResources().getDimensionPixelSize(R.dimen.qs_icon_size);
        ImageView imageView = new ImageView(((ViewGroup) this).mContext);
        imageView.setId(android.R.id.icon);
        imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
        this.mIcon = imageView;
        addView(imageView);
        valueAnimator.setDuration(350L);
    }

    @Override // com.android.systemui.plugins.qs.QSIconView
    public final void disableAnimation() {
        this.mAnimationEnabled = false;
    }

    public final int getColor(QSTile.State state) {
        int i;
        Context context = getContext();
        if (state.disabledByPolicy || (i = state.state) == 0) {
            return Utils.getColorAttrDefaultColor(R.attr.outline, 0, context);
        }
        if (i == 1) {
            return Utils.getColorAttrDefaultColor(R.attr.onShadeInactiveVariant, 0, context);
        }
        if (i == 2) {
            return Utils.getColorAttrDefaultColor(R.attr.onShadeActive, 0, context);
        }
        Log.e("QSIconView", "Invalid state " + state);
        return 0;
    }

    @Override // com.android.systemui.plugins.qs.QSIconView
    public final View getIconView() {
        return this.mIcon;
    }

    @Override // android.view.View
    public final void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        this.mIconSizePx = getContext().getResources().getDimensionPixelSize(R.dimen.qs_icon_size);
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int measuredWidth = (getMeasuredWidth() - this.mIcon.getMeasuredWidth()) / 2;
        View view = this.mIcon;
        view.layout(measuredWidth, 0, view.getMeasuredWidth() + measuredWidth, view.getMeasuredHeight());
    }

    @Override // android.view.View
    public final void onMeasure(int i, int i2) {
        int size = View.MeasureSpec.getSize(i);
        this.mIcon.measure(View.MeasureSpec.makeMeasureSpec(size, 1073741824), View.MeasureSpec.makeMeasureSpec(this.mIconSizePx, 1073741824));
        setMeasuredDimension(size, this.mIcon.getMeasuredHeight());
    }

    @Override // com.android.systemui.plugins.qs.QSIconView
    public final void setIcon(QSTile.State state, boolean z) {
        final ImageView imageView = (ImageView) this.mIcon;
        if (state.state == this.mState && state.disabledByPolicy == this.mDisabledByPolicy) {
            updateIcon(imageView, state, z);
            return;
        }
        int color = getColor(state);
        this.mState = state.state;
        this.mDisabledByPolicy = state.disabledByPolicy;
        if (this.mTint == 0 || !z || !this.mAnimationEnabled || !imageView.isShown() || imageView.getDrawable() == null) {
            imageView.setImageTintList(ColorStateList.valueOf(color));
            this.mTint = color;
            updateIcon(imageView, state, z);
            return;
        }
        long j = 1 + this.mHighestScheduledIconChangeTransactionId;
        this.mHighestScheduledIconChangeTransactionId = j;
        this.mScheduledIconChangeTransactionId = j;
        int i = this.mTint;
        QSIconViewImpl$$ExternalSyntheticLambda0 qSIconViewImpl$$ExternalSyntheticLambda0 = new QSIconViewImpl$$ExternalSyntheticLambda0(this, j, imageView, state, z);
        this.mColorAnimator.cancel();
        if (!this.mAnimationEnabled || !ValueAnimator.areAnimatorsEnabled()) {
            imageView.setImageTintList(ColorStateList.valueOf(color));
            this.mTint = color;
            qSIconViewImpl$$ExternalSyntheticLambda0.run();
            return;
        }
        PropertyValuesHolder ofInt = PropertyValuesHolder.ofInt("color", i, color);
        ofInt.setEvaluator(ArgbEvaluator.getInstance());
        this.mColorAnimator.setValues(ofInt);
        this.mColorAnimator.removeAllListeners();
        this.mColorAnimator.removeAllUpdateListeners();
        this.mColorAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.android.systemui.qs.tileimpl.QSIconViewImpl$$ExternalSyntheticLambda1
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                QSIconViewImpl qSIconViewImpl = QSIconViewImpl.this;
                ImageView imageView2 = imageView;
                int intValue = ((Integer) valueAnimator.getAnimatedValue()).intValue();
                imageView2.setImageTintList(ColorStateList.valueOf(intValue));
                qSIconViewImpl.mTint = intValue;
            }
        });
        ValueAnimator valueAnimator = this.mColorAnimator;
        EndRunnableAnimatorListener endRunnableAnimatorListener = new EndRunnableAnimatorListener();
        endRunnableAnimatorListener.mRunnable = qSIconViewImpl$$ExternalSyntheticLambda0;
        valueAnimator.addListener(endRunnableAnimatorListener);
        this.mColorAnimator.start();
    }

    @Override // android.view.View
    public final String toString() {
        StringBuilder sb = new StringBuilder("QSIconViewImpl[");
        sb.append("state=" + this.mState);
        sb.append(", tint=" + this.mTint);
        if (this.mLastIcon != null) {
            sb.append(", lastIcon=" + this.mLastIcon.toString());
        }
        sb.append("]");
        return sb.toString();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r3v0 */
    /* JADX WARN: Type inference failed for: r3v1, types: [android.graphics.drawable.Drawable] */
    /* JADX WARN: Type inference failed for: r3v11 */
    /* JADX WARN: Type inference failed for: r3v12 */
    /* JADX WARN: Type inference failed for: r3v13 */
    /* JADX WARN: Type inference failed for: r3v14 */
    /* JADX WARN: Type inference failed for: r3v2, types: [android.graphics.drawable.Drawable] */
    /* JADX WARN: Type inference failed for: r3v4, types: [android.graphics.drawable.Drawable] */
    /* JADX WARN: Type inference failed for: r7v0, types: [android.widget.ImageView] */
    public final void updateIcon(ImageView imageView, QSTile.State state, boolean z) {
        this.mScheduledIconChangeTransactionId = -1L;
        Supplier supplier = state.iconSupplier;
        QSTile.Icon icon = supplier != null ? (QSTile.Icon) supplier.get() : state.icon;
        if (Objects.equals(icon, imageView.getTag(R.id.qs_icon_tag))) {
            return;
        }
        boolean z2 = z && this.mAnimationEnabled && imageView.isShown() && imageView.getDrawable() != null;
        this.mLastIcon = icon;
        ?? drawable = icon != null ? z2 ? icon.getDrawable(((ViewGroup) this).mContext) : icon.getInvisibleDrawable(((ViewGroup) this).mContext) : 0;
        int padding = icon != null ? icon.getPadding() : 0;
        if (drawable != 0) {
            Drawable.ConstantState constantState = drawable.getConstantState();
            drawable = drawable;
            if (constantState != null) {
                drawable = drawable.getConstantState().newDrawable();
            }
            drawable.setAutoMirrored(false);
            drawable.setLayoutDirection(getLayoutDirection());
        }
        Object drawable2 = imageView.getDrawable();
        if (drawable2 instanceof Animatable2) {
            ((Animatable2) drawable2).clearAnimationCallbacks();
        }
        imageView.setImageDrawable(drawable);
        imageView.setTag(R.id.qs_icon_tag, icon);
        imageView.setPadding(0, padding, 0, padding);
        if (drawable instanceof Animatable2) {
            final Animatable2 animatable2 = (Animatable2) drawable;
            animatable2.start();
            if (!z2) {
                animatable2.stop();
            } else if (state.isTransient) {
                animatable2.registerAnimationCallback(new Animatable2.AnimationCallback() { // from class: com.android.systemui.qs.tileimpl.QSIconViewImpl.1
                    @Override // android.graphics.drawable.Animatable2.AnimationCallback
                    public final void onAnimationEnd(Drawable drawable3) {
                        animatable2.start();
                    }
                });
            }
        }
    }
}
