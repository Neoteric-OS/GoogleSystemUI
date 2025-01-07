package com.android.systemui.statusbar.notification.row.wrapper;

import android.content.Context;
import android.graphics.drawable.AnimatedImageDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import com.android.internal.widget.CachingIconView;
import com.android.internal.widget.ConversationLayout;
import com.android.internal.widget.MessagingGroup;
import com.android.internal.widget.MessagingImageMessage;
import com.android.internal.widget.MessagingLinearLayout;
import com.android.systemui.statusbar.ViewTransformationHelper;
import com.android.systemui.statusbar.notification.NotificationFadeAware;
import com.android.systemui.statusbar.notification.NotificationUtils;
import com.android.systemui.statusbar.notification.row.ExpandableNotificationRow;
import com.android.systemui.statusbar.notification.row.wrapper.NotificationMessagingTemplateViewWrapper;
import com.android.systemui.util.ConvenienceExtensionsKt;
import com.android.wm.shell.R;
import java.util.ArrayList;
import kotlin.collections.CollectionsKt___CollectionsKt$asSequence$$inlined$Sequence$1;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.SequencesKt;
import kotlin.sequences.TransformingSequence;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class NotificationConversationTemplateViewWrapper extends NotificationTemplateViewWrapper {
    public View appName;
    public View conversationBadgeBg;
    public View conversationIconContainer;
    public CachingIconView conversationIconView;
    public final ConversationLayout conversationLayout;
    public View conversationTitleView;
    public View expandBtn;
    public View facePileBottom;
    public View facePileBottomBg;
    public View facePileTop;
    public ViewGroup imageMessageContainer;
    public View importanceRing;
    public ArrayList messageContainers;
    public MessagingLinearLayout messagingLinearLayout;
    public final int minHeightWithActions;

    public NotificationConversationTemplateViewWrapper(Context context, View view, ExpandableNotificationRow expandableNotificationRow) {
        super(context, view, expandableNotificationRow);
        this.minHeightWithActions = NotificationUtils.getFontScaledHeight(R.dimen.notification_messaging_actions_min_height, context);
        this.conversationLayout = (ConversationLayout) view;
    }

    @Override // com.android.systemui.statusbar.notification.row.wrapper.NotificationViewWrapper
    public final int getMinLayoutHeight() {
        View view = this.mActionsContainer;
        if (view == null || view.getVisibility() == 8) {
            return 0;
        }
        return this.minHeightWithActions;
    }

    @Override // com.android.systemui.statusbar.notification.row.wrapper.NotificationHeaderViewWrapper, com.android.systemui.statusbar.notification.row.wrapper.NotificationViewWrapper
    public final View getShelfTransformationTarget() {
        if (!this.conversationLayout.isImportantConversation()) {
            return this.mIcon;
        }
        CachingIconView cachingIconView = this.conversationIconView;
        if (cachingIconView == null) {
            cachingIconView = null;
        }
        if (cachingIconView.getVisibility() == 8) {
            return this.mIcon;
        }
        CachingIconView cachingIconView2 = this.conversationIconView;
        if (cachingIconView2 == null) {
            return null;
        }
        return cachingIconView2;
    }

    @Override // com.android.systemui.statusbar.notification.row.wrapper.NotificationTemplateViewWrapper, com.android.systemui.statusbar.notification.row.wrapper.NotificationHeaderViewWrapper, com.android.systemui.statusbar.notification.row.wrapper.NotificationViewWrapper
    public final void onContentUpdated(ExpandableNotificationRow expandableNotificationRow) {
        this.messagingLinearLayout = this.conversationLayout.getMessagingLinearLayout();
        this.imageMessageContainer = this.conversationLayout.getImageMessageContainer();
        this.messageContainers = this.conversationLayout.getMessagingGroups();
        ConversationLayout conversationLayout = this.conversationLayout;
        this.conversationIconContainer = conversationLayout.requireViewById(android.R.id.date_picker_header_year);
        this.conversationIconView = conversationLayout.requireViewById(android.R.id.datePicker);
        this.conversationBadgeBg = conversationLayout.requireViewById(android.R.id.date_picker_header);
        this.expandBtn = conversationLayout.requireViewById(android.R.id.feedbackAllMask);
        conversationLayout.requireViewById(android.R.id.feedbackHaptic);
        this.importanceRing = conversationLayout.requireViewById(android.R.id.date_picker_header_date);
        this.appName = conversationLayout.requireViewById(android.R.id.autofill_dataset_picker);
        this.conversationTitleView = conversationLayout.requireViewById(android.R.id.datetime);
        this.facePileTop = conversationLayout.findViewById(android.R.id.dataSync);
        this.facePileBottom = conversationLayout.findViewById(android.R.id.cycle);
        this.facePileBottomBg = conversationLayout.findViewById(android.R.id.dangerous);
        super.onContentUpdated(expandableNotificationRow);
    }

    @Override // com.android.systemui.statusbar.notification.row.wrapper.NotificationViewWrapper
    public final void setAnimationsRunning(boolean z) {
        ArrayList arrayList = this.messageContainers;
        if (arrayList == null) {
            arrayList = null;
        }
        TransformingSequence transformingSequence = new TransformingSequence(new CollectionsKt___CollectionsKt$asSequence$$inlined$Sequence$1(arrayList), new Function1() { // from class: com.android.systemui.statusbar.notification.row.wrapper.NotificationConversationTemplateViewWrapper$setAnimationsRunning$containers$1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return ((MessagingGroup) obj).getMessageContainer();
            }
        });
        ViewGroup[] viewGroupArr = new ViewGroup[1];
        ViewGroup viewGroup = this.imageMessageContainer;
        viewGroupArr[0] = viewGroup != null ? viewGroup : null;
        for (AnimatedImageDrawable animatedImageDrawable : SequencesKt.toSet(SequencesKt.mapNotNull(SequencesKt.flatMap(SequencesKt.flatten(SequencesKt.sequenceOf(transformingSequence, SequencesKt.sequenceOf(viewGroupArr))), new Function1() { // from class: com.android.systemui.statusbar.notification.row.wrapper.NotificationConversationTemplateViewWrapper$setAnimationsRunning$drawables$1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                ViewGroup viewGroup2 = (ViewGroup) obj;
                Intrinsics.checkNotNull(viewGroup2);
                return ConvenienceExtensionsKt.getChildren(viewGroup2);
            }
        }), new Function1() { // from class: com.android.systemui.statusbar.notification.row.wrapper.NotificationConversationTemplateViewWrapper$setAnimationsRunning$drawables$2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                MessagingImageMessage messagingImageMessage = (View) obj;
                MessagingImageMessage messagingImageMessage2 = messagingImageMessage instanceof MessagingImageMessage ? messagingImageMessage : null;
                if (messagingImageMessage2 == null) {
                    return null;
                }
                Drawable drawable = messagingImageMessage2.getDrawable();
                if (drawable instanceof AnimatedImageDrawable) {
                    return (AnimatedImageDrawable) drawable;
                }
                return null;
            }
        }))) {
            if (z) {
                animatedImageDrawable.start();
            } else if (!z) {
                animatedImageDrawable.stop();
            }
        }
    }

    @Override // com.android.systemui.statusbar.notification.row.wrapper.NotificationViewWrapper
    public final void setNotificationFaded(boolean z) {
        View view = this.expandBtn;
        if (view == null) {
            view = null;
        }
        NotificationFadeAware.setLayerTypeForFaded(view, z);
        View view2 = this.conversationIconContainer;
        NotificationFadeAware.setLayerTypeForFaded(view2 != null ? view2 : null, z);
    }

    @Override // com.android.systemui.statusbar.notification.row.wrapper.NotificationViewWrapper
    public final void setRemoteInputVisible(boolean z) {
        this.conversationLayout.showHistoricMessages(z);
    }

    @Override // com.android.systemui.statusbar.notification.row.wrapper.NotificationHeaderViewWrapper, com.android.systemui.statusbar.notification.row.wrapper.NotificationViewWrapper
    public final void updateExpandability(boolean z, ExpandableNotificationRow.AnonymousClass1 anonymousClass1, boolean z2) {
        this.conversationLayout.updateExpandability(z, anonymousClass1);
    }

    @Override // com.android.systemui.statusbar.notification.row.wrapper.NotificationTemplateViewWrapper, com.android.systemui.statusbar.notification.row.wrapper.NotificationHeaderViewWrapper
    public final void updateTransformedTypes() {
        super.updateTransformedTypes();
        View view = this.conversationTitleView;
        if (view == null) {
            view = null;
        }
        ViewTransformationHelper viewTransformationHelper = this.mTransformationHelper;
        viewTransformationHelper.addTransformedView(view, 1);
        View[] viewArr = new View[2];
        MessagingLinearLayout messagingLinearLayout = this.messagingLinearLayout;
        if (messagingLinearLayout == null) {
            messagingLinearLayout = null;
        }
        viewArr[0] = messagingLinearLayout;
        View view2 = this.appName;
        if (view2 == null) {
            view2 = null;
        }
        viewArr[1] = view2;
        addTransformedViews(viewArr);
        ViewGroup viewGroup = this.imageMessageContainer;
        if (viewGroup == null) {
            viewGroup = null;
        }
        if (viewGroup != null) {
            viewTransformationHelper.mCustomTransformations.put(Integer.valueOf(viewGroup.getId()), new NotificationMessagingTemplateViewWrapper.AnonymousClass1());
        }
        View[] viewArr2 = new View[7];
        CachingIconView cachingIconView = this.conversationIconView;
        if (cachingIconView == null) {
            cachingIconView = null;
        }
        viewArr2[0] = cachingIconView;
        View view3 = this.conversationBadgeBg;
        if (view3 == null) {
            view3 = null;
        }
        viewArr2[1] = view3;
        View view4 = this.expandBtn;
        if (view4 == null) {
            view4 = null;
        }
        viewArr2[2] = view4;
        View view5 = this.importanceRing;
        viewArr2[3] = view5 != null ? view5 : null;
        viewArr2[4] = this.facePileTop;
        viewArr2[5] = this.facePileBottom;
        viewArr2[6] = this.facePileBottomBg;
        addViewsTransformingToSimilar(viewArr2);
    }
}
