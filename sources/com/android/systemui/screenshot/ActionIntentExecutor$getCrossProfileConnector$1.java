package com.android.systemui.screenshot;

import android.os.IBinder;
import android.os.IInterface;
import java.util.function.Function;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class ActionIntentExecutor$getCrossProfileConnector$1 implements Function {
    public static final ActionIntentExecutor$getCrossProfileConnector$1 INSTANCE = new ActionIntentExecutor$getCrossProfileConnector$1();

    @Override // java.util.function.Function
    public final Object apply(Object obj) {
        IBinder iBinder = (IBinder) obj;
        int i = ScreenshotCrossProfileService$mBinder$1.$r8$clinit;
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.android.systemui.screenshot.ICrossProfileService");
        if (queryLocalInterface != null && (queryLocalInterface instanceof ICrossProfileService)) {
            return (ICrossProfileService) queryLocalInterface;
        }
        ICrossProfileService$Stub$Proxy iCrossProfileService$Stub$Proxy = new ICrossProfileService$Stub$Proxy();
        iCrossProfileService$Stub$Proxy.mRemote = iBinder;
        return iCrossProfileService$Stub$Proxy;
    }
}
