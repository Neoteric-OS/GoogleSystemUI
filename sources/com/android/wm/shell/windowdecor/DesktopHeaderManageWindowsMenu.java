package com.android.wm.shell.windowdecor;

import android.app.ActivityManager;
import android.content.Context;
import android.graphics.Point;
import android.graphics.Rect;
import android.view.SurfaceControl;
import android.view.SurfaceControlViewHost;
import android.view.WindowManager;
import android.view.WindowlessWindowManager;
import android.window.InputTransferToken;
import androidx.compose.ui.graphics.ColorKt;
import com.android.wm.shell.RootTaskDisplayAreaOrganizer;
import com.android.wm.shell.common.DisplayController;
import com.android.wm.shell.shared.desktopmode.ManageWindowsViewContainer;
import com.android.wm.shell.windowdecor.additionalviewcontainer.AdditionalViewHostViewContainer;
import com.android.wm.shell.windowdecor.common.DecorThemeUtil;
import java.util.List;
import java.util.function.Supplier;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class DesktopHeaderManageWindowsMenu extends ManageWindowsViewContainer {
    public final ActivityManager.RunningTaskInfo callerTaskInfo;
    public final DisplayController displayController;
    public AdditionalViewHostViewContainer menuViewContainer;
    public final RootTaskDisplayAreaOrganizer rootTdaOrganizer;
    public final Supplier surfaceControlBuilderSupplier;
    public final Supplier surfaceControlTransactionSupplier;

    public DesktopHeaderManageWindowsMenu(ActivityManager.RunningTaskInfo runningTaskInfo, DisplayController displayController, RootTaskDisplayAreaOrganizer rootTaskDisplayAreaOrganizer, Context context, Supplier supplier, Supplier supplier2, List list, DesktopModeWindowDecorViewModel$$ExternalSyntheticLambda13 desktopModeWindowDecorViewModel$$ExternalSyntheticLambda13, DesktopModeWindowDecoration$$ExternalSyntheticLambda3 desktopModeWindowDecoration$$ExternalSyntheticLambda3) {
        super(ColorKt.m373toArgb8_81llA(new DecorThemeUtil(context).getColorScheme(runningTaskInfo).background), context);
        this.callerTaskInfo = runningTaskInfo;
        this.displayController = displayController;
        this.rootTdaOrganizer = rootTaskDisplayAreaOrganizer;
        this.surfaceControlBuilderSupplier = supplier;
        this.surfaceControlTransactionSupplier = supplier2;
        show(list, desktopModeWindowDecorViewModel$$ExternalSyntheticLambda13, desktopModeWindowDecoration$$ExternalSyntheticLambda3);
    }

    @Override // com.android.wm.shell.shared.desktopmode.ManageWindowsViewContainer
    public final void addToContainer(ManageWindowsViewContainer.ManageWindowsView manageWindowsView) {
        Rect bounds = this.callerTaskInfo.getConfiguration().windowConfiguration.getBounds();
        Point point = new Point(bounds.left, bounds.top);
        SurfaceControl.Builder builder = (SurfaceControl.Builder) this.surfaceControlBuilderSupplier.get();
        this.rootTdaOrganizer.attachToDisplayArea(this.callerTaskInfo.displayId, builder);
        SurfaceControl build = builder.setName("Manage Windows Menu").setContainerLayer().build();
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams(manageWindowsView.menuWidth, manageWindowsView.menuHeight, 2, 8650760, -2);
        SurfaceControlViewHost surfaceControlViewHost = new SurfaceControlViewHost(this.context, this.displayController.getDisplay(this.callerTaskInfo.displayId), new WindowlessWindowManager(this.callerTaskInfo.configuration, build, (InputTransferToken) null), "MaximizeMenu");
        surfaceControlViewHost.setView(manageWindowsView.rootView, layoutParams);
        SurfaceControl.Transaction transaction = (SurfaceControl.Transaction) this.surfaceControlTransactionSupplier.get();
        transaction.setLayer(build, 70000).setPosition(build, point.x, point.y).show(build);
        transaction.apply();
        this.menuViewContainer = new AdditionalViewHostViewContainer(build, surfaceControlViewHost, this.surfaceControlTransactionSupplier);
    }

    @Override // com.android.wm.shell.shared.desktopmode.ManageWindowsViewContainer
    public final void close() {
        AdditionalViewHostViewContainer additionalViewHostViewContainer = this.menuViewContainer;
        if (additionalViewHostViewContainer != null) {
            additionalViewHostViewContainer.releaseView();
        }
    }
}
