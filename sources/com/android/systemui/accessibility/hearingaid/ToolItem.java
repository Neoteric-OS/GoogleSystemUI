package com.android.systemui.accessibility.hearingaid;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class ToolItem {
    public final Drawable toolIcon;
    public final Intent toolIntent;
    public final String toolName;

    public ToolItem(String str, Drawable drawable, Intent intent) {
        this.toolName = str;
        this.toolIcon = drawable;
        this.toolIntent = intent;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ToolItem)) {
            return false;
        }
        ToolItem toolItem = (ToolItem) obj;
        return Intrinsics.areEqual(this.toolName, toolItem.toolName) && Intrinsics.areEqual(this.toolIcon, toolItem.toolIcon) && Intrinsics.areEqual(this.toolIntent, toolItem.toolIntent);
    }

    public final int hashCode() {
        return this.toolIntent.hashCode() + ((this.toolIcon.hashCode() + (this.toolName.hashCode() * 31)) * 31);
    }

    public final String toString() {
        return "ToolItem(toolName=" + this.toolName + ", toolIcon=" + this.toolIcon + ", toolIntent=" + this.toolIntent + ")";
    }
}
