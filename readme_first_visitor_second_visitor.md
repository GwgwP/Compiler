# Error Handling in MiniPython Compiler : Documentation

## Table of Contents
1. [Usage of Undeclared Variables](#1.-usage-of-undeclared-variables)
2. [Calling Undeclared Functions](#2.-calling-undeclared-functions)
3. [Incorrect Function Argument Definitions](#3.-incorrect-function-argument-definitions)
4. [Use of Variables with Different Types](#4.-use-of-variables-with-different-types)
5. [Operations Involving None](#5.-operations-involving-none-and-type)
6. [Incorrect Usage of Functions](#6.-incorrect-usage-of-functions)

---
Disclaimer:
We have created a `Function.java` file that stores the name, the total, the default parameters of a function defined.
## 1. Usage of Undeclared Variables
code example: 

```python
def add(x, y):
   return x + y
print k
k = 0 
```
- <b>Explanation:</b> In this case, the variable k is used before being declared. A variable is considered declared if it is assigned a value or used as an argument in a function declaration. Note that all variables are considered global.

- <b>How it is addressed:</b> `inAIdId` whenever we get a new id we check if it exists in the `variableTypes` and if its not, we output the error. Also we have an error if we are in a for statement and the second id is not defined. Also we have an error if we have a return statement with an undeclared variable.
code example: 

```python
def f(x, y):
    for i in z:
        print 1
```
- <b>Explanation:</b> In this case, the variable z is used without first being initialized. (i doesnt have any problem)

- <i>Disclaimer: </i> we add a new id and its type when we are at `inSingleQuotesValueno`, `inANumNum` etc, so we have both type and the id of a new variable.

## 2. Calling Undeclared Functions
```python
def f(x, y):
    for i in z:
        print 1
g(1)
```

- <b>Explanation:</b> In this case, the function g  is used without first being declared.t and if its not, we output the error.
- <b>Info:</b> Attempting to call a function before its declaration is NOT an error in MiniPython. That's why We check the error in `MySecondVisitor`.
- <b>How it is addressed:</b> We have Data Structures when we save the `functions` and the argument list (this is not applicable here) when we are in `outAFuncCallFunctionCall` we check the data structure if it contains the id of the function call and if not, the we print the error. 
- <i>Disclaimer: </i> the data structure is completed (contains all the defined functions) from the in's of `MyFirstVisitor` so we address the fact that we can have a function call before its function declaration.

## 3. Incorrect Function Argument Definitions
(Incorrect number of arguments given)
```python
def add(x, z ,y=2):
    return x + y
print add(1)
```


- <b>How it is addressed:</b> 

- <b>Explanation: </b> Ensure correct function argument definitions, especially when using default values. This code is not correct because when function `add` is called it is given 1 value when it should have been given 2 or 3 values
- <b>How it is addressed:</b> `inAFuncCallFunctionCall` whenever we get a new function call we check if its id exists in the `functions` list
We count how many arguments are  given in the function call and we compare it with the saved ones*. If we he have more arguments (more of the total arguments) or less arguments (less than the total-defaults) we print the error.

<i>* Saved ones: </i> When we have a new defined funciton we create a new instance of it (`Function.java`) and we count all the variables using `countVars`. In the Function instance we add its name, its total and its default parameters,
 
## 4. Use of Variables with Different Types
Use of Variables with Different Types (as integers) 
Exact code errors:
```python 
x="hello world"
print x+2
```
```python 
def add(x,y):
   return x + y
k="hello world"
print add(2,k)
```
- <b>Explanation: </b>
- <b>How it is addressed:</b> 

## 5. Operations Involving None
Arithmetic operations invilving None type code errors:
```python 
def g(e,r):
    e = 1
    return e+None
```
- <b>Explanation:</b> Be cautious when performing operations with None as operator. 
- <b>How it is addressed:</b>  
    1. When we are in an `inIdId` node, first we check if the 3rd parent of the node is an arithmetic operation (such as `addition`, `substraction` etc), if yes, we check if the id (name) is contained in the `variableTypes` HashTable. If yes, we check the type of the variable and if it is "None" we print the error. (meaning that we have addressed the error that an arithmetic operation utilizes None type from another variable.)
    2. When we are in an `inANoneValueValueno` we check if the 2nd parent is an arithmetic operation (such as `addition` etc) and if yes, we print the error.


## 6. Incorrect Usage of Functions
```python 
def add(x,y):
   return "hello world"
print add(2,1)+2
```
- <b>Explanation: </b>
- <b>How it is addressed:</b> 

## 7. 
```python 
def add(x,y):
def add(x,y,z=1)
```
```python 
def add(x,y,z):
def add(x,y,z=1)
```
- <b>Explanation: </b>
- <b>How it is addressed:</b> 



