package androidx.preference;

import android.R;
import android.view.View;
import android.view.accessibility.AccessibilityManager;
import android.widget.Checkable;
import android.widget.CompoundButton;
import android.widget.Switch;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public class SwitchPreference extends TwoStatePreference {
    public final Listener mListener;
    public CharSequence mSwitchOff;
    public CharSequence mSwitchOn;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Listener implements CompoundButton.OnCheckedChangeListener {
        public Listener() {
        }

        @Override // android.widget.CompoundButton.OnCheckedChangeListener
        public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
            if (SwitchPreference.this.callChangeListener(Boolean.valueOf(z))) {
                SwitchPreference.this.setChecked(z);
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
    public SwitchPreference(android.content.Context r4, android.util.AttributeSet r5) {
        /*
            r3 = this;
            r0 = 0
            r1 = 2130970224(0x7f040670, float:1.7549152E38)
            r2 = 16843629(0x101036d, float:2.3696016E-38)
            int r1 = androidx.core.content.res.TypedArrayUtils.getAttr(r1, r2, r4)
            r3.<init>(r4, r5, r1, r0)
            androidx.preference.SwitchPreference$Listener r2 = new androidx.preference.SwitchPreference$Listener
            r2.<init>()
            r3.mListener = r2
            int[] r2 = androidx.preference.R$styleable.SwitchPreference
            android.content.res.TypedArray r4 = r4.obtainStyledAttributes(r5, r2, r1, r0)
            r5 = 7
            java.lang.String r5 = r4.getString(r5)
            if (r5 != 0) goto L26
            java.lang.String r5 = r4.getString(r0)
        L26:
            r3.mSummaryOn = r5
            boolean r5 = r3.mChecked
            if (r5 == 0) goto L2f
            r3.notifyChanged()
        L2f:
            r5 = 6
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
            r5 = 9
            java.lang.String r5 = r4.getString(r5)
            if (r5 != 0) goto L51
            r5 = 3
            java.lang.String r5 = r4.getString(r5)
        L51:
            r3.mSwitchOn = r5
            r3.notifyChanged()
            r5 = 8
            java.lang.String r5 = r4.getString(r5)
            if (r5 != 0) goto L63
            r5 = 4
            java.lang.String r5 = r4.getString(r5)
        L63:
            r3.mSwitchOff = r5
            r3.notifyChanged()
            r5 = 2
            boolean r5 = r4.getBoolean(r5, r0)
            r0 = 5
            boolean r5 = r4.getBoolean(r0, r5)
            r3.mDisableDependentsState = r5
            r4.recycle()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.preference.SwitchPreference.<init>(android.content.Context, android.util.AttributeSet):void");
    }

    @Override // androidx.preference.Preference
    public void onBindViewHolder(PreferenceViewHolder preferenceViewHolder) {
        super.onBindViewHolder(preferenceViewHolder);
        syncSwitchView(preferenceViewHolder.findViewById(R.id.switch_widget));
        syncSummaryView(preferenceViewHolder.findViewById(R.id.summary));
    }

    @Override // androidx.preference.Preference
    public final void performClick(View view) {
        performClick();
        if (((AccessibilityManager) this.mContext.getSystemService("accessibility")).isEnabled()) {
            syncSwitchView(view.findViewById(R.id.switch_widget));
            syncSummaryView(view.findViewById(R.id.summary));
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void syncSwitchView(View view) {
        boolean z = view instanceof Switch;
        if (z) {
            ((Switch) view).setOnCheckedChangeListener(null);
        }
        if (view instanceof Checkable) {
            ((Checkable) view).setChecked(this.mChecked);
        }
        if (z) {
            Switch r4 = (Switch) view;
            r4.setTextOn(this.mSwitchOn);
            r4.setTextOff(this.mSwitchOff);
            r4.setOnCheckedChangeListener(this.mListener);
        }
    }
}
