package md.utm.fcim.common.repository.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import md.utm.fcim.common.dto.Message;
import md.utm.fcim.common.repository.MessageRepository;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

public class MessageRepositoryImpl implements MessageRepository {

    @Override
    public ConcurrentLinkedQueue<Message> findAll() {
        ObjectMapper mapper = new ObjectMapper();
        List<Message> messages = new ArrayList<>();
        ConcurrentLinkedQueue<Message> messageConcurrentLinkedQueue = new ConcurrentLinkedQueue<>();

        try {
            // Convert JSON string from file to Object
            messages = mapper.readValue(new File("/home/veladii/IdeaProjects/Pad_Lab1/common/src/main/resources/messages.json"), new TypeReference<List<Message>>() {
            });
            messages.forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
        messageConcurrentLinkedQueue.addAll(messages);
        return messageConcurrentLinkedQueue;
    }

    @Override
    public void save(ConcurrentLinkedQueue<Message> messages) {
        messages.forEach(System.out::println);
        ObjectMapper mapper = new ObjectMapper();
        try {
            // Convert object to JSON string and save into a file directly
            mapper.writeValue(new File("/home/veladii/IdeaProjects/Pad_Lab1/common/src/main/resources/messages.json"), messages);

            // Convert object to JSON string
            String jsonInString = mapper.writeValueAsString(messages);
            System.out.println(jsonInString);

            // Convert object to JSON string and pretty print
            jsonInString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(messages);
            System.out.println(jsonInString);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
