import java.util.Scanner;

public class SmallMonster extends Monster{
    private String image = "\uD83D\uDC7E";


    SmallMonster(int sizeBoard) {
        super(sizeBoard);
    }

    //    @Override
    public String getImage() {
        return image;
    }

    //    @Override
    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public boolean taskMonster(int difficultGame){
        // оставляем игроку число кратное 4
        byte percentOfMistake;
        switch (difficultGame) {
            case (1):
                percentOfMistake = 50;
                break;
            case (2):
                percentOfMistake = 40;
                break;
            case (3):
                percentOfMistake = 25;
                break;
            case (4):
                percentOfMistake = 15;
                break;
            default:
                percentOfMistake = 0;
        }
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
                if (stones % 4 != 0 && r.nextInt(100) >= percentOfMistake) {
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
