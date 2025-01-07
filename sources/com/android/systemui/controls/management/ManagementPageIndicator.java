package com.android.systemui.controls.management;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import com.android.systemui.qs.PageIndicator;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class ManagementPageIndicator extends PageIndicator {
    public Lambda visibilityListener;

    public ManagementPageIndicator(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.visibilityListener = new Function1() { // from class: com.android.systemui.controls.management.ManagementPageIndicator$visibilityListener$1
            @Override // kotlin.jvm.functions.Function1
            public final /* bridge */ /* synthetic */ Object invoke(Object obj) {
                ((Number) obj).intValue();
                return Unit.INSTANCE;
            }
        };
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [kotlin.jvm.functions.Function1, kotlin.jvm.internal.Lambda] */
    @Override // android.view.View
    public final void onVisibilityChanged(View view, int i) {
        super.onVisibilityChanged(view, i);
        if (view.equals(this)) {
            this.visibilityListener.invoke(Integer.valueOf(i));
        }
    }

    @Override // com.android.systemui.qs.PageIndicator
    public final void setLocation(float f) {
        if (getLayoutDirection() == 1) {
            super.setLocation((getChildCount() - 1) - f);
        } else {
            super.setLocation(f);
        }
    }
}
