package androidx.compose.ui.platform.accessibility;

import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.semantics.CollectionInfo;
import androidx.compose.ui.semantics.CollectionItemInfo;
import androidx.compose.ui.semantics.SemanticsConfigurationKt;
import androidx.compose.ui.semantics.SemanticsNode;
import androidx.compose.ui.semantics.SemanticsProperties;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import java.util.ArrayList;
import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.EmptyList;
import kotlin.jvm.functions.Function0;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class CollectionInfo_androidKt {
    public static final boolean calculateIfHorizontallyStacked(List list) {
        List list2;
        long j;
        ArrayList arrayList = (ArrayList) list;
        if (arrayList.size() < 2) {
            return true;
        }
        if (arrayList.size() == 0 || arrayList.size() == 1) {
            list2 = EmptyList.INSTANCE;
        } else {
            list2 = new ArrayList();
            Object obj = arrayList.get(0);
            int lastIndex = CollectionsKt__CollectionsKt.getLastIndex(list);
            int i = 0;
            while (i < lastIndex) {
                i++;
                Object obj2 = arrayList.get(i);
                SemanticsNode semanticsNode = (SemanticsNode) obj2;
                SemanticsNode semanticsNode2 = (SemanticsNode) obj;
                list2.add(new Offset((Float.floatToRawIntBits(Math.abs(Float.intBitsToFloat((int) (semanticsNode2.getBoundsInRoot().m320getCenterF1C5BW0() >> 32)) - Float.intBitsToFloat((int) (semanticsNode.getBoundsInRoot().m320getCenterF1C5BW0() >> 32)))) << 32) | (Float.floatToRawIntBits(Math.abs(Float.intBitsToFloat((int) (semanticsNode2.getBoundsInRoot().m320getCenterF1C5BW0() & 4294967295L)) - Float.intBitsToFloat((int) (semanticsNode.getBoundsInRoot().m320getCenterF1C5BW0() & 4294967295L)))) & 4294967295L)));
                obj = obj2;
            }
        }
        if (list2.size() == 1) {
            j = ((Offset) CollectionsKt.first(list2)).packedValue;
        } else {
            if (list2.isEmpty()) {
                throw new UnsupportedOperationException("Empty collection can't be reduced.");
            }
            Object first = CollectionsKt.first(list2);
            int lastIndex2 = CollectionsKt__CollectionsKt.getLastIndex(list2);
            if (1 <= lastIndex2) {
                int i2 = 1;
                while (true) {
                    first = new Offset(Offset.m315plusMKHz9U(((Offset) first).packedValue, ((Offset) list2.get(i2)).packedValue));
                    if (i2 == lastIndex2) {
                        break;
                    }
                    i2++;
                }
            }
            j = ((Offset) first).packedValue;
        }
        return Float.intBitsToFloat((int) (j & 4294967295L)) < Float.intBitsToFloat((int) (j >> 32));
    }

    public static final void setCollectionItemInfo(SemanticsNode semanticsNode, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        CollectionItemInfo collectionItemInfo = (CollectionItemInfo) SemanticsConfigurationKt.getOrNull(semanticsNode.getConfig(), SemanticsProperties.CollectionItemInfo);
        if (collectionItemInfo != null) {
            accessibilityNodeInfoCompat.setCollectionItemInfo(AccessibilityNodeInfoCompat.CollectionItemInfoCompat.obtain(((Boolean) semanticsNode.getConfig().getOrElse(SemanticsProperties.Selected, new Function0() { // from class: androidx.compose.ui.platform.accessibility.CollectionInfo_androidKt$toAccessibilityCollectionItemInfo$1
                @Override // kotlin.jvm.functions.Function0
                public final /* bridge */ /* synthetic */ Object invoke() {
                    return Boolean.FALSE;
                }
            })).booleanValue(), collectionItemInfo.rowIndex, 1, collectionItemInfo.columnIndex, 1));
        }
        SemanticsNode parent = semanticsNode.getParent();
        if (parent == null || SemanticsConfigurationKt.getOrNull(parent.getConfig(), SemanticsProperties.SelectableGroup) == null) {
            return;
        }
        CollectionInfo collectionInfo = (CollectionInfo) SemanticsConfigurationKt.getOrNull(parent.getConfig(), SemanticsProperties.CollectionInfo);
        if (collectionInfo == null || (collectionInfo.rowCount >= 0 && collectionInfo.columnCount >= 0)) {
            if (semanticsNode.getConfig().props.containsKey(SemanticsProperties.Selected)) {
                ArrayList arrayList = new ArrayList();
                List children$ui_release$default = SemanticsNode.getChildren$ui_release$default(parent, 4);
                int size = children$ui_release$default.size();
                int i = 0;
                for (int i2 = 0; i2 < size; i2++) {
                    SemanticsNode semanticsNode2 = (SemanticsNode) children$ui_release$default.get(i2);
                    if (semanticsNode2.getConfig().props.containsKey(SemanticsProperties.Selected)) {
                        arrayList.add(semanticsNode2);
                        if (semanticsNode2.layoutNode.getPlaceOrder$ui_release() < semanticsNode.layoutNode.getPlaceOrder$ui_release()) {
                            i++;
                        }
                    }
                }
                if (arrayList.isEmpty()) {
                    return;
                }
                boolean calculateIfHorizontallyStacked = calculateIfHorizontallyStacked(arrayList);
                accessibilityNodeInfoCompat.setCollectionItemInfo(AccessibilityNodeInfoCompat.CollectionItemInfoCompat.obtain(((Boolean) semanticsNode.getConfig().getOrElse(SemanticsProperties.Selected, new Function0() { // from class: androidx.compose.ui.platform.accessibility.CollectionInfo_androidKt$setCollectionItemInfo$itemInfo$1
                    @Override // kotlin.jvm.functions.Function0
                    public final /* bridge */ /* synthetic */ Object invoke() {
                        return Boolean.FALSE;
                    }
                })).booleanValue(), calculateIfHorizontallyStacked ? 0 : i, 1, calculateIfHorizontallyStacked ? i : 0, 1));
            }
        }
    }
}
