package androidx.preference;

import android.R;
import android.view.View;
import android.view.accessibility.AccessibilityManager;
import android.widget.Checkable;
import android.widget.CompoundButton;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public class CheckBoxPreference extends TwoStatePreference {
    public final Listener mListener;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Listener implements CompoundButton.OnCheckedChangeListener {
        public Listener() {
        }

        @Override // android.widget.CompoundButton.OnCheckedChangeListener
        public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
            if (CheckBoxPreference.this.callChangeListener(Boolean.valueOf(z))) {
                CheckBoxPreference.this.setChecked(z);
            } else {
                compoundButton.setChecked(!z);
            }
        }
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public CheckBoxPreference(android.content.Context r4, android.util.AttributeSet r5) {
        /*
            r3 = this;
            r0 = 0
            r1 = 2130968806(0x7f0400e6, float:1.7546276E38)
            r2 = 16842895(0x101008f, float:2.369396E-38)
            int r1 = androidx.core.content.res.TypedArrayUtils.getAttr(r1, r2, r4)
            r3.<init>(r4, r5, r1, r0)
            androidx.preference.CheckBoxPreference$Listener r2 = new androidx.preference.CheckBoxPreference$Listener
            r2.<init>()
            r3.mListener = r2
            int[] r2 = androidx.preference.R$styleable.CheckBoxPreference
            android.content.res.TypedArray r4 = r4.obtainStyledAttributes(r5, r2, r1, r0)
            r5 = 5
            java.lang.String r5 = r4.getString(r5)
            if (r5 != 0) goto L26
            java.lang.String r5 = r4.getString(r0)
        L26:
            r3.mSummaryOn = r5
            boolean r5 = r3.mChecked
            if (r5 == 0) goto L2f
            r3.notifyChanged()
        L2f:
            r5 = 4
            java.lang.String r5 = r4.getString(r5)
            if (r5 != 0) goto L3b
            r5 = 1
            java.lang.String r5 = r4.getString(r5)
        L3b:
            r3.mSummaryOff = r5
            boolean r5 = r3.mChecked
            if (r5 != 0) goto L44
            r3.notifyChanged()
        L44:
            r5 = 2
            boolean r5 = r4.getBoolean(r5, r0)
            r0 = 3
            boolean r5 = r4.getBoolean(r0, r5)
            r3.mDisableDependentsState = r5
            r4.recycle()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.preference.CheckBoxPreference.<init>(android.content.Context, android.util.AttributeSet):void");
    }

    @Override // androidx.preference.Preference
    public void onBindViewHolder(PreferenceViewHolder preferenceViewHolder) {
        super.onBindViewHolder(preferenceViewHolder);
        syncCheckboxView(preferenceViewHolder.findViewById(R.id.checkbox));
        syncSummaryView(preferenceViewHolder.findViewById(R.id.summary));
    }

    @Override // androidx.preference.Preference
    public final void performClick(View view) {
        performClick();
        if (((AccessibilityManager) this.mContext.getSystemService("accessibility")).isEnabled()) {
            syncCheckboxView(view.findViewById(R.id.checkbox));
            syncSummaryView(view.findViewById(R.id.summary));
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void syncCheckboxView(View view) {
        boolean z = view instanceof CompoundButton;
        if (z) {
            ((CompoundButton) view).setOnCheckedChangeListener(null);
        }
        if (view instanceof Checkable) {
            ((Checkable) view).setChecked(this.mChecked);
        }
        if (z) {
            ((CompoundButton) view).setOnCheckedChangeListener(this.mListener);
        }
    }
}
