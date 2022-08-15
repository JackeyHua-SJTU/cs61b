public class Animal {
	protected String name, noise;
	protected int age;

	public Animal(String name, int age) {
		this.name = name;
		this.age = age;
		this.noise = "Huh?";
	}

	public String makeNoise() {
		if (age < 5) {
			return noise.toUpperCase();
		} else {
			return noise;
		}
	}

	@Override
	public void greet() {
		System.out.println("Animal " + name + " says: " + makeNoise());
	}

}

public class Cat extends Animal {
	public Cat (String name, int age) {
		super(name, age);
		this.name = "Meow!";
	}

	public void greet() {
		System.out.println("Cat " + this.name + " says: " + this.makeNoise());
	}

}

/** Answer for Question 2 Raining Cats and Dogs
 *  (A) Animal Pluto says: Huh?
 *	(B) Cat Garfield says: Meow!
 *  (C) Dog Fido says: WOOF!
 *	(D) Cat Garfield says: Meow!
 *  (E) Cat Garfield says: Meow!
 *  cause a compile error -> change to d = (Dog) a;
 */

