jsick-app
=========

jsick (`djay-si:k`) programming language.

Code:

    x=123;y=2*x+1;z=(x+y)/2;?x;?y;?z;?z-4;

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
    push.i 2
    div.i
    save.2
    load.0
    print.i
    load.1
    print.i
    load.2
    print.i
    load.2
    push.i 4
    sub.i
    print.i

Executed as:

    PRINT 123
    PRINT 247
    PRINT 185
    PRINT 181

