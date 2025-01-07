package com.android.settingslib.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatDelegateImpl$$ExternalSyntheticOutline0;
import androidx.preference.R$styleable;
import com.android.wm.shell.R;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public class MainSwitchBar extends LinearLayout implements CompoundButton.OnCheckedChangeListener {
    public static final /* synthetic */ int $r8$clinit = 0;
    public final View mFrameView;
    public final CompoundButton mSwitch;
    public final List mSwitchChangeListeners;
    public final TextView mTextView;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class SavedState extends View.BaseSavedState {
        public static final Parcelable.Creator CREATOR = new AnonymousClass1();
        public boolean mChecked;
        public boolean mVisible;

        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        /* renamed from: com.android.settingslib.widget.MainSwitchBar$SavedState$1, reason: invalid class name */
        public final class AnonymousClass1 implements Parcelable.Creator {
            @Override // android.os.Parcelable.Creator
            public final Object createFromParcel(Parcel parcel) {
                SavedState savedState = new SavedState(parcel);
                savedState.mChecked = ((Boolean) parcel.readValue(null)).booleanValue();
                savedState.mVisible = ((Boolean) parcel.readValue(null)).booleanValue();
                return savedState;
            }

            @Override // android.os.Parcelable.Creator
            public final Object[] newArray(int i) {
                return new SavedState[i];
            }
        }

        public final String toString() {
            StringBuilder sb = new StringBuilder("MainSwitchBar.SavedState{");
            sb.append(Integer.toHexString(System.identityHashCode(this)));
            sb.append(" checked=");
            sb.append(this.mChecked);
            sb.append(" visible=");
            return AppCompatDelegateImpl$$ExternalSyntheticOutline0.m(sb, this.mVisible, "}");
        }

        @Override // android.view.View.BaseSavedState, android.view.AbsSavedState, android.os.Parcelable
        public final void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeValue(Boolean.valueOf(this.mChecked));
            parcel.writeValue(Boolean.valueOf(this.mVisible));
        }
    }

    public MainSwitchBar(Context context) {
        this(context, null);
    }

    @Override // android.widget.CompoundButton.OnCheckedChangeListener
    public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        this.mFrameView.setActivated(z);
        Iterator it = this.mSwitchChangeListeners.iterator();
        while (it.hasNext()) {
            ((CompoundButton.OnCheckedChangeListener) it.next()).onCheckedChanged(this.mSwitch, z);
        }
    }

    @Override // android.view.View
    public final void onRestoreInstanceState(Parcelable parcelable) {
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.getSuperState());
        this.mSwitch.setChecked(savedState.mChecked);
        boolean z = savedState.mChecked;
        CompoundButton compoundButton = this.mSwitch;
        if (compoundButton != null) {
            compoundButton.setChecked(z);
        }
        this.mFrameView.setActivated(z);
        this.mFrameView.setActivated(savedState.mChecked);
        setVisibility(savedState.mVisible ? 0 : 8);
        this.mSwitch.setOnCheckedChangeListener(savedState.mVisible ? this : null);
        requestLayout();
    }

    @Override // android.view.View
    public final Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        savedState.mChecked = this.mSwitch.isChecked();
        savedState.mVisible = getVisibility() == 0;
        return savedState;
    }

    @Override // android.view.View
    public final boolean performClick() {
        this.mSwitch.performClick();
        return super.performClick();
    }

    @Override // android.view.View
    public final void setEnabled(boolean z) {
        super.setEnabled(z);
        this.mTextView.setEnabled(z);
        this.mSwitch.setEnabled(z);
        this.mFrameView.setEnabled(z);
        this.mFrameView.setActivated(this.mSwitch.isChecked());
    }

    public MainSwitchBar(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public MainSwitchBar(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, 0);
    }

    public MainSwitchBar(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        ArrayList arrayList = new ArrayList();
        this.mSwitchChangeListeners = arrayList;
        LayoutInflater.from(context).inflate(R.layout.settingslib_main_switch_bar, this);
        setFocusable(true);
        setClickable(true);
        View findViewById = findViewById(R.id.frame);
        this.mFrameView = findViewById;
        this.mTextView = (TextView) findViewById(R.id.switch_text);
        CompoundButton compoundButton = (CompoundButton) findViewById(android.R.id.switch_widget);
        this.mSwitch = compoundButton;
        CompoundButton.OnCheckedChangeListener onCheckedChangeListener = new CompoundButton.OnCheckedChangeListener() { // from class: com.android.settingslib.widget.MainSwitchBar$$ExternalSyntheticLambda0
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton2, boolean z) {
                MainSwitchBar mainSwitchBar = MainSwitchBar.this;
                int i3 = MainSwitchBar.$r8$clinit;
                CompoundButton compoundButton3 = mainSwitchBar.mSwitch;
                if (compoundButton3 != null) {
                    compoundButton3.setChecked(z);
                }
                mainSwitchBar.mFrameView.setActivated(z);
            }
        };
        if (!arrayList.contains(onCheckedChangeListener)) {
            arrayList.add(onCheckedChangeListener);
        }
        if (compoundButton.getVisibility() == 0) {
            compoundButton.setOnCheckedChangeListener(this);
        }
        boolean isChecked = compoundButton.isChecked();
        CompoundButton compoundButton2 = this.mSwitch;
        if (compoundButton2 != null) {
            compoundButton2.setChecked(isChecked);
        }
        findViewById.setActivated(isChecked);
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.Preference, 0, 0);
            CharSequence text = obtainStyledAttributes.getText(4);
            TextView textView = this.mTextView;
            if (textView != null) {
                textView.setText(text);
            }
            obtainStyledAttributes.recycle();
        }
        findViewById.setActivated(compoundButton.isChecked());
    }
}
