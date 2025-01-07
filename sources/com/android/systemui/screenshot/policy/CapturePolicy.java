package com.android.systemui.screenshot.policy;

import androidx.activity.ComponentActivity$activityResultRegistry$1$$ExternalSyntheticOutline0;
import androidx.compose.animation.graphics.vector.PropertyValuesHolder2D$$ExternalSyntheticOutline0;
import com.android.systemui.screenshot.data.model.DisplayContentModel;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public interface CapturePolicy {

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public interface PolicyResult {

        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        public final class Matched implements PolicyResult {
            public final CaptureParameters parameters;
            public final String policy;
            public final String reason;

            public Matched(String str, String str2, CaptureParameters captureParameters) {
                this.policy = str;
                this.reason = str2;
                this.parameters = captureParameters;
            }

            public final boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                if (!(obj instanceof Matched)) {
                    return false;
                }
                Matched matched = (Matched) obj;
                return Intrinsics.areEqual(this.policy, matched.policy) && Intrinsics.areEqual(this.reason, matched.reason) && Intrinsics.areEqual(this.parameters, matched.parameters);
            }

            public final int hashCode() {
                return this.parameters.hashCode() + PropertyValuesHolder2D$$ExternalSyntheticOutline0.m(this.reason, this.policy.hashCode() * 31, 31);
            }

            public final String toString() {
                return "Matched(policy=" + this.policy + ", reason=" + this.reason + ", parameters=" + this.parameters + ")";
            }
        }

        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        public final class NotMatched implements PolicyResult {
            public final String policy;
            public final String reason;

            public NotMatched(String str, String str2) {
                this.policy = str;
                this.reason = str2;
            }

            public final boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                if (!(obj instanceof NotMatched)) {
                    return false;
                }
                NotMatched notMatched = (NotMatched) obj;
                return Intrinsics.areEqual(this.policy, notMatched.policy) && Intrinsics.areEqual(this.reason, notMatched.reason);
            }

            public final int hashCode() {
                return this.reason.hashCode() + (this.policy.hashCode() * 31);
            }

            public final String toString() {
                StringBuilder sb = new StringBuilder("NotMatched(policy=");
                sb.append(this.policy);
                sb.append(", reason=");
                return ComponentActivity$activityResultRegistry$1$$ExternalSyntheticOutline0.m(sb, this.reason, ")");
            }
        }
    }

    Object check(DisplayContentModel displayContentModel, ContinuationImpl continuationImpl);
}
