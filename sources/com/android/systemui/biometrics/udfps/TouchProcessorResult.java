package com.android.systemui.biometrics.udfps;

import androidx.activity.ComponentActivity$activityResultRegistry$1$$ExternalSyntheticOutline0;
import androidx.compose.animation.core.KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class TouchProcessorResult {

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Failure extends TouchProcessorResult {
        public final String reason;

        public Failure(String str) {
            this.reason = str;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return (obj instanceof Failure) && Intrinsics.areEqual(this.reason, ((Failure) obj).reason);
        }

        public final int hashCode() {
            return this.reason.hashCode();
        }

        public final String toString() {
            return ComponentActivity$activityResultRegistry$1$$ExternalSyntheticOutline0.m(new StringBuilder("Failure(reason="), this.reason, ")");
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class ProcessedTouch extends TouchProcessorResult {
        public final InteractionEvent event;
        public final int pointerOnSensorId;
        public final NormalizedTouchData touchData;

        public ProcessedTouch(InteractionEvent interactionEvent, int i, NormalizedTouchData normalizedTouchData) {
            this.event = interactionEvent;
            this.pointerOnSensorId = i;
            this.touchData = normalizedTouchData;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof ProcessedTouch)) {
                return false;
            }
            ProcessedTouch processedTouch = (ProcessedTouch) obj;
            return this.event == processedTouch.event && this.pointerOnSensorId == processedTouch.pointerOnSensorId && Intrinsics.areEqual(this.touchData, processedTouch.touchData);
        }

        public final int hashCode() {
            return this.touchData.hashCode() + KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0.m(this.pointerOnSensorId, this.event.hashCode() * 31, 31);
        }

        public final String toString() {
            return "ProcessedTouch(event=" + this.event + ", pointerOnSensorId=" + this.pointerOnSensorId + ", touchData=" + this.touchData + ")";
        }
    }
}
