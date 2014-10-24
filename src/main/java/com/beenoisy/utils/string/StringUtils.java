package com.beenoisy.utils.string;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.channels.FileChannel;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

/**
 * utils for string <br>
 * 2014年10月24日:下午2:50:19
 * 
 * @author Keen | jacks808@163.com <br>
 */
public class StringUtils {
    public static float checkToDefaultValue(final Float n) {
        return checkToDefaultValue(n, 0f);
    }

    public static int[] checkToDefaultValue(final int[] arrays) {
        return arrays == null ? EMPTY_INT_ARRAY : arrays;
    }

    public static int checkToDefaultValue(final Integer n) {
        return checkToDefaultValue(n, 0);
    }

    public static String checkToDefaultValue(final String n) {
        return checkToDefaultValue(n, "");
    }

    @SuppressWarnings("unchecked")
    public static <T extends Collection<?>> T checkToDefaultValue(T collection) {
        if (collection == null) {
            collection = (T) Collections.emptyList();
        }
        return collection;
    }

    @SuppressWarnings("unchecked")
    public static <T extends List<?>> T checkToDefaultValue(T collection) {
        if (collection == null) {
            collection = (T) Collections.emptyList();
        }
        return collection;
    }

    @SuppressWarnings("unchecked")
    public static <T extends Set<?>> T checkToDefaultValue(T collection) {
        if (collection == null) {
            collection = (T) Collections.emptySet();
        }
        return collection;
    }

    @SuppressWarnings("unchecked")
    public static <T extends Map<?, ?>> T checkToDefaultValue(T collection) {
        if (collection == null) {
            collection = (T) Collections.emptyMap();
        }
        return collection;
    }

    /**
     * 检测参数,若参数为null,则返回默认值
     * 
     * @param n
     * @param defaultValue
     *            指定的默认值
     * @return
     */
    public static <T> T checkToDefaultValue(final T n, final T defaultValue) {
        return n == null ? defaultValue : n;
    }

