public class RMQ {

    public int[][] queries;
    public int[] pairs;

    public RMQ(int[] array){
        queries = new int[array.length][array.length];
        pairs = array;
        preProcess();
    }

    public void preProcess(){
        for (int i = 0; i < pairs.length; i++){
            for (int j = 0; j < pairs.length; j++){
                queries[i][j] = getMinimum(i, j);
            }
        }
    }

    public int getMinimum(int i, int j){
        int min = 0;
        if (i < j) {
            min = pairs[i];
            for (int x = i + 1; x <= j; x++) {
                if (min > pairs[x]) {
                    min = pairs[x];
                }
            }
        }else if (i > j){
            min = pairs[i];
            for (int x = j + 1; x <= i; x++) {
                if (min > pairs[x]) {
                    min = pairs[x];
                }
            }
        }
        return min;
    }

    public int getQuery(int i, int j){
        if (i < queries.length && j < queries.length){
            return queries[i][j];
        }
        return 0;
    }

    public static void main(String[] args) {
        int[] ints = new int[10];
        ints[0] = 1;
        ints[1] = 5 ; ints[2] =9  ;ints[3] =-1; ints[4] = -12 ;ints[5] = 16 ;ints[6] = 7; ints[7] = 10; ints[8] = 25 ;ints[9] = 28;

        RMQ rmq = new RMQ(ints);
        System.out.println(rmq.getQuery(8,9));
        System.out.println(rmq.getQuery(0,9));
        System.out.println(rmq.getQuery(5,9));
    }
}
