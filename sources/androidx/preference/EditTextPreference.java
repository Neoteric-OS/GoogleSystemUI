package androidx.preference;

import android.content.res.TypedArray;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.view.AbsSavedState;
import androidx.preference.Preference;
import com.android.wm.shell.R;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public class EditTextPreference extends DialogPreference {
    public String mText;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class SimpleSummaryProvider implements Preference.SummaryProvider {
        public static SimpleSummaryProvider sSimpleSummaryProvider;

        @Override // androidx.preference.Preference.SummaryProvider
        public final CharSequence provideSummary(Preference preference) {
            EditTextPreference editTextPreference = (EditTextPreference) preference;
            return TextUtils.isEmpty(editTextPreference.mText) ? editTextPreference.mContext.getString(R.string.not_set) : editTextPreference.mText;
        }
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public EditTextPreference(android.content.Context r4, android.util.AttributeSet r5) {
        /*
            r3 = this;
            r0 = 0
            r1 = 2130969096(0x7f040208, float:1.7546864E38)
            r2 = 16842898(0x1010092, float:2.3693967E-38)
            int r1 = androidx.core.content.res.TypedArrayUtils.getAttr(r1, r2, r4)
            r3.<init>(r4, r5, r1)
            int[] r2 = androidx.preference.R$styleable.EditTextPreference
            android.content.res.TypedArray r4 = r4.obtainStyledAttributes(r5, r2, r1, r0)
            boolean r5 = r4.getBoolean(r0, r0)
            boolean r5 = r4.getBoolean(r0, r5)
            if (r5 == 0) goto L30
            androidx.preference.EditTextPreference$SimpleSummaryProvider r5 = androidx.preference.EditTextPreference.SimpleSummaryProvider.sSimpleSummaryProvider
            if (r5 != 0) goto L29
            androidx.preference.EditTextPreference$SimpleSummaryProvider r5 = new androidx.preference.EditTextPreference$SimpleSummaryProvider
            r5.<init>()
            androidx.preference.EditTextPreference.SimpleSummaryProvider.sSimpleSummaryProvider = r5
        L29:
            androidx.preference.EditTextPreference$SimpleSummaryProvider r5 = androidx.preference.EditTextPreference.SimpleSummaryProvider.sSimpleSummaryProvider
            r3.mSummaryProvider = r5
            r3.notifyChanged()
        L30:
            r4.recycle()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.preference.EditTextPreference.<init>(android.content.Context, android.util.AttributeSet):void");
    }

    @Override // androidx.preference.Preference
    public final Object onGetDefaultValue(TypedArray typedArray, int i) {
        return typedArray.getString(i);
    }

    @Override // androidx.preference.Preference
    public final void onRestoreInstanceState(Parcelable parcelable) {
        if (!parcelable.getClass().equals(SavedState.class)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.getSuperState());
        setText(savedState.mText);
    }

    @Override // androidx.preference.Preference
    public final Parcelable onSaveInstanceState() {
        this.mBaseMethodCalled = true;
        AbsSavedState absSavedState = AbsSavedState.EMPTY_STATE;
        if (this.mPersistent) {
            return absSavedState;
        }
        SavedState savedState = new SavedState(absSavedState);
        savedState.mText = this.mText;
        return savedState;
    }

    @Override // androidx.preference.Preference
    public final void onSetInitialValue(Object obj) {
        setText(getPersistedString((String) obj));
    }

    public final void setText(String str) {
        boolean shouldDisableDependents = shouldDisableDependents();
        this.mText = str;
        persistString(str);
        boolean shouldDisableDependents2 = shouldDisableDependents();
        if (shouldDisableDependents2 != shouldDisableDependents) {
            notifyDependencyChange(shouldDisableDependents2);
        }
        notifyChanged();
    }

    @Override // androidx.preference.Preference
    public final boolean shouldDisableDependents() {
        return TextUtils.isEmpty(this.mText) || super.shouldDisableDependents();
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class SavedState extends Preference.BaseSavedState {
        public static final Parcelable.Creator CREATOR = new AnonymousClass1();
        public String mText;

        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        /* renamed from: androidx.preference.EditTextPreference$SavedState$1, reason: invalid class name */
        public final class AnonymousClass1 implements Parcelable.Creator {
            @Override // android.os.Parcelable.Creator
            public final Object createFromParcel(Parcel parcel) {
                return new SavedState(parcel);
            }

            @Override // android.os.Parcelable.Creator
            public final Object[] newArray(int i) {
                return new SavedState[i];
            }
        }

        public SavedState(Parcel parcel) {
            super(parcel);
            this.mText = parcel.readString();
        }

        @Override // android.view.AbsSavedState, android.os.Parcelable
        public final void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeString(this.mText);
        }

        public SavedState(Parcelable parcelable) {
            super(parcelable);
        }
    }
}
