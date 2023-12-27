def f(y,z,x=3,h=1):
    print "4"


def f(x):
    print "3"

def g(x=0,y=0):
    print "8"

f(1)
f(1,2)
f(1,2,3)
f(1,2,3,4)
g()
g(1)
g(2,1)
