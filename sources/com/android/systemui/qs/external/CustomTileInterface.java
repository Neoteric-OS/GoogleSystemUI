package com.android.systemui.qs.external;

import android.app.PendingIntent;
import android.content.ComponentName;
import android.service.quicksettings.Tile;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public interface CustomTileInterface {
    ComponentName getComponent();

    Tile getQsTile();

    String getTileSpec();

    int getUser();

    void onDialogHidden();

    void onDialogShown();

    void refreshState();

    void startActivityAndCollapse(PendingIntent pendingIntent);

    void startUnlockAndRun();

    void updateTileState(Tile tile, int i);
}
