/*

   Derby - Class org.apache.derby.impl.sql.execute.HashLeftOuterJoinResultSet

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

package org.apache.derby.impl.sql.execute;

import org.apache.derby.iapi.services.loader.GeneratedMethod;
import org.apache.derby.iapi.sql.Activation;
import org.apache.derby.iapi.sql.execute.NoPutResultSet;


/**
 * Left outer join using hash join of 2 arbitrary result sets.
 * Simple subclass of nested loop left outer join, differentiated
 * to ease RunTimeStatistics output generation.
 */
class HashLeftOuterJoinResultSet extends NestedLoopLeftOuterJoinResultSet
{
    HashLeftOuterJoinResultSet(
						NoPutResultSet leftResultSet,
						int leftNumCols,
						NoPutResultSet rightResultSet,
						int rightNumCols,
						Activation activation,
						GeneratedMethod restriction,
						int resultSetNumber,
						GeneratedMethod emptyRowFun,
						boolean wasRightOuterJoin,
					    boolean oneRowRightSide,
					    boolean notExistsRightSide,
 					    double optimizerEstimatedRowCount,
						double optimizerEstimatedCost,
						String userSuppliedOptimizerOverrides)
    {
		super(leftResultSet, leftNumCols, rightResultSet, rightNumCols,
			  activation, restriction, resultSetNumber, 
			  emptyRowFun, wasRightOuterJoin,
			  oneRowRightSide, notExistsRightSide,
			  optimizerEstimatedRowCount, optimizerEstimatedCost, 
			  userSuppliedOptimizerOverrides);
    }
}
