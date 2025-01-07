package com.android.systemui.keyguard.ui.binder;

import android.content.res.ColorStateList;
import android.util.StateSet;
import android.widget.ImageView;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.ColorKt;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.RepeatOnLifecycleKt;
import com.android.app.tracing.coroutines.CoroutineTracingKt;
import com.android.systemui.keyguard.ui.view.DeviceEntryIconView;
import com.android.systemui.keyguard.ui.viewmodel.DeviceEntryForegroundViewModel;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt__ZipKt$combine$$inlined$combineUnsafe$FlowKt__ZipKt$1;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class DeviceEntryIconViewBinder$bind$3 extends SuspendLambda implements Function3 {
    final /* synthetic */ ImageView $fgIconView;
    final /* synthetic */ DeviceEntryForegroundViewModel $fgViewModel;
    final /* synthetic */ Color $overrideColor;
    final /* synthetic */ DeviceEntryIconView $view;
    private /* synthetic */ Object L$0;
    int label;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.keyguard.ui.binder.DeviceEntryIconViewBinder$bind$3$1, reason: invalid class name */
    final class AnonymousClass1 extends SuspendLambda implements Function2 {
        final /* synthetic */ ImageView $fgIconView;
        final /* synthetic */ DeviceEntryForegroundViewModel $fgViewModel;
        final /* synthetic */ Color $overrideColor;
        final /* synthetic */ DeviceEntryIconView $view;
        private /* synthetic */ Object L$0;
        int label;

        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        /* renamed from: com.android.systemui.keyguard.ui.binder.DeviceEntryIconViewBinder$bind$3$1$1, reason: invalid class name and collision with other inner class name */
        final class C01051 extends SuspendLambda implements Function2 {
            final /* synthetic */ ImageView $fgIconView;
            final /* synthetic */ DeviceEntryForegroundViewModel $fgViewModel;
            final /* synthetic */ Color $overrideColor;
            final /* synthetic */ DeviceEntryIconView $view;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public C01051(ImageView imageView, Color color, DeviceEntryIconView deviceEntryIconView, DeviceEntryForegroundViewModel deviceEntryForegroundViewModel, Continuation continuation) {
                super(2, continuation);
                this.$fgViewModel = deviceEntryForegroundViewModel;
                this.$fgIconView = imageView;
                this.$view = deviceEntryIconView;
                this.$overrideColor = color;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation create(Object obj, Continuation continuation) {
                DeviceEntryForegroundViewModel deviceEntryForegroundViewModel = this.$fgViewModel;
                return new C01051(this.$fgIconView, this.$overrideColor, this.$view, deviceEntryForegroundViewModel, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return ((C01051) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    FlowKt__ZipKt$combine$$inlined$combineUnsafe$FlowKt__ZipKt$1 flowKt__ZipKt$combine$$inlined$combineUnsafe$FlowKt__ZipKt$1 = this.$fgViewModel.viewModel;
                    final ImageView imageView = this.$fgIconView;
                    final DeviceEntryIconView deviceEntryIconView = this.$view;
                    final Color color = this.$overrideColor;
                    FlowCollector flowCollector = new FlowCollector() { // from class: com.android.systemui.keyguard.ui.binder.DeviceEntryIconViewBinder.bind.3.1.1.1
                        @Override // kotlinx.coroutines.flow.FlowCollector
                        public final Object emit(Object obj2, Continuation continuation) {
                            DeviceEntryForegroundViewModel.ForegroundIconViewModel foregroundIconViewModel = (DeviceEntryForegroundViewModel.ForegroundIconViewModel) obj2;
                            ImageView imageView2 = imageView;
                            DeviceEntryIconView.IconType iconType = foregroundIconViewModel.type;
                            deviceEntryIconView.getClass();
                            imageView2.setImageState(DeviceEntryIconView.getIconState(iconType, foregroundIconViewModel.useAodVariant), false);
                            DeviceEntryIconView.IconType iconType2 = foregroundIconViewModel.type;
                            if (iconType2.getContentDescriptionResId() != -1) {
                                ImageView imageView3 = imageView;
                                imageView3.setContentDescription(imageView3.getResources().getString(iconType2.getContentDescriptionResId()));
                            }
                            ImageView imageView4 = imageView;
                            Color color2 = color;
                            imageView4.setImageTintList(ColorStateList.valueOf(color2 != null ? ColorKt.m373toArgb8_81llA(color2.value) : foregroundIconViewModel.tint));
                            ImageView imageView5 = imageView;
                            int i2 = foregroundIconViewModel.padding;
                            imageView5.setPadding(i2, i2, i2, i2);
                            return Unit.INSTANCE;
                        }
                    };
                    this.label = 1;
                    if (flowKt__ZipKt$combine$$inlined$combineUnsafe$FlowKt__ZipKt$1.collect(flowCollector, this) == coroutineSingletons) {
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
        public AnonymousClass1(ImageView imageView, Color color, DeviceEntryIconView deviceEntryIconView, DeviceEntryForegroundViewModel deviceEntryForegroundViewModel, Continuation continuation) {
            super(2, continuation);
            this.$fgIconView = imageView;
            this.$fgViewModel = deviceEntryForegroundViewModel;
            this.$view = deviceEntryIconView;
            this.$overrideColor = color;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation create(Object obj, Continuation continuation) {
            ImageView imageView = this.$fgIconView;
            DeviceEntryForegroundViewModel deviceEntryForegroundViewModel = this.$fgViewModel;
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(imageView, this.$overrideColor, this.$view, deviceEntryForegroundViewModel, continuation);
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
            this.$fgIconView.setImageState(StateSet.NOTHING, false);
            DeviceEntryForegroundViewModel deviceEntryForegroundViewModel = this.$fgViewModel;
            CoroutineTracingKt.launch$default(coroutineScope, null, new C01051(this.$fgIconView, this.$overrideColor, this.$view, deviceEntryForegroundViewModel, null), 6);
            return Unit.INSTANCE;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DeviceEntryIconViewBinder$bind$3(ImageView imageView, Color color, DeviceEntryIconView deviceEntryIconView, DeviceEntryForegroundViewModel deviceEntryForegroundViewModel, Continuation continuation) {
        super(3, continuation);
        this.$fgIconView = imageView;
        this.$fgViewModel = deviceEntryForegroundViewModel;
        this.$view = deviceEntryIconView;
        this.$overrideColor = color;
    }

    @Override // kotlin.jvm.functions.Function3
    public final Object invoke(Object obj, Object obj2, Object obj3) {
        ImageView imageView = this.$fgIconView;
        DeviceEntryForegroundViewModel deviceEntryForegroundViewModel = this.$fgViewModel;
        DeviceEntryIconView deviceEntryIconView = this.$view;
        DeviceEntryIconViewBinder$bind$3 deviceEntryIconViewBinder$bind$3 = new DeviceEntryIconViewBinder$bind$3(imageView, this.$overrideColor, deviceEntryIconView, deviceEntryForegroundViewModel, (Continuation) obj3);
        deviceEntryIconViewBinder$bind$3.L$0 = (LifecycleOwner) obj;
        return deviceEntryIconViewBinder$bind$3.invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            LifecycleOwner lifecycleOwner = (LifecycleOwner) this.L$0;
            Lifecycle.State state = Lifecycle.State.STARTED;
            ImageView imageView = this.$fgIconView;
            DeviceEntryForegroundViewModel deviceEntryForegroundViewModel = this.$fgViewModel;
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(imageView, this.$overrideColor, this.$view, deviceEntryForegroundViewModel, null);
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
