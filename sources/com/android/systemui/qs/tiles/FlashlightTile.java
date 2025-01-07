package com.android.systemui.qs.tiles;

import android.app.ActivityManager;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.widget.Switch;
import com.android.internal.logging.MetricsLogger;
import com.android.systemui.animation.Expandable;
import com.android.systemui.plugins.ActivityStarter;
import com.android.systemui.plugins.FalsingManager;
import com.android.systemui.plugins.qs.QSTile;
import com.android.systemui.plugins.statusbar.StatusBarStateController;
import com.android.systemui.qs.QSHost;
import com.android.systemui.qs.QSHostAdapter;
import com.android.systemui.qs.QsEventLoggerImpl;
import com.android.systemui.qs.logging.QSLogger;
import com.android.systemui.qs.tileimpl.QSTileImpl;
import com.android.systemui.statusbar.policy.FlashlightController$FlashlightListener;
import com.android.systemui.statusbar.policy.FlashlightControllerImpl;
import com.android.wm.shell.R;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public class FlashlightTile extends QSTileImpl implements FlashlightController$FlashlightListener {
    public final FlashlightControllerImpl mFlashlightController;

    public FlashlightTile(QSHost qSHost, QsEventLoggerImpl qsEventLoggerImpl, Looper looper, Handler handler, FalsingManager falsingManager, MetricsLogger metricsLogger, StatusBarStateController statusBarStateController, ActivityStarter activityStarter, QSLogger qSLogger, FlashlightControllerImpl flashlightControllerImpl) {
        super(qSHost, qsEventLoggerImpl, looper, handler, falsingManager, metricsLogger, statusBarStateController, activityStarter, qSLogger);
        this.mFlashlightController = flashlightControllerImpl;
        flashlightControllerImpl.observe(this.mLifecycle, this);
    }

    @Override // com.android.systemui.qs.tileimpl.QSTileImpl
    public final Intent getLongClickIntent() {
        return new Intent("android.media.action.STILL_IMAGE_CAMERA");
    }

    @Override // com.android.systemui.qs.tileimpl.QSTileImpl, com.android.systemui.plugins.qs.QSTile
    public final int getMetricsCategory() {
        return 119;
    }

    @Override // com.android.systemui.qs.tileimpl.QSTileImpl, com.android.systemui.plugins.qs.QSTile
    public final CharSequence getTileLabel() {
        return this.mContext.getString(R.string.quick_settings_flashlight_label);
    }

    @Override // com.android.systemui.qs.tileimpl.QSTileImpl
    public final void handleClick(Expandable expandable) {
        if (ActivityManager.isUserAMonkey()) {
            return;
        }
        boolean z = !((QSTile.BooleanState) this.mState).value;
        refreshState(Boolean.valueOf(z));
        this.mFlashlightController.setFlashlight(z);
    }

    @Override // com.android.systemui.qs.tileimpl.QSTileImpl
    public final void handleLongClick(Expandable expandable) {
        handleClick(expandable);
    }

    @Override // com.android.systemui.qs.tileimpl.QSTileImpl
    public final void handleUpdateState(QSTile.State state, Object obj) {
        QSTile.BooleanState booleanState = (QSTile.BooleanState) state;
        booleanState.label = ((QSHostAdapter) this.mHost).context.getString(R.string.quick_settings_flashlight_label);
        booleanState.secondaryLabel = "";
        booleanState.stateDescription = "";
        FlashlightControllerImpl flashlightControllerImpl = this.mFlashlightController;
        boolean isAvailable = flashlightControllerImpl.isAvailable();
        int i = R.drawable.qs_flashlight_icon_off;
        if (!isAvailable) {
            String string = this.mContext.getString(R.string.quick_settings_flashlight_camera_in_use);
            booleanState.secondaryLabel = string;
            booleanState.stateDescription = string;
            booleanState.state = 0;
            booleanState.icon = QSTileImpl.ResourceIcon.get(R.drawable.qs_flashlight_icon_off);
            return;
        }
        if (obj instanceof Boolean) {
            boolean booleanValue = ((Boolean) obj).booleanValue();
            if (booleanValue == booleanState.value) {
                return;
            } else {
                booleanState.value = booleanValue;
            }
        } else {
            booleanState.value = flashlightControllerImpl.isEnabled();
        }
        booleanState.contentDescription = this.mContext.getString(R.string.quick_settings_flashlight_label);
        booleanState.expandedAccessibilityClassName = Switch.class.getName();
        boolean z = booleanState.value;
        booleanState.state = z ? 2 : 1;
        if (z) {
            i = R.drawable.qs_flashlight_icon_on;
        }
        booleanState.icon = QSTileImpl.ResourceIcon.get(i);
    }

    @Override // com.android.systemui.qs.tileimpl.QSTileImpl, com.android.systemui.plugins.qs.QSTile
    public final boolean isAvailable() {
        return this.mFlashlightController.mHasFlashlight;
    }

    @Override // com.android.systemui.qs.tileimpl.QSTileImpl
    public final QSTile.State newTileState() {
        QSTile.BooleanState booleanState = new QSTile.BooleanState();
        booleanState.handlesLongClick = false;
        return booleanState;
    }

    @Override // com.android.systemui.statusbar.policy.FlashlightController$FlashlightListener
    public final void onFlashlightAvailabilityChanged(boolean z) {
        refreshState(null);
    }

    @Override // com.android.systemui.statusbar.policy.FlashlightController$FlashlightListener
    public final void onFlashlightChanged(boolean z) {
        refreshState(Boolean.valueOf(z));
    }

    @Override // com.android.systemui.statusbar.policy.FlashlightController$FlashlightListener
    public final void onFlashlightError() {
        refreshState(Boolean.FALSE);
    }

    @Override // com.android.systemui.qs.tileimpl.QSTileImpl
    public final void handleUserSwitch(int i) {
    }
}
