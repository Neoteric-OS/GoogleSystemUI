package com.android.systemui.screenshot.proxy;

import android.os.IBinder;
import android.os.IInterface;
import com.android.systemui.screenshot.IScreenshotProxy;
import com.android.systemui.screenshot.IScreenshotProxy$Stub$Proxy;
import com.android.systemui.screenshot.ScreenshotProxyService$mBinder$1;
import java.util.function.Function;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class SystemUiProxyClient$proxyConnector$1 implements Function {
    public static final SystemUiProxyClient$proxyConnector$1 INSTANCE = new SystemUiProxyClient$proxyConnector$1();

    @Override // java.util.function.Function
    public final Object apply(Object obj) {
        IBinder iBinder = (IBinder) obj;
        int i = ScreenshotProxyService$mBinder$1.$r8$clinit;
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.android.systemui.screenshot.IScreenshotProxy");
        if (queryLocalInterface != null && (queryLocalInterface instanceof IScreenshotProxy)) {
            return (IScreenshotProxy) queryLocalInterface;
        }
        IScreenshotProxy$Stub$Proxy iScreenshotProxy$Stub$Proxy = new IScreenshotProxy$Stub$Proxy();
        iScreenshotProxy$Stub$Proxy.mRemote = iBinder;
        return iScreenshotProxy$Stub$Proxy;
    }
}
