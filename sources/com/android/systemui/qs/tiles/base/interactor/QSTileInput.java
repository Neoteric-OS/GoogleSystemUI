package com.android.systemui.qs.tiles.base.interactor;

import android.os.UserHandle;
import com.android.systemui.qs.tiles.viewmodel.QSTileUserAction;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class QSTileInput {
    public final QSTileUserAction action;
    public final Object data;
    public final UserHandle user;

    public QSTileInput(UserHandle userHandle, QSTileUserAction qSTileUserAction, Object obj) {
        this.user = userHandle;
        this.data = obj;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof QSTileInput)) {
            return false;
        }
        QSTileInput qSTileInput = (QSTileInput) obj;
        return Intrinsics.areEqual(this.user, qSTileInput.user) && Intrinsics.areEqual(this.action, qSTileInput.action) && Intrinsics.areEqual(this.data, qSTileInput.data);
    }

    public final int hashCode() {
        int hashCode = (this.action.hashCode() + (this.user.hashCode() * 31)) * 31;
        Object obj = this.data;
        return hashCode + (obj == null ? 0 : obj.hashCode());
    }

    public final String toString() {
        return "QSTileInput(user=" + this.user + ", action=" + this.action + ", data=" + this.data + ")";
    }
}
