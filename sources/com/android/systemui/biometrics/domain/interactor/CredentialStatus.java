package com.android.systemui.biometrics.domain.interactor;

import androidx.activity.ComponentActivity$activityResultRegistry$1$$ExternalSyntheticOutline0;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public interface CredentialStatus {

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public interface Fail extends CredentialStatus {

        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        public final class Error implements Fail {
            public final String error;
            public final Integer remainingAttempts;
            public final String urgentMessage;

            public /* synthetic */ Error(String str, int i) {
                this((i & 1) != 0 ? null : str, null, null);
            }

            public final boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                if (!(obj instanceof Error)) {
                    return false;
                }
                Error error = (Error) obj;
                return Intrinsics.areEqual(this.error, error.error) && Intrinsics.areEqual(this.remainingAttempts, error.remainingAttempts) && Intrinsics.areEqual(this.urgentMessage, error.urgentMessage);
            }

            public final int hashCode() {
                String str = this.error;
                int hashCode = (str == null ? 0 : str.hashCode()) * 31;
                Integer num = this.remainingAttempts;
                int hashCode2 = (hashCode + (num == null ? 0 : num.hashCode())) * 31;
                String str2 = this.urgentMessage;
                return hashCode2 + (str2 != null ? str2.hashCode() : 0);
            }

            public final String toString() {
                StringBuilder sb = new StringBuilder("Error(error=");
                sb.append(this.error);
                sb.append(", remainingAttempts=");
                sb.append(this.remainingAttempts);
                sb.append(", urgentMessage=");
                return ComponentActivity$activityResultRegistry$1$$ExternalSyntheticOutline0.m(sb, this.urgentMessage, ")");
            }

            public Error(String str, String str2, Integer num) {
                this.error = str;
                this.remainingAttempts = num;
                this.urgentMessage = str2;
            }
        }

        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        public final class Throttled implements Fail {
            public final String error;

            public Throttled(String str) {
                this.error = str;
            }

            public final boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                return (obj instanceof Throttled) && Intrinsics.areEqual(this.error, ((Throttled) obj).error);
            }

            public final int hashCode() {
                return this.error.hashCode();
            }

            public final String toString() {
                return ComponentActivity$activityResultRegistry$1$$ExternalSyntheticOutline0.m(new StringBuilder("Throttled(error="), this.error, ")");
            }
        }
    }
}
