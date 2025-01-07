package com.android.systemui.screenshot.proxy;

import android.content.Context;
import android.content.Intent;
import com.android.internal.infra.ServiceConnector;
import com.android.systemui.screenshot.ScreenshotProxyService;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class SystemUiProxyClient {
    public final ServiceConnector proxyConnector;

    public SystemUiProxyClient(Context context) {
        this.proxyConnector = new ServiceConnector.Impl(context, new Intent(context, (Class<?>) ScreenshotProxyService.class), 1073741857, context.getUserId(), SystemUiProxyClient$proxyConnector$1.INSTANCE);
    }
}
