package androidx.compose.material3;

import androidx.compose.animation.Scale$$ExternalSyntheticOutline0;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerImpl;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.OpaqueKey;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.ui.graphics.Color;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class DefaultDrawerItemsColor implements NavigationDrawerItemColors {
    public final long selectedBadgeColor;
    public final long selectedContainerColor;
    public final long selectedIconColor;
    public final long selectedTextColor;
    public final long unselectedBadgeColor;
    public final long unselectedContainerColor;
    public final long unselectedIconColor;
    public final long unselectedTextColor;

    public DefaultDrawerItemsColor(long j, long j2, long j3, long j4, long j5, long j6, long j7, long j8) {
        this.selectedIconColor = j;
        this.unselectedIconColor = j2;
        this.selectedTextColor = j3;
        this.unselectedTextColor = j4;
        this.selectedContainerColor = j5;
        this.unselectedContainerColor = j6;
        this.selectedBadgeColor = j7;
        this.unselectedBadgeColor = j8;
    }

    @Override // androidx.compose.material3.NavigationDrawerItemColors
    public final MutableState containerColor(boolean z, Composer composer) {
        ComposerImpl composerImpl = (ComposerImpl) composer;
        composerImpl.startReplaceGroup(-433512770);
        OpaqueKey opaqueKey = ComposerKt.invocation;
        MutableState rememberUpdatedState = SnapshotStateKt.rememberUpdatedState(new Color(z ? this.selectedContainerColor : this.unselectedContainerColor), composerImpl);
        composerImpl.end(false);
        return rememberUpdatedState;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof DefaultDrawerItemsColor)) {
            return false;
        }
        DefaultDrawerItemsColor defaultDrawerItemsColor = (DefaultDrawerItemsColor) obj;
        if (Color.m363equalsimpl0(this.selectedIconColor, defaultDrawerItemsColor.selectedIconColor) && Color.m363equalsimpl0(this.unselectedIconColor, defaultDrawerItemsColor.unselectedIconColor) && Color.m363equalsimpl0(this.selectedTextColor, defaultDrawerItemsColor.selectedTextColor) && Color.m363equalsimpl0(this.unselectedTextColor, defaultDrawerItemsColor.unselectedTextColor) && Color.m363equalsimpl0(this.selectedContainerColor, defaultDrawerItemsColor.selectedContainerColor) && Color.m363equalsimpl0(this.unselectedContainerColor, defaultDrawerItemsColor.unselectedContainerColor) && Color.m363equalsimpl0(this.selectedBadgeColor, defaultDrawerItemsColor.selectedBadgeColor)) {
            return Color.m363equalsimpl0(this.unselectedBadgeColor, defaultDrawerItemsColor.unselectedBadgeColor);
        }
        return false;
    }

    public final int hashCode() {
        int i = Color.$r8$clinit;
        return Long.hashCode(this.unselectedBadgeColor) + Scale$$ExternalSyntheticOutline0.m(Scale$$ExternalSyntheticOutline0.m(Scale$$ExternalSyntheticOutline0.m(Scale$$ExternalSyntheticOutline0.m(Scale$$ExternalSyntheticOutline0.m(Scale$$ExternalSyntheticOutline0.m(Long.hashCode(this.selectedIconColor) * 31, 31, this.unselectedIconColor), 31, this.selectedTextColor), 31, this.unselectedTextColor), 31, this.selectedContainerColor), 31, this.unselectedContainerColor), 31, this.selectedBadgeColor);
    }

    @Override // androidx.compose.material3.NavigationDrawerItemColors
    public final MutableState iconColor(boolean z, Composer composer) {
        ComposerImpl composerImpl = (ComposerImpl) composer;
        composerImpl.startReplaceGroup(1141354218);
        OpaqueKey opaqueKey = ComposerKt.invocation;
        MutableState rememberUpdatedState = SnapshotStateKt.rememberUpdatedState(new Color(z ? this.selectedIconColor : this.unselectedIconColor), composerImpl);
        composerImpl.end(false);
        return rememberUpdatedState;
    }

    @Override // androidx.compose.material3.NavigationDrawerItemColors
    public final MutableState textColor(boolean z, Composer composer) {
        ComposerImpl composerImpl = (ComposerImpl) composer;
        composerImpl.startReplaceGroup(1275109558);
        OpaqueKey opaqueKey = ComposerKt.invocation;
        MutableState rememberUpdatedState = SnapshotStateKt.rememberUpdatedState(new Color(z ? this.selectedTextColor : this.unselectedTextColor), composerImpl);
        composerImpl.end(false);
        return rememberUpdatedState;
    }
}
