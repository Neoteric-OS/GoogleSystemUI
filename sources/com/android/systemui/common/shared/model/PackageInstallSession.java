package com.android.systemui.common.shared.model;

import android.graphics.Bitmap;
import android.os.UserHandle;
import androidx.compose.animation.graphics.vector.PropertyValuesHolder2D$$ExternalSyntheticOutline0;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class PackageInstallSession {
    public final Bitmap icon;
    public final String packageName;
    public final int sessionId;
    public final UserHandle user;

    public PackageInstallSession(int i, String str, Bitmap bitmap, UserHandle userHandle) {
        this.sessionId = i;
        this.packageName = str;
        this.icon = bitmap;
        this.user = userHandle;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof PackageInstallSession)) {
            return false;
        }
        PackageInstallSession packageInstallSession = (PackageInstallSession) obj;
        return this.sessionId == packageInstallSession.sessionId && Intrinsics.areEqual(this.packageName, packageInstallSession.packageName) && Intrinsics.areEqual(this.icon, packageInstallSession.icon) && Intrinsics.areEqual(this.user, packageInstallSession.user);
    }

    public final int hashCode() {
        int m = PropertyValuesHolder2D$$ExternalSyntheticOutline0.m(this.packageName, Integer.hashCode(this.sessionId) * 31, 31);
        Bitmap bitmap = this.icon;
        return this.user.hashCode() + ((m + (bitmap == null ? 0 : bitmap.hashCode())) * 31);
    }

    public final String toString() {
        return "PackageInstallSession(sessionId=" + this.sessionId + ", packageName=" + this.packageName + ", icon=" + this.icon + ", user=" + this.user + ")";
    }
}
