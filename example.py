#! /usr/bin/env python
#A miniPython example

def fib(n):    # write Fibonacci series up to n

       x = 5
       if x == 5:
              y = x + 1
             

       a = 9
       b = 1
       while a < n:
              print a
              a = b
              b = a + b
                      
def funcwithdef(name,university="aueb"):
       print \t + name, " studies in ", university
