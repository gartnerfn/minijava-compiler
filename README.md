# Building and Running the MiniJava Compiler

## Project Overview

The MiniJava compiler is a pure Java project organized into five pipeline stages: source management, lexical analysis, syntax analysis, semantic analysis (I & II), and code generation.

---

## 1. Dependencies

There is **no build file** (no `pom.xml`, `build.gradle`, or `Makefile`) in the repository. The project requires:

- **Java** (compiled with standard `javac`)
- **JUnit 4** + **Hamcrest** — used by all test classes (e.g., `TesterDeCasosSinErrores`, `TesterDeCasosConErrores`). 
- **`CeIVM-cei2011.jar`** — an external virtual machine (CEI VM) required to *execute* the compiler's output assembly.

---

## 2. Build Commands

Since there is no build tool, you would compile manually from the project root, pointing to all source directories under `src/`. No compiled artifacts are present in the repository. The project is likely intended to be opened and built through an IDE (e.g., IntelliJ IDEA) with JUnit 4 on the classpath.

---

## 3. Entry Points

There is **one entry point class**:

### `src.Main` — Full compiler (lexing → parsing → semantic checking → code generation)

---

## 4. Command-Line Arguments

The `Main` class accepts **one required** and **one optional** argument:

| Argument | Position | Description | Default |
|---|---|---|---|
| `inFilePath` | `args[0]` | Path to the MiniJava source file | *(required)* |
| `outFilePath` | `args[1]` | Path for the generated assembly output | `out.txt` |

**Example invocations** (as used by tests):
```bash
# Full compile (lex + parse + semantic + codegen):
java src.Main resources/codeGeneration/sinErrores/gen-01.java out.txt

# Then run the generated code on the CeIVM:
java -jar CeIVM-cei2011.jar out.txt
```

---

## 5. Input File Format

Input files are **MiniJava source files** (`.java` extension). The language supports:

### Special First-Line Comment (for test harness)
The very first line must be a `///` comment encoding expected output values separated by `&`:
```
///1234&exitosamente
```
The test harness strips the `///` prefix and splits by `&` to get expected VM output substrings.

### Language Features
- **Reserved words**: `class`, `interface`, `extends`, `implements`, `public`, `private`, `static`, `void`, `boolean`, `char`, `int`, `abstract`, `final`, `for`, `if`, `else`, `while`, `return`, `var`, `this`, `new`, `null`, `true`, `false`
- **Class identifiers** start with an uppercase letter; **method/variable identifiers** start with a lowercase letter. 
- **Integer literals**: up to 9 digits (values exceeding that trigger an `IntegerOutOfBoundsException`). 
- **Char literals**: `'x'`, escape sequences, and 4-digit unicode escapes `'\uXXXX'`.
- **String literals**: `"..."` with escape sequences and unicode. 
- **Comments**: single-line `//` and multi-line `/* */`.
- **Operators**: `+`, `-`, `*`, `/`, `%`, `++`, `--`, `==`, `!=`, `<`, `<=`, `>`, `>=`, `&&`, `||`, `!`, `=`
- **Logical operators**: `&&` and `||` only (single `&` or `|` are lexical errors).
- **Entry point**: A class named `Init` (or similar) with a `static void main()` method.
- **Predefined classes**: `Object`, `System`, `String` are always available.
- **`System` methods**: `read()`, `printB(b)`, `printC(c)`, `printI(i)`, `printS(s)`, `println()`, `printBln(b)`, `printCln(c)`, `printIln(i)`, `printSln(s)`. 

**A complete valid MiniJava input example:**
```java
///55&exitosamente
class Test77 {
    static int fib(int n) { ... }
}
```

---

## 6. Output Format

### Stdout
- **Success**: prints `\n[SinErrores]` to stdout.
- **Lexical error**: prints `[Error:<lexeme>|<lineNumber>]` (ANSI red-colored) to stdout. 
- **Syntax error**: prints `[Error:<lexeme>|<lineNumber>]` to stdout. 
- **Semantic error**: prints `[Error:<lexeme>|<lineNumber>]` to stdout. 

### Output File (assembly for CeIVM)
On success, the compiler writes a text file (default: `out.txt`) containing CEI virtual machine assembly instructions: 

The file structure includes:
- A `.CODE` section preamble that calls `main` and then `HALT`. 
- Heap management routines (`simple_heap_init`, `simple_malloc`).
- Method/constructor bodies as labeled blocks of stack-machine instructions (`PUSH`, `CALL`, `LOAD`, `STORE`, `ADD`, `IPRINT`, `PRNLN`, etc.).
- An optional `.DATA` section for string literals. 

---

## Notes

- The file encoding for both input and output is **UTF-8**.
