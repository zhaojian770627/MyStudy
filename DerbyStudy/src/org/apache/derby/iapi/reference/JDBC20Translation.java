/*

   Derby - Class org.apache.derby.iapi.reference.JDBC20Translation

   Licensed to the Apache Software Foundation (ASF) under one or more
   contributor license agreements.  See the NOTICE file distributed with
   this work for additional information regarding copyright ownership.
   The ASF licenses this file to you under the Apache License, Version 2.0
   (the "License"); you may not use this file except in compliance with
   the License.  You may obtain a copy of the License at

      http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.

 */

package org.apache.derby.iapi.reference;

import javax.transaction.xa.XAResource;

/**
        This class contains public statics that map directly
        to the new public statics in the jdbc 2.0 classes.
        By providing an intermediary class, we can use the
        same statics without having to import the jdbc 2.0 classes
        into other classes.


        <P>
        This class should not be shipped with the product.

        <P>
        This class has no methods, all it contains are constants
        are public, static and final since they are declared in an interface.
*/

public interface JDBC20Translation {

        /*
        ** public statics from javax.transaction.xa.XAResource
        */
        public static final int XA_ENDRSCAN = XAResource.TMENDRSCAN;
        public static final int XA_FAIL = XAResource.TMFAIL;
        public static final int XA_JOIN = XAResource.TMJOIN;
        public static final int XA_NOFLAGS = XAResource.TMNOFLAGS;
        public static final int XA_RESUME = XAResource.TMRESUME;
        public static final int XA_STARTRSCAN = XAResource.TMSTARTRSCAN;
        public static final int XA_SUCCESS = XAResource.TMSUCCESS;
        public static final int XA_SUSPEND = XAResource.TMSUSPEND;
}
