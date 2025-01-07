package com.airbnb.lottie.parser.moshi;

import androidx.constraintlayout.core.PriorityGoalRow$GoalVariableAccessor$$ExternalSyntheticOutline0;
import java.io.Closeable;
import java.util.Arrays;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class JsonReader implements Closeable {
    public static final String[] REPLACEMENT_CHARS = new String[128];
    public int[] pathIndices;
    public String[] pathNames;
    public int[] scopes;
    public int stackSize;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Options {
        public final okio.Options doubleQuoteSuffix;
        public final String[] strings;

        public Options(String[] strArr, okio.Options options) {
            this.strings = strArr;
            this.doubleQuoteSuffix = options;
        }

        /* JADX WARN: Removed duplicated region for block: B:15:0x003a A[Catch: IOException -> 0x005f, TryCatch #0 {IOException -> 0x005f, blocks: (B:2:0x0000, B:3:0x000a, B:5:0x000d, B:7:0x001e, B:9:0x0026, B:13:0x0046, B:15:0x003a, B:16:0x003d, B:27:0x004b, B:29:0x004e, B:32:0x0061), top: B:1:0x0000 }] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public static com.airbnb.lottie.parser.moshi.JsonReader.Options of(java.lang.String... r12) {
            /*
                int r0 = r12.length     // Catch: java.io.IOException -> L5f
                okio.ByteString[] r0 = new okio.ByteString[r0]     // Catch: java.io.IOException -> L5f
                okio.Buffer r1 = new okio.Buffer     // Catch: java.io.IOException -> L5f
                r1.<init>()     // Catch: java.io.IOException -> L5f
                r2 = 0
                r3 = r2
            La:
                int r4 = r12.length     // Catch: java.io.IOException -> L5f
                if (r3 >= r4) goto L61
                r4 = r12[r3]     // Catch: java.io.IOException -> L5f
                java.lang.String[] r5 = com.airbnb.lottie.parser.moshi.JsonReader.REPLACEMENT_CHARS     // Catch: java.io.IOException -> L5f
                r6 = 34
                r1.writeByte(r6)     // Catch: java.io.IOException -> L5f
                int r7 = r4.length()     // Catch: java.io.IOException -> L5f
                r8 = r2
                r9 = r8
            L1c:
                if (r8 >= r7) goto L49
                char r10 = r4.charAt(r8)     // Catch: java.io.IOException -> L5f
                r11 = 128(0x80, float:1.8E-43)
                if (r10 >= r11) goto L2b
                r10 = r5[r10]     // Catch: java.io.IOException -> L5f
                if (r10 != 0) goto L38
                goto L46
            L2b:
                r11 = 8232(0x2028, float:1.1535E-41)
                if (r10 != r11) goto L32
                java.lang.String r10 = "\\u2028"
                goto L38
            L32:
                r11 = 8233(0x2029, float:1.1537E-41)
                if (r10 != r11) goto L46
                java.lang.String r10 = "\\u2029"
            L38:
                if (r9 >= r8) goto L3d
                r1.writeUtf8(r4, r9, r8)     // Catch: java.io.IOException -> L5f
            L3d:
                int r9 = r10.length()     // Catch: java.io.IOException -> L5f
                r1.writeUtf8(r10, r2, r9)     // Catch: java.io.IOException -> L5f
                int r9 = r8 + 1
            L46:
                int r8 = r8 + 1
                goto L1c
            L49:
                if (r9 >= r7) goto L4e
                r1.writeUtf8(r4, r9, r7)     // Catch: java.io.IOException -> L5f
            L4e:
                r1.writeByte(r6)     // Catch: java.io.IOException -> L5f
                r1.readByte()     // Catch: java.io.IOException -> L5f
                long r4 = r1.size     // Catch: java.io.IOException -> L5f
                okio.ByteString r4 = r1.readByteString(r4)     // Catch: java.io.IOException -> L5f
                r0[r3] = r4     // Catch: java.io.IOException -> L5f
                int r3 = r3 + 1
                goto La
            L5f:
                r12 = move-exception
                goto L71
            L61:
                com.airbnb.lottie.parser.moshi.JsonReader$Options r1 = new com.airbnb.lottie.parser.moshi.JsonReader$Options     // Catch: java.io.IOException -> L5f
                java.lang.Object r12 = r12.clone()     // Catch: java.io.IOException -> L5f
                java.lang.String[] r12 = (java.lang.String[]) r12     // Catch: java.io.IOException -> L5f
                okio.Options r0 = okio.Options.of(r0)     // Catch: java.io.IOException -> L5f
                r1.<init>(r12, r0)     // Catch: java.io.IOException -> L5f
                return r1
            L71:
                java.lang.AssertionError r0 = new java.lang.AssertionError
                r0.<init>(r12)
                throw r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.airbnb.lottie.parser.moshi.JsonReader.Options.of(java.lang.String[]):com.airbnb.lottie.parser.moshi.JsonReader$Options");
        }
    }

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Token {
        public static final /* synthetic */ Token[] $VALUES;
        public static final Token BEGIN_ARRAY;
        public static final Token BEGIN_OBJECT;
        public static final Token BOOLEAN;
        public static final Token END_ARRAY;
        public static final Token END_DOCUMENT;
        public static final Token END_OBJECT;
        public static final Token NAME;
        public static final Token NULL;
        public static final Token NUMBER;
        public static final Token STRING;

        static {
            Token token = new Token("BEGIN_ARRAY", 0);
            BEGIN_ARRAY = token;
            Token token2 = new Token("END_ARRAY", 1);
            END_ARRAY = token2;
            Token token3 = new Token("BEGIN_OBJECT", 2);
            BEGIN_OBJECT = token3;
            Token token4 = new Token("END_OBJECT", 3);
            END_OBJECT = token4;
            Token token5 = new Token("NAME", 4);
            NAME = token5;
            Token token6 = new Token("STRING", 5);
            STRING = token6;
            Token token7 = new Token("NUMBER", 6);
            NUMBER = token7;
            Token token8 = new Token("BOOLEAN", 7);
            BOOLEAN = token8;
            Token token9 = new Token("NULL", 8);
            NULL = token9;
            Token token10 = new Token("END_DOCUMENT", 9);
            END_DOCUMENT = token10;
            $VALUES = new Token[]{token, token2, token3, token4, token5, token6, token7, token8, token9, token10};
        }

        public static Token valueOf(String str) {
            return (Token) Enum.valueOf(Token.class, str);
        }

        public static Token[] values() {
            return (Token[]) $VALUES.clone();
        }
    }

    static {
        for (int i = 0; i <= 31; i++) {
            REPLACEMENT_CHARS[i] = String.format("\\u%04x", Integer.valueOf(i));
        }
        String[] strArr = REPLACEMENT_CHARS;
        strArr[34] = "\\\"";
        strArr[92] = "\\\\";
        strArr[9] = "\\t";
        strArr[8] = "\\b";
        strArr[10] = "\\n";
        strArr[13] = "\\r";
        strArr[12] = "\\f";
    }

    public abstract void beginArray();

    public abstract void beginObject();

    public abstract void endArray();

    public abstract void endObject();

    public final String getPath() {
        int i = this.stackSize;
        int[] iArr = this.scopes;
        String[] strArr = this.pathNames;
        int[] iArr2 = this.pathIndices;
        StringBuilder sb = new StringBuilder("$");
        for (int i2 = 0; i2 < i; i2++) {
            int i3 = iArr[i2];
            if (i3 == 1 || i3 == 2) {
                sb.append('[');
                sb.append(iArr2[i2]);
                sb.append(']');
            } else if (i3 == 3 || i3 == 4 || i3 == 5) {
                sb.append('.');
                String str = strArr[i2];
                if (str != null) {
                    sb.append(str);
                }
            }
        }
        return sb.toString();
    }

    public abstract boolean hasNext();

    public abstract boolean nextBoolean();

    public abstract double nextDouble();

    public abstract int nextInt();

    public abstract String nextString();

    public abstract Token peek();

    public final void pushScope(int i) {
        int i2 = this.stackSize;
        int[] iArr = this.scopes;
        if (i2 == iArr.length) {
            if (i2 == 256) {
                throw new JsonDataException("Nesting too deep at " + getPath());
            }
            this.scopes = Arrays.copyOf(iArr, iArr.length * 2);
            String[] strArr = this.pathNames;
            this.pathNames = (String[]) Arrays.copyOf(strArr, strArr.length * 2);
            int[] iArr2 = this.pathIndices;
            this.pathIndices = Arrays.copyOf(iArr2, iArr2.length * 2);
        }
        int[] iArr3 = this.scopes;
        int i3 = this.stackSize;
        this.stackSize = i3 + 1;
        iArr3[i3] = i;
    }

    public abstract int selectName(Options options);

    public abstract void skipName();

    public abstract void skipValue();

    public final void syntaxError(String str) {
        StringBuilder m = PriorityGoalRow$GoalVariableAccessor$$ExternalSyntheticOutline0.m(str, " at path ");
        m.append(getPath());
        throw new JsonEncodingException(m.toString());
    }
}
