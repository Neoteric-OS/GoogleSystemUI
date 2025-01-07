package com.android.systemui.qs.tiles;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import com.android.internal.logging.MetricsLogger;
import com.android.systemui.animation.ActivityTransitionAnimator;
import com.android.systemui.animation.Expandable;
import com.android.systemui.controls.controller.ControlsController;
import com.android.systemui.controls.controller.ControlsControllerImpl;
import com.android.systemui.controls.dagger.ControlsComponent;
import com.android.systemui.controls.management.ControlsListingController;
import com.android.systemui.controls.ui.ControlsUiController;
import com.android.systemui.controls.ui.ControlsUiControllerImpl;
import com.android.systemui.controls.ui.SelectedItem;
import com.android.systemui.plugins.ActivityStarter;
import com.android.systemui.plugins.FalsingManager;
import com.android.systemui.plugins.qs.QSTile;
import com.android.systemui.plugins.statusbar.StatusBarStateController;
import com.android.systemui.qs.QSHost;
import com.android.systemui.qs.QsEventLoggerImpl;
import com.android.systemui.qs.logging.QSLogger;
import com.android.systemui.qs.tileimpl.QSTileImpl;
import com.android.wm.shell.R;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Consumer;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class DeviceControlsTile extends QSTileImpl {
    public final ControlsComponent controlsComponent;
    public final AtomicBoolean hasControlsApps;
    public final DeviceControlsTile$listingCallback$1 listingCallback;

    /* JADX WARN: Type inference failed for: r1v2, types: [com.android.systemui.qs.tiles.DeviceControlsTile$listingCallback$1] */
    public DeviceControlsTile(QSHost qSHost, QsEventLoggerImpl qsEventLoggerImpl, Looper looper, Handler handler, FalsingManager falsingManager, MetricsLogger metricsLogger, StatusBarStateController statusBarStateController, ActivityStarter activityStarter, QSLogger qSLogger, ControlsComponent controlsComponent) {
        super(qSHost, qsEventLoggerImpl, looper, handler, falsingManager, metricsLogger, statusBarStateController, activityStarter, qSLogger);
        this.controlsComponent = controlsComponent;
        this.hasControlsApps = new AtomicBoolean(false);
        this.listingCallback = new ControlsListingController.ControlsListingCallback() { // from class: com.android.systemui.qs.tiles.DeviceControlsTile$listingCallback$1
            @Override // com.android.systemui.controls.management.ControlsListingController.ControlsListingCallback
            public final void onServicesUpdated(List list) {
                DeviceControlsTile deviceControlsTile = DeviceControlsTile.this;
                if (deviceControlsTile.hasControlsApps.compareAndSet(list.isEmpty(), !list.isEmpty())) {
                    deviceControlsTile.refreshState(null);
                }
            }
        };
        controlsComponent.controlsListingController.ifPresent(new Consumer() { // from class: com.android.systemui.qs.tiles.DeviceControlsTile.1
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                ControlsListingController controlsListingController = (ControlsListingController) obj;
                DeviceControlsTile deviceControlsTile = DeviceControlsTile.this;
                DeviceControlsTile$listingCallback$1 deviceControlsTile$listingCallback$1 = deviceControlsTile.listingCallback;
                controlsListingController.getClass();
                controlsListingController.observe(deviceControlsTile.getLifecycle(), deviceControlsTile$listingCallback$1);
            }
        });
    }

    @Override // com.android.systemui.qs.tileimpl.QSTileImpl
    public final Intent getLongClickIntent() {
        return null;
    }

    @Override // com.android.systemui.qs.tileimpl.QSTileImpl, com.android.systemui.plugins.qs.QSTile
    public final int getMetricsCategory() {
        return 0;
    }

    @Override // com.android.systemui.qs.tileimpl.QSTileImpl, com.android.systemui.plugins.qs.QSTile
    public final CharSequence getTileLabel() {
        return this.mContext.getText(this.controlsComponent.controlsTileResourceConfiguration.getTileTitleId());
    }

    @Override // com.android.systemui.qs.tileimpl.QSTileImpl
    public final void handleClick(Expandable expandable) {
        if (this.mState.state == 0) {
            return;
        }
        final Intent intent = new Intent();
        intent.setComponent(new ComponentName(this.mContext, (Class<?>) ((ControlsUiControllerImpl) ((ControlsUiController) this.controlsComponent.controlsUiController.get())).resolveActivity()));
        intent.addFlags(335544320);
        intent.putExtra("extra_animate", true);
        final ActivityTransitionAnimator.Controller activityTransitionController = expandable != null ? expandable.activityTransitionController(32) : null;
        this.mUiHandler.post(new Runnable() { // from class: com.android.systemui.qs.tiles.DeviceControlsTile$handleClick$1
            @Override // java.lang.Runnable
            public final void run() {
                DeviceControlsTile deviceControlsTile = DeviceControlsTile.this;
                deviceControlsTile.mActivityStarter.startActivity(intent, true, activityTransitionController, deviceControlsTile.mState.state == 2);
            }
        });
    }

    @Override // com.android.systemui.qs.tileimpl.QSTileImpl
    public final void handleUpdateState(QSTile.State state, Object obj) {
        CharSequence tileLabel = getTileLabel();
        state.label = tileLabel;
        state.contentDescription = tileLabel;
        ControlsComponent controlsComponent = this.controlsComponent;
        state.icon = QSTileImpl.ResourceIcon.get(controlsComponent.controlsTileResourceConfiguration.getTileImageId());
        if (!controlsComponent.featureEnabled || !this.hasControlsApps.get()) {
            state.state = 0;
            return;
        }
        if (controlsComponent.getVisibility() == ControlsComponent.Visibility.AVAILABLE) {
            SelectedItem preferredSelection = ((ControlsControllerImpl) ((ControlsController) controlsComponent.controlsController.get())).getPreferredSelection();
            state.state = ((preferredSelection instanceof SelectedItem.StructureItem) && ((SelectedItem.StructureItem) preferredSelection).structure.controls.isEmpty()) ? 1 : 2;
            CharSequence name = preferredSelection.getName();
            if (Intrinsics.areEqual(name, getTileLabel())) {
                name = null;
            }
            state.secondaryLabel = name;
        } else {
            state.state = 1;
            state.secondaryLabel = this.mContext.getText(R.string.controls_tile_locked);
        }
        state.stateDescription = state.secondaryLabel;
    }

    @Override // com.android.systemui.qs.tileimpl.QSTileImpl, com.android.systemui.plugins.qs.QSTile
    public final boolean isAvailable() {
        return this.controlsComponent.controlsController.isPresent();
    }

    @Override // com.android.systemui.qs.tileimpl.QSTileImpl
    public final QSTile.State newTileState() {
        QSTile.State state = new QSTile.State();
        state.state = 0;
        state.handlesLongClick = false;
        return state;
    }

    public static /* synthetic */ void getIcon$annotations() {
    }

    @Override // com.android.systemui.qs.tileimpl.QSTileImpl
    public final void handleLongClick(Expandable expandable) {
    }
}
