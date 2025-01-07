package androidx.slice;

import android.app.slice.SliceManager;
import android.content.Context;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;
import androidx.collection.ArraySet;
import androidx.collection.ArraySet.ElementIterator;
import androidx.slice.SliceViewManager;
import androidx.slice.widget.SliceLiveData;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class SliceViewManagerBase$SliceListenerImpl {
    public final SliceViewManager.SliceCallback mCallback;
    public final SliceViewManagerBase$1 mExecutor;
    public boolean mPinned;
    public final Uri mUri;
    public final /* synthetic */ SliceViewManagerWrapper this$0;
    public final AnonymousClass1 mUpdateSlice = new Runnable() { // from class: androidx.slice.SliceViewManagerBase$SliceListenerImpl.1
        @Override // java.lang.Runnable
        public final void run() {
            SliceViewManagerBase$SliceListenerImpl sliceViewManagerBase$SliceListenerImpl = SliceViewManagerBase$SliceListenerImpl.this;
            if (!sliceViewManagerBase$SliceListenerImpl.mPinned) {
                try {
                    sliceViewManagerBase$SliceListenerImpl.this$0.pinSlice(sliceViewManagerBase$SliceListenerImpl.mUri);
                    sliceViewManagerBase$SliceListenerImpl.mPinned = true;
                } catch (SecurityException unused) {
                }
            }
            SliceViewManagerBase$SliceListenerImpl sliceViewManagerBase$SliceListenerImpl2 = SliceViewManagerBase$SliceListenerImpl.this;
            Context context = sliceViewManagerBase$SliceListenerImpl2.this$0.mContext;
            Uri uri = sliceViewManagerBase$SliceListenerImpl2.mUri;
            ArraySet arraySet = SliceLiveData.SUPPORTED_SPECS;
            SliceManager sliceManager = (SliceManager) context.getSystemService(SliceManager.class);
            ArraySet arraySet2 = new ArraySet(0);
            if (arraySet != null) {
                ArraySet.ElementIterator elementIterator = arraySet.new ElementIterator();
                while (elementIterator.hasNext()) {
                    SliceSpec sliceSpec = (SliceSpec) elementIterator.next();
                    arraySet2.add(sliceSpec == null ? null : new android.app.slice.SliceSpec(sliceSpec.mType, sliceSpec.mRevision));
                }
            }
            final Slice wrap = SliceConvert.wrap(sliceManager.bindSlice(uri, arraySet2), context);
            SliceViewManagerBase$SliceListenerImpl.this.mExecutor.execute(new Runnable() { // from class: androidx.slice.SliceViewManagerBase.SliceListenerImpl.1.1
                @Override // java.lang.Runnable
                public final void run() {
                    SliceViewManagerBase$SliceListenerImpl.this.mCallback.onSliceUpdated(wrap);
                }
            });
        }
    };
    public final AnonymousClass2 mObserver = new ContentObserver(new Handler(Looper.getMainLooper())) { // from class: androidx.slice.SliceViewManagerBase$SliceListenerImpl.2
        @Override // android.database.ContentObserver
        public final void onChange(boolean z) {
            AsyncTask.execute(SliceViewManagerBase$SliceListenerImpl.this.mUpdateSlice);
        }
    };

    /* JADX WARN: Type inference failed for: r3v1, types: [androidx.slice.SliceViewManagerBase$SliceListenerImpl$1] */
    /* JADX WARN: Type inference failed for: r3v2, types: [androidx.slice.SliceViewManagerBase$SliceListenerImpl$2] */
    public SliceViewManagerBase$SliceListenerImpl(SliceViewManagerWrapper sliceViewManagerWrapper, Uri uri, SliceViewManagerBase$1 sliceViewManagerBase$1, SliceViewManager.SliceCallback sliceCallback) {
        this.this$0 = sliceViewManagerWrapper;
        this.mUri = uri;
        this.mExecutor = sliceViewManagerBase$1;
        this.mCallback = sliceCallback;
    }
}
