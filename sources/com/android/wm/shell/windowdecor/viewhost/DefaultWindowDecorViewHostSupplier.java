package com.android.wm.shell.windowdecor.viewhost;

import kotlinx.coroutines.CoroutineScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class DefaultWindowDecorViewHostSupplier implements WindowDecorViewHostSupplier {
    public final CoroutineScope mainScope;

    public DefaultWindowDecorViewHostSupplier(CoroutineScope coroutineScope) {
        this.mainScope = coroutineScope;
    }
}
