package androidx.core.provider;

import android.graphics.Typeface;
import androidx.appcompat.widget.AppCompatTextHelper;
import androidx.core.graphics.TypefaceCompat;
import androidx.core.provider.FontRequestWorker;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class CallbackWrapper {
    public final TypefaceCompat.ResourcesCallbackAdapter mCallback;
    public final RequestExecutor$HandlerExecutor mExecutor;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: androidx.core.provider.CallbackWrapper$1, reason: invalid class name */
    public final class AnonymousClass1 implements Runnable {
        public final /* synthetic */ TypefaceCompat.ResourcesCallbackAdapter val$callback;
        public final /* synthetic */ Typeface val$typeface;

        public AnonymousClass1(TypefaceCompat.ResourcesCallbackAdapter resourcesCallbackAdapter, Typeface typeface) {
            this.val$callback = resourcesCallbackAdapter;
            this.val$typeface = typeface;
        }

        @Override // java.lang.Runnable
        public final void run() {
            TypefaceCompat.ResourcesCallbackAdapter resourcesCallbackAdapter = this.val$callback;
            Typeface typeface = this.val$typeface;
            AppCompatTextHelper.AnonymousClass1 anonymousClass1 = resourcesCallbackAdapter.mFontCallback;
            if (anonymousClass1 != null) {
                anonymousClass1.onFontRetrieved(typeface);
            }
        }
    }

    public CallbackWrapper(TypefaceCompat.ResourcesCallbackAdapter resourcesCallbackAdapter, RequestExecutor$HandlerExecutor requestExecutor$HandlerExecutor) {
        this.mCallback = resourcesCallbackAdapter;
        this.mExecutor = requestExecutor$HandlerExecutor;
    }

    public final void onTypefaceResult(FontRequestWorker.TypefaceResult typefaceResult) {
        int i = typefaceResult.mResult;
        TypefaceCompat.ResourcesCallbackAdapter resourcesCallbackAdapter = this.mCallback;
        RequestExecutor$HandlerExecutor requestExecutor$HandlerExecutor = this.mExecutor;
        if (i == 0) {
            requestExecutor$HandlerExecutor.execute(new AnonymousClass1(resourcesCallbackAdapter, typefaceResult.mTypeface));
        } else {
            requestExecutor$HandlerExecutor.execute(new AnonymousClass2(resourcesCallbackAdapter, i));
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: androidx.core.provider.CallbackWrapper$2, reason: invalid class name */
    public final class AnonymousClass2 implements Runnable {
        public final /* synthetic */ TypefaceCompat.ResourcesCallbackAdapter val$callback;

        public AnonymousClass2(TypefaceCompat.ResourcesCallbackAdapter resourcesCallbackAdapter, int i) {
        }

        @Override // java.lang.Runnable
        public final void run() {
        }
    }
}
