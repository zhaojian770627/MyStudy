/* ***** BEGIN LICENSE BLOCK *****
 * Version: MPL 1.1/GPL 2.0/LGPL 2.1
 *
 * The contents of this file are subject to the Mozilla Public License Version
 * 1.1 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 * http://www.mozilla.org/MPL/
 *
 * Software distributed under the License is distributed on an "AS IS" basis,
 * WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License
 * for the specific language governing rights and limitations under the
 * License.
 *
 * The Original Code is part of dcm4che, an implementation of DICOM(TM) in
 * Java(TM), hosted at http://sourceforge.net/projects/dcm4che.
 *
 * The Initial Developer of the Original Code is
 * Gunter Zeilinger, Huetteldorferstr. 24/10, 1150 Vienna/Austria/Europe.
 * Portions created by the Initial Developer are Copyright (C) 2008
 * the Initial Developer. All Rights Reserved.
 *
 * Contributor(s):
 * See listed authors below.
 *
 * Alternatively, the contents of this file may be used under the terms of
 * either the GNU General Public License Version 2 or later (the "GPL"), or
 * the GNU Lesser General Public License Version 2.1 or later (the "LGPL"),
 * in which case the provisions of the GPL or the LGPL are applicable instead
 * of those above. If you wish to allow use of your version of this file only
 * under the terms of either the GPL or the LGPL, and not to allow others to
 * use your version of this file under the terms of the MPL, indicate your
 * decision by deleting the provisions above and replace them with the notice
 * and other provisions required by the GPL or the LGPL. If you do not delete
 * the provisions above, a recipient may use your version of this file under
 * the terms of any one of the MPL, the GPL or the LGPL.
 *
 * ***** END LICENSE BLOCK ***** */
package org.dcm4che2.base64;

/**
 * @author Gunter Zeilinger <gunterze@gmail.com>
 *
 * @version $Revision$ $Date$
 * @since Jun 29, 2009
 */
public class Base64Decoder {

    public static byte[] decode(byte[] in) {
        if ((in.length & 3) != 0) {
            throw new IllegalArgumentException(
                    "length: " + in.length + " % 4 != 0");
        }
        int padding = in.length > 0 && in[in.length - 1] == '='
                    ? in.length > 1 && in[in.length - 2] == '=' ? 2 : 1 : 0;
        byte[] out = new byte[in.length / 4 * 3 - padding];
        int inlen = in.length - padding;
        int i = 3;
        int j = 0;
        int b2,b3;
        for (; i < inlen; i += 4) {
            out[j++] = (byte)((INV_BASE64[in[i-3]] << 2)
                    | ((b2 = INV_BASE64[in[i-2]]) >>> 4));
            out[j++] = (byte)((b2 << 4)
                    | ((b3 = INV_BASE64[in[i-1]]) >>> 2));
            out[j++] = (byte)((b3 << 6) | INV_BASE64[in[i]]);
        }
        if (padding > 0) {
            out[j++] = (byte)((INV_BASE64[in[i-3]] << 2)
                    | ((b2 = INV_BASE64[in[i-2]]) >>> 4));
            if (padding < 2) {
                out[j++] = (byte)((b2 << 4)
                        | ((b3 = INV_BASE64[in[i-1]]) >>> 2));
            }
        }
        assert j == out.length;
        return out;
    }

    public static byte[] decode(CharSequence in) {
        int inlen = in.length();
        if ((inlen & 3) != 0) {
            throw new IllegalArgumentException(
                    "length: " + in.length() + " % 4 != 0");
        }
        int padding = inlen > 0 && in.charAt(inlen-1) == '='
                    ? inlen > 1 && in.charAt(inlen-2) == '=' ? 2 : 1 : 0;
        byte[] out = new byte[inlen / 4 * 3 - padding];
        inlen -= padding;
        int i = 3;
        int j = 0;
        int b2,b3;
        for (; i < inlen; i += 4) {
            out[j++] = (byte)((INV_BASE64[in.charAt(i-3)] << 2)
                    | ((b2 = INV_BASE64[in.charAt(i-2)]) >>> 4));
            out[j++] = (byte)((b2 << 4)
                    | ((b3 = INV_BASE64[in.charAt(i-1)]) >>> 2));
            out[j++] = (byte)((b3 << 6) | INV_BASE64[in.charAt(i)]);
        }
        if (padding > 0) {
            out[j++] = (byte)((INV_BASE64[in.charAt(i-3)] << 2)
                    | ((b2 = INV_BASE64[in.charAt(i-2)]) >>> 4));
            if (padding < 2) {
                out[j++] = (byte)((b2 << 4)
                        | ((b3 = INV_BASE64[in.charAt(i-1)]) >>> 2));
            }
        }
        assert j == out.length;
        return out;
    }

    private static final byte INV_BASE64[] = {
        -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
        -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
        -1, -1, -1, -1, -1, -1, -1, -1, -1, 62, -1, -1, -1, 63, 52, 53, 54,
        55, 56, 57, 58, 59, 60, 61, -1, -1, -1, -1, -1, -1, -1, 0, 1, 2, 3, 4,
        5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23,
        24, 25, -1, -1, -1, -1, -1, -1, 26, 27, 28, 29, 30, 31, 32, 33, 34,
        35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51
    };
}
