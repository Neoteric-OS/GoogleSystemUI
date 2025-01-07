package com.google.android.systemui.assist;

import android.content.Context;
import android.os.ServiceManager;
import com.android.internal.widget.ILockSettings;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class OpaEnabledSettings {
    public final Context mContext;
    public final ILockSettings mLockSettings = ILockSettings.Stub.asInterface(ServiceManager.getService("lock_settings"));

    public OpaEnabledSettings(Context context) {
        this.mContext = context;
    }
}
