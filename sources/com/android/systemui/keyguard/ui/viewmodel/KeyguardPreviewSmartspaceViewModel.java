package com.android.systemui.keyguard.ui.viewmodel;

import android.content.Context;
import android.content.res.Resources;
import com.android.internal.policy.SystemBarUtils;
import com.android.systemui.keyguard.domain.interactor.KeyguardClockInteractor;
import com.android.wm.shell.R;
import kotlinx.coroutines.flow.FlowKt__ZipKt$combine$$inlined$unsafeFlow$1;
import kotlinx.coroutines.flow.ReadonlyStateFlow;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class KeyguardPreviewSmartspaceViewModel {
    public final ReadonlyStateFlow selectedClockSize;
    public final KeyguardPreviewSmartspaceViewModel$special$$inlined$map$1 shouldHideSmartspace;

    public KeyguardPreviewSmartspaceViewModel(KeyguardClockInteractor keyguardClockInteractor) {
        ReadonlyStateFlow readonlyStateFlow = keyguardClockInteractor.selectedClockSize;
        this.selectedClockSize = readonlyStateFlow;
        this.shouldHideSmartspace = new KeyguardPreviewSmartspaceViewModel$special$$inlined$map$1(new FlowKt__ZipKt$combine$$inlined$unsafeFlow$1(readonlyStateFlow, keyguardClockInteractor.currentClockId, KeyguardPreviewSmartspaceViewModel$shouldHideSmartspace$2.INSTANCE));
    }

    public static int getSmallClockTopPadding(Context context, boolean z) {
        Resources resources = context.getResources();
        if (z) {
            return resources.getDimensionPixelSize(R.dimen.keyguard_split_shade_top_margin);
        }
        return SystemBarUtils.getStatusBarHeight(context) + resources.getDimensionPixelSize(R.dimen.keyguard_clock_top_margin) + resources.getDimensionPixelSize(R.dimen.keyguard_smartspace_top_offset);
    }
}
