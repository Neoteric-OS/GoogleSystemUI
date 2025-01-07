package com.android.systemui.communal.ui.compose;

import android.content.ComponentName;
import android.os.UserHandle;
import androidx.compose.runtime.snapshots.SnapshotStateList;
import com.android.systemui.communal.domain.model.CommunalContentModel;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import kotlin.Pair;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class ContentListState {
    public final SnapshotStateList list;
    public final Function3 onAddWidget;
    public final Function3 onDeleteWidget;
    public final Function1 onReorderWidgets;

    public ContentListState(List list, Function3 function3, Function3 function32, Function1 function1) {
        this.onAddWidget = function3;
        this.onDeleteWidget = function32;
        this.onReorderWidgets = function1;
        SnapshotStateList snapshotStateList = new SnapshotStateList();
        snapshotStateList.addAll(list);
        this.list = snapshotStateList;
    }

    public final boolean isItemEditable(int i) {
        CommunalContentModel communalContentModel = (CommunalContentModel) this.list.get(i);
        communalContentModel.getClass();
        return communalContentModel instanceof CommunalContentModel.WidgetContent;
    }

    public final void onRemove(int i) {
        SnapshotStateList snapshotStateList = this.list;
        CommunalContentModel communalContentModel = (CommunalContentModel) snapshotStateList.get(i);
        communalContentModel.getClass();
        if (communalContentModel instanceof CommunalContentModel.WidgetContent) {
            CommunalContentModel.WidgetContent widgetContent = (CommunalContentModel.WidgetContent) snapshotStateList.get(i);
            snapshotStateList.remove(i);
            ((ContentListStateKt$rememberContentListState$1$2) this.onDeleteWidget).invoke(Integer.valueOf(widgetContent.getAppWidgetId()), widgetContent.getComponentName(), Integer.valueOf(widgetContent.getRank()));
        }
    }

    public final void onSaveList(ComponentName componentName, UserHandle userHandle, Integer num) {
        if (componentName != null && userHandle != null && num != null) {
            ((ContentListStateKt$rememberContentListState$1$1) this.onAddWidget).invoke(componentName, userHandle, num);
            return;
        }
        ArrayList arrayList = new ArrayList();
        ListIterator listIterator = this.list.listIterator();
        int i = 0;
        while (listIterator.hasNext()) {
            Object next = listIterator.next();
            int i2 = i + 1;
            if (i < 0) {
                CollectionsKt__CollectionsKt.throwIndexOverflow();
                throw null;
            }
            CommunalContentModel communalContentModel = (CommunalContentModel) next;
            Pair pair = communalContentModel instanceof CommunalContentModel.WidgetContent ? new Pair(Integer.valueOf(((CommunalContentModel.WidgetContent) communalContentModel).getAppWidgetId()), Integer.valueOf(i)) : null;
            if (pair != null) {
                arrayList.add(pair);
            }
            i = i2;
        }
        ((ContentListStateKt$rememberContentListState$1$3) this.onReorderWidgets).invoke(MapsKt.toMap(arrayList));
    }
}
