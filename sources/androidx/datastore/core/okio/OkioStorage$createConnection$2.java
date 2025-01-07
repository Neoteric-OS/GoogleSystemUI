package androidx.datastore.core.okio;

import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
import okio.Path;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class OkioStorage$createConnection$2 extends Lambda implements Function0 {
    final /* synthetic */ OkioStorage this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public OkioStorage$createConnection$2(OkioStorage okioStorage) {
        super(0);
        this.this$0 = okioStorage;
    }

    @Override // kotlin.jvm.functions.Function0
    public final Object invoke() {
        Synchronizer synchronizer = OkioStorage.activeFilesLock;
        OkioStorage okioStorage = this.this$0;
        synchronized (synchronizer) {
            OkioStorage.activeFiles.remove(((Path) okioStorage.canonicalPath$delegate.getValue()).bytes.utf8());
        }
        return Unit.INSTANCE;
    }
}
