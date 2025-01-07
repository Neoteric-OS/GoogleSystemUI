package androidx.compose.ui.node;

import androidx.compose.ui.Modifier;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class ModifierNodeElement implements Modifier.Element {
    public abstract Modifier.Node create();

    public abstract void update(Modifier.Node node);
}
