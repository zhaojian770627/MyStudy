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
 * Gunter Zeilinger <gunterze@gmail.com>
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

package org.dcm4che2.data;

public class TransferSyntax
{

    public static final TransferSyntax ImplicitVRLittleEndian = 
            new TransferSyntax("1.2.840.10008.1.2", false, false, false, false);

    public static final TransferSyntax ImplicitVRBigEndian =
            new TransferSyntax(null, false, true, false, false);

    public static final TransferSyntax ExplicitVRLittleEndian =
            new TransferSyntax("1.2.840.10008.1.2.1", true, false, false, false);

    public static final TransferSyntax ExplicitVRBigEndian = 
            new TransferSyntax("1.2.840.10008.1.2.2", true, true, false, false);

    public static final TransferSyntax DeflatedExplicitVRLittleEndian = 
            new TransferSyntax("1.2.840.10008.1.2.1.99", true, false, true, false);

    public static final TransferSyntax NoPixelData = 
            new TransferSyntax("1.2.840.10008.1.2.4.96", true, false, false, false);

    public static final TransferSyntax NoPixelDataDeflate = 
        new TransferSyntax("1.2.840.10008.1.2.4.97", true, false, true, false);

    public static TransferSyntax valueOf(String uid)
    {
        if (uid == null)
            throw new NullPointerException("uid");
        if (uid.equals(ImplicitVRLittleEndian.uid))
            return ImplicitVRLittleEndian;
        if (uid.equals(ExplicitVRLittleEndian.uid))
            return ExplicitVRLittleEndian;
        if (uid.equals(ExplicitVRBigEndian.uid))
            return ExplicitVRBigEndian;
        if (uid.equals(DeflatedExplicitVRLittleEndian.uid))
            return DeflatedExplicitVRLittleEndian;
        if (uid.equals(NoPixelData.uid))
            return NoPixelData;
        if (uid.equals(NoPixelDataDeflate.uid))
            return NoPixelDataDeflate;
        return new TransferSyntax(uid, true, false, false, true);
    }

    final String uid;

    final boolean bigEndian;

    final boolean explicitVR;

    final boolean deflated;

    final boolean encapsulated;

    private TransferSyntax(String uid, boolean explicitVR, boolean bigEndian,
            boolean deflated, boolean encapsulated)
    {
        this.uid = uid;
        this.explicitVR = explicitVR;
        this.bigEndian = bigEndian;
        this.deflated = deflated;
        this.encapsulated = encapsulated;
    }

    public final String uid()
    {
        return uid;
    }

    public final boolean bigEndian()
    {
        return bigEndian;
    }

    public final boolean explicitVR()
    {
        return explicitVR;
    }

    public final boolean deflated()
    {
        return deflated;
    }

    public final boolean encapsulated()
    {
        return encapsulated;
    }

    public final boolean uncompressed()
    {
        return !deflated && !encapsulated;
    }
    
    /** Check to see if the transfer syntax is the same */
    @Override
    public  boolean equals(Object o2) {
    	if( ! (o2 instanceof TransferSyntax) ) return false;
    	return uid().equals(((TransferSyntax) o2).uid());
    }
}
