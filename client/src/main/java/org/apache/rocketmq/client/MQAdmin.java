/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.rocketmq.client;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.common.message.MessageQueue;
import org.apache.rocketmq.remoting.exception.RemotingException;

/**
 * Base interface for MQ management
 */
public interface MQAdmin {
    /**
     * Creates an topic
     *
     * 创建一个主题
     *
     * @param key accesskey 鉴权key
     * @param newTopic topic name 主题的名称
     * @param queueNum topic's queue number 主题队列号
     */
    void createTopic(final String key, final String newTopic, final int queueNum)
        throws MQClientException;

    /**
     * Creates an topic
     *
     * @param key accesskey 鉴权key
     * @param newTopic topic name 主题的名称
     * @param queueNum topic's queue number 主题队列号
     * @param topicSysFlag topic system flag  主题系统标识
     */
    void createTopic(String key, String newTopic, int queueNum, int topicSysFlag)
        throws MQClientException;

    /**
     * Gets the message queue offset according to some time in milliseconds<br>
     * be cautious to call because of more IO overhead
     *  获取消息队列偏移量，以毫秒为单位。
     *  由于IO开销较大，请谨慎调用
     * @param mq Instance of MessageQueue MessageQueue的实例
     * @param timestamp from when in milliseconds. 时间戳
     * @return offset
     */
    long searchOffset(final MessageQueue mq, final long timestamp) throws MQClientException;

    /**
     * Gets the max offset
     *
     * 获取最大的偏移量
     *
     * @param mq Instance of MessageQueue  MessageQueue的实例
     * @return the max offset  返回最大的偏移量
     */
    long maxOffset(final MessageQueue mq) throws MQClientException;

    /**
     * Gets the minimum offset
     *
     * 获取最小的偏移量
     *
     * @param mq Instance of MessageQueue MessageQueue的实例
     * @return the minimum offset  返回最小的偏移量
     */
    long minOffset(final MessageQueue mq) throws MQClientException;

    /**
     * Gets the earliest stored message time
     *
     *  获取最早存储的消息时间
     *
     * @param mq Instance of MessageQueue   MessageQueue的实例
     * @return the time in microseconds 最早存储的时间
     */
    long earliestMsgStoreTime(final MessageQueue mq) throws MQClientException;

    /**
     * Query message according tto message id
     *
     * 根据消息id查询消息
     *
     * @param offsetMsgId message id 消息id
     * @return message 消息
     */
    MessageExt viewMessage(final String offsetMsgId) throws RemotingException, MQBrokerException,
        InterruptedException, MQClientException;

    /**
     * Query messages
     *
     * @param topic message topic
     * @param key message key index word
     * @param maxNum max message number
     * @param begin from when
     * @param end to when
     * @return Instance of QueryResult
     */
    QueryResult queryMessage(final String topic, final String key, final int maxNum, final long begin,
        final long end) throws MQClientException, InterruptedException;

    /**
     * @return The {@code MessageExt} of given msgId
     */
    MessageExt viewMessage(String topic,
        String msgId) throws RemotingException, MQBrokerException, InterruptedException, MQClientException;

}