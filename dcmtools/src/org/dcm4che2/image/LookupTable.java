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
 * Portions created by the Initial Developer are Copyright (C) 2002-2005
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

package org.dcm4che2.image;

import java.awt.image.ComponentSampleModel;
import java.awt.image.DataBuffer;
import java.awt.image.DataBufferByte;
import java.awt.image.DataBufferInt;
import java.awt.image.DataBufferShort;
import java.awt.image.DataBufferUShort;
import java.awt.image.Raster;

import org.dcm4che2.data.DicomObject;
import org.dcm4che2.data.Tag;
import org.dcm4che2.util.ByteUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Gunter Zeilinger <gunterze@gmail.com>
 * @version $Revision$ $Date$
 * @since Jul 23, 2007
 */
public abstract class LookupTable {
    private static final Logger log = 
        LoggerFactory.getLogger(LookupTable.class);

    public static final String LINEAR = "LINEAR";
    public static final String SIGMOID = "SIGMOID";

    private static final int OPAQUE = 255;

    protected final int inBits;

    protected final int andmask;

    protected final int ormask;

    protected final int signbit;

    protected final boolean preserve;

    protected int outBits;

    protected int off;

    protected LookupTable(int inBits, boolean signed, int off, int outBits,
            boolean preserve) {
        this.inBits = inBits;
        this.outBits = outBits;
        this.andmask = (1 << inBits) - 1;
        this.ormask = ~andmask;
        this.signbit = signed ? 1 << (inBits - 1) : 0;
        this.off = (off & signbit) != 0 ? (off | ormask) : off;
        this.preserve = preserve;
    }

    public final int getOffset() {
        return off;
    }

    public abstract int length();

    public abstract byte lookupByte(int in);

    public abstract short lookupShort(int in);

    public abstract int lookup(int in);

    public abstract byte[] lookup(byte[] src, int srcPos, byte[] dst,
            int dstPos, int length, int channels, int skip);

    public abstract short[] lookup(byte[] src, int srcPos, short[] dst,
            int dstPos, int length);

    public abstract int[] lookup(byte[] src, int srcPos, int[] dst,
            int dstPos, int length, int alpha);

    public abstract byte[] lookup(short[] src, int srcPos, byte[] dst,
            int dstPos, int length);

    public abstract short[] lookup(short[] src, int srcPos, short[] dst,
            int dstPos, int length);

    public abstract int[] lookup(short[] src, int srcPos, int[] dst,
            int dstPos, int length, int alpha);

    public byte[] lookup(byte[] src, byte[] dst) {
        return lookup(src, 0, dst, 0, src.length, 1,0);
    }

    public short[] lookup(byte[] src, short[] dst) {
        return lookup(src, 0, dst, 0, src.length);
    }

    public int[] lookup(byte[] src, int[] dst, int alpha) {
        return lookup(src, 0, dst, 0, src.length, alpha);
    }

    public byte[] lookup(short[] src, byte[] dst) {
        return lookup(src, 0, dst, 0, src.length);
    }

    public short[] lookup(short[] src, int srcPos, short[] dst) {
        return lookup(src, 0, dst, 0, src.length);
    }

    public int[] lookup(short[] src, int[] dst,int alpha) {
        return lookup(src, 0, dst, 0, src.length, alpha);
    }

    public void lookup(Raster src, Raster dst) {
        lookup(src, dst, OPAQUE);
    }
    
    public void lookup(Raster src, Raster dst, int alpha) {
    	lookup(src,dst,alpha,1,0);    	
    }
    
    public void lookup(Raster src, Raster dst, int alpha, int channels, int skip) {
        int srcWidth = src.getWidth();
        int srcHeight = src.getHeight();
        int srcScanlineStride = ((ComponentSampleModel) src.getSampleModel())
                .getScanlineStride();
        DataBuffer srcdata = src.getDataBuffer();
        int dstWidth = src.getWidth();
        int dstHeight = src.getHeight();
        int dstScanlineStride = ((ComponentSampleModel) dst.getSampleModel())
                .getScanlineStride();
        DataBuffer dstdata = dst.getDataBuffer();
        if (srcWidth != dstWidth) {
            throw new IllegalArgumentException("src.width:" + srcWidth
                    + " != dst.width:" + dstWidth);
        }
        if (srcHeight != dstHeight) {
            throw new IllegalArgumentException("src.height:" + srcWidth
                    + " != dst.height:" + dstWidth);
        }
        if (srcHeight * srcScanlineStride != srcdata.getSize()) {
            throw new IllegalArgumentException("srcHeight:" + srcHeight
                    + " * srcScanlineStride:" + srcScanlineStride
                    + " != src.length:" + srcdata.getSize());
        }
        if (srcHeight * dstScanlineStride != dstdata.getSize()) {
            throw new IllegalArgumentException("srcHeight:" + srcHeight
                    + " * dstScanlineStride:" + dstScanlineStride
                    + " != dst.length:" + dstdata.getSize());
        }
        switch (dstdata.getDataType()) {
        case DataBuffer.TYPE_BYTE:
            lookup(srcdata, srcWidth, srcHeight, srcScanlineStride,
                    ((DataBufferByte) dstdata).getData(), dstScanlineStride, channels, skip);
            break;
        case DataBuffer.TYPE_USHORT:
            lookup(srcdata, srcWidth, srcHeight, srcScanlineStride,
                    ((DataBufferUShort) dstdata).getData(), dstScanlineStride);
            break;
        case DataBuffer.TYPE_SHORT:
            lookup(srcdata, srcWidth, srcHeight, srcScanlineStride,
                    ((DataBufferShort) dstdata).getData(), dstScanlineStride);
            break;
        case DataBuffer.TYPE_INT:
            lookup(srcdata, srcWidth, srcHeight, srcScanlineStride,
                    ((DataBufferInt) dstdata).getData(), dstScanlineStride, alpha);
            break;
        default:
            throw new IllegalArgumentException(
                    "Illegal Type of Destination DataBuffer: " + dst);
        }
    }

