# cpsc410_project1_team23
Project 1 Team Repository

Link to demo: https://www.dropbox.com/s/uih7mxh4phjtt8l/Demo2.mp4?dl=0

Our DSL accepts a text file and plays music from it.

The text file is first tokenized using the Tokenizer, then it is sent to the Parser, our Validator ensures that all the syntax and input commands are correct and then our Evaluator converts the parsed data into music.

We have used the jFugue library which is already a part of Java.

Here is our EBNF for the input text file:-

PROGRAM ::= STATEMENT*

STATEMENT ::= SET | PLAY | FUNCTION | LOOP | START | STOP | RHYTHM

NAME ::= [A-Za-z0-9]+

SET ::= "SET " LIST | VAR

LIST ::= "LIST " NAME "(" NAME (", " NAME)* ")"

VAR ::= "VAR " NAME " " NOTES " " INSTRUMENT " " TEMPO

NOTES ::= "NOTES(" SINGLE_NOTE (", " SINGLE_NOTE)* ")"

SINGLE_NOTE ::= [A-G] FLAT_OR_SHARP OCTAVE ("[" DURATION "]")

FLAT_OR_SHARP ::= "#" | "b" | ""

OCTAVE ::= [1-8] | ""

DURATION ::= (“w” | “h” | “q” | “i” | “s” | “t” | “x” | “o”)+

INSTRUMENT ::= "INSTRUMENT(" INSTRUMENT_TYPE ")"

INSTRUMENT_TYPE ::= ”piano”|”bass”|”guitar”|”trumpet”|”violin”|”flute”|”whistle”

TEMPO ::= "TEMPO(" [1-9]+ ")"

PLAY ::= "PLAY " ("SIMUL ")? NAME (", " NAME)*

RHYTHM ::= "RHYTHM" LAYER*

LAYER ::= "LAYER(" (S|H|B|X|O|o|.|^|`|\*|!|){16} ")"

START ::= "START"

STOP ::= "STOP"

FUNCTION ::= "FUNCTION " NAME " {" (PLAY | LOOP)* "}"

LOOP ::= TIMES LOOP|PLAY "END LOOP"

TIMES ::= "LOOP " [0-9]+ " TIMES"
