package androidx.compose.animation.graphics.vector;

import androidx.compose.animation.core.Transition;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.State;
import java.util.ArrayList;
import java.util.List;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class PropertyValues {
    public final List timestamps = new ArrayList();

    public abstract State createState(Transition transition, String str, int i, Composer composer, int i2);
}
