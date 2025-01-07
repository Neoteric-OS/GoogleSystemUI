package com.android.systemui.controls.settings;

import kotlin.jvm.functions.Function0;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class ControlsSettingsDialogManagerImpl$maybeShowDialog$1 implements Runnable {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ Object this$0;

    public /* synthetic */ ControlsSettingsDialogManagerImpl$maybeShowDialog$1(int i, Object obj) {
        this.$r8$classId = i;
        this.this$0 = obj;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.$r8$classId) {
            case 0:
                ((ControlsSettingsDialogManagerImpl) this.this$0).dialog = null;
                break;
            default:
                ((Function0) this.this$0).invoke();
                break;
        }
    }
}
