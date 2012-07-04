jsick-app
=========

jsick (`djay-si:k`) programming language.

Code:

    int x = 123; /* assign 123 to x */
    int y = 2 * x + 1;
    double z = (x + y) / 2.0; // dividing int by double
    ? x; // print x
    ? y;
    ? z;
    ? z - 4; // print (z - 4) 

Compiled as:

    // Compiled from HelloWorld.java (version 1.5 : 49.0, no super bit)
    public class HelloWorld {
      
      // Method descriptor #7 ()V
      // Stack: 1, Locals: 1
      public HelloWorld();
        0  aload_0 [this]
        1  invokespecial java.lang.Object() [9]
        4  return

      
      // Method descriptor #11 ([Ljava/lang/String;)V
      // Stack: 100, Locals: 4
      public static void main(java.lang.String[] arg0);
         0  ldc <Integer 123> [12]
         2  istore 0 [arg0]
         4  ldc <Integer 2> [13]
         6  iload 0 [arg0]
         8  imul
         9  ldc <Integer 1> [14]
        11  iadd
        12  istore 1
        14  iload 0 [arg0]
        16  iload 1
        18  iadd
        19  i2d
        20  ldc2_w <Double 2.0> [15]
        23  ddiv
        24  dstore 2
        26  getstatic java.lang.System.out : java.io.PrintStream [22]
        29  iload 0 [arg0]
        31  invokevirtual java.io.PrintStream.println(int) : void [28]
        34  getstatic java.lang.System.out : java.io.PrintStream [22]
        37  iload 1
        39  invokevirtual java.io.PrintStream.println(int) : void [28]
        42  getstatic java.lang.System.out : java.io.PrintStream [22]
        45  dload 2
        47  invokevirtual java.io.PrintStream.println(double) : void [31]
        50  getstatic java.lang.System.out : java.io.PrintStream [22]
        53  dload 2
        55  ldc <Integer 4> [32]
        57  i2d
        58  dsub
        59  invokevirtual java.io.PrintStream.println(double) : void [31]
        62  return

    }

Executed as:

    123
    247
    185.0
    181.0

Task definition
===============
I'm going to stop as soon as following things are done:

 * `boolean` type and comparison operators (`==`, `>=`, `<=`, `!=`, `<`, `>`, `!`, `&&`, `||`)
 * `int` and `double` types and related operators (`<<`, `>>`, `+=`, `-=`, `*=`, `/=`, `++`, `--`, `+`, `-`, `*`, `/`, `%`, `&`, `|`, `^`)
 * `void` type
 * `string` type and related operators (`+`, `+=`, formatting, char access)
 * functions/procedures
 * `if`, `else`
 * `for`, `while`, `do-while`, `continue`, `break`

