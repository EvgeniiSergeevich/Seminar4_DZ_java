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
        int[] start = markPoints(key, "старта");                              // Координаты точки старта
        int[] finishPoint = new int[2];                             // Координаты точки финиша
        int[] walls = new int[2];                                   // Координаты стены
        boardLi[start[1]][start[0]] = 1;                            // Помечаю ячейку старта "1"
        System.out.print("Введите количество точек выхода(финиша): ");
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
            finishPoint = markPoints(key, "финиша");
            boardLi[finishPoint[1]][finishPoint[0]] = -2;
            finPointsAmount--;
        }


        int wallAmount = 0;                                         // Создание стен
        System.out.print("Ввдите количество стен: ");
        if(key.hasNextInt())
        {
            wallAmount = key.nextInt();
        }
        else
        {
            System.out.println("Вы ввели не число!");
        }
        System.out.println(wallAmount + " WALL");

        while (wallAmount != 0)                                // Отмечаю точки финиша "-1"
        {
            walls = markPoints(key, "стен");
            boardLi[walls[1]][walls[0]] = -1;
            wallAmount--;
        }

        for (int i = 0; i < boardLi.length; i++)                    // Вывожу на экран Игровое поле
        {
            for (int j = 0; j < boardLi[i].length; j++)
            {
                System.out.print(boardLi[i][j] + "   ");
            }
            System.out.println();
        }
    }


    public static int[][] board(Scanner key)                                // Создаю квадратного поля, размером size
    {
        System.out.print("Укажите размер поля: ");
        int size = consoleIn(key);
        return new int[size][size];
    }


    public static int[] markPoints(Scanner key, String str)                // Отмечаю точки на поле
    {
        System.out.printf("Укажите координату X точки %s (считая первую клетку, как 1,1): ", str);
        int x = consoleIn(key) - 1;
        System.out.printf("Укажите координату Y точки %s (считая первую клетку, как 1,1): ", str);
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
