package com.android.systemui.statusbar;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import com.android.systemui.animation.LaunchableView;
import com.android.systemui.animation.LaunchableViewDelegate;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public class AlphaOptimizedFrameLayout extends FrameLayout implements LaunchableView {
    public final LaunchableViewDelegate mLaunchableViewDelegate;

    public AlphaOptimizedFrameLayout(Context context) {
        super(context);
        this.mLaunchableViewDelegate = new LaunchableViewDelegate(this, new Function1() { // from class: com.android.systemui.statusbar.AlphaOptimizedFrameLayout$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                super/*android.widget.FrameLayout*/.setVisibility(((Integer) obj).intValue());
                return Unit.INSTANCE;
            }
        });
    }

    @Override // android.view.View
    public final boolean hasOverlappingRendering() {
        return false;
    }

    @Override // com.android.systemui.animation.LaunchableView
    public final void setShouldBlockVisibilityChanges(boolean z) {
        this.mLaunchableViewDelegate.setShouldBlockVisibilityChanges(z);
    }

    @Override // android.view.View
    public final void setVisibility(int i) {
        this.mLaunchableViewDelegate.setVisibility(i);
    }

    public AlphaOptimizedFrameLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mLaunchableViewDelegate = new LaunchableViewDelegate(this, new Function1() { // from class: com.android.systemui.statusbar.AlphaOptimizedFrameLayout$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                super/*android.widget.FrameLayout*/.setVisibility(((Integer) obj).intValue());
                return Unit.INSTANCE;
            }
        });
    }

    public AlphaOptimizedFrameLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mLaunchableViewDelegate = new LaunchableViewDelegate(this, new Function1() { // from class: com.android.systemui.statusbar.AlphaOptimizedFrameLayout$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                super/*android.widget.FrameLayout*/.setVisibility(((Integer) obj).intValue());
                return Unit.INSTANCE;
            }
        });
    }

    public AlphaOptimizedFrameLayout(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.mLaunchableViewDelegate = new LaunchableViewDelegate(this, new Function1() { // from class: com.android.systemui.statusbar.AlphaOptimizedFrameLayout$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                super/*android.widget.FrameLayout*/.setVisibility(((Integer) obj).intValue());
                return Unit.INSTANCE;
            }
        });
    }
}
