package com.google.android.systemui.columbus.legacy;

import android.content.ContentResolver;
import android.content.Context;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class ContentResolverWrapper {
    public final ContentResolver contentResolver;

    public ContentResolverWrapper(Context context) {
        this.contentResolver = context.getContentResolver();
    }
}
