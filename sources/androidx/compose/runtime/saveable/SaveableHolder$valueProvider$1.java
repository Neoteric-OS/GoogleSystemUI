package androidx.compose.runtime.saveable;

import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class SaveableHolder$valueProvider$1 extends Lambda implements Function0 {
    final /* synthetic */ SaveableHolder this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SaveableHolder$valueProvider$1(SaveableHolder saveableHolder) {
        super(0);
        this.this$0 = saveableHolder;
    }

    @Override // kotlin.jvm.functions.Function0
    public final Object invoke() {
        SaveableHolder saveableHolder = this.this$0;
        Saver saver = saveableHolder.saver;
        Object obj = saveableHolder.value;
        if (obj != null) {
            return saver.save(saveableHolder, obj);
        }
        throw new IllegalArgumentException("Value should be initialized");
    }
}
