package com.android.systemui.qs.tiles;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.widget.Switch;
import com.android.internal.logging.MetricsLogger;
import com.android.systemui.animation.Expandable;
import com.android.systemui.common.shared.model.ContentDescription;
import com.android.systemui.common.shared.model.Text;
import com.android.systemui.plugins.ActivityStarter;
import com.android.systemui.plugins.FalsingManager;
import com.android.systemui.plugins.qs.QSTile;
import com.android.systemui.plugins.statusbar.StatusBarStateController;
import com.android.systemui.qs.QSHost;
import com.android.systemui.qs.QsEventLoggerImpl;
import com.android.systemui.qs.logging.QSLogger;
import com.android.systemui.qs.tileimpl.QSTileImpl;
import com.android.systemui.qs.tiles.dialog.InternetDialogManager;
import com.android.systemui.qs.tiles.dialog.WifiStateWorker;
import com.android.systemui.statusbar.connectivity.AccessPointController;
import com.android.systemui.statusbar.connectivity.AccessPointControllerImpl;
import com.android.systemui.statusbar.pipeline.shared.ui.binder.InternetTileBinder;
import com.android.systemui.statusbar.pipeline.shared.ui.model.InternetTileModel;
import com.android.systemui.statusbar.pipeline.shared.ui.viewmodel.InternetTileViewModel;
import com.android.wm.shell.R;
import java.util.function.Consumer;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.flow.ReadonlyStateFlow;
import kotlinx.coroutines.flow.StateFlowImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class InternetTileNewImpl extends QSTileImpl {
    public static final Intent WIFI_SETTINGS = new Intent("android.settings.WIFI_SETTINGS");
    public final AccessPointController accessPointController;
    public final InternetDialogManager internetDialogManager;
    public final Handler mainHandler;
    public InternetTileModel model;
    public final WifiStateWorker wifiStateWorker;

    /* JADX WARN: Type inference failed for: r3v1, types: [com.android.systemui.qs.tiles.InternetTileNewImpl$1] */
    public InternetTileNewImpl(QSHost qSHost, QsEventLoggerImpl qsEventLoggerImpl, Looper looper, Handler handler, FalsingManager falsingManager, MetricsLogger metricsLogger, StatusBarStateController statusBarStateController, ActivityStarter activityStarter, QSLogger qSLogger, InternetTileViewModel internetTileViewModel, InternetDialogManager internetDialogManager, WifiStateWorker wifiStateWorker, AccessPointController accessPointController) {
        super(qSHost, qsEventLoggerImpl, looper, handler, falsingManager, metricsLogger, statusBarStateController, activityStarter, qSLogger);
        this.mainHandler = handler;
        this.internetDialogManager = internetDialogManager;
        this.wifiStateWorker = wifiStateWorker;
        this.accessPointController = accessPointController;
        ReadonlyStateFlow readonlyStateFlow = internetTileViewModel.tileModel;
        this.model = (InternetTileModel) ((StateFlowImpl) readonlyStateFlow.$$delegate_0).getValue();
        InternetTileBinder.bind(this.mLifecycle, readonlyStateFlow, new Consumer() { // from class: com.android.systemui.qs.tiles.InternetTileNewImpl.1
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                InternetTileNewImpl internetTileNewImpl = InternetTileNewImpl.this;
                internetTileNewImpl.model = (InternetTileModel) obj;
                internetTileNewImpl.refreshState(null);
            }
        });
    }

    @Override // com.android.systemui.qs.tileimpl.QSTileImpl
    public final Intent getLongClickIntent() {
        return WIFI_SETTINGS;
    }

    @Override // com.android.systemui.qs.tileimpl.QSTileImpl, com.android.systemui.plugins.qs.QSTile
    public final CharSequence getTileLabel() {
        return this.mContext.getString(R.string.quick_settings_internet_label);
    }

    @Override // com.android.systemui.qs.tileimpl.QSTileImpl
    public final void handleClick(final Expandable expandable) {
        this.mainHandler.post(new Runnable() { // from class: com.android.systemui.qs.tiles.InternetTileNewImpl$handleClick$1
            @Override // java.lang.Runnable
            public final void run() {
                InternetTileNewImpl internetTileNewImpl = InternetTileNewImpl.this;
                internetTileNewImpl.internetDialogManager.create(((AccessPointControllerImpl) internetTileNewImpl.accessPointController).canConfigMobileData(), ((AccessPointControllerImpl) InternetTileNewImpl.this.accessPointController).canConfigWifi(), expandable);
            }
        });
    }

    @Override // com.android.systemui.qs.tileimpl.QSTileImpl
    public final void handleUpdateState(QSTile.State state, Object obj) {
        String string;
        String string2;
        QSTile.BooleanState booleanState = (QSTile.BooleanState) state;
        booleanState.label = this.mContext.getResources().getString(R.string.quick_settings_internet_label);
        booleanState.expandedAccessibilityClassName = Switch.class.getName();
        InternetTileModel internetTileModel = this.model;
        Context context = this.mContext;
        String str = null;
        if (internetTileModel.getSecondaryLabel() != null) {
            Text secondaryLabel = internetTileModel.getSecondaryLabel();
            if (secondaryLabel == null) {
                string2 = null;
            } else if (secondaryLabel instanceof Text.Loaded) {
                string2 = ((Text.Loaded) secondaryLabel).text;
            } else {
                if (!(secondaryLabel instanceof Text.Resource)) {
                    throw new NoWhenBranchMatchedException();
                }
                string2 = context.getString(((Text.Resource) secondaryLabel).res);
            }
            booleanState.secondaryLabel = string2;
        } else {
            booleanState.secondaryLabel = internetTileModel.getSecondaryTitle();
        }
        ContentDescription stateDescription = internetTileModel.getStateDescription();
        if (stateDescription == null) {
            string = null;
        } else if (stateDescription instanceof ContentDescription.Loaded) {
            string = ((ContentDescription.Loaded) stateDescription).description;
        } else {
            if (!(stateDescription instanceof ContentDescription.Resource)) {
                throw new NoWhenBranchMatchedException();
            }
            string = context.getString(((ContentDescription.Resource) stateDescription).res);
        }
        booleanState.stateDescription = string;
        ContentDescription contentDescription = internetTileModel.getContentDescription();
        if (contentDescription != null) {
            if (contentDescription instanceof ContentDescription.Loaded) {
                str = ((ContentDescription.Loaded) contentDescription).description;
            } else {
                if (!(contentDescription instanceof ContentDescription.Resource)) {
                    throw new NoWhenBranchMatchedException();
                }
                str = context.getString(((ContentDescription.Resource) contentDescription).res);
            }
        }
        booleanState.contentDescription = str;
        if (internetTileModel.getIcon() != null) {
            booleanState.icon = internetTileModel.getIcon();
        } else if (internetTileModel.getIconId() != null) {
            Integer iconId = internetTileModel.getIconId();
            Intrinsics.checkNotNull(iconId);
            booleanState.icon = QSTileImpl.ResourceIcon.get(iconId.intValue());
        }
        booleanState.state = internetTileModel instanceof InternetTileModel.Active ? 2 : 1;
    }

    @Override // com.android.systemui.qs.tileimpl.QSTileImpl
    public final QSTile.State newTileState() {
        QSTile.BooleanState booleanState = new QSTile.BooleanState();
        booleanState.forceExpandIcon = true;
        booleanState.handlesSecondaryClick = true;
        return booleanState;
    }

    @Override // com.android.systemui.qs.tileimpl.QSTileImpl, com.android.systemui.plugins.qs.QSTile
    public final void secondaryClick(Expandable expandable) {
        this.wifiStateWorker.setWifiEnabled(!r0.isWifiEnabled());
    }
}
