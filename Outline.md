# KARP (.kp)

## A fun little high-level programming language by Garrison Mullen following the work of Robert Nystrom's *Crafting Interpreters*

You should definitely give this guy some kudos if you ever get the chance; he rocks. Most of the work here comes straight from his [book](https://craftinginterpreters.com/).

## Section 0 | Introduction

Welcome to Karplang, or Karp for short. Karp is a very simple (more nihilistic than minimalistic, as Nystrom says) high-level, dynamically-typed language with automatic memory management in the C language family.

A Karp snippet is found below:

```.kp
?? Hello world
say "Hello world!"|
```

Comments in Karp are denoted with "??" (because comments are there to answer questions others may have), and lines ended with the pipe (because it looks more suave than the humble semicolon).
The statement "say" is functionally equivalent to most languages' console print statement (it personifies the machine more and that's fun).

## Section I | Data Types

### Booleans

Two values, two literals:

```.kp
true| ?? true
false| ?? false
```

### Numbers

Karp only supports double-precision floating points to represent numbers. Basic integer and decimal literals are found below:

```.kp
1234| ?? An integer
12.34| ?? A decimal
```

### Strings

```.kp
"This is a string"|
""| // Empty string
"123"| // String representation of a number
```

### Nil

Represents the absence of any value, like null or void might in other languages.

## Section II | Expressions

### Arithmetic Operators

The following operations only take numbers, with the exception of the '+' operator, which is also used to concatenate strings.

```.kp
add + this|
subtrack - that|
multiply * oneathese|
divide / oneathose|
-negateThis|
```

### Comparison and Equality Operators

```.kp
less < than|
lessThan <= orEqual|
greater > than|
greaterThan >= orEqual|
```

Two values of any kind can be tested for (in)equality, even different types (though values of different types are *never* equivalent, because implicit typing sucks).

```.kp
1 == 2| ?? false
"cat" ^= "dog" ?? true

314 == "pi"| ?? false
123 == "123"| ?? false
```

### Logical Operators

The '^' operator prefixes a value and returns its logical inverse.

```.kp
^true| ?? evaluates to false
^false| ?? evalustes to true
```

The "and" expression checks Boolean truth and equality among operators, returning the right operand if both values are true and the left operand otherwise.

```.kp
true and false| ?? false
true and true| ?? true
```

The "or" expression checks only Boolean truth among operators, returning the left operand if true and the right operand otherwise.

```.kp
false or false| ?? false
true or false| ?? true
false or true| ?? true
```

### Precedence and Grouping

All of the above operators have the same precedence and associativity expected from the C family. Parenthesis can be used to group things.

## Section III | Statements

"say" is used as Karp's print statement. It was shown above.

```.kp
say "something"|
```

Expression statements are formed by (take a guess) appending a pipe to an expression, making it a statement.

```.kp
"an expression"|
```

A block is formed with braces to provide a series of statements where one is expected.

```.kp
{
    "statement 1"|
    125|
    "statement 3"|
}
```

Blocks also affect scoping.

## Section IV | Variables

Variables are declared with the "var" statement. Without an initializer, the variable will default to a value of nil.

```.kp
var thisIsAVar = "some value"|
var thisIsNil|

print thisIsAVar| ?? outputs "some value"
thisIsAVar = "test"|
print thisIsAVar| ?? outputs "test" now
```

The rules for variable scope follow those expected from C or Java by and large.

## Section V | Control Flow

If statements executes one of two statements based on one condition.

```.kp
if (condition) {
    say "yes"|
} else {
    say "no"|
}
```

A while loop executes the body repeatedly as long as the condition evaluates to true.

```.kp
var a = 1|
while (a < 10) {
    say a|
    a = a + 1|
}
```

For loops are like while loops with initialization and modification of the condition expression included in the expression itself.

```.kp
for (var a = 1| a < 10| a = a + 1) {
    say a|
}
```

## Section VI | Functions

Function calls resemble those in C or Java.

```.kp
makeBreakfast(eggs, sausage)|
makeBreakfastNoArgs()|
```

Functions are defined with the keyword "func".

```.kp
func printSum(a, b) {
    say a + b|
}
```

Values are returned from functions with the keyword "reply". In the absence of a reply statement, nil is implicitly returned.

```.kp
func returnSum(a, b) {
    reply a + b|
}
```

### Closures

Functions in Karp are first class. They are real values that can be referenced, stored in vars, and passed around.

```.kp
func addPair(a, b) {
    reply a + b|
}

func identity(a) {
    reply a|
}

say identity(addPair)(a, b)| ?? prints "3"
```

Local functions can be declared inside other functions.

```.kp
func outerFunc() {
    func innerFunc() {
        say "I'm local!"|
    }

    innerFunc()|
}
```

Functions in Karp are also closures, meaning code like the below will work even though the scoping is weird.

```.kp
func returnFunc() {
    var outer = "out"|

    func inner() {
        say outer|
    }

    reply inner|
}

var func = returnFunc()|
func()|
```

## Section VII | Classes

Karp is just a bit object-oriented. Don't hold it against me. Or it.
Karp is not, however, a *pure* OOP language. Values of primitive types (booleans, numbers, etc.) are not instances of classes and lack methods and properties.

```.kp
class Meal {
    cook() {
        say "Cooking!"|
    }

    serve(someone) {
        say someone + " cleaned their plate..."|
    }
}
```

Note that the methods within a class aren't declared the same way as functions - cheifly, the declarations lack the func keyword.

```.kp
var someVar = Meal|
someFunc(Meal)|
```

Classes self-instantiate when called, so the calls mirror those of functions to create a new object of the class.

```.kp
var meal = Meal()|
say meal|
```

Assigning properties to a field creates said field if it doesn't already exist.
Additionally, the keyword "this" accesses a field/method on the current object from within one of its own methods.

```.kp
class Breakfast {
    serve(who) {
        say "Enjoy your " + this.meat|
    }
}

var breakfast = Breakfast()|
breakfast.meat = "sausage"|
breakfast.serve("James")|
```

The method setup(param1, param2, ...) acts as a constructor for the class it resides in and is called automatically upon object construction. Any parameters passed to the class are forwarded to the constructor.

```.kp
class Breakfast {
    setup(meat, bread) {
        this.meat = meat|
        this.bread = bread|
    }

    ?? ...
}

var blt = Breakfast("bacon", "rye")|
blt.serve("Mike")|
```

### Inheritance

Inheritance is specified with the < operator.
Every method defined in the superclass is available to the subclass.

```.kp
class Brunch < Breakfast {
    drink() {
        say "How about a mimosa?"|
    }
}

var hamToastie = Brunch("ham", "white")|
hamToastie.serve("Jeff")|
```

The super keyword calls the superclass' constructor in its subclass.

```.kp
class Brunch < Breakfast {
    setup(meat, bread, drink) {
        super.setup(meat, bread)|
        this.drink = drink|
    }
}
```

## Section VIII | The Karp Standard Library

The standard library of Karp is where its "nihilism" begins to show itself.
As of now, the standard library only includes a built-in clock() function that returns the number of seconds since the program started.
