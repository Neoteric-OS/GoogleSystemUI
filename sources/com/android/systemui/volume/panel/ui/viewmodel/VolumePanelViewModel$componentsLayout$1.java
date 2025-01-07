package com.android.systemui.volume.panel.ui.viewmodel;

import androidx.appsearch.app.DocumentClassFactoryRegistry$$ExternalSyntheticOutline0;
import com.android.systemui.volume.panel.domain.model.ComponentModel;
import com.android.systemui.volume.panel.ui.composable.ComponentsFactory;
import com.android.systemui.volume.panel.ui.composable.ComposeVolumePanelUiComponent;
import com.android.systemui.volume.panel.ui.layout.ComponentsLayout;
import com.android.systemui.volume.panel.ui.layout.DefaultComponentsLayoutManager;
import com.android.systemui.volume.panel.ui.layout.DefaultComponentsLayoutManager$layout$$inlined$sortedBy$3;
import com.android.systemui.volume.panel.ui.viewmodel.ComponentState;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.inject.Provider;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.collections.MapsKt;
import kotlin.comparisons.ComparisonsKt___ComparisonsJvmKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
final class VolumePanelViewModel$componentsLayout$1 extends SuspendLambda implements Function3 {
    /* synthetic */ Object L$0;
    /* synthetic */ Object L$1;
    int label;
    final /* synthetic */ VolumePanelViewModel this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public VolumePanelViewModel$componentsLayout$1(VolumePanelViewModel volumePanelViewModel, Continuation continuation) {
        super(3, continuation);
        this.this$0 = volumePanelViewModel;
    }

    @Override // kotlin.jvm.functions.Function3
    public final Object invoke(Object obj, Object obj2, Object obj3) {
        VolumePanelViewModel$componentsLayout$1 volumePanelViewModel$componentsLayout$1 = new VolumePanelViewModel$componentsLayout$1(this.this$0, (Continuation) obj3);
        volumePanelViewModel$componentsLayout$1.L$0 = (Collection) obj;
        volumePanelViewModel$componentsLayout$1.L$1 = (VolumePanelState) obj2;
        return volumePanelViewModel$componentsLayout$1.invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        String str;
        Object obj2;
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        Collection collection = (Collection) this.L$0;
        Collection<ComponentModel> collection2 = collection;
        VolumePanelViewModel volumePanelViewModel = this.this$0;
        ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(collection2, 10));
        for (ComponentModel componentModel : collection2) {
            String str2 = componentModel.key;
            ComponentsFactory componentsFactory = (ComponentsFactory) volumePanelViewModel.volumePanelComponent.componentsFactoryProvider.get();
            Map map = componentsFactory.componentByKey;
            String str3 = componentModel.key;
            if (!map.containsKey(str3)) {
                throw new IllegalArgumentException(DocumentClassFactoryRegistry$$ExternalSyntheticOutline0.m("Component for key=", str3, " is not bound.").toString());
            }
            arrayList.add(new ComponentState(str2, (ComposeVolumePanelUiComponent) ((Provider) MapsKt.getValue(str3, componentsFactory.componentByKey)).get(), componentModel.isAvailable));
        }
        final DefaultComponentsLayoutManager defaultComponentsLayoutManager = (DefaultComponentsLayoutManager) this.this$0.volumePanelComponent.defaultComponentsLayoutManagerProvider.get();
        defaultComponentsLayoutManager.getClass();
        ArrayList arrayList2 = new ArrayList();
        Iterator it = arrayList.iterator();
        while (true) {
            boolean hasNext = it.hasNext();
            str = defaultComponentsLayoutManager.bottomBar;
            if (!hasNext) {
                break;
            }
            Object next = it.next();
            ComponentState componentState = (ComponentState) next;
            if (!defaultComponentsLayoutManager.headerComponents.contains(componentState.key)) {
                Collection collection3 = defaultComponentsLayoutManager.footerComponents;
                String str4 = componentState.key;
                if (!collection3.contains(str4) && !Intrinsics.areEqual(str4, str)) {
                    arrayList2.add(next);
                }
            }
        }
        ArrayList arrayList3 = new ArrayList();
        for (Object obj3 : arrayList) {
            if (defaultComponentsLayoutManager.headerComponents.contains(((ComponentState) obj3).key)) {
                arrayList3.add(obj3);
            }
        }
        final int i = 0;
        List sortedWith = CollectionsKt.sortedWith(arrayList3, new Comparator() { // from class: com.android.systemui.volume.panel.ui.layout.DefaultComponentsLayoutManager$layout$$inlined$sortedBy$1
            @Override // java.util.Comparator
            public final int compare(Object obj4, Object obj5) {
                switch (i) {
                    case 0:
                        return ComparisonsKt___ComparisonsJvmKt.compareValues(Integer.valueOf(CollectionsKt.indexOf(defaultComponentsLayoutManager.headerComponents, ((ComponentState) obj4).key)), Integer.valueOf(CollectionsKt.indexOf(defaultComponentsLayoutManager.headerComponents, ((ComponentState) obj5).key)));
                    default:
                        return ComparisonsKt___ComparisonsJvmKt.compareValues(Integer.valueOf(CollectionsKt.indexOf(defaultComponentsLayoutManager.footerComponents, ((ComponentState) obj4).key)), Integer.valueOf(CollectionsKt.indexOf(defaultComponentsLayoutManager.footerComponents, ((ComponentState) obj5).key)));
                }
            }
        });
        ArrayList arrayList4 = new ArrayList();
        for (Object obj4 : arrayList) {
            if (defaultComponentsLayoutManager.footerComponents.contains(((ComponentState) obj4).key)) {
                arrayList4.add(obj4);
            }
        }
        final int i2 = 1;
        List sortedWith2 = CollectionsKt.sortedWith(arrayList4, new Comparator() { // from class: com.android.systemui.volume.panel.ui.layout.DefaultComponentsLayoutManager$layout$$inlined$sortedBy$1
            @Override // java.util.Comparator
            public final int compare(Object obj42, Object obj5) {
                switch (i2) {
                    case 0:
                        return ComparisonsKt___ComparisonsJvmKt.compareValues(Integer.valueOf(CollectionsKt.indexOf(defaultComponentsLayoutManager.headerComponents, ((ComponentState) obj42).key)), Integer.valueOf(CollectionsKt.indexOf(defaultComponentsLayoutManager.headerComponents, ((ComponentState) obj5).key)));
                    default:
                        return ComparisonsKt___ComparisonsJvmKt.compareValues(Integer.valueOf(CollectionsKt.indexOf(defaultComponentsLayoutManager.footerComponents, ((ComponentState) obj42).key)), Integer.valueOf(CollectionsKt.indexOf(defaultComponentsLayoutManager.footerComponents, ((ComponentState) obj5).key)));
                }
            }
        });
        List sortedWith3 = CollectionsKt.sortedWith(arrayList2, new DefaultComponentsLayoutManager$layout$$inlined$sortedBy$3());
        Iterator it2 = arrayList.iterator();
        while (true) {
            if (!it2.hasNext()) {
                obj2 = null;
                break;
            }
            obj2 = it2.next();
            if (Intrinsics.areEqual(((ComponentState) obj2).key, str)) {
                break;
            }
        }
        ComponentState componentState2 = (ComponentState) obj2;
        if (componentState2 != null) {
            return new ComponentsLayout(sortedWith, sortedWith3, sortedWith2, componentState2);
        }
        throw new IllegalStateException("VolumePanelComponents.BOTTOM_BAR must be present in the default components layout.");
    }
}
