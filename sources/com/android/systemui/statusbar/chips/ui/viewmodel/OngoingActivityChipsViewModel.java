package com.android.systemui.statusbar.chips.ui.viewmodel;

import androidx.compose.animation.TransitionData$$ExternalSyntheticOutline0;
import com.android.systemui.log.LogBuffer;
import com.android.systemui.statusbar.chips.call.ui.viewmodel.CallChipViewModel;
import com.android.systemui.statusbar.chips.casttootherdevice.ui.viewmodel.CastToOtherDeviceChipViewModel;
import com.android.systemui.statusbar.chips.ron.demo.ui.viewmodel.DemoRonChipViewModel;
import com.android.systemui.statusbar.chips.screenrecord.ui.viewmodel.ScreenRecordChipViewModel;
import com.android.systemui.statusbar.chips.sharetoapp.ui.viewmodel.ShareToAppChipViewModel;
import com.android.systemui.statusbar.chips.ui.model.MultipleOngoingActivityChipsModel;
import com.android.systemui.statusbar.chips.ui.model.OngoingActivityChipModel;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.FlowKt__ZipKt$combine$$inlined$combineUnsafe$FlowKt__ZipKt$1;
import kotlinx.coroutines.flow.ReadonlyStateFlow;
import kotlinx.coroutines.flow.SafeFlow;
import kotlinx.coroutines.flow.SharingStarted;
import kotlinx.coroutines.flow.StartedLazily;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class OngoingActivityChipsViewModel {
    public static final InternalChipModel.Hidden DEFAULT_INTERNAL_HIDDEN_MODEL = new InternalChipModel.Hidden(new OngoingActivityChipModel.Hidden(true), new OngoingActivityChipModel.Hidden(true), new OngoingActivityChipModel.Hidden(true), new OngoingActivityChipModel.Hidden(true), new OngoingActivityChipModel.Hidden(true));
    public final ReadonlyStateFlow chips;
    public final ReadonlyStateFlow incomingChipBundle;
    public final OngoingActivityChipsViewModel$special$$inlined$map$1 internalChip;
    public final OngoingActivityChipsViewModel$special$$inlined$map$1 internalChips;
    public final LogBuffer logger;
    public final ReadonlyStateFlow primaryChip;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class ChipBundle {
        public final OngoingActivityChipModel call;
        public final OngoingActivityChipModel castToOtherDevice;
        public final OngoingActivityChipModel demoRon;
        public final OngoingActivityChipModel screenRecord;
        public final OngoingActivityChipModel shareToApp;

        public ChipBundle(OngoingActivityChipModel ongoingActivityChipModel, OngoingActivityChipModel ongoingActivityChipModel2, OngoingActivityChipModel ongoingActivityChipModel3, OngoingActivityChipModel ongoingActivityChipModel4, OngoingActivityChipModel ongoingActivityChipModel5) {
            this.screenRecord = ongoingActivityChipModel;
            this.shareToApp = ongoingActivityChipModel2;
            this.castToOtherDevice = ongoingActivityChipModel3;
            this.call = ongoingActivityChipModel4;
            this.demoRon = ongoingActivityChipModel5;
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r10v2, types: [com.android.systemui.statusbar.chips.ui.model.OngoingActivityChipModel] */
        /* JADX WARN: Type inference failed for: r7v5, types: [com.android.systemui.statusbar.chips.ui.model.OngoingActivityChipModel] */
        /* JADX WARN: Type inference failed for: r8v2, types: [com.android.systemui.statusbar.chips.ui.model.OngoingActivityChipModel] */
        /* JADX WARN: Type inference failed for: r9v2, types: [com.android.systemui.statusbar.chips.ui.model.OngoingActivityChipModel] */
        public static ChipBundle copy$default(ChipBundle chipBundle, OngoingActivityChipModel.Hidden hidden, OngoingActivityChipModel.Hidden hidden2, OngoingActivityChipModel.Hidden hidden3, OngoingActivityChipModel.Hidden hidden4, int i) {
            OngoingActivityChipModel.Hidden hidden5 = hidden;
            if ((i & 1) != 0) {
                hidden5 = chipBundle.screenRecord;
            }
            OngoingActivityChipModel.Hidden hidden6 = hidden5;
            OngoingActivityChipModel.Hidden hidden7 = hidden2;
            if ((i & 2) != 0) {
                hidden7 = chipBundle.shareToApp;
            }
            OngoingActivityChipModel.Hidden hidden8 = hidden7;
            OngoingActivityChipModel.Hidden hidden9 = hidden3;
            if ((i & 4) != 0) {
                hidden9 = chipBundle.castToOtherDevice;
            }
            OngoingActivityChipModel.Hidden hidden10 = hidden9;
            OngoingActivityChipModel.Hidden hidden11 = hidden4;
            if ((i & 8) != 0) {
                hidden11 = chipBundle.call;
            }
            OngoingActivityChipModel ongoingActivityChipModel = chipBundle.demoRon;
            chipBundle.getClass();
            return new ChipBundle(hidden6, hidden8, hidden10, hidden11, ongoingActivityChipModel);
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof ChipBundle)) {
                return false;
            }
            ChipBundle chipBundle = (ChipBundle) obj;
            return Intrinsics.areEqual(this.screenRecord, chipBundle.screenRecord) && Intrinsics.areEqual(this.shareToApp, chipBundle.shareToApp) && Intrinsics.areEqual(this.castToOtherDevice, chipBundle.castToOtherDevice) && Intrinsics.areEqual(this.call, chipBundle.call) && Intrinsics.areEqual(this.demoRon, chipBundle.demoRon);
        }

        public final int hashCode() {
            return this.demoRon.hashCode() + ((this.call.hashCode() + ((this.castToOtherDevice.hashCode() + ((this.shareToApp.hashCode() + (this.screenRecord.hashCode() * 31)) * 31)) * 31)) * 31);
        }

        public final String toString() {
            return "ChipBundle(screenRecord=" + this.screenRecord + ", shareToApp=" + this.shareToApp + ", castToOtherDevice=" + this.castToOtherDevice + ", call=" + this.call + ", demoRon=" + this.demoRon + ")";
        }
    }

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    final class ChipType {
        public static final /* synthetic */ ChipType[] $VALUES;
        public static final ChipType Call;
        public static final ChipType CastToOtherDevice;
        public static final ChipType ScreenRecord;
        public static final ChipType ShareToApp;

        static {
            ChipType chipType = new ChipType("ScreenRecord", 0);
            ScreenRecord = chipType;
            ChipType chipType2 = new ChipType("ShareToApp", 1);
            ShareToApp = chipType2;
            ChipType chipType3 = new ChipType("CastToOtherDevice", 2);
            CastToOtherDevice = chipType3;
            ChipType chipType4 = new ChipType("Call", 3);
            Call = chipType4;
            ChipType[] chipTypeArr = {chipType, chipType2, chipType3, chipType4, new ChipType("DemoRon", 4)};
            $VALUES = chipTypeArr;
            EnumEntriesKt.enumEntries(chipTypeArr);
        }

        public static ChipType valueOf(String str) {
            return (ChipType) Enum.valueOf(ChipType.class, str);
        }

        public static ChipType[] values() {
            return (ChipType[]) $VALUES.clone();
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public interface InternalChipModel {

        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        public final class Hidden implements InternalChipModel {
            public final OngoingActivityChipModel.Hidden call;
            public final OngoingActivityChipModel.Hidden castToOtherDevice;
            public final OngoingActivityChipModel.Hidden demoRon;
            public final OngoingActivityChipModel.Hidden screenRecord;
            public final OngoingActivityChipModel.Hidden shareToApp;

            public Hidden(OngoingActivityChipModel.Hidden hidden, OngoingActivityChipModel.Hidden hidden2, OngoingActivityChipModel.Hidden hidden3, OngoingActivityChipModel.Hidden hidden4, OngoingActivityChipModel.Hidden hidden5) {
                this.screenRecord = hidden;
                this.shareToApp = hidden2;
                this.castToOtherDevice = hidden3;
                this.call = hidden4;
                this.demoRon = hidden5;
            }

            public final boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                if (!(obj instanceof Hidden)) {
                    return false;
                }
                Hidden hidden = (Hidden) obj;
                return Intrinsics.areEqual(this.screenRecord, hidden.screenRecord) && Intrinsics.areEqual(this.shareToApp, hidden.shareToApp) && Intrinsics.areEqual(this.castToOtherDevice, hidden.castToOtherDevice) && Intrinsics.areEqual(this.call, hidden.call) && Intrinsics.areEqual(this.demoRon, hidden.demoRon);
            }

            public final int hashCode() {
                return Boolean.hashCode(this.demoRon.shouldAnimate) + TransitionData$$ExternalSyntheticOutline0.m(TransitionData$$ExternalSyntheticOutline0.m(TransitionData$$ExternalSyntheticOutline0.m(Boolean.hashCode(this.screenRecord.shouldAnimate) * 31, 31, this.shareToApp.shouldAnimate), 31, this.castToOtherDevice.shouldAnimate), 31, this.call.shouldAnimate);
            }

            public final String toString() {
                return "Hidden(screenRecord=" + this.screenRecord + ", shareToApp=" + this.shareToApp + ", castToOtherDevice=" + this.castToOtherDevice + ", call=" + this.call + ", demoRon=" + this.demoRon + ")";
            }
        }

        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        public final class Shown implements InternalChipModel {
            public final OngoingActivityChipModel.Shown model;
            public final ChipType type;

            public Shown(ChipType chipType, OngoingActivityChipModel.Shown shown) {
                this.type = chipType;
                this.model = shown;
            }

            public final boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                if (!(obj instanceof Shown)) {
                    return false;
                }
                Shown shown = (Shown) obj;
                return this.type == shown.type && Intrinsics.areEqual(this.model, shown.model);
            }

            public final int hashCode() {
                return this.model.hashCode() + (this.type.hashCode() * 31);
            }

            public final String toString() {
                return "Shown(type=" + this.type + ", model=" + this.model + ")";
            }
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class InternalMultipleOngoingActivityChipsModel {
        public final InternalChipModel primary;
        public final InternalChipModel secondary;

        public InternalMultipleOngoingActivityChipsModel(InternalChipModel internalChipModel, InternalChipModel internalChipModel2) {
            this.primary = internalChipModel;
            this.secondary = internalChipModel2;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof InternalMultipleOngoingActivityChipsModel)) {
                return false;
            }
            InternalMultipleOngoingActivityChipsModel internalMultipleOngoingActivityChipsModel = (InternalMultipleOngoingActivityChipsModel) obj;
            return Intrinsics.areEqual(this.primary, internalMultipleOngoingActivityChipsModel.primary) && Intrinsics.areEqual(this.secondary, internalMultipleOngoingActivityChipsModel.secondary);
        }

        public final int hashCode() {
            return this.secondary.hashCode() + (this.primary.hashCode() * 31);
        }

        public final String toString() {
            return "InternalMultipleOngoingActivityChipsModel(primary=" + this.primary + ", secondary=" + this.secondary + ")";
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class MostImportantChipResult {
        public final InternalChipModel mostImportantChip;
        public final ChipBundle remainingChips;

        public MostImportantChipResult(InternalChipModel internalChipModel, ChipBundle chipBundle) {
            this.mostImportantChip = internalChipModel;
            this.remainingChips = chipBundle;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof MostImportantChipResult)) {
                return false;
            }
            MostImportantChipResult mostImportantChipResult = (MostImportantChipResult) obj;
            return Intrinsics.areEqual(this.mostImportantChip, mostImportantChipResult.mostImportantChip) && Intrinsics.areEqual(this.remainingChips, mostImportantChipResult.remainingChips);
        }

        public final int hashCode() {
            return this.remainingChips.hashCode() + (this.mostImportantChip.hashCode() * 31);
        }

        public final String toString() {
            return "MostImportantChipResult(mostImportantChip=" + this.mostImportantChip + ", remainingChips=" + this.remainingChips + ")";
        }
    }

    public OngoingActivityChipsViewModel(CoroutineScope coroutineScope, ScreenRecordChipViewModel screenRecordChipViewModel, ShareToAppChipViewModel shareToAppChipViewModel, CastToOtherDeviceChipViewModel castToOtherDeviceChipViewModel, CallChipViewModel callChipViewModel, DemoRonChipViewModel demoRonChipViewModel, LogBuffer logBuffer) {
        this.logger = logBuffer;
        FlowKt__ZipKt$combine$$inlined$combineUnsafe$FlowKt__ZipKt$1 combine = FlowKt.combine(screenRecordChipViewModel.chip, shareToAppChipViewModel.chip, castToOtherDeviceChipViewModel.chip, callChipViewModel.chip, demoRonChipViewModel.chip, new OngoingActivityChipsViewModel$incomingChipBundle$1(this, null));
        StartedLazily startedLazily = SharingStarted.Companion.Lazily;
        final ReadonlyStateFlow stateIn = FlowKt.stateIn(combine, coroutineScope, startedLazily, new ChipBundle(new OngoingActivityChipModel.Hidden(true), new OngoingActivityChipModel.Hidden(true), new OngoingActivityChipModel.Hidden(true), new OngoingActivityChipModel.Hidden(true), new OngoingActivityChipModel.Hidden(true)));
        final int i = 0;
        final SafeFlow pairwise = com.android.systemui.util.kotlin.FlowKt.pairwise(new Flow() { // from class: com.android.systemui.statusbar.chips.ui.viewmodel.OngoingActivityChipsViewModel$special$$inlined$map$1

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.systemui.statusbar.chips.ui.viewmodel.OngoingActivityChipsViewModel$special$$inlined$map$1$2, reason: invalid class name */
            public final class AnonymousClass2 implements FlowCollector {
                public final /* synthetic */ FlowCollector $this_unsafeFlow;
                public final /* synthetic */ OngoingActivityChipsViewModel this$0;

                /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                /* renamed from: com.android.systemui.statusbar.chips.ui.viewmodel.OngoingActivityChipsViewModel$special$$inlined$map$1$2$1, reason: invalid class name */
                public final class AnonymousClass1 extends ContinuationImpl {
                    Object L$0;
                    int label;
                    /* synthetic */ Object result;

                    public AnonymousClass1(Continuation continuation) {
                        super(continuation);
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Object invokeSuspend(Object obj) {
                        this.result = obj;
                        this.label |= Integer.MIN_VALUE;
                        return AnonymousClass2.this.emit(null, this);
                    }
                }

                public AnonymousClass2(FlowCollector flowCollector, OngoingActivityChipsViewModel ongoingActivityChipsViewModel) {
                    this.$this_unsafeFlow = flowCollector;
                    this.this$0 = ongoingActivityChipsViewModel;
                }

                /* JADX WARN: Removed duplicated region for block: B:15:0x002f  */
                /* JADX WARN: Removed duplicated region for block: B:8:0x0021  */
                @Override // kotlinx.coroutines.flow.FlowCollector
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct add '--show-bad-code' argument
                */
                public final java.lang.Object emit(java.lang.Object r5, kotlin.coroutines.Continuation r6) {
                    /*
                        r4 = this;
                        boolean r0 = r6 instanceof com.android.systemui.statusbar.chips.ui.viewmodel.OngoingActivityChipsViewModel$special$$inlined$map$1.AnonymousClass2.AnonymousClass1
                        if (r0 == 0) goto L13
                        r0 = r6
                        com.android.systemui.statusbar.chips.ui.viewmodel.OngoingActivityChipsViewModel$special$$inlined$map$1$2$1 r0 = (com.android.systemui.statusbar.chips.ui.viewmodel.OngoingActivityChipsViewModel$special$$inlined$map$1.AnonymousClass2.AnonymousClass1) r0
                        int r1 = r0.label
                        r2 = -2147483648(0xffffffff80000000, float:-0.0)
                        r3 = r1 & r2
                        if (r3 == 0) goto L13
                        int r1 = r1 - r2
                        r0.label = r1
                        goto L18
                    L13:
                        com.android.systemui.statusbar.chips.ui.viewmodel.OngoingActivityChipsViewModel$special$$inlined$map$1$2$1 r0 = new com.android.systemui.statusbar.chips.ui.viewmodel.OngoingActivityChipsViewModel$special$$inlined$map$1$2$1
                        r0.<init>(r6)
                    L18:
                        java.lang.Object r6 = r0.result
                        kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
                        int r2 = r0.label
                        r3 = 1
                        if (r2 == 0) goto L2f
                        if (r2 != r3) goto L27
                        kotlin.ResultKt.throwOnFailure(r6)
                        goto L47
                    L27:
                        java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
                        java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
                        r4.<init>(r5)
                        throw r4
                    L2f:
                        kotlin.ResultKt.throwOnFailure(r6)
                        com.android.systemui.statusbar.chips.ui.viewmodel.OngoingActivityChipsViewModel$ChipBundle r5 = (com.android.systemui.statusbar.chips.ui.viewmodel.OngoingActivityChipsViewModel.ChipBundle) r5
                        com.android.systemui.statusbar.chips.ui.viewmodel.OngoingActivityChipsViewModel r6 = r4.this$0
                        com.android.systemui.statusbar.chips.ui.viewmodel.OngoingActivityChipsViewModel$MostImportantChipResult r5 = com.android.systemui.statusbar.chips.ui.viewmodel.OngoingActivityChipsViewModel.access$pickMostImportantChip(r6, r5)
                        r0.label = r3
                        com.android.systemui.statusbar.chips.ui.viewmodel.OngoingActivityChipsViewModel$InternalChipModel r5 = r5.mostImportantChip
                        kotlinx.coroutines.flow.FlowCollector r4 = r4.$this_unsafeFlow
                        java.lang.Object r4 = r4.emit(r5, r0)
                        if (r4 != r1) goto L47
                        return r1
                    L47:
                        kotlin.Unit r4 = kotlin.Unit.INSTANCE
                        return r4
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.statusbar.chips.ui.viewmodel.OngoingActivityChipsViewModel$special$$inlined$map$1.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                }
            }

            @Override // kotlinx.coroutines.flow.Flow
            public final Object collect(FlowCollector flowCollector, Continuation continuation) {
                switch (i) {
                    case 0:
                        ((ReadonlyStateFlow) stateIn).collect(new AnonymousClass2(flowCollector, this), continuation);
                        return CoroutineSingletons.COROUTINE_SUSPENDED;
                    case 1:
                        ((ReadonlyStateFlow) stateIn).collect(new OngoingActivityChipsViewModel$special$$inlined$map$3$2(flowCollector, this), continuation);
                        return CoroutineSingletons.COROUTINE_SUSPENDED;
                    default:
                        Object collect = ((SafeFlow) stateIn).collect(new OngoingActivityChipsViewModel$special$$inlined$map$2$2(flowCollector, this), continuation);
                        return collect == CoroutineSingletons.COROUTINE_SUSPENDED ? collect : Unit.INSTANCE;
                }
            }
        }, DEFAULT_INTERNAL_HIDDEN_MODEL);
        final int i2 = 2;
        final ReadonlyStateFlow stateIn2 = FlowKt.stateIn(new Flow() { // from class: com.android.systemui.statusbar.chips.ui.viewmodel.OngoingActivityChipsViewModel$special$$inlined$map$1

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.systemui.statusbar.chips.ui.viewmodel.OngoingActivityChipsViewModel$special$$inlined$map$1$2, reason: invalid class name */
            public final class AnonymousClass2 implements FlowCollector {
                public final /* synthetic */ FlowCollector $this_unsafeFlow;
                public final /* synthetic */ OngoingActivityChipsViewModel this$0;

                /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                /* renamed from: com.android.systemui.statusbar.chips.ui.viewmodel.OngoingActivityChipsViewModel$special$$inlined$map$1$2$1, reason: invalid class name */
                public final class AnonymousClass1 extends ContinuationImpl {
                    Object L$0;
                    int label;
                    /* synthetic */ Object result;

                    public AnonymousClass1(Continuation continuation) {
                        super(continuation);
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Object invokeSuspend(Object obj) {
                        this.result = obj;
                        this.label |= Integer.MIN_VALUE;
                        return AnonymousClass2.this.emit(null, this);
                    }
                }

                public AnonymousClass2(FlowCollector flowCollector, OngoingActivityChipsViewModel ongoingActivityChipsViewModel) {
                    this.$this_unsafeFlow = flowCollector;
                    this.this$0 = ongoingActivityChipsViewModel;
                }

                @Override // kotlinx.coroutines.flow.FlowCollector
                public final Object emit(Object obj, Continuation continuation) {
                    /*
                        this = this;
                        boolean r0 = r6 instanceof com.android.systemui.statusbar.chips.ui.viewmodel.OngoingActivityChipsViewModel$special$$inlined$map$1.AnonymousClass2.AnonymousClass1
                        if (r0 == 0) goto L13
                        r0 = r6
                        com.android.systemui.statusbar.chips.ui.viewmodel.OngoingActivityChipsViewModel$special$$inlined$map$1$2$1 r0 = (com.android.systemui.statusbar.chips.ui.viewmodel.OngoingActivityChipsViewModel$special$$inlined$map$1.AnonymousClass2.AnonymousClass1) r0
                        int r1 = r0.label
                        r2 = -2147483648(0xffffffff80000000, float:-0.0)
                        r3 = r1 & r2
                        if (r3 == 0) goto L13
                        int r1 = r1 - r2
                        r0.label = r1
                        goto L18
                    L13:
                        com.android.systemui.statusbar.chips.ui.viewmodel.OngoingActivityChipsViewModel$special$$inlined$map$1$2$1 r0 = new com.android.systemui.statusbar.chips.ui.viewmodel.OngoingActivityChipsViewModel$special$$inlined$map$1$2$1
                        r0.<init>(r6)
                    L18:
                        java.lang.Object r6 = r0.result
                        kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
                        int r2 = r0.label
                        r3 = 1
                        if (r2 == 0) goto L2f
                        if (r2 != r3) goto L27
                        kotlin.ResultKt.throwOnFailure(r6)
                        goto L47
                    L27:
                        java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
                        java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
                        r4.<init>(r5)
                        throw r4
                    L2f:
                        kotlin.ResultKt.throwOnFailure(r6)
                        com.android.systemui.statusbar.chips.ui.viewmodel.OngoingActivityChipsViewModel$ChipBundle r5 = (com.android.systemui.statusbar.chips.ui.viewmodel.OngoingActivityChipsViewModel.ChipBundle) r5
                        com.android.systemui.statusbar.chips.ui.viewmodel.OngoingActivityChipsViewModel r6 = r4.this$0
                        com.android.systemui.statusbar.chips.ui.viewmodel.OngoingActivityChipsViewModel$MostImportantChipResult r5 = com.android.systemui.statusbar.chips.ui.viewmodel.OngoingActivityChipsViewModel.access$pickMostImportantChip(r6, r5)
                        r0.label = r3
                        com.android.systemui.statusbar.chips.ui.viewmodel.OngoingActivityChipsViewModel$InternalChipModel r5 = r5.mostImportantChip
                        kotlinx.coroutines.flow.FlowCollector r4 = r4.$this_unsafeFlow
                        java.lang.Object r4 = r4.emit(r5, r0)
                        if (r4 != r1) goto L47
                        return r1
                    L47:
                        kotlin.Unit r4 = kotlin.Unit.INSTANCE
                        return r4
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.statusbar.chips.ui.viewmodel.OngoingActivityChipsViewModel$special$$inlined$map$1.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                }
            }

            @Override // kotlinx.coroutines.flow.Flow
            public final Object collect(FlowCollector flowCollector, Continuation continuation) {
                switch (i2) {
                    case 0:
                        ((ReadonlyStateFlow) pairwise).collect(new AnonymousClass2(flowCollector, this), continuation);
                        return CoroutineSingletons.COROUTINE_SUSPENDED;
                    case 1:
                        ((ReadonlyStateFlow) pairwise).collect(new OngoingActivityChipsViewModel$special$$inlined$map$3$2(flowCollector, this), continuation);
                        return CoroutineSingletons.COROUTINE_SUSPENDED;
                    default:
                        Object collect = ((SafeFlow) pairwise).collect(new OngoingActivityChipsViewModel$special$$inlined$map$2$2(flowCollector, this), continuation);
                        return collect == CoroutineSingletons.COROUTINE_SUSPENDED ? collect : Unit.INSTANCE;
                }
            }
        }, coroutineScope, startedLazily, new OngoingActivityChipModel.Hidden(true));
        this.primaryChip = stateIn2;
        final int i3 = 1;
        new Flow() { // from class: com.android.systemui.statusbar.chips.ui.viewmodel.OngoingActivityChipsViewModel$special$$inlined$map$1

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.systemui.statusbar.chips.ui.viewmodel.OngoingActivityChipsViewModel$special$$inlined$map$1$2, reason: invalid class name */
            public final class AnonymousClass2 implements FlowCollector {
                public final /* synthetic */ FlowCollector $this_unsafeFlow;
                public final /* synthetic */ OngoingActivityChipsViewModel this$0;

                /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                /* renamed from: com.android.systemui.statusbar.chips.ui.viewmodel.OngoingActivityChipsViewModel$special$$inlined$map$1$2$1, reason: invalid class name */
                public final class AnonymousClass1 extends ContinuationImpl {
                    Object L$0;
                    int label;
                    /* synthetic */ Object result;

                    public AnonymousClass1(Continuation continuation) {
                        super(continuation);
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Object invokeSuspend(Object obj) {
                        this.result = obj;
                        this.label |= Integer.MIN_VALUE;
                        return AnonymousClass2.this.emit(null, this);
                    }
                }

                public AnonymousClass2(FlowCollector flowCollector, OngoingActivityChipsViewModel ongoingActivityChipsViewModel) {
                    this.$this_unsafeFlow = flowCollector;
                    this.this$0 = ongoingActivityChipsViewModel;
                }

                @Override // kotlinx.coroutines.flow.FlowCollector
                public final Object emit(Object obj, Continuation continuation) {
                    /*
                        this = this;
                        boolean r0 = r6 instanceof com.android.systemui.statusbar.chips.ui.viewmodel.OngoingActivityChipsViewModel$special$$inlined$map$1.AnonymousClass2.AnonymousClass1
                        if (r0 == 0) goto L13
                        r0 = r6
                        com.android.systemui.statusbar.chips.ui.viewmodel.OngoingActivityChipsViewModel$special$$inlined$map$1$2$1 r0 = (com.android.systemui.statusbar.chips.ui.viewmodel.OngoingActivityChipsViewModel$special$$inlined$map$1.AnonymousClass2.AnonymousClass1) r0
                        int r1 = r0.label
                        r2 = -2147483648(0xffffffff80000000, float:-0.0)
                        r3 = r1 & r2
                        if (r3 == 0) goto L13
                        int r1 = r1 - r2
                        r0.label = r1
                        goto L18
                    L13:
                        com.android.systemui.statusbar.chips.ui.viewmodel.OngoingActivityChipsViewModel$special$$inlined$map$1$2$1 r0 = new com.android.systemui.statusbar.chips.ui.viewmodel.OngoingActivityChipsViewModel$special$$inlined$map$1$2$1
                        r0.<init>(r6)
                    L18:
                        java.lang.Object r6 = r0.result
                        kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
                        int r2 = r0.label
                        r3 = 1
                        if (r2 == 0) goto L2f
                        if (r2 != r3) goto L27
                        kotlin.ResultKt.throwOnFailure(r6)
                        goto L47
                    L27:
                        java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
                        java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
                        r4.<init>(r5)
                        throw r4
                    L2f:
                        kotlin.ResultKt.throwOnFailure(r6)
                        com.android.systemui.statusbar.chips.ui.viewmodel.OngoingActivityChipsViewModel$ChipBundle r5 = (com.android.systemui.statusbar.chips.ui.viewmodel.OngoingActivityChipsViewModel.ChipBundle) r5
                        com.android.systemui.statusbar.chips.ui.viewmodel.OngoingActivityChipsViewModel r6 = r4.this$0
                        com.android.systemui.statusbar.chips.ui.viewmodel.OngoingActivityChipsViewModel$MostImportantChipResult r5 = com.android.systemui.statusbar.chips.ui.viewmodel.OngoingActivityChipsViewModel.access$pickMostImportantChip(r6, r5)
                        r0.label = r3
                        com.android.systemui.statusbar.chips.ui.viewmodel.OngoingActivityChipsViewModel$InternalChipModel r5 = r5.mostImportantChip
                        kotlinx.coroutines.flow.FlowCollector r4 = r4.$this_unsafeFlow
                        java.lang.Object r4 = r4.emit(r5, r0)
                        if (r4 != r1) goto L47
                        return r1
                    L47:
                        kotlin.Unit r4 = kotlin.Unit.INSTANCE
                        return r4
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.statusbar.chips.ui.viewmodel.OngoingActivityChipsViewModel$special$$inlined$map$1.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                }
            }

            @Override // kotlinx.coroutines.flow.Flow
            public final Object collect(FlowCollector flowCollector, Continuation continuation) {
                switch (i3) {
                    case 0:
                        ((ReadonlyStateFlow) stateIn).collect(new AnonymousClass2(flowCollector, this), continuation);
                        return CoroutineSingletons.COROUTINE_SUSPENDED;
                    case 1:
                        ((ReadonlyStateFlow) stateIn).collect(new OngoingActivityChipsViewModel$special$$inlined$map$3$2(flowCollector, this), continuation);
                        return CoroutineSingletons.COROUTINE_SUSPENDED;
                    default:
                        Object collect = ((SafeFlow) stateIn).collect(new OngoingActivityChipsViewModel$special$$inlined$map$2$2(flowCollector, this), continuation);
                        return collect == CoroutineSingletons.COROUTINE_SUSPENDED ? collect : Unit.INSTANCE;
                }
            }
        };
        this.chips = FlowKt.stateIn(new Flow() { // from class: com.android.systemui.statusbar.chips.ui.viewmodel.OngoingActivityChipsViewModel$special$$inlined$map$4

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.systemui.statusbar.chips.ui.viewmodel.OngoingActivityChipsViewModel$special$$inlined$map$4$2, reason: invalid class name */
            public final class AnonymousClass2 implements FlowCollector {
                public final /* synthetic */ FlowCollector $this_unsafeFlow;

                /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                /* renamed from: com.android.systemui.statusbar.chips.ui.viewmodel.OngoingActivityChipsViewModel$special$$inlined$map$4$2$1, reason: invalid class name */
                public final class AnonymousClass1 extends ContinuationImpl {
                    Object L$0;
                    int label;
                    /* synthetic */ Object result;

                    public AnonymousClass1(Continuation continuation) {
                        super(continuation);
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Object invokeSuspend(Object obj) {
                        this.result = obj;
                        this.label |= Integer.MIN_VALUE;
                        return AnonymousClass2.this.emit(null, this);
                    }
                }

                public AnonymousClass2(FlowCollector flowCollector) {
                    this.$this_unsafeFlow = flowCollector;
                }

                /* JADX WARN: Removed duplicated region for block: B:15:0x002f  */
                /* JADX WARN: Removed duplicated region for block: B:8:0x0021  */
                @Override // kotlinx.coroutines.flow.FlowCollector
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct add '--show-bad-code' argument
                */
                public final java.lang.Object emit(java.lang.Object r5, kotlin.coroutines.Continuation r6) {
                    /*
                        r4 = this;
                        boolean r0 = r6 instanceof com.android.systemui.statusbar.chips.ui.viewmodel.OngoingActivityChipsViewModel$special$$inlined$map$4.AnonymousClass2.AnonymousClass1
                        if (r0 == 0) goto L13
                        r0 = r6
                        com.android.systemui.statusbar.chips.ui.viewmodel.OngoingActivityChipsViewModel$special$$inlined$map$4$2$1 r0 = (com.android.systemui.statusbar.chips.ui.viewmodel.OngoingActivityChipsViewModel$special$$inlined$map$4.AnonymousClass2.AnonymousClass1) r0
                        int r1 = r0.label
                        r2 = -2147483648(0xffffffff80000000, float:-0.0)
                        r3 = r1 & r2
                        if (r3 == 0) goto L13
                        int r1 = r1 - r2
                        r0.label = r1
                        goto L18
                    L13:
                        com.android.systemui.statusbar.chips.ui.viewmodel.OngoingActivityChipsViewModel$special$$inlined$map$4$2$1 r0 = new com.android.systemui.statusbar.chips.ui.viewmodel.OngoingActivityChipsViewModel$special$$inlined$map$4$2$1
                        r0.<init>(r6)
                    L18:
                        java.lang.Object r6 = r0.result
                        kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
                        int r2 = r0.label
                        r3 = 1
                        if (r2 == 0) goto L2f
                        if (r2 != r3) goto L27
                        kotlin.ResultKt.throwOnFailure(r6)
                        goto L49
                    L27:
                        java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
                        java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
                        r4.<init>(r5)
                        throw r4
                    L2f:
                        kotlin.ResultKt.throwOnFailure(r6)
                        com.android.systemui.statusbar.chips.ui.model.OngoingActivityChipModel r5 = (com.android.systemui.statusbar.chips.ui.model.OngoingActivityChipModel) r5
                        com.android.systemui.statusbar.chips.ui.model.MultipleOngoingActivityChipsModel r6 = new com.android.systemui.statusbar.chips.ui.model.MultipleOngoingActivityChipsModel
                        com.android.systemui.statusbar.chips.ui.model.OngoingActivityChipModel$Hidden r2 = new com.android.systemui.statusbar.chips.ui.model.OngoingActivityChipModel$Hidden
                        r2.<init>(r3)
                        r6.<init>(r5, r2)
                        r0.label = r3
                        kotlinx.coroutines.flow.FlowCollector r4 = r4.$this_unsafeFlow
                        java.lang.Object r4 = r4.emit(r6, r0)
                        if (r4 != r1) goto L49
                        return r1
                    L49:
                        kotlin.Unit r4 = kotlin.Unit.INSTANCE
                        return r4
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.statusbar.chips.ui.viewmodel.OngoingActivityChipsViewModel$special$$inlined$map$4.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                }
            }

            @Override // kotlinx.coroutines.flow.Flow
            public final Object collect(FlowCollector flowCollector, Continuation continuation) {
                ReadonlyStateFlow.this.collect(new AnonymousClass2(flowCollector), continuation);
                return CoroutineSingletons.COROUTINE_SUSPENDED;
            }
        }, coroutineScope, startedLazily, new MultipleOngoingActivityChipsModel(new OngoingActivityChipModel.Hidden(true), new OngoingActivityChipModel.Hidden(true)));
    }

    public static final MostImportantChipResult access$pickMostImportantChip(OngoingActivityChipsViewModel ongoingActivityChipsViewModel, ChipBundle chipBundle) {
        ongoingActivityChipsViewModel.getClass();
        OngoingActivityChipModel ongoingActivityChipModel = chipBundle.screenRecord;
        if (ongoingActivityChipModel instanceof OngoingActivityChipModel.Shown) {
            return new MostImportantChipResult(new InternalChipModel.Shown(ChipType.ScreenRecord, (OngoingActivityChipModel.Shown) ongoingActivityChipModel), ChipBundle.copy$default(chipBundle, new OngoingActivityChipModel.Hidden(true), new OngoingActivityChipModel.Hidden(true), null, null, 28));
        }
        OngoingActivityChipModel ongoingActivityChipModel2 = chipBundle.shareToApp;
        if (ongoingActivityChipModel2 instanceof OngoingActivityChipModel.Shown) {
            return new MostImportantChipResult(new InternalChipModel.Shown(ChipType.ShareToApp, (OngoingActivityChipModel.Shown) ongoingActivityChipModel2), ChipBundle.copy$default(chipBundle, null, new OngoingActivityChipModel.Hidden(true), null, null, 29));
        }
        OngoingActivityChipModel ongoingActivityChipModel3 = chipBundle.castToOtherDevice;
        if (ongoingActivityChipModel3 instanceof OngoingActivityChipModel.Shown) {
            return new MostImportantChipResult(new InternalChipModel.Shown(ChipType.CastToOtherDevice, (OngoingActivityChipModel.Shown) ongoingActivityChipModel3), ChipBundle.copy$default(chipBundle, null, null, new OngoingActivityChipModel.Hidden(true), null, 27));
        }
        OngoingActivityChipModel ongoingActivityChipModel4 = chipBundle.call;
        if (ongoingActivityChipModel4 instanceof OngoingActivityChipModel.Shown) {
            return new MostImportantChipResult(new InternalChipModel.Shown(ChipType.Call, (OngoingActivityChipModel.Shown) ongoingActivityChipModel4), ChipBundle.copy$default(chipBundle, null, null, null, new OngoingActivityChipModel.Hidden(true), 23));
        }
        OngoingActivityChipModel ongoingActivityChipModel5 = chipBundle.demoRon;
        if (ongoingActivityChipModel5 instanceof OngoingActivityChipModel.Shown) {
            throw new IllegalStateException("New code path not supported when com.android.systemui.status_bar_ron_chips is disabled.");
        }
        if (!(ongoingActivityChipModel instanceof OngoingActivityChipModel.Hidden)) {
            throw new IllegalStateException("Check failed.");
        }
        if (!(ongoingActivityChipModel2 instanceof OngoingActivityChipModel.Hidden)) {
            throw new IllegalStateException("Check failed.");
        }
        if (!(ongoingActivityChipModel3 instanceof OngoingActivityChipModel.Hidden)) {
            throw new IllegalStateException("Check failed.");
        }
        if (!(ongoingActivityChipModel4 instanceof OngoingActivityChipModel.Hidden)) {
            throw new IllegalStateException("Check failed.");
        }
        if (ongoingActivityChipModel5 instanceof OngoingActivityChipModel.Hidden) {
            return new MostImportantChipResult(new InternalChipModel.Hidden((OngoingActivityChipModel.Hidden) ongoingActivityChipModel, (OngoingActivityChipModel.Hidden) ongoingActivityChipModel2, (OngoingActivityChipModel.Hidden) ongoingActivityChipModel3, (OngoingActivityChipModel.Hidden) ongoingActivityChipModel4, (OngoingActivityChipModel.Hidden) ongoingActivityChipModel5), chipBundle);
        }
        throw new IllegalStateException("Check failed.");
    }
}
