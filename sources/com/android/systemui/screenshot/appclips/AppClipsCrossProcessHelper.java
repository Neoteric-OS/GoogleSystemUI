package com.android.systemui.screenshot.appclips;

import android.content.Context;
import android.content.Intent;
import com.android.internal.infra.ServiceConnector;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class AppClipsCrossProcessHelper {
    public final ServiceConnector mProxyConnector;

    public AppClipsCrossProcessHelper(Context context) {
        this.mProxyConnector = new ServiceConnector.Impl(context, new Intent(context, (Class<?>) AppClipsScreenshotHelperService.class), 1073741857, 0, new AppClipsCrossProcessHelper$$ExternalSyntheticLambda0());
    }
}
