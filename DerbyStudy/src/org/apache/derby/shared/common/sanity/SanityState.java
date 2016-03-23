package org.apache.derby.shared.common.sanity;

/*
 **
 * This class is intended for the use of the SanityManager in conjunction
 * with making a build either Sane or Insane. An insane build is one which
 * has the two booleans expressed here as "false"; a sane build should be
 * have the booleans expressed as "true".
 *
 * @see com.ihost.cs.iapi.services.sanity.SanityManager
 ** 
*/

public class SanityState
{
	public static final boolean ASSERT=false ;
	public static final boolean DEBUG=false ;
}
