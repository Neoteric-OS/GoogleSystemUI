package com.android.systemui.statusbar.notification.domain.interactor;

import android.os.Trace;
import com.android.app.tracing.TraceUtilsKt;
import com.android.systemui.statusbar.notification.collection.provider.SectionStyleProvider;
import com.android.systemui.statusbar.notification.data.repository.ActiveNotificationListRepository;
import com.android.systemui.statusbar.notification.data.repository.ActiveNotificationsStore;
import java.util.List;
import kotlin.jvm.functions.Function1;
import kotlinx.coroutines.flow.StateFlowImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class RenderNotificationListInteractor {
    public final ActiveNotificationListRepository repository;
    public final SectionStyleProvider sectionStyleProvider;

    public RenderNotificationListInteractor(ActiveNotificationListRepository activeNotificationListRepository, SectionStyleProvider sectionStyleProvider) {
        this.repository = activeNotificationListRepository;
        this.sectionStyleProvider = sectionStyleProvider;
    }

    public final void setRenderedList(final List list) {
        Object value;
        ActiveNotificationsStore.Builder builder;
        boolean isEnabled = Trace.isEnabled();
        if (isEnabled) {
            TraceUtilsKt.beginSlice("RenderNotificationListInteractor.setRenderedList");
        }
        try {
            StateFlowImpl stateFlowImpl = this.repository.activeNotifications;
            do {
                value = stateFlowImpl.getValue();
                SectionStyleProvider sectionStyleProvider = this.sectionStyleProvider;
                Function1 function1 = new Function1() { // from class: com.android.systemui.statusbar.notification.domain.interactor.RenderNotificationListInteractor$setRenderedList$1$1$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    /* JADX WARN: Code restructure failed: missing block: B:31:0x0075, code lost:
                    
                        if (r4 == null) goto L25;
                     */
                    @Override // kotlin.jvm.functions.Function1
                    /*
                        Code decompiled incorrectly, please refer to instructions dump.
                        To view partially-correct add '--show-bad-code' argument
                    */
                    public final java.lang.Object invoke(java.lang.Object r8) {
                        /*
                            Method dump skipped, instructions count: 324
                            To view this dump add '--comments-level debug' option
                        */
                        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.statusbar.notification.domain.interactor.RenderNotificationListInteractor$setRenderedList$1$1$1.invoke(java.lang.Object):java.lang.Object");
                    }
                };
                ActiveNotificationsStoreBuilder activeNotificationsStoreBuilder = new ActiveNotificationsStoreBuilder((ActiveNotificationsStore) value, sectionStyleProvider);
                function1.invoke(activeNotificationsStoreBuilder);
                builder = activeNotificationsStoreBuilder.builder;
            } while (!stateFlowImpl.compareAndSet(value, new ActiveNotificationsStore(builder.groups, builder.individuals, builder.renderList, builder.rankingsMap)));
        } finally {
            if (isEnabled) {
                TraceUtilsKt.endSlice();
            }
        }
    }
}
