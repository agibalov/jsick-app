jsick-app
=========

jsick (`djay-si:k`) programming language.

Code:

    x = 123; /* assign 123 to x */
    y = 2 * x + 1;
    z = (x + y) / 2.0; // dividing int by double causes z to be double
    ? x; // print x
    ? y;
		? z;
		? z - 4; // print (z - 4)

Compiled as:

    push.i 123
    save.0
    push.i 2
    load.0
    mul.i
    push.i 1
    add.i
    save.1
    load.0
    load.1
    add.i
    cast.i.d
    push.d 2,000000
    div.d
    save.2
    load.0
    print.i
    load.1
    print.i
    load.2
    print.d
    load.2
    push.i 4
    cast.i.d
    sub.d
    print.d

Executed as:

    PRINT 123
    PRINT 247
    PRINT 185.000000
    PRINT 181.000000

