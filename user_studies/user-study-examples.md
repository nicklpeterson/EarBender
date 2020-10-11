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
**Description**: You can play two layers simultaneously by using the SIMUL command. <br/>
If one ends first it will just stop while the other continues.

SET VAR var1 NOTES(G[qq], D[q], E[h], F[qqq]) INSTRUMENT(PIANO) TEMPO(140)
SET VAR var2 NOTES(C, B[w], C) INSTRUMENT(violin) TEMPO(160)

SET LIST layer1(var1)
SET LIST layer2(var2)

START
PLAY SIMUL layer1, layer2
STOP

-----------------------------

**EXAMPLE 3**: Define a rhythm layer<br/>
**Description**: The rhythm layer will play in throughout the song and can containe three "sub" layers. <br/>
A sub layer is composed of 16th not beats each must be defined as one of the following:<br/>
. - no sound
H - hi-hat
B - Base Drum
S - Snare
C - Crash Cymbal

SET VAR var1 NOTES(G[q], C[qq], D[qqq], E[qqqq]) INSTRUMENT(trumpet) TEMPO(100)
RHYTHM LAYER1(HHHHHHHH) LAYER2(B...B...) LAYER3(..S...S.) TEMPO(100)

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

YOUR TASK:

Make a song with 3 layers using any instruments. <br/>
Play the two layers simultaneously, three times in a loop (Use function command to group the loop). <br/>
After the loop has finished play the third layer.

-----------------------------

SET VAR var1 NOTES(G[q]) INSTRUMENT(piano) TEMPO(100)
SET VAR var2 NOTES(F[q]) INSTRUMENT(piano) TEMPO(100)
SET VAR var3 NOTES(E[q]) INSTRUMENT(trumpet) TEMPO(100)

SET VAR var4 NOTES(G[qq], D[q], E[h], F[qqq]) INSTRUMENT(PIANO) TEMPO(140)
SET VAR var5 NOTES(C, B[w], C, C, D) INSTRUMENT(violin) TEMPO(140)

SET LIST layer1(var4)
SET LIST layer2(var5)
SET LIST layer3(var1, var2, var1)
SET LIST layer4(var1, var2, var3)

FUNCTION fun1 {
    LOOP 3 TIMES
    PLAY layer1, layer2
    END LOOP
}

START
fun1
PLAY layer3, layer4, layer2, layer2
PLAY SIMUL layer1, layer2, layer3, layer4
STOP

