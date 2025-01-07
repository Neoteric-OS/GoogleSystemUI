package com.android.systemui.qs.composefragment.ui;

import androidx.appsearch.safeparcel.PropertyConfigParcel$JoinableConfigParcel$$ExternalSyntheticOutline0;
import androidx.compose.animation.core.KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.node.ModifierNodeElement;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
final class NotificationScrimClipElement extends ModifierNodeElement {
    public final int bottom;
    public final int leftInset;
    public final int radius;
    public final int rightInset;
    public final int top;

    public NotificationScrimClipElement(int i, int i2, int i3, int i4, int i5) {
        this.leftInset = i;
        this.top = i2;
        this.rightInset = i3;
        this.bottom = i4;
        this.radius = i5;
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    public final Modifier.Node create() {
        return new NotificationScrimClipNode(this.leftInset, this.top, this.rightInset, this.bottom, this.radius);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof NotificationScrimClipElement)) {
            return false;
        }
        NotificationScrimClipElement notificationScrimClipElement = (NotificationScrimClipElement) obj;
        return this.leftInset == notificationScrimClipElement.leftInset && this.top == notificationScrimClipElement.top && this.rightInset == notificationScrimClipElement.rightInset && this.bottom == notificationScrimClipElement.bottom && this.radius == notificationScrimClipElement.radius;
    }

    public final int hashCode() {
        return Integer.hashCode(this.radius) + KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0.m(this.bottom, KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0.m(this.rightInset, KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0.m(this.top, Integer.hashCode(this.leftInset) * 31, 31), 31), 31);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("NotificationScrimClipElement(leftInset=");
        sb.append(this.leftInset);
        sb.append(", top=");
        sb.append(this.top);
        sb.append(", rightInset=");
        sb.append(this.rightInset);
        sb.append(", bottom=");
        sb.append(this.bottom);
        sb.append(", radius=");
        return PropertyConfigParcel$JoinableConfigParcel$$ExternalSyntheticOutline0.m(sb, this.radius, ")");
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    public final void update(Modifier.Node node) {
        NotificationScrimClipNode notificationScrimClipNode = (NotificationScrimClipNode) node;
        float f = notificationScrimClipNode.leftInset;
        float f2 = this.leftInset;
        int i = this.radius;
        int i2 = this.bottom;
        int i3 = this.rightInset;
        int i4 = this.top;
        if (f == f2 && notificationScrimClipNode.top == i4 && notificationScrimClipNode.rightInset == i3 && notificationScrimClipNode.bottom == i2 && notificationScrimClipNode.radius == i) {
            return;
        }
        notificationScrimClipNode.leftInset = f2;
        notificationScrimClipNode.top = i4;
        notificationScrimClipNode.rightInset = i3;
        notificationScrimClipNode.bottom = i2;
        notificationScrimClipNode.radius = i;
        notificationScrimClipNode.invalidated = true;
    }
}
