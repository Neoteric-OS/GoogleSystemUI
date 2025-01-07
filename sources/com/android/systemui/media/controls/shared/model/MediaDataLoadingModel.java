package com.android.systemui.media.controls.shared.model;

import androidx.compose.animation.TransitionData$$ExternalSyntheticOutline0;
import androidx.compose.animation.core.KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0;
import com.android.internal.logging.InstanceId;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class MediaDataLoadingModel {

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Loaded extends MediaDataLoadingModel {
        public final boolean immediatelyUpdateUi;
        public final InstanceId instanceId;
        public final boolean isSsReactivated;
        public final int receivedSmartspaceCardLatency;

        public Loaded(InstanceId instanceId, boolean z, int i, int i2) {
            z = (i2 & 2) != 0 ? true : z;
            i = (i2 & 4) != 0 ? 0 : i;
            boolean z2 = (i2 & 8) == 0;
            this.instanceId = instanceId;
            this.immediatelyUpdateUi = z;
            this.receivedSmartspaceCardLatency = i;
            this.isSsReactivated = z2;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof Loaded)) {
                return false;
            }
            Loaded loaded = (Loaded) obj;
            return Intrinsics.areEqual(this.instanceId, loaded.instanceId) && this.immediatelyUpdateUi == loaded.immediatelyUpdateUi && this.receivedSmartspaceCardLatency == loaded.receivedSmartspaceCardLatency && this.isSsReactivated == loaded.isSsReactivated;
        }

        @Override // com.android.systemui.media.controls.shared.model.MediaDataLoadingModel
        public final InstanceId getInstanceId() {
            return this.instanceId;
        }

        public final int hashCode() {
            return Boolean.hashCode(this.isSsReactivated) + KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0.m(this.receivedSmartspaceCardLatency, TransitionData$$ExternalSyntheticOutline0.m(this.instanceId.hashCode() * 31, 31, this.immediatelyUpdateUi), 31);
        }

        public final String toString() {
            return "Loaded(instanceId=" + this.instanceId + ", immediatelyUpdateUi=" + this.immediatelyUpdateUi + ", receivedSmartspaceCardLatency=" + this.receivedSmartspaceCardLatency + ", isSsReactivated=" + this.isSsReactivated + ")";
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Removed extends MediaDataLoadingModel {
        public final InstanceId instanceId;

        public Removed(InstanceId instanceId) {
            this.instanceId = instanceId;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return (obj instanceof Removed) && Intrinsics.areEqual(this.instanceId, ((Removed) obj).instanceId);
        }

        @Override // com.android.systemui.media.controls.shared.model.MediaDataLoadingModel
        public final InstanceId getInstanceId() {
            return this.instanceId;
        }

        public final int hashCode() {
            return this.instanceId.hashCode();
        }

        public final String toString() {
            return "Removed(instanceId=" + this.instanceId + ")";
        }
    }

    public abstract InstanceId getInstanceId();
}
