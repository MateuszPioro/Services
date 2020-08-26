package Optional;

class App {
    int rearrange(int n) {
        int m = 0;
        while (n != 0) {
             int c = n %10;
             m = m*10 + c;
             n/=10;
        }
        return m;
    }

    public static void main(String[] args) {

        App app = new App();
         System.out.println(app.rearrange(4126 ));
    }
}
