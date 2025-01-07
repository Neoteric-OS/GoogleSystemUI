package com.android.systemui.qs.tiles.impl.custom.domain.entity;

import android.content.ComponentName;
import android.graphics.drawable.Icon;
import android.os.UserHandle;
import android.service.quicksettings.Tile;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class CustomTileDataModel {
    public final ComponentName componentName;
    public final Icon defaultTileIcon;
    public final Tile tile;
    public final UserHandle user;
}
