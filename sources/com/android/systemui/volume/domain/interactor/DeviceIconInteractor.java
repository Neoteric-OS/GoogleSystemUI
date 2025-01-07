package com.android.systemui.volume.domain.interactor;

import android.content.Context;
import com.android.settingslib.media.DeviceIconUtil;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class DeviceIconInteractor {
    public final Context context;
    public final DeviceIconUtil iconUtil;

    public DeviceIconInteractor(Context context) {
        this.context = context;
        this.iconUtil = new DeviceIconUtil(context);
    }
}
