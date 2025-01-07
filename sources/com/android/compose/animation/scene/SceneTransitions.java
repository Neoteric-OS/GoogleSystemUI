package com.android.compose.animation.scene;

import androidx.compose.animation.core.AnimationSpecKt;
import androidx.compose.animation.core.FiniteAnimationSpec;
import androidx.compose.animation.core.SpringSpec;
import androidx.compose.foundation.gestures.Orientation;
import com.android.compose.animation.scene.TransformationSpec;
import com.android.compose.animation.scene.transformation.Transformation;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.collections.EmptyList;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class SceneTransitions {
    public static final SpringSpec DefaultSwipeSpec;
    public static final SceneTransitions Empty = null;
    public final ProgressConverter$Companion$tanh$1 defaultProgressConverter;
    public final SpringSpec defaultSwipeSpec;
    public final DefaultInterruptionHandler interruptionHandler;
    public final List overscrollSpecs;
    public final List transitionSpecs;
    public final Map transitionCache = new LinkedHashMap();
    public final Map overscrollCache = new LinkedHashMap();

    static {
        SpringSpec spring$default = AnimationSpecKt.spring$default(0.0f, 400.0f, Float.valueOf(0.5f), 1);
        DefaultSwipeSpec = spring$default;
        EmptyList emptyList = EmptyList.INSTANCE;
        new SceneTransitions(spring$default, emptyList, emptyList, DefaultInterruptionHandler.INSTANCE, ProgressConverter$Companion.Default);
    }

    public SceneTransitions(SpringSpec springSpec, List list, List list2, DefaultInterruptionHandler defaultInterruptionHandler, ProgressConverter$Companion$tanh$1 progressConverter$Companion$tanh$1) {
        this.defaultSwipeSpec = springSpec;
        this.transitionSpecs = list;
        this.overscrollSpecs = list2;
        this.interruptionHandler = defaultInterruptionHandler;
        this.defaultProgressConverter = progressConverter$Companion$tanh$1;
    }

    /* JADX WARN: Type inference failed for: r5v1, types: [kotlin.jvm.functions.Function0, kotlin.jvm.internal.Lambda] */
    /* JADX WARN: Type inference failed for: r5v2, types: [kotlin.jvm.functions.Function0, kotlin.jvm.internal.Lambda] */
    /* JADX WARN: Type inference failed for: r6v1, types: [kotlin.jvm.functions.Function0, kotlin.jvm.internal.Lambda] */
    /* JADX WARN: Type inference failed for: r6v2, types: [kotlin.jvm.functions.Function0, kotlin.jvm.internal.Lambda] */
    public final TransitionSpecImpl findSpec(final ContentKey contentKey, final ContentKey contentKey2, TransitionKey transitionKey) {
        TransitionSpecImpl transition = transition(contentKey, contentKey2, transitionKey, new Function1() { // from class: com.android.compose.animation.scene.SceneTransitions$findSpec$spec$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                boolean z;
                TransitionSpecImpl transitionSpecImpl = (TransitionSpecImpl) obj;
                if (Intrinsics.areEqual(transitionSpecImpl.from, ContentKey.this)) {
                    if (Intrinsics.areEqual(transitionSpecImpl.to, contentKey2)) {
                        z = true;
                        return Boolean.valueOf(z);
                    }
                }
                z = false;
                return Boolean.valueOf(z);
            }
        });
        if (transition != null) {
            return transition;
        }
        final TransitionSpecImpl transition2 = transition(contentKey, contentKey2, transitionKey, new Function1() { // from class: com.android.compose.animation.scene.SceneTransitions$findSpec$reversed$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                boolean z;
                TransitionSpecImpl transitionSpecImpl = (TransitionSpecImpl) obj;
                if (Intrinsics.areEqual(transitionSpecImpl.from, ContentKey.this)) {
                    if (Intrinsics.areEqual(transitionSpecImpl.to, contentKey)) {
                        z = true;
                        return Boolean.valueOf(z);
                    }
                }
                z = false;
                return Boolean.valueOf(z);
            }
        });
        if (transition2 != null) {
            Function0 function0 = new Function0() { // from class: com.android.compose.animation.scene.TransitionSpecImpl$reversed$1
                {
                    super(0);
                }

                /* JADX WARN: Type inference failed for: r4v2, types: [kotlin.jvm.functions.Function0, kotlin.jvm.internal.Lambda] */
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    TransformationSpecImpl transformationSpecImpl = (TransformationSpecImpl) TransitionSpecImpl.this.transformationSpec.invoke();
                    FiniteAnimationSpec finiteAnimationSpec = transformationSpecImpl.progressSpec;
                    List list = transformationSpecImpl.transformations;
                    ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(list, 10));
                    Iterator it = list.iterator();
                    while (it.hasNext()) {
                        arrayList.add(((Transformation) it.next()).reversed());
                    }
                    return new TransformationSpecImpl(finiteAnimationSpec, transformationSpecImpl.swipeSpec, transformationSpecImpl.distance, arrayList);
                }
            };
            ?? r5 = transition2.reversePreviewTransformationSpec;
            ?? r6 = transition2.previewTransformationSpec;
            return new TransitionSpecImpl(transition2.key, transition2.to, transition2.from, r5, r6, function0);
        }
        TransitionSpecImpl transition3 = transition(contentKey, contentKey2, transitionKey, new Function1() { // from class: com.android.compose.animation.scene.SceneTransitions$findSpec$relaxedSpec$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                TransitionSpecImpl transitionSpecImpl = (TransitionSpecImpl) obj;
                ContentKey contentKey3 = ContentKey.this;
                ContentKey contentKey4 = transitionSpecImpl.from;
                boolean areEqual = Intrinsics.areEqual(contentKey4, contentKey3);
                ContentKey contentKey5 = transitionSpecImpl.to;
                return Boolean.valueOf((areEqual && contentKey5 == null) || (Intrinsics.areEqual(contentKey5, contentKey2) && contentKey4 == null));
            }
        });
        if (transition3 != null) {
            return transition3;
        }
        final TransitionSpecImpl transition4 = transition(contentKey, contentKey2, transitionKey, new Function1() { // from class: com.android.compose.animation.scene.SceneTransitions$findSpec$relaxedReversed$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                TransitionSpecImpl transitionSpecImpl = (TransitionSpecImpl) obj;
                ContentKey contentKey3 = ContentKey.this;
                ContentKey contentKey4 = transitionSpecImpl.from;
                boolean areEqual = Intrinsics.areEqual(contentKey4, contentKey3);
                ContentKey contentKey5 = transitionSpecImpl.to;
                return Boolean.valueOf((areEqual && contentKey5 == null) || (Intrinsics.areEqual(contentKey5, contentKey) && contentKey4 == null));
            }
        });
        if (transition4 == null) {
            if (transitionKey != null) {
                return findSpec(contentKey, contentKey2, null);
            }
            TransformationSpec.Companion.getClass();
            return new TransitionSpecImpl(null, contentKey, contentKey2, null, null, TransformationSpec.Companion.EmptyProvider);
        }
        Function0 function02 = new Function0() { // from class: com.android.compose.animation.scene.TransitionSpecImpl$reversed$1
            {
                super(0);
            }

            /* JADX WARN: Type inference failed for: r4v2, types: [kotlin.jvm.functions.Function0, kotlin.jvm.internal.Lambda] */
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                TransformationSpecImpl transformationSpecImpl = (TransformationSpecImpl) TransitionSpecImpl.this.transformationSpec.invoke();
                FiniteAnimationSpec finiteAnimationSpec = transformationSpecImpl.progressSpec;
                List list = transformationSpecImpl.transformations;
                ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(list, 10));
                Iterator it = list.iterator();
                while (it.hasNext()) {
                    arrayList.add(((Transformation) it.next()).reversed());
                }
                return new TransformationSpecImpl(finiteAnimationSpec, transformationSpecImpl.swipeSpec, transformationSpecImpl.distance, arrayList);
            }
        };
        ?? r52 = transition4.reversePreviewTransformationSpec;
        ?? r62 = transition4.previewTransformationSpec;
        return new TransitionSpecImpl(transition4.key, transition4.to, transition4.from, r52, r62, function02);
    }

    public final OverscrollSpecImpl overscrollSpec$frameworks__base__packages__SystemUI__compose__scene__android_common__PlatformComposeSceneTransitionLayout(final ContentKey contentKey, Orientation orientation) {
        Map map = this.overscrollCache;
        Object obj = map.get(contentKey);
        if (obj == null) {
            obj = new LinkedHashMap();
            map.put(contentKey, obj);
        }
        Map map2 = (Map) obj;
        Object obj2 = map2.get(orientation);
        if (obj2 == null) {
            Function1 function1 = new Function1() { // from class: com.android.compose.animation.scene.SceneTransitions$overscrollSpec$2$1
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj3) {
                    return Boolean.valueOf(Intrinsics.areEqual(((OverscrollSpecImpl) obj3).content, ContentKey.this));
                }
            };
            List list = this.overscrollSpecs;
            int size = list.size();
            OverscrollSpecImpl overscrollSpecImpl = null;
            for (int i = 0; i < size; i++) {
                OverscrollSpecImpl overscrollSpecImpl2 = (OverscrollSpecImpl) list.get(i);
                if (overscrollSpecImpl2.orientation == orientation && ((Boolean) function1.invoke(overscrollSpecImpl2)).booleanValue()) {
                    if (overscrollSpecImpl != null) {
                        throw new IllegalStateException(("Found multiple overscroll specs for overscroll " + contentKey).toString());
                    }
                    overscrollSpecImpl = overscrollSpecImpl2;
                }
            }
            map2.put(orientation, overscrollSpecImpl);
            obj2 = overscrollSpecImpl;
        }
        return (OverscrollSpecImpl) obj2;
    }

    public final TransitionSpecImpl transition(ContentKey contentKey, ContentKey contentKey2, TransitionKey transitionKey, Function1 function1) {
        List list = this.transitionSpecs;
        int size = list.size();
        TransitionSpecImpl transitionSpecImpl = null;
        for (int i = 0; i < size; i++) {
            TransitionSpecImpl transitionSpecImpl2 = (TransitionSpecImpl) list.get(i);
            if (Intrinsics.areEqual(transitionSpecImpl2.key, transitionKey) && ((Boolean) function1.invoke(transitionSpecImpl2)).booleanValue()) {
                if (transitionSpecImpl != null) {
                    throw new IllegalStateException(("Found multiple transition specs for transition " + contentKey + " => " + contentKey2).toString());
                }
                transitionSpecImpl = transitionSpecImpl2;
            }
        }
        return transitionSpecImpl;
    }

    public final TransitionSpecImpl transitionSpec$frameworks__base__packages__SystemUI__compose__scene__android_common__PlatformComposeSceneTransitionLayout(ContentKey contentKey, ContentKey contentKey2, TransitionKey transitionKey) {
        Map map = this.transitionCache;
        Object obj = map.get(contentKey);
        if (obj == null) {
            obj = new LinkedHashMap();
            map.put(contentKey, obj);
        }
        Map map2 = (Map) obj;
        Object obj2 = map2.get(contentKey2);
        if (obj2 == null) {
            obj2 = new LinkedHashMap();
            map2.put(contentKey2, obj2);
        }
        Map map3 = (Map) obj2;
        Object obj3 = map3.get(transitionKey);
        if (obj3 == null) {
            obj3 = findSpec(contentKey, contentKey2, transitionKey);
            map3.put(transitionKey, obj3);
        }
        return (TransitionSpecImpl) obj3;
    }
}
