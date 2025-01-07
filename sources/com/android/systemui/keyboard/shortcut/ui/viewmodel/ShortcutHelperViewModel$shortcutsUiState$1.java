package com.android.systemui.keyboard.shortcut.ui.viewmodel;

import com.android.systemui.keyboard.shortcut.shared.model.Shortcut;
import com.android.systemui.keyboard.shortcut.shared.model.ShortcutCategory;
import com.android.systemui.keyboard.shortcut.shared.model.ShortcutCategoryType;
import com.android.systemui.keyboard.shortcut.shared.model.ShortcutSubCategory;
import com.android.systemui.keyboard.shortcut.ui.model.ShortcutsUiState;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function3;
import kotlin.text.StringsKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class ShortcutHelperViewModel$shortcutsUiState$1 extends SuspendLambda implements Function3 {
    /* synthetic */ Object L$0;
    /* synthetic */ Object L$1;
    int label;
    final /* synthetic */ ShortcutHelperViewModel this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ShortcutHelperViewModel$shortcutsUiState$1(ShortcutHelperViewModel shortcutHelperViewModel, Continuation continuation) {
        super(3, continuation);
        this.this$0 = shortcutHelperViewModel;
    }

    @Override // kotlin.jvm.functions.Function3
    public final Object invoke(Object obj, Object obj2, Object obj3) {
        ShortcutHelperViewModel$shortcutsUiState$1 shortcutHelperViewModel$shortcutsUiState$1 = new ShortcutHelperViewModel$shortcutsUiState$1(this.this$0, (Continuation) obj3);
        shortcutHelperViewModel$shortcutsUiState$1.L$0 = (String) obj;
        shortcutHelperViewModel$shortcutsUiState$1.L$1 = (List) obj2;
        return shortcutHelperViewModel$shortcutsUiState$1.invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        String str;
        Object access$excludeLauncherApp;
        Object access$getDefaultSelectedCategory;
        List list;
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            str = (String) this.L$0;
            List list2 = (List) this.L$1;
            if (list2.isEmpty()) {
                return ShortcutsUiState.Inactive.INSTANCE;
            }
            ShortcutHelperViewModel shortcutHelperViewModel = this.this$0;
            this.L$0 = str;
            this.label = 1;
            access$excludeLauncherApp = ShortcutHelperViewModel.access$excludeLauncherApp(shortcutHelperViewModel, list2, this);
            if (access$excludeLauncherApp == coroutineSingletons) {
                return coroutineSingletons;
            }
        } else {
            if (i != 1) {
                if (i != 2) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                list = (List) this.L$1;
                String str2 = (String) this.L$0;
                ResultKt.throwOnFailure(obj);
                str = str2;
                access$getDefaultSelectedCategory = obj;
                return new ShortcutsUiState.Active(str, list, (ShortcutCategoryType) access$getDefaultSelectedCategory);
            }
            str = (String) this.L$0;
            ResultKt.throwOnFailure(obj);
            access$excludeLauncherApp = obj;
        }
        List<ShortcutCategory> list3 = (List) access$excludeLauncherApp;
        this.this$0.getClass();
        String lowerCase = StringsKt.trim(str).toString().toLowerCase(Locale.ROOT);
        if (lowerCase.length() != 0) {
            int i2 = 10;
            ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(list3, 10));
            for (ShortcutCategory shortcutCategory : list3) {
                List<ShortcutSubCategory> list4 = shortcutCategory.subCategories;
                ArrayList arrayList2 = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(list4, i2));
                for (ShortcutSubCategory shortcutSubCategory : list4) {
                    List list5 = shortcutSubCategory.shortcuts;
                    ArrayList arrayList3 = new ArrayList();
                    for (Object obj2 : list5) {
                        if (StringsKt.contains$default(StringsKt.trim(((Shortcut) obj2).label).toString().toLowerCase(Locale.ROOT), lowerCase)) {
                            arrayList3.add(obj2);
                        }
                    }
                    arrayList2.add(new ShortcutSubCategory(shortcutSubCategory.label, arrayList3));
                }
                ArrayList arrayList4 = new ArrayList();
                for (Object obj3 : arrayList2) {
                    if (!((ShortcutSubCategory) obj3).shortcuts.isEmpty()) {
                        arrayList4.add(obj3);
                    }
                }
                arrayList.add(new ShortcutCategory(shortcutCategory.type, arrayList4));
                i2 = 10;
            }
            list3 = new ArrayList();
            for (Object obj4 : arrayList) {
                if (!((ShortcutCategory) obj4).subCategories.isEmpty()) {
                    list3.add(obj4);
                }
            }
        }
        ShortcutHelperViewModel shortcutHelperViewModel2 = this.this$0;
        this.L$0 = str;
        this.L$1 = list3;
        this.label = 2;
        access$getDefaultSelectedCategory = ShortcutHelperViewModel.access$getDefaultSelectedCategory(shortcutHelperViewModel2, list3, this);
        if (access$getDefaultSelectedCategory == coroutineSingletons) {
            return coroutineSingletons;
        }
        list = list3;
        return new ShortcutsUiState.Active(str, list, (ShortcutCategoryType) access$getDefaultSelectedCategory);
    }
}
