package androidx.emoji2.text;

import android.text.Editable;
import android.text.Selection;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.SparseArray;
import android.view.KeyEvent;
import androidx.core.graphics.PaintCompat;
import androidx.emoji2.text.EmojiCompat;
import androidx.emoji2.text.MetadataRepo;
import androidx.emoji2.text.flatbuffer.MetadataItem;
import java.util.Iterator;
import java.util.Set;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class EmojiProcessor {
    public final DefaultGlyphChecker mGlyphChecker;
    public final MetadataRepo mMetadataRepo;
    public final EmojiCompat.DefaultSpanFactory mSpanFactory;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class EmojiProcessAddSpanCallback implements EmojiProcessCallback {
        public final EmojiCompat.DefaultSpanFactory mSpanFactory;
        public UnprecomputeTextOnModificationSpannable spannable;

        public EmojiProcessAddSpanCallback(UnprecomputeTextOnModificationSpannable unprecomputeTextOnModificationSpannable, EmojiCompat.DefaultSpanFactory defaultSpanFactory) {
            this.spannable = unprecomputeTextOnModificationSpannable;
            this.mSpanFactory = defaultSpanFactory;
        }

        @Override // androidx.emoji2.text.EmojiProcessor.EmojiProcessCallback
        public final Object getResult() {
            return this.spannable;
        }

        @Override // androidx.emoji2.text.EmojiProcessor.EmojiProcessCallback
        public final boolean handleEmoji(CharSequence charSequence, int i, int i2, TypefaceEmojiRasterizer typefaceEmojiRasterizer) {
            if ((typefaceEmojiRasterizer.mCache & 4) > 0) {
                return true;
            }
            if (this.spannable == null) {
                this.spannable = new UnprecomputeTextOnModificationSpannable(charSequence instanceof Spannable ? (Spannable) charSequence : new SpannableString(charSequence));
            }
            this.mSpanFactory.getClass();
            this.spannable.setSpan(new TypefaceEmojiSpan(typefaceEmojiRasterizer), i, i2, 33);
            return true;
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public interface EmojiProcessCallback {
        Object getResult();

        boolean handleEmoji(CharSequence charSequence, int i, int i2, TypefaceEmojiRasterizer typefaceEmojiRasterizer);
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class ProcessorSm {
        public int mCurrentDepth;
        public MetadataRepo.Node mCurrentNode;
        public MetadataRepo.Node mFlushNode;
        public int mLastCodepoint;
        public final MetadataRepo.Node mRootNode;
        public int mState = 1;

        public ProcessorSm(MetadataRepo.Node node) {
            this.mRootNode = node;
            this.mCurrentNode = node;
        }

        public final void reset() {
            this.mState = 1;
            this.mCurrentNode = this.mRootNode;
            this.mCurrentDepth = 0;
        }

        public final boolean shouldUseEmojiPresentationStyleForSingleCodepoint() {
            MetadataItem metadataItem = this.mCurrentNode.mData.getMetadataItem();
            int __offset = metadataItem.__offset(6);
            return !(__offset == 0 || metadataItem.bb.get(__offset + metadataItem.bb_pos) == 0) || this.mLastCodepoint == 65039;
        }
    }

    public EmojiProcessor(MetadataRepo metadataRepo, EmojiCompat.DefaultSpanFactory defaultSpanFactory, DefaultGlyphChecker defaultGlyphChecker, Set set) {
        this.mSpanFactory = defaultSpanFactory;
        this.mMetadataRepo = metadataRepo;
        this.mGlyphChecker = defaultGlyphChecker;
        if (set.isEmpty()) {
            return;
        }
        Iterator it = set.iterator();
        while (it.hasNext()) {
            int[] iArr = (int[]) it.next();
            String str = new String(iArr, 0, iArr.length);
            process(str, 0, str.length(), 1, true, new MarkExclusionCallback(str));
        }
    }

    public static boolean delete(Editable editable, KeyEvent keyEvent, boolean z) {
        TypefaceEmojiSpan[] typefaceEmojiSpanArr;
        if (!KeyEvent.metaStateHasNoModifiers(keyEvent.getMetaState())) {
            return false;
        }
        int selectionStart = Selection.getSelectionStart(editable);
        int selectionEnd = Selection.getSelectionEnd(editable);
        if (selectionStart != -1 && selectionEnd != -1 && selectionStart == selectionEnd && (typefaceEmojiSpanArr = (TypefaceEmojiSpan[]) editable.getSpans(selectionStart, selectionEnd, TypefaceEmojiSpan.class)) != null && typefaceEmojiSpanArr.length > 0) {
            for (TypefaceEmojiSpan typefaceEmojiSpan : typefaceEmojiSpanArr) {
                int spanStart = editable.getSpanStart(typefaceEmojiSpan);
                int spanEnd = editable.getSpanEnd(typefaceEmojiSpan);
                if ((z && spanStart == selectionStart) || ((!z && spanEnd == selectionStart) || (selectionStart > spanStart && selectionStart < spanEnd))) {
                    editable.delete(spanStart, spanEnd);
                    return true;
                }
            }
        }
        return false;
    }

    public final boolean hasGlyph(CharSequence charSequence, int i, int i2, TypefaceEmojiRasterizer typefaceEmojiRasterizer) {
        if ((typefaceEmojiRasterizer.mCache & 3) == 0) {
            DefaultGlyphChecker defaultGlyphChecker = this.mGlyphChecker;
            MetadataItem metadataItem = typefaceEmojiRasterizer.getMetadataItem();
            int __offset = metadataItem.__offset(8);
            if (__offset != 0) {
                metadataItem.bb.getShort(__offset + metadataItem.bb_pos);
            }
            defaultGlyphChecker.getClass();
            ThreadLocal threadLocal = DefaultGlyphChecker.sStringBuilder;
            if (threadLocal.get() == null) {
                threadLocal.set(new StringBuilder());
            }
            StringBuilder sb = (StringBuilder) threadLocal.get();
            sb.setLength(0);
            while (i < i2) {
                sb.append(charSequence.charAt(i));
                i++;
            }
            TextPaint textPaint = defaultGlyphChecker.mTextPaint;
            String sb2 = sb.toString();
            int i3 = PaintCompat.$r8$clinit;
            boolean hasGlyph = textPaint.hasGlyph(sb2);
            int i4 = typefaceEmojiRasterizer.mCache & 4;
            typefaceEmojiRasterizer.mCache = hasGlyph ? i4 | 2 : i4 | 1;
        }
        return (typefaceEmojiRasterizer.mCache & 3) == 2;
    }

    public final Object process(CharSequence charSequence, int i, int i2, int i3, boolean z, EmojiProcessCallback emojiProcessCallback) {
        int i4;
        char c;
        ProcessorSm processorSm = new ProcessorSm(this.mMetadataRepo.mRootNode);
        int codePointAt = Character.codePointAt(charSequence, i);
        boolean z2 = true;
        int i5 = 0;
        int i6 = i;
        loop0: while (true) {
            i4 = i6;
            while (i6 < i2 && i5 < i3 && z2) {
                SparseArray sparseArray = processorSm.mCurrentNode.mChildren;
                MetadataRepo.Node node = sparseArray == null ? null : (MetadataRepo.Node) sparseArray.get(codePointAt);
                if (processorSm.mState == 2) {
                    if (node != null) {
                        processorSm.mCurrentNode = node;
                        processorSm.mCurrentDepth++;
                    } else {
                        if (codePointAt == 65038) {
                            processorSm.reset();
                        } else if (codePointAt != 65039) {
                            MetadataRepo.Node node2 = processorSm.mCurrentNode;
                            if (node2.mData != null) {
                                if (processorSm.mCurrentDepth != 1) {
                                    processorSm.mFlushNode = node2;
                                    processorSm.reset();
                                } else if (processorSm.shouldUseEmojiPresentationStyleForSingleCodepoint()) {
                                    processorSm.mFlushNode = processorSm.mCurrentNode;
                                    processorSm.reset();
                                } else {
                                    processorSm.reset();
                                }
                                c = 3;
                            } else {
                                processorSm.reset();
                            }
                        }
                        c = 1;
                    }
                    c = 2;
                } else if (node == null) {
                    processorSm.reset();
                    c = 1;
                } else {
                    processorSm.mState = 2;
                    processorSm.mCurrentNode = node;
                    processorSm.mCurrentDepth = 1;
                    c = 2;
                }
                processorSm.mLastCodepoint = codePointAt;
                if (c == 1) {
                    i6 = Character.charCount(Character.codePointAt(charSequence, i4)) + i4;
                    if (i6 < i2) {
                        codePointAt = Character.codePointAt(charSequence, i6);
                    }
                } else if (c == 2) {
                    int charCount = Character.charCount(codePointAt) + i6;
                    if (charCount < i2) {
                        codePointAt = Character.codePointAt(charSequence, charCount);
                    }
                    i6 = charCount;
                } else if (c == 3) {
                    if (z || !hasGlyph(charSequence, i4, i6, processorSm.mFlushNode.mData)) {
                        z2 = emojiProcessCallback.handleEmoji(charSequence, i4, i6, processorSm.mFlushNode.mData);
                        i5++;
                    }
                }
            }
        }
        if (processorSm.mState == 2 && processorSm.mCurrentNode.mData != null && ((processorSm.mCurrentDepth > 1 || processorSm.shouldUseEmojiPresentationStyleForSingleCodepoint()) && i5 < i3 && z2 && (z || !hasGlyph(charSequence, i4, i6, processorSm.mCurrentNode.mData)))) {
            emojiProcessCallback.handleEmoji(charSequence, i4, i6, processorSm.mCurrentNode.mData);
        }
        return emojiProcessCallback.getResult();
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class EmojiProcessLookupCallback implements EmojiProcessCallback {
        public final int mOffset;
        public int start = -1;
        public int end = -1;

        public EmojiProcessLookupCallback(int i) {
            this.mOffset = i;
        }

        @Override // androidx.emoji2.text.EmojiProcessor.EmojiProcessCallback
        public final boolean handleEmoji(CharSequence charSequence, int i, int i2, TypefaceEmojiRasterizer typefaceEmojiRasterizer) {
            int i3 = this.mOffset;
            if (i > i3 || i3 >= i2) {
                return i2 <= i3;
            }
            this.start = i;
            this.end = i2;
            return false;
        }

        @Override // androidx.emoji2.text.EmojiProcessor.EmojiProcessCallback
        public final Object getResult() {
            return this;
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class MarkExclusionCallback implements EmojiProcessCallback {
        public final String mExclusion;

        public MarkExclusionCallback(String str) {
            this.mExclusion = str;
        }

        @Override // androidx.emoji2.text.EmojiProcessor.EmojiProcessCallback
        public final boolean handleEmoji(CharSequence charSequence, int i, int i2, TypefaceEmojiRasterizer typefaceEmojiRasterizer) {
            if (!TextUtils.equals(charSequence.subSequence(i, i2), this.mExclusion)) {
                return true;
            }
            typefaceEmojiRasterizer.mCache = (typefaceEmojiRasterizer.mCache & 3) | 4;
            return false;
        }

        @Override // androidx.emoji2.text.EmojiProcessor.EmojiProcessCallback
        public final Object getResult() {
            return this;
        }
    }
}
