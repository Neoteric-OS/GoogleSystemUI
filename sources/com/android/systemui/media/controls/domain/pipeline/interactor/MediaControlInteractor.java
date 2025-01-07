package com.android.systemui.media.controls.domain.pipeline.interactor;

import android.app.BroadcastOptions;
import android.app.PendingIntent;
import android.content.Intent;
import android.util.Log;
import com.android.internal.logging.InstanceId;
import com.android.systemui.ActivityIntentHelper;
import com.android.systemui.animation.Expandable;
import com.android.systemui.bluetooth.BroadcastDialogController;
import com.android.systemui.media.controls.data.repository.MediaFilterRepository;
import com.android.systemui.media.controls.domain.pipeline.MediaDataProcessor;
import com.android.systemui.media.controls.shared.MediaLogger;
import com.android.systemui.media.controls.util.MediaSmartspaceLogger;
import com.android.systemui.media.dialog.MediaOutputDialogManager;
import com.android.systemui.plugins.ActivityStarter;
import com.android.systemui.statusbar.NotificationLockscreenUserManager;
import com.android.systemui.statusbar.NotificationLockscreenUserManagerImpl;
import com.android.systemui.statusbar.policy.KeyguardStateController;
import com.android.systemui.statusbar.policy.KeyguardStateControllerImpl;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.ReadonlyStateFlow;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class MediaControlInteractor {
    public static final Intent SETTINGS_INTENT = new Intent("android.settings.ACTION_MEDIA_CONTROLS_SETTINGS");
    public final ActivityIntentHelper activityIntentHelper;
    public final ActivityStarter activityStarter;
    public final BroadcastDialogController broadcastDialogController;
    public final InstanceId instanceId;
    public final KeyguardStateController keyguardStateController;
    public final NotificationLockscreenUserManager lockscreenUserManager;
    public final Flow mediaControl;
    public final MediaDataProcessor mediaDataProcessor;
    public final MediaLogger mediaLogger;
    public final MediaOutputDialogManager mediaOutputDialogManager;
    public final Flow onAnyMediaConfigurationChange;
    public final MediaFilterRepository repository;

    public MediaControlInteractor(InstanceId instanceId, MediaFilterRepository mediaFilterRepository, MediaDataProcessor mediaDataProcessor, KeyguardStateController keyguardStateController, ActivityStarter activityStarter, ActivityIntentHelper activityIntentHelper, NotificationLockscreenUserManager notificationLockscreenUserManager, MediaOutputDialogManager mediaOutputDialogManager, BroadcastDialogController broadcastDialogController, MediaLogger mediaLogger) {
        this.instanceId = instanceId;
        this.repository = mediaFilterRepository;
        this.mediaDataProcessor = mediaDataProcessor;
        this.keyguardStateController = keyguardStateController;
        this.activityStarter = activityStarter;
        this.activityIntentHelper = activityIntentHelper;
        this.lockscreenUserManager = notificationLockscreenUserManager;
        this.mediaOutputDialogManager = mediaOutputDialogManager;
        this.broadcastDialogController = broadcastDialogController;
        final ReadonlyStateFlow readonlyStateFlow = mediaFilterRepository.selectedUserEntries;
        this.mediaControl = FlowKt.distinctUntilChanged(new Flow() { // from class: com.android.systemui.media.controls.domain.pipeline.interactor.MediaControlInteractor$special$$inlined$map$1

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.systemui.media.controls.domain.pipeline.interactor.MediaControlInteractor$special$$inlined$map$1$2, reason: invalid class name */
            public final class AnonymousClass2 implements FlowCollector {
                public final /* synthetic */ FlowCollector $this_unsafeFlow;
                public final /* synthetic */ MediaControlInteractor this$0;

                /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                /* renamed from: com.android.systemui.media.controls.domain.pipeline.interactor.MediaControlInteractor$special$$inlined$map$1$2$1, reason: invalid class name */
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

                public AnonymousClass2(FlowCollector flowCollector, MediaControlInteractor mediaControlInteractor) {
                    this.$this_unsafeFlow = flowCollector;
                    this.this$0 = mediaControlInteractor;
                }

                /* JADX WARN: Removed duplicated region for block: B:15:0x0034  */
                /* JADX WARN: Removed duplicated region for block: B:8:0x0025  */
                @Override // kotlinx.coroutines.flow.FlowCollector
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct add '--show-bad-code' argument
                */
                public final java.lang.Object emit(java.lang.Object r27, kotlin.coroutines.Continuation r28) {
                    /*
                        r26 = this;
                        r0 = r26
                        r1 = r28
                        boolean r2 = r1 instanceof com.android.systemui.media.controls.domain.pipeline.interactor.MediaControlInteractor$special$$inlined$map$1.AnonymousClass2.AnonymousClass1
                        if (r2 == 0) goto L17
                        r2 = r1
                        com.android.systemui.media.controls.domain.pipeline.interactor.MediaControlInteractor$special$$inlined$map$1$2$1 r2 = (com.android.systemui.media.controls.domain.pipeline.interactor.MediaControlInteractor$special$$inlined$map$1.AnonymousClass2.AnonymousClass1) r2
                        int r3 = r2.label
                        r4 = -2147483648(0xffffffff80000000, float:-0.0)
                        r5 = r3 & r4
                        if (r5 == 0) goto L17
                        int r3 = r3 - r4
                        r2.label = r3
                        goto L1c
                    L17:
                        com.android.systemui.media.controls.domain.pipeline.interactor.MediaControlInteractor$special$$inlined$map$1$2$1 r2 = new com.android.systemui.media.controls.domain.pipeline.interactor.MediaControlInteractor$special$$inlined$map$1$2$1
                        r2.<init>(r1)
                    L1c:
                        java.lang.Object r1 = r2.result
                        kotlin.coroutines.intrinsics.CoroutineSingletons r3 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
                        int r4 = r2.label
                        r5 = 1
                        if (r4 == 0) goto L34
                        if (r4 != r5) goto L2c
                        kotlin.ResultKt.throwOnFailure(r1)
                        goto L9b
                    L2c:
                        java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
                        java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
                        r0.<init>(r1)
                        throw r0
                    L34:
                        kotlin.ResultKt.throwOnFailure(r1)
                        r1 = r27
                        java.util.Map r1 = (java.util.Map) r1
                        com.android.systemui.media.controls.domain.pipeline.interactor.MediaControlInteractor r4 = r0.this$0
                        com.android.internal.logging.InstanceId r6 = r4.instanceId
                        java.lang.Object r1 = r1.get(r6)
                        com.android.systemui.media.controls.shared.model.MediaData r1 = (com.android.systemui.media.controls.shared.model.MediaData) r1
                        if (r1 == 0) goto L8d
                        com.android.systemui.media.controls.shared.model.MediaControlModel r25 = new com.android.systemui.media.controls.shared.model.MediaControlModel
                        com.android.internal.logging.InstanceId r9 = r1.instanceId
                        android.media.session.MediaSession$Token r10 = r1.token
                        android.graphics.drawable.Icon r11 = r1.appIcon
                        android.app.PendingIntent r12 = r1.clickIntent
                        java.lang.CharSequence r14 = r1.song
                        java.lang.CharSequence r15 = r1.artist
                        android.graphics.drawable.Icon r6 = r1.artwork
                        java.util.List r7 = r1.actions
                        com.android.systemui.plugins.ActivityStarter r4 = r4.activityStarter
                        java.util.List r20 = com.android.systemui.media.controls.domain.pipeline.MediaActionsKt.getNotificationActions(r7, r4)
                        java.util.List r4 = r1.actionsToShowInCompact
                        boolean r7 = r1.resumption
                        r23 = r7
                        java.lang.Double r7 = r1.resumeProgress
                        r24 = r7
                        int r7 = r1.appUid
                        java.lang.String r8 = r1.packageName
                        java.lang.String r13 = r1.app
                        boolean r5 = r1.isExplicit
                        r16 = r5
                        com.android.systemui.media.controls.shared.model.MediaDeviceData r5 = r1.device
                        r18 = r5
                        com.android.systemui.media.controls.shared.model.MediaButton r5 = r1.semanticActions
                        r19 = r5
                        boolean r1 = r1.isClearable
                        r22 = r1
                        r1 = r6
                        r6 = r25
                        r17 = r1
                        r21 = r4
                        r6.<init>(r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18, r19, r20, r21, r22, r23, r24)
                    L89:
                        r1 = r25
                        r4 = 1
                        goto L90
                    L8d:
                        r25 = 0
                        goto L89
                    L90:
                        r2.label = r4
                        kotlinx.coroutines.flow.FlowCollector r0 = r0.$this_unsafeFlow
                        java.lang.Object r0 = r0.emit(r1, r2)
                        if (r0 != r3) goto L9b
                        return r3
                    L9b:
                        kotlin.Unit r0 = kotlin.Unit.INSTANCE
                        return r0
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.media.controls.domain.pipeline.interactor.MediaControlInteractor$special$$inlined$map$1.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                }
            }

            @Override // kotlinx.coroutines.flow.Flow
            public final Object collect(FlowCollector flowCollector, Continuation continuation) {
                ReadonlyStateFlow.this.collect(new AnonymousClass2(flowCollector, this), continuation);
                return CoroutineSingletons.COROUTINE_SUSPENDED;
            }
        });
        this.onAnyMediaConfigurationChange = mediaFilterRepository.onAnyMediaConfigurationChange;
    }

    public final boolean launchOverLockscreen(Expandable expandable, PendingIntent pendingIntent) {
        if (!((KeyguardStateControllerImpl) this.keyguardStateController).mShowing) {
            return false;
        }
        if (!this.activityIntentHelper.wouldPendingShowOverLockscreen(pendingIntent, ((NotificationLockscreenUserManagerImpl) this.lockscreenUserManager).mCurrentUserId)) {
            return false;
        }
        try {
            if (expandable != null) {
                this.activityStarter.startPendingIntentMaybeDismissingKeyguard(pendingIntent, null, expandable.activityTransitionController(31));
            } else {
                BroadcastOptions makeBasic = BroadcastOptions.makeBasic();
                makeBasic.setInteractive(true);
                makeBasic.setPendingIntentBackgroundActivityStartMode(1);
                pendingIntent.send(makeBasic.toBundle());
            }
        } catch (PendingIntent.CanceledException unused) {
            Log.e("MediaControlInteractor", "pending intent of " + this.instanceId + " was canceled");
        }
        return true;
    }

    public final void logSmartspaceUserEvent(int i, int i2) {
        boolean z = MediaSmartspaceLogger.DEBUG;
        MediaFilterRepository.logSmartspaceCardUserEvent$default(this.repository, i, MediaSmartspaceLogger.Companion.getSurface(i2), 0, this.instanceId, 44);
    }
}
