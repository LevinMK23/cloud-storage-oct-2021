package com.geekbrains.io;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class ChatController implements Initializable {

    public ListView<String> listView;
    public TextField input;
    private Path root;
    private byte[] buffer;
    private DataInputStream dis;
    private DataOutputStream dos;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        buffer = new byte[1024];
        root = Paths.get("root");
        if (!Files.exists(root)) {
            try {
                Files.createDirectory(root);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }

        try {
            fillFilesInView();
        } catch (Exception e) {
            e.printStackTrace();
        }

        listView.setOnMouseClicked(e -> {
            if (e.getClickCount() == 2) {
                String fileName = listView.getSelectionModel().getSelectedItem();
                if (!Files.isDirectory(root.resolve(fileName))) {
                    input.setText(fileName);
                } else {
                    input.setText("Select file! Not directory");
                }
            }
        });

        try {
            Socket socket = new Socket("localhost", 8189);
            dis = new DataInputStream(socket.getInputStream());
            dos = new DataOutputStream(socket.getOutputStream());
            Thread readThread = new Thread(() -> {
                try {
                    while (true) {
                        String message = dis.readUTF();
                        Platform.runLater(() -> input.setText(message));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            readThread.setDaemon(true);
            readThread.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void fillFilesInView() throws Exception {
        listView.getItems().clear();
        List<String> list = Files.list(root)
                .map(p -> p.getFileName().toString())
                .collect(Collectors.toList());
        listView.getItems().addAll(list);
    }

    public void sendMessage(ActionEvent actionEvent) throws IOException {
        String fileName = input.getText();
        input.clear();
        Path filePath = root.resolve(fileName);
        if (Files.exists(filePath)) {
            dos.writeUTF(fileName);
            dos.writeLong(Files.size(filePath));
            try (FileInputStream fis = new FileInputStream(filePath.toFile())) {
                int read;
                while ((read = fis.read(buffer)) != -1) {
                    dos.write(buffer, 0, read);
                }
            }
            dos.flush();
        }
    }
}
