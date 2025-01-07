package com.android.wm.shell.pip.phone;

import android.graphics.Rect;
import com.android.wm.shell.common.pip.PipBoundsState;
import java.util.function.Consumer;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class PipMotionHelper$$ExternalSyntheticLambda1 implements Consumer {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ PipMotionHelper f$0;

    public /* synthetic */ PipMotionHelper$$ExternalSyntheticLambda1(PipMotionHelper pipMotionHelper, int i) {
        this.$r8$classId = i;
        this.f$0 = pipMotionHelper;
    }

    @Override // java.util.function.Consumer
    public final void accept(Object obj) {
        int i = this.$r8$classId;
        PipMotionHelper pipMotionHelper = this.f$0;
        switch (i) {
            case 0:
                pipMotionHelper.getClass();
                break;
            case 1:
                Rect rect = (Rect) obj;
                PipBoundsState pipBoundsState = pipMotionHelper.mPipBoundsState;
                if (!pipBoundsState.getBounds().equals(rect)) {
                    PhonePipMenuController phonePipMenuController = pipMotionHelper.mMenuController;
                    if (phonePipMenuController.isMenuVisible()) {
                        phonePipMenuController.mPipMenuView.mPipMenuIconsAlgorithm.getClass();
                    }
                    pipBoundsState.setBounds(rect);
                    break;
                }
                break;
            default:
                PhonePipMenuController phonePipMenuController2 = pipMotionHelper.mMenuController;
                if (phonePipMenuController2.isMenuVisible()) {
                    phonePipMenuController2.mPipMenuView.mPipMenuIconsAlgorithm.getClass();
                    break;
                }
                break;
        }
    }
}
