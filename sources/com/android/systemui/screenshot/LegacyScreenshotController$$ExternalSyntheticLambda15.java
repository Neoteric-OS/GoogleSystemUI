package com.android.systemui.screenshot;

import com.android.systemui.screenshot.ImageExporter;
import java.util.function.Consumer;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class LegacyScreenshotController$$ExternalSyntheticLambda15 implements Consumer {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ Object f$0;

    public /* synthetic */ LegacyScreenshotController$$ExternalSyntheticLambda15(int i, Object obj) {
        this.$r8$classId = i;
        this.f$0 = obj;
    }

    @Override // java.util.function.Consumer
    public final void accept(Object obj) {
        int i = this.$r8$classId;
        Object obj2 = this.f$0;
        switch (i) {
            case 0:
                LegacyScreenshotController legacyScreenshotController = (LegacyScreenshotController) obj2;
                legacyScreenshotController.getClass();
                if (((ImageExporter.Result) obj).uri != null) {
                    legacyScreenshotController.mScreenshotHandler.post(new LegacyScreenshotController$$ExternalSyntheticLambda9(2, legacyScreenshotController));
                    break;
                }
                break;
            default:
                ((ScreenshotShelfViewProxy) obj2).view.announceForAccessibility((String) obj);
                break;
        }
    }
}
