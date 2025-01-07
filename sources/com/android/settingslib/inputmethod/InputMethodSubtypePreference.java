package com.android.settingslib.inputmethod;

import android.content.Context;
import android.text.TextUtils;
import java.util.Locale;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public class InputMethodSubtypePreference extends SwitchWithNoTextPreference {
    public InputMethodSubtypePreference(Context context, String str, CharSequence charSequence, Locale locale, Locale locale2) {
        super(context, null);
        this.mSwitchOn = "";
        notifyChanged();
        this.mSwitchOff = "";
        notifyChanged();
        this.mPersistent = false;
        setKey(str);
        setTitle(charSequence);
        if (locale == null || locale.equals(locale2)) {
            return;
        }
        TextUtils.equals(locale.getLanguage(), locale2.getLanguage());
    }
}
