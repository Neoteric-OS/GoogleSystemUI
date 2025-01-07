package com.android.compose.animation.scene;

import androidx.activity.ComponentActivity$activityResultRegistry$1$$ExternalSyntheticOutline0;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class SceneKey extends ContentKey {
    public final ElementKey rootElementKey;
    public final String testTag;

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public SceneKey(java.lang.String r4) {
        /*
            r3 = this;
            java.lang.Object r0 = new java.lang.Object
            r0.<init>()
            r3.<init>(r0, r4)
            java.lang.String r1 = "scene:"
            java.lang.String r1 = r1.concat(r4)
            r3.testTag = r1
            com.android.compose.animation.scene.ElementKey r3 = new com.android.compose.animation.scene.ElementKey
            r1 = 0
            r2 = 12
            r3.<init>(r4, r0, r1, r2)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.compose.animation.scene.SceneKey.<init>(java.lang.String):void");
    }

    @Override // com.android.compose.animation.scene.ContentKey
    public final String getTestTag() {
        return this.testTag;
    }

    @Override // com.android.compose.animation.scene.Key
    public final String toString() {
        return ComponentActivity$activityResultRegistry$1$$ExternalSyntheticOutline0.m(new StringBuilder("SceneKey(debugName="), this.debugName, ")");
    }
}
