package com.android.systemui.media.muteawait;

import android.content.Context;
import com.android.settingslib.media.DeviceIconUtil;
import java.util.concurrent.Executor;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class MediaMuteAwaitConnectionManagerFactory {
    public final Context context;
    public final DeviceIconUtil deviceIconUtil;
    public final MediaMuteAwaitLogger logger;
    public final Executor mainExecutor;

    public MediaMuteAwaitConnectionManagerFactory(Context context, MediaMuteAwaitLogger mediaMuteAwaitLogger, Executor executor) {
        this.context = context;
        this.logger = mediaMuteAwaitLogger;
        this.mainExecutor = executor;
        this.deviceIconUtil = new DeviceIconUtil(context);
    }
}
