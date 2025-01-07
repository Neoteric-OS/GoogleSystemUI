package com.android.systemui.statusbar.notification.row;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.RippleDrawable;
import android.util.AttributeSet;
import android.view.View;
import com.android.internal.util.ContrastColorUtil;
import com.android.keyguard.KeyguardClockSwitchController$$ExternalSyntheticOutline0;
import com.android.keyguard.LegacyLockIconViewController$$ExternalSyntheticOutline0;
import com.android.settingslib.Utils;
import com.android.systemui.Dumpable;
import com.android.systemui.util.ColorUtilKt;
import com.android.wm.shell.R;
import java.io.PrintWriter;
import java.util.Arrays;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public class NotificationBackgroundView extends View implements Dumpable {
    public int mActualHeight;
    public int mActualWidth;
    public Drawable mBackground;
    public boolean mBottomAmountClips;
    public boolean mBottomIsRounded;
    public int mClipBottomAmount;
    public int mClipTopAmount;
    public final float[] mCornerRadii;
    public final ColorStateList mDarkColoredStatefulColors;
    public final boolean mDontModifyCorners;
    public int mDrawableAlpha;
    public int mExpandAnimationHeight;
    public boolean mExpandAnimationRunning;
    public int mExpandAnimationWidth;
    public final float[] mFocusOverlayCornerRadii;
    public final float mFocusOverlayStroke;
    public final ColorStateList mLightColoredStatefulColors;
    public final int mNormalColor;
    public Integer mRippleColor;
    public int mTintColor;

    public NotificationBackgroundView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mCornerRadii = new float[8];
        this.mFocusOverlayCornerRadii = new float[8];
        this.mFocusOverlayStroke = 0.0f;
        this.mBottomAmountClips = true;
        this.mActualHeight = -1;
        this.mActualWidth = -1;
        this.mExpandAnimationWidth = -1;
        this.mExpandAnimationHeight = -1;
        this.mDrawableAlpha = 255;
        this.mDontModifyCorners = getResources().getBoolean(R.bool.config_clipNotificationsToOutline);
        this.mLightColoredStatefulColors = getResources().getColorStateList(R.color.notification_state_color_light);
        this.mDarkColoredStatefulColors = getResources().getColorStateList(R.color.notification_state_color_dark);
        this.mNormalColor = Utils.getColorAttrDefaultColor(android.R.^attr-private.materialColorSurfaceContainerHigh, 0, ((View) this).mContext);
        this.mFocusOverlayStroke = getResources().getDimension(R.dimen.notification_focus_stroke_width);
    }

    @Override // android.view.View
    public final void drawableHotspotChanged(float f, float f2) {
        Drawable drawable = this.mBackground;
        if (drawable != null) {
            drawable.setHotspot(f, f2);
        }
    }

    @Override // android.view.View
    public final void drawableStateChanged() {
        int[] drawableState = getDrawableState();
        Drawable drawable = this.mBackground;
        if (drawable == null || !drawable.isStateful()) {
            return;
        }
        this.mBackground.setState(drawableState);
    }

    @Override // com.android.systemui.Dumpable
    public final void dump(PrintWriter printWriter, String[] strArr) {
        StringBuilder m = LegacyLockIconViewController$$ExternalSyntheticOutline0.m(LegacyLockIconViewController$$ExternalSyntheticOutline0.m(KeyguardClockSwitchController$$ExternalSyntheticOutline0.m(new StringBuilder("mDontModifyCorners: "), this.mDontModifyCorners, printWriter, "mClipTopAmount: "), this.mClipTopAmount, printWriter, "mClipBottomAmount: "), this.mClipBottomAmount, printWriter, "mCornerRadii: ");
        m.append(Arrays.toString(this.mCornerRadii));
        printWriter.println(m.toString());
        StringBuilder m2 = LegacyLockIconViewController$$ExternalSyntheticOutline0.m(KeyguardClockSwitchController$$ExternalSyntheticOutline0.m(KeyguardClockSwitchController$$ExternalSyntheticOutline0.m(new StringBuilder("mBottomIsRounded: "), this.mBottomIsRounded, printWriter, "mBottomAmountClips: "), this.mBottomAmountClips, printWriter, "mActualWidth: "), this.mActualWidth, printWriter, "mActualHeight: ");
        m2.append(this.mActualHeight);
        printWriter.println(m2.toString());
        printWriter.println("mTintColor: ".concat(ColorUtilKt.hexColorString(Integer.valueOf(this.mTintColor))));
        printWriter.println("mRippleColor: ".concat(ColorUtilKt.hexColorString(this.mRippleColor)));
        printWriter.println("mBackground: ".concat(String.valueOf(this.mBackground)));
    }

    public final int getActualHeight$1() {
        int i;
        if (this.mExpandAnimationRunning && (i = this.mExpandAnimationHeight) > -1) {
            return i;
        }
        int i2 = this.mActualHeight;
        return i2 > -1 ? i2 : getHeight();
    }

    @Override // android.view.View
    public final boolean hasOverlappingRendering() {
        return false;
    }

    @Override // android.view.View
    public final void onDraw(Canvas canvas) {
        int i;
        if (this.mClipTopAmount + this.mClipBottomAmount < getActualHeight$1() || this.mExpandAnimationRunning) {
            canvas.save();
            if (!this.mExpandAnimationRunning) {
                canvas.clipRect(0, this.mClipTopAmount, getWidth(), getActualHeight$1() - this.mClipBottomAmount);
            }
            Drawable drawable = this.mBackground;
            if (drawable != null) {
                int actualHeight$1 = getActualHeight$1();
                if (this.mBottomIsRounded && this.mBottomAmountClips && !this.mExpandAnimationRunning) {
                    actualHeight$1 -= this.mClipBottomAmount;
                }
                boolean isLayoutRtl = isLayoutRtl();
                int width = getWidth();
                if ((!this.mExpandAnimationRunning || (i = this.mExpandAnimationWidth) <= -1) && (i = this.mActualWidth) <= -1) {
                    i = getWidth();
                }
                int i2 = isLayoutRtl ? width - i : 0;
                int i3 = isLayoutRtl ? width : i;
                if (this.mExpandAnimationRunning) {
                    i2 = (int) ((width - i) / 2.0f);
                    i3 = i2 + i;
                }
                drawable.setBounds(i2, 0, i3, actualHeight$1);
                drawable.draw(canvas);
            }
            canvas.restore();
        }
    }

    public final void setCustomBackground$1() {
        Drawable drawable = ((View) this).mContext.getDrawable(R.drawable.notification_material_bg);
        Drawable drawable2 = this.mBackground;
        if (drawable2 != null) {
            drawable2.setCallback(null);
            unscheduleDrawable(this.mBackground);
        }
        this.mBackground = drawable;
        this.mRippleColor = null;
        drawable.mutate();
        Drawable drawable3 = this.mBackground;
        if (drawable3 != null) {
            drawable3.setCallback(this);
            setTint(this.mTintColor);
        }
        Drawable drawable4 = this.mBackground;
        if (drawable4 instanceof RippleDrawable) {
            ((RippleDrawable) drawable4).setForceSoftware(true);
        }
        updateBackgroundRadii();
        invalidate();
    }

    public final void setTint(int i) {
        Drawable drawable = ((LayerDrawable) this.mBackground).getDrawable(0);
        drawable.mutate().setTintMode(PorterDuff.Mode.SRC_ATOP);
        drawable.setTint(i);
        this.mTintColor = i;
        if (i != this.mNormalColor) {
            ((GradientDrawable) ((LayerDrawable) this.mBackground).getDrawable(1).mutate()).setColor(ContrastColorUtil.isColorDark(i) ? this.mDarkColoredStatefulColors : this.mLightColoredStatefulColors);
        }
        invalidate();
    }

    public final void updateBackgroundRadii() {
        if (this.mDontModifyCorners) {
            return;
        }
        Drawable drawable = this.mBackground;
        if (!(drawable instanceof LayerDrawable)) {
            return;
        }
        LayerDrawable layerDrawable = (LayerDrawable) drawable;
        int numberOfLayers = layerDrawable.getNumberOfLayers();
        int i = 0;
        for (int i2 = 0; i2 < numberOfLayers; i2++) {
            ((GradientDrawable) layerDrawable.getDrawable(i2)).setCornerRadii(this.mCornerRadii);
        }
        GradientDrawable gradientDrawable = (GradientDrawable) layerDrawable.findDrawableByLayerId(R.id.notification_focus_overlay);
        while (true) {
            float[] fArr = this.mCornerRadii;
            if (i >= fArr.length) {
                gradientDrawable.setCornerRadii(this.mFocusOverlayCornerRadii);
                return;
            } else {
                this.mFocusOverlayCornerRadii[i] = Math.max(0.0f, fArr[i] - this.mFocusOverlayStroke);
                i++;
            }
        }
    }

    @Override // android.view.View
    public final boolean verifyDrawable(Drawable drawable) {
        return super.verifyDrawable(drawable) || drawable == this.mBackground;
    }
}
