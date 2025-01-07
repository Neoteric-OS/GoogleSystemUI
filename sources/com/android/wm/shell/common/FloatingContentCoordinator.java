package com.android.wm.shell.common;

import android.graphics.Rect;
import android.util.Log;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Pair;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref$ObjectRef;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class FloatingContentCoordinator {
    public final Map allContentBounds = new HashMap();
    public boolean currentlyResolvingConflicts;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public interface FloatingContent {
        Rect getAllowedFloatingBoundsRegion();

        Rect getFloatingBoundsOnScreen();

        void moveToBounds(Rect rect);
    }

    public final void maybeMoveConflictingContent(FloatingContent floatingContent) {
        int i;
        this.currentlyResolvingConflicts = true;
        Object obj = this.allContentBounds.get(floatingContent);
        Intrinsics.checkNotNull(obj);
        final Rect rect = (Rect) obj;
        Map map = this.allContentBounds;
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (Map.Entry entry : ((HashMap) map).entrySet()) {
            FloatingContent floatingContent2 = (FloatingContent) entry.getKey();
            Rect rect2 = (Rect) entry.getValue();
            if (!Intrinsics.areEqual(floatingContent2, floatingContent) && Rect.intersects(rect, rect2)) {
                linkedHashMap.put(entry.getKey(), entry.getValue());
            }
        }
        Iterator it = linkedHashMap.entrySet().iterator();
        while (true) {
            if (!it.hasNext()) {
                this.currentlyResolvingConflicts = false;
                return;
            }
            Map.Entry entry2 = (Map.Entry) it.next();
            FloatingContent floatingContent3 = (FloatingContent) entry2.getKey();
            List minus = CollectionsKt.minus(CollectionsKt.minus(this.allContentBounds.values(), (Rect) entry2.getValue()), rect);
            final Rect floatingBoundsOnScreen = floatingContent3.getFloatingBoundsOnScreen();
            final Rect allowedFloatingBoundsRegion = floatingContent3.getAllowedFloatingBoundsRegion();
            boolean z = rect.centerY() < floatingBoundsOnScreen.centerY();
            ArrayList arrayList = new ArrayList();
            for (Object obj2 : minus) {
                Rect rect3 = (Rect) obj2;
                int i2 = rect3.left;
                int i3 = floatingBoundsOnScreen.left;
                if ((i2 >= i3 && i2 <= floatingBoundsOnScreen.right) || ((i = rect3.right) <= floatingBoundsOnScreen.right && i >= i3)) {
                    arrayList.add(obj2);
                }
            }
            ArrayList arrayList2 = new ArrayList();
            ArrayList arrayList3 = new ArrayList();
            for (Object obj3 : arrayList) {
                if (((Rect) obj3).top < floatingBoundsOnScreen.top) {
                    arrayList2.add(obj3);
                } else {
                    arrayList3.add(obj3);
                }
            }
            Pair pair = new Pair(arrayList2, arrayList3);
            final Ref$ObjectRef ref$ObjectRef = new Ref$ObjectRef();
            ref$ObjectRef.element = pair.component1();
            final Ref$ObjectRef ref$ObjectRef2 = new Ref$ObjectRef();
            ref$ObjectRef2.element = pair.component2();
            final Lazy lazy = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.android.wm.shell.common.FloatingContentCoordinator$Companion$findAreaForContentVertically$newContentBoundsAbove$2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    Rect rect4 = floatingBoundsOnScreen;
                    List<Rect> sortedWith = CollectionsKt.sortedWith(CollectionsKt.plus((Collection) ref$ObjectRef.element, rect), new FloatingContentCoordinator$Companion$findAreaForContentAboveOrBelow$$inlined$sortedBy$1(true));
                    Rect rect5 = new Rect(rect4);
                    for (Rect rect6 : sortedWith) {
                        if (!Rect.intersects(rect5, rect6)) {
                            break;
                        }
                        rect5.offsetTo(rect5.left, rect6.top + (-rect4.height()));
                    }
                    return rect5;
                }
            });
            final Lazy lazy2 = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.android.wm.shell.common.FloatingContentCoordinator$Companion$findAreaForContentVertically$newContentBoundsBelow$2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    Rect rect4 = floatingBoundsOnScreen;
                    List<Rect> sortedWith = CollectionsKt.sortedWith(CollectionsKt.plus((Collection) ref$ObjectRef2.element, rect), new FloatingContentCoordinator$Companion$findAreaForContentAboveOrBelow$$inlined$sortedBy$1(false));
                    Rect rect5 = new Rect(rect4);
                    for (Rect rect6 : sortedWith) {
                        if (!Rect.intersects(rect5, rect6)) {
                            break;
                        }
                        rect5.offsetTo(rect5.left, rect6.top + rect6.height());
                    }
                    return rect5;
                }
            });
            Rect rect4 = (!(z && ((Boolean) LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.android.wm.shell.common.FloatingContentCoordinator$Companion$findAreaForContentVertically$positionBelowInBounds$2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return Boolean.valueOf(allowedFloatingBoundsRegion.contains((Rect) lazy2.getValue()));
                }
            }).getValue()).booleanValue()) && (z || ((Boolean) LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.android.wm.shell.common.FloatingContentCoordinator$Companion$findAreaForContentVertically$positionAboveInBounds$2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return Boolean.valueOf(allowedFloatingBoundsRegion.contains((Rect) lazy.getValue()));
                }
            }).getValue()).booleanValue())) ? (Rect) lazy.getValue() : (Rect) lazy2.getValue();
            if (!allowedFloatingBoundsRegion.contains(rect4)) {
                rect4 = new Rect();
            }
            if (!rect4.isEmpty()) {
                floatingContent3.moveToBounds(rect4);
                this.allContentBounds.put(floatingContent3, floatingContent3.getFloatingBoundsOnScreen());
            }
        }
    }

    public final void onContentMoved(FloatingContent floatingContent) {
        if (this.currentlyResolvingConflicts) {
            return;
        }
        if (!this.allContentBounds.containsKey(floatingContent)) {
            Log.wtf("FloatingCoordinator", "Received onContentMoved call before onContentAdded! This should never happen.");
        } else {
            updateContentBounds();
            maybeMoveConflictingContent(floatingContent);
        }
    }

    public final void updateContentBounds() {
        for (FloatingContent floatingContent : this.allContentBounds.keySet()) {
            this.allContentBounds.put(floatingContent, floatingContent.getFloatingBoundsOnScreen());
        }
    }
}
