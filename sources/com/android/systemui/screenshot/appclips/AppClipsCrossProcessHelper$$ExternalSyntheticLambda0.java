package com.android.systemui.screenshot.appclips;

import android.os.IBinder;
import android.os.IInterface;
import com.android.systemui.screenshot.appclips.AppClipsScreenshotHelperService;
import java.util.function.Function;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class AppClipsCrossProcessHelper$$ExternalSyntheticLambda0 implements Function {
    @Override // java.util.function.Function
    public final Object apply(Object obj) {
        IBinder iBinder = (IBinder) obj;
        int i = AppClipsScreenshotHelperService.AnonymousClass1.$r8$clinit;
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.android.systemui.screenshot.appclips.IAppClipsScreenshotHelperService");
        if (queryLocalInterface != null && (queryLocalInterface instanceof IAppClipsScreenshotHelperService)) {
            return (IAppClipsScreenshotHelperService) queryLocalInterface;
        }
        IAppClipsScreenshotHelperService$Stub$Proxy iAppClipsScreenshotHelperService$Stub$Proxy = new IAppClipsScreenshotHelperService$Stub$Proxy();
        iAppClipsScreenshotHelperService$Stub$Proxy.mRemote = iBinder;
        return iAppClipsScreenshotHelperService$Stub$Proxy;
    }
}
