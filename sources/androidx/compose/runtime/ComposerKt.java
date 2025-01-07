package androidx.compose.runtime;

import androidx.appsearch.app.DocumentClassFactoryRegistry$$ExternalSyntheticOutline0;
import androidx.collection.MutableScatterSet;
import androidx.collection.ScatterSetKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.internal.RememberEventDispatcher;
import java.util.ArrayList;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class ComposerKt {
    public static final OpaqueKey invocation = new OpaqueKey("provider");
    public static final OpaqueKey provider = new OpaqueKey("provider");
    public static final OpaqueKey compositionLocalMap = new OpaqueKey("compositionLocalMap");
    public static final OpaqueKey providerMaps = new OpaqueKey("providers");
    public static final OpaqueKey reference = new OpaqueKey("reference");
    public static final ComposerKt$$ExternalSyntheticLambda0 InvalidationLocationAscending = new ComposerKt$$ExternalSyntheticLambda0();

    public static final void access$removeRange(int i, int i2, List list) {
        int findLocation = findLocation(i, list);
        if (findLocation < 0) {
            findLocation = -(findLocation + 1);
        }
        while (true) {
            ArrayList arrayList = (ArrayList) list;
            if (findLocation >= arrayList.size() || ((Invalidation) arrayList.get(findLocation)).location >= i2) {
                return;
            } else {
                list.remove(findLocation);
            }
        }
    }

    public static final void collectNodesFrom$lambda$10$collectFromGroup(SlotReader slotReader, List list, int i) {
        int[] iArr = slotReader.groups;
        if (SlotTableKt.access$isNode(iArr, i)) {
            list.add(slotReader.node(i));
            return;
        }
        int i2 = iArr[(i * 5) + 3] + i;
        for (int i3 = i + 1; i3 < i2; i3 += iArr[(i3 * 5) + 3]) {
            collectNodesFrom$lambda$10$collectFromGroup(slotReader, list, i3);
        }
    }

    public static final void composeImmediateRuntimeError(String str) {
        throw new ComposeRuntimeError(DocumentClassFactoryRegistry$$ExternalSyntheticOutline0.m("Compose Runtime internal error. Unexpected or incorrect use of the Compose internal runtime API (", str, "). Please report to Google or use https://goo.gle/compose-feedback"));
    }

    public static final Void composeRuntimeError(String str) {
        throw new ComposeRuntimeError(DocumentClassFactoryRegistry$$ExternalSyntheticOutline0.m("Compose Runtime internal error. Unexpected or incorrect use of the Compose internal runtime API (", str, "). Please report to Google or use https://goo.gle/compose-feedback"));
    }

    public static final void deactivateCurrentGroup(SlotWriter slotWriter, RememberEventDispatcher rememberEventDispatcher) {
        int i;
        int[] iArr = slotWriter.groups;
        int i2 = slotWriter.currentGroup;
        int dataIndex = slotWriter.dataIndex(iArr, slotWriter.groupIndexToAddress(slotWriter.groupSize(i2) + i2));
        for (int dataIndex2 = slotWriter.dataIndex(slotWriter.groups, slotWriter.groupIndexToAddress(slotWriter.currentGroup)); dataIndex2 < dataIndex; dataIndex2++) {
            Object obj = slotWriter.slots[slotWriter.dataIndexToDataAddress(dataIndex2)];
            int i3 = -1;
            if (obj instanceof ComposeNodeLifecycleCallback) {
                rememberEventDispatcher.recordLeaving((ComposeNodeLifecycleCallback) obj, slotWriter.getSlotsSize() - dataIndex2, -1, -1);
            } else if (obj instanceof RememberObserverHolder) {
                RememberObserverHolder rememberObserverHolder = (RememberObserverHolder) obj;
                RememberObserver rememberObserver = rememberObserverHolder.wrapped;
                if (!(rememberObserver instanceof ReusableRememberObserver)) {
                    removeData(slotWriter, dataIndex2, obj);
                    int slotsSize = slotWriter.getSlotsSize() - dataIndex2;
                    Anchor anchor = rememberObserverHolder.after;
                    if (anchor == null || !anchor.getValid()) {
                        i = -1;
                    } else {
                        i3 = slotWriter.anchorIndex(anchor);
                        i = slotWriter.getSlotsSize() - slotWriter.slotsEndAllIndex$runtime_release(i3);
                    }
                    rememberEventDispatcher.recordLeaving(rememberObserver, slotsSize, i3, i);
                }
            } else if (obj instanceof RecomposeScopeImpl) {
                removeData(slotWriter, dataIndex2, obj);
                ((RecomposeScopeImpl) obj).release();
            }
        }
    }

    public static final int findLocation(int i, List list) {
        ArrayList arrayList = (ArrayList) list;
        int size = arrayList.size() - 1;
        int i2 = 0;
        while (i2 <= size) {
            int i3 = (i2 + size) >>> 1;
            int compare = Intrinsics.compare(((Invalidation) arrayList.get(i3)).location, i);
            if (compare < 0) {
                i2 = i3 + 1;
            } else {
                if (compare <= 0) {
                    return i3;
                }
                size = i3 - 1;
            }
        }
        return -(i2 + 1);
    }

    public static final void removeCurrentGroup(SlotWriter slotWriter, RememberEventDispatcher rememberEventDispatcher) {
        int i;
        int[] iArr = slotWriter.groups;
        int i2 = slotWriter.currentGroup;
        int dataIndex = slotWriter.dataIndex(iArr, slotWriter.groupIndexToAddress(slotWriter.groupSize(i2) + i2));
        for (int dataIndex2 = slotWriter.dataIndex(slotWriter.groups, slotWriter.groupIndexToAddress(slotWriter.currentGroup)); dataIndex2 < dataIndex; dataIndex2++) {
            Object obj = slotWriter.slots[slotWriter.dataIndexToDataAddress(dataIndex2)];
            int i3 = -1;
            if (obj instanceof ComposeNodeLifecycleCallback) {
                int slotsSize = slotWriter.getSlotsSize() - dataIndex2;
                ComposeNodeLifecycleCallback composeNodeLifecycleCallback = (ComposeNodeLifecycleCallback) obj;
                MutableScatterSet mutableScatterSet = rememberEventDispatcher.releasing;
                if (mutableScatterSet == null) {
                    int i4 = ScatterSetKt.$r8$clinit;
                    mutableScatterSet = new MutableScatterSet();
                    rememberEventDispatcher.releasing = mutableScatterSet;
                }
                mutableScatterSet.plusAssign(composeNodeLifecycleCallback);
                rememberEventDispatcher.recordLeaving(composeNodeLifecycleCallback, slotsSize, -1, -1);
            }
            if (obj instanceof RememberObserverHolder) {
                int slotsSize2 = slotWriter.getSlotsSize() - dataIndex2;
                RememberObserverHolder rememberObserverHolder = (RememberObserverHolder) obj;
                Anchor anchor = rememberObserverHolder.after;
                if (anchor == null || !anchor.getValid()) {
                    i = -1;
                } else {
                    i3 = slotWriter.anchorIndex(anchor);
                    i = slotWriter.getSlotsSize() - slotWriter.slotsEndAllIndex$runtime_release(i3);
                }
                rememberEventDispatcher.recordLeaving(rememberObserverHolder.wrapped, slotsSize2, i3, i);
            }
            if (obj instanceof RecomposeScopeImpl) {
                ((RecomposeScopeImpl) obj).release();
            }
        }
        slotWriter.removeGroup();
    }

    public static final void removeData(SlotWriter slotWriter, int i, Object obj) {
        int dataIndexToDataAddress = slotWriter.dataIndexToDataAddress(i);
        Object[] objArr = slotWriter.slots;
        Object obj2 = objArr[dataIndexToDataAddress];
        objArr[dataIndexToDataAddress] = Composer.Companion.Empty;
        if (obj == obj2) {
            return;
        }
        composeImmediateRuntimeError("Slot table is out of sync (expected " + obj + ", got " + obj2 + ')');
    }

    public static final void runtimeCheck(boolean z) {
        if (z) {
            return;
        }
        composeImmediateRuntimeError("Check failed");
    }
}
