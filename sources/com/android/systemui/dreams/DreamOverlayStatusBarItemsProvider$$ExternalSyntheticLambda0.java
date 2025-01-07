package com.android.systemui.dreams;

import android.view.View;
import com.android.systemui.ambient.statusbar.ui.AmbientStatusBarViewController;
import com.android.systemui.ambient.statusbar.ui.AmbientStatusBarViewController$$ExternalSyntheticLambda3;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.stream.Collectors;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final /* synthetic */ class DreamOverlayStatusBarItemsProvider$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ DreamOverlayStatusBarItemsProvider f$0;
    public final /* synthetic */ AmbientStatusBarViewController$$ExternalSyntheticLambda3 f$1;

    public /* synthetic */ DreamOverlayStatusBarItemsProvider$$ExternalSyntheticLambda0(DreamOverlayStatusBarItemsProvider dreamOverlayStatusBarItemsProvider, AmbientStatusBarViewController$$ExternalSyntheticLambda3 ambientStatusBarViewController$$ExternalSyntheticLambda3, int i) {
        this.$r8$classId = i;
        this.f$0 = dreamOverlayStatusBarItemsProvider;
        this.f$1 = ambientStatusBarViewController$$ExternalSyntheticLambda3;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.$r8$classId) {
            case 0:
                DreamOverlayStatusBarItemsProvider dreamOverlayStatusBarItemsProvider = this.f$0;
                AmbientStatusBarViewController$$ExternalSyntheticLambda3 ambientStatusBarViewController$$ExternalSyntheticLambda3 = this.f$1;
                dreamOverlayStatusBarItemsProvider.getClass();
                Objects.requireNonNull(ambientStatusBarViewController$$ExternalSyntheticLambda3, "Callback must not be null.");
                if (!dreamOverlayStatusBarItemsProvider.mCallbacks.contains(ambientStatusBarViewController$$ExternalSyntheticLambda3)) {
                    dreamOverlayStatusBarItemsProvider.mCallbacks.add(ambientStatusBarViewController$$ExternalSyntheticLambda3);
                    if (!dreamOverlayStatusBarItemsProvider.mItems.isEmpty()) {
                        final List list = dreamOverlayStatusBarItemsProvider.mItems;
                        final AmbientStatusBarViewController ambientStatusBarViewController = ambientStatusBarViewController$$ExternalSyntheticLambda3.f$0;
                        ambientStatusBarViewController.mMainExecutor.execute(new Runnable() { // from class: com.android.systemui.ambient.statusbar.ui.AmbientStatusBarViewController$$ExternalSyntheticLambda12
                            @Override // java.lang.Runnable
                            public final void run() {
                                AmbientStatusBarViewController ambientStatusBarViewController2 = AmbientStatusBarViewController.this;
                                List list2 = list;
                                ambientStatusBarViewController2.mExtraStatusBarItems.clear();
                                ambientStatusBarViewController2.mExtraStatusBarItems.addAll(list2);
                                final AmbientStatusBarView ambientStatusBarView = (AmbientStatusBarView) ambientStatusBarViewController2.mView;
                                List list3 = (List) list2.stream().map(new AmbientStatusBarViewController$$ExternalSyntheticLambda0()).collect(Collectors.toList());
                                ambientStatusBarView.mExtraSystemStatusViewGroup.removeAllViews();
                                list3.forEach(new Consumer() { // from class: com.android.systemui.ambient.statusbar.ui.AmbientStatusBarView$$ExternalSyntheticLambda0
                                    @Override // java.util.function.Consumer
                                    public final void accept(Object obj) {
                                        AmbientStatusBarView.this.mExtraSystemStatusViewGroup.addView((View) obj);
                                    }
                                });
                            }
                        });
                        break;
                    }
                }
                break;
            default:
                DreamOverlayStatusBarItemsProvider dreamOverlayStatusBarItemsProvider2 = this.f$0;
                AmbientStatusBarViewController$$ExternalSyntheticLambda3 ambientStatusBarViewController$$ExternalSyntheticLambda32 = this.f$1;
                dreamOverlayStatusBarItemsProvider2.getClass();
                Objects.requireNonNull(ambientStatusBarViewController$$ExternalSyntheticLambda32, "Callback must not be null.");
                dreamOverlayStatusBarItemsProvider2.mCallbacks.remove(ambientStatusBarViewController$$ExternalSyntheticLambda32);
                break;
        }
    }
}
