## Milestone 1 (Week2)
**Project Description**: A DSL, written in Java, that allows users to create music by inputting chord or note names.
The user can define variables to reuse melodies and create loops within the song. The user can also choose their
own volume, pitch and tempo for the note. Eventually we also plan to allow users to add their own melodies in text
format into the DSL.<br/>

**Sample Syntax UPDATED 9/25/2020**:<br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;    SET layer1 NOTES(G[qq], D[q], E[h], F[qqq]) INSTRUMENT(PIANO) TEMPO(140)<br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;    SET layer2 NOTES(C, B[w], C) INSTRUMENT(violin) TEMPO(160)<br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;    SET layer3 NOTES(A, B, C) INSTRUMENT(piano) TEMPO(100)<br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;    RHYTHM LAYER1(HHHHHHHH) LAYER2(B...B...) LAYER3(..S...S.)<br/>

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;    PLAY<br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;    LOOP 3 TIMES<br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;    START layer1, layer2<br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;    END LOOP<br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;    START layer3<br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;    STOP<br/></p>

**Feedback from TA**: Our TA commented that we need to make sure we are giving the user enough "programming space" to
 implement anything they want. Specifically that we should not limit the features to looping and fading. 
 
 To make the language more flexible in this regard, we will allow the users to use and layer different instruments and
 possibly define custom instruments. We will also allow the user to customize the sound globally by setting defining
 things like treble and bass. 
 
 EDIT 9/25/2020: With more feedback from Amir we have come up with the following list of features:<br/>
 1. Users will be able to use loops within their songs.
 2. Users will be able to define as many sound layers as they want and can play up to three at any one time.
 3. Users can define the length of each note within a layer
 4. Users will be able to define the instrument used to play each layer.
 5. Users will be able to define the tempo of each layer.
 5. Users will be able to choose to define a global rhythm (beat) that will play as an additional layer throughout the
  entire song. 
 6. Users MAY be able to include fade in/out in their layers. We are not sure yet if we will include this feature
 , because the implementation seems like it may be very difficult.
 
**Planned Follow Up Tasks**: We will work on developing more feature ideas and making our current ideas concrete.
 
 ## Milestone 2 (Week3)
**Planned division of main responsibilities between team members**
 
 Overall Breakdown
EBNF and UML Diagram: Everyone<br/>
Implement Java Music Api: Muriel<br/>
Tokenizer: Roy<br/>
Parser/Evaluator: Nick and Shobhit<br/>
Validator: Francis<br/>

**Roadmap for what should be done when**

**September 25th**: EBNF and UML Diagram<br/>
**September 28th**: Concrete definition of the inputs and outputs of each Major Class. This includes specifying the
 tokens and the AST.<br/>
**October 2nd**: Implement our Music API using the Java Music API. Implement AST classes (including evaluate methods
).<br/>
**October 5th**: Finish Tokenizer, Parser, and Validator classes.<br/>
**October 13th**: Implement the top level controller and fully test implementation.<br/>
... The rest of the time will be spent on testing and debugging.

**Summary of Progress so far**:
Our syntax has been designed, it just needs to be formalized in an EBNF. We have also begun looking into Java music
API's that can be used to play music. Finally, we have divided up the core responsibilities among the group. We
are ready and stoked to start coding!

## MILESTONE 3
- user studies: ./user_studies
- EBNF: ./ebnf.txt

On advice from Amir we have added some two additional features:
1. Functions so users can easily re-use code
2. Variables that users can add to layers

Updated Sample Code:<br/>
SET VAR var1 NOTES(G[q]) INSTRUMENT(piano) TEMPO(100)<br/>
SET VAR var2 NOTES(F[q]) INSTRUMENT(piano) TEMPO(100)<br/>
SET VAR var3 NOTES(E[q]) INSTRUMENT(trumpet) TEMPO(100)<br/>
<br/>
SET VAR var4 NOTES(G[qq], D[q], E[h], F[qqq]) INSTRUMENT(PIANO) TEMPO(140)<br/>
SET VAR var5 NOTES(C, B[w], C, C, D) INSTRUMENT(violin) TEMPO(140)<br/>
<br/>
SET LIST layer1(var4)<br/>
SET LIST layer2(var5)<br/>
SET LIST layer3(var1, var2, var1)<br/>
SET LIST layer4(var1, var2, var3)<br/>
<br/>
FUNCTION fun1 {<br/>
&nbsp;&nbsp;&nbsp;    LOOP 3 TIMES<br/>
&nbsp;&nbsp;&nbsp;    PLAY layer1, layer2<br/>
&nbsp;&nbsp;&nbsp;    END LOOP<br/>
}<br/>
<br/>
START<br/>
PLAY fun1<br/>
PLAY layer3, layer4, layer2, layer2<br/>
PLAY SIMUL layer1, layer2, layer3, layer4<br/>
STOP<br/>

## MILESTONE 4
Status of the implementation:<br/>
EBNF and UML Diagram: Ongoing<br/>
Implement Java Music Api: Ongoing<br/>
Tokenizer: Finished<br/>
Parser: Ongoing<br/>
Evaluator: Will start when parser is ready<br/>
Validator: Will start when parser is ready<br/>


<br/>Planned timeline for the remaining days:<br/>
**October 10th**: Finish up the implementation for the ast and parser.<br/>
**October 12th**: Finish up the implementation for the evaluator and integration for the music library.<br/>
**October 13th**: Meeting to review the development progress.<br/>
**October 14th**: Finish up the implementation for the validator.<br/>
**October 13th**: Meeting to review the development progress. Start integration testings.<br/>
**October 16th**: Start to prepare the required documentations and demo video for the final submission.<br/>
**October 18th**: Finish up the required documentations and demo video. Wrap up the testings.<br/>
**October 19th**: Day of final submission.<br/>

<br/>Plans for final user study:<br/>
Provide users the example code snippets we prepared (6 examples covering all functionalities).<br/>
Answer and write down any questions users asked.<br/>
Give users the description of the task we want them to finish.<br/>
After they finish, compare their work with the sample solution.<br/>
Answer potential follow-up questions and ask for feedback on our language.<br/>

