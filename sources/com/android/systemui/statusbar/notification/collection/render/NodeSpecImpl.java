package com.android.systemui.statusbar.notification.collection.render;

import java.util.ArrayList;
import java.util.List;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class NodeSpecImpl {
    public final List children = new ArrayList();
    public final NodeController controller;
    public final NodeSpecImpl parent;

    public NodeSpecImpl(NodeSpecImpl nodeSpecImpl, NodeController nodeController) {
        this.parent = nodeSpecImpl;
        this.controller = nodeController;
    }
}
