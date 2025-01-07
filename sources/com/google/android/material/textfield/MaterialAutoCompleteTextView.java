package com.google.android.material.textfield;

import android.R;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.RippleDrawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatAutoCompleteTextView;
import androidx.appcompat.widget.AppCompatPopupWindow;
import androidx.appcompat.widget.ListPopupWindow;
import androidx.core.graphics.ColorUtils;
import androidx.core.view.ViewCompat;
import com.google.android.material.R$styleable;
import com.google.android.material.internal.ThemeEnforcement;
import com.google.android.material.resources.MaterialResources;
import com.google.android.material.theme.overlay.MaterialThemeOverlay;
import java.util.WeakHashMap;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class MaterialAutoCompleteTextView extends AppCompatAutoCompleteTextView {
    public final AccessibilityManager accessibilityManager;
    public final ListPopupWindow modalListPopup;
    public final int simpleItemSelectedColor;
    public final ColorStateList simpleItemSelectedRippleColor;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class MaterialArrayAdapter extends ArrayAdapter {
        public final ColorStateList pressedRippleColor;
        public final ColorStateList selectedItemRippleOverlaidColor;

        public MaterialArrayAdapter(Context context, int i, String[] strArr) {
            super(context, i, strArr);
            ColorStateList colorStateList;
            ColorStateList colorStateList2;
            ColorStateList colorStateList3 = MaterialAutoCompleteTextView.this.simpleItemSelectedRippleColor;
            ColorStateList colorStateList4 = null;
            if (colorStateList3 != null) {
                int[] iArr = {R.attr.state_pressed};
                colorStateList = new ColorStateList(new int[][]{iArr, new int[0]}, new int[]{colorStateList3.getColorForState(iArr, 0), 0});
            } else {
                colorStateList = null;
            }
            this.pressedRippleColor = colorStateList;
            if (MaterialAutoCompleteTextView.this.simpleItemSelectedColor != 0 && (colorStateList2 = MaterialAutoCompleteTextView.this.simpleItemSelectedRippleColor) != null) {
                int[] iArr2 = {R.attr.state_hovered, -16842919};
                int[] iArr3 = {R.attr.state_selected, -16842919};
                colorStateList4 = new ColorStateList(new int[][]{iArr3, iArr2, new int[0]}, new int[]{ColorUtils.compositeColors(colorStateList2.getColorForState(iArr3, 0), MaterialAutoCompleteTextView.this.simpleItemSelectedColor), ColorUtils.compositeColors(MaterialAutoCompleteTextView.this.simpleItemSelectedRippleColor.getColorForState(iArr2, 0), MaterialAutoCompleteTextView.this.simpleItemSelectedColor), MaterialAutoCompleteTextView.this.simpleItemSelectedColor});
            }
            this.selectedItemRippleOverlaidColor = colorStateList4;
        }

        @Override // android.widget.ArrayAdapter, android.widget.Adapter
        public final View getView(int i, View view, ViewGroup viewGroup) {
            View view2 = super.getView(i, view, viewGroup);
            if (view2 instanceof TextView) {
                TextView textView = (TextView) view2;
                Drawable drawable = null;
                if (MaterialAutoCompleteTextView.this.getText().toString().contentEquals(textView.getText()) && MaterialAutoCompleteTextView.this.simpleItemSelectedColor != 0) {
                    ColorDrawable colorDrawable = new ColorDrawable(MaterialAutoCompleteTextView.this.simpleItemSelectedColor);
                    if (this.pressedRippleColor != null) {
                        colorDrawable.setTintList(this.selectedItemRippleOverlaidColor);
                        drawable = new RippleDrawable(this.pressedRippleColor, colorDrawable, null);
                    } else {
                        drawable = colorDrawable;
                    }
                }
                WeakHashMap weakHashMap = ViewCompat.sViewPropertyAnimatorMap;
                textView.setBackground(drawable);
            }
            return view2;
        }
    }

    public MaterialAutoCompleteTextView(Context context, AttributeSet attributeSet) {
        super(MaterialThemeOverlay.wrap(context, attributeSet, com.android.wm.shell.R.attr.autoCompleteTextViewStyle, 0), attributeSet, com.android.wm.shell.R.attr.autoCompleteTextViewStyle);
        new Rect();
        Context context2 = getContext();
        int[] iArr = R$styleable.MaterialAutoCompleteTextView;
        ThemeEnforcement.checkCompatibleTheme(context2, attributeSet, com.android.wm.shell.R.attr.autoCompleteTextViewStyle, com.android.wm.shell.R.style.Widget_AppCompat_AutoCompleteTextView);
        ThemeEnforcement.checkTextAppearance(context2, attributeSet, iArr, com.android.wm.shell.R.attr.autoCompleteTextViewStyle, com.android.wm.shell.R.style.Widget_AppCompat_AutoCompleteTextView, new int[0]);
        TypedArray obtainStyledAttributes = context2.obtainStyledAttributes(attributeSet, iArr, com.android.wm.shell.R.attr.autoCompleteTextViewStyle, com.android.wm.shell.R.style.Widget_AppCompat_AutoCompleteTextView);
        if (obtainStyledAttributes.hasValue(0) && obtainStyledAttributes.getInt(0, 0) == 0) {
            setKeyListener(null);
        }
        int resourceId = obtainStyledAttributes.getResourceId(3, com.android.wm.shell.R.layout.mtrl_auto_complete_simple_item);
        obtainStyledAttributes.getDimensionPixelOffset(1, com.android.wm.shell.R.dimen.mtrl_exposed_dropdown_menu_popup_elevation);
        if (obtainStyledAttributes.hasValue(2)) {
            ColorStateList.valueOf(obtainStyledAttributes.getColor(2, 0));
        }
        this.simpleItemSelectedColor = obtainStyledAttributes.getColor(4, 0);
        this.simpleItemSelectedRippleColor = MaterialResources.getColorStateList(context2, obtainStyledAttributes, 5);
        this.accessibilityManager = (AccessibilityManager) context2.getSystemService("accessibility");
        ListPopupWindow listPopupWindow = new ListPopupWindow(context2, null, com.android.wm.shell.R.attr.listPopupWindowStyle);
        AppCompatPopupWindow appCompatPopupWindow = listPopupWindow.mPopup;
        this.modalListPopup = listPopupWindow;
        listPopupWindow.mModal = true;
        appCompatPopupWindow.setFocusable(true);
        listPopupWindow.mDropDownAnchorView = this;
        appCompatPopupWindow.setInputMethodMode(2);
        listPopupWindow.setAdapter(getAdapter());
        listPopupWindow.mItemClickListener = new AdapterView.OnItemClickListener() { // from class: com.google.android.material.textfield.MaterialAutoCompleteTextView.1
            @Override // android.widget.AdapterView.OnItemClickListener
            public final void onItemClick(AdapterView adapterView, View view, int i, long j) {
                Object item;
                MaterialAutoCompleteTextView materialAutoCompleteTextView = MaterialAutoCompleteTextView.this;
                if (i < 0) {
                    ListPopupWindow listPopupWindow2 = materialAutoCompleteTextView.modalListPopup;
                    item = !listPopupWindow2.mPopup.isShowing() ? null : listPopupWindow2.mDropDownList.getSelectedItem();
                } else {
                    item = materialAutoCompleteTextView.getAdapter().getItem(i);
                }
                MaterialAutoCompleteTextView.access$100(MaterialAutoCompleteTextView.this, item);
                AdapterView.OnItemClickListener onItemClickListener = MaterialAutoCompleteTextView.this.getOnItemClickListener();
                if (onItemClickListener != null) {
                    if (view == null || i < 0) {
                        ListPopupWindow listPopupWindow3 = MaterialAutoCompleteTextView.this.modalListPopup;
                        view = listPopupWindow3.mPopup.isShowing() ? listPopupWindow3.mDropDownList.getSelectedView() : null;
                        ListPopupWindow listPopupWindow4 = MaterialAutoCompleteTextView.this.modalListPopup;
                        i = !listPopupWindow4.mPopup.isShowing() ? -1 : listPopupWindow4.mDropDownList.getSelectedItemPosition();
                        ListPopupWindow listPopupWindow5 = MaterialAutoCompleteTextView.this.modalListPopup;
                        j = !listPopupWindow5.mPopup.isShowing() ? Long.MIN_VALUE : listPopupWindow5.mDropDownList.getSelectedItemId();
                    }
                    onItemClickListener.onItemClick(MaterialAutoCompleteTextView.this.modalListPopup.mDropDownList, view, i, j);
                }
                MaterialAutoCompleteTextView.this.modalListPopup.dismiss();
            }
        };
        if (obtainStyledAttributes.hasValue(6)) {
            setAdapter(new MaterialArrayAdapter(getContext(), resourceId, getResources().getStringArray(obtainStyledAttributes.getResourceId(6, 0))));
        }
        obtainStyledAttributes.recycle();
    }

    public static void access$100(MaterialAutoCompleteTextView materialAutoCompleteTextView, Object obj) {
        materialAutoCompleteTextView.setText(materialAutoCompleteTextView.convertSelectionToString(obj), false);
    }

    @Override // android.widget.AutoCompleteTextView
    public final void dismissDropDown() {
        AccessibilityManager accessibilityManager = this.accessibilityManager;
        if (accessibilityManager == null || !accessibilityManager.isTouchExplorationEnabled()) {
            super.dismissDropDown();
        } else {
            this.modalListPopup.dismiss();
        }
    }

    @Override // android.widget.TextView
    public final CharSequence getHint() {
        for (ViewParent parent = getParent(); parent != null; parent = parent.getParent()) {
        }
        return super.getHint();
    }

    @Override // android.widget.AutoCompleteTextView, android.widget.TextView, android.view.View
    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        for (ViewParent parent = getParent(); parent != null; parent = parent.getParent()) {
        }
    }

    @Override // android.widget.AutoCompleteTextView, android.view.View
    public final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.modalListPopup.dismiss();
    }

    @Override // android.widget.TextView, android.view.View
    public final void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        if (View.MeasureSpec.getMode(i) == Integer.MIN_VALUE) {
            int measuredWidth = getMeasuredWidth();
            getAdapter();
            for (ViewParent parent = getParent(); parent != null; parent = parent.getParent()) {
            }
            setMeasuredDimension(Math.min(Math.max(measuredWidth, 0), View.MeasureSpec.getSize(i)), getMeasuredHeight());
        }
    }

    @Override // android.widget.AutoCompleteTextView, android.widget.TextView, android.view.View
    public final void onWindowFocusChanged(boolean z) {
        AccessibilityManager accessibilityManager = this.accessibilityManager;
        if (accessibilityManager == null || !accessibilityManager.isTouchExplorationEnabled()) {
            super.onWindowFocusChanged(z);
        }
    }

    @Override // android.widget.AutoCompleteTextView
    public final void setAdapter(ListAdapter listAdapter) {
        super.setAdapter(listAdapter);
        this.modalListPopup.setAdapter(getAdapter());
    }

    @Override // android.widget.AutoCompleteTextView
    public final void setDropDownBackgroundDrawable(Drawable drawable) {
        super.setDropDownBackgroundDrawable(drawable);
        ListPopupWindow listPopupWindow = this.modalListPopup;
        if (listPopupWindow != null) {
            listPopupWindow.setBackgroundDrawable(drawable);
        }
    }

    @Override // android.widget.AutoCompleteTextView
    public final void setOnItemSelectedListener(AdapterView.OnItemSelectedListener onItemSelectedListener) {
        super.setOnItemSelectedListener(onItemSelectedListener);
        this.modalListPopup.mItemSelectedListener = getOnItemSelectedListener();
    }

    @Override // android.widget.TextView
    public final void setRawInputType(int i) {
        super.setRawInputType(i);
        for (ViewParent parent = getParent(); parent != null; parent = parent.getParent()) {
        }
    }

    @Override // android.widget.AutoCompleteTextView
    public final void showDropDown() {
        AccessibilityManager accessibilityManager = this.accessibilityManager;
        if (accessibilityManager == null || !accessibilityManager.isTouchExplorationEnabled()) {
            super.showDropDown();
        } else {
            this.modalListPopup.show();
        }
    }
}
