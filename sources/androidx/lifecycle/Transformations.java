package androidx.lifecycle;

import androidx.arch.core.internal.SafeIterableMap;
import androidx.lifecycle.MediatorLiveData;
import com.android.systemui.complication.ComplicationCollectionLiveData;
import com.android.systemui.complication.ComplicationCollectionViewModel$$ExternalSyntheticLambda0;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class Transformations {
    public static final MediatorLiveData map(ComplicationCollectionLiveData complicationCollectionLiveData, final ComplicationCollectionViewModel$$ExternalSyntheticLambda0 complicationCollectionViewModel$$ExternalSyntheticLambda0) {
        final MediatorLiveData mediatorLiveData;
        Object obj;
        if (complicationCollectionLiveData.mData != LiveData.NOT_SET) {
            mediatorLiveData = new MediatorLiveData(complicationCollectionViewModel$$ExternalSyntheticLambda0.invoke(complicationCollectionLiveData.getValue()));
            mediatorLiveData.mSources = new SafeIterableMap();
        } else {
            mediatorLiveData = new MediatorLiveData();
            mediatorLiveData.mSources = new SafeIterableMap();
        }
        Transformations$sam$androidx_lifecycle_Observer$0 transformations$sam$androidx_lifecycle_Observer$0 = new Transformations$sam$androidx_lifecycle_Observer$0(new Function1() { // from class: androidx.lifecycle.Transformations$map$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj2) {
                MediatorLiveData.this.setValue(complicationCollectionViewModel$$ExternalSyntheticLambda0.invoke(obj2));
                return Unit.INSTANCE;
            }
        });
        MediatorLiveData.Source source = new MediatorLiveData.Source(complicationCollectionLiveData, transformations$sam$androidx_lifecycle_Observer$0);
        SafeIterableMap safeIterableMap = mediatorLiveData.mSources;
        SafeIterableMap.Entry entry = safeIterableMap.get(complicationCollectionLiveData);
        if (entry != null) {
            obj = entry.mValue;
        } else {
            SafeIterableMap.Entry entry2 = new SafeIterableMap.Entry(complicationCollectionLiveData, source);
            safeIterableMap.mSize++;
            SafeIterableMap.Entry entry3 = safeIterableMap.mEnd;
            if (entry3 == null) {
                safeIterableMap.mStart = entry2;
                safeIterableMap.mEnd = entry2;
            } else {
                entry3.mNext = entry2;
                entry2.mPrevious = entry3;
                safeIterableMap.mEnd = entry2;
            }
            obj = null;
        }
        MediatorLiveData.Source source2 = (MediatorLiveData.Source) obj;
        if (source2 != null && source2.mObserver != transformations$sam$androidx_lifecycle_Observer$0) {
            throw new IllegalArgumentException("This source was already added with the different observer");
        }
        if (source2 == null && mediatorLiveData.mActiveCount > 0) {
            complicationCollectionLiveData.observeForever(source);
        }
        return mediatorLiveData;
    }
}
