package com.google.android.systemui.qs.launcher;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import com.android.systemui.plugins.qs.QSTile;
import com.android.systemui.qs.QSHost;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.Executor;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class LauncherTileService extends Service {
    public final Executor executor;
    public final QSHost qsHost;
    public final Map callbacksMap = new LinkedHashMap();
    public final Map createdTilesMap = new LinkedHashMap();
    public final LauncherTileService$stub$1 stub = new LauncherTileService$stub$1(this);

    public LauncherTileService(QSHost qSHost, Executor executor) {
        this.qsHost = qSHost;
        this.executor = executor;
    }

    @Override // android.app.Service
    public final IBinder onBind(Intent intent) {
        return this.stub;
    }

    @Override // android.app.Service
    public final void onDestroy() {
        this.executor.execute(new Runnable() { // from class: com.google.android.systemui.qs.launcher.LauncherTileService$destroyTilesAndCallbacks$1
            @Override // java.lang.Runnable
            public final void run() {
                Iterator it = LauncherTileService.this.createdTilesMap.values().iterator();
                while (it.hasNext()) {
                    ((QSTile) it.next()).destroy();
                }
                LauncherTileService.this.createdTilesMap.clear();
                HashSet hashSet = new HashSet(LauncherTileService.this.callbacksMap.keySet());
                LauncherTileService launcherTileService = LauncherTileService.this;
                Iterator it2 = hashSet.iterator();
                while (it2.hasNext()) {
                    String str = (String) it2.next();
                    LauncherTileService$stub$1 launcherTileService$stub$1 = launcherTileService.stub;
                    launcherTileService$stub$1.this$0.executor.execute(new LauncherTileService$stub$1$handleClick$1(launcherTileService$stub$1, str, 1));
                }
                LauncherTileService.this.callbacksMap.clear();
            }
        });
        super.onDestroy();
    }
}
