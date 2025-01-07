package androidx.compose.ui.text.font;

import androidx.compose.ui.text.platform.DispatcherKt;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlinx.coroutines.CoroutineExceptionHandler;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.SupervisorJobImpl;
import kotlinx.coroutines.android.HandlerContext;
import kotlinx.coroutines.internal.ContextScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class FontListFontFamilyTypefaceAdapter {
    public static final FontListFontFamilyTypefaceAdapter$special$$inlined$CoroutineExceptionHandler$1 DropExceptionHandler = new FontListFontFamilyTypefaceAdapter$special$$inlined$CoroutineExceptionHandler$1(CoroutineExceptionHandler.Key.$$INSTANCE);
    public final ContextScope asyncLoadScope;
    public final AsyncTypefaceCache asyncTypefaceCache;

    public FontListFontFamilyTypefaceAdapter(AsyncTypefaceCache asyncTypefaceCache) {
        EmptyCoroutineContext emptyCoroutineContext = EmptyCoroutineContext.INSTANCE;
        this.asyncTypefaceCache = asyncTypefaceCache;
        HandlerContext handlerContext = DispatcherKt.FontCacheManagementDispatcher;
        FontListFontFamilyTypefaceAdapter$special$$inlined$CoroutineExceptionHandler$1 fontListFontFamilyTypefaceAdapter$special$$inlined$CoroutineExceptionHandler$1 = DropExceptionHandler;
        fontListFontFamilyTypefaceAdapter$special$$inlined$CoroutineExceptionHandler$1.getClass();
        CoroutineContext plus = CoroutineContext.DefaultImpls.plus(fontListFontFamilyTypefaceAdapter$special$$inlined$CoroutineExceptionHandler$1, handlerContext).plus(emptyCoroutineContext);
        emptyCoroutineContext.getClass();
        this.asyncLoadScope = CoroutineScopeKt.CoroutineScope(plus.plus(new SupervisorJobImpl(null)));
    }
}
