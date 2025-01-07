package com.android.systemui.communal.ui.view.layout.sections;

import android.content.Context;
import android.util.SizeF;
import android.widget.FrameLayout;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerImpl;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.OpaqueKey;
import androidx.compose.runtime.RecomposeScopeImpl;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.viewinterop.AndroidView_androidKt;
import androidx.lifecycle.compose.FlowExtKt;
import com.android.systemui.communal.domain.model.CommunalContentModel;
import com.android.systemui.communal.ui.binder.CommunalAppWidgetHostViewBinder;
import com.android.systemui.communal.ui.viewmodel.BaseCommunalViewModel;
import com.android.systemui.communal.util.WidgetViewFactory;
import com.android.wm.shell.R;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.DisposableHandle;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class CommunalAppWidgetSection {
    public final CoroutineScope applicationScope;
    public final WidgetViewFactory factory;

    public CommunalAppWidgetSection(CoroutineScope coroutineScope, WidgetViewFactory widgetViewFactory) {
        this.applicationScope = coroutineScope;
        this.factory = widgetViewFactory;
    }

    public final void Widget(final BaseCommunalViewModel baseCommunalViewModel, final CommunalContentModel.WidgetContent.Widget widget, final SizeF sizeF, Modifier modifier, Composer composer, final int i, final int i2) {
        ComposerImpl composerImpl = (ComposerImpl) composer;
        composerImpl.startRestartGroup(-1423935837);
        final Modifier modifier2 = (i2 & 8) != 0 ? Modifier.Companion.$$INSTANCE : modifier;
        OpaqueKey opaqueKey = ComposerKt.invocation;
        final MutableState collectAsStateWithLifecycle = FlowExtKt.collectAsStateWithLifecycle(baseCommunalViewModel.isFocusable(), Boolean.FALSE, composerImpl, 56);
        Function1 function1 = new Function1() { // from class: com.android.systemui.communal.ui.view.layout.sections.CommunalAppWidgetSection$Widget$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                Context context = (Context) obj;
                FrameLayout frameLayout = new FrameLayout(context);
                CommunalAppWidgetSection communalAppWidgetSection = CommunalAppWidgetSection.this;
                CommunalContentModel.WidgetContent.Widget widget2 = widget;
                SizeF sizeF2 = sizeF;
                BaseCommunalViewModel baseCommunalViewModel2 = baseCommunalViewModel;
                frameLayout.setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
                frameLayout.setTag(R.id.communal_widget_disposable_tag, CommunalAppWidgetHostViewBinder.bind(context, communalAppWidgetSection.applicationScope, frameLayout, widget2, sizeF2, communalAppWidgetSection.factory));
                frameLayout.setAccessibilityDelegate(baseCommunalViewModel2.getWidgetAccessibilityDelegate());
                return frameLayout;
            }
        };
        CommunalAppWidgetSection$Widget$2 communalAppWidgetSection$Widget$2 = new Function1() { // from class: com.android.systemui.communal.ui.view.layout.sections.CommunalAppWidgetSection$Widget$2
            @Override // kotlin.jvm.functions.Function1
            public final /* bridge */ /* synthetic */ Object invoke(Object obj) {
                return Unit.INSTANCE;
            }
        };
        CommunalAppWidgetSection$Widget$3 communalAppWidgetSection$Widget$3 = new Function1() { // from class: com.android.systemui.communal.ui.view.layout.sections.CommunalAppWidgetSection$Widget$3
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                Object tag = ((FrameLayout) obj).getTag(R.id.communal_widget_disposable_tag);
                DisposableHandle disposableHandle = tag instanceof DisposableHandle ? (DisposableHandle) tag : null;
                if (disposableHandle != null) {
                    disposableHandle.dispose();
                }
                return Unit.INSTANCE;
            }
        };
        composerImpl.startReplaceGroup(-411247941);
        boolean changed = composerImpl.changed(collectAsStateWithLifecycle);
        Object rememberedValue = composerImpl.rememberedValue();
        if (changed || rememberedValue == Composer.Companion.Empty) {
            rememberedValue = new Function1() { // from class: com.android.systemui.communal.ui.view.layout.sections.CommunalAppWidgetSection$Widget$4$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    ((FrameLayout) obj).setImportantForAccessibility(((Boolean) collectAsStateWithLifecycle.getValue()).booleanValue() ? 0 : 4);
                    return Unit.INSTANCE;
                }
            };
            composerImpl.updateRememberedValue(rememberedValue);
        }
        composerImpl.end(false);
        AndroidView_androidKt.AndroidView(function1, modifier2, communalAppWidgetSection$Widget$2, communalAppWidgetSection$Widget$3, (Function1) rememberedValue, composerImpl, ((i >> 6) & 112) | 3456, 0);
        RecomposeScopeImpl endRestartGroup = composerImpl.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.block = new Function2() { // from class: com.android.systemui.communal.ui.view.layout.sections.CommunalAppWidgetSection$Widget$5
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    ((Number) obj2).intValue();
                    CommunalAppWidgetSection.this.Widget(baseCommunalViewModel, widget, sizeF, modifier2, (Composer) obj, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                    return Unit.INSTANCE;
                }
            };
        }
    }
}
