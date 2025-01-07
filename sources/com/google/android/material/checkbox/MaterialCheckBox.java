package com.google.android.material.checkbox;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.AnimatedStateListDrawable;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.autofill.AutofillManager;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import androidx.activity.ComponentActivity$activityResultRegistry$1$$ExternalSyntheticOutline0;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.appcompat.widget.AppCompatCheckBox;
import androidx.appcompat.widget.AppCompatCompoundButtonHelper;
import androidx.core.app.NotificationCompat$Builder$$ExternalSyntheticOutline0;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.view.ViewCompat;
import androidx.vectordrawable.graphics.drawable.Animatable2Compat;
import androidx.vectordrawable.graphics.drawable.AnimatedVectorDrawableCompat;
import com.android.wm.shell.R;
import com.google.android.material.R$styleable;
import com.google.android.material.color.MaterialColors;
import com.google.android.material.drawable.DrawableUtils;
import com.google.android.material.internal.ThemeEnforcement;
import com.google.android.material.internal.ViewUtils;
import com.google.android.material.resources.MaterialAttributes;
import com.google.android.material.theme.overlay.MaterialThemeOverlay;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.WeakHashMap;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class MaterialCheckBox extends AppCompatCheckBox {
    public boolean broadcasting;
    public Drawable buttonDrawable;
    public Drawable buttonIconDrawable;
    public final ColorStateList buttonIconTintList;
    public final PorterDuff.Mode buttonIconTintMode;
    public ColorStateList buttonTintList;
    public final boolean centerIfNoTextEnabled;
    public int checkedState;
    public int[] currentStateChecked;
    public CharSequence customStateDescription;
    public final CharSequence errorAccessibilityLabel;
    public final boolean errorShown;
    public ColorStateList materialThemeColorsTintList;
    public CompoundButton.OnCheckedChangeListener onCheckedChangeListener;
    public final LinkedHashSet onCheckedStateChangedListeners;
    public final AnimatedVectorDrawableCompat transitionToUnchecked;
    public final AnonymousClass1 transitionToUncheckedCallback;
    public boolean useMaterialThemeColors;
    public boolean usingMaterialButtonDrawable;
    public static final int[] INDETERMINATE_STATE_SET = {R.attr.state_indeterminate};
    public static final int[] ERROR_STATE_SET = {R.attr.state_error};
    public static final int[][] CHECKBOX_STATES = {new int[]{android.R.attr.state_enabled, R.attr.state_error}, new int[]{android.R.attr.state_enabled, android.R.attr.state_checked}, new int[]{android.R.attr.state_enabled, -16842912}, new int[]{-16842910, android.R.attr.state_checked}, new int[]{-16842910, -16842912}};
    public static final int FRAMEWORK_BUTTON_DRAWABLE_RES_ID = Resources.getSystem().getIdentifier("btn_check_material_anim", "drawable", "android");

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class SavedState extends View.BaseSavedState {
        public static final Parcelable.Creator CREATOR = new AnonymousClass1();
        public int checkedState;

        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        /* renamed from: com.google.android.material.checkbox.MaterialCheckBox$SavedState$1, reason: invalid class name */
        public final class AnonymousClass1 implements Parcelable.Creator {
            @Override // android.os.Parcelable.Creator
            public final Object createFromParcel(Parcel parcel) {
                SavedState savedState = new SavedState(parcel);
                savedState.checkedState = ((Integer) parcel.readValue(SavedState.class.getClassLoader())).intValue();
                return savedState;
            }

            @Override // android.os.Parcelable.Creator
            public final Object[] newArray(int i) {
                return new SavedState[i];
            }
        }

        public final String toString() {
            StringBuilder sb = new StringBuilder("MaterialCheckBox.SavedState{");
            sb.append(Integer.toHexString(System.identityHashCode(this)));
            sb.append(" CheckedState=");
            int i = this.checkedState;
            return ComponentActivity$activityResultRegistry$1$$ExternalSyntheticOutline0.m(sb, i != 1 ? i != 2 ? "unchecked" : "indeterminate" : "checked", "}");
        }

        @Override // android.view.View.BaseSavedState, android.view.AbsSavedState, android.os.Parcelable
        public final void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeValue(Integer.valueOf(this.checkedState));
        }
    }

    /* JADX WARN: Type inference failed for: r13v7, types: [com.google.android.material.checkbox.MaterialCheckBox$1] */
    public MaterialCheckBox(Context context, AttributeSet attributeSet) {
        super(MaterialThemeOverlay.wrap(context, attributeSet, R.attr.checkboxStyle, R.style.Widget_MaterialComponents_CompoundButton_CheckBox), attributeSet);
        ColorStateList colorStateList;
        int resourceId;
        ColorStateList colorStateList2;
        int resourceId2;
        int resourceId3;
        new LinkedHashSet();
        this.onCheckedStateChangedListeners = new LinkedHashSet();
        Context context2 = getContext();
        AnimatedVectorDrawableCompat animatedVectorDrawableCompat = new AnimatedVectorDrawableCompat(context2);
        Resources resources = context2.getResources();
        Resources.Theme theme = context2.getTheme();
        ThreadLocal threadLocal = ResourcesCompat.sTempTypedValue;
        Drawable drawable = resources.getDrawable(R.drawable.mtrl_checkbox_button_checked_unchecked, theme);
        drawable.setCallback(animatedVectorDrawableCompat.mCallback);
        new AnimatedVectorDrawableCompat.AnimatedVectorDrawableDelegateState(drawable.getConstantState());
        animatedVectorDrawableCompat.mDelegateDrawable = drawable;
        this.transitionToUnchecked = animatedVectorDrawableCompat;
        this.transitionToUncheckedCallback = new Animatable2Compat.AnimationCallback() { // from class: com.google.android.material.checkbox.MaterialCheckBox.1
            @Override // androidx.vectordrawable.graphics.drawable.Animatable2Compat.AnimationCallback
            public final void onAnimationEnd(Drawable drawable2) {
                ColorStateList colorStateList3 = MaterialCheckBox.this.buttonTintList;
                if (colorStateList3 != null) {
                    drawable2.setTintList(colorStateList3);
                }
            }

            @Override // androidx.vectordrawable.graphics.drawable.Animatable2Compat.AnimationCallback
            public final void onAnimationStart(Drawable drawable2) {
                MaterialCheckBox materialCheckBox = MaterialCheckBox.this;
                ColorStateList colorStateList3 = materialCheckBox.buttonTintList;
                if (colorStateList3 != null) {
                    drawable2.setTint(colorStateList3.getColorForState(materialCheckBox.currentStateChecked, colorStateList3.getDefaultColor()));
                }
            }
        };
        Context context3 = getContext();
        this.buttonDrawable = this.buttonDrawable;
        ColorStateList colorStateList3 = this.buttonTintList;
        this.buttonTintList = colorStateList3 == null ? super.getButtonTintList() != null ? super.getButtonTintList() : null : colorStateList3;
        AppCompatCompoundButtonHelper appCompatCompoundButtonHelper = this.mCompoundButtonHelper;
        if (appCompatCompoundButtonHelper != null) {
            appCompatCompoundButtonHelper.mHasButtonTint = true;
            appCompatCompoundButtonHelper.applyButtonTint();
        }
        int[] iArr = R$styleable.MaterialCheckBox;
        ThemeEnforcement.checkCompatibleTheme(context3, attributeSet, R.attr.checkboxStyle, R.style.Widget_MaterialComponents_CompoundButton_CheckBox);
        ThemeEnforcement.checkTextAppearance(context3, attributeSet, iArr, R.attr.checkboxStyle, R.style.Widget_MaterialComponents_CompoundButton_CheckBox, new int[0]);
        TypedArray obtainStyledAttributes = context3.obtainStyledAttributes(attributeSet, iArr, R.attr.checkboxStyle, R.style.Widget_MaterialComponents_CompoundButton_CheckBox);
        this.buttonIconDrawable = (!obtainStyledAttributes.hasValue(2) || (resourceId3 = obtainStyledAttributes.getResourceId(2, 0)) == 0) ? obtainStyledAttributes.getDrawable(2) : AppCompatResources.getDrawable(resourceId3, context3);
        if (this.buttonDrawable != null && MaterialAttributes.resolveBoolean(context3, R.attr.isMaterial3Theme, false)) {
            int resourceId4 = obtainStyledAttributes.getResourceId(0, 0);
            int resourceId5 = obtainStyledAttributes.getResourceId(1, 0);
            if (resourceId4 == FRAMEWORK_BUTTON_DRAWABLE_RES_ID && resourceId5 == 0) {
                super.setButtonDrawable((Drawable) null);
                this.buttonDrawable = AppCompatResources.getDrawable(R.drawable.mtrl_checkbox_button, context3);
                this.usingMaterialButtonDrawable = true;
                if (this.buttonIconDrawable == null) {
                    this.buttonIconDrawable = AppCompatResources.getDrawable(R.drawable.mtrl_checkbox_button_icon, context3);
                }
            }
        }
        if (!obtainStyledAttributes.hasValue(3) || (resourceId2 = obtainStyledAttributes.getResourceId(3, 0)) == 0 || (colorStateList = ContextCompat.getColorStateList(resourceId2, context3)) == null) {
            colorStateList = (!obtainStyledAttributes.hasValue(3) || (resourceId = obtainStyledAttributes.getResourceId(3, 0)) == 0 || (colorStateList2 = ContextCompat.getColorStateList(resourceId, context3)) == null) ? obtainStyledAttributes.getColorStateList(3) : colorStateList2;
        }
        this.buttonIconTintList = colorStateList;
        this.buttonIconTintMode = ViewUtils.parseTintMode(obtainStyledAttributes.getInt(4, -1), PorterDuff.Mode.SRC_IN);
        this.useMaterialThemeColors = obtainStyledAttributes.getBoolean(10, false);
        this.centerIfNoTextEnabled = obtainStyledAttributes.getBoolean(6, true);
        this.errorShown = obtainStyledAttributes.getBoolean(9, false);
        this.errorAccessibilityLabel = obtainStyledAttributes.getText(8);
        if (obtainStyledAttributes.hasValue(7)) {
            setCheckedState(obtainStyledAttributes.getInt(7, 0));
        }
        obtainStyledAttributes.recycle();
        refreshButtonDrawable();
    }

    @Override // android.widget.CompoundButton
    public final Drawable getButtonDrawable() {
        return this.buttonDrawable;
    }

    @Override // android.widget.CompoundButton
    public final ColorStateList getButtonTintList() {
        return this.buttonTintList;
    }

    @Override // android.widget.CompoundButton, android.widget.Checkable
    public final boolean isChecked() {
        return this.checkedState == 1;
    }

    @Override // android.widget.TextView, android.view.View
    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.useMaterialThemeColors && this.buttonTintList == null && this.buttonIconTintList == null) {
            this.useMaterialThemeColors = true;
            if (this.materialThemeColorsTintList == null) {
                int color = MaterialColors.getColor(this, R.attr.colorControlActivated);
                int color2 = MaterialColors.getColor(this, R.attr.colorError);
                int color3 = MaterialColors.getColor(this, R.attr.colorSurface);
                int color4 = MaterialColors.getColor(this, R.attr.colorOnSurface);
                this.materialThemeColorsTintList = new ColorStateList(CHECKBOX_STATES, new int[]{MaterialColors.layer(color3, 1.0f, color2), MaterialColors.layer(color3, 1.0f, color), MaterialColors.layer(color3, 0.54f, color4), MaterialColors.layer(color3, 0.38f, color4), MaterialColors.layer(color3, 0.38f, color4)});
            }
            setButtonTintList(this.materialThemeColorsTintList);
        }
    }

    @Override // android.widget.CompoundButton, android.widget.TextView, android.view.View
    public final int[] onCreateDrawableState(int i) {
        int[] onCreateDrawableState = super.onCreateDrawableState(i + 2);
        if (this.checkedState == 2) {
            CheckBox.mergeDrawableStates(onCreateDrawableState, INDETERMINATE_STATE_SET);
        }
        if (this.errorShown) {
            CheckBox.mergeDrawableStates(onCreateDrawableState, ERROR_STATE_SET);
        }
        this.currentStateChecked = DrawableUtils.getCheckedState(onCreateDrawableState);
        return onCreateDrawableState;
    }

    @Override // android.widget.CompoundButton, android.widget.TextView, android.view.View
    public final void onDraw(Canvas canvas) {
        Drawable drawable;
        if (!this.centerIfNoTextEnabled || !TextUtils.isEmpty(getText()) || (drawable = this.buttonDrawable) == null) {
            super.onDraw(canvas);
            return;
        }
        WeakHashMap weakHashMap = ViewCompat.sViewPropertyAnimatorMap;
        int width = ((getWidth() - drawable.getIntrinsicWidth()) / 2) * (getLayoutDirection() == 1 ? -1 : 1);
        int save = canvas.save();
        canvas.translate(width, 0.0f);
        super.onDraw(canvas);
        canvas.restoreToCount(save);
        if (getBackground() != null) {
            Rect bounds = drawable.getBounds();
            getBackground().setHotspotBounds(bounds.left + width, bounds.top, bounds.right + width, bounds.bottom);
        }
    }

    @Override // android.view.View
    public final void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        if (accessibilityNodeInfo != null && this.errorShown) {
            accessibilityNodeInfo.setText(((Object) accessibilityNodeInfo.getText()) + ", " + ((Object) this.errorAccessibilityLabel));
        }
    }

    @Override // android.widget.CompoundButton, android.widget.TextView, android.view.View
    public final void onRestoreInstanceState(Parcelable parcelable) {
        if (!(parcelable instanceof SavedState)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.getSuperState());
        setCheckedState(savedState.checkedState);
    }

    @Override // android.widget.CompoundButton, android.widget.TextView, android.view.View
    public final Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        savedState.checkedState = this.checkedState;
        return savedState;
    }

    public final void refreshButtonDrawable() {
        ColorStateList colorStateList;
        ColorStateList colorStateList2;
        AnimatedVectorDrawableCompat animatedVectorDrawableCompat;
        AnimatedVectorDrawableCompat.AnonymousClass2 anonymousClass2;
        this.buttonDrawable = DrawableUtils.createTintableMutatedDrawableIfNeeded(this.buttonDrawable, this.buttonTintList, getButtonTintMode());
        this.buttonIconDrawable = DrawableUtils.createTintableMutatedDrawableIfNeeded(this.buttonIconDrawable, this.buttonIconTintList, this.buttonIconTintMode);
        if (this.usingMaterialButtonDrawable) {
            AnimatedVectorDrawableCompat animatedVectorDrawableCompat2 = this.transitionToUnchecked;
            if (animatedVectorDrawableCompat2 != null) {
                AnonymousClass1 anonymousClass1 = this.transitionToUncheckedCallback;
                if (anonymousClass1 != null) {
                    Drawable drawable = animatedVectorDrawableCompat2.mDelegateDrawable;
                    if (drawable != null) {
                        AnimatedVectorDrawable animatedVectorDrawable = (AnimatedVectorDrawable) drawable;
                        if (anonymousClass1.mPlatformCallback == null) {
                            anonymousClass1.mPlatformCallback = new Animatable2Compat.AnimationCallback.AnonymousClass1();
                        }
                        animatedVectorDrawable.unregisterAnimationCallback(anonymousClass1.mPlatformCallback);
                    }
                    ArrayList arrayList = animatedVectorDrawableCompat2.mAnimationCallbacks;
                    if (arrayList != null) {
                        arrayList.remove(anonymousClass1);
                        if (animatedVectorDrawableCompat2.mAnimationCallbacks.size() == 0 && (anonymousClass2 = animatedVectorDrawableCompat2.mAnimatorListener) != null) {
                            animatedVectorDrawableCompat2.mAnimatedVectorState.mAnimatorSet.removeListener(anonymousClass2);
                            animatedVectorDrawableCompat2.mAnimatorListener = null;
                        }
                    }
                }
                this.transitionToUnchecked.registerAnimationCallback(this.transitionToUncheckedCallback);
            }
            Drawable drawable2 = this.buttonDrawable;
            if ((drawable2 instanceof AnimatedStateListDrawable) && (animatedVectorDrawableCompat = this.transitionToUnchecked) != null) {
                ((AnimatedStateListDrawable) drawable2).addTransition(R.id.checked, R.id.unchecked, animatedVectorDrawableCompat, false);
                ((AnimatedStateListDrawable) this.buttonDrawable).addTransition(R.id.indeterminate, R.id.unchecked, this.transitionToUnchecked, false);
            }
        }
        Drawable drawable3 = this.buttonDrawable;
        if (drawable3 != null && (colorStateList2 = this.buttonTintList) != null) {
            drawable3.setTintList(colorStateList2);
        }
        Drawable drawable4 = this.buttonIconDrawable;
        if (drawable4 != null && (colorStateList = this.buttonIconTintList) != null) {
            drawable4.setTintList(colorStateList);
        }
        super.setButtonDrawable(DrawableUtils.compositeTwoLayeredDrawable(this.buttonDrawable, this.buttonIconDrawable, -1, -1));
        refreshDrawableState();
    }

    @Override // androidx.appcompat.widget.AppCompatCheckBox, android.widget.CompoundButton
    public final void setButtonDrawable(int i) {
        setButtonDrawable(AppCompatResources.getDrawable(i, getContext()));
    }

    @Override // android.widget.CompoundButton
    public final void setButtonTintList(ColorStateList colorStateList) {
        if (this.buttonTintList == colorStateList) {
            return;
        }
        this.buttonTintList = colorStateList;
        refreshButtonDrawable();
    }

    @Override // android.widget.CompoundButton
    public final void setButtonTintMode(PorterDuff.Mode mode) {
        AppCompatCompoundButtonHelper appCompatCompoundButtonHelper = this.mCompoundButtonHelper;
        if (appCompatCompoundButtonHelper != null) {
            appCompatCompoundButtonHelper.mButtonTintMode = mode;
            appCompatCompoundButtonHelper.mHasButtonTintMode = true;
            appCompatCompoundButtonHelper.applyButtonTint();
        }
        refreshButtonDrawable();
    }

    @Override // android.widget.CompoundButton, android.widget.Checkable
    public final void setChecked(boolean z) {
        setCheckedState(z ? 1 : 0);
    }

    public final void setCheckedState(int i) {
        CompoundButton.OnCheckedChangeListener onCheckedChangeListener;
        if (this.checkedState != i) {
            this.checkedState = i;
            super.setChecked(i == 1);
            refreshDrawableState();
            setDefaultStateDescription$1();
            if (this.broadcasting) {
                return;
            }
            this.broadcasting = true;
            LinkedHashSet linkedHashSet = this.onCheckedStateChangedListeners;
            if (linkedHashSet != null) {
                Iterator it = linkedHashSet.iterator();
                if (it.hasNext()) {
                    throw NotificationCompat$Builder$$ExternalSyntheticOutline0.m(it);
                }
            }
            if (this.checkedState != 2 && (onCheckedChangeListener = this.onCheckedChangeListener) != null) {
                onCheckedChangeListener.onCheckedChanged(this, isChecked());
            }
            AutofillManager autofillManager = (AutofillManager) getContext().getSystemService(AutofillManager.class);
            if (autofillManager != null) {
                autofillManager.notifyValueChanged(this);
            }
            this.broadcasting = false;
        }
    }

    public final void setDefaultStateDescription$1() {
        if (this.customStateDescription == null) {
            int i = this.checkedState;
            super.setStateDescription(i == 1 ? getResources().getString(R.string.mtrl_checkbox_state_description_checked) : i == 0 ? getResources().getString(R.string.mtrl_checkbox_state_description_unchecked) : getResources().getString(R.string.mtrl_checkbox_state_description_indeterminate));
        }
    }

    @Override // android.widget.CompoundButton
    public final void setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener onCheckedChangeListener) {
        this.onCheckedChangeListener = onCheckedChangeListener;
    }

    @Override // android.widget.CompoundButton, android.view.View
    public final void setStateDescription(CharSequence charSequence) {
        this.customStateDescription = charSequence;
        if (charSequence == null) {
            setDefaultStateDescription$1();
        } else {
            super.setStateDescription(charSequence);
        }
    }

    @Override // android.widget.CompoundButton, android.widget.Checkable
    public final void toggle() {
        setCheckedState(!isChecked() ? 1 : 0);
    }

    @Override // androidx.appcompat.widget.AppCompatCheckBox, android.widget.CompoundButton
    public final void setButtonDrawable(Drawable drawable) {
        this.buttonDrawable = drawable;
        this.usingMaterialButtonDrawable = false;
        refreshButtonDrawable();
    }
}
