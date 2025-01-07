package androidx.preference;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.accessibility.AccessibilityManager;
import android.widget.Checkable;
import android.widget.CompoundButton;
import androidx.appcompat.widget.SwitchCompat;
import androidx.core.view.ViewCompat;
import com.android.wm.shell.R;
import java.util.WeakHashMap;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public class SwitchPreferenceCompat extends TwoStatePreference {
    public final Listener mListener;
    public final CharSequence mSwitchOff;
    public final CharSequence mSwitchOn;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Listener implements CompoundButton.OnCheckedChangeListener {
        public Listener() {
        }

        @Override // android.widget.CompoundButton.OnCheckedChangeListener
        public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
            if (SwitchPreferenceCompat.this.callChangeListener(Boolean.valueOf(z))) {
                SwitchPreferenceCompat.this.setChecked(z);
            } else {
                compoundButton.setChecked(!z);
            }
        }
    }

    public SwitchPreferenceCompat(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, R.attr.switchPreferenceCompatStyle, 0);
        this.mListener = new Listener();
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.SwitchPreferenceCompat, R.attr.switchPreferenceCompatStyle, 0);
        String string = obtainStyledAttributes.getString(7);
        this.mSummaryOn = string == null ? obtainStyledAttributes.getString(0) : string;
        if (this.mChecked) {
            notifyChanged();
        }
        String string2 = obtainStyledAttributes.getString(6);
        this.mSummaryOff = string2 == null ? obtainStyledAttributes.getString(1) : string2;
        if (!this.mChecked) {
            notifyChanged();
        }
        String string3 = obtainStyledAttributes.getString(9);
        this.mSwitchOn = string3 == null ? obtainStyledAttributes.getString(3) : string3;
        notifyChanged();
        String string4 = obtainStyledAttributes.getString(8);
        this.mSwitchOff = string4 == null ? obtainStyledAttributes.getString(4) : string4;
        notifyChanged();
        this.mDisableDependentsState = obtainStyledAttributes.getBoolean(5, obtainStyledAttributes.getBoolean(2, false));
        obtainStyledAttributes.recycle();
    }

    @Override // androidx.preference.Preference
    public void onBindViewHolder(PreferenceViewHolder preferenceViewHolder) {
        super.onBindViewHolder(preferenceViewHolder);
        syncSwitchView$1(preferenceViewHolder.findViewById(R.id.switchWidget));
        syncSummaryView(preferenceViewHolder.findViewById(android.R.id.summary));
    }

    @Override // androidx.preference.Preference
    public final void performClick(View view) {
        performClick();
        if (((AccessibilityManager) this.mContext.getSystemService("accessibility")).isEnabled()) {
            syncSwitchView$1(view.findViewById(R.id.switchWidget));
            syncSummaryView(view.findViewById(android.R.id.summary));
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void syncSwitchView$1(View view) {
        boolean z = view instanceof SwitchCompat;
        if (z) {
            ((SwitchCompat) view).setOnCheckedChangeListener(null);
        }
        if (view instanceof Checkable) {
            ((Checkable) view).setChecked(this.mChecked);
        }
        if (z) {
            SwitchCompat switchCompat = (SwitchCompat) view;
            switchCompat.setTextOnInternal(this.mSwitchOn);
            switchCompat.requestLayout();
            if (switchCompat.isChecked()) {
                Object obj = switchCompat.mTextOn;
                if (obj == null) {
                    obj = switchCompat.getResources().getString(R.string.abc_capital_on);
                }
                WeakHashMap weakHashMap = ViewCompat.sViewPropertyAnimatorMap;
                new ViewCompat.AnonymousClass2(R.id.tag_state_description, CharSequence.class, 64, 30, 1).set(switchCompat, obj);
            }
            switchCompat.setTextOffInternal(this.mSwitchOff);
            switchCompat.requestLayout();
            if (!switchCompat.isChecked()) {
                Object obj2 = switchCompat.mTextOff;
                if (obj2 == null) {
                    obj2 = switchCompat.getResources().getString(R.string.abc_capital_off);
                }
                WeakHashMap weakHashMap2 = ViewCompat.sViewPropertyAnimatorMap;
                new ViewCompat.AnonymousClass2(R.id.tag_state_description, CharSequence.class, 64, 30, 1).set(switchCompat, obj2);
            }
            switchCompat.setOnCheckedChangeListener(this.mListener);
        }
    }

    public SwitchPreferenceCompat(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }
}
