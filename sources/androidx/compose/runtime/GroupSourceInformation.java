package androidx.compose.runtime;

import java.util.ArrayList;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class GroupSourceInformation {
    public ArrayList groups;

    public abstract boolean hasAnchor(Anchor anchor);

    public abstract GroupSourceInformation openInformation();

    public abstract boolean removeAnchor(Anchor anchor);
}
