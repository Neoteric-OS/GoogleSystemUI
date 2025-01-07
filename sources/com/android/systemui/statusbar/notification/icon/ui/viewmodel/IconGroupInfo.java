package com.android.systemui.statusbar.notification.icon.ui.viewmodel;

import android.graphics.drawable.Icon;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class IconGroupInfo {
    public final String groupKey;
    public final Icon sourceIcon;

    public IconGroupInfo(Icon icon, String str) {
        this.sourceIcon = icon;
        this.groupKey = str;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!IconGroupInfo.class.equals(obj != null ? obj.getClass() : null)) {
            return false;
        }
        IconGroupInfo iconGroupInfo = (IconGroupInfo) obj;
        if (Intrinsics.areEqual(this.groupKey, iconGroupInfo.groupKey)) {
            return this.sourceIcon.sameAs(iconGroupInfo.sourceIcon);
        }
        return false;
    }

    public final int hashCode() {
        int i;
        int hashCode;
        int hashCode2;
        int hashCode3;
        int hashCode4 = Integer.hashCode(this.sourceIcon.getType()) + (this.groupKey.hashCode() * 31);
        switch (this.sourceIcon.getType()) {
            case 1:
            case 5:
                i = hashCode4 * 31;
                hashCode = this.sourceIcon.getBitmap().hashCode();
                return i + hashCode;
            case 2:
                hashCode2 = (Integer.hashCode(this.sourceIcon.getResId()) + (hashCode4 * 31)) * 31;
                hashCode3 = this.sourceIcon.getResPackage().hashCode();
                break;
            case 3:
                hashCode2 = (Integer.hashCode(this.sourceIcon.getDataLength()) + (hashCode4 * 31)) * 31;
                hashCode3 = Integer.hashCode(this.sourceIcon.getDataOffset());
                break;
            case 4:
            case 6:
                i = hashCode4 * 31;
                hashCode = this.sourceIcon.getUriString().hashCode();
                return i + hashCode;
            default:
                return hashCode4;
        }
        return hashCode3 + hashCode2;
    }

    public final String toString() {
        return "IconGroupInfo(sourceIcon=" + this.sourceIcon + ", groupKey=" + this.groupKey + ")";
    }
}
