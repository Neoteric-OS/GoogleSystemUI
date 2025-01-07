package com.google.android.material.materialswitch;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.util.AttributeSet;
import android.widget.CompoundButton;
import androidx.appcompat.widget.SwitchCompat;
import androidx.appcompat.widget.TintTypedArray;
import androidx.core.graphics.ColorUtils;
import com.android.wm.shell.R;
import com.google.android.material.R$styleable;
import com.google.android.material.drawable.DrawableUtils;
import com.google.android.material.internal.ThemeEnforcement;
import com.google.android.material.internal.ViewUtils;
import com.google.android.material.theme.overlay.MaterialThemeOverlay;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public class MaterialSwitch extends SwitchCompat {
    public static final int[] STATE_SET_WITH_ICON = {R.attr.state_with_icon};
    public int[] currentStateChecked;
    public int[] currentStateUnchecked;
    public final Drawable thumbDrawable;
    public final Drawable thumbIconDrawable;
    public final ColorStateList thumbIconTintList;
    public final ColorStateList thumbTintList;
    public final Drawable trackDecorationDrawable;
    public final ColorStateList trackDecorationTintList;
    public final Drawable trackDrawable;
    public final ColorStateList trackTintList;

    public MaterialSwitch(Context context) {
        this(context, null);
    }

    public static void setInterpolatedDrawableTintIfPossible(Drawable drawable, ColorStateList colorStateList, int[] iArr, int[] iArr2, float f) {
        if (drawable == null || colorStateList == null) {
            return;
        }
        drawable.setTint(ColorUtils.blendARGB(colorStateList.getColorForState(iArr, 0), f, colorStateList.getColorForState(iArr2, 0)));
    }

    @Override // android.view.View
    public final void invalidate() {
        updateDrawableTints();
        super.invalidate();
    }

    @Override // androidx.appcompat.widget.SwitchCompat, android.widget.CompoundButton, android.widget.TextView, android.view.View
    public final int[] onCreateDrawableState(int i) {
        int[] onCreateDrawableState = super.onCreateDrawableState(i + 1);
        if (this.thumbIconDrawable != null) {
            CompoundButton.mergeDrawableStates(onCreateDrawableState, STATE_SET_WITH_ICON);
        }
        int[] iArr = new int[onCreateDrawableState.length];
        int i2 = 0;
        for (int i3 : onCreateDrawableState) {
            if (i3 != 16842912) {
                iArr[i2] = i3;
                i2++;
            }
        }
        this.currentStateUnchecked = iArr;
        this.currentStateChecked = DrawableUtils.getCheckedState(onCreateDrawableState);
        return onCreateDrawableState;
    }

    public final void updateDrawableTints() {
        ColorStateList colorStateList = this.thumbTintList;
        if (colorStateList == null && this.thumbIconTintList == null && this.trackTintList == null && this.trackDecorationTintList == null) {
            return;
        }
        float f = this.mThumbPosition;
        if (colorStateList != null) {
            setInterpolatedDrawableTintIfPossible(this.thumbDrawable, colorStateList, this.currentStateUnchecked, this.currentStateChecked, f);
        }
        ColorStateList colorStateList2 = this.thumbIconTintList;
        if (colorStateList2 != null) {
            setInterpolatedDrawableTintIfPossible(this.thumbIconDrawable, colorStateList2, this.currentStateUnchecked, this.currentStateChecked, f);
        }
        ColorStateList colorStateList3 = this.trackTintList;
        if (colorStateList3 != null) {
            setInterpolatedDrawableTintIfPossible(this.trackDrawable, colorStateList3, this.currentStateUnchecked, this.currentStateChecked, f);
        }
        ColorStateList colorStateList4 = this.trackDecorationTintList;
        if (colorStateList4 != null) {
            setInterpolatedDrawableTintIfPossible(this.trackDecorationDrawable, colorStateList4, this.currentStateUnchecked, this.currentStateChecked, f);
        }
    }

    public MaterialSwitch(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.materialSwitchStyle);
    }

    public MaterialSwitch(Context context, AttributeSet attributeSet, int i) {
        super(MaterialThemeOverlay.wrap(context, attributeSet, i, R.style.Widget_Material3_CompoundButton_MaterialSwitch), attributeSet, i);
        Context context2 = getContext();
        this.thumbDrawable = this.mThumbDrawable;
        ColorStateList colorStateList = this.mThumbTintList;
        this.thumbTintList = colorStateList;
        this.mThumbTintList = null;
        this.mHasThumbTint = true;
        applyThumbTint();
        this.trackDrawable = this.mTrackDrawable;
        ColorStateList colorStateList2 = this.mTrackTintList;
        this.trackTintList = colorStateList2;
        this.mTrackTintList = null;
        this.mHasTrackTint = true;
        applyTrackTint();
        int[] iArr = R$styleable.MaterialSwitch;
        ThemeEnforcement.checkCompatibleTheme(context2, attributeSet, i, R.style.Widget_Material3_CompoundButton_MaterialSwitch);
        ThemeEnforcement.checkTextAppearance(context2, attributeSet, iArr, i, R.style.Widget_Material3_CompoundButton_MaterialSwitch, new int[0]);
        TypedArray obtainStyledAttributes = context2.obtainStyledAttributes(attributeSet, iArr, i, R.style.Widget_Material3_CompoundButton_MaterialSwitch);
        TintTypedArray tintTypedArray = new TintTypedArray(context2, obtainStyledAttributes);
        this.thumbIconDrawable = tintTypedArray.getDrawable(0);
        int dimensionPixelSize = obtainStyledAttributes.getDimensionPixelSize(1, -1);
        ColorStateList colorStateList3 = tintTypedArray.getColorStateList(2);
        this.thumbIconTintList = colorStateList3;
        int i2 = obtainStyledAttributes.getInt(3, -1);
        PorterDuff.Mode mode = PorterDuff.Mode.SRC_IN;
        PorterDuff.Mode parseTintMode = ViewUtils.parseTintMode(i2, mode);
        this.trackDecorationDrawable = tintTypedArray.getDrawable(4);
        ColorStateList colorStateList4 = tintTypedArray.getColorStateList(5);
        this.trackDecorationTintList = colorStateList4;
        PorterDuff.Mode parseTintMode2 = ViewUtils.parseTintMode(obtainStyledAttributes.getInt(6, -1), mode);
        tintTypedArray.recycle();
        this.mEnforceSwitchWidth = false;
        invalidate();
        this.thumbDrawable = DrawableUtils.createTintableMutatedDrawableIfNeeded(this.thumbDrawable, colorStateList, this.mThumbTintMode);
        this.thumbIconDrawable = DrawableUtils.createTintableMutatedDrawableIfNeeded(this.thumbIconDrawable, colorStateList3, parseTintMode);
        updateDrawableTints();
        Drawable compositeTwoLayeredDrawable = DrawableUtils.compositeTwoLayeredDrawable(this.thumbDrawable, this.thumbIconDrawable, dimensionPixelSize, dimensionPixelSize);
        Drawable drawable = this.mThumbDrawable;
        if (drawable != null) {
            drawable.setCallback(null);
        }
        this.mThumbDrawable = compositeTwoLayeredDrawable;
        if (compositeTwoLayeredDrawable != null) {
            compositeTwoLayeredDrawable.setCallback(this);
        }
        requestLayout();
        refreshDrawableState();
        this.trackDrawable = DrawableUtils.createTintableMutatedDrawableIfNeeded(this.trackDrawable, colorStateList2, this.mTrackTintMode);
        this.trackDecorationDrawable = DrawableUtils.createTintableMutatedDrawableIfNeeded(this.trackDecorationDrawable, colorStateList4, parseTintMode2);
        updateDrawableTints();
        Drawable drawable2 = this.trackDrawable;
        if (drawable2 != null && this.trackDecorationDrawable != null) {
            drawable2 = new LayerDrawable(new Drawable[]{this.trackDrawable, this.trackDecorationDrawable});
        } else if (drawable2 == null) {
            drawable2 = this.trackDecorationDrawable;
        }
        if (drawable2 != null) {
            this.mSwitchMinWidth = drawable2.getIntrinsicWidth();
            requestLayout();
        }
        Drawable drawable3 = this.mTrackDrawable;
        if (drawable3 != null) {
            drawable3.setCallback(null);
        }
        this.mTrackDrawable = drawable2;
        if (drawable2 != null) {
            drawable2.setCallback(this);
        }
        requestLayout();
    }
}
