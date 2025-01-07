package com.android.systemui.authentication.shared.model;

import androidx.appsearch.safeparcel.PropertyConfigParcel$JoinableConfigParcel$$ExternalSyntheticOutline0;
import androidx.compose.animation.core.KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0;
import com.android.wm.shell.R;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class AuthenticationWipeModel {
    public final int failedAttempts;
    public final int remainingAttempts;
    public final WipeTarget wipeTarget;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public abstract class WipeTarget {

        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        public final class ManagedProfile extends WipeTarget {
            public static final ManagedProfile INSTANCE = new ManagedProfile(R.string.kg_failed_attempts_almost_at_erase_profile, R.string.kg_failed_attempts_now_erasing_profile);

            public final boolean equals(Object obj) {
                return this == obj || (obj instanceof ManagedProfile);
            }

            public final int hashCode() {
                return -1539762587;
            }

            public final String toString() {
                return "ManagedProfile";
            }
        }

        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        public final class User extends WipeTarget {
            public static final User INSTANCE = new User(R.string.kg_failed_attempts_almost_at_erase_user, R.string.kg_failed_attempts_now_erasing_user);

            public final boolean equals(Object obj) {
                return this == obj || (obj instanceof User);
            }

            public final int hashCode() {
                return -1642015002;
            }

            public final String toString() {
                return "User";
            }
        }

        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        public final class WholeDevice extends WipeTarget {
            public static final WholeDevice INSTANCE = new WholeDevice(R.string.kg_failed_attempts_almost_at_wipe, R.string.kg_failed_attempts_now_wiping);

            public final boolean equals(Object obj) {
                return this == obj || (obj instanceof WholeDevice);
            }

            public final int hashCode() {
                return 744290162;
            }

            public final String toString() {
                return "WholeDevice";
            }
        }

        public WipeTarget(int i, int i2) {
        }
    }

    public AuthenticationWipeModel(WipeTarget wipeTarget, int i, int i2) {
        this.wipeTarget = wipeTarget;
        this.failedAttempts = i;
        this.remainingAttempts = i2;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof AuthenticationWipeModel)) {
            return false;
        }
        AuthenticationWipeModel authenticationWipeModel = (AuthenticationWipeModel) obj;
        return Intrinsics.areEqual(this.wipeTarget, authenticationWipeModel.wipeTarget) && this.failedAttempts == authenticationWipeModel.failedAttempts && this.remainingAttempts == authenticationWipeModel.remainingAttempts;
    }

    public final int hashCode() {
        return Integer.hashCode(this.remainingAttempts) + KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0.m(this.failedAttempts, this.wipeTarget.hashCode() * 31, 31);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("AuthenticationWipeModel(wipeTarget=");
        sb.append(this.wipeTarget);
        sb.append(", failedAttempts=");
        sb.append(this.failedAttempts);
        sb.append(", remainingAttempts=");
        return PropertyConfigParcel$JoinableConfigParcel$$ExternalSyntheticOutline0.m(sb, this.remainingAttempts, ")");
    }
}
