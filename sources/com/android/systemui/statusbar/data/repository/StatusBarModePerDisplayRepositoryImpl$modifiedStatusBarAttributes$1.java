package com.android.systemui.statusbar.data.repository;

import android.graphics.Rect;
import com.android.internal.statusbar.LetterboxDetails;
import com.android.internal.util.ContrastColorUtil;
import com.android.internal.view.AppearanceRegion;
import com.android.systemui.statusbar.data.repository.StatusBarModePerDisplayRepositoryImpl;
import com.android.systemui.statusbar.phone.BoundsPair;
import com.android.systemui.statusbar.phone.LetterboxAppearance;
import com.android.systemui.statusbar.phone.LetterboxAppearanceCalculator;
import com.android.systemui.statusbar.phone.LetterboxBackgroundProvider;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
final class StatusBarModePerDisplayRepositoryImpl$modifiedStatusBarAttributes$1 extends SuspendLambda implements Function3 {
    /* synthetic */ Object L$0;
    /* synthetic */ Object L$1;
    int label;
    final /* synthetic */ StatusBarModePerDisplayRepositoryImpl this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public StatusBarModePerDisplayRepositoryImpl$modifiedStatusBarAttributes$1(StatusBarModePerDisplayRepositoryImpl statusBarModePerDisplayRepositoryImpl, Continuation continuation) {
        super(3, continuation);
        this.this$0 = statusBarModePerDisplayRepositoryImpl;
    }

