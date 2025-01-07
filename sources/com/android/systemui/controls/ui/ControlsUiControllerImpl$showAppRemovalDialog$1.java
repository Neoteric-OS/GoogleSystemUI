package com.android.systemui.controls.ui;

import android.content.ComponentName;
import android.os.UserHandle;
import android.view.ViewGroup;
import com.android.systemui.controls.controller.ControlsController;
import com.android.systemui.controls.controller.ControlsControllerImpl;
import com.android.systemui.controls.controller.ControlsControllerImpl$removeFavorites$1;
import com.android.systemui.controls.controller.Favorites;
import com.android.systemui.controls.panels.SelectedComponentRepository$SelectedComponent;
import com.android.systemui.controls.panels.SelectedComponentRepositoryImpl;
import com.android.systemui.settings.UserTrackerImpl;
import com.android.systemui.util.concurrency.ExecutorImpl;
import java.util.function.Consumer;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class ControlsUiControllerImpl$showAppRemovalDialog$1 implements Consumer {
    public final /* synthetic */ ComponentName $componentName;
    public final /* synthetic */ ControlsUiControllerImpl this$0;

    public ControlsUiControllerImpl$showAppRemovalDialog$1(ControlsUiControllerImpl controlsUiControllerImpl, ComponentName componentName) {
        this.this$0 = controlsUiControllerImpl;
        this.$componentName = componentName;
    }

    @Override // java.util.function.Consumer
    public final void accept(Object obj) {
        SelectedComponentRepository$SelectedComponent selectedComponent;
        if (((Boolean) obj).booleanValue()) {
            ControlsController controlsController = (ControlsController) this.this$0.controlsController.get();
            ComponentName componentName = this.$componentName;
            ControlsControllerImpl controlsControllerImpl = (ControlsControllerImpl) controlsController;
            if (controlsControllerImpl.confirmAvailability()) {
                ((ExecutorImpl) controlsControllerImpl.executor).execute(new ControlsControllerImpl$removeFavorites$1(componentName, controlsControllerImpl, 0));
                selectedComponent = this.this$0.selectedComponentRepository.getSelectedComponent(UserHandle.CURRENT);
                if (Intrinsics.areEqual(selectedComponent != null ? selectedComponent.componentName : null, this.$componentName)) {
                    this.this$0.selectedComponentRepository.removeSelectedComponent();
                }
                ControlsUiControllerImpl controlsUiControllerImpl = this.this$0;
                ((ControlsControllerImpl) ((ControlsController) controlsUiControllerImpl.controlsController.get())).getClass();
                if (Intrinsics.areEqual(controlsUiControllerImpl.getPreferredSelectedItem(Favorites.getAllStructures()), SelectedItem.EMPTY_SELECTION)) {
                    SelectedComponentRepositoryImpl selectedComponentRepositoryImpl = this.this$0.selectedComponentRepository;
                    selectedComponentRepositoryImpl.getSharedPreferencesForUser(((UserTrackerImpl) selectedComponentRepositoryImpl.userTracker).getUserId()).edit().putBoolean("should_add_default_panel", false).apply();
                }
                ControlsUiControllerImpl controlsUiControllerImpl2 = this.this$0;
                ViewGroup viewGroup = controlsUiControllerImpl2.parent;
                ControlsUiControllerImpl.reload$default(controlsUiControllerImpl2, viewGroup != null ? viewGroup : null);
            }
        }
    }
}
