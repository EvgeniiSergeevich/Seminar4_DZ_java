import java.util.ArrayDeque;
import java.util.Scanner;

public class Alg_Li {
    public static void main(String[] args) {
        runLi();
//        System.out.println(Arrays.toString(enterPoint(key)));
//        int[][] board = board();
//        for (int i = 0; i < board.length; i++) {
//            for (int j = 0; j < board[i].length; j++) {
//                System.out.print(board[i][j] + "  ");
//            }
//            System.out.println();
//        }
    }
    public static void runLi()                                      // Логика программы
    {
        ArrayDeque<String> states = new ArrayDeque<String>();
        Scanner key = new Scanner(System.in);
        int[][] boardLi = board(key);                               // Игровое поле
        int[] start = enterPoint(key);                              // Координаты точки старта
        int[] finishPoint = new int[2];                             // Координаты точки финиша
        boardLi[start[1]][start[0]] = 1;                            // Помечаю ячейку старта "1"
        System.out.println("Введите количество точек выхода(финиша)");
        int finPointsAmount = 0;                                    // Количество точек финиша

        if(key.hasNextInt())
        {
            finPointsAmount = key.nextInt();
        }
        else
        {
            System.out.println("Вы ввели не число!");
        }

        while (finPointsAmount != 0)                                // Отмечаю точки финиша "-2"
        {
            finishPoint = finishPoint(key);
            boardLi[finishPoint[1]][finishPoint[0]] = -2;
            finPointsAmount--;
        }
        for (int i = 0; i < boardLi.length; i++)                    // Вывожу на экран Игровое поле
        {
            for (int j = 0; j < boardLi[i].length; j++)
            {
                System.out.print(boardLi[i][j] + "  ");
            }
            System.out.println();
        }
    }


    public static int[][] board(Scanner key)                    // Создаю квадратного поля, размером size
    {
        System.out.print("Укажите размер поля: ");
        int size = consoleIn(key);
        return new int[size][size];
    }


    public static int[] enterPoint(Scanner key)                 // Создаю точку входа
    {
        System.out.print("Укажите координату X точки старта (считая первую клетку, как 1,1): ");
        int x = consoleIn(key) - 1;
        System.out.print("Укажите координату Y точки старта (считая первую клетку, как 1,1): ");
        int y = consoleIn(key) - 1;
        return new int[]{x, y};
    }


    public static int[] finishPoint(Scanner key)                // Создаю точку выхода
    {
        System.out.print("Укажите координату X точки финиша (считая первую клетку, как 1,1): ");
        int x = consoleIn(key) - 1;
        System.out.print("Укажите координату Y точки финиша (считая первую клетку, как 1,1): ");
        int y = consoleIn(key) - 1;
        System.out.println();
        return new int[]{x, y};
    }


    public static int consoleIn(Scanner key)                    // Читаю с консоли число
    {
        int i = 0;
        if (key.hasNextInt())
        {
            i = key.nextInt();
        }
        else
        {
            System.out.println("Вы ввели не число!");
        }
        return i;
    }

}
