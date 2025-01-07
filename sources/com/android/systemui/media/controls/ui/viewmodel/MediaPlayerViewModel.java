package com.android.systemui.media.controls.ui.viewmodel;

import android.graphics.drawable.Icon;
import androidx.compose.animation.ChangeSize$$ExternalSyntheticOutline0;
import androidx.compose.animation.TransitionData$$ExternalSyntheticOutline0;
import androidx.compose.animation.graphics.vector.PropertyValuesHolder2D$$ExternalSyntheticOutline0;
import java.util.List;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class MediaPlayerViewModel {
    public final List actionButtons;
    public final Icon appIcon;
    public final CharSequence artistName;
    public final Icon backgroundCover;
    public final boolean canShowTime;
    public final Function1 contentDescription;
    public final GutsViewModel gutsMenu;
    public final boolean isExplicitVisible;
    public final com.android.systemui.common.shared.model.Icon launcherIcon;
    public final Function1 onBindSeekbar;
    public final Function1 onClicked;
    public final Function1 onLocationChanged;
    public final Function0 onLongClicked;
    public final Function0 onSeek;
    public final MediaOutputSwitcherViewModel outputSwitcher;
    public final boolean playTurbulenceNoise;
    public final CharSequence titleName;
    public final boolean useGrayColorFilter;
    public final boolean useSemanticActions;

    public MediaPlayerViewModel(Function1 function1, Icon icon, Icon icon2, com.android.systemui.common.shared.model.Icon icon3, boolean z, CharSequence charSequence, CharSequence charSequence2, boolean z2, boolean z3, boolean z4, boolean z5, List list, MediaOutputSwitcherViewModel mediaOutputSwitcherViewModel, GutsViewModel gutsViewModel, Function1 function12, Function0 function0, Function0 function02, Function1 function13, Function1 function14) {
        this.contentDescription = function1;
        this.backgroundCover = icon;
        this.appIcon = icon2;
        this.launcherIcon = icon3;
        this.useGrayColorFilter = z;
        this.artistName = charSequence;
        this.titleName = charSequence2;
        this.isExplicitVisible = z2;
        this.canShowTime = z3;
        this.playTurbulenceNoise = z4;
        this.useSemanticActions = z5;
        this.actionButtons = list;
        this.outputSwitcher = mediaOutputSwitcherViewModel;
        this.gutsMenu = gutsViewModel;
        this.onClicked = function12;
        this.onLongClicked = function0;
        this.onSeek = function02;
        this.onBindSeekbar = function13;
        this.onLocationChanged = function14;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof MediaPlayerViewModel)) {
            return false;
        }
        MediaPlayerViewModel mediaPlayerViewModel = (MediaPlayerViewModel) obj;
        return Intrinsics.areEqual(this.contentDescription, mediaPlayerViewModel.contentDescription) && Intrinsics.areEqual(this.backgroundCover, mediaPlayerViewModel.backgroundCover) && Intrinsics.areEqual(this.appIcon, mediaPlayerViewModel.appIcon) && Intrinsics.areEqual(this.launcherIcon, mediaPlayerViewModel.launcherIcon) && this.useGrayColorFilter == mediaPlayerViewModel.useGrayColorFilter && Intrinsics.areEqual(this.artistName, mediaPlayerViewModel.artistName) && Intrinsics.areEqual(this.titleName, mediaPlayerViewModel.titleName) && this.isExplicitVisible == mediaPlayerViewModel.isExplicitVisible && this.canShowTime == mediaPlayerViewModel.canShowTime && this.playTurbulenceNoise == mediaPlayerViewModel.playTurbulenceNoise && this.useSemanticActions == mediaPlayerViewModel.useSemanticActions && Intrinsics.areEqual(this.actionButtons, mediaPlayerViewModel.actionButtons) && Intrinsics.areEqual(this.outputSwitcher, mediaPlayerViewModel.outputSwitcher) && Intrinsics.areEqual(this.gutsMenu, mediaPlayerViewModel.gutsMenu) && Intrinsics.areEqual(this.onClicked, mediaPlayerViewModel.onClicked) && Intrinsics.areEqual(this.onLongClicked, mediaPlayerViewModel.onLongClicked) && Intrinsics.areEqual(this.onSeek, mediaPlayerViewModel.onSeek) && Intrinsics.areEqual(this.onBindSeekbar, mediaPlayerViewModel.onBindSeekbar) && Intrinsics.areEqual(this.onLocationChanged, mediaPlayerViewModel.onLocationChanged);
    }

    public final int hashCode() {
        int hashCode = this.contentDescription.hashCode() * 31;
        Icon icon = this.backgroundCover;
        int hashCode2 = (hashCode + (icon == null ? 0 : icon.hashCode())) * 31;
        Icon icon2 = this.appIcon;
        return this.onLocationChanged.hashCode() + ChangeSize$$ExternalSyntheticOutline0.m(this.onBindSeekbar, (this.onSeek.hashCode() + ((this.onLongClicked.hashCode() + ChangeSize$$ExternalSyntheticOutline0.m(this.onClicked, (this.gutsMenu.hashCode() + ((this.outputSwitcher.hashCode() + PropertyValuesHolder2D$$ExternalSyntheticOutline0.m(TransitionData$$ExternalSyntheticOutline0.m(TransitionData$$ExternalSyntheticOutline0.m(TransitionData$$ExternalSyntheticOutline0.m(TransitionData$$ExternalSyntheticOutline0.m((this.titleName.hashCode() + ((this.artistName.hashCode() + TransitionData$$ExternalSyntheticOutline0.m((this.launcherIcon.hashCode() + ((hashCode2 + (icon2 != null ? icon2.hashCode() : 0)) * 31)) * 31, 31, this.useGrayColorFilter)) * 31)) * 31, 31, this.isExplicitVisible), 31, this.canShowTime), 31, this.playTurbulenceNoise), 31, this.useSemanticActions), 31, this.actionButtons)) * 31)) * 31, 31)) * 31)) * 31, 31);
    }

    public final String toString() {
        Icon icon = this.backgroundCover;
        Icon icon2 = this.appIcon;
        CharSequence charSequence = this.artistName;
        CharSequence charSequence2 = this.titleName;
        return "MediaPlayerViewModel(contentDescription=" + this.contentDescription + ", backgroundCover=" + icon + ", appIcon=" + icon2 + ", launcherIcon=" + this.launcherIcon + ", useGrayColorFilter=" + this.useGrayColorFilter + ", artistName=" + ((Object) charSequence) + ", titleName=" + ((Object) charSequence2) + ", isExplicitVisible=" + this.isExplicitVisible + ", canShowTime=" + this.canShowTime + ", playTurbulenceNoise=" + this.playTurbulenceNoise + ", useSemanticActions=" + this.useSemanticActions + ", actionButtons=" + this.actionButtons + ", outputSwitcher=" + this.outputSwitcher + ", gutsMenu=" + this.gutsMenu + ", onClicked=" + this.onClicked + ", onLongClicked=" + this.onLongClicked + ", onSeek=" + this.onSeek + ", onBindSeekbar=" + this.onBindSeekbar + ", onLocationChanged=" + this.onLocationChanged + ")";
    }
}
