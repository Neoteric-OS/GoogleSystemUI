package com.android.systemui.screenshot.ui.binder;

import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.compose.foundation.text.input.internal.AndroidLegacyPlatformTextInputServiceAdapter$startInput$2$1$1$$ExternalSyntheticOutline0;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LifecycleOwnerKt;
import androidx.lifecycle.RepeatOnLifecycleKt;
import com.android.systemui.screenshot.ui.ScreenshotShelfView;
import com.android.systemui.screenshot.ui.viewmodel.AnimationState;
import com.android.systemui.screenshot.ui.viewmodel.PreviewAction;
import com.android.systemui.screenshot.ui.viewmodel.ScreenshotViewModel;
import com.android.systemui.util.ConvenienceExtensionsKt;
import com.android.wm.shell.R;
import java.util.List;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.sequences.SequenceBuilderIterator;
import kotlin.sequences.SequencesKt__SequenceBuilderKt;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.StateFlowImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
final class ScreenshotShelfViewBinder$bind$3 extends SuspendLambda implements Function3 {
    final /* synthetic */ LinearLayout $actionsContainer;
    final /* synthetic */ ImageView $badgeView;
    final /* synthetic */ LayoutInflater $layoutInflater;
    final /* synthetic */ View $previewBorder;
    final /* synthetic */ ImageView $previewView;
    final /* synthetic */ ImageView $previewViewBlur;
    final /* synthetic */ ImageView $scrollablePreview;
    final /* synthetic */ ImageView $scrollingScrim;
    final /* synthetic */ ScreenshotShelfView $view;
    final /* synthetic */ ScreenshotViewModel $viewModel;
    private /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ ScreenshotShelfViewBinder this$0;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.screenshot.ui.binder.ScreenshotShelfViewBinder$bind$3$1, reason: invalid class name */
    final class AnonymousClass1 extends SuspendLambda implements Function2 {
        final /* synthetic */ LifecycleOwner $$this$repeatWhenAttached;
        final /* synthetic */ LinearLayout $actionsContainer;
        final /* synthetic */ ImageView $badgeView;
        final /* synthetic */ LayoutInflater $layoutInflater;
        final /* synthetic */ View $previewBorder;
        final /* synthetic */ ImageView $previewView;
        final /* synthetic */ ImageView $previewViewBlur;
        final /* synthetic */ ImageView $scrollablePreview;
        final /* synthetic */ ImageView $scrollingScrim;
        final /* synthetic */ ScreenshotShelfView $view;
        final /* synthetic */ ScreenshotViewModel $viewModel;
        int label;
        final /* synthetic */ ScreenshotShelfViewBinder this$0;

        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        /* renamed from: com.android.systemui.screenshot.ui.binder.ScreenshotShelfViewBinder$bind$3$1$1, reason: invalid class name and collision with other inner class name */
        final class C01751 extends SuspendLambda implements Function2 {
            final /* synthetic */ LinearLayout $actionsContainer;
            final /* synthetic */ ImageView $badgeView;
            final /* synthetic */ LayoutInflater $layoutInflater;
            final /* synthetic */ View $previewBorder;
            final /* synthetic */ ImageView $previewView;
            final /* synthetic */ ImageView $previewViewBlur;
            final /* synthetic */ ImageView $scrollablePreview;
            final /* synthetic */ ImageView $scrollingScrim;
            final /* synthetic */ ScreenshotShelfView $view;
            final /* synthetic */ ScreenshotViewModel $viewModel;
            private /* synthetic */ Object L$0;
            int label;
            final /* synthetic */ ScreenshotShelfViewBinder this$0;

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.systemui.screenshot.ui.binder.ScreenshotShelfViewBinder$bind$3$1$1$1, reason: invalid class name and collision with other inner class name */
            final class C01761 extends SuspendLambda implements Function2 {
                final /* synthetic */ View $previewBorder;
                final /* synthetic */ ImageView $previewView;
                final /* synthetic */ ImageView $previewViewBlur;
                final /* synthetic */ ScreenshotViewModel $viewModel;
                int label;
                final /* synthetic */ ScreenshotShelfViewBinder this$0;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public C01761(ScreenshotViewModel screenshotViewModel, ScreenshotShelfViewBinder screenshotShelfViewBinder, ImageView imageView, ImageView imageView2, View view, Continuation continuation) {
                    super(2, continuation);
                    this.$viewModel = screenshotViewModel;
                    this.this$0 = screenshotShelfViewBinder;
                    this.$previewView = imageView;
                    this.$previewViewBlur = imageView2;
                    this.$previewBorder = view;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Continuation create(Object obj, Continuation continuation) {
                    return new C01761(this.$viewModel, this.this$0, this.$previewView, this.$previewViewBlur, this.$previewBorder, continuation);
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    ((C01761) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
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
                    StateFlowImpl stateFlowImpl = this.$viewModel.preview;
                    AnonymousClass7.C01801 c01801 = new AnonymousClass7.C01801(this.this$0, this.$previewView, this.$previewViewBlur, this.$previewBorder);
                    this.label = 1;
                    stateFlowImpl.collect(c01801, this);
                    return coroutineSingletons;
                }
            }

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.systemui.screenshot.ui.binder.ScreenshotShelfViewBinder$bind$3$1$1$2, reason: invalid class name */
            final class AnonymousClass2 extends SuspendLambda implements Function2 {
                final /* synthetic */ ImageView $scrollingScrim;
                final /* synthetic */ ScreenshotViewModel $viewModel;
                int label;

                /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                /* renamed from: com.android.systemui.screenshot.ui.binder.ScreenshotShelfViewBinder$bind$3$1$1$2$1, reason: invalid class name and collision with other inner class name */
                public final class C01771 implements FlowCollector {
                    public final /* synthetic */ int $r8$classId;
                    public final /* synthetic */ ImageView $scrollingScrim;

                    public /* synthetic */ C01771(ImageView imageView, int i) {
                        this.$r8$classId = i;
                        this.$scrollingScrim = imageView;
                    }

                    @Override // kotlinx.coroutines.flow.FlowCollector
                    public final Object emit(Object obj, Continuation continuation) {
                        switch (this.$r8$classId) {
                            case 0:
                                Bitmap bitmap = (Bitmap) obj;
                                if (bitmap != null) {
                                    this.$scrollingScrim.setImageBitmap(bitmap);
                                    this.$scrollingScrim.setVisibility(0);
                                } else {
                                    this.$scrollingScrim.setVisibility(8);
                                }
                                break;
                            case 1:
                                Drawable drawable = (Drawable) obj;
                                this.$scrollingScrim.setImageDrawable(drawable);
                                this.$scrollingScrim.setVisibility(drawable != null ? 0 : 8);
                                break;
                            default:
                                PreviewAction previewAction = (PreviewAction) obj;
                                this.$scrollingScrim.setOnClickListener(new ScreenshotShelfViewBinder$bind$2(1, previewAction));
                                this.$scrollingScrim.setContentDescription(previewAction != null ? previewAction.contentDescription : null);
                                break;
                        }
                        return Unit.INSTANCE;
                    }
                }

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public AnonymousClass2(ScreenshotViewModel screenshotViewModel, ImageView imageView, Continuation continuation) {
                    super(2, continuation);
                    this.$viewModel = screenshotViewModel;
                    this.$scrollingScrim = imageView;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Continuation create(Object obj, Continuation continuation) {
                    return new AnonymousClass2(this.$viewModel, this.$scrollingScrim, continuation);
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    ((AnonymousClass2) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
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
                    StateFlowImpl stateFlowImpl = this.$viewModel.scrollingScrim;
                    C01771 c01771 = new C01771(this.$scrollingScrim, 0);
                    this.label = 1;
                    stateFlowImpl.collect(c01771, this);
                    return coroutineSingletons;
                }
            }

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.systemui.screenshot.ui.binder.ScreenshotShelfViewBinder$bind$3$1$1$3, reason: invalid class name */
            final class AnonymousClass3 extends SuspendLambda implements Function2 {
                final /* synthetic */ ImageView $scrollablePreview;
                final /* synthetic */ ScreenshotViewModel $viewModel;
                int label;
                final /* synthetic */ ScreenshotShelfViewBinder this$0;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public AnonymousClass3(ScreenshotViewModel screenshotViewModel, ScreenshotShelfViewBinder screenshotShelfViewBinder, ImageView imageView, Continuation continuation) {
                    super(2, continuation);
                    this.$viewModel = screenshotViewModel;
                    this.this$0 = screenshotShelfViewBinder;
                    this.$scrollablePreview = imageView;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Continuation create(Object obj, Continuation continuation) {
                    return new AnonymousClass3(this.$viewModel, this.this$0, this.$scrollablePreview, continuation);
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    ((AnonymousClass3) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
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
                    final ScreenshotViewModel screenshotViewModel = this.$viewModel;
                    StateFlowImpl stateFlowImpl = screenshotViewModel.scrollableRect;
                    final ScreenshotShelfViewBinder screenshotShelfViewBinder = this.this$0;
                    final ImageView imageView = this.$scrollablePreview;
                    FlowCollector flowCollector = new FlowCollector() { // from class: com.android.systemui.screenshot.ui.binder.ScreenshotShelfViewBinder.bind.3.1.1.3.1
                        @Override // kotlinx.coroutines.flow.FlowCollector
                        public final Object emit(Object obj2, Continuation continuation) {
                            if (((Rect) obj2) != null) {
                                ImageView imageView2 = imageView;
                                Bitmap bitmap = (Bitmap) screenshotViewModel.preview.getValue();
                                ScreenshotShelfViewBinder.this.getClass();
                                if (bitmap != null) {
                                    float dimensionPixelSize = imageView2.getResources().getDimensionPixelSize(R.dimen.overlay_x_scale) / (imageView2.getResources().getConfiguration().orientation == 1 ? bitmap.getWidth() : bitmap.getHeight());
                                    ViewGroup.LayoutParams layoutParams = imageView2.getLayoutParams();
                                    layoutParams.width = (int) (r6.width() * dimensionPixelSize);
                                    layoutParams.height = (int) (r6.height() * dimensionPixelSize);
                                    Matrix matrix = new Matrix();
                                    matrix.setScale(dimensionPixelSize, dimensionPixelSize);
                                    matrix.postTranslate((-r6.left) * dimensionPixelSize, (-r6.top) * dimensionPixelSize);
                                    imageView2.setTranslationX((imageView2.getLayoutDirection() == 0 ? r6.left : r6.right - ((View) imageView2.getParent()).getWidth()) * dimensionPixelSize);
                                    imageView2.setTranslationY(dimensionPixelSize * r6.top);
                                    imageView2.setImageMatrix(matrix);
                                    imageView2.setImageBitmap(bitmap);
                                    imageView2.setVisibility(0);
                                }
                            } else {
                                imageView.setVisibility(8);
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
            /* renamed from: com.android.systemui.screenshot.ui.binder.ScreenshotShelfViewBinder$bind$3$1$1$4, reason: invalid class name */
            final class AnonymousClass4 extends SuspendLambda implements Function2 {
                final /* synthetic */ ImageView $badgeView;
                final /* synthetic */ ScreenshotViewModel $viewModel;
                int label;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public AnonymousClass4(ScreenshotViewModel screenshotViewModel, ImageView imageView, Continuation continuation) {
                    super(2, continuation);
                    this.$viewModel = screenshotViewModel;
                    this.$badgeView = imageView;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Continuation create(Object obj, Continuation continuation) {
                    return new AnonymousClass4(this.$viewModel, this.$badgeView, continuation);
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    ((AnonymousClass4) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
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
                    StateFlowImpl stateFlowImpl = this.$viewModel.badge;
                    AnonymousClass2.C01771 c01771 = new AnonymousClass2.C01771(this.$badgeView, 1);
                    this.label = 1;
                    stateFlowImpl.collect(c01771, this);
                    return coroutineSingletons;
                }
            }

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.systemui.screenshot.ui.binder.ScreenshotShelfViewBinder$bind$3$1$1$5, reason: invalid class name */
            final class AnonymousClass5 extends SuspendLambda implements Function2 {
                final /* synthetic */ ImageView $previewView;
                final /* synthetic */ ScreenshotViewModel $viewModel;
                int label;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public AnonymousClass5(ScreenshotViewModel screenshotViewModel, ImageView imageView, Continuation continuation) {
                    super(2, continuation);
                    this.$viewModel = screenshotViewModel;
                    this.$previewView = imageView;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Continuation create(Object obj, Continuation continuation) {
                    return new AnonymousClass5(this.$viewModel, this.$previewView, continuation);
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    ((AnonymousClass5) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
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
                    StateFlowImpl stateFlowImpl = this.$viewModel.previewAction;
                    AnonymousClass2.C01771 c01771 = new AnonymousClass2.C01771(this.$previewView, 2);
                    this.label = 1;
                    stateFlowImpl.collect(c01771, this);
                    return coroutineSingletons;
                }
            }

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.systemui.screenshot.ui.binder.ScreenshotShelfViewBinder$bind$3$1$1$6, reason: invalid class name */
            final class AnonymousClass6 extends SuspendLambda implements Function2 {
                final /* synthetic */ LinearLayout $actionsContainer;
                final /* synthetic */ ImageView $previewView;
                final /* synthetic */ ScreenshotViewModel $viewModel;
                int label;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public AnonymousClass6(ScreenshotViewModel screenshotViewModel, ImageView imageView, LinearLayout linearLayout, Continuation continuation) {
                    super(2, continuation);
                    this.$viewModel = screenshotViewModel;
                    this.$previewView = imageView;
                    this.$actionsContainer = linearLayout;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Continuation create(Object obj, Continuation continuation) {
                    return new AnonymousClass6(this.$viewModel, this.$previewView, this.$actionsContainer, continuation);
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    ((AnonymousClass6) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
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
                    StateFlowImpl stateFlowImpl = this.$viewModel.isAnimating;
                    final ImageView imageView = this.$previewView;
                    final LinearLayout linearLayout = this.$actionsContainer;
                    FlowCollector flowCollector = new FlowCollector() { // from class: com.android.systemui.screenshot.ui.binder.ScreenshotShelfViewBinder.bind.3.1.1.6.1
                        /* JADX WARN: Type inference failed for: r0v3, types: [kotlin.coroutines.jvm.internal.RestrictedSuspendLambda, kotlin.jvm.functions.Function2] */
                        @Override // kotlinx.coroutines.flow.FlowCollector
                        public final Object emit(Object obj2, Continuation continuation) {
                            boolean z = !((Boolean) obj2).booleanValue();
                            imageView.setClickable(z);
                            SequenceBuilderIterator it = SequencesKt__SequenceBuilderKt.iterator(ConvenienceExtensionsKt.getChildren(linearLayout).$block$inlined);
                            while (it.hasNext()) {
                                ((View) it.next()).setClickable(z);
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
            /* renamed from: com.android.systemui.screenshot.ui.binder.ScreenshotShelfViewBinder$bind$3$1$1$7, reason: invalid class name */
            final class AnonymousClass7 extends SuspendLambda implements Function2 {
                final /* synthetic */ LayoutInflater $layoutInflater;
                final /* synthetic */ ScreenshotShelfView $view;
                final /* synthetic */ ScreenshotViewModel $viewModel;
                int label;
                final /* synthetic */ ScreenshotShelfViewBinder this$0;

                /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                /* renamed from: com.android.systemui.screenshot.ui.binder.ScreenshotShelfViewBinder$bind$3$1$1$7$1, reason: invalid class name and collision with other inner class name */
                public final class C01801 implements FlowCollector {
                    public final /* synthetic */ Object $layoutInflater;
                    public final /* synthetic */ int $r8$classId;
                    public final /* synthetic */ Object $view;
                    public final /* synthetic */ Object $viewModel;
                    public final /* synthetic */ ScreenshotShelfViewBinder this$0;

                    public C01801(ScreenshotShelfViewBinder screenshotShelfViewBinder, ImageView imageView, ImageView imageView2, View view) {
                        this.$r8$classId = 2;
                        this.this$0 = screenshotShelfViewBinder;
                        this.$viewModel = imageView;
                        this.$view = imageView2;
                        this.$layoutInflater = view;
                    }

                    @Override // kotlinx.coroutines.flow.FlowCollector
                    public final Object emit(Object obj, Continuation continuation) {
                        switch (this.$r8$classId) {
                            case 0:
                                AnimationState animationState = (AnimationState) ((ScreenshotViewModel) this.$viewModel).animationState.getValue();
                                LayoutInflater layoutInflater = (LayoutInflater) this.$layoutInflater;
                                ScreenshotShelfViewBinder.access$updateActions(this.this$0, (List) obj, animationState, (ScreenshotShelfView) this.$view, layoutInflater);
                                break;
                            case 1:
                                List list = (List) ((ScreenshotViewModel) this.$viewModel).actions.getValue();
                                LayoutInflater layoutInflater2 = (LayoutInflater) this.$layoutInflater;
                                ScreenshotShelfViewBinder.access$updateActions(this.this$0, list, (AnimationState) obj, (ScreenshotShelfView) this.$view, layoutInflater2);
                                break;
                            default:
                                Bitmap bitmap = (Bitmap) obj;
                                if (bitmap != null) {
                                    ImageView imageView = (ImageView) this.$viewModel;
                                    ScreenshotShelfViewBinder screenshotShelfViewBinder = this.this$0;
                                    ScreenshotShelfViewBinder.access$setScreenshotBitmap(screenshotShelfViewBinder, imageView, bitmap);
                                    ScreenshotShelfViewBinder.access$setScreenshotBitmap(screenshotShelfViewBinder, (ImageView) this.$view, bitmap);
                                    ((ImageView) this.$viewModel).setVisibility(0);
                                    ((View) this.$layoutInflater).setVisibility(0);
                                } else {
                                    ((ImageView) this.$viewModel).setVisibility(8);
                                    ((View) this.$layoutInflater).setVisibility(8);
                                }
                                break;
                        }
                        return Unit.INSTANCE;
                    }

                    public /* synthetic */ C01801(ScreenshotShelfViewBinder screenshotShelfViewBinder, ScreenshotViewModel screenshotViewModel, ScreenshotShelfView screenshotShelfView, LayoutInflater layoutInflater, int i) {
                        this.$r8$classId = i;
                        this.this$0 = screenshotShelfViewBinder;
                        this.$viewModel = screenshotViewModel;
                        this.$view = screenshotShelfView;
                        this.$layoutInflater = layoutInflater;
                    }
                }

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public AnonymousClass7(ScreenshotViewModel screenshotViewModel, ScreenshotShelfViewBinder screenshotShelfViewBinder, ScreenshotShelfView screenshotShelfView, LayoutInflater layoutInflater, Continuation continuation) {
                    super(2, continuation);
                    this.$viewModel = screenshotViewModel;
                    this.this$0 = screenshotShelfViewBinder;
                    this.$view = screenshotShelfView;
                    this.$layoutInflater = layoutInflater;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Continuation create(Object obj, Continuation continuation) {
                    return new AnonymousClass7(this.$viewModel, this.this$0, this.$view, this.$layoutInflater, continuation);
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    ((AnonymousClass7) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
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
                    ScreenshotViewModel screenshotViewModel = this.$viewModel;
                    StateFlowImpl stateFlowImpl = screenshotViewModel.actions;
                    C01801 c01801 = new C01801(this.this$0, screenshotViewModel, this.$view, this.$layoutInflater, 0);
                    this.label = 1;
                    stateFlowImpl.collect(c01801, this);
                    return coroutineSingletons;
                }
            }

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.systemui.screenshot.ui.binder.ScreenshotShelfViewBinder$bind$3$1$1$8, reason: invalid class name */
            final class AnonymousClass8 extends SuspendLambda implements Function2 {
                final /* synthetic */ LayoutInflater $layoutInflater;
                final /* synthetic */ ScreenshotShelfView $view;
                final /* synthetic */ ScreenshotViewModel $viewModel;
                int label;
                final /* synthetic */ ScreenshotShelfViewBinder this$0;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public AnonymousClass8(ScreenshotViewModel screenshotViewModel, ScreenshotShelfViewBinder screenshotShelfViewBinder, ScreenshotShelfView screenshotShelfView, LayoutInflater layoutInflater, Continuation continuation) {
                    super(2, continuation);
                    this.$viewModel = screenshotViewModel;
                    this.this$0 = screenshotShelfViewBinder;
                    this.$view = screenshotShelfView;
                    this.$layoutInflater = layoutInflater;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Continuation create(Object obj, Continuation continuation) {
                    return new AnonymousClass8(this.$viewModel, this.this$0, this.$view, this.$layoutInflater, continuation);
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    ((AnonymousClass8) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
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
                    ScreenshotViewModel screenshotViewModel = this.$viewModel;
                    StateFlowImpl stateFlowImpl = screenshotViewModel.animationState;
                    AnonymousClass7.C01801 c01801 = new AnonymousClass7.C01801(this.this$0, screenshotViewModel, this.$view, this.$layoutInflater, 1);
                    this.label = 1;
                    stateFlowImpl.collect(c01801, this);
                    return coroutineSingletons;
                }
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public C01751(ScreenshotViewModel screenshotViewModel, ScreenshotShelfViewBinder screenshotShelfViewBinder, ImageView imageView, ImageView imageView2, View view, ImageView imageView3, ImageView imageView4, ImageView imageView5, LinearLayout linearLayout, ScreenshotShelfView screenshotShelfView, LayoutInflater layoutInflater, Continuation continuation) {
                super(2, continuation);
                this.$viewModel = screenshotViewModel;
                this.this$0 = screenshotShelfViewBinder;
                this.$previewView = imageView;
                this.$previewViewBlur = imageView2;
                this.$previewBorder = view;
                this.$scrollingScrim = imageView3;
                this.$scrollablePreview = imageView4;
                this.$badgeView = imageView5;
                this.$actionsContainer = linearLayout;
                this.$view = screenshotShelfView;
                this.$layoutInflater = layoutInflater;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation create(Object obj, Continuation continuation) {
                C01751 c01751 = new C01751(this.$viewModel, this.this$0, this.$previewView, this.$previewViewBlur, this.$previewBorder, this.$scrollingScrim, this.$scrollablePreview, this.$badgeView, this.$actionsContainer, this.$view, this.$layoutInflater, continuation);
                c01751.L$0 = obj;
                return c01751;
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                C01751 c01751 = (C01751) create((CoroutineScope) obj, (Continuation) obj2);
                Unit unit = Unit.INSTANCE;
                c01751.invokeSuspend(unit);
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
                BuildersKt.launch$default(coroutineScope, null, null, new C01761(this.$viewModel, this.this$0, this.$previewView, this.$previewViewBlur, this.$previewBorder, null), 3);
                BuildersKt.launch$default(coroutineScope, null, null, new AnonymousClass2(this.$viewModel, this.$scrollingScrim, null), 3);
                BuildersKt.launch$default(coroutineScope, null, null, new AnonymousClass3(this.$viewModel, this.this$0, this.$scrollablePreview, null), 3);
                BuildersKt.launch$default(coroutineScope, null, null, new AnonymousClass4(this.$viewModel, this.$badgeView, null), 3);
                BuildersKt.launch$default(coroutineScope, null, null, new AnonymousClass5(this.$viewModel, this.$previewView, null), 3);
                BuildersKt.launch$default(coroutineScope, null, null, new AnonymousClass6(this.$viewModel, this.$previewView, this.$actionsContainer, null), 3);
                BuildersKt.launch$default(coroutineScope, null, null, new AnonymousClass7(this.$viewModel, this.this$0, this.$view, this.$layoutInflater, null), 3);
                BuildersKt.launch$default(coroutineScope, null, null, new AnonymousClass8(this.$viewModel, this.this$0, this.$view, this.$layoutInflater, null), 3);
                return Unit.INSTANCE;
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(LifecycleOwner lifecycleOwner, ScreenshotViewModel screenshotViewModel, ScreenshotShelfViewBinder screenshotShelfViewBinder, ImageView imageView, ImageView imageView2, View view, ImageView imageView3, ImageView imageView4, ImageView imageView5, LinearLayout linearLayout, ScreenshotShelfView screenshotShelfView, LayoutInflater layoutInflater, Continuation continuation) {
            super(2, continuation);
            this.$$this$repeatWhenAttached = lifecycleOwner;
            this.$viewModel = screenshotViewModel;
            this.this$0 = screenshotShelfViewBinder;
            this.$previewView = imageView;
            this.$previewViewBlur = imageView2;
            this.$previewBorder = view;
            this.$scrollingScrim = imageView3;
            this.$scrollablePreview = imageView4;
            this.$badgeView = imageView5;
            this.$actionsContainer = linearLayout;
            this.$view = screenshotShelfView;
            this.$layoutInflater = layoutInflater;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation create(Object obj, Continuation continuation) {
            return new AnonymousClass1(this.$$this$repeatWhenAttached, this.$viewModel, this.this$0, this.$previewView, this.$previewViewBlur, this.$previewBorder, this.$scrollingScrim, this.$scrollablePreview, this.$badgeView, this.$actionsContainer, this.$view, this.$layoutInflater, continuation);
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
                Lifecycle.State state = Lifecycle.State.STARTED;
                C01751 c01751 = new C01751(this.$viewModel, this.this$0, this.$previewView, this.$previewViewBlur, this.$previewBorder, this.$scrollingScrim, this.$scrollablePreview, this.$badgeView, this.$actionsContainer, this.$view, this.$layoutInflater, null);
                this.label = 1;
                if (RepeatOnLifecycleKt.repeatOnLifecycle(lifecycleOwner, state, c01751, this) == coroutineSingletons) {
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
    public ScreenshotShelfViewBinder$bind$3(ScreenshotViewModel screenshotViewModel, ScreenshotShelfViewBinder screenshotShelfViewBinder, ImageView imageView, ImageView imageView2, View view, ImageView imageView3, ImageView imageView4, ImageView imageView5, LinearLayout linearLayout, ScreenshotShelfView screenshotShelfView, LayoutInflater layoutInflater, Continuation continuation) {
        super(3, continuation);
        this.$viewModel = screenshotViewModel;
        this.this$0 = screenshotShelfViewBinder;
        this.$previewView = imageView;
        this.$previewViewBlur = imageView2;
        this.$previewBorder = view;
        this.$scrollingScrim = imageView3;
        this.$scrollablePreview = imageView4;
        this.$badgeView = imageView5;
        this.$actionsContainer = linearLayout;
        this.$view = screenshotShelfView;
        this.$layoutInflater = layoutInflater;
    }

    @Override // kotlin.jvm.functions.Function3
    public final Object invoke(Object obj, Object obj2, Object obj3) {
        ScreenshotShelfViewBinder$bind$3 screenshotShelfViewBinder$bind$3 = new ScreenshotShelfViewBinder$bind$3(this.$viewModel, this.this$0, this.$previewView, this.$previewViewBlur, this.$previewBorder, this.$scrollingScrim, this.$scrollablePreview, this.$badgeView, this.$actionsContainer, this.$view, this.$layoutInflater, (Continuation) obj3);
        screenshotShelfViewBinder$bind$3.L$0 = (LifecycleOwner) obj;
        Unit unit = Unit.INSTANCE;
        screenshotShelfViewBinder$bind$3.invokeSuspend(unit);
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
        BuildersKt.launch$default(LifecycleOwnerKt.getLifecycleScope(lifecycleOwner), null, null, new AnonymousClass1(lifecycleOwner, this.$viewModel, this.this$0, this.$previewView, this.$previewViewBlur, this.$previewBorder, this.$scrollingScrim, this.$scrollablePreview, this.$badgeView, this.$actionsContainer, this.$view, this.$layoutInflater, null), 3);
        return Unit.INSTANCE;
    }
}
