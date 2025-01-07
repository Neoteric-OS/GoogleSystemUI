package com.android.systemui.statusbar.notification.footer.ui.viewbinder;

import androidx.compose.foundation.text.input.internal.AndroidLegacyPlatformTextInputServiceAdapter$startInput$2$1$1$$ExternalSyntheticOutline0;
import com.android.systemui.statusbar.notification.footer.ui.view.FooterView;
import com.android.systemui.statusbar.notification.footer.ui.viewmodel.FooterMessageViewModel;
import com.android.systemui.statusbar.notification.footer.ui.viewmodel.FooterViewModel;
import com.android.systemui.util.ui.AnimatedValue;
import com.android.wm.shell.R;
import kotlin.NoWhenBranchMatchedException;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.FlowCollector;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
final class FooterViewBinder$bindMessage$2 extends SuspendLambda implements Function2 {
    final /* synthetic */ FooterView $footer;
    final /* synthetic */ FooterViewModel $viewModel;
    private /* synthetic */ Object L$0;
    int label;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.statusbar.notification.footer.ui.viewbinder.FooterViewBinder$bindMessage$2$1, reason: invalid class name */
    final class AnonymousClass1 extends SuspendLambda implements Function2 {
        final /* synthetic */ FooterView $footer;
        final /* synthetic */ FooterViewModel $viewModel;
        int label;

        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        /* renamed from: com.android.systemui.statusbar.notification.footer.ui.viewbinder.FooterViewBinder$bindMessage$2$1$1, reason: invalid class name and collision with other inner class name */
        public final class C01961 implements FlowCollector {
            public final /* synthetic */ FooterView $footer;
            public final /* synthetic */ int $r8$classId;

            public /* synthetic */ C01961(FooterView footerView, int i) {
                this.$r8$classId = i;
                this.$footer = footerView;
            }

            @Override // kotlinx.coroutines.flow.FlowCollector
            public final Object emit(Object obj, Continuation continuation) {
                Object obj2;
                switch (this.$r8$classId) {
                    case 0:
                        boolean booleanValue = ((Boolean) obj).booleanValue();
                        FooterView footerView = this.$footer;
                        if (booleanValue) {
                            footerView.mSeenNotifsFooterTextView.setVisibility(0);
                        } else {
                            footerView.mSeenNotifsFooterTextView.setVisibility(8);
                        }
                        return Unit.INSTANCE;
                    case 1:
                        int intValue = ((Number) obj).intValue();
                        FooterView footerView2 = this.$footer;
                        if (footerView2.mClearAllButtonTextId != intValue) {
                            footerView2.mClearAllButtonTextId = intValue;
                            if (intValue != 0) {
                                footerView2.mClearAllButton.setText(footerView2.getContext().getString(footerView2.mClearAllButtonTextId));
                            }
                        }
                        return Unit.INSTANCE;
                    case 2:
                        int intValue2 = ((Number) obj).intValue();
                        FooterView footerView3 = this.$footer;
                        if (footerView3.mClearAllButtonDescriptionId != intValue2) {
                            footerView3.mClearAllButtonDescriptionId = intValue2;
                            if (intValue2 != 0) {
                                footerView3.mClearAllButton.setContentDescription(footerView3.getContext().getString(footerView3.mClearAllButtonDescriptionId));
                            }
                        }
                        return Unit.INSTANCE;
                    case 3:
                        int intValue3 = ((Number) obj).intValue();
                        FooterView footerView4 = this.$footer;
                        if (footerView4.mManageOrHistoryButtonTextId != intValue3) {
                            footerView4.mManageOrHistoryButtonTextId = intValue3;
                            if (intValue3 != 0) {
                                footerView4.mManageOrHistoryButton.setText(footerView4.getContext().getString(footerView4.mManageOrHistoryButtonTextId));
                            }
                        }
                        return Unit.INSTANCE;
                    case 4:
                        int intValue4 = ((Number) obj).intValue();
                        FooterView footerView5 = this.$footer;
                        if (footerView5.mManageOrHistoryButtonDescriptionId != intValue4) {
                            footerView5.mManageOrHistoryButtonDescriptionId = intValue4;
                            if (intValue4 != 0) {
                                footerView5.mManageOrHistoryButton.setContentDescription(footerView5.getContext().getString(footerView5.mManageOrHistoryButtonDescriptionId));
                            }
                        }
                        return Unit.INSTANCE;
                    default:
                        AnimatedValue animatedValue = (AnimatedValue) obj;
                        if (animatedValue instanceof AnimatedValue.Animating) {
                            obj2 = ((AnimatedValue.Animating) animatedValue).value;
                        } else {
                            if (!(animatedValue instanceof AnimatedValue.NotAnimating)) {
                                throw new NoWhenBranchMatchedException();
                            }
                            obj2 = ((AnimatedValue.NotAnimating) animatedValue).value;
                        }
                        this.$footer.mManageOrHistoryButton.setVisibility(((Boolean) obj2).booleanValue() ? 0 : 8);
                        return Unit.INSTANCE;
                }
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(FooterView footerView, FooterViewModel footerViewModel, Continuation continuation) {
            super(2, continuation);
            this.$viewModel = footerViewModel;
            this.$footer = footerView;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation create(Object obj, Continuation continuation) {
            return new AnonymousClass1(this.$footer, this.$viewModel, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            ((AnonymousClass1) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
            return CoroutineSingletons.COROUTINE_SUSPENDED;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
            int i = this.label;
            if (i != 0) {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                throw AndroidLegacyPlatformTextInputServiceAdapter$startInput$2$1$1$$ExternalSyntheticOutline0.m(obj);
            }
            ResultKt.throwOnFailure(obj);
            FooterMessageViewModel footerMessageViewModel = this.$viewModel.message;
            C01961 c01961 = new C01961(this.$footer, 0);
            this.label = 1;
            footerMessageViewModel.isVisible.collect(c01961, this);
            return coroutineSingletons;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FooterViewBinder$bindMessage$2(FooterView footerView, FooterViewModel footerViewModel, Continuation continuation) {
        super(2, continuation);
        this.$footer = footerView;
        this.$viewModel = footerViewModel;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        FooterViewBinder$bindMessage$2 footerViewBinder$bindMessage$2 = new FooterViewBinder$bindMessage$2(this.$footer, this.$viewModel, continuation);
        footerViewBinder$bindMessage$2.L$0 = obj;
        return footerViewBinder$bindMessage$2;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((FooterViewBinder$bindMessage$2) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        CoroutineScope coroutineScope = (CoroutineScope) this.L$0;
        FooterView footerView = this.$footer;
        FooterMessageViewModel footerMessageViewModel = this.$viewModel.message;
        if (footerView.mMessageStringId != R.string.unlock_to_see_notif_text) {
            footerView.mMessageStringId = R.string.unlock_to_see_notif_text;
            footerView.mSeenNotifsFooterTextView.setText(footerView.getContext().getString(footerView.mMessageStringId));
        }
        FooterView footerView2 = this.$footer;
        FooterMessageViewModel footerMessageViewModel2 = this.$viewModel.message;
        if (footerView2.mMessageIconId != R.drawable.ic_friction_lock_closed) {
            footerView2.mMessageIconId = R.drawable.ic_friction_lock_closed;
            footerView2.updateMessageIcon();
        }
        return BuildersKt.launch$default(coroutineScope, null, null, new AnonymousClass1(this.$footer, this.$viewModel, null), 3);
    }
}
