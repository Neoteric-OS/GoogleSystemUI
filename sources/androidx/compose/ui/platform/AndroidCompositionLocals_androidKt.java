package androidx.compose.ui.platform;

import android.content.ComponentCallbacks2;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import androidx.appsearch.app.AppSearchSchema$Builder$$ExternalSyntheticOutline0;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.Composer$Companion$Empty$1;
import androidx.compose.runtime.ComposerImpl;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalKt;
import androidx.compose.runtime.DisposableEffectResult;
import androidx.compose.runtime.DynamicProvidableCompositionLocal;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.OpaqueKey;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.ProvidedValue;
import androidx.compose.runtime.RecomposeScopeImpl;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.SnapshotMutableStateImpl;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.StaticProvidableCompositionLocal;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.runtime.saveable.SaveableStateRegistry;
import androidx.compose.runtime.saveable.SaveableStateRegistryKt;
import androidx.compose.ui.platform.AndroidComposeView;
import androidx.compose.ui.res.ImageVectorCache;
import androidx.compose.ui.res.ResourceIdCache;
import androidx.compose.ui.scrollcapture.ScrollCapture;
import androidx.lifecycle.compose.LocalLifecycleOwnerKt;
import androidx.savedstate.SavedStateRegistry;
import androidx.savedstate.SavedStateRegistryOwner;
import com.android.wm.shell.R;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class AndroidCompositionLocals_androidKt {
    public static final DynamicProvidableCompositionLocal LocalConfiguration = new DynamicProvidableCompositionLocal(new Function0() { // from class: androidx.compose.ui.platform.AndroidCompositionLocals_androidKt$LocalConfiguration$1
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            AndroidCompositionLocals_androidKt.access$noLocalProvidedFor("LocalConfiguration");
            throw null;
        }
    });
    public static final StaticProvidableCompositionLocal LocalContext = new StaticProvidableCompositionLocal(new Function0() { // from class: androidx.compose.ui.platform.AndroidCompositionLocals_androidKt$LocalContext$1
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            AndroidCompositionLocals_androidKt.access$noLocalProvidedFor("LocalContext");
            throw null;
        }
    });
    public static final StaticProvidableCompositionLocal LocalImageVectorCache = new StaticProvidableCompositionLocal(new Function0() { // from class: androidx.compose.ui.platform.AndroidCompositionLocals_androidKt$LocalImageVectorCache$1
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            AndroidCompositionLocals_androidKt.access$noLocalProvidedFor("LocalImageVectorCache");
            throw null;
        }
    });
    public static final StaticProvidableCompositionLocal LocalResourceIdCache = new StaticProvidableCompositionLocal(new Function0() { // from class: androidx.compose.ui.platform.AndroidCompositionLocals_androidKt$LocalResourceIdCache$1
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            AndroidCompositionLocals_androidKt.access$noLocalProvidedFor("LocalResourceIdCache");
            throw null;
        }
    });
    public static final StaticProvidableCompositionLocal LocalSavedStateRegistryOwner = new StaticProvidableCompositionLocal(new Function0() { // from class: androidx.compose.ui.platform.AndroidCompositionLocals_androidKt$LocalSavedStateRegistryOwner$1
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            AndroidCompositionLocals_androidKt.access$noLocalProvidedFor("LocalSavedStateRegistryOwner");
            throw null;
        }
    });
    public static final StaticProvidableCompositionLocal LocalView = new StaticProvidableCompositionLocal(new Function0() { // from class: androidx.compose.ui.platform.AndroidCompositionLocals_androidKt$LocalView$1
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            AndroidCompositionLocals_androidKt.access$noLocalProvidedFor("LocalView");
            throw null;
        }
    });

    /* JADX WARN: Type inference failed for: r5v9, types: [androidx.compose.ui.platform.AndroidCompositionLocals_androidKt$ProvideAndroidCompositionLocals$3, kotlin.jvm.internal.Lambda] */
    public static final void ProvideAndroidCompositionLocals(final AndroidComposeView androidComposeView, final Function2 function2, Composer composer, final int i) {
        boolean z;
        ComposerImpl composerImpl = (ComposerImpl) composer;
        composerImpl.startRestartGroup(1396852028);
        int i2 = (i & 6) == 0 ? (composerImpl.changedInstance(androidComposeView) ? 4 : 2) | i : i;
        if ((i & 48) == 0) {
            i2 |= composerImpl.changedInstance(function2) ? 32 : 16;
        }
        if ((i2 & 19) == 18 && composerImpl.getSkipping()) {
            composerImpl.skipToGroupEnd();
        } else {
            OpaqueKey opaqueKey = ComposerKt.invocation;
            final Context context = androidComposeView.getContext();
            Object rememberedValue = composerImpl.rememberedValue();
            Composer$Companion$Empty$1 composer$Companion$Empty$1 = Composer.Companion.Empty;
            if (rememberedValue == composer$Companion$Empty$1) {
                rememberedValue = SnapshotStateKt.mutableStateOf$default(new Configuration(context.getResources().getConfiguration()));
                composerImpl.updateRememberedValue(rememberedValue);
            }
            final MutableState mutableState = (MutableState) rememberedValue;
            Object rememberedValue2 = composerImpl.rememberedValue();
            if (rememberedValue2 == composer$Companion$Empty$1) {
                rememberedValue2 = new Function1() { // from class: androidx.compose.ui.platform.AndroidCompositionLocals_androidKt$ProvideAndroidCompositionLocals$1$1
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        MutableState mutableState2 = MutableState.this;
                        Configuration configuration = new Configuration((Configuration) obj);
                        DynamicProvidableCompositionLocal dynamicProvidableCompositionLocal = AndroidCompositionLocals_androidKt.LocalConfiguration;
                        mutableState2.setValue(configuration);
                        return Unit.INSTANCE;
                    }
                };
                composerImpl.updateRememberedValue(rememberedValue2);
            }
            androidComposeView.configurationChangeObserver = (Function1) rememberedValue2;
            Object rememberedValue3 = composerImpl.rememberedValue();
            if (rememberedValue3 == composer$Companion$Empty$1) {
                rememberedValue3 = new AndroidUriHandler(context);
                composerImpl.updateRememberedValue(rememberedValue3);
            }
            final AndroidUriHandler androidUriHandler = (AndroidUriHandler) rememberedValue3;
            AndroidComposeView.ViewTreeOwners viewTreeOwners = androidComposeView.getViewTreeOwners();
            if (viewTreeOwners == null) {
                throw new IllegalStateException("Called when the ViewTreeOwnersAvailability is not yet in Available state");
            }
            Object rememberedValue4 = composerImpl.rememberedValue();
            SavedStateRegistryOwner savedStateRegistryOwner = viewTreeOwners.savedStateRegistryOwner;
            if (rememberedValue4 == composer$Companion$Empty$1) {
                Class[] clsArr = DisposableSaveableStateRegistry_androidKt.AcceptableClasses;
                View view = (View) androidComposeView.getParent();
                Object tag = view.getTag(R.id.compose_view_saveable_id_tag);
                LinkedHashMap linkedHashMap = null;
                String str = tag instanceof String ? (String) tag : null;
                if (str == null) {
                    str = String.valueOf(view.getId());
                }
                String m = AppSearchSchema$Builder$$ExternalSyntheticOutline0.m("SaveableStateRegistry:", str);
                SavedStateRegistry savedStateRegistry = savedStateRegistryOwner.getSavedStateRegistry();
                Bundle consumeRestoredStateForKey = savedStateRegistry.consumeRestoredStateForKey(m);
                if (consumeRestoredStateForKey != null) {
                    linkedHashMap = new LinkedHashMap();
                    for (Iterator it = consumeRestoredStateForKey.keySet().iterator(); it.hasNext(); it = it) {
                        String str2 = (String) it.next();
                        linkedHashMap.put(str2, consumeRestoredStateForKey.getParcelableArrayList(str2));
                    }
                }
                final SaveableStateRegistry SaveableStateRegistry = SaveableStateRegistryKt.SaveableStateRegistry(linkedHashMap, new Function1() { // from class: androidx.compose.ui.platform.DisposableSaveableStateRegistry_androidKt$DisposableSaveableStateRegistry$saveableStateRegistry$1
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return Boolean.valueOf(DisposableSaveableStateRegistry_androidKt.canBeSavedToBundle(obj));
                    }
                });
                try {
                    savedStateRegistry.registerSavedStateProvider(m, new SavedStateRegistry.SavedStateProvider() { // from class: androidx.compose.ui.platform.DisposableSaveableStateRegistry_androidKt$$ExternalSyntheticLambda0
                        @Override // androidx.savedstate.SavedStateRegistry.SavedStateProvider
                        public final Bundle saveState() {
                            Class[] clsArr2 = DisposableSaveableStateRegistry_androidKt.AcceptableClasses;
                            Map performSave = SaveableStateRegistry.this.performSave();
                            Bundle bundle = new Bundle();
                            for (Map.Entry entry : performSave.entrySet()) {
                                String str3 = (String) entry.getKey();
                                List list = (List) entry.getValue();
                                bundle.putParcelableArrayList(str3, list instanceof ArrayList ? (ArrayList) list : new ArrayList<>(list));
                            }
                            return bundle;
                        }
                    });
                    z = true;
                } catch (IllegalArgumentException unused) {
                    z = false;
                }
                DisposableSaveableStateRegistry disposableSaveableStateRegistry = new DisposableSaveableStateRegistry(SaveableStateRegistry, new DisposableSaveableStateRegistry_androidKt$DisposableSaveableStateRegistry$1(z, savedStateRegistry, m));
                composerImpl.updateRememberedValue(disposableSaveableStateRegistry);
                rememberedValue4 = disposableSaveableStateRegistry;
            }
            final DisposableSaveableStateRegistry disposableSaveableStateRegistry2 = (DisposableSaveableStateRegistry) rememberedValue4;
            Unit unit = Unit.INSTANCE;
            boolean changedInstance = composerImpl.changedInstance(disposableSaveableStateRegistry2);
            Object rememberedValue5 = composerImpl.rememberedValue();
            if (changedInstance || rememberedValue5 == composer$Companion$Empty$1) {
                rememberedValue5 = new Function1() { // from class: androidx.compose.ui.platform.AndroidCompositionLocals_androidKt$ProvideAndroidCompositionLocals$2$1
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        final DisposableSaveableStateRegistry disposableSaveableStateRegistry3 = DisposableSaveableStateRegistry.this;
                        return new DisposableEffectResult() { // from class: androidx.compose.ui.platform.AndroidCompositionLocals_androidKt$ProvideAndroidCompositionLocals$2$1$invoke$$inlined$onDispose$1
                            @Override // androidx.compose.runtime.DisposableEffectResult
                            public final void dispose() {
                                ((DisposableSaveableStateRegistry_androidKt$DisposableSaveableStateRegistry$1) DisposableSaveableStateRegistry.this.onDispose).invoke();
                            }
                        };
                    }
                };
                composerImpl.updateRememberedValue(rememberedValue5);
            }
            EffectsKt.DisposableEffect(unit, (Function1) rememberedValue5, composerImpl);
            Configuration configuration = (Configuration) mutableState.getValue();
            OpaqueKey opaqueKey2 = ComposerKt.invocation;
            Object rememberedValue6 = composerImpl.rememberedValue();
            if (rememberedValue6 == composer$Companion$Empty$1) {
                rememberedValue6 = new ImageVectorCache();
                composerImpl.updateRememberedValue(rememberedValue6);
            }
            final ImageVectorCache imageVectorCache = (ImageVectorCache) rememberedValue6;
            Object rememberedValue7 = composerImpl.rememberedValue();
            Object obj = rememberedValue7;
            if (rememberedValue7 == composer$Companion$Empty$1) {
                Configuration configuration2 = new Configuration();
                if (configuration != null) {
                    configuration2.setTo(configuration);
                }
                composerImpl.updateRememberedValue(configuration2);
                obj = configuration2;
            }
            final Configuration configuration3 = (Configuration) obj;
            Object rememberedValue8 = composerImpl.rememberedValue();
            if (rememberedValue8 == composer$Companion$Empty$1) {
                rememberedValue8 = new ComponentCallbacks2() { // from class: androidx.compose.ui.platform.AndroidCompositionLocals_androidKt$obtainImageVectorCache$callbacks$1$1
                    @Override // android.content.ComponentCallbacks
                    public final void onConfigurationChanged(Configuration configuration4) {
                        int updateFrom = configuration3.updateFrom(configuration4);
                        Iterator it2 = imageVectorCache.map.entrySet().iterator();
                        while (it2.hasNext()) {
                            ImageVectorCache.ImageVectorEntry imageVectorEntry = (ImageVectorCache.ImageVectorEntry) ((WeakReference) ((Map.Entry) it2.next()).getValue()).get();
                            if (imageVectorEntry == null || Configuration.needNewResources(updateFrom, imageVectorEntry.configFlags)) {
                                it2.remove();
                            }
                        }
                        configuration3.setTo(configuration4);
                    }

                    @Override // android.content.ComponentCallbacks
                    public final void onLowMemory() {
                        imageVectorCache.map.clear();
                    }

                    @Override // android.content.ComponentCallbacks2
                    public final void onTrimMemory(int i3) {
                        imageVectorCache.map.clear();
                    }
                };
                composerImpl.updateRememberedValue(rememberedValue8);
            }
            final AndroidCompositionLocals_androidKt$obtainImageVectorCache$callbacks$1$1 androidCompositionLocals_androidKt$obtainImageVectorCache$callbacks$1$1 = (AndroidCompositionLocals_androidKt$obtainImageVectorCache$callbacks$1$1) rememberedValue8;
            boolean changedInstance2 = composerImpl.changedInstance(context);
            Object rememberedValue9 = composerImpl.rememberedValue();
            if (changedInstance2 || rememberedValue9 == composer$Companion$Empty$1) {
                rememberedValue9 = new Function1() { // from class: androidx.compose.ui.platform.AndroidCompositionLocals_androidKt$obtainImageVectorCache$1$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj2) {
                        context.getApplicationContext().registerComponentCallbacks(androidCompositionLocals_androidKt$obtainImageVectorCache$callbacks$1$1);
                        final Context context2 = context;
                        final AndroidCompositionLocals_androidKt$obtainImageVectorCache$callbacks$1$1 androidCompositionLocals_androidKt$obtainImageVectorCache$callbacks$1$12 = androidCompositionLocals_androidKt$obtainImageVectorCache$callbacks$1$1;
                        return new DisposableEffectResult() { // from class: androidx.compose.ui.platform.AndroidCompositionLocals_androidKt$obtainImageVectorCache$1$1$invoke$$inlined$onDispose$1
                            @Override // androidx.compose.runtime.DisposableEffectResult
                            public final void dispose() {
                                context2.getApplicationContext().unregisterComponentCallbacks(androidCompositionLocals_androidKt$obtainImageVectorCache$callbacks$1$12);
                            }
                        };
                    }
                };
                composerImpl.updateRememberedValue(rememberedValue9);
            }
            EffectsKt.DisposableEffect(imageVectorCache, (Function1) rememberedValue9, composerImpl);
            Object rememberedValue10 = composerImpl.rememberedValue();
            if (rememberedValue10 == composer$Companion$Empty$1) {
                rememberedValue10 = new ResourceIdCache();
                composerImpl.updateRememberedValue(rememberedValue10);
            }
            final ResourceIdCache resourceIdCache = (ResourceIdCache) rememberedValue10;
            Object rememberedValue11 = composerImpl.rememberedValue();
            if (rememberedValue11 == composer$Companion$Empty$1) {
                rememberedValue11 = new ComponentCallbacks2() { // from class: androidx.compose.ui.platform.AndroidCompositionLocals_androidKt$obtainResourceIdCache$callbacks$1$1
                    @Override // android.content.ComponentCallbacks
                    public final void onConfigurationChanged(Configuration configuration4) {
                        ResourceIdCache resourceIdCache2 = ResourceIdCache.this;
                        synchronized (resourceIdCache2) {
                            resourceIdCache2.resIdPathMap.clear();
                        }
                    }

                    @Override // android.content.ComponentCallbacks
                    public final void onLowMemory() {
                        ResourceIdCache resourceIdCache2 = ResourceIdCache.this;
                        synchronized (resourceIdCache2) {
                            resourceIdCache2.resIdPathMap.clear();
                        }
                    }

                    @Override // android.content.ComponentCallbacks2
                    public final void onTrimMemory(int i3) {
                        ResourceIdCache resourceIdCache2 = ResourceIdCache.this;
                        synchronized (resourceIdCache2) {
                            resourceIdCache2.resIdPathMap.clear();
                        }
                    }
                };
                composerImpl.updateRememberedValue(rememberedValue11);
            }
            final AndroidCompositionLocals_androidKt$obtainResourceIdCache$callbacks$1$1 androidCompositionLocals_androidKt$obtainResourceIdCache$callbacks$1$1 = (AndroidCompositionLocals_androidKt$obtainResourceIdCache$callbacks$1$1) rememberedValue11;
            boolean changedInstance3 = composerImpl.changedInstance(context);
            Object rememberedValue12 = composerImpl.rememberedValue();
            if (changedInstance3 || rememberedValue12 == composer$Companion$Empty$1) {
                rememberedValue12 = new Function1() { // from class: androidx.compose.ui.platform.AndroidCompositionLocals_androidKt$obtainResourceIdCache$1$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj2) {
                        context.getApplicationContext().registerComponentCallbacks(androidCompositionLocals_androidKt$obtainResourceIdCache$callbacks$1$1);
                        final Context context2 = context;
                        final AndroidCompositionLocals_androidKt$obtainResourceIdCache$callbacks$1$1 androidCompositionLocals_androidKt$obtainResourceIdCache$callbacks$1$12 = androidCompositionLocals_androidKt$obtainResourceIdCache$callbacks$1$1;
                        return new DisposableEffectResult() { // from class: androidx.compose.ui.platform.AndroidCompositionLocals_androidKt$obtainResourceIdCache$1$1$invoke$$inlined$onDispose$1
                            @Override // androidx.compose.runtime.DisposableEffectResult
                            public final void dispose() {
                                context2.getApplicationContext().unregisterComponentCallbacks(androidCompositionLocals_androidKt$obtainResourceIdCache$callbacks$1$12);
                            }
                        };
                    }
                };
                composerImpl.updateRememberedValue(rememberedValue12);
            }
            EffectsKt.DisposableEffect(resourceIdCache, (Function1) rememberedValue12, composerImpl);
            DynamicProvidableCompositionLocal dynamicProvidableCompositionLocal = CompositionLocalsKt.LocalProvidableScrollCaptureInProgress;
            boolean booleanValue = ((Boolean) composerImpl.consume(dynamicProvidableCompositionLocal)).booleanValue();
            ScrollCapture scrollCapture = androidComposeView.scrollCapture;
            CompositionLocalKt.CompositionLocalProvider(new ProvidedValue[]{LocalConfiguration.defaultProvidedValue$runtime_release((Configuration) mutableState.getValue()), LocalContext.defaultProvidedValue$runtime_release(context), LocalLifecycleOwnerKt.LocalLifecycleOwner.defaultProvidedValue$runtime_release(viewTreeOwners.lifecycleOwner), LocalSavedStateRegistryOwner.defaultProvidedValue$runtime_release(savedStateRegistryOwner), SaveableStateRegistryKt.LocalSaveableStateRegistry.defaultProvidedValue$runtime_release(disposableSaveableStateRegistry2), LocalView.defaultProvidedValue$runtime_release(androidComposeView), LocalImageVectorCache.defaultProvidedValue$runtime_release(imageVectorCache), LocalResourceIdCache.defaultProvidedValue$runtime_release(resourceIdCache), dynamicProvidableCompositionLocal.defaultProvidedValue$runtime_release(Boolean.valueOf(booleanValue | (scrollCapture != null ? ((Boolean) ((SnapshotMutableStateImpl) scrollCapture.scrollCaptureInProgress$delegate).getValue()).booleanValue() : false)))}, ComposableLambdaKt.rememberComposableLambda(1471621628, new Function2() { // from class: androidx.compose.ui.platform.AndroidCompositionLocals_androidKt$ProvideAndroidCompositionLocals$3
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj2, Object obj3) {
                    Composer composer2 = (Composer) obj2;
                    if ((((Number) obj3).intValue() & 3) == 2) {
                        ComposerImpl composerImpl2 = (ComposerImpl) composer2;
                        if (composerImpl2.getSkipping()) {
                            composerImpl2.skipToGroupEnd();
                            return Unit.INSTANCE;
                        }
                    }
                    OpaqueKey opaqueKey3 = ComposerKt.invocation;
                    CompositionLocalsKt.ProvideCommonCompositionLocals(AndroidComposeView.this, androidUriHandler, function2, composer2, 0);
                    return Unit.INSTANCE;
                }
            }, composerImpl), composerImpl, 56);
        }
        RecomposeScopeImpl endRestartGroup = composerImpl.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.block = new Function2() { // from class: androidx.compose.ui.platform.AndroidCompositionLocals_androidKt$ProvideAndroidCompositionLocals$4
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj2, Object obj3) {
                    ((Number) obj3).intValue();
                    AndroidCompositionLocals_androidKt.ProvideAndroidCompositionLocals(AndroidComposeView.this, function2, (Composer) obj2, RecomposeScopeImplKt.updateChangedFlags(i | 1));
                    return Unit.INSTANCE;
                }
            };
        }
    }

    public static final void access$noLocalProvidedFor(String str) {
        throw new IllegalStateException(("CompositionLocal " + str + " not present").toString());
    }

    public static final ProvidableCompositionLocal getLocalLifecycleOwner() {
        return LocalLifecycleOwnerKt.LocalLifecycleOwner;
    }
}
