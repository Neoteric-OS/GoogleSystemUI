package com.android.systemui.media.controls.shared.model;

import androidx.compose.animation.TransitionData$$ExternalSyntheticOutline0;
import com.android.systemui.media.controls.shared.model.MediaDataLoadingModel;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class MediaCommonModel {

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class MediaControl extends MediaCommonModel {
        public final boolean canBeRemoved;
        public final boolean isMediaFromRec;
        public final MediaDataLoadingModel.Loaded mediaLoadedModel;
        public final long updateTime;

        public MediaControl(MediaDataLoadingModel.Loaded loaded, boolean z, boolean z2, long j) {
            this.mediaLoadedModel = loaded;
            this.canBeRemoved = z;
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
            return Intrinsics.areEqual(this.mediaLoadedModel, mediaControl.mediaLoadedModel) && this.canBeRemoved == mediaControl.canBeRemoved && this.isMediaFromRec == mediaControl.isMediaFromRec && this.updateTime == mediaControl.updateTime;
        }

        public final int hashCode() {
            return Long.hashCode(this.updateTime) + TransitionData$$ExternalSyntheticOutline0.m(TransitionData$$ExternalSyntheticOutline0.m(this.mediaLoadedModel.hashCode() * 31, 31, this.canBeRemoved), 31, this.isMediaFromRec);
        }

        public final String toString() {
            return "MediaControl(mediaLoadedModel=" + this.mediaLoadedModel + ", canBeRemoved=" + this.canBeRemoved + ", isMediaFromRec=" + this.isMediaFromRec + ", updateTime=" + this.updateTime + ")";
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class MediaRecommendations extends MediaCommonModel {
        public final SmartspaceMediaLoadingModel recsLoadingModel;

        public MediaRecommendations(SmartspaceMediaLoadingModel smartspaceMediaLoadingModel) {
            this.recsLoadingModel = smartspaceMediaLoadingModel;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return (obj instanceof MediaRecommendations) && Intrinsics.areEqual(this.recsLoadingModel, ((MediaRecommendations) obj).recsLoadingModel);
        }

        public final int hashCode() {
            return this.recsLoadingModel.hashCode();
        }

        public final String toString() {
            return "MediaRecommendations(recsLoadingModel=" + this.recsLoadingModel + ")";
        }
    }
}
