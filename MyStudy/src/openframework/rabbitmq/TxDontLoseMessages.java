//   The contents of this file are subject to the Mozilla Public License
//   Version 1.1 (the "License"); you may not use this file except in
//   compliance with the License. You may obtain a copy of the License at
//   http://www.mozilla.org/MPL/
//
//   Software distributed under the License is distributed on an "AS IS"
//   basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See the
//   License for the specific language governing rights and limitations
//   under the License.
//
//   The Original Code is RabbitMQ.
//
//   The Initial Developers of the Original Code are LShift Ltd,
//   Cohesive Financial Technologies LLC, and Rabbit Technologies Ltd.
//
//   Portions created before 22-Nov-2008 00:00:00 GMT by LShift Ltd,
//   Cohesive Financial Technologies LLC, or Rabbit Technologies Ltd
//   are Copyright (C) 2007-2008 LShift Ltd, Cohesive Financial
//   Technologies LLC, and Rabbit Technologies Ltd.
//
//   Portions created by LShift Ltd are Copyright (C) 2007-2010 LShift
//   Ltd. Portions created by Cohesive Financial Technologies LLC are
//   Copyright (C) 2007-2010 Cohesive Financial Technologies
//   LLC. Portions created by Rabbit Technologies Ltd are Copyright
//   (C) 2007-2010 Rabbit Technologies Ltd.
//
//   All Rights Reserved.
//
//   Contributor(s): ______________________________________.
//

package openframework.rabbitmq;

import java.io.IOException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;
import com.rabbitmq.client.QueueingConsumer;

public class TxDontLoseMessages {
    final static int MSG_COUNT = 10;
    final static String QUEUE_NAME = "tx-test";
    static ConnectionFactory connectionFactory;

    public static void main(String[] args)
        throws IOException, InterruptedException
    {
        connectionFactory = new ConnectionFactory();

        connectionFactory.setHost("20.12.22.124");
        connectionFactory.setPort(5672);
        connectionFactory.setUsername("sscmq");
        connectionFactory.setPassword("sscmq");
        
        //(new Thread(new Consumer())).start();
        (new Thread(new Publisher())).start();
    }

    static class Publisher implements Runnable {
        public void run() {
            try {
                long startTime = System.currentTimeMillis();

                Connection conn = connectionFactory.newConnection();
                Channel ch = conn.createChannel();
                //ch.queueDeclare(QUEUE_NAME, true, false, true, null);
                ch.txSelect();
                for (int i = 0; i < MSG_COUNT; ++i) {
                    ch.basicPublish("ssc", "zjtest",
                                    MessageProperties.PERSISTENT_BASIC,
                                    "nop".getBytes());
                    // 可以放到外面
                    ch.txCommit();                    
                }
                
                ch.close();
                conn.close();

                long endTime = System.currentTimeMillis();
                System.out.printf("Test took %.3fs\n", (float)(endTime - startTime)/1000);
            } catch (Throwable e) {
                System.out.println("foobar :(");
                System.out.print(e);
            }
        }
    }

    static class Consumer implements Runnable {
        public void run() {
            try {
                Connection conn = connectionFactory.newConnection();
                Channel ch = conn.createChannel();
                ch.queueDeclare(QUEUE_NAME, true, false, true, null);
                QueueingConsumer qc = new QueueingConsumer(ch);
                ch.basicConsume(QUEUE_NAME, true, qc);
                for (int i = 0; i < MSG_COUNT; ++i) {
                    qc.nextDelivery();
                    System.out.printf("Consumed %d\n", i);
                }
                ch.close();
                conn.close();
            } catch (Throwable e) {
                System.out.println("Whoosh!");
                System.out.print(e);
            }
        }
    }
}