    public void lookup(DataBuffer src, int srcWidth, int srcHeight,
            int srcScanlineStride, byte[] dst, int dstScanlineStride, int channels, int skip) {
        switch (src.getDataType()) {
        case DataBuffer.TYPE_BYTE:
            lookup(((DataBufferByte) src).getData(), srcWidth, srcHeight,
                    srcScanlineStride, dst, dstScanlineStride, channels, skip);
            break;
        case DataBuffer.TYPE_USHORT:
            lookup(((DataBufferUShort) src).getData(), srcWidth, srcHeight,
                    srcScanlineStride, dst, dstScanlineStride);
            break;
        case DataBuffer.TYPE_SHORT:
            lookup(((DataBufferShort) src).getData(), srcWidth, srcHeight,
                    srcScanlineStride, dst, dstScanlineStride);
            break;
        default:
            throw new IllegalArgumentException(
                    "Illegal Type of Source DataBuffer: " + src);
        }            
    }

    public void lookup(DataBuffer src, int srcWidth, int srcHeight,
            int srcScanlineStride, short[] dst, int dstScanlineStride) {
        switch (src.getDataType()) {
        case DataBuffer.TYPE_BYTE:
            lookup(((DataBufferByte) src).getData(), srcWidth, srcHeight,
                    srcScanlineStride, dst, dstScanlineStride);
            break;
        case DataBuffer.TYPE_USHORT:
            lookup(((DataBufferUShort) src).getData(), srcWidth, srcHeight,
                    srcScanlineStride, dst, dstScanlineStride);
            break;
        case DataBuffer.TYPE_SHORT:
            lookup(((DataBufferShort) src).getData(), srcWidth, srcHeight,
                    srcScanlineStride, dst, dstScanlineStride);
            break;
        default:
            throw new IllegalArgumentException(
                    "Illegal Type of Source DataBuffer: " + src);
        }            
    }

    public void lookup(DataBuffer src, int srcWidth, int srcHeight,
            int srcScanlineStride, int[] dst, int dstScanlineStride, int alpha) {
        switch (src.getDataType()) {
        case DataBuffer.TYPE_BYTE:
            lookup(((DataBufferByte) src).getData(), srcWidth, srcHeight,
                    srcScanlineStride, dst, dstScanlineStride, alpha);
            break;
        case DataBuffer.TYPE_USHORT:
            lookup(((DataBufferUShort) src).getData(), srcWidth, srcHeight,
                    srcScanlineStride, dst, dstScanlineStride, alpha);
            break;
        case DataBuffer.TYPE_SHORT:
            lookup(((DataBufferShort) src).getData(), srcWidth, srcHeight,
                    srcScanlineStride, dst, dstScanlineStride, alpha);
            break;
        default:
            throw new IllegalArgumentException(
                    "Illegal Type of Source DataBuffer: " + src);
        }            
    }

    public void lookup(byte[] src, int srcWidth, int srcHeight,
            int srcScanlineStride, byte[] dst, int dstScanlineStride, int channels, int skip) {
   		for (int y = 0; y < srcHeight; y++) {
   			lookup(src, y * srcScanlineStride, dst, y * dstScanlineStride,
   					srcWidth, channels, skip);
   		}
    }

    public void lookup(short[] src, int srcWidth, int srcHeight,
            int srcScanlineStride, byte[] dst, int dstScanlineStride) {
        for (int x = 0; x < srcHeight; x++) {
            lookup(src, x * srcScanlineStride, dst, x * dstScanlineStride,
                    srcWidth);
        }
    }

    public void lookup(byte[] src, int srcWidth, int srcHeight,
            int srcScanlineStride, short[] dst, int dstScanlineStride) {
        for (int x = 0; x < srcHeight; x++) {
            lookup(src, x * srcScanlineStride, dst, x * dstScanlineStride,
                    srcWidth);
        }
    }

    public void lookup(short[] src, int srcWidth, int srcHeight,
            int srcScanlineStride, short[] dst, int dstScanlineStride) {
        for (int x = 0; x < srcHeight; x++) {
            lookup(src, x * srcScanlineStride, dst, x * dstScanlineStride,
                    srcWidth);
        }
    }

    public void lookup(byte[] src, int srcWidth, int srcHeight,
            int srcScanlineStride, int[] dst, int dstScanlineStride, int alpha) {
        for (int x = 0; x < srcHeight; x++) {
            lookup(src, x * srcScanlineStride, dst, x * dstScanlineStride,
                    srcWidth, alpha);
        }
    }

    public void lookup(short[] src, int srcWidth, int srcHeight,
            int srcScanlineStride, int[] dst, int dstScanlineStride, int alpha) {
        for (int x = 0; x < srcHeight; x++) {
            lookup(src, x * srcScanlineStride, dst, x * dstScanlineStride,
                    srcWidth, alpha);
        }
    }

