package androidx.slice.widget;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;
import androidx.collection.ArraySet;
import androidx.lifecycle.LiveData;
import androidx.slice.Slice;
import androidx.slice.SliceSpec;
import androidx.slice.SliceSpecs;
import androidx.slice.SliceViewManager;
import androidx.slice.SliceViewManagerWrapper;
import androidx.slice.widget.SliceLiveData;
import com.android.systemui.volume.VolumePanelDialog$$ExternalSyntheticLambda0;
import java.util.Arrays;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class SliceLiveData {
    public static final ArraySet SUPPORTED_SPECS = new ArraySet(Arrays.asList(SliceSpecs.BASIC, SliceSpecs.LIST, SliceSpecs.LIST_V2, new SliceSpec("androidx.app.slice.BASIC", 1), new SliceSpec("androidx.app.slice.LIST", 1)));

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class SliceLiveDataImpl extends LiveData {
        public final VolumePanelDialog$$ExternalSyntheticLambda0 mListener;
        public final SliceViewManagerWrapper mSliceViewManager;
        public Uri mUri;
        public final AnonymousClass1 mUpdateSlice = new Runnable() { // from class: androidx.slice.widget.SliceLiveData.SliceLiveDataImpl.1
            @Override // java.lang.Runnable
            public final void run() {
                try {
                    SliceLiveDataImpl sliceLiveDataImpl = SliceLiveDataImpl.this;
                    Uri uri = sliceLiveDataImpl.mUri;
                    SliceViewManagerWrapper sliceViewManagerWrapper = sliceLiveDataImpl.mSliceViewManager;
                    if (uri == null) {
                        sliceViewManagerWrapper.getClass();
                        throw null;
                    }
                    Slice bindSlice = sliceViewManagerWrapper.bindSlice(uri);
                    SliceLiveDataImpl sliceLiveDataImpl2 = SliceLiveDataImpl.this;
                    if (sliceLiveDataImpl2.mUri == null && bindSlice != null) {
                        sliceLiveDataImpl2.mUri = Uri.parse(bindSlice.mUri);
                        SliceLiveDataImpl sliceLiveDataImpl3 = SliceLiveDataImpl.this;
                        sliceLiveDataImpl3.mSliceViewManager.registerSliceCallback(sliceLiveDataImpl3.mUri, sliceLiveDataImpl3.mSliceCallback);
                    }
                    SliceLiveDataImpl.this.postValue(bindSlice);
                } catch (IllegalArgumentException e) {
                    VolumePanelDialog$$ExternalSyntheticLambda0 volumePanelDialog$$ExternalSyntheticLambda0 = SliceLiveDataImpl.this.mListener;
                    Log.e("SliceLiveData", "Error binding slice", e);
                    SliceLiveDataImpl.this.postValue(null);
                } catch (Exception e2) {
                    VolumePanelDialog$$ExternalSyntheticLambda0 volumePanelDialog$$ExternalSyntheticLambda02 = SliceLiveDataImpl.this.mListener;
                    Log.e("SliceLiveData", "Error binding slice", e2);
                    SliceLiveDataImpl.this.postValue(null);
                }
            }
        };
        public final SliceLiveData$SliceLiveDataImpl$$ExternalSyntheticLambda0 mSliceCallback = new SliceViewManager.SliceCallback() { // from class: androidx.slice.widget.SliceLiveData$SliceLiveDataImpl$$ExternalSyntheticLambda0
            @Override // androidx.slice.SliceViewManager.SliceCallback
            public final void onSliceUpdated(Slice slice) {
                SliceLiveData.SliceLiveDataImpl.this.postValue(slice);
            }
        };

        /* JADX WARN: Type inference failed for: r3v1, types: [androidx.slice.widget.SliceLiveData$SliceLiveDataImpl$1] */
        /* JADX WARN: Type inference failed for: r3v2, types: [androidx.slice.widget.SliceLiveData$SliceLiveDataImpl$$ExternalSyntheticLambda0] */
        public SliceLiveDataImpl(Context context, Uri uri, VolumePanelDialog$$ExternalSyntheticLambda0 volumePanelDialog$$ExternalSyntheticLambda0) {
            this.mSliceViewManager = new SliceViewManagerWrapper(context);
            this.mUri = uri;
        }

        @Override // androidx.lifecycle.LiveData
        public final void onActive() {
            AsyncTask.execute(this.mUpdateSlice);
            Uri uri = this.mUri;
            if (uri != null) {
                this.mSliceViewManager.registerSliceCallback(uri, this.mSliceCallback);
            }
        }

        @Override // androidx.lifecycle.LiveData
        public final void onInactive() {
            Uri uri = this.mUri;
            if (uri != null) {
                this.mSliceViewManager.unregisterSliceCallback(uri, this.mSliceCallback);
            }
        }
    }
}
