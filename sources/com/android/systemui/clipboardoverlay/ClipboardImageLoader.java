package com.android.systemui.clipboardoverlay;

import android.content.Context;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class ClipboardImageLoader {
    public final CoroutineDispatcher bgDispatcher;
    public final Context context;
    public final CoroutineScope mainScope;

    public ClipboardImageLoader(Context context, CoroutineDispatcher coroutineDispatcher, CoroutineScope coroutineScope) {
        this.context = context;
        this.bgDispatcher = coroutineDispatcher;
        this.mainScope = coroutineScope;
    }
}
