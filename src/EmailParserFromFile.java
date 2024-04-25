import java.util.*;

public class EmailParserFromFile {

    public static void main(String[] args) {
        // Create a map to store email addresses for different domains
        Map<String, List<String>> emailMap = new HashMap<>();

        // Add email domains as keys and empty lists as values
        emailMap.put("gmail.com", new ArrayList<>());
        emailMap.put("yahoo.com", new ArrayList<>());
        emailMap.put("rediffmail.com", new ArrayList<>());
        emailMap.put("outlook.com", new ArrayList<>());


        try (Scanner scanner = new Scanner(EmailParserFromFile.class.getResourceAsStream("emails.txt"))) {
            while (scanner.hasNextLine()) {
                String email = scanner.nextLine();
                String domain = getEmailDomain(email);
                if (emailMap.containsKey(domain)) {
                    emailMap.get(domain).add(email);
                } else {
                    System.out.println("Unsupported email domain: " + domain);
                }
            }
        } catch (NullPointerException e) {
            System.out.println("File not found!");
        }

        // Print email addresses for each domain in table format
        System.out.println("Domain           | Email Addresses");
        System.out.println("--------------------------------------");
        for (Map.Entry<String, List<String>> entry : emailMap.entrySet()) {
            System.out.printf("%-15s | %s%n", entry.getKey(), entry.getValue());
        }
    }

    private static String getEmailDomain(String email) {
        int atIndex = email.indexOf('@');
        if (atIndex != -1) {
            return email.substring(atIndex + 1);
        } else {
            // Handle invalid email addresses
            return "";
        }
    }
}