    protected abstract LookupTable scale(int outBits, boolean inverse,
            short[] pval2out);

    protected abstract LookupTable combine(LookupTable other, int outBits,
            boolean inverse, short[] pval2out);

    protected abstract LookupTable combine(LookupTable vlut, LookupTable plut,
            int outBits, boolean inverse, short[] pval2out);

    protected final int toIndex(int in) {
        return ((in & signbit) != 0 ? (in | ormask) : (in & andmask)) - off;
    }


    static int inBits(short[] pval2out) {
        switch (pval2out.length) {
        case 0x100:
            return 8;
        case 0x200:
            return 9;
        case 0x400:
            return 10;
        case 0x800:
            return 11;
        case 0x1000:
            return 12;
        case 0x2000:
            return 13;
        case 0x4000:
            return 14;
        case 0x8000:
            return 15;
        case 0x10000:
            return 16;
        default:
            throw new IllegalArgumentException(
                    "pval2out.length: " + pval2out.length + " != 2^[8..16]");
        }
    }
    
    /**
     * Create ramp or sigmoid LUT for given i/o range, Rescale Slope/Intercept
     * and Window Center/Width. Create linear LUT if Window Width = 0.
     * <p>
     * If <code>vlutFct</code> is <code>null</code> or <code>"LINEAR"</code>,
     * a ramp LUT will be created. If <code>vlutFct</code> is
     * <code>"SIGMOID"</code>, a sigmoid LUT will be created.
     * <p>
     * If <code>pval2out</code> is not <code>null</code>, the output will
     * be weighted according this function, where the highest input value 
     * (p-value) maps to array index length-1 and the highest output value
     * (2^outBits-1) is represented by 0xFFFF. Length of <code>pval2out</code>
     * must be equal to 2^inBits, with inBits in the range [8, 16].
     * 
     * @param inBits
     *            number of significant bits within input values
     * @param signed
     *            specifies if input values are signed or unsigned
     * @param outBits
     *            bit depth of output range
     * @param slope
     *            Rescale Slope (0028,1053)
     * @param intercept
     *            Rescale Intercept (0028,1052)
     * @param center
     *            Window Center (0028,1050)
     * @param width
     *            Window Width (0028,1051) or 0 (= no Window specified)
     * @param vlutFct
     *            VOI LUT Function (0028,1056)
     * @param inverse
     *            specifies if output shall be inverted
     * @param pval2out
     *            p-value to output map or <code>null</code>
     * @return created LUT
     */
    public static LookupTable createLut(int inBits, boolean signed,
            int outBits, float slope, float intercept, float center,
            float width, String vlutFct, boolean inverse, short[] pval2out) {
    	log.debug("Creating a slope/intercept LUT  "+slope+"/"+intercept+" WL c/w= "+center+"/"+width);
        if (width == 0 || vlutFct == null || LINEAR.equals(vlutFct)) {
            return createRampLut(inBits, signed, outBits, slope, intercept,
                    center, width, inverse, pval2out);
        } else if (SIGMOID.equals(vlutFct)) {
            return createSigmoidLut(inBits, signed, outBits, slope, intercept,
                    center, width, inverse, pval2out);            
        } else {
            throw new UnsupportedOperationException(
                    "Unsupported VOI LUT function: " + vlutFct);
        }
    }
    
    private static LookupTable createRampLut(int inBits, boolean signed,
                int outBits, float slope, float intercept, float center,
                float width, boolean inverse, short[] pval2out) {
    	log.debug("Ramp LUT "+slope+"/"+intercept+" c/w="+center+"/"+width+" inverse "+inverse);
        if (slope < 0) {
            slope = -slope;
            intercept = -intercept;
            center = 1 - center;
            inverse = !inverse;
        }
        // Some of the IHE Image Display COnsistency tests use
        // a negative width, so this should not throw an exception.
        if( width<0 ) {
        	width = -width;
        	inverse = !inverse;
        }
        int inRange = 1 << inBits;
        int inMin = signed ? -inRange / 2 : 0;
        int inMax = inMin + inRange - 1;
        int in1;
        int in2;
        if (width == 0) {
            in1 = inMin;
            in2 = inMax;
        } else {
            float c_05 = center - .5f;
            float w_2 = (width - 1f) / 2;
            in1 = (int) (((c_05 - w_2) - intercept) / slope);
            in2 = (int) (((c_05 + w_2) - intercept) / slope);
        }
        int off = Math.max(in1, inMin);
        int iMax = Math.min(in2, inMax) - off;
        int size = iMax + 1;
        int outBits1 = pval2out == null ? outBits : inBits(pval2out);
        int outRange = 1 << outBits1;
        int pval2outShift = 16 - outBits;
        int out1;
        int out2;
        if (inverse) {
            out1 = outRange - 1;
            out2 = 0;
        } else {
            out1 = 0;
            out2 = outRange - 1;
        }
        float m = (float) (out2 - out1) / (in2 - in1);
        float b = out1 + m * (off - in1) + .5f;
        if (outBits <= 8) {
            byte[] data = new byte[size];
            if (pval2out == null) {
                for (int i = 0; i < size; i++) {
                    data[i] = (byte) (m * i + b);
                }
                if (iMax + off == in2) {
                    data[iMax] = (byte) out2;
                }
            } else {
                for (int i = 0; i < size; i++) {
                    data[i] = (byte) ((pval2out[(int) (m * i + b)] & 0xffff)
                            >>> pval2outShift);
                }
                if (iMax + off == in2) {
                    data[iMax] = (byte) ((pval2out[out2] & 0xffff)
                            >>> pval2outShift);
                }               
            }
            return new ByteLookupTable(inBits, signed, off, outBits, data);
        }
        short[] data = new short[size];
        if (pval2out == null) {
            for (int i = 0; i < size; i++) {
                data[i] = (short) (m * i + b);
            }
            if (iMax + off == in2) {
                data[iMax] = (short) out2;
            }
        } else {
            for (int i = 0; i < size; i++) {
                data[i] = (short) ((pval2out[(int) (m * i + b)] & 0xffff)
                        >>> pval2outShift);
            }
            if (iMax + off == in2) {
                data[iMax] = (short) ((pval2out[out2] & 0xffff)
                        >>> pval2outShift);
            }               
        }
        return new ShortLookupTable(inBits, signed, off, outBits, data);
    }

