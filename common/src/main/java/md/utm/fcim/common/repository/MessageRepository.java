package md.utm.fcim.common.repository;

import md.utm.fcim.common.dto.Message;

import java.util.concurrent.ConcurrentLinkedQueue;

public interface MessageRepository {
    ConcurrentLinkedQueue<Message> findAll();

    void save(ConcurrentLinkedQueue<Message> messages);
}
