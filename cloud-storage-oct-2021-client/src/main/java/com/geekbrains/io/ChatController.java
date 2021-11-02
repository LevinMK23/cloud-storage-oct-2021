package com.geekbrains.io;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import com.geekbrains.model.AbstractMessage;
import com.geekbrains.model.FileMessage;
import com.geekbrains.model.FileRequest;
import com.geekbrains.model.ListMessage;
import com.geekbrains.network.Net;
import io.netty.handler.codec.serialization.ObjectDecoderInputStream;
import io.netty.handler.codec.serialization.ObjectEncoderOutputStream;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import lombok.extern.slf4j.Slf4j;

import static javafx.application.Platform.runLater;

@Slf4j
public class ChatController implements Initializable {


    public ListView<String> listView;
    public TextField input;
    private Path currentDir;
    private Net net;
    private ObjectDecoderInputStream dis;
    private ObjectEncoderOutputStream dos;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        currentDir = Paths.get("client");
        net = Net.getInstance(this::processMessage); // wait
    }

    private void processMessage(AbstractMessage message) throws IOException {
        log.debug("Start processing {}", message);
        switch (message.getType()) {

            case FILE_MESSAGE:
                FileMessage msg = (FileMessage) message;
                Path file = currentDir.resolve(msg.getName());

                if (msg.isFirstButch()) {
                    Files.deleteIfExists(file);
                }

                try (FileOutputStream os = new FileOutputStream(file.toFile(), true)) {
                    os.write(msg.getBytes(), 0, msg.getEndByteNum());
                }

                break;
            case LIST_MESSAGE:
                ListMessage list = (ListMessage) message;
                runLater(() -> {
                    listView.getItems().clear();
                    listView.getItems().addAll(list.getFiles());
                });
                break;
        }
    }

    private List<String> getFilesInCurrentDir() throws IOException {
        return Files.list(currentDir).map(p -> p.getFileName().toString())
                .collect(Collectors.toList());
    }

    public void sendMessage(ActionEvent actionEvent) throws IOException {
        String fileName = input.getText();
        net.send(new FileRequest(fileName));
    }
}