    private static LookupTable createSigmoidLut(int inBits, boolean signed,
            int outBits, float slope, float intercept, float center,
            float width, boolean inverse, short[] pval2out) {
        int size = 1 << inBits;
        int off = signed ? -size / 2 : 0;
        int outBits1 = pval2out == null ? outBits : inBits(pval2out);
        int outRange = 1 << outBits1;
        int outMax = outRange - 1;
        int pval2outShift = 16 - outBits;
        float ic = (center - intercept) / slope - off;
        float k = -4 * slope / width;
        if (outBits <= 8) {
            byte[] data = new byte[size];
            for (int i = 0; i < size; i++) {
                int tmp = (int) (outRange / (1 + Math.exp((i - ic) * k)));
                if (inverse) {
                    tmp = outMax - tmp;
                }
                if (pval2out != null) {
                    tmp = (pval2out[tmp] & 0xffff) >>> pval2outShift;
                }
                data[i] = (byte) tmp;
            }
            return new ByteLookupTable(inBits, signed, off, outBits, data);
        }
        short[] data = new short[size];
        for (int i = 0; i < size; i++) {
            int tmp = (int) (outRange / (1 + Math.exp((i - ic) * k)));
            if (inverse) {
                tmp = outMax - tmp;
            }
            if (pval2out != null) {
                tmp = (pval2out[tmp] & 0xffff) >>> pval2outShift;
            }
            data[i] = (short) tmp;
        }
        return new ShortLookupTable(inBits, signed, off, outBits, data);
    }
    
    /**
     * Create LUT for given i/o range, non-linear Modality LUT and Window
     * Center/Width. Do not apply any Window if Window Width = 0.
     * <p>
     * If <code>pval2out</code> is not <code>null</code>, the output will
     * be weighted according this function, where the highest input value 
     * (p-value) maps to array index length-1 and the highest output value
     * (2^outBits-1) is represented by 0xFFFF. Length of <code>pval2out</code>
     * must be equal to 2^inBits, with inBits in the range [8, 16].
     * 
     * @param inBits
     *            number of significant bits within input values
     * @param signed
     *            specifies if input values are signed or unsigned
     * @param outBits
     *            bit depth of output range
     * @param mLut
     *            item of Modality LUT Sequence (0028,3000)
     * @param center
     *            Window Center (0028,1050)
     * @param width
     *            Window Width (0028,1051) or 0 (= no Window specified)
     * @param vlutFct
     *            VOI LUT Function (0028,1056)
     * @param inverse
     *            specifies if output shall be inverted
     * @param pval2out
     *            p-value to output map or <code>null</code>
     * @return created LUT
     */
    public static LookupTable createLut(int inBits, boolean signed,
            int outBits, DicomObject mLut, float center, float width,
            String vlutFct, boolean inverse, short[] pval2out) {
    	log.debug("Creating MLUT WL c/w="+center+"/"+width);
        LookupTable mlut = createLut(inBits, signed, mLut);
        if (width == 0) {
            return mlut.scale(outBits, inverse, pval2out);
        }
        LookupTable vlut = createLut(mlut.outBits, false, outBits, 1, 0,
                center, width, vlutFct, inverse, pval2out);
        return mlut.combine(vlut, outBits, false, null);
    }

    private static LookupTable createLut(int inBits, boolean signed,
            DicomObject ds) {
    	int[] desc = ds.getInts(Tag.LUTDescriptor);
        byte[] data = ds.getBytes(Tag.LUTData);
        if (desc == null) {
            throw new IllegalArgumentException("Missing LUT Descriptor!");
        }
        if (desc.length != 3) {
            throw new IllegalArgumentException(
                    "Illegal number of LUT Descriptor values: " + desc.length);
        }
        if (data == null) {
            throw new IllegalArgumentException("Missing LUT Data!");
        }
        int len = desc[0] == 0 ? 0x10000 : desc[0];
        int off = desc[1];
        int bits = desc[2];
        if (inBits == 0) {
            // ignore offset for P-LUT
            off = 0;
            for (int i = len - 1; i != 0; i >>>= 1) {
                ++inBits;
            }
        }
        if (data.length == len) {
            return new ByteLookupTable(inBits, signed, off, bits, data, true);
        } else if (data.length == len << 1) {
            short[] sdata = ds.bigEndian() ? ByteUtils.bytesBE2shorts(data) : ByteUtils
                    .bytesLE2shorts(data);
            // Get the actual number of bits in this lookup table.
            bits = 0;
            for( int maxVal = Math.max(sdata[0] & 0xFFFF, sdata[sdata.length-1] & 0xFFFF); maxVal!=0; maxVal >>>=1 ) {
            	bits++;
            }
            ShortLookupTable ret = new ShortLookupTable(inBits, signed, off, bits, sdata, true);
            // Uncomment out the following to print out the resulting table.
            //for(int i=0; i<sdata.length; i += (1+sdata.length/10)) {
            //	log.info("Sdata[i] = "+sdata[i] +" offset "+(i+off)+" lookup "+ret.lookup(i+off));
            //}
            return ret;
        }
        throw new IllegalArgumentException("LUT Data length: " + data.length
                + " mismatch entry value: " + len + " in LUT Descriptor");
    }

