package androidx.core.content.res;

import android.graphics.Typeface;
import androidx.appcompat.widget.AppCompatTextHelper;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final /* synthetic */ class ResourcesCompat$FontCallback$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ AppCompatTextHelper.AnonymousClass1 f$0;
    public final /* synthetic */ Typeface f$1;

    public /* synthetic */ ResourcesCompat$FontCallback$$ExternalSyntheticLambda0(AppCompatTextHelper.AnonymousClass1 anonymousClass1, Typeface typeface) {
        this.f$0 = anonymousClass1;
        this.f$1 = typeface;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.f$0.onFontRetrieved(this.f$1);
    }
}
