**Example 1**: Play a trumpet layer with a single quarter note at Tempo 100
**Description**: The basis of this language is VAR. <br/>
A var can contain any number of musicVar and must contain an instrument and a tempo. <br/>
You can choose to group one or more vars in a single layer by using the LIST command.

SET VAR var1 NOTES(G[q]) INSTRUMENT(trumpet) TEMPO(100)

SET LIST layer1(var1)

START
PLAY layer1
STOP

-----------------------------

**EXAMPLE 2**: Play two layers simultaneously. <br/>
**Description**: You can play different layers simultaneously by using the SIMUL command. <br/>
However, these layers should have same size of variables, and these variables should have same number of notes and same tempo.

SET VAR var11 NOTES(C, B[w], C, C, D) INSTRUMENT(piano) TEMPO(100)
SET VAR var12 NOTES(C, B[w], C, C, D) INSTRUMENT(violin) TEMPO(100)
SET VAR var13 NOTES(C, B[w], C, C, D) INSTRUMENT(trumpet) TEMPO(100)

SET VAR var14 NOTES(B, A[w], F, D, A) INSTRUMENT(piano) TEMPO(100)
SET VAR var15 NOTES(B, A[w], F, D, A) INSTRUMENT(violin) TEMPO(100)
SET VAR var16 NOTES(B, A[w], F, D, A) INSTRUMENT(trumpet) TEMPO(100)


SET LIST layer11(var11, var14)
SET LIST layer12(var12, var15)
SET LIST layer13(var13, var16)
// all layers should have two vars if you want to play them simultaneously
// var11, var12, var13 should have same number of notes and tempo, same for var14, var15, var16 


START
PLAY SIMUL layer11, layer12, layer13
STOP

-----------------------------

**EXAMPLE 3**: Define a rhythm layer<br/>
**Description**: The rhythm layer will play in throughout the song and length can be determined by number of measures <br/>
A sub layer is composed of 16th not beats each must be defined as one of the following:<br/>
. - no sound
H - hi-hat
B - Base Drum
S - Snare
C - Crash Cymbal

SET VAR var1 NOTES(G[q], C[qq], D[qqq], E[qqqq]) INSTRUMENT(trumpet) TEMPO(100)
RHYTHM LAYER(B.BB...B.BB....B) LENGTH(2)

SET LIST layer1(var1)

START
PLAY layer1
STOP

-----------------------------

**EXAMPLE 4**: Make a loop
**Description**: Loops will repeat the number of times specified. Each iteration of the loop will run the length of the longest layer. <br/>
A loop must contain one, and only one, "PLAY" command.

SET VAR var1 NOTES(G[qq], D[q], E[h], F[qqq]) INSTRUMENT(PIANO) TEMPO(140)
SET VAR var2 NOTES(C, B[w], C, C, D) INSTRUMENT(violin) TEMPO(140)

SET LIST layer1(var1,var2) // OR you can choose to create two layers: layer1(var1) && layer2(var2)

START
LOOP 3 TIMES
PLAY layer1 // if you choose to create two layers, then this command needs to be: PLAY layer1, layer2
END LOOP
STOP

----------------------------

**EXAMPLE 5**: Use function command
**Description**: You can choose to use the function command to group different command together, and probably reuse this function later.

SET VAR var1 NOTES(G[qq], D[q], E[h], F[qqq]) INSTRUMENT(PIANO) TEMPO(140)
SET VAR var2 NOTES(C, B[w], C, C, D) INSTRUMENT(violin) TEMPO(140)

SET LIST layer1(var1)
SET LIST layer2(var2)

FUNCTION fun1 {
    LOOP 3 TIMES
    PLAY layer1, layer2
    END LOOP
}

START
fun1
PLAY layer1 
STOP

----------------------------
**EXAMPLE 6**: Loop within loop
**Description**: You can generate loops within loop.

SET VAR var1 NOTES(G[qq], D[q], E[h], F[qqq]) INSTRUMENT(PIANO) TEMPO(140)
SET VAR var2 NOTES(C, B[w], C, C, D) INSTRUMENT(violin) TEMPO(140)

SET LIST layer1(var1)
SET LIST layer2(var2)


FUNCTION fun {
    LOOP 3 TIMES
        PLAY layer1
        LOOP 2 TIMES
            PLAY layer2
        END LOOP
    END LOOP
}

START
fun1
STOP

----------------------------

YOUR TASK:

Make a song with a rhythm (any beat & length) and 3 layers using any instruments. <br/>
Play the two layers simultaneously, three times in a loop (Use function command to group the loop). <br/>
After the loop has finished play the third layer.

-----------------------------

