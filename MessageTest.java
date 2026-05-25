package testApplication;

import mainApplication.Message;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MessageTest {

    @Test
    public void testMessageLengthSuccess() {

        Message m =
                new Message(
                        "0012345678",
                        0,
                        "+27718693002",
                        "Hi Mike, can you join us for dinner tonight?"
                );

        assertTrue(
                m.checkMessageLength()
        );
    }

    @Test
    public void testMessageLengthFailure() {

        String longMessage =
                "A".repeat(260);

        Message m =
                new Message(
                        "0012345678",
                        0,
                        "+27718693002",
                        longMessage
                );

        assertFalse(
                m.checkMessageLength()
        );
    }

    @Test
    public void testRecipientCellSuccess() {

        Message m =
                new Message(
                        "0012345678",
                        0,
                        "+27718693002",
                        "Test message"
                );

        assertEquals(
                1,
                m.checkRecipientCell()
        );
    }

    @Test
    public void testRecipientCellFailure() {

        Message m =
                new Message(
                        "0012345678",
                        0,
                        "0812345678",
                        "Test message"
                );

        assertEquals(
                0,
                m.checkRecipientCell()
        );
    }

    @Test
    public void testHashCreation() {

        Message m =
                new Message(
                        "0012345678",
                        0,
                        "+27718693002",
                        "Hi Mike, can you join us for dinner tonight?"
                );

        assertEquals(
                "00:0:HITONIGHT?",
                m.getMessageHash()
        );
    }

    @Test
    public void testMessageIDCreation() {

        Message m =
                new Message(
                        "0012345678",
                        0,
                        "+27718693002",
                        "Hello"
                );

        assertTrue(
                m.checkMessageID()
        );
    }

    @Test
    public void testSendOption() {

        Message m =
                new Message(
                        "0012345678",
                        0,
                        "+27718693002",
                        "Send test"
                );

        assertEquals(
                "Message successfully sent.",
                m.sentMessage(1)
        );
    }

    @Test
    public void testDisregardOption() {

        Message m =
                new Message(
                        "0012345678",
                        0,
                        "+27718693002",
                        "Delete test"
                );

        assertEquals(
                "Press 0 to delete message.",
                m.sentMessage(2)
        );
    }

    @Test
    public void testStoreOption() {

        Message m =
                new Message(
                        "0012345678",
                        0,
                        "+27718693002",
                        "Store test"
                );

        assertEquals(
                "Message successfully stored.",
                m.sentMessage(3)
        );
    }
}