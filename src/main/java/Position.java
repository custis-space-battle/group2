public class Position {

    private int move (int position) {
        position = position + 2;
        if(position > 9)
            position = 0;
        return position;
    }

    public int[][] get_position() {
        int [][] pole = new int [10][10];

        for (int i = 0; i < 10; i++)
            for (int j = 0; j < 10; j++)
                pole[i][j] = 0;

        int start_x = (int)(Math.random() * 10);

        for (int i = 0; i < 4; i++)
            pole[i][start_x] = 1;
        for (int i = 5; i < 7; i++)
            pole[i][start_x] = 1;
        for (int i = 8; i < 10; i++)
            pole[i][start_x] = 1;

        start_x = move(start_x);

        for (int i = 0; i < 3; i++)
            pole[i][start_x] = 1;
        for (int i = 4; i < 7; i++)
            pole[i][start_x] = 1;
        for (int i = 8; i < 10; i++)
            pole[i][start_x] = 1;

        start_x = move(start_x);

        pole[0][start_x] = 1;
        pole[3][start_x] = 1;
        pole[6][start_x] = 1;
        pole[9][start_x] = 1;

        return pole;
    }

    public String get_coordinates_to_string()
    {
        String result = "";
        int [][]position = get_position();

        for (int i = 0; i < 10; i++)
            for (int j = 0; j < 10; j++)
                if (position[i][j] == 1)
                    result = result + (i+1) + "," + (j+1) + ";";

        result = result.substring(0, result.length() - 1);

        return result;
    }
}