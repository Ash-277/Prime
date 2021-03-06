import java.lang.*;

 class TestCreate {
   public static void main(String[] args) {
        Cat catV = new Cat("Vasya", 10, 10, 10);
        Cat catF = new Cat("Fasya", 1, 2, 9);
        Cat catG = new Cat("Gasya", 8, 4, 2);
        System.out.println(catV.fight(catF));
        System.out.println(catF.fight(catG));
        System.out.println(catV.fight(catG));
    }

    public static class Cat {
        protected String name;
        protected int age;
        protected int weight;
        protected int strength;

        public Cat(String name, int age, int weight, int strength) {
            this.name = name;
            this.age = age;
            this.weight = weight;
            this.strength = strength;
        }

        public boolean fight(Cat anotherCat) {
            int agePlus = this.age > anotherCat.age ? 1 : 0;
            int weightPlus = this.weight > anotherCat.weight ? 1 : 0;
            int strengthPlus = this.strength > anotherCat.strength ? 1 : 0;

            int score = agePlus + weightPlus + strengthPlus;
            return score > 2; // return score > 2 ? true : false;
        }
    }
}
                }
            }
            return max;
        }
    }