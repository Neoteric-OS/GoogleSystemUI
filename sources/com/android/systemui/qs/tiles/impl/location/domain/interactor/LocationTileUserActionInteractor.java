package com.android.systemui.qs.tiles.impl.location.domain.interactor;

import android.content.Intent;
import com.android.app.tracing.coroutines.TraceContextElementKt;
import com.android.app.tracing.coroutines.TraceDataThreadLocal;
import com.android.systemui.plugins.ActivityStarter;
import com.android.systemui.qs.tiles.base.actions.QSTileIntentUserInputHandlerImpl;
import com.android.systemui.qs.tiles.base.interactor.QSTileInput;
import com.android.systemui.qs.tiles.base.interactor.QSTileUserActionInteractor;
import com.android.systemui.qs.tiles.impl.location.domain.model.LocationTileModel;
import com.android.systemui.qs.tiles.viewmodel.QSTileUserAction;
import com.android.systemui.statusbar.policy.KeyguardStateController;
import com.android.systemui.statusbar.policy.KeyguardStateControllerImpl;
import com.android.systemui.statusbar.policy.LocationController;
import com.android.systemui.statusbar.policy.LocationControllerImpl;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class LocationTileUserActionInteractor implements QSTileUserActionInteractor {
    public final ActivityStarter activityStarter;
    public final CoroutineScope applicationScope;
    public final CoroutineContext coroutineContext;
    public final KeyguardStateController keyguardController;
    public final LocationController locationController;
    public final QSTileIntentUserInputHandlerImpl qsTileIntentUserActionHandler;

    public LocationTileUserActionInteractor(CoroutineContext coroutineContext, CoroutineScope coroutineScope, LocationController locationController, QSTileIntentUserInputHandlerImpl qSTileIntentUserInputHandlerImpl, ActivityStarter activityStarter, KeyguardStateController keyguardStateController) {
        this.coroutineContext = coroutineContext;
        this.applicationScope = coroutineScope;
        this.locationController = locationController;
        this.qsTileIntentUserActionHandler = qSTileIntentUserInputHandlerImpl;
        this.activityStarter = activityStarter;
        this.keyguardController = keyguardStateController;
    }

    @Override // com.android.systemui.qs.tiles.base.interactor.QSTileUserActionInteractor
    public final Object handleInput(QSTileInput qSTileInput, Continuation continuation) {
        QSTileUserAction qSTileUserAction = qSTileInput.action;
        if (qSTileUserAction instanceof QSTileUserAction.Click) {
            LocationTileModel locationTileModel = (LocationTileModel) qSTileInput.data;
            KeyguardStateControllerImpl keyguardStateControllerImpl = (KeyguardStateControllerImpl) this.keyguardController;
            boolean z = keyguardStateControllerImpl.mSecure;
            final boolean z2 = locationTileModel.isEnabled;
            if (z && keyguardStateControllerImpl.mShowing) {
                this.activityStarter.postQSRunnableDismissingKeyguard(new Runnable() { // from class: com.android.systemui.qs.tiles.impl.location.domain.interactor.LocationTileUserActionInteractor$handleInput$2$1

                    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                    /* renamed from: com.android.systemui.qs.tiles.impl.location.domain.interactor.LocationTileUserActionInteractor$handleInput$2$1$1, reason: invalid class name */
                    final class AnonymousClass1 extends SuspendLambda implements Function2 {
                        final /* synthetic */ boolean $wasEnabled;
                        int label;
                        final /* synthetic */ LocationTileUserActionInteractor this$0;

                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        public AnonymousClass1(LocationTileUserActionInteractor locationTileUserActionInteractor, boolean z, Continuation continuation) {
                            super(2, continuation);
                            this.this$0 = locationTileUserActionInteractor;
                            this.$wasEnabled = z;
                        }

                        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                        public final Continuation create(Object obj, Continuation continuation) {
                            return new AnonymousClass1(this.this$0, this.$wasEnabled, continuation);
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
                            ((LocationControllerImpl) this.this$0.locationController).setLocationEnabled(!this.$wasEnabled);
                            return Unit.INSTANCE;
                        }
                    }

                    @Override // java.lang.Runnable
                    public final void run() {
                        CoroutineContext coroutineContext = LocationTileUserActionInteractor.this.applicationScope.getCoroutineContext();
                        TraceDataThreadLocal traceDataThreadLocal = TraceContextElementKt.traceThreadLocal;
                        BuildersKt.launch$default(CoroutineScopeKt.CoroutineScope(coroutineContext.plus(EmptyCoroutineContext.INSTANCE)), null, null, new AnonymousClass1(LocationTileUserActionInteractor.this, z2, null), 3);
                    }
                });
            } else {
                Object withContext = BuildersKt.withContext(this.coroutineContext, new LocationTileUserActionInteractor$handleInput$2$2(this, z2, null), continuation);
                if (withContext == CoroutineSingletons.COROUTINE_SUSPENDED) {
                    return withContext;
                }
            }
        } else if (qSTileUserAction instanceof QSTileUserAction.LongClick) {
            QSTileIntentUserInputHandlerImpl.handle$default(this.qsTileIntentUserActionHandler, ((QSTileUserAction.LongClick) qSTileUserAction).expandable, new Intent("android.settings.LOCATION_SOURCE_SETTINGS"));
        } else {
            boolean z3 = qSTileUserAction instanceof QSTileUserAction.ToggleClick;
        }
        return Unit.INSTANCE;
    }
}
