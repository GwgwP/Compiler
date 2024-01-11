# Documentation
## MiniPython Compiler Extension Abstract Syntax Tree (with SableCC)
The purpose of constructing an abstract syntax tree is to discard elements and nodes that are unnecessary for the subsequent stages of compilation. Up to the syntactic analysis, specific tokens were needed to distinguish which rule to apply, or various levels of rules were necessary to declare priorities and resolve potential conflicts. Once all these decisions have been made by the syntactic analyzer, it is no longer beneficial to retain a large volume of information.

markdown
Copy code
# MiniPython Compiler Code Explanation

## Abstract Syntax Tree (AST)

The Abstract Syntax Tree (AST) is a hierarchical representation of the syntactic structure of a MiniPython program. It is constructed during the parsing phase and serves as an intermediate representation that simplifies subsequent stages of the compiler, such as semantic analysis and code generation.

### Goal

The `goal` production represents the root of the AST, encapsulating all the commands within the MiniPython program.

```
goal = commands *;
```
The `commands` production is a collection of either functions or statements, making up the body of the MiniPython program. (It is necessary)
```
commands = {func} function | {stat} statement;
```

The `function` production defines the structure of a MiniPython function within the AST. It includes **only** an identifier (function name), a list of arguments, and a statement block.
```
function = {def_func} id argument* statement;
```

The `argument` production represents the arguments of a function, including an identifier, an optional assignment value and an optional ciav (comma id assign value).
```
argument = {arg} id assign_value* ciav*;
```

The `ciav` production represents comma-separated identifiers with optional assignment values within the arguments of a function.
```
ciav = {comma_id} id assign_value*;
```
The `assign_value` production represents an assignment value within function arguments.
```
assign_value = {asvalue} valueno;
```
The `statement` production encompasses various types of statements  such as if statements, while loops, for loops, return statements, print statements, and more. Each type of statement has its corresponding AST node. All of them cointain the necassary information such as id's, left and right exapressions, comparisons etc. 
```
{func_call_statement} function_call ;
```
The `function_call` production represents a function call within the AST, including the function identifier and a list of argument values.

```
function_call = {func_call} id arglist* ;
```
The `arglist` production represents a list of argument values within a function call.
```
arglist = {arglist} [l]: expression [r]:expression*;
```

The `comparisons` production represents logical comparisons within the AST, including logical AND, OR, and NOT operations . [All of the contain the nesessary information]
```
comparisons = {or} [l]:comparisons [r]:comparisons | ... | {greatc}[lpar]:expression [rpar]:expression ;
```
The `expression` production represents arithmetic and logical expressions within the AST. It includes various operations like addition, subtraction, multiplication, division, power, and more. All of them contain the necessary information such as id, left and right expression etc
```
expression = {value} valueno | {addition_ex} [l]:expression [r]:expression | ... | {brackets_expr_value} [l]:expression [r]: expression*;
```

The `valueno` production represents values within expressions, including function calls, identifiers, numbers, strings, and the special 'None' value.
'''
valueno = {func_call_value} id function_call | ... | {none};
'''

The `num` and `id` productions represent numbers and identifiers, respectively, within the AST.
```
num = {num} number;
id = {id} ident;
```
### -> New
In the provided code, the -> New syntax is used to create new instances of classes representing nodes in the Abstract Syntax Tree (AST). Let's break it down:

**Creating AST Nodes:**
```
goal = commands *{-> New goal([commands])};
```
Here, when the parser encounters a goal production, it creates a new instance of the goal class in the AST. The New goal([commands]) part specifies the creation of a new goal node, and the [commands] argument initializes its properties.

**Creating AST Nodes for Commands:**
```
commands = 
   {func} function{-> New commands.func(function)} | 
   {stat} statement{-> New commands.stat(statement)};
```
This is used when defining the commands production. Depending on whether it encounters a function (func) or a statement (stat), it creates a new instance of the corresponding class (commands.func or commands.stat) in the AST that has been analyzed above.

**Creating AST Nodes for Functions:**

```
function = 
   {def_func} tab * def id l_par argument? r_par semi statement{-> New function.def_func(id, [argument], statement)};
   ```
