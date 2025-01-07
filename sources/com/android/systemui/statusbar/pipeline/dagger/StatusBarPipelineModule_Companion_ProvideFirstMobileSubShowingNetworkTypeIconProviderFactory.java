package com.android.systemui.statusbar.pipeline.dagger;

import com.android.systemui.statusbar.pipeline.mobile.ui.viewmodel.MobileIconsViewModel;
import dagger.internal.Provider;
import java.util.function.Supplier;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class StatusBarPipelineModule_Companion_ProvideFirstMobileSubShowingNetworkTypeIconProviderFactory implements Provider {
    /* JADX WARN: Type inference failed for: r0v0, types: [com.android.systemui.statusbar.pipeline.dagger.StatusBarPipelineModule$Companion$provideFirstMobileSubShowingNetworkTypeIconProvider$1] */
    public static StatusBarPipelineModule$Companion$provideFirstMobileSubShowingNetworkTypeIconProvider$1 provideFirstMobileSubShowingNetworkTypeIconProvider(final MobileIconsViewModel mobileIconsViewModel) {
        return new Supplier() { // from class: com.android.systemui.statusbar.pipeline.dagger.StatusBarPipelineModule$Companion$provideFirstMobileSubShowingNetworkTypeIconProvider$1
            @Override // java.util.function.Supplier
            public final Object get() {
                return MobileIconsViewModel.this.firstMobileSubShowingNetworkTypeIcon;
            }
        };
    }
}
