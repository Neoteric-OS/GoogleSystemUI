package com.google.android.systemui.qs.tiles;

import android.R;
import android.content.Intent;
import android.content.om.OverlayInfo;
import android.content.om.OverlayManager;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.os.Looper;
import android.os.Process;
import android.os.UserHandle;
import com.android.internal.logging.MetricsLogger;
import com.android.systemui.animation.Expandable;
import com.android.systemui.plugins.ActivityStarter;
import com.android.systemui.plugins.FalsingManager;
import com.android.systemui.plugins.qs.QSTile;
import com.android.systemui.plugins.statusbar.StatusBarStateController;
import com.android.systemui.qs.QSHost;
import com.android.systemui.qs.QsEventLoggerImpl;
import com.android.systemui.qs.logging.QSLogger;
import com.android.systemui.qs.tileimpl.QSTileImpl;
import java.util.Iterator;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class OverlayToggleTile extends QSTileImpl {
    public final OverlayManager om;
    public CharSequence overlayLabel;
    public String overlayPackage;

    public OverlayToggleTile(QSHost qSHost, QsEventLoggerImpl qsEventLoggerImpl, Looper looper, Handler handler, FalsingManager falsingManager, MetricsLogger metricsLogger, StatusBarStateController statusBarStateController, ActivityStarter activityStarter, QSLogger qSLogger, OverlayManager overlayManager) {
        super(qSHost, qsEventLoggerImpl, looper, handler, falsingManager, metricsLogger, statusBarStateController, activityStarter, qSLogger);
        this.om = overlayManager;
    }

    @Override // com.android.systemui.qs.tileimpl.QSTileImpl
    public final Intent getLongClickIntent() {
        return null;
    }

    @Override // com.android.systemui.qs.tileimpl.QSTileImpl, com.android.systemui.plugins.qs.QSTile
    public final int getMetricsCategory() {
        return -1;
    }

    @Override // com.android.systemui.qs.tileimpl.QSTileImpl, com.android.systemui.plugins.qs.QSTile
    public final CharSequence getTileLabel() {
        return "Overlay";
    }

    @Override // com.android.systemui.qs.tileimpl.QSTileImpl
    public final void handleClick(Expandable expandable) {
        QSTile.BooleanState booleanState;
        String str = this.overlayPackage;
        if (str == null || (booleanState = (QSTile.BooleanState) this.mState) == null) {
            return;
        }
        this.om.setEnabled(str, booleanState.state != 2, UserHandle.CURRENT);
        refreshState("Restarting...");
        Thread.sleep(250L);
        Process.killProcess(Process.myPid());
    }

    @Override // com.android.systemui.qs.tileimpl.QSTileImpl
    public final void handleUpdateState(QSTile.State state, Object obj) {
        Object obj2;
        QSTile.BooleanState booleanState = (QSTile.BooleanState) state;
        PackageManager packageManager = this.mContext.getPackageManager();
        booleanState.state = 0;
        booleanState.label = "No overlay";
        List overlayInfosForTarget = this.om.getOverlayInfosForTarget("com.android.systemui", UserHandle.CURRENT);
        if (overlayInfosForTarget != null) {
            Iterator it = overlayInfosForTarget.iterator();
            while (true) {
                if (!it.hasNext()) {
                    obj2 = null;
                    break;
                } else {
                    obj2 = it.next();
                    if (((OverlayInfo) obj2).packageName.startsWith("com.google.")) {
                        break;
                    }
                }
            }
            OverlayInfo overlayInfo = (OverlayInfo) obj2;
            if (overlayInfo != null) {
                if (!Intrinsics.areEqual(this.overlayPackage, overlayInfo.packageName)) {
                    String str = overlayInfo.packageName;
                    this.overlayPackage = str;
                    ApplicationInfo applicationInfo = packageManager.getPackageInfo(str, 0).applicationInfo;
                    this.overlayLabel = applicationInfo != null ? applicationInfo.loadLabel(packageManager) : null;
                }
                booleanState.value = overlayInfo.isEnabled();
                booleanState.state = overlayInfo.isEnabled() ? 2 : 1;
                booleanState.icon = QSTileImpl.ResourceIcon.get(R.drawable.stat_sys_download_anim5);
                booleanState.label = this.overlayLabel;
                booleanState.secondaryLabel = obj != null ? String.valueOf(obj) : overlayInfo.isEnabled() ? "Enabled" : "Disabled";
            }
        }
    }

    @Override // com.android.systemui.qs.tileimpl.QSTileImpl, com.android.systemui.plugins.qs.QSTile
    public final boolean isAvailable() {
        return false;
    }

    @Override // com.android.systemui.qs.tileimpl.QSTileImpl
    public final QSTile.State newTileState() {
        return new QSTile.BooleanState();
    }

    @Override // com.android.systemui.qs.tileimpl.QSTileImpl
    public final void handleLongClick(Expandable expandable) {
    }
}