    /**
     * Create LUT for given i/o range, Rescale Slope/Intercept and non-linear
     * VOI LUT.
     * <p>
     * If <code>pval2out</code> is not <code>null</code>, the output will
     * be weighted according this function, where the highest input value 
     * (p-value) maps to array index length-1 and the highest output value
     * (2^outBits-1) is represented by 0xFFFF. Length of <code>pval2out</code>
     * must be equal to 2^inBits, with inBits in the range [8, 16].
     * 
     * @param inBits
     *            number of significant bits within input values
     * @param signed
     *            specifies if input values are signed or unsigned
     * @param outBits
     *            bit depth of output range
     * @param slope
     *            Rescale Slope (0028,1053)
     * @param intercept
     *            Rescale Intercept (0028,1052)
     * @param voiLut
     *            item of VOI LUT Sequence (0028,3010)
     * @param inverse
     *            specifies if output shall be inverted
     * @param pval2out
     *            p-value to output map or <code>null</code>
     * @return created LUT
     */
    public static LookupTable createLut(int inBits, boolean signed,
            int outBits, float slope, float intercept, DicomObject voiLut,
            boolean inverse, short[] pval2out) {
        return createLut(inBits, signed, slope, intercept, voiLut).scale(
                outBits, inverse, pval2out);
    }

    private static LookupTable createLut(int inBits, boolean signed,
            float slope, float intercept, DicomObject voiLut) {
    	log.debug("Creating slope/intercept LUT with V-LUT "+slope+"/"+intercept);
        if (slope == 1) {
            LookupTable lut = createLut(inBits, signed, voiLut);
            lut.off -= intercept;
            return lut;
        }
        LookupTable vlut = createLut(32, true, voiLut);
        float in1 = (vlut.off - intercept) / slope;
        float in2 = in1 + vlut.length() / slope;
        int off = (int) Math.floor(Math.min(in1, in2));
        int len = ((int) Math.ceil(Math.max(in1, in2))) - off;
        short[] data = new short[len];
        for (int i = 0; i < data.length; i++) {
            data[i] = vlut.lookupShort(Math.round(i * slope + intercept));
        }
        return new ShortLookupTable(inBits, signed, off, vlut.outBits, data);
    }

    /**
     * Create LUT for given i/o range, Rescale Slope/Intercept, Window
     * Center/Width and non-linear Presentation LUT. Apply no Window if Window
     * Width = 0.
     * <p>
     * If <code>pval2out</code> is not <code>null</code>, the output will
     * be weighted according this function, where the highest input value 
     * (p-value) maps to array index length-1 and the highest output value
     * (2^outBits-1) is represented by 0xFFFF. Length of <code>pval2out</code>
     * must be equal to 2^inBits, with inBits in the range [8, 16].
     * 
     * @param inBits
     *            number of significant bits within input values
     * @param signed
     *            specifies if input values are signed or unsigned
     * @param outBits
     *            bit depth of output range
     * @param slope
     *            Rescale Slope (0028,1053)
     * @param intercept
     *            Rescale Intercept (0028,1052)
     * @param center
     *            Window Center (0028,1050)
     * @param width
     *            Window Width (0028,1051) or 0 (= no Window specified)
     * @param vlutFct
     *            VOI LUT Function (0028,1056)
     * @param pLut
     *            item of Presentation LUT Sequence (2050,0010)
     * @param inverse
     *            specifies if output shall be inverted
     * @param pval2out
     *            p-value to output map or <code>null</code>
     * @return created LUT
     */
    public static LookupTable createLut(int inBits, boolean signed,
            int outBits, float slope, float intercept, float center,
            float width, String vlutFct, DicomObject pLut, boolean inverse,
            short[] pval2out) {
    	log.debug("Creating LUT for slope/intercept="+slope+"/"+intercept+" center/width="+center+"/"+width);
        LookupTable plut = createLut(0, false, pLut);
        LookupTable vlut = createLut(inBits, signed, plut.inBits, slope,
                intercept, center, width, vlutFct, false, null);
        return vlut.combine(plut, outBits, inverse, pval2out);
    }

