public class disc6 {
    public void question1_1() {
        /**
         * Pebble is mutable.
         * Rock is immutable.
         * Rocks is mutable, elements in the list that rocks point to can be changed.
         * SecretRocks is mutable.
         * PunkRock is mutable.
         * MommaRock is mutable.
         */
    }

    public void question2_1() {
        /**
         * BadIntegerStack test = new BadIntegerStack();
         * test.top.peek();
         * -> NullPointerException
         *
         * BadIntegerStack test = new BadIntegerStack();
         * test.top = new Node(1, null);
         * test.top.prev = test.top;
         * -> Infinite
         *
         * change the nested Node class into private
         * use the isEmpty() function to judge whether it is an empty stack
         * change top into private
         */
    }

    public void question3() {
        /**
         *
         * there should be one class called parkLot and another class called car
         *
         * private String type
         * private int[] state (0 stands for unavailable, 1 stands for vacant)
         * private typeCheck() (check whether the allocated parking lot meets the needs of the car type)
         * private int totalLot
         * private double distance()
         * public int allocate(String type)
         *
         */
    }

}
