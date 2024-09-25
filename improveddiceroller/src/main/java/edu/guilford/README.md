# Improved Dice Roller

Your objective in this project is to create a dice roller. Of course, you've already done that, so this should do something extra. This time, the dice roller inputs a number of dice, the size of the dice, and a number of low dice to drop.

In a popular paper & pencil game, we commonly roll 4d6 and drop the lowest. The same game sometimes allows a player to roll 2d20 and drop the lower (called rolling at advantage). This program allows you to do those.

In this project you will use the following ideas.

- input using `Scanner` methods
- loop to repeat actions
- use `break` to exit a loop
- create an array
- randomize integers
- sort an array using `Array` methods
- sum an array using a `for` loop

## Sample program

You've been given a sample program that generates an array of 10 integers from 0 to 99, prints them as generated, sorts them, and then prints the sorted array. 

## Getting started

Write a print statement that lets the user know the purpose of the program.

For organizational purposes, let's put all of our declarations next. In later parts of the project, I'll say "put this in the declarations section". That means to put it here.

Declare and instantiate a `Scanner` object with a name like `scan` that uses `System.in`. Declare a `char` variable with a name like `yesno`. This time, let's create our repeater loop in a different way.

```
    while (true) {
        System.out.println("Do you want to continue? Y/N ");

        yesno = scan.next().toUpperCase().charAt(0);
    }
```

Test this. It *should* just repeat the question over and over. You can escape the loop by hitting control-C or by clicking the red lined stop button. This is because the condition for the `while` statement is always `true`.

Since you want to be able to exit the loop, create an `if` statement like the following after the input line but before the end of the loop.

```
    if (yesno == 'N') {
        break;
    }
```

The `break` statement is purposely designed to exit loops this way. You could certainly construct your loop as in the previous assignment, this just seemed like a convenient time to introduce `break`. Notice this structure only exits on some form of "no", whereas the previous only continued on some form of "yes".

Test this to make sure it behaves the way you think it should.

## Adding the inputs

Now add declarations of the integers `number` for the number of dice, `sides` for the number of sides on each die, and `low` for the number of low rolls to drop. Put this in the declaration section - if you were to make the declaration inside the loop, you would get errors when it tried to declare a variable that already existed.

Inside the repeater loop, add print and input statements that ask the user how many dice to roll (`number`), what size dice to roll (`sides`), and how many low rolls to drop (`low`).

Create another print statement to test these. Something like this.

```
    System.out.print("Rolled " + number + "d" + sides);
    System.out.println(" and dropped the " + low + " lowest.");
```

Test this to make sure it's doing what you expect.

## Creating a method

As in the previous assignment, create a function like this.

```
    private static int diceroll( int num, int size, int drop) {
        return 0;
    }
```

Having a minimal working example is convenient. The IDE will complain that you've created a method and never used it. So modify the preceding output like so.

```
    System.out.print("Rolled " + number + "d" + sides);
    System.out.print(" and dropped the " + low + " lowest. ");
    System.out.println("Total = " + diceroll(number, sides, low) + ".");
```

Test this. It should print things like "Rolled 3d6 and dropped the lowest 1. Result = 0." if you had input 3, 6, and 1 respectively.

## Creating dice rolls

Inside the `diceroll` method, create an array of integers like this.

```
    int[] rolls = {1, 2, 3};

    return rolls[0];
```

Test this - it should give the result that the dice rolls totalled 1 no matter your input.

An array is a list of numbers or other objects - in this case {1, 2, 3} in that order. The first one is `rolls[0]`, the second `rolls[1]`, and the last `rolls[3]`. (Once you get used to numbering that starts with 0, it is very annoying to encounter other languages that start with 1...)

Since we want to roll dice, but don't know how many, change to this.

```
    int[] rolls = new int[10];

    return rolls[0];
```

Test this. It should return 0, but in other languages - even earlier versions of Java - it would return an error. This suggests integer arrays default to 0 unless another value is provided.

