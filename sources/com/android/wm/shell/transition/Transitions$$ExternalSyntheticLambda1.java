package com.android.wm.shell.transition;

import android.R;
import android.content.ContentResolver;
import android.provider.Settings;
import android.util.Pair;
import android.view.WindowManager;
import android.window.RemoteTransition;
import android.window.TransitionInfo;
import android.window.TransitionMetrics;
import com.android.wm.shell.ShellTaskOrganizer;
import com.android.wm.shell.sysui.ShellCommandHandler;
import com.android.wm.shell.transition.Transitions;
import com.android.wm.shell.transition.Transitions.SettingsObserver;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.function.BiConsumer;
import java.util.function.Supplier;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class Transitions$$ExternalSyntheticLambda1 implements Runnable {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ Object f$0;

    public /* synthetic */ Transitions$$ExternalSyntheticLambda1(int i, Object obj) {
        this.$r8$classId = i;
        this.f$0 = obj;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i = this.$r8$classId;
        Object obj = this.f$0;
        switch (i) {
            case 0:
                final Transitions transitions = (Transitions) obj;
                ShellTaskOrganizer shellTaskOrganizer = transitions.mOrganizer;
                boolean z = Transitions.ENABLE_SHELL_TRANSITIONS;
                if (z) {
                    shellTaskOrganizer.shareTransactionQueue();
                }
                transitions.mShellController.addExternalInterface("extra_shell_shell_transitions", new Supplier() { // from class: com.android.wm.shell.transition.Transitions$$ExternalSyntheticLambda5
                    @Override // java.util.function.Supplier
                    public final Object get() {
                        Transitions transitions2 = Transitions.this;
                        transitions2.getClass();
                        Transitions.IShellTransitionsImpl iShellTransitionsImpl = new Transitions.IShellTransitionsImpl();
                        iShellTransitionsImpl.attachInterface(iShellTransitionsImpl, "com.android.wm.shell.shared.IShellTransitions");
                        iShellTransitionsImpl.mTransitions = transitions2;
                        return iShellTransitionsImpl;
                    }
                }, transitions);
                ContentResolver contentResolver = transitions.mContext.getContentResolver();
                float fixScale = WindowManager.fixScale(Settings.Global.getFloat(transitions.mContext.getContentResolver(), "transition_animation_scale", transitions.mContext.getResources().getFloat(R.dimen.config_buttonCornerRadius)));
                transitions.mTransitionAnimationScaleSetting = fixScale;
                for (int size = transitions.mHandlers.size() - 1; size >= 0; size--) {
                    ((Transitions.TransitionHandler) transitions.mHandlers.get(size)).setAnimScaleSetting(fixScale);
                }
                contentResolver.registerContentObserver(Settings.Global.getUriFor("transition_animation_scale"), false, transitions.new SettingsObserver());
                if (z) {
                    transitions.mIsRegistered = true;
                    try {
                        shellTaskOrganizer.registerTransitionPlayer(transitions.mPlayerImpl);
                        TransitionMetrics.getInstance();
                    } catch (RuntimeException e) {
                        transitions.mIsRegistered = false;
                        throw e;
                    }
                }
                ShellCommandHandler shellCommandHandler = transitions.mShellCommandHandler;
                shellCommandHandler.addCommandCallback("transitions", transitions, transitions);
                shellCommandHandler.addDumpCallback(new BiConsumer() { // from class: com.android.wm.shell.transition.Transitions$$ExternalSyntheticLambda6
                    @Override // java.util.function.BiConsumer
                    public final void accept(Object obj2, Object obj3) {
                        Transitions transitions2 = Transitions.this;
                        PrintWriter printWriter = (PrintWriter) obj2;
                        String str = (String) obj3;
                        transitions2.getClass();
                        printWriter.println(str + "ShellTransitions");
                        String str2 = str + "  ";
                        printWriter.println(str + "Handlers:");
                        Iterator it = transitions2.mHandlers.iterator();
                        while (it.hasNext()) {
                            Transitions.TransitionHandler transitionHandler = (Transitions.TransitionHandler) it.next();
                            printWriter.print(str2);
                            printWriter.print(transitionHandler.getClass().getSimpleName());
                            printWriter.println(" (" + Integer.toHexString(System.identityHashCode(transitionHandler)) + ")");
                        }
                        RemoteTransitionHandler remoteTransitionHandler = transitions2.mRemoteTransitionHandler;
                        remoteTransitionHandler.getClass();
                        String str3 = str + "  ";
                        printWriter.println(str + "Registered Remotes:");
                        if (remoteTransitionHandler.mFilters.isEmpty()) {
                            printWriter.println(str3 + "none");
                        } else {
                            Iterator it2 = remoteTransitionHandler.mFilters.iterator();
                            while (it2.hasNext()) {
                                RemoteTransitionHandler.dumpRemote(printWriter, str3, (RemoteTransition) ((Pair) it2.next()).second);
                            }
                        }
                        printWriter.println(str + "Registered Takeover Remotes:");
                        if (remoteTransitionHandler.mTakeoverFilters.isEmpty()) {
                            printWriter.println(str3 + "none");
                        } else {
                            Iterator it3 = remoteTransitionHandler.mTakeoverFilters.iterator();
                            while (it3.hasNext()) {
                                RemoteTransitionHandler.dumpRemote(printWriter, str3, (RemoteTransition) ((Pair) it3.next()).second);
                            }
                        }
                        printWriter.println(str + "Observers:");
                        Iterator it4 = transitions2.mObservers.iterator();
                        while (it4.hasNext()) {
                            Transitions.TransitionObserver transitionObserver = (Transitions.TransitionObserver) it4.next();
                            printWriter.print(str2);
                            printWriter.println(transitionObserver.getClass().getSimpleName());
                        }
                        printWriter.println(str + "Pending Transitions:");
                        Iterator it5 = transitions2.mPendingTransitions.iterator();
                        while (true) {
                            String str4 = null;
                            if (!it5.hasNext()) {
                                break;
                            }
                            Transitions.ActiveTransition activeTransition = (Transitions.ActiveTransition) it5.next();
                            printWriter.print(str2 + "token=");
                            printWriter.println(activeTransition.mToken);
                            printWriter.print(str2 + "id=");
                            TransitionInfo transitionInfo = activeTransition.mInfo;
                            printWriter.println(transitionInfo != null ? transitionInfo.getDebugId() : -1);
                            printWriter.print(str2 + "handler=");
                            Transitions.TransitionHandler transitionHandler2 = activeTransition.mHandler;
                            if (transitionHandler2 != null) {
                                str4 = transitionHandler2.getClass().getSimpleName();
                            }
                            printWriter.println(str4);
                        }
                        if (transitions2.mPendingTransitions.isEmpty()) {
                            printWriter.println(str2 + "none");
                        }
                        printWriter.println(str + "Ready-during-sync Transitions:");
                        Iterator it6 = transitions2.mReadyDuringSync.iterator();
                        while (it6.hasNext()) {
                            Transitions.ActiveTransition activeTransition2 = (Transitions.ActiveTransition) it6.next();
                            printWriter.print(str2 + "token=");
                            printWriter.println(activeTransition2.mToken);
                            printWriter.print(str2 + "id=");
                            TransitionInfo transitionInfo2 = activeTransition2.mInfo;
                            printWriter.println(transitionInfo2 != null ? transitionInfo2.getDebugId() : -1);
                            printWriter.print(str2 + "handler=");
                            Transitions.TransitionHandler transitionHandler3 = activeTransition2.mHandler;
                            printWriter.println(transitionHandler3 != null ? transitionHandler3.getClass().getSimpleName() : null);
                        }
                        if (transitions2.mReadyDuringSync.isEmpty()) {
                            printWriter.println(str2 + "none");
                        }
                        printWriter.println(str + "Tracks:");
                        for (int i2 = 0; i2 < transitions2.mTracks.size(); i2++) {
                            Transitions.ActiveTransition activeTransition3 = ((Transitions.Track) transitions2.mTracks.get(i2)).mActiveTransition;
                            printWriter.println(str2 + "Track #" + i2);
                            StringBuilder sb = new StringBuilder();
                            sb.append(str2);
                            sb.append("active=");
                            printWriter.print(sb.toString());
                            printWriter.println(activeTransition3);
                            if (activeTransition3 != null) {
                                printWriter.print(str2 + "hander=");
                                printWriter.println(activeTransition3.mHandler);
                            }
                        }
                    }
                }, transitions);
                return;
            default:
                Transitions transitions2 = Transitions.this;
                float f = transitions2.mTransitionAnimationScaleSetting;
                for (int size2 = transitions2.mHandlers.size() - 1; size2 >= 0; size2--) {
                    ((Transitions.TransitionHandler) transitions2.mHandlers.get(size2)).setAnimScaleSetting(f);
                }
                return;
        }
    }
}
