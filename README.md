# cpsc410_project1_team23
Project 1 Team Repository

Link to demo: https://www.dropbox.com/s/uih7mxh4phjtt8l/Demo2.mp4?dl=0

Our DSL accepts a text file and plays the music defined the in the file.

The text file is first tokenized using the Tokenizer, which breaks the raw text into an array of tokens. Next, it is
 passed to the parser, which creates an abstract syntax tree from the tokens. The third step is validation by our
 validator, which checks for a few specific errors that are not checked by the parser. Finally, the evaluator
 traverses the AST , compiles a music object and plays it!

The Dsl consists of several components designed to give the programmer space to implement most songs.
- Variables: variables are the smallest song component. Each variable consists of one or more notes and has an
 instrument and a tempo. Variables must be defined before the START command in the program.
 The syntax looks like this: SET VAR NOTES(G#6[q]) INSTRUMENT(piano) TEMPO(150)
 The G#6[q] corresponds to a G sharp quarter note at the 6th octave. It is okay to leave out the #/b and the 6, the
 default octave is 5. You can add as many comma separated notes to a variable as you would like. In the zelda.txt
 example each variable corresponds to a measure.
- Functions: These are segments of code that can be used in the body, other functions, or lists. They must be defined
 before the START command. The syntax looks like this: FUNCTION fun1 { ... } where ... is your commands.
- Lists: These can be a list of any number/combination of variables, functions, and lists that have been previously
 declared. All lists must be declared before the START command. When lists are played, all the items in the list will
  be played in order.
- Rhythm: This is one of the most fun parts of this language. We added a very simple way for users to create beats.
 Just use the command RHYTHM LAYER(...) and add as many layers as you would like. The ... in each layer must contain
  exactly 16 beats each beat can be one of ("S" - acoustic snare , "X" - snare drum , "O" - acoustic bass , "o
  " - bass drum, "." - rest , "^" - open hi-hat , "`" - closed hi-hat , "*" - crash cymbal , "+" - ride cymbal). The
  rhythm will play for the entire song, you cannot change the ryhthm during the song. This was difficult to
  implement but we felt that it is a great feature for people making simple songs who want to add a beat with
  minimal code.
- START: this marks the beginning of the main execution.
- loops: Loops can be used in the main execution or in functions. They work just like loops in any programming
 language. The syntax is LOOP X TIMES ... END LOOP, where X is the number of times to execute the loop and ... is the
  commands to execute. 
- PLAY: the play command is followed by one or more comma separated declarations (functions, variables, lists). They
 are played in order. The syntax is: PLAY list1, list2, fun1
- PLAY SIMUL: The play simul command plays two to 7 declarations at the same time. The 7 limit comes from the
 underlying api we are using to generate the music. It is not allowed to play functions simultaneously if one or more
  of those functions contains a PLAY SIMUL. We added this restriction, because deeply nested PLAY SIMULS result in
  undefined behavior. In our users studies, we found that users were confused about what would happen if
  two declarations of different length are played simultaneously. We decided to require that all items in
  a play simul have the same number of beats. They can have different tempos, but will be played at the
  slowest tempo at any given time.
- STOP: This marks the end of the main execution. Anything added after stop will result in an error.

Create a new program by creating a text file in the root and adding that the name as an argument to the tokenizer. 

Another option is to just play with our zelda.txt file. The lists defined in that file all have the same number of
 beats, so they can be played simultaneously. 

A small example:<br/>
SET VAR measure1 NOTES(E5[h], E5[q], D5[q]) INSTRUMENT(guitar) TEMPO(150) <br/>
SET VAR measure2 NOTES(C5[h], C5[h]) INSTRUMENT(guitar) TEMPO(150) <br/>
SET VAR measure3 NOTES(D5[h], D5[q], F5[q]) INSTRUMENT(guitar) TEMPO(150) <br/>
SET VAR measure4 NOTES(E5[q], D5[q], C5[h]) INSTRUMENT(guitar) TEMPO(150) <br/>
SET VAR measure5 NOTES(G6[h], G6[q], F6[q]) INSTRUMENT(guitar) TEMPO(150)
SET VAR measure6 NOTES(E6[h], E6[q], E6[q]) INSTRUMENT(guitar) TEMPO(150)
SET VAR measure7 NOTES(D6[q], F6[q], E6[q], D6[q]) INSTRUMENT(guitar) TEMPO(150)
SET VAR measure8 NOTES(C6[w]) INSTRUMENT(guitar) TEMPO(150)

SET LIST line1(measure1, measure2, measure3, measure4)
SET LIST line2(measure5, measure6, measure7, measure8)

RHYTHM LAYER(^^X.^^X.^^X.^^X.) LAYER(O...O...O...O..*)
RHYTHM LAYER(.OOOO...........)

FUNCTION fun2 {
    LOOP 2 TIMES
        PLAY SIMUL line1, line2
    END LOOP
}
START
PLAY fun2
STOP

Here is our EBNF for the input text file:-
<br/>
PROGRAM ::= STATEMENT*<br/>
STATEMENT ::= SET | PLAY | FUNCTION | LOOP | START | STOP | RHYTHM<br/>
NAME ::= [A-Za-z0-9]+<br/>

SET ::= "SET " LIST | VAR<br/>
LIST ::= "LIST " NAME "(" NAME (", " NAME)* ")"<br/>
VAR ::= "VAR " NAME " " NOTES " " INSTRUMENT " " TEMPO<br/>
NOTES ::= "NOTES(" SINGLE_NOTE (", " SINGLE_NOTE)* ")"<br/>
SINGLE_NOTE ::= [A-G] FLAT_OR_SHARP OCTAVE ("[" DURATION "]")<br/>
FLAT_OR_SHARP ::= "#" | "b" | ""<br/>
OCTAVE ::= [1-8] | ""<br/>
DURATION ::= (“w” | “h” | “q” | “i” | “s” | “t” | “x” | “o”)+<br/>

INSTRUMENT ::= "INSTRUMENT(" INSTRUMENT_TYPE ")"<br/>
INSTRUMENT_TYPE ::= "piano"|"flute"|"violin"|"trumpet"|"guitar"|"voice"|"acoustic_bass"<br/>
TEMPO ::= "TEMPO(" [1-9]+ ")"<br/>
PLAY ::= "PLAY " ("SIMUL ")? NAME (", " NAME)*<br/>
RHYTHM ::= "RHYTHM" LAYER*<br/>
LAYER ::= "LAYER(" (S|X|O|o|.|^|`|\*|\+){16} ")"<br/>
START ::= "START"<br/>
STOP ::= "STOP"<br/>
FUNCTION ::= "FUNCTION " NAME " {" (PLAY | LOOP)* "}"<br/>
LOOP ::= TIMES LOOP|PLAY "END LOOP"<br/>
TIMES ::= "LOOP " [0-9]+ " TIMES"<br/>
