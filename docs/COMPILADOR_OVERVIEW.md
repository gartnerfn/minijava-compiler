COMPILADOR MINI-JAVA — Visión general

Este documento describe de forma breve y clara las partes principales del compilador que existe en este repositorio y qué hace cada componente. Está pensado para desarrolladores que quieran entender la arquitectura general y localizar rápidamente dónde implementar cambios.

1. Punto de entrada

- Archivo: `src/src/Main.java`
- Propósito: Punto de entrada del programa. Se encarga de abrir el archivo fuente mediante `SourceManager`, instanciar el analizador léxico (`LexicalAnalyzer`) y luego el analizador sintáctico (`SyntaxAnalyzer`). Después de realizar el análisis llama a la comprobación semántica a través de `SymbolTable` (métodos `isWellDeclared()` y `consolidate()`). También captura y muestra excepciones léxicas, sintácticas y semánticas.

2. Source Manager (gestión de entrada de texto)

- Interfaces/implementaciones: `src/sourceManager/SourceManager.java`, `src/sourceManager/SourceManagerImpl.java`
- Propósito: Abstraer la lectura de caracteres desde un archivo. Proporciona métodos para abrir/leer caracteres (`getNextChar()`), obtener la línea y columna actuales y cerrar el recurso. El analizador léxico consulta aquí el siguiente carácter y obtiene el número de línea/columna para construir tokens y reportar errores.

3. Analizador Léxico (tokenización)

- Archivo principal: `src/lexicalAnalyzer/LexicalAnalyzer.java`
- Excepciones relacionadas: `src/lexicalAnalyzer/exceptions/*` (por ejemplo `LexicalException`, `UnclosedStringException`, `IntegerOutOfBoundsException`, etc.)
- Propósito:
  - Lee caracteres desde `SourceManager` y agrupa lexemas en tokens (`Token`).
  - Detecta palabras reservadas, identificadores, literales (int, char, string), operadores y separadores.
  - Maneja y acumula excepciones léxicas en una lista para mostrarlas posteriormente.
  - Exposición pública: `nextToken()` (obtiene el siguiente token) y `getExceptions()` (devuelve errores léxicos detectados).

4. Analizador Sintáctico (parsing)

- Archivo principal: `src/syntaxAnalyzer/SyntaxAnalyzer.java`
- Excepciones relacionadas: `src/syntaxAnalyzer/exceptions/SyntaxException.java`
- Propósito:
  - Consume tokens del `LexicalAnalyzer` y valida la estructura del programa según la gramática (parsing predictivo/descendente recursivo en este proyecto).
  - Construye la representación inicial del programa en estructuras internas: crea `Class`, `Interface`, `Method`, `Attribute`, `Constructor`, `Parameter`, etc., y las registra en la `SymbolTable`.
  - Valida constructos sintácticos (por ejemplo firma de métodos, declaraciones, parámetros, bloques, sentencias).
  - Lanza `SyntaxException` cuando el token esperado no coincide.

5. Analizador Semántico (chequeos semánticos)

- Paquetes y archivos clave:
  - `src/semanticAnalyzerI/entities/` — entidades que modelan clases, interfaces, métodos, atributos, constructores, parámetros, rutina y tabla de símbolos (`Class.java`, `Interface.java`, `Method.java`, `Attribute.java`, `Constructor.java`, `Parameter.java`, `Routine.java`, `Entity.java`, `SymbolTable.java`, `Constructor.java`, `Attribute.java`).
  - `src/semanticAnalyzerI/types/` — representación de tipos (`Type.java`, `PrimitiveType.java`, `ReferenceType.java`, `VoidType.java`).
  - `src/semanticAnalyzerI/exceptions/SemanticException.java` — excepción usada para reportar errores semánticos.

