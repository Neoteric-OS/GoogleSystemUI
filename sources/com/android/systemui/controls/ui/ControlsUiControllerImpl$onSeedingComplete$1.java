package com.android.systemui.controls.ui;

import android.view.ViewGroup;
import com.android.systemui.controls.controller.ControlsController;
import com.android.systemui.controls.controller.ControlsControllerImpl;
import com.android.systemui.controls.controller.Favorites;
import com.android.systemui.controls.controller.StructureInfo;
import com.android.systemui.controls.ui.SelectedItem;
import java.util.Iterator;
import java.util.function.Consumer;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class ControlsUiControllerImpl$onSeedingComplete$1 implements Consumer {
    public final /* synthetic */ ControlsUiControllerImpl this$0;

    public ControlsUiControllerImpl$onSeedingComplete$1(ControlsUiControllerImpl controlsUiControllerImpl) {
        this.this$0 = controlsUiControllerImpl;
    }

    @Override // java.util.function.Consumer
    public final void accept(Object obj) {
        Object next;
        Boolean bool = (Boolean) obj;
        Intrinsics.checkNotNull(bool);
        if (bool.booleanValue()) {
            ControlsUiControllerImpl controlsUiControllerImpl = this.this$0;
            ((ControlsControllerImpl) ((ControlsController) controlsUiControllerImpl.controlsController.get())).getClass();
            Iterator it = Favorites.getAllStructures().iterator();
            if (it.hasNext()) {
                next = it.next();
                if (it.hasNext()) {
                    int size = ((StructureInfo) next).controls.size();
                    do {
                        Object next2 = it.next();
                        int size2 = ((StructureInfo) next2).controls.size();
                        if (size < size2) {
                            next = next2;
                            size = size2;
                        }
                    } while (it.hasNext());
                }
            } else {
                next = null;
            }
            StructureInfo structureInfo = (StructureInfo) next;
            controlsUiControllerImpl.selectedItem = structureInfo != null ? new SelectedItem.StructureItem(structureInfo) : SelectedItem.EMPTY_SELECTION;
            ControlsUiControllerImpl controlsUiControllerImpl2 = this.this$0;
            controlsUiControllerImpl2.updatePreferences(controlsUiControllerImpl2.selectedItem);
        }
        ControlsUiControllerImpl controlsUiControllerImpl3 = this.this$0;
        ViewGroup viewGroup = controlsUiControllerImpl3.parent;
        ControlsUiControllerImpl.reload$default(controlsUiControllerImpl3, viewGroup != null ? viewGroup : null);
    }
}