    /**
     * Create LUT for given i/o range, non-linear Modality LUT and non-linear
     * VOI LUT.
     * <p>
     * If <code>pval2out</code> is not <code>null</code>, the output will
     * be weighted according this function, where the highest input value 
     * (p-value) maps to array index length-1 and the highest output value
     * (2^outBits-1) is represented by 0xFFFF. Length of <code>pval2out</code>
     * must be equal to 2^inBits, with inBits in the range [8, 16].
     * 
     * @param inBits
     *            number of significant bits within input values
     * @param signed
     *            specifies if input values are signed or unsigned
     * @param outBits
     *            bit depth of output range
     * @param mLut
     *            item of Modality LUT Sequence (0028,3000)
     * @param voiLut
     *            item of VOI LUT Sequence (0028,3010)
     * @param inverse
     *            specifies if output shall be inverted
     * @param pval2out
     *            p-value to output map or <code>null</code>
     * @return created LUT
     */
    public static LookupTable createLut(int inBits, boolean signed,
            int outBits, DicomObject mLut, DicomObject voiLut, boolean inverse,
            short[] pval2out) {
    	log.debug("Creating a combined m/v LUT, assuming MLUT output is unsigned.");
        LookupTable mlut = createLut(inBits, signed, voiLut);
        LookupTable vlut = createLut(mlut.outBits, false, voiLut);
        return mlut.combine(vlut, outBits, inverse, pval2out);
    }

    /**
     * Create LUT for given i/o range, Rescale Slope/Intercept, non-linear VOI
     * LUT and non-linear Presentation LUT.
     * <p>
     * If <code>pval2out</code> is not <code>null</code>, the output will
     * be weighted according this function, where the highest input value 
     * (p-value) maps to array index length-1 and the highest output value
     * (2^outBits-1) is represented by 0xFFFF. Length of <code>pval2out</code>
     * must be equal to 2^inBits, with inBits in the range [8, 16].
     * 
     * @param inBits
     *            number of significant bits within input values
     * @param signed
     *            specifies if input values are signed or unsigned
     * @param outBits
     *            bit depth of output range
     * @param slope
     *            Rescale Slope (0028,1053)
     * @param intercept
     *            Rescale Intercept (0028,1052)
     * @param voiLut
     *            item of VOI LUT Sequence (0028,3010)
     * @param pLut
     *            item of Presentation LUT Sequence (2050,0010)
     * @param inverse
     *            specifies if output shall be inverted
     * @param pval2out
     *            p-value to output map or <code>null</code>
     * @return created LUT
     */
    public static LookupTable createLut(int inBits, boolean signed,
            int outBits, float slope, float intercept, DicomObject voiLut,
            DicomObject pLut, boolean inverse, short[] pval2out) {
        LookupTable vlut = createLut(inBits, signed, slope, intercept, voiLut);
        LookupTable plut = createLut(0, false, pLut);
        return vlut.combine(plut, outBits, inverse, pval2out);
    }

    /**
     * Create LUT for given i/o range, non-linear Modality LUT, Window
     * Center/Width and non-linear Presentation LUT. Apply no Window if Window
     * Width = 0.
     * <p>
     * If <code>pval2out</code> is not <code>null</code>, the output will
     * be weighted according this function, where the highest input value 
     * (p-value) maps to array index length-1 and the highest output value
     * (2^outBits-1) is represented by 0xFFFF. Length of <code>pval2out</code>
     * must be equal to 2^inBits, with inBits in the range [8, 16].
     * 
     * @param inBits
     *            number of significant bits within input values
     * @param signed
     *            specifies if input values are signed or unsigned
     * @param outBits
     *            bit depth of output range
     * @param mLut
     *            item of Modality LUT Sequence (0028,3000)
     * @param center
     *            Window Center (0028,1050)
     * @param width
     *            Window Width (0028,1051) or 0 (= no Window specified)
     * @param vlutFct
     *            VOI LUT Function (0028,1056)
     * @param pLut
     *            item of Presentation LUT Sequence (2050,0010)
     * @param inverse
     *            specifies if output shall be inverted
     * @param pval2out
     *            p-value to output map or <code>null</code>
     * @return created LUT
     */
    public static LookupTable createLut(int inBits, boolean signed,
            int outBits, DicomObject mLut, float center, float width,
            String vlutFct, DicomObject pLut, boolean inverse,
            short[] pval2out) {
        LookupTable mlut = createLut(inBits, signed, mLut);
        LookupTable plut = createLut(0, false, pLut);
        if (width == 0) {
            return mlut.combine(plut, outBits, inverse, pval2out);
        }
        LookupTable vlut = createLut(mlut.outBits, false, plut.inBits, 1,
                0, center, width, vlutFct, false, null);
        return mlut.combine(vlut, plut, outBits, inverse, pval2out);
    }

    /**
     * Create LUT for given i/o range, non-linear Modality LUT, non-linear VOI
     * LUT and non-linear Presentation LUT.
     * <p>
     * If <code>pval2out</code> is not <code>null</code>, the output will
     * be weighted according this function, where the highest input value 
     * (p-value) maps to array index length-1 and the highest output value
     * (2^outBits-1) is represented by 0xFFFF. Length of <code>pval2out</code>
     * must be equal to 2^inBits, with inBits in the range [8, 16].
     * 
     * @param inBits
     *            number of significant bits within input values
     * @param signed
     *            specifies if input values are signed or unsigned
     * @param outBits
     *            bit depth of output range
     * @param mLut
     *            item of Modality LUT Sequence (0028,3000)
     * @param voiLut
     *            item of VOI LUT Sequence (0028,3010)
     * @param pLut
     *            item of Presentation LUT Sequence (2050,0010)
     * @param inverse
     *            specifies if output shall be inverted
     * @param pval2out
     *            p-value to output map or <code>null</code>
     * @return created LUT
     */
    public static LookupTable createLut(int inBits, boolean signed,
            int outBits, DicomObject mLut, DicomObject voiLut,
            DicomObject pLut, boolean inverse, short[] pval2out) {
        LookupTable mlut = createLut(inBits, signed, mLut);
        LookupTable vlut = createLut(mlut.outBits, false, voiLut);
        LookupTable plut = createLut(0, false, pLut);
        return mlut.combine(vlut, plut, outBits, inverse, pval2out);
    }

