package com.google.android.material.bottomappbar;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import com.google.android.material.behavior.HideBottomViewOnScrollBehavior;
import java.lang.ref.WeakReference;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public class BottomAppBar$Behavior extends HideBottomViewOnScrollBehavior {
    public final AnonymousClass1 fabLayoutListener;
    public final WeakReference viewRef;

    public BottomAppBar$Behavior() {
        new View.OnLayoutChangeListener() { // from class: com.google.android.material.bottomappbar.BottomAppBar$Behavior.1
            @Override // android.view.View.OnLayoutChangeListener
            public final void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
                if (BottomAppBar$Behavior.this.viewRef.get() != null) {
                    throw new ClassCastException();
                }
                view.removeOnLayoutChangeListener(this);
            }
        };
        new Rect();
    }

    @Override // com.google.android.material.behavior.HideBottomViewOnScrollBehavior, androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public final boolean onLayoutChild(CoordinatorLayout coordinatorLayout, View view, int i) {
        throw new ClassCastException();
    }

    @Override // com.google.android.material.behavior.HideBottomViewOnScrollBehavior, androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public final boolean onStartNestedScroll(View view, int i, int i2) {
        throw new ClassCastException();
    }

    public BottomAppBar$Behavior(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        new View.OnLayoutChangeListener() { // from class: com.google.android.material.bottomappbar.BottomAppBar$Behavior.1
            @Override // android.view.View.OnLayoutChangeListener
            public final void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
                if (BottomAppBar$Behavior.this.viewRef.get() != null) {
                    throw new ClassCastException();
                }
                view.removeOnLayoutChangeListener(this);
            }
        };
        new Rect();
    }
}