When the parser identifies a function definition, it creates a new instance of the function.def_func class in the AST, capturing details such as the function name (id), arguments, and the function body (statement).

**Creating AST Nodes for Statements:**
```
statement =
   {if_statement} tab * if comparison semi statement{-> New statement.if_statement(comparison.comparisons, statement)} | 
   {while_statement} tab * while comparison semi statement {-> New statement.while_statement(comparison.comparisons, statement)} |
   ...
```
Similar to functions, when parsing different types of statements (e.g., if statements, while loops, etc.), new instances of the corresponding statement classes are created in the AST.

The `-> New` syntax is essentially instructing the parser to construct and instantiate the appropriate AST node classes with the provided information from the parsed code. This approach helps in organizing and representing the structure of the MiniPython code in a hierarchical manner, making it easier for subsequent compiler stages to process and transform the code.



















## Error Handling in MiniPython Compiler : 
### Table of Contents
1. [Usage of Undeclared Variables](#1.-usage-of-undeclared-variables)
2. [Calling Undeclared Functions](#2.-calling-undeclared-functions)
3. [Incorrect Function Argument Definitions](#3.-incorrect-function-argument-definitions)
4. [Use of Variables with Different Types](#4.-use-of-variables-with-different-types)
5. [Operations Involving None](#5.-operations-involving-none)
6. [Incorrect Usage of Functions](#6.-incorrect-usage-of-functions)
7. [Repetition of Function Declarations with the Same Number of Arguments](#7.-repetition-of-function-declarations-with-the-same-number-of-arguments)

---
Disclaimer:
We have created a `Function.java` file that stores the name, the total, the default parameters of a function defined.
### 1. Usage of Undeclared Variables
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

### 2. Calling Undeclared Functions
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

### 3. Incorrect Function Argument Definitions
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
 
### 4. Use of Variables with Different Types
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
- <b>Explanation:</b> Ensure that values that should be of integer type, according to either how they're used or initialized, are indeed of integer type.
- <b>How it is addressed:</b> In an `inIdId` node, we check two cases: `a)` if the id is used as an argument on a function call, is the type of the id corresponding to the type that is expected from the initialization of the function and `b)` if the id is used in an operation of an assignment does it match the other ids/expressions/values of the operation?

### 5. Operations Involving None
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


### 6. Incorrect Usage of Functions
```python 
def add(x,y):
   return "hello world"
print add(2,1)+2
```
- <b>Explanation:</b>  Ensure that the case of any function call should be in accordance to the return type of the called function. For example if the function's return type is String we shouldn't be able to add it with an Integer value in an Assignment. 
- <b>How it is addressed:</b> In an `inAFuncCallFunctionCall` node we check the return type of the function. The return type could also emerge from the node `AIdId` that is a "descendant" node of an `AArglistArglist` node, since there are functions with a return type completely dependent to the arguments passed on the function call. Then we check the instance in which the function is called from the node's "ancestor" nodes (for ex: is it in a Multiplication? is it in an Addition?). If the return type does not match the type of the operation then we print an error. The type of the operation is either classified before the function call, or the function call defines the type of the operation and then we check the following values to match that of the function.

### 7. Repetition of Function Declarations with the Same Number of Arguments
```python 
def add(x,y):
def add(x,y,z=1)
```
```python 
def add(x,y,z):
def add(x,y,z=1)
```
- <b>Explanation: </b> The above declarations are incorrect. Declaring the same function name with the same number of arguments is incorrect. This also includes cases where one function has default values for some arguments. .
- <b>How it is addressed:</b> 
    1. We have a method `countVars` that counts the number of total and default parameters of a new defined function and returns it as LinkedList. (Pos 0 contains the number of function's total parameters and pos 1 contains the number of function's default parameters ).
    2. we check if the function name is contained in the `functions` hashtable. if yes, we count the total and default vars (parameters) using `countVars` method. For every function with the same name we count again the total and default parameters of each one and if the number is not accepted depending on the number of total and default parameters we print the error. 
    3. if the above code passes without errors, we create a new `Function` and we add it in `functions`



