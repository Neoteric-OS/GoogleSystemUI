package com.android.systemui.qs.tileimpl;

import android.app.IUriGrantsManager;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import com.android.internal.logging.MetricsLogger;
import com.android.systemui.plugins.ActivityStarter;
import com.android.systemui.plugins.FalsingManager;
import com.android.systemui.plugins.qs.QSFactory;
import com.android.systemui.plugins.qs.QSTile;
import com.android.systemui.plugins.statusbar.StatusBarStateController;
import com.android.systemui.qs.QSHost;
import com.android.systemui.qs.QSHostAdapter;
import com.android.systemui.qs.QsEventLoggerImpl;
import com.android.systemui.qs.external.CustomTile;
import com.android.systemui.qs.external.CustomTileStatePersisterImpl;
import com.android.systemui.qs.external.TileServices;
import com.android.systemui.qs.tileimpl.QSTileImpl;
import com.android.systemui.settings.DisplayTracker;
import com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl;
import com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl;
import com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$51;
import com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import java.util.Map;
import javax.inject.Provider;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class QSFactoryImpl implements QSFactory {
    public final Provider mCustomTileFactoryProvider;
    public final Lazy mQsHostLazy;
    public final Map mTileMap;

    public QSFactoryImpl(Lazy lazy, Provider provider, Map map) {
        this.mQsHostLazy = lazy;
        this.mCustomTileFactoryProvider = provider;
        this.mTileMap = map;
    }

    @Override // com.android.systemui.plugins.qs.QSFactory
    public final QSTile createTile(String str) {
        QSTileImpl qSTileImpl;
        if (this.mTileMap.containsKey(str)) {
            qSTileImpl = (QSTileImpl) ((Provider) this.mTileMap.get(str)).get();
        } else if (str.startsWith("custom(")) {
            DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$51 daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$51 = (DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$51) this.mCustomTileFactoryProvider.get();
            Context userContext = ((QSHostAdapter) ((QSHost) this.mQsHostLazy.get())).getUserContext();
            if (!str.startsWith("custom(") || !str.endsWith(")")) {
                throw new IllegalArgumentException("Bad custom tile spec: ".concat(str));
            }
            String substring = str.substring(7, str.length() - 1);
            if (substring.isEmpty()) {
                throw new IllegalArgumentException("Empty custom tile spec action");
            }
            DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider switchingProvider = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$51.this$0;
            Lazy lazy = DoubleCheck.lazy(((DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) switchingProvider.wMComponentImpl).qSHostAdapterProvider);
            DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl = (DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) switchingProvider.wMComponentImpl;
            QsEventLoggerImpl qsEventLoggerImpl = (QsEventLoggerImpl) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.qsEventLoggerImplProvider.get();
            Looper looper = (Looper) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideBgLooperProvider.get();
            DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl = switchingProvider.sysUIGoogleGlobalRootComponentImpl;
            qSTileImpl = new CustomTile(lazy, qsEventLoggerImpl, looper, (Handler) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideMainHandlerProvider.get(), (FalsingManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.falsingManagerProxyProvider.get(), (MetricsLogger) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideMetricsLoggerProvider.get(), (StatusBarStateController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.statusBarStateControllerImplProvider.get(), (ActivityStarter) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.activityStarterImplProvider.get(), daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.qSLogger(), substring, userContext, new CustomTileStatePersisterImpl(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.sysUIGoogleGlobalRootComponentImpl.context), (TileServices) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.tileServicesProvider.get(), (DisplayTracker) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideDisplayTrackerProvider.get(), (IUriGrantsManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideIUriGrantsManagerProvider.get());
        } else {
            Log.w("QSFactory", "No stock tile spec: ".concat(str));
            qSTileImpl = null;
        }
        if (qSTileImpl != null) {
            QSTileImpl.H h = qSTileImpl.mHandler;
            h.sendEmptyMessage(12);
            h.sendEmptyMessage(11);
            qSTileImpl.setTileSpec(str);
        }
        return qSTileImpl;
    }
}