    public static boolean copyFile(final File src, final File target) {
        try {
            final FileInputStream fin = new FileInputStream(src);
            final FileOutputStream fout = new FileOutputStream(target);
            final FileChannel in = fin.getChannel(), out = fout.getChannel();
            out.transferFrom(in, 0, src.length());
            in.close();
            out.close();
            fin.close();
            fout.close();
            return true;
        } catch (final Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static String getExtention(final String fileName) {
        final int pos = fileName.lastIndexOf(".");
        return fileName.substring(pos).toLowerCase();
    }

    public static String getPasswordString(final int length) {
        if (length > 0) {
            final char[] result = new char[length];
            int index = 0, rand = RANDOM.nextInt();
            for (int i = 0; i < length % 5; i++) {
                result[index++] = ch[(byte) rand & 63];
                rand >>= 6;
            }
            for (int i = length / 5; i > 0; i--) {
                rand = RANDOM.nextInt();
                for (int j = 0; j < 5; j++) {
                    result[index++] = ch[(byte) rand & 63];
                    rand >>= 6;
                }
            }
            return new String(result, 0, length);
        } else if (length == 0) {
            return "";
        } else {
            throw new IllegalArgumentException();
        }
    }

    /**
     * 用Joiner吧.
     * 
     * @param <T>
     * @param o
     * @return
     */
    public static <T> String join(final Collection<T> o) {
        if (o != null) {
            final StringBuilder sb = new StringBuilder();
            boolean s = false;
            for (final T i : o) {
                if (s) {
                    sb.append(",");
                } else {
                    s = true;
                }
                sb.append(i);
            }
            return sb.toString();
        } else {
            return "";
        }
    }

    /**
     * 将一个数组转换为字符串连接
     * 
     * @param array
     *            必须是数组
     * @return
     */
    public static String join(Object array) {
        if (array != null) {
            if (array instanceof Collection) {
                array = ((Collection<?>) array).toArray();
            }

            final StringBuilder sb = new StringBuilder();
            final int len = Array.getLength(array);
            for (int index = 0; index < len; index++) {
                if (index != 0) {
                    sb.append(",");
                }
                sb.append(Array.get(array, index));
            }
            return sb.toString();
        } else {
            return "";
        }
    }

    public static boolean maskTaskByBit(final long source, final int bit) {
        final long mask = 1 << (bit - 1);
        return maskTest(source, mask);
    }

    public static boolean maskTest(final int source, final int mask) {
        return (source & mask) == mask;
    }

    public static boolean maskTest(final long source, final long mask) {
        return (source & mask) == mask;
    }

    public static String md5(final byte b[]) {
        int i;
        final StringBuffer buf = new StringBuffer("");
        for (final byte element : b) {
            i = element;
            if (i < 0) {
                i += 256;
            }
            if (i < 16) {
                buf.append("0");
            }
            buf.append(Integer.toHexString(i));
        }
        return buf.toString();
    }

    public static String md5(final File file) {
        FileInputStream fin = null;
        try {
            fin = new FileInputStream(file);
            final byte[] b = new byte[8192];
            int length = -1;
            final MessageDigest md = MessageDigest.getInstance("MD5");
            while ((length = fin.read(b)) != -1) {
                md.update(b, 0, length);
            }
            return md5(md.digest());
        } catch (final Exception e) {
            e.printStackTrace();
        } finally {
            if (fin != null) {
                try {
                    fin.close();
                } catch (final IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    public static String md5(final Object... objects) {
        return md5(Arrays.toString(objects));
    }

    public static String md5(final String plainText) {
        if (plainText != null) {
            try {
                final MessageDigest md = MessageDigest.getInstance("MD5");
                md.update(plainText.getBytes());
                final byte b[] = md.digest();
                return md5(b);
            } catch (final NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    private static int rand(final int lo, final int hi) {
        final int n = hi - lo + 1;
        int i = RANDOM.nextInt() % n;
        if (i < 0) {
            i = -i;
        }
        return lo + i;
    }

    public static String randomstring(final int lo, final int hi) {
        final int len = rand(lo, hi);
        final byte b[] = new byte[len];
        for (int i = 0; i < len; i++) {
            int mux = RANDOM.nextInt() % 3;
            if (mux < 0) {
                mux = -mux;
            }
            switch (mux) {
            case 0:
                b[i] = (byte) rand('a', 'z');
                break;
            case 1:
                b[i] = (byte) rand('A', 'Z');
                break;
            case 2:
                b[i] = (byte) rand('0', '9');
                break;
            }
        }
        return new String(b, 0, len);
    }

    public static String randomString(final int len) {
        return randomstring(len, len);
    }

    public static int[] splitToInt(final String string, final String delimiter) {
        final String[] source = string.split(delimiter);
        if (source != null) {
            final int length = source.length;
            final int[] result = new int[length];
            for (int i = 0; i < length; i++) {
                result[i] = Integer.valueOf(source[i]);
            }
            return result;
        }
        return null;
    }

    /**
     * @param string
     * @param delimiter
     * @return
     */
    public static Integer[] splitToInteger(final String string, final String delimiter) {
        final String[] source = string.split(delimiter);
        if (source != null) {
            final int length = source.length;
            final Integer[] result = new Integer[length];
            for (int i = 0; i < length; i++) {
                result[i] = Integer.valueOf(source[i]);
            }
            return result;
        }
        return null;
    }

    public static String str2unicode(final String s) {
        final StringBuilder uStr = new StringBuilder("");
        if (s == null) {
            return "";
        }
        final int size = s.length();
        for (int i = 0; i < size; i++) {
            final int iValue = s.codePointAt(i);
            if (iValue < 16) {
                uStr.append("\\u000").append(Integer.toHexString(iValue));
            } else if (iValue < 256) {
                uStr.append("\\u00").append(Integer.toHexString(iValue));
            } else if (iValue < 4096) {
                uStr.append("\\u0").append(Integer.toHexString(iValue));
            } else {
                uStr.append("\\u").append(Integer.toHexString(iValue));
            }
        }
        return uStr.toString();
    }

    public static int strlen(String s) {
        if (s == null) {
            return 0;
        }
        int len = 0;
        s = s.trim();
        for (int i = 0; i < s.length(); i++) {
            if (s.codePointAt(i) > 255) {
                len += 2;
            } else {
                ++len;
            }
        }
        return len;
    }

    public static int strlen2(String s) {
        if (s == null) {
            return 0;
        }
        int len = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.codePointAt(i) > 255) {
                len += 2;
            } else {
                ++len;
            }
        }
        return len;
    }

    public static final Random RANDOM = new Random(System.currentTimeMillis());

    private static char ch[] = {//
    '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',//
                    'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',//
                    'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',//
                    'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm',//
                    'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',//
                    '0', '1' };

    private static final int[] EMPTY_INT_ARRAY = new int[] {};
}
