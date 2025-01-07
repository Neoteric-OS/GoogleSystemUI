package com.android.systemui.screenshot;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.ComponentName;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.UserHandle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.android.systemui.screenshot.message.LabeledIcon;
import com.android.systemui.screenshot.message.ProfileMessageController;
import com.android.wm.shell.R;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.NoWhenBranchMatchedException;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.collections.EmptyList;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
final class MessageContainerController$onScreenshotTaken$1 extends SuspendLambda implements Function2 {
    final /* synthetic */ ScreenshotData $screenshot;
    int label;
    final /* synthetic */ MessageContainerController this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MessageContainerController$onScreenshotTaken$1(MessageContainerController messageContainerController, ScreenshotData screenshotData, Continuation continuation) {
        super(2, continuation);
        this.this$0 = messageContainerController;
        this.$screenshot = screenshotData;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new MessageContainerController$onScreenshotTaken$1(this.this$0, this.$screenshot, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((MessageContainerController$onScreenshotTaken$1) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        List list;
        ViewGroup viewGroup;
        int i;
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i2 = this.label;
        if (i2 == 0) {
            ResultKt.throwOnFailure(obj);
            ProfileMessageController profileMessageController = this.this$0.profileMessageController;
            UserHandle userHandle = this.$screenshot.userHandle;
            this.label = 1;
            obj = profileMessageController.onScreenshotTaken(userHandle, this);
            if (obj == coroutineSingletons) {
                return coroutineSingletons;
            }
        } else {
            if (i2 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        final ProfileMessageController.ProfileFirstRunData profileFirstRunData = (ProfileMessageController.ProfileFirstRunData) obj;
        ScreenshotDetectionController screenshotDetectionController = this.this$0.screenshotDetectionController;
        if (this.$screenshot.source == 3) {
            list = EmptyList.INSTANCE;
        } else {
            List notifyScreenshotListeners = screenshotDetectionController.windowManager.notifyScreenshotListeners(0);
            Intrinsics.checkNotNull(notifyScreenshotListeners);
            ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(notifyScreenshotListeners, 10));
            Iterator it = notifyScreenshotListeners.iterator();
            while (it.hasNext()) {
                arrayList.add(screenshotDetectionController.packageManager.getActivityInfo((ComponentName) it.next(), PackageManager.ComponentInfoFlags.of(4194816L)).loadLabel(screenshotDetectionController.packageManager));
            }
            list = arrayList;
        }
        if (profileFirstRunData != null) {
            ViewGroup viewGroup2 = this.this$0.workProfileFirstRunView;
            if (viewGroup2 == null) {
                viewGroup2 = null;
            }
            viewGroup2.setVisibility(0);
            ViewGroup viewGroup3 = this.this$0.detectionNoticeView;
            if (viewGroup3 == null) {
                viewGroup3 = null;
            }
            viewGroup3.setVisibility(8);
            final MessageContainerController messageContainerController = this.this$0;
            final ProfileMessageController profileMessageController2 = messageContainerController.profileMessageController;
            ViewGroup viewGroup4 = messageContainerController.workProfileFirstRunView;
            viewGroup = viewGroup4 != null ? viewGroup4 : null;
            final Function0 function0 = new Function0() { // from class: com.android.systemui.screenshot.MessageContainerController$onScreenshotTaken$1.1
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    final MessageContainerController messageContainerController2 = MessageContainerController.this;
                    if (messageContainerController2.animateOut == null) {
                        Animator animator = messageContainerController2.getAnimator(false);
                        animator.addListener(new AnimatorListenerAdapter() { // from class: com.android.systemui.screenshot.MessageContainerController$animateOutMessageContainer$1$1
                            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                            public final void onAnimationEnd(Animator animator2) {
                                super.onAnimationEnd(animator2);
                                ViewGroup viewGroup5 = MessageContainerController.this.container;
                                if (viewGroup5 == null) {
                                    viewGroup5 = null;
                                }
                                viewGroup5.setVisibility(8);
                                MessageContainerController.this.animateOut = null;
                            }
                        });
                        animator.start();
                        messageContainerController2.animateOut = animator;
                    }
                    return Unit.INSTANCE;
                }
            };
            LabeledIcon labeledIcon = profileFirstRunData.labeledIcon;
            if (labeledIcon.badgedIcon != null) {
                ((ImageView) viewGroup.requireViewById(R.id.screenshot_message_icon)).setImageDrawable(labeledIcon.badgedIcon);
            }
            TextView textView = (TextView) viewGroup.requireViewById(R.id.screenshot_message_content);
            Context context = viewGroup.getContext();
            int ordinal = profileFirstRunData.profileType.ordinal();
            if (ordinal == 0) {
                i = R.string.screenshot_work_profile_notification;
            } else {
                if (ordinal != 1) {
                    throw new NoWhenBranchMatchedException();
                }
                i = R.string.screenshot_private_profile_notification;
            }
            textView.setText(context.getString(i, labeledIcon.label));
            viewGroup.requireViewById(R.id.message_dismiss_button).setOnClickListener(new View.OnClickListener() { // from class: com.android.systemui.screenshot.message.ProfileMessageController$bindView$1
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    String str;
                    Function0.this.invoke();
                    ProfileMessageController profileMessageController3 = profileMessageController2;
                    ProfileMessageController.ProfileFirstRunData profileFirstRunData2 = profileFirstRunData;
                    ProfileFirstRunSettingsImpl profileFirstRunSettingsImpl = profileMessageController3.firstRunSettings;
                    profileFirstRunSettingsImpl.getClass();
                    int ordinal2 = profileFirstRunData2.profileType.ordinal();
                    if (ordinal2 == 0) {
                        str = "work_profile_first_run";
                    } else {
                        if (ordinal2 != 1) {
                            throw new NoWhenBranchMatchedException();
                        }
                        str = "private_profile_first_run";
                    }
                    SharedPreferences.Editor edit = profileFirstRunSettingsImpl.context.getSharedPreferences("com.android.systemui.screenshot", 0).edit();
                    edit.putBoolean(str, true);
                    edit.apply();
                }
            });
            MessageContainerController.access$animateInMessageContainer(this.this$0);
        } else if (!list.isEmpty()) {
            ViewGroup viewGroup5 = this.this$0.detectionNoticeView;
            if (viewGroup5 == null) {
                viewGroup5 = null;
            }
            viewGroup5.setVisibility(0);
            ViewGroup viewGroup6 = this.this$0.workProfileFirstRunView;
            if (viewGroup6 == null) {
                viewGroup6 = null;
            }
            viewGroup6.setVisibility(8);
            MessageContainerController messageContainerController2 = this.this$0;
            ScreenshotDetectionController screenshotDetectionController2 = messageContainerController2.screenshotDetectionController;
            ViewGroup viewGroup7 = messageContainerController2.detectionNoticeView;
            viewGroup = viewGroup7 != null ? viewGroup7 : null;
            list.isEmpty();
            TextView textView2 = (TextView) viewGroup.requireViewById(R.id.screenshot_detection_notice_text);
            if (list.size() == 1) {
                textView2.setText(viewGroup.getResources().getString(R.string.screenshot_detected_template, list.get(0)));
            } else {
                textView2.setText(viewGroup.getResources().getString(R.string.screenshot_detected_multiple_template, list.get(0)));
            }
            MessageContainerController.access$animateInMessageContainer(this.this$0);
        }
        return Unit.INSTANCE;
    }
}
