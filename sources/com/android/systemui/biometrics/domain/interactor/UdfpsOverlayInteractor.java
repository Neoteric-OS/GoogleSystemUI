package com.android.systemui.biometrics.domain.interactor;

import android.content.Context;
import android.hardware.fingerprint.FingerprintManager;
import android.hardware.fingerprint.FingerprintSensorPropertiesInternal;
import android.util.Log;
import com.android.systemui.biometrics.AuthContainerView$$ExternalSyntheticOutline0;
import com.android.systemui.biometrics.AuthController;
import com.android.systemui.biometrics.shared.model.UdfpsOverlayParams;
import com.android.systemui.user.domain.interactor.SelectedUserInteractor;
import com.android.systemui.utils.coroutines.flow.FlowConflatedKt;
import com.android.wm.shell.R;
import java.util.List;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.ReadonlyStateFlow;
import kotlinx.coroutines.flow.SharingStarted;
import kotlinx.coroutines.flow.StateFlowImpl;
import kotlinx.coroutines.flow.StateFlowKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class UdfpsOverlayInteractor {
    public final StateFlowImpl _requestId;
    public final StateFlowImpl _shouldHandleTouches;
    public final AuthController authController;
    public final FingerprintManager fingerprintManager;
    public final UdfpsOverlayInteractor$special$$inlined$map$1 iconPadding;
    public final int iconSize;
    public final ReadonlyStateFlow requestId;
    public final ReadonlyStateFlow shouldHandleTouches;
    public final ReadonlyStateFlow udfpsOverlayParams;

    /* JADX WARN: Type inference failed for: r2v7, types: [com.android.systemui.biometrics.domain.interactor.UdfpsOverlayInteractor$special$$inlined$map$1] */
    public UdfpsOverlayInteractor(Context context, AuthController authController, SelectedUserInteractor selectedUserInteractor, FingerprintManager fingerprintManager, CoroutineScope coroutineScope) {
        this.authController = authController;
        this.fingerprintManager = fingerprintManager;
        float f = context.getResources().getFloat(R.dimen.pixel_pitch);
        if (f <= 0.0f) {
            Log.e("UdfpsOverlayInteractor", "invalid pixelPitch: " + f + ". Pixel pitch must be updated per device.");
        }
        this.iconSize = (int) (context.getResources().getFloat(R.dimen.udfps_icon_size) / f);
        StateFlowImpl MutableStateFlow = StateFlowKt.MutableStateFlow(0L);
        this._requestId = MutableStateFlow;
        this.requestId = new ReadonlyStateFlow(MutableStateFlow);
        StateFlowImpl MutableStateFlow2 = StateFlowKt.MutableStateFlow(Boolean.TRUE);
        this._shouldHandleTouches = MutableStateFlow2;
        this.shouldHandleTouches = new ReadonlyStateFlow(MutableStateFlow2);
        final ReadonlyStateFlow stateIn = FlowKt.stateIn(FlowConflatedKt.conflatedCallbackFlow(new UdfpsOverlayInteractor$udfpsOverlayParams$1(this, null)), coroutineScope, SharingStarted.Companion.Eagerly, new UdfpsOverlayParams());
        this.udfpsOverlayParams = stateIn;
        this.iconPadding = new Flow() { // from class: com.android.systemui.biometrics.domain.interactor.UdfpsOverlayInteractor$special$$inlined$map$1

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.systemui.biometrics.domain.interactor.UdfpsOverlayInteractor$special$$inlined$map$1$2, reason: invalid class name */
            public final class AnonymousClass2 implements FlowCollector {
                public final /* synthetic */ FlowCollector $this_unsafeFlow;
                public final /* synthetic */ UdfpsOverlayInteractor this$0;

                /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                /* renamed from: com.android.systemui.biometrics.domain.interactor.UdfpsOverlayInteractor$special$$inlined$map$1$2$1, reason: invalid class name */
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

                public AnonymousClass2(FlowCollector flowCollector, UdfpsOverlayInteractor udfpsOverlayInteractor) {
                    this.$this_unsafeFlow = flowCollector;
                    this.this$0 = udfpsOverlayInteractor;
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
                        boolean r0 = r6 instanceof com.android.systemui.biometrics.domain.interactor.UdfpsOverlayInteractor$special$$inlined$map$1.AnonymousClass2.AnonymousClass1
                        if (r0 == 0) goto L13
                        r0 = r6
                        com.android.systemui.biometrics.domain.interactor.UdfpsOverlayInteractor$special$$inlined$map$1$2$1 r0 = (com.android.systemui.biometrics.domain.interactor.UdfpsOverlayInteractor$special$$inlined$map$1.AnonymousClass2.AnonymousClass1) r0
                        int r1 = r0.label
                        r2 = -2147483648(0xffffffff80000000, float:-0.0)
                        r3 = r1 & r2
                        if (r3 == 0) goto L13
                        int r1 = r1 - r2
                        r0.label = r1
                        goto L18
                    L13:
                        com.android.systemui.biometrics.domain.interactor.UdfpsOverlayInteractor$special$$inlined$map$1$2$1 r0 = new com.android.systemui.biometrics.domain.interactor.UdfpsOverlayInteractor$special$$inlined$map$1$2$1
                        r0.<init>(r6)
                    L18:
                        java.lang.Object r6 = r0.result
                        kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
                        int r2 = r0.label
                        r3 = 1
                        if (r2 == 0) goto L2f
                        if (r2 != r3) goto L27
                        kotlin.ResultKt.throwOnFailure(r6)
                        goto L57
                    L27:
                        java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
                        java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
                        r4.<init>(r5)
                        throw r4
                    L2f:
                        kotlin.ResultKt.throwOnFailure(r6)
                        com.android.systemui.biometrics.shared.model.UdfpsOverlayParams r5 = (com.android.systemui.biometrics.shared.model.UdfpsOverlayParams) r5
                        android.graphics.Rect r6 = r5.nativeSensorBounds
                        int r2 = r6.right
                        int r6 = r6.left
                        int r2 = r2 - r6
                        com.android.systemui.biometrics.domain.interactor.UdfpsOverlayInteractor r6 = r4.this$0
                        int r6 = r6.iconSize
                        int r2 = r2 - r6
                        int r2 = r2 / 2
                        float r6 = (float) r2
                        float r5 = r5.scaleFactor
                        float r6 = r6 * r5
                        int r5 = (int) r6
                        java.lang.Integer r6 = new java.lang.Integer
                        r6.<init>(r5)
                        r0.label = r3
                        kotlinx.coroutines.flow.FlowCollector r4 = r4.$this_unsafeFlow
                        java.lang.Object r4 = r4.emit(r6, r0)
                        if (r4 != r1) goto L57
                        return r1
                    L57:
                        kotlin.Unit r4 = kotlin.Unit.INSTANCE
                        return r4
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.biometrics.domain.interactor.UdfpsOverlayInteractor$special$$inlined$map$1.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                }
            }

            @Override // kotlinx.coroutines.flow.Flow
            public final Object collect(FlowCollector flowCollector, Continuation continuation) {
                ReadonlyStateFlow.this.collect(new AnonymousClass2(flowCollector, this), continuation);
                return CoroutineSingletons.COROUTINE_SUSPENDED;
            }
        };
    }

    public final void setHandleTouches(boolean z) {
        FingerprintManager fingerprintManager;
        AuthController authController = this.authController;
        boolean isUdfpsSupported = authController.isUdfpsSupported();
        StateFlowImpl stateFlowImpl = this._shouldHandleTouches;
        if (isUdfpsSupported && z != ((Boolean) stateFlowImpl.getValue()).booleanValue() && (fingerprintManager = this.fingerprintManager) != null) {
            long longValue = ((Number) this.requestId.getValue()).longValue();
            List list = authController.mUdfpsProps;
            Intrinsics.checkNotNull(list);
            fingerprintManager.setIgnoreDisplayTouches(longValue, ((FingerprintSensorPropertiesInternal) list.get(0)).sensorId, !z);
        }
        AuthContainerView$$ExternalSyntheticOutline0.m(z, stateFlowImpl, null);
    }
}
