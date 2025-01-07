package com.android.systemui.statusbar.notification.row.shared;

import android.graphics.drawable.Icon;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class IconModel {
    public final Icon icon;

    public IconModel(Icon icon) {
        this.icon = icon;
    }

    public final boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj.equals(Boolean.valueOf(obj == this))) {
            return true;
        }
        if (obj instanceof IconModel) {
            return ((IconModel) obj).icon.sameAs(this.icon);
        }
        return false;
    }

    public final String toString() {
        return "IconModel(icon=" + this.icon + ", drawable=null)";
    }
}
