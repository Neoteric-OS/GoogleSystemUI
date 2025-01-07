package com.android.systemui.controls.controller;

import android.content.ComponentName;
import com.android.systemui.controls.management.ControlsFavoritingActivity$loadControls$1$1;
import com.android.systemui.controls.management.ControlsFavoritingActivity$loadControls$1$2;
import com.android.systemui.statusbar.policy.DeviceControlsControllerImpl$seedFavorites$2;
import java.util.List;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class ControlsControllerImpl$addFavorite$1 implements Runnable {
    public final /* synthetic */ ComponentName $componentName;
    public final /* synthetic */ Object $controlInfo;
    public final /* synthetic */ int $r8$classId = 0;
    public final /* synthetic */ Object $structureName;
    public final /* synthetic */ ControlsControllerImpl this$0;

    public ControlsControllerImpl$addFavorite$1(ComponentName componentName, CharSequence charSequence, ControlInfo controlInfo, ControlsControllerImpl controlsControllerImpl) {
        this.$componentName = componentName;
        this.$structureName = charSequence;
        this.$controlInfo = controlInfo;
        this.this$0 = controlsControllerImpl;
    }

    /* JADX WARN: Code restructure failed: missing block: B:63:0x018a, code lost:
    
        if (r4 != null) goto L56;
     */
    @Override // java.lang.Runnable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void run() {
        /*
            Method dump skipped, instructions count: 462
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.controls.controller.ControlsControllerImpl$addFavorite$1.run():void");
    }

    public ControlsControllerImpl$addFavorite$1(ComponentName componentName, List list, ControlsControllerImpl controlsControllerImpl, ControlsFavoritingActivity$loadControls$1$1 controlsFavoritingActivity$loadControls$1$1) {
        this.$componentName = componentName;
        this.$structureName = list;
        this.this$0 = controlsControllerImpl;
        this.$controlInfo = controlsFavoritingActivity$loadControls$1$1;
    }

    public ControlsControllerImpl$addFavorite$1(ControlsControllerImpl controlsControllerImpl, ComponentName componentName, ControlsFavoritingActivity$loadControls$1$1 controlsFavoritingActivity$loadControls$1$1, ControlsFavoritingActivity$loadControls$1$2 controlsFavoritingActivity$loadControls$1$2) {
        this.this$0 = controlsControllerImpl;
        this.$componentName = componentName;
        this.$structureName = controlsFavoritingActivity$loadControls$1$1;
        this.$controlInfo = controlsFavoritingActivity$loadControls$1$2;
    }

    public ControlsControllerImpl$addFavorite$1(DeviceControlsControllerImpl$seedFavorites$2 deviceControlsControllerImpl$seedFavorites$2, ComponentName componentName, ControlsControllerImpl controlsControllerImpl, List list) {
        this.$structureName = deviceControlsControllerImpl$seedFavorites$2;
        this.$componentName = componentName;
        this.this$0 = controlsControllerImpl;
        this.$controlInfo = list;
    }
}
