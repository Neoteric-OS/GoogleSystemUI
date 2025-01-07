package com.android.systemui.keyguard.ui.binder;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.android.systemui.keyguard.ui.binder.KeyguardQuickAffordanceViewBinder;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.flow.FlowCollector;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class KeyguardQuickAffordanceViewBinder$updateButtonAlpha$4 implements FlowCollector {
    public final /* synthetic */ int $r8$classId = 0;
    public final /* synthetic */ Object $view;

    public KeyguardQuickAffordanceViewBinder$updateButtonAlpha$4(View view) {
        this.$view = view;
    }

    @Override // kotlinx.coroutines.flow.FlowCollector
    public final Object emit(Object obj, Continuation continuation) {
        switch (this.$r8$classId) {
            case 0:
                ((View) this.$view).setAlpha(((Number) obj).floatValue());
                return Unit.INSTANCE;
            default:
                KeyguardQuickAffordanceViewBinder.ConfigurationBasedDimensions configurationBasedDimensions = (KeyguardQuickAffordanceViewBinder.ConfigurationBasedDimensions) obj;
                ImageView imageView = (ImageView) this.$view;
                ViewGroup.LayoutParams layoutParams = imageView.getLayoutParams();
                if (layoutParams == null) {
                    throw new NullPointerException("null cannot be cast to non-null type android.view.ViewGroup.LayoutParams");
                }
                layoutParams.width = configurationBasedDimensions.buttonSizePx.getWidth();
                layoutParams.height = configurationBasedDimensions.buttonSizePx.getHeight();
                imageView.setLayoutParams(layoutParams);
                return Unit.INSTANCE;
        }
    }

    public KeyguardQuickAffordanceViewBinder$updateButtonAlpha$4(ImageView imageView) {
        this.$view = imageView;
    }
}
