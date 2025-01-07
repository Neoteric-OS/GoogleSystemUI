package com.android.systemui.communal.data.db;

import android.os.UserManager;
import androidx.room.RoomDatabase;
import com.android.systemui.communal.widgets.CommunalWidgetHost;
import com.android.systemui.log.LogBuffer;
import com.android.systemui.log.core.Logger;
import dagger.internal.DelegateFactory;
import kotlin.enums.EnumEntriesKt;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class DefaultWidgetPopulation extends RoomDatabase.Callback {
    public final CoroutineScope bgScope;
    public final DelegateFactory communalWidgetDaoProvider;
    public final CommunalWidgetHost communalWidgetHost;
    public final String[] defaultWidgets;
    public final Logger logger;
    public SkipReason skipReason = SkipReason.NONE;
    public final UserManager userManager;

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class SkipReason {
        public static final /* synthetic */ SkipReason[] $VALUES;
        public static final SkipReason NONE;
        public static final SkipReason RESTORED_FROM_BACKUP;

        static {
            SkipReason skipReason = new SkipReason("NONE", 0);
            NONE = skipReason;
            SkipReason skipReason2 = new SkipReason("RESTORED_FROM_BACKUP", 1);
            RESTORED_FROM_BACKUP = skipReason2;
            SkipReason[] skipReasonArr = {skipReason, skipReason2};
            $VALUES = skipReasonArr;
            EnumEntriesKt.enumEntries(skipReasonArr);
        }

        public static SkipReason valueOf(String str) {
            return (SkipReason) Enum.valueOf(SkipReason.class, str);
        }

        public static SkipReason[] values() {
            return (SkipReason[]) $VALUES.clone();
        }
    }

    public DefaultWidgetPopulation(CoroutineScope coroutineScope, CommunalWidgetHost communalWidgetHost, DelegateFactory delegateFactory, String[] strArr, LogBuffer logBuffer, UserManager userManager) {
        this.bgScope = coroutineScope;
        this.communalWidgetHost = communalWidgetHost;
        this.communalWidgetDaoProvider = delegateFactory;
        this.defaultWidgets = strArr;
        this.userManager = userManager;
        this.logger = new Logger(logBuffer, "DefaultWidgetPopulation");
    }

    @Override // androidx.room.RoomDatabase.Callback
    public final void onCreate() {
        SkipReason skipReason = this.skipReason;
        if (skipReason == SkipReason.NONE) {
            BuildersKt.launch$default(this.bgScope, null, null, new DefaultWidgetPopulation$onCreate$1(this, null), 3);
        } else {
            Logger.i$default(this.logger, "Skipped populating default widgets. Reason: " + skipReason, null, 2, null);
        }
    }
}
