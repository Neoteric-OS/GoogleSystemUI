package com.android.systemui.statusbar.notification;

import android.util.Pools;
import android.view.View;
import android.view.animation.Interpolator;
import android.view.animation.PathInterpolator;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.android.app.animation.Interpolators;
import com.android.internal.widget.MessagingImageMessage;
import com.android.internal.widget.MessagingPropertyAnimator;
import com.android.internal.widget.ViewClippingUtil;
import com.android.systemui.statusbar.CrossFadeHelper;
import com.android.systemui.statusbar.TransformableView;
import com.android.systemui.statusbar.ViewTransformationHelper;
import com.android.systemui.statusbar.notification.row.ExpandableNotificationRow;
import com.android.wm.shell.R;
import java.util.HashMap;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public class TransformState {
    public boolean mAlignEnd;
    public boolean mSameAsAny;
    public ViewTransformationHelper mTransformInfo;
    public View mTransformedView;
    public static final Pools.SimplePool sInstancePool = new Pools.SimplePool(40);
    public static final AnonymousClass1 CLIPPING_PARAMETERS = new AnonymousClass1();
    public final int[] mOwnPosition = new int[2];
    public float mTransformationEndY = -1.0f;
    public Interpolator mDefaultInterpolator = Interpolators.FAST_OUT_SLOW_IN;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.statusbar.notification.TransformState$1, reason: invalid class name */
    public final class AnonymousClass1 implements ViewClippingUtil.ClippingParameters {
        public final void onClippingStateChanged(View view, boolean z) {
            if (view instanceof ExpandableNotificationRow) {
                ExpandableNotificationRow expandableNotificationRow = (ExpandableNotificationRow) view;
                if (z) {
                    expandableNotificationRow.setClipToActualHeight(true);
                } else if (expandableNotificationRow.isChildInGroup()) {
                    expandableNotificationRow.setClipToActualHeight(false);
                }
            }
        }

        public final boolean shouldFinish(View view) {
            if (view instanceof ExpandableNotificationRow) {
                return !((ExpandableNotificationRow) view).isChildInGroup();
            }
            return false;
        }
    }

    public static TransformState createFrom(View view, ViewTransformationHelper viewTransformationHelper) {
        if (view instanceof TextView) {
            TextViewTransformState textViewTransformState = (TextViewTransformState) TextViewTransformState.sInstancePool.acquire();
            if (textViewTransformState == null) {
                textViewTransformState = new TextViewTransformState();
            }
            textViewTransformState.initFrom(view, viewTransformationHelper);
            return textViewTransformState;
        }
        if (view.getId() == 16908767) {
            ActionListTransformState actionListTransformState = (ActionListTransformState) ActionListTransformState.sInstancePool.acquire();
            if (actionListTransformState == null) {
                actionListTransformState = new ActionListTransformState();
            }
            actionListTransformState.initFrom(view, viewTransformationHelper);
            return actionListTransformState;
        }
        if (view.getId() == 16909343) {
            MessagingLayoutTransformState messagingLayoutTransformState = (MessagingLayoutTransformState) MessagingLayoutTransformState.sInstancePool.acquire();
            if (messagingLayoutTransformState == null) {
                messagingLayoutTransformState = new MessagingLayoutTransformState();
                messagingLayoutTransformState.mGroupMap = new HashMap();
            }
            messagingLayoutTransformState.initFrom(view, viewTransformationHelper);
            return messagingLayoutTransformState;
        }
        if (view instanceof MessagingImageMessage) {
            MessagingImageTransformState messagingImageTransformState = (MessagingImageTransformState) MessagingImageTransformState.sInstancePool.acquire();
            if (messagingImageTransformState == null) {
                messagingImageTransformState = new MessagingImageTransformState();
            }
            messagingImageTransformState.initFrom(view, viewTransformationHelper);
            return messagingImageTransformState;
        }
        if (view instanceof ImageView) {
            ImageTransformState imageTransformState = (ImageTransformState) ImageTransformState.sInstancePool.acquire();
            if (imageTransformState == null) {
                imageTransformState = new ImageTransformState();
            }
            imageTransformState.initFrom(view, viewTransformationHelper);
            return imageTransformState;
        }
        if (view instanceof ProgressBar) {
            ProgressTransformState progressTransformState = (ProgressTransformState) ProgressTransformState.sInstancePool.acquire();
            if (progressTransformState == null) {
                progressTransformState = new ProgressTransformState();
            }
            progressTransformState.initFrom(view, viewTransformationHelper);
            return progressTransformState;
        }
        TransformState transformState = (TransformState) sInstancePool.acquire();
        if (transformState == null) {
            transformState = new TransformState();
        }
        transformState.initFrom(view, viewTransformationHelper);
        return transformState;
    }

    public static void setClippingDeactivated(View view, boolean z) {
        ViewClippingUtil.setClippingDeactivated(view, z, CLIPPING_PARAMETERS);
    }

    public final void abortTransformation() {
        View view = this.mTransformedView;
        Float valueOf = Float.valueOf(-1.0f);
        view.setTag(R.id.transformation_start_x_tag, valueOf);
        this.mTransformedView.setTag(R.id.transformation_start_y_tag, valueOf);
        this.mTransformedView.setTag(R.id.transformation_start_scale_x_tag, valueOf);
        this.mTransformedView.setTag(R.id.transformation_start_scale_y_tag, valueOf);
    }

    public void appear(float f, TransformableView transformableView) {
        if (f == 0.0f) {
            prepareFadeIn();
        }
        CrossFadeHelper.fadeIn(this.mTransformedView, f, true);
    }

    public void disappear(float f, TransformableView transformableView) {
        CrossFadeHelper.fadeOut(this.mTransformedView, f, true);
    }

    public final void ensureVisible() {
        if (this.mTransformedView.getVisibility() == 4 || this.mTransformedView.getAlpha() != 1.0f) {
            this.mTransformedView.setAlpha(1.0f);
            this.mTransformedView.setVisibility(0);
        }
    }

    public int getContentHeight() {
        return this.mTransformedView.getHeight();
    }

    public int getContentWidth() {
        return this.mTransformedView.getWidth();
    }

    public final int[] getLaidOutLocationOnScreen() {
        int[] locationOnScreen = getLocationOnScreen();
        locationOnScreen[0] = (int) (locationOnScreen[0] - this.mTransformedView.getTranslationX());
        locationOnScreen[1] = (int) (locationOnScreen[1] - this.mTransformedView.getTranslationY());
        return locationOnScreen;
    }

    public final int[] getLocationOnScreen() {
        View view = this.mTransformedView;
        int[] iArr = this.mOwnPosition;
        view.getLocationOnScreen(iArr);
        iArr[0] = (int) (iArr[0] - (this.mTransformedView.getPivotX() * (1.0f - this.mTransformedView.getScaleX())));
        iArr[1] = (int) (iArr[1] - (this.mTransformedView.getPivotY() * (1.0f - this.mTransformedView.getScaleY())));
        iArr[1] = iArr[1] - (MessagingPropertyAnimator.getTop(this.mTransformedView) - MessagingPropertyAnimator.getLayoutTop(this.mTransformedView));
        return iArr;
    }

    public final float getTransformationStartScaleX() {
        Object tag = this.mTransformedView.getTag(R.id.transformation_start_scale_x_tag);
        if (tag == null) {
            return -1.0f;
        }
        return ((Float) tag).floatValue();
    }

    public final float getTransformationStartScaleY() {
        Object tag = this.mTransformedView.getTag(R.id.transformation_start_scale_y_tag);
        if (tag == null) {
            return -1.0f;
        }
        return ((Float) tag).floatValue();
    }

    public final float getTransformationStartX() {
        Object tag = this.mTransformedView.getTag(R.id.transformation_start_x_tag);
        if (tag == null) {
            return -1.0f;
        }
        return ((Float) tag).floatValue();
    }

    public final float getTransformationStartY() {
        Object tag = this.mTransformedView.getTag(R.id.transformation_start_y_tag);
        if (tag == null) {
            return -1.0f;
        }
        return ((Float) tag).floatValue();
    }

    public void initFrom(View view, ViewTransformationHelper viewTransformationHelper) {
        this.mTransformedView = view;
        this.mTransformInfo = viewTransformationHelper;
        this.mAlignEnd = Boolean.TRUE.equals(view.getTag(R.id.align_transform_end_tag));
    }

    public void prepareFadeIn() {
        resetTransformedView();
    }

    public void recycle() {
        reset();
        if (getClass() == TransformState.class) {
            sInstancePool.release(this);
        }
    }

    public void reset() {
        this.mTransformedView = null;
        this.mTransformInfo = null;
        this.mSameAsAny = false;
        this.mTransformationEndY = -1.0f;
        this.mAlignEnd = false;
        this.mDefaultInterpolator = Interpolators.FAST_OUT_SLOW_IN;
    }

    public void resetTransformedView() {
        this.mTransformedView.setTranslationX(0.0f);
        this.mTransformedView.setTranslationY(0.0f);
        this.mTransformedView.setScaleX(1.0f);
        this.mTransformedView.setScaleY(1.0f);
        setClippingDeactivated(this.mTransformedView, false);
        abortTransformation();
    }

    public boolean sameAs(TransformState transformState) {
        return this.mSameAsAny;
    }

    public void setVisible(boolean z, boolean z2) {
        if (z2 || this.mTransformedView.getVisibility() != 8) {
            if (this.mTransformedView.getVisibility() != 8) {
                this.mTransformedView.setVisibility(z ? 0 : 4);
            }
            this.mTransformedView.animate().cancel();
            this.mTransformedView.setAlpha(z ? 1.0f : 0.0f);
            resetTransformedView();
        }
    }

    public final boolean transformRightEdge(TransformState transformState) {
        boolean z = false;
        boolean z2 = this.mAlignEnd && transformState.mAlignEnd;
        if (this.mTransformedView.isLayoutRtl() && transformState.mTransformedView.isLayoutRtl()) {
            z = true;
        }
        return z2 ^ z;
    }

    public boolean transformScale(TransformState transformState) {
        return sameAs(transformState);
    }

    public void transformViewFrom(TransformState transformState, float f) {
        this.mTransformedView.animate().cancel();
        if (sameAs(transformState)) {
            ensureVisible();
        } else {
            CrossFadeHelper.fadeIn(this.mTransformedView, f, true);
        }
        transformViewFullyFrom(transformState, f);
    }

    public void transformViewFullyFrom(TransformState transformState, float f) {
        transformViewFrom(transformState, 17, null, f);
    }

    public void transformViewFullyTo(TransformState transformState, float f) {
        transformViewTo(transformState, 17, null, f);
    }

    public boolean transformViewTo(TransformState transformState, float f) {
        this.mTransformedView.animate().cancel();
        if (!sameAs(transformState)) {
            CrossFadeHelper.fadeOut(this.mTransformedView, f, true);
            transformViewFullyTo(transformState, f);
            return true;
        }
        if (this.mTransformedView.getVisibility() != 0) {
            return false;
        }
        this.mTransformedView.setAlpha(0.0f);
        this.mTransformedView.setVisibility(4);
        return false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v12, types: [boolean, int] */
    /* JADX WARN: Type inference failed for: r1v27 */
    /* JADX WARN: Type inference failed for: r1v8 */
    public void transformViewFrom(TransformState transformState, int i, ViewTransformationHelper.CustomTransformation customTransformation, float f) {
        int[] locationOnScreen;
        ?? r1;
        Interpolator customInterpolator;
        Interpolator customInterpolator2;
        View view = this.mTransformedView;
        boolean z = (i & 1) != 0;
        int contentHeight = getContentHeight();
        int contentHeight2 = transformState.getContentHeight();
        boolean z2 = (contentHeight2 == contentHeight || contentHeight2 == 0 || contentHeight == 0) ? false : true;
        int contentWidth = getContentWidth();
        int contentWidth2 = transformState.getContentWidth();
        boolean z3 = (contentWidth2 == contentWidth || contentWidth2 == 0 || contentWidth == 0) ? false : true;
        boolean z4 = (z2 || z3) && transformScale(transformState);
        boolean transformRightEdge = transformRightEdge(transformState);
        if (f == 0.0f || ((z && getTransformationStartX() == -1.0f) || getTransformationStartY() == -1.0f || ((z4 && getTransformationStartScaleX() == -1.0f && z3) || (z4 && getTransformationStartScaleY() == -1.0f && z2)))) {
            if (f != 0.0f) {
                locationOnScreen = transformState.getLaidOutLocationOnScreen();
            } else {
                locationOnScreen = transformState.getLocationOnScreen();
            }
            int[] laidOutLocationOnScreen = getLaidOutLocationOnScreen();
            if (customTransformation == 0 || !customTransformation.initTransformation(this, transformState)) {
                if (z) {
                    if (transformRightEdge) {
                        this.mTransformedView.setTag(R.id.transformation_start_x_tag, Float.valueOf((locationOnScreen[0] + transformState.mTransformedView.getWidth()) - (laidOutLocationOnScreen[0] + view.getWidth())));
                    } else {
                        this.mTransformedView.setTag(R.id.transformation_start_x_tag, Float.valueOf(locationOnScreen[0] - laidOutLocationOnScreen[0]));
                    }
                }
                this.mTransformedView.setTag(R.id.transformation_start_y_tag, Float.valueOf(locationOnScreen[1] - laidOutLocationOnScreen[1]));
                View view2 = transformState.mTransformedView;
                if (z4 && z3) {
                    this.mTransformedView.setTag(R.id.transformation_start_scale_x_tag, Float.valueOf((view2.getScaleX() * contentWidth2) / contentWidth));
                    view.setPivotX(transformRightEdge ? view.getWidth() : 0.0f);
                } else {
                    this.mTransformedView.setTag(R.id.transformation_start_scale_x_tag, Float.valueOf(-1.0f));
                }
                if (z4 && z2) {
                    this.mTransformedView.setTag(R.id.transformation_start_scale_y_tag, Float.valueOf((view2.getScaleY() * contentHeight2) / contentHeight));
                    view.setPivotY(0.0f);
                } else {
                    this.mTransformedView.setTag(R.id.transformation_start_scale_y_tag, Float.valueOf(-1.0f));
                }
            }
            if (!z) {
                this.mTransformedView.setTag(R.id.transformation_start_x_tag, Float.valueOf(-1.0f));
            }
            if (!z4) {
                this.mTransformedView.setTag(R.id.transformation_start_scale_x_tag, Float.valueOf(-1.0f));
                this.mTransformedView.setTag(R.id.transformation_start_scale_y_tag, Float.valueOf(-1.0f));
            }
            r1 = 1;
            setClippingDeactivated(view, true);
        } else {
            r1 = 1;
        }
        float interpolation = this.mDefaultInterpolator.getInterpolation(f);
        if (z) {
            view.setTranslationX(NotificationUtils.interpolate(getTransformationStartX(), 0.0f, (customTransformation == 0 || (customInterpolator2 = customTransformation.getCustomInterpolator(r1, r1)) == null) ? interpolation : ((PathInterpolator) customInterpolator2).getInterpolation(f)));
        }
        view.setTranslationY(NotificationUtils.interpolate(getTransformationStartY(), 0.0f, (customTransformation == 0 || (customInterpolator = customTransformation.getCustomInterpolator(16, true)) == null) ? interpolation : ((PathInterpolator) customInterpolator).getInterpolation(f)));
        if (z4) {
            float transformationStartScaleX = getTransformationStartScaleX();
            if (transformationStartScaleX != -1.0f) {
                view.setScaleX(NotificationUtils.interpolate(transformationStartScaleX, 1.0f, interpolation));
            }
            float transformationStartScaleY = getTransformationStartScaleY();
            if (transformationStartScaleY != -1.0f) {
                view.setScaleY(NotificationUtils.interpolate(transformationStartScaleY, 1.0f, interpolation));
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:56:0x0132  */
    /* JADX WARN: Removed duplicated region for block: B:65:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void transformViewTo(com.android.systemui.statusbar.notification.TransformState r18, int r19, com.android.systemui.statusbar.ViewTransformationHelper.CustomTransformation r20, float r21) {
        /*
            Method dump skipped, instructions count: 351
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.statusbar.notification.TransformState.transformViewTo(com.android.systemui.statusbar.notification.TransformState, int, com.android.systemui.statusbar.ViewTransformationHelper$CustomTransformation, float):void");
    }
}
