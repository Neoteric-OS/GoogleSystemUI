package com.android.systemui.volume.panel.component.selector.ui.composable;

import androidx.compose.runtime.internal.ComposableLambdaImpl;
import kotlin.jvm.functions.Function0;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class Item {
    public final String contentDescription;
    public final ComposableLambdaImpl icon;
    public final ComposableLambdaImpl label;
    public final Function0 onItemSelected;

    public Item(Function0 function0, ComposableLambdaImpl composableLambdaImpl, ComposableLambdaImpl composableLambdaImpl2, String str) {
        this.onItemSelected = function0;
        this.icon = composableLambdaImpl;
        this.label = composableLambdaImpl2;
        this.contentDescription = str;
    }
}