To assign values to `rolls`, we use a `for` loop. Notice that the structure of a `for` loop is ideally designed for this. If you start typing `for` and let VSCode autocomplete, it will provide the following.

```
    for (int i = 0; i < 10; i++){

    }
```

Inside the block of the `for` loop, insert this code.

```
    rolls[i] = i + 1;
```

The variable `i` is a "local" variable. It only exists inside the loop - you cannot access it from outside the loop! It initially has a value of `i = 0` and is incremented by the `i++` until it reaches 10 - at which case the loop ceases to repeat. And as the variable is local, then it disappears.

By putting an assignment statement this way, we have set `rolls[0] = 1`, `rolls[1] = 2`, etc. Test this - you should get 1 from the `return` statement, and you can change it to output some other entry.

## Randomizing the rolls

Since we want to randomize the values, we will need a randomizer. Import `Random` as before - declaring it inside the `Main` class, but outside any method. This could look like the following.

```
    public class Main {

    static final Random rand = new Random();

    private static int diceroll(int num, int size, int drop) {
        int[] rolls = new int[num];
        ...
```

As previously discussed, `static` and `final` are necessary to create a "global" variable like this. Also, having a single randomizer reduces resource usage. VSCode will complain because you've instantiated an object but never used it, but we'll fix that next.

Inside of `diceroll`, modify your code to 

```
   private static int diceroll(int num, int size, int drop) {
        int[] rolls = new int[num];

        for (int i = 0; i < num; i++) {
            rolls[i] = rand.nextInt(size) + 1;
        }

        return rolls[0];
    }

```

Notice that `rand.nextInt(size) + 1` will produce an integer from 1 to `size`. When you test this, you should now get different values each time you run it. But we wanted to sum these dice, so we need to add the following.

```
    ...
    int sum = 0;

    for (int i = 0; i < num; i++) {
        sum += rolls[i];
    }

    return sum;
```

Here we've started returning the sum instead of a single entry. Test this to make sure it's getting reasonable results.

## Dropping the low rolls

This got us most of what we wanted. Now we just have to drop the low rolls. But since they were randomly assigned, there is no way to know where the low values are! It sure would be convenient if they were sorted, right?

In your import section add `import java.util.Arrays;` - this is a collection of functions that are useful when dealing with arrays. In `diceroller`, modify the ending to something like this.

```
    ...
    int sum = 0;
    Arrays.sort(rolls);

    for (int i = 0; i < num; i++) {
        sum += rolls[i];
    }

    return rolls[0];
```

Test this. It will probably return 1 or 2 most of the time now. This is because `rolls` are sorted in ascending order. There are options to put the largest values first - if you want to know about this, Google `Arrays.sort`.

Since we want to drop the lowest dice rolls, we further make this modification.

```
    ...
    int sum = 0;
    Arrays.sort(rolls);

    for (int i = drop; i < num; i++) {
        sum += rolls[i];
    }

    return sum;
```

This will start adding the member of `rolls` numbered `drop`, so that the first few simply never get added to the sum. 

This is pretty neat! And if the user asks to drop more dice than are rolled, then none of `rolls` gets added, and `sum` is 0.

Test this by running it multiple times with 2d6 drop the lowest 1. Unless you are VERY patient, you probably won't get a 1 to come up, and rarely a 2. 

If you feel inclined, you can use this to test the notion that "rolling with advantage" in a certain game gets a lot of 20s. To do this, tell it to roll 2d20 and drop the lowest 1. Normally, when rolling a d20, we get a 20 about 5% of the time. When you do this, it jumps to 10.25% - making it happen more than twice as often.

## Wrapping up

Look back at your program. Comment each section of code so that you can look back at it and know what it did and why. Don't forget your name and date!

When you're ready, STAGE your changes by hovering over the list to get a + sign, then clicking on that. COMMIT your changes by giving a message and clicking the button. SYNC your changes by clicking the resulting button. COMPLETE by indicating on Canvas you're ready for me to grade it.