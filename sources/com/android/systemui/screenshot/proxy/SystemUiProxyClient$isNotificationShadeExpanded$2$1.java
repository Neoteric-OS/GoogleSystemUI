package com.android.systemui.screenshot.proxy;

import com.android.internal.infra.ServiceConnector;
import com.android.systemui.screenshot.IScreenshotProxy;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class SystemUiProxyClient$isNotificationShadeExpanded$2$1 implements ServiceConnector.Job {
    public static final SystemUiProxyClient$isNotificationShadeExpanded$2$1 INSTANCE = new SystemUiProxyClient$isNotificationShadeExpanded$2$1();

    public final Object run(Object obj) {
        return Boolean.valueOf(((IScreenshotProxy) obj).isNotificationShadeExpanded());
    }
}
