package com.android.systemui.scene.data.repository;

import android.os.RemoteException;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class WindowRootViewVisibilityRepository$executeServiceCallOnUiBg$1 implements Runnable {
    public final /* synthetic */ Lambda $runnable;

    /* JADX WARN: Multi-variable type inference failed */
    public WindowRootViewVisibilityRepository$executeServiceCallOnUiBg$1(Function0 function0) {
        this.$runnable = (Lambda) function0;
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [kotlin.jvm.functions.Function0, kotlin.jvm.internal.Lambda] */
    @Override // java.lang.Runnable
    public final void run() {
        try {
            this.$runnable.invoke();
        } catch (RemoteException unused) {
        }
    }
}
