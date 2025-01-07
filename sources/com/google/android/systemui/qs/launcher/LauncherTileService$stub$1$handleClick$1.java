package com.google.android.systemui.qs.launcher;

import com.android.systemui.plugins.qs.QSTile;
import java.util.Iterator;
import java.util.List;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class LauncherTileService$stub$1$handleClick$1 implements Runnable {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ String $tileSpec;
    public final /* synthetic */ LauncherTileService$stub$1 this$0;

    public /* synthetic */ LauncherTileService$stub$1$handleClick$1(LauncherTileService$stub$1 launcherTileService$stub$1, String str, int i) {
        this.$r8$classId = i;
        this.this$0 = launcherTileService$stub$1;
        this.$tileSpec = str;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.$r8$classId) {
            case 0:
                QSTile tile = this.this$0.getTile(this.$tileSpec, false);
                if (tile != null) {
                    tile.click(null);
                    break;
                }
                break;
            case 1:
                LauncherTileService$stub$1 launcherTileService$stub$1 = this.this$0;
                String str = this.$tileSpec;
                launcherTileService$stub$1.getClass();
                QSTile tile2 = launcherTileService$stub$1.getTile(str, false);
                if (tile2 != null) {
                    List list = (List) launcherTileService$stub$1.this$0.callbacksMap.get(str);
                    if (list != null) {
                        Iterator it = list.iterator();
                        while (it.hasNext()) {
                            tile2.removeCallback((QSTile.Callback) it.next());
                        }
                    }
                    tile2.setListening(launcherTileService$stub$1, false);
                    List list2 = (List) launcherTileService$stub$1.this$0.callbacksMap.get(str);
                    if (list2 != null) {
                        list2.clear();
                    }
                    if (launcherTileService$stub$1.this$0.createdTilesMap.remove(str, tile2)) {
                        tile2.destroy();
                        break;
                    }
                }
                break;
            default:
                QSTile tile3 = this.this$0.getTile(this.$tileSpec, false);
                if (tile3 != null) {
                    tile3.longClick(null);
                    break;
                }
                break;
        }
    }
}
