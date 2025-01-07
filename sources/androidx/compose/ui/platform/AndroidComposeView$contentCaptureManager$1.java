package androidx.compose.ui.platform;

import android.view.View;
import android.view.contentcapture.ContentCaptureSession;
import androidx.compose.ui.platform.coreshims.ContentCaptureSessionCompat;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionReferenceImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final /* synthetic */ class AndroidComposeView$contentCaptureManager$1 extends FunctionReferenceImpl implements Function0 {
    @Override // kotlin.jvm.functions.Function0
    public final Object invoke() {
        View view = (View) this.receiver;
        Function1 function1 = AndroidComposeView_androidKt.platformTextInputServiceInterceptor;
        view.setImportantForContentCapture(1);
        ContentCaptureSession contentCaptureSession = view.getContentCaptureSession();
        if (contentCaptureSession == null) {
            return null;
        }
        return new ContentCaptureSessionCompat(contentCaptureSession, view);
    }
}
