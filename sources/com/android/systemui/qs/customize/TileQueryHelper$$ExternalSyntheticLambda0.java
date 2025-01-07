package com.android.systemui.qs.customize;

import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.graphics.drawable.Drawable;
import com.android.systemui.plugins.qs.QSTile;
import com.android.systemui.qs.QSHost;
import com.android.systemui.qs.QSHostAdapter;
import com.android.systemui.qs.external.CustomTile;
import com.android.systemui.qs.tileimpl.QSTileImpl;
import com.android.systemui.settings.UserTrackerImpl;
import com.android.wm.shell.R;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class TileQueryHelper$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ TileQueryHelper f$0;
    public final /* synthetic */ QSHost f$1;

    public /* synthetic */ TileQueryHelper$$ExternalSyntheticLambda0(TileQueryHelper tileQueryHelper, QSHost qSHost) {
        this.f$0 = tileQueryHelper;
        this.f$1 = qSHost;
    }

    @Override // java.lang.Runnable
    public final void run() {
        QSTile.State state;
        TileQueryHelper tileQueryHelper = this.f$0;
        QSHost qSHost = this.f$1;
        tileQueryHelper.getClass();
        Collection tiles = ((QSHostAdapter) qSHost).getTiles();
        PackageManager packageManager = tileQueryHelper.mContext.getPackageManager();
        List<ResolveInfo> queryIntentServicesAsUser = packageManager.queryIntentServicesAsUser(new Intent("android.service.quicksettings.action.QS_TILE"), 0, ((UserTrackerImpl) tileQueryHelper.mUserTracker).getUserId());
        String string = tileQueryHelper.mContext.getString(R.string.quick_settings_tiles_stock);
        for (ResolveInfo resolveInfo : queryIntentServicesAsUser) {
            ComponentName componentName = new ComponentName(resolveInfo.serviceInfo.packageName, resolveInfo.serviceInfo.name);
            if (!string.contains(componentName.flattenToString())) {
                CharSequence loadLabel = resolveInfo.serviceInfo.applicationInfo.loadLabel(packageManager);
                String spec = CustomTile.toSpec(componentName);
                Iterator it = tiles.iterator();
                while (true) {
                    state = null;
                    if (!it.hasNext()) {
                        break;
                    }
                    QSTile qSTile = (QSTile) it.next();
                    if (spec.equals(qSTile.getTileSpec())) {
                        if (qSTile.isTileReady()) {
                            state = qSTile.getState().copy();
                        }
                    }
                }
                if (state != null) {
                    tileQueryHelper.addTile(spec, loadLabel, state, false);
                } else {
                    ServiceInfo serviceInfo = resolveInfo.serviceInfo;
                    if (serviceInfo.icon != 0 || serviceInfo.applicationInfo.icon != 0) {
                        Drawable loadIcon = serviceInfo.loadIcon(packageManager);
                        if ("android.permission.BIND_QUICK_SETTINGS_TILE".equals(resolveInfo.serviceInfo.permission) && loadIcon != null) {
                            loadIcon.mutate();
                            loadIcon.setTint(tileQueryHelper.mContext.getColor(android.R.color.white));
                            CharSequence loadLabel2 = resolveInfo.serviceInfo.loadLabel(packageManager);
                            String charSequence = loadLabel2 != null ? loadLabel2.toString() : "null";
                            QSTile.State state2 = new QSTile.State();
                            state2.state = 1;
                            state2.label = charSequence;
                            state2.contentDescription = charSequence;
                            state2.icon = new QSTileImpl.DrawableIcon(loadIcon);
                            tileQueryHelper.addTile(spec, loadLabel, state2, false);
                        }
                    }
                }
            }
        }
        tileQueryHelper.mMainExecutor.execute(new TileQueryHelper$$ExternalSyntheticLambda1(tileQueryHelper, new ArrayList(tileQueryHelper.mTiles), true));
    }
}
