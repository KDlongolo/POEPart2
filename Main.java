package mainApplication;

import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanInput = new Scanner(System.in);

        System.out.println("===== REGISTRATION =====");

        System.out.print("Enter first name: ");
        String first = scanInput.nextLine();

        System.out.print("Enter last name: ");
        String last = scanInput.nextLine();

        System.out.print("Enter username: ");
        String username = scanInput.nextLine();

        System.out.print("Enter password: ");
        String password = scanInput.nextLine();

        System.out.print("Enter South African cell phone number (include +27): ");
        String phone = scanInput.nextLine();

        Login accountLogin =
                new Login(first, last, username, password, phone);

        System.out.println(
                accountLogin.registerUser()
        );

        if (accountLogin.checkUserName()
                && accountLogin.checkPasswordComplexity()
                && accountLogin.checkCellPhoneNumber()) {

            System.out.println();
            System.out.println("===== LOGIN =====");

            System.out.print("Enter username: ");
            String loginUsername =
                    scanInput.nextLine();

            System.out.print("Enter password: ");
            String loginPassword =
                    scanInput.nextLine();

            System.out.println(
                    accountLogin.returnLoginStatus(
                            loginUsername,
                            loginPassword
                    )
            );

            if (accountLogin.loginUser(
                    loginUsername,
                    loginPassword)) {

                System.out.println();
                System.out.println("Welcome to QuickChat.");

                int option = 0;

                while (option != 3) {

                    System.out.println();
                    System.out.println("===== QUICKCHAT =====");
                    System.out.println("1) Send Messages");
                    System.out.println("2) Show recently sent messages");
                    System.out.println("3) Quit");

                    System.out.print("Select option: ");

                    option = scanInput.nextInt();
                    scanInput.nextLine();

                    switch (option) {

                        case 1:

                            System.out.print(
                                    "How many messages would you like to send? "
                            );

                            int totalMessages =
                                    scanInput.nextInt();

                            scanInput.nextLine();

                            for (int x = 1;
                                 x <= totalMessages;
                                 x++) {

                                System.out.println();
                                System.out.println(
                                        "===== MESSAGE "
                                                + x
                                                + " ====="
                                );

                                String generatedID =
                                        createMessageID();

                                System.out.println(
                                        "Message ID generated: "
                                                + generatedID
                                );

                                System.out.print(
                                        "Enter recipient number: "
                                );

                                String recipient =
                                        scanInput.nextLine();

                                System.out.print(
                                        "Enter message: "
                                );

                                String text =
                                        scanInput.nextLine();

                                Message msg =
                                        new Message(
                                                generatedID,
                                                x,
                                                recipient,
                                                text
                                        );

                                if (!msg.checkMessageID()) {

                                    System.out.println(
                                            "Message ID invalid."
                                    );

                                    continue;
                                }

                                if (msg.checkRecipientCell() == 1) {

                                    System.out.println(
                                            "Cell phone number successfully captured."
                                    );

                                } else {

                                    System.out.println(
                                            "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again."
                                    );

                                    continue;
                                }

                                if (msg.checkMessageLength()) {

                                    System.out.println(
                                            "Message ready to send."
                                    );

                                } else {

                                    int exceeded =
                                            text.length() - 250;

                                    System.out.println(
                                            "Message exceeds 250 characters by "
                                                    + exceeded
                                                    + ", please reduce the size."
                                    );

                                    continue;
                                }

                                System.out.println();
                                System.out.println("1) Send Message");
                                System.out.println("2) Disregard Message");
                                System.out.println("3) Store Message");

                                System.out.print(
                                        "Enter option: "
                                );

                                int sendChoice =
                                        scanInput.nextInt();

                                scanInput.nextLine();

                                System.out.println(
                                        msg.sentMessage(sendChoice)
                                );

                                System.out.println();
                                System.out.println(
                                        msg.printMessage()
                                );
                            }

                            System.out.println();
                            System.out.println(
                                    "Total messages sent: "
                                            + Message.returnTotalMessages()
                            );

                            break;

                        case 2:

                            System.out.println(
                                    "Coming Soon."
                            );

                            break;

                        case 3:

                            System.out.println(
                                    "Exiting QuickChat."
                            );

                            break;

                        default:

                            System.out.println(
                                    "Invalid menu option."
                            );
                    }
                }
            }
        }

        scanInput.close();
    }

    public static String createMessageID() {

        Random random =
                new Random();

        long generatedNumber =
                1000000000L
                        + (long) (
                        random.nextDouble()
                                * 9000000000L
                );

        return String.valueOf(
                generatedNumber
        );
    }
}