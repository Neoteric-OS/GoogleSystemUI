package com.android.systemui.controls.management;

import android.content.res.Resources;
import kotlin.jvm.functions.Function1;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class FavoritesRenderer {
    public final Function1 favoriteFunction;
    public final Resources resources;

    public FavoritesRenderer(Resources resources, Function1 function1) {
        this.resources = resources;
        this.favoriteFunction = function1;
    }
}
