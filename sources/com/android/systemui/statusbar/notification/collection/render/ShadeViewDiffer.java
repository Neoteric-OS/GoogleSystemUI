package com.android.systemui.statusbar.notification.collection.render;

import android.os.Trace;
import android.view.View;
import androidx.appsearch.app.DocumentClassFactoryRegistry$$ExternalSyntheticOutline0;
import androidx.room.TriggerBasedInvalidationTracker$$ExternalSyntheticOutline0;
import com.android.app.tracing.TraceUtilsKt;
import com.android.systemui.log.LogBuffer;
import com.android.systemui.log.LogMessageImpl;
import com.android.systemui.log.core.LogLevel;
import com.android.systemui.log.core.LogMessage;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Pair;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.collections.MapsKt;
import kotlin.collections.MapsKt__MapsJVMKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class ShadeViewDiffer {
    public final ShadeViewDifferLogger logger;
    public final Map nodes;
    public final ShadeNode rootNode;

    public ShadeViewDiffer(RootNodeController rootNodeController, ShadeViewDifferLogger shadeViewDifferLogger) {
        this.logger = shadeViewDifferLogger;
        ShadeNode shadeNode = new ShadeNode(rootNodeController);
        this.rootNode = shadeNode;
        this.nodes = MapsKt.mutableMapOf(new Pair(rootNodeController, shadeNode));
    }

    public static final void detachChildren$lambda$4$detachRecursively(Map map, ShadeViewDiffer shadeViewDiffer, ShadeNode shadeNode, Map map2) {
        boolean z;
        NodeSpecImpl nodeSpecImpl;
        ShadeNode shadeNode2 = shadeNode;
        NodeController nodeController = shadeNode2.controller;
        NodeSpecImpl nodeSpecImpl2 = (NodeSpecImpl) map2.get(nodeController);
        boolean z2 = true;
        int childCount = nodeController.getChildCount() - 1;
        while (-1 < childCount) {
            ShadeNode shadeNode3 = (ShadeNode) map.get(nodeController.getChildAt(childCount));
            if (shadeNode3 != null) {
                NodeController nodeController2 = shadeNode3.controller;
                NodeSpecImpl nodeSpecImpl3 = (NodeSpecImpl) map2.get(nodeController2);
                shadeViewDiffer.getClass();
                ShadeNode node = (nodeSpecImpl3 == null || (nodeSpecImpl = nodeSpecImpl3.parent) == null) ? null : shadeViewDiffer.getNode(nodeSpecImpl);
                if (Intrinsics.areEqual(node, shadeNode2)) {
                    z = z2;
                } else {
                    boolean z3 = node == null ? z2 : false;
                    if (z3) {
                        shadeViewDiffer.nodes.remove(nodeController2);
                    }
                    ShadeViewDifferLogger shadeViewDifferLogger = shadeViewDiffer.logger;
                    if (z3 && nodeSpecImpl2 == null && nodeController2.offerToKeepInParentForAnimation()) {
                        String nodeLabel = nodeController2.getNodeLabel();
                        String nodeLabel2 = nodeController.getNodeLabel();
                        LogLevel logLevel = LogLevel.DEBUG;
                        ShadeViewDifferLogger$logSkipDetachingChild$2 shadeViewDifferLogger$logSkipDetachingChild$2 = new Function1() { // from class: com.android.systemui.statusbar.notification.collection.render.ShadeViewDifferLogger$logSkipDetachingChild$2
                            @Override // kotlin.jvm.functions.Function1
                            public final Object invoke(Object obj) {
                                LogMessage logMessage = (LogMessage) obj;
                                String str1 = logMessage.getStr1();
                                String str2 = logMessage.getStr2();
                                boolean bool1 = logMessage.getBool1();
                                boolean bool2 = logMessage.getBool2();
                                StringBuilder m = TriggerBasedInvalidationTracker$$ExternalSyntheticOutline0.m("Skip detaching ", str1, " from ", str2, " isTransfer=");
                                m.append(bool1);
                                m.append(" isParentRemoved=");
                                m.append(bool2);
                                return m.toString();
                            }
                        };
                        LogBuffer logBuffer = shadeViewDifferLogger.buffer;
                        LogMessage obtain = logBuffer.obtain("NotifViewManager", logLevel, shadeViewDifferLogger$logSkipDetachingChild$2, null);
                        LogMessageImpl logMessageImpl = (LogMessageImpl) obtain;
                        logMessageImpl.str1 = nodeLabel;
                        logMessageImpl.str2 = nodeLabel2;
                        logMessageImpl.bool1 = !z3;
                        z = true;
                        logMessageImpl.bool2 = true;
                        logBuffer.commit(obtain);
                    } else {
                        z = z2;
                        boolean z4 = !z3;
                        shadeViewDifferLogger.logDetachingChild(nodeController2.getNodeLabel(), nodeController.getNodeLabel(), node != null ? node.controller.getNodeLabel() : null, z4, nodeSpecImpl2 == null ? z : false);
                        boolean isEnabled = Trace.isEnabled();
                        if (isEnabled) {
                            TraceUtilsKt.beginSlice("ShadeNode#removeChild");
                        }
                        try {
                            nodeController.removeChild(nodeController2, z4);
                            if (isEnabled) {
                                TraceUtilsKt.endSlice();
                            }
                            shadeNode3.parent = null;
                        } finally {
                        }
                    }
                }
                if (nodeController2.getChildCount() > 0) {
                    detachChildren$lambda$4$detachRecursively(map, shadeViewDiffer, shadeNode3, map2);
                }
            } else {
                z = z2;
            }
            childCount--;
            shadeNode2 = shadeNode;
            z2 = z;
        }
    }

    public static void registerNodes(NodeSpecImpl nodeSpecImpl, Map map) {
        if (map.containsKey(nodeSpecImpl.controller)) {
            throw new DuplicateNodeException(DocumentClassFactoryRegistry$$ExternalSyntheticOutline0.m("Node ", nodeSpecImpl.controller.getNodeLabel(), " appears more than once"));
        }
        map.put(nodeSpecImpl.controller, nodeSpecImpl);
        if (nodeSpecImpl.children.isEmpty()) {
            return;
        }
        Iterator it = nodeSpecImpl.children.iterator();
        while (it.hasNext()) {
            registerNodes((NodeSpecImpl) it.next(), map);
        }
    }

    public final void applySpec(NodeSpecImpl nodeSpecImpl) {
        NodeController nodeController = nodeSpecImpl.controller;
        ShadeNode shadeNode = this.rootNode;
        NodeController nodeController2 = shadeNode.controller;
        boolean isEnabled = Trace.isEnabled();
        if (isEnabled) {
            TraceUtilsKt.beginSlice("ShadeViewDiffer.applySpec");
        }
        try {
            Map treeToMap = treeToMap(nodeSpecImpl);
            if (Intrinsics.areEqual(nodeController, nodeController2)) {
                detachChildren(shadeNode, treeToMap);
                attachChildren(shadeNode, treeToMap);
            } else {
                throw new IllegalArgumentException("Tree root " + nodeController.getNodeLabel() + " does not match own root at " + nodeController2.getNodeLabel());
            }
        } finally {
            if (isEnabled) {
                TraceUtilsKt.endSlice();
            }
        }
    }

    public final void attachChildren(ShadeNode shadeNode, Map map) {
        Iterator it;
        int i;
        ShadeViewDifferLogger shadeViewDifferLogger;
        boolean isEnabled;
        boolean isEnabled2 = Trace.isEnabled();
        if (isEnabled2) {
            TraceUtilsKt.beginSlice("attachChildren");
        }
        try {
            NodeController nodeController = shadeNode.controller;
            Object obj = map.get(nodeController);
            if (obj == null) {
                throw new IllegalStateException("Required value was null.");
            }
            Iterator it2 = ((NodeSpecImpl) obj).children.iterator();
            int i2 = 0;
            while (it2.hasNext()) {
                int i3 = i2 + 1;
                NodeSpecImpl nodeSpecImpl = (NodeSpecImpl) it2.next();
                View childAt = nodeController.getChildAt(i2);
                ShadeNode node = getNode(nodeSpecImpl);
                NodeController nodeController2 = node.controller;
                if (Intrinsics.areEqual(nodeController2.getView(), childAt)) {
                    it = it2;
                    i = i3;
                } else {
                    boolean removeFromParentIfKeptForAnimation = nodeController2.removeFromParentIfKeptForAnimation();
                    ShadeViewDifferLogger shadeViewDifferLogger2 = this.logger;
                    if (removeFromParentIfKeptForAnimation) {
                        shadeViewDifferLogger = shadeViewDifferLogger2;
                        shadeViewDifferLogger2.logDetachingChild(nodeController2.getNodeLabel(), null, null, false, true);
                    } else {
                        shadeViewDifferLogger = shadeViewDifferLogger2;
                    }
                    ShadeNode shadeNode2 = node.parent;
                    LogBuffer logBuffer = shadeViewDifferLogger.buffer;
                    if (shadeNode2 == null) {
                        String nodeLabel = nodeController2.getNodeLabel();
                        String nodeLabel2 = nodeController.getNodeLabel();
                        it = it2;
                        i = i3;
                        LogMessage obtain = logBuffer.obtain("NotifViewManager", LogLevel.DEBUG, new Function1() { // from class: com.android.systemui.statusbar.notification.collection.render.ShadeViewDifferLogger$logAttachingChild$2
                            @Override // kotlin.jvm.functions.Function1
                            public final Object invoke(Object obj2) {
                                LogMessage logMessage = (LogMessage) obj2;
                                String str1 = logMessage.getStr1();
                                String str2 = logMessage.getStr2();
                                int int1 = logMessage.getInt1();
                                StringBuilder m = TriggerBasedInvalidationTracker$$ExternalSyntheticOutline0.m("Attaching view ", str1, " to ", str2, " at index ");
                                m.append(int1);
                                return m.toString();
                            }
                        }, null);
                        ((LogMessageImpl) obtain).str1 = nodeLabel;
                        LogMessageImpl logMessageImpl = (LogMessageImpl) obtain;
                        logMessageImpl.str2 = nodeLabel2;
                        logMessageImpl.int1 = i2;
                        logBuffer.commit(obtain);
                        isEnabled = Trace.isEnabled();
                        if (isEnabled) {
                            TraceUtilsKt.beginSlice("ShadeNode#addChildAt");
                        }
                        try {
                            nodeController.addChildAt(nodeController2, i2);
                            nodeController2.onViewAdded();
                            if (isEnabled) {
                                TraceUtilsKt.endSlice();
                            }
                            node.parent = shadeNode;
                        } finally {
                        }
                    } else {
                        it = it2;
                        i = i3;
                        if (!shadeNode2.equals(shadeNode)) {
                            String nodeLabel3 = nodeController2.getNodeLabel();
                            String nodeLabel4 = nodeController.getNodeLabel();
                            ShadeNode shadeNode3 = node.parent;
                            throw new IllegalStateException("Child " + nodeLabel3 + " should have parent " + nodeLabel4 + " but is actually " + (shadeNode3 != null ? shadeNode3.controller.getNodeLabel() : null));
                        }
                        String nodeLabel5 = nodeController2.getNodeLabel();
                        String nodeLabel6 = nodeController.getNodeLabel();
                        LogMessage obtain2 = logBuffer.obtain("NotifViewManager", LogLevel.DEBUG, new Function1() { // from class: com.android.systemui.statusbar.notification.collection.render.ShadeViewDifferLogger$logMovingChild$2
                            @Override // kotlin.jvm.functions.Function1
                            public final Object invoke(Object obj2) {
                                LogMessage logMessage = (LogMessage) obj2;
                                String str1 = logMessage.getStr1();
                                String str2 = logMessage.getStr2();
                                int int1 = logMessage.getInt1();
                                StringBuilder m = TriggerBasedInvalidationTracker$$ExternalSyntheticOutline0.m("Moving child view ", str1, " in ", str2, " to index ");
                                m.append(int1);
                                return m.toString();
                            }
                        }, null);
                        ((LogMessageImpl) obtain2).str1 = nodeLabel5;
                        LogMessageImpl logMessageImpl2 = (LogMessageImpl) obtain2;
                        logMessageImpl2.str2 = nodeLabel6;
                        logMessageImpl2.int1 = i2;
                        logBuffer.commit(obtain2);
                        isEnabled = Trace.isEnabled();
                        if (isEnabled) {
                            TraceUtilsKt.beginSlice("ShadeNode#moveChildTo");
                        }
                        try {
                            nodeController.moveChildTo(nodeController2, i2);
                            if (isEnabled) {
                                TraceUtilsKt.endSlice();
                            }
                        } finally {
                            if (!isEnabled) {
                                throw th;
                            }
                        }
                    }
                }
                nodeController2.resetKeepInParentForAnimation();
                if (!nodeSpecImpl.children.isEmpty()) {
                    attachChildren(node, map);
                }
                it2 = it;
                i2 = i;
            }
            if (isEnabled2) {
                TraceUtilsKt.endSlice();
            }
        } catch (Throwable th) {
            if (isEnabled2) {
                TraceUtilsKt.endSlice();
            }
            throw th;
        }
    }

    public final void detachChildren(ShadeNode shadeNode, Map map) {
        boolean isEnabled = Trace.isEnabled();
        if (isEnabled) {
            TraceUtilsKt.beginSlice("detachChildren");
        }
        try {
            Collection values = this.nodes.values();
            int mapCapacity = MapsKt__MapsJVMKt.mapCapacity(CollectionsKt__IterablesKt.collectionSizeOrDefault(values, 10));
            if (mapCapacity < 16) {
                mapCapacity = 16;
            }
            LinkedHashMap linkedHashMap = new LinkedHashMap(mapCapacity);
            for (Object obj : values) {
                linkedHashMap.put(((ShadeNode) obj).controller.getView(), obj);
            }
            detachChildren$lambda$4$detachRecursively(linkedHashMap, this, shadeNode, map);
            if (isEnabled) {
                TraceUtilsKt.endSlice();
            }
        } catch (Throwable th) {
            if (isEnabled) {
                TraceUtilsKt.endSlice();
            }
            throw th;
        }
    }

    public final ShadeNode getNode(NodeSpecImpl nodeSpecImpl) {
        ShadeNode shadeNode = (ShadeNode) this.nodes.get(nodeSpecImpl.controller);
        if (shadeNode != null) {
            return shadeNode;
        }
        NodeController nodeController = nodeSpecImpl.controller;
        ShadeNode shadeNode2 = new ShadeNode(nodeController);
        this.nodes.put(nodeController, shadeNode2);
        return shadeNode2;
    }

    public final Map treeToMap(NodeSpecImpl nodeSpecImpl) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        try {
            registerNodes(nodeSpecImpl, linkedHashMap);
            return linkedHashMap;
        } catch (DuplicateNodeException e) {
            ShadeViewDifferLogger shadeViewDifferLogger = this.logger;
            LogLevel logLevel = LogLevel.ERROR;
            ShadeViewDifferLogger$logDuplicateNodeInTree$2 shadeViewDifferLogger$logDuplicateNodeInTree$2 = new Function1() { // from class: com.android.systemui.statusbar.notification.collection.render.ShadeViewDifferLogger$logDuplicateNodeInTree$2
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    LogMessage logMessage = (LogMessage) obj;
                    return DocumentClassFactoryRegistry$$ExternalSyntheticOutline0.m$1(logMessage.getStr1(), " when mapping tree: ", logMessage.getStr2());
                }
            };
            LogBuffer logBuffer = shadeViewDifferLogger.buffer;
            LogMessage obtain = logBuffer.obtain("NotifViewManager", logLevel, shadeViewDifferLogger$logDuplicateNodeInTree$2, null);
            LogMessageImpl logMessageImpl = (LogMessageImpl) obtain;
            logMessageImpl.str1 = e.toString();
            StringBuilder sb = new StringBuilder();
            NodeControllerKt.treeSpecToStrHelper(nodeSpecImpl, sb, "");
            logMessageImpl.str2 = sb.toString();
            logBuffer.commit(obtain);
            throw e;
        }
    }
}
