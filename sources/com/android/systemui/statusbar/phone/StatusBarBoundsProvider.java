package com.android.systemui.statusbar.phone;

import android.view.View;
import com.android.systemui.statusbar.data.repository.StatusBarModePerDisplayRepositoryImpl$onStatusBarViewInitialized$listener$1;
import com.android.systemui.util.ConvenienceExtensionsKt;
import com.android.systemui.util.ListenerSet;
import java.util.Iterator;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.flow.StateFlowImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class StatusBarBoundsProvider {
    public final View endSideContent;
    public BoundsPair previousBounds;
    public final View startSideContent;
    public final ListenerSet changeListeners = new ListenerSet();
    public final StatusBarBoundsProvider$layoutListener$1 layoutListener = new View.OnLayoutChangeListener() { // from class: com.android.systemui.statusbar.phone.StatusBarBoundsProvider$layoutListener$1
        @Override // android.view.View.OnLayoutChangeListener
        public final void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
            BoundsPair boundsPair = new BoundsPair(ConvenienceExtensionsKt.getBoundsOnScreen(StatusBarBoundsProvider.this.startSideContent), ConvenienceExtensionsKt.getBoundsOnScreen(StatusBarBoundsProvider.this.endSideContent));
            if (Intrinsics.areEqual(StatusBarBoundsProvider.this.previousBounds, boundsPair)) {
                return;
            }
            StatusBarBoundsProvider statusBarBoundsProvider = StatusBarBoundsProvider.this;
            statusBarBoundsProvider.previousBounds = boundsPair;
            Iterator it = statusBarBoundsProvider.changeListeners.listeners.iterator();
            while (it.hasNext()) {
                StateFlowImpl stateFlowImpl = ((StatusBarModePerDisplayRepositoryImpl$onStatusBarViewInitialized$listener$1) it.next()).this$0._statusBarBounds;
                stateFlowImpl.getClass();
                stateFlowImpl.updateState(null, boundsPair);
            }
        }
    };

    /* JADX WARN: Type inference failed for: r2v2, types: [com.android.systemui.statusbar.phone.StatusBarBoundsProvider$layoutListener$1] */
    public StatusBarBoundsProvider(View view, View view2) {
        this.startSideContent = view;
        this.endSideContent = view2;
        this.previousBounds = new BoundsPair(ConvenienceExtensionsKt.getBoundsOnScreen(view), ConvenienceExtensionsKt.getBoundsOnScreen(view2));
    }
}
