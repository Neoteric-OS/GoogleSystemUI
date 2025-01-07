package com.android.systemui.statusbar.pipeline.shared.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import com.android.keyguard.logging.CarrierTextManagerLogger$logUpdate$2$$ExternalSyntheticOutline0;
import com.android.systemui.lifecycle.RepeatWhenAttachedKt;
import com.android.systemui.statusbar.StatusBarIconView;
import com.android.systemui.statusbar.pipeline.shared.ui.binder.ModernStatusBarViewBinding;
import com.android.wm.shell.R;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Ref$BooleanRef;
import kotlinx.coroutines.flow.StateFlowImpl;
import kotlinx.coroutines.flow.StateFlowKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class SingleBindableStatusBarIconView extends ModernStatusBarView {
    public static final /* synthetic */ int $r8$clinit = 0;
    public StatusBarIconView dotView;
    public ImageView iconView;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public abstract class Companion {
        /* JADX WARN: Type inference failed for: r13v1, types: [com.android.systemui.statusbar.pipeline.shared.ui.view.SingleBindableStatusBarIconView$Companion$withDefaultBinding$2] */
        public static SingleBindableStatusBarIconView$Companion$withDefaultBinding$2 withDefaultBinding(SingleBindableStatusBarIconView singleBindableStatusBarIconView, final Function0 function0, Function3 function3) {
            final StateFlowImpl MutableStateFlow = StateFlowKt.MutableStateFlow(2);
            final StateFlowImpl MutableStateFlow2 = StateFlowKt.MutableStateFlow(-1);
            final StateFlowImpl MutableStateFlow3 = StateFlowKt.MutableStateFlow(-1);
            final Ref$BooleanRef ref$BooleanRef = new Ref$BooleanRef();
            RepeatWhenAttachedKt.repeatWhenAttached(singleBindableStatusBarIconView, EmptyCoroutineContext.INSTANCE, new SingleBindableStatusBarIconView$Companion$withDefaultBinding$1(function3, singleBindableStatusBarIconView, ref$BooleanRef, MutableStateFlow, MutableStateFlow2, MutableStateFlow3, null));
            return new SingleBindableStatusBarIconViewBinding() { // from class: com.android.systemui.statusbar.pipeline.shared.ui.view.SingleBindableStatusBarIconView$Companion$withDefaultBinding$2
                @Override // com.android.systemui.statusbar.pipeline.shared.ui.binder.ModernStatusBarViewBinding
                public final boolean getShouldIconBeVisible() {
                    return ((Boolean) function0.invoke()).booleanValue();
                }

                @Override // com.android.systemui.statusbar.pipeline.shared.ui.binder.ModernStatusBarViewBinding
                public final boolean isCollecting() {
                    return ref$BooleanRef.element;
                }

                @Override // com.android.systemui.statusbar.pipeline.shared.ui.binder.ModernStatusBarViewBinding
                public final void onDecorTintChanged(int i) {
                    StateFlowImpl.this.updateState(null, Integer.valueOf(i));
                }

                @Override // com.android.systemui.statusbar.pipeline.shared.ui.binder.ModernStatusBarViewBinding
                public final void onIconTintChanged(int i, int i2) {
                    MutableStateFlow2.updateState(null, Integer.valueOf(i));
                }

                @Override // com.android.systemui.statusbar.pipeline.shared.ui.binder.ModernStatusBarViewBinding
                public final void onVisibilityStateChanged(int i) {
                    MutableStateFlow.updateState(null, Integer.valueOf(i));
                }
            };
        }
    }

    public SingleBindableStatusBarIconView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override // com.android.systemui.statusbar.pipeline.shared.ui.view.ModernStatusBarView
    public final void initView(String str, Function0 function0) {
        super.initView(str, function0);
        this.iconView = (ImageView) requireViewById(R.id.icon_view);
        this.dotView = (StatusBarIconView) requireViewById(R.id.status_bar_dot);
    }

    @Override // android.view.View
    public final String toString() {
        String slot = getSlot();
        ModernStatusBarViewBinding modernStatusBarViewBinding = this.binding;
        if (modernStatusBarViewBinding == null) {
            modernStatusBarViewBinding = null;
        }
        boolean isCollecting = modernStatusBarViewBinding.isCollecting();
        String visibleStateString = StatusBarIconView.getVisibleStateString(this.iconVisibleState);
        String frameLayout = super.toString();
        StringBuilder m = CarrierTextManagerLogger$logUpdate$2$$ExternalSyntheticOutline0.m("SingleBindableStatusBarIcon(slot='", slot, "', isCollecting=", isCollecting, ", visibleState=");
        m.append(visibleStateString);
        m.append("); viewString=");
        m.append(frameLayout);
        return m.toString();
    }
}
