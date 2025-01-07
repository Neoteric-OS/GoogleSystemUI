package com.android.systemui.statusbar.policy.ui.dialog.viewmodel;

import com.android.settingslib.notification.modes.ZenMode;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function3;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
final class ModesDialogViewModel$visibleModes$1 extends SuspendLambda implements Function3 {
    /* synthetic */ Object L$0;
    /* synthetic */ Object L$1;
    int label;

    @Override // kotlin.jvm.functions.Function3
    public final Object invoke(Object obj, Object obj2, Object obj3) {
        ModesDialogViewModel$visibleModes$1 modesDialogViewModel$visibleModes$1 = new ModesDialogViewModel$visibleModes$1(3, (Continuation) obj3);
        modesDialogViewModel$visibleModes$1.L$0 = (List) obj;
        modesDialogViewModel$visibleModes$1.L$1 = (List) obj2;
        return modesDialogViewModel$visibleModes$1.invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        List list = (List) this.L$0;
        List list2 = (List) this.L$1;
        ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(list, 10));
        Iterator it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(((ZenMode) it.next()).mId);
        }
        Set set = CollectionsKt.toSet(arrayList);
        ArrayList arrayList2 = new ArrayList();
        for (Object obj2 : list2) {
            ZenMode zenMode = (ZenMode) obj2;
            if (!set.contains(zenMode.mId)) {
                if (zenMode.mRule.isEnabled()) {
                    if (!zenMode.isActive() && !zenMode.mRule.isManualInvocationAllowed()) {
                    }
                } else if (!zenMode.mRule.isEnabled() && zenMode.mStatus == ZenMode.Status.DISABLED_BY_OTHER) {
                }
            }
            arrayList2.add(obj2);
        }
        return arrayList2;
    }
}
