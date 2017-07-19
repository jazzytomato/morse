# Morse

This app is a CLI for encoding text to morse code and obfuscating the result. It has been developed as part of a code challenge (instructions below).

I have written it in Clojure as a learning exercise, but the JVM slow startup time would not be ideal for this kind of interactive application. Alternatively, it could be compiled to JS and run with NodeJS.
### Usage

The program accepts an optional filename, if provided, it will output the result to output.txt

Otherwise, you will be prompted for a string to encode.

To run in dev mode:

    lein run [filename]

To compile and run a jar

    lein uberjar
    java -jar target/uberjar/morse-0.1.0-standalone.jar [filename]


## Premise
You're competing in a battle on a far away planet and you're in some trouble. You need to send a distress call to your home base for
reinforcements, however, enemy agents are listening. Luckily your team have a secret encoding for messages. It's
Morse code with further obfuscation.

## Challenge
Write a program that will accept text either from stdin, or as a file path and will translate the alphanumeric sentence
to Morse code. Then for bonus points, obfuscate the Morse code. Below is a list of international Morse code and the algorithm
for obfuscation. Separate letters with pipe (|), and separate words with forward slash (/).

### International Morse Code
* A .-
* B -...
* C -.-.
* D -..
* E .
* F ..-.
* G --.
* H ....
* I ..
* J .---
* K -.-
* L .-..
* M --
* N -.
* O ---
* P .--.
* Q --.-
* R .-.
* S ...
* T -
* U ..-
* V ...-
* W .--
* X -..-
* Y -.--
* Z --..
* 0 -----
* 1 .----
* 2 ..---
* 3 ...--
* 4 ....-
* 5 .....
* 6 -....
* 7 --...
* 8 ---..
* 9 ----.
* Fullstop .-.-.-
* Comma --..--

### Obfuscation
For obfuscation, your team decided to replace the number of consecutive dots with a number, and replace the number of consecutive dashes with the letter of the alphabet at that position. E.g. S = ... = 3, Q = --.- = b1a, F = ..-. = 2a1.

### Example
* Sentence: I AM IN TROUBLE
* Morse Code: ../.-|--/..|-./-|.-.|---|..-|-...|.-..|.
* Obfuscated Morse Code: 2/1A|B/2|A1/A|1A1|C|2A|A3|1A2|1

### Input
An example input text file:

```
HELLO
I AM IN TROUBLE
```

### Output
A text file in the following format:

```
4|1|1A2|1A2|C
2/1A|B/2|A1/A|1A1|C|2A|A3|1A2|1
```

### What we're looking for
The details matter. Tests are expected, as is well written, simple idiomatic code. A good submission will be
self explanatory, the tests will be good documentation for the code and it will gracefully handle strange or
invalid inputs. Classes or functions have clearly defined responsibilities. Test cases cover likely problems
with input data.

It’s an excellent answer if you learned something from reading the code.