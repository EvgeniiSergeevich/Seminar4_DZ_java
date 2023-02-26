import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Scanner;

public class Alg_Li {
    public static void main(String[] args) {
        runLi();

    }
    public static void runLi()                                      // Логика программы
    {
        ArrayDeque<int[]> states = new ArrayDeque<int[]>();
        ArrayDeque<int[]> walls = new ArrayDeque<int[]>();
        ArrayDeque<int[]> finish = new ArrayDeque<int[]>();
        ArrayDeque<int[]> finish2 = new ArrayDeque<int[]>();
        Scanner key = new Scanner(System.in);
        int[][] boardLi = board(key);                               // Игровое поле
        int[] start = markPoints(key, "точки старта");          // Координаты точки старта
        ArrayDeque <int[]> strt = new ArrayDeque<>();
        strt.addFirst(start);
        int[] coordPoint = new int[2];                              // Координаты точек
        boardLi[start[1]][start[0]] = 1;                            // Помечаю ячейку старта "1"
        System.out.print("Введите количество точек выхода(финиша): ");
        int finPointsAmount = consoleIn(key);                       // Количество точек финиша
        while (finPointsAmount != 0)                                // Отмечаю точки финиша "-2"
        {
            coordPoint = markPoints(key, "точки финиша");
            System.out.println(Arrays.toString(coordPoint) + " COORDS");
            finish.addLast(coordPoint);
            finish2.addLast(coordPoint);
            boardLi[coordPoint[1]][coordPoint[0]] = -2;
            finPointsAmount--;
        }
        System.out.println("FINISH" + Arrays.toString(finish.peek()));
        System.out.print("Ввдите количество стен: ");
        int wallAmount = consoleIn(key);                             // Создание стен


        for (int i = 0; i < boardLi.length; i++) {                   // Ставлю "стены" по периметру поля
            boardLi[0][i] = -1;
            boardLi[boardLi.length-1][i] = -1;
            boardLi[i][0] = -1;
            boardLi[i][boardLi.length - 1] = -1;
        }

        while (wallAmount != 0)                                      // Отмечаю точки стен "-1"
        {
            coordPoint = markPoints(key, "стены");
            walls.add(coordPoint);
            boardLi[coordPoint[1]][coordPoint[0]] = -1;
            wallAmount--;
        }

        System.out.println("Стартовое поле");
        printArray(boardLi);

        boardLi = algLi(boardLi, start, walls, finish);
        System.out.println("Поле с заполненными путями");
        printArray(boardLi);
        System.out.println("Кратчайший путь отмечен \"-7\", старт \"1\"");
        printArray(path(boardLi, finish2, strt));
    }


