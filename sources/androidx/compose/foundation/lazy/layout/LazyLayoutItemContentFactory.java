package androidx.compose.foundation.lazy.layout;

import androidx.collection.MutableScatterMap;
import androidx.collection.ScatterMapKt;
import androidx.compose.foundation.lazy.layout.LazyLayoutItemContentFactory;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerImpl;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.DisposableEffectResult;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.OpaqueKey;
import androidx.compose.runtime.internal.ComposableLambdaImpl;
import androidx.compose.runtime.saveable.SaveableStateHolder;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class LazyLayoutItemContentFactory {
    public final Function0 itemProvider;
    public final MutableScatterMap lambdasCache;
    public final SaveableStateHolder saveableStateHolder;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    final class CachedItemContent {
        public ComposableLambdaImpl _content;
        public final Object contentType;
        public int index;
        public final Object key;

        public CachedItemContent(int i, Object obj, Object obj2) {
            this.key = obj;
            this.contentType = obj2;
            this.index = i;
        }
    }

    public LazyLayoutItemContentFactory(SaveableStateHolder saveableStateHolder, Function0 function0) {
        this.saveableStateHolder = saveableStateHolder;
        this.itemProvider = function0;
        long[] jArr = ScatterMapKt.EmptyGroup;
        this.lambdasCache = new MutableScatterMap();
    }

    public final Function2 getContent(int i, Object obj, Object obj2) {
        ComposableLambdaImpl composableLambdaImpl;
        MutableScatterMap mutableScatterMap = this.lambdasCache;
        final CachedItemContent cachedItemContent = (CachedItemContent) mutableScatterMap.get(obj);
        if (cachedItemContent != null && cachedItemContent.index == i && Intrinsics.areEqual(cachedItemContent.contentType, obj2)) {
            ComposableLambdaImpl composableLambdaImpl2 = cachedItemContent._content;
            if (composableLambdaImpl2 != null) {
                return composableLambdaImpl2;
            }
            final LazyLayoutItemContentFactory lazyLayoutItemContentFactory = LazyLayoutItemContentFactory.this;
            composableLambdaImpl = new ComposableLambdaImpl(1403994769, true, new Function2() { // from class: androidx.compose.foundation.lazy.layout.LazyLayoutItemContentFactory$CachedItemContent$createContentLambda$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj3, Object obj4) {
                    Composer composer = (Composer) obj3;
                    if ((((Number) obj4).intValue() & 3) == 2) {
                        ComposerImpl composerImpl = (ComposerImpl) composer;
                        if (composerImpl.getSkipping()) {
                            composerImpl.skipToGroupEnd();
                            return Unit.INSTANCE;
                        }
                    }
                    OpaqueKey opaqueKey = ComposerKt.invocation;
                    LazyLayoutItemProvider lazyLayoutItemProvider = (LazyLayoutItemProvider) ((LazyLayoutKt$LazyLayout$1$itemContentFactory$1$1) LazyLayoutItemContentFactory.this.itemProvider).invoke();
                    int i2 = cachedItemContent.index;
                    if ((i2 >= lazyLayoutItemProvider.getItemCount() || !lazyLayoutItemProvider.getKey(i2).equals(cachedItemContent.key)) && (i2 = lazyLayoutItemProvider.getIndex(cachedItemContent.key)) != -1) {
                        cachedItemContent.index = i2;
                    }
                    boolean z = i2 != -1;
                    LazyLayoutItemContentFactory lazyLayoutItemContentFactory2 = LazyLayoutItemContentFactory.this;
                    LazyLayoutItemContentFactory.CachedItemContent cachedItemContent2 = cachedItemContent;
                    ComposerImpl composerImpl2 = (ComposerImpl) composer;
                    composerImpl2.startReusableGroup(Boolean.valueOf(z));
                    boolean changed = composerImpl2.changed(z);
                    composerImpl2.startReplaceGroup(-869709043);
                    if (z) {
                        composerImpl2.startReplaceGroup(-2120060505);
                        LazyLayoutItemContentFactoryKt.m135access$SkippableItemJVlU9Rs(lazyLayoutItemProvider, lazyLayoutItemContentFactory2.saveableStateHolder, i2, cachedItemContent2.key, composerImpl2, 0);
                        composerImpl2.end(false);
                    } else {
                        composerImpl2.deactivateToEndGroup(changed);
                    }
                    composerImpl2.end(false);
                    composerImpl2.endReusableGroup();
                    LazyLayoutItemContentFactory.CachedItemContent cachedItemContent3 = cachedItemContent;
                    Object obj5 = cachedItemContent3.key;
                    boolean changedInstance = composerImpl2.changedInstance(cachedItemContent3);
                    final LazyLayoutItemContentFactory.CachedItemContent cachedItemContent4 = cachedItemContent;
                    Object rememberedValue = composerImpl2.rememberedValue();
                    if (changedInstance || rememberedValue == Composer.Companion.Empty) {
                        rememberedValue = new Function1() { // from class: androidx.compose.foundation.lazy.layout.LazyLayoutItemContentFactory$CachedItemContent$createContentLambda$1$2$1
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public final Object invoke(Object obj6) {
                                final LazyLayoutItemContentFactory.CachedItemContent cachedItemContent5 = LazyLayoutItemContentFactory.CachedItemContent.this;
                                return new DisposableEffectResult() { // from class: androidx.compose.foundation.lazy.layout.LazyLayoutItemContentFactory$CachedItemContent$createContentLambda$1$2$1$invoke$$inlined$onDispose$1
                                    @Override // androidx.compose.runtime.DisposableEffectResult
                                    public final void dispose() {
                                        LazyLayoutItemContentFactory.CachedItemContent.this._content = null;
                                    }
                                };
                            }
                        };
                        composerImpl2.updateRememberedValue(rememberedValue);
                    }
                    EffectsKt.DisposableEffect(obj5, (Function1) rememberedValue, composerImpl2);
                    return Unit.INSTANCE;
                }
            });
            cachedItemContent._content = composableLambdaImpl;
        } else {
            final CachedItemContent cachedItemContent2 = new CachedItemContent(i, obj, obj2);
            mutableScatterMap.set(obj, cachedItemContent2);
            composableLambdaImpl = cachedItemContent2._content;
            if (composableLambdaImpl == null) {
                ComposableLambdaImpl composableLambdaImpl3 = new ComposableLambdaImpl(1403994769, true, new Function2() { // from class: androidx.compose.foundation.lazy.layout.LazyLayoutItemContentFactory$CachedItemContent$createContentLambda$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(Object obj3, Object obj4) {
                        Composer composer = (Composer) obj3;
                        if ((((Number) obj4).intValue() & 3) == 2) {
                            ComposerImpl composerImpl = (ComposerImpl) composer;
                            if (composerImpl.getSkipping()) {
                                composerImpl.skipToGroupEnd();
                                return Unit.INSTANCE;
                            }
                        }
                        OpaqueKey opaqueKey = ComposerKt.invocation;
                        LazyLayoutItemProvider lazyLayoutItemProvider = (LazyLayoutItemProvider) ((LazyLayoutKt$LazyLayout$1$itemContentFactory$1$1) LazyLayoutItemContentFactory.this.itemProvider).invoke();
                        int i2 = cachedItemContent2.index;
                        if ((i2 >= lazyLayoutItemProvider.getItemCount() || !lazyLayoutItemProvider.getKey(i2).equals(cachedItemContent2.key)) && (i2 = lazyLayoutItemProvider.getIndex(cachedItemContent2.key)) != -1) {
                            cachedItemContent2.index = i2;
                        }
                        boolean z = i2 != -1;
                        LazyLayoutItemContentFactory lazyLayoutItemContentFactory2 = LazyLayoutItemContentFactory.this;
                        LazyLayoutItemContentFactory.CachedItemContent cachedItemContent22 = cachedItemContent2;
                        ComposerImpl composerImpl2 = (ComposerImpl) composer;
                        composerImpl2.startReusableGroup(Boolean.valueOf(z));
                        boolean changed = composerImpl2.changed(z);
                        composerImpl2.startReplaceGroup(-869709043);
                        if (z) {
                            composerImpl2.startReplaceGroup(-2120060505);
                            LazyLayoutItemContentFactoryKt.m135access$SkippableItemJVlU9Rs(lazyLayoutItemProvider, lazyLayoutItemContentFactory2.saveableStateHolder, i2, cachedItemContent22.key, composerImpl2, 0);
                            composerImpl2.end(false);
                        } else {
                            composerImpl2.deactivateToEndGroup(changed);
                        }
                        composerImpl2.end(false);
                        composerImpl2.endReusableGroup();
                        LazyLayoutItemContentFactory.CachedItemContent cachedItemContent3 = cachedItemContent2;
                        Object obj5 = cachedItemContent3.key;
                        boolean changedInstance = composerImpl2.changedInstance(cachedItemContent3);
                        final LazyLayoutItemContentFactory.CachedItemContent cachedItemContent4 = cachedItemContent2;
                        Object rememberedValue = composerImpl2.rememberedValue();
                        if (changedInstance || rememberedValue == Composer.Companion.Empty) {
                            rememberedValue = new Function1() { // from class: androidx.compose.foundation.lazy.layout.LazyLayoutItemContentFactory$CachedItemContent$createContentLambda$1$2$1
                                {
                                    super(1);
                                }

                                @Override // kotlin.jvm.functions.Function1
                                public final Object invoke(Object obj6) {
                                    final LazyLayoutItemContentFactory.CachedItemContent cachedItemContent5 = LazyLayoutItemContentFactory.CachedItemContent.this;
                                    return new DisposableEffectResult() { // from class: androidx.compose.foundation.lazy.layout.LazyLayoutItemContentFactory$CachedItemContent$createContentLambda$1$2$1$invoke$$inlined$onDispose$1
                                        @Override // androidx.compose.runtime.DisposableEffectResult
                                        public final void dispose() {
                                            LazyLayoutItemContentFactory.CachedItemContent.this._content = null;
                                        }
                                    };
                                }
                            };
                            composerImpl2.updateRememberedValue(rememberedValue);
                        }
                        EffectsKt.DisposableEffect(obj5, (Function1) rememberedValue, composerImpl2);
                        return Unit.INSTANCE;
                    }
                });
                cachedItemContent2._content = composableLambdaImpl3;
                return composableLambdaImpl3;
            }
        }
        return composableLambdaImpl;
    }

    public final Object getContentType(Object obj) {
        if (obj == null) {
            return null;
        }
        CachedItemContent cachedItemContent = (CachedItemContent) this.lambdasCache.get(obj);
        if (cachedItemContent != null) {
            return cachedItemContent.contentType;
        }
        LazyLayoutItemProvider lazyLayoutItemProvider = (LazyLayoutItemProvider) ((LazyLayoutKt$LazyLayout$1$itemContentFactory$1$1) this.itemProvider).invoke();
        int index = lazyLayoutItemProvider.getIndex(obj);
        if (index != -1) {
            return lazyLayoutItemProvider.getContentType(index);
        }
        return null;
    }
}
