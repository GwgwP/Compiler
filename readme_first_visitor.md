# Error Handling in MiniPython Compiler : Documentation

## Table of Contents
1. [Usage of Undeclared Variables](#usage-of-undeclared-variables)
2. [Calling Undeclared Functions](#calling-undeclared-functions)
3. [Incorrect Function Argument Definitions](#incorrect-function-argument-definitions)
4. [Use of Variables with Different Types](#use-of-variables-with-different-types)
5. [Operations Involving None and type()](#operations-involving-none-and-type)
6. [Incorrect Usage of Functions](#incorrect-usage-of-functions)

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
- <b>Explanation:</b> Attempting to call a function before its declaration is an error in MiniPython. We first check the error in `firstVisitor` an we double check it in second visitor.

- <b>Explanation:</b> In this case, the function g  is used without first being declared.t and if its not, we output the error.

## Incorrect Function Argument Definitions
```python
def add(x, z ,y=2):
    return x + y
print add(1)
```


- <b>How it is addressed:</b> `inAFuncCallFunctionCall` whenever we get a new function call we check if its id exists in the `functions` list

- <b>Explanation: </b> Ensure correct function argument definitions, especially when using default values. This code is not correct because when function `add` is called it is given 1 value when it should have been given 2 or 3 values
- <b>How it is addressed:</b> `inAFuncCallFunctionCall` We count how many arguments are  given in the function call and we compare it with the saved ones*. If we he have more arguments (more of the total arguments) or less arguments (less than the total-defaults) we print the error.
<i>* Sved ones: </i> When we have a new defined funciton we create a new instance of it (`Function.java`) and we count all the variables using `countVars`. In the Function instance we add its name, its total and its default parameters, 
