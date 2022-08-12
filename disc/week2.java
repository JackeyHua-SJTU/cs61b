/** CS61B课程第二周Discussion部分的代码实现*/


public class Pokeman{
    public String name;
    public int level;

    public Pokeman(String name, int level){
        /** 可以看出如果参数和类的变量重名，必须用this才能访问类的变量*/
        this.name = name;
        this.level = level;
    }

    public static void change(Pokeman poke, int level){
        poke.level = level;
        level = 50;
        /** 注意：参数传递的时候会在函数体的那一帧创建一个叫poke的变量，并且值为传进来的地址
         *  但下面的赋值语句改变了poke的地址，却不会影响main函数里的变量 */
        poke = new Pokeman("Gengar", 1);
    }

    public static void main(String[] args){
        Pokeman p = new Pokeman("Pikachu", 17);
        int level = 100;
        change(p, level);
        System.out.println("Name: " + p.name + ", Level: " + p.level);
    }

}

public class cat{
    public String name;
    /** static的变量是整个类共有的，改变一次就会改变所有 */
    public static String noise;

    public cat(String name, String noise){
        this.name = name;
        this.noise = noise;
    }

    public void play(){
        System.out.println(noise + " I'm " + name + " the cat!");
    }

    public static void anger(){
        noise = noise.toUpperCase();
    }

    public static void calm(){
        noise = noise.toLowerCase();
    }

    public static void main(String[] args){
        cat a = new cat("Cream", "Meow!");
        cat b = new cat("Tubbs", "Nyan!");
        a.play();
        b.play();
        cat.anger();
        a.calm();
        a.play();
        b.play();
    }

}

public class IntList{
    int first;
    IntList rest;

    public IntList(int f, IntList r){
        first = f;
        rest = r;
    }

    public static IntList square(IntList L){
        if(L == null){
            return null;
        }
        return new IntList(L.first * L.first, square(L.rest));
    }

    public static IntList iter_square(IntList L){
        if(L == null){
            return null;
        }
        /** 不能迭代并返回同一个列表，需要借助一个迭代的去完成操作，并返回原来的*/
        IntList the_return_one = new IntList(L.first, null);
        IntList temp_one = the_return_one;
        L = L.rest;
        while(L != null){
            temp_one = new IntList(L.first * L.first, null);
            temp_one = temp_one.rest;
            L = L.rest;
        }
        return the_return_one;
    }

    private static void change(IntList L){
        if(L == null){
            return;
        }else{
            L.first = L.first * L.first;
            change(L.rest);
            return;
        }
    }

    public static IntList squareMutative(IntList L){
        if(L == null){
            return L;
        }else{
            change(L);
            return L;
        }
    }

    public static void main(String[] args){
        IntList L = new IntList(3, IntList(2, IntList(1, null)));
        IntList.squareMutative(L);
    }

    public class Horse{
        private Horse same;
        private String jimmy;

        public Horse(String lee){
            jimmy = lee;
        }

        public Horse same(Horse horse){
            if(same != null){ /* 这一行的same指的是this.same，因为在这之前没有同名变量的定义 */
                Horse same = horse; /* 在这之后，单独说same指的是Horse same而非this.same */
                same.same = horse;
                same = horse.same;
            }
            return same.same; /* 这一行的same是this.same，因为Horse same定义在上一个程序块中，执行完if语句后就会被回收 */
        }

        public static void main(String[] args){
            Horse horse = new Horse("you've been");
            Horse cult = new Horse("horsed");
            cult.same = cult;
            cult = cult.same(horse);
            System.out.println(cult.jimmy);
            System.out.println(horse.jimmy);
        }
        /** It'll print 
         *  horsed
         *  you've been */

    }

}
