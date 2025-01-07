package com.android.systemui.statusbar.notification;

import android.graphics.drawable.Icon;
import android.util.Pools;
import android.view.View;
import android.view.animation.PathInterpolator;
import android.widget.ImageView;
import com.android.app.animation.Interpolators;
import com.android.systemui.statusbar.CrossFadeHelper;
import com.android.systemui.statusbar.TransformableView;
import com.android.systemui.statusbar.ViewTransformationHelper;
import com.android.systemui.statusbar.notification.row.HybridNotificationView;
import com.android.wm.shell.R;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public class ImageTransformState extends TransformState {
    public static final Pools.SimplePool sInstancePool = new Pools.SimplePool(40);
    public Icon mIcon;

    @Override // com.android.systemui.statusbar.notification.TransformState
    public final void appear(float f, TransformableView transformableView) {
        if (!(transformableView instanceof HybridNotificationView)) {
            super.appear(f, transformableView);
            return;
        }
        if (f == 0.0f) {
            this.mTransformedView.setPivotY(0.0f);
            this.mTransformedView.setPivotX(r0.getWidth() / 2);
            resetTransformedView();
        }
        float max = Math.max(Math.min(((f * 360.0f) - 150.0f) / 210.0f, 1.0f), 0.0f);
        CrossFadeHelper.fadeIn(this.mTransformedView, max, false);
        float interpolation = ((PathInterpolator) Interpolators.LINEAR_OUT_SLOW_IN).getInterpolation(max);
        this.mTransformedView.setScaleX(interpolation);
        this.mTransformedView.setScaleY(interpolation);
    }

    @Override // com.android.systemui.statusbar.notification.TransformState
    public final void disappear(float f, TransformableView transformableView) {
        if (!(transformableView instanceof HybridNotificationView)) {
            super.disappear(f, transformableView);
            return;
        }
        if (f == 0.0f) {
            this.mTransformedView.setPivotY(0.0f);
            this.mTransformedView.setPivotX(r0.getWidth() / 2);
        }
        float max = Math.max(Math.min((((1.0f - f) * 360.0f) - 150.0f) / 210.0f, 1.0f), 0.0f);
        CrossFadeHelper.fadeOut(this.mTransformedView, 1.0f - max, false);
        float interpolation = ((PathInterpolator) Interpolators.LINEAR_OUT_SLOW_IN).getInterpolation(max);
        this.mTransformedView.setScaleX(interpolation);
        this.mTransformedView.setScaleY(interpolation);
    }

    @Override // com.android.systemui.statusbar.notification.TransformState
    public void initFrom(View view, ViewTransformationHelper viewTransformationHelper) {
        super.initFrom(view, viewTransformationHelper);
        if (view instanceof ImageView) {
            this.mIcon = (Icon) view.getTag(R.id.image_icon_tag);
        }
    }

    @Override // com.android.systemui.statusbar.notification.TransformState
    public void recycle() {
        super.recycle();
        if (getClass() == ImageTransformState.class) {
            sInstancePool.release(this);
        }
    }

    @Override // com.android.systemui.statusbar.notification.TransformState
    public void reset() {
        super.reset();
        this.mIcon = null;
    }

    @Override // com.android.systemui.statusbar.notification.TransformState
    public boolean sameAs(TransformState transformState) {
        if (this.mSameAsAny) {
            return true;
        }
        if (!(transformState instanceof ImageTransformState)) {
            return false;
        }
        Icon icon = ((ImageTransformState) transformState).mIcon;
        Icon icon2 = this.mIcon;
        if (icon2 != icon) {
            return (icon2 == null || icon == null || !icon2.sameAs(icon)) ? false : true;
        }
        return true;
    }
}
