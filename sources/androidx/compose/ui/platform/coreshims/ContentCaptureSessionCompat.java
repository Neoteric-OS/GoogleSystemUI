package androidx.compose.ui.platform.coreshims;

import android.view.View;
import android.view.contentcapture.ContentCaptureSession;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public class ContentCaptureSessionCompat {
    public final View mView;
    public final Object mWrappedObj;

    public ContentCaptureSessionCompat(ContentCaptureSession contentCaptureSession, View view) {
        this.mWrappedObj = contentCaptureSession;
        this.mView = view;
    }
}
