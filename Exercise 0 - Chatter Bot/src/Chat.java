import java.util.Scanner;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Chat {
    public static void main(String[] args) {
        ChatterBot[] bots = new ChatterBot[2];
        ChatterBot bot1 =
                new ChatterBot("Alice", new String[]{"<phrase>", "say <phrase> ? okay: <phrase>"},
                        new String[]{"what", "what is <request>?"});
        ChatterBot bot2 =
                new ChatterBot("Bob", new String[]{"<phrase>", "say <phrase>? alright: <phrase>"},
                        new String[]{"whaaat", "say say"});

        bots[0] = bot1;
        bots[1] = bot2;

        String statement = "Hey";
        Scanner scanner = new Scanner(System.in);
        while (true) {
            for (ChatterBot bot : bots) {
                statement = bot.replyTo(statement);
                System.out.print(bot.getName() + ": " + statement);
                scanner.nextLine();
            }
        }
    }
}