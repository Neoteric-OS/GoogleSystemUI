package com.android.systemui.statusbar.events;

import android.graphics.Rect;
import android.view.View;
import androidx.activity.ComponentActivity$activityResultRegistry$1$$ExternalSyntheticOutline0;
import androidx.appsearch.app.GenericDocument$$ExternalSyntheticOutline0;
import androidx.compose.animation.TransitionData$$ExternalSyntheticOutline0;
import androidx.compose.animation.core.KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class ViewState {
    public final String contentDescription;
    public final int cornerIndex;
    public final View designatedCorner;
    public final Rect landscapeRect;
    public final boolean layoutRtl;
    public final int paddingTop;
    public final Rect portraitRect;
    public final boolean qsExpanded;
    public final int rotation;
    public final Rect seascapeRect;
    public final boolean shadeExpanded;
    public final boolean systemPrivacyEventIsActive;
    public final Rect upsideDownRect;
    public final boolean viewInitialized;

    public ViewState(boolean z, boolean z2, boolean z3, boolean z4, Rect rect, Rect rect2, Rect rect3, Rect rect4, boolean z5, int i, int i2, int i3, View view, String str) {
        this.viewInitialized = z;
        this.systemPrivacyEventIsActive = z2;
        this.shadeExpanded = z3;
        this.qsExpanded = z4;
        this.portraitRect = rect;
        this.landscapeRect = rect2;
        this.upsideDownRect = rect3;
        this.seascapeRect = rect4;
        this.layoutRtl = z5;
        this.rotation = i;
        this.paddingTop = i2;
        this.cornerIndex = i3;
        this.designatedCorner = view;
        this.contentDescription = str;
    }

    public static ViewState copy$default(ViewState viewState, boolean z, boolean z2, boolean z3, Rect rect, Rect rect2, Rect rect3, Rect rect4, boolean z4, int i, int i2, int i3, View view, String str, int i4) {
        boolean z5 = (i4 & 1) != 0 ? viewState.viewInitialized : true;
        boolean z6 = (i4 & 2) != 0 ? viewState.systemPrivacyEventIsActive : z;
        boolean z7 = (i4 & 4) != 0 ? viewState.shadeExpanded : z2;
        boolean z8 = (i4 & 8) != 0 ? viewState.qsExpanded : z3;
        Rect rect5 = (i4 & 16) != 0 ? viewState.portraitRect : rect;
        Rect rect6 = (i4 & 32) != 0 ? viewState.landscapeRect : rect2;
        Rect rect7 = (i4 & 64) != 0 ? viewState.upsideDownRect : rect3;
        Rect rect8 = (i4 & 128) != 0 ? viewState.seascapeRect : rect4;
        boolean z9 = (i4 & 256) != 0 ? viewState.layoutRtl : z4;
        int i5 = (i4 & 512) != 0 ? viewState.rotation : i;
        int i6 = (i4 & 1024) != 0 ? viewState.paddingTop : i2;
        int i7 = (i4 & 2048) != 0 ? viewState.cornerIndex : i3;
        View view2 = (i4 & 4096) != 0 ? viewState.designatedCorner : view;
        String str2 = (i4 & 8192) != 0 ? viewState.contentDescription : str;
        viewState.getClass();
        return new ViewState(z5, z6, z7, z8, rect5, rect6, rect7, rect8, z9, i5, i6, i7, view2, str2);
    }

    public final Rect contentRectForRotation(int i) {
        if (i == 0) {
            Rect rect = this.portraitRect;
            Intrinsics.checkNotNull(rect);
            return rect;
        }
        if (i == 1) {
            Rect rect2 = this.landscapeRect;
            Intrinsics.checkNotNull(rect2);
            return rect2;
        }
        if (i == 2) {
            Rect rect3 = this.upsideDownRect;
            Intrinsics.checkNotNull(rect3);
            return rect3;
        }
        if (i != 3) {
            throw new IllegalArgumentException(GenericDocument$$ExternalSyntheticOutline0.m("not a rotation (", ")", i));
        }
        Rect rect4 = this.seascapeRect;
        Intrinsics.checkNotNull(rect4);
        return rect4;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ViewState)) {
            return false;
        }
        ViewState viewState = (ViewState) obj;
        return this.viewInitialized == viewState.viewInitialized && this.systemPrivacyEventIsActive == viewState.systemPrivacyEventIsActive && this.shadeExpanded == viewState.shadeExpanded && this.qsExpanded == viewState.qsExpanded && Intrinsics.areEqual(this.portraitRect, viewState.portraitRect) && Intrinsics.areEqual(this.landscapeRect, viewState.landscapeRect) && Intrinsics.areEqual(this.upsideDownRect, viewState.upsideDownRect) && Intrinsics.areEqual(this.seascapeRect, viewState.seascapeRect) && this.layoutRtl == viewState.layoutRtl && this.rotation == viewState.rotation && this.paddingTop == viewState.paddingTop && this.cornerIndex == viewState.cornerIndex && Intrinsics.areEqual(this.designatedCorner, viewState.designatedCorner) && Intrinsics.areEqual(this.contentDescription, viewState.contentDescription);
    }

    public final int hashCode() {
        int m = TransitionData$$ExternalSyntheticOutline0.m(TransitionData$$ExternalSyntheticOutline0.m(TransitionData$$ExternalSyntheticOutline0.m(Boolean.hashCode(this.viewInitialized) * 31, 31, this.systemPrivacyEventIsActive), 31, this.shadeExpanded), 31, this.qsExpanded);
        Rect rect = this.portraitRect;
        int hashCode = (m + (rect == null ? 0 : rect.hashCode())) * 31;
        Rect rect2 = this.landscapeRect;
        int hashCode2 = (hashCode + (rect2 == null ? 0 : rect2.hashCode())) * 31;
        Rect rect3 = this.upsideDownRect;
        int hashCode3 = (hashCode2 + (rect3 == null ? 0 : rect3.hashCode())) * 31;
        Rect rect4 = this.seascapeRect;
        int m2 = KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0.m(this.cornerIndex, KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0.m(this.paddingTop, KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0.m(this.rotation, TransitionData$$ExternalSyntheticOutline0.m((hashCode3 + (rect4 == null ? 0 : rect4.hashCode())) * 31, 31, this.layoutRtl), 31), 31), 31);
        View view = this.designatedCorner;
        int hashCode4 = (m2 + (view == null ? 0 : view.hashCode())) * 31;
        String str = this.contentDescription;
        return hashCode4 + (str != null ? str.hashCode() : 0);
    }

    public final boolean shouldShowDot() {
        return (!this.systemPrivacyEventIsActive || this.shadeExpanded || this.qsExpanded) ? false : true;
    }

    public final String toString() {
        Rect rect = this.portraitRect;
        Rect rect2 = this.landscapeRect;
        Rect rect3 = this.upsideDownRect;
        Rect rect4 = this.seascapeRect;
        View view = this.designatedCorner;
        StringBuilder sb = new StringBuilder("ViewState(viewInitialized=");
        sb.append(this.viewInitialized);
        sb.append(", systemPrivacyEventIsActive=");
        sb.append(this.systemPrivacyEventIsActive);
        sb.append(", shadeExpanded=");
        sb.append(this.shadeExpanded);
        sb.append(", qsExpanded=");
        sb.append(this.qsExpanded);
        sb.append(", portraitRect=");
        sb.append(rect);
        sb.append(", landscapeRect=");
        sb.append(rect2);
        sb.append(", upsideDownRect=");
        sb.append(rect3);
        sb.append(", seascapeRect=");
        sb.append(rect4);
        sb.append(", layoutRtl=");
        sb.append(this.layoutRtl);
        sb.append(", rotation=");
        sb.append(this.rotation);
        sb.append(", paddingTop=");
        sb.append(this.paddingTop);
        sb.append(", cornerIndex=");
        sb.append(this.cornerIndex);
        sb.append(", designatedCorner=");
        sb.append(view);
        sb.append(", contentDescription=");
        return ComponentActivity$activityResultRegistry$1$$ExternalSyntheticOutline0.m(sb, this.contentDescription, ")");
    }
}
