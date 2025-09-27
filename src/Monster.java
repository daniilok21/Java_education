import java.util.Random;
import java.util.Scanner;

public class Monster {
    private String image = "\uD83E\uDDDF\u200D";
    private final int x, y;
    private final String monsterSize;
    Random r = new Random();

    Monster(int sizeBoard, String monsterSize){
        this.y = r.nextInt(sizeBoard - 1);
        this.x = r.nextInt(sizeBoard);
        this.monsterSize = monsterSize;
        this.image = monsterSize.equals("Small") ? "\uD83E\uDDDF\u200D" : "\uD83D\uDC7E";
    }

    public String getImage() {
        return image;
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }

    public String getMonsterSize() {
        return monsterSize;
    }

    public boolean conflictPerson(int perX, int perY){
        return perY - 1 == this.y && perX - 1 == this.x;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public boolean taskMonster(int difficultGame, String monsterSize){
        switch (monsterSize) {
            case "Small":
                return mathTaskMonster(difficultGame);
            case "Big":
                return nimTaskMonster(difficultGame);
        }
        return false;
    }

    public boolean mathTaskMonster(int difficultGame) {
        System.out.println("Решите задачу:");
        int x = r.nextInt(100);
        int y = r.nextInt(100);
        int trueAnswer = x + y;
        System.out.println("Реши пример: " + x + " + " + y + " = ?");
        Scanner sc = new Scanner(System.in);
        int ans = sc.nextInt();
        if (trueAnswer == ans) {
            System.out.println("Верно! Ты победил монстра");
            return true;
        }
        System.out.println("Ты проиграл эту битву!");
        return false;
    }

    public boolean nimTaskMonster(int difficultGame) {
        // Ним - игра для большого монстра.
        // оставляем игроку число кратное 4
        Scanner sc = new Scanner(System.in);
        printRulesOfNim();
        int stones = r.nextInt(15, 21);
        System.out.println("Выбрано случайное количество камней: " + stones);
        boolean turnOfPerson = true;
        int countOfTurn;
        boolean personIsWin;
        while (stones > 0) {
            if (!turnOfPerson) {
                // логика бота
                if (stones % 4 != 0) {
                    countOfTurn = stones % 4;
                } else {
                    countOfTurn = r.nextInt(1, 4);
                }
                stones -= countOfTurn;
                System.out.println("Я взял " + countOfTurn);
                System.out.println("Осталось камней: " + stones);
                turnOfPerson = true;
            } else {
                System.out.println("Твой ход. Напиши любое число от 1 до 3:");
                countOfTurn = sc.nextInt();
                while (!(countOfTurn > 0 && countOfTurn <= 3 && countOfTurn <= stones)) {
                    if (countOfTurn > stones) {
                        System.out.println("Недостатчно камней.");
                    }
                    else {
                        System.out.println("Неверный формат ввода. Напиши любое число от 1 до 3:");
                    }
                    countOfTurn = sc.nextInt();
                }
                stones -= countOfTurn;
                System.out.println("Осталось камней: " + stones);
                turnOfPerson = false;
            }
        }
        if (turnOfPerson) {
            System.out.println("Ха-ха-ха, я победил!");
            return false;
        }
        System.out.println("Поздравляю, ты победил!");
        return true;
    }

    public void printRulesOfNim() {
        System.out.println("┌─────────────────────────────┐");
        System.out.println("│         ПРАВИЛА НИМ         │");
        System.out.println("├─────────────────────────────┤");
        System.out.println("│ • Камней: 15-20             │");
        System.out.println("│ • Брать за ход: 1-3 камня   │");
        System.out.println("│ • Проигрывает: кто взял     │");
        System.out.println("│   ПОСЛЕДНИЙ камень!         │");
        System.out.println("└─────────────────────────────┘");
    }

}