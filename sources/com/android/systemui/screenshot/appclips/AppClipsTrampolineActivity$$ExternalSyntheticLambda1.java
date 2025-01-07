package com.android.systemui.screenshot.appclips;

import android.os.IBinder;
import com.android.internal.statusbar.IAppClipsService;
import java.util.function.Function;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class AppClipsTrampolineActivity$$ExternalSyntheticLambda1 implements Function {
    @Override // java.util.function.Function
    public final Object apply(Object obj) {
        return IAppClipsService.Stub.asInterface((IBinder) obj);
    }
}
