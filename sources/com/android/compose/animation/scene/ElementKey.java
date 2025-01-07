package com.android.compose.animation.scene;

import androidx.activity.ComponentActivity$activityResultRegistry$1$$ExternalSyntheticOutline0;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public class ElementKey extends Key implements ElementMatcher {
    public final ElementContentPicker contentPicker;
    public final boolean placeAllCopies;
    public final String testTag;

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public ElementKey(java.lang.String r2, java.lang.Object r3, com.android.compose.animation.scene.ElementContentPicker r4, int r5) {
        /*
            r1 = this;
            r0 = r5 & 2
            if (r0 == 0) goto L9
            java.lang.Object r3 = new java.lang.Object
            r3.<init>()
        L9:
            r0 = r5 & 4
            if (r0 == 0) goto Lf
            com.android.compose.animation.scene.HighestZIndexContentPicker r4 = com.android.compose.animation.scene.HighestZIndexContentPicker.INSTANCE
        Lf:
            r5 = r5 & 8
            if (r5 == 0) goto L15
            r5 = 0
            goto L16
        L15:
            r5 = 1
        L16:
            r1.<init>(r3, r2)
            r1.contentPicker = r4
            r1.placeAllCopies = r5
            java.lang.String r3 = "element:"
            java.lang.String r2 = r3.concat(r2)
            r1.testTag = r2
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.compose.animation.scene.ElementKey.<init>(java.lang.String, java.lang.Object, com.android.compose.animation.scene.ElementContentPicker, int):void");
    }

    @Override // com.android.compose.animation.scene.ElementMatcher
    public final boolean matches(ElementKey elementKey) {
        return elementKey.equals(this);
    }

    @Override // com.android.compose.animation.scene.Key
    public String toString() {
        return ComponentActivity$activityResultRegistry$1$$ExternalSyntheticOutline0.m(new StringBuilder("ElementKey(debugName="), this.debugName, ")");
    }

    public static /* synthetic */ void getTestTag$annotations() {
    }
}
