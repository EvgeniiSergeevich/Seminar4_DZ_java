import java.util.ArrayDeque;
import java.util.Arrays;
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
        ArrayDeque<int[]> states = new ArrayDeque<int[]>();
        ArrayDeque<int[]> walls = new ArrayDeque<int[]>();
        ArrayDeque<int[]> finish = new ArrayDeque<int[]>();
        Scanner key = new Scanner(System.in);
        int[][] boardLi = board(key);                               // Игровое поле
        int[] start = markPoints(key, "точки старта");          // Координаты точки старта
        int[] coordPoint = new int[2];                              // Координаты точек
        boardLi[start[1]][start[0]] = 1;                            // Помечаю ячейку старта "1"
        System.out.print("Введите количество точек выхода(финиша): ");
        int finPointsAmount = consoleIn(key);                       // Количество точек финиша
        while (finPointsAmount != 0)                                // Отмечаю точки финиша "-2"
        {
            coordPoint = markPoints(key, "точки финиша");
            System.out.println(Arrays.toString(coordPoint) + " COORDS");
            finish.addLast(coordPoint);
            boardLi[coordPoint[1]][coordPoint[0]] = -2;
            finPointsAmount--;
        }

        System.out.print("Ввдите количество стен: ");
        int wallAmount = consoleIn(key);                             // Создание стен



        while (wallAmount != 0)                                      // Отмечаю точки финиша "-1"
        {
            coordPoint = markPoints(key, "стены");
            walls.add(coordPoint);
            boardLi[coordPoint[1]][coordPoint[0]] = -1;
            wallAmount--;
        }

//        int[] a = finish.peek();
//        System.out.println(Arrays.toString(a) + " runLi");

        boardLi = algLi(boardLi, start, walls, finish);

        for (int i = 0; i < boardLi.length; i++)                     // Вывожу на экран игровое поле
        {
            for (int j = 0; j < boardLi[i].length; j++)
            {
                System.out.print(boardLi[i][j] + "   ");
            }
            System.out.println();
        }
    }


    public static boolean isValidIndex(int[] array, int index)
    {
        return index >=0 && index < array.length;
    }

    public static int[][] algLi(int[][] array, int[] start, ArrayDeque walls, ArrayDeque finish)
    {
        ArrayDeque<int[]> states = new ArrayDeque<>();
        states.addFirst(start);
        System.out.println(Arrays.toString(states.peek()) + " States");
        int i = 1;
        int[] a = (int[]) finish.peek();
        System.out.println("array in");

        for (int j = 0; j < array.length; j++)                     // Вывожу на экран игровое поле
        {
            for (int k = 0; k < array[j].length; k++)
            {
                System.out.print(array[j][k] + "   ");
            }
            System.out.println();
        }
        System.out.println();

        while (finish.peek() != null)
        {

            try {
//                System.out.println(Arrays.toString(states.getFirst()) + " states.getFirst()");
                if (isValidIndex(array[states.getFirst()[1]], states.getFirst()[0] - i) &&
                        (array[states.getFirst()[1] - i][states.getFirst()[0]] == 0 || array[states.getFirst()[1] - i][states.getFirst()[0]] == -2)) {
                    array[states.getFirst()[1] - i][states.getFirst()[0]] = array[states.getFirst()[1]][states.getFirst()[0]] + 1;
                    states.addLast(new int[]{states.getFirst()[0], states.getFirst()[1] - i});

                } else {
                    System.out.println("else1");
                }
                if (isValidIndex(array[states.getFirst()[0]], states.getFirst()[0] + i) &&
                        (array[states.getFirst()[1]][states.getFirst()[0] + i] == 0 || array[states.getFirst()[1]][states.getFirst()[0] + i] == -2)) {
                    array[states.getFirst()[1]][states.getFirst()[0] + i] = array[states.getFirst()[1]][states.getFirst()[0]] + 1;
                    states.addLast(new int[]{states.getFirst()[0] + i, states.getFirst()[1]});
                } else {
                    System.out.println("else2");
                }
                if (isValidIndex(array[states.getFirst()[1]], states.getFirst()[0] + 1) &&
                        (array[states.getFirst()[1] + i][states.getFirst()[0]] == 0 || array[states.getFirst()[1] + i][states.getFirst()[0]] == -2)) {
                    array[states.getFirst()[1] + i][states.getFirst()[0]] = array[states.getFirst()[1]][states.getFirst()[0]] + 1;
                    states.addLast(new int[]{states.getFirst()[0], states.getFirst()[1] + i});
                } else {
                    System.out.println("else3");
                }
                if (isValidIndex(array[states.getFirst()[0]], states.getFirst()[0] - i) &&
                        (array[states.getFirst()[1]][states.getFirst()[0] - i] == 0 || array[states.getFirst()[1]][states.getFirst()[0] - i] == -2)) {
                    array[states.getFirst()[1]][states.getFirst()[0] - i] = array[states.getFirst()[1]][states.getFirst()[0]] + 1;
                    states.addLast(new int[]{states.getFirst()[0] - i, states.getFirst()[1]});
                } else {
                    System.out.println("else4");
                }
//            finish.pop();
                states.pollFirst();
                for (int j = 0; j < array.length; j++)                     // Вывожу на экран игровое поле
                {
                    for (int k = 0; k < array[j].length; k++) {
                        System.out.print(array[j][k] + "   ");
                    }
                    System.out.println();
                }
                System.out.println();
//            System.out.println(Arrays.toString(array) + " array " + Arrays.toString(array[a[0]]) + " array[a[0]] " + Arrays.toString(array[a[1]]) + " array[a[1]]");
//            System.out.println(array[a[0]][a[1]] + " array[a[0]][a[1]]");

                if (array[a[1]][a[0]] != (-2)) {
                    finish.pop();
                }
            }
            catch (ArrayIndexOutOfBoundsException e)
            {
                e.printStackTrace();
            }
        }
        return array;
    }
    public static int[][] board(Scanner key)                         // Создаю квадратного поля, размером size
    {
        System.out.print("Укажите размер поля: ");
        int size = consoleIn(key);
        return new int[size][size];
    }


    public static int[] markPoints(Scanner key, String str)           // Отмечаю точки на поле
    {
        System.out.printf("Укажите координату X %s (считая первую клетку, как 1,1): ", str);
        int x = consoleIn(key) - 1;
        System.out.printf("Укажите координату Y %s (считая первую клетку, как 1,1): ", str);
        int y = consoleIn(key) - 1;
        System.out.println();
        return new int[]{x, y};
    }


    public static int consoleIn(Scanner key)                            // Читаю с консоли число
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

//    public static int[][] path(int[][] array, ArrayDeque finish)
//    {
//        int[] a = (int[]) finish.getFirst();
//
//
//    }
}
