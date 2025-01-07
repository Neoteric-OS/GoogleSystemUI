package com.android.systemui.utils;

import com.android.settingslib.RestrictedLockUtils;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public interface PolicyRestriction {

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class NoRestriction implements PolicyRestriction {
        public static final NoRestriction INSTANCE = new NoRestriction();

        public final boolean equals(Object obj) {
            return this == obj || (obj instanceof NoRestriction);
        }

        public final int hashCode() {
            return 204915163;
        }

        public final String toString() {
            return "NoRestriction";
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Restricted implements PolicyRestriction {
        public final RestrictedLockUtils.EnforcedAdmin admin;

        public Restricted(RestrictedLockUtils.EnforcedAdmin enforcedAdmin) {
            this.admin = enforcedAdmin;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return (obj instanceof Restricted) && Intrinsics.areEqual(this.admin, ((Restricted) obj).admin);
        }

        public final int hashCode() {
            return this.admin.hashCode();
        }

        public final String toString() {
            return "Restricted(admin=" + this.admin + ")";
        }
    }
}
