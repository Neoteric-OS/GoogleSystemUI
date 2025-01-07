package com.android.systemui.controls.management;

import android.content.ComponentName;
import android.graphics.drawable.Icon;
import com.android.systemui.controls.CustomIconCache;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.FunctionReferenceImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final /* synthetic */ class FavoritesModel$elements$1$1 extends FunctionReferenceImpl implements Function2 {
    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        Icon icon;
        String str = (String) obj2;
        CustomIconCache customIconCache = (CustomIconCache) this.receiver;
        if (!((ComponentName) obj).equals(customIconCache.currentComponent)) {
            return null;
        }
        synchronized (customIconCache.cache) {
            icon = (Icon) customIconCache.cache.get(str);
        }
        return icon;
    }
}
