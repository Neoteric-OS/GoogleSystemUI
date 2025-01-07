package com.android.systemui.qs.footer.data.model;

import android.graphics.drawable.Drawable;
import androidx.appcompat.app.AppCompatDelegateImpl$$ExternalSyntheticOutline0;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class UserSwitcherStatusModel {

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Disabled extends UserSwitcherStatusModel {
        public static final Disabled INSTANCE = new Disabled();
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Enabled extends UserSwitcherStatusModel {
        public final Drawable currentUserImage;
        public final String currentUserName;
        public final boolean isGuestUser;

        public Enabled(String str, Drawable drawable, boolean z) {
            this.currentUserName = str;
            this.currentUserImage = drawable;
            this.isGuestUser = z;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof Enabled)) {
                return false;
            }
            Enabled enabled = (Enabled) obj;
            return Intrinsics.areEqual(this.currentUserName, enabled.currentUserName) && Intrinsics.areEqual(this.currentUserImage, enabled.currentUserImage) && this.isGuestUser == enabled.isGuestUser;
        }

        public final int hashCode() {
            String str = this.currentUserName;
            int hashCode = (str == null ? 0 : str.hashCode()) * 31;
            Drawable drawable = this.currentUserImage;
            return Boolean.hashCode(this.isGuestUser) + ((hashCode + (drawable != null ? drawable.hashCode() : 0)) * 31);
        }

        public final String toString() {
            Drawable drawable = this.currentUserImage;
            StringBuilder sb = new StringBuilder("Enabled(currentUserName=");
            sb.append(this.currentUserName);
            sb.append(", currentUserImage=");
            sb.append(drawable);
            sb.append(", isGuestUser=");
            return AppCompatDelegateImpl$$ExternalSyntheticOutline0.m(sb, this.isGuestUser, ")");
        }
    }
}
