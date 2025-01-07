package com.android.systemui.controls.ui;

import android.content.ContentProvider;
import android.graphics.drawable.Icon;
import android.net.Uri;
import kotlin.jvm.functions.Function1;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class CanUseIconPredicate implements Function1 {
    public final int currentUserId;

    public CanUseIconPredicate(int i) {
        this.currentUserId = i;
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object obj) {
        Icon icon = (Icon) obj;
        boolean z = true;
        if (icon.getType() == 4 || icon.getType() == 6) {
            Uri uri = icon.getUri();
            int i = this.currentUserId;
            if (ContentProvider.getUserIdFromUri(uri, i) != i) {
                z = false;
            }
        }
        return Boolean.valueOf(z);
    }
}
