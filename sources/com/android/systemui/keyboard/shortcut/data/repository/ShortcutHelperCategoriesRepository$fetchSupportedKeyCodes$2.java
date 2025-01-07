package com.android.systemui.keyboard.shortcut.data.repository;

import android.view.KeyboardShortcutGroup;
import android.view.KeyboardShortcutInfo;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.collections.CollectionsKt__MutableCollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class ShortcutHelperCategoriesRepository$fetchSupportedKeyCodes$2 extends SuspendLambda implements Function2 {
    final /* synthetic */ int $deviceId;
    final /* synthetic */ List $groupsFromAllSources;
    int label;
    final /* synthetic */ ShortcutHelperCategoriesRepository this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ShortcutHelperCategoriesRepository$fetchSupportedKeyCodes$2(List list, ShortcutHelperCategoriesRepository shortcutHelperCategoriesRepository, int i, Continuation continuation) {
        super(2, continuation);
        this.$groupsFromAllSources = list;
        this.this$0 = shortcutHelperCategoriesRepository;
        this.$deviceId = i;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new ShortcutHelperCategoriesRepository$fetchSupportedKeyCodes$2(this.$groupsFromAllSources, this.this$0, this.$deviceId, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((ShortcutHelperCategoriesRepository$fetchSupportedKeyCodes$2) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        List<List> list = this.$groupsFromAllSources;
        ArrayList arrayList = new ArrayList();
        for (List list2 : list) {
            ArrayList arrayList2 = new ArrayList();
            Iterator it = list2.iterator();
            while (it.hasNext()) {
                CollectionsKt__MutableCollectionsKt.addAll(((KeyboardShortcutGroup) it.next()).getItems(), arrayList2);
            }
            CollectionsKt__MutableCollectionsKt.addAll(arrayList2, arrayList);
        }
        ArrayList arrayList3 = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(arrayList, 10));
        Iterator it2 = arrayList.iterator();
        while (it2.hasNext()) {
            arrayList3.add(new Integer(((KeyboardShortcutInfo) it2.next()).getKeycode()));
        }
        List distinct = CollectionsKt.distinct(arrayList3);
        boolean[] deviceHasKeys = this.this$0.inputManager.deviceHasKeys(this.$deviceId, CollectionsKt.toIntArray(distinct));
        ArrayList arrayList4 = new ArrayList();
        int i = 0;
        for (Object obj2 : distinct) {
            int i2 = i + 1;
            if (i < 0) {
                CollectionsKt__CollectionsKt.throwIndexOverflow();
                throw null;
            }
            ((Number) obj2).intValue();
            if (deviceHasKeys[i]) {
                arrayList4.add(obj2);
            }
            i = i2;
        }
        return CollectionsKt.toSet(arrayList4);
    }
}
