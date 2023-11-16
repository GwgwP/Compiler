# miniPython Grammar Explanation

## Package Declaration
The grammar is defined under the `minipython` package.

## Helpers
- `digit`: Matches any digit from '0' to '9'.
- `letter`: Matches any uppercase or lowercase letter.
- `cr`: Represents a carriage return (enter).
- `lf`: Represents a line feed or newline character.
- `sp`: Represents a space character.
- `all`: Matches any ASCII character from 0 to 127.
- `eol`: Matches end-of-line characters, including line feed, carriage return, or carriage return followed by line feed.
- `not_eol`: Matches any character except end-of-line characters.

## Tokens
- Tokens are the building blocks of the miniPython language.
- Order of token declaration is crucial for their recognition by the lexical analyzer; hence, we employ the logic of 'longest string first' to prioritize token identification.

- **Arithmetic Operators:**
  - `plusplus`: '++'
  - `equalequal`: '=='
  - `minusminus`: '--'
  - `mineq`: '-='
  - `pluseq`: '+='
  - `diveq`: '/='
  - `multeq`: '*='
  - `plus`: '+'
  - `minus`: '-'
  - `pow`: '**'
  - `mult`: '*'
  - `mod`: '%'
  - `div`: '/'
  
- **Relational Operators:**
  - `noteq`: '!='
  - `lesseq`: '<='
  - `greateq`: '>='
  - `less`: '<'
  - `great`: '>'
  
- **Assignment and Logic Operators:**
  - `assign`: '='
  - `def`: 'def'
  - `not`: 'not'
  - `logic_and`: 'and'
  - `logic_or`: 'or'
  
- **Brackets and Parentheses:**
  - `l_br`: '['
  - `r_br`: ']'
  - `l_par`: '('
  - `r_par`: ')'
  
- **Others:**
  - `comma`: ','
  - `in`: 'in'
  - `if`: 'if'
  - `while`: 'while'
  - `for`: 'for'
  - `len`: 'len'
  - `min`: 'min'
  - `max`: 'max'
  - `print`: 'print'
  - `return`: 'return'
  - `assert`: 'assert'
  - `true`: 'true'
  - `semi`: ':'
  - `false`: 'false'
  - `else`: 'else'
  - `none`: 'None'
  - `quote`: '"'
  - `blank`: Matches spaces, line feed, or carriage return.
  - `line_comment`: Matches a line comment starting with '#' and ending with an end-of-line character.
  - `number`: Matches one or more digits.
  - `dot`: Matches a dot ('.').
  - `id`: Matches an identifier starting with a letter, followed by letters or digits.
  - `string_double_quotes`: Matches a string enclosed in double quotes.
  - `string_single_quotes`: Matches a string enclosed in single quotes.
  - `everything_else`: Matches any other character.


# Syntax Analysis Explanation

## Ignored Tokens
- `blank`: Represents spaces, line feed, or carriage return.
- `line_comment`: Represents comments starting with '#' until the end of the line.

These tokens are ignored during the syntax analysis.

## Productions
### `goal`
- Represents the main entry point for the syntax analysis.
- Consists of zero or more `commands`.

### `commands`
- Represents a sequence of either a `function` or a `statement`.

### `function`
- Describes the syntax for defining a function.
- Begins with the keyword `def` followed by the function name, optional arguments, and a statement.

### `argument`
- Represents the definition of a function argument.
- Consists of an identifier followed by an optional assignment value and zero or more comma-separated identifiers with optional assignment values.

### `ciav`
- Represents a comma-separated identifier with an optional assignment value.
- Used in the definition of function arguments.

### `assign_value`
- Describes the assignment of a value.
- Consists of an assignment operator followed by a value.

### `statement`
- Represents various types of statements, including conditional statements, loops, assignments, and function calls.

### `function_call`
- Describes the syntax for calling a function.
- Consists of a function name followed by optional arguments enclosed in parentheses.

### `arglist`
- Represents a list of arguments in a function call.
- Consists of one or more expressions separated by commas.

### `comma_expression`
- Represents a comma-separated expression.
- Used in function calls and print statements.

### `comparison`
- Describes logical comparisons using logical AND and OR operators.

### `afteror`, `afterand`, `afternot`
- Define the logical relationships between operands in comparisons. (Different productions for priority, or < and < not -> biggest priority hence it is in the last production)

### `comma_value`
- Represents a comma-separated value.

### `expression`
- Describes mathematical expressions involving addition, subtraction, and subset operations.

### `expressiono`
- Describes more complex expressions involving max, min, simple expressions, arrays, and function calls.

### `simple_expression`
- Represents simple mathematical expressions involving addition, subtraction, and factors.

### `factor`
- Represents factors in mathematical expressions, including multiplication and unary operators.
- Used for being able to have multiple + and - expressions without facing the problem of shift/reduce errors if we had "double" recursion. e.g.:

```
{addition_ex} [sa]:expression plus [sd]:expression |
{subtraction_ex} [ss]:expression minus [da]:expression;
```

## `multiplication`, `power`: Defining priority of +, -, *, /, %, ** 
### `multiplication`
- Describes multiplication, division, and modulo operations.

### `power`
- Represents exponentiation in expressions.

### `value`
- Represents values in expressions, including identifiers, numbers, strings, and function calls.


### `valueno`
- Represents more specific cases of values, including identifiers with dot notation, identifiers, numbers, strings, and `None`.

### `ident`
- Represents an identifier.

These productions define the syntax rules for the miniPython language, guiding the syntax analyzer in understanding and processing valid programs.


This grammar defines the structure of the miniPython language and will be used for lexical and syntactic analysis during the implementation of the miniPython compiler.

