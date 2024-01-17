# miniPython Grammar 
![python](https://editor.analyticsvidhya.com/uploads/58004python.jpg)
**Project Description**
### BNF Form:
### NON-TERMINALS
- **Goal::=** (Function|Statement)* `<EOF>`
- **Function::=** "def" Identifier "(" Argument? ")" ":" Statement
- **Argument::=** Identifier ("=" Value )? ( "," Identifier ("=" Value )?)*
- **Statement::=**
  - tab* "if" Comparison ":" Statement
  - tab* "while" Comparison ":" Statement
  - tab* "for" Identifier "in" Identifier ":" Statement
  - tab* "return" Expression
  - tab* "print" Expression ("," Expression)*
  - tab* Identifier ( "=" | "-=" | "/=" ) Expression
  - tab* Identifier "[" Expression "]" "=" Expression
  - tab* "assert" Expression ("," Expression )?
  - tab* Function Call

- **Expression::=**
  - Expression ( "+" | "-" | "*" | "/" | "%" | "**" ) Expression
  - Identifier "[" Expression "]"
  - Function Call
  - Value
  - Identifier
  - Expression( "++" | "--" )
  - "len" "(" Expression ")"
  - ( "max" | "min" ) "(" Value ( "," Value )* ")"
  - "(" Expression ")"
  - "["Expression ( "," Expression)*"]"

- **Comparison::=**
  - Comparison ( "and" | "or") Comparison
  - "not" Comparison
  - Expression ( ">" | "<" | ">=" | "<=" |"!=" | "==") Expression
  - "true"
  - "false"

- **Function Call::=** Identifier "(" Arglist? ")"

- **Arglist::=** Expression ( "," Expression )*

- **Value::=**
  - Identifier "." Function Call
  - Number
  - "<STRING_LITERAL>"
  - '<STRING_LITERAL>'
  - "None"

- **Number::=** `<INTEGER_LITERAL>`

- **Identifier::=** `<IDENTIFIER>`

- `<STRING_LITERAL>`: A word containing letters (both lowercase and uppercase) and spaces.
- `<INTEGER_LITERAL>`: An integer or decimal number.
- `<IDENTIFIER>`: A variable name.

For operator precedence, refer to the table at the following link: [Python Operator Precedence and Associativity](https://www.programiz.com/python-programming/precedence-associativity)



![image](https://media.istockphoto.com/id/1134880364/photo/grammar-word-from-wooden-blocks.jpg?s=612x612&w=0&k=20&c=V2dS_iEWmy9vSyb0myx2OXaVZSHFFYvXiFmto0Ab7r0=)

## miniPython Language

The miniPython language is a subset of Python, and therefore, the meaning of a program in miniPython is the same as that of the equivalent program in Python.

You will use the SableCC tool, which will facilitate the construction of the compiler. Additional assistance can also be found in the book "Modern Compiler Implementation in Java" by Andrew W. Appel.

## Supported Commands

- Assignment of integers, decimals, and strings to variables
- Assignment of a list to a variable
- Declaration of functions with simple arguments and arguments with default values
- Commands: if, print, while, and for
- Function calls
- Single-line comments
- Code Example
- BNF (Backus-Naur Form) of the language

## Project Structure

The project will be divided into 2 Phases:

### Phase A
<details>
<summary>1. Lexical analysis of the miniPython language.</summary>

## Exercise: Building a Lexer for miniPython using SableCC

## Purpose of the Exercise

The aim of this exercise is to construct a lexer for miniPython using the SableCC tool.

## Necessary Files

You are provided with the `sablecc.jar` file, which you should download locally and copy into a `lib` folder within your working directory. For example, if your working directory is named "compiler," create a folder named "lib" inside it and copy the `sablecc.jar` file there. Additionally, copy the `sablecc.bat` file (for Windows) into your working directory.

The `sablecc.bat` file facilitates the execution of SableCC and contains the command:

```sh
java -jar lib\sablecc.jar %1 %2 %3 %4 %5 %6 %7 %8 %9
```
you are also given the LexerTest1.java file, which includes a main method that invokes the lexer and displays the results on the screen.

For those working on the exercise in a Linux or IRIX environment, use the sablecc file instead of sablecc.bat. In this case, ensure that you have added execution permissions to the downloaded sablecc file (chmod +x sablecc).

Creating the Lexer
Your task is to create a grammar definition file. This file may contain the following sections for this phase:
```sh
Package minipython; // Declaration of the package, hence the folder, where generated files will be placed.

Helpers // Helper elements for the sections below
...

States // Declaration of states that the lexer can be in
...

Tokens // Lexical units of the lexer
...
```
The package declaration must be minipython as it is used in the imports of LexerTest1.java. Brief instructions for lexical analysis are provided.

Suppose your file is named minipython.grammar. Then, from your working directory, execute:
```sh
sablecc minipython.grammar
```
After execution, a directory named minipython should be created, containing subdirectories: analysis, lexer, node, and parser. Immediately after, execute from the same directory:
```java
javac LexerTest1.java
java LexerTest1 minipythonexample.py
```

The result should be the printing of all tokens separated by spaces.
</details>

<details>
<summary> 2. Syntax analysis of the miniPython language.<summary>

# Exercise: Building a Parser for miniPython using SableCC
## Purpose of the Exercise
The purpose of this exercise is to construct a parser for miniPython using the SableCC tool. Essentially, this is an extension of the previous exercise on the lexer.
## Necessary Files
You should rely on the grammar definition file you created in the previous phase and extend it. SableCC is required for this task as well. You are also provided with the `ParserTest1.java` file, which includes a `main` method that invokes the parser and displays the results on the screen.
## Creating the Parser
Additional sections should be added to the grammar definition file, below the mentioned sections related to lexical analysis. The following additional sections should be added:

  ```sh
  Ignored Tokens // Tokens that are ignored, typically comments and spaces
  ...

  Productions // The rules of the language grammar
  ...

  ```
In the Productions section, write the rules that constitute the grammar of the desired language. It should be noted that the provided grammar is ambiguous. This means that if you try to convert it directly to a SableCC grammar definition file, there will be several reduce/reduce or shift/reduce conflicts. Therefore, it is necessary to rephrase or add some rules to resolve priority issues between rules where needed. However, the semantics of the language must not change. An example of handling priority is given. Brief instructions for syntactic analysis in SableCC are also provided.

Suppose your file is named minipython.grammar. Then, execute the following commands in the initial directory:
  ```sh
  sablecc minipython.grammar
  javac ParserTest1.java 
  java ParserTest1 minipythonexample.py
  ```
The correct result should be the printing of all tokens separated by spaces.
</details>

### Phase B
<details>
<summary>1. Abstract syntax trees for the miniPython language.</summary>

# Exercise: Extending Grammar and Building an Abstract Syntax Tree (AST) for miniPython using SableCC

## Purpose of the Exercise

The purpose of this exercise is to extend the grammar definition file derived from the two previous exercises. The goal is to automate the construction of abstract syntax trees (AST) using the SableCC tool.

## Provided Code

You should build upon the grammar definition file you created in the previous phases and extend it. SableCC is also required for this task. Additionally, you are provided with the `ASTPrinter.java` and `ASTTest1.java` files. These will help you verify the correctness of the abstract syntax tree through visualization of the AST.

## Creating the Abstract Syntax Tree (AST)

The aim of constructing an abstract syntax tree is to discard elements and nodes in the specific syntax tree that are unnecessary for the subsequent stages of compilation. When performing syntactic analysis, we needed specific tokens to distinguish which rule to apply or required various levels of rules to declare priorities and resolve potential conflicts. Once all of these decisions have been made by the syntactic analyzer, it is no longer beneficial to retain a large volume of information.

You should add the following section to the grammar definition file, below the mentioned sections related to lexical and syntactic analysis:

```java
Abstract Syntax Tree // Declaration of the AST
...
```
At the same time, you need to modify the Productions section from the previous phase to align with the new Abstract Syntax Tree section. Explanations for the transformations to help you create abstract syntax trees are available at AST transformations in SableCC 3.0. Instructions in Greek for AST transformations in the SableCC tool are also provided.

Suppose your file is named minipython.grammar. Then, execute the following commands:
```java
sablecc minipython.grammar
javac ASTTest1.java
java ASTTest1 example.py
```
You will observe that the output now includes the abstract syntax tree.
</details>

<details>
<summary>2. Symbol Table - Semantic Analysis of the miniPython language.</summary>

## Exercise: Semantic Analysis for MiniPython Programs

## Purpose of the Exercise

The goal of this exercise is to perform semantic analysis on a program written in MiniPython. To achieve this, you will first construct a symbol table for storing elements necessary for semantic analysis, such as methods and variables. The symbol table will be later used by a type checker. The analysis should be conducted on the abstract syntax tree (AST) you generated in the previous exercise.

## Provided Code

Recall that SableCC has generated various `.java` files based on the grammar file you created in the previous stages of the project. These files are organized into suitable directories according to their categories. You should build upon these files to proceed with semantic analysis.

## Creating and Populating the Symbol Table

You need to define classes that will be used to store symbols in MiniPython. As mentioned earlier, symbols include methods, method parameters, local variables, and fields.

Next, you will use Visitors to traverse the AST. SableCC provides a ready-made visitor called `Switches`. To understand how to work with these visitors in the context of SableCC, you can refer to Etienne Gagnon's thesis. In summary, you should write a visitor class that extends (`extends`) the `DepthFirstAdapter` class found in the `MiniPython/analysis` directory. In this class, you will override (`override`) the methods needed from `DepthFirstAdapter`. The methods you write should aim to populate the symbol table and detect errors during this process.

Additional instructions for this stage can be found in the documents:
- [Instructions for Visitors - DepthFirstAdapter (MS Word)](link)
- [Instructions for AST Traversal](link)

Whenever an error is detected, an appropriate error message should be displayed.

Ideally, you should create at least two visitors to perform the following checks:

**1) Use of an undeclared variable, e.g.,**

  ```python
   def add(x, y):
       return x + y
   print k
  ```
  A variable is considered declared if it has been assigned a value or used as an argument in a function declaration. All variables are global.

  *Note: The following code is incorrect because the variable is used before it is declared:*

**2) Call to an undeclared function (here a function can be used before its declaration).**

**3) Incorrect parameter definitions in a function call.**

