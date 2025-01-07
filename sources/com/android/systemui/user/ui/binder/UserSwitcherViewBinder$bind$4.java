package com.android.systemui.user.ui.binder;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import androidx.constraintlayout.helper.widget.Flow;
import androidx.core.content.res.ResourcesCompat;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleKt;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LifecycleOwnerKt;
import androidx.lifecycle.RepeatOnLifecycleKt;
import com.android.settingslib.Utils;
import com.android.systemui.common.shared.model.Text;
import com.android.systemui.user.UserSwitcherPopupMenu;
import com.android.systemui.user.UserSwitcherRootView;
import com.android.systemui.user.shared.model.UserActionModel;
import com.android.systemui.user.ui.binder.UserSwitcherViewBinder;
import com.android.systemui.user.ui.viewmodel.UserActionViewModel;
import com.android.systemui.user.ui.viewmodel.UserSwitcherViewModel;
import com.android.systemui.user.ui.viewmodel.UserSwitcherViewModel$special$$inlined$map$1;
import com.android.systemui.user.ui.viewmodel.UserSwitcherViewModel$special$$inlined$map$5;
import com.android.systemui.user.ui.viewmodel.UserViewModel;
import com.android.systemui.util.ConvenienceExtensionsKt;
import com.android.wm.shell.R;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.NoWhenBranchMatchedException;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref$ObjectRef;
import kotlin.sequences.SequencesKt;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt__ZipKt$combine$$inlined$combineUnsafe$FlowKt__ZipKt$1;
import kotlinx.coroutines.flow.StateFlowImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
final class UserSwitcherViewBinder$bind$4 extends SuspendLambda implements Function3 {
    final /* synthetic */ View $addButton;
    final /* synthetic */ Flow $flowWidget;
    final /* synthetic */ UserSwitcherRootView $gridContainerView;
    final /* synthetic */ LayoutInflater $layoutInflater;
    final /* synthetic */ Function0 $onFinish;
    final /* synthetic */ Ref$ObjectRef $popupMenu;
    final /* synthetic */ UserSwitcherViewBinder.MenuAdapter $popupMenuAdapter;
    final /* synthetic */ ViewGroup $view;
    final /* synthetic */ UserSwitcherViewModel $viewModel;
    private /* synthetic */ Object L$0;
    int label;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.user.ui.binder.UserSwitcherViewBinder$bind$4$1, reason: invalid class name */
    final class AnonymousClass1 extends SuspendLambda implements Function2 {
        final /* synthetic */ LifecycleOwner $$this$repeatWhenAttached;
        final /* synthetic */ Function0 $onFinish;
        final /* synthetic */ Ref$ObjectRef $popupMenu;
        final /* synthetic */ UserSwitcherViewModel $viewModel;
        int label;

        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        /* renamed from: com.android.systemui.user.ui.binder.UserSwitcherViewBinder$bind$4$1$1, reason: invalid class name and collision with other inner class name */
        final class C02541 extends SuspendLambda implements Function2 {
            final /* synthetic */ Function0 $onFinish;
            final /* synthetic */ Ref$ObjectRef $popupMenu;
            final /* synthetic */ UserSwitcherViewModel $viewModel;
            private /* synthetic */ Object L$0;
            int label;

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.systemui.user.ui.binder.UserSwitcherViewBinder$bind$4$1$1$1, reason: invalid class name and collision with other inner class name */
            final class C02551 extends SuspendLambda implements Function2 {
                final /* synthetic */ Function0 $onFinish;
                final /* synthetic */ Ref$ObjectRef $popupMenu;
                final /* synthetic */ UserSwitcherViewModel $viewModel;
                int label;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public C02551(UserSwitcherViewModel userSwitcherViewModel, Ref$ObjectRef ref$ObjectRef, Function0 function0, Continuation continuation) {
                    super(2, continuation);
                    this.$viewModel = userSwitcherViewModel;
                    this.$popupMenu = ref$ObjectRef;
                    this.$onFinish = function0;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Continuation create(Object obj, Continuation continuation) {
                    return new C02551(this.$viewModel, this.$popupMenu, this.$onFinish, continuation);
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return ((C02551) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object obj) {
                    CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
                    int i = this.label;
                    Unit unit = Unit.INSTANCE;
                    if (i == 0) {
                        ResultKt.throwOnFailure(obj);
                        final UserSwitcherViewModel userSwitcherViewModel = this.$viewModel;
                        FlowKt__ZipKt$combine$$inlined$combineUnsafe$FlowKt__ZipKt$1 flowKt__ZipKt$combine$$inlined$combineUnsafe$FlowKt__ZipKt$1 = userSwitcherViewModel.isFinishRequested;
                        final Ref$ObjectRef ref$ObjectRef = this.$popupMenu;
                        final Function0 function0 = this.$onFinish;
                        FlowCollector flowCollector = new FlowCollector() { // from class: com.android.systemui.user.ui.binder.UserSwitcherViewBinder.bind.4.1.1.1.2
                            @Override // kotlinx.coroutines.flow.FlowCollector
                            public final Object emit(Object obj2, Continuation continuation) {
                                ((Boolean) obj2).getClass();
                                UserSwitcherPopupMenu userSwitcherPopupMenu = (UserSwitcherPopupMenu) Ref$ObjectRef.this.element;
                                if (userSwitcherPopupMenu != null) {
                                    userSwitcherPopupMenu.dismiss();
                                }
                                function0.invoke();
                                UserSwitcherViewModel userSwitcherViewModel2 = userSwitcherViewModel;
                                StateFlowImpl stateFlowImpl = userSwitcherViewModel2.hasCancelButtonBeenClicked;
                                Boolean bool = Boolean.FALSE;
                                stateFlowImpl.getClass();
                                stateFlowImpl.updateState(null, bool);
                                StateFlowImpl stateFlowImpl2 = userSwitcherViewModel2.isFinishRequiredDueToExecutedAction;
                                stateFlowImpl2.getClass();
                                stateFlowImpl2.updateState(null, bool);
                                StateFlowImpl stateFlowImpl3 = userSwitcherViewModel2.userSwitched;
                                stateFlowImpl3.getClass();
                                stateFlowImpl3.updateState(null, bool);
                                return Unit.INSTANCE;
                            }
                        };
                        this.label = 1;
                        Object collect = flowKt__ZipKt$combine$$inlined$combineUnsafe$FlowKt__ZipKt$1.collect(new UserSwitcherViewBinder$bind$4$1$1$1$invokeSuspend$$inlined$filter$1$2(flowCollector), this);
                        if (collect != coroutineSingletons) {
                            collect = unit;
                        }
                        if (collect == coroutineSingletons) {
                            return coroutineSingletons;
                        }
                    } else {
                        if (i != 1) {
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                        ResultKt.throwOnFailure(obj);
                    }
                    return unit;
                }
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public C02541(UserSwitcherViewModel userSwitcherViewModel, Ref$ObjectRef ref$ObjectRef, Function0 function0, Continuation continuation) {
                super(2, continuation);
                this.$viewModel = userSwitcherViewModel;
                this.$popupMenu = ref$ObjectRef;
                this.$onFinish = function0;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation create(Object obj, Continuation continuation) {
                C02541 c02541 = new C02541(this.$viewModel, this.$popupMenu, this.$onFinish, continuation);
                c02541.L$0 = obj;
                return c02541;
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                C02541 c02541 = (C02541) create((CoroutineScope) obj, (Continuation) obj2);
                Unit unit = Unit.INSTANCE;
                c02541.invokeSuspend(unit);
                return unit;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
                if (this.label != 0) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
                BuildersKt.launch$default((CoroutineScope) this.L$0, null, null, new C02551(this.$viewModel, this.$popupMenu, this.$onFinish, null), 3);
                return Unit.INSTANCE;
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(LifecycleOwner lifecycleOwner, UserSwitcherViewModel userSwitcherViewModel, Ref$ObjectRef ref$ObjectRef, Function0 function0, Continuation continuation) {
            super(2, continuation);
            this.$$this$repeatWhenAttached = lifecycleOwner;
            this.$viewModel = userSwitcherViewModel;
            this.$popupMenu = ref$ObjectRef;
            this.$onFinish = function0;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation create(Object obj, Continuation continuation) {
            return new AnonymousClass1(this.$$this$repeatWhenAttached, this.$viewModel, this.$popupMenu, this.$onFinish, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ((AnonymousClass1) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                LifecycleOwner lifecycleOwner = this.$$this$repeatWhenAttached;
                Lifecycle.State state = Lifecycle.State.CREATED;
                C02541 c02541 = new C02541(this.$viewModel, this.$popupMenu, this.$onFinish, null);
                this.label = 1;
                if (RepeatOnLifecycleKt.repeatOnLifecycle(lifecycleOwner, state, c02541, this) == coroutineSingletons) {
                    return coroutineSingletons;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            return Unit.INSTANCE;
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.user.ui.binder.UserSwitcherViewBinder$bind$4$2, reason: invalid class name */
    final class AnonymousClass2 extends SuspendLambda implements Function2 {
        final /* synthetic */ LifecycleOwner $$this$repeatWhenAttached;
        final /* synthetic */ View $addButton;
        final /* synthetic */ Flow $flowWidget;
        final /* synthetic */ UserSwitcherRootView $gridContainerView;
        final /* synthetic */ LayoutInflater $layoutInflater;
        final /* synthetic */ Ref$ObjectRef $popupMenu;
        final /* synthetic */ UserSwitcherViewBinder.MenuAdapter $popupMenuAdapter;
        final /* synthetic */ ViewGroup $view;
        final /* synthetic */ UserSwitcherViewModel $viewModel;
        int label;

        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        /* renamed from: com.android.systemui.user.ui.binder.UserSwitcherViewBinder$bind$4$2$1, reason: invalid class name */
        final class AnonymousClass1 extends SuspendLambda implements Function2 {
            final /* synthetic */ View $addButton;
            final /* synthetic */ Flow $flowWidget;
            final /* synthetic */ UserSwitcherRootView $gridContainerView;
            final /* synthetic */ LayoutInflater $layoutInflater;
            final /* synthetic */ Ref$ObjectRef $popupMenu;
            final /* synthetic */ UserSwitcherViewBinder.MenuAdapter $popupMenuAdapter;
            final /* synthetic */ ViewGroup $view;
            final /* synthetic */ UserSwitcherViewModel $viewModel;
            private /* synthetic */ Object L$0;
            int label;

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.systemui.user.ui.binder.UserSwitcherViewBinder$bind$4$2$1$1, reason: invalid class name and collision with other inner class name */
            final class C02561 extends SuspendLambda implements Function2 {
                final /* synthetic */ View $addButton;
                final /* synthetic */ UserSwitcherViewModel $viewModel;
                int label;

                /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                /* renamed from: com.android.systemui.user.ui.binder.UserSwitcherViewBinder$bind$4$2$1$1$1, reason: invalid class name and collision with other inner class name */
                public final class C02571 implements FlowCollector {
                    public final /* synthetic */ Object $addButton;
                    public final /* synthetic */ int $r8$classId;

                    public /* synthetic */ C02571(int i, Object obj) {
                        this.$r8$classId = i;
                        this.$addButton = obj;
                    }

                    @Override // kotlinx.coroutines.flow.FlowCollector
                    public final Object emit(Object obj, Continuation continuation) {
                        Unit unit = Unit.INSTANCE;
                        Object obj2 = this.$addButton;
                        switch (this.$r8$classId) {
                            case 0:
                                ((View) obj2).setVisibility(((Boolean) obj).booleanValue() ? 0 : 8);
                                break;
                            case 1:
                                List list = (List) obj;
                                UserSwitcherViewBinder.MenuAdapter menuAdapter = (UserSwitcherViewBinder.MenuAdapter) obj2;
                                menuAdapter.getClass();
                                ArrayList arrayList = new ArrayList();
                                for (Object obj3 : list) {
                                    long j = ((UserActionViewModel) obj3).viewKey;
                                    UserActionModel userActionModel = UserActionModel.ENTER_GUEST_MODE;
                                    if (j != 3) {
                                        arrayList.add(obj3);
                                    }
                                }
                                ArrayList arrayList2 = new ArrayList();
                                for (Object obj4 : list) {
                                    long j2 = ((UserActionViewModel) obj4).viewKey;
                                    UserActionModel userActionModel2 = UserActionModel.ENTER_GUEST_MODE;
                                    if (j2 == 3) {
                                        arrayList2.add(obj4);
                                    }
                                }
                                menuAdapter.sections = CollectionsKt__CollectionsKt.listOf(arrayList, arrayList2);
                                menuAdapter.notifyDataSetChanged();
                                break;
                            default:
                                Flow flow = (Flow) obj2;
                                flow.mFlow.mMaxElementsWrap = ((Number) obj).intValue();
                                flow.requestLayout();
                                break;
                        }
                        return unit;
                    }
                }

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public C02561(UserSwitcherViewModel userSwitcherViewModel, View view, Continuation continuation) {
                    super(2, continuation);
                    this.$viewModel = userSwitcherViewModel;
                    this.$addButton = view;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Continuation create(Object obj, Continuation continuation) {
                    return new C02561(this.$viewModel, this.$addButton, continuation);
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return ((C02561) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object obj) {
                    CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
                    int i = this.label;
                    if (i == 0) {
                        ResultKt.throwOnFailure(obj);
                        UserSwitcherViewModel$special$$inlined$map$5 userSwitcherViewModel$special$$inlined$map$5 = this.$viewModel.isOpenMenuButtonVisible;
                        C02571 c02571 = new C02571(0, this.$addButton);
                        this.label = 1;
                        if (userSwitcherViewModel$special$$inlined$map$5.collect(c02571, this) == coroutineSingletons) {
                            return coroutineSingletons;
                        }
                    } else {
                        if (i != 1) {
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                        ResultKt.throwOnFailure(obj);
                    }
                    return Unit.INSTANCE;
                }
            }

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.systemui.user.ui.binder.UserSwitcherViewBinder$bind$4$2$1$2, reason: invalid class name and collision with other inner class name */
            final class C02582 extends SuspendLambda implements Function2 {
                final /* synthetic */ View $addButton;
                final /* synthetic */ Ref$ObjectRef $popupMenu;
                final /* synthetic */ UserSwitcherViewBinder.MenuAdapter $popupMenuAdapter;
                final /* synthetic */ ViewGroup $view;
                final /* synthetic */ UserSwitcherViewModel $viewModel;
                int label;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public C02582(UserSwitcherViewModel userSwitcherViewModel, Ref$ObjectRef ref$ObjectRef, ViewGroup viewGroup, View view, UserSwitcherViewBinder.MenuAdapter menuAdapter, Continuation continuation) {
                    super(2, continuation);
                    this.$viewModel = userSwitcherViewModel;
                    this.$popupMenu = ref$ObjectRef;
                    this.$view = viewGroup;
                    this.$addButton = view;
                    this.$popupMenuAdapter = menuAdapter;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Continuation create(Object obj, Continuation continuation) {
                    return new C02582(this.$viewModel, this.$popupMenu, this.$view, this.$addButton, this.$popupMenuAdapter, continuation);
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return ((C02582) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object obj) {
                    CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
                    int i = this.label;
                    if (i != 0) {
                        if (i != 1) {
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                        ResultKt.throwOnFailure(obj);
                        return Unit.INSTANCE;
                    }
                    ResultKt.throwOnFailure(obj);
                    final UserSwitcherViewModel userSwitcherViewModel = this.$viewModel;
                    StateFlowImpl stateFlowImpl = userSwitcherViewModel.isMenuVisible;
                    final Ref$ObjectRef ref$ObjectRef = this.$popupMenu;
                    final ViewGroup viewGroup = this.$view;
                    final View view = this.$addButton;
                    final UserSwitcherViewBinder.MenuAdapter menuAdapter = this.$popupMenuAdapter;
                    FlowCollector flowCollector = new FlowCollector() { // from class: com.android.systemui.user.ui.binder.UserSwitcherViewBinder.bind.4.2.1.2.1
                        @Override // kotlinx.coroutines.flow.FlowCollector
                        public final Object emit(Object obj2, Continuation continuation) {
                            UserSwitcherPopupMenu userSwitcherPopupMenu;
                            UserSwitcherPopupMenu userSwitcherPopupMenu2;
                            boolean booleanValue = ((Boolean) obj2).booleanValue();
                            Ref$ObjectRef ref$ObjectRef2 = Ref$ObjectRef.this;
                            if (booleanValue && ((userSwitcherPopupMenu2 = (UserSwitcherPopupMenu) ref$ObjectRef2.element) == null || !userSwitcherPopupMenu2.isShowing())) {
                                UserSwitcherPopupMenu userSwitcherPopupMenu3 = (UserSwitcherPopupMenu) ref$ObjectRef2.element;
                                if (userSwitcherPopupMenu3 != null) {
                                    userSwitcherPopupMenu3.dismiss();
                                }
                                final ViewGroup viewGroup2 = viewGroup;
                                final View view2 = view;
                                final Ref$ObjectRef ref$ObjectRef3 = Ref$ObjectRef.this;
                                final UserSwitcherViewBinder.MenuAdapter menuAdapter2 = menuAdapter;
                                final UserSwitcherViewModel userSwitcherViewModel2 = userSwitcherViewModel;
                                viewGroup2.post(new Runnable() { // from class: com.android.systemui.user.ui.binder.UserSwitcherViewBinder.bind.4.2.1.2.1.1

                                    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                                    /* renamed from: com.android.systemui.user.ui.binder.UserSwitcherViewBinder$bind$4$2$1$2$1$1$1, reason: invalid class name and collision with other inner class name */
                                    final /* synthetic */ class C02611 extends FunctionReferenceImpl implements Function0 {
                                        @Override // kotlin.jvm.functions.Function0
                                        public final Object invoke() {
                                            StateFlowImpl stateFlowImpl = ((UserSwitcherViewModel) this.receiver)._isMenuVisible;
                                            Boolean bool = Boolean.FALSE;
                                            stateFlowImpl.getClass();
                                            stateFlowImpl.updateState(null, bool);
                                            return Unit.INSTANCE;
                                        }
                                    }

                                    @Override // java.lang.Runnable
                                    public final void run() {
                                        Ref$ObjectRef ref$ObjectRef4 = Ref$ObjectRef.this;
                                        Context context = viewGroup2.getContext();
                                        View view3 = view2;
                                        UserSwitcherViewBinder.MenuAdapter menuAdapter3 = menuAdapter2;
                                        final C02611 c02611 = new C02611(0, userSwitcherViewModel2, UserSwitcherViewModel.class, "onMenuClosed", "onMenuClosed()V", 0);
                                        UserSwitcherPopupMenu userSwitcherPopupMenu4 = new UserSwitcherPopupMenu(context);
                                        userSwitcherPopupMenu4.setDropDownGravity(8388613);
                                        userSwitcherPopupMenu4.setAnchorView(view3);
                                        userSwitcherPopupMenu4.setAdapter(menuAdapter3);
                                        userSwitcherPopupMenu4.setOnDismissListener(new PopupWindow.OnDismissListener() { // from class: com.android.systemui.user.ui.binder.UserSwitcherViewBinder$createAndShowPopupMenu$1$1
                                            @Override // android.widget.PopupWindow.OnDismissListener
                                            public final void onDismiss() {
                                                Function0.this.invoke();
                                            }
                                        });
                                        userSwitcherPopupMenu4.show();
                                        ref$ObjectRef4.element = userSwitcherPopupMenu4;
                                    }
                                });
                            } else if (!booleanValue && (userSwitcherPopupMenu = (UserSwitcherPopupMenu) ref$ObjectRef2.element) != null && userSwitcherPopupMenu.isShowing()) {
                                UserSwitcherPopupMenu userSwitcherPopupMenu4 = (UserSwitcherPopupMenu) ref$ObjectRef2.element;
                                if (userSwitcherPopupMenu4 != null) {
                                    userSwitcherPopupMenu4.dismiss();
                                }
                                ref$ObjectRef2.element = null;
                            }
                            return Unit.INSTANCE;
                        }
                    };
                    this.label = 1;
                    stateFlowImpl.collect(flowCollector, this);
                    return coroutineSingletons;
                }
            }

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.systemui.user.ui.binder.UserSwitcherViewBinder$bind$4$2$1$3, reason: invalid class name */
            final class AnonymousClass3 extends SuspendLambda implements Function2 {
                final /* synthetic */ UserSwitcherViewBinder.MenuAdapter $popupMenuAdapter;
                final /* synthetic */ UserSwitcherViewModel $viewModel;
                int label;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public AnonymousClass3(UserSwitcherViewModel userSwitcherViewModel, UserSwitcherViewBinder.MenuAdapter menuAdapter, Continuation continuation) {
                    super(2, continuation);
                    this.$viewModel = userSwitcherViewModel;
                    this.$popupMenuAdapter = menuAdapter;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Continuation create(Object obj, Continuation continuation) {
                    return new AnonymousClass3(this.$viewModel, this.$popupMenuAdapter, continuation);
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return ((AnonymousClass3) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object obj) {
                    CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
                    int i = this.label;
                    if (i == 0) {
                        ResultKt.throwOnFailure(obj);
                        UserSwitcherViewModel$special$$inlined$map$1 userSwitcherViewModel$special$$inlined$map$1 = this.$viewModel.menu;
                        C02561.C02571 c02571 = new C02561.C02571(1, this.$popupMenuAdapter);
                        this.label = 1;
                        if (userSwitcherViewModel$special$$inlined$map$1.collect(c02571, this) == coroutineSingletons) {
                            return coroutineSingletons;
                        }
                    } else {
                        if (i != 1) {
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                        ResultKt.throwOnFailure(obj);
                    }
                    return Unit.INSTANCE;
                }
            }

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.systemui.user.ui.binder.UserSwitcherViewBinder$bind$4$2$1$4, reason: invalid class name */
            final class AnonymousClass4 extends SuspendLambda implements Function2 {
                final /* synthetic */ Flow $flowWidget;
                final /* synthetic */ UserSwitcherViewModel $viewModel;
                int label;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public AnonymousClass4(UserSwitcherViewModel userSwitcherViewModel, Flow flow, Continuation continuation) {
                    super(2, continuation);
                    this.$viewModel = userSwitcherViewModel;
                    this.$flowWidget = flow;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Continuation create(Object obj, Continuation continuation) {
                    return new AnonymousClass4(this.$viewModel, this.$flowWidget, continuation);
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return ((AnonymousClass4) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object obj) {
                    CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
                    int i = this.label;
                    if (i == 0) {
                        ResultKt.throwOnFailure(obj);
                        UserSwitcherViewModel$special$$inlined$map$1 userSwitcherViewModel$special$$inlined$map$1 = this.$viewModel.maximumUserColumns;
                        C02561.C02571 c02571 = new C02561.C02571(2, this.$flowWidget);
                        this.label = 1;
                        if (userSwitcherViewModel$special$$inlined$map$1.collect(c02571, this) == coroutineSingletons) {
                            return coroutineSingletons;
                        }
                    } else {
                        if (i != 1) {
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                        ResultKt.throwOnFailure(obj);
                    }
                    return Unit.INSTANCE;
                }
            }

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.systemui.user.ui.binder.UserSwitcherViewBinder$bind$4$2$1$5, reason: invalid class name */
            final class AnonymousClass5 extends SuspendLambda implements Function2 {
                final /* synthetic */ Flow $flowWidget;
                final /* synthetic */ UserSwitcherRootView $gridContainerView;
                final /* synthetic */ LayoutInflater $layoutInflater;
                final /* synthetic */ ViewGroup $view;
                final /* synthetic */ UserSwitcherViewModel $viewModel;
                int label;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public AnonymousClass5(UserSwitcherViewModel userSwitcherViewModel, UserSwitcherRootView userSwitcherRootView, Flow flow, LayoutInflater layoutInflater, ViewGroup viewGroup, Continuation continuation) {
                    super(2, continuation);
                    this.$viewModel = userSwitcherViewModel;
                    this.$gridContainerView = userSwitcherRootView;
                    this.$flowWidget = flow;
                    this.$layoutInflater = layoutInflater;
                    this.$view = viewGroup;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Continuation create(Object obj, Continuation continuation) {
                    return new AnonymousClass5(this.$viewModel, this.$gridContainerView, this.$flowWidget, this.$layoutInflater, this.$view, continuation);
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return ((AnonymousClass5) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object obj) {
                    CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
                    int i = this.label;
                    if (i == 0) {
                        ResultKt.throwOnFailure(obj);
                        UserSwitcherViewModel$special$$inlined$map$1 userSwitcherViewModel$special$$inlined$map$1 = this.$viewModel.users;
                        final UserSwitcherRootView userSwitcherRootView = this.$gridContainerView;
                        final Flow flow = this.$flowWidget;
                        final LayoutInflater layoutInflater = this.$layoutInflater;
                        final ViewGroup viewGroup = this.$view;
                        FlowCollector flowCollector = new FlowCollector() { // from class: com.android.systemui.user.ui.binder.UserSwitcherViewBinder.bind.4.2.1.5.1
                            @Override // kotlinx.coroutines.flow.FlowCollector
                            public final Object emit(Object obj2, Continuation continuation) {
                                Flow flow2;
                                View inflate;
                                String str;
                                List<UserViewModel> list = (List) obj2;
                                UserSwitcherRootView userSwitcherRootView2 = UserSwitcherRootView.this;
                                List mutableList = SequencesKt.toMutableList(SequencesKt.filter(ConvenienceExtensionsKt.getChildren(userSwitcherRootView2), new Function1() { // from class: com.android.systemui.user.ui.binder.UserSwitcherViewBinder$bind$4$2$1$5$1$viewPool$1
                                    @Override // kotlin.jvm.functions.Function1
                                    public final Object invoke(Object obj3) {
                                        return Boolean.valueOf(Intrinsics.areEqual(((View) obj3).getTag(), "user_view"));
                                    }
                                }));
                                Iterator it = mutableList.iterator();
                                while (true) {
                                    boolean hasNext = it.hasNext();
                                    flow2 = flow;
                                    if (!hasNext) {
                                        break;
                                    }
                                    View view = (View) it.next();
                                    userSwitcherRootView2.removeView(view);
                                    flow2.removeView(view);
                                }
                                LayoutInflater layoutInflater2 = layoutInflater;
                                ViewGroup viewGroup2 = viewGroup;
                                for (final UserViewModel userViewModel : list) {
                                    if (mutableList.isEmpty()) {
                                        inflate = layoutInflater2.inflate(R.layout.user_switcher_fullscreen_item, viewGroup2, false);
                                        inflate.setTag("user_view");
                                    } else {
                                        inflate = (View) mutableList.remove(0);
                                    }
                                    inflate.setId(View.generateViewId());
                                    userSwitcherRootView2.addView(inflate);
                                    flow2.addView(inflate);
                                    TextView textView = (TextView) inflate.requireViewById(R.id.user_switcher_text);
                                    Text text = userViewModel.name;
                                    if (text instanceof Text.Resource) {
                                        str = textView.getContext().getString(((Text.Resource) text).res);
                                    } else {
                                        if (!(text instanceof Text.Loaded)) {
                                            throw new NoWhenBranchMatchedException();
                                        }
                                        str = ((Text.Loaded) text).text;
                                    }
                                    textView.setText(str);
                                    ImageView imageView = (ImageView) inflate.requireViewById(R.id.user_switcher_icon);
                                    Context context = inflate.getContext();
                                    Resources resources = context.getResources();
                                    Resources.Theme theme = context.getTheme();
                                    ThreadLocal threadLocal = ResourcesCompat.sTempTypedValue;
                                    Drawable drawable = resources.getDrawable(R.drawable.user_switcher_icon_large, theme);
                                    if (drawable == null) {
                                        throw new IllegalStateException("Required value was null.");
                                    }
                                    LayerDrawable layerDrawable = (LayerDrawable) drawable.mutate();
                                    if (userViewModel.isSelectionMarkerVisible) {
                                        ((GradientDrawable) layerDrawable.findDrawableByLayerId(R.id.ring)).setStroke(context.getResources().getDimensionPixelSize(R.dimen.user_switcher_icon_selected_width), Utils.getColorAttrDefaultColor(android.R.^attr-private.colorAccentPrimary, 0, context));
                                    }
                                    layerDrawable.setDrawableByLayerId(R.id.user_avatar, userViewModel.image);
                                    imageView.setImageDrawable(layerDrawable);
                                    inflate.setAlpha(userViewModel.alpha);
                                    if (userViewModel.onClicked != null) {
                                        inflate.setOnClickListener(new View.OnClickListener() { // from class: com.android.systemui.user.ui.binder.UserViewBinder$bind$1
                                            @Override // android.view.View.OnClickListener
                                            public final void onClick(View view2) {
                                                UserViewModel.this.onClicked.invoke();
                                            }
                                        });
                                    } else {
                                        inflate.setOnClickListener(null);
                                    }
                                }
                                return Unit.INSTANCE;
                            }
                        };
                        this.label = 1;
                        if (userSwitcherViewModel$special$$inlined$map$1.collect(flowCollector, this) == coroutineSingletons) {
                            return coroutineSingletons;
                        }
                    } else {
                        if (i != 1) {
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                        ResultKt.throwOnFailure(obj);
                    }
                    return Unit.INSTANCE;
                }
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public AnonymousClass1(UserSwitcherViewModel userSwitcherViewModel, View view, Ref$ObjectRef ref$ObjectRef, ViewGroup viewGroup, UserSwitcherViewBinder.MenuAdapter menuAdapter, Flow flow, UserSwitcherRootView userSwitcherRootView, LayoutInflater layoutInflater, Continuation continuation) {
                super(2, continuation);
                this.$viewModel = userSwitcherViewModel;
                this.$addButton = view;
                this.$popupMenu = ref$ObjectRef;
                this.$view = viewGroup;
                this.$popupMenuAdapter = menuAdapter;
                this.$flowWidget = flow;
                this.$gridContainerView = userSwitcherRootView;
                this.$layoutInflater = layoutInflater;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation create(Object obj, Continuation continuation) {
                AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$viewModel, this.$addButton, this.$popupMenu, this.$view, this.$popupMenuAdapter, this.$flowWidget, this.$gridContainerView, this.$layoutInflater, continuation);
                anonymousClass1.L$0 = obj;
                return anonymousClass1;
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                AnonymousClass1 anonymousClass1 = (AnonymousClass1) create((CoroutineScope) obj, (Continuation) obj2);
                Unit unit = Unit.INSTANCE;
                anonymousClass1.invokeSuspend(unit);
                return unit;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
                if (this.label != 0) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
                CoroutineScope coroutineScope = (CoroutineScope) this.L$0;
                BuildersKt.launch$default(coroutineScope, null, null, new C02561(this.$viewModel, this.$addButton, null), 3);
                BuildersKt.launch$default(coroutineScope, null, null, new C02582(this.$viewModel, this.$popupMenu, this.$view, this.$addButton, this.$popupMenuAdapter, null), 3);
                BuildersKt.launch$default(coroutineScope, null, null, new AnonymousClass3(this.$viewModel, this.$popupMenuAdapter, null), 3);
                BuildersKt.launch$default(coroutineScope, null, null, new AnonymousClass4(this.$viewModel, this.$flowWidget, null), 3);
                BuildersKt.launch$default(coroutineScope, null, null, new AnonymousClass5(this.$viewModel, this.$gridContainerView, this.$flowWidget, this.$layoutInflater, this.$view, null), 3);
                return Unit.INSTANCE;
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass2(LifecycleOwner lifecycleOwner, UserSwitcherViewModel userSwitcherViewModel, View view, Ref$ObjectRef ref$ObjectRef, ViewGroup viewGroup, UserSwitcherViewBinder.MenuAdapter menuAdapter, Flow flow, UserSwitcherRootView userSwitcherRootView, LayoutInflater layoutInflater, Continuation continuation) {
            super(2, continuation);
            this.$$this$repeatWhenAttached = lifecycleOwner;
            this.$viewModel = userSwitcherViewModel;
            this.$addButton = view;
            this.$popupMenu = ref$ObjectRef;
            this.$view = viewGroup;
            this.$popupMenuAdapter = menuAdapter;
            this.$flowWidget = flow;
            this.$gridContainerView = userSwitcherRootView;
            this.$layoutInflater = layoutInflater;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation create(Object obj, Continuation continuation) {
            return new AnonymousClass2(this.$$this$repeatWhenAttached, this.$viewModel, this.$addButton, this.$popupMenu, this.$view, this.$popupMenuAdapter, this.$flowWidget, this.$gridContainerView, this.$layoutInflater, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ((AnonymousClass2) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                LifecycleOwner lifecycleOwner = this.$$this$repeatWhenAttached;
                Lifecycle.State state = Lifecycle.State.STARTED;
                AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$viewModel, this.$addButton, this.$popupMenu, this.$view, this.$popupMenuAdapter, this.$flowWidget, this.$gridContainerView, this.$layoutInflater, null);
                this.label = 1;
                if (RepeatOnLifecycleKt.repeatOnLifecycle(lifecycleOwner, state, anonymousClass1, this) == coroutineSingletons) {
                    return coroutineSingletons;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            return Unit.INSTANCE;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public UserSwitcherViewBinder$bind$4(UserSwitcherViewModel userSwitcherViewModel, Ref$ObjectRef ref$ObjectRef, Function0 function0, View view, ViewGroup viewGroup, UserSwitcherViewBinder.MenuAdapter menuAdapter, Flow flow, UserSwitcherRootView userSwitcherRootView, LayoutInflater layoutInflater, Continuation continuation) {
        super(3, continuation);
        this.$viewModel = userSwitcherViewModel;
        this.$popupMenu = ref$ObjectRef;
        this.$onFinish = function0;
        this.$addButton = view;
        this.$view = viewGroup;
        this.$popupMenuAdapter = menuAdapter;
        this.$flowWidget = flow;
        this.$gridContainerView = userSwitcherRootView;
        this.$layoutInflater = layoutInflater;
    }

    @Override // kotlin.jvm.functions.Function3
    public final Object invoke(Object obj, Object obj2, Object obj3) {
        UserSwitcherViewBinder$bind$4 userSwitcherViewBinder$bind$4 = new UserSwitcherViewBinder$bind$4(this.$viewModel, this.$popupMenu, this.$onFinish, this.$addButton, this.$view, this.$popupMenuAdapter, this.$flowWidget, this.$gridContainerView, this.$layoutInflater, (Continuation) obj3);
        userSwitcherViewBinder$bind$4.L$0 = (LifecycleOwner) obj;
        Unit unit = Unit.INSTANCE;
        userSwitcherViewBinder$bind$4.invokeSuspend(unit);
        return unit;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        LifecycleOwner lifecycleOwner = (LifecycleOwner) this.L$0;
        BuildersKt.launch$default(LifecycleOwnerKt.getLifecycleScope(lifecycleOwner), null, null, new AnonymousClass1(lifecycleOwner, this.$viewModel, this.$popupMenu, this.$onFinish, null), 3);
        BuildersKt.launch$default(LifecycleKt.getCoroutineScope(lifecycleOwner.getLifecycle()), null, null, new AnonymousClass2(lifecycleOwner, this.$viewModel, this.$addButton, this.$popupMenu, this.$view, this.$popupMenuAdapter, this.$flowWidget, this.$gridContainerView, this.$layoutInflater, null), 3);
        return Unit.INSTANCE;
    }
}
