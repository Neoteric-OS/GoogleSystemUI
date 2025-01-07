package com.android.systemui.statusbar.notification.collection.render;

import androidx.appsearch.app.DocumentClassFactoryRegistry$$ExternalSyntheticOutline0;
import java.util.Iterator;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class NodeControllerKt {
    public static final void treeSpecToStrHelper(NodeSpecImpl nodeSpecImpl, StringBuilder sb, String str) {
        sb.append(str + "{" + nodeSpecImpl.controller.getNodeLabel() + "}\n");
        if (nodeSpecImpl.children.isEmpty()) {
            return;
        }
        String m = DocumentClassFactoryRegistry$$ExternalSyntheticOutline0.m(str, "  ");
        Iterator it = nodeSpecImpl.children.iterator();
        while (it.hasNext()) {
            treeSpecToStrHelper((NodeSpecImpl) it.next(), sb, m);
        }
    }
}