    /**
     * Create LUT for given DICOM image and output range. If the image specifies
     * multiple non-linear VOI LUTs, the VOI LUT specified by the first item of
     * the VOI LUT Sequence (0028,3010) will be applied. If the image does not
     * specify any non-linear VOI LUT, but multiple values for Window
     * Center/Width, the first Window Center/Width value will be applied.
     * <p>
     * If <code>pval2out</code> is not <code>null</code>, the output will
     * be weighted according this function, where the highest input value 
     * (p-value) maps to array index length-1 and the highest output value
     * (2^outBits-1) is represented by 0xFFFF. Length of <code>pval2out</code>
     * must be equal to 2^inBits, with inBits in the range [8, 16].
     * 
     * @param img
     *            DICOM image
     * @param outBits
     *            bit depth of output range
     * @param pval2out
     *            p-value to output map or <code>null</code>
     * @return created LUT
     */
    public static LookupTable createLutForImage(DicomObject img, int outBits,
            short[] pval2out) {
    	return createLutForImageWithPR(img, null, 1, 0f, 0f, null, outBits, pval2out);
    }


    /**
     * Create LUT for given DICOM image, modality LUT, and Window Center/Width and output range.
     * Apply no Window if Window Width = 0.
     * <p>
     * If <code>pval2out</code> is not <code>null</code>, the output will
     * be weighted according this function, where the highest input value 
     * (p-value) maps to array index length-1 and the highest output value
     * (2^outBits-1) is represented by 0xFFFF. Length of <code>pval2out</code>
     * must be equal to 2^inBits, with inBits in the range [8, 16].
     * 
     * @param img
     *            DICOM image
     * @param mLut is the dicom object containing the modality LUT to apply
     * @param center
     *            Window Center (0028,1050)
     * @param width
     *            Window Width (0028,1051) or 0 (= no Window specified)
     * @param vlutFct
     *            VOI LUT Function (0028,1056)
     * @param inverse is true when the output should be inverted.
     * @param outBits
     *            bit depth of output range
     * @param pval2out
     *            p-value to output map or <code>null</code>
     * @return created LUT
     */
    public static LookupTable createLutFromWL(DicomObject img, DicomObject mLut, float center,
            float width, String vlutFct, boolean inverse, int outBits, short[] pval2out) {
        int allocated = img.getInt(Tag.BitsAllocated, 8);
        int stored = img.getInt(Tag.BitsStored, allocated);
        boolean signed = img.getInt(Tag.PixelRepresentation) != 0;
        float slope = mLut.getFloat(Tag.RescaleSlope, 1.f);
        float intercept = mLut.getFloat(Tag.RescaleIntercept, 0.f);
        DicomObject tableMLut = mLut.getNestedDicomObject(Tag.ModalityLUTSequence);
        if (tableMLut != null) {
            return createLut(stored, signed, outBits, tableMLut, center, width,
                    vlutFct, inverse, pval2out);
        }
        return createLut(stored, signed, outBits, slope, intercept,
                center, width, vlutFct, inverse, pval2out);
    }

    private static boolean isInverse(DicomObject img) {
        String shape = img.getString(Tag.PresentationLUTShape);
        return shape != null ? "INVERSE".equals(shape) : "MONOCHROME1"
                .equals(img.getString(Tag.PhotometricInterpretation));
    }

    /**
     * Create LUT for given DICOM image, non-linear VOI LUT and output range.
     * <p>
     * If <code>pval2out</code> is not <code>null</code>, the output will
     * be weighted according this function, where the highest input value 
     * (p-value) maps to array index length-1 and the highest output value
     * (2^outBits-1) is represented by 0xFFFF. Length of <code>pval2out</code>
     * must be equal to 2^inBits, with inBits in the range [8, 16].
     * 
     * @param img
     *            DICOM image
     * @param mLut contains the modality LUT information.
     * @param voiLut
     *            item of VOI LUT Sequence (0028,3010)
     * @param outBits
     *            bit depth of output range
     * @param pval2out
     *            p-value to output map or <code>null</code>
     * @return created LUT
     */
    public static LookupTable createLutFromVOISequence(DicomObject img, DicomObject mLut,
            DicomObject voiLut, boolean inverse, int outBits, short[] pval2out) {
        int allocated = img.getInt(Tag.BitsAllocated, 8);
        int stored = img.getInt(Tag.BitsStored, allocated);
        boolean signed = img.getInt(Tag.PixelRepresentation) != 0;
        float slope = mLut.getFloat(Tag.RescaleSlope, 1.f);
        float intercept = mLut.getFloat(Tag.RescaleIntercept, 0.f);
        DicomObject tableMLut = img.getNestedDicomObject(Tag.ModalityLUTSequence);
        if (tableMLut != null) {
            return createLut(stored, signed, outBits, tableMLut, voiLut, inverse,
                    pval2out);
        }
        return createLut(stored, signed, outBits, slope, intercept, voiLut,
                inverse, pval2out);
    }