    @Override // kotlin.jvm.functions.Function3
    public final Object invoke(Object obj, Object obj2, Object obj3) {
        StatusBarModePerDisplayRepositoryImpl$modifiedStatusBarAttributes$1 statusBarModePerDisplayRepositoryImpl$modifiedStatusBarAttributes$1 = new StatusBarModePerDisplayRepositoryImpl$modifiedStatusBarAttributes$1(this.this$0, (Continuation) obj3);
        statusBarModePerDisplayRepositoryImpl$modifiedStatusBarAttributes$1.L$0 = (StatusBarModePerDisplayRepositoryImpl.StatusBarAttributes) obj;
        statusBarModePerDisplayRepositoryImpl$modifiedStatusBarAttributes$1.L$1 = (BoundsPair) obj2;
        return statusBarModePerDisplayRepositoryImpl$modifiedStatusBarAttributes$1.invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Pair pair;
        LetterboxAppearance letterboxAppearance;
        Object obj2;
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        StatusBarModePerDisplayRepositoryImpl.StatusBarAttributes statusBarAttributes = (StatusBarModePerDisplayRepositoryImpl.StatusBarAttributes) this.L$0;
        BoundsPair boundsPair = (BoundsPair) this.L$1;
        if (statusBarAttributes == null) {
            return null;
        }
        StatusBarModePerDisplayRepositoryImpl statusBarModePerDisplayRepositoryImpl = this.this$0;
        List<AppearanceRegion> list = statusBarAttributes.appearanceRegions;
        List<LetterboxDetails> list2 = statusBarAttributes.letterboxDetails;
        statusBarModePerDisplayRepositoryImpl.getClass();
        boolean isEmpty = list2.isEmpty();
        int i = statusBarAttributes.appearance;
        if (isEmpty) {
            pair = new Pair(Integer.valueOf(i), list);
        } else {
            Integer valueOf = Integer.valueOf(i);
            LetterboxAppearanceCalculator letterboxAppearanceCalculator = statusBarModePerDisplayRepositoryImpl.letterboxAppearanceCalculator;
            letterboxAppearanceCalculator.lastAppearance = valueOf;
            letterboxAppearanceCalculator.lastAppearanceRegions = list;
            letterboxAppearanceCalculator.lastLetterboxes = list2;
            LetterboxBackgroundProvider letterboxBackgroundProvider = letterboxAppearanceCalculator.letterboxBackgroundProvider;
            if (!letterboxBackgroundProvider.isLetterboxBackgroundMultiColored) {
                if (!list2.isEmpty()) {
                    for (LetterboxDetails letterboxDetails : list2) {
                        Rect letterboxInnerBounds = letterboxDetails.getLetterboxInnerBounds();
                        Rect rect = boundsPair.start;
                        if (!((letterboxInnerBounds.contains(rect) || rect.contains(letterboxInnerBounds)) ? false : letterboxInnerBounds.intersects(rect.left, rect.top, rect.right, rect.bottom))) {
                            Rect letterboxInnerBounds2 = letterboxDetails.getLetterboxInnerBounds();
                            Rect rect2 = boundsPair.end;
                            if ((letterboxInnerBounds2.contains(rect2) || rect2.contains(letterboxInnerBounds2)) ? false : letterboxInnerBounds2.intersects(rect2.left, rect2.top, rect2.right, rect2.bottom)) {
                            }
                        }
                    }
                }
                int i2 = i & (-33);
                ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(list, 10));
                for (AppearanceRegion appearanceRegion : list) {
                    Iterator it = list2.iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            obj2 = null;
                            break;
                        }
                        obj2 = it.next();
                        if (Intrinsics.areEqual(((LetterboxDetails) obj2).getLetterboxFullBounds(), appearanceRegion.getBounds())) {
                            break;
                        }
                    }
                    LetterboxDetails letterboxDetails2 = (LetterboxDetails) obj2;
                    if (letterboxDetails2 != null) {
                        appearanceRegion = new AppearanceRegion(appearanceRegion.getAppearance(), letterboxDetails2.getLetterboxInnerBounds());
                    }
                    arrayList.add(appearanceRegion);
                }
                ArrayList arrayList2 = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(list2, 10));
                Iterator it2 = list2.iterator();
                while (it2.hasNext()) {
                    LetterboxDetails letterboxDetails3 = (LetterboxDetails) it2.next();
                    int i3 = letterboxBackgroundProvider.letterboxBackgroundColor;
                    int i4 = ContrastColorUtil.calculateContrast(letterboxAppearanceCalculator.lightAppearanceIconColor, i3) > ContrastColorUtil.calculateContrast(letterboxAppearanceCalculator.darkAppearanceIconColor, i3) ? 8 : 0;
                    Rect letterboxInnerBounds3 = letterboxDetails3.getLetterboxInnerBounds();
                    Rect letterboxFullBounds = letterboxDetails3.getLetterboxFullBounds();
                    Iterator it3 = it2;
                    LetterboxBackgroundProvider letterboxBackgroundProvider2 = letterboxBackgroundProvider;
                    List listOf = CollectionsKt__CollectionsKt.listOf(new Rect(letterboxFullBounds.left, letterboxFullBounds.top, letterboxInnerBounds3.left, letterboxFullBounds.bottom), new Rect(letterboxFullBounds.left, letterboxFullBounds.top, letterboxFullBounds.right, letterboxInnerBounds3.top), new Rect(letterboxInnerBounds3.right, letterboxFullBounds.top, letterboxFullBounds.right, letterboxFullBounds.bottom), new Rect(letterboxFullBounds.left, letterboxInnerBounds3.bottom, letterboxFullBounds.right, letterboxFullBounds.bottom));
                    ArrayList arrayList3 = new ArrayList();
                    for (Object obj3 : listOf) {
                        if (!((Rect) obj3).isEmpty()) {
                            arrayList3.add(obj3);
                        }
                    }
                    ArrayList arrayList4 = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(arrayList3, 10));
                    Iterator it4 = arrayList3.iterator();
                    while (it4.hasNext()) {
                        arrayList4.add(new AppearanceRegion(i4, (Rect) it4.next()));
                    }
                    arrayList2.add(arrayList4);
                    it2 = it3;
                    letterboxBackgroundProvider = letterboxBackgroundProvider2;
                }
                letterboxAppearance = new LetterboxAppearance(i2, CollectionsKt.plus((Iterable) CollectionsKt__IterablesKt.flatten(arrayList2), (Collection) arrayList));
                letterboxAppearanceCalculator.lastLetterboxAppearance = letterboxAppearance;
                pair = new Pair(Integer.valueOf(letterboxAppearance.appearance), letterboxAppearance.appearanceRegions);
            }
            letterboxAppearance = new LetterboxAppearance(i | 32, list);
            letterboxAppearanceCalculator.lastLetterboxAppearance = letterboxAppearance;
            pair = new Pair(Integer.valueOf(letterboxAppearance.appearance), letterboxAppearance.appearanceRegions);
        }
        return new StatusBarModePerDisplayRepositoryImpl.ModifiedStatusBarAttributes(((Number) pair.component1()).intValue(), (List) pair.component2(), statusBarAttributes.navbarColorManagedByIme, boundsPair);
    }
}
