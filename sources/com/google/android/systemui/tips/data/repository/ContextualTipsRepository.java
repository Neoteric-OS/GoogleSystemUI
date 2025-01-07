package com.google.android.systemui.tips.data.repository;

import android.content.Context;
import android.content.SharedPreferences;
import com.android.systemui.util.settings.GlobalSettings;
import com.android.systemui.util.settings.SettingsProxyExt;
import com.android.systemui.util.time.SystemClock;
import com.android.systemui.util.time.SystemClockImpl;
import com.google.android.systemui.tips.data.model.HistoricalTipsModel;
import com.google.android.systemui.tips.data.model.TipType;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.FlowKt__EmittersKt$onStart$$inlined$unsafeFlow$1;
import kotlinx.coroutines.flow.ReadonlyStateFlow;
import kotlinx.coroutines.flow.StateFlowImpl;
import kotlinx.coroutines.flow.StateFlowKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class ContextualTipsRepository {
    public final StateFlowImpl _approxAssistantDismissals;
    public final StateFlowImpl _assistantDismissals;
    public final StateFlowImpl _assistantStartCount;
    public final StateFlowImpl _eligibleVolumeDialogDismissals;
    public final StateFlowImpl _muteVolumeTipHistory;
    public final StateFlowImpl _powerMenuDismissCount;
    public final StateFlowImpl _powerMenuStartCount;
    public final StateFlowImpl _powerOffTipHistory;
    public final StateFlowImpl _screenshotTipHistory;
    public final ReadonlyStateFlow approxAssistantDismissals;
    public final ReadonlyStateFlow assistantDismissals;
    public final ReadonlyStateFlow assistantStartCount;
    public final ReadonlyStateFlow eligibleVolumeDialogDismissals;
    public final GlobalSettings globalSettings;
    public final Flow longPressOnPowerBehavior;
    public final ReadonlyStateFlow muteVolumeTipHistory;
    public final ReadonlyStateFlow powerMenuDismissCount;
    public final ReadonlyStateFlow powerMenuStartCount;
    public final ReadonlyStateFlow powerOffTipHistory;
    public final SharedPreferences preferences;
    public final ReadonlyStateFlow screenshotTipHistory;
    public final SystemClock systemClock;

    public ContextualTipsRepository(Context context, CoroutineDispatcher coroutineDispatcher, GlobalSettings globalSettings, SystemClock systemClock) {
        this.globalSettings = globalSettings;
        this.systemClock = systemClock;
        StateFlowImpl MutableStateFlow = StateFlowKt.MutableStateFlow(0);
        this._powerMenuStartCount = MutableStateFlow;
        this.powerMenuStartCount = new ReadonlyStateFlow(MutableStateFlow);
        StateFlowImpl MutableStateFlow2 = StateFlowKt.MutableStateFlow(0);
        this._powerMenuDismissCount = MutableStateFlow2;
        this.powerMenuDismissCount = new ReadonlyStateFlow(MutableStateFlow2);
        StateFlowImpl MutableStateFlow3 = StateFlowKt.MutableStateFlow(0);
        this._assistantStartCount = MutableStateFlow3;
        this.assistantStartCount = new ReadonlyStateFlow(MutableStateFlow3);
        StateFlowImpl MutableStateFlow4 = StateFlowKt.MutableStateFlow(0);
        this._approxAssistantDismissals = MutableStateFlow4;
        this.approxAssistantDismissals = new ReadonlyStateFlow(MutableStateFlow4);
        StateFlowImpl MutableStateFlow5 = StateFlowKt.MutableStateFlow(0);
        this._assistantDismissals = MutableStateFlow5;
        this.assistantDismissals = new ReadonlyStateFlow(MutableStateFlow5);
        StateFlowImpl MutableStateFlow6 = StateFlowKt.MutableStateFlow(0);
        this._eligibleVolumeDialogDismissals = MutableStateFlow6;
        this.eligibleVolumeDialogDismissals = new ReadonlyStateFlow(MutableStateFlow6);
        final FlowKt__EmittersKt$onStart$$inlined$unsafeFlow$1 flowKt__EmittersKt$onStart$$inlined$unsafeFlow$1 = new FlowKt__EmittersKt$onStart$$inlined$unsafeFlow$1(new ContextualTipsRepository$longPressOnPowerBehavior$1(2, null), SettingsProxyExt.observerFlow(globalSettings, "power_button_long_press"));
        this.longPressOnPowerBehavior = FlowKt.flowOn(new Flow() { // from class: com.google.android.systemui.tips.data.repository.ContextualTipsRepository$special$$inlined$map$1

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.google.android.systemui.tips.data.repository.ContextualTipsRepository$special$$inlined$map$1$2, reason: invalid class name */
            public final class AnonymousClass2 implements FlowCollector {
                public final /* synthetic */ FlowCollector $this_unsafeFlow;
                public final /* synthetic */ ContextualTipsRepository this$0;

                /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                /* renamed from: com.google.android.systemui.tips.data.repository.ContextualTipsRepository$special$$inlined$map$1$2$1, reason: invalid class name */
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

                public AnonymousClass2(FlowCollector flowCollector, ContextualTipsRepository contextualTipsRepository) {
                    this.$this_unsafeFlow = flowCollector;
                    this.this$0 = contextualTipsRepository;
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
                        boolean r0 = r6 instanceof com.google.android.systemui.tips.data.repository.ContextualTipsRepository$special$$inlined$map$1.AnonymousClass2.AnonymousClass1
                        if (r0 == 0) goto L13
                        r0 = r6
                        com.google.android.systemui.tips.data.repository.ContextualTipsRepository$special$$inlined$map$1$2$1 r0 = (com.google.android.systemui.tips.data.repository.ContextualTipsRepository$special$$inlined$map$1.AnonymousClass2.AnonymousClass1) r0
                        int r1 = r0.label
                        r2 = -2147483648(0xffffffff80000000, float:-0.0)
                        r3 = r1 & r2
                        if (r3 == 0) goto L13
                        int r1 = r1 - r2
                        r0.label = r1
                        goto L18
                    L13:
                        com.google.android.systemui.tips.data.repository.ContextualTipsRepository$special$$inlined$map$1$2$1 r0 = new com.google.android.systemui.tips.data.repository.ContextualTipsRepository$special$$inlined$map$1$2$1
                        r0.<init>(r6)
                    L18:
                        java.lang.Object r6 = r0.result
                        kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
                        int r2 = r0.label
                        r3 = 1
                        if (r2 == 0) goto L2f
                        if (r2 != r3) goto L27
                        kotlin.ResultKt.throwOnFailure(r6)
                        goto L4f
                    L27:
                        java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
                        java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
                        r4.<init>(r5)
                        throw r4
                    L2f:
                        kotlin.ResultKt.throwOnFailure(r6)
                        kotlin.Unit r5 = (kotlin.Unit) r5
                        com.google.android.systemui.tips.data.repository.ContextualTipsRepository r5 = r4.this$0
                        com.android.systemui.util.settings.GlobalSettings r5 = r5.globalSettings
                        java.lang.String r6 = "power_button_long_press"
                        r2 = 5
                        int r5 = r5.getInt(r2, r6)
                        java.lang.Integer r6 = new java.lang.Integer
                        r6.<init>(r5)
                        r0.label = r3
                        kotlinx.coroutines.flow.FlowCollector r4 = r4.$this_unsafeFlow
                        java.lang.Object r4 = r4.emit(r6, r0)
                        if (r4 != r1) goto L4f
                        return r1
                    L4f:
                        kotlin.Unit r4 = kotlin.Unit.INSTANCE
                        return r4
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.google.android.systemui.tips.data.repository.ContextualTipsRepository$special$$inlined$map$1.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                }
            }

            @Override // kotlinx.coroutines.flow.Flow
            public final Object collect(FlowCollector flowCollector, Continuation continuation) {
                Object collect = FlowKt__EmittersKt$onStart$$inlined$unsafeFlow$1.this.collect(new AnonymousClass2(flowCollector, this), continuation);
                return collect == CoroutineSingletons.COROUTINE_SUSPENDED ? collect : Unit.INSTANCE;
            }
        }, coroutineDispatcher);
        this.preferences = context.getSharedPreferences("contextual_tips_preferences", 0);
        StateFlowImpl MutableStateFlow7 = StateFlowKt.MutableStateFlow(readTipsHistoryFromPreferences(TipType.SCREENSHOT));
        this._screenshotTipHistory = MutableStateFlow7;
        this.screenshotTipHistory = new ReadonlyStateFlow(MutableStateFlow7);
        StateFlowImpl MutableStateFlow8 = StateFlowKt.MutableStateFlow(readTipsHistoryFromPreferences(TipType.POWER_OFF));
        this._powerOffTipHistory = MutableStateFlow8;
        this.powerOffTipHistory = new ReadonlyStateFlow(MutableStateFlow8);
        StateFlowImpl MutableStateFlow9 = StateFlowKt.MutableStateFlow(readTipsHistoryFromPreferences(TipType.MUTE_VOLUME));
        this._muteVolumeTipHistory = MutableStateFlow9;
        this.muteVolumeTipHistory = new ReadonlyStateFlow(MutableStateFlow9);
    }

    public static void updateTriggeringHistory$default(ContextualTipsRepository contextualTipsRepository, TipType tipType) {
        contextualTipsRepository.getClass();
        int ordinal = tipType.ordinal();
        if (ordinal == 1) {
            StateFlowImpl stateFlowImpl = contextualTipsRepository._screenshotTipHistory;
            HistoricalTipsModel incrementTipsHistory = contextualTipsRepository.incrementTipsHistory((HistoricalTipsModel) stateFlowImpl.getValue());
            stateFlowImpl.getClass();
            stateFlowImpl.updateState(null, incrementTipsHistory);
            contextualTipsRepository.writeTipsHistoryToPreferences(tipType, (HistoricalTipsModel) stateFlowImpl.getValue());
            return;
        }
        if (ordinal == 2) {
            StateFlowImpl stateFlowImpl2 = contextualTipsRepository._powerOffTipHistory;
            HistoricalTipsModel incrementTipsHistory2 = contextualTipsRepository.incrementTipsHistory((HistoricalTipsModel) stateFlowImpl2.getValue());
            stateFlowImpl2.getClass();
            stateFlowImpl2.updateState(null, incrementTipsHistory2);
            contextualTipsRepository.writeTipsHistoryToPreferences(tipType, (HistoricalTipsModel) stateFlowImpl2.getValue());
            return;
        }
        if (ordinal != 3) {
            throw new IllegalStateException(("Unsupported type " + tipType + "!").toString());
        }
        StateFlowImpl stateFlowImpl3 = contextualTipsRepository._muteVolumeTipHistory;
        HistoricalTipsModel incrementTipsHistory3 = contextualTipsRepository.incrementTipsHistory((HistoricalTipsModel) stateFlowImpl3.getValue());
        stateFlowImpl3.getClass();
        stateFlowImpl3.updateState(null, incrementTipsHistory3);
        contextualTipsRepository.writeTipsHistoryToPreferences(tipType, (HistoricalTipsModel) stateFlowImpl3.getValue());
    }

    public final HistoricalTipsModel incrementTipsHistory(HistoricalTipsModel historicalTipsModel) {
        ((SystemClockImpl) this.systemClock).getClass();
        return new HistoricalTipsModel(historicalTipsModel.count + 1, System.currentTimeMillis(), historicalTipsModel.lastTimestamp);
    }

    public final HistoricalTipsModel readTipsHistoryFromPreferences(TipType tipType) {
        HistoricalTipsModel historicalTipsModel;
        int ordinal = tipType.ordinal();
        if (ordinal == 1) {
            historicalTipsModel = new HistoricalTipsModel(this.preferences.getInt("screenshot_tip_count", 0), this.preferences.getLong("screenshot_tip_last_timestamp", 0L), this.preferences.getLong("screenshot_tip_penultimate_timestamp", 0L));
        } else {
            if (ordinal != 2) {
                if (ordinal != 3) {
                    return new HistoricalTipsModel(0, 0L, 0L);
                }
                return new HistoricalTipsModel(this.preferences.getInt("mute_volume_tip_count", 0), this.preferences.getLong("mute_volume_tip_last_timestamp", 0L), this.preferences.getLong("mute_volume_tip_penultimate_timestamp", 0L));
            }
            historicalTipsModel = new HistoricalTipsModel(this.preferences.getInt("power_off_tip_count", 0), this.preferences.getLong("power_off_tip_last_timestamp", 0L), this.preferences.getLong("power_off_tip_penultimate_timestamp", 0L));
        }
        return historicalTipsModel;
    }

    public final void writeTipsHistoryToPreferences(TipType tipType, HistoricalTipsModel historicalTipsModel) {
        SharedPreferences.Editor edit = this.preferences.edit();
        int ordinal = tipType.ordinal();
        int i = historicalTipsModel.count;
        long j = historicalTipsModel.penultimateTimestamp;
        long j2 = historicalTipsModel.lastTimestamp;
        if (ordinal == 1) {
            edit.putLong("screenshot_tip_last_timestamp", j2);
            edit.putLong("screenshot_tip_penultimate_timestamp", j);
            edit.putInt("screenshot_tip_count", i);
        } else if (ordinal == 2) {
            edit.putLong("power_off_tip_last_timestamp", j2);
            edit.putLong("power_off_tip_penultimate_timestamp", j);
            edit.putInt("power_off_tip_count", i);
        } else {
            if (ordinal != 3) {
                throw new IllegalStateException(("Unsupported type " + tipType + "!").toString());
            }
            edit.putLong("mute_volume_tip_last_timestamp", j2);
            edit.putLong("mute_volume_tip_penultimate_timestamp", j);
            edit.putInt("mute_volume_tip_count", i);
        }
        edit.apply();
    }
}
