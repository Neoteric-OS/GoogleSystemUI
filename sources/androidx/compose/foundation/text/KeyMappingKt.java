package androidx.compose.foundation.text;

import androidx.compose.ui.input.key.KeyEvent;
import kotlin.jvm.internal.PropertyReference1Impl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class KeyMappingKt {
    public static final KeyMappingKt$defaultKeyMapping$2$1 defaultKeyMapping = new KeyMappingKt$defaultKeyMapping$2$1(new KeyMappingKt$commonKeyMapping$1(new PropertyReference1Impl() { // from class: androidx.compose.foundation.text.KeyMappingKt$defaultKeyMapping$1
        @Override // kotlin.jvm.internal.PropertyReference1Impl, kotlin.reflect.KProperty1
        public final Object get(Object obj) {
            return Boolean.valueOf(((KeyEvent) obj).nativeKeyEvent.isCtrlPressed());
        }
    }));
}