- Propósito:
  - Las clases en `entities` representan la información semántica del programa. Durante el parsing el `SyntaxAnalyzer` crea instancias de estas clases y las añade a la `SymbolTable`.
  - `SymbolTable` mantiene las clases e interfaces declaradas, proporciona métodos para agregar y buscar entidades, inicializa tipos predefinidos (por ejemplo `Object`, `System`, `String`) y coordina dos fases importantes: `isWellDeclared()` (verificaciones de integridad/validez declarativa) y `consolidate()` (propagación/chequeo final de herencia/implementaciones).
  - Chequeos semánticos implementados (no exhaustivo): duplicados (clase, interfaz, atributo, método, parámetro, constructor), tipos inexistentes para referencia, firmas y cuerpos de métodos (abstract/final/static/visibilidad), reglas de herencia (extiende/implementa, final/static, herencia circular), implementación de métodos heredados y de interfaces, correspondencia de tipos en redefiniciones, constructores.
  - Todas las condiciones semánticas críticas lanzan `SemanticException` con mensajes descriptivos.

6. Tipos y tokens

- Token: la clase `Token` (ubicada en `src/Token.java`) es la unidad que pasa el analizador léxico al sintáctico. Contiene el tipo de token, el lexema y el número de línea.
- Tipos semánticos: `PrimitiveType` (int, char, boolean), `ReferenceType` (clases), `VoidType`. Estas clases contienen el nombre del tipo y la línea donde se usó para poder reportar errores con contexto.

7. Excepciones y mensajes de error

- Léxico: `lexicalAnalyzer.exceptions` contiene excepciones que construyen mensajes detallados con contexto (línea, columna, puntero a la posición en la línea). El analizador léxico acumula estas excepciones para que `Main` las muestre.
- Sintáctico: `syntaxAnalyzer.exceptions.SyntaxException` se lanza cuando se encuentra un token inesperado.
- Semántico: `semanticAnalyzerI.exceptions.SemanticException` se lanza para errores semánticos; el mensaje incluye el lexema problemático y la línea.

8. Recursos y pruebas

- Carpeta `resources/` contiene ejemplos de entrada (programas Mini-Java) con y sin errores. Sirve para pruebas manuales o automatizadas.

9. Flujo de ejecución completo

1. `Main` abre el archivo fuente con `SourceManagerImpl`.
2. `LexicalAnalyzer` se inicializa con el `SourceManager` y tokeniza el archivo, almacenando posibles errores léxicos.
3. `SyntaxAnalyzer` consume tokens y construye las entidades semánticas, registrándolas en `SymbolTable`.
4. Tras el parser, `Main` solicita a `SymbolTable.isWellDeclared()` para chequear declaraciones y luego `SymbolTable.consolidate()` para propagar y verificar herencias/implementaciones.
5. Si hay errores léxicos, sintácticos o semánticos, éstos se muestran (el flujo principal captura y imprime `LexicalException`, `SyntaxException` y `SemanticException`).

10. Dónde tocar para qué

- Añadir nuevas reglas léxicas: `src/lexicalAnalyzer/LexicalAnalyzer.java` y, si aplica, nuevas excepciones en `lexicalAnalyzer/exceptions/`.
- Cambiar gramática o añadir construcciones sintácticas: `src/syntaxAnalyzer/SyntaxAnalyzer.java`.
- Agregar/ajustar reglas semánticas: `src/semanticAnalyzerI/entities/*` y `src/semanticAnalyzerI/types/*`. La `SymbolTable` es el lugar central para coherencia global.
- Cambiar lectura de archivos o encoding: `src/sourceManager/*`.

---

Generé este documento con un resumen de las responsabilidades de cada parte y las rutas de archivos relevantes. Si quieres, lo guardo también en el repo (ya lo hice en `docs/COMPILADOR_OVERVIEW.md`) y puedo:
- Extender cada sección con ejemplos concretos de código que desencadenen cada error.
- Añadir un diagrama simple de componentes.
- Generar un JSON con la estructura del proyecto y las responsabilidades (útil para herramientas de documentación automáticas).

¿Deseas que agregue ejemplos para cada tipo de error semántico o que lo transforme en un README más detallado?