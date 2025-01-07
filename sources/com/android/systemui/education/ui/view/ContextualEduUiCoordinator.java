package com.android.systemui.education.ui.view;

import android.R;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.widget.Toast;
import com.android.systemui.CoreStartable;
import com.android.systemui.education.ui.viewmodel.ContextualEduViewModel;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class ContextualEduUiCoordinator implements CoreStartable {
    public final CoroutineScope applicationScope;
    public final Context context;
    public final Function1 createToast;
    public final NotificationManager notificationManager;
    public final ContextualEduViewModel viewModel;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.education.ui.view.ContextualEduUiCoordinator$1, reason: invalid class name */
    final class AnonymousClass1 extends Lambda implements Function1 {
        final /* synthetic */ Context $context;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(Context context) {
            super(1);
            this.$context = context;
        }

        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Object obj) {
            return Toast.makeText(this.$context, (String) obj, 1);
        }
    }

    public ContextualEduUiCoordinator(CoroutineScope coroutineScope, Context context, ContextualEduViewModel contextualEduViewModel, NotificationManager notificationManager) {
        AnonymousClass1 anonymousClass1 = new AnonymousClass1(context);
        this.applicationScope = coroutineScope;
        this.viewModel = contextualEduViewModel;
        this.context = context;
        this.notificationManager = notificationManager;
        this.createToast = anonymousClass1;
    }

    @Override // com.android.systemui.CoreStartable
    public final void start() {
        this.notificationManager.createNotificationChannel(new NotificationChannel("ContextualEduNotificationChannel", this.context.getString(R.string.android_upgrading_complete), 2));
        BuildersKt.launch$default(this.applicationScope, null, null, new ContextualEduUiCoordinator$start$1(this, null), 3);
    }
}
