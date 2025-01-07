package com.android.systemui.statusbar.notification.row.shared;

import android.widget.RemoteViews;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class NewRemoteViews {
    public final RemoteViews contracted;
    public final RemoteViews expanded;
    public final RemoteViews headsUp;
    public final RemoteViews minimizedGroupHeader;
    public final RemoteViews normalGroupHeader;

    /* renamed from: public, reason: not valid java name */
    public final RemoteViews f46public;

    public NewRemoteViews(RemoteViews remoteViews, RemoteViews remoteViews2, RemoteViews remoteViews3, RemoteViews remoteViews4, RemoteViews remoteViews5, RemoteViews remoteViews6) {
        this.contracted = remoteViews;
        this.headsUp = remoteViews2;
        this.expanded = remoteViews3;
        this.f46public = remoteViews4;
        this.normalGroupHeader = remoteViews5;
        this.minimizedGroupHeader = remoteViews6;
    }
}
