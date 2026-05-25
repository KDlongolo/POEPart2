package mainApplication;

import java.io.FileWriter;
import java.io.IOException;

public class Message {

    private String messageID;
    private int messageNumber;
    private String recipientNumber;
    private String messageText;
    private String messageHash;

    private static int totalSentMessages = 0;

    public Message(String messageID,
                   int messageNumber,
                   String recipientNumber,
                   String messageText) {

        this.messageID = messageID;
        this.messageNumber = messageNumber;
        this.recipientNumber = recipientNumber;
        this.messageText = messageText;

        messageHash = createMessageHash();
    }

    public boolean checkMessageID() {

        return messageID.length() == 10;
    }

    public int checkRecipientCell() {

        if (recipientNumber.matches("^\\+27\\d{9}$")) {
            return 1;
        }

        return 0;
    }

    public boolean checkMessageLength() {

        return messageText.length() <= 250;
    }

    public String createMessageHash() {

        String[] words =
                messageText.trim().split("\\s+");

        String firstWord =
                words[0].toUpperCase();

        String lastWord =
                words[words.length - 1].toUpperCase();

        return messageID.substring(0, 2)
                + ":"
                + messageNumber
                + ":"
                + firstWord
                + lastWord;
    }

    public String sentMessage(int choice) {

        switch (choice) {

            case 1:
                totalSentMessages++;
                return "Message successfully sent.";

            case 2:
                return "Press 0 to delete message.";

            case 3:
                storeMessage();
                return "Message successfully stored.";

            default:
                return "Invalid option selected.";
        }
    }

    public String printMessage() {

        return "Message ID: " + messageID
                + "\nMessage Hash: " + messageHash
                + "\nRecipient: " + recipientNumber
                + "\nMessage: " + messageText;
    }

    public static int returnTotalMessages() {

        return totalSentMessages;
    }

    public void storeMessage() {

        try {

            FileWriter writeFile =
                    new FileWriter("messages.json", true);

            writeFile.write("{\n");
            writeFile.write("\"ID\":\"" + messageID + "\",\n");
            writeFile.write("\"Hash\":\"" + messageHash + "\",\n");
            writeFile.write("\"Recipient\":\"" + recipientNumber + "\",\n");
            writeFile.write("\"Message\":\"" + messageText + "\"\n");
            writeFile.write("}\n");

            writeFile.close();

        } catch (IOException e) {

            System.out.println(
                    "Error saving message."
            );
        }
    }

    public String getMessageHash() {
        return messageHash;
    }
}