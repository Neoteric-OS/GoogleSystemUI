package com.android.systemui.statusbar.pipeline.shared.ui.binder;

import android.view.View;
import com.android.systemui.lifecycle.RepeatWhenAttachedKt;
import com.android.systemui.statusbar.phone.fragment.CollapsedStatusBarFragment;
import com.android.systemui.statusbar.pipeline.shared.ui.viewmodel.CollapsedStatusBarViewModel;
import kotlin.coroutines.EmptyCoroutineContext;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class CollapsedStatusBarViewBinderImpl {
    public final void bind(View view, CollapsedStatusBarViewModel collapsedStatusBarViewModel, CollapsedStatusBarFragment.AnonymousClass5 anonymousClass5) {
        RepeatWhenAttachedKt.repeatWhenAttached(view, EmptyCoroutineContext.INSTANCE, new CollapsedStatusBarViewBinderImpl$bind$1(view, this, anonymousClass5, collapsedStatusBarViewModel, null));
    }
}
