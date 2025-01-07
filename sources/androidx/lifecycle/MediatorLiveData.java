package androidx.lifecycle;

import androidx.arch.core.internal.SafeIterableMap;
import com.android.systemui.complication.ComplicationCollectionLiveData;
import java.util.Iterator;
import java.util.Map;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class MediatorLiveData extends MutableLiveData {
    public SafeIterableMap mSources;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Source implements Observer {
        public final ComplicationCollectionLiveData mLiveData;
        public final Transformations$sam$androidx_lifecycle_Observer$0 mObserver;
        public int mVersion = -1;

        public Source(ComplicationCollectionLiveData complicationCollectionLiveData, Transformations$sam$androidx_lifecycle_Observer$0 transformations$sam$androidx_lifecycle_Observer$0) {
            this.mLiveData = complicationCollectionLiveData;
            this.mObserver = transformations$sam$androidx_lifecycle_Observer$0;
        }

        @Override // androidx.lifecycle.Observer
        public final void onChanged(Object obj) {
            int i = this.mVersion;
            int i2 = this.mLiveData.mVersion;
            if (i != i2) {
                this.mVersion = i2;
                this.mObserver.onChanged(obj);
            }
        }
    }

    @Override // androidx.lifecycle.LiveData
    public final void onActive() {
        Iterator it = this.mSources.iterator();
        while (true) {
            SafeIterableMap.AscendingIterator ascendingIterator = (SafeIterableMap.AscendingIterator) it;
            if (!ascendingIterator.hasNext()) {
                return;
            }
            Source source = (Source) ((Map.Entry) ascendingIterator.next()).getValue();
            source.mLiveData.observeForever(source);
        }
    }

    @Override // androidx.lifecycle.LiveData
    public final void onInactive() {
        Iterator it = this.mSources.iterator();
        while (true) {
            SafeIterableMap.AscendingIterator ascendingIterator = (SafeIterableMap.AscendingIterator) it;
            if (!ascendingIterator.hasNext()) {
                return;
            }
            Source source = (Source) ((Map.Entry) ascendingIterator.next()).getValue();
            source.mLiveData.removeObserver(source);
        }
    }
}
