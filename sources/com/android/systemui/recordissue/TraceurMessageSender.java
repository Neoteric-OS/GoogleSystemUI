package com.android.systemui.recordissue;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.ClipData;
import android.content.ClipDescription;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.frameworks.stats.AnnotationValue$1$$ExternalSyntheticOutline0;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import android.os.SystemProperties;
import android.util.Log;
import android.util.Patterns;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Pair;
import kotlin.collections.CollectionsKt;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class TraceurMessageSender {
    public final Looper backgroundLooper;
    public Messenger binder;
    public boolean isBound;
    public final List onBoundToTraceur = new ArrayList();
    public final TraceurMessageSender$traceurConnection$1 traceurConnection = new ServiceConnection() { // from class: com.android.systemui.recordissue.TraceurMessageSender$traceurConnection$1
        @Override // android.content.ServiceConnection
        public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            TraceurMessageSender.this.binder = new Messenger(iBinder);
            TraceurMessageSender traceurMessageSender = TraceurMessageSender.this;
            traceurMessageSender.isBound = true;
            Iterator it = traceurMessageSender.onBoundToTraceur.iterator();
            while (it.hasNext()) {
                ((Runnable) it.next()).run();
            }
            TraceurMessageSender.this.onBoundToTraceur.clear();
        }

        @Override // android.content.ServiceConnection
        public final void onServiceDisconnected(ComponentName componentName) {
            TraceurMessageSender traceurMessageSender = TraceurMessageSender.this;
            traceurMessageSender.binder = null;
            traceurMessageSender.isBound = false;
        }
    };

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class ShareFilesHandler extends Handler {
        public final IssueRecordingService context;
        public final Uri screenRecord;

        public ShareFilesHandler(IssueRecordingService issueRecordingService, Uri uri, Looper looper) {
            super(looper);
            this.context = issueRecordingService;
            this.screenRecord = uri;
        }

        @Override // android.os.Handler
        public final void handleMessage(Message message) {
            Account account;
            if (2 != message.what) {
                throw new IllegalArgumentException(AnnotationValue$1$$ExternalSyntheticOutline0.m(message.what, "received unknown msg.what: "));
            }
            Uri uri = (Uri) message.getData().getParcelable("com.android.traceur.PERFETTO", Uri.class);
            Uri uri2 = (Uri) message.getData().getParcelable("com.android.traceur.WINSCOPE_ZIP", Uri.class);
            ArrayList arrayList = new ArrayList();
            if (uri != null) {
                arrayList.add(uri);
            }
            if (uri2 != null) {
                arrayList.add(uri2);
            }
            Uri uri3 = this.screenRecord;
            if (uri3 != null) {
                arrayList.add(uri3);
            }
            String str = Build.FINGERPRINT;
            Intent intent = new Intent("android.intent.action.SEND_MULTIPLE");
            intent.addFlags(1);
            intent.addCategory("android.intent.category.DEFAULT");
            intent.setType("application/vnd.android.systrace");
            intent.putExtra("android.intent.extra.SUBJECT", ((Uri) arrayList.get(0)).getLastPathSegment());
            intent.putExtra("android.intent.extra.TEXT", (CharSequence) str);
            intent.putExtra("android.intent.extra.STREAM", new ArrayList(arrayList));
            ArrayList arrayList2 = new ArrayList();
            Iterator it = arrayList.iterator();
            while (true) {
                account = null;
                if (!it.hasNext()) {
                    break;
                }
                arrayList2.add(new ClipData.Item(Build.FINGERPRINT, null, (Uri) it.next()));
            }
            intent.setClipData(new ClipData(new ClipDescription(null, new String[]{"application/vnd.android.systrace"}), arrayList2));
            IssueRecordingService issueRecordingService = this.context;
            AccountManager accountManager = (AccountManager) issueRecordingService.getSystemService("account");
            String str2 = SystemProperties.get("sendbug.preferred.domain");
            if (!str2.startsWith("@")) {
                str2 = "@".concat(str2);
            }
            for (Account account2 : accountManager.getAccounts()) {
                if (Patterns.EMAIL_ADDRESS.matcher(account2.name).matches()) {
                    if (str2.isEmpty() || account2.name.endsWith(str2)) {
                        account = account2;
                        break;
                    }
                    account = account2;
                }
            }
            if (account != null) {
                intent.putExtra("android.intent.extra.EMAIL", new String[]{account.name});
            }
            issueRecordingService.startActivity(intent.addFlags(272629760));
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class TagsHandler extends Handler {
        public final IssueRecordingState state;

        public TagsHandler(Looper looper, IssueRecordingState issueRecordingState) {
            super(looper);
            this.state = issueRecordingState;
        }

        @Override // android.os.Handler
        public final void handleMessage(Message message) {
            if (3 != message.what) {
                throw new IllegalArgumentException(AnnotationValue$1$$ExternalSyntheticOutline0.m(message.what, "received unknown msg.what: "));
            }
            ArrayList<String> stringArrayList = message.getData().getStringArrayList("com.android.traceur.tags");
            ArrayList<String> stringArrayList2 = message.getData().getStringArrayList("com.android.traceur.tag_descriptions");
            if (stringArrayList == null || stringArrayList2 == null) {
                throw new IllegalArgumentException("Neither keys: " + stringArrayList + ", nor values: " + stringArrayList2 + " can be null");
            }
            List<Pair> zip = CollectionsKt.zip(stringArrayList, stringArrayList2);
            ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(zip, 10));
            for (Pair pair : zip) {
                arrayList.add(pair.getFirst() + ": " + pair.getSecond());
            }
            this.state.prefs.edit().putStringSet("key_tagTitles", CollectionsKt.toSet(arrayList)).apply();
        }
    }

    /* JADX WARN: Type inference failed for: r1v2, types: [com.android.systemui.recordissue.TraceurMessageSender$traceurConnection$1] */
    public TraceurMessageSender(Looper looper) {
        this.backgroundLooper = looper;
    }

    public static void notifyTraceur$default(TraceurMessageSender traceurMessageSender, int i, Bundle bundle, Messenger messenger, int i2) {
        if ((i2 & 2) != 0) {
            bundle = new Bundle();
        }
        if ((i2 & 4) != 0) {
            messenger = null;
        }
        traceurMessageSender.getClass();
        try {
            Messenger messenger2 = traceurMessageSender.binder;
            Intrinsics.checkNotNull(messenger2);
            Message obtain = Message.obtain();
            obtain.what = i;
            obtain.setData(bundle);
            obtain.replyTo = messenger;
            messenger2.send(obtain);
        } catch (Exception e) {
            Log.e("TraceurMessageSender", "failed to notify Traceur", e);
        }
    }
}
