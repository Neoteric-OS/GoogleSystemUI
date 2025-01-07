package androidx.window.embedding;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.IBinder;
import android.view.WindowMetrics;
import androidx.window.SafeWindowExtensionsProvider;
import androidx.window.core.ConsumerAdapter;
import androidx.window.core.ExtensionsUtil;
import androidx.window.extensions.WindowExtensions;
import androidx.window.extensions.core.util.function.Consumer;
import androidx.window.extensions.core.util.function.Function;
import androidx.window.extensions.core.util.function.Predicate;
import androidx.window.extensions.embedding.ActivityEmbeddingComponent;
import androidx.window.extensions.embedding.ActivityRule;
import androidx.window.extensions.embedding.ActivityStack;
import androidx.window.extensions.embedding.AnimationBackground;
import androidx.window.extensions.embedding.AnimationParams;
import androidx.window.extensions.embedding.DividerAttributes;
import androidx.window.extensions.embedding.EmbeddedActivityWindowInfo;
import androidx.window.extensions.embedding.SplitAttributes;
import androidx.window.extensions.embedding.SplitAttributesCalculatorParams;
import androidx.window.extensions.embedding.SplitInfo;
import androidx.window.extensions.embedding.SplitPairRule;
import androidx.window.extensions.embedding.SplitPinRule;
import androidx.window.extensions.embedding.SplitPlaceholderRule;
import androidx.window.extensions.embedding.SplitRule;
import androidx.window.extensions.embedding.WindowAttributes;
import androidx.window.extensions.layout.WindowLayoutInfo;
import androidx.window.reflection.ReflectionUtils;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Set;
import java.util.concurrent.Executor;
import kotlin.jvm.functions.Function0;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class SafeActivityEmbeddingComponentProvider {
    public final ConsumerAdapter consumerAdapter;
    public final ClassLoader loader;
    public final SafeWindowExtensionsProvider safeWindowExtensionsProvider;
    public final WindowExtensions windowExtensions;

    public SafeActivityEmbeddingComponentProvider(ClassLoader classLoader, ConsumerAdapter consumerAdapter, WindowExtensions windowExtensions) {
        this.loader = classLoader;
        this.consumerAdapter = consumerAdapter;
        this.windowExtensions = windowExtensions;
        this.safeWindowExtensionsProvider = new SafeWindowExtensionsProvider(classLoader);
    }

    public final ActivityEmbeddingComponent getActivityEmbeddingComponent() {
        boolean z = false;
        if (this.safeWindowExtensionsProvider.isWindowExtensionsValid$window_release() && ReflectionUtils.validateReflection$window_release("WindowExtensions#getActivityEmbeddingComponent is not valid", new Function0() { // from class: androidx.window.embedding.SafeActivityEmbeddingComponentProvider$isActivityEmbeddingComponentValid$1
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                Class loadClass;
                boolean z2 = false;
                Class[] clsArr = new Class[0];
                Method method = SafeActivityEmbeddingComponentProvider.this.safeWindowExtensionsProvider.loader.loadClass("androidx.window.extensions.WindowExtensions").getMethod("getActivityEmbeddingComponent", null);
                loadClass = SafeActivityEmbeddingComponentProvider.this.loader.loadClass("androidx.window.extensions.embedding.ActivityEmbeddingComponent");
                if (Modifier.isPublic(method.getModifiers()) && method.getReturnType().equals(loadClass)) {
                    z2 = true;
                }
                return Boolean.valueOf(z2);
            }
        })) {
            int safeVendorApiLevel = ExtensionsUtil.getSafeVendorApiLevel();
            if (safeVendorApiLevel == 1) {
                z = hasValidVendorApiLevel1$window_release();
            } else if (safeVendorApiLevel == 2) {
                z = hasValidVendorApiLevel2$window_release();
            } else if (3 <= safeVendorApiLevel && safeVendorApiLevel < 5) {
                z = hasValidVendorApiLevel3$window_release();
            } else if (safeVendorApiLevel == 5) {
                z = hasValidVendorApiLevel5$window_release();
            } else if (safeVendorApiLevel == 6) {
                z = hasValidVendorApiLevel6$window_release();
            } else if (7 <= safeVendorApiLevel && safeVendorApiLevel <= Integer.MAX_VALUE && hasValidVendorApiLevel6$window_release() && ReflectionUtils.validateReflection$window_release("SplitAttributes#getAnimationParams is not valid", new Function0() { // from class: androidx.window.embedding.SafeActivityEmbeddingComponentProvider$isMethodGetAnimationParamsValid$1
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    boolean z2 = false;
                    Class[] clsArr = new Class[0];
                    Method method = androidx.window.extensions.embedding.SplitAttributes.class.getMethod("getAnimationParams", null);
                    if (Modifier.isPublic(method.getModifiers()) && method.getReturnType().equals(AnimationParams.class)) {
                        z2 = true;
                    }
                    return Boolean.valueOf(z2);
                }
            }) && ReflectionUtils.validateReflection$window_release("SplitAttributes#setAnimationParams is not valid", new Function0() { // from class: androidx.window.embedding.SafeActivityEmbeddingComponentProvider$isMethodSetAnimationParamsValid$1
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    Method method = SplitAttributes.Builder.class.getMethod("setAnimationParams", AnimationParams.class);
                    return Boolean.valueOf(Modifier.isPublic(method.getModifiers()) && method.getReturnType().equals(SplitAttributes.Builder.class));
                }
            }) && ReflectionUtils.validateReflection$window_release("DividerAttributes#isDraggingToFullscreenAllowed is not valid", new Function0() { // from class: androidx.window.embedding.SafeActivityEmbeddingComponentProvider$isMethodIsDraggingToFullscreenAllowedValid$1
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    boolean z2 = false;
                    Class[] clsArr = new Class[0];
                    Method method = androidx.window.extensions.embedding.DividerAttributes.class.getMethod("isDraggingToFullscreenAllowed", null);
                    if (Modifier.isPublic(method.getModifiers())) {
                        if (method.getReturnType().equals(Boolean.TYPE)) {
                            z2 = true;
                        }
                    }
                    return Boolean.valueOf(z2);
                }
            }) && ReflectionUtils.validateReflection$window_release("DividerAttributes.Builder#setDraggingToFullscreenAllowed is not valid", new Function0() { // from class: androidx.window.embedding.SafeActivityEmbeddingComponentProvider$isMethodSetDraggingToFullscreenAllowedValid$1
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    Method method = DividerAttributes.Builder.class.getMethod("setDraggingToFullscreenAllowed", Boolean.TYPE);
                    return Boolean.valueOf(Modifier.isPublic(method.getModifiers()) && method.getReturnType().equals(DividerAttributes.Builder.class));
                }
            }) && ReflectionUtils.validateReflection$window_release("Class AnimationParams is not valid", new Function0() { // from class: androidx.window.embedding.SafeActivityEmbeddingComponentProvider$isClassAnimationParamsValid$1
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    Field declaredField = AnimationParams.class.getDeclaredField("DEFAULT_ANIMATION_RESOURCES_ID");
                    boolean z2 = false;
                    Class[] clsArr = new Class[0];
                    Method method = AnimationParams.class.getMethod("getAnimationBackground", null);
                    Class[] clsArr2 = new Class[0];
                    Method method2 = AnimationParams.class.getMethod("getOpenAnimationResId", null);
                    Class[] clsArr3 = new Class[0];
                    Method method3 = AnimationParams.class.getMethod("getCloseAnimationResId", null);
                    Class[] clsArr4 = new Class[0];
                    Method method4 = AnimationParams.class.getMethod("getChangeAnimationResId", null);
                    if (Modifier.isPublic(declaredField.getModifiers()) && Modifier.isPublic(method.getModifiers()) && method.getReturnType().equals(AnimationBackground.class) && Modifier.isPublic(method2.getModifiers())) {
                        Class cls = Integer.TYPE;
                        if (method2.getReturnType().equals(cls) && Modifier.isPublic(method3.getModifiers()) && method3.getReturnType().equals(cls) && Modifier.isPublic(method4.getModifiers()) && method4.getReturnType().equals(cls)) {
                            z2 = true;
                        }
                    }
                    return Boolean.valueOf(z2);
                }
            }) && ReflectionUtils.validateReflection$window_release("Class AnimationParams.Builder is not valid", new Function0() { // from class: androidx.window.embedding.SafeActivityEmbeddingComponentProvider$isClassAnimationParamsBuilderValid$1
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    Method method = AnimationParams.Builder.class.getMethod("setAnimationBackground", AnimationBackground.class);
                    Class cls = Integer.TYPE;
                    Method method2 = AnimationParams.Builder.class.getMethod("setOpenAnimationResId", cls);
                    Method method3 = AnimationParams.Builder.class.getMethod("setCloseAnimationResId", cls);
                    Method method4 = AnimationParams.Builder.class.getMethod("setChangeAnimationResId", cls);
                    return Boolean.valueOf(Modifier.isPublic(method.getModifiers()) && method.getReturnType().equals(AnimationParams.Builder.class) && Modifier.isPublic(method2.getModifiers()) && method2.getReturnType().equals(AnimationParams.Builder.class) && Modifier.isPublic(method3.getModifiers()) && method3.getReturnType().equals(AnimationParams.Builder.class) && Modifier.isPublic(method4.getModifiers()) && method4.getReturnType().equals(AnimationParams.Builder.class));
                }
            })) {
                z = true;
            }
        }
        if (!z) {
            return null;
        }
        try {
            return this.windowExtensions.getActivityEmbeddingComponent();
        } catch (UnsupportedOperationException unused) {
            return null;
        }
    }

    public final boolean hasValidVendorApiLevel1$window_release() {
        return ReflectionUtils.validateReflection$window_release("ActivityEmbeddingComponent#setEmbeddingRules is not valid", new Function0() { // from class: androidx.window.embedding.SafeActivityEmbeddingComponentProvider$isMethodSetEmbeddingRulesValid$1
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                Class loadClass;
                loadClass = SafeActivityEmbeddingComponentProvider.this.loader.loadClass("androidx.window.extensions.embedding.ActivityEmbeddingComponent");
                return Boolean.valueOf(Modifier.isPublic(loadClass.getMethod("setEmbeddingRules", Set.class).getModifiers()));
            }
        }) && ReflectionUtils.validateReflection$window_release("ActivityEmbeddingComponent#isActivityEmbedded is not valid", new Function0() { // from class: androidx.window.embedding.SafeActivityEmbeddingComponentProvider$isMethodIsActivityEmbeddedValid$1
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                Class loadClass;
                loadClass = SafeActivityEmbeddingComponentProvider.this.loader.loadClass("androidx.window.extensions.embedding.ActivityEmbeddingComponent");
                Method method = loadClass.getMethod("isActivityEmbedded", Activity.class);
                return Boolean.valueOf(Modifier.isPublic(method.getModifiers()) && method.getReturnType().equals(Boolean.TYPE));
            }
        }) && ReflectionUtils.validateReflection$window_release("ActivityEmbeddingComponent#setSplitInfoCallback is not valid", new Function0() { // from class: androidx.window.embedding.SafeActivityEmbeddingComponentProvider$isMethodSetSplitInfoCallbackJavaConsumerValid$1
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                Class<?> cls;
                Class loadClass;
                try {
                    cls = SafeActivityEmbeddingComponentProvider.this.consumerAdapter.loader.loadClass("java.util.function.Consumer");
                } catch (ClassNotFoundException unused) {
                    cls = null;
                }
                if (cls == null) {
                    return Boolean.FALSE;
                }
                loadClass = SafeActivityEmbeddingComponentProvider.this.loader.loadClass("androidx.window.extensions.embedding.ActivityEmbeddingComponent");
                return Boolean.valueOf(Modifier.isPublic(loadClass.getMethod("setSplitInfoCallback", cls).getModifiers()));
            }
        }) && ReflectionUtils.validateReflection$window_release("SplitRule#getSplitRatio is not valid", new Function0() { // from class: androidx.window.embedding.SafeActivityEmbeddingComponentProvider$isMethodGetSplitRatioValid$1
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                boolean z = false;
                Class[] clsArr = new Class[0];
                Method method = SplitRule.class.getMethod("getSplitRatio", null);
                if (Modifier.isPublic(method.getModifiers())) {
                    if (method.getReturnType().equals(Float.TYPE)) {
                        z = true;
                    }
                }
                return Boolean.valueOf(z);
            }
        }) && ReflectionUtils.validateReflection$window_release("SplitRule#getLayoutDirection is not valid", new Function0() { // from class: androidx.window.embedding.SafeActivityEmbeddingComponentProvider$isMethodGetLayoutDirectionValid$1
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                boolean z = false;
                Class[] clsArr = new Class[0];
                Method method = SplitRule.class.getMethod("getLayoutDirection", null);
                if (Modifier.isPublic(method.getModifiers())) {
                    if (method.getReturnType().equals(Integer.TYPE)) {
                        z = true;
                    }
                }
                return Boolean.valueOf(z);
            }
        }) && ReflectionUtils.validateReflection$window_release("Class ActivityRule is not valid", new Function0() { // from class: androidx.window.embedding.SafeActivityEmbeddingComponentProvider$isClassActivityRuleValid$1
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                boolean z = false;
                Class[] clsArr = new Class[0];
                Method method = ActivityRule.class.getMethod("shouldAlwaysExpand", null);
                if (Modifier.isPublic(method.getModifiers())) {
                    if (method.getReturnType().equals(Boolean.TYPE)) {
                        z = true;
                    }
                }
                return Boolean.valueOf(z);
            }
        }) && ReflectionUtils.validateReflection$window_release("Class ActivityRule.Builder is not valid", new Function0() { // from class: androidx.window.embedding.SafeActivityEmbeddingComponentProvider$isClassActivityRuleBuilderLevel1Valid$1
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                Method method = ActivityRule.Builder.class.getMethod("setShouldAlwaysExpand", Boolean.TYPE);
                return Boolean.valueOf(Modifier.isPublic(method.getModifiers()) && method.getReturnType().equals(ActivityRule.Builder.class));
            }
        }) && ReflectionUtils.validateReflection$window_release("Class SplitInfo is not valid", new Function0() { // from class: androidx.window.embedding.SafeActivityEmbeddingComponentProvider$isClassSplitInfoValid$1
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                boolean z = false;
                Class[] clsArr = new Class[0];
                Method method = androidx.window.extensions.embedding.SplitInfo.class.getMethod("getPrimaryActivityStack", null);
                Class[] clsArr2 = new Class[0];
                Method method2 = androidx.window.extensions.embedding.SplitInfo.class.getMethod("getSecondaryActivityStack", null);
                Class[] clsArr3 = new Class[0];
                Method method3 = androidx.window.extensions.embedding.SplitInfo.class.getMethod("getSplitRatio", null);
                if (Modifier.isPublic(method.getModifiers()) && method.getReturnType().equals(androidx.window.extensions.embedding.ActivityStack.class) && Modifier.isPublic(method2.getModifiers()) && method2.getReturnType().equals(androidx.window.extensions.embedding.ActivityStack.class) && Modifier.isPublic(method3.getModifiers())) {
                    if (method3.getReturnType().equals(Float.TYPE)) {
                        z = true;
                    }
                }
                return Boolean.valueOf(z);
            }
        }) && ReflectionUtils.validateReflection$window_release("Class SplitPairRule is not valid", new Function0() { // from class: androidx.window.embedding.SafeActivityEmbeddingComponentProvider$isClassSplitPairRuleValid$1
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                boolean z = false;
                Class[] clsArr = new Class[0];
                Method method = SplitPairRule.class.getMethod("getFinishPrimaryWithSecondary", null);
                Class[] clsArr2 = new Class[0];
                Method method2 = SplitPairRule.class.getMethod("getFinishSecondaryWithPrimary", null);
                Class[] clsArr3 = new Class[0];
                Method method3 = SplitPairRule.class.getMethod("shouldClearTop", null);
                if (Modifier.isPublic(method.getModifiers())) {
                    Class cls = Integer.TYPE;
                    if (method.getReturnType().equals(cls) && Modifier.isPublic(method2.getModifiers()) && method2.getReturnType().equals(cls) && Modifier.isPublic(method3.getModifiers())) {
                        if (method3.getReturnType().equals(Boolean.TYPE)) {
                            z = true;
                        }
                    }
                }
                return Boolean.valueOf(z);
            }
        }) && ReflectionUtils.validateReflection$window_release("Class SplitPairRule.Builder is not valid", new Function0() { // from class: androidx.window.embedding.SafeActivityEmbeddingComponentProvider$isClassSplitPairRuleBuilderLevel1Valid$1
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                Method method = SplitPairRule.Builder.class.getMethod("setSplitRatio", Float.TYPE);
                Method method2 = SplitPairRule.Builder.class.getMethod("setLayoutDirection", Integer.TYPE);
                return Boolean.valueOf(Modifier.isPublic(method.getModifiers()) && method.getReturnType().equals(SplitPairRule.Builder.class) && Modifier.isPublic(method2.getModifiers()) && method2.getReturnType().equals(SplitPairRule.Builder.class));
            }
        }) && ReflectionUtils.validateReflection$window_release("Class SplitPlaceholderRule is not valid", new Function0() { // from class: androidx.window.embedding.SafeActivityEmbeddingComponentProvider$isClassSplitPlaceholderRuleValid$1
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                boolean z = false;
                Class[] clsArr = new Class[0];
                Method method = SplitPlaceholderRule.class.getMethod("getPlaceholderIntent", null);
                Class[] clsArr2 = new Class[0];
                Method method2 = SplitPlaceholderRule.class.getMethod("isSticky", null);
                Class[] clsArr3 = new Class[0];
                Method method3 = SplitPlaceholderRule.class.getMethod("getFinishPrimaryWithSecondary", null);
                if (Modifier.isPublic(method.getModifiers()) && method.getReturnType().equals(Intent.class) && Modifier.isPublic(method2.getModifiers())) {
                    if (method2.getReturnType().equals(Boolean.TYPE) && Modifier.isPublic(method3.getModifiers())) {
                        if (method3.getReturnType().equals(Integer.TYPE)) {
                            z = true;
                        }
                    }
                }
                return Boolean.valueOf(z);
            }
        }) && ReflectionUtils.validateReflection$window_release("Class SplitPlaceholderRule.Builder is not valid", new Function0() { // from class: androidx.window.embedding.SafeActivityEmbeddingComponentProvider$isClassSplitPlaceholderRuleBuilderLevel1Valid$1
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                Method method = SplitPlaceholderRule.Builder.class.getMethod("setSplitRatio", Float.TYPE);
                Class cls = Integer.TYPE;
                Method method2 = SplitPlaceholderRule.Builder.class.getMethod("setLayoutDirection", cls);
                Method method3 = SplitPlaceholderRule.Builder.class.getMethod("setSticky", Boolean.TYPE);
                Method method4 = SplitPlaceholderRule.Builder.class.getMethod("setFinishPrimaryWithSecondary", cls);
                return Boolean.valueOf(Modifier.isPublic(method.getModifiers()) && method.getReturnType().equals(SplitPlaceholderRule.Builder.class) && Modifier.isPublic(method2.getModifiers()) && method2.getReturnType().equals(SplitPlaceholderRule.Builder.class) && Modifier.isPublic(method3.getModifiers()) && method3.getReturnType().equals(SplitPlaceholderRule.Builder.class) && Modifier.isPublic(method4.getModifiers()) && method4.getReturnType().equals(SplitPlaceholderRule.Builder.class));
            }
        });
    }

    public final boolean hasValidVendorApiLevel2$window_release() {
        return hasValidVendorApiLevel1$window_release() && ReflectionUtils.validateReflection$window_release("ActivityEmbeddingComponent#setSplitInfoCallback is not valid", new Function0() { // from class: androidx.window.embedding.SafeActivityEmbeddingComponentProvider$isMethodSetSplitInfoCallbackWindowConsumerValid$1
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                Class loadClass;
                loadClass = SafeActivityEmbeddingComponentProvider.this.loader.loadClass("androidx.window.extensions.embedding.ActivityEmbeddingComponent");
                return Boolean.valueOf(Modifier.isPublic(loadClass.getMethod("setSplitInfoCallback", Consumer.class).getModifiers()));
            }
        }) && ReflectionUtils.validateReflection$window_release("ActivityEmbeddingComponent#clearSplitInfoCallback is not valid", new Function0() { // from class: androidx.window.embedding.SafeActivityEmbeddingComponentProvider$isMethodClearSplitInfoCallbackValid$1
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                Class loadClass;
                loadClass = SafeActivityEmbeddingComponentProvider.this.loader.loadClass("androidx.window.extensions.embedding.ActivityEmbeddingComponent");
                Class[] clsArr = new Class[0];
                return Boolean.valueOf(Modifier.isPublic(loadClass.getMethod("clearSplitInfoCallback", null).getModifiers()));
            }
        }) && ReflectionUtils.validateReflection$window_release("ActivityEmbeddingComponent#setSplitAttributesCalculator is not valid", new Function0() { // from class: androidx.window.embedding.SafeActivityEmbeddingComponentProvider$isMethodSplitAttributesCalculatorValid$1
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                Class loadClass;
                Class loadClass2;
                loadClass = SafeActivityEmbeddingComponentProvider.this.loader.loadClass("androidx.window.extensions.embedding.ActivityEmbeddingComponent");
                Method method = loadClass.getMethod("setSplitAttributesCalculator", Function.class);
                loadClass2 = SafeActivityEmbeddingComponentProvider.this.loader.loadClass("androidx.window.extensions.embedding.ActivityEmbeddingComponent");
                Class[] clsArr = new Class[0];
                return Boolean.valueOf(Modifier.isPublic(method.getModifiers()) && Modifier.isPublic(loadClass2.getMethod("clearSplitAttributesCalculator", null).getModifiers()));
            }
        }) && ReflectionUtils.validateReflection$window_release("SplitInfo#getSplitAttributes is not valid", new Function0() { // from class: androidx.window.embedding.SafeActivityEmbeddingComponentProvider$isMethodGetSplitAttributesValid$1
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                boolean z = false;
                Class[] clsArr = new Class[0];
                Method method = androidx.window.extensions.embedding.SplitInfo.class.getMethod("getSplitAttributes", null);
                if (Modifier.isPublic(method.getModifiers()) && method.getReturnType().equals(androidx.window.extensions.embedding.SplitAttributes.class)) {
                    z = true;
                }
                return Boolean.valueOf(z);
            }
        }) && ReflectionUtils.validateReflection$window_release("SplitPlaceholderRule#getFinishPrimaryWithPlaceholder is not valid", new Function0() { // from class: androidx.window.embedding.SafeActivityEmbeddingComponentProvider$isMethodGetFinishPrimaryWithPlaceholderValid$1
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                boolean z = false;
                Class[] clsArr = new Class[0];
                Method method = SplitPlaceholderRule.class.getMethod("getFinishPrimaryWithPlaceholder", null);
                if (Modifier.isPublic(method.getModifiers())) {
                    if (method.getReturnType().equals(Integer.TYPE)) {
                        z = true;
                    }
                }
                return Boolean.valueOf(z);
            }
        }) && ReflectionUtils.validateReflection$window_release("SplitRule#getDefaultSplitAttributes is not valid", new Function0() { // from class: androidx.window.embedding.SafeActivityEmbeddingComponentProvider$isMethodGetDefaultSplitAttributesValid$1
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                boolean z = false;
                Class[] clsArr = new Class[0];
                Method method = SplitRule.class.getMethod("getDefaultSplitAttributes", null);
                if (Modifier.isPublic(method.getModifiers()) && method.getReturnType().equals(androidx.window.extensions.embedding.SplitAttributes.class)) {
                    z = true;
                }
                return Boolean.valueOf(z);
            }
        }) && ReflectionUtils.validateReflection$window_release("Class ActivityRule.Builder is not valid", new Function0() { // from class: androidx.window.embedding.SafeActivityEmbeddingComponentProvider$isClassActivityRuleBuilderLevel2Valid$1
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                boolean z = false;
                Constructor declaredConstructor = ActivityRule.Builder.class.getDeclaredConstructor(Predicate.class, Predicate.class);
                Method method = ActivityRule.Builder.class.getMethod("setTag", String.class);
                if (Modifier.isPublic(declaredConstructor.getModifiers()) && Modifier.isPublic(method.getModifiers()) && method.getReturnType().equals(ActivityRule.Builder.class)) {
                    z = true;
                }
                return Boolean.valueOf(z);
            }
        }) && ReflectionUtils.validateReflection$window_release("Class EmbeddingRule is not valid", new Function0() { // from class: androidx.window.embedding.SafeActivityEmbeddingComponentProvider$isClassEmbeddingRuleValid$1
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                boolean z = false;
                Class[] clsArr = new Class[0];
                Method method = EmbeddingRule.class.getMethod("getTag", null);
                if (Modifier.isPublic(method.getModifiers()) && method.getReturnType().equals(String.class)) {
                    z = true;
                }
                return Boolean.valueOf(z);
            }
        }) && ReflectionUtils.validateReflection$window_release("Class SplitAttributes is not valid", new Function0() { // from class: androidx.window.embedding.SafeActivityEmbeddingComponentProvider$isClassSplitAttributesValid$1
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                boolean z = false;
                Class[] clsArr = new Class[0];
                Method method = androidx.window.extensions.embedding.SplitAttributes.class.getMethod("getLayoutDirection", null);
                Class[] clsArr2 = new Class[0];
                Method method2 = androidx.window.extensions.embedding.SplitAttributes.class.getMethod("getSplitType", null);
                Method method3 = SplitAttributes.Builder.class.getMethod("setSplitType", SplitAttributes.SplitType.class);
                Class cls = Integer.TYPE;
                Method method4 = SplitAttributes.Builder.class.getMethod("setLayoutDirection", cls);
                if (Modifier.isPublic(method.getModifiers()) && method.getReturnType().equals(cls) && Modifier.isPublic(method2.getModifiers()) && method2.getReturnType().equals(SplitAttributes.SplitType.class) && Modifier.isPublic(method3.getModifiers()) && Modifier.isPublic(method4.getModifiers())) {
                    z = true;
                }
                return Boolean.valueOf(z);
            }
        }) && ReflectionUtils.validateReflection$window_release("Class SplitAttributesCalculatorParams is not valid", new Function0() { // from class: androidx.window.embedding.SafeActivityEmbeddingComponentProvider$isClassSplitAttributesCalculatorParamsValid$1
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                boolean isPublic;
                boolean equals;
                boolean isPublic2;
                boolean equals2;
                boolean isPublic3;
                boolean equals3;
                boolean isPublic4;
                boolean equals4;
                boolean isPublic5;
                boolean equals5;
                boolean isPublic6;
                boolean equals6;
                boolean z = false;
                Class[] clsArr = new Class[0];
                Method method = SplitAttributesCalculatorParams.class.getMethod("getParentWindowMetrics", null);
                Class[] clsArr2 = new Class[0];
                Method method2 = SplitAttributesCalculatorParams.class.getMethod("getParentConfiguration", null);
                Class[] clsArr3 = new Class[0];
                Method method3 = SplitAttributesCalculatorParams.class.getMethod("getDefaultSplitAttributes", null);
                Class[] clsArr4 = new Class[0];
                Method method4 = SplitAttributesCalculatorParams.class.getMethod("areDefaultConstraintsSatisfied", null);
                Class[] clsArr5 = new Class[0];
                Method method5 = SplitAttributesCalculatorParams.class.getMethod("getParentWindowLayoutInfo", null);
                Class[] clsArr6 = new Class[0];
                Method method6 = SplitAttributesCalculatorParams.class.getMethod("getSplitRuleTag", null);
                isPublic = Modifier.isPublic(method.getModifiers());
                if (isPublic) {
                    equals = method.getReturnType().equals(WindowMetrics.class);
                    if (equals) {
                        isPublic2 = Modifier.isPublic(method2.getModifiers());
                        if (isPublic2) {
                            equals2 = method2.getReturnType().equals(Configuration.class);
                            if (equals2) {
                                isPublic3 = Modifier.isPublic(method3.getModifiers());
                                if (isPublic3) {
                                    equals3 = method3.getReturnType().equals(androidx.window.extensions.embedding.SplitAttributes.class);
                                    if (equals3) {
                                        isPublic4 = Modifier.isPublic(method4.getModifiers());
                                        if (isPublic4) {
                                            equals4 = method4.getReturnType().equals(Boolean.TYPE);
                                            if (equals4) {
                                                isPublic5 = Modifier.isPublic(method5.getModifiers());
                                                if (isPublic5) {
                                                    equals5 = method5.getReturnType().equals(WindowLayoutInfo.class);
                                                    if (equals5) {
                                                        isPublic6 = Modifier.isPublic(method6.getModifiers());
                                                        if (isPublic6) {
                                                            equals6 = method6.getReturnType().equals(String.class);
                                                            if (equals6) {
                                                                z = true;
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                return Boolean.valueOf(z);
            }
        }) && ReflectionUtils.validateReflection$window_release("Class SplitAttributes.SplitType is not valid", new Function0() { // from class: androidx.window.embedding.SafeActivityEmbeddingComponentProvider$isClassSplitTypeValid$1
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                Class cls = Float.TYPE;
                Constructor declaredConstructor = SplitAttributes.SplitType.RatioSplitType.class.getDeclaredConstructor(cls);
                Class[] clsArr = new Class[0];
                Method method = SplitAttributes.SplitType.RatioSplitType.class.getMethod("getRatio", null);
                Class[] clsArr2 = new Class[0];
                Method method2 = SplitAttributes.SplitType.RatioSplitType.class.getMethod("splitEqually", null);
                Constructor declaredConstructor2 = SplitAttributes.SplitType.HingeSplitType.class.getDeclaredConstructor(SplitAttributes.SplitType.class);
                Class[] clsArr3 = new Class[0];
                Method method3 = SplitAttributes.SplitType.HingeSplitType.class.getMethod("getFallbackSplitType", null);
                Class[] clsArr4 = new Class[0];
                return Boolean.valueOf(Modifier.isPublic(declaredConstructor.getModifiers()) && Modifier.isPublic(method.getModifiers()) && method.getReturnType().equals(cls) && Modifier.isPublic(declaredConstructor2.getModifiers()) && Modifier.isPublic(method2.getModifiers()) && method2.getReturnType().equals(SplitAttributes.SplitType.RatioSplitType.class) && Modifier.isPublic(method3.getModifiers()) && method3.getReturnType().equals(SplitAttributes.SplitType.class) && Modifier.isPublic(SplitAttributes.SplitType.ExpandContainersSplitType.class.getDeclaredConstructor(null).getModifiers()));
            }
        }) && ReflectionUtils.validateReflection$window_release("Class SplitPairRule.Builder is not valid", new Function0() { // from class: androidx.window.embedding.SafeActivityEmbeddingComponentProvider$isClassSplitPairRuleBuilderLevel2Valid$1
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                boolean z = false;
                Constructor declaredConstructor = SplitPairRule.Builder.class.getDeclaredConstructor(Predicate.class, Predicate.class, Predicate.class);
                Method method = SplitPairRule.Builder.class.getMethod("setDefaultSplitAttributes", androidx.window.extensions.embedding.SplitAttributes.class);
                Method method2 = SplitPairRule.Builder.class.getMethod("setTag", String.class);
                if (Modifier.isPublic(declaredConstructor.getModifiers()) && Modifier.isPublic(method.getModifiers()) && method.getReturnType().equals(SplitPairRule.Builder.class) && Modifier.isPublic(method2.getModifiers()) && method2.getReturnType().equals(SplitPairRule.Builder.class)) {
                    z = true;
                }
                return Boolean.valueOf(z);
            }
        }) && ReflectionUtils.validateReflection$window_release("Class SplitPlaceholderRule.Builder is not valid", new Function0() { // from class: androidx.window.embedding.SafeActivityEmbeddingComponentProvider$isClassSplitPlaceholderRuleBuilderLevel2Valid$1
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                boolean z = false;
                Constructor declaredConstructor = SplitPlaceholderRule.Builder.class.getDeclaredConstructor(Intent.class, Predicate.class, Predicate.class, Predicate.class);
                Method method = SplitPlaceholderRule.Builder.class.getMethod("setDefaultSplitAttributes", androidx.window.extensions.embedding.SplitAttributes.class);
                Method method2 = SplitPlaceholderRule.Builder.class.getMethod("setFinishPrimaryWithPlaceholder", Integer.TYPE);
                Method method3 = SplitPlaceholderRule.Builder.class.getMethod("setTag", String.class);
                if (Modifier.isPublic(declaredConstructor.getModifiers()) && Modifier.isPublic(method.getModifiers()) && method.getReturnType().equals(SplitPlaceholderRule.Builder.class) && Modifier.isPublic(method2.getModifiers()) && method2.getReturnType().equals(SplitPlaceholderRule.Builder.class) && Modifier.isPublic(method3.getModifiers()) && method3.getReturnType().equals(SplitPlaceholderRule.Builder.class)) {
                    z = true;
                }
                return Boolean.valueOf(z);
            }
        });
    }

    public final boolean hasValidVendorApiLevel3$window_release() {
        return hasValidVendorApiLevel2$window_release() && ReflectionUtils.validateReflection$window_release("#invalidateTopVisibleSplitAttributes is not valid", new Function0() { // from class: androidx.window.embedding.SafeActivityEmbeddingComponentProvider$isMethodInvalidateTopVisibleSplitAttributesValid$1
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                Class loadClass;
                loadClass = SafeActivityEmbeddingComponentProvider.this.loader.loadClass("androidx.window.extensions.embedding.ActivityEmbeddingComponent");
                Class[] clsArr = new Class[0];
                return Boolean.valueOf(Modifier.isPublic(loadClass.getMethod("invalidateTopVisibleSplitAttributes", null).getModifiers()));
            }
        }) && ReflectionUtils.validateReflection$window_release("#updateSplitAttributes is not valid", new Function0() { // from class: androidx.window.embedding.SafeActivityEmbeddingComponentProvider$isMethodUpdateSplitAttributesValid$1
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                Class loadClass;
                loadClass = SafeActivityEmbeddingComponentProvider.this.loader.loadClass("androidx.window.extensions.embedding.ActivityEmbeddingComponent");
                return Boolean.valueOf(Modifier.isPublic(loadClass.getMethod("updateSplitAttributes", IBinder.class, androidx.window.extensions.embedding.SplitAttributes.class).getModifiers()));
            }
        }) && ReflectionUtils.validateReflection$window_release("SplitInfo#getToken is not valid", new Function0() { // from class: androidx.window.embedding.SafeActivityEmbeddingComponentProvider$isMethodSplitInfoGetTokenValid$1
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                boolean z = false;
                Class[] clsArr = new Class[0];
                Method method = androidx.window.extensions.embedding.SplitInfo.class.getMethod("getToken", null);
                if (Modifier.isPublic(method.getModifiers()) && method.getReturnType().equals(IBinder.class)) {
                    z = true;
                }
                return Boolean.valueOf(z);
            }
        });
    }

    public final boolean hasValidVendorApiLevel5$window_release() {
        return hasValidVendorApiLevel3$window_release() && ReflectionUtils.validateReflection$window_release("ActivityStack#getActivityToken is not valid", new Function0() { // from class: androidx.window.embedding.SafeActivityEmbeddingComponentProvider$isActivityStackGetActivityStackTokenValid$1
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                boolean z = false;
                Class[] clsArr = new Class[0];
                Method method = androidx.window.extensions.embedding.ActivityStack.class.getMethod("getActivityStackToken", null);
                if (Modifier.isPublic(method.getModifiers()) && method.getReturnType().equals(ActivityStack.Token.class)) {
                    z = true;
                }
                return Boolean.valueOf(z);
            }
        }) && ReflectionUtils.validateReflection$window_release("registerActivityStackCallback is not valid", new Function0() { // from class: androidx.window.embedding.SafeActivityEmbeddingComponentProvider$isMethodRegisterActivityStackCallbackValid$1
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                Class loadClass;
                loadClass = SafeActivityEmbeddingComponentProvider.this.loader.loadClass("androidx.window.extensions.embedding.ActivityEmbeddingComponent");
                return Boolean.valueOf(Modifier.isPublic(loadClass.getMethod("registerActivityStackCallback", Executor.class, Consumer.class).getModifiers()));
            }
        }) && ReflectionUtils.validateReflection$window_release("unregisterActivityStackCallback is not valid", new Function0() { // from class: androidx.window.embedding.SafeActivityEmbeddingComponentProvider$isMethodUnregisterActivityStackCallbackValid$1
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                Class loadClass;
                loadClass = SafeActivityEmbeddingComponentProvider.this.loader.loadClass("androidx.window.extensions.embedding.ActivityEmbeddingComponent");
                return Boolean.valueOf(Modifier.isPublic(loadClass.getMethod("unregisterActivityStackCallback", Consumer.class).getModifiers()));
            }
        }) && ReflectionUtils.validateReflection$window_release("#pin(unPin)TopActivityStack is not valid", new Function0() { // from class: androidx.window.embedding.SafeActivityEmbeddingComponentProvider$isMethodPinUnpinTopActivityStackValid$1
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                Class loadClass;
                Class loadClass2;
                boolean z = false;
                Class[] clsArr = new Class[0];
                Method method = SplitPinRule.class.getMethod("isSticky", null);
                loadClass = SafeActivityEmbeddingComponentProvider.this.loader.loadClass("androidx.window.extensions.embedding.ActivityEmbeddingComponent");
                Class<?> cls = Integer.TYPE;
                Method method2 = loadClass.getMethod("pinTopActivityStack", cls, SplitPinRule.class);
                loadClass2 = SafeActivityEmbeddingComponentProvider.this.loader.loadClass("androidx.window.extensions.embedding.ActivityEmbeddingComponent");
                Method method3 = loadClass2.getMethod("unpinTopActivityStack", cls);
                if (Modifier.isPublic(method.getModifiers())) {
                    Class cls2 = Boolean.TYPE;
                    if (method.getReturnType().equals(cls2) && Modifier.isPublic(method2.getModifiers()) && method2.getReturnType().equals(cls2) && Modifier.isPublic(method3.getModifiers())) {
                        z = true;
                    }
                }
                return Boolean.valueOf(z);
            }
        }) && ReflectionUtils.validateReflection$window_release("updateSplitAttributes is not valid", new Function0() { // from class: androidx.window.embedding.SafeActivityEmbeddingComponentProvider$isMethodUpdateSplitAttributesWithTokenValid$1
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                Class loadClass;
                loadClass = SafeActivityEmbeddingComponentProvider.this.loader.loadClass("androidx.window.extensions.embedding.ActivityEmbeddingComponent");
                return Boolean.valueOf(Modifier.isPublic(loadClass.getMethod("updateSplitAttributes", SplitInfo.Token.class, androidx.window.extensions.embedding.SplitAttributes.class).getModifiers()));
            }
        }) && ReflectionUtils.validateReflection$window_release("SplitInfo#getSplitInfoToken is not valid", new Function0() { // from class: androidx.window.embedding.SafeActivityEmbeddingComponentProvider$isMethodGetSplitInfoTokenValid$1
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                boolean z = false;
                Class[] clsArr = new Class[0];
                Method method = androidx.window.extensions.embedding.SplitInfo.class.getMethod("getSplitInfoToken", null);
                if (Modifier.isPublic(method.getModifiers()) && method.getReturnType().equals(SplitInfo.Token.class)) {
                    z = true;
                }
                return Boolean.valueOf(z);
            }
        }) && ReflectionUtils.validateReflection$window_release("Class AnimationBackground is not valid", new Function0() { // from class: androidx.window.embedding.SafeActivityEmbeddingComponentProvider$isClassAnimationBackgroundValid$1
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                Class cls = Integer.TYPE;
                Method method = AnimationBackground.class.getMethod("createColorBackground", cls);
                Field declaredField = AnimationBackground.class.getDeclaredField("ANIMATION_BACKGROUND_DEFAULT");
                Class[] clsArr = new Class[0];
                Method method2 = AnimationBackground.ColorBackground.class.getMethod("getColor", null);
                Class[] clsArr2 = new Class[0];
                Method method3 = androidx.window.extensions.embedding.SplitAttributes.class.getMethod("getAnimationBackground", null);
                Method method4 = SplitAttributes.Builder.class.getMethod("setAnimationBackground", AnimationBackground.class);
                return Boolean.valueOf(Modifier.isPublic(method.getModifiers()) && method.getReturnType().equals(AnimationBackground.ColorBackground.class) && Modifier.isPublic(declaredField.getModifiers()) && Modifier.isPublic(method2.getModifiers()) && method2.getReturnType().equals(cls) && Modifier.isPublic(method3.getModifiers()) && method3.getReturnType().equals(AnimationBackground.class) && Modifier.isPublic(method4.getModifiers()) && method4.getReturnType().equals(SplitAttributes.Builder.class));
            }
        }) && ReflectionUtils.validateReflection$window_release("Class ActivityStack.Token is not valid", new Function0() { // from class: androidx.window.embedding.SafeActivityEmbeddingComponentProvider$isClassActivityStackTokenValid$1
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                boolean z = false;
                Class[] clsArr = new Class[0];
                Method method = ActivityStack.Token.class.getMethod("toBundle", null);
                Method method2 = ActivityStack.Token.class.getMethod("readFromBundle", Bundle.class);
                Method method3 = ActivityStack.Token.class.getMethod("createFromBinder", IBinder.class);
                Field declaredField = ActivityStack.Token.class.getDeclaredField("INVALID_ACTIVITY_STACK_TOKEN");
                if (Modifier.isPublic(method.getModifiers()) && method.getReturnType().equals(Bundle.class) && Modifier.isPublic(method2.getModifiers()) && method2.getReturnType().equals(ActivityStack.Token.class) && Modifier.isPublic(method3.getModifiers()) && method3.getReturnType().equals(ActivityStack.Token.class) && Modifier.isPublic(declaredField.getModifiers())) {
                    z = true;
                }
                return Boolean.valueOf(z);
            }
        }) && ReflectionUtils.validateReflection$window_release("Class WindowAttributes is not valid", new Function0() { // from class: androidx.window.embedding.SafeActivityEmbeddingComponentProvider$isClassWindowAttributesValid$1
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                boolean z = false;
                Class[] clsArr = new Class[0];
                Method method = WindowAttributes.class.getMethod("getDimAreaBehavior", null);
                Class[] clsArr2 = new Class[0];
                Method method2 = androidx.window.extensions.embedding.SplitAttributes.class.getMethod("getWindowAttributes", null);
                Method method3 = SplitAttributes.Builder.class.getMethod("setWindowAttributes", WindowAttributes.class);
                if (Modifier.isPublic(method.getModifiers()) && method.getReturnType().equals(Integer.TYPE) && Modifier.isPublic(method2.getModifiers()) && method2.getReturnType().equals(WindowAttributes.class) && Modifier.isPublic(method3.getModifiers()) && method3.getReturnType().equals(SplitAttributes.Builder.class)) {
                    z = true;
                }
                return Boolean.valueOf(z);
            }
        }) && ReflectionUtils.validateReflection$window_release("SplitInfo.Token is not valid", new Function0() { // from class: androidx.window.embedding.SafeActivityEmbeddingComponentProvider$isClassSplitInfoTokenValid$1
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                Method method = SplitInfo.Token.class.getMethod("createFromBinder", IBinder.class);
                return Boolean.valueOf(Modifier.isPublic(method.getModifiers()) && method.getReturnType().equals(SplitInfo.Token.class));
            }
        });
    }

    public final boolean hasValidVendorApiLevel6$window_release() {
        return hasValidVendorApiLevel5$window_release() && ReflectionUtils.validateReflection$window_release("ActivityEmbeddingComponent#getEmbeddedActivityWindowInfo is not valid", new Function0() { // from class: androidx.window.embedding.SafeActivityEmbeddingComponentProvider$isMethodGetEmbeddedActivityWindowInfoValid$1
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                Class loadClass;
                loadClass = SafeActivityEmbeddingComponentProvider.this.loader.loadClass("androidx.window.extensions.embedding.ActivityEmbeddingComponent");
                Method method = loadClass.getMethod("getEmbeddedActivityWindowInfo", Activity.class);
                return Boolean.valueOf(Modifier.isPublic(method.getModifiers()) && method.getReturnType().equals(EmbeddedActivityWindowInfo.class));
            }
        }) && ReflectionUtils.validateReflection$window_release("ActivityEmbeddingComponent#setEmbeddedActivityWindowInfoCallback is not valid", new Function0() { // from class: androidx.window.embedding.SafeActivityEmbeddingComponentProvider$isMethodSetEmbeddedActivityWindowInfoCallbackValid$1
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                Class loadClass;
                loadClass = SafeActivityEmbeddingComponentProvider.this.loader.loadClass("androidx.window.extensions.embedding.ActivityEmbeddingComponent");
                return Boolean.valueOf(Modifier.isPublic(loadClass.getMethod("setEmbeddedActivityWindowInfoCallback", Executor.class, Consumer.class).getModifiers()));
            }
        }) && ReflectionUtils.validateReflection$window_release("ActivityEmbeddingComponent#clearEmbeddedActivityWindowInfoCallback is not valid", new Function0() { // from class: androidx.window.embedding.SafeActivityEmbeddingComponentProvider$isMethodClearEmbeddedActivityWindowInfoCallbackValid$1
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                Class loadClass;
                loadClass = SafeActivityEmbeddingComponentProvider.this.loader.loadClass("androidx.window.extensions.embedding.ActivityEmbeddingComponent");
                Class[] clsArr = new Class[0];
                return Boolean.valueOf(Modifier.isPublic(loadClass.getMethod("clearEmbeddedActivityWindowInfoCallback", null).getModifiers()));
            }
        }) && ReflectionUtils.validateReflection$window_release("SplitAttributes#getDividerAttributes is not valid", new Function0() { // from class: androidx.window.embedding.SafeActivityEmbeddingComponentProvider$isMethodGetDividerAttributesValid$1
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                boolean z = false;
                Class[] clsArr = new Class[0];
                Method method = androidx.window.extensions.embedding.SplitAttributes.class.getMethod("getDividerAttributes", null);
                if (Modifier.isPublic(method.getModifiers()) && method.getReturnType().equals(androidx.window.extensions.embedding.DividerAttributes.class)) {
                    z = true;
                }
                return Boolean.valueOf(z);
            }
        }) && ReflectionUtils.validateReflection$window_release("SplitAttributes#setDividerAttributes is not valid", new Function0() { // from class: androidx.window.embedding.SafeActivityEmbeddingComponentProvider$isMethodSetDividerAttributesValid$1
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                Method method = SplitAttributes.Builder.class.getMethod("setDividerAttributes", androidx.window.extensions.embedding.DividerAttributes.class);
                return Boolean.valueOf(Modifier.isPublic(method.getModifiers()) && method.getReturnType().equals(SplitAttributes.Builder.class));
            }
        }) && ReflectionUtils.validateReflection$window_release("Class EmbeddedActivityWindowInfo is not valid", new Function0() { // from class: androidx.window.embedding.SafeActivityEmbeddingComponentProvider$isClassEmbeddedActivityWindowInfoValid$1
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                boolean z = false;
                Class[] clsArr = new Class[0];
                Method method = EmbeddedActivityWindowInfo.class.getMethod("getActivity", null);
                Class[] clsArr2 = new Class[0];
                Method method2 = EmbeddedActivityWindowInfo.class.getMethod("isEmbedded", null);
                Class[] clsArr3 = new Class[0];
                Method method3 = EmbeddedActivityWindowInfo.class.getMethod("getTaskBounds", null);
                Class[] clsArr4 = new Class[0];
                Method method4 = EmbeddedActivityWindowInfo.class.getMethod("getActivityStackBounds", null);
                if (Modifier.isPublic(method.getModifiers()) && method.getReturnType().equals(Activity.class) && Modifier.isPublic(method2.getModifiers())) {
                    if (method2.getReturnType().equals(Boolean.TYPE) && Modifier.isPublic(method3.getModifiers()) && method3.getReturnType().equals(Rect.class) && Modifier.isPublic(method4.getModifiers()) && method4.getReturnType().equals(Rect.class)) {
                        z = true;
                    }
                }
                return Boolean.valueOf(z);
            }
        }) && ReflectionUtils.validateReflection$window_release("Class DividerAttributes is not valid", new Function0() { // from class: androidx.window.embedding.SafeActivityEmbeddingComponentProvider$isClassDividerAttributesValid$1
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                boolean z = false;
                Class[] clsArr = new Class[0];
                Method method = androidx.window.extensions.embedding.DividerAttributes.class.getMethod("getDividerType", null);
                Class[] clsArr2 = new Class[0];
                Method method2 = androidx.window.extensions.embedding.DividerAttributes.class.getMethod("getWidthDp", null);
                Class[] clsArr3 = new Class[0];
                Method method3 = androidx.window.extensions.embedding.DividerAttributes.class.getMethod("getPrimaryMinRatio", null);
                Class[] clsArr4 = new Class[0];
                Method method4 = androidx.window.extensions.embedding.DividerAttributes.class.getMethod("getPrimaryMaxRatio", null);
                Class[] clsArr5 = new Class[0];
                Method method5 = androidx.window.extensions.embedding.DividerAttributes.class.getMethod("getDividerColor", null);
                if (Modifier.isPublic(method.getModifiers())) {
                    Class cls = Integer.TYPE;
                    if (method.getReturnType().equals(cls) && Modifier.isPublic(method2.getModifiers()) && method2.getReturnType().equals(cls) && Modifier.isPublic(method3.getModifiers())) {
                        Class cls2 = Float.TYPE;
                        if (method3.getReturnType().equals(cls2) && Modifier.isPublic(method4.getModifiers()) && method4.getReturnType().equals(cls2) && Modifier.isPublic(method5.getModifiers()) && method5.getReturnType().equals(cls)) {
                            z = true;
                        }
                    }
                }
                return Boolean.valueOf(z);
            }
        }) && ReflectionUtils.validateReflection$window_release("Class DividerAttributes.Builder is not valid", new Function0() { // from class: androidx.window.embedding.SafeActivityEmbeddingComponentProvider$isClassDividerAttributesBuilderValid$1
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                Class cls = Integer.TYPE;
                Constructor declaredConstructor = DividerAttributes.Builder.class.getDeclaredConstructor(cls);
                Constructor declaredConstructor2 = DividerAttributes.Builder.class.getDeclaredConstructor(androidx.window.extensions.embedding.DividerAttributes.class);
                Method method = DividerAttributes.Builder.class.getMethod("setWidthDp", cls);
                Class cls2 = Float.TYPE;
                Method method2 = DividerAttributes.Builder.class.getMethod("setPrimaryMinRatio", cls2);
                Method method3 = DividerAttributes.Builder.class.getMethod("setPrimaryMaxRatio", cls2);
                Method method4 = DividerAttributes.Builder.class.getMethod("setDividerColor", cls);
                return Boolean.valueOf(Modifier.isPublic(declaredConstructor.getModifiers()) && Modifier.isPublic(declaredConstructor2.getModifiers()) && Modifier.isPublic(method.getModifiers()) && method.getReturnType().equals(DividerAttributes.Builder.class) && Modifier.isPublic(method2.getModifiers()) && method2.getReturnType().equals(DividerAttributes.Builder.class) && Modifier.isPublic(method3.getModifiers()) && method3.getReturnType().equals(DividerAttributes.Builder.class) && Modifier.isPublic(method4.getModifiers()) && method4.getReturnType().equals(DividerAttributes.Builder.class));
            }
        });
    }
}
