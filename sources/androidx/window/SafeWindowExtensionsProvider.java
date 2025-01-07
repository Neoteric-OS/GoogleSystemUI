package androidx.window;

import androidx.window.reflection.ReflectionUtils;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import kotlin.jvm.functions.Function0;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class SafeWindowExtensionsProvider {
    public final ClassLoader loader;

    public SafeWindowExtensionsProvider(ClassLoader classLoader) {
        this.loader = classLoader;
    }

    public final boolean isWindowExtensionsValid$window_release() {
        try {
            new Function0() { // from class: androidx.window.SafeWindowExtensionsProvider$isWindowExtensionsPresent$1
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return SafeWindowExtensionsProvider.this.loader.loadClass("androidx.window.extensions.WindowExtensionsProvider");
                }
            }.invoke();
        } catch (ClassNotFoundException | NoClassDefFoundError unused) {
        }
        return ReflectionUtils.validateReflection$window_release("WindowExtensionsProvider#getWindowExtensions is not valid", new Function0() { // from class: androidx.window.SafeWindowExtensionsProvider$isWindowExtensionsValid$1
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                boolean z = false;
                Class[] clsArr = new Class[0];
                Method declaredMethod = SafeWindowExtensionsProvider.this.loader.loadClass("androidx.window.extensions.WindowExtensionsProvider").getDeclaredMethod("getWindowExtensions", null);
                if (declaredMethod.getReturnType().equals(SafeWindowExtensionsProvider.this.loader.loadClass("androidx.window.extensions.WindowExtensions")) && Modifier.isPublic(declaredMethod.getModifiers())) {
                    z = true;
                }
                return Boolean.valueOf(z);
            }
        });
    }
}