    public static int[][] algLi(int[][] array, int[] start, ArrayDeque walls, ArrayDeque finish)
    {
        ArrayDeque<int[]> states = new ArrayDeque<>();
        states.addFirst(start);
        int i = 1;

        while (finish.peek() != null)
        {
            int[] a = (int[]) finish.peek();
            try {
                if ((array[states.getFirst()[1] - i][states.getFirst()[0]] == 0 || array[states.getFirst()[1] - i][states.getFirst()[0]] == -2)) {
                    array[states.getFirst()[1] - i][states.getFirst()[0]] = array[states.getFirst()[1]][states.getFirst()[0]] + 1;
                    states.addLast(new int[]{states.getFirst()[0], states.getFirst()[1] - i});

                }
                if ((array[states.getFirst()[1]][states.getFirst()[0] + i] == 0 || array[states.getFirst()[1]][states.getFirst()[0] + i] == -2)) {
                    array[states.getFirst()[1]][states.getFirst()[0] + i] = array[states.getFirst()[1]][states.getFirst()[0]] + 1;
                    states.addLast(new int[]{states.getFirst()[0] + i, states.getFirst()[1]});
                }
                if ((array[states.getFirst()[1] + i][states.getFirst()[0]] == 0 || array[states.getFirst()[1] + i][states.getFirst()[0]] == -2)) {
                    array[states.getFirst()[1] + i][states.getFirst()[0]] = array[states.getFirst()[1]][states.getFirst()[0]] + 1;
                    states.addLast(new int[]{states.getFirst()[0], states.getFirst()[1] + i});
                }
                if ((array[states.getFirst()[1]][states.getFirst()[0] - i] == 0 || array[states.getFirst()[1]][states.getFirst()[0] - i] == -2)) {
                    array[states.getFirst()[1]][states.getFirst()[0] - i] = array[states.getFirst()[1]][states.getFirst()[0]] + 1;
                    states.addLast(new int[]{states.getFirst()[0] - i, states.getFirst()[1]});
                }
                states.pollFirst();

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
        return new int[size + 2][size + 2];
    }


    public static int[] markPoints(Scanner key, String str)           // Отмечаю точки на поле
    {
        System.out.printf("Укажите координату X %s (считая первую клетку, как 1,1): ", str);
        int x = consoleIn(key);
        System.out.printf("Укажите координату Y %s (считая первую клетку, как 1,1): ", str);
        int y = consoleIn(key);
        System.out.println();
        return new int[]{x, y};
    }


    public static void printArray(int[][] array)
    {
        for (int j = 0; j < array.length; j++)                     // Вывожу на экран игровое поле
        {
            for (int k = 0; k < array[j].length; k++)
            {
                System.out.print(array[j][k] + "   ");
            }
            System.out.println();
        }
        System.out.println();
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

    public static int[][] path(int[][] array, ArrayDeque finish, ArrayDeque start)
    {
        ArrayDeque <int[]>states = new ArrayDeque();

        int[] fin = (int[]) finish.peek();
        int min = array[fin[1]][fin[0]];
        while (finish.peek() != null)
        {
            int[] tmp = (int[]) finish.pop();
            if (min > array[tmp[1]][tmp[0]])
            {
                fin = tmp;
            }
        }
        states.addFirst(fin);
        System.out.println("Кратчайший путь равен: " + array[states.getFirst()[1]][states.getFirst()[0]]);
        while (start.peek() != null)
        {
            if (array[states.getFirst()[1] - 1][states.getFirst()[0]] == 1 || array[states.getFirst()[1]][states.getFirst()[0] + 1] == 1
                || array[states.getFirst()[1] + 1][states.getFirst()[0]] == 1 || array[states.getFirst()[1]][states.getFirst()[0] - 1] == 1)
            {
                start.pop();
            }
            if (array[states.getFirst()[1] - 1][states.getFirst()[0]] + 1 == array[states.getFirst()[1]][states.getFirst()[0]])
            {
                states.addLast(new int[]{states.getFirst()[0], states.getFirst()[1] - 1});
                array[states.getFirst()[1]][states.getFirst()[0]] = -7;
            }
            if (array[states.getFirst()[1]][states.getFirst()[0] + 1] + 1 == array[states.getFirst()[1]][states.getFirst()[0]])
            {
                states.addLast(new int[]{states.getFirst()[0] + 1, states.getFirst()[1]});
                array[states.getFirst()[1]][states.getFirst()[0]] = -7;
            }
            if (array[states.getFirst()[1] + 1][states.getFirst()[0]] + 1 == array[states.getFirst()[1]][states.getFirst()[0]])
            {
                states.addLast(new int[]{states.getFirst()[0], states.getFirst()[1] + 1});
                array[states.getFirst()[1]][states.getFirst()[0]] = -7;
            }
            if (array[states.getFirst()[1]][states.getFirst()[0] - 1] + 1 == array[states.getFirst()[1]][states.getFirst()[0]])
            {
                states.addLast(new int[]{states.getFirst()[0] - 1, states.getFirst()[1]});
                array[states.getFirst()[1]][states.getFirst()[0]] = -7;


            }

            states.pollFirst();
        }
        return array;
    }
}
