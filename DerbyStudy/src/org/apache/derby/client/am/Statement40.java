/*

   Derby - Class org.apache.derby.client.am.Statement40

   Licensed to the Apache Software Foundation (ASF) under one or more
   contributor license agreements.  See the NOTICE file distributed with
   this work for additional information regarding copyright ownership.
   The ASF licenses this file to You under the Apache License, Version 2.0
   (the "License"); you may not use this file except in compliance with
   the License.  You may obtain a copy of the License at

      http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.

*/

package org.apache.derby.client.am;

import java.sql.SQLException;
import org.apache.derby.shared.common.reference.SQLState;

/**
 * Implements JDBC 4.0 specific methods
 */

public class Statement40 extends Statement{
    /**
     * Calls the super class constructor to pass the parameters
     * @param  agent      Agent
     * @param  connection Connection
     * @throws SqlException
     *
     */
    public Statement40(Agent agent, Connection connection) throws SqlException {
        super(agent,connection);
    }
    
    /**
     * Calls the superclass constructor to pass the arguments
     * @param agent             Agent
     * @param connection        Connection
     * @param type              int
     * @param concurrency       int
     * @param holdability       int
     * @param autoGeneratedKeys int
     * @param columnNames       String[]
     * @param columnIndexes     int[]
     * @throws SqlException
     *
     */
    public Statement40(Agent agent, Connection connection, int type, 
                     int concurrency, int holdability,
                     int autoGeneratedKeys, String[] columnNames,
                     int[]  columnIndexes) 
                     throws SqlException {
        super(agent,connection,type,concurrency,holdability,autoGeneratedKeys,
                columnNames, columnIndexes);
    }
    
    /**
     * Returns <code>this</code> if this class implements the interface
     *
     * @param  interfaces a Class defining an interface
     * @return an object that implements the interface
     * @throws java.sql.SQLExption if no object if found that implements the 
     * interface
     */
    public <T> T unwrap(java.lang.Class<T> interfaces)
                                   throws SQLException {
        try { 
            checkForClosedStatement();
            return interfaces.cast(this);
        } catch (ClassCastException cce) {
            throw new SqlException(null,new ClientMessageId(SQLState.UNABLE_TO_UNWRAP),
                    interfaces).getSQLException();
        } catch (SqlException se) {
            throw se.getSQLException();
        }
    }
}
