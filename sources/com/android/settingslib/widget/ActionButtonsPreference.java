package com.android.settingslib.widget;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import androidx.preference.Preference;
import androidx.preference.PreferenceViewHolder;
import com.android.wm.shell.R;
import java.util.ArrayList;
import java.util.List;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public class ActionButtonsPreference extends Preference {
    public final List mBtnBackgroundStyle1;
    public final List mBtnBackgroundStyle2;
    public final List mBtnBackgroundStyle3;
    public final List mBtnBackgroundStyle4;
    public final ButtonInfo mButton1Info;
    public final ButtonInfo mButton2Info;
    public final ButtonInfo mButton3Info;
    public final ButtonInfo mButton4Info;
    public View mDivider1;
    public View mDivider2;
    public View mDivider3;
    public final List mVisibleButtonInfos;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class ButtonInfo {
        public Button mButton;

        public final boolean isVisible() {
            return this.mButton.getVisibility() == 0;
        }

        public final void setUpButton() {
            this.mButton.setText((CharSequence) null);
            this.mButton.setOnClickListener(null);
            this.mButton.setEnabled(true);
            this.mButton.setCompoundDrawablesWithIntrinsicBounds((Drawable) null, (Drawable) null, (Drawable) null, (Drawable) null);
            if (TextUtils.isEmpty(null)) {
                this.mButton.setVisibility(8);
            } else {
                this.mButton.setVisibility(0);
            }
        }
    }

    public ActionButtonsPreference(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mButton1Info = new ButtonInfo();
        this.mButton2Info = new ButtonInfo();
        this.mButton3Info = new ButtonInfo();
        this.mButton4Info = new ButtonInfo();
        this.mVisibleButtonInfos = new ArrayList(4);
        ArrayList arrayList = new ArrayList(1);
        this.mBtnBackgroundStyle1 = arrayList;
        ArrayList arrayList2 = new ArrayList(2);
        this.mBtnBackgroundStyle2 = arrayList2;
        ArrayList arrayList3 = new ArrayList(3);
        this.mBtnBackgroundStyle3 = arrayList3;
        ArrayList arrayList4 = new ArrayList(4);
        this.mBtnBackgroundStyle4 = arrayList4;
        this.mLayoutResId = R.layout.settingslib_action_buttons;
        setSelectable(false);
        Resources resources = this.mContext.getResources();
        fetchDrawableArray(arrayList, resources.obtainTypedArray(R.array.background_style1));
        fetchDrawableArray(arrayList2, resources.obtainTypedArray(R.array.background_style2));
        fetchDrawableArray(arrayList3, resources.obtainTypedArray(R.array.background_style3));
        fetchDrawableArray(arrayList4, resources.obtainTypedArray(R.array.background_style4));
    }

    public static void setupBackgrounds(List list, List list2) {
        int i = 0;
        while (true) {
            ArrayList arrayList = (ArrayList) list2;
            if (i >= arrayList.size()) {
                return;
            }
            ((ButtonInfo) ((ArrayList) list).get(i)).mButton.setBackground((Drawable) arrayList.get(i));
            i++;
        }
    }

    public static void setupRtlBackgrounds(List list, List list2) {
        ArrayList arrayList = (ArrayList) list2;
        for (int size = arrayList.size() - 1; size >= 0; size--) {
            ((ButtonInfo) ((ArrayList) list).get((arrayList.size() - 1) - size)).mButton.setBackground((Drawable) arrayList.get(size));
        }
    }

    public final void fetchDrawableArray(List list, TypedArray typedArray) {
        for (int i = 0; i < typedArray.length(); i++) {
            list.add(this.mContext.getDrawable(typedArray.getResourceId(i, 0)));
        }
    }

    @Override // androidx.preference.Preference
    public final void notifyChanged() {
        super.notifyChanged();
        if (this.mVisibleButtonInfos.isEmpty()) {
            return;
        }
        this.mVisibleButtonInfos.clear();
        updateLayout();
    }

    @Override // androidx.preference.Preference
    public final void onBindViewHolder(PreferenceViewHolder preferenceViewHolder) {
        super.onBindViewHolder(preferenceViewHolder);
        preferenceViewHolder.mDividerAllowedAbove = false;
        preferenceViewHolder.mDividerAllowedBelow = false;
        this.mButton1Info.mButton = (Button) preferenceViewHolder.findViewById(R.id.button1);
        this.mButton2Info.mButton = (Button) preferenceViewHolder.findViewById(R.id.button2);
        this.mButton3Info.mButton = (Button) preferenceViewHolder.findViewById(R.id.button3);
        this.mButton4Info.mButton = (Button) preferenceViewHolder.findViewById(R.id.button4);
        this.mDivider1 = preferenceViewHolder.findViewById(R.id.divider1);
        this.mDivider2 = preferenceViewHolder.findViewById(R.id.divider2);
        this.mDivider3 = preferenceViewHolder.findViewById(R.id.divider3);
        this.mButton1Info.setUpButton();
        this.mButton2Info.setUpButton();
        this.mButton3Info.setUpButton();
        this.mButton4Info.setUpButton();
        if (!this.mVisibleButtonInfos.isEmpty()) {
            this.mVisibleButtonInfos.clear();
        }
        updateLayout();
    }

    public final void updateLayout() {
        if (this.mButton1Info.isVisible()) {
            this.mVisibleButtonInfos.add(this.mButton1Info);
        }
        if (this.mButton2Info.isVisible()) {
            this.mVisibleButtonInfos.add(this.mButton2Info);
        }
        if (this.mButton3Info.isVisible()) {
            this.mVisibleButtonInfos.add(this.mButton3Info);
        }
        if (this.mButton4Info.isVisible()) {
            this.mVisibleButtonInfos.add(this.mButton4Info);
        }
        boolean z = this.mContext.getResources().getConfiguration().getLayoutDirection() == 1;
        int size = ((ArrayList) this.mVisibleButtonInfos).size();
        if (size != 1) {
            if (size != 2) {
                if (size != 3) {
                    if (size != 4) {
                        Log.e("ActionButtonPreference", "No visible buttons info, skip background settings.");
                    } else if (z) {
                        setupRtlBackgrounds(this.mVisibleButtonInfos, this.mBtnBackgroundStyle4);
                    } else {
                        setupBackgrounds(this.mVisibleButtonInfos, this.mBtnBackgroundStyle4);
                    }
                } else if (z) {
                    setupRtlBackgrounds(this.mVisibleButtonInfos, this.mBtnBackgroundStyle3);
                } else {
                    setupBackgrounds(this.mVisibleButtonInfos, this.mBtnBackgroundStyle3);
                }
            } else if (z) {
                setupRtlBackgrounds(this.mVisibleButtonInfos, this.mBtnBackgroundStyle2);
            } else {
                setupBackgrounds(this.mVisibleButtonInfos, this.mBtnBackgroundStyle2);
            }
        } else if (z) {
            setupRtlBackgrounds(this.mVisibleButtonInfos, this.mBtnBackgroundStyle1);
        } else {
            setupBackgrounds(this.mVisibleButtonInfos, this.mBtnBackgroundStyle1);
        }
        if (this.mDivider1 != null && this.mButton1Info.isVisible() && this.mButton2Info.isVisible()) {
            this.mDivider1.setVisibility(0);
        }
        if (this.mDivider2 != null && this.mButton3Info.isVisible() && (this.mButton1Info.isVisible() || this.mButton2Info.isVisible())) {
            this.mDivider2.setVisibility(0);
        }
        if (this.mDivider3 == null || ((ArrayList) this.mVisibleButtonInfos).size() <= 1 || !this.mButton4Info.isVisible()) {
            return;
        }
        this.mDivider3.setVisibility(0);
    }
}
