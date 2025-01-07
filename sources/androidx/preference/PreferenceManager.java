package androidx.preference;

import android.content.Context;
import android.content.SharedPreferences;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class PreferenceManager {
    public final Context mContext;
    public SharedPreferences.Editor mEditor;
    public boolean mNoCommit;
    public PreferenceFragment mOnDisplayPreferenceDialogListener;
    public PreferenceFragment mOnNavigateToScreenListener;
    public PreferenceFragment mOnPreferenceTreeClickListener;
    public PreferenceScreen mPreferenceScreen;
    public final String mSharedPreferencesName;
    public long mNextId = 0;
    public SharedPreferences mSharedPreferences = null;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public interface OnDisplayPreferenceDialogListener {
        void onDisplayPreferenceDialog(DialogPreference dialogPreference);
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public interface OnPreferenceTreeClickListener {
        boolean onPreferenceTreeClick(Preference preference);
    }

    public PreferenceManager(Context context) {
        this.mContext = context;
        this.mSharedPreferencesName = getDefaultSharedPreferencesName(context);
    }

    public static String getDefaultSharedPreferencesName(Context context) {
        return context.getPackageName() + "_preferences";
    }

    public final SharedPreferences.Editor getEditor() {
        if (!this.mNoCommit) {
            return getSharedPreferences().edit();
        }
        if (this.mEditor == null) {
            this.mEditor = getSharedPreferences().edit();
        }
        return this.mEditor;
    }

    public final SharedPreferences getSharedPreferences() {
        if (this.mSharedPreferences == null) {
            this.mSharedPreferences = this.mContext.getSharedPreferences(this.mSharedPreferencesName, 0);
        }
        return this.mSharedPreferences;
    }
}
