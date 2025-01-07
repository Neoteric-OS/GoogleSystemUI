package androidx.emoji2.text;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.Spanned;
import android.view.inputmethod.EditorInfo;
import androidx.collection.ArraySet;
import androidx.core.util.Preconditions;
import androidx.emoji2.text.EmojiCompatInitializer;
import androidx.emoji2.text.EmojiProcessor;
import androidx.emoji2.text.flatbuffer.MetadataList;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class EmojiCompat {
    public static final Object INSTANCE_LOCK = new Object();
    public static volatile EmojiCompat sInstance;
    public final DefaultGlyphChecker mGlyphChecker;
    public final CompatInternal mHelper;
    public final ArraySet mInitCallbacks;
    public final ReadWriteLock mInitLock;
    public volatile int mLoadState;
    public final int mMetadataLoadStrategy;
    public final MetadataRepoLoader mMetadataLoader;
    public final DefaultSpanFactory mSpanFactory;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class CompatInternal {
        public final EmojiCompat mEmojiCompat;
        public volatile MetadataRepo mMetadataRepo;
        public volatile EmojiProcessor mProcessor;

        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        /* renamed from: androidx.emoji2.text.EmojiCompat$CompatInternal$1, reason: invalid class name */
        public final class AnonymousClass1 extends InitCallback {
            public AnonymousClass1() {
            }

            @Override // androidx.emoji2.text.EmojiCompat.InitCallback
            public final void onFailed(Throwable th) {
                CompatInternal.this.mEmojiCompat.onMetadataLoadFailed(th);
            }

            @Override // androidx.emoji2.text.EmojiCompat.InitCallback
            public final void onLoaded(MetadataRepo metadataRepo) {
                Set emptySet;
                CompatInternal compatInternal = CompatInternal.this;
                compatInternal.mMetadataRepo = metadataRepo;
                MetadataRepo metadataRepo2 = compatInternal.mMetadataRepo;
                EmojiCompat emojiCompat = compatInternal.mEmojiCompat;
                DefaultSpanFactory defaultSpanFactory = emojiCompat.mSpanFactory;
                DefaultGlyphChecker defaultGlyphChecker = emojiCompat.mGlyphChecker;
                try {
                    Class[] clsArr = new Class[0];
                    Object invoke = Class.forName("android.text.EmojiConsistency").getMethod("getEmojiConsistencySet", null).invoke(null, null);
                    if (invoke != null) {
                        emptySet = (Set) invoke;
                        Iterator it = emptySet.iterator();
                        while (it.hasNext()) {
                            if (!(it.next() instanceof int[])) {
                                emptySet = Collections.emptySet();
                                break;
                            }
                        }
                    } else {
                        emptySet = Collections.emptySet();
                    }
                } catch (Throwable unused) {
                    emptySet = Collections.emptySet();
                }
                compatInternal.mProcessor = new EmojiProcessor(metadataRepo2, defaultSpanFactory, defaultGlyphChecker, emptySet);
                EmojiCompat emojiCompat2 = compatInternal.mEmojiCompat;
                ArraySet arraySet = emojiCompat2.mInitCallbacks;
                ArrayList arrayList = new ArrayList(arraySet._size);
                ((ReentrantReadWriteLock) emojiCompat2.mInitLock).writeLock().lock();
                try {
                    emojiCompat2.mLoadState = 1;
                    arrayList.addAll(arraySet);
                    arraySet.clear();
                    for (int i = 0; i < arrayList.size(); i++) {
                        InitWithExecutor initWithExecutor = (InitWithExecutor) arrayList.get(i);
                        initWithExecutor.mExecutor.execute(new EmojiCompat$InitWithExecutor$$ExternalSyntheticLambda0(initWithExecutor));
                    }
                } finally {
                    ((ReentrantReadWriteLock) emojiCompat2.mInitLock).writeLock().unlock();
                }
            }
        }

        public CompatInternal(EmojiCompat emojiCompat) {
            this.mEmojiCompat = emojiCompat;
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public abstract class Config {
        public final MetadataRepoLoader mMetadataLoader;
        public int mMetadataLoadStrategy = 0;
        public final DefaultGlyphChecker mGlyphChecker = new DefaultGlyphChecker();

        public Config(MetadataRepoLoader metadataRepoLoader) {
            this.mMetadataLoader = metadataRepoLoader;
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class DefaultSpanFactory {
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public abstract class InitCallback {
        public void onFailed() {
        }

        public abstract void onFailed(Throwable th);

        public abstract void onInitialized();

        public abstract void onLoaded(MetadataRepo metadataRepo);
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class InitWithExecutor {
        public ConcurrencyHelpers$$ExternalSyntheticLambda0 mExecutor;
        public InitCallback mInitCallback;
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public interface MetadataRepoLoader {
        void load(InitCallback initCallback);
    }

    public EmojiCompat(EmojiCompatInitializer.BackgroundDefaultConfig backgroundDefaultConfig) {
        ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();
        this.mInitLock = reentrantReadWriteLock;
        this.mLoadState = 3;
        MetadataRepoLoader metadataRepoLoader = backgroundDefaultConfig.mMetadataLoader;
        this.mMetadataLoader = metadataRepoLoader;
        int i = backgroundDefaultConfig.mMetadataLoadStrategy;
        this.mMetadataLoadStrategy = i;
        this.mGlyphChecker = backgroundDefaultConfig.mGlyphChecker;
        this.mInitCallbacks = new ArraySet(0);
        this.mSpanFactory = new DefaultSpanFactory();
        CompatInternal compatInternal = new CompatInternal(this);
        this.mHelper = compatInternal;
        reentrantReadWriteLock.writeLock().lock();
        if (i == 0) {
            try {
                this.mLoadState = 0;
            } catch (Throwable th) {
                ((ReentrantReadWriteLock) this.mInitLock).writeLock().unlock();
                throw th;
            }
        }
        reentrantReadWriteLock.writeLock().unlock();
        if (getLoadState() == 0) {
            try {
                metadataRepoLoader.load(compatInternal.new AnonymousClass1());
            } catch (Throwable th2) {
                onMetadataLoadFailed(th2);
            }
        }
    }

    public static EmojiCompat get() {
        EmojiCompat emojiCompat;
        synchronized (INSTANCE_LOCK) {
            emojiCompat = sInstance;
            Preconditions.checkState("EmojiCompat is not initialized.\n\nYou must initialize EmojiCompat prior to referencing the EmojiCompat instance.\n\nThe most likely cause of this error is disabling the EmojiCompatInitializer\neither explicitly in AndroidManifest.xml, or by including\nandroidx.emoji2:emoji2-bundled.\n\nAutomatic initialization is typically performed by EmojiCompatInitializer. If\nyou are not expecting to initialize EmojiCompat manually in your application,\nplease check to ensure it has not been removed from your APK's manifest. You can\ndo this in Android Studio using Build > Analyze APK.\n\nIn the APK Analyzer, ensure that the startup entry for\nEmojiCompatInitializer and InitializationProvider is present in\n AndroidManifest.xml. If it is missing or contains tools:node=\"remove\", and you\nintend to use automatic configuration, verify:\n\n  1. Your application does not include emoji2-bundled\n  2. All modules do not contain an exclusion manifest rule for\n     EmojiCompatInitializer or InitializationProvider. For more information\n     about manifest exclusions see the documentation for the androidx startup\n     library.\n\nIf you intend to use emoji2-bundled, please call EmojiCompat.init. You can\nlearn more in the documentation for BundledEmojiCompatConfig.\n\nIf you intended to perform manual configuration, it is recommended that you call\nEmojiCompat.init immediately on application startup.\n\nIf you still cannot resolve this issue, please open a bug with your specific\nconfiguration to help improve error message.", emojiCompat != null);
        }
        return emojiCompat;
    }

    public static boolean isConfigured() {
        return sInstance != null;
    }

    public final int getEmojiStart(int i, CharSequence charSequence) {
        Preconditions.checkState("Not initialized yet", getLoadState() == 1);
        Preconditions.checkNotNull(charSequence, "charSequence cannot be null");
        EmojiProcessor emojiProcessor = this.mHelper.mProcessor;
        emojiProcessor.getClass();
        if (i < 0 || i >= charSequence.length()) {
            return -1;
        }
        if (charSequence instanceof Spanned) {
            Spanned spanned = (Spanned) charSequence;
            TypefaceEmojiSpan[] typefaceEmojiSpanArr = (TypefaceEmojiSpan[]) spanned.getSpans(i, i + 1, TypefaceEmojiSpan.class);
            if (typefaceEmojiSpanArr.length > 0) {
                return spanned.getSpanStart(typefaceEmojiSpanArr[0]);
            }
        }
        return ((EmojiProcessor.EmojiProcessLookupCallback) emojiProcessor.process(charSequence, Math.max(0, i - 16), Math.min(charSequence.length(), i + 16), Integer.MAX_VALUE, true, new EmojiProcessor.EmojiProcessLookupCallback(i))).start;
    }

    public final int getLoadState() {
        ((ReentrantReadWriteLock) this.mInitLock).readLock().lock();
        try {
            return this.mLoadState;
        } finally {
            ((ReentrantReadWriteLock) this.mInitLock).readLock().unlock();
        }
    }

    public final void load() {
        Preconditions.checkState("Set metadataLoadStrategy to LOAD_STRATEGY_MANUAL to execute manual loading", this.mMetadataLoadStrategy == 1);
        if (getLoadState() == 1) {
            return;
        }
        ((ReentrantReadWriteLock) this.mInitLock).writeLock().lock();
        try {
            if (this.mLoadState == 0) {
                return;
            }
            this.mLoadState = 0;
            ((ReentrantReadWriteLock) this.mInitLock).writeLock().unlock();
            CompatInternal compatInternal = this.mHelper;
            EmojiCompat emojiCompat = compatInternal.mEmojiCompat;
            try {
                emojiCompat.mMetadataLoader.load(compatInternal.new AnonymousClass1());
            } catch (Throwable th) {
                emojiCompat.onMetadataLoadFailed(th);
            }
        } finally {
            ((ReentrantReadWriteLock) this.mInitLock).writeLock().unlock();
        }
    }

    public final void onMetadataLoadFailed(Throwable th) {
        ArraySet arraySet = this.mInitCallbacks;
        ArrayList arrayList = new ArrayList(arraySet._size);
        ((ReentrantReadWriteLock) this.mInitLock).writeLock().lock();
        try {
            this.mLoadState = 2;
            arrayList.addAll(arraySet);
            arraySet.clear();
            ((ReentrantReadWriteLock) this.mInitLock).writeLock().unlock();
            for (int i = 0; i < arrayList.size(); i++) {
                InitWithExecutor initWithExecutor = (InitWithExecutor) arrayList.get(i);
                initWithExecutor.mExecutor.execute(new EmojiCompat$InitWithExecutor$$ExternalSyntheticLambda1(initWithExecutor, th));
            }
        } catch (Throwable th2) {
            ((ReentrantReadWriteLock) this.mInitLock).writeLock().unlock();
            throw th2;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:36:0x00a6 A[Catch: all -> 0x0087, TryCatch #0 {all -> 0x0087, blocks: (B:68:0x0060, B:71:0x0065, B:73:0x0069, B:75:0x0078, B:30:0x0094, B:32:0x00a0, B:34:0x00a3, B:36:0x00a6, B:38:0x00b6, B:40:0x00b9, B:45:0x00c8, B:48:0x00cf, B:50:0x00e2, B:28:0x008a), top: B:67:0x0060 }] */
    /* JADX WARN: Removed duplicated region for block: B:50:0x00e2 A[Catch: all -> 0x0087, TRY_LEAVE, TryCatch #0 {all -> 0x0087, blocks: (B:68:0x0060, B:71:0x0065, B:73:0x0069, B:75:0x0078, B:30:0x0094, B:32:0x00a0, B:34:0x00a3, B:36:0x00a6, B:38:0x00b6, B:40:0x00b9, B:45:0x00c8, B:48:0x00cf, B:50:0x00e2, B:28:0x008a), top: B:67:0x0060 }] */
    /* JADX WARN: Removed duplicated region for block: B:61:0x00ed  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.CharSequence process(int r12, int r13, int r14, java.lang.CharSequence r15) {
        /*
            Method dump skipped, instructions count: 275
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.emoji2.text.EmojiCompat.process(int, int, int, java.lang.CharSequence):java.lang.CharSequence");
    }

    public final void registerInitCallback(InitCallback initCallback) {
        Handler createAsync = Handler.createAsync(Looper.getMainLooper());
        Objects.requireNonNull(createAsync);
        ConcurrencyHelpers$$ExternalSyntheticLambda0 concurrencyHelpers$$ExternalSyntheticLambda0 = new ConcurrencyHelpers$$ExternalSyntheticLambda0(createAsync);
        Preconditions.checkNotNull(initCallback, "initCallback cannot be null");
        InitWithExecutor initWithExecutor = new InitWithExecutor();
        initWithExecutor.mInitCallback = initCallback;
        initWithExecutor.mExecutor = concurrencyHelpers$$ExternalSyntheticLambda0;
        ((ReentrantReadWriteLock) this.mInitLock).writeLock().lock();
        try {
            if (this.mLoadState == 1) {
                initWithExecutor.mExecutor.execute(new EmojiCompat$InitWithExecutor$$ExternalSyntheticLambda0(initWithExecutor));
            } else if (this.mLoadState == 2) {
                initWithExecutor.mExecutor.execute(new EmojiCompat$InitWithExecutor$$ExternalSyntheticLambda1(initWithExecutor, new IllegalStateException("Initialization failed prior to registering this callback, please add an initialization callback to the EmojiCompat.Config instead to see the cause.")));
            } else {
                this.mInitCallbacks.add(initWithExecutor);
            }
            ((ReentrantReadWriteLock) this.mInitLock).writeLock().unlock();
        } catch (Throwable th) {
            ((ReentrantReadWriteLock) this.mInitLock).writeLock().unlock();
            throw th;
        }
    }

    public final void updateEditorInfo(EditorInfo editorInfo) {
        if (getLoadState() != 1 || editorInfo == null) {
            return;
        }
        if (editorInfo.extras == null) {
            editorInfo.extras = new Bundle();
        }
        CompatInternal compatInternal = this.mHelper;
        compatInternal.getClass();
        Bundle bundle = editorInfo.extras;
        MetadataList metadataList = compatInternal.mMetadataRepo.mMetadataList;
        int __offset = metadataList.__offset(4);
        bundle.putInt("android.support.text.emoji.emojiCompat_metadataVersion", __offset != 0 ? metadataList.bb.getInt(__offset + metadataList.bb_pos) : 0);
        Bundle bundle2 = editorInfo.extras;
        compatInternal.mEmojiCompat.getClass();
        bundle2.putBoolean("android.support.text.emoji.emojiCompat_replaceAll", false);
    }
}