*Note: Pay attention to default values. The following code is correct:*
  ```python
  def add(x, y=2):
      return x + y
  print add(1)
  ```
**4) Use of a variable of a different type (e.g., Integer with String) as an integer.**
  *Note: Be careful with function calls. The following code is incorrect:*
  ```python
  def add(x, y):
      return x + y
  k = "hello world"
  print add(2, k)
  ```
**5) Operations in which None is used as operators.**

**6) Incorrect usage of a function, e.g.,**

  ```python
  def add(x, y):
      return "hello world"
  print add(2, 1) + 2
  ```
**7) Redefinition of a function with the same number of parameters.**

*Pay attention: If a function has 2 parameters and one with the same name has 3 but one has a default value, this is still incorrect. That is, the following declarations are incorrect:*
```python
def add(x, y):
def add(x, y, z=1)
#Also, the following declarations are incorrect:
def add(x, y, z):
def add(x, y, z=1)
```
No additional files are provided at this stage. You will modify the ParserTest1.java file to assist you in checking the correctness of your implementation. Specifically, after the line:

```java
Start ast = parser.parse();
```
in the ParserTest1.java file, you should add a line for each traversal of the abstract syntax tree using a Visitor. Assuming you define a class named SymbolTablePopulation, which inherits from DepthFirstAdapter, to check the results of your implemented code, add the line:

```java
ast.apply(new SymbolTablePopulation());
```
In the example above, we assume that the constructor of the class you defined does not take any parameters. With the added line, an additional traversal of the abstract syntax tree is performed, allowing you to perform checks by overriding specific methods of the DepthFirstAdapter class in the SymbolTablePopulation class.

Additionally, remove the following line from the ParserTest1.java file:

``` java
System.out.println(ast);
```
You will notice that the output now contains error messages
</details>