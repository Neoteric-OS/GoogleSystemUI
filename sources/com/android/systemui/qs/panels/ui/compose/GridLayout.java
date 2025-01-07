package com.android.systemui.qs.panels.ui.compose;

import androidx.compose.runtime.Composer;
import androidx.compose.ui.Modifier;
import com.android.compose.animation.scene.ContentScope;
import java.util.List;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public interface GridLayout {
    void EditTileGrid(List list, Modifier modifier, Function2 function2, Function1 function1, Function1 function12, Composer composer, int i);

    void TileGrid(ContentScope contentScope, List list, Modifier modifier, Function0 function0, Composer composer, int i);
}
