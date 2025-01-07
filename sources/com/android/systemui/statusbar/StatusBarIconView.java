package com.android.systemui.statusbar;

import android.R;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Trace;
import android.service.notification.StatusBarNotification;
import android.text.TextUtils;
import android.util.FloatProperty;
import android.util.Log;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import android.view.animation.Interpolator;
import android.widget.ImageView;
import androidx.core.graphics.ColorUtils;
import com.android.app.animation.Interpolators;
import com.android.internal.statusbar.StatusBarIcon;
import com.android.internal.util.ContrastColorUtil;
import com.android.systemui.plugins.DarkIconDispatcher;
import com.android.systemui.statusbar.notification.NotificationContentDescription;
import com.android.systemui.statusbar.notification.NotificationDozeHelper;
import com.android.systemui.statusbar.notification.NotificationUtils;
import com.android.systemui.statusbar.phone.NotificationIconContainer$$ExternalSyntheticLambda0;
import java.util.ArrayList;
import java.util.Arrays;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class StatusBarIconView extends AnimatedImageView implements StatusIconDisplayable {
    public static final AnonymousClass1 DOT_APPEAR_AMOUNT;
    public static final AnonymousClass1 ICON_APPEAR_AMOUNT;
    public int mAnimationStartColor;
    public final boolean mBlocked;
    public int mCachedContrastBackgroundColor;
    public ValueAnimator mColorAnimator;
    public final StatusBarIconView$$ExternalSyntheticLambda0 mColorUpdater;
    public final Configuration mConfiguration;
    public int mContrastedDrawableColor;
    public int mCurrentSetColor;
    public int mDecorColor;
    public ObjectAnimator mDotAnimator;
    public float mDotAppearAmount;
    public final Paint mDotPaint;
    public float mDotRadius;
    public float mDozeAmount;
    public final NotificationDozeHelper mDozer;
    public int mDrawableColor;
    public StatusBarIcon mIcon;
    public float mIconAppearAmount;
    public ObjectAnimator mIconAppearAnimator;
    public int mIconColor;
    public float mIconScale;
    public boolean mIncreasedSize;
    public float[] mMatrix;
    public ColorMatrixColorFilter mMatrixColorFilter;
    int mNewStatusBarIconSize;
    public boolean mNightMode;
    public StatusBarNotification mNotification;
    int mOriginalStatusBarIconSize;
    float mScaleToFitNewIconSize;
    public boolean mShowsConversation;
    public final String mSlot;
    public int mStaticDotRadius;
    int mStatusBarIconDrawingSize;
    public int mStatusBarIconDrawingSizeIncreased;
    public float mSystemIconDefaultScale;
    public float mSystemIconDesiredHeight;
    public float mSystemIconIntrinsicHeight;
    public int mVisibleState;

    /* JADX WARN: Type inference failed for: r0v0, types: [com.android.systemui.statusbar.StatusBarIconView$1] */
    /* JADX WARN: Type inference failed for: r0v1, types: [com.android.systemui.statusbar.StatusBarIconView$1] */
    static {
        final int i = 0;
        ICON_APPEAR_AMOUNT = new FloatProperty("iconAppearAmount") { // from class: com.android.systemui.statusbar.StatusBarIconView.1
            @Override // android.util.Property
            public final Float get(Object obj) {
                switch (i) {
                    case 0:
                        return Float.valueOf(((StatusBarIconView) obj).mIconAppearAmount);
                    default:
                        return Float.valueOf(((StatusBarIconView) obj).mDotAppearAmount);
                }
            }

            @Override // android.util.FloatProperty
            public final void setValue(Object obj, float f) {
                switch (i) {
                    case 0:
                        StatusBarIconView statusBarIconView = (StatusBarIconView) obj;
                        if (statusBarIconView.mIconAppearAmount != f) {
                            statusBarIconView.mIconAppearAmount = f;
                            statusBarIconView.invalidate();
                            break;
                        }
                        break;
                    default:
                        StatusBarIconView statusBarIconView2 = (StatusBarIconView) obj;
                        if (statusBarIconView2.mDotAppearAmount != f) {
                            statusBarIconView2.mDotAppearAmount = f;
                            statusBarIconView2.invalidate();
                            break;
                        }
                        break;
                }
            }
        };
        final int i2 = 1;
        DOT_APPEAR_AMOUNT = new FloatProperty("dot_appear_amount") { // from class: com.android.systemui.statusbar.StatusBarIconView.1
            @Override // android.util.Property
            public final Float get(Object obj) {
                switch (i2) {
                    case 0:
                        return Float.valueOf(((StatusBarIconView) obj).mIconAppearAmount);
                    default:
                        return Float.valueOf(((StatusBarIconView) obj).mDotAppearAmount);
                }
            }

            @Override // android.util.FloatProperty
            public final void setValue(Object obj, float f) {
                switch (i2) {
                    case 0:
                        StatusBarIconView statusBarIconView = (StatusBarIconView) obj;
                        if (statusBarIconView.mIconAppearAmount != f) {
                            statusBarIconView.mIconAppearAmount = f;
                            statusBarIconView.invalidate();
                            break;
                        }
                        break;
                    default:
                        StatusBarIconView statusBarIconView2 = (StatusBarIconView) obj;
                        if (statusBarIconView2.mDotAppearAmount != f) {
                            statusBarIconView2.mDotAppearAmount = f;
                            statusBarIconView2.invalidate();
                            break;
                        }
                        break;
                }
            }
        };
    }

    /* JADX WARN: Type inference failed for: r1v1, types: [com.android.systemui.statusbar.StatusBarIconView$$ExternalSyntheticLambda0] */
    public StatusBarIconView(Context context, String str, StatusBarNotification statusBarNotification, boolean z) {
        super(context);
        this.mSystemIconDesiredHeight = 15.0f;
        this.mSystemIconIntrinsicHeight = 17.0f;
        this.mSystemIconDefaultScale = 0.88235295f;
        this.mStatusBarIconDrawingSizeIncreased = 1;
        this.mStatusBarIconDrawingSize = 1;
        this.mOriginalStatusBarIconSize = 1;
        this.mNewStatusBarIconSize = 1;
        this.mScaleToFitNewIconSize = 1.0f;
        this.mIconScale = 1.0f;
        this.mDotPaint = new Paint(1);
        this.mVisibleState = 0;
        this.mIconAppearAmount = 1.0f;
        this.mCurrentSetColor = 0;
        this.mAnimationStartColor = 0;
        this.mColorUpdater = new ValueAnimator.AnimatorUpdateListener() { // from class: com.android.systemui.statusbar.StatusBarIconView$$ExternalSyntheticLambda0
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                StatusBarIconView statusBarIconView = StatusBarIconView.this;
                statusBarIconView.mCurrentSetColor = NotificationUtils.interpolateColors(statusBarIconView.mAnimationStartColor, valueAnimator.getAnimatedFraction(), statusBarIconView.mIconColor);
                statusBarIconView.updateIconColor();
            }
        };
        this.mCachedContrastBackgroundColor = 0;
        this.mDozer = new NotificationDozeHelper();
        this.mBlocked = z;
        this.mSlot = str;
        setNotification(statusBarNotification, statusBarNotification != null ? NotificationContentDescription.contentDescForNotification(((ImageView) this).mContext, statusBarNotification.getNotification()) : null);
        setScaleType(ImageView.ScaleType.CENTER);
        Configuration configuration = new Configuration(context.getResources().getConfiguration());
        this.mConfiguration = configuration;
        this.mNightMode = (configuration.uiMode & 48) == 32;
        if (this.mNotification != null) {
            setDecorColor(getContext().getColor(this.mNightMode ? R.color.notification_default_color_light : R.color.notification_expand_button_state_tint));
        }
        reloadDimens$1();
        maybeUpdateIconScaleDimens();
        setCropToPadding(true);
    }

    public static String getVisibleStateString(int i) {
        return i != 0 ? i != 1 ? i != 2 ? "UNKNOWN" : "HIDDEN" : "DOT" : "ICON";
    }

    public final void debug(int i) {
        super.debug(i);
        Log.d("View", ImageView.debugIndent(i) + "slot=" + this.mSlot);
        Log.d("View", ImageView.debugIndent(i) + "icon=" + this.mIcon);
    }

    @Override // android.view.View
    public final void getDrawingRect(Rect rect) {
        super.getDrawingRect(rect);
        float translationX = getTranslationX();
        float translationY = getTranslationY();
        rect.left = (int) (rect.left + translationX);
        rect.right = (int) (rect.right + translationX);
        rect.top = (int) (rect.top + translationY);
        rect.bottom = (int) (rect.bottom + translationY);
    }

    /* JADX WARN: Code restructure failed: missing block: B:41:0x00da, code lost:
    
        if (r8 != false) goto L51;
     */
    /* JADX WARN: Code restructure failed: missing block: B:80:0x01c1, code lost:
    
        if (r8 != false) goto L51;
     */
    /* JADX WARN: Code restructure failed: missing block: B:82:0x01c9, code lost:
    
        if (r8 != false) goto L51;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final android.graphics.drawable.Drawable getIcon(com.android.internal.statusbar.StatusBarIcon r17) {
        /*
            Method dump skipped, instructions count: 481
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.statusbar.StatusBarIconView.getIcon(com.android.internal.statusbar.StatusBarIcon):android.graphics.drawable.Drawable");
    }

    public final float getIconScaleIncreased() {
        return this.mStatusBarIconDrawingSizeIncreased / this.mStatusBarIconDrawingSize;
    }

    @Override // com.android.systemui.statusbar.StatusIconDisplayable
    public final String getSlot() {
        return this.mSlot;
    }

    @Override // com.android.systemui.statusbar.StatusIconDisplayable
    public final int getVisibleState() {
        return this.mVisibleState;
    }

    @Override // com.android.systemui.statusbar.AnimatedImageView, android.widget.ImageView, android.view.View
    public final boolean hasOverlappingRendering() {
        return false;
    }

    @Override // com.android.systemui.statusbar.StatusIconDisplayable
    public final boolean isIconBlocked() {
        return this.mBlocked;
    }

    @Override // com.android.systemui.statusbar.StatusIconDisplayable
    public final boolean isIconVisible() {
        StatusBarIcon statusBarIcon = this.mIcon;
        return statusBarIcon != null && statusBarIcon.visible;
    }

    public void maybeUpdateIconScaleDimens() {
        int i;
        int i2;
        if (this.mNotification == null) {
            float intrinsicHeight = getDrawable() != null ? getDrawable().getIntrinsicHeight() : this.mSystemIconIntrinsicHeight;
            this.mIconScale = (intrinsicHeight != 0.0f ? this.mSystemIconDesiredHeight / intrinsicHeight : this.mSystemIconDefaultScale) * this.mScaleToFitNewIconSize;
            return;
        }
        ViewGroup.LayoutParams layoutParams = getLayoutParams();
        float f = 1.0f;
        if (getDrawable() != null && layoutParams != null && (i = layoutParams.width) > 0 && (i2 = layoutParams.height) > 0) {
            float intrinsicWidth = getDrawable().getIntrinsicWidth();
            float intrinsicHeight2 = getDrawable().getIntrinsicHeight();
            float min = Math.min(i / intrinsicWidth, i2 / intrinsicHeight2);
            if (min > 1.0f) {
                min = 1.0f;
            }
            float f2 = this.mOriginalStatusBarIconSize;
            float min2 = Math.min(f2 / (intrinsicWidth * min), f2 / (intrinsicHeight2 * min));
            f = min2 > 1.0f ? Math.min(min2, 1.0f / min) : min2;
        }
        this.mIconScale = ((this.mIncreasedSize ? this.mStatusBarIconDrawingSizeIncreased : this.mStatusBarIconDrawingSize) / this.mOriginalStatusBarIconSize) * f * this.mScaleToFitNewIconSize;
        updatePivot$1();
    }

    @Override // android.view.View
    public final void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        int diff = configuration.diff(this.mConfiguration);
        this.mConfiguration.setTo(configuration);
        if ((diff & 1073745920) != 0) {
            updateIconDimens();
        }
        boolean z = (configuration.uiMode & 48) == 32;
        if (z != this.mNightMode) {
            this.mNightMode = z;
            if (this.mNotification != null) {
                setDecorColor(getContext().getColor(this.mNightMode ? R.color.notification_default_color_light : R.color.notification_expand_button_state_tint));
            }
        }
    }

    @Override // com.android.systemui.plugins.DarkIconDispatcher.DarkReceiver
    public final void onDarkChanged(ArrayList arrayList, float f, int i) {
        int tint = DarkIconDispatcher.getTint(arrayList, this, i);
        setImageTintList(ColorStateList.valueOf(tint));
        setDecorColor(tint);
    }

    @Override // android.widget.ImageView, android.view.View
    public final void onDraw(Canvas canvas) {
        float interpolate;
        if (this.mIconAppearAmount > 0.0f) {
            canvas.save();
            int width = getWidth() / 2;
            int height = getHeight() / 2;
            float f = this.mIconScale;
            float f2 = this.mIconAppearAmount;
            canvas.scale(f * f2, f * f2, width, height);
            super.onDraw(canvas);
            canvas.restore();
        }
        if (this.mDotAppearAmount != 0.0f) {
            float alpha = Color.alpha(this.mDecorColor) / 255.0f;
            float f3 = this.mDotAppearAmount;
            if (f3 <= 1.0f) {
                interpolate = this.mDotRadius * f3;
            } else {
                float f4 = f3 - 1.0f;
                alpha *= 1.0f - f4;
                interpolate = NotificationUtils.interpolate(this.mDotRadius, getWidth() / 4, f4);
            }
            this.mDotPaint.setAlpha((int) (alpha * 255.0f));
            canvas.drawCircle(this.mNewStatusBarIconSize / 2, getHeight() / 2, interpolate, this.mDotPaint);
        }
    }

    @Override // android.view.View
    public final void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super.onInitializeAccessibilityEvent(accessibilityEvent);
        StatusBarNotification statusBarNotification = this.mNotification;
        if (statusBarNotification != null) {
            accessibilityEvent.setParcelableData(statusBarNotification.getNotification());
        }
    }

    @Override // android.view.View
    public final void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        updatePivot$1();
    }

    @Override // android.widget.ImageView, android.view.View
    public final void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        if (this.mNotification != null) {
            return;
        }
        setMeasuredDimension((int) (getMeasuredWidth() * this.mScaleToFitNewIconSize), getMeasuredHeight());
    }

    @Override // android.widget.ImageView, android.view.View
    public final void onRtlPropertiesChanged(int i) {
        super.onRtlPropertiesChanged(i);
        updateDrawable(true);
    }

    public final void reloadDimens$1() {
        boolean z = this.mDotRadius == ((float) this.mStaticDotRadius);
        Resources resources = getResources();
        this.mStaticDotRadius = resources.getDimensionPixelSize(com.android.wm.shell.R.dimen.overflow_dot_radius);
        this.mOriginalStatusBarIconSize = resources.getDimensionPixelSize(com.android.wm.shell.R.dimen.status_bar_icon_size);
        int dimensionPixelSize = resources.getDimensionPixelSize(com.android.wm.shell.R.dimen.status_bar_icon_size_sp);
        this.mNewStatusBarIconSize = dimensionPixelSize;
        this.mScaleToFitNewIconSize = dimensionPixelSize / this.mOriginalStatusBarIconSize;
        this.mStatusBarIconDrawingSizeIncreased = resources.getDimensionPixelSize(com.android.wm.shell.R.dimen.status_bar_icon_drawing_size_dark);
        this.mStatusBarIconDrawingSize = resources.getDimensionPixelSize(com.android.wm.shell.R.dimen.status_bar_icon_drawing_size);
        if (z) {
            this.mDotRadius = this.mStaticDotRadius;
        }
        this.mSystemIconDesiredHeight = resources.getDimension(R.dimen.text_handle_min_size);
        float dimension = resources.getDimension(R.dimen.text_edit_floating_toolbar_margin);
        this.mSystemIconIntrinsicHeight = dimension;
        this.mSystemIconDefaultScale = this.mSystemIconDesiredHeight / dimension;
    }

    /* JADX WARN: Code restructure failed: missing block: B:49:0x004b, code lost:
    
        if (r0.getResId() == r3.getResId()) goto L6;
     */
    /* JADX WARN: Removed duplicated region for block: B:19:0x007a  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x008d  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0094  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0050  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean set(com.android.internal.statusbar.StatusBarIcon r7) {
        /*
            r6 = this;
            com.android.internal.statusbar.StatusBarIcon r0 = r6.mIcon
            r1 = 1
            r2 = 0
            if (r0 == 0) goto L52
            android.graphics.drawable.Icon r0 = r0.icon
            android.graphics.drawable.Icon r3 = r7.icon
            if (r0 != r3) goto Le
        Lc:
            r0 = r1
            goto L4e
        Le:
            int r4 = r0.getType()
            int r5 = r3.getType()
            if (r4 == r5) goto L1a
        L18:
            r0 = r2
            goto L4e
        L1a:
            int r4 = r0.getType()
            r5 = 2
            if (r4 == r5) goto L35
            r5 = 4
            if (r4 == r5) goto L28
            r5 = 6
            if (r4 == r5) goto L28
            goto L18
        L28:
            java.lang.String r0 = r0.getUriString()
            java.lang.String r3 = r3.getUriString()
            boolean r0 = r0.equals(r3)
            goto L4e
        L35:
            java.lang.String r4 = r0.getResPackage()
            java.lang.String r5 = r3.getResPackage()
            boolean r4 = r4.equals(r5)
            if (r4 == 0) goto L18
            int r0 = r0.getResId()
            int r3 = r3.getResId()
            if (r0 != r3) goto L18
            goto Lc
        L4e:
            if (r0 == 0) goto L52
            r0 = r1
            goto L53
        L52:
            r0 = r2
        L53:
            if (r0 == 0) goto L5f
            com.android.internal.statusbar.StatusBarIcon r3 = r6.mIcon
            int r3 = r3.iconLevel
            int r4 = r7.iconLevel
            if (r3 != r4) goto L5f
            r3 = r1
            goto L60
        L5f:
            r3 = r2
        L60:
            com.android.internal.statusbar.StatusBarIcon r4 = r6.mIcon
            if (r4 == 0) goto L6c
            boolean r4 = r4.visible
            boolean r5 = r7.visible
            if (r4 != r5) goto L6c
            r4 = r1
            goto L6d
        L6c:
            r4 = r2
        L6d:
            com.android.internal.statusbar.StatusBarIcon r5 = r7.clone()
            r6.mIcon = r5
            java.lang.CharSequence r5 = r7.contentDescription
            r6.setContentDescription(r5)
            if (r0 != 0) goto L8b
            boolean r0 = r6.updateDrawable(r2)
            if (r0 != 0) goto L81
            return r2
        L81:
            r0 = 2131362735(0x7f0a03af, float:1.8345259E38)
            r5 = 0
            r6.setTag(r0, r5)
            r6.maybeUpdateIconScaleDimens()
        L8b:
            if (r3 != 0) goto L92
            int r0 = r7.iconLevel
            r6.setImageLevel(r0)
        L92:
            if (r4 != 0) goto La2
            boolean r7 = r7.visible
            if (r7 == 0) goto L9d
            boolean r7 = r6.mBlocked
            if (r7 != 0) goto L9d
            goto L9f
        L9d:
            r2 = 8
        L9f:
            r6.setVisibility(r2)
        La2:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.statusbar.StatusBarIconView.set(com.android.internal.statusbar.StatusBarIcon):boolean");
    }

    @Override // com.android.systemui.statusbar.StatusIconDisplayable
    public final void setDecorColor(int i) {
        this.mDecorColor = i;
        updateDecorColor();
    }

    public final void setNotification(StatusBarNotification statusBarNotification, CharSequence charSequence) {
        this.mNotification = statusBarNotification;
        if (!TextUtils.isEmpty(charSequence)) {
            setContentDescription(charSequence);
        }
        maybeUpdateIconScaleDimens();
    }

    @Override // com.android.systemui.statusbar.StatusIconDisplayable
    public final void setStaticDrawableColor(int i) {
        this.mDrawableColor = i;
        this.mCurrentSetColor = i;
        updateIconColor();
        updateContrastedStaticColor();
        this.mIconColor = i;
    }

    @Override // com.android.systemui.statusbar.StatusIconDisplayable
    public final void setVisibleState(int i) {
        setVisibleState(i, true, null, 0L);
    }

    @Override // android.view.View
    public final String toString() {
        return "StatusBarIconView(slot='" + this.mSlot + "' alpha=" + getAlpha() + " icon=" + this.mIcon + " visibleState=" + getVisibleStateString(this.mVisibleState) + " iconColor=#" + Integer.toHexString(this.mIconColor) + " staticDrawableColor=#" + Integer.toHexString(this.mDrawableColor) + " decorColor=#" + Integer.toHexString(this.mDecorColor) + " animationStartColor=#" + Integer.toHexString(this.mAnimationStartColor) + " currentSetColor=#" + Integer.toHexString(this.mCurrentSetColor) + " notification=" + this.mNotification + ')';
    }

    public final void updateContrastedStaticColor() {
        float f;
        float abs;
        if (Color.alpha(this.mCachedContrastBackgroundColor) != 255) {
            this.mContrastedDrawableColor = this.mDrawableColor;
            return;
        }
        int i = this.mDrawableColor;
        if (!ContrastColorUtil.satisfiesTextContrast(this.mCachedContrastBackgroundColor, i)) {
            int i2 = this.mDrawableColor;
            ThreadLocal threadLocal = ColorUtils.TEMP_ARRAY;
            float red = Color.red(i2) / 255.0f;
            float green = Color.green(i2) / 255.0f;
            float blue = Color.blue(i2) / 255.0f;
            float max = Math.max(red, Math.max(green, blue));
            float min = Math.min(red, Math.min(green, blue));
            float f2 = max - min;
            float f3 = (max + min) / 2.0f;
            if (max == min) {
                f = 0.0f;
                abs = 0.0f;
            } else {
                f = max == red ? ((green - blue) / f2) % 6.0f : max == green ? ((blue - red) / f2) + 2.0f : ((red - green) / f2) + 4.0f;
                abs = f2 / (1.0f - Math.abs((2.0f * f3) - 1.0f));
            }
            float f4 = (f * 60.0f) % 360.0f;
            if (f4 < 0.0f) {
                f4 += 360.0f;
            }
            i = ContrastColorUtil.resolveContrastColor(((ImageView) this).mContext, new float[]{(f4 > 0.0f ? 1 : (f4 == 0.0f ? 0 : -1)) < 0 ? 0.0f : Math.min(f4, 360.0f), (abs > 0.0f ? 1 : (abs == 0.0f ? 0 : -1)) < 0 ? 0.0f : Math.min(abs, 1.0f), f3 >= 0.0f ? Math.min(f3, 1.0f) : 0.0f}[1] >= 0.2f ? i : 0, this.mCachedContrastBackgroundColor, true ^ ContrastColorUtil.isColorLight(this.mCachedContrastBackgroundColor));
        }
        this.mContrastedDrawableColor = i;
    }

    public final void updateDecorColor() {
        int interpolateColors = NotificationUtils.interpolateColors(this.mDecorColor, this.mDozeAmount, -1);
        if (this.mDotPaint.getColor() != interpolateColors) {
            this.mDotPaint.setColor(interpolateColors);
            if (this.mDotAppearAmount != 0.0f) {
                invalidate();
            }
        }
    }

    public final boolean updateDrawable(boolean z) {
        if (this.mIcon == null) {
            return false;
        }
        try {
            Trace.beginSection("StatusBarIconView#updateDrawable()");
            Drawable icon = getIcon(this.mIcon);
            if (icon != null) {
                if (z) {
                    setImageDrawable(null);
                }
                setImageDrawable(icon);
                return true;
            }
            Log.w("StatusBarIconView", "No icon for slot " + this.mSlot + "; " + this.mIcon.icon);
            return false;
        } catch (OutOfMemoryError unused) {
            Log.w("StatusBarIconView", "OOM while inflating " + this.mIcon.icon + " for slot " + this.mSlot);
            return false;
        } finally {
            Trace.endSection();
        }
    }

    public final void updateIconColor() {
        if (this.mShowsConversation) {
            setColorFilter((ColorFilter) null);
            return;
        }
        if (this.mCurrentSetColor == 0) {
            NotificationDozeHelper notificationDozeHelper = this.mDozer;
            float f = this.mDozeAmount;
            if (f > 0.0f) {
                notificationDozeHelper.mGrayscaleColorMatrix.setSaturation(1.0f - f);
                setColorFilter(new ColorMatrixColorFilter(notificationDozeHelper.mGrayscaleColorMatrix));
                return;
            } else {
                notificationDozeHelper.getClass();
                setColorFilter((ColorFilter) null);
                return;
            }
        }
        if (this.mMatrixColorFilter == null) {
            this.mMatrix = new float[20];
            this.mMatrixColorFilter = new ColorMatrixColorFilter(this.mMatrix);
        }
        int interpolateColors = NotificationUtils.interpolateColors(this.mCurrentSetColor, this.mDozeAmount, -1);
        float[] fArr = this.mMatrix;
        float f2 = this.mDozeAmount * 0.67f;
        Arrays.fill(fArr, 0.0f);
        fArr[4] = Color.red(interpolateColors);
        fArr[9] = Color.green(interpolateColors);
        fArr[14] = Color.blue(interpolateColors);
        fArr[18] = (Color.alpha(interpolateColors) / 255.0f) + f2;
        this.mMatrixColorFilter.setColorMatrixArray(this.mMatrix);
        setColorFilter((ColorFilter) null);
        setColorFilter(this.mMatrixColorFilter);
    }

    public final void updateIconDimens() {
        Trace.beginSection("StatusBarIconView#updateIconDimens");
        try {
            reloadDimens$1();
            updateDrawable(true);
            maybeUpdateIconScaleDimens();
        } finally {
            Trace.endSection();
        }
    }

    public final void updatePivot$1() {
        if (isLayoutRtl()) {
            setPivotX(((this.mIconScale + 1.0f) / 2.0f) * getWidth());
        } else {
            setPivotX(((1.0f - this.mIconScale) / 2.0f) * getWidth());
        }
        setPivotY((getHeight() - (this.mIconScale * getWidth())) / 2.0f);
    }

    @Override // com.android.systemui.statusbar.StatusIconDisplayable
    public final void setVisibleState(int i, boolean z) {
        setVisibleState(i, z, null, 0L);
    }

    public final void setVisibleState(int i, boolean z, final NotificationIconContainer$$ExternalSyntheticLambda0 notificationIconContainer$$ExternalSyntheticLambda0, long j) {
        float f;
        Interpolator interpolator;
        boolean z2;
        boolean z3 = true;
        boolean z4 = false;
        if (i != this.mVisibleState) {
            this.mVisibleState = i;
            ObjectAnimator objectAnimator = this.mIconAppearAnimator;
            if (objectAnimator != null) {
                objectAnimator.cancel();
            }
            ObjectAnimator objectAnimator2 = this.mDotAnimator;
            if (objectAnimator2 != null) {
                objectAnimator2.cancel();
            }
            float f2 = 1.0f;
            if (z) {
                Interpolator interpolator2 = Interpolators.FAST_OUT_LINEAR_IN;
                if (i == 0) {
                    interpolator = Interpolators.LINEAR_OUT_SLOW_IN;
                    f = 1.0f;
                } else {
                    f = 0.0f;
                    interpolator = interpolator2;
                }
                float f3 = this.mIconAppearAmount;
                if (f != f3) {
                    ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this, ICON_APPEAR_AMOUNT, f3, f);
                    this.mIconAppearAnimator = ofFloat;
                    ofFloat.setInterpolator(interpolator);
                    this.mIconAppearAnimator.setDuration(j == 0 ? 100L : j);
                    this.mIconAppearAnimator.addListener(new AnimatorListenerAdapter() { // from class: com.android.systemui.statusbar.StatusBarIconView.4
                        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                        public final void onAnimationEnd(Animator animator) {
                            StatusBarIconView.this.mIconAppearAnimator = null;
                            NotificationIconContainer$$ExternalSyntheticLambda0 notificationIconContainer$$ExternalSyntheticLambda02 = notificationIconContainer$$ExternalSyntheticLambda0;
                            if (notificationIconContainer$$ExternalSyntheticLambda02 != null) {
                                notificationIconContainer$$ExternalSyntheticLambda02.run();
                            }
                        }
                    });
                    this.mIconAppearAnimator.start();
                    z2 = true;
                } else {
                    z2 = false;
                }
                float f4 = i == 0 ? 2.0f : 0.0f;
                if (i == 1) {
                    interpolator2 = Interpolators.LINEAR_OUT_SLOW_IN;
                } else {
                    f2 = f4;
                }
                float f5 = this.mDotAppearAmount;
                if (f2 != f5) {
                    ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(this, DOT_APPEAR_AMOUNT, f5, f2);
                    this.mDotAnimator = ofFloat2;
                    ofFloat2.setInterpolator(interpolator2);
                    this.mDotAnimator.setDuration(j != 0 ? j : 100L);
                    final boolean z5 = !z2;
                    this.mDotAnimator.addListener(new AnimatorListenerAdapter() { // from class: com.android.systemui.statusbar.StatusBarIconView.5
                        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                        public final void onAnimationEnd(Animator animator) {
                            NotificationIconContainer$$ExternalSyntheticLambda0 notificationIconContainer$$ExternalSyntheticLambda02;
                            StatusBarIconView.this.mDotAnimator = null;
                            if (!z5 || (notificationIconContainer$$ExternalSyntheticLambda02 = notificationIconContainer$$ExternalSyntheticLambda0) == null) {
                                return;
                            }
                            notificationIconContainer$$ExternalSyntheticLambda02.run();
                        }
                    });
                    this.mDotAnimator.start();
                } else {
                    z3 = z2;
                }
                z4 = z3;
            } else {
                float f6 = i == 0 ? 1.0f : 0.0f;
                if (this.mIconAppearAmount != f6) {
                    this.mIconAppearAmount = f6;
                    invalidate();
                }
                float f7 = i == 1 ? 1.0f : i == 0 ? 2.0f : 0.0f;
                if (this.mDotAppearAmount != f7) {
                    this.mDotAppearAmount = f7;
                    invalidate();
                }
            }
        }
        if (z4 || notificationIconContainer$$ExternalSyntheticLambda0 == null) {
            return;
        }
        notificationIconContainer$$ExternalSyntheticLambda0.run();
    }
}
