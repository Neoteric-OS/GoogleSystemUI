package com.android.systemui.media.controls.ui.viewmodel;

import androidx.compose.animation.ChangeSize$$ExternalSyntheticOutline0;
import androidx.compose.animation.TransitionData$$ExternalSyntheticOutline0;
import com.android.internal.logging.InstanceId;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class MediaCommonViewModel {

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class MediaControl extends MediaCommonViewModel {
        public final MediaControlViewModel controlViewModel;
        public final boolean immediatelyUpdateUi;
        public final InstanceId instanceId;
        public final boolean isMediaFromRec;
        public final Function1 onAdded;
        public final Function1 onRemoved;
        public final Function1 onUpdated;
        public final long updateTime;

        public MediaControl(InstanceId instanceId, boolean z, MediaControlViewModel mediaControlViewModel, Function1 function1, Function1 function12, Function1 function13, boolean z2, long j) {
            this.instanceId = instanceId;
            this.immediatelyUpdateUi = z;
            this.controlViewModel = mediaControlViewModel;
            this.onAdded = function1;
            this.onRemoved = function12;
            this.onUpdated = function13;
            this.isMediaFromRec = z2;
            this.updateTime = j;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof MediaControl)) {
                return false;
            }
            MediaControl mediaControl = (MediaControl) obj;
            return Intrinsics.areEqual(this.instanceId, mediaControl.instanceId) && this.immediatelyUpdateUi == mediaControl.immediatelyUpdateUi && Intrinsics.areEqual(this.controlViewModel, mediaControl.controlViewModel) && Intrinsics.areEqual(this.onAdded, mediaControl.onAdded) && Intrinsics.areEqual(this.onRemoved, mediaControl.onRemoved) && Intrinsics.areEqual(this.onUpdated, mediaControl.onUpdated) && this.isMediaFromRec == mediaControl.isMediaFromRec && this.updateTime == mediaControl.updateTime;
        }

        @Override // com.android.systemui.media.controls.ui.viewmodel.MediaCommonViewModel
        public final Function1 getOnRemoved() {
            return this.onRemoved;
        }

        public final int hashCode() {
            return Long.hashCode(this.updateTime) + TransitionData$$ExternalSyntheticOutline0.m(ChangeSize$$ExternalSyntheticOutline0.m(this.onUpdated, ChangeSize$$ExternalSyntheticOutline0.m(this.onRemoved, ChangeSize$$ExternalSyntheticOutline0.m(this.onAdded, (this.controlViewModel.hashCode() + TransitionData$$ExternalSyntheticOutline0.m(this.instanceId.hashCode() * 31, 31, this.immediatelyUpdateUi)) * 31, 31), 31), 31), 31, this.isMediaFromRec);
        }

        public final String toString() {
            return "MediaControl(instanceId=" + this.instanceId + ", immediatelyUpdateUi=" + this.immediatelyUpdateUi + ", controlViewModel=" + this.controlViewModel + ", onAdded=" + this.onAdded + ", onRemoved=" + this.onRemoved + ", onUpdated=" + this.onUpdated + ", isMediaFromRec=" + this.isMediaFromRec + ", updateTime=" + this.updateTime + ")";
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class MediaRecommendations extends MediaCommonViewModel {
        public final String key;
        public final boolean loadingEnabled;
        public final Function1 onAdded;
        public final Function1 onRemoved;
        public final Function1 onUpdated;
        public final MediaRecommendationsViewModel recsViewModel;

        public MediaRecommendations(String str, boolean z, MediaRecommendationsViewModel mediaRecommendationsViewModel, Function1 function1, Function1 function12, Function1 function13) {
            this.key = str;
            this.loadingEnabled = z;
            this.recsViewModel = mediaRecommendationsViewModel;
            this.onAdded = function1;
            this.onRemoved = function12;
            this.onUpdated = function13;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof MediaRecommendations)) {
                return false;
            }
            MediaRecommendations mediaRecommendations = (MediaRecommendations) obj;
            return Intrinsics.areEqual(this.key, mediaRecommendations.key) && this.loadingEnabled == mediaRecommendations.loadingEnabled && Intrinsics.areEqual(this.recsViewModel, mediaRecommendations.recsViewModel) && Intrinsics.areEqual(this.onAdded, mediaRecommendations.onAdded) && Intrinsics.areEqual(this.onRemoved, mediaRecommendations.onRemoved) && Intrinsics.areEqual(this.onUpdated, mediaRecommendations.onUpdated);
        }

        @Override // com.android.systemui.media.controls.ui.viewmodel.MediaCommonViewModel
        public final Function1 getOnRemoved() {
            return this.onRemoved;
        }

        public final int hashCode() {
            return this.onUpdated.hashCode() + ChangeSize$$ExternalSyntheticOutline0.m(this.onRemoved, ChangeSize$$ExternalSyntheticOutline0.m(this.onAdded, (this.recsViewModel.hashCode() + TransitionData$$ExternalSyntheticOutline0.m(this.key.hashCode() * 31, 31, this.loadingEnabled)) * 31, 31), 31);
        }

        public final String toString() {
            return "MediaRecommendations(key=" + this.key + ", loadingEnabled=" + this.loadingEnabled + ", recsViewModel=" + this.recsViewModel + ", onAdded=" + this.onAdded + ", onRemoved=" + this.onRemoved + ", onUpdated=" + this.onUpdated + ")";
        }
    }

    public abstract Function1 getOnRemoved();
}
