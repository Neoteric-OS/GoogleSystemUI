package com.android.systemui.qs;

import android.database.ContentObserver;
import android.os.Handler;
import com.android.systemui.util.settings.UserSettingsProxy;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class UserSettingObserver extends ContentObserver {
    public boolean mListening;
    public int mObservedValue;
    public final String mSettingName;
    public final UserSettingsProxy mSettingsProxy;
    public int mUserId;

    public UserSettingObserver(UserSettingsProxy userSettingsProxy, Handler handler, String str, int i) {
        super(handler);
        this.mSettingsProxy = userSettingsProxy;
        this.mSettingName = str;
        this.mObservedValue = 0;
        this.mUserId = i;
    }

    public final int getValue() {
        return this.mListening ? this.mObservedValue : this.mSettingsProxy.getIntForUser(this.mSettingName, 0, this.mUserId);
    }

    public abstract void handleValueChanged(int i);

    @Override // android.database.ContentObserver
    public final void onChange(boolean z) {
        int intForUser = this.mSettingsProxy.getIntForUser(this.mSettingName, 0, this.mUserId);
        this.mObservedValue = intForUser;
        handleValueChanged(intForUser);
    }

    public final void setListening(boolean z) {
        if (z == this.mListening) {
            return;
        }
        this.mListening = z;
        if (!z) {
            this.mSettingsProxy.unregisterContentObserverAsync(this);
            this.mObservedValue = 0;
        } else {
            this.mObservedValue = this.mSettingsProxy.getIntForUser(this.mSettingName, 0, this.mUserId);
            UserSettingsProxy userSettingsProxy = this.mSettingsProxy;
            userSettingsProxy.registerContentObserverForUserAsync(userSettingsProxy.getUriFor(this.mSettingName), this, this.mUserId, new Runnable() { // from class: com.android.systemui.qs.UserSettingObserver$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    UserSettingObserver userSettingObserver = UserSettingObserver.this;
                    userSettingObserver.mObservedValue = userSettingObserver.mSettingsProxy.getIntForUser(userSettingObserver.mSettingName, 0, userSettingObserver.mUserId);
                }
            });
        }
    }

    public final void setUserId(int i) {
        this.mUserId = i;
        if (this.mListening) {
            setListening(false);
            setListening(true);
        }
    }
}
