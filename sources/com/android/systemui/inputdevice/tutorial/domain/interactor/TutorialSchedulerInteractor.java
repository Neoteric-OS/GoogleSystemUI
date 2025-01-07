package com.android.systemui.inputdevice.tutorial.domain.interactor;

import com.android.systemui.inputdevice.tutorial.data.repository.DeviceType;
import com.android.systemui.inputdevice.tutorial.data.repository.TutorialSchedulerRepository;
import com.android.systemui.keyboard.data.repository.KeyboardRepositoryImpl;
import com.android.systemui.touchpad.data.repository.TouchpadRepositoryImpl;
import java.util.Map;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.collections.MapsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.enums.EnumEntriesKt;
import kotlin.time.Duration;
import kotlin.time.DurationKt;
import kotlin.time.DurationUnit;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.SafeFlow;
import kotlinx.coroutines.flow.internal.ChannelLimitedFlowMerge;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class TutorialSchedulerInteractor {
    public static final long DEFAULT_LAUNCH_DELAY_SEC;
    public final Map isAnyDeviceConnected;
    public final SafeFlow keyboardScheduleFlow;
    public final TutorialSchedulerRepository repo;
    public final SafeFlow touchpadScheduleFlow;
    public final TutorialSchedulerInteractor$special$$inlined$map$1 tutorials;

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class TutorialType {
        public static final /* synthetic */ TutorialType[] $VALUES;
        public static final TutorialType BOTH;
        public static final TutorialType KEYBOARD;
        public static final TutorialType NONE;
        public static final TutorialType TOUCHPAD;

        static {
            TutorialType tutorialType = new TutorialType("KEYBOARD", 0);
            KEYBOARD = tutorialType;
            TutorialType tutorialType2 = new TutorialType("TOUCHPAD", 1);
            TOUCHPAD = tutorialType2;
            TutorialType tutorialType3 = new TutorialType("BOTH", 2);
            BOTH = tutorialType3;
            TutorialType tutorialType4 = new TutorialType("NONE", 3);
            NONE = tutorialType4;
            TutorialType[] tutorialTypeArr = {tutorialType, tutorialType2, tutorialType3, tutorialType4};
            $VALUES = tutorialTypeArr;
            EnumEntriesKt.enumEntries(tutorialTypeArr);
        }

        public static TutorialType valueOf(String str) {
            return (TutorialType) Enum.valueOf(TutorialType.class, str);
        }

        public static TutorialType[] values() {
            return (TutorialType[]) $VALUES.clone();
        }
    }

    static {
        int i = Duration.$r8$clinit;
        DEFAULT_LAUNCH_DELAY_SEC = Duration.m1782toLongimpl(DurationKt.toDuration(72, DurationUnit.HOURS), DurationUnit.SECONDS);
    }

    public TutorialSchedulerInteractor(KeyboardRepositoryImpl keyboardRepositoryImpl, TouchpadRepositoryImpl touchpadRepositoryImpl, TutorialSchedulerRepository tutorialSchedulerRepository) {
        this.repo = tutorialSchedulerRepository;
        this.isAnyDeviceConnected = MapsKt.mapOf(new Pair(DeviceType.KEYBOARD, keyboardRepositoryImpl.isAnyKeyboardConnected), new Pair(DeviceType.TOUCHPAD, touchpadRepositoryImpl.isAnyTouchpadConnected));
        final ChannelLimitedFlowMerge merge = FlowKt.merge(new SafeFlow(new TutorialSchedulerInteractor$touchpadScheduleFlow$1(this, null)), new SafeFlow(new TutorialSchedulerInteractor$keyboardScheduleFlow$1(this, null)));
        new Flow() { // from class: com.android.systemui.inputdevice.tutorial.domain.interactor.TutorialSchedulerInteractor$special$$inlined$map$1

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.systemui.inputdevice.tutorial.domain.interactor.TutorialSchedulerInteractor$special$$inlined$map$1$2, reason: invalid class name */
            public final class AnonymousClass2 implements FlowCollector {
                public final /* synthetic */ FlowCollector $this_unsafeFlow;
                public final /* synthetic */ TutorialSchedulerInteractor this$0;

                /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                /* renamed from: com.android.systemui.inputdevice.tutorial.domain.interactor.TutorialSchedulerInteractor$special$$inlined$map$1$2$1, reason: invalid class name */
                public final class AnonymousClass1 extends ContinuationImpl {
                    Object L$0;
                    Object L$1;
                    Object L$2;
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

                public AnonymousClass2(FlowCollector flowCollector, TutorialSchedulerInteractor tutorialSchedulerInteractor) {
                    this.$this_unsafeFlow = flowCollector;
                    this.this$0 = tutorialSchedulerInteractor;
                }

                /* JADX WARN: Removed duplicated region for block: B:21:0x00d5 A[RETURN] */
                /* JADX WARN: Removed duplicated region for block: B:25:0x00aa  */
                /* JADX WARN: Removed duplicated region for block: B:29:0x00c6 A[RETURN] */
                /* JADX WARN: Removed duplicated region for block: B:38:0x00a5 A[RETURN] */
                /* JADX WARN: Removed duplicated region for block: B:39:0x0066  */
                /* JADX WARN: Removed duplicated region for block: B:8:0x0025  */
                @Override // kotlinx.coroutines.flow.FlowCollector
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct add '--show-bad-code' argument
                */
                public final java.lang.Object emit(java.lang.Object r11, kotlin.coroutines.Continuation r12) {
                    /*
                        Method dump skipped, instructions count: 217
                        To view this dump add '--comments-level debug' option
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.inputdevice.tutorial.domain.interactor.TutorialSchedulerInteractor$special$$inlined$map$1.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                }
            }

            @Override // kotlinx.coroutines.flow.Flow
            public final Object collect(FlowCollector flowCollector, Continuation continuation) {
                Object collect = ChannelLimitedFlowMerge.this.collect(new AnonymousClass2(flowCollector, this), continuation);
                return collect == CoroutineSingletons.COROUTINE_SUSPENDED ? collect : Unit.INSTANCE;
            }
        };
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x00cc  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x00d0  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x00b8  */
    /* JADX WARN: Removed duplicated region for block: B:30:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:34:0x0077  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x007a  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x005c  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0026  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object access$resolveTutorialType(com.android.systemui.inputdevice.tutorial.domain.interactor.TutorialSchedulerInteractor r7, com.android.systemui.inputdevice.tutorial.data.repository.DeviceType r8, kotlin.coroutines.jvm.internal.ContinuationImpl r9) {
        /*
            Method dump skipped, instructions count: 212
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.inputdevice.tutorial.domain.interactor.TutorialSchedulerInteractor.access$resolveTutorialType(com.android.systemui.inputdevice.tutorial.domain.interactor.TutorialSchedulerInteractor, com.android.systemui.inputdevice.tutorial.data.repository.DeviceType, kotlin.coroutines.jvm.internal.ContinuationImpl):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x002d  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0032  */
    /* JADX WARN: Removed duplicated region for block: B:17:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:18:0x003f  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0127  */
    /* JADX WARN: Removed duplicated region for block: B:22:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0050  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x00de  */
    /* JADX WARN: Removed duplicated region for block: B:28:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:29:0x005d  */
    /* JADX WARN: Removed duplicated region for block: B:32:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:33:0x0069  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0092  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x0076  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0025  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object access$schedule(com.android.systemui.inputdevice.tutorial.domain.interactor.TutorialSchedulerInteractor r9, com.android.systemui.inputdevice.tutorial.data.repository.DeviceType r10, kotlin.coroutines.jvm.internal.ContinuationImpl r11) {
        /*
            Method dump skipped, instructions count: 348
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.inputdevice.tutorial.domain.interactor.TutorialSchedulerInteractor.access$schedule(com.android.systemui.inputdevice.tutorial.domain.interactor.TutorialSchedulerInteractor, com.android.systemui.inputdevice.tutorial.data.repository.DeviceType, kotlin.coroutines.jvm.internal.ContinuationImpl):java.lang.Object");
    }
}