    /**
     * Create LUT for given DICOM image with DICOM Presentation State and output
     * range and possibly a window level over-ride.
     * <p>
     * If <code>pval2out</code> is not <code>null</code>, the output will
     * be weighted according this function, where the highest input value 
     * (p-value) maps to array index length-1 and the highest output value
     * (2^outBits-1) is represented by 0xFFFF. Length of <code>pval2out</code>
     * must be equal to 2^inBits, with inBits in the range [8, 16].
     * 
     * @param img DICOM image.  Must not be null.
     * @param pr  DICOM Presentation State.  If null, fall back to the image information.
     * @param frame  The frame number to use.  Non-multi-frame objects can provide any value here, multi-frame objects must start this from frame 1 to frame n.
     * @param center  The window level center
     * @param width   The window level width
     * @param vlutFct A function to use when applying the center/width. The center/width will only be used if this function is non-null.
     * @param outBits   bit depth of output range - typically 8, 10, 12 or 16
     * @param pval2out
     *            p-value to output map or <code>null</code>
     * @return created LUT
     */
    public static LookupTable createLutForImageWithPR(DicomObject img,
            DicomObject pr, int frame, float center, float width, String vlutFct, int outBits, short[] pval2out) {
    	DicomObject mlutObj = VOIUtils.selectModalityLUTObject(img, pr, frame);
    	DicomObject voiObj = VOIUtils.selectVoiObject(img, pr, frame);
    	
    	boolean inverse;
    	if( pr!=null ) {
           inverse = "INVERSE".equals(pr.getString(Tag.PresentationLUTShape));
    	}
    	else {
    		inverse = isInverse(img);
    	}
        DicomObject pLut = pr!=null ? pr.getNestedDicomObject(Tag.PresentationLUTSequence) : null;
    	return createLutForImage(img, mlutObj, voiObj, pLut, center, width, vlutFct, inverse, outBits, pval2out);
    }

    /**
     * Creates a LUT based on a set of provided objects.  These can come from different places within images, so providing them separately is important, even thought
     * the common use case will be that mlutObj, voiObj are the same as img. Allows a custom center/width/function to be applied that overrides the voi obj values.
     * @param img 		The image dicom object header
     * @param mlutObj  	The object containing the modality LUT information
     * @param voiObj  	The object containing the VOI LUT information (or null if none)
     * @param pLut    	The Presentation LUT Sequence (or null if none)
     * @param center	An over-riding window center
     * @param width     An over-riding window width
     * @param vlutFct   An over-riding VOI LUT Function.  Must be non-null to override.
     * @param inverse   True if the image should be inverted.
     * @param outBits   The number of output bits after everything.
     * @param pval2out  A p-value to output DDL (digital driving levels).
     * @return
     */
    public static LookupTable createLutForImage(DicomObject img, DicomObject mlutObj, DicomObject voiObj, DicomObject pLut, float center, float width, String vlutFct, boolean inverse, int outBits, short[] pval2out) {
        int allocated = img.getInt(Tag.BitsAllocated, 8);
        int stored = img.getInt(Tag.BitsStored, allocated);
        boolean signed = img.getInt(Tag.PixelRepresentation) != 0;
        float slope = mlutObj.getFloat(Tag.RescaleSlope, 1.f);
        float intercept = mlutObj.getFloat(Tag.RescaleIntercept, 0.f);
    	
        DicomObject mLut = mlutObj.getNestedDicomObject(Tag.ModalityLUTSequence);
        DicomObject voiLut = (voiObj!=null && vlutFct==null) ? voiObj.getNestedDicomObject(Tag.VOILUTSequence) : null;

        if( voiLut==null && width==0 && voiObj!=null) {
        	vlutFct = voiObj.getString(Tag.VOILUTFunction);
        	center = voiObj.getFloat(Tag.WindowCenter,0f);
        	width = voiObj.getFloat(Tag.WindowWidth,0f);
        }
        if (mLut == null) {
            if (voiLut == null ) {
                if (pLut == null) {
                    return LookupTable.createLut(stored, signed, outBits,
                            slope, intercept, center, width, vlutFct, inverse,
                            pval2out);
                }
                return LookupTable.createLut(stored, signed, outBits,
                        slope, intercept, center, width, vlutFct, pLut,
                        false, pval2out);
            }
        	if (pLut == null) {
                    return LookupTable.createLut(stored, signed, outBits,
                        slope, intercept, voiLut, inverse, pval2out);
            }
            return LookupTable.createLut(stored, signed, outBits,
                    slope, intercept, voiLut, pLut, false, pval2out);
        }
        if (voiLut == null) {
            if (pLut == null) {
                return LookupTable.createLut(stored, signed, outBits, mLut,
                        center, width, vlutFct, inverse, pval2out);
            }
            return LookupTable.createLut(stored, signed, outBits, mLut,
                    center, width, vlutFct, pLut, false, pval2out);
        }
        if (pLut == null) {
            return LookupTable.createLut(stored, signed, outBits, mLut,
                    voiLut, inverse, pval2out);
        }
        return LookupTable.createLut(stored, signed, outBits, mLut,
                voiLut, pLut, false, pval2out);
    }

}
