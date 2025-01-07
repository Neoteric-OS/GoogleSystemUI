package com.android.systemui.media.controls.ui.viewmodel;

import androidx.compose.animation.ChangeSize$$ExternalSyntheticOutline0;
import androidx.compose.animation.TransitionData$$ExternalSyntheticOutline0;
import androidx.compose.animation.graphics.vector.PropertyValuesHolder2D$$ExternalSyntheticOutline0;
import java.util.List;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class MediaRecsCardViewModel {
    public final boolean areSubtitlesVisible;
    public final boolean areTitlesVisible;
    public final Function1 contentDescription;
    public final GutsViewModel gutsMenu;
    public final List mediaRecs;
    public final Function1 onClicked;
    public final Function1 onLocationChanged;
    public final Function0 onLongClicked;

    public MediaRecsCardViewModel(Function1 function1, Function1 function12, Function0 function0, List list, boolean z, boolean z2, GutsViewModel gutsViewModel, Function1 function13) {
        this.contentDescription = function1;
        this.onClicked = function12;
        this.onLongClicked = function0;
        this.mediaRecs = list;
        this.areTitlesVisible = z;
        this.areSubtitlesVisible = z2;
        this.gutsMenu = gutsViewModel;
        this.onLocationChanged = function13;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof MediaRecsCardViewModel)) {
            return false;
        }
        MediaRecsCardViewModel mediaRecsCardViewModel = (MediaRecsCardViewModel) obj;
        return Intrinsics.areEqual(this.contentDescription, mediaRecsCardViewModel.contentDescription) && Intrinsics.areEqual(this.onClicked, mediaRecsCardViewModel.onClicked) && Intrinsics.areEqual(this.onLongClicked, mediaRecsCardViewModel.onLongClicked) && Intrinsics.areEqual(this.mediaRecs, mediaRecsCardViewModel.mediaRecs) && this.areTitlesVisible == mediaRecsCardViewModel.areTitlesVisible && this.areSubtitlesVisible == mediaRecsCardViewModel.areSubtitlesVisible && Intrinsics.areEqual(this.gutsMenu, mediaRecsCardViewModel.gutsMenu) && Intrinsics.areEqual(this.onLocationChanged, mediaRecsCardViewModel.onLocationChanged);
    }

    public final int hashCode() {
        return this.onLocationChanged.hashCode() + ((this.gutsMenu.hashCode() + TransitionData$$ExternalSyntheticOutline0.m(TransitionData$$ExternalSyntheticOutline0.m(PropertyValuesHolder2D$$ExternalSyntheticOutline0.m((this.onLongClicked.hashCode() + ChangeSize$$ExternalSyntheticOutline0.m(this.onClicked, this.contentDescription.hashCode() * 31, 31)) * 31, 31, this.mediaRecs), 31, this.areTitlesVisible), 31, this.areSubtitlesVisible)) * 31);
    }

    public final String toString() {
        return "MediaRecsCardViewModel(contentDescription=" + this.contentDescription + ", onClicked=" + this.onClicked + ", onLongClicked=" + this.onLongClicked + ", mediaRecs=" + this.mediaRecs + ", areTitlesVisible=" + this.areTitlesVisible + ", areSubtitlesVisible=" + this.areSubtitlesVisible + ", gutsMenu=" + this.gutsMenu + ", onLocationChanged=" + this.onLocationChanged + ")";
    }
}
