package com.android.systemui.complication;

import com.android.systemui.complication.ComplicationId;
import java.util.Collection;
import java.util.HashMap;
import java.util.function.Function;
import java.util.stream.Collectors;
import kotlin.jvm.functions.Function1;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final /* synthetic */ class ComplicationCollectionViewModel$$ExternalSyntheticLambda0 implements Function1 {
    public final /* synthetic */ ComplicationCollectionViewModel f$0;

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object obj) {
        final ComplicationCollectionViewModel complicationCollectionViewModel = this.f$0;
        complicationCollectionViewModel.getClass();
        return (Collection) ((Collection) obj).stream().map(new Function() { // from class: com.android.systemui.complication.ComplicationCollectionViewModel$$ExternalSyntheticLambda1
            @Override // java.util.function.Function
            public final Object apply(Object obj2) {
                ComplicationCollectionViewModel complicationCollectionViewModel2 = ComplicationCollectionViewModel.this;
                if (obj2 != null) {
                    throw new ClassCastException();
                }
                ComplicationViewModelTransformer complicationViewModelTransformer = complicationCollectionViewModel2.mTransformer;
                if (!complicationViewModelTransformer.mComplicationIdMapping.containsKey(null)) {
                    HashMap hashMap = complicationViewModelTransformer.mComplicationIdMapping;
                    ComplicationId.Factory factory = complicationViewModelTransformer.mComplicationIdFactory;
                    int i = factory.mNextId;
                    factory.mNextId = i + 1;
                    ComplicationId complicationId = new ComplicationId();
                    complicationId.mId = i;
                    hashMap.put(null, complicationId);
                }
                complicationViewModelTransformer.mViewModelComponentFactory.getClass();
                throw null;
            }
        }).collect(Collectors.toSet());
    }
}
