package com.android.systemui.qs.tiles.base.interactor;

import com.android.settingslib.RestrictedLockUtils;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public interface DisabledByPolicyInteractor$PolicyResult {

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class TileDisabled implements DisabledByPolicyInteractor$PolicyResult {
        public final RestrictedLockUtils.EnforcedAdmin admin;

        public TileDisabled(RestrictedLockUtils.EnforcedAdmin enforcedAdmin) {
            this.admin = enforcedAdmin;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return (obj instanceof TileDisabled) && Intrinsics.areEqual(this.admin, ((TileDisabled) obj).admin);
        }

        public final int hashCode() {
            return this.admin.hashCode();
        }

        public final String toString() {
            return "TileDisabled(admin=" + this.admin + ")";
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class TileEnabled implements DisabledByPolicyInteractor$PolicyResult {
        public static final TileEnabled INSTANCE = new TileEnabled();

        public final boolean equals(Object obj) {
            return this == obj || (obj instanceof TileEnabled);
        }

        public final int hashCode() {
            return -1086222378;
        }

        public final String toString() {
            return "TileEnabled";
        }
    }
}
