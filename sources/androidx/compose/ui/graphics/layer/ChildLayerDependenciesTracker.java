package androidx.compose.ui.graphics.layer;

import androidx.collection.MutableScatterSet;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class ChildLayerDependenciesTracker {
    public MutableScatterSet dependenciesSet;
    public GraphicsLayer dependency;
    public MutableScatterSet oldDependenciesSet;
    public GraphicsLayer oldDependency;
    public boolean trackingInProgress;
}
