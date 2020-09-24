## Milestone 1 (Week2)
**Project Description**: A DSL, written in Java, that allows users to create music by inputting chord or note names.
The user can define variables to reuse melodies and create loops within the song. The user can also choose their
own volume, pitch and tempo for the note. Eventually we also plan to allow users to add their own melodies in text
format into the DSL.<br/>

**Sample Syntax**:<br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;    SET refrain TO G, D, E, F<br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;    SET verse1 TO C, B, C<br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;    SET verse2 TO B, A, B<br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;    PLAY<br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;    TEMPO 140<br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;    LOOP 2 TIMES<br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;    verse1<br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;    refrain<br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;    verse2<br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;    END LOOP<br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;    STOP<br/></p>

**Feedback from TA**: Our TA commented that we need to make sure we are giving the user enough "programming space" to
 implement anything they want. Specifically that we should not limit the features to looping and fading. 
 
 To make the language more flexible in this regard, we will allow the users to use and layer different instruments and
 possibly define custom instruments. We will also allow the user to customize the sound globally by setting defining
 things like treble and bass. 
 
**Planned Follow Up Tasks**: We will work on developing more feature ideas and making our current ideas concrete.
 
 ## Milestone 2 (Week3)
**Planned division of main responsibilities between team members**
 
EBNF and UML Diagram: Everyone<br/>
Implement Java Music Api: Muriel<br/>
Tokenizer: Roy<br/>
Parser: Nick and Shobhit<br/>
Validator: Francis<br/>

**Roadmap for what should be done when**

September 25th: EBNF and UML Diagram<br/>
October 3rd: Implement our Music API using the Java Music API
October 13th: Implement Tokenizer, Parser, and Validator

**Summary of Progress so far**
Our syntax has been designed, it just needs to be formalized in an EBNF. We have also begun looking into Java music
API's that can be used to play music. Finally, we have divided up the core responsibilities among the group. We
are ready and stoked to start coding!

