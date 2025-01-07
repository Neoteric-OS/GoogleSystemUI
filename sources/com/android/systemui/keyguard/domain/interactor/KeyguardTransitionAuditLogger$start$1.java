package com.android.systemui.keyguard.domain.interactor;

import androidx.compose.foundation.text.input.internal.AndroidLegacyPlatformTextInputServiceAdapter$startInput$2$1$1$$ExternalSyntheticOutline0;
import com.android.app.viewcapture.data.ViewNode;
import com.android.systemui.common.shared.model.NotificationContainerBounds;
import com.android.systemui.keyguard.data.repository.ShowWhenLockedActivityInfo;
import com.android.systemui.keyguard.shared.model.BurnInModel;
import com.android.systemui.keyguard.shared.model.CameraLaunchSourceModel;
import com.android.systemui.keyguard.shared.model.DozeTransitionModel;
import com.android.systemui.log.core.LogLevel;
import com.android.systemui.power.shared.model.WakefulnessModel;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.ReadonlyStateFlow;
import kotlinx.coroutines.flow.StateFlowImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class KeyguardTransitionAuditLogger$start$1 extends SuspendLambda implements Function2 {
    int label;
    final /* synthetic */ KeyguardTransitionAuditLogger this$0;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.keyguard.domain.interactor.KeyguardTransitionAuditLogger$start$1$1, reason: invalid class name */
    public final class AnonymousClass1 implements FlowCollector {
        public final /* synthetic */ int $r8$classId;
        public final /* synthetic */ KeyguardTransitionAuditLogger this$0;

        public /* synthetic */ AnonymousClass1(KeyguardTransitionAuditLogger keyguardTransitionAuditLogger, int i) {
            this.$r8$classId = i;
            this.this$0 = keyguardTransitionAuditLogger;
        }

        @Override // kotlinx.coroutines.flow.FlowCollector
        public final Object emit(Object obj, Continuation continuation) {
            switch (this.$r8$classId) {
                case 0:
                    this.this$0.logger.log(KeyguardTransitionAuditLoggerKt.TAG, LogLevel.VERBOSE, "WakefulnessModel", (WakefulnessModel) obj);
                    break;
                case 1:
                    Boolean bool = (Boolean) obj;
                    bool.booleanValue();
                    this.this$0.logger.log(KeyguardTransitionAuditLoggerKt.TAG, LogLevel.VERBOSE, "isDreamingWithOverlay", bool);
                    break;
                case 2:
                    Boolean bool2 = (Boolean) obj;
                    bool2.booleanValue();
                    this.this$0.logger.log(KeyguardTransitionAuditLoggerKt.TAG, LogLevel.VERBOSE, "isAbleToDream", bool2);
                    break;
                case 3:
                    Boolean bool3 = (Boolean) obj;
                    bool3.booleanValue();
                    this.this$0.logger.log(KeyguardTransitionAuditLoggerKt.TAG, LogLevel.VERBOSE, "isKeyguardGoingAway", bool3);
                    break;
                case 4:
                    Boolean bool4 = (Boolean) obj;
                    bool4.booleanValue();
                    this.this$0.logger.log(KeyguardTransitionAuditLoggerKt.TAG, LogLevel.VERBOSE, "isOccluded", bool4);
                    break;
                case 5:
                    this.this$0.logger.log(KeyguardTransitionAuditLoggerKt.TAG, LogLevel.VERBOSE, "keyguardTranslationY", new Float(((Number) obj).floatValue()));
                    break;
                case 6:
                    this.this$0.logger.log(KeyguardTransitionAuditLoggerKt.TAG, LogLevel.VERBOSE, "BurnInModel (debounced)", (BurnInModel) obj);
                    break;
                case 7:
                    Boolean bool5 = (Boolean) obj;
                    bool5.booleanValue();
                    this.this$0.logger.log(KeyguardTransitionAuditLoggerKt.TAG, LogLevel.VERBOSE, "isDismissible", bool5);
                    break;
                case 8:
                    Boolean bool6 = (Boolean) obj;
                    bool6.booleanValue();
                    this.this$0.logger.log(KeyguardTransitionAuditLoggerKt.TAG, LogLevel.VERBOSE, "isShowing", bool6);
                    break;
                case 9:
                    this.this$0.logger.log(KeyguardTransitionAuditLoggerKt.TAG, LogLevel.VERBOSE, "Doze transition", (DozeTransitionModel) obj);
                    break;
                case 10:
                    this.this$0.logger.log(KeyguardTransitionAuditLoggerKt.TAG, LogLevel.VERBOSE, "onCameraLaunchDetected", (CameraLaunchSourceModel) obj);
                    break;
                case ViewNode.TRANSLATIONX_FIELD_NUMBER /* 11 */:
                    Boolean bool7 = (Boolean) obj;
                    bool7.booleanValue();
                    this.this$0.logger.log(KeyguardTransitionAuditLoggerKt.TAG, LogLevel.VERBOSE, "Notif: isOnLockscreen", bool7);
                    break;
                case ViewNode.TRANSLATIONY_FIELD_NUMBER /* 12 */:
                    this.this$0.logger.log(KeyguardTransitionAuditLoggerKt.TAG, LogLevel.VERBOSE, "showWhenLockedActivityInfo", (ShowWhenLockedActivityInfo) obj);
                    break;
                case ViewNode.SCALEX_FIELD_NUMBER /* 13 */:
                    Boolean bool8 = (Boolean) obj;
                    bool8.booleanValue();
                    this.this$0.logger.log(KeyguardTransitionAuditLoggerKt.TAG, LogLevel.VERBOSE, "Shade: isUserInteracting", bool8);
                    break;
                case ViewNode.SCALEY_FIELD_NUMBER /* 14 */:
                    this.this$0.logger.log(KeyguardTransitionAuditLoggerKt.TAG, LogLevel.VERBOSE, "Notif: bounds (debounced)", (NotificationContainerBounds) obj);
                    break;
                case 15:
                    Boolean bool9 = (Boolean) obj;
                    bool9.booleanValue();
                    this.this$0.logger.log(KeyguardTransitionAuditLoggerKt.TAG, LogLevel.VERBOSE, "Notif: isOnLockscreenWithoutShade", bool9);
                    break;
                case 16:
                    Boolean bool10 = (Boolean) obj;
                    bool10.booleanValue();
                    this.this$0.logger.log(KeyguardTransitionAuditLoggerKt.TAG, LogLevel.VERBOSE, "Primary bouncer showing", bool10);
                    break;
                case ViewNode.CLIPCHILDREN_FIELD_NUMBER /* 17 */:
                    Boolean bool11 = (Boolean) obj;
                    bool11.booleanValue();
                    this.this$0.logger.log(KeyguardTransitionAuditLoggerKt.TAG, LogLevel.VERBOSE, "Alternate bouncer showing", bool11);
                    break;
                case ViewNode.VISIBILITY_FIELD_NUMBER /* 18 */:
                    Boolean bool12 = (Boolean) obj;
                    bool12.booleanValue();
                    this.this$0.logger.log(KeyguardTransitionAuditLoggerKt.TAG, LogLevel.VERBOSE, "isDozing", bool12);
                    break;
                default:
                    Boolean bool13 = (Boolean) obj;
                    bool13.booleanValue();
                    this.this$0.logger.log(KeyguardTransitionAuditLoggerKt.TAG, LogLevel.VERBOSE, "isDreaming", bool13);
                    break;
            }
            return Unit.INSTANCE;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public KeyguardTransitionAuditLogger$start$1(KeyguardTransitionAuditLogger keyguardTransitionAuditLogger, Continuation continuation) {
        super(2, continuation);
        this.this$0 = keyguardTransitionAuditLogger;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new KeyguardTransitionAuditLogger$start$1(this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        ((KeyguardTransitionAuditLogger$start$1) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
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
        KeyguardTransitionAuditLogger keyguardTransitionAuditLogger = this.this$0;
        ReadonlyStateFlow readonlyStateFlow = keyguardTransitionAuditLogger.powerInteractor.detailedWakefulness;
        AnonymousClass1 anonymousClass1 = new AnonymousClass1(keyguardTransitionAuditLogger, 0);
        this.label = 1;
        ((StateFlowImpl) readonlyStateFlow.$$delegate_0).collect(anonymousClass1, this);
        return coroutineSingletons;
    }
}
