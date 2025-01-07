package com.android.systemui.communal.ui.binder;

import android.content.Context;
import android.util.SizeF;
import android.widget.FrameLayout;
import com.android.app.tracing.coroutines.CoroutineTracingKt;
import com.android.systemui.communal.domain.model.CommunalContentModel;
import com.android.systemui.communal.util.WidgetViewFactory;
import com.android.systemui.util.kotlin.DisposableHandles;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.DisposableHandle;
import kotlinx.coroutines.StandaloneCoroutine;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class CommunalAppWidgetHostViewBinder {
    public static DisposableHandles bind(Context context, CoroutineScope coroutineScope, final FrameLayout frameLayout, CommunalContentModel.WidgetContent.Widget widget, SizeF sizeF, WidgetViewFactory widgetViewFactory) {
        DisposableHandles disposableHandles = new DisposableHandles();
        final StandaloneCoroutine launch$default = CoroutineTracingKt.launch$default(coroutineScope, null, new CommunalAppWidgetHostViewBinder$bind$loadingJob$1(widgetViewFactory, context, widget, sizeF, frameLayout, null), 6);
        final int i = 0;
        disposableHandles.plusAssign(new DisposableHandle() { // from class: com.android.systemui.communal.ui.binder.CommunalAppWidgetHostViewBinder$bind$1
            @Override // kotlinx.coroutines.DisposableHandle
            public final void dispose() {
                switch (i) {
                    case 0:
                        ((StandaloneCoroutine) launch$default).cancel(null);
                        break;
                    default:
                        ((FrameLayout) launch$default).removeAllViews();
                        break;
                }
            }
        });
        final int i2 = 1;
        disposableHandles.plusAssign(new DisposableHandle() { // from class: com.android.systemui.communal.ui.binder.CommunalAppWidgetHostViewBinder$bind$1
            @Override // kotlinx.coroutines.DisposableHandle
            public final void dispose() {
                switch (i2) {
                    case 0:
                        ((StandaloneCoroutine) frameLayout).cancel(null);
                        break;
                    default:
                        ((FrameLayout) frameLayout).removeAllViews();
                        break;
                }
            }
        });
        return disposableHandles;
    }
}
