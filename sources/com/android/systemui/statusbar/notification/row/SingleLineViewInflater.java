package com.android.systemui.statusbar.notification.row;

import android.app.Notification;
import android.app.Person;
import android.content.Context;
import android.graphics.drawable.Icon;
import android.os.Trace;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.room.TriggerBasedInvalidationTracker$$ExternalSyntheticOutline0;
import com.android.app.tracing.TraceUtilsKt;
import com.android.internal.widget.MessagingMessage;
import com.android.internal.widget.PeopleHelper;
import com.android.systemui.log.LogBuffer;
import com.android.systemui.log.LogMessageImpl;
import com.android.systemui.log.core.LogLevel;
import com.android.systemui.log.core.LogMessage;
import com.android.systemui.statusbar.notification.NotificationUtilsKt;
import com.android.systemui.statusbar.notification.collection.NotificationEntry;
import com.android.systemui.statusbar.notification.row.NotificationRowContentBinderLogger;
import com.android.systemui.statusbar.notification.row.ui.viewmodel.ConversationAvatar;
import com.android.systemui.statusbar.notification.row.ui.viewmodel.ConversationData;
import com.android.systemui.statusbar.notification.row.ui.viewmodel.FacePile;
import com.android.systemui.statusbar.notification.row.ui.viewmodel.SingleIcon;
import com.android.systemui.statusbar.notification.row.ui.viewmodel.SingleLineViewModel;
import com.android.wm.shell.R;
import java.util.ArrayList;
import java.util.List;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.EmptyList;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class SingleLineViewInflater {
    public static final SingleLineViewInflater INSTANCE = null;
    public static final PeopleHelper peopleHelper = new PeopleHelper();

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class ConversationTextData {
        public final CharSequence conversationText;
        public final CharSequence conversationTitle;
        public final CharSequence senderName;

        public ConversationTextData(CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3) {
            this.conversationTitle = charSequence;
            this.conversationText = charSequence2;
            this.senderName = charSequence3;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof ConversationTextData)) {
                return false;
            }
            ConversationTextData conversationTextData = (ConversationTextData) obj;
            return Intrinsics.areEqual(this.conversationTitle, conversationTextData.conversationTitle) && Intrinsics.areEqual(this.conversationText, conversationTextData.conversationText) && Intrinsics.areEqual(this.senderName, conversationTextData.senderName);
        }

        public final int hashCode() {
            int hashCode = this.conversationTitle.hashCode() * 31;
            CharSequence charSequence = this.conversationText;
            int hashCode2 = (hashCode + (charSequence == null ? 0 : charSequence.hashCode())) * 31;
            CharSequence charSequence2 = this.senderName;
            return hashCode2 + (charSequence2 != null ? charSequence2.hashCode() : 0);
        }

        public final String toString() {
            return "ConversationTextData(conversationTitle=" + ((Object) this.conversationTitle) + ", conversationText=" + ((Object) this.conversationText) + ", senderName=" + ((Object) this.senderName) + ")";
        }
    }

    public static Icon getDefaultAvatar(Notification.Builder builder, CharSequence charSequence, PeopleHelper.NameToPrefixMap nameToPrefixMap) {
        int smallIconColor = builder.getSmallIconColor(false);
        if (charSequence == null || charSequence.length() == 0) {
            return peopleHelper.createAvatarSymbol("", "", smallIconColor);
        }
        String prefix = nameToPrefixMap != null ? nameToPrefixMap.getPrefix(charSequence) : null;
        return peopleHelper.createAvatarSymbol(charSequence, prefix != null ? prefix : "", smallIconColor);
    }

    public static CharSequence getKeyOrName(Person person) {
        return person.getKey() == null ? person.getName() : person.getKey();
    }

    public static final HybridNotificationView inflatePrivateSingleLineView(boolean z, int i, NotificationEntry notificationEntry, Context context, NotificationRowContentBinderLogger notificationRowContentBinderLogger) {
        HybridNotificationView hybridNotificationView = null;
        if ((i & 16) != 0) {
            notificationRowContentBinderLogger.getClass();
            LogLevel logLevel = LogLevel.DEBUG;
            NotificationRowContentBinderLogger$logInflateSingleLine$2 notificationRowContentBinderLogger$logInflateSingleLine$2 = new Function1() { // from class: com.android.systemui.statusbar.notification.row.NotificationRowContentBinderLogger$logInflateSingleLine$2
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    LogMessage logMessage = (LogMessage) obj;
                    String flagToString = NotificationRowContentBinderLogger.Companion.flagToString(logMessage.getInt1());
                    String str1 = logMessage.getStr1();
                    boolean bool1 = logMessage.getBool1();
                    StringBuilder m = TriggerBasedInvalidationTracker$$ExternalSyntheticOutline0.m("inflateSingleLineView, inflationFlags: ", flagToString, " for ", str1, ", isConversation: ");
                    m.append(bool1);
                    return m.toString();
                }
            };
            LogBuffer logBuffer = notificationRowContentBinderLogger.buffer;
            LogMessage obtain = logBuffer.obtain("NotificationRowContentBinder", logLevel, notificationRowContentBinderLogger$logInflateSingleLine$2, null);
            LogMessageImpl logMessageImpl = (LogMessageImpl) obtain;
            logMessageImpl.str1 = NotificationUtilsKt.getLogKey(notificationEntry);
            logMessageImpl.int1 = i;
            logMessageImpl.bool1 = z;
            logBuffer.commit(obtain);
            notificationRowContentBinderLogger.logAsyncTaskProgress(notificationEntry, "inflating single-line content view");
            boolean isEnabled = Trace.isEnabled();
            if (isEnabled) {
                TraceUtilsKt.beginSlice("SingleLineViewInflater#inflateSingleLineView");
            }
            try {
                hybridNotificationView = (HybridNotificationView) LayoutInflater.from(context).inflate(z ? R.layout.hybrid_conversation_notification : R.layout.hybrid_notification, (ViewGroup) null);
            } finally {
                if (isEnabled) {
                    TraceUtilsKt.endSlice();
                }
            }
        }
        return hybridNotificationView;
    }

    public static final SingleLineViewModel inflateSingleLineViewModel(Notification notification, Notification.MessagingStyle messagingStyle, Notification.Builder builder, Context context) {
        CharSequence name;
        ConversationTextData conversationTextData;
        List list;
        List<Notification.MessagingStyle.Message> list2;
        Notification.MessagingStyle.Message message;
        List<Notification.MessagingStyle.Message> list3;
        CharSequence charSequence;
        ArrayList arrayList;
        CharSequence charSequence2;
        ConversationAvatar singleIcon;
        Icon icon;
        CharSequence charSequence3;
        CharSequence charSequence4;
        peopleHelper.init(context);
        CharSequence resolveTitle = HybridGroupManager.resolveTitle(notification);
        CharSequence resolveText = HybridGroupManager.resolveText(notification);
        CharSequence charSequence5 = null;
        if (messagingStyle == null) {
            return new SingleLineViewModel(resolveTitle, resolveText, null);
        }
        boolean isGroupConversation = messagingStyle.isGroupConversation();
        if (messagingStyle.getMessages().isEmpty()) {
            conversationTextData = null;
        } else {
            Notification.MessagingStyle.Message message2 = messagingStyle.getMessages().get(CollectionsKt__CollectionsKt.getLastIndex(messagingStyle.getMessages()));
            CharSequence text = message2.getText();
            if (text == null && MessagingMessage.hasImage(message2)) {
                text = !MessagingMessage.hasImage(message2) ? null : context.getResources().getString(android.R.string.csd_momentary_exposure_warning);
            }
            Person senderPerson = message2.getSenderPerson();
            if (senderPerson == null || (name = senderPerson.getName()) == null) {
                name = messagingStyle.getUser().getName();
            }
            String string = context.getResources().getString(android.R.string.data_saver_description, name != null ? name.toString() : null);
            CharSequence conversationTitle = messagingStyle.getConversationTitle();
            if (conversationTitle == null) {
                if (messagingStyle.isGroupConversation()) {
                    conversationTitle = context.getResources().getString(android.R.string.data_saver_enable_button);
                    Intrinsics.checkNotNull(conversationTitle);
                } else {
                    conversationTitle = string;
                }
            }
            conversationTextData = new ConversationTextData(conversationTitle, text, string);
        }
        if (conversationTextData != null && (charSequence4 = conversationTextData.conversationTitle) != null && charSequence4.length() > 0) {
            resolveTitle = conversationTextData.conversationTitle;
        }
        if (conversationTextData != null && (charSequence3 = conversationTextData.conversationText) != null && charSequence3.length() > 0) {
            resolveText = conversationTextData.conversationText;
        }
        CharSequence keyOrName = getKeyOrName(messagingStyle.getUser());
        Icon shortcutIcon = messagingStyle.getShortcutIcon();
        CharSequence conversationTitle2 = messagingStyle.getConversationTitle();
        List<Notification.MessagingStyle.Message> messages = messagingStyle.getMessages();
        List<Notification.MessagingStyle.Message> historicMessages = messagingStyle.getHistoricMessages();
        if (messages.isEmpty() && historicMessages.isEmpty()) {
            list = EmptyList.INSTANCE;
        } else {
            ArrayList arrayList2 = new ArrayList();
            int size = historicMessages.size();
            int size2 = messages.size() + size;
            ArrayList arrayList3 = null;
            int i = 0;
            while (i < size2) {
                if (i < size) {
                    list2 = historicMessages;
                    message = historicMessages.get(i);
                } else {
                    list2 = historicMessages;
                    message = messages.get(i - size);
                }
                Person senderPerson2 = message.getSenderPerson();
                if (senderPerson2 != null) {
                    CharSequence keyOrName2 = getKeyOrName(senderPerson2);
                    list3 = messages;
                    charSequence = keyOrName2;
                } else {
                    list3 = messages;
                    charSequence = null;
                }
                if (arrayList3 == null || !Intrinsics.areEqual(charSequence, charSequence5)) {
                    ArrayList arrayList4 = new ArrayList();
                    arrayList2.add(arrayList4);
                    CharSequence charSequence6 = charSequence;
                    arrayList = arrayList4;
                    charSequence5 = charSequence6;
                } else {
                    arrayList = arrayList3;
                }
                arrayList.add(message);
                i++;
                arrayList3 = arrayList;
                historicMessages = list2;
                messages = list3;
            }
            list = arrayList2;
        }
        PeopleHelper.NameToPrefixMap mapUniqueNamesToPrefixWithGroupList = peopleHelper.mapUniqueNamesToPrefixWithGroupList(list);
        if (!isGroupConversation) {
            for (int lastIndex = CollectionsKt__CollectionsKt.getLastIndex(messagingStyle.getMessages()); -1 < lastIndex; lastIndex--) {
                Person senderPerson3 = messagingStyle.getMessages().get(lastIndex).getSenderPerson();
                CharSequence keyOrName3 = senderPerson3 != null ? getKeyOrName(senderPerson3) : null;
                if ((senderPerson3 != null && !Intrinsics.areEqual(keyOrName3, keyOrName)) || lastIndex == 0) {
                    if ((conversationTitle2 == null || conversationTitle2.length() == 0) && (senderPerson3 == null || (conversationTitle2 = senderPerson3.getName()) == null)) {
                        conversationTitle2 = "";
                    }
                    if (shortcutIcon == null) {
                        shortcutIcon = senderPerson3 != null ? senderPerson3.getIcon() : null;
                        if (shortcutIcon == null) {
                            shortcutIcon = getDefaultAvatar(builder, conversationTitle2, null);
                        }
                    }
                }
            }
        }
        if (shortcutIcon == null) {
            shortcutIcon = notification.getLargeIcon();
        }
        if (isGroupConversation && shortcutIcon == null) {
            int lastIndex2 = CollectionsKt__CollectionsKt.getLastIndex(list);
            Icon icon2 = null;
            CharSequence charSequence7 = null;
            while (true) {
                if (-1 >= lastIndex2) {
                    icon = null;
                    break;
                }
                Person senderPerson4 = ((Notification.MessagingStyle.Message) ((List) list.get(lastIndex2)).get(0)).getSenderPerson();
                if (senderPerson4 == null) {
                    senderPerson4 = messagingStyle.getUser();
                }
                Intrinsics.checkNotNull(senderPerson4);
                CharSequence keyOrName4 = getKeyOrName(senderPerson4);
                boolean areEqual = Intrinsics.areEqual(keyOrName4, keyOrName);
                boolean areEqual2 = Intrinsics.areEqual(keyOrName4, charSequence7);
                if ((!areEqual && !areEqual2) || (lastIndex2 == 0 && charSequence7 == null)) {
                    if (icon2 == null) {
                        icon2 = senderPerson4.getIcon();
                        if (icon2 == null) {
                            icon2 = getDefaultAvatar(builder, senderPerson4.getName(), mapUniqueNamesToPrefixWithGroupList);
                        }
                        charSequence7 = keyOrName4;
                    } else {
                        Icon icon3 = senderPerson4.getIcon();
                        icon = icon3 == null ? getDefaultAvatar(builder, senderPerson4.getName(), mapUniqueNamesToPrefixWithGroupList) : icon3;
                    }
                }
                lastIndex2--;
            }
            charSequence2 = null;
            if (icon2 == null) {
                icon2 = getDefaultAvatar(builder, "", null);
            }
            if (icon == null) {
                icon = getDefaultAvatar(builder, "", null);
            }
            singleIcon = new FacePile(icon.loadDrawable(context), icon2.loadDrawable(context), builder.getBackgroundColor(false));
        } else {
            charSequence2 = null;
            singleIcon = new SingleIcon(shortcutIcon != null ? shortcutIcon.loadDrawable(context) : null);
        }
        if (isGroupConversation && conversationTextData != null) {
            charSequence2 = conversationTextData.senderName;
        }
        return new SingleLineViewModel(resolveTitle, resolveText, new ConversationData(charSequence2, singleIcon));
    }
}